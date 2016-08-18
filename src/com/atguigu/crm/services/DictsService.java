package com.atguigu.crm.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.mappers.DictsMapper;
import com.atuigu.crm.entity.Dict;
import com.atuigu.crm.orm.Page;
import com.atuigu.crm.orm.PropertyFilter;

@Service
@Transactional
public class DictsService {
	
	@Autowired
	private DictsMapper dictsMapper;
	
	/**
	 *@Description:根据类型，获取本类型下的所有的元素
	 *@Author:du_minchao
	 *@Version:
	 *@CreateDate:29 Mar 2016
	 *@return:List<String>
	 */
	public List<String> getAllElementsByType(String type){
		return dictsMapper.getAllElementsByType(type);
	}
	
	public Page<Dict> getPage(int pageNo, Map<String, Object> params) {
		Page<Dict> page = new Page<Dict>();
		page.setPageNo(pageNo);
		//设置分页每页显示的页数
		page.setPageSize(5);
		//1. 把请求参数转为 mybatis 可以使用的请求参数
		Map<String , Object> mybatisParams = PropertyFilter.parseRequestParams2MybatisParams(params);
		
		//2. 查询 totalElements
		int totalElements = dictsMapper.getTotalElements(mybatisParams);
		page.setTotalElements(totalElements);
		
		//3. 确定查询 content 需要的 firstIndex 和 endIndex
		int firstIndex = (page.getPageNo() - 1) * page.getPageSize() + 1;
		int endIndex = firstIndex + page.getPageSize();
		
		//4. 把 firstIndex 和 endIndex 加入到 mybatisParams 中
		mybatisParams.put("firstIndex", firstIndex);
		mybatisParams.put("endIndex", endIndex);
		
		//5. 调方法查询 content
		List<Dict> content = dictsMapper.getContent(mybatisParams);
		page.setContent(content);
		return page;
	}
}
