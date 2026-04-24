package com.apm.Payroll.eu.bi;

import java.util.ArrayList;

import com.apm.Payroll.eu.entity.Payroll;

public interface BankDAO {

	ArrayList<Payroll> getAllBankList();

	int addBank(Payroll payroll);

	Payroll getBankData(String id);

	int updateBank(Payroll payroll);

}
