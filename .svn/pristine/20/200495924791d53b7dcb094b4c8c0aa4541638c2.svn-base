package com.atguigu.crm.handlers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.services.DictsService;
import com.atguigu.crm.utils.PageUtils;
import com.atuigu.crm.entity.Dict;
import com.atuigu.crm.orm.Page;
@Controller
@RequestMapping("/dict")
public class DictHandler {
	@Autowired
	private DictsService dictService;
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request, 
			@RequestParam(value="pageNoStr",required=false) String pageNoStr, Map<String, Object> map){
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {}
		
		//获取查询条件的请求参数对应的 Map
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, "search_");
		
		//如何能保证在分页时可以携带查询条件. 
		//把查询条件的 params 转为查询的字符串
		String queryString = PageUtils.encodeParamsToQueryString(params);
		//把查询条件的字符串放入到 request 中
		map.put("queryString", queryString);
		
		Page<Dict> page = dictService.getPage(pageNo, params);
		map.put("page", page);
		return "dict/list";
	}
	
	public String create(){
		return "dict/input";
	}
}
