package com.apm.Report.web.form;

import java.util.ArrayList;

import com.apm.Accounts.eu.entity.Accounts;
import com.apm.Inventory.eu.entity.Product;
import com.apm.Master.eu.entity.Master;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.Report.eu.entity.MisReport;
import com.apm.Report.eu.entity.Report;

public class ReportForm {
	private String fromDate;
	private String toDate;
	private String report;
	private String columnOfList1;
	private String columnOfList2;

	private ArrayList<MisReport> arealist;
	
	
	private ArrayList<String>list1;
	private ArrayList<String>list2;
	private String ipdInitialAssesmentPer="0";
	
	private ArrayList<String>reportList;
	private String jobtitle;
	private ArrayList<String>practionerCommList;
	
	private ArrayList<Accounts> doctorShareList;
	private String emergencyAssesmentPer="0";
	
	private String nursingPlanDocument="0";
	
	//for kpi dashboard
	private String practitionerId;
	private String indicator;
	private ArrayList<Master> nabhcatagorylist;
	private ArrayList<Master> nabhsubcatagorylist;
	private String nabhcatalistid;
	private String nabhsubcatalistid;
	public String getNabhcatalistid() {
		return nabhcatalistid;
	}
	public void setNabhcatalistid(String nabhcatalistid) {
		this.nabhcatalistid = nabhcatalistid;
	}
	public String getNabhsubcatalistid() {
		return nabhsubcatalistid;
	}
	public void setNabhsubcatalistid(String nabhsubcatalistid) {
		this.nabhsubcatalistid = nabhsubcatalistid;
	}
	public ArrayList<Master> getNabhcatagorylist() {
		return nabhcatagorylist;
	}
	public void setNabhcatagorylist(ArrayList<Master> nabhcatagorylist) {
		this.nabhcatagorylist = nabhcatagorylist;
	}
	public ArrayList<Master> getNabhsubcatagorylist() {
		return nabhsubcatagorylist;
	}
	public void setNabhsubcatagorylist(ArrayList<Master> nabhsubcatagorylist) {
		this.nabhsubcatagorylist = nabhsubcatagorylist;
	}
	public String getIndicator() {
		return indicator;
	}
	public void setIndicator(String indicator) {
		this.indicator = indicator;
	}
	public String getFormula_desc() {
		return formula_desc;
	}
	public void setFormula_desc(String formula_desc) {
		this.formula_desc = formula_desc;
	}
	public String getFormula() {
		return formula;
	}
	public void setFormula(String formula) {
		this.formula = formula;
	}
	public String getNo_of_input() {
		return no_of_input;
	}
	public void setNo_of_input(String no_of_input) {
		this.no_of_input = no_of_input;
	}
	private String formula_desc;
	private String formula;
	private String no_of_input;
	
	public ArrayList<Report> getIndicatorlist() {
		return indicatorlist;
	}
	public void setIndicatorlist(ArrayList<Report> indicatorlist) {
		this.indicatorlist = indicatorlist;
	}
	private String areaid;
	private ArrayList<Report> indicatorlist;
	public String getAreaid() {
		return areaid;
	}
	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}
	public ArrayList<MisReport> getArealist() {
		return arealist;
	}
	public void setArealist(ArrayList<MisReport> arealist) {
		this.arealist = arealist;
	}
	private ArrayList<UserProfile> userProfileList;
	
	public ArrayList<UserProfile> getUserProfileList() {
		return userProfileList;
	}
	public void setUserProfileList(ArrayList<UserProfile> userProfileList) {
		this.userProfileList = userProfileList;
	}
	public String getColumnOfList1() {
		return columnOfList1;
	}
	public void setColumnOfList1(String columnOfList1) {
		this.columnOfList1 = columnOfList1;
	}
	public String getColumnOfList2() {
		return columnOfList2;
	}
	public void setColumnOfList2(String columnOfList2) {
		this.columnOfList2 = columnOfList2;
	}
	public ArrayList<String> getPractionerCommList() {
		return practionerCommList;
	}
	public void setPractionerCommList(ArrayList<String> practionerCommList) {
		this.practionerCommList = practionerCommList;
	}
	public ArrayList<String> getList1() {
		return list1;
	}
	public void setList1(ArrayList<String> list1) {
		this.list1 = list1;
	}
	public ArrayList<String> getList2() {
		return list2;
	}
	public void setList2(ArrayList<String> list2) {
		this.list2 = list2;
	}
	public ArrayList<String> getReportList() {
		return reportList;
	}
	public void setReportList(ArrayList<String> reportList) {
		this.reportList = reportList;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	public ArrayList<Accounts> getDoctorShareList() {
		return doctorShareList;
	}
	public void setDoctorShareList(ArrayList<Accounts> doctorShareList) {
		this.doctorShareList = doctorShareList;
	}
	public String getPractitionerId() {
		return practitionerId;
	}
	public void setPractitionerId(String practitionerId) {
		this.practitionerId = practitionerId;
	}
	public String getJobtitle() {
		return jobtitle;
	}
	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}
	public String getIpdInitialAssesmentPer() {
		return ipdInitialAssesmentPer;
	}
	public void setIpdInitialAssesmentPer(String ipdInitialAssesmentPer) {
		this.ipdInitialAssesmentPer = ipdInitialAssesmentPer;
	}
	public String getEmergencyAssesmentPer() {
		return emergencyAssesmentPer;
	}
	public void setEmergencyAssesmentPer(String emergencyAssesmentPer) {
		this.emergencyAssesmentPer = emergencyAssesmentPer;
	}
	public String getNursingPlanDocument() {
		return nursingPlanDocument;
	}
	public void setNursingPlanDocument(String nursingPlanDocument) {
		this.nursingPlanDocument = nursingPlanDocument;
	}
	public ArrayList<Product> getProductlist() {
		return productlist;
	}
	public void setProductlist(ArrayList<Product> productlist) {
		this.productlist = productlist;
	}
	public ArrayList<Master> getDepartmentlist() {
		return departmentlist;
	}
	public void setDepartmentlist(ArrayList<Master> departmentlist) {
		this.departmentlist = departmentlist;
	}
	public String getIpdopd() {
		return ipdopd;
	}
	public void setIpdopd(String ipdopd) {
		this.ipdopd = ipdopd;
	}
	private ArrayList<Product> productlist;
	private ArrayList<Master> departmentlist;
	private String ipdopd;
}
