package com.prompt.marginplus.services;

import java.util.Collection;

import com.prompt.marginplus.exceptions.ServiceExcpetion;
import com.prompt.marginplus.models.*;
import org.hibernate.service.spi.ServiceException;

public interface IMainService {

	public String[] getProductTypes() throws ServiceExcpetion;

	public long saveProduct(Product product, String userid) throws ServiceExcpetion;

	public Collection<Product> getProducts(String userid) throws ServiceException;

	public Product getProduct(String productId, String userid) throws ServiceExcpetion;

	public Collection<Customer> getCustomers(String userid) throws ServiceException;

	public Collection<Consignee> getConsignees(String userid) throws ServiceException;

	public Collection<SacHeadingModel> getHeadingsForAllAccountingCodes() throws ServiceException;

	public Collection<SacGroupModel> getGroupsForHeading(String headingId) throws ServiceException;

	public Collection<SacModel> getSacs(String groupId) throws ServiceException;

	public Collection<HSNSectionModel> getHSNSections() throws ServiceException;

	public Collection<HSNChapterModel> getHSNChapters(String sectionId) throws ServiceException;

	public Collection<HSNModel> getHSNs(String chapterId) throws ServiceException;

}
