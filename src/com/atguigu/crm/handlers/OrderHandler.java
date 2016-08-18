package com.atguigu.crm.handlers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.services.OrderService;
import com.atguigu.crm.utils.PageUtils;
import com.atuigu.crm.entity.Order;
import com.atuigu.crm.entity.SalesChance;
import com.atuigu.crm.orm.Page;
@Controller
@RequestMapping("/order")
public class OrderHandler {
	@Autowired
	private OrderService orderService;
	/**by--dylan
	 * 根据客户id来查询当前客户的订单
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(HttpServletRequest request,@RequestParam("customerid") Long id,@RequestParam(value="pageNoStr",required=false) String pageNoStr,Map<String,Object> map){
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {}
		
		//获取查询条件的请求参数对应的 Map
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, "search_");
		//如何能保证在分页时可以携带查询条件. 
		//把查询条件的 params 转为查询的字符串
		String queryString = PageUtils.encodeParamsToQueryString(params);
		//customerid这个查询条件的保持
		queryString=queryString+"customerid="+id;
		//把查询条件的字符串放入到 request 中
		map.put("queryString", queryString);
		Page<Order> page=orderService.getCustomerOrder(id,pageNo,params);
		map.put("page", page);
		return "order/list";
	}
	@RequestMapping(value="/details",method=RequestMethod.GET)
	public String details(@RequestParam("id") Long id,Map<String,Object> map){
		Order order=orderService.getOrderById(id);
		map.put("order", order);
		return "order/details";
	}
	
}
