package com.apm.Payroll.eu.bi;

import java.util.ArrayList;

import com.apm.Payroll.eu.entity.Payroll;

public interface PayrollDepartmentDAO {

	ArrayList<Payroll> getAllDepartmentList();

	int addDepartment(Payroll payroll);

	Payroll getDepartment(String id);

	int updateDepartment(Payroll payroll);

	Payroll getCompanyDetails(String id);

	int updateCompany(Payroll payroll);

	ArrayList<Payroll> getAllDesignation();

	int addDesignation(Payroll payroll);

	Payroll getCompanyDetailsUser(int clinicid);

	Payroll getDesignation(String id);

	int updateDesignation(Payroll payroll);

	int deleteDepartment(String id);

	int deleteHoliday(String id);

	int deleteDesignation(String id);

	int deleteLeave(String id);

	ArrayList<Payroll> getAllQualification();

	int addQualification(Payroll payroll);

	int updateQualification(Payroll payroll);

	Payroll getQualifications(String id);

	int deleteQualification(String id);

	int deleteOT(String id);

}
