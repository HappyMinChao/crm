package com.atguigu.crm.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.atuigu.crm.entity.CustomerActivity;

public interface ActivtyMapper {
	
	@Select("select * from"
			+ "(select rownum rn,id,activity_date as \"date\","
			+ "description,place,title,customer_id as \"customer.id\" "
			+ "from customer_activities "
			+ "where customer_id=#{id})"
			+ "where rn<#{endIndex} and rn>=#{firstIndex}")
	List<CustomerActivity> getActivityListByCustomerId(@Param("id")long id, @Param("firstIndex")int firstIndex, @Param("endIndex")int endIndex);
	
	@Select("select count(*) "
			+ "from customer_activities "
			+ "where customer_id=#{id}")
	int getTotalCount(@Param("id")long id);
	
	@Select("select id,activity_date as \"date\",description,place,title,"
			+ "customer_id as \"customer.id\" "
			+ "from customer_activities "
			+ "where id=#{id}")
	CustomerActivity getBeanById(@Param("id")long id);
	
	@Insert("insert into customer_activities "
			+ "(id,activity_date,description,place,title,customer_id) "
			+ "values(crm_seq.nextval,#{date},#{description},#{place},#{title},#{customer.id})")
	void create(CustomerActivity customerActivity);
	
	@Update("update customer_activities set activity_date=#{date},description=#{description},place=#{place},title=#{title}"
			+ "where id=#{id}")
	void update(CustomerActivity customerActivity);
	
	@Delete("delete from customer_activities where id=#{id}")
	void delete(@Param("id")long id);

}
