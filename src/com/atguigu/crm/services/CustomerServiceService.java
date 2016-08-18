package com.atguigu.crm.services;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.mappers.CustomerServiceMapper;
import com.atguigu.crm.utils.PageUtils;
import com.atuigu.crm.entity.CustomerServiceEntity;
import com.atuigu.crm.entity.SalesChance;
import com.atuigu.crm.entity.User;
import com.atuigu.crm.orm.Page;
import com.atuigu.crm.orm.PropertyFilter;

/**
 * @Description: 客户服务类的service方法
 * @Author:du_minchao
 * @CreateDate:19:56:00
 */

@Service
@Transactional
public class CustomerServiceService {
	
	@Autowired
	private CustomerServiceMapper customerServiceMapper;
	
	/**
	 *@Description: 保存客户服务管理
	 *@Author:du_minchao
	 *@Version:
	 *@CreateDate:1 Apr 2016
	 *@return:void
	 */
	public void saveCustomerService(CustomerServiceEntity customerServiceEntity){
		customerServiceMapper.saveCustomerService(customerServiceEntity);
	}

	/**
	 *@Description:  实现服务的分配工作
	 *@Author:du_minchao
	 *@Version:
	 *@CreateDate:1 Apr 2016
	 *@return:void
	 */
	public void updateAllotTo(Integer id,Integer allotTo_id) {
		customerServiceMapper.updateAllotTo(id, (long)allotTo_id,new Date());
	}
	
	/**
	 *@Description:   
	 *	url: /deal/list    参数包含： userId
	 *	url: /allot/list   参数不包含: userId
	 *	条件查询： mybatisParams 中包含查询的条件
	 *	非条件查询： mybatisParams 中不包含查询的条件
	 *@Author:du_minchao
	 *@Version:
	 *@CreateDate:1 Apr 2016
	 *@return:int
	 */
	public Page<CustomerServiceEntity> listPageWithCondition(
			Map<String, Object> parameters, int pageNo, int pageSize,Long userId,String urlType) {

		Page<CustomerServiceEntity> page = new Page<CustomerServiceEntity>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		
		/*	serviceTitle=%asdF%, 
		 *  customer.name=ASDF,
		 *  serviceType=%ASDF%,
		 *  createDateEnd=2016-03-31, 
		 *  createDateStart=2016-03-30
		 */
		//1. 把请求参数转为 mybatis 可以使用的请求参数
		Map<String , Object> mybatisParams = PropertyFilter.parseRequestParams2MybatisParams(parameters);
		
		/*
		 *  serviceType  serviceTitle  customerName  createDate  createDate
		 *  需要把customer.name进行转换
		 */
		Object value = mybatisParams.get("customer.name");
		mybatisParams.put("customerName", value);
		mybatisParams.put("userId", userId);
		mybatisParams.put("urlType", urlType);
		
		
		//2. 查询 totalElements
		int totalElements = (int) customerServiceMapper.getTotalElementsWithCondition(mybatisParams);
		page.setTotalElements(totalElements);
		
		//3. 确定查询 content 需要的 firstIndex 和 endIndex
		int firstIndex = (page.getPageNo() - 1) * page.getPageSize() + 1;
		int lastIndex = firstIndex + page.getPageSize();
		
		//4. 把 firstIndex 和 endIndex 加入到 mybatisParams 中
		mybatisParams.put("firstIndex", firstIndex);
		mybatisParams.put("lastIndex", lastIndex);
		
		//5. 调方法查询 content
		List<CustomerServiceEntity> content = customerServiceMapper.
								getContentWithCondition(mybatisParams);
		page.setContent(content);
		
		
		return page;
	}

	/**
	 *@Description: 根据服务id删除  用户服务
	 *@Author:du_minchao
	 *@Version:
	 *@CreateDate:1 Apr 2016
	 *@return:void
	 */
	public void delete(String id) {
		customerServiceMapper.delete(id);
	}

	/**
	 *@Description: 根据id获取CustomerServiceEntity
	 *@Author:du_minchao
	 *@Version:
	 *@CreateDate:1 Apr 2016
	 *@return:CustomerServiceEntity
	 */
	public CustomerServiceEntity getServiceById(Integer id) {
		return customerServiceMapper.getServiceById(id);
	}

	/**
	 *@Description:更新处理请求
	 *@Author:du_minchao
	 *@Version:
	 *@CreateDate:1 Apr 2016
	 *@return:void
	 */
	public void updateDeal(CustomerServiceEntity customerServiceEntity) {
		// 更新 处理时间、 处理人(即为分配给的人)  服务处理
		customerServiceMapper.updateDeal(customerServiceEntity);
	}

	public void feedbackUpdate(CustomerServiceEntity customerServiceEntity) {
		//需要修改： satisfy  dealResult
		customerServiceMapper.feedbackUpdate(customerServiceEntity);
	}
	

	/*public Page<CustomerServiceEntity> listWithPage(int pageNo,
			int pageSize,Long userId) {
		
		//获取totalElements content
		int totalElements = customerServiceMapper.getTotalElementCount(userId);
		Page<CustomerServiceEntity>  page = new Page<CustomerServiceEntity>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setTotalElements(totalElements);
		
		int firstIndex = (page.getPageNo()-1) * page.getPageSize();
		int lastIndex = firstIndex + page.getPageSize();
		//查询出page.content
		List<CustomerServiceEntity> pageContent = customerServiceMapper.getPageContent(userId,firstIndex, lastIndex);
		page.setContent(pageContent);
		return page;
	}*/

}
