package com.apm.Mrd.web.form;

import java.util.ArrayList;
import java.util.Collection;

import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Master.eu.entity.Master;
import com.apm.Mrd.eu.entity.Mrd;
import com.apm.ThirdParties.eu.entity.ThirdParty;
import com.apm.TreatmentEpisode.eu.entity.TreatmentEpisode;


public class MrdForm {
	private String patient_department;
	private ArrayList<Client> allPatientList;
	private ArrayList<Client> diagnosisList;
	private String dectitle;
	ArrayList<Client> gpList;
	private ArrayList<String> occupationList;
	ArrayList<Master> declerationTitleList;
	private ArrayList<TreatmentEpisode> sourceOfReferralList;
	private ArrayList<String> apmtDurationList;
	ArrayList<ThirdParty> tpnameList;
	private ArrayList<DiaryManagement> userList;
	private ArrayList<Client> condtitionList;
	private String country;
	private ArrayList<String> countryList;
	private ArrayList<Master> statelist;
	private ArrayList<Master> citylist;
	private ArrayList<Client> thirdPartyTypeList;
	private ArrayList<Client> thirdPartyTypeNameList;
	private ArrayList<Client> surgeryList;
	private ArrayList<Client> clientOccupationList;
	private ArrayList<Client> refrenceList;
	private ArrayList<String> initialList;
	ArrayList<Client> sourceOfIntroList;
	private String templateID;
	private String assessmenetid;
	private int id;
	private String checklist;
	private String clientid;
	private String wardid;
	private String bedid;
	private String admissiondsate;
	private String dischargedate;
	private String wardname;
	private String clientname;
	private String whopay;
	private String payby;
	private String searchtext;
	private String bedname;
	private ArrayList<Mrd> mrdlist;
	private ArrayList<Bed> wardlist; 
	private String tpname;
	private String shelf_no;
	private String place;
	private String remark;
	private String mlc;
	private String fromdate;
	private String todate;
	private String status;
	private String wardnameid;
	private String mastername;
	private String pagerecords;
	private String totalRecords;
	private String lastmodfied;
	private Collection<Master> shiftmrd;
	private String searchbydate;
	private ArrayList<String> hourList;
	private ArrayList<String> minuteList;
	
	public ArrayList<String> getHourList() {
		return hourList;
	}
	public void setHourList(ArrayList<String> hourList) {
		this.hourList = hourList;
	}
	public ArrayList<String> getMinuteList() {
		return minuteList;
	}
	public void setMinuteList(ArrayList<String> minuteList) {
		this.minuteList = minuteList;
	}
	public String getSearchbydate() {
		return searchbydate;
	}
	public void setSearchbydate(String searchbydate) {
		this.searchbydate = searchbydate;
	}
	public Collection<Master> getShiftmrd() {
		return shiftmrd;
	}
	public void setShiftmrd(Collection<Master> shiftmrd) {
		this.shiftmrd = shiftmrd;
	}
	public String getLastmodfied() {
		return lastmodfied;
	}
	public void setLastmodfied(String lastmodfied) {
		this.lastmodfied = lastmodfied;
	}
	public String getPagerecords() {
		return pagerecords;
	}
	public void setPagerecords(String pagerecords) {
		this.pagerecords = pagerecords;
	}
	public String getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}
	public String getMastername() {
		return mastername;
	}
	public void setMastername(String mastername) {
		this.mastername = mastername;
	}
	private ArrayList<Master> shelflist;
	
	public ArrayList<Master> getShelflist() {
		return shelflist;
	}
	public void setShelflist(ArrayList<Master> shelflist) {
		this.shelflist = shelflist;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFromdate() {
		return fromdate;
	}
	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}
	public String getTodate() {
		return todate;
	}
	public void setTodate(String todate) {
		this.todate = todate;
	}
	public String getMlc() {
		return mlc;
	}
	public void setMlc(String mlc) {
		this.mlc = mlc;
	}
	public String getShelf_no() {
		return shelf_no;
	}
	public void setShelf_no(String shelf_no) {
		this.shelf_no = shelf_no;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getTpname() {
		return tpname;
	}
	public void setTpname(String tpname) {
		this.tpname = tpname;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getClientid() {
		return clientid;
	}
	public void setClientid(String clientid) {
		this.clientid = clientid;
	}
	public String getWardid() {
		return wardid;
	}
	public void setWardid(String wardid) {
		this.wardid = wardid;
	}
	public String getBedid() {
		return bedid;
	}
	public void setBedid(String bedid) {
		this.bedid = bedid;
	}
	public String getAdmissiondsate() {
		return admissiondsate;
	}
	public void setAdmissiondsate(String admissiondsate) {
		this.admissiondsate = admissiondsate;
	}
	public String getWardname() {
		return wardname;
	}
	public void setWardname(String wardname) {
		this.wardname = wardname;
	}
	public String getClientname() {
		return clientname;
	}
	public void setClientname(String clientname) {
		this.clientname = clientname;
	}
	public String getWhopay() {
		return whopay;
	}
	public void setWhopay(String whopay) {
		this.whopay = whopay;
	}
	public String getPayby() {
		return payby;
	}
	public void setPayby(String payby) {
		this.payby = payby;
	}
	public String getBedname() {
		return bedname;
	}
	public void setBedname(String bedname) {
		this.bedname = bedname;
	}
	public ArrayList<Mrd> getMrdlist() {
		return mrdlist;
	}
	public void setMrdlist(ArrayList<Mrd> mrdlist) {
		this.mrdlist = mrdlist;
	}
	public String getDischargedate() {
		return dischargedate;
	}
	public void setDischargedate(String dischargedate) {
		this.dischargedate = dischargedate;
	}
	public String getSearchtext() {
		return searchtext;
	}
	public void setSearchtext(String searchtext) {
		this.searchtext = searchtext;
	}
	public ArrayList<Bed> getWardlist() {
		return wardlist;
	}
	public void setWardlist(ArrayList<Bed> wardlist) {
		this.wardlist = wardlist;
	}
	public String getWardnameid() {
		return wardnameid;
	}
	public void setWardnameid(String wardnameid) {
		this.wardnameid = wardnameid;
	}
	public String getChecklist() {
		return checklist;
	}
	public void setChecklist(String checklist) {
		this.checklist = checklist;
	}
	public String getAssessmenetid() {
		return assessmenetid;
	}
	public void setAssessmenetid(String assessmenetid) {
		this.assessmenetid = assessmenetid;
	}
	
	public ArrayList<Client> getThirdPartyTypeList() {
		return thirdPartyTypeList;
	}
	public void setThirdPartyTypeList(ArrayList<Client> thirdPartyTypeList) {
		this.thirdPartyTypeList = thirdPartyTypeList;
	}
	public ArrayList<Client> getThirdPartyTypeNameList() {
		return thirdPartyTypeNameList;
	}
	public void setThirdPartyTypeNameList(ArrayList<Client> thirdPartyTypeNameList) {
		this.thirdPartyTypeNameList = thirdPartyTypeNameList;
	}
	public ArrayList<Client> getSurgeryList() {
		return surgeryList;
	}
	public void setSurgeryList(ArrayList<Client> surgeryList) {
		this.surgeryList = surgeryList;
	}
	public ArrayList<Client> getClientOccupationList() {
		return clientOccupationList;
	}
	public void setClientOccupationList(ArrayList<Client> clientOccupationList) {
		this.clientOccupationList = clientOccupationList;
	}
	public ArrayList<Client> getRefrenceList() {
		return refrenceList;
	}
	public void setRefrenceList(ArrayList<Client> refrenceList) {
		this.refrenceList = refrenceList;
	}
	public ArrayList<String> getInitialList() {
		return initialList;
	}
	public void setInitialList(ArrayList<String> initialList) {
		this.initialList = initialList;
	}
	public ArrayList<Client> getSourceOfIntroList() {
		return sourceOfIntroList;
	}
	public void setSourceOfIntroList(ArrayList<Client> sourceOfIntroList) {
		this.sourceOfIntroList = sourceOfIntroList;
	}
	public ArrayList<Master> getStatelist() {
		return statelist;
	}
	public void setStatelist(ArrayList<Master> statelist) {
		this.statelist = statelist;
	}
	public ArrayList<Master> getCitylist() {
		return citylist;
	}
	public void setCitylist(ArrayList<Master> citylist) {
		this.citylist = citylist;
	}
	public ArrayList<String> getCountryList() {
		return countryList;
	}
	public void setCountryList(ArrayList<String> countryList) {
		this.countryList = countryList;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public ArrayList<Client> getGpList() {
		return gpList;
	}
	public void setGpList(ArrayList<Client> gpList) {
		this.gpList = gpList;
	}
	public ArrayList<String> getOccupationList() {
		return occupationList;
	}
	public void setOccupationList(ArrayList<String> occupationList) {
		this.occupationList = occupationList;
	}
	public ArrayList<Master> getDeclerationTitleList() {
		return declerationTitleList;
	}
	public void setDeclerationTitleList(ArrayList<Master> declerationTitleList) {
		this.declerationTitleList = declerationTitleList;
	}
	public ArrayList<TreatmentEpisode> getSourceOfReferralList() {
		return sourceOfReferralList;
	}
	public void setSourceOfReferralList(
			ArrayList<TreatmentEpisode> sourceOfReferralList) {
		this.sourceOfReferralList = sourceOfReferralList;
	}
	public ArrayList<String> getApmtDurationList() {
		return apmtDurationList;
	}
	public void setApmtDurationList(ArrayList<String> apmtDurationList) {
		this.apmtDurationList = apmtDurationList;
	}
	public ArrayList<ThirdParty> getTpnameList() {
		return tpnameList;
	}
	public void setTpnameList(ArrayList<ThirdParty> tpnameList) {
		this.tpnameList = tpnameList;
	}
	public ArrayList<DiaryManagement> getUserList() {
		return userList;
	}
	public void setUserList(ArrayList<DiaryManagement> userList) {
		this.userList = userList;
	}
	public ArrayList<Client> getCondtitionList() {
		return condtitionList;
	}
	public void setCondtitionList(ArrayList<Client> condtitionList) {
		this.condtitionList = condtitionList;
	}
	public String getDectitle() {
		return dectitle;
	}
	public void setDectitle(String dectitle) {
		this.dectitle = dectitle;
	}
	public ArrayList<Client> getDiagnosisList() {
		return diagnosisList;
	}
	public void setDiagnosisList(ArrayList<Client> diagnosisList) {
		this.diagnosisList = diagnosisList;
	}
	public ArrayList<Client> getAllPatientList() {
		return allPatientList;
	}
	public void setAllPatientList(ArrayList<Client> allPatientList) {
		this.allPatientList = allPatientList;
	}
	public String getPatient_department() {
		return patient_department;
	}
	public void setPatient_department(String patient_department) {
		this.patient_department = patient_department;
	}
	public String getTemplateID() {
		return templateID;
	}
	public void setTemplateID(String templateID) {
		this.templateID = templateID;
	}
	
	 public String ipdidsearch;

	public String getIpdidsearch() {
		return ipdidsearch;
	}
	public void setIpdidsearch(String ipdidsearch) {
		this.ipdidsearch = ipdidsearch;
	}
	 
}
