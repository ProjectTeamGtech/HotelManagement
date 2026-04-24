package com.apm.Ipd.eu.blogic.jdbc;

import java.nio.Buffer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.record.UseSelFSRecord;
import org.apache.struts2.ServletActionContext;

import com.a.a.a.a.a.b;
import com.a.a.a.g.m.q;
import com.apm.Accounts.eu.bi.AccountsDAO;
import com.apm.Accounts.eu.bi.StatementDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAccountsDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCStatementDAO;
import com.apm.Accounts.eu.entity.Accounts;
import com.apm.Appointment.eu.bi.AppointmentTypeDAO;
import com.apm.Appointment.eu.blogic.jdbc.JDBCAppointmentTypeDAO;
import com.apm.AssesmentForms.eu.bi.AssessmentFormDAO;
import com.apm.AssesmentForms.eu.blogic.jdbc.JDBCAssessmentFormDAO;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.bi.CompleteAptmDAO;
import com.apm.DiaryManagement.eu.bi.DiaryManagementDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCCompleteAptmDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCDiaryManagentDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.CompleteAppointment;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.Dietary.eu.bi.DietaryDetailsDAO;
import com.apm.Dietary.eu.blogic.jdbc.JDBCDietaryDetailsDAO;
import com.apm.Dietary.eu.entity.DietaryDetails;
import com.apm.Emr.eu.bi.EmrDAO;
import com.apm.Emr.eu.bi.InvestigationDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCEmrDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCInvestigationDAO;
import com.apm.Emr.eu.entity.Investigation;
import com.apm.InstantMessage.web.action.Email;
import com.apm.Ipd.eu.bi.BedDao;
import com.apm.Ipd.eu.bi.IpdDAO;
import com.apm.Ipd.eu.bi.IpdLogDAO;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Ipd.eu.entity.Discharge;
import com.apm.Ipd.eu.entity.Ipd;
import com.apm.Ipd.web.form.IpdForm;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.bi.TreatmentTypeDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCTreatmentTypeDAO;
import com.apm.Master.eu.entity.Master;

import com.apm.Master.eu.entity.PackageMaster;
import com.apm.Master.eu.entity.SittingFollowup;
import com.apm.Master.eu.entity.TreatmentType;
import com.apm.Mrd.eu.entity.Mrd;
import com.apm.Registration.eu.bi.ClinicDAO;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCClinicDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.ThirdParties.eu.bi.ThirdPartyDAO;
import com.apm.ThirdParties.eu.blogic.jdbc.JDBCThirdPartyDAO;
import com.apm.ThirdParties.eu.entity.ThirdParty;
import com.apm.TreatmentEpisode.eu.entity.TreatmentEpisode;
import com.apm.common.eu.blogic.jdbc.JDBCBaseDAO;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.LoginAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.main.common.constants.Constants;
import com.mysql.jdbc.Statement;
import com.opensymphony.xwork2.ActionContext;

import oracle.jdbc.util.Login;

public class JDBCIpdDAO extends JDBCBaseDAO implements IpdDAO {

	public JDBCIpdDAO(Connection connection) {
		this.connection = connection;
	}

	static HashSet<String> allMails = new HashSet<String>();

	public ArrayList<Bed> getAllBedList(String wardid, int clinicid, LoginInfo loginInfo, String filter_status,
			String action, String activefilter, String isfromandroid, String androidpractid, String excessamtbt) {
		PreparedStatement preparedStatement = null;
		ArrayList<Bed> list = new ArrayList<Bed>();

		CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
		AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
		String sql = "";

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		// cal.add(Calendar.DATE, -7);
		String currDate = dateFormat.format(cal.getTime());

		StringBuffer buffer = new StringBuffer();
		String practid = getUserTableid(loginInfo.getUserId());
		if (loginInfo.isDrwise_ipd()) {

			if (isfromandroid.equals("1")) {
				buffer.append(
						"SELECT apm_ipd_bed.id,apm_ipd_bed.wardid,apm_ipd_bed.sectionid,apm_ipd_bed.bedname,apm_ipd_bed.active FROM apm_ipd_bed   ");
				buffer.append("inner join ipd_addmission_form_new on apm_ipd_bed.id = ipd_addmission_form_new.bedid ");
				buffer.append("where apm_ipd_bed.wardid in(" + wardid + ") and apm_ipd_bed.casualty=" + action
						+ " and practitionerid='" + androidpractid + "' ");
				buffer.append("and apm_ipd_bed.active='" + activefilter + "' ");
			} else {
				if (!wardid.equals("0")) { // previous code
											// if(loginInfo.getUserType()==2){
					/*
					 * sql = "SELECT id,wardid,sectionid,bedname FROM apm_ipd_bed where wardid in("
					 * +wardid+") and active=1 and casualty="+action+" ";
					 */
					/*
					 * sql =
					 * "SELECT id,wardid,sectionid,bedname,active FROM apm_ipd_bed where wardid in("
					 * +wardid+") and casualty="+action+" ";
					 */
					buffer.append(
							"SELECT apm_ipd_bed.id,apm_ipd_bed.wardid,apm_ipd_bed.sectionid,apm_ipd_bed.bedname,apm_ipd_bed.active FROM apm_ipd_bed");
					buffer.append(
							" left join ipd_addmission_form_new on apm_ipd_bed.id = ipd_addmission_form_new.bedid ");
					buffer.append(
							" where apm_ipd_bed.wardid in(" + wardid + ") and apm_ipd_bed.casualty=" + action + "  ");

					buffer.append("and apm_ipd_bed.active='" + activefilter + "' ");
					if (loginInfo.getJobTitle().equals("Practitioner")) {
						buffer.append(" and (ipd_addmission_form_new.practitionerid='" + practid
								+ "' or ipd_addmission_form_new.practitionerid is null) ");
					}

				} else {
					/*
					 * sql =
					 * "SELECT id,wardid,sectionid,bedname FROM apm_ipd_bed where active=1 and casualty="
					 * +action+" ";
					 */
					/*
					 * sql =
					 * "SELECT id,wardid,sectionid,bedname,active FROM apm_ipd_bed where casualty="
					 * +action+" ";
					 */
					buffer.append(
							"SELECT apm_ipd_bed.id,apm_ipd_bed.wardid,apm_ipd_bed.sectionid,apm_ipd_bed.bedname,apm_ipd_bed.active FROM apm_ipd_bed");
					buffer.append(
							" left join ipd_addmission_form_new on apm_ipd_bed.id = ipd_addmission_form_new.bedid ");
					buffer.append(" where apm_ipd_bed.casualty=" + action + "  ");
					buffer.append("and apm_ipd_bed.active='" + activefilter + "' ");
					if (loginInfo.getJobTitle().equals("Practitioner")) {
						buffer.append(" and (ipd_addmission_form_new.practitionerid='" + practid
								+ "' or ipd_addmission_form_new.practitionerid is null )");
					}
				}
			}
		} else {
			if (isfromandroid.equals("1")) {
				buffer.append(
						"SELECT apm_ipd_bed.id,apm_ipd_bed.wardid,apm_ipd_bed.sectionid,apm_ipd_bed.bedname,apm_ipd_bed.active FROM apm_ipd_bed   ");
				buffer.append("inner join ipd_addmission_form_new on apm_ipd_bed.id = ipd_addmission_form_new.bedid ");
				buffer.append("where apm_ipd_bed.wardid in(" + wardid + ") and apm_ipd_bed.casualty=" + action
						+ " and practitionerid='" + androidpractid + "' ");
				buffer.append("and apm_ipd_bed.active='" + activefilter + "' ");
			} else {
//				if (!wardid.equals("0")) { // previous code
//					buffer.append("SELECT id,wardid,sectionid,bedname,active FROM apm_ipd_bed where wardid in(" + wardid+ ") and casualty=" + action + "  ");
//					buffer.append("and active='" + activefilter + "' ");
//
//				} else {
//					buffer.append("SELECT id,wardid,sectionid,bedname,active FROM apm_ipd_bed where casualty=" + action+ "  ");
//					buffer.append("and active='" + activefilter + "' ");
//				}
				buffer.append("SELECT id,wardid,sectionid,bedname,active FROM apm_ipd_bed where wardid in(" + wardid
						+ ") and casualty=" + action + "  ");
				buffer.append("and active='" + activefilter + "' ");
				/*
				 * buffer.
				 * append("select apm_ipd_bed.id,apm_ipd_bed.wardid,sectionid,bedname,active,ipd_addmission_form_new.id,clientid,practitionerid,conditionid,admissiondsate,treatmentepisodeid,speciality,ismlc,ipdseqno,ipdabrivationid  FROM apm_ipd_bed "
				 * ); buffer.
				 * append(" inner join ipd_addmission_form_new on apm_ipd_bed.id=ipd_addmission_form_new.bedid "
				 * ); buffer.append(" where apm_ipd_bed.wardid in(" + wardid+
				 * ") and apm_ipd_bed.casualty=" + action + "  "); buffer.append("and active='"
				 * + activefilter + "'");
				 */
			}

		}
		try {

			preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			int totalbookedbed = 0;
			int totolintitaldischarge = 0;
			int totalbed = 0;
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			// ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			while (rs.next()) {

				Bed bed = new Bed();
				bed.setId(rs.getInt(1));
				bed.setWardid(rs.getString(2));
				bed.setSectionid(rs.getString(3));
				bed.setBedname(rs.getString(4));
				bed.setAction(action);

				String wardname = getIpdWardName(bed.getWardid());
				bed.setWardname(wardname);
				bed.setIsactive(rs.getString(5));

				boolean checkbedstatus = checkBedStatus(bed.getId());
				if (checkbedstatus) {

					totalbookedbed++;
					bed.setStatus("1");
					Ipd ipd = getAddmisionFormDetails(bed.getId());

					bed.setIpdabrivationid(ipd.getIpdabrivationid());

					int autochargeraised = completeAptmDAO.getifAutoChargeRaisedNew(String.valueOf(ipd.getId()));
					bed.setAdmissiondate(ipd.getAdmissiondate());
					bed.setAddmissionid(Integer.toString(ipd.getId()));
					bed.setClientid(ipd.getClientid());
					bed.setPractitionerid(ipd.getPractitionerid());
					bed.setConditionid(ipd.getConditionid());
					bed.setTreatmentepisodeid(ipd.getTreatmentepisodeid());
					bed.setAutochargeraised(String.valueOf(autochargeraised));
					bed.setMlccase(ipd.getMlccase());

					// discharge details
					Discharge discharge = getDischargeDetailsFromView(bed.getTreatmentepisodeid());
					bed.setDis_initiate_status(discharge.getDis_initiate_status());
					bed.setDis_initiate_time(discharge.getDis_initiate_time());
					bed.setDis_form_status(discharge.getDis_form_status());
					bed.setDis_form_time(discharge.getDis_form_time());
					bed.setDis_mdicine_status(discharge.getDis_mdicine_status());
					bed.setDis_mdicine_time(discharge.getDis_mdicine_time());
					bed.setDis_bill_status(discharge.getDis_bill_status());
					bed.setDis_bill_time(discharge.getDis_bill_time());
					bed.setDis_nursing_status(discharge.getDis_bill_status());
					bed.setDis_nursing_time(discharge.getDis_nursing_time());

					bed.setInitialdischargeStatus(discharge.getDis_initiate_status());

					if (DateTimeUtils.isNull(discharge.getDis_initiate_status()).equals("1")) {
						totolintitaldischarge++;
					}

					Client client = clientDAO.getClientDetails(bed.getClientid());
					String clientname = client.getTitle() + " " + client.getFirstName() + " " + client.getMiddlename()
							+ " " + client.getLastName();
					bed.setClientname(clientname);
					bed.setDob(client.getDob());
					if (loginInfo.getIpd_abbr_access() == 1) {
						/* String newipdabbr = getIpdAbrivationIds(ipd.getId()); */
						String newipdabbr = ipd.getIpdabrivationid();
						bed.setIpdseqno(newipdabbr);
					} else {
						bed.setIpdseqno(ipd.getIpdseqno());
					}
					try {
						bed.setAge(DateTimeUtils.getAge1(client.getDob()));
					} catch (Exception e) {
					}

					bed.setTown(client.getTown());
					bed.setWhopay(client.getWhopay());
					bed.setTpid(client.getTypeName());
					bed.setClientemail(client.getEmail());
					bed.setGender(client.getGender());
					bed.setAbrivationid(client.getAbrivationid());

					if (client.getImageName() == null) {
						client.setImageName("");
					}
					bed.setImagename(client.getImageName());

					if (bed.getWhopay() != null) {

						if (bed.getWhopay().equalsIgnoreCase("Client") || bed.getWhopay().equalsIgnoreCase("Self")) {

							bed.setPayby("SELF");
							bed.setTpid("0");
						} else {
							bed.setPayby("TP");
							ThirdParty thirdParty = client.getTpDetails();
							bed.setPayby(thirdParty.getShortname());
							bed.setTpid(client.getTypeName());
							bed.setTpname(thirdParty.getCompanyName());
						}
					}

					bed.setWhopay(client.getWhopay());

					// practitioner details

					if (bed.getPractitionerid() != null) {
						if (bed.getPractitionerid().equals("0") || bed.getPractitionerid().equals("")) {
							bed.setPractitionername("");
							bed.setPractitionerMob("");

						} else {
							UserProfile userProfile = userProfileDAO
									.getUserprofileDetails(Integer.parseInt(bed.getPractitionerid()));
							String practitionername = userProfile.getInitial() + " " + userProfile.getFirstname() + " "
									+ userProfile.getLastname();
							bed.setPractitionername(practitionername);
							bed.setPractitionerMob(userProfile.getMobile());
						}
					} else {
						bed.setPractitionername("");
						bed.setPractitionerMob("");
					}

					/*
					 * int invsid = getInvsIfExist(bed.getClientid()); bed.setInvsid(invsid);
					 */
					if (excessamtbt.equals("1")) {
						// get autocharge balance amount
						ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
						String tempdate[] = ipd.getAdmissiondate().split(" ");
						double debitAmount = getDebitAmountList(bed.getClientid(), tempdate[0]);
						// double advanceAmt = getAdvanceAmt(bed.getClientid());
						double advanceamt = getAdvanceAmount(bed.getClientid(), tempdate[0]);
						double paidamount = getPaymentAmount(bed.getClientid(), tempdate[0]);

						double balance = debitAmount - (advanceamt + paidamount);

						Clinic clinic = clinicDAO.getCliniclistDetails(clinicid);
						if (clinic.getExcess() != null) {
							if (clinic.getExcess().equals("")) {
								clinic.setExcess("0");
							}
						} else {
							clinic.setExcess("0");
						}

						double excessAmt = Double.parseDouble(clinic.getExcess());

						if (balance > 0) {
							if (balance >= excessAmt) {
								bed.setExcessAmt("1");
							} else {
								bed.setExcessAmt("0");
							}
						}
					}

				} else {
					bed.setStatus("0");
				}

				bed.setTotalbookedbed(totalbookedbed);
				bed.setTotolintitaldischarge(totolintitaldischarge);
				totalbed++;
				bed.setTotalbed(totalbed);
				Accounts accounts = accountsDAO.getPatientTransactiondata(bed.getClientid());
				bed.setBalance(accounts.getBalance());
				bed.setAdvance(accounts.getAdvance());
				list.add(bed);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

		return list;
	}

	private Discharge getDischargeDetailsFromView(String treatmentepisodeid) {

		PreparedStatement preparedStatement = null;
		Discharge discharge = new Discharge();
		String sql = "select dis_initiate_time, dis_initiate_status, dis_form_time, dis_form_status, "
				+ "dis_mdicine_time, dis_mdicine_status, dis_bill_time, dis_bill_status, dis_nursing_time, "
				+ "dis_nursing_status,treatmentstatus,dischargedate from discharge_form where id=" + treatmentepisodeid
				+ " ";
		// view_apm_treatment_episode
		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				discharge.setDis_initiate_time(rs.getString(1));
				discharge.setDis_initiate_status(rs.getString(2));
				discharge.setDis_form_time(rs.getString(3));
				discharge.setDis_form_status(rs.getString(4));
				discharge.setDis_mdicine_time(rs.getString(5));
				discharge.setDis_mdicine_status(rs.getString(6));
				discharge.setDis_bill_time(rs.getString(7));
				discharge.setDis_bill_status(rs.getString(8));
				discharge.setDis_nursing_time(rs.getString(9));
				discharge.setDis_nursing_status(rs.getString(10));
				discharge.setDis_discharge_status(rs.getInt(11));
				discharge.setDis_discharge_date(rs.getString(12));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return discharge;

	}

	private double getPaymentAmount(String clientid, String date) {
		PreparedStatement preparedStatement = null;
		double result = 0;

		String sql = "select sum(payment) from apm_charges_payment where clientid = '" + clientid + "'  and tpid = 0  "
				+ " and  date >='" + date + "'   ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getDouble(1);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	private double getAdvanceAmount(String clientid, String date) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "SELECT balance FROM apm_credit_account where client_id = '" + clientid
				+ "' and payment_mode is not null " + " and  date >='" + date + "'  order by id desc  ";

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

	private int getAprovedInvestigation(int id) {
		int res = 0;
		try {
			String sql = "select count(*) from apm_client_parent_investigation where ipdid ='" + id + "' and seen='0'";
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

	private int getInvsIfExist(String clientId) {

		int result = 0;
		try {
			String sql = "select id from apm_client_parent_investigation where clientid=" + clientId + " limit 1 ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				result = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getAllActiveStdCharges(int id) {

		String result = "";

		try {
			String sql = "select std_charge_id from apm_standard_acess_charge where ipdid=" + id + "";

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

	public boolean checkStandardChargeExist(int id) {

		PreparedStatement ps = null;

		boolean result = false;
		String sql = "SELECT * FROM apm_standard_acess_charge where ipdid=" + id + "";
		try {

			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				result = true;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	private double getAdvanceAmt(String clientid) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "SELECT sum(charge) FROM apm_credit_account where client_id = " + clientid
				+ " and payby ='Client' ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getDouble(1);
			}

		} catch (Exception e) {
			e.printStackTrace(); // TODO: handle exception
		}
		return result;
	}

	private double getPaidAmount(String clientid) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "SELECT sum(payment) FROM apm_charges_payment where clientid = " + clientid + " and tpid = 0 ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getDouble(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return result;
	}

	public Discharge getDischargeDetails(String treatmentepisodeid) {
		PreparedStatement preparedStatement = null;
		Discharge discharge = new Discharge();
		String sql = "select dis_initiate_time, dis_initiate_status, dis_form_time, dis_form_status, "
				+ "dis_mdicine_time, dis_mdicine_status, dis_bill_time, dis_bill_status, dis_nursing_time, "
				+ "dis_nursing_status,treatmentstatus,dischargedate from apm_treatment_episode where id="
				+ treatmentepisodeid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				discharge.setDis_initiate_time(rs.getString(1));
				discharge.setDis_initiate_status(rs.getString(2));
				discharge.setDis_form_time(rs.getString(3));
				discharge.setDis_form_status(rs.getString(4));
				discharge.setDis_mdicine_time(rs.getString(5));
				discharge.setDis_mdicine_status(rs.getString(6));
				discharge.setDis_bill_time(rs.getString(7));
				discharge.setDis_bill_status(rs.getString(8));
				discharge.setDis_nursing_time(rs.getString(9));
				discharge.setDis_nursing_status(rs.getString(10));
				discharge.setDis_discharge_status(rs.getInt(11));
				discharge.setDis_discharge_date(rs.getString(12));
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return discharge;
	}

	private Priscription getIpdPrescriptionList(int id, long mdicinedays, String dosecolumn, String mdicinename,
			String dosage) {
		PreparedStatement preparedStatement = null;
		Priscription priscription = new Priscription();
		String sql = "SELECT id,dos1,dos2,dos3,dos4,dos5,dos6,dos7,dos8,dos9,dos10,userid,days FROM ipd_prescription_reminder where prescid = "
				+ id + " and days = " + mdicinedays + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				priscription.setId(rs.getInt(1));
				priscription.setMdicinenametxt(mdicinename);
				priscription.setDosage(dosage);

				String temp[] = dosecolumn.split(",");
				priscription.setDosesize(temp.length);

				priscription.setDos1(rs.getBoolean(2));
				priscription.setDos2(rs.getBoolean(3));
				priscription.setDos3(rs.getBoolean(4));
				priscription.setDos4(rs.getBoolean(5));
				priscription.setDos5(rs.getBoolean(6));
				priscription.setDos6(rs.getBoolean(7));
				priscription.setDos7(rs.getBoolean(8));
				priscription.setDos8(rs.getBoolean(9));
				priscription.setDos9(rs.getBoolean(10));
				priscription.setDos10(rs.getBoolean(11));
				priscription.setUserid(rs.getString(12));
				priscription.setCurrdays(rs.getInt(13));
				String values[] = dosage.split("-");

				if (values.length < 3) {
					dosage = getAlterNateDose(dosage);
					values = dosage.split("-");
				}

				if (values.length <= 3) {

					priscription.setDosevalue1(values[0]);
					priscription.setDosevalue2(values[1]);
					priscription.setDosevalue3(values[2]);

				} else if (values.length == 4) {
					priscription.setDosevalue1(values[0]);
					priscription.setDosevalue2(values[1]);
					priscription.setDosevalue3(values[2]);
					priscription.setDosevalue4(values[3]);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return priscription;
	}

	private Master getIpdNursingList(int id, long mdicinedays, String dosecolumn, String mdicinename, String dosage) {
		PreparedStatement preparedStatement = null;
		Master priscription = new Master();
		String sql = "SELECT id,dos1,dos2,dos3,dos4,dos5,dos6,dos7,dos8,dos9,dos10 FROM ipd_nursing_reminder where nursingid = "
				+ id + " and days = " + mdicinedays + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				priscription.setId(rs.getInt(1));
				priscription.setTaskname(mdicinename);
				priscription.setFrequency(dosage);

				String temp[] = dosecolumn.split(",");
				priscription.setDosesize(temp.length);

				priscription.setDos1(rs.getBoolean(2));
				priscription.setDos2(rs.getBoolean(3));
				priscription.setDos3(rs.getBoolean(4));
				priscription.setDos4(rs.getBoolean(5));
				priscription.setDos5(rs.getBoolean(6));
				priscription.setDos6(rs.getBoolean(7));
				priscription.setDos7(rs.getBoolean(8));
				priscription.setDos8(rs.getBoolean(9));
				priscription.setDos9(rs.getBoolean(10));
				priscription.setDos10(rs.getBoolean(11));

				String values[] = dosage.split("-");

				if (values.length <= 3) {

					priscription.setDosevalue1(values[0]);
					priscription.setDosevalue2(values[1]);
					priscription.setDosevalue3(values[2]);

				} else if (values.length == 4) {
					priscription.setDosevalue1(values[0]);
					priscription.setDosevalue2(values[1]);
					priscription.setDosevalue3(values[2]);
					priscription.setDosevalue4(values[3]);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return priscription;
	}

	public String getIpdWardName(String wardid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT wardname FROM apm_ipd_ward where id  = " + wardid + " ";

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

	private Ipd getAddmisionFormDetails(int bedid) {
		PreparedStatement preparedStatement = null;
		Ipd ipd = new Ipd();
		String sql = "SELECT id,clientid,practitionerid,conditionid,admissiondsate,treatmentepisodeid,speciality,ismlc,ipdseqno,ipdabrivationid FROM ipd_addmission_form_new where bedid="
				+ bedid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				ipd.setId(rs.getInt(1));
				ipd.setClientid(rs.getString(2));
				ipd.setPractitionerid(rs.getString(3));
				ipd.setConditionid(rs.getString(4));
				if (rs.getString(5) != null) {
					String temp[] = rs.getString(5).split(" ");
					ipd.setAdmissiondate(DateTimeUtils.getCommencingDate1(temp[0]));

				} else {
					ipd.setAdmissiondate("");
				}

				ipd.setTreatmentepisodeid(rs.getString(6));
				ipd.setConditionid(rs.getString(7));
				ipd.setMlccase(rs.getString(8));
				ipd.setIpdseqno(rs.getString(9));
				ipd.setIpdabrivationid(rs.getString(10));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ipd;
	}

	public boolean checkBedStatus(int id) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "SELECT id FROM ipd_addmission_form_new where bedid = " + id + " ";

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

	public ArrayList<Master> getFilteredChargeList(String chargetype, String tpid, String wardid, String clientId,
			boolean show_wardname) {
		PreparedStatement preparedStatement = null;

		ArrayList<Master> list = new ArrayList<Master>();

		boolean checkInventoryChargeType = checkInventoryChargeType(chargetype);
		int isward = checkChargeTypeWard(chargetype);
		String sql = "";

		if (chargetype == null) {

			chargetype = "";
		}

		if (!checkifMainTp(tpid)) {

			String temptpid = getFollowerTp(tpid);
			if (temptpid != null) {

				if (!temptpid.equals("0")) {
					tpid = temptpid;
				}
			}

	}

		if (tpid.equals("")) {
			tpid = "0";
		}
		if (chargetype == null) {

			chargetype = "";
		}

		/*
		 * if(checkInventoryChargeType){
		 * 
		 * if(chargetype.equals("INVESTIGATION")){
		 * 
		 * sql = "SELECT id,name FROM apm_appointment_type where tpid="
		 * +tpid+" and chargeType='" + chargetype + "' order by name "; } else {
		 * sql = "SELECT id,name FROM apm_appointment_type where tpid="
		 * +tpid+" and chargeType='" + chargetype + "'  order by name "; }
		 * }else{ sql =
		 * "SELECT inventory_product.id,prodname,stock FROM inventory_product "
		 * +
		 * "inner join inventory_category on inventory_category.id = inventory_product.categoryid "
		 * + "where inventory_category.name = '"
		 * +chargetype+"'  order by prodname "; }
		 */

		// wardid = "0";
		AssessmentFormDAO assessmentFormDAO = new JDBCAssessmentFormDAO(connection);
		int bedid = assessmentFormDAO.getIpdBedno(clientId);
		/*
		 * sql = "SELECT id,name FROM apm_appointment_type where tpid="
		 * +tpid+" and chargeType='" + chargetype +
		 * "' and wardid = "+wardid+"  and stdcharge=0 order by name "; //get
		 * ipd details AssessmentFormDAO assessmentFormDAO = new
		 * JDBCAssessmentFormDAO(connection); int bedid =
		 * assessmentFormDAO.getIpdBedno(clientId);
		 * 
		 * if(bedid!=0){ IpdDAO ipdDAO = new JDBCIpdDAO(connection); BedDao
		 * bedDao=new JDBCBedDao(connection);
		 * 
		 * String ipdid = assessmentFormDAO.getAdmissionid(clientId); Bed bed =
		 * bedDao.getEditIpdData(ipdid); wardid = bed.getWardid();
		 * 
		 * sql = "SELECT id,name FROM apm_appointment_type where tpid="
		 * +tpid+" and chargeType='" + chargetype +
		 * "' and wardid = "+wardid+"  and stdcharge=0 order by name "; }
		 */

		StringBuffer buffer = new StringBuffer();
		//   16 MAY 2018
		if (isward == 1) {
			// With Wardid
			buffer.append("SELECT id,name FROM apm_appointment_type where tpid=" + tpid + " and chargeType='"
					+ chargetype + "' and stdcharge=0 ");
			if (bedid != 0) {
				BedDao bedDao = new JDBCBedDao(connection);
				String ipdid = assessmentFormDAO.getAdmissionid(clientId);
				Bed bed = bedDao.getEditIpdData(ipdid);
				wardid = bed.getWardid();
			}

			if (!tpid.equals("0")) {
				wardid = "0";
			}
			buffer.append("and wardid = " + wardid + " ");
			buffer.append("order by name");
			}else {
				// Without Wardid
				buffer.append("SELECT id,name FROM apm_appointment_type where tpid=" + tpid + " and chargeType='"
						+ chargetype + "' and stdcharge=0 ");
				buffer.append("order by name");
			}
		/*
		 * if(!wardid.equals("0")) { if(bedid != 0) { // With Wardid
		 * buffer.append("SELECT id,name FROM apm_appointment_type where tpid=" + tpid +
		 * " and chargeType='" + chargetype + "' ");
		 * if(chargetype.equals("EQUIPMENT CHARGES")) {
		 * buffer.append("and stdcharge=1 "); }else { buffer.append("and stdcharge=0 ");
		 * } if (bedid != 0) { BedDao bedDao = new JDBCBedDao(connection); String ipdid
		 * = assessmentFormDAO.getAdmissionid(clientId); Bed bed =
		 * bedDao.getEditIpdData(ipdid); wardid = bed.getWardid(); }
		 * 
		 * 
		 * if (!tpid.equals("0")) { wardid = "0"; }
		 * 
		 * if(chargetype.equals("INVESTIGATION")) { wardid = "0"; }
		 * 
		 * if(chargetype.equals("Other Charges")) { wardid = "0"; }
		 * 
		 * buffer.append("and wardid = " + wardid + " ");
		 * buffer.append("order by name"); } }
		 *//*
			 * else { // Without Wardid
			 * buffer.append("SELECT id,name FROM apm_appointment_type where tpid=" + tpid +
			 * " and chargeType='" + chargetype + "' and stdcharge=0 ");
			 * buffer.append("order by name"); }
			 */
		JDBCIpdDAO jdbcIpdDAO = new JDBCIpdDAO(connection);
		try {
			/* preparedStatement = connection.prepareStatement(sql); */
			preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			BedDao bedDao = new JDBCBedDao(connection);
			while (rs.next()) {

				Master master = new Master();
				master.setId(rs.getInt(1));
				//   13/11 Showing Apointment NAme with wardname

				if (show_wardname == true) {
					if (wardid == null) {
						wardid = "";
					}
					if (wardid.equals("")) {
						wardid = "0";

					}
					if (wardid.equals("0")) {
						master.setName(rs.getString(2));
					}
					String wardname = bedDao.getWardName(wardid);
					master.setName(rs.getString(2) + "(" + wardname + ")");
				} else {
					master.setName(rs.getString(2));
				}
				if (!checkInventoryChargeType) {
					master.setName(rs.getString(2) + " (" + rs.getString(3) + ")");
				}

				list.add(master);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int checkChargeTypeWard(String chargetype) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT ward FROM apm_newchargetype where name='" + chargetype + "' ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
		return result;
	}

	public ArrayList<Master> getAdditionalFilteredChargeList(String chargetype, String tpid, String wardid,
			String clientId) {
		PreparedStatement preparedStatement = null;

		ArrayList<Master> list = new ArrayList<Master>();

		boolean checkInventoryChargeType = checkInventoryChargeType(chargetype);

		String sql = "";

		if (chargetype == null) {

			chargetype = "";
		}

		if (!checkifMainTp(tpid)) {

			String temptpid = getFollowerTp(tpid);
			if (temptpid != null) {

				if (!temptpid.equals("0")) {
					tpid = temptpid;
				}
			}

		}

		if (tpid.equals("")) {
			tpid = "0";
		}
		if (chargetype == null) {

			chargetype = "";
		}

		/*
		 * if(checkInventoryChargeType){
		 * 
		 * if(chargetype.equals("INVESTIGATION")){
		 * 
		 * sql = "SELECT id,name FROM apm_appointment_type where tpid="
		 * +tpid+" and chargeType='" + chargetype + "' order by name "; } else { sql =
		 * "SELECT id,name FROM apm_appointment_type where tpid="
		 * +tpid+" and chargeType='" + chargetype + "'  order by name "; } }else{ sql =
		 * "SELECT inventory_product.id,prodname,stock FROM inventory_product " +
		 * "inner join inventory_category on inventory_category.id = inventory_product.categoryid "
		 * + "where inventory_category.name = '" +chargetype+"'  order by prodname "; }
		 */
		sql = "SELECT id,name FROM apm_appointment_type where tpid=" + tpid + " and chargeType='" + chargetype
				+ "' and stdcharge=0 order by name ";
		// get ipd details
		/*
		 * AssessmentFormDAO assessmentFormDAO = new JDBCAssessmentFormDAO(connection);
		 * int bedid = assessmentFormDAO.getIpdBedno(clientId); wardid = "0";
		 * if(bedid!=0){ IpdDAO ipdDAO = new JDBCIpdDAO(connection); BedDao bedDao=new
		 * JDBCBedDao(connection);
		 * 
		 * String ipdid = assessmentFormDAO.getAdmissionid(clientId); Bed bed =
		 * bedDao.getEditIpdData(ipdid); wardid = bed.getWardid();
		 * 
		 * sql = "SELECT id,name FROM apm_appointment_type where tpid="
		 * +tpid+" and chargeType='" + chargetype +
		 * "' and wardid = "+wardid+"  and stdcharge=0 order by name "; }
		 */

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				if (!checkInventoryChargeType) {
					master.setName(rs.getString(2) + " (" + rs.getString(3) + ")");
				}

				list.add(master);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Master> getProcedureChargeList(String chargetype, String clienId, String otplanned) {
		PreparedStatement preparedStatement = null;

		ArrayList<Master> list = new ArrayList<Master>();

		boolean checkInventoryChargeType = checkInventoryChargeType(chargetype);

		String sql = "";
		/*
		 * if(checkInventoryChargeType){
		 * 
		 * sql =
		 * "SELECT id,name FROM apm_appointment_type where tpid=0 and chargeType='" +
		 * chargetype + "' order by name "; }else{ sql =
		 * "SELECT inventory_product.id,prodname,stock FROM inventory_product " +
		 * "inner join inventory_category on inventory_category.id = inventory_product.categoryid "
		 * + "where inventory_category.name = '" +chargetype+"'  order by prodname "; }
		 */

		ClientDAO clientDAO = new JDBCClientDAO(connection);
		InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);

		Client client = clientDAO.getClientDetails(clienId);
		// get ipd details
		AssessmentFormDAO assessmentFormDAO = new JDBCAssessmentFormDAO(connection);
		int bedid = assessmentFormDAO.getIpdBedno(clienId);
		String wardid = "0";
		String whopay = client.getWhopay();
		String tpid = "";
		if (bedid != 0) {
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			BedDao bedDao = new JDBCBedDao(connection);

			String ipdid = assessmentFormDAO.getAdmissionid(clienId);
			Bed bed = bedDao.getEditIpdData(ipdid);
			wardid = bed.getWardid();

		}

		if (otplanned.equals("Unplaned")) {
			wardid = "0";
		}

		sql = "SELECT id,name,charges FROM apm_appointment_type where chargeType='" + chargetype
				+ "' and tpid = 0 and wardid=" + wardid + " and otchargetype in(1,2,3,4,5) ";

		if (whopay.equals(Constants.PAY_BY_THIRD_PARTY)) {
			int followup = investigationDAO.getTpFollowupid(tpid);
			if (followup != 0) {
				tpid = Integer.toString(followup);
			}
			sql = "SELECT id,name,charges FROM apm_appointment_type where chargeType='" + chargetype + "' and tpid = "
					+ tpid + " and wardid=" + wardid + " and otchargetype in(1,2,3,4,5) ";
		}

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				master.setCharge(rs.getString(3));

				list.add(master);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Master> getOtFilteredChargeList(String chargetype, String clienId) {
		PreparedStatement preparedStatement = null;

		ArrayList<Master> list = new ArrayList<Master>();

		boolean checkInventoryChargeType = checkInventoryChargeType(chargetype);

		String sql = "";
		/*
		 * if(checkInventoryChargeType){
		 * 
		 * sql =
		 * "SELECT id,name FROM apm_appointment_type where tpid=0 and chargeType='" +
		 * chargetype + "' order by name "; }else{ sql =
		 * "SELECT inventory_product.id,prodname,stock FROM inventory_product " +
		 * "inner join inventory_category on inventory_category.id = inventory_product.categoryid "
		 * + "where inventory_category.name = '" +chargetype+"'  order by prodname "; }
		 */

		ClientDAO clientDAO = new JDBCClientDAO(connection);
		InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
		AppointmentTypeDAO appointmentTypeDAO = new JDBCAppointmentTypeDAO(connection);

		Client client = clientDAO.getClientDetails(clienId);
		// get ipd details
		AssessmentFormDAO assessmentFormDAO = new JDBCAssessmentFormDAO(connection);
		int bedid = assessmentFormDAO.getIpdBedno(clienId);
		String wardid = "0";
		String whopay = client.getWhopay();
		String tpid = "0";
		if (bedid != 0) {
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			BedDao bedDao = new JDBCBedDao(connection);

			String ipdid = assessmentFormDAO.getAdmissionid(clienId);
			Bed bed = bedDao.getEditIpdData(ipdid);
			wardid = bed.getWardid();

		}

		// book ot charge only
		boolean checkchargeexsist = appointmentTypeDAO.checkChargeExisist(chargetype, Constants.OT_CHARGE, wardid, tpid,
				"1");
		if (!checkchargeexsist) {
			int res = appointmentTypeDAO.saveOtCharges(chargetype, Constants.OT_CHARGE, "0", wardid, tpid, "1",
					"01:00");
		}

		sql = "SELECT id,name,chargeType FROM apm_appointment_type where chargeType='" + chargetype
				+ "' and tpid = 0 and wardid=" + wardid + " and otchargetype = 1 ";

		if (whopay.equals(Constants.PAY_BY_THIRD_PARTY)) {
			int followup = investigationDAO.getTpFollowupid(tpid);
			if (followup != 0) {
				tpid = Integer.toString(followup);
			}
			sql = "SELECT id,name,chargeType FROM apm_appointment_type where chargeType='" + chargetype
					+ "' and tpid = " + tpid + " and wardid=" + wardid + " and otchargetype = 1 ";
		}

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				if (!checkInventoryChargeType) {
					master.setName(rs.getString(2) + " (" + rs.getString(3) + ")");
				}
				master.setChargetype(rs.getString(3));
				list.add(master);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean checkInventoryChargeType(String chargetype) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "SELECT * FROM apm_newchargetype where name='" + chargetype + "' ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
		return result;
	}

	public ArrayList<Bed> getWardWiseBedList(String wardid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Bed> list = new ArrayList<Bed>();
		String sql = "SELECT  id,bedname FROM apm_ipd_bed where wardid=" + wardid + " and active=1 ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Bed bed = new Bed();
				bed.setId(rs.getInt(1));
				bed.setBedname(rs.getString(2));

				boolean checkbedstatus = checkBedStatus(bed.getId());

				if (!checkbedstatus) {
					list.add(bed);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Bed> geteditWardWiseBedList(String wardid, String bedid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Bed> list = new ArrayList<Bed>();
		String sql = "SELECT  id,bedname FROM apm_ipd_bed where wardid=" + wardid + "  and active=1";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Bed bed = new Bed();
				bed.setId(rs.getInt(1));
				bed.setBedname(rs.getString(2));

				boolean checkbedstatus = checkBedStatus(bed.getId());

				if (Integer.parseInt(bedid) == bed.getId()) {
					list.add(bed);
				}

				if (!checkbedstatus) {
					list.add(bed);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public String getIpdBedName(String bedid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT bedname FROM apm_ipd_bed where id = " + bedid + " ";

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

	public String getTreatmentEpisodeDischargeStatus(String tepisodeid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT treatmentstatus FROM apm_treatment_episode where id = " + tepisodeid + " ";

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

	public ArrayList<Bed> getBookedBedList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Bed> list = new ArrayList<Bed>();
		String sql = "SELECT id FROM ipd_addmission_form where bedid!=0 ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Bed bed = new Bed();
				bed.setAddmissionid(rs.getString(1));
				list.add(bed);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Priscription> getParentPriscList(String addmissionid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Priscription> list = new ArrayList<Priscription>();
		String sql = "SELECT id,lastmodified FROM apm_client_parent_priscription where ipdid = " + addmissionid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Priscription priscription = new Priscription();
				priscription.setParentid(rs.getString(1));
				priscription.setDate(rs.getString(2));

				list.add(priscription);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public ArrayList<Priscription> getClientPriscList(String parentid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Priscription> list = new ArrayList<Priscription>();
		String sql = "SELECT id,days,dose,mdicinename,notes,prisctimename,priscremark,ipdtimeshow FROM apm_client_priscription where parentid = "
				+ parentid + " and isipdremove=0 and isnurseprisc='0' ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Priscription priscription = new Priscription();
				priscription.setId(rs.getInt(1));
				priscription.setDays(rs.getInt(2));
				priscription.setDosage(rs.getString(3));
				priscription.setMdicinenametxt(rs.getString(4));
				priscription.setDosenotes(rs.getString(5));
				priscription.setPrisctimename(rs.getString(6));
				priscription.setPriscindivisualremark(rs.getString(7));
				priscription.setDosegiventime(rs.getString(8));
				list.add(priscription);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean checkPrescExist(long mdicinedays, int id) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "SELECT * FROM ipd_prescription_reminder where prescid = " + id + " and days = " + mdicinedays
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

	public int savePrescReminder(String dosecolumn, String doseqmark, long days, int prescid, String ipdid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into ipd_prescription_reminder(" + dosecolumn + ",prescid,days,ipdid) values(" + doseqmark
				+ "," + prescid + "," + days + "," + ipdid + ")";

		try {
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updatePriscReminder(String selectedid, String colname, boolean savetogle, String userid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update ipd_prescription_reminder set " + colname + "=?,userid=? where id = " + selectedid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setBoolean(1, savetogle);
			preparedStatement.setString(2, userid);

			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateNursingReminder(String selectedid, String colname, boolean savetogle, String userid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update ipd_nursing_reminder set " + colname + "=?, userid=? where id = " + selectedid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setBoolean(1, savetogle);
			preparedStatement.setString(2, userid);

			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Bed> getTreatmentSheetList(String clientid, String[] calldays) {

		String daybefore2 = calldays[0];
		String daybefore1 = calldays[1];
		String today = calldays[2];
		String nextday = calldays[3];

		boolean flagday1 = false;
		boolean flagday2 = false;
		boolean flagtoday = false;

		ArrayList<Bed> list = new ArrayList<Bed>();
		String datetime = "";

		try {

			String sql = "SELECT id, practitionerid, conditionid, categoryid, mdicineid, mdicinename, dose, frequency, days, lastmodified, code, type, total, parentid, priscdurationtype FROM apm_client_priscription where clientid="
					+ clientid + " and lastmodified between '" + daybefore2 + "' and '" + nextday + "';";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Bed bed = new Bed();

				String lastmodif = rs.getString(10);

				String tempdate = lastmodif.substring(0, 10);
				if (tempdate.equals(daybefore2)) {

					flagday2 = true;

					bed.setId(rs.getInt(1));
					bed.setPractitionerid(rs.getString(2));
					bed.setConditionid(rs.getString(3));
					bed.setMedicinename(rs.getString(6));
					bed.setDose(rs.getString(7));
					bed.setLastmodified(rs.getString(10));

					String query = "select id,presc_rem_id, dosename, dstatus, ddatetime from ipd_dosage_given where prisc_id='"
							+ bed.getId() + "'";

					PreparedStatement ps1 = connection.prepareStatement(query);
					ResultSet rs1 = ps1.executeQuery();

					String str[] = bed.getDose().split("-");
					int count = str.length;
					int tmp = -1;
					boolean flag = false;
					String temp[] = new String[count];
					while (rs1.next()) {
						flag = true;
						String dosename = rs1.getString(3);
						String status = rs1.getString(4);
						String date = rs1.getString(5);
						String tdate = date.substring(11, 16);
						String data = dosename + "-" + status + "-" + tdate;
						temp[++tmp] = data;
					}

					if (!flag) {
						datetime = "_/_/_";
					} else {
						datetime = getSortedTimeStamp(temp, tmp, count);
					}
					bed.setDate3(datetime);

				} else if (tempdate.equals(daybefore1)) {

					flagday1 = true;
					bed.setId(rs.getInt(1));
					bed.setPractitionerid(rs.getString(2));
					bed.setConditionid(rs.getString(3));
					bed.setMedicinename(rs.getString(6));
					bed.setDose(rs.getString(7));
					bed.setLastmodified(rs.getString(10));

					String query = "select id,presc_rem_id, dosename, dstatus, ddatetime from ipd_dosage_given where prisc_id='"
							+ bed.getId() + "'";

					PreparedStatement ps1 = connection.prepareStatement(query);
					ResultSet rs1 = ps1.executeQuery();

					String str[] = bed.getDose().split("-");
					int count = str.length;
					int tmp = -1;
					boolean flag = false;
					String temp[] = new String[count];
					while (rs1.next()) {
						flag = true;
						String dosename = rs1.getString(3);
						String status = rs1.getString(4);
						String date = rs1.getString(5);
						String tdate = date.substring(11, 16);
						String data = dosename + "-" + status + "-" + tdate;
						temp[++tmp] = data;
					}

					if (!flag) {
						datetime = "_/_/_";
					} else {
						datetime = getSortedTimeStamp(temp, tmp, count);
					}
					bed.setDate2(datetime);
				} else {

					flagtoday = true;
					bed.setId(rs.getInt(1));
					bed.setPractitionerid(rs.getString(2));
					bed.setConditionid(rs.getString(3));
					bed.setMedicinename(rs.getString(6));
					bed.setDose(rs.getString(7));
					bed.setLastmodified(rs.getString(10));

					String query = "select id,presc_rem_id, dosename, dstatus, ddatetime from ipd_dosage_given where prisc_id='"
							+ bed.getId() + "'";

					PreparedStatement ps1 = connection.prepareStatement(query);
					ResultSet rs1 = ps1.executeQuery();

					String str[] = bed.getDose().split("-");
					int count = str.length;
					int tmp = -1;
					boolean flag = false;
					String temp[] = new String[count];
					while (rs1.next()) {
						flag = true;
						String dosename = rs1.getString(3);
						String status = rs1.getString(4);
						String date = rs1.getString(5);
						String tdate = date.substring(11, 16);
						String data = dosename + "-" + status + "-" + tdate;
						temp[++tmp] = data;
					}

					if (!flag) {
						datetime = "_/_/_";
					} else {
						datetime = getSortedTimeStamp(temp, tmp, count);
					}
					bed.setDatetime(datetime);

				}

				if (!flagday1) {
					bed.setDate2("N/A");
				}
				if (!flagday2) {
					bed.setDate3("N/A");
				}
				if (!flagtoday) {
					bed.setDatetime("N/A");
				}

				list.add(bed);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	private String getSortedTimeStamp(String[] temp, int count, int length) {

		String datetime = "";

		String time[] = { "_", "_", "_", "_", "_", "_", "_", "_", "_", "_" };

		try {

			for (int i = 0; i <= count; i++) {

				String str[] = temp[i].split("-");
				if (str[0].equals("dos1")) {

					if (str[1].equals("1")) {

						time[0] = str[2];

					} else {
						time[0] = "_";
					}

				}
				if (str[0].equals("dos2")) {

					if (str[1].equals("1")) {

						time[1] = str[2];

					} else {
						time[1] = "_";
					}

				}
				if (str[0].equals("dos3")) {

					if (str[1].equals("1")) {

						time[2] = str[2];

					} else {
						time[2] = "_";
					}

				}
				if (str[0].equals("dos4")) {

					if (str[1].equals("1")) {

						time[3] = str[2];

					} else {
						time[3] = "_";
					}

				}
				if (str[0].equals("dos5")) {

					if (str[1].equals("1")) {

						time[4] = str[2];

					} else {
						time[4] = "_";
					}

				}
				if (str[0].equals("dos6")) {

					if (str[1].equals("1")) {

						time[5] = str[2];

					} else {
						time[5] = "_";
					}
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		if (length <= 3) {

			datetime = time[0] + "/" + time[1] + "/" + time[2];
		} else if (length == 4) {
			datetime = time[0] + "/" + time[1] + "/" + time[2] + "/" + time[3];
		} else if (length == 5) {
			datetime = time[0] + "/" + time[1] + "/" + time[2] + "/" + time[3] + "/" + time[4];
		}

		return datetime;
	}

	public int updateorInsert(String id, String colname, String doseval, String datetime, String priscid) {

		int result = 0;

		int status = 0;
		if (doseval.equals("false")) {
			status = 1;
		} else {
			status = 0;
		}

		try {

			String sql = "select id from ipd_dosage_given where presc_rem_id='" + id + "' and dosename='" + colname
					+ "'";

			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				int iid = rs.getInt(1);
				String sql1 = "update ipd_dosage_given set prisc_id=?, presc_rem_id=?, dosename=?, dstatus=?, ddatetime=? where id="
						+ iid + "";
				PreparedStatement ps1 = connection.prepareStatement(sql1);
				ps1.setString(1, priscid);
				ps1.setString(2, id);
				ps1.setString(3, colname);
				ps1.setString(4, String.valueOf(status));
				ps1.setString(5, datetime);

				result = ps1.executeUpdate();
			} else {

				String sql1 = "insert into ipd_dosage_given (prisc_id, presc_rem_id, dosename, dstatus, ddatetime) values (?,?,?,?,?)";
				PreparedStatement ps1 = connection.prepareStatement(sql1);
				ps1.setString(1, priscid);
				ps1.setString(2, id);
				ps1.setString(3, colname);
				ps1.setString(4, String.valueOf(status));
				ps1.setString(5, datetime);
				result = ps1.executeUpdate();

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public int updateorInsertNursingToggle(String id, String colname, String doseval, String datetime,
			String nursingid) {

		int result = 0;

		int status = 0;
		if (doseval.equals("false")) {
			status = 1;
		} else {
			status = 0;
		}

		try {

			String sql = "select id from ipd_nursing_given where nurse_rem_id='" + id + "' and dosename='" + colname
					+ "'";

			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				int iid = rs.getInt(1);
				String sql1 = "update ipd_nursing_given set nursing_id=?, nurse_rem_id=?, dosename=?, dstatus=?, ddatetime=? where id="
						+ iid + "";
				PreparedStatement ps1 = connection.prepareStatement(sql1);
				ps1.setString(1, nursingid);
				ps1.setString(2, id);
				ps1.setString(3, colname);
				ps1.setString(4, String.valueOf(status));
				ps1.setString(5, datetime);

				result = ps1.executeUpdate();
			} else {

				String sql1 = "insert into ipd_nursing_given (nursing_id, nurse_rem_id, dosename, dstatus, ddatetime) values (?,?,?,?,?)";
				PreparedStatement ps1 = connection.prepareStatement(sql1);
				ps1.setString(1, nursingid);
				ps1.setString(2, id);
				ps1.setString(3, colname);
				ps1.setString(4, String.valueOf(status));
				ps1.setString(5, datetime);
				result = ps1.executeUpdate();

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public String getPriscId(String id) {

		String priscid = "";

		try {

			String sql = "select prescid from ipd_prescription_reminder where id='" + id + "'";
			PreparedStatement ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				priscid = rs.getString(1);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return priscid;
	}

	public String getNursingId(String id) {

		String nursingid = "";

		try {

			String sql = "select nursingid from ipd_nursing_reminder where id='" + id + "'";
			PreparedStatement ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				nursingid = rs.getString(1);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return nursingid;
	}

	public ArrayList<Bed> getTreetmentSheetInDays(String clientid, String day1, String day2, String day3) {

		ArrayList<Bed> list = new ArrayList<Bed>();
		ArrayList<Bed> beds = new ArrayList<Bed>();
		String arrday1[] = null, arrday2[] = null, arrday3[] = null;
		int i = -1, j = -1, k = -1;

		try {

			String sql = "SELECT id, practitionerid, conditionid, categoryid, mdicineid, mdicinename, dose, frequency, days, lastmodified, code, type, total, parentid, priscdurationtype FROM apm_client_priscription where clientid="
					+ clientid + " and lastmodified between '" + day1 + "' and '" + day3 + "';";

			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			arrday1 = new String[10];
			arrday2 = new String[10];
			arrday3 = new String[10];

			while (rs.next()) {
				Bed bed = new Bed();
				String lastmodified = rs.getString(10);
				String date = lastmodified.substring(0, 10);

				bed.setId(rs.getInt(1));
				bed.setPractitionerid(rs.getString(2));
				bed.setConditionid(rs.getString(3));
				bed.setMedicinename(rs.getString(6));
				bed.setDose(rs.getString(7));
				bed.setLastmodified(lastmodified);

				if (date.equals(day1)) {

					String datetime = "";

					String query = "select id,presc_rem_id, dosename, dstatus, ddatetime from ipd_dosage_given where prisc_id='"
							+ bed.getId() + "'";

					PreparedStatement ps1 = connection.prepareStatement(query);
					ResultSet rs1 = ps1.executeQuery();

					String str[] = bed.getDose().split("-");
					int count = str.length;
					int tmp = -1;
					boolean flag = false;
					String temp[] = new String[count];
					while (rs1.next()) {
						flag = true;
						String dosename = rs1.getString(3);
						String status = rs1.getString(4);
						String tempdate = rs1.getString(5);
						String tdate = tempdate.substring(11, 16);
						String data = dosename + "-" + status + "-" + tdate;
						temp[++tmp] = data;
					}

					if (!flag) {
						datetime = "N/A";
					} else {
						datetime = getSortedTimeStamp(temp, tmp, count);
					}
					arrday1[++i] = datetime;

				} else if (date.equals(day2)) {

					String datetime = "";

					bed.setId(rs.getInt(1));
					bed.setPractitionerid(rs.getString(2));
					bed.setConditionid(rs.getString(3));
					bed.setMedicinename(rs.getString(6));
					bed.setDose(rs.getString(7));
					bed.setLastmodified(lastmodified);

					String query = "select id,presc_rem_id, dosename, dstatus, ddatetime from ipd_dosage_given where prisc_id='"
							+ bed.getId() + "'";

					PreparedStatement ps1 = connection.prepareStatement(query);
					ResultSet rs1 = ps1.executeQuery();

					String str[] = bed.getDose().split("-");
					int count = str.length;
					int tmp = -1;
					boolean flag = false;
					String temp[] = new String[count];
					while (rs1.next()) {
						flag = true;
						String dosename = rs1.getString(3);
						String status = rs1.getString(4);
						String tempdate = rs1.getString(5);
						String tdate = tempdate.substring(11, 16);
						String data = dosename + "-" + status + "-" + tdate;
						temp[++tmp] = data;
					}

					if (!flag) {
						datetime = "N/A";
					} else {
						datetime = getSortedTimeStamp(temp, tmp, count);
					}
					arrday2[++j] = datetime;

				} else if (date.equals(day3)) {

					String datetime = "";

					bed.setId(rs.getInt(1));
					bed.setPractitionerid(rs.getString(2));
					bed.setConditionid(rs.getString(3));
					bed.setMedicinename(rs.getString(6));
					bed.setDose(rs.getString(7));
					bed.setLastmodified(lastmodified);

					String query = "select id,presc_rem_id, dosename, dstatus, ddatetime from ipd_dosage_given where prisc_id='"
							+ bed.getId() + "'";

					PreparedStatement ps1 = connection.prepareStatement(query);
					ResultSet rs1 = ps1.executeQuery();

					String str[] = bed.getDose().split("-");
					int count = str.length;
					int tmp = -1;
					boolean flag = false;
					String temp[] = new String[count];
					while (rs1.next()) {
						flag = true;
						String dosename = rs1.getString(3);
						String status = rs1.getString(4);
						String tempdate = rs1.getString(5);
						String tdate = tempdate.substring(11, 16);
						String data = dosename + "-" + status + "-" + tdate;
						temp[++tmp] = data;
					}

					if (!flag) {
						datetime = "N/A";
					} else {
						datetime = getSortedTimeStamp(temp, tmp, count);
					}

					arrday3[++k] = datetime;
				}

				list.add(bed);
			}

			int x = 0;
			for (Bed bed : list) {

				if (arrday1[x] == null) {

					bed.setDatetime("N/A");
				} else {
					bed.setDatetime(arrday1[x]);
				}
				if (arrday2[x] == null) {

					bed.setDate2("N/A");
				} else {
					bed.setDate2(arrday2[x]);
				}
				if (arrday3[x] == null) {

					bed.setDate3("N/A");
				} else {
					bed.setDate3(arrday3[x]);
				}

				beds.add(bed);
				x++;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return beds;
	}

	public ArrayList<Bed> getAllDosageRemainders(String date, String nextday, LoginInfo loginInfo) {

		ArrayList<Bed> list = new ArrayList<Bed>();
		String timeformat[] = { null, null, null };
		String color = "";
		String ipdid = "";

		try {

			String sql = "select id, clientid, practitionerid, conditionid, categoryid, mdicineid, mdicinename, dose, frequency, days, lastmodified, code, type, total, parentid, priscdurationtype from apm_client_priscription where lastmodified between '"
					+ date + "' and '" + nextday + "'";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				String priscid = rs.getString(1);
				String dose = rs.getString(8);
				String practid = rs.getString(3);
				String clientid = rs.getString(2);

				String query = "select ipdid from ipd_prescription_reminder where prescid='" + priscid + "'";
				PreparedStatement ps2 = connection.prepareStatement(query);
				ResultSet rs2 = ps2.executeQuery();
				while (rs2.next()) {

					ipdid = rs2.getString(1);
				}

				if (!dose.startsWith("0") && !dose.startsWith("1")) {

				}
				String dosessplit[] = dose.split("-");
				if (dosessplit.length == 3) {
					timeformat = new String[] { "9:00", "16:00", "21:00" };
					for (int i = 0; i < dosessplit.length; i++) {

						if (dosessplit[i].equals("1")) {

							Bed bed = new Bed();
							String qu = "select id,presc_rem_id, dosename, dstatus, ddatetime from ipd_dosage_given where prisc_id='"
									+ priscid + "' and dosename='dos" + (i + 1) + "' ";
							PreparedStatement ps1 = connection.prepareStatement(qu);
							ResultSet rs1 = ps1.executeQuery();

							if (rs1.next()) {
								color = "#fff";
								bed.setId(Integer.parseInt(ipdid));
								bed.setConditionname(String.valueOf(i + 1));
								bed.setColor(color);
								list.add(bed);
							} else {

								String ddate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
								String curtime = ddate.substring(11, 16);

								String tmpdbtime[] = timeformat[i].split(":");
								String tmpcutime[] = curtime.split(":");
								color = getColorBell(tmpdbtime, tmpcutime, loginInfo, practid, ipdid, clientid);

								if (color.equals("Red")) {
									bed.setId(Integer.parseInt(ipdid));
									bed.setConditionname(String.valueOf(i + 1));
									bed.setColor(color);
									list.add(bed);
								} else if (color.equals("Orange")) {
									bed.setId(Integer.parseInt(ipdid));
									bed.setConditionname(String.valueOf(i + 1));
									bed.setColor(color);
									list.add(bed);
								}

							}

						}

					}

				} else if (dosessplit.length == 4) {

					timeformat = new String[] { "8:00", "14:00", "16:00", "22:00" };
					for (int i = 0; i < dosessplit.length; i++) {

						if (dosessplit[i].equals("1")) {

							Bed bed = new Bed();
							String qu = "select id,presc_rem_id, dosename, dstatus, ddatetime from ipd_dosage_given where prisc_id='"
									+ priscid + "' and dosename='dos" + (i + 1) + "' ";
							PreparedStatement ps1 = connection.prepareStatement(qu);
							ResultSet rs1 = ps1.executeQuery();

							if (rs1.next()) {
								color = "#fff";
								bed.setId(Integer.parseInt(ipdid));
								bed.setColor(color);
								bed.setConditionname(String.valueOf(i + 1));
								list.add(bed);
							} else {

								String ddate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
								String curtime = ddate.substring(11, 16);

								String tmpdbtime[] = timeformat[i].split(":");
								String tmpcutime[] = curtime.split(":");
								color = getColorBell(tmpdbtime, tmpcutime, loginInfo, practid, ipdid, clientid);

								if (color.equals("Red")) {
									bed.setId(Integer.parseInt(ipdid));
									bed.setColor(color);
									bed.setConditionname(String.valueOf(i + 1));
									list.add(bed);
								} else if (color.equals("Orange")) {
									bed.setId(Integer.parseInt(ipdid));
									bed.setConditionname(String.valueOf(i + 1));
									bed.setColor(color);
									list.add(bed);
								}

							}

						}

					}

				}

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	private String getColorBell(String dbdate[], String nwdate[], LoginInfo loginInfo, String pracid, String ipdid,
			String clientid) throws Exception {

		String color = "Normal";

		int hourdb = Integer.parseInt(dbdate[0]);
		int mindb = Integer.parseInt(dbdate[1]);

		int hourcur = Integer.parseInt(nwdate[0]);
		int mincur = Integer.parseInt(nwdate[1]);

		if (hourcur + 1 == hourdb) {

			if (mincur + 10 == 60 || mincur + 9 == 60 || mincur + 8 == 60 || mincur + 7 == 60 || mincur + 6 == 60
					|| mincur + 5 == 60 || mincur + 4 == 60 || mincur + 3 == 60 || mincur + 2 == 60
					|| mincur + 1 == 60) {

				color = "Orange";
			} else {
				color = "Normal";
			}
		}

		if (hourcur == hourdb) {

			if (mincur == 15) {

				String email = "";
				try {

					UserProfileDAO profileDAO = new JDBCUserProfileDAO(connection);
					UserProfile userProfile = profileDAO.getUserprofileDetails(Integer.parseInt(pracid));
					email = userProfile.getEmail();
					BedDao bedDao = new JDBCBedDao(connection);
					Bed bed = bedDao.getEditIpdData(ipdid);

					String wardname = getIpdWardName(bed.getWardid());
					String bedname = getIpdBedName(bed.getBedid());

					ClientDAO clientDAO = new JDBCClientDAO(connection);
					Client client = clientDAO.getClientDetails(clientid);
					String clientname = client.getFirstName() + " " + client.getLastName();

					String allData = clientname + " at " + wardname + "/" + bedname + "\n";
					allMails.add(allData);

					color = "Red";
					// send mail once
					// email="prafulghagre@gmail.com";
					// String subject="Medicine dose Not given to Patient";
					// String body="Dear sir, Medicine dose sheduled at time
					// "+hourdb+":"+mindb+" yet not given to Patient
					// "+clientname+" at "+wardname+"/"+bedname+" please See
					// this Issue....";

					// Email.sendMail(connection, email, "", subject, body,
					// loginInfo, null, null);

				} catch (Exception e) {

					e.printStackTrace();
				}

			} else if (mincur == 16) {

				// send mail
				StringBuffer sb = new StringBuffer();
				sb.append("Dear sir, Medicine dose sheduled at time " + hourdb + ":" + mindb
						+ " yet not given to following patients: <br>");
				int i = 0;
				color = "Red";
				for (String patients : allMails) {

					sb.append("" + (++i) + ": " + patients + "<br>");
				}
				sb.append("please See this Issue....");
				String email = "info@pranam.co.in";
				String subject = "Medicine dose Not given to Patients";
				// commented on 19-10-2018
				/*
				 * Email.sendMail(connection, email, "", subject, sb.toString(), loginInfo,
				 * null, null); allMails.clear();
				 */

			} else if (mincur > 16 && mincur < 30) {
				color = "Red";
			}
			if (mincur < 15) {
				color = "Orange";
			}
		}

		return color;
	}

	public ArrayList<Bed> getAllPricsandDoseTodayOfPatient(String wardid, LoginInfo loginInfo) {

		ArrayList<Bed> list = new ArrayList<Bed>();
		BedDao bedDao = new JDBCBedDao(connection);
		int ipdid = 0;
		PreparedStatement preparedStatement = null;
		String sql = "";
		try {

			if (wardid == null || wardid.equals("0")) {
				sql = "SELECT id,wardid,sectionid,bedname FROM apm_ipd_bed where active=1 ";
			} else {
				sql = "SELECT id,wardid,sectionid,bedname FROM apm_ipd_bed where wardid in (" + wardid
						+ ") and active=1 ";
			}

			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				ArrayList<Priscription> ipdPriscList = new ArrayList<Priscription>();

				Bed bed = new Bed();
				bed.setId(rs.getInt(1));
				bed.setWardid(rs.getString(2));
				bed.setSectionid(rs.getString(3));
				bed.setBedname(rs.getString(4));

				boolean checkbedstatus = checkBedStatus(bed.getId());
				if (checkbedstatus) {
					bed.setStatus("1");
					Ipd ipd = getAddmisionFormDetails(bed.getId());
					bed.setAdmissiondate(ipd.getAdmissiondate());
					bed.setAddmissionid(Integer.toString(ipd.getId()));
					bed.setClientid(ipd.getClientid());
					bed.setPractitionerid(ipd.getPractitionerid());
					bed.setConditionid(ipd.getConditionid());

					ClientDAO clientDAO = new JDBCClientDAO(connection);
					Client client = clientDAO.getClientDetails(bed.getClientid());
					String clientname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();
					bed.setClientname(clientname);
					bed.setDob(client.getDob());
					bed.setAge(DateTimeUtils.getAge(client.getDob()));
					bed.setTown(client.getTown());
					bed.setWhopay(client.getWhopay());
					bed.setTpid(client.getTypeName());
					bed.setClientemail(client.getEmail());
					bed.setGender(client.getGender());

					String agegender = bed.getAge() + " yr/" + bed.getGender();
					bed.setAge(agegender);
					bed.setId(ipd.getId());
					ipdid = bed.getId();

					Bed bed2 = bedDao.getEditIpdData(String.valueOf(bed.getId()));

					String wardname = getIpdWardName(bed2.getWardid());
					String bedname = getIpdBedName(bed2.getBedid());
					bed.setWardname(wardname + " " + bedname);

					String today = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
					String nextday = DateTimeUtils.getNextDaysInSimpleDBFormat(1);

					today = today.substring(0, 10);
					String query = "SELECT id,clientid,mdicinename,dose,days,parentid FROM apm_client_priscription where clientid="
							+ bed.getClientid() + " and lastmodified between '" + today + "' and '" + nextday + "' ";

					PreparedStatement ps = connection.prepareStatement(query);
					ResultSet rs1 = ps.executeQuery();

					boolean flag = false;

					while (rs1.next()) {

						flag = true;
						Priscription priscription = new Priscription();
						priscription.setId(rs1.getInt(1));
						priscription.setClientId(rs1.getString(2));
						priscription.setClientname(bed.getClientname());
						priscription.setAgeandgender(bed.getAge() + " yr/" + bed.getGender());
						priscription.setMdicinenametxt(rs1.getString(3));
						priscription.setDosage(rs1.getString(4));
						priscription.setDays(rs1.getInt(5));

						String dosage = priscription.getDosage();
						String parentid = rs1.getString(6);

						Priscription priscription2 = getParentPrisc(parentid);

						priscription.setIpdid(String.valueOf(ipdid));
						priscription.setDosenotes(priscription2.getDosenotes());
						priscription.setAdvoice(priscription2.getAdvoice());

						String dosecolumn = "";

						/*
						 * if(!dosage.startsWith("0") && !dosage.startsWith("1")){
						 * 
						 * priscription.setDosesize(3); priscription.setDos1(false);
						 * priscription.setDos2(false); priscription.setDos3(false);
						 * priscription.setDos4(false); priscription.setDos5(false);
						 * priscription.setDos6(false); priscription.setDos7(false);
						 * priscription.setDos8(false); priscription.setDos9(false);
						 * priscription.setDos10(false); priscription.setDosevalue1("1");
						 * priscription.setDosevalue2("1"); priscription.setDosevalue3("1");
						 * ipdPriscList.add(priscription); bed.setIpdPriscList(ipdPriscList);
						 * list.add(bed); continue;
						 * 
						 * }
						 */

						String dosetemp[] = dosage.split("-");
						if (dosetemp.length < 3) {

							dosage = getAlterNateDose(dosage);
							dosetemp = dosage.split("-");
						}

						for (int i = 1; i <= dosetemp.length; i++) {

							dosecolumn = dosecolumn + "dos" + i + ",";
						}

						String query1 = "SELECT id,dos1,dos2,dos3,dos4,dos5,dos6,dos7,dos8,dos9,dos10 FROM ipd_prescription_reminder where prescid="
								+ priscription.getId() + " ";

						PreparedStatement ps2 = connection.prepareStatement(query1);
						ResultSet rs2 = ps2.executeQuery();

						while (rs2.next()) {

							priscription.setId(rs2.getInt(1));

							priscription.setIpdid(bed.getAddmissionid());
							String temp[] = dosecolumn.split(",");
							priscription.setDosesize(temp.length);

							priscription.setDos1(rs2.getBoolean(2));
							priscription.setDos2(rs2.getBoolean(3));
							priscription.setDos3(rs2.getBoolean(4));
							priscription.setDos4(rs2.getBoolean(5));
							priscription.setDos5(rs2.getBoolean(6));
							priscription.setDos6(rs2.getBoolean(7));
							priscription.setDos7(rs2.getBoolean(8));
							priscription.setDos8(rs2.getBoolean(9));
							priscription.setDos9(rs2.getBoolean(10));
							priscription.setDos10(rs2.getBoolean(11));

							String values[] = dosage.split("-");

							if (values.length <= 3) {

								priscription.setDosevalue1(values[0]);
								priscription.setDosevalue2(values[1]);
								priscription.setDosevalue3(values[2]);

							} else if (values.length == 4) {
								priscription.setDosevalue1(values[0]);
								priscription.setDosevalue2(values[1]);
								priscription.setDosevalue3(values[2]);
								priscription.setDosevalue4(values[3]);
							}

						}
						ipdPriscList.add(priscription);
					}

					if (!flag) {

						Priscription priscription = new Priscription();
						priscription.setClientname(bed.getClientname());
						priscription.setAgeandgender(bed.getAge() + " yr/" + bed.getGender());
						priscription.setMdicinenametxt("N/A");
						priscription.setDosage("N/A");
						priscription.setIpdid(bed.getAddmissionid());
						priscription.setDosesize(0);
						ipdPriscList.add(priscription);
					}

					bed.setIpdPriscList(ipdPriscList);
					list.add(bed);
				}

			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	private Priscription getParentPrisc(String id) {

		Priscription priscription = new Priscription();

		try {

			String sql = "select dosenotes,advoice,ipdid from apm_client_parent_priscription where id='" + id + "'";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				priscription.setDosenotes(rs.getString(1));
				priscription.setAdvoice(rs.getString(2));
				priscription.setIpdid(rs.getString(3));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return priscription;
	}

	private Master getParentNursing(String id) {

		Master priscription = new Master();

		try {

			String sql = "select notes,ipdid from apm_client_parent_nursing where id='" + id + "'";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				priscription.setNotes(rs.getString(1));
				priscription.setIpdid(rs.getString(2));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return priscription;
	}

	public ArrayList<Priscription> getDischargePrescList(String selectedid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Priscription> list = new ArrayList<Priscription>();
		EmrDAO emrDAO = new JDBCEmrDAO(connection);
		StringBuffer sql = new StringBuffer();
		sql.append(
				"SELECT apm_client_priscription.id,mdicinename,dose,days,notes,priscdurationtype,advoice,dispriscsrno,unit,priscremark,unitextension,dr_qty FROM apm_client_parent_priscription inner join apm_client_priscription ");
		sql.append("on apm_client_priscription.parentid = apm_client_parent_priscription.id ");
		sql.append("where ipdid = " + selectedid
				+ " and discharge=0 and fromtreatmentgiven!='1' order by (dispriscsrno+0) asc  ");

		try {
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			String total = "0";
			int i = 0;
			boolean strengthflag = false;
			boolean quantityflag = false;
			boolean remarkflag = false;
			while (rs.next()) {
				Priscription priscription = new Priscription();
				priscription.setId(rs.getInt(1));
				priscription.setMdicinenametxt(rs.getString(2));
				priscription.setPriscdose(rs.getString(3));
				priscription.setPriscdays(rs.getString(4));
				priscription.setDosenotes(rs.getString(5));
				priscription.setPriscdurationtype(rs.getString(6));
				priscription.setMedicine_shedule("0");
				priscription.setRegional(emrDAO.getregionalText(priscription.getPriscdose()));
				total = total + "," + rs.getInt(1);
				priscription.setTotalchildmedids(total);
				priscription.setDispriscsrno("" + (++i));

				priscription.setStrength(rs.getString(9));
				priscription.setStrengthnew(priscription.getStrength());
				if (priscription.getStrengthnew().equals("0")) {
					priscription.setStrengthnew("");
				}
				priscription.setPriscindivisualremark(rs.getString(10));
				priscription.setUnitextension(rs.getString(11));
				priscription.setDr_qty(rs.getString(12));
				// lokesh 04 July 2018 error while show in ipd discharge form
				if (priscription.getUnitextension() != null) {
					if (priscription.getUnitextension().equals("0")) {
						priscription.setUnitextension("");
					}
				} else {
					priscription.setUnitextension("");
				}

				if (priscription.getStrengthnew() == null) {
					priscription.setStrengthnew("");
				}

				if (!priscription.getUnitextension().equals("")) {
					quantityflag = true;
				}
				if (!priscription.getStrengthnew().equals("")) {
					strengthflag = true;
				}
				if (priscription.getPriscindivisualremark() == null) {
					priscription.setPriscindivisualremark("");
				}
				if (!priscription.getPriscindivisualremark().equals("")) {
					remarkflag = true;
				}

				priscription.setQuantityflag(quantityflag);
				priscription.setStrengthflag(strengthflag);
				priscription.setRemarkflag(remarkflag);
				list.add(priscription);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public Bed getDischargeData(String treatmentepisodeid) {
		PreparedStatement preparedStatement = null;
		Bed bed = new Bed();
		String sql = "SELECT treatmentstatus, outcomes, dschargestatus,dischargedate,hospcourse, disadvnotes,dis_extra_note,findingondischarge,treatmentgiven,investigation,otNotes,operative_procedure, anesthesia, surgeon, anesthesiologist,dischargebyid,death_note,emergencydetail,dis_extra_note,ot_manual_surg,ot_manual_anesthesio,endeduserid FROM apm_treatment_episode where id = "
				+ treatmentepisodeid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				bed.setChkDischarge(rs.getBoolean(1));
				bed.setDischrgeOutcomes(DateTimeUtils.isNull(rs.getString(2)));
				bed.setDischargeStatus(DateTimeUtils.isNull(rs.getString(3)));
				bed.setDischargedate(DateTimeUtils.isNull(rs.getString(4)));
				bed.setHospitalcourse(DateTimeUtils.isNull(rs.getString(5)));
				bed.setDiscadvnotes(DateTimeUtils.isNull(rs.getString(6)));
				bed.setExample(DateTimeUtils.isNull(rs.getString(7)));
				bed.setFindondischarge(DateTimeUtils.isNull(rs.getString(8)));
				bed.setTreatmentgiven(DateTimeUtils.isNull(rs.getString(9)));
				bed.setInvestigation(DateTimeUtils.isNull(rs.getString(10)));

				bed.setOtNotes(DateTimeUtils.isNull(rs.getString(11)));
				bed.setAppointmentText(DateTimeUtils.isNull(rs.getString(12)));
				bed.setAnesthesia(DateTimeUtils.isNull(rs.getString(13)));
				bed.setSurgeon(DateTimeUtils.isNull(rs.getString(14)));
				bed.setAnesthesiologist(DateTimeUtils.isNull(rs.getString(15)));
				bed.setDischargebyid(DateTimeUtils.isNull(rs.getString(16)));
				bed.setDeathnote(DateTimeUtils.isNull(rs.getString(17)));
				bed.setEmergencydetail(DateTimeUtils.isNull(rs.getString(18)));
				bed.setExample(rs.getString(19));
				bed.setOt_manual_surg(DateTimeUtils.isNull(rs.getString(20)));
				bed.setOt_manual_anesthesio(DateTimeUtils.isNull(rs.getString(21)));
				bed.setEnddischargename(rs.getString(22));

				if (bed.getExample() == null) {
					bed.setExample("");
				}
				if (bed.getFindondischarge() == null) {
					bed.setFindondischarge("");
				}
				if (bed.getInvestigation() == null) {
					bed.setInvestigation("");
				}
				if (bed.getOtNotes() == null) {
					bed.setOtNotes("");
				}
				if (bed.getHospitalcourse() == null) {
					bed.setHospitalcourse("");
				}
				if (bed.getTreatmentgiven() == null) {
					bed.setTreatmentgiven("");
				}
				if (bed.getEmergencydetail() == null) {
					bed.setEmergencydetail("");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bed;
	}

	public String getDischargedIpdid(String clientid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT id FROM ipd_addmission_form where clientid = " + clientid
				+ " and bedid!=0  order by id desc limit 0,1 ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getString(1);
			}

			if (result != null) {

				if (result.equals("")) {
					result = getIfDischargedIpdid(clientid);

				}
			} else {
				result = getIfDischargedIpdid(clientid);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private String getIfDischargedIpdid(String clientid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT id FROM ipd_addmission_form where clientid = " + clientid
				+ " and bedid=0  order by id desc limit 0,1 ";

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

	public int getValueToUpdate(String column, String ipdtreatmentepisodeid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select " + column + " from apm_treatment_episode where id = " + ipdtreatmentepisodeid + " ";

		try {

			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
				if (result == 0) {
					result = 1;
				} else {
					result = 0;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return result;
	}

	public int updateInitialDischargeStatus(String column, String column2, int valuetoupdate, String datetime,
			String ipdtreatmentepisodeid, String userid) {
		PreparedStatement preparedStatement = null;
		int result = 0;

		String sql = "update apm_treatment_episode set " + column + "=?," + column2
				+ "=?,finalmodify=?,finalmodifydate=? where id=" + ipdtreatmentepisodeid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, valuetoupdate);
			preparedStatement.setString(2, datetime);
			preparedStatement.setString(3, userid);
			preparedStatement.setString(4, datetime);
			result = preparedStatement.executeUpdate();

			BedDao bedDao = new JDBCBedDao(connection);
			bedDao.updateDischargeForm(ipdtreatmentepisodeid);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public String getIpdDischargeDate(String treatmentepisode) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT dischargedate FROM apm_treatment_episode where id = " + treatmentepisode + " ";

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

	public int updateDischaregeStatus(String treatmentepisode, String dischargedate) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_treatment_episode set treatmentstatus=1,discharge_end_date='" + dischargedate
				+ "' where id=" + treatmentepisode + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();

			BedDao bedDao = new JDBCBedDao(connection);
			bedDao.updateDischargeForm(treatmentepisode);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public ArrayList<Bed> getAllIpdBedList(String wardid, String searchtext, String fromdate, String todate,
			String status, Pagination pagination, LoginInfo loginInfo, String maintypestatus, String patient_type) {
		PreparedStatement preparedStatement = null;
		ArrayList<Bed> list = new ArrayList<Bed>();
		StringBuffer sql = new StringBuffer();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		String currDate = dateFormat.format(cal.getTime());
		/*
		 * if (searchtext != null && fromdate != null && todate != null) {
		 * 
		 * sql.append(
		 * "SELECT apm_ipd_bed.id,apm_ipd_bed.wardid,sectionid,bedname,discharge_end_date FROM apm_ipd_bed inner join ipd_addmission_form on "
		 * ); sql.
		 * append("ipd_addmission_form.bedid = apm_ipd_bed.id inner join apm_treatment_episode on "
		 * ); sql.
		 * append("apm_treatment_episode.id = ipd_addmission_form.treatmentepisodeid ");
		 * // lokesh sql.
		 * append(" inner join apm_patient on apm_patient.id = ipd_addmission_form.clientid "
		 * ); sql.append("where (apm_treatment_episode.clientname like ('%" + searchtext
		 * + "%') or apm_patient.abrivationid like ('%" + searchtext + "%') )"); if
		 * (status != null) { if (!status.equals("")) { if (status.equals("1")) {
		 * sql.append("and dis_initiate_status = 1 ");
		 * sql.append("and apm_treatment_episode.dis_initiate_time between '" + fromdate
		 * + "' and '" + todate + "' "); } else if (status.equals("2")) {
		 * sql.append("and dis_initiate_status = 1 ");
		 * sql.append("and apm_treatment_episode.dis_form_time between '" + fromdate +
		 * "' and '" + todate + "' "); } else if (status.equals("3")) {
		 * sql.append("and dis_initiate_status = 1 ");
		 * sql.append("and apm_treatment_episode.dis_mdicine_time between '" + fromdate
		 * + "' and '" + todate + "' "); } else if (status.equals("4")) {
		 * sql.append("and dis_initiate_status = 1 ");
		 * sql.append("and apm_treatment_episode.dis_bill_time between '" + fromdate +
		 * "' and '" + todate + "' "); } else if (status.equals("5")) {
		 * sql.append("and dis_initiate_status = 1 ");
		 * sql.append("and apm_treatment_episode.dis_nursing_time between '" + fromdate
		 * + "' and '" + todate + "' "); } else if (status.equals("6")) {
		 * sql.append("and apm_treatment_episode.dischargedate between '" + fromdate +
		 * "' and '" + todate + "' "); } } else {
		 * sql.append("and dis_initiate_status = 1 ");
		 * sql.append("and apm_treatment_episode.dis_initiate_time between '" + fromdate
		 * + "' and '" + todate + "' "); } } else {
		 * sql.append("and dis_initiate_status = 1 "); sql.append(
		 * "and apm_treatment_episode.dis_initiate_time between '" + fromdate +
		 * "' and '" + todate + "' "); } if(!patient_type.equals("0")){
		 * if(patient_type.equals("1")){ sql.append("and apm_patient.whopay='Client' ");
		 * }else if(patient_type.equals("2")){
		 * sql.append("and apm_patient.whopay='Third Party' "); } }
		 * 
		 * } else if (fromdate != null && todate != null) {
		 * 
		 * sql.append(
		 * "SELECT apm_ipd_bed.id,apm_ipd_bed.wardid,sectionid,bedname,discharge_end_date FROM apm_ipd_bed inner join ipd_addmission_form on "
		 * ); sql.
		 * append("ipd_addmission_form.bedid = apm_ipd_bed.id inner join apm_treatment_episode on "
		 * ); sql.
		 * append("apm_treatment_episode.id = ipd_addmission_form.treatmentepisodeid ");
		 * sql.
		 * append(" inner join apm_patient on apm_patient.id = ipd_addmission_form.clientid "
		 * );
		 * 
		 * if (status != null) { if (!status.equals("")) { if (status.equals("1")) {
		 * sql.append("where dis_initiate_status = 1 ");
		 * sql.append("and apm_treatment_episode.dis_initiate_time between '" + fromdate
		 * + "' and '" + todate + "' "); } else if (status.equals("2")) {
		 * sql.append("where dis_initiate_status = 1 ");
		 * sql.append("and apm_treatment_episode.dis_form_time between '" + fromdate +
		 * "' and '" + todate + "' "); } else if (status.equals("3")) {
		 * sql.append("where dis_initiate_status = 1 ");
		 * sql.append("and apm_treatment_episode.dis_mdicine_time between '" + fromdate
		 * + "' and '" + todate + "' "); } else if (status.equals("4")) {
		 * sql.append("where dis_initiate_status = 1 ");
		 * sql.append("and apm_treatment_episode.dis_bill_time between '" + fromdate +
		 * "' and '" + todate + "' "); } else if (status.equals("5")) {
		 * sql.append("where dis_initiate_status = 1 ");
		 * sql.append("and apm_treatment_episode.dis_nursing_time between '" + fromdate
		 * + "' and '" + todate + "' "); } else if (status.equals("6")) {
		 * sql.append("where apm_treatment_episode.dischargedate between '" + fromdate +
		 * "' and '" + todate + "' "); } } else {
		 * sql.append("where dis_initiate_status = 1 ");
		 * sql.append("and apm_treatment_episode.dis_initiate_time between '" + fromdate
		 * + "' and '" + todate + "' "); } } else {
		 * sql.append("where dis_initiate_status = 1 "); sql.append(
		 * "and apm_treatment_episode.dis_initiate_time between '" + fromdate +
		 * "' and '" + todate + "' "); } if(!patient_type.equals("0")){
		 * if(patient_type.equals("1")){ sql.append("and apm_patient.whopay='Client' ");
		 * }else if(patient_type.equals("2")){
		 * sql.append("and apm_patient.whopay='Third Party' "); } } }
		 * 
		 * else if (searchtext != null) { sql.append(
		 * "SELECT apm_ipd_bed.id,apm_ipd_bed.wardid,sectionid,bedname,discharge_end_date FROM apm_ipd_bed inner join ipd_addmission_form on "
		 * ); sql.
		 * append("ipd_addmission_form.bedid = apm_ipd_bed.id inner join apm_treatment_episode on "
		 * ); sql.
		 * append("apm_treatment_episode.id = ipd_addmission_form.treatmentepisodeid ");
		 * // lokesh sql.
		 * append(" inner join apm_patient on apm_patient.id = ipd_addmission_form.clientid "
		 * );
		 * 
		 * sql.append("where (apm_treatment_episode.clientname like ('%" + searchtext +
		 * "%') or apm_patient.abrivationid like ('%" + searchtext + "%') )");
		 * 
		 * if (status != null) { if (!status.equals("")) { if (status.equals("1")) {
		 * sql.append("and dis_initiate_status = 1 "); } else if (status.equals("2")) {
		 * sql.append("and dis_initiate_status = 1 "); } else if (status.equals("3")) {
		 * sql.append("and dis_initiate_status = 1 "); } else if (status.equals("4")) {
		 * sql.append("and dis_initiate_status = 1 "); } else if (status.equals("5")) {
		 * sql.append("and dis_initiate_status = 1 "); } else if (status.equals("6")) {
		 * 
		 * } } else { sql.append("and dis_initiate_status = 1 "); } } else {
		 * sql.append("and dis_initiate_status = 1 "); } if(!patient_type.equals("0")){
		 * if(patient_type.equals("1")){ sql.append("and apm_patient.whopay='Client' ");
		 * }else if(patient_type.equals("2")){
		 * sql.append("and apm_patient.whopay='Third Party' "); } } } else { sql.append(
		 * "SELECT apm_ipd_bed.id,apm_ipd_bed.wardid,sectionid,bedname,discharge_end_date FROM apm_ipd_bed inner join ipd_addmission_form on "
		 * ); sql.
		 * append("ipd_addmission_form.bedid = apm_ipd_bed.id inner join apm_treatment_episode on "
		 * ); sql.
		 * append("apm_treatment_episode.id = ipd_addmission_form.treatmentepisodeid ");
		 * sql.
		 * append(" inner join apm_patient on apm_patient.id = ipd_addmission_form.clientid "
		 * );
		 * 
		 * if (status != null) { if (!status.equals("")) { if (status.equals("1")) {
		 * sql.append("where dis_initiate_status = 1 "); } else if (status.equals("2"))
		 * { sql.append("where dis_initiate_status = 1 "); } else if
		 * (status.equals("3")) { sql.append("where dis_initiate_status = 1 "); } else
		 * if (status.equals("4")) { sql.append("where dis_initiate_status = 1 "); }
		 * else if (status.equals("5")) { sql.append("where dis_initiate_status = 1 ");
		 * } else if (status.equals("6")) {
		 * 
		 * } } else { sql.append("where dis_initiate_status = 1 "); } } else {
		 * sql.append("where dis_initiate_status = 1 "); }
		 * if(!patient_type.equals("0")){ if(patient_type.equals("1")){
		 * sql.append("and apm_patient.whopay='Client' "); }else
		 * if(patient_type.equals("2")){
		 * sql.append("and apm_patient.whopay='Third Party' "); } } } if (status !=
		 * null) { if (!status.equals("")) { if (status.equals("1")) { if
		 * (!maintypestatus.equals("1")) { sql.append(
		 * "and apm_treatment_episode.dis_initiate_status=1 and apm_treatment_episode.dis_form_status=0 and apm_treatment_episode.dis_mdicine_status=0 and apm_treatment_episode.dis_bill_status=0 and apm_treatment_episode.dis_nursing_status=0 "
		 * ); } } else if (status.equals("2")) { sql.append(
		 * "and apm_treatment_episode.dis_initiate_status=1 and apm_treatment_episode.dis_form_status=1 and apm_treatment_episode.dis_mdicine_status=0 and apm_treatment_episode.dis_bill_status=0 and apm_treatment_episode.dis_nursing_status=0 "
		 * ); } else if (status.equals("3")) { sql.append(
		 * "and apm_treatment_episode.dis_initiate_status=1 and apm_treatment_episode.dis_form_status=1 and apm_treatment_episode.dis_mdicine_status=1 and apm_treatment_episode.dis_bill_status=0 and apm_treatment_episode.dis_nursing_status=0 "
		 * ); } else if (status.equals("4")) { sql.append(
		 * "and apm_treatment_episode.dis_initiate_status=1 and apm_treatment_episode.dis_form_status=1 and apm_treatment_episode.dis_mdicine_status=1 and apm_treatment_episode.dis_bill_status=1 and apm_treatment_episode.dis_nursing_status=0 "
		 * ); } else if (status.equals("5")) { sql.append(
		 * "and apm_treatment_episode.dis_initiate_status=1 and apm_treatment_episode.dis_form_status=1 and apm_treatment_episode.dis_mdicine_status=1 and apm_treatment_episode.dis_bill_status=1 and apm_treatment_episode.dis_nursing_status=1 "
		 * ); } else if (status.equals("6")) {
		 * sql.append("and apm_treatment_episode.dschargestatus!=0 "); } } }
		 */
		sql.append("SELECT apm_ipd_bed.id,ipd_addmission_form.wardid,sectionid,bedname,discharge_end_date,wardname, ");
		sql.append(
				"ipd_addmission_form.id,ipd_addmission_form.clientid,ipd_addmission_form.practitionerid,conditionid,apm_treatment_episode.id,admissiondsate, ");
		sql.append(
				"ipdseqno,ipdabrivationid,concat(initial,' ',apm_user.firstname,' ',lastname),apm_user.mobile,company_name, ");
		sql.append(
				"concat(apm_patient.title,' ',apm_patient.firstname,' ', surname),abrivationid,apm_patient.dob,apm_patient.town, ");
		sql.append("apm_patient.third_party_name_id,whopay,apm_patient.email,gender, ");
		sql.append("dis_initiate_time, dis_initiate_status, dis_form_time, dis_form_status, ");
		sql.append("dis_mdicine_time, dis_mdicine_status, dis_bill_time, dis_bill_status, dis_nursing_time, ");
		sql.append("dis_nursing_status,treatmentstatus,dischargedate ");
		sql.append("FROM apm_treatment_episode ");
		sql.append(
				"inner join ipd_addmission_form on apm_treatment_episode.id = ipd_addmission_form.treatmentepisodeid ");
		if (!maintypestatus.equals("1")) {
			sql.append("left join apm_ipd_bed on ipd_addmission_form.bedid = apm_ipd_bed.id ");
		} else {
			sql.append("inner join apm_ipd_bed on ipd_addmission_form.bedid = apm_ipd_bed.id ");
		}
		sql.append("inner join apm_patient on apm_patient.id = ipd_addmission_form.clientid ");
		sql.append("inner join apm_ipd_ward on ipd_addmission_form.wardid = apm_ipd_ward.id ");
		sql.append("inner join apm_user on apm_user.id = ipd_addmission_form.practitionerid ");
		sql.append(
				"left join apm_third_party_details on apm_third_party_details.id = apm_patient.third_party_name_id ");
		sql.append("where dis_initiate_status=1 ");
		if (!patient_type.equals("0")) {
			if (patient_type.equals("1")) {
				sql.append("and apm_patient.whopay='Client' ");
			} else if (patient_type.equals("2")) {
				sql.append("and apm_patient.whopay='Third Party' ");
			}
		}
		if (searchtext != null) {
			sql.append("and (apm_treatment_episode.clientname like ('%" + searchtext
					+ "%') or apm_patient.abrivationid like ('%" + searchtext + "%')) ");
		}

		if (fromdate != null && todate != null) {
			todate = todate + " 23:59:59";
			if (!DateTimeUtils.isNull(status).equals("")) {
				if (status.equals("1")) {
					sql.append("and apm_treatment_episode.dis_initiate_time between '" + fromdate + "' and '" + todate
							+ "' ");
				} else if (status.equals("2")) {
					sql.append(
							"and apm_treatment_episode.dis_form_time between '" + fromdate + "' and '" + todate + "' ");
				} else if (status.equals("3")) {
					sql.append("and apm_treatment_episode.dis_mdicine_time between '" + fromdate + "' and '" + todate
							+ "' ");
				} else if (status.equals("4")) {
					sql.append(
							"and apm_treatment_episode.dis_bill_time between '" + fromdate + "' and '" + todate + "' ");
				} else if (status.equals("5")) {
					sql.append("and apm_treatment_episode.dis_nursing_time between '" + fromdate + "' and '" + todate
							+ "' ");
				} else if (status.equals("6")) {
					sql.append(
							"and apm_treatment_episode.dischargedate between '" + fromdate + "' and '" + todate + "' ");
				}
			} else {
				sql.append("and (apm_treatment_episode.dis_initiate_time between '" + fromdate + "' and '" + todate
						+ "' ");
				sql.append("or apm_treatment_episode.dis_form_time between '" + fromdate + "' and '" + todate + "' ");
				sql.append(
						"or apm_treatment_episode.dis_mdicine_time between '" + fromdate + "' and '" + todate + "' ");
				sql.append("or apm_treatment_episode.dis_bill_time between '" + fromdate + "' and '" + todate + "' ");
				sql.append(
						"or apm_treatment_episode.dis_nursing_time between '" + fromdate + "' and '" + todate + "' ");
				sql.append("or apm_treatment_episode.dischargedate between '" + fromdate + "' and '" + todate + "') ");
			}
		}

		/*
		 * if (!DateTimeUtils.isNull(status).equals("")){ if (status.equals("1")){ if
		 * (!maintypestatus.equals("1")) { sql.
		 * append("and apm_treatment_episode.dis_initiate_status=1 and apm_treatment_episode.dis_form_status=0 and apm_treatment_episode.dis_mdicine_status=0 and apm_treatment_episode.dis_bill_status=0 and apm_treatment_episode.dis_nursing_status=0 "
		 * ); } } else if (status.equals("2")) { sql.
		 * append("and apm_treatment_episode.dis_initiate_status=1 and apm_treatment_episode.dis_form_status=1 and apm_treatment_episode.dis_mdicine_status=0 and apm_treatment_episode.dis_bill_status=0 and apm_treatment_episode.dis_nursing_status=0 "
		 * ); } else if (status.equals("3")) { sql.
		 * append("and apm_treatment_episode.dis_initiate_status=1 and apm_treatment_episode.dis_form_status=1 and apm_treatment_episode.dis_mdicine_status=1 and apm_treatment_episode.dis_bill_status=0 and apm_treatment_episode.dis_nursing_status=0 "
		 * ); } else if (status.equals("4")) { sql.
		 * append("and apm_treatment_episode.dis_initiate_status=1 and apm_treatment_episode.dis_form_status=1 and apm_treatment_episode.dis_mdicine_status=1 and apm_treatment_episode.dis_bill_status=1 and apm_treatment_episode.dis_nursing_status=0 "
		 * ); } else if (status.equals("5")) { sql.
		 * append("and apm_treatment_episode.dis_initiate_status=1 and apm_treatment_episode.dis_form_status=1 and apm_treatment_episode.dis_mdicine_status=1 and apm_treatment_episode.dis_bill_status=1 and apm_treatment_episode.dis_nursing_status=1 "
		 * ); } else if (status.equals("6")) {
		 * sql.append("and apm_treatment_episode.dschargestatus!=0 "); } }
		 */
		if (!DateTimeUtils.isNull(status).equals("")) {
			if (status.equals("1")) {
				if (!maintypestatus.equals("1")) {
					sql.append("and apm_treatment_episode.dis_initiate_status=1 ");
				}
			} else if (status.equals("2")) {
				sql.append(
						"and apm_treatment_episode.dis_initiate_status=1 and apm_treatment_episode.dis_form_status=1 ");
			} else if (status.equals("3")) {
				sql.append(
						"and apm_treatment_episode.dis_initiate_status=1 and apm_treatment_episode.dis_mdicine_status=1 ");
			} else if (status.equals("4")) {
				sql.append(
						"and apm_treatment_episode.dis_initiate_status=1 and apm_treatment_episode.dis_bill_status=1 ");
			} else if (status.equals("5")) {
				sql.append(
						"and apm_treatment_episode.dis_initiate_status=1 and apm_treatment_episode.dis_nursing_status=1 ");
			} else if (status.equals("6")) {
				sql.append("and apm_treatment_episode.dschargestatus!=0 ");
			}
		}

		if (!DateTimeUtils.isNull(status).equals("")) {
			if (status.equals("1")) {
				sql.append(" order by apm_treatment_episode.dis_initiate_time desc ");
			} else if (status.equals("2")) {
				sql.append(" order by apm_treatment_episode.dis_form_time desc ");
			} else if (status.equals("3")) {
				sql.append(" order by apm_treatment_episode.dis_mdicine_time desc ");
			} else if (status.equals("4")) {
				sql.append(" order by apm_treatment_episode.dis_bill_time desc ");
			} else if (status.equals("5")) {
				sql.append(" order by apm_treatment_episode.dis_nursing_time desc ");
			} else if (status.equals("6")) {
				sql.append(" order by apm_treatment_episode.dischargedate desc ");
			} else {
				sql.append(" order by apm_treatment_episode.id desc ");
			}
		} else {
			sql.append(" order by apm_treatment_episode.id desc ");
		}

		// 02 feb 2018 new query created combine of all above
		try {

			String query = sql.toString();
			if (pagination != null) {
				query = pagination.getSQLQuery(query);
			}

			preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			DiaryManagementDAO diaryManagementDAO = new JDBCDiaryManagentDAO(connection);

			while (rs.next()) {
				String newdischarghedate = "";
				Bed bed = new Bed();
				Bed bed1 = new Bed();
				bed.setId(rs.getInt(1));
				bed.setWardid(rs.getString(2));
				bed.setSectionid(rs.getString(3));
				bed.setBedname(rs.getString(4));
				if (rs.getString(5) == null) {
					bed.setDischarge_end_date("");
				} else {
					// bed.setDischarge_end_date(rs.getString(5));
					String t1[] = rs.getString(5).split(" ");
					bed.setDischarge_end_date(DateTimeUtils.getCommencingDate1(t1[0]) + " " + t1[1]);
				}
				String wardname = rs.getString(6);
				bed.setWardname(wardname);

				boolean checkbedstatus = checkBedStatus(bed.getId());
				if (checkbedstatus) {
					bed.setStatus("1");
				} else {
					bed.setStatus("0");
				}
				bed.setAdmissiondate(rs.getString(12));
				bed.setAddmissionid("" + rs.getInt(7));
				bed1 = getInvoicenumberbyId(rs.getInt(7));
				bed.setInvoiceno(bed1.getInvoiceno());
				bed.setClientid(rs.getString(8));
				bed.setPractitionerid(rs.getString(9));
				bed.setConditionid(rs.getString(10));
				bed.setTreatmentepisodeid(rs.getString(11));
				if (loginInfo.getIpd_abbr_access() == 1) {
					String ipdabrivation = rs.getString(14);
					bed.setNewipdabbrseq(ipdabrivation);
					bed.setIpdseqno(ipdabrivation);
				} else {
					bed.setIpdseqno(rs.getString(13));
					bed.setNewipdabbrseq(rs.getString(13));
				}
				bed.setDis_initiate_status(rs.getString(27));

				if (!DateTimeUtils.isNull(rs.getString(26)).equals("")) {
					// dis_initiate_time
					String t1[] = rs.getString(26).split(" ");
					bed.setDis_initiate_date(DateTimeUtils.getCommencingDate1(t1[0]));
					bed.setDis_initiate_time(t1[1]);
				}

				if (!DateTimeUtils.isNull(rs.getString(28)).equals("")) {
//						dis_form_time
					String t1[] = rs.getString(28).split(" ");
					bed.setDis_form_date(DateTimeUtils.getCommencingDate1(t1[0]));
					// lokesh
					if (t1[0] != null) {
						newdischarghedate = t1[0];
					}
					bed.setDis_form_time(t1[1]);
				}

				if (!DateTimeUtils.isNull(rs.getString(30)).equals("")) {
					// dis_mdicine_time
					String t1[] = rs.getString(30).split(" ");
					bed.setDis_mdicine_date(DateTimeUtils.getCommencingDate1(t1[0]));
					bed.setDis_mdicine_time(t1[1]);
				}

				if (!DateTimeUtils.isNull(rs.getString(32)).equals("")) {
					// dis_bill_time
					String t1[] = rs.getString(32).split(" ");
					bed.setDis_bill_date(DateTimeUtils.getCommencingDate1(t1[0]));
					bed.setDis_bill_time(t1[1]);
				}

				if (!DateTimeUtils.isNull(rs.getString(34)).equals("")) {
					// dis_nursing_time
					String t1[] = rs.getString(34).split(" ");
					bed.setDis_nursing_date(DateTimeUtils.getCommencingDate1(t1[0]));
					bed.setDis_nursing_time(t1[1]);
				}

				bed.setDis_form_status(rs.getString(29));

				bed.setDis_mdicine_status(rs.getString(31));

				bed.setDis_bill_status(rs.getString(33));

				bed.setDis_nursing_status(rs.getString(35));
				bed.setTreatmentstatus(rs.getInt(36));
				if (!DateTimeUtils.isNull(rs.getString(37)).equals("")) {
					// discharge date
					String t1[] = rs.getString(37).split(" ");
					bed.setDischargeDate(DateTimeUtils.getCommencingDate1(t1[0]) + " " + t1[1]);
				} else {
					bed.setDischargeDate("");
				}

				bed.setPatientIdAbrivation(rs.getString(19));
				bed.setClientname(rs.getString(18));
				bed.setDob(rs.getString(20));
				if (newdischarghedate != null) {
					String age2 = DateTimeUtils.getAge1onAddmission(rs.getString(20), newdischarghedate);
					if (age2 == null) {
						bed.setAge(DateTimeUtils.getAge1(rs.getString(20)));
					} else if (age2.equals("")) {
						bed.setAge(DateTimeUtils.getAge1(rs.getString(20)));
					} else {
						bed.setAge(age2);
					}
				} else {
					bed.setAge(DateTimeUtils.getAge1(rs.getString(20)));
				}
				// third_party_name_id,whopay,apm_patient.email,gender,
				bed.setTown(rs.getString(21));
				bed.setWhopay(rs.getString(23));
				bed.setTpid(rs.getString(22));
				bed.setClientemail(rs.getString(24));
				bed.setGender(rs.getString(25));
				bed.setDstatus("0");

				double balance = 0;

				if (rs.getString(23).equals(Constants.PAY_BY_CLIENT)) {
					double debit = diaryManagementDAO.getClientDebitTotal(bed.getClientid());
					double payment = diaryManagementDAO.getClientPayment(bed.getClientid());
					balance = debit - payment;
				}
				bed.setBalance(balance);
				bed.setWhopay(rs.getString(23));
				bed.setTpname(rs.getString(17));
				bed.setPractitionername(rs.getString(15));
				bed.setPractitionerMob(rs.getString(16));
				list.add(bed);
			}

			/*
			 * ArrayList<Bed>dischargedPatientList =
			 * getDischargedPatientrList(searchtext,fromdate,todate,status);
			 * 
			 * 
			 * 
			 * list.addAll(dischargedPatientList);
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	private Bed getInvoicenumberbyId(int ipdid) {
		PreparedStatement pst = null;
		Bed bed = new Bed();
		try {
			String sql = "select f_seq_no from apm_charges_invoice where ipdid='" + ipdid + "'";
			pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				bed.setInvoiceno(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bed;
	}

	private ArrayList<Bed> getDischargedPatientrList(String searchtext, String fromdate, String todate, String status) {
		PreparedStatement preparedStatement = null;
		ArrayList<Bed> list = new ArrayList<Bed>();

		// DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// Calendar cal = Calendar.getInstance();
		// cal.add(Calendar.DATE, -2);
		// String fromDate = dateFormat.format(cal.getTime());

		// DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		// Calendar cal1 = Calendar.getInstance();
		// cal1.add(Calendar.DATE, -2);
		// String todate = dateFormat1.format(cal1.getTime());

		// todate = todate + " 23:59:59";

		if (fromdate == null) {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, -7);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			fromdate = dateFormat.format(calendar.getTime());
		}

		StringBuilder sql = new StringBuilder();

		if (searchtext != null) {

			if (!searchtext.equals("")) {
				sql.append(
						"SELECT apm_treatment_episode.id,apm_treatment_episode.clientid FROM apm_treatment_episode inner join apm_patient on apm_treatment_episode.clientid=apm_patient.id where apm_treatment_episode.dischargedate between '"
								+ fromdate + "' and '" + todate + "' and apm_patient.firstname like ('%" + searchtext
								+ "') or apm_patient.surname like ('" + searchtext + "%') ");
			} else {
				sql.append("SELECT id,clientid FROM apm_treatment_episode where dischargedate between '" + fromdate
						+ "' and '" + todate + "' ");
			}
		} else {
			sql.append("SELECT id,clientid FROM apm_treatment_episode where dischargedate between '" + fromdate
					+ "' and '" + todate + "' ");
		}
		if (status != null) {
			if (!status.equals("")) {

				if (status.equals("1")) {
					sql.append(
							"and apm_treatment_episode.dis_initiate_status=1 and apm_treatment_episode.dis_form_status=0 and apm_treatment_episode.dis_mdicine_status=0 and apm_treatment_episode.dis_bill_status=0 and apm_treatment_episode.dis_nursing_status=0 ");
				} else if (status.equals("2")) {
					sql.append(
							"and apm_treatment_episode.dis_initiate_status=1 and apm_treatment_episode.dis_form_status=1 and apm_treatment_episode.dis_mdicine_status=0 and apm_treatment_episode.dis_bill_status=0 and apm_treatment_episode.dis_nursing_status=0 ");
				} else if (status.equals("3")) {
					sql.append(
							"and apm_treatment_episode.dis_initiate_status=1 and apm_treatment_episode.dis_form_status=1 and apm_treatment_episode.dis_mdicine_status=1 and apm_treatment_episode.dis_bill_status=0 and apm_treatment_episode.dis_nursing_status=0 ");
				} else if (status.equals("4")) {
					sql.append(
							"and apm_treatment_episode.dis_initiate_status=1 and apm_treatment_episode.dis_form_status=1 and apm_treatment_episode.dis_mdicine_status=1 and apm_treatment_episode.dis_bill_status=1 and apm_treatment_episode.dis_nursing_status=0 ");
				} else if (status.equals("5")) {
					sql.append(
							"and apm_treatment_episode.dis_initiate_status=1 and apm_treatment_episode.dis_form_status=1 and apm_treatment_episode.dis_mdicine_status=1 and apm_treatment_episode.dis_bill_status=1 and apm_treatment_episode.dis_nursing_status=1 ");
				} else if (status.equals("6")) {
					sql.append(
							"and apm_treatment_episode.dis_initiate_status=1 and apm_treatment_episode.dis_form_status=1 and apm_treatment_episode.dis_mdicine_status=1 and apm_treatment_episode.dis_bill_status=1 and apm_treatment_episode.dis_nursing_status=1 and apm_treatment_episode.dschargestatus!=0");
				}
			}

		}

		try {
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int treatmentepisodeid = rs.getInt(1);
				int clientid = rs.getInt(2);
				int ipdid = getTreatmentEpisodeIpdid(treatmentepisodeid);
				int bedid = getDischargeLogBedid(ipdid);

				Bed beddata = getPatientBedDetails(bedid);

				Bed bed = new Bed();
				bed.setId(beddata.getId());
				bed.setWardid(beddata.getWardid());
				bed.setSectionid(beddata.getSectionid());
				bed.setBedname(beddata.getBedname());

				String wardname = getIpdWardName(bed.getWardid());
				bed.setWardname(wardname);

				BedDao bedDao = new JDBCBedDao(connection);
				Bed bed3 = bedDao.getEditIpdData(Integer.toString(ipdid));
				bed.setPractitionerid(bed3.getPractitionerid());
				bed.setConditionid(bed3.getConditionid());
				bed.setTreatmentepisodeid(bed3.getTreatmentepisodeid());
				bed.setClientid(bed3.getClientid());
				bed.setAddmissionid(Integer.toString(ipdid));
				bed.setDstatus("1");

				// discharge details
				Discharge discharge = getDischargeDetails(bed.getTreatmentepisodeid());
				bed.setDis_initiate_status(discharge.getDis_initiate_status());

				if (discharge.getDis_initiate_time() != null) {
					String t1[] = discharge.getDis_initiate_time().split(" ");
					bed.setDis_initiate_date(t1[0]);
					bed.setDis_initiate_time(t1[1]);
				}

				if (discharge.getDis_form_time() != null) {
					String t1[] = discharge.getDis_form_time().split(" ");
					bed.setDis_form_date(t1[0]);
					bed.setDis_form_time(t1[1]);
				}

				if (discharge.getDis_mdicine_time() != null) {
					String t1[] = discharge.getDis_mdicine_time().split(" ");
					bed.setDis_mdicine_date(t1[0]);
					bed.setDis_mdicine_time(t1[1]);
				}

				if (discharge.getDis_bill_time() != null) {
					String t1[] = discharge.getDis_bill_time().split(" ");
					bed.setDis_bill_date(t1[0]);
					bed.setDis_bill_time(t1[1]);
				}

				if (discharge.getDis_nursing_time() != null) {
					String t1[] = discharge.getDis_nursing_time().split(" ");
					bed.setDis_nursing_date(t1[0]);
					bed.setDis_nursing_time(t1[1]);
				}

				bed.setDis_form_status(discharge.getDis_form_status());

				bed.setDis_mdicine_status(discharge.getDis_mdicine_status());

				bed.setDis_bill_status(discharge.getDis_bill_status());

				bed.setDis_nursing_status(discharge.getDis_nursing_status());

				ClientDAO clientDAO = new JDBCClientDAO(connection);
				Client client = clientDAO.getClientDetails(bed.getClientid());
				String clientname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();
				bed.setClientname(clientname);
				bed.setDob(client.getDob());
				bed.setAge(DateTimeUtils.getAge(client.getDob()));
				bed.setTown(client.getTown());
				bed.setPatientIdAbrivation(client.getAbrivationid());
				bed.setWhopay(client.getWhopay());
				bed.setTpid(client.getTypeName());
				bed.setClientemail(client.getEmail());
				bed.setGender(client.getGender());

				// practitioner details
				UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
				UserProfile userProfile = userProfileDAO
						.getUserprofileDetails(Integer.parseInt(bed.getPractitionerid()));
				String practitionername = userProfile.getInitial() + " " + userProfile.getFirstname() + " "
						+ userProfile.getLastname();
				bed.setPractitionername(practitionername);
				bed.setPractitionerMob(userProfile.getMobile());

				list.add(bed);

			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return list;
	}

	private Bed getPatientBedDetails(int bedid) {
		PreparedStatement preparedStatement = null;
		Bed bed = new Bed();
		String sql = "SELECT id,sectionid,bedname,wardid FROM apm_ipd_bed where id = " + bedid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				bed.setId(rs.getInt(1));
				bed.setSectionid(rs.getString(2));
				bed.setBedname(rs.getString(3));
				bed.setWardid(rs.getString(4));

			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return bed;
	}

	private int getDischargeLogBedid(int ipdid) {
		PreparedStatement preparedStatement = null;
		int resyult = 0;
		String sql = "SELECT bedid FROM ipd_discharge_log where admissionid = " + ipdid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				resyult = rs.getInt(1);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return resyult;
	}

	private int getTreatmentEpisodeIpdid(int treatmentepisodeid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT id FROM ipd_addmission_form where treatmentepisodeid = " + treatmentepisodeid + " ";

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

	public int getSumOfProdQty(String prodid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT sum(quantity) FROM apm_patient_complete_apmt where prodid=" + prodid + " ";

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

	public double getDebitAmountList(String clientid, String date) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		date = DateTimeUtils.getCommencingDate1(date);
		String sql = "SELECT sum(charge*quantity) FROM apm_invoice_assesments where clientid = " + clientid
				+ " and commencing >='" + date + "' and paybuy=0 ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				/*
				 * double debit = rs.getDouble(1); double discount = rs.getDouble(2);
				 * 
				 * double discamt = (debit * discount) / 100; debit = debit - discamt;
				 */
				/*
				 * double charge=rs.getDouble(1); int qty=rs.getInt(2);
				 */
				result = rs.getDouble(1);

			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return result;
	}

	public ArrayList<Bed> getClientVisitingConsultList(String ipdid, String date) {

		UserProfileDAO profileDAO = new JDBCUserProfileDAO(connection);
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		BedDao bedDao = new JDBCBedDao(connection);
		ArrayList<Bed> list = new ArrayList<Bed>();
		date = DateTimeUtils.getCommencingDate1(date);
		String sql = "";
		try {

			sql = "select id, ipdid, practitionerid, date, time, fees, clientid, status, payment from ipd_visiting_consultant where ipdid='"
					+ ipdid + "' and date between '" + date + "' and '" + date + "' ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				Bed bed = new Bed();
				bed.setId(rs.getInt(1));
				bed.setIpdid(rs.getString(2));
				bed.setPractitionerid(rs.getString(3));
				String practitioner = profileDAO.getFullName(bed.getPractitionerid());
				bed.setPractitionername(practitioner);
				bed.setDate(rs.getString(4));
				bed.setTime(rs.getString(5));
				bed.setFees(rs.getString(6));
				bed.setClientid(rs.getString(7));

				Bed bed2 = bedDao.getEditIpdData(bed.getIpdid());
				String wardname = getIpdWardName(bed2.getWardid());
				String bedname = getIpdBedName(bed2.getBedid());
				bed.setWardname(wardname + "/" + bedname);

				Client client = clientDAO.getClientDetails(bed.getClientid());
				String fullname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();
				bed.setClientname(fullname);

				bed.setStatus(rs.getString(8));
				bed.setPayment(rs.getString(9));
				list.add(bed);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	public int addVisitingConsult(Bed bed) {

		int result = 0;

		try {

			String sql = "insert into ipd_visiting_consultant (ipdid, practitionerid, date, time, fees, "
					+ "clientid, status, payment,tds,visi_bedid,visi_wardid,visi_shareper) values (?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, bed.getIpdid());
			ps.setString(2, bed.getPractitionerid());
			ps.setString(3, bed.getDate());
			ps.setString(4, bed.getTime());
			ps.setString(5, bed.getFees());
			ps.setString(6, bed.getClientid());
			ps.setString(7, bed.getStatus());
			ps.setString(8, bed.getPayment());
			ps.setString(9, bed.getTds());

			ps.setString(10, bed.getBedid());
			ps.setString(11, bed.getWardid());
			ps.setString(12, bed.getSharePercent());

			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Bed> getVisitingConsult(String clientid, String ipdid) {

		ArrayList<Bed> list = new ArrayList<Bed>();
		UserProfileDAO profileDAO = new JDBCUserProfileDAO(connection);

		BedDao bedDao = new JDBCBedDao(connection);
		try {

			String sql = "select id, ipdid, practitionerid, date, time, fees, clientid, status, payment from ipd_visiting_consultant where clientid="
					+ clientid + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				Bed bed = new Bed();
				bed.setId(rs.getInt(1));
				bed.setIpdid(rs.getString(2));
				Bed bed2 = bedDao.getEditIpdData(bed.getIpdid());
				String wardname = getIpdWardName(bed2.getWardid());
				String bedname = getIpdBedName(bed2.getBedid());
				bed.setWardname(wardname + "/" + bedname);
				bed.setPractitionerid(rs.getString(3));
				String practitioner = profileDAO.getFullName(bed.getPractitionerid());
				bed.setPractitionername(practitioner);
				bed.setDate(rs.getString(4));
				bed.setTime(rs.getString(5));
				bed.setFees(rs.getString(6));
				bed.setClientid(rs.getString(7));
				bed.setStatus(rs.getString(8));
				bed.setPayment(rs.getString(9));
				list.add(bed);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	public int updatePractitionerVisitedorNot(String id, String status) {

		int result = 0;

		try {

			String sql = "update ipd_visiting_consultant set status=" + status + " where id=" + id + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	public Bed getIpdVisitingConsult(String visitid) {

		Bed bed = new Bed();
		UserProfileDAO profileDAO = new JDBCUserProfileDAO(connection);
		try {

			String sql = "select id, ipdid, practitionerid, date, time, fees, clientid, status, payment,tds from ipd_visiting_consultant where id="
					+ visitid + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				bed.setId(rs.getInt(1));
				bed.setIpdid(rs.getString(2));
				bed.setPractitionerid(rs.getString(3));
				String practitioner = profileDAO.getFullName(bed.getPractitionerid());
				bed.setPractitionername(practitioner);
				bed.setDate(rs.getString(4));
				bed.setTime(rs.getString(5));
				bed.setFees(rs.getString(6));
				bed.setClientid(rs.getString(7));
				bed.setStatus(rs.getString(8));
				bed.setPayment(rs.getString(9));
				bed.setTds(rs.getString(10));
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return bed;
	}

	public int updateVisitingPaymentStatus(String status, String visitid) {

		int result = 0;

		try {
			String sql = "update ipd_visiting_consultant set payment=" + status + " where id=" + visitid + "";
			PreparedStatement ps = connection.prepareStatement(sql);

			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateVisitingConsult(Bed bed) {

		int result = 0;

		try {

			/*
			 * String sql =
			 * "update ipd_visiting_consultant set practitionerid=?, date=?, time=?, fees=?,ipdid=?,clientid=? where id="
			 * + bed.getId() + "";
			 */
			String sql = "update ipd_visiting_consultant set practitionerid=?, date=?, time=?, fees=?,tds=? where id="
					+ bed.getId() + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, bed.getPractitionerid());
			ps.setString(2, bed.getDate());
			ps.setString(3, bed.getTime());
			ps.setString(4, bed.getFees());
			ps.setString(5, bed.getTds());
			/*
			 * ps.setString(5, bed.getIpdid()); ps.setString(6, bed.getClientid());
			 */

			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public int deleteIpdVisitConsult(String visitid) {

		int result = 0;
		try {

			String sql = "delete from ipd_visiting_consultant where id=" + visitid + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Bed> getAllActiveIpdPatients() {

		ArrayList<Bed> list = new ArrayList<Bed>();
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		try {

			String sql = "SELECT id,wardid,sectionid,bedname FROM apm_ipd_bed where active=1";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				Bed bed = new Bed();
				bed.setId(rs.getInt(1));
				bed.setWardid(rs.getString(2));
				bed.setSectionid(rs.getString(3));

				boolean checkbedstatus = checkBedStatus(bed.getId());
				if (checkbedstatus) {

					Ipd ipd = getAddmisionFormDetails(bed.getId());
					Client client = clientDAO.getClientDetails(ipd.getClientid());
					String fullname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();
					bed.setClientid(ipd.getClientid());
					bed.setIpdid(String.valueOf(ipd.getId()));
					bed.setClientname(fullname);
					list.add(bed);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public String getIpdId(String clientid) {

		String ipdid = "";
		try {

			String sql = "select id from ipd_addmission_form where clientid=" + clientid + " order by id desc limit 1";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ipdid = rs.getString(1);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return ipdid;
	}

	public ArrayList<Priscription> getAJaxPriscription(String ipdid) {

		ArrayList<Priscription> ipdPriscList = new ArrayList<Priscription>();
		try {

			// set patient prescription
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			// cal.add(Calendar.DATE, -7);
			String currDate = dateFormat.format(cal.getTime());

			ArrayList<Priscription> parentPriscList = ipdDAO.getParentPriscList(ipdid);
			for (Priscription priscription : parentPriscList) {
				String temp[] = priscription.getDate().split(" ");
				String mdicinestartdate = temp[0];

				long mdicinedays = DateTimeUtils.getDiffofTwoDates(mdicinestartdate, currDate);
				mdicinedays++;

				ArrayList<Priscription> clientPriscList = getClientPriscList(priscription.getParentid());
				for (Priscription pr : clientPriscList) {
					String dosecolumn = "";
					String dosage = pr.getDosage();
					if (dosage != null) {
						String dosetemp[] = dosage.split("-");

						if (dosetemp.length < 3) {
							dosage = getAlterNateDose(dosage);
							dosetemp = dosage.split("-");
						}

						int c = 0;
						for (int i = 1; i <= dosetemp.length; i++) {

							dosecolumn = dosecolumn + "dos" + i + ",";
							c++;
						}
						dosecolumn = dosecolumn.substring(0, dosecolumn.length() - 1);

						Priscription ipdPrescriptionList = getIpdPrescriptionList(pr.getId(), mdicinedays, dosecolumn,
								pr.getMdicinenametxt(), pr.getDosage());
						ipdPrescriptionList.setDosenotes(pr.getDosenotes());
						ipdPrescriptionList.setPrisctimename(pr.getPrisctimename());
						ipdPrescriptionList.setPriscindivisualremark(pr.getPriscindivisualremark());
						ipdPrescriptionList.setPrischildid("" + pr.getId());
						ipdPrescriptionList.setDosegiventime(pr.getDosegiventime());
						ipdPrescriptionList.setTotaldays(pr.getDays());
						ipdPriscList.add(ipdPrescriptionList);
					}
				}

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return ipdPriscList;
	}

	public ArrayList<Master> getAJaxNursing(String ipdid) {

		ArrayList<Master> ipdNursingList = new ArrayList<Master>();
		try {

			// set patient prescription
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			// cal.add(Calendar.DATE, -7);
			String currDate = dateFormat.format(cal.getTime());

			ArrayList<Master> parentNursingList = ipdDAO.getParentNursingList(ipdid);
			for (Master master : parentNursingList) {
				String temp[] = master.getDate().split(" ");
				String mdicinestartdate = temp[0];

				long mdicinedays = DateTimeUtils.getDiffofTwoDates(mdicinestartdate, currDate);
				mdicinedays++;

				ArrayList<Master> clientNursingList = ipdDAO.getClientNursingList(master.getParentid());
				for (Master pr : clientNursingList) {
					String dosecolumn = "";

					if (pr.getFrequency() != null) {
						String dosetemp[] = pr.getFrequency().split("-");
						int c = 0;
						for (int i = 1; i <= dosetemp.length; i++) {

							dosecolumn = dosecolumn + "dos" + i + ",";
							c++;
						}
						dosecolumn = dosecolumn.substring(0, dosecolumn.length() - 1);

						Master masterNursinglist = getIpdNursingList(pr.getId(), mdicinedays, dosecolumn,
								pr.getTaskname(), pr.getFrequency());
						ipdNursingList.add(masterNursinglist);
					}
				}

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return ipdNursingList;
	}

	public ArrayList<Master> getParentNursingList(String admissionid) {

		PreparedStatement preparedStatement = null;
		ArrayList<Master> list = new ArrayList<Master>();
		String sql = "SELECT id,lastmodified FROM apm_client_parent_nursing where ipdid = " + admissionid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setParentid(rs.getString(1));
				master.setDate(rs.getString(2));

				list.add(master);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public ArrayList<Master> getClientNursingList(String parentid) {

		PreparedStatement preparedStatement = null;
		ArrayList<Master> list = new ArrayList<Master>();
		String sql = "SELECT id,days,frequency,taskname FROM apm_client_nursing where parentid = " + parentid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setDays(rs.getInt(2));
				master.setFrequency(rs.getString(3));
				master.setTaskname(rs.getString(4));

				list.add(master);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean checkNursingExist(long mdicinedays, int id) {

		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "SELECT id FROM ipd_nursing_reminder where nursingid = " + id + " and days = " + mdicinedays + " ";
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

	public int saveNursingReminder(String freqcolumn, String freqqmark, long days, int nursingid, String ipdid) {

		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into ipd_nursing_reminder(" + freqcolumn + ",nursingid,days,ipdid) values(" + freqcolumn
				+ "," + nursingid + "," + days + "," + ipdid + ")";

		try {
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	public ArrayList<Bed> getAllNursingDoseTodayofPatient(String wardid, LoginInfo loginInfo) {

		ArrayList<Bed> list = new ArrayList<Bed>();
		BedDao bedDao = new JDBCBedDao(connection);
		int ipdid = 0;
		PreparedStatement preparedStatement = null;
		String sql = "";
		try {

			if (wardid == null || wardid.equals("0")) {
				sql = "SELECT id,wardid,sectionid,bedname FROM apm_ipd_bed where active=1 ";
			} else {
				sql = "SELECT id,wardid,sectionid,bedname FROM apm_ipd_bed where wardid = " + wardid + " and active=1 ";
			}

			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				ArrayList<Master> ipdNursingList = new ArrayList<Master>();

				Bed bed = new Bed();
				bed.setId(rs.getInt(1));
				bed.setWardid(rs.getString(2));
				bed.setSectionid(rs.getString(3));
				bed.setBedname(rs.getString(4));

				boolean checkbedstatus = checkBedStatus(bed.getId());
				if (checkbedstatus) {
					bed.setStatus("1");
					Ipd ipd = getAddmisionFormDetails(bed.getId());
					bed.setAdmissiondate(ipd.getAdmissiondate());
					bed.setAddmissionid(Integer.toString(ipd.getId()));
					bed.setClientid(ipd.getClientid());
					bed.setPractitionerid(ipd.getPractitionerid());
					bed.setConditionid(ipd.getConditionid());

					ClientDAO clientDAO = new JDBCClientDAO(connection);
					Client client = clientDAO.getClientDetails(bed.getClientid());
					String clientname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();
					bed.setClientname(clientname);
					bed.setDob(client.getDob());
					bed.setAge(DateTimeUtils.getAge(client.getDob()));
					bed.setTown(client.getTown());
					bed.setWhopay(client.getWhopay());
					bed.setTpid(client.getTypeName());
					bed.setClientemail(client.getEmail());
					bed.setGender(client.getGender());

					String agegender = bed.getAge() + " yr/" + bed.getGender();
					bed.setAge(agegender);
					bed.setId(ipd.getId());
					ipdid = bed.getId();

					Bed bed2 = bedDao.getEditIpdData(String.valueOf(bed.getId()));

					String wardname = getIpdWardName(bed2.getWardid());
					String bedname = getIpdBedName(bed2.getBedid());
					bed.setWardname(wardname + " " + bedname);

					String today = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
					String nextday = DateTimeUtils.getNextDaysInSimpleDBFormat(1);

					today = today.substring(0, 10);
					String query = "SELECT id,clientid,taskname,frequency,days,parentid FROM apm_client_nursing where clientid="
							+ bed.getClientid() + " and datetime between '" + today + "' and '" + nextday + "' ";

					PreparedStatement ps = connection.prepareStatement(query);
					ResultSet rs1 = ps.executeQuery();

					boolean flag = false;

					while (rs1.next()) {

						flag = true;
						Master master = new Master();
						master.setId(rs1.getInt(1));
						master.setClientid(rs1.getString(2));
						master.setClientname(bed.getClientname());
						master.setAgeandgender(bed.getAge() + " yr/" + bed.getGender());
						master.setTaskname(rs1.getString(3));
						master.setFrequency(rs1.getString(4));
						master.setDays(rs1.getInt(5));

						String parentid = rs1.getString(6);

						Master master2 = getParentNursing(parentid);

						master.setIpdid(String.valueOf(ipdid));
						master.setNotes(master2.getNotes());

						String dosecolumn = "";

						if (!master.getFrequency().startsWith("0") && !master.getFrequency().startsWith("1")) {

							master.setDosesize(3);
							master.setDos1(false);
							master.setDos2(false);
							master.setDos3(false);
							master.setDos4(false);
							master.setDos5(false);
							master.setDos6(false);
							master.setDos7(false);
							master.setDos8(false);
							master.setDos9(false);
							master.setDos10(false);
							master.setDosevalue1("1");
							master.setDosevalue2("1");
							master.setDosevalue3("1");
							ipdNursingList.add(master);
							bed.setIpdNursingList(ipdNursingList);
							list.add(bed);
							continue;

						}

						String dosetemp[] = master.getFrequency().split("-");

						if (dosetemp.length < 2) {

							String alternateDose = getAlterNateDose(master.getFrequency());
							dosetemp = alternateDose.split("-");
						}

						for (int i = 1; i <= dosetemp.length; i++) {

							dosecolumn = dosecolumn + "dos" + i + ",";
						}

						String query1 = "SELECT id,dos1,dos2,dos3,dos4,dos5,dos6,dos7,dos8,dos9,dos10 FROM ipd_nursing_reminder where nursingid="
								+ master.getId() + " ";

						PreparedStatement ps2 = connection.prepareStatement(query1);
						ResultSet rs2 = ps2.executeQuery();

						while (rs2.next()) {

							master.setId(rs2.getInt(1));
							master.setIpdid(bed.getAddmissionid());
							String temp[] = dosecolumn.split(",");
							master.setDosesize(temp.length);

							master.setDos1(rs2.getBoolean(2));
							master.setDos2(rs2.getBoolean(3));
							master.setDos3(rs2.getBoolean(4));
							master.setDos4(rs2.getBoolean(5));
							master.setDos5(rs2.getBoolean(6));
							master.setDos6(rs2.getBoolean(7));
							master.setDos7(rs2.getBoolean(8));
							master.setDos8(rs2.getBoolean(9));
							master.setDos9(rs2.getBoolean(10));
							master.setDos10(rs2.getBoolean(11));

							String values[] = master.getFrequency().split("-");

							if (values.length <= 3) {

								master.setDosevalue1(values[0]);
								master.setDosevalue2(values[1]);
								master.setDosevalue3(values[2]);

							} else if (values.length == 4) {
								master.setDosevalue1(values[0]);
								master.setDosevalue2(values[1]);
								master.setDosevalue3(values[2]);
								master.setDosevalue4(values[3]);
							}

						}

						ipdNursingList.add(master);

					}

					if (!flag) {

						Master priscription = new Master();
						priscription.setClientname(bed.getClientname());
						priscription.setAgeandgender(bed.getAge() + " yr/" + bed.getGender());
						priscription.setTaskname("N/A");
						priscription.setFrequency("N/A");
						priscription.setIpdid(bed.getAddmissionid());
						priscription.setDosesize(0);
						ipdNursingList.add(priscription);
					}

					bed.setIpdNursingList(ipdNursingList);
					list.add(bed);
				}

			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	public String getParentPriscId(String selectedid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT id FROM apm_client_parent_priscription where ipdid = " + selectedid
				+ " and discharge=1 order by id desc limit 0,1";

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

	public String getDIscPrisc(String selectedid) {

		String name = "";
		try {

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT advoice FROM apm_client_parent_priscription inner join apm_client_priscription ");
			sql.append("on apm_client_priscription.parentid = apm_client_parent_priscription.id ");
			sql.append("where ipdid = " + selectedid + " and discharge=1 ");

			PreparedStatement ps = connection.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				name = rs.getString(1);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return name;
	}

	public int savefinalConditionDischarge(String sessionadmissionid, String treatmentEpisodeid, String datetime,
			String conditionname) {

		int result = 0;
		int id = 0;
		try {

			String sql1 = "SELECT id from apm_final_diagnosis where treatmentepisodeid='" + treatmentEpisodeid
					+ "' and ipdid='" + sessionadmissionid + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql1);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				id = rs.getInt(1);

			}

			if (id == 0) {

				String sql = "insert into apm_final_diagnosis (ipdid,treatmentepisodeid,conditionid,datetime) values (?,?,?,?)";
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setString(1, sessionadmissionid);
				ps.setString(2, treatmentEpisodeid);
				ps.setString(3, conditionname);
				ps.setString(4, datetime);

				result = ps.executeUpdate();
			} else {

				String sql = "update apm_final_diagnosis set ipdid=?,treatmentepisodeid=?,conditionid=?,datetime=? where id="
						+ id + "";
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setString(1, sessionadmissionid);
				ps.setString(2, treatmentEpisodeid);
				ps.setString(3, conditionname);
				ps.setString(4, datetime);

				result = ps.executeUpdate();

			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public Bed getAllFinalCondition(String ipdid, String tpid) {

		Bed bed = new Bed();

		try {

			String sql = "Select conditionid from apm_final_diagnosis where ipdid=" + ipdid + " and treatmentepisodeid="
					+ tpid + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				bed.setConditionname(rs.getString(1));

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return bed;
	}

	public String getNumofAdmissionCount(String clientid) {

		String count = "0";

		try {

			String sql = "select id from ipd_addmission_form where clientid=" + clientid + " order by id desc";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			int result = 0;
			while (rs.next()) {
				result++;
			}

			if (result == 0) {
				result++;
			}
			count = String.valueOf(result);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return count;
	}

	public ArrayList<Bed> getBedLogClientList(String clientid, String admissionid) {

		ArrayList<Bed> list = new ArrayList<Bed>();
		IpdLogDAO ipdLogDAO = new JDBCIpdLogDAO(connection);
		try {

			String sql = "select id,wardid,bedid from ipd_addmission_form where clientid=" + clientid
					+ " order by id desc";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				Bed bed = new Bed();
				bed.setId(rs.getInt(1));
				admissionid = "" + bed.getId() + "";
				bed.setWardid(rs.getString(2));
				bed.setBedid(rs.getString(3));
				if (rs.getInt(3) == 0) {
					String bedid = ipdLogDAO.getDischargeBedId(rs.getString(1));
					bed.setBedname(bedid);
					bed.setWardname(getIpdWardName(bed.getWardid()));
				} else {
					bed.setWardname(getIpdWardName(bed.getWardid()));
					bed.setBedname(getIpdBedName(bed.getBedid()));
				}

				list.add(bed);
			}

			boolean isbedchanged = ipdLogDAO.isBedChanged(admissionid, clientid);

			if (isbedchanged) {
				ArrayList<Bed> listBedChanged = ipdLogDAO.getBedChangeLogList(clientid, admissionid);
				list.addAll(listBedChanged);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	public ArrayList<Master> getFilteredOpdChargeList(String chargetype, String tpid, String payby) {

		if (!checkifMainTp(tpid)) {

			String temptpid = getFollowerTp(tpid);
			if (temptpid != null) {

				if (!temptpid.equals("0")) {
					tpid = temptpid;
				}
			}

		}

		ArrayList<Master> list = new ArrayList<Master>();

		boolean checkInventoryChargeType = checkInventoryChargeType(chargetype);

		String sql = "";

		if (checkInventoryChargeType) {
			sql = "SELECT id,name FROM apm_appointment_type where tpid=" + tpid + " and chargeType='" + chargetype
					+ "' and stdcharge=0 order by name ";
		} else {
			sql = "SELECT inventory_product.id,prodname,stock FROM inventory_product "
					+ "inner join inventory_category on inventory_category.id = inventory_product.categoryid "
					+ "where inventory_category.name = '" + chargetype + "'  order by prodname ";
		}

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				if (!checkInventoryChargeType) {
					master.setName(rs.getString(2) + " (" + rs.getString(3) + ")");
				}

				list.add(master);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	public String getwardIdfromName(String wardname) {

		String wardid = "0";
		try {

			String sql = "select id from apm_ipd_ward where wardname='" + wardname + "'";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				wardid = rs.getString(1);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return wardid;
	}

	public int getLastIpdId(String clientid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT id FROM ipd_addmission_form where clientid = " + clientid + " order by id desc limit 0,1 ";

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

	public boolean checkifMainTp(String tpid) {

		boolean res = false;
		try {

			String sql = "select maintp from apm_third_party_details where id=" + tpid + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				res = rs.getBoolean(1);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return res;
	}

	public String getFollowerTp(String tpid) {

		String result = "0";
		try {

			String sql = "select followtp from apm_third_party_details where id=" + tpid + "";
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

	public ArrayList<Mrd> getmrddetails(String fromdate, String todate, String searchText, String wardnameid) {
		ArrayList<Mrd> arrayList = new ArrayList<Mrd>();
		try {

			StringBuffer buffer = new StringBuffer();
			boolean flag = false;

			buffer.append(
					"select ipd_addmission_form.id, ipd_addmission_form.clientid, ipd_addmission_form.bedid, ipd_addmission_form.wardid, ipd_addmission_form.admissiondsate ");
			buffer.append("from ipd_addmission_form ");

			if (searchText != null) {
				flag = true;
				buffer.append("INNER JOIN apm_patient ON  ipd_addmission_form.clientid = apm_patient.id  ");
				buffer.append("where apm_patient.firstname like ('" + searchText + "%') or surname like ('%"
						+ searchText + "')  ");
			}
			if (flag) {
				buffer.append("and  admissiondsate between '" + fromdate + "' and '" + todate + "'  ");
			} else {
				buffer.append("where admissiondsate between '" + fromdate + "' and '" + todate + "'  ");
			}

			if (wardnameid != null) {
				buffer.append("and ipd_addmission_form.wardid=" + wardnameid + " ");
			}

			String query = buffer.toString();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Mrd mrd = new Mrd();
				mrd.setId(rs.getInt(1));
				mrd.setClientid(rs.getString(2));
				mrd.setBedid(rs.getString(3));
				mrd.setWardid(rs.getString(4));
				String admissiondatetime = rs.getString(5);
				// mrd.setAdmissiondsate(rs.getString(5));
				// String firstname = rs.getString(6);
				// String surname = rs.getString(7);

				String[] splited = admissiondatetime.split(" ");
				String admissiondate = splited[0];
				String admissiondate1 = DateTimeUtils.getCommencingDate2(admissiondate);
				mrd.setAdmissiondsate(admissiondate1);

				String wardid = mrd.getWardid();
				String wardname = getIpdWardName(wardid);
				mrd.setWardname(wardname);

				String bedname = getIpdBedName(mrd.getBedid());
				mrd.setBedname(bedname);

				mrd.setFromdate(fromdate);
				mrd.setTodate(todate);

				IpdLogDAO ipdLogDAO = new JDBCIpdLogDAO(connection);
				int ipdid = mrd.getId();

				String dischargedatetime = ipdLogDAO.getDischargeDate(ipdid);
				if (dischargedatetime == "")
					dischargedatetime = null;

				if (dischargedatetime == null || dischargedatetime.equals(null)) {
					mrd.setDischargedate(dischargedatetime);
				} else {
					String[] split1 = dischargedatetime.split(" ");
					String dischargedate = split1[0];
					String[] temp = dischargedate.split("-");
					dischargedate = temp[2] + "/" + temp[1] + "/" + temp[0];
					mrd.setDischargedate(dischargedate);
				}

				if (bedname.equals("")) {
					String bedid = ipdLogDAO.getDischargeBedId("" + ipdid);
					String newbedname = getIpdBedName(bedid);
					mrd.setBedname(newbedname);
				}

				com.apm.Mrd.eu.entity.Mrd mrd1 = getmrdinfo(ipdid);
				String shelf_no = mrd1.getShelf_no();
				String place = mrd1.getPlace();
				String remark = mrd1.getRemark();
				String mlc = mrd1.getMlc();
				mrd.setShelf_no(shelf_no);
				mrd.setPlace(place);
				mrd.setRemark(remark);
				mrd.setMlc(mlc);
				String status = mrd1.getStatus();
				mrd.setStatus(status);

				ClientDAO clientDAO = new JDBCClientDAO(connection);
				Client client = clientDAO.getClientDetails(mrd.getClientid());
				String clientname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();
				mrd.setClientname(clientname);
				mrd.setWhopay(client.getWhopay());
				// bed.setTpid(client.getTypeName());
				if (mrd.getWhopay() != null) {

					if (mrd.getWhopay().equalsIgnoreCase("Client") || mrd.getWhopay().equalsIgnoreCase("Self")) {

						mrd.setPayby("SELF");
					} else {
						mrd.setPayby("TP");
						// ThirdParty thirdParty=client.getTpDetails();
						// mrd.setPayby(thirdParty.getShortname());
						ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
						ThirdParty thirdParty = thirdPartyDAO.getThirdPartyDetails(client.getTypeName());
						mrd.setPayby(thirdParty.getCompanyName());

					}
					// ThirdPartyDAO thirdPartyDAO = new
					// JDBCThirdPartyDAO(connection);
					// ThirdParty thirdParty =
					// thirdPartyDAO.getThirdPartyDetails(client.getTypeName());
					// mrd.setTpname(thirdParty.getCompanyName());
					//
				}
				arrayList.add(mrd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public Mrd getmrdinfo(int ipdid) {
		Mrd mrd1 = new Mrd();
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT ipdid,clientid, shelf_no, place, remark, mlc, status  FROM apm_mrd where ipdid  = " + ipdid
				+ " ";

		try {

			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				mrd1 = new Mrd();
				mrd1.setId(rs.getInt(1));
				mrd1.setClientid(rs.getString(2));
				mrd1.setShelf_no(rs.getString(3));
				mrd1.setPlace(rs.getString(4));
				mrd1.setRemark(rs.getString(5));
				mrd1.setMlc(rs.getString(6));
				mrd1.setStatus(rs.getString(7));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mrd1;
	}

	public int updatemrdetails(Mrd mrd) {
		int result = 0;
		Boolean flag = false;
		int ipdid1 = 0;
		String clientid1 = "";
		try {
			String query = "select ipdid,clientid from apm_mrd where ipdid=" + mrd.getId();
			PreparedStatement stmt = connection.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				ipdid1 = rs.getInt(1);
				clientid1 = rs.getString(2);
				flag = true;
			}

			if (flag == false) {
				String query1 = "insert into apm_mrd (ipdid,clientid, shelf_no, place, remark, mlc,status) values(?,?,?,?,?,?,?)";
				PreparedStatement stmt1 = connection.prepareStatement(query1);
				stmt1.setInt(1, mrd.getId());
				stmt1.setString(2, mrd.getClientid());
				stmt1.setString(3, mrd.getShelf_no());
				stmt1.setString(4, mrd.getPlace());
				stmt1.setString(5, mrd.getRemark());
				stmt1.setString(6, mrd.getMlc());
				stmt1.setString(7, mrd.getStatus());
				result = stmt1.executeUpdate();
			} else {
				int id = ipdid1;
				String query2 = "update apm_mrd set shelf_no=?, place=?, remark=?, mlc=?, status=? where ipdid="
						+ ipdid1;
				PreparedStatement stmt2 = connection.prepareStatement(query2);
				stmt2.setString(1, mrd.getShelf_no());
				stmt2.setString(2, mrd.getPlace());
				stmt2.setString(3, mrd.getRemark());
				stmt2.setString(4, mrd.getMlc());
				stmt2.setString(5, mrd.getStatus());
				result = stmt2.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int changeMrdStatus(String id) {
		int result = 0;
		try {
			int ipdid = Integer.parseInt(id);
			String query2 = "update apm_mrd set status=? where ipdid=" + ipdid;
			PreparedStatement stmt2 = connection.prepareStatement(query2);
			stmt2.setString(1, "0");
			result = stmt2.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String[] getFromIpdOrOpd(String clientId) {

		String[] str = null;
		boolean flag = false;
		String doctorId = "0";
		String conditionId = "0";
		try {
			String sql1 = "select practitionerid,conditionid from ipd_addmission_form where clientid=" + clientId
					+ " and bedid!=0 ";
			PreparedStatement ps1 = connection.prepareStatement(sql1);
			ResultSet rs1 = ps1.executeQuery();
			while (rs1.next()) {

				doctorId = rs1.getString(1);
				conditionId = rs1.getString(2);
				flag = true;
			}

			if (!flag) {

				String sql2 = "select diaryuserid,condition_id from apm_available_slot where clientId=" + clientId
						+ " ";
				PreparedStatement ps2 = connection.prepareStatement(sql2);
				ResultSet rs2 = ps2.executeQuery();

				while (rs2.next()) {

					doctorId = rs2.getString(1);
					conditionId = rs2.getString(2);
				}
			}
			if (doctorId == null) {
				doctorId = "0";
			}
			if (conditionId == null) {
				conditionId = "0";
			}

			str = new String[] { doctorId, conditionId };
		} catch (Exception e) {

			e.printStackTrace();
		}
		return str;
	}

	private String getchargeinvoiceid(String id) {
		String chargeinvoiceid = "";
		try {
			String query = "select chargeinvoiceid from apm_charges_assesment where invoiceid=" + id + "";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				// bed.setChargeinvoiceid(rs.getString(1));
				chargeinvoiceid = rs.getString(1);
				String str = chargeinvoiceid;
				String str1 = str;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return chargeinvoiceid;
	}

	private String getpaymentinfo(String id) {
		String chargeinvoiceid = "";
		try {
			String query = "select chargeinvoiceid from apm_charges_payment where chargeinvoiceid='" + id + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				chargeinvoiceid = rs.getString(1);
				String str = chargeinvoiceid;
				String str1 = str;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return chargeinvoiceid;
	}

	private Bed getinvoicedate(String appointmentid) {
		Bed bed = new Bed();
		try {
			String query = "select appointmentid,id from apm_invoice where appointmentid=" + appointmentid + "";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {

				bed.setAppointmentid(rs.getString(1));
				bed.setId(rs.getInt(2));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bed;
	}

	private Bed getappointmentinfo(int id) {
		Bed bed = new Bed();
		;
		try {
			String query = "select id,commencing from apm_available_slot where ipdno=" + id + "";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				bed.setAppointmentid("" + rs.getInt(1));
				bed.setCommencing(rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bed;
	}

	// Dietary Ipd Patient list
	public ArrayList<Bed> getAllBedListForDietary(String wardid, int clinicid, String date1) {
		PreparedStatement preparedStatement = null;
		ArrayList<Bed> list = new ArrayList<Bed>();

		CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);

		String sql = "";

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		// cal.add(Calendar.DATE, -7);
		String currDate = dateFormat.format(cal.getTime());

		if (wardid == null || wardid.equals("0")) {
			sql = "SELECT id FROM apm_ipd_bed where active=1 ";
		} else {
			sql = "SELECT id FROM apm_ipd_bed where wardid = " + wardid + " and active=1 ";
		}

		try {

			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Bed bed = new Bed();
				bed.setId(rs.getInt(1));

				boolean checkbedstatus = checkBedStatus(bed.getId());
				if (checkbedstatus) {
					bed.setStatus("1");
					Ipd ipd = getAddmisionFormDetails(bed.getId());

					bed.setAddmissionid(Integer.toString(ipd.getId()));
					bed.setClientid(ipd.getClientid());

					ClientDAO clientDAO = new JDBCClientDAO(connection);
					Client client = clientDAO.getClientDetails(bed.getClientid());
					String clientname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();
					bed.setClientname(clientname);
				} else {
					bed.setStatus("0");
				}
				Boolean flag = false;
				DietaryDetailsDAO detailsDAO = new JDBCDietaryDetailsDAO(connection);
				if (date1 != null) {
					if (!date1.equals("")) {
						currDate = DateTimeUtils.getCommencingDate1(date1);
					}
				}
				ArrayList<DietaryDetails> arrayList = detailsDAO.getExistDiet(currDate);
				for (DietaryDetails dietaryDetails : arrayList) {
					if (bed.getAddmissionid() != null) {
						if (dietaryDetails.getIpdid().equals(bed.getAddmissionid())) {
							flag = true;
						}
					}
				}

				if (!flag) {
					list.add(bed);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public ArrayList<String> getAllSecondaryConsultList(String selectedid) {

		UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
		ArrayList<String> list = new ArrayList<String>();
		try {
			String temp = "0";
			String sql = "select secndryconsult from ipd_addmission_form where id=" + selectedid + " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				temp = rs.getString(1);
			}

			if (temp.length() > 1) {

				for (String str : temp.split(",")) {
					if (str.equals("0")) {
						continue;
					}
					UserProfile userProfile = userProfileDAO.getUserprofileDetails(Integer.parseInt(str));
					list.add(userProfile.getFullname());
				}

			} else if (!temp.equals("0")) {

				UserProfile userProfile = userProfileDAO.getUserprofileDetails(Integer.parseInt(temp));
				list.add(userProfile.getFullname());

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	public Priscription getMNameFromId(String string) {
		Priscription master = new Priscription();
		try {
			String sql = "SELECT id,drug,genericname FROM apm_medicine_details where id=" + string + " ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				master.setGenericname(rs.getString(3) + "- " + master.getName() + "");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return master;
	}

	public ArrayList<Master> getPackageList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Master> list = new ArrayList<Master>();
		String sql = "select id,name from his_parent_package ";

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

	public ArrayList<Accounts> getPackageDetailList(String id) {
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts> list = new ArrayList<Accounts>();
		String sql = "select id, chargeid, chargename, percentage, cal_amount,tpcode from  his_child_package where  packageid = "
				+ id + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Accounts accounts = new Accounts();
				accounts.setId(rs.getInt(1));
				accounts.setChargeid(rs.getString(2));
				accounts.setAppointmentType(rs.getString(3));
				accounts.setPercentage(DateTimeUtils.isNull(rs.getString(4)));
				accounts.setChargeTotal(rs.getString(5));
				accounts.setTpcode(rs.getString(6));
				list.add(accounts);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	public Master getParentPackageData(String id) {
		PreparedStatement preparedStatement = null;
		Master master = new Master();
		String sql = "SELECT id, name, amount,third_party,days FROM his_parent_package where id = " + id + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				master.setCharge(DateTimeUtils.changeFormat(rs.getDouble(3)));
				master.setThird_party(rs.getBoolean(4));
				master.setDays(rs.getInt(5));
			}

		} catch (Exception e) {
			// TODmO: handle exception
		}
		return master;
	}

	public ArrayList<Master> getPatientPackageList(String admissionid, String ipdclientid) {
		PreparedStatement preparedStatement = null;

		ArrayList<Master> list = new ArrayList<Master>();
		StringBuffer sql = new StringBuffer();
		sql.append(
				"SELECT packageid, name,apm_parent_patient_package.id FROM apm_parent_patient_package inner join his_parent_package on ");
		sql.append("his_parent_package.id = apm_parent_patient_package.packageid ");
		sql.append("where ipdid = " + admissionid + " ");

		try {
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				master.setMasterid(rs.getInt(3));
				list.add(master);
			}

		} catch (Exception e) {
			// TODmO: handle exception
		}
		return list;
	}

	public int saveparentpackagedata(String fromdate, String todate, PackageMaster master, String clientid,
			String ipdid, String amt) {
		int result = 0;
		try {
			String query = "insert into apm_parent_patient_package(packageid, fromdate, todate, amount, clientid, ipdid) values(?,?,?,?,?,?)";

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, "" + master.getId());
			preparedStatement.setString(2, fromdate);
			preparedStatement.setString(3, todate);
			preparedStatement.setString(4, amt);
			preparedStatement.setString(5, clientid);
			preparedStatement.setString(6, ipdid);
			result = preparedStatement.executeUpdate();

			if (result > 0) {
				ResultSet resultSet = preparedStatement.getGeneratedKeys();

				while (resultSet.next()) {
					result = resultSet.getInt(1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int savechildpackagedata(PackageMaster packageMaster, int result) {
		int result1 = 0;
		try {
			String query = "insert into apm_child_patient_package(parentid, chargeid, chargename, percentage, cal_amount) values(?,?,?,?,?)";

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, "" + result);
			statement.setString(2, packageMaster.getChargeid());
			statement.setString(3, packageMaster.getChargename());
			statement.setString(4, packageMaster.getPercentage());
			statement.setString(5, packageMaster.getCal_amount());
			result1 = statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result1;
	}

	public int updateDischargeBed(String bedid, String ipdid) {

		int result = 0;
		try {
			String sql = "update ipd_addmission_form set bedid=" + bedid + " where id=" + ipdid + " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			result = ps.executeUpdate();

			BedDao bedDao = new JDBCBedDao(connection);
			bedDao.updateAdmissionFormInParent(DateTimeUtils.convertToInteger(ipdid));
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	public int updateDischargeDateandStatus(String treatmentepisodeid, String admittnotes) {

		int res = 0;
		try {

			String sql = "update apm_treatment_episode set treatmentstatus=0,readmittnotes=? where id="
					+ treatmentepisodeid + " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, admittnotes);
			res = ps.executeUpdate();

			BedDao bedDao = new JDBCBedDao(connection);
			bedDao.updateDischargeForm(treatmentepisodeid);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return res;
	}

	public int updateDischargeBedandWard(String bedid, String wardid, String ipdid) {

		int result = 0;
		try {
			String sql = "update ipd_addmission_form set wardid=" + wardid + ",bedid=" + bedid + " where id=" + ipdid
					+ " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			result = ps.executeUpdate();

			BedDao bedDao = new JDBCBedDao(connection);
			bedDao.updateAdmissionFormInParent(DateTimeUtils.convertToInteger(ipdid));
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	public int updateInProcessCharges(String sessionadmissionid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_invoice_assesments set newshftcharge=0 where ipdid=" + sessionadmissionid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	// @ for count total bed
	public int getAllTotalBed(String wardid, int clinicid, LoginInfo loginInfo) {
		int count = 0;
		try {
			String sql = "";
			if (loginInfo.getUserType() == 2) {
				sql = "SELECT count(*) FROM apm_ipd_bed";
			} else {
				sql = "SELECT count(*) FROM apm_ipd_bed where wardid in(" + wardid + ")";
			}
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return count;
	}

	public ArrayList<Bed> getAllIpdBedList1(String wardid, String searchtext, String fromdate, String todate,
			String status) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getClientIDFromIPDID(String ipdid) {
		// 03 oct 2017 clientdid from ipdid
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT clientid FROM ipd_addmission_form where id = " + ipdid + " ";

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

	public ArrayList<Master> getVitalMasterList() {

		ArrayList<Master> list = new ArrayList<Master>();
		try {

			String sql = "SELECT id, name, department, min_value_m, max_value_m, min_value_f, max_value_f from his_vital_master";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				master.setDepartment(rs.getString(3));
				master.setMin_value_m(rs.getString(4));
				master.setMax_value_m(rs.getString(5));
				master.setMin_value_f(rs.getString(6));
				master.setMax_value_f(rs.getString(7));
				list.add(master);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return list;
	}

	public int saveClientVitalData(Master pmaster, String value, String time) {

		int res = 0;
		try {

			String sql = "insert into his_vital (vital_master_id, clientid, ipdid, date, time, datetime, userid, finding,isfromdcard) values ( ?, ?, ?, ?, ?, ?, ?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, pmaster.getVital_master_id());
			ps.setString(2, pmaster.getClientid());
			ps.setString(3, pmaster.getIpdid());
			ps.setString(4, pmaster.getDate());
			ps.setString(5, time);
			ps.setString(6, pmaster.getDatetime());
			ps.setString(7, pmaster.getUserid());
			ps.setString(8, value);
			ps.setString(9, pmaster.getIsfromdcard());
			res = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return res;
	}

	public boolean isVitalDateIsExist(String clientid, String ipdid, String date) {

		try {
			String sql = "select id from his_vital where clientid=" + clientid + " and ipdid=" + ipdid + " and date='"
					+ date + "' ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				return true;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return false;
	}

	public int getVitalClientDataId(Master pmaster, String time) {

		int res = 0;
		try {
			String sql = "select id from his_vital where clientid=" + pmaster.getClientid() + " and ipdid="
					+ pmaster.getIpdid() + " and date='" + pmaster.getDate() + "' and time='" + time
					+ "' and vital_master_id=" + pmaster.getVital_master_id() + " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				res = rs.getInt(1);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return res;
	}

	public int updateClientVitalData(String finding, int id, String dateTime) {

		int res = 0;
		try {
			String sql = "update his_vital set finding='" + finding + "', datetime='" + dateTime + "' where id=" + id
					+ " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			res = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<Master> getVitalMasterandValuesList(String ipdclientid, String ipdid, String date) {

		ArrayList<Master> list = new ArrayList<Master>();

		try {

			String sql = "SELECT id, name, department, min_value_m, max_value_m, min_value_f, max_value_f from his_vital_master";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				String vital_master_id = rs.getString(1);
				master.setDepartment(rs.getString(3));
				master.setMin_value_m(rs.getString(4));
				master.setMax_value_m(rs.getString(5));
				master.setMin_value_f(rs.getString(6));
				master.setMax_value_f(rs.getString(7));
				ArrayList<Master> vitalDataList = getVitalPropertyDataList(vital_master_id, ipdclientid, ipdid, date);
				master.setVitalDataList(vitalDataList);
				list.add(master);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return list;
	}

	private ArrayList<Master> getVitalPropertyDataList(String vital_master_id, String ipdclientid, String ipdid,
			String date) {

		ArrayList<Master> list = new ArrayList<Master>();
		ArrayList<String> timeList = getVitalClientTimeList(ipdid, ipdclientid, date);
		try {

			for (String time : timeList) {
				Master master = new Master();
				String keyname = vital_master_id + "t" + time;
				master.setKeyname(keyname);

				String finding = getFindingofVital(ipdclientid, ipdid, vital_master_id, time, date);
				master.setFinding(finding);
				list.add(master);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	public String getFindingofVital(String ipdclientid, String ipdid, String vital_master_id, String time,
			String date) {

		String finding = "0";
		String sql = "";
		try {

			if (!ipdid.equals("0")) {
				sql = "select finding from his_vital where vital_master_id=" + vital_master_id + " and clientid="
						+ ipdclientid + " and ipdid=" + ipdid + " and date='" + date + "' and time='" + time + "' ";
			} else {
				sql = "select finding from his_vital where vital_master_id=" + vital_master_id + " and clientid="
						+ ipdclientid + " and date='" + date + "' and time='" + time + "' ";
			}
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				finding = rs.getString(1);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return finding;
	}

	public ArrayList<String> getVitalTimeList() {

		ArrayList<String> list = new ArrayList<String>();
		list.add("08");
		list.add("09");
		list.add("10");
		list.add("11");
		list.add("12");
		list.add("13");
		list.add("14");
		list.add("15");
		list.add("16");
		list.add("17");
		list.add("18");
		list.add("19");
		list.add("20");
		list.add("21");
		list.add("22");
		list.add("23");
		list.add("00");
		list.add("01");
		list.add("02");
		list.add("03");
		list.add("04");
		list.add("05");
		list.add("06");
		list.add("07");
		return list;
	}

	public ArrayList<Master> getVitalDataList(String ipdid, String clientid, String date) {

		ArrayList<Master> list = new ArrayList<Master>();
		try {

			String sql = "SELECT id, name, department, min_value_m, max_value_m, min_value_f, max_value_f from his_vital_master";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				String vital_master_id = rs.getString(1);
				master.setDepartment(rs.getString(3));
				master.setMin_value_m(rs.getString(4));
				master.setMax_value_m(rs.getString(5));
				master.setMin_value_f(rs.getString(6));
				master.setMax_value_f(rs.getString(7));

				String finding = getVitalDataOfMaster(vital_master_id, ipdid, clientid, date);
				master.setFinding(finding);
				list.add(master);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	private String getVitalDataOfMaster(String vitalmasterid, String ipdid, String clientid, String date) {

		String finding = "0";
		String sql = "";
		try {
			if (!ipdid.equals("0")) {
				sql = "SELECT finding from his_vital where clientid=" + clientid + " and ipdid=" + ipdid
						+ " and vital_master_id=" + vitalmasterid + " and date='" + date + "' ";
			} else {
				sql = "SELECT finding from his_vital where clientid=" + clientid + " and vital_master_id="
						+ vitalmasterid + " and date='" + date + "' ";
			}

			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				finding = rs.getString(1);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return finding;
	}

	public ArrayList<String> getVitalClientTimeList(String ipdid, String ipdclientid, String date) {

		ArrayList<String> list = new ArrayList<String>();
		String sql = "";
		try {
			if (!ipdid.equals("0")) {
				sql = "select time from his_vital where clientid=" + ipdclientid + " and ipdid=" + ipdid + " and date='"
						+ date + "' group by time";
			} else {
				sql = "select time from his_vital where clientid=" + ipdclientid + " and date='" + date
						+ "' group by time";
			}

			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(rs.getString(1));
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Priscription> getTreatmentSheetPriscription(String ipdid, String clientid, String commencing) {

		ArrayList<Priscription> list = new ArrayList<Priscription>();
		try {
			String todate = commencing + " 23:59:59";
			String sql = "SELECT id,clientid,mdicinename,dose,days,parentid,notes FROM apm_client_priscription where clientid="
					+ clientid + " and lastmodified between '" + commencing + "' and '" + todate
					+ "' order by id desc ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Priscription priscription = new Priscription();
				priscription.setId(rs.getInt(1));
				priscription.setClientId(rs.getString(2));
				priscription.setMdicinenametxt(rs.getString(3));
				priscription.setDosage(rs.getString(4));
				priscription.setDays(rs.getInt(5));

				String parentid = rs.getString(6);

				Priscription priscription2 = getParentPrisc(parentid);

				priscription.setIpdid(String.valueOf(ipdid));
				priscription.setDosenotes(rs.getString(7));
				priscription.setAdvoice(priscription2.getAdvoice());

				String dosecolumn = "";

				String dosetemp[] = priscription.getDosage().split("-");
				if (dosetemp.length < 2) {

					String alternateDose = getAlterNateDose(priscription.getDosage());
					dosetemp = alternateDose.split("-");
				}

				for (int i = 1; i <= dosetemp.length; i++) {

					dosecolumn = dosecolumn + "dos" + i + ",";
				}
				String temp[] = dosecolumn.split(",");
				priscription.setDosesize(temp.length);

				String sql1 = "SELECT id,dos1,dos2,dos3,dos4,dos5,dos6,dos7,dos8,dos9,dos10,userid FROM ipd_prescription_reminder where prescid="
						+ priscription.getId() + " ";

				PreparedStatement ps2 = connection.prepareStatement(sql1);
				ResultSet rs2 = ps2.executeQuery();

				while (rs2.next()) {

					// priscription.setId(rs2.getInt(1));

					priscription.setDos1(rs2.getBoolean(2));
					priscription.setDos2(rs2.getBoolean(3));
					priscription.setDos3(rs2.getBoolean(4));
					priscription.setDos4(rs2.getBoolean(5));
					priscription.setDos5(rs2.getBoolean(6));
					priscription.setDos6(rs2.getBoolean(7));
					priscription.setDos7(rs2.getBoolean(8));
					priscription.setDos8(rs2.getBoolean(9));
					priscription.setDos9(rs2.getBoolean(10));
					priscription.setDos10(rs2.getBoolean(11));
					priscription.setUserid(rs2.getString(12));

					String values[] = priscription.getDosage().split("-");

					if (values.length == 1) {
						priscription.setDosevalue1(values[0]);
					} else if (values.length == 2) {
						priscription.setDosevalue1(values[0]);
						priscription.setDosevalue2(values[1]);
					} else if (values.length == 3) {
						priscription.setDosevalue1(values[0]);
						priscription.setDosevalue2(values[1]);
						priscription.setDosevalue3(values[2]);

					} else if (values.length == 4) {
						priscription.setDosevalue1(values[0]);
						priscription.setDosevalue2(values[1]);
						priscription.setDosevalue3(values[2]);
						priscription.setDosevalue4(values[3]);
					}

					String dosetime1 = getTimeOfDoseGiven(priscription.getId(), "dos1");
					String dosetime2 = getTimeOfDoseGiven(priscription.getId(), "dos2");
					String dosetime3 = getTimeOfDoseGiven(priscription.getId(), "dos3");
					String dosetime4 = getTimeOfDoseGiven(priscription.getId(), "dos4");
					String dosetime5 = getTimeOfDoseGiven(priscription.getId(), "dos5");

					priscription.setDosetime1(dosetime1);
					priscription.setDosetime2(dosetime2);
					priscription.setDosetime3(dosetime3);
					priscription.setDosetime4(dosetime4);
					priscription.setDosetime5(dosetime5);

				}

				list.add(priscription);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	private String getTimeOfDoseGiven(int priscid, String dosecolum) {
		String res = "";
		try {

			String sql = "select ddatetime from ipd_dosage_given where dosename='" + dosecolum + "' and prisc_id="
					+ priscid + " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				res = rs.getString(1);

				if (res == null) {
					res = "";
				}
				String time = res.split(" ")[1];
				res = time;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return res;
	}

	private String getTimeOfNursingGiven(int priscid, String dosecolum) {
		String res = "";
		try {

			String sql = "select ddatetime from ipd_nursing_given where dosename='" + dosecolum + "' and nursing_id="
					+ priscid + " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				res = rs.getString(1);

				if (res == null) {
					res = "";
				}
				String time = res.split(" ")[1];
				res = time;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return res;
	}

	public ArrayList<Master> getTreatmentSheetNursing(String ipdid, String clientid, String date) {

		ArrayList<Master> list = new ArrayList<Master>();
		try {
			String todate = date + " 23:59:59";

			String query = "SELECT id,clientid,taskname,frequency,days,parentid FROM apm_client_nursing where clientid="
					+ clientid + " and datetime between '" + date + "' and '" + todate + "' ";

			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs1 = ps.executeQuery();

			while (rs1.next()) {

				Master master = new Master();
				master.setId(rs1.getInt(1));
				master.setClientid(rs1.getString(2));
				master.setTaskname(rs1.getString(3));
				master.setFrequency(rs1.getString(4));
				master.setDays(rs1.getInt(5));

				String parentid = rs1.getString(6);

				Master master2 = getParentNursing(parentid);

				master.setIpdid(String.valueOf(ipdid));
				master.setNotes(master2.getNotes());

				String dosecolumn = "";

				if (!master.getFrequency().startsWith("0") && !master.getFrequency().startsWith("1")) {

					master.setDosesize(3);
					master.setDos1(false);
					master.setDos2(false);
					master.setDos3(false);
					master.setDos4(false);
					master.setDos5(false);
					master.setDos6(false);
					master.setDos7(false);
					master.setDos8(false);
					master.setDos9(false);
					master.setDos10(false);
					master.setDosevalue1("1");
					master.setDosevalue2("1");
					master.setDosevalue3("1");
					continue;

				}

				String dosetemp[] = master.getFrequency().split("-");

				for (int i = 1; i <= dosetemp.length; i++) {

					dosecolumn = dosecolumn + "dos" + i + ",";

				}
				String temp[] = dosecolumn.split(",");
				master.setDosesize(temp.length);

				String query1 = "SELECT id,dos1,dos2,dos3,dos4,dos5,dos6,dos7,dos8,dos9,dos10,userid FROM ipd_nursing_reminder where nursingid="
						+ master.getId() + " ";

				PreparedStatement ps2 = connection.prepareStatement(query1);
				ResultSet rs2 = ps2.executeQuery();

				while (rs2.next()) {

					// master.setId(rs2.getInt(1));
					master.setDos1(rs2.getBoolean(2));
					master.setDos2(rs2.getBoolean(3));
					master.setDos3(rs2.getBoolean(4));
					master.setDos4(rs2.getBoolean(5));
					master.setDos5(rs2.getBoolean(6));
					master.setDos6(rs2.getBoolean(7));
					master.setDos7(rs2.getBoolean(8));
					master.setDos8(rs2.getBoolean(9));
					master.setDos9(rs2.getBoolean(10));
					master.setDos10(rs2.getBoolean(11));
					master.setUserid(rs2.getString(12));

					String values[] = master.getFrequency().split("-");

					if (values.length <= 3) {

						master.setDosevalue1(values[0]);
						master.setDosevalue2(values[1]);
						master.setDosevalue3(values[2]);

					} else if (values.length == 4) {
						master.setDosevalue1(values[0]);
						master.setDosevalue2(values[1]);
						master.setDosevalue3(values[2]);
						master.setDosevalue4(values[3]);
					}
					String dosetime1 = getTimeOfNursingGiven(master.getId(), "dos1");
					String dosetime2 = getTimeOfNursingGiven(master.getId(), "dos2");
					String dosetime3 = getTimeOfNursingGiven(master.getId(), "dos3");
					String dosetime4 = getTimeOfNursingGiven(master.getId(), "dos4");
					String dosetime5 = getTimeOfNursingGiven(master.getId(), "dos5");

					master.setDosetime1(dosetime1);
					master.setDosetime2(dosetime2);
					master.setDosetime3(dosetime3);
					master.setDosetime4(dosetime4);
					master.setDosetime5(dosetime5);

				}

				list.add(master);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	public int saveDischargeCheckList(int tretmentEpisodeId, String clientid) {
		int res = 0;
		try {
			ArrayList<Bed> arrayList = getDischargeCheckList();
			for (Bed bed : arrayList) {
				String sql = "insert into dischargeckldata (clientid, dis_stepsid, dis_cklid, treatmentid) values (?,?,?,?)";
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setString(1, clientid);
				ps.setString(2, bed.getDis_stepid());
				ps.setString(3, "" + bed.getId());
				ps.setString(4, "" + tretmentEpisodeId);
				res = ps.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<Bed> getDischargeCheckList() {
		ArrayList<Bed> arrayList = new ArrayList<Bed>();
		try {
			String sql = "select id, dis_stepid, dis_checklist from discharge_checklist_master";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Bed bed = new Bed();
				bed.setId(rs.getInt(1));
				bed.setDis_stepid(rs.getString(2));
				bed.setDis_checklistname(rs.getString(3));
				arrayList.add(bed);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public ArrayList<Bed> getDischargeChecklistData(String treatmentepisodeid, String clientid, String disstepid) {
		ArrayList<Bed> arrayList = new ArrayList<Bed>();
		try {
			// String sql ="select id, clientid, ipdid, dis_stepsid, dis_cklid,
			// treatmentid, executed, userid, userdatetime, remark, status from
			// dischargeckldata where dis_stepsid='"+disstepid+"' ";
			StringBuilder builder = new StringBuilder();
			builder.append(
					"select id, clientid, ipdid, dis_stepsid, dis_cklid, treatmentid, executed, userid, userdatetime, remark, status from dischargeckldata ");
			builder.append("where treatmentid='" + treatmentepisodeid + "' ");
			if (disstepid != null) {
				builder.append("and dis_stepsid='" + disstepid + "' ");
			}
			PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Bed bed = new Bed();
				bed.setId(rs.getInt(1));
				bed.setClientid(rs.getString(2));
				bed.setDis_stepid(rs.getString(4));
				bed.setDischecklistid(rs.getString(5));
				bed.setTreatmentepisodeid(rs.getString(6));
				bed.setIsexecuted(rs.getString(7));
				bed.setUserid(rs.getString(8));
				bed.setDatetime(rs.getString(9));
				bed.setRemark(rs.getString(10));
				bed.setStatus(rs.getString(11));
				bed.setDis_checklistname(getDisCheckListName(rs.getString(5)));
				arrayList.add(bed);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public String getDisCheckListName(String string) {
		String name = "";
		try {
			String sql = "select dis_checklist from discharge_checklist_master where id='" + string + "'";
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

	public int updateCheckListStatus(String disstepid, String dischecklistid, String treatmentepisodeid, String userid,
			String todate) {
		int result = 0;
		try {
			String sql = "update dischargeckldata set executed='1',userid='" + userid + "', userdatetime='" + todate
					+ "' where treatmentid='" + treatmentepisodeid + "' and dis_cklid='" + dischecklistid + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateCheckListStatusById(String disdataid, String userid, String todate, String status) {
		int result = 0;
		try {
			String sql = "update dischargeckldata set executed='" + status + "',userid='" + userid + "', userdatetime='"
					+ todate + "' where id='" + disdataid + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean isDisCheckListAlreadyPresent(String disdataid) {
		boolean flag = false;
		try {
			String sql = "select id from dischargeckldata where id='" + disdataid + "' and userid is null";
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

	public int updateCheckListStatusModicfy(String disdataid, String userid, String todate, String status,
			String ische) {
		int result = 0;
		try {
			String sql1 = "select editdata from dischargeckldata where id ='" + disdataid + "'";
			PreparedStatement statement = connection.prepareStatement(sql1);
			ResultSet rs = statement.executeQuery();
			String data = "";
			while (rs.next()) {
				data = rs.getString(1);
			}
			if (data == null) {
				data = "Editby" + userid + " " + todate + " " + " " + ische + ".";
			} else if (data.equals("")) {
				data = "Editby" + userid + " " + todate + " " + " " + ische + ".";
			} else {
				data = data + "Editby" + userid + " " + todate + " " + " " + ische + ".";
			}
			String sql = "update dischargeckldata set executed='" + status + "',editby='" + userid + "', editdatetime='"
					+ todate + "',editdata='" + data + "' where id='" + disdataid + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getClientVitalData(String vitalname, String clientid) {

		String res = "";
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append(
					"select finding from his_vital inner join his_vital_master on his_vital_master.id=his_vital.vital_master_id ");
			buffer.append("where his_vital_master.name='" + vitalname + "' and clientid=" + clientid
					+ " order by his_vital.id desc limit 0,1 ");
			PreparedStatement ps = connection.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				res = rs.getString(1);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return res;
	}

	public String getAlterNateDose(String dosage) {

		String res = "";
		try {
			String sql = "select alternate from apm_medicine_dosage where name='" + dosage + "'";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				res = rs.getString(1);
				if (res == null) {
					res = "";
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return res;
	}

	public boolean getIsDisCheckListStatus(String dischecklistid, String treatmentepisodeid) {
		boolean flag = false;
		try {
			String sql = "select status,id from dischargeckldata where dis_cklid='" + dischecklistid
					+ "' and treatmentid='" + treatmentepisodeid + "' ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				if (rs.getString(1).equals("1")) {
					flag = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public int updateCheckListStatusSystemModify(String disstepid, String dischecklistid, String treatmentepisodeid,
			String userid, String todate, String ische) {
		int result = 0;
		try {
			String sql1 = "select editdata from dischargeckldata where dis_stepsid='" + disstepid + "' and dis_cklid='"
					+ dischecklistid + "' and treatmentid='" + treatmentepisodeid + "'";
			PreparedStatement statement = connection.prepareStatement(sql1);
			ResultSet rs = statement.executeQuery();
			String data = "";
			while (rs.next()) {
				data = rs.getString(1);
			}
			if (data == null) {
				data = "Editby" + userid + " " + todate + " " + " " + ische + ".";
			} else if (data.equals("")) {
				data = "Editby" + userid + " " + todate + " " + " " + ische + ".";
			} else {
				data = data + "Editby" + userid + " " + todate + " " + " " + ische + ".";
			}
			String sql = "update dischargeckldata set editby='" + userid + "', editdatetime='" + todate + "',editdata='"
					+ data + "' where dis_stepsid='" + disstepid + "' and dis_cklid='" + dischecklistid
					+ "' and treatmentid='" + treatmentepisodeid + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getDisDataId(String dischecklistid, String treatmentepisodeid) {
		int flag = 0;
		try {
			String sql = "select status,id from dischargeckldata where dis_cklid='" + dischecklistid
					+ "' and treatmentid='" + treatmentepisodeid + "' ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				flag = rs.getInt(2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public int updateDischargeCKLIndi(int disdataid1) {
		int result = 0;
		try {
			String sql = "update dischargeckldata set status='1' where id='" + disdataid1 + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getTotalDischargePatientCount(String fromdate, String todate) {

		int res = 0;

		StringBuffer sql = new StringBuffer();
		try {
			sql.append("SELECT count(*) FROM apm_ipd_bed inner join ipd_addmission_form on ");
			sql.append("ipd_addmission_form.bedid = apm_ipd_bed.id inner join apm_treatment_episode on ");
			sql.append("apm_treatment_episode.id = ipd_addmission_form.treatmentepisodeid ");
			// sql.append("where dis_initiate_status = 1 and treatmentstatus = 0
			// ");
			sql.append("where dis_initiate_status = 1 ");
			if (fromdate != null) {
				sql.append(
						"and apm_treatment_episode.dis_initiate_time between '" + fromdate + "' and '" + todate + "' ");
			}

			PreparedStatement ps = connection.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				res = rs.getInt(1);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return res;
	}

	public String getBedIdFromLog(String selectedid) {

		String res = "";
		try {
			String sql = "SELECT bedid from ipd_bed_change_log where admissionid=" + selectedid + " and bedid>0 ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				res = rs.getString(1);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return res;
	}

	public ArrayList<Master> getVitalMasterByCategory(String vital_type) {

		ArrayList<Master> list = new ArrayList<Master>();
		try {

			String sql = "SELECT id, name, department, min_value_m, max_value_m, min_value_f, max_value_f,imagename,unit from his_vital_master where vital_type="
					+ vital_type + " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				master.setDepartment(rs.getString(3));
				master.setMin_value_m(rs.getString(4));
				master.setMax_value_m(rs.getString(5));
				master.setMin_value_f(rs.getString(6));
				master.setMax_value_f(rs.getString(7));
				master.setImagename(rs.getString(8));
				master.setUnit(rs.getString(9));
				list.add(master);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<String> getDailyCareTimeList(String ipdid, String ipdclietid, String date, String type) {

		ArrayList<String> list = new ArrayList<String>();
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select time from his_vital inner join his_vital_master ");
			buffer.append("on his_vital.vital_master_id= his_vital_master.id where ");
			buffer.append("clientid=" + ipdclietid + " ");
			if (!ipdid.equals("0")) {
				buffer.append("and ipdid=" + ipdid + " ");
			}
			buffer.append("and date='" + date + "' and his_vital_master.vital_type=" + type + "  group by time ");

			PreparedStatement ps = connection.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(rs.getString(1));
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return list;

	}

	public ArrayList<Master> getDailyCareDataListandValues(String clientid, String ipdid, String date, String type) {
		ArrayList<Master> list = new ArrayList<Master>();

		try {

			String sql = "SELECT id, name, department, min_value_m, max_value_m, min_value_f, max_value_f,imagename,unit from his_vital_master where vital_type="
					+ type + " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				String vital_master_id = rs.getString(1);
				master.setDepartment(rs.getString(3));
				master.setMin_value_m(rs.getString(4));
				master.setMax_value_m(rs.getString(5));
				master.setMin_value_f(rs.getString(6));
				master.setMax_value_f(rs.getString(7));
				master.setImagename(rs.getString(8));
				master.setUnit(rs.getString(9));
				ArrayList<Master> vitalDataList = getDailyCareDataList(vital_master_id, clientid, ipdid, date, type);
				master.setVitalDataList(vitalDataList);
				list.add(master);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return list;
	}

	private ArrayList<Master> getDailyCareDataList(String vital_master_id, String ipdclientid, String ipdid,
			String date, String type) {

		ArrayList<Master> list = new ArrayList<Master>();
		ArrayList<String> timeList = getDailyCareTimeList(ipdid, ipdclientid, date, type);
		try {

			for (String time : timeList) {
				Master master = new Master();
				String keyname = vital_master_id + "t" + time;
				master.setKeyname(keyname);

				String finding = getFindingofVital(ipdclientid, ipdid, vital_master_id, time, date);
				master.setFinding(finding);
				list.add(master);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	public boolean checkCheckListAvailable(String treatmentepisodeid) {
		boolean flag = false;
		try {
			String sql = "select id from dischargeckldata where treatmentid='" + treatmentepisodeid + "'";
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

	public boolean checkHasInvoiceCreated(String ipdid, String clientid) {

		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append("select apm_invoice.id from apm_invoice inner join apm_invoice_assesments on ");
			buffer.append("apm_invoice_assesments.invoiceid = apm_invoice.id where apm_invoice_assesments.ipdid="
					+ ipdid + " and  ");
			buffer.append("apm_invoice.clientid=" + clientid + " and apm_invoice.chargetype='Submit' ");
			buffer.append(
					"and (apm_invoice_assesments.appointmentid=0 || apm_invoice_assesments.appointmentid is null) ");
			PreparedStatement ps = connection.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				return true;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return false;
	}

	public int cancelInvoice(String ipdid, String clientid) {

		int res = 0;
		try {
			String sql = "delete from apm_invoice where ipdid=" + ipdid + " and clientid=" + clientid + " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			res = ps.executeUpdate();

			String sql1 = "delete from apm_invoice_assesments where ipdid=" + ipdid + " and clientId=" + clientid + " ";
			PreparedStatement ps1 = connection.prepareStatement(sql1);

			res = ps1.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	public int cancelBedStatus(String ipdid, String cancelNotes, String userid) {

		int res = 0;
		try {

			String sql = "update ipd_addmission_form set bedid=0,cancel=1,cancelnote=?,canceluser=? where id=" + ipdid
					+ " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, cancelNotes);
			ps.setString(2, userid);
			res = ps.executeUpdate();

			BedDao bedDao = new JDBCBedDao(connection);
			bedDao.updateAdmissionFormInParent(DateTimeUtils.convertToInteger(ipdid));
		} catch (Exception e) {

			e.printStackTrace();
		}

		return res;
	}

	public ArrayList<Client> getAllDiagnosis(String search) {
		PreparedStatement preparedStatement = null;
		ArrayList<Client> list = new ArrayList<Client>();
		// ClientDAO clientDAO = new JDBCClientDAO(connection);
		try {
			String sql = "select id,name,diseasecode,icdcode from apm_diagnosis where name like('%" + search
					+ "%') and isdeleted!=1 order by name limit 5";
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Client client = new Client();
				client.setId(rs.getInt(1));
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
				client.setTreatmentType(condition);
				list.add(client);
			}
			/*
			 * int maxId = clientDAO.getMaxIdOfCondition(); Client c1= new Client();
			 * c1.setId(maxId+1); c1.setTreatmentType("ADD NEW"); list.add(c1);
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int savenursenoteajax(String nursemsg, String ipdclientid, String ipdaddmissionid, String userid,
			String todate) {
		int res = 0;
		try {
			String sql = "insert into his_nursing_notes (clientid, ipdid, userid, datetime, notes) values (?,?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, ipdclientid);
			ps.setString(2, ipdaddmissionid);
			ps.setString(3, userid);
			ps.setString(4, todate);
			ps.setString(5, nursemsg);
			res = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<Bed> getAllNurseNotes(String ipdaddmissionid) {
		ArrayList<Bed> arrayList = new ArrayList<Bed>();
		try {
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			String sql = "select id,clientid, ipdid, userid, datetime, notes from his_nursing_notes where ipdid='"
					+ ipdaddmissionid + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Bed bed = new Bed();
				bed.setId(rs.getInt(1));
				bed.setClientid(rs.getString(2));
				bed.setIpdid(rs.getString(3));
				bed.setUserid(rs.getString(4));
				bed.setDatetime(rs.getString(5));
				bed.setNursenotes(rs.getString(6));
				String name = userProfileDAO.getUserNameFromUserid(rs.getString(4));
				bed.setClientname(name);
				String[] data = rs.getString(5).split(" ");
				String date = DateTimeUtils.getCommencingDate1(data[0]) + " " + data[1];
				bed.setDatetime(date);
				arrayList.add(bed);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public int saveIPDTemplate(String tempname, String ipdtempid, String department, String msg) {
		int res = 0;
		try {
			String sql = "insert into ipd_template (template_nameid, text, name,speciality,department) values (?,?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, ipdtempid);
			ps.setString(2, msg);
			ps.setString(3, tempname);
			ps.setString(4, department);
			ps.setString(5, "1");
			res = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int updateAdmDataFromDisc(Bed bed3) {
		int res = 0;
		try {

			String sql = "update ipd_addmission_form set addmissionfor=?,chiefcomplains=?,presentillness=?,allergies=?,suggestedtrtment=?,early_investigation=?,surgicalnote=?, birthhist=?, diethist=?, developmenthist=?, emmunizationhist=?,head_circumference=?, mid_arm_circumference=?,length=?,wt_addmission =?, wt_discharge=?,pasthistory=?, personalhist=?, familyhist=?, onexamination=? ,suggestedtrtment=? ,early_investigation=?, surgicalnote=?,kunal_manual_medicine_text=?,kunal_final_diagnosis_text=?,gstureage=?,wtonbirth=?,maternal_history=?,perinatal_history=?,reasonlamadama=?,secndryconsult=?  where id="
					+ bed3.getIpdid() + " ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, bed3.getAddmissionfor());
			preparedStatement.setString(2, bed3.getChiefcomplains());
			preparedStatement.setString(3, bed3.getPresentillness());
			preparedStatement.setString(4, bed3.getAlergies());
			preparedStatement.setString(5, bed3.getSuggestedtrtment());
			preparedStatement.setString(6, bed3.getEarlierinvest());
			preparedStatement.setString(7, bed3.getSurgicalnotes());
			preparedStatement.setString(8, bed3.getBirthhist());
			preparedStatement.setString(9, bed3.getDiethist());
			preparedStatement.setString(10, bed3.getDevelopmenthist());
			preparedStatement.setString(11, bed3.getEmmunizationhist());
			preparedStatement.setString(12, bed3.getHeadcircumference());
			preparedStatement.setString(13, bed3.getMidarmcircumference());
			preparedStatement.setString(14, bed3.getLength());
			preparedStatement.setString(15, bed3.getWtaddmission());
			preparedStatement.setString(16, bed3.getWtdischarge());
			preparedStatement.setString(17, bed3.getPasthistory());
			preparedStatement.setString(18, bed3.getPersonalhist());
			preparedStatement.setString(19, bed3.getFamily_history());
			preparedStatement.setString(20, bed3.getOnexamination());
			preparedStatement.setString(21, bed3.getSuggestedtrtment());
			preparedStatement.setString(22, bed3.getEarlierinvest());
			preparedStatement.setString(23, bed3.getSurgicalnotes());
			preparedStatement.setString(24, bed3.getKunal_manual_medicine_text());
			preparedStatement.setString(25, bed3.getKunal_final_diagnosis_text());
			preparedStatement.setString(26, bed3.getGstureage());
			preparedStatement.setString(27, bed3.getWtonbirth());
			preparedStatement.setString(28, bed3.getMaternal_history());
			preparedStatement.setString(29, bed3.getPerinatal_history());
			preparedStatement.setString(30, DateTimeUtils.removeBreaks(bed3.getReasonlamadama()));
			preparedStatement.setString(31, bed3.getSecndryconsult());

			res = preparedStatement.executeUpdate();

			BedDao bedDao = new JDBCBedDao(connection);
			bedDao.updateAdmissionFormInParent(DateTimeUtils.convertToInteger(bed3.getIpdid()));

		} catch (Exception e) {

			e.printStackTrace();
		}

		return res;
	}

	public int removeMedicineDischarge(String id) {

		int res = 0;
		try {

			String sql = "delete from apm_client_priscription where id=" + id + " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			res = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return res;
	}

	public int saveIpdGynicDetails(Bed bed) {

		int result = 0;
		try {

			StringBuffer buffer = new StringBuffer();
			buffer.append(
					"insert into ipd_gynic_form (ipdid, clientid, practitionerid, commencing, userid, lastmodified, lmp, edd, usg, gravida, para, live, abortion, mtp, still_born, death, high_risk_factor,  ");
			buffer.append(
					"visit_reason_ids, age_at_minarche, llmp, pmc, cycle_length, duration_flow, amount_flow, dysmenorrehea, dyspareunia, ho_passing_clots, white_discharge_itching, intercouse_frequency, galactorrea, ho_contraception, rubella_vaccine, menopause, nulligravida, current_pregnant, iud, live_boys, live_girls, still_birth, abortions, death_child, parity_abortion_notes, earlier_investigation, pt_history, pt_history_notes, family_history, family_history_notes, p, l, a, d, ");
			buffer.append("surgical_ho, date1, date2, date3, date4,hb1, hb2, hb3, hb4, ");
			buffer.append("fbs1, fbs2, fbs3, fbs4, dpbs1, dpbs2, dpbs3, dpbs4, ");
			buffer.append("urm1, urm2, urm3, urm4, tsh1, tsh2, tsh3, tsh4, ");
			buffer.append("ict1, ict2, ict3, ict4, gtt1, gtt2, gtt3, gtt4, ");
			buffer.append("hvm, hbsag, vdrl, hb_srecta, hbac, duet_markers, triple, Quradraple_maicers, ");
			buffer.append("ivf_date, down_regulation, ovarian_stimulation, os_dosage, sperm_quality, et_day, ");
			buffer.append(
					"oocytes_obtained, oocytes_quality, embroyos_grade, embroyos_transfered, embroyos_description, ");
			buffer.append(
					"freezing, transfer_process, betahcgcm, ivf_remark, do_family_history, ho_fertility_family, ho_genetic_family, ho_premature_family, ");
			buffer.append(
					"age_of_menarche, age_of_menarche_notes, dysmenorrhoe, dysmenorrhoe_notes, flow, flow_notes, sleep_disruption_bleeding, sleep_disruption_bleeding_notes, blachouts, blachouts_notes, ");
			buffer.append("alchohol, drugs, other_medication, tobaco, smoking, tobaco_notes, ");

			buffer.append("anormaly_scan_11week, cervical_length_11week, double_marker_11week, ");
			buffer.append("all_investigation_16week, sikling_16week, triple_marker_16week, abstinence_1visit,");
			buffer.append("barrier_contra_1visit, bed_rest_1visit, book_1visit, csv_1visit, dispi_test_1visit, ");
			buffer.append(
					"drug_reaction_1visit, hcg_1visit, heparin_1visit, oral_hygeine_1visit, other_test_1visit, physio_diet_1visit, ");
			buffer.append(
					"rubelle_status_1visit, smart_doc_1visit, stream_cell_1visit, vaginities_1visit, animally_scan_20week, ");
			buffer.append(
					"fetal_eco_20week, anti_d_24week, dipsi_24week, itc_24week, investigation_sos_30week, steroids_30week, ");
			buffer.append(
					"vaginities_treatment_30week, breast_preparation_34week, color_doppler_34week, labour_counselling_34week, nst_34week, vaginities_treatment_34week, ");

			buffer.append("nst_date1, nst_date2, nst_date3, nst_date4, nst_date5, nst_date6, ");
			buffer.append("nst_time1, nst_time2, nst_time3, nst_time4, nst_time5, nst_time6, ");
			buffer.append(
					"nst_indication1, nst_indication2, nst_indication3, nst_indication4, nst_indication5, nst_indication6, ");
			buffer.append("nst_duration1, nst_duration2, nst_duration3, nst_duration4, nst_duration5, nst_duration6, ");
			buffer.append(
					"nst_interpretation1, nst_interpretation2, nst_interpretation3, nst_interpretation4, nst_interpretation5, nst_interpretation6, ");
			buffer.append(
					"nst_intervention1, nst_intervention2, nst_intervention3, nst_intervention4, nst_intervention5, nst_intervention6, ");
			buffer.append("tt_dose1, tt_dose2, influenza_vaccine, usgdate1, usgdate2, usgdate3, usgdate4, ");
			buffer.append(
					"amenorrhea1, amenorrhea2, amenorrhea3, amenorrhea4, presentation1, presentation2, presentation3, presentation4,");
			buffer.append("bpdgs1, bpdgs2, bpdgs3, bpdgs4, hc1, hc2, hc3, hc4, ");
			buffer.append("ac1, ac2, ac3, ac4, flcrl1, flcrl2, flcrl3, flcrl4, ");
			buffer.append("ga_usg1, ga_usg2, ga_usg3, ga_usg4, liquor1, liquor2, liquor3, liquor4, ");
			buffer.append(
					"placenta1, placenta2, placenta3, placenta4, foetal_weight1, foetal_weight2, foetal_weight3, foetal_weight4, ");
			buffer.append(
					"cervical_length1, cervical_length2, cervical_length3, cervical_length4, nt_scan, anomaly_scan, colour_doppler_scan, ");

			buffer.append(
					"gen_condition, temp, pallor, pedal_edema, pulse, bmi, height,weight, sys_bp, dia_bp, wall_edema, fundal_height, ");
			buffer.append(
					"cephalic, breech, baley_size, transverse_fhs, liquor, uterine_contractions, ps_cervix, ps_vagine, ");
			buffer.append(
					"ps_vault, pv_trim, pv_unettacced, pv_ant, pv_os, pv_membranes, pv_tubular, pv_just_ettacced, pv_mid_posits, pv_soft, pv_ettacced, pv_post, ");

			buffer.append("ps_fhs,pv_membrane,pv_station,pv_liquor,pv_pelvis,pv_position,beats_min, ");
			// Adarsh

			buffer.append("pmp, diagnosisgyn, le_vulva, le_vagina, pa_gynic, plan, finaldiagnosis, ");
			buffer.append("priscription, pv_uterus, pv_uterus_size, pv_bl_fomices, pv_mobility, formtype ");

//			buffer.append("hrf_gdm, hrf_pih , hrf_boh , hrf_hypothyroid , hrf_hyperthyroid , hrf_prev_lscs , hrf_rh_negative , hrf_other, ");
//			buffer.append("fundalheight_weeks , pa_soft, external_ballotment, effaced_text, pv_dialated, pv_dialatedcm, ");
//			buffer.append("g, dating_usg_date, weeks_dating_scan, pog_dating_scan, ");
//			buffer.append("usg_report, additonal_notes, diagonosis, treatment_advice, investigation_advice ");

			buffer.append(") ");
			buffer.append(" values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
			buffer.append(
					"?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
			buffer.append("?, ?, ?, ?, ?,?, ?, ?, ?, ");
			buffer.append("?, ?, ?, ?, ?, ?, ?, ?, ");
			buffer.append("?, ?, ?, ?, ?, ?, ?, ?, ");
			buffer.append("?, ?, ?, ?, ?, ?, ?, ?, ");
			buffer.append("?, ?, ?, ?, ?, ?, ?, ?,  ");
			buffer.append("?, ?, ?, ?, ?, ?, ");
			buffer.append("?, ?, ?, ?, ?, ");
			buffer.append("?, ?, ?, ?, ?, ?, ?, ?, ");
			buffer.append("?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
			buffer.append("?, ?, ?, ?, ?, ?, ");
			buffer.append("?, ?, ?, ");
			buffer.append("?, ?, ?, ?,");
			buffer.append("?, ?, ?, ?, ?, ");
			buffer.append("?, ?, ?, ?, ?, ?, ");
			buffer.append("?, ?, ?, ?, ?, ");
			buffer.append("?, ?, ?, ?, ?, ?, ");
			buffer.append("?, ?, ?, ?, ?, ?, ");
			buffer.append("?, ?, ?, ?, ?, ?, ");
			buffer.append("?, ?, ?, ?, ?, ?, ");
			buffer.append("?, ?, ?, ?, ?, ?, ");
			buffer.append("?, ?, ?, ?, ?, ?, ");
			buffer.append("?, ?, ?, ?, ?, ?, ");
			buffer.append("?, ?, ?, ?, ?, ?, ");
			buffer.append("?, ?, ?, ?, ?, ?, ?, ");
			buffer.append("?, ?, ?, ?, ?, ?, ?, ?,");
			buffer.append("?, ?, ?, ?, ?, ?, ?, ?, ");
			buffer.append("?, ?, ?, ?, ?, ?, ?, ?, ");
			buffer.append("?, ?, ?, ?, ?, ?, ?, ?, ");
			buffer.append("?, ?, ?, ?, ?, ?, ?, ?, ");
			buffer.append("?, ?, ?, ?, ?, ?, ?, ");
			buffer.append("?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ");
			buffer.append("?, ?, ?, ?, ?, ?, ?, ?, ");
			buffer.append("?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
			// Adarsh
			buffer.append("?, ?, ?, ?, ?, ?, ?, ");
			buffer.append("?, ?, ?, ?, ?, ?, ?, ");
			buffer.append("?, ?, ?, ?, ?,? ");

//			buffer.append("?,?,?,?,?,?,?,?,?,?,");
//			buffer.append("?,?,?,?,?,?,?,?,?,?,");
//			buffer.append("?,?,?");

			buffer.append(") ");

			PreparedStatement ps = connection.prepareStatement(buffer.toString());
			ps.setString(1, bed.getIpdid());
			ps.setString(2, bed.getClientid());
			ps.setString(3, bed.getPractitionerid());
			ps.setString(4, bed.getCommencing());
			ps.setString(5, bed.getUserid());
			ps.setString(6, bed.getLastmodified());
			ps.setString(7, bed.getLmp());
			ps.setString(8, bed.getEdd());
			ps.setString(9, bed.getUsg());
			ps.setString(10, bed.getGravida());
			ps.setString(11, bed.getPara());
			ps.setString(12, bed.getLive());
			ps.setString(13, bed.getAbortion());
			ps.setString(14, bed.getMtp());
			ps.setString(15, bed.getStill_born());
			ps.setString(16, bed.getDeath());
			ps.setString(17, bed.getHigh_risk_factor());
			ps.setString(18, bed.getVisit_reason_ids());
			ps.setString(19, bed.getAge_menarche());
			ps.setString(20, bed.getLlmp());
			ps.setString(21, bed.getPmc());
			ps.setString(22, bed.getCycle_length());
			ps.setString(23, bed.getDuration_flow());
			ps.setString(24, bed.getAmount_flow());
			ps.setString(25, bed.getDysmenorrhea());
			ps.setString(26, bed.getDyspareunia());
			ps.setString(27, bed.getHopassing_clots());
			ps.setString(28, bed.getWhite_disc_itching());
			ps.setString(29, bed.getIntercourse_freq());
			ps.setString(30, bed.getGalactorrea());
			ps.setString(31, bed.getHo_contraception());
			ps.setString(32, bed.getRubella_vaccine());
			ps.setString(33, bed.getMenopause());
			ps.setString(34, bed.getNulligravida());
			ps.setString(35, bed.getCurrent_pregnent());
			ps.setString(36, bed.getIud());
			ps.setString(37, bed.getLive_boys());
			ps.setString(38, bed.getLive_girls());
			ps.setString(39, bed.getStillbirths());
			ps.setString(40, bed.getAbortions());
			ps.setString(41, bed.getDeath_children());
			ps.setString(42, bed.getParity_abortion_notes());
			ps.setString(43, bed.getEarlierinvest());
			ps.setString(44, bed.getPasthistory());
			ps.setString(45, bed.getPt_history_notes()); // past_history_notes
			ps.setString(46, bed.getFamilyhist());
			ps.setString(47, bed.getFamily_history_notes()); // family_history_notes
			ps.setString(48, bed.getP());
			ps.setString(49, bed.getL());
			ps.setString(50, bed.getA());
			ps.setString(51, bed.getD());

			// surgical_ho, date1, date2, date3, date4,hb1, hb2, hb3, hb4,
			ps.setString(52, bed.getSurgical_ho());
			ps.setString(53, bed.getDate1());
			ps.setString(54, bed.getDate2());
			ps.setString(55, bed.getDate3());
			ps.setString(56, bed.getDate4());
			ps.setString(57, bed.getHb1());
			ps.setString(58, bed.getHb2());
			ps.setString(59, bed.getHb3());
			ps.setString(60, bed.getHb4());

			// fbs1, fbs2, fbs3, fbs4, dpbs1, dpbs2, dpbs3, dpbs4,

			ps.setString(61, bed.getFbs1());
			ps.setString(62, bed.getFbs2());
			ps.setString(63, bed.getFbs3());
			ps.setString(64, bed.getFbs4());
			ps.setString(65, bed.getDpbs1());
			ps.setString(66, bed.getDpbs2());
			ps.setString(67, bed.getDpbs3());
			ps.setString(68, bed.getDpbs4());

			// urm1, urm2, urm3, urm4, tsh1, tsh2, tsh3, tsh4,
			ps.setString(69, bed.getUrm1());
			ps.setString(70, bed.getUrm2());
			ps.setString(71, bed.getUrm3());
			ps.setString(72, bed.getUrm4());
			ps.setString(73, bed.getTsh1());
			ps.setString(74, bed.getTsh2());
			ps.setString(75, bed.getTsh3());
			ps.setString(76, bed.getTsh4());

			// ict1, ict2, ict3, ict4, gtt1, gtt2, gtt3, gtt4
			ps.setString(77, bed.getIct1());
			ps.setString(78, bed.getIct2());
			ps.setString(79, bed.getIct3());
			ps.setString(80, bed.getIct4());
			ps.setString(81, bed.getGtt1());
			ps.setString(82, bed.getGtt2());
			ps.setString(83, bed.getGtt3());
			ps.setString(84, bed.getGtt4());

			// hvm, hbsag, vdrl, hb_srecta, hbac, duet_markers, triple,
			// Quradraple Maicers
			ps.setString(85, bed.getHv_1m());
			ps.setString(86, bed.getHbs_ag());
			ps.setString(87, bed.getVdrl());
			ps.setString(88, bed.getHb_srecta());
			ps.setString(89, bed.getHb_ac());
			ps.setString(90, bed.getDuet_markess());
			ps.setString(91, bed.getTriple());
			ps.setString(92, bed.getQuadrple_maicers());

			// ivf_date, down_regulation, ovarian_stimulation, os_dosage,
			// sperm_quality, et_day

			ps.setString(93, bed.getIvf_date());
			ps.setString(94, bed.getDown_regulation());
			ps.setString(95, bed.getOvarian_stimulation());
			ps.setString(96, bed.getOs_dosage());
			ps.setString(97, bed.getSperm_quality());
			ps.setString(98, bed.getEt_day());

			// oocytes_obtained, oocytes_quality, embroyos_grade,
			// embroyos_transfered, embroyos_description,
			ps.setString(99, bed.getOocytes_obtained());
			ps.setString(100, bed.getOocytes_quality());
			ps.setString(101, bed.getEmbroyos_grade());
			ps.setString(102, bed.getEmbroyos_transfered());
			ps.setString(103, bed.getEmbroyos_description());

			// freezing, transfer_process, betahcgcm, ivf_remark,
			// do_family_history, ho_fertility_family, ho_genetic_family,
			// ho_premature_family,
			ps.setString(104, bed.getFreezing());
			ps.setString(105, bed.getTransfer_process());
			ps.setString(106, bed.getBetahcgcm());
			ps.setString(107, bed.getIvf_remark());
			ps.setString(108, bed.getDo_family_history());
			ps.setString(109, bed.getHo_fertility_family());
			ps.setString(110, bed.getHo_genetic_family());
			ps.setString(111, bed.getHo_premature_family());

			// age_of_menarche, age_of_menarche_notes, dysmenorrhoe,
			// dysmenorrhoe_notes, flow, flow_notes, sleep_disruption_bleeding,
			// sleep_disruption_bleeding_notes, blachouts, blachouts_notes;
			ps.setString(112, bed.getAge_of_menarche());
			ps.setString(113, bed.getAge_of_menarche_notes());
			ps.setString(114, bed.getDysmenorrhoe());
			ps.setString(115, bed.getDysmenorrhoe_notes());
			ps.setString(116, bed.getFlow());
			ps.setString(117, bed.getFlow_notes());
			ps.setString(118, bed.getSleep_disruption_bleeding());
			ps.setString(119, bed.getSleep_disruption_bleeding_notes());
			ps.setString(120, bed.getBlachouts());
			ps.setString(121, bed.getBlachouts_notes());

			// alchohol, drugs, other_medication, tobaco, smoking, tobaco_notes
			ps.setString(122, bed.getAlcohal());
			ps.setString(123, bed.getDrugs());
			ps.setString(124, bed.getOther_medication());
			ps.setString(125, bed.getTobaco());
			ps.setString(126, bed.getSmoking());
			ps.setString(127, bed.getTobaconotes());

			// buffer.append("anormaly_scan_11week, cervical_length_11week,
			// double_marker_11week, ");
			ps.setString(128, bed.getAnormaly_scan_11week());
			ps.setString(129, bed.getCervical_length_11week());
			ps.setString(130, bed.getDouble_marker_11week());

			// buffer.append("all_investigation_16week, sikling_16week,
			// triple_marker_16week, abstinence_1visit,");
			ps.setString(131, bed.getAll_investigation_16week());
			ps.setString(132, bed.getSikling_16week());
			ps.setString(133, bed.getTriple_marker_16week());
			ps.setString(134, bed.getAbstinence_1visit());

			// buffer.append("barrier_contra_1visit, bed_rest_1visit,
			// book_1visit, csv_1visit, dispi_test_1visit, ");
			ps.setString(135, bed.getBarrier_contra_1visit());
			ps.setString(136, bed.getBed_rest_1visit());
			ps.setString(137, bed.getBook_1visit());
			ps.setString(138, bed.getCsv_1visit());
			ps.setString(139, bed.getDispi_test_1visit());

			// buffer.append("drug_reaction_1visit, hcg_1visit, heparin_1visit,
			// oral_hygeine_1visit, other_test_1visit, physio_diet_1visit, ");
			ps.setString(140, bed.getDrug_reaction_1visit());
			ps.setString(141, bed.getHcg_1visit());
			ps.setString(142, bed.getHeparin_1visit());
			ps.setString(143, bed.getOral_hygeine_1visit());
			ps.setString(144, bed.getOther_test_1visit());
			ps.setString(145, bed.getPhysio_diet_1visit());

			// buffer.append("rubelle_status_1visit, smart_doc_1visit,
			// stream_cell_1visit, vaginities_1visit, animally_scan_20week, ");
			ps.setString(146, bed.getRubelle_status_1visit());
			ps.setString(147, bed.getSmart_doc_1visit());
			ps.setString(148, bed.getStream_cell_1visit());
			ps.setString(149, bed.getVaginities_1visit());
			ps.setString(150, bed.getAnimally_scan_20week());

			// buffer.append("fetal_eco_20week, anti_d_24week, dipsi_24week,
			// itc_24week, investigation_sos_30week, steroids_30week, ");
			ps.setString(151, bed.getFetal_eco_20week());
			ps.setString(152, bed.getAnti_d_24week());
			ps.setString(153, bed.getDipsi_24week());
			ps.setString(154, bed.getItc_24week());
			ps.setString(155, bed.getInvestigation_sos_30week());
			ps.setString(156, bed.getSteroids_30week());

			// buffer.append("vaginities_treatment_30week,
			// breast_preparation_34week, color_doppler_34week,
			// labour_counselling_34week, nst_34week,
			// vaginities_treatment_34week ");
			ps.setString(157, bed.getVaginities_treatment_30week());
			ps.setString(158, bed.getBreast_preparation_34week());
			ps.setString(159, bed.getColor_doppler_34week());
			ps.setString(160, bed.getLabour_counselling_34week());
			ps.setString(161, bed.getNst_34week());
			ps.setString(162, bed.getVaginities_treatment_34week());

			// nst_date1, nst_date2, nst_date3, nst_date4, nst_date5, nst_date6,
			ps.setString(163, bed.getNst_date1());
			ps.setString(164, bed.getNst_date2());
			ps.setString(165, bed.getNst_date3());
			ps.setString(166, bed.getNst_date4());
			ps.setString(167, bed.getNst_date5());
			ps.setString(168, bed.getNst_date6());

			// nst_time1, nst_time2, nst_time3, nst_time4, nst_time5, nst_time6,
			ps.setString(169, bed.getNst_time1());
			ps.setString(170, bed.getNst_time2());
			ps.setString(171, bed.getNst_time3());
			ps.setString(172, bed.getNst_time4());
			ps.setString(173, bed.getNst_time5());
			ps.setString(174, bed.getNst_time6());

			// nst_indication1, nst_indication2, nst_indication3,
			// nst_indication4, nst_indication5, nst_indication6,
			ps.setString(175, bed.getNst_indication1());
			ps.setString(176, bed.getNst_indication2());
			ps.setString(177, bed.getNst_indication3());
			ps.setString(178, bed.getNst_indication4());
			ps.setString(179, bed.getNst_indication5());
			ps.setString(180, bed.getNst_indication6());

			// nst_duration1, nst_duration2, nst_duration3, nst_duration4,
			// nst_duration5, nst_duration6,
			ps.setString(181, bed.getNst_duration1());
			ps.setString(182, bed.getNst_duration2());
			ps.setString(183, bed.getNst_duration3());
			ps.setString(184, bed.getNst_duration4());
			ps.setString(185, bed.getNst_duration5());
			ps.setString(186, bed.getNst_duration6());

			// nst_interpretation1, nst_interpretation2, nst_interpretation3,
			// nst_interpretation4, nst_interpretation5, nst_interpretation6,
			ps.setString(187, bed.getNst_interpretation1());
			ps.setString(188, bed.getNst_interpretation2());
			ps.setString(189, bed.getNst_interpretation3());
			ps.setString(190, bed.getNst_interpretation4());
			ps.setString(191, bed.getNst_interpretation5());
			ps.setString(192, bed.getNst_interpretation6());

			// nst_intervention1, nst_intervention2, nst_intervention3,
			// nst_intervention4, nst_intervention5, nst_intervention6
			ps.setString(193, bed.getNst_intervention1());
			ps.setString(194, bed.getNst_intervention2());
			ps.setString(195, bed.getNst_intervention3());
			ps.setString(196, bed.getNst_intervention4());
			ps.setString(197, bed.getNst_intervention5());
			ps.setString(198, bed.getNst_intervention6());

			// tt_dose1, tt_dose2, influenza_vaccine, usgdate1, usgdate2,
			// usgdate3, usgdate4,
			ps.setString(199, bed.getTt_dose1());
			ps.setString(200, bed.getTt_dose2());
			ps.setString(201, bed.getInfluenza_vaccine());
			ps.setString(202, bed.getUsgdate1());
			ps.setString(203, bed.getUsgdate2());
			ps.setString(204, bed.getUsgdate3());
			ps.setString(205, bed.getUsgdate4());

			// amenorrhea1, amenorrhea2, amenorrhea3, amenorrhea4,
			// presentation1, presentation2, presentation3, presentation4;
			ps.setString(206, bed.getAmenorrhea1());
			ps.setString(207, bed.getAmenorrhea2());
			ps.setString(208, bed.getAmenorrhea3());
			ps.setString(209, bed.getAmenorrhea4());
			ps.setString(210, bed.getPresentation1());
			ps.setString(211, bed.getPresentation2());
			ps.setString(212, bed.getPresentation3());
			ps.setString(213, bed.getPresentation4());

			// bpdgs1, bpdgs2, bpdgs3, bpdgs4, hc1, hc2, hc3, hc4,
			ps.setString(214, bed.getBpdgs1());
			ps.setString(215, bed.getBpdgs2());
			ps.setString(216, bed.getBpdgs3());
			ps.setString(217, bed.getBpdgs4());
			ps.setString(218, bed.getHc1());
			ps.setString(219, bed.getHc2());
			ps.setString(220, bed.getHc3());
			ps.setString(221, bed.getHc4());

			// ac1, ac2, ac3, ac4, flcrl1, flcrl2, flcrl3, flcrl4;
			ps.setString(222, bed.getAc1());
			ps.setString(223, bed.getAc2());
			ps.setString(224, bed.getAc3());
			ps.setString(225, bed.getAc4());
			ps.setString(226, bed.getFlcrl1());
			ps.setString(227, bed.getFlcrl2());
			ps.setString(228, bed.getFlcrl3());
			ps.setString(229, bed.getFlcrl4());

			// ga_usg1, ga_usg2, ga_usg3, ga_usg4, liquor1, liquor2, liquor3,
			// liquor4;
			ps.setString(230, bed.getGa_usg1());
			ps.setString(231, bed.getGa_usg2());
			ps.setString(232, bed.getGa_usg3());
			ps.setString(233, bed.getGa_usg4());
			ps.setString(234, bed.getLiquor1());
			ps.setString(235, bed.getLiquor2());
			ps.setString(236, bed.getLiquor3());
			ps.setString(237, bed.getLiquor4());

			// placenta1, placenta2, placenta3, placenta4, foetal_weight1,
			// foetal_weight2, foetal_weight3, foetal_weight4;
			ps.setString(238, bed.getPlacenta1());
			ps.setString(239, bed.getLiquor2());
			ps.setString(240, bed.getLiquor3());
			ps.setString(241, bed.getLiquor4());
			ps.setString(242, bed.getFoetal_weight1());
			ps.setString(243, bed.getFoetal_weight2());
			ps.setString(244, bed.getFoetal_weight3());
			ps.setString(245, bed.getFoetal_weight4());

			// cervical_length1, cervical_length2, cervical_length3,
			// cervical_length4, nt_scan, anomaly_scan, colour_doppler_scan
			ps.setString(246, bed.getCervical_length1());
			ps.setString(247, bed.getCervical_length2());
			ps.setString(248, bed.getCervical_length3());
			ps.setString(249, bed.getCervical_length4());
			ps.setString(250, bed.getNt_scan());
			ps.setString(251, bed.getAnomaly_scan());
			ps.setString(252, bed.getColour_doppler_scan());

			// gen_condition, temp, pallor, pedal_edema, pulse, bmi,
			// height,weight, sys_bp, dia_bp, wall_edema, fundal_height
			ps.setString(253, bed.getGen_condition());
			ps.setString(254, bed.getTemp());
			ps.setString(255, bed.getPallor());
			ps.setString(256, bed.getPedal_edema());
			ps.setString(257, bed.getPulse());
			ps.setString(258, bed.getBmi());
			ps.setString(259, bed.getHeight());
			ps.setString(260, bed.getWeight());
			ps.setString(261, bed.getSys_bp());
			ps.setString(262, bed.getDia_bp());
			ps.setString(263, bed.getWall_edema());
			ps.setString(264, bed.getFundal_height());

			// cephalic, breech, baley_size, transverse_fhs, liquor,
			// uterine_contractions, ps_cervix, ps_vagine;
			ps.setString(265, bed.getCephalic());
			ps.setString(266, bed.getBreech());
			ps.setString(267, bed.getBaley_size());
			ps.setString(268, bed.getTransverse_fhs());
			ps.setString(269, bed.getLiquor());
			ps.setString(270, bed.getUterine_contractions());
			ps.setString(271, bed.getPs_cervix());
			ps.setString(272, bed.getPs_vagine());

			// ps_vault, pv_trim, pv_unettacced, pv_ant, pv_os, pv_membranes,
			// pv_tubular, pv_just_ettacced, pv_mid_posits,
			// pv_soft, pv_ettacced, pv_post;
			ps.setString(273, bed.getPs_vault());
			ps.setString(274, bed.getPv_trim());
			ps.setString(275, bed.getPv_unettacced());
			ps.setString(276, bed.getPv_ant());
			ps.setString(277, bed.getPv_os());
			ps.setString(278, bed.getPv_membranes());
			ps.setString(279, bed.getPv_tubular());
			ps.setString(280, bed.getPv_just_ettacced());
			ps.setString(281, bed.getPv_mid_posits());
			ps.setString(282, bed.getPv_soft());
			ps.setString(283, bed.getPv_ettacced());
			ps.setString(284, bed.getPv_post());

			ps.setString(285, bed.getPs_fhs());
			ps.setString(286, bed.getPv_membrane());
			ps.setString(287, bed.getPv_station());
			ps.setString(288, bed.getPv_liquor());
			ps.setString(289, bed.getPv_pelvis());
			ps.setString(290, bed.getPv_position());
			ps.setString(291, bed.getBeats_min());

			// pmp, diagnosisgyn, le_vulva, le_vagina, pa_gynic, plan,
			// finaldiagnosis,
			ps.setString(292, bed.getPmp());
			ps.setString(293, bed.getDiagnosisgyn());
			ps.setString(294, bed.getLe_vulva());
			ps.setString(295, bed.getLe_vagina());
			ps.setString(296, bed.getPa_gynic());
			ps.setString(297, bed.getPlan());
			ps.setString(298, bed.getFinaldiagnosis());

			// priscription, pv_uterus, pv_uterus_size, pv_bl_fomices,
			// pv_mobility "
			ps.setString(299, bed.getPriscription());
			ps.setString(300, bed.getPv_uterus());
			ps.setString(301, bed.getPv_uterus_size());
			ps.setString(302, bed.getPv_bl_fomices());
			ps.setString(303, bed.getPv_mobility());
			ps.setString(304, bed.getFormtype());

//			//hrf_gdm, hrf_pih , hrf_boh , hrf_hypothyroid , hrf_hyperthyroid , hrf_prev_lscs , hrf_rh_negative , hrf_other,
//			ps.setString(305, bed.getHrf_gdm());
//			ps.setString(306, bed.getHrf_pih());
//			ps.setString(307, bed.getHrf_boh());
//			ps.setString(308, bed.getHrf_hypothyroid());
//			ps.setString(309, bed.getHrf_hyperthyroid());
//			ps.setString(310, bed.getHrf_prev_lscs());
//			ps.setString(311, bed.getHrf_rh_negative());
//			ps.setString(312, bed.getHrf_other());
//			
//			//fundalheight_weeks , pa_soft, external_ballotment, effaced_text, pv_dialated, pv_dialatedcm,
//			ps.setString(313, bed.getFundalheight_weeks());
//			ps.setString(314, bed.getExternal_ballotment());
//			ps.setString(315, bed.getEffaced_text());
//			ps.setString(316, bed.getPv_dialated());
//			ps.setString(317, bed.getPv_dialatedcm());
//			
//			//g, dating_usg_date, weeks_dating_scan, pog_dating_scan,
//			ps.setString(318, bed.getG());
//			ps.setString(319, bed.getDating_usg_date());
//			ps.setString(320, bed.getWeeks_dating_scan());
//			ps.setString(321, bed.getPog_dating_scan());
//			
//			//usg_report, additonal_notes, diagonosis, treatment_advice, investigation_advice
//			ps.setString(322, bed.getUsg_report());
//			ps.setString(323, bed.getAdditonal_notes());
//			ps.setString(324, bed.getDiagonosis());
//			ps.setString(325, bed.getTreatment_advice());
//			ps.setString(326, bed.getInvestigation_advice());
			result = ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			while (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public Bed getEditGynicFormData(String id) {

		Bed bed = new Bed();
		try {

			StringBuffer buffer = new StringBuffer();
			buffer.append("select ipdid, clientid, practitionerid, commencing, userid, lastmodified, lmp, edd, usg, ");
			buffer.append(
					"gravida, para, live, abortion, mtp, still_born, death, high_risk_factor, visit_reason_ids, ");
			buffer.append(
					"age_at_minarche, llmp, pmc, cycle_length, duration_flow, amount_flow, dysmenorrehea, dyspareunia, ");
			buffer.append(
					"ho_passing_clots, white_discharge_itching, intercouse_frequency, galactorrea, ho_contraception, rubella_vaccine, ");
			buffer.append(
					"menopause, nulligravida, current_pregnant, iud, live_boys, live_girls, still_birth, abortions, ");
			buffer.append("death_child, parity_abortion_notes, earlier_investigation, pt_history, pt_history_notes, ");
			buffer.append("family_history, family_history_notes, p, l, a, d, ");
			buffer.append("surgical_ho, date1, date2, date3, date4,hb1, hb2, hb3, hb4, ");
			buffer.append("fbs1, fbs2, fbs3, fbs4, dpbs1, dpbs2, dpbs3, dpbs4, ");
			buffer.append("urm1, urm2, urm3, urm4, tsh1, tsh2, tsh3, tsh4, ");
			buffer.append("ict1, ict2, ict3, ict4, gtt1, gtt2, gtt3, gtt4, ");
			buffer.append("hvm, hbsag, vdrl, hb_srecta, hbac, duet_markers, triple, Quradraple_maicers, ");
			buffer.append("ivf_date, down_regulation, ovarian_stimulation, os_dosage, sperm_quality, et_day, ");
			buffer.append(
					"oocytes_obtained, oocytes_quality, embroyos_grade, embroyos_transfered, embroyos_description, ");
			buffer.append(
					"freezing, transfer_process, betahcgcm, ivf_remark, do_family_history, ho_fertility_family, ho_genetic_family, ho_premature_family, ");
			buffer.append(
					"age_of_menarche, age_of_menarche_notes, dysmenorrhoe, dysmenorrhoe_notes, flow, flow_notes, sleep_disruption_bleeding, sleep_disruption_bleeding_notes, blachouts, blachouts_notes, ");
			buffer.append("alchohol, drugs, other_medication, tobaco, smoking, tobaco_notes, ");
			buffer.append("anormaly_scan_11week, cervical_length_11week, double_marker_11week, ");
			buffer.append("all_investigation_16week, sikling_16week, triple_marker_16week, abstinence_1visit,");
			buffer.append("barrier_contra_1visit, bed_rest_1visit, book_1visit, csv_1visit, dispi_test_1visit, ");
			buffer.append(
					"drug_reaction_1visit, hcg_1visit, heparin_1visit, oral_hygeine_1visit, other_test_1visit, physio_diet_1visit, ");
			buffer.append(
					"rubelle_status_1visit, smart_doc_1visit, stream_cell_1visit, vaginities_1visit, animally_scan_20week, ");
			buffer.append(
					"fetal_eco_20week, anti_d_24week, dipsi_24week, itc_24week, investigation_sos_30week, steroids_30week, ");
			buffer.append(
					"vaginities_treatment_30week, breast_preparation_34week, color_doppler_34week, labour_counselling_34week, nst_34week, vaginities_treatment_34week, ");
			buffer.append("nst_date1, nst_date2, nst_date3, nst_date4, nst_date5, nst_date6, ");
			buffer.append("nst_time1, nst_time2, nst_time3, nst_time4, nst_time5, nst_time6, ");
			buffer.append(
					"nst_indication1, nst_indication2, nst_indication3, nst_indication4, nst_indication5, nst_indication6, ");
			buffer.append("nst_duration1, nst_duration2, nst_duration3, nst_duration4, nst_duration5, nst_duration6, ");
			buffer.append(
					"nst_interpretation1, nst_interpretation2, nst_interpretation3, nst_interpretation4, nst_interpretation5, nst_interpretation6, ");
			buffer.append(
					"nst_intervention1, nst_intervention2, nst_intervention3, nst_intervention4, nst_intervention5, nst_intervention6, ");
			buffer.append("tt_dose1, tt_dose2, influenza_vaccine, usgdate1, usgdate2, usgdate3, usgdate4, ");
			buffer.append(
					"amenorrhea1, amenorrhea2, amenorrhea3, amenorrhea4, presentation1, presentation2, presentation3, presentation4,");
			buffer.append("bpdgs1, bpdgs2, bpdgs3, bpdgs4, hc1, hc2, hc3, hc4, ");
			buffer.append("ac1, ac2, ac3, ac4, flcrl1, flcrl2, flcrl3, flcrl4, ");
			buffer.append("ga_usg1, ga_usg2, ga_usg3, ga_usg4, liquor1, liquor2, liquor3, liquor4, ");
			buffer.append(
					"placenta1, placenta2, placenta3, placenta4, foetal_weight1, foetal_weight2, foetal_weight3, foetal_weight4, ");
			buffer.append(
					"cervical_length1, cervical_length2, cervical_length3, cervical_length4, nt_scan, anomaly_scan, colour_doppler_scan, ");

			buffer.append(
					"gen_condition, temp, pallor, pedal_edema, pulse, bmi, height,weight, sys_bp, dia_bp, wall_edema, fundal_height, ");
			buffer.append(
					"cephalic, breech, baley_size, transverse_fhs, liquor, uterine_contractions, ps_cervix, ps_vagine, ");
			buffer.append(
					"ps_vault, pv_trim, pv_unettacced, pv_ant, pv_os, pv_membranes, pv_tubular, pv_just_ettacced, pv_mid_posits, pv_soft, pv_ettacced, pv_post, ");
			buffer.append("ps_fhs,pv_membrane,pv_station,pv_liquor,pv_pelvis,pv_position,beats_min, ");

			buffer.append("pmp, diagnosisgyn, le_vulva, le_vagina, pa_gynic, plan, finaldiagnosis, ");
			buffer.append("priscription, pv_uterus, pv_uterus_size, pv_bl_fomices, pv_mobility, formtype ");

			buffer.append("from ipd_gynic_form where id=" + id + " ");

			PreparedStatement ps = connection.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				bed.setIpdid(rs.getString(1));
				bed.setClientid(rs.getString(2));
				bed.setPractitionerid(rs.getString(3));
				bed.setCommencing(rs.getString(4));
				bed.setUserid(rs.getString(5));
				bed.setLastmodified(rs.getString(6));
				bed.setLmp(rs.getString(7));
				bed.setEdd(rs.getString(8));
				bed.setUsg(rs.getString(9));
				bed.setGravida(rs.getString(10));
				bed.setPara(rs.getString(11));
				bed.setLive(rs.getString(12));
				bed.setAbortion(rs.getString(13));
				bed.setMtp(rs.getString(14));
				bed.setStill_born(rs.getString(15));
				bed.setDeath(rs.getString(16));
				bed.setHigh_risk_factor(rs.getString(17));
				bed.setVisit_reason_ids(rs.getString(18));
				bed.setAge_menarche(rs.getString(19));
				bed.setLlmp(rs.getString(20));
				bed.setPmc(rs.getString(21));
				bed.setCycle_length(rs.getString(22));
				bed.setDuration_flow(rs.getString(23));
				bed.setAmount_flow(rs.getString(24));
				bed.setDysmenorrhea(rs.getString(25));
				bed.setDyspareunia(rs.getString(26));
				bed.setHopassing_clots(rs.getString(27));
				bed.setWhite_disc_itching(rs.getString(28));
				bed.setIntercourse_freq(rs.getString(29));
				bed.setGalactorrea(rs.getString(30));
				bed.setHo_contraception(rs.getString(31));
				bed.setRubella_vaccine(rs.getString(32));
				bed.setMenopause(rs.getString(33));
				bed.setNulligravida(rs.getString(34));
				bed.setCurrent_pregnent(rs.getString(35));
				bed.setIud(rs.getString(36));
				// live_boys, live_girls, still_birth, abortions,
				bed.setLive_boys(rs.getString(37));
				bed.setLive_girls(rs.getString(38));
				bed.setStillbirths(rs.getString(39));
				bed.setAbortions(rs.getString(40));
				// death_child, parity_abortion_notes, earlier_investigation,
				// pt_history, pt_history_notes,
				bed.setDeath_children(rs.getString(41));
				bed.setParity_abortion_notes(rs.getString(42));
				bed.setEarlierinvest(rs.getString(43));
				bed.setPasthistory(rs.getString(44));
				bed.setPt_history_notes(rs.getString(45));

				// family_history, family_history_notes, p, l, a, d
				bed.setFamilyhist(rs.getString(46));
				bed.setFamily_history_notes(rs.getString(47));
				bed.setP(rs.getString(48));
				bed.setL(rs.getString(49));
				bed.setA(rs.getString(50));
				bed.setD(rs.getString(51));

				// surgical_ho, date1, date2, date3, date4,hb1, hb2, hb3, hb4,
				bed.setSurgical_ho(rs.getString(52));
				bed.setDate1(rs.getString(53));
				bed.setDate2(rs.getString(54));
				bed.setDate3(rs.getString(55));
				bed.setDate4(rs.getString(56));
				bed.setHb1(rs.getString(57));
				bed.setHb2(rs.getString(58));
				bed.setHb3(rs.getString(59));
				bed.setHb4(rs.getString(60));

				// fbs1, fbs2, fbs3, fbs4, dpbs1, dpbs2, dpbs3, dpbs4,
				bed.setFbs1(rs.getString(61));
				bed.setFbs2(rs.getString(62));
				bed.setFbs3(rs.getString(63));
				bed.setFbs4(rs.getString(64));
				bed.setDpbs1(rs.getString(65));
				bed.setDpbs2(rs.getString(66));
				bed.setDpbs3(rs.getString(67));
				bed.setDpbs4(rs.getString(68));

				// urm1, urm2, urm3, urm4, tsh1, tsh2, tsh3, tsh4,
				bed.setUrm1(rs.getString(69));
				bed.setUrm2(rs.getString(70));
				bed.setUrm3(rs.getString(71));
				bed.setUrm4(rs.getString(72));
				bed.setTsh1(rs.getString(73));
				bed.setTsh2(rs.getString(74));
				bed.setTsh3(rs.getString(75));
				bed.setTsh4(rs.getString(76));

				// ict1, ict2, ict3, ict4, gtt1, gtt2, gtt3, gtt4,
				bed.setIct1(rs.getString(77));
				bed.setIct2(rs.getString(78));
				bed.setIct3(rs.getString(79));
				bed.setIct4(rs.getString(80));
				bed.setGtt1(rs.getString(81));
				bed.setGtt2(rs.getString(82));
				bed.setGtt3(rs.getString(83));
				bed.setGtt4(rs.getString(84));

				// hvm, hbsag, vdrl, hb_srecta, hbac, duet_markers, triple,
				// Quradraple_maicers
				bed.setHv_1m(rs.getString(85));
				bed.setHbs_ag(rs.getString(86));
				bed.setVdrl(rs.getString(87));
				bed.setHb_srecta(rs.getString(88));
				bed.setHb_ac(rs.getString(89));
				bed.setDuet_markess(rs.getString(90));
				bed.setTriple(rs.getString(91));
				bed.setQuadrple_maicers(rs.getString(92));

				// buffer.append("ivf_date, down_regulation,
				// ovarian_stimulation, os_dosage, sperm_quality, et_day, ");
				bed.setIvf_date(rs.getString(93));
				bed.setDown_regulation(rs.getString(94));
				bed.setOvarian_stimulation(rs.getString(95));
				bed.setOs_dosage(rs.getString(96));
				bed.setSperm_quality(rs.getString(97));
				bed.setEt_day(rs.getString(98));

				// buffer.append("oocytes_obtained, oocytes_quality,
				// embroyos_grade, embroyos_transfered, embroyos_description,
				// ");
				bed.setOocytes_obtained(rs.getString(99));
				bed.setOocytes_quality(rs.getString(100));
				bed.setEmbroyos_grade(rs.getString(101));
				bed.setEmbroyos_transfered(rs.getString(102));
				bed.setEmbroyos_description(rs.getString(103));

				// buffer.append("freezing, transfer_process, betahcgcm,
				// ivf_remark, do_family_history, ho_fertility_family,
				// ho_genetic_family, ho_premature_family, ");
				bed.setFreezing(rs.getString(104));
				bed.setTransfer_process(rs.getString(105));
				bed.setBetahcgcm(rs.getString(106));
				bed.setIvf_remark(rs.getString(107));
				bed.setDo_family_history(rs.getString(108));
				bed.setHo_fertility_family(rs.getString(109));
				bed.setHo_genetic_family(rs.getString(110));
				bed.setHo_premature_family(rs.getString(111));

				// buffer.append("age_of_menarche, age_of_menarche_notes,
				// dysmenorrhoe, dysmenorrhoe_notes, flow, flow_notes,
				// sleep_disruption_bleeding, sleep_disruption_bleeding_notes,
				// blachouts, blachouts_notes ");
				bed.setAge_of_menarche(rs.getString(112));
				bed.setAge_of_menarche_notes(rs.getString(113));
				bed.setDysmenorrhoe(rs.getString(114));
				bed.setDysmenorrhoe_notes(rs.getString(115));
				bed.setFlow(rs.getString(116));
				bed.setFlow_notes(rs.getString(117));
				bed.setSleep_disruption_bleeding(rs.getString(118));
				bed.setSleep_disruption_bleeding_notes(rs.getString(119));
				bed.setBlachouts(rs.getString(120));
				bed.setBlachouts_notes(rs.getString(121));

				// alchohol, drugs, other_medication, tobaco, smoking,
				// tobaco_notes
				bed.setAlcohal(rs.getString(122));
				bed.setDrugs(rs.getString(123));
				bed.setOther_medication(rs.getString(124));
				bed.setTobaco(rs.getString(125));
				bed.setSmoking(rs.getString(126));
				bed.setTobaconotes(rs.getString(127));

				// anormaly_scan_11week, cervical_length_11week,
				// double_marker_11week,;
				bed.setAnormaly_scan_11week(rs.getString(128));
				bed.setCervical_length_11week(rs.getString(129));
				bed.setDouble_marker_11week(rs.getString(130));

				// all_investigation_16week, sikling_16week,
				// triple_marker_16week, abstinence_1visit;
				bed.setAll_investigation_16week(rs.getString(131));
				bed.setSikling_16week(rs.getString(132));
				bed.setTriple_marker_16week(rs.getString(133));
				bed.setAbstinence_1visit(rs.getString(134));

				// barrier_contra_1visit, bed_rest_1visit, book_1visit,
				// csv_1visit, dispi_test_1visit;
				bed.setBarrier_contra_1visit(rs.getString(135));
				bed.setBed_rest_1visit(rs.getString(136));
				bed.setBook_1visit(rs.getString(137));
				bed.setCsv_1visit(rs.getString(138));
				bed.setDispi_test_1visit(rs.getString(139));

				// drug_reaction_1visit, hcg_1visit, heparin_1visit,
				// oral_hygeine_1visit, other_test_1visit, physio_diet_1visit,
				bed.setDrug_reaction_1visit(rs.getString(140));
				bed.setHcg_1visit(rs.getString(141));
				bed.setHeparin_1visit(rs.getString(142));
				bed.setOral_hygeine_1visit(rs.getString(143));
				bed.setOther_test_1visit(rs.getString(144));
				bed.setPhysio_diet_1visit(rs.getString(145));

				// rubelle_status_1visit, smart_doc_1visit, stream_cell_1visit,
				// vaginities_1visit, animally_scan_20week
				bed.setRubelle_status_1visit(rs.getString(146));
				bed.setSmart_doc_1visit(rs.getString(147));
				bed.setStream_cell_1visit(rs.getString(148));
				bed.setVaginities_1visit(rs.getString(149));
				bed.setAnimally_scan_20week(rs.getString(150));

				// fetal_eco_20week, anti_d_24week, dipsi_24week, itc_24week,
				// investigation_sos_30week, steroids_30week;
				bed.setFetal_eco_20week(rs.getString(151));
				bed.setAnti_d_24week(rs.getString(152));
				bed.setDipsi_24week(rs.getString(153));
				bed.setItc_24week(rs.getString(154));
				bed.setInvestigation_sos_30week(rs.getString(155));
				bed.setSteroids_30week(rs.getString(156));

				// vaginities_treatment_30week, breast_preparation_34week,
				// color_doppler_34week, labour_counselling_34week, nst_34week,
				// vaginities_treatment_34week
				bed.setVaginities_treatment_30week(rs.getString(157));
				bed.setBreast_preparation_34week(rs.getString(158));
				bed.setColor_doppler_34week(rs.getString(159));
				bed.setLabour_counselling_34week(rs.getString(160));
				bed.setNst_34week(rs.getString(161));
				bed.setVaginities_treatment_34week(rs.getString(162));

				// nst_date1, nst_date2, nst_date3, nst_date4, nst_date5,
				// nst_date6,
				bed.setNst_date1(rs.getString(163));
				bed.setNst_date2(rs.getString(164));
				bed.setNst_date3(rs.getString(165));
				bed.setNst_date4(rs.getString(166));
				bed.setNst_date5(rs.getString(167));
				bed.setNst_date6(rs.getString(168));

				// nst_time1, nst_time2, nst_time3, nst_time4, nst_time5,
				// nst_time6,
				bed.setNst_time1(rs.getString(169));
				bed.setNst_time2(rs.getString(170));
				bed.setNst_time3(rs.getString(171));
				bed.setNst_time4(rs.getString(172));
				bed.setNst_time5(rs.getString(173));
				bed.setNst_time6(rs.getString(174));

				// nst_indication1, nst_indication2, nst_indication3,
				// nst_indication4, nst_indication5, nst_indication6,
				bed.setNst_indication1(rs.getString(175));
				bed.setNst_indication2(rs.getString(176));
				bed.setNst_indication3(rs.getString(177));
				bed.setNst_indication4(rs.getString(178));
				bed.setNst_indication5(rs.getString(179));
				bed.setNst_indication6(rs.getString(180));

				// nst_duration1, nst_duration2, nst_duration3, nst_duration4,
				// nst_duration5, nst_duration6,
				bed.setNst_duration1(rs.getString(181));
				bed.setNst_duration2(rs.getString(182));
				bed.setNst_duration3(rs.getString(183));
				bed.setNst_duration4(rs.getString(184));
				bed.setNst_duration5(rs.getString(185));
				bed.setNst_duration6(rs.getString(186));

				// nst_interpretation1, nst_interpretation2,
				// nst_interpretation3, nst_interpretation4,
				// nst_interpretation5, nst_interpretation6,
				bed.setNst_interpretation1(rs.getString(187));
				bed.setNst_interpretation2(rs.getString(188));
				bed.setNst_interpretation3(rs.getString(189));
				bed.setNst_interpretation4(rs.getString(190));
				bed.setNst_interpretation5(rs.getString(191));
				bed.setNst_interpretation6(rs.getString(192));

				// nst_intervention1, nst_intervention2, nst_intervention3,
				// nst_intervention4, nst_intervention5, nst_intervention6
				bed.setNst_intervention1(rs.getString(193));
				bed.setNst_intervention2(rs.getString(194));
				bed.setNst_intervention3(rs.getString(195));
				bed.setNst_intervention4(rs.getString(196));
				bed.setNst_intervention5(rs.getString(197));
				bed.setNst_intervention6(rs.getString(198));

				// tt_dose1, tt_dose2, influenza_vaccine, usgdate1, usgdate2,
				// usgdate3, usgdate4,
				bed.setTt_dose1(rs.getString(199));
				bed.setTt_dose2(rs.getString(200));
				bed.setInfluenza_vaccine(rs.getString(201));
				bed.setUsgdate1(rs.getString(202));
				bed.setUsgdate2(rs.getString(203));
				bed.setUsgdate3(rs.getString(204));
				bed.setUsgdate4(rs.getString(205));

				// amenorrhea1, amenorrhea2, amenorrhea3, amenorrhea4,
				// presentation1, presentation2, presentation3, presentation4,
				bed.setAmenorrhea1(rs.getString(206));
				bed.setAmenorrhea2(rs.getString(207));
				bed.setAmenorrhea3(rs.getString(208));
				bed.setAmenorrhea4(rs.getString(209));
				bed.setPresentation1(rs.getString(210));
				bed.setPresentation2(rs.getString(211));
				bed.setPresentation3(rs.getString(212));
				bed.setPresentation4(rs.getString(213));

				// bpdgs1, bpdgs2, bpdgs3, bpdgs4, hc1, hc2, hc3, hc4,
				bed.setBpdgs1(rs.getString(214));
				bed.setBpdgs2(rs.getString(215));
				bed.setBpdgs3(rs.getString(216));
				bed.setBpdgs4(rs.getString(217));
				bed.setHc1(rs.getString(218));
				bed.setHc2(rs.getString(219));
				bed.setHc3(rs.getString(220));
				bed.setHc4(rs.getString(221));

				// ac1, ac2, ac3, ac4, flcrl1, flcrl2, flcrl3, flcrl4,
				bed.setAc1(rs.getString(222));
				bed.setAc2(rs.getString(223));
				bed.setAc3(rs.getString(224));
				bed.setAc4(rs.getString(225));
				bed.setFlcrl1(rs.getString(226));
				bed.setFlcrl2(rs.getString(227));
				bed.setFlcrl3(rs.getString(228));
				bed.setFlcrl4(rs.getString(229));

				// ga_usg1, ga_usg2, ga_usg3, ga_usg4, liquor1, liquor2,
				// liquor3, liquor4,
				bed.setGa_usg1(rs.getString(230));
				bed.setGa_usg2(rs.getString(231));
				bed.setGa_usg3(rs.getString(232));
				bed.setGa_usg4(rs.getString(233));
				bed.setLiquor1(rs.getString(234));
				bed.setLiquor2(rs.getString(235));
				bed.setLiquor3(rs.getString(236));
				bed.setLiquor4(rs.getString(237));

				// placenta1, placenta2, placenta3, placenta4, foetal_weight1,
				// foetal_weight2, foetal_weight3, foetal_weight4,
				bed.setPlacenta1(rs.getString(238));
				bed.setPlacenta2(rs.getString(239));
				bed.setPlacenta3(rs.getString(240));
				bed.setPlacenta4(rs.getString(241));
				bed.setFoetal_weight1(rs.getString(242));
				bed.setFoetal_weight2(rs.getString(243));
				bed.setFoetal_weight3(rs.getString(244));
				bed.setFoetal_weight4(rs.getString(245));

				// cervical_length1, cervical_length2, cervical_length3,
				// cervical_length4, nt_scan, anomaly_scan, colour_doppler_scan
				bed.setCervical_length1(rs.getString(246));
				bed.setCervical_length2(rs.getString(247));
				bed.setCervical_length3(rs.getString(248));
				bed.setCervical_length4(rs.getString(249));
				bed.setNt_scan(rs.getString(250));
				bed.setAnomaly_scan(rs.getString(251));
				bed.setColour_doppler_scan(rs.getString(252));

				// gen_condition, temp, pallor, pedal_edema, pulse, bmi,
				// height,weight, sys_bp, dia_bp, wall_edema, fundal_height
				bed.setGen_condition(rs.getString(253));
				bed.setTemp(rs.getString(254));
				bed.setPallor(rs.getString(255));
				bed.setPedal_edema(rs.getString(256));
				bed.setPulse(rs.getString(257));
				bed.setBmi(rs.getString(258));
				bed.setHeight(rs.getString(259));
				bed.setWeight(rs.getString(260));
				bed.setSys_bp(rs.getString(261));
				bed.setDia_bp(rs.getString(262));
				bed.setWall_edema(rs.getString(263));
				bed.setFundal_height(rs.getString(264));

				// cephalic, breech, baley_size, transverse_fhs, liquor,
				// uterine_contractions, ps_cervix, ps_vagine,
				bed.setCephalic(rs.getString(265));
				bed.setBreech(rs.getString(266));
				bed.setBaley_size(rs.getString(267));
				bed.setTransverse_fhs(rs.getString(268));
				bed.setLiquor(rs.getString(269));
				bed.setUterine_contractions(rs.getString(270));
				bed.setPs_cervix(rs.getString(271));
				bed.setPs_vagine(rs.getString(272));

				// ps_vault, pv_trim, pv_unettacced, pv_ant, pv_os,
				// pv_membranes, pv_tubular, pv_just_ettacced, pv_mid_posits,
				// pv_soft, pv_ettacced, pv_post
				bed.setPs_vault(rs.getString(273));
				bed.setPv_trim(rs.getString(274));
				bed.setPv_unettacced(rs.getString(275));
				bed.setPv_ant(rs.getString(276));
				bed.setPv_os(rs.getString(277));
				bed.setPv_membranes(rs.getString(278));
				bed.setPv_tubular(rs.getString(279));
				bed.setPv_just_ettacced(rs.getString(280));
				bed.setPv_mid_posits(rs.getString(281));
				bed.setPv_soft(rs.getString(282));
				bed.setPv_ettacced(rs.getString(283));
				bed.setPv_post(rs.getString(284));

				// ps_fhs,pv_membrane,pv_station,pv_liquor,pv_pelvis,pv_position,
				// beats_min;
				bed.setPs_fhs(rs.getString(285));
				bed.setPv_membrane(rs.getString(286));
				bed.setPv_station(rs.getString(287));
				bed.setPv_liquor(rs.getString(288));
				bed.setPv_pelvis(rs.getString(289));
				bed.setPv_position(rs.getString(290));
				bed.setBeats_min(rs.getString(291));

				// pmp, diagnosisgyn, le_vulva, le_vagina, pa_gynic, plan,
				// finaldiagnosis,
				bed.setPmp(rs.getString(292));
				bed.setDiagnosisgyn(rs.getString(293));
				bed.setLe_vulva(rs.getString(294));
				bed.setLe_vagina(rs.getString(295));
				bed.setPa_gynic(rs.getString(296));
				bed.setPlan(rs.getString(297));
				bed.setFinaldiagnosis(rs.getString(298));

				// priscription, pv_uterus, pv_uterus_size, pv_bl_fomices,
				// pv_mobility
				bed.setPriscription(rs.getString(299));
				bed.setPv_uterus(rs.getString(300));
				bed.setPv_uterus_size(rs.getString(301));
				bed.setPv_bl_fomices(rs.getString(302));
				bed.setPv_mobility(rs.getString(303));

				bed.setFormtype(rs.getString(304));

				bed.setId(Integer.parseInt(id));
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return bed;
	}

	public int updateIpdGynicDetails(Bed bed) {

		int result = 0;
		StringBuffer buffer = new StringBuffer();

		try {
			buffer.append("update ipd_gynic_form set lastmodified= ?, lmp=?, edd =?, usg =?, ");
			buffer.append(
					"gravida=?, para=?, live=?, abortion=?, mtp=?, still_born=?, death=?, high_risk_factor=?, visit_reason_ids=?, ");
			buffer.append(
					"age_at_minarche= ?, llmp=?, pmc=? , cycle_length=? , duration_flow=?, amount_flow=?, dysmenorrehea=? , dyspareunia=?, ");
			buffer.append(
					"ho_passing_clots=? , white_discharge_itching=?, intercouse_frequency=?, galactorrea=? , ho_contraception=? , rubella_vaccine=?, ");
			buffer.append(
					"menopause=? , nulligravida=? , current_pregnant=?, iud=?, live_boys=?, live_girls=?, still_birth=?, abortions=?, ");
			buffer.append(
					"death_child=?, parity_abortion_notes=?, earlier_investigation=?, pt_history=?, pt_history_notes=?, ");
			buffer.append("family_history=?, family_history_notes=? , p=?, l=?, a=?, d=?, ");
			buffer.append("surgical_ho=?, date1=? , date2=? , date3=?, date4= ?,hb1=? , hb2=?, hb3=?, hb4=?, ");
			buffer.append("fbs1=?, fbs2=?, fbs3=?, fbs4=?, dpbs1=?, dpbs2=?, dpbs3=?, dpbs4=?, ");
			buffer.append("urm1=?, urm2=?, urm3=?, urm4=?, tsh1=?, tsh2=?, tsh3=?, tsh4=?, ");
			buffer.append("ict1=?, ict2=?, ict3=?, ict4=?, gtt1=?, gtt2=?, gtt3=?, gtt4=?, ");
			buffer.append(
					"hvm=?, hbsag=?, vdrl=?, hb_srecta=?, hbac=?, duet_markers=?, triple=?, Quradraple_maicers=?, ");
			buffer.append(
					"ivf_date=?, down_regulation=?, ovarian_stimulation=?, os_dosage=?, sperm_quality=?, et_day=?, ");
			buffer.append(
					"oocytes_obtained=?, oocytes_quality=?, embroyos_grade=?, embroyos_transfered=?, embroyos_description=?, ");
			buffer.append(
					"freezing=?, transfer_process=?, betahcgcm=?, ivf_remark=?, do_family_history=?, ho_fertility_family=?, ho_genetic_family=?, ho_premature_family=?, ");
			buffer.append(
					"age_of_menarche=?, age_of_menarche_notes=?, dysmenorrhoe=?, dysmenorrhoe_notes=?, flow=?, flow_notes=?, sleep_disruption_bleeding=?, sleep_disruption_bleeding_notes=?, blachouts=?, blachouts_notes=?, ");
			buffer.append("alchohol=? , drugs=?, other_medication=?, tobaco=?, smoking=?, tobaco_notes=?, ");
			buffer.append("anormaly_scan_11week =?, cervical_length_11week=?, double_marker_11week=?, ");
			buffer.append("all_investigation_16week=?, sikling_16week=?, triple_marker_16week=?, abstinence_1visit=?,");
			buffer.append(
					"barrier_contra_1visit=?, bed_rest_1visit=?, book_1visit=?, csv_1visit=?, dispi_test_1visit=?, ");
			buffer.append(
					"drug_reaction_1visit=?, hcg_1visit=?, heparin_1visit=?, oral_hygeine_1visit=?, other_test_1visit=?, physio_diet_1visit=?, ");
			buffer.append(
					"rubelle_status_1visit=?, smart_doc_1visit=?, stream_cell_1visit=?, vaginities_1visit=?, animally_scan_20week=?, ");
			buffer.append(
					"fetal_eco_20week=?, anti_d_24week=?, dipsi_24week=?, itc_24week=?, investigation_sos_30week=?, steroids_30week=?, ");
			buffer.append(
					"vaginities_treatment_30week=?, breast_preparation_34week=?, color_doppler_34week=?, labour_counselling_34week=?, nst_34week=?, vaginities_treatment_34week=?, ");
			buffer.append("nst_date1=?, nst_date2=?, nst_date3=?, nst_date4=?, nst_date5=?, nst_date6=?, ");
			buffer.append("nst_time1=?, nst_time2=?, nst_time3=?, nst_time4=?, nst_time5=?, nst_time6=?, ");
			buffer.append(
					"nst_indication1=?, nst_indication2=?, nst_indication3=?, nst_indication4=?, nst_indication5=?, nst_indication6=?, ");
			buffer.append(
					"nst_duration1=?, nst_duration2=?, nst_duration3=?, nst_duration4=?, nst_duration5=?, nst_duration6=?, ");
			buffer.append(
					"nst_interpretation1=?, nst_interpretation2=?, nst_interpretation3=?, nst_interpretation4=?, nst_interpretation5=?, nst_interpretation6=?, ");
			buffer.append(
					"nst_intervention1=?, nst_intervention2=?, nst_intervention3=?, nst_intervention4=?, nst_intervention5=?, nst_intervention6=?, ");
			buffer.append(
					"tt_dose1=?, tt_dose2=?, influenza_vaccine=?, usgdate1=?, usgdate2=?, usgdate3=?, usgdate4=?, ");
			buffer.append(
					"amenorrhea1=?, amenorrhea2=?, amenorrhea3=?, amenorrhea4=?, presentation1=?, presentation2=?, presentation3=?, presentation4=?,");
			buffer.append("bpdgs1=?, bpdgs2=?, bpdgs3=?, bpdgs4=?, hc1=?, hc2=?, hc3=?, hc4=?, ");
			buffer.append("ac1=?, ac2=?, ac3=?, ac4=?, flcrl1=?, flcrl2=?, flcrl3=?, flcrl4=?, ");
			buffer.append("ga_usg1=?, ga_usg2=?, ga_usg3=?, ga_usg4=?, liquor1=?, liquor2=?, liquor3=?, liquor4=?, ");
			buffer.append(
					"placenta1=?, placenta2=?, placenta3=?, placenta4=?, foetal_weight1=?, foetal_weight2=?, foetal_weight3=?, foetal_weight4=?, ");
			buffer.append(
					"cervical_length1=?, cervical_length2=?, cervical_length3=?, cervical_length4=?, nt_scan=?, anomaly_scan=?, colour_doppler_scan=?, ");
			buffer.append(
					"gen_condition=?, temp=?, pallor=?, pedal_edema=?, pulse=?, bmi=?, height=?,weight=?, sys_bp=?, dia_bp=?, wall_edema=?, fundal_height=?, ");
			buffer.append(
					"cephalic=?, breech=?, baley_size=?, transverse_fhs=?, liquor=?, uterine_contractions=?, ps_cervix=?, ps_vagine=?, ");
			buffer.append(
					"ps_vault=?, pv_trim=?, pv_unettacced=?, pv_ant=?, pv_os=?, pv_membranes=?, pv_tubular=?, pv_just_ettacced=?, pv_mid_posits=?, pv_soft=?, pv_ettacced=?, pv_post=?, ");
			buffer.append(
					"ps_fhs=?, pv_membrane=?, pv_station=?, pv_liquor=?, pv_pelvis=?, pv_position=?,beats_min=?, ");
			buffer.append("pmp=?, diagnosisgyn=?, le_vulva=?, le_vagina=?, pa_gynic=?, plan=?, finaldiagnosis=?, ");
			buffer.append(
					"priscription=? , pv_uterus=? , pv_uterus_size=? , pv_bl_fomices=?, pv_mobility=?, formtype=? ");

			buffer.append("where id=" + bed.getId() + " ");

			PreparedStatement ps = connection.prepareStatement(buffer.toString());
			ps.setString(1, bed.getLastmodified());
			ps.setString(2, bed.getLmp());
			ps.setString(3, bed.getEdd());
			ps.setString(4, bed.getUsg());
			ps.setString(5, bed.getGravida());
			ps.setString(6, bed.getPara());
			ps.setString(7, bed.getLive());
			ps.setString(8, bed.getAbortion());
			ps.setString(9, bed.getMtp());
			ps.setString(10, bed.getStill_born());
			ps.setString(11, bed.getDeath());
			ps.setString(12, bed.getHigh_risk_factor());
			ps.setString(13, bed.getVisit_reason_ids());
			ps.setString(14, bed.getAge_menarche());
			ps.setString(15, bed.getLlmp());
			ps.setString(16, bed.getPmc());
			ps.setString(17, bed.getCycle_length());
			ps.setString(18, bed.getDuration_flow());
			ps.setString(19, bed.getAmount_flow());
			ps.setString(20, bed.getDysmenorrhea());
			ps.setString(21, bed.getDyspareunia());
			ps.setString(22, bed.getHopassing_clots());
			ps.setString(23, bed.getWhite_disc_itching());
			ps.setString(24, bed.getIntercourse_freq());
			ps.setString(25, bed.getGalactorrea());
			ps.setString(26, bed.getHo_contraception());
			ps.setString(27, bed.getRubella_vaccine());
			ps.setString(28, bed.getMenopause());
			ps.setString(29, bed.getNulligravida());
			ps.setString(30, bed.getCurrent_pregnent());
			ps.setString(31, bed.getIud());
			ps.setString(32, bed.getLive_boys());
			ps.setString(33, bed.getLive_girls());
			ps.setString(34, bed.getStillbirths());
			ps.setString(35, bed.getAbortions());
			ps.setString(36, bed.getDeath_children());
			ps.setString(37, bed.getParity_abortion_notes());
			ps.setString(38, bed.getEarlierinvest());
			ps.setString(39, bed.getPasthistory());
			ps.setString(40, bed.getPt_history_notes()); // past_history_notes
			ps.setString(41, bed.getFamilyhist());
			ps.setString(42, bed.getFamily_history_notes()); // family_history_notes
			ps.setString(43, bed.getP());
			ps.setString(44, bed.getL());
			ps.setString(45, bed.getA());
			ps.setString(46, bed.getD());

			// surgical_ho, date1, date2, date3, date4,hb1, hb2, hb3, hb4,
			ps.setString(47, bed.getSurgical_ho());
			ps.setString(48, bed.getDate1());
			ps.setString(49, bed.getDate2());
			ps.setString(50, bed.getDate3());
			ps.setString(51, bed.getDate4());
			ps.setString(52, bed.getHb1());
			ps.setString(53, bed.getHb2());
			ps.setString(54, bed.getHb3());
			ps.setString(55, bed.getHb4());

			// fbs1, fbs2, fbs3, fbs4, dpbs1, dpbs2, dpbs3, dpbs4,

			ps.setString(56, bed.getFbs1());
			ps.setString(57, bed.getFbs2());
			ps.setString(58, bed.getFbs3());
			ps.setString(59, bed.getFbs4());
			ps.setString(60, bed.getDpbs1());
			ps.setString(61, bed.getDpbs2());
			ps.setString(62, bed.getDpbs3());
			ps.setString(63, bed.getDpbs4());

			// urm1, urm2, urm3, urm4, tsh1, tsh2, tsh3, tsh4,
			ps.setString(64, bed.getUrm1());
			ps.setString(65, bed.getUrm2());
			ps.setString(66, bed.getUrm3());
			ps.setString(67, bed.getUrm4());
			ps.setString(68, bed.getTsh1());
			ps.setString(69, bed.getTsh2());
			ps.setString(70, bed.getTsh3());
			ps.setString(71, bed.getTsh4());

			// ict1, ict2, ict3, ict4, gtt1, gtt2, gtt3, gtt4
			ps.setString(72, bed.getIct1());
			ps.setString(73, bed.getIct2());
			ps.setString(74, bed.getIct3());
			ps.setString(75, bed.getIct4());
			ps.setString(76, bed.getGtt1());
			ps.setString(77, bed.getGtt2());
			ps.setString(78, bed.getGtt3());
			ps.setString(79, bed.getGtt4());

			// hvm, hbsag, vdrl, hb_srecta, hbac, duet_markers, triple,
			// Quradraple Maicers
			ps.setString(80, bed.getHv_1m());
			ps.setString(81, bed.getHbs_ag());
			ps.setString(82, bed.getVdrl());
			ps.setString(83, bed.getHb_srecta());
			ps.setString(84, bed.getHb_ac());
			ps.setString(85, bed.getDuet_markess());
			ps.setString(86, bed.getTriple());
			ps.setString(87, bed.getQuadrple_maicers());

			// buffer.append("ivf_date=?, down_regulation=?,
			// ovarian_stimulation=?, os_dosage=?, sperm_quality=?, et_day=?,
			// ");
			ps.setString(88, bed.getIvf_date());
			ps.setString(89, bed.getDown_regulation());
			ps.setString(90, bed.getOvarian_stimulation());
			ps.setString(91, bed.getOs_dosage());
			ps.setString(92, bed.getSperm_quality());
			ps.setString(93, bed.getEt_day());

			// buffer.append("oocytes_obtained=?, oocytes_quality=?,
			// embroyos_grade=?, embroyos_transfered=?, embroyos_description=?,
			// ");
			ps.setString(94, bed.getOocytes_obtained());
			ps.setString(95, bed.getOocytes_quality());
			ps.setString(96, bed.getEmbroyos_grade());
			ps.setString(97, bed.getEmbroyos_transfered());
			ps.setString(98, bed.getEmbroyos_description());

			// buffer.append("freezing=?, transfer_process=?, betahcgcm=?,
			// ivf_remark=?, do_family_history=?, ho_fertility_family=?,
			// ho_genetic_family=?, ho_premature_family=?, ");
			ps.setString(99, bed.getFreezing());
			ps.setString(100, bed.getTransfer_process());
			ps.setString(101, bed.getBetahcgcm());
			ps.setString(102, bed.getIvf_remark());
			ps.setString(103, bed.getDo_family_history());
			ps.setString(104, bed.getHo_fertility_family());
			ps.setString(105, bed.getHo_genetic_family());
			ps.setString(106, bed.getHo_premature_family());

			// buffer.append("age_of_menarche=?, age_of_menarche_notes=?,
			// dysmenorrhoe=?, dysmenorrhoe_notes=?, flow=?, flow_notes=?,
			// sleep_disruption_bleeding=?, sleep_disruption_bleeding_notes=?,
			// blachouts=?, blachouts_notes=? ");
			ps.setString(107, bed.getAge_of_menarche());
			ps.setString(108, bed.getAge_of_menarche_notes());
			ps.setString(109, bed.getDysmenorrhoe());
			ps.setString(110, bed.getDysmenorrhoe_notes());
			ps.setString(111, bed.getFlow());
			ps.setString(112, bed.getFlow_notes());
			ps.setString(113, bed.getSleep_disruption_bleeding());
			ps.setString(114, bed.getSleep_disruption_bleeding_notes());
			ps.setString(115, bed.getBlachouts());
			ps.setString(116, bed.getBlachouts_notes());

			// alchohol, drugs, other_medication, tobaco, smoking, tobaco_notes
			ps.setString(117, bed.getAlcohal());
			ps.setString(118, bed.getDrugs());
			ps.setString(119, bed.getOther_medication());
			ps.setString(120, bed.getTobaco());
			ps.setString(121, bed.getSmoking());
			ps.setString(122, bed.getTobaconotes());

			// buffer.append("anormaly_scan_11week, cervical_length_11week,
			// double_marker_11week, ");
			ps.setString(123, bed.getAnormaly_scan_11week());
			ps.setString(124, bed.getCervical_length_11week());
			ps.setString(125, bed.getDouble_marker_11week());

			// buffer.append("all_investigation_16week, sikling_16week,
			// triple_marker_16week, abstinence_1visit,");
			ps.setString(126, bed.getAll_investigation_16week());
			ps.setString(127, bed.getSikling_16week());
			ps.setString(128, bed.getTriple_marker_16week());
			ps.setString(129, bed.getAbstinence_1visit());

			// buffer.append("barrier_contra_1visit, bed_rest_1visit,
			// book_1visit, csv_1visit, dispi_test_1visit, ");
			ps.setString(130, bed.getBarrier_contra_1visit());
			ps.setString(131, bed.getBed_rest_1visit());
			ps.setString(132, bed.getBook_1visit());
			ps.setString(133, bed.getCsv_1visit());
			ps.setString(134, bed.getDispi_test_1visit());

			// buffer.append("drug_reaction_1visit, hcg_1visit, heparin_1visit,
			// oral_hygeine_1visit, other_test_1visit, physio_diet_1visit, ");
			ps.setString(135, bed.getDrug_reaction_1visit());
			ps.setString(136, bed.getHcg_1visit());
			ps.setString(137, bed.getHeparin_1visit());
			ps.setString(138, bed.getOral_hygeine_1visit());
			ps.setString(139, bed.getOther_test_1visit());
			ps.setString(140, bed.getPhysio_diet_1visit());

			// buffer.append("rubelle_status_1visit, smart_doc_1visit,
			// stream_cell_1visit, vaginities_1visit, animally_scan_20week, ");
			ps.setString(141, bed.getRubelle_status_1visit());
			ps.setString(142, bed.getSmart_doc_1visit());
			ps.setString(143, bed.getStream_cell_1visit());
			ps.setString(144, bed.getVaginities_1visit());
			ps.setString(145, bed.getAnimally_scan_20week());

			// buffer.append("fetal_eco_20week, anti_d_24week, dipsi_24week,
			// itc_24week, investigation_sos_30week, steroids_30week, ");
			ps.setString(146, bed.getFetal_eco_20week());
			ps.setString(147, bed.getAnti_d_24week());
			ps.setString(148, bed.getDipsi_24week());
			ps.setString(149, bed.getItc_24week());
			ps.setString(150, bed.getInvestigation_sos_30week());
			ps.setString(151, bed.getSteroids_30week());

			// buffer.append("vaginities_treatment_30week,
			// breast_preparation_34week, color_doppler_34week,
			// labour_counselling_34week, nst_34week,
			// vaginities_treatment_34week ");
			ps.setString(152, bed.getVaginities_treatment_30week());
			ps.setString(153, bed.getBreast_preparation_34week());
			ps.setString(154, bed.getColor_doppler_34week());
			ps.setString(155, bed.getLabour_counselling_34week());
			ps.setString(156, bed.getNst_34week());
			ps.setString(157, bed.getVaginities_treatment_34week());

			// nst_date1, nst_date2, nst_date3, nst_date4, nst_date5, nst_date6,
			ps.setString(158, bed.getNst_date1());
			ps.setString(159, bed.getNst_date2());
			ps.setString(160, bed.getNst_date3());
			ps.setString(161, bed.getNst_date4());
			ps.setString(162, bed.getNst_date5());
			ps.setString(163, bed.getNst_date6());

			// nst_time1, nst_time2, nst_time3, nst_time4, nst_time5, nst_time6,
			ps.setString(164, bed.getNst_time1());
			ps.setString(165, bed.getNst_time2());
			ps.setString(166, bed.getNst_time3());
			ps.setString(167, bed.getNst_time4());
			ps.setString(168, bed.getNst_time5());
			ps.setString(169, bed.getNst_time6());

			// nst_indication1, nst_indication2, nst_indication3,
			// nst_indication4, nst_indication5, nst_indication6,
			ps.setString(170, bed.getNst_indication1());
			ps.setString(171, bed.getNst_indication2());
			ps.setString(172, bed.getNst_indication3());
			ps.setString(173, bed.getNst_indication4());
			ps.setString(174, bed.getNst_indication5());
			ps.setString(175, bed.getNst_indication6());

			// nst_duration1, nst_duration2, nst_duration3, nst_duration4,
			// nst_duration5, nst_duration6,
			ps.setString(176, bed.getNst_duration1());
			ps.setString(177, bed.getNst_duration2());
			ps.setString(178, bed.getNst_duration3());
			ps.setString(179, bed.getNst_duration4());
			ps.setString(180, bed.getNst_duration5());
			ps.setString(181, bed.getNst_duration6());

			// nst_interpretation1, nst_interpretation2, nst_interpretation3,
			// nst_interpretation4, nst_interpretation5, nst_interpretation6,
			ps.setString(182, bed.getNst_interpretation1());
			ps.setString(183, bed.getNst_interpretation2());
			ps.setString(184, bed.getNst_interpretation3());
			ps.setString(185, bed.getNst_interpretation4());
			ps.setString(186, bed.getNst_interpretation5());
			ps.setString(187, bed.getNst_interpretation6());

			// nst_intervention1, nst_intervention2, nst_intervention3,
			// nst_intervention4, nst_intervention5, nst_intervention6
			ps.setString(188, bed.getNst_intervention1());
			ps.setString(189, bed.getNst_intervention2());
			ps.setString(190, bed.getNst_intervention3());
			ps.setString(191, bed.getNst_intervention4());
			ps.setString(192, bed.getNst_intervention5());
			ps.setString(193, bed.getNst_intervention6());

			// tt_dose1, tt_dose2, influenza_vaccine, usgdate1, usgdate2,
			// usgdate3, usgdate4,
			ps.setString(194, bed.getTt_dose1());
			ps.setString(195, bed.getTt_dose2());
			ps.setString(196, bed.getInfluenza_vaccine());
			ps.setString(197, bed.getUsgdate1());
			ps.setString(198, bed.getUsgdate2());
			ps.setString(199, bed.getUsgdate3());
			ps.setString(200, bed.getUsgdate4());

			// amenorrhea1, amenorrhea2, amenorrhea3, amenorrhea4,
			// presentation1, presentation2, presentation3, presentation4;
			ps.setString(201, bed.getAmenorrhea1());
			ps.setString(202, bed.getAmenorrhea2());
			ps.setString(203, bed.getAmenorrhea3());
			ps.setString(204, bed.getAmenorrhea4());
			ps.setString(205, bed.getPresentation1());
			ps.setString(206, bed.getPresentation2());
			ps.setString(207, bed.getPresentation3());
			ps.setString(208, bed.getPresentation4());

			// bpdgs1, bpdgs2, bpdgs3, bpdgs4, hc1, hc2, hc3, hc4,
			ps.setString(209, bed.getBpdgs1());
			ps.setString(210, bed.getBpdgs2());
			ps.setString(211, bed.getBpdgs3());
			ps.setString(212, bed.getBpdgs4());
			ps.setString(213, bed.getHc1());
			ps.setString(214, bed.getHc2());
			ps.setString(215, bed.getHc3());
			ps.setString(216, bed.getHc4());

			// ac1, ac2, ac3, ac4, flcrl1, flcrl2, flcrl3, flcrl4;
			ps.setString(217, bed.getAc1());
			ps.setString(218, bed.getAc2());
			ps.setString(219, bed.getAc3());
			ps.setString(220, bed.getAc4());
			ps.setString(221, bed.getFlcrl1());
			ps.setString(222, bed.getFlcrl2());
			ps.setString(223, bed.getFlcrl3());
			ps.setString(224, bed.getFlcrl4());

			// ga_usg1, ga_usg2, ga_usg3, ga_usg4, liquor1, liquor2, liquor3,
			// liquor4;
			ps.setString(225, bed.getGa_usg1());
			ps.setString(226, bed.getGa_usg2());
			ps.setString(227, bed.getGa_usg3());
			ps.setString(228, bed.getGa_usg4());
			ps.setString(229, bed.getLiquor1());
			ps.setString(230, bed.getLiquor2());
			ps.setString(231, bed.getLiquor3());
			ps.setString(232, bed.getLiquor4());

			// placenta1, placenta2, placenta3, placenta4, foetal_weight1,
			// foetal_weight2, foetal_weight3, foetal_weight4;
			ps.setString(233, bed.getPlacenta1());
			ps.setString(234, bed.getLiquor2());
			ps.setString(235, bed.getLiquor3());
			ps.setString(236, bed.getLiquor4());
			ps.setString(237, bed.getFoetal_weight1());
			ps.setString(238, bed.getFoetal_weight2());
			ps.setString(239, bed.getFoetal_weight3());
			ps.setString(240, bed.getFoetal_weight4());

			// cervical_length1, cervical_length2, cervical_length3,
			// cervical_length4, nt_scan, anomaly_scan, colour_doppler_scan
			ps.setString(241, bed.getCervical_length1());
			ps.setString(242, bed.getCervical_length2());
			ps.setString(243, bed.getCervical_length3());
			ps.setString(244, bed.getCervical_length4());
			ps.setString(245, bed.getNt_scan());
			ps.setString(246, bed.getAnomaly_scan());
			ps.setString(247, bed.getColour_doppler_scan());

			// gen_condition, temp, pallor, pedal_edema, pulse, bmi,
			// height,weight, sys_bp, dia_bp, wall_edema, fundal_height
			ps.setString(248, bed.getGen_condition());
			ps.setString(249, bed.getTemp());
			ps.setString(250, bed.getPallor());
			ps.setString(251, bed.getPedal_edema());
			ps.setString(252, bed.getPulse());
			ps.setString(253, bed.getBmi());
			ps.setString(254, bed.getHeight());
			ps.setString(255, bed.getWeight());
			ps.setString(256, bed.getSys_bp());
			ps.setString(257, bed.getDia_bp());
			ps.setString(258, bed.getWall_edema());
			ps.setString(259, bed.getFundal_height());

			// cephalic, breech, baley_size, transverse_fhs, liquor,
			// uterine_contractions, ps_cervix, ps_vagine;
			ps.setString(260, bed.getCephalic());
			ps.setString(261, bed.getBreech());
			ps.setString(262, bed.getBaley_size());
			ps.setString(263, bed.getTransverse_fhs());
			ps.setString(264, bed.getLiquor());
			ps.setString(265, bed.getUterine_contractions());
			ps.setString(266, bed.getPs_cervix());
			ps.setString(267, bed.getPs_vagine());

			// ps_vault, pv_trim, pv_unettacced, pv_ant, pv_os, pv_membranes,
			// pv_tubular, pv_just_ettacced, pv_mid_posits,
			// pv_soft, pv_ettacced, pv_post;
			ps.setString(268, bed.getPs_vault());
			ps.setString(269, bed.getPv_trim());
			ps.setString(270, bed.getPv_unettacced());
			ps.setString(271, bed.getPv_ant());
			ps.setString(272, bed.getPv_os());
			ps.setString(273, bed.getPv_membranes());
			ps.setString(274, bed.getPv_tubular());
			ps.setString(275, bed.getPv_just_ettacced());
			ps.setString(276, bed.getPv_mid_posits());
			ps.setString(277, bed.getPv_soft());
			ps.setString(278, bed.getPv_ettacced());
			ps.setString(279, bed.getPv_post());
			// ps_fhs,pv_membrane,pv_station,pv_liquor,pv_pelvis,pv_position

			ps.setString(280, bed.getPs_fhs());
			ps.setString(281, bed.getPv_membrane());
			ps.setString(282, bed.getPv_station());
			ps.setString(283, bed.getPv_liquor());
			ps.setString(284, bed.getPv_pelvis());
			ps.setString(285, bed.getPv_position());
			ps.setString(286, bed.getBeats_min());

			// pmp=?, diagnosisgyn=?, le_vulva=?, le_vagina=?, pa_gynic=?,
			// plan=?, finaldiagnosis=?,
			ps.setString(287, bed.getPmp());
			ps.setString(288, bed.getDiagnosisgyn());
			ps.setString(289, bed.getLe_vulva());
			ps.setString(290, bed.getLe_vagina());
			ps.setString(291, bed.getPa_gynic());
			ps.setString(292, bed.getPlan());
			ps.setString(293, bed.getFinaldiagnosis());

			// priscription=? , pv_uterus=? , pv_uterus_size=? ,
			// pv_bl_fomices=?, pv_mobility=?
			ps.setString(294, bed.getPriscription());
			ps.setString(295, bed.getPv_uterus());
			ps.setString(296, bed.getPv_uterus_size());
			ps.setString(297, bed.getPv_bl_fomices());
			ps.setString(298, bed.getPv_mobility());

			ps.setString(299, bed.getFormtype());

			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public int saveReasonGynicVisit(Bed bed) {

		int result = 0;
		try {

			String sql = "insert into gynic_visit_reason (clientid, reason, quality, periodicity, influence_factor, since, notes, datetime,visitreason,perceives,notperceives,decreased) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?) ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, bed.getClientid());
			ps.setString(2, bed.getReason());
			ps.setString(3, bed.getQuality());
			ps.setString(4, bed.getPeriodicity());
			ps.setString(5, bed.getInfluence());
			ps.setString(6, bed.getSince());
			ps.setString(7, bed.getNotes());
			ps.setString(8, bed.getDatetime());
			ps.setString(9, bed.getReasonvisit());
			ps.setString(10, bed.getPerceives());
			ps.setString(11, bed.getNotperceives());
			ps.setString(12, bed.getDecreased());
			result = ps.executeUpdate();

			if (result > 0) {

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

	public ArrayList<Bed> getGynicVisitReasonList(String clientid, String commencing) {

		ArrayList<Bed> list = new ArrayList<Bed>();

		try {

			String todate = commencing + " 23:59:59";
			String sql = "SELECT id, reason, quality, periodicity, influence_factor, since, notes, datetime,visitreason,perceives,notperceives,decreased from gynic_visit_reason where datetime between '"
					+ commencing + "' and '" + todate + "' and clientid=" + clientid + "  ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				Bed bed = new Bed();
				bed.setId(rs.getInt(1));
				bed.setReason(rs.getString(2));
				bed.setQuality(rs.getString(3));
				bed.setPeriodicity(rs.getString(4));
				bed.setInfluence(rs.getString(5));
				bed.setSince(rs.getString(6));
				bed.setNotes(rs.getString(7));
				bed.setDatetime(rs.getString(8));
				bed.setReasonvisit(rs.getString(9));
				bed.setPerceives(rs.getString(10));
				bed.setNotperceives(rs.getString(11));
				bed.setDecreased(rs.getString(12));
				list.add(bed);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Bed> getVisitingConsultList(String ipdid, String searchText, String fromdate, String todate,
			String searchdrname, Pagination pagination) {

		UserProfileDAO profileDAO = new JDBCUserProfileDAO(connection);
		BedDao bedDao = new JDBCBedDao(connection);
		ArrayList<Bed> list = new ArrayList<Bed>();
		String sql = "";
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select ipd_visiting_consultant.id, ipd_visiting_consultant.ipdid, "
					+ "ipd_visiting_consultant.practitionerid, ipd_visiting_consultant.date, ipd_visiting_consultant.time, "
					+ "ipd_visiting_consultant.fees, ipd_visiting_consultant.clientid, ipd_visiting_consultant.status, "
					+ "ipd_visiting_consultant.payment,paid_amount,tds,apm_patient.abrivationid,"
					+ "visi_shareper,concat(apm_patient.title,' ',apm_patient.firstname,' ',apm_patient.surname),"
					+ "visi_bedid, visi_wardid,visi_cancel from ipd_visiting_consultant ");
			buffer.append("inner join apm_patient on ipd_visiting_consultant.clientid = apm_patient.id ");
			buffer.append("where date between '" + fromdate + "' and '" + todate + "' ");
			if (ipdid != null) {
				buffer.append("and ipdid='" + ipdid + "' ");
			}
			if (searchdrname != null) {
				buffer.append("and ipd_visiting_consultant.practitionerid='" + searchdrname + "' ");
			}
			if (searchText != null) {
				buffer.append("and (apm_patient.firstname like ('%" + searchText + "%') or apm_patient.surname like ('%"
						+ searchText + "%') or apm_patient.abrivationid='" + searchText + "' "
						+ " or apm_patient.fullname like ('%" + searchText + "%') ) ");
			}
			buffer.append("order by ipd_visiting_consultant.id desc ");

			/*
			 * if(ipdid!=null){ if(!ipdid.equals("")){
			 * sql="select id, ipdid, practitionerid, date, time, fees, clientid, status, payment from ipd_visiting_consultant where ipdid='"
			 * +ipdid+"'"; } else {
			 * sql="select id, ipdid, practitionerid, date, time, fees, clientid, status, payment from ipd_visiting_consultant"
			 * ; } } else {
			 * sql="select id, ipdid, practitionerid, date, time, fees, clientid, status, payment from ipd_visiting_consultant"
			 * ; }
			 */

			String query = buffer.toString();
			if (pagination != null) {
				// query=pagination.getSQLQuery(buffer.toString());
				query = pagination.getSQLQuery(query);
			}
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				Bed bed = new Bed();
				bed.setId(rs.getInt(1));
				bed.setIpdid(rs.getString(2));
				bed.setPractitionerid(rs.getString(3));
				// String
				// practitioner=profileDAO.getFullName(bed.getPractitionerid());
				String practitioner = profileDAO.getReferalDrName(bed.getPractitionerid());
				bed.setPractitionername(practitioner);
				bed.setDate(DateTimeUtils.getCommencingDate1(rs.getString(4)));
				bed.setTime(rs.getString(5));
				bed.setFees(rs.getString(6));
				bed.setClientid(rs.getString(7));

				bed.setStatus(rs.getString(8));
				bed.setPayment(rs.getString(9));
				bed.setPaid_amount(rs.getString(10));
				bed.setTds(rs.getString(11));
				bed.setAbrivationid(rs.getString(12));
				if (rs.getDouble(13) > 0) {
					bed.setSharePercent(DateTimeUtils.changeFormat(rs.getDouble(13)));
				} else {
					bed.setSharePercent(shareAmt(rs.getString(3)));
				}
				String fullname = rs.getString(14);
				bed.setClientname(fullname);
				if (rs.getInt(15) > 0 || rs.getInt(16) > 0) {
					String bedname = getIpdBedName(rs.getString(15));
					String wardname = getIpdWardName(rs.getString(16));
					bed.setWardname(wardname + "/" + bedname);
				} else {
					Bed bed2 = bedDao.getEditIpdData(bed.getIpdid());
					String wardname = getIpdWardName(bed2.getWardid());
					String bedname = getIpdBedName(bed2.getBedid());
					bed.setWardname(wardname + "/" + bedname);
				}
				bed.setCancel(rs.getInt(17));
				list.add(bed);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	private String shareAmt(String userid) {
		String res = "";
		try {
			PreparedStatement ps = connection
					.prepareStatement(" select userid from reference where id='" + userid + "' ");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				res = rs.getString(1);
				ps = connection.prepareStatement(" select vivitingper from apm_user where id='" + res + "' ");
				ResultSet rs1 = ps.executeQuery();
				while (rs1.next()) {
					res = rs1.getString(1);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<Bed> getGynicVisitReasonList(String visit_reason_ids) {
		ArrayList<Bed> list = new ArrayList<Bed>();

		try {

			String sql = "SELECT id, reason, quality, periodicity, influence_factor, since, notes, datetime,visitreason,perceives, notperceives, decreased from gynic_visit_reason where id in("
					+ visit_reason_ids + ") ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				Bed bed = new Bed();
				bed.setId(rs.getInt(1));
				bed.setReason(rs.getString(2));
				bed.setQuality(rs.getString(3));
				bed.setPeriodicity(rs.getString(4));
				bed.setInfluence(rs.getString(5));
				bed.setSince(rs.getString(6));
				bed.setNotes(rs.getString(7));
				bed.setDatetime(rs.getString(8));
				bed.setReasonvisit(rs.getString(9));
				bed.setPerceives(rs.getString(10));
				bed.setNotperceives(rs.getString(11));
				bed.setDecreased(rs.getString(12));
				list.add(bed);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return list;
	}

	public int getTotalVisitingConsultList(String ipdid, String searchtext, String fromdate, String todate,
			String searchdrname) {

		int result = 0;
		try {
			StringBuffer buffer = new StringBuffer();
			boolean flag = false;
			todate = "59:59:59";

			buffer.append("select count(*) ");
			buffer.append("from ipd_visiting_consultant ");
			buffer.append("inner join apm_patient on ipd_visiting_consultant.clientid = apm_patient.id ");
			buffer.append("where date between '" + fromdate + "' and '" + todate + "' ");
			if (ipdid != null) {
				buffer.append("and ipdid='" + ipdid + "' ");
			}
			if (searchdrname != null) {
				buffer.append("and ipd_visiting_consultant.practitionerid='" + searchdrname + "' ");
			}
			if (searchtext != null) {
				buffer.append("and (apm_patient.firstname like ('%" + searchtext + "%') or apm_patient.surname like ('%"
						+ searchtext + "%') or apm_patient.abrivationid='" + searchtext + "' "
						+ " or apm_patient.fullname like ('%" + searchtext + "%')) ");
			}

			String query = buffer.toString();
			PreparedStatement stmt = connection.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				result = rs.getInt(1);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	public int deleteFinalConditions(String sessionadmissionid, String treatmentEpisodeid) {

		int res = 0;
		try {

			String sql = "delete from apm_final_diagnosis where ipdid=" + sessionadmissionid
					+ " and treatmentepisodeid=" + treatmentEpisodeid + " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			res = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return res;
	}

	public int deleteConditionReport(String sessionadmissionid, String treatmentEpisodeid) {

		int res = 0;
		try {

			String sql = "delete from ipd_condition_report where ipdid=" + sessionadmissionid
					+ " and treatmentepisodeid=" + treatmentEpisodeid + " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			res = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return res;
	}

	public int getTotalDischargePatientCount(String wardid, String searchtext, String fromdate, String todate,
			String status, String maintypestatus, String patient_type) {

		int res = 0;
		StringBuffer sql = new StringBuffer();
		try {
			todate = todate + " 23:59:59";
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			// cal.add(Calendar.DATE, -7);
			String currDate = dateFormat.format(cal.getTime());

//			if (searchtext != null && fromdate != null && todate != null) {
//
//				sql.append("select count(*) FROM apm_ipd_bed inner join ipd_addmission_form on ");
//				sql.append("ipd_addmission_form.bedid = apm_ipd_bed.id inner join apm_treatment_episode on ");
//				sql.append("apm_treatment_episode.id = ipd_addmission_form.treatmentepisodeid ");
//				// lokesh
//				sql.append(" inner join apm_patient on apm_patient.id = ipd_addmission_form.clientid ");
//				// sql.append("where dis_initiate_status = 1 and treatmentstatus
//				// = 0
//				// and apm_treatment_episode.clientname like ('%"+searchtext+"')
//				// or
//				// apm_treatment_episode.clientname like ('"+searchtext+"%') ");
//				sql.append("where (apm_treatment_episode.clientname like ('%" + searchtext
//						+ "%') or apm_patient.abrivationid like ('%" + searchtext + "%') )");
//				// sql.append("and apm_treatment_episode.dis_initiate_time
//				// between
//				// '"+fromdate+"' and '"+todate+"' ");
//
//				if (status != null) {
//					if (!status.equals("")) {
//						if (status.equals("1")) {
//							sql.append("and dis_initiate_status = 1 ");
//							sql.append("and apm_treatment_episode.dis_initiate_time between '" + fromdate + "' and '"
//									+ todate + "' ");
//						} else if (status.equals("2")) {
//							sql.append("and dis_initiate_status = 1 ");
//							sql.append("and apm_treatment_episode.dis_form_time between '" + fromdate + "' and '"
//									+ todate + "' ");
//						} else if (status.equals("3")) {
//							sql.append("and dis_initiate_status = 1 ");
//							sql.append("and apm_treatment_episode.dis_mdicine_time between '" + fromdate + "' and '"
//									+ todate + "' ");
//						} else if (status.equals("4")) {
//							sql.append("and dis_initiate_status = 1 ");
//							sql.append("and apm_treatment_episode.dis_bill_time between '" + fromdate + "' and '"
//									+ todate + "' ");
//						} else if (status.equals("5")) {
//							sql.append("and dis_initiate_status = 1 ");
//							sql.append("and apm_treatment_episode.dis_nursing_time between '" + fromdate + "' and '"
//									+ todate + "' ");
//						} else if (status.equals("6")) {
//							sql.append("and apm_treatment_episode.dischargedate between '" + fromdate + "' and '"
//									+ todate + "' ");
//						}
//					} else {
//						sql.append("and dis_initiate_status = 1 ");
//						sql.append("and apm_treatment_episode.dis_initiate_time between '" + fromdate + "' and '"
//								+ todate + "' ");
//					}
//				} else {
//					sql.append("and dis_initiate_status = 1 ");
//					sql.append("and apm_treatment_episode.dis_initiate_time between '" + fromdate + "' and '" + todate
//							+ "' ");
//				}
//				if(!patient_type.equals("0")){
//					if(patient_type.equals("1")){
//						sql.append("and apm_patient.whopay='Client' ");
//					}else if(patient_type.equals("2")){
//						sql.append("and apm_patient.whopay='Third Party' ");
//					}
//				}
//
//			} else if (fromdate != null && todate != null) {
//
//				sql.append("select count(*) FROM apm_ipd_bed inner join ipd_addmission_form on ");
//				sql.append("ipd_addmission_form.bedid = apm_ipd_bed.id inner join apm_treatment_episode on ");
//				sql.append("apm_treatment_episode.id = ipd_addmission_form.treatmentepisodeid ");
//				sql.append(" inner join apm_patient on apm_patient.id = ipd_addmission_form.clientid ");
//				if (status != null) {
//					if (!status.equals("")) {
//						if (status.equals("1")) {
//							sql.append("where dis_initiate_status = 1 ");
//							sql.append("and apm_treatment_episode.dis_initiate_time between '" + fromdate + "' and '"
//									+ todate + "' ");
//						} else if (status.equals("2")) {
//							sql.append("where dis_initiate_status = 1 ");
//							sql.append("and apm_treatment_episode.dis_form_time between '" + fromdate + "' and '"
//									+ todate + "' ");
//						} else if (status.equals("3")) {
//							sql.append("where dis_initiate_status = 1 ");
//							sql.append("and apm_treatment_episode.dis_mdicine_time between '" + fromdate + "' and '"
//									+ todate + "' ");
//						} else if (status.equals("4")) {
//							sql.append("where dis_initiate_status = 1 ");
//							sql.append("and apm_treatment_episode.dis_bill_time between '" + fromdate + "' and '"
//									+ todate + "' ");
//						} else if (status.equals("5")) {
//							sql.append("where dis_initiate_status = 1 ");
//							sql.append("and apm_treatment_episode.dis_nursing_time between '" + fromdate + "' and '"
//									+ todate + "' ");
//						} else if (status.equals("6")) {
//							sql.append("where apm_treatment_episode.dischargedate between '" + fromdate + "' and '"
//									+ todate + "' ");
//						}
//					} else {
//						sql.append("where dis_initiate_status = 1 ");
//						sql.append("and apm_treatment_episode.dis_initiate_time between '" + fromdate + "' and '"
//								+ todate + "' ");
//					}
//					
//				} else {
//					sql.append("where dis_initiate_status = 1 ");
//					sql.append("and apm_treatment_episode.dis_initiate_time between '" + fromdate + "' and '" + todate
//							+ "' ");
//				}
//				if(!patient_type.equals("0")){
//					if(patient_type.equals("1")){
//						sql.append("and apm_patient.whopay='Client' ");
//					}else if(patient_type.equals("2")){
//						sql.append("and apm_patient.whopay='Third Party' ");
//					}
//				}
//
//			} else if (searchtext != null) {
//				sql.append("select count(*) FROM apm_ipd_bed inner join ipd_addmission_form on ");
//				sql.append("ipd_addmission_form.bedid = apm_ipd_bed.id inner join apm_treatment_episode on ");
//				sql.append("apm_treatment_episode.id = ipd_addmission_form.treatmentepisodeid ");
//				// lokesh
//				sql.append(" inner join apm_patient on apm_patient.id = ipd_addmission_form.clientid ");
//				// sql.append("where dis_initiate_status = 1 and treatmentstatus
//				// = 0
//				// and apm_treatment_episode.clientname like ('%"+searchtext+"')
//				// or
//				// apm_treatment_episode.clientname like ('"+searchtext+"%') ");
//				sql.append("where (apm_treatment_episode.clientname like ('%" + searchtext
//						+ "%') or apm_patient.abrivationid like ('%" + searchtext + "%') )");
//
//				if (status != null) {
//					if (!status.equals("")) {
//						if (status.equals("1")) {
//							sql.append("and dis_initiate_status = 1 ");
//						} else if (status.equals("2")) {
//							sql.append("and dis_initiate_status = 1 ");
//						} else if (status.equals("3")) {
//							sql.append("and dis_initiate_status = 1 ");
//						} else if (status.equals("4")) {
//							sql.append("and dis_initiate_status = 1 ");
//						} else if (status.equals("5")) {
//							sql.append("and dis_initiate_status = 1 ");
//						} else if (status.equals("6")) {
//
//						}
//					} else {
//						sql.append("and dis_initiate_status = 1 ");
//					}
//				} else {
//					sql.append("and dis_initiate_status = 1 ");
//				}
//				if(!patient_type.equals("0")){
//					if(patient_type.equals("1")){
//						sql.append("and apm_patient.whopay='Client' ");
//					}else if(patient_type.equals("2")){
//						sql.append("and apm_patient.whopay='Third Party' ");
//					}
//				}
//			} else {
//				sql.append("select count(*) FROM apm_ipd_bed inner join ipd_addmission_form on ");
//				sql.append("ipd_addmission_form.bedid = apm_ipd_bed.id inner join apm_treatment_episode on ");
//				sql.append("apm_treatment_episode.id = ipd_addmission_form.treatmentepisodeid ");
//				sql.append(" inner join apm_patient on apm_patient.id = ipd_addmission_form.clientid ");
//				// sql.append("where dis_initiate_status = 1 and treatmentstatus
//				// = 0
//				// ");
//				/* sql.append("where dis_initiate_status = 1 "); */
//
//				if (status != null) {
//					if (!status.equals("")) {
//						if (status.equals("1")) {
//							sql.append("where dis_initiate_status = 1 ");
//						} else if (status.equals("2")) {
//							sql.append("where dis_initiate_status = 1 ");
//						} else if (status.equals("3")) {
//							sql.append("where dis_initiate_status = 1 ");
//						} else if (status.equals("4")) {
//							sql.append("where dis_initiate_status = 1 ");
//						} else if (status.equals("5")) {
//							sql.append("where dis_initiate_status = 1 ");
//						} else if (status.equals("6")) {
//
//						}
//					} else {
//						sql.append("where dis_initiate_status = 1 ");
//					}
//				} else {
//					sql.append("where dis_initiate_status = 1 ");
//				}
//				if(!patient_type.equals("0")){
//					if(patient_type.equals("1")){
//						sql.append("and apm_patient.whopay='Client' ");
//					}else if(patient_type.equals("2")){
//						sql.append("and apm_patient.whopay='Third Party' ");
//					}
//				}
//			}
//			if (status != null) {
//				if (!status.equals("")) {
//					if (status.equals("1")) {
//						if (!maintypestatus.equals("1")) {
//							sql.append(
//									"and apm_treatment_episode.dis_initiate_status=1 and apm_treatment_episode.dis_form_status=0 and apm_treatment_episode.dis_mdicine_status=0 and apm_treatment_episode.dis_bill_status=0 and apm_treatment_episode.dis_nursing_status=0 ");
//						}
//					} else if (status.equals("2")) {
//						sql.append(
//								"and apm_treatment_episode.dis_initiate_status=1 and apm_treatment_episode.dis_form_status=1 and apm_treatment_episode.dis_mdicine_status=0 and apm_treatment_episode.dis_bill_status=0 and apm_treatment_episode.dis_nursing_status=0 ");
//					} else if (status.equals("3")) {
//						sql.append(
//								"and apm_treatment_episode.dis_initiate_status=1 and apm_treatment_episode.dis_form_status=1 and apm_treatment_episode.dis_mdicine_status=1 and apm_treatment_episode.dis_bill_status=0 and apm_treatment_episode.dis_nursing_status=0 ");
//					} else if (status.equals("4")) {
//						sql.append(
//								"and apm_treatment_episode.dis_initiate_status=1 and apm_treatment_episode.dis_form_status=1 and apm_treatment_episode.dis_mdicine_status=1 and apm_treatment_episode.dis_bill_status=1 and apm_treatment_episode.dis_nursing_status=0 ");
//					} else if (status.equals("5")) {
//						sql.append(
//								"and apm_treatment_episode.dis_initiate_status=1 and apm_treatment_episode.dis_form_status=1 and apm_treatment_episode.dis_mdicine_status=1 and apm_treatment_episode.dis_bill_status=1 and apm_treatment_episode.dis_nursing_status=1 ");
//					} else if (status.equals("6")) {
//						sql.append("and apm_treatment_episode.dschargestatus!=0 ");
//					}
//				}
//			}
			sql.append("SELECT count(*) ");
			sql.append("FROM apm_treatment_episode ");
			sql.append(
					"inner join ipd_addmission_form on apm_treatment_episode.id = ipd_addmission_form.treatmentepisodeid ");
			if (!maintypestatus.equals("1")) {
				sql.append("left join apm_ipd_bed on ipd_addmission_form.bedid = apm_ipd_bed.id ");
			} else {
				sql.append("inner join apm_ipd_bed on ipd_addmission_form.bedid = apm_ipd_bed.id ");
			}
			sql.append("inner join apm_patient on apm_patient.id = ipd_addmission_form.clientid ");
			sql.append("inner join apm_ipd_ward on ipd_addmission_form.wardid = apm_ipd_ward.id ");
			sql.append("inner join apm_user on apm_user.id = ipd_addmission_form.practitionerid ");
			sql.append(
					"left join apm_third_party_details on apm_third_party_details.id = apm_patient.third_party_name_id ");
			sql.append("where dis_initiate_status=1 ");
			if (!patient_type.equals("0")) {
				if (patient_type.equals("1")) {
					sql.append("and apm_patient.whopay='Client' ");
				} else if (patient_type.equals("2")) {
					sql.append("and apm_patient.whopay='Third Party' ");
				}
			}
			if (searchtext != null) {
				sql.append("and (apm_treatment_episode.clientname like ('%" + searchtext
						+ "%') or apm_patient.abrivationid like ('%" + searchtext + "%')) ");
			}

			if (fromdate != null && todate != null) {
				todate = todate + " 23:59:59";
				if (!DateTimeUtils.isNull(status).equals("")) {
					if (status.equals("1")) {
						sql.append("and apm_treatment_episode.dis_initiate_time between '" + fromdate + "' and '"
								+ todate + "' ");
					} else if (status.equals("2")) {
						sql.append("and apm_treatment_episode.dis_form_time between '" + fromdate + "' and '" + todate
								+ "' ");
					} else if (status.equals("3")) {
						sql.append("and apm_treatment_episode.dis_mdicine_time between '" + fromdate + "' and '"
								+ todate + "' ");
					} else if (status.equals("4")) {
						sql.append("and apm_treatment_episode.dis_bill_time between '" + fromdate + "' and '" + todate
								+ "' ");
					} else if (status.equals("5")) {
						sql.append("and apm_treatment_episode.dis_nursing_time between '" + fromdate + "' and '"
								+ todate + "' ");
					} else if (status.equals("6")) {
						sql.append("and apm_treatment_episode.dischargedate between '" + fromdate + "' and '" + todate
								+ "' ");
					}
				} else {
					sql.append("and (apm_treatment_episode.dis_initiate_time between '" + fromdate + "' and '" + todate
							+ "' ");
					sql.append(
							"or apm_treatment_episode.dis_form_time between '" + fromdate + "' and '" + todate + "' ");
					sql.append("or apm_treatment_episode.dis_mdicine_time between '" + fromdate + "' and '" + todate
							+ "' ");
					sql.append(
							"or apm_treatment_episode.dis_bill_time between '" + fromdate + "' and '" + todate + "' ");
					sql.append("or apm_treatment_episode.dis_nursing_time between '" + fromdate + "' and '" + todate
							+ "' ");
					sql.append(
							"or apm_treatment_episode.dischargedate between '" + fromdate + "' and '" + todate + "') ");
				}
			}

			if (!DateTimeUtils.isNull(status).equals("")) {
				if (status.equals("1")) {
					if (!maintypestatus.equals("1")) {
						sql.append("and apm_treatment_episode.dis_initiate_status=1 ");
					}
				} else if (status.equals("2")) {
					sql.append(
							"and apm_treatment_episode.dis_initiate_status=1 and apm_treatment_episode.dis_form_status=1 ");
				} else if (status.equals("3")) {
					sql.append(
							"and apm_treatment_episode.dis_initiate_status=1 and apm_treatment_episode.dis_mdicine_status=1 ");
				} else if (status.equals("4")) {
					sql.append(
							"and apm_treatment_episode.dis_initiate_status=1 and apm_treatment_episode.dis_bill_status=1 ");
				} else if (status.equals("5")) {
					sql.append(
							"and apm_treatment_episode.dis_initiate_status=1 and apm_treatment_episode.dis_nursing_status=1 ");
				} else if (status.equals("6")) {
					sql.append("and apm_treatment_episode.dschargestatus!=0 ");
				}
			}
			PreparedStatement ps = connection.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				res = rs.getInt(1);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return res;
	}

	public ArrayList<Bed> getAllIpdDischargeBedList(String searchtext, String fromdate, String todate, String status,
			Pagination pagination, LoginInfo loginInfo, String patient_type) {
		PreparedStatement preparedStatement = null;
		ArrayList<Bed> list = new ArrayList<Bed>();

		// DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// Calendar cal = Calendar.getInstance();
		// cal.add(Calendar.DATE, -2);
		// String fromDate = dateFormat.format(cal.getTime());

		// DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		// Calendar cal1 = Calendar.getInstance();
		// cal1.add(Calendar.DATE, -2);
		// String todate = dateFormat1.format(cal1.getTime());

		if (todate == null) {
			Calendar calendar = Calendar.getInstance();
			// calendar.add(Calendar.DATE, -7);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			todate = dateFormat.format(calendar.getTime());
		}

		if (fromdate == null) {
			Calendar calendar = Calendar.getInstance();
			// calendar.add(Calendar.DATE, -7);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			fromdate = dateFormat.format(calendar.getTime());
		}
		todate = todate + " 23:59:59";

		StringBuilder sql = new StringBuilder();

		/*
		 * if (searchtext != null) {
		 * 
		 * if (!searchtext.equals("")) { sql.append(
		 * "SELECT apm_treatment_episode.id,apm_treatment_episode.clientid,discharge_end_date FROM apm_treatment_episode inner join apm_patient on apm_treatment_episode.clientid=apm_patient.id where apm_treatment_episode.dischargedate between '"
		 * + fromdate + "' and '" + todate + "' and apm_patient.firstname like ('%" +
		 * searchtext + "') or apm_patient.surname like ('" + searchtext +
		 * "%')and apm_treatment_episode.clientname like ('%" + searchtext +
		 * "%') or apm_patient.abrivationid like ('%" + searchtext + "%') "); } else {
		 * sql.append(
		 * "SELECT id,clientid,discharge_end_date FROM apm_treatment_episode where dischargedate between '"
		 * + fromdate + "' and '" + todate + "' "); } } else { sql.
		 * append("SELECT id,clientid,discharge_end_date FROM apm_treatment_episode where dischargedate between '"
		 * + fromdate + "' and '" + todate + "' "); }
		 */
		sql.append(
				"SELECT apm_treatment_episode.id,apm_treatment_episode.clientid,discharge_end_date,dischargedate,treatmentstatus FROM apm_treatment_episode ");
		sql.append("inner join apm_patient on apm_treatment_episode.clientid=apm_patient.id ");
		sql.append("where apm_treatment_episode.dischargedate between '" + fromdate + "' and '" + todate + "' ");
		if (searchtext != null) {
			if (!searchtext.equals("")) {
				sql.append("and apm_patient.firstname like ('%" + searchtext + "') or apm_patient.surname like ('"
						+ searchtext + "%') and apm_treatment_episode.clientname like ('%" + searchtext
						+ "%') or apm_patient.abrivationid like ('%" + searchtext + "%') ");
			}
		}
		if (status != null) {
			if (!status.equals("")) {

				if (status.equals("1")) {
					sql.append(
							"and apm_treatment_episode.dis_initiate_status=1 and apm_treatment_episode.dis_form_status=0 and apm_treatment_episode.dis_mdicine_status=0 and apm_treatment_episode.dis_bill_status=0 and apm_treatment_episode.dis_nursing_status=0 ");
				} else if (status.equals("2")) {
					sql.append(
							"and apm_treatment_episode.dis_initiate_status=1 and apm_treatment_episode.dis_form_status=1 and apm_treatment_episode.dis_mdicine_status=0 and apm_treatment_episode.dis_bill_status=0 and apm_treatment_episode.dis_nursing_status=0 ");
				} else if (status.equals("3")) {
					sql.append(
							"and apm_treatment_episode.dis_initiate_status=1 and apm_treatment_episode.dis_form_status=1 and apm_treatment_episode.dis_mdicine_status=1 and apm_treatment_episode.dis_bill_status=0 and apm_treatment_episode.dis_nursing_status=0 ");
				} else if (status.equals("4")) {
					sql.append(
							"and apm_treatment_episode.dis_initiate_status=1 and apm_treatment_episode.dis_form_status=1 and apm_treatment_episode.dis_mdicine_status=1 and apm_treatment_episode.dis_bill_status=1 and apm_treatment_episode.dis_nursing_status=0 ");
				} else if (status.equals("5")) {
					sql.append(
							"and apm_treatment_episode.dis_initiate_status=1 and apm_treatment_episode.dis_form_status=1 and apm_treatment_episode.dis_mdicine_status=1 and apm_treatment_episode.dis_bill_status=1 and apm_treatment_episode.dis_nursing_status=1 ");
				} else if (status.equals("6")) {
					// sql.append("and
					// apm_treatment_episode.dis_initiate_status=1 and
					// apm_treatment_episode.dis_form_status=1 and
					// apm_treatment_episode.dis_mdicine_status=1 and
					// apm_treatment_episode.dis_bill_status=1 and
					// apm_treatment_episode.dis_nursing_status=1 and
					// apm_treatment_episode.dschargestatus!=0");
					sql.append("and apm_treatment_episode.treatmentstatus=1 ");
				}
			}

		}
		if (!patient_type.equals("0")) {
			if (patient_type.equals("1")) {
				sql.append("and apm_patient.whopay='Client' ");
			} else if (patient_type.equals("2")) {
				sql.append("and apm_patient.whopay='Third Party' ");
			}
		}
		sql.append(" order by apm_treatment_episode.dischargedate desc ");
		try {
			String query = sql.toString();
			if (pagination != null) {
				query = pagination.getSQLQuery(query);
			}

			preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			BedDao bedDao = new JDBCBedDao(connection);
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			while (rs.next()) {
				int treatmentepisodeid = rs.getInt(1);
				int clientid = rs.getInt(2);
				int ipdid = getTreatmentEpisodeIpdid(treatmentepisodeid);
				int bedid = getDischargeLogBedid(ipdid);

				Bed beddata = getPatientBedDetails(bedid);

				Bed bed = new Bed();
				bed.setId(beddata.getId());
				bed.setWardid(beddata.getWardid());
				bed.setSectionid(beddata.getSectionid());
				bed.setBedname(beddata.getBedname());
				String wardname = getIpdWardName(bed.getWardid());
				bed.setWardname(wardname);
				if (!DateTimeUtils.isNull(rs.getString(4)).equals("")) {
					// discharge date
					String t1[] = rs.getString(4).split(" ");
					bed.setDischargeDate(DateTimeUtils.getCommencingDate1(t1[0]) + " " + t1[1]);
				} else {
					bed.setDischargeDate("");
				}
				bed.setTreatmentstatus(rs.getInt(5));
				Bed bed3 = bedDao.getEditIpdData(Integer.toString(ipdid));
				bed.setPractitionerid(bed3.getPractitionerid());
				bed.setConditionid(bed3.getConditionid());
				bed.setTreatmentepisodeid(bed3.getTreatmentepisodeid());
				bed.setClientid(bed3.getClientid());
				bed.setAddmissionid(Integer.toString(ipdid));
				bed.setDstatus("1");
				if (loginInfo.getIpd_abbr_access() == 1) {
					String ipdabrivation = getIpdAbrivationIds(ipdid);
					bed.setNewipdabbrseq(ipdabrivation);
					bed.setIpdseqno(bed3.getIpdseqno());
				} else {
					bed.setIpdseqno(bed3.getIpdseqno());
					bed.setNewipdabbrseq(bed3.getIpdseqno());
				}
				// discharge details
				Discharge discharge = getDischargeDetails(bed.getTreatmentepisodeid());
				bed.setDis_initiate_status(discharge.getDis_initiate_status());

				if (discharge.getDis_initiate_time() != null) {
					String t1[] = discharge.getDis_initiate_time().split(" ");
					bed.setDis_initiate_date(DateTimeUtils.getCommencingDate1(t1[0]));
					bed.setDis_initiate_time(t1[1]);
				}

				if (discharge.getDis_form_time() != null) {
					String t1[] = discharge.getDis_form_time().split(" ");
					bed.setDis_form_date(DateTimeUtils.getCommencingDate1(t1[0]));
					bed.setDis_form_time(t1[1]);
				}

				if (discharge.getDis_mdicine_time() != null) {
					String t1[] = discharge.getDis_mdicine_time().split(" ");
					bed.setDis_mdicine_date(DateTimeUtils.getCommencingDate1(t1[0]));
					bed.setDis_mdicine_time(t1[1]);
				}

				if (discharge.getDis_bill_time() != null) {
					String t1[] = discharge.getDis_bill_time().split(" ");
					bed.setDis_bill_date(DateTimeUtils.getCommencingDate1(t1[0]));
					bed.setDis_bill_time(t1[1]);
				}

				if (discharge.getDis_nursing_time() != null) {
					String t1[] = discharge.getDis_nursing_time().split(" ");
					bed.setDis_nursing_date(DateTimeUtils.getCommencingDate1(t1[0]));
					bed.setDis_nursing_time(t1[1]);
				}

				bed.setDis_form_status(discharge.getDis_form_status());

				bed.setDis_mdicine_status(discharge.getDis_mdicine_status());

				bed.setDis_bill_status(discharge.getDis_bill_status());

				bed.setDis_nursing_status(discharge.getDis_nursing_status());

				Client client = clientDAO.getClientDetails(bed.getClientid());
				String clientname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();
				bed.setClientname(clientname);
				bed.setDob(client.getDob());
				bed.setAge(DateTimeUtils.getAge1(client.getDob()));
				bed.setTown(client.getTown());
				bed.setPatientIdAbrivation(client.getAbrivationid());
				bed.setWhopay(client.getWhopay());
				bed.setTpid(client.getTypeName());
				bed.setClientemail(client.getEmail());
				bed.setGender(client.getGender());

				// practitioner details

				UserProfile userProfile = userProfileDAO
						.getUserprofileDetails(Integer.parseInt(bed.getPractitionerid()));
				String practitionername = userProfile.getInitial() + " " + userProfile.getFirstname() + " "
						+ userProfile.getLastname();
				bed.setPractitionername(practitionername);
				bed.setPractitionerMob(userProfile.getMobile());
				if (rs.getString(3) == null) {
					bed.setDischarge_end_date("");
				} else {
					String t1[] = rs.getString(3).split(" ");
					bed.setDischarge_end_date(DateTimeUtils.getCommencingDate1(t1[0]) + " " + t1[1]);
				}
				list.add(bed);

			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return list;
	}

	public int getTotalDischargeCount(String searchtext, String fromdate, String todate, String status,
			String patient_type) {
		int res = 0;
		/*
		 * try {
		 * 
		 * StringBuffer sql = new StringBuffer();
		 * 
		 * DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); Calendar cal =
		 * Calendar.getInstance(); // cal.add(Calendar.DATE, -7); String currDate =
		 * dateFormat.format(cal.getTime()); String todate = currDate + " 23:59:59";
		 * sql.append("SELECT count(*) FROM apm_ipd_bed "); sql.
		 * append("inner join ipd_addmission_form on ipd_addmission_form.bedid = apm_ipd_bed.id "
		 * ); sql.
		 * append("inner join apm_treatment_episode on apm_treatment_episode.id = ipd_addmission_form.treatmentepisodeid "
		 * ); if(maintypestatus.equals("1")){
		 * sql.append("where dis_initiate_status = 1 "); }else{
		 * sql.append("where dschargestatus!='0' ");
		 * sql.append("and apm_treatment_episode.dischargedate between '"
		 * +currDate+"' and '"+todate+"' "); }
		 * 
		 * PreparedStatement ps = connection.prepareStatement(sql.toString()); ResultSet
		 * rs = ps.executeQuery();
		 * 
		 * while (rs.next()) { res = rs.getInt(1); } } catch (Exception e) {
		 * e.printStackTrace(); }
		 */

		if (todate == null) {
			Calendar calendar = Calendar.getInstance();
			/* calendar.add(Calendar.DATE, -7); */
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			todate = dateFormat.format(calendar.getTime());
		}

		if (fromdate == null) {
			Calendar calendar = Calendar.getInstance();
			/* calendar.add(Calendar.DATE, -7); */
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			fromdate = dateFormat.format(calendar.getTime());
		}
		todate = todate + " 23:59:59";

		StringBuilder sql = new StringBuilder();

		/*
		 * if (searchtext != null) {
		 * 
		 * if (!searchtext.equals("")) { sql.append(
		 * "SELECT count(*) FROM apm_treatment_episode inner join apm_patient on apm_treatment_episode.clientid=apm_patient.id where apm_treatment_episode.dischargedate between '"
		 * + fromdate + "' and '" + todate + "' and apm_patient.firstname like ('%" +
		 * searchtext + "') or apm_patient.surname like ('" + searchtext + "%') "); }
		 * else { sql.
		 * append("SELECT count(*) FROM apm_treatment_episode where dischargedate between '"
		 * + fromdate + "' and '" + todate + "' "); } } else { sql.
		 * append("SELECT count(*) FROM apm_treatment_episode where dischargedate between '"
		 * + fromdate + "' and '" + todate + "' "); }
		 */
		sql.append("SELECT count(*) FROM apm_treatment_episode ");
		sql.append("inner join apm_patient on apm_treatment_episode.clientid=apm_patient.id ");
		sql.append(" where apm_treatment_episode.dischargedate between '" + fromdate + "' and '" + todate + "' ");

		if (searchtext != null) {
			if (!searchtext.equals("")) {
				sql.append("and apm_patient.firstname like ('%" + searchtext + "') or apm_patient.surname like ('"
						+ searchtext + "%') and apm_treatment_episode.clientname like ('%" + searchtext
						+ "%') or apm_patient.abrivationid like ('%" + searchtext + "%') ");
			}
		}
		if (status != null) {
			if (!status.equals("")) {

				if (status.equals("1")) {
					sql.append(
							"and apm_treatment_episode.dis_initiate_status=1 and apm_treatment_episode.dis_form_status=0 and apm_treatment_episode.dis_mdicine_status=0 and apm_treatment_episode.dis_bill_status=0 and apm_treatment_episode.dis_nursing_status=0 ");
				} else if (status.equals("2")) {
					sql.append(
							"and apm_treatment_episode.dis_initiate_status=1 and apm_treatment_episode.dis_form_status=1 and apm_treatment_episode.dis_mdicine_status=0 and apm_treatment_episode.dis_bill_status=0 and apm_treatment_episode.dis_nursing_status=0 ");
				} else if (status.equals("3")) {
					sql.append(
							"and apm_treatment_episode.dis_initiate_status=1 and apm_treatment_episode.dis_form_status=1 and apm_treatment_episode.dis_mdicine_status=1 and apm_treatment_episode.dis_bill_status=0 and apm_treatment_episode.dis_nursing_status=0 ");
				} else if (status.equals("4")) {
					sql.append(
							"and apm_treatment_episode.dis_initiate_status=1 and apm_treatment_episode.dis_form_status=1 and apm_treatment_episode.dis_mdicine_status=1 and apm_treatment_episode.dis_bill_status=1 and apm_treatment_episode.dis_nursing_status=0 ");
				} else if (status.equals("5")) {
					sql.append(
							"and apm_treatment_episode.dis_initiate_status=1 and apm_treatment_episode.dis_form_status=1 and apm_treatment_episode.dis_mdicine_status=1 and apm_treatment_episode.dis_bill_status=1 and apm_treatment_episode.dis_nursing_status=1 ");
				} else if (status.equals("6")) {
					// sql.append("and
					// apm_treatment_episode.dis_initiate_status=1 and
					// apm_treatment_episode.dis_form_status=1 and
					// apm_treatment_episode.dis_mdicine_status=1 and
					// apm_treatment_episode.dis_bill_status=1 and
					// apm_treatment_episode.dis_nursing_status=1 and
					// apm_treatment_episode.dschargestatus!=0");
					sql.append("and apm_treatment_episode.treatmentstatus=1 ");
				}
			}

		}
		if (!patient_type.equals("0")) {
			if (patient_type.equals("1")) {
				sql.append("and apm_patient.whopay='Client' ");
			} else if (patient_type.equals("2")) {
				sql.append("and apm_patient.whopay='Third Party' ");
			}
		}

		try {

			PreparedStatement ps = connection.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				res = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	public int addnursingcareplan(Ipd ipd) {
		int result = 0;
		try {
			String query = "insert into apm_nursingcareplan(subjective, objective, diagnosis, inference, planning, intervention, rationale, evaluation,clientid,ipdid,date) values(?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, ipd.getSubjectivecare());
			ps.setString(2, ipd.getObjectivecare());
			ps.setString(3, ipd.getDiagnosiscare());
			ps.setString(4, ipd.getInferencecare());
			ps.setString(5, ipd.getPlanningcare());
			ps.setString(6, ipd.getInterventioncare());
			ps.setString(7, ipd.getRationalecare());
			ps.setString(8, ipd.getEvaluationcare());
			ps.setString(9, ipd.getClientid());
			ps.setString(10, ipd.getIpdid());
			ps.setString(11, ipd.getNursing_date());
			result = ps.executeUpdate();
			if (result > 0) {
				ResultSet resultSet2 = ps.getGeneratedKeys();
				while (resultSet2.next()) {
					result = resultSet2.getInt(1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Ipd> getallnursingdiagnosislist() {
		ArrayList<Ipd> arrayList = new ArrayList<Ipd>();
		String query = "";
		try {
			PreparedStatement stmt = connection.prepareStatement(query);
			query = "select id,name from nursing_care_diagnosis";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Ipd ipd = new Ipd();
				ipd.setId(rs.getInt(1));
				ipd.setName(rs.getString(2));
				arrayList.add(ipd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public ArrayList<Ipd> getplanninglist(String id) {

		ArrayList<Ipd> list = new ArrayList<Ipd>();
		String query = "";
		try {
			PreparedStatement stmt = connection.prepareStatement(query);

			query = "select id, diagnosisname, planningname from nursing_care_planning where diagnosisname=" + id + "";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Ipd ipd = new Ipd();
				ipd.setId(rs.getInt(1));
				ipd.setDiagnosisid(rs.getString(2));
				ipd.setPlanningname(rs.getString(3));
				list.add(ipd);
			}

			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public String diagnosisnoteslist(Ipd ipd) {
		String diagnosiscare = "";
		try {

			String query = "select name from nursing_care_diagnosis where id =" + ipd.getId() + "";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				diagnosiscare = rs.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return diagnosiscare;

	}

	public ArrayList<Ipd> getAllinterventionlist(String id) {
		ArrayList<Ipd> list = new ArrayList<Ipd>();
		String query = "";
		try {
			PreparedStatement stmt = connection.prepareStatement(query);
			query = "select id, planningid, intervention_name from nursing_care_intervention where planningid=" + id
					+ "";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Ipd ipd = new Ipd();
				ipd.setId(rs.getInt(1));
				ipd.setPlanningid(rs.getString(2));
				ipd.setIntervention_name(rs.getString(3));
				list.add(ipd);
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public String getplanningnoteslist(Ipd ipd) {
		String planningcare = "";
		try {
			String query = "select planningname from nursing_care_planning where id =" + ipd.getId() + "";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				planningcare = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return planningcare;
	}

	public String getinterventionnoteslist(Ipd ipd) {
		String interventioncare = "";
		try {
			String query = "select intervention_name from nursing_care_intervention where id =" + ipd.getId() + "";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				interventioncare = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return interventioncare;
	}

	public ArrayList<Ipd> getallrationalelist() {
		ArrayList<Ipd> list = new ArrayList<Ipd>();
		try {
			String query = "select id,name from nursing_care_rationale";
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery(query);
			while (rs.next()) {
				Ipd ipd = new Ipd();
				ipd.setId(rs.getInt(1));
				ipd.setName(rs.getString(2));
				list.add(ipd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public String getrationalecarenoteslist(Ipd ipd) {
		String rationalecare = "";

		try {
			String query = "select name from nursing_care_rationale where id =" + ipd.getId() + "";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				rationalecare = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rationalecare;

	}

	public Ipd printnursingcareplan(int result) {
		Ipd ipd = new Ipd();

		try {
			String query = "select subjective, objective, diagnosis, inference, planning, intervention, rationale, evaluation, clientid, ipdid from apm_nursingcareplan where id="
					+ result + "";
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet res = ps.executeQuery();
			while (res.next()) {

				ipd.setSubjectivecare(res.getString(1));
				ipd.setObjectivecare(res.getString(2));
				;
				ipd.setDiagnosiscare(res.getString(3));
				ipd.setInferencecare(res.getString(4));
				ipd.setPlanningcare(res.getString(5));
				ipd.setInterventioncare(res.getString(6));
				ipd.setRationalecare(res.getString(7));
				ipd.setEvaluationcare(res.getString(8));
				ipd.setClientid(res.getString(9));
				ipd.setIpdid(res.getString(10));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ipd;
	}

	public ArrayList<Master> getAllOtNotes(String selectedid) {
		ArrayList<Master> arrayList = new ArrayList<Master>();
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select his_multi_otimg.id,his_multi_otimg.otnotes from his_multi_otimg ");
			// buffer.append("inner join his_multi_otimg on his_multi_otimg.otid
			// = apm_available_slot.id ");
			buffer.append("where otid='" + selectedid + "' and issaved='0' ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setOtnotes(rs.getString(2));
				arrayList.add(master);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public ArrayList<String> getAllOTIds(String selectedid, String clientid) {
		ArrayList<String> arrayList = new ArrayList<String>();
		try {
			String sql = "select id from apm_available_slot where ipdno='" + selectedid + "' and clientId='" + clientid
					+ "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String data = rs.getString(1);
				arrayList.add(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public Boolean checkInhousePatientExists(String newdate) {
		boolean flag = false;
		try {
			String sql = "select id from daily_inhouse_patient where date='" + newdate + "'";
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

	public int saveInhousePatient(int total, String newdate, int totalbed, String ipdids) {
		int result = 0;
		try {
			String sql = "insert into daily_inhouse_patient (date,totalcount,totalbed,ipdids) values (?, ?, ?,?) ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, newdate);
			ps.setString(2, "" + total);
			ps.setString(3, "" + totalbed);
			ps.setString(4, ipdids);
			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}
	// Adarsh

	public Ipd getProcedureName(String selectedid) {

		Ipd ipd = new Ipd();

		try {
			String query = "select procedures,surgeon, anesthesia from apm_available_slot where ipdno =" + selectedid
					+ "";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				ipd.setProcedurename(rs.getString(1));
				ipd.setSurgeon(rs.getString(2));
				ipd.setAnesthesia(rs.getString(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ipd;
	}

	public String getProcedureId(String procedurename) {
		String procedureid = "";
		try {
			String query = "select id from apm_newchargetype where name='" + procedurename + "' ";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				procedureid = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return procedureid;
	}

	public int updateotnotesid(String notesid) {
		int result = 0;
		try {
			String query = "update his_multi_otimg set issaved=1 where id='" + notesid + "'";
			PreparedStatement ps = connection.prepareStatement(query);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Boolean checkPatientAdmorDis(String date) {
		boolean flag = false;
		try {
			String sql = "select id from daily_ipd_summary where date='" + date + "'";
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

	public Ipd getTotalAdmDisCount(String date, String today, String yesterday) {
		Ipd ipd = new Ipd();
		try {
			String sql = "select sum(admission),sum(discharge) from daily_ipd_summary where date between '" + date
					+ "' and '" + yesterday + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				ipd.setTotaladmission(rs.getInt(1));
				ipd.setTotaldischarge(rs.getInt(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ipd;
	}

	public int updatePatientAdmandDis(String date, int ipdnewadmission, int totaladmision, int dischargepatients,
			int totaldischarge) {
		int result = 0;
		String sql = "update daily_ipd_summary set admission=?,total_admission=?,discharge=?,total_discharge=? where date = '"
				+ date + "' ";

		try {
			PreparedStatement preparedStatement = null;
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "" + ipdnewadmission);
			preparedStatement.setString(2, "" + totaladmision);
			preparedStatement.setString(3, "" + dischargepatients);
			preparedStatement.setString(4, "" + totaldischarge);
			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int savePatientAdmandDis(String date, int ipdnewadmission, int totaladmision, int dischargepatients,
			int totaldischarge) {
		int result = 0;
		try {
			String sql = "insert into daily_ipd_summary (date,admission,total_admission,discharge,total_discharge) values (?, ?,?,?,?) ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, date);
			ps.setString(2, "" + ipdnewadmission);
			ps.setString(3, "" + totaladmision);
			ps.setString(4, "" + dischargepatients);
			ps.setString(5, "" + totaldischarge);
			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Master> getOtDatesAndIds(String selectedid, String clientid) {
		ArrayList<Master> arrayList = new ArrayList<Master>();
		try {
			String sql = "select id,commencing,procedures,discharge_procedure from apm_available_slot where ipdno='"
					+ selectedid + "' and clientId='" + clientid + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			String total = "0";
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setDate(DateTimeUtils.getCommencingDate1(rs.getString(2)));
				if (rs.getString(4) == null) {
					master.setProcedurename(rs.getString(3));
				} else {
					if (rs.getString(4).equals("<br>")) {
						master.setProcedurename(rs.getString(3));
					} else {
						master.setProcedurename(rs.getString(4));
					}
				}
				if (!rs.getString(4).equals("")) {
					total = total + "," + rs.getInt(1);
					master.setTotalids(total);
					arrayList.add(master);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public UserProfile getVisitedDrInfor(String id) {
		UserProfile userProfile = new UserProfile();
		try {
			String sql = "select fees,tds,practitionerid,visi_shareper from ipd_visiting_consultant where id='" + id
					+ "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				userProfile.setFees(String.valueOf(rs.getInt(1)));
				userProfile.setTds(rs.getString(2));
				userProfile.setDoctor(rs.getString(3));
				userProfile.setSharepercentage(rs.getString(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userProfile;
	}

	public int updateVisitingPaymentStatus(String status, String visitid, String userid, String date, String total) {
		int result = 0;

		try {
			String sql = "update ipd_visiting_consultant set payment=" + status + ",paid_amount='" + total
					+ "',paid_date='" + date + "',paid_userid='" + userid + "' where id=" + visitid + "";
			PreparedStatement ps = connection.prepareStatement(sql);

			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getVistingDrPer(String doctor) {
		String val = "0";
		try {
			String sql = "select vivitingper from apm_user inner join reference on reference.userid = apm_user.id where reference.id='"
					+ doctor + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				val = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return val;
	}

	public ArrayList<UserProfile> getSecConsWithDepartment(String selectedid) {
		UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
		ArrayList<UserProfile> list = new ArrayList<UserProfile>();
		try {
			String temp = "0";
			String sql = "select secndryconsult from ipd_addmission_form where id=" + selectedid + " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				temp = rs.getString(1);
			}

			if (temp.length() > 1) {

				for (String str : temp.split(",")) {
					if (str.equals("0")) {
						continue;
					}
					UserProfile userProfile = userProfileDAO.getUserprofileDetails(Integer.parseInt(str));
					list.add(userProfile);
				}

			} else if (!temp.equals("0")) {

				UserProfile userProfile = userProfileDAO.getUserprofileDetails(Integer.parseInt(temp));
				list.add(userProfile);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	public ArrayList<Bed> geteditBedListAfterDischarge(String bedid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Bed> list = new ArrayList<Bed>();
		String sql = "SELECT  id,bedname FROM apm_ipd_bed where id=" + bedid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Bed bed = new Bed();
				bed.setId(rs.getInt(1));
				bed.setBedname(rs.getString(2));
				list.add(bed);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int updateDischrgeOTProcedure(String editotprocedure, String id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_available_slot set discharge_procedure=? where id = " + id + " ";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, editotprocedure);
			result = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateDischrgePriscSrNo(String dicpriscmedsrno, String id, String dicpriscdose, String dicpriscdays) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_client_priscription set dispriscsrno=?,dose=?,days=? where id = " + id + " ";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, dicpriscmedsrno);
			preparedStatement.setString(2, dicpriscdose);
			preparedStatement.setString(3, dicpriscdays);
			result = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Master> getOtDatesAndIdsFromdischarge(String selectedid) {
		ArrayList<Master> arrayList = new ArrayList<Master>();
		try {
			String sql = "select id,commencing,procedures,discharge_procedure from apm_available_slot where ipdno='"
					+ selectedid + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			String total = "0";
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setDate(DateTimeUtils.getCommencingDate1(rs.getString(2)));
				if (rs.getString(4) == null) {
					master.setProcedurename(rs.getString(3));
				} else {
					/*
					 * if(rs.getString(4).equals("") || rs.getString(4).equals("<br>")){
					 * master.setProcedurename(rs.getString(3)); }else{
					 * master.setProcedurename(rs.getString(4)); }
					 */
					master.setProcedurename(rs.getString(4));
				}

				total = total + "," + rs.getInt(1);
				master.setTotalids(total);
				arrayList.add(master);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public int updatePriscChildFromIpd(String prischildid, String date, String userid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_client_priscription set isipdremove=1,ipdremovedt='" + date + "',ipdremoveuserid='"
				+ userid + "' where id = '" + prischildid + "' ";
		try {
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int insertTempIPDData(String ipdid, String clientid, String addmissionfor, String alergies,
			String packagename, String chiefcomplains, String presentillness, String onexamination, String pasthistory,
			String familyhist, String personalhist, String surgicalnotes, String suggestedtrtment, String earlierinvest,
			String diethist, String birthhist, String developmenthist, String emmunizationhist) {
		int result = 0;
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();

		try {
			sql.append(
					"insert into temp_addmission_data(ipdid, clientid ,addmissionfor,alergies,packagename,chiefcomplains,presentillness,onexamination,pasthistory,familyhist,personalhist,surgicalnotes,suggestedtrtment,earlierinvest,birthhist, diethist, developmenthist , emmunizationhist) ");
			sql.append(" values('" + ipdid + "','" + clientid + "','" + addmissionfor + "','" + alergies + "','"
					+ packagename + "','" + chiefcomplains + "','" + presentillness + "','" + onexamination + "','"
					+ pasthistory + "','" + familyhist + "','" + personalhist + "','" + surgicalnotes + "','"
					+ suggestedtrtment + "','" + earlierinvest + "','" + birthhist + "','" + diethist + "','"
					+ developmenthist + "','" + emmunizationhist + "')");
			String sql1 = sql.toString();
			ps = connection.prepareStatement(sql1);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean checkOfTempData(String ipdid, String clientid, String column) {
		PreparedStatement ps = null;
		String sql = "";
		try {
			sql = "select ipdid, clientid from temp_addmission_data where ipdid ='" + ipdid + "' and clientid= '"
					+ clientid + "' ";
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				rs.getString(1);
				rs.getString(2);

				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public int updateTempIPDData(String ipdid, String clientid, String addmissionfor, String alergies,
			String packagename, String chiefcomplains, String presentillness, String onexamination, String pasthistory,
			String familyhist, String personalhist, String surgicalnotes, String suggestedtrtment, String earlierinvest,
			String diethist, String birthhist, String developmenthist, String emmunizationhist) {
		int result = 0;
		StringBuffer sql = new StringBuffer();
		PreparedStatement ps = null;
		try {
			/*
			 * sql.append("update temp_addmission_data set addmissionfor ='" + addmissionfor
			 * + "',alergies='" + alergies + "',packagename='" + packagename +
			 * "',chiefcomplains='" + chiefcomplains + "',presentillness='" + presentillness
			 * + "',"); sql.append(" onexamination='" + onexamination + "',pasthistory='" +
			 * pasthistory + "',familyhist='" + familyhist + "',personalhist='" +
			 * personalhist + "',surgicalnotes='" + surgicalnotes + "',suggestedtrtment ='"
			 * + suggestedtrtment + "',earlierinvest='" + earlierinvest + "' ,");
			 * sql.append("  birthhist='" + birthhist + "', diethist='" + diethist +
			 * "', developmenthist='" + developmenthist + "', emmunizationhist='" +
			 * emmunizationhist + "'  ");
			 * 
			 * sql.append(" where ipdid='" + ipdid + "' and clientid= '" + clientid + "' ");
			 */

			String query = "update temp_addmission_data set addmissionfor=?,alergies=?,packagename=?,chiefcomplains=?,presentillness=?,onexamination=?,pasthistory=?,familyhist=?,";
			query = query
					+ "  personalhist=?,surgicalnotes=?,suggestedtrtment=?,earlierinvest=?,birthhist=?,diethist=?,developmenthist=?,emmunizationhist=?";
			query = query + " where ipdid='" + ipdid + "' and clientid= '" + clientid + "'  ";
			String sql1 = sql.toString();
			ps = connection.prepareStatement(query);

			ps.setString(1, addmissionfor);
			ps.setString(2, alergies);
			ps.setString(3, packagename);
			ps.setString(4, chiefcomplains);
			ps.setString(5, presentillness);
			ps.setString(6, onexamination);
			ps.setString(7, pasthistory);
			ps.setString(8, familyhist);
			ps.setString(9, personalhist);
			ps.setString(10, surgicalnotes);
			ps.setString(11, suggestedtrtment);
			ps.setString(12, earlierinvest);
			ps.setString(13, birthhist);
			ps.setString(14, diethist);
			ps.setString(15, developmenthist);
			ps.setString(16, emmunizationhist);

			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Bed getAllIPDData(String ipdid, String clientid) {
		Bed bed = new Bed();
		PreparedStatement ps = null;
		String sql = "";
		try {
			sql = "select * from temp_addmission_data where clientid='" + clientid + "' and ipdid='" + ipdid + "'";
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				bed.setAddmissionfor(rs.getString(4));
				bed.setAlergies(rs.getString(5));
				bed.setPackagename(rs.getString(6));
				bed.setChiefcomplains(rs.getString(7));
				bed.setPresentillness(rs.getString(8));
				bed.setOnexamination(rs.getString(9));
				bed.setPasthistory(rs.getString(10));
				bed.setFamily_history(rs.getString(11));
				bed.setPersonalhist(rs.getString(12));
				bed.setSurgicalnotes(rs.getString(13));
				bed.setSuggestedtrtment(rs.getString(14));
				bed.setEarlierinvest(rs.getString(15));
				bed.setBirthhist(rs.getString(23));
				bed.setDiethist(rs.getString(24));
				bed.setEmmunizationhist(rs.getString(25));
				bed.setDevelopmenthist(rs.getString(26));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return bed;
	}

	public ArrayList<Ipd> getallNursingplans(String ipdid, String date) {

		String date1[] = date.split("-");
		date = date1[2] + "/" + date1[1] + "/" + date1[0];

		ArrayList<Ipd> planlist = new ArrayList<Ipd>();

		try {
			String query = "select subjective, objective, diagnosis, inference, planning, intervention, rationale, evaluation, clientid from apm_nursingcareplan where ipdid='"
					+ ipdid + "' and date='" + date + "' ";
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet res = ps.executeQuery();
			while (res.next()) {
				Ipd ipd = new Ipd();
				ipd.setSubjectivecare(res.getString(1));
				ipd.setObjectivecare(res.getString(2));
				;
				ipd.setDiagnosiscare(res.getString(3));
				ipd.setInferencecare(res.getString(4));
				ipd.setPlanningcare(res.getString(5));
				ipd.setInterventioncare(res.getString(6));
				ipd.setRationalecare(res.getString(7));
				ipd.setEvaluationcare(res.getString(8));
				ipd.setClientid(res.getString(9));

				planlist.add(ipd);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return planlist;
	}

	public int updateprisc(String id) {
		int result = 0;
		PreparedStatement ps = null;
		try {
			String sql = "update apm_client_priscription set intreatmentgiven='1' where id ='" + id + "' ";
			ps = connection.prepareStatement(sql);

			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public int updateinvst(String id) {
		int result = 0;
		PreparedStatement ps = null;
		try {
			String sql = "update apm_client_investigation set ininvestigation ='1' where parentid ='" + id + "' ";
			ps = connection.prepareStatement(sql);

			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public int updateTempIPDDischargeData(String ipdid, String clientid, String history, String surgicalnotes,
			String hospitalcourse, String treatmentgiven, String investigation, String findondis,
			String dischargeadvice, String diethist, String birthhist, String developmenthist,
			String emmunizationhist) {
		int result = 0;
		StringBuffer sql = new StringBuffer();
		PreparedStatement ps = null;
		try {
			sql.append("update temp_addmission_data set operation_notes ='" + surgicalnotes + "',hospitalcourse='"
					+ hospitalcourse + "',treatmentgiven='" + treatmentgiven + "',investigation='" + investigation
					+ "',finddis='" + findondis + "',");
			sql.append(" discadvnotes='" + dischargeadvice + "',");
			sql.append("  birthhist='" + birthhist + "', diethist='" + diethist + "', developmenthist='"
					+ developmenthist + "', emmunizationhist='" + emmunizationhist + "'  ");

			sql.append(" where ipdid='" + ipdid + "' and clientid= '" + clientid + "' ");
			String sql1 = sql.toString();
			ps = connection.prepareStatement(sql1);
			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int insertTempIPDDischargeData(String ipdid, String clientid, String history, String surgicalnotes,
			String hospitalcourse, String treatmentgiven, String investigation, String findondis,
			String dischargeadvice, String diethist, String birthhist, String developmenthist,
			String emmunizationhist) {
		int result = 0;
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();

		try {
			sql.append(
					"insert into temp_addmission_data(ipdid, clientid ,operation_notes,hospitalcourse,treatmentgiven,investigation,finddis,discadvnotes,birthhist, diethist, developmenthist , emmunizationhist) ");
			sql.append(" values('" + ipdid + "','" + clientid + "','" + surgicalnotes + "','" + hospitalcourse + "','"
					+ treatmentgiven + "','" + investigation + "','" + findondis + "','" + dischargeadvice + "','"
					+ birthhist + "','" + diethist + "','" + developmenthist + "','" + emmunizationhist + "')");
			String sql1 = sql.toString();
			ps = connection.prepareStatement(sql1);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Bed getAllIPDDischargeData(String ipdid, String clientid) {
		Bed bed = new Bed();
		PreparedStatement ps = null;
		String sql = "";
		try {
			sql = "select operation_notes,hospitalcourse,treatmentgiven,investigation,finddis,discadvnotes,birthhist, diethist, developmenthist , emmunizationhist from temp_addmission_data where clientid='"
					+ clientid + "' and ipdid='" + ipdid + "' ";
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bed.setSurgicalnotes(rs.getString(1));
				bed.setHospitalcourse(rs.getString(2));
				bed.setTreatmentgiven(rs.getString(3));
				bed.setInvestigation(rs.getString(4));
				bed.setFindondis(rs.getString(5));
				bed.setDischargeadvice(rs.getString(6));
				bed.setBirthhist(rs.getString(7));
				bed.setDiethist(rs.getString(8));
				bed.setDevelopmenthist(rs.getString(9));
				bed.setEmmunizationhist(rs.getString(10));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return bed;
	}

	public String getIPDAdmissionDate(String ipdid) {
		String admissiondate = "";
		try {
			String sql = "select admissiondsate from ipd_addmission_form  where id='" + ipdid + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				admissiondate = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return admissiondate;
	}

	public ArrayList<Ipd> getAllRMONotes(String ipdid, long day) {
		ArrayList<Ipd> arrayList = new ArrayList<Ipd>();
		try {
			String sql = "select id, ipdid, day, notes from ipd_rmo_notes where ipdid='" + ipdid + "' and day!='" + day
					+ "' order by (day+0)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Ipd ipd = new Ipd();
				ipd.setId(rs.getInt(1));
				ipd.setIpdid(rs.getString(2));
				ipd.setDay(rs.getString(3));
				ipd.setNotes(rs.getString(4));
				arrayList.add(ipd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public Ipd getRmoNoteDay(String ipdid, long day) {
		Ipd ipd = new Ipd();
		try {
			String sql = "select id, ipdid, day, notes from ipd_rmo_notes where ipdid='" + ipdid + "' and day='" + day
					+ "' ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			ipd.setId(0);
			ipd.setNotes("");
			while (rs.next()) {
				ipd.setNotes(rs.getString(4));
				ipd.setId(rs.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ipd;
	}

	public int updateRMONotes(Ipd ipd) {
		int res = 0;
		try {
			String sql = "update ipd_rmo_notes set notes=? where ipdid='" + ipd.getIpdid() + "' and day=" + ipd.getDay()
					+ "";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, ipd.getNotes());
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int saveRMONotes(Ipd ipd) {
		int res = 0;
		try {
			String sql = "insert into ipd_rmo_notes(ipdid, day, notes,clientid,date) values(?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, ipd.getIpdid());
			preparedStatement.setString(2, ipd.getDay());
			preparedStatement.setString(3, ipd.getNotes());
			preparedStatement.setString(4, ipd.getClientid());
			preparedStatement.setString(5, ipd.getDate());
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<Ipd> getRMONotesList(String selectedid) {
		ArrayList<Ipd> arrayList = new ArrayList<Ipd>();
		try {
			String sql = "select id, ipdid, day, notes from ipd_rmo_notes where ipdid='" + selectedid
					+ "' and isindish='0' order by (day+0)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Ipd ipd = new Ipd();
				ipd.setId(rs.getInt(1));
				ipd.setIpdid(rs.getString(2));
				ipd.setDay(rs.getString(3));
				ipd.setNotes(rs.getString(4));
				arrayList.add(ipd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public int updateRMONotesDisplayed(String rmonotesid) {
		int res = 0;
		try {
			String sql = "update ipd_rmo_notes set isindish='1' where id='" + rmonotesid + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public boolean checkifSequenceExist(String cdate) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "select id from mlc_patient_seq where year = '" + cdate + "' ";

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

	public int getSqeunceNumber(String cdate) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select max(seqno) from mlc_patient_seq where year = '" + cdate + "' ";

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

	public int InserCdateSeq(String cdate, int seqno) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into mlc_patient_seq(year,seqno) values(?,?) ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, cdate);
			preparedStatement.setInt(2, seqno);
			result = preparedStatement.executeUpdate();

			if (result == 1) {
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if (resultSet.next()) {
					result = resultSet.getInt(1);
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public ArrayList<Priscription> getAdmissionPrescList(String sessionadmissionid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Priscription> list = new ArrayList<Priscription>();
		EmrDAO emrDAO = new JDBCEmrDAO(connection);
		StringBuffer sql = new StringBuffer();
		sql.append(
				"SELECT apm_client_priscription.id,mdicinename,dose,days,notes,priscdurationtype,advoice,dispriscsrno,unit,priscremark,unitextension,nurse_qty FROM apm_client_parent_priscription inner join apm_client_priscription ");
		sql.append("on apm_client_priscription.parentid = apm_client_parent_priscription.id ");
		sql.append("where ipdid = " + sessionadmissionid + " and admission=1 order by (dispriscsrno+0) asc  ");

		try {
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			String total = "0";
			int i = 0;
			boolean strengthflag = false;
			boolean quantityflag = false;
			boolean remarkflag = false;
			while (rs.next()) {
				Priscription priscription = new Priscription();
				priscription.setId(rs.getInt(1));
				priscription.setMdicinenametxt(rs.getString(2));
				priscription.setPriscdose(rs.getString(3));
				priscription.setPriscdays(rs.getString(4));
				priscription.setDosenotes(rs.getString(5));
				priscription.setPriscdurationtype(rs.getString(6));
				priscription.setMedicine_shedule("0");
				priscription.setRegional(emrDAO.getregionalText(priscription.getPriscdose()));
				total = total + "," + rs.getInt(1);
				priscription.setTotalchildmedids(total);
				priscription.setDispriscsrno("" + (++i));

				priscription.setStrength(rs.getString(9));
				priscription.setStrengthnew(priscription.getStrength());
				if (priscription.getStrengthnew().equals("0")) {
					priscription.setStrengthnew("");
				}
				priscription.setPriscindivisualremark(rs.getString(10));
				priscription.setUnitextension(rs.getString(11));
				// lokesh 04 July 2018 error while show in ipd discharge form
				if (priscription.getUnitextension() != null) {
					if (priscription.getUnitextension().equals("0")) {
						priscription.setUnitextension("");
					}
				} else {
					priscription.setUnitextension("");
				}

				if (priscription.getStrengthnew() == null) {
					priscription.setStrengthnew("");
				}

				if (!priscription.getUnitextension().equals("")) {
					quantityflag = true;
				}
				if (!priscription.getStrengthnew().equals("")) {
					strengthflag = true;
				}
				if (priscription.getPriscindivisualremark() == null) {
					priscription.setPriscindivisualremark("");
				}
				if (!priscription.getPriscindivisualremark().equals("")) {
					remarkflag = true;
				}

				priscription.setQuantityflag(quantityflag);
				priscription.setStrengthflag(strengthflag);
				priscription.setRemarkflag(remarkflag);
				priscription.setPriscqty("" + rs.getInt(12));
				list.add(priscription);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public int getipdidbyfinalDiagnosis(String finalcond) {
		PreparedStatement ps = null;
		int res = 0;
		try {

			String sql = "select ipdid from apm_final_diagnosis where FIND_IN_SET('" + finalcond
					+ "',conditionid) order by id desc limit 1";
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

	public int getsecondLastIpdid(String clientid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT id FROM ipd_addmission_form where clientid = " + clientid + " order by id desc limit 2 ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			int count = 0;
			while (rs.next()) {
				result = rs.getInt(1);
				++count;
			}
			if (count != 2) {
				result = 0;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public int settoShiftedFromIpd(int ipdid, String lastopdno) {
		int res = 0;
		PreparedStatement ps = null;
		try {
			String sql = "update ipd_addmission_form set opdtoipd=1,lastopdno='" + lastopdno + "' where id='" + ipdid
					+ "'";
			ps = connection.prepareStatement(sql);
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int setfinalcond(String ipdid, String tpid, String finaldia) {
		int res = 0;
		PreparedStatement ps = null;
		try {

			String x = getcurrentdia(ipdid, tpid);

			if (x == null) {
				insertfinaldia(ipdid, tpid, finaldia);
			}
			x = getcurrentdia(ipdid, tpid);
			if (x != null && finaldia != null) {
				for (String d : x.split(",")) {
					if (finaldia.equals(d)) {
						return 0;
					}
				}
			}
			x = x + "," + finaldia;

			String sql = "update apm_final_diagnosis set conditionid='" + x + "'  where ipdid='" + ipdid
					+ "' and treatmentepisodeid='" + tpid + "'  ";
			ps = connection.prepareStatement(sql);
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	private String getcurrentdia(String ipdid, String tpid) {
		String res = null;
		String sql = "select conditionid from apm_final_diagnosis where ipdid='" + ipdid + "' and treatmentepisodeid='"
				+ tpid + "'";
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				res = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	private int insertfinaldia(String ipdid, String tpid, String finaldia) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String date = dateFormat.format(cal.getTime());
		int i = 0;
		String sql = "insert into apm_final_diagnosis(ipdid,treatmentepisodeid,conditionid,datetime) values(?,?,?,?) ";
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, ipdid);
			ps.setString(2, tpid);
			ps.setString(3, "0," + finaldia);
			ps.setString(4, date);
			i = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return i;
	}

	public boolean checkWardWiseDataStored(String newdate) {
		boolean flag = false;
		try {
			String sql = "select id from daily_wardwise_patient where date='" + newdate + "'";
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

	public boolean checkPractWiseDataStored(String newdate) {
		boolean flag = false;
		try {
			String sql = "select id from daily_practitionerwise_patient where date='" + newdate + "'";
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

	public ArrayList<String> getWardListFromIpdids(String ipdids) {
		PreparedStatement preparedStatement = null;
		ArrayList<String> arrayList = new ArrayList<String>();
		try {
			if (DateTimeUtils.isNull(ipdids).equals("")) {
				ipdids = "0";
			}
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT distinct wardid FROM ipd_addmission_form where id in (" + ipdids
					+ ") and wardid is not null  ");

			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			int i = 0;
			while (rs.next()) {
				arrayList.add(rs.getString(1));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public int saveInhousePatientWardWise(int total, String newdate, int totalbed, String ipdwardwiseids,
			String wardid) {
		int result = 0;
		try {
			String sql = "insert into daily_wardwise_patient (date,count,totalbed,ipdids,wardiid) values (?, ?, ?,?,?) ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, newdate);
			ps.setString(2, "" + total);
			ps.setString(3, "" + totalbed);
			ps.setString(4, ipdwardwiseids);
			ps.setString(5, wardid);
			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<String> getPractListFromIpdids(String ipdids) {
		PreparedStatement preparedStatement = null;
		ArrayList<String> arrayList = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		try {
			if (DateTimeUtils.isNull(ipdids).equals("")) {
				ipdids = "0";
			}
			sql.append("select distinct practitionerid from ipd_addmission_form where id in (" + ipdids + ") ");
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			int i = 0;
			while (rs.next()) {
				arrayList.add(rs.getString(1));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public int saveInhousePatientPractWise(int total, String newdate, String ipdwardwiseids) {
		int result = 0;
		try {
			String sql = "insert into daily_practitionerwise_patient (date,count,ipdids) values (?, ?,?) ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, newdate);
			ps.setString(2, "" + total);
			ps.setString(3, ipdwardwiseids);
			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	public String getIpdidByClient(String ipdid) {
		String ipdidbyclient = "";
		try {
			String sql = "select clientid from ipd_addmission_form where id='" + ipdid + "'";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ipdidbyclient = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ipdidbyclient;
	}

	private String getUserTableid(String userid) {
		String id = "";
		PreparedStatement ps = null;
		try {
			String sql = "select id from apm_user where userid='" + userid + "'";
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getString(1);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	public String getPractidbyipd(String ipdid) {
		PreparedStatement ps = null;
		String result = "";
		try {
			String sql = "select practitionerid from ipd_addmission_form where id='" + ipdid + "' ";
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result = rs.getString(1);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	public String getipdseqno(String ipdid) {
		PreparedStatement ps = null;
		String result = "";
		try {
			String sql = "select ipdseqno from ipd_addmission_form where id='" + ipdid + "' ";
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result = rs.getString(1);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	public ArrayList<Ipd> getAllIpdList(String clientId) {
		ArrayList<Ipd> list = new ArrayList<Ipd>();
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext()
				.get(ServletActionContext.HTTP_REQUEST);
		LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		try {
			String sql = "select id,ipdseqno,ipdabrivationid from ipd_addmission_form where clientid='" + clientId
					+ "' ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Ipd ipd = new Ipd();
				ipd.setIpdid(rs.getString(1));
				if (loginInfo.getIpd_abbr_access() == 1) {
					ipd.setIpdseqno(rs.getString(3));
				} else {
					ipd.setIpdseqno(rs.getString(2));
				}
				list.add(ipd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int gettreatmentstatus(String treatmentepisodeid) {
		int stat = 0;
		String sql = "select treatmentstatus from apm_treatment_episode where id=" + treatmentepisodeid + " ";

		ResultSet rs;
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				stat = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}

		return stat;
	}

	public String generateIPDSequenceNewFormat(String action) {
		if (action == null) {
			action = "0";
		} else if (action.equals("")) {
			action = "0";
		}
		String ipdabrfinal = "";
		// if action =1 then casualty else ipd
		DateFormat dateFormat = new SimpleDateFormat("yyyy");
		Calendar cal = Calendar.getInstance();
		String year = dateFormat.format(cal.getTime());

		String yearRange = DateTimeUtils.getFinancialYearRange();
		int maxipdabr = getMaxIPDAbrivation(yearRange, action);
		int yr = Integer.parseInt(year) % 1000;

		int newmaxipdabr = maxipdabr + 1;
		String temp = String.valueOf(newmaxipdabr);
		int l = temp.length();
		String ipdabrstr = "";
		if (l == 3) {
			ipdabrstr = "0" + newmaxipdabr;
		} else if (l == 2) {
			ipdabrstr = "00" + newmaxipdabr;
		} else if (l == 1) {
			ipdabrstr = "000" + newmaxipdabr;
		} else {
			ipdabrstr = "" + newmaxipdabr;
		}
		String prefix = "IP";
		if (DateTimeUtils.isNull(action).equals("2")) {
			prefix = "DC";
		} else if (DateTimeUtils.isNull(action).equals("1")) {
			prefix = "CS";
		}
		/*
		 * String abrivaationcode = getclinicAbrivationCode(); ipdabrfinal =
		 * abrivaationcode +prefix+ "/" + yearRange + "/" + ipdabrstr;
		 */

		String[] data = yearRange.split("-");
		String[] data1 = data[0].split("");
		String[] data2 = data[1].split("");
		String value = data1[2] + "" + data1[3] + "-" + data2[2] + "" + data2[3];
		ipdabrfinal = prefix + "" + value + "/" + ipdabrstr;

		int res = insertIPDAbrivation(yearRange, newmaxipdabr, action);
		return ipdabrfinal;
	}

	public int getMaxIPDAbrivation(String date, String action) {
		int maxid = 0;
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement(" select max(seqno) from ipd_sequence_numbers where date='" + date
							+ "'  and action ='" + action + "'");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				maxid = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return maxid;
	}

	public int insertIPDAbrivation(String date, int seqno, String action) {
		int res = 0;
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(" insert into ipd_sequence_numbers(date, seqno,action) values(?,?,?)");
			ps.setString(1, date);
			ps.setInt(2, seqno);
			ps.setString(3, action);
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public String getclinicAbrivationCode() {
		String code = "";
		try {
			PreparedStatement ps = connection.prepareStatement(" select abrivation from apm_user where id =1 ");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				code = rs.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return code;
	}

	public String getIpdAbrivationIds(int id) {
		String result = "";
		try {
			PreparedStatement ps = connection
					.prepareStatement("select ipdabrivationid from ipd_addmission_form_new where id =" + id + "");  //slow open change table name @ manoj sir ipd_addmission_form to ipd_addmission_form_new
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;

	}

	public String getIpdAbrivationIdsByClientid(int clientid) {
		String result = "";
		try {
			PreparedStatement ps = connection.prepareStatement(
					"select ipdabrivationid from ipd_addmission_form where clientid=" + clientid + "");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;

	}

	public int updateAdmissionDate(int ipdid, String date) {
		int res = 0;
		try {
			PreparedStatement ps = connection.prepareStatement(
					"update ipd_addmission_form set admissiondsate='" + date + "' where id='" + ipdid + "' ");
			res = ps.executeUpdate();

			BedDao bedDao = new JDBCBedDao(connection);
			bedDao.updateAdmissionFormInParent(ipdid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<PackageMaster> getappliedpakgeList(String parentid) {
		ArrayList<PackageMaster> list = new ArrayList<PackageMaster>();
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append(" select id,chargename,cal_amount from apm_child_patient_package where parentid='" + parentid
					+ "' ");
			buffer.append("  ");
			PreparedStatement ps = connection.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				PackageMaster master = new PackageMaster();
				master.setAmount(rs.getString(3));
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				list.add(master);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public Master getParentPackageAppliedData(String id) {
		PreparedStatement ps = null;
		Master m1 = new Master();
		try {
			ps = connection.prepareStatement(
					"select packageid, amount,fromdate,todate from apm_parent_patient_package where id='" + id + "'");

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				m1.setAmmount(rs.getString(2));
				if (rs.getString(3) != null) {
					if (!rs.getString(3).equals("")) {
						String fdt[] = rs.getString(3).split(" ");
						m1.setFdate(DateTimeUtils.getCommencingDate1(fdt[0]) + " " + fdt[1]);

					}
				}
				if (rs.getString(4) != null) {
					if (!rs.getString(4).equals("")) {
						String fdt[] = rs.getString(4).split(" ");
						m1.setTdate(DateTimeUtils.getCommencingDate1(fdt[0]) + " " + fdt[1]);

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return m1;
	}

	public int updatePatientpkg(int id, String amount, String name) {
		try {
			PreparedStatement ps = connection.prepareStatement("update apm_child_patient_package set cal_amount='"
					+ amount + "' , chargename=? where id='" + id + "'");
			ps.setString(1, name);
			int x = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int updateParentPkgAmt(String id, String amt, String fromdate, String todate) {
		try {
			PreparedStatement ps = connection.prepareStatement("update apm_parent_patient_package set amount='" + amt
					+ "',fromdate='" + fromdate + "' ,todate='" + todate + "' where id='" + id + "'");
			int res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public String getDischargeChecklistDataText(String treamentepisodeid, String clientid, String type,
			Discharge discharge) {
		StringBuffer buffer = new StringBuffer();
		StringBuffer builder = new StringBuffer();
		PreparedStatement ps = null;
		try {
			builder.append(
					"select id, clientid, ipdid, dis_stepsid, dis_cklid, treatmentid, executed, userid, userdatetime, remark, status from dischargeckldata ");
			builder.append("where treatmentid='" + treamentepisodeid + "' ");
			if (type != null) {
				builder.append("and dis_stepsid='" + type + "' ");
			}
			ps = connection.prepareStatement(builder.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (type.equals("1")) {
					if (rs.getInt(7) == 0) {
						buffer.append(
								"<li class='setlimet'><label class='checkbox checkbox-custom-alt'><input class='akashaclass"
										+ treamentepisodeid + "' id='discklnameid" + rs.getInt(1) + "' value='"
										+ rs.getInt(1) + "' type='checkbox'><i></i>"
										+ getDisCheckListName(rs.getString(5)) + "</label></li>");
					} else {
						buffer.append(
								"<li class='setlimet'><label class='checkbox checkbox-custom-alt'><input class='akashaclass"
										+ treamentepisodeid + "' id='discklnameid" + rs.getInt(1) + "' value='"
										+ rs.getInt(1) + "' type='checkbox' checked><i></i>"
										+ getDisCheckListName(rs.getString(5)) + "</label></li>");
					}
				} else if (type.equals("2")) {
					if (discharge.getDis_mdicine_status().equals("0")) {
						if (rs.getInt(7) == 0) {
							buffer.append(
									"<li class='setlimet'><label class='checkbox checkbox-custom-alt'><input class='akashbclass"
											+ treamentepisodeid + "' id='discklnameid" + rs.getInt(1) + "' value='"
											+ rs.getInt(1) + "' onclick='updateDischargeCheckListStatus(" + rs.getInt(1)
											+ ",this.value)' type='checkbox'><i></i>"
											+ getDisCheckListName(rs.getString(5)) + "</label></li>");
						} else {
							buffer.append(
									"<li class='setlimet'><label class='checkbox checkbox-custom-alt'><input class='akashbclass"
											+ treamentepisodeid + "' id='discklnameid" + rs.getInt(1) + "' value='"
											+ rs.getInt(1) + "' onclick='updateDischargeCheckListStatus(" + rs.getInt(1)
											+ ",this.value)' type='checkbox' checked><i></i>"
											+ getDisCheckListName(rs.getString(5)) + "</label></li>");
						}
					} else {
						if (rs.getInt(7) == 0) {
							buffer.append(
									"<li class='setlimet'><label class='checkbox checkbox-custom-alt'><input class='akashbclass"
											+ treamentepisodeid + "' id='discklnameid" + rs.getInt(1) + "' value='"
											+ rs.getInt(1) + "'  type='checkbox'><i></i>"
											+ getDisCheckListName(rs.getString(5)) + "</label></li>");
						} else {
							buffer.append(
									"<li class='setlimet'><label class='checkbox checkbox-custom-alt'><input class='akashbclass"
											+ treamentepisodeid + "' id='discklnameid" + rs.getInt(1) + "' value='"
											+ rs.getInt(1) + "'  type='checkbox' checked ><i></i>"
											+ getDisCheckListName(rs.getString(5)) + "</label></li>");
						}

					}
				} else if (type.equals("3")) {
					if (discharge.getDis_bill_status().equals("0")) {
						if (rs.getInt(7) == 0) {
							buffer.append(
									"<li class='setlimet'><label class='checkbox checkbox-custom-alt'><input class='akashcclass"
											+ treamentepisodeid + "' id='discklnameid" + rs.getInt(1) + "' value='"
											+ rs.getInt(1) + "' onclick='updateDischargeCheckListStatus(" + rs.getInt(1)
											+ ",this.value)' type='checkbox'><i></i>"
											+ getDisCheckListName(rs.getString(5)) + "</label></li>");
						} else {
							buffer.append(
									"<li class='setlimet'><label class='checkbox checkbox-custom-alt'><input class='akashcclass"
											+ treamentepisodeid + "' id='discklnameid" + rs.getInt(1) + "' value='"
											+ rs.getInt(1) + "' onclick='updateDischargeCheckListStatus(" + rs.getInt(1)
											+ ",this.value)' type='checkbox' checked><i></i>"
											+ getDisCheckListName(rs.getString(5)) + "</label></li>");
						}
					} else {
						if (rs.getInt(7) == 0) {
							buffer.append(
									"<li class='setlimet'><label class='checkbox checkbox-custom-alt'><input class='akashcclass"
											+ treamentepisodeid + "' id='discklnameid" + rs.getInt(1) + "' value='"
											+ rs.getInt(1) + "' type='checkbox'><i></i>"
											+ getDisCheckListName(rs.getString(5)) + "</label></li>");
						} else {
							buffer.append(
									"<li class='setlimet'><label class='checkbox checkbox-custom-alt'><input class='akashcclass"
											+ treamentepisodeid + "' id='discklnameid" + rs.getInt(1) + "' value='"
											+ rs.getInt(1) + "' type='checkbox' checked><i></i>"
											+ getDisCheckListName(rs.getString(5)) + "</label></li>");
						}
					}
				} else if (type.equals("4")) {
					if (discharge.getDis_nursing_status().equals("0")) {
						if (rs.getInt(7) == 0) {
							buffer.append(
									"<li class='setlimet'><label class='checkbox checkbox-custom-alt'><input class='akashdclass"
											+ treamentepisodeid + "' id='discklnameid" + rs.getInt(1) + "' value='"
											+ rs.getInt(1) + "' onclick='updateDischargeCheckListStatus(" + rs.getInt(1)
											+ ",this.value)' type='checkbox'><i></i>"
											+ getDisCheckListName(rs.getString(5)) + "</label></li>");
						} else {
							buffer.append(
									"<li class='setlimet'><label class='checkbox checkbox-custom-alt'><input class='akashdclass"
											+ treamentepisodeid + "' id='discklnameid" + rs.getInt(1) + "' value='"
											+ rs.getInt(1) + "' onclick='updateDischargeCheckListStatus(" + rs.getInt(1)
											+ ",this.value)' type='checkbox' checked><i></i>"
											+ getDisCheckListName(rs.getString(5)) + "</label></li>");
						}
					} else {
						if (rs.getInt(7) == 0) {
							buffer.append(
									"<li class='setlimet'><label class='checkbox checkbox-custom-alt'><input class='akashdclass"
											+ treamentepisodeid + "' id='discklnameid" + rs.getInt(1) + "' value='"
											+ rs.getInt(1) + "' type='checkbox'><i></i>"
											+ getDisCheckListName(rs.getString(5)) + "</label></li>");
						} else {
							buffer.append(
									"<li class='setlimet'><label class='checkbox checkbox-custom-alt'><input class='akashdclass"
											+ treamentepisodeid + "' id='discklnameid" + rs.getInt(1) + "' value='"
											+ rs.getInt(1) + "' type='checkbox' checked><i></i>"
											+ getDisCheckListName(rs.getString(5)) + "</label></li>");
						}
					}
				}
			}
			buffer.append(" ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}

	public int updatePkgInAssessment(String fromdate, String todate, String ipdid, String pkgid, String mstercharge) {
		int res = 0;
		String ms[] = mstercharge.split("[(]");
		String name = ms[0];
		String dt[] = fromdate.split(" ");
		String tdt[] = todate.split(" ");
		dt[0] = DateTimeUtils.getCommencingDate1(dt[0]);
		tdt[0] = DateTimeUtils.getCommencingDate1(tdt[0]);

		try {
			String mastercharge = name + " (" + dt[0] + " - " + tdt[0] + ")";
			PreparedStatement ps = connection.prepareStatement(
					" update apm_invoice_assesments set masterchargetype=? ,tpfdate=?, tptodate=? where ipdid='" + ipdid
							+ "' and tpkg='" + pkgid + "'  ");
			ps.setString(1, mastercharge);
			ps.setString(2, fromdate);
			ps.setString(3, todate);
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public String getMasterchargeAss(String fromdate, String todate, String ipdid, String parentid) {
		String res = "";
		try {
			String sql = "select masterchargetype from  apm_invoice_assesments where ipdid='" + ipdid + "' and tpkg='"
					+ parentid + "'";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				res = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<Master> getPatientPackageListByClientid(String clientId) {
		PreparedStatement preparedStatement = null;

		ArrayList<Master> list = new ArrayList<Master>();
		StringBuffer sql = new StringBuffer();
		sql.append(
				"SELECT packageid, name,apm_parent_patient_package.id,apm_parent_patient_package.fromdate, apm_parent_patient_package.todate FROM apm_parent_patient_package inner join his_parent_package on ");
		sql.append("his_parent_package.id = apm_parent_patient_package.packageid ");
		sql.append("where clientid = " + clientId + " ");

		try {
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				master.setMasterid(rs.getInt(3));
				String fdate[] = rs.getString(4).split(" ");
				String fromdate = DateTimeUtils.getCommencingDate1(fdate[0]) + " " + fdate[1];
				String tdate[] = rs.getString(5).split(" ");
				String todate = DateTimeUtils.getCommencingDate1(tdate[0]) + " " + tdate[1];
				master.setName(rs.getString(2) + " (" + fromdate + " To " + todate + ") ");
				list.add(master);
			}

		} catch (Exception e) {
			// TODmO: handle exception
		}
		return list;
	}

	public int saveIpdTemplates(String id, String text, String name) {
		int i = 0;
		try {
			PreparedStatement ps = connection.prepareStatement(
					" insert into ipd_template(template_nameid,text,name,department,speciality,showall) values(?,?,?,'1','0','0')  ");
			ps.setString(1, id);
			ps.setString(2, text);
			ps.setString(3, name);
			i = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	public String getClientIdByIpdSeqno(String ipdseqno) {
		String result = "";
		try {
			PreparedStatement ps = connection
					.prepareStatement("select clientid from ipd_addmission_form where ipdseqno='" + ipdseqno + "'");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Bed getIpdDetails(String ipdid) {
		Bed bed = new Bed();
		try {
			String sql = "select practitionerid,wardid,bedid from ipd_addmission_form where id='" + ipdid + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				bed.setPractitionerid("" + rs.getInt(1));
				bed.setWardid("" + rs.getInt(2));
				bed.setBedid("" + rs.getInt(3));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return bed;
	}

	public String getLAstIpdIdByClient(String client) {
		int i = 0;
		try {
			PreparedStatement ps = connection.prepareStatement(
					"select id from ipd_addmission_form where clientid ='" + client + "' order by id desc limit 1 ");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				i = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "" + i;
	}

	public int updateDischargeBedReadmit(String selectedbedid, String wardid, String ipdid) {

		int result = 0;
		try {
			String sql = "update ipd_addmission_form set bedid=" + selectedbedid + ",wardid=" + wardid + " where id="
					+ ipdid + " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			result = ps.executeUpdate();

			BedDao bedDao = new JDBCBedDao(connection);
			bedDao.updateAdmissionFormInParent(DateTimeUtils.convertToInteger(ipdid));
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	public void rateChangeFlagWard(String ipdid, String flag) {
		try {
			PreparedStatement ps = connection.prepareStatement(
					"  update ipd_addmission_form set ward_change_rate='" + flag + "' where id='" + ipdid + "'");
			int res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void rateChangeWardFromMaster(String wardid, String bedid) {
		try {
			PreparedStatement ps = connection
					.prepareStatement("  update apm_ipd_bed set wardid='" + wardid + "' where id='" + bedid + "'");
			int res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String getWardidofPat(String ipdid) {
		String wardid = "";
		return null;
	}

	public int saveInvestigationOfDischargeForm(Bed bed) {
		int x = 0;
		try {
			PreparedStatement ps = connection.prepareStatement(
					"update apm_treatment_episode set investigation =? where id=" + bed.getTreatmentepisodeid() + "");
			ps.setString(1, bed.getInvestigation());
			x = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return x;
	}

	public int saveEmergencyDetailsOfDischargeForm(Bed bed) {
		int x = 0;
		try {
			PreparedStatement ps = connection.prepareStatement(
					"update apm_treatment_episode set emergencydetail =? where id=" + bed.getTreatmentepisodeid() + "");
			ps.setString(1, bed.getEmergencydetail());
			x = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return x;
	}

	public int saveOTHERDivDataOfDischargeForm(Bed bed) {
		int x = 0;
		try {
			PreparedStatement ps = connection.prepareStatement(
					"update apm_treatment_episode set findingondischarge =?,disadvnotes=?,dischargedate=?,dis_form_status=?,dis_form_time=?  where id="
							+ bed.getTreatmentepisodeid() + "");
			ps.setString(1, bed.getFindondischarge());
			ps.setString(2, bed.getDiscadvnotes());
			ps.setString(3, bed.getDischargeDate());
			ps.setString(4, "1");
			ps.setString(5, bed.getDischargeDate());
			x = ps.executeUpdate();
			PreparedStatement ps2 = connection.prepareStatement(
					"update ipd_addmission_form set kunal_manual_medicine_text=?,reasonlamadama=? where id='"
							+ bed.getIpdnewid() + "' ");
			ps2.setString(1, bed.getKunal_manual_medicine_text());
			ps2.setString(2, DateTimeUtils.removeBreaks(bed.getReasonlamadama()));
			x = ps2.executeUpdate();

			BedDao bedDao = new JDBCBedDao(connection);
			bedDao.updateDischargeForm(bed.getTreatmentepisodeid());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return x;
	}

	public int saveTearmentGivenDivDataOfDischargeForm(Bed bed) {

		int x = 0;
		try {
			PreparedStatement ps = connection.prepareStatement(
					"update apm_treatment_episode set treatmentgiven =? where id=" + bed.getTreatmentepisodeid() + "");
			ps.setString(1, bed.getTreatmentgiven());
			x = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return x;
		// TODO Auto-generated method stub

	}

	public int saveHospitalCourseDivDataOfDischargeForm(Bed bed) {

		int x = 0;
		try {
			PreparedStatement ps = connection.prepareStatement(
					"update apm_treatment_episode set hospcourse =? where id=" + bed.getTreatmentepisodeid() + "");
			ps.setString(1, bed.getHospitalcourse());
			x = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return x;
		// TODO Auto-generated method stub

	}

	public int saveHistoryDivDataOfDischargeForm(Bed bed) {
		int x = 0;
		try {
			// familyhist,personalhist,earlierinvest,suggestedtrtment,suggestedtrtment,surgicalnotes,birthhist,diethist,developmenthist,emmunizationhist

			String sql = "update ipd_addmission_form set familyhist=?,personalhist=?,early_investigation=?,suggestedtrtment=?,surgicalnote=?, birthhist=?,diethist=?,developmenthist=?,emmunizationhist=?,onexamination=?,pasthistory=? where id="
					+ bed.getIpdnewid() + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, bed.getFamilyhist());
			ps.setString(2, bed.getPersonalhist());
			ps.setString(3, bed.getEarlierinvest());
			ps.setString(4, bed.getSuggestedtrtment());
			ps.setString(5, bed.getSurgicalnotes());
			ps.setString(6, bed.getBirthhist());
			ps.setString(7, bed.getDiethist());
			ps.setString(8, bed.getDevelopmenthist());
			ps.setString(9, bed.getEmmunizationhist());
			ps.setString(10, bed.getOnexamination());
			ps.setString(11, bed.getPasthistory());
			x = ps.executeUpdate();
			/* example,onexamination,otNotes */
			PreparedStatement ps2 = connection.prepareStatement(
					"update apm_treatment_episode set dis_extra_note=? where id='" + bed.getTreatmentepisodeid() + "'");
			ps2.setString(1, bed.getExample());
			x = ps2.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return x;
	}

	public int saveAdmissionDataOfDischForm(Bed bed) {
		int x = 0;
		try {
			// chiefcomplains,alergies,presentillness,headcircumference.midarmcircumference,length,wtaddmission,wtdischarge

			String sql = "update ipd_addmission_form set chiefcomplains=?,allergies=?,presentillness=?,head_circumference=?,mid_arm_circumference=?, length=?,wt_addmission=?,wt_discharge=? where id="
					+ bed.getIpdnewid() + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, bed.getChiefcomplains());
			ps.setString(2, bed.getAlergies());
			ps.setString(3, bed.getPresentillness());
			ps.setString(4, bed.getHeadcircumference());
			ps.setString(5, bed.getMidarmcircumference());
			ps.setString(6, bed.getLength());
			ps.setString(7, bed.getWtaddmission());
			ps.setString(8, bed.getWtdischarge());

			x = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return x;
	}

	public int saveSurgicalNotesOfDiv(Bed bed) {
		int x = 0;
		try {
			PreparedStatement ps = connection.prepareStatement(
					" update apm_treatment_episode set otNotes=?,surgeon=?,anesthesiologist=?,anesthesia=?,operative_procedure=? where id='"
							+ bed.getTreatmentepisodeid() + "'");
			ps.setString(1, bed.getOtNotes());
			ps.setString(2, bed.getSurgeon());
			ps.setString(3, bed.getAnesthesiologist());
			ps.setString(4, bed.getAnesthesia());
			ps.setString(5, bed.getAppointmentText());
			x = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return x;
	}

	public boolean checkAlreadyAdmit(String clientid) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "SELECT sum(bedid) FROM ipd_addmission_form where clientid = " + clientid + " ";

		try {

			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				int res = rs.getInt(1);
				if (res > 0) {
					result = true;
				} else {
					result = false;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Bed> getAllIpdidsOfDiagnosis(String diagnosis) {
		ArrayList<Bed> bedlist = new ArrayList<Bed>();
		try {
			String sql = "select ipdid from apm_final_diagnosis where FIND_IN_SET('" + diagnosis
					+ "',conditionid) order by id desc limit 5";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			BedDao bedDao = new JDBCBedDao(connection);
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			while (rs.next()) {
				Bed bed = new Bed();
				bed = bedDao.getEditIpdData(rs.getString(1));
				Client client = clientDAO.getClientDetails(bed.getClientid());
				bed.setGender(client.getGender());
				bed.setAge(client.getAge1());
				Bed bed1 = getDischargeData(bed.getTreatmentepisodeid());
				bed.setDischargeStatus(bed1.getDischargeStatus());
				bed.setDis_status_name(getDischargeStatusName(bed.getDischargeStatus()));
				bed.setClientname(client.getTitle() + " " + client.getFirstName() + " " + client.getLastName());
				bedlist.add(bed);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bedlist;
	}

	private String getDischargeStatusName(String statusid) {
		String status = "";
		try {
			PreparedStatement ps = connection
					.prepareStatement(" select name from  apm_discharge_status where id='" + statusid + "'");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				status = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public boolean checkIfJsonNewDischargeFormStatusExist(String ipdid) {
		boolean flag = false;
		try {
			PreparedStatement ps = connection
					.prepareStatement(" select * from json_dischrgeform_div_status where ipdid='" + ipdid + "' ");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public int insereJsonNewDischargeFormStatus(String ipdid) {
		int res = 0;
		try {
			PreparedStatement ps = connection
					.prepareStatement(" insert into  json_dischrgeform_div_status(ipdid) values(" + ipdid + ") ");
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int updateJsonNewDischargeFormStatus(String ipdid, String column, String status) {
		int res = 0;
		try {
			PreparedStatement ps = connection
					.prepareStatement("update json_dischrgeform_div_status set " + column + "='" + status + "'");
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int chkStatusOfJsonNewDischargeForm(String column, String ipdid) {
		int res = 0;
		try {
			PreparedStatement ps = connection.prepareStatement(
					"select   " + column + " from json_dischrgeform_div_status where ipdid='" + ipdid + "'");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				res = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<Bed> getAllIpdOfClient(String clientid) {
		ArrayList<Bed> bedlist = new ArrayList<Bed>();
		try {
			PreparedStatement ps = connection
					.prepareStatement(" select id from ipd_addmission_form where clientid='" + clientid + "'  ");
			ResultSet rs = ps.executeQuery();

			BedDao bedDao = new JDBCBedDao(connection);
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			while (rs.next()) {
				Bed bed = new Bed();
				bed = bedDao.getEditIpdData(rs.getString(1));
				Client client = clientDAO.getClientDetails(bed.getClientid());
				bed.setGender(client.getGender());
				bed.setAge(client.getAge1());
				Bed bed1 = getDischargeData(bed.getTreatmentepisodeid());
				bed.setDischargeStatus(bed1.getDischargeStatus());
				bed.setDis_status_name(getDischargeStatusName(bed.getDischargeStatus()));
				bed.setClientname(client.getTitle() + " " + client.getFirstName() + " " + client.getLastName());
				bedlist.add(bed);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return bedlist;
	}

	public ArrayList<Bed> getIPDfinalDiagnosisList(String fromdate, String todate, String diagnose,
			String filter_sortby) {
		ArrayList<Bed> ipddiaglist = new ArrayList<Bed>();
		try {
			todate = todate + " 23:59:59";
			StringBuffer buffer = new StringBuffer();
			buffer.append(
					"select apm_final_diagnosis.conditionid,datetime,ipd_addmission_form.clientid,ipdid,discharge_end_date,ipdabrivationid,admissiondsate,dischargedate from apm_final_diagnosis ");
			buffer.append("inner join ipd_addmission_form on ipd_addmission_form.id= apm_final_diagnosis.ipdid ");
			buffer.append(
					"inner join apm_treatment_episode on apm_treatment_episode.id=ipd_addmission_form.treatmentepisodeid ");
			if (filter_sortby.equals("0")) {
				buffer.append(
						"where ipd_addmission_form.admissiondsate between '" + fromdate + "' and '" + todate + "' ");
			} else {
				buffer.append(
						"where apm_treatment_episode.dischargedate between '" + fromdate + "' and '" + todate + "' ");

			}
			// buffer.append(" where datetime between '"+fromdate+"' and '"+todate+"' " );
			buffer.append(" and treatmentstatus=1 ");
//			if(!diagnose.equals("0")){
//				buffer.append("and (apm_final_diagnosis.conditionid like ('%,"+diagnose+",%') or  apm_final_diagnosis.conditionid like ('%,"+diagnose+"') )");
//			}
			PreparedStatement ps = connection.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();

			BedDao bedDao = new JDBCBedDao(connection);
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			while (rs.next()) {
				Bed bed = new Bed();
				String conditionid = rs.getString(1);

				if (!diagnose.equals("")) {
					boolean flag = checkDiagnosisBySearchText(conditionid, diagnose);
					if (!flag) {
						continue;
					}
				}

				String temp[] = conditionid.split(",");
				String diagnosis = "";
				for (String string : temp) {
					if (!string.equals("0")) {
						String diagnosis1 = getdiagnosisbyconditionid(string);
						if (diagnosis.equals("")) {
							diagnosis = diagnosis1;
						} else {
							diagnosis = diagnosis + "," + diagnosis1;
						}
					}
				}
				bed.setDiagnosis(diagnosis);
				bed.setDischarge_end_date(rs.getString(5));
				bed.setAdmissiondate(rs.getString(7));
				bed.setDischargedate(rs.getString(8));
				Client client = clientDAO.getClientDetails(rs.getString(3));
				bed.setAbrivationid(client.getAbrivationid());
				bed.setClientname(client.getTitle() + " " + client.getFirstName() + " " + client.getLastName());
				bed.setClientid(rs.getString(3));
				bed.setIpdabrivationid(rs.getString(6));
				bed.setInvoicetype("IPD");
				//For balgopal
				if(!DateTimeUtils.isNull(bed.getAdmissiondate()).equals("")){
	    			//Client client= new Client();
	    			//client= clientDAO.getClientDetails(bed.getClientid());
	    			String dob = client.getDob();
	    			String admndt[]= bed.getAdmissiondate().split(" ");
	    			String ageonadmn= DateTimeUtils.getAge1onAddmission(dob,admndt[0]);
	    			bed.setAgeonAdmn(ageonadmn+"/"+client.getGender());
	    		}else{
	    			bed.setAgeonAdmn(""+"/"+client.getGender());
	    		}
				ipddiaglist.add(bed);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ipddiaglist;
	}

	private boolean checkDiagnosisBySearchText(String conditionid, String diagnose) {
		boolean res = false;
		try {
			PreparedStatement ps = connection.prepareStatement("select   name from apm_diagnosis where id in ("
					+ conditionid + ") and name like ('%" + diagnose + "%')");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				res = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	private String getdiagnosisbyconditionid(String string) {
		String res = "";
		try {
			PreparedStatement ps = connection
					.prepareStatement("select   name from apm_diagnosis where id='" + string + "'");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				res = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<Bed> getOPDfinalDiagnosisList(String fromdate, String todate, String diagnose,
			String filter_sortby, LoginInfo loginInfo) {
		ArrayList<Bed> ipddiaglist = new ArrayList<Bed>();
		try {
			StringBuffer buffer = new StringBuffer();
			if (loginInfo.isSimpliclinic()) {
				buffer.append(
						"select apmt_id,client_id,opdabbrevationid,commencing,conditionid from consultation_form ");
				buffer.append("inner join apm_available_slot on apm_available_slot.id=apmt_id ");
				buffer.append(
						"inner join consultant_notes_diagnosis on apm_available_slot.id=consultant_notes_diagnosis.apmtid ");
				buffer.append(" where apm_available_slot.commencing between '" + fromdate + "' and '" + todate + "' ");

			} else {
				buffer.append(
						"select diagnosis_id,appointmentid,clientid,opdabbrevationid,commencing from apm_consultation_note ");
				buffer.append("inner join apm_available_slot on apm_available_slot.id=appointmentid ");
				buffer.append(" where apm_available_slot.commencing between '" + fromdate + "' and '" + todate + "' ");
				buffer.append(" and diagnosis_id is not null ");
				if (!diagnose.equals("0")) {
					buffer.append("and (apm_consultation_note.diagnosis_id like ('%," + diagnose
							+ ",%') or  apm_consultation_note.diagnosis_id like ('%," + diagnose + "') )");
				}
			}
			PreparedStatement ps = connection.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();

			BedDao bedDao = new JDBCBedDao(connection);
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			while (rs.next()) {
				Bed bed = new Bed();
//				NotAvailableSlot availableSlot=new NotAvailableSlot();
//				
//				availableSlot= accountsDAO.getClientsAppointmentData(rs.getString(2));
				String conditionid = rs.getString(5);
				if (!diagnose.equals("")) {
					boolean flag = checkDiagnosisBySearchText(conditionid, diagnose);
					if (!flag) {
						continue;
					}
				}

				String temp[] = conditionid.split(",");
				String diagnosis = "";
				for (String string : temp) {
					if (!string.equals("0")) {
						String diagnosis1 = getdiagnosisbyconditionid(string);
						if (diagnosis.equals("")) {
							diagnosis = diagnosis1;
						} else {
							diagnosis = diagnosis + "," + diagnosis1;
						}
					}
				}
				bed.setAppointmentid("" + rs.getInt(1));
				bed.setDiagnosis(diagnosis);
				bed.setDischarge_end_date(rs.getString(4));
				Client client = clientDAO.getClientDetails(rs.getString(2));
				bed.setAbrivationid(client.getAbrivationid());
				bed.setClientname(client.getTitle() + " " + client.getFirstName() + " " + client.getLastName());
				bed.setClientid(rs.getString(2));
				bed.setIpdabrivationid(rs.getString(3));
				bed.setInvoicetype("OPD");

				ipddiaglist.add(bed);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ipddiaglist;
	}

	public int saveDeathNotesDivData(Bed bed) {
		try {
			PreparedStatement ps = connection.prepareStatement(
					" update apm_treatment_episode set death_note=? where id =" + bed.getTreatmentepisodeid() + "");
			ps.setString(1, bed.getDeathnote());
			int x = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public ArrayList<String> getAllWardIdsOFPerson(String ipdid) {

		ArrayList<String> list = new ArrayList<String>();
		try {
			PreparedStatement ps = connection.prepareStatement(
					"select wardid from ipd_bed_change_log where admissionid='" + ipdid + "' group by wardid");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(DateTimeUtils.isNull(rs.getString(1)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void startOnOffChargesWardChangingProcess(String ipdid, String currentWardid, LoginInfo loginInfo) {
		try {
			ArrayList<String> wardlist = getAllWardIdsOFPerson(ipdid);
			for (String oldwardId : wardlist) {
				if (!(currentWardid.equals(oldwardId))) {
					onoffwardchangingProcess(ipdid, currentWardid, oldwardId, loginInfo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void onoffwardchangingProcess(String ipdid, String currentWardid, String oldwardid, LoginInfo loginInfo) {
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append(
					"   select  apm_appointment_type.name,apm_appointment_type.id,assesmentid ,ondatetime ,offdatetime,apm_std_onoff_charge.id from apm_std_onoff_charge   ");
			buffer.append(
					"   inner join apm_appointment_type on apm_appointment_type.id=apm_std_onoff_charge.chargeid   ");
			buffer.append("   where status='1' and apm_appointment_type.wardid='" + oldwardid + "' and ipdid='" + ipdid
					+ "' and stdcharge=1 and onoff=1   ");
			PreparedStatement ps = connection.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();
			// Pattern pattern = Pattern.compile("\\w{2}-\\w{2}-\\w{4}
			// \\w{2}:\\w{2}:\\w{2}");
			BedDao bedDao = new JDBCBedDao(connection);
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
			AppointmentTypeDAO appointmentTypeDAO = new JDBCAppointmentTypeDAO(connection);
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			while (rs.next()) {
				String apmtType = DateTimeUtils.isNull(rs.getString(1));
				int chargeId = rs.getInt(2);
				int assesmentId = rs.getInt(3);
				Accounts onoffChildata = getOnOffDataChild(rs.getString(6));
				String ondates = DateTimeUtils.isNull(rs.getString(4));
				boolean issingleentry = true;
				String off = DateTimeUtils.isNull(rs.getString(5));
				if (off.equals("")) {

				}
				String ondt = "", resetdates = "";
				int counter = 0;
				int lenght = ondates.split(",").length;
				for (String ondate : ondates.split(",")) {

					ondt = ondate;
					counter++;
					if (counter == lenght) {
						break;
					}
					if (counter > 1) {
						issingleentry = false;
						resetdates = resetdates + "," + ondate;
					} else {
						resetdates = ondate;
					}
				}
				boolean upstatus = false;
				if (issingleentry) {
					ondt = DateTimeUtils.isNull(ondt);
					String ondtdateonly = "";
					if (ondt.contains("-")) {
						ondtdateonly = ondt.split(" ")[0];
						String xoffdt = DateTimeUtils.getCommencingDate1(
								DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0]);
						String xoftime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[1];
						if (!(DateTimeUtils.isNull(ondtdateonly).equals(xoffdt))
								&& !(DateTimeUtils.isNull(ondtdateonly).equals(""))) {
							String minusOnday = "";
							SimpleDateFormat minusOndayDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
							Date datemin = minusOndayDate.parse(xoffdt + " " + xoftime);
							Calendar cal2 = Calendar.getInstance();
							cal2.setTime(datemin);
							cal2.add(Calendar.DATE, -1);
							minusOnday = minusOndayDate.format(cal2.getTime());
							updateOnOffDate(ondt, minusOnday, rs.getString(6));
							upstatus = true;
						}
					}

				}

				String ondatetimeforcal = "", offdatetimeforcal;
				int oldchrgeid = getAssmtId(rs.getString(6));
				int newchargeid = 0, qtynew = 0;
				{

				}
				if (!upstatus) {
					updateCurrentdata(resetdates, rs.getString(6));
				}

				Bed bed = bedDao.getEditIpdData(ipdid);
				String clientid = bed.getClientid();
				Client client = clientDAO.getClientDetails(clientid);
				String enddate = "", endTime = "", offdatetime = "";
				int getnewChargeIdofCurrentWard = getApmtTypeByWard(currentWardid, apmtType, client.getTypeName());
				//
				{
					String ondatetime = ondt;
					String chargeid = "" + getnewChargeIdofCurrentWard;
					String date1 = DateTimeUtils.getDateinSimpleFormate(new Date());
					enddate = DateTimeUtils.getCommencingDate1(
							(DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0]));
					endTime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[1];

					offdatetime = enddate + " " + endTime;

					String stdcharges = "001";
					CompleteAppointment appointment = new CompleteAppointment();
					appointment.setClientId(clientid);
					appointment.setPractitionerId(bed.getPractitionerid());
					appointment.setChargeType("Charge");
					appointment.setLocation("1");
					appointment.setAdditionalcharge_id(stdcharges);
					appointment.setIpdid(Integer.parseInt(ipdid));
					appointment.setInvoiceDate(date1);
					appointment.setIpd("1");
					appointment.setAppointmentid("0");
					appointment.setWardid(bed.getWardid());

					if (client.getWhopay() != null) {

						if (client.getWhopay().equals("Self") || client.getWhopay().equals("Client")) {

							appointment.setPolicyExcess("0");
							appointment.setPayBuy("0");
						} else {
							appointment.setPolicyExcess("1");
							appointment.setPayBuy("1");
						}
					}
					int invoiceid = completeAptmDAO.getInvoiceforStandardCharges(ipdid, stdcharges);
					if (invoiceid == 0) {
						invoiceid = completeAptmDAO.saveAmpmInvoice(appointment, loginInfo.getId(),
								loginInfo.getUserId());
					}

					String fullname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();
					appointment.setUser(fullname);
					appointment.setCommencing(date1);

					Master master = appointmentTypeDAO.getMasterCharges(chargeid);
					appointment.setApmtType(master.getName());
					appointment.setCharges(master.getCharge());
					appointment.setAdditionalcharge_id(chargeid);
					appointment.setMasterchargetype(master.getMasterchargetype());
					String time = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[1];
					appointment.setStartTime(time);

					int qty = DateTimeUtils.getDifferanceofDateWithTime(ondatetime, offdatetime,
							master.getChargehours());
					if (qty == 0) {
						qty = 1;
					}

					qtynew = qty;
					prechargeupdateProcess(qty, "" + oldchrgeid);
					appointment.setQuantity(1);
					appointment.setBackDate(DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0]);
					appointment.setStdflag("1");

					int result = appointmentTypeDAO.getStdChargeIdIdExists(ipdid, chargeid);

					int assesmentid = completeAptmDAO.saveInvoiceAssesment(appointment, invoiceid);
					// here off datetime is ondate`time for new entry
					result = appointmentTypeDAO.saveStdCharge(ipdid, chargeid, assesmentid, "1", offdatetime, "");

					Accounts accounts = accountsDAO.showonofftime(Integer.parseInt(chargeid), ipdid);
					int res = accountsDAO.insertStdChargeChild(chargeid, "" + accounts.getId(), loginInfo.getUserId(),
							DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()), ondatetime);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private int getApmtTypeByWard(String wardid, String chargeName, String tpid) {
		int appointmentTypeId = 0;
		tpid = DateTimeUtils.isNull(tpid);
		if (tpid.equals("")) {
			tpid = "0";
		}
		try {
			PreparedStatement ps = connection
					.prepareStatement(" select id,name from apm_appointment_type where wardid='" + wardid
							+ "' and name = '" + chargeName + "' and tpid='" + tpid + "' and stdcharge=1 and onoff=1 ");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				appointmentTypeId = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return appointmentTypeId;
	}

	private int updateCurrentdata(String ondatetime, String id) {
		int re = 0;
		try {
			PreparedStatement ps = connection.prepareStatement("   update apm_std_onoff_charge set ondatetime='"
					+ ondatetime + "' , status='0' where id='" + id + "'  ");
			re = ps.executeUpdate();
			if (re > 0) {

				ps = connection.prepareStatement("  select id from apm_std_onoff_charge_child where child_parentid='"
						+ id + "' and offcommencing is null order by id desc ");
				ResultSet rs = ps.executeQuery();
				if (ondatetime.equals("")) {
					ps = connection.prepareStatement("delete from apm_std_onoff_charge where id='" + id + "'");
					re = ps.executeUpdate();
				}
				while (rs.next()) {

					ps = connection.prepareStatement(
							"delete from apm_std_onoff_charge_child where id='" + rs.getString(1) + "'");
					re = ps.executeUpdate();
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return re;
	}

	void prechargeupdateProcess(int qty, String assId) {
		try {

			PreparedStatement ps = connection
					.prepareStatement(" select quantity from apm_invoice_assesments where id=" + assId);
			ResultSet rs1 = ps.executeQuery();
			while (rs1.next()) {
				int actualqty = rs1.getInt(1);
				int updateQty = (actualqty - 1);
				if (updateQty < 0) {
					updateQty = 0;
				}
				ps = connection.prepareStatement(
						" update  apm_invoice_assesments set quantity=" + updateQty + " where id= " + assId);
				int res = ps.executeUpdate();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private int getAssmtId(String stdonoffId) {
		int assId = 0;
		try {

			String sql = "select assesmentid from apm_std_onoff_charge where id='" + stdonoffId + "'";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				assId = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return assId;
	}

	public ArrayList<Priscription> getTreatmentGivenDischargePrescList(String selectedid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Priscription> list = new ArrayList<Priscription>();
		EmrDAO emrDAO = new JDBCEmrDAO(connection);
		StringBuffer sql = new StringBuffer();
		sql.append(
				"SELECT apm_client_priscription.id,mdicinename,dose,days,notes,priscdurationtype,advoice,dispriscsrno,unit,priscremark,unitextension,dr_qty FROM apm_client_parent_priscription inner join apm_client_priscription ");
		sql.append("on apm_client_priscription.parentid = apm_client_parent_priscription.id ");
		sql.append("where ipdid = " + selectedid + " and fromtreatmentgiven=1 order by (dispriscsrno+0) asc  ");

		try {
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			String total = "0";
			int i = 0;
			boolean strengthflag = false;
			boolean quantityflag = false;
			boolean remarkflag = false;
			while (rs.next()) {
				Priscription priscription = new Priscription();
				priscription.setId(rs.getInt(1));
				priscription.setMdicinenametxt(rs.getString(2));
				priscription.setPriscdose(rs.getString(3));
				priscription.setPriscdays(rs.getString(4));
				priscription.setDosenotes(rs.getString(5));
				priscription.setPriscdurationtype(rs.getString(6));
				priscription.setMedicine_shedule("0");
				priscription.setRegional(emrDAO.getregionalText(priscription.getPriscdose()));
				total = total + "," + rs.getInt(1);
				priscription.setTotalchildmedids(total);
				priscription.setDispriscsrno("" + (++i));

				priscription.setStrength(rs.getString(9));
				priscription.setStrengthnew(priscription.getStrength());
				if (priscription.getStrengthnew().equals("0")) {
					priscription.setStrengthnew("");
				}
				priscription.setPriscindivisualremark(rs.getString(10));
				priscription.setUnitextension(rs.getString(11));
				priscription.setDr_qty(rs.getString(12));
				// lokesh 04 July 2018 error while show in ipd discharge form
				if (priscription.getUnitextension() != null) {
					if (priscription.getUnitextension().equals("0")) {
						priscription.setUnitextension("");
					}
				} else {
					priscription.setUnitextension("");
				}

				if (priscription.getStrengthnew() == null) {
					priscription.setStrengthnew("");
				}

				if (!priscription.getUnitextension().equals("")) {
					quantityflag = true;
				}
				if (!priscription.getStrengthnew().equals("")) {
					strengthflag = true;
				}
				if (priscription.getPriscindivisualremark() == null) {
					priscription.setPriscindivisualremark("");
				}
				if (!priscription.getPriscindivisualremark().equals("")) {
					remarkflag = true;
				}

				priscription.setQuantityflag(quantityflag);
				priscription.setStrengthflag(strengthflag);
				priscription.setRemarkflag(remarkflag);
				list.add(priscription);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	private int updateOnOffDate(String ondt, String offdate, String id) {
		int res = 0;
		try {
			String sql = "  update apm_std_onoff_charge set ondatetime='" + ondt + "' ,offdatetime='" + offdate
					+ "',status='0' where id='" + id + "'";
			PreparedStatement ps = connection.prepareStatement(sql);
			res = ps.executeUpdate();
			String on = DateTimeUtils.getCommencingDate1(ondt.split(" ")[0]) + " " + ondt.split(" ")[1];
			String off = DateTimeUtils.getCommencingDate1(offdate.split(" ")[0]) + " " + offdate.split(" ")[1];
			sql = "  update apm_std_onoff_charge_child set oncommencing='" + on + "' ,offcommencing='" + off
					+ "' where child_parentid='" + id + "'  ";
			ps = connection.prepareStatement(sql);
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public Accounts getOnOffDataChild(String parentId) {
		Accounts accounts = new Accounts();
		try {
			String sql = "select oncommencing ,offcommencing from  apm_std_onoff_charge_child where child_parentid="
					+ parentId;
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			int count = 0;
			while (rs.next()) {
				String ondate = rs.getString(1);
				String offdate = rs.getString(2);
				if (count == 0) {
					accounts.setOndatetime(ondate);
					if (offdate != null) {
						accounts.setOffdatetime(offdate);
					} else {
						break;
					}
				} else {
					accounts.setOndatetime(accounts.getOndatetime() + "," + ondate);
					if (offdate != null) {
						accounts.setOffdatetime(accounts.getOffdatetime() + "," + offdate);
					}
				}
				count++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accounts;
	}

	public void inserDischargeCardFields() {
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append(
					" past_hist_HTN,past_hist_DM,past_hist_IHD,past_hist_Other,past_hist_CVE,past_hist_br_asthama,past_hist_COAD,past_hist_Thyroid");
			buffer.append(
					",past_hist_HTN_text,past_hist_DM_text,past_hist_IHD_text,past_hist_Other_text,past_hist_CVE_text,past_hist_br_asthama_text,past_hist_COAD_text,past_hist_Thyroid_text");
			buffer.append(
					",person_hist_Smoking,person_hist_Alchohol,person_hist_OtherAddt,person_hist_Bowel_Bladder,person_hist_Sleep,person_hist_Tobacco");
			buffer.append(
					",person_hist_Smoking_text,person_hist_Alchohol_text,person_hist_OtherAddt_text,person_hist_Bowel_Bladder_text,person_hist_Sleep_text,person_hist_Tobacco_text");
			buffer.append(",obng_menstrual_hist,obng_gpla,obng_tubesctomy,obng_lmp");
			buffer.append(
					",apearnace_Pallor,apearnace_Cynosis,apearnace_Clubbing,apearnace_Icterus,apearnace_ln,sys_exa_CVS,sys_exa_RS,sys_exa_CNS,sys_exa_PA,sys_exa_PVPR,sys_exa_Others");
			buffer.append(
					",gen_cond_Temp,gen_cond_Pulse,gen_cond_BP,gen_cond_CVS,gen_cond_PS,gen_cond_CNS,physio_th_adv_Mobilization,physio_th_adv_fallRisk,physio_th_adv_Driving,physio_th_adv_sexual_Activity,dietary_advice,local_relevant_area,tubes_and_training");

			for (String columnname : buffer.toString().split(",")) {
				String sql = " ALTER TABLE `his_new_discharge_fields` ADD COLUMN `" + columnname
						+ "` LONGTEXT DEFAULT NULL ;  ";
				PreparedStatement ps = connection.prepareStatement(sql);
				int x = ps.executeUpdate();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public ArrayList<Master> commonTemplateList() {
		ArrayList<Master> list = new ArrayList<Master>();
		try {
			String sql = " select * from ipd_template where showall='1' ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt("id"));
				master.setText(rs.getString("text"));
				master.setName(rs.getString("name"));
				list.add(master);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int saveCommonTeplate(Master master) {
		int res = 0;
		try {
			String sql = " insert into ipd_template(name,text,template_nameid, department, speciality, showall) values(?,?,?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, master.getName());
			ps.setString(2, master.getText());
			ps.setString(3, "");
			ps.setString(4, "1");
			ps.setString(5, "");
			ps.setString(6, "1");

			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public void startInvestigationChargeCancelProcess(Client client, String investId, LoginInfo loginInfo) {
		try {
			InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
			Investigation investigation = investigationDAO.getEditInvestigation(investId);
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			StatementDAO statementDAO = new JDBCStatementDAO(connection);
			CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
			if (!investigation.getCharge().equals("0")) {
				String sql = "  select quantity from apm_invoice_assesments where id='" + investigation.getCharge()
						+ "'";
				PreparedStatement ps = connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					int qty = rs.getInt(1);
					if (qty > 0) {
						ps = connection.prepareStatement(" update apm_invoice_assesments set quantity=? where   id='"
								+ investigation.getCharge() + "' ");
						ps.setString(1, "" + (qty - 1));
						int res = ps.executeUpdate();
						if (res > 0) {
							CompleteAppointment thisCharge = accountsDAO
									.chargeAssementDetails(investigation.getCharge());
							if (thisCharge.getChargeInvoiceId() > 0) {
								double creditAmount = statementDAO.getCreditAmount(thisCharge.getChargeInvoiceId());
								int paymentdone = 0;
								if (creditAmount > 0) {
									paymentdone = 1;
								}
								String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
								CompleteAppointment completeAppointment1 = completeAptmDAO
										.getInvoiceDiscountByChargeId("" + thisCharge.getChargeInvoiceId());
								CompleteAppointment deletedCharge = thisCharge;
								deletedCharge.setDate(datetime);
								int deducQty = 1;
								deletedCharge.setQuantity(deducQty);
								int result = completeAptmDAO.saveInvoiceDeletedCharge(deletedCharge,
										thisCharge.getInvoiceid(), loginInfo.getId(), 0,
										thisCharge.getChargeInvoiceId(), paymentdone, datetime, loginInfo.getUserId());

								String sqlw = "select debit from apm_charges_invoice where id='"
										+ thisCharge.getChargeInvoiceId() + "'";
								ps = connection.prepareStatement(sqlw);
								ResultSet rs1 = ps.executeQuery();
								double debitAmount = 0;
								int deb = 0;
								while (rs1.next()) {
									deb = rs1.getInt(1);
								}
								int amt = deb - DateTimeUtils.convertToInteger(thisCharge.getCharges());
								debitAmount = amt;
								int update = completeAptmDAO.updateModifyInvoiceDebitAmmount(debitAmount,
										thisCharge.getChargeInvoiceId());

							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Bed> getActiveIpdPatientList() {
		ArrayList<Bed> arrayList = new ArrayList<Bed>();
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select apm_patient.id,concat(title,' ',firstname,' ',surname) from ipd_addmission_form ");
			buffer.append("inner join apm_patient on apm_patient.id = ipd_addmission_form.clientid ");
			buffer.append("where bedid!=0 order by concat(firstname,' ',surname) ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Bed bed = new Bed();
				bed.setClientid(rs.getString(1));
				bed.setClientname(rs.getString(2));
				arrayList.add(bed);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public String getActiveIpdIdFromClientId(String clientid) {
		String ipdid = "0";
		try {
			String sql = "select id from ipd_addmission_form where clientid=" + clientid + " and bedid>0";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ipdid = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ipdid;

	}

	public int cancelIpdVisitConsult(String visitid, String userId, String date) {
		int res = 0;
		try {
			String sql = "update ipd_visiting_consultant set visi_cancel=1,visi_cancel_userid='" + userId
					+ "',visi_cancel_date='" + date + "' where id='" + visitid + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int updateWardBedInAdmission(int id, String wardid, String bedid) {
		int res = 0;
		try {
			String sql = "update ipd_addmission_form set wardid='" + wardid + "',bedid='" + bedid + "'where id='" + id
					+ "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();

			BedDao bedDao = new JDBCBedDao(connection);
			bedDao.updateAdmissionFormInParent(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int getTotalBookedBedCount(String wardid, int clinicid, LoginInfo loginInfo, String filter_status,
			String action, String activefilter, String isfromandroid, String androidpractid, String excessamtbt) {

		PreparedStatement preparedStatement = null;
		int res = 0;
		StringBuffer buffer = new StringBuffer();
		String practid = getUserTableid(loginInfo.getUserId());
		if (loginInfo.isDrwise_ipd()) {
			if (isfromandroid.equals("1")) {
				buffer.append("SELECT count(*) FROM apm_ipd_bed   ");
				buffer.append("inner join ipd_addmission_form on apm_ipd_bed.id = ipd_addmission_form.bedid ");
				buffer.append("where apm_ipd_bed.wardid in(" + wardid + ") and apm_ipd_bed.casualty=" + action
						+ " and practitionerid='" + androidpractid + "' ");
				buffer.append("and apm_ipd_bed.active='" + activefilter + "' ");
			} else {
				if (!wardid.equals("0")) { // previous code
					buffer.append("SELECT count(*) FROM apm_ipd_bed");
					buffer.append(" inner join ipd_addmission_form on apm_ipd_bed.id = ipd_addmission_form.bedid ");
					buffer.append(
							" where apm_ipd_bed.wardid in(" + wardid + ") and apm_ipd_bed.casualty=" + action + "  ");
					buffer.append("and apm_ipd_bed.active='" + activefilter + "' ");
					if (loginInfo.getJobTitle().equals("Practitioner")) {
						buffer.append(" and (ipd_addmission_form.practitionerid='" + practid
								+ "' or ipd_addmission_form.practitionerid is null) ");
					}
				} else {
					buffer.append("SELECT count(*) FROM apm_ipd_bed");
					buffer.append(" inner join ipd_addmission_form on apm_ipd_bed.id = ipd_addmission_form.bedid ");
					buffer.append(" where apm_ipd_bed.casualty=" + action + "  ");
					buffer.append("and apm_ipd_bed.active='" + activefilter + "' ");
					if (loginInfo.getJobTitle().equals("Practitioner")) {
						buffer.append(" and (ipd_addmission_form.practitionerid='" + practid
								+ "' or ipd_addmission_form.practitionerid is null )");
					}
				}
			}
		} else {
			if (isfromandroid.equals("1")) {
				buffer.append("SELECT count(*) FROM apm_ipd_bed   ");
				buffer.append("inner join ipd_addmission_form on apm_ipd_bed.id = ipd_addmission_form.bedid ");
				buffer.append("where apm_ipd_bed.wardid in(" + wardid + ") and apm_ipd_bed.casualty=" + action
						+ " and practitionerid='" + androidpractid + "' ");
				buffer.append("and apm_ipd_bed.active='" + activefilter + "' ");
			} else {
				/*
				 * if (!wardid.equals("0")) { // previous code
				 * buffer.append("SELECT count(*) FROM apm_ipd_bed  "); buffer.
				 * append("inner join ipd_addmission_form on ipd_addmission_form.bedid =apm_ipd_bed.id "
				 * ); buffer.append("where apm_ipd_bed.wardid in(" + wardid +
				 * ") and apm_ipd_bed.casualty=" + action + "  ");
				 * buffer.append("and apm_ipd_bed.active='" + activefilter + "' ");
				 * 
				 * } else { buffer.append("SELECT count(*) FROM apm_ipd_bed   "); buffer.
				 * append("inner join ipd_addmission_form on ipd_addmission_form.bedid =apm_ipd_bed.id "
				 * ); buffer.append("where apm_ipd_bed.casualty=" + action+
				 * " and apm_ipd_bed.active='" + activefilter + "' "); }
				 */
				buffer.append("SELECT count(*) FROM apm_ipd_bed  ");
				buffer.append("inner join ipd_addmission_form on ipd_addmission_form.bedid =apm_ipd_bed.id ");
				buffer.append("where apm_ipd_bed.wardid in(" + wardid + ") and apm_ipd_bed.casualty=" + action + "  ");
				buffer.append("and apm_ipd_bed.active='" + activefilter + "' ");

			}

		}
		try {

			preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				res = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

		return res;

	}

	public int savenewIpdGynicDetails(Bed bed) {

		int result = 0;
		try {

			StringBuffer buffer = new StringBuffer();
			buffer.append(
					"insert into ipd_new_gynic_form (ipdid, clientid, practitionerid, commencing, userid, lastmodified,  ");
			buffer.append("visit_reason_ids, hrf_gdm, hrf_pih, hrf_boh, hrf_hypothyroid, hrf_hyperthyroid, ");
			buffer.append("hrf_prev_lscs, hrf_rh_negative, hrf_other, nulligravida, current_pregnant, iud, ");
			buffer.append(
					"live_boys, live_girls, still_birth, abortions, death_child, parity_abortion_notes, g, p, l, a, d, ");
			buffer.append("surgical_ho, date1, date2, date3, date4, hb1, hb2, hb3, hb4, fbs1, fbs2, fbs3, fbs4, ");
			buffer.append("dpbs1, dpbs2, dpbs3, dpbs4, urm1, urm2, urm3, urm4, tsh1, tsh2, tsh3, tsh4, ");
			buffer.append("ict1, ict2, ict3, ict4, gtt1, gtt2, gtt3, gtt4, hvm, hbsag, vdrl, hb_srecta, ");
			buffer.append("hbac, duet_markers, Quradraple_maicers, ");
			buffer.append("lmp, dating_usg_date, edd, weeks_dating_scan, pog, pog_dating_scan, pmc, usg_report, ");
			buffer.append(
					"tt_dose1, tt_dose2, gen_condition, temp, pallor, pedal_edema, pulse, bmi, height, weight, sys_bp, dia_bp, ");
			buffer.append(
					"wall_edema, pa_soft, fundal_height, cephalic, breech, baley_size, transverse_fhs, cephalicVal, baley_sizeVal, ");
			buffer.append(
					"external_ballotment, ps_fhs, beats_min, liquor, uterine_contractions, ps_cervix, ps_cervixVal, ");
			buffer.append(
					"ps_vagine, ps_vagineVal,ps_vault, ps_vaultVal, pv_trim, pv_unettacced, pv_ant, pv_tubular, ");
			buffer.append("pv_just_ettacced, pv_mid_posits, pv_soft, ");
			buffer.append(
					"pv_ettacced, effaced_text, pv_post, pv_station, pv_liquor, pv_pelvis, pv_position, pv_os, pv_osVal, ");
			buffer.append("pv_membranes, pv_MVal, pv_dialated, pv_dialatedcm, formtype, additonal_notes, diagonosis, ");
			buffer.append(
					"treatment_advice, investigation_advice,triple,days_dating_scan,hrf_otherval,createddate,influenza_vaccine,lastappointmentid ");
			buffer.append(") ");
			buffer.append(" values (?, ?, ?, ?, ?, ?, ");
			buffer.append("?, ?, ?, ?, ?, ?, ");
			buffer.append("?, ?, ?, ?, ?, ?, ");
			buffer.append("?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
			buffer.append("?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
			buffer.append("?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
			buffer.append("?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
			buffer.append("?, ?, ?, ?, ");
			buffer.append("?, ?, ?, ?, ?, ?, ?, ?, ");
			buffer.append("?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
			buffer.append("?, ?, ?, ?, ?, ?, ?, ?, ?,  ");
			buffer.append("?, ?, ?, ?, ?, ?, ?, ");
			buffer.append("?, ?, ?, ?, ?, ?, ?, ?, ");
			buffer.append("?, ?, ?, ");
			buffer.append("?, ?, ?, ?, ?, ?, ?, ?, ?, ");
			buffer.append("?, ?, ?, ?, ?, ?, ?, ");
			buffer.append("?, ?, ?, ?, ?, ?, ? ");
			buffer.append(") ");

			PreparedStatement ps = connection.prepareStatement(buffer.toString());
			// ipdid, clientid, practitionerid, commencing, userid, lastmodified,
			ps.setString(1, bed.getIpdid());
			ps.setString(2, bed.getClientid());
			ps.setString(3, bed.getPractitionerid());
			ps.setString(4, bed.getCommencing());
			ps.setString(5, bed.getUserid());
			ps.setString(6, bed.getLastmodified());

			// visit_reason_ids, hrf_gdm, hrf_pih, hrf_boh, hrf_hypothyroid,
			// hrf_hyperthyroid,
			ps.setString(7, bed.getVisit_reason_ids());
			ps.setString(8, bed.getHrf_gdm());
			ps.setString(9, bed.getHrf_pih());
			ps.setString(10, bed.getHrf_boh());
			ps.setString(11, bed.getHrf_hypothyroid());
			ps.setString(12, bed.getHrf_hyperthyroid());

			// hrf_prev_lscs, hrf_rh_negative, hrf_other, nulligravida, current_pregnant,
			// iud,
			ps.setString(13, bed.getHrf_prev_lscs());
			ps.setString(14, bed.getHrf_rh_negative());
			ps.setString(15, bed.getHrf_other());
			ps.setString(16, bed.getNulligravida());
			ps.setString(17, bed.getCurrent_pregnent());
			ps.setString(18, bed.getIud());

			// live_boys, live_girls, still_birth, abortions, death_child,
			// parity_abortion_notes, g, p, l, a, d,
			ps.setString(19, bed.getLive_boys());
			ps.setString(20, bed.getLive_girls());
			ps.setString(21, bed.getStillbirths());
			ps.setString(22, bed.getAbortions());
			ps.setString(23, bed.getDeath_children());
			ps.setString(24, bed.getParity_abortion_notes());
			ps.setString(25, bed.getG());
			ps.setString(26, bed.getP());
			ps.setString(27, bed.getL());
			ps.setString(28, bed.getA());
			ps.setString(29, bed.getD());

			// surgical_ho, date1, date2, date3, date4, hb1, hb2, hb3, hb4, fbs1, fbs2,
			// fbs3, fbs4,
			ps.setString(30, bed.getSurgical_ho());
			ps.setString(31, bed.getDate1());
			ps.setString(32, bed.getDate2());
			ps.setString(33, bed.getDate3());
			ps.setString(34, bed.getDate4());
			ps.setString(35, bed.getHb1());
			ps.setString(36, bed.getHb2());
			ps.setString(37, bed.getHb3());
			ps.setString(38, bed.getHb4());
			ps.setString(39, bed.getFbs1());
			ps.setString(40, bed.getFbs2());
			ps.setString(41, bed.getFbs3());
			ps.setString(42, bed.getFbs4());

			// dpbs1, dpbs2, dpbs3, dpbs4, urm1, urm2, urm3, urm4, tsh1, tsh2, tsh3, tsh4,
			ps.setString(43, bed.getDpbs1());
			ps.setString(44, bed.getDpbs2());
			ps.setString(45, bed.getDpbs3()); // past_history_notes
			ps.setString(46, bed.getDpbs4());
			ps.setString(47, bed.getUrm1()); // family_history_notes
			ps.setString(48, bed.getUrm2());
			ps.setString(49, bed.getUrm3());
			ps.setString(50, bed.getUrm4());
			ps.setString(51, bed.getTsh1());
			ps.setString(52, bed.getTsh2());
			ps.setString(53, bed.getTsh3());
			ps.setString(54, bed.getTsh4());

			// ict1, ict2, ict3, ict4, gtt1, gtt2, gtt3, gtt4, hvm, hbsag, vdrl, hb_srecta,
			ps.setString(55, bed.getIct1());
			ps.setString(56, bed.getIct2());
			ps.setString(57, bed.getIct3());
			ps.setString(58, bed.getIct4());
			ps.setString(59, bed.getGtt1());
			ps.setString(60, bed.getGtt2());
			ps.setString(61, bed.getGtt3());
			ps.setString(62, bed.getGtt4());
			ps.setString(63, bed.getHv_1m());
			ps.setString(64, bed.getHbs_ag());
			ps.setString(65, bed.getVdrl());
			ps.setString(66, bed.getHb_srecta());

			// hbac, duet_markers, Quradraple_maicers,
			ps.setString(67, bed.getHb_ac());
			ps.setString(68, bed.getDuet_markess());
			ps.setString(69, bed.getQuadrple_maicers());

			// lmp, dating_usg_date, edd, weeks_dating_scan, pog, pog_dating_scan, pmc,
			// usg_report,
			ps.setString(70, bed.getLmp());
			ps.setString(71, bed.getDating_usg_date());
			ps.setString(72, bed.getEdd());
			ps.setString(73, bed.getWeeks_dating_scan());
			ps.setString(74, bed.getPog());
			ps.setString(75, bed.getPog_dating_scan());
			ps.setString(76, bed.getPmc());
			ps.setString(77, bed.getUsg_report());

			// tt_dose1, tt_dose2, gen_condition, temp, pallor, pedal_edema, pulse, bmi,
			// height, weight, sys_bp, dia_bp,
			ps.setString(78, bed.getTt_dose1());
			ps.setString(79, bed.getTt_dose2());
			ps.setString(80, bed.getGen_condition());
			ps.setString(81, bed.getTemp());
			ps.setString(82, bed.getPallor());
			ps.setString(83, bed.getPedal_edema());
			ps.setString(84, bed.getPulse());
			ps.setString(85, bed.getBmi());
			ps.setString(86, bed.getHeight());
			ps.setString(87, bed.getWeight());
			ps.setString(88, bed.getSys_bp());
			ps.setString(89, bed.getDia_bp());

			// wall_edema, pa_soft, fundal_height, cephalic, breech, baley_size,
			// transverse_fhs, cephalicVal, baley_sizeVal,
			ps.setString(90, bed.getWall_edema());
			ps.setString(91, bed.getPa_soft());
			ps.setString(92, bed.getFundal_height());
			ps.setString(93, bed.getCephalic());
			ps.setString(94, bed.getBreech());
			ps.setString(95, bed.getBaley_size());
			ps.setString(96, bed.getTransverse_fhs());
			ps.setString(97, bed.getCephalicVal());
			ps.setString(98, bed.getBaley_sizeVal());

			// external_ballotment, ps_fhs, beats_min, liquor, uterine_contractions,
			// ps_cervix, ps_cervixVal,
			ps.setString(99, bed.getExternal_ballotment());
			ps.setString(100, bed.getPs_fhs());
			ps.setString(101, bed.getBeats_min());
			ps.setString(102, bed.getLiquor());
			ps.setString(103, bed.getUterine_contractions());
			ps.setString(104, bed.getPs_cervix());
			ps.setString(105, bed.getPs_cervixVal());

			// ps_vagine, ps_vagineVal,ps_vault, ps_vaultVal, pv_trim, pv_unettacced,
			// pv_ant, pv_tubular,
			ps.setString(106, bed.getPs_vagine());
			ps.setString(107, bed.getPs_vagineVal());
			ps.setString(108, bed.getPs_vault());
			ps.setString(109, bed.getPs_vaultVal());
			ps.setString(110, bed.getPv_trim());
			ps.setString(111, bed.getPv_unettacced());
			ps.setString(112, bed.getPv_ant());
			ps.setString(113, bed.getPv_tubular());

			// pv_just_ettacced, pv_mid_posits, pv_soft,
			ps.setString(114, bed.getPv_just_ettacced());
			ps.setString(115, bed.getPv_mid_posits());
			ps.setString(116, bed.getPv_soft());

			// pv_ettacced, effaced_text, pv_post, pv_station, pv_liquor, pv_pelvis,
			// pv_position, pv_os, pv_osVal,
			ps.setString(117, bed.getPv_ettacced());
			ps.setString(118, bed.getEffaced_text());
			ps.setString(119, bed.getPv_post());
			ps.setString(120, bed.getPv_station());
			ps.setString(121, bed.getPv_liquor());
			ps.setString(122, bed.getPv_pelvis());
			ps.setString(123, bed.getPv_position());
			ps.setString(124, bed.getPv_os());
			ps.setString(125, bed.getPv_osVal());

			// pv_membranes, pv_MVal, pv_dialated, pv_dialatedcm, formtype, additonal_notes,
			// diagonosis,
			ps.setString(126, bed.getPv_membrane());
			ps.setString(127, bed.getPv_MVal());
			ps.setString(128, bed.getPv_dialated());
			ps.setString(129, bed.getPv_dialatedcm());
			ps.setString(130, bed.getFormtype());
			ps.setString(131, bed.getAdditonal_notes());
			ps.setString(132, bed.getDiagonosis());

			// treatment_advice, investigation_advice, triple, days_dating_scan,
			// hrf_otherval
			ps.setString(133, bed.getTreatment_advice());
			ps.setString(134, bed.getInvestigation_advice());
			ps.setString(135, bed.getTriple());
			ps.setString(136, bed.getDays_dating_scan());
			ps.setString(137, bed.getHrf_otherval());
			ps.setString(138, bed.getCreateddate());
			ps.setString(139, bed.getInfluenza_vaccine());
			ps.setString(140, "" + bed.getLastappointmentid());
			result = ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			while (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;

	}

	public Bed getEditNewGynicFormData(String id) {

		Bed bed = new Bed();
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select ipdid, clientid, practitionerid, commencing, userid, lastmodified,  ");
			buffer.append("visit_reason_ids, hrf_gdm, hrf_pih, hrf_boh, hrf_hypothyroid, hrf_hyperthyroid, ");
			buffer.append("hrf_prev_lscs, hrf_rh_negative, hrf_other, nulligravida, current_pregnant, iud, ");
			buffer.append(
					"live_boys, live_girls, still_birth, abortions, death_child, parity_abortion_notes, g, p, l, a, d, ");
			buffer.append("surgical_ho, date1, date2, date3, date4, hb1, hb2, hb3, hb4, fbs1, fbs2, fbs3, fbs4, ");
			buffer.append("dpbs1, dpbs2, dpbs3, dpbs4, urm1, urm2, urm3, urm4, tsh1, tsh2, tsh3, tsh4, ");
			buffer.append("ict1, ict2, ict3, ict4, gtt1, gtt2, gtt3, gtt4, hvm, hbsag, vdrl, hb_srecta, ");
			buffer.append("hbac, duet_markers, Quradraple_maicers, ");
			buffer.append("lmp, dating_usg_date, edd, weeks_dating_scan, pog, pog_dating_scan, pmc, usg_report, ");
			buffer.append(
					"tt_dose1, tt_dose2, gen_condition, temp, pallor, pedal_edema, pulse, bmi, height, weight, sys_bp, dia_bp, ");
			buffer.append(
					"wall_edema, pa_soft, fundal_height, cephalic, breech, baley_size, transverse_fhs, cephalicVal, baley_sizeVal, ");
			buffer.append(
					"external_ballotment, ps_fhs, beats_min, liquor, uterine_contractions, ps_cervix, ps_cervixVal, ");
			buffer.append(
					"ps_vagine, ps_vagineVal,ps_vault, ps_vaultVal, pv_trim, pv_unettacced, pv_ant, pv_tubular, ");
			buffer.append("pv_just_ettacced, pv_mid_posits, pv_soft, ");
			buffer.append(
					"pv_ettacced, effaced_text, pv_post, pv_station, pv_liquor, pv_pelvis, pv_position, pv_os, pv_osVal, ");
			buffer.append("pv_membranes, pv_MVal, pv_dialated, pv_dialatedcm, formtype, additonal_notes, diagonosis, ");
			buffer.append(
					"treatment_advice, investigation_advice,triple,id,days_dating_scan,hrf_otherval,createddate,influenza_vaccine,lastappointmentid ");
			buffer.append("from ipd_new_gynic_form where id='" + id + "' ");
			PreparedStatement ps = connection.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bed.setIpdid(rs.getString(1));
				bed.setClientid(rs.getString(2));
				bed.setPractitionerid(rs.getString(3));
				bed.setCommencing(rs.getString(4));
				bed.setUserid(rs.getString(5));
				bed.setLastmodified(rs.getString(6));

				bed.setVisit_reason_ids(rs.getString(7));
				bed.setHrf_gdm(rs.getString(8));
				bed.setHrf_pih(rs.getString(9));
				bed.setHrf_boh(rs.getString(10));
				bed.setHrf_hypothyroid(rs.getString(11));
				bed.setHrf_hyperthyroid(rs.getString(12));

				bed.setHrf_prev_lscs(rs.getString(13));
				bed.setHrf_rh_negative(rs.getString(14));
				bed.setHrf_other(rs.getString(15));
				bed.setNulligravida(rs.getString(16));
				bed.setCurrent_pregnent(rs.getString(17));
				bed.setIud(rs.getString(18));

				bed.setLive_boys(rs.getString(19));
				bed.setLive_girls(rs.getString(20));
				bed.setStillbirths(rs.getString(21));
				bed.setAbortions(rs.getString(22));
				bed.setDeath_children(rs.getString(23));
				bed.setParity_abortion_notes(rs.getString(24));
				bed.setG(rs.getString(25));
				bed.setP(rs.getString(26));
				bed.setL(rs.getString(27));
				bed.setA(rs.getString(28));
				bed.setD(rs.getString(29));

				bed.setSurgical_ho(rs.getString(30));
				bed.setDate1(rs.getString(31));
				bed.setDate2(rs.getString(32));
				bed.setDate3(rs.getString(33));
				bed.setDate4(rs.getString(34));
				bed.setHb1(rs.getString(35));
				bed.setHb2(rs.getString(36));
				bed.setHb3(rs.getString(37));
				bed.setHb4(rs.getString(38));
				bed.setFbs1(rs.getString(39));
				bed.setFbs2(rs.getString(40));
				bed.setFbs3(rs.getString(41));
				bed.setFbs4(rs.getString(42));

				bed.setDpbs1(rs.getString(43));
				bed.setDpbs2(rs.getString(44));
				bed.setDpbs3(rs.getString(45));
				bed.setDpbs4(rs.getString(46));
				bed.setUrm1(rs.getString(47));
				bed.setUrm2(rs.getString(48));
				bed.setUrm3(rs.getString(49));
				bed.setUrm4(rs.getString(50));
				bed.setTsh1(rs.getString(51));
				bed.setTsh2(rs.getString(52));
				bed.setTsh3(rs.getString(53));
				bed.setTsh4(rs.getString(54));

				bed.setIct1(rs.getString(55));
				bed.setIct2(rs.getString(56));
				bed.setIct3(rs.getString(57));
				bed.setIct4(rs.getString(58));
				bed.setGtt1(rs.getString(59));
				bed.setGtt2(rs.getString(60));
				bed.setGtt3(rs.getString(61));
				bed.setGtt4(rs.getString(62));
				bed.setHv_1m(rs.getString(63));
				bed.setHbs_ag(rs.getString(64));
				bed.setVdrl(rs.getString(65));
				bed.setHb_srecta(rs.getString(66));

				bed.setHb_ac(rs.getString(67));
				bed.setDuet_markess(rs.getString(68));
				bed.setQuadrple_maicers(rs.getString(69));

				bed.setLmp(rs.getString(70));
				bed.setDating_usg_date(rs.getString(71));
				bed.setEdd(rs.getString(72));
				bed.setWeeks_dating_scan(rs.getString(73));
				bed.setPog(rs.getString(74));
				bed.setPog_dating_scan(rs.getString(75));
				bed.setPmc(rs.getString(76));
				bed.setUsg_report(rs.getString(77));

				bed.setTt_dose1(rs.getString(78));
				bed.setTt_dose2(rs.getString(79));
				bed.setGen_condition(rs.getString(80));
				bed.setTemp(rs.getString(81));
				bed.setPallor(rs.getString(82));
				bed.setPedal_edema(rs.getString(83));
				bed.setPulse(rs.getString(84));
				bed.setBmi(rs.getString(85));
				bed.setHeight(rs.getString(86));
				bed.setWeight(rs.getString(87));
				bed.setSys_bp(rs.getString(88));
				bed.setDia_bp(rs.getString(89));

				bed.setWall_edema(rs.getString(90));
				bed.setPa_soft(rs.getString(91));
				bed.setFundal_height(rs.getString(92));
				bed.setCephalic(rs.getString(93));
				bed.setBreech(rs.getString(94));
				bed.setBaley_size(rs.getString(95));
				bed.setTransverse_fhs(rs.getString(96));
				bed.setCephalicVal(rs.getString(97));
				bed.setBaley_sizeVal(rs.getString(98));

				bed.setExternal_ballotment(rs.getString(99));
				bed.setPs_fhs(rs.getString(100));
				bed.setBeats_min(rs.getString(101));
				bed.setLiquor(rs.getString(102));
				bed.setUterine_contractions(rs.getString(103));
				bed.setPs_cervix(rs.getString(104));
				bed.setPs_cervixVal(rs.getString(105));

				bed.setPs_vagine(rs.getString(106));
				bed.setPs_vagineVal(rs.getString(107));
				bed.setPs_vault(rs.getString(108));
				bed.setPs_vaultVal(rs.getString(109));
				bed.setPv_trim(rs.getString(110));
				bed.setPv_unettacced(rs.getString(111));
				bed.setPv_ant(rs.getString(112));
				bed.setPv_tubular(rs.getString(113));

				bed.setPv_just_ettacced(rs.getString(114));
				bed.setPv_mid_posits(rs.getString(115));
				bed.setPv_soft(rs.getString(116));

				bed.setPv_ettacced(rs.getString(117));
				bed.setEffaced_text(rs.getString(118));
				bed.setPv_post(rs.getString(119));
				bed.setPv_station(rs.getString(120));
				bed.setPv_liquor(rs.getString(121));
				bed.setPv_pelvis(rs.getString(122));
				bed.setPv_position(rs.getString(123));
				bed.setPv_os(rs.getString(124));
				bed.setPv_osVal(rs.getString(125));

				bed.setPv_membrane(rs.getString(126));
				bed.setPv_MVal(rs.getString(127));
				bed.setPv_dialated(rs.getString(128));
				bed.setPv_dialatedcm(rs.getString(129));
				bed.setFormtype(rs.getString(130));
				bed.setAdditonal_notes(rs.getString(131));
				bed.setDiagonosis(rs.getString(132));

				bed.setTreatment_advice(rs.getString(133));
				bed.setInvestigation_advice(rs.getString(134));
				bed.setTriple(rs.getString(135));
				bed.setId(rs.getInt(136));
				bed.setDays_dating_scan(rs.getString(137));
				bed.setHrf_otherval(rs.getString(138));
				bed.setCreateddate(rs.getString(139));
				bed.setInfluenza_vaccine(rs.getString(140));
				bed.setLastappointmentid(rs.getInt(141));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return bed;

	}

	public int updatenewIpdGynicDetails(Bed bed) {

		int result = 0;
		try {

			StringBuffer buffer = new StringBuffer();
			buffer.append(
					"update ipd_new_gynic_form set ipdid=?, clientid=?, practitionerid=?, commencing=?, userid=?, lastmodified=?,  ");
			buffer.append(
					"visit_reason_ids=?, hrf_gdm=?, hrf_pih=?, hrf_boh=?, hrf_hypothyroid=?, hrf_hyperthyroid=?, ");
			buffer.append(
					"hrf_prev_lscs=?, hrf_rh_negative=?, hrf_other=?, nulligravida=?, current_pregnant=?, iud=?, ");
			buffer.append(
					"live_boys=?, live_girls=?, still_birth=?, abortions=?, death_child=?, parity_abortion_notes=?, g=?, p=?, l=?, a=?, d=?, ");
			buffer.append(
					"surgical_ho=?, date1=?, date2=?, date3=?, date4=?, hb1=?, hb2=?, hb3=?, hb4=?, fbs1=?, fbs2=?, fbs3=?, fbs4=?, ");
			buffer.append(
					"dpbs1=?, dpbs2=?, dpbs3=?, dpbs4=?, urm1=?, urm2=?, urm3=?, urm4=?, tsh1=?, tsh2=?, tsh3=?, tsh4=?, ");
			buffer.append(
					"ict1=?, ict2=?, ict3=?, ict4=?, gtt1=?, gtt2=?, gtt3=?, gtt4=?, hvm=?, hbsag=?, vdrl=?, hb_srecta=?, ");
			buffer.append("hbac=?, duet_markers=?, Quradraple_maicers=?, ");
			buffer.append(
					"lmp=?, dating_usg_date=?, edd=?, weeks_dating_scan=?, pog=?, pog_dating_scan=?, pmc=?, usg_report=?, ");
			buffer.append(
					"tt_dose1=?, tt_dose2=?, gen_condition=?, temp=?, pallor=?, pedal_edema=?, pulse=?, bmi=?, height=?, weight=?, sys_bp=?, dia_bp=?, ");
			buffer.append(
					"wall_edema=?, pa_soft=?, fundal_height=?, cephalic=?, breech=?, baley_size=?, transverse_fhs=?, cephalicVal=?, baley_sizeVal=?, ");
			buffer.append(
					"external_ballotment=?, ps_fhs=?, beats_min=?, liquor=?, uterine_contractions=?, ps_cervix=?, ps_cervixVal=?, ");
			buffer.append(
					"ps_vagine=?, ps_vagineVal=?,ps_vault=?, ps_vaultVal=?, pv_trim=?, pv_unettacced=?, pv_ant=?, pv_tubular=?, ");
			buffer.append("pv_just_ettacced=?, pv_mid_posits=?, pv_soft=?, ");
			buffer.append(
					"pv_ettacced=?, effaced_text=?, pv_post=?, pv_station=?, pv_liquor=?, pv_pelvis=?, pv_position=?, pv_os=?, pv_osVal=?, ");
			buffer.append(
					"pv_membranes=?, pv_MVal=?, pv_dialated=?, pv_dialatedcm=?, formtype=?, additonal_notes=?, diagonosis=?, ");
			buffer.append(
					"treatment_advice=?, investigation_advice=?,triple=?,days_dating_scan=?,hrf_otherval=?,influenza_vaccine=? ");
			buffer.append("where id='" + bed.getId() + "' ");

			PreparedStatement ps = connection.prepareStatement(buffer.toString());
			// ipdid, clientid, practitionerid, commencing, userid, lastmodified,
			ps.setString(1, bed.getIpdid());
			ps.setString(2, bed.getClientid());
			ps.setString(3, bed.getPractitionerid());
			ps.setString(4, bed.getCommencing());
			ps.setString(5, bed.getUserid());
			ps.setString(6, bed.getLastmodified());

			// visit_reason_ids, hrf_gdm, hrf_pih, hrf_boh, hrf_hypothyroid,
			// hrf_hyperthyroid,
			ps.setString(7, bed.getVisit_reason_ids());
			ps.setString(8, bed.getHrf_gdm());
			ps.setString(9, bed.getHrf_pih());
			ps.setString(10, bed.getHrf_boh());
			ps.setString(11, bed.getHrf_hypothyroid());
			ps.setString(12, bed.getHrf_hyperthyroid());

			// hrf_prev_lscs, hrf_rh_negative, hrf_other, nulligravida, current_pregnant,
			// iud,
			ps.setString(13, bed.getHrf_prev_lscs());
			ps.setString(14, bed.getHrf_rh_negative());
			ps.setString(15, bed.getHrf_other());
			ps.setString(16, bed.getNulligravida());
			ps.setString(17, bed.getCurrent_pregnent());
			ps.setString(18, bed.getIud());

			// live_boys, live_girls, still_birth, abortions, death_child,
			// parity_abortion_notes, g, p, l, a, d,
			ps.setString(19, bed.getLive_boys());
			ps.setString(20, bed.getLive_girls());
			ps.setString(21, bed.getStillbirths());
			ps.setString(22, bed.getAbortions());
			ps.setString(23, bed.getDeath_children());
			ps.setString(24, bed.getParity_abortion_notes());
			ps.setString(25, bed.getG());
			ps.setString(26, bed.getP());
			ps.setString(27, bed.getL());
			ps.setString(28, bed.getA());
			ps.setString(29, bed.getD());

			// surgical_ho, date1, date2, date3, date4, hb1, hb2, hb3, hb4, fbs1, fbs2,
			// fbs3, fbs4,
			ps.setString(30, bed.getSurgical_ho());
			ps.setString(31, bed.getDate1());
			ps.setString(32, bed.getDate2());
			ps.setString(33, bed.getDate3());
			ps.setString(34, bed.getDate4());
			ps.setString(35, bed.getHb1());
			ps.setString(36, bed.getHb2());
			ps.setString(37, bed.getHb3());
			ps.setString(38, bed.getHb4());
			ps.setString(39, bed.getFbs1());
			ps.setString(40, bed.getFbs2());
			ps.setString(41, bed.getFbs3());
			ps.setString(42, bed.getFbs4());

			// dpbs1, dpbs2, dpbs3, dpbs4, urm1, urm2, urm3, urm4, tsh1, tsh2, tsh3, tsh4,
			ps.setString(43, bed.getDpbs1());
			ps.setString(44, bed.getDpbs2());
			ps.setString(45, bed.getDpbs3()); // past_history_notes
			ps.setString(46, bed.getDpbs4());
			ps.setString(47, bed.getUrm1()); // family_history_notes
			ps.setString(48, bed.getUrm2());
			ps.setString(49, bed.getUrm3());
			ps.setString(50, bed.getUrm4());
			ps.setString(51, bed.getTsh1());
			ps.setString(52, bed.getTsh2());
			ps.setString(53, bed.getTsh3());
			ps.setString(54, bed.getTsh4());

			// ict1, ict2, ict3, ict4, gtt1, gtt2, gtt3, gtt4, hvm, hbsag, vdrl, hb_srecta,
			ps.setString(55, bed.getIct1());
			ps.setString(56, bed.getIct2());
			ps.setString(57, bed.getIct3());
			ps.setString(58, bed.getIct4());
			ps.setString(59, bed.getGtt1());
			ps.setString(60, bed.getGtt2());
			ps.setString(61, bed.getGtt3());
			ps.setString(62, bed.getGtt4());
			ps.setString(63, bed.getHv_1m());
			ps.setString(64, bed.getHbs_ag());
			ps.setString(65, bed.getVdrl());
			ps.setString(66, bed.getHb_srecta());

			// hbac, duet_markers, Quradraple_maicers,
			ps.setString(67, bed.getHb_ac());
			ps.setString(68, bed.getDuet_markess());
			ps.setString(69, bed.getQuadrple_maicers());

			// lmp, dating_usg_date, edd, weeks_dating_scan, pog, pog_dating_scan, pmc,
			// usg_report,
			ps.setString(70, bed.getLmp());
			ps.setString(71, bed.getDating_usg_date());
			ps.setString(72, bed.getEdd());
			ps.setString(73, bed.getWeeks_dating_scan());
			ps.setString(74, bed.getPog());
			ps.setString(75, bed.getPog_dating_scan());
			ps.setString(76, bed.getPmc());
			ps.setString(77, bed.getUsg_report());

			// tt_dose1, tt_dose2, gen_condition, temp, pallor, pedal_edema, pulse, bmi,
			// height, weight, sys_bp, dia_bp,
			ps.setString(78, bed.getTt_dose1());
			ps.setString(79, bed.getTt_dose2());
			ps.setString(80, bed.getGen_condition());
			ps.setString(81, bed.getTemp());
			ps.setString(82, bed.getPallor());
			ps.setString(83, bed.getPedal_edema());
			ps.setString(84, bed.getPulse());
			ps.setString(85, bed.getBmi());
			ps.setString(86, bed.getHeight());
			ps.setString(87, bed.getWeight());
			ps.setString(88, bed.getSys_bp());
			ps.setString(89, bed.getDia_bp());

			// wall_edema, pa_soft, fundal_height, cephalic, breech, baley_size,
			// transverse_fhs, cephalicVal, baley_sizeVal,
			ps.setString(90, bed.getWall_edema());
			ps.setString(91, bed.getPa_soft());
			ps.setString(92, bed.getFundal_height());
			ps.setString(93, bed.getCephalic());
			ps.setString(94, bed.getBreech());
			ps.setString(95, bed.getBaley_size());
			ps.setString(96, bed.getTransverse_fhs());
			ps.setString(97, bed.getCephalicVal());
			ps.setString(98, bed.getBaley_sizeVal());

			// external_ballotment, ps_fhs, beats_min, liquor, uterine_contractions,
			// ps_cervix, ps_cervixVal,
			ps.setString(99, bed.getExternal_ballotment());
			ps.setString(100, bed.getPs_fhs());
			ps.setString(101, bed.getBeats_min());
			ps.setString(102, bed.getLiquor());
			ps.setString(103, bed.getUterine_contractions());
			ps.setString(104, bed.getPs_cervix());
			ps.setString(105, bed.getPs_cervixVal());

			// ps_vagine, ps_vagineVal,ps_vault, ps_vaultVal, pv_trim, pv_unettacced,
			// pv_ant, pv_tubular,
			ps.setString(106, bed.getPs_vagine());
			ps.setString(107, bed.getPs_vagineVal());
			ps.setString(108, bed.getPs_vault());
			ps.setString(109, bed.getPs_vaultVal());
			ps.setString(110, bed.getPv_trim());
			ps.setString(111, bed.getPv_unettacced());
			ps.setString(112, bed.getPv_ant());
			ps.setString(113, bed.getPv_tubular());

			// pv_just_ettacced, pv_mid_posits, pv_soft,
			ps.setString(114, bed.getPv_just_ettacced());
			ps.setString(115, bed.getPv_mid_posits());
			ps.setString(116, bed.getPv_soft());

			// pv_ettacced, effaced_text, pv_post, pv_station, pv_liquor, pv_pelvis,
			// pv_position, pv_os, pv_osVal,
			ps.setString(117, bed.getPv_ettacced());
			ps.setString(118, bed.getEffaced_text());
			ps.setString(119, bed.getPv_post());
			ps.setString(120, bed.getPv_station());
			ps.setString(121, bed.getPv_liquor());
			ps.setString(122, bed.getPv_pelvis());
			ps.setString(123, bed.getPv_position());
			ps.setString(124, bed.getPv_os());
			ps.setString(125, bed.getPv_osVal());

			// pv_membranes, pv_MVal, pv_dialated, pv_dialatedcm, formtype, additonal_notes,
			// diagonosis,
			ps.setString(126, bed.getPv_membrane());
			ps.setString(127, bed.getPv_MVal());
			ps.setString(128, bed.getPv_dialated());
			ps.setString(129, bed.getPv_dialatedcm());
			ps.setString(130, bed.getFormtype());
			ps.setString(131, bed.getAdditonal_notes());
			ps.setString(132, bed.getDiagonosis());

			// treatment_advice, investigation_advice, triple, days_dating_scan,
			// hrf_otherval
			ps.setString(133, bed.getTreatment_advice());
			ps.setString(134, bed.getInvestigation_advice());
			ps.setString(135, bed.getTriple());
			ps.setString(136, bed.getDays_dating_scan());
			ps.setString(137, bed.getHrf_otherval());
			ps.setString(138, bed.getInfluenza_vaccine());
			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;

	}

	public int getLastGynicformId(String clientid) {
		int res = 0;
		try {
			String sql = "select id from ipd_new_gynic_form where clientid='" + clientid + "' order by id desc limit 1";
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

	public ArrayList<Priscription> getGynicPrescList(String lastappointmentid) {

		PreparedStatement preparedStatement = null;
		ArrayList<Priscription> list = new ArrayList<Priscription>();
		EmrDAO emrDAO = new JDBCEmrDAO(connection);
		StringBuffer sql = new StringBuffer();
		sql.append(
				"SELECT apm_client_priscription.id,mdicinename,dose,days,notes,priscdurationtype,advoice,dispriscsrno,unit,priscremark,unitextension,dr_qty FROM apm_client_parent_priscription inner join apm_client_priscription ");
		sql.append("on apm_client_priscription.parentid = apm_client_parent_priscription.id ");
		sql.append("where lastappointmentid = '" + lastappointmentid
				+ "' and gynicform=1 and lastappointmentid!='0' order by (dispriscsrno+0) asc  ");

		try {
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			String total = "0";
			int i = 0;
			boolean strengthflag = false;
			boolean quantityflag = false;
			boolean remarkflag = false;
			while (rs.next()) {
				Priscription priscription = new Priscription();
				priscription.setId(rs.getInt(1));
				priscription.setMdicinenametxt(rs.getString(2));
				priscription.setPriscdose(rs.getString(3));
				priscription.setPriscdays(rs.getString(4));
				priscription.setDosenotes(rs.getString(5));
				priscription.setPriscdurationtype(rs.getString(6));
				priscription.setMedicine_shedule("0");
				priscription.setRegional(emrDAO.getregionalText(priscription.getPriscdose()));
				total = total + "," + rs.getInt(1);
				priscription.setTotalchildmedids(total);
				priscription.setDispriscsrno("" + (++i));

				priscription.setStrength(rs.getString(9));
				priscription.setStrengthnew(priscription.getStrength());
				if (priscription.getStrengthnew().equals("0")) {
					priscription.setStrengthnew("");
				}
				priscription.setPriscindivisualremark(rs.getString(10));
				priscription.setUnitextension(rs.getString(11));
				priscription.setDr_qty(rs.getString(12));
				// lokesh 04 July 2018 error while show in ipd discharge form
				if (priscription.getUnitextension() != null) {
					if (priscription.getUnitextension().equals("0")) {
						priscription.setUnitextension("");
					}
				} else {
					priscription.setUnitextension("");
				}

				if (priscription.getStrengthnew() == null) {
					priscription.setStrengthnew("");
				}

				if (!priscription.getUnitextension().equals("")) {
					quantityflag = true;
				}
				if (!priscription.getStrengthnew().equals("")) {
					strengthflag = true;
				}
				if (priscription.getPriscindivisualremark() == null) {
					priscription.setPriscindivisualremark("");
				}
				if (!priscription.getPriscindivisualremark().equals("")) {
					remarkflag = true;
				}

				priscription.setQuantityflag(quantityflag);
				priscription.setStrengthflag(strengthflag);
				priscription.setRemarkflag(remarkflag);
				list.add(priscription);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}

	public ArrayList<String> getPatientNameDataList(String val, String colmnname) {

		ArrayList<String> arrayList = new ArrayList<String>();
		try {
			String sql = "select distinct " + colmnname + " from apm_patient where " + colmnname + " like '" + val
					+ "%' order by " + colmnname + " asc limit 5";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				arrayList.add(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;

	}

	public boolean checkAdmissionView() {
		boolean flag = false;
		try {
			String sql = "select id from view_ipd_addmission_form limit 1";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				flag = true;
			}
		} catch (Exception e) {

		}
		return flag;
	}

	public int createAdmissionView() {
		int res = 0;
		try {
			/*
			 * String sql ="CREATE OR REPLACE VIEW view_ipd_addmission_form AS " +
			 * "SELECT * FROM ipd_addmission_form where bedid!=0";
			 */
			String sql = "CREATE OR REPLACE VIEW view_ipd_addmission_form AS "
					+ "SELECT id,clientid,practitionerid,conditionid,admissiondsate,treatmentepisodeid,"
					+ "speciality,ismlc,ipdseqno,ipdabrivationid,bedid " + " FROM ipd_addmission_form where bedid!=0";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public boolean checkDischargeView() {
		boolean flag = false;
		try {
			String sql = "select id from view_apm_treatment_episode limit 1";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				flag = true;
			}
		} catch (Exception e) {

		}
		return flag;
	}

	public int createDischargeView() {
		int res = 0;
		try {
			/*
			 * String sql ="CREATE OR REPLACE VIEW view_apm_treatment_episode AS " +
			 * "select apm_treatment_episode.* from apm_treatment_episode " +
			 * "inner join ipd_addmission_form on ipd_addmission_form.treatmentepisodeid = apm_treatment_episode.id "
			 * + "where bedid!=0 ";
			 */
			String sql = "CREATE OR REPLACE VIEW view_apm_treatment_episode AS "
					+ "select apm_treatment_episode.id,dis_initiate_time, dis_initiate_status, dis_form_time, dis_form_status,"
					+ "dis_mdicine_time, dis_mdicine_status, dis_bill_time, dis_bill_status, dis_nursing_time,"
					+ "dis_nursing_status,treatmentstatus,dischargedate " + " from apm_treatment_episode "
					+ "inner join ipd_addmission_form on ipd_addmission_form.treatmentepisodeid = apm_treatment_episode.id "
					+ "where bedid!=0 ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int updateUserLanaguage(String language, String userId) {
		// user_language_access
		int result = 0;
		try {
			String sql = "update apm_user set user_language_access='" + language + "' where userid='" + userId + "' ";
			PreparedStatement ps = connection.prepareStatement(sql);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getConditionidofipdid(String ipdid) {
		String flag = "";
		try {
			String sql = "select conditionid from ipd_addmission_form where id=" + ipdid + "";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				flag = rs.getString(1);
			}
		} catch (Exception e) {

		}
		return flag;
	}

	public boolean isPatientDeadOrLAMADAMAForBGH(String clientId) {
		boolean flag = false;
		try {
			String sql = "select * from apm_treatment_episode where  dschargestatus in ('3','6','7') and clientid='"
					+ clientId + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public void clearIpdDetails(String ipdid) {

		BedDao bedDao = new JDBCBedDao(connection);
		Bed bed = bedDao.getEditIpdData(ipdid);

		int ipdCheck = DateTimeUtils.convertToInteger(bed.getIpdnewid());
		if (ipdCheck > 0) {
			deleteIpdData(bed);
			deleteIpdBillingData(bed);
			deleteIpdAdvAndRef(bed);
			deleteIpdInvestigationData(bed);
			deleteIpdPrescriptionData(bed);
		}

	}

	private void deleteIpdBillingData(Bed bed) {
		try {

			String mainQuery = "select id from apm_charges_invoice where ipdid='" + bed.getIpdnewid() + "' ";
			PreparedStatement ps = connection.prepareStatement(mainQuery);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int invoiceId = rs.getInt(1);
				String sql = " delete from apm_charges_invoice where id='" + invoiceId + "' ";
				ps = connection.prepareStatement(sql);
				int x = ps.executeUpdate();
				if (x > 0) {
					deleteinvoiceCharges(invoiceId);
					deletePayment("" + invoiceId);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void deleteinvoiceCharges(int invoiceId) {

		try {
			String mainQuery = "select invoiceid from apm_charges_assesment where chargeinvoiceid='" + invoiceId + "' ";
			PreparedStatement ps = connection.prepareStatement(mainQuery);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				int chargeInvoiceId = rs.getInt(1);
				deleteMetaCharge("" + chargeInvoiceId);
				deleteInnerCharge("" + chargeInvoiceId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void deletePayment(String invoicId) {
		try {
			String sql = " delete from from apm_charges_payment where chargeinvoiceid='" + invoicId + "' ";
			PreparedStatement ps = connection.prepareStatement(sql);
			int x = ps.executeUpdate();
			System.out.println("deletePayment->" + x);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void deleteMetaCharge(String invoicId) {
		try {
			String sql = " delete from from apm_invoice where id='" + invoicId + "' ";
			PreparedStatement ps = connection.prepareStatement(sql);
			int x = ps.executeUpdate();
			System.out.println("deleteMetaCharge->" + x);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void deleteInnerCharge(String invoicId) {
		try {
			String sql = " delete from from apm_invoice_assesments where invoiceid='" + invoicId + "' ";
			PreparedStatement ps = connection.prepareStatement(sql);
			int x = ps.executeUpdate();
			System.out.println("deleteInnerCharge->" + x);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void deleteIpdData(Bed bed) {
		try {
			String sql = " delete from ipd_addmission_form where id='" + bed.getIpdnewid() + "' ";
			PreparedStatement ps = connection.prepareStatement(sql);
			int x = ps.executeUpdate();
			System.out.println("deleteIpdData->" + x);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void deleteIpdInvestigationData(Bed bed) {
		try {
			String sql = "delete from apm_client_parent_investigation where ipdid='" + bed.getIpdnewid() + "'";
			PreparedStatement ps = connection.prepareStatement(sql);
			int x = ps.executeUpdate();
			System.out.println("deleteIpdInvestigationData->" + x);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void deleteIpdPrescriptionData(Bed bed) {
		try {
			String sql = "delete from apm_client_parent_priscription where ipdid='" + bed.getIpdnewid() + "'";
			PreparedStatement ps = connection.prepareStatement(sql);
			int x = ps.executeUpdate();
			System.out.println("deleteIpdPrescriptionData->" + x);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void deleteIpdAdvAndRef(Bed bed) {
		try {
			String sql = "delete from apm_credit_account where advance_ipdid='" + bed.getIpdnewid() + "'";
			PreparedStatement ps = connection.prepareStatement(sql);
			int x = ps.executeUpdate();
			System.out.println("deleteIpdAdvAndRef->" + x);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveIpdInvoiceDataClearLog(LoginInfo loginInfo, Accounts accounts) {
		try {
			String dtime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			String details = "Invoices, Investigations, Prescriptions, Advance and Refunds, Ipd Details";
			String sql = " insert into deleted_invoice_data_log(datetime,userid,details,clientId,billid,ipdid) values(?,?,?,?,?,?)  ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, dtime);
			ps.setString(2, "" + loginInfo.getUserId());
			ps.setString(3, details);
			ps.setString(4, "" + accounts.getClientid());
			ps.setString(5, "" + accounts.getId());
			ps.setString(6, "" + accounts.getIpdid());
			int x = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public int updateAdm_Tp(String selectedid, String typeName) {
		int res = 0;
		try {
			typeName = DateTimeUtils.numberCheck(typeName);
			String sql = "update ipd_addmission_form set tpid='" + typeName + "' where id='" + selectedid + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int updateAdmNew_Tp(String selectedid, String typeName) {
		int res = 0;
		try {
			typeName = DateTimeUtils.numberCheck(typeName);
			String sql = "update ipd_addmission_form_new set tpid='" + typeName + "' where id='" + selectedid + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public ArrayList<Priscription> getDischargeParentPriscIds(String selectedid) {
		ArrayList<Priscription> arrayList = new ArrayList<>();
		try {
			String sql = "select id,fromtreatmentgiven,advoice from apm_client_parent_priscription where ipdid ='"
					+ selectedid + "' and discharge=1 ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			String ids = "0";
			while (rs.next()) {
				Priscription priscription = new Priscription();
				priscription.setId(rs.getInt(1));
				priscription.setFromtreatmentgiven(rs.getInt(2));
				priscription.setAdvoice(rs.getString(3));
				ids = ids + "," + rs.getInt(1);
				priscription.setIds(ids);
				arrayList.add(priscription);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	@Override
	public ArrayList<Priscription> getDichargePriscriptionList(String ids, String apmtId) {
		ArrayList<Priscription> arrayList = new ArrayList<>();
		try {
			EmrDAO emrDAO = new JDBCEmrDAO(connection);
			StringBuffer buffer = new StringBuffer();
			buffer.append("SELECT apm_client_priscription.id,mdicinename,dose,days,notes,priscdurationtype, ");
			buffer.append("dispriscsrno,unit,priscremark,unitextension,dr_qty,parentid,prisctimename ");
			buffer.append(" FROM apm_client_priscription ");
			buffer.append("where parentid in (" + ids + ") ");
			if (!DateTimeUtils.numberCheck(apmtId).equals("0")) {
				buffer.append("order by (sqno+0) asc ");
			} else {
				buffer.append("order by (dispriscsrno+0) asc ");
			}

			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			String total = "0";
			int i = 0;
			boolean strengthflag = false;
			boolean quantityflag = false;
			boolean remarkflag = false;
			while (rs.next()) {
				Priscription priscription = new Priscription();
				priscription.setId(rs.getInt(1));
				priscription.setMdicinenametxt(rs.getString(2));
				priscription.setPriscdose(rs.getString(3));
				priscription.setPriscdays(rs.getString(4));
				priscription.setDosenotes(rs.getString(5));
				priscription.setPriscdurationtype(rs.getString(6));
				priscription.setMedicine_shedule("0");
				priscription.setRegional(emrDAO.getregionalText(priscription.getPriscdose()));
				total = total + "," + rs.getInt(1);
				priscription.setTotalchildmedids(total);
				priscription.setDispriscsrno("" + (++i));

				priscription.setStrength(rs.getString(8));
				priscription.setStrengthnew(priscription.getStrength());
				if (priscription.getStrengthnew().equals("0")) {
					priscription.setStrengthnew("");
				}
				priscription.setPriscindivisualremark(rs.getString(9));
				priscription.setUnitextension(rs.getString(10));
				priscription.setDr_qty(rs.getString(11));
				// lokesh 04 July 2018 error while show in ipd discharge form
				if (priscription.getUnitextension() != null) {
					if (priscription.getUnitextension().equals("0")) {
						priscription.setUnitextension("");
					}
				} else {
					priscription.setUnitextension("");
				}

				if (priscription.getStrengthnew() == null) {
					priscription.setStrengthnew("");
				}

				if (!priscription.getUnitextension().equals("")) {
					quantityflag = true;
				}
				if (!priscription.getStrengthnew().equals("")) {
					strengthflag = true;
				}
				if (priscription.getPriscindivisualremark() == null) {
					priscription.setPriscindivisualremark("");
				}
				if (!priscription.getPriscindivisualremark().equals("")) {
					remarkflag = true;
				}
				priscription.setQuantityflag(quantityflag);
				priscription.setStrengthflag(strengthflag);
				priscription.setRemarkflag(remarkflag);
				priscription.setParentid(rs.getString(12));
				priscription.setPrisctimename(rs.getString(13));
				arrayList.add(priscription);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	@Override
	public String getAdmissionParentPriscIds(String selectedid) {
		String ids = "0";
		try {
			String sql = "select id from apm_client_parent_priscription where ipdid ='" + selectedid
					+ "' and admission=1 ";
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
	public ArrayList<Priscription> getAdmissionPriscList(String ids) {

		PreparedStatement preparedStatement = null;
		ArrayList<Priscription> list = new ArrayList<Priscription>();
		EmrDAO emrDAO = new JDBCEmrDAO(connection);
		StringBuffer sql = new StringBuffer();
		sql.append(
				"SELECT apm_client_priscription.id,mdicinename,dose,days,notes,priscdurationtype,parentid,dispriscsrno,");
		sql.append("unit,priscremark,unitextension,nurse_qty FROM apm_client_priscription ");
		sql.append("where parentid in (" + ids + ") order by (dispriscsrno+0) asc ");
		try {
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			String total = "0";
			int i = 0;
			boolean strengthflag = false;
			boolean quantityflag = false;
			boolean remarkflag = false;
			while (rs.next()) {
				Priscription priscription = new Priscription();
				priscription.setId(rs.getInt(1));
				priscription.setMdicinenametxt(rs.getString(2));
				priscription.setPriscdose(rs.getString(3));
				priscription.setPriscdays(rs.getString(4));
				priscription.setDosenotes(rs.getString(5));
				priscription.setPriscdurationtype(rs.getString(6));
				priscription.setMedicine_shedule("0");
				priscription.setRegional(emrDAO.getregionalText(priscription.getPriscdose()));
				total = total + "," + rs.getInt(1);
				priscription.setTotalchildmedids(total);
				priscription.setDispriscsrno("" + (++i));

				priscription.setStrength(rs.getString(9));
				priscription.setStrengthnew(priscription.getStrength());
				if (priscription.getStrengthnew().equals("0")) {
					priscription.setStrengthnew("");
				}
				priscription.setPriscindivisualremark(rs.getString(10));
				priscription.setUnitextension(rs.getString(11));
				// lokesh 04 July 2018 error while show in ipd discharge form
				if (priscription.getUnitextension() != null) {
					if (priscription.getUnitextension().equals("0")) {
						priscription.setUnitextension("");
					}
				} else {
					priscription.setUnitextension("");
				}

				if (priscription.getStrengthnew() == null) {
					priscription.setStrengthnew("");
				}

				if (!priscription.getUnitextension().equals("")) {
					quantityflag = true;
				}
				if (!priscription.getStrengthnew().equals("")) {
					strengthflag = true;
				}
				if (priscription.getPriscindivisualremark() == null) {
					priscription.setPriscindivisualremark("");
				}
				if (!priscription.getPriscindivisualremark().equals("")) {
					remarkflag = true;
				}

				priscription.setQuantityflag(quantityflag);
				priscription.setStrengthflag(strengthflag);
				priscription.setRemarkflag(remarkflag);
				priscription.setPriscqty("" + rs.getInt(12));
				list.add(priscription);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}

	@Override
	public ArrayList<Bed> getIpdReportKalmegh(String fromdate, String todate, String docotrid) {
		ArrayList<Bed> arrayList = new ArrayList<>();
		try {
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			StringBuffer buffer = new StringBuffer();
			buffer.append(
					"select ipd_fake_data.id, clientid, diagnosis, doctorid, dateOfAdmission, dateOfDischarge, ipdabrivationid,");
			buffer.append("concat(apm_user.initial,' ',apm_user.firstname,' ',apm_user.lastname),discription,ischeck ");
			buffer.append("from ipd_fake_data ");
			buffer.append("inner join apm_user on apm_user.id = ipd_fake_data.doctorid ");

			if (docotrid != null) {
				if (!docotrid.equals("0")) {
					buffer.append("and doctorid=" + docotrid + " ");
				}
			}
			buffer.append("where dateOfAdmission between '" + fromdate + "' and '" + todate + "' ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Bed bed = new Bed();
				bed.setId(rs.getInt(1));
				bed.setClientid(rs.getString(2));
				bed.setDiagnosis(rs.getString(3));
				bed.setPractitionerid(rs.getString(4));
				bed.setAdmissiondate(DateTimeUtils.getCommencingDate1(rs.getString(5)));
				bed.setDischargeDate(DateTimeUtils.isNull(rs.getString(6)));
				bed.setIpdabrivationid(rs.getString(7));
				bed.setPractitionername(rs.getString(8));
				bed.setIscheck(rs.getInt(10));
				String department = masterDAO.departmentid(rs.getString(9));
				bed.setDepartment(department);
				Client client = clientDAO.getClientDetails(rs.getString(2));
				bed.setClientname(client.getTitle() + " " + client.getFirstName() + " " + client.getLastName());
				bed.setAbrivationid(client.getAbrivationid());
				bed.setOldClientId(client.getOldclientId());
				bed.setClientAddress(client.getAddress());
				bed.setGender(client.getGender());
				bed.setAge(DateTimeUtils.isNull(DateTimeUtils.getAge1(client.getDob())));
				arrayList.add(bed);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	@Override
	public Bed getConsultationNotesDiagnosis(String apmtId) {
		Bed bed = new Bed();
		try {
			// String sql = "Select conditionid from apm_final_diagnosis where ipdid=" +
			// ipdid + "";
			String sql = "Select conditionid from consultant_notes_diagnosis where apmtid=" + apmtId + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bed.setConditionname(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bed;
	}

	@Override
	public int deleteFinalConditionsCF(String apmtId) {
		int res = 0;
		try {
			String sql = "delete from consultant_notes_diagnosis where apmtid=" + apmtId + " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int savefinalConditionCF(String apmtId, String allconditions,String chief_complains,String history, String investigation,String notes,String observation, String treatment) {
		int result = 0;
		int id = 0;
		try {
			String sql1 = "SELECT id from consultant_notes_diagnosis where apmtId='" + apmtId + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql1);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				id = rs.getInt(1);
			}
			if (id == 0) {
				String sql = "insert into consultant_notes_diagnosis (apmtId,conditionid,chiefcomplainid,historyid,investigationid,notesid,observid,treatmentid) values (?,?,?,?,?,?,?,?)";
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setString(1, apmtId);
				ps.setString(2, allconditions);
				ps.setString(3, chief_complains);
				ps.setString(4, history);
				ps.setString(5, investigation);
				ps.setString(6, notes);
				ps.setString(7, observation);	
				ps.setString(8, treatment);
				result = ps.executeUpdate();
			} else {
				String sql = "update consultant_notes_diagnosis set conditionid=? and chiefcomplainid=? and historyid=? and investigationid=? and notesid=? and observid=? and treatmentid=? where id=" + id + "";
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setString(1, allconditions);
				ps.setString(2, chief_complains);
				ps.setString(3, history);
				ps.setString(4, investigation);
				ps.setString(5, notes);
				ps.setString(6, observation);
				ps.setString(7, treatment);
				result = ps.executeUpdate();
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;

	}

	@Override
	public int getConsulationFormId(String apmtId) {
		int res = 0;
		try {
			String sql = "select id from consultation_form where apmt_id='" + apmtId + "'";
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
	public int updateConsultationForm(Bed bed, int consulationFormId) {
		int res = 0;
		try {
			String sql = "update consultation_form set chief_complains=?,investigation=?,"
					+ "discadvnotes=?,notes=?,udpate_user_id=?,update_date_time=?,medicine_text=?, examination_finding=?,ondiet=?, karma=?,procedurebams=?,consult_data=?,priscription=?,vision=?,LSplRFI=?,LCylRFI=?,LAxisRFI=?,RSplRFI=?,RCylRFI=?,RAxisRFI=?,consult_data=?,vision=?,history=?,remark=?,observation=?,treatment=?,consult_email=?  "
					+ "where id='" + consulationFormId + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, bed.getChiefcomplains());
			preparedStatement.setString(2, bed.getInvestigation());
			preparedStatement.setString(3, bed.getDiscadvnotes());
			preparedStatement.setString(4, bed.getNotes());
			preparedStatement.setString(5, bed.getUserid());
			preparedStatement.setString(6, bed.getDatetime());
			preparedStatement.setString(7, bed.getKunal_manual_medicine_text());
			preparedStatement.setString(8, bed.getExamination());
			preparedStatement.setString(9, bed.getOndiet());
			preparedStatement.setString(10, bed.getKarma());
			preparedStatement.setString(11, bed.getProcesurebams());
			preparedStatement.setString(12, bed.getConsNote());
			preparedStatement.setString(13, bed.getPriscription());
			preparedStatement.setString(14, bed.getEyereading());
			preparedStatement.setString(15, bed.getLSplRFI());
			preparedStatement.setString(16, bed.getLCylRFI());
			preparedStatement.setString(17, bed.getLAxisRFI());
			preparedStatement.setString(18, bed.getRSplRFI());
			preparedStatement.setString(19, bed.getRCylRFI());	
			preparedStatement.setString(20, bed.getRAxisRFI());
			preparedStatement.setString(21, bed.getConsNote());
			preparedStatement.setString(22, bed.getEyereading());
			preparedStatement.setString(23, bed.getHistory());
			preparedStatement.setString(24, bed.getRemark());
			preparedStatement.setString(25, bed.getObservation());
			preparedStatement.setString(26, bed.getTreatmentgiven());
			preparedStatement.setString(27, bed.getClientemail());
			
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int insertConsultationForm(Bed bed) {
		int res = 0;
		try {
			String sql = "insert into consultation_form (client_id,who_pay,tp_id,apmt_id,"
					+ "user_id,date_time,chief_complains,investigation,discadvnotes,notes,"
					+ "udpate_user_id,update_date_time,medicine_text,examination_finding,ondiet,karma,procedurebams,ipdid,LSplRFI,LCylRFI,LAxisRFI,RSplRFI,RCylRFI,RAxisRFI,consult_data,priscription,vision,history,remark,observation,treatment,consult_email) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, bed.getClientid());
			preparedStatement.setString(2, bed.getWhopay());
			preparedStatement.setString(3, bed.getTpid());
			preparedStatement.setString(4, bed.getAppointmentid());
			preparedStatement.setString(5, bed.getUserid());
			preparedStatement.setString(6, bed.getDatetime());
			preparedStatement.setString(7, bed.getChiefcomplains());
			preparedStatement.setString(8, bed.getInvestigation());
			preparedStatement.setString(9, bed.getDiscadvnotes());
			preparedStatement.setString(10, bed.getNotes());
			preparedStatement.setString(11, bed.getUserid());
			preparedStatement.setString(12, bed.getDatetime());
			preparedStatement.setString(13, bed.getKunal_manual_medicine_text());
			preparedStatement.setString(14, bed.getExamination());
			preparedStatement.setString(15, bed.getOndiet());
			preparedStatement.setString(16, bed.getKarma());
			preparedStatement.setString(17, bed.getProcesurebams());
			preparedStatement.setString(18, bed.getIpdid());
			preparedStatement.setString(19, bed.getLSplRFI());
			preparedStatement.setString(20, bed.getLCylRFI());
			preparedStatement.setString(21, bed.getLAxisRFI());
			preparedStatement.setString(22, bed.getRSplRFI());
			preparedStatement.setString(23, bed.getRCylRFI());
			preparedStatement.setString(24, bed.getRAxisRFI());
			preparedStatement.setString(25, bed.getConsNote());
			preparedStatement.setString(26, bed.getPriscription());
			preparedStatement.setString(27, bed.getEyereading());
			preparedStatement.setString(28, bed.getHistory());
			preparedStatement.setString(29, bed.getRemark());
			preparedStatement.setString(30, bed.getObservation());
			preparedStatement.setString(31, bed.getTreatmentgiven());
			preparedStatement.setString(32, bed.getClientemail());			
			res = preparedStatement.executeUpdate();
			
			if (res > 0) {
				ResultSet resultSet = preparedStatement.getGeneratedKeys();

				while (resultSet.next()) {
					res = resultSet.getInt(1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public Bed getConsultationFormDetails(String apmtId,String id2) {
		Bed bed = new Bed();
		StringBuffer sql=new StringBuffer();
		try {
			 sql.append("select id, client_id, who_pay, tp_id, apmt_id, user_id, date_time, "
					+ "chief_complains, investigation, discadvnotes, notes, "
					+ "udpate_user_id, update_date_time,medicine_text,examination_finding,ondiet,karma,procedurebams,LSplRFI,LCylRFI,LAxisRFI,RSplRFI,RCylRFI,RAxisRFI,priscription,vision,history,remark "
					+ "from consultation_form  where ");
			         if(!apmtId.equals("0")) {
			       sql.append("apmt_id='" + apmtId + "'");
			 		}else {
			 			sql.append("id=" + id2 + "");
			 		}
			PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				bed.setId(rs.getInt(1));
				bed.setClientid(rs.getString(2));
				bed.setWhopay(rs.getString(3));
				bed.setTpid(rs.getString(4));
				bed.setAppointmentid(rs.getString(5));
				bed.setUserid(rs.getString(6));
				bed.setDatetime(rs.getString(7));
				if (DateTimeUtils.isNull(rs.getString(8)).equals("<br>")) {
					bed.setChiefcomplains("");
				} else {
					bed.setChiefcomplains(DateTimeUtils.isNull(rs.getString(8)).replace("<br>", ""));
				}
				if (DateTimeUtils.isNull(rs.getString(9)).equals("<br>")) {
					bed.setInvestigation("");
				} else {
					bed.setInvestigation(DateTimeUtils.isNull(rs.getString(9)).replace("<br><br>", ""));
				}
				if (DateTimeUtils.isNull(rs.getString(10)).equals("<br>")) {
					bed.setDiscadvnotes("");
				} else {
					bed.setDiscadvnotes(DateTimeUtils.isNull(rs.getString(10)).replace("<br>", ""));
				}
				if (DateTimeUtils.isNull(rs.getString(11)).equals("<br>")) {
					bed.setNotes("");
				} else {
					bed.setNotes(DateTimeUtils.isNull(rs.getString(11).replace("<br>", "")));
				}
				if (DateTimeUtils.isNull(rs.getString(14)).equals("<br>")) {
					bed.setKunal_manual_medicine_text("");
				} else {
					bed.setKunal_manual_medicine_text(DateTimeUtils.isNull(rs.getString(14)));
				}
				bed.setUpdateUserId(rs.getString(12));
				bed.setUpdateDateTime(rs.getString(13));
				bed.setExamination(rs.getString(15));

				if (DateTimeUtils.isNull(rs.getString(16)).equals("<br>")) {
					bed.setOndiet("");
				} else {
					bed.setOndiet(DateTimeUtils.isNull(rs.getString(16)));
				}
				if (DateTimeUtils.isNull(rs.getString(17)).equals("<br>")) {
					bed.setKarma("");
				} else {
					bed.setKarma(DateTimeUtils.isNull(rs.getString(17)));
				}
				if (DateTimeUtils.isNull(rs.getString(18)).equals("<br>")) {
					bed.setProcesurebams("");
				} else {
					bed.setProcesurebams(DateTimeUtils.isNull(rs.getString(18)));
				}
                bed.setLSplRFI(rs.getString(19));
                bed.setLCylRFI(rs.getString(20));
                bed.setLAxisRFI(rs.getString(21));
                bed.setRSplRFI(rs.getString(22));
                bed.setRCylRFI(rs.getString(23));
                bed.setRAxisRFI(rs.getString(24));
                bed.setConsNote(rs.getString(25));
                bed.setEyereading(rs.getString(26));
                bed.setHistory(rs.getString(27));
                bed.setRemark(rs.getString(28));
                
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bed;
	}

	@Override
	public int getIpdTemplateId(String string) {
		int id = 0;
		try {
			String sql = "select id from ipd_templatename where name=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, string);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public ArrayList<Priscription> getConsultFormPrescList(String apmtId) {

		PreparedStatement preparedStatement = null;
		ArrayList<Priscription> list = new ArrayList<Priscription>();
		EmrDAO emrDAO = new JDBCEmrDAO(connection);
		StringBuffer sql = new StringBuffer();
		sql.append(
				"SELECT apm_client_priscription.id,mdicinename,dose,days,notes,priscdurationtype,advoice,dispriscsrno,unit,priscremark,unitextension,dr_qty,prisctimename FROM apm_client_parent_priscription inner join apm_client_priscription ");
		sql.append("on apm_client_priscription.parentid = apm_client_parent_priscription.id ");
		sql.append("where cf_apmt_id = " + apmtId + " order by (sqno+0) asc  ");

		try {
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			String total = "0";
			int i = 0;
			boolean strengthflag = false;
			boolean quantityflag = false;
			boolean remarkflag = false;
			while (rs.next()) {
				Priscription priscription = new Priscription();
				priscription.setId(rs.getInt(1));
				priscription.setMdicinenametxt(rs.getString(2));
				priscription.setPriscdose(rs.getString(3));
				priscription.setPriscdays(rs.getString(4));
				priscription.setDosenotes(rs.getString(5));
				priscription.setPriscdurationtype(rs.getString(6));
				priscription.setMedicine_shedule("0");
				priscription.setRegional(emrDAO.getregionalText(priscription.getPriscdose()));
				total = total + "," + rs.getInt(1);
				priscription.setTotalchildmedids(total);
				priscription.setDispriscsrno("" + (++i));

				priscription.setStrength(rs.getString(9));
				priscription.setStrengthnew(priscription.getStrength());
				if (priscription.getStrengthnew().equals("0")) {
					priscription.setStrengthnew("");
				}
				priscription.setPriscindivisualremark(rs.getString(10));
				priscription.setUnitextension(rs.getString(11));
				priscription.setDr_qty(rs.getString(12));
				priscription.setPrisctimename(rs.getString(13));
				// lokesh 04 July 2018 error while show in ipd discharge form
				if (priscription.getUnitextension() != null) {
					if (priscription.getUnitextension().equals("0")) {
						priscription.setUnitextension("");
					}
				} else {
					priscription.setUnitextension("");
				}

				if (priscription.getStrengthnew() == null) {
					priscription.setStrengthnew("");
				}

				if (!priscription.getUnitextension().equals("")) {
					quantityflag = true;
				}
				if (!priscription.getStrengthnew().equals("")) {
					strengthflag = true;
				}
				if (priscription.getPriscindivisualremark() == null) {
					priscription.setPriscindivisualremark("");
				}
				if (!priscription.getPriscindivisualremark().equals("")) {
					remarkflag = true;
				}

				priscription.setQuantityflag(quantityflag);
				priscription.setStrengthflag(strengthflag);
				priscription.setRemarkflag(remarkflag);
				list.add(priscription);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public ArrayList<Priscription> getCFParentPriscIds(String apmtId) {

		ArrayList<Priscription> arrayList = new ArrayList<>();
		try {
			String sql = "select id,fromtreatmentgiven,advoice from apm_client_parent_priscription where cf_apmt_id  ='"
					+ apmtId + "' ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			String ids = "0";
			while (rs.next()) {
				Priscription priscription = new Priscription();
				priscription.setId(rs.getInt(1));
				priscription.setFromtreatmentgiven(rs.getInt(2));
				priscription.setAdvoice(rs.getString(3));
				ids = ids + "," + rs.getInt(1);
				priscription.setIds(ids);
				arrayList.add(priscription);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;

	}

	@Override
	public ArrayList<Bed> getConsultantNoteHistory(String clientid) {
		// TODO Auto-generated method stub
		ArrayList<Bed> list = new ArrayList<>();

		try {

			String sql = "select id,date_time,apmt_id from consultation_form where client_id=" + clientid + "";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {

				Bed bed = new Bed();

				bed.setId(rs.getInt(1));
				bed.setDatetime(rs.getString(2));
				bed.setAppointmentid(rs.getString(3));
				String doc_name=getDoctorname_apmtid(rs.getString(3));
				bed.setPractitionername(doc_name);
                bed.setClientid(clientid);
				list.add(bed);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return list;
	}

	private String getDoctorname_apmtid(String apmtid) {
		String docname = "";
		try {

			String sql = "select diaryusername from apm_available_slot where id=" + apmtid + "";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				docname = rs.getString(1);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return docname;
	}

	@Override
	public String getDateTime(String clientId, String apmtId) {
		// TODO Auto-generated method stub
		String datetime = "";
		try {

			String sql = "select date_time from consultation_form where client_id=" + clientId + " and apmt_id="
					+ apmtId + "";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				datetime = rs.getString(1);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return datetime;
	}

	@Override
	public int getTotalIPDcount(String fromdate, String todate, String doctorid) {
		// TODO Auto-generated method stub

		int res = 0;
		try {

			StringBuffer buffer = new StringBuffer();
			buffer.append("select count(*) from ipd_fake_data where dateOfAdmission between '" + fromdate + "' and '"
					+ todate + " 59:59:59' ");

			if (doctorid != null) {
				if (!doctorid.equals("0")) {
					buffer.append("and doctorid=" + doctorid + " ");
				}
			}

			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				res = rs.getInt(1);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return res;

	}

	@Override
	public int getupdateDischrgeDate(String clientid, String date) {
		// TODO Auto-generated method stub

		int res = 0;

		try {

			String sql = "update ipd_fake_data set dateOfDischarge=?" + "where clientid='" + clientid + "'";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, date);

			res = preparedStatement.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return res;
	}

	@Override
	public Bed editdischargeDate(String selectedid) {
		// TODO Auto-generated method stub

		PreparedStatement pmt = null;
		Bed bed = new Bed();

		try {

			String sql = "select id,dateOfDischarge from ipd_fake_data  where id=" + selectedid + "";
			pmt = connection.prepareStatement(sql);

			ResultSet rs = pmt.executeQuery();

			while (rs.next()) {

				bed.setId(rs.getInt(1));
				bed.setDischargedate(rs.getString(2));

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return bed;

	}

	@Override
	public ArrayList<Bed> getIpdConsultFake(String fromdate, String todate, String doctorid, String userid,
			String searchtext, String dept) {

		ArrayList<Bed> arrayList = new ArrayList<>();
		try {
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			StringBuffer buffer = new StringBuffer();
			/*
			 * buffer.
			 * append("select ipd_addmission_form.id,ipd_addmission_form.clientid,ipd_addmission_form.practitionerid,admissiondsate,dischargedate,concat(apm_user.initial,' ',apm_user.firstname,' ',apm_user.lastname),apm_user.discription from ipd_addmission_form "
			 * ); buffer.
			 * append("inner join apm_treatment_episode on apm_treatment_episode.id=ipd_addmission_form.treatmentepisodeid  "
			 * ); buffer.
			 * append("inner join apm_user on apm_user.id = ipd_addmission_form.practitionerid "
			 * );
			 */

			buffer.append(
					"select ipd_addmission_form.id,ipd_addmission_form.clientid,ipd_addmission_form.practitionerid,admissiondsate,dischargedate,concat(apm_user.initial,' ',apm_user.firstname,' ',apm_user.lastname),apm_user.discription,ipd_addmission_form.wardid,mbbsseqno from ipd_addmission_form ");
			buffer.append("inner join apm_user on apm_user.id=ipd_addmission_form.practitionerid ");
			buffer.append("inner join apm_discipline on apm_discipline.id=apm_user.discription inner join ");
			buffer.append("apm_treatment_episode on apm_treatment_episode.id=ipd_addmission_form.treatmentepisodeid ");

			if (dept.equals("")) {
				buffer.append("where admissiondsate between '" + fromdate + "' and '" + todate + " 59:59:59' ");
			} else {

				buffer.append("where admissiondsate between '" + fromdate + "' and '" + todate + " 59:59:59' and apm_discipline.id='" + dept + "' ");
			}
			// buffer.append("where admissiondsate between '"+fromdate+"' and '"+todate+"
			// 59:59:59' ");

			if (doctorid != null) {
				if (!doctorid.equals("0")) {
					buffer.append("and practitionerid=" + doctorid + " ");
				}
			}

			if (!userid.equals("")) {
				buffer.append("and ipd_addmission_form.addmited_by_userid=" + userid + " ");
			}

			if (searchtext != null) {
				buffer.append("and clientname like('%" + searchtext + "%') or ipd_addmission_form.clientid='"
						+ searchtext + "' ");
			}
			buffer.append("order by id desc");
			/*
			 * if(!dept.equals("")) { buffer.append("and apm_discipline.id='"+dept+"' "); }
			 */

			// buffer.append("where admissiondsate between '"+fromdate+"' and '"+todate+"
			// 59:59:59' ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Bed bed = new Bed();
				bed.setId(rs.getInt(1));
				bed.setClientid(rs.getString(2));
				// bed.setDiagnosis(rs.getString(3));
				bed.setPractitionerid(rs.getString(3));
				bed.setAdmissiondate((rs.getString(4).split(" ")[0]));
				bed.setDischargeDate(DateTimeUtils.isNull(rs.getString(5)).split(" ")[0]);
				// bed.setIpdabrivationid(rs.getString(6));
				bed.setPractitionername(rs.getString(6));
				// bed.setIscheck(rs.getInt(8));
				String department = masterDAO.departmentid(rs.getString(7));
				bed.setDepartment(department);
				Client client = clientDAO.getClientDetails(rs.getString(2));
				bed.setClientname(client.getTitle() + " " + client.getFirstName() + " " + client.getLastName());
				bed.setAbrivationid(client.getAbrivationid());
				bed.setOldClientId(client.getOldclientId());
				bed.setClientAddress(client.getAddress());
				bed.setGender(client.getGender());
				bed.setAge(DateTimeUtils.isNull(DateTimeUtils.getAge1(client.getDob())));

				String conditionid = getCondionid(rs.getString(1));

				String temp[] = conditionid.split(",");
				String diagnosis = "";
				for (String string : temp) {
					if (!string.equals("0")) {
						String diagnosis1 = getdiagnosisbyconditionid(string);
						if (diagnosis.equals("")) {
							diagnosis = diagnosis1;
						} else {
							diagnosis = diagnosis + ", " + diagnosis1;
						}
					}
				}

				bed.setDiagnosis(diagnosis);

				Bed bed1 = getIpdWardBedName(bed.getId());
				bed.setWardname(bed1.getWardname());
				bed.setBedname(bed1.getBedname());

				Bed bed2 = getOpdabrivationidbyclientid(bed.getClientid(), bed.getId());
				bed.setOpdabbrivationid(bed2.getOpdabbrivationid());
				bed.setOpdid(bed2.getOpdid());
				bed.setIpdseqno(rs.getString(9));
				bed.setOpdsequence(bed2.getOpdsequence());
				arrayList.add(bed);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;

	}

	private Bed getOpdabrivationidbyclientid(String clientid, int id) {
		PreparedStatement preparedStatement = null;
		Bed bed = new Bed();
		try {
			String sql = "select id,opdabbrevationid,opdSequnce from apm_available_slot where clientId='" + clientid
					+ "' and admissionid='" + id + "'";
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				bed.setOpdid(rs.getString(1));
				bed.setOpdabbrivationid(rs.getString(2));
				bed.setOpdsequence(rs.getString(3));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return bed;
	}

	private Bed getIpdWardBedName(int admissionid) {

		PreparedStatement preparedStatement = null;
		Bed bed = new Bed();
		try {

			String sql = "SELECT wardid,bedid FROM ipd_bed_change_log where admissionid=" + admissionid + " ";
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {

				bed.setAddmissionid(rs.getString(1));

				IpdDAO ipdDAO = new JDBCIpdDAO(connection);

				String wardname = ipdDAO.getIpdWardName(rs.getString(1));
				bed.setWardname(wardname);

				String bedname = ipdDAO.getIpdBedName(rs.getString(2));
				bed.setBedname(bedname);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return bed;

	}

	private String getCondionid(String chipdid) {

		PreparedStatement preparedStatement = null;
		String result = "";
		try {
			String sql = "select conditionid from apm_final_diagnosis where ipdid= '" + chipdid + "' ";
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

	@Override
	public int getipdFakeconsultcount(String fromdate, String todate, String doctorid) {

		int res = 0;
		try {

			StringBuffer buffer = new StringBuffer();
			buffer.append("select count(*) from ipd_addmission_form where admissiondsate between '" + fromdate
					+ "' and '" + todate + " 59:59:59' ");

			if (doctorid != null) {
				if (!doctorid.equals("0")) {
					buffer.append("and doctorid=" + doctorid + " ");
				}
			}

			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				res = rs.getInt(1);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return res;

	}

	@Override
	public String getfakedatebyClientid(String fakclientid) {
		// TODO Auto-generated method stub

		PreparedStatement preparedStatement = null;
		String result = "";
		try {
			String sql = "select min(id),admissiondsate from ipd_addmission_form where clientid= '" + fakclientid
					+ "' ";
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
	public String getfakeDischrgedate(String fakclientid) {

		PreparedStatement preparedStatement = null;
		String result = "";
		try {
			String sql = "select min(id),dischargedate from apm_treatment_episode where clientid= '" + fakclientid
					+ "' ";
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
	public String getdepmnDate(String deptid) {

		String res = "0";
		try {

			StringBuffer buffer = new StringBuffer();
			buffer.append("select min(ipd_addmission_form.id),admissiondsate from ipd_addmission_form ");
			buffer.append("inner join apm_user on apm_user.id=ipd_addmission_form.practitionerid ");
			buffer.append("inner join apm_discipline on apm_discipline.id=apm_user.discription ");
			buffer.append("where apm_discipline.id= '" + deptid + "' ");

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
	public Bed getFinalDiagnosis(int au_ipdid, String au_treatmentid, String dbname) {
		Bed bed = new Bed();

		try {

			String sql = "Select treatmentepisodeid,conditionid from " + dbname + ".apm_final_diagnosis where ipdid="
					+ au_ipdid + " and treatmentepisodeid='" + au_treatmentid + "' ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				bed.setTreatmentepisodeid(rs.getString(1));
				bed.setConditionid(rs.getString(2));

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return bed;

	}

	@Override
	public ArrayList<Bed> ipdDgetIpdgenderList(String fromdate, String todate) {
		PreparedStatement pst = null;
		// fromDate=DateTimeUtils.getCommencingDate4(fromDate);
		// toDate=DateTimeUtils.getCommencingDate4(toDate);
		ArrayList<Bed> list = new ArrayList<Bed>();
		try {
			String sql = "SELECT id,discipline FROM apm_discipline where showdeptlist='1'";
			pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Bed bed = new Bed();
				bed.setId(rs.getInt(1));
				bed.setDepartment(rs.getString(2));
				int malecount = getipdMaleCount(0, rs.getInt(1), fromdate, todate);
				int femalecount = getipdMaleCount(1, rs.getInt(1), fromdate, todate);
				int totalcount = malecount + femalecount;
				bed.setMalecount(malecount);
				bed.setFemalecount(femalecount);
				bed.setGendercount(totalcount);
				list.add(bed);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private int getipdMaleCount(int i, int deptid, String fromdate, String todate) {
		PreparedStatement pst = null;
		StringBuffer buffer = new StringBuffer();
		int result = 0;
		try {
			if (i == 0) {
				buffer.append(
						"select count(gender) from apm_patient inner join ipd_addmission_form on apm_patient.id=ipd_addmission_form.clientid "
								+ "where gender='male' and speciality='" + deptid + "' and admissiondsate between '"
								+ fromdate + " 00:00' and  '" + todate + " 23:59'");
			} else {
				buffer.append(
						"select count(gender) from apm_patient inner join ipd_addmission_form on apm_patient.id=ipd_addmission_form.clientid "
								+ "where gender='female' and speciality='" + deptid + "' and admissiondsate between '"
								+ fromdate + " 00:00' and  '" + todate + " 23:59'");
			}
			pst = connection.prepareStatement(buffer.toString());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ArrayList<Bed> getpatientRequestNote(String ipdaddmissionid) {

		ArrayList<Bed> list = new ArrayList<>();

		try {

			String sql = "select id,date_time,note,userid,isseen from request_note where admissionid=" + ipdaddmissionid
					+ " and isdeleted=0 ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {

				Bed bed = new Bed();

				bed.setId(rs.getInt(1));
				bed.setDatetime(rs.getString(2));
				bed.setNotes(rs.getString(3));
				bed.setUserid(rs.getString(4));
				bed.setSeen(rs.getString(5));
				list.add(bed);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public int deletenote(String selectedid) {
		int result = 0;
		PreparedStatement pmt = null;

		try {

			String sql = "update request_note set isdeleted=1 where id=" + selectedid + "";

			pmt = connection.prepareStatement(sql);
			result = pmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String getRequestnotebyid(String id) {
		String note = "";
		PreparedStatement pst = null;
		try {
			String sql = "select note from request_note where id=" + id + "";
			pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				note = rs.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return note;
	}

	@Override
	public int updateisSeen(String id) {

		int result = 0;
		PreparedStatement pmt = null;

		try {

			String sql = "update request_note set isseen=0 where id=" + id + "";
			pmt = connection.prepareStatement(sql);
			result = pmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	@Override
	public ArrayList<Ipd> getAllpatienttransactionList(String fromdate, String todate, String searchtext,
			String paymentid) {
		ArrayList<Ipd> list = new ArrayList<Ipd>();
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append(
					"select clientid,datetime,advance,balance,concat(title,' ',firstname,' ',surname) from patient_transection");
			buffer.append(" inner join apm_patient on patient_transection.clientid=apm_patient.id  "
					+ "where datetime between '" + fromdate + "' and '" + todate + " 23:59:59' ");
			if (searchtext != null) {
				buffer.append(" and clientid ='" + searchtext + "' or apm_patient.fullname like('%" + searchtext
						+ "%') or apm_patient.abrivationid='" + searchtext + "'");
			}
			if (paymentid.equals("1")) {
				buffer.append(" and advance!=0.0");
			}
			if (paymentid.equals("2")) {
				buffer.append(" and balance!=0.0");
			}
			if (paymentid.equals("3")) {
				buffer.append(" and advance=0.0 and balance=0.0");
			}

			buffer.append(" order by patient_transection.id desc");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Ipd ipd = new Ipd();
				ipd.setClientid(rs.getString(1));
				ipd.setDate(rs.getString(2));
				ipd.setAdvance(rs.getString(3));
				ipd.setBalance(rs.getString(4));
				ipd.setName(rs.getString(5));
				list.add(ipd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public String getprocedurebyid(String id) {
		String procedure = "";
		PreparedStatement pst = null;
		try {
			String sql = "select procedureid from request_note where id=" + id + "";
			pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				procedure = rs.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return procedure;

	}

	@Override
	public Ipd getDietData(int id, String opdid, int treatmentstatus) {

		Ipd ipd = new Ipd();
		String sql = "";
		try {
			if (id != 0) {
				if (treatmentstatus != 1) {
					sql = "Select ondiet from ipd_addmission_form where treatmentepisodeid=" + id + " ";
				} else {
					sql = "Select ondiet from apm_treatment_episode where id=" + id + " ";
				}
				// sql = "Select ondiet from apm_treatment_episode where id=" + id + " ";
			} else {
				sql = "Select ondiet from consultation_form where apmt_id=" + opdid + " ";
			}

			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String ondiet=rs.getString(1).replace("<br>", "");
				ipd.setOndiet(DateTimeUtils.isNull(ondiet));

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return ipd;
	}

	@Override
	public int getTreatmentId(int id) {
		int treatmentid = 0;
		try {

			String sql = "Select treatmentepisodeid from ipd_addmission_form where id=" + id + " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				treatmentid = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return treatmentid;
	}

	@Override
	public int gettreatmentStatus(String clientid) {
		int treatmentstatus = 0;
		try {

			String sql = "Select treatmentstatus from apm_treatment_episode where clientid=" + clientid + " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				treatmentstatus = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return treatmentstatus;
	}

	@Override
	public String getInvestigationNameFindings(String clientid,String admissiondate, String dischargedate) {
		Bed bed=new Bed();
		StringBuffer buffer = new StringBuffer();
		admissiondate=admissiondate.split(" ")[0]+" "+"00:00:00";
		dischargedate=dischargedate.split(" ")[0]+" "+"23:59:59";
		try {
			String sql = "Select apm_client_investigation.lastmodified,invsttype,invstname,obsvalue from apm_client_investigation inner join apm_client_parent_investigation on apm_client_parent_investigation.id=apm_client_investigation.parentid where apm_client_parent_investigation.lastmodified between '"+admissiondate+"' and '"+dischargedate+"' and apm_client_parent_investigation.clientid=" + clientid + " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				buffer.append(rs.getString(1).split(" ")[0]+":-"+rs.getString(2)+":-"+rs.getString(3)+"-"+rs.getString(4)+",");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}

	@Override
	public ArrayList<Bed> getInvTypelist(String clientid, String admissiondate, String dischargedate) {
		ArrayList<Bed>list=new ArrayList<Bed>();
        StringBuffer sql = new StringBuffer();
        PreparedStatement preparedStatement = null;
        StringBuffer buffer =new StringBuffer();
        
        admissiondate=admissiondate.split(" ")[0]+" "+"00:00:00";
        if(!dischargedate.equals("")) {
        	dischargedate=dischargedate.split(" ")[0]+" "+"23:59:59";
        }
		//dischargedate=dischargedate.split(" ")[0]+" "+"23:59:59";
	
        try {
    		sql.append("SELECT name,apm_client_parent_investigation.id FROM apm_investigation_type inner join apm_client_parent_investigation on apm_client_parent_investigation.invsttypeid=apm_investigation_type.id where apm_client_parent_investigation.lastmodified between '"+admissiondate+"' and '"+dischargedate+"' and apm_client_parent_investigation.clientid=" + clientid + " and apm_client_parent_investigation.reporttype!='writeup' ");
    		
			/*
			 * buffer.
			 * append("SELECT name,apm_client_parent_investigation.id FROM apm_investigation_type inner join apm_client_parent_investigation on "
			 * ); buffer.
			 * append("apm_client_parent_investigation.invsttypeid=apm_investigation_type.id inner join apm_client_investigation on "
			 * ); buffer.
			 * append("apm_client_investigation.parentid=apm_client_parent_investigation.invsttypeid "
			 * );
			 * buffer.append("where apm_client_parent_investigation.lastmodified between '"
			 * +admissiondate+"' and '"+dischargedate+"' ");
			 * buffer.append("and apm_client_parent_investigation.clientid=" + clientid +
			 * " and apm_client_parent_investigation.reporttype!='writeup' and obsvalue!='' "
			 * );
			 */
    		
    		
    		preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet  rs = preparedStatement.executeQuery();
			while(rs.next()){
				Bed bed=new Bed();
				bed.setInvestigation_type(rs.getString(1));
				bed.setId(rs.getInt(2));
				
				Vector<Bed> investigationnameList = getInvestigationNameList(bed.getInvestigation_type(),bed.getId());
				if(investigationnameList.size()>0) {
				Bed bed1=investigationnameList.get(investigationnameList.size()-1);
				bed.setInv_date(bed1.getDate().split(" ")[0]);
				bed.setInvestigation_name(bed1.getInvestigation_name());
				bed.setInvnamelist(investigationnameList);
				}
				
				list.add(bed);
		  }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private Vector<Bed> getInvestigationNameList(String investigation_type_name,int parentid) {
		PreparedStatement preparedStatement = null;
		Vector<Bed>list = new Vector<Bed>();
		StringBuffer buffer =new StringBuffer();
		try {
			String sql = "Select apm_client_investigation.lastmodified,invstname,obsvalue from apm_client_investigation where invsttype='"+investigation_type_name+"' and parentid="+parentid+" and obsvalue!='' ";
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Bed bed=new Bed();
				
				bed.setDate(rs.getString(1));
				buffer=buffer.append(rs.getString(2)+"-"+rs.getString(3)+",");
				bed.setInvestigation_name(buffer.toString());
				//bed.setInvestigation_name(rs.getString(2));
				//bed.setObsvalue(rs.getString(3));
				
				list.add(bed);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public ArrayList<Accounts> getdailynoteslist(String ipdid, String clientId,String admissiondate, String dischargedate) {
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts>list=new ArrayList<Accounts>();
		StringBuffer sql=new StringBuffer();
		try {
		    sql.append("select id,date_time,notes,apmt_id from consultation_form where client_id='"+clientId+"' and ipdid='"+ipdid+"'");
		    if(!admissiondate.equals("") || !dischargedate.equals("")) {
		    	sql.append(" and date_time between '"+admissiondate+"' and '"+dischargedate+"'");
		    }
			preparedStatement =connection.prepareStatement(sql.toString());
			ResultSet rs=preparedStatement.executeQuery();
			while(rs.next()) {
				Accounts accounts=new Accounts();
				accounts.setId(rs.getInt(1));
				accounts.setDate_time(rs.getString(2));
				accounts.setNotes(rs.getString(3));
				accounts.setApmt_id(rs.getString(4));
				list.add(accounts);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Bed getDailyipdnote(String id) {
		PreparedStatement preparedStatement=null;
		Bed bed=new Bed();
		try {
			String sql="select notes,ipdid,client_id from consultation_form where id="+id+"";
			preparedStatement =connection.prepareStatement(sql);
			ResultSet rs=preparedStatement.executeQuery();
			while(rs.next()) {
				bed.setNotes(rs.getString(1));
				bed.setIpdid(rs.getString(2));
				bed.setClientid(rs.getString(3));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bed;
	}

	@Override
	public ArrayList<Bed> getConsultationFormDetailslistbydate(String apmtId, String clientId, String datetime) {
		PreparedStatement preparedStatement=null;
		ArrayList<Bed> list=new ArrayList<Bed>();
		StringBuffer sql=new StringBuffer();
		try {
			 sql.append("select id, client_id, who_pay, tp_id, apmt_id, user_id, date_time, "
						+ "chief_complains, investigation, discadvnotes, notes, "
						+ "udpate_user_id, update_date_time,medicine_text,examination_finding,ondiet,karma,procedurebams,LSplRFI,LCylRFI,LAxisRFI,RSplRFI,RCylRFI,RAxisRFI,consult_data "
						+ "from consultation_form  where client_id='"+clientId+"' and date_time='"+datetime+"' and apmt_id='" + apmtId + "'");
				       
				 		
				 preparedStatement = connection.prepareStatement(sql.toString());
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					Bed bed=new Bed();
					bed.setId(rs.getInt(1));
					bed.setClientid(rs.getString(2));
					bed.setWhopay(rs.getString(3));
					bed.setTpid(rs.getString(4));
					bed.setAppointmentid(rs.getString(5));
					bed.setUserid(rs.getString(6));
					bed.setDatetime(rs.getString(7));
					if (DateTimeUtils.isNull(rs.getString(8)).equals("<br>")) {
						bed.setChiefcomplains("");
					} else {
						bed.setChiefcomplains(rs.getString(8).replace("<br>", ""));
					}
					if (DateTimeUtils.isNull(rs.getString(9)).equals("<br>")) {
						bed.setInvestigation("");
					} else {
						bed.setInvestigation(rs.getString(9).replace("<br><br>", ""));
					}
					if (DateTimeUtils.isNull(rs.getString(10)).equals("<br>")) {
						bed.setDiscadvnotes("");
					} else {
						bed.setDiscadvnotes(rs.getString(10).replace("<br>", ""));
					}
					if (DateTimeUtils.isNull(rs.getString(11)).equals("<br>")) {
						bed.setNotes("");
					} else {
						bed.setNotes(rs.getString(11));
					}
					if (DateTimeUtils.isNull(rs.getString(14)).equals("<br>")) {
						bed.setKunal_manual_medicine_text("");
					} else {
						bed.setKunal_manual_medicine_text(rs.getString(14));
					}
					bed.setUpdateUserId(rs.getString(12));
					bed.setUpdateDateTime(rs.getString(13));
					bed.setExamination(rs.getString(15));

					if (DateTimeUtils.isNull(rs.getString(16)).equals("<br>")) {
						bed.setOndiet("");
					} else {
						bed.setOndiet(rs.getString(16));
					}
					if (DateTimeUtils.isNull(rs.getString(17)).equals("<br>")) {
						bed.setKarma("");
					} else {
						bed.setKarma(rs.getString(17));
					}
					if (DateTimeUtils.isNull(rs.getString(18)).equals("<br>")) {
						bed.setProcesurebams("");
					} else {
						bed.setProcesurebams(rs.getString(18));
					}
	                bed.setLSplRFI(rs.getString(19));
	                bed.setLCylRFI(rs.getString(20));
	                bed.setLAxisRFI(rs.getString(21));
	                bed.setRSplRFI(rs.getString(22));
	                bed.setRCylRFI(rs.getString(23));
	                bed.setRAxisRFI(rs.getString(24));
	                bed.setConsNote(rs.getString(25));
	                
	                list.add(bed);
				}
			} catch (Exception e) {
		e.printStackTrace();
		}
		return list;
	}

	@Override
	public int updateAdmissionid(String admissionid, String ipdseqno) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update ipd_addmission_form_new set  sramadmissionid='"+admissionid+"'  where ipdseqno = " + ipdseqno + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			

			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String getnewAdmissionid(int ipdid) {
		String result = "";
		try {
			PreparedStatement ps = connection
					.prepareStatement("select sramadmissionid from ipd_addmission_form_new where id =" +ipdid + "");  
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;

	}

	@Override
	public String getDiagnosisids(String apmtId) {
		String ids="";
	
		try {
			
			String sql="select conditionid from consultant_notes_diagnosis where apmtid='"+apmtId+"'";
			PreparedStatement pst=connection.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				ids=rs.getString(1);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ids;
	}

	@Override
	public String getDiagnosisText(String ids) {
		String diagnosis="";

		StringBuffer buffer=new StringBuffer();
		try {
			String sql="select name from apm_diagnosis where id in("+ids+")";
			PreparedStatement pst=connection.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
			
				buffer=buffer.append(rs.getString(1)+",<br>");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}

	@Override
	public int getwardId(int ipdid, String clientId) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT wardid FROM ipd_addmission_form where clientid = " + clientId + " and id='"+ipdid+"' order by id desc ";

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

	@Override
	public ArrayList<Bed> getDailyNotelist(String apmtId, String id2, String datetime) {
		PreparedStatement preparedStatement=null;
		ArrayList<Bed> list=new ArrayList<Bed>();
		StringBuffer sql=new StringBuffer();
		
		
		try {
			 sql.append("select id, client_id, who_pay, tp_id, apmt_id, user_id, date_time, "
					+ "chief_complains, investigation, discadvnotes, notes, "
					+ "udpate_user_id, update_date_time,medicine_text,examination_finding,ondiet,karma,procedurebams,LSplRFI,LCylRFI,LAxisRFI,RSplRFI,RCylRFI,RAxisRFI,priscription,vision,history "
					+ "from consultation_form  where ");
			         if(!apmtId.equals("0")) {
			       sql.append("apmt_id='" + apmtId + "' and date_time between '"+datetime+"' and '"+datetime+" 23:59:59'");
			 		}else {
			 			sql.append("date_time between '"+datetime+"' and '"+datetime+" 23:59:59' ");
			 		}
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Bed bed = new Bed();
				bed.setId(rs.getInt(1));
				bed.setClientid(rs.getString(2));
				bed.setWhopay(rs.getString(3));
				bed.setTpid(rs.getString(4));
				bed.setAppointmentid(rs.getString(5));
				bed.setUserid(rs.getString(6));
				bed.setDatetime(rs.getString(7));
				if (DateTimeUtils.isNull(rs.getString(8)).equals("<br>")) {
					bed.setChiefcomplains("");
				} else {
					bed.setChiefcomplains(DateTimeUtils.isNull(rs.getString(8)).replace("<br>", ""));
				}
				if (DateTimeUtils.isNull(rs.getString(9)).equals("<br>")) {
					bed.setInvestigation("");
				} else {
					bed.setInvestigation(DateTimeUtils.isNull(rs.getString(9)).replace("<br><br>", ""));
				}
				if (DateTimeUtils.isNull(rs.getString(10)).equals("<br>")) {
					bed.setDiscadvnotes("");
				} else {
					bed.setDiscadvnotes(DateTimeUtils.isNull(rs.getString(10)).replace("<br>", ""));
				}
				if (DateTimeUtils.isNull(rs.getString(11)).equals("<br>")) {
					bed.setNotes("");
				} else {
					bed.setNotes(DateTimeUtils.isNull(rs.getString(11).replace("<br>", "")));
				}
				if (DateTimeUtils.isNull(rs.getString(14)).equals("<br>")) {
					bed.setKunal_manual_medicine_text("");
				} else {
					bed.setKunal_manual_medicine_text(DateTimeUtils.isNull(rs.getString(14)));
				}
				bed.setUpdateUserId(rs.getString(12));
				bed.setUpdateDateTime(rs.getString(13));
				bed.setExamination(rs.getString(15));

				if (DateTimeUtils.isNull(rs.getString(16)).equals("<br>")) {
					bed.setOndiet("");
				} else {
					bed.setOndiet(DateTimeUtils.isNull(rs.getString(16)));
				}
				if (DateTimeUtils.isNull(rs.getString(17)).equals("<br>")) {
					bed.setKarma("");
				} else {
					bed.setKarma(DateTimeUtils.isNull(rs.getString(17)));
				}
				if (DateTimeUtils.isNull(rs.getString(18)).equals("<br>")) {
					bed.setProcesurebams("");
				} else {
					bed.setProcesurebams(DateTimeUtils.isNull(rs.getString(18)));
				}
               bed.setLSplRFI(rs.getString(19));
               bed.setLCylRFI(rs.getString(20));
               bed.setLAxisRFI(rs.getString(21));
               bed.setRSplRFI(rs.getString(22));
               bed.setRCylRFI(rs.getString(23));
               bed.setRAxisRFI(rs.getString(24));
               bed.setConsNote(rs.getString(25));
               bed.setEyereading(rs.getString(26));
               bed.setHistory(rs.getString(27));
               list.add(bed);
               
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
		
	}

	@Override
	public ArrayList<Master> getAllChiefComplains(String search,String id) {
		PreparedStatement preparedStatement = null;
		ArrayList<Master> list = new ArrayList<Master>();
		
		try {
			String sql = "select id,text from ipd_template where text like('%" + search
					+ "%') and template_nameid="+id+" order by text limit 5";
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				String text = rs.getString(2);
				
				master.setChiefcomplains(text);
				list.add(master);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public String getChiefComplainsbyid(String chief_complains) {
		String chiefcomplain="";

		StringBuffer buffer=new StringBuffer();
		try {
			String sql="select text from ipd_template where id in("+chief_complains+")";
			PreparedStatement pst=connection.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
			
				buffer=buffer.append(rs.getString(1)+",<br>");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}

	@Override
	public Master getChiefComplainsIds(String apmtId) {
		Master master = new Master();
		try {
			// String sql = "Select conditionid from apm_final_diagnosis where ipdid=" +
			// ipdid + "";
			String sql = "Select chiefcomplainid from consultant_notes_diagnosis where apmtid=" + apmtId + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				master.setChiefcomplains(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return master;
	}

	@Override
	public String getIpdChiefComplainname(String str) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select text from ipd_template where id = '"+str+"'";
		
		try{
			
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

	@Override
	public ArrayList<Master> getAllHistoryList(String search, String id) {
		PreparedStatement preparedStatement = null;
		ArrayList<Master> list = new ArrayList<Master>();
		
		try {
			String sql = "select id,text from ipd_template where text like('%" + search
					+ "%') and template_nameid="+id+" order by text limit 5";
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setHistoryid(rs.getString(1));
				String text = rs.getString(2);
				
				master.setPasthistory(text);
				list.add(master);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Master getHistoryIds(String apmtId) {
		Master master = new Master();
		try {
			
			String sql = "Select historyid from consultant_notes_diagnosis where apmtid=" + apmtId + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				master.setPasthistory(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return master;
	}

	@Override
	public ArrayList<Master> getAllInvestigation(String search) {
		PreparedStatement preparedStatement = null;
		ArrayList<Master> list = new ArrayList<Master>();
		// ClientDAO clientDAO = new JDBCClientDAO(connection);
		try {
			String sql = "SELECT id,name FROM apm_investigation_type where isdeleted!=1 and name like('%" + search
					+ "%') order by name limit 5";
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setInvestigation_name(rs.getString(2));
				list.add(master);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public String getInvestigationNameByid(String investigation) {
		String diagnosis="";

		StringBuffer buffer=new StringBuffer();
		try {
			String sql="select name from apm_investigation_type where id in("+investigation+")";
			PreparedStatement pst=connection.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
			
				buffer=buffer.append(rs.getString(1)+",<br>");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}

	@Override
	public Master getInvestigationIds(String apmtId) {
		Master master = new Master();
		try {
			
			String sql = "Select investigationid from consultant_notes_diagnosis where apmtid=" + apmtId + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				master.setInvestigstion_id(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return master;
	}

	@Override
	public String getInvestigationNames(String str) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select name from apm_investigation_type where id = '"+str+"'";
		
		try{
			
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

	@Override
	public ArrayList<Master> getAllNotesList(String search, String id) {
		PreparedStatement preparedStatement = null;
		ArrayList<Master> list = new ArrayList<Master>();
		
		try {
			String sql = "select id,text from ipd_template where text like('%" + search
					+ "%') and template_nameid="+id+" order by text limit 5";
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				String text = rs.getString(2);
				
				master.setNotes(text);
				list.add(master);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public String getNotestext(String notes) {
		String note="";

		StringBuffer buffer=new StringBuffer();
		try {
			String sql="select text from ipd_template where id in("+notes+")";
			PreparedStatement pst=connection.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
			
				buffer=buffer.append(rs.getString(1)+",<br>");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}

	@Override
	public Master getNotesIds(String apmtId) {
		Master master = new Master();
		try {
			
			String sql = "Select notesid from consultant_notes_diagnosis where apmtid=" + apmtId + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				master.setNotes(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return master;
	}

	@Override
	public String setObservText(String observation) {
		String observ="";

		StringBuffer buffer=new StringBuffer();
		try {
			String sql="select text from ipd_template where id in("+observation+")";
			PreparedStatement pst=connection.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
			
				buffer=buffer.append(rs.getString(1)+",<br>");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}

	@Override
	public Master getObservationIds(String apmtId) {
		Master master = new Master();
		try {
			
			String sql = "Select observid from consultant_notes_diagnosis where apmtid=" + apmtId + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				master.setNotes(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return master;
	}

	@Override
	public ArrayList<Master> getAllChargesList(String search) {
		PreparedStatement preparedStatement = null;
		ArrayList<Master> list = new ArrayList<Master>();
		
		try {
			String sql = "select id,name,charges from apm_appointment_type where name like('%" + search
					+ "%')  order by name limit 5";
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				String text = rs.getString(2);
				
				master.setChargename(text);
				master.setCharge(rs.getString(3));
				list.add(master);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Master getTreatmentIds(String apmtId) {
		Master master = new Master();
		try {
			
			String sql = "Select treatmentid from consultant_notes_diagnosis where apmtid=" + apmtId + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				master.setChargename(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return master;
	}

	@Override
	public String setTreatmentName(String treatment) {
		String diagnosis="";

		StringBuffer buffer=new StringBuffer();
		try {
			String sql="select name from apm_appointment_type where id in("+treatment+")";
			PreparedStatement pst=connection.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
			
				buffer=buffer.append(rs.getString(1)+",<br>");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}

	@Override
	public Master getTreatmentPlan(String str) {
		PreparedStatement preparedStatement = null;
		Master master=new Master();
		String sql = "select name,charges,chargeType from apm_appointment_type where id = '"+str+"'";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
			
				master.setChargename(rs.getString(1));
				master.setCharge(rs.getString(2));
				master.setChargetype(rs.getString(3));
			}
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
		return master;
	}

}
