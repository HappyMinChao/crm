package com.atguigu.crm.services;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.mappers.ContactMapper;
import com.atguigu.crm.mappers.CustomerMapper;
import com.atguigu.crm.mappers.SalesChanceMapper;
import com.atuigu.crm.entity.Contact;
import com.atuigu.crm.entity.Customer;
import com.atuigu.crm.entity.SalesChance;
import com.atuigu.crm.orm.Page;
import com.atuigu.crm.orm.PropertyFilter;

@Service
public class SalesChanceService {

	@Autowired
	private SalesChanceMapper salesChanceMapper;
	@Autowired
	private CustomerMapper customerMapper;
	@Autowired
	private ContactMapper contactMapper;
	
	@Transactional
	public void update(SalesChance chance){
		salesChanceMapper.update(chance);
	}
	
	@Transactional(readOnly=true)
	public SalesChance get(Integer id){
		return salesChanceMapper.get(id);
	}
	
	@Transactional
	public void delete(Integer id){
		salesChanceMapper.delete(id);
	}
	
	@Transactional
	public void save(SalesChance salesChance){
		salesChanceMapper.save(salesChance);
	}
	
	@Transactional(readOnly=true)
	public Page<SalesChance> getPage(int pageNo){
		Page<SalesChance> page = new Page<>();
		page.setPageNo(pageNo);
		
		//1. 查询 totalElements
		int totalElements = (int) salesChanceMapper.getTotalElements();
		page.setTotalElements(totalElements);
		
		//2. 查询 content
		int firstIndex = (page.getPageNo() - 1) * page.getPageSize() + 1;
		int endIndex = firstIndex + page.getPageSize();
		
		List<SalesChance> content = salesChanceMapper.getContent(firstIndex, endIndex);
		page.setContent(content);
		
		return page;
	}

	public Page<SalesChance> getPage(int pageNo, Map<String, Object> params) {
		Page<SalesChance> page = new Page<>();
		page.setPageNo(pageNo);
		
		//1. 把请求参数转为 mybatis 可以使用的请求参数
		Map<String , Object> mybatisParams = PropertyFilter.parseRequestParams2MybatisParams(params);
		
		//2. 查询 totalElements
		int totalElements = (int) salesChanceMapper.getTotalElements2(mybatisParams);
		page.setTotalElements(totalElements);
		
		//3. 确定查询 content 需要的 firstIndex 和 endIndex
		int firstIndex = (page.getPageNo() - 1) * page.getPageSize() + 1;
		int endIndex = firstIndex + page.getPageSize();
		
		//4. 把 firstIndex 和 endIndex 加入到 mybatisParams 中
		mybatisParams.put("firstIndex", firstIndex);
		mybatisParams.put("endIndex", endIndex);
		
		//5. 调方法查询 content
		List<SalesChance> content = salesChanceMapper.getContent2(mybatisParams);
		page.setContent(content);
		
		return page;
	}

	/**
	 *@Description: 根据id查询销售机会， 用于分配销售机会
	 *@Author:du_minchao
	 *@Version:
	 *@CreateDate:29 Mar 2016
	 *@return:SalesChance
	 */
	public SalesChance getElementById(Integer id) {
		return salesChanceMapper.getElementById(id);
		
	}

	public void updateDispatchSalesChance(SalesChance chance) {
		salesChanceMapper.updateDispatchSalesChance(chance);
		
	}
	
	/**
	 * 将销售机会的状态设置为完成 by 宁华勇
	 * @param id
	 */
	public void finish(Integer id) {
		//1.通过数据库获取销售机会的实体，将其状态设置为完成
		SalesChance chance = salesChanceMapper.getElementById(id);
		//chance.setStatus(3);
		salesChanceMapper.developSuccess(chance);
		//System.out.println(chance);
		//2.向用户表中插入客户的姓名，no，state
		Customer customer=new Customer();
		customer.setName(chance.getCustName());
		customer.setNo(UUID.randomUUID().toString());
		customer.setState("正常");
		customerMapper.add(customer);
		//3.向联系表中插入以下信息 name,tel,customerId
		Contact contact=new Contact();
		contact.setName(chance.getContact());
		contact.setTel(chance.getContactTel());
		contact.setCustomer(customer);
		contactMapper.add(contact);
	}
	/**
	 * 客户开发失败--- by ning
	 * @param id 
	 */
	public void stop(Integer id) {
		// TODO Auto-generated method stub
		//1.通过数据库获取销售机会的实体，将其状态设置为失败
		SalesChance chance = salesChanceMapper.getElementById(id);
		salesChanceMapper.developFail(chance);
	}

}
