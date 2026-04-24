package com.apm.Pacs.eu.bi;


import java.sql.Connection;
import java.util.ArrayList;

import com.apm.Master.eu.entity.Master;
import com.apm.Pacs.eu.entity.Pacs;



public interface PacsDAO {

	int saveData(String modality, String string);

	int saveFileName(String fileName,Connection connection,String dir,String invstid,String date);

	int updateData(String modality, String column,int id,Connection connection);

	ArrayList<Pacs> getPacsList(Connection connection);

	ArrayList<Pacs> getPacsDataList(Connection connection);

	Pacs getPacsData(Connection connection, String label);

	int getModid(String modality,Connection connection);

	int updateModid(int modid, int insertid, Connection connection);

	ArrayList<Pacs> getSearchedPacsList(Connection connection, String fromdate,
			String todate,ArrayList<String>list,int status,String pid,String pname);

	int saveSelectedImgid(String label,Connection connection);

	boolean checkInvst(Connection connection,String jnvstid);

	Pacs getInvstigationDetails(String invstid,Connection connection);

	int saveInvstID(String invstid,Pacs pacs,Connection connection);

	boolean checkPacsImgId(String invstid,Connection connection);

	int getPacsId(String invstid,Connection connection);

	int saveFindings(String finding, String findingtxt,Connection connection);

	int getSelectedImgID(Connection connection);

	int saveServerFindings(String finding, String findingtxt,
			Connection connection);

	int getDicomSelectedId(Connection connection);

	String getSelectedDicomInvstid(int selectedid,Connection connection);

	ArrayList<Master> getInvstList(String curdate);

	ArrayList<Master> getModalityList();

	ArrayList<Pacs> getwebpacsList(Connection connection, String searchText,
			String fromDate, String toDate, String selectedmodality,String searchInvstid,String clientid,String pacsip);

	int updatefinding(int selectedid, String txt);

	String getSelectedFinding(String id);

	String getSelectedInvstid(int selectedid);

	ArrayList<Pacs> getFolderList(String fromDate, String toDate);

	int getPacsCount(String id);

	ArrayList<Pacs> getPacsImgList(String id);

	int insertDicomid(int dicomid,String userid);

	int truncatemultipacs(String userid);

	int updatewordfilename(String selectedinvstid, String fileName);

	ArrayList<Pacs> getnewwebpacsList(Connection connection, String clientid, String pacsip, String abrivationid);

	int updatepatientuhid(Pacs pacs, String id, String uhid, String pacsip, String pacsid);

	Pacs getPatientUhidbyid(String id);

	Pacs getpacsFindingbypacsid(String selectedid);

	Pacs getusernamebyuhid(String abrivationid);

	int updateWriteupData(int parseInt, String finding, String string, String string2);

	ArrayList<Pacs> getTemplateList();

	String getTemplateData(String id);


}
