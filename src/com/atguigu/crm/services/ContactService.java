package com.atguigu.crm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.mappers.ContactMapper;
import com.atuigu.crm.entity.Contact;
import com.atuigu.crm.orm.Page;

@Transactional
@Service
public class ContactService {

	@Autowired
	private ContactMapper contactMapper;

	public Page<Contact> getContactPage(int pageNo, int pageSize,long userId) {
		//获取总元素数
		int totalElements = contactMapper.getTotalElementsNum(userId);
		
		Page<Contact> page = new Page<Contact>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setTotalElements(totalElements);
		
		int firstIndex = (page.getPageNo() - 1) * page.getPageSize();
		int lastIndex = firstIndex + page.getPageSize();
		
		//获取page中的内容
		List<Contact> content = contactMapper.getContent(firstIndex, lastIndex,userId);
		page.setContent(content);
		
		return page;
	}

	/**
	 *@Description: 保存联系人
	 *@Author:du_minchao
	 *@Version:
	 *@CreateDate:31 Mar 2016
	 *@return:void
	 */
	public void saveContact(Contact contact) {
		contactMapper.add(contact);
	}
	
	/**
	 *@Description:根据id获取联系人
	 *@Author:du_minchao
	 *@Version:
	 *@CreateDate:31 Mar 2016
	 *@return:Contact
	 */
	public Contact getContactById(Integer id) {
		Contact contact =contactMapper.getContactById(id);
		return contact;
	}
	
	/**
	 *@Description:更新联系人
	 *@Author:du_minchao
	 *@Version:
	 *@CreateDate:31 Mar 2016
	 *@return:void
	 */
	public void updateContact(Contact contact) {
		contactMapper.update(contact);
	}
	
	/**
	 *@Description:根据联系人id和customerid删除联系人
	 *@Author:du_minchao
	 *@Version:
	 *@CreateDate:31 Mar 2016
	 *@return:void
	 */
	public void deleteContact(String contactid, String customerid) {
		contactMapper.deleteContact(contactid,customerid);
	}
	

}
