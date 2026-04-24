package com.apm.Report.eu.entity;

import java.util.ArrayList;

public class MisReport {
	
	private String ipd_admission,ipd_discharge,ipd_inhouse,ipd_vacantbed;
	 private String opd_booked,opd_completed,opd_dna,total_male,total_female,upto20;
	 private String upto40,upto60,above60;
	
	public String getIpd_admission() {
		return ipd_admission;
	}

	public void setIpd_admission(String ipd_admission) {
		this.ipd_admission = ipd_admission;
	}

	public String getIpd_discharge() {
		return ipd_discharge;
	}

	public void setIpd_discharge(String ipd_discharge) {
		this.ipd_discharge = ipd_discharge;
	}

	public String getIpd_inhouse() {
		return ipd_inhouse;
	}

	public void setIpd_inhouse(String ipd_inhouse) {
		this.ipd_inhouse = ipd_inhouse;
	}

	public String getIpd_vacantbed() {
		return ipd_vacantbed;
	}

	public void setIpd_vacantbed(String ipd_vacantbed) {
		this.ipd_vacantbed = ipd_vacantbed;
	}

	public String getOpd_booked() {
		return opd_booked;
	}

	public void setOpd_booked(String opd_booked) {
		this.opd_booked = opd_booked;
	}

	public String getOpd_completed() {
		return opd_completed;
	}

	public void setOpd_completed(String opd_completed) {
		this.opd_completed = opd_completed;
	}

	public String getOpd_dna() {
		return opd_dna;
	}

	public void setOpd_dna(String opd_dna) {
		this.opd_dna = opd_dna;
	}

	public String getTotal_male() {
		return total_male;
	}

	public void setTotal_male(String total_male) {
		this.total_male = total_male;
	}

	public String getTotal_female() {
		return total_female;
	}

	public void setTotal_female(String total_female) {
		this.total_female = total_female;
	}

	public String getUpto20() {
		return upto20;
	}

	public void setUpto20(String upto20) {
		this.upto20 = upto20;
	}

	public String getUpto40() {
		return upto40;
	}

	public void setUpto40(String upto40) {
		this.upto40 = upto40;
	}

	public String getUpto60() {
		return upto60;
	}

	public void setUpto60(String upto60) {
		this.upto60 = upto60;
	}

	public String getAbove60() {
		return above60;
	}

	public void setAbove60(String above60) {
		this.above60 = above60;
	}

	public String getReport_name() {
		return report_name;
	}

	public void setReport_name(String report_name) {
		this.report_name = report_name;
	}

	public String getFrom_date() {
		return from_date;
	}

	public void setFrom_date(String from_date) {
		this.from_date = from_date;
	}

	public String getTo_date() {
		return to_date;
	}

	public void setTo_date(String to_date) {
		this.to_date = to_date;
	}

	public String getCurrent_date() {
		return current_date;
	}

	public void setCurrent_date(String current_date) {
		this.current_date = current_date;
	}

	private String report_name;
	private String from_date;
	private String to_date;
	private String current_date;
	
	
	
	
	
	
	private String clinicName;
	
	private String invoiceno;
	
	private String location;
	
	private String nhsNumber;
	
	private String age;
	
	private String gender;
	
	private String practiceCode = "N/A";
	
	private String CommissionerCCG = "N/A";
	
	private String subCCD = "N/A";
	
	private String otherSubCCD = "N/A";
	
	private String sourceofRefferal;
	
	private String refferalRecievedDate;
	
	private String urgency;
	
	private String assesmentDate;
	
	private String reftoAssesmentDays;
	
	private String practitonerType;
	
	private String apptDate;
	
	private String apptTime;
	
	private String practitonerSurname;
	
	private String TreatmentOutcome;
	
	private String dischargeReason;
	
	private String apptName;
	
	private String apptCharge;
	
	private int id;
	
	private String name;
	
	private String kpiareaid;
	private String kpiindicator;
	private String no_of_input;
	private String formula;
	private String formula_desc;
	private String kpiarea;
	private String kpiindicatorid;
	private String kpiid;
	private String input1;
	private String target;
	private String ismannual;
	private String average;
	private String clientname;
	private ArrayList<MisReport> datalist;
	private String subject;
	private String subcatname;
	private String subcatid;
	private String evidence;
	private ArrayList<MisReport> satDataList;
	private ArrayList<MisReport> areaarraylist;
	private String practname;
	private ArrayList<MisReport> opdappointmenttypelist;
	private ArrayList<MisReport> ipdnewaddmissionlist;
	
	public String indicator;
	public String indicatorid;
	public String areaid;
	public String area;
	public ArrayList<MisReport> indicatorlist;
	public ArrayList<MisReport> arealist;
	private String remark;
	private String category;
	private String filename;
	private String uploadtype;
	private int chcklistid;
	private String chcklistname;
	private int chkliststatus;
	private ArrayList<MisReport> indicatorchklist ;
	private String complete_discription, submit_discription;
	private int pendingcount,submitcount,completecount;
	private String filePathnew;
	private String filesubmodule;
	private String filesearchkey;
	private String filePathlink;
	private String doctitle, docfeature;
	
	public String getDoctitle() {
		return doctitle;
	}

	public void setDoctitle(String doctitle) {
		this.doctitle = doctitle;
	}

	public String getDocfeature() {
		return docfeature;
	}

	public void setDocfeature(String docfeature) {
		this.docfeature = docfeature;
	}

	public String getFilesubmodule() {
		return filesubmodule;
	}

	public String getFilePathlink() {
		return filePathlink;
	}

	public void setFilePathlink(String filePathlink) {
		this.filePathlink = filePathlink;
	}

	public void setFilesubmodule(String filesubmodule) {
		this.filesubmodule = filesubmodule;
	}

	public String getFilesearchkey() {
		return filesearchkey;
	}

	public void setFilesearchkey(String filesearchkey) {
		this.filesearchkey = filesearchkey;
	}

	public String getFilePathnew() {
		return filePathnew;
	}

	public void setFilePathnew(String filePathnew) {
		this.filePathnew = filePathnew;
	}

	public int getPendingcount() {
		return pendingcount;
	}

	public void setPendingcount(int pendingcount) {
		this.pendingcount = pendingcount;
	}

	public int getSubmitcount() {
		return submitcount;
	}

	public void setSubmitcount(int submitcount) {
		this.submitcount = submitcount;
	}

	public int getCompletecount() {
		return completecount;
	}

	public void setCompletecount(int completecount) {
		this.completecount = completecount;
	}

	public String getComplete_discription() {
		return complete_discription;
	}

	public void setComplete_discription(String complete_discription) {
		this.complete_discription = complete_discription;
	}

	public String getSubmit_discription() {
		return submit_discription;
	}

	public void setSubmit_discription(String submit_discription) {
		this.submit_discription = submit_discription;
	}

	public ArrayList<MisReport> getIndicatorchklist() {
		return indicatorchklist;
	}

	public void setIndicatorchklist(ArrayList<MisReport> indicatorchklist) {
		this.indicatorchklist = indicatorchklist;
	}

	public int getChcklistid() {
		return chcklistid;
	}

	public void setChcklistid(int chcklistid) {
		this.chcklistid = chcklistid;
	}

	public String getChcklistname() {
		return chcklistname;
	}

	public void setChcklistname(String chcklistname) {
		this.chcklistname = chcklistname;
	}

	public int getChkliststatus() {
		return chkliststatus;
	}

	public void setChkliststatus(int chkliststatus) {
		this.chkliststatus = chkliststatus;
	}

	public String getUploadtype() {
		return uploadtype;
	}

	public void setUploadtype(String uploadtype) {
		this.uploadtype = uploadtype;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIndicator() {
		return indicator;
	}

	public void setIndicator(String indicator) {
		this.indicator = indicator;
	}

	public String getIndicatorid() {
		return indicatorid;
	}

	public void setIndicatorid(String indicatorid) {
		this.indicatorid = indicatorid;
	}

	public String getAreaid() {
		return areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public ArrayList<MisReport> getIndicatorlist() {
		return indicatorlist;
	}

	public void setIndicatorlist(ArrayList<MisReport> indicatorlist) {
		this.indicatorlist = indicatorlist;
	}

	public ArrayList<MisReport> getArealist() {
		return arealist;
	}

	public void setArealist(ArrayList<MisReport> arealist) {
		this.arealist = arealist;
	}

	public ArrayList<MisReport> getIpdnewaddmissionlist() {
		return ipdnewaddmissionlist;
	}

	public void setIpdnewaddmissionlist(ArrayList<MisReport> ipdnewaddmissionlist) {
		this.ipdnewaddmissionlist = ipdnewaddmissionlist;
	}

	public ArrayList<MisReport> getOpdappointmenttypelist() {
		return opdappointmenttypelist;
	}

	public void setOpdappointmenttypelist(ArrayList<MisReport> opdappointmenttypelist) {
		this.opdappointmenttypelist = opdappointmenttypelist;
	}

	public String getPractname() {
		return practname;
	}

	public void setPractname(String practname) {
		this.practname = practname;
	}

	public ArrayList<MisReport> getAreaarraylist() {
		return areaarraylist;
	}

	public void setAreaarraylist(ArrayList<MisReport> areaarraylist) {
		this.areaarraylist = areaarraylist;
	}

	public ArrayList<MisReport> getSatDataList() {
		return satDataList;
	}

	public void setSatDataList(ArrayList<MisReport> satDataList) {
		this.satDataList = satDataList;
	}

	public String getEvidence() {
		return evidence;
	}

	public void setEvidence(String evidence) {
		this.evidence = evidence;
	}

	public String getSubcatid() {
		return subcatid;
	}

	public void setSubcatid(String subcatid) {
		this.subcatid = subcatid;
	}

	public String getSubcatname() {
		return subcatname;
	}

	public void setSubcatname(String subcatname) {
		this.subcatname = subcatname;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public ArrayList<MisReport> getDatalist() {
		return datalist;
	}

	public void setDatalist(ArrayList<MisReport> datalist) {
		this.datalist = datalist;
	}

	public String getClientname() {
		return clientname;
	}

	public void setClientname(String clientname) {
		this.clientname = clientname;
	}

	public String getAverage() {
		return average;
	}

	public void setAverage(String average) {
		this.average = average;
	}

	public String getIsmannual() {
		return ismannual;
	}

	public void setIsmannual(String ismannual) {
		this.ismannual = ismannual;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	private String fname;
	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private String message;
	private ArrayList<String> no_of_inputList;

	public ArrayList<String> getNo_of_inputList() {
		return no_of_inputList;
	}

	public void setNo_of_inputList(ArrayList<String> no_of_inputList) {
		this.no_of_inputList = no_of_inputList;
	}

	public String getInput1() {
		return input1;
	}

	public void setInput1(String input1) {
		this.input1 = input1;
	}

	public String getInput2() {
		return input2;
	}

	public void setInput2(String input2) {
		this.input2 = input2;
	}

	public String getInput3() {
		return input3;
	}

	public void setInput3(String input3) {
		this.input3 = input3;
	}

	public String getInput4() {
		return input4;
	}

	public void setInput4(String input4) {
		this.input4 = input4;
	}

	public String getInput5() {
		return input5;
	}

	public void setInput5(String input5) {
		this.input5 = input5;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getKpi_year() {
		return kpi_year;
	}

	public void setKpi_year(String kpi_year) {
		this.kpi_year = kpi_year;
	}

	public String getKpi_month() {
		return kpi_month;
	}

	public void setKpi_month(String kpi_month) {
		this.kpi_month = kpi_month;
	}

	private String input2;
	private String input3;
	private String input4;
	private String input5;
	private String result;
	private String kpi_year;
	private String kpi_month;
	private String is_active;
	private String kpi_dataid;
	private String comment;
	private String userid;
	private String datetime;
	private String edit_userid;
	private String inputData;
	private ArrayList<MisReport> noofinputlist;
	public String getMonth1() {
		return month1;
	}

	public void setMonth1(String month1) {
		this.month1 = month1;
	}

	public String getMonth2() {
		return month2;
	}

	public void setMonth2(String month2) {
		this.month2 = month2;
	}

	public String getMonth3() {
		return month3;
	}

	public void setMonth3(String month3) {
		this.month3 = month3;
	}

	public String getMonth4() {
		return month4;
	}

	public void setMonth4(String month4) {
		this.month4 = month4;
	}

	public String getMonth5() {
		return month5;
	}

	public void setMonth5(String month5) {
		this.month5 = month5;
	}

	public String getMonth6() {
		return month6;
	}

	public void setMonth6(String month6) {
		this.month6 = month6;
	}

	public String getMonth7() {
		return month7;
	}

	public void setMonth7(String month7) {
		this.month7 = month7;
	}

	public String getMonth8() {
		return month8;
	}

	public void setMonth8(String month8) {
		this.month8 = month8;
	}

	public String getMonth9() {
		return month9;
	}

	public void setMonth9(String month9) {
		this.month9 = month9;
	}

	public String getMonth10() {
		return month10;
	}

	public void setMonth10(String month10) {
		this.month10 = month10;
	}

	public String getMonth11() {
		return month11;
	}

	public void setMonth11(String month11) {
		this.month11 = month11;
	}

	public String getMonth12() {
		return month12;
	}

	public void setMonth12(String month12) {
		this.month12 = month12;
	}
	private String month1;
	private String month2;
	private String month3;
	private String month4;
	private String month5;
	private String month6;
	private String month7;
	private String month8;
	private String month9;
	private String month10;
	private String month11;
	private String month12;
	private String month1_target;
	private String month2_target;
	private String month3_target;
	private String month4_target;
	private String month5_target;
	private String month6_target;
	private String month7_target;
	private String month8_target;
	private String month9_target;
	private String month10_target;
	private String month11_target;
	private String month12_target;
	public ArrayList<MisReport> getNoofinputlist() {
		return noofinputlist;
	}

	public String getMonth1_target() {
		return month1_target;
	}

	public void setMonth1_target(String month1_target) {
		this.month1_target = month1_target;
	}

	public String getMonth2_target() {
		return month2_target;
	}

	public void setMonth2_target(String month2_target) {
		this.month2_target = month2_target;
	}

	public String getMonth3_target() {
		return month3_target;
	}

	public void setMonth3_target(String month3_target) {
		this.month3_target = month3_target;
	}

	public String getMonth4_target() {
		return month4_target;
	}

	public void setMonth4_target(String month4_target) {
		this.month4_target = month4_target;
	}

	public String getMonth5_target() {
		return month5_target;
	}

	public void setMonth5_target(String month5_target) {
		this.month5_target = month5_target;
	}

	public String getMonth6_target() {
		return month6_target;
	}

	public void setMonth6_target(String month6_target) {
		this.month6_target = month6_target;
	}

	public String getMonth7_target() {
		return month7_target;
	}

	public void setMonth7_target(String month7_target) {
		this.month7_target = month7_target;
	}

	public String getMonth8_target() {
		return month8_target;
	}

	public void setMonth8_target(String month8_target) {
		this.month8_target = month8_target;
	}

	public String getMonth9_target() {
		return month9_target;
	}

	public void setMonth9_target(String month9_target) {
		this.month9_target = month9_target;
	}

	public String getMonth10_target() {
		return month10_target;
	}

	public void setMonth10_target(String month10_target) {
		this.month10_target = month10_target;
	}

	public String getMonth11_target() {
		return month11_target;
	}

	public void setMonth11_target(String month11_target) {
		this.month11_target = month11_target;
	}

	public String getMonth12_target() {
		return month12_target;
	}

	public void setMonth12_target(String month12_target) {
		this.month12_target = month12_target;
	}

	public void setNoofinputlist(ArrayList<MisReport> noofinputlist) {
		this.noofinputlist = noofinputlist;
	}

	public String getInputData() {
		return inputData;
	}

	public void setInputData(String inputData) {
		this.inputData = inputData;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getEdit_userid() {
		return edit_userid;
	}

	public void setEdit_userid(String edit_userid) {
		this.edit_userid = edit_userid;
	}

	public String getEdit_datetime() {
		return edit_datetime;
	}

	public void setEdit_datetime(String edit_datetime) {
		this.edit_datetime = edit_datetime;
	}

	private String edit_datetime;
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getKpi_dataid() {
		return kpi_dataid;
	}

	public void setKpi_dataid(String kpi_dataid) {
		this.kpi_dataid = kpi_dataid;
	}

	public String getIs_active() {
		return is_active;
	}

	public void setIs_active(String is_active) {
		this.is_active = is_active;
	}

	
	
	public String getKpiid() {
		return kpiid;
	}

	public void setKpiid(String kpiid) {
		this.kpiid = kpiid;
	}

	public String getKpiareaid() {
		return kpiareaid;
	}

	public void setKpiareaid(String kpiareaid) {
		this.kpiareaid = kpiareaid;
	}

	public String getKpiindicator() {
		return kpiindicator;
	}

	public void setKpiindicator(String kpiindicator) {
		this.kpiindicator = kpiindicator;
	}

	public String getNo_of_input() {
		return no_of_input;
	}

	public void setNo_of_input(String no_of_input) {
		this.no_of_input = no_of_input;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public String getFormula_desc() {
		return formula_desc;
	}

	public void setFormula_desc(String formula_desc) {
		this.formula_desc = formula_desc;
	}

	public String getKpiarea() {
		return kpiarea;
	}

	public void setKpiarea(String kpiarea) {
		this.kpiarea = kpiarea;
	}

	public String getKpiindicatorid() {
		return kpiindicatorid;
	}

	public void setKpiindicatorid(String kpiindicatorid) {
		this.kpiindicatorid = kpiindicatorid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClinicName() {
		return clinicName;
	}

	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}

	public String getInvoiceno() {
		return invoiceno;
	}

	public void setInvoiceno(String invoiceno) {
		this.invoiceno = invoiceno;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getNhsNumber() {
		return nhsNumber;
	}

	public void setNhsNumber(String nhsNumber) {
		this.nhsNumber = nhsNumber;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPracticeCode() {
		return practiceCode;
	}

	public void setPracticeCode(String practiceCode) {
		this.practiceCode = practiceCode;
	}

	public String getCommissionerCCG() {
		return CommissionerCCG;
	}

	public void setCommissionerCCG(String commissionerCCG) {
		CommissionerCCG = commissionerCCG;
	}

	public String getSubCCD() {
		return subCCD;
	}

	public void setSubCCD(String subCCD) {
		this.subCCD = subCCD;
	}

	public String getOtherSubCCD() {
		return otherSubCCD;
	}

	public void setOtherSubCCD(String otherSubCCD) {
		this.otherSubCCD = otherSubCCD;
	}

	public String getSourceofRefferal() {
		return sourceofRefferal;
	}

	public void setSourceofRefferal(String sourceofRefferal) {
		this.sourceofRefferal = sourceofRefferal;
	}

	public String getRefferalRecievedDate() {
		return refferalRecievedDate;
	}

	public void setRefferalRecievedDate(String refferalRecievedDate) {
		this.refferalRecievedDate = refferalRecievedDate;
	}

	public String getUrgency() {
		return urgency;
	}

	public void setUrgency(String urgency) {
		this.urgency = urgency;
	}

	public String getAssesmentDate() {
		return assesmentDate;
	}

	public void setAssesmentDate(String assesmentDate) {
		this.assesmentDate = assesmentDate;
	}

	public String getReftoAssesmentDays() {
		return reftoAssesmentDays;
	}

	public void setReftoAssesmentDays(String reftoAssesmentDays) {
		this.reftoAssesmentDays = reftoAssesmentDays;
	}

	public String getPractitonerType() {
		return practitonerType;
	}

	public void setPractitonerType(String practitonerType) {
		this.practitonerType = practitonerType;
	}

	public String getApptDate() {
		return apptDate;
	}

	public void setApptDate(String apptDate) {
		this.apptDate = apptDate;
	}

	public String getApptTime() {
		return apptTime;
	}

	public void setApptTime(String apptTime) {
		this.apptTime = apptTime;
	}

	public String getPractitonerSurname() {
		return practitonerSurname;
	}

	public void setPractitonerSurname(String practitonerSurname) {
		this.practitonerSurname = practitonerSurname;
	}

	public String getTreatmentOutcome() {
		return TreatmentOutcome;
	}

	public void setTreatmentOutcome(String treatmentOutcome) {
		TreatmentOutcome = treatmentOutcome;
	}

	public String getDischargeReason() {
		return dischargeReason;
	}

	public void setDischargeReason(String dischargeReason) {
		this.dischargeReason = dischargeReason;
	}

	public String getApptName() {
		return apptName;
	}

	public void setApptName(String apptName) {
		this.apptName = apptName;
	}

	public String getApptCharge() {
		return apptCharge;
	}

	public void setApptCharge(String apptCharge) {
		this.apptCharge = apptCharge;
	}
	
	
	
	

}
