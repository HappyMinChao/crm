package com.atguigu.crm.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.mappers.OrderMapper;
import com.atuigu.crm.entity.Order;
import com.atuigu.crm.entity.SalesChance;
import com.atuigu.crm.orm.Page;
import com.atuigu.crm.orm.PropertyFilter;
@Service
@Transactional
public class OrderService {
	@Autowired
	private OrderMapper orderMapper;

	public Page<Order> getCustomerOrder(Long id, int pageNo,
			Map<String, Object> params) {
		Page<Order> page = new Page<>();
		page.setPageNo(pageNo);
		
		//1. 把请求参数转为 mybatis 可以使用的请求参数
		Map<String , Object> mybatisParams = PropertyFilter.parseRequestParams2MybatisParams(params);
		mybatisParams.put("customerId", id);
		//2. 查询 totalElements
		int totalElements = orderMapper.getTotalElements(mybatisParams);
		page.setTotalElements(totalElements);
		
		//3. 确定查询 content 需要的 firstIndex 和 endIndex
		int firstIndex = (page.getPageNo() - 1) * page.getPageSize() + 1;
		int endIndex = firstIndex + page.getPageSize();
		
		//4. 把 firstIndex 和 endIndex 加入到 mybatisParams 中
		mybatisParams.put("firstIndex", firstIndex);
		mybatisParams.put("endIndex", endIndex);
		
		//5. 调方法查询 content
		List<Order> content = orderMapper.getContent(mybatisParams);
		page.setContent(content);
		
		return page;
	}

	public Order getOrderById(Long id) {
		// TODO Auto-generated method stub
		return orderMapper.getOrderById(id);
	}


}
