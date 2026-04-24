package com.apm.Payroll.eu.bi;

import java.util.ArrayList;

import com.apm.Payroll.eu.entity.Payroll;

public interface DeductionDAO {

	public ArrayList<Payroll> getDeductionList();
	public int addDeduction(Payroll payroll);
	public Payroll getDeduction(String id);
	public int updateDeduction(Payroll payroll);
}
