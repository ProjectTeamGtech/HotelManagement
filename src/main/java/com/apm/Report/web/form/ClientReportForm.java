package com.apm.Report.web.form;

import java.util.ArrayList;

import com.apm.Accounts.eu.entity.Accounts;
import com.apm.Diagnosis.eu.entity.Diagnosis;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.Emr.eu.entity.Investigation;
import com.apm.Inventory.eu.entity.Procurement;
import com.apm.Inventory.eu.entity.Product;
import com.apm.Inventory.eu.entity.Vendor;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.TreatmentType;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.Report.eu.entity.SummaryReport;

public class ClientReportForm {
	
	public ArrayList<Master> getDeptWiseCountList() {
		return deptWiseCountList;
	}

	public void setDeptWiseCountList(ArrayList<Master> deptWiseCountList) {
		this.deptWiseCountList = deptWiseCountList;
	}

	public int getFinalNewPatientCount() {
		return finalNewPatientCount;
	}

	public void setFinalNewPatientCount(int finalNewPatientCount) {
		this.finalNewPatientCount = finalNewPatientCount;
	}

	public int getFinalOldPatientCount() {
		return finalOldPatientCount;
	}

	public void setFinalOldPatientCount(int finalOldPatientCount) {
		this.finalOldPatientCount = finalOldPatientCount;
	}

	public int getFinalTotalPatientCount() {
		return finalTotalPatientCount;
	}

	public void setFinalTotalPatientCount(int finalTotalPatientCount) {
		this.finalTotalPatientCount = finalTotalPatientCount;
	}

	public ArrayList<Client> getSmsloglist() {
		return smsloglist;
	}

	public void setSmsloglist(ArrayList<Client> smsloglist) {
		this.smsloglist = smsloglist;
	}

	public String getPatientname() {
		return patientname;
	}

	public void setPatientname(String patientname) {
		this.patientname = patientname;
	}

	public String getMobno() {
		return mobno;
	}

	public void setMobno(String mobno) {
		this.mobno = mobno;
	}

	private ArrayList<Client> clientList;
	
	private ArrayList<Client> currentTreatmentNoFutureApmtsList;
	
	private ArrayList<Client> noActivityRecordList;
	
	private ArrayList<Product>subcategorylist ;
	
	private ArrayList<Product>subcategoryrpt ;
	private ArrayList<Master> deptWiseCountList;
	private int finalNewPatientCount=0;
    private int finalOldPatientCount=0;
    private int finalTotalPatientCount=0;
	public ArrayList<Product> getSubcategoryrpt() {
		return subcategoryrpt;
	}

	public void setSubcategoryrpt(ArrayList<Product> subcategoryrpt) {
		this.subcategoryrpt = subcategoryrpt;
	}

	public ArrayList<Product> getSubcategorylist() {
		return subcategorylist;
	}

	public void setSubcategorylist(ArrayList<Product> subcategorylist) {
		this.subcategorylist = subcategorylist;
	}

	public String getMonth1() {
		return month1;
	}

	public void setMonth1(String month1) {
		this.month1 = month1;
	}

	public String getYear2() {
		return year2;
	}

	public void setYear2(String year2) {
		this.year2 = year2;
	}

	public String getYear3() {
		return year3;
	}

	public void setYear3(String year3) {
		this.year3 = year3;
	}

	private ArrayList<Client> DNANoFutureAppReport;
	private ArrayList<Client> anesthesiaList;
	private ArrayList<Client> noActivityClientRecordList;
	private ArrayList<SummaryReport> ipdrevenuematrix;
	private ArrayList<DiaryManagement> userList;
	private ArrayList<SummaryReport> nosofadmissiondischarge;
	private String pagerecords;
	private String otroom;
	private String otuser;
	private String otuser1;
	private String year;
	private String anesthesia;
	private ArrayList<Accounts>thirdPartyList;
	private ArrayList<UserProfile>surgeonlist;
	private ArrayList<Accounts>locationList;
	private ArrayList<Bed> wardlist;
	private ArrayList<TreatmentType> conditionList;
	private String thirdParty;
	private ArrayList<DiaryManagement> allChargeWardlist;
	private String location;
	private String year1;
	private String fromDate = "";
	
	private String toDate = "";
	private String month1= "";
	private String year2 = "";
	private String year3 = "";
	private String payby;
	
	private String diaryUser;
	
	private String orderby = "";
	
	private String order = "asc";
	
	
	private String condition1;
	private String condition2;
	private String condition3;
	private String type;
	
	
	private ArrayList<SummaryReport> ipdConditionList;
	private ArrayList<SummaryReport> opdConditionList;
	private ArrayList<SummaryReport> otreportlist;
	private ArrayList<Master> citylist;
	private ArrayList<Master> otroomlist;
	private String city;
	private ArrayList<Product> stockvaluationlist;
	private ArrayList<Master> locationlist ;
	private ArrayList<SummaryReport> departmentwisereportlist;
	private ArrayList<Product> departmaterialissuelist;
	private String filteroforder;
	private String clinicName;
	 private String clinicOwner;
	 private String clinicemail;
	 private String clinicaddress;
	 private String clinicity;
	 private String websiteUrl;
	 private String landLine;
	 private ArrayList<Clinic> locationAdressList;
	 private String owner_qualification;
	 private String clinicLogo;
	 private String address;
	 private ArrayList<Product> nonmovingitemlist;
	 private ArrayList<Procurement> paybleaginglist;
	 private String report_filter;
	 private ArrayList<Product> stocklist;
	
	 private String withstock_filter;
	 private String subcat_filter;
	 private ArrayList<Product> subcategoryList;
	 private ArrayList<Product> categoryList;
	 private String cat_filter;
	 private ArrayList<UserProfile> userlist;
	 private ArrayList<Investigation> outsourcelist;
	 
	private String totalmrp ="0";
	private String totalsaleprice="0";
	//int to double 
	private double totalqty=0;
	private String totalpurchaseprice;
	private ArrayList<Master> warehouseList;
	private String warehouse_id;
	private String fromwherefilter;
	private String isfromcathlab;
	private String searchtext;
	private String categoryid;
	private String subcategoryid;
	private double totalsalevaluation;
	private String month;
	private String adjustmenttype;
	private int isfrombincard;
	private ArrayList<Bed> bedoccupancylist;
    private ArrayList<Accounts>renderchrgesreport ;
	private int totaldifference;
	private String name;
	ArrayList<Client> chargeTypeList;
	private String chargeType;
	private Double totalrevenue;
	
	

	public Double getTotalrevenue() {
		return totalrevenue;
	}

	public void setTotalrevenue(Double totalrevenue) {
		this.totalrevenue = totalrevenue;
	}

	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}

	public ArrayList<Client> getChargeTypeList() {
		return chargeTypeList;
	}

	public void setChargeTypeList(ArrayList<Client> chargeTypeList) {
		this.chargeTypeList = chargeTypeList;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public int getTotaldifference() {
		return totaldifference;
	}

	public void setTotaldifference(int totaldifference) {
		this.totaldifference = totaldifference;
	}
	
	
	public ArrayList<Accounts> getRenderchrgesreport() {
		return renderchrgesreport;
	}

	public void setRenderchrgesreport(ArrayList<Accounts> renderchrgesreport) {
		this.renderchrgesreport = renderchrgesreport;
	}

	public ArrayList<Bed> getBedoccupancylist() {
		return bedoccupancylist;
	}

	public void setBedoccupancylist(ArrayList<Bed> bedoccupancylist) {
		this.bedoccupancylist = bedoccupancylist;
	}

	public int getIsfrombincard() {
		return isfrombincard;
	}

	public void setIsfrombincard(int isfrombincard) {
		this.isfrombincard = isfrombincard;
	}

	public String getAdjustmenttype() {
		return adjustmenttype;
	}

	public void setAdjustmenttype(String adjustmenttype) {
		this.adjustmenttype = adjustmenttype;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public double getTotalsalevaluation() {
		return totalsalevaluation;
	}

	public void setTotalsalevaluation(double totalsalevaluation) {
		this.totalsalevaluation = totalsalevaluation;
	}

	public String getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}

	public String getSubcategoryid() {
		return subcategoryid;
	}

	public void setSubcategoryid(String subcategoryid) {
		this.subcategoryid = subcategoryid;
	}

	public String getSearchtext() {
		return searchtext;
	}

	public void setSearchtext(String searchtext) {
		this.searchtext = searchtext;
	}

	public String getIsfromcathlab() {
		return isfromcathlab;
	}

	public void setIsfromcathlab(String isfromcathlab) {
		this.isfromcathlab = isfromcathlab;
	}

	public String getFromwherefilter() {
		return fromwherefilter;
	}

	public void setFromwherefilter(String fromwherefilter) {
		this.fromwherefilter = fromwherefilter;
	}

	public ArrayList<Master> getWarehouseList() {
		return warehouseList;
	}

	public void setWarehouseList(ArrayList<Master> warehouseList) {
		this.warehouseList = warehouseList;
	}

	public String getWarehouse_id() {
		return warehouse_id;
	}

	public void setWarehouse_id(String warehouse_id) {
		this.warehouse_id = warehouse_id;
	}

	public String getTotalpurchaseprice() {
		return totalpurchaseprice;
	}

	public void setTotalpurchaseprice(String totalpurchaseprice) {
		this.totalpurchaseprice = totalpurchaseprice;
	}

	public String getTotalmrp() {
		return totalmrp;
	}

	public void setTotalmrp(String totalmrp) {
		this.totalmrp = totalmrp;
	}

	public String getTotalsaleprice() {
		return totalsaleprice;
	}

	public void setTotalsaleprice(String totalsaleprice) {
		this.totalsaleprice = totalsaleprice;
	}

	public double getTotalqty() {
		return totalqty;
	}

	public void setTotalqty(double totalqty) {
		this.totalqty = totalqty;
	}

	public ArrayList<Investigation> getOutsourcelist() {
		return outsourcelist;
	}

	public void setOutsourcelist(ArrayList<Investigation> outsourcelist) {
		this.outsourcelist = outsourcelist;
	}

	public ArrayList<UserProfile> getUserlist() {
		return userlist;
	}

	public void setUserlist(ArrayList<UserProfile> userlist) {
		this.userlist = userlist;
	}

	public String getCat_filter() {
		return cat_filter;
	}

	public void setCat_filter(String cat_filter) {
		this.cat_filter = cat_filter;
	}

	public ArrayList<Product> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(ArrayList<Product> categoryList) {
		this.categoryList = categoryList;
	}

	public ArrayList<Product> getSubcategoryList() {
		return subcategoryList;
	}

	public void setSubcategoryList(ArrayList<Product> subcategoryList) {
		this.subcategoryList = subcategoryList;
	}

	public String getWithstock_filter() {
		return withstock_filter;
	}

	public void setWithstock_filter(String withstock_filter) {
		this.withstock_filter = withstock_filter;
	}

	public String getSubcat_filter() {
		return subcat_filter;
	}

	public void setSubcat_filter(String subcat_filter) {
		this.subcat_filter = subcat_filter;
	}

	public ArrayList<Product> getStocklist() {
		return stocklist;
	}

	public void setStocklist(ArrayList<Product> stocklist) {
		this.stocklist = stocklist;
	}

	public String getReport_filter() {
		return report_filter;
	}

	public void setReport_filter(String report_filter) {
		this.report_filter = report_filter;
	}

	public ArrayList<Procurement> getPaybleaginglist() {
		return paybleaginglist;
	}

	public void setPaybleaginglist(ArrayList<Procurement> paybleaginglist) {
		this.paybleaginglist = paybleaginglist;
	}

	public ArrayList<Product> getNonmovingitemlist() {
		return nonmovingitemlist;
	}

	public void setNonmovingitemlist(ArrayList<Product> nonmovingitemlist) {
		this.nonmovingitemlist = nonmovingitemlist;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getClinicName() {
		return clinicName;
	}

	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}

	public String getClinicOwner() {
		return clinicOwner;
	}

	public void setClinicOwner(String clinicOwner) {
		this.clinicOwner = clinicOwner;
	}

	public String getClinicemail() {
		return clinicemail;
	}

	public void setClinicemail(String clinicemail) {
		this.clinicemail = clinicemail;
	}

	public String getClinicaddress() {
		return clinicaddress;
	}

	public void setClinicaddress(String clinicaddress) {
		this.clinicaddress = clinicaddress;
	}

	public String getClinicity() {
		return clinicity;
	}

	public void setClinicity(String clinicity) {
		this.clinicity = clinicity;
	}

	public String getWebsiteUrl() {
		return websiteUrl;
	}

	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}

	public String getLandLine() {
		return landLine;
	}

	public void setLandLine(String landLine) {
		this.landLine = landLine;
	}

	public ArrayList<Clinic> getLocationAdressList() {
		return locationAdressList;
	}

	public void setLocationAdressList(ArrayList<Clinic> locationAdressList) {
		this.locationAdressList = locationAdressList;
	}

	public String getOwner_qualification() {
		return owner_qualification;
	}

	public void setOwner_qualification(String owner_qualification) {
		this.owner_qualification = owner_qualification;
	}

	public String getClinicLogo() {
		return clinicLogo;
	}

	public void setClinicLogo(String clinicLogo) {
		this.clinicLogo = clinicLogo;
	}

	public String getFilteroforder() {
		return filteroforder;
	}

	public void setFilteroforder(String filteroforder) {
		this.filteroforder = filteroforder;
	}

	public ArrayList<Product> getDepartmaterialissuelist() {
		return departmaterialissuelist;
	}

	public void setDepartmaterialissuelist(ArrayList<Product> departmaterialissuelist) {
		this.departmaterialissuelist = departmaterialissuelist;
	}

	public ArrayList<SummaryReport> getDepartmentwisereportlist() {
		return departmentwisereportlist;
	}

	public void setDepartmentwisereportlist(ArrayList<SummaryReport> departmentwisereportlist) {
		this.departmentwisereportlist = departmentwisereportlist;
	}

	public ArrayList<Master> getLocationlist() {
		return locationlist;
	}

	public void setLocationlist(ArrayList<Master> locationlist) {
		this.locationlist = locationlist;
	}

	public ArrayList<Product> getStockvaluationlist() {
		return stockvaluationlist;
	}

	public void setStockvaluationlist(ArrayList<Product> stockvaluationlist) {
		this.stockvaluationlist = stockvaluationlist;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public ArrayList<Master> getCitylist() {
		return citylist;
	}

	public void setCitylist(ArrayList<Master> citylist) {
		this.citylist = citylist;
	}

	public String getCondition1() {
		return condition1;
	}

	public void setCondition1(String condition1) {
		this.condition1 = condition1;
	}

	public String getCondition2() {
		return condition2;
	}

	public void setCondition2(String condition2) {
		this.condition2 = condition2;
	}

	public String getCondition3() {
		return condition3;
	}

	public void setCondition3(String condition3) {
		this.condition3 = condition3;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getDiaryUser() {
		return diaryUser;
	}

	public void setDiaryUser(String diaryUser) {
		this.diaryUser = diaryUser;
	}

	public String getPayby() {
		return payby;
	}

	public void setPayby(String payby) {
		this.payby = payby;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getThirdParty() {
		return thirdParty;
	}

	public void setThirdParty(String thirdParty) {
		this.thirdParty = thirdParty;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}


	public ArrayList<Accounts> getLocationList() {
		return locationList;
	}

	public void setLocationList(ArrayList<Accounts> locationList) {
		this.locationList = locationList;
	}

	public ArrayList<Accounts> getThirdPartyList() {
		return thirdPartyList;
	}

	public void setThirdPartyList(ArrayList<Accounts> thirdPartyList) {
		this.thirdPartyList = thirdPartyList;
	}

	public String getPagerecords() {
		return pagerecords;
	}

	public void setPagerecords(String pagerecords) {
		this.pagerecords = pagerecords;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	private int totalRecords;
	
	public ArrayList<DiaryManagement> getUserList() {
		return userList;
	}

	public void setUserList(ArrayList<DiaryManagement> userList) {
		this.userList = userList;
	}

	public ArrayList<Client> getNoActivityClientRecordList() {
		return noActivityClientRecordList;
	}

	public void setNoActivityClientRecordList(
			ArrayList<Client> noActivityClientRecordList) {
		this.noActivityClientRecordList = noActivityClientRecordList;
	}

	public ArrayList<Client> getDNANoFutureAppReport() {
		return DNANoFutureAppReport;
	}

	public void setDNANoFutureAppReport(ArrayList<Client> dNANoFutureAppReport) {
		DNANoFutureAppReport = dNANoFutureAppReport;
	}

	public ArrayList<Client> getNoActivityRecordList() {
		return noActivityRecordList;
	}

	public void setNoActivityRecordList(ArrayList<Client> noActivityRecordList) {
		this.noActivityRecordList = noActivityRecordList;
	}	

	public ArrayList<Client> getCurrentTreatmentNoFutureApmtsList() {
		return currentTreatmentNoFutureApmtsList;
	}

	public void setCurrentTreatmentNoFutureApmtsList(
			ArrayList<Client> currentTreatmentNoFutureApmtsList) {
		this.currentTreatmentNoFutureApmtsList = currentTreatmentNoFutureApmtsList;
	}

	public ArrayList<Client> getClientList() {
		return clientList;
	}

	public void setClientList(ArrayList<Client> clientList) {
		this.clientList = clientList;
	}

	public ArrayList<TreatmentType> getConditionList() {
		return conditionList;
	}

	public void setConditionList(ArrayList<TreatmentType> conditionList) {
		this.conditionList = conditionList;
	}

	public ArrayList<SummaryReport> getIpdConditionList() {
		return ipdConditionList;
	}

	public void setIpdConditionList(ArrayList<SummaryReport> ipdConditionList) {
		this.ipdConditionList = ipdConditionList;
	}

	public ArrayList<SummaryReport> getOpdConditionList() {
		return opdConditionList;
	}

	public void setOpdConditionList(ArrayList<SummaryReport> opdConditionList) {
		this.opdConditionList = opdConditionList;
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

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	private ArrayList<Product> productlist;
	private ArrayList<Master> departmentlist;
	private String department;
	private String product;
	private int jan1=0,feb1=0,march1=0,april1=0,may1=0,june1=0,jully1=0,aug1=0,sept1=0,oct1=0,nov1=0,dec1=0;
	private String jan,feb,mar,apr,may,jun,jul,aug,sep,oct,nov,dec;
	private double january=0,feburary=0,march=0,april=0,may2=0,juneee1=0,july1=0,august1=0,september=0,october=0,november=0,december=0;
	public double getJanuary() {
		return january;
	}

	public void setJanuary(double january) {
		this.january = january;
	}

	public double getFeburary() {
		return feburary;
	}

	public void setFeburary(double feburary) {
		this.feburary = feburary;
	}

	public double getMarch() {
		return march;
	}

	public void setMarch(double march) {
		this.march = march;
	}

	public double getApril() {
		return april;
	}

	public void setApril(double april) {
		this.april = april;
	}

	public double getMay2() {
		return may2;
	}

	public void setMay2(double may2) {
		this.may2 = may2;
	}

	public double getJuneee1() {
		return juneee1;
	}

	public void setJuneee1(double juneee1) {
		this.juneee1 = juneee1;
	}

	public double getJuly1() {
		return july1;
	}

	public void setJuly1(double july1) {
		this.july1 = july1;
	}

	public double getAugust1() {
		return august1;
	}

	public void setAugust1(double august1) {
		this.august1 = august1;
	}

	public double getSeptember() {
		return september;
	}

	public void setSeptember(double september) {
		this.september = september;
	}

	public double getOctober() {
		return october;
	}

	public void setOctober(double october) {
		this.october = october;
	}

	public double getNovember() {
		return november;
	}

	public void setNovember(double november) {
		this.november = november;
	}

	public double getDecember() {
		return december;
	}

	public void setDecember(double december) {
		this.december = december;
	}

	public String getJan() {
		return jan;
	}

	public void setJan(String jan) {
		this.jan = jan;
	}

	public String getFeb() {
		return feb;
	}

	public void setFeb(String feb) {
		this.feb = feb;
	}

	public String getMar() {
		return mar;
	}

	public void setMar(String mar) {
		this.mar = mar;
	}

	public String getApr() {
		return apr;
	}

	public void setApr(String apr) {
		this.apr = apr;
	}

	public String getMay() {
		return may;
	}

	public void setMay(String may) {
		this.may = may;
	}

	public String getJun() {
		return jun;
	}

	public void setJun(String jun) {
		this.jun = jun;
	}

	public String getJul() {
		return jul;
	}

	public void setJul(String jul) {
		this.jul = jul;
	}

	public String getAug() {
		return aug;
	}

	public void setAug(String aug) {
		this.aug = aug;
	}

	public String getSep() {
		return sep;
	}

	public void setSep(String sep) {
		this.sep = sep;
	}

	public String getOct() {
		return oct;
	}

	public void setOct(String oct) {
		this.oct = oct;
	}

	public String getNov() {
		return nov;
	}

	public void setNov(String nov) {
		this.nov = nov;
	}

	public String getDec() {
		return dec;
	}

	public void setDec(String dec) {
		this.dec = dec;
	}

	public int getJan1() {
		return jan1;
	}

	public void setJan1(int jan1) {
		this.jan1 = jan1;
	}

	public int getFeb1() {
		return feb1;
	}

	public void setFeb1(int feb1) {
		this.feb1 = feb1;
	}

	public int getMarch1() {
		return march1;
	}

	public void setMarch1(int march1) {
		this.march1 = march1;
	}

	public int getApril1() {
		return april1;
	}

	public void setApril1(int april1) {
		this.april1 = april1;
	}

	public int getMay1() {
		return may1;
	}

	public void setMay1(int may1) {
		this.may1 = may1;
	}

	public int getJune1() {
		return june1;
	}

	public void setJune1(int june1) {
		this.june1 = june1;
	}

	public int getJully1() {
		return jully1;
	}

	public void setJully1(int jully1) {
		this.jully1 = jully1;
	}

	public int getAug1() {
		return aug1;
	}

	public void setAug1(int aug1) {
		this.aug1 = aug1;
	}

	public int getSept1() {
		return sept1;
	}

	public void setSept1(int sept1) {
		this.sept1 = sept1;
	}

	public int getOct1() {
		return oct1;
	}

	public void setOct1(int oct1) {
		this.oct1 = oct1;
	}

	public int getNov1() {
		return nov1;
	}
	private String outsourcelab;
	public void setNov1(int nov1) {
		this.nov1 = nov1;
	}

	public int getDec1() {
		return dec1;
	}

	public void setDec1(int dec1) {
		this.dec1 = dec1;
	}
	public ArrayList<Master> getOutsrclablist() {
		return outsrclablist;
	}

	public void setOutsrclablist(ArrayList<Master> outsrclablist) {
		this.outsrclablist = outsrclablist;
	}

	public String getOutsourcelab() {
		return outsourcelab;
	}

	public void setOutsourcelab(String outsourcelab) {
		this.outsourcelab = outsourcelab;
	}

	public double getTotalofstock() {
		return totalofstock;
	}

	public void setTotalofstock(double totalofstock) {
		this.totalofstock = totalofstock;
	}

	public String getTtl() {
		return ttl;
	}

	public void setTtl(String ttl) {
		this.ttl = ttl;
	}

	public ArrayList<Vendor> getVendorlist() {
		return vendorlist;
	}

	public void setVendorlist(ArrayList<Vendor> vendorlist) {
		this.vendorlist = vendorlist;
	}

	public int getVendorid() {
		return vendorid;
	}

	public void setVendorid(int vendorid) {
		this.vendorid = vendorid;
	}

	
	public String getOrder_filter() {
		return order_filter;
	}

	public void setOrder_filter(String order_filter) {
		this.order_filter = order_filter;
	}

	public String getWarehousefilter() {
		return warehousefilter;
	}

	public void setWarehousefilter(String warehousefilter) {
		this.warehousefilter = warehousefilter;
	}


	public String getTotalcountpurprice() {
		return totalcountpurprice;
	}

	public void setTotalcountpurprice(String totalcountpurprice) {
		this.totalcountpurprice = totalcountpurprice;
	}

	public int getTotalcharge() {
		return totalcharge;
	}

	public void setTotalcharge(int totalcharge) {
		this.totalcharge = totalcharge;
	}

	public String getIpdopd() {
		return ipdopd;
	}

	public void setIpdopd(String ipdopd) {
		this.ipdopd = ipdopd;
	}

	public ArrayList<Product> getAdjustmentlist() {
		return adjustmentlist;
	}

	public void setAdjustmentlist(ArrayList<Product> adjustmentlist) {
		this.adjustmentlist = adjustmentlist;
	}

	public int getTotalpatient() {
		return totalpatient;
	}

	public void setTotalpatient(int totalpatient) {
		this.totalpatient = totalpatient;
	}

	public ArrayList<Product> getOpdtatreportlist() {
		return opdtatreportlist;
	}

	public void setOpdtatreportlist(ArrayList<Product> opdtatreportlist) {
		this.opdtatreportlist = opdtatreportlist;
	}

	private ArrayList<Master> outsrclablist;
	private double totalofstock;
	private String ttl;
	private ArrayList<Vendor> vendorlist;
	private int vendorid;
	private String order_filter;
	private String warehousefilter;
	private String totalcountpurprice;
	private int totalcharge;
	private String ipdopd;
	 private ArrayList<Product> adjustmentlist;
	 private int totalpatient;
	 private ArrayList<Product> opdtatreportlist;
	 private String invsttype;
	 private String filter_status;
	 private String filter_ward;
	 private String isdeleted;
	 private String patientname="";
	 private String mobno="";
	 ArrayList<Investigation> investigationtatlist;
	 ArrayList<Master>invsTypeList;
	 private ArrayList<Client> smsloglist;
	public ArrayList<Master> getInvsTypeList() {
		return invsTypeList;
	}

	public void setInvsTypeList(ArrayList<Master> invsTypeList) {
		this.invsTypeList = invsTypeList;
	}

	public ArrayList<Investigation> getInvestigationtatlist() {
		return investigationtatlist;
	}

	public void setInvestigationtatlist(ArrayList<Investigation> investigationtatlist) {
		this.investigationtatlist = investigationtatlist;
	}

	public String getInvsttype() {
		return invsttype;
	}

	public void setInvsttype(String invsttype) {
		this.invsttype = invsttype;
	}

	public String getFilter_status() {
		return filter_status;
	}

	public void setFilter_status(String filter_status) {
		this.filter_status = filter_status;
	}

	public String getFilter_ward() {
		return filter_ward;
	}

	public void setFilter_ward(String filter_ward) {
		this.filter_ward = filter_ward;
	}

	public String getIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(String isdeleted) {
		this.isdeleted = isdeleted;
	}
	public String getInvstName() {
		return invstName;
	}

	public void setInvstName(String invstName) {
		this.invstName = invstName;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public ArrayList<SummaryReport> getOtreportlist() {
		return otreportlist;
	}

	public void setOtreportlist(ArrayList<SummaryReport> otreportlist) {
		this.otreportlist = otreportlist;
	}

	public ArrayList<Master> getOtroomlist() {
		return otroomlist;
	}

	public void setOtroomlist(ArrayList<Master> otroomlist) {
		this.otroomlist = otroomlist;
	}

	public String getOtroom() {
		return otroom;
	}

	public void setOtroom(String otroom) {
		this.otroom = otroom;
	}


	public String getOtuser() {
		return otuser;
	}

	public void setOtuser(String otuser) {
		this.otuser = otuser;
	}

	public ArrayList<UserProfile> getSurgeonlist() {
		return surgeonlist;
	}

	public void setSurgeonlist(ArrayList<UserProfile> surgeonlist) {
		this.surgeonlist = surgeonlist;
	}

	public String getOtuser1() {
		return otuser1;
	}

	public void setOtuser1(String otuser1) {
		this.otuser1 = otuser1;
	}

	public ArrayList<SummaryReport> getNosofadmissiondischarge() {
		return nosofadmissiondischarge;
	}

	public void setNosofadmissiondischarge(ArrayList<SummaryReport> nosofadmissiondischarge) {
		this.nosofadmissiondischarge = nosofadmissiondischarge;
	}

	public ArrayList<SummaryReport> getIpdrevenuematrix() {
		return ipdrevenuematrix;
	}

	public void setIpdrevenuematrix(ArrayList<SummaryReport> ipdrevenuematrix) {
		this.ipdrevenuematrix = ipdrevenuematrix;
	}

	public ArrayList<Client> getAnesthesiaList() {
		return anesthesiaList;
	}

	public void setAnesthesiaList(ArrayList<Client> anesthesiaList) {
		this.anesthesiaList = anesthesiaList;
	}

	public String getAnesthesia() {
		return anesthesia;
	}

	public void setAnesthesia(String anesthesia) {
		this.anesthesia = anesthesia;
	}

	public ArrayList<Bed> getWardlist() {
		return wardlist;
	}

	public void setWardlist(ArrayList<Bed> wardlist) {
		this.wardlist = wardlist;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public ArrayList<DiaryManagement> getAllChargeWardlist() {
		return allChargeWardlist;
	}

	public void setAllChargeWardlist(ArrayList<DiaryManagement> allChargeWardlist) {
		this.allChargeWardlist = allChargeWardlist;
	}

	public String getYear1() {
		return year1;
	}

	public void setYear1(String year1) {
		this.year1 = year1;
	}

	private String invstName,clientId;
}
