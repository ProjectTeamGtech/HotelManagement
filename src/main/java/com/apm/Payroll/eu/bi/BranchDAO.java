package com.apm.Payroll.eu.bi;

import java.util.ArrayList;

import com.apm.Payroll.eu.entity.Payroll;

public interface BranchDAO {

	public int addBranch(Payroll payroll);

	public int updateBranch(Payroll payroll);

	public ArrayList<Payroll> getAllBranchList();

	public Payroll getBranch(String id);
	
	
}
