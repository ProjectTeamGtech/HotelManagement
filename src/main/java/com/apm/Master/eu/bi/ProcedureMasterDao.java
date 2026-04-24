package com.apm.Master.eu.bi;

import java.util.ArrayList;

import com.apm.Master.eu.entity.Procedure;

public interface ProcedureMasterDao {

	int saveProcedure(Procedure procedure);

	ArrayList<Procedure> getprocedurelist(String searchText);

	Procedure geteditprocedure(String selectedid);

	int updateprocedure(Procedure procedure);

	int deleteprocedureData(String selectedid);

	

}
