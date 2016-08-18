package com.atguigu.crm.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atuigu.crm.entity.SalesChance;
import com.atuigu.crm.entity.SalesPlan;

public interface PlanMapper {

	int getTotalElements(Map<String, Object> myBatisParam);

	List<SalesChance> getContent(Map<String, Object> params);

	SalesChance getBeanById(@Param("id")Integer id);

	void savePlan(SalesPlan salesPlan);

	void deletePlan(@Param("id")Integer id);

	void updatePlan(SalesPlan salesPlan);

	List<SalesPlan> getBeanListByChanceId(@Param("id")Integer id);

	void exePlan(@Param("id")Integer id,@Param("result")String result);
	
}
