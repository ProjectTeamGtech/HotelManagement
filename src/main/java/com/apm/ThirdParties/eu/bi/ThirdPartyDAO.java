package com.apm.ThirdParties.eu.bi;

import java.util.ArrayList;

import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.ThirdParties.eu.entity.GP;
import com.apm.ThirdParties.eu.entity.ThirdParty;
import com.apm.ThirdParties.web.form.DynamicAppointment;
import com.apm.common.utils.Pagination;

public interface ThirdPartyDAO {

	ArrayList<ThirdParty> getThirdPartyList();

	int saveDetails(String type,ThirdParty thirdParty);

	

	int editDetails(String type, int id, ThirdParty thirdParty);

	int deleteDetail(int id);

	int saveType(ThirdParty thirdParty);

	ThirdParty getTypeDetail(int id);

	int updateType(int id, ThirdParty thirdParty);

	int deleteType(int id);

	public ArrayList<ThirdParty> getThirdPartyDetailsList();

	int getThirdPartyCount(String type);

	ArrayList<ThirdParty> getThirdPartyDetailsList(int i,
			Pagination pagination);

	int getThirdPartyTypeCount();

	ArrayList<ThirdParty> getThirdPartyList(Pagination pagination);

	ArrayList<ThirdParty> getThirdPartyDetailsList(String type,int clinicId,Pagination paginationm,String searchText);

	ThirdParty getThirdPartyDetails(String id);

	int saveNewDetails(String thirdPartyType, ThirdParty thirdParty);

	int getTotalThirdPartiesCount(int id);

	ArrayList<ThirdParty> getThirdPartyDetailsList(int id);

	int getTotalThirdPartiesCount(int id, String type, String searchText);

	int saveData(String type, ThirdParty thirdParty);

	boolean isEmailIdExist(String email);

	boolean isTelePhoneExist(String telephone);

	int saveGp(String type, ThirdParty thirdParty,String tpid);

	int saveTp(String type, ThirdParty thirdParty,int id);

	ArrayList<ThirdParty> getThirdPartyDetailsList(int id, String type);

	int saveInitialAppointmentTYpe(ThirdParty thirdParty,int tpid);

	int saveFollowupAppointmentTYpe(ThirdParty thirdParty, int r1);

	int saveFinalAppointmentTYpe(ThirdParty thirdParty, int r1);

	int saveMaintnanceAppointmentTYpe(ThirdParty thirdParty, int r1);

	ArrayList<ThirdParty> getThirdPartyDetailsList(String searchClient, int id);

	String getFullName(String id);

	String getThirdPartyEmail(String id);

	ThirdParty getTPDetails(String tpname);

	int updateTp(String type, ThirdParty thirdParty, int i, String typename);

	ArrayList<Client> getTPNameList(String id);

	ArrayList<ThirdParty> getTPNameList();

	ArrayList<ThirdParty> getSearchTPCompanyNameList(String searchClient, int id);

	ArrayList<ThirdParty> getSearchSurgeryNameList(String searchClient, int id);

	ArrayList<ThirdParty> getTPCompanyNameList(int id);

	ArrayList<ThirdParty> getSurjeryCompanyDetailsList(int id);

	int getThirdPartyId(int patientId);

	ThirdParty getGPDetailLetter(int tpId);

	ArrayList<ThirdParty> getDoctorSurgeryList(String type);

	ArrayList<ThirdParty> getGPList(int r1);

	String getCompanyNameForChargeType(String typename);

	boolean checkAppointmentTypeExist(String typename,
			int appointType);

	int updateThirpartyAppointmentType(String initialAppointmentName,
			String compltInitialApmt, String compltInitialApmtDuration,String dnaCharge,boolean dnaoffset,int aptypeid);

	int getThirdPartyTempId(int selectedid);

	int updateAppointmentTypeName(String typeName, String initialAppointmentName,String constantaname);

	int updateDoctorSurgery(String typename, ThirdParty thirdParty);

	int saveDynamicApmts(int r1, ThirdParty thirdParty);

	ArrayList<DynamicAppointment> getDynmicApmtList(String id);

	int updateDynamicApmts(int r1, ThirdParty thirdParty1, int iddnymic);

	int saveDynamicAppointmentTypeData(int r1, ThirdParty thirdParty1);

	int updateDynamicAppointmentTypeData(int r1, ThirdParty thirdParty1,String tpid);

	ThirdParty getAppointmentChargesData(String id, int initialAppointmentType);

	int getExistingAppointmentTypeid(String typename, int initialAppointmentType);

	GP getGPDetails(String id);

	ArrayList<ThirdParty> getMainTPList();

	ArrayList<ThirdParty> getSubTpList();

	ArrayList<ThirdParty> getSelectedSubTPList(String maintp);

	int updateFollowerTp(int str, int tpid);

	int validatePayerType(String patientType, String payerType, String edit_Id);

	ArrayList<ThirdParty> getThirdPartyDetailsListData();

	ArrayList<ThirdParty> getNDSCharges(String department, String ids);

	int saveNonDiscountServices(String department, String patientType, String payerType, String payer, String ids);

	boolean checkAlreadyNDSIds(String department, String patientType, String payerType, String payer, String ids);

	int updateNonDiscountServices(String department, String patientType, String payerType, String payer, String ids, int id);

	ThirdParty getNDSids(String patientType, String payerType, String payer);

	ArrayList<ThirdParty> getNDSChargesList(String ids);

	ArrayList<ThirdParty> getMainTPListNew(String patientType);

	ArrayList<ThirdParty> getTPNameListNew(String payerType);

	int updatePayerType(ThirdParty thirdParty);

	int updateCompanyName(String typename, ThirdParty thirdParty);

	ThirdParty getRegisterPayer(String typename);

	int saveRegisterPayerLog(String userId, ThirdParty old_registerpayer, ThirdParty thirdParty, String datetime);

	int getPatientType(String typename);

	ArrayList<ThirdParty> getChargeslistbytpid(String tpidold, String tpidnew);



	
	
	

}
