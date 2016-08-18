package com.atguigu.crm.handlers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.services.PlanService;
import com.atguigu.crm.utils.PageUtils;
import com.atuigu.crm.entity.SalesChance;
import com.atuigu.crm.entity.SalesPlan;
import com.atuigu.crm.orm.Page;

@Controller
@RequestMapping("/plan")
public class PlanHandle {
	
	@Autowired
	private PlanService planService;
	
	/**
	 * @describe 
	 * @author shanshan
	 * @time 2016年3月31日
	 */
	@RequestMapping(value="/list")
	public String list(HttpServletRequest request, String pageNoStr, Map<String, Object> map){
		//获取查询条件的请求参数对应的 Map
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, "search_");
		
		//保证在分页时可以携带查询条件. 
		//1.查询条件的 params 转为查询的字符串
		String queryString = PageUtils.encodeParamsToQueryString(params);
		//2.查询条件的字符串放入到 request 中
		map.put("queryString", queryString);
		
		Page<SalesChance> page=planService.getPage(pageNoStr,params);
		map.put("page", page);
		return "plan/list";
	}
	
	/**
	 * @describe 
	 * @author shanshan
	 * @time 2016年3月31日
	 */
	@RequestMapping(value="/make/{id}",method=RequestMethod.GET)
	public String make(@PathVariable("id") Integer id,Map<String,Object> map){
		//显示计划用户的基本信息
		SalesChance chance=planService.getBeanById(id);
		//显示对用户所要使用的计划
		List<SalesPlan> plans= planService.getBeanListByChanceId(id);
		map.put("chance", chance);
		map.put("plans", plans);
		return "plan/make";
	}
	
	/**
	 * @describe 
	 * @author shanshan
	 * @time 2016年3月31日
	 */
	@RequestMapping(value="/execution/{chanceId} ",method=RequestMethod.GET)
	public String execution(@PathVariable("chanceId")Integer chanceId,Map<String,Object> map){
		//显示计划用户的基本信息
		SalesChance chance=planService.getBeanById(chanceId);//----------------------需要修改
		//显示对用户所要执行的计划
		List<SalesPlan> plans= planService.getBeanListByChanceId(chanceId);
		map.put("chance", chance);
		map.put("plans", plans);
		return "plan/execution";
	}
	
	//------------------------------------------------------------(plan的crud)ajax请求
	/**
	 * @describe 
	 * @author shanshan
	 * @time 2016年3月31日
	 */
	@ResponseBody
	@RequestMapping(value="/ajax/",method=RequestMethod.POST)
	public long createPlan(SalesPlan salesPlan){
		long id=planService.savePlan(salesPlan);
		return id;
	}
	
	/**
	 * @describe 
	 * @author shanshan
	 * @time 2016年3月31日
	 */
	@ResponseBody
	@RequestMapping(value="/ajax/{id}",method=RequestMethod.PUT)
	public String updatePlan(SalesPlan salesPlan){
		planService.updatePlan(salesPlan);
		return 1+""; 
	}
	
	/**
	 * @describe 
	 * @author shanshan
	 * @time 2016年3月31日
	 */
	@ResponseBody
	@RequestMapping(value="/ajax/{id}",method=RequestMethod.DELETE)
	public String DeletePlan(@PathVariable("id")Integer id){
		planService.deletePlan(id);
		return 1+"";
	}
	
	//------------------------------------------------------------(plan执行结果的crud)ajax请求
	
	/**
	 * @describe 
	 * @author shanshan
	 * @time 2016年3月31日
	 */
	@ResponseBody
	@RequestMapping(value="/ajax_exe/{id}",method=RequestMethod.PUT)
	public String exePlan(@PathVariable("id") Integer id,String result){
		if(result==null || result.length()==0){
			return null;
		}
		planService.exePlan(id,result);
		return "1";
	}
	
}
