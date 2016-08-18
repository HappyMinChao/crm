package com.atguigu.crm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.crm.mappers.ActivtyMapper;
import com.atuigu.crm.entity.CustomerActivity;
import com.atuigu.crm.orm.Page;

@Service
public class ActivityService {
	
	@Autowired
	private ActivtyMapper activtyMapper;
	
	/**
	 * @describe 获取指定客户的交往记录 
	 * @author shanshan
	 * @time 2016年3月31日
	 */
	public Page getPage(String pageNoStr, long id) {
		int pageNo=1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {
		}
		
		int totalCount=activtyMapper.getTotalCount(id);//                          总记录
		Page page=new Page<CustomerActivity>();
		page.setPageNo(pageNo);
		page.setTotalElements(totalCount);
		
		//确定查询 content 需要的 firstIndex 和 endIndex
		int firstIndex = (page.getPageNo() - 1) * page.getPageSize() + 1;
		int endIndex = firstIndex + page.getPageSize();
		
		List<CustomerActivity> pageList = activtyMapper.getActivityListByCustomerId(id,firstIndex,endIndex);
		page.setContent(pageList);
		return page;
	}

	public void create(CustomerActivity customerActivity) {
		activtyMapper.create(customerActivity);
	}

	public CustomerActivity getBeanById(Long id) {
		if(id==null){
			return new CustomerActivity();
		}
		return activtyMapper.getBeanById(id);
	}

	public void update(CustomerActivity customerActivity) {
		activtyMapper.update(customerActivity);
	}

	public void delete(long id) {
		activtyMapper.delete(id);
	}

}
