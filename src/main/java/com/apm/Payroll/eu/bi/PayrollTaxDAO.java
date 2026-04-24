package com.apm.Payroll.eu.bi;

import java.util.ArrayList;

import com.apm.Payroll.eu.entity.Payroll;

public interface PayrollTaxDAO {

	
	public ArrayList<Payroll> getAllTaxlist();

	public int addTax(Payroll payroll);

	public Payroll getTax(String id);

	public int updateTax(Payroll payroll);
	
	
}
