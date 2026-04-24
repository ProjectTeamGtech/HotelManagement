package com.apm.Payroll.eu.bi;

import java.util.ArrayList;

import com.apm.Payroll.eu.entity.Allowance;

public interface AllowanceDAO {

	ArrayList<Allowance> getAllAllowancesList();

	int addAllowance(Allowance allowance);

	Allowance getAllowance(String id);

	int updateAllowance(Allowance allowance);

	
	
}
