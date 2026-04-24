package com.apm.Ambulance.eu.bi;

import java.util.ArrayList;
import java.util.Vector;

import com.apm.Ambulance.eu.entity.Duty;
import com.apm.DiaryManagement.eu.entity.OpdConsult;
import com.apm.common.utils.Pagination;

public interface DutyDAO {

	int addDutyDB(Duty duty);

	int getTotalDutylist();

	ArrayList<Duty> getDutylist(String searchText, Pagination pagination, String driverid);

	Duty getdutyAllocation(String selectedid);

	int updateDutyDB(Duty duty);

	int deleteDuty(String selectedid);

	ArrayList<Duty> getambulancereportlist(String fromDate, String toDate, String searchText);

	ArrayList<Duty> getPunchkarmaReport(String fromDate, String toDate, String searchText);

	int savePunchkarmanote(Duty duty);

	Duty geteditPunchkarmanote(String selectedid);

	int updatePunchkarmaNote(Duty duty);
	
	ArrayList<Duty> getPunchkarmaList(String fromDate, String toDate, int ipdid, String opdid);
	
	int deletepunchkarmaData(String selectedid);

	Vector<Duty> getBamsPunchkarmaData(OpdConsult opdConsult2, String dbname);

	int insertBamsPunchkarmaData(Duty duty);

	Vector<Duty> getChildPunchkarmadata(String id, String dbname);

	int insertChildPunchkarmaData(Duty duty2);


}
