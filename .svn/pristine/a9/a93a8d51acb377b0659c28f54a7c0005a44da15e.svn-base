package com.atguigu.crm.mappers;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.atuigu.crm.entity.CustomerServiceEntity;

/**
 * @Description: 服务管理模块
 * @Author:du_minchao
 * @CreateDate:14:12:52
 */

public interface CustomerServiceMapper {
	/**
	 *@Description: 保存客户服务管理
	 *@Author:du_minchao
	 *@Version:
	 *@CreateDate:1 Apr 2016
	 *@return:void
	 */
	public abstract void saveCustomerService(CustomerServiceEntity customerServiceEntity);
	
	
	/**
	 *@Description:  实现服务的分配工作
	 *@Author:du_minchao
	 *@Version:
	 *@CreateDate:1 Apr 2016
	 *@return:void
	 */
	public abstract void updateAllotTo(@Param("id") Integer id,
									   @Param("allotTo_id")Long allotTo_id,
									   @Param("date" )Date date);

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
	public abstract int getTotalElementsWithCondition(
			Map<String, Object> mybatisParams);

	public abstract List<CustomerServiceEntity> getContentWithCondition(
			Map<String, Object> mybatisParams);

	/**
	 *@Description: 根据服务id删除  用户服务
	 *@Author:du_minchao
	 *@Version:
	 *@CreateDate:1 Apr 2016
	 *@return:void
	 */
	public abstract void delete(@Param("id")String id);

	
	/**
	 *@Description: 根据id获取CustomerServiceEntity
	 *@Author:du_minchao
	 *@Version:
	 *@CreateDate:1 Apr 2016
	 *@return:CustomerServiceEntity
	 */
	
	public abstract CustomerServiceEntity getServiceById(@Param("id")Integer id);

	
	public abstract void updateDeal(CustomerServiceEntity customerServiceEntity);

	
	public abstract void feedbackUpdate(
			CustomerServiceEntity customerServiceEntity);

	/**
	 *@Description:  不带查询条件的查询
	public abstract int getTotalElementCount(
			@Param("userId")Long userId
			);
	public abstract List<CustomerServiceEntity> getPageContent(
						@Param("userId")Long userId,
						@Param("firstIndex")int firstIndex,
						@Param("lastIndex")int lastIndex);
	*/
	
}
