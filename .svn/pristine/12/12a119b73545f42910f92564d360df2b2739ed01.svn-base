package com.atguigu.crm.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Update;

import com.atuigu.crm.entity.CustomerDrain;


public interface CustomerDrainMapper {
	
	@Update("{call check_drain}")
	public void add();

	public abstract int getTotalNumCondition(Map<String, Object> mybatisParams);
	public abstract List<CustomerDrain> getContentCondition(Map<String, Object> mybatisParams);
	public abstract CustomerDrain getElementById(Long id);
	public abstract void updateDelay(CustomerDrain customerDrain);
	
}

	