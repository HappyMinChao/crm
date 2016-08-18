package com.atguigu.crm.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.atuigu.crm.entity.Contact;

/**
 *  create by 宁华勇
 * @author Administrator
 *
 */
public interface ContactMapper {
	
	
	public void delete(Integer id);
	
	public List<Contact> getAll();
	
	/**
	 *@Description:更新联系人
	 *@Author:du_minchao
	 *@Version:
	 *@CreateDate:31 Mar 2016
	 *@return:void
	 */
	public void update(Contact contact);
	
	/**
	 *@Description:保存联系人信息
	 *@Author:du_minchao
	 *@Version:
	 *@CreateDate:30 Mar 2016
	 *@return:void
	 */
	public void add(Contact contact);
	
	/**
	 *@Description:根据id获取给用户下的联系人数量
	 *@Author:du_minchao
	 *@Version:
	 *@CreateDate:30 Mar 2016
	 *@return:int
	 */
	public int getTotalElementsNum(@Param("userId") long userId);
	
	/**
	 *@Description:根据id获取联系人列表page
	 *@Author:du_minchao
	 *@Version:
	 *@CreateDate:30 Mar 2016
	 *@return:List<Contact>
	 */
	public List<Contact> getContent(@Param("firstIndex") int firstIndex,
									@Param("lastIndex") int lastIndex,
									@Param("userId") long userId
									);
	/**
	 *@Description:根据id获取联系人
	 *@Author:du_minchao
	 *@Version:
	 *@CreateDate:31 Mar 2016
	 *@return:Contact
	 */
	public Contact getContactById(@Param("id")Integer id);
	
	/**
	 *@Description:根据联系人id和customerid删除联系人
	 *@Author:du_minchao
	 *@Version:
	 *@CreateDate:31 Mar 2016
	 *@return:void
	 */
	public void deleteContact(@Param("contactid")String contactid,@Param("customerid") String customerid);
	
}
