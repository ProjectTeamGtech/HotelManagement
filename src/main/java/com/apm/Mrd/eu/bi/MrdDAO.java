package com.apm.Mrd.eu.bi;

import java.util.ArrayList;

import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.Mrd.eu.entity.Mrd;
import com.apm.common.utils.Pagination;



public interface MrdDAO {

	ArrayList<Mrd> getmrddetails(String fromdate, String todate,
			String searchtext, String wardnameid);
	int updatemrdetails(Mrd mrd);

	int changeMrdStatus(String id);
	int setMrdCheckList(String mrd_clientid, String mrd_ipdid, String templateId);
	String gettemplateid();
	int updateMrdChecklist(String templateId,String mrdid);
	ArrayList<Mrd> getbedlistFromWardId(String id);
	String getIpdidFromClientId(String clientid);
	int saveMrdDetails(Mrd mrd);
	ArrayList<Mrd> getmrdlist(String fromdate, String todate, String searchtext,
			String department,Pagination pagination, String searchbydate,String searchbyipdid, int i);
	Mrd getPatientForEdit(String id);
	int updateMrdInformation(Mrd mrd);
	
	String getSpecialityId(String condition);
	
	int getTotalMrdCount(String fromdate,String todate,String searchtext,String department, String searchbydate);
	public ArrayList<Mrd>getallshiftmrdlist(String mrdid);
	public int saveshiftMrdDetails(Mrd mrd);
	public Mrd editshiftMrdDetails(String id);
	public int updateshiftMrdDetails(Mrd mrd);
	int updateShiftStatus(String shiftpatientmrdid);
	int saveeditShiftMrdDetails(String shiftmrdid);
	int cancelMrd(String id);
}
