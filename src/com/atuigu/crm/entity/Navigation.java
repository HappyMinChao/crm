package com.atuigu.crm.entity;

import java.util.Collection;
import java.util.HashSet;

public class Navigation {
	
	private Long id;
	private String text;
	
	private String state;
	private String url;
	
	private Collection<Navigation> children = new HashSet();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Collection<Navigation> getChildren() {
		return children;
	}

	public void setChildren(Collection<Navigation> children) {
		this.children = children;
	}
	
	
}
