package com.atguigu.crm.handlers;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.services.CustomerService;
import com.atguigu.crm.services.CustomerServiceService;
import com.atguigu.crm.services.DictsService;
import com.atguigu.crm.services.UserService;
import com.atguigu.crm.utils.DataUtils;
import com.atguigu.crm.utils.PageUtils;
import com.atuigu.crm.entity.Customer;
import com.atuigu.crm.entity.CustomerServiceEntity;
import com.atuigu.crm.entity.User;
import com.atuigu.crm.orm.Page;

@RequestMapping(value="/service")
@Controller
public class CustomerServiceHandler {
	
	@Autowired
	private DictsService dictsService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private UserService userService;
	@Autowired
	private CustomerServiceService customerServiceService;
	
	@RequestMapping(value="/archive")
	public String archive(@RequestParam(value="id")Integer id,
			Map<String, Object> map){
		CustomerServiceEntity service = customerServiceService.getServiceById(id);
		map.put("service", service);
		return "/service/archive/archive";
	}

	@RequestMapping(value="/feedback",method=RequestMethod.PUT)
	public String feedbackUpdate(CustomerServiceEntity customerServiceEntity){
		//需要修改： satisfy  dealResult
		customerServiceService.feedbackUpdate(customerServiceEntity);
		return "redirect:cc/feedback/list";
	}
	//======================反馈操作========================
	
	@RequestMapping(value="/feedback",method=RequestMethod.GET)
	public String feedback(@RequestParam(value="id")Integer id,
				Map<String, Object> map
			){
		CustomerServiceEntity service = customerServiceService.getServiceById(id);
		map.put("service", service);
		return "/service/feedback/feedback";
	}
	//======================处理操作========================
	
	/**
	 *@Description: 提交处理
	 *@Author:du_minchao
	 *@Version:
	 *@CreateDate:1 Apr 2016
	 *@return:String
	 */
	@RequestMapping(value="/deal",method=RequestMethod.PUT)
	public String updateDeal(CustomerServiceEntity customerServiceEntity){
		customerServiceEntity.setDealDate(DataUtils.FormateDate(new Date()));
		customerServiceService.updateDeal(customerServiceEntity);

		return "redirect:/service/deal/list";
	}
	
	/**
	 *@Description: 挑战到处理页面
	 *@Author:du_minchao
	 *@Version:
	 *@CreateDate:1 Apr 2016
	 *@return:String
	 */
	@RequestMapping(value="/deal",method=RequestMethod.GET)
	public String deal(@RequestParam(value="id")Integer id,
			Map<String, Object> map
			){
		CustomerServiceEntity customerServiceEntity = customerServiceService.getServiceById(id);
		map.put("service", customerServiceEntity);
		return "/service/deal/deal";
	}
	//=======================分配操作=======================
	
	@RequestMapping(value="/delete")
	public String delete(@RequestParam(value="id")String id){
		//
		customerServiceService.delete(id);
		return "redirect:/service/allot/list?pageNoStr=1";
	}
	
	/**
	 *@Description:  带查询条件的分页显示
	 *@Author:du_minchao
	 *@Version:
	 *@CreateDate:1 Apr 2016
	 *@return:String
	 */
	@RequestMapping(value="/{urlType}/list")
	public String conditionList(HttpServletRequest request,
		HttpSession session,
		@PathVariable("urlType")String urlType,
		@RequestParam(value="pageNoStr",required=false)String pageNoStr,
		Map<String, Object> map
			){
		
		Map<String, Object> parameters = WebUtils.getParametersStartingWith(request, "search_");
		
		int pageNo = 1;
		int pageSize = 5;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {}
		Page<CustomerServiceEntity> page = null;
		
		Long userId = null;
		
		//如果是deal操作
		if("deal".equals(urlType) ||"feedback".equals(urlType)){
			//获取当前用户
			User user = (User) session.getAttribute("user");
			userId = user.getId();
		}
		
		page = customerServiceService.listPageWithCondition(parameters,pageNo,pageSize,userId,urlType);
		if(parameters != null && parameters.size() > 0){
			
			//把查询条件转换成字符串， 用于传递给页码，在点击下一页是会吧查询条件传递下去
			String queryString = PageUtils.encodeParamsToQueryString(parameters);
			map.put("queryString", queryString);
		}
		map.put("page", page);
		//获取所有用户 ,用于分配给
		List<User> userList = userService.getAllUser();
		map.put("userList", userList);
		//用户form:select 和 form:option绑定模型
		map.put("service", new CustomerServiceEntity());
		
		return "/service/"+urlType+"/list";
	}
	
	
	/**
	 *@Description:Ajax实现 服务分配
	 *@Author:du_minchao
	 *@Version:
	 *@CreateDate:1 Apr 2016
	 *@return:String   /service/update
	 */
	@RequestMapping(value="/update",method=RequestMethod.PUT)
	public String update(@RequestParam("allotTo") Integer allotTo_id,
			HttpSession session,
			@RequestParam(value="id",required=false)Integer id
			){
		//需要修改分配者id， 分配时间
		/*User user = (User) session.getAttribute("user");
		Long userId = user.getId();*/
		customerServiceService.updateAllotTo(id,allotTo_id);
		
		return "redirect:/service/allot/list?pageNoStr=1";
	}
	
	
	/**
	 *@Description:创建客户服务
	 *@Author:du_minchao
	 *@Version:
	 *@CreateDate:29 Mar 2016
	 *@return:String
	 */
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public String create(CustomerServiceEntity customerServiceEntity){
		customerServiceService.saveCustomerService(customerServiceEntity);
		return "redirect:/service/list/1000";
	}
	
	/**
	 *@Description: 为创建新服务准备数据，然后跳转页面
	 *@Author:du_minchao
	 *@Version:
	 *@CreateDate:29 Mar 2016
	 *@return:String
	 */
	@RequestMapping(value="/create",method=RequestMethod.GET)
	public String toCreate(Map<String, Object> map){
		
		//1.查询数据字典获取服务类型
		List<String> serviceType = dictsService.getAllElementsByType("服务类型");
		//2.查询所有客户customer
		List<Customer> customerList = customerService.getAllCustomer();
	
		//3.把查询到的数据放在map隐含域中， 跳转页面显示数据
		map.put("serviceType", serviceType);
		map.put("customerList", customerList);
		
		return "service/input";
	}
	

	/**
	 *@Description: 不带查询条件的分页显示  服务的分配
	 *@Author:du_minchao
	 *@Version:
	 *@CreateDate:1 Apr 2016
	 *@return:String       为题： 下一页不好使    /service/allot/list
	 */
	/*@RequestMapping(value="/allot/list")
	public String list(@RequestParam(value="pageNoStr",required=false) String pageNoStr,
			   			Map<String, Object> map){
		int pageNo = 1;
		//强转失败保持默认值
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {}
		
		//查询带分页的客户服务列表  为分配的
		int pageSize = 5;
		Page<CustomerServiceEntity> page = customerServiceService.listWithPage(pageNo , pageSize);
		map.put("page", page);
		//获取所有用户 ,用于分配给
		List<User> userList = userService.getAllUser();
		map.put("userList", userList);
		//用户form:select 和 form:option绑定模型
		map.put("service", new CustomerServiceEntity());
		
		return "/service/allot/list";
	}*/
	
}
