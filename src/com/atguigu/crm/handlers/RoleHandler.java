package com.atguigu.crm.handlers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.services.RoleService;
import com.atguigu.crm.utils.PageUtils;
import com.atuigu.crm.entity.Role;
import com.atuigu.crm.orm.Page;

@RequestMapping("/role")
@Controller
public class RoleHandler {
	
	@Autowired
	private RoleService roleService;
	
	//---------------------start-----------------------------------------------------role 的crud
	/**
	 * @describe 显示分页的数据
	 * @author shanshan
	 * @time 2016年4月1日
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(String pageNoStr, 
					Map<String, Object> map,
					HttpServletRequest request) {
		// 获取查询条件的请求参数对应的 Map
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, "search_");

		Page<Role> page = roleService.getPage(pageNoStr, params);
		map.put("page", page);
		return "role/list";
	}
	
	/**
	 * @describe 增加一个角色
	 * @author shanshan
	 * @time 2016年4月1日
	 */
	@RequestMapping(value="/",method=RequestMethod.POST)
	public String create(Role role){
		roleService.create(role);
		return "redirect:list";
	}
	
	/**
	 * @describe 删除一个角色
	 * @author shanshan
	 * @time 2016年4月1日
	 */
	@RequestMapping(value="/{roleId}",method=RequestMethod.DELETE)
	public String delete(@PathVariable("roleId")Integer roleId){
		roleService.delete(roleId);
		return "redirect:list";
	}
	
	/**
	 * @describe 更新一个角色
	 * @author shanshan
	 * @time 2016年4月1日
	 */
	@RequestMapping(value="/",method=RequestMethod.PUT)
	public String update(Role role){
		roleService.update(role);
		return "redirect:list";
	}
	
	/**
	 * @describe 跳转到更新或者新建页面
	 * @author shanshan
	 * @time 2016年4月1日
	 */
	@RequestMapping(value="/input",method=RequestMethod.GET)
	public String toInput(Integer roleId,Map<String,Object> map){
		Role role=null;
		if(roleId==null) {
			role=new Role();
		}else{
			role=roleService.getBeanById(roleId);
		}
		return "role/input";
	}
	//---------------------end-----------------------------------------------------role 的crud
	@RequestMapping(value="/assign",method=RequestMethod.GET)
	public String assign(){
		return "role/assign";
	}
	
	
}
