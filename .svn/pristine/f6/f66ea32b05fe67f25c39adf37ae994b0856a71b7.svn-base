package com.atguigu.crm.handlers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.crm.services.ActivityService;
import com.atguigu.crm.services.CustomerService;
import com.atuigu.crm.entity.Customer;
import com.atuigu.crm.entity.CustomerActivity;
import com.atuigu.crm.orm.Page;

/**
 * @author shanshan
 * @describe 客户交往记录的crud
 * @time 2016/3/31上午8:42:22
 */
@RequestMapping(value="/activity")
@Controller
public class ActivityHandler {
	
	@Autowired
	private ActivityService activityService;
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value="/list/{customerId}",method=RequestMethod.GET)
	public String list(@PathVariable("customerId")Long customerId,String pageNoStr,Map<String,Object> map){
		Customer customer = customerService.getBeanById(customerId);
		@SuppressWarnings("unchecked")
		Page<CustomerActivity> page=activityService.getPage(pageNoStr,customerId);
		map.put("customer",customer);
		map.put("page",page);
		return "activity/list";
	}
	
	@RequestMapping(value="/",method=RequestMethod.POST)
	public String create(CustomerActivity customerActivity){
		activityService.create(customerActivity);
		Long customerId = customerActivity.getCustomer().getId();
		return "redirect:list/"+customerId;
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public String update(@PathVariable("id")Long id,CustomerActivity customerActivity){
		customerActivity.setId(id);
		activityService.update(customerActivity);
		Long customerId = customerActivity.getCustomer().getId();
		return "redirect:list/"+customerId;
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id,Long customerId){
		activityService.delete(id);
		return "redirect:list/"+customerId;
	}
	
	@RequestMapping(value="/input",method=RequestMethod.GET)
	public String input(Long customerId,Long id,Map<String,Object> map){
		CustomerActivity customerActivty = activityService.getBeanById(id);
		
		if(customerActivty.getCustomer()==null){
			Customer customer=new Customer();
			customer.setId(customerId);
			customerActivty.setCustomer(customer);
		} 
		
		map.put("activity", customerActivty);
		return "activity/input";
	}
	
}
