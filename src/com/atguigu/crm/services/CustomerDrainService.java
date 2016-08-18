
package com.atguigu.crm.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.mappers.CustomerDrainMapper;
import com.atuigu.crm.entity.CustomerDrain;
import com.atuigu.crm.orm.Page;
import com.atuigu.crm.orm.PropertyFilter;


@Service
@Transactional
public class CustomerDrainService {
	
	@Autowired
	private CustomerDrainMapper customerDrainMapper;
	/*
	 * 查询字段：
	 * 	cd cd.id,
		cd.delay, 
		cd.drain_date,
		cd.last_order_date,
		cd.reason,
		cd.status,
		cd.customer_id,
	 */
	
	/**
	 *@Description:
	 *@Author:du_minchao
	 *@Version:
	 *@CreateDate:30 Mar 2016
	 *@return:Page<Customer>
	 */
	
	public Page<CustomerDrain> getPage(Map<String, Object> params, int pageNo,
			int pageSize) {
		Page<CustomerDrain> page = new Page<>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		
		//1. 把请求参数转为 mybatis 可以使用的请求参数
		Map<String , Object> mybatisParams = PropertyFilter.parseRequestParams2MybatisParams(params);
		//把项目经理条件转换一下放到mybatisParams里面用于查询条件
		Object managerName = mybatisParams.get("customer.manager.name");
		Object customerName = mybatisParams.get("customer.name");
		mybatisParams.put("managerName", managerName);
		mybatisParams.put("customerName", customerName);
		
		//2. 查询 totalElements
		int totalElements = (int) customerDrainMapper.getTotalNumCondition(mybatisParams);
		page.setTotalElements(totalElements);
		
		//3. 确定查询 content 需要的 firstIndex 和 endIndex
		int firstIndex = (page.getPageNo() - 1) * page.getPageSize() + 1;
		int lastIndex = firstIndex + page.getPageSize();
		
		//4. 把 firstIndex 和 endIndex 加入到 mybatisParams 中
		mybatisParams.put("firstIndex", firstIndex);
		mybatisParams.put("lastIndex", lastIndex);
		
		//5. 调方法查询 content
		List<CustomerDrain> content = customerDrainMapper.getContentCondition(mybatisParams);
		page.setContent(content);
		
		return page;
	}

	public CustomerDrain getElementById(Long id) {
		
		return customerDrainMapper.getElementById(id);
	}

	public void updateDelay(CustomerDrain customerDrain) {
		customerDrainMapper.updateDelay(customerDrain);
	}

	public void add(){
		customerDrainMapper.add();
	}
}
