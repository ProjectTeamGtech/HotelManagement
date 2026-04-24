package com.apm.Registration.eu.entity;

public class Currency {
	
	private int id;
	
	private String sign;
	
	public Currency(int id,String sign){
		this.id = id;
		this.sign = sign;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
	
	

}
