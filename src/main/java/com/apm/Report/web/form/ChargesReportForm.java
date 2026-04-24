package com.apm.Report.web.form;

import java.util.ArrayList;
import java.util.Vector;

import com.apm.Accounts.eu.entity.Accounts;
import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.SittingFollowup;
import com.apm.Master.eu.entity.TreatmentType;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.Report.eu.entity.SummaryReport;
import com.apm.ThirdParties.eu.entity.ThirdParty;

public class ChargesReportForm {

	public ArrayList<Master> getMasterassesmentlist() {
		return masterassesmentlist;
	}

	public void setMasterassesmentlist(ArrayList<Master> masterassesmentlist) {
		this.masterassesmentlist = masterassesmentlist;
	}

	public String getOutsourcename() {
		return Outsourcename;
	}

	public void setOutsourcename(String outsourcename) {
		Outsourcename = outsourcename;
	}

	public ArrayList<DiaryManagement> getOutsourcelist() {
		return outsourcelist;
	}

	public void setOutsourcelist(ArrayList<DiaryManagement> outsourcelist) {
		this.outsourcelist = outsourcelist;
	}

	public ArrayList<Accounts> getChargesinvoicelist() {
		return chargesinvoicelist;
	}

	public void setChargesinvoicelist(ArrayList<Accounts> chargesinvoicelist) {
		this.chargesinvoicelist = chargesinvoicelist;
	}
	private String date;
	private String transactionType;
	private String payee;
	private String location;
	private String description;
	private String debit;
	private String credit;
	private String balance;
	private String chargeType;
	private String charge;
	private String qty;
	private ArrayList<Accounts> list;
	private double balanceTotal;
	private double debitTotal;
	private double creditTotal;
	private String userid;
	private String cashtotal="0";
	private String cheqtotal="0";
	private String crtotal="0";
	private String drtotal="0";
	private String nefttotal="0";
	private String othertotal="0";
	private String upitotal="0";
	private String actiontype = "0";
	private ArrayList<Accounts>maserUserList;
	private String sortfilter;
	private String totalamount;
	private double requestdiscamt =0;
	private double approvediscamt =0;
	private double invoicettotal =0;
	private double ipdref=0,opdref=0;
	private String registrationTotal;
	private ArrayList<Master> departmentList;
	private String departmentName;
	private ArrayList<Client> thirdPartyTypeNameList;
	private String patientCategory;
	private ArrayList<NotAvailableSlot> sittingAndFolloUpList;
	private String pagerecords;
	private int totalRecords;
	private String sittingOrFollowUp;
	private ArrayList<SittingFollowup> sittingList;
	private ArrayList<Master>procedureMasterList;
	private String sittingName;
	private String proceduerMasterName;
	private ArrayList<AppointmentType> procedureList;
	private String procedureName;
	private int procedurecount;
	private ArrayList<NotAvailableSlot> dailyopdcolorList;
    private ArrayList<String>hourList;
	private ArrayList<String>minuteList;
    private String hour;
    private String minute;
	private String fromhour;
    private Double totaldiscamt;
    private String ipdoropd;
    private double invref;
    private ArrayList<Master> masterassesmentlist;
    private String selectedpayer;
    private double totalbalance;
    
    public double getTotalbalance() {
		return totalbalance;
	}

	public void setTotalbalance(double totalbalance) {
		this.totalbalance = totalbalance;
	}

	public String getSelectedpayer() {
		return selectedpayer;
	}

	public void setSelectedpayer(String selectedpayer) {
		this.selectedpayer = selectedpayer;
	}
	
    public double getInvref() {
		return invref;
	}

	public void setInvref(double invref) {
		this.invref = invref;
	}

	public String getIpdoropd() {
		return ipdoropd;
	}

	public void setIpdoropd(String ipdoropd) {
		this.ipdoropd = ipdoropd;
	}
	public Double getTotaldiscamt() {
		return totaldiscamt;
	}

	public void setTotaldiscamt(Double totaldiscamt) {
		this.totaldiscamt = totaldiscamt;
	}

	public String getFromhour() {
		return fromhour;
	}

	public void setFromhour(String fromhour) {
		this.fromhour = fromhour;
	}

	public String getFromminute() {
		return fromminute;
	}

	public void setFromminute(String fromminute) {
		this.fromminute = fromminute;
	}
	private String fromminute;
	
	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getMinute() {
		return minute;
	}

	public void setMinute(String minute) {
		this.minute = minute;
	}

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

	public ArrayList<NotAvailableSlot> getDailyopdcolorList() {
		return dailyopdcolorList;
	}

	public void setDailyopdcolorList(ArrayList<NotAvailableSlot> dailyopdcolorList) {
		this.dailyopdcolorList = dailyopdcolorList;
	}

	public int getProcedurecount() {
		return procedurecount;
	}

	public void setProcedurecount(int procedurecount) {
		this.procedurecount = procedurecount;
	}

	public String getProcedureName() {
		return procedureName;
	}

	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}

	public ArrayList<AppointmentType> getProcedureList() {
		return procedureList;
	}

	public void setProcedureList(ArrayList<AppointmentType> procedureList) {
		this.procedureList = procedureList;
	}

	public String getProceduerMasterName() {
		return proceduerMasterName;
	}

	public void setProceduerMasterName(String proceduerMasterName) {
		this.proceduerMasterName = proceduerMasterName;
	}

	public String getSittingName() {
		return sittingName;
	}

	public void setSittingName(String sittingName) {
		this.sittingName = sittingName;
	}

	public ArrayList<Master> getProcedureMasterList() {
		return procedureMasterList;
	}

	public void setProcedureMasterList(ArrayList<Master> procedureMasterList) {
		this.procedureMasterList = procedureMasterList;
	}

	public ArrayList<SittingFollowup> getSittingList() {
		return sittingList;
	}

	public void setSittingList(ArrayList<SittingFollowup> sittingList) {
		this.sittingList = sittingList;
	}

	public String getSittingOrFollowUp() {
		return sittingOrFollowUp;
	}

	public void setSittingOrFollowUp(String sittingOrFollowUp) {
		this.sittingOrFollowUp = sittingOrFollowUp;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public String getPagerecords() {
		return pagerecords;
	}

	public void setPagerecords(String pagerecords) {
		this.pagerecords = pagerecords;
	}

	public ArrayList<NotAvailableSlot> getSittingAndFolloUpList() {
		return sittingAndFolloUpList;
	}

	public void setSittingAndFolloUpList(ArrayList<NotAvailableSlot> sittingAndFolloUpList) {
		this.sittingAndFolloUpList = sittingAndFolloUpList;
	}

	public String getPatientCategory() {
		return patientCategory;
	}

	public void setPatientCategory(String patientCategory) {
		this.patientCategory = patientCategory;
	}

	public ArrayList<Client> getThirdPartyTypeNameList() {
		return thirdPartyTypeNameList;
	}

	public void setThirdPartyTypeNameList(ArrayList<Client> thirdPartyTypeNameList) {
		this.thirdPartyTypeNameList = thirdPartyTypeNameList;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public ArrayList<Master> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(ArrayList<Master> departmentList) {
		this.departmentList = departmentList;
	}

	public String getRegistrationTotal() {
		return registrationTotal;
	}

	public void setRegistrationTotal(String registrationTotal) {
		this.registrationTotal = registrationTotal;
	}

	public double getIpdref() {
		return ipdref;
	}

	public void setIpdref(double ipdref) {
		this.ipdref = ipdref;
	}

	public double getOpdref() {
		return opdref;
	}

	public void setOpdref(double opdref) {
		this.opdref = opdref;
	}

	public double getInvoicettotal() {
		return invoicettotal;
	}

	public void setInvoicettotal(double invoicettotal) {
		this.invoicettotal = invoicettotal;
	}
	private ArrayList<NotAvailableSlot>distlist;
	
	
	
	 public ArrayList<NotAvailableSlot> getDistlist() {
		return distlist;
	}

	public void setDistlist(ArrayList<NotAvailableSlot> distlist) {
		this.distlist = distlist;
	}

	public double getRequestdiscamt() {
		return requestdiscamt;
	}

	public void setRequestdiscamt(double requestdiscamt) {
		this.requestdiscamt = requestdiscamt;
	}

	public double getApprovediscamt() {
		return approvediscamt;
	}

	public void setApprovediscamt(double approvediscamt) {
		this.approvediscamt = approvediscamt;
	}

	public String getSortfilter() {
		return sortfilter;
	}

	public void setSortfilter(String sortfilter) {
		this.sortfilter = sortfilter;
	}
	ArrayList<Client>conditionList;
	 private String total;
	 
	    public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}
		private ArrayList<String> jobTitleList;
	    
	    private String jobtitle;
	
	    private String searchuserid;
	    

	public String getSearchuserid() {
			return searchuserid;
		}

		public void setSearchuserid(String searchuserid) {
			this.searchuserid = searchuserid;
		}

	public ArrayList<String> getJobTitleList() {
			return jobTitleList;
		}

		public void setJobTitleList(ArrayList<String> jobTitleList) {
			this.jobTitleList = jobTitleList;
		}

		public String getJobtitle() {
			return jobtitle;
		}

		public void setJobtitle(String jobtitle) {
			this.jobtitle = jobtitle;
		}

	public ArrayList<Client> getConditionList() {
		return conditionList;
	}

	public void setConditionList(ArrayList<Client> conditionList) {
		this.conditionList = conditionList;
	}

	public ArrayList<Accounts> getMaserUserList() {
		return maserUserList;
	}

	public void setMaserUserList(ArrayList<Accounts> maserUserList) {
		this.maserUserList = maserUserList;
	}

	public String getActiontype() {
		return actiontype;
	}

	public void setActiontype(String actiontype) {
		this.actiontype = actiontype;
	}
	private String payby;
	private ArrayList<UserProfile> userProfileList;
	
	private ArrayList<Accounts>thirdPartyList;
	
	private String thirdParty;
	
	private String howpaid;
	
	private String totalref;
	private String calcdebittotal;
	
	private String paymentStatus;
	
	private String order = "desc"; 
	
	private String orderby = "";
	
	private String invoiceStatus = "";
	
	private String dtotal;
	private String btotal;
	private String ctotal;
	
	private String tpName;
	
	
	//daily report
	ArrayList<SummaryReport>rptouststatndingList;
	
	ArrayList<SummaryReport>newptsList;
	
	ArrayList<SummaryReport>dnaotsList;
	
	ArrayList<SummaryReport>totalPtsList;
	
	
	ArrayList<DiaryManagement>userList;
	
	ArrayList<Accounts>locationList;
	
	ArrayList<Client> treatmentTypeList;
	
	ArrayList<AppointmentType>additionalChargesList;
	
	private String diaryUser;
	
	private String condition;
	
	private String apmtType;
	
	private String totalPts;
	
	private String invoicecategory = "2";
	
	private String hdnprimaryinvoice;
	
	
	
	ArrayList<Master>invoiceTypeLis;
	
	private String invcetype = "0";
	
	ArrayList<Accounts>opdInvoiceList;
	ArrayList<Accounts> ipdInvoiceList;
	ArrayList<Accounts> invInvoiceList;
	ArrayList<Accounts> hdInvoiceList;
	ArrayList<Accounts> advanceInvoiceList;
	ArrayList<Accounts>refundPaymentList;
	
	private String selectedmodality = "0";
	
	private String opdtotal;
	private String ipdtotal;
	private String pathlabtotal;
	private String mdcinetotal;
	private String advreftotal;
	
	private ArrayList<Accounts> opdpaywiselist;
	private ArrayList<Accounts> ipdpaywiselist;
	private ArrayList<Accounts> pathlabpaywiselist;
	private ArrayList<Accounts> mdcinepaywiselist;
	private ArrayList<Accounts> advrefpaywiselist;
	private ArrayList<ThirdParty> thirdPartyListNew;
	private String tpid;
	
	public String getTpid() {
		return tpid;
	}

	public void setTpid(String tpid) {
		this.tpid = tpid;
	}

	public ArrayList<ThirdParty> getThirdPartyListNew() {
		return thirdPartyListNew;
	}

	public void setThirdPartyListNew(ArrayList<ThirdParty> thirdPartyListNew) {
		this.thirdPartyListNew = thirdPartyListNew;
	}

	public String getOpdtotal() {
		return opdtotal;
	}

	public void setOpdtotal(String opdtotal) {
		this.opdtotal = opdtotal;
	}

	public String getIpdtotal() {
		return ipdtotal;
	}

	public void setIpdtotal(String ipdtotal) {
		this.ipdtotal = ipdtotal;
	}

	public String getPathlabtotal() {
		return pathlabtotal;
	}

	public void setPathlabtotal(String pathlabtotal) {
		this.pathlabtotal = pathlabtotal;
	}

	public String getMdcinetotal() {
		return mdcinetotal;
	}

	public void setMdcinetotal(String mdcinetotal) {
		this.mdcinetotal = mdcinetotal;
	}

	public String getAdvreftotal() {
		return advreftotal;
	}

	public void setAdvreftotal(String advreftotal) {
		this.advreftotal = advreftotal;
	}

	public String getSelectedmodality() {
		return selectedmodality;
	}

	public void setSelectedmodality(String selectedmodality) {
		this.selectedmodality = selectedmodality;
	}

	public ArrayList<Accounts> getHdInvoiceList() {
		return hdInvoiceList;
	}

	public void setHdInvoiceList(ArrayList<Accounts> hdInvoiceList) {
		this.hdInvoiceList = hdInvoiceList;
	}

	public ArrayList<Accounts> getAdvanceInvoiceList() {
		return advanceInvoiceList;
	}

	public void setAdvanceInvoiceList(ArrayList<Accounts> advanceInvoiceList) {
		this.advanceInvoiceList = advanceInvoiceList;
	}

	public ArrayList<Accounts> getIpdInvoiceList() {
		return ipdInvoiceList;
	}

	public void setIpdInvoiceList(ArrayList<Accounts> ipdInvoiceList) {
		this.ipdInvoiceList = ipdInvoiceList;
	}

	public ArrayList<Accounts> getInvInvoiceList() {
		return invInvoiceList;
	}

	public void setInvInvoiceList(ArrayList<Accounts> invInvoiceList) {
		this.invInvoiceList = invInvoiceList;
	}

	public ArrayList<Accounts> getOpdInvoiceList() {
		return opdInvoiceList;
	}

	public void setOpdInvoiceList(ArrayList<Accounts> opdInvoiceList) {
		this.opdInvoiceList = opdInvoiceList;
	}

	public String getHdnprimaryinvoice() {
		return hdnprimaryinvoice;
	}

	public void setHdnprimaryinvoice(String hdnprimaryinvoice) {
		this.hdnprimaryinvoice = hdnprimaryinvoice;
	}

	public String getInvoicecategory() {
		return invoicecategory;
	}

	public void setInvoicecategory(String invoicecategory) {
		this.invoicecategory = invoicecategory;
	}

	public String getTotalPts() {
		return totalPts;
	}

	public void setTotalPts(String totalPts) {
		this.totalPts = totalPts;
	}

	public ArrayList<AppointmentType> getAdditionalChargesList() {
		return additionalChargesList;
	}

	public void setAdditionalChargesList(
			ArrayList<AppointmentType> additionalChargesList) {
		this.additionalChargesList = additionalChargesList;
	}

	public String getApmtType() {
		return apmtType;
	}

	public void setApmtType(String apmtType) {
		this.apmtType = apmtType;
	}

	public String getDiaryUser() {
		return diaryUser;
	}

	public void setDiaryUser(String diaryUser) {
		this.diaryUser = diaryUser;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public ArrayList<DiaryManagement> getUserList() {
		return userList;
	}

	public void setUserList(ArrayList<DiaryManagement> userList) {
		this.userList = userList;
	}

	public ArrayList<Accounts> getLocationList() {
		return locationList;
	}

	public void setLocationList(ArrayList<Accounts> locationList) {
		this.locationList = locationList;
	}

	public ArrayList<Client> getTreatmentTypeList() {
		return treatmentTypeList;
	}

	public void setTreatmentTypeList(ArrayList<Client> treatmentTypeList) {
		this.treatmentTypeList = treatmentTypeList;
	}

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	public ArrayList<SummaryReport> getTotalPtsList() {
		return totalPtsList;
	}

	public void setTotalPtsList(ArrayList<SummaryReport> totalPtsList) {
		this.totalPtsList = totalPtsList;
	}

	public ArrayList<SummaryReport> getDnaotsList() {
		return dnaotsList;
	}

	public void setDnaotsList(ArrayList<SummaryReport> dnaotsList) {
		this.dnaotsList = dnaotsList;
	}

	public ArrayList<SummaryReport> getNewptsList() {
		return newptsList;
	}

	public void setNewptsList(ArrayList<SummaryReport> newptsList) {
		this.newptsList = newptsList;
	}

	public ArrayList<SummaryReport> getRptouststatndingList() {
		return rptouststatndingList;
	}

	public void setRptouststatndingList(
			ArrayList<SummaryReport> rptouststatndingList) {
		this.rptouststatndingList = rptouststatndingList;
	}

	public String getTpName() {
		return tpName;
	}

	public void setTpName(String tpName) {
		this.tpName = tpName;
	}

	public String getDtotal() {
		return dtotal;
	}

	public void setDtotal(String dtotal) {
		this.dtotal = dtotal;
	}

	public String getBtotal() {
		return btotal;
	}

	public void setBtotal(String btotal) {
		this.btotal = btotal;
	}

	public String getCtotal() {
		return ctotal;
	}

	public void setCtotal(String ctotal) {
		this.ctotal = ctotal;
	}

	public String getInvoiceStatus() {
		return invoiceStatus;
	}

	public void setInvoiceStatus(String invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getHowpaid() {
		return howpaid;
	}

	public void setHowpaid(String howpaid) {
		this.howpaid = howpaid;
	}

	public String getThirdParty() {
		return thirdParty;
	}

	public void setThirdParty(String thirdParty) {
		this.thirdParty = thirdParty;
	}

	public ArrayList<Accounts> getThirdPartyList() {
		return thirdPartyList;
	}

	public void setThirdPartyList(ArrayList<Accounts> thirdPartyList) {
		this.thirdPartyList = thirdPartyList;
	}

	public String getPayby() {
		return payby;
	}

	public void setPayby(String payby) {
		this.payby = payby;
	}

	public String getDebitTotalx() {
		return debitTotalx;
	}

	public void setDebitTotalx(String debitTotalx) {
		this.debitTotalx = debitTotalx;
	}

	public String getCreditTotalx() {
		return creditTotalx;
	}

	public void setCreditTotalx(String creditTotalx) {
		this.creditTotalx = creditTotalx;
	}
	private ArrayList<Accounts> invoiceList;
	private ArrayList<Accounts> paymentList;
	private String fromDate = "";
	private String toDate = "";
	private String hiddenFromDate = "";
	private String hiddenToDate = "";
	
	private String balanceTotalx;
	private String debitTotalx;
	private String creditTotalx;
	private Vector<Accounts> paymentList1;
	
	public Vector<Accounts> getPaymentList1() {
		return paymentList1;
	}

	public void setPaymentList1(Vector<Accounts> paymentList1) {
		this.paymentList1 = paymentList1;
	}

	public String getBalanceTotalx() {
		return balanceTotalx;
	}

	public void setBalanceTotalx(String balanceTotalx) {
		this.balanceTotalx = balanceTotalx;
	}
	
	public String getHiddenFromDate() {
		return hiddenFromDate;
	}
	public void setHiddenFromDate(String hiddenFromDate) {
		this.hiddenFromDate = hiddenFromDate;
	}
	public String getHiddenToDate() {
		return hiddenToDate;
	}
	public void setHiddenToDate(String hiddenToDate) {
		this.hiddenToDate = hiddenToDate;
	}
	public ArrayList<Accounts> getPaymentList() {
		return paymentList;
	}
	public void setPaymentList(ArrayList<Accounts> paymentList) {
		this.paymentList = paymentList;
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
	
	
	
	
	public ArrayList<Accounts> getInvoiceList() {
		return invoiceList;
	}
	public void setInvoiceList(ArrayList<Accounts> invoiceList) {
		this.invoiceList = invoiceList;
	}
	public double getBalanceTotal() {
		return balanceTotal;
	}
	public void setBalanceTotal(double balanceTotal) {
		this.balanceTotal = balanceTotal;
	}
	public double getDebitTotal() {
		return debitTotal;
	}
	public void setDebitTotal(double debitTotal) {
		this.debitTotal = debitTotal;
	}
	public double getCreditTotal() {
		return creditTotal;
	}
	public void setCreditTotal(double creditTotal) {
		this.creditTotal = creditTotal;
	}
	public ArrayList<Accounts> getList() {
		return list;
	}
	public void setList(ArrayList<Accounts> list) {
		this.list = list;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getPayee() {
		return payee;
	}
	public void setPayee(String payee) {
		this.payee = payee;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDebit() {
		return debit;
	}
	public void setDebit(String debit) {
		this.debit = debit;
	}
	public String getCredit() {
		return credit;
	}
	public void setCredit(String credit) {
		this.credit = credit;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getChargeType() {
		return chargeType;
	}
	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}
	public String getCharge() {
		return charge;
	}
	public void setCharge(String charge) {
		this.charge = charge;
	}
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}

	public ArrayList<Master> getInvoiceTypeLis() {
		return invoiceTypeLis;
	}

	public void setInvoiceTypeLis(ArrayList<Master> invoiceTypeLis) {
		this.invoiceTypeLis = invoiceTypeLis;
	}

	public String getInvcetype() {
		return invcetype;
	}

	public void setInvcetype(String invcetype) {
		this.invcetype = invcetype;
	}

	public ArrayList<UserProfile> getUserProfileList() {
		return userProfileList;
	}

	public void setUserProfileList(ArrayList<UserProfile> userProfileList) {
		this.userProfileList = userProfileList;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public ArrayList<Accounts> getOpdpaywiselist() {
		return opdpaywiselist;
	}

	public void setOpdpaywiselist(ArrayList<Accounts> opdpaywiselist) {
		this.opdpaywiselist = opdpaywiselist;
	}

	public ArrayList<Accounts> getIpdpaywiselist() {
		return ipdpaywiselist;
	}

	public void setIpdpaywiselist(ArrayList<Accounts> ipdpaywiselist) {
		this.ipdpaywiselist = ipdpaywiselist;
	}

	public ArrayList<Accounts> getPathlabpaywiselist() {
		return pathlabpaywiselist;
	}

	public void setPathlabpaywiselist(ArrayList<Accounts> pathlabpaywiselist) {
		this.pathlabpaywiselist = pathlabpaywiselist;
	}

	public ArrayList<Accounts> getMdcinepaywiselist() {
		return mdcinepaywiselist;
	}

	public void setMdcinepaywiselist(ArrayList<Accounts> mdcinepaywiselist) {
		this.mdcinepaywiselist = mdcinepaywiselist;
	}

	public ArrayList<Accounts> getAdvrefpaywiselist() {
		return advrefpaywiselist;
	}

	public void setAdvrefpaywiselist(ArrayList<Accounts> advrefpaywiselist) {
		this.advrefpaywiselist = advrefpaywiselist;
	}

	public String getCashtotal() {
		return cashtotal;
	}

	public void setCashtotal(String cashtotal) {
		this.cashtotal = cashtotal;
	}

	public String getCheqtotal() {
		return cheqtotal;
	}

	public void setCheqtotal(String cheqtotal) {
		this.cheqtotal = cheqtotal;
	}

	public String getCrtotal() {
		return crtotal;
	}

	public void setCrtotal(String crtotal) {
		this.crtotal = crtotal;
	}

	public String getDrtotal() {
		return drtotal;
	}

	public void setDrtotal(String drtotal) {
		this.drtotal = drtotal;
	}

	public String getNefttotal() {
		return nefttotal;
	}

	public void setNefttotal(String nefttotal) {
		this.nefttotal = nefttotal;
	}

	public String getOthertotal() {
		return othertotal;
	}

	public void setOthertotal(String othertotal) {
		this.othertotal = othertotal;
	}

	public String getTotalref() {
		return totalref;
	}

	public void setTotalref(String totalref) {
		this.totalref = totalref;
	}

	public String getCalcdebittotal() {
		return calcdebittotal;
	}

	public void setCalcdebittotal(String calcdebittotal) {
		this.calcdebittotal = calcdebittotal;
	}

	public ArrayList<Accounts> getRefundPaymentList() {
		return refundPaymentList;
	}

	public void setRefundPaymentList(ArrayList<Accounts> refundPaymentList) {
		this.refundPaymentList = refundPaymentList;
	}
public String getItype() {
		return itype;
	}

	public void setItype(String itype) {
		this.itype = itype;
	}
public ArrayList<Master> getInvoicetypelist() {
		return invoicetypelist;
	}

	public void setInvoicetypelist(ArrayList<Master> invoicetypelist) {
		this.invoicetypelist = invoicetypelist;
	}
private String itype;
private ArrayList<Master> invoicetypelist;
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
private String netipddebitcount;
private String netopddebitcount;
private String netinvestigationdebitcount;
private String address;
private ArrayList<Accounts> chargesinvoicelist;
private Vector<Accounts> chargesinvoicelist1;

public Vector<Accounts> getChargesinvoicelist1() {
	return chargesinvoicelist1;
}

public void setChargesinvoicelist1(Vector<Accounts> chargesinvoicelist1) {
	this.chargesinvoicelist1 = chargesinvoicelist1;
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

public String getNetipddebitcount() {
	return netipddebitcount;
}

public void setNetipddebitcount(String netipddebitcount) {
	this.netipddebitcount = netipddebitcount;
}

public String getNetopddebitcount() {
	return netopddebitcount;
}

public void setNetopddebitcount(String netopddebitcount) {
	this.netopddebitcount = netopddebitcount;
}

public String getNetinvestigationdebitcount() {
	return netinvestigationdebitcount;
}

public void setNetinvestigationdebitcount(String netinvestigationdebitcount) {
	this.netinvestigationdebitcount = netinvestigationdebitcount;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

public ArrayList<AppointmentType> getApmtlist() {
	return apmtlist;
}

public void setApmtlist(ArrayList<AppointmentType> apmtlist) {
	this.apmtlist = apmtlist;
}
public ArrayList<DiaryManagement> getDoctorList() {
	return doctorList;
}

public void setDoctorList(ArrayList<DiaryManagement> doctorList) {
	this.doctorList = doctorList;
}
public ArrayList<Accounts> getPaymentrecivedList() {
	return paymentrecivedList;
}

public void setPaymentrecivedList(ArrayList<Accounts> paymentrecivedList) {
	this.paymentrecivedList = paymentrecivedList;
}
public String getTotalamount() {
	return totalamount;
}

public void setTotalamount(String totalamount) {
	this.totalamount = totalamount;
}
private ArrayList<AppointmentType> apmtlist;
private ArrayList<DiaryManagement> doctorList;
private ArrayList<Accounts> paymentrecivedList;
private int totalrefund,totaladvnce,totalipd;
private String ipdopdfilter;


public String getIpdopdfilter() {
	return ipdopdfilter;
}

public void setIpdopdfilter(String ipdopdfilter) {
	this.ipdopdfilter = ipdopdfilter;
}

public int getTotalrefund() {
	return totalrefund;
}

public void setTotalrefund(int totalrefund) {
	this.totalrefund = totalrefund;
}

public int getTotaladvnce() {
	return totaladvnce;
}

public void setTotaladvnce(int totaladvnce) {
	this.totaladvnce = totaladvnce;
}

public int getTotalipd() {
	return totalipd;
}

public void setTotalipd(int totalipd) {
	this.totalipd = totalipd;
}
public String getDoctor() {
	return doctor;
}

public void setDoctor(String doctor) {
	this.doctor = doctor;
}
public String getIpdopd() {
	return ipdopd;
}

public void setIpdopd(String ipdopd) {
	this.ipdopd = ipdopd;
}
public ArrayList<Client> getInvoicedclients() {
	return invoicedclients;
}

public String getClientid() {
	return clientid;
}

public void setClientid(String clientid) {
	this.clientid = clientid;
}

public void setInvoicedclients(ArrayList<Client> invoicedclients) {
	this.invoicedclients = invoicedclients;
}



public String getSearchinv() {
	return searchinv;
}

public void setSearchinv(String searchinv) {
	this.searchinv = searchinv;
}

public ArrayList<Accounts> getCreditBalanceReportList() {
	return creditBalanceReportList;
}

public void setCreditBalanceReportList(ArrayList<Accounts> creditBalanceReportList) {
	this.creditBalanceReportList = creditBalanceReportList;
}

public double getTotalcredit() {
	return totalcredit;
}

public void setTotalcredit(double totalcredit) {
	this.totalcredit = totalcredit;
}
public String getTotalcashcollect() {
	return totalcashcollect;
}

public void setTotalcashcollect(String totalcashcollect) {
	this.totalcashcollect = totalcashcollect;
}
public ArrayList<Accounts> getVaccinelist() {
	return vaccinelist;
}

public void setVaccinelist(ArrayList<Accounts> vaccinelist) {
	this.vaccinelist = vaccinelist;
}
public double getRefundtotal() {
	return refundtotal;
}

public void setRefundtotal(double refundtotal) {
	this.refundtotal = refundtotal;
}
public double getAdvtotal() {
	return advtotal;
}

public void setAdvtotal(double advtotal) {
	this.advtotal = advtotal;
}
public String getDaycaretotal() {
	return daycaretotal;
}

public void setDaycaretotal(String daycaretotal) {
	this.daycaretotal = daycaretotal;
}
public double getAppliedDisc() {
	return appliedDisc;
}

public void setAppliedDisc(double appliedDisc) {
	this.appliedDisc = appliedDisc;
}
public ArrayList<Accounts> getIpdRefundList() {
	return ipdRefundList;
}

public void setIpdRefundList(ArrayList<Accounts> ipdRefundList) {
	this.ipdRefundList = ipdRefundList;
}
public ArrayList<Accounts> getOpdRefundList() {
	return opdRefundList;
}

public void setOpdRefundList(ArrayList<Accounts> opdRefundList) {
	this.opdRefundList = opdRefundList;
}
public int getBghseqid() {
	return bghseqid;
}

public void setBghseqid(int bghseqid) {
	this.bghseqid = bghseqid;
}
public String getPaymentReportNote() {
	return paymentReportNote;
}

public void setPaymentReportNote(String paymentReportNote) {
	this.paymentReportNote = paymentReportNote;
}
public ArrayList<Master> getSmallPaymentReportListCollection() {
	return smallPaymentReportListCollection;
}

public void setSmallPaymentReportListCollection(ArrayList<Master> smallPaymentReportListCollection) {
	this.smallPaymentReportListCollection = smallPaymentReportListCollection;
}
private String doctor;
private String ipdopd;
private ArrayList<Client> invoicedclients;
private String clientid;
private String searchinv;
private ArrayList<Accounts> creditBalanceReportList;
private double totalcredit;
private String totalcashcollect;
private ArrayList<Accounts> vaccinelist;
private double refundtotal;
private double advtotal;
private String daycaretotal;
private double appliedDisc;
private ArrayList<Accounts> ipdRefundList;
private ArrayList<Accounts> opdRefundList;
private int bghseqid;
private String paymentReportNote;
private ArrayList<Master> smallPaymentReportListCollection;
private double advttl, refttl, ipdrefttl,opdrefttl;
private ArrayList<DiaryManagement> outsourcelist;
private String Outsourcename="";
private ArrayList<Accounts> ipdmainsmallPaymentReportList;
private ArrayList<Accounts> invRefundList;


public ArrayList<Accounts> getInvRefundList() {
	return invRefundList;
}

public void setInvRefundList(ArrayList<Accounts> invRefundList) {
	this.invRefundList = invRefundList;
}

public ArrayList<Accounts> getIpdmainsmallPaymentReportList() {
	return ipdmainsmallPaymentReportList;
}

public void setIpdmainsmallPaymentReportList(ArrayList<Accounts> ipdmainsmallPaymentReportList) {
	this.ipdmainsmallPaymentReportList = ipdmainsmallPaymentReportList;
}

public double getAdvttl() {
	return advttl;
}

public void setAdvttl(double advttl) {
	this.advttl = advttl;
}

public double getRefttl() {
	return refttl;
}

public void setRefttl(double refttl) {
	this.refttl = refttl;
}

public double getIpdrefttl() {
	return ipdrefttl;
}

public void setIpdrefttl(double ipdrefttl) {
	this.ipdrefttl = ipdrefttl;
}

public double getOpdrefttl() {
	return opdrefttl;
}

public void setOpdrefttl(double opdrefttl) {
	this.opdrefttl = opdrefttl;
}
private ArrayList<Accounts> cancelledInvoiceShifts;
private String cancelInvShiftTotal;

public ArrayList<Accounts> getCreditInvoiceReportList() {
	return creditInvoiceReportList;
}

public void setCreditInvoiceReportList(ArrayList<Accounts> creditInvoiceReportList) {
	this.creditInvoiceReportList = creditInvoiceReportList;
}
public double getTotalcr() {
	return totalcr;
}

public void setTotalcr(double totalcr) {
	this.totalcr = totalcr;
}
public ArrayList<Accounts> getCancelledInvoiceShifts() {
	return cancelledInvoiceShifts;
}

public void setCancelledInvoiceShifts(ArrayList<Accounts> cancelledInvoiceShifts) {
	this.cancelledInvoiceShifts = cancelledInvoiceShifts;
}
public String getCancelInvShiftTotal() {
	return cancelInvShiftTotal;
}

public void setCancelInvShiftTotal(String cancelInvShiftTotal) {
	this.cancelInvShiftTotal = cancelInvShiftTotal;
}
public String getUpitotal() {
	return upitotal;
}

public void setUpitotal(String upitotal) {
	this.upitotal = upitotal;
}
private ArrayList<Accounts> creditInvoiceReportList;
private double totalcr;
private ArrayList<Client> chargeTypeList;

public ArrayList<Client> getChargeTypeList() {
	return chargeTypeList;
}

public void setChargeTypeList(ArrayList<Client> chargeTypeList) {
	this.chargeTypeList = chargeTypeList;
}


}

