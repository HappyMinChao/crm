package com.atguigu.crm.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.mappers.RoleMapper;
import com.atuigu.crm.entity.Role;
import com.atuigu.crm.orm.Page;
import com.atuigu.crm.orm.PropertyFilter;

@Service
@Transactional
public class RoleService {
	
	@Autowired
	private RoleMapper roleMapper;
	
	public List<Role> getAllRoles() {
		return roleMapper.getAllRoles();
	}
	
	@Transactional(readOnly=true)
	public Page<Role> getPage(String pageNoStr, Map<String, Object> params) {
		int pageNo=1;
		try {
			pageNo=Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {
		}
		
		Map<String, Object> myBatisParam = PropertyFilter.parseRequestParams2MybatisParams(params);
		
		Page<Role> page = new Page<>();
		page.setPageNo(pageNo);
		
		//1. 查询 totalElements
		int totalElements = roleMapper.getTotalElements(myBatisParam);
		page.setTotalElements(totalElements);
		
		//2. 确定查询 content 需要的 firstIndex 和 endIndex
		int firstIndex = (page.getPageNo() - 1) * page.getPageSize() + 1;
		int endIndex = firstIndex + page.getPageSize();
		
		//3. 把 firstIndex 和 endIndex 加入到 mybatisParams 中
		myBatisParam.put("firstIndex", firstIndex);
		myBatisParam.put("endIndex", endIndex);
		
		//4. 调方法查询 content
		List<Role> content = roleMapper.getContent(myBatisParam);
		page.setContent(content);
		return page;
	}

	public void create(Role role) {
		roleMapper.create(role);
	}

	public void delete(Integer roleId) {
		roleMapper.delete(roleId);
	}

	public void update(Role role) {
		roleMapper.update(role);
	}

	public Role getBeanById(Integer roleId) {
		return roleMapper.getBeanById(roleId);
	}

}
