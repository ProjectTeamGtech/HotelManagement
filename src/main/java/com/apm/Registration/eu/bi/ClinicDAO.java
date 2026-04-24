package com.apm.Registration.eu.bi;

import java.sql.Connection;
import java.util.ArrayList;

import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.Location;
import com.apm.common.utils.Pagination;
import com.apm.common.web.common.helper.Access;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.Registration.eu.entity.Clinic;

public interface ClinicDAO {
	
	int deleteClinicList(int selectedid);
	
	int updateCliniclist(Clinic cliniclist);
	
	ArrayList<Clinic> getClinicLocationList(int selectedid);
	
	Clinic getCliniclistDetails(int selectedid);
	
	int saveLocation(int selectedid, int locationid, Clinic cliniclist);
	
	int getCliniclistCount(String searchText)throws Exception;
	
	ArrayList<Clinic> getClinicList(Pagination pagination,String searchText)throws Exception;

	boolean isUserExist(String userid,String selectedid,String existUserid);

	int saveClinic(Clinic clinic);

	int saveAdminAccessPassword(int userid, Clinic clinic);

	int saveLocation(int userid, Clinic clinic);

	Clinic getuser(String userId);
	
	boolean isSmsActive(int selectedid);
	
	ArrayList<Clinic> getColorList();
	
	int updateClinic(Clinic clinic, int selectedid);
	
	int deleteClinicLocation(int userid);
	
	Clinic getLocationDetails(int selectedid);
	
	int updateClinicLocation(Clinic clinic, int selectedid);
	
	int deleteClinicLocation(Clinic clinic, int selectedid);
	
	int changeMailSend(String checkMailSend, int id); 
	
	String IsMailSend(int id);
	
	boolean checkEmailidExist(String emailid);
	
	int updateClinicStatus(String selectedid, int activestatus);
	
	ArrayList<Clinic> getLocationList(int id);
	
	String getOtherUserID(String clinicID);
	
	int updateLocationCheck(String checkLoc, int locId);

	String getSemikaUserID(String usr);

	ArrayList<Clinic> getDNAChargeList();

	int saveDnaCharge(Clinic clinic);

	Clinic getDnaChargeDetails(int selectedid);

	int updateDNACharge(int dnaCharge, int id);

	int deleteDNACharge(int id);

	int getClinicId(int id);

	boolean getLoginStatus(String userId);

	int getAdminClinicId(String userid);

	int updateClinicByAdmin(Clinic clinic, int selectedid);

	int updateAdminLoginStatus(String userid, boolean b);

	int updateLogoutStatus(String userId);

	int updateLoginStatus(String userId);

	int deleteApmloggedUser(String userid);

	String getFirstDiaryUserid();

	String getCountryForPractitoner(String clinicID);

	int updateAdminEmailAndMobile(Clinic clinic);

	boolean checkLoginUserExist(String userId);

	String getExistUserId(String userId);

	Location getRegisterdLication();

	/*int SaveEmailConfugureInfo(Clinic clinic);*/
	
	String getAutoChargeTime(String selectedid);

	boolean isPaymentSMSActive(int clinicid);

	String getStdChargeSetup(int clinicid);
	
	public Clinic getLettHeadDetails(String userid);

	int truncateTempIpdFormData();

	int saveIpdFormData(String speci, String f);

	ArrayList<Clinic> getAllJobTitle();
	
	int setroleaccesssetiing(String name);

	ArrayList<Clinic> getAllAccessModule();

	ArrayList<Clinic> getJobTitle();

	Clinic checkroleAccessName(String job_title);

	ArrayList<Clinic> getAllRoleAccessData(int id, String modulename);

	ArrayList<Clinic> getSpecificAccessModule(String modulename);

	int updateRoleAccessSetting(String roleid, String name, String value);

	String getClinicIpAddress(String clinicUserid);

	Access getGlobalAccessAccess(String jobtitle);

	ArrayList<Clinic> getAllRoleAccesssData(int id, String moduleid, String jobtitle);
	ArrayList<Clinic> getPatients(String fromDate, String toDate, String dept, String type);
	 
	  ArrayList<Clinic> getAllMisRoleAccesssData(int id, String moduleid, String jobtitle);

	String getPacsIpAddress(String clinicUserid);

	int getoutoprisc(String clinicUserid);
	Clinic getclinicNewHospitalAccess();
	
	int getClinicDeactiveDays(Connection connection2,LoginInfo loginInfo );
	
	String getJobtile(String userid);

	int getwardforcharge(String clinicUserid);

	int getIsKunal(String clinicUserid);

	String getpreparedby(String invoiceid);
	int getPharmacyPagelimit();

	Clinic getLoginExpiry();

	int updateHospName(String hospname);

	ArrayList<Clinic> getSMSCountList(String month_filter, String year_filter);
	int switchAccessOfClinic(String value, String colname);

	int saveLocation1(int selectedid, Clinic clinic);

	ArrayList<Clinic> getAllhospitalName();
	Clinic getCliniclistDetailsByUserId(String userid);

	Clinic getServerData();

	Clinic getNewClinicAccess();

	int switchAccessOfClinicNew(String value, String colname);

	int updateUserLanguageAccess(String value);

	Clinic getClinicDetails(String clinicUserid);

	Clinic getBankClinicDetails(String clinicUserid, String pro_userid);

	int updateClinicBankDetails(String bankName, String accountNo, String ifscCode, String upiId, String string, String pro_userid);

	int insertClinicBankDetails(String bankName, String accountNo, String ifscCode, String upiId, String clinicUserid,
			String pro_userid);

	Clinic getClinicCommercial(String referHospitalId, String pro_userid);

	boolean checkClinicCommercial(String referHospitalId, String pro_userid);

	int saveClinicHospitalCommercial(String referHospitalId, String pro_userid, Clinic clinic);

	ArrayList<Clinic> getClinicTempComercial(String pro_userid);

	int deleteClinicHospital(String pro_userid);

	int checkClinicHospitalCommerical(String hosp_id, String pro_userid);

	int insertClinicHospitalCommercial(String hosp_id, String pro_userid, String hosp_commercial, String dateTime);

	int updateClinicHospitalCommercial(String hosp_id, String pro_userid, String hosp_commercial, String dateTime,
			int result);

	Clinic getWhatsappActive(int id);

	String getRequiredopddate(String clientId, String opddate);

	int updateAddressForManasclinic(Clinic clinic, int selectedid);



	
}
