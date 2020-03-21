package com.cp.dd.web.controller.member;

import java.io.Serializable;





public class GghMenu implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -651717476101675311L;

	private Long id;
	
	private String name;

	private String url;

	private int parent;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	public int getParent() {
		return parent;
	}

	public void setParent(int parent) {
		this.parent = parent;
	}



	public GghMenu(Long id, String name, String url,  int parent) {
		this.id = id;
		this.name = name;
		this.url = url;
		this.parent = parent;
	}
}