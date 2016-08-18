package com.atguigu.crm.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.atuigu.crm.orm.PropertyFilter;
import com.atuigu.crm.orm.PropertyFilter.MatchType;

public class PropertyFilterTest {

	@Test
	public void testParseRequestParmasToFilters(){
		Map<String, Object> params = new HashMap<>();
		params.put("EQD_birth", "1990-12-12");
		params.put("LIKES_lastName", "a");
		
		List<PropertyFilter> filters = PropertyFilter.parseRequestParmasToFilters(params);
		System.out.println(filters);
		
		params = PropertyFilter.parseFiltersToMybatisParams(filters);
		System.out.println(params);
	}
	
	//Enum 的 valueOf 方法可以把一个字符串转为其对应的枚举类对象。 
	@Test
	public void testEnumValueOf() {
		String str = "EQ";
		
		MatchType matchType = Enum.valueOf(MatchType.class, str);
		System.out.println(matchType.getClass().getName());
	}

}
