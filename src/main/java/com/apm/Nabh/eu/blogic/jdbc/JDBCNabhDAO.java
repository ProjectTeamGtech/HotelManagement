package com.apm.Nabh.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.apm.Mis.eu.bi.MisChartDAO;
import com.apm.Mis.eu.blogic.jdbc.JDBCMisChartDAO;
import com.apm.Nabh.eu.bi.NabhDAO;
import com.apm.Report.eu.entity.MisReport;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.web.action.BaseAction;

public class JDBCNabhDAO extends BaseAction implements NabhDAO {
 Connection connection = null;
 public JDBCNabhDAO(Connection connection){
	 this.connection = connection;
 }
public MisReport getGrahicalKPI(String kpi_year, String indicatorid) {
	MisReport misReport = new MisReport();
	try {
		MisChartDAO misChartDAO = new JDBCMisChartDAO(connection);
		StringBuilder builder = new StringBuilder();
		builder.append("select kpi_master.id, kpi_master.areaid, indicatorid, no_of_input, prompt1, prompt2, prompt3, prompt4, prompt5, formula, formula_desc ");
		builder.append(",area,indicator ");
		builder.append("from kpi_master ");
		builder.append("inner join kpi_area on kpi_area.id = kpi_master.areaid  ");
		builder.append("inner join kpi_indicator on kpi_indicator.id = kpi_master.indicatorid ");
		builder.append("where kpi_master.indicatorid ='"+indicatorid+"'");
		PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			misReport = misChartDAO.getYearWiseData(kpi_year,rs.getString(1));
			misReport.setKpiid(rs.getString(1));
			misReport.setKpi_year(kpi_year);
			misReport.setKpiarea(rs.getString(12));
			misReport.setKpiindicator(rs.getString(13));
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return misReport;
}
public String checkKPIStatus(String kpiid, String kpi_year, String kpi_month) {
	PreparedStatement preparedStatement = null;
	String result ="0";
	try{
		String sql = "select id from kpi_data where kpiid="+kpiid+" and kpi_year='"+kpi_year+"' and kpi_month='"+kpi_month+"' ";
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()){
			result = rs.getString(1);
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	return result;
}
public ArrayList<MisReport> getAccadmicTrackerList(String subcategoryid, String areaid) {
	ArrayList<MisReport> arrayList = new ArrayList<MisReport>();
	try {
		StringBuilder builder = new StringBuilder();
		builder.append("select nabh_subcatagory.id,nabh_subcatagory.name from nabh_subcatagory ");
		builder.append("inner join nabh_Catagory on nabh_Catagory.id = nabh_subcatagory.catagoryid ");
		builder.append("where nabh_Catagory.id='2' and nabh_subcatagory.id='"+subcategoryid+"' ");
		PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
		ResultSet rs = preparedStatement.executeQuery();
		int i=0;
		while (rs.next()) {
			MisReport misReport = new MisReport();
			misReport.setSubcatid(rs.getString(1));
			misReport.setSubcatname(rs.getString(2));
			if(areaid!=null){
				if(i==0){
					ArrayList<MisReport> arealist = getKPIAssementArea(rs.getString(1),areaid);
					misReport.setArealist(arealist);
				}else{
					ArrayList<MisReport> arealist = new ArrayList<MisReport>();
					misReport.setArealist(arealist);
				}
				i = i+1;
			}else{
				ArrayList<MisReport> arealist = getKPIAssementArea(rs.getString(1),areaid);
				misReport.setArealist(arealist);
			}
			int pendingcount= getCheckListCount(subcategoryid,0);
			int submitcount= getCheckListCount(subcategoryid,1);
			int completecount= getCheckListCount(subcategoryid,2);
			misReport.setPendingcount(pendingcount);
			misReport.setSubmitcount(submitcount);
			misReport.setCompletecount(completecount);
			arrayList.add(misReport);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return arrayList;
}

private int getCheckListCount(String subcategoryid, int status) {
	int count =0;
	try {
		StringBuffer buffer = new StringBuffer();
		buffer.append("select count(*) from nabh_subcatagory ");
		buffer.append("inner join kpi_area on kpi_area.subcatagoryid = nabh_subcatagory.id ");
		buffer.append("inner join kpi_indicator on kpi_indicator.areaid = kpi_area.id ");
		buffer.append("inner join nabh_checklist on nabh_checklist.indicatorid = kpi_indicator.id ");
		buffer.append("where status='"+status+"' and nabh_subcatagory.id ='"+subcategoryid+"'");
		PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			count = rs.getInt(1);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return count;
}
public ArrayList<MisReport> getKPIAssementArea(String subcatid, String areaid){
	ArrayList<MisReport> arrayList = new ArrayList<MisReport>();
	try {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT kpi_area.id,kpi_area.area,kpi_area.subcatagoryid FROM kpi_area ");
		builder.append("where kpi_area.subcatagoryid='"+subcatid+"' ");
		if(areaid!=null){
			builder.append("and kpi_area.id='"+areaid+"' ");
		}
		PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()){
			MisReport misReport = new MisReport();
			misReport.setAreaid(rs.getString(1));
			misReport.setArea(rs.getString(2));
			ArrayList<MisReport> indicatorlist = getaccdmictrackerindicator(rs.getString(1));
			misReport.setIndicatorlist(indicatorlist);
			arrayList.add(misReport);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return arrayList;
}
public ArrayList<MisReport> getaccdmictrackerindicator(String areaid) {
	ArrayList<MisReport> arrayList = new ArrayList<MisReport>();
	try {
		StringBuilder builder = new StringBuilder();
		builder.append("select id,indicator from kpi_indicator ");
		builder.append("where areaid='"+areaid+"' ");
		PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()){
			MisReport misReport = new MisReport();
			misReport.setIndicatorid(rs.getString(1));
			misReport.setIndicator(rs.getString(2));
			ArrayList<MisReport> indicatorchklist = getNabhCheckList(rs.getString(1));
			misReport.setIndicatorchklist(indicatorchklist);
			arrayList.add(misReport);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return arrayList;
}
private ArrayList<MisReport> getNabhCheckList(String indicatorid) {
	ArrayList<MisReport> arrayList = new ArrayList<MisReport>();
	try {
		StringBuilder builder = new StringBuilder();
		builder.append("select id,chklist_name,status,complete_discription, submit_discription,filename from nabh_checklist ");
		builder.append("where indicatorid='"+indicatorid+"' ");
		PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()){
			MisReport misReport = new MisReport();
			misReport.setChcklistid(rs.getInt(1));
			misReport.setChcklistname(rs.getString(2));
			misReport.setChkliststatus(rs.getInt(3));
			misReport.setComplete_discription(rs.getString(4));
			misReport.setSubmit_discription(rs.getString(5));
			String filename ="";
			if(rs.getString(6)!=null){
				filename = rs.getString(6);
			}
			misReport.setFilename(filename);
			arrayList.add(misReport);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return arrayList;
}
public String getTopNabhSubcategory() {
	String subcategoryid="0";
	try {
		String sql="select id from nabh_subcatagory where catagoryid=2 limit 1";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			subcategoryid = rs.getString(1);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return subcategoryid;
}
public ArrayList<MisReport> getNABHAreaList(String subcategoryid) {
	ArrayList<MisReport> arrayList = new ArrayList<MisReport>();
	try {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT kpi_area.id,kpi_area.area,kpi_area.subcatagoryid FROM kpi_area ");
		builder.append("where kpi_area.subcatagoryid='"+subcategoryid+"' ");
		PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()){
			MisReport misReport = new MisReport();
			misReport.setAreaid(rs.getString(1));
			misReport.setArea(rs.getString(2));
			arrayList.add(misReport);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return arrayList;
}
public int saveNabhFileSubmissionData(String datetime, String userid, String filesubmission_category,
		String filesubremark, String fileName, String filecontenttype, String action, int fromvideotutorial, String filesubsubmodule, String filesubserachkey) {
	int result=0;
	try {
		String sql="insert into nabh_file_uploaded (discription, category, filename,userid,datetime,filecontenttype,uploadtype,from_videotutorial,filesubmodule,filesearchkey) values (?,?,?,?,?,?,?,?,?,?) ";
		PreparedStatement ps=connection.prepareStatement(sql);
		ps.setString(1, filesubremark);
		ps.setString(2, filesubmission_category);
		ps.setString(3, fileName);
		ps.setString(4, userid);
		ps.setString(5, datetime);
		ps.setString(6, filecontenttype);
		ps.setString(7, action);
		ps.setString(8, ""+fromvideotutorial);
		ps.setString(9, filesubsubmodule);
		ps.setString(10, filesubserachkey);
		result =ps.executeUpdate();
	} catch (Exception e) {

		e.printStackTrace();
	}
	return result;
	
}
public ArrayList<MisReport> getFileSubmissionList(String action, String filePath, int fromvideotutorial,String querysearch) {
	ArrayList<MisReport> arrayList = new ArrayList<MisReport>();
	try {
		//String sql="select id, discription, category, filename, userid, datetime,uploadtype from nabh_file_uploaded where uploadtype='"+action+"' and deleted='0' ";
		StringBuffer buffer = new StringBuffer();
		buffer.append("select id, discription, category, filename, userid, datetime,uploadtype,filesubmodule,filesearchkey from nabh_file_uploaded where uploadtype='"+action+"' and deleted='0' ");
		buffer.append("and from_videotutorial='"+fromvideotutorial+"' ");
		if(!querysearch.equals("")){
			buffer.append("and filesearchkey like '"+querysearch+"%'" );
		}
		PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			MisReport misReport = new MisReport();
			misReport.setId(rs.getInt(1));
			misReport.setRemark(rs.getString(2));
			misReport.setCategory(rs.getString(3));
			misReport.setFilename(rs.getString(4));
			misReport.setUserid(rs.getString(5));
			misReport.setDatetime(DateTimeUtils.getCommencingDate1(rs.getString(6).split(" ")[0]));
			String filePathnew = "liveData/document/"+rs.getString(4);
			//filePathnew = filePathnew.replace(" ", ENCODE_SPACE);
			misReport.setFilePathnew(filePathnew);
			if(fromvideotutorial>0){
				//String filePathlink = filePath +"/"+rs.getString(4);
				//misReport.setFilePathlink(filePathlink);
				misReport.setFilesubmodule(getSubmodulename(rs.getInt(8)));
				misReport.setFilesearchkey(rs.getString(9));
				if(rs.getString(7)!=null){
					if(rs.getString(7).equals("OPD")){
						misReport.setUploadtype("OPD Tutorial");
					}else{
						misReport.setUploadtype("OPD Tutorial");
					}
				}else{
					misReport.setUploadtype("OPD Tutorial");
				}
			}else{
				if(rs.getString(7)!=null){
					if(rs.getString(7).equals("PostersandVisuals")){
						misReport.setUploadtype("Posters and Visuals");
					}else if(rs.getString(7).equals("filesubmission")){
						misReport.setUploadtype("File Submission");
					}else if(rs.getString(7).equals("resource_forms")){
						misReport.setUploadtype("Forms and SOPS");
					}else if(rs.getString(7).equals("resource_qi_tools")){
						misReport.setUploadtype("QI Tools");
					}else if(rs.getString(7).equals("resource_training_material")){
						misReport.setUploadtype("Training Materials");
					}else if(rs.getString(7).equals("resource_video")){
						misReport.setUploadtype("Videos");
					}else{
						misReport.setUploadtype("File Submission");
					}
				}else{
					misReport.setUploadtype("File Submission");
				}
			}
			
			arrayList.add(misReport);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return arrayList;
}
private String getSubmodulename(int int1) {
	String data="";
	try {
		String sql="select id, submodulename from videotutorial_submodule where id='"+int1+"'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			data = rs.getString(2);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return data;
}
public MisReport getFileSubmissionData(String id) {
	MisReport misReport = new MisReport();
	try {
		String sql="select id, discription, category, filename, userid, datetime from nabh_file_uploaded where id='"+id+"'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			misReport.setId(rs.getInt(1));
			misReport.setRemark(rs.getString(2));
			misReport.setCategory(rs.getString(3));
			misReport.setFilename(rs.getString(4));
			misReport.setUserid(rs.getString(5));
			misReport.setDatetime(DateTimeUtils.getCommencingDate1(rs.getString(6).split(" ")[0]));
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return misReport;
}
public String getTopAreaFromSubcategoryId(String subcategoryid) {
	String areaid=null;
	try {
		String sql="select id from kpi_area where subcatagoryid='"+subcategoryid+"' limit 1";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			areaid = rs.getString(1);
			if(areaid!=null){
				if(areaid.equals("") || areaid.equals("0")){
					areaid = null;
				}
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return areaid;
}
public int deleteFileUploaded(String id, String userId, String todate) {
	int res =0;
	try {
		String sql ="update nabh_file_uploaded set deleted=1,deleted_userid='"+userId+"',deleted_time='"+todate+"' where id='"+id+"'";
		PreparedStatement  preparedStatement = connection.prepareStatement(sql);
		res = preparedStatement.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}
public int updateNabhChecklistData(String datetime, String userid, int chcklistid, String filesubremark,
		String fileName, String subuploadfilesContentType, String action, int status) {
	int res =0;
	try {
		String sql ="update nabh_checklist set status='"+status+"',submit_userid='"+userid+"',submit_date='"+datetime+"',filename=?, filetype=?,submit_discription=? where id='"+chcklistid+"'";
		PreparedStatement  preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, fileName);
		preparedStatement.setString(2, subuploadfilesContentType);
		preparedStatement.setString(3, filesubremark);
		res = preparedStatement.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}
public int updateNabhCompleteChecklistData(String datetime, String userid, int chcklistid, String filesubremark) {
	int res =0;
	try {
		String sql ="update nabh_checklist set status='2',completed_userid='"+userid+"',completed_date='"+datetime+"',complete_discription=? where id='"+chcklistid+"'";
		PreparedStatement  preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, filesubremark);
		res = preparedStatement.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}
public MisReport getChkListData(String id) {
	MisReport misReport = new MisReport();
	try {
		String sql="select id, filename from nabh_checklist where id='"+id+"'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			misReport.setId(rs.getInt(1));
			misReport.setFilename(rs.getString(2));
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return misReport;
}
public ArrayList<MisReport> getNABHIndicatorList(String areaid) {
	ArrayList<MisReport> arrayList = new ArrayList<MisReport>();
	try {
		StringBuilder builder = new StringBuilder();
		builder.append("select id,indicator from kpi_indicator ");
		builder.append("where areaid='"+areaid+"' ");
		PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()){
			MisReport misReport = new MisReport();
			misReport.setId(rs.getInt(1));
			misReport.setIndicator(rs.getString(2));
			arrayList.add(misReport);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return arrayList;
}
public int saveNabhChecklist(String areaid, String indicator, String remark, String userid, String toDate) {
	int result=0;
	try {
		String sql="insert into nabh_checklist (indicatorid, chklist_name, added_userid, added_date) values (?,?,?,?) ";
		PreparedStatement ps=connection.prepareStatement(sql);
		ps.setString(1, indicator);
		ps.setString(2, remark);
		ps.setString(3, userid);
		ps.setString(4, toDate);
		result =ps.executeUpdate();
	} catch (Exception e) {

		e.printStackTrace();
	}
	return result;
}
public ArrayList<MisReport> getSubmoduleList(String action) {
	ArrayList<MisReport> arrayList = new ArrayList<MisReport>();
	try {
		StringBuilder builder = new StringBuilder();
		builder.append("select id,modulename,submodulename from videotutorial_submodule ");
		builder.append("where modulename='"+action+"' ");
		PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()){
			MisReport misReport = new MisReport();
			misReport.setId(rs.getInt(1));
			misReport.setName(rs.getString(3));
			arrayList.add(misReport);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return arrayList;
}
public int checkAlreadySavedSubModule(String submodule, String action) {
	int res =0;
	try {
		String sql="select id from videotutorial_submodule where modulename='"+action+"' and submodulename='"+submodule+"'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rs= preparedStatement.executeQuery();
		while (rs.next()) {
			res =1;
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}
public int saveSubmoduleData(String submodule, String action) {
	int result=0;
	try {
		String sql="insert into videotutorial_submodule (modulename,submodulename) values (?,?) ";
		PreparedStatement ps=connection.prepareStatement(sql);
		ps.setString(1, action);
		ps.setString(2, submodule);
		result =ps.executeUpdate();
	} catch (Exception e) {

		e.printStackTrace();
	}
	return result;
}
public ArrayList<MisReport> getVideoTutorialFileSubmissionList(String action, String filePath, int fromvideotutorial,String querysearch) {
	ArrayList<MisReport> arrayList = new ArrayList<MisReport>();
	try {
		StringBuffer buffer = new StringBuffer();
		buffer.append("select id, discription, category, filename, userid, datetime,uploadtype,filesubmodule,filesearchkey,doctitle, docfeature from videotutorial_filedata where uploadtype='"+action+"' and deleted='0' ");
		buffer.append("and from_videotutorial='"+fromvideotutorial+"' ");
		if(!querysearch.equals("")){
			buffer.append("and filesearchkey like '"+querysearch+"%'" );
		}
		PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			MisReport misReport = new MisReport();
			misReport.setId(rs.getInt(1));
			misReport.setRemark(rs.getString(2));
			misReport.setCategory(rs.getString(3));
			misReport.setFilename(rs.getString(4));
			misReport.setUserid(rs.getString(5));
			misReport.setDatetime(DateTimeUtils.getCommencingDate1(rs.getString(6).split(" ")[0]));
			String filePathnew = "liveData/document/"+rs.getString(4);
			misReport.setFilePathnew(filePathnew);
			
			//String filePathlink = filePath +"/"+rs.getString(4);
			//misReport.setFilePathlink(filePathlink);
			misReport.setFilesubmodule(getSubmodulename(rs.getInt(8)));
			misReport.setFilesearchkey(rs.getString(9));
			if(rs.getString(7)!=null){
				if(rs.getString(7).equals("OPD")){
					misReport.setUploadtype("OPD Tutorial");
				}else{
					misReport.setUploadtype("OPD Tutorial");
				}
			}else{
				misReport.setUploadtype("OPD Tutorial");
			}
			misReport.setDoctitle(rs.getString(10));
			misReport.setDocfeature(rs.getString(11));
			arrayList.add(misReport);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return arrayList;
}
public int saveVideoTFileSubmissionData(String datetime, String userid, String filesubmission_category,
		String filesubremark, String fileName, String filecontenttype, String action, int fromvideotutorial, String filesubsubmodule,
		String filesubserachkey, String doctitle, String docfeature) {

	int result=0;
	try {
		String sql="insert into videotutorial_filedata (discription, category, filename,userid,datetime,filecontenttype,uploadtype,from_videotutorial,filesubmodule,filesearchkey,doctitle,docfeature) values (?,?,?,?,?,?,?,?,?,?,?,?) ";
		PreparedStatement ps=connection.prepareStatement(sql);
		ps.setString(1, filesubremark);
		ps.setString(2, filesubmission_category);
		ps.setString(3, fileName);
		ps.setString(4, userid);
		ps.setString(5, datetime);
		ps.setString(6, filecontenttype);
		ps.setString(7, action);
		ps.setString(8, ""+fromvideotutorial);
		ps.setString(9, filesubsubmodule);
		ps.setString(10, filesubserachkey);
		ps.setString(11, doctitle);
		ps.setString(12, docfeature);
		result =ps.executeUpdate();
	} catch (Exception e) {

		e.printStackTrace();
	}
	return result;
	

}
public MisReport getVideoTutorialData(String id) {
	MisReport misReport = new MisReport();
	try {
		String sql="select id, discription, category, filename, userid, datetime from videotutorial_filedata where id='"+id+"'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			misReport.setId(rs.getInt(1));
			misReport.setRemark(rs.getString(2));
			misReport.setCategory(rs.getString(3));
			misReport.setFilename(rs.getString(4));
			misReport.setUserid(rs.getString(5));
			misReport.setDatetime(DateTimeUtils.getCommencingDate1(rs.getString(6).split(" ")[0]));
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return misReport;
}
public int deleteVideoTFileUploaded(String id, String userId, String todate) {
	int res =0;
	try {
		String sql ="update videotutorial_filedata set deleted=1,deleted_userid='"+userId+"',deleted_time='"+todate+"' where id='"+id+"'";
		PreparedStatement  preparedStatement = connection.prepareStatement(sql);
		res = preparedStatement.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}

}
