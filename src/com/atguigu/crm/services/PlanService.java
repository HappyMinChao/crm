package com.atguigu.crm.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.crm.mappers.PlanMapper;
import com.atuigu.crm.entity.SalesChance;
import com.atuigu.crm.entity.SalesPlan;
import com.atuigu.crm.orm.Page;
import com.atuigu.crm.orm.PropertyFilter;

@Service
public class PlanService {
	
	@Autowired
	private PlanMapper planMapper;
	
	public Page<SalesChance> getPage(String pageNoStr, Map<String, Object> params) {
		int pageNo=1;
		try {
			pageNo=Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {
		}
		
		Map<String, Object> myBatisParam = PropertyFilter.parseRequestParams2MybatisParams(params);
		
		Page<SalesChance> page = new Page<>();
		page.setPageNo(pageNo);
		
		//1. 查询 totalElements
		int totalElements = (int) planMapper.getTotalElements(myBatisParam);
		page.setTotalElements(totalElements);
		
		//2. 确定查询 content 需要的 firstIndex 和 endIndex
		int firstIndex = (page.getPageNo() - 1) * page.getPageSize() + 1;
		int endIndex = firstIndex + page.getPageSize();
		
		//3. 把 firstIndex 和 endIndex 加入到 mybatisParams 中
		myBatisParam.put("firstIndex", firstIndex);
		myBatisParam.put("endIndex", endIndex);
		
		//4. 调方法查询 content
		List<SalesChance> content = planMapper.getContent(myBatisParam);
		page.setContent(content);
		return page;
	}

	public SalesChance getBeanById(Integer id) {
		SalesChance chance=planMapper.getBeanById(id);
		return chance;
	}

	public long savePlan(SalesPlan salesPlan) {
		planMapper.savePlan(salesPlan);
		return salesPlan.getId();
	}

	public void deletePlan(Integer id) {
		planMapper.deletePlan(id);
	}

	public void updatePlan(SalesPlan salesPlan) {
		planMapper.updatePlan(salesPlan);
	}

	public List<SalesPlan> getBeanListByChanceId(Integer chanceId) {
		List<SalesPlan> plans=planMapper.getBeanListByChanceId(chanceId);
		return plans;
	}

	public void exePlan(Integer id, String result) {
		planMapper.exePlan(id,result);
	}
	
	

}
