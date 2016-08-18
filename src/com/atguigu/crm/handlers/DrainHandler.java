package com.atguigu.crm.handlers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.services.CustomerDrainService;
import com.atguigu.crm.utils.PageUtils;
import com.atuigu.crm.entity.CustomerDrain;
import com.atuigu.crm.orm.Page;

@Controller
@RequestMapping("/drain")
public class DrainHandler {
	
	@Autowired
	private CustomerDrainService customerDrainService;
	
	@RequestMapping(value="/confirm",method=RequestMethod.PUT)
	public String updateReason(CustomerDrain customerDrain){
		
		//reason/流失 status
		customerDrain.setStatus("流失");
		customerDrainService.updateDelay(customerDrain);
		
		return "redirect:/drain/list";
	}
	
	@RequestMapping(value="/confirm")
	public String confirm(@RequestParam(value="id")Long id,
			Map<String, Object> map){
		
		CustomerDrain drain = customerDrainService.getElementById(id);
		map.put("drain", drain);
		
		return "drain/confirm";
	}
	
	@RequestMapping(value="/delay",method=RequestMethod.PUT)
	public String update(CustomerDrain customerDrain){
		//先查询出CustomerDrain
		CustomerDrain drain = customerDrainService.getElementById(customerDrain.getId());
		String delay = drain.getDelay();
		if(delay != null && !"".equals(delay)){
			delay += "`"+customerDrain.getDelay();
		}
		customerDrain.setDelay(delay);
		//对delay属性进行更新
		customerDrainService.updateDelay(customerDrain);
		return "redirect:/drain/list";
	}
	
	@RequestMapping(value="/delay",method=RequestMethod.GET)
	public String delay(Map<String,Object> map,
			@RequestParam(value="id",required=false)Long id
			){
		CustomerDrain customerDrain = customerDrainService.getElementById(id);
		map.put("drain", customerDrain);
		return "drain/delay";
	}
	
	@RequestMapping(value="/list")
	public String list(@RequestParam(value="pageNoStr",required=false)String pageNoStr,
			HttpServletRequest request,
			Map<String, Object> map){
		//准备分页信息
		int pageNo = 1;
		int pageSize = 5;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {}
		//获取请求参数
		Map<String, Object> parameters = WebUtils.getParametersStartingWith(request, "search_");
		
		//调用服务层信息,获取XXX月没有业务往来的客户
		Page<CustomerDrain> page = customerDrainService.getPage(parameters, pageNo, pageSize);
		
		//把参数信息拼装成字符串，用于查询条件保持       
		String queryString = PageUtils.encodeParamsToQueryString(parameters);
		map.put("queryString", queryString);
		//把page放入map中送显
		map.put("page", page);
		
		return "drain/list";
	}

}
