package com.apm.Accounts.web.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.apm.Accounts.eu.entity.Accounts;
import com.apm.Accounts.eu.entity.Invoice;
import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.DiaryManagement.eu.entity.CompleteAppointment;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.DiaryManagement.eu.entity.IpdFakeConsult;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Ipd.eu.entity.Ipd;
import com.apm.Master.eu.entity.Master;
import com.apm.Payroll.eu.entity.Salary;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.Tools.eu.entity.EmailTemplate;

public class AccountsForm {
	
	public String getSelectedinvcharge() {
		return selectedinvcharge;
	}

	public void setSelectedinvcharge(String selectedinvcharge) {
		this.selectedinvcharge = selectedinvcharge;
	}

	public String getMarkinvdate() {
		return markinvdate;
	}

	public void setMarkinvdate(String markinvdate) {
		this.markinvdate = markinvdate;
	}

	public ArrayList<Accounts> getProcedurelist() {
		return procedurelist;
	}

	public void setProcedurelist(ArrayList<Accounts> procedurelist) {
		this.procedurelist = procedurelist;
	}

	public String getNewadmissionid() {
		return newadmissionid;
	}

	public void setNewadmissionid(String newadmissionid) {
		this.newadmissionid = newadmissionid;
	}

	public String getOwner_name() {
		return owner_name;
	}

	public void setOwner_name(String owner_name) {
		this.owner_name = owner_name;
	}

	public String getSessioncount() {
		return sessioncount;
	}

	public void setSessioncount(String sessioncount) {
		this.sessioncount = sessioncount;
	}

	public String getExample() {
		return example;
	}

	public void setExample(String example) {
		this.example = example;
	}

	public ArrayList<Accounts> getDailynoteslist() {
		return dailynoteslist;
	}

	public void setDailynoteslist(ArrayList<Accounts> dailynoteslist) {
		this.dailynoteslist = dailynoteslist;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOutsourcename() {
		return outsourcename;
	}

	public void setOutsourcename(String outsourcename) {
		this.outsourcename = outsourcename;
	}

	public ArrayList<DiaryManagement> getOutsourcelist() {
		return outsourcelist;
	}

	public void setOutsourcelist(ArrayList<DiaryManagement> outsourcelist) {
		this.outsourcelist = outsourcelist;
	}

	public String getDiaryUser() {
		return diaryUser;
	}

	public void setDiaryUser(String diaryUser) {
		this.diaryUser = diaryUser;
	}

	public String getTokenno() {
		return tokenno;
	}

	public void setTokenno(String tokenno) {
		this.tokenno = tokenno;
	}

	public String getTransactionno() {
		return transactionno;
	}

	public void setTransactionno(String transactionno) {
		this.transactionno = transactionno;
	}

	private String invoice_date;
	private String clientId = "";
	
	private String client = "";
	
	private String payby = "";
	private Collection<Accounts> collectionsal;
	private int numberOfChages;
	private String unit;
	private String wardid;
	private String showcharges;
	private boolean refundstatus;
	private String unitcharges; 
	private String area;
	private String printedBy;
	private String actionType = "";
	private String autocharge="0";
	private String ipdadmissionid="0";
	private int advref = 0;
	private String dateTime="";
	private String tpname;
	private String middlename="";
	private String selectedInvoiceid = "";
    private String invoicenameid;
    private double totaldiscount;
    private int billno;
	private String invoicepayby;
	private String invoiceTime;
	private String creditCharge;
	private String tpid;
	private double payAmount;
	private String payAmountx;
	private List<Accounts>assesmentList;
	private ArrayList<Accounts>accountList;
	private int invoiceid;
	private String cashinvoiceid;
	private String invoiceDate;
	private String practitionerName;
	private String totalassesment;
	private String id;
	private String templateName;
	private String payeename;
	private String payeeadress;
	private ArrayList<AppointmentType> additionalChargesList;
	private String apmtType;
	private String raiseChargeType;
	private String apmtType1;
	private int apmtChargeType;
	private String payeeTown;
	private String payeePostcode;
	private String payeeEmail;
	private String payeeConatctNo;
	private String payee;
	private String policyExcess;
	private String totalExcludingPolicyExcess; 
	private String balanceExcludingPolicyExcess;
	private String creditDebitCharge;
	private String creditChargeId;
	private String hdfromDate;
	private String hdtoDate;
	private String chequeno;
	private String filter_status;
	private double newbalnace;
	private String paidamt;
	private String paymentdone;
	private boolean statusrequestdiscamt;
	private boolean inddiscsts;
	private double discountamt;
	private String countdata;
	private int non_approve_count;
	private int non_applied_count;
	private String description;
	private String example;
	private String sessioncount="0";
	private ArrayList<UserProfile> consultantdoclist;
	private ArrayList<Accounts>dailynoteslist;
	private String owner_name;
	private String ageonaddmission;
	private String newadmissionid;
	private String markinvdate;
	private ArrayList<Accounts> procedurelist;
	ArrayList<Master> discharge_default_list;
	private String disdefaulttempname;
	private String patientcategory;
	public String getPatientcategory() {
		return patientcategory;
	}

	public void setPatientcategory(String patientcategory) {
		this.patientcategory = patientcategory;
	}

	public String getDisdefaulttempname() {
		return disdefaulttempname;
	}

	public void setDisdefaulttempname(String disdefaulttempname) {
		this.disdefaulttempname = disdefaulttempname;
	}

	public ArrayList<Master> getDischarge_default_list() {
		return discharge_default_list;
	}

	public void setDischarge_default_list(ArrayList<Master> discharge_default_list) {
		this.discharge_default_list = discharge_default_list;
	}
	public String getAgeonaddmission() {
		return ageonaddmission;
	}

	public void setAgeonaddmission(String ageonaddmission) {
		this.ageonaddmission = ageonaddmission;
	}

	public ArrayList<UserProfile> getConsultantdoclist() {
		return consultantdoclist;
	}

	public void setConsultantdoclist(ArrayList<UserProfile> consultantdoclist) {
		this.consultantdoclist = consultantdoclist;
	}

	public int getDelete_count() {
		return delete_count;
	}

	public void setDelete_count(int delete_count) {
		this.delete_count = delete_count;
	}

	private int delete_count;
	private String fathername;
	private boolean ispreiousipdid;
	private String advancestatus;
	private ArrayList<Accounts> deleteinvoicelist;
	
	private String selectedCharges;
	private String selectedinvcharge;
	private String dayseqno;
    private String paymentLMHSequence;
	private String notes;
	private String department;
	private String gp_id;
	
	public String getGp_id() {
		return gp_id;
	}

	public void setGp_id(String gp_id) {
		this.gp_id = gp_id;
	}
	
	public String getNotes() {
		return notes;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	public String getPaymentLMHSequence() {
		return paymentLMHSequence;
	}

	public void setPaymentLMHSequence(String paymentLMHSequence) {
		this.paymentLMHSequence = paymentLMHSequence;
	}

	public String getDayseqno() {
		return dayseqno;
	}

	public void setDayseqno(String dayseqno) {
		this.dayseqno = dayseqno;
	}

	public String getSelectedCharges() {
		return selectedCharges;
	}

	public void setSelectedCharges(String selectedCharges) {
		this.selectedCharges = selectedCharges;
	}

	public boolean isIspreiousipdid() {
		return ispreiousipdid;
	}

	public void setIspreiousipdid(boolean ispreiousipdid) {
		this.ispreiousipdid = ispreiousipdid;
	}

	public String getFathername() {
		return fathername;
	}

	public void setFathername(String fathername) {
		this.fathername = fathername;
	}

	public int getNon_approve_count() {
		return non_approve_count;
	}

	public void setNon_approve_count(int non_approve_count) {
		this.non_approve_count = non_approve_count;
	}

	public int getNon_applied_count() {
		return non_applied_count;
	}

	public void setNon_applied_count(int non_applied_count) {
		this.non_applied_count = non_applied_count;
	}

	public String getCountdata() {
		return countdata;
	}

	public void setCountdata(String countdata) {
		this.countdata = countdata;
	}

	public double getDiscountamt() {
		return discountamt;
	}

	public void setDiscountamt(double discountamt) {
		this.discountamt = discountamt;
	}

	public boolean isStatusrequestdiscamt() {
		return statusrequestdiscamt;
	}

	public void setStatusrequestdiscamt(boolean statusrequestdiscamt) {
		this.statusrequestdiscamt = statusrequestdiscamt;
	}

	public String getPaidamt() {
		return paidamt;
	}

	public void setPaidamt(String paidamt) {
		this.paidamt = paidamt;
	}

	public String getPaymentdone() {
		return paymentdone;
	}

	public void setPaymentdone(String paymentdone) {
		this.paymentdone = paymentdone;
	}



	public double getNewbalnace() {
		return newbalnace;
	}



	public void setNewbalnace(double newbalnace) {
		this.newbalnace = newbalnace;
	}

	ArrayList<Accounts> allBillList;
	
	private ArrayList<Accounts>creditList;
	
	private String paymentNote = "";
	
	private String balanceAmount = "";
	
	private boolean balanceAmt;
	
	private String appointmentid = "";
	
	private String creditNote = "";
	
	private String crdAmount = "";
	
	private String message;
	
	private String clinicLogo;
	private String ipdabbrivationid;
	
	private String paymode;
	
	private ArrayList<Master>masterChageTypeList;
	
	private String masterchargetype;
	
	ArrayList<Invoice>caInvoiceList;
	
	private String invoicesearchid = "";
	
	private String invoicetype = "0";
	
	ArrayList<Master>masterAssessmentList;
	
	ArrayList<Master>tpMasterAssessmentList;
	
	public String getFilter_status() {
		return filter_status;
	}



	public void setFilter_status(String filter_status) {
		this.filter_status = filter_status;
	}



	public ArrayList<Master> getTpMasterAssessmentList() {
		return tpMasterAssessmentList;
	}



	public void setTpMasterAssessmentList(ArrayList<Master> tpMasterAssessmentList) {
		this.tpMasterAssessmentList = tpMasterAssessmentList;
	}

	private String preparedby;
	
	private String invoicepstype;
	
	private String invoicecategory = "0";
	
	private String clienttown;
	
	private String clientpostcode;
	
	private String dicsAmount;
	
	ArrayList<Master>invoiceTypeLis;
	
	private String invcetype;
	
	private String creditBalancex;
	
	private String totalpaymentx;
	
	private String invoicename;
	
	private String totalinword;
	
	private String netpayamount;
	
	private String disctype;
	
	private String clraradv = "";
	
	private String finalDiagnosis = "";
	
	private String abrivationid;
	
	private String sectionroom;
	private String cancelUserid;
	private String cancelDT;
	private String cancelNotes;
	private String deleted;
	
	private String pkgstr;
	private ArrayList<Master>pkgAssessmentList;
	
	
	
	ArrayList<NotAvailableSlot>otDetailsList;
	private String ipdseqno;
	private String opdid;
	private ArrayList<DiaryManagement> userList;
	private String doctorid;
	private String checkinvoice;
	private String manualinvoiceid;
	
	private String selfcredit;
	private String refundnote;
	
	private String refundtype;
	private String approvedrefund;
	private String fromdate;
	private String todate;
	private ArrayList<CompleteAppointment> parentrefundrequestlist;
	private ArrayList<CompleteAppointment> clientChargeListDetail;
	private String chargeTotalx;
	private String refundrequestid;
	
	private String hdnmorehowpaid = "0";
	private String hdnmorepaudamount = "0";
	private ArrayList<Accounts> refundList;
	private ArrayList<Master>ledgerservicesList;
	
	private String ledgerservicestr = "";
	
	ArrayList<Master>bankNameList;
	private String bnkname;
	private String hdnbnkname;
	
	private String status = "1";
	
	private String refundtotalx;
	private String action;
	private String billsummary;
	private String isforrefund;
	private double totalrefund;
	private String isparkingcharge;
	
	private String pkgtotal;
	private String tpstotal;
	private String refundinvoiceid;
	private String searchtext;
	
	public String getSearchtext() {
		return searchtext;
	}



	public void setSearchtext(String searchtext) {
		this.searchtext = searchtext;
	}



	public String getRefundinvoiceid() {
		return refundinvoiceid;
	}



	public void setRefundinvoiceid(String refundinvoiceid) {
		this.refundinvoiceid = refundinvoiceid;
	}



	public String getPkgtotal() {
		return pkgtotal;
	}



	public void setPkgtotal(String pkgtotal) {
		this.pkgtotal = pkgtotal;
	}



	public String getTpstotal() {
		return tpstotal;
	}



	public void setTpstotal(String tpstotal) {
		this.tpstotal = tpstotal;
	}



	public String getPkgstr() {
		return pkgstr;
	}



	public void setPkgstr(String pkgstr) {
		this.pkgstr = pkgstr;
	}



	public ArrayList<Master> getPkgAssessmentList() {
		return pkgAssessmentList;
	}



	public void setPkgAssessmentList(ArrayList<Master> pkgAssessmentList) {
		this.pkgAssessmentList = pkgAssessmentList;
	}



	public String getIsparkingcharge() {
		return isparkingcharge;
	}



	public void setIsparkingcharge(String isparkingcharge) {
		this.isparkingcharge = isparkingcharge;
	}



	public double getTotalrefund() {
		return totalrefund;
	}



	public void setTotalrefund(double totalrefund) {
		this.totalrefund = totalrefund;
	}



	public String getIsforrefund() {
		return isforrefund;
	}



	public void setIsforrefund(String isforrefund) {
		this.isforrefund = isforrefund;
	}



	public String getBillsummary() {
		return billsummary;
	}



	public void setBillsummary(String billsummary) {
		this.billsummary = billsummary;
	}



	public String getAction() {
		return action;
	}



	public void setAction(String action) {
		this.action = action;
	}



	public String getRefundtotalx() {
		return refundtotalx;
	}



	public void setRefundtotalx(String refundtotalx) {
		this.refundtotalx = refundtotalx;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public String getHdnbnkname() {
		return hdnbnkname;
	}



	public void setHdnbnkname(String hdnbnkname) {
		this.hdnbnkname = hdnbnkname;
	}



	public ArrayList<Master> getBankNameList() {
		return bankNameList;
	}



	public void setBankNameList(ArrayList<Master> bankNameList) {
		this.bankNameList = bankNameList;
	}



	public String getBnkname() {
		return bnkname;
	}



	public void setBnkname(String bnkname) {
		this.bnkname = bnkname;
	}

	private ArrayList<Accounts> discountgivenuserlist;
	private String dischargestatus;
	
	public String getDischargestatus() {
		return dischargestatus;
	}



	public void setDischargestatus(String dischargestatus) {
		this.dischargestatus = dischargestatus;
	}



	public ArrayList<Accounts> getDiscountgivenuserlist() {
		return discountgivenuserlist;
	}



	public void setDiscountgivenuserlist(ArrayList<Accounts> discountgivenuserlist) {
		this.discountgivenuserlist = discountgivenuserlist;
	}



	public String getLedgerservicestr() {
		return ledgerservicestr;
	}



	public void setLedgerservicestr(String ledgerservicestr) {
		this.ledgerservicestr = ledgerservicestr;
	}



	public ArrayList<Master> getLedgerservicesList() {
		return ledgerservicesList;
	}



	public void setLedgerservicesList(ArrayList<Master> ledgerservicesList) {
		this.ledgerservicesList = ledgerservicesList;
	}



	public ArrayList<Accounts> getRefundList() {
		return refundList;
	}



	public void setRefundList(ArrayList<Accounts> refundList) {
		this.refundList = refundList;
	}



	public String getHdnmorehowpaid() {
		return hdnmorehowpaid;
	}



	public void setHdnmorehowpaid(String hdnmorehowpaid) {
		this.hdnmorehowpaid = hdnmorehowpaid;
	}



	public String getHdnmorepaudamount() {
		return hdnmorepaudamount;
	}



	public void setHdnmorepaudamount(String hdnmorepaudamount) {
		this.hdnmorepaudamount = hdnmorepaudamount;
	}
	
	public String getRefundrequestid() {
		return refundrequestid;
	}



	public void setRefundrequestid(String refundrequestid) {
		this.refundrequestid = refundrequestid;
	}



	public String getChargeTotalx() {
		return chargeTotalx;
	}



	public void setChargeTotalx(String chargeTotalx) {
		this.chargeTotalx = chargeTotalx;
	}



	public ArrayList<CompleteAppointment> getClientChargeListDetail() {
		return clientChargeListDetail;
	}



	public void setClientChargeListDetail(ArrayList<CompleteAppointment> clientChargeListDetail) {
		this.clientChargeListDetail = clientChargeListDetail;
	}



	public ArrayList<CompleteAppointment> getParentrefundrequestlist() {
		return parentrefundrequestlist;
	}



	public void setParentrefundrequestlist(ArrayList<CompleteAppointment> parentrefundrequestlist) {
		this.parentrefundrequestlist = parentrefundrequestlist;
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



	public String getApprovedrefund() {
		return approvedrefund;
	}



	public void setApprovedrefund(String approvedrefund) {
		this.approvedrefund = approvedrefund;
	}



	public String getRefundtype() {
		return refundtype;
	}



	public void setRefundtype(String refundtype) {
		this.refundtype = refundtype;
	}



	public String getRefundnote() {
		return refundnote;
	}



	public void setRefundnote(String refundnote) {
		this.refundnote = refundnote;
	}



	public String getSelfcredit() {
		return selfcredit;
	}



	public void setSelfcredit(String selfcredit) {
		this.selfcredit = selfcredit;
	}



	public String getManualinvoiceid() {
		return manualinvoiceid;
	}



	public void setManualinvoiceid(String manualinvoiceid) {
		this.manualinvoiceid = manualinvoiceid;
	}



	public String getCheckinvoice() {
		return checkinvoice;
	}



	public void setCheckinvoice(String checkinvoice) {
		this.checkinvoice = checkinvoice;
	}



	public String getDoctorid() {
		return doctorid;
	}



	public void setDoctorid(String doctorid) {
		this.doctorid = doctorid;
	}



	public ArrayList<DiaryManagement> getUserList() {
		return userList;
	}



	public void setUserList(ArrayList<DiaryManagement> userList) {
		this.userList = userList;
	}



	public String getOpdid() {
		return opdid;
	}



	public String getCashinvoiceid() {
		return cashinvoiceid;
	}



	public void setCashinvoiceid(String cashinvoiceid) {
		this.cashinvoiceid = cashinvoiceid;
	}



	public void setOpdid(String opdid) {
		this.opdid = opdid;
	}



	public String getIpdseqno() {
		return ipdseqno;
	}



	public void setIpdseqno(String ipdseqno) {
		this.ipdseqno = ipdseqno;
	}



	public ArrayList<NotAvailableSlot> getOtDetailsList() {
		return otDetailsList;
	}



	public void setOtDetailsList(ArrayList<NotAvailableSlot> otDetailsList) {
		this.otDetailsList = otDetailsList;
	}



	public ArrayList<Master> getDoctorlist() {
		return doctorlist;
	}



	public void setDoctorlist(ArrayList<Master> doctorlist) {
		this.doctorlist = doctorlist;
	}

	private ArrayList<Master> doctorlist;
	public String getCancelUserid() {
		return cancelUserid;
	}



	public void setCancelUserid(String cancelUserid) {
		this.cancelUserid = cancelUserid;
	}



	public String getCancelDT() {
		return cancelDT;
	}



	public void setCancelDT(String cancelDT) {
		this.cancelDT = cancelDT;
	}



	public String getCancelNotes() {
		return cancelNotes;
	}



	public void setCancelNotes(String cancelNotes) {
		this.cancelNotes = cancelNotes;
	}



	public String getDeleted() {
		return deleted;
	}



	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}



	public String getSectionroom() {
		return sectionroom;
	}



	public void setSectionroom(String sectionroom) {
		this.sectionroom = sectionroom;
	}



	public String getAbrivationid() {
		return abrivationid;
	}



	public void setAbrivationid(String abrivationid) {
		this.abrivationid = abrivationid;
	}



	public String getFinalDiagnosis() {
		return finalDiagnosis;
	}



	public void setFinalDiagnosis(String finalDiagnosis) {
		this.finalDiagnosis = finalDiagnosis;
	}



	public String getClraradv() {
		return clraradv;
	}



	public void setClraradv(String clraradv) {
		this.clraradv = clraradv;
	}



	public String getDisctype() {
		return disctype;
	}



	public void setDisctype(String disctype) {
		this.disctype = disctype;
	}



	public String getNetpayamount() {
		return netpayamount;
	}



	public void setNetpayamount(String netpayamount) {
		this.netpayamount = netpayamount;
	}



	public String getTotalinword() {
		return totalinword;
	}



	public void setTotalinword(String totalinword) {
		this.totalinword = totalinword;
	}



	public String getInvoicename() {
		return invoicename;
	}



	public void setInvoicename(String invoicename) {
		this.invoicename = invoicename;
	}



	public String getInvoicenameid() {
		return invoicenameid;
	}

	
	
	public void setInvoicenameid(String invoicenameid) {
		this.invoicenameid = invoicenameid;
	}

	public String getTotalpaymentx() {
		return totalpaymentx;
	}

	public void setTotalpaymentx(String totalpaymentx) {
		this.totalpaymentx = totalpaymentx;
	}

	public String getCreditBalancex() {
		return creditBalancex;
	}

	public void setCreditBalancex(String creditBalancex) {
		this.creditBalancex = creditBalancex;
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

	public String getDicsAmount() {
		return dicsAmount;
	}

	public void setDicsAmount(String dicsAmount) {
		this.dicsAmount = dicsAmount;
	}

	public String getClienttown() {
		return clienttown;
	}

	public void setClienttown(String clienttown) {
		this.clienttown = clienttown;
	}

	public String getClientpostcode() {
		return clientpostcode;
	}

	public void setClientpostcode(String clientpostcode) {
		this.clientpostcode = clientpostcode;
	}

	public String getInvoicecategory() {
		return invoicecategory;
	}

	public void setInvoicecategory(String invoicecategory) {
		this.invoicecategory = invoicecategory;
	}

	public String getInvoicepstype() {
		return invoicepstype;
	}

	public void setInvoicepstype(String invoicepstype) {
		this.invoicepstype = invoicepstype;
	}

	public String getPreparedby() {
		return preparedby;
	}

	public void setPreparedby(String preparedby) {
		this.preparedby = preparedby;
	}

	public ArrayList<Master> getMasterAssessmentList() {
		return masterAssessmentList;
	}

	public void setMasterAssessmentList(ArrayList<Master> masterAssessmentList) {
		this.masterAssessmentList = masterAssessmentList;
	}

	public String getInvoicesearchid() {
		return invoicesearchid;
	}

	public void setInvoicesearchid(String invoicesearchid) {
		this.invoicesearchid = invoicesearchid;
	}

	public String getInvoicetype() {
		return invoicetype;
	}

	public void setInvoicetype(String invoicetype) {
		this.invoicetype = invoicetype;
	}

	public ArrayList<Invoice> getCaInvoiceList() {
		return caInvoiceList;
	}

	public void setCaInvoiceList(ArrayList<Invoice> caInvoiceList) {
		this.caInvoiceList = caInvoiceList;
	}

	public int getAdvref() {
		return advref;
	}

	public void setAdvref(int advref) {
		this.advref = advref;
	}

	public ArrayList<Master> getMasterChageTypeList() {
		return masterChageTypeList;
	}

	public void setMasterChageTypeList(ArrayList<Master> masterChageTypeList) {
		this.masterChageTypeList = masterChageTypeList;
	}

	public String getMasterchargetype() {
		return masterchargetype;
	}

	public void setMasterchargetype(String masterchargetype) {
		this.masterchargetype = masterchargetype;
	}

	public String getPaymode() {
		return paymode;
	}

	public void setPaymode(String paymode) {
		this.paymode = paymode;
	}

	public String getClinicLogo() {
		return clinicLogo;
	}

	public void setClinicLogo(String clinicLogo) {
		this.clinicLogo = clinicLogo;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCreditNote() {
		return creditNote;
	}

	public void setCreditNote(String creditNote) {
		this.creditNote = creditNote;
	}

	public String getCrdAmount() {
		return crdAmount;
	}

	public void setCrdAmount(String crdAmount) {
		this.crdAmount = crdAmount;
	}

	public String getSelectedInvoiceid() {
		return selectedInvoiceid;
	}

	public void setSelectedInvoiceid(String selectedInvoiceid) {
		this.selectedInvoiceid = selectedInvoiceid;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getAppointmentid() {
		return appointmentid;
	}

	public void setAppointmentid(String appointmentid) {
		this.appointmentid = appointmentid;
	}

	public boolean isBalanceAmt() {
		return balanceAmt;
	}

	public void setBalanceAmt(boolean balanceAmt) {
		this.balanceAmt = balanceAmt;
	}

	public String getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(String balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public String getPaymentNote() {
		return paymentNote;
	}

	public void setPaymentNote(String paymentNote) {
		this.paymentNote = paymentNote;
	}

	public ArrayList<Accounts> getCreditList() {
		return creditList;
	}

	public void setCreditList(ArrayList<Accounts> creditList) {
		this.creditList = creditList;
	}

	public String getCreditChargeId() {
		return creditChargeId;
	}

	public void setCreditChargeId(String creditChargeId) {
		this.creditChargeId = creditChargeId;
	}

	public String getCreditDebitCharge() {
		return creditDebitCharge;
	}

	public void setCreditDebitCharge(String creditDebitCharge) {
		this.creditDebitCharge = creditDebitCharge;
	}

	private int policyexcesscode = 0;
	private ArrayList<Clinic> locationAdressList;
	
	
	public ArrayList<Clinic> getLocationAdressList() {
		return locationAdressList;
	}

	public void setLocationAdressList(ArrayList<Clinic> locationAdressList) {
		this.locationAdressList = locationAdressList;
	}

	public int getPolicyexcesscode() {
		return policyexcesscode;
	}

	public void setPolicyexcesscode(int policyexcesscode) {
		this.policyexcesscode = policyexcesscode;
	}

	public String getBalanceExcludingPolicyExcess() {
		return balanceExcludingPolicyExcess;
	}

	public void setBalanceExcludingPolicyExcess(String balanceExcludingPolicyExcess) {
		this.balanceExcludingPolicyExcess = balanceExcludingPolicyExcess;
	}

	public String getTotalExcludingPolicyExcess() {
		return totalExcludingPolicyExcess;
	}

	public void setTotalExcludingPolicyExcess(String totalExcludingPolicyExcess) {
		this.totalExcludingPolicyExcess = totalExcludingPolicyExcess;
	}

	public String getPolicyExcess() {
		return policyExcess;
	}

	public void setPolicyExcess(String policyExcess) {
		this.policyExcess = policyExcess;
	}

	public String getPayeeTown() {
		return payeeTown;
	}

	public void setPayeeTown(String payeeTown) {
		this.payeeTown = payeeTown;
	}

	public String getPayeePostcode() {
		return payeePostcode;
	}

	public void setPayeePostcode(String payeePostcode) {
		this.payeePostcode = payeePostcode;
	}

	public String getPayeeEmail() {
		return payeeEmail;
	}

	public void setPayeeEmail(String payeeEmail) {
		this.payeeEmail = payeeEmail;
	}

	public String getPayeeConatctNo() {
		return payeeConatctNo;
	}

	public void setPayeeConatctNo(String payeeConatctNo) {
		this.payeeConatctNo = payeeConatctNo;
	}

	public int getApmtChargeType() {
		return apmtChargeType;
	}

	public void setApmtChargeType(int apmtChargeType) {
		this.apmtChargeType = apmtChargeType;
	}

	public String getApmtType1() {
		return apmtType1;
	}

	public void setApmtType1(String apmtType1) {
		this.apmtType1 = apmtType1;
	}

	public String getRaiseChargeType() {
		return raiseChargeType = "";
	}

	public void setRaiseChargeType(String raiseChargeType) {
		this.raiseChargeType = raiseChargeType;
	}

	public String getApmtType() {
		return apmtType;
	}

	public void setApmtType(String apmtType) {
		this.apmtType = apmtType;
	}

	public ArrayList<AppointmentType> getAdditionalChargesList() {
		return additionalChargesList;
	}

	public void setAdditionalChargesList(
			ArrayList<AppointmentType> additionalChargesList) {
		this.additionalChargesList = additionalChargesList;
	}

	public String getPayeename() {
		return payeename;
	}

	public void setPayeename(String payeename) {
		this.payeename = payeename;
	}

	public String getPayeeadress() {
		return payeeadress;
	}

	public void setPayeeadress(String payeeadress) {
		this.payeeadress = payeeadress;
	}

	
	
	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	private ArrayList<EmailTemplate> templateNameList;	

	public ArrayList<EmailTemplate> getTemplateNameList() {
		return templateNameList;
	}

	public void setTemplateNameList(ArrayList<EmailTemplate> templateNameList) {
		this.templateNameList = templateNameList;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	private String debitAmounnt;
	;
	private String insuranceCompany;
	
	private String hdnSelectedID;
	
	private String discount = "0";
	
	private boolean hdnSelectAll;
	
	private String mobno;
	
	private String policyNo;
	private String thirdPartyContacttno;
	private String thirdPartyPostcode;
	private String thirdPartyemail;
	private String thirdPartyAddress;

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	private String postcode;
	
	public String getThirdPartyAddress() {
		return thirdPartyAddress;
	}

	public void setThirdPartyAddress(String thirdPartyAddress) {
		this.thirdPartyAddress = thirdPartyAddress;
	}

	public String getMobno() {
		return mobno;
	}

	public void setMobno(String mobno) {
		this.mobno = mobno;
	}

	private String howpaid;
	
	private String email;
	private String filename;
	
	private ArrayList<Invoice>invoiceList;
	
	
	
	public String getThirdPartyContacttno() {
		return thirdPartyContacttno;
	}

	public void setThirdPartyContacttno(String thirdPartyContacttno) {
		this.thirdPartyContacttno = thirdPartyContacttno;
	}

	public String getThirdPartyPostcode() {
		return thirdPartyPostcode;
	}

	public void setThirdPartyPostcode(String thirdPartyPostcode) {
		this.thirdPartyPostcode = thirdPartyPostcode;
	}

	public String getThirdPartyemail() {
		return thirdPartyemail;
	}

	public void setThirdPartyemail(String thirdPartyemail) {
		this.thirdPartyemail = thirdPartyemail;
	}

	public ArrayList<Invoice> getInvoiceList() {
		return invoiceList;
	}

	public void setInvoiceList(ArrayList<Invoice> invoiceList) {
		this.invoiceList = invoiceList;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	private String totalamount;
	
	private String dob;
	
	private String  admissionDate;
	
	private String dischargeDate;
	
	private String agegender;
	
	private int ipdid;
	
	private String ipdconsultant;
	
	ArrayList<Accounts>prepaymentList;
	

	public String getIpdconsultant() {
		return ipdconsultant;
	}

	public void setIpdconsultant(String ipdconsultant) {
		this.ipdconsultant = ipdconsultant;
	}

	public int getIpdid() {
		return ipdid;
	}

	public void setIpdid(int ipdid) {
		this.ipdid = ipdid;
	}

	public String getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(String admissionDate) {
		this.admissionDate = admissionDate;
	}

	public String getDischargeDate() {
		return dischargeDate;
	}

	public void setDischargeDate(String dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	public String getAgegender() {
		return agegender;
	}

	public void setAgegender(String agegender) {
		this.agegender = agegender;
	}

	public String getHowpaid() {
		return howpaid;
	}

	public void setHowpaid(String howpaid) {
		this.howpaid = howpaid;
	}

	public String getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(String totalamount) {
		this.totalamount = totalamount;
	}

	public boolean isHdnSelectAll() {
		return hdnSelectAll;
	}

	public void setHdnSelectAll(boolean hdnSelectAll) {
		this.hdnSelectAll = hdnSelectAll;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	private String invoiceNo;
	private double creditTotal;
	private double debitTotal;
	private double balanceTotal;
	private String creditTotalx;
	private String debitTotalx;
	private String balanceTotalx;
	private ArrayList<Accounts> clientDetailsList;
	private String initial;
	private String firstname;
	private String lastname;
	private String address;
	private String city;
	private String transactionType = "";
	private String location = "";
	private String thirdParty = "";
	private String submitInvoiceNotes;
	
	private String selectedPayBy;
	
	private String selectedLocation;
	
	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	private String locationName;
	
	
	public String getSelectedLocation() {
		return selectedLocation;
	}

	public void setSelectedLocation(String selectedLocation) {
		this.selectedLocation = selectedLocation;
	}

	public String getSelectedPayBy() {
		return selectedPayBy;
	}

	public void setSelectedPayBy(String selectedPayBy) {
		this.selectedPayBy = selectedPayBy;
	}

	public String getSubmitInvoiceNotes() {
		return submitInvoiceNotes;
	}

	public void setSubmitInvoiceNotes(String submitInvoiceNotes) {
		this.submitInvoiceNotes = submitInvoiceNotes;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getThirdParty() {
		return thirdParty;
	}

	public void setThirdParty(String thirdParty) {
		this.thirdParty = thirdParty;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	private ArrayList<Accounts>locationList;
	private ArrayList<Accounts>thirdPartyList;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public ArrayList<Accounts> getClientDetailsList() {
		return clientDetailsList;
	}

	public void setClientDetailsList(ArrayList<Accounts> clientDetailsList) {
		this.clientDetailsList = clientDetailsList;
	}

	public String getInitial() {
		return initial;
	}

	public void setInitial(String initial) {
		this.initial = initial;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getCreditTotal() {
		return creditTotal;
	}

	public void setCreditTotal(double creditTotal) {
		this.creditTotal = creditTotal;
	}

	public double getDebitTotal() {
		return debitTotal;
	}

	public void setDebitTotal(double debitTotal) {
		this.debitTotal = debitTotal;
	}

	public double getBalanceTotal() {
		return balanceTotal;
	}

	public void setBalanceTotal(double balanceTotal) {
		this.balanceTotal = balanceTotal;
	}

	private ArrayList<Accounts>transactionList ;
	public ArrayList<Accounts> getTransactionList() {
		return transactionList;
	}

	public void setTransactionList(ArrayList<Accounts> transactionList) {
		this.transactionList = transactionList;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPaymentmode() {
		return paymentmode;
	}

	public void setPaymentmode(String paymentmode) {
		this.paymentmode = paymentmode;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	private String date;
	private String paymentmode;
	private String amount;
	private String amountx;
	
	public String getHdnSelectedID() {
		return hdnSelectedID;
	}

	public void setHdnSelectedID(String hdnSelectedID) {
		this.hdnSelectedID = hdnSelectedID;
	}

	private String creditAmt;
	private String pagerecords;
	private int totalRecords;
	private String clientId1;
	private String client1;
	private double totalAmount;
	private String totalAmountx;
	private String commencing;
	private double balance;
	private String dchtotal;
	
	ArrayList<CompleteAppointment> parentdiscountrequestlist;
	
	public ArrayList<CompleteAppointment> getParentdiscountrequestlist() {
		return parentdiscountrequestlist;
	}



	public void setParentdiscountrequestlist(ArrayList<CompleteAppointment> parentdiscountrequestlist) {
		this.parentdiscountrequestlist = parentdiscountrequestlist;
	}



	public String getDchtotal() {
		return dchtotal;
	}



	public void setDchtotal(String dchtotal) {
		this.dchtotal = dchtotal;
	}



	public String getPayAmountx() {
		return payAmountx;
	}

	public void setPayAmountx(String payAmountx) {
		this.payAmountx = payAmountx;
	}

	public String getCreditTotalx() {
		return creditTotalx;
	}

	public void setCreditTotalx(String creditTotalx) {
		this.creditTotalx = creditTotalx;
	}

	public String getDebitTotalx() {
		return debitTotalx;
	}

	public void setDebitTotalx(String debitTotalx) {
		this.debitTotalx = debitTotalx;
	}

	public String getBalanceTotalx() {
		return balanceTotalx;
	}

	public void setBalanceTotalx(String balanceTotalx) {
		this.balanceTotalx = balanceTotalx;
	}

	public String getAmountx() {
		return amountx;
	}

	public void setAmountx(String amountx) {
		this.amountx = amountx;
	}

	public String getTotalAmountx() {
		return totalAmountx;
	}

	public void setTotalAmountx(String totalAmountx) {
		this.totalAmountx = totalAmountx;
	}

	public String getBalancex() {
		return balancex;
	}

	public void setBalancex(String balancex) {
		this.balancex = balancex;
	}

	private String balancex;
	private String fromDate = "";
	private String toDate = "";
	private ArrayList<Accounts>chargeProcessingList;
	public ArrayList<Accounts> getChargeProcessingList() {
		return chargeProcessingList;
	}

	public void setChargeProcessingList(ArrayList<Accounts> chargeProcessingList) {
		this.chargeProcessingList = chargeProcessingList;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
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

	public String getCommencing() {
		return commencing;
	}

	public void setCommencing(String commencing) {
		this.commencing = commencing;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getClient1() {
		return client1;
	}

	public void setClient1(String client1) {
		this.client1 = client1;
	}

	public String getClientId1() {
		return clientId1;
	}

	public void setClientId1(String clientId1) {
		this.clientId1 = clientId1;
	}

	public String getPayby1() {
		return payby1;
	}

	public void setPayby1(String payby1) {
		this.payby1 = payby1;
	}

	private String payby1;
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

	public String getCreditAmt() {
		return creditAmt;
	}

	public void setCreditAmt(String creditAmt) {
		this.creditAmt = creditAmt;
	}

	public String getDebitAmt() {
		return debitAmt;
	}

	public void setDebitAmt(String debitAmt) {
		this.debitAmt = debitAmt;
	}

	private String debitAmt;
	
	

	public String getDebitAmounnt() {
		return debitAmounnt;
	}

	public void setDebitAmounnt(String debitAmounnt) {
		this.debitAmounnt = debitAmounnt;
	}

	public String getTotalassesment() {
		return totalassesment;
	}

	public void setTotalassesment(String totalassesment) {
		this.totalassesment = totalassesment;
	}

	public String getPractitionerName() {
		return practitionerName;
	}

	public void setPractitionerName(String practitionerName) {
		this.practitionerName = practitionerName;
	}

	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public int getInvoiceid() {
		return invoiceid;
	}

	public void setInvoiceid(int invoiceid) {
		this.invoiceid = invoiceid;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getPayby() {
		return payby;
	}

	public void setPayby(String payby) {
		this.payby = payby;
	}

	public ArrayList<Accounts> getAccountList() {
		return accountList;
	}

	public void setAccountList(ArrayList<Accounts> accountList) {
		this.accountList = accountList;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public int getNumberOfChages() {
		return numberOfChages;
	}

	public void setNumberOfChages(int numberOfChages) {
		this.numberOfChages = numberOfChages;
	}

	public String getCreditCharge() {
		return creditCharge;
	}

	public void setCreditCharge(String creditCharge) {
		this.creditCharge = creditCharge;
	}

	public double getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(double payAmount) {
		this.payAmount = payAmount;
	}

	public List<Accounts> getAssesmentList() {
		return assesmentList;
	}

	public void setAssesmentList(List<Accounts> assesmentList) {
		this.assesmentList = assesmentList;
	}

	public String getInsuranceCompany() {
		return insuranceCompany;
	}

	public void setInsuranceCompany(String insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}
	
	private String clinicName;
	private String clinicOwner;
	private String clinicemail;
	private String mobileNo;
	private String landLine;
	private String country;
	private String clinicity;
	private String clinicaddress;
	private String pinCode;
	private int locationid;
	private String websiteUrl;
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

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getLandLine() {
		return landLine;
	}

	public void setLandLine(String landLine) {
		this.landLine = landLine;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getClinicity() {
		return clinicity;
	}

	public void setClinicity(String clinicity) {
		this.clinicity = clinicity;
	}

	public String getClinicaddress() {
		return clinicaddress;
	}

	public void setClinicaddress(String clinicaddress) {
		this.clinicaddress = clinicaddress;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public int getLocationid() {
		return locationid;
	}

	public void setLocationid(int locationid) {
		this.locationid = locationid;
	}

	public String getWebsiteUrl() {
		return websiteUrl;
	}

	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}

	public String getOwner_qualification() {
		return owner_qualification;
	}

	public void setOwner_qualification(String owner_qualification) {
		this.owner_qualification = owner_qualification;
	}

	private String owner_qualification;


	public String getHdfromDate() {
		return hdfromDate;
	}



	public void setHdfromDate(String hdfromDate) {
		this.hdfromDate = hdfromDate;
	}



	public String getHdtoDate() {
		return hdtoDate;
	}



	public void setHdtoDate(String hdtoDate) {
		this.hdtoDate = hdtoDate;
	}



	public ArrayList<Accounts> getAllBillList() {
		return allBillList;
	}



	public void setAllBillList(ArrayList<Accounts> allBillList) {
		this.allBillList = allBillList;
	}



	public int getBillno() {
		return billno;
	}



	public void setBillno(int billno) {
		this.billno = billno;
	}



	public String getInvoicepayby() {
		return invoicepayby;
	}



	public void setInvoicepayby(String invoicepayby) {
		this.invoicepayby = invoicepayby;
	}



	public String getTpname() {
		return tpname;
	}



	public String getTpid() {
		return tpid;
	}



	public void setTpid(String tpid) {
		this.tpid = tpid;
	}



	public void setTpname(String tpname) {
		this.tpname = tpname;
	}



	public String getPayee() {
		return payee;
	}



	public void setPayee(String payee) {
		this.payee = payee;
	}



	public String getChequeno() {
		return chequeno;
	}



	public void setChequeno(String chequeno) {
		this.chequeno = chequeno;
	}



	public String getIpdadmissionid() {
		return ipdadmissionid;
	}



	public void setIpdadmissionid(String ipdadmissionid) {
		this.ipdadmissionid = ipdadmissionid;
	}



	public String getUnit() {
		return unit;
	}



	public void setUnit(String unit) {
		this.unit = unit;
	}



	public String getArea() {
		return area;
	}



	public void setArea(String area) {
		this.area = area;
	}



	public ArrayList<Accounts> getPrepaymentList() {
		return prepaymentList;
	}



	public void setPrepaymentList(ArrayList<Accounts> prepaymentList) {
		this.prepaymentList = prepaymentList;
	}



	public String getMiddlename() {
		return middlename;
	}



	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}



	public String getPrintedBy() {
		return printedBy;
	}



	public void setPrintedBy(String printedBy) {
		this.printedBy = printedBy;
	}



	public String getDateTime() {
		return dateTime;
	}



	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}



	public String getInvoice_date() {
		return invoice_date;
	}

	public void setInvoice_date(String invoice_date) {
		this.invoice_date = invoice_date;
	}



	public String getAutocharge() {
		return autocharge;
	}



	public void setAutocharge(String autocharge) {
		this.autocharge = autocharge;
	}



	public String getInvoiceTime() {
		return invoiceTime;
	}



	public void setInvoiceTime(String invoiceTime) {
		this.invoiceTime = invoiceTime;
	}
	
	
	public String getRefereddr() {
		return refereddr;
	}



	public void setRefereddr(String refereddr) {
		this.refereddr = refereddr;
	}

	public String getIpdopdseqno() {
		return ipdopdseqno;
	}



	public void setIpdopdseqno(String ipdopdseqno) {
		this.ipdopdseqno = ipdopdseqno;
	}

	public boolean isDiscstatus1() {
		return discstatus1;
	}



	public void setDiscstatus1(boolean discstatus1) {
		this.discstatus1 = discstatus1;
	}

	public String getWardname() {
		return wardname;
	}



	public void setWardname(String wardname) {
		this.wardname = wardname;
	}

	public String getBedname() {
		return bedname;
	}



	public void setBedname(String bedname) {
		this.bedname = bedname;
	}

	public ArrayList<Bed> getWardlist() {
		return wardlist;
	}



	public void setWardlist(ArrayList<Bed> wardlist) {
		this.wardlist = wardlist;
	}

	public ArrayList<Ipd> getIpdseqlist() {
		return ipdseqlist;
	}



	public void setIpdseqlist(ArrayList<Ipd> ipdseqlist) {
		this.ipdseqlist = ipdseqlist;
	}

	public String getIpdidnew() {
		return ipdidnew;
	}



	public void setIpdidnew(String ipdidnew) {
		this.ipdidnew = ipdidnew;
	}

	public String getAdvrefsrno() {
		return advrefsrno;
	}



	public void setAdvrefsrno(String advrefsrno) {
		this.advrefsrno = advrefsrno;
	}

	private  String refereddr;
	private String ipdopdseqno;
	private boolean discstatus1;
	private String wardname;
	private String bedname;
	private ArrayList<Bed> wardlist;
	private ArrayList<Ipd> ipdseqlist;
	private String ipdidnew;
	private String advrefsrno;
	
	
	
	private String payedbytp;
	private String neiscardno;
	private String companyname;
	private String designation;
	private String relationofuser;
	private String unit_station;
	private String claimid;
	private String colliery;
	private String areatp;
	private String employeenamebytp;


	public String getEmployeenamebytp() {
		return employeenamebytp;
	}



	public void setEmployeenamebytp(String employeenamebytp) {
		this.employeenamebytp = employeenamebytp;
	}



	public String getPayedbytp() {
		return payedbytp;
	}



	public void setPayedbytp(String payedbytp) {
		this.payedbytp = payedbytp;
	}



	public String getNeiscardno() {
		return neiscardno;
	}



	public void setNeiscardno(String neiscardno) {
		this.neiscardno = neiscardno;
	}



	public String getCompanyname() {
		return companyname;
	}



	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}



	public String getDesignation() {
		return designation;
	}



	public void setDesignation(String designation) {
		this.designation = designation;
	}



	public String getRelationofuser() {
		return relationofuser;
	}



	public void setRelationofuser(String relationofuser) {
		this.relationofuser = relationofuser;
	}



	public String getUnit_station() {
		return unit_station;
	}



	public void setUnit_station(String unit_station) {
		this.unit_station = unit_station;
	}



	public String getClaimid() {
		return claimid;
	}



	public void setClaimid(String claimid) {
		this.claimid = claimid;
	}



	public String getColliery() {
		return colliery;
	}



	public void setColliery(String colliery) {
		this.colliery = colliery;
	}



	public String getAreatp() {
		return areatp;
	}



	public void setAreatp(String areatp) {
		this.areatp = areatp;
	}
	
	
	
	public boolean isStatusfortp() {
		return statusfortp;
	}



	public void setStatusfortp(boolean statusfortp) {
		this.statusfortp = statusfortp;
	}

	
	
	public String getPolicyholder() {
		return policyholder;
	}



	public void setPolicyholder(String policyholder) {
		this.policyholder = policyholder;
	}

	
	public String getAutoselect() {
		return autoselect;
	}



	public void setAutoselect(String autoselect) {
		this.autoselect = autoselect;
	}

	public String getAutoselect1() {
		return autoselect1;
	}



	public void setAutoselect1(String autoselect1) {
		this.autoselect1 = autoselect1;
	}

	public String getNewipdabbr() {
		return newipdabbr;
	}



	public void setNewipdabbr(String newipdabbr) {
		this.newipdabbr = newipdabbr;
	}

	public String getUserid() {
		return userid;
	}



	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getDrqualification() {
		return drqualification;
	}



	public void setDrqualification(String drqualification) {
		this.drqualification = drqualification;
	}

	public String getLastipdid() {
		return lastipdid;
	}



	public void setLastipdid(String lastipdid) {
		this.lastipdid = lastipdid;
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

	public ArrayList<Master> getPackageList() {
		return packageList;
	}



	public void setPackageList(ArrayList<Master> packageList) {
		this.packageList = packageList;
	}
	public String getPkgfromdate() {
		return pkgfromdate;
	}



	public void setPkgfromdate(String pkgfromdate) {
		this.pkgfromdate = pkgfromdate;
	}



	public String getPkgtodate() {
		return pkgtodate;
	}



	public void setPkgtodate(String pkgtodate) {
		this.pkgtodate = pkgtodate;
	}
	
	
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

	public ArrayList<Master> getPatientPackageList() {
		return patientPackageList;
	}



	public void setPatientPackageList(ArrayList<Master> patientPackageList) {
		this.patientPackageList = patientPackageList;
	}

	public ArrayList<Accounts> getNewdisclist() {
		return newdisclist;
	}



	public void setNewdisclist(ArrayList<Accounts> newdisclist) {
		this.newdisclist = newdisclist;
	}

	public double getPkgamttot() {
		return pkgamttot;
	}



	public void setPkgamttot(double pkgamttot) {
		this.pkgamttot = pkgamttot;
	}

	public double getSubpkgamttot() {
		return subpkgamttot;
	}



	public void setSubpkgamttot(double subpkgamttot) {
		this.subpkgamttot = subpkgamttot;
	}

	public String getDischargehead() {
		return dischargehead;
	}



	public void setDischargehead(String dischargehead) {
		this.dischargehead = dischargehead;
	}

	public String getPhysical_paymentid() {
		return physical_paymentid;
	}



	public void setPhysical_paymentid(String physical_paymentid) {
		this.physical_paymentid = physical_paymentid;
	}

	public boolean isInvoicecreated() {
		return invoicecreated;
	}



	public void setInvoicecreated(boolean invoicecreated) {
		this.invoicecreated = invoicecreated;
	}

	public ArrayList<Master> getTaxlist() {
		return taxlist;
	}



	public void setTaxlist(ArrayList<Master> taxlist) {
		this.taxlist = taxlist;
	}

	public boolean isInvdtsts() {
		return invdtsts;
	}



	public void setInvdtsts(boolean invdtsts) {
		this.invdtsts = invdtsts;
	}

	public String getKuanlInvSeq() {
		return kuanlInvSeq;
	}



	public void setKuanlInvSeq(String kuanlInvSeq) {
		this.kuanlInvSeq = kuanlInvSeq;
	}

	public String getSelectipdid() {
		return selectipdid;
	}



	public void setSelectipdid(String selectipdid) {
		this.selectipdid = selectipdid;
	}

	private String hour;
	    
	    private String minute;
	private String pkgfromdate;
	private String pkgtodate;
	private boolean statusfortp;
	private String policyholder;
	private String autoselect;
	private String autoselect1;
	private String newipdabbr;
	private String userid;
	private String drqualification;
	private String lastipdid;
private ArrayList<String>hourList;
    private ArrayList<String>minuteList;
    private ArrayList<Master>packageList;
    private ArrayList<Master>patientPackageList ;
    private ArrayList<Accounts> newdisclist;
    private double pkgamttot;
    private double subpkgamttot;
    private String dischargehead; 
    private String mothername; 
    private String physical_paymentid;
    private boolean invoicecreated;
    private ArrayList<Master> taxlist;
    private boolean invdtsts;
    private String kuanlInvSeq;
    private String selectipdid;
    private String chargeid;
    private double taxtype1,taxtype2,taxtype3;
private String tax1,tax2,tax3;
private String height,weight,bmi,headcir,tempr;
	public String getHeight() {
	return height;
}



public void setHeight(String height) {
	this.height = height;
}



public String getWeight() {
	return weight;
}



public void setWeight(String weight) {
	this.weight = weight;
}



public String getBmi() {
	return bmi;
}



public void setBmi(String bmi) {
	this.bmi = bmi;
}



public String getHeadcir() {
	return headcir;
}



public void setHeadcir(String headcir) {
	this.headcir = headcir;
}



public String getTempr() {
	return tempr;
}



public void setTempr(String tempr) {
	this.tempr = tempr;
}



	public String getTax1() {
	return tax1;
}



public void setTax1(String tax1) {
	this.tax1 = tax1;
}



public String getTax2() {
	return tax2;
}



public void setTax2(String tax2) {
	this.tax2 = tax2;
}



public String getTax3() {
	return tax3;
}



public void setTax3(String tax3) {
	this.tax3 = tax3;
}



	public double getTaxtype1() {
		return taxtype1;
	}



	public void setTaxtype1(double taxtype1) {
		this.taxtype1 = taxtype1;
	}



	public double getTaxtype2() {
		return taxtype2;
	}



	public void setTaxtype2(double taxtype2) {
		this.taxtype2 = taxtype2;
	}



	public double getTaxtype3() {
		return taxtype3;
	}



	public void setTaxtype3(double taxtype3) {
		this.taxtype3 = taxtype3;
	}
	
	public boolean isDaycare() {
		return daycare;
	}



	public void setDaycare(boolean daycare) {
		this.daycare = daycare;
	}

	public String getShowcharges() {
		return showcharges;
	}

	public void setShowcharges(String showcharges) {
		this.showcharges = showcharges;
	}

	public Collection<Accounts> getCollectionsal() {
		return collectionsal;
	}

	public void setCollectionsal(Collection<Accounts> collectionsal) {
		this.collectionsal = collectionsal;
	}

	public String getChargeid() {
		return chargeid;
	}

	public void setChargeid(String chargeid) {
		this.chargeid = chargeid;
	}

	public String getMothername() {
		return mothername;
	}

	public void setMothername(String mothername) {
		this.mothername = mothername;
	}

	public String getIpdabbrivationid() {
		return ipdabbrivationid;
	}

	public void setIpdabbrivationid(String ipdabbrivationid) {
		this.ipdabbrivationid = ipdabbrivationid;
	}
	public String getAmtWithouttax() {
		return amtWithouttax;
	}


	public void setAmtWithouttax(String amtWithouttax) {
		this.amtWithouttax = amtWithouttax;
	}

	private String amtWithouttax;

	public String getWardid() {
		return wardid;
	}

	public void setWardid(String wardid) {
		this.wardid = wardid;
	}

	public String getUnitcharges() {
		return unitcharges;
	}

	public void setUnitcharges(String unitcharges) {
		this.unitcharges = unitcharges;
	}

	public boolean isInddiscsts() {
		return inddiscsts;
	}

	public void setInddiscsts(boolean inddiscsts) {
		this.inddiscsts = inddiscsts;
	}

	public double getTotaldiscount() {
		return totaldiscount;
	}

	public void setTotaldiscount(double totaldiscount) {
		this.totaldiscount = totaldiscount;
	}

	public String getInvpaid() {
		return invpaid;
	}

	public void setInvpaid(String invpaid) {
		this.invpaid = invpaid;
	}

	public String getAdvancestatus() {
		return advancestatus;
	}

	public void setAdvancestatus(String advancestatus) {
		this.advancestatus = advancestatus;
	}

	public String getColorcode() {
		return colorcode;
	}

	public void setColorcode(String colorcode) {
		this.colorcode = colorcode;
	}

	public boolean isRefundstatus() {
		return refundstatus;
	}

	public void setRefundstatus(boolean refundstatus) {
		this.refundstatus = refundstatus;
	}

	public String getRefpayMode() {
		return refpayMode;
	}

	public void setRefpayMode(String refpayMode) {
		this.refpayMode = refpayMode;
	}

	public ArrayList<Accounts> getDeleteinvoicelist() {
		return deleteinvoicelist;
	}

	public void setDeleteinvoicelist(ArrayList<Accounts> deleteinvoicelist) {
		this.deleteinvoicelist = deleteinvoicelist;
	}

	private boolean daycare;
	private String invpaid;
	private String colorcode;
	private String refpayMode;
	private String mobile;
	private String transactionno;
	private ArrayList<DiaryManagement> outsourcelist;
	private String outsourcename;
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	private String tokenno;
	private String diaryUser;
}
