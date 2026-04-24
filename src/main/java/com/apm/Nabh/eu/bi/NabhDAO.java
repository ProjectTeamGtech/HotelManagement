package com.apm.Nabh.eu.bi;

import java.util.ArrayList;

import com.apm.Report.eu.entity.MisReport;

public interface NabhDAO {

	MisReport getGrahicalKPI(String kpi_year, String kpiid);

	String checkKPIStatus(String kpiid, String kpi_year, String kpi_month);

	ArrayList<MisReport> getAccadmicTrackerList(String subcategoryid, String areaid);

	String getTopNabhSubcategory();

	ArrayList<MisReport> getNABHAreaList(String subcategoryid);

	int saveNabhFileSubmissionData(String datetime, String userid, String filesubmission_category, String filesubremark,
			String fileName, String filecontenttype, String action, int fromvideotutorial, String filesubsubmodule, String filesubserachkey);

	ArrayList<MisReport> getFileSubmissionList(String action, String filePath, int fromvideotutorial, String querysearch);

	MisReport getFileSubmissionData(String id);

	String getTopAreaFromSubcategoryId(String subcategoryid);

	int deleteFileUploaded(String id, String userId, String todate);

	int updateNabhChecklistData(String datetime, String userid, int chcklistid, String filesubremark, String fileName,
			String subuploadfilesContentType, String action, int i);

	int updateNabhCompleteChecklistData(String datetime, String userid, int chcklistid, String filesubremark);

	MisReport getChkListData(String id);

	ArrayList<MisReport> getNABHIndicatorList(String areaid);

	int saveNabhChecklist(String areaid, String indicator, String remark, String userid, String toDate);

	ArrayList<MisReport> getSubmoduleList(String action);

	int checkAlreadySavedSubModule(String submodule, String action);

	int saveSubmoduleData(String submodule, String action);

	ArrayList<MisReport> getVideoTutorialFileSubmissionList(String action, String filePath, int i, String querysearch);

	int saveVideoTFileSubmissionData(String datetime, String userid, String filesubmission_category,
			String filesubremark, String fileName, String filecontenttype, String action, int i,
			String filesubsubmodule, String filesubserachkey, String doctitle, String docfeature);

	MisReport getVideoTutorialData(String id);

	int deleteVideoTFileUploaded(String id, String userId, String todate);

}
