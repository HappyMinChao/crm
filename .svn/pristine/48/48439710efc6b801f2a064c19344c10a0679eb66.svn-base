package com.atguigu.crm.test;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.crm.mappers.CustomerServiceMapper;
import com.atguigu.crm.services.ContactService;
import com.atguigu.crm.services.CustomerService;
import com.atguigu.crm.services.SalesChanceService;
import com.atuigu.crm.entity.Contact;
import com.atuigu.crm.entity.Customer;
import com.atuigu.crm.entity.CustomerServiceEntity;
import com.atuigu.crm.entity.SalesChance;
import com.atuigu.crm.orm.Page;

public class ApplicationContextTest {

	private ApplicationContext ctx = null;
	private SalesChanceService salesChanceService;
	
	{
		//ctx = new ClassPathXmlApplicationContext("applicationContext*.xml");
		//salesChanceService = ctx.getBean(SalesChanceService.class);
	}
	

	@Test
	public void testGetPage4(){
		ContactService bean = ctx.getBean(ContactService.class);
		Page<Contact> page = bean.getContactPage(1, 2, 2L);
		List<Contact> contents = page.getContent();
		for (Contact contact : contents) {
			System.out.println(contact);
			
		}
		
	}
	@Test
	public void testGetPage3(){
		CustomerService bean = ctx.getBean(CustomerService.class);
		Page<Customer> pageWithoutCondition = bean.getPageWithoutCondition(2, 3);
		List<Customer> content = pageWithoutCondition.getContent();
		for (Customer customer : content) {
			System.out.println(customer);
			
		}
	}
	
	@Test
	public void testGetPage2(){
		String sdf = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
		System.out.println(sdf);
	}
	
	
	
	@Test
	public void testGetPage1(){
		Page<SalesChance> page = salesChanceService.getPage(4);
		
		System.out.println(page.getTotalElements());
		System.out.println(page.getTotalPages());
		System.out.println(page.getContent());
	}
	
	@Test
	public void testDataSource() throws SQLException {
		DataSource dataSource = ctx.getBean(DataSource.class);
		System.out.println(dataSource.getConnection());
	}
	@Test
	public void testFinish(){
		salesChanceService.finish(1004);
	}

}
