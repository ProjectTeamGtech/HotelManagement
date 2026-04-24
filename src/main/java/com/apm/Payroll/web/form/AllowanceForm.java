package com.apm.Payroll.web.form;

import java.util.ArrayList;

import com.apm.Payroll.eu.entity.Allowance;

public class AllowanceForm {

	private int id;
	private String allowance_name;
	private String allowanceType;
	private String value;
	
	
	private ArrayList<Allowance> listallowances;
	
	
	
	
	public ArrayList<Allowance> getListallowances() {
		return listallowances;
	}
	public void setListallowances(ArrayList<Allowance> listallowances) {
		this.listallowances = listallowances;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAllowance_name() {
		return allowance_name;
	}
	public void setAllowance_name(String allowance_name) {
		this.allowance_name = allowance_name;
	}
	public String getAllowanceType() {
		return allowanceType;
	}
	public void setAllowanceType(String allowanceType) {
		this.allowanceType = allowanceType;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
