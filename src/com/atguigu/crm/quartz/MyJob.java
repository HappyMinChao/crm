package com.atguigu.crm.quartz;

import org.springframework.beans.factory.annotation.Autowired;

import com.atguigu.crm.services.CustomerDrainService;

public class MyJob {
	
	@Autowired
	private CustomerDrainService customerDrainService;
	//在这个方法里面定义石英执行时需要的操作
	public void doMyJob(){
		System.out.println("====================");
		customerDrainService.add();
	}
	
}
