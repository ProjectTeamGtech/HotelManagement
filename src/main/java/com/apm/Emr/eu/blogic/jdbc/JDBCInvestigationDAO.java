package com.apm.Emr.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.mail.Session;

import com.apm.Accounts.eu.bi.AccountsDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAccountsDAO;
import com.apm.Appointment.eu.entity.Appointment;
import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.AssesmentForms.eu.bi.AssessmentFormDAO;
import com.apm.AssesmentForms.eu.blogic.jdbc.JDBCAssessmentFormDAO;
import com.apm.AssesmentForms.web.action.Template;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.Emr.eu.bi.InvestigationDAO;
import com.apm.Emr.eu.entity.Cbc;
import com.apm.Emr.eu.entity.Emr;
import com.apm.Emr.eu.entity.Investigation;
import com.apm.Emr.eu.entity.InvstTemplate;
import com.apm.Inventory.eu.entity.Product;
import com.apm.Ipd.eu.bi.BedDao;
import com.apm.Ipd.eu.bi.IpdDAO;
import com.apm.Ipd.eu.blogic.jdbc.JDBCBedDao;
import com.apm.Ipd.eu.blogic.jdbc.JDBCIpdDAO;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Log.eu.blogic.jdbc.JDBCAccountLogDAO;
import com.apm.Master.eu.bi.InvestigationMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCInvestigationMasterDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.ThirdParties.eu.bi.ThirdPartyDAO;
import com.apm.ThirdParties.eu.blogic.jdbc.JDBCThirdPartyDAO;
import com.apm.ThirdParties.eu.entity.ThirdParty;
import com.apm.common.eu.blogic.jdbc.JDBCBaseDAO;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.main.common.constants.Constants;

import oracle.jdbc.util.Login;

public class JDBCInvestigationDAO extends JDBCBaseDAO implements
		InvestigationDAO {

	public JDBCInvestigationDAO(Connection connection) {
		this.connection = connection;
	}

	public ArrayList<Master> getGroupList(String selectedid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Master> list = new ArrayList<Master>();
		String sql = "SELECT id,name FROM apm_investigation_group where invtypeid = "
				+ selectedid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				list.add(master);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Master> getNameList(String selectedid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Master> list = new ArrayList<Master>();
		String sql = "SELECT id,name FROM apm_investigation_name where invgrpid  = "
				+ selectedid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				list.add(master);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Master> geSpecimenList(String selectedid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Master> list = new ArrayList<Master>();
		String sql = "SELECT id,name FROM apm_investigation_specimen where invnameid  = "
				+ selectedid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				list.add(master);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Master> geReportList(String selectedid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Master> list = new ArrayList<Master>();
		String sql = "SELECT id,name FROM apm_invtigation_report_type where specimenid  = "
				+ selectedid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				list.add(master);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Master> getUnitList(String selectedid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Master> list = new ArrayList<Master>();
		String sql = "SELECT id,name FROM apm_invstigation_units where reportid  = "
				+ selectedid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				list.add(master);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int saveParentInvestigation(Investigation investigation,
			String ukCurrentDataTime, String sessionadmissionid,
			String invsttypeid,int invrequest,LoginInfo loginInfo) {
		PreparedStatement preparedStatement = null;
		UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
		InvestigationMasterDAO investigationMasterDAO= new JDBCInvestigationMasterDAO(connection);
		int result = 0;
		int outoid = 0;
		
		String ipdid = "0";
		String opdid = "0";
		String date="";
		//For Bams
		if(loginInfo.isBams1()) {
			date=ukCurrentDataTime;
		}else {
			  DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		      Calendar cal = Calendar.getInstance();
		      date = dateFormat.format(cal.getTime());  
			
		}
		
		  /*DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	      Calendar cal = Calendar.getInstance();
	      String date = dateFormat.format(cal.getTime());*/  
		
		Master  master= investigationMasterDAO.getInvestigationType(invsttypeid);
		
		//set department to charges
		AssessmentFormDAO assessmentFormDAO = new JDBCAssessmentFormDAO(connection);
		String practionerId = "0";
		String condition = "0";
		
		String clientId = investigation.getClientId();
		//get ipd details
		int bedid = assessmentFormDAO.getIpdBedno(clientId);
		if(bedid!=0){
			ipdid = assessmentFormDAO.getAdmissionid(clientId);
			
		}else{
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			NotAvailableSlot notAvailableSlot = clientDAO.getLastAppointmentdetails(clientId);
			if(notAvailableSlot.getDiaryUserId()!=0){
				practionerId = Integer.toString(notAvailableSlot.getDiaryUserId());
				opdid = Integer.toString(notAvailableSlot.getId());
			}
			
			
			
		}
		
		
		
		String sql = "insert into apm_client_parent_investigation(clientid,practitionerid,conditionid,advoice,english,regional,hindi,prepay,postpay,other,lastmodified,reporttype,ipdid,jobtitle,invsttypeid,department,userid,updatedby,invreq,opdid,pkg,created_userid,created_datetime) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try {
			
			if(investigation.getPrectionerid()!=null){
				
				if(!investigation.getPrectionerid().equals("")){
					 
					UserProfile userProfile=userProfileDAO.getUserprofileDetails(Integer.parseInt(investigation.getPrectionerid()));
					investigation.setConditionid(userProfile.getDiciplineName());
				}
			}
			
			
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, investigation.getClientId());
			preparedStatement.setString(2, investigation.getPrectionerid());
			preparedStatement.setString(3, investigation.getConditionid());
			preparedStatement.setString(4, investigation.getAdvoice());
			preparedStatement.setString(5, investigation.getEnglish());
			preparedStatement.setString(6, investigation.getRegional());
			preparedStatement.setString(7, investigation.getHindi());
			preparedStatement.setString(8, investigation.getPrepay());
			preparedStatement.setString(9, investigation.getPostpay());
			preparedStatement.setString(10, investigation.getOtherpay());
			preparedStatement.setString(11, ukCurrentDataTime);
			preparedStatement.setString(12, master.getReport_type());
			preparedStatement.setString(13, ipdid);
			preparedStatement.setString(14, investigation.getJobtitle());
			preparedStatement.setString(15, invsttypeid);

			String department = getInvtypeDepartmentId(invsttypeid);
			preparedStatement.setString(16, department);
			preparedStatement.setString(17, investigation.getUserid());
			preparedStatement.setString(18, investigation.getUserid());
			preparedStatement.setInt(19, invrequest);
			preparedStatement.setString(20, opdid);
			preparedStatement.setString(21, investigation.getInvpkg());
			preparedStatement.setString(22, investigation.getUseridnew());
			preparedStatement.setString(23, date);
			result = preparedStatement.executeUpdate();

			if (result == 1) {
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if (resultSet.next()) {
					outoid = resultSet.getInt(1);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return outoid;
	}

	private String getInvtypeDepartmentId(String invsttypeid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT department FROM apm_investigation_type where id = "+invsttypeid+"";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getString(1);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public String getConditionDepartment(String conditionid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select  department from apm_condition where id = "
				+ conditionid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getString(1);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public int saveInvestigation(Investigation investigation, String date,
			int saveparent, String clientId, String prectionerid,
			String conditionid) {
		PreparedStatement preparedStatement = null;
		UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
		int result = 0;
		String sql = "insert into apm_client_investigation(clientid,practitionerid,conditionid,invstcode,"
				+ "invsttype,invstgroup,invstname,specimen,invstUnit,invstreporttype,parentid,"
				+ "lastmodified,normvalue,invnameid,critical_value,obsvalue,value_cf) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try {
			
			if(prectionerid!=null){
				
				if(!prectionerid.equals("")){
					 
					UserProfile userProfile=userProfileDAO.getUserprofileDetails(Integer.parseInt(prectionerid));
					conditionid=userProfile.getDiciplineName();
				}
			}

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, clientId);
			preparedStatement.setString(2, prectionerid);
			preparedStatement.setString(3, conditionid);
			preparedStatement.setString(4, investigation.getInvstcode());
			preparedStatement.setString(5, investigation.getInvsttype());
			preparedStatement.setString(6, investigation.getInvstgroup());
			preparedStatement.setString(7, investigation.getInvstname());
			preparedStatement.setString(8, investigation.getSpecimen());
			preparedStatement.setString(9, investigation.getInvstUnit());
			preparedStatement.setString(10, investigation.getInvstreporttype());
			preparedStatement.setInt(11, saveparent);
			preparedStatement.setString(12, date);
			preparedStatement.setString(13, investigation.getNormvalue());
		    preparedStatement.setInt(14, investigation.getId());
		    preparedStatement.setString(15, investigation.getCritical_value());
		    preparedStatement.setString(16, "");
		    preparedStatement.setString(17, investigation.getValue_cf());
			result = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Investigation> getParentPriscList(String clientId,
			String prectionerid, String conditionid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Investigation> list = new ArrayList<Investigation>();
		String sql = "SELECT id,lastmodified,reporttype FROM apm_client_parent_investigation where clientid ='"+clientId+"' and practitionerid='"+prectionerid+"' order by id desc ";
				

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Investigation investigation = new Investigation();
				investigation.setId(rs.getInt(1));

				if (rs.getString(2) != null) {
					investigation.setDate(DateTimeUtils.getDBDate(rs
							.getString(2)));
				} else {
					investigation.setDate("");
				}
				Investigation investigation2 = getSelectedInvestigationClient(rs
						.getString(1));
				investigation.setInvsttype(investigation2.getInvsttype());
				investigation.setInvstreporttype(rs.getString(3));
				
				//check if pacs  exist
				int pacsexist = checkifPacsExist(rs.getInt(1));
				investigation.setPacs(pacsexist);
				

				list.add(investigation);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private int checkifPacsExist(int invstid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select * from dicom_list where invstid = "+invstid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = 1;
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}

	public Investigation getEditInvestigation(String selectedid) {
		PreparedStatement preparedStatement = null;
		Investigation investigation = new Investigation();
		String sql = "SELECT advoice,english,regional,hindi,prepay,postpay,other,clientid,practitionerid,lastmodified,conditionid,updatedby,dateofupdated,dateofcompleted,ipdid,userid,compdate,remark,reporttype,invsttypeid,status,approved_userid,invreq,pkg,invsttypeid,created_userid,charge_id,jobtitle,signuser   FROM apm_client_parent_investigation where id in ("+selectedid+")";
				

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {

				investigation.setAdvoice(rs.getString(1));
				investigation.setEnglish(rs.getString(2));
				investigation.setRegional(rs.getString(3));
				investigation.setHindi(rs.getString(4));
				investigation.setPrepay(rs.getString(5));
				investigation.setPostpay(rs.getString(6));
				investigation.setOtherpay(rs.getString(7));
				investigation.setClientId(rs.getString(8));
				investigation.setPrectionerid(rs.getString(9));

				if (rs.getString(10) != null) {
					investigation.setDate(DateTimeUtils.getDBDate(rs
							.getString(10)));
				} else {
					investigation.setDate("");

				}

				investigation.setConditionid(rs.getString(11));
				investigation.setUpdatedby(rs.getString(12));
				if(rs.getString(13)!=null){
					String dd=DateTimeUtils.getDBDate(rs.getString(13));
					investigation.setUpdate_date(dd);
				}else {
					String dd=DateTimeUtils.getDBDate(rs.getString(10));
					investigation.setUpdate_date(dd);
				}
				if(rs.getString(14)!=null){
					String dd=DateTimeUtils.getDBDate(rs.getString(14));
					investigation.setComplete_date(dd);
				}else {
					String dd=DateTimeUtils.getDBDate(rs.getString(10));
					investigation.setComplete_date(dd);
				}
				investigation.setIpdid(rs.getString(15));
				investigation.setUserid(rs.getString(16));
				
				if(rs.getString(17)!=null){
					String dd= DateTimeUtils.getDBDate(rs.getString(17));
					investigation.setCollect_date(dd);
				} else {
					String dd=DateTimeUtils.getDBDate(rs.getString(10));
					investigation.setCollect_date(dd);
				}
				investigation.setRemark(rs.getString(18));
				if(rs.getString(18)==null){
					investigation.setRemark("");
				}
				investigation.setReporttype(rs.getString(19));
				investigation.setInvsttypeid(rs.getString(20));
				investigation.setStatus(rs.getString(21));
				investigation.setApproved_userid(rs.getString(22));
				investigation.setInvreq(String.valueOf(rs.getInt(23)));
				investigation.setPackagename(String.valueOf(rs.getInt(24)));
				investigation.setInvsttype(rs.getString(25));
				investigation.setSectionid(getInvewstsectionID(rs.getString(25)));
				investigation.setRequested_userid(rs.getString(26));
				investigation.setCharge(""+rs.getInt("charge_id"));
				investigation.setJobtitle(DateTimeUtils.isNull(rs.getString("jobtitle")));
				investigation.setSignUser(DateTimeUtils.isNull(rs.getString("signuser")));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return investigation;
	}

	public ArrayList<Investigation> getPrintSelectedInvestigationData(
			String selectedid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Investigation> list = new ArrayList<Investigation>();
		String sql = "SELECT invstcode,invsttype,invstgroup,invstname,specimen,invstUnit,invstreporttype,normvalue,obsvalue,id,findings,biorefrange,methods,finding1,finding2,finding3,finding4 FROM apm_client_investigation where parentid = "
				+ selectedid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			int i = 1;
			while (rs.next()) {

				Investigation investigation = new Investigation();

				investigation.setInvstcode(rs.getString(1));
				investigation.setInvsttype(rs.getString(2));
				investigation.setInvstgroup(rs.getString(3));
				investigation.setInvstname(rs.getString(4));
				investigation.setSpecimen(rs.getString(5));
				investigation.setInvstUnit(rs.getString(6));
				investigation.setInvstreporttype(rs.getString(7));
				investigation.setNormvalue(rs.getString(8));
				
				
				/*if(obsval%1.0==0.0){
					investigation.setObsvalue(String.valueOf(rs.getInt(9)));
				}else{
					investigation.setObsvalue(String.valueOf(obsval));
				}*/
				investigation.setObsvalue(rs.getString(9));
				
				if(investigation.getObsvalue()!=null){
					if(investigation.getObsvalue().equals("")){
						investigation.setObsvalue("0");
					}else{
						
						if(!investigation.getObsvalue().matches(".*[a-zA-Z]+.*")){
							if(investigation.getObsvalue().contains(".")){
							String temp[]=investigation.getObsvalue().split("\\.");
							int x=Integer.parseInt(temp[1]);
							if(x==0){
								investigation.setObsvalue(temp[0]);
								
							}
						}
						}
					}
				} else {
					investigation.setObsvalue("0");
				}
				
				investigation.setKunalobsval(investigation.getObsvalue());
				investigation.setObsvalue(rs.getString(9));
				if(investigation.getObsvalue()==null){
					investigation.setObsvalue("0");
				}else if (investigation.getObsvalue().equals("")) {
					investigation.setObsvalue("0");
				}
				
				if(investigation.getNormvalue()!=null){
					
					String str[] = investigation.getNormvalue().split("-");
					if(str.length==2&&investigation.getInvstreporttype().equals("Numerical")){
						
						double min = Double.parseDouble(str[0]);
						double max= Double.parseDouble(str[1]);
						String chkr=investigation.getObsvalue().replaceAll("[^\\d.]", "");
						if(chkr!=null){
							if(!chkr.equals("")){
								String obstr = investigation.getObsvalue().replaceAll("[^\\d.]", "");
								double obs= Double.parseDouble(obstr);
								if(obs<min || obs>max){
									investigation.setBold(1);
								} 
							}
							
						}
						
					  
					}
				}
				
				

				if (investigation.getInvsttype().equals("COMPLETE BLOOD COUNT")
						|| investigation.getInvsttype().equals(
								"COMPLET BLOOD COUNT")) {
					String temp[] = investigation.getObsvalue().split(" ");
					if (temp.length > 1) {
						if (i != 14) {
							List<String> output = DateTimeUtils
									.getCbcHLValue(investigation.getObsvalue());
							investigation
									.setFlag(output.get(output.size() - 1));
							investigation.setObsvalue(temp[0]);
						}

					}

				}

				investigation.setId(rs.getInt(10));
				if (rs.getString(11) != null) {
					investigation.setFindings(rs.getString(11));
				} else {
					investigation.setFindings("");
				}

				investigation.setBiorefrange(rs.getString(12));
				investigation.setMethods(rs.getString(13));
				investigation.setFinding1(rs.getString(14));
				investigation.setFinding2(rs.getString(15));
				investigation.setFinding3(rs.getString(16));
				investigation.setFinding4(rs.getString(17));

				list.add(investigation);
				i++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Investigation> getSelectedInvestigationData(
			String selectedid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Investigation> list = new ArrayList<Investigation>();
		String sql = "SELECT invstcode,invsttype,invstgroup,invstname,specimen,invstUnit,invstreporttype,normvalue,obsvalue,id,findings,biorefrange,methods,critical_value,clientid,finding1,finding2,finding3,finding4 FROM apm_client_investigation where parentid = "
				+ selectedid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				Investigation investigation = new Investigation();

				investigation.setInvstcode(rs.getString(1));
				investigation.setInvsttype(rs.getString(2));
				investigation.setInvstgroup(rs.getString(3));
				investigation.setInvstname(rs.getString(4));
				investigation.setSpecimen(rs.getString(5));
				investigation.setInvstUnit(rs.getString(6));
				investigation.setInvstreporttype(rs.getString(7));
				investigation.setNormvalue(rs.getString(8));
				investigation.setObsvalue(rs.getString(9));
				investigation.setId(rs.getInt(10));
				investigation.setFindings(rs.getString(11));
				if(investigation.getFindings()==null){
					investigation.setFindings("");
				}
				investigation.setBiorefrange(rs.getString(12));
				investigation.setMethods(rs.getString(13));
				if(investigation.getMethods()==null){
					investigation.setMethods("");
				}
				investigation.setCritical_value(rs.getString(14));
				investigation.setClientId(rs.getString(15));
				investigation.setFinding1(rs.getString(16));
				investigation.setFinding2(rs.getString(17));
				investigation.setFinding3(rs.getString(18));
				investigation.setFinding4(rs.getString(19));
				list.add(investigation);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int deleteInvestigationData(String selectedid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete FROM apm_client_investigation where parentid = "
				+ selectedid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deleteParentData(String selectedid) {

		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete FROM apm_client_parent_investigation where id = "
				+ selectedid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Investigation getInveStigationDetails(
			String selectedinvestigationnameid) {
		PreparedStatement preparedStatement = null;
		Investigation investigation = new Investigation();
		String sql = "SELECT specimen,reporttype,unit,normvalue FROM apm_investigation_name  where id = "
				+ selectedinvestigationnameid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				investigation.setSpecimen(rs.getString(1));
				investigation.setInvstreporttype(rs.getString(2));
				investigation.setInvstUnit(rs.getString(3));
				investigation.setNormvalue(rs.getString(4));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return investigation;
	}

	private String newInvestigationChargeCheckDate(){
		String date="";
		try {
			PreparedStatement ps=connection.prepareStatement(" select invst_new_chrge_chk_sys from apm_user where id=1 ");
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				date=DateTimeUtils.isNull(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public ArrayList<Investigation> getViewInvestigationList(
			Pagination pagination, String searchText, String jobtitle,
			String invsttype, String fromdate, String todate,LoginInfo loginInfo, String filter_status, String filter_ward, String invstsecid, String outsource,String isdeleted) {
 		PreparedStatement preparedStatement = null;
 		
 		
 		UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
 		UserProfile userProfile = userProfileDAO.getUserprofileDetails(loginInfo.getId());
 		
 		String labname = "";
 		if(loginInfo.getUserType()!=2){
 			labname = getLabnameStr(userProfile.getLabname());
 		}
 		 

		if (todate != null) {
			todate = todate + " 23:59:59";
		}

		ArrayList<Investigation> list = new ArrayList<Investigation>();
		StringBuffer sql = new StringBuffer();

		/*String userid=loginInfo.getUserId();
		if (!searchText.equals("") && jobtitle != null && invsttype != null
				&& fromdate != null && todate != null) {

			sql.append("SELECT clientid,practitionerid,conditionid,advoice,english,regional,hindi,prepay,postpay,other, ");
			sql.append("apm_client_parent_investigation.lastmodified,reporttype,apm_client_parent_investigation.id,ipdid,upstatus, ");
			sql.append("apm_client_parent_investigation.status,charginvoiceid,dateofupdated,dateofcompleted,compstatus,compdate,deleted FROM apm_client_parent_investigation inner join apm_patient ");
			sql.append("on apm_patient.id = apm_client_parent_investigation.clientid where ");
            sql.append("apm_client_parent_investigation.lastmodified between '"+fromdate+"' and '"+todate+"' ");
            sql.append("and jobtitle='"+jobtitle+"' and invsttypeid="+invsttype+" ");
            
            if(loginInfo.getUserType()!=2){
            	 
            	sql.append("and userid='"+userid+"' ");
            }
            sql.append("and (firstname like('%"+searchText+"%') or ");
            sql.append("surname like('%"+searchText+"%')) ");
            //sql.append("order by id desc ");

		} else if (searchText.equals("") && invsttype != null
				&& jobtitle != null && fromdate != null && todate != null) {

			sql.append("SELECT clientid,practitionerid,conditionid,advoice,english,regional,hindi,prepay,postpay,other,lastmodified,reporttype,id,ipdid,upstatus,apm_client_parent_investigation.status,charginvoiceid,dateofupdated,dateofcompleted,compstatus,compdate,deleted FROM apm_client_parent_investigation where jobtitle='"+jobtitle+"' ");
			sql.append("and lastmodified between '"+ fromdate+ "' and '"+ todate+"' ");				
			 if(loginInfo.getUserType()!=2){
            	 
	            	sql.append("and userid='"+userid+"' ");
	            }
			sql.append("and invsttypeid="+invsttype+" ");	
		} else if (searchText.equals("") && invsttype != null
				&& jobtitle != null) {

			sql.append("SELECT clientid,practitionerid,conditionid,advoice,english,regional,hindi,prepay,postpay,other,lastmodified,reporttype,id,ipdid,upstatus,apm_client_parent_investigation.status,charginvoiceid,dateofupdated,dateofcompleted,compstatus,compdate,deleted FROM apm_client_parent_investigation where ");
			sql.append("and jobtitle='"+jobtitle+"' ");

			if(loginInfo.getUserType()!=2){
            	sql.append("and userid='"+userid+"' ");
            }
			
			sql.append("and invsttypeid="+invsttype+" ");
		} else if (searchText.equals("") && fromdate!=null && todate!=null) {
			sql.append("SELECT clientid,practitionerid,conditionid,advoice,english,regional,hindi,prepay,postpay,other,lastmodified,reporttype,id,ipdid,upstatus,status,charginvoiceid,dateofupdated,dateofcompleted,compstatus,compdate,deleted FROM apm_client_parent_investigation where ");
			if(loginInfo.getUserType()!=2){
            	//sql.append("userid='"+userid+"' ");
            	sql.append(" lastmodified between '"+fromdate+"' and '"+todate+"' and jobtitle in("+labname+")  ");
            } else {
            	sql.append("lastmodified between '"+fromdate+"' and '"+todate+"' ");
            }
			if(invsttype != null){
				sql.append("and invsttypeid="+invsttype+" ");
			}
		} else {
			sql.append("SELECT clientid,practitionerid,conditionid,advoice,english,regional,hindi,prepay,postpay,other, ");
			sql.append("apm_client_parent_investigation.lastmodified,reporttype,apm_client_parent_investigation.id,ipdid,upstatus,apm_client_parent_investigation.status,charginvoiceid,dateofupdated,dateofcompleted,compstatus,compdate,deleted ");
			sql.append("FROM apm_client_parent_investigation inner join apm_patient on ");
			sql.append("apm_patient.id = apm_client_parent_investigation.clientid ");
			sql.append("where apm_client_parent_investigation.lastmodified between '"+fromdate+"' and '"+todate+"' ");
			
			if(loginInfo.getUserType()!=2){
            	sql.append("and userid='"+userid+"' ");
            }
			
			sql.append("and (fullname like('%"+searchText+"%') or  abrivationid like('%"+searchText+"%') or   invreq = '"+searchText+"')  ");
		}*/
		
		sql.append("SELECT clientid,practitionerid,conditionid,advoice,english,regional,hindi,prepay,postpay,other, ");
		sql.append("apm_client_parent_investigation.lastmodified,apm_client_parent_investigation.reporttype,apm_client_parent_investigation.id, ");
		sql.append("ipdid,upstatus,apm_client_parent_investigation.status,charginvoiceid,dateofupdated,dateofcompleted,compstatus,compdate,deleted,outsourceid,pkg,apm_investigation_type.name ,payment_status_new,apm_client_parent_investigation.userid,apm_client_parent_investigation.charge_id,outsrc_expect_date,address ");
		sql.append("FROM apm_client_parent_investigation ");
		sql.append("inner join apm_patient on apm_patient.id = apm_client_parent_investigation.clientid ");
		sql.append("inner join apm_investigation_type on apm_investigation_type.id = apm_client_parent_investigation.invsttypeid ");
		//sql.append("inner join apm_investigation_section on apm_investigation_section.id = apm_investigation_type.sectionid ");
		//sql.append("where apm_client_parent_investigation.lastmodified between '"+ fromdate+ "' and '"+ todate+"' ");
		
		if(jobtitle==null) {
			if(loginInfo.getJobTitle().equals("Radiologist")) {                //loginInfo.getJobTitle().equals("PATHOLOGIST") remove these as requirement 
				sql.append("where apm_client_parent_investigation.lastmodified between '"+ fromdate+ "' and '"+ todate+"' and jobtitle='"+loginInfo.getJobTitle()+"' ");

			}else {
				sql.append("where apm_client_parent_investigation.lastmodified between '"+ fromdate+ "' and '"+ todate+"' ");

			}
		}else {
			sql.append("where apm_client_parent_investigation.lastmodified between '"+ fromdate+ "' and '"+ todate+"' and jobtitle='"+jobtitle+"'");

		}

		
		
		if(searchText!=null){
			sql.append("and (fullname like('%"+searchText+"%') or  abrivationid like('%"+searchText+"%') or   invreq = '"+searchText+"' or clientid= '"+searchText+"' )  ");
		}
		
		/*
		 * if(jobtitle!=null || loginInfo.getJobTitle()!=null &&
		 * !loginInfo.getJobTitle().equals("Admin")){
		 * sql.append("and (jobtitle='"+jobtitle+"' or jobtitle='"+loginInfo.getJobTitle
		 * ()+"')"); }
		 */
		
		if(invsttype != null){
			sql.append("and invsttypeid='"+invsttype+"' ");
		}
		
		if(invstsecid!=null){
			sql.append("and sectionid='"+invstsecid+"' ");
		}
	
		if(!filter_ward.equals("0")){
			if(filter_ward.equals("IPD")){
				sql.append("and ipdid!= 0 and ipdid is not null ");
			}else if(filter_ward.equals("OPD")){
				sql.append("and (ipdid=0 or ipdid is null) ");
			}else if(filter_ward.equals("URGENT")){
				sql.append("and ipdid!= 0 and ipdid is not null ");
			}
		}
		
		if(!filter_status.equals("0")){
			if(filter_status.equals("1")){
				sql.append("and upstatus=0 and compstatus=0 ");
			}else if(filter_status.equals("2")){
				sql.append("and upstatus=0 and compstatus=1 ");
			}else if(filter_status.equals("3")){
				sql.append("and upstatus=1 and compstatus=1 and apm_client_parent_investigation.status=0 ");
			}else if(filter_status.equals("4")){
				sql.append("and apm_client_parent_investigation.status=1 ");
			}
		}
		
		if(jobtitle!=null){
			if(!jobtitle.equals("0")){
				String dpartment = getInvestigationTypeDepartment(jobtitle);
				sql.append(" and apm_client_parent_investigation.department="+dpartment+" ");
			}
		}
		if(outsource!=null){
			if(!outsource.equals("0")){
				if(outsource.equals("10000")){
					sql.append(" and outsourceid!='0' ");
				}else{
					sql.append(" and outsourceid="+outsource+" ");
				}
				
			}
		}
		if(loginInfo.isJobtitlewise_investigation()){
			if(loginInfo.getJobTitle().equals("Pathlab")||loginInfo.getJobTitle().equals("PATHLAB INCHARGE")){
				sql.append(" and  apm_client_parent_investigation.department in(5,19) ");
			}else if(loginInfo.getJobTitle().equals("Radiologist")){
				sql.append(" and  apm_client_parent_investigation.department in (7,16,17,18,20) ");
			}
		}
		
		if(!isdeleted.equals("")){
			sql.append(" and  apm_client_parent_investigation.deleted='"+isdeleted+"' ");
		}
		if(loginInfo.isInvest_order()){
			sql.append(" order by compstatus,upstatus asc ,deleted,apm_client_parent_investigation.id desc ");	
		}else{
			sql.append("  order by apm_client_parent_investigation.id desc ");
		}
		
		// String sql = "SELECT
		// clientid,practitionerid,conditionid,advoice,english,regional,hindi,prepay,postpay,other,lastmodified,reporttype,id,ipdid,upstatus
		// FROM apm_client_parent_investigation order by id desc ";
		String sql1 = pagination.getSQLQuery(sql.toString());
		SimpleDateFormat dateformat  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		Date updateDate=null;
		try{
			String dbdate=newInvestigationChargeCheckDate();
			if(dbdate.equals("")){
				dbdate="2024-03-10 00:00:00";
			}
			
			 updateDate = dateformat.parse(dbdate); 
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		try {
			preparedStatement = connection.prepareStatement(sql1);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Investigation investigation = new Investigation();
				investigation.setClientId(rs.getString(1));
				investigation.setPrectionerid(rs.getString(2));
				investigation.setConditionid(rs.getString(3));
				investigation.setAdvoice(rs.getString(4));
				investigation.setEnglish(rs.getString(5));
				investigation.setRegional(rs.getString(6));
				investigation.setHindi(rs.getString(7));
				investigation.setPrepay(rs.getString(8));
				investigation.setPostpay(rs.getString(9));
				investigation.setOtherpay(rs.getString(10));

				if (rs.getString(11) != null) {
					investigation.setDate(DateTimeUtils.getDBDate(rs
							.getString(11)));
				} else {
					investigation.setDate("");
				}
				
				//when date after
				boolean dateflag=false;
				String test=rs.getString(11);
				Date lastmodDate = dateformat.parse(test); 
				if(updateDate.before(lastmodDate)){
					dateflag=true;
				}
				
				
				
				
				
				investigation.setInvstreporttype(rs.getString(12));
				investigation.setId(rs.getInt(13));
				String invreq1 = getInveReqId(investigation.getId());
				 int chargeIdsame=rs.getInt(28);
				
				if(dateflag){
					if(chargeIdsame>0){
						investigation.setCheckChargeRaised(1);
						
						investigation.setCheckInvoiceCreated(checkInvoiceCreated(apm_InvoiceId(""+chargeIdsame)));
					}
					
				}else{
					int checkChargeRaised =0, chargeid=0 ;
					if(rs.getInt(24)!=0){
						if(rs.getInt(26)==0){
							/*new way to check charges raised*/
							checkChargeRaised=investigationInvByreq(invreq1,rs.getInt(24));
						}else{
							checkChargeRaised=1;
						}
						
						chargeid=investigationInvByreqInvid(invreq1,rs.getInt(24));
					}else{
						if(rs.getInt(26)==0){
							checkChargeRaised= checkChargeRaised(investigation.getId());
						}else{
							checkChargeRaised=1;
						}
						
						chargeid=getChargedInvoiceid(investigation.getId());
					}
					
					
					investigation.setCheckChargeRaised(checkChargeRaised);

					 
					int checkInvoiceCreated = checkInvoiceCreated(chargeid);
					investigation.setCheckInvoiceCreated(checkInvoiceCreated);
				}
				
				String expectedDate=DateTimeUtils.isNull(rs.getString(29));
				if(!expectedDate.equals("")){
					expectedDate=DateTimeUtils.getCommencingDate1(expectedDate);
				}
				investigation.setOutsrcExpDate(expectedDate);
				/*String investType = getInvestType(investigation.getId());*/
				investigation.setInvsttype(rs.getString(25));
				
				String sectionid = getInvstigationSectionId(investigation.getId());
				if(sectionid==null){
					sectionid = "0";
				}
				String invreq = getInveReqId(investigation.getId());
				investigation.setInvreq(invreq);
				
				investigation.setSectionid(sectionid);

				String ipdid = rs.getString(14);
				BedDao bedDao = new JDBCBedDao(connection);
				IpdDAO ipdDAO = new JDBCIpdDAO(connection);
				Bed bed = bedDao.getEditIpdData(ipdid);

				if (ipdid == null || ipdid.equals("0")) {
					investigation.setIpdid("0");
				} else {
					investigation.setIpdid(ipdid);
					investigation.setDaycare(bedDao.isDayCare(ipdid));
				}

				investigation.setUpstatus(rs.getString(15));
				investigation.setStatus(rs.getString(16));
				investigation.setCharginvoiceid(rs.getInt(17));
				investigation.setUpdate_date(rs.getString(18));
				investigation.setComplete_date(rs.getString(19));
				investigation.setCollectstatus(rs.getString(20));
				investigation.setCollect_date(rs.getString(21));
				investigation.setDeleted(rs.getString(22));
				investigation.setOutsourceid(rs.getString(23));
				investigation.setPackagename(rs.getString(24));
				if(investigation.getCollect_date()!=null){
					if(!investigation.getCollect_date().equals("")){
						String dd= DateTimeUtils.getDBDate(investigation.getCollect_date());
						investigation.setCollect_date(dd);
					}
				} else {
					investigation.setCollect_date("");
				}
				
				if(investigation.getUpdate_date()!=null){
					
					if(!investigation.getUpdate_date().equals("")){
						String dd= DateTimeUtils.getDBDate(investigation.getUpdate_date());
						investigation.setUpdate_date(dd);
					}
				}
				if(investigation.getComplete_date()!=null){
					
					if(!investigation.getComplete_date().equals("")){
						String dd= DateTimeUtils.getDBDate(investigation.getComplete_date());
						investigation.setComplete_date(dd);
					}
				}

				/*int invchargeid = getChargedInvoiceid(investigation.getId());

				if (invchargeid != 0) {

					int chargeinvoiceid = getchargeInvoiceidFromAssesment(invchargeid);
					if (chargeinvoiceid != 0) {

						boolean ischargepaid = isInvChargePaid(chargeinvoiceid);

						if (ischargepaid) {
							investigation.setUpstatus("2"); // charge paid

							int completed = rs.getInt(15);
							if (completed == 1) {

								investigation.setUpstatus("1"); // completed
							} else {

								investigation.setUpstatus("2"); // only charges
								// paid

							}

						} else {
							investigation.setUpstatus("0"); // only requested
						}

					} else {
						investigation.setUpstatus("0"); // only requested
					}

				} else {
					investigation.setUpstatus("0");
					// only requested
				}*/

				String wardname = ipdDAO.getIpdWardName(bed.getWardid());
				investigation.setWardname(wardname);
				String bedname = ipdDAO.getIpdBedName(bed.getBedid());
				investigation.setBedname(bedname);
				String urgentward = checkICUorCasulty(bed.getWardid());
				if (!urgentward.equals("")) {
					investigation.setUrgentward("1");
				}

				String clientid = investigation.getClientId();
				ClientDAO clientDAO = new JDBCClientDAO(connection);
				Client client = clientDAO.getClientDetails(clientid);

				String fullname = client.getTitle() + " "
						+ client.getFirstName() + " " + client.getLastName();
				String ageandgender = "";
				if (client.getDob() != null) {
					ageandgender = DateTimeUtils.getAge(client.getDob())
							+ " / " + client.getGender();
				}
				
				
				
				String selftp = client.getWhopay();
				
				if(selftp.equals(Constants.PAY_BY_THIRD_PARTY)){
					String tpid = client.getTypeName();
					
					
					if(!ipdDAO.checkifMainTp(tpid)){
						 
						String temptpid= ipdDAO.getFollowerTp(tpid); 
						if(temptpid!=null){
							
							if(!temptpid.equals("0")){
								 tpid=temptpid;  
							}
							
							ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
							ThirdParty thirdParty = thirdPartyDAO.getThirdPartyDetails(tpid);
							
							selftp = selftp + " {"+thirdParty.getCompanyName()+"} ";
							
						}
						
					}	
				}

				investigation.setSelftp(selftp);
				

				investigation.setFullname(fullname);
				investigation.setAgeandgender(ageandgender);
				investigation.setWhopay(client.getWhopay());
				investigation.setAbrivationid(client.getAbrivationid());
				investigation.setAddress(rs.getString(30));

				
				 userProfile = userProfileDAO
						.getUserprofileDetails(Integer.parseInt(investigation
								.getPrectionerid()));
				String practitionerName = userProfile.getInitial() + " "
						+ userProfile.getFirstname() + " "
						+ userProfile.getLastname();

				if (investigation.getPrectionerid().equals("0")) {
					practitionerName = clientDAO.getGPname(client.getGpid());
				}
				investigation.setPractitionerName(practitionerName);
				investigation.setPractitionerMob(userProfile.getMobile());
				investigation.setSpecialization(userProfile.getSpecialization());

				/*boolean isAttachment = checkAttachment(investigation.getId());*/
				boolean isAttachment=false;
				if (isAttachment) {
					investigation.setIsAttachment("1");
					/*
					 * ArrayList<Emr>docmntList =
					 * getUploadedeDocmntList(investigation.getId());
					 * investigation.setDocList(docmntList);
					 */
				} else {
					investigation.setIsAttachment("0");
				}
				if (rs.getString(4)!=null) {
					if (!rs.getString(4).equals("")) {
						investigation.setValidnote("1");
					}else{
						investigation.setValidnote("0");
					}
				}else{
					investigation.setValidnote("0");
				}
				investigation.setFromdate(fromdate); 
				investigation.setTodate(todate);
				
				ArrayList<Investigation> outSourceList = getOutSourceList(rs.getString(23));
				investigation.setOutSourceList(outSourceList);
				investigation.setOutsource(rs.getString(23));
				investigation.setUserid(userProfileDAO.getFullName(rs.getString(27)));
				Investigation invst=getInvestigationbalance(rs.getString(1));
				investigation.setBalance(invst.getBalance());
				if(filter_ward.equals("URGENT")){
					String name = checkICUorCasulty(bed.getWardid());
					if (!name.equals("")) {
						investigation.setUrgentward("1");
						list.add(investigation);
					}
				}else{
					list.add(investigation);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private Investigation getInvestigationbalance(String clientid) {
	      PreparedStatement pst=null;
	      Investigation invst=new Investigation();
	      try {
	    	  String sql="select debit,apm_charges_invoice.discamt,sum(payment) from apm_charges_invoice inner join apm_charges_payment on "
	    	  		+ "apm_charges_payment.chargeinvoiceid=apm_charges_invoice.id where apm_charges_invoice.clientid='"+clientid+"' and itype=3 "
	    	  		+ "group by chargeinvoiceid desc limit 0,1 ";
	    	  pst=connection.prepareStatement(sql);
	    	  ResultSet rs=pst.executeQuery();
	    	  while(rs.next()) {
	    		 invst.setDebit(rs.getDouble(1));
	    		 invst.setDiscount(rs.getDouble(2));
	    		 invst.setPayment(rs.getDouble(3));
	    		 double balance=invst.getDebit()-invst.getDiscount()-invst.getPayment();
	    		 invst.setBalance(""+balance);
	    	  }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return invst;
	}

	public ArrayList<Investigation> getOutSourceList(String outsourseid) {
		ArrayList<Investigation> arrayList = new ArrayList<Investigation>();
		try {
			String sql ="select id,name from apm_outsource";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Investigation investigation = new Investigation();
				investigation.setId(rs.getInt(1));
				investigation.setName(rs.getString(2));
				if(outsourseid!=null){
					if(Integer.parseInt(outsourseid)==rs.getInt(1)){
						investigation.setStatus("1");
					}else{
						investigation.setStatus("0");
					}
				}else{
					investigation.setStatus("0");
				}
				arrayList.add(investigation);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	
	private int apm_InvoiceId(String chargeid){
		int res=0;
		try {
			PreparedStatement ps= connection.prepareStatement("  select invoiceid from apm_invoice_assesments where id='"+chargeid+"'");
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				res=rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	private String getInvestigationTypeDepartment(String jobtitle) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT id FROM job_title where jobtitle = '"+jobtitle+"' ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	private String checkICUorCasulty(String wardid) {
		String name ="";
		try {
			String sql ="select id from apm_ipd_ward where id='"+wardid+"' and (wardname like('%ICU%') or wardname like('%CASUALTY%'))";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				name=rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}

	public String getInveReqId(int id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT invreq FROM apm_client_parent_investigation where id = "+id+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private String getLabnameStr(String str) {
		//String str = "0,pathlab,admin";
		String temp[] = str.split(",");
		String result = "";
		for(int i=0;i<temp.length;i++){
			result = result + "'" + temp[i] + "'" + ",";
		}
		
		result = result.substring(0, result.length()-1);
		//System.out.println(result);
		return result;
	}

	public String getInvstigationSectionId(int id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT sectionid FROM apm_client_parent_investigation " +
				" inner join apm_investigation_type on apm_investigation_type.id = apm_client_parent_investigation.invsttypeid " +
				" where apm_client_parent_investigation.id = "+id+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	private String getInvstSectionId(String name) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT sectionid FROM apm_investigation_type where name = '"+name+"' ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public int checkInvoiceCreated(int chargeid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT * FROM apm_charges_assesment where invoiceid = "
				+ chargeid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = 1;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getChargedInvoiceid(int id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT id FROM apm_invoice where ginvstid like('%"+id+"%') ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int checkChargeRaised(int id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		/* String sql = "SELECT * FROM apm_invoice where ginvstid like('%"+id+"%') "; */ // query commented on 12th jan 2024 
		   String sql = "SELECT * FROM apm_invoice where ginvstid='"+id+"' ";
		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = 1;
				int flag=setChargesRaisedStatus(""+id);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private ArrayList<Emr> getUploadedeDocmntList(int id) {
		PreparedStatement preparedStatement = null;
		ArrayList<Emr> list = new ArrayList<Emr>();

		String sql = "select apm_document.id,apm_document.history,apm_document."
				+ "filename,apm_document.practitionerid,apm_document.lastmodified,apm_document.doct_type "
				+ "from apm_document where lnvstid = " + id + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Emr emr = new Emr();
				emr.setId(rs.getInt(1));
				emr.setDescription(rs.getString(2));
				emr.setFileName(rs.getString(3));
				emr.setPractitionerId(rs.getInt(4));
				emr.setLastModified(DateTimeUtils.getIndianDateTimeFormat(rs
						.getString(5)));
				emr.setDoctType(rs.getString(6));

				// String userName = getUserName(rs.getInt(4));
				// emr.setPractitionerName(userName);

				list.add(emr);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public String getInvestType(int id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT invsttype FROM apm_client_investigation where parentid = "
				+ id + " group by parentid ";

		try {

			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public boolean checkAttachment(int id) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "SELECT * FROM apm_document where lnvstid = " + id + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public int updateReportData(int id, String obsvalue,String finding1, String finding2, String finding3, String finding4) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_client_investigation set obsvalue=?,finding1=?,finding2=?,finding3=?,finding4=? where id = "
				+ id + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, obsvalue);
			preparedStatement.setString(2, finding1);
			preparedStatement.setString(3, finding2);
			preparedStatement.setString(4, finding3);
			preparedStatement.setString(5, finding4);
			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateWriteupData(int id, String findings, String biorefrange,
			String methods) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_client_investigation  set findings=?,biorefrange=?,methods=? where id = "
				+ id + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, findings);
			preparedStatement.setString(2, biorefrange);
			preparedStatement.setString(3, methods);

			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Investigation> getInvestigationNameList(String ids) {
		PreparedStatement preparedStatement = null;
		ArrayList<Investigation> list = new ArrayList<Investigation>();
		String sql = "SELECT name,specimen,reporttype,unit,normvalue,id,critical_value FROM apm_investigation_name where id in("
				+ ids + ") ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Investigation investigation = new Investigation();
				investigation.setInvstname(rs.getString(1));
				investigation.setSpecimen(rs.getString(2));
				investigation.setInvstreporttype(rs.getString(3));
				investigation.setInvstUnit(rs.getString(4));
				investigation.setNormvalue(rs.getString(5));
				investigation.setId(rs.getInt(6));
				investigation.setCritical_value(rs.getString(7));
				list.add(investigation);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Master> getCbcIdList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Master> list = new ArrayList<Master>();

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -3);
		String date = dateFormat.format(cal.getTime());

		DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal1 = Calendar.getInstance();
		// cal.add(Calendar.DATE, -3);
		String todate = dateFormat1.format(cal1.getTime());

		todate = todate + " 23:59:59";

		String sql = "SELECT id,refid FROM apm_cbc_report where  lastmodified between '"
				+ date + "' and  '" + todate + "' order by id desc  ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setCbcid(rs.getString(2));

				list.add(master);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public Cbc getCbcReportData(String cbcid) {
		PreparedStatement preparedStatement = null;
		Cbc cbc = new Cbc();
		String sql = "SELECT  wbc, hashlym, perlym, hashmon, permon, hashgra, pergra, rbc, "
				+ "hgb, hcp, mcv, mch, mchc, rdw, tlt, mpv, testdate,pdw,pct FROM apm_cbc_report where id = "
				+ cbcid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				cbc.setWbc(rs.getString(1));
				cbc.setHashlym(rs.getString(2));
				cbc.setPerlym(rs.getString(3));
				cbc.setHashmon(rs.getString(4));
				cbc.setPermon(rs.getString(5));
				cbc.setHashgra(rs.getString(6));
				cbc.setPergra(rs.getString(7));
				cbc.setRbc(rs.getString(8));
				cbc.setHgb(rs.getString(9));
				cbc.setHcp(rs.getString(10));
				cbc.setMcv(rs.getString(11));
				cbc.setMch(rs.getString(12));
				cbc.setMchc(rs.getString(13));
				cbc.setRdw(rs.getString(14));
				cbc.setTlt(rs.getString(15));
				cbc.setMpv(rs.getString(6));
				cbc.setTestdate(rs.getString(17));
				cbc.setPdw(rs.getString(18));
				cbc.setPct(rs.getString(19));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return cbc;
	}

	public int insertGPDetails(String doctorname,String diciplinename) {

		int result = 0;

		try {

			String sql = "insert into apm_gp_details (tptypeid, tpid, gpname, note, fax, email, workno,dname) values (1,0,'"
					+ doctorname + "','','','','','"+diciplinename+"')";

			PreparedStatement ps = connection.prepareStatement(sql);

			result = ps.executeUpdate();

			if (result != 0) {

				ResultSet rs = ps.getGeneratedKeys();
				while (rs.next()) {
					result = rs.getInt(1);
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public int UpdateUpStatus(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int UpdateUpStatus(String editinvstparentid, int userid,String status) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		
		if(status==null){
			status="0";
		}
		
		String sql = "update apm_client_parent_investigation set upstatus=1,updatedby="
				+ userid + ", status='"+status+"' where id = " + editinvstparentid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public int getAllInvestigationCount(String searchText, String jobtitle,
			String invsttype, String fromdate, String todate, String filter_status, String filter_ward, LoginInfo loginInfo, String invstsecid,String outsource,String isdeleted) {

		
		int result = 0;

		if (todate != null) {
			todate = todate + " 23:59:59";
		}

		try {

			// String sql="select count(*) from
			// apm_client_parent_investigation";

			//StringBuffer sql = new StringBuffer();

			/*if (!searchText.equals("") && jobtitle != null && invsttype != null
					&& fromdate != null && todate != null) {

				sql.append("select count(*) from apm_client_parent_investigation inner join apm_patient on ");
				sql.append("apm_patient.id = apm_client_parent_investigation.clientid where ");
				sql.append("apm_client_parent_investigation.lastmodified between '"+fromdate+"' and '"+todate+"' ");
	            sql.append("and jobtitle='"+jobtitle+"' and invsttypeid="+invsttype+" ");
	            sql.append("and firstname like('%"+searchText+"') or ");
	            sql.append("surname like('%"+searchText+"') ");

			} else if (!searchText.equals("") && jobtitle != null
					&& fromdate != null && todate != null && invsttype == null) {

				sql.append("select count(*) from apm_client_parent_investigation inner join apm_patient on ");
				sql.append("apm_patient.id = apm_client_parent_investigation.clientid ");
				sql.append("where firstname like('%" + searchText
						+ "') or surname like('%" + searchText
						+ "') and jobtitle='" + jobtitle + "' ");
				sql.append("and apm_client_parent_investigation.lastmodified between '"
								+ fromdate + "' and '" + todate + "'");
			} else if (searchText.equals("") && invsttype != null
					&& jobtitle != null && fromdate != null && todate != null) {

				sql.append("select count(*) from apm_client_parent_investigation where jobtitle='"
								+ jobtitle
								+ "' and lastmodified between '"
								+ fromdate + "' and '" + todate + "' and invsttypeid='"+invsttype+"' ");

			} else if (searchText.equals("") && invsttype != null
					&& jobtitle != null) {

				sql.append("select count(*) from apm_client_parent_investigation where jobtitle='"
								+ jobtitle
								+ "' and invsttypeid='"
								+ invsttype
								+ "'");

			} else if (searchText.equals("") && fromdate!=null && todate!=null) {
				sql.append("select count(*) from apm_client_parent_investigation where jobtitle='"
								+ jobtitle + "' and lastmodified between '"+fromdate+"' and '"+todate+"' ");
			} else {
				sql.append("SELECT count(*) ");
				sql.append("FROM apm_client_parent_investigation inner join apm_patient on ");
				sql.append("apm_patient.id = apm_client_parent_investigation.clientid ");
				sql.append("where firstname like('%" + searchText
						+ "') or surname like('%" + searchText
						+ "') and jobtitle='" + jobtitle + "' ");

			}
			
			if(!filter_ward.equals("0")){
				if(filter_ward.equals("IPD")){
					sql.append("and ipdid!= 0 and ipdid is not null ");
				}else if(filter_ward.equals("OPD")){
					sql.append("and (ipdid=0 or ipdid is null) ");
				}
			}
			
			if(!filter_status.equals("0")){
				if(filter_status.equals("1")){
					sql.append("and upstatus=0 and compstatus=0 ");
				}else if(filter_status.equals("2")){
					sql.append("and upstatus=0 and compstatus=1 ");
				}else if(filter_status.equals("3")){
					sql.append("and upstatus=1 and compstatus=1 ");
				}else if(filter_status.equals("4")){
					sql.append("and status=1 ");
				}
			}*/
			
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
	 		UserProfile userProfile = userProfileDAO.getUserprofileDetails(loginInfo.getId());
	 		
	 		String labname = "";
	 		if(loginInfo.getUserType()!=2){
	 			labname = getLabnameStr(userProfile.getLabname());
	 		}
	 		 

			if (todate != null) {
				todate = todate + " 23:59:59";
			}

			ArrayList<Investigation> list = new ArrayList<Investigation>();
			StringBuffer sql = new StringBuffer();

			/*String userid=loginInfo.getUserId();
			if (!searchText.equals("") && jobtitle != null && invsttype != null
					&& fromdate != null && todate != null) {

				sql.append("SELECT id,ipdid ");
				sql.append("FROM apm_client_parent_investigation inner join apm_patient ");
				sql.append("on apm_patient.id = apm_client_parent_investigation.clientid where ");
	            sql.append("apm_client_parent_investigation.lastmodified between '"+fromdate+"' and '"+todate+"' ");
	            sql.append("and jobtitle='"+jobtitle+"' and invsttypeid="+invsttype+" ");
	            
	            if(loginInfo.getUserType()!=2){
	            	 
	            	sql.append("and userid='"+userid+"' ");
	            }
	            sql.append("and (firstname like('%"+searchText+"%') or ");
	            sql.append("surname like('%"+searchText+"%')) ");
	            //sql.append("order by id desc ");

			} else if (searchText.equals("") && invsttype != null
					&& jobtitle != null && fromdate != null && todate != null) {

				sql.append("SELECT id,ipdid FROM apm_client_parent_investigation where jobtitle='"+jobtitle+"' ");
				sql.append("and lastmodified between '"+ fromdate+ "' and '"+ todate+"' ");				
				 if(loginInfo.getUserType()!=2){
	            	 
		            	sql.append("and userid='"+userid+"' ");
		            }
				sql.append("and invsttypeid="+invsttype+" ");	
			} else if (searchText.equals("") && invsttype != null
					&& jobtitle != null) {

				sql.append("SELECT id,ipdid FROM apm_client_parent_investigation where ");
				sql.append("and jobtitle='"+jobtitle+"' ");

				if(loginInfo.getUserType()!=2){
	            	sql.append("and userid='"+userid+"' ");
	            }
				
				sql.append("and invsttypeid="+invsttype+" ");
			} else if (searchText.equals("") && fromdate!=null && todate!=null) {
				sql.append("SELECT id,ipdid FROM apm_client_parent_investigation where ");
				if(loginInfo.getUserType()!=2){
	            	//sql.append("userid='"+userid+"' ");
	            	sql.append(" lastmodified between '"+fromdate+"' and '"+todate+"' and jobtitle in("+labname+") order by id desc ");
	            } else {
	            	sql.append("lastmodified between '"+fromdate+"' and '"+todate+"' ");
	            }
				if(invsttype != null){
					sql.append("and invsttypeid="+invsttype+" ");
				}
			} else {
				sql.append("SELECT id,ipdid ");
				sql.append("FROM apm_client_parent_investigation inner join apm_patient on ");
				sql.append("apm_patient.id = apm_client_parent_investigation.clientid ");
				sql.append("where apm_client_parent_investigation.lastmodified between '"+fromdate+"' and '"+todate+"' ");
				
				if(loginInfo.getUserType()!=2){
	            	sql.append("and userid='"+userid+"' ");
	            }
				
				sql.append("and (fullname like('%"+searchText+"%') or  abrivationid like('%"+searchText+"%') or   invreq = '"+searchText+"')  ");
			}
			
			
			if(!filter_ward.equals("0")){
				if(filter_ward.equals("IPD")){
					sql.append("and ipdid!= 0 and ipdid is not null ");
				}else if(filter_ward.equals("OPD")){
					sql.append("and (ipdid=0 or ipdid is null) ");
				}else if(filter_ward.equals("URGENT")){
					sql.append("and ipdid!= 0 and ipdid is not null ");
				}
			}
			
			if(!filter_status.equals("0")){
				if(filter_status.equals("1")){
					sql.append("and upstatus=0 and compstatus=0 ");
				}else if(filter_status.equals("2")){
					sql.append("and upstatus=0 and compstatus=1 ");
				}else if(filter_status.equals("3")){
					sql.append("and upstatus=1 and compstatus=1 ");
				}else if(filter_status.equals("4")){
					sql.append("and status=1 ");
				}
			}
			
			if(jobtitle!=null){
				if(!jobtitle.equals("0")){
					String dpartment = getInvestigationTypeDepartment(jobtitle);
					sql.append(" and department="+dpartment+" ");
				}
			}
			
			sql.append("order by id desc ");*/
			
			sql.append("SELECT apm_client_parent_investigation.id ");
			sql.append("FROM apm_client_parent_investigation ");
			sql.append("inner join apm_patient on apm_patient.id = apm_client_parent_investigation.clientid ");
			sql.append("inner join apm_investigation_type on apm_investigation_type.id = apm_client_parent_investigation.invsttypeid ");
			//sql.append("inner join apm_investigation_section on apm_investigation_section.id = apm_investigation_type.sectionid ");
			sql.append("where apm_client_parent_investigation.lastmodified between '"+ fromdate+ "' and '"+ todate+"' ");
			
			if(searchText!=null){
				sql.append("and (fullname like('%"+searchText+"%') or  abrivationid like('%"+searchText+"%') or   invreq = '"+searchText+"')  ");
			}
			
			if(jobtitle!=null){
				sql.append("and jobtitle='"+jobtitle+"' ");
			}
			
			if(invsttype != null){
				sql.append("and invsttypeid='"+invsttype+"' ");
			}
			
			if(invstsecid!=null){
				sql.append("and sectionid='"+invstsecid+"' ");
			}
		
			if(!filter_ward.equals("0")){
				if(filter_ward.equals("IPD")){
					sql.append("and ipdid!= 0 and ipdid is not null ");
				}else if(filter_ward.equals("OPD")){
					sql.append("and (ipdid=0 or ipdid is null) ");
				}else if(filter_ward.equals("URGENT")){
					sql.append("and ipdid!= 0 and ipdid is not null ");
				}
			}
			
			if(!filter_status.equals("0")){
				if(filter_status.equals("1")){
					sql.append("and upstatus=0 and compstatus=0 ");
				}else if(filter_status.equals("2")){
					sql.append("and upstatus=0 and compstatus=1 ");
				}else if(filter_status.equals("3")){
					sql.append("and upstatus=1 and compstatus=1 and status=0 ");
				}else if(filter_status.equals("4")){
					sql.append("and status=1 ");
				}
			}
			
			if(jobtitle!=null){
				if(!jobtitle.equals("0")){
					String dpartment = getInvestigationTypeDepartment(jobtitle);
					sql.append(" and department="+dpartment+" ");
				}
			}
			
			if(outsource!=null){
				if(!outsource.equals("0")){
					sql.append(" and outsourceid="+outsource+" ");
				}
			}
			
			if(loginInfo.isJobtitlewise_investigation()){
				if(loginInfo.getJobTitle().equals("Pathlab")){
					sql.append(" and  apm_client_parent_investigation.department='5' ");
				}else if(loginInfo.getJobTitle().equals("Radiologist")){
					sql.append(" and  apm_client_parent_investigation.department='7' ");
				}
			}
			if(!isdeleted.equals("")){
				sql.append(" and  apm_client_parent_investigation.deleted='"+isdeleted+"' ");
			}
			sql.append("order by id desc ");
			
			PreparedStatement ps = connection.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				
				if(filter_ward.equals("URGENT")){
					String name = checkICUorCasulty(rs.getString(2));
					if (!name.equals("")) {
						result++;
					}
				}else{
					result++;
				}
				
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public String getPrintInvsttype(String selectedid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT invsttype FROM apm_client_investigation where parentid = "
				+ selectedid + " group by parentid ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public String getAttchmentFile(String selectedid, String invsttype) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT filename FROM apm_document where lnvstid = "
				+ selectedid + " ";

		StringBuffer str = new StringBuffer();

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();

			int i = 1;
			while (rs.next()) {
				str
						.append("(" + i + ") " + selectedid + "_" + invsttype
								+ " ,");

				i++;
			}

			if (str.length() > 0) {
				result = str.substring(0, str.length() - 2);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public int updateReportType(int parentid, String reporttype) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_client_parent_investigation set reporttype=? where id = "
				+ parentid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, reporttype);
			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<Emr> getInvstDocList(String selectedid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Emr> list = new ArrayList<Emr>();
		String sql = "select apm_document.id,apm_document.history,apm_document.filename,apm_document.practitionerid,apm_document.lastmodified,apm_document.doct_type,lnvstid,patientid "
				+ "from apm_document where lnvstid=" + selectedid + " ";

		try {

			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			int i = 1;
			StringBuffer str = new StringBuffer();
			while (rs.next()) {
				Emr emr = new Emr();
				emr.setId(rs.getInt(1));
				emr.setDescription(rs.getString(2));
				emr.setFileName(rs.getString(3));
				emr.setPractitionerId(rs.getInt(4));
				emr.setLastModified(DateTimeUtils.getIndianDateTimeFormat(rs
						.getString(5)));
				emr.setDoctType(rs.getString(6));

				int invatid = rs.getInt(7);
				emr.setInvstid(invatid);

				if (invatid > 0) {
					InvestigationDAO investigationDAO = new JDBCInvestigationDAO(
							connection);
					String invsttype = investigationDAO
							.getPrintInvsttype(Integer.toString(invatid));
					emr.setInvstFoleName("(" + i + ") " + invatid + "_"
							+ invsttype);
				}

				i++;
				list.add(emr);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public Investigation getInvestigationTyeID(String selectedid) {
		PreparedStatement preparedStatement = null;
		Investigation investigation = new Investigation();
		String sql = "SELECT invsttypeid,clientid,department,practitionerid FROM apm_client_parent_investigation where id = "
				+ selectedid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				investigation.setInvsttypeid(rs.getString(1));
				investigation.setClientId(rs.getString(2));
				investigation.setPrectionerid(rs.getString(3));
				//practitionerid is already taken ..so we use name for id ===lokesh 
				investigation.setPractitionerName(rs.getString(4));
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return investigation;
	}

	public Investigation getInvstTypeCharges(String invsttypeid) {
		PreparedStatement preparedStatement = null;
		Investigation investigation = new Investigation();
		String sql = "SELECT name,charge,department FROM apm_investigation_type where id = "
				+ invsttypeid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				investigation.setInvsttype(rs.getString(1));
				investigation.setCharge(rs.getString(2));
				investigation.setPrectionerid(rs.getString(3));
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return investigation;
	}

	public ArrayList<String> getinvstClientChargeList(String selectedid) {
		PreparedStatement preparedStatement = null;
		ArrayList<String> list = new ArrayList<String>();
		String sql = "SELECT invsttype FROM apm_client_investigation where parentid = "
				+ selectedid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				list.add(rs.getString(1));
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return list;
	}

	public Investigation getClientInvstChargeDetails(String id) {
		PreparedStatement preparedStatement = null;
		Investigation investigation = new Investigation();
		String sql = "SELECT name,charge FROM apm_investigation_name where id = "
				+ id + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				investigation.setInvstname(rs.getString(1));
				investigation.setCharge(rs.getString(2));
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return investigation;
	}

	public Investigation getSelectedInvestigationClient(String selectedid) {
		PreparedStatement preparedStatement = null;

		String sql = "SELECT invstcode,invsttype,invstgroup,invstname,specimen,invstUnit,invstreporttype,normvalue,obsvalue,id,findings,biorefrange,methods FROM apm_client_investigation where parentid = "
				+ selectedid + " ";
		Investigation investigation = new Investigation();
		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				investigation.setInvstcode(rs.getString(1));
				investigation.setInvsttype(rs.getString(2));
				investigation.setInvstgroup(rs.getString(3));
				investigation.setInvstname(rs.getString(4));
				investigation.setSpecimen(rs.getString(5));
				investigation.setInvstUnit(rs.getString(6));
				investigation.setInvstreporttype(rs.getString(7));
				investigation.setNormvalue(rs.getString(8));
				investigation.setObsvalue(rs.getString(9));
				investigation.setId(rs.getInt(10));
				investigation.setFindings(rs.getString(11));
				investigation.setBiorefrange(rs.getString(12));
				investigation.setMethods(rs.getString(13));
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return investigation;
	}

	public int updateApproveStatus(String status, String id) {

		int result = 0;
		try {

			String sql = "update apm_client_parent_investigation set status="
					+ status + " where id=" + id + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public boolean isInvChargePaid(int chargeid) {

		boolean result = false;
		try {

			String sql = "SELECT id from apm_charges_payment where chargeinvoiceid="
					+ chargeid + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result = true;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	public int getchargeInvoiceidFromAssesment(int invchargeid) {

		int result = 0;
		try {

			String sql = "SELECT chargeinvoiceid from apm_charges_assesment where invoiceid="
					+ invchargeid + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				result = rs.getInt(1);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	public int saveTemplateParentInvestigation(Investigation investigation,
			String currentDataTime, String sessionadmissionid,
			String invsttypeid, int tmplateid) {
		int result = 0;
		int outoid = 0;
		PreparedStatement preparedStatement = null;
		String sql = "insert into apm_template_parent_investigation(clientid,practitionerid,conditionid,advoice,english,regional,hindi,prepay,postpay,other,lastmodified,reporttype,ipdid,jobtitle,invsttypeid,department,tmplateid) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, investigation.getClientId());
			preparedStatement.setString(2, investigation.getPrectionerid());
			preparedStatement.setString(3, investigation.getConditionid());
			preparedStatement.setString(4, investigation.getAdvoice());
			preparedStatement.setString(5, investigation.getEnglish());
			preparedStatement.setString(6, investigation.getRegional());
			preparedStatement.setString(7, investigation.getHindi());
			preparedStatement.setString(8, investigation.getPrepay());
			preparedStatement.setString(9, investigation.getPostpay());
			preparedStatement.setString(10, investigation.getOtherpay());
			preparedStatement.setString(11, currentDataTime);
			preparedStatement.setString(12, investigation.getInvstreporttype());
			preparedStatement.setString(13, sessionadmissionid);
			preparedStatement.setString(14, investigation.getJobtitle());
			preparedStatement.setString(15, invsttypeid);

			String department = getConditionDepartment(investigation
					.getConditionid());
			preparedStatement.setString(16, department);
			preparedStatement.setInt(17, tmplateid);
			result = preparedStatement.executeUpdate();

			if (result == 1) {
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if (resultSet.next()) {
					outoid = resultSet.getInt(1);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return outoid;
	}

	public int saveTemplateInvestigation(Investigation investigation,
			String currentDataTime, int saveparent, String clientId,
			String prectionerid, String conditionid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_template_investigation(clientid,practitionerid,conditionid,invstcode,invsttype,invstgroup,invstname,specimen,invstUnit,invstreporttype,parentid,lastmodified,normvalue) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try {

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, clientId);
			preparedStatement.setString(2, prectionerid);
			preparedStatement.setString(3, conditionid);
			preparedStatement.setString(4, investigation.getInvstcode());
			preparedStatement.setString(5, investigation.getInvsttype());
			preparedStatement.setString(6, investigation.getInvstgroup());
			preparedStatement.setString(7, investigation.getInvstname());
			preparedStatement.setString(8, investigation.getSpecimen());
			preparedStatement.setString(9, investigation.getInvstUnit());
			preparedStatement.setString(10, investigation.getInvstreporttype());
			preparedStatement.setInt(11, saveparent);
			preparedStatement.setString(12, currentDataTime);
			preparedStatement.setString(13, investigation.getNormvalue());
			// preparedStatement.setInt(14, investigation.getId());

			result = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int saveInvTemplateName(String templatetext, String currentDataTime) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		int outoid = 0;
		String sql = "insert into apm_invstigation_templatename(name,date) values(?,?) ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, templatetext);
			preparedStatement.setString(2, currentDataTime);

			result = preparedStatement.executeUpdate();

			if (result == 1) {
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if (resultSet.next()) {
					outoid = resultSet.getInt(1);
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return outoid;
	}

	public ArrayList<InvstTemplate> getTemplateList() {
		PreparedStatement preparedStatement = null;
		ArrayList<InvstTemplate> list = new ArrayList<InvstTemplate>();
		String sql = "SELECT id,name,date FROM apm_invstigation_templatename ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				InvstTemplate invstTemplate = new InvstTemplate();
				invstTemplate.setId(rs.getInt(1));
				invstTemplate.setName(rs.getString(2));
				invstTemplate.setDate(rs.getString(3));

				ArrayList<Master> invstTypeList = getInvstTypeList(invstTemplate
						.getId());
				invstTemplate.setInvstTypeList(invstTypeList);
				list.add(invstTemplate);

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	private ArrayList<Master> getInvstTypeList(int id) {
		PreparedStatement preparedStatement = null;
		ArrayList<Master> list = new ArrayList<Master>();
		StringBuffer sql = new StringBuffer();

		sql
				.append("SELECT apm_template_parent_investigation.id, name FROM apm_investigation_type inner join apm_template_parent_investigation ");
		sql
				.append("on apm_template_parent_investigation.invsttypeid = apm_investigation_type.id where tmplateid="
						+ id + " ");
		sql.append("group by apm_template_parent_investigation.invsttypeid ");

		try {
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				master.setTemplateid(id);

				ArrayList<Investigation> testNameList = getTestNameList(master
						.getName(), master.getId());
				master.setTestNameList(testNameList);

				list.add(master);

			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return list;
	}

	private ArrayList<Investigation> getTestNameList(String name, int id) {
		PreparedStatement preparedStatement = null;
		ArrayList<Investigation> list = new ArrayList<Investigation>();
		String sql = "SELECT invstname FROM apm_template_investigation where invsttype = '"
				+ name + "' and parentid = " + id + " ";
		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Investigation investigation = new Investigation();
				investigation.setInvstname(rs.getString(1));

				list.add(investigation);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	public ArrayList<Investigation> getInvstNameList(String templateid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Investigation> list = new ArrayList<Investigation>();
		StringBuffer sql = new StringBuffer();

		sql
				.append("SELECT invstname FROM apm_template_parent_investigation inner join apm_template_investigation ");
		sql
				.append("on apm_template_investigation.parentid = apm_template_parent_investigation.id ");
		sql.append("where tmplateid = " + templateid + " ");

		try {
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Investigation investigation = new Investigation();
				investigation.setInvstname(rs.getString(1));

				list.add(investigation);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public String getGraphDateList(String clientid, String invstname,
			String fromdate, String todate) {
		PreparedStatement preparedStatement = null;
		String result = "";
		StringBuffer sql = new StringBuffer();
		StringBuffer str = new StringBuffer();

		todate = todate + " 23:59:59";

		sql
				.append("SELECT lastmodified FROM apm_client_investigation where clientid = "
						+ clientid + " and lastmodified between ");
		sql.append("'" + fromdate + "' and '" + todate + "' and invstname = '"
				+ invstname + "' and obsvalue!=0 ");

		try {
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String date = rs.getString(1);
				date = DateTimeUtils.getDBToSimpleDate(date);

				str.append("'" + date + "'" + ",");
				result = str.substring(0, str.length() - 1);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return result;
	}

	public String getGraphValueList(String clientid, String invstname,
			String fromdate, String todate) {
		PreparedStatement preparedStatement = null;
		String result = "";
		StringBuffer sql = new StringBuffer();
		StringBuffer str = new StringBuffer();

		todate = todate + " 23:59:59";

		sql
				.append("SELECT obsvalue FROM apm_client_investigation where clientid = "
						+ clientid + " and lastmodified between ");
		sql.append("'" + fromdate + "' and '" + todate + "' and invstname = '"
				+ invstname + "' and obsvalue!=0 ");

		try {
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String val = rs.getString(1);

				str.append(val + ",");
				result = str.substring(0, str.length() - 1);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return result;
	}

	public String getGraphinvstTypeName(String clientid, String invstname,
			String fromdate, String todate) {
		PreparedStatement preparedStatement = null;
		String result = "";
		StringBuffer sql = new StringBuffer();
		StringBuffer str = new StringBuffer();

		todate = todate + " 23:59:59";

		sql
				.append("SELECT invsttype FROM apm_client_investigation where clientid = "
						+ clientid + " and lastmodified between ");
		sql.append("'" + fromdate + "' and '" + todate + "' and invstname = '"
				+ invstname + "' and obsvalue!=0 order by id desc limit 0,1 ");

		try {
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String val = rs.getString(1);

				result = val;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return result;
	}

	public ArrayList<Client> getPaymentClientList(String searchText,
			String fromDate, String toDate,LoginInfo loginInfo) {
		PreparedStatement preparedStatement = null;
		ArrayList<Client>list = new ArrayList<Client>();
		StringBuffer sql = new StringBuffer();
		
		toDate = toDate + " 23:59:59";
		
		sql.append("SELECT concat(firstname,' ',surname), apm_client_parent_investigation.id,clientid,apm_client_parent_investigation.invsttypeid FROM apm_client_parent_investigation ");
		sql.append("inner join apm_patient on apm_patient.id = apm_client_parent_investigation.clientid ");
		sql.append("where apm_client_parent_investigation.lastmodified between '"+fromDate+"' and '"+toDate+"' and apm_client_parent_investigation.charginvoiceid=0  group by apm_client_parent_investigation.clientid ");
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Client client = new Client();
				client.setClientName(rs.getString(1));
				client.setInvstid(rs.getString(2));
				client.setClientid(rs.getString(3));
				client.setInvstypeid(rs.getString(4));
				String department= getDepartmentFromInvsType(client.getInvstypeid());
				client.setDepartment(department);
				ArrayList<Master>invetigationList = getInvestigationList(client.getClientid(),fromDate,toDate,loginInfo);
				
				client.setInvetigationList(invetigationList);
				
				list.add(client);
			}
			
		}catch (Exception e) {

			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Master> getInvestigationList(String clientid, String fromDate,
			String toDate,LoginInfo loginInfo) {
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list = new ArrayList<Master>();
		StringBuffer sql = new StringBuffer();
		
		
		toDate = toDate + " 23:59:59";
		
		sql.append("SELECT apm_client_parent_investigation.id,invsttype,apm_client_investigation.invstname FROM apm_client_parent_investigation ");
		sql.append("inner join apm_client_investigation on apm_client_investigation.parentid = apm_client_parent_investigation.id ");
		sql.append("where apm_client_parent_investigation.clientid = "+clientid+" ");
		sql.append("and  apm_client_parent_investigation.lastmodified between '"+fromDate+"' and '"+toDate+"' ");
		sql.append("and apm_client_parent_investigation.charginvoiceid=0 ");
		sql.append("group by invsttype ");
		
		try{
			
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Master master = new Master();
				master.setId(rs.getInt(1));	
				master.setName(rs.getString(2));
				master.setInvsname(rs.getString(3));
				ArrayList<UserProfile> investigationUsers=getAllInvestigationUsers(loginInfo.getClinicid());
				master.setInvestigationUsers(investigationUsers);
				ArrayList<Master> investiNames= getAllInvestigationNames(clientid,master.getId());
				int invoiceid= getifInvoiceCreated(rs.getString(1));
				master.setInvoiceid(invoiceid);
				master.setInvsparentid(rs.getString(1));
				master.setInvestiNames(investiNames);
				master.setClientid(clientid);
				list.add(master);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		return list;
	}

	private ArrayList<Master> getAllInvestigationNames(String clientid,int parentid ) {
		ClientDAO clientDAO=new JDBCClientDAO(connection);
		ArrayList<Master> list=new ArrayList<Master>();
		
		try {
			Client client=clientDAO.getClientDetails(clientid);
			InvestigationMasterDAO investigationMasterDAO=new JDBCInvestigationMasterDAO(connection);
		
			
			String sql="SELECT id,invstname,invsttype,normvalue,obsvalue,invstUnit from apm_client_investigation where parentid="+parentid+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				 
				 Master master=new Master();
				 master.setId(rs.getInt(1));
				 String invsname =rs.getString(2);
				 String invtype=rs.getString(3);
				 master.setReference(rs.getString(4));
				 master.setFinding(rs.getString(5));
				 master.setUnit(rs.getString(6));
				 master.setName(invsname);
			  	 String invgrpid=investigationMasterDAO.getInvestigationTypeId(invtype);
			     int chargeid=investigationMasterDAO.getInvsNameId(invgrpid,invsname);
				 
				 if(client.getWhopay()!=null){
					  
					 if(client.getWhopay().equals("Client")){
						 
					       
						  AppointmentType appointmentType = getInvestigationCharges("0", invgrpid, chargeid);
						  master.setCharge(appointmentType.getCharges());
						  if(appointmentType.getId()>0){
							  master.setId(appointmentType.getId());
						  } 
						   
					 } else {
						 
						 String tpid=client.getTypeName();
						 AppointmentType appointmentType = getInvestigationCharges(tpid, invgrpid, chargeid);
						 master.setCharge(appointmentType.getCharges());
						 if(appointmentType.getId()>0){
							  master.setId(appointmentType.getId());
						  } 
					 }
				 }
				 master.setClientid(clientid);
				 list.add(master);
			}
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return list;
	}

	
	public AppointmentType getInvestigationCharges(String tpid,String invsgroup,int chargeId) {

		AppointmentType appointmentType=new AppointmentType();
		IpdDAO ipdDAO=new JDBCIpdDAO(connection);
		
		try {
			
			if(!ipdDAO.checkifMainTp(tpid)){
				 
				String temptpid= ipdDAO.getFollowerTp(tpid); 
				if(temptpid!=null){
					
					if(!temptpid.equals("0")){
						 tpid=temptpid;  
					}
				}
				
			}
			
			
			String sql="select id,charges,tpid,code from apm_appointment_type where invstype="+invsgroup+" and chargeid="+chargeId+" and tpid="+tpid+";";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				  
				  appointmentType.setId(rs.getInt(1));
				  appointmentType.setCharges(rs.getString(2));
				  appointmentType.setTpid(rs.getString(3));
				  appointmentType.setCode(rs.getString(4));
				  
			}
			
			
		} catch (Exception e) {

			e.printStackTrace();
		} 
		
		return appointmentType;
	}
	
	public ArrayList<UserProfile> getAllInvestigationUsers(int clinicid) {

		ArrayList<UserProfile> list=new ArrayList<UserProfile>();
		try {
			
			String sql="select id,concat(initial,' ',firstname,' ',lastname) from apm_user where clinicid="+clinicid+" and jobgroup=6 ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				
				   UserProfile profile=new UserProfile();
				   profile.setId(rs.getInt(1));
				   profile.setFullname(rs.getString(2));
				   list.add(profile);
			} 
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int updateInvsInvoiceId(String invsparentid, int chargeInvoiceid,String userid) {

		int result=0;	
		try {
			
			String sql="update apm_client_parent_investigation set charginvoiceid="+chargeInvoiceid+",userid='"+userid+"' where id="+invsparentid+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			result=ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}
	
	public int getIpdIdFromInvestigation(String id) {
		
		int ipdid=0;
		try {
			
			String sql="select ipdid from apm_client_parent_investigation where id="+id+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				 
				  ipdid=rs.getInt(1);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
	
		return ipdid;
	}
	
	public String getDepartmentFromInvsType(String invstypeid) {
		
		String dept="";
		try {
			
			String sql="select department from apm_investigation_type where id="+invstypeid+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				 dept=rs.getString(1);
				 dept=getDepName(dept);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return dept;
	}
	
	private String getDepName(String id)  {
		
		String depname="";
		try {
			
			String sql="select concat(initial,' ',firstname,' ',lastname) from apm_user where id="+id+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				  depname=rs.getString(1);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return depname;
	}

	public int getifInvoiceCreated(String invsparentid) {

		int result=0;
		try {
			
			String sql="SELECT id from apm_invoice where ginvstid="+invsparentid+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				  result=rs.getInt(1);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}

	public AppointmentType getInvestigationPaybyCharge(String whopay,String invstname,String tpid,int clientid,LoginInfo loginInfo) {
		PreparedStatement preparedStatement = null;
		AppointmentType appointmentType = new AppointmentType();
		ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
		
		//get ipd details
	       AssessmentFormDAO assessmentFormDAO = new JDBCAssessmentFormDAO(connection);
			int bedid = assessmentFormDAO.getIpdBedno(Integer.toString(clientid));
			String wardid = "0";
			if(bedid!=0){
				IpdDAO ipdDAO = new JDBCIpdDAO(connection);
				BedDao bedDao=new JDBCBedDao(connection);
				
				String ipdid = assessmentFormDAO.getAdmissionid(Integer.toString(clientid));
				Bed bed = bedDao.getEditIpdData(ipdid);
				wardid = bed.getWardid();
				
				if(loginInfo.getWardforcharge()==1){
					wardid = "0";
				}
			
			}
		
		String sql = "SELECT id,charges FROM apm_appointment_type where name='"+invstname+"' and tpid = 0 and wardid="+wardid+" "
				+ " and chargeType='Investigation' order by id desc limit 0,1 ";
		
		
		if(whopay.equals(Constants.PAY_BY_THIRD_PARTY)){
			int followup = getTpFollowupid(tpid);
			if(followup!=0){
				tpid = Integer.toString(followup);
				
			}
			
			if(loginInfo.getWardforcharge()==1){
				wardid = "0";
			}
			
			ThirdParty thirdParty = thirdPartyDAO.getThirdPartyDetails(tpid);
			sql = "SELECT id,"+thirdParty.getChargecolumnname()+" FROM apm_appointment_type where name='"+invstname+"' and tpid = "+tpid+" and wardid="+wardid+" "
					+ " and chargeType='Investigation' order by id desc limit 0,1 ";
		}
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				appointmentType.setId(rs.getInt(1));
				appointmentType.setCharges(rs.getString(2));
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return appointmentType;
	}

	public int getTpFollowupid(String tpid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT followtp FROM apm_third_party_details where id = "+tpid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public int checkRoundCharge(String invstype) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "SELECT roundcharge FROM apm_investigation_type where id = "+invstype+" ";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				if(rs.next()){
					result = rs.getInt(1);
				}
				
			}catch (Exception e) {
				// TODO: handle exception
			}
			
		return result;
	}

	public String getMasterChargeName(String invstype) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT name FROM apm_investigation_type where id = "+invstype+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
			}
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}

	public ArrayList<String> getRequestedInvestigationList(String curdate,String ipdclientid,String invreq) {
		PreparedStatement preparedStatement = null;
		ArrayList<String>list = new ArrayList<String>();
		
		String todate = curdate +  " 23:59:59";
		//and deleted=0 because after delete it also show in add charge in investigation
		String sql = "SELECT id FROM apm_client_parent_investigation where lastmodified between '"+curdate+"' and '"+todate+"' and clientid="+ipdclientid+" and deleted=0  ";
		if(!invreq.equals("0")){
			sql = "SELECT id FROM apm_client_parent_investigation where invreq = "+invreq+" and deleted=0 ";
		}
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				int checkChargeRaised = checkChargeRaised(rs.getInt(1));
				if(checkChargeRaised==0){
					list.add(rs.getString(1));
				}
				
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	public int updateDateOFInvestigation(String editinvstparentid,
			String approved,String datetime, String userid) {

		int result= 0;
		StringBuffer buffer = new StringBuffer();
		try {
			if(approved.equals("1")){
				
				buffer.append("update apm_client_parent_investigation set dateofcompleted='"+datetime+"',approved_userid='"+userid+"' ");
			} else {
				buffer.append("update apm_client_parent_investigation set dateofupdated='"+datetime+"' ");
			}
			
			buffer.append("where id="+editinvstparentid+"");
			
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			result =ps.executeUpdate();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}

	public int updateCollectDateandStatus(String id, String date) {

		int result=0;
		try {
			String sql="update apm_client_parent_investigation set compstatus=1,compdate='"+date+"' where id="+id+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			result =ps.executeUpdate();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}

	public ArrayList<Master> getInvestigationSectionList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list = new ArrayList<Master>();
		String sql = "SELECT id,name FROM apm_investigation_section ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				
				list.add(master);
				
			}
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	public ArrayList<Master> getInvTypeList(String sectionid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list = new ArrayList<Master>();
		String sql = "SELECT id,name FROM apm_investigation_type where sectionid = "+sectionid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				
				list.add(master);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	public ArrayList<Master> getMasterInvstTypeList(String sectionid, String clientid,String fromdate,String todate,String reporttype,String selecteid,String invreq) {
		  PreparedStatement preparedStatement = null;
		  ArrayList<Master>list = new ArrayList<Master>();
		  InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
		 /* String sql = "SELECT apm_client_parent_investigation.id,name,apm_investigation_type.reporttype FROM apm_client_parent_investigation "
		    + " inner join apm_investigation_type on apm_investigation_type.id = apm_client_parent_investigation.invsttypeid "
		    + " where clientid = "+clientid+" and sectionid = "+sectionid+" "
		    + " and lastmodified between '"+fromdate+"' and  '"+todate+"' and upstatus = 1  ";
		 
		  if(!reporttype.equals("Text")){
			 sql = sql + " and apm_investigation_type.reporttype!='Text' "; 
		  }else{
			  sql = sql + " and apm_client_parent_investigation.id = "+selecteid+""; 
		  }*/
		  
		 
		  StringBuffer sql = new StringBuffer();
		  sql.append("SELECT apm_client_parent_investigation.id,name,apm_investigation_type.reporttype,remark,apm_client_parent_investigation.department FROM apm_client_parent_investigation ");
		  sql.append("inner join apm_investigation_type on apm_investigation_type.id = apm_client_parent_investigation.invsttypeid ");
		  sql.append("where clientid = "+clientid+"   ");
		  if(sectionid!=null){
				if(sectionid.equals("none")){
					
				}else{
					sql.append(" and sectionid = "+sectionid+"  ");
				} 
			 }else{
					sql.append(" and sectionid = "+sectionid+"  ");
				} 
		 if(invreq==null&&fromdate==null){
			 sql.append("  and apm_client_parent_investigation.id = "+selecteid+"   ");
		 }else{
		  
		  if(invreq.equals("0")){
			 
				  sql.append("and lastmodified between '"+fromdate+"' and  '"+todate+"' and upstatus = 1 "); 	  
			  
			
		  }else{
			  sql.append("and invreq ="+invreq+" and upstatus = 1 "); 
		  }
		  
		  
		  if(!reporttype.equals("Text")){
			  sql.append("and apm_investigation_type.reporttype!='Text' ");
			  }else{
				  sql.append("and apm_client_parent_investigation.id = "+selecteid+" ");
			  }
		 }
		  ClientDAO clientDAO = new JDBCClientDAO(connection);
		  Client client = clientDAO.getClientDetails(clientid);
		  ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
		  AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
		  
		   
		  
		
		  try{
		   preparedStatement = connection.prepareStatement(sql.toString());
		   ResultSet rs = preparedStatement.executeQuery();
		   while(rs.next()){
		    Master master = new Master();
		    master.setId(rs.getInt(1));
		    String name = rs.getString(2).split("~")[0];
		    
		  //set investigation alternate name
			String tpid = client.getTypeName();
			ThirdParty thirdParty = thirdPartyDAO.getThirdPartyDetails(tpid);
			
//				String alternamename = accountsDAO.getInvestigationAltName(name);
//				if(alternamename !=null){
//					if(!alternamename.equals("")){
//						name = alternamename;
//					}
//				}
		    
		    
		    master.setName(name);
		    master.setReporttypex(rs.getString(3));
		    master.setDescription(rs.getString(4));
		    master.setDepartment(rs.getString(5));
		    ArrayList<Investigation>selectedInvstList = investigationDAO.getPrintSelectedInvestigationData(rs.getString(1));
		    master.setSelectedInvstList(selectedInvstList);
		    
		    list.add(master);
		   }
		   
		  }catch (Exception e) {
			  e.printStackTrace();
		  }
		  return list;
		 }

	public String getInvSectionName(String sectionid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT name FROM apm_investigation_section where id = "+sectionid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
			}
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public int updateRemark(String editinvstparentid, String remark) {
		int result= 0;
		try {
			StringBuffer buffer= new StringBuffer();	
			buffer.append("update apm_client_parent_investigation set remark=? ");
			
			buffer.append("where id="+editinvstparentid+"");
			
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ps.setString(1, remark);
			result =ps.executeUpdate();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}

	public ArrayList<Master> getAllInvestigationTemplateList(String sectionid) {

		ArrayList<Master> list= new ArrayList<Master>();
		try {
			
			  String sql= "select id, invs_type, name from apm_investigation_template where invs_section="+sectionid+" ";
			  PreparedStatement ps=connection.prepareStatement(sql);
			  ResultSet rs =ps.executeQuery();
			  while(rs.next()){
				  
				   Master master= new Master();
				   master.setId(rs.getInt(1));
				   master.setInvestigation_type_id(rs.getString(2));
				   master.setName(rs.getString(3));
				   list.add(master);
			  }
			  
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return list;
	}

	public String getInvsTemplateData(String id) {

		String data="";
		try {
			String sql="select template from apm_investigation_template where id="+id+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs =ps.executeQuery();
			while(rs.next()){
				
				 data =rs.getString(1);
			}
			
		} catch (Exception e) {

				e.printStackTrace();
		}	
			
		return data;
	}

	
	public Master getInvSectionDetails(String sectionid) {
		PreparedStatement preparedStatement = null;
		Master master = new Master();
		String sql = "SELECT labname,creport,doctorid FROM apm_investigation_section where id = "+sectionid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				master.setLabname(rs.getString(1));
				master.setCreport(rs.getString(2));
				master.setPractitionerid(rs.getString(3));
				if(rs.getString(2)==null){
					master.setCreport("");
				}
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return master;
	}

	public String getInvstPrintbr(String invsttypeid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT printbr FROM apm_investigation_type where id = "+invsttypeid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public String getGpDepartment(String gpid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT dname FROM apm_gp_details where id = "+gpid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	
	public String getInvsJobtitle(String invsttypeid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select jobtitle from apm_investigation_type inner join job_title on "
				+ " job_title.id = apm_investigation_type.department "
				+ " where apm_investigation_type.id = "+invsttypeid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public int saveInvRequest(String ukCurrentDataTime) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_investigation_req(commencing) values(?) ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, ukCurrentDataTime);
			result = preparedStatement.executeUpdate();
			
			if (result == 1) {
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				result = resultSet.getInt(1);
			}
		}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}
	
public int cancelInvestigation(String selectedid,String userid) {
		int result=0;
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		      Calendar cal = Calendar.getInstance();
		      String date = dateFormat.format(cal.getTime());  
			//String sql="update inventory_procurement set deleted=1,cancel_reason='"+delete_reason+"' where id="+id+"";
			String sql="update apm_client_parent_investigation set deleted=1,deleted_userid='"+userid+"',deleted_datetime='"+date+"' where id="+selectedid+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			result=ps.executeUpdate();
		} catch (Exception e) {
		   e.printStackTrace();
		}
		return result;
	}

	public int saveUpDeleteInvestigation(String selectedid, String delete_reason, String userid, String loc) {
		int result = 0;
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			String date = dateFormat.format(cal.getTime());

			String sql = "insert into inventory_product_log (productid, userid, location,  date, deleted, comment, isindent, investigationid) values (?,?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "0");
			preparedStatement.setString(2, userid);
			preparedStatement.setString(3, loc);
			preparedStatement.setString(4, date);
			preparedStatement.setString(5, "1");
			preparedStatement.setString(6, delete_reason);
			preparedStatement.setString(7, "4");
			preparedStatement.setString(8, selectedid);
			result = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//@  for get aproved investigation
			public ArrayList<Investigation> getAprovedInvestigation(String ipdid) {
				ArrayList<Investigation> arrayList = new ArrayList<Investigation>();
				try {
					String sql ="select lastmodified,userid,invsttypeid,dateofcompleted from apm_client_parent_investigation where ipdid ='"+ipdid+"' and seen='0'";
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					ResultSet rs = preparedStatement.executeQuery();
					while (rs.next()) {
						Investigation investigation = new Investigation();
						if (rs.getString(1) != null) {
							investigation.setDate(DateTimeUtils.getDBDate(rs.getString(1)));
						} else {
							investigation.setDate("");
						}
						String userid1 = rs.getString(2);
						String invtypeid = rs.getString(3);
						if (rs.getString(4) != null) {
							String date = rs.getString(4);
							investigation.setComplete_date(DateTimeUtils.getDBDate(rs.getString(4)));
						} else {
							investigation.setComplete_date("");
						}
						String userid = getUserIFromId(userid1); 
						investigation.setUserid(userid);
						String invsttype = getInvestType(Integer.parseInt(invtypeid));
						investigation.setInvsttype(invsttype);
						arrayList.add(investigation);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return arrayList;
			}

			private String getUserIFromId(String userid1) {
				String name ="";
				try {
					String sql ="select userid from apm_user where id='"+userid1+"'";
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					ResultSet rs = preparedStatement.executeQuery();
					while (rs.next()) {
						name = rs.getString(1);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return name;
			}

			public int updateInvestiSeenStatus(String ipdid) {
				int res =0;
				try {
					String sql ="update apm_client_parent_investigation set seen='1' where ipdid='"+ipdid+"'";
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					res= preparedStatement.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return res;
			}

			public ArrayList<Investigation> getIPDInvestList(String ipdid) {
				//  03 oct 2017 ipd investigation called from ipddashboardaction
				PreparedStatement preparedStatement = null;
				ArrayList<Investigation> list = new ArrayList<Investigation>();
				String sql = "SELECT id,lastmodified,reporttype FROM apm_client_parent_investigation where ipdid ='"+ipdid+"'  order by id desc ";

				try {
					preparedStatement = connection.prepareStatement(sql);
					ResultSet rs = preparedStatement.executeQuery();
					while (rs.next()) {
						Investigation investigation = new Investigation();
						investigation.setId(rs.getInt(1));

						if (rs.getString(2) != null) {
							investigation.setDate(DateTimeUtils.getDBDate(rs
									.getString(2)));
						} else {
							investigation.setDate("");
						}
						Investigation investigation2 = getSelectedInvestigationClient(rs
								.getString(1));
						investigation.setInvsttype(investigation2.getInvsttype());
						investigation.setInvstreporttype(rs.getString(3));
						
						//check if pacs  exist
						int pacsexist = checkifPacsExist(rs.getInt(1));
						investigation.setPacs(pacsexist);
						

						list.add(investigation);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				return list;
			}

			public ArrayList<Master> getDepartmentinvList(String invreq) {
				PreparedStatement preparedStatement = null;
				ArrayList<Master>list = new ArrayList<Master>();
				StringBuffer sql = new StringBuffer();
				
				sql.append("SELECT apm_client_parent_investigation.id,apm_investigation_type.department FROM apm_client_parent_investigation ");
				sql.append("inner join apm_investigation_type on apm_investigation_type.id = apm_client_parent_investigation.invsttypeid ");
				sql.append("where invreq = "+invreq+" group by apm_investigation_type.department ");
				
				try{
					preparedStatement = connection.prepareStatement(sql.toString());
					ResultSet rs = preparedStatement.executeQuery();
					while(rs.next()){
						Master master = new Master();
						master.setId(rs.getInt(1));
						String dpartmentname = getJobTitleInvDepartmentName(rs.getString(2));
						master.setName(dpartmentname);
						
						ArrayList<Investigation>invTypeList = getInvTypeList(master.getId());
						master.setBghhead(false);
						master.setInvTypeList(invTypeList);
						
						list.add(master);
					}
					
				}catch (Exception e) {
					// TODO: handle exception
				}
				
				return list;
			}

			private ArrayList<Investigation> getInvTypeList(int id) {
				PreparedStatement preparedStatement = null;
				ArrayList<Investigation>list = new ArrayList<Investigation>();
				String sql = "select invsttype from apm_client_investigation where parentid = "+id+" group by parentid ";
				
				try{
					preparedStatement = connection.prepareStatement(sql);
					ResultSet rs = preparedStatement.executeQuery();
					while(rs.next()){
						Investigation investigation = new Investigation();
						investigation.setInvstname(rs.getString(1));
						
						list.add(investigation);
					}
					
				}catch (Exception e) {
					// TODO: handle exception
				}
				return list;
			}

			private String getInvDepartmentName(String id) {
				PreparedStatement preparedStatement = null;
				String result = "";
				String sql = "select name from apm_condition where department="+id+" ";
				
				try{
					preparedStatement = connection.prepareStatement(sql);
					ResultSet rs  = preparedStatement.executeQuery();
					if(rs.next()){
						result = rs.getString(1);
					}
					
				}catch (Exception e) {
					// TODO: handle exception
				}
				return result;
			}

			public Investigation getInvChargeInfo(int invrequest) {
				PreparedStatement preparedStatement = null;
				Investigation investigation = new Investigation();
				String sql = "SELECT id,clientid,practitionerid,invreq,invsttypeid,ipdid,department FROM apm_client_parent_investigation where invreq = "+invrequest+" ";
				
				try{
					preparedStatement = connection.prepareStatement(sql);
					ResultSet rs = preparedStatement.executeQuery();
					if(rs.next()){
						investigation.setId(rs.getInt(1));
						investigation.setClientId(rs.getString(2));
						investigation.setPrectionerid(rs.getString(3));
						investigation.setInvreq(rs.getString(4));
						investigation.setInvsttypeid(rs.getString(5));
						investigation.setIpdid(rs.getString(6));
						investigation.setDepartment(rs.getString(7));
						
						String machineInvList = getMachineInvList(invrequest);
						investigation.setMachineInvList(machineInvList);
						
						
						String mchinereqidlist = getMacineReqidList(invrequest);
						investigation.setMchinereqidlist(mchinereqidlist);
					}
					
				}catch (Exception e) {
					// TODO: handle exception
				}
				return investigation;
			}

			private String getMacineReqidList(int invrequest) {
				PreparedStatement preparedStatement = null;
				String result = "";
				String sql = "SELECT id FROM apm_client_parent_investigation where department in(5,19) and invreq = "+invrequest+" ";
				
				try{
					preparedStatement = connection.prepareStatement(sql);
					ResultSet rs = preparedStatement.executeQuery();
					while(rs.next()){
						result = result + rs.getString(1) + ",";
					}
					
					
					if(result.equals("")){
						result = "0";
					}
					
				}catch (Exception e) {
					e.printStackTrace();
				}
				
				return result;
			}

			private String getMachineInvList(int invrequest) {
				PreparedStatement preparedStatement = null;
				String result = "";
				String sql = "SELECT invsttypeid,invreq FROM apm_client_parent_investigation where department in(5,19) and invreq = "+invrequest+" ";
				
				try{
					preparedStatement = connection.prepareStatement(sql);
					ResultSet rs = preparedStatement.executeQuery();
					while(rs.next()){
						result = result + rs.getString(1) + ",";
					}
					
					
					if(result.equals("")){
						result = "0";
					}
					
				}catch (Exception e) {
					e.printStackTrace();
				}
				
				return result;
			}

			public ArrayList<Master> getInvPaksLists() {
				PreparedStatement preparedStatement = null;
				ArrayList<Master>list = new ArrayList<Master>();
				String sql = "select id,name from his_parent_package ";
				
				try{
					preparedStatement = connection.prepareStatement(sql);
					ResultSet rs = preparedStatement.executeQuery();
					while(rs.next()){
						Master master = new Master();
						master.setId(rs.getInt(1));
						master.setName(rs.getString(2));
						
						list.add(master);
					}
					
				}catch (Exception e) {
					// TODO: handle exception
				}
				return list;
			}

			public int getAppliedPkgID(String selectedid) {
				PreparedStatement preparedStatement = null;
				int result = 0;
				String sql = "SELECT pkg FROM apm_client_parent_investigation where id = "+selectedid+" ";
				
				try{
					preparedStatement = connection.prepareStatement(sql);
					ResultSet rs = preparedStatement.executeQuery();
					if(rs.next()){
						result = rs.getInt(1);
					}
					
				}catch (Exception e) {
					// TODO: handle exception
				}
				return result;
			}

			public String getPkgCharge(int pkg, String str) {
				PreparedStatement preparedStatement = null;
				String result = "";
				String sql = "SELECT cal_amount FROM his_child_package where packageid = "+pkg+" and chargename = '"+str+"' ";
				
				try{
					preparedStatement = connection.prepareStatement(sql);
					ResultSet rs = preparedStatement.executeQuery();
					if(rs.next()){
						result = rs.getString(1);
					}
					
				}catch (Exception e) {
					// TODO: handle exception
				}
				return result;
			}

			public ArrayList<Master> getPackageInvTypeList(String sectionid) {
				PreparedStatement preparedStatement = null;
				ArrayList<Master>list = new ArrayList<Master>();
				
				String invtypeid = getPkgInvTypeId(sectionid);
				
				String sql = "SELECT id,name FROM apm_investigation_type where id in("+invtypeid+") ";
				
				try{
					preparedStatement = connection.prepareStatement(sql);
					ResultSet rs = preparedStatement.executeQuery();
					while(rs.next()){
						Master master = new Master();
						master.setId(rs.getInt(1));
						master.setName(rs.getString(2));
						
						list.add(master);
					}
					
				}catch (Exception e) {
					// TODO: handle exception
				}
				return list;
			}

			private String getPkgInvTypeId(String sectionid) {
				PreparedStatement preparedStatement = null;
				String result = "";
				String sql = "SELECT invtypeid FROM his_parent_package where id = "+sectionid+" ";
				
				try{
					preparedStatement = connection.prepareStatement(sql);
					ResultSet rs = preparedStatement.executeQuery();
					if(rs.next()){
						result = rs.getString(1);
					}
					
				}catch (Exception e) {
					// TODO: handle exception
				}
				return result;
			}

			public int setPathTestingInDB(Cbc cbc) {
				int result = 0;
				String sql = "insert into apm_cbc_report(refid, lastmodified, wbc, hashlym, perlym, hashmon, permon, hashgra, pergra, rbc, hgb, hcp, mcv, mch, mchc, rdw, tlt, mpv, testdate, pdw, pct) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				
				PreparedStatement preparedStatement = null;
				try{
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1, cbc.getRefid());
					preparedStatement.setString(2, DateTimeUtils.getUKCurrentDataTime(""));
					preparedStatement.setString(3, cbc.getWbc());
					preparedStatement.setString(4, cbc.getHashlym());
					preparedStatement.setString(5, cbc.getPerlym());
					preparedStatement.setString(6, cbc.getHashmon());
					preparedStatement.setString(7, cbc.getPermon());
					preparedStatement.setString(8, cbc.getHashgra());
					preparedStatement.setString(9, cbc.getPergra());
					preparedStatement.setString(10, cbc.getRbc());
					//hgb, hcp, mcv, mch, mchc, rdw, tlt, mpv
					preparedStatement.setString(11, cbc.getHgb());
					preparedStatement.setString(12, cbc.getHcp());
					preparedStatement.setString(13, cbc.getMcv());
					preparedStatement.setString(14, cbc.getMch());
					preparedStatement.setString(15, cbc.getMchc());
					preparedStatement.setString(16, cbc.getRdw());
					preparedStatement.setString(17, cbc.getTlt());
					preparedStatement.setString(18, cbc.getMpv());
					preparedStatement.setString(19, cbc.getTestdate());
					preparedStatement.setString(20, cbc.getPdw());
					preparedStatement.setString(21, cbc.getPct());
					
					result = preparedStatement.executeUpdate();
					
					
				}catch(Exception e){
					e.printStackTrace();
				}
				return result;
			}

			public int getPkgIDFromInvID(String ginvstid) {
				int id =0;
				try {
					String sql ="select pkg from apm_client_parent_investigation where id='"+ginvstid+"'";
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					ResultSet rs = preparedStatement.executeQuery();
					while (rs.next()) {
						id = rs.getInt(1);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return id;
			}

			public ArrayList<Investigation> getMobApiInvsList(String clientid) {
				
				ArrayList<Investigation> list= new ArrayList<Investigation>();
				InvestigationMasterDAO investigationMasterDAO= new JDBCInvestigationMasterDAO(connection);
				try {
					
					String sql="SELECT id,lastmodified,invsttypeid from apm_client_parent_investigation where clientid="+clientid+" ";
					PreparedStatement ps=connection.prepareStatement(sql);
					ResultSet rs =ps.executeQuery();
					while(rs.next()){
						
						Investigation investigation=new Investigation();
						investigation.setId(rs.getInt(1));
						String dateTime= DateTimeUtils.getDBDate(rs.getString(2));
						investigation.setInvsttypeid(rs.getString(3));
						Master master= investigationMasterDAO.getInvestigationType(investigation.getInvsttypeid());
						String name =master.getName();
						
						String data=name+" "+dateTime;
						investigation.setDate(data);
						list.add(investigation);
					}
					
				} catch (Exception e) {

					e.printStackTrace();
				}
				return list;
			}

			public String getInvereqDate(int invrequest) {
				PreparedStatement preparedStatement = null;
				String result = "";
				String sql = "select commencing from apm_investigation_req where id = "+invrequest+" ";
				
				try{
					preparedStatement = connection.prepareStatement(sql);
					ResultSet rs = preparedStatement.executeQuery();
					if(rs.next()){
						result = rs.getString(1);
					}
					
				}catch (Exception e) {
					// TODO: handle exception
				}
				return result;
			}

			public ArrayList<Investigation> getSavedInvestigationList(String saveid) {
				ArrayList<Investigation> arrayList = new ArrayList<Investigation>();
				try {
					String sql ="select invsttype,invstname from apm_client_investigation where parentid in ("+saveid+") group by invsttype";
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					ResultSet rs = preparedStatement.executeQuery();
					while (rs.next()) {
						Investigation investigation = new Investigation();
						investigation.setInvsttype(rs.getString(1));
						investigation.setInvstname(rs.getString(2));
						arrayList.add(investigation);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return arrayList;
			}

			public int updateInvestigationOutsource(String invid, String val, String userid, String date) {
				PreparedStatement preparedStatement = null;
				int result = 0;
				try {
					String sql = "update apm_client_parent_investigation set outsourceid=?,outsourceuserid=?,outsourcedate=? where id = '"+ invid + "' ";
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1, val);
					preparedStatement.setString(2, userid);
					preparedStatement.setString(3, date);
					result = preparedStatement.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return result;
			}

public ArrayList<Investigation> getOutSourceList() {
	ArrayList<Investigation> arrayList = new ArrayList<Investigation>();
	try {
		String sql ="select id,name from apm_outsource";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			Investigation investigation = new Investigation();
			investigation.setId(rs.getInt(1));
			investigation.setName(rs.getString(2));
			arrayList.add(investigation);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return arrayList;
}

public ArrayList<Investigation> getInvestiCriticalValue(String editinvstparentid) {
	PreparedStatement preparedStatement = null;
	ArrayList<Investigation> list = new ArrayList<Investigation>();
	/*String sql = "SELECT invstcode,invsttype,invstgroup,invstname,specimen,invstUnit,invstreporttype,normvalue,obsvalue,id,findings,biorefrange,methods FROM apm_client_investigation where parentid = "
			+ editinvstparentid + " ";
	 */
	
	StringBuffer buffer = new StringBuffer();
	buffer.append("select invstname,invstreporttype,normvalue,obsvalue,mobile from apm_client_investigation ");
	buffer.append("inner join apm_client_parent_investigation on apm_client_parent_investigation.id = apm_client_investigation.parentid ");
	buffer.append("inner join apm_user on apm_user.id = apm_client_parent_investigation.practitionerid ");
	buffer.append("where parentid='"+editinvstparentid+"' and obsvalue!='' and obsvalue>0 ");
	
	try {
		preparedStatement = connection.prepareStatement(buffer.toString());
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {

			Investigation investigation = new Investigation();
			investigation.setInvstname(rs.getString(1));
			investigation.setInvstreporttype(rs.getString(2));
			investigation.setNormvalue(rs.getString(3));
			investigation.setObsvalue(rs.getString(4));
			investigation.setMobile(rs.getString(5));
			
			if(investigation.getObsvalue()!=null){
				if(investigation.getObsvalue().equals("")){
					investigation.setObsvalue("0");
				}
			} else {
				investigation.setObsvalue("0");
			}
			
			if(investigation.getNormvalue()!=null){
				
				String str[] = investigation.getNormvalue().split("-");
				if(str.length==2){
					double min = Double.parseDouble(str[0]);
					double max= Double.parseDouble(str[1]);
					String obstr = investigation.getObsvalue().replaceAll("[^\\d.]", "");
					double obs= Double.parseDouble(obstr);
					if(obs<min || obs>max){
						investigation.setBold(1);
						list.add(investigation);
					} 
				}
			}
			
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
	return list;
}

public Investigation getInvestigationDetails(String inparentid) {
	Investigation investigation = new Investigation();
	try {
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT id, clientid, practitionerid, lastmodified, ipdid,opdid, ");
		buffer.append("outsourceid,outsourcedate, outsourceuserid, handoverto, isreturnOS, isreturndate, isreturnuserid, handoverfrom ");
		buffer.append("from apm_client_parent_investigation where id='"+inparentid+"' ");
		PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			investigation.setId(rs.getInt(1));
			investigation.setClientId(rs.getString(2));
			investigation.setPrectionerid(rs.getString(3));
			String lastmodifiedate = rs.getString(4);
			investigation.setIpdid(rs.getString(5));
			String opdid = rs.getString(6);
			investigation.setOutsourceid(rs.getString(7));
			investigation.setHandoverto(rs.getString(10));
			investigation.setIsreturnOS(rs.getString(11));
			investigation.setIsreturndate(rs.getString(12));
			investigation.setIsreturnuserid(rs.getString(13));
			investigation.setHandoverfrom(rs.getString(14));
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return investigation;
}

public String getOutSourceName(String outsourceid) {
	String name = "";
	try {
		String sql ="select name from apm_outsource where id='"+outsourceid+"'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			name = rs.getString(1);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return name;
}

public int updateInvesOutsourceNew(String invid, String userid, String date, String oshandoverto,
		String oshandoverfrom, String isreturnOS,String oshandovertostatus, String expdate) {
	PreparedStatement preparedStatement = null;
	int result = 0;
	try {
		//String sql = "update apm_client_parent_investigation set handoverto=?,isreturnOS=?,isreturndate=?,isreturnuserid=?,handoverfrom=? where id = '"+ invid + "' ";
		StringBuffer buffer = new StringBuffer();
		buffer.append("update apm_client_parent_investigation set handoverto=?,isreturnOS=?,isreturndate=?,isreturnuserid=?,handoverfrom=?,outsrc_expect_date=? ");
		if(oshandovertostatus.equals("0")){
			buffer.append(",handovertoDT='"+date+"',handovertouser='"+userid+"' ");
		}
		buffer.append("where id = '"+ invid + "'");
		preparedStatement = connection.prepareStatement(buffer.toString());
		preparedStatement.setString(1, oshandoverto);
		preparedStatement.setString(2, isreturnOS);
		preparedStatement.setString(3, date);
		preparedStatement.setString(4, userid);
		preparedStatement.setString(5, oshandoverfrom);
		preparedStatement.setString(6, expdate);
		result = preparedStatement.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}

public ArrayList<Investigation> getOutsourceReport(String fromDate, String toDate, String outsrclabid,String dept,String clientId, String invstTypeId) {
	ArrayList<Investigation> arrayList = new ArrayList<Investigation>();
	try {
		StringBuffer buffer = new StringBuffer();
		toDate = toDate +" "+"59:59:59";
		buffer.append("select apm_client_parent_investigation.id, clientid, practitionerid,apm_client_parent_investigation.lastmodified, ");
		buffer.append("ipdid, invsttypeid,  outsourceid, outsourcedate, outsourceuserid, handoverto, isreturnOS, isreturndate, ");
		buffer.append("isreturnuserid,handoverfrom, handovertoDT, handovertouser, ");
		buffer.append("apm_outsource.name,apm_investigation_type.name,fullname,charge,apm_client_parent_investigation.invreq ");
		buffer.append("FROM apm_client_parent_investigation ");
		buffer.append("inner join apm_outsource on apm_outsource.id = apm_client_parent_investigation.outsourceid ");
		buffer.append("inner join apm_investigation_type on apm_investigation_type.id = apm_client_parent_investigation.invsttypeid ");
		buffer.append("inner join apm_patient on apm_patient.id = apm_client_parent_investigation.clientid ");
		buffer.append("where apm_client_parent_investigation.outsourceid!='0' and apm_client_parent_investigation.lastmodified between '"+fromDate+"' and '"+toDate+"' and deleted='0' ");
		if(!outsrclabid.equals("")){
		buffer.append(" and outsourceid='"+outsrclabid+"' ");
		}
		if(!dept.equals("0")){
			buffer.append(" and apm_client_parent_investigation.department='"+dept+"' ");
		}
		if(!clientId.equals("")){
			buffer.append(" and apm_client_parent_investigation.clientid='"+clientId+"' ");
		}
		if(!invstTypeId.equals("")){
			buffer.append(" and apm_client_parent_investigation.invsttypeid='"+invstTypeId+"' ");
		}
		int x=0;
		PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			Investigation investigation = new Investigation();
			investigation.setId(rs.getInt(1));
			investigation.setClientId(rs.getString(2));
			investigation.setPrectionerid(rs.getString(3));
			investigation.setDate(DateTimeUtils.getCommencingDate1(rs.getString(4).split(" ")[0])+" "+rs.getString(4).split(" ")[1]);
			
			investigation.setIpdid(rs.getString(5));
			investigation.setInvsttypeid(rs.getString(6));
			investigation.setOutsourceid(rs.getString(7));
			investigation.setOutsourcedate(rs.getString(8));
			investigation.setOutsourceuserid(rs.getString(9));
			investigation.setHandoverto(rs.getString(10));
			investigation.setIsreturnOS(rs.getString(11));
			investigation.setIsreturndate(rs.getString(12));
			investigation.setIsreturnuserid(rs.getString(13));
			investigation.setHandoverfrom(rs.getString(14));
			investigation.setHandovertoDT(rs.getString(15));
			investigation.setHandovertouser(rs.getString(16));
			investigation.setOutsource(rs.getString(17));
			investigation.setInvsttype(rs.getString(18));
			investigation.setFullname(rs.getString(19));
			investigation.setCharge(""+rs.getInt(20));
						ClientDAO clientDAO= new JDBCClientDAO(connection);
			Client client= clientDAO.getClientDetails(rs.getString(2));
		/*	AppointmentType app=getInvestigationPaybyCharge(client.getWhopay(),
					investigation.getInvsttype(),String.valueOf(client.getNewtpid()),rs.getInt(2), 
					null);*/
			Investigation investigation2 = getOutSourceDataValue(rs.getString(7),rs.getString(6));
			/*investigation.setCharge(app.getCharges());*/
			if(investigation.getCharge()==null){
				investigation.setCharge("0");
			}else if(investigation.getCharge().equals("")){
				investigation.setCharge("0");
			}
			investigation.setAbrivationid(client.getAbrivationid());
			
				String chrge=getChargeOfInvestForOutSource(investigation.getInvsttype(),rs.getString(21));
				if(chrge!=null){
					if(chrge.contains("~")){
						String temp[]=chrge.split("~");
						investigation.setCharge(""+temp[0]);
						AccountsDAO accountsDAO= new  JDBCAccountsDAO(connection);
				
						investigation.setAdvoice(paymentidPhysical(accountsDAO.getChargeInvoiceId(temp[1])));
						
						
					}
				}else{
					investigation.setCharge(""+chrge);	
				}
				
		
			investigation.setTotalcharge(Integer.parseInt(investigation.getCharge())+x);
			x=investigation.getTotalcharge();

			if(investigation2.getAmmount()!=null){
				investigation.setAmmount(investigation2.getAmmount());
				if(investigation.getSharingtype()==1){
					investigation.setAmmount(investigation2.getAmmount());
				}else{
					int shareammt= (Integer.parseInt(investigation2.getAmmount())*Integer.parseInt(investigation.getCharge())/100);
					investigation.setAmmount(String.valueOf(shareammt));
				}
			}else{
				investigation.setAmmount("0");
			}
			
			arrayList.add(investigation);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return arrayList;
}

private Investigation getOutSourceDataValue(String outid, String invstid) {
	Investigation investigation = new Investigation();
	try {
		String sql = "SELECT id, ammount, sharing_type FROM apm_outsource_data where outsource_id='"+outid+"' and investigation_type_id='"+invstid+"' ";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			investigation.setSharingtype(rs.getInt(3));
			investigation.setAmmount(""+rs.getInt(2));
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return investigation;
}

public ArrayList<Investigation> getInvestiCriticalValueNew(String editinvstparentid) {
	PreparedStatement preparedStatement = null;
	ArrayList<Investigation> list = new ArrayList<Investigation>();
	/*String sql = "SELECT invstcode,invsttype,invstgroup,invstname,specimen,invstUnit,invstreporttype,normvalue,obsvalue,id,findings,biorefrange,methods FROM apm_client_investigation where parentid = "
			+ editinvstparentid + " ";
	 */
	
	StringBuffer buffer = new StringBuffer();
	buffer.append("select invstname,invstreporttype,normvalue,obsvalue,mobile,critical_value from apm_client_investigation ");
	buffer.append("inner join apm_client_parent_investigation on apm_client_parent_investigation.id = apm_client_investigation.parentid ");
	buffer.append("inner join apm_user on apm_user.id = apm_client_parent_investigation.practitionerid ");
	buffer.append("where parentid='"+editinvstparentid+"' and obsvalue!='' and obsvalue>0 and critical_value is not null ");
	
	try {
		preparedStatement = connection.prepareStatement(buffer.toString());
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {

			Investigation investigation = new Investigation();
			investigation.setInvstname(rs.getString(1));
			investigation.setInvstreporttype(rs.getString(2));
			investigation.setNormvalue(rs.getString(3));
			investigation.setObsvalue(rs.getString(4));
			investigation.setMobile(rs.getString(5));
			investigation.setCritical_value(rs.getString(6));
			if(investigation.getObsvalue()!=null){
				if(investigation.getObsvalue().equals("")){
					investigation.setObsvalue("0");
				}
			} else {
				investigation.setObsvalue("0");
			}
			
			if(investigation.getCritical_value()!=null){
				
				String str[] = investigation.getCritical_value().split("-");
				if(str.length==2){
					double min = Double.parseDouble(str[0]);
					double max= Double.parseDouble(str[1]);
					String obstr = investigation.getObsvalue().replaceAll("[^\\d.]", "");
					double obs= Double.parseDouble(obstr);
					if(min==1 && max==1){
						if(investigation.getObsvalue().equals("POSITIVE")){
							investigation.setBold(1);
							list.add(investigation);
						}
					}else if(obs<min || obs>max){
						investigation.setBold(1);
						list.add(investigation);
					} 
				}
			}
			
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
	return list;}

public ArrayList<Investigation> getallinvsttypewisecountrpt(String fromdate, String todate) {
	ArrayList<Investigation> list= new ArrayList<Investigation>();
	fromdate= DateTimeUtils.getCommencingDate1(fromdate);
	todate= DateTimeUtils.getCommencingDate1(todate);

	PreparedStatement ps =null;
	try {
		StringBuffer buffer= new StringBuffer();
		buffer.append("  select a.invsttypeid,b.name,count(*),sum(a.compstatus),sum(a.upstatus),sum(a.status) from apm_client_parent_investigation a  ");
		buffer.append(" inner join apm_investigation_type b on b.id= a.invsttypeid  ");
		buffer.append("  where a.lastmodified between '"+fromdate+"' and '"+todate+" 23:59:59' ");
		buffer.append("  group by a.invsttypeid order by (a.invsttypeid+0)  ");
		
		ps= connection.prepareStatement(buffer.toString());
		ResultSet  rs = ps.executeQuery();
		while(rs.next()){
			Investigation investigation= new Investigation();
			investigation.setInvsttypeid(rs.getString(1));
			investigation.setInvsttype(rs.getString(2));
			investigation.setCount(rs.getInt(3));
			Investigation investigation2= new Investigation();
			investigation2= getcountoofinvsttype(fromdate, todate, investigation.getInvsttypeid());
			investigation.setDeleted_investigation(investigation2.getDeleted_investigation());
			investigation.setNew_aproved(investigation2.getNew_aproved());
			investigation.setNew_collected(investigation2.getNew_collected());
			investigation.setNew_completed(investigation2.getNew_completed());
			investigation.setNew_invistigation(investigation2.getNew_invistigation());
			list.add(investigation);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return list;
}

public Investigation getcountoofinvsttype(String fromdate,String todate , String invstype){
	Investigation investigation= new Investigation();
	PreparedStatement ps= null;
	int deleted=0, newinvst=0,collected=0,completed=0,approved=0;
	try {
		String sql=" select deleted,upstatus,compstatus,status  from apm_client_parent_investigation where lastmodified between '"+fromdate+"' and '"+todate+" 23:59:59' and invsttypeid='"+invstype+"' ";
		ps= connection.prepareStatement(sql);
		ResultSet rs= ps.executeQuery();
		while(rs.next()){
			if(rs.getInt(1)==1){
				deleted++;
			} if(rs.getInt(2)==0 && rs.getInt(3)==0){
				newinvst++;
			} if(rs.getInt(2)==0 && rs.getInt(3)==1){
				collected++;
			} if(rs.getInt(2)==1 && rs.getInt(3)==1 && rs.getInt(4)==0){
				completed++;
			} if(rs.getInt(4)==1){
				approved++;
			}
		}
		investigation.setDeleted_investigation(deleted);
		investigation.setNew_invistigation(newinvst);
		investigation.setNew_collected(collected);
		investigation.setNew_completed(completed);
		investigation.setNew_aproved(approved);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return investigation;
}

public ArrayList<Investigation> getallInvestigationincome(String fromdate, String todate, String practioner,
		String dept) {
	ArrayList<Investigation> list= new ArrayList<Investigation>();
	try {
		StringBuffer buffer= new StringBuffer();
		buffer.append("  ");
		buffer.append("  ");
		buffer.append("  ");
		buffer.append("  ");
		buffer.append("  ");
	} catch (Exception e) {
		e.printStackTrace();
	}
	return list;
}

public String getClientdtails(String uhid) {
	PreparedStatement ps= null;
	String res="";
	try {
		String sql="select id, firstname,surname,gender,dob from apm_patient where abrivationid='"+uhid+"' ";
		ps= connection.prepareStatement(sql);
		ResultSet rs= ps.executeQuery();
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		UserProfileDAO userProfileDAO= new JDBCUserProfileDAO(connection);
		while(rs.next()){
			String x=rs.getString(1);
			String y=rs.getString(2);
			String z=rs.getString(3);
			String e=DateTimeUtils.getAge1(rs.getString(5));
			String f=rs.getString(4);
			String arr[]= ipdDAO.getFromIpdOrOpd(rs.getString(1));
			String dr="Dr. "+ userProfileDAO.getName(arr[0]);
			  UserProfile userProfile=userProfileDAO.getUserprofileDetails(Integer.parseInt(arr[0]));
			  
			res=x+"~"+y+"/"+z+"~"+dr+"/ ("+userProfile.getSpecialization()+")"+"~"+f+"~"+e+"~"+userProfile.getDiscription();
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}			
	

private int investigationInvByreq(String reqid,int pkgid){
	int res=0;
	PreparedStatement ps= null;
	try {
		String sql="select id from apm_client_parent_investigation where invreq='"+reqid+"' and pkg ='"+pkgid+"'";
		ps= connection.prepareStatement(sql);
		ResultSet rs= ps.executeQuery();
		while(rs.next()){
			int checkChargeRaised = checkChargeRaised(rs.getInt(1));
			if(checkChargeRaised==1){
				return 1;
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}

private int investigationInvByreqInvid(String reqid,int pkgid){
	int res=0;
	PreparedStatement ps= null;
	try {
		String sql="select id from apm_client_parent_investigation where invreq='"+reqid+"' and pkg ='"+pkgid+"'";
		ps= connection.prepareStatement(sql);
		ResultSet rs= ps.executeQuery();
		while(rs.next()){
			int invid = getChargedInvoiceid(rs.getInt(1));
			return invid;
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}

public ArrayList<Investigation> getInvestigationsFrompakages(String reqid, String pkgid) {
	ArrayList<Investigation> list= new ArrayList<Investigation>();
	PreparedStatement ps= null;
	try {
		StringBuffer buffer=  new StringBuffer();
		buffer.append(" select  apm_investigation_type.name from apm_client_parent_investigation   ");
		buffer.append("  inner join apm_investigation_type on apm_client_parent_investigation.invsttypeid= apm_investigation_type.id where ");
		buffer.append("  apm_client_parent_investigation.invreq='"+reqid+"' and apm_client_parent_investigation.pkg ='"+pkgid+"' ");
		ps= connection.prepareStatement(buffer.toString());
		
		ResultSet rs= ps.executeQuery();
		while(rs.next()){
			Investigation inv=new Investigation();
			inv.setName(rs.getString(1));
			list.add(inv);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return list;
}

public ArrayList<ThirdParty> getTpListofInvestigation() {
	ArrayList<ThirdParty> list= new ArrayList<ThirdParty>();
	PreparedStatement ps= null;
	try{
		String sql=" select id , company_name from apm_third_party_details where maintp='1'";
		ps= connection.prepareStatement(sql);
		ResultSet rs= ps.executeQuery();
		while(rs.next()){
			ThirdParty tp= new ThirdParty();
			tp.setId(rs.getInt(1));
			tp.setName(rs.getString(2));
			list.add(tp);
		}
	}catch (Exception e) {
		e.printStackTrace();
	}
	return list;
}

public String getInvestTypelistByTpid(String tpid) {
	String result="";
	StringBuffer buffer= new StringBuffer();
	PreparedStatement ps= null;
	try {
		String sql=" select id,name from tp_invtype where tp_comp_id='"+tpid+"'";
		ps= connection.prepareStatement(sql);
		ResultSet rs= ps.executeQuery();
		buffer.append("<label>Select TP Investigation</label>");
		buffer.append(" <select name='tpinvstname' id='tpinvstname' class='form-control chosen-select'> ");
		buffer.append("<option value='' selected>Choose an Option </option> ");
		while(rs.next()){
			buffer.append("<option value='"+rs.getInt(1)+"'>"+rs.getString(2)+"</option>");
		}
		buffer.append(" </select > ");
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return buffer.toString();
}

public String getShortnameofTP(String tpid) {
	String result=null;
	String sql="select shortname from apm_third_party_details where id='"+tpid+"'";
	PreparedStatement ps= null;
	
	try {
	ps= connection.prepareStatement(sql);
	ResultSet rs= ps.executeQuery();
	while(rs.next()){
		result= rs.getString(1);
	}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}

public int createColofInvst(String column) {
	PreparedStatement ps= null;
	try {
		ps =connection.prepareStatement("  ALTER TABLE `apm_investigation_type` ADD COLUMN `"+column+"` LONGTEXT DEFAULT NULL  ");
		int x=ps.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return 0;
}

public int updateTPInvsetigationName(String tpinvname, String id,String col) {
	PreparedStatement ps= null;
	try {
		ps= connection.prepareStatement("  update apm_investigation_type set "+col+"='"+tpinvname+"' where id='"+id+"' ");
		int x= ps.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return 0;
}
private int getChargeApmt(String invstype){
	int res= 0;
	PreparedStatement ps= null;
	try{
		ps= connection.prepareStatement("  select  ");
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}

public String getInvdepartment(String invsttype) {
	PreparedStatement preparedStatement = null;
	String result = "";
	String sql = "SELECT jobtitle FROM apm_investigation_type inner join job_title on "
			+ " job_title.id = apm_investigation_type.department where "
			+ " apm_investigation_type.name = '"+invsttype+"' ";
	
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
			}
			
					
		}catch (Exception e) {
			// TODO: handle exception
		}
	return result;
}

public String getDefaultRemark(String invsttypeid) {
	String result="";
	PreparedStatement ps= null;
	try {
		ps= connection.prepareStatement(" select defaultremark from apm_investigation_type where id='"+invsttypeid+"'");
		ResultSet rs= ps.executeQuery();
		while(rs.next()){
			result=rs.getString(1);
		}
		if(result==null){
			result="";
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}

public  int  setChargesRaisedStatus(String invstid){
	int rews=0;
	try {
		PreparedStatement ps= connection.prepareStatement(" update apm_client_parent_investigation set payment_status_new=1 where id in ("+invstid+") ");
		rews=ps.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return rews;
}

public ArrayList<Investigation> getinvestrevenuenCountReport(String fromdate, String todate) {
	ArrayList<Investigation> list= new ArrayList<Investigation>();
	try {
		StringBuffer buffer= new StringBuffer();
		buffer.append(" select sum(quantity), sum(apm_invoice_assesments.charge* quantity),apm_investigation_type.name,apm_investigation_section.name from apm_invoice_assesments  ");
		buffer.append(" inner join apm_investigation_type on apm_investigation_type.name= apm_invoice_assesments.apmtType  ");
		buffer.append(" inner join apm_investigation_section on apm_investigation_section.id= apm_investigation_type.sectionid  ");
		buffer.append(" where  apm_invoice_assesments.charge!='0' and apm_invoice_assesments.charge!='0.00' and apm_invoice_assesments.commencing between '"+DateTimeUtils.getCommencingDate1(fromdate)+"' and '"+DateTimeUtils.getCommencingDate1(todate)+"' ");
		buffer.append(" group by apm_investigation_type.name ");
		
		PreparedStatement ps= connection.prepareStatement(buffer.toString());
		ResultSet rs= ps.executeQuery();
		while(rs.next()){
			Investigation investigation= new Investigation();
			investigation.setCount(rs.getInt(1));
			investigation.setAmmount(rs.getString(2));
			investigation.setName(rs.getString(3));
			investigation.setDepartment(rs.getString(4));
			list.add(investigation);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return list;
}

public Investigation getApmtTypeName(String id) {
	String name="";
	Investigation investigation= new Investigation();
	try {
		String sql="select apmtType,inves_reqid from apm_invoice_assesments where id='"+id+"'";
		PreparedStatement ps=connection.prepareStatement(sql);
		ResultSet rs= ps.executeQuery();
		while(rs.next()){
			
			investigation.setName(rs.getString(1));
			investigation.setInvreq(rs.getString(2));
			investigation.setId(invewsttypeId(rs.getString(1)));
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return investigation;
}
private int invewsttypeId(String name){
	int id=0;
	try {
		String sql=" select id from apm_investigation_type where name='"+name+"'";
		PreparedStatement ps =connection.prepareStatement(sql);
		ResultSet rs= ps.executeQuery();
		while(rs.next()){
			id= rs.getInt(1);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return  id;
}

public int updateInvstDate(String invreqid, int investypeid, String date) {
	try {
		PreparedStatement ps = connection.prepareStatement("  update apm_client_parent_investigation set lastmodified='"+date+"',dateofupdated='"+date+"',dateofcompleted='"+date+"',compdate='"+date+"' where invsttypeid='"+investypeid+"'  and invreq='"+invreqid+"'  ");
		int x= ps.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return 0;
}

public int updateapmtAsssInvstDate(String chargename, String invreqid,String date) {
	int res=0;
	try {
		PreparedStatement ps= connection.prepareStatement("update apm_invoice_assesments set commencing='"+date+"', tpcommencing='"+date+" 00:00:00' where apmtType='"+chargename+"' and inves_reqid='"+invreqid+"' ");
	res=ps.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return 0;
}

private String getInvewstsectionID(String invstypeid){
	String res="0";
	try {
		PreparedStatement ps=connection.prepareStatement("select sectionid from apm_investigation_type where id='"+invstypeid+"'");
		ResultSet rs= ps.executeQuery();
		while(rs.next()){
			res= ""+rs.getInt(1);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}

public int saveChargesToInvestigation(String charge, String invstid, String chargeid,String invreqid) {
	int res=0;
	try {
		PreparedStatement ps= connection.prepareStatement("  update apm_client_parent_investigation set charge_id='"+chargeid+"',charge_saved='"+charge+"' where invreq='"+invreqid+"'");
		res=ps.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}

	return res;
}

public String getListOFIpdInvestigation(String ipdid) {
	StringBuffer list=new StringBuffer();
	try {
	
		StringBuffer buffer= new StringBuffer();
		buffer.append(" select apm_investigation_type.name,lastmodified,apm_client_parent_investigation.reporttype,apm_client_parent_investigation.remark,apm_client_parent_investigation.department,apm_client_parent_investigation.id , lastmodified from apm_client_parent_investigation ");
		buffer.append(" inner join apm_investigation_type on apm_investigation_type.id=invsttypeid ");
		buffer.append("  where ipdid='"+ipdid+"' and  apm_client_parent_investigation.status='1' ");
		PreparedStatement ps= connection.prepareStatement(buffer.toString());
		ResultSet rs= ps.executeQuery();
		buffer=list;
		while (rs.next()) {
			String selectedid=""+rs.getInt(6);
			String type=DateTimeUtils.isNull(rs.getString(3));
			String remark=DateTimeUtils.removeBreaks(rs.getString(4));
			String dt[]=rs.getString(7).split(" ");
			String modfieddate="";
			modfieddate= DateTimeUtils.getCommencingDate1(dt[0])+" "+dt[1];
				if(!remark.equals("")){
					if(type.equals("Text")){
						buffer.append("<h6><b>"+rs.getString(1)+"</b>    ("+modfieddate+")  </h6>");
						buffer.append("Impression :");
						buffer.append("<p>"+remark.toString()+"</p>");
					}
					
					
					
				}
			}
		buffer.append(getInvestigationInIpdList(ipdid));
		list= buffer;
	} catch (Exception e) {
		e.printStackTrace();
	}
	return list.toString();
}

private String getChargeOfInvestForOutSource(String name, String invreqid){
String res="0";
try {
	String sql="SELECT invoiceid,(charge*quantity) FROM apm_invoice_assesments where apmtType= ? and inves_reqid='"+invreqid+"' ";
	PreparedStatement ps= connection.prepareStatement(sql);
	ps.setString(1, name);
	ResultSet rs= ps.executeQuery();
	while(rs.next()){
		res=rs.getInt(2)+"~"+rs.getString(1);
	}
} catch (Exception e) {
	e.printStackTrace();
}
return res;
} 

private String paymentidPhysical(String invoiceid){
	String res="0";
	try {
		PreparedStatement ps= connection.prepareStatement("select id from his_payment_record_physical where invoiceid='"+invoiceid+"'");
		ResultSet rs= ps.executeQuery();
		while(rs.next()){
			res=rs.getString(1);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}

public ArrayList<Investigation> getAllInvestigationRepoerTypeWise(String clientid,String ipdid) {
	ArrayList<Investigation> list= new ArrayList<Investigation>();
	try {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    Calendar cal = Calendar.getInstance();
	    String date = dateFormat.format(cal.getTime()); 
		StringBuffer buffer= new StringBuffer();
		buffer.append("   select apm_client_parent_investigation.id,apm_investigation_type.name,apm_client_parent_investigation.reporttype,remark,defaultremark,created_datetime,deleted from apm_client_parent_investigation ");
		buffer.append("  inner join apm_investigation_type on apm_investigation_type.id=apm_client_parent_investigation.invsttypeid  ");
		if(ipdid.equals("")){
			buffer.append("   where created_datetime between '"+date+" 00:00:00' and '"+date+" 23:59:59'  and deleted_datetime is null and apm_client_parent_investigation.clientid='"+clientid+"'  order by apm_client_parent_investigation.reporttype ");
		}
		else{
			buffer.append("   where ipdid='"+ipdid+"' order by apm_client_parent_investigation.reporttype ");
		}
		
		PreparedStatement ps=connection.prepareStatement(buffer.toString());
		ResultSet rs= ps.executeQuery();
		while (rs.next()) {
			Investigation investigation= new Investigation();
			ArrayList<Investigation> invstinnerlist= new  ArrayList<Investigation>();
			invstinnerlist=getSelectedInvestigationData(rs.getString(1));
			investigation.setId(rs.getInt(1));
			investigation.setInvsttype(rs.getString(2));
			
			investigation.setFindinglist(invstinnerlist);
			investigation.setReporttype(rs.getString(3));
			String remark=rs.getString(4);
			if(remark==null||remark.equals("")){
				remark=rs.getString(5);
			}
			if(remark==null){
				remark="";
			}
			if(investigation.getReporttype().equals("Text")){
				String sectionid=getInvstigationSectionId(Integer.parseInt(rs.getString(1)));
				ArrayList<Master> templateList= getAllInvestigationTemplateList(sectionid);
				investigation.setTemplatelist(templateList);
			}
			
			investigation.setRemark(remark);
			String datereq= rs.getString(6);
			if(datereq!=null){
				investigation.setDate(DateTimeUtils.getCommencingDate1(datereq.split(" ")[0])+" "+datereq.split(" ")[1]);
			}else{
				investigation.setDate("");
			}
			investigation.setDeleted(""+rs.getInt(7));
			//Make it Collected 
			updateCollectDateandStatus(rs.getString(1),date);
			list.add(investigation);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return list;
}

public ArrayList<Investigation> getInvestigationTat(String fromDate, String toDate,String invsttype, String filter_status, String filter_ward,String isdeleted,Pagination pagination,LoginInfo loginInfo) {
	ArrayList<Investigation> list = new ArrayList<Investigation>();
	try {
PreparedStatement preparedStatement = null;
 		
 		
 		UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
 		UserProfile userProfile = userProfileDAO.getUserprofileDetails(loginInfo.getId());
 		
 		String labname = "";
 		 

		if (toDate != null) {
			toDate = toDate + " 23:59:59";
		}

		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT clientid,practitionerid,conditionid,advoice,english,regional,hindi,prepay,postpay,other, ");
		sql.append("apm_client_parent_investigation.lastmodified,apm_client_parent_investigation.reporttype,apm_client_parent_investigation.id, ");
		sql.append("ipdid,upstatus,apm_client_parent_investigation.status,charginvoiceid,dateofupdated,dateofcompleted,compstatus,compdate,deleted,outsourceid,pkg,apm_investigation_type.name ,payment_status_new ");
		sql.append("FROM apm_client_parent_investigation ");
		sql.append("inner join apm_patient on apm_patient.id = apm_client_parent_investigation.clientid ");
		sql.append("inner join apm_investigation_type on apm_investigation_type.id = apm_client_parent_investigation.invsttypeid ");
		//sql.append("inner join apm_investigation_section on apm_investigation_section.id = apm_investigation_type.sectionid ");
		sql.append("where apm_client_parent_investigation.lastmodified between '"+ fromDate+ "' and '"+ toDate+"' ");
		
		
		
		if(!invsttype.equals("")){
			sql.append("and invsttypeid='"+invsttype+"' ");
		}
		
	
		if(!filter_ward.equals("0")){
			if(filter_ward.equals("IPD")){
				sql.append("and ipdid!= 0 and ipdid is not null ");
			}else if(filter_ward.equals("OPD")){
				sql.append("and (ipdid=0 or ipdid is null) ");
			}else if(filter_ward.equals("URGENT")){
				sql.append("and ipdid!= 0 and ipdid is not null ");
			}
		}
		
		if(!filter_status.equals("0")){
			if(filter_status.equals("1")){
				sql.append("and upstatus=0 and compstatus=0 ");
			}else if(filter_status.equals("2")){
				sql.append("and upstatus=0 and compstatus=1 ");
			}else if(filter_status.equals("3")){
				sql.append("and upstatus=1 and compstatus=1 and apm_client_parent_investigation.status=0 ");
			}else if(filter_status.equals("4")){
				sql.append("and apm_client_parent_investigation.status=1 ");
			}
		}
		
		
		if(!isdeleted.equals("")){
			sql.append(" and  apm_client_parent_investigation.deleted='"+isdeleted+"' ");
		}
		
		sql.append("order by id desc ");
		String sql1 = pagination.getSQLQuery(sql.toString());
		
			preparedStatement = connection.prepareStatement(sql1);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Investigation investigation = new Investigation();
				investigation.setClientId(rs.getString(1));
				investigation.setConditionid(rs.getString(3));
				investigation.setAdvoice(rs.getString(4));
				investigation.setPrepay(rs.getString(8));
				investigation.setPostpay(rs.getString(9));
				investigation.setOtherpay(rs.getString(10));
				investigation.setPrectionerid(rs.getString(2));

				if (rs.getString(11) != null) {
					investigation.setDate(DateTimeUtils.getDBDate(rs
							.getString(11)));
				} else {
					investigation.setDate("");
				}

				investigation.setInvstreporttype(rs.getString(12));
				investigation.setId(rs.getInt(13));
				String invreq1 = getInveReqId(investigation.getId());
				
				int checkChargeRaised =0, chargeid=0 ;
				if(rs.getInt(24)!=0){
					if(rs.getInt(26)==0){
						checkChargeRaised=investigationInvByreq(invreq1,rs.getInt(24));
					}else{
						checkChargeRaised=1;
					}
					
					chargeid=investigationInvByreqInvid(invreq1,rs.getInt(24));
				}else{
					if(rs.getInt(26)==0){
						checkChargeRaised= checkChargeRaised(investigation.getId());
					}else{
						checkChargeRaised=1;
					}
					
					chargeid=getChargedInvoiceid(investigation.getId());
				}
				
				
				investigation.setCheckChargeRaised(checkChargeRaised);

				 
				int checkInvoiceCreated = checkInvoiceCreated(chargeid);
				investigation.setCheckInvoiceCreated(checkInvoiceCreated);

				/*String investType = getInvestType(investigation.getId());*/
				investigation.setInvsttype(rs.getString(25));
				
				String sectionid = getInvstigationSectionId(investigation.getId());
				if(sectionid==null){
					sectionid = "0";
				}
				String invreq = getInveReqId(investigation.getId());
				investigation.setInvreq(invreq);
				
				investigation.setSectionid(sectionid);

				String ipdid = rs.getString(14);
				BedDao bedDao = new JDBCBedDao(connection);
				IpdDAO ipdDAO = new JDBCIpdDAO(connection);
				Bed bed = bedDao.getEditIpdData(ipdid);

				if (ipdid == null || ipdid.equals("0")) {
					investigation.setIpdid("0");
				} else {
					investigation.setIpdid(ipdid);
				}

				investigation.setUpstatus(rs.getString(15));
				investigation.setStatus(rs.getString(16));
				investigation.setCharginvoiceid(rs.getInt(17));
				investigation.setUpdate_date(rs.getString(18));
				investigation.setComplete_date(rs.getString(19));
				investigation.setCollectstatus(rs.getString(20));
				investigation.setCollect_date(rs.getString(21));
				investigation.setDeleted(rs.getString(22));
				investigation.setOutsourceid(rs.getString(23));
				
				if(investigation.getCollect_date()!=null){
					if(!investigation.getCollect_date().equals("")){
						String dd= DateTimeUtils.getDBDate(investigation.getCollect_date());
						investigation.setCollect_date(dd);
					}
				} else {
					investigation.setCollect_date("");
				}
				
				if(investigation.getUpdate_date()!=null){
					
					if(!investigation.getUpdate_date().equals("")){
						String dd= DateTimeUtils.getDBDate(investigation.getUpdate_date());
						investigation.setUpdate_date(dd);
					}
				}
				if(investigation.getComplete_date()!=null){
					
					if(!investigation.getComplete_date().equals("")){
						String dd= DateTimeUtils.getDBDate(investigation.getComplete_date());
						investigation.setComplete_date(dd);
					}
				}


				String wardname = ipdDAO.getIpdWardName(bed.getWardid());
				investigation.setWardname(wardname);
				String bedname = ipdDAO.getIpdBedName(bed.getBedid());
				investigation.setBedname(bedname);
				String urgentward = checkICUorCasulty(bed.getWardid());
				if (!urgentward.equals("")) {
					investigation.setUrgentward("1");
				}

				String clientid = investigation.getClientId();
				ClientDAO clientDAO = new JDBCClientDAO(connection);
				Client client = clientDAO.getClientDetails(clientid);

				String fullname = client.getTitle() + " "
						+ client.getFirstName() + " " + client.getLastName();
				String ageandgender = "";
				if (client.getDob() != null) {
					ageandgender = DateTimeUtils.getAge(client.getDob())
							+ " / " + client.getGender();
				}
				
				
				
				String selftp = client.getWhopay();
				
				if(selftp.equals(Constants.PAY_BY_THIRD_PARTY)){
					String tpid = client.getTypeName();
					
					
					if(!ipdDAO.checkifMainTp(tpid)){
						 
						String temptpid= ipdDAO.getFollowerTp(tpid); 
						if(temptpid!=null){
							
							if(!temptpid.equals("0")){
								 tpid=temptpid;  
							}
							
							ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
							ThirdParty thirdParty = thirdPartyDAO.getThirdPartyDetails(tpid);
							
							selftp = selftp + " {"+thirdParty.getCompanyName()+"} ";
							
						}
						
					}	
				}

				investigation.setSelftp(selftp);
				

				investigation.setFullname(fullname);
				investigation.setAgeandgender(ageandgender);
				investigation.setWhopay(client.getWhopay());
				investigation.setAbrivationid(client.getAbrivationid());
                investigation.setMobile(client.getMobNo());
				
				 userProfile = userProfileDAO
						.getUserprofileDetails(Integer.parseInt(investigation
								.getPrectionerid()));
				String practitionerName = userProfile.getInitial() + " "
						+ userProfile.getFirstname() + " "
						+ userProfile.getLastname();

				if (investigation.getPrectionerid().equals("0")) {
					practitionerName = clientDAO.getGPname(client.getGpid());
				}
				investigation.setPractitionerName(practitionerName);
				investigation.setPractitionerMob(userProfile.getMobile());
				investigation.setSpecialization(userProfile.getSpecialization());
                
				boolean isAttachment=false;
				if (isAttachment) {
					investigation.setIsAttachment("1");
				} else {
					investigation.setIsAttachment("0");
				}
				if (rs.getString(4)!=null) {
					if (!rs.getString(4).equals("")) {
						investigation.setValidnote("1");
					}else{
						investigation.setValidnote("0");
					}
				}else{
					investigation.setValidnote("0");
				}
				investigation.setFromdate(fromDate); 
				investigation.setTodate(toDate);
				
				ArrayList<Investigation> outSourceList = getOutSourceList(rs.getString(23));
				investigation.setOutSourceList(outSourceList);
				investigation.setOutsource(rs.getString(23));
				
				if(!DateTimeUtils.isNull(investigation.getComplete_date()).equals("")){
					if(!DateTimeUtils.isNull(investigation.getCollect_date()).equals("")){
						Date d1=new SimpleDateFormat("dd-MM-yyyy HH:mm").parse(investigation.getComplete_date());
						Date d2= new SimpleDateFormat("dd-MM-yyyy HH:mm").parse(investigation.getCollect_date());
						
						long diff=d2.getTime()- d1.getTime();
						long difference= (Math.abs((diff / (1000*60))));
						investigation.setNew_invistigation((int)difference);
					}
				}
				
				
				if(filter_ward.equals("URGENT")){
					String name = checkICUorCasulty(bed.getWardid());
					if (!name.equals("")) {
						investigation.setUrgentward("1");
						list.add(investigation);
					}
				}else{
					list.add(investigation);
				}
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return list;
	}

public int getInvestigationTatcount(String fromDate, String toDate, String invsttype, String filter_status, String filter_ward, String isdeleted, LoginInfo loginInfo) {
	int res=0;
	if (toDate != null) {
		toDate = toDate + " 23:59:59";
	}

	try{
	StringBuffer sql = new StringBuffer();
	sql.append("SELECT count(*) ");
	sql.append("FROM apm_client_parent_investigation ");
	sql.append("inner join apm_patient on apm_patient.id = apm_client_parent_investigation.clientid ");
	sql.append("inner join apm_investigation_type on apm_investigation_type.id = apm_client_parent_investigation.invsttypeid ");
	//sql.append("inner join apm_investigation_section on apm_investigation_section.id = apm_investigation_type.sectionid ");
	sql.append("where apm_client_parent_investigation.lastmodified between '"+ fromDate+ "' and '"+ toDate+"' ");
	
	
	
	if(!invsttype.equals("")){
		sql.append("and invsttypeid='"+invsttype+"' ");
	}
	

	if(!filter_ward.equals("0")){
		if(filter_ward.equals("IPD")){
			sql.append("and ipdid!= 0 and ipdid is not null ");
		}else if(filter_ward.equals("OPD")){
			sql.append("and (ipdid=0 or ipdid is null) ");
		}else if(filter_ward.equals("URGENT")){
			sql.append("and ipdid!= 0 and ipdid is not null ");
		}
	}
	
	if(!filter_status.equals("0")){
		if(filter_status.equals("1")){
			sql.append("and upstatus=0 and compstatus=0 ");
		}else if(filter_status.equals("2")){
			sql.append("and upstatus=0 and compstatus=1 ");
		}else if(filter_status.equals("3")){
			sql.append("and upstatus=1 and compstatus=1 and apm_client_parent_investigation.status=0 ");
		}else if(filter_status.equals("4")){
			sql.append("and apm_client_parent_investigation.status=1 ");
		}
	}
	
	
	if(!isdeleted.equals("")){
		sql.append(" and  apm_client_parent_investigation.deleted='"+isdeleted+"' ");
	}
	
	PreparedStatement preparedStatement=null;
		preparedStatement = connection.prepareStatement(sql.toString());
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			res=rs.getInt(1);
		}
}catch (Exception e) {
	e.printStackTrace();
}
	return res;
}

public Investigation getparentdata(int saveparent) {
	Investigation investigation=new Investigation();
	try{
	 StringBuffer sql = new StringBuffer();
	  sql.append("SELECT apm_client_parent_investigation.id,apm_client_parent_investigation.reporttype,apm_investigation_type.sectionid,apm_client_parent_investigation.invreq,apm_client_parent_investigation.lastmodified FROM apm_client_parent_investigation ");
	  sql.append("inner join apm_investigation_type on apm_investigation_type.id = apm_client_parent_investigation.invsttypeid where apm_client_parent_investigation.id="+saveparent+" ");
	  PreparedStatement preparedStatement=null;
		preparedStatement = connection.prepareStatement(sql.toString());
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			investigation.setParentid(rs.getInt(1));
			investigation.setReporttype(rs.getString(2));
			investigation.setSectionid(rs.getString(3));
			investigation.setInvreq(rs.getString(4));
			investigation.setDate(rs.getString(5));
		}
	}catch (Exception e) {
		e.printStackTrace();
	}
	return investigation;
}

public ArrayList<Master> getDepartmentinvListPrint(String invreq,String deptid) {
	PreparedStatement preparedStatement = null;
	ArrayList<Master>list = new ArrayList<Master>();
	StringBuffer sql = new StringBuffer();
	
	sql.append("SELECT apm_client_parent_investigation.id,apm_investigation_type.department FROM apm_client_parent_investigation ");
	sql.append("inner join apm_investigation_type on apm_investigation_type.id = apm_client_parent_investigation.invsttypeid ");
	sql.append("where invreq = "+invreq+" ");
	
	try{
		preparedStatement = connection.prepareStatement(sql.toString());
		ResultSet rs = preparedStatement.executeQuery();
		Master master = new Master();
		String dpartmentname = getJobTitleInvDepartmentName(deptid);
		master.setName(dpartmentname);
		ArrayList<Investigation>invTypeList1 = new ArrayList<Investigation>();
		while(rs.next()){
			master.setId(rs.getInt(1));
			ArrayList<Investigation>invTypeList = getInvTypeList(master.getId());
			invTypeList1.addAll(invTypeList);
			//for save and print
			master.setBghhead(true);
			master.setInvTypeList(invTypeList1);
		}
		list.add(master);
	}catch (Exception e) {
		// TODO: handle exception
	}
	
	return list;
}

private String getJobTitleInvDepartmentName(String deptid) {
	PreparedStatement preparedStatement = null;
	String result = "";
	String sql = "select jobtitle from job_title where id="+deptid+" ";
	
	try{
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs  = preparedStatement.executeQuery();
		if(rs.next()){
			result = rs.getString(1);
		}
		
	}catch (Exception e) {
		// TODO: handle exception
	}
	return result;
}

public String getdeptinvid(String invreq) {
	String res="";
	try {
		String sql="SELECT distinct(apm_investigation_type.department) FROM apm_client_parent_investigation"
				+ " inner join apm_investigation_type on apm_investigation_type.id = apm_client_parent_investigation.invsttypeid "
				+ "where invreq = '"+invreq+"'   ";
		PreparedStatement ps= connection.prepareStatement(sql);
		ResultSet rs= ps.executeQuery();
		while(rs.next()){
			if(res.equals("")){
				res=rs.getString(1);
			}else{
				res=res+","+rs.getString(1);
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}

public String getInvestigationInIpdList(String ipdid) {
	String list="";
	try {
		
		StringBuffer buffer= new StringBuffer();
		buffer.append(" select apm_investigation_type.name,invsttypeid,apm_client_parent_investigation.reporttype,apm_client_parent_investigation.remark from apm_client_parent_investigation ");
		buffer.append(" inner join apm_investigation_type on apm_investigation_type.id=invsttypeid ");
		buffer.append("  where ipdid='"+ipdid+"' and deleted='0' and apm_client_parent_investigation.reporttype ='Numerical' and dateofcompleted is not null  group by invsttypeid ");
		PreparedStatement ps= connection.prepareStatement(buffer.toString());
		ResultSet rs=ps.executeQuery();
		StringBuffer bud= new StringBuffer();
		
		while (rs.next()) {
			bud.append("<h4><b>"+rs.getString(1)+"</b></h4>");
			bud.append("<table class='my-table lkclass'>");
			String th=getDateWiseInvestigationTableHeader( ipdid, rs.getString(2));
			bud.append(th);
			String body="";
			String typs=getNameListForRow(rs.getString(2));
			for(String name:typs.split("���")){
				boolean blnkflag=false;
				String chkdtabalnk=getRowToTheTbleInvestigationWthoutUnit(rs.getString(2), ipdid, name);
				for(String val:chkdtabalnk.split("���")){
					val=DateTimeUtils.isNull(val);
					if(val.equals("")||val.equals("0")||val.equals("0.00")||val.equals(" ")){
						blnkflag=true;
					}else{
						blnkflag=false;
					}
				}
				if(!blnkflag){
					bud.append("<tr>");
					bud.append("<td>"+name+"</td>");
					String datarow=getRowToTheTbleInvestigation(rs.getString(2), ipdid, name);
					for(String val:datarow.split("���")){
						bud.append("<td>"+val+"</td>");
					}
					bud.append("</tr>");
				}
				
			}
			bud.append("</table >");
		}
		
		list=bud.toString();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return list;
}

private String getDateWiseInvestigationTableHeader(String ipdid,String invsttypeid){
	String table="";
	try {
		StringBuffer buffer= new StringBuffer();
		buffer.append("  select distinct(id),compdate from apm_client_parent_investigation  ");
		buffer.append(" where deleted='0' and status ='1'  and  ipdid='"+ipdid+"' and invsttypeid='"+invsttypeid+"' ");
		buffer.append("    ");
		buffer.append("    ");
		PreparedStatement ps= connection.prepareStatement(buffer.toString());
		String datelist="";
		int i=0;
		ResultSet rs=ps.executeQuery();
		while (rs.next()) {
			String res=DateTimeUtils.getCommencingDate1(rs.getString(2).split(" ")[0]);
			if(i==0){
				datelist=res;
			}else{
				datelist=datelist+","+res;
			}
			i=i+1;
		}
		table="<tr><th>Names</td>";
		for(String x:datelist.split(",")){
			table=table+"<th>"+x+"</td>";
		}
		table=table+"</tr>";
	} catch (Exception e) {
		e.printStackTrace();
	}
	return table;
}

private String getRowToTheTbleInvestigation(String invsttypeid,String ipdid,String name){
	String row="";
	try {
		StringBuffer buffer= new StringBuffer();
		buffer.append("   select concat(obsvalue,'  ',invstUnit),obsvalue from apm_client_investigation ");
		buffer.append(" inner join apm_client_parent_investigation on apm_client_parent_investigation.id=parentid where  invsttypeid='"+invsttypeid+"' and  ipdid='"+ipdid+"' and invstname='"+name+"' and apm_client_parent_investigation.deleted='0' and apm_client_parent_investigation.status='1' ");
		PreparedStatement ps= connection.prepareStatement(buffer.toString());
		String valuelist="";
		int i=0;
		ResultSet rs=ps.executeQuery();
		while (rs.next()) {
			String res=rs.getString(1);
			String rr=DateTimeUtils.isNull(rs.getString(2));
			if(rr.equals("")){
				res=" "+rr;
			}
			if(i==0){
				valuelist=res;
			}else{
				valuelist=valuelist+"���"+res;
			}
			i=i+1;
		}
		row=valuelist;
	} catch (Exception e) {
		e.printStackTrace();
	}
	return row;
}
private String getRowToTheTbleInvestigationWthoutUnit(String invsttypeid,String ipdid,String name){
	String row="";
	try {
		StringBuffer buffer= new StringBuffer();
		buffer.append("   select obsvalue,obsvalue from apm_client_investigation ");
		buffer.append(" inner join apm_client_parent_investigation on apm_client_parent_investigation.id=parentid where  invsttypeid='"+invsttypeid+"' and  ipdid='"+ipdid+"' and invstname='"+name+"' and apm_client_parent_investigation.deleted='0' and apm_client_parent_investigation.status='1' ");
		PreparedStatement ps= connection.prepareStatement(buffer.toString());
		String valuelist="";
		int i=0;
		ResultSet rs=ps.executeQuery();
		while (rs.next()) {
			String res=rs.getString(1);
			String rr=DateTimeUtils.isNull(rs.getString(2));
			if(rr.equals("")){
				res=" "+rr;
			}
			if(i==0){
				valuelist=res;
			}else{
				valuelist=valuelist+"���"+res;
			}
			i=i+1;
		}
		row=valuelist;
	} catch (Exception e) {
		e.printStackTrace();
	}
	return row;
}
private String getNameListForRow(String typeid){
	String names="";
	try {
		StringBuffer buffer= new StringBuffer();
		buffer.append("   select name from apm_investigation_name where invgrpid ");
		buffer.append(" ='"+typeid+"' ");
		PreparedStatement ps= connection.prepareStatement(buffer.toString());
		String typelist="";
		int i=0;
		ResultSet rs=ps.executeQuery();
		while (rs.next()) {
			String res=rs.getString(1);
			if(i==0){
				typelist=res;
			}else{
				typelist=typelist+"���"+res;
			}
			i=i+1;
		}
		names=typelist;
	} catch (Exception e) {
		e.printStackTrace();
	}
	return names;
}

public int updateCompStatusAndDate(String parentId, String datetime) {
	
	try {
		String date=datetime.split(" ")[0];
		PreparedStatement ps= connection.prepareStatement(" update apm_client_parent_investigation set compdate='"+date+"' , dateofcompleted='"+datetime+"' where id='"+parentId+"'");
		int x=ps.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return 0;
}

public double getoutsourceAmount(String invid, String val,String invtypeid) {
	double charges=0;
	try{
		
		 StringBuffer sql = new StringBuffer();
		  sql.append("SELECT charge FROM outsource_investigation_charges where invstypeid="+invtypeid+" and vendor="+val+"");
		  PreparedStatement preparedStatement=null;
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
			charges=rs.getDouble(1)	;
			}
}catch (Exception e) {
	e.printStackTrace();
}
	return charges;
	
}

public int updateOutsourceChargeAmount(String invreq,double charge) {
	int x=0;
	try {
		PreparedStatement ps= connection.prepareStatement(" update apm_invoice_assesments set charge="+charge+" where inves_reqid='"+invreq+"'");
		 x=ps.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return x;
}

public int getinvestigationIdByApmtName(String apmtName, String invstReqId,int pkgid) {
	int res=0;
	StringBuffer sql=new StringBuffer();
	try {
		  sql.append("select apm_client_parent_investigation.id, name from apm_client_parent_investigation inner join apm_investigation_type on apm_investigation_type.id=invsttypeid  where invreq='"+invstReqId+"'");
		if(pkgid==0) {
		  sql.append(" and and name = '"+apmtName+"'");
		}
		PreparedStatement ps= connection.prepareStatement(sql.toString());
		
		ResultSet rs=ps.executeQuery();
		while (rs.next()) {
			res=rs.getInt(1);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}

public ArrayList<Investigation> investigationCriticalValueReport(Investigation investigation) {
	ArrayList<Investigation> list= new ArrayList<Investigation>();
	String fromdate=DateTimeUtils.getCommencingDate1(investigation.getFromdate())+" 00:00:00";
	String todate=DateTimeUtils.getCommencingDate1(investigation.getTodate())+" 23:59:59";
	try {
		StringBuffer buffer= new StringBuffer();
		buffer.append(" select apm_client_parent_investigation.id,apm_client_parent_investigation.clientid,apm_client_parent_investigation.lastmodified,invsttype,ipdid,opdid,invstname,invstUnit,normvalue,obsvalue,apm_client_parent_investigation.practitionerid  ");
		buffer.append(" from apm_client_parent_investigation ");
		buffer.append(" inner join apm_client_investigation on apm_client_parent_investigation.id=apm_client_investigation.parentid ");
		buffer.append(" where reporttype='Numerical' and apm_client_parent_investigation.lastmodified between '"+fromdate+"' and '"+todate+"'  ");
		buffer.append(" and normvalue like '%-%'  ");
		investigation.setClientId(DateTimeUtils.isNull(investigation.getClientId()));
		if(!investigation.getClientId().equals("")){
			buffer.append(" and apm_client_parent_investigation.clientid='"+investigation.getClientId()+"'  ");
		}
		if(!investigation.getInvsttypeid().equals("")){
			buffer.append("  and invsttypeid='"+investigation.getInvsttypeid()+"' ");
		}
		if(investigation.getDepartment().equals("IPD")){
			buffer.append("  and ipdid!='0' ");
		}else if (investigation.getDepartment().equals("OPD")) {
			buffer.append("  and ipdid='0' ");
		}
	ClientDAO clientDAO= new JDBCClientDAO(connection);	
	PreparedStatement preparedStatement=connection.prepareStatement(buffer.toString());	
	ResultSet rs=preparedStatement.executeQuery();
	String clientId="";
	IpdDAO ipdDAO= new JDBCIpdDAO(connection);
	Client client2= new Client();
	UserProfileDAO userProfileDAO= new JDBCUserProfileDAO(connection);
	while (rs.next()) {
		Investigation investigation2= new Investigation();
		investigation2.setClientId(rs.getString(2));
		investigation2.setDate(DateTimeUtils.getCommencingDate1(rs.getString(3).split(" ")[0])+" "+(rs.getString(3).split(" ")[1]));
		investigation2.setObsvalue(rs.getString(10));
		investigation2.setNormvalue(rs.getString(9));
		String val[]=investigation2.getNormvalue().split("-");
		double min=DateTimeUtils.convertToDouble(val[0]);
		double max=DateTimeUtils.convertToDouble(val[1]);
		double obsval=DateTimeUtils.convertToDouble(investigation2.getObsvalue());
		if((min<obsval)&&(max>obsval)){
			continue;
		}
		investigation2.setInvsttype(rs.getString(4));
		investigation2.setInvstname(rs.getString(7));
		investigation2.setInvstUnit(rs.getString(8));
		investigation2.setIpdid(ipdDAO.getipdseqno(rs.getString(5)));
		//opdid
		investigation2.setFlag((rs.getString(6)));
		Client client= new Client();
		if(!investigation2.getClientId().equals(clientId)){
			client=clientDAO.getPatient(DateTimeUtils.convertToInteger(investigation2.getClientId()));
		}else{
			client= client2;
		}
		
		String user= userProfileDAO.getUserFullNameFromId(""+rs.getInt(11));
		investigation2.setPractitionerName(user);
		clientId=investigation2.getClientId();
		client2= client;
		
		investigation2.setClientDetalis(client);
		list.add(investigation2);
	}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return list;
}

public ArrayList<UserProfile> userList(String jobtitle) {
	ArrayList<UserProfile> list= new ArrayList<UserProfile>();
	try {
		String sql="  select id,initial,firstname,lastname from apm_user  ";
		PreparedStatement ps= connection.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		while (rs.next()) {
			UserProfile userProfile= new UserProfile();
			userProfile.setId(rs.getInt(1));
			userProfile.setName(rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
			list.add(userProfile);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return list;
}

public void updateSingUser(String invstids, String userid) {
	try {
		PreparedStatement ps=connection.prepareStatement(" update apm_client_parent_investigation set signuser =? where id in("+invstids+")");
		ps.setString(1, userid);
		int x=ps.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	
}

public ArrayList<Investigation> investigationNameListMaster(String invstTypeId) {
	ArrayList<Investigation> list = new ArrayList<Investigation>();
	
	try {
		String sql="select id, invgrpid, name, specimen, reporttype, unit, normvalue, charge, critical_value from apm_investigation_name "
				+ "where invgrpid="+invstTypeId;
		PreparedStatement ps= connection.prepareStatement(sql);
		ResultSet rs= ps.executeQuery();
		while (rs.next()) {
			Investigation investigation= new Investigation();
			investigation.setInvstname(rs.getString(3));
			investigation.setId(rs.getInt(1));
			investigation.setParentid(rs.getInt(2));
			investigation.setReporttype(rs.getString(4));
			investigation.setInvstUnit(rs.getString(6));
			investigation.setNormvalue(rs.getString(7));
			investigation.setCritical_value(DateTimeUtils.isNull(rs.getString(9)));
			list.add(investigation);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return list;
}


public ArrayList<Investigation> getNumericInvestigationListOfPatient(String fromDate, String todate, String clientId) {
	  StringBuffer sql = new StringBuffer();
	   fromDate=fromDate+" 00:00:00";
	   todate=todate+" 23:59:59";
	  sql.append("SELECT apm_client_parent_investigation.id,name,apm_client_parent_investigation.invsttypeid FROM apm_client_parent_investigation ");
	  sql.append("inner join apm_investigation_type on apm_investigation_type.id = apm_client_parent_investigation.invsttypeid ");
	  
	  sql.append(" where lastmodified between '"+ fromDate+ "' and '"+ todate+"' and clientid='"+clientId+"' and apm_client_parent_investigation.reporttype='Numerical' ");
	  
	  sql.append(" group by apm_investigation_type.id ");
	  ArrayList<Investigation> list= new ArrayList<>();
	  try {
		  PreparedStatement ps= connection.prepareStatement(sql.toString());
			ResultSet rs= ps.executeQuery();
			while (rs.next()) {
				Investigation investigation= new Investigation();
				investigation.setName(rs.getString(2));
				investigation.setInvsttypeid(rs.getString(3));
				investigation.setId(rs.getInt(1));
				list.add(investigation);
			}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return list;
}


public ArrayList<Master> getParentList(String clientId, String investigationTypeid,String 

fromDate, String todate){
	ArrayList<Master> list= new ArrayList<>();
	try {
		String sql=" SELECT apm_client_parent_investigation.id,invsttypeid,lastmodified from apm_client_parent_investigation"
				+ " where invsttypeid='"+investigationTypeid+"' and clientid='"+clientId+"' ";
		
		PreparedStatement ps= connection.prepareStatement(sql);
		ResultSet rs= ps.executeQuery();
		while (rs.next()) {
			Master master= new Master();
			master.setInvestigstion_id(rs.getString(1));
			master.setTestNameList(getSelectedInvestigationData(rs.getString(1)));
			master.setDate(rs.getString(3));
			list.add(master);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return list;
}

@Override
public String getListOFCFInvestigation(String ids) {

	StringBuffer list=new StringBuffer();
	try {
	
		StringBuffer buffer= new StringBuffer();
		buffer.append(" select apm_investigation_type.name,apm_client_parent_investigation.id from apm_client_parent_investigation ");
		buffer.append(" inner join apm_investigation_type on apm_investigation_type.id=invsttypeid ");
		buffer.append("  where apm_client_parent_investigation.id in ("+ids+") ");
		PreparedStatement ps= connection.prepareStatement(buffer.toString());
		ResultSet rs= ps.executeQuery();
		buffer=list;
		while (rs.next()) {
			StringBuffer data = getInvestigationChildCfData(rs.getString(2));
			if(data.toString().equals("")){
				if(buffer.toString().equals("")){
					buffer.append(""+rs.getString(1)+"");
				}else{
					buffer.append("<br>"+rs.getString(1)+"");
				}
			}else{
				if(buffer.toString().equals("")){
					buffer.append(""+data.toString()+"");
				}else{
					buffer.append("<br>"+data.toString()+"");
				}
			}
		}
		list= buffer;
	} catch (Exception e) {
		e.printStackTrace();
	}
	return list.toString();

}

private StringBuffer getInvestigationChildCfData(String string) {
	StringBuffer buffer= new StringBuffer();
	try {
		String sql ="select invsttype,invstname,value_cf,invstUnit from apm_client_investigation where parentid='"+string+"' and value_cf is not null and value_cf!='' and value_cf!='0';";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			String data = rs.getString(1)+" - "+rs.getString(2)+" - "+rs.getString(3)+""+DateTimeUtils.isNull(rs.getString(4));
			if(buffer.toString().equals("")){
				buffer.append(""+data+"");
			}else{
				buffer.append("<br>"+data+"");
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return buffer;
}

@Override
public ArrayList<Investigation> getallThirdpartynamelist() {
	PreparedStatement preparedStatement = null;
	ArrayList<Investigation>list = new ArrayList<Investigation>();
	try {
		String sql="select id,company_name from apm_third_party_details";
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()){
			Investigation inv=new Investigation();
			inv.setId(rs.getInt(1));
			inv.setThirdparty(rs.getString(2));
			list.add(inv);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return list;
}

@Override
public ArrayList<Investigation> getallwardnamelist() {
	PreparedStatement preparedStatement = null;
	ArrayList<Investigation>list = new ArrayList<Investigation>();
	try {
		String sql="select id,wardname from apm_ipd_ward";
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()){
			Investigation inv=new Investigation();
			inv.setId(rs.getInt(1));
			inv.setWardname(rs.getString(2));
			list.add(inv);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return list;
}

@Override
public String getShortnamebytpid(String tpid) {
	PreparedStatement preparedStatement=null;
	String result="";
	try {
		String sql="select shortname from apm_third_party_details where id='"+tpid+"'";
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()){
			
			result=rs.getString(1);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}

@Override
public int savealternatInvstnameByid(Investigation investigation) {
	PreparedStatement preparedStatement = null;
	int result = 0;
	String sql = "update apm_investigation_type set "+investigation.getShortname()+"='"+investigation.getChargename()+"' where id="+investigation.getInvestnameid()+"";

	try {
		preparedStatement = connection.prepareStatement(sql);
	
		result = preparedStatement.executeUpdate();

	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}

@Override
public int saveInvestigationdetail(Investigation investigation) {
	PreparedStatement preparedStatement = null;
	int result = 0;
	try {
		
		String sql="insert into apm_appointment_type(name,tpid,code,wardid,charges,chargeType) values (?,?,?,?,?,?)";
		preparedStatement=connection.prepareStatement(sql);
		String originalchargename=getOriginalChargename(investigation);
		preparedStatement.setString(1, originalchargename);
		preparedStatement.setString(2, investigation.getThirdparty());
		preparedStatement.setString(3, investigation.getCode());
		preparedStatement.setString(4, investigation.getWardname());
		preparedStatement.setString(5, investigation.getAmount());
		preparedStatement.setString(6, "INVESTIGATION");
		
		result=preparedStatement.executeUpdate();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}

private String getOriginalChargename(Investigation investigation) {
	PreparedStatement preparedStatement=null;
	String result="";
	try {
		String sql="select name from apm_investigation_type where "+investigation.getShortname()+"='"+investigation.getChargename()+"'";
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()){
			
			result=rs.getString(1);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}

@Override
public String getfakedatebyClientid(String fakclientid) {
	// TODO Auto-generated method stub
	
	PreparedStatement preparedStatement=null;
	String result="";
	try {
		String sql="select min(id),lastmodified from apm_client_parent_investigation where clientid= "+fakclientid+" ";
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()){
			
			result=rs.getString(2);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
	

}
public ArrayList<Investigation> getAllInvestigation(String fromdate,String todate,int ipdid,String opdid) {
	PreparedStatement preparedStatement = null;
	ArrayList<Investigation>list = new ArrayList<Investigation>();
	StringBuffer buffer=new StringBuffer();
	try {
		        buffer.append("select invstname,obsvalue,normvalue,apm_investigation_type.name,apm_client_parent_investigation.reporttype,remark,invstUnit from apm_client_parent_investigation ");
		        buffer.append("inner join apm_investigation_type on apm_investigation_type.id=apm_client_parent_investigation.invsttypeid ");
				buffer.append("inner join apm_client_investigation aci on aci.parentid=apm_client_parent_investigation.id ");
				if(ipdid!=0) {
				   buffer.append(" where ipdid="+ipdid+" and created_datetime between '"+fromdate+"' and '"+todate+"' order by apm_client_parent_investigation.reporttype ");

				}else {
			       buffer.append(" where opdid="+opdid+" and created_datetime between '"+fromdate+"' and '"+todate+"' order by apm_client_parent_investigation.reporttype ");
				}
				
	    preparedStatement = connection.prepareStatement(buffer.toString());
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()){
			Investigation inv=new Investigation();
			inv.setInvstname(rs.getString(1));
			inv.setObsvalue(rs.getString(2));
			inv.setNormvalue(rs.getString(3));
			inv.setName(rs.getString(4));
			inv.setReporttype(rs.getString(5));
			inv.setRemark(rs.getString(6));
			inv.setInvstUnit(rs.getString(7));
			list.add(inv);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return list;
}

@Override
public Investigation getFindingsbyid(String selectedid) {
	PreparedStatement pst=null;
	Investigation invst=new Investigation();
	try {
		String sql="select id,findings from apm_client_investigation where parentid='"+selectedid+"'";
		pst=connection.prepareStatement(sql);
		ResultSet rs=pst.executeQuery();
		while(rs.next()) {
			invst.setId(rs.getInt(1));
			invst.setFindings(rs.getString(2));
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return invst;
}

@Override
public Investigation getSignUser() {
	PreparedStatement pst=null;
	Investigation invst=new Investigation();
	try {
		
		StringBuffer buffer= new StringBuffer();
		buffer.append(" select apm_user.id,signuser from apm_user_access ");
		buffer.append(" inner join apm_user on apm_user.userid=apm_user_access.userid ");
		buffer.append("  where signuser=1 ");
		PreparedStatement ps= connection.prepareStatement(buffer.toString());
		
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			
			invst.setPractitionerName(rs.getString(1));
			invst.setSignuser_access(rs.getBoolean(2));
			
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	
	return invst;
}

@Override
public ArrayList<UserProfile> userList1(String updatedby) {
	ArrayList<UserProfile> list= new ArrayList<UserProfile>();
	try {
		String sql="  select id,initial,firstname,lastname from apm_user where id= '"+updatedby+"' ";
		PreparedStatement ps= connection.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		while (rs.next()) {
			UserProfile userProfile= new UserProfile();
			userProfile.setId(rs.getInt(1));
			userProfile.setName(rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
			list.add(userProfile);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return list;
	
}

}
