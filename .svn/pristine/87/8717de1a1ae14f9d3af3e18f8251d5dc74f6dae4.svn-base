package com.atguigu.crm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtils {
	public static Date FormateDate(Date date){
		String sdf = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
		
		try {
			date = new SimpleDateFormat().parse(sdf);
		} catch (ParseException e) {}
		return date;
	}
}
