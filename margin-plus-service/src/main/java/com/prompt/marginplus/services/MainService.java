package com.prompt.marginplus.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import com.prompt.marginplus.entities.*;
import com.prompt.marginplus.models.*;
import com.prompt.marginplus.repositories.*;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.collect.Iterables;
import com.prompt.marginplus.types.ProductType;
import com.prompt.marginplus.utility.Util;

@Service("mainService")
public class MainService implements IMainService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MainService.class);

	@Resource(name="userRepo")
	private UserRepository userRepo;

	@Resource(name="productRepository")
	private ProductRepository productRepository;
	
	@Resource(name="invoiceNumberRepo")
	private InvoiceNumberRepo invoiceNumberRepo;
	
	@Resource(name="customerRepository")
	private CustomerRepository customerRepository;
	
	@Resource(name="consigneeRepository")
	private ConsigneeRepository consigneeRepo;
	
	@Resource(name="sacHeadingRepo")
	private SacHeadingRepository sacHeadingRepo;
	
	@Resource(name="sacGroupRepo")
	private SacGroupRepository sacGroupRepo;
	
	@Resource(name="sacRepo")
	private SacRepository sacRepo;
	
	@Resource(name="hsnSectionRepo")
	private HSNSectionRepository hsSectionRepo;
	
	@Resource(name="hsnChapterRepo")
	private HSNChapterRepository hsnChapterRepo;
	
	@Resource(name="hsnRepo")
	private HSNRepository hsnRepo;
	
	@Override
	public String[] getProductTypes() {
		LOGGER.info("Getting Product types");
		List<String> listOfTypes = new ArrayList<String>();
		for(ProductType type : ProductType.values()) {
			listOfTypes.add(type.getType());
		}
		return listOfTypes.toArray(new String[listOfTypes.size()]);
	}
	
	@Override
	public long saveProduct(Product product, String userId) {
		LOGGER.info("Saving Product");

		User user = userRepo.getUser(userId);

        Productdetail productDetail = new Productdetail();
		if(product.getProductId() != 0) {
            // The request is for editing since product id is already present
            productDetail = productRepository.findById(product.getProductId()).get();
        }


		productDetail.setProductType(product.getType());
		productDetail.setAgencySecurityDeposit(product.getDepositAmount());
		productDetail.setAgencyStartDate(product.getAgencyStartDate());
		productDetail.setProductCompany(product.getCompany());
		productDetail.setProductTaxRate(product.getTaxRate());
		productDetail.setUser(user);
		if(product.getType().equalsIgnoreCase("Good")) {
			HSN hsn = new HSN();
			hsn.setHsnCode(product.getHsnCode());
			productDetail.setProductServiceOrGood("G");
			productDetail.setProductHSN(hsn);
		}
		else if(product.getType().equalsIgnoreCase("Service")){
			SacMaster sac = new SacMaster();
			sac.setSacId(product.getHsnCode());
			productDetail.setProductSac(sac);
			productDetail.setProductServiceOrGood("S");
		}
		productDetail.setProductName(product.getName());
		Productdetail savedProduct = productRepository.save(productDetail);
		LOGGER.info("Saved Product with ID " + savedProduct.getProductId());
		return savedProduct.getProductId();
	}
	
	@Override
	public Collection<Product> getProducts(String userid) {
		LOGGER.info("Get all the products");
		Collection<Product> products = new ArrayList<Product>();

		User user = userRepo.getUser(userid);

		Iterable<Productdetail> productsFromDB = productRepository.findAllByUser(user);
		LOGGER.info("Found " + Iterables.size(productsFromDB) + " from Database.");
		String goodsOrService = "";
		for (Productdetail productdetail : productsFromDB) {
			Product product = new Product();
			product.setProductId(productdetail.getProductId());
			product.setType(productdetail.getProductType().toUpperCase());
			product.setAgencyStartDate(productdetail.getAgencyStartDate());
			product.setCompany(productdetail.getProductCompany());
			product.setDepositAmount(productdetail.getAgencySecurityDeposit());
			
			goodsOrService = productdetail.getProductServiceOrGood();
			
			if(goodsOrService.equalsIgnoreCase("G")) {
				product.setHsnCode(productdetail.getProductHSN().getHsnCode());
				product.setAccountingCodeDesc(productdetail.getProductHSN().getHsnDesc());
			}
			else if(goodsOrService.equalsIgnoreCase("S")) {
				product.setHsnCode(productdetail.getProductSac().getSacId());
				product.setAccountingCodeDesc(productdetail.getProductSac().getSacDesc());
			}
			product.setName(productdetail.getProductName());
			product.setTaxRate(productdetail.getProductTaxRate());
			product.setGood(goodsOrService.equalsIgnoreCase("G")?true:false);
			products.add(product);
		}
		return products;
	}

	@Override
	public Product getProduct(final String productId, final String userId) {
		LOGGER.info("Get product with Id: " + productId);
		Collection<Product> products = new ArrayList<Product>();
		User user = userRepo.findById(userId).get();
		Productdetail productdetail = productRepository.findByProductIdAndUser(Long.parseLong(productId), user);
		LOGGER.info("Found " + productdetail + " from Database.");
		String goodsOrService = "";
			Product product = new Product();
			product.setProductId(productdetail.getProductId());
			product.setType(productdetail.getProductType().toUpperCase());
			product.setAgencyStartDate(productdetail.getAgencyStartDate());
			product.setCompany(productdetail.getProductCompany());
			product.setDepositAmount(productdetail.getAgencySecurityDeposit());

			goodsOrService = productdetail.getProductServiceOrGood();

			if(goodsOrService.equalsIgnoreCase("G")) {
				product.setHsnCode(productdetail.getProductHSN().getHsnCode());
				product.setAccountingCodeDesc(productdetail.getProductHSN().getHsnDesc());
			}
			else if(goodsOrService.equalsIgnoreCase("S")) {
				product.setHsnCode(productdetail.getProductSac().getSacId());
				product.setAccountingCodeDesc(productdetail.getProductSac().getSacDesc());
			}
			product.setName(productdetail.getProductName());
			product.setTaxRate(productdetail.getProductTaxRate());
			product.setGood(goodsOrService.equalsIgnoreCase("G")?true:false);
		return product;
	}
	
	@Override
	public String generateInvoiceNumber() {
		LOGGER.info("Generating invoice number");
		String invoiceNumber = "";
		Integer sequenceNo = invoiceNumberRepo.getInvoiceSequenceNumber(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
		if(sequenceNo != null) {
			invoiceNumber = Util.getInvoiceNumber(sequenceNo);
		}
		else {
			invoiceNumber = Util.getInvoiceNumber(0);
		}
		LOGGER.info("Generated invoice number : " + invoiceNumber);
		return invoiceNumber;
	}

	@Override
	public Collection<Customer> getCustomers(final String userid) throws ServiceException {
		LOGGER.info("Getting list of customers.");

		User user = userRepo.getUser(userid);

		Iterable<CustomerDetail> cutomersFromDb = customerRepository.findAllByUser(user);
		List<Customer> customers = new ArrayList<Customer>();
		LOGGER.info("Received " + Iterables.size(cutomersFromDb) + " cutomers from DB");
		for (CustomerDetail customerDetail : cutomersFromDb) {
			Customer customer = new Customer();
			customer.setCustomerId(customerDetail.getCdCustomerId());
			customer.setName(customerDetail.getCdCustomerName());
			customer.setAddress(customerDetail.getCdCustomerAddress());
			customer.setState(customerDetail.getState().getStatename());
			customer.setStateCode(customerDetail.getState().getStatecode());
			customer.setGstin(customerDetail.getCdCustomerGSTIN());
			customer.setEmail(customerDetail.getCdCustomerEmail());
			customer.setMobileNo(customerDetail.getCdCustomerMobile());
			customer.setPhoneNo(customerDetail.getCdCustomerPhone());
			customers.add(customer);
		}
		return customers;
	}
	
	@Override
	public Collection<Consignee> getConsignees(final String userid) throws ServiceException {
		LOGGER.info("Getting list of consignees.");

		User user = userRepo.getUser(userid);

		Iterable<ConsigneeDetail> cutomersFromDb = consigneeRepo.findAllByUser(user);
		List<Consignee> consignees = new ArrayList<Consignee>();
		LOGGER.info("Received " + Iterables.size(cutomersFromDb) + " cutomers from DB");
		for (ConsigneeDetail consigneeDetail : cutomersFromDb) {
			Consignee consignee = new Consignee();
			consignee.setConsigneeId(consigneeDetail.getConsigneeId());
			consignee.setName(consigneeDetail.getConsigneeName());
			consignee.setAddress(consigneeDetail.getConsigneeAddress());
			consignee.setState(consigneeDetail.getState().getStatename());
			consignee.setStateCode(consigneeDetail.getState().getStatecode());
			consignee.setGstin(consigneeDetail.getConsigneeGSTIN());
			consignee.setEmail(consigneeDetail.getConsigneeEmail());
			consignee.setMobileNo(consigneeDetail.getConsigneeMobile());
			consignee.setPhoneNo(consigneeDetail.getConsigneePhone());
			consignees.add(consignee);
		}
		return consignees;
	}
	
	@Override
	public Collection<SacHeadingModel> getHeadingsForAllAccountingCodes() {
		LOGGER.info("Getting All Headings for SACs");
		Collection<SacHeadingModel> sacHeadings = new ArrayList<SacHeadingModel>();
		Iterable<SacHeadingMaster> allSacHeadings = sacHeadingRepo.findAll();
		for (SacHeadingMaster sacHeadingMaster : allSacHeadings) {
			SacHeadingModel model = new SacHeadingModel();
			model.setHeadingCode(sacHeadingMaster.getHeadingId());
			model.setHeadingDesc(sacHeadingMaster.getHeadingDesc());
			sacHeadings.add(model);
		}
		LOGGER.info("Got " + sacHeadings.size() + " Headings from database");
		return sacHeadings;
	}
	
	@Override
	public Collection<SacGroupModel> getGroupsForHeading(String headingId) {
		LOGGER.info("Getting SAC groups for Heading : " + headingId);
		Collection<SacGroupHeadinMap> groupHeadingMap = sacGroupRepo.getGroupsByHeading(headingId);
		Collection<SacGroupModel> groups = new ArrayList<SacGroupModel>();
		for(SacGroupHeadinMap sacGroupHeadinMap : groupHeadingMap) {
			SacGroupModel groupModel = new SacGroupModel();
			groupModel.setGroupCode(sacGroupHeadinMap.getSacGroupMaster().getGroupId());
			groupModel.setGroupDesc(sacGroupHeadinMap.getSacGroupMaster().getGroupDesc());
			groups.add(groupModel);
		}
		LOGGER.info("Received "+ groups.size() +" Groups from Database.");
		return groups;
	}
	
	@Override
	public Collection<SacModel> getSacs(String groupId) {
		LOGGER.info("Getting SAC for group id  : " + groupId);
		Collection<SacAccountingcodeGroupMap> accountingCodes = sacRepo.getSacByGroupId(groupId);
		Collection<SacModel> sacModels = new ArrayList<SacModel>();
		
		for(SacAccountingcodeGroupMap sacFromDB : accountingCodes) {
			SacModel accountingCodeModel = new SacModel();
			accountingCodeModel.setSacCode(sacFromDB.getSacMaster().getSacId());
			accountingCodeModel.setSacDesc(sacFromDB.getSacMaster().getSacDesc());
			sacModels.add(accountingCodeModel);
		}
		LOGGER.info("Received " + sacModels.size() + " from database");
		return sacModels;
	}
	
	@Override
	public Collection<HSNSectionModel> getHSNSections() {
		LOGGER.info("Getting list of all HSN Sections");
		Iterable<SectionMaster> sections = hsSectionRepo.findAll();
		Collection<HSNSectionModel> hsnAllSections = new ArrayList<HSNSectionModel>();
		for(SectionMaster secMaster : sections) {
			HSNSectionModel sectionModel = new HSNSectionModel();
			sectionModel.setSectionCode(secMaster.getSectionId());
			sectionModel.setSectionDesc(secMaster.getSectionDesc());
			hsnAllSections.add(sectionModel);
		}
		LOGGER.info("Retreived " + hsnAllSections.size() + " sections from the database.");
		return hsnAllSections;
	}
	
	@Override
	public Collection<HSNChapterModel> getHSNChapters(String sectionId) {
		LOGGER.info("Getting HSN Chapters for section id : " + sectionId);
		Collection<ChapterSectionMap> hsnChapters = hsnChapterRepo.getChaptersBySectionId(sectionId);
		Collection<HSNChapterModel> chapters = new ArrayList<HSNChapterModel>();
		for(ChapterSectionMap chapter : hsnChapters) {
			HSNChapterModel chapterModel = new HSNChapterModel();
			chapterModel.setChapterId(chapter.getChapterId());
			chapterModel.setChapterDesc(chapter.getHsnchapterMaster().getHsnchapterDesc());
			chapters.add(chapterModel);
		}
		LOGGER.info("Retreived " + chapters.size() + " chapters from database.");
		return chapters;
	}
	
	@Override
	public Collection<HSNModel> getHSNs(String chapterId) {
		LOGGER.info("Getting HSN Chapters for chapter id : " + chapterId);
		Collection<HsnChapterMap> hsns = hsnRepo.getHSNByChapter(chapterId);
		Collection<HSNModel> hsnModels = new ArrayList<HSNModel>();
		for(HsnChapterMap hsnEntity : hsns) {
			HSNModel hsnModel = new HSNModel();
			hsnModel.setHsnCode(hsnEntity.getHsncode());
			hsnModel.setHsnDesc(hsnEntity.getHsnmaster().getHsndesc());
			hsnModels.add(hsnModel);
		}
		LOGGER.info("Retreived "+ hsnModels.size() + " from database.");
		return hsnModels;
	}

}
