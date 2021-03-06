package com.prompt.marginplus.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.prompt.marginplus.models.*;
import com.prompt.marginplus.services.*;
import com.prompt.marginplus.types.ExpenseType;
import com.prompt.marginplus.exceptions.ServiceExcpetion;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.itextpdf.text.DocumentException;

@RestController
@RequestMapping("/services")
public class MainController {

    private static Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    @Resource(name = "mainService")
    private IMainService mainService;

    @Resource(name = "userService")
    private IUserService userService;

    @Resource(name = "invoiceService")
    private IInvoiceService invoiceService;

    @Resource(name = "invoiceReportService")
    private IInvoiceReportService invoiceReportService;

    @Resource(name = "expenseService")
    private IExpenseService expenseService;

    @RequiresPermissions("create-product")
    @PostMapping(value = "/createProduct", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String createProduct(@RequestBody Product product, @RequestParam String userid) throws JsonProcessingException {
        LOGGER.info("Request received for Product Creation with data " + product);
        Long id = -1L;
        try {
            id = mainService.saveProduct(product, userid);
            LOGGER.info(product.toString());
        } catch (ServiceExcpetion e) {
            LOGGER.info("ServiceExcpetion during service call", e);
        }
        return convertToJson(id);
    }

    @ResponseBody
    @RequiresPermissions("read-product")
    @GetMapping(value = "/getProductTypes")
    public String[] getProductTypes() {
        LOGGER.info("Getting product types from service");
        String[] productTypes = null;
        try {
            productTypes = mainService.getProductTypes();
        } catch (ServiceExcpetion e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return productTypes;
    }

    @RequiresPermissions("read-product")
    @PostMapping(value = "/printInvoice", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void printInvoice(@RequestBody String invoiceIdJson, HttpServletResponse response)
            throws MalformedURLException, DocumentException, IOException {

        String invoiceId = "";
        ObjectNode objectNode = new ObjectMapper().readValue(invoiceIdJson, ObjectNode.class);
        if (objectNode.has("invoiceId")) {
            invoiceId = objectNode.get("invoiceId").asText();
        }
        LOGGER.info("Creating PDF for invoice" + invoiceId);
        UserModel user = userService.getUserInfo();
        File invoiceFile = invoiceService.createInvoicePDF(invoiceId, user.getUserid());

        FileInputStream fis = new FileInputStream(invoiceFile);
        response.setHeader("Content-Disposition", "attachment; filename=" + invoiceFile.getName());
        response.setHeader("Content-type", "application/octet-stream");

        try {
            int c;
            while ((c = fis.read()) != -1) {
                response.getWriter().write(c);
            }
        } finally {
            if (fis != null)
                fis.close();
            response.getWriter().close();
        }
    }

    @ResponseBody
    @RequiresPermissions("read-product")
    @GetMapping(value = "/getProducts")
    public Collection<Product> getProducts(@RequestParam String userid) {
        LOGGER.info("Getting product types from service");
        Collection<Product> products = null;
        try {
            products = mainService.getProducts(userid);
        } catch (ServiceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return products;
    }

    @ResponseBody
    @RequiresPermissions("rw-invoice")
    @GetMapping(value = "/getProduct")
    public Product getProduct(@RequestParam String productId, @RequestParam String userid) throws ServiceExcpetion {
        LOGGER.info("Request for retrieving Product:" + productId);
        return mainService.getProduct(productId, userid);
    }

    @ResponseBody
    @RequiresPermissions("read-customer")
    @GetMapping(value = "/getCustomers")
    public Collection<Customer> getCustomers(@RequestParam String userid) {
        LOGGER.info("Getting Customers from service");
        Collection<Customer> customers = null;
        try {
            customers = mainService.getCustomers(userid);
        } catch (ServiceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return customers;
    }

    @ResponseBody
    @RequiresPermissions("rw-invoice")
    @PostMapping(value = "/createInvoice")
    public Map<String, String> createInvoice(@Valid @RequestBody Invoice invoiceJson, @RequestParam String userId) {
        LOGGER.info("Create Invoice received for " + invoiceJson);
        String invoiceNumber = "";
        //Invoice invoice = new ObjectMapper().readValue(invoiceJson, Invoice.class);
        invoiceNumber = invoiceService.createInvoice(invoiceJson, userId);
        LOGGER.info("Returning created invoice's number: "+invoiceNumber);
        return Collections.singletonMap("response", "your string value");
    }

    @ResponseBody
    @RequiresPermissions("read-consignee")
    @GetMapping(value = "/getConsignees")
    public Collection<Consignee> getConsignees(@RequestParam String userid) {
        LOGGER.info("Getting product types from service");
        Collection<Consignee> consignees = null;
        try {
            consignees = mainService.getConsignees(userid);
        } catch (ServiceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return consignees;
    }

    @ResponseBody
    @RequiresPermissions("read-accounting-codes")
    @GetMapping(value = "/getSacHeadings")
    public Collection<SacHeadingModel> getSacHeadings() {
        LOGGER.info("Request received for getting Headings for Service accounting codes");
        Collection<SacHeadingModel> sacHeadings = null;
        try {
            sacHeadings = mainService.getHeadingsForAllAccountingCodes();
        } catch (ServiceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return sacHeadings;
    }

    @ResponseBody
    @RequiresPermissions("read-accounting-codes")
    @GetMapping(value = "/getGroupsForHeading")
    public Collection<SacGroupModel> getSacGroup(@RequestParam String headingId) {
        LOGGER.info("Request received for getting Groups for Heading : " + headingId);
        Collection<SacGroupModel> sacHeadings = null;
        try {
            sacHeadings = mainService.getGroupsForHeading(headingId);
        } catch (ServiceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return sacHeadings;
    }

    @ResponseBody
    @RequiresPermissions("read-accounting-codes")
    @GetMapping(value = "/getSacsFromGroupId")
    public Collection<SacModel> getSacs(@RequestParam String groupId) {
        LOGGER.info("Request received for getting Sacs for Group Id : " + groupId);
        Collection<SacModel> sacs = null;
        try {
            sacs = mainService.getSacs(groupId);
        } catch (ServiceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return sacs;
    }

    @ResponseBody
    @RequiresPermissions("read-accounting-codes")
    @GetMapping(value = "/getHSNSections")
    public Collection<HSNSectionModel> getAllHSNSections() {
        LOGGER.info("Request received for getting HSN-Sections");
        Collection<HSNSectionModel> hsnSections = null;
        try {
            hsnSections = mainService.getHSNSections();
        } catch (ServiceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return hsnSections;
    }

    @ResponseBody
    @RequiresPermissions("read-accounting-codes")
    @GetMapping(value = "/getHsnChapter")
    public Collection<HSNChapterModel> getHsnChapter(@RequestParam String sectionId) {
        LOGGER.info("Request received for getting HSN-Chapters for section id " + sectionId);
        Collection<HSNChapterModel> hsnChapters = null;
        try {
            hsnChapters = mainService.getHSNChapters(sectionId);
        } catch (ServiceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return hsnChapters;
    }

    @ResponseBody
    @RequiresPermissions("read-accounting-codes")
    @GetMapping(value = "/getHsn")
    public Collection<HSNModel> getHsns(@RequestParam String chapterId) {
        LOGGER.info("Request received for getting HSN-Chapters for section id " + chapterId);
        Collection<HSNModel> hsns = null;
        try {
            hsns = mainService.getHSNs(chapterId);
        } catch (ServiceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return hsns;
    }

    @ResponseBody
    @RequiresPermissions("rw-invoice")
    @PostMapping(value = "/getInvoiceReport")
    public Collection<InvoiceReportModel> getInvoiceReports(@RequestBody InvoiceSearchRequest invoiceReportRequest)
            throws ParseException, JsonParseException, JsonMappingException, IOException {
        LOGGER.info("Request for generating invoice report received.");
        Collection<InvoiceReportModel> reportData = null;
        reportData = invoiceReportService.getInvoices(invoiceReportRequest.getFromDate(), invoiceReportRequest.getToDate(), invoiceReportRequest.getUserid());
        return reportData;
    }

    @ResponseBody
    @RequiresPermissions("rw-invoice")
    @GetMapping(value = "/getInvoice")
    public Invoice getInvoice(@RequestParam String invoiceId, @RequestParam String userid) {
        LOGGER.info("Request for retriving invoice:" + invoiceId);
        return invoiceService.getInvoice(invoiceId, userid);
    }

    @RequiresPermissions("rw-invoice")
    @PostMapping(value = "/receivePayment")
    public void receivePayment(@RequestBody String requestBody) throws JsonParseException, JsonMappingException, IOException {
        String invoiceId = "";
        String amountReceived = "";
        ObjectNode objectNode = new ObjectMapper().readValue(requestBody, ObjectNode.class);
        if (objectNode.has("invoiceId")) {
            invoiceId = objectNode.get("invoiceId").asText();
        }
        if (objectNode.has("amountReceived")) {
            amountReceived = objectNode.get("amountReceived").asText();
        }
        LOGGER.info("Request for payment:" + invoiceId + ", and payment amount: " + amountReceived);
        invoiceService.receivePayment(invoiceId, amountReceived);
    }

    @RequiresPermissions("rw-expense")
    @GetMapping(value = "/getExpenseTypes")
    public @ResponseBody
    ExpenseType[] getExpenseTypes() {
        return expenseService.getExpenseTypes();
    }

    @RequiresPermissions("rw-expense")
    @PostMapping(value = "/saveExpense")
    public @ResponseBody
    Long createExpense(@RequestBody Expense expense) {
        return expenseService.saveExpense(expense);
    }

    private String convertToJson(Object object) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(object);
        return json;
    }

}
