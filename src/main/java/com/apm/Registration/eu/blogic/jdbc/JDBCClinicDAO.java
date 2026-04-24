package com.apm.Registration.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.naming.ldap.PagedResultsControl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
//import org.omg.CORBA.Request;

import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Registration.eu.bi.ClinicDAO;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.Location;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.eu.blogic.jdbc.JDBCBaseDAO;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Encryption;
import com.apm.common.utils.Pagination;
import com.apm.common.web.common.helper.Access;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ActionContext;

public class JDBCClinicDAO extends JDBCBaseDAO implements ClinicDAO {

	/*HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);*/

	public JDBCClinicDAO(Connection connection) {
		this.connection = connection;

	}

	public ArrayList<Clinic> getClinicList(Pagination pagination,
			String searchText) throws Exception {
		PreparedStatement preparedStatement = null;
		ArrayList<Clinic> list = new ArrayList<Clinic>();
		/*
		 * String sql = "select id,clinicname,firstname,lastname from apm_user
		 * where clinicname like('%"+searchText+"%') and userid = ? ";
		 */

		String sql = "";

		if (!searchText.equals("")) {
			sql = "select id,clinicname,clinicowner,userid,activestatus,usertype,loginstatus,clinicid from apm_user where userid like('%"
					+ searchText + "%')  ";
		} else {
			sql = "select id,clinicname,clinicowner,userid,activestatus,usertype,loginstatus,clinicid from apm_user  ";
		}

		try {

			preparedStatement = connection.prepareStatement(sql);
			// preparedStatement.setString(1,userID);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				Clinic cliniclist = new Clinic();

				// cliniclist.setId(rs.getInt(1));

				cliniclist.setId(rs.getInt(1));
				cliniclist.setClinicName(rs.getString(2));
				cliniclist.setClinicOwner(rs.getString(3));
				cliniclist.setUserId(rs.getString(4));
				String password = getPassword(rs.getString(4));
				cliniclist.setPassword(password);
				cliniclist.setActivestatus(rs.getInt(5));
				cliniclist.setUserType(rs.getInt(6));
				cliniclist.setLoginstatus(rs.getBoolean(7));
				cliniclist.setClinicID(rs.getString(8));

				list.add(cliniclist);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	private String getPassword(String userId) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT password FROM apm_admin where  userid = '"
				+ userId + "'";

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

	public ArrayList<Clinic> getClinicList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Clinic> list = new ArrayList<Clinic>();
		String sql = "select id,clinicname,clinicowner,userid from apm_user";

		try {

			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				Clinic cliniclist = new Clinic();

				// cliniclist.setId(rs.getInt(1));

				cliniclist.setId(rs.getInt(1));
				cliniclist.setClinicName(rs.getString(2));
				// cliniclist.setFirstname(rs.getString(3));
				cliniclist.setClinicOwner(rs.getString(3));
				cliniclist.setUserId(rs.getString(4));

				list.add(cliniclist);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public Clinic getCliniclistDetails(int selectedid) {
		PreparedStatement preparedStatement = null;
		Clinic cliniclist = new Clinic();
		MasterDAO masterDAO=new JDBCMasterDAO(connection);
		// String sql = "SELECT * from apm_user where id=?";
		String sql = "select id,clinicname,clinicowner,email,mobile,landline,userid,password,standalone,hosteddb,onlinesingledevice,onlinemultidevice,"
				+ "diarymanagement,appointmentbooking,basicfinance,fullfinance,medicalrecord,clinicresourcemanagement,clinicpayrollmanagement,"
				+ "owner_qualification,website_url,clinicLogo,desktop,mobilePhone,ios,tablet,communication,report,assessmentForm,address,starttime,endtime,"
				+ "excess_limit,auto_charge_time,advancerenge,ipdregcharge,ipdregtype,smscheck,smspayment,registerno,setstdcharge,withpayment,withoutpayment,"
				+ "invoice_date,isshowdiscount, showinvestigation, criticalvaluesms,medtreatgiven,gstn_no,stock_log,bdaysms,immusms,f_diagnosis_discharge,seq_no_gen,"
				+ "fromtime,totime,remove_procurement,modify_disc,consultsms,show_wardname,opd_invoice_post,sms_on_bedchange,sms_on_newadm,"
				+ "ipd_abbr_access, hidelogoinvst, hidelogoemr, hidelogobillinv,le_msg,smscheckdoctor,auto_generic_name,default_phar_location,"
				+ "direct_prisc,invest_savenprint,prisc_savenprint,followupday,sms_access,opd_paymnet_sms,ipd_payment_sms,sms_cancel_appointment,refered_clinicname,address "
				+ "from apm_user where id = "
				+ selectedid + "";
		try {

			preparedStatement = connection.prepareStatement(sql);
			

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				cliniclist.setId(rs.getInt(1));
				cliniclist.setClinicName(rs.getString(2));
				cliniclist.setClinicOwner(rs.getString(3));
				cliniclist.setEmail(rs.getString(4));
				cliniclist.setMobileNo(rs.getString(5));
				cliniclist.setLandLine(rs.getString(6));
				cliniclist.setUserId(rs.getString(7));
				cliniclist.setPassword(rs.getString(8));

				Location location = getLovationAndPostCode();
				cliniclist.setLocationname(location.getLocationname());
				cliniclist.setPinCode(location.getPinCode());
				cliniclist.setAddress(location.getAddress());
				cliniclist.setContactNo(location.getContactNo());

				cliniclist.setStandalone(rs.getBoolean(9));
				cliniclist.setHostedDB(rs.getBoolean(10));
				cliniclist.setOnlineSingleDevice(rs.getBoolean(11));
				cliniclist.setOnlineMultiDevice(rs.getBoolean(12));
				cliniclist.setDiaryManagement(rs.getBoolean(13));
				cliniclist.setAppointmentBooking(rs.getBoolean(14));
				cliniclist.setBasicFinance(rs.getBoolean(15));
				cliniclist.setFullFinance(rs.getBoolean(16));
				cliniclist.setMedicalRecord(rs.getBoolean(17));
				cliniclist.setClinicResourceMngment(rs.getBoolean(18));
				cliniclist.setClinicPayrollMngment(rs.getBoolean(19));
				cliniclist.setOwner_qualification(rs.getString(20));
				cliniclist.setWebsiteUrl(rs.getString(21));
				cliniclist.setUserImageFileName(rs.getString(22));
				cliniclist.setDesktop(rs.getBoolean(23));
				cliniclist.setMobile(rs.getBoolean(24));
				cliniclist.setIOS(rs.getBoolean(25));
				cliniclist.setTablet(rs.getBoolean(26));
				cliniclist.setCommunication(rs.getBoolean(27));
				cliniclist.setReport(rs.getBoolean(28));
				cliniclist.setAssessmentForms(rs.getBoolean(29));

				// cliniclist.setAddress(rs.getString(30));
				cliniclist.setStarttime(rs.getString(31));
				cliniclist.setEndtime(rs.getString(32));
				cliniclist.setExcess(rs.getString(33));	
				cliniclist.setAdvanceTime(rs.getString(34));
				cliniclist.setAdvancerenge(rs.getString(35));
				cliniclist.setIpdregcharge(rs.getString(36));
				cliniclist.setIpdregtype(rs.getString(37));
				cliniclist.setSmscheck(rs.getBoolean(38));
				cliniclist.setSmsPayment(rs.getBoolean(39));
				cliniclist.setRegisterno(rs.getString(40));
				cliniclist.setSetupstdcharge(rs.getString(41));
				cliniclist.setWithpayment(rs.getString(42));
				cliniclist.setWithoutpayment(rs.getString(43));
				cliniclist.setInvoice_date(rs.getString(44));
				cliniclist.setDiscount_show(rs.getString(45));
				
			    cliniclist.setInvestigation_show(rs.getString(46));
			    cliniclist.setCriticalvaluesms(rs.getBoolean(47));
			    cliniclist.setMedtreatgiven(rs.getBoolean(48));
			    //lokesh
			    if(rs.getString(49)!=null){
			    cliniclist.setGstn_no(rs.getString(49));
			    }else{
			    	cliniclist.setGstn_no("");
			    }
			    cliniclist.setStock_log(rs.getBoolean(50));
			    int smscount=masterDAO.getSMSCount();	
			    cliniclist.setSmscount(smscount);
			    int wsmscount=masterDAO.getWSMSCount();
			    cliniclist.setWsms(wsmscount);
			    cliniclist.setBdaysms(rs.getBoolean(51));
			    cliniclist.setImmusms(rs.getBoolean(52));
			    cliniclist.setF_diagnosis_discharge(rs.getBoolean(53));
			    cliniclist.setSeq_no_gen(rs.getBoolean(54));
			    cliniclist.setFromtime(rs.getString(55));
			    cliniclist.setTotime(rs.getString(56));
			    cliniclist.setRemoveprocurement(rs.getBoolean(57));
			    cliniclist.setModify_disc(rs.getBoolean(58));
			    cliniclist.setSmsVisitingConslt(rs.getBoolean(59));
			    //  15/12/2018 show ward name access wise
			    cliniclist.setShow_wardname(rs.getBoolean(60));
			    //  14/12/2018 show book with payment unposted
			    cliniclist.setShow_unpost(rs.getBoolean(61));
			    //  20/12/2018 sms dr on bedchange and new admission
			    cliniclist.setSms_on_bedchange(rs.getBoolean(62));
			    cliniclist.setSms_on_newadm(rs.getBoolean(63));
			    //  08/01/19 show new ipd id
			    cliniclist.setShow_new_ipd_id(rs.getBoolean(64));
			    //  14/01/2019 hide letterhead access
			    cliniclist.setHidelettinvst(rs.getBoolean(65));
			    cliniclist.setHidelettemr(rs.getBoolean(66));
			    cliniclist.setHidelettbillinv(rs.getBoolean(67));
			    cliniclist.setLe_msg(rs.getString(68));
			    cliniclist.setSmscheckdoctor(rs.getBoolean(69));
			    cliniclist.setAuto_generic_name(rs.getBoolean(70));
			    cliniclist.setDefault_phar_location(""+rs.getInt(71));
			    cliniclist.setDirect_prisc(rs.getBoolean(72));
			    cliniclist.setInvest_savenprint(rs.getBoolean(73));
			    cliniclist.setPrisc_savenprint(rs.getBoolean(74));
			    cliniclist.setOpdfollowup(rs.getString(75));
			    cliniclist.setSms_access(rs.getBoolean(76));
			    cliniclist.setOpd_payamnt_sms(rs.getBoolean(77));
			    cliniclist.setIpd_payamnt_sms(rs.getBoolean(78));
			    cliniclist.setSms_cancel_appointment(rs.getBoolean(79));
			    cliniclist.setManas_clinic_name(rs.getString(80));
			    cliniclist.setAddress(DateTimeUtils.isNull(rs.getString(81)));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return cliniclist;
	}

	private Location getLovationAndPostCode() {
		PreparedStatement preparedStatement = null;
		Location location = new Location();
		String sql = "SELECT location,postcode,address,contact_no FROM apm_clinic_location where checkLocation = 'true' ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				location.setLocationname(rs.getString(1));
				location.setPinCode(rs.getString(2));
				location.setAddress(rs.getString(3));
				location.setContactNo(rs.getString(4));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return location;
	}

	public ArrayList<Clinic> getClinicLocationList(int selectedid) {
		PreparedStatement preparedLocationStatement = null;
		ArrayList<Clinic> list = new ArrayList<Clinic>();

		String sqlLocation = "SELECT * from apm_clinic_location where userid=?";

		try {
			preparedLocationStatement = connection
					.prepareStatement(sqlLocation);
			preparedLocationStatement.setInt(1, selectedid);
			ResultSet rslocation = preparedLocationStatement.executeQuery();

			while (rslocation.next()) {

				Clinic cliniclist = new Clinic();
				cliniclist.setLocationid(rslocation.getInt(1));
				cliniclist.setCountry(rslocation.getString(2));
				cliniclist.setCity(rslocation.getString(3));
				cliniclist.setAddress(rslocation.getString(4));
				cliniclist.setPinCode(rslocation.getString(5));
				cliniclist.setLocationname(rslocation.getString(7));
				cliniclist.setColorName(rslocation.getString(8));
				cliniclist.setContactNo(rslocation.getString(9));
				cliniclist.setCheckLocation(rslocation.getString(12));
				cliniclist.setAddressType(rslocation.getString(13));

				list.add(cliniclist);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}

	public int getCliniclistCount(String searchText) throws Exception {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select count(*) from apm_user  where clinicname like('%"
				+ searchText + "%') and usertype = 2 ";
		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateCliniclist(Clinic cliniclist) {

		PreparedStatement preparedStatement = null;

		int result = 0;
		/*
		 * String sql = "UPDATE userprofile INNER JOIN usersetup ON
		 * userprofile.id = usersetup.userid set
		 * userprofile.fullname=?,userprofile.initial=?,userprofile.jobtitle=?,userprofile.discription=?,userprofile.registerno=?,userprofile.usergroup=?," + "
		 * usersetup.isapractitioner=?,usersetup.appointmentdiary=?,usersetup.diarycolor=?,usersetup.diarycolumnposition=?,usersetup.compressionrate=?," + "
		 * usersetup.onlinename=?,usersetup.isavailableonline=? where
		 * userprofile.id=?";
		 */

		String sql = "UPDATE apm_user set clinicname=?,firstname=?,lastname=?,userid=?,email=?,mobile=?,landline=?,clinicowner=?,standalone=?,"
				+ "hosteddb=?,onlinesingledevice=?,onlinemultidevice=?,diarymanagement=?,appointmentbooking=?,"
				+ "basicfinance=?,fullfinance=?,medicalrecord=?,clinicresourcemanagement=?,clinicpayrollmanagement=?,website_url=?,owner_qualification=?,clinicLogo=? where id=?";

		try {
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, cliniclist.getClinicName());
			preparedStatement.setString(2, cliniclist.getFirstName());
			preparedStatement.setString(3, cliniclist.getLastName());
			/* preparedStatement.setString(4, cliniclist.getDiscription()); */
			preparedStatement.setString(4, cliniclist.getUserId());
			/* preparedStatement.setString(5, cliniclist.getPassword()); */

			preparedStatement.setString(5, cliniclist.getEmail());
			preparedStatement.setString(6, cliniclist.getMobileNo());
			preparedStatement.setString(7, cliniclist.getLandLine());

			preparedStatement.setString(8, cliniclist.getClinicOwner());
			preparedStatement.setBoolean(9, cliniclist.isStandalone());
			preparedStatement.setBoolean(10, cliniclist.isHostedDB());

			preparedStatement.setBoolean(11, cliniclist.isOnlineSingleDevice());
			preparedStatement.setBoolean(12, cliniclist.isOnlineMultiDevice());
			preparedStatement.setBoolean(13, cliniclist.isDiaryManagement());
			preparedStatement.setBoolean(14, cliniclist.isAppointmentBooking());
			preparedStatement.setBoolean(15, cliniclist.isBasicFinance());
			preparedStatement.setBoolean(16, cliniclist.isFullFinance());
			preparedStatement.setBoolean(17, cliniclist.isMedicalRecord());
			preparedStatement.setBoolean(18, cliniclist
					.isClinicResourceMngment());
			preparedStatement.setBoolean(19, cliniclist
					.isClinicPayrollMngment());
			preparedStatement.setString(20, cliniclist.getWebsiteUrl());
			preparedStatement
					.setString(21, cliniclist.getOwner_qualification());

			preparedStatement.setInt(22, cliniclist.getId());
			preparedStatement.setString(23, cliniclist.getUserImageFileName());

			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public int saveLocation(int selectedid, int locationid, Clinic cliniclist) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "UPDATE apm_clinic_location set address=?,postcode=?,location=?,color=?,contact_no =? where userid=? and id=?";

		try {
			preparedStatement = connection.prepareStatement(sql);
			// preparedStatement.setString(1, cliniclist.getCountry());
			// preparedStatement.setString(1, cliniclist.getCity());
			preparedStatement.setString(1, cliniclist.getAddress());
			preparedStatement.setString(2, cliniclist.getPinCode());
			preparedStatement.setString(3, cliniclist.getLocationname());
			preparedStatement.setString(4, "#" + cliniclist.getColorName());
			preparedStatement.setString(5, cliniclist.getContactNo());
			// preparedStatement.setString(6, cliniclist.getAddressType());
			// preparedStatement.setString(7, cliniclist.getEmailId());
			preparedStatement.setInt(6, selectedid);
			preparedStatement.setInt(7, locationid);

			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public int deleteClinicList(int selectedid) {
		int result = 0;
		try {
			String sql = "delete from apm_user where id= " + selectedid;
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);
			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean isUserExist(String userid, String selectedid,
			String existUserid) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String usrid = "";
		String sql = "select userid from apm_user where userid = ? ";

		try {

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userid);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				usrid = rs.getString(1);
				result = true;
			}

			if (!selectedid.equals("0")) {
				if (usrid.equals(existUserid)) {
					result = false;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public int saveClinic(Clinic clinic) {
		PreparedStatement preparedStatement = null;
		int userid = 0;
		String sql = "insert into apm_user (clinicname,clinicowner,email,mobile,landline,userid,password,standalone,hosteddb,onlinesingledevice,onlinemultidevice,diarymanagement,appointmentbooking,basicfinance,fullfinance,medicalrecord,clinicresourcemanagement,clinicpayrollmanagement,usertype,owner_qualification,clinicLogo,checkMailSend,desktop,mobilePhone,ios,tablet,communication,report,assessmentForm) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int usertype = 2;
		String checkMailSend = "true";
		try {

			String encPassword = Encryption.encryptSHA(clinic.getPassword());

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, clinic.getClinicName());
			preparedStatement.setString(2, clinic.getClinicOwner());
			preparedStatement.setString(3, clinic.getEmail());
			preparedStatement.setString(4, "0" + clinic.getMobileNo());
			preparedStatement.setString(5, clinic.getLandLine());
			preparedStatement.setString(6, clinic.getUserId());
			preparedStatement.setString(7, encPassword);
			preparedStatement.setBoolean(8, clinic.isStandalone());
			preparedStatement.setBoolean(9, clinic.isHostedDB());
			preparedStatement.setBoolean(10, clinic.isOnlineSingleDevice());
			preparedStatement.setBoolean(11, clinic.isOnlineMultiDevice());
			preparedStatement.setBoolean(12, clinic.isDiaryManagement());
			preparedStatement.setBoolean(13, clinic.isAppointmentBooking());
			preparedStatement.setBoolean(14, clinic.isBasicFinance());
			preparedStatement.setBoolean(15, clinic.isFullFinance());
			preparedStatement.setBoolean(16, clinic.isMedicalRecord());
			preparedStatement.setBoolean(17, clinic.isClinicResourceMngment());
			preparedStatement.setBoolean(18, clinic.isClinicPayrollMngment());
			preparedStatement.setInt(19, usertype);
			preparedStatement.setString(20, clinic.getOwner_qualification());

			preparedStatement.setString(21, clinic.getUserImageFileName());
			preparedStatement.setString(22, checkMailSend);
			preparedStatement.setBoolean(23, clinic.isDesktop());
			preparedStatement.setBoolean(24, clinic.isMobile());
			preparedStatement.setBoolean(25, clinic.isIOS());
			preparedStatement.setBoolean(26, clinic.isTablet());
			preparedStatement.setBoolean(27, clinic.isCommunication());
			preparedStatement.setBoolean(28, clinic.isReport());
			preparedStatement.setBoolean(29, clinic.isAssessmentForms());

			int result = preparedStatement.executeUpdate();

			if (result == 1) {
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if (resultSet.next()) {
					userid = resultSet.getInt(1);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return userid;
	}

	public int saveAdminAccessPassword(int userid, Clinic clinic) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_admin(userid,password) values(?,?)";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, clinic.getUserId());
			preparedStatement.setString(2, clinic.getPassword());

			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int saveLocation(int userid, Clinic clinic) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_clinic_location(address,postcode,location,color,userid,contact_no,checkLocation,addressType,emailId) values(?,?,?,?,?,?,?,?,?)";

		try {
			preparedStatement = connection.prepareStatement(sql);
			// preparedStatement.setString(1, clinic.getCountry());
			// preparedStatement.setString(2, clinic.getCity());
			preparedStatement.setString(1, clinic.getAddress());
			preparedStatement.setString(2, clinic.getPinCode());
			preparedStatement.setString(3, clinic.getLocationname());
			preparedStatement.setString(4, "#" + clinic.getColorName());
			preparedStatement.setInt(5, userid);
			preparedStatement.setString(6, clinic.getContactNo());
			String checkLocation = clinic.getCheckLocation();
			if (checkLocation == null) {
				checkLocation = "false";
			} else if (checkLocation.equals("on")) {
				checkLocation = "true";
			} else {
				checkLocation = "false";
			}
			preparedStatement.setString(7, checkLocation);
			preparedStatement.setString(8, clinic.getAddressType());
			preparedStatement.setString(9, clinic.getEmailId());

			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public Clinic getuser(String userId) {
		PreparedStatement preparedStatement = null;
		Clinic clinic = new Clinic();
		/*
		 * String sql = "select
		 * id,userid,password,firstname,lastname,clinicowner,usertype from
		 * apm_user where userid=?";
		 */

		String sql = "select apm_user.id,userid,password,firstname,lastname,clinicowner,apm_user.usertype,usertypemaster.usertype as uusertype,clinicid,country,islogo,jobtitle,mobile from apm_user "
				+ "inner join usertypemaster on apm_user.usertype = usertypemaster.id "
				+ "where userid=?";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userId);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				clinic.setId(rs.getInt(1));
				clinic.setUserId(rs.getString(2));
				clinic.setPassword(rs.getString(3));
				clinic.setFirstName(rs.getString(4));
				clinic.setLastName(rs.getString(5));
				clinic.setClinicOwner(rs.getString(6));
				clinic.setUserType(rs.getInt(7));
				clinic.setUuserType(rs.getString(8));
				clinic.setClinicID(rs.getString(9));
				clinic.setCountry(rs.getString(10));
				clinic.setIslogo(rs.getString(11));
                clinic.setJobtitle(rs.getString(12));
                clinic.setMobileNo(rs.getString(13));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return clinic;
	}

	public ArrayList<Clinic> getColorList() {
		PreparedStatement preparedLocationStatement = null;
		ArrayList<Clinic> list = new ArrayList<Clinic>();

		String sqlLocation = "select id,color from color ";

		try {
			preparedLocationStatement = connection
					.prepareStatement(sqlLocation);

			ResultSet rslocation = preparedLocationStatement.executeQuery();

			while (rslocation.next()) {

				Clinic clinic = new Clinic();
				clinic.setId(rslocation.getInt(1));
				clinic.setColorName(rslocation.getString(2));

				list.add(clinic);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public int updateClinic(Clinic clinic, int selectedid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		// String sql = "update apm_user set clinicname = ?,clinicowner =
		// ?,email = ?,mobile = ?,landline = ?,standalone = ?,hosteddb
		// =?,onlinesingledevice = ?,onlinemultidevice = ?,diarymanagement =
		// ?,appointmentbooking = ?,basicfinance = ?,fullfinance =
		// ?,medicalrecord = ?,clinicresourcemanagement =
		// ?,clinicpayrollmanagement =
		// ?,website_url=?,owner_qualification=?,clinicLogo=? where id =
		// "+selectedid+"";
		// removed by jitu this line // ,smsrelativeDischarge=?,smsdoctorBedchange=?,smscheckrelativebedchange=?,smscheckdoctor=?,smspatientApproved=?,smspatientCompleted=?
		String sql = "update apm_user  set clinicname = ?,clinicowner = ?,email = ?,mobile = ?,landline=?,website_url=?,owner_qualification=?,clinicLogo=?,starttime=?,"
				+ "endtime=?,excess_limit=?,auto_charge_time=?,advancerenge=?,ipdregcharge=?,ipdregtype=?,smscheck=?,smspayment=?,setstdcharge=?,withpayment=?,"
				+ "withoutpayment=?,invoice_date=?,isshowdiscount=?,showinvestigation=?,bdaysms=?,immusms=?,consultsms=?,show_wardname=?,opd_invoice_post=?,"
				+ "sms_on_bedchange=?,sms_on_newadm=?,ipd_abbr_access=?, hidelogoinvst=?, hidelogoemr=?, hidelogobillinv=?,le_msg=?,smscheckdoctor=?,auto_generic_name=?,"
				+ "default_phar_location=?,direct_prisc=?,invest_savenprint=?,prisc_savenprint=?,followupday=? where id = "
			+ selectedid + "";

		try {

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, clinic.getClinicName());
			preparedStatement.setString(2, clinic.getClinicOwner());
			preparedStatement.setString(3, clinic.getEmail());
			preparedStatement.setString(4, clinic.getMobileNo());
			preparedStatement.setString(5, clinic.getLandLine());

			/*
			 * preparedStatement.setBoolean(6, clinic.isStandalone());
			 * preparedStatement.setBoolean(7, clinic.isHostedDB());
			 * preparedStatement.setBoolean(8, clinic.isOnlineSingleDevice());
			 * preparedStatement.setBoolean(9, clinic.isOnlineMultiDevice());
			 * preparedStatement.setBoolean(10, clinic.isDiaryManagement());
			 * preparedStatement.setBoolean(11, clinic.isAppointmentBooking());
			 * preparedStatement.setBoolean(12, clinic.isBasicFinance());
			 * preparedStatement.setBoolean(13, clinic.isFullFinance());
			 * preparedStatement.setBoolean(14, clinic.isMedicalRecord());
			 * preparedStatement.setBoolean(15,
			 * clinic.isClinicResourceMngment());
			 * preparedStatement.setBoolean(16,
			 * clinic.isClinicPayrollMngment());
			 */
			
			preparedStatement.setString(6, clinic.getWebsiteUrl());
			preparedStatement.setString(7, clinic.getOwner_qualification());
			preparedStatement.setString(8, clinic.getUserImageFileName());
			preparedStatement.setString(9, clinic.getStarttime());
			preparedStatement.setString(10, clinic.getEndtime());
            preparedStatement.setString(11, clinic.getExcess());
			preparedStatement.setString(12, clinic.getAdvanceTime());
			preparedStatement.setString(13, clinic.getAdvancerenge());
            preparedStatement.setString(14, clinic.getIpdregcharge());
            preparedStatement.setString(15, clinic.getIpdregtype());
            preparedStatement.setBoolean(16, clinic.isSmscheck());
            preparedStatement.setBoolean(17, clinic.isSmsPayment());
            preparedStatement.setString(18, clinic.getSetupstdcharge());
            preparedStatement.setString(19, clinic.getWithpayment());
            preparedStatement.setString(20, clinic.getWithoutpayment());
			preparedStatement.setString(21, clinic.getInvoice_date());
			preparedStatement.setString(22, clinic.getDiscount_show());
			preparedStatement.setString(23, clinic.getInvestigation_show() );
			preparedStatement.setBoolean(24, clinic.isBdaysms());
			preparedStatement.setBoolean(25, clinic.isImmusms());
			preparedStatement.setBoolean(26, clinic.isSmsVisitingConslt());
			preparedStatement.setBoolean(27, clinic.isShow_wardname());
			preparedStatement.setBoolean(28, clinic.isShow_unpost());
			preparedStatement.setBoolean(29, clinic.isSms_on_bedchange());
			preparedStatement.setBoolean(30, clinic.isSms_on_newadm());
			preparedStatement.setBoolean(31, clinic.isShow_new_ipd_id());
			preparedStatement.setBoolean(32, clinic.isHidelettinvst());
			preparedStatement.setBoolean(33, clinic.isHidelettemr());
			preparedStatement.setBoolean(34, clinic.isHidelettbillinv());
			preparedStatement.setString(35, clinic.getLe_msg());
			preparedStatement.setBoolean(36, clinic.isSmscheckdoctor());
			preparedStatement.setBoolean(37, clinic.isAuto_generic_name());
			preparedStatement.setString(38, clinic.getDefault_phar_location());
			preparedStatement.setBoolean(39, clinic.isDirect_prisc());
			preparedStatement.setBoolean(40, clinic.isPrisc_savenprint());
			preparedStatement.setBoolean(41, clinic.isInvest_savenprint());
			preparedStatement.setString(42, clinic.getOpdfollowup());
			/*preparedStatement.setBoolean(22, clinic.isSmsrelativeDischarge());
			preparedStatement.setBoolean(23, clinic.isSmsdoctorBedchange());
			preparedStatement.setBoolean(24, clinic.isSmscheckrelativebedchange());
			preparedStatement.setBoolean(25, clinic.isSmscheckdoctor());
			preparedStatement.setBoolean(26, clinic.isSmspatientCompleted());
			preparedStatement.setBoolean(27, clinic.isSmspatientApproved());*/
			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deleteClinicLocation(int userid) {
		int result = 0;
		try {
			String sql = "delete from apm_clinic_location where userid= "
					+ userid;
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);
			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Clinic getLocationDetails(int selectedid) {
		PreparedStatement preparedLocationStatement = null;
		Clinic clinic = new Clinic();

		String sqlLocation = "SELECT * from apm_clinic_location where id= "
				+ selectedid + "";

		try {
			preparedLocationStatement = connection
					.prepareStatement(sqlLocation);
			ResultSet rslocation = preparedLocationStatement.executeQuery();

			while (rslocation.next()) {

				clinic.setLocationid(rslocation.getInt(1));
				// clinic.setCountry(rslocation.getString(2));
				clinic.setCity(rslocation.getString(3));
				clinic.setAddress(rslocation.getString(4));
				clinic.setPinCode(rslocation.getString(5));
				clinic.setLocationname(rslocation.getString(7));
				clinic.setColorName(rslocation.getString(8));
				clinic.setContactNo(rslocation.getString(9));
				clinic.setLoc_direction(rslocation.getString(10));
				clinic.setUserImageFileName(rslocation.getString(11));
				clinic.setCheckLocation(rslocation.getString(12));
				clinic.setAddressType(rslocation.getString(13));
				clinic.setEmailId(rslocation.getString(14));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return clinic;
	}

	public int updateClinicLocation(Clinic clinic, int selectedid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_clinic_location set address = ?,"
				+ " postcode = ?,location = ?,color = ?,contact_no = ?,loc_direction = ?,"
				+ " map_filename = ?,checkLocation = ?,addressType = ?,emailId = ?,"
				+ " city = ?,hospname=? where id = "
				+ selectedid + "";
		try {

			preparedStatement = connection.prepareStatement(sql);
			// preparedStatement.setString(1, clinic.getCountry());
			// preparedStatement.setString(2, clinic.getCity());
			preparedStatement.setString(1, clinic.getAddress());
			preparedStatement.setString(2, clinic.getPinCode());
			preparedStatement.setString(3, clinic.getLocationname());
			preparedStatement.setString(4, "#" + clinic.getColorName());
			preparedStatement.setString(5, clinic.getContactNo());
			preparedStatement.setString(6, clinic.getLoc_direction());
			preparedStatement.setString(7, clinic.getUserImageFileName());
			preparedStatement.setString(8, clinic.getCheckLocation());
			preparedStatement.setString(9, clinic.getAddressType());
			preparedStatement.setString(10, clinic.getEmailId());
			preparedStatement.setString(11, clinic.getCity());
			preparedStatement.setString(12, clinic.getClinicName());

			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deleteClinicLocation(Clinic clinic, int selectedid) {
		int result = 0;
		try {
			String sql = "delete from apm_clinic_location where id = "
					+ selectedid;
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);
			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int changeMailSend(String checkMailSend, int id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_user set checkMailSend = ? where id = " + id
				+ "";
		try {
			preparedStatement = connection.prepareStatement(sql);
			// String value = getMailSendValue(id);
			preparedStatement.setString(1, checkMailSend);
			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String IsMailSend(int id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select checkMailSend from apm_user where id = " + id + "";
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

	public boolean checkEmailidExist(String emailid) {

		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "SELECT * FROM apm_user where email = '" + emailid + "' ";

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

	public int updateClinicStatus(String selectedid, int activestatus) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update  apm_user set activestatus = " + activestatus
				+ " where id = " + selectedid + " ";

		try {

			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Clinic> getLocationList(int id) {
		PreparedStatement preparedLocationStatement = null;
		ArrayList<Clinic> list = new ArrayList<Clinic>();

		String sqlLocation = "select id,address,postcode from apm_clinic_location where userid = "
				+ id + " and checkLocation = 'true'";

		try {
			preparedLocationStatement = connection
					.prepareStatement(sqlLocation);

			ResultSet rslocation = preparedLocationStatement.executeQuery();

			while (rslocation.next()) {

				Clinic clinic = new Clinic();
				clinic.setId(rslocation.getInt(1));
				clinic.setAddress(rslocation.getString(2));
				clinic.setPinCode(rslocation.getString(3));

				list.add(clinic);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public String getOtherUserID(String clinicID) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select userid from apm_user where id= " + clinicID + "";

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

	public int updateLocationCheck(String checkLoc, int locId) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_clinic_location set checkLocation='"
				+ checkLoc + "' where id = " + locId + "";
		try {
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getSemikaUserID(String usr) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT clinicid FROM admin.apm_user where userid='" + usr
				+ "'";

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

	public ArrayList<Clinic> getDNAChargeList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Clinic> list = new ArrayList<Clinic>();
		String sql = "select id,dnaCharge from apm_dnacharge";
		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Clinic clinic = new Clinic();
				clinic.setId(rs.getInt(1));
				clinic.setDnaCharges(rs.getInt(2));

				list.add(clinic);
			}

		} catch (Exception e) {

		}
		return list;
	}

	public int saveDnaCharge(Clinic clinic) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_dnacharge (dnaCharge) values (?)";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, clinic.getDnaCharges());
			result = preparedStatement.executeUpdate();

		} catch (Exception e) {

		}
		return result;
	}

	public Clinic getDnaChargeDetails(int selectedid) {
		PreparedStatement preparedStatement = null;
		Clinic clinic = new Clinic();
		String sql = "select id,dnaCharge from apm_dnacharge where id = "
				+ selectedid + "";
		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				clinic.setId(rs.getInt(1));
				clinic.setDnaCharges(rs.getInt(2));
			}

		} catch (Exception e) {

		}
		return clinic;
	}

	public int updateDNACharge(int dnaCharge, int id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_dnacharge set dnaCharge = ? where id = " + id
				+ "";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, dnaCharge);
			result = preparedStatement.executeUpdate();

		} catch (Exception e) {

		}
		return result;
	}

	public int deleteDNACharge(int id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from apm_dnacharge where id = " + id + "";
		try {
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();

		} catch (Exception e) {

		}
		return result;
	}

	public int getClinicId(int id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select clinicid from apm_user where id = " + id + "";
		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (Exception e) {

		}

		return result;
	}

	public int updateLoginStatus(String userId) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update admin.apm_user set loginstatus=? where userid=? ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setBoolean(1, true);
			preparedStatement.setString(2, userId);

			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean getLoginStatus(String userId) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "select loginstatus from apm_user where userid='" + userId
				+ "' ";

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

	public int updateLogoutStatus(String userId) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_user set loginstatus=? where userid=? ";

		try {
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setBoolean(1, false);
			preparedStatement.setString(2, userId);

			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateAdminLoginStatus(String userid, boolean status) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_user set loginstatus=? where userid=?";

		try {

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setBoolean(1, status);
			preparedStatement.setString(2, userid);

			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getAdminClinicId(String userid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select id from apm_user where userid='" + userid + "' ";

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

	public String getAdminUserId(int selectedid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select userid from apm_user where id = " + selectedid
				+ " ";

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

	public int updateClinicByAdmin(Clinic clinic, int selectedid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_user  set clinicname = ?,clinicowner = ?,email = ?,mobile = ?,landline = ?,standalone = ?,"
				+ "hosteddb =?,onlinesingledevice = ?,onlinemultidevice = ?,diarymanagement = ?,appointmentbooking = ?,"
				+ "basicfinance = ?,fullfinance = ?,medicalrecord = ?,clinicresourcemanagement = ?,clinicpayrollmanagement = ?,"
				+ "website_url=?,owner_qualification=?,desktop=?,mobilePhone=?,ios=?,tablet=?,communication=?,report=?,assessmentForm=? where id = "
				+ selectedid + "";

		// String sql = "update apm_user set clinicname = ?,clinicowner =
		// ?,email = ?,mobile =
		// ?,landline=?,website_url=?,owner_qualification=?,clinicLogo=? where
		// id = "+selectedid+"";

		try {

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, clinic.getClinicName());
			preparedStatement.setString(2, clinic.getClinicOwner());
			preparedStatement.setString(3, clinic.getEmail());
			preparedStatement.setString(4, clinic.getMobileNo());
			preparedStatement.setString(5, clinic.getLandLine());

			preparedStatement.setBoolean(6, clinic.isStandalone());
			preparedStatement.setBoolean(7, clinic.isHostedDB());
			preparedStatement.setBoolean(8, clinic.isOnlineSingleDevice());
			preparedStatement.setBoolean(9, clinic.isOnlineMultiDevice());
			preparedStatement.setBoolean(10, clinic.isDiaryManagement());
			preparedStatement.setBoolean(11, clinic.isAppointmentBooking());
			preparedStatement.setBoolean(12, clinic.isBasicFinance());
			preparedStatement.setBoolean(13, clinic.isFullFinance());
			preparedStatement.setBoolean(14, clinic.isMedicalRecord());
			preparedStatement.setBoolean(15, clinic.isClinicResourceMngment());
			preparedStatement.setBoolean(16, clinic.isClinicPayrollMngment());

			preparedStatement.setString(17, clinic.getWebsiteUrl());
			preparedStatement.setString(18, clinic.getOwner_qualification());
			preparedStatement.setBoolean(19, clinic.isDesktop());
			preparedStatement.setBoolean(20, clinic.isMobile());
			preparedStatement.setBoolean(21, clinic.isIOS());
			preparedStatement.setBoolean(22, clinic.isTablet());
			preparedStatement.setBoolean(23, clinic.isCommunication());
			preparedStatement.setBoolean(24, clinic.isReport());
			preparedStatement.setBoolean(25, clinic.isAssessmentForms());

			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deleteApmloggedUser(String userid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from apm_logged_user where userid='" + userid
				+ "' ";

		try {

			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getFirstDiaryUserid() {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT id FROM apm_user where diarycolumnposition = 1";

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

	public String getCountryForPractitoner(String clinicID) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select country from apm_user where userid = '" + clinicID
				+ "' ";

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

	public int updateAdminEmailAndMobile(Clinic clinic) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_user set email=?,mobile=? where userid=? ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, clinic.getEmail());
			preparedStatement.setString(2, clinic.getMobileNo());
			preparedStatement.setString(3, clinic.getUserId());

			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean checkLoginUserExist(String userId) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "SELECT * FROM apm_user where userid='" + userId
				+ "' or email = '" + userId + "' or mobile='" + userId + "' ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = true;
				;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getExistUserId(String userId) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT userid FROM apm_user where userid='" + userId
				+ "' or email = '" + userId + "' or mobile='" + userId + "' ";

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

	public Location getRegisterdLication() {
		PreparedStatement preparedStatement = null;
		Location location = new Location();
		String sql = "SELECT address,postcode,city FROM apm_clinic_location where addressType = 'Registered'";

		try {

			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				location.setAddress(rs.getString(1));
				location.setPinCode(rs.getString(2));
				location.setLocationname(rs.getString(3));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return location;

	}

	public String getAutoChargeTime(String selectedid) {

		String time="";
		
		try {
			
			 String sql="select auto_charge_time from apm_user where id="+selectedid+"";
			 PreparedStatement ps=connection.prepareStatement(sql);
			 ResultSet rs=ps.executeQuery();
			 
			 while(rs.next()){
				
				  time=rs.getString(1);  
			 }
				 
		} catch (Exception e) {

			e.printStackTrace();
		}
		return time;
	}

	public boolean isSmsActive(int selectedid) {

		boolean result=false;
		try {
			
			String sql="select smscheck from apm_user where id="+selectedid+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				
				result=rs.getBoolean(1);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}

	public boolean isPaymentSMSActive(int clinicid) {
		
		boolean result=false;
		try {
			String sql="select smspayment from apm_user where id=1";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				result =rs.getBoolean(1);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		
		return result;
	}

	public String getStdChargeSetup(int clinicid) {

		String str="0";
		try {
			
			String sql="select setstdcharge from apm_user where id="+clinicid+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			
			while(rs.next()){
				 
				  str= rs.getString(1); 
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}	
	
		return str;
	}
	
	
	
	public Clinic getLettHeadDetails(String userid) {
		PreparedStatement preparedStatement = null;
		Clinic cliniclist = new Clinic();
		MasterDAO masterDAO=new JDBCMasterDAO(connection);
		// String sql = "SELECT * from apm_user where id=?";
		String sql = "select id,clinicname,clinicowner,email,mobile,landline,userid,password,standalone,hosteddb,onlinesingledevice,onlinemultidevice,"
				+ "diarymanagement,appointmentbooking,basicfinance,fullfinance,medicalrecord,clinicresourcemanagement,clinicpayrollmanagement,"
				+ "owner_qualification,website_url,clinicLogo,desktop,mobilePhone,ios,tablet,communication,report,assessmentForm,address,starttime,endtime,excess_limit,auto_charge_time,advancerenge,ipdregcharge,ipdregtype,smscheck,smspayment,registerno,setstdcharge from apm_user where userid = '"
				+ userid + "'";
		try {

			preparedStatement = connection.prepareStatement(sql);
			

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				cliniclist.setId(rs.getInt(1));
				cliniclist.setClinicName(rs.getString(2));
				cliniclist.setClinicOwner(rs.getString(3));
				cliniclist.setEmail(rs.getString(4));
				cliniclist.setMobileNo(rs.getString(5));
				cliniclist.setLandLine(rs.getString(6));
				cliniclist.setUserId(rs.getString(7));
				cliniclist.setPassword(rs.getString(8));

				Location location = getLovationAndPostCode();
				cliniclist.setLocationname(location.getLocationname());
				cliniclist.setPinCode(location.getPinCode());
				cliniclist.setAddress(location.getAddress());
				cliniclist.setContactNo(location.getContactNo());

				cliniclist.setStandalone(rs.getBoolean(9));
				cliniclist.setHostedDB(rs.getBoolean(10));
				cliniclist.setOnlineSingleDevice(rs.getBoolean(11));
				cliniclist.setOnlineMultiDevice(rs.getBoolean(12));
				cliniclist.setDiaryManagement(rs.getBoolean(13));
				cliniclist.setAppointmentBooking(rs.getBoolean(14));
				cliniclist.setBasicFinance(rs.getBoolean(15));
				cliniclist.setFullFinance(rs.getBoolean(16));
				cliniclist.setMedicalRecord(rs.getBoolean(17));
				cliniclist.setClinicResourceMngment(rs.getBoolean(18));
				cliniclist.setClinicPayrollMngment(rs.getBoolean(19));
				cliniclist.setOwner_qualification(rs.getString(20));
				cliniclist.setWebsiteUrl(rs.getString(21));
				cliniclist.setUserImageFileName(rs.getString(22));
				cliniclist.setDesktop(rs.getBoolean(23));
				cliniclist.setMobile(rs.getBoolean(24));
				cliniclist.setIOS(rs.getBoolean(25));
				cliniclist.setTablet(rs.getBoolean(26));
				cliniclist.setCommunication(rs.getBoolean(27));
				cliniclist.setReport(rs.getBoolean(28));
				cliniclist.setAssessmentForms(rs.getBoolean(29));

				// cliniclist.setAddress(rs.getString(30));
				cliniclist.setStarttime(rs.getString(31));
				cliniclist.setEndtime(rs.getString(32));
				cliniclist.setExcess(rs.getString(33));	
				cliniclist.setAdvanceTime(rs.getString(34));
				cliniclist.setAdvancerenge(rs.getString(35));
				cliniclist.setIpdregcharge(rs.getString(36));
				cliniclist.setIpdregtype(rs.getString(37));
				cliniclist.setSmscheck(rs.getBoolean(38));
				cliniclist.setSmsPayment(rs.getBoolean(39));
				cliniclist.setRegisterno(rs.getString(40));
				cliniclist.setSetupstdcharge(rs.getString(41));
			    int smscount=masterDAO.getSMSCount();	
			    cliniclist.setSmscount(smscount);
				
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return cliniclist;
	}

	public int truncateTempIpdFormData() {

		int result= 0;
		try {
			String sql="truncate apm_ipd_form_setting";
			PreparedStatement ps=connection.prepareStatement(sql);
			 result =ps.executeUpdate();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	public int saveIpdFormData(String speci, String f) {

		int result=0;
		try {
			
			String sql="insert into apm_ipd_form_setting (dept_id, field) values (?,?) ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, speci);
			ps.setString(2, f);
			
			result =ps.executeUpdate();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}

	public ArrayList<Clinic> getAllJobTitle() {
		ArrayList<Clinic> arrayList = new ArrayList<Clinic>();
		try {
			String sql = "select id,usertype from apm_role_access";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Clinic clinic = new Clinic();
				clinic.setUserType(rs.getInt(1));
				clinic.setJobtitle(rs.getString(2));
				arrayList.add(clinic);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}
	
	public ArrayList<Clinic> getAllAccessModule() {
		ArrayList<Clinic> arrayList = new ArrayList<Clinic>();
		try {
			String sql = "select id,name from apm_role_access_module";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Clinic clinic = new Clinic();
				clinic.setId(rs.getInt(1));
				clinic.setName(rs.getString(2));
				arrayList.add(clinic);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public ArrayList<Clinic> getJobTitle() {
		ArrayList<Clinic> arrayList = new ArrayList<Clinic>();
		try {
			String sql ="select id, jobtitle from job_title";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Clinic clinic = new Clinic();
				clinic.setId(rs.getInt(1));
				clinic.setName(rs.getString(2));
				arrayList.add(clinic);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public Clinic checkroleAccessName(String job_title) {
		String name = "";
		Clinic clinic = new Clinic();
		try {
			String sql = "select id,usertype from apm_role_access where usertype='"+job_title+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				clinic.setId(rs.getInt(1));
				clinic.setName(rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clinic;
	}
	
	public int setroleaccesssetiing(String name) {
		int result = 0;
		try {
			String sql ="insert into apm_role_access (usertype, diarymanagement, appointmentbooking, basicfinance, fullfinance, medicalrecord, communication, report, assessmentForm, manageclient, manageclinic, managemaster, manageprisc, manageinvst, manageipd, manageopd, apmtfinder, manageemr, expences, managemis, payroll, bloodbak, inventory, discharge) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, "0");
			preparedStatement.setString(3, "0");
			preparedStatement.setString(4, "0");
			preparedStatement.setString(5, "0");
			preparedStatement.setString(6, "0");
			preparedStatement.setString(7, "0");
			preparedStatement.setString(8, "0");
			preparedStatement.setString(9, "0");
			preparedStatement.setString(10, "0");
			preparedStatement.setString(11, "0");
			preparedStatement.setString(12, "0");
			preparedStatement.setString(13, "0");
			preparedStatement.setString(14, "0");
			preparedStatement.setString(15, "0");
			preparedStatement.setString(16, "0");
			preparedStatement.setString(17, "0");
			preparedStatement.setString(18, "0");
			preparedStatement.setString(19, "0");
			preparedStatement.setString(20, "0");
			preparedStatement.setString(21, "0");
			preparedStatement.setString(22, "0");
			preparedStatement.setString(23, "0");
			preparedStatement.setString(24, "0");
			result = preparedStatement.executeUpdate();
			
			if(result>0)
			{
				ResultSet resultSet=preparedStatement.getGeneratedKeys();
			
				while(resultSet.next()){
				     result= resultSet.getInt(1);	
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Clinic> getAllRoleAccessData(int id, String modulename) {
		ArrayList<Clinic> arrayList = new ArrayList<Clinic>();
		String temp = "";
		try {
			String sql = "select access,module_name from apm_role where moduleid='"+modulename+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				temp = rs.getString(1);
				boolean flag = getroleaccesvalue(temp,id);
				Clinic clinic =  new Clinic();
				clinic.setName(temp);
				clinic.setFlag(flag);
				if(rs.getString(2)!=null){
					clinic.setModulename(rs.getString(2));
				}else{
					clinic.setModulename(temp);
				}
				arrayList.add(clinic);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public boolean getroleaccesvalue(String temp,int id) {
		Boolean flag = false;
		try {
			String query = "select "+temp+" from apm_role_access where id="+id+"";
			PreparedStatement preparedStatement2 = connection.prepareStatement(query);
			ResultSet rs1 = preparedStatement2.executeQuery();
			
			while (rs1.next()) {
				flag = rs1.getBoolean(1);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public ArrayList<Clinic> getSpecificAccessModule(String modulename) {
		ArrayList<Clinic> arrayList = new ArrayList<Clinic>();
		String temp = "";
		try {
			String sql = "select access from apm_role where moduleid='"+modulename+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Clinic clinic = new Clinic();
				clinic.setName(rs.getString(1));
				arrayList.add(clinic);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public int updateRoleAccessSetting(String roleid, String name, String value) {
		int result = 0;
		try {
			String sql = "update apm_role_access set "+name+"="+value+" where id="+roleid+"";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getClinicIpAddress(String clinicUserid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select ipadd from apm_user where userid = '"+clinicUserid+"' ";
		
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

	public Access getGlobalAccessAccess(String jobtitle) {
		PreparedStatement preparedStatement = null;
		Access access = new Access();
		String sql = "SELECT globalaccess FROM apm_role_access where usertype='"+jobtitle+"' ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				access.setGlobalaccess(rs.getBoolean(1));
			}
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return access;
	}

	public ArrayList<Clinic> getAllRoleAccesssData(int id, String moduleid, String jobtitle) {
		ArrayList<Clinic> arrayList = new ArrayList<Clinic>();
		String temp = "";
		try {
			String sql = "select access,module_name from apm_role where moduleid='"+moduleid+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				temp = rs.getString(1);
				boolean flag = getroleaccesvalue(temp,id);
				Clinic clinic =  new Clinic();
				clinic.setName(temp);
				clinic.setFlag(flag);
				if(rs.getString(2)!=null){
					clinic.setModulename(rs.getString(2));
				}else{
					clinic.setModulename(temp);
				}
				clinic.setJobtitle(jobtitle);
				arrayList.add(clinic);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public ArrayList<Clinic> getPatients(String fromDate, String toDate, String dept, String type) {
		
	PreparedStatement ps= null;
	StringBuffer strbuff= new StringBuffer();
	try{
		strbuff.append("");
		
	}catch(Exception e){
		e.printStackTrace(); 
	}
		
		return null;
	}
	
	
	public ArrayList<Clinic> getAllMisRoleAccesssData(int id, String moduleid, String userid) {
		  ArrayList<Clinic> arrayList = new ArrayList<Clinic>();
		  String temp = "";
		  try {
		   String sql = "select access from mis_role where moduleid='"+moduleid+"'";
		   PreparedStatement preparedStatement = connection.prepareStatement(sql);
		   ResultSet rs = preparedStatement.executeQuery();
		   while (rs.next()) {
		    temp = rs.getString(1);
		    boolean flag = getmisroleaccesvalue(temp,userid);
		    if(flag==true){
//		     System.out.println("hh");
		    }
		    Clinic clinic =  new Clinic();
		    clinic.setName(temp);
		    clinic.setFlag(flag);
		    if(rs.getString(1)!=null){
		     clinic.setModulename(rs.getString(1));
		    }else{
		     clinic.setModulename(temp);
		    }
		    clinic.setUserId(userid);
		    arrayList.add(clinic);
		   }
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
		  return arrayList;
		 }
		 
		 public boolean getmisroleaccesvalue(String temp,String id) {
		  Boolean flag = false;
		  try {
		   String query = "select "+temp+" from mis_role_access where users='"+id+"'";
		   PreparedStatement preparedStatement2 = connection.prepareStatement(query);
		   ResultSet rs1 = preparedStatement2.executeQuery();
		   
		   while (rs1.next()) {
		    String s=rs1.getString(1);
		    if(s.equals("1")){
		     flag=true;
		    }
		   }
		  
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
		  return flag;
		 }

		public String getPacsIpAddress(String clinicUserid) {
			PreparedStatement preparedStatement = null;
			String result = "";
			String sql = "select pacsip from apm_user where userid = '"+clinicUserid+"' ";
			
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

		public int getoutoprisc(String clinicUserid) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "select outoprisc from apm_user where userid = '"+clinicUserid+"' ";
			
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

		public Clinic getclinicNewHospitalAccess() {
			Clinic clinic= new Clinic();
			String sql="select  hospital_admin,direct_ipd,drwise_ipd,jobtitlewise_investigation,mlc_charge,active_clinic,"
					+ "show_wardname,discharge_new,opd_invoice_post,sms_on_bedchange,sms_on_newadm,ipd_abbr_access,isledgerhosp, "
					+ "hidelogoinvst, hidelogoemr, hidelogobillinv,invst_inv_apr,direct_refund_disc,discharge_validation,ptientname_field,"
					+ "practitioner_field,address_manual,le_date,le_msg,demo_access,invest_order,isbalgopal,json_diagnosis,"
					+ "opd_paymnet_sms,ipd_payment_sms,advance_payment_sms,ref_payment_sms,other_payment_invoice,"
					+ "prisc_deliver_return,auto_generic_name,prisc_location_list,prisc_print,direct_prisc,invest_savenprint,"
					+ "prisc_savenprint,prachi_clinic,opening_closeing_on,opening_locations,newdischargecard,invoice_date_modify,phar_print_seq,"
					+ "package_access,discharge_msg_hs,barcode_productname_show,invoice_groupby,invoice_charge_seqno,opd_tp_zero_invoice,"
					+ "release_notes_upload,sms_senderid,new_aureus_discard,grn_to_prisc_location,emr_vitals_show,"
					+ "opd_video_icon_show,deleted_invst_charge,hidecalinpoprint,invoice_default_note,disc_approve_sms,ot_patient_sms,"
					+ "ot_surgeon_sms,pcsadmin,create_invoice_selected_charges,sms_reg_patient,phar_batchwise_sale,followupday,"
					+ "sms_access,vermanh,wardhazp,maindash_graph,opd_recep_sp_list,opd_telemed,vaci_sms_7day,def_vend_ledg_ser,sms_cancel_appointment,"
					+ "del_ipd_data_cancel_inv,analytics_template,lmh_po_discount,isclinic,isclinic_use_yuvi,clinicname,landline "
					+ " from apm_user where id=1";
			PreparedStatement ps= null;
			try{
				ps= connection.prepareStatement(sql);
				ResultSet rs= ps.executeQuery();
				while(rs.next()){
					clinic.setShow_hospital_admin(rs.getBoolean(1));
					clinic.setDirect_ipd(rs.getBoolean(2));
					clinic.setDrwise_ipd(rs.getBoolean(3));
					clinic.setJobtitlewise_investigation(rs.getBoolean(4));
					clinic.setMlc_charge(rs.getInt(5));
					clinic.setActive_clinic(rs.getBoolean(6));
					clinic.setShow_wardname(rs.getBoolean(7));
					clinic.setDischarge_new(rs.getBoolean(8));
					clinic.setShow_unpost(rs.getBoolean(9));
					clinic.setSms_on_bedchange(rs.getBoolean(10));
					clinic.setSms_on_newadm(rs.getBoolean(11));
					clinic.setIpd_abbr_access(rs.getInt(12));
					clinic.setIsledgerhosp(rs.getBoolean(13));
					clinic.setHidelettinvst(rs.getBoolean(14));
					clinic.setHidelettemr(rs.getBoolean(15));
					clinic.setHidelettbillinv(rs.getBoolean(16));
					clinic.setInvst_inv_apr(rs.getBoolean(17));
					clinic.setDirect_refund_disc(rs.getBoolean(18));
					clinic.setDischarge_validation(rs.getInt(19));
					clinic.setPatientname_field(rs.getString(20));
					clinic.setPractitonername_field(rs.getString(21));
					clinic.setAddress_manual(rs.getInt(22));
					clinic.setLe_date(rs.getString(23));
					clinic.setLe_msg(rs.getString(24));
					clinic.setDemo_access(rs.getBoolean(25));
					clinic.setInvest_order(rs.getBoolean(26));
					clinic.setBalgopal(rs.getBoolean(27));
					clinic.setJson_diagnosis(rs.getBoolean(28));
					clinic.setOpd_payamnt_sms(rs.getBoolean(29));
					clinic.setIpd_payamnt_sms(rs.getBoolean(30));
					clinic.setAdv_payamnt_sms(rs.getBoolean(31));
					clinic.setRefund_payamnt_sms(rs.getBoolean(32));
					clinic.setOther_payamnt_sms(rs.getBoolean(33));
					clinic.setPrisc_deliver_return(rs.getBoolean(34));
					clinic.setAuto_generic_name(rs.getBoolean(35));
					clinic.setPrisc_location_list(rs.getBoolean(36));
					clinic.setPrisc_print(rs.getBoolean(37));
					clinic.setDirect_prisc(rs.getBoolean(38));
					clinic.setInvest_savenprint(rs.getBoolean(39));
					clinic.setPrisc_savenprint(rs.getBoolean(40));
					clinic.setPrachi_clinic(rs.getBoolean(41));
					clinic.setOpening_closeing_on(rs.getBoolean(42));
					clinic.setOpening_locations(rs.getString(43));
					clinic.setNewdischargecard(rs.getBoolean(44));
					clinic.setInvoice_date_modify(rs.getBoolean(45));
					clinic.setPhar_print_seq(rs.getBoolean(46));
					clinic.setPackage_access(rs.getBoolean(47));
					clinic.setDischarge_msg_hs(rs.getBoolean(48));
					clinic.setBarcode_productname_show(rs.getBoolean(49));
					clinic.setInvoice_groupby(rs.getBoolean(50));
					clinic.setInvoice_charge_seqno(rs.getBoolean(51));
					clinic.setOpd_tp_zero_invoice(rs.getBoolean(52));
					clinic.setRelease_notes_upload(rs.getBoolean(53));
					clinic.setSms_senderid(rs.getString(54));
					clinic.setNew_aureus_discard(rs.getBoolean(55));
					clinic.setGrn_to_prisc_location(rs.getInt(56));
					clinic.setEmr_vitals_show(rs.getBoolean(57));
					clinic.setOpd_video_icon_show(rs.getBoolean(58));
					clinic.setDeleted_invst_charge(rs.getBoolean(59));
					clinic.setHidecalinpoprint(rs.getBoolean(60));
					clinic.setInvoice_default_note(DateTimeUtils.isNull(rs.getString(61)));
					clinic.setDisc_approve_sms(rs.getBoolean(62));
					clinic.setOt_patient_sms(rs.getBoolean(63));
					clinic.setOt_surgeon_sms(rs.getBoolean(64));
					clinic.setPcsadmin(rs.getBoolean(65));
					clinic.setCreate_invoice_selected_charges(rs.getBoolean(66));
					clinic.setSms_reg_patient(rs.getBoolean(67));
					clinic.setPhar_batchwise_sale(rs.getBoolean(68));
					clinic.setOpdfollowup(rs.getString(69));
					clinic.setSms_access(rs.getBoolean(70));
					clinic.setVermanh(rs.getBoolean(71));
					clinic.setWardhazp(rs.getBoolean(72));
					clinic.setMaindash_graph(rs.getBoolean(73));
					clinic.setOpd_recep_sp_list(rs.getBoolean(74));
					clinic.setOpd_telemed(rs.getBoolean(75));
					clinic.setVaci_sms_7day(rs.getBoolean(76));
					clinic.setDefaultVendorServices(DateTimeUtils.isNull(rs.getString(77)));
					clinic.setSms_cancel_appointment(rs.getBoolean(78));
					clinic.setDel_ipd_data_cancel_inv(rs.getBoolean(79));
					clinic.setAnalytics_template(rs.getBoolean(80));
					clinic.setLmh_po_discount(rs.getBoolean(81));
					clinic.setSmallClinic(rs.getBoolean(82));
					clinic.setIsclinic_use_yuvi(rs.getBoolean(83));
					clinic.setClinicName(rs.getString(84));
					clinic.setLandLine(rs.getString(85));
				}
				
			}catch (Exception e) {
				
				e.printStackTrace();
			}
			return clinic;
		}

		public int getClinicDeactiveDays(Connection connection2, LoginInfo loginInfo) {
			int res=17;
			String clinicid= loginInfo.getClinicUserid();
			PreparedStatement ps=null;
			String sql="select  expirey_date from his_hospital_management where clinicid='"+clinicid+"'";
			try {
				
				ps= connection2.prepareStatement(sql);
				ResultSet rs= ps.executeQuery();
				while(rs.next()){
					String date= rs.getString(1);
					if(date==null){
						date="";
					}
					if(!date.equals("")){
						DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					      Calendar cal = Calendar.getInstance();
					      String todaysdate = dateFormat.format(cal.getTime());  
					      
					      Date expdate=new SimpleDateFormat("yyyy-MM-dd").parse(date);
					      Date today= new SimpleDateFormat("yyyy-MM-dd").parse(todaysdate);
							
					      long diff=expdate.getTime()- today.getTime();
					      long difference= (((diff / (1000*60*60*24))));
					      res= (int)difference;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return res;
		}

		public String getJobtile(String userid) {
			String res="";
			PreparedStatement ps= null;
			try {
				String sql=" select jobtitle from apm_user where userid ='"+userid+"' ";
				ps= connection.prepareStatement(sql);
				ResultSet rs= ps.executeQuery();
				while(rs.next()){
					res=rs.getString(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return res;
		}

		public int getwardforcharge(String clinicUserid) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "select wardforcharge from apm_user where userid = '"+clinicUserid+"' ";
			
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

		public int getIsKunal(String clinicUserid) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "select iskunal from apm_user where userid = '"+clinicUserid+"' ";
			
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

		public String getpreparedby(String invoiceid) {
			PreparedStatement preparedStatement = null;
			String result = "";
			String sql = "select prepareuserid from apm_invoice where id = '"+invoiceid+"' ";
			
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

		public int getPharmacyPagelimit() {
			int res=0;
			try {
				PreparedStatement ps= connection.prepareStatement("select pagelimit from apm_pharmacy_user where id=1");
				ResultSet rs=ps.executeQuery();
				while(rs.next()){
					res= rs.getInt(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return res;
		}

		public Clinic getLoginExpiry() {
			Clinic clinic =new Clinic();
	String sql="select le_date,le_msg from apm_user where id=1";
			PreparedStatement ps= null;
			try{
				ps= connection.prepareStatement(sql);
				ResultSet rs= ps.executeQuery();
				while(rs.next()){
					clinic.setLe_date(rs.getString(1));
					clinic.setLe_msg(rs.getString(2));
				}
		}catch (Exception e) {
				e.printStackTrace();
		}
			return clinic;
		}

		public int updateHospName(String hospname) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "update apm_user set clinicname='"+hospname+"' where id = 1 ";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				result = preparedStatement.executeUpdate();
			}catch (Exception e) {
				// TODO: handle exception
			}
			return result;
		}

		public ArrayList<Clinic> getSMSCountList(String month_filter, String year_filter) {
			ArrayList<Clinic> arrayList = new ArrayList<Clinic>();
			try {
				StringBuffer buffer = new StringBuffer();
				buffer.append("select id, count, month, year from sms_month_count ");
				buffer.append("where year='"+year_filter+"' ");
				if(!month_filter.equals("00")){
					buffer.append("and month='"+month_filter+"' ");
				}
				PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					Clinic clinic = new Clinic();
					clinic.setId(rs.getInt(1));
					clinic.setCounts(rs.getInt(2));
					clinic.setMonth(rs.getString(3));
					clinic.setYear(rs.getString(4));
					arrayList.add(clinic);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return arrayList;
		}

		public int switchAccessOfClinic(String value, String colname) {
			int res=0;
			try {
				PreparedStatement ps = connection.prepareStatement("  update apm_user set "+colname+" =? where id=1 ");
				ps.setString(1, value);
				res=ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return res;
		}

		public int saveLocation1(int selectedid, Clinic clinic) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "update apm_clinic_location set address=? where id="+selectedid+"";

			try {
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, clinic.getAddress());
				result = preparedStatement.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			}

			return result;
		}

		public ArrayList<Clinic> getAllhospitalName() {
			 ArrayList<Clinic> arrayList = new ArrayList<Clinic>();
			  try {
			   String sql = "select userid,linkaddress,islogo from admin.apm_user where manas=1";
			   PreparedStatement preparedStatement = connection.prepareStatement(sql);
			   ResultSet rs = preparedStatement.executeQuery();
			   while (rs.next()) {
			   Clinic clinic =new Clinic();
			   clinic.setUserId(rs.getString(1));
			   clinic.setLinkaddress(rs.getString(2));
			   clinic.setId(rs.getInt(3));
			   arrayList.add(clinic);
			   }
			  } catch (Exception e) {
			   e.printStackTrace();
			  }
			  return arrayList;
			 }
	

		public Clinic getCliniclistDetailsByUserId(String userid) {
			PreparedStatement preparedStatement = null;
			Clinic cliniclist = new Clinic();
			MasterDAO masterDAO=new JDBCMasterDAO(connection);
			// String sql = "SELECT * from apm_user where id=?";
			String sql = "select id,clinicname,clinicowner,email,mobile,landline,userid,password,standalone,hosteddb,onlinesingledevice,onlinemultidevice,"
					+ "diarymanagement,appointmentbooking,basicfinance,fullfinance,medicalrecord,clinicresourcemanagement,clinicpayrollmanagement,"
					+ "owner_qualification,website_url,clinicLogo,desktop,mobilePhone,ios,tablet,communication,report,assessmentForm,address,starttime,endtime,"
					+ "excess_limit,auto_charge_time,advancerenge,ipdregcharge,ipdregtype,smscheck,smspayment,registerno,setstdcharge,withpayment,withoutpayment,"
					+ "invoice_date,isshowdiscount, showinvestigation, criticalvaluesms,medtreatgiven,gstn_no,stock_log,bdaysms,immusms,f_diagnosis_discharge,seq_no_gen,"
					+ "fromtime,totime,remove_procurement,modify_disc,consultsms,show_wardname,opd_invoice_post,sms_on_bedchange,sms_on_newadm,"
					+ "ipd_abbr_access, hidelogoinvst, hidelogoemr, hidelogobillinv,le_msg,smscheckdoctor,auto_generic_name,default_phar_location,"
					+ "direct_prisc,invest_savenprint,prisc_savenprint,followupday,sms_access "
//					+ "from apm_user ";
			+ "from "+userid+".apm_user where userid = '"+ userid + "'";
			try {

				preparedStatement = connection.prepareStatement(sql);
				

				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {

					cliniclist.setId(rs.getInt(1));
					cliniclist.setClinicName(rs.getString(2));
					cliniclist.setClinicOwner(rs.getString(3));
					cliniclist.setEmail(rs.getString(4));
					cliniclist.setMobileNo(rs.getString(5));
					cliniclist.setLandLine(rs.getString(6));
					cliniclist.setUserId(rs.getString(7));
					cliniclist.setPassword(rs.getString(8));

					Location location = getLovationAndPostCode();
					cliniclist.setLocationname(location.getLocationname());
					cliniclist.setPinCode(location.getPinCode());
//					cliniclist.setAddress(location.getAddress());
					cliniclist.setContactNo(location.getContactNo());

					cliniclist.setStandalone(rs.getBoolean(9));
					cliniclist.setHostedDB(rs.getBoolean(10));
					cliniclist.setOnlineSingleDevice(rs.getBoolean(11));
					cliniclist.setOnlineMultiDevice(rs.getBoolean(12));
					cliniclist.setDiaryManagement(rs.getBoolean(13));
					cliniclist.setAppointmentBooking(rs.getBoolean(14));
					cliniclist.setBasicFinance(rs.getBoolean(15));
					cliniclist.setFullFinance(rs.getBoolean(16));
					cliniclist.setMedicalRecord(rs.getBoolean(17));
					cliniclist.setClinicResourceMngment(rs.getBoolean(18));
					cliniclist.setClinicPayrollMngment(rs.getBoolean(19));
					cliniclist.setOwner_qualification(rs.getString(20));
					cliniclist.setWebsiteUrl(rs.getString(21));
					cliniclist.setUserImageFileName(rs.getString(22));
					cliniclist.setClinicLogo(rs.getString(22));
					cliniclist.setDesktop(rs.getBoolean(23));
					cliniclist.setMobile(rs.getBoolean(24));
					cliniclist.setIOS(rs.getBoolean(25));
					cliniclist.setTablet(rs.getBoolean(26));
					cliniclist.setCommunication(rs.getBoolean(27));
					cliniclist.setReport(rs.getBoolean(28));
					cliniclist.setAssessmentForms(rs.getBoolean(29));

					// cliniclist.setAddress(rs.getString(30));
					cliniclist.setStarttime(rs.getString(31));
					cliniclist.setEndtime(rs.getString(32));
					cliniclist.setExcess(rs.getString(33));	
					cliniclist.setAdvanceTime(rs.getString(34));
					cliniclist.setAdvancerenge(rs.getString(35));
					cliniclist.setIpdregcharge(rs.getString(36));
					cliniclist.setIpdregtype(rs.getString(37));
					cliniclist.setSmscheck(rs.getBoolean(38));
					cliniclist.setSmsPayment(rs.getBoolean(39));
					cliniclist.setRegisterno(rs.getString(40));
					cliniclist.setSetupstdcharge(rs.getString(41));
					cliniclist.setWithpayment(rs.getString(42));
					cliniclist.setWithoutpayment(rs.getString(43));
					cliniclist.setInvoice_date(rs.getString(44));
					cliniclist.setDiscount_show(rs.getString(45));
					
				    cliniclist.setInvestigation_show(rs.getString(46));
				    cliniclist.setCriticalvaluesms(rs.getBoolean(47));
				    cliniclist.setMedtreatgiven(rs.getBoolean(48));
				    //lokesh
				    if(rs.getString(49)!=null){
				    cliniclist.setGstn_no(rs.getString(49));
				    }else{
				    	cliniclist.setGstn_no("");
				    }
				    cliniclist.setStock_log(rs.getBoolean(50));
				    int smscount=masterDAO.getSMSCount();	
				    cliniclist.setSmscount(smscount);
				    cliniclist.setBdaysms(rs.getBoolean(51));
				    cliniclist.setImmusms(rs.getBoolean(52));
				    cliniclist.setF_diagnosis_discharge(rs.getBoolean(53));
				    cliniclist.setSeq_no_gen(rs.getBoolean(54));
				    cliniclist.setFromtime(rs.getString(55));
				    cliniclist.setTotime(rs.getString(56));
				    cliniclist.setRemoveprocurement(rs.getBoolean(57));
				    cliniclist.setModify_disc(rs.getBoolean(58));
				    cliniclist.setSmsVisitingConslt(rs.getBoolean(59));
				    //  15/12/2018 show ward name access wise
				    cliniclist.setShow_wardname(rs.getBoolean(60));
				    //  14/12/2018 show book with payment unposted
				    cliniclist.setShow_unpost(rs.getBoolean(61));
				    //  20/12/2018 sms dr on bedchange and new admission
				    cliniclist.setSms_on_bedchange(rs.getBoolean(62));
				    cliniclist.setSms_on_newadm(rs.getBoolean(63));
				    //  08/01/19 show new ipd id
				    cliniclist.setShow_new_ipd_id(rs.getBoolean(64));
				    //  14/01/2019 hide letterhead access
				    cliniclist.setHidelettinvst(rs.getBoolean(65));
				    cliniclist.setHidelettemr(rs.getBoolean(66));
				    cliniclist.setHidelettbillinv(rs.getBoolean(67));
				    cliniclist.setLe_msg(rs.getString(68));
				    cliniclist.setSmscheckdoctor(rs.getBoolean(69));
				    cliniclist.setAuto_generic_name(rs.getBoolean(70));
				    cliniclist.setDefault_phar_location(""+rs.getInt(71));
				    cliniclist.setDirect_prisc(rs.getBoolean(72));
				    cliniclist.setInvest_savenprint(rs.getBoolean(73));
				    cliniclist.setPrisc_savenprint(rs.getBoolean(74));
				    cliniclist.setOpdfollowup(rs.getString(75));
				    cliniclist.setSms_access(rs.getBoolean(76));
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			return cliniclist;
		}

		public Clinic getServerData() {
			Clinic clinic =new Clinic();
			String sql="select hostname,password,port,username,databasepw from server_data";
					PreparedStatement ps= null;
					try{
						ps= connection.prepareStatement(sql);
						ResultSet rs= ps.executeQuery();
						while(rs.next()){
							clinic.setSshhostname(rs.getString(1));
							clinic.setSshpassword(rs.getString(2));
							clinic.setPort(rs.getInt(3));
							clinic.setSshuser(rs.getString(4));
							clinic.setDbpassword(rs.getString(5));
						}
				}catch (Exception e) {
						e.printStackTrace();
				}
					return clinic;
		}

		public Clinic getNewClinicAccess() {
			Clinic clinic =new Clinic();
			String sql="select id, userid, opd_chat, regional_lang,package_investigation,lmh,patient_category,"
					+ "australia,kalmegha,account_name,account_number,account_ifsc,account_upi,common_clinic_db,  "
					+ "saimed,matrusevasang,physio,amravatiden,simpliclinic,infinyte,vrundawan,bams1,mbbs,uhiddatewise,ayushman,time_utility_report,parihar,physio_ipd,sjivh,totehosp,sramhosp,markhosp,afitech,nabh,clinic_small from clinic_access where id=1";
					PreparedStatement ps= null;
					try{
						ps= connection.prepareStatement(sql);
						ResultSet rs= ps.executeQuery();
						while(rs.next()){
							clinic.setId(rs.getInt(1));
							clinic.setUserId(DateTimeUtils.isNull(rs.getString(2)));
							clinic.setOpd_chat(rs.getBoolean(3));
							clinic.setRegional_lang(rs.getString(4));
							clinic.setPackage_investigation(rs.getBoolean(5));
							clinic.setLmh(rs.getBoolean(6));
							clinic.setPatient_category(rs.getInt(7));
							clinic.setAustralia(rs.getBoolean(8));
							clinic.setKalmegha(rs.getBoolean(9));
							clinic.setAccount_name(rs.getString(10));
							clinic.setAccount_number(rs.getString(11));
							clinic.setAccount_ifsc(rs.getString(12));
							clinic.setAccount_upi(rs.getString(13));
							clinic.setCommon_clinic_db(rs.getBoolean(14));
							clinic.setSaimed(rs.getBoolean(15));
							clinic.setMatrusevasang(rs.getBoolean(16));
							clinic.setPhysio(rs.getBoolean(17));
							clinic.setAmravati(rs.getBoolean(18));
							clinic.setSimpliclinic(rs.getBoolean(19));
							clinic.setInfinyte(rs.getBoolean(20));
							clinic.setVrundawan(rs.getBoolean(21));
							clinic.setBams1(rs.getBoolean(22));
							clinic.setMbbs(rs.getBoolean(23));
							clinic.setUhiddatewise(rs.getBoolean(24));
							clinic.setAyushman(rs.getBoolean(25));	
							clinic.setTime_utility_report(rs.getBoolean(26));
							clinic.setParihar(rs.getBoolean(27));
							clinic.setPhysio_ipd(rs.getBoolean(28));
							clinic.setSjivh(rs.getBoolean(29));
							clinic.setTotehosp(rs.getBoolean(30));
							clinic.setSramhosp(rs.getBoolean(31));
							clinic.setMarkhosp(rs.getBoolean(32));
							clinic.setAfitech(rs.getBoolean(33));
							clinic.setNabh(rs.getBoolean(34));
							clinic.setClinic_small(rs.getBoolean(35));
						}
				}catch (Exception e) {
						e.printStackTrace();
				}
					return clinic;
		}

		public int switchAccessOfClinicNew(String value, String colname) {
			int res=0;
			try {
				PreparedStatement ps = connection.prepareStatement("  update clinic_access set "+colname+" =? where id=1 ");
				ps.setString(1, value);
				res=ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return res;
		}

		public int updateUserLanguageAccess(String value) {
			int result=0;
			try {
				String sql="update apm_user set user_language_access='"+value+"' ";
				PreparedStatement ps=connection.prepareStatement(sql);
				result =ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}

		@Override
		public Clinic getClinicDetails(String clinicUserid) {
			// TODO Auto-generated method stub
			//Clinic clinic = new Clinic();
			PreparedStatement preparedStatement = null;
			Clinic clinic = new Clinic();
			try {
				 
				String sql="select isclinic from apm_user where userid='"+clinicUserid+"' ";
				
				preparedStatement = connection.prepareStatement(sql);
			    ResultSet rs = preparedStatement.executeQuery();
			    
			    while(rs.next()){
			    	
			    	clinic.setIsclinic(rs.getString(1));
			    	
			    }

				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			return clinic;
		}

		@Override
		public Clinic getBankClinicDetails(String clinicUserid, String pro_userid) {
			Clinic clinic =new Clinic();
			StringBuffer buffer = new StringBuffer();
			buffer.append("select id, clinic_userid, ");
			buffer.append("account_name,account_number,account_ifsc,account_upi ");
			buffer.append("from "+clinicUserid+".clinic_account_details ");
			if(!DateTimeUtils.isNull(pro_userid).equals("")){
				buffer.append("where clinic_userid='"+pro_userid+"' ");
			}else{
				buffer.append("where clinic_userid='"+clinicUserid+"' ");
			}
			PreparedStatement ps= null;
			try{
				ps= connection.prepareStatement(buffer.toString());
				ResultSet rs= ps.executeQuery();
				while(rs.next()){
					clinic.setId(rs.getInt(1));
					clinic.setUserId(DateTimeUtils.isNull(rs.getString(2)));
					clinic.setAccount_name(rs.getString(3));
					clinic.setAccount_number(rs.getString(4));
					clinic.setAccount_ifsc(rs.getString(5));
					clinic.setAccount_upi(rs.getString(6));
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			return clinic;
		}

		@Override
		public int updateClinicBankDetails(String bankName, String accountNo, String ifscCode, String upiId, String clinicUserid, String pro_userid) {
			int res =0;
			try {
				String sql="update clinic_account_details set account_name=?,account_number=?,account_ifsc=?,account_upi=? where clinic_userid=?";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, bankName);
				preparedStatement.setString(2, accountNo);
				preparedStatement.setString(3, ifscCode);
				preparedStatement.setString(4, upiId);
				if(!DateTimeUtils.isNull(pro_userid).equals("")){
					preparedStatement.setString(5, pro_userid);
				}else{
					preparedStatement.setString(5, clinicUserid);
				}
				res = preparedStatement.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		}

		@Override
		public int insertClinicBankDetails(String bankName, String accountNo, String ifscCode, String upiId,
				String clinicUserid, String pro_userid) {
			int res =0;
			try {
				String sql="insert into clinic_account_details(clinic_userid,account_name,account_number,account_ifsc,account_upi) values(?,?,?,?,?) ";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				if(!DateTimeUtils.isNull(pro_userid).equals("")){
					preparedStatement.setString(1, pro_userid);
				}else{
					preparedStatement.setString(1, clinicUserid);
				}
				preparedStatement.setString(2, bankName);
				preparedStatement.setString(3, accountNo);
				preparedStatement.setString(4, ifscCode);
				preparedStatement.setString(5, upiId);
				res = preparedStatement.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return res;
		}

		@Override
		public Clinic getClinicCommercial(String referHospitalId, String pro_userid) {
			Clinic clinic = new Clinic();
			try {
				String sql="select id, clinic_id, hospital_id, commercial_charge, consultant_charge from admin.clinic_hospital_commercial where clinic_id=? and hospital_id=?";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, pro_userid);
				preparedStatement.setString(2, referHospitalId);
				ResultSet rs = preparedStatement.executeQuery();
				if(rs.next()) {
					clinic.setId(rs.getInt(1));
					clinic.setClinicID(rs.getString(2));
					clinic.setHospital_id(rs.getString(3));
					clinic.setCommercial_charge(rs.getString(4));
					clinic.setConsultant_charge(rs.getString(5));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return clinic;
		}

		@Override
		public boolean checkClinicCommercial(String referHospitalId, String pro_userid) {
			boolean flag = false;
			try {
				String sql="select * from admin.clinic_hosp_temp where clinic_id=? and hospital_id=?";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, pro_userid);
				preparedStatement.setString(2, referHospitalId);
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					flag = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return flag;
		}

		@Override
		public int saveClinicHospitalCommercial(String referHospitalId, String pro_userid,Clinic clinic) {
			int res =0;
			try {
				String sql="insert into admin.clinic_hosp_temp(clinic_id, hospital_id, commercial_charge) values(?,?,?) ";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, pro_userid);
				preparedStatement.setString(2, referHospitalId);
				preparedStatement.setString(3, DateTimeUtils.numberCheck(clinic.getCommercial_charge()));
				res = preparedStatement.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return res;
		}

		@Override
		public ArrayList<Clinic> getClinicTempComercial(String pro_userid) {
			ArrayList<Clinic> arrayList = new ArrayList<>();
			try {
				StringBuffer buffer = new StringBuffer();
				buffer.append("select clinic_hosp_temp.id, clinic_id, hospital_id, commercial_charge,hosp_name, city from admin.clinic_hosp_temp ");
				buffer.append("inner join admin.ipd_hospital on ipd_hospital.id = clinic_hosp_temp.hospital_id ");
				buffer.append("where clinic_id='"+pro_userid+"' ");
				PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					Clinic clinic = new Clinic();
					clinic.setId(rs.getInt(1));
					clinic.setClinicID(rs.getString(2));
					clinic.setHospital_id(rs.getString(3));
					clinic.setCommercial_charge(rs.getString(4));
					clinic.setHospitalName(rs.getString(5));
					clinic.setCity(rs.getString(6));
					arrayList.add(clinic);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return arrayList;
		}

		@Override
		public int deleteClinicHospital(String pro_userid) {
			int res =0;
			try {
				String sql="delete from admin.clinic_hosp_temp where clinic_id=? ";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, pro_userid);
				res = preparedStatement.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return res;
		}

		@Override
		public int checkClinicHospitalCommerical(String hosp_id, String pro_userid) {
			int res = 0;
			try {
				String sql="select id from admin.clinic_hospital_commercial where clinic_id=? and hospital_id=?";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, pro_userid);
				preparedStatement.setString(2, hosp_id);
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
		public int insertClinicHospitalCommercial(String hosp_id, String pro_userid, String hosp_commercial,
				String dateTime) {
			int res =0;
			try {
				String sql="insert into admin.clinic_hospital_commercial(clinic_id,hospital_id,commercial_charge,datetime)"
						+ " values(?,?,?,?)";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, pro_userid);
				preparedStatement.setString(2, hosp_id);
				preparedStatement.setString(3, hosp_commercial);
				preparedStatement.setString(4, dateTime);
				res = preparedStatement.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return res;
		}

		@Override
		public int updateClinicHospitalCommercial(String hosp_id, String pro_userid, String hosp_commercial,
				String dateTime, int result) {
			int res =0;
			try {
				String sql="update admin.clinic_hospital_commercial set commercial_charge=?,update_datetime=? where id=? ";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, hosp_commercial);
				preparedStatement.setString(2, dateTime);
				preparedStatement.setString(3, ""+result);
				res = preparedStatement.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return res;
		}

		@Override
		public Clinic getWhatsappActive(int id) {
			Clinic clinic=new Clinic();
			try {
				
				String sql="select wsms from apm_user where id=1";
				PreparedStatement ps=connection.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				
				while(rs.next()){
					
					clinic.setWsms(rs.getInt(1));
				}
				
			} catch (Exception e) {

				e.printStackTrace();
			}
			
			return clinic;
		}

		@Override
		public String getRequiredopddate(String clientId, String opddate) {
			PreparedStatement pst=null;
			String result="";
			try {
				String sql="select reqdatetime from department_opd where clientid='"+clientId+"' and commencing='"+opddate+"' limit 0,1;";
				pst=connection.prepareStatement(sql);
				ResultSet rs=pst.executeQuery();
				while(rs.next()) {
					result=rs.getString(1);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}

		@Override
		public int updateAddressForManasclinic(Clinic clinic,int selectedid) {
			int res =0;
			try {
				String sql="update apm_user set address=? where id=? ";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, clinic.getAddress());
				preparedStatement.setString(2, ""+selectedid);
				res = preparedStatement.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return res;
			
		}

		
}
