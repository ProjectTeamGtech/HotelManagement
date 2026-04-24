package com.apm.Registration.eu.bi;

import java.sql.Connection;
import java.util.ArrayList;

import com.apm.Registration.eu.entity.Casualty;
import com.apm.common.utils.Pagination;

public interface CasualtyDAO {

	int saveCasualty(Casualty casualty);

	int savePatient(Casualty casualty);



	Casualty getCasualtyDetails(String id);

	int updateCasualty(Casualty casualty, int id);

	ArrayList<Casualty> getCasualtyList(Connection connection, Pagination pagination);

	int getTotalCasualtyList();

}
