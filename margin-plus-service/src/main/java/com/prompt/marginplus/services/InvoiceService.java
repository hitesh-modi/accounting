package com.prompt.marginplus.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.util.*;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import com.prompt.marginplus.entities.HSN;
import com.prompt.marginplus.entities.InvoiceNumberDetail;
import com.prompt.marginplus.entities.Invoicedetail;
import com.prompt.marginplus.entities.SacMaster;
import com.prompt.marginplus.models.*;
import com.prompt.marginplus.repositories.StateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.prompt.marginplus.entities.ConsigneeDetail;
import com.prompt.marginplus.entities.CustomerDetail;
import com.prompt.marginplus.entities.Invoiceitemdetail;
import com.prompt.marginplus.entities.Invoiceitemtaxdetail;
import com.prompt.marginplus.entities.Productdetail;
import com.prompt.marginplus.entities.State;
import com.prompt.marginplus.entities.User;
import com.prompt.marginplus.repositories.ConsigneeRepository;
import com.prompt.marginplus.repositories.CustomerRepository;
import com.prompt.marginplus.repositories.InvoiceNumberRepo;
import com.prompt.marginplus.repositories.InvoiceRepository;
import com.prompt.marginplus.repositories.ProductRepository;
import com.prompt.marginplus.repositories.UserRepository;
import com.prompt.marginplus.types.TaxType;
import com.prompt.marginplus.utility.ConversionUtility;
import com.prompt.marginplus.utility.Util;


@Service("invoiceService")
public class InvoiceService implements IInvoiceService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceService.class);
	
	@Resource(name="customerRepository")
	private CustomerRepository customerRepository;
	
	@Resource(name="userRepo")
	private UserRepository userRepo;
	
	@Resource(name="invoiceNumberRepo")
	private InvoiceNumberRepo invoiceNumberRepo;
	
	@Resource(name="consigneeRepository")
	private ConsigneeRepository consigneeRepo;
	
	@Resource(name="invoiceRepo")
	private InvoiceRepository invoiceRepo;

	@Resource(name="userService")
    private IUserService userService;
	
	@Resource(name="productRepository")
	private ProductRepository productRepository;
	
	@Resource(name="stateRepo")
	private StateRepository stateRepo;
	
	@Value("${invoice.path.pdf}")
	private String invoiceGenerationPath;
	
	private static final String REGULAR_FONT="fonts/Montserrat-Regular.ttf";
	private static final String BOLD_FONT="fonts/Montserrat-Bold.ttf";

	@Override
	@Transactional
	public String createInvoice(final Invoice invoice, final String userid) {
		LOGGER.info("Creating invoice " + invoice);
		CustomerDetail customerEntity = null;
		ConsigneeDetail consigneeEntity = null;

		User user = userRepo.getUser(userid);

		customerEntity = createCustomerEntity(invoice.getCustomer(), user);
		if(invoice.isNewCustomer()) {
			//customerRepository.save(customerEntity);
		}
		
        if(invoice.getNewConsignee().equalsIgnoreCase("true")) {
		    consigneeEntity = createConsigneeEntity(invoice.getConsignee(), user);
		    //consigneeRepo.save(consigneeEntity);
        } else if(invoice.getNewConsignee().equalsIgnoreCase("SAME_AS_CUSTOMER")){
        	// Check if the Consignee with same name and contact details exists if yes then do not create a new consignee else create it.
        	Consignee consigneeModel = invoice.getConsignee();
        	ConsigneeDetail consigneeDetailFromDb = consigneeRepo.getConsigneeByNameAndMobileNumber(consigneeModel.getName(), consigneeModel.getMobileNo());
        	if(consigneeDetailFromDb == null) {
        		consigneeEntity = createConsigneeEntity(invoice.getConsignee(), user);
        		//consigneeRepo.save(consigneeEntity);
        	}
        	else
        		consigneeEntity = consigneeDetailFromDb;
        } else {
        	consigneeEntity = consigneeRepo.findById(invoice.getConsignee().getConsigneeId()).get();
        }

        Collection<InvoiceItem> invoiceItems = invoice.getInvoiceItemDetails();
        Invoicedetail invoiceEntity = createInvoiceEntity(invoice, consigneeEntity, customerEntity);
        invoiceEntity.setUser(user);

        boolean isConsigneeInSameState = false;
        if(consigneeEntity.getState().getStatecode().equals(user.getState().getStatecode())) {
            isConsigneeInSameState = true;
        }

        Set<Invoiceitemdetail> invoiceItemDetailsEntities = new HashSet<>();
        for (InvoiceItem currInvoiceItem:
        	invoiceItems) {
        	Invoiceitemdetail invoiceItemDetail = createInvoiceItemEntity(currInvoiceItem, invoiceEntity, isConsigneeInSameState);
        	invoiceItemDetailsEntities.add(invoiceItemDetail);
        }
        invoiceEntity.setInvoiceitemdetails(invoiceItemDetailsEntities);

        String invoiceNumber = generateInvoiceNumber(user);

        invoiceEntity.setID_InvoiceNumber(invoiceNumber);

        invoiceRepo.save(invoiceEntity);
        
        return invoiceNumber;
	}

	private String generateInvoiceNumber(final User user) {
		LOGGER.info("Generating invoice number");
		String invoiceNumber = "";

		Integer sequenceNo = invoiceNumberRepo.getInvoiceSequenceNumber(new java.sql.Date(Calendar.getInstance().getTimeInMillis()), user);
		if(sequenceNo == null)
			sequenceNo = 0;
		invoiceNumber = Util.getInvoiceNumber(sequenceNo);

		InvoiceNumberDetail generatedNumber = new InvoiceNumberDetail();
		generatedNumber.setInvoiceDate(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
		generatedNumber.setSequenceNo(sequenceNo+1);
		generatedNumber.setUser(user);

		invoiceNumberRepo.save(generatedNumber);

		LOGGER.info("Generated invoice number : " + invoiceNumber);
		return invoiceNumber;
	}

	private Invoicedetail createInvoiceEntity(Invoice invoice, ConsigneeDetail consigneeDetail, CustomerDetail customerDetail) {
		Invoicedetail invoiceDetail = new Invoicedetail();
		invoiceDetail.setConsigneeDetail(consigneeDetail);
		invoiceDetail.setCustomerDetail(customerDetail);
		invoiceDetail.setID_InvoiceTotalAmount(invoice.getGrandTotal());
		invoiceDetail.setID_InvoicePaidAmount(invoice.getAmountReceived());
		BigDecimal remainingAmount = invoice.getNetTotal().subtract(invoice.getAmountReceived());
		invoiceDetail.setID_InvoiceBalanceAmount(remainingAmount);
		invoiceDetail.setID_InvoiceDate(invoice.getInvoiceDate());
		invoiceDetail.setID_InvoiceNumber(invoice.getInvoiceNumber());
		invoiceDetail.setID_InvoiceDueDate(invoice.getInvoiceDueDate());
		invoiceDetail.setID_TaxAmount(invoice.getTotalTax());
		invoiceDetail.setID_GrandTotal(invoice.getNetTotal());
		return invoiceDetail;
	}
	
	
	
	private CustomerDetail createCustomerEntity(Customer customer, User user) {

	    CustomerDetail customerDetail = new CustomerDetail();
	    if (customer.getCustomerId() > 0) {
	    	return customerRepository.findById(new Long(customer.getCustomerId())).get();
		}
	    customerDetail.setCdCustomerId(customer.getCustomerId());
	    customerDetail.setCdCustomerName(customer.getName());
	    customerDetail.setCdCustomerAddress(customer.getAddress());
	    customerDetail.setCdCustomerGSTIN(customer.getGstin());
	    customerDetail.setCdCustomerMobile(customer.getMobileNo());
	    customerDetail.setCdCustomerEmail(customer.getEmail());
	    customerDetail.setCdCustomerPhone(customer.getPhoneNo());
	    customerDetail.setUser(user);
	    State state = stateRepo.findById(customer.getStateCode()).get();
	    customerDetail.setState(state);
	   
	    return customerDetail;
    }

    private ConsigneeDetail createConsigneeEntity(Consignee consignee, User user) {

        ConsigneeDetail consigneeDetail = new ConsigneeDetail();
        consigneeDetail.setConsigneeName(consignee.getName());
        consigneeDetail.setConsigneeAddress(consignee.getAddress());
        consigneeDetail.setConsigneeGSTIN(consignee.getGstin());
        consigneeDetail.setConsigneeEmail(consignee.getEmail());
        consigneeDetail.setConsigneeMobile(consignee.getMobileNo());
        consigneeDetail.setConsigneePhone(consignee.getPhoneNo());
        consigneeDetail.setUser(user);
        State state = stateRepo.findById(consignee.getStateCode()).get();
        consigneeDetail.setState(state);
        return  consigneeDetail;
    }

    private Invoiceitemdetail createInvoiceItemEntity(InvoiceItem invoiceItem, Invoicedetail invoiceEntity, boolean isConsigneeInSameState) {
	    Invoiceitemdetail invoiceitemdetail = new Invoiceitemdetail();
	    Product product = invoiceItem.getProduct();
	    invoiceitemdetail.setProductdetail(createProductEntity(product));
	    invoiceitemdetail.setIID_ProductQuantity(invoiceItem.getQuantity());
	    invoiceitemdetail.setIID_ItemDiscount(invoiceItem.getDiscount());
	    invoiceitemdetail.setIID_ItemUnit(invoiceItem.getUnit());
	    invoiceitemdetail.setIID_ItemTotalAmount(invoiceItem.getTotal());
	    invoiceitemdetail.setIID_ItemPrice(invoiceItem.getRate());
	    invoiceitemdetail.setIidTaxableamount(invoiceItem.getTaxableValue());

	    Collection<TaxItem> additionalTaxes = getAllTaxesForInvoiceItem(invoiceItem, isConsigneeInSameState);
	    Set<Invoiceitemtaxdetail> taxEntities = createInvoiceTaxItemEntities(additionalTaxes, invoiceitemdetail);
	    invoiceitemdetail.setInvoiceitemtaxdetails(taxEntities);
	    invoiceitemdetail.setInvoicedetail(invoiceEntity);
        return  invoiceitemdetail;
    }
    
    private Collection<TaxItem> getAllTaxesForInvoiceItem(InvoiceItem invoiceItem, boolean isConsigneeInSameState) {
    	Collection<TaxItem> invoiceTaxes = invoiceItem.getAdditionalTaxes();

    	if(invoiceTaxes == null) {
    		invoiceTaxes = new ArrayList<>();
		}

    	if(isConsigneeInSameState) {
            if((invoiceItem.getCgstRate() != null && invoiceItem.getCgstAmount() != null) &&
                    (!invoiceItem.getCgstRate().equals(BigDecimal.ZERO) && !invoiceItem.getCgstAmount().equals(BigDecimal.ZERO) )) {
                TaxItem cgstTaxItem = new TaxItem();
                cgstTaxItem.setAmount(invoiceItem.getCgstAmount());
                cgstTaxItem.setRate(invoiceItem.getCgstRate());
                cgstTaxItem.setType(TaxType.CGST);
                invoiceTaxes.add(cgstTaxItem);
            }

            if((invoiceItem.getSgstRate() != null && invoiceItem.getSgstAmount() != null) &&
                    (!invoiceItem.getSgstRate().equals(BigDecimal.ZERO) && !invoiceItem.getSgstAmount().equals(BigDecimal.ZERO) )) {
                TaxItem sgstTaxItem = new TaxItem();
                sgstTaxItem.setAmount(invoiceItem.getSgstAmount());
                sgstTaxItem.setRate(invoiceItem.getSgstRate());
                sgstTaxItem.setType(TaxType.SGST);
                invoiceTaxes.add(sgstTaxItem);
            }
        } else {
            if((invoiceItem.getIgstRate() != null && invoiceItem.getIgstAmount() != null) &&
                    (!invoiceItem.getIgstRate().equals(BigDecimal.ZERO) && !invoiceItem.getIgstAmount().equals(BigDecimal.ZERO) )) {
                TaxItem igstTaxItem = new TaxItem();
                igstTaxItem.setAmount(invoiceItem.getIgstAmount());
                igstTaxItem.setRate(invoiceItem.getIgstRate());
                igstTaxItem.setType(TaxType.IGST);
                invoiceTaxes.add(igstTaxItem);
            }
        }
    	
    	return invoiceTaxes;
    }

    private Set<Invoiceitemtaxdetail> createInvoiceTaxItemEntities(Collection<TaxItem> taxItems, Invoiceitemdetail invoiceItem) {
    	Set<Invoiceitemtaxdetail> taxEntities = new HashSet<>();
    	for (TaxItem taxItem : taxItems) {
			Invoiceitemtaxdetail taxEntity = new Invoiceitemtaxdetail();
			taxEntity.setITD_taxType(taxItem.getType().getTaxType());
			taxEntity.setITD_taxamount(taxItem.getAmount());
			taxEntity.setITD_taxrate(taxItem.getRate());
			taxEntity.setInvoiceitemdetail(invoiceItem);
			taxEntities.add(taxEntity);
		}
    	return taxEntities;
    }
    
    private Productdetail createProductEntity(Product product) {
		Productdetail productDetail = new Productdetail();
		productDetail.setProductId(product.getProductId());
		productDetail.setProductType(product.getType());
		productDetail.setAgencySecurityDeposit(product.getDepositAmount());
		productDetail.setAgencyStartDate(product.getAgencyStartDate());
		productDetail.setProductCompany(product.getCompany());
		productDetail.setProductTaxRate(product.getTaxRate());
		if(product.isGood()) {
			HSN hsn = new HSN();
			hsn.setHsnCode(product.getHsnCode());
			productDetail.setProductServiceOrGood("G");
			productDetail.setProductHSN(hsn);
		}
		else {
			SacMaster sac = new SacMaster();
			sac.setSacId(product.getHsnCode());
			productDetail.setProductSac(sac);
			productDetail.setProductServiceOrGood("S");
		}
		productDetail.setProductName(product.getName());
		return productDetail;
	}
    
    @Override
    public File createInvoicePDF(final String invoiceId, final String userId) throws DocumentException, MalformedURLException, IOException {
    	User user = userRepo.getUser(userId);
    	//String logoPath = user.getLogopath();

		String logoPath = "F:\\Development\\git-repo\\learn-git\\app\\src\\main\\resources\\static\\images\\invoice-logo.png";

    	Invoicedetail invoiceModel = invoiceRepo.findById(invoiceId).get();
    	
    	Document invoiceDoc = new Document();
    	File file = new File(invoiceGenerationPath+File.separator+invoiceModel.getID_InvoiceNumber().replaceAll("/", "")+".pdf");
    	file.createNewFile();
    	PdfWriter.getInstance(invoiceDoc, new FileOutputStream(file));
    	
    	invoiceDoc.open();
    	// Add Logo to the invoice
    	Image image = Image.getInstance(logoPath);
    	invoiceDoc.add(image);
    	
    	// Add header to the invoice
    	PdfPTable headerTable = new PdfPTable(3);
    	headerTable.setWidthPercentage(100);
    	PdfPCell taxInvoiceCell = new PdfPCell(new Phrase("TAX INVOICE", getBoldFont()));
    	taxInvoiceCell.setHorizontalAlignment(Element.ALIGN_LEFT);
    	headerTable.addCell(taxInvoiceCell);
    	
    	PdfPCell invoiceNumberCell = new PdfPCell(new Phrase(invoiceModel.getID_InvoiceNumber(), getBoldFont()));
    	invoiceNumberCell.setHorizontalAlignment(Element.ALIGN_CENTER);
    	headerTable.addCell(invoiceNumberCell);
    	
    	PdfPCell invoiceDateCell = new PdfPCell(new Phrase(Util.getDateInPrintableFormat(invoiceModel.getID_InvoiceDate()), getBoldFont()));
    	invoiceDateCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
    	headerTable.addCell(invoiceDateCell);
    	
    	invoiceDoc.add(headerTable);
    	
    	// Add Traders Details
    	PdfPTable traderTable = new PdfPTable(2);
    	traderTable.setWidthPercentage(100);
    	
    	PdfPCell sellerDetailsCell = new PdfPCell(new Phrase("Seller's Details", getBoldFont()));
    	sellerDetailsCell.setHorizontalAlignment(Element.ALIGN_CENTER);
    	sellerDetailsCell.setColspan(2);
    	
    	traderTable.addCell(sellerDetailsCell);
    	
    	PdfPCell traderNameCell = new PdfPCell();
    	
    	StringBuilder nameBuilder = new StringBuilder();
    	String traderNameStr = user.getBusinessname();
    	nameBuilder.append(traderNameStr);
    	nameBuilder.append("\n");
    	String gstin = "GSTIN: " + user.getGstin();
    	nameBuilder.append(gstin);
    	traderNameCell.setPhrase(new Phrase(nameBuilder.toString(), getRegularFont()));
    	
    	traderTable.addCell(traderNameCell);
    	
    	StringBuilder addressBuilder = new StringBuilder();
    	String address = user.getAddress();
    	addressBuilder.append(address);
    	String stateName = user.getState().getStatename();
    	String stateCode = user.getState().getStatecode();
    	
    	addressBuilder.append("\n");
    	addressBuilder.append(stateName);
    	addressBuilder.append(" ("+stateCode+")");
    	
    	PdfPCell traderAddressCell = new PdfPCell(new Phrase(addressBuilder.toString(), getRegularFont()));
    	traderTable.addCell(traderAddressCell);
    	
    	invoiceDoc.add(traderTable);
    	
    	
    	// Add Customer Detail
    	PdfPTable customerDetailHeaderTable = new PdfPTable(2);
    	customerDetailHeaderTable.setWidthPercentage(100);
    	String billingDetails = "Billing Details";
    	PdfPCell headerBillingCell = new PdfPCell(new Phrase(billingDetails, getBoldFont()));
    	headerBillingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
    	customerDetailHeaderTable.addCell(headerBillingCell);
    	
    	String shippingDetails = "Shipping Details";
    	PdfPCell headerShippingCell = new PdfPCell(new Phrase(shippingDetails, getBoldFont()));
    	headerShippingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
    	customerDetailHeaderTable.addCell(headerShippingCell);
    	
    	invoiceDoc.add(customerDetailHeaderTable);
    	
    	// Add Customer Details to the invoice
    	
    	PdfPTable customerDetailsTable = new PdfPTable(2);
    	customerDetailsTable.setWidthPercentage(100);
    	StringBuilder customerBuilder = new StringBuilder();
    	CustomerDetail customer = invoiceModel.getCustomerDetail();
    	customerBuilder.append(customer.getCdCustomerName());
    	customerBuilder.append("\n");
    	customerBuilder.append(customer.getCdCustomerAddress());
    	customerBuilder.append("\n");
    	customerBuilder.append("State: " + customer.getState().getStatename() + "("+customer.getState().getStatecode()+")");
    	customerBuilder.append("\n");
    	customerBuilder.append("GSTIN: " + customer.getCdCustomerGSTIN());
    	PdfPCell customerDetailCell = new PdfPCell(new Phrase(customerBuilder.toString(), getRegularFont()));
    	customerDetailsTable.addCell(customerDetailCell);
    	
    	StringBuilder consgineeBuilder = new StringBuilder();
    	ConsigneeDetail consignee = invoiceModel.getConsigneeDetail();
    	consgineeBuilder.append(consignee.getConsigneeName());
    	consgineeBuilder.append("\n");
    	consgineeBuilder.append(consignee.getConsigneeAddress());
    	consgineeBuilder.append("\n");
    	consgineeBuilder.append("State: " + consignee.getState().getStatename() + "("+consignee.getState().getStatecode()+")");
    	consgineeBuilder.append("\n");
    	consgineeBuilder.append("GSTIN: " + consignee.getConsigneeGSTIN());
    	PdfPCell consigneeDetailsCell = new PdfPCell(new Phrase(consgineeBuilder.toString(), getRegularFont()));
    	customerDetailsTable.addCell(consigneeDetailsCell);
    	
    	invoiceDoc.add(customerDetailsTable);
    	
    	// Create header for invoice items:
    	PdfPTable invoiceItemTables = new PdfPTable(15);
    	invoiceItemTables.setWidthPercentage(100);
    	
    	String snLabel = "S.No.";
    	PdfPCell serialNoCell = new PdfPCell(new Phrase(snLabel, getBoldFont()));
    	serialNoCell.setRowspan(2);
    	
    	String productLabel = "Product";
    	PdfPCell productCell = new PdfPCell(new Phrase(productLabel, getBoldFont()));
    	productCell.setRowspan(2);
    	
    	String accountCodeLabel = "HSN/SAC";
    	PdfPCell accountCodeCell = new PdfPCell(new Phrase(accountCodeLabel, getBoldFont()));
    	accountCodeCell.setRowspan(2);
    	
    	String rateLabel = "Rate";
    	PdfPCell rateCell = new PdfPCell(new Phrase(rateLabel, getBoldFont()));
    	rateCell.setRowspan(2);
    	
    	String unitsLabel = "Units";
    	PdfPCell unitsCell = new PdfPCell(new Phrase(unitsLabel, getBoldFont()));
    	unitsCell.setRowspan(2);
    	
    	String quanitityLabel = "Quant.";
    	PdfPCell quatityCell = new PdfPCell(new Phrase(quanitityLabel, getBoldFont()));
    	quatityCell.setRowspan(2);
    	
    	String totalLabel = "Total";
    	PdfPCell totalCell = new PdfPCell(new Phrase(totalLabel, getBoldFont()));
    	totalCell.setRowspan(2);
    	
    	String discLabel = "Disc.";
    	PdfPCell discCell = new PdfPCell(new Phrase(discLabel, getBoldFont()));
    	discCell.setRowspan(2);
    	
    	String taxableValueLabel = "Taxable Value";
    	PdfPCell taxableValueCell = new PdfPCell(new Phrase(taxableValueLabel, getBoldFont()));
    	taxableValueCell.setRowspan(2);
    	
    	String cgstParentLabel = "CGST";
    	PdfPCell cgstParentCell = new PdfPCell(new Phrase(cgstParentLabel, getBoldFont()));
    	cgstParentCell.setColspan(2);
    	
    	String sgstParentLabel = "SGST";
    	PdfPCell sgstParentCell = new PdfPCell(new Phrase(sgstParentLabel, getBoldFont()));
    	sgstParentCell.setColspan(2);
    	
    	String igstParentLabel = "IGST";
    	PdfPCell igstParentCell = new PdfPCell(new Phrase(igstParentLabel, getBoldFont()));
    	igstParentCell.setColspan(2);
    	
    	invoiceItemTables.addCell(serialNoCell);
    	invoiceItemTables.addCell(productCell);
    	invoiceItemTables.addCell(accountCodeCell);
    	invoiceItemTables.addCell(rateCell);
    	invoiceItemTables.addCell(unitsCell);
    	invoiceItemTables.addCell(quatityCell);
    	invoiceItemTables.addCell(totalCell);
    	invoiceItemTables.addCell(discCell);
    	invoiceItemTables.addCell(taxableValueCell);
    	invoiceItemTables.addCell(cgstParentCell);
    	invoiceItemTables.addCell(sgstParentCell);
    	invoiceItemTables.addCell(igstParentCell);
    	
    	String cgstRate = "Rate";
    	PdfPCell cgstRateCell = new PdfPCell(new Phrase(cgstRate, getBoldFont()));
    	
    	String cgstAmount = "Amount";
    	PdfPCell cgstAmountCell = new PdfPCell(new Phrase(cgstAmount, getBoldFont()));
    	
    	String sgstRate = "Rate";
    	PdfPCell sgstRateCell = new PdfPCell(new Phrase(sgstRate, getBoldFont()));
    	
    	String sgstAmount = "Amount";
    	PdfPCell sgstAmountCell = new PdfPCell(new Phrase(sgstAmount, getBoldFont()));
    	
    	String igstRate = "Rate";
    	PdfPCell igstRateCell = new PdfPCell(new Phrase(igstRate, getBoldFont()));
    	
    	String igstAmount = "Amount";
    	PdfPCell igstAmountCell = new PdfPCell(new Phrase(igstAmount, getBoldFont()));
    	
    	invoiceItemTables.addCell(cgstRateCell);
    	invoiceItemTables.addCell(cgstAmountCell);
    	invoiceItemTables.addCell(sgstRateCell);
    	invoiceItemTables.addCell(sgstAmountCell);
    	invoiceItemTables.addCell(igstRateCell);
    	invoiceItemTables.addCell(igstAmountCell);
    	
    	Collection<Invoiceitemdetail> invoiceItems = invoiceModel.getInvoiceitemdetails();
    	int serialNumber = 1;
    	for (Invoiceitemdetail invoiceitemdetail : invoiceItems) {
			String invoiceSN = ""+serialNumber++;
			PdfPCell iSerialNumberCell = new PdfPCell(new Phrase(invoiceSN, getRegularFont()));
			
			String iProduct = invoiceitemdetail.getProductdetail().getProductName();
			PdfPCell iProductCell = new PdfPCell(new Phrase(iProduct, getRegularFont()));
			
			String iAccntCode = "";
			if(invoiceitemdetail.getProductdetail().getProductServiceOrGood().equals("G"))
				iAccntCode = invoiceitemdetail.getProductdetail().getProductHSN().getHsnCode();
			else
				iAccntCode = invoiceitemdetail.getProductdetail().getProductSac().getSacId();
			PdfPCell iAccntCodeCell = new PdfPCell(new Phrase(iAccntCode, getRegularFont()));
			
			String iRate = invoiceitemdetail.getIID_ItemPrice().toPlainString();
			PdfPCell iRateCell = new PdfPCell(new Phrase(iRate, getRegularFont()));
			
			String iUnits = invoiceitemdetail.getIID_ItemUnit() == null ? "" : invoiceitemdetail.getIID_ItemUnit();
			PdfPCell iUnitsCell = new PdfPCell(new Phrase(iUnits, getRegularFont()));
			
			Integer iQuant = invoiceitemdetail.getIID_ProductQuantity();
			PdfPCell iQuantCell = new PdfPCell(new Phrase(iQuant.toString(), getRegularFont()));
			
			String iTotal = invoiceitemdetail.getIID_ItemTotalAmount().toPlainString();
			PdfPCell iTotalCell = new PdfPCell(new Phrase(iTotal, getRegularFont()));
			
			String iDiscount = invoiceitemdetail.getIID_ItemDiscount() == null ? "": invoiceitemdetail.getIID_ItemDiscount().toPlainString();
			PdfPCell iDiscountCell = new PdfPCell(new Phrase(iDiscount, getRegularFont()));
			
			String iTaxableValue = invoiceitemdetail.getIidTaxableamount().toPlainString();
			PdfPCell iTaxableValueCell= new PdfPCell(new Phrase(iTaxableValue, getRegularFont()));
			
			Set<Invoiceitemtaxdetail> taxesForInvoice = invoiceitemdetail.getInvoiceitemtaxdetails();
			
			TaxItem cgstDetails = getCGSTDetails(taxesForInvoice);
			TaxItem sgstDetails = getSGSTDetails(taxesForInvoice);
			TaxItem igstDetails = getIGSTDetails(taxesForInvoice);
			
			String iCgstRate = cgstDetails != null ? cgstDetails.getRate().toPlainString() : "-";
			PdfPCell iCgstRateCell= new PdfPCell(new Phrase(iCgstRate, getRegularFont()));
			
			String iCgstAmount = cgstDetails != null ? cgstDetails.getAmount().toPlainString() : "-";
			PdfPCell iCgstAmountCell = new PdfPCell(new Phrase(iCgstAmount, getRegularFont()));
			
			String iSgstRate = sgstDetails != null ? sgstDetails.getRate().toPlainString() : "-";
			PdfPCell iSgstRateCell= new PdfPCell(new Phrase(iSgstRate, getRegularFont()));
			
			String iSgstAmount = sgstDetails != null ? sgstDetails.getAmount().toPlainString() : "-";
			PdfPCell iSgstAmountCell = new PdfPCell(new Phrase(iSgstAmount, getRegularFont()));
			
			
			String iIgstRate = igstDetails != null ? igstDetails.getRate().toPlainString() : "-";
			PdfPCell iIgstRateCell= new PdfPCell(new Phrase(iIgstRate, getRegularFont()));
			
			String iIgstAmount = igstDetails != null ? igstDetails.getAmount().toPlainString() : "-";
			PdfPCell iIgstAmountCell = new PdfPCell(new Phrase(iIgstAmount, getRegularFont()));
			
			invoiceItemTables.addCell(iSerialNumberCell);
			invoiceItemTables.addCell(iProductCell);
			invoiceItemTables.addCell(iAccntCodeCell);
			invoiceItemTables.addCell(iRateCell);
			invoiceItemTables.addCell(iUnitsCell);
			invoiceItemTables.addCell(iQuantCell);
			invoiceItemTables.addCell(iTotalCell);
			invoiceItemTables.addCell(iDiscountCell);
			invoiceItemTables.addCell(iTaxableValueCell);
			invoiceItemTables.addCell(iCgstRateCell);
			invoiceItemTables.addCell(iCgstAmountCell);
			invoiceItemTables.addCell(iSgstRateCell);
			invoiceItemTables.addCell(iSgstAmountCell);
			invoiceItemTables.addCell(iIgstRateCell);
			invoiceItemTables.addCell(iIgstAmountCell);
		}
    	
    	// Loop and add the invoice Items to the PDF
    	
    	invoiceDoc.add(invoiceItemTables);
    	
    	PdfPTable totalSectionTable = new PdfPTable(4);
    	totalSectionTable.setWidthPercentage(100);
    	PdfPCell signatureCell = new PdfPCell();
    	signatureCell.setColspan(2);
    	signatureCell.setRowspan(5);
    	
    	totalSectionTable.addCell(signatureCell);
    	
    	String totalAmountLabel = "Total Amount:";
    	String totalAmountValue = invoiceModel.getID_InvoiceTotalAmount().toPlainString();
    	PdfPCell totalAmountLabelCell = new PdfPCell(new Phrase(totalAmountLabel, getBoldFont()));
    	PdfPCell totalAmountValueCell = new PdfPCell(new Phrase(totalAmountValue, getRegularFont()));
    	
    	totalSectionTable.addCell(totalAmountLabelCell);
    	totalSectionTable.addCell(totalAmountValueCell);
    	
    	String totalTaxLabel = "Total Tax:";
    	String totalTaxValue = invoiceModel.getID_TaxAmount().toPlainString();
    	
    	PdfPCell totalTaxLabelCell = new PdfPCell(new Phrase(totalTaxLabel, getBoldFont()));
    	PdfPCell totalTaxValueCell = new PdfPCell(new Phrase(totalTaxValue, getRegularFont()));
    	
    	totalSectionTable.addCell(totalTaxLabelCell);
    	totalSectionTable.addCell(totalTaxValueCell);
    	
    	
    	String grandTotalLabel = "Grand Total";
    	String grandTotalValue = invoiceModel.getID_GrandTotal().toPlainString();
    	
    	PdfPCell grandTotalLabelCell = new PdfPCell(new Phrase(grandTotalLabel, getBoldFont()));
    	PdfPCell grandTotalValueCell = new PdfPCell(new Phrase(grandTotalValue, getRegularFont()));
    	
    	totalSectionTable.addCell(grandTotalLabelCell);
    	totalSectionTable.addCell(grandTotalValueCell);
    	
    	String totalPaidLabel = "Amount Paid";
    	String totalPaidValue = invoiceModel.getID_InvoicePaidAmount().toPlainString();
    	
    	PdfPCell amountPaidLabelCell = new PdfPCell(new Phrase(totalPaidLabel, getBoldFont()));
    	PdfPCell amountPaidValueCell = new PdfPCell(new Phrase(totalPaidValue, getRegularFont()));
    	
    	totalSectionTable.addCell(amountPaidLabelCell);
    	totalSectionTable.addCell(amountPaidValueCell);
    	
    	String balAmountLabel = "Balance Amount";
    	String balAmountValue = invoiceModel.getID_InvoiceBalanceAmount().toPlainString();
    	
    	PdfPCell balAmountLabelCell = new PdfPCell(new Phrase(balAmountLabel, getBoldFont()));
    	PdfPCell balAmountValueCell = new PdfPCell(new Phrase(balAmountValue, getRegularFont()));
    	
    	totalSectionTable.addCell(balAmountLabelCell);
    	totalSectionTable.addCell(balAmountValueCell);
    	
    	
    	invoiceDoc.add(totalSectionTable);
    	
    	PdfPTable signatureNameTable = new PdfPTable(4);
    	signatureNameTable.setWidthPercentage(100);
    	StringBuilder signNameBuilder = new StringBuilder();
    	signNameBuilder.append("For ");
    	signNameBuilder.append(user.getBusinessname());
    	signNameBuilder.append(" (Seal and Sign in above box)");
    	
    	PdfPCell signatureNameCell = new PdfPCell(new Phrase(signNameBuilder.toString(), getRegularFont()));
    	signatureNameCell.setColspan(2);
    	signatureNameTable.addCell(signatureNameCell);
    	
    	
    	PdfPCell dueDateLabel = new PdfPCell(new Phrase("Due Date:", getBoldFont()));
    	signatureNameTable.addCell(dueDateLabel);
    	
    	PdfPCell dueDateValue = new PdfPCell(new Phrase(Util.getDateInPrintableFormat(invoiceModel.getID_InvoiceDueDate()), getBoldFont()));
    	signatureNameTable.addCell(dueDateValue);
    	
    	invoiceDoc.add(signatureNameTable);
    	
    	PdfPTable termsAndConditionTable = new PdfPTable(1);
    	termsAndConditionTable.setWidthPercentage(100);
    	String termsAndCondition = "Terms and Conditions:";
    	PdfPCell termsAndConditionCell = new PdfPCell(new Phrase(termsAndCondition, getRegularFont()));
    	termsAndConditionTable.addCell(termsAndConditionCell);
    	
    	invoiceDoc.add(termsAndConditionTable);
    	
    	invoiceDoc.close();
    	return file;
    }
    
    private TaxItem getCGSTDetails(Set<Invoiceitemtaxdetail> taxDetails) {
    	for (Invoiceitemtaxdetail invoiceitemtaxdetail : taxDetails) {
			if(invoiceitemtaxdetail.getITD_taxType().equals("Central GST")) {
				TaxItem taxItem = new TaxItem();
				taxItem.setRate(invoiceitemtaxdetail.getITD_taxrate());
				taxItem.setAmount(invoiceitemtaxdetail.getITD_taxamount());
				return taxItem;
			}
		}
    	return null;
    }

    private TaxItem getSGSTDetails(Set<Invoiceitemtaxdetail> taxDetails) {
    	for (Invoiceitemtaxdetail invoiceitemtaxdetail : taxDetails) {
			if(invoiceitemtaxdetail.getITD_taxType().equals("State GST")) {
				TaxItem taxItem = new TaxItem();
				taxItem.setRate(invoiceitemtaxdetail.getITD_taxrate());
				taxItem.setAmount(invoiceitemtaxdetail.getITD_taxamount());
				return taxItem;
			}
		}
    	return null;
    }
    
    private TaxItem getIGSTDetails(Set<Invoiceitemtaxdetail> taxDetails) {
    	for (Invoiceitemtaxdetail invoiceitemtaxdetail : taxDetails) {
			if(invoiceitemtaxdetail.getITD_taxType().equals("Integrated GST")) {
				TaxItem taxItem = new TaxItem();
				taxItem.setRate(invoiceitemtaxdetail.getITD_taxrate());
				taxItem.setAmount(invoiceitemtaxdetail.getITD_taxamount());
				return taxItem;
			}
		}
    	return null;
    }
    
    private Font getRegularFont() {
    	Font font = FontFactory.getFont(REGULAR_FONT, "Cp1252", true);
    	font.setSize(8);
    	return font;
    }
    
    private Font getBoldFont() {
    	Font font = FontFactory.getFont(BOLD_FONT, "Cp1252", true);
    	font.setSize(8);
    	return font;
    }
    
    @Override
    public Invoice getInvoice(String invoiceId, String userid) {
    	Invoicedetail invoiceEntity = invoiceRepo.findById(invoiceId).get();
    	return ConversionUtility.convertInvoiceEntityToModel(invoiceEntity);
    }

	@Override
	public void receivePayment(String invoiceId, String amount) {
		Invoicedetail invoiceEntity = invoiceRepo.findById(invoiceId).get();
		BigDecimal paidAmount = invoiceEntity.getID_InvoicePaidAmount();
		BigDecimal amountPaid = new BigDecimal(amount);
		
		paidAmount = paidAmount.add(amountPaid);
		BigDecimal balanceAmount = invoiceEntity.getID_InvoiceBalanceAmount();
		balanceAmount = balanceAmount.subtract(amountPaid);
		invoiceEntity.setID_InvoiceBalanceAmount(balanceAmount);
		invoiceEntity.setID_InvoicePaidAmount(paidAmount);
		invoiceRepo.save(invoiceEntity);
	}
    
    
}
