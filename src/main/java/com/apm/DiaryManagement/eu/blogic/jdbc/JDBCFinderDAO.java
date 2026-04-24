package com.apm.DiaryManagement.eu.blogic.jdbc;

import java.nio.Buffer;
import java.rmi.NoSuchObjectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.a.a.a.g.m.n;
import com.apm.Accounts.eu.entity.Accounts;
import com.apm.Appointment.eu.entity.Appointment;
import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.bi.DiaryManagementDAO;
import com.apm.DiaryManagement.eu.bi.FinderDAO;
import com.apm.DiaryManagement.eu.bi.NotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.DiaryManagement.web.form.NotAvailableSlotForm;
import com.apm.Emr.eu.bi.EmrDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCEmrDAO;
import com.apm.Emr.eu.entity.Emr;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Registration.eu.bi.ClinicDAO;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCClinicDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.Report.eu.bi.ChargesReportDAO;
import com.apm.Report.eu.blogic.jdbc.JDBCChargesReportDAO;
import com.apm.ThirdParties.eu.entity.ThirdParty;
import com.apm.common.eu.blogic.jdbc.JDBCBaseDAO;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.common.helper.LoginInfo;

public class JDBCFinderDAO extends JDBCBaseDAO implements FinderDAO {

	public JDBCFinderDAO(Connection connection) {
		this.connection = connection;

	}

	public ArrayList<NotAvailableSlot> getFinderList(String clientId, String commencing, String todate, String practId,
			String payee, Appointment appointment, String apptype, String allapmt) {
		if (appointment == null) {
			appointment = new Appointment();
		}

		PreparedStatement preparedStatement = null;
		ArrayList<NotAvailableSlot> list = new ArrayList<NotAvailableSlot>();
		// String sql = "select id,commencing,
		// diaryuserid,starttime,duration,aptmtype,notes,endtime,location,treatmentEpisodeId,apmslotid,condition_id
		// from apm_available_slot where clientid= "+clientId+" ";
		String sql = "select apm_available_slot.id,commencing, diaryuserid,starttime,duration,aptmtype,notes,endtime,location,treatmentEpisodeId,apmslotid,condition_id,apm_available_slot.status,clientid,reqdatetime,whopay,opdpmnt,drcompleted  from apm_available_slot "
				+ " where clientid is not null ";
		/*
		 * if(!clientId.equals("") && !commencing.equals("")){ sql =
		 * "select id,commencing, diaryuserid,starttime,duration,aptmtype,notes,endtime,location,treatmentEpisodeId,apmslotid,condition_id,status,clientid,reqdatetime  from apm_available_slot where clientid= "
		 * +clientId+" and commencing between '"+commencing+"'and '"+todate+"' "; } else
		 * if(!clientId.equals("")){ sql =
		 * "select id,commencing, diaryuserid,starttime,duration,aptmtype,notes,endtime,location,treatmentEpisodeId,apmslotid,condition_id,status,clientid,reqdatetime  from apm_available_slot where clientid= "
		 * +clientId+" "; } else if(!commencing.equals("")){ sql =
		 * "select id,commencing, diaryuserid,starttime,duration,aptmtype,notes,endtime,location,treatmentEpisodeId,apmslotid,condition_id,status,clientid,reqdatetime  from apm_available_slot where commencing between '"
		 * +commencing+"'and '"+todate+"' "; }
		 */
		if (DateTimeUtils.isNull(allapmt).equals("")) {

		} else {
			if (!commencing.equals("")) {
				sql = sql + " and commencing between '" + commencing + "'and '" + todate + "'  ";
			}
		}
		if (!clientId.equals("")) {
			sql = sql + " and clientid= '" + clientId + "' ";
		}
		if (!practId.equals("")) {
			sql = sql + " and diaryuserid='" + practId + "' ";
		}
		if (!payee.equals("")) {
			sql = sql + " and whopay='" + payee + "' ";
		}
		if (apptype.equals("1")) {
			sql = sql + " and appt_type=0 ";
		} else if (apptype.equals("2")) {
			sql = sql + " and appt_type=1 ";
		} else if (apptype.equals("3")) {
			sql = sql + " and appt_type=2 ";
		} else {

		}

		JDBCMasterDAO dao = new JDBCMasterDAO(connection);

		try {

			preparedStatement = connection.prepareStatement(sql);

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NotAvailableSlot availableSlot = new NotAvailableSlot();
				availableSlot.setId(rs.getInt(1));
				availableSlot.setCommencing(rs.getString(2));

				String date = DateTimeUtils.getCommencingDate1(rs.getString(2));
				availableSlot.setCommencing(date);

				String diaryUserName = getDiaryUserName(rs.getInt(3));
				availableSlot.setDiaryUser(diaryUserName);
				availableSlot.setSTime(rs.getString(4));
				availableSlot.setDuration(rs.getString(5));
				String appointmentType = getAppointmentType(rs.getInt(6));
				availableSlot.setApmttypetext(appointmentType);
				availableSlot.setNotes(rs.getString(7));
				availableSlot.setEndTime(rs.getString(8));
				availableSlot.setLocation(rs.getString(9));
				availableSlot.setTreatmentEpisodeId(rs.getString(10));
				availableSlot.setApmtSlotId(rs.getInt(11));
				availableSlot.setCondition(rs.getString(12));
				availableSlot.setStatus(rs.getString(13));
				availableSlot.setApmtType(rs.getString(6));
				availableSlot.setDiaryUserId(rs.getInt(3));
				availableSlot.setClientId(clientId);
				availableSlot.setWhopay(rs.getString(16));
				int invId = rs.getInt(17);
				int ispaid = 0;
				if (invId > 0) {
					ispaid = isPaid(invId);
				}
				if (ispaid > 0) {
					availableSlot.setPayBy("Paid");
				} else {
					availableSlot.setPayBy("Unpaid");
				}
				availableSlot.setRequestDateTime(DateTimeUtils.isNull(rs.getString(15)));
				if (!availableSlot.getRequestDateTime().equals("")) {
					availableSlot.setRequestDateTime(
							DateTimeUtils.getCommencingDate1(availableSlot.getRequestDateTime().split(" ")[0]) + " "
									+ availableSlot.getRequestDateTime().split(" ")[1]);
				}
				String te = rs.getString(10);
				if (rs.getString(10) != null) {
					if (!te.equals("0")) {
						String treatmentSessions = getTreatmentEpisodeName(availableSlot.getId());
						availableSlot.setTreatmentSessions(treatmentSessions);
					} else {
						availableSlot.setTreatmentSessions("");
					}

				}

				boolean iscompleted = getIsCompleted(availableSlot.getId());

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date1 = sdf.parse(availableSlot.getCommencing());
				Date date2 = sdf.parse(DateTimeUtils.getSimpleDateFormat(new Date()));

				if (date1.before(date2)) {
					availableSlot.setOldata(true);
				} else {
					availableSlot.setOldata(false);

				}
				boolean flag = rs.getBoolean(18);

				if (!DateTimeUtils.isNull(appointment.getDrcompleted()).equals("")) {
					if (DateTimeUtils.isNull(appointment.getDrcompleted()).equals("1")) {
						if (rs.getBoolean(18)) {
							continue;
						}
					} else {
						if (!rs.getBoolean(18)) {
							continue;
						}
					}

				}

				ClientDAO clientDAO = new JDBCClientDAO(connection);
				Client client = clientDAO.getClientDetails(rs.getString(14));
				String clientname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();
				availableSlot.setClientName(clientname);
				availableSlot.setClientEmail(rs.getString(14));
				list.add(availableSlot);
				/*
				 * if(availableSlot.getStatus().equals("1")){
				 * availableSlot.setClientName("N/A"); list.add(availableSlot); }else{
				 * if(!rs.getBoolean(18)){ ClientDAO clientDAO = new JDBCClientDAO(connection);
				 * Client client = clientDAO.getClientDetails(rs.getString(14)); String
				 * clientname = client.getTitle() + " " + client.getFirstName() + " " +
				 * client.getLastName(); availableSlot.setClientName(clientname);
				 * 
				 * list.add(availableSlot); } }
				 */

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}

	private int isPaid(int invoiceId) {
		int res = 0;
		try {
			PreparedStatement ps = connection
					.prepareStatement(" select * from apm_charges_payment where chargeinvoiceid ='" + invoiceId + "'");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				res = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	private String getTreatmentEpisodeName(int id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		StringBuffer sql = new StringBuffer();

		sql.append(
				"SELECT apm_available_slot.usedsession,sessions,name FROM apm_available_slot inner join apm_treatment_episode on ");
		sql.append("apm_available_slot.treatmentEpisodeId = apm_treatment_episode.id where apm_available_slot.id = "
				+ id + "");

		try {
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				// 2 Session for Back Pain / 1-10
				String usedsessions = rs.getString(1);
				String sessions = rs.getString(2);
				String name = rs.getString(3);

				result = sessions + " " + "for" + " " + name + " " + "/" + usedsessions + "-" + sessions;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	private boolean getIsCompleted(int id) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "SELECT * FROM apm_invoice where appointmentid= " + id + " ";

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

	private String getAppointmentType(int id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT name FROM apm_appointment_type where id= " + id + " ";

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

	private String getDiaryUserName(int id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT firstname,lastname FROM apm_user where id= " + id + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getString(1) + " " + rs.getString(2);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<NotAvailableSlot> departmentOPDList(String department, String date, Pagination pagination,
			String category, String orderby, LoginInfo loginInfo,String primarydoc) {
		PreparedStatement preparedStatement = null;
		ArrayList<NotAvailableSlot> list = new ArrayList<NotAvailableSlot>();
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		UserProfileDAO profileDAO = new JDBCUserProfileDAO(connection);
		NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
		ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
		ArrayList<DiaryManagement> userList = new ArrayList<DiaryManagement>();
		StringBuffer sql = new StringBuffer();
		String query = "";
		if (!department.equals("")) {
			userList = notAvailableSlotDAO.getUserListwithdepartment(0, department);
		} else {
			userList = notAvailableSlotDAO.getUserList(0);
		}
		// refertodept==0
		// followup_date!=''
		sql.append(
				"select department_opd.id, commencing,starttime,abrivationid,concat(title,' ',firstname,' ',surname),clientid,apmttypetext,condition_id, ");
		sql.append(
				"opdpmnt,department_opd.whopay,department_opd.status,dna,chrgstatus,drcompleted,department_opd.arrivedstatus,opdpmnt,opdbooktime, ");
		sql.append(
				"duration,reqdatetime,mobstatus,reception_vid_verify,doctor_vid_verify,doctor_vid_reject_remark,diaryuserid,pending_remark, ");
		sql.append(
				" added_by,patient_being_seen_time,refferedfrom,dept,referredfromdept,patientcategory,refferremark,diaryusername,campArea,enrollcode,newpatient, ");
		sql.append("refertodept,followup_date,planid ");
		sql.append("from  department_opd inner join apm_patient on ");
		sql.append("apm_patient.id = department_opd.clientid ");
		sql.append("where  commencing= '" + date + "' ");

		if (!department.equals("")) {
			sql.append(" and dept=" + department + "");
		}

		if (!category.equals("0")) {
			sql.append(" and apm_patient.patientcategory='" + category + "'");
		}
		if (!primarydoc.equals("")) {
			sql.append(" and diaryuserid=" + primarydoc + " ");
		}
		if (loginInfo.isAmravati()) {
			sql.append(" order by id asc");
		} else {
			sql.append(" order by id " + orderby + "");
		}
		if (pagination != null) {
			query = pagination.getSQLQuery(sql.toString());
		}
		try {
			preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NotAvailableSlot notavailable = new NotAvailableSlot();

				notavailable.setId(rs.getInt(1));

				notavailable.setCommencing(rs.getString(2));
				notavailable.setSTime(rs.getString(3));
				notavailable.setAbrivationid(rs.getString(4));
				notavailable.setClientName(rs.getString(5));
				notavailable.setClientId(rs.getString(6));
				Client client = clientDAO.getClientDetails(notavailable.getClientId());

				String age = DateTimeUtils.getAge1(client.getDob());
				notavailable.setAgegender(age + " / " + client.getGender());
				notavailable.setMobno(client.getMobNo());
				notavailable.setApmttypetext(rs.getString(7));
				notavailable.setCondition(rs.getString(8));
				notavailable.setInvoiceid(rs.getString(9));
				notavailable.setWhopay(rs.getString(10));
				notavailable.setStatus(rs.getString(11));// check appointment completed
				notavailable.setDna(rs.getBoolean(12));
				boolean isCompleted = false;
				if (rs.getInt(13) == 1) {
					isCompleted = true;

				}
				if (isCompleted && !notavailable.isDna()) {
					notavailable.setAppointmentCompleted(isCompleted);
				}

				notavailable.setDrcompleted(rs.getInt(14));

				notavailable.setArrivedStatus(rs.getInt(15));
				notavailable.setOpdpmnt(rs.getInt(16));
				notavailable.setOpdbooktime(DateTimeUtils.isNull(rs.getString(17)));
				notavailable.setDuration(rs.getString(18));
				notavailable.setOpdbookdate(DateTimeUtils.getCommencingDate1(rs.getString(19).split(" ")[0]));
				notavailable.setMobstatusnew(rs.getInt(20));
				notavailable.setReception_vid_verify(rs.getInt(21));
				notavailable.setDoctor_vid_verify(rs.getInt(22));
				notavailable.setDoctor_vid_reject_remark(DateTimeUtils.isNull(rs.getString(23)));
				// if(rs.getInt(24)!=2){
				notavailable.setDiaryUserId(rs.getInt(24));
				// }
				notavailable.setDocid("" + notavailable.getDiaryUserId());
				notavailable.setPending_remark(DateTimeUtils.isNull(rs.getString(25)));
				notavailable.setAddedBy(rs.getString(26));
				if (DateTimeUtils.isNull(rs.getString(27)).equals("")) {
					notavailable.setPatientisseen(0);
				} else {
					notavailable.setPatientisseen(1);
				}
				if (rs.getInt(24) > 1) {
					notavailable.setDrselected("1");
					notavailable.setDoctorname(rs.getString(33));
				} else {
					notavailable.setDoctorname("");
					notavailable.setDrselected("0");
				}
				String reqdatetime = rs.getString(19);

				notavailable.setRefferedfrom(rs.getString(28));
				/*
				 * String
				 * bookdate=DateTimeUtils.getdatewithmonth(DateTimeUtils.getCommencingDate1(
				 * reqdatetime.split(" ")[0]))+" "+reqdatetime.split(" ")[1];
				 */
				String bookdate = DateTimeUtils.getCommencingDate1(reqdatetime.split(" ")[0]);
				notavailable.setDatetime(bookdate);
				/*
				 * String
				 * apptdate=DateTimeUtils.getdatewithmonth(DateTimeUtils.getCommencingDate1(
				 * notavailable.getCommencing()))+" "+notavailable.getOpdbooktime();
				 */
				String apptdate = DateTimeUtils.getCommencingDate1(notavailable.getCommencing());
				notavailable.setApptdate(apptdate);
				int duserid = profileDAO.getDiaryUserId(notavailable.getAddedBy());
				UserProfile userProfile2 = profileDAO.getUserprofileDetails(duserid);
				notavailable.setAddedBy(userProfile2.getFullname());
				notavailable.setDept(rs.getString(29));
				notavailable.setDepartmentname(DateTimeUtils.isNull(chargesReportDAO.getDepartmentName(rs.getInt(29))));
				notavailable
						.setReferredfromdept(DateTimeUtils.isNull(chargesReportDAO.getDepartmentName(rs.getInt(30))));
				notavailable.setPatientcategory(DateTimeUtils.isNull(rs.getString(31)));
				notavailable.setRefferremark(DateTimeUtils.isNull(rs.getString(32)));
				notavailable.setCampArea(rs.getString(34));
				notavailable.setEnrollcode(rs.getString(35));
				notavailable.setNewpatient(rs.getInt(36));
				notavailable.setUserList(userList);

				int referStatus = 0;
				if (!DateTimeUtils.isNull(rs.getString(37)).equals("0")) {
					referStatus = 1;
				}
				notavailable.setReferStatus(referStatus);

				int folloupGiven = 0;
				if (DateTimeUtils.isNull(rs.getString(38)).equals("")) {
					folloupGiven = checkFollowUpGiven(rs.getInt(1));
				} else {
					folloupGiven = 1;
				}
				notavailable.setFolloupGiven(folloupGiven);

				String activeplanname = getActivePlanofPatient(rs.getInt(39));
				notavailable.setActiveplan(activeplanname);
				list.add(notavailable);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private String getActivePlanofPatient(int planid) {
		PreparedStatement pst = null;
		String result = "";
		try {
			String sql = "select name from apm_appointment_type where id='" + planid + "'";
			pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {

				result = rs.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int checkFollowUpGiven(int int1) {
		int res = 0;
		try {
			String sql = "select * from sitting_followuplist where deptopd_id='" + int1 + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				res = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public String getBookedDateTime(String commencing, String clientid) {
		String res = "";
		try {
			String sql = "select reqdatetime from department_opd where clientid=" + clientid + " and commencing='"
					+ commencing + "' order by id limit 1";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				res = resultSet.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public ArrayList<Client> getFakePatientData(int patientcount, String type, String userSessionId,
			String patientAgeType, boolean iskaalmegh, String fromDate, String drId, String nextDate, int ipddata) {
		PreparedStatement preparedStatement = null;
		ArrayList<Client> list = new ArrayList<Client>();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String date = dateFormat.format(cal.getTime());
		String id = "";
		/* + "and (loginSessionId is null or loginSessionId='' or loginSessionId=?) " */

		StringBuffer buffer = new StringBuffer();
		buffer.append("select id,title,firstname,surname,mobno,email,gender,dob,address,town,country,");
		buffer.append("postcode,refrence,sourceofintro,third_party_id,third_party_name_id,occupation,expiryDate,");
		buffer.append("whopay,policyauthorzcode,policyno,knownAs,county,homeNo,workNo,emailCc,prefContactMode,");
		buffer.append("emergencyContName,emergencyContNo,patientType,middlename,oldClientId,gp_id,employer_name,");
		buffer.append("treatment_type,refered_date,policyExcess,lastModified,doctor_surgery_id,second_line_address,");
		buffer.append(
				"source_of_intro_name,note,accountnote,clinicalnote,nhs,imgname,relation,adhno,tpmemb,middlename,");
		buffer.append(
				"mbalance,abrivationid,fullname,hospitalborn,companyname, neiscardno, designation, relationofuser,");
		buffer.append(
				"unit_station, claimid, colliery, areatp,maritalsts,policyholder,mothername,fathername,birthplace,");
		buffer.append(
				"birthtime,document_name,document_data,relation,relativeNo,relativename,pincode,docType,profileImg,docImg,town_village,relativeImg,");
		buffer.append(
				"patientcategory,lmh_department, apmtDate, drname, drnameId,admissionDate,dischargeDate,diagnosis from fake_patient ");
		buffer.append("where transfer=" + type + " ");
		if (patientAgeType.equals("0")) {
			buffer.append("and FLOOR((TIMESTAMPDIFF(MONTH, STR_TO_DATE(dob, '%d/%m/%Y'), CURDATE()) / 12))<14 ");
		} else if (patientAgeType.equals("1")) {
			buffer.append("and FLOOR((TIMESTAMPDIFF(MONTH, STR_TO_DATE(dob, '%d/%m/%Y'), CURDATE()) / 12))>=14 ");
		}
		if (iskaalmegh) {
			if (ipddata == 1) {
				buffer.append("and admissionDate between '" + fromDate + "' and '" + nextDate + "'  ");
			} else {
				buffer.append("and apmtDate between '" + fromDate + "' and '" + nextDate + "'  ");
			}
			if (!drId.equals("0")) {
				buffer.append("and drnameId='" + drId + "' ");
			}
		} else {
			buffer.append("limit " + patientcount + " ");
		}

		try {

			preparedStatement = connection.prepareStatement(buffer.toString());
			// preparedStatement.setString(1, userSessionId);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Client client = new Client();
				if (id.equals("")) {
					id = rs.getString(1);
				} else {
					id = id + "," + rs.getString(1);
				}
				client.setId(rs.getInt(1));
				client.setTitle(rs.getString(2));
				client.setFirstName(rs.getString(3));
				client.setLastName(rs.getString(4));
				client.setMobNo(rs.getString(5));
				client.setEmail(rs.getString(6));
				client.setGender(rs.getString(7));
				client.setDob(rs.getString(8));
				client.setAddress(rs.getString(9));
				if (!client.getDob().equals("")) {
					String age = DateTimeUtils.getAgeyearmonthdays(rs.getString(8));
					client.setYear(age.split("~~")[0]);
					client.setMonth(age.split("~~")[1]);
					client.setDays(age.split("~~")[2]);
				}

				// String city = rs.getString(10);
				client.setCity(rs.getString(10));
				client.setTown(rs.getString(10));

				String citynew = rs.getString(10);
				citynew = citynew.charAt(0) + citynew.substring(1).toLowerCase();
				client.setCity(citynew);

				client.setCountry(rs.getString(11));
				client.setPostCode(rs.getString(12));
				client.setReference(rs.getString(13));
				client.setSourceOfIntro(rs.getString(14));
				client.setType(rs.getString(15));
				client.setTypeName(rs.getString(16));
				client.setOccupation(rs.getString(17));
				client.setExpiryDate(rs.getString(18));
				client.setWhopay(rs.getString(19));
				client.setPolicyAuthorzCode(rs.getString(20));
				client.setPolicyNo(rs.getString(21));
				client.setKnownAs(rs.getString(22));

				client.setCounty(rs.getString(23));

				String statelowercase = rs.getString(23);
				statelowercase = statelowercase.charAt(0) + statelowercase.substring(1).toLowerCase();
				client.setCounty(statelowercase);

				client.setState(rs.getString(23));
				client.setHomeNo(rs.getString(24));
				client.setWorkNo(rs.getString(25));
				client.setEmailCc(rs.getString(26));
				client.setPrefContactMode(rs.getString(27));
				client.setEmergencyContName(rs.getString(28));
				client.setEmergencyContNo(rs.getString(29));
				client.setPatientType(rs.getString(30));
				client.setMiddlename(rs.getString(31));
				client.setOldclientId(rs.getString(32));
				String gpname = rs.getString(33);
				client.setGpname(gpname);
				client.setEmployerName(rs.getString(34));
				client.setTreatmentType(rs.getString(35));
				client.setReferedDate(rs.getString(36));
				client.setPolicyExcess(rs.getString(37));
				client.setLastModified(rs.getString(38));
				client.setGptypeName(rs.getString(39));

				if (rs.getString(40) == null) {
					client.setSecondLineaddress("");
				} else {
					client.setSecondLineaddress(rs.getString(40));
				}

				client.setSourceOfIntroName(rs.getString(41));
				client.setClientNote(rs.getString(42));
				client.setAccountNote(rs.getString(43));
				client.setClinicalNote(rs.getString(44));
				client.setNhsNumber(rs.getString(45));
				client.setImageName(rs.getString(46));
				client.setRelation(rs.getString(47));
				client.setAdhno(rs.getString(48));
				client.setTpmemb(rs.getString(49));
				client.setMiddlename(rs.getString(50));
				client.setBalance(rs.getString(51));
				client.setAbrivationid(rs.getString(52));
				client.setFullname(rs.getString(53));
				client.setHospitalborn(String.valueOf(rs.getInt(54)));
				// 06/12/2018
				client.setCompname(rs.getString(55));
				client.setNeisno(rs.getString(56));
				client.setDesignationbytp(rs.getString(57));
				client.setRelationvbytpe(rs.getString(58));
				client.setUnitstation(rs.getString(59));
				client.setClaimbytp(rs.getString(60));
				client.setColliery(rs.getString(61));
				client.setAreabytp(rs.getString(62));
				client.setMaritalsts(rs.getString(63));
				client.setPolicyholder(rs.getString(64));
				client.setMothername(rs.getString(65));
				client.setFathername(rs.getString(66));
				client.setBirthplace(rs.getString(67));
				client.setBirthtime(rs.getString(68));
				client.setDocumentID(DateTimeUtils.isNull(rs.getString(69)));
				client.setDocumentValue(DateTimeUtils.isNull(rs.getString(70)));
				client.setAge1(DateTimeUtils.getAge1(rs.getString(8)));

				client.setRelation(rs.getString(71));
				client.setRelativeno(rs.getString(72));
				client.setRelativename(rs.getString(73));
				client.setPincode(rs.getString(74));
				client.setDocType(rs.getString(75));
				client.setProfileImg(rs.getString(76));
				client.setDocImg(rs.getString(77));
				client.setTown_village(rs.getString(78));
				client.setRelativeImg(rs.getString(79));
				client.setPatientcategory(rs.getString(80));
				client.setLmh_department(rs.getString(81));

				client.setApmtDate(rs.getString(82));
				client.setDrname(rs.getString(83));
				client.setDrnameId(rs.getString(84));
				// admissionDate,dischargeDate,diagnosis
				client.setAdmissiondate(rs.getString(85));
				client.setDischargedate(rs.getString(86));
				client.setDiagnosis(rs.getString(87));
				client.setSelectedids(id);

				// int res = updateFakePatient(rs.getInt(1),date,userSessionId);

				list.add(client);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	private int updateFakePatient(int int1, String date, String userSessionId) {
		int res = 0;
		try {
			String sql = "update fake_patient set loginSessionId=?, tempDateTime=? where id=? ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userSessionId);
			preparedStatement.setString(2, date);
			preparedStatement.setString(3, "" + int1);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public String generateAbrivationId(LoginInfo loginInfo, String currentDate) {

		String abrivationid = "";
		try {

			ClientDAO clientDAO = new JDBCClientDAO(connection);
			String cdate = "";
			if (currentDate == null) {
				cdate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			} else {
				cdate = currentDate;
			}

//		String cdate="2021-11-30";
			boolean checkifseq = clientDAO.checkifSequenceExist(cdate);

			String clinicabrivation = clientDAO.getClinicAbrivation(loginInfo.getClinicid());
//		String clinicabrivation ="DC";
			String tempd[] = cdate.split("-");
			String y = tempd[0];
			String m = tempd[1];
			String d = tempd[2];
			String newseq = "";
			if (checkifseq) {

				int seqno = clientDAO.getSqeunceNumber(cdate);
				seqno++;
				int r = clientDAO.InserCdateSeq(cdate, seqno);
				// SNH170609001
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
			} else {
				int seqno = 1;
				int r = clientDAO.InserCdateSeq(cdate, seqno);
				// String seqno = clientDAO.getSqeunceNumber(cdate);
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
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return abrivationid;

	}

	@Override
	public int updatePatientTransferSts1(int id, int previousDate, String specialityid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update vspm.fake_patient set transfer=1,previousDate='" + previousDate + "' where id = " + id
				+ "";
		if (specialityid.equals("21")) {
			sql = "update vspm.fake_pedo set transfer=1,previousDate='" + previousDate + "' where id = " + id + "";
		}

		try {
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int updateModifyAppointment(String clientid, String aptid, String department) {
		PreparedStatement preparedStatement = null;
		int res = 0;
		try {
			String sql = "update department_opd set dept='" + department + "' where id=" + aptid + " and  clientId="
					+ clientid + "";
			preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public ArrayList<NotAvailableSlot> departmentOPDPreviewList(String patienttype, String department, String fromDate,
			String toDate, Pagination pagination, String category, String secondarydoc, String primarydoc,
			String referto, String orderby, String searchText, LoginInfo loginInfo,String referfrom) {

		PreparedStatement preparedStatement = null;
		ArrayList<NotAvailableSlot> list = new ArrayList<NotAvailableSlot>();
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		EmrDAO emrDAO = new JDBCEmrDAO(connection);
		UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
		ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
		StringBuffer sql = new StringBuffer();
		StringBuffer buffer = new StringBuffer();
		/*
		 * sql.
		 * append("select department_opd.id, commencing,abrivationid,concat(title,' ',firstname,' ',surname),department_opd.clientid, "
		 * ); sql.
		 * append("opdbooktime,reqdatetime, dept,patientcategory,newpatient,diaryuserid,diaryusername,seconadary_dr,opdappointment,refertodept,campArea,enrollcode "
		 * ); sql.append("from  department_opd inner join apm_patient on ");
		 * sql.append("apm_patient.id = department_opd.clientid "); sql.
		 * append("left join opd_secondary_dr on opd_secondary_dr.deptappointmentid=department_opd.id "
		 * ); sql.append("where  commencing between '"+fromDate+"' and '"+toDate+"' ");
		 * 
		 * if(!department.equals("")){ sql.append(" and dept="+department+" "); }
		 * 
		 * if(!category.equals("")) {
		 * sql.append(" and apm_patient.patientcategory='"+category+"' "); }
		 * if(searchText!=null){
		 * 
		 * sql.append("and apm_patient.fullname like('%"+searchText+"%') ");
		 * 
		 * } if(!patienttype.equals("")) {
		 * sql.append(" and newpatient="+patienttype+""); }
		 * 
		 * if(!patienttype.equals("")) { if(patienttype.equals("1")){
		 * sql.append(" and newpatient=1 "); }else{ sql.append(" and newpatient!=1 "); }
		 * //sql.append(" and newpatient="+patienttype+""); }
		 * 
		 * if(!primarydoc.equals("")){ sql.append(" and diaryuserid="+primarydoc+" "); }
		 * if(!secondarydoc.equals("")){
		 * sql.append(" and FIND_IN_SET("+secondarydoc+",seconadary_dr) "); }
		 * if(!referto.equals("")){
		 * sql.append(" and FIND_IN_SET("+referto+",refertodept) "); }
		 * sql.append(" order by department_opd.commencing,opdbooktime "+orderby+"");
		 */
		sql.append(
				"select department_opd.id,commencing,clientname,clientid,opdbooktime,reqdatetime,dept,diaryuserid,diaryusername,refertodept,newpatient,patientcategory,referredfromdept from department_opd ");
		sql.append(" inner join apm_patient on apm_patient.id = department_opd.clientid ");
		sql.append(" where commencing between '" + fromDate + "' and '" + toDate + "'");
		if (!department.equals("")) {
			sql.append(" and dept=" + department + " ");
		}

		if (!category.equals("")) {
			sql.append(" and apm_patient.patientcategory='" + category + "' ");
		}
		if (searchText != null) {

			sql.append("and clientname like('%" + searchText + "%') ");

		}
		if (!patienttype.equals("")) {
			sql.append(" and newpatient=" + patienttype + "");
		}

		if (!patienttype.equals("")) {
			if (patienttype.equals("1")) {
				sql.append(" and newpatient=1 ");
			} else {
				sql.append(" and newpatient!=1 ");
			}
			// sql.append(" and newpatient="+patienttype+"");
		}

		if (!primarydoc.equals("")) {
			sql.append(" and diaryuserid=" + primarydoc + " ");
		}
		/*
		 * if(!secondarydoc.equals("")){
		 * sql.append(" and FIND_IN_SET("+secondarydoc+",seconadary_dr) "); }
		 */
		if (!referto.equals("")) {
			sql.append(" and FIND_IN_SET(" + referto + ",refertodept) ");

		}
		if (!referfrom.equals("")) {
			sql.append(" and FIND_IN_SET(" + referfrom + ",referredfromdept) ");

		}
		if (loginInfo.isAmravati()) {
			sql.append(" order by id asc");
		} else {
			sql.append(" order by department_opd.commencing,opdbooktime " + orderby + "");
		}
		// sql.append(" order by department_opd.commencing,opdbooktime "+orderby+"");

		// String sqls = pagination.getSQLQuery(sql.toString());
		try {

			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NotAvailableSlot notavailable = new NotAvailableSlot();

				notavailable.setId(rs.getInt(1));

				notavailable.setCommencing(rs.getString(2));
				notavailable.setClientName(rs.getString(3));
				notavailable.setClientId(rs.getString(4));
				Client client = clientDAO.getClientDetails(notavailable.getClientId());
				String age = DateTimeUtils.getAge1(client.getDob());
				notavailable.setAgegender(age + " / " + client.getGender());
				notavailable.setMobno(client.getMobNo());
				notavailable.setOpdbooktime(DateTimeUtils.isNull(rs.getString(5)));
				notavailable.setOpdbookdate(DateTimeUtils.getCommencingDate1(rs.getString(6).split(" ")[0]));
				notavailable.setAddress(client.getAddress());
				String reqdatetime = rs.getString(6);
				notavailable.setAbrivationid(client.getAbrivationid());
				/*
				 * String
				 * bookdate=DateTimeUtils.getdatewithmonth(DateTimeUtils.getCommencingDate1(
				 * reqdatetime.split(" ")[0]))+" "+reqdatetime.split(" ")[1];
				 */
				String bookdate = DateTimeUtils.getCommencingDate1(reqdatetime.split(" ")[0]);
				if (loginInfo.isAmravati()) {
					notavailable.setDatetime(reqdatetime);
				} else {
					notavailable.setDatetime(bookdate);
				}
				// notavailable.setDatetime(bookdate);
				String apptdate = DateTimeUtils
						.getdatewithmonth(DateTimeUtils.getCommencingDate1(notavailable.getCommencing())) + " "
						+ notavailable.getOpdbooktime();
				notavailable.setApptdate(apptdate);
				notavailable.setPatientcategory(DateTimeUtils.isNull(client.getPatientcategory()));
				notavailable.setDepartmentname(DateTimeUtils.isNull(chargesReportDAO.getDepartmentName(rs.getInt(7))));
				notavailable.setDiaryUserId(rs.getInt(8));
				notavailable.setDiaryUser(rs.getString(9));
				notavailable.setReferto(rs.getString(10));
				notavailable.setPatienttype(rs.getString(11));
				String referedfromdept = chargesReportDAO.getDepartmentName(DateTimeUtils.convertToInteger(rs.getString(13)));
				notavailable.setReferredfromdept(referedfromdept);				/*
				 * notavailable.setDept(rs.getString(8));
				 * 
				 * 
				 * notavailable.setPatienttype(rs.getString(10));
				 * 
				 * 
				 * notavailable.setSecondarydoc(DateTimeUtils.isNull(rs.getString(13)));
				 * notavailable.setOpdpmnt(rs.getInt(14));
				 * 
				 * 
				 */

				/*
				 * String secondary_dr=getSecondaryData(""+rs.getInt(1)); String
				 * secondary_dr2=""; String temp[] = secondary_dr.split(","); for (String string
				 * : temp) { if(!string.equals(" ")){ String data=string;
				 * if(secondary_dr2.equals("")){ secondary_dr2=data; }else{
				 * secondary_dr2=secondary_dr2+", "+data; } } }
				 */

				/*
				 * boolean check = checkdeptidsecdr(rs.getInt(1)); if (check == false) { int
				 * insert = saveDeptapptid(rs.getInt(1)); }
				 */

				buffer = buffer.append(rs.getInt(1) + ",");
				notavailable.setOpdappoinmentid("" + buffer);
				// notavailable.setSecondarydoc(secondary_dr2);
				notavailable.setCampArea(client.getCampArea());
				notavailable.setEnrollcode(client.getEnrollcode());
				ArrayList<Emr> secondarylist = new ArrayList<Emr>();
				// String
				// listofsecdr=emrDAO.getsecondarydrlist(notavailable.getClientId(),""+notavailable.getOpdpmnt());
				String listofsecdr = "";
				String data[] = listofsecdr.split(",");
				if (!listofsecdr.equals("")) {
					if (data.length > 0) {
						for (String string : data) {
							Emr emr = new Emr();
							emr.setSecondarydr(userProfileDAO.getFullName(string));
							secondarylist.add(emr);
						}
					} else {
						Emr emr = new Emr();
						emr.setSecondarydr(userProfileDAO.getFullName(listofsecdr));
						secondarylist.add(emr);
					}
				}
				notavailable.setSecondarylist(secondarylist);
				
				//code for new second dr
				
				boolean check=checkseconddrexist(rs.getInt(1));
				if(!check) {
					int second_dr=saveDeptapptid(rs.getInt(1),notavailable.getClientId());
					}

				ArrayList<NotAvailableSlot> deptlist = new ArrayList<NotAvailableSlot>();
				List<String> myList = new ArrayList<String>(Arrays.asList(notavailable.getReferto().split(",")));
				for (String string : myList) {
					NotAvailableSlot availableSlot = new NotAvailableSlot();
					String dept = chargesReportDAO.getDepartmentName(DateTimeUtils.convertToInteger(string));
					availableSlot.setDepartmentname(dept);
					deptlist.add(availableSlot);
				}
				notavailable.setDeptlist(deptlist);
				list.add(notavailable);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	private boolean checkseconddrexist(int apmtid) {
		boolean deptid=false;
		try {
			
			String sql="select * from new_secondary_dr where deptappointmentid='"+apmtid+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				deptid = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deptid;
	}

	public int saveDeptapptid(int appointmentid,String clientid) {
		PreparedStatement pst = null;
		int res = 0;
		String cdate = DateTimeUtils.getUKCurrentDataTime("India");
		try {
			String sql = "insert into new_secondary_dr(deptappointmentid,secondary_dr,clientid,date) values(" + appointmentid + ",0,"+clientid+",'"+cdate+"') ";
			pst = connection.prepareStatement(sql);
			res = pst.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	private boolean checkdeptidsecdr(int deptid) {
		boolean res = false;
		try {
			String sql = "select * from new_secondary_dr where deptappointmentid='" + deptid + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				res = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<NotAvailableSlot> getSecondaryData(String opdappoinmentid) {
		StringBuffer buffer = new StringBuffer();
		PreparedStatement preparedStatement = null;

		ArrayList<NotAvailableSlot> list = new ArrayList<NotAvailableSlot>();
		try {
			// buffer.append("select secondary_dr from new_secondary_dr where
			// deptappointmentid in("+opdappoinmentid+") order by id asc ");
			buffer.append(
					"SELECT GROUP_CONCAT(concat(firstname,' ',lastname)) as secondary_dr FROM new_secondary_dr left join apm_user on new_secondary_dr.secondary_dr=apm_user.id where deptappointmentid in("
							+ opdappoinmentid + ") GROUP BY deptappointmentid   order by new_secondary_dr.id desc");

			preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NotAvailableSlot notavail = new NotAvailableSlot();
				/*
				 * String docname=getDiaryUserName(rs.getInt(1));
				 */
				notavail.setSecondarydoc(rs.getString(1));
				list.add(notavail);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int getcountOfDepartmentOPDPreview(String patienttype, String department, String fromDate, String toDate,
			String category, String secondarydoc, String primarydoc, String referto, String orderby,String referfrom) {
		PreparedStatement preparedStatement = null;
		StringBuffer sql = new StringBuffer();
		int res = 0;
		sql.append("select count(*) ");
		sql.append("from  department_opd inner join apm_patient on ");
		sql.append("apm_patient.id = department_opd.clientid ");
		// sql.append("left join opd_secondary_dr on
		// opd_secondary_dr.deptappointmentid=department_opd.id ");
		sql.append("where  commencing between '" + fromDate + "' and '" + toDate + "' ");

		if (!department.equals("")) {
			sql.append(" and dept=" + department + " ");
		}

		if (!category.equals("")) {
			sql.append(" and apm_patient.patientcategory='" + category + "' ");
		}

		if (!patienttype.equals("")) {
			if (patienttype.equals("1")) {
				sql.append(" and newpatient=1 ");
			} else {
				sql.append(" and newpatient!=1 ");
			}
			// sql.append(" and newpatient="+patienttype+"");
		}
		if (!primarydoc.equals("")) {
			sql.append(" and diaryuserid=" + primarydoc + " ");
		}
		/*
		 * if(!secondarydoc.equals("")){
		 * sql.append(" and FIND_IN_SET("+secondarydoc+",seconadary_dr) "); }
		 */
		if (!referto.equals("")) {
			sql.append(" and FIND_IN_SET(" + referto + ",refertodept) ");
		}
		if (!referfrom.equals("")) {
			sql.append(" and FIND_IN_SET(" + referfrom + ",referredfromdept) ");
		}
		sql.append(" order by department_opd.commencing,opdbooktime " + orderby + "");

		try {

			preparedStatement = connection.prepareStatement(sql.toString());
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
	public int updatePatientDepartment(String clientid, String dept) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_patient set lmh_department='" + dept + "' where id = " + clientid + "";
		try {
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int getcountOfDepartmentOPD(String department, String date, String category, String orderby) {
		StringBuffer sql = new StringBuffer();
		PreparedStatement preparedStatement = null;
		int res = 0;
		sql.append("select count(*) from  department_opd inner join apm_patient on ");
		sql.append("apm_patient.id = department_opd.clientid ");
		sql.append("where  commencing= '" + date + "' ");

		if (!department.equals("")) {
			sql.append(" and dept=" + department + "");
		}

		if (!category.equals("0")) {
			sql.append(" and apm_patient.patientcategory='" + category + "'");
		}

		sql.append(" order by department_opd.id " + orderby + "");

		try {

			preparedStatement = connection.prepareStatement(sql.toString());
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
	public void insertinsideopd_secondary_dr(String deptaptid, String opdappointmentid, String secondarydr, String date,
			String clientid) {

		PreparedStatement pstm = null;
		String sql = "insert into opd_secondary_dr(opdappointment, deptappointmentid, seconadary_dr, date,clientid)values(?,?,?,?,?)";
		try {
			pstm = connection.prepareStatement(sql);
			pstm.setString(1, opdappointmentid);
			pstm.setString(2, deptaptid);
			pstm.setString(3, secondarydr);
			pstm.setString(4, date);
			pstm.setString(5, clientid);
			int result = pstm.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	@Override
	public ArrayList<Client> getMoveFakePatientData(String ids) {

		PreparedStatement preparedStatement = null;
		ArrayList<Client> list = new ArrayList<Client>();
		String id = "";
		String sql = "select id,title,firstname,surname,mobno,email,gender,dob,address,town,country,"
				+ "postcode,refrence,sourceofintro,third_party_id,third_party_name_id,occupation,expiryDate,"
				+ "whopay,policyauthorzcode,policyno,knownAs,county,homeNo,workNo,emailCc,prefContactMode,"
				+ "emergencyContName,emergencyContNo,patientType,middlename,oldClientId,gp_id,employer_name,"
				+ "treatment_type,refered_date,policyExcess,lastModified,doctor_surgery_id,second_line_address,"
				+ "source_of_intro_name,note,accountnote,clinicalnote,nhs,imgname,relation,adhno,tpmemb,middlename,"
				+ "mbalance,abrivationid,fullname,hospitalborn,companyname, neiscardno, designation, relationofuser, "
				+ "unit_station, claimid, colliery, areatp,maritalsts,policyholder,mothername,fathername,birthplace,"
				+ "birthtime,document_name,document_data,relation,relativeNo,relativename,pincode,docType,profileImg,docImg,town_village,relativeImg,"
				+ "patientcategory,lmh_department,apmtDate, drname, drnameId " + " from fake_patient where id in ("
				+ ids + ")";

		try {

			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Client client = new Client();
				if (id.equals("")) {
					id = rs.getString(1);
				} else {
					id = id + "," + rs.getString(1);
				}
				client.setId(rs.getInt(1));
				client.setTitle(rs.getString(2));
				client.setFirstName(rs.getString(3));
				client.setLastName(rs.getString(4));
				client.setMobNo(rs.getString(5));
				client.setEmail(rs.getString(6));
				client.setGender(rs.getString(7));
				client.setDob(rs.getString(8));
				client.setAddress(rs.getString(9));
				if (!client.getDob().equals("")) {
					String age = DateTimeUtils.getAgeyearmonthdays(rs.getString(8));
					client.setYear(age.split("~~")[0]);
					client.setMonth(age.split("~~")[1]);
					client.setDays(age.split("~~")[2]);
				}

				// String city = rs.getString(10);
				// client.setCity(city);
				client.setTown(rs.getString(10));

				client.setCountry(rs.getString(11));
				client.setPostCode(rs.getString(12));
				client.setReference(rs.getString(13));
				client.setSourceOfIntro(rs.getString(14));
				client.setType(rs.getString(15));
				client.setTypeName(rs.getString(16));
				client.setOccupation(rs.getString(17));
				client.setExpiryDate(rs.getString(18));
				client.setWhopay(rs.getString(19));
				client.setPolicyAuthorzCode(rs.getString(20));
				client.setPolicyNo(rs.getString(21));
				client.setKnownAs(rs.getString(22));
				client.setCounty(rs.getString(23));
				client.setHomeNo(rs.getString(24));
				client.setWorkNo(rs.getString(25));
				client.setEmailCc(rs.getString(26));
				client.setPrefContactMode(rs.getString(27));
				client.setEmergencyContName(rs.getString(28));
				client.setEmergencyContNo(rs.getString(29));
				client.setPatientType(rs.getString(30));
				client.setMiddlename(rs.getString(31));
				client.setOldclientId(rs.getString(32));
				String gpname = rs.getString(33);
				client.setGpname(gpname);
				client.setEmployerName(rs.getString(34));
				client.setTreatmentType(rs.getString(35));
				client.setReferedDate(rs.getString(36));
				client.setPolicyExcess(rs.getString(37));
				client.setLastModified(rs.getString(38));
				client.setGptypeName(rs.getString(39));

				if (rs.getString(40) == null) {
					client.setSecondLineaddress("");
				} else {
					client.setSecondLineaddress(rs.getString(40));
				}

				client.setSourceOfIntroName(rs.getString(41));
				client.setClientNote(rs.getString(42));
				client.setAccountNote(rs.getString(43));
				client.setClinicalNote(rs.getString(44));
				client.setNhsNumber(rs.getString(45));
				client.setImageName(rs.getString(46));
				client.setRelation(rs.getString(47));
				client.setAdhno(rs.getString(48));
				client.setTpmemb(rs.getString(49));
				client.setMiddlename(rs.getString(50));
				client.setBalance(rs.getString(51));
				client.setAbrivationid(rs.getString(52));
				client.setFullname(rs.getString(53));
				client.setHospitalborn(String.valueOf(rs.getInt(54)));
				// 06/12/2018
				client.setCompname(rs.getString(55));
				client.setNeisno(rs.getString(56));
				client.setDesignationbytp(rs.getString(57));
				client.setRelationvbytpe(rs.getString(58));
				client.setUnitstation(rs.getString(59));
				client.setClaimbytp(rs.getString(60));
				client.setColliery(rs.getString(61));
				client.setAreabytp(rs.getString(62));
				client.setMaritalsts(rs.getString(63));
				client.setPolicyholder(rs.getString(64));
				client.setMothername(rs.getString(65));
				client.setFathername(rs.getString(66));
				client.setBirthplace(rs.getString(67));
				client.setBirthtime(rs.getString(68));
				client.setDocumentID(DateTimeUtils.isNull(rs.getString(69)));
				client.setDocumentValue(DateTimeUtils.isNull(rs.getString(70)));
				client.setAge1(DateTimeUtils.getAge1(rs.getString(8)));

				client.setRelation(rs.getString(71));
				client.setRelativeno(rs.getString(72));
				client.setRelativename(rs.getString(73));
				client.setPincode(rs.getString(74));
				client.setDocType(rs.getString(75));
				client.setProfileImg(rs.getString(76));
				client.setDocImg(rs.getString(77));
				client.setTown_village(rs.getString(78));
				client.setRelativeImg(rs.getString(79));
				client.setPatientcategory(rs.getString(80));
				client.setLmh_department(rs.getString(81));
				client.setApmtDate(rs.getString(82));
				client.setDrname(rs.getString(83));
				client.setDrnameId(rs.getString(84));
				client.setSelectedids(id);
				list.add(client);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	@Override
	public int updatePatientTypeAndDob(Client client) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update fake_patient set transfer=" + client.getApmtStatus() + " " + ", type=" + client.getType()
				+ " " + ", dob='" + client.getDob() + "'" + ",title='" + client.getTitle() + "', firstname='"
				+ client.getFirstName() + "', surname='" + client.getLastName() + "', address='" + client.getAddress()
				+ "', mobno='" + client.getMobNo() + "'" + ",gender='" + client.getGender() + "',county='"
				+ client.getState() + "',town='" + client.getCity() + "'  where id = " + client.getClientId() + "";
		try {
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<NotAvailableSlot> fakedepartmentOPDList(String department, String date, Pagination pagination,
			String category, String orderby) {
		PreparedStatement preparedStatement = null;
		ArrayList<NotAvailableSlot> list = new ArrayList<NotAvailableSlot>();
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		UserProfileDAO profileDAO = new JDBCUserProfileDAO(connection);
		NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
		ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
		ArrayList<DiaryManagement> userList = new ArrayList<DiaryManagement>();
		StringBuffer sql = new StringBuffer();
		if (!department.equals("")) {
			userList = notAvailableSlotDAO.getUserListwithdepartment(0, department);
		} else {
			userList = notAvailableSlotDAO.getUserList(0);
		}

		sql.append(
				"select department_opd.id, commencing,starttime,abrivationid,concat(title,' ',firstname,' ',surname),clientid,apmttypetext,condition_id, ");
		sql.append(
				"opdpmnt,department_opd.whopay,department_opd.status,dna,chrgstatus,drcompleted,department_opd.arrivedstatus,opdpmnt,opdbooktime, ");
		sql.append(
				"duration,reqdatetime,mobstatus,reception_vid_verify,doctor_vid_verify,doctor_vid_reject_remark,diaryuserid,pending_remark, ");
		sql.append(
				" added_by,patient_being_seen_time,refferedfrom,dept,referredfromdept,patientcategory,refferremark,diaryusername,refertodept ");
		sql.append("from  department_opd inner join apm_patient on ");
		sql.append("apm_patient.id = department_opd.clientid ");
		sql.append("where  commencing= '" + date + "' and fakestatus=1 ");

		if (!department.equals("")) {
			sql.append(" and dept=" + department + "");
		}

		if (!category.equals("")) {
			sql.append(" and apm_patient.patientcategory='" + category + "'");
		}

		sql.append(" order by id " + orderby + "");

		try {
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NotAvailableSlot notavailable = new NotAvailableSlot();

				notavailable.setId(rs.getInt(1));

				notavailable.setCommencing(rs.getString(2));
				notavailable.setSTime(rs.getString(3));
				notavailable.setAbrivationid(rs.getString(4));
				notavailable.setClientName(rs.getString(5));
				notavailable.setClientId(rs.getString(6));
				Client client = clientDAO.getClientDetails(notavailable.getClientId());

				String agegender = client.getAgegender();
				String age = DateTimeUtils.getAge1(client.getDob());
				notavailable.setAgegender(age + " / " + client.getGender());
				notavailable.setMobno(client.getMobNo());
				notavailable.setApmttypetext(rs.getString(7));
				notavailable.setCondition(rs.getString(8));
				notavailable.setInvoiceid(rs.getString(9));
				notavailable.setWhopay(rs.getString(10));
				notavailable.setStatus(rs.getString(11));// check appointment completed
				notavailable.setDna(rs.getBoolean(12));
				boolean isCompleted = false;
				if (rs.getInt(13) == 1) {
					isCompleted = true;

				}
				if (isCompleted && !notavailable.isDna()) {
					notavailable.setAppointmentCompleted(isCompleted);
				}

				notavailable.setDrcompleted(rs.getInt(14));

				notavailable.setArrivedStatus(rs.getInt(15));
				notavailable.setOpdpmnt(rs.getInt(16));
				notavailable.setOpdbooktime(DateTimeUtils.isNull(rs.getString(17)));
				notavailable.setDuration(rs.getString(18));
				notavailable.setOpdbookdate(DateTimeUtils.getCommencingDate1(rs.getString(19).split(" ")[0]));
				notavailable.setMobstatusnew(rs.getInt(20));
				notavailable.setReception_vid_verify(rs.getInt(21));
				notavailable.setDoctor_vid_verify(rs.getInt(22));
				notavailable.setDoctor_vid_reject_remark(DateTimeUtils.isNull(rs.getString(23)));
				if (rs.getInt(24) != 2) {
					notavailable.setDiaryUserId(rs.getInt(24));
				}
				notavailable.setDocid("" + notavailable.getDiaryUserId());
				notavailable.setPending_remark(DateTimeUtils.isNull(rs.getString(25)));
				notavailable.setAddedBy(rs.getString(26));
				if (DateTimeUtils.isNull(rs.getString(27)).equals("")) {
					notavailable.setPatientisseen(0);
				} else {
					notavailable.setPatientisseen(1);
				}
				if (rs.getInt(24) > 1) {
					notavailable.setDrselected("1");
					notavailable.setDoctorname(rs.getString(33));
				} else {
					notavailable.setDoctorname("");
					notavailable.setDrselected("0");
				}
				String reqdatetime = rs.getString(19);

				notavailable.setRefferedfrom(rs.getString(28));
				String bookdate = DateTimeUtils.getdatewithmonth(
						DateTimeUtils.getCommencingDate1(reqdatetime.split(" ")[0])) + " " + reqdatetime.split(" ")[1];
				notavailable.setDatetime(bookdate);
				String apptdate = DateTimeUtils
						.getdatewithmonth(DateTimeUtils.getCommencingDate1(notavailable.getCommencing())) + " "
						+ notavailable.getOpdbooktime();
				notavailable.setApptdate(apptdate);
				int duserid = profileDAO.getDiaryUserId(notavailable.getAddedBy());
				UserProfile userProfile2 = profileDAO.getUserprofileDetails(duserid);
				notavailable.setAddedBy(userProfile2.getFullname());
				notavailable.setDept(rs.getString(29));
				notavailable.setDepartmentname(DateTimeUtils.isNull(chargesReportDAO.getDepartmentName(rs.getInt(29))));
				notavailable
						.setReferredfromdept(DateTimeUtils.isNull(chargesReportDAO.getDepartmentName(rs.getInt(30))));
				notavailable.setPatientcategory(DateTimeUtils.isNull(rs.getString(31)));
				notavailable.setRefferremark(DateTimeUtils.isNull(rs.getString(32)));
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				String todaydate = dateFormat.format(cal.getTime());
				todaydate = DateTimeUtils.getCommencingDate1(todaydate);
//				boolean flagdept=checkreffered(notavailable.getClientId(),todaydate,department);
				notavailable.setUserList(userList);
				if (rs.getString(34).equals("0")) {
					list.add(notavailable);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private boolean checkreffered(String clientId, String todaydate, String department) {
		boolean res = false;
		try {
			String sql = "select * from department_opd where clientid=" + clientId + " and commencing='" + todaydate
					+ "' " + "and referredfromdept='" + department + "' order by id limit 1";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				res = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public String getclientidbyODMRid(String aptid) {
		String res = "";
		try {
			String sql = "select clientId from department_opd where id=" + aptid + "";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				res = resultSet.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int updatedepartmentfaketransfer(String appointment) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update department_opd set transfer=1 where id = " + appointment + "";
		try {
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int updatefakestatus(String appointment, int previousDate, int preDeptId) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update department_opd set fakestatus=1,preDate='" + previousDate + "',preDeptId='" + preDeptId
				+ "' where id = " + appointment + "";
		try {
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ArrayList<Client> getOldDepartmentList(int patientcount, String type, String department, String date,
			int preDeptId, String currentdate) {
		PreparedStatement preparedStatement = null;
		ArrayList<Client> list = new ArrayList<Client>();
		StringBuffer sql = new StringBuffer();
		String id = "";
		try {
			/*
			 * SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd"); Date
			 * d=sdf1.parse(date); Calendar cal = Calendar.getInstance(); cal.setTime(d);
			 * cal.add(Calendar.DATE, -30); String fromdate=sdf1.format(cal.getTime());
			 * 
			 * SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd"); Date
			 * d2=sdf1.parse(date); Calendar cal1 = Calendar.getInstance();
			 * cal1.setTime(d2); cal1.add(Calendar.DATE, 7); String
			 * todate=sdf2.format(cal1.getTime());
			 */

			sql.append("select department_opd.id, commencing,abrivationid,title,");
			sql.append("concat(firstname,' ',surname),dob,mobno,gender,address,clientid, ");
			sql.append("firstname,surname,town,county,followup_date ");
			sql.append(" from  department_opd inner join apm_patient on ");
			sql.append("apm_patient.id = department_opd.clientid ");
			/*
			 * sql.
			 * append("where clientid not in (select clientid from department_opd where commencing='"
			 * +date+"' ");
			 */
			/*
			 * sql.append("where clientid not in (select clientid from department_opd  ");
			 * sql.append("where commencing between '"+fromdate+"' and '"+todate+"' ");
			 * sql.append("and fakestatus=1 "); if(!department.equals("")){
			 * sql.append(" and dept="+department+" "); } sql.append(") ");
			 */

			sql.append("where followup_date='" + currentdate + "' and followupdone=0 ");

			sql.append("and fakestatus=1 ");
			if (!department.equals("")) {
				sql.append(" and dept=" + department + " ");
			}
			// sql.append("and department_opd.id>'"+preDeptId+"' ");
			sql.append("group by clientid ");
			// sql.append(" order by id asc limit "+patientcount+"");

			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				Client client = new Client();
				if (id.equals("")) {
					id = rs.getString(1);
				} else {
					id = id + "," + rs.getString(1);
				}
				client.setId(rs.getInt(1));
				client.setDate(rs.getString(2));
				client.setAbrivationid(rs.getString(3));
				client.setTitle(rs.getString(4));
				client.setFullname(rs.getString(5));
				client.setDob(rs.getString(6));
				if (!client.getDob().equals("")) {
					String age = DateTimeUtils.getAgeyearmonthdays(rs.getString(6));
					client.setYear(age.split("~~")[0]);
					client.setMonth(age.split("~~")[1]);
					client.setDays(age.split("~~")[2]);
				}
				client.setMobNo(rs.getString(7));
				client.setGender(rs.getString(8));
				client.setAddress(rs.getString(9));
				client.setClientId(rs.getString(10));

				client.setFirstName(rs.getString(11));
				client.setLastName(rs.getString(12));
				client.setCity(rs.getString(13));
				client.setState(rs.getString(14));
				client.setFollowupdate(DateTimeUtils.getCommencingDate1(rs.getString(15)));

				client.setSelectedids(id);
				list.add(client);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int saveDetails(NotAvailableSlotForm notAvailableSlotForm) {
		PreparedStatement pstm = null;
		int result = 0;
		String sql = "insert into collegeadmission(name, bloodgroup, fathername, dob, parentname, parentaddress, mobileno, "
				+ "institudename, institudeaddress, caste, education,subcaste, cet, physicsmax, physicsmark, physicsper,"
				+ " chemistrymax, chemistrymark, chemistryper, biologymax, biologymark, biologyper, englishmark, "
				+ "englishmax, englishper, totalmax, totalmark, totalper)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			pstm = connection.prepareStatement(sql);
			pstm.setString(1, DateTimeUtils.isNull(notAvailableSlotForm.getName()));
			pstm.setString(2, DateTimeUtils.isNull(notAvailableSlotForm.getBloodgroup()));
			pstm.setString(3, DateTimeUtils.isNull(notAvailableSlotForm.getFathername()));
			pstm.setString(4, DateTimeUtils.isNull(notAvailableSlotForm.getDob()));
			pstm.setString(5, DateTimeUtils.isNull(notAvailableSlotForm.getParentname()));
			pstm.setString(6, DateTimeUtils.isNull(notAvailableSlotForm.getParentaddress()));
			pstm.setString(7, DateTimeUtils.isNull(notAvailableSlotForm.getMobileno()));
			pstm.setString(8, DateTimeUtils.isNull(notAvailableSlotForm.getInstitudename()));
			pstm.setString(9, DateTimeUtils.isNull(notAvailableSlotForm.getInstitudeaddress()));
			pstm.setString(10, DateTimeUtils.isNull(notAvailableSlotForm.getCaste()));
			pstm.setString(11, DateTimeUtils.isNull(notAvailableSlotForm.getEducation()));
			pstm.setString(12, DateTimeUtils.isNull(notAvailableSlotForm.getSubcaste()));
			pstm.setString(13, DateTimeUtils.isNull(notAvailableSlotForm.getCet()));
			pstm.setString(14, DateTimeUtils.isNull(notAvailableSlotForm.getPhysicsmax()));
			pstm.setString(15, DateTimeUtils.isNull(notAvailableSlotForm.getPhysicsmark()));
			pstm.setString(16, DateTimeUtils.isNull(notAvailableSlotForm.getPhysicsper()));
			pstm.setString(17, DateTimeUtils.isNull(notAvailableSlotForm.getChemistrymax()));
			pstm.setString(18, DateTimeUtils.isNull(notAvailableSlotForm.getChemistrymark()));
			pstm.setString(19, DateTimeUtils.isNull(notAvailableSlotForm.getChemistryper()));
			pstm.setString(20, DateTimeUtils.isNull(notAvailableSlotForm.getBiologymax()));
			pstm.setString(21, DateTimeUtils.isNull(notAvailableSlotForm.getBiologymark()));
			pstm.setString(22, DateTimeUtils.isNull(notAvailableSlotForm.getBiologyper()));
			pstm.setString(23, DateTimeUtils.isNull(notAvailableSlotForm.getEnglishmark()));
			pstm.setString(24, DateTimeUtils.isNull(notAvailableSlotForm.getEnglishmax()));
			pstm.setString(25, DateTimeUtils.isNull(notAvailableSlotForm.getEnglishper()));
			pstm.setString(26, DateTimeUtils.isNull(notAvailableSlotForm.getTotalmax()));
			pstm.setString(27, DateTimeUtils.isNull(notAvailableSlotForm.getTotalmark()));
			pstm.setString(28, DateTimeUtils.isNull(notAvailableSlotForm.getTotalper()));

			result = pstm.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();

		}
		return result;

	}

	@Override
	public ArrayList<NotAvailableSlot> getadmissionlist() {
		ArrayList<NotAvailableSlot> list = new ArrayList<NotAvailableSlot>();
		PreparedStatement preparedStatement = null;
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select id,name,dob,mobileno from collegeadmission");
			preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
				notAvailableSlot.setId(rs.getInt(1));
				notAvailableSlot.setName(rs.getString(2));
				notAvailableSlot.setDob(rs.getString(3));
				notAvailableSlot.setMobileno(rs.getString(4));
				list.add(notAvailableSlot);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public NotAvailableSlot viewcollegeadmission(String id) {
		PreparedStatement preparedStatement = null;
		NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
		try {
			String sql = "select id, name, bloodgroup, fathername, dob, parentname, parentaddress, mobileno, institudename, "
					+ "institudeaddress, caste, education, subcaste, cet, physicsmax, physicsmark, physicsper, "
					+ "chemistrymax, chemistrymark, chemistryper, biologymax, biologymark, biologyper, englishmark, "
					+ "englishmax, englishper, totalmax, totalmark, totalper from collegeadmission";
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				notAvailableSlot.setId(rs.getInt(1));
				notAvailableSlot.setName(rs.getString(2));
				notAvailableSlot.setBloodgroup(rs.getString(3));
				notAvailableSlot.setFathername(rs.getString(4));
				notAvailableSlot.setDob(rs.getString(5));
				notAvailableSlot.setParentname(rs.getString(6));
				notAvailableSlot.setParentaddress(rs.getString(7));
				notAvailableSlot.setMobileno(rs.getString(8));
				notAvailableSlot.setInstitudename(rs.getString(9));
				notAvailableSlot.setInstitudeaddress(rs.getString(10));
				notAvailableSlot.setCaste(rs.getString(11));
				notAvailableSlot.setEducation(rs.getString(12));
				notAvailableSlot.setSubcaste(rs.getString(13));
				notAvailableSlot.setCet(rs.getString(14));
				notAvailableSlot.setPhysicsmax(rs.getString(15));
				notAvailableSlot.setPhysicsmark(rs.getString(16));
				notAvailableSlot.setPhysicsper(rs.getString(17));
				notAvailableSlot.setChemistrymax(rs.getString(18));
				notAvailableSlot.setChemistrymark(rs.getString(19));
				notAvailableSlot.setChemistryper(rs.getString(20));
				notAvailableSlot.setBiologymax(rs.getString(21));
				notAvailableSlot.setBiologymark(rs.getString(22));
				notAvailableSlot.setBiologyper(rs.getString(23));
				notAvailableSlot.setEnglishmark(rs.getString(24));
				notAvailableSlot.setEnglishmax(rs.getString(25));
				notAvailableSlot.setEnglishper(rs.getString(26));
				notAvailableSlot.setTotalmax(rs.getString(27));
				notAvailableSlot.setTotalmark(rs.getString(28));
				notAvailableSlot.setTotalper(rs.getString(29));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return notAvailableSlot;
	}

	@Override
	public int updatePatient(Client client1, String id) {

		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update fake_patient set title=" + client1.getTitle() + ",fullname=" + client1.getFullname()
				+ ", dob='" + client1.getDob() + "',address=" + client1.getAddress() + ",mobno=" + client1.getMobNo()
				+ " where id =" + id + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ArrayList<NotAvailableSlot> getAdditionalChargeTypeList(String deptid) {
		PreparedStatement preparedStatement = null;
		ArrayList<NotAvailableSlot> list = new ArrayList<NotAvailableSlot>();
		String sql = "SELECT id,name,chargeType FROM apm_appointment_type order by name ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
				notAvailableSlot.setId(rs.getInt(1));
				notAvailableSlot.setName(rs.getString(2));
				notAvailableSlot.setChargeType(rs.getString(3));
				list.add(notAvailableSlot);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int saveFollowUpDetails(NotAvailableSlot notAvailableSlot, String clientid, String deptid,
			String givenDate) {
		int result = 0;
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append(
					"insert into follow_up(departmentId,clientId,followupDate,followup_procedure,followupReason,givenDate)values(?,?,?,?,?)");
			PreparedStatement ps = connection.prepareStatement(buffer.toString());
			ps.setString(1, deptid);
			ps.setString(2, clientid);
			ps.setString(3, notAvailableSlot.getFollowupDate());
			ps.setString(4, notAvailableSlot.getProcedure());
			ps.setString(5, notAvailableSlot.getFollowupReason());
			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ArrayList<NotAvailableSlot> getProcedureList(String deptid, String clientid) {
		PreparedStatement preparedStatement = null;
		ArrayList<NotAvailableSlot> list = new ArrayList<NotAvailableSlot>();
		String sql = "SELECT * FROM apm_appointment_type where chargeType= ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
				notAvailableSlot.setId(rs.getInt(1));
				notAvailableSlot.setName(rs.getString(2));
				notAvailableSlot.setChargeType(rs.getString(3));
				list.add(notAvailableSlot);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public ArrayList<NotAvailableSlot> gettpNameList(String patientType) {
		PreparedStatement preparedStatement = null;
		ArrayList<NotAvailableSlot> list = new ArrayList<NotAvailableSlot>();

		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append(
					"select apm_third_party_details.id,apm_third_party_details.company_name from apm_third_party ");
			buffer.append(
					"inner join apm_third_party_details on apm_third_party_details.third_party_id = apm_third_party.id ");
			if (patientType.equals("1")) {
				buffer.append("where patientType=1 and maintp=1  ");
			} else {
				buffer.append("where patientType='" + patientType + "'  ");
			}
			preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
				notAvailableSlot.setId(rs.getInt(1));
				notAvailableSlot.setTpName(rs.getString(2));

				list.add(notAvailableSlot);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public ArrayList<NotAvailableSlot> gettpNameListAll() {
		PreparedStatement preparedStatement = null;
		ArrayList<NotAvailableSlot> list = new ArrayList<NotAvailableSlot>();

		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append(
					"select apm_third_party_details.id,apm_third_party_details.company_name from apm_third_party ");
			buffer.append(
					"inner join apm_third_party_details on apm_third_party_details.third_party_id = apm_third_party.id ");
			buffer.append("where patientType = 0 ");
			preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
				notAvailableSlot.setId(rs.getInt(1));
				notAvailableSlot.setTpName(rs.getString(2));

				list.add(notAvailableSlot);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int getFakePatientCount(int patientcount, String type, String loginsessionid) {
		int res = 0;
		PreparedStatement preparedStatement = null;
		String sql = "select count(*) from fake_patient " + "where transfer=" + type + " " + "and loginSessionId=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, loginsessionid);
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
	public int getPreDeptId(String dept, String date) {
		int res = 0;
		try {

			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			Date d = sdf1.parse(date);
			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
			cal.add(Calendar.DATE, -15);
			String fromdate = sdf1.format(cal.getTime());

			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			Date d2 = sdf1.parse(date);
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(d2);
			cal1.add(Calendar.DATE, 7);
			String todate = sdf2.format(cal1.getTime());

			StringBuffer buffer = new StringBuffer();
			buffer.append("select preDeptId from department_opd ");
			buffer.append("where preDeptId>0  ");
			buffer.append("and clientid not in (select clientid from department_opd  ");

			buffer.append("where commencing between '" + fromdate + "' and '" + todate + "' ");
			// buffer.append("where commencing='"+date+"' ");
			buffer.append("and fakestatus=1  ");
			if (!dept.equals("")) {
				buffer.append(" and dept=" + dept + " ");
			}
			buffer.append(") ");
			buffer.append("and fakestatus=1 ");
			if (!dept.equals("")) {
				buffer.append(" and dept=" + dept + " ");
			}
			buffer.append("order by id desc limit 1 ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
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
	public int getsittingData(NotAvailableSlot notAvailableSlot) {
		// TODO Auto-generated method stub\

		PreparedStatement pmt = null;
		int result = 0;

		try {

			String sql = "insert into sitting_followuplist(patient_id, dept_id, sitting, followup_date, remark, user_id, date_time, proceduremaster_id, procedure_id, deptopd_id, sitting_num, diagnosis)values(?,?,?,?,?,?,?,?,?,?,?,?)";
			pmt = connection.prepareStatement(sql);

			pmt.setString(1, notAvailableSlot.getClientId());
			pmt.setString(2, notAvailableSlot.getDepartment());
			pmt.setString(3, notAvailableSlot.getSitting());
			// pmt.setBoolean(4, notAvailableSlot.isSittingFollowup());
			pmt.setString(4, notAvailableSlot.getDate());
			pmt.setString(5, notAvailableSlot.getRemark());
			pmt.setString(6, notAvailableSlot.getUser_id());
			pmt.setString(7, notAvailableSlot.getDate_time());
			pmt.setString(8, notAvailableSlot.getAll_procedure());
			pmt.setString(9, notAvailableSlot.getProcedure_list());
			pmt.setInt(10, notAvailableSlot.getDeptOpdId());
			pmt.setString(11, notAvailableSlot.getSittingnum());
			pmt.setString(12, notAvailableSlot.getDiagnosisarea());
			result = pmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public int updateDepartmentfollowupdate(String deptopdid, String date) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update department_opd set followup_date=? where id=" + deptopdid + "";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, date);
			result = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void updateDepartmentFollowUpDone(int preDeptId) {
		try {
			String sql = "update department_opd set followupdone=1 where id='" + preDeptId + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public ArrayList<NotAvailableSlot> dailyRegistrationList(String fromdate, String todate) {
		// TODO Auto-generated method stub
		ArrayList<NotAvailableSlot> list = new ArrayList<NotAvailableSlot>();
		PreparedStatement preparedStatement = null;
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		todate = todate + " 23:59:59";

		try {

			StringBuffer sql = new StringBuffer();

			/*
			 * sql.
			 * append("select department_opd.id,commencing,abrivationid,concat(title,' ',firstname,' ',surname),department_opd.clientid, "
			 * ); sql.
			 * append("opdbooktime,reqdatetime, dept,patientcategory,newpatient,diaryuserid,diaryusername,seconadary_dr,opdappointment,refertodept,campArea,enrollcode "
			 * ); sql.append("from  department_opd inner join apm_patient on ");
			 * sql.append("apm_patient.id = department_opd.clientid ");
			 * sql.append("where  commencing between '"+fromdate+"' and '"+todate+"' ");
			 */
			sql.append(
					"select regdate,fullname,gender,mobno,address,patientcategory,lmh_department,newpatient from apm_patient inner join department_opd ");
			sql.append("on apm_patient.id=department_opd.clientid where regdate between '" + fromdate + "' and '"
					+ todate + "' ");

			/*
			 * sql.
			 * append("select regdate,clientname,dept,gender,mobno,address,patientcategory,newpatient from apm_patient inner join department_opd on "
			 * );
			 * sql.append("apm_patient.id=department_opd.clientid where regdate between '"
			 * +fromdate+"' and '"+todate+"' ");
			 */

			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();

			FinderDAO finderDAO = new JDBCFinderDAO(connection);
			while (rs.next()) {

				String deptname = departmentid(rs.getString(3));

				NotAvailableSlot notavailable = new NotAvailableSlot();

				notavailable.setCommencing(rs.getString(1));
				notavailable.setClientName(rs.getString(2));

				Client client = clientDAO.getClientDetails(notavailable.getClientId());

				String age = DateTimeUtils.getAge1(client.getDob());

				notavailable.setAgegender(age + " / " + client.getGender());
				notavailable.setMobno(rs.getString(5));
				notavailable.setAddress(rs.getString(6));
				notavailable.setPatientcategory(rs.getString(7));
				notavailable.setDepartment(deptname);
				notavailable.setNewpatient(rs.getInt(8));
				list.add(notavailable);

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return list;
	}

	private String departmentid(String string) {
		// TODO Auto-generated method stub

		PreparedStatement pmt = null;
		String name = "";

		try {

			String sql = "select discipline from apm_discipline where id='" + string + "'";
			pmt = connection.prepareStatement(sql);

			ResultSet rs = pmt.executeQuery();

			while (rs.next()) {

				name = rs.getString(1);

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return name;
	}

	@Override
	public int getcountOfDailyregistrationreport(String patienttype, String department, String fromDate, String toDate,
			String category, String secondarydoc, String primarydoc, String referto, String orderby) {
		// TODO Auto-generated method stub

		PreparedStatement preparedStatement = null;

		StringBuffer sql = new StringBuffer();
		int res = 0;
		sql.append("select count(*) ");
		sql.append("from  department_opd  ");
		sql.append("inner join apm_patient on apm_patient.id = department_opd.clientid ");
		sql.append("left join opd_secondary_dr on opd_secondary_dr.deptappointmentid=department_opd.id ");
		sql.append("where  commencing between '" + fromDate + "' and '" + toDate + "' ");

		if (!department.equals("")) {
			sql.append(" and dept=" + department + " ");
		}

		if (!category.equals("")) {
			sql.append(" and apm_patient.patientcategory='" + category + "' ");
		}

		if (!patienttype.equals("")) {
			if (patienttype.equals("1")) {
				sql.append(" and newpatient=1 ");
			} else {
				sql.append(" and newpatient!=1 ");
			}
			// sql.append(" and newpatient="+patienttype+"");
		}
		if (!primarydoc.equals("")) {
			sql.append(" and diaryuserid=" + primarydoc + " ");
		}
		if (!secondarydoc.equals("")) {
			sql.append(" and FIND_IN_SET(" + secondarydoc + ",seconadary_dr) ");
		}
		if (!referto.equals("")) {
			sql.append(" and FIND_IN_SET(" + referto + ",refertodept) ");
		}
		sql.append(" group by department_opd.clientid order by department_opd.commencing,opdbooktime " + orderby + "");

		try {

			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.last()) {
				res = rs.getRow();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;

	}

	@Override
	public ArrayList<NotAvailableSlot> dailyRegistrationreport(String patienttype, String department, String fromDate,
			String toDate, Pagination pagination, String category, String secondarydoc, String primarydoc,
			String referto, String orderby, String searchText) {
		// TODO Auto-generated method stub

		PreparedStatement preparedStatement = null;
		ArrayList<NotAvailableSlot> list = new ArrayList<NotAvailableSlot>();
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		EmrDAO emrDAO = new JDBCEmrDAO(connection);
		UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
		ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
		StringBuffer sql = new StringBuffer();

		sql.append(
				"select department_opd.id, commencing,abrivationid,concat(title,' ',firstname,' ',surname),department_opd.clientid, ");
		sql.append(
				"opdbooktime,reqdatetime, dept,patientcategory,newpatient,diaryuserid,diaryusername,seconadary_dr,opdappointment,refertodept,campArea,enrollcode ");
		sql.append("from  department_opd inner join apm_patient on ");
		sql.append("apm_patient.id = department_opd.clientid ");
		sql.append("left join opd_secondary_dr on opd_secondary_dr.deptappointmentid=department_opd.id ");
		sql.append("where  commencing between '" + fromDate + "' and '" + toDate + "'  ");

		if (!department.equals("")) {
			sql.append(" and dept=" + department + " ");
		}
		if (!patienttype.equals("")) {
			if (patienttype.equals("1")) {
				sql.append(" and newpatient=1 ");
			} else {
				sql.append(" and newpatient!=1 ");
			}
		}
		if (!primarydoc.equals("")) {
			sql.append(" and diaryuserid=" + primarydoc + " ");
		}
		if (!referto.equals("")) {
			sql.append(" and FIND_IN_SET(" + referto + ",refertodept) ");
		}

		if (!category.equals("")) {
			sql.append(" and apm_patient.patientcategory='" + category + "' ");
		}
		if (searchText != null) {
			sql.append(" and apm_patient.firstname like '" + searchText + "%' ");
		}
		/*
		 * if(!patienttype.equals("")) { sql.append(" and newpatient="+patienttype+"");
		 * }
		 */

		if (!secondarydoc.equals("")) {
			sql.append(" and FIND_IN_SET(" + secondarydoc + ",seconadary_dr) ");
		}
		sql.append(" group by clientid order by department_opd.commencing,opdbooktime " + orderby + "");

		String sqls = pagination.getSQLQuery(sql.toString());
		try {

			preparedStatement = connection.prepareStatement(sqls.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NotAvailableSlot notavailable = new NotAvailableSlot();

				notavailable.setId(rs.getInt(1));

				notavailable.setCommencing(rs.getString(2));
				notavailable.setAbrivationid(rs.getString(3));
				notavailable.setClientName(rs.getString(4));
				notavailable.setClientId(rs.getString(5));
				Client client = clientDAO.getClientDetails(notavailable.getClientId());

				String age = DateTimeUtils.getAge1(client.getDob());
				notavailable.setAgegender(age + " / " + client.getGender());
				notavailable.setMobno(client.getMobNo());
				notavailable.setAddress(client.getAddress());
				notavailable.setOpdbooktime(DateTimeUtils.isNull(rs.getString(6)));
				notavailable.setOpdbookdate(DateTimeUtils.getCommencingDate1(rs.getString(7).split(" ")[0]));
				String reqdatetime = rs.getString(7);
				/*
				 * String
				 * bookdate=DateTimeUtils.getdatewithmonth(DateTimeUtils.getCommencingDate1(
				 * reqdatetime.split(" ")[0]))+" "+reqdatetime.split(" ")[1];
				 */
				String bookdate = DateTimeUtils.getCommencingDate1(reqdatetime.split(" ")[0]);
				notavailable.setDatetime(bookdate);
				String apptdate = DateTimeUtils
						.getdatewithmonth(DateTimeUtils.getCommencingDate1(notavailable.getCommencing())) + " "
						+ notavailable.getOpdbooktime();
				notavailable.setApptdate(apptdate);
				notavailable.setDept(rs.getString(8));
				notavailable.setDepartmentname(DateTimeUtils.isNull(chargesReportDAO.getDepartmentName(rs.getInt(8))));
				notavailable.setPatientcategory(DateTimeUtils.isNull(rs.getString(9)));
				notavailable.setPatienttype(rs.getString(10));
				notavailable.setDiaryUserId(rs.getInt(11));
				notavailable.setDiaryUser(rs.getString(12));
				notavailable.setSecondarydoc(DateTimeUtils.isNull(rs.getString(13)));
				notavailable.setOpdpmnt(rs.getInt(14));
				notavailable.setReferto(rs.getString(15));
				notavailable.setCampArea(rs.getString(16));
				notavailable.setEnrollcode(rs.getString(17));
				ArrayList<Emr> secondarylist = new ArrayList<Emr>();
				String listofsecdr = emrDAO.getsecondarydrlist(notavailable.getClientId(),
						"" + notavailable.getOpdpmnt());

				String data[] = listofsecdr.split(",");
				if (!listofsecdr.equals("")) {
					if (data.length > 0) {
						for (String string : data) {
							Emr emr = new Emr();
							emr.setSecondarydr(userProfileDAO.getFullName(string));
							secondarylist.add(emr);
						}
					} else {
						Emr emr = new Emr();
						emr.setSecondarydr(userProfileDAO.getFullName(listofsecdr));
						secondarylist.add(emr);
					}
				}
				notavailable.setSecondarylist(secondarylist);

				ArrayList<NotAvailableSlot> deptlist = new ArrayList<NotAvailableSlot>();
				List<String> myList = new ArrayList<String>(Arrays.asList(notavailable.getReferto().split(",")));
				for (String string : myList) {
					NotAvailableSlot availableSlot = new NotAvailableSlot();
					String dept = chargesReportDAO.getDepartmentName(DateTimeUtils.convertToInteger(string));
					availableSlot.setDepartmentname(dept);
					deptlist.add(availableSlot);
				}
				notavailable.setDeptlist(deptlist);
				list.add(notavailable);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	@Override
	public ArrayList<NotAvailableSlot> getreferedpatientlist(String fromDate, String toDate, String searchText,
			boolean clinicid, String sclinicid, boolean isHospital, String userid, String pro_userid, int superadmin) {

		ArrayList<NotAvailableSlot> list = new ArrayList<NotAvailableSlot>();
		PreparedStatement preparedStatement = null;

		ClientDAO clientDAO = new JDBCClientDAO(connection);
		toDate = toDate + " 23:59:59";

		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select refered_patient.id,patientid,refered_hosp,referd_date,sourceclinicid,   ");
			buffer.append("hosp_name,city,commercial,deleted,confirm,patient_status,name,consultant_share, ");
			buffer.append("paid_status, total_bill_amt, paid_clinic_amt, paid_consulatant_amt,clinic_refer_userid, ");
			buffer.append("paid_clinic_share,patient_disease,refered_userid,sourcedocid ");
			buffer.append("from admin.refered_patient ");
			buffer.append(
					"inner join admin.ipd_hospital on admin.refered_patient.refered_hosp = admin.ipd_hospital.id ");
			buffer.append(
					"left join admin.patient_status_list on patient_status_list.id = refered_patient.patient_status ");
			if (clinicid) {
				if (!DateTimeUtils.isNull(pro_userid).equals("")) {
					// Pro_Userid having data means its common database clinic or hospital
					if (superadmin == 1) {
						// For super admin user showing all clinic or ambulance user data
						buffer.append("where sourceclinicid = '" + sclinicid + "' ");
					} else {
						// Data show according to clinic or ambulance within common database
						buffer.append("where clinic_refer_userid = '" + pro_userid + "' ");
					}
				} else {
					// Pro_Userid doesn't having any data means its separate database clinic or
					// hospital
					// buffer.append("where sourceclinicid = '"+sclinicid+"' ");
					buffer.append("where refered_userid = '" + userid + "' ");
				}
			} else {
				buffer.append("where deleted=0 ");
				if (isHospital) {
					// Yuvicare used
					buffer.append(" and referdhosp_clinicid ='" + sclinicid + "' ");
				} else {
					// Yuvicare Not Used
					buffer.append("and hospital_userid='" + userid + "' ");
				}
			}
			buffer.append("and referd_date between '" + fromDate + "' and '" + toDate + "' ");
			buffer.append("order by refered_patient.id desc ");

			preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				NotAvailableSlot notavailable = new NotAvailableSlot();
				notavailable.setId(rs.getInt(1));
				notavailable.setClientId(rs.getString(2));
				notavailable.setReferetohospital(rs.getString(6));
				notavailable.setReferCity(rs.getString(7));
				notavailable.setReferdate(DateTimeUtils.getIndianDateTimeFormat(rs.getString(4)));
				notavailable.setSourceclinic_id(rs.getString(5));
				Client client = clientDAO.referClientDetails(notavailable.getClientId(),
						notavailable.getSourceclinic_id());

				String age = DateTimeUtils.getAge1(client.getDob());
				notavailable.setAgegender(age + " / " + client.getGender());
				notavailable.setMobno(client.getMobNo());
				notavailable.setAddress(client.getAddress());
				notavailable.setClientName(client.getFullname());
				notavailable.setCity(client.getCity());
				notavailable.setCommercial(DateTimeUtils.numberCheck(rs.getString(19)));

				notavailable.setDeleted(rs.getInt(9));
				notavailable.setConfirm(rs.getInt(10));
				notavailable.setPatient_status(rs.getInt(11));
				notavailable.setPatient_status_name(rs.getString(12));
				notavailable.setConsultantShare(rs.getDouble(13));
				notavailable.setClinicShare(rs.getDouble(19));
				notavailable.setDisease(rs.getString(20));
				notavailable.setReferuserid(rs.getString(21));

				notavailable.setPaid_status(rs.getInt(14));
				notavailable.setTotal_bill_amt(rs.getDouble(15));
				notavailable.setPaid_clinic_amt(rs.getDouble(16));
				notavailable.setPaid_consulatant_amt(rs.getDouble(17));
				String clinic_refer_userid = rs.getString(5);
				if (!DateTimeUtils.isNull(rs.getString(18)).equals("")) {
					clinic_refer_userid = rs.getString(18);
				}
				notavailable.setClinic_refer_userid(clinic_refer_userid);
				String smallClinicName = getSourceClinicName(notavailable.getSourceclinic_id(), clinic_refer_userid);
				// notavailable.setSmallClinicName(smallClinicName);
				String sourceclinicid = notavailable.getSourceclinic_id();
				String refclinicname = clinicnamebyDocId(rs.getString(22), sourceclinicid);
				notavailable.setSmallClinicName(refclinicname);
				// Clinic
				// bankdetails=clinicDAO.getBankClinicDetails(notavailable.getSourceclinic_id());
				list.add(notavailable);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	private String clinicnamebyDocId(String id, String sourceclinic_id) {
		String clinicName = "";
		try {
			String sql = "select refered_clinicname from " + sourceclinic_id + ".apm_user where id='" + id + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				clinicName = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clinicName;
	}

	private String getSourceClinicName(String sourceclinic_id, String clinic_refer_userid) {
		String clinicName = "";
		try {
			String sql = "select clinicname from " + sourceclinic_id + ".apm_user where userid='" + clinic_refer_userid
					+ "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				clinicName = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clinicName;
	}

	private String refcity(String string) {
		// TODO Auto-generated method stub

		PreparedStatement pmt = null;
		String refcity = "";

		try {

			String sql = "select city from admin.ipd_hospital where id='" + string + "'";
			pmt = connection.prepareStatement(sql);

			ResultSet rs = pmt.executeQuery();

			while (rs.next()) {

				refcity = rs.getString(1);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return refcity;
	}

	private String clinicid(String string) {
		// TODO Auto-generated method stub

		PreparedStatement pmt = null;
		String commercial = "";

		try {

			String sql = "select commercial from admin.ipd_hospital where id='" + string + "'";
			pmt = connection.prepareStatement(sql);

			ResultSet rs = pmt.executeQuery();

			while (rs.next()) {

				commercial = rs.getString(1);

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return commercial;
	}

	private String referedhospitalid(String string) {
		// TODO Auto-generated method stub

		PreparedStatement pmt = null;
		String name = "";

		try {

			String sql = "select referdhosp_clinicid from admin.ipd_hospital where id='" + string + "'";
			pmt = connection.prepareStatement(sql);

			ResultSet rs = pmt.executeQuery();

			while (rs.next()) {
				name = rs.getString(1);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return name;
	}

	@Override
	public int cancelDeletePatient(String parentid, String delete_reason, String userid, String datetime) {
		int res = 0;
		try {
			String sql = "update admin.refered_patient set deleted=1,deleted_userid='" + userid + "',"
					+ "deleted_date='" + datetime + "',deleted_remark=? where id='" + parentid + "' ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, delete_reason);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int confirmReferPatient(String parentid, String userid, String datetime) {
		int res = 0;
		try {
			String sql = "update admin.refered_patient set confirm=1,confirm_user='" + userid + "'," + "confirm_date='"
					+ datetime + "' where id='" + parentid + "' ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public ArrayList<Master> getPatientStatusList() {
		ArrayList<Master> arrayList = new ArrayList<>();
		try {
			String sql = "select id,name from admin.patient_status_list";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				arrayList.add(master);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	@Override
	public int updateReferPatientStatus(String parentid, String patientStatus, String userid, String datetime) {
		int res = 0;
		try {
			String sql = "update admin.refered_patient set patient_status='" + patientStatus + "',ps_userid='" + userid
					+ "'," + "ps_datetime='" + datetime + "' where id='" + parentid + "' ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int updateReferPatientPayment(String parentid, String totalBillAmt, String clinicShare,
			String consultantShare, String clinicShareAmt, String consultantShareAmt, String userid, String datetime) {
		int res = 0;
		try {
			String sql = "update admin.refered_patient set paid_status=1,total_bill_amt='" + totalBillAmt + "',"
					+ "paid_userid='" + userid + "'," + "paid_datetime='" + datetime + "',paid_clinic_share='"
					+ clinicShare + "',paid_consultant_share='" + consultantShare + "'," + "paid_clinic_amt='"
					+ clinicShareAmt + "',paid_consulatant_amt='" + consultantShareAmt + "' " + " where id='" + parentid
					+ "' ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public boolean checkLoginByUserOrHosp(String sclinicid) {
		boolean flag = true;
		try {
			String sql = "select * from admin.ipd_hospital where hospital_userid='" + sclinicid + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				// Hospital who not used our software
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public NotAvailableSlot geteditsitting(String selectedid) {
		// TODO Auto-generated method stub

		PreparedStatement pmt = null;
		NotAvailableSlot notAvailableSlot = new NotAvailableSlot();

		try {

			String sql = "select dept_id, sitting, followup_date, remark, proceduremaster_id, procedure_id,sitting_num,date_time from sitting_followuplist where id="
					+ selectedid + " ";
			pmt = connection.prepareStatement(sql);

			ResultSet rs = pmt.executeQuery();

			while (rs.next()) {

				notAvailableSlot.setId(Integer.parseInt(selectedid));
				notAvailableSlot.setDepartment(rs.getString(1));
				notAvailableSlot.setSitting(rs.getString(2));
				notAvailableSlot.setFollowupDate(rs.getString(3));
				notAvailableSlot.setRemark(rs.getString(4));
				notAvailableSlot.setAll_procedure(rs.getString(5));
				notAvailableSlot.setProcedure_list(rs.getString(6));
				notAvailableSlot.setSittingnum(rs.getString(7));
                notAvailableSlot.setDate_time(rs.getString(8));
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return notAvailableSlot;
	}

	@Override
	public int getupdatesitting(NotAvailableSlot notAvailableSlot) {
		// TODO Auto-generated method stub

		PreparedStatement pmt = null;
		int result = 0;

		try {

			String sql = "update sitting_followuplist set dept_id=?,sitting=?,followup_date=?,remark=?,proceduremaster_id=?,procedure_id=?,sitting_num=?,date_time=? where id=?";
			pmt = connection.prepareStatement(sql);

			pmt.setString(1, notAvailableSlot.getDepartment());
			pmt.setString(2, notAvailableSlot.getSitting());
			pmt.setString(3, notAvailableSlot.getFollowupDate());
			pmt.setString(4, notAvailableSlot.getRemark());
			pmt.setString(5, notAvailableSlot.getAll_procedure());
			pmt.setString(6, notAvailableSlot.getProcedure_list());
			pmt.setString(7, notAvailableSlot.getSittingnum());
			pmt.setString(8, notAvailableSlot.getDate_time());
			pmt.setInt(9, notAvailableSlot.getId());

			result = pmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public int deletesittingdata(String selectedid) {
		// TODO Auto-generated method stub

		int result = 0;
		PreparedStatement pmt = null;

		try {

			String sql = "delete from sitting_followuplist where id=" + selectedid + "";

			pmt = connection.prepareStatement(sql);
			result = pmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public String Departmentopdid(String selectedid) {
		// TODO Auto-generated method stub
		PreparedStatement pmt = null;
		String opdid = "";

		try {

			String sql = "select deptopd_id from sitting_followuplist where id='" + selectedid + "'";
			pmt = connection.prepareStatement(sql);

			ResultSet rs = pmt.executeQuery();

			while (rs.next()) {

				opdid = rs.getString(1);

			}

		} catch (Exception e) { // TODO: handle exception e.printStackTrace(); }
			e.printStackTrace();

		}
		return opdid;

	}

	@Override
	public void deletedata(String opdid) {
		PreparedStatement pmt = null;
		int result = 0;
		try {
			String sql = "update department_opd set followup_date='' where id='" + opdid + "'";
			pmt = connection.prepareStatement(sql);
			result = pmt.executeUpdate();
		} catch (Exception e) { // TODO: handle exception e.printStackTrace(); }
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Master> getOffersList(int i) {
		ArrayList<Master> arrayList = new ArrayList<>();
		try {
			// clinic =1
			// hospital=2
			// Both =3
			// 0 -deleted
			StringBuffer buffer = new StringBuffer();
			buffer.append("select id, offers, status from admin.clinic_offers where status!=0 ");
			buffer.append("and (status=3 or status=" + i + ") order by id desc ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				arrayList.add(master);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	@Override
	public int updatedata(NotAvailableSlot notAvailableSlot) {
		// TODO Auto-generated method stub
		PreparedStatement pmt = null;
		int result = 0;
		try {
			String sql = "update department_opd set followup_date=? where id='" + notAvailableSlot.getDeptOpdId() + "'";
			pmt = connection.prepareStatement(sql);

			pmt.setString(1, DateTimeUtils.getCommencingDate1(notAvailableSlot.getFollowupDate()));

			result = pmt.executeUpdate();
		} catch (Exception e) { // TODO: handle exception e.printStackTrace(); }
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int saveDepartmentfollowupdate(String deptopdid, String date) {
		// TODO Auto-generated method stub

		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = " insert into department_opd(followup_date)values(?) where id=" + deptopdid + "";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, date);
			result = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ArrayList<NotAvailableSlot> dailyRegistrationReport1(String patienttype, String department, String fromDate,
			String toDate, String category, String secondarydoc, String primarydoc, String referto, String orderby,
			String searchText) {

		PreparedStatement preparedStatement = null;
		ArrayList<NotAvailableSlot> list = new ArrayList<NotAvailableSlot>();
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		EmrDAO emrDAO = new JDBCEmrDAO(connection);
		UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
		ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
		StringBuffer sql = new StringBuffer();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		String date1 = DateTimeUtils.getCommencingDate1(dateFormat.format(calendar.getTime()));
		date1 = DateTimeUtils.getCommencingDate1(date1);
		/*
		 * fromDate = DateTimeUtils.getCommencingDate1(fromDate); toDate =
		 * DateTimeUtils.getCommencingDate1(toDate);
		 */

		sql.append("select id,commencing,clientid,opdbooktime,reqdatetime,dept,newpatient, ");
		sql.append("diaryuserid,diaryusername,refertodept ");
		sql.append("from department_opd  ");
		// sql.append("inner join apm_patient on apm_patient.id =
		// department_opd.clientid ");
		// sql.append("left join opd_secondary_dr on
		// opd_secondary_dr.deptappointmentid=department_opd.id ");
		sql.append("where commencing between '" + fromDate + "' and '" + toDate + "'  ");
		if (!department.equals("")) {
			sql.append(" and dept=" + department + " ");
		}
		if (!patienttype.equals("")) {
			if (patienttype.equals("1")) {
				sql.append(" and newpatient=1 ");
			} else {
				sql.append(" and newpatient!=1 ");
			}
		}
		if (!primarydoc.equals("")) {
			sql.append(" and diaryuserid=" + primarydoc + " ");
		}
		if (searchText != null) {
			sql.append("and clientname like('%" + searchText + "%') ");

		}
		if (!referto.equals("")) {
			sql.append(" and FIND_IN_SET(" + referto + ",refertodept) ");
		}
		sql.append(" group by clientId order by department_opd.commencing,opdbooktime " + orderby + "");// group by
																										// clientid
		try {

			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				/*
				 * NotAvailableSlot availableSlot1 =
				 * getDailyOPDSecondaryDoctor(rs.getInt(1),secondarydoc);
				 * if(availableSlot1.getId()==0){ continue; }
				 */
				NotAvailableSlot notavailable = new NotAvailableSlot();
				// department_opd.id
				notavailable.setId(rs.getInt(1));
				// department_opd.commencing
				notavailable.setCommencing(rs.getString(2));
				// department_opd.clientid
				notavailable.setClientId(rs.getString(3));
				notavailable.setOpdbooktime(DateTimeUtils.isNull(rs.getString(4)));
				// department_opd.reqdatetime
				notavailable.setOpdbookdate(DateTimeUtils.getCommencingDate1(rs.getString(5).split(" ")[0]));
				String reqdatetime = rs.getString(5);
				String bookdate = DateTimeUtils.getCommencingDate1(reqdatetime.split(" ")[0]);
				notavailable.setDatetime(bookdate);
				// department_opd.dept
				notavailable.setDept(rs.getString(6));
				notavailable.setDepartmentname(DateTimeUtils.isNull(chargesReportDAO.getDepartmentName(rs.getInt(6))));
				// department_opd.newpatient
				notavailable.setPatienttype(rs.getString(7));
				// department_opd.diaryuserid
				notavailable.setDiaryUserId(rs.getInt(8));
				// department_opd.diaryusername
				notavailable.setDiaryUser(rs.getString(9));
				// department_opd.refertodept
				notavailable.setReferto(rs.getString(10));

				Client client = getDailyOPDClientData(notavailable.getClientId(), category, searchText);
				if (!client.isStatus()) {
					continue;
				}
				// apm_patient.abrivationid
				notavailable.setAbrivationid(client.getAbrivationid());
				// concat(apm_patient.title,' ',apm_patient.firstname,' ',apm_patient.surname)
				notavailable.setClientName(client.getFullname());

				// apm_patient.patientcategory
				notavailable.setPatientcategory(DateTimeUtils.isNull(client.getPatientcategory()));

				// apm_patient.campArea
				notavailable.setCampArea(client.getCampArea());
				// apm_patient.enrollcode
				notavailable.setEnrollcode(client.getEnrollcode());

				String age = DateTimeUtils.getAge1(client.getDob());
				notavailable.setAgegender(age + " / " + client.getGender());
				notavailable.setMobno(client.getMobNo());
				notavailable.setAddress(client.getAddress());

				String apptdate = DateTimeUtils
						.getdatewithmonth(DateTimeUtils.getCommencingDate1(notavailable.getCommencing())) + " "
						+ notavailable.getOpdbooktime();
				notavailable.setApptdate(apptdate);

				// opd_secondary_dr.seconadary_dr
				// notavailable.setSecondarydoc(DateTimeUtils.isNull(availableSlot1.getSecondarydoc()));
				// opd_secondary_dr.opdappointment
				// notavailable.setOpdpmnt(availableSlot1.getOpdpmnt());

				/*
				 * ArrayList<Emr> secondarylist=new ArrayList<Emr>(); String
				 * listofsecdr=emrDAO.getsecondarydrlist(notavailable.getClientId(),""+
				 * notavailable.getOpdpmnt());
				 * 
				 * String data[]=listofsecdr.split(","); if(!listofsecdr.equals("")) {
				 * if(data.length>0) { for (String string : data) { Emr emr=new Emr();
				 * emr.setSecondarydr(userProfileDAO.getFullName(string));
				 * secondarylist.add(emr); } }else{ Emr emr=new Emr();
				 * emr.setSecondarydr(userProfileDAO.getFullName(listofsecdr));
				 * secondarylist.add(emr); } } notavailable.setSecondarylist(secondarylist);
				 * 
				 * ArrayList<NotAvailableSlot> deptlist=new ArrayList<NotAvailableSlot>();
				 * List<String> myList = new
				 * ArrayList<String>(Arrays.asList(notavailable.getReferto().split(","))); for
				 * (String string : myList) { NotAvailableSlot availableSlot=new
				 * NotAvailableSlot(); String
				 * dept=chargesReportDAO.getDepartmentName(DateTimeUtils.convertToInteger(string
				 * )); availableSlot.setDepartmentname(dept); deptlist.add(availableSlot); }
				 * notavailable.setDeptlist(deptlist);
				 */

				list.add(notavailable);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private NotAvailableSlot getDailyOPDSecondaryDoctor(int int1, String secondarydoc) {
		NotAvailableSlot availableSlot = new NotAvailableSlot();
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select id,seconadary_dr,opdappointment from opd_secondary_dr ");
			buffer.append("where deptappointmentid=" + int1 + " ");
			if (!secondarydoc.equals("")) {
				buffer.append(" and FIND_IN_SET(" + secondarydoc + ",seconadary_dr) ");
			}
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				availableSlot.setId(resultSet.getInt(1));
				availableSlot.setSecondarydoc(resultSet.getString(2));
				availableSlot.setOpdpmnt(resultSet.getInt(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return availableSlot;
	}

	private Client getDailyOPDClientData(String clientId, String category, String searchText) {
		Client client = new Client();
		try {
			StringBuffer sql = new StringBuffer();
			sql.append(
					"select id,abrivationid,concat(apm_patient.title,' ',apm_patient.firstname,' ',apm_patient.surname), ");
			sql.append("dob,gender,mobno,address,patientcategory,campArea,enrollcode ");
			sql.append("from apm_patient ");
			sql.append("where id=" + clientId + " ");
			if (!category.equals("")) {
				sql.append(" and patientcategory='" + category + "' ");
			}
			if (searchText != null) {
				sql.append("and  fullname like ('%" + searchText + "%') ");
			}
			client.setStatus(false);
			PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				client.setStatus(true);
				client.setId(rs.getInt(1));
				client.setAbrivationid(rs.getString(2));
				client.setFullname(rs.getString(3));
				client.setDob(rs.getString(4));
				client.setGender(rs.getString(5));
				client.setMobNo(rs.getString(6));
				client.setAddress(rs.getString(7));
				client.setPatientcategory(rs.getString(8));
				client.setCampArea(rs.getString(9));
				client.setEnrollcode(rs.getString(10));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return client;
	}

	@Override
	public ArrayList<NotAvailableSlot> dailyRegistrationReport2(String patienttype, String department, String fromDate,
			String toDate, String category, String secondarydoc, String primarydoc, String referto, String orderby,
			String searchText, long dnumber) {
		// TODO Auto-generated method stub
		ArrayList<NotAvailableSlot> list = new ArrayList<>();
		StringBuffer sql = new StringBuffer();
		PreparedStatement preparedStatement = null;
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		EmrDAO emrDAO = new JDBCEmrDAO(connection);
		UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
		ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
		/* StringBuffer sql = new StringBuffer(); */
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		String date1 = DateTimeUtils.getCommencingDate1(dateFormat.format(calendar.getTime()));
		date1 = DateTimeUtils.getCommencingDate1(date1);
		/*
		 * fromDate = DateTimeUtils.getCommencingDate1(fromDate); toDate =
		 * DateTimeUtils.getCommencingDate1(toDate);
		 */

		fromDate = DateTimeUtils.getCommencingDate1(fromDate);
		fromDate = DateTimeUtils.getCommencingDate2(fromDate);
		fromDate = DateTimeUtils.getCommencingDate3(fromDate);

		String tdate = null;
		for (int i = 0; i <= dnumber; i++) {
			sql = new StringBuffer();
			tdate = fromDate;
			if (i > 0) {
				fromDate = fromDate + "," + "00:00:00" + " " + "PM";
				tdate = DateTimeUtils.calnewdate(fromDate, i);
				// tdate=DateTimeUtils.getCommencingDate1(tdate);
			}
			tdate = DateTimeUtils.getCommencingDate4(tdate);
			sql.append("select id,commencing,clientid,opdbooktime,reqdatetime,dept,newpatient, ");
			sql.append("diaryuserid,diaryusername,refertodept ");
			sql.append("from department_opd  ");
			// sql.append("inner join apm_patient on apm_patient.id =
			// department_opd.clientid ");
			// sql.append("left join opd_secondary_dr on
			// opd_secondary_dr.deptappointmentid=department_opd.id ");
			sql.append("where commencing='" + tdate + "'  ");
			if (!department.equals("")) {
				sql.append(" and dept=" + department + " ");
			}
			if (!patienttype.equals("")) {
				if (patienttype.equals("1")) {
					sql.append(" and newpatient=1 ");
				} else {
					sql.append(" and newpatient!=1 ");
				}
			}
			if (!primarydoc.equals("")) {
				sql.append(" and diaryuserid=" + primarydoc + " ");
			}
			if (!referto.equals("")) {
				sql.append(" and FIND_IN_SET(" + referto + ",refertodept) ");
			}
			sql.append(" group by clientid order by department_opd.commencing,opdbooktime " + orderby + "");
			try {

				preparedStatement = connection.prepareStatement(sql.toString());
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					/*
					 * NotAvailableSlot availableSlot1 =
					 * getDailyOPDSecondaryDoctor(rs.getInt(1),secondarydoc);
					 * if(availableSlot1.getId()==0){ continue; }
					 */
					NotAvailableSlot notavailable = new NotAvailableSlot();
					// department_opd.id
					notavailable.setId(rs.getInt(1));
					// department_opd.commencing
					notavailable.setCommencing(rs.getString(2));
					// department_opd.clientid
					notavailable.setClientId(rs.getString(3));
					notavailable.setOpdbooktime(DateTimeUtils.isNull(rs.getString(4)));
					// department_opd.reqdatetime
					notavailable.setOpdbookdate(DateTimeUtils.getCommencingDate1(rs.getString(5).split(" ")[0]));
					String reqdatetime = rs.getString(5);
					String bookdate = DateTimeUtils.getCommencingDate1(reqdatetime.split(" ")[0]);
					notavailable.setDatetime(bookdate);
					// department_opd.dept
					notavailable.setDept(rs.getString(6));
					notavailable
							.setDepartmentname(DateTimeUtils.isNull(chargesReportDAO.getDepartmentName(rs.getInt(6))));
					// department_opd.newpatient
					notavailable.setPatienttype(rs.getString(7));
					// department_opd.diaryuserid
					notavailable.setDiaryUserId(rs.getInt(8));
					// department_opd.diaryusername
					notavailable.setDiaryUser(rs.getString(9));
					// department_opd.refertodept
					notavailable.setReferto(rs.getString(10));

					Client client = getDailyOPDClientData(notavailable.getClientId(), category, searchText);
					if (!client.isStatus()) {
						continue;
					}
					// apm_patient.abrivationid
					notavailable.setAbrivationid(client.getAbrivationid());
					// concat(apm_patient.title,' ',apm_patient.firstname,' ',apm_patient.surname)
					notavailable.setClientName(client.getFullname());

					// apm_patient.patientcategory
					notavailable.setPatientcategory(DateTimeUtils.isNull(client.getPatientcategory()));

					// apm_patient.campArea
					notavailable.setCampArea(client.getCampArea());
					// apm_patient.enrollcode
					notavailable.setEnrollcode(client.getEnrollcode());

					String age = DateTimeUtils.getAge1(client.getDob());
					notavailable.setAgegender(age + " / " + client.getGender());
					notavailable.setMobno(client.getMobNo());
					notavailable.setAddress(client.getAddress());

					String apptdate = DateTimeUtils
							.getdatewithmonth(DateTimeUtils.getCommencingDate1(notavailable.getCommencing())) + " "
							+ notavailable.getOpdbooktime();
					notavailable.setApptdate(apptdate);

					// opd_secondary_dr.seconadary_dr
					// notavailable.setSecondarydoc(DateTimeUtils.isNull(availableSlot1.getSecondarydoc()));
					// opd_secondary_dr.opdappointment
					// notavailable.setOpdpmnt(availableSlot1.getOpdpmnt());

					/*
					 * ArrayList<Emr> secondarylist=new ArrayList<Emr>(); String
					 * listofsecdr=emrDAO.getsecondarydrlist(notavailable.getClientId(),""+
					 * notavailable.getOpdpmnt());
					 * 
					 * String data[]=listofsecdr.split(","); if(!listofsecdr.equals("")) {
					 * if(data.length>0) { for (String string : data) { Emr emr=new Emr();
					 * emr.setSecondarydr(userProfileDAO.getFullName(string));
					 * secondarylist.add(emr); } }else{ Emr emr=new Emr();
					 * emr.setSecondarydr(userProfileDAO.getFullName(listofsecdr));
					 * secondarylist.add(emr); } } notavailable.setSecondarylist(secondarylist);
					 * 
					 * ArrayList<NotAvailableSlot> deptlist=new ArrayList<NotAvailableSlot>();
					 * List<String> myList = new
					 * ArrayList<String>(Arrays.asList(notavailable.getReferto().split(","))); for
					 * (String string : myList) { NotAvailableSlot availableSlot=new
					 * NotAvailableSlot(); String
					 * dept=chargesReportDAO.getDepartmentName(DateTimeUtils.convertToInteger(string
					 * )); availableSlot.setDepartmentname(dept); deptlist.add(availableSlot); }
					 * notavailable.setDeptlist(deptlist);
					 */

					list.add(notavailable);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return list;

	}

	@Override
	public void insertinnew_secondary_dr(String aptid, String newappointmentid, String dr, String dateTime,
			String clientid) {
		PreparedStatement pstm = null;
		String sql = "insert into new_secondary_dr(opdappointment, deptappointmentid, secondary_dr, clientid, date)values(?,?,?,?,?)";
		try {
			pstm = connection.prepareStatement(sql);
			pstm.setString(1, newappointmentid);
			pstm.setString(2, aptid);
			pstm.setString(3, dr);
			pstm.setString(4, clientid);
			pstm.setString(5, dateTime);
			int result = pstm.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	@Override
	public ArrayList<NotAvailableSlot> getnewFinderList(String clientId, String fromDate, String todate,
			String practionerId, String payee, Appointment appointment, String apptype, String apmtType,
			String searchtext, String appf, String fkdepartment) {

		if (appointment == null) {
			appointment = new Appointment();
		}

		PreparedStatement preparedStatement = null;
		ArrayList<NotAvailableSlot> list = new ArrayList<NotAvailableSlot>();
		StringBuffer sql = new StringBuffer();
		JDBCMasterDAO dao = new JDBCMasterDAO(connection);
		// String sql = "select id,commencing,
		// diaryuserid,starttime,duration,aptmtype,notes,endtime,location,treatmentEpisodeId,apmslotid,condition_id
		// from apm_available_slot where clientid= "+clientId+" ";
		/*
		 * if(appf.equals("1")) { sql =
		 * "select apm_available_slot.id,commencing, diaryuserid,starttime,duration,aptmtype,notes,endtime,location,treatmentEpisodeId,apmslotid,condition_id,apm_available_slot.status,clientid,reqdatetime,whopay,opdpmnt,drcompleted  from apm_available_slot "
		 * + " where commencing between '"+fromDate+"'and '"
		 * +todate+"' and  clientid is not null"; }else {
		 * 
		 * sql =
		 * "select apm_available_slot.id,commencing, diaryuserid,starttime,duration,aptmtype,notes,endtime,location,treatmentEpisodeId,apmslotid,condition_id,apm_available_slot.status,clientid,reqdatetime,whopay,opdpmnt,drcompleted  from apm_available_slot "
		 * + " where commencing between '"+fromDate+"'and '"
		 * +todate+"' and  clientid is not null and surgeon!=0 ";
		 * 
		 * }
		 */

		if (appf.equals("1") || appf.equals("")) {

			sql.append(
					"select apm_available_slot.id,commencing, diaryuserid,apm_available_slot.starttime,duration,aptmtype,notes,apm_available_slot.endtime,location,treatmentEpisodeId,apmslotid,condition_id, ");
			sql.append(
					"apm_available_slot.status,clientid,reqdatetime,apm_available_slot.whopay,opdpmnt,drcompleted,discipline,surgeon,admissionid,title,apm_patient.firstname,lastname,apm_patient.dob,gender,abrivationid,clientname from apm_available_slot inner join apm_user on apm_user.id=apm_available_slot.diaryuserid ");
			sql.append(
					"inner join apm_discipline on apm_discipline.id=apm_user.discription inner join apm_patient on apm_patient.id=apm_available_slot.clientid ");
			if (fkdepartment.equals("")) {
				sql.append("where commencing between '" + fromDate + "' and '" + todate + "' and surgeon=0 ");
			} else {
				sql.append("where apm_discipline.id='" + fkdepartment + "' and surgeon=0 ");
			}
			// sql.append("where commencing between '"+fromDate+"' and '"+todate+"' ");

		} else {

			sql.append(
					"select apm_available_slot.id,commencing, diaryuserid,apm_available_slot.starttime,duration,aptmtype,notes,apm_available_slot.endtime,location,treatmentEpisodeId,apmslotid,condition_id, ");
			sql.append(
					"apm_available_slot.status,clientid,reqdatetime,apm_available_slot.whopay,opdpmnt,drcompleted,discipline,surgeon,admissionid,title,firstname,lastname,dob,gender,abrivationid,clientname from apm_available_slot inner join apm_user on apm_user.id=apm_available_slot.diaryuserid ");
			sql.append(
					"inner join apm_discipline on apm_discipline.id=apm_user.discription inner join apm_patient on apm_patient.id=apm_available_slot.clientid ");
			sql.append("where commencing between '" + fromDate + "' and '" + todate + "' and surgeon!=0 ");
		}

		if (!searchtext.equals("")) {
			sql.append("and clientname like ('%" + searchtext + "%') or clientId like ('%" + searchtext + "%') ");
		}

		/*
		 * if(!fkdepartment.equals("")) {
		 * sql.append("and apm_discipline.id='"+fkdepartment+"' "); }
		 */

		sql.append("order by id desc limit 200");

		try {

			preparedStatement = connection.prepareStatement(sql.toString());
			dao.logj(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NotAvailableSlot availableSlot = new NotAvailableSlot();
				String deptnm = description(rs.getString(20));
				availableSlot.setId(rs.getInt(1));
				availableSlot.setCommencing(rs.getString(2));

				String date = DateTimeUtils.getCommencingDate1(rs.getString(2));
				availableSlot.setCommencing(date);

				String diaryUserName = getDiaryUserName(rs.getInt(3));
				availableSlot.setDiaryUser(diaryUserName);
				availableSlot.setSTime(rs.getString(4));
				availableSlot.setDuration(rs.getString(5));
				String appointmentType = getAppointmentType(rs.getInt(6));
				availableSlot.setApmttypetext(appointmentType);
				availableSlot.setNotes(rs.getString(7));
				availableSlot.setEndTime(rs.getString(8));
				availableSlot.setLocation(rs.getString(9));
				availableSlot.setTreatmentEpisodeId(rs.getString(10));
				availableSlot.setApmtSlotId(rs.getInt(11));
				availableSlot.setCondition(rs.getString(12));
				availableSlot.setStatus(rs.getString(13));
				availableSlot.setApmtType(rs.getString(6));
				availableSlot.setDiaryUserId(rs.getInt(3));
				availableSlot.setClientId(rs.getString(14));
				availableSlot.setWhopay(rs.getString(16));
				// availableSlot.setDepartment(deptnm);

				if (appf.equals("1") || appf.equals("")) {
					availableSlot.setDepartment(rs.getString(19));
				} else {
					availableSlot.setDepartment(deptnm);
				}
				int invId = rs.getInt(17);
				int ispaid = 0;
				if (invId > 0) {
					ispaid = isPaid(invId);
				}
				if (ispaid > 0) {
					availableSlot.setPayBy("Paid");
				} else {
					availableSlot.setPayBy("Unpaid");
				}
				availableSlot.setRequestDateTime(DateTimeUtils.isNull(rs.getString(15)));
				if (!availableSlot.getRequestDateTime().equals("")) {
					availableSlot.setRequestDateTime(
							DateTimeUtils.getCommencingDate1(availableSlot.getRequestDateTime().split(" ")[0]) + " "
									+ availableSlot.getRequestDateTime().split(" ")[1]);
				}
				String te = rs.getString(10);
				if (rs.getString(10) != null) {
					if (!te.equals("0")) {
						String treatmentSessions = getTreatmentEpisodeName(availableSlot.getId());
						availableSlot.setTreatmentSessions(treatmentSessions);
					} else {
						availableSlot.setTreatmentSessions("");
					}

				}

				boolean iscompleted = getIsCompleted(availableSlot.getId());

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date1 = sdf.parse(availableSlot.getCommencing());
				Date date2 = sdf.parse(DateTimeUtils.getSimpleDateFormat(new Date()));

				if (date1.before(date2)) {
					availableSlot.setOldata(true);
				} else {
					availableSlot.setOldata(false);

				}
				boolean flag = rs.getBoolean(18);

				if (!DateTimeUtils.isNull(appointment.getDrcompleted()).equals("")) {
					if (DateTimeUtils.isNull(appointment.getDrcompleted()).equals("1")) {
						if (rs.getBoolean(18)) {
							continue;
						}
					} else {
						if (!rs.getBoolean(18)) {
							continue;
						}
					}

				}

				ClientDAO clientDAO = new JDBCClientDAO(connection);
				/*
				 * Client client = clientDAO.getClientDetails(rs.getString(14)); String
				 * clientname = client.getTitle() + " " + client.getFirstName() + " " +
				 * client.getLastName();
				 * availableSlot.setAbrivationid(client.getAbrivationid());
				 * availableSlot.setAgegender(client.getAge1()+"/"+client.getGender());
				 * availableSlot.setClientName(clientname);
				 */

				availableSlot.setAbrivationid(rs.getString(27));
				String age1 = "";
				age1 = DateTimeUtils.getAge1(rs.getString(25));
				availableSlot.setAgegender(age1 + "/" + rs.getString(26));
				//availableSlot.setClientName(rs.getString(22) + " " + rs.getString(23) + " " + rs.getString(24));
				availableSlot.setClientName(rs.getString(28));
				availableSlot.setClientEmail(rs.getString(14));
				/*
				 * availableSlot.setAbrivationid(client.getAbrivationid());
				 * availableSlot.setAgegender(client.getAge1()+"/"+client.getGender());
				 */
				String mbbsseqino = getipdno(rs.getString(21));
				availableSlot.setMbbs_seq_no(mbbsseqino);
				list.add(availableSlot);
				/*
				 * if(availableSlot.getStatus().equals("1")){
				 * availableSlot.setClientName("N/A"); list.add(availableSlot); }else{
				 * if(!rs.getBoolean(18)){ ClientDAO clientDAO = new JDBCClientDAO(connection);
				 * Client client = clientDAO.getClientDetails(rs.getString(14)); String
				 * clientname = client.getTitle() + " " + client.getFirstName() + " " +
				 * client.getLastName(); availableSlot.setClientName(clientname);
				 * 
				 * list.add(availableSlot); } }
				 */

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		int logsize = list.size();
		// String hello="hello"+logsize;

		dao.logj("" + logsize);
		return list;
	}

	private String getipdno(String ipdno) {
		PreparedStatement pmt = null;

		String mbbsipdseqno = "";

		try {

			String sql = "select mbbsseqno from ipd_addmission_form where id='" + ipdno + "'";
			pmt = connection.prepareStatement(sql);

			ResultSet rs = pmt.executeQuery();

			while (rs.next()) {

				mbbsipdseqno = (rs.getString(1));

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return mbbsipdseqno;

	}

	private String description(String string) {

		PreparedStatement pmt = null;

		String deptid = "";

		try {

			String sql = "select discription from apm_user where id='" + string + "'";
			pmt = connection.prepareStatement(sql);

			ResultSet rs = pmt.executeQuery();

			while (rs.next()) {

				deptid = deptname(rs.getString(1));

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return deptid;

	}

	private String deptname(String string) {

		PreparedStatement pmt = null;

		String name = "";

		try {

			String sql = "select discipline from apm_discipline where id='" + string + "'";
			pmt = connection.prepareStatement(sql);

			ResultSet rs = pmt.executeQuery();

			while (rs.next()) {

				name = (rs.getString(1));

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return name;

	}

	@Override
	public String getfakedatebyClientid(String fakclientid) {

		PreparedStatement preparedStatement = null;
		String result = "";
		try {
			String sql = "select min(id),commencing from apm_available_slot where clientId= '" + fakclientid + "' ";
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				result = rs.getString(2);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	@Override
	public ArrayList<NotAvailableSlot> getconsultDepartmentList() {

		PreparedStatement preparedStatement = null;
		ArrayList<NotAvailableSlot> list = new ArrayList<NotAvailableSlot>();
		StringBuffer sql = new StringBuffer();

		sql.append(
				"select count(apm_discipline.id),apm_discipline.id,apm_discipline.discipline from apm_available_slot ");
		sql.append("inner join apm_user on apm_user.id=apm_available_slot.diaryuserid ");
		sql.append("inner join apm_discipline on apm_discipline.id=apm_user.discription group by ");
		sql.append("apm_user.discription ");

		// String sqls = pagination.getSQLQuery(sql.toString());
		try {

			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NotAvailableSlot notavailable = new NotAvailableSlot();

				notavailable.setCount(rs.getInt(1));
				notavailable.setId(rs.getInt(2));
				notavailable.setDisciplineName(rs.getString(3) + "(" + (rs.getInt(1)) + ")");

				list.add(notavailable);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public int getinvestigationCount(String clientid) {

		PreparedStatement preparedStatement = null;
		StringBuffer sql = new StringBuffer();
		int res = 0;
		sql.append("select count(*) ");
		sql.append(
				"from apm_client_parent_investigation inner join apm_investigation_type on apm_investigation_type.id = apm_client_parent_investigation.invsttypeid where clientid="
						+ clientid + " ");

		try {

			preparedStatement = connection.prepareStatement(sql.toString());
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
	public int getipdCount(String clientid) {

		PreparedStatement preparedStatement = null;
		StringBuffer sql = new StringBuffer();
		int res = 0;
		sql.append("select count(*) ");
		sql.append("from ipd_addmission_form where clientid=" + clientid + " ");

		try {

			preparedStatement = connection.prepareStatement(sql.toString());
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
	public int getprescriptionCount(String clientid) {
		PreparedStatement preparedStatement = null;
		StringBuffer sql = new StringBuffer();
		int res = 0;
		sql.append("select count(*) ");
		sql.append(
				"from apm_client_parent_priscription inner join apm_parent_prisc on apm_client_parent_priscription.id = apm_parent_prisc.oldparentid where apm_client_parent_priscription.clientid="
						+ clientid + " ");

		try {

			preparedStatement = connection.prepareStatement(sql.toString());
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
	public int getpharmacyCount(String clientid) {
		PreparedStatement preparedStatement = null;
		StringBuffer sql = new StringBuffer();
		int res = 0;
		sql.append("select count(*) ");
		sql.append("from apm_medicine_bill where clientid=" + clientid + " ");

		try {

			preparedStatement = connection.prepareStatement(sql.toString());
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
	public int getopdCount(String clientid) {
		PreparedStatement preparedStatement = null;
		StringBuffer sql = new StringBuffer();
		int res = 0;
		sql.append("select count(*) ");
		sql.append("from apm_available_slot where clientid=" + clientid + " and surgeon=0 ");

		try {

			preparedStatement = connection.prepareStatement(sql.toString());
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
	public int getotCount(String clientid) {
		PreparedStatement preparedStatement = null;
		StringBuffer sql = new StringBuffer();
		int res = 0;
		sql.append("select count(*) ");
		sql.append("from apm_available_slot where clientid=" + clientid + " and surgeon!=0 ");

		try {

			preparedStatement = connection.prepareStatement(sql.toString());
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
	public String getfOTdatebyClientid(String fakclientid) {

		PreparedStatement preparedStatement = null;
		StringBuffer sql = new StringBuffer();
		String res = "";
		sql.append("select min(id),commencing ");
		sql.append("from apm_available_slot where clientid=" + fakclientid + " and surgeon!=0 ");

		try {

			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				res = rs.getString(2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;

	}

	@Override
	public String getdepmnDate(String fkdepartment) {

		String res = "0";
		try {

			StringBuffer buffer = new StringBuffer();
			buffer.append("select min(apm_available_slot.id),commencing from apm_available_slot ");
			buffer.append("inner join apm_user on apm_user.id=apm_available_slot.diaryuserid ");
			buffer.append("inner join apm_discipline on apm_discipline.id=apm_user.discription ");
			buffer.append("where apm_discipline.id= '" + fkdepartment + "' ");

			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				res = rs.getString(2);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return res;

	}

	@Override
	public String getotDatebydropdown() {
		PreparedStatement preparedStatement = null;
		StringBuffer sql = new StringBuffer();
		String res = "";
		sql.append("select min(id),commencing ");
		sql.append("from apm_available_slot where surgeon!=0 ");

		try {

			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				res = rs.getString(2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int getdeleteData(String clientid) {

		int result = 0;
		PreparedStatement pmt = null;

		try {

			String sql = "delete from apm_client_parent_priscription  where clientid=" + clientid + "";
			pmt = connection.prepareStatement(sql);
			result = pmt.executeUpdate();

			sql = "delete from apm_client_parent_investigation where clientId=" + clientid + "";
			pmt = connection.prepareStatement(sql);
			result = pmt.executeUpdate();

			sql = "delete from apm_treatment_episode where clientid=" + clientid + "";
			pmt = connection.prepareStatement(sql);
			result = pmt.executeUpdate();

			sql = "delete from ipd_addmission_form  where clientId=" + clientid + "";
			pmt = connection.prepareStatement(sql);
			result = pmt.executeUpdate();

			sql = "delete from apm_available_slot  where clientId=" + clientid + "";
			pmt = connection.prepareStatement(sql);
			result = pmt.executeUpdate();

			sql = "delete from apm_child_prisc  where clientId=" + clientid + "";
			pmt = connection.prepareStatement(sql);
			result = pmt.executeUpdate();

			sql = "delete from apm_client_priscription  where clientId=" + clientid + "";
			pmt = connection.prepareStatement(sql);
			result = pmt.executeUpdate();

			sql = "delete from apm_parent_prisc where clientId=" + clientid + "";
			pmt = connection.prepareStatement(sql);
			result = pmt.executeUpdate();

			sql = "delete from apm_client_investigation  where clientId=" + clientid + "";
			pmt = connection.prepareStatement(sql);
			result = pmt.executeUpdate();

			sql = "delete from apm_medicine_bill  where clientId=" + clientid + "";
			pmt = connection.prepareStatement(sql);
			result = pmt.executeUpdate();

			sql = "delete from apm_medicine_payment  where clientId=" + clientid + "";
			pmt = connection.prepareStatement(sql);
			result = pmt.executeUpdate();

			sql = "delete from apm_medicine_charges  where clientId=" + clientid + "";
			pmt = connection.prepareStatement(sql);
			result = pmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return result;

	}

	@Override
	public int updatePatientTransferSts(int id, int previousDate) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update fake_patient set transfer=1,previousDate='" + previousDate + "' where id = " + id + "";
		try {
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ArrayList<NotAvailableSlot> getPatientHistory(String searchText) {
		PreparedStatement preparedStatement = null;
		ArrayList<NotAvailableSlot> list = new ArrayList<NotAvailableSlot>();
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		EmrDAO emrDAO = new JDBCEmrDAO(connection);
		UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
		ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
		String cdate = DateTimeUtils.getUKCurrentDataTime("India").split(" ")[0];
		try {
			StringBuffer buffer = new StringBuffer();

			if (!searchText.equals("")) {
				buffer.append(
						"select id,commencing,clientname,clientid,opdbooktime,reqdatetime,dept,diaryuserid,diaryusername,refertodept,newpatient from department_opd ");
				buffer.append("where  clientname like('%" + searchText + "%')  order by id desc");
			} else {
				buffer.append(
						"select id,commencing,clientname,clientid,opdbooktime,reqdatetime,dept,diaryuserid,diaryusername,refertodept,newpatient from department_opd where commencing='"
								+ cdate + "'");
			}
			preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NotAvailableSlot notavailable = new NotAvailableSlot();
				notavailable.setId(rs.getInt(1));

				notavailable.setCommencing(rs.getString(2));
				notavailable.setClientName(rs.getString(3));
				notavailable.setClientId(rs.getString(4));
				Client client = clientDAO.getClientDetails(notavailable.getClientId());
				String age = DateTimeUtils.getAge1(client.getDob());
				notavailable.setAgegender(age + " / " + client.getGender());
				notavailable.setMobno(client.getMobNo());
				notavailable.setOpdbooktime(DateTimeUtils.isNull(rs.getString(5)));
				notavailable.setOpdbookdate(DateTimeUtils.getCommencingDate1(rs.getString(6).split(" ")[0]));
				notavailable.setAddress(client.getAddress());
				String reqdatetime = rs.getString(6);
				notavailable.setAbrivationid(client.getAbrivationid());
				/*
				 * String
				 * bookdate=DateTimeUtils.getdatewithmonth(DateTimeUtils.getCommencingDate1(
				 * reqdatetime.split(" ")[0]))+" "+reqdatetime.split(" ")[1];
				 */
				String bookdate = DateTimeUtils.getCommencingDate1(reqdatetime.split(" ")[0]);
				notavailable.setDatetime(reqdatetime);

				// notavailable.setDatetime(bookdate);
				String apptdate = DateTimeUtils
						.getdatewithmonth(DateTimeUtils.getCommencingDate1(notavailable.getCommencing())) + " "
						+ notavailable.getOpdbooktime();
				notavailable.setApptdate(apptdate);
				notavailable.setPatientcategory(DateTimeUtils.isNull(client.getPatientcategory()));
				notavailable.setDepartmentname(DateTimeUtils.isNull(chargesReportDAO.getDepartmentName(rs.getInt(7))));
				notavailable.setDiaryUserId(rs.getInt(8));
				notavailable.setDiaryUser(rs.getString(9));
				notavailable.setReferto(rs.getString(10));
				notavailable.setPatienttype(rs.getString(11));
				notavailable.setCampArea(client.getCampArea());
				notavailable.setEnrollcode(client.getEnrollcode());
				list.add(notavailable);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Client getRefereddata(String parentid) {
		PreparedStatement preparedStatement = null;
		Client client = new Client();
		String sql = "SELECT patientid,sourceclinicid,refered_userid FROM admin.refered_patient where  id = " + parentid
				+ "";

		try {
			preparedStatement = connection.prepareStatement(sql);

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				client.setClientid(rs.getString(1));
				client.setSourceclientid(rs.getString(2));
				client.setRefclinic(rs.getString(3));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return client;
	}

	@Override
	public int getnoteCount(String ipdaddmissionid) {

		PreparedStatement preparedStatement = null;
		StringBuffer sql = new StringBuffer();
		int res = 0;
		sql.append("select count(*) ");
		sql.append("from request_note where admissionid=" + ipdaddmissionid + " and isseen=1 ");

		try {

			preparedStatement = connection.prepareStatement(sql.toString());
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
	public NotAvailableSlot getClientId(String id) {
		PreparedStatement preparedStatement = null;
		NotAvailableSlot notAvailableSlot = new NotAvailableSlot();
		String sql = "SELECT clientId,clientname FROM department_opd where id = " + id + "";

		try {
			preparedStatement = connection.prepareStatement(sql);

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String abbrivationid = getAbbrivationid(rs.getString(1));
				notAvailableSlot.setClientId(rs.getString(1));
				notAvailableSlot.setClientName(rs.getString(2));
				notAvailableSlot.setAbrivationid(abbrivationid);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return notAvailableSlot;

	}

	private String getAbbrivationid(String id) {
		PreparedStatement preparedStatement = null;
		String sql = "SELECT abrivationid FROM apm_patient where id = " + id + "";
		String abbrivationid = "";
		try {
			preparedStatement = connection.prepareStatement(sql);

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				abbrivationid = rs.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return abbrivationid;
	}

	@Override
	public int saveColorCode(NotAvailableSlot notAvailableSlot) {
		int result = 0;
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append(
					"insert into opd_color_report(datetime,clientId,color,clientname,abrivationid)values(?,?,?,?,?)");
			PreparedStatement ps = connection.prepareStatement(buffer.toString());
			ps.setString(1, notAvailableSlot.getDate_time());
			ps.setString(2, notAvailableSlot.getClientId());
			ps.setString(3, notAvailableSlot.getColor());
			ps.setString(4, notAvailableSlot.getClientName());
			ps.setString(5, notAvailableSlot.getAbrivationid());

			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	@Override
	public ArrayList<NotAvailableSlot> PhysioIPDList(String department, String date, Pagination pagination,
			String category, String orderby, LoginInfo loginInfo, String searchtext) {
		PreparedStatement preparedStatement = null;
		ArrayList<NotAvailableSlot> list = new ArrayList<NotAvailableSlot>();
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		UserProfileDAO profileDAO = new JDBCUserProfileDAO(connection);
		NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
		ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
		ArrayList<DiaryManagement> userList = new ArrayList<DiaryManagement>();
		StringBuffer sql = new StringBuffer();
		String query = "";
		if (!department.equals("")) {
			userList = notAvailableSlotDAO.getUserListwithdepartment(0, department);
		} else {
			userList = notAvailableSlotDAO.getUserList(0);
		}
		// refertodept==0
		// followup_date!=''
		sql.append(
				"select physio_ipd.id, commencing,starttime,abrivationid,concat(title,' ',firstname,' ',surname),clientid,apmttypetext,condition_id, ");
		sql.append(
				"opdpmnt,physio_ipd.whopay,physio_ipd.status,dna,chrgstatus,drcompleted,physio_ipd.arrivedstatus,opdpmnt,opdbooktime, ");
		sql.append(
				"duration,reqdatetime,mobstatus,reception_vid_verify,doctor_vid_verify,doctor_vid_reject_remark,diaryuserid,pending_remark, ");
		sql.append(
				" added_by,patient_being_seen_time,refferedfrom,dept,referredfromdept,patientcategory,refferremark,diaryusername,campArea,enrollcode,newpatient, ");
		sql.append("refertodept,followup_date,planid ");
		sql.append("from  physio_ipd inner join apm_patient on ");
		sql.append("apm_patient.id = physio_ipd.clientid ");
		// sql.append("where commencing= '"+date+"' ");

		if (!searchtext.equals("")) {
			sql.append("where firstname like('%" + searchtext + "%') ");
		} else {
			sql.append("where commencing= '" + date + "' ");
		}

		if (!department.equals("")) {
			sql.append(" and dept=" + department + "");
		}

		if (!category.equals("0")) {
			sql.append(" and apm_patient.patientcategory='" + category + "'");
		}

		if (!searchtext.equals("")) {
			sql.append(" order by id desc limit 0,1");
		} else {
			sql.append(" order by id " + orderby + "");
		}
		/*
		 * if(pagination!=null){ query= pagination.getSQLQuery(sql.toString()); }
		 */
		try {
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NotAvailableSlot notavailable = new NotAvailableSlot();

				notavailable.setId(rs.getInt(1));

				notavailable.setCommencing(rs.getString(2));
				notavailable.setSTime(rs.getString(3));
				notavailable.setAbrivationid(rs.getString(4));
				notavailable.setClientName(rs.getString(5));
				notavailable.setClientId(rs.getString(6));
				Client client = clientDAO.getClientDetails(notavailable.getClientId());

				String age = DateTimeUtils.getAge1(client.getDob());
				notavailable.setAgegender(age + " / " + client.getGender());
				notavailable.setMobno(client.getMobNo());
				notavailable.setApmttypetext(rs.getString(7));
				notavailable.setCondition(rs.getString(8));
				notavailable.setOpdpmnt(rs.getInt(9));
				notavailable.setWhopay(rs.getString(10));
				notavailable.setStatus(rs.getString(11));// check appointment completed
				notavailable.setDna(rs.getBoolean(12));
				boolean isCompleted = false;
				if (rs.getInt(13) == 1) {
					isCompleted = true;

				}
				if (isCompleted && !notavailable.isDna()) {
					notavailable.setAppointmentCompleted(isCompleted);
				}

				notavailable.setDrcompleted(rs.getInt(14));

				notavailable.setArrivedStatus(rs.getInt(15));
				notavailable.setOpdpmnt(rs.getInt(16));
				notavailable.setOpdbooktime(DateTimeUtils.isNull(rs.getString(17)));
				notavailable.setDuration(rs.getString(18));
				notavailable.setOpdbookdate(DateTimeUtils.getCommencingDate1(rs.getString(19).split(" ")[0]));
				notavailable.setMobstatusnew(rs.getInt(20));
				notavailable.setReception_vid_verify(rs.getInt(21));
				notavailable.setDoctor_vid_verify(rs.getInt(22));
				notavailable.setDoctor_vid_reject_remark(DateTimeUtils.isNull(rs.getString(23)));
				// if(rs.getInt(24)!=2){
				notavailable.setDiaryUserId(rs.getInt(24));
				// }
				notavailable.setDocid("" + notavailable.getDiaryUserId());
				notavailable.setPending_remark(DateTimeUtils.isNull(rs.getString(25)));
				notavailable.setAddedBy(rs.getString(26));
				if (DateTimeUtils.isNull(rs.getString(27)).equals("")) {
					notavailable.setPatientisseen(0);
				} else {
					notavailable.setPatientisseen(1);
				}
				if (rs.getInt(24) > 1) {
					notavailable.setDrselected("1");
					notavailable.setDoctorname(rs.getString(33));
				} else {
					notavailable.setDoctorname("");
					notavailable.setDrselected("0");
				}
				String reqdatetime = rs.getString(19);

				notavailable.setRefferedfrom(rs.getString(28));
				/*
				 * String
				 * bookdate=DateTimeUtils.getdatewithmonth(DateTimeUtils.getCommencingDate1(
				 * reqdatetime.split(" ")[0]))+" "+reqdatetime.split(" ")[1];
				 */
				String bookdate = DateTimeUtils.getCommencingDate1(reqdatetime.split(" ")[0]);
				notavailable.setDatetime(bookdate);
				/*
				 * String
				 * apptdate=DateTimeUtils.getdatewithmonth(DateTimeUtils.getCommencingDate1(
				 * notavailable.getCommencing()))+" "+notavailable.getOpdbooktime();
				 */
				String apptdate = DateTimeUtils.getCommencingDate1(notavailable.getCommencing());
				notavailable.setApptdate(apptdate);
				int duserid = profileDAO.getDiaryUserId(notavailable.getAddedBy());
				UserProfile userProfile2 = profileDAO.getUserprofileDetails(duserid);
				notavailable.setAddedBy(userProfile2.getFullname());
				notavailable.setDept(rs.getString(29));
				notavailable.setDepartmentname(DateTimeUtils.isNull(chargesReportDAO.getDepartmentName(rs.getInt(29))));
				notavailable
						.setReferredfromdept(DateTimeUtils.isNull(chargesReportDAO.getDepartmentName(rs.getInt(30))));
				notavailable.setPatientcategory(DateTimeUtils.isNull(rs.getString(31)));
				notavailable.setRefferremark(DateTimeUtils.isNull(rs.getString(32)));
				notavailable.setCampArea(rs.getString(34));
				notavailable.setEnrollcode(rs.getString(35));
				notavailable.setNewpatient(rs.getInt(36));
				notavailable.setUserList(userList);

				int referStatus = 0;
				if (!DateTimeUtils.isNull(rs.getString(37)).equals("0")) {
					referStatus = 1;
				}
				notavailable.setReferStatus(referStatus);

				int folloupGiven = 0;
				if (DateTimeUtils.isNull(rs.getString(38)).equals("")) {
					folloupGiven = checkFollowUpGiven(rs.getInt(1));
				} else {
					folloupGiven = 1;
				}
				notavailable.setFolloupGiven(folloupGiven);

				String activeplanname = getActivePlanofPatient(rs.getInt(39));
				notavailable.setActiveplan(activeplanname);
				String invoiceid = getChargesInvoiceid(notavailable.getClientId());
				notavailable.setInvoiceid(invoiceid);
				int totalsession = getTotalsittingbyinvoiceid(invoiceid);
				notavailable.setTotalsetting("" + totalsession);
				int ipdsession = getTotalipdsetting(notavailable);
				notavailable.setIpdsetting("" + ipdsession);
				list.add(notavailable);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	private int getTotalipdsetting(NotAvailableSlot notavailable) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		try {
			String sql = "select count(*) from ipd_sitting_followuplist where patient_id='" + notavailable.getClientId()
					+ "' and dept_id='" + notavailable.getDept() + "' group by invoiceid";
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

	private int getTotalsittingbyinvoiceid(String invoiceid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		try {
			String sql = "select session from apm_charges_invoice where id=" + invoiceid + "";
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

	@Override
	public int getIpdphysioaccess(int id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		try {
			String sql = "select ipdphysio from apm_user_access where userkeyid='" + id + "'";
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

	@Override
	public int saveIpdsittingdata(NotAvailableSlot notAvailableSlot) {
		PreparedStatement pmt = null;
		int result = 0;

		try {

			String sql = "insert into ipd_sitting_followuplist(patient_id, dept_id, sitting, followup_date, remark, user_id, date_time, proceduremaster_id, procedure_id, deptopd_id, sitting_num, diagnosis,hospitalid,ward_bed,invoiceid)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pmt = connection.prepareStatement(sql);

			pmt.setString(1, notAvailableSlot.getClientId());
			pmt.setString(2, notAvailableSlot.getDepartment());
			pmt.setString(3, notAvailableSlot.getSitting());
			// pmt.setBoolean(4, notAvailableSlot.isSittingFollowup());
			pmt.setString(4, notAvailableSlot.getDate());
			pmt.setString(5, notAvailableSlot.getRemark());
			pmt.setString(6, notAvailableSlot.getUser_id());
			pmt.setString(7, notAvailableSlot.getDate_time());
			pmt.setString(8, notAvailableSlot.getAll_procedure());
			pmt.setString(9, notAvailableSlot.getProcedure_list());
			pmt.setInt(10, notAvailableSlot.getDeptOpdId());
			pmt.setString(11, notAvailableSlot.getSittingnum());
			pmt.setString(12, notAvailableSlot.getDiagnosisarea());
			pmt.setString(13, notAvailableSlot.getHosp_name());
			pmt.setString(14, notAvailableSlot.getBed_ward());
			pmt.setString(15, notAvailableSlot.getInvoiceid());
			result = pmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public int gettotalSittingcount(String selecteddept_id, String client_id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		try {
			String sql = "select count(*) from ipd_sitting_followuplist where patient_id='" + client_id
					+ "' and dept_id='" + selecteddept_id + "'";
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

	@Override
	public String getChargesInvoiceid(String clientid) {
		PreparedStatement preparedStatement = null;
		String invoice = "";
		try {
			String sql = "select max(id) from apm_charges_invoice where clientid='" + clientid + "' and itype=15";
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				invoice = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return invoice;
	}

	@Override
	public ArrayList<NotAvailableSlot> departmentIPDPreviewList(String patienttype, String department, String fromDate,
			String toDate, Pagination pagination, String category, String secondarydoc, String primarydoc,
			String referto, String orderby, String searchText, LoginInfo loginInfo, String string2) {
		PreparedStatement preparedStatement = null;
		ArrayList<NotAvailableSlot> list = new ArrayList<NotAvailableSlot>();
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		EmrDAO emrDAO = new JDBCEmrDAO(connection);
		UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
		ChargesReportDAO chargesReportDAO = new JDBCChargesReportDAO(connection);
		StringBuffer sql = new StringBuffer();
		StringBuffer buffer = new StringBuffer();
		/*
		 * sql.
		 * append("select physio_ipd.id, commencing,abrivationid,concat(title,' ',firstname,' ',surname),physio_ipd.clientid, "
		 * ); sql.
		 * append("opdbooktime,reqdatetime, dept,patientcategory,newpatient,diaryuserid,diaryusername,seconadary_dr,opdappointment,refertodept,campArea,enrollcode "
		 * ); sql.append("from  physio_ipd inner join apm_patient on ");
		 * sql.append("apm_patient.id = physio_ipd.clientid "); sql.
		 * append("left join opd_secondary_dr on opd_secondary_dr.deptappointmentid=physio_ipd.id "
		 * ); sql.append("where  commencing between '"+fromDate+"' and '"+toDate+"' ");
		 * 
		 * if(!department.equals("")){ sql.append(" and dept="+department+" "); }
		 * 
		 * if(!category.equals("")) {
		 * sql.append(" and apm_patient.patientcategory='"+category+"' "); }
		 * if(searchText!=null){
		 * 
		 * sql.append("and apm_patient.fullname like('%"+searchText+"%') ");
		 * 
		 * } if(!patienttype.equals("")) {
		 * sql.append(" and newpatient="+patienttype+""); }
		 * 
		 * if(!patienttype.equals("")) { if(patienttype.equals("1")){
		 * sql.append(" and newpatient=1 "); }else{ sql.append(" and newpatient!=1 "); }
		 * //sql.append(" and newpatient="+patienttype+""); }
		 * 
		 * if(!primarydoc.equals("")){ sql.append(" and diaryuserid="+primarydoc+" "); }
		 * if(!secondarydoc.equals("")){
		 * sql.append(" and FIND_IN_SET("+secondarydoc+",seconadary_dr) "); }
		 * if(!referto.equals("")){
		 * sql.append(" and FIND_IN_SET("+referto+",refertodept) "); }
		 * sql.append(" order by physio_ipd.commencing,opdbooktime "+orderby+"");
		 */
		sql.append(
				"select physio_ipd.id,commencing,clientname,clientid,opdbooktime,reqdatetime,dept,diaryuserid,diaryusername,refertodept,newpatient,patientcategory from physio_ipd ");
		sql.append(" inner join apm_patient on apm_patient.id = physio_ipd.clientid ");
		sql.append("where commencing between '" + fromDate + "' and '" + toDate + "' and dept=" + string2 + " ");
		/*
		 * if(!department.equals("")){ sql.append(" and dept="+department+" "); }
		 * 
		 * if(!category.equals("")) {
		 * sql.append(" and apm_patient.patientcategory='"+category+"' "); }
		 * if(searchText!=null){
		 * 
		 * sql.append("and clientname like('%"+searchText+"%') ");
		 * 
		 * } if(!patienttype.equals("")) {
		 * sql.append(" and newpatient="+patienttype+""); }
		 * 
		 * if(!patienttype.equals("")) { if(patienttype.equals("1")){
		 * sql.append(" and newpatient=1 "); }else{ sql.append(" and newpatient!=1 "); }
		 * //sql.append(" and newpatient="+patienttype+""); }
		 * 
		 * if(!primarydoc.equals("")){ sql.append(" and diaryuserid="+primarydoc+" "); }
		 * 
		 * if(!secondarydoc.equals("")){
		 * sql.append(" and FIND_IN_SET("+secondarydoc+",seconadary_dr) "); }
		 * 
		 * if(!referto.equals("")){
		 * sql.append(" and FIND_IN_SET("+referto+",refertodept) ");
		 * 
		 * } if(loginInfo.isAmravati()){ sql.append(" order by id asc"); } else{
		 * sql.append(" order by physio_ipd.commencing,opdbooktime "+orderby+""); }
		 */
		sql.append(" order by physio_ipd.commencing,opdbooktime " + orderby + "");
		// sql.append(" order by physio_ipd.commencing,opdbooktime "+orderby+"");

		// String sqls = pagination.getSQLQuery(sql.toString());
		try {

			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NotAvailableSlot notavailable = new NotAvailableSlot();

				notavailable.setId(rs.getInt(1));

				notavailable.setCommencing(rs.getString(2));
				notavailable.setClientName(rs.getString(3));
				notavailable.setClientId(rs.getString(4));
				Client client = clientDAO.getClientDetails(notavailable.getClientId());
				String age = DateTimeUtils.getAge1(client.getDob());
				notavailable.setAgegender(age + " / " + client.getGender());
				notavailable.setMobno(client.getMobNo());
				notavailable.setOpdbooktime(DateTimeUtils.isNull(rs.getString(5)));
				notavailable.setOpdbookdate(DateTimeUtils.getCommencingDate1(rs.getString(6).split(" ")[0]));
				notavailable.setAddress(client.getAddress());
				String reqdatetime = rs.getString(6);
				notavailable.setAbrivationid(client.getAbrivationid());
				/*
				 * String
				 * bookdate=DateTimeUtils.getdatewithmonth(DateTimeUtils.getCommencingDate1(
				 * reqdatetime.split(" ")[0]))+" "+reqdatetime.split(" ")[1];
				 */
				String bookdate = DateTimeUtils.getCommencingDate1(reqdatetime.split(" ")[0]);
				if (loginInfo.isAmravati()) {
					notavailable.setDatetime(reqdatetime);
				} else {
					notavailable.setDatetime(bookdate);
				}
				// notavailable.setDatetime(bookdate);
				String apptdate = DateTimeUtils
						.getdatewithmonth(DateTimeUtils.getCommencingDate1(notavailable.getCommencing())) + " "
						+ notavailable.getOpdbooktime();
				notavailable.setApptdate(apptdate);
				notavailable.setPatientcategory(DateTimeUtils.isNull(client.getPatientcategory()));
				notavailable.setDepartmentname(DateTimeUtils.isNull(chargesReportDAO.getDepartmentName(rs.getInt(7))));
				notavailable.setDiaryUserId(rs.getInt(8));
				notavailable.setDiaryUser(rs.getString(9));
				notavailable.setReferto(rs.getString(10));
				notavailable.setPatienttype(rs.getString(11));
				/*
				 * notavailable.setDept(rs.getString(8));
				 * 
				 * 
				 * notavailable.setPatienttype(rs.getString(10));
				 * 
				 * 
				 * notavailable.setSecondarydoc(DateTimeUtils.isNull(rs.getString(13)));
				 * notavailable.setOpdpmnt(rs.getInt(14));
				 * 
				 * 
				 */

				/*
				 * String secondary_dr=getSecondaryData(""+rs.getInt(1)); String
				 * secondary_dr2=""; String temp[] = secondary_dr.split(","); for (String string
				 * : temp) { if(!string.equals(" ")){ String data=string;
				 * if(secondary_dr2.equals("")){ secondary_dr2=data; }else{
				 * secondary_dr2=secondary_dr2+", "+data; } } }
				 */

				boolean check = checkdeptidsecdr(rs.getInt(1));
				if (check == false) {
					int insert = saveDeptapptid(rs.getInt(1),notavailable.getClientId());
				}

				buffer = buffer.append(rs.getInt(1) + ",");
				notavailable.setOpdappoinmentid("" + buffer);
				// notavailable.setSecondarydoc(secondary_dr2);
				notavailable.setCampArea(client.getCampArea());
				notavailable.setEnrollcode(client.getEnrollcode());
				ArrayList<Emr> secondarylist = new ArrayList<Emr>();
				// String
				// listofsecdr=emrDAO.getsecondarydrlist(notavailable.getClientId(),""+notavailable.getOpdpmnt());
				String listofsecdr = "";
				String data[] = listofsecdr.split(",");
				if (!listofsecdr.equals("")) {
					if (data.length > 0) {
						for (String string : data) {
							Emr emr = new Emr();
							emr.setSecondarydr(userProfileDAO.getFullName(string));
							secondarylist.add(emr);
						}
					} else {
						Emr emr = new Emr();
						emr.setSecondarydr(userProfileDAO.getFullName(listofsecdr));
						secondarylist.add(emr);
					}
				}
				notavailable.setSecondarylist(secondarylist);

				ArrayList<NotAvailableSlot> deptlist = new ArrayList<NotAvailableSlot>();
				List<String> myList = new ArrayList<String>(Arrays.asList(notavailable.getReferto().split(",")));
				for (String string : myList) {
					NotAvailableSlot availableSlot = new NotAvailableSlot();
					String dept = chargesReportDAO.getDepartmentName(DateTimeUtils.convertToInteger(string));
					availableSlot.setDepartmentname(dept);
					deptlist.add(availableSlot);
				}
				notavailable.setDeptlist(deptlist);
				list.add(notavailable);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public int getcountOfDepartmentIPDPreview(String patienttype, String department, String fromDate, String toDate,
			String category, String secondarydoc, String primarydoc, String referto, String orderby) {
		PreparedStatement preparedStatement = null;
		StringBuffer sql = new StringBuffer();
		int res = 0;
		sql.append("select count(*) ");
		sql.append("from  physio_ipd inner join apm_patient on ");
		sql.append("apm_patient.id = physio_ipd.clientid ");
		// sql.append("left join opd_secondary_dr on
		// opd_secondary_dr.deptappointmentid=physio_ipd.id ");
		sql.append("where  commencing between '" + fromDate + "' and '" + toDate + "' ");

		if (!department.equals("")) {
			sql.append(" and dept=" + department + " ");
		}

		if (!category.equals("")) {
			sql.append(" and apm_patient.patientcategory='" + category + "' ");
		}

		if (!patienttype.equals("")) {
			if (patienttype.equals("1")) {
				sql.append(" and newpatient=1 ");
			} else {
				sql.append(" and newpatient!=1 ");
			}
			// sql.append(" and newpatient="+patienttype+"");
		}
		if (!primarydoc.equals("")) {
			sql.append(" and diaryuserid=" + primarydoc + " ");
		}
		/*
		 * if(!secondarydoc.equals("")){
		 * sql.append(" and FIND_IN_SET("+secondarydoc+",seconadary_dr) "); }
		 */
		if (!referto.equals("")) {
			sql.append(" and FIND_IN_SET(" + referto + ",refertodept) ");
		}
		sql.append(" group by dept order by physio_ipd.commencing,opdbooktime " + orderby + "");

		try {

			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				res = res+rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public ArrayList<NotAvailableSlot> getdepartmentIpdList(String patienttype, String department, String fromDate,
			String toDate, Pagination pagination, String category, String secondarydoc, String primarydoc,
			String referto, String orderby, String searchText, LoginInfo loginInfo) {

		FinderDAO finderDAO = new JDBCFinderDAO(connection);
		PreparedStatement preparedStatement = null;
		ArrayList<NotAvailableSlot> list = new ArrayList<NotAvailableSlot>();
		StringBuffer sql = new StringBuffer();


		sql.append("select id,dept from physio_ipd ");
		sql.append("where commencing between '" + fromDate + "' and '" + toDate + "' ");

		if (!department.equals("")) {
			sql.append(" and dept=" + department + " ");
		}

		if (!category.equals("")) {
			sql.append(" and apm_patient.patientcategory='" + category + "' ");
		}
		if (searchText != null) {

			sql.append("and clientname like('%" + searchText + "%') ");

		}
		if (!patienttype.equals("")) {
			sql.append(" and newpatient=" + patienttype + "");
		}

		if (!patienttype.equals("")) {
			if (patienttype.equals("1")) {
				sql.append(" and newpatient=1 ");
			} else {
				sql.append(" and newpatient!=1 ");
			}
			// sql.append(" and newpatient="+patienttype+"");
		}

		if (!primarydoc.equals("")) {
			sql.append(" and diaryuserid=" + primarydoc + " ");
		}
		/*
		 * if(!secondarydoc.equals("")){
		 * sql.append(" and FIND_IN_SET("+secondarydoc+",seconadary_dr) "); }
		 */
		if (!referto.equals("")) {
			sql.append(" and FIND_IN_SET(" + referto + ",refertodept) ");

		}
		if (loginInfo.isAmravati()) {
			sql.append(" order by id asc");
		} else {
			sql.append(" group by dept order by physio_ipd.commencing,opdbooktime " + orderby + "");
		}

		try {
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NotAvailableSlot notavailable = new NotAvailableSlot();
				notavailable.setDepartment(rs.getString(2));
                String deptname=getdepartementname(rs.getString(2));
                notavailable.setDepartmentname(deptname);
				ArrayList<NotAvailableSlot> departmentIPDPreviewList = departmentIPDPreviewList(patienttype, department,
						fromDate, toDate, pagination, category, secondarydoc, primarydoc, referto, orderby, searchText,
						loginInfo, notavailable.getDepartment());
				notavailable.setDepartmentIPDPreviewList(departmentIPDPreviewList);

				list.add(notavailable);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public String getdepartementname(String deptid) {
		PreparedStatement preparedStatement = null;
		String deptname = "";
		try {
			String sql = "select discipline from apm_discipline where id='" + deptid + "' ";
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				deptname = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deptname;
		
	}
	
	

	@Override
	public int updateVaccduedateforvet(String duedate, String mastername, String clientid, String action) {
		int res = 0;
		try {
			String table_name="";
			if(action.equals("0")) {
				table_name="apm_vacination_data";
			}else {
				table_name="veterinary_medicin_data";
			}
			String sql = "update "+table_name+" set duedate='"+duedate+"', duedate_flag=1 where mastername='"+mastername+"' and clientid='"+clientid+"' and givendate is null";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int getDuedate_flagbyclientid(String clientid ,  String action, String mastername) {
		PreparedStatement pst=null;
		int dueflag=0;
		try {
			
			String table_name="";
			if(action.equals("0")) {
				table_name="apm_vacination_data";
			}else {
				table_name="veterinary_medicin_data";
			}
			String sql="select duedate_flag from "+table_name+" where mastername='"+mastername+"' and clientid='"+clientid+"' ";
			pst=connection.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				dueflag=rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dueflag;
	}

	@Override
	public NotAvailableSlot getPetmedcininedataFollowup(String clientId, String mastername,String action) {
		PreparedStatement pst=null;
		NotAvailableSlot notAvailableSlot=new NotAvailableSlot();
		String tablename="";
		try {
			
			if(action.equals("0")) {
				tablename="apm_vacination_data";
			}else {
				tablename="veterinary_medicin_data";
			}
			String sql="select id, masterid, mastername, clientid, vac_productid,consumeid from "+tablename+" where clientid="+clientId+" and mastername='"+mastername+"' order by id desc";
			pst=connection.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				notAvailableSlot.setId(rs.getInt(1));
				notAvailableSlot.setMasterid(rs.getString(2));
				notAvailableSlot.setMastername(rs.getString(3));
				notAvailableSlot.setClientId(rs.getString(4));
				notAvailableSlot.setVac_productid(rs.getString(5));
				notAvailableSlot.setConsumeid(rs.getString(6));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return notAvailableSlot;
	}

	@Override
	public int insertMedicinefollowup(NotAvailableSlot notAvailableSlot, String action) {
	PreparedStatement pst=null;
	int res=0;
	String tablename="";
	try {
		if(action.equals("0")) {
			tablename="apm_vacination_data";
		}else {
			tablename="veterinary_medicin_data";
		}
		String sql="insert into "+tablename+"(masterid, mastername, clientid, duedate, vac_productid,duedate_flag) values(?,?,?,?,?,?)";
		pst=connection.prepareStatement(sql);
		pst.setString(1, notAvailableSlot.getMasterid());
		pst.setString(2, notAvailableSlot.getMastername());
		pst.setString(3, notAvailableSlot.getClientId());
		pst.setString(4, notAvailableSlot.getDuedate());
		pst.setString(5, notAvailableSlot.getVac_productid());
		pst.setString(6, "1");
		res=pst.executeUpdate();
		
		if(res>0){
			
			 ResultSet rs=pst.getGeneratedKeys();
			 while(rs.next()){
				 
				  res=rs.getInt(1);
			 }
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
		return res;
	}

	@Override
	public int updateFollowupid(NotAvailableSlot notAvailableSlot, String action,int id) {
		PreparedStatement pst=null;
		int res=0;
		String tablename="";
		try {
			if(action.equals("0")) {
				tablename="apm_vacination_data";
			}else {
				tablename="veterinary_medicin_data";
			}
			
			String sql="update "+tablename+" set followup_id='"+id+"' where clientid='"+notAvailableSlot.getClientId()+"' and consumeid='"+notAvailableSlot.getConsumeid()+"'";
		    pst=connection.prepareStatement(sql);
		    res=pst.executeUpdate();
		}catch (Exception e) {
		 e.printStackTrace();	
		}
		return res;
	}

	@Override
	public NotAvailableSlot getClientnamebyId(String clientid) {
		PreparedStatement preparedStatement = null;
		NotAvailableSlot notAvailableSlot=new NotAvailableSlot();
		try {
			String sql = "select fullname,mobno from apm_patient where id='" + clientid + "' ";
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				notAvailableSlot.setClientName( rs.getString(1));
				notAvailableSlot.setMobno(rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return notAvailableSlot;
	}

	@Override
	public String getOdmrCount(String fromDate, String toDate) {
		PreparedStatement preparedStatement = null;
		String count = "";
		try {
			String sql = "select count(*) from department_opd where commencing between '"+fromDate+"' and '"+toDate+"' and refertodept='0'and referredfromdept='113' ";
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				count = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
		
	}

	@Override
	public ArrayList<NotAvailableSlot> deptwisecount(String fromDate, String toDate,String referto) {
		PreparedStatement preparedStatement = null;
		ArrayList<NotAvailableSlot> list = new ArrayList<NotAvailableSlot>();
		StringBuffer buffer =new StringBuffer();
		buffer.append("select count(refertodept),refertodept,discipline from department_opd inner join apm_discipline on apm_discipline.id=department_opd.refertodept  where commencing between '"+fromDate+"' and '"+toDate+"' and refertodept!='0' ");
		
		if (!referto.equals("")) {
			buffer.append(" and FIND_IN_SET(" + referto + ",refertodept) ");

		}
	    buffer.append("group by refertodept ");
		try {
			preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				NotAvailableSlot master = new NotAvailableSlot();
				master.setCount(rs.getInt(1));
				master.setRefertodept(rs.getString(2));
				master.setDisciplineName(rs.getString(3));

				
				list.add(master);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
