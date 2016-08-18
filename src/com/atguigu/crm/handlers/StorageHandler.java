package com.atguigu.crm.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.crm.services.StorageService;

@RequestMapping("/storage")
@Controller
public class StorageHandler {
	
	@Autowired
	private StorageService storageService;
	
	
}
