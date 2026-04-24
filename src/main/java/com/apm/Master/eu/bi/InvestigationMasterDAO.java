package com.apm.Master.eu.bi;

import java.util.ArrayList;

import com.apm.Ipd.eu.entity.Bed;
import com.apm.Master.eu.entity.Master;
import com.apm.common.utils.Pagination;


public interface InvestigationMasterDAO {

	public ArrayList<Master> getAllInvestigationTypes(Pagination pagination, String searchText, String isdeleted, ArrayList<Bed> wardlist);
	public ArrayList<Master> getAllInvestigationTypes();
	public int addInvestigationTypeMaster(Master master);
	public ArrayList<Master> getAllInvestigationNames(Pagination pagination, String searchText);
	public ArrayList<Master> getAllInvestigationNames();
	public int getTotalInvestigationTypesCount(String searchText);
	public int getTotalInvestigationNamesCount(String searchText);
	public int addInvestigationName(Master master);
	public int deleteInvestigationName(String selectedid);
	public Master getInvestigationName(String selectedid);
	public int updateInvestigationName(Master master);
	public Master getInvestigationType(String selectedid);
	public int updateInvestigationType(Master master);
	public int deleteInvestigationType(String selectedid);
	public String getInvestigationTypeId(String invtype);
	public int getInvsNameId(String invgrpid, String invsname);
	public ArrayList<Master> getAllSectionList();
	public ArrayList<Master> getAllJobTitle();
	public ArrayList<Master> getSectionList(String id);
	public int isdeletedInvestigationType(String selectedid);
	public int addInvAppointType(Master master1, int wardid);
	public boolean checkExistAppointType(Master master1, int id);
	public int updateInvAppointType(Master master, int id, int sts);
	public int updateInvestigationTypeData(String id, String alternativetext, String chargess);
	public int getInvChargeId(String invtypename, String wardid, String paytype);
	public int updateInvApmentTypeData(int aptid, String chargess);
	
	
}
