package com.apm.DiaryManagement.eu.blogic.jdbc;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import org.apache.struts2.convention.annotation.Results;

import com.a.a.a.g.m.p;
import com.apm.Accounts.eu.bi.AccountsDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAccountsDAO;
import com.apm.Accounts.eu.entity.Accounts;
import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.AssesmentForms.eu.bi.AssessmentFormDAO;
import com.apm.AssesmentForms.eu.blogic.jdbc.JDBCAssessmentFormDAO;
import com.apm.Diagnosis.eu.entity.Diagnosis;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.bi.CompleteAptmDAO;
import com.apm.DiaryManagement.eu.bi.DiaryManagementDAO;
import com.apm.DiaryManagement.eu.bi.FinderDAO;
import com.apm.DiaryManagement.eu.bi.NotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.CompleteAppointment;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.DiaryManagement.eu.entity.Tdcode;
import com.apm.DiaryManagement.web.action.SmsService;
import com.apm.DiaryManagement.web.action.WhatsAppService;
import com.apm.Emr.eu.bi.InvestigationDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCInvestigationDAO;
import com.apm.Emr.web.action.PrescriptionAction;
import com.apm.Ipd.eu.bi.BedDao;
import com.apm.Ipd.eu.bi.IpdDAO;
import com.apm.Ipd.eu.blogic.jdbc.JDBCBedDao;
import com.apm.Ipd.eu.blogic.jdbc.JDBCIpdDAO;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Log.eu.blogic.jdbc.JDBCAccountLogDAO;
import com.apm.Log.eu.entity.EmailLetterLog;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Mis.eu.bi.MisChartDAO;
import com.apm.Mis.eu.blogic.jdbc.JDBCMisChartDAO;
import com.apm.Pharmacy.web.action.Tra;
import com.apm.Registration.eu.bi.ClinicDAO;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCClinicDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.Location;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.Report.eu.bi.ChargesReportDAO;
import com.apm.Report.eu.blogic.jdbc.JDBCChargesReportDAO;
import com.apm.ThirdParties.eu.entity.ThirdParty;
import com.apm.TreatmentEpisode.eu.bi.TreatmentEpisodeDAO;
import com.apm.TreatmentEpisode.eu.blogic.jdbc.JDBCTreatmentEpisode;
import com.apm.TreatmentEpisode.eu.entity.TreatmentEpisode;
import com.apm.common.eu.blogic.jdbc.JDBCBaseDAO;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.main.common.constants.Constants;

public class JDBCNotAvailableSlotDAO extends JDBCBaseDAO implements NotAvailableSlotDAO {

	public JDBCNotAvailableSlotDAO(Connection connection) {

		this.connection = connection;
	}

	public ArrayList<DiaryManagement> getUserList(int clinicid, String commencing) {
		PreparedStatement preparedStatement = null;
		ArrayList<DiaryManagement> list = new ArrayList<DiaryManagement>();

		StringBuffer sql = new StringBuffer();
		sql.append(
				"SELECT apm_apmt_slot.id,starttime,endtime,apmtduration,location,tdcode,weekname,commencing,weekfullname,apm_apmt_slot.onlinebooking,apm_apmt_slot.diaryuser FROM apm_apmt_slot ");
		sql.append(
				"inner join apm_user on apm_apmt_slot.diaryuserid = apm_user.id and apm_apmt_slot.commencing=? order by apm_user.id");

		try {

			preparedStatement = connection.prepareStatement(sql.toString());
			preparedStatement.setInt(1, clinicid);
			preparedStatement.setString(2, commencing);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				DiaryManagement diaryManagement = new DiaryManagement();
				diaryManagement.setId(rs.getInt(1));
				diaryManagement.setSTime(rs.getString(2));
				diaryManagement.setEndTime(rs.getString(3));
				diaryManagement.setApmtDuration(rs.getString(4));
				diaryManagement.setLocation(rs.getString(5));
				String locationColor = getLocationColor(diaryManagement.getLocation(), clinicid);
				diaryManagement.setLocationColor(locationColor);
				diaryManagement.setTdCode(rs.getString(6));
				diaryManagement.setWeekName(rs.getString(7));
				diaryManagement.setCommencing(rs.getString(8));
				diaryManagement.setWeekFullName(rs.getString(9));
				diaryManagement.setOnlineBooking(rs.getBoolean(10));
				diaryManagement.setDiaryUser(rs.getString(11));

				list.add(diaryManagement);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public ArrayList<Location> getLocationList(int id) {
		PreparedStatement preparedStatement = null;
		ArrayList<Location> list = new ArrayList<Location>();
		String sql = "select country,city,address,postcode,location,color,id from apm_clinic_location order by location ";

		try {

			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Location location = new Location();

				location.setCountry(rs.getString(1));
				location.setCity(rs.getString(2));
				location.setAddress(rs.getString(3));
				location.setPinCode(rs.getString(4));
				location.setLocation(rs.getString(5));
				location.setColorName(rs.getString(6));
				location.setLocationid(rs.getString(7));

				list.add(location);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<NotAvailableSlot> getList(int id, String date) {
		PreparedStatement preparedStatement = null;
		ArrayList<NotAvailableSlot> list = new ArrayList<NotAvailableSlot>();

		String sql = "SELECT id,starttime,endtime,apmtduration,location,commencing FROM apm_apmt_slot where diaryuserid = "
				+ id + " and commencing = '" + date + "' ";
		try {

			preparedStatement = connection.prepareStatement(sql);

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NotAvailableSlot notavailableslot = new NotAvailableSlot();
				notavailableslot.setId(rs.getInt(1));
				notavailableslot.setSTime(rs.getString(2));
				notavailableslot.setEndTime(rs.getString(3));
				notavailableslot.setApmtDuration(rs.getString(4));
				notavailableslot.setLocation(rs.getString(5));
				String locationColor = getLocationColor(notavailableslot.getLocation(), id);
				notavailableslot.setLocationColor(locationColor);
				notavailableslot.setCommencing(rs.getString(6));

				list.add(notavailableslot);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	private String getLocationColor(String location, int id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT color FROM apm_clinic_location where  userid = " + id + " and location = '" + location
				+ "' ";

		try {
			preparedStatement = connection.prepareStatement(sql);

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				result = rs.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getUserTotalCount(String opendb, String jobgroup) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select count(*) from apm_user where usertype = 4  and  hasdiary=1 ";
		if (opendb.equals("staff")) {
			if (!jobgroup.equals("0")) {
				sql = "select count(*) from apm_user inner join jobtitle_group "
						+ " on jobtitle_group.id = apm_user.jobgroup where jobgroup = " + jobgroup + " ";
			} else {
				sql = "select count(*) from apm_user inner join jobtitle_group "
						+ " on jobtitle_group.id = apm_user.jobgroup  ";
			}
		}
		if (opendb.equals("otdb")) {
			sql = "select count(*) from apm_user inner join jobtitle_group "
					+ " on jobtitle_group.id = apm_user.jobgroup where jobgroup = 4 and jobtitle = 'OT' ";
		}

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

	public ArrayList<DiaryManagement> getDisplayDashBoardUserList(int id, Pagination pagination, String commencing,
			String opendb, String jobgroup, String resultstr) {

		commencing = DateTimeUtils.getCommencingDate(commencing);

		PreparedStatement preparedStatement = null;
		ArrayList<DiaryManagement> list = new ArrayList<DiaryManagement>();
		String sql = "select id,initial,firstname,lastname from apm_user where id in(" + resultstr
				+ ") order by usrposition";

		if (opendb.equals("staff")) {
			if (!jobgroup.equals("0")) {
				sql = "select apm_user.id,initial,firstname,lastname from apm_user inner join jobtitle_group "
						+ " on jobtitle_group.id = apm_user.jobgroup where jobgroup =" + jobgroup
						+ " and islogin=1 order by usrposition ";
			} else {
				sql = "select apm_user.id,initial,firstname,lastname from apm_user inner join jobtitle_group "
						+ " on jobtitle_group.id = apm_user.jobgroup and islogin=1 order by usrposition  ";
			}
		}
		if (opendb.equals("otdb")) {
			sql = "select apm_user.id,initial,firstname,lastname from apm_user inner join jobtitle_group "
					+ " on jobtitle_group.id = apm_user.jobgroup where jobgroup = 4 and jobtitle = 'OT' and islogin=1 order by usrposition ";
		}

		sql = pagination.getSQLQuery(sql);

		try {

			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				DiaryManagement diaryManagement = new DiaryManagement();
				diaryManagement.setId(rs.getInt(1));
				String initial = rs.getString(2);
				if (initial == null || initial.equals("")) {
					diaryManagement.setDiaryUser(rs.getString(3) + " " + rs.getString(4));

				} else {
					diaryManagement.setDiaryUser(rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));

				}

				boolean isApmtExist = isApmtExist(commencing, diaryManagement.getId());
				if (isApmtExist) {
					ArrayList<Location> multiLicationList = getLocationString(diaryManagement.getId(), commencing);
					diaryManagement.setMultiLicationList(multiLicationList);
					/*
					 * locationString = "("+locationString+")";
					 * diaryManagement.setLocationString(locationString);
					 */
				}

				list.add(diaryManagement);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}

	public ArrayList<DiaryManagement> getIdDashBoardUserList(int id, Pagination pagination, String commencing,
			String opendb, String jobgroup) {

		commencing = DateTimeUtils.getCommencingDate(commencing);

		PreparedStatement preparedStatement = null;
		ArrayList<DiaryManagement> list = new ArrayList<DiaryManagement>();
		String sql = "select id,initial,firstname,lastname from apm_user where usertype=4 and hasdiary=1 and islogin=1 order by usrposition";

//		if(opendb.equals("staff")){
//			if(!jobgroup.equals("0")){
//				sql = "select apm_user.id,initial,firstname,lastname from apm_user inner join jobtitle_group " +
//					" on jobtitle_group.id = apm_user.jobgroup where jobgroup ="+jobgroup+" and islogin=1 order by usrposition ";
//			}else{
//				sql = "select apm_user.id,initial,firstname,lastname from apm_user inner join jobtitle_group " +
//				" on jobtitle_group.id = apm_user.jobgroup and islogin=1 order by usrposition  ";
//			}
//		}
//		if(opendb.equals("otdb")){
//			sql = "select apm_user.id,initial,firstname,lastname from apm_user inner join jobtitle_group " +
//			" on jobtitle_group.id = apm_user.jobgroup where jobgroup = 4 and jobtitle = 'OT' and islogin=1 order by usrposition ";
//		}
//		
		// sql = pagination.getSQLQuery(sql);

		try {

			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				DiaryManagement diaryManagement = new DiaryManagement();
				diaryManagement.setId(rs.getInt(1));
				String initial = rs.getString(2);
				if (initial == null || initial.equals("")) {
					diaryManagement.setDiaryUser(rs.getString(3) + " " + rs.getString(4));

				} else {
					diaryManagement.setDiaryUser(rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));

				}

				boolean isApmtExist = isApmtExist(commencing, diaryManagement.getId());
				if (isApmtExist) {
					ArrayList<Location> multiLicationList = getLocationString(diaryManagement.getId(), commencing);
					diaryManagement.setMultiLicationList(multiLicationList);
					/*
					 * locationString = "("+locationString+")";
					 * diaryManagement.setLocationString(locationString);
					 */
				}

				list.add(diaryManagement);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}

	public ArrayList<DiaryManagement> getDashBoardUserList(int id, Pagination pagination, String commencing,
			String opendb, String jobgroup) {

		commencing = DateTimeUtils.getCommencingDate(commencing);

		PreparedStatement preparedStatement = null;
		ArrayList<DiaryManagement> list = new ArrayList<DiaryManagement>();
		String sql = "select id,initial,firstname,lastname from apm_user where usertype=4 and hasdiary=1 and islogin=1 order by usrposition";

		if (opendb.equals("staff")) {
			if (!jobgroup.equals("0")) {
				sql = "select apm_user.id,initial,firstname,lastname from apm_user inner join jobtitle_group "
						+ " on jobtitle_group.id = apm_user.jobgroup where jobgroup =" + jobgroup
						+ " and islogin=1 order by usrposition ";
			} else {
				sql = "select apm_user.id,initial,firstname,lastname from apm_user inner join jobtitle_group "
						+ " on jobtitle_group.id = apm_user.jobgroup and islogin=1 order by usrposition  ";
			}
		}
		if (opendb.equals("otdb")) {
			sql = "select apm_user.id,initial,firstname,lastname from apm_user inner join jobtitle_group "
					+ " on jobtitle_group.id = apm_user.jobgroup where jobgroup = 4 and jobtitle = 'OT' and islogin=1 order by usrposition ";
		}

		sql = pagination.getSQLQuery(sql);

		try {

			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				DiaryManagement diaryManagement = new DiaryManagement();
				diaryManagement.setId(rs.getInt(1));
				String initial = rs.getString(2);
				if (initial == null || initial.equals("")) {
					diaryManagement.setDiaryUser(rs.getString(3) + " " + rs.getString(4));

				} else {
					diaryManagement.setDiaryUser(rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));

				}

				boolean isApmtExist = isApmtExist(commencing, diaryManagement.getId());
				if (isApmtExist) {
					ArrayList<Location> multiLicationList = getLocationString(diaryManagement.getId(), commencing);
					diaryManagement.setMultiLicationList(multiLicationList);
					/*
					 * locationString = "("+locationString+")";
					 * diaryManagement.setLocationString(locationString);
					 */
				}

				list.add(diaryManagement);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}

	private boolean isApmtExist(String commencing, int id) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "SELECT * FROM apm_apmt_slot where commencing = '" + commencing + "' and diaryuserid = " + id
				+ " ";

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

	private ArrayList<Location> getLocationString(int id, String commencing) {
		PreparedStatement preparedStatement = null;
		ArrayList<Location> list = new ArrayList<Location>();
		StringBuffer sql = new StringBuffer();
		String res = "";

		sql.append("SELECT apm_clinic_location.location FROM apm_apmt_slot inner join apm_clinic_location on ");
		sql.append("apm_clinic_location.id = apm_apmt_slot.location ");
		sql.append(
				"where diaryuserid=" + id + " and commencing ='" + commencing + "' group by apm_apmt_slot.location ");

		try {
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Location location = new Location();
				location.setLocationname(rs.getString(1));

				list.add(location);
			}

			// res = result.substring(0,result.toString().length()-1);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<DiaryManagement> getUserList(int id) {
		PreparedStatement preparedStatement = null;
		ArrayList<DiaryManagement> list = new ArrayList<DiaryManagement>();
		//String  clinistaff=getclinicstaff(id);
		String sql = "select id,initial,firstname,lastname from apm_user where usertype=4 and islogin=1 and jobtitle!='OT'  and hasdiary=1 order by firstname asc";
        
		try {

			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				DiaryManagement diaryManagement = new DiaryManagement();
				diaryManagement.setId(rs.getInt(1));
				if (rs.getString(2) == null || rs.getString(2).equals("")) {
					diaryManagement.setDiaryUser(rs.getString(3) + " " + rs.getString(4));

				} else {
					diaryManagement.setDiaryUser(rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));

				}
				list.add(diaryManagement);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public String getclinicstaff(int id) {
		PreparedStatement preparedStatement = null;
		String clinicstaff_id= "";
		String sql = "SELECT clinic_staff FROM apm_user where id = '" + id + "' ";

		try {

			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				clinicstaff_id = rs.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return clinicstaff_id;
		
	}

	public ArrayList<DiaryManagement> getUserAccountList(int id) {
		PreparedStatement preparedStatement = null;
		ArrayList<DiaryManagement> list = new ArrayList<DiaryManagement>();
		String sql = "select id,initial,firstname,lastname from apm_user where usertype=4  and jobtitle='Practitioner' or hasdiary=1 or id=1 ";

		try {

			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				DiaryManagement diaryManagement = new DiaryManagement();
				diaryManagement.setId(rs.getInt(1));
				if (rs.getString(2) == null || rs.getString(2).equals("")) {
					diaryManagement.setDiaryUser(rs.getString(3) + " " + rs.getString(4));

				} else {
					diaryManagement.setDiaryUser(rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));

				}
				list.add(diaryManagement);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public int saveAppointment(NotAvailableSlot notAvailableSlot) {
		int result = 0;
		int id = 0;

		PreparedStatement pstm = null;
		String sql = "insert into apm_available_slot(apmslotid,commencing,starttime,endtime,"
				+ "notes,diaryuserid,diaryusername,dept,location,room,clientname,aptmtype,"
				+ "duration,clientId,charge,treatmentEpisodeId,added_by,apmttypetext,usedsession,"
				+ "condition_id,whopay,otid,category,procedures,surgeon,anesthesia,ipdno,wardid,"
				+ "anidoctorfees,psurcharge,panetcharge,sic,assistaffcharge,opdbooktime,reqdatetime,"
				+ "mobstatus,speciality,appt_type,opdabbrevationid,refferedfrom,isPreDate,opdSequnce,deptOpdId) "
				+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," + "?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			pstm = connection.prepareStatement(sql);
			pstm.setInt(1, notAvailableSlot.getApmtSlotId());
			pstm.setString(2, notAvailableSlot.getCommencing());
			pstm.setString(3, notAvailableSlot.getSTime());
			pstm.setString(4, notAvailableSlot.getEndTime());
			pstm.setString(5, notAvailableSlot.getNotes());
			pstm.setInt(6, notAvailableSlot.getDiaryUserId());
			pstm.setString(7, notAvailableSlot.getDiaryUser());
			pstm.setString(8, notAvailableSlot.getDept());
			pstm.setString(9, notAvailableSlot.getLocation());
			pstm.setString(10, notAvailableSlot.getRoom());
			pstm.setString(11, notAvailableSlot.getClient());
			pstm.setString(12, notAvailableSlot.getApmtType());
			pstm.setString(13, notAvailableSlot.getApmtDuration());
			pstm.setString(14, notAvailableSlot.getClientId());
			if (notAvailableSlot.isVaccineApmt()) {

			}
			double charge = getCharge(notAvailableSlot.getApmtType());
			pstm.setDouble(15, charge);
			pstm.setString(16, notAvailableSlot.getTreatmentEpisodeId());
			pstm.setString(17, notAvailableSlot.getAddedBy());
			String apmtTYpeText = getAppointmentTypeText(notAvailableSlot.getApmtType());
			pstm.setString(18, apmtTYpeText);
			pstm.setString(19, notAvailableSlot.getUsedsession());
			pstm.setString(20, notAvailableSlot.getCondition());
			pstm.setString(21, notAvailableSlot.getPayBy());
			pstm.setInt(22, notAvailableSlot.getOtid());
			pstm.setString(23, notAvailableSlot.getOtplan());
			pstm.setString(24, DateTimeUtils.numberCheck(notAvailableSlot.getProcedure()));
			pstm.setString(25, DateTimeUtils.numberCheck(notAvailableSlot.getSurgeon()));
			pstm.setString(26, DateTimeUtils.numberCheck(notAvailableSlot.getAnesthesia()));
			pstm.setString(27, DateTimeUtils.numberCheck(notAvailableSlot.getIpdno()));
			pstm.setString(28, DateTimeUtils.numberCheck(notAvailableSlot.getWardid()));

			pstm.setString(29, DateTimeUtils.numberCheck(notAvailableSlot.getAnifees()));
			pstm.setString(30, DateTimeUtils.numberCheck(notAvailableSlot.getPsurcharge()));
			pstm.setString(31, DateTimeUtils.numberCheck(notAvailableSlot.getPanetcharge()));
			pstm.setString(32, DateTimeUtils.numberCheck(notAvailableSlot.getSic()));
			pstm.setString(33, DateTimeUtils.numberCheck(notAvailableSlot.getAssistaffcharge()));
			pstm.setString(34, notAvailableSlot.getOpdbooktime());

			String dateTime = "";
			if (notAvailableSlot.getPreDate() == 1) {
				DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
				Calendar cal = Calendar.getInstance();
				String time = dateFormat.format(cal.getTime());
				dateTime = notAvailableSlot.getCommencing() + " " + time;
			} else {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();
				dateTime = dateFormat.format(cal.getTime());
			}

			pstm.setString(35, dateTime);
			pstm.setString(36, notAvailableSlot.getMobstatus());
			pstm.setString(37, notAvailableSlot.getSpeciality());
			pstm.setInt(38, notAvailableSlot.getAppt_type());
			String newopdabbr = "";
			String opdSeqAbbr = "";
			boolean flag = true;
			if (notAvailableSlot.getOtplan().equals("0") && !DateTimeUtils.isNull(apmtTYpeText).equals("")) {
				//we are adding parameter(notAvailableSlot.getDiaryUserId()) in below method beacause of balgopal>physio >generate opdabbrivationid 
				newopdabbr = generateOPDSequenceNewFormat(notAvailableSlot.getDiaryUserId());
				opdSeqAbbr = generateLMHOPDSeq(notAvailableSlot.getCommencing());
				flag = false;
			}
			if (notAvailableSlot.getOtplan().equals("0") && flag) {
				opdSeqAbbr = generateLMHOPDSeq(notAvailableSlot.getCommencing());
			}
			pstm.setString(39, newopdabbr);
			pstm.setString(40, DateTimeUtils.isNull(notAvailableSlot.getRefferedfrom()));
			pstm.setString(41, "" + notAvailableSlot.getPreDate());
			pstm.setString(42, opdSeqAbbr);
			pstm.setString(43, "" + notAvailableSlot.getDeptOpdId());
			result = pstm.executeUpdate();

			if (result == 1) {
				ResultSet resultSet = pstm.getGeneratedKeys();
				if (resultSet.next()) {
					id = resultSet.getInt(1);
				}
			}

			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			UserProfile userProfile = userProfileDAO.getUserprofileDetails(notAvailableSlot.getDiaryUserId());
			if (userProfile.getJobgroup().equals("4")) {
				int update = updateMachineDoctorID(id, userProfile.getDoctor());
			}
            // int updtlod=updateLatopddate(notAvailableSlot.getClientId(),notAvailableSlot.getCommencing());
			// int
			// dd=addOpdConditionReport(id,notAvailableSlot.getClientId(),notAvailableSlot.getCondition(),notAvailableSlot.getCommencing());

		} catch (Exception e) {

			e.printStackTrace();
//			StringWriter sw = new StringWriter();
//	         PrintWriter pw = new PrintWriter(sw);
//	         e.printStackTrace(pw);
//			Tra.exceptionMail(sw.toString());

		}

		return id;
	}

	/*
	 * private int updateLatopddate(String clientId, String commencing) {
	 * PreparedStatement preparedStatement = null; int result = 0; String sql =
	 * "update apm_patient set last_opd_date=? where id=" + clientId + " ";
	 * 
	 * try { preparedStatement = connection.prepareStatement(sql);
	 * preparedStatement.setString(1, commencing); result =
	 * preparedStatement.executeUpdate();
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } return result; }
	 */

	private String generateLMHOPDSeq(String cdate) {
		String abrivationid = "";
		try {

			int seqno = checkifSequenceExistLMH(cdate);
			String clinicabrivation = "OP";

			String tempd[] = cdate.split("-");
			String y = tempd[0];
			String m = tempd[1];
			String d = tempd[2];
			String newseq = "";
			int yr = Integer.parseInt(y) % 1000;
			yr = Integer.parseInt(y);
			if (String.valueOf(seqno).length() == 1) {
				newseq = "000" + seqno;
			} else if (String.valueOf(seqno).length() == 2) {
				newseq = "00" + seqno;
			} else if (String.valueOf(seqno).length() == 3) {
				newseq = "0" + seqno;
			} else {
				newseq = "" + seqno;
			}
			abrivationid = clinicabrivation + yr + m + d + "" + newseq;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return abrivationid;

	}

	private int checkifSequenceExistLMH(String cdate) {

		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select seqno from opd_sequence where commencing = '" + cdate + "' ";
		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
				result++;
				String querry = "update opd_sequence set seqno='" + result + "' where commencing='" + cdate + "' ";
				PreparedStatement preparedStatement2 = connection.prepareStatement(querry);
				preparedStatement2.executeUpdate();
			} else {
				result = 1;
				String querry = "insert into opd_sequence(commencing,seqno) values(?,?)";
				PreparedStatement preparedStatement2 = connection.prepareStatement(querry);
				preparedStatement2.setString(1, cdate);
				preparedStatement2.setString(2, "" + result);
				preparedStatement2.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	private int updateMachineDoctorID(int id, String doctor) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_available_slot set doctor=" + doctor + " where id=" + id + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public String getAppointmentTypeText(String id) {
		PreparedStatement preparedStatement = null;
		StringBuffer buffer= new StringBuffer();
		String result = "";
		buffer.append("SELECT name FROM apm_appointment_type where id= " + id + " ");

		try {

		    preparedStatement=connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<NotAvailableSlot> getNewAppointmentData(String diaryuserId, String commencing) {
		PreparedStatement preparedStatement = null;
		ArrayList<NotAvailableSlot> list = new ArrayList<NotAvailableSlot>();
		String sql = "SELECT id,apmslotid,commencing,starttime,endtime,notes,diaryuserid,diaryusername,dept,location,room,clientname,aptmtype from apm_available_slot where diaryuserid = "
				+ diaryuserId + " and commencing = " + commencing + " ";

		try {

			preparedStatement = connection.prepareStatement(sql);

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
				notAvailableSlot.setId(rs.getInt(1));
				notAvailableSlot.setApmtSlotId(rs.getInt(2));
				notAvailableSlot.setCommencing(rs.getString(3));
				notAvailableSlot.setSTime(rs.getString(4));
				notAvailableSlot.setEndTime(rs.getString(5));
				notAvailableSlot.setNotes(rs.getString(6));
				notAvailableSlot.setDiaryUserId(rs.getInt(7));
				notAvailableSlot.setDept(rs.getString(8));
				notAvailableSlot.setLocation(rs.getString(9));
				notAvailableSlot.setRoom(rs.getString(10));
				notAvailableSlot.setClient(rs.getString(11));
				notAvailableSlot.setApmtType(rs.getString(12));

				list.add(notAvailableSlot);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int saveBlockSlot(NotAvailableSlot notAvailableSlot, String opendb) {
		int result = 0;

		PreparedStatement pstm = null;
		String sql = "insert into apm_available_slot(apmslotid,commencing,starttime,endtime,notes,diaryuserid,diaryusername,location,room,reasonforblock,status,duration,blockot) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		if (opendb.equals("staff")) {
			sql = "insert into his_staff_slot(apmslotid,commencing,starttime,endtime,notes,diaryuserid,diaryusername,location,room,reasonforblock,status,duration,blockot) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		}
		try {
			pstm = connection.prepareStatement(sql);
			pstm.setInt(1, notAvailableSlot.getApmtSlotId());
			pstm.setString(2, notAvailableSlot.getCommencing());
			pstm.setString(3, notAvailableSlot.getSTime());
			pstm.setString(4, notAvailableSlot.getEndTime());
			pstm.setString(5, notAvailableSlot.getNotes());
			pstm.setInt(6, notAvailableSlot.getDiaryUserId());
			pstm.setString(7, notAvailableSlot.getDiaryUser());
			pstm.setString(8, notAvailableSlot.getLocation());
			pstm.setString(9, notAvailableSlot.getRoom());
			pstm.setString(10, notAvailableSlot.getReasonforblock());
			pstm.setString(11, "1");
			pstm.setString(12, notAvailableSlot.getApmtDuration());
			pstm.setInt(13, notAvailableSlot.getBlockot());

			result = pstm.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
			// TODO: handle exception
		}

		return result;
	}

	public ArrayList<AppointmentType> getAppointmentTypeList() {
		PreparedStatement preparedStatement = null;
		ArrayList<AppointmentType> list = new ArrayList<AppointmentType>();
		String sql = "SELECT duration,name,id,charges FROM apm_appointment_type where tpid = 0 order by name ";

		try {

			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				AppointmentType appointmentType = new AppointmentType();
				appointmentType.setDuration(rs.getString(1));
				String charge = rs.getString(4);
				appointmentType.setName(rs.getString(2) + " (" + charge + ")");
				appointmentType.setId(rs.getInt(3));
				list.add(appointmentType);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int updateAppointment(NotAvailableSlot notAvailableSlot, int selectedid) {
		PreparedStatement preparedStatement = null;
		int result = 0;

		String sql = "update apm_available_slot set starttime=?,endtime=?,duration=?,notes=?,clientname=?,aptmtype=?,clientId=?,treatmentEpisodeId=?,last_modified_date=?,last_modified_time=?,modified_by=?,diaryuserid=?,diaryusername=?,location=?,commencing=?,apmslotid=?,charge=?,apmttypetext=?,condition_id=?,whopay=? where id = "
				+ selectedid + " ";

		try {

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, notAvailableSlot.getSTime());
			preparedStatement.setString(2, notAvailableSlot.getEndTime());
			preparedStatement.setString(3, notAvailableSlot.getApmtDuration());
			preparedStatement.setString(4, notAvailableSlot.getNotes());
			preparedStatement.setString(5, notAvailableSlot.getClient());
			preparedStatement.setString(6, notAvailableSlot.getApmtType());
			preparedStatement.setString(7, notAvailableSlot.getClientId());
			preparedStatement.setString(8, notAvailableSlot.getTreatmentEpisodeId());
			Calendar currentDate = Calendar.getInstance();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateNow = formatter.format(currentDate.getTime());

			String temp[] = dateNow.split("\\s+");
			String modified_date = temp[0];
			String modified_time = temp[1];

			preparedStatement.setString(9, modified_date);
			preparedStatement.setString(10, modified_time);
			preparedStatement.setString(11, notAvailableSlot.getModifiedBy());
			preparedStatement.setInt(12, notAvailableSlot.getDiaryUserId());

			preparedStatement.setString(13, notAvailableSlot.getDiaryUser());
			preparedStatement.setString(14, notAvailableSlot.getLocation());
			preparedStatement.setString(15, notAvailableSlot.getCommencing());
			preparedStatement.setInt(16, notAvailableSlot.getApmtSlotId());
			String apmtType = notAvailableSlot.getApmtType();
			double charge = getCharge(apmtType);
			preparedStatement.setDouble(17, charge);

			String apmtTYpeText = getAppointmentTypeText(notAvailableSlot.getApmtType());
			preparedStatement.setString(18, apmtTYpeText);
			preparedStatement.setString(19, notAvailableSlot.getCondition());
			preparedStatement.setString(20, notAvailableSlot.getPayBy());

			result = preparedStatement.executeUpdate();

			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			UserProfile userProfile = userProfileDAO.getUserprofileDetails(notAvailableSlot.getDiaryUserId());
			if (userProfile.getJobgroup().equals("4")) {
				int update = updateMachineDoctorID(selectedid, userProfile.getDoctor());
			}

		} catch (Exception e) {
			e.printStackTrace();
//			StringWriter sw = new StringWriter();
//	         PrintWriter pw = new PrintWriter(sw);
//	         e.printStackTrace(pw);
//			Tra.exceptionMail(sw.toString());
		}
		return result;

	}

	public int updateBlockSlot(NotAvailableSlot notAvailableSlot, int selectedid, String opendb) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_available_slot set starttime=?,endtime=?,duration=?,notes=?,reasonforblock=? where id = "
				+ selectedid + " ";
		if (opendb.equals("staff")) {
			sql = "update his_staff_slot set starttime=?,endtime=?,duration=?,notes=?,reasonforblock=? where id = "
					+ selectedid + " ";
		}
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, notAvailableSlot.getSTime());
			preparedStatement.setString(2, notAvailableSlot.getEndTime());
			preparedStatement.setString(3, notAvailableSlot.getApmtDuration());
			preparedStatement.setString(4, notAvailableSlot.getNotes());
			preparedStatement.setString(5, notAvailableSlot.getReasonforblock());

			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateClientHasArrived(int selectedid, int status) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_available_slot set arrivedstatus =" + status + " where id = " + selectedid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateClientIsBeingSeen(int selectedid, int status) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_available_slot set arrivedstatus =" + status + " where id = " + selectedid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	public int updateResetNotArrived(int selectedid, int status) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_available_slot set arrivedstatus =" + status + " where id = " + selectedid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	public int updateDNA(int selectedid, String notes, boolean dna, String dnaReason, String dnacharge) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_available_slot set  dnanotes=?,dna=?,dnaReason=?,charge=? where id = " + selectedid
				+ " ";

		try {
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, notes);
			preparedStatement.setBoolean(2, dna);
			preparedStatement.setString(3, dnaReason);
			preparedStatement.setString(4, dnacharge);

			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int saveCharge(NotAvailableSlot notAvailableSlot, String apmtType, int id) {
		int result = 0;

		PreparedStatement pstm = null;
		String sql = "insert into apm_patient_complete_apmt(user,apmtType,charge,startTime,duration,practitionerId,practitionerName,clientId,commencing,startUserId)values(?,?,?,?,?,?,?,?,?,?)";
		try {
			pstm = connection.prepareStatement(sql);
			pstm.setString(1, notAvailableSlot.getClient());
			pstm.setString(2, notAvailableSlot.getApmtType());
			double charge = getCharge(apmtType);
			pstm.setDouble(3, charge);
			pstm.setString(4, notAvailableSlot.getSTime());
			pstm.setString(5, notAvailableSlot.getApmtDuration());
			pstm.setInt(6, notAvailableSlot.getDiaryUserId());
			pstm.setString(7, notAvailableSlot.getDiaryUser());
			pstm.setString(8, notAvailableSlot.getClientId());
			pstm.setString(9, notAvailableSlot.getCommencing());
			pstm.setInt(10, id);

			result = pstm.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();

		}

		return result;
	}

	public double getCharge(String apmtType) {
		PreparedStatement preparedStatement = null;
		double charge = 0.0;
		String sql = "select charges from apm_appointment_type where id = '" + apmtType + "'";

		try {
			preparedStatement = connection.prepareStatement(sql);

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				charge = rs.getDouble(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return charge;
	}

	public int updateCharge(NotAvailableSlot notAvailableSlot, String apmtType, int selectedid) {
		int result = 0;

		PreparedStatement pstm = null;
		String sql = "update apm_patient_complete_apmt set user=?,apmtType=?,charge=?,startTime=?,duration=?,practitionerName=?,clientId=?,commencing=? where appointmentid = "
				+ selectedid + "";
		try {
			pstm = connection.prepareStatement(sql);
			pstm.setString(1, notAvailableSlot.getClient());
			pstm.setString(2, notAvailableSlot.getApmtType());
			double charge = getCharge(apmtType);
			pstm.setDouble(3, charge);
			pstm.setString(4, notAvailableSlot.getSTime());
			pstm.setString(5, notAvailableSlot.getApmtDuration());
			pstm.setString(6, notAvailableSlot.getDiaryUser());
			pstm.setString(7, notAvailableSlot.getClientId());
			pstm.setString(8, notAvailableSlot.getCommencing());

			result = pstm.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();

		}

		return result;
	}

	public ArrayList<NotAvailableSlot> getPrintDataOfWeek(String practionerId, String fromDate, String toDate) {
		PreparedStatement preparedStatement = null;
		ArrayList<NotAvailableSlot> list = new ArrayList<NotAvailableSlot>();
		String sql = "SELECT id,apmslotid,commencing,starttime,endtime,notes,diaryuserid,diaryusername,dept,location,room,clientname,apmttypetext,duration from apm_available_slot where diaryuserid = "
				+ practionerId + " and commencing between '" + fromDate + "' AND '" + toDate + "' ";

		try {

			preparedStatement = connection.prepareStatement(sql);

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
				notAvailableSlot.setId(rs.getInt(1));
				notAvailableSlot.setApmtSlotId(rs.getInt(2));
				notAvailableSlot.setCommencing(rs.getString(3));
				notAvailableSlot.setSTime(rs.getString(4));
				notAvailableSlot.setEndTime(rs.getString(5));
				notAvailableSlot.setNotes(rs.getString(6));
				notAvailableSlot.setDiaryUserId(rs.getInt(7));
				notAvailableSlot.setDiaryUser(rs.getString(8));
				notAvailableSlot.setDept(rs.getString(9));
				notAvailableSlot.setLocation(rs.getString(10));
				notAvailableSlot.setRoom(rs.getString(11));
				notAvailableSlot.setClient(rs.getString(12));
				notAvailableSlot.setApmtType(rs.getString(13));
				notAvailableSlot.setApmtDuration(rs.getString(14));

				list.add(notAvailableSlot);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<NotAvailableSlot> getPractitionerPrintData(String practionerId, String date) {
		PreparedStatement preparedStatement = null;
		ArrayList<NotAvailableSlot> list = new ArrayList<NotAvailableSlot>();
		String sql = "SELECT id,apmslotid,commencing,starttime,endtime,notes,diaryuserid,diaryusername,dept,location,room,clientname,apmttypetext,duration from apm_available_slot where diaryuserid = "
				+ practionerId + " and commencing = '" + date + "' ";

		try {

			preparedStatement = connection.prepareStatement(sql);

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
				notAvailableSlot.setId(rs.getInt(1));
				notAvailableSlot.setApmtSlotId(rs.getInt(2));
				notAvailableSlot.setCommencing(rs.getString(3));
				notAvailableSlot.setSTime(rs.getString(4));
				notAvailableSlot.setEndTime(rs.getString(5));
				notAvailableSlot.setNotes(rs.getString(6));
				notAvailableSlot.setDiaryUserId(rs.getInt(7));
				notAvailableSlot.setDiaryUser(rs.getString(8));
				notAvailableSlot.setDept(rs.getString(9));
				notAvailableSlot.setLocation(rs.getString(10));
				notAvailableSlot.setRoom(rs.getString(11));
				notAvailableSlot.setClient(rs.getString(12));
				notAvailableSlot.setApmtType(rs.getString(13));
				notAvailableSlot.setApmtDuration(rs.getString(14));

				list.add(notAvailableSlot);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<NotAvailableSlot> getAllPractitionerPrintData(String date, String enddate, String location,
			String diaryuser, String action, String opendb, String diaryuserjd, String fromDate, String toDate,LoginInfo loginInfo) {
		PreparedStatement preparedStatement = null;
		ArrayList<NotAvailableSlot> list = new ArrayList<NotAvailableSlot>();
		if (location.equals("")) {
			location = "0";
		}
		StringBuffer buffer = new StringBuffer();
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		/*
		 * String sql =
		 * "SELECT apm_available_slot.id,apmslotid,commencing,starttime,endtime,notes,diaryuserid,diaryusername,dept,location,room,clientname,aptmtype,duration,apm_available_slot.status,treatmentEpisodeId,usedsession,clientid,apm_available_slot.whopay,reasonforblock,workcompleted,admissionid from apm_available_slot inner join apm_patient on  apm_patient.id = apm_available_slot.clientId  "
		 * ;
		 * 
		 * if(opendb.equals("staff")){ sql =
		 * "SELECT id,apmslotid,commencing,starttime,endtime,notes,diaryuserid,diaryusername,dept,location,room,clientname,aptmtype,duration,status,treatmentEpisodeId,usedsession,clientid,whopay,reasonforblock,workcompleted,admissionid from his_staff_slot  "
		 * ; }
		 * 
		 * if(action.equals("dashboard")){ if(location.equals("0")){
		 * 
		 * sql = sql + " where commencing = '"+date+"' "; sql = sql +
		 * " where commencing between '"+fromDate+"' and '"
		 * +toDate+"' and procedures='0' ";
		 * 
		 * }else{ sql = sql +
		 * " where commencing = '"+date+"' and location = "+location+"  "; sql = sql +
		 * " where commencing between '"+fromDate+"' and '"+toDate+"' and location = "
		 * +location+" and procedures='0' "; } if(diaryuserjd!=null){
		 * if(!diaryuserjd.equals("0")){ sql = sql +
		 * " and diaryuserid="+diaryuserjd+" "; } }
		 * 
		 * 
		 * 
		 * }
		 * 
		 * if(action.equals("week")){ sql = sql +
		 * " where commencing between '"+date+"' and  '"+enddate+"' and diaryuserid = "
		 * +diaryuser+" "; }
		 * 
		 * if(action.equals("day")){ if(location.equals("0")){ sql = sql +
		 * " where commencing = '"+date+"' and diaryuserid="+diaryuser+" "; }else{ sql =
		 * sql +
		 * " where commencing = '"+date+"' and diaryuserid="+diaryuser+" and location="
		 * +location+" "; }
		 * 
		 * } if(action.equals("ot")){ if(location.equals("0")){ sql = sql +
		 * " where commencing = '"+date+"' and diaryuserid="
		 * +diaryuser+" and procedures!='0' "; }else{ sql = sql +
		 * " where commencing = '"+date+"' and diaryuserid="+diaryuser+" and location="
		 * +location+" procedures!='0' "; }
		 * 
		 * } sql = sql + " order by diaryuserid  ";
		 */
         //we are adding inner join query for physio opd balgopal inner join apm_discipline on apm_discipline.id=apm_available_slot.dept and add showdeptlist
		buffer.append("SELECT apm_available_slot.id,apmslotid,commencing,starttime,endtime,notes,diaryuserid,diaryusername,dept, ");
		buffer.append("location,room,clientname,aptmtype,duration,apm_available_slot.status,treatmentEpisodeId,usedsession,clientid,");
		buffer.append("apm_available_slot.whopay,reasonforblock,workcompleted,admissionid,opdabbrevationid from apm_available_slot inner join ");
		buffer.append("apm_patient on  apm_patient.id = apm_available_slot.clientId inner join apm_discipline on apm_discipline.id=apm_available_slot.dept ");

		if (opendb.equals("staff")) {
			buffer.append("SELECT id,apmslotid,commencing,starttime,endtime,notes,diaryuserid,diaryusername,dept,location,room,clientname,aptmtype,duration,status,treatmentEpisodeId,usedsession,clientid,whopay,reasonforblock,workcompleted,admissionid from his_staff_slot  ");
		}

		if (action.equals("dashboard")) {
			if (location.equals("0")) {

				/* sql = sql + " where commencing = '"+date+"' "; */
				buffer.append(" where commencing between '" + fromDate + "' and '" + toDate + "' and procedures='0' ");

			} else {
				/*
				 * sql = sql + " where commencing = '"+date+"' and location = "+location+"  ";
				 */
				buffer.append(" where commencing between '" + fromDate + "' and '" + toDate + "' and location = "
						+ location + " and procedures='0' ");
			}
			if (diaryuserjd != null) {
				if (!diaryuserjd.equals("0")) {
					buffer.append(" and diaryuserid=" + diaryuserjd + " ");
				}
			}

		}

		if (action.equals("week")) {
			buffer.append(" where commencing between '" + date + "' and  '" + enddate + "' and diaryuserid = " + diaryuser
					+ " ");
		}

		if (action.equals("day")) {
			if (location.equals("0")) {
				buffer.append(" where commencing = '" + date + "' and diaryuserid=" + diaryuser + " ");
			} else {
				buffer.append( " where commencing = '" + date + "' and diaryuserid=" + diaryuser + " and location="
						+ location + " ");
			}

		}
		if (action.equals("ot")) {
			if (location.equals("0")) {
				buffer.append( " where commencing = '" + date + "' and diaryuserid=" + diaryuser + " and procedures!='0' ");
			} else {
				buffer.append( " where commencing = '" + date + "' and diaryuserid=" + diaryuser + " and location="
						+ location + " procedures!='0' ");
			}

		}
		
		// condition applied for vspm because showopdlist is 1
		if(loginInfo.isLmh() ) {
			buffer.append("and showdeptlist='1' order by diaryuserid  ");	
		}else {
		    buffer.append("and showdeptlist='0' order by diaryuserid  ");
		}

		// String sql = "SELECT
		// id,apmslotid,commencing,starttime,endtime,notes,diaryuserid,diaryusername,dept,location,room,clientname,aptmtype,duration
		// from apm_available_slot where commencing = '"+date+"' ";
		try {
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			//preparedStatement = connection.prepareStatement(sql);
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();
			int i = 0;
			if (action.equals("day")) {
				DiaryManagement diaryManagement = getApmtSlotData(date, diaryuser, location);
				NotAvailableSlot nn = getAvailableSlotData(date, diaryuser, location);
				if (!diaryManagement.getSTime().equals(nn.getSTime())) {
					String duration = DateTimeUtils.getDuration(diaryManagement.getSTime(), nn.getSTime());
					nn = new NotAvailableSlot();
					nn.setSTime(diaryManagement.getSTime());
					nn.setEndTime(diaryManagement.getSTime());
					nn.setApmtDuration(duration);
					nn.setActionType(action);
					nn.setStatus("2");
					nn.setApmtSlotStimeEmpty(true);
					list.add(nn);
				}
			}
			while (rs.next()) {
				NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
				notAvailableSlot.setId(rs.getInt(1));
				notAvailableSlot.setApmtSlotId(rs.getInt(2));
				notAvailableSlot.setCommencing(DateTimeUtils.getCommencingDate1(rs.getString(3)));
				notAvailableSlot.setSTime(rs.getString(4));
				notAvailableSlot.setEndTime(rs.getString(5));
				notAvailableSlot.setNotes(rs.getString(6));
				notAvailableSlot.setDiaryUserId(rs.getInt(7));
				notAvailableSlot.setDiaryUser(rs.getString(8));
				notAvailableSlot.setDept(rs.getString(9));
				if (!notAvailableSlot.getDept().equals("")) {
					String deptname = getdeptName(rs.getString(9));
					notAvailableSlot.setDepartmentname(deptname);
				}

				location = getLocationName(rs.getString(10));
				notAvailableSlot.setLocation(location);
				notAvailableSlot.setRoom(rs.getString(11));
				notAvailableSlot.setClient(rs.getString(12));
				// notAvailableSlot.setUhid(rs.getString(22));

				Client client = clientDAO.getClientDetails(rs.getString(18));
				notAvailableSlot.setOldclientid(client.getOldclientId());
				notAvailableSlot.setAddress(client.getAddress());
				notAvailableSlot.setAbrivationid(client.getAbrivationid());
				notAvailableSlot.setClientId(rs.getString(18));
				notAvailableSlot.setMobno(client.getMobNo());
				try {
					// String age= DateTimeUtils.getAge(client.getDob());
					/* notAvailableSlot.setAgegender(age+"/"+client.getGender()); */
					String agegender = "";
					// String dob = client.getDob();
					String age2 = DateTimeUtils.getAge(client.getDob());
					if (age2 == null) {
						if (age2.equals("")) {
							age2 = "0";
						}
					}
					if (Integer.parseInt(age2) < 2) {
						if (Integer.parseInt(age2) < 1) {
							String monthdays = DateTimeUtils.getMonthandDays(client.getDob());
							agegender = monthdays + "/" + client.getGender();
						} else {
							String monthdays = DateTimeUtils.getMonthandDays(client.getDob());
							agegender = age2 + " Years" + " " + monthdays + "/" + client.getGender();
						}
					} else {
						agegender = age2 + "Years /" + client.getGender();
					}
					notAvailableSlot.setAgegender(agegender);

				} catch (Exception e) {
					// TODO: handle exception
				}

				String apmtyType = getAppointmentTypeText(rs.getString(13));
				notAvailableSlot.setApmtType(apmtyType);
				notAvailableSlot.setApmtDuration(rs.getString(14));
				notAvailableSlot.setActionType(action);
				notAvailableSlot.setStatus(rs.getString(15));

				CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
				CompleteAppointment completeAppointment = completeAptmDAO.getInsuranceCompanyName(rs.getString(18));
				String tpName = completeAppointment.getInsuranceCompanyName();
				notAvailableSlot.setTpName(tpName);

				notAvailableSlot.setWhopay(rs.getString(19));

				if (notAvailableSlot.getStatus().equals("1")) {
					notAvailableSlot.setApmtType(rs.getString(20));
				}
				notAvailableSlot.setWorkcompleted(rs.getString(21));
				notAvailableSlot.setAdmisionid(rs.getString(22));
				notAvailableSlot.setOpdAbbrId(rs.getString(23));
				String usdsession = rs.getString(17);

				if (rs.getInt(16) > 0) {
					TreatmentEpisodeDAO treatmentEpisodeDAO = new JDBCTreatmentEpisode(connection);
					TreatmentEpisode treatmentEpisode = treatmentEpisodeDAO
							.getTreatmentEpisodeSessionDetails(rs.getString(16));
					notAvailableSlot.setUsedsession(usdsession + "-" + treatmentEpisode.getSessions());

					notAvailableSlot.setApmtType("(" + notAvailableSlot.getUsedsession() + ")" + " " + apmtyType);
				}

				notAvailableSlot.setApmtSlotStimeEmpty(false);
				int invoiceid = accountsDAO.getInvoiceIdOfApmt("" + rs.getInt(1));
				//Accounts accounts = accountsDAO.getInvoiceChargesDetails("" + invoiceid);
				notAvailableSlot.setDiscount("" + accountsDAO.getDiscamtofInvoice("" + invoiceid));
				notAvailableSlot.setDebit("" + accountsDAO.getDebitfromInvoice("" + invoiceid));
				notAvailableSlot.setPayment("" + accountsDAO.getPaymentammount("" + invoiceid));
				String diagnosis = getOpdDiagnsis(client.getId(), notAvailableSlot.getDept());
				notAvailableSlot.setDiagnosis(diagnosis);
				notAvailableSlot.setDob(client.getDob());				
	            
				if(loginInfo.isBams1() ) {
				String ipdabriid = getIpdabrivationid(rs.getString(18), rs.getString(9));
				notAvailableSlot.setIpdid(ipdabriid);
				StringBuffer prisc= getPriscriptionbyid(notAvailableSlot.getClientId(),rs.getString(3));
				notAvailableSlot.setPriscription(prisc);
				
				StringBuffer invst=getInvestigationbyid(notAvailableSlot.getClientId(),rs.getString(3));
				notAvailableSlot.setInvestigation(invst);
				
				String chiefcomplaint=getchiefcomplaintbyid(notAvailableSlot.getClientId(),rs.getString(3));
				notAvailableSlot.setChief_complains(chiefcomplaint);
				
				NotAvailableSlot punch=getPunchkarmadata(notAvailableSlot.getClientId(),rs.getString(3));
				notAvailableSlot.setReqnote(punch.getReqnote());
				String procedurename=getprocedurenamebyid(punch.getProcedureid());
				String punchnote=getPunchnotebyparentid(punch.getReqid());
				String punchkarma=procedurename+", "+punch.getReqnote()+", "+punchnote;
				notAvailableSlot.setPunchkarma(punchkarma);
				NotAvailableSlot karma=getKarmaandProcedure(notAvailableSlot.getClientId(),rs.getString(3)); 
				notAvailableSlot.setKarma(DateTimeUtils.isNull(karma.getKarma()));
				notAvailableSlot.setProcedurebams(DateTimeUtils.isNull(karma.getProcedurebams()));
				
				/*
				 * StringBuffer
				 * notes1=getEmrnotesbyid(notAvailableSlot.getId(),notAvailableSlot.getClientId(
				 * )); notAvailableSlot.setConsultnotes(notes1);
				 */
				}
				
				if(loginInfo.isLmh()) {
					
					StringBuffer prisc= getPriscriptionbyid(notAvailableSlot.getClientId(),rs.getString(3));
					notAvailableSlot.setPriscription(prisc);
					
					StringBuffer invst=getInvestigationbyid(notAvailableSlot.getClientId(),rs.getString(3));
					notAvailableSlot.setInvestigation(invst);
				}
			    list.add(notAvailableSlot);
			    i++;
				 
			
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private StringBuffer getEmrnotesbyid(int id, String clientId) {
		PreparedStatement preparedStatement=null;
		StringBuffer history=new StringBuffer();
		StringBuffer buffer=new StringBuffer();
		
		try {
			buffer.append("select history from apm_consultation_note where patientid='"+clientId+"' and appointmentid = '"+id+"'");
			preparedStatement=connection.prepareStatement(buffer.toString());
			ResultSet rs=preparedStatement.executeQuery();
			while(rs.next()) {
				history=history.append(rs.getString(1)+", ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return history;
	}

	private NotAvailableSlot getKarmaandProcedure(String clientId, String commencing) {
		PreparedStatement pst=null;
		NotAvailableSlot notAvailableSlot=new NotAvailableSlot();
		StringBuffer buffer=new StringBuffer();
		try {
			 buffer.append("select karma,procedurebams from consultation_form where client_id='"+clientId+"' and date_time between '"+commencing+" 00:00:00' and '"+commencing+" 23:59:59' ");
		     pst=connection.prepareStatement(buffer.toString());
		     ResultSet rs=pst.executeQuery();
		     while(rs.next()) {
		    	 notAvailableSlot.setKarma(rs.getString(1));
		    	 notAvailableSlot.setProcedurebams(rs.getString(2));
		     }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return notAvailableSlot;
	}

	private String getPunchnotebyparentid(String reqid) {
		PreparedStatement preparedStatement=null;
		String punchnote="";
		StringBuffer buffer=new StringBuffer();
		try {
			buffer.append("select punch_note from punchkarma_note where parent_id='"+reqid+"'");
			preparedStatement=connection.prepareStatement(buffer.toString());
			ResultSet rs=preparedStatement.executeQuery();
			while(rs.next()) {
				punchnote=rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return punchnote;
	}

	private String getprocedurenamebyid(String procedureid) {
		PreparedStatement preparedStatement=null;
		String name="";
		StringBuffer buffer=new StringBuffer();
		try {
			buffer.append("select name from apm_newchargetype where id="+procedureid+"");
			preparedStatement=connection.prepareStatement(buffer.toString());
			ResultSet rs=preparedStatement.executeQuery();
			while(rs.next()) {
				name=rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return name;
	}

	private NotAvailableSlot getPunchkarmadata(String clientId, String commencing) {
		PreparedStatement pst=null;
		NotAvailableSlot punchkarma=new NotAvailableSlot();
		StringBuffer buffer=new StringBuffer();
		try {
			buffer.append("select id,note,procedureid from request_note where clientid='"+clientId+"' and date_time='"+commencing+"' ");
			pst=connection.prepareStatement(buffer.toString());
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				punchkarma.setReqid(rs.getString(1));
				punchkarma.setReqnote(rs.getString(2));
				punchkarma.setProcedureid(rs.getString(3));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return punchkarma;
	}

	private String getchiefcomplaintbyid(String clientId, String commencing) {
		PreparedStatement preparedStatement=null;
		String chief="";
		StringBuffer buffer=new StringBuffer();
		
		try {
			buffer.append("select chief_complains from consultation_form where client_id='"+clientId+"' and date_time between '"+commencing+" 00:00:00' and '"+commencing+" 23:59:59'");
			preparedStatement=connection.prepareStatement(buffer.toString());
			ResultSet rs=preparedStatement.executeQuery();
			while(rs.next()) {
				chief=rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return chief;
	}

	private StringBuffer getInvestigationbyid(String clientId, String commencing) {
		PreparedStatement preparedStatement=null;
		StringBuffer invst=new StringBuffer();
		StringBuffer buffer=new StringBuffer();
		
		try {
			buffer.append("select invsttype from apm_client_investigation where clientid='"+clientId+"' and lastmodified between '"+commencing+" 00:00:00' and '"+commencing+" 23:59:59' group by parentid");
			preparedStatement=connection.prepareStatement(buffer.toString());
			ResultSet rs=preparedStatement.executeQuery();
			while(rs.next()) {
				invst=invst.append(rs.getString(1)+", ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return invst;
	}

	private StringBuffer getPriscriptionbyid(String clientId, String commencing) {
		PreparedStatement preparedStatement=null;
		StringBuffer prisc=new StringBuffer();
		StringBuffer buffer=new StringBuffer();
		try {
			buffer.append("select mdicinename,dose,days from apm_client_priscription where clientid='"+clientId+"' and lastmodified between '"+commencing+" 00:00:00' and '"+commencing+" 23:59:59'");
	        preparedStatement=connection.prepareStatement(buffer.toString());
	        ResultSet rs=preparedStatement.executeQuery();
	        while(rs.next()) {
	        	prisc=prisc.append(rs.getString(1)+"("+rs.getString(2)+") X "+rs.getString(3)+"D, ");
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prisc;
	}

	private String getIpdabrivationid(String id, String dept) {
		PreparedStatement pst = null;
		String res = "";
		try {
			String sql = "select ipdabrivationid from ipd_addmission_form where clientid=" + id + "";
			pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				res = rs.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	private String getOpdDiagnsis(int id, String dept) {
		PreparedStatement pst = null;
		String res = "";
		try {
			String sql = "select diagnosis from opd_diagnosis where clientid=" + id + " and department='" + dept + "'";
			pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				res = rs.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public String getdeptName(String id) {
		PreparedStatement pst = null;
		String dept = "";
		try {
			String sql = "select discipline from apm_discipline where id=" + id + "";
			pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				dept = rs.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dept;
	}

	private NotAvailableSlot getAvailableSlotData(String date, String diaryuser, String location) {

		PreparedStatement preparedStatement = null;
		NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
		String sql = "";
		if (location.equals("0")) {
			sql = "SELECT starttime,endtime FROM apm_available_slot where commencing = '" + date
					+ "' and diaryuserid = " + diaryuser + " order by starttime limit 0,1";
		} else {
			sql = "SELECT starttime,endtime FROM apm_available_slot where commencing = '" + date
					+ "' and diaryuserid = " + diaryuser + " and location=" + location
					+ " order by starttime limit 0,1";
		}

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				notAvailableSlot.setSTime(rs.getString(1));
				notAvailableSlot.setEndTime(rs.getString(2));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return notAvailableSlot;
	}

	public DiaryManagement getApmtSlotData(String date, String diaryuser, String location) {
		PreparedStatement preparedStatement = null;
		DiaryManagement diaryManagement = new DiaryManagement();
		String sql = "";
		if (location.equals("0")) {
			sql = "SELECT starttime,endtime,apmslotid FROM apm_apmt_slot where commencing = '" + date
					+ "' and diaryuserid = " + diaryuser + " order by starttime limit 0,1";
		} else {
			sql = "SELECT starttime,endtime,apmslotid FROM apm_apmt_slot where commencing = '" + date
					+ "' and diaryuserid = " + diaryuser + " and location=" + location
					+ " order by starttime limit 0,1";
		}

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				diaryManagement.setSTime(rs.getString(1));
				diaryManagement.setEndTime(rs.getString(2));
				diaryManagement.setApmtslotid(rs.getString(3));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return diaryManagement;
	}

	public String getAppointmentDuration(String duration) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT duration FROM apm_appointment_type where id= " + duration + " ";

		try {

			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getString(1);
			}

		} catch (Exception e) {

		}

		return result;
	}

	public int UpdateDragAndDropData(String availableSlotID, String practitionerid, String startTime, String endTime,
			String slotid, String location, String commencing) {

		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_available_slot set diaryuserid=?,diaryusername=?,starttime=?,endtime=?,apmslotid=?,location=?,commencing=? where id=?";

		try {

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, practitionerid);
			String diaryUserName = getDiaryUserName(practitionerid);
			preparedStatement.setString(2, diaryUserName);
			preparedStatement.setString(3, startTime);
			preparedStatement.setString(4, endTime);
			preparedStatement.setString(5, slotid);

			int locationID = getSlotLocationId(slotid);

			preparedStatement.setInt(6, locationID);
			preparedStatement.setString(7, commencing);
			preparedStatement.setString(8, availableSlotID);

			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private int getSlotLocationId(String slotid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT location FROM apm_apmt_slot where id = " + slotid + " ";

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

	public String getDiaryUserName(String practitionerid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT initial,firstname,lastname FROM apm_user where id= '" + practitionerid + "' ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				if (rs.getString(2) == null || rs.getString(2).equals("")) {
					result = rs.getString(2) + " " + rs.getString(3);

				} else {
					result = rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getSelecedTreatmentEpisodeId(int selectedid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT treatmentEpisodeId FROM apm_available_slot where id = " + selectedid + " ";
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

	public ArrayList<NotAvailableSlot> getAvailableSlotList(String avlbltyDate, String chdiaryUser, String chlocation) {
		PreparedStatement preparedStatement = null;
		ArrayList<NotAvailableSlot> list = new ArrayList<NotAvailableSlot>();
		StringBuffer str = new StringBuffer();
		str.append(
				"SELECT id, diaryuserid,diaryuser,location,room,starttime,endtime FROM apm_apmt_slot where commencing='"
						+ avlbltyDate + "' ");
		str.append("and diaryuser like('%" + chdiaryUser + "%') and location like('%" + chlocation + "%') ");

		try {

			preparedStatement = connection.prepareStatement(str.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
				notAvailableSlot.setId(rs.getInt(1));
				notAvailableSlot.setDiaryUserId(rs.getInt(2));
				notAvailableSlot.setDiaryUser(rs.getString(3));
				notAvailableSlot.setLocation(rs.getString(4));
				notAvailableSlot.setRoom(rs.getString(5));
				notAvailableSlot.setsTime(rs.getString(6));
				notAvailableSlot.setEndTime(rs.getString(7));

				list.add(notAvailableSlot);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public String getExistStartTime(String commencing, String location, String diaryuserId, String starttime,
			String endtime, int editAppointId) {

		starttime = starttime + ":" + "00";
		endtime = endtime + ":" + "00";
		PreparedStatement preparedStatement = null;
		String result = "";
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT starttime FROM apm_available_slot  where ");
		sql.append(
				"commencing='" + commencing + "' and diaryuserid=" + diaryuserId + " and location='" + location + "' ");
		sql.append("and NOT ('" + starttime + "' > endtime OR '" + endtime + "' < starttime) ");

		try {

			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int coutnEsistingSlot(String commencing, String location, String diaryuserId, String starttime,
			String endtime, int editAppointId) {

		starttime = starttime + ":" + "00";
		endtime = endtime + ":" + "00";
		PreparedStatement preparedStatement = null;
		int result = 0;
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT count(*) FROM apm_available_slot  where ");
		sql.append(
				"commencing='" + commencing + "' and diaryuserid=" + diaryuserId + " and location='" + location + "' ");
		sql.append("and NOT ('" + starttime + "' > endtime OR '" + endtime + "' < starttime) ");

		try {

			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean checkEventAllreadyExist(String commencing, String location, String diaryuserId, String starttime,
			String endtime) {
		starttime = starttime + ":" + "00";
		endtime = endtime + ":" + "00";
		PreparedStatement preparedStatement = null;
		boolean result = false;
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT * FROM apm_available_slot  where ");
		sql.append("commencing='" + commencing + "' and diaryuserid='" + diaryuserId + "' and location='" + location
				+ "' ");
		sql.append("and NOT ('" + starttime + "' > endtime OR '" + endtime + "' < starttime) ");

		try {

			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public NotAvailableSlot getAvailableSlotdata(int editAppointId) {
		PreparedStatement preparedStatement = null;
		NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
		// String sql = "SELECT starttime,endtime FROM apm_available_slot where
		// id="+editAppointId+" ";

		String sql = "SELECT starttime,endtime,commencing,diaryusername,clientname,clientid,diaryuserid,aptmtype,"
				+ "duration,apmttypetext,dna,opdpmnt,chrgstatus,procedures,reasonforblock,med_doc_verify,reception_vid_verify,"
				+ "pending_verify,pending_remark,doctor_vid_reject_remark,location,whopay,drcompleted,"
				+ "added_by,condition_id,refferedfrom,dept,isPreDate,opdSequnce,charge,apmslotid,transfer_id FROM apm_available_slot "
				+ "where id = " + editAppointId + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				notAvailableSlot.setsTime(rs.getString(1));
				notAvailableSlot.setEndTime(rs.getString(2));
				notAvailableSlot.setCommencing(rs.getString(3));
				notAvailableSlot.setDiaryUser(rs.getString(4));
				notAvailableSlot.setClientName(rs.getString(5));
				notAvailableSlot.setClientId(rs.getString(6));
				notAvailableSlot.setDiaryUserId(rs.getInt(7));
				notAvailableSlot.setAppointmentid(rs.getInt(8));
				notAvailableSlot.setDuration(rs.getString(9));
				notAvailableSlot.setApmttypetext(rs.getString(10));
				notAvailableSlot.setDna(rs.getBoolean(11));
				notAvailableSlot.setOpdpmnt(rs.getInt(12));
				notAvailableSlot.setChrgstatus(rs.getInt(13));
				notAvailableSlot.setId(editAppointId);
				if (DateTimeUtils.numberCheck(rs.getString(14)).equals("0")) {
					notAvailableSlot.setProcedure("0");
				} else {
					notAvailableSlot.setProcedure("1");
				}
				notAvailableSlot.setReasonforblock(rs.getString(15));
				notAvailableSlot.setMed_doc_verify(rs.getInt(16));
				notAvailableSlot.setReception_vid_verify(rs.getInt(17));
				notAvailableSlot.setPending_verify(rs.getInt(18));
				notAvailableSlot.setPending_remark(rs.getString(19));
				notAvailableSlot.setDoctor_vid_reject_remark(DateTimeUtils.isNull(rs.getString(20)));
				notAvailableSlot.setApmtType(rs.getString(8));
				notAvailableSlot.setLocation(rs.getString(21));
				notAvailableSlot.setPayBy(rs.getString(22));
				notAvailableSlot.setDrcompleted(rs.getInt(23));
				notAvailableSlot.setAddedBy(rs.getString(24));
				notAvailableSlot.setCondition(rs.getString(25));
				notAvailableSlot.setDept(rs.getString(27));
				notAvailableSlot.setPreDate(rs.getInt(28));
				notAvailableSlot.setOpdAbbrId(rs.getString(29));
				notAvailableSlot.setCharge(rs.getDouble(30));
				notAvailableSlot.setApmtSlotId(rs.getInt(31));
				notAvailableSlot.setInvoiceid(rs.getString(32));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return notAvailableSlot;
	}

	public boolean checkEventAllreadyExist(String commencing, String location, String diaryuserId, String starttime,
			String endtime, int editAppointId) {
		starttime = starttime + ":" + "00";
		endtime = endtime + ":" + "00";
		PreparedStatement preparedStatement = null;
		boolean result = false;
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT * FROM apm_available_slot  where ");
		sql.append("commencing='" + commencing + "' and diaryuserid='" + diaryuserId + "' and location='" + location
				+ "' ");
		sql.append("and NOT ('" + starttime + "' > endtime OR '" + endtime + "' < starttime) and id!=" + editAppointId
				+ " ");

		try {

			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	public ArrayList<NotAvailableSlot> getAllAvailableSlotList(String date, String diaryuserId, String location) {
		PreparedStatement preparedStatement = null;
		ArrayList<NotAvailableSlot> list = new ArrayList<NotAvailableSlot>();
		StringBuffer str = new StringBuffer();
		str.append(
				"SELECT id,diaryuserid,diaryusername,location,room,starttime,endtime FROM apm_available_slot where commencing='"
						+ date + "' ");
		str.append("and diaryuserid like('%" + diaryuserId + "%') and location like('%" + location
				+ "%') order by starttime");
		int count = 0;
		try {

			preparedStatement = connection.prepareStatement(str.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
				String endtime = "";
				notAvailableSlot.setId(rs.getInt(1));
				notAvailableSlot.setDiaryUserId(rs.getInt(2));
				notAvailableSlot.setDiaryUser(rs.getString(3));
				notAvailableSlot.setLocation(rs.getString(4));
				notAvailableSlot.setRoom(rs.getString(5));
				notAvailableSlot.setsTime(rs.getString(6));
				notAvailableSlot.setEndTime(rs.getString(7));
				notAvailableSlot.setNewEndTime(endtime);
				endtime = getNewEndTime(date, diaryuserId, location, rs.getInt(1));

				list.add(notAvailableSlot);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	private String getNewEndTime(String date, String diaryuserId, String location, int id) {
		PreparedStatement preparedStatement = null;
		String result = "";

		StringBuffer str = new StringBuffer();
		str.append("SELECT starttime FROM apm_available_slot where commencing='" + date + "' ");
		str.append("and diaryuserid like('%" + diaryuserId + "%') and location like('%" + location + "%') and id = "
				+ id + " ");

		try {

			preparedStatement = connection.prepareStatement(str.toString());

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				result = rs.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getDuration(String selectedid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT duration FROM apm_appointment_type where id=" + selectedid + " ";

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

	public int DeleteBlockedSlot(String selectedid, String opendb) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete  FROM apm_available_slot  where id= " + selectedid + " ";
		if (opendb.equals("staff")) {
			sql = "delete  FROM his_staff_slot  where id= " + selectedid + " ";
		}

		try {

			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getStatus(String availableSlotID, String opendb) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT status FROM apm_available_slot where id= " + availableSlotID + " ";
		if (opendb.equals("staff")) {
			sql = "SELECT status FROM his_staff_slot where id= " + availableSlotID + " ";
		}
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

	public int saveDnaCharge(NotAvailableSlot notAvailableSlot, int selectedid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		int id = 0;
		String sql = "insert into apm_invoice(clientid,practitionerid,clientname,practitionername,date,chargetype,appointmentid,location) values(?,?,?,?,?,?,?,?)";

		try {

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, notAvailableSlot.getClientId());
			preparedStatement.setInt(2, notAvailableSlot.getDiaryUserId());
			preparedStatement.setString(3, notAvailableSlot.getClientName());
			preparedStatement.setString(4, notAvailableSlot.getDiaryUser());
			preparedStatement.setString(5, notAvailableSlot.getCommencing());
			preparedStatement.setString(6, "DNA");
			preparedStatement.setInt(7, selectedid);
			preparedStatement.setString(8, notAvailableSlot.getLocation());
			result = preparedStatement.executeUpdate();

			if (result == 1) {
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if (resultSet.next()) {
					id = resultSet.getInt(1);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return id;
	}

	public int saveDnaApmInvoiceAssesment(NotAvailableSlot notAvailableSlot, int invoiceid, int appointmentid,
			String dnacharge) {

		PreparedStatement preparedStatement = null;
		int result = 0;
		String clientId = notAvailableSlot.getClientId();
		// set department to charges
		UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
		AssessmentFormDAO assessmentFormDAO = new JDBCAssessmentFormDAO(connection);
		String practionerId = "0";
		String condition = "0";

		// get ipd details
		int bedid = assessmentFormDAO.getIpdBedno(clientId);
		if (bedid != 0) {
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			BedDao bedDao = new JDBCBedDao(connection);

			String admissionid = assessmentFormDAO.getAdmissionid(clientId);

			Bed bed = bedDao.getEditIpdData(admissionid);
			practionerId = bed.getPractitionerid();
			UserProfile userProfile = userProfileDAO.getUserprofileDetails(Integer.parseInt(practionerId));

			condition = userProfile.getDiciplineName();

		} else {
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			// NotAvailableSlot notAvailableSlot =
			// clientDAO.getLastAppointmentdetails(clientId);
			if (notAvailableSlot.getDiaryUserId() != 0) {
				practionerId = Integer.toString(notAvailableSlot.getDiaryUserId());
			}

			// check if doctor placed with machine

			UserProfile userProfile = userProfileDAO.getUserprofileDetails(notAvailableSlot.getDiaryUserId());

			if (userProfile.getJobgroup() == null) {
				userProfile.setJobgroup("");
			}
			if (userProfile.getJobgroup().equals("4")) {
				practionerId = userProfile.getDoctor();
			}

			if (practionerId == null) {
				practionerId = "0";
			}

			userProfile = userProfileDAO.getUserprofileDetails(Integer.parseInt(practionerId));
			condition = userProfile.getDiciplineName();

			if (practionerId.equals("0")) {
				condition = "0";
			}

		}

		String sql = "insert into apm_invoice_assesments(invoiceid,user,apmtType,charge,startTime,duration,practitionerId,practitionerName,clientId,commencing,appointmentid,paybuy,markAppointment,thirdPartyId,department) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try {

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, invoiceid);
			preparedStatement.setString(2, notAvailableSlot.getClientName());
			preparedStatement.setString(3, notAvailableSlot.getApmtType());
			preparedStatement.setString(4, dnacharge);
			preparedStatement.setString(5, notAvailableSlot.getSTime());
			preparedStatement.setString(6, notAvailableSlot.getDuration());
			preparedStatement.setInt(7, notAvailableSlot.getDiaryUserId());
			preparedStatement.setString(8, notAvailableSlot.getDiaryUser());
			preparedStatement.setString(9, notAvailableSlot.getClientId());
			preparedStatement.setString(10, notAvailableSlot.getCommencing());
			preparedStatement.setInt(11, appointmentid);
			// int payby = getPayBy(notAvailableSlot.getTreatmentEpisodeId());
			preparedStatement.setString(12, notAvailableSlot.getPayBy());
			preparedStatement.setInt(13, 1);
			int tpid = getTPid(notAvailableSlot.getClientId());
			preparedStatement.setInt(14, tpid);
			preparedStatement.setString(15, condition);

			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public int saveApmInvoiceAssesment(NotAvailableSlot notAvailableSlot, int invoiceid, int appointmentid) {

		PreparedStatement preparedStatement = null;
		int result = 0;

		String clientId = notAvailableSlot.getClientId();
		// set department to charges
		UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
		AssessmentFormDAO assessmentFormDAO = new JDBCAssessmentFormDAO(connection);
		String practionerId = "0";
		String condition = "0";

		// get ipd details
		int bedid = assessmentFormDAO.getIpdBedno(clientId);
		if (bedid != 0) {
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			BedDao bedDao = new JDBCBedDao(connection);

			String admissionid = assessmentFormDAO.getAdmissionid(clientId);

			Bed bed = bedDao.getEditIpdData(admissionid);
			practionerId = bed.getPractitionerid();
			UserProfile userProfile = userProfileDAO.getUserprofileDetails(Integer.parseInt(practionerId));

			condition = userProfile.getDiciplineName();

		} else {
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			// NotAvailableSlot notAvailableSlot =
			// clientDAO.getLastAppointmentdetails(clientId);
			if (notAvailableSlot.getDiaryUserId() != 0) {
				practionerId = Integer.toString(notAvailableSlot.getDiaryUserId());
			}

			// check if doctor placed with machine

			UserProfile userProfile = userProfileDAO.getUserprofileDetails(notAvailableSlot.getDiaryUserId());

			if (userProfile.getJobgroup() == null) {
				userProfile.setJobgroup("");
			}
			if (userProfile.getJobgroup().equals("4")) {
				practionerId = userProfile.getDoctor();
			}

			if (practionerId == null) {
				practionerId = "0";
			}

			userProfile = userProfileDAO.getUserprofileDetails(Integer.parseInt(practionerId));
			condition = userProfile.getDiciplineName();

			if (practionerId.equals("0")) {
				condition = "0";
			}

		}

		String sql = "insert into apm_invoice_assesments(invoiceid,user,apmtType,charge,startTime,duration,practitionerId,practitionerName,clientId,commencing,appointmentid,paybuy,markAppointment,thirdPartyId,department) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try {

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, invoiceid);
			preparedStatement.setString(2, notAvailableSlot.getClientName());
			preparedStatement.setString(3, notAvailableSlot.getApmtType());
			preparedStatement.setString(4, Double.toString(notAvailableSlot.getCharge()));
			preparedStatement.setString(5, notAvailableSlot.getSTime());
			preparedStatement.setString(6, notAvailableSlot.getDuration());
			preparedStatement.setInt(7, notAvailableSlot.getDiaryUserId());
			preparedStatement.setString(8, notAvailableSlot.getDiaryUser());
			preparedStatement.setString(9, notAvailableSlot.getClientId());
			preparedStatement.setString(10, notAvailableSlot.getCommencing());
			preparedStatement.setInt(11, appointmentid);
			// int payby = getPayBy(notAvailableSlot.getTreatmentEpisodeId());
			preparedStatement.setString(12, notAvailableSlot.getPayBy());
			preparedStatement.setInt(13, 1);
			int tpid = getTPid(notAvailableSlot.getClientId());
			preparedStatement.setInt(14, tpid);
			preparedStatement.setString(15, condition);

			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	private int getPayBy(String treatmentEpisodeId) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT payby FROM apm_treatment_episode where id = " + treatmentEpisodeId + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				String payby = rs.getString(1);
				if (payby.equals(Constants.PAY_BY_THIRD_PARTY)) {
					result = 1;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	private int getTPid(String clientId) {

		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT third_party_id FROM apm_patient where id = " + clientId + " ";

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

	public int deleteDnaInvoiceAssesment(int selectedid) {

		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from apm_invoice_assesments where appointmentid = " + selectedid + " ";

		try {

			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public int deleteDnaInvoice(int selectedid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from apm_invoice where appointmentid = " + selectedid + " ";

		try {

			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public NotAvailableSlot getAvailableSlotDnadata(int selectedid) {
		PreparedStatement preparedStatement = null;
		NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
		String sql = "SELECT commencing,starttime,duration,diaryuserid,diaryusername,location,clientname,apmttypetext,clientid,charge,treatmentEpisodeId,whopay,dnareason,dnanotes,usedsession,aptmtype,dna,dnaoffset FROM apm_available_slot where id = "
				+ selectedid + " ";

		try {

			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				notAvailableSlot.setCommencing(rs.getString(1));
				notAvailableSlot.setSTime(rs.getString(2));
				notAvailableSlot.setDuration(rs.getString(3));
				notAvailableSlot.setDiaryUserId(rs.getInt(4));
				notAvailableSlot.setDiaryUser(rs.getString(5));
				notAvailableSlot.setLocation(rs.getString(6));
				notAvailableSlot.setClientName(rs.getString(7));
				notAvailableSlot.setApmtType(rs.getString(8));
				notAvailableSlot.setClientId(rs.getString(9));
				notAvailableSlot.setCharge(rs.getDouble(10));
				notAvailableSlot.setTreatmentEpisodeId(rs.getString(11));
				notAvailableSlot.setWhopay(rs.getString(12));
				notAvailableSlot.setDnaReason(rs.getString(13));
				notAvailableSlot.setDnaNotes(rs.getString(14));
				notAvailableSlot.setUsedsession(rs.getString(15));
				notAvailableSlot.setAppointmentTypeid(rs.getInt(16));
				notAvailableSlot.setDna(rs.getBoolean(17));
				notAvailableSlot.setAppointmentDnaOffset(rs.getBoolean(18));

				boolean dnaoffset = getDNAOffset(notAvailableSlot.getAppointmentTypeid());
				notAvailableSlot.setDnaOffset(dnaoffset);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return notAvailableSlot;
	}

	private boolean getDNAOffset(int appointmentTypeid) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "SELECT dnaoffset FROM apm_appointment_type where id = " + appointmentTypeid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getBoolean(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateSessions(int treatmeId) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_treatment_episode set usedsession=? where id= " + treatmeId + "";

		try {

			preparedStatement = connection.prepareStatement(sql);
			int usedSeesion = getUsedSessions(treatmeId);
			usedSeesion = usedSeesion - 1;
			preparedStatement.setInt(1, usedSeesion);

			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private int getUsedSessions(int treatmeId) {
		PreparedStatement preparedStatement = null;
		int usedsession = 0;
		String sql = "select usedsession from apm_treatment_episode where id = " + treatmeId + "";

		try {
			preparedStatement = connection.prepareStatement(sql);

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				usedsession = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return usedsession;
	}

	public int saveApmtInLog(int appointmentid, String date, String time, String userId, String clientId,
			String commencingTemp, String apmt_start_time, String status, String lastModifiedDate) {

		int result = 0;

		/*
		 * SimpleDateFormat sdf = new SimpleDateFormat("d-MM-yyyy hh:mm:ss"); Calendar
		 * now = Calendar.getInstance(); now.add(Calendar.HOUR, 5);
		 * now.add(Calendar.MINUTE, 30); String date1 = sdf.format(now.getTime());
		 * System.out.println(date1);
		 */

		PreparedStatement pstm = null;
		String sql = "insert into apm_appointment_log(appmt_id,date,time,performed_by,clientId,commencing,apmt_start_time,status)values(?,?,?,?,?,?,?,?)";
		try {
			pstm = connection.prepareStatement(sql);
			pstm.setInt(1, appointmentid);
			pstm.setString(2, lastModifiedDate);
			pstm.setString(3, time);
			pstm.setString(4, userId);
			pstm.setString(5, clientId);
			pstm.setString(6, commencingTemp);
			pstm.setString(7, apmt_start_time);
			pstm.setString(8, status);

			result = pstm.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
//			StringWriter sw = new StringWriter();
//	         PrintWriter pw = new PrintWriter(sw);
//	         e.printStackTrace(pw);
//			Tra.exceptionMail(sw.toString());

		}

		return result;
	}

	public String getClientId(String availableSlotID) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT clientId FROM apm_available_slot where id = " + availableSlotID + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				result = rs.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int saveDnaLog(String clientId, String date, String time, String userId, int selectedid) {
		int result = 0;

		PreparedStatement pstm = null;
		String sql = "insert into apm_appointment_log(appmt_id,date,time,performed_by,clientId,status)values(?,?,?,?,?,?)";
		try {
			pstm = connection.prepareStatement(sql);
			pstm.setInt(1, selectedid);
			pstm.setString(2, date);
			pstm.setString(3, time);
			pstm.setString(4, userId);
			pstm.setString(5, clientId);
			pstm.setString(6, "dna");

			result = pstm.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();

		}

		return result;
	}

	public int getOtAppointmentSlotID(String commencing, int diaryUserId, String clientstarttime, String clientendtime,
			String clientlocation) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT id FROM apm_apmt_slot where commencing = '" + commencing + "' and diaryuserid="
				+ diaryUserId + " and starttime <= '" + clientstarttime + "' and endtime >='" + clientendtime + "' ";

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

	public int getAppointmentSlotID(String commencing, int diaryUserId, String clientstarttime, String clientendtime,
			String clientlocation) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT id FROM apm_apmt_slot where commencing = '" + commencing + "' and diaryuserid="
				+ diaryUserId + " and starttime <= '" + clientstarttime + "' and endtime >='" + clientendtime
				+ "' and location = " + clientlocation + " ";

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

	public double getPendingBalanceTotal(String clientId) {
		PreparedStatement preparedStatement = null;
		double result = 0.0;
		String sql = "SELECT sum(debit) FROM apm_charges_invoice where clientid = " + clientId + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				double debit = rs.getDouble(1);
				double paid = getPaidAmountTotal(clientId);
				result = debit - paid;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private double getPaidAmountTotal(String clientId) {
		PreparedStatement preparedStatement = null;
		double result = 0.0;
		String sql = "SELECT sum(payment) FROM apm_charges_payment where clientid = " + clientId + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				result = rs.getDouble(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getUsedSession(int treatmeId, String selectedid) {
		PreparedStatement preparedStatement = null;
		int usedsession = 0;
		String sql = "select usedsession from apm_available_slot where treatmentEpisodeId = " + treatmeId + " and id = "
				+ selectedid + "";

		try {
			preparedStatement = connection.prepareStatement(sql);

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				usedsession = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return usedsession;

	}

	public ArrayList<NotAvailableSlot> getUsedSessionList(int treatmeId, int usedSession) {
		PreparedStatement preparedStatement = null;
		ArrayList<NotAvailableSlot> list = new ArrayList<NotAvailableSlot>();
		String sql = "select id from apm_available_slot where treatmentEpisodeId= " + treatmeId + " and usedsession >= "
				+ usedSession + "";

		try {

			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
				notAvailableSlot.setId(rs.getInt(1));

				list.add(notAvailableSlot);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}

	public int updateAllPrevious(int id, int treatmeId) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_available_slot set usedsession=? where id = " + id + "";

		try {

			preparedStatement = connection.prepareStatement(sql);
			int usedSeesion = getUsedSession(treatmeId, Integer.toString(id));
			usedSeesion = usedSeesion - 1;
			preparedStatement.setInt(1, usedSeesion);

			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	public String getConditionPatient(String clientId) {
		String id = "";
		PreparedStatement preparedStatement = null;
		String sql = "select treatment_type from apm_patient where id = " + clientId + "";

		try {

			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				id = rs.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return id;
	}

	public int updateCondition(String clientId, String conditionID) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_patient set treatment_type =" + conditionID + " where id = " + clientId + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getUserListToalCount() {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select count(*) from apm_user where usertype = 4 and jobtitle='Practitioner' ";

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

	public int saveActivity(int clientId, int activity) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_activity (clientId,activity) values(?,?)";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, clientId);
			preparedStatement.setInt(2, activity);
			result = preparedStatement.executeUpdate();

		} catch (Exception e) {

		}
		return result;
	}

	public boolean checkDurationExist(String duration) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "SELECT * FROM apm_appointment_type where duration = '" + duration + "' ";

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

	public String getEditExistStartTime(String commencing, String location, String diaryuserId, String starttime,
			String endtime, int editAppointId) {
		starttime = starttime + ":" + "00";
		endtime = endtime + ":" + "00";
		PreparedStatement preparedStatement = null;
		String result = "";
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT starttime FROM apm_available_slot  where ");
		sql.append(
				"commencing='" + commencing + "' and diaryuserid=" + diaryuserId + " and location='" + location + "' ");
		sql.append(
				"and NOT ('" + starttime + "' > endtime OR '" + endtime + "' < starttime) order by id desc limit 1 ");

		try {

			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public double getDNAAercentage() {
		PreparedStatement preparedStatement = null;
		double result = 0.0;
		String sql = "SELECT dnaCharge FROM apm_dnacharge ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getDouble(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public ThirdParty getTPDNALimit(String clientId) {
		PreparedStatement preparedStatement = null;
		ThirdParty thirdParty = new ThirdParty();
		StringBuffer sql = new StringBuffer();

		sql.append(
				"SELECT dnaLimit,dnaforall,apm_third_party_details.id,dnaoffset FROM apm_third_party_details inner join apm_patient on ");
		sql.append("apm_patient.third_party_name_id = apm_third_party_details.id where apm_patient.id = " + clientId
				+ " ");

		try {

			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				thirdParty.setDnaLimit(rs.getString(1));
				thirdParty.setDnaForAll(rs.getBoolean(2));
				thirdParty.setTypeName(rs.getString(3));
				thirdParty.setOffset(rs.getBoolean(4));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return thirdParty;
	}

	public NotAvailableSlot getApmtDetailsForLog(int selectedid) {
		PreparedStatement preparedStatement = null;
		NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
		String sql = "SELECT commencing,starttime,duration,diaryuserid,diaryusername,location,clientname,apmttypetext,clientid,charge,treatmentEpisodeId,whopay FROM apm_available_slot where id = "
				+ selectedid + " ";

		try {

			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				notAvailableSlot.setCommencing(rs.getString(1));
				notAvailableSlot.setSTime(rs.getString(2));
				notAvailableSlot.setDuration(rs.getString(3));
				notAvailableSlot.setDiaryUserId(rs.getInt(4));
				notAvailableSlot.setDiaryUser(rs.getString(5));
				notAvailableSlot.setLocation(rs.getString(6));
				notAvailableSlot.setClientName(rs.getString(7));
				notAvailableSlot.setApmtType(rs.getString(8));
				notAvailableSlot.setClientId(rs.getString(9));
				notAvailableSlot.setCharge(rs.getDouble(10));
				notAvailableSlot.setTreatmentEpisodeId(rs.getString(11));
				notAvailableSlot.setWhopay(rs.getString(12));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return notAvailableSlot;
	}

	public double getInvoiceCharge(int selectedid) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT charge FROM apm_invoice_assesments inner join apm_invoice on ");
		sql.append("apm_invoice.id = apm_invoice_assesments.invoiceid where apm_invoice.appointmentid = " + selectedid
				+ " ");

		try {
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getDouble(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getDNAInvoicePayBy(int selectedid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		// String sql = "SELECT paybuy FROM apm_invoice_assesments where invoiceid =
		// "+selectedid+"";
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT paybuy FROM apm_invoice_assesments inner join apm_invoice on ");
		sql.append("apm_invoice.id = apm_invoice_assesments.invoiceid where apm_invoice.appointmentid = " + selectedid
				+ " ");

		try {
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateDNAInvoiceAssesment(String editdnaCharge, String dnapayby, int invoiceid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_invoice_assesments set paybuy=?,charge=? where invoiceid=? ";

		try {

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, dnapayby);
			preparedStatement.setString(2, editdnaCharge);
			preparedStatement.setInt(3, invoiceid);

			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public int getDNAInvoiceid(int selectedid) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		int result = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT apm_invoice.id FROM apm_invoice_assesments inner join apm_invoice on ");
		sql.append("apm_invoice.id = apm_invoice_assesments.invoiceid where apm_invoice.appointmentid = " + selectedid
				+ " ");

		try {
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public int saveCancelApmtInLog(int appointmentid, String date, String time, String userId, String clientId,
			String commencingTemp, String sTime, String apmtstatus, String cancelApmtNote, String lastModified,
			NotAvailableSlot notAvailableSlot) {

		int result = 0;

		/*
		 * SimpleDateFormat sdf = new SimpleDateFormat("d-MM-yyyy hh:mm:ss"); Calendar
		 * now = Calendar.getInstance(); now.add(Calendar.HOUR, 5);
		 * now.add(Calendar.MINUTE, 30); String date1 = sdf.format(now.getTime());
		 * System.out.println(date1);
		 */

		PreparedStatement pstm = null;
		String sql = "insert into apm_appointment_log(appmt_id,date,time,performed_by,clientId,commencing,apmt_start_time,status,cancel_apmts_notes,practitoner,apmttype,location,charges,diaryuserid)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			pstm = connection.prepareStatement(sql);
			pstm.setInt(1, appointmentid);
			pstm.setString(2, lastModified);
			pstm.setString(3, time);
			pstm.setString(4, userId);
			pstm.setString(5, clientId);
			pstm.setString(6, commencingTemp);
			pstm.setString(7, sTime);
			pstm.setString(8, apmtstatus);
			pstm.setString(9, cancelApmtNote);
			pstm.setString(10, notAvailableSlot.getDiaryUser());
			pstm.setString(11, notAvailableSlot.getApmtType());

			ClinicDAO clinicListDAO = new JDBCClinicDAO(connection);
			Clinic clinic = new Clinic();
			clinic = clinicListDAO.getLocationDetails(Integer.parseInt(notAvailableSlot.getLocation()));

			pstm.setString(12, clinic.getCity());
			pstm.setDouble(13, notAvailableSlot.getCharge());
			pstm.setInt(14, notAvailableSlot.getDiaryUserId());		
			result = pstm.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();

		}

		return result;
	}

	public int getPreviousTreatmentUsedSession(int selectedTreatmentEpisodeId) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT usedsession FROM apm_treatment_episode where id = " + selectedTreatmentEpisodeId + " ";

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

	public int updatePreviousTreatmentEpisode(int prevTreatmentSession, int selectedTreatmentEpisodeId) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_treatment_episode set usedsession=? where id=? ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, prevTreatmentSession);
			preparedStatement.setInt(2, selectedTreatmentEpisodeId);

			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getSpecificDnaCharge(int selectedid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		StringBuffer sql = new StringBuffer();
		sql.append("select dnacharge from apm_appointment_type inner join apm_available_slot on ");
		sql.append("apm_available_slot.aptmtype = apm_appointment_type.id where apm_available_slot.id = " + selectedid
				+ " ");

		try {
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public int IncraeseDnaOffset(int selectedid, int usedsession) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_available_slot set usedsession=? where id=? ";

		try {

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, usedsession);
			preparedStatement.setInt(2, selectedid);

			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getTpEpisodeUsedSession(String tepisodeid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT usedsession FROM apm_treatment_episode where id = " + tepisodeid + " ";

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

	public int updateupdateTpEpisodeusedsession(String tepisodeid, int usedsession) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_treatment_episode set usedsession=? where id=" + tepisodeid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, usedsession);

			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateDnaOffset(boolean dnaOffset, int appointmentTypeid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_appointment_type set dnaoffset=? where id=" + appointmentTypeid + " ";

		try {

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setBoolean(1, dnaOffset);

			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateAppointmentDnaoffset(int selectedid, boolean dnaOffset) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_available_slot set dnaoffset=? where id=" + selectedid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setBoolean(1, dnaOffset);

			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public ArrayList<NotAvailableSlot> getDnaOffsetList(String treatmentEpisodeId) {
		PreparedStatement preparedStatement = null;
		ArrayList<NotAvailableSlot> list = new ArrayList<NotAvailableSlot>();
		String sql = "SELECT id,usedsession FROM apm_available_slot where treatmentepisodeid = " + treatmentEpisodeId
				+ " and dnaoffset = 0 ";

		try {

			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
				notAvailableSlot.setId(rs.getInt(1));
				notAvailableSlot.setUsedsession(rs.getString(2));

				list.add(notAvailableSlot);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int updateAppointmentUsedSession(int id, int usedsession) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_available_slot set usedsession=? where id=? ";

		try {

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, usedsession);
			preparedStatement.setInt(2, id);

			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public int updateClientCondition(String clientId, String condition) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_patient set treatment_type=? where id=" + clientId + "";

		try {

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, condition);

			result = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public NotAvailableSlot getInitilizedElementData(String diaryuserid, String commencing, String selectedStarttime,
			String prevEndTime) {

		PreparedStatement preparedStatement = null;
		NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
		String sql = "SELECT id,starttime,endtime,location  FROM apm_apmt_slot where commencing = '" + commencing
				+ "' and diaryuserid = " + diaryuserid + " and endtime>'" + selectedStarttime
				+ "' order by starttime limit 1";

		try {

			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				notAvailableSlot.setApmtSlotId(rs.getInt(1));
				notAvailableSlot.setSTime(rs.getString(2));
				notAvailableSlot.setEndTime(rs.getString(3));
				notAvailableSlot.setLocation(rs.getString(4));

				UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
				UserProfile userProfile = userProfileDAO.getUserprofileDetails(Integer.parseInt(diaryuserid));
				notAvailableSlot.setDisciplineid(userProfile.getDiciplineName());

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return notAvailableSlot;
	}

	public ArrayList<String> getRemainderAppointmentList(String dt) {
		PreparedStatement preparedStatement = null;
		ArrayList<String> list = new ArrayList<String>();
		String sql = "SELECT id FROM apm_available_slot where commencing = '" + dt + "' and status = 0";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				list.add(rs.getString(1));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int getAdDurationData(int clinicid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT ad FROM apm_email_configure where loginid = " + clinicid + " ";

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

	public String getDiaryUserFullname(String diaryuser) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select concat(initial,' ',firstname,' ',lastname) from apm_user where id = '" + diaryuser + "' ";

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

	public String getLocationName(String location) {
		PreparedStatement preparedStatement = null;
		StringBuffer buffer= new StringBuffer();
		String result = "";
		buffer.append("SELECT location FROM apm_clinic_location where id = '" + location + "' ");

		try {

			//preparedStatement = connection.prepareStatement(sql);
			preparedStatement=connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int saveLoggedInUserID(String userid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_logged_user(userid) values(?) ";

		try {

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userid);

			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getApmLoggedUserList(String clinicid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		// String sql = "select userid from apm_logged_user ";
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT apm_logged_user.userid FROM apm_logged_user inner join apm_user on ");
		sql.append("apm_logged_user.userid = apm_user.userid where clinicid='" + clinicid + "' ");

		StringBuffer str = new StringBuffer();
		try {
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				str.append(rs.getString(1) + ",");
			}

			if (str.length() > 0) {
				result = str.substring(0, str.length() - 1);
			}

			result = clinicid + "," + result;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public boolean checLoggedkUseridExist(String userid) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "SELECT * FROM apm_logged_user where userid = '" + userid + "' ";

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

	public String getSlotCommencingDate(String slotid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT commencing FROM apm_apmt_slot where id = " + slotid + " ";

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

	public NotAvailableSlot getTreatmentDetails(int apptid) {
		PreparedStatement preparedStatement = null;
		NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
		String sql = "SELECT clientid,whopay,treatmentepisodeid FROM apm_available_slot where id = " + apptid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				notAvailableSlot.setClientId(rs.getString(1));
				notAvailableSlot.setWhopay(rs.getString(2));
				notAvailableSlot.setTreatmentEpisodeId(rs.getString(3));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return notAvailableSlot;
	}

	public NotAvailableSlot getApmtConsNoteData(String apmtid) {
		PreparedStatement preparedStatement = null;
		NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
		String sql = "SELECT clientid,diaryuserid,condition_id,treatmentEpisodeId,whopay,diaryuserid,location FROM apm_available_slot  where id = "
				+ apmtid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				notAvailableSlot.setClientId(rs.getString(1));
				notAvailableSlot.setDiaryUserId(rs.getInt(2));
				notAvailableSlot.setCondition(rs.getString(3));
				notAvailableSlot.setTreatmentEpisodeId(rs.getString(4));
				notAvailableSlot.setWhopay(rs.getString(5));
				notAvailableSlot.setDiaryUserId(rs.getInt(6));
				notAvailableSlot.setLocation(rs.getString(7));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return notAvailableSlot;
	}

	public String getDiaryUserIdName(int diaryUserId) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select userid from apm_user where id = " + diaryUserId + " ";

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

	public boolean checkApmtExist(String clientId) {
		PreparedStatement preparedStatement = null;
		boolean resule = false;
		String sql = "SELECT * FROM apm_available_slot where clientid=" + clientId + " ";

		try {

			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				resule = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resule;
	}

	public int updateNewPtsStatus(String clientId, int sts) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_patient set newpts = " + sts + " where id = " + clientId + "";

		try {
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getDiaryDuration(String slotid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT apmtduration FROM apm_apmt_slot where id = '" + slotid + "' ";

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

	public ArrayList<Master> getMedicineList(String selectedid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Master> list = new ArrayList<Master>();
		String sql = "SELECT id,drug,genericname FROM apm_medicine_details where categeory = " + selectedid
				+ " order by drug ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				master.setGenericname(rs.getString(3) + " (" + master.getName() + ")");

				list.add(master);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Priscription> getFollowupApmtList(String followupdate) {
		PreparedStatement preparedStatement = null;
		ArrayList<Priscription> list = new ArrayList<Priscription>();
		String sql = "SELECT clientid,advoice,followupdate FROM apm_client_parent_priscription where followupdate = '"
				+ followupdate + "' ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Priscription priscription = new Priscription();
				priscription.setClientId(rs.getString(1));
				priscription.setAdvoice(rs.getString(2));
				priscription.setFollowupdate(DateTimeUtils.getCommencingDate1(rs.getString(3)));

				ClientDAO clientDAO = new JDBCClientDAO(connection);
				Client client = clientDAO.getClientDetails(priscription.getClientId());
				String clientname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();
				priscription.setClientname(clientname);

				boolean checkfollowupapmtexist = checkfollowupapmtexist(priscription.getClientId(), followupdate);
				if (!checkfollowupapmtexist) {
					list.add(priscription);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private boolean checkfollowupapmtexist(String clientId, String followupdate) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "SELECT * FROM apm_available_slot where clientid = " + clientId + " and commencing = '"
				+ followupdate + "' ";

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

	public int updateAD(String appointmentid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_available_slot set ad=1 where id = " + appointmentid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<UserProfile> getOTstaffList() {
		PreparedStatement preparedStatement = null;
		ArrayList<UserProfile> list = new ArrayList<UserProfile>();
		ArrayList<UserProfile> otlist = new ArrayList<UserProfile>();
		String sql = "select id, concat(initial, ' ', firstname, ' ', lastname),jobtitle from apm_user where jobtitle!='OT' and hasdiary = 1 ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				UserProfile userProfile = new UserProfile();
				userProfile.setId(rs.getInt(1));
				userProfile.setFullname(rs.getString(2));
				userProfile.setJobtitle(rs.getString(3));
				list.add(userProfile);
			}

			/*
			 * otlist = getotList(); otlist.addAll(list);
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private ArrayList<UserProfile> getotList() {
		PreparedStatement preparedStatement = null;
		ArrayList<UserProfile> list = new ArrayList<UserProfile>();
		String sql = "select id,firstname,jobtitle from apm_user where jobtitle='OT' ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				UserProfile userProfile = new UserProfile();
				userProfile.setId(rs.getInt(1));
				userProfile.setFullname(rs.getString(2));
				userProfile.setJobtitle(rs.getString(3));

				list.add(userProfile);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	public int saveParenrotData(String commencing, String selectedot, String asistantdoctor, int apmtid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		int otid = 0;
		String sql = "insert into apm_ot_parent(commencing,otroomid,asistantdoctor,apmtid) value(?,?,?,?)";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, commencing);
			preparedStatement.setString(2, selectedot);
			preparedStatement.setString(3, asistantdoctor);
			preparedStatement.setInt(4, apmtid);
			result = preparedStatement.executeUpdate();

			if (result == 1) {
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if (resultSet.next()) {
					otid = resultSet.getInt(1);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return otid;
	}

	public int checkotAppointment(String selectedid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT otid FROM apm_available_slot where id = " + selectedid + " ";

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

	public int deleteOtApmt(int otid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete FROM apm_available_slot where otid = " + otid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<UserProfile> getOTDoctorList() {
		PreparedStatement preparedStatement = null;
		ArrayList<UserProfile> list = new ArrayList<UserProfile>();
		String sql = "select id from apm_user where hasdiary = 1 ";

		try {

			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				UserProfile userProfile = new UserProfile();
				userProfile.setId(rs.getInt(1));

				list.add(userProfile);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public String getSelectedDiagnosisID(String editAppointId) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT condition_id FROM apm_available_slot where id = " + editAppointId + "  ";

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

	public int updateApmtDiagnosis(String editAppointId, String conid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_available_slot set condition_id=" + conid + " where id = " + editAppointId + " ";

		try {

			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public boolean checkOtChargeExist(String editAppointId) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "select * from apm_invoice where appointmentid = " + editAppointId + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = true;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return result;
	}

	public int addMultiConditionstoEmr(String editAppointId, String moreconditions) {

		int result = 0;
		try {

			String sql = "insert into apm_multi_condition (apmtid,conditionid) values (?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, editAppointId);
			ps.setString(2, moreconditions);

			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public int updateConsultationEmr(String editAppointId, String conid) {

		int result = 0;

		try {

			String sql = "update apm_consultation_note set condition_id=" + conid + " where appointmentid="
					+ editAppointId + "";
			PreparedStatement ps = connection.prepareStatement(sql);

			result = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public String getDiagnosisNameFromID(String condition) {

		String dignosisName = "";
		try {

			String sql = "select name from apm_condition where id=" + condition + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				dignosisName = rs.getString(1);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return dignosisName;
	}

	public ArrayList<Diagnosis> getMultipleConditions(String text) {

		ArrayList<Diagnosis> list = new ArrayList<Diagnosis>();
		String sql = "";
		try {

			if (text == "") {
				sql = "select id,name from apm_condition";
			} else {

				sql = "select id,name from apm_condition where name like '" + text + "%' or name like '%" + text + "'";
			}

			PreparedStatement ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				Diagnosis diagnosis = new Diagnosis();
				diagnosis.setId(rs.getInt(1));
				diagnosis.setName(rs.getString(2));
				list.add(diagnosis);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return list;
	}

	public String getMultipleDiagnosis(String editAppointId) {

		String str = null;

		try {

			String sql = "select conditionid from apm_multi_condition where apmtid=" + editAppointId + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				str = rs.getString(1);

			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return str;
	}

	/*
	 * public int updateWorkCompleted(int selectedid, String status,String datetime)
	 * { PreparedStatement preparedStatement = null; int result = 0;
	 * 
	 * if(status.equals("0")){ datetime = "0"; }
	 * 
	 * String sql = "update his_staff_slot set work="+status+",workcompleted='"+
	 * datetime+"' where id = "+selectedid+" ";
	 * 
	 * try{ preparedStatement = connection.prepareStatement(sql); result =
	 * preparedStatement.executeUpdate();
	 * 
	 * }catch (Exception e) { e.printStackTrace(); } return result; }
	 */
	/* adarsh changes */
	public int updateWorkCompleted(int selectedid, String status, String datetime, String cancelnotes) {
		PreparedStatement preparedStatement = null;
		int result = 0;

		if (status.equals("0")) {
			datetime = "0";
		}

		String sql = "update his_staff_slot set work=" + status + ",workcompleted='" + datetime + "',cancelnotes ='"
				+ cancelnotes + "' where id = " + selectedid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int addOpdConditionReport(int apmtid, String clientid, String conditionid, String lastmodified) {

		int result = 0;
		try {

			String sql = "insert into apm_opd_condition (apmtid, clientid, conditionid, lastmodified) values (?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, apmtid);
			ps.setString(2, clientid);
			ps.setString(3, conditionid);
			ps.setString(4, lastmodified);
			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public int deleteOpdConditionifExistsReport(String editAppointId) {

		int result = 0;
		try {
			String sql = "delete from apm_opd_condition where apmtid=" + editAppointId + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			result = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	public String getDiaryUserId(String appoinmentid) {

		String diaruuserid = "0";
		try {

			String sql = "select diaryuserid from apm_available_slot where id=" + appoinmentid + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				diaruuserid = rs.getString(1);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return diaruuserid;
	}

	public ArrayList<Master> getProcedureList(String department) {
		PreparedStatement preparedStatement = null;
		ArrayList<Master> list = new ArrayList<Master>();
		String sql = "SELECT * FROM apm_newchargetype where procedures = 1 and description=" + department + "";

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
			// TODO: handle exception
		}
		return list;
	}

	public int updateOTdata(NotAvailableSlot notAvailableSlot, int selectedid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_available_slot set category=?, procedures=?, surgeon=?, anesthesia=?,anidoctorfees=?,psurcharge=?,panetcharge=?,sic=? where id = "
				+ selectedid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, notAvailableSlot.getOtplan());
			preparedStatement.setString(2, notAvailableSlot.getProcedure());
			preparedStatement.setString(3, notAvailableSlot.getSurgeon());
			preparedStatement.setString(4, notAvailableSlot.getAnesthesia());
			preparedStatement.setString(5, notAvailableSlot.getAnifees());
			preparedStatement.setString(6, notAvailableSlot.getPsurcharge());
			preparedStatement.setString(7, notAvailableSlot.getPanetcharge());
			preparedStatement.setString(8, notAvailableSlot.getSic());

			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public int deleteAsistantDoctor(int selectedid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from apm_ot_parent where apmtid = " + selectedid + " ";

		try {

			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
		}

		return result;
	}

	public int deleteBlockOt(String aomtid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from apm_available_slot where blockot = " + aomtid + " ";

		try {

			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
		}

		return result;
	}

	public String getTemplateText(String id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select othertemplate_text from apm_other_template  where id = " + id + " ";

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

	public NotAvailableSlot getOTData(String apmtid) {
		PreparedStatement preparedStatement = null;
		NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
		String sql = "SELECT ipdno,clientname,surgeon,apmttypetext,anesthesia,commencing,clientid,timeofincision, ansintime, otnotes,imgdata,anidoctorfees,psurcharge,panetcharge,procedures,charge,sic,assistaffcharge,diaryuserid,clientid,aptmtype,transfer_id FROM apm_available_slot where id = "
				+ apmtid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {

				notAvailableSlot.setIpdno(rs.getString(1));
				notAvailableSlot.setClientName(rs.getString(2));
				UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
				// 05 jan 2018 set surgon name from reference table
				UserProfile userProfile = userProfileDAO.getUserprofileDetails(rs.getInt(3));
				notAvailableSlot.setSurgeonid(rs.getInt(3));
				String surgeon = userProfile.getInitial() + " " + userProfile.getFirstname() + " "
						+ userProfile.getLastname();
				// String surgeon = getOtAnisthesiaDoctorName(rs.getString(3));
				notAvailableSlot.setSurgeon(surgeon);
				notAvailableSlot.setApmttypetext(rs.getString(4));
				// userProfile = userProfileDAO.getUserprofileDetails(rs.getInt(5));
				// String anesthesia = userProfile.getInitial() + " " +
				// userProfile.getFirstname() + " " + userProfile.getLastname();
				String anesthesia = getOtAnisthesiaDoctorName(rs.getString(5));
				notAvailableSlot.setAnesthesia(anesthesia);
				notAvailableSlot.setCommencing(rs.getString(6));
				notAvailableSlot.setTimeofincision(rs.getString(8));
				notAvailableSlot.setAnsintime(rs.getString(9));
				notAvailableSlot.setOtnotes(rs.getString(10));
				notAvailableSlot.setImgdata(rs.getString(11));

				notAvailableSlot.setAnifees(rs.getString(12));
				notAvailableSlot.setPsurcharge(rs.getString(13));
				notAvailableSlot.setPanetcharge(rs.getString(14));
				notAvailableSlot.setProcedure(rs.getString(15));
				notAvailableSlot.setChargeamout(rs.getString(16));
				notAvailableSlot.setSic(rs.getString(17));
				notAvailableSlot.setAssistaffcharge(rs.getString(18));
				notAvailableSlot.setDiaryUserId(rs.getInt(19));
				notAvailableSlot.setClientId(rs.getString(20));
				notAvailableSlot.setApmtType(rs.getString(21));

				BedDao bedDao = new JDBCBedDao(connection);
				Bed bed = bedDao.getEditIpdData(rs.getString(1));
				notAvailableSlot.setAdmitdate(bed.getAdmissiondate());

				ClientDAO clientDAO = new JDBCClientDAO(connection);
				Client client = clientDAO.getClientDetails(rs.getString(7));
				String age = DateTimeUtils.getAge(client.getDob());
				String agegender = age + "Years" + " / " + client.getGender();

				IpdDAO ipdDAO = new JDBCIpdDAO(connection);
				String wardname = ipdDAO.getIpdWardName(bed.getWardid());
				String bedname = ipdDAO.getIpdBedName(bed.getBedid());

				notAvailableSlot.setAgegender(agegender);
				notAvailableSlot.setWardbed(wardname + " / " + bedname);

				String asistantdoctlist = getasistantDoctorList(apmtid);
				notAvailableSlot.setAsistantdoclist(asistantdoctlist);
				//
				notAvailableSlot.setAbrivationid(client.getAbrivationid());
				notAvailableSlot.setMobno(client.getMobNo());
				notAvailableSlot.setAddress(client.getAddress());

				String ipdid = ipdDAO.getIpdId(rs.getString(7));
				notAvailableSlot.setIpdid(ipdid);
				notAvailableSlot.setInvoiceid(rs.getString(22));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return notAvailableSlot;
	}

	private String getOtAnisthesiaDoctorName(String id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select name from reference where id = " + id + " ";

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

	private String getasistantDoctorList(String apmtid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		StringBuffer str = new StringBuffer();
		String sql = "SELECT asistantdoctor FROM apm_ot_parent where apmtid=" + apmtid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
				UserProfile userProfile = userProfileDAO.getUserprofileDetails(rs.getInt(1));
				String doctor = userProfile.getInitial() + " " + userProfile.getFirstname() + " "
						+ userProfile.getLastname();
				str.append(doctor + " , ");
			}

			if (str.length() != 0) {
				result = str.substring(0, str.length() - 1);
			}
			result = str.toString();

		} catch (Exception e) {
			// TODO: handle exception
		}

		return result;
	}

	public int updateOtnotes(NotAvailableSlot notAvailableSlot, int id, String imageData) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = " update apm_available_slot set timeofincision=?, ansintime=?, otnotes=?,imgdata=? where id=" + id
				+ " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, notAvailableSlot.getTimeofincision());
			preparedStatement.setString(2, notAvailableSlot.getAnsintime());
			preparedStatement.setString(3, notAvailableSlot.getOtnotes());
			preparedStatement.setString(4, imageData);

			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public String getOtProcedure(String appointemntid) {

		String result = "0";

		try {

			String sql = "select procedures from apm_available_slot where id=" + appointemntid + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				result = rs.getString(1);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public NotAvailableSlot getOTDataByIpd(String ipdno) {
		PreparedStatement preparedStatement = null;
		NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
		String sql = "SELECT id,clientname,surgeon,apmttypetext,anesthesia,commencing,clientid,timeofincision, ansintime, otnotes FROM apm_available_slot where ipdno = "
				+ ipdno + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {

				notAvailableSlot.setId(rs.getInt(1));
				notAvailableSlot.setIpdno(ipdno);
				notAvailableSlot.setClientName(rs.getString(2));
				UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
				UserProfile userProfile = userProfileDAO.getUserprofileDetails(rs.getInt(3));
				String surgeon = userProfile.getInitial() + " " + userProfile.getFirstname() + " "
						+ userProfile.getLastname();
				notAvailableSlot.setSurgeon(surgeon);
				notAvailableSlot.setApmttypetext(rs.getString(4));
				userProfile = userProfileDAO.getUserprofileDetails(rs.getInt(5));
				String anesthesia = userProfile.getInitial() + " " + userProfile.getFirstname() + " "
						+ userProfile.getLastname();
				notAvailableSlot.setAnesthesia(anesthesia);
				notAvailableSlot.setCommencing(rs.getString(6));
				notAvailableSlot.setTimeofincision(rs.getString(8));
				notAvailableSlot.setAnsintime(rs.getString(9));
				notAvailableSlot.setOtnotes(rs.getString(10));

				BedDao bedDao = new JDBCBedDao(connection);
				Bed bed = bedDao.getEditIpdData(rs.getString(1));
				notAvailableSlot.setAdmitdate(bed.getAdmissiondate());

				ClientDAO clientDAO = new JDBCClientDAO(connection);
				Client client = clientDAO.getClientDetails(rs.getString(7));
				String age = DateTimeUtils.getAge(client.getDob());
				String agegender = age + "Years" + " / " + client.getGender();

				IpdDAO ipdDAO = new JDBCIpdDAO(connection);
				String wardname = ipdDAO.getIpdWardName(bed.getWardid());
				String bedname = ipdDAO.getIpdBedName(bed.getBedid());

				notAvailableSlot.setAgegender(agegender);
				notAvailableSlot.setWardbed(wardname + " / " + bedname);

				String asistantdoctlist = getasistantDoctorList(rs.getString(1));
				notAvailableSlot.setAsistantdoclist(asistantdoctlist);

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return notAvailableSlot;
	}

	public ArrayList<DiaryManagement> getEditUserList(int id, String selectedid) {

		PreparedStatement preparedStatement = null;
		ArrayList<DiaryManagement> list = new ArrayList<DiaryManagement>();
		String sql = "select id,initial,firstname,lastname from apm_user where usertype=4 and islogin=1  and (jobtitle='Practitioner' or hasdiary=1)  or id=1 order by firstname asc ";

		try {

			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				DiaryManagement diaryManagement = new DiaryManagement();
				diaryManagement.setId(rs.getInt(1));

				String selectedDoctors = getSelectedSecondaryDoctors(selectedid);

				for (String str : selectedDoctors.split(",")) {

					if (str.equals("0")) {
						continue;
					}
					int t = Integer.parseInt(str);
					if (t == diaryManagement.getId()) {
						diaryManagement.setStatus(1);
						break;
					}

				}

				if (rs.getString(2) == null || rs.getString(2).equals("")) {
					diaryManagement.setDiaryUser(rs.getString(3) + " " + rs.getString(4));

				} else {
					diaryManagement.setDiaryUser(rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));

				}
				list.add(diaryManagement);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public String getSelectedSecondaryDoctors(String ipdid) {

		String result = "";
		try {

			String sql = "select secndryconsult from ipd_addmission_form where id=" + ipdid + " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				result = rs.getString(1);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public int updateDischargeOtData(String sessionadmissionid, NotAvailableSlot notAvailableSlot) {

		int result = 0;
		try {
			String sql = "update apm_available_slot set otnotes=? where ipdno=" + sessionadmissionid + "   ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, notAvailableSlot.getOtnotes());

			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public boolean checkifSequenceExist(String cdate, int diaryuserid) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		AccountsDAO accountsDAO=new JDBCAccountsDAO(connection);
		int physioOpdorNot=accountsDAO.patientphysioIpdOrNot(""+diaryuserid);
		String tablename="";
		if(physioOpdorNot==1) {
			tablename="physio_available_slot_seqno";
		}else {
			tablename="apm_available_slot_seqno";
		}

		String sql = "select id from "+tablename+" where commencing = '" + cdate + "' and pid=" + diaryuserid
				+ " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = true;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return result;
	}

	public int getSqeunceNumber(String cdate, int diaryuserid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select max(seqno) from apm_available_slot_seqno where commencing = '" + cdate + "' and pid="
				+ diaryuserid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
//			StringWriter sw = new StringWriter();
//	         PrintWriter pw = new PrintWriter(sw);
//	         e.printStackTrace(pw);
//			Tra.exceptionMail(sw.toString());
		}

		return result;
	}

	public int InserCdateSeq(String cdate, int seqno, int appointmentid, int diaryusrid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		AccountsDAO accountsDAO=new JDBCAccountsDAO(connection);
		int physioOpdorNot=accountsDAO.patientphysioIpdOrNot(""+diaryusrid);
		String tablename="";
		if(physioOpdorNot==1) {
			tablename="physio_available_slot_seqno";
		}else {
			tablename="apm_available_slot_seqno";
		}

		String sql = "insert into "+tablename+"(commencing,seqno,apmtid,pid) values(?,?,?,?) ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, cdate);
			preparedStatement.setInt(2, seqno);
			preparedStatement.setInt(3, appointmentid);
			preparedStatement.setInt(4, diaryusrid);
			result = preparedStatement.executeUpdate();

			if (result == 1) {
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if (resultSet.next()) {
					result = resultSet.getInt(1);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
//			StringWriter sw = new StringWriter();
//	         PrintWriter pw = new PrintWriter(sw);
//	         e.printStackTrace(pw);
//			Tra.exceptionMail(sw.toString());
		}
		return result;
	}

	public ArrayList<Master> getAccountUserList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Master> list = new ArrayList<Master>();
		String sql = "select id from apm_user where jobtitle = 'Accounts' ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));

				list.add(master);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	public int getLastAppointmentId(String clientid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT id FROM apm_available_slot where clientid = " + clientid + " order by id desc limit 0,1 ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return result;
	}

	public String getDrNameFromApptId(int apptid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT diaryusername FROM apm_available_slot where id = " + apptid + "";

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

	public String getDrApptId(int apptid) {
		PreparedStatement preparedStatement = null;
		String result = "0";
		String sql = "SELECT diaryuserid FROM apm_available_slot where id = " + apptid + "";
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

	public int saveOptionalForm(NotAvailableSlot notAvailableSlot) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into his_optimal_form(clientid, opdid, re_unaided_d, re_unaided_n, re_withglass_d,re_withglass_n, re_gtph_d , re_gtph_n, le_unaided_d, le_unaided_n,"
				+ "le_withglass_d, le_withglass_n, le_gtph_d, le_gtph_n, air_r, air_l, perkins_r, perkins_l, appl_r, appl_l,"
				+ " keratometry1, keratometry2, keratometry3, keratometry4, keratometry5, keratometry6, keratometry7, keratometry8, re_usingglass_s, re_usingglass_c,"
				+ " re_usingglass_a, re_usingglass_va, re_usingglass_nv, re_usingglass_add, re_ar_s, re_ar_c, re_ar_a, re_ar_va, re_ar_nv, re_ar_add, "
				+ "re_ace_s, re_ace_c, re_ace_a, re_ace_va, re_ace_nv, re_ace_add, le_usingglass_s, le_usingglass_c, le_usingglass_a, le_usingglass_va, "
				+ "le_usingglass_nv, le_usingglass_add, le_ar_s, le_ar_c, le_ar_a, le_ar_va, le_ar_nv, le_ar_add, le_ace_s, le_ace_c, "
				+ "le_ace_a, le_ace_va, le_ace_nv, le_ace_add, lens_left1, lens_left2, diagnosisarea, lens_right1, lens_right2, followup, datetime, userid) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
		try {
			preparedStatement = connection.prepareStatement(sql);
			// clientid, opdid, re_unaided_d, re_unaided_n, re_withglass_d,re_withglass_n,
			// re_gtph_d , re_gtph_n, le_unaided_d, le_unaided_n,
			preparedStatement.setString(1, notAvailableSlot.getClientId());
			preparedStatement.setString(2, notAvailableSlot.getOpdid());
			preparedStatement.setString(3, notAvailableSlot.getRe_unaided_d());
			preparedStatement.setString(4, notAvailableSlot.getRe_unaided_n());
			preparedStatement.setString(5, notAvailableSlot.getRe_withglass_d());
			preparedStatement.setString(6, notAvailableSlot.getRe_withglass_n());
			preparedStatement.setString(7, notAvailableSlot.getRe_gtph_d());
			preparedStatement.setString(8, notAvailableSlot.getRe_gtph_n());
			preparedStatement.setString(9, notAvailableSlot.getLe_unaided_d());
			preparedStatement.setString(10, notAvailableSlot.getLe_unaided_n());

			// le_withglass_d, le_withglass_n, le_gtph_d, le_gtph_n, air_r, air_l,
			// perkins_r, perkins_l, appl_r, appl_l,
			preparedStatement.setString(11, notAvailableSlot.getLe_withglass_d());
			preparedStatement.setString(12, notAvailableSlot.getLe_withglass_n());
			preparedStatement.setString(13, notAvailableSlot.getLe_gtph_d());
			preparedStatement.setString(14, notAvailableSlot.getLe_gtph_n());
			preparedStatement.setString(15, notAvailableSlot.getAir_r());
			preparedStatement.setString(16, notAvailableSlot.getAir_l());
			preparedStatement.setString(17, notAvailableSlot.getPerkins_r());
			preparedStatement.setString(18, notAvailableSlot.getPerkins_l());
			preparedStatement.setString(19, notAvailableSlot.getAppl_r());
			preparedStatement.setString(20, notAvailableSlot.getAppl_l());

			// keratometry1, keratometry2, keratometry3, keratometry4, keratometry5,
			// keratometry6, keratometry7, keratometry8, re_usingglass_s, re_usingglass_c,
			preparedStatement.setString(21, notAvailableSlot.getKeratometry1());
			preparedStatement.setString(22, notAvailableSlot.getKeratometry2());
			preparedStatement.setString(23, notAvailableSlot.getKeratometry3());
			preparedStatement.setString(24, notAvailableSlot.getKeratometry4());
			preparedStatement.setString(25, notAvailableSlot.getKeratometry5());
			preparedStatement.setString(26, notAvailableSlot.getKeratometry6());
			preparedStatement.setString(27, notAvailableSlot.getKeratometry7());
			preparedStatement.setString(28, notAvailableSlot.getKeratometry8());
			preparedStatement.setString(29, notAvailableSlot.getRe_usingglass_s());
			preparedStatement.setString(30, notAvailableSlot.getRe_usingglass_c());

			// re_usingglass_a, re_usingglass_va, re_usingglass_nv, re_usingglass_add,
			// re_ar_s, re_ar_c, re_ar_a, re_ar_va, re_ar_nv, re_ar_add,
			preparedStatement.setString(31, notAvailableSlot.getRe_usingglass_a());
			preparedStatement.setString(32, notAvailableSlot.getRe_usingglass_va());
			preparedStatement.setString(33, notAvailableSlot.getRe_usingglass_nv());
			preparedStatement.setString(34, notAvailableSlot.getRe_usingglass_add());
			preparedStatement.setString(35, notAvailableSlot.getRe_ar_s());
			preparedStatement.setString(36, notAvailableSlot.getRe_ar_c());
			preparedStatement.setString(37, notAvailableSlot.getRe_ar_a());
			preparedStatement.setString(38, notAvailableSlot.getRe_ar_va());
			preparedStatement.setString(39, notAvailableSlot.getRe_ar_nv());
			preparedStatement.setString(40, notAvailableSlot.getRe_ar_add());

			// re_ace_s, re_ace_c, re_ace_a, re_ace_va, re_ace_nv, re_ace_add,
			// le_usingglass_s, le_usingglass_c, le_usingglass_a, le_usingglass_va,
			preparedStatement.setString(41, notAvailableSlot.getRe_ace_s());
			preparedStatement.setString(42, notAvailableSlot.getRe_ace_c());
			preparedStatement.setString(43, notAvailableSlot.getRe_ace_a());
			preparedStatement.setString(44, notAvailableSlot.getRe_ace_va());
			preparedStatement.setString(45, notAvailableSlot.getRe_ace_nv());
			preparedStatement.setString(46, notAvailableSlot.getRe_ace_add());
			preparedStatement.setString(47, notAvailableSlot.getLe_usingglass_s());
			preparedStatement.setString(48, notAvailableSlot.getLe_usingglass_c());
			preparedStatement.setString(49, notAvailableSlot.getLe_usingglass_a());
			preparedStatement.setString(50, notAvailableSlot.getLe_usingglass_va());

			// le_usingglass_nv, le_usingglass_add, le_ar_s, le_ar_c, le_ar_a, le_ar_va,
			// le_ar_nv, le_ar_add, le_ace_s, le_ace_c,
			preparedStatement.setString(51, notAvailableSlot.getLe_usingglass_nv());
			preparedStatement.setString(52, notAvailableSlot.getLe_usingglass_add());
			preparedStatement.setString(53, notAvailableSlot.getLe_ar_s());
			preparedStatement.setString(54, notAvailableSlot.getLe_ar_c());
			preparedStatement.setString(55, notAvailableSlot.getLe_ar_a());
			preparedStatement.setString(56, notAvailableSlot.getLe_ar_va());
			preparedStatement.setString(57, notAvailableSlot.getLe_ar_nv());
			preparedStatement.setString(58, notAvailableSlot.getLe_ar_add());
			preparedStatement.setString(59, notAvailableSlot.getLe_ace_s());
			preparedStatement.setString(60, notAvailableSlot.getLe_ace_c());

			// le_ace_a, le_ace_va, le_ace_nv, le_ace_add, lens_left1, lens_left2,
			// diagnosisarea, lens_right1, lens_right2, followup,
			preparedStatement.setString(61, notAvailableSlot.getLe_ace_a());
			preparedStatement.setString(62, notAvailableSlot.getLe_ace_va());
			preparedStatement.setString(63, notAvailableSlot.getLe_ace_nv());
			preparedStatement.setString(64, notAvailableSlot.getLe_ace_add());
			preparedStatement.setString(65, notAvailableSlot.getLens_left1());
			preparedStatement.setString(66, notAvailableSlot.getLens_left2());
			preparedStatement.setString(67, notAvailableSlot.getDiagnosisarea());
			preparedStatement.setString(68, notAvailableSlot.getLens_right1());
			preparedStatement.setString(69, notAvailableSlot.getLens_right2());
			preparedStatement.setString(70, notAvailableSlot.getFollowup());

			// datetime, userid
			preparedStatement.setString(71, notAvailableSlot.getDatetime());
			preparedStatement.setString(72, notAvailableSlot.getUserid());
			result = preparedStatement.executeUpdate();

			if (result == 1) {
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if (resultSet.next()) {
					result = resultSet.getInt(1);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public NotAvailableSlot getOptionalFormDetails(int id) {
		NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
		try {
			StringBuilder builder = new StringBuilder();
			builder.append(
					"select clientid, opdid, re_unaided_d, re_unaided_n, re_withglass_d,re_withglass_n, re_gtph_d , re_gtph_n, le_unaided_d, le_unaided_n,");
			builder.append(
					"le_withglass_d, le_withglass_n, le_gtph_d, le_gtph_n, air_r, air_l, perkins_r, perkins_l, appl_r, appl_l,");
			builder.append(
					"keratometry1, keratometry2, keratometry3, keratometry4, keratometry5, keratometry6, keratometry7, keratometry8, re_usingglass_s, re_usingglass_c,");
			builder.append(
					"re_usingglass_a, re_usingglass_va, re_usingglass_nv, re_usingglass_add, re_ar_s, re_ar_c, re_ar_a, re_ar_va, re_ar_nv, re_ar_add,");
			builder.append(
					"re_ace_s, re_ace_c, re_ace_a, re_ace_va, re_ace_nv, re_ace_add, le_usingglass_s, le_usingglass_c, le_usingglass_a, le_usingglass_va,");
			builder.append(
					"le_usingglass_nv, le_usingglass_add, le_ar_s, le_ar_c, le_ar_a, le_ar_va, le_ar_nv, le_ar_add, le_ace_s, le_ace_c,");
			builder.append(
					"le_ace_a, le_ace_va, le_ace_nv, le_ace_add, lens_left1, lens_left2, diagnosisarea, lens_right1, lens_right2, followup,");
			builder.append("datetime, userid,id from his_optimal_form where id='" + id + "'");
			PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				// clientid, opdid, re_unaided_d, re_unaided_n, re_withglass_d,re_withglass_n,
				// re_gtph_d , re_gtph_n, le_unaided_d, le_unaided_n,
				notAvailableSlot.setClientId(rs.getString(1));
				notAvailableSlot.setOpdid(rs.getString(2));
				notAvailableSlot.setRe_unaided_d(rs.getString(3));
				notAvailableSlot.setRe_unaided_n(rs.getString(4));
				notAvailableSlot.setRe_withglass_d(rs.getString(5));
				notAvailableSlot.setRe_withglass_n(rs.getString(6));
				notAvailableSlot.setRe_gtph_d(rs.getString(7));
				notAvailableSlot.setRe_gtph_n(rs.getString(8));
				notAvailableSlot.setLe_unaided_d(rs.getString(9));
				notAvailableSlot.setLe_unaided_n(rs.getString(10));

				// le_withglass_d, le_withglass_n, le_gtph_d, le_gtph_n, air_r, air_l,
				// perkins_r, perkins_l, appl_r, appl_l,
				notAvailableSlot.setLe_withglass_d(rs.getString(11));
				notAvailableSlot.setLe_withglass_n(rs.getString(12));
				notAvailableSlot.setLe_gtph_d(rs.getString(13));
				notAvailableSlot.setLe_gtph_n(rs.getString(14));
				notAvailableSlot.setAir_r(rs.getString(15));
				notAvailableSlot.setAir_l(rs.getString(16));
				notAvailableSlot.setPerkins_r(rs.getString(17));
				notAvailableSlot.setPerkins_l(rs.getString(18));
				notAvailableSlot.setAppl_r(rs.getString(19));
				notAvailableSlot.setAppl_l(rs.getString(20));

				// keratometry1, keratometry2, keratometry3, keratometry4, keratometry5,
				// keratometry6, keratometry7, keratometry8, re_usingglass_s, re_usingglass_c,
				notAvailableSlot.setKeratometry1(rs.getString(21));
				notAvailableSlot.setKeratometry2(rs.getString(22));
				notAvailableSlot.setKeratometry3(rs.getString(23));
				notAvailableSlot.setKeratometry4(rs.getString(24));
				notAvailableSlot.setKeratometry5(rs.getString(25));
				notAvailableSlot.setKeratometry6(rs.getString(26));
				notAvailableSlot.setKeratometry7(rs.getString(27));
				notAvailableSlot.setKeratometry8(rs.getString(28));
				notAvailableSlot.setRe_usingglass_s(rs.getString(29));
				notAvailableSlot.setRe_usingglass_c(rs.getString(30));

				// re_usingglass_a, re_usingglass_va, re_usingglass_nv, re_usingglass_add,
				// re_ar_s, re_ar_c, re_ar_a, re_ar_va, re_ar_nv, re_ar_add,
				notAvailableSlot.setRe_usingglass_a(rs.getString(31));
				notAvailableSlot.setRe_usingglass_va(rs.getString(32));
				notAvailableSlot.setRe_usingglass_nv(rs.getString(33));
				notAvailableSlot.setRe_usingglass_add(rs.getString(34));
				notAvailableSlot.setRe_ar_s(rs.getString(35));
				notAvailableSlot.setRe_ar_c(rs.getString(36));
				notAvailableSlot.setRe_ar_a(rs.getString(37));
				notAvailableSlot.setRe_ar_va(rs.getString(38));
				notAvailableSlot.setRe_ar_nv(rs.getString(39));
				notAvailableSlot.setRe_ar_add(rs.getString(40));

				// re_ace_s, re_ace_c, re_ace_a, re_ace_va, re_ace_nv, re_ace_add,
				// le_usingglass_s, le_usingglass_c, le_usingglass_a, le_usingglass_va,
				notAvailableSlot.setRe_ace_s(rs.getString(41));
				notAvailableSlot.setRe_ace_c(rs.getString(42));
				notAvailableSlot.setRe_ace_a(rs.getString(43));
				notAvailableSlot.setRe_ace_va(rs.getString(44));
				notAvailableSlot.setRe_ace_nv(rs.getString(45));
				notAvailableSlot.setRe_ace_add(rs.getString(46));
				notAvailableSlot.setLe_usingglass_s(rs.getString(47));
				notAvailableSlot.setLe_usingglass_c(rs.getString(48));
				notAvailableSlot.setLe_usingglass_a(rs.getString(49));
				notAvailableSlot.setLe_usingglass_va(rs.getString(50));

				// le_usingglass_nv, le_usingglass_add, le_ar_s, le_ar_c, le_ar_a, le_ar_va,
				// le_ar_nv, le_ar_add, le_ace_s, le_ace_c,
				notAvailableSlot.setLe_usingglass_nv(rs.getString(51));
				notAvailableSlot.setLe_usingglass_add(rs.getString(52));
				notAvailableSlot.setLe_ar_s(rs.getString(53));
				notAvailableSlot.setLe_ar_c(rs.getString(54));
				notAvailableSlot.setLe_ar_a(rs.getString(55));
				notAvailableSlot.setLe_ar_va(rs.getString(56));
				notAvailableSlot.setLe_ar_nv(rs.getString(57));
				notAvailableSlot.setLe_ar_add(rs.getString(58));
				notAvailableSlot.setLe_ace_s(rs.getString(59));
				notAvailableSlot.setLe_ace_c(rs.getString(60));

				// le_ace_a, le_ace_va, le_ace_nv, le_ace_add, lens_left1, lens_left2,
				// diagnosisarea, lens_right1, lens_right2, followup,
				notAvailableSlot.setLe_ace_a(rs.getString(61));
				notAvailableSlot.setLe_ace_va(rs.getString(62));
				notAvailableSlot.setLe_ace_nv(rs.getString(63));
				notAvailableSlot.setLe_ace_add(rs.getString(64));
				notAvailableSlot.setLens_left1(rs.getString(65));
				notAvailableSlot.setLens_left2(rs.getString(66));
				notAvailableSlot.setDiagnosisarea(rs.getString(67));
				notAvailableSlot.setLens_right1(rs.getString(68));
				notAvailableSlot.setLens_right2(rs.getString(69));
				notAvailableSlot.setFollowup(rs.getString(70));

				// datetime, userid
				notAvailableSlot.setDatetime(rs.getString(71));
				notAvailableSlot.setUserid(rs.getString(72));
				notAvailableSlot.setId(rs.getInt(73));

				String[] date = rs.getString(71).split(" ");
				String date1 = DateTimeUtils.getCommencingDate1(date[0]) + " " + date[1];
				notAvailableSlot.setDatetime(date1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return notAvailableSlot;
	}

	public ArrayList<NotAvailableSlot> getAllOptionFormList(String clientId) {
		ArrayList<NotAvailableSlot> arrayList = new ArrayList<NotAvailableSlot>();
		try {
			StringBuilder builder = new StringBuilder();
			builder.append(
					"select clientid, opdid, re_unaided_d, re_unaided_n, re_withglass_d,re_withglass_n, re_gtph_d , re_gtph_n, le_unaided_d, le_unaided_n,");
			builder.append(
					"le_withglass_d, le_withglass_n, le_gtph_d, le_gtph_n, air_r, air_l, perkins_r, perkins_l, appl_r, appl_l,");
			builder.append(
					"keratometry1, keratometry2, keratometry3, keratometry4, keratometry5, keratometry6, keratometry7, keratometry8, re_usingglass_s, re_usingglass_c,");
			builder.append(
					"re_usingglass_a, re_usingglass_va, re_usingglass_nv, re_usingglass_add, re_ar_s, re_ar_c, re_ar_a, re_ar_va, re_ar_nv, re_ar_add,");
			builder.append(
					"re_ace_s, re_ace_c, re_ace_a, re_ace_va, re_ace_nv, re_ace_add, le_usingglass_s, le_usingglass_c, le_usingglass_a, le_usingglass_va,");
			builder.append(
					"le_usingglass_nv, le_usingglass_add, le_ar_s, le_ar_c, le_ar_a, le_ar_va, le_ar_nv, le_ar_add, le_ace_s, le_ace_c,");
			builder.append(
					"le_ace_a, le_ace_va, le_ace_nv, le_ace_add, lens_left1, lens_left2, diagnosisarea, lens_right1, lens_right2, followup,");
			builder.append("datetime, userid,id from his_optimal_form where clientid='" + clientId + "'");
			PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
				// clientid, opdid, re_unaided_d, re_unaided_n, re_withglass_d,re_withglass_n,
				// re_gtph_d , re_gtph_n, le_unaided_d, le_unaided_n,
				notAvailableSlot.setClientId(rs.getString(1));
				notAvailableSlot.setOpdid(rs.getString(2));
				notAvailableSlot.setRe_unaided_d(rs.getString(3));
				notAvailableSlot.setRe_unaided_n(rs.getString(4));
				notAvailableSlot.setRe_withglass_d(rs.getString(5));
				notAvailableSlot.setRe_withglass_n(rs.getString(6));
				notAvailableSlot.setRe_gtph_d(rs.getString(7));
				notAvailableSlot.setRe_gtph_n(rs.getString(8));
				notAvailableSlot.setLe_unaided_d(rs.getString(9));
				notAvailableSlot.setLe_unaided_n(rs.getString(10));

				// le_withglass_d, le_withglass_n, le_gtph_d, le_gtph_n, air_r, air_l,
				// perkins_r, perkins_l, appl_r, appl_l,
				notAvailableSlot.setLe_withglass_d(rs.getString(11));
				notAvailableSlot.setLe_withglass_n(rs.getString(12));
				notAvailableSlot.setLe_gtph_d(rs.getString(13));
				notAvailableSlot.setLe_gtph_n(rs.getString(14));
				notAvailableSlot.setAir_r(rs.getString(15));
				notAvailableSlot.setAir_l(rs.getString(16));
				notAvailableSlot.setPerkins_r(rs.getString(17));
				notAvailableSlot.setPerkins_l(rs.getString(18));
				notAvailableSlot.setAppl_r(rs.getString(19));
				notAvailableSlot.setAppl_l(rs.getString(20));

				// keratometry1, keratometry2, keratometry3, keratometry4, keratometry5,
				// keratometry6, keratometry7, keratometry8, re_usingglass_s, re_usingglass_c,
				notAvailableSlot.setKeratometry1(rs.getString(21));
				notAvailableSlot.setKeratometry2(rs.getString(22));
				notAvailableSlot.setKeratometry3(rs.getString(23));
				notAvailableSlot.setKeratometry4(rs.getString(24));
				notAvailableSlot.setKeratometry5(rs.getString(25));
				notAvailableSlot.setKeratometry6(rs.getString(26));
				notAvailableSlot.setKeratometry7(rs.getString(27));
				notAvailableSlot.setKeratometry8(rs.getString(28));
				notAvailableSlot.setRe_usingglass_s(rs.getString(29));
				notAvailableSlot.setRe_usingglass_c(rs.getString(30));

				// re_usingglass_a, re_usingglass_va, re_usingglass_nv, re_usingglass_add,
				// re_ar_s, re_ar_c, re_ar_a, re_ar_va, re_ar_nv, re_ar_add,
				notAvailableSlot.setRe_usingglass_a(rs.getString(31));
				notAvailableSlot.setRe_usingglass_va(rs.getString(32));
				notAvailableSlot.setRe_usingglass_nv(rs.getString(33));
				notAvailableSlot.setRe_usingglass_add(rs.getString(34));
				notAvailableSlot.setRe_ar_s(rs.getString(35));
				notAvailableSlot.setRe_ar_c(rs.getString(36));
				notAvailableSlot.setRe_ar_a(rs.getString(37));
				notAvailableSlot.setRe_ar_va(rs.getString(38));
				notAvailableSlot.setRe_ar_nv(rs.getString(39));
				notAvailableSlot.setRe_ar_add(rs.getString(40));

				// re_ace_s, re_ace_c, re_ace_a, re_ace_va, re_ace_nv, re_ace_add,
				// le_usingglass_s, le_usingglass_c, le_usingglass_a, le_usingglass_va,
				notAvailableSlot.setRe_ace_s(rs.getString(41));
				notAvailableSlot.setRe_ace_c(rs.getString(42));
				notAvailableSlot.setRe_ace_a(rs.getString(43));
				notAvailableSlot.setRe_ace_va(rs.getString(44));
				notAvailableSlot.setRe_ace_nv(rs.getString(45));
				notAvailableSlot.setRe_ace_add(rs.getString(46));
				notAvailableSlot.setLe_usingglass_s(rs.getString(47));
				notAvailableSlot.setLe_usingglass_c(rs.getString(48));
				notAvailableSlot.setLe_usingglass_a(rs.getString(49));
				notAvailableSlot.setLe_usingglass_va(rs.getString(50));

				// le_usingglass_nv, le_usingglass_add, le_ar_s, le_ar_c, le_ar_a, le_ar_va,
				// le_ar_nv, le_ar_add, le_ace_s, le_ace_c,
				notAvailableSlot.setLe_usingglass_nv(rs.getString(51));
				notAvailableSlot.setLe_usingglass_add(rs.getString(52));
				notAvailableSlot.setLe_ar_s(rs.getString(53));
				notAvailableSlot.setLe_ar_c(rs.getString(54));
				notAvailableSlot.setLe_ar_a(rs.getString(55));
				notAvailableSlot.setLe_ar_va(rs.getString(56));
				notAvailableSlot.setLe_ar_nv(rs.getString(57));
				notAvailableSlot.setLe_ar_add(rs.getString(58));
				notAvailableSlot.setLe_ace_s(rs.getString(59));
				notAvailableSlot.setLe_ace_c(rs.getString(60));

				// le_ace_a, le_ace_va, le_ace_nv, le_ace_add, lens_left1, lens_left2,
				// diagnosisarea, lens_right1, lens_right2, followup,
				notAvailableSlot.setLe_ace_a(rs.getString(61));
				notAvailableSlot.setLe_ace_va(rs.getString(62));
				notAvailableSlot.setLe_ace_nv(rs.getString(63));
				notAvailableSlot.setLe_ace_add(rs.getString(64));
				notAvailableSlot.setLens_left1(rs.getString(65));
				notAvailableSlot.setLens_left2(rs.getString(66));
				notAvailableSlot.setDiagnosisarea(rs.getString(67));
				notAvailableSlot.setLens_right1(rs.getString(68));
				notAvailableSlot.setLens_right2(rs.getString(69));
				notAvailableSlot.setFollowup(rs.getString(70));

				// datetime, userid
				notAvailableSlot.setDatetime(rs.getString(71));
				notAvailableSlot.setUserid(rs.getString(72));
				notAvailableSlot.setId(rs.getInt(73));

				String[] date = rs.getString(71).split(" ");
				String date1 = DateTimeUtils.getCommencingDate1(date[0]) + " " + date[1];
				notAvailableSlot.setDatetime(date1);
				arrayList.add(notAvailableSlot);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;

	}

	public int updateOptionalForm(NotAvailableSlot notAvailableSlot) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update his_optimal_form set clientid=?, opdid=?, re_unaided_d=?, re_unaided_n=?, re_withglass_d=?,re_withglass_n=?, re_gtph_d =?, re_gtph_n=?, le_unaided_d=?, le_unaided_n=?,"
				+ "le_withglass_d=?, le_withglass_n=?, le_gtph_d=?, le_gtph_n=?, air_r=?, air_l=?, perkins_r=?, perkins_l=?, appl_r=?, appl_l=?,"
				+ " keratometry1=?, keratometry2=?, keratometry3=?, keratometry4=?, keratometry5=?, keratometry6=?, keratometry7=?, keratometry8=?, re_usingglass_s=?, re_usingglass_c=?,"
				+ " re_usingglass_a=?, re_usingglass_va=?, re_usingglass_nv=?, re_usingglass_add=?, re_ar_s=?, re_ar_c=?, re_ar_a=?, re_ar_va=?, re_ar_nv=?, re_ar_add=?, "
				+ "re_ace_s=?, re_ace_c=?, re_ace_a=?, re_ace_va=?, re_ace_nv=?, re_ace_add=?, le_usingglass_s=?, le_usingglass_c=?, le_usingglass_a=?, le_usingglass_va=?, "
				+ "le_usingglass_nv=?, le_usingglass_add=?, le_ar_s=?, le_ar_c=?, le_ar_a=?, le_ar_va=?, le_ar_nv=?, le_ar_add=?, le_ace_s=?, le_ace_c=?, "
				+ "le_ace_a=?, le_ace_va=?, le_ace_nv=?, le_ace_add=?, lens_left1=?, lens_left2=?, diagnosisarea=?, lens_right1=?, lens_right2=?, followup=?,editdatetime=?,edituserid=? where id="
				+ notAvailableSlot.getId() + "";
		try {
			preparedStatement = connection.prepareStatement(sql);
			// clientid, opdid, re_unaided_d, re_unaided_n, re_withglass_d,re_withglass_n,
			// re_gtph_d , re_gtph_n, le_unaided_d, le_unaided_n,
			preparedStatement.setString(1, notAvailableSlot.getClientId());
			preparedStatement.setString(2, notAvailableSlot.getOpdid());
			preparedStatement.setString(3, notAvailableSlot.getRe_unaided_d());
			preparedStatement.setString(4, notAvailableSlot.getRe_unaided_n());
			preparedStatement.setString(5, notAvailableSlot.getRe_withglass_d());
			preparedStatement.setString(6, notAvailableSlot.getRe_withglass_n());
			preparedStatement.setString(7, notAvailableSlot.getRe_gtph_d());
			preparedStatement.setString(8, notAvailableSlot.getRe_gtph_n());
			preparedStatement.setString(9, notAvailableSlot.getLe_unaided_d());
			preparedStatement.setString(10, notAvailableSlot.getLe_unaided_n());

			// le_withglass_d, le_withglass_n, le_gtph_d, le_gtph_n, air_r, air_l,
			// perkins_r, perkins_l, appl_r, appl_l,
			preparedStatement.setString(11, notAvailableSlot.getLe_withglass_d());
			preparedStatement.setString(12, notAvailableSlot.getLe_withglass_n());
			preparedStatement.setString(13, notAvailableSlot.getLe_gtph_d());
			preparedStatement.setString(14, notAvailableSlot.getLe_gtph_n());
			preparedStatement.setString(15, notAvailableSlot.getAir_r());
			preparedStatement.setString(16, notAvailableSlot.getAir_l());
			preparedStatement.setString(17, notAvailableSlot.getPerkins_r());
			preparedStatement.setString(18, notAvailableSlot.getPerkins_l());
			preparedStatement.setString(19, notAvailableSlot.getAppl_r());
			preparedStatement.setString(20, notAvailableSlot.getAppl_l());

			// keratometry1, keratometry2, keratometry3, keratometry4, keratometry5,
			// keratometry6, keratometry7, keratometry8, re_usingglass_s, re_usingglass_c,
			preparedStatement.setString(21, notAvailableSlot.getKeratometry1());
			preparedStatement.setString(22, notAvailableSlot.getKeratometry2());
			preparedStatement.setString(23, notAvailableSlot.getKeratometry3());
			preparedStatement.setString(24, notAvailableSlot.getKeratometry4());
			preparedStatement.setString(25, notAvailableSlot.getKeratometry5());
			preparedStatement.setString(26, notAvailableSlot.getKeratometry6());
			preparedStatement.setString(27, notAvailableSlot.getKeratometry7());
			preparedStatement.setString(28, notAvailableSlot.getKeratometry8());
			preparedStatement.setString(29, notAvailableSlot.getRe_usingglass_s());
			preparedStatement.setString(30, notAvailableSlot.getRe_usingglass_c());

			// re_usingglass_a, re_usingglass_va, re_usingglass_nv, re_usingglass_add,
			// re_ar_s, re_ar_c, re_ar_a, re_ar_va, re_ar_nv, re_ar_add,
			preparedStatement.setString(31, notAvailableSlot.getRe_usingglass_a());
			preparedStatement.setString(32, notAvailableSlot.getRe_usingglass_va());
			preparedStatement.setString(33, notAvailableSlot.getRe_usingglass_nv());
			preparedStatement.setString(34, notAvailableSlot.getRe_usingglass_add());
			preparedStatement.setString(35, notAvailableSlot.getRe_ar_s());
			preparedStatement.setString(36, notAvailableSlot.getRe_ar_c());
			preparedStatement.setString(37, notAvailableSlot.getRe_ar_a());
			preparedStatement.setString(38, notAvailableSlot.getRe_ar_va());
			preparedStatement.setString(39, notAvailableSlot.getRe_ar_nv());
			preparedStatement.setString(40, notAvailableSlot.getRe_ar_add());

			// re_ace_s, re_ace_c, re_ace_a, re_ace_va, re_ace_nv, re_ace_add,
			// le_usingglass_s, le_usingglass_c, le_usingglass_a, le_usingglass_va,
			preparedStatement.setString(41, notAvailableSlot.getRe_ace_s());
			preparedStatement.setString(42, notAvailableSlot.getRe_ace_c());
			preparedStatement.setString(43, notAvailableSlot.getRe_ace_a());
			preparedStatement.setString(44, notAvailableSlot.getRe_ace_va());
			preparedStatement.setString(45, notAvailableSlot.getRe_ace_nv());
			preparedStatement.setString(46, notAvailableSlot.getRe_ace_add());
			preparedStatement.setString(47, notAvailableSlot.getLe_usingglass_s());
			preparedStatement.setString(48, notAvailableSlot.getLe_usingglass_c());
			preparedStatement.setString(49, notAvailableSlot.getLe_usingglass_a());
			preparedStatement.setString(50, notAvailableSlot.getLe_usingglass_va());

			// le_usingglass_nv, le_usingglass_add, le_ar_s, le_ar_c, le_ar_a, le_ar_va,
			// le_ar_nv, le_ar_add, le_ace_s, le_ace_c,
			preparedStatement.setString(51, notAvailableSlot.getLe_usingglass_nv());
			preparedStatement.setString(52, notAvailableSlot.getLe_usingglass_add());
			preparedStatement.setString(53, notAvailableSlot.getLe_ar_s());
			preparedStatement.setString(54, notAvailableSlot.getLe_ar_c());
			preparedStatement.setString(55, notAvailableSlot.getLe_ar_a());
			preparedStatement.setString(56, notAvailableSlot.getLe_ar_va());
			preparedStatement.setString(57, notAvailableSlot.getLe_ar_nv());
			preparedStatement.setString(58, notAvailableSlot.getLe_ar_add());
			preparedStatement.setString(59, notAvailableSlot.getLe_ace_s());
			preparedStatement.setString(60, notAvailableSlot.getLe_ace_c());

			// le_ace_a, le_ace_va, le_ace_nv, le_ace_add, lens_left1, lens_left2,
			// diagnosisarea, lens_right1, lens_right2, followup,
			preparedStatement.setString(61, notAvailableSlot.getLe_ace_a());
			preparedStatement.setString(62, notAvailableSlot.getLe_ace_va());
			preparedStatement.setString(63, notAvailableSlot.getLe_ace_nv());
			preparedStatement.setString(64, notAvailableSlot.getLe_ace_add());
			preparedStatement.setString(65, notAvailableSlot.getLens_left1());
			preparedStatement.setString(66, notAvailableSlot.getLens_left2());
			preparedStatement.setString(67, notAvailableSlot.getDiagnosisarea());
			preparedStatement.setString(68, notAvailableSlot.getLens_right1());
			preparedStatement.setString(69, notAvailableSlot.getLens_right2());
			preparedStatement.setString(70, notAvailableSlot.getFollowup());

			// datetime, userid
			preparedStatement.setString(71, notAvailableSlot.getDatetime());
			preparedStatement.setString(72, notAvailableSlot.getUserid());
			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Master> getotDepartmenrList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Master> list = new ArrayList<Master>();
		String sql = "SELECT id,discipline FROM apm_discipline ";

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
			// TODO: handle exception
		}
		return list;
	}

	public int saveMultiImgData(String otnotesapmtid, String data22, String savemoreid, String otnotes) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into his_multi_otimg(otid, imgdata, sqno,otnotes) values(?,?,?,?) ";

		try {

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, otnotesapmtid);
			preparedStatement.setString(2, data22);
			preparedStatement.setString(3, savemoreid);
			preparedStatement.setString(4, otnotes);
			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public boolean checkMultiOtDataExist(String otnotesapmtid, String savemoreid) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "select * from his_multi_otimg where otid=" + otnotesapmtid + " and sqno=" + savemoreid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = true;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return result;
	}

	public int updateMultimgotData(String otnotesapmtid, String data22, String savemoreid, String otnotes) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update his_multi_otimg set imgdata=?,otnotes=? where otid=" + otnotesapmtid + " and sqno="
				+ savemoreid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, data22);
			preparedStatement.setString(2, otnotes);
			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Master> getMultiOtImgList(String otnotesapmtid, String savemoreid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Master> list = new ArrayList<Master>();
		String sql = "select sqno, imgdata,otnotes from his_multi_otimg where otid=" + otnotesapmtid + "  ";

		try {
			int count = 1;
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				master.setOtnotes(rs.getString(3));
				master.setCount(count++);
				list.add(master);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return list;
	}

	public int getSpeciFromRefernce(int surgeonid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select speciality from reference where id = " + surgeonid + " ";

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

	public ArrayList<NotAvailableSlot> getOTNotesFormList(String clientId) {
		PreparedStatement preparedStatement = null;
		ArrayList<NotAvailableSlot> arrayList = new ArrayList<NotAvailableSlot>();
		String sql = "SELECT id,commencing FROM apm_available_slot where clientid = " + clientId
				+ " and procedures!='0' ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
				/*
				 * String[] date = rs.getString(4).split(" "); String datetime =
				 * DateTimeUtils.getCommencingDate1(date[0])+" "+date[1];
				 */
				notAvailableSlot.setId(rs.getInt(1));
				String datetime = DateTimeUtils.getCommencingDate1(rs.getString(2));
				notAvailableSlot.setCommencing(datetime);
				arrayList.add(notAvailableSlot);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public NotAvailableSlot getProcedureDepartment(String editAppointId) {
		PreparedStatement preparedStatement = null;
		NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
		StringBuffer buffer = new StringBuffer();
		buffer.append(
				"SELECT apm_newchargetype.id,apm_available_slot.procedures,description,clientid,aptmtype FROM apm_available_slot ");
		buffer.append("inner join apm_newchargetype on apm_newchargetype.name = apm_available_slot.procedures ");
		buffer.append("where apm_available_slot.id='" + editAppointId + "' ");
		try {
			preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				notAvailableSlot.setId(rs.getInt(1));
				notAvailableSlot.setProcedure(rs.getString(2));
				notAvailableSlot.setDept(rs.getString(3));
				notAvailableSlot.setClientId(rs.getString(4));
				notAvailableSlot.setAppointmentTypeid(rs.getInt(5));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return notAvailableSlot;
	}

	public int getSpeciSurgonFromRefernce(int surgeonid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select speciality from reference where userid = '" + surgeonid + "' ";

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

	public Master getImmurtizeData(String clientid) {
		Master notAvailableSlot = new Master();
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append(
					"select immurtizationdata.id, bcg, opv0, hep_b1, dtwp1, ipv1, hep_b2, hib1, rotavirus1, pcv1, dtwp2, ipv2, hib2, rotavirus2, pcv2, dtwp3, ipv3, ");
			buffer.append("hib3, rotavirus3, pcv3, opv1, hepb3, opv2, mmr1, typhoid_conjugate, vaccine, ");
			buffer.append(
					"hepa1, mmr2, varicella1, pcvbooster, dtwpb1dtapb1, ipvb1, hibb1, hepa2, boosteroftyphoid, conjugatevaccine, dtwpb2dtapb2, tdaptd, hpv,");
			buffer.append(
					"bcgdt, opv0dt, hep_b1dt, dtwp1dt, ipv1dt, hep_b2dt, hib1dt, rotavirus1dt, pcv1dt, dtwp2dt, ipv2dt, hib2dt, rotavirus2dt, pcv2dt, dtwp3dt, ipv3dt, ");
			buffer.append(
					"hib3dt, rotavirus3dt, pcv3dt, opv1dt, hepb3dt, opv2dt, mmr1dt, typhoid_conjugatedt, vaccinedt, ");
			buffer.append(
					"hepa1dt, mmr2dt, varicella1dt, pcvboosterdt, dtwpb1dtapb1dt, ipvb1dt, hibb1dt, hepa2dt, boosteroftyphoiddt, conjugatevaccinedt, dtwpb2dtapb2dt, tdaptddt, hpvdt,");
			buffer.append(
					"remark1,remark2,remark3,remark4,remark5,remark6,remark7,remark8,remark9,remark10,remark11,remark12,remark13,remark14,remark15,dob,fullname ");
			buffer.append("from immurtizationdata  ");
			buffer.append("inner join apm_patient on apm_patient.id =immurtizationdata.clientid ");
			buffer.append("where clientid='" + clientid + "' ");

			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				// id, bcg, opv0, hep_b1, dtwp1, ipv1, hep_b2, hib1, rotavirus1, pcv1,
				notAvailableSlot.setId(rs.getInt(1));
				notAvailableSlot.setBcg(rs.getString(2));
				notAvailableSlot.setOpv0(rs.getString(3));
				notAvailableSlot.setHep_b1(rs.getString(4));
				notAvailableSlot.setDtwp1(rs.getString(5));
				notAvailableSlot.setIpv1(rs.getString(6));
				notAvailableSlot.setHep_b2(rs.getString(7));
				notAvailableSlot.setHib1(rs.getString(8));
				notAvailableSlot.setRotavirus1(rs.getString(9));
				notAvailableSlot.setPcv1(rs.getString(10));

				// dtwp2, ipv2, hib2, rotavirus2, pcv2, dtwp3, ipv3,
				notAvailableSlot.setDtwp2(rs.getString(11));
				notAvailableSlot.setIpv2(rs.getString(12));
				notAvailableSlot.setHib2(rs.getString(13));
				notAvailableSlot.setRotavirus2(rs.getString(14));
				notAvailableSlot.setPcv2(rs.getString(15));
				notAvailableSlot.setDtwp3(rs.getString(16));
				notAvailableSlot.setIpv3(rs.getString(17));

				// hib3, rotavirus3, pcv3, opv1, hepb3, opv2, mmr1, typhoid_conjugate, vaccine,
				notAvailableSlot.setHib3(rs.getString(18));
				notAvailableSlot.setRotavirus3(rs.getString(19));
				notAvailableSlot.setPcv3(rs.getString(20));
				notAvailableSlot.setOpv1(rs.getString(21));
				notAvailableSlot.setHepb3(rs.getString(22));
				notAvailableSlot.setOpv2(rs.getString(23));
				notAvailableSlot.setMmr1(rs.getString(24));
				notAvailableSlot.setTyphoid_conjugate(rs.getString(25));
				notAvailableSlot.setVaccine(rs.getString(26));

				// hepa1, mmr2, varicella1, pcvbooster, dtwpb1dtapb1, ipvb1, hibb1, hepa2,
				// boosteroftyphoid, conjugatevaccine, dtwpb2dtapb2, tdaptd, hpv
				notAvailableSlot.setHepa1(rs.getString(27));
				notAvailableSlot.setMmr2(rs.getString(28));
				notAvailableSlot.setVaricella1(rs.getString(29));
				notAvailableSlot.setPcvbooster(rs.getString(30));
				notAvailableSlot.setDtwpb1dtapb1(rs.getString(31));
				notAvailableSlot.setIpvb1(rs.getString(32));
				notAvailableSlot.setHibb1(rs.getString(33));
				notAvailableSlot.setHepa2(rs.getString(34));
				notAvailableSlot.setBoosteroftyphoid(rs.getString(35));
				notAvailableSlot.setConjugatevaccine(rs.getString(36));
				notAvailableSlot.setDtwpb2dtapb2(rs.getString(37));
				notAvailableSlot.setTdaptd(rs.getString(38));
				notAvailableSlot.setHpv(rs.getString(39));

				notAvailableSlot.setBcgdt(rs.getString(40));
				notAvailableSlot.setOpv0dt(rs.getString(41));
				notAvailableSlot.setHep_b1dt(rs.getString(42));
				notAvailableSlot.setDtwp1dt(rs.getString(43));
				notAvailableSlot.setIpv1dt(rs.getString(44));
				notAvailableSlot.setHep_b2dt(rs.getString(45));
				notAvailableSlot.setHib1dt(rs.getString(46));
				notAvailableSlot.setRotavirus1dt(rs.getString(47));
				notAvailableSlot.setPcv1dt(rs.getString(48));

				notAvailableSlot.setDtwp2dt(rs.getString(49));
				notAvailableSlot.setIpv2dt(rs.getString(50));
				notAvailableSlot.setHib2dt(rs.getString(51));
				notAvailableSlot.setRotavirus2dt(rs.getString(52));
				notAvailableSlot.setPcv2dt(rs.getString(53));
				notAvailableSlot.setDtwp3dt(rs.getString(54));
				notAvailableSlot.setIpv3dt(rs.getString(55));

				notAvailableSlot.setHib3dt(rs.getString(56));
				notAvailableSlot.setRotavirus3dt(rs.getString(57));
				notAvailableSlot.setPcv3dt(rs.getString(58));
				notAvailableSlot.setOpv1dt(rs.getString(59));
				notAvailableSlot.setHepb3dt(rs.getString(60));
				notAvailableSlot.setOpv2dt(rs.getString(61));
				notAvailableSlot.setMmr1dt(rs.getString(62));
				notAvailableSlot.setTyphoid_conjugatedt(rs.getString(63));
				notAvailableSlot.setVaccinedt(rs.getString(64));

				notAvailableSlot.setHepa1dt(rs.getString(65));
				notAvailableSlot.setMmr2dt(rs.getString(66));
				notAvailableSlot.setVaricella1dt(rs.getString(67));
				notAvailableSlot.setPcvboosterdt(rs.getString(68));
				notAvailableSlot.setDtwpb1dtapb1dt(rs.getString(69));
				notAvailableSlot.setIpvb1dt(rs.getString(70));
				notAvailableSlot.setHibb1dt(rs.getString(71));
				notAvailableSlot.setHepa2dt(rs.getString(72));
				notAvailableSlot.setBoosteroftyphoiddt(rs.getString(73));
				notAvailableSlot.setConjugatevaccinedt(rs.getString(74));
				notAvailableSlot.setDtwpb2dtapb2dt(rs.getString(75));
				notAvailableSlot.setTdaptddt(rs.getString(76));
				notAvailableSlot.setHpvdt(rs.getString(77));

				notAvailableSlot.setRemark1(rs.getString(78));
				notAvailableSlot.setRemark2(rs.getString(79));
				notAvailableSlot.setRemark3(rs.getString(80));
				notAvailableSlot.setRemark4(rs.getString(81));
				notAvailableSlot.setRemark5(rs.getString(82));
				notAvailableSlot.setRemark6(rs.getString(83));
				notAvailableSlot.setRemark7(rs.getString(84));
				notAvailableSlot.setRemark8(rs.getString(85));
				notAvailableSlot.setRemark9(rs.getString(86));
				notAvailableSlot.setRemark10(rs.getString(87));
				notAvailableSlot.setRemark11(rs.getString(88));
				notAvailableSlot.setRemark12(rs.getString(89));
				notAvailableSlot.setRemark13(rs.getString(90));
				notAvailableSlot.setRemark14(rs.getString(91));
				notAvailableSlot.setRemark15(rs.getString(92));

				notAvailableSlot.setDob(rs.getString(93));
				notAvailableSlot.setFullname(rs.getString(94));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return notAvailableSlot;
	}

	public int updateimmurtizationchart(String clientId, String colname, String val) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update immurtizationdata set " + colname + "='" + val + "' where clientid = '" + clientId + "' ";
		try {
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Boolean checkimmurtizationdata(String clientid) {
		boolean flag = false;
		try {
			String sql = "select id from immurtizationdata where clientid = '" + clientid + "' ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public int saveimmurtizationdata(String clientid) {
		int result = 0;
		try {
			String sql = "insert into immurtizationdata (clientid) values (?) ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, clientid);
			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	// lokesh
	public ArrayList<Master> getVaccinationdata(Client client) {
		PreparedStatement ps = null;
		ArrayList<Master> list = new ArrayList<Master>();
		/*
		 * int s=getcountoftotalimmunization(); for(int i=0;i<s;i++){ list.add(null); }
		 */

		try {
			StringBuffer bf = new StringBuffer();
			bf.append(
					"select id,name,depends_on,scheduled_on,is_compulsary,excludes, parent,info,duration,dependancy,totaldays,genderspecified from apm_vacination_master");
			if (!(client.getVacine_type() == 0)) {
				bf.append("  where type ='" + client.getVacine_type() + "' ");
			} else {
				bf.append("  where type is null or type=0 ");
			}
			bf.append("order by (scheduled_on+0) ");
			// String sql="select id,name,depends_on,scheduled_on,is_compulsary,excludes,
			// parent,info,duration,dependancy,totaldays,genderspecified from
			// apm_vacination_master";
			ps = connection.prepareStatement(bf.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setVacinname(rs.getString(2));
				master.setVacine_dependson(rs.getString(3));
				master.setVacine_scheduledon(rs.getString(4));
				master.setVacine_iscompulsary(rs.getString(5));
				master.setVacine_excludes(rs.getString(6));
				master.setParentid(rs.getString(7));
				master.setVacine_info(rs.getString(8));
				master.setDuration(rs.getString(9));
				master.setDependson(rs.getInt(10));
				Master master2 = getremarkNdate(String.valueOf(client.getId()), String.valueOf(master.getId()));
				master.setVacine_remark(master2.getVacine_remark());
				master.setVacine_givendate(master2.getVacine_givendate());
				Master mstr = new Master();

				if (!DateTimeUtils.isNull(master.getVacine_dependson()).equals("")) {
					mstr = getremarkNdate(String.valueOf(client.getId()), master.getVacine_dependson());
				} else if (rs.getInt(10) > 0) {
					mstr = getremarkNdate(String.valueOf(client.getId()), "" + master.getDependson());
				}

				if (mstr != null) {
					master.setDependent_name(mstr.getVacinname());
				} else {
					master.setDependent_name("");
				}
				if (mstr != null && mstr.getVacine_givendate() != null) {
					if (!mstr.getVacine_givendate().equals("")) {
						master.setDependant_given(true);
					}
				}
				if (DateTimeUtils.isNull(master.getVacine_dependson()).equals("")) {
					master.setDependant_given(true);
				}
				master.setDependson(rs.getInt(10));
				master.setDeppendsonDays(rs.getInt(11));
				master.setGendervaccine(rs.getInt(12));
				client.setGender(DateTimeUtils.isNull(client.getGender()));
				if (master.getGendervaccine() > 0) {
					if (master.getGendervaccine() == 1 && client.getGender().equals("Female")) {
						master = null;
						continue;
					}
					if (master.getGendervaccine() == 2 && client.getGender().equals("Male")) {
						master = null;
						continue;
					}
				}
				/* list.set(master.getId()-1, master); */

				list.add(master);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int getvacinedependacyvale(String dependid) {
		PreparedStatement ps = null;
		int res = 0;
		try {
			String sql = "select scheduled_on from apm_vacination_master where id=" + dependid + "";
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				res = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int savevacinationimmunizationajax(String mastername, String masterid, String clientid, String givendate,
			String duedate) {
		PreparedStatement ps = null;
		int result = 0;
		givendate = DateTimeUtils.getCommencingDate1(givendate);
		duedate = DateTimeUtils.getCommencingDate1(duedate);
		try {
			String sql = "insert into apm_vacination_data(masterid,mastername,clientid,givendate,duedate) values(?,?,?,?,?)";
			ps = connection.prepareStatement(sql);
			ps.setString(1, masterid);
			ps.setString(2, mastername);
			ps.setString(3, clientid);
			ps.setString(4, givendate);
			ps.setString(5, duedate);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int checkvacinationimmunizationajaxData(String masterid, String clientid) {
		int result = 0;
		PreparedStatement ps = null;
		try {
			String sql = "select id from apm_vacination_data where masterid='" + masterid + "' and clientid='"
					+ clientid + "'";
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public int updatevacinationimmunizationajaxdate(String id, String givendate, String duedate) {
		PreparedStatement ps = null;
		int result = 0;
		givendate = DateTimeUtils.getCommencingDate1(givendate);
		duedate = DateTimeUtils.getCommencingDate1(duedate);
		try {
			String sql = "update apm_vacination_data set givendate =? , duedate=? where id=" + id + "";
			ps = connection.prepareStatement(sql);
			ps.setString(1, givendate);
			ps.setString(2, duedate);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updatevacinationimmunizationajaxRemark(String clientid, String masterid, String remark) {
		PreparedStatement ps = null;
		int result = 0;
		try {
			String sql = "update apm_vacination_data set remark =? where clientid=" + clientid + " and masterid='"
					+ masterid + "'";
			ps = connection.prepareStatement(sql);
			ps.setString(1, remark);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Master getremarkNdate(String clientid, String masterid) {
		Master master = new Master();
		PreparedStatement ps = null;
		try {
			String sql = "select remark , givendate, mastername from apm_vacination_data where clientid='" + clientid
					+ "' and masterid='" + masterid + "'";
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			master.setVacine_remark("");
			master.setVacine_givendate("");
			while (rs.next()) {
				master.setVacine_remark(DateTimeUtils.isNull(rs.getString(1)));
				if (DateTimeUtils.isNull(rs.getString(2)).equals("")) {
					master.setVacine_givendate("");
				} else {
					if (rs.getString(2).contains("-")) {
						master.setVacine_givendate(DateTimeUtils.getCommencingDate1(rs.getString(2)));
					} else {
						master.setVacine_givendate(rs.getString(2));
					}
				}
				master.setVacinname(DateTimeUtils.isNull(rs.getString(3)));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return master;
	}

	int getcountoftotalimmunization() {
		int res = 0;
		PreparedStatement ps = null;
		try {
			String sql = " select id from apm_vacination_master order by id desc limit 1";
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				res = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int setdatatoVacinationInfo(Master master, String clientid) {
		PreparedStatement ps = null;
		int res = 0;
		String x = DateTimeUtils.getCommencingDate1(master.getScheduledate());
		try {
			String sql = "insert into apm_vacination_data(masterid,mastername,clientid,duedate) values(?,?,?,?)";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, master.getId());
			ps.setString(2, master.getVacinname());
			ps.setString(3, clientid);
			ps.setString(4, DateTimeUtils.getCommencingDate1(master.getScheduledate()));
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public boolean checksmsflag(int masterid, String clientid) {
		PreparedStatement ps = null;
		boolean res = false;
		try {
			String sql = "select smsflag from  apm_vacination_data where masterid='" + masterid + "' and clientid='"
					+ clientid + "' ";
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getBoolean(1)) {
					res = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int setsmsflag(int masterid, String clientid) {
		PreparedStatement ps = null;
		int res = 0;
		String sql = " update apm_vacination_data set smsflag=1 where masterid='" + masterid + "' and clientid='"
				+ clientid + "' ";
		try {
			ps = connection.prepareStatement(sql);
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

//gets the date as param for checking whether is vacine date or not
	public ArrayList<Master> getAllClientVaccinations(String date, LoginInfo loginInfo, boolean is1daysms) {
		ArrayList<Master> list = new ArrayList<Master>();
		PreparedStatement ps = null;
		try {
			SmsService s = new SmsService();
		
			StringBuffer buffer = new StringBuffer();
			buffer.append(
					" select a.id,a.masterid,a.mastername,concat(b.title,' ',b.firstname,' ',b.surname),b.mobno,a.clientid,fullname  from apm_vacination_data a ");
			buffer.append("  inner join apm_patient b on b.id= a.clientid  where a.duedate ='" + date
					+ "' and a.smsflag='0' and b.mobno is not null and b.mobno!=''   ");
			ps = connection.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();
			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			FinderDAO finderDAO=new  JDBCFinderDAO(connection);
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setMasterid(rs.getInt(2));
				master.setVacinname(rs.getString(3));
				master.setClientname(rs.getString(4));
				master.setMobileNo(rs.getString(5));
				master.setClientid(rs.getString(6));
				master.setFullname(rs.getString(7));
				if (DateTimeUtils.isNull(rs.getString(5)).equals("")) {
					continue;
				}
				boolean smssent = true;
				if (is1daysms) {
					smssent = checksmsflag(master.getMasterid(), rs.getString(6));
				} else {
					smssent = checksmsflag7day(master.getMasterid(), rs.getString(6));
				}

				String expired = getExpiredstatus(rs.getString(6));
				if (!smssent) {
					if (!expired.equals("11") && !expired.equals("7") && !expired.equals("8")) {
						UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
						Connection con = connection;
						String msg="";
						
						String  templateid = userProfileDAO.getSMSTemplateID("Vaccination");
						Clinic clinic1 = clinicDAO.getWhatsappActive(loginInfo.getId());
						// String msg =loginInfo.getClinicName() +" : "+master.getClientname() +"
						// à¤†à¤ªà¤•à¤¾ "+master.getVacinname()+" à¤•à¤¾ à¤Ÿà¥€à¤•à¤¾à¤•à¤°à¤£
						// "+DateTimeUtils.getCommencingDate1(date)+" à¤ªà¤° à¤¨à¤¿à¤°à¥�à¤§à¤¾à¤°à¤¿à¤¤
						// à¤¹à¥ˆ| Phone: "+clinic.getLandLine()+" ";
						/*
						 * String msg = loginInfo.getClinicName() + " : Dear Parents\n" +
						 * master.getClientname() + " à¤†à¤ªà¤•à¤¾ " + master.getVacinname() +
						 * " à¤•à¤¾ à¤Ÿà¥€à¤•à¤¾à¤•à¤°à¤£ " + DateTimeUtils.getCommencingDate1(date) +
						 * " à¤ªà¤° à¤¨à¤¿à¤°à¥�à¤§à¤¾à¤°à¤¿à¤¤ à¤¹à¥ˆ| Phone: " + clinic.getLandLine()
						 * + " ";
						 */
						loginInfo.setPatient_name(master.getClientname());
						 if(loginInfo.isParihar()) {
								 
						 msg="PARIHAR HOSPITAL प्रिय माता पिता "+ master.getClientname() +" आपका "+ master.getVacinname() +" वैक्सीन का टीकाकरण दिनांक "+ DateTimeUtils.getCommencingDate1(date) +" पर निर्धारित है फोन 07741232577, 9753059148";
						 s.sendvaccineSms(msg, master.getMobileNo(), loginInfo, new EmailLetterLog(), con, templateid);
					    }else if(loginInfo.isSjivh()) {
					     msg="INDIAN VETERINARY HOSPITAL : Dear Pet Parent "+ master.getClientname() +" आपके Pet "+master.getFullname()+"  का टीकाकरण "+ DateTimeUtils.getCommencingDate1(date) +" पर निर्धारित है| 0771-3586865, 6232765217 www.ivhraipur.com";	
					    
					     int duedate_flag=finderDAO.getDuedate_flagbyclientid(master.getClientid(),"0",master.getVacinname());
					     if(duedate_flag==1) {
					     s.sendvaccineSms(msg, master.getMobileNo(), loginInfo, new EmailLetterLog(), con, templateid);
					     }
					    
					    }else {
						 msg = loginInfo.getClinicName() + " : Dear Parents\n" + master.getClientname()
						+ " %20%E0%A4%86%E0%A4%AA%E0%A4%95%E0%A4%BE%20 " + master.getVacinname() + " %20%E0%A4%95%E0%A4%BE%20%E0%A4%9F%E0%A5%80%E0%A4%95%E0%A4%BE%E0%A4%95%E0%A4%B0%E0%A4%A3%20 "
						+ DateTimeUtils.getCommencingDate1(date)
						+ " %E0%A4%AA%E0%A4%B0%20%E0%A4%A8%E0%A4%BF%E0%A4%B0%E0%A5%8D%E0%A4%A7%E0%A4%BE%E0%A4%B0%E0%A4%BF%E0%A4%A4%20%E0%A4%B9%E0%A5%88| Phone: " + clinic.getLandLine() + " ";
						
						 s.sendvaccineSms(msg, master.getMobileNo(), loginInfo, new EmailLetterLog(), con, templateid);
					    }
						
						
						
						if(clinic1.getWsms()==1){
							WhatsAppService wsms=new WhatsAppService();
							//String templateid = userProfileDAO.getSMSTemplateID("Tokan SMS");
							String message=loginInfo.getClinicName() +" :Dear Parents "+ master.getClientname() +" आपका "+ master.getVacinname() +" वैक्सीन का टीकाकरण दिनांक "+ DateTimeUtils.getCommencingDate1(date) +" पर निर्धारित है";
							wsms.sendMsgvacc(loginInfo,master.getMobileNo(),message,connection);
						}
						if (is1daysms) {
							int w = setsmsflag(master.getMasterid(), rs.getString(6));
						} else {
							int w = setsmsflag7Day(master.getMasterid(), rs.getString(6));
						}
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private String getExpiredstatus(String clientid) {
		PreparedStatement pst = null;
		String res = "";
		try {
			String sql = "select outcomes from apm_treatment_episode where clientid='" + clientid + "'";
			pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				res = rs.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int setsmsflag7Day(int masterid, String clientid) {
		PreparedStatement ps = null;
		int res = 0;
		String sql = " update apm_vacination_data set smsflag7days=1 where masterid='" + masterid + "' and clientid='"
				+ clientid + "' ";
		try {
			ps = connection.prepareStatement(sql);
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public boolean checksmsflag7day(int masterid, String clientid) {
		PreparedStatement ps = null;
		boolean res = false;
		try {
			String sql = "select smsflag7days from  apm_vacination_data where masterid='" + masterid
					+ "' and clientid='" + clientid + "' ";
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getBoolean(1)) {
					res = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public String lmpDAte(String clientid) {
		PreparedStatement ps = null;
		String date = "";
		String sql = "select lmp from   ipd_gynic_form  where FIND_IN_SET('" + clientid + "',clientid)  ";
		try {
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				date = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (date == null) {
			date = "";
		}
		return date;
	}

	public Master getVacinationInfo(String masterid, String clientid) {
		Master master = new Master();
		PreparedStatement ps = null;
		try {
			String sql = "select name,depends_on,is_compulsary ,info,duration from apm_vacination_master where id='"
					+ masterid + "'";
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				master.setVacinname(rs.getString(1));
				master.setVacine_dependson(rs.getString(2));
				master.setVacine_iscompulsary(rs.getString(3));
				master.setVacine_info(rs.getString(4));
				master.setDuration(rs.getString(5));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return master;
	}

	public ArrayList<Master> getVacinationForShow(String clientid) {
		ArrayList<Master> list = new ArrayList<Master>();
		PreparedStatement ps = null;
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append(" SELECT name,depends_on,is_compulsary ,info,duration,givendate,remark,duedate,b.id  ");
			buffer.append("  FROM apm_vacination_data a  ");
			buffer.append(" inner join apm_vacination_master b on b.id= a.masterid where clientid='" + clientid
					+ "' and type!=1 ");
			buffer.append(" and  givendate is not null or depends_on!='' ");
			ps = connection.prepareStatement(buffer.toString());

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setVacinname(rs.getString(1));
				master.setVacine_dependson(rs.getString(2));
				master.setVacine_iscompulsary(rs.getString(3));
				master.setVacine_info(rs.getString(4));
				master.setDuration(rs.getString(5));
				master.setVacine_givendate(rs.getString(6));
				master.setVacine_remark(rs.getString(7));
				master.setScheduledate(DateTimeUtils.getCommencingDate1(rs.getString(8)));
				master.setId(rs.getInt(9));
				list.add(master);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	private String getparantnameofvacc(String masterid) {
		String res = "";

		return res;
	}

	public NotAvailableSlot getNewOpdDiaryUserData(String ndate, String nduserid) {
		PreparedStatement preparedStatement = null;
		NotAvailableSlot n = new NotAvailableSlot();
		String sql = "SELECT id,starttime,endtime,apmtduration,location,diaryuser,diaryuserid FROM apm_apmt_slot where commencing = '"
				+ ndate + "' and diaryuserid = " + nduserid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				n.setId(rs.getInt(1));
				n.setSTime(rs.getString(2));
				n.setEndTime(rs.getString(3));
				n.setDuration(rs.getString(4));
				n.setLocation(rs.getString(5));
				n.setDiaryUser(rs.getString(6));
				n.setDiaryUserId(rs.getInt(7));

				String slotetime = getSlotEndTime(ndate, nduserid);
				if (slotetime.equals("")) {
					slotetime = n.getSTime();

				}
				n.setSlotstime(slotetime);

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return n;
	}

	private String getSlotEndTime(String ndate, String nduserid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select endtime from  apm_available_slot  where commencing = '" + ndate + "' and diaryuserid = "
				+ nduserid + " " + " order by id desc limit 0,1";
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

	public ArrayList<NotAvailableSlot> getNewOpdList(String ndate, String nduserid, LoginInfo loginInfo,
			String opdlogstatus, String patientcategory) {
		PreparedStatement preparedStatement = null;
		ArrayList<NotAvailableSlot> list = new ArrayList<NotAvailableSlot>();
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
		DiaryManagementDAO diaryManagementDAO = new JDBCDiaryManagentDAO(connection);
		StringBuffer sql = new StringBuffer();

		sql.append(
				"select apm_available_slot.id, apm_available_slot.commencing,starttime,abrivationid,concat(title,' ',firstname,' ',surname),apm_available_slot.clientid,apmttypetext,condition_id, ");
		sql.append(
				"opdpmnt,apm_available_slot.whopay,apm_available_slot.status,dna,chrgstatus,drcompleted,apm_available_slot.arrivedstatus,opdpmnt,opdbooktime, ");
		sql.append(
				"duration,reqdatetime,mobstatus,reception_vid_verify,doctor_vid_verify,doctor_vid_reject_remark,diaryuserid,pending_remark,added_by,"
						+ "patient_being_seen_time,refferedfrom,apm_available_slot.opdabbrevationid,apm_patient.patientcategory,opdSequnce,fullname ");
		sql.append("from  apm_available_slot inner join apm_patient on ");
		sql.append("apm_patient.id = apm_available_slot.clientid ");
		//sql.append("inner join apm_available_slot_seqno on apm_available_slot_seqno.apmtid=apm_available_slot.id ");
		// sql.append("left join opd_secondary_dr on opd_secondary_dr.opdappointment =
		// apm_available_slot.id ");
		if (opdlogstatus.equals("0")) {
			sql.append(" where apm_available_slot.commencing < '" + ndate + "' and appt_type=0 ");
		} else if (opdlogstatus.equals("2")) {
			sql.append(" where apm_available_slot.commencing > '" + ndate + "' and appt_type=0 ");
		} else if (opdlogstatus.equals("1")) {
			sql.append(" where apm_available_slot.commencing = '" + ndate + "' and appt_type=0 ");
		} else if (opdlogstatus.equals("1")) {
			sql.append("where (diaryuserid = " + nduserid + " or seconadary_dr=" + nduserid + " or seconadary_dr like '"
					+ nduserid + ",%' or seconadary_dr like '%," + nduserid + ",%' or seconadary_dr like '%," + nduserid
					+ "') and apm_available_slot.commencing = '" + ndate
					+ "' and apm_available_slot.status=0 and appt_type=0 ");
		} else if (opdlogstatus.equals("3")) {
			/*
			 * sql.append("where (diaryuserid = " + nduserid + " or seconadary_dr=" +
			 * nduserid + " or seconadary_dr like '" + nduserid +
			 * ",%' or seconadary_dr like '%," + nduserid + ",%' or seconadary_dr like '%,"
			 * + nduserid + "') and apm_available_slot.commencing = '" + ndate +
			 * "' and appt_type=0 ");
			 */
			sql.append("where diaryuserid = " + nduserid + " and apm_available_slot.commencing = '" + ndate
					+ "' and appt_type=0 ");
		} else {
			// sql.append("where (diaryuserid = "+nduserid+" or seconadary_dr="+nduserid+"
			// or seconadary_dr like '"+nduserid+",%' or seconadary_dr like
			// '%,"+nduserid+",%' or seconadary_dr like '%,"+nduserid+"') and commencing =
			// '"+ndate+"' and appt_type=0 ");
			sql.append("where diaryuserid = " + nduserid + " and apm_available_slot.commencing = '" + ndate
					+ "' and appt_type=0 ");
		}
		if (loginInfo.getUserType() == 5) {
			sql.append(" and apm_available_slot.clientid=" + loginInfo.getPuresevaclientid() + "");
		}

		if (loginInfo.isLmh()) {
			if (!patientcategory.equals("")) {
				sql.append(" and apm_patient.patientcategory='" + patientcategory + "'");
			}

		}

		if (opdlogstatus.equals("3")) {
			sql.append(" order by id asc");
		} else {
			sql.append(" order by id desc");
		}

		try {

			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NotAvailableSlot n = new NotAvailableSlot();

				n.setId(rs.getInt(1));

				String pbodytemplate = getopdpbodytmplate();
				String pbodyeditedtmplate = getopdpbodyedited(n.getId());

				n.setPbodytemplate(pbodytemplate);
				n.setPbodyeditedtmplate(pbodyeditedtmplate);

				n.setCommencing(rs.getString(2));
				n.setSTime(rs.getString(3));
				n.setAbrivationid(rs.getString(4));
				n.setClientName(rs.getString(5));
				n.setClientId(rs.getString(6));
				Client client = clientDAO.getClientDetails(n.getClientId());

				String agegender = client.getAgegender();
				//String age = DateTimeUtils.getAge1(client.getDob());
				String age = DateTimeUtils.getAgeyear(client.getDob());
				n.setAgegender(age + " / " + client.getGender());
				n.setApmttypetext(rs.getString(7));
				n.setCondition(rs.getString(8));
				n.setInvoiceid(rs.getString(9));
				n.setWhopay(rs.getString(10));
				n.setStatus(rs.getString(11));// check appointment completed
				n.setDna(rs.getBoolean(12));
				boolean isCompleted = false;
				if (rs.getInt(13) == 1) {
					isCompleted = true;

				}
				if (isCompleted && !n.isDna()) {
					n.setAppointmentCompleted(isCompleted);
				}

				n.setDrcompleted(rs.getInt(14));

				n.setArrivedStatus(rs.getInt(15));
				n.setOpdpmnt(rs.getInt(16));
				n.setOpdbooktime(DateTimeUtils.isNull(rs.getString(17)));
				n.setDuration(rs.getString(18));
				if (rs.getString(19) != null) {
					n.setOpdbookdate(DateTimeUtils.getCommencingDate1(rs.getString(19).split(" ")[0]));
				}
				// n.setOpdbookdate(DateTimeUtils.getCommencingDate1(rs.getString(19).split("
				// ")[0]));
				n.setMobstatusnew(rs.getInt(20));
				n.setReception_vid_verify(rs.getInt(21));
				n.setDoctor_vid_verify(rs.getInt(22));
				n.setDoctor_vid_reject_remark(DateTimeUtils.isNull(rs.getString(23)));
				n.setDiaryUserId(rs.getInt(24));
				n.setPending_remark(DateTimeUtils.isNull(rs.getString(25)));
				n.setAddedBy(rs.getString(26));
				if (DateTimeUtils.isNull(rs.getString(27)).equals("")) {
					n.setPatientisseen(0);
				} else {
					n.setPatientisseen(1);
				}
				n.setRefferedfrom(rs.getString(28));
				n.setOpdAbbrId(rs.getString(29));
				n.setPatientcategory(DateTimeUtils.isNull(rs.getString(30)));
				n.setOpdSequnce(DateTimeUtils.isNull(rs.getString(31)));
				n.setFullname(DateTimeUtils.isNull(rs.getString(32)));
				//n.setToken(rs.getString(32));
				String pstatus = clientDAO.getPtypeNewOld(rs.getInt(6));
				n.setPstatus(pstatus);
				Accounts accounts=accountsDAO.getPatientTransactiondata(n.getClientId());
				n.setBalance(accounts.getBalance());
				n.setAdvance(accounts.getAdvance());
				String seqno=getavialableSeqno(rs.getInt(1),rs.getString(2),rs.getInt(24));
				n.setToken(seqno);
				list.add(n);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private String getavialableSeqno(int apmtid, String date, int docid) {
		PreparedStatement preparedStatement = null;
		String seqno = "";
		date=date.split(" ")[0];
		String sql = "select seqno from apm_available_slot_seqno where apmtid = " +apmtid+" and pid='"+docid+"' and commencing='"+date+"'";
				

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				seqno = rs.getString(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return seqno;
	}

	private String getopdpbodyedited(int id) {
		PreparedStatement preparedStatement = null;
		String result = "0";
		String sql = "select id from apm_assessment_client_details where opdid = " + id
				+ " order by id desc limit 0,1 ";

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

	private String getopdpbodytmplate() {
		PreparedStatement preparedStatement = null;
		String result = "0";
		String sql = "SELECT id FROM apm_assment_template where pbody = 1 ";
		try {

			int t = 1;

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

	public int updateChargeStatus(String appointmentid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_available_slot set chrgstatus=1 where id = " + appointmentid + " ";

		try {

			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public int saveDateOfOPDEvents(String appointmentid, String colname) {
		int res = 0;
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String date = dateFormat.format(cal.getTime());
			PreparedStatement ps = connection.prepareStatement(
					"update apm_available_slot set " + colname + "='" + date + "' where id ='" + appointmentid + "'");
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int saveDiagnosisOpd(String opdid, String diagnosisids) {
		int x = 0;
		try {
			PreparedStatement ps = connection.prepareStatement(
					"update apm_available_slot set opd_diagnosis='" + diagnosisids + "' where id='" + opdid + "'");
			x = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return x;
	}

	public String getAllDiagnosisofOpd(String opdid) {
		String result = "", temp = "";
		try {
			PreparedStatement ps = connection
					.prepareStatement("select opd_diagnosis from   apm_available_slot  where id='" + opdid + "'");
			ResultSet rs = ps.executeQuery();
			int i = 0;
			while (rs.next()) {
				temp = rs.getString(1);
			}
			if (temp != null) {
				if (!temp.equals("")) {
					for (String x : temp.split(",")) {
						if (x.equals("0")) {
							continue;
						}
						if (i == 0) {
							result = getDiagnosisNames(x);
							i = 1;
						} else {
							result = result + "," + getDiagnosisNames(x);
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private String getDiagnosisNames(String id) {
		String result = "";
		try {
			PreparedStatement ps = connection
					.prepareStatement(" select id,name,diseasecode,icdcode from apm_diagnosis where id='" + id + "'");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String condition = rs.getString(2);
				if (rs.getString(3) != null && rs.getString(4) != null) {
					condition = condition + " " + rs.getString(3) + " / " + rs.getString(4);
				} else if (rs.getString(4) != null) {
					if (rs.getString(4).equals("")) {
						// condition = condition;
					} else if (rs.getString(4).equals("0")) {
						// condition = condition;
					} else {
						condition = condition + " / " + rs.getString(4);
					}
				}
				result = condition;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean chkmveapmtaxsist(String duserid, String commencing) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "select * from apm_available_slot where diaryuserid = " + duserid + " and commencing = '"
				+ commencing + "' ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = true;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public NotAvailableSlot getMveDiaryUserDetails(String duserid, String commencing) {
		PreparedStatement preparedStatement = null;
		NotAvailableSlot n = new NotAvailableSlot();
		String sql = "SELECT id,starttime,apmtduration FROM apm_apmt_slot where diaryuserid = " + duserid
				+ " and commencing = '" + commencing + "'";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				n.setId(rs.getInt(1));
				n.setSTime(rs.getString(2));
				n.setDuration(rs.getString(3));
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return n;
	}

	public String getmveapmtendtime(String duserid, String commencing) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select endtime from apm_available_slot " + " where diaryuserid = " + duserid
				+ " and commencing = '" + commencing + "' order by id desc limit 0,1 ";

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

	public int updatemveappointment(NotAvailableSlot n) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_available_slot set apmslotid=?, diaryusername=?,diaryuserid=?,"
				+ " commencing=?,starttime=?,endtime=?,duration=?,aptmtype=?,apmttypetext=?,charge=?,transfer_id=?	  " + " where id = "
				+ n.getAppointmentid() + " ";

		try {

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, n.getId());
			preparedStatement.setString(2, n.getDiaryUser());
			preparedStatement.setInt(3, n.getDiaryUserId());
			preparedStatement.setString(4, n.getCommencing());
			preparedStatement.setString(5, n.getSTime());
			preparedStatement.setString(6, n.getEndTime());
			preparedStatement.setString(7, n.getDuration());
			preparedStatement.setString(8, n.getApmtType());
			preparedStatement.setString(9, n.getApmttypetext());
			preparedStatement.setDouble(10, n.getCharge());
			preparedStatement.setString(11, n.getInvoiceid());
			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updatemveapminvoice(String editAppointId, NotAvailableSlot n) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_invoice set practitionerId=?,practitionerName=? where appointmentid = " + editAppointId
				+ " ";

		try {

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, n.getDiaryUserId());
			preparedStatement.setString(2, n.getDiaryUser());

			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
		}

		return 0;
	}

	public Accounts getmveapmtchargeinfo(String editAppointId) {
		PreparedStatement preparedStatement = null;
		Accounts a = new Accounts();
		String sql = "select invoiceid, invoiced from apm_invoice inner join apm_invoice_assesments "
				+ " on apm_invoice_assesments.invoiceid = apm_invoice.id " + " where apm_invoice.appointmentid = "
				+ editAppointId + " limit 0,1 ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				a.setId(rs.getInt(1));
				a.setInvoiceid(rs.getInt(2));
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return a;
	}

	public int updatemveapminvoiceassesmnt(int id, NotAvailableSlot n) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_invoice_assesments set practitionerId=?,practitionerName=?,apmtType=?,charge=? "
				+ "  where invoiceid = " + id + " and masterchargetype!='Registration Charge'";

		try {

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, n.getDiaryUserId());
			preparedStatement.setString(2, n.getDiaryUser());
			preparedStatement.setString(3, n.getApmttypetext());
			preparedStatement.setDouble(4, n.getCharge());

			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
		}

		return 0;
	}

	public int updatemveapmtchargesinvoice(int invoiceid, NotAvailableSlot n) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_charges_invoice set practid=" + n.getDiaryUserId() + " " + " where id = " + invoiceid
				+ " ";

		try {

			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
		}

		return 0;
	}

	public String getGivenDate(int dependson, String clientid) {
		String res = "";
		try {
			PreparedStatement ps = connection
					.prepareStatement(" select givendate from apm_vacination_data where masterid='" + dependson
							+ "' and clientid='" + clientid + "' ");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				res = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int updatedueDateOfVaccine(String clientid, String masterid, String date) {
		int res = 0;
		try {
			PreparedStatement ps = connection.prepareStatement(" update apm_vacination_data set duedate='" + date
					+ "' where  masterid='" + masterid + "' and clientid='" + clientid + "'  ");
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public void startImmnunizationAppTProccess(String date, LoginInfo loginInfo, String type) {
		try {
			int diaryUser = getVaccinator();
			if (diaryUser > 0) {
				UserProfile user = new UserProfile();
				UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
				user = userProfileDAO.getUserprofileDetails(diaryUser);
				PreparedStatement ps = null;
				StringBuffer buffer = new StringBuffer();
				buffer.append(
						" select a.id,a.masterid,a.mastername,concat(b.title,' ',b.firstname,' ',b.surname),b.mobno,a.clientid,a.duedate  from apm_vacination_data a ");
				buffer.append("  inner join apm_patient b on b.id= a.clientid  where a.clientid ='" + date
						+ "' and a.isbooked='0' ");
				ps = connection.prepareStatement(buffer.toString());
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					date = rs.getString(7);
					Master master = new Master();
					master.setId(rs.getInt(1));
					master.setMasterid(rs.getInt(2));
					master.setVacinname(rs.getString(3));
					master.setClientname(rs.getString(4));
					master.setMobileNo(rs.getString(5));
					master.setClientid(rs.getString(6));

					/* Logic */
					NotAvailableSlot n = getMveDiaryUserDetails("" + diaryUser, date);

					String stime = n.getSTime();
					int slotid = n.getId();
					String duration = n.getDuration();
					boolean chkapmtexsist = chkmveapmtaxsist("" + diaryUser, date);
					if (chkapmtexsist) {
						stime = getmveapmtendtime("" + diaryUser, date);
					}
					SimpleDateFormat df = new SimpleDateFormat("HH:mm");
					Date d = df.parse(stime);
					Calendar cal = Calendar.getInstance();
					cal.setTime(d);
					cal.add(Calendar.MINUTE, 10);
					String endtime = df.format(cal.getTime());
					int apmtid = getApmTOfVaccine("" + master.getMasterid());
					NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
					notAvailableSlot.setApmtSlotId(slotid);
					notAvailableSlot.setCommencing(date);
					notAvailableSlot.setSTime(stime);
					notAvailableSlot.setEndTime(endtime);
					notAvailableSlot.setDiaryUserId(diaryUser);
					notAvailableSlot.setDiaryUser(user.getFullname());
					notAvailableSlot.setLocation("1");
					notAvailableSlot.setDept(user.getDiciplineName());
					notAvailableSlot.setClient(master.getClientname());
					notAvailableSlot.setClientId(master.getClientid());
					notAvailableSlot.setApmtDuration("00:10");
					notAvailableSlot.setApmtType("" + apmtid);
					notAvailableSlot.setRoom("Room1");
					notAvailableSlot.setPayBy("Client");
					notAvailableSlot.setAddedBy("" + loginInfo.getUserId());
					notAvailableSlot.setCondition(user.getDiciplineName());
					notAvailableSlot.setVaccineApmt(true);
					notAvailableSlot.setNotes("");
					notAvailableSlot.setTreatmentEpisodeId("0");
					notAvailableSlot.setUsedsession("0");
					notAvailableSlot.setOtid(0);
					notAvailableSlot.setOtplan("" + 0);
					notAvailableSlot.setProcedure("" + 0);
					notAvailableSlot.setSurgeon("" + 0);
					notAvailableSlot.setAnesthesia("" + 0);
					notAvailableSlot.setIpdno("" + 0);
					notAvailableSlot.setWardid("" + 0);
					notAvailableSlot.setAssistaffcharge("" + 0);
					notAvailableSlot.setSic("" + 0);
					notAvailableSlot.setPsurcharge("" + 0);
					notAvailableSlot.setPanetcharge("" + 0);
					notAvailableSlot.setAppt_type(DateTimeUtils.convertToInteger(type));

					int x = saveAppointment(notAvailableSlot);
					setBookedStatus(master.getId());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private int setBookedStatus(int id) {
		int res = 0;
		try {
			PreparedStatement ps = connection
					.prepareStatement(" update apm_vacination_data set isbooked='1' where id='" + id + "'");
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int getVaccinator() {
		int res = 0;
		try {
			PreparedStatement ps = connection.prepareStatement(" select id from apm_user where vaccinator='1' ");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				res = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int updateDueDate(String value, String masterid, String clientid) {
		int res = 0;
		try {
			PreparedStatement ps = connection.prepareStatement(
					" update apm_vacination_data set duedate='" + DateTimeUtils.getCommencingDate1(value)
							+ "' where masterid='" + masterid + "' and clientid='" + clientid + "' ");

			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	private int getApmTOfVaccine(String masterid) {
		int x = 0;
		try {
			PreparedStatement ps = connection
					.prepareStatement(" select charge_name from apm_vacination_master where id='" + masterid + "'");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return x;
	}

	public String getDueDate(String masterid, String clientid) {
		String date = "";
		try {
			PreparedStatement ps = connection
					.prepareStatement(" select  duedate from apm_vacination_data   where masterid='" + masterid
							+ "' and clientid='" + clientid + "'  ");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				date = DateTimeUtils.getCommencingDate1(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	public boolean bookededStatsu(String clientid) {
		boolean ststs = false;
		try {
			PreparedStatement ps = connection.prepareStatement(
					" select  isbooked from apm_vacination_data   where  clientid='" + clientid + "' limit 1 ");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ststs = rs.getBoolean(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ststs;
	}

	public String getDOBChangeLogDate(String clientId) {
		String res = "";
		try {
			String sql = " select dob from dob_change_log where clientId='" + clientId + "'";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				res = DateTimeUtils.isNull(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int insertDobChangeLog(String clientId, String dob) {
		int res = 0;
		try {
			String sql = "insert into dob_change_log(dob,clientId) values(?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, dob);
			ps.setString(2, clientId);
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int updateScheduledDateAfterDOBChange(String masterid, String date, String clientId) {
		String sql = " update apm_vacination_data set duedate='" + date + "' where masterid='" + masterid
				+ "' and clientid ='" + clientId + "'";

		int res = 0;
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int checkPatientChargesCreated(String apmtid) {
		int res = 0;
		try {
			String sql = " select chrgstatus,opdpmnt from apm_available_slot where id='" + apmtid + "'";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				res = rs.getInt(1);
				if (res == 0 && rs.getInt(2) > 0) {
					res = 1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<DiaryManagement> getUserListwithdept(int id, String dept) {
		PreparedStatement preparedStatement = null;
		ArrayList<DiaryManagement> list = new ArrayList<DiaryManagement>();
		String sql = "select id,initial,firstname,lastname from apm_user where usertype=4 and islogin=1  and (jobtitle='Practitioner' or hasdiary=1) order by firstname asc";

		try {

			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				DiaryManagement diaryManagement = new DiaryManagement();
				diaryManagement.setId(rs.getInt(1));
				if (rs.getString(2) == null || rs.getString(2).equals("")) {
					diaryManagement.setDiaryUser(rs.getString(3) + " " + rs.getString(4));

				} else {
					diaryManagement.setDiaryUser(rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));

				}
				list.add(diaryManagement);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public boolean checkallbooked(String date, int id, String stime) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "SELECT * FROM apm_available_slot where diaryuserid = " + id + " and starttime = '" + stime
				+ "' and commencing = '" + date + "' ";

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

	public NotAvailableSlot getDiaryData(String date, String diaryuserId, String location) {
		PreparedStatement preparedStatement = null;
		NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
		String sql = "SELECT starttime,endtime,id,apmtduration FROM apm_apmt_slot where commencing='" + date
				+ "' and diaryuserid =" + diaryuserId + " and location =1 ";
		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				notAvailableSlot.setsTime(rs.getString(1));
				notAvailableSlot.setEndTime(rs.getString(2));
				notAvailableSlot.setId(rs.getInt(3));
				notAvailableSlot.setDuration(rs.getString(4));

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return notAvailableSlot;
	}

	public ArrayList<NotAvailableSlot> getctimeList(String cweekName) {
		PreparedStatement preparedStatement = null;
		ArrayList<NotAvailableSlot> list = new ArrayList<NotAvailableSlot>();
		String sql = "SELECT stime,etime,id FROM apm_configuard_time ";
		if (cweekName.equals("Saturday")) {
			sql = "SELECT stime,etime,id FROM apm_configuard_time where sat = 1 ";
		}
		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NotAvailableSlot n = new NotAvailableSlot();
				n.setsTime(rs.getString(1));
				n.setEndTime(rs.getString(2));
				n.setId(rs.getInt(3));

				list.add(n);

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	public boolean checkLastAppointment(int diaryuserid, String speciality, String clientid) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "SELECT * FROM apm_available_slot where diaryuserid = " + diaryuserid + " and speciality = "
				+ speciality + " and clientId=" + clientid + " ";

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

	public String getlastappointmentdate(int diaryuserid, String speciality, String clientid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT commencing FROM apm_available_slot where diaryuserid = " + diaryuserid
				+ " and speciality = " + speciality + " and clientId=" + clientid + " order by id DESC limit 0,1 ";

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

	public String getAppointmentTypeId(String opdFollowupCharges) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT id FROM apm_appointment_type where name='" + opdFollowupCharges + "'";

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

	public ArrayList<AppointmentType> getAppointmentTypeListForOPD() {
		PreparedStatement preparedStatement = null;
		ArrayList<AppointmentType> list = new ArrayList<AppointmentType>();
		String sql = "SELECT duration,name,id,charges FROM apm_appointment_type where tpid = 0 and chargeType='Appointment Charge' order by name ";

		try {

			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				AppointmentType appointmentType = new AppointmentType();
				appointmentType.setDuration(rs.getString(1));
				String charge = rs.getString(4);
				appointmentType.setName(rs.getString(2) + " (" + charge + ")");
				appointmentType.setId(rs.getInt(3));
				list.add(appointmentType);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int checkBlockslot(String date, int diaryuserid, String starttime1) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT * FROM apm_available_slot where diaryuserid = " + diaryuserid + " and starttime = '"
				+ starttime1 + "' and commencing = '" + date + "' and status=1 ";

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

	public boolean checkAttachment(int id) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "SELECT * FROM apm_document where appointmentid = " + id + " ";

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

	public int setnotesandstatus(String editAppointId, String remark) {
		String sql = " update apm_available_slot set doctor_vid_reject_remark='" + remark + "' where id='"
				+ editAppointId + "' ";

		int res = 0;
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int setvideostatus(String editAppointId) {
		String sql = " update apm_available_slot set doctor_vid_verify=1 where id='" + editAppointId + "' ";

		int res = 0;
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public boolean checkisappointment(int id, String commencing) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "SELECT * FROM apm_available_slot where commencing = '" + commencing + "' and diaryuserid=" + id
				+ " ";

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

	public ArrayList<NotAvailableSlot> getSheduleStaffReport(String fromDate, String toDate) {
		ArrayList<NotAvailableSlot> arrayList = new ArrayList<NotAvailableSlot>();
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("SELECT diaryusername,commencing,starttime,endtime,notes, duration FROM his_staff_slot ");
			buffer.append("where commencing between '" + fromDate + "' and '" + toDate + "' ");
			buffer.append("ORDER BY commencing ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NotAvailableSlot availableSlot = new NotAvailableSlot();
				availableSlot.setDiaryUser(rs.getString(1));
				availableSlot.setCommencing(DateTimeUtils.getCommencingDate1(rs.getString(2)));
				availableSlot.setsTime(rs.getString(3));
				availableSlot.setEndTime(rs.getString(4));
				availableSlot.setNotes(rs.getString(5));
				availableSlot.setDuration(rs.getString(6));
				String temp[] = rs.getString(2).split("-");
				int wyear = Integer.parseInt(temp[0]);
				int month = Integer.parseInt(temp[1]);
				int day = Integer.parseInt(temp[2]);
				String weekday = DateTimeUtils.getWeekName(wyear, month, day);
				availableSlot.setWeekday(weekday);
				arrayList.add(availableSlot);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public ArrayList<DiaryManagement> getUserListwithdepartment(int id, String dept) {
		PreparedStatement preparedStatement = null;
		ArrayList<DiaryManagement> list = new ArrayList<DiaryManagement>();
		StringBuffer buffer = new StringBuffer();

		try {
			buffer.append("select id,initial,firstname,lastname from apm_user ");
			buffer.append("where usertype=4 and islogin=1  and hasdiary=1 ");
			if (!DateTimeUtils.isNull(dept).equals("")) {
				buffer.append(" and discription=" + dept + " and secondary_doc=0 ");
			}

			buffer.append("order by firstname asc ");
			preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				DiaryManagement diaryManagement = new DiaryManagement();
				diaryManagement.setId(rs.getInt(1));
				if (rs.getString(2) == null || rs.getString(2).equals("")) {
					diaryManagement.setDiaryUser(rs.getString(3) + " " + rs.getString(4));

				} else {
					diaryManagement.setDiaryUser(rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));

				}
				list.add(diaryManagement);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public int getUndoneAppointment(String nduserid, String ndate) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select count(*) from apm_available_slot where chrgstatus=0 and opdpmnt=0  and diaryuserid="
				+ nduserid + " and commencing = '" + ndate + "'";

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

	public ArrayList<DiaryManagement> getallchargesList(String fromdate, String todate, String practid) {
		PreparedStatement preparedStatement = null;

		ArrayList<DiaryManagement> list = new ArrayList<DiaryManagement>();
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		StringBuffer buffer = new StringBuffer();
		buffer.append("select  admissionid, ipd_bed_change_log.wardid, ipd_bed_change_log.bedid, ");
		buffer.append("ipd_bed_change_log.clientid, selectedshiftdata ");
		buffer.append("from ipd_bed_change_log ");
		buffer.append("inner join ipd_addmission_form on ipd_addmission_form.id=admissionid ");
		buffer.append("where selectedshiftdata between '" + fromdate + "' and '" + todate + "' and practitionerid="
				+ practid + " group by admissionid");
		try {

			preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				DiaryManagement diaryManagement = new DiaryManagement();
				diaryManagement.setIpdid(rs.getString(1));
				diaryManagement.setWardid(rs.getString(2));
				diaryManagement.setBedid(rs.getString(3));
				diaryManagement.setClientid(rs.getString(4));
				diaryManagement.setAdmissiondate(DateTimeUtils.getDBDate(ipdDAO.getIPDAdmissionDate(rs.getString(1))));
				diaryManagement.setDischargedate(clientDAO.getIpdDischargeDate(rs.getString(1)));
				ArrayList<DiaryManagement> shiftlist = getAllshiftdata(rs.getString(1), fromdate, todate);
//			String wardname = ipdDAO.getIpdWardName(rs.getString(2));
//			diaryManagement.setWardname(wardname);
//			
//			String bedname = ipdDAO.getIpdBedName(rs.getString(3));
//			diaryManagement.setBedname(bedname);
				diaryManagement.setSelectedshiftdata(rs.getString(5));
				Client client = clientDAO.getClientDetails(rs.getString(4));
				diaryManagement.setClientname(client.getFullname());
				diaryManagement.setUhid(client.getAbrivationid());
				diaryManagement.setShiftlist(shiftlist);
				list.add(diaryManagement);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	private ArrayList<DiaryManagement> getAllshiftdata(String admissionid, String fromdate, String todate) {
		PreparedStatement preparedStatement = null;
		ArrayList<DiaryManagement> list = new ArrayList<DiaryManagement>();
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		StringBuffer buffer = new StringBuffer();
		buffer.append("select  admissionid, ipd_bed_change_log.wardid, ipd_bed_change_log.bedid, ");
		buffer.append("ipd_bed_change_log.clientid, selectedshiftdata ");
		buffer.append("from (SELECT * FROM ipd_bed_change_log ORDER BY selectedshiftdata desc ) AS ipd_bed_change_log");
		buffer.append(" where admissionid=" + admissionid + " and selectedshiftdata between '" + fromdate + "' and '"
				+ todate + "' group by wardid ");
		buffer.append("order by admissionid,selectedshiftdata");
		try {

			preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			String shiftfrdate = DateTimeUtils.getDBDate(ipdDAO.getIPDAdmissionDate(admissionid)).split(" ")[0];
			String durfrdate = ipdDAO.getIPDAdmissionDate(admissionid);
			while (rs.next()) {
				String shifttdate = DateTimeUtils.getDBDate(rs.getString(5)).split(" ")[0];
				String durtodate = rs.getString(5);
				DiaryManagement diaryManagement = new DiaryManagement();
				diaryManagement.setFromdate(shiftfrdate + " " + "To" + " " + shifttdate);
				long duration = DateTimeUtils.getDifferenceOfTwoDateDBFormatExactinHours(durfrdate, durtodate);
				shiftfrdate = DateTimeUtils.getDBDate(rs.getString(5)).split(" ")[0];
				durfrdate = rs.getString(5);
				String wardname = ipdDAO.getIpdWardName(rs.getString(2));
				diaryManagement.setWardname(wardname);
				String bedname = ipdDAO.getIpdBedName(rs.getString(3));
				diaryManagement.setBedname(bedname);
				diaryManagement.setDuration("" + duration);
				ArrayList<DiaryManagement> wardwiseChargelist = getwardwiseChargelist(admissionid, rs.getInt(2),
						fromdate, todate);
				diaryManagement.setWardwiseChargelist(wardwiseChargelist);
				list.add(diaryManagement);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	private ArrayList<DiaryManagement> getwardwiseChargelist(String admissionid, int wardid, String fromdate,
			String todate) {
		PreparedStatement preparedStatement = null;
		ArrayList<DiaryManagement> list = new ArrayList<DiaryManagement>();
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		StringBuffer buffer = new StringBuffer();
		buffer.append("select  apmtType,charge,sum(quantity) ");
		buffer.append("from apm_invoice_assesments ");
		buffer.append("where tpcommencing between '" + fromdate + "' and '" + todate + "' and ipdid=" + admissionid
				+ " and wardid=" + wardid + " and masterchargetype='Bed Charge' group by apmtType");
		try {

			preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				DiaryManagement diaryManagement = new DiaryManagement();
				diaryManagement.setApmttype(rs.getString(1));
				diaryManagement.setCharge(rs.getDouble(2));
				diaryManagement.setQuantity(rs.getInt(3));
				diaryManagement.setChargetotal(rs.getDouble(2) * rs.getInt(3));
				list.add(diaryManagement);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<NotAvailableSlot> getVaccinationList(String fromDate, String toDate, String nduserid) {
		PreparedStatement preparedStatement = null;
		ArrayList<NotAvailableSlot> list = new ArrayList<NotAvailableSlot>();
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		DiaryManagementDAO diaryManagementDAO = new JDBCDiaryManagentDAO(connection);
		StringBuffer sql = new StringBuffer();

		sql.append(
				"select apm_available_slot.id, commencing,starttime,abrivationid,concat(title,' ',firstname,' ',surname),apm_available_slot.clientid,apmttypetext,condition_id, ");
		sql.append(
				"opdpmnt,apm_available_slot.whopay,apm_available_slot.status,dna,chrgstatus,drcompleted,apm_available_slot.arrivedstatus,opdpmnt,opdbooktime, ");
		sql.append(
				"duration,reqdatetime,mobstatus,reception_vid_verify,doctor_vid_verify,doctor_vid_reject_remark,diaryuserid,pending_remark,added_by,patient_being_seen_time ");
		sql.append(" ,mastername,givendate,smsflag,duedate,consumption_status,apm_vacination_data.id ");
		sql.append("from  apm_available_slot ");
		sql.append("inner join apm_vacination_data on apm_vacination_data.clientid=apm_available_slot.clientid");
		sql.append(" inner join apm_patient on apm_patient.id = apm_available_slot.clientid where duedate between '"
				+ fromDate + "' and '" + toDate + "' and appt_type=1");
		sql.append(" and isbooked=1");
		if (!(nduserid.equals("") || nduserid.equals("0"))) {
			sql.append("  and diaryuserid = " + nduserid + " ");
		}
		sql.append(" group by apm_vacination_data.id");
		try {

			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NotAvailableSlot n = new NotAvailableSlot();

				n.setId(rs.getInt(1));

				n.setCommencing(DateTimeUtils.getCommencingDate1(rs.getString(2)));
				n.setAbrivationid(rs.getString(4));
				n.setClientName(rs.getString(5));
				n.setClientId(rs.getString(6));
				Client client = clientDAO.getClientDetails(n.getClientId());

				String agegender = client.getAgegender();
				String age = DateTimeUtils.getAge1(client.getDob());
				n.setAgegender(age + " / " + client.getGender());
				n.setWhopay(rs.getString(10));
				n.setStatus(rs.getString(11));// check appointment completed
				n.setDna(rs.getBoolean(12));
				boolean isCompleted = false;
				if (rs.getInt(13) == 1) {
					isCompleted = true;

				}
				if (isCompleted && !n.isDna()) {
					n.setAppointmentCompleted(isCompleted);
				}

				n.setDiaryUserId(rs.getInt(24));
				if (DateTimeUtils.isNull(rs.getString(27)).equals("")) {
					n.setPatientisseen(0);
				} else {
					n.setPatientisseen(1);
				}
				n.setMastername(rs.getString(28));
				n.setVaccinationduration(getvaccinayionduration(rs.getString(28)));
				n.setGivendate(DateTimeUtils.getCommencingDate1(rs.getString(29)));
				n.setSmsflag(rs.getInt(30));
				n.setDuedate(DateTimeUtils.getCommencingDate1(rs.getString(31)));
				n.setConsumption_status(rs.getInt(32));
				n.setVaccinationid(rs.getInt(33));
				list.add(n);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<NotAvailableSlot> vaccinationListOfPatients(String fromDate, String toDate, String type,
			String masterid, String clientId, String status, String serachText) {
		ArrayList<NotAvailableSlot> list = new ArrayList<NotAvailableSlot>();
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append(
					"  SELECT apm_vacination_data.id,abrivationid,concat(title,' ',firstname,' ',surname),clientid,mastername,duration,givendate,smsflag,duedate,consumption_status,charge_created_id,fullname,duedate_flag,followup_id,consumeid from  apm_vacination_data  ");
			buffer.append("  INNER JOIN apm_patient ON apm_vacination_data.clientid=apm_patient.id  ");
			buffer.append(
					"  INNER JOIN apm_vacination_master ON apm_vacination_data.masterid=apm_vacination_master.id  ");

			if (DateTimeUtils.isNull(serachText).equals("")) {
				buffer.append("where duedate between '" + fromDate + "' and '" + toDate + "'  ");
			} else {
				buffer.append("where apm_patient.abrivationid='" + serachText + "' or apm_patient.fullname like('%"
						+ serachText + "%') ");
				buffer.append("or apm_patient.firstname like('%" + serachText + "%') or apm_patient.surname like('%"
						+ serachText + "%') ");
				buffer.append("or apm_patient.mobno like('%" + serachText + "%') ");
			}

			if (!DateTimeUtils.isNull(type).equals("")) {
				buffer.append(" and type='" + type + "'  ");
			} else {
				buffer.append(" and (type is null or type in('','0'))  ");
			}
			if (!DateTimeUtils.isNull(masterid).equals("")) {
				buffer.append(" and  masterid='" + masterid + "' ");
			}
			if (!DateTimeUtils.isNull(clientId).equals("")) {
				buffer.append("  and apm_vacination_data.clientid='" + clientId + "' ");
			}
			if (DateTimeUtils.isNull(status).equals("1")) {
				buffer.append(
						"  and (apm_vacination_data.givendate is not null and apm_vacination_data.givendate !='') ");
			} else if (DateTimeUtils.isNull(status).equals("2")) {
				buffer.append("  and (apm_vacination_data.givendate is  null or apm_vacination_data.givendate='')  ");
			}

			buffer.append("  order by duedate asc ");

			InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
			PreparedStatement ps = connection.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();
			ClientDAO clientDAO = new JDBCClientDAO(connection);

			String todaysDateString = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			while (rs.next()) {
				NotAvailableSlot n = new NotAvailableSlot();
				n.setVaccinationid(rs.getInt(1));
				n.setAbrivationid(rs.getString(2));
				n.setClientName(rs.getString(3));
				n.setClientId(rs.getString(4));
				Client client = clientDAO.getClientDetails(n.getClientId());
				String agegender = client.getAgegender();
				String age = DateTimeUtils.getAge1(client.getDob());
				n.setAgegender(age + " / " + client.getGender());
				n.setUhid(client.getAbrivationid());
				n.setMastername(rs.getString(5));
				n.setVaccinationduration(rs.getString(6));
				n.setGivendate(DateTimeUtils.getCommencingDate1(rs.getString(7)));
				n.setSmsflag(rs.getInt(8));
				n.setDuedate(DateTimeUtils.getCommencingDate1(rs.getString(9)));
				n.setConsumption_status(rs.getInt(10));
				n.setChargeId(rs.getString(11));
                n.setFullname(rs.getString(12));
                n.setDatecolor(rs.getString(13));
                n.setFollowupId(rs.getString(14));
                n.setConsumeid(rs.getString(15));
				int chargeid = DateTimeUtils.convertToInteger(n.getChargeId());
				int invoiceid = 0;
				boolean paymentid = false;
				// charge status
				if (chargeid > 0) {
					n.setStatus("Charge Created");
					invoiceid = investigationDAO.getchargeInvoiceidFromAssesment(chargeid);
					if (invoiceid > 0) {
						n.setStatus("Invoice Created");
						paymentid = investigationDAO.isInvChargePaid(invoiceid);
						if (paymentid) {
							n.setStatus("Paid");
						}
					}

				}
				n.setInvoiceid("" + invoiceid);
				n.setPayBy(client.getWhopay());

				boolean smsflag = checkVaccineSmsSent(todaysDateString, "" + n.getVaccinationid());
				if (smsflag) {
					n.setSmsflag(1);
				} else {
					n.setSmsflag(0);
				}

				list.add(n);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private String getvaccinayionduration(String string) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select duration from apm_vacination_master where name='" + string + "'";

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

	public ArrayList<DiaryManagement> getUserListofvaccination(int id) {
		PreparedStatement preparedStatement = null;
		ArrayList<DiaryManagement> list = new ArrayList<DiaryManagement>();
		String sql = "select id,initial,firstname,lastname from apm_user where  vaccinator=1 and usertype=4 and islogin=1  and (jobtitle='Practitioner' or hasdiary=1)  order by firstname asc";

		try {

			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				DiaryManagement diaryManagement = new DiaryManagement();
				diaryManagement.setId(rs.getInt(1));
				if (rs.getString(2) == null || rs.getString(2).equals("")) {
					diaryManagement.setDiaryUser(rs.getString(3) + " " + rs.getString(4));

				} else {
					diaryManagement.setDiaryUser(rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));

				}
				list.add(diaryManagement);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public String getVaccinationLocation() {
		String location = "0";
		try {
			String sql = "select vacination_location from apm_user where id=1";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				location = "" + rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return location;
	}

	public NotAvailableSlot getVaccinationdataFromId(String vaccinationid) {
		NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
		try {
			PreparedStatement ps = null;
			StringBuffer buffer = new StringBuffer();
			buffer.append(
					"select mastername,clientid,concat(title,' ',fullname),vac_productid from apm_vacination_data ");
			buffer.append("inner join apm_patient on apm_patient.id = apm_vacination_data.clientid ");
			buffer.append("where apm_vacination_data.id=" + vaccinationid + "");
			ps = connection.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				notAvailableSlot.setMastername(rs.getString(1));
				notAvailableSlot.setClientId(rs.getString(2));
				notAvailableSlot.setClientName(rs.getString(3));
				notAvailableSlot.setVac_productid(rs.getString(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return notAvailableSlot;
	}

	public int updateConsumeStatus(String vaccinationid, String string) {
		int res = 0;
		try {
			String sql = "update apm_vacination_data set consumption_status=? where id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, string);
			preparedStatement.setString(2, vaccinationid);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int updateVacinationConsumptiondata(String vaccinationid, int result, int prodid, String qty) {
		int res = 0;
		try {
			String sql = "update apm_vacination_data set vac_productid=?,consumeid=? where id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "" + prodid);
			preparedStatement.setString(2, "" + result);
			preparedStatement.setString(3, vaccinationid);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public void saveToVaccineSMSLog(String vaccineId) {
		try {

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String dateString = simpleDateFormat.format(new Date());
			PreparedStatement ps = connection
					.prepareStatement(" insert into vaccine_manual_sms_log (date,vaccineid) values('" + dateString
							+ "','" + vaccineId + "')");
			int c = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean checkVaccineSmsSent(String date, String vaccineid) {
		boolean flag = false;
		try {
			PreparedStatement ps = connection.prepareStatement("select * from vaccine_manual_sms_log where date='"
					+ date + "' and vaccineid='" + vaccineid + "'  ");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public boolean sendVaccineSMSManual(String vaccineId, LoginInfo loginInfo) {
		boolean isSent = false;
		try {
			SmsService s = new SmsService();
			StringBuffer buffer = new StringBuffer();
			buffer.append(
					" select a.id,a.masterid,a.mastername,concat(b.title,' ',b.firstname,' ',b.surname),b.mobno,a.clientid,a.duedate  from apm_vacination_data a ");
			buffer.append("  inner join apm_patient b on b.id= a.clientid  where a.id='" + vaccineId + "' ");
			PreparedStatement ps = connection.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();
			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setMasterid(rs.getInt(2));
				master.setVacinname(rs.getString(3));
				master.setClientname(rs.getString(4));
				master.setMobileNo(rs.getString(5));
				master.setClientid(rs.getString(6));
				Connection con = connection;
				UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
				String templateid = userProfileDAO.getSMSTemplateID("Vaccination");
				// String msg =loginInfo.getClinicName() +" : "+master.getClientname() +"
				// à¤†à¤ªà¤•à¤¾ "+master.getVacinname()+" à¤•à¤¾ à¤Ÿà¥€à¤•à¤¾à¤•à¤°à¤£
				// "+DateTimeUtils.getCommencingDate1(rs.getString(7))+" à¤ªà¤°
				// à¤¨à¤¿à¤°à¥�à¤§à¤¾à¤°à¤¿à¤¤ à¤¹à¥ˆ| Phone: "+clinic.getLandLine()+" ";
				String msg = loginInfo.getClinicName() + " : Dear Parents\n" + master.getClientname() + " à¤†à¤ªà¤•à¤¾ "
						+ master.getVacinname() + " à¤•à¤¾ à¤Ÿà¥€à¤•à¤¾à¤•à¤°à¤£ "
						+ DateTimeUtils.getCommencingDate1(rs.getString(7))
						+ " à¤ªà¤° à¤¨à¤¿à¤°à¥�à¤§à¤¾à¤°à¤¿à¤¤ à¤¹à¥ˆ| Phone: " + clinic.getLandLine() + " ";
				isSent = s.sendvaccineSms(msg, master.getMobileNo(), loginInfo, new EmailLetterLog(), con, templateid);
				if (isSent) {
					saveToVaccineSMSLog(vaccineId);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return isSent;
	}

	public String generateOPDSequenceNewFormat(int docid) {
		String opdabrfinal = "";
		// if action =1 then casualty else ipd
		DateFormat dateFormat = new SimpleDateFormat("yyyy");
		Calendar cal = Calendar.getInstance();
		String year = dateFormat.format(cal.getTime());

		String yearRange = DateTimeUtils.getFinancialYearRange();
		int maxopdabr = getMaxOPDAbrivation(yearRange,docid);
		int yr = Integer.parseInt(year) % 1000;

		int newmaxopdabr = maxopdabr + 1;
		String temp = String.valueOf(newmaxopdabr);
		int l = temp.length();
		String opdabrstr = "";
		if (l == 3) {
			opdabrstr = "0" + newmaxopdabr;
		} else if (l == 2) {
			opdabrstr = "00" + newmaxopdabr;
		} else if (l == 1) {
			opdabrstr = "000" + newmaxopdabr;
		} else {
			opdabrstr = "" + newmaxopdabr;
		}
		String prefix = "OP";
		//
		/*
		 * String abrivaationcode = getclinicAbrivationCode(); ipdabrfinal =
		 * abrivaationcode +prefix+ "/" + yearRange + "/" + ipdabrstr;
		 */

		// akash 10-08-2020 as per dipanjay sir new format
		String[] data = yearRange.split("-");
		String[] data1 = data[0].split("");
		String[] data2 = data[1].split("");
		String value = data1[2] + "" + data1[3] + "-" + data2[2] + "" + data2[3];
		opdabrfinal = prefix + "" + value + "/" + opdabrstr;

		int res = insertOPDAbrivation(yearRange, newmaxopdabr,docid);
		return opdabrfinal;
	}

	private int insertOPDAbrivation(String yearRange, int newmaxopdabr,int docid) {
		int res = 0;
		PreparedStatement ps = null;
		AccountsDAO accountsDAO=new JDBCAccountsDAO(connection);
		int physioOpdorNot=accountsDAO.patientphysioIpdOrNot(""+docid);
		String tablename="";
		if(physioOpdorNot==1) {
			tablename="physio_opd_sequence_numbers";
		}else {
			tablename="opd_sequence_numbers";
		}

		try {
			ps = connection.prepareStatement(" insert into "+tablename+"(date, seqno) values(?,?)");
			ps.setString(1, yearRange);
			ps.setInt(2, newmaxopdabr);
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	private int getMaxOPDAbrivation(String yearRange,int docid) {
		int maxid = 0;
		AccountsDAO accountsDAO=new JDBCAccountsDAO(connection);
		int physioOpdorNot=accountsDAO.patientphysioIpdOrNot(""+docid);
		String tablename="";
		if(physioOpdorNot==1) {
			tablename="physio_opd_sequence_numbers";
		}else {
			tablename="opd_sequence_numbers";
		}

		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement(" select max(seqno) from "+tablename+" where date='" + yearRange + "' ");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				maxid = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return maxid;
	}

	public void addApmtChangeLog(NotAvailableSlot notAvailableSlot) {
		try {
			PreparedStatement ps = connection.prepareStatement(
					" insert into redscheduled_apmts_log (prev_date,new_date,apmtId,date_time) values(?,?,?,?) ");
			ps.setString(1, notAvailableSlot.getCommencing());
			ps.setString(2, notAvailableSlot.getCurrentDate());
			ps.setInt(3, notAvailableSlot.getId());
			ps.setString(4, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			int x = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String lmpDateFromVital(String clientId) {
		String date = "";
		try {
			PreparedStatement ps = connection
					.prepareStatement(" select lmpd from his_bmi where clientid='" + clientId + "'");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				date = DateTimeUtils.isNull(rs.getString(1));
				if (date.contains("/")) {
					date = date.replaceAll("/", "-");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	public String hindiBdaySMSText(String clientId, LoginInfo loginInfo) {
		String bdayText = "";
		try {
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			Client client = clientDAO.getClientDetails(clientId);
			String hospitalname = "à¤¬à¤¾à¤² à¤—à¥‹à¤ªà¤¾à¤² à¤¹à¥‰à¤¸à¥�à¤ªà¤¿à¤Ÿà¤²";
			/*
			 * if(loginInfo.getClinicName().equals("raibhattar")){
			 * hospitalname="à¤­à¤Ÿà¥�à¤Ÿà¤° à¤•à¥�à¤²à¤¿à¤¨à¤¿à¤•"; }else
			 * if(loginInfo.getClinicName().equals("raiprachi")){
			 * hospitalname="à¤ªà¥�à¤°à¤¾à¤šà¥€ à¤•à¥�à¤²à¤¿à¤¨à¤¿à¤•"; }
			 */

			if (DateTimeUtils.isNull(loginInfo.getClinicUserid()).equals("raibhattar")) {
				hospitalname = "à¤­à¤Ÿà¥�à¤Ÿà¤° à¤•à¥�à¤²à¤¿à¤¨à¤¿à¤•";
			} else if (DateTimeUtils.isNull(loginInfo.getClinicUserid()).equals("raiprachi")) {
				hospitalname = "à¤ªà¥�à¤°à¤¾à¤šà¥€ à¤•à¥�à¤²à¤¿à¤¨à¤¿à¤•";
			}

			String ptname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName() + "";

			bdayText = ptname+ " %E0%A4%95%E0%A5%8B%20%E0%A4%AC%E0%A4%BE%E0%A4%B2%20%E0%A4%97%E0%A5%8B%E0%A4%AA%E0%A4%BE%E0%A4%B2%20%E0%A4%B9%E0%A5%89%E0%A4%B8%E0%A5%8D%E0%A4%AA%E0%A4%BF%E0%A4%9F%E0%A4%B2%20%E0%A4%AA%E0%A4%B0%E0%A4%BF%E0%A4%B5%E0%A4%BE%E0%A4%B0%20%E0%A4%95%E0%A5%80%20%E0%A4%93%E0%A4%B0%20%E0%A4%B8%E0%A5%87%20%E0%A4%9C%E0%A4%A8%E0%A5%8D%E0%A4%AE%E0%A4%A6%E0%A4%BF%E0%A4%A8%20%E0%A4%95%E0%A5%80%20%E0%A4%B9%E0%A4%BE%E0%A4%B0%E0%A5%8D%E0%A4%A6%E0%A4%BF%E0%A4%95%20%E0%A4%B6%E0%A5%81%E0%A4%AD%E0%A4%95%E0%A4%BE%E0%A4%AE%E0%A4%A8%E0%A4%BE%E0%A4%8F%E0%A4%81.%20%22%E0%A4%B8%E0%A5%8D%E0%A4%B5%E0%A4%B8%E0%A5%8D%E0%A4%A5%20%E0%A4%B0%E0%A4%B9%E0%A5%87%E0%A4%82%2C%20%E0%A4%B8%E0%A5%81%E0%A4%B0%E0%A4%95%E0%A5%8D%E0%A4%B7%E0%A4%BF%E0%A4%A4%20%E0%A4%B0%E0%A4%B9%E0%A5%87%E0%A4%82%22\n0771-4225600, 7869920001\nwww.balgopalhospital.com";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bdayText;
	}

	@Override
	public int updatereferfrom(String appointment, String refferedfrom) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_available_slot set refferedfrom='" + refferedfrom + "' where id=" + appointment + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int saveDepartmentAppointment(NotAvailableSlot notAvailableSlot) {
		int result = 0;
		int id = 0;
		String tablename="";
        if(notAvailableSlot.getPhysio_ipd().equals("0")) {
        	tablename="department_opd";
        }else {
        	tablename="physio_ipd";
        }
		PreparedStatement pstm = null;
		String sql = "insert into "+tablename+" (apmslotid,commencing,starttime,endtime,notes,diaryuserid,"
				+ "diaryusername,dept,location,room,clientname,aptmtype,duration,clientId,charge,"
				+ "treatmentEpisodeId,added_by,apmttypetext,usedsession,condition_id,whopay,otid,category,"
				+ "procedures,surgeon,anesthesia,ipdno,wardid,anidoctorfees,psurcharge,panetcharge,sic,"
				+ "assistaffcharge,opdbooktime,reqdatetime,mobstatus,speciality,appt_type,opdabbrevationid,"
				+ "newpatient,preDate,planid,days,physio_ipd) "
				+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			pstm = connection.prepareStatement(sql);
			pstm.setInt(1, notAvailableSlot.getApmtSlotId());
			pstm.setString(2, notAvailableSlot.getCommencing());
			pstm.setString(3, notAvailableSlot.getSTime());
			pstm.setString(4, notAvailableSlot.getEndTime());
			pstm.setString(5, notAvailableSlot.getNotes());
			pstm.setInt(6, notAvailableSlot.getDiaryUserId());
			pstm.setString(7, notAvailableSlot.getDiaryUser());
			pstm.setString(8, notAvailableSlot.getDept());
			pstm.setString(9, notAvailableSlot.getLocation());
			pstm.setString(10, notAvailableSlot.getRoom());
			pstm.setString(11, notAvailableSlot.getClient());
			pstm.setString(12, notAvailableSlot.getApmtType());
			pstm.setString(13, notAvailableSlot.getApmtDuration());
			pstm.setString(14, notAvailableSlot.getClientId());
			if (notAvailableSlot.isVaccineApmt()) {

			}
			double charge = getCharge(notAvailableSlot.getApmtType());
			pstm.setDouble(15, charge);
			pstm.setString(16, notAvailableSlot.getTreatmentEpisodeId());
			pstm.setString(17, notAvailableSlot.getAddedBy());
			String apmtTYpeText = getAppointmentTypeText(notAvailableSlot.getApmtType());
			pstm.setString(18, apmtTYpeText);
			pstm.setString(19, notAvailableSlot.getUsedsession());
			pstm.setString(20, notAvailableSlot.getCondition());
			pstm.setString(21, notAvailableSlot.getPayBy());
			pstm.setInt(22, notAvailableSlot.getOtid());
			pstm.setString(23, notAvailableSlot.getOtplan());
			pstm.setString(24, DateTimeUtils.numberCheck(notAvailableSlot.getProcedure()));
			pstm.setString(25, DateTimeUtils.numberCheck(notAvailableSlot.getSurgeon()));
			pstm.setString(26, DateTimeUtils.numberCheck(notAvailableSlot.getAnesthesia()));
			pstm.setString(27, DateTimeUtils.numberCheck(notAvailableSlot.getIpdno()));
			pstm.setString(28, DateTimeUtils.numberCheck(notAvailableSlot.getWardid()));

			pstm.setString(29, DateTimeUtils.numberCheck(notAvailableSlot.getAnifees()));
			pstm.setString(30, DateTimeUtils.numberCheck(notAvailableSlot.getPsurcharge()));
			pstm.setString(31, DateTimeUtils.numberCheck(notAvailableSlot.getPanetcharge()));
			pstm.setString(32, DateTimeUtils.numberCheck(notAvailableSlot.getSic()));
			pstm.setString(33, DateTimeUtils.numberCheck(notAvailableSlot.getAssistaffcharge()));
			pstm.setString(34, notAvailableSlot.getOpdbooktime());
			if (notAvailableSlot.getRequestDateTime().equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();
				pstm.setString(35, dateFormat.format(cal.getTime()));
			} else {
				pstm.setString(35, notAvailableSlot.getRequestDateTime());
			}

			pstm.setString(36, notAvailableSlot.getMobstatus());
			pstm.setString(37, DateTimeUtils.numberCheck(notAvailableSlot.getSpeciality()));
			pstm.setInt(38, notAvailableSlot.getAppt_type());
			String newopdabbr = "";
			if (notAvailableSlot.getOtplan().equals("0") && !DateTimeUtils.isNull(apmtTYpeText).equals("")) {
				// newopdabbr=generateOPDSequenceNewFormat();
				newopdabbr = "";
			}
			pstm.setString(39, newopdabbr);
			pstm.setInt(40, notAvailableSlot.getNewpatient());
			pstm.setInt(41, notAvailableSlot.getPreDate());
			pstm.setInt(42, notAvailableSlot.getPlanid());
			pstm.setInt(43, notAvailableSlot.getDay());
			pstm.setString(44, notAvailableSlot.getPhysio_ipd());
			result = pstm.executeUpdate();

			if (result == 1) {
				ResultSet resultSet = pstm.getGeneratedKeys();
				if (resultSet.next()) {
					id = resultSet.getInt(1);
				}
			}
//		String sql1="update department_opd set parentid="+id+" where id="+id+" ";
//		PreparedStatement preparedStatement=connection.prepareStatement(sql1);
//		int ress=preparedStatement.executeUpdate();
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			UserProfile userProfile = userProfileDAO.getUserprofileDetails(notAvailableSlot.getDiaryUserId());

			if (userProfile.getJobgroup().equals("4")) {
				int update = updateMachineDoctorID(id, userProfile.getDoctor());
			}

			// int
			// dd=addOpdConditionReport(id,notAvailableSlot.getClientId(),notAvailableSlot.getCondition(),notAvailableSlot.getCommencing());

		} catch (Exception e) {

			e.printStackTrace();

		}

		return id;
	}

	@Override
	public NotAvailableSlot getDepartmentAvailableSlotdata(int appointmentid) {
		PreparedStatement preparedStatement = null;
		NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
		String tablename="";
		// String sql = "SELECT starttime,endtime FROM apm_available_slot where
		// id="+editAppointId+" ";
		String physio_ipd=getphysioipdvalue(appointmentid);
		
		 if(physio_ipd.equals("0")) {
	        	tablename="department_opd";
	        }else {
	        	tablename="physio_ipd";
	        }

		String sql = "SELECT starttime,endtime,commencing,diaryusername,clientname,clientid,diaryuserid,aptmtype,"
				+ "duration,apmttypetext,dna,opdpmnt,chrgstatus,procedures,reasonforblock,med_doc_verify,reception_vid_verify,"
				+ "pending_verify,pending_remark,doctor_vid_reject_remark,location,whopay,drcompleted,added_by,condition_id,"
				+ "referredfromdept,dept,reqdatetime,refferremark,refertodept,newpatient,preDate,id,physio_ipd "
				+ "FROM "+tablename+" where id = " + appointmentid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				notAvailableSlot.setsTime(rs.getString(1));
				notAvailableSlot.setEndTime(rs.getString(2));
				notAvailableSlot.setCommencing(rs.getString(3));
				notAvailableSlot.setDiaryUser(rs.getString(4));
				notAvailableSlot.setClientName(rs.getString(5));
				notAvailableSlot.setClientId(rs.getString(6));
				notAvailableSlot.setDiaryUserId(rs.getInt(7));
				notAvailableSlot.setAppointmentid(rs.getInt(8));
				notAvailableSlot.setDuration(rs.getString(9));
				notAvailableSlot.setApmttypetext(rs.getString(10));
				notAvailableSlot.setDna(rs.getBoolean(11));
				notAvailableSlot.setOpdpmnt(rs.getInt(12));
				notAvailableSlot.setChrgstatus(rs.getInt(13));
				notAvailableSlot.setId(appointmentid);
				if (DateTimeUtils.numberCheck(rs.getString(14)).equals("0")) {
					notAvailableSlot.setProcedure("0");
				} else {
					notAvailableSlot.setProcedure("1");
				}
				notAvailableSlot.setReasonforblock(rs.getString(15));
				notAvailableSlot.setMed_doc_verify(rs.getInt(16));
				notAvailableSlot.setReception_vid_verify(rs.getInt(17));
				notAvailableSlot.setPending_verify(rs.getInt(18));
				notAvailableSlot.setPending_remark(rs.getString(19));
				notAvailableSlot.setDoctor_vid_reject_remark(DateTimeUtils.isNull(rs.getString(20)));
				notAvailableSlot.setApmtType(rs.getString(8));
				notAvailableSlot.setLocation(rs.getString(21));
				notAvailableSlot.setPayBy(rs.getString(22));
				notAvailableSlot.setDrcompleted(rs.getInt(23));
				notAvailableSlot.setAddedBy(rs.getString(24));
				notAvailableSlot.setCondition(rs.getString(25));
				notAvailableSlot.setReferredfromdept(rs.getString(26));
				notAvailableSlot.setDept(rs.getString(27));
				notAvailableSlot.setRequestDateTime(rs.getString(28));
				notAvailableSlot.setRefferremark(DateTimeUtils.isNull(rs.getString(29)));
				notAvailableSlot.setRefertodept(rs.getString(30));
				notAvailableSlot.setNewpatient(rs.getInt(31));
				notAvailableSlot.setPreDate(rs.getInt(32));
				notAvailableSlot.setId(rs.getInt(33));
				notAvailableSlot.setPhysio_ipd(rs.getString(34));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return notAvailableSlot;
	}

	private String getphysioipdvalue(int appointmentid) {
		String physioipd = "0";
		try {
			String sql = "select physio_ipd from physio_ipd where id='" + appointmentid + "' ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				physioipd = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return physioipd;
		
	}

	@Override
	public int updateDeparttmentrefferdfrom(String newappointmentid, String dept) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update department_opd set referredfromdept='" + dept + "' where id=" + newappointmentid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int updatediaryuser(String aptid, String diaryuserid, String diaryusername) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update department_opd set diaryuserid='" + diaryuserid + "',diaryusername='" + diaryusername
				+ "' where id=" + aptid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int updateclientDepartment(String dept, String clientid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_patient set lmh_department='" + dept + "' where id=" + clientid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ArrayList<Master> getselecteddeptlist(String commencing, String clientid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Master> list = new ArrayList<Master>();
		String sql = "select dept from department_opd where commencing='" + commencing + "' and clientid=" + clientid
				+ "";
		ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
		try {

			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(DateTimeUtils.isNull(chargesReportDAO.getDepartmentName(rs.getInt(1))));
				list.add(master);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public int updaterefferremark(String appointment, String referremark) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update department_opd set refferremark='" + referremark + "' where id=" + appointment + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int settrefertodepartment(String aptid, String dept) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update department_opd set refertodept='" + dept + "' where id=" + aptid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int updaterefferremarkforAllDept(NotAvailableSlot notavailableslot, String referremark, String commencing) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into department_remark (clientid, commencing, dept, remark) values (?,?,?,?)";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, notavailableslot.getClientId());
			preparedStatement.setString(2, commencing);
			preparedStatement.setString(3, notavailableslot.getDept());
			preparedStatement.setString(4, referremark);
			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ArrayList<NotAvailableSlot> getRemarklist(String commencing, String clientid) {
		PreparedStatement preparedStatement = null;
		ArrayList<NotAvailableSlot> list = new ArrayList<NotAvailableSlot>();
		String sql = "select id,dept,remark from department_remark where commencing='" + commencing + "' and clientid="
				+ clientid + "";
		ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
		try {

			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
				notAvailableSlot.setId(rs.getInt(1));
				notAvailableSlot
						.setDepartmentname(DateTimeUtils.isNull(chargesReportDAO.getDepartmentName(rs.getInt(2))));
				notAvailableSlot.setRefferremark(rs.getString(3));
				list.add(notAvailableSlot);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public int updatePatientStatus(String appointment, int status) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update department_opd set newpatient='" + status + "' where id=" + appointment + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int patientNewOrOld(String clientid, String dept) {
		int res = 1;
		try {
			String sql = "select * from department_opd where clientId='" + clientid + "' and dept='" + dept
					+ "' limit 1";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				res = 2;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public ArrayList<NotAvailableSlot> vaccinationListOfPatient(String serachText, int consumption_status) {

		ArrayList<NotAvailableSlot> list = new ArrayList<NotAvailableSlot>();
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("SELECT apm_vacination_data.id,abrivationid,concat(title,' ',firstname,' ',surname),  ");
			buffer.append(
					"clientid,mastername,duration,givendate,smsflag,duedate,consumption_status,charge_created_id, ");
			buffer.append("vac_productid,consumeid from  apm_vacination_data ");
			buffer.append("  INNER JOIN apm_patient ON apm_vacination_data.clientid=apm_patient.id  ");
			buffer.append(
					"  INNER JOIN apm_vacination_master ON apm_vacination_data.masterid=apm_vacination_master.id  ");
			buffer.append(" where apm_patient.abrivationid='" + serachText + "' ");
			buffer.append(" and (type is null or type in('','0'))  ");
			buffer.append(" and consumption_status='" + consumption_status + "' ");
			buffer.append(" and apm_vacination_data.givendate is not null and apm_vacination_data.givendate !='' ");
			buffer.append("  order by duedate asc ");

			PreparedStatement ps = connection.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();
			ClientDAO clientDAO = new JDBCClientDAO(connection);

			while (rs.next()) {
				NotAvailableSlot n = new NotAvailableSlot();
				n.setVaccinationid(rs.getInt(1));
				n.setAbrivationid(rs.getString(2));
				n.setClientName(rs.getString(3));
				n.setClientId(rs.getString(4));
				Client client = clientDAO.getClientDetails(n.getClientId());
				String age = DateTimeUtils.getAge1(client.getDob());
				n.setAgegender(age + " / " + client.getGender());
				n.setUhid(client.getAbrivationid());
				n.setMastername(rs.getString(5));
				n.setVaccinationduration(rs.getString(6));
				n.setGivendate(DateTimeUtils.getCommencingDate1(rs.getString(7)));
				n.setSmsflag(rs.getInt(8));
				n.setDuedate(DateTimeUtils.getCommencingDate1(rs.getString(9)));
				n.setConsumption_status(rs.getInt(10));
				n.setChargeId(rs.getString(11));
				n.setVac_productid(rs.getString(12));
				n.setConsumeid(rs.getString(13));
				n.setPayBy(client.getWhopay());
				list.add(n);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	@Override
	public int getPatientFakeStatus(String clientid) {
		int res = 0;
		try {
			String sql = "select fake_status from apm_patient where id='" + clientid + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				res = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int updateDepartmentFakeStatus(int fakestatus, String appointment) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		try {
			String sql = "update department_opd set fakestatus='" + fakestatus + "' where id = " + appointment + "";
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String getConsumeIdFromVaccinId(String vaccinationid) {
		String ids = "0";
		try {
			String sql = "select consumeid from apm_vacination_data where id in (" + vaccinationid + ") ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				ids = ids + "," + rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ids;
	}

	@Override
	public Map<Integer, String> getVaccineProductList(String consumeid) {
		Map<Integer, String> productMap = new HashMap<Integer, String>();
		try {
			String sql = "select sum(qty),prodid from indent_patient_transfer_log where parentid in (" + consumeid
					+ ") group by prodid";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				productMap.put(rs.getInt(2), rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return productMap;
	}

	@Override
	public String getPractIdFromApmtId(String apmtId) {
		String practId = "0";
		try {
			String sql = "select diaryuserid from apm_available_slot where id='" + apmtId + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				practId = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return practId;
	}

	@Override
	public ArrayList<DiaryManagement> getadmittedbyUserList() {
		// TODO Auto-generated method stub

		PreparedStatement preparedStatement = null;
		ArrayList<DiaryManagement> list = new ArrayList<DiaryManagement>();
		String sql = "select id,initial,firstname,lastname,userid from apm_user where usertype=4 and jobtitle='Reception' order by firstname asc";

		try {

			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				DiaryManagement diaryManagement = new DiaryManagement();
				diaryManagement.setId(rs.getInt(1));
				diaryManagement.setDiaryUser(rs.getString(3) + " " + rs.getString(4));
				diaryManagement.setUserid(rs.getString(5));

				list.add(diaryManagement);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public ArrayList<DiaryManagement> getipdfakeDepartment() {

		PreparedStatement preparedStatement = null;
		ArrayList<DiaryManagement> list = new ArrayList<DiaryManagement>();
		StringBuffer sql = new StringBuffer();

		sql.append(
				"select count(apm_discipline.id),apm_discipline.id,apm_discipline.discipline from ipd_addmission_form ");
		sql.append("inner join apm_user on apm_user.id=ipd_addmission_form.practitionerid ");
		sql.append("inner join apm_discipline on apm_discipline.id=apm_user.discription group by ");
		sql.append("apm_user.discription ");

		// String sqls = pagination.getSQLQuery(sql.toString());
		try {

			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				DiaryManagement diaryManagement = new DiaryManagement();

				diaryManagement.setCount(rs.getString(1));
				diaryManagement.setId(rs.getInt(2));
				diaryManagement.setDisciplineid(rs.getString(3) + "(" + rs.getString(1) + ")");

				list.add(diaryManagement);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}

	@Override
	public ArrayList<Master> getVaccineListbyid(int id, Client client) {
		PreparedStatement ps = null;
		ArrayList<Master> list = new ArrayList<Master>();
		/*
		 * int s=getcountoftotalimmunization(); for(int i=0;i<s;i++){ list.add(null); }
		 */

		try {
			StringBuffer bf = new StringBuffer();
			bf.append(
					"select id,name,depends_on,scheduled_on,is_compulsary,excludes, parent,info,duration,dependancy,totaldays,genderspecified from apm_vacination_master");
			bf.append("  where id between 1 and 46 ");
			bf.append("order by (scheduled_on+0) ");
			// String sql="select id,name,depends_on,scheduled_on,is_compulsary,excludes,
			// parent,info,duration,dependancy,totaldays,genderspecified from
			// apm_vacination_master";
			ps = connection.prepareStatement(bf.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setVacinname(rs.getString(2));
				master.setVacine_dependson(rs.getString(3));
				master.setVacine_scheduledon(rs.getString(4));
				master.setVacine_iscompulsary(rs.getString(5));
				master.setVacine_excludes(rs.getString(6));
				master.setParentid(rs.getString(7));
				master.setVacine_info(rs.getString(8));
				master.setDuration(rs.getString(9));
				master.setDependson(rs.getInt(10));
				Master master2 = getremarkNdate(String.valueOf(client.getId()), String.valueOf(master.getId()));
				master.setVacine_remark(master2.getVacine_remark());
				master.setVacine_givendate(master2.getVacine_givendate());
				Master mstr = new Master();

				if (!DateTimeUtils.isNull(master.getVacine_dependson()).equals("")) {
					mstr = getremarkNdate(String.valueOf(client.getId()), master.getVacine_dependson());
				} else if (rs.getInt(10) > 0) {
					mstr = getremarkNdate(String.valueOf(client.getId()), "" + master.getDependson());
				}

				if (mstr != null) {
					master.setDependent_name(mstr.getVacinname());
				} else {
					master.setDependent_name("");
				}
				if (mstr != null && mstr.getVacine_givendate() != null) {
					if (!mstr.getVacine_givendate().equals("")) {
						master.setDependant_given(true);
					}
				}
				if (DateTimeUtils.isNull(master.getVacine_dependson()).equals("")) {
					master.setDependant_given(true);
				}
				master.setDependson(rs.getInt(10));
				master.setDeppendsonDays(rs.getInt(11));
				master.setGendervaccine(rs.getInt(12));
				client.setGender(DateTimeUtils.isNull(client.getGender()));
				if (master.getGendervaccine() > 0) {
					if (master.getGendervaccine() == 1 && client.getGender().equals("Female")) {
						master = null;
						continue;
					}
					if (master.getGendervaccine() == 2 && client.getGender().equals("Male")) {
						master = null;
						continue;
					}
				}
				/* list.set(master.getId()-1, master); */

				list.add(master);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int setdatatoVacinationInfo1(Master master, String clientid) {
		PreparedStatement ps = null;
		int res = 0;
		String x = DateTimeUtils.getCommencingDate1(master.getScheduledate());
		try {
			String sql = "insert into apm_vacination_data(masterid,mastername,clientid,duedate) values(?,?,?,?)";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, master.getId());
			ps.setString(2, master.getVacinname());
			ps.setString(3, clientid);
			ps.setString(4, DateTimeUtils.getCommencingDate1(master.getScheduledate()));
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int getClientCount(String clientid, int masterid) {
		PreparedStatement pst = null;
		int count = 0;
		try {
			String sql = "select count(clientid) from apm_vacination_data where masterid=" + masterid + " and clientid="
					+ clientid + "";
			pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public ArrayList<NotAvailableSlot> getDeptwisegenderList(String fromDate, String toDate, LoginInfo loginInfo) {
		PreparedStatement pst = null;
		fromDate = DateTimeUtils.getCommencingDate4(fromDate);
		toDate = DateTimeUtils.getCommencingDate4(toDate);
		ArrayList<NotAvailableSlot> list = new ArrayList<NotAvailableSlot>();
		try {
			String showopdlist="";
			if(loginInfo.isBams1()) {
				showopdlist="0";
			}else {
				showopdlist="1";
			}
			String sql = "SELECT id,discipline FROM apm_discipline where showdeptlist='"+showopdlist+"'";
			pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
				MasterDAO masterDAO=new JDBCMasterDAO(connection);
				notAvailableSlot.setId(rs.getInt(1));
				notAvailableSlot.setDisciplineName(rs.getString(2));
				int malecount = getMaleCount(0, rs.getInt(1), fromDate, toDate);
				int femalecount = getMaleCount(1, rs.getInt(1), fromDate, toDate);
				int totalcount = malecount + femalecount;
				int newpatient=masterDAO.getArvpatientcount(""+rs.getInt(1),fromDate,toDate,"1","");
			    int oldpatient=masterDAO.getArvpatientcount(""+rs.getInt(1),fromDate,toDate,"2","");
			    notAvailableSlot.setNewpatient(newpatient);
			    notAvailableSlot.setOldpatient(oldpatient);
				notAvailableSlot.setMalecount(malecount);
				notAvailableSlot.setFemalecount(femalecount);
				notAvailableSlot.setGendercount(totalcount);
				list.add(notAvailableSlot);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private int getMaleCount(int i, int deptid, String fromDate, String toDate) {
		int result = 0;
		StringBuffer buffer = new StringBuffer();
		try {
			if (i == 0) {
				buffer.append(
						"select count(gender) from apm_patient inner join apm_available_slot on apm_patient.id=apm_available_slot.clientId where gender='male' and dept='"
								+ deptid + "' and commencing between '" + fromDate + "' and  '" + toDate + "' ; ");
			} else {
				buffer.append(
						"select count(gender) from apm_patient inner join apm_available_slot on apm_patient.id=apm_available_slot.clientId where gender='female' and dept='"
								+ deptid + "' and commencing between '" + fromDate + "' and  '" + toDate + "' ; ");
			}
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Client getChargebyapmtid(String appointmentid) {
		Client client=new Client();
		StringBuffer buffer=new StringBuffer();
		PreparedStatement preparedStatement=null;
		try {
			buffer.append("select charge,apmttypetext,commencing,starttime from apm_available_slot where id='"+appointmentid+"'");
			preparedStatement=connection.prepareStatement(buffer.toString());
			ResultSet rs=preparedStatement.executeQuery();
			while(rs.next()) {
				client.setCharges(rs.getString(1));
				client.setApmttypetext(rs.getString(2));
				client.setCommencing(rs.getString(3));
				client.setStime(rs.getString(4));			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return client;
	}

	@Override
	public ArrayList<DiaryManagement> getOutsourcemasterlist() {
		PreparedStatement preparedStatement=null;
		ArrayList<DiaryManagement> list=new ArrayList<DiaryManagement>();
		try {
			String sql="select * from apm_outsource";
			preparedStatement=connection.prepareStatement(sql);
			ResultSet rs=preparedStatement.executeQuery();
			while(rs.next()) {
				DiaryManagement diaryManagement=new DiaryManagement();
				diaryManagement.setId(rs.getInt(1));
				diaryManagement.setOutsourcename(rs.getString(2));
				list.add(diaryManagement);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public String getdatetimebyid(String clientid, String appointmentid) {
		String result="";
		StringBuffer buffer=new StringBuffer();
		PreparedStatement preparedStatement=null;
		try {
			buffer.append("select reqdatetime from apm_available_slot where id='"+appointmentid+"' and clientId='"+clientid+"'");
			preparedStatement=connection.prepareStatement(buffer.toString());
			ResultSet rs=preparedStatement.executeQuery();
			while(rs.next()) {
				result=rs.getString(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ArrayList<DiaryManagement> getUserListwithdeptToken(int id, String string) {
		PreparedStatement preparedStatement = null;
		ArrayList<DiaryManagement> list = new ArrayList<DiaryManagement>();
		String sql = "select id,initial,firstname,lastname from apm_user where usertype=4 and islogin=1  and (jobtitle='Practitioner' or hasdiary=1) order by firstname asc";

		try {

			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				DiaryManagement diaryManagement = new DiaryManagement();
				diaryManagement.setId(rs.getInt(1));
				if (rs.getString(2) == null || rs.getString(2).equals("")) {
					diaryManagement.setDiaryUser(rs.getString(3) + " " + rs.getString(4));

				} else {
					diaryManagement.setDiaryUser(rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));

				}
				int count=getcount(rs.getInt(1));
				if(count>0) {
					list.add(diaryManagement);
				}
				//list.add(diaryManagement);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
		
	}

	private int getcount(int id) {
		int count=0;
		StringBuffer buffer=new StringBuffer();
		PreparedStatement preparedStatement=null;
		try {
			buffer.append("select count(*) from apm_available_slot where diaryuserid='"+id+"' ");
			preparedStatement=connection.prepareStatement(buffer.toString());
			ResultSet rs=preparedStatement.executeQuery();
			while(rs.next()) {
				count=rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
		
	}

	@Override
	public NotAvailableSlot getpatientdetails(String clientid) {
		PreparedStatement pst=null;
		NotAvailableSlot notAvailableSlot=new NotAvailableSlot();
		try {
			String sql="select fullname,mobno from apm_patient where id="+clientid+"";
			pst=connection.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				notAvailableSlot.setName(rs.getString(1));
				notAvailableSlot.setMobno(rs.getString(2));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return notAvailableSlot;
	}

	@Override
	public ArrayList<DiaryManagement> getManasClinicUserList(LoginInfo loginInfo) {
		PreparedStatement preparedStatement = null;
		ArrayList<DiaryManagement> list = new ArrayList<DiaryManagement>();
		String  clinistaff=getclinicstaff(loginInfo.getId());
		if(clinistaff.equals("0")) {
			clinistaff=Integer.toString(loginInfo.getId());
		}
		String sql = "select id,initial,firstname,lastname from apm_user where clinic_staff="+clinistaff+" and jobtitle='Practitioner' and usertype=4 and islogin=1 and jobtitle!='OT'  and hasdiary=1 order by firstname asc";
        
		try {

			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				DiaryManagement diaryManagement = new DiaryManagement();
				diaryManagement.setId(rs.getInt(1));
				if (rs.getString(2) == null || rs.getString(2).equals("")) {
					diaryManagement.setDiaryUser(rs.getString(3) + " " + rs.getString(4));

				} else {
					diaryManagement.setDiaryUser(rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));

				}
				list.add(diaryManagement);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public String getManasClinicName(String clinicstaff_id) {
		PreparedStatement pst = null;
		String clinic_name = "";
		try {
			String sql = "select refered_clinicname from apm_user where id=" + clinicstaff_id + " ";
			pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				clinic_name = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clinic_name;
		
	}

	@Override
	public ArrayList<NotAvailableSlot> getManasclinicAllPractitionerPrintData(String date, String endDate,
			String location, String diaryuser, String action, String openedb, String previewdiaryuser, String fromDate,
			String toDate, LoginInfo loginInfo, String clinicstaff_id) {
		PreparedStatement preparedStatement = null;
		ArrayList<NotAvailableSlot> list = new ArrayList<NotAvailableSlot>();
		if (location.equals("")) {
			location = "0";
		}
		StringBuffer buffer = new StringBuffer();
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		/*
		 * String sql =
		 * "SELECT apm_available_slot.id,apmslotid,commencing,starttime,endtime,notes,diaryuserid,diaryusername,dept,location,room,clientname,aptmtype,duration,apm_available_slot.status,treatmentEpisodeId,usedsession,clientid,apm_available_slot.whopay,reasonforblock,workcompleted,admissionid from apm_available_slot inner join apm_patient on  apm_patient.id = apm_available_slot.clientId  "
		 * ;
		 * 
		 * if(opendb.equals("staff")){ sql =
		 * "SELECT id,apmslotid,commencing,starttime,endtime,notes,diaryuserid,diaryusername,dept,location,room,clientname,aptmtype,duration,status,treatmentEpisodeId,usedsession,clientid,whopay,reasonforblock,workcompleted,admissionid from his_staff_slot  "
		 * ; }
		 * 
		 * if(action.equals("dashboard")){ if(location.equals("0")){
		 * 
		 * sql = sql + " where commencing = '"+date+"' "; sql = sql +
		 * " where commencing between '"+fromDate+"' and '"
		 * +toDate+"' and procedures='0' ";
		 * 
		 * }else{ sql = sql +
		 * " where commencing = '"+date+"' and location = "+location+"  "; sql = sql +
		 * " where commencing between '"+fromDate+"' and '"+toDate+"' and location = "
		 * +location+" and procedures='0' "; } if(diaryuserjd!=null){
		 * if(!diaryuserjd.equals("0")){ sql = sql +
		 * " and diaryuserid="+diaryuserjd+" "; } }
		 * 
		 * 
		 * 
		 * }
		 * 
		 * if(action.equals("week")){ sql = sql +
		 * " where commencing between '"+date+"' and  '"+enddate+"' and diaryuserid = "
		 * +diaryuser+" "; }
		 * 
		 * if(action.equals("day")){ if(location.equals("0")){ sql = sql +
		 * " where commencing = '"+date+"' and diaryuserid="+diaryuser+" "; }else{ sql =
		 * sql +
		 * " where commencing = '"+date+"' and diaryuserid="+diaryuser+" and location="
		 * +location+" "; }
		 * 
		 * } if(action.equals("ot")){ if(location.equals("0")){ sql = sql +
		 * " where commencing = '"+date+"' and diaryuserid="
		 * +diaryuser+" and procedures!='0' "; }else{ sql = sql +
		 * " where commencing = '"+date+"' and diaryuserid="+diaryuser+" and location="
		 * +location+" procedures!='0' "; }
		 * 
		 * } sql = sql + " order by diaryuserid  ";
		 */

		buffer.append("SELECT apm_available_slot.id,apmslotid,commencing,starttime,endtime,notes,diaryuserid,diaryusername,dept, ");
		buffer.append("location,room,clientname,aptmtype,duration,apm_available_slot.status,treatmentEpisodeId,usedsession,clientid,");
		buffer.append("apm_available_slot.whopay,reasonforblock,workcompleted,admissionid,opdabbrevationid from apm_available_slot inner join ");
		buffer.append("apm_patient on  apm_patient.id = apm_available_slot.clientId ");
        buffer.append(" where commencing between '" + fromDate + "' and '" + toDate + "' and procedures='0' and clinic_staff="+clinicstaff_id+" ");

			
			if (diaryuser != null) {
				if (!diaryuser.equals("0")) {
					buffer.append(" and diaryuserid=" + diaryuser + " ");
				}
			}

		
        buffer.append(" order by diaryuserid  ");

		// String sql = "SELECT
		// id,apmslotid,commencing,starttime,endtime,notes,diaryuserid,diaryusername,dept,location,room,clientname,aptmtype,duration
		// from apm_available_slot where commencing = '"+date+"' ";
		try {
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			//preparedStatement = connection.prepareStatement(sql);
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();
			int i = 0;
			if (action.equals("day")) {
				DiaryManagement diaryManagement = getApmtSlotData(date, diaryuser, location);
				NotAvailableSlot nn = getAvailableSlotData(date, diaryuser, location);
				if (!diaryManagement.getSTime().equals(nn.getSTime())) {
					String duration = DateTimeUtils.getDuration(diaryManagement.getSTime(), nn.getSTime());
					nn = new NotAvailableSlot();
					nn.setSTime(diaryManagement.getSTime());
					nn.setEndTime(diaryManagement.getSTime());
					nn.setApmtDuration(duration);
					nn.setActionType(action);
					nn.setStatus("2");
					nn.setApmtSlotStimeEmpty(true);
					list.add(nn);
				}
			}
			while (rs.next()) {
				NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
				notAvailableSlot.setId(rs.getInt(1));
				notAvailableSlot.setApmtSlotId(rs.getInt(2));
				notAvailableSlot.setCommencing(DateTimeUtils.getCommencingDate1(rs.getString(3)));
				notAvailableSlot.setSTime(rs.getString(4));
				notAvailableSlot.setEndTime(rs.getString(5));
				notAvailableSlot.setNotes(rs.getString(6));
				notAvailableSlot.setDiaryUserId(rs.getInt(7));
				notAvailableSlot.setDiaryUser(rs.getString(8));
				notAvailableSlot.setDept(rs.getString(9));
				if (!notAvailableSlot.getDept().equals("")) {
					String deptname = getdeptName(rs.getString(9));
					notAvailableSlot.setDepartmentname(deptname);
				}

				location = getLocationName(rs.getString(10));
				notAvailableSlot.setLocation(location);
				notAvailableSlot.setRoom(rs.getString(11));
				notAvailableSlot.setClient(rs.getString(12));
				// notAvailableSlot.setUhid(rs.getString(22));

				Client client = clientDAO.getClientDetails(rs.getString(18));
				notAvailableSlot.setOldclientid(client.getOldclientId());
				notAvailableSlot.setAddress(client.getAddress());
				notAvailableSlot.setAbrivationid(client.getAbrivationid());
				notAvailableSlot.setClientId(rs.getString(18));
				try {
					// String age= DateTimeUtils.getAge(client.getDob());
					/* notAvailableSlot.setAgegender(age+"/"+client.getGender()); */
					String agegender = "";
					// String dob = client.getDob();
					String age2 = DateTimeUtils.getAge(client.getDob());
					if (age2 == null) {
						if (age2.equals("")) {
							age2 = "0";
						}
					}
					if (Integer.parseInt(age2) < 2) {
						if (Integer.parseInt(age2) < 1) {
							String monthdays = DateTimeUtils.getMonthandDays(client.getDob());
							agegender = monthdays + "/" + client.getGender();
						} else {
							String monthdays = DateTimeUtils.getMonthandDays(client.getDob());
							agegender = age2 + " Years" + " " + monthdays + "/" + client.getGender();
						}
					} else {
						agegender = age2 + "Years /" + client.getGender();
					}
					notAvailableSlot.setAgegender(agegender);

				} catch (Exception e) {
					// TODO: handle exception
				}

				String apmtyType = getAppointmentTypeText(rs.getString(13));
				notAvailableSlot.setApmtType(apmtyType);
				notAvailableSlot.setApmtDuration(rs.getString(14));
				notAvailableSlot.setActionType(action);
				notAvailableSlot.setStatus(rs.getString(15));

				CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
				CompleteAppointment completeAppointment = completeAptmDAO.getInsuranceCompanyName(rs.getString(18));
				String tpName = completeAppointment.getInsuranceCompanyName();
				notAvailableSlot.setTpName(tpName);

				notAvailableSlot.setWhopay(rs.getString(19));

				if (notAvailableSlot.getStatus().equals("1")) {
					notAvailableSlot.setApmtType(rs.getString(20));
				}
				notAvailableSlot.setWorkcompleted(rs.getString(21));
				notAvailableSlot.setAdmisionid(rs.getString(22));
				notAvailableSlot.setOpdAbbrId(rs.getString(23));
				String usdsession = rs.getString(17);

				if (rs.getInt(16) > 0) {
					TreatmentEpisodeDAO treatmentEpisodeDAO = new JDBCTreatmentEpisode(connection);
					TreatmentEpisode treatmentEpisode = treatmentEpisodeDAO
							.getTreatmentEpisodeSessionDetails(rs.getString(16));
					notAvailableSlot.setUsedsession(usdsession + "-" + treatmentEpisode.getSessions());

					notAvailableSlot.setApmtType("(" + notAvailableSlot.getUsedsession() + ")" + " " + apmtyType);
				}

				notAvailableSlot.setApmtSlotStimeEmpty(false);
				int invoiceid = accountsDAO.getInvoiceIdOfApmt("" + rs.getInt(1));
				//Accounts accounts = accountsDAO.getInvoiceChargesDetails("" + invoiceid);
				notAvailableSlot.setDiscount("" + accountsDAO.getDiscamtofInvoice("" + invoiceid));
				notAvailableSlot.setDebit("" + accountsDAO.getDebitfromInvoice("" + invoiceid));
				notAvailableSlot.setPayment("" + accountsDAO.getPaymentammount("" + invoiceid));
				String diagnosis = getOpdDiagnsis(client.getId(), notAvailableSlot.getDept());
				notAvailableSlot.setDiagnosis(diagnosis);
				notAvailableSlot.setDob(client.getDob());				
	            
				if(loginInfo.isBams1()) {
				String ipdabriid = getIpdabrivationid(rs.getString(18), rs.getString(9));
				notAvailableSlot.setIpdid(ipdabriid);
				StringBuffer prisc= getPriscriptionbyid(notAvailableSlot.getClientId(),rs.getString(3));
				notAvailableSlot.setPriscription(prisc);
				
				StringBuffer invst=getInvestigationbyid(notAvailableSlot.getClientId(),rs.getString(3));
				notAvailableSlot.setInvestigation(invst);
				
				String chiefcomplaint=getchiefcomplaintbyid(notAvailableSlot.getClientId(),rs.getString(3));
				notAvailableSlot.setChief_complains(chiefcomplaint);
				
				NotAvailableSlot punch=getPunchkarmadata(notAvailableSlot.getClientId(),rs.getString(3));
				notAvailableSlot.setReqnote(punch.getReqnote());
				String procedurename=getprocedurenamebyid(punch.getProcedureid());
				String punchnote=getPunchnotebyparentid(punch.getReqid());
				String punchkarma=procedurename+", "+punch.getReqnote()+", "+punchnote;
				notAvailableSlot.setPunchkarma(punchkarma);
				NotAvailableSlot karma=getKarmaandProcedure(notAvailableSlot.getClientId(),rs.getString(3)); 
				notAvailableSlot.setKarma(DateTimeUtils.isNull(karma.getKarma()));
				notAvailableSlot.setProcedurebams(DateTimeUtils.isNull(karma.getProcedurebams()));
				}
			    list.add(notAvailableSlot);
			    i++;
				 
			
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}

	@Override
	public int updateVaccineflag(int id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_patient set vacc_flag=1  where id=" + id + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String getDepatmentId(String id) {
		PreparedStatement pst = null;
		String deptname = "";
		try {
			String sql = "select dept from physio_ipd where id=" + id + "";
			pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				deptname =(rs.getString(1));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return deptname;
		
	}

	@Override
	public ArrayList<NotAvailableSlot> getPhysioIpdAllPractitionerPrintData(String date, String endDate,
			String location, String diaryuser, String action, String openedb, String previewdiaryuser, String fromDate,
			String toDate, LoginInfo loginInfo) {

		PreparedStatement preparedStatement = null;
		ArrayList<NotAvailableSlot> list = new ArrayList<NotAvailableSlot>();
		if (location.equals("")) {
			location = "0";
		}
		StringBuffer buffer = new StringBuffer();
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		/*
		 * String sql =
		 * "SELECT apm_available_slot.id,apmslotid,commencing,starttime,endtime,notes,diaryuserid,diaryusername,dept,location,room,clientname,aptmtype,duration,apm_available_slot.status,treatmentEpisodeId,usedsession,clientid,apm_available_slot.whopay,reasonforblock,workcompleted,admissionid from apm_available_slot inner join apm_patient on  apm_patient.id = apm_available_slot.clientId  "
		 * ;
		 * 
		 * if(opendb.equals("staff")){ sql =
		 * "SELECT id,apmslotid,commencing,starttime,endtime,notes,diaryuserid,diaryusername,dept,location,room,clientname,aptmtype,duration,status,treatmentEpisodeId,usedsession,clientid,whopay,reasonforblock,workcompleted,admissionid from his_staff_slot  "
		 * ; }
		 * 
		 * if(action.equals("dashboard")){ if(location.equals("0")){
		 * 
		 * sql = sql + " where commencing = '"+date+"' "; sql = sql +
		 * " where commencing between '"+fromDate+"' and '"
		 * +toDate+"' and procedures='0' ";
		 * 
		 * }else{ sql = sql +
		 * " where commencing = '"+date+"' and location = "+location+"  "; sql = sql +
		 * " where commencing between '"+fromDate+"' and '"+toDate+"' and location = "
		 * +location+" and procedures='0' "; } if(diaryuserjd!=null){
		 * if(!diaryuserjd.equals("0")){ sql = sql +
		 * " and diaryuserid="+diaryuserjd+" "; } }
		 * 
		 * 
		 * 
		 * }
		 * 
		 * if(action.equals("week")){ sql = sql +
		 * " where commencing between '"+date+"' and  '"+enddate+"' and diaryuserid = "
		 * +diaryuser+" "; }
		 * 
		 * if(action.equals("day")){ if(location.equals("0")){ sql = sql +
		 * " where commencing = '"+date+"' and diaryuserid="+diaryuser+" "; }else{ sql =
		 * sql +
		 * " where commencing = '"+date+"' and diaryuserid="+diaryuser+" and location="
		 * +location+" "; }
		 * 
		 * } if(action.equals("ot")){ if(location.equals("0")){ sql = sql +
		 * " where commencing = '"+date+"' and diaryuserid="
		 * +diaryuser+" and procedures!='0' "; }else{ sql = sql +
		 * " where commencing = '"+date+"' and diaryuserid="+diaryuser+" and location="
		 * +location+" procedures!='0' "; }
		 * 
		 * } sql = sql + " order by diaryuserid  ";
		 */

		/*
		 * buffer.
		 * append("select physio_ipd.id,commencing,dept,clientname,apmttypetext,gender,dob,address,abrivationid,mobno "
		 * ); buffer.
		 * append("from physio_ipd inner join apm_patient on  apm_patient.id = physio_ipd.clientId"
		 * );
		 */
		buffer.append("SELECT apm_available_slot.id,apmslotid,commencing,starttime,endtime,notes,diaryuserid,diaryusername,dept, ");
		buffer.append("location,room,clientname,aptmtype,duration,apm_available_slot.status,treatmentEpisodeId,usedsession,clientid,");
		buffer.append("apm_available_slot.whopay,reasonforblock,workcompleted,admissionid,opdabbrevationid from apm_available_slot inner join ");
		buffer.append("apm_patient on  apm_patient.id = apm_available_slot.clientId inner join apm_discipline on apm_discipline.id=apm_available_slot.dept");


		if (openedb.equals("staff")) {
			buffer.append("SELECT id,apmslotid,commencing,starttime,endtime,notes,diaryuserid,diaryusername,dept,location,room,clientname,aptmtype,duration,status,treatmentEpisodeId,usedsession,clientid,whopay,reasonforblock,workcompleted,admissionid from his_staff_slot  ");
		}

		if (action.equals("dashboard")) {
			if (location.equals("0")) {

				/* sql = sql + " where commencing = '"+date+"' "; */
				buffer.append(" where commencing between '" + fromDate + "' and '" + toDate + "' ");

			} else {
				/*
				 * sql = sql + " where commencing = '"+date+"' and location = "+location+"  ";
				 */
				buffer.append(" where commencing between '" + fromDate + "' and '" + toDate + "' and location = "
						+ location + " and procedures='0' ");
			}
			if (diaryuser != null) {
				if (!diaryuser.equals("0")) {
					buffer.append(" and diaryuserid=" + diaryuser + " ");
				}
			}

		}

		if (action.equals("week")) {
			buffer.append(" where commencing between '" + date + "' and  '" + endDate + "' and diaryuserid = " + diaryuser
					+ " ");
		}

		if (action.equals("day")) {
			if (location.equals("0")) {
				buffer.append(" where commencing = '" + date + "' and diaryuserid=" + diaryuser + " ");
			} else {
				buffer.append( " where commencing = '" + date + "' and diaryuserid=" + diaryuser + " and location="
						+ location + " ");
			}

		}
		if (action.equals("ot")) {
			if (location.equals("0")) {
				buffer.append( " where commencing = '" + date + "' and diaryuserid=" + diaryuser + " and procedures!='0' ");
			} else {
				buffer.append( " where commencing = '" + date + "' and diaryuserid=" + diaryuser + " and location="
						+ location + " procedures!='0' ");
			}

		}
		buffer.append("and showdeptlist=0 order by diaryuserid  ");

		// String sql = "SELECT
		// id,apmslotid,commencing,starttime,endtime,notes,diaryuserid,diaryusername,dept,location,room,clientname,aptmtype,duration
		// from apm_available_slot where commencing = '"+date+"' ";
		try {
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			//preparedStatement = connection.prepareStatement(sql);
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();
			int i = 0;
			if (action.equals("day")) {
				DiaryManagement diaryManagement = getApmtSlotData(date, diaryuser, location);
				NotAvailableSlot nn = getAvailableSlotData(date, diaryuser, location);
				if (!diaryManagement.getSTime().equals(nn.getSTime())) {
					String duration = DateTimeUtils.getDuration(diaryManagement.getSTime(), nn.getSTime());
					nn = new NotAvailableSlot();
					nn.setSTime(diaryManagement.getSTime());
					nn.setEndTime(diaryManagement.getSTime());
					nn.setApmtDuration(duration);
					nn.setActionType(action);
					nn.setStatus("2");
					nn.setApmtSlotStimeEmpty(true);
					list.add(nn);
				}
			}
			while (rs.next()) {
				NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
				notAvailableSlot.setId(rs.getInt(1));
				notAvailableSlot.setApmtSlotId(rs.getInt(2));
				notAvailableSlot.setCommencing(DateTimeUtils.getCommencingDate1(rs.getString(3)));
				notAvailableSlot.setSTime(rs.getString(4));
				notAvailableSlot.setEndTime(rs.getString(5));
				notAvailableSlot.setNotes(rs.getString(6));
				notAvailableSlot.setDiaryUserId(rs.getInt(7));
				notAvailableSlot.setDiaryUser(rs.getString(8));
				notAvailableSlot.setDept(rs.getString(9));
				if (!notAvailableSlot.getDept().equals("")) {
					String deptname = getdeptName(rs.getString(9));
					notAvailableSlot.setDepartmentname(deptname);
				}

				location = getLocationName(rs.getString(10));
				notAvailableSlot.setLocation(location);
				notAvailableSlot.setRoom(rs.getString(11));
				notAvailableSlot.setClient(rs.getString(12));
				// notAvailableSlot.setUhid(rs.getString(22));

				Client client = clientDAO.getClientDetails(rs.getString(18));
				notAvailableSlot.setOldclientid(client.getOldclientId());
				notAvailableSlot.setAddress(client.getAddress());
				notAvailableSlot.setAbrivationid(client.getAbrivationid());
				notAvailableSlot.setClientId(rs.getString(18));
				notAvailableSlot.setMobno(client.getMobNo());
				try {
					// String age= DateTimeUtils.getAge(client.getDob());
					/* notAvailableSlot.setAgegender(age+"/"+client.getGender()); */
					String agegender = "";
					// String dob = client.getDob();
					String age2 = DateTimeUtils.getAge(client.getDob());
					if (age2 == null) {
						if (age2.equals("")) {
							age2 = "0";
						}
					}
					if (Integer.parseInt(age2) < 2) {
						if (Integer.parseInt(age2) < 1) {
							String monthdays = DateTimeUtils.getMonthandDays(client.getDob());
							agegender = monthdays + "/" + client.getGender();
						} else {
							String monthdays = DateTimeUtils.getMonthandDays(client.getDob());
							agegender = age2 + " Years" + " " + monthdays + "/" + client.getGender();
						}
					} else {
						agegender = age2 + "Years /" + client.getGender();
					}
					notAvailableSlot.setAgegender(agegender);

				} catch (Exception e) {
					// TODO: handle exception
				}

				String apmtyType = getAppointmentTypeText(rs.getString(13));
				notAvailableSlot.setApmtType(apmtyType);
				notAvailableSlot.setApmtDuration(rs.getString(14));
				notAvailableSlot.setActionType(action);
				notAvailableSlot.setStatus(rs.getString(15));

				CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
				CompleteAppointment completeAppointment = completeAptmDAO.getInsuranceCompanyName(rs.getString(18));
				String tpName = completeAppointment.getInsuranceCompanyName();
				notAvailableSlot.setTpName(tpName);

				notAvailableSlot.setWhopay(rs.getString(19));

				if (notAvailableSlot.getStatus().equals("1")) {
					notAvailableSlot.setApmtType(rs.getString(20));
				}
				notAvailableSlot.setWorkcompleted(rs.getString(21));
				notAvailableSlot.setAdmisionid(rs.getString(22));
				notAvailableSlot.setOpdAbbrId(rs.getString(23));
				String usdsession = rs.getString(17);

				if (rs.getInt(16) > 0) {
					TreatmentEpisodeDAO treatmentEpisodeDAO = new JDBCTreatmentEpisode(connection);
					TreatmentEpisode treatmentEpisode = treatmentEpisodeDAO
							.getTreatmentEpisodeSessionDetails(rs.getString(16));
					notAvailableSlot.setUsedsession(usdsession + "-" + treatmentEpisode.getSessions());

					notAvailableSlot.setApmtType("(" + notAvailableSlot.getUsedsession() + ")" + " " + apmtyType);
				}

				notAvailableSlot.setApmtSlotStimeEmpty(false);
				int invoiceid = accountsDAO.getInvoiceIdOfApmt("" + rs.getInt(1));
				//Accounts accounts = accountsDAO.getInvoiceChargesDetails("" + invoiceid);
				notAvailableSlot.setDiscount("" + accountsDAO.getDiscamtofInvoice("" + invoiceid));
				notAvailableSlot.setDebit("" + accountsDAO.getDebitfromInvoice("" + invoiceid));
				notAvailableSlot.setPayment("" + accountsDAO.getPaymentammount("" + invoiceid));
				String diagnosis = getOpdDiagnsis(client.getId(), notAvailableSlot.getDept());
				notAvailableSlot.setDiagnosis(diagnosis);
				notAvailableSlot.setDob(client.getDob());				
	            
				if(loginInfo.isBams1()) {
				String ipdabriid = getIpdabrivationid(rs.getString(18), rs.getString(9));
				notAvailableSlot.setIpdid(ipdabriid);
				StringBuffer prisc= getPriscriptionbyid(notAvailableSlot.getClientId(),rs.getString(3));
				notAvailableSlot.setPriscription(prisc);
				
				StringBuffer invst=getInvestigationbyid(notAvailableSlot.getClientId(),rs.getString(3));
				notAvailableSlot.setInvestigation(invst);
				
				String chiefcomplaint=getchiefcomplaintbyid(notAvailableSlot.getClientId(),rs.getString(3));
				notAvailableSlot.setChief_complains(chiefcomplaint);
				
				NotAvailableSlot punch=getPunchkarmadata(notAvailableSlot.getClientId(),rs.getString(3));
				notAvailableSlot.setReqnote(punch.getReqnote());
				String procedurename=getprocedurenamebyid(punch.getProcedureid());
				String punchnote=getPunchnotebyparentid(punch.getReqid());
				String punchkarma=procedurename+", "+punch.getReqnote()+", "+punchnote;
				notAvailableSlot.setPunchkarma(punchkarma);
				NotAvailableSlot karma=getKarmaandProcedure(notAvailableSlot.getClientId(),rs.getString(3)); 
				notAvailableSlot.setKarma(DateTimeUtils.isNull(karma.getKarma()));
				notAvailableSlot.setProcedurebams(DateTimeUtils.isNull(karma.getProcedurebams()));
				}
			    list.add(notAvailableSlot);
			    i++;
				 
			
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	
		
	}
	
	
	
	
	

@Override
	public ArrayList<Master> getVaccinationdataveterinary(Client client) {
		PreparedStatement ps = null;
		ArrayList<Master> list = new ArrayList<Master>();
		/*
		 * int s=getcountoftotalimmunization(); for(int i=0;i<s;i++){ list.add(null); }
		 */

		try {
			StringBuffer bf = new StringBuffer();
			bf.append(
					"select id,name,depends_on,scheduled_on,is_compulsary,excludes, parent,info,duration,dependancy,totaldays,genderspecified from veterinary_medicine_master");
			if (!(client.getVacine_type() == 0)) {
				bf.append("  where type ='" + client.getVacine_type() + "' ");
			} else {
				bf.append("  where type is null or type=0 ");
			}
			bf.append("order by (scheduled_on+0) ");
			// String sql="select id,name,depends_on,scheduled_on,is_compulsary,excludes,
			// parent,info,duration,dependancy,totaldays,genderspecified from
			// apm_vacination_master";
			ps = connection.prepareStatement(bf.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setVacinname(rs.getString(2));
				master.setVacine_dependson(rs.getString(3));
				master.setVacine_scheduledon(rs.getString(4));
				master.setVacine_iscompulsary(rs.getString(5));
				master.setVacine_excludes(rs.getString(6));
				master.setParentid(rs.getString(7));
				master.setVacine_info(rs.getString(8));
				master.setDuration(rs.getString(9));
				master.setDependson(rs.getInt(10));
				Master master2 = getremarkNdateVet(String.valueOf(client.getId()), String.valueOf(master.getId()));
				master.setVacine_remark(master2.getVacine_remark());
				master.setVacine_givendate(master2.getVacine_givendate());
				Master mstr = new Master();

				if (!DateTimeUtils.isNull(master.getVacine_dependson()).equals("")) {
					mstr = getremarkNdate(String.valueOf(client.getId()), master.getVacine_dependson());
				} else if (rs.getInt(10) > 0) {
					mstr = getremarkNdate(String.valueOf(client.getId()), "" + master.getDependson());
				}

				if (mstr != null) {
					master.setDependent_name(mstr.getVacinname());
				} else {
					master.setDependent_name("");
				}
				if (mstr != null && mstr.getVacine_givendate() != null) {
					if (!mstr.getVacine_givendate().equals("")) {
						master.setDependant_given(true);
					}
				}
				if (DateTimeUtils.isNull(master.getVacine_dependson()).equals("")) {
					master.setDependant_given(true);
				}
				master.setDependson(rs.getInt(10));
				master.setDeppendsonDays(rs.getInt(11));
				master.setGendervaccine(rs.getInt(12));
				client.setGender(DateTimeUtils.isNull(client.getGender()));
				if (master.getGendervaccine() > 0) {
					if (master.getGendervaccine() == 1 && client.getGender().equals("Female")) {
						master = null;
						continue;
					}
					if (master.getGendervaccine() == 2 && client.getGender().equals("Male")) {
						master = null;
						continue;
					}
				}
				/* list.set(master.getId()-1, master); */

				list.add(master);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private Master getremarkNdateVet(String clientid, String masterid) {
		Master master = new Master();
		PreparedStatement ps = null;
		try {
			String sql = "select remark , givendate, mastername from veterinary_medicin_data where clientid='" + clientid
					+ "' and masterid='" + masterid + "'";
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			master.setVacine_remark("");
			master.setVacine_givendate("");
			while (rs.next()) {
				master.setVacine_remark(DateTimeUtils.isNull(rs.getString(1)));
				if (DateTimeUtils.isNull(rs.getString(2)).equals("")) {
					master.setVacine_givendate("");
				} else {
					if (rs.getString(2).contains("-")) {
						master.setVacine_givendate(DateTimeUtils.getCommencingDate1(rs.getString(2)));
					} else {
						master.setVacine_givendate(rs.getString(2));
					}
				}
				master.setVacinname(DateTimeUtils.isNull(rs.getString(3)));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return master;
}

	@Override
	public int getvacinedependacyvaleveterinary(String vacine_dependson) {
		PreparedStatement ps = null;
		int res = 0;
		try {
			String sql = "select scheduled_on from veterinary_medicine_master where id=" + vacine_dependson + "";
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				res = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int checkvacinationimmunizationajaxDataVet(String masterid, String clientid) {
		int result = 0;
		PreparedStatement ps = null;
		try {
			String sql = "select id from veterinary_medicin_data where masterid='" + masterid + "' and clientid='"
					+ clientid + "'";
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public int setdatatoVacinationInfoVet(Master master, String clientid) {
		PreparedStatement ps = null;
		int res = 0;
		String x = DateTimeUtils.getCommencingDate1(master.getScheduledate());
		try {
			String sql = "insert into veterinary_medicin_data(masterid,mastername,clientid,duedate) values(?,?,?,?)";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, master.getId());
			ps.setString(2, master.getVacinname());
			ps.setString(3, clientid);
			ps.setString(4, DateTimeUtils.getCommencingDate1(master.getScheduledate()));
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public boolean checksmsflagvet(int masterid, String clientid) {
		PreparedStatement ps = null;
		boolean res = false;
		try {
			String sql = "select smsflag from  veterinary_medicin_data where masterid='" + masterid + "' and clientid='"
					+ clientid + "' ";
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getBoolean(1)) {
					res = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public String getGivenDateVet(int dependson, String clientid) {
		String res = "";
		try {
			PreparedStatement ps = connection
					.prepareStatement(" select givendate from veterinary_medicin_data where masterid='" + dependson
							+ "' and clientid='" + clientid + "' ");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				res = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int updatedueDateOfVaccineVet(String clientid, String masterid, String dependate) {
		int res = 0;
		try {
			PreparedStatement ps = connection.prepareStatement(" update veterinary_medicin_data set duedate='" + dependate
					+ "' where  masterid='" + masterid + "' and clientid='" + clientid + "'  ");
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public String getDueDateVet(String masterid, String clientid) {
		String date = "";
		try {
			PreparedStatement ps = connection
					.prepareStatement(" select  duedate from veterinary_medicin_data   where masterid='" + masterid
							+ "' and clientid='" + clientid + "'  ");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				date = DateTimeUtils.getCommencingDate1(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	@Override
	public ArrayList<NotAvailableSlot> vaccinationListOfPatientsvet(String fromDate, String toDate, String type,
			String masterid, String clientId, String status, String serachText) {
		ArrayList<NotAvailableSlot> list = new ArrayList<NotAvailableSlot>();
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append(
					"  SELECT veterinary_medicin_data.id,abrivationid,concat(title,' ',firstname,' ',surname),clientid,mastername,duration,givendate,smsflag,duedate,consumption_status,charge_created_id,fullname,duedate_flag,followup_id,consumeid from  veterinary_medicin_data  ");
			buffer.append("  INNER JOIN apm_patient ON veterinary_medicin_data.clientid=apm_patient.id  ");
			buffer.append(
					"  INNER JOIN apm_vacination_master ON veterinary_medicin_data.masterid=apm_vacination_master.id  ");

			if (DateTimeUtils.isNull(serachText).equals("")) {
				buffer.append("where duedate between '" + fromDate + "' and '" + toDate + "'  ");
			} else {
				buffer.append("where apm_patient.abrivationid='" + serachText + "' or apm_patient.fullname like('%"
						+ serachText + "%') ");
				buffer.append("or apm_patient.firstname like('%" + serachText + "%') or apm_patient.surname like('%"
						+ serachText + "%') ");
				buffer.append("or apm_patient.mobno like('%" + serachText + "%') ");
			}

			if (!DateTimeUtils.isNull(type).equals("")) {
				buffer.append(" and type='" + type + "'  ");
			} else {
				buffer.append(" and (type is null or type in('','0'))  ");
			}
			if (!DateTimeUtils.isNull(masterid).equals("")) {
				buffer.append(" and  masterid='" + masterid + "' ");
			}
			if (!DateTimeUtils.isNull(clientId).equals("")) {
				buffer.append("  and veterinary_medicin_data.clientid='" + clientId + "' ");
			}
			if (DateTimeUtils.isNull(status).equals("1")) {
				buffer.append(
						"  and (veterinary_medicin_data.givendate is not null and veterinary_medicin_data.givendate !='') ");
			} else if (DateTimeUtils.isNull(status).equals("2")) {
				buffer.append("  and (veterinary_medicin_data.givendate is  null or veterinary_medicin_data.givendate='')  ");
			}

			buffer.append("  order by duedate asc ");

			InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
			PreparedStatement ps = connection.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();
			ClientDAO clientDAO = new JDBCClientDAO(connection);

			String todaysDateString = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			while (rs.next()) {
				NotAvailableSlot n = new NotAvailableSlot();
				n.setVaccinationid(rs.getInt(1));
				n.setAbrivationid(rs.getString(2));
				n.setClientName(rs.getString(3));
				n.setClientId(rs.getString(4));
				Client client = clientDAO.getClientDetails(n.getClientId());
				String agegender = client.getAgegender();
				String age = DateTimeUtils.getAge1(client.getDob());
				n.setAgegender(age + " / " + client.getGender());
				n.setUhid(client.getAbrivationid());
				n.setMastername(rs.getString(5));
				n.setVaccinationduration(rs.getString(6));
				n.setGivendate(DateTimeUtils.getCommencingDate1(rs.getString(7)));
				n.setSmsflag(rs.getInt(8));
				n.setDuedate(DateTimeUtils.getCommencingDate1(rs.getString(9)));
				n.setConsumption_status(rs.getInt(10));
				n.setChargeId(rs.getString(11));
                n.setFullname(rs.getString(12));
                n.setDatecolor(rs.getString(13));
                n.setFollowupId(rs.getString(14));
                n.setConsumeid(rs.getString(15));
				int chargeid = DateTimeUtils.convertToInteger(n.getChargeId());
				int invoiceid = 0;
				boolean paymentid = false;
				// charge status
				if (chargeid > 0) {
					n.setStatus("Charge Created");
					invoiceid = investigationDAO.getchargeInvoiceidFromAssesment(chargeid);
					if (invoiceid > 0) {
						n.setStatus("Invoice Created");
						paymentid = investigationDAO.isInvChargePaid(invoiceid);
						if (paymentid) {
							n.setStatus("Paid");
						}
					}

				}
				n.setInvoiceid("" + invoiceid);
				n.setPayBy(client.getWhopay());

				boolean smsflag = checkVaccineSmsSent(todaysDateString, "" + n.getVaccinationid());
				if (smsflag) {
					n.setSmsflag(1);
				} else {
					n.setSmsflag(0);
				}

				list.add(n);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public ArrayList<Master> getAllClientdeworming(String date, LoginInfo loginInfo, boolean is1daysms) {
		ArrayList<Master> list = new ArrayList<Master>();
		PreparedStatement ps = null;
		try {
			SmsService s = new SmsService();
			StringBuffer buffer = new StringBuffer();
			buffer.append(
					" select a.id,a.masterid,a.mastername,concat(b.title,' ',b.firstname,' ',b.surname),b.mobno,a.clientid,fullname  from veterinary_medicin_data a ");
			buffer.append("  inner join apm_patient b on b.id= a.clientid  where a.duedate ='" + date
					+ "' and a.smsflag='0' and b.mobno is not null and b.mobno!=''   ");
			ps = connection.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();
			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			FinderDAO finderDAO=new  JDBCFinderDAO(connection);
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setMasterid(rs.getInt(2));
				master.setVacinname(rs.getString(3));
				master.setClientname(rs.getString(4));
				master.setMobileNo(rs.getString(5));
				master.setClientid(rs.getString(6));
				master.setFullname(rs.getString(7));
				if (DateTimeUtils.isNull(rs.getString(5)).equals("")) {
					continue;
				}
				boolean smssent = true;
				if (is1daysms) {
					smssent = checksmsflagdeworming(master.getMasterid(), rs.getString(6));
				} else {
					smssent = checksmsflag7daydeworming(master.getMasterid(), rs.getString(6));
				}

				String expired = getExpiredstatus(rs.getString(6));
				if (!smssent) {
					if (!expired.equals("11") && !expired.equals("7") && !expired.equals("8")) {
						UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
						Connection con = connection;
						String msg="";
						
						String  templateid = userProfileDAO.getSMSTemplateID("Deworming");
						
						// String msg =loginInfo.getClinicName() +" : "+master.getClientname() +"
						// à¤†à¤ªà¤•à¤¾ "+master.getVacinname()+" à¤•à¤¾ à¤Ÿà¥€à¤•à¤¾à¤•à¤°à¤£
						// "+DateTimeUtils.getCommencingDate1(date)+" à¤ªà¤° à¤¨à¤¿à¤°à¥�à¤§à¤¾à¤°à¤¿à¤¤
						// à¤¹à¥ˆ| Phone: "+clinic.getLandLine()+" ";
						/*
						 * String msg = loginInfo.getClinicName() + " : Dear Parents\n" +
						 * master.getClientname() + " à¤†à¤ªà¤•à¤¾ " + master.getVacinname() +
						 * " à¤•à¤¾ à¤Ÿà¥€à¤•à¤¾à¤•à¤°à¤£ " + DateTimeUtils.getCommencingDate1(date) +
						 * " à¤ªà¤° à¤¨à¤¿à¤°à¥�à¤§à¤¾à¤°à¤¿à¤¤ à¤¹à¥ˆ| Phone: " + clinic.getLandLine()
						 * + " ";
						 */
						loginInfo.setPatient_name(master.getFullname());  
						 if(loginInfo.isParihar()) {
								 
						 msg="PARIHAR HOSPITAL Dear Parents "+ master.getClientname() +" आपका "+ master.getVacinname() +" वैक्सीन का टीकाकरण दिनांक "+ DateTimeUtils.getCommencingDate1(date) +" पर निर्धारित है फोन 07741232577, 9753059148";
						 s.sendvaccineSms(msg, master.getMobileNo(), loginInfo, new EmailLetterLog(), con, templateid);
					    }else if(loginInfo.isSjivh()) {
					     msg="INDIAN VETERINARY HOSPITAL : Dear Pet Parent "+ master.getVacinname() +" आपके Pet "+master.getFullname()+" का Deworming "+ DateTimeUtils.getCommencingDate1(date) +" पर निर्धारित हैI 0771-3586865, 6232765217 www.ivhraipur.com";	
					     int duedate_flag=finderDAO.getDuedate_flagbyclientid(master.getClientid(),"1",master.getVacinname());
					     if(duedate_flag==1) {
					     s.sendvaccineSms(msg, master.getMobileNo(), loginInfo, new EmailLetterLog(), con, templateid);
					     }
					    
					    }else {
						 msg = loginInfo.getClinicName() + " : Dear Parents\n" + master.getClientname()
						+ " %20%E0%A4%86%E0%A4%AA%E0%A4%95%E0%A4%BE%20 " + master.getVacinname() + " %20%E0%A4%95%E0%A4%BE%20%E0%A4%9F%E0%A5%80%E0%A4%95%E0%A4%BE%E0%A4%95%E0%A4%B0%E0%A4%A3%20 "
						+ DateTimeUtils.getCommencingDate1(date)
						+ " %E0%A4%AA%E0%A4%B0%20%E0%A4%A8%E0%A4%BF%E0%A4%B0%E0%A5%8D%E0%A4%A7%E0%A4%BE%E0%A4%B0%E0%A4%BF%E0%A4%A4%20%E0%A4%B9%E0%A5%88| Phone: " + clinic.getLandLine() + " ";
						
						s.sendvaccineSms(msg, master.getMobileNo(), loginInfo, new EmailLetterLog(), con, templateid);
					    }
						
					
						if (is1daysms) {
							int w = setsmsflagdeworming(master.getMasterid(), rs.getString(6));
						} else {
							int w = setsmsflag7Daydeworming(master.getMasterid(), rs.getString(6));
						}
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}

	private int setsmsflag7Daydeworming(int masterid, String clientid) {
		PreparedStatement ps = null;
		int res = 0;
		String sql = " update veterinary_medicin_data set smsflag7days=1 where masterid='" + masterid + "' and clientid='"
				+ clientid + "' ";
		try {
			ps = connection.prepareStatement(sql);
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	private int setsmsflagdeworming(int masterid, String clientid) {
		PreparedStatement ps = null;
		int res = 0;
		String sql = " update veterinary_medicin_data set smsflag=1 where masterid='" + masterid + "' and clientid='"
				+ clientid + "' ";
		try {
			ps = connection.prepareStatement(sql);
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	private boolean checksmsflag7daydeworming(int masterid, String clientid) {
		PreparedStatement ps = null;
		boolean res = false;
		try {
			String sql = "select smsflag7days from  veterinary_medicin_data where masterid='" + masterid
					+ "' and clientid='" + clientid + "' ";
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getBoolean(1)) {
					res = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	private boolean checksmsflagdeworming(int masterid, String clientid) {
		PreparedStatement ps = null;
		boolean res = false;
		try {
			String sql = "select smsflag from  veterinary_medicin_data where masterid='" + masterid + "' and clientid='"
					+ clientid + "' ";
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getBoolean(1)) {
					res = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int savevacinationimmunizationajaxVet(String mastername, String masterid, String clientid, String givendate,
			String duedate) {
		PreparedStatement ps = null;
		int result = 0;
		givendate = DateTimeUtils.getCommencingDate1(givendate);
		duedate = DateTimeUtils.getCommencingDate1(duedate);
		try {
			String sql = "insert into veterinary_medicin_data(masterid,mastername,clientid,givendate,duedate) values(?,?,?,?,?)";
			ps = connection.prepareStatement(sql);
			ps.setString(1, masterid);
			ps.setString(2, mastername);
			ps.setString(3, clientid);
			ps.setString(4, givendate);
			ps.setString(5, duedate);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int updatevacinationimmunizationajaxdateVet(String id, String givendate, String duedate) {
		PreparedStatement ps = null;
		int result = 0;
		givendate = DateTimeUtils.getCommencingDate1(givendate);
		duedate = DateTimeUtils.getCommencingDate1(duedate);
		try {
			String sql = "update veterinary_medicin_data set givendate =? , duedate=? where id=" + id + "";
			ps = connection.prepareStatement(sql);
			ps.setString(1, givendate);
			ps.setString(2, duedate);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public ArrayList<NotAvailableSlot> vaccinationListOfPatientVeterinary(String serachText, int consumption_status) {

		ArrayList<NotAvailableSlot> list = new ArrayList<NotAvailableSlot>();
		try {
			
			StringBuffer buffer = new StringBuffer();
			buffer.append("SELECT veterinary_medicin_data.id,abrivationid,concat(title,' ',firstname,' ',surname),  ");
			buffer.append(
					"clientid,mastername,duration,givendate,smsflag,duedate,consumption_status,charge_created_id, ");
			buffer.append("vac_productid,consumeid from  veterinary_medicin_data ");
			buffer.append("  INNER JOIN apm_patient ON veterinary_medicin_data.clientid=apm_patient.id  ");
			buffer.append(
					"  INNER JOIN veterinary_medicine_master ON veterinary_medicin_data.masterid=veterinary_medicine_master.id  ");
			buffer.append(" where apm_patient.abrivationid='" + serachText + "' ");
			buffer.append(" and (type is null or type in('','0'))  ");
			buffer.append(" and consumption_status='" + consumption_status + "' ");
			buffer.append(" and veterinary_medicin_data.givendate is not null and veterinary_medicin_data.givendate !='' ");
			buffer.append("  order by duedate asc ");
			
			PreparedStatement ps = connection.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			while (rs.next()) {
				NotAvailableSlot n = new NotAvailableSlot();
				n.setVaccinationid(rs.getInt(1));
				n.setAbrivationid(rs.getString(2));
				n.setClientName(rs.getString(3));
				n.setClientId(rs.getString(4));
				Client client = clientDAO.getClientDetails(n.getClientId());
				String age = DateTimeUtils.getAge1(client.getDob());
				n.setAgegender(age + " / " + client.getGender());
				n.setUhid(client.getAbrivationid());
				n.setMastername(rs.getString(5));
				n.setVaccinationduration(rs.getString(6));
				n.setGivendate(DateTimeUtils.getCommencingDate1(rs.getString(7)));
				n.setSmsflag(rs.getInt(8));
				n.setDuedate(DateTimeUtils.getCommencingDate1(rs.getString(9)));
				n.setConsumption_status(rs.getInt(10));
				n.setChargeId(rs.getString(11));
				n.setVac_productid(rs.getString(12));
				n.setConsumeid(rs.getString(13));
				n.setPayBy(client.getWhopay());
				list.add(n);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}
	
	public int updateConsumeStatusVet(String vaccinationid, String string) {
		int res = 0;
		try {
			String sql = "update veterinary_medicin_data set consumption_status=? where id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, string);
			preparedStatement.setString(2, vaccinationid);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int updateVacinationConsumptiondataVet(String vaccinationid, int result, int prodid, String qty) {
		int res = 0;
		try {
			String sql = "update veterinary_medicin_data set vac_productid=?,consumeid=? where id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "" + prodid);
			preparedStatement.setString(2, "" + result);
			preparedStatement.setString(3, vaccinationid);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public String getConsumeIdFromVaccinIdVet(String vaccinationid) {
		String ids = "0";
		try {
			String sql = "select consumeid from veterinary_medicin_data where id in (" + vaccinationid + ") ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				ids = ids + "," + rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ids;
	}

	@Override
	public ArrayList<DiaryManagement> getManasClinicUserList(int id, String clinicstaff_id) {
		PreparedStatement preparedStatement = null;
		ArrayList<DiaryManagement> list = new ArrayList<DiaryManagement>();
		//String  clinistaff=getclinicstaff(id);
		String sql = "select id,initial,firstname,lastname from apm_user where usertype=4 and islogin=1 and jobtitle!='OT'  and hasdiary=1 and clinic_staff="+clinicstaff_id+" order by firstname asc";
        
		try {

			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				DiaryManagement diaryManagement = new DiaryManagement();
				diaryManagement.setId(rs.getInt(1));
				if (rs.getString(2) == null || rs.getString(2).equals("")) {
					diaryManagement.setDiaryUser(rs.getString(3) + " " + rs.getString(4));

				} else {
					diaryManagement.setDiaryUser(rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));

				}
				list.add(diaryManagement);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public ArrayList<DiaryManagement> getUserList1(LoginInfo loginInfo) {
		PreparedStatement preparedStatement = null;
		ArrayList<DiaryManagement> list = new ArrayList<DiaryManagement>();
		StringBuffer buffer=new StringBuffer();
		buffer.append("select id,initial,firstname,lastname from apm_user where usertype=4 and islogin=1 and jobtitle!='OT'  and hasdiary=1 ");
		if(!loginInfo.getJobTitle().equals("Admin")) {
			buffer.append(" and id="+loginInfo.getId()+"");
		}
		buffer.append(" order by firstname asc");
		//String sql = "select id,initial,firstname,lastname from apm_user where usertype=4 and islogin=1 and jobtitle!='OT'  and hasdiary=1 order by firstname asc";
        
		try {

			preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				DiaryManagement diaryManagement = new DiaryManagement();
				diaryManagement.setId(rs.getInt(1));
				if (rs.getString(2) == null || rs.getString(2).equals("")) {
					diaryManagement.setDiaryUser(rs.getString(3) + " " + rs.getString(4));

				} else {
					diaryManagement.setDiaryUser(rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));

				}
				list.add(diaryManagement);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public int saveAppointmentType(String chargename) {
		PreparedStatement ps = null;
		int result = 0;
		
		try {
			String sql = "insert into apm_appointment_type(name,charges,chargeType) values(?,?,?)";
			ps = connection.prepareStatement(sql);
			ps.setString(1, chargename);
			ps.setString(2, "0");
			ps.setString(3, "AYUSHMANBHARAT");
	
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ArrayList<DiaryManagement> getUserListByOutsideHosp(String practid) {
		PreparedStatement preparedStatement = null;
		ArrayList<DiaryManagement> list = new ArrayList<DiaryManagement>();
		//String  clinistaff=getclinicstaff(id);
		String sql = "select id,initial,firstname,lastname from apm_user where usertype=4 and islogin=1 and jobtitle!='OT'  and hasdiary=1 and id="+practid+" order by firstname asc";
        
		try {

			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				DiaryManagement diaryManagement = new DiaryManagement();
				diaryManagement.setId(rs.getInt(1));
				if (rs.getString(2) == null || rs.getString(2).equals("")) {
					diaryManagement.setDiaryUser(rs.getString(3) + " " + rs.getString(4));

				} else {
					diaryManagement.setDiaryUser(rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));

				}
				list.add(diaryManagement);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
		
	}
	@Override
	public ArrayList<Master> getDoctorList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Master> list = new ArrayList<Master>();
		String sql = "SELECT * FROM ipd_refered_data ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setDoctorName(rs.getString(2));

				list.add(master);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}

	@Override
	public int insertProceduredata(int appointmentid, String procedureid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		int procedure = 0;
		String sql = "insert into ot_procedure(otid,procedureid) value(?,?)";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, appointmentid);
			preparedStatement.setString(2, procedureid);
			
			result = preparedStatement.executeUpdate();

			if (result == 1) {
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if (resultSet.next()) {
					procedure = resultSet.getInt(1);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return procedure;
	}

	@Override
	public double getProcedureCharges(String procedure) {
		PreparedStatement pst=null;
	double charge=0.0;
		try {
		String sql="SELECT charges FROM apm_appointment_type where chargeType ='"+procedure+"' and tpid = 0 and wardid=0 and otchargetype = 1 ";
		pst=connection.prepareStatement(sql);
		ResultSet rs=pst.executeQuery();
		while(rs.next()) {
			charge=rs.getDouble(1);
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return charge;
	}

	/*
	 * @Override public int updateCharges(double chargediff, int id) {
	 * PreparedStatement preparedStatement = null; int result = 0; String sql =
	 * "update apm_available_slot set charge=" + chargediff + " where id=" + id +
	 * " ";
	 * 
	 * try { preparedStatement = connection.prepareStatement(sql); result =
	 * preparedStatement.executeUpdate();
	 * 
	 * } catch (Exception e) { // TODO: handle exception } return result; }
	 */

	@Override
	public int saveaApmInvoiceData(NotAvailableSlot apmt, int appointmentid) {
		PreparedStatement pst=null;
		int result =0;
		int id=0;
		try {
			
			String sql = "insert into apm_invoice(clientid,practitionerid,clientname,practitionername,date,chargetype,appointmentid,location) values(?,?,?,?,?,?,?,?)";
            pst=connection.prepareStatement(sql);
            pst.setString(1, apmt.getClientId());
            pst.setInt(2, apmt.getDiaryUserId());
            pst.setString(3, apmt.getClientName());
            pst.setString(4, apmt.getDiaryUser());
            pst.setString(5, apmt.getCommencing());
            pst.setString(6, "");
            pst.setInt(7, appointmentid);
            pst.setString(8, "1");
        	result = pst.executeUpdate();

			if (result == 1) {
				ResultSet resultSet = pst.getGeneratedKeys();
				if (resultSet.next()) {
					id = resultSet.getInt(1);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}
	
	

	public int saveAppointment1(NotAvailableSlot notAvailableSlot) {
		int result = 0;
		int id = 0;

		PreparedStatement pstm = null;
		String sql = "insert into apm_available_slot(apmslotid,commencing,starttime,endtime,"
				+ "notes,diaryuserid,diaryusername,dept,location,room,clientname,aptmtype,"
				+ "duration,clientId,charge,treatmentEpisodeId,added_by,apmttypetext,usedsession,"
				+ "condition_id,whopay,otid,category,procedures,surgeon,anesthesia,ipdno,wardid,"
				+ "anidoctorfees,psurcharge,panetcharge,sic,assistaffcharge,opdbooktime,reqdatetime,"
				+ "mobstatus,speciality,appt_type,opdabbrevationid,refferedfrom,isPreDate,opdSequnce,deptOpdId,transfer_id) "
				+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," + "?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			pstm = connection.prepareStatement(sql);
			pstm.setInt(1, notAvailableSlot.getId());
			pstm.setString(2, notAvailableSlot.getCommencing());
			pstm.setString(3, notAvailableSlot.getSTime());
			pstm.setString(4, notAvailableSlot.getEndTime());
			pstm.setString(5, notAvailableSlot.getNotes());
			pstm.setInt(6, notAvailableSlot.getDiaryUserId());
			pstm.setString(7, notAvailableSlot.getDiaryUser());
			pstm.setString(8, notAvailableSlot.getDept());
			pstm.setString(9, "1");
			pstm.setString(10, "room1");
			pstm.setString(11, notAvailableSlot.getClientName());
			pstm.setString(12, notAvailableSlot.getApmtType());
			pstm.setString(13, notAvailableSlot.getDuration());
			pstm.setString(14, notAvailableSlot.getClientId());
			if (notAvailableSlot.isVaccineApmt()) {

			}
			//double charge = getCharge(notAvailableSlot.getApmtType());
			pstm.setDouble(15, notAvailableSlot.getCharge());
			pstm.setString(16, notAvailableSlot.getTreatmentEpisodeId());
			pstm.setString(17, notAvailableSlot.getAddedBy());
			String apmtTYpeText = getAppointmentTypeText(notAvailableSlot.getApmtType());
			pstm.setString(18, apmtTYpeText);
			pstm.setString(19, notAvailableSlot.getUsedsession());
			pstm.setString(20, "1");
			pstm.setString(21, "Client");
			pstm.setInt(22, notAvailableSlot.getOtid());
			pstm.setString(23, notAvailableSlot.getOtplan());
			pstm.setString(24, DateTimeUtils.numberCheck(notAvailableSlot.getProcedure()));
			pstm.setString(25, DateTimeUtils.numberCheck(notAvailableSlot.getSurgeon()));
			pstm.setString(26, DateTimeUtils.numberCheck(notAvailableSlot.getAnesthesia()));
			pstm.setString(27, DateTimeUtils.numberCheck(notAvailableSlot.getIpdno()));
			pstm.setString(28, DateTimeUtils.numberCheck(notAvailableSlot.getWardid()));

			pstm.setString(29, DateTimeUtils.numberCheck(notAvailableSlot.getAnifees()));
			pstm.setString(30, DateTimeUtils.numberCheck(notAvailableSlot.getPsurcharge()));
			pstm.setString(31, DateTimeUtils.numberCheck(notAvailableSlot.getPanetcharge()));
			pstm.setString(32, DateTimeUtils.numberCheck(notAvailableSlot.getSic()));
			pstm.setString(33, DateTimeUtils.numberCheck(notAvailableSlot.getAssistaffcharge()));
			
			String time="";
			String dateTime = "";
			if (notAvailableSlot.getPreDate() == 1) {
				DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
				Calendar cal = Calendar.getInstance();
				 time = dateFormat.format(cal.getTime());
				dateTime = notAvailableSlot.getCommencing() + " " + time;
			} else {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();
				dateTime = dateFormat.format(cal.getTime());
				time=dateTime.split(" ")[1];
			}
			pstm.setString(34, time);
			pstm.setString(35, dateTime);
			pstm.setString(36, notAvailableSlot.getMobstatus());
			pstm.setString(37, notAvailableSlot.getDept());
			pstm.setInt(38, notAvailableSlot.getAppt_type());
			String newopdabbr = "";
			String opdSeqAbbr = "";
			boolean flag = true;
			if (!DateTimeUtils.isNull(notAvailableSlot.getOtplan()).equals("0") && !DateTimeUtils.isNull(apmtTYpeText).equals("")) {
				//we are adding parameter(notAvailableSlot.getDiaryUserId()) in below method beacause of balgopal>physio >generate opdabbrivationid 
				newopdabbr = generateOPDSequenceNewFormat(notAvailableSlot.getDiaryUserId());
				opdSeqAbbr = generateLMHOPDSeq(notAvailableSlot.getCommencing());
				flag = false;
			}
			if (DateTimeUtils.isNull(notAvailableSlot.getOtplan()).equals("0") && flag) {
				opdSeqAbbr = generateLMHOPDSeq(notAvailableSlot.getCommencing());
			}
			pstm.setString(39, newopdabbr);
			pstm.setString(40, DateTimeUtils.isNull(notAvailableSlot.getRefferedfrom()));
			pstm.setString(41, "" + notAvailableSlot.getPreDate());
			pstm.setString(42, opdSeqAbbr);
			pstm.setString(43, "" + notAvailableSlot.getDeptOpdId());
			pstm.setString(44, notAvailableSlot.getInvoiceid());
			result = pstm.executeUpdate();

			if (result == 1) {
				ResultSet resultSet = pstm.getGeneratedKeys();
				if (resultSet.next()) {
					id = resultSet.getInt(1);
				}
			}

			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			UserProfile userProfile = userProfileDAO.getUserprofileDetails(notAvailableSlot.getDiaryUserId());
			if (userProfile.getJobgroup().equals("4")) {
				int update = updateMachineDoctorID(id, userProfile.getDoctor());
			}
            // int updtlod=updateLatopddate(notAvailableSlot.getClientId(),notAvailableSlot.getCommencing());
			// int
			// dd=addOpdConditionReport(id,notAvailableSlot.getClientId(),notAvailableSlot.getCondition(),notAvailableSlot.getCommencing());

		} catch (Exception e) {

			e.printStackTrace();
//			StringWriter sw = new StringWriter();
//	         PrintWriter pw = new PrintWriter(sw);
//	         e.printStackTrace(pw);
//			Tra.exceptionMail(sw.toString());

		}

		return id;
	}

	@Override
	public int saveApmInvoiceAssesmentformark(NotAvailableSlot notAvailableSlot, int apminv, int appointmentid) {
		
		PreparedStatement preparedStatement=null;
		int result=0;
		String sql = "insert into apm_invoice_assesments(invoiceid,user,apmtType,charge,startTime,duration,practitionerId,practitionerName,clientId,commencing,appointmentid,paybuy,markAppointment,thirdPartyId,department,chargeId) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try {

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, apminv);
			preparedStatement.setString(2, notAvailableSlot.getClientName());
			
			String apmt_name=getAppointmentTypeText( notAvailableSlot.getApmtType());
			preparedStatement.setString(3, apmt_name);
			preparedStatement.setString(4, Double.toString(notAvailableSlot.getCharge()));
			preparedStatement.setString(5, notAvailableSlot.getSTime());
			preparedStatement.setString(6, notAvailableSlot.getDuration());
			preparedStatement.setInt(7, notAvailableSlot.getDiaryUserId());
			preparedStatement.setString(8, notAvailableSlot.getDiaryUser());
			preparedStatement.setString(9, notAvailableSlot.getClientId());
			preparedStatement.setString(10, notAvailableSlot.getCommencing());
			preparedStatement.setInt(11, appointmentid);
			// int payby = getPayBy(notAvailableSlot.getTreatmentEpisodeId());
			preparedStatement.setString(12, notAvailableSlot.getPayBy());
			preparedStatement.setInt(13, 1);
			int tpid = getTPid(notAvailableSlot.getClientId());
			preparedStatement.setInt(14, tpid);
			preparedStatement.setString(15, notAvailableSlot.getDept());
			preparedStatement.setString(16, notAvailableSlot.getApmtType());

			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public String getDoctorsDept(String duserid) {
		PreparedStatement pst = null;
		String deptname = "";
		try {
			String sql = "select discription from apm_user where id=" + duserid + "";
			pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				deptname =(rs.getString(1));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return deptname;
	}

	@Override
	public int updateTransferAppointment(NotAvailableSlot n, int diaryUserId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getInvoice_id(NotAvailableSlot apmt) {
		PreparedStatement pst = null;
		String chargeinvoiceid = "";
		try {
			String sql = "select charges_invoice_id from apm_invoice where clientid=" + apmt.getClientId() + " and appointmentid='"+apmt.getId()+"'";
			pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				chargeinvoiceid =(rs.getString(1));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return chargeinvoiceid;
	}

	@Override
	public int deleteAppointment(int id) {
		PreparedStatement pst=null;
		int result=0;
		String sql="delete  from apm_available_slot where id="+id+"";
		try {
			
			pst=connection.prepareStatement(sql);
			result=pst.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public double getDebitfromChargeinvoice(String invoiceid) {
		PreparedStatement pst = null;
		double debit = 0;
		try {
			String sql = "select debit from apm_charges_invoice where id=" + invoiceid + "";
			pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				debit =(rs.getDouble(1));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return debit;
	}

	@Override
	public int updateChargeInvoicedebit(double newcharge, String invoiceid) {
		PreparedStatement pst=null;
		int result=0;
		try {
			String sql="update apm_charges_invoice set debit="+newcharge+" where id="+invoiceid+" ";
			pst=connection.prepareStatement(sql);
			result=pst.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int updateAssessmentCharge(double newcharge, String invoiceid) {
		PreparedStatement pst=null;
		int result=0;
		try {
			String sql="update apm_invoice_assesments set charge="+newcharge+" where invoiced="+invoiceid+" ";
			pst=connection.prepareStatement(sql);
			result=pst.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int updateChargesPayment(double newcharge, String invoiceid) {
		PreparedStatement pst=null;
		int result=0;
		try {
			String sql="update apm_charges_payment set payment="+newcharge+" where chargeinvoiceid="+invoiceid+" ";
			pst=connection.prepareStatement(sql);
			result=pst.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public NotAvailableSlot getDoctorname(String invoiceid) {
		PreparedStatement pst=null;
		NotAvailableSlot notslot=new NotAvailableSlot();
		try {
			
			String sql="select practitionerName,charge from apm_invoice_assesments where invoiced='"+invoiceid+"'";
			pst=connection.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				notslot.setDoctorname(rs.getString(1));
				notslot.setCharge(rs.getDouble(2));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return notslot;
	}

	@Override
	public ArrayList<DiaryManagement> getUserListforOpdData(int id,String fromdate, String todate) {
		PreparedStatement preparedStatement = null;
		ArrayList<DiaryManagement> list = new ArrayList<DiaryManagement>();
		MisChartDAO misChartDAO=new JDBCMisChartDAO(connection);
		//String  clinistaff=getclinicstaff(id);
		String sql = "select id,initial,firstname,lastname from apm_user where usertype=4 and islogin=1 and jobtitle!='OT'  and hasdiary=1 order by firstname asc";
        
		try {

			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			int docwisetotalCompleted=0;
			int docwisetotaldna=0;
			int docwisetotalcancel=0;
			int docwiseotPatientCount=0;
			int docwisetotalOpdPatient=0;
			int docwisebookedAppointment=0;
			int totalrev=0;
			while (rs.next()) {
				DiaryManagement diaryManagement = new DiaryManagement();
				diaryManagement.setId(rs.getInt(1));
				if (rs.getString(2) == null || rs.getString(2).equals("")) {
					diaryManagement.setDiaryUser(rs.getString(3) + " " + rs.getString(4));

				} else {
					diaryManagement.setDiaryUser(rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));

				}
				
				
				docwisetotalOpdPatient=misChartDAO.getDocwiseTotalOpdPatient(fromdate, todate,diaryManagement.getId());
				docwisebookedAppointment=misChartDAO.getDocwiseBookedAppointment(fromdate, todate,diaryManagement.getId());
				docwisetotalCompleted=misChartDAO.getDocwiseOPDCompletedAppointment(fromdate, todate,diaryManagement.getId());
				docwisetotaldna=misChartDAO.getDocwiseOPDdnaAppointment(fromdate, todate,diaryManagement.getId());
				docwisetotalcancel=misChartDAO.getDocwiseOPDcancelcountAppointment(fromdate, todate,diaryManagement.getId());
			    docwiseotPatientCount=misChartDAO.getDocwiseOtPatientsCounts(fromdate, todate,diaryManagement.getId());
			    totalrev=misChartDAO.getTotalRevnue(fromdate, todate,diaryManagement.getId());
			    
			    int noc = docwisetotalCompleted + docwisetotaldna;
				long notCompleted = docwisebookedAppointment - noc;
			    
			    diaryManagement.setDocwisetotalOpdPatient(""+docwisetotalOpdPatient);
			    diaryManagement.setDocwisetotalCompleted(""+docwisetotalCompleted);
			    diaryManagement.setDocwisetotaldna(""+docwisetotaldna);
			    diaryManagement.setDocwisetotalcancel(""+docwisetotalcancel);
			    diaryManagement.setDocwiseotPatientCount(""+docwiseotPatientCount);		
			    diaryManagement.setDocwisebookedAppointment(""+docwisebookedAppointment);
			    diaryManagement.setDocwisetotalcancel(""+docwisetotalcancel);
			    diaryManagement.setDocwisenotcompleted(""+notCompleted);
			    diaryManagement.setTotalrev(totalrev);
				
				list.add(diaryManagement);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
		
	}
	
}