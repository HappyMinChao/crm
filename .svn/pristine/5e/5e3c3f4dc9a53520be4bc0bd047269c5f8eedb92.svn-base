package com.atguigu.crm.utils;

import java.util.Map;

public class PageUtils {
	
	/**
	 * 把查询的条件封装成一个字符串
	 * @param params
	 * @return
	 */
	public static String encodeParamsToQueryString(Map<String, Object> params) {
		StringBuilder queryString = new StringBuilder();
		
		if(params != null
				&& params.size() > 0){
			for(Map.Entry<String, Object> entry: params.entrySet()){
				String key = entry.getKey();
				Object val = entry.getValue();
				
				if("".equals(val)){
					continue;
				}
				
				queryString.append("search_")
				                  .append(key)
				                  .append("=")
				                  .append(val)
				                  .append("&");
			}
			
			if(queryString.length() > 0){
				queryString.replace(queryString.length() - 1, queryString.length(), "");
			}
		}
		
		return queryString.toString();
	}
	
}
