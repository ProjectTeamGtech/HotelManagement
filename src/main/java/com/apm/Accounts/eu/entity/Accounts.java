package com.apm.Accounts.eu.entity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;

import com.apm.Emr.eu.entity.Investigation;
import com.apm.Master.eu.entity.Master;

public class Accounts implements Comparable<Accounts>{
	public String getProcedure_name() {
		return procedure_name;
	}

	public void setProcedure_name(String procedure_name) {
		this.procedure_name = procedure_name;
	}

	public String getProcedureid() {
		return procedureid;
	}

	public void setProcedureid(String procedureid) {
		this.procedureid = procedureid;
	}

	public ArrayList<Master> getAssesmentlist() {
		return assesmentlist;
	}

	public void setAssesmentlist(ArrayList<Master> assesmentlist) {
		this.assesmentlist = assesmentlist;
	}

	public ArrayList<Accounts> getMasterassesmentList() {
		return masterassesmentList;
	}

	public void setMasterassesmentList(ArrayList<Accounts> masterassesmentList) {
		this.masterassesmentList = masterassesmentList;
	}

	public Vector<Accounts> getAssesmentList() {
		return assesmentList;
	}

	public void setAssesmentList(Vector<Accounts> assesmentList) {
		this.assesmentList = assesmentList;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getCancelinv() {
		return cancelinv;
	}

	public void setCancelinv(int cancelinv) {
		this.cancelinv = cancelinv;
	}

	public double getOriginal_pay() {
		return original_pay;
	}

	public void setOriginal_pay(double original_pay) {
		this.original_pay = original_pay;
	}

	public String getApmt_id() {
		return apmt_id;
	}

	public void setApmt_id(String apmt_id) {
		this.apmt_id = apmt_id;
	}

	public String getOutsource() {
		return outsource;
	}

	public void setOutsource(String outsource) {
		this.outsource = outsource;
	}

	public String getPaymentdate() {
		return paymentdate;
	}

	public void setPaymentdate(String paymentdate) {
		this.paymentdate = paymentdate;
	}

	public double getAdvance() {
		return advance;
	}

	public void setAdvance(double advance) {
		this.advance = advance;
	}

	private String patientno;
	private String packagename;
	private String totalpayment;
	private String paymodecash;
	private String showdate;
	private String upitotal;
private String totalReceived;
private String totalamt;
private String ledgername;
private String invpstype;
private int todyasadm;
private int todaydis;
private String cancelsts;
private String receiptid;
private String newreceiptid;
private String wardid;
private String iclosed;
private String ipost;
private String status;
private int count;
private String totaldebitAmount;
private String unitcharges; 
private String credurAmountx;
private String dbalx;
private String cbalx;
private String dbaltotalx;
private String cbaltotalx;
String ltype;
private String refundAmountx;
private String manualcharge;
private String manualinvoiceid;
private String invoicetype;
private String advno;
private String inddiscounttotal;
private String chargeofqty = "0";
private String unitchargeofqty = "0";
private String invoicedate;
private String invoiceids;
private String apmt_id;
private int additionaldiscallow;
private ArrayList<Accounts>prepaymentList;
private ArrayList<Accounts> refundList;
private ArrayList<Accounts>transactionList;
private Vector<Accounts>assesmentList;
private double newdiscount;
private int creditid ;
private String physicalid;
private String ipdabrivationid;
private String registrationTotal;
private String payer;

private String netPayableAmt;
private String invAmt;
private String discAmt;
private String preAmt;
private String balAmt;

private int predayornot,cancelinv;
private String dayseqno;
private String chargename;
private String docid;
private String date_time;
private String paymentdate;
private String outsource,code;
private double original_pay;
private int totalsession;
private int session;
private String procedureid,procedure_name;
private ArrayList<Accounts> ipdsmallPaymentReportListCollection;
private ArrayList<Accounts> ipdsmallPaymentinnerlist;
private ArrayList<Accounts>masterassesmentList;
private ArrayList<Master> assesmentlist;
private String clientids;
private String deptid;
private Long diff;
private double netadvance;
private ArrayList<Accounts> discountchargereportlist;
private double billdiscamount;
public double getBilldiscamount() {
	return billdiscamount;
}

public void setBilldiscamount(double billdiscamount) {
	this.billdiscamount = billdiscamount;
}

public ArrayList<Accounts> getDiscountchargereportlist() {
	return discountchargereportlist;
}

public void setDiscountchargereportlist(ArrayList<Accounts> discountchargereportlist) {
	this.discountchargereportlist = discountchargereportlist;
}

public double getNetadvance() {
	return netadvance;
}

public void setNetadvance(double netadvance) {
	this.netadvance = netadvance;
}

public Long getDiff() {
	return diff;
}

public void setDiff(Long diff) {
	this.diff = diff;
}

public String getDeptid() {
	return deptid;
}

public void setDeptid(String deptid) {
	this.deptid = deptid;
}

public String getClientids() {
	return clientids;
}

public void setClientids(String clientids) {
	this.clientids = clientids;
}

public ArrayList<Accounts> getIpdsmallPaymentinnerlist() {
	return ipdsmallPaymentinnerlist;
}

public void setIpdsmallPaymentinnerlist(ArrayList<Accounts> ipdsmallPaymentinnerlist) {
	this.ipdsmallPaymentinnerlist = ipdsmallPaymentinnerlist;
}

public ArrayList<Accounts> getIpdsmallPaymentReportListCollection() {
	return ipdsmallPaymentReportListCollection;
}

public void setIpdsmallPaymentReportListCollection(ArrayList<Accounts> ipdsmallPaymentReportListCollection) {
	this.ipdsmallPaymentReportListCollection = ipdsmallPaymentReportListCollection;
}

public int getTotalsession() {
	return totalsession;
}

public void setTotalsession(int totalsession) {
	this.totalsession = totalsession;
}

public int getSession() {
	return session;
}

public void setSession(int session) {
	this.session = session;
}

public String getDate_time() {
	return date_time;
}

public void setDate_time(String date_time) {
	this.date_time = date_time;
}

public String getChargename() {
	return chargename;
}

public void setChargename(String chargename) {
	this.chargename = chargename;
}

public String getDocid() {
	return docid;
}

public void setDocid(String docid) {
	this.docid = docid;
}

public String getDayseqno() {
	return dayseqno;
}

public void setDayseqno(String dayseqno) {
	this.dayseqno = dayseqno;
}

public int getPredayornot() {
	return predayornot;
}

public void setPredayornot(int predayornot) {
	this.predayornot = predayornot;
}

public String getNetPayableAmt() {
	return netPayableAmt;
}

public void setNetPayableAmt(String netPayableAmt) {
	this.netPayableAmt = netPayableAmt;
}

public String getInvAmt() {
	return invAmt;
}

public void setInvAmt(String invAmt) {
	this.invAmt = invAmt;
}

public String getDiscAmt() {
	return discAmt;
}

public void setDiscAmt(String discAmt) {
	this.discAmt = discAmt;
}

public String getPreAmt() {
	return preAmt;
}

public void setPreAmt(String preAmt) {
	this.preAmt = preAmt;
}

public String getBalAmt() {
	return balAmt;
}

public void setBalAmt(String balAmt) {
	this.balAmt = balAmt;
}

public String getPayer() {
	return payer;
}

public void setPayer(String payer) {
	this.payer = payer;
}

public String getRegistrationTotal() {
	return registrationTotal;
}

public void setRegistrationTotal(String registrationTotal) {
	this.registrationTotal = registrationTotal;
}

public String getIpdabrivationid() {
	return ipdabrivationid;
}

public void setIpdabrivationid(String ipdabrivationid) {
	this.ipdabrivationid = ipdabrivationid;
}

public int getCreditid() {
	return creditid;
}

public void setCreditid(int creditid) {
	this.creditid = creditid;
}

public String getPhysicalid() {
	return physicalid;
}

public void setPhysicalid(String physicalid) {
	this.physicalid = physicalid;
}

public double getNewdiscount() {
	return newdiscount;
}

public void setNewdiscount(double newdiscount) {
	this.newdiscount = newdiscount;
}

public ArrayList<Accounts> getPrepaymentList() {
	return prepaymentList;
}

public void setPrepaymentList(ArrayList<Accounts> prepaymentList) {
	this.prepaymentList = prepaymentList;
}

public ArrayList<Accounts> getRefundList() {
	return refundList;
}

public void setRefundList(ArrayList<Accounts> refundList) {
	this.refundList = refundList;
}

public ArrayList<Accounts> getTransactionList() {
	return transactionList;
}

public void setTransactionList(ArrayList<Accounts> transactionList) {
	this.transactionList = transactionList;
}

public int getAdditionaldiscallow() {
	return additionaldiscallow;
}

public void setAdditionaldiscallow(int additionaldiscallow) {
	this.additionaldiscallow = additionaldiscallow;
}

public String getInvoiceids() {
	return invoiceids;
}

public void setInvoiceids(String invoiceids) {
	this.invoiceids = invoiceids;
}

public String getInvoicedate() {
	return invoicedate;
}

public void setInvoicedate(String invoicedate) {
	this.invoicedate = invoicedate;
}

public String getChargeofqty() {
	return chargeofqty;
}

public void setChargeofqty(String chargeofqty) {
	this.chargeofqty = chargeofqty;
}

public String getUnitchargeofqty() {
	return unitchargeofqty;
}

public void setUnitchargeofqty(String unitchargeofqty) {
	this.unitchargeofqty = unitchargeofqty;
}

public String getManualinvoiceid() {
	return manualinvoiceid;
}

public void setManualinvoiceid(String manualinvoiceid) {
	this.manualinvoiceid = manualinvoiceid;
}

public String getRefundAmountx() {
	return refundAmountx;
}

public void setRefundAmountx(String refundAmountx) {
	this.refundAmountx = refundAmountx;
}

public String getLtype() {
	return ltype;
}

public void setLtype(String ltype) {
	this.ltype = ltype;
}

public String getDbaltotalx() {
	return dbaltotalx;
}

public void setDbaltotalx(String dbaltotalx) {
	this.dbaltotalx = dbaltotalx;
}

public String getCbaltotalx() {
	return cbaltotalx;
}

public void setCbaltotalx(String cbaltotalx) {
	this.cbaltotalx = cbaltotalx;
}

public String getDbalx() {
	return dbalx;
}

public void setDbalx(String dbalx) {
	this.dbalx = dbalx;
}

public String getCbalx() {
	return cbalx;
}

public void setCbalx(String cbalx) {
	this.cbalx = cbalx;
}

public String getCredurAmountx() {
	return credurAmountx;
}

public void setCredurAmountx(String credurAmountx) {
	this.credurAmountx = credurAmountx;
}

public String getTotaldebitAmount() {
	return totaldebitAmount;
}

public void setTotaldebitAmount(String totaldebitAmount) {
	this.totaldebitAmount = totaldebitAmount;
}

public int getCount() {
	return count;
}

public void setCount(int count) {
	this.count = count;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public String getInvoiceidstr() {
	return invoiceidstr;
}

public void setInvoiceidstr(String invoiceidstr) {
	this.invoiceidstr = invoiceidstr;
}

private String invoiceidstr;




	public String getIclosed() {
	return iclosed;
}

public void setIclosed(String iclosed) {
	this.iclosed = iclosed;
}

public String getIpost() {
	return ipost;
}

public void setIpost(String ipost) {
	this.ipost = ipost;
}

	public String getInvpstype() {
	return invpstype;
}

public void setInvpstype(String invpstype) {
	this.invpstype = invpstype;
}

	public String getLedgername() {
	return ledgername;
}

public void setLedgername(String ledgername) {
	this.ledgername = ledgername;
}

	public String getTotalamt() {
	return totalamt;
}

public void setTotalamt(String totalamt) {
	this.totalamt = totalamt;
}

	public String getTotalReceived() {
	return totalReceived;
}

public void setTotalReceived(String totalReceived) {
	this.totalReceived = totalReceived;
}

	private String cashtotal="0";
	private String cheqtotal="0";
	private String crtotal="0";
	private String drtotal="0";
	private String nefttotal="0";
	private String othertotal="0";
	private String actiontype = "0";
	
	private String totalref;
	private String calcdebittotal;
	private String shared_amount;
	private String isShared;
	private String indivisual_shared;
	private ArrayList<Accounts> sharelist;
	private String commission;
	private String pkginvissid;
	
	private String unitcharge;
	private String chargedisc;
	private String disc_approve;
	
	private String itype;
	private String admissiondate;
	private String dischargedate;
	
	private String department;
	private String disc_request;
	
	private int totaladmission;
	private int totaldischarge;

	private int admission;
	private int discharge;
	private String serviceamt;
	
	private String productname = "";
	
	public String getServiceamt() {
		return serviceamt;
	}

	public void setServiceamt(String serviceamt) {
		this.serviceamt = serviceamt;
	}
	
	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public int getTotaladmission() {
		return totaladmission;
	}

	public void setTotaladmission(int totaladmission) {
		this.totaladmission = totaladmission;
	}

	public int getTotaldischarge() {
		return totaldischarge;
	}

	public void setTotaldischarge(int totaldischarge) {
		this.totaldischarge = totaldischarge;
	}

	public int getAdmission() {
		return admission;
	}

	public void setAdmission(int admission) {
		this.admission = admission;
	}

	public int getDischarge() {
		return discharge;
	}

	public void setDischarge(int discharge) {
		this.discharge = discharge;
	}

	public String getDisc_request() {
		return disc_request;
	}

	public void setDisc_request(String disc_request) {
		this.disc_request = disc_request;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getAdmissiondate() {
		return admissiondate;
	}

	public void setAdmissiondate(String admissiondate) {
		this.admissiondate = admissiondate;
	}

	public String getDischargedate() {
		return dischargedate;
	}

	public void setDischargedate(String dischargedate) {
		this.dischargedate = dischargedate;
	}

	public String getItype() {
		return itype;
	}

	public void setItype(String itype) {
		this.itype = itype;
	}

	public String getDisc_approve() {
		return disc_approve;
	}

	public void setDisc_approve(String disc_approve) {
		this.disc_approve = disc_approve;
	}

	public String getUnitcharge() {
		return unitcharge;
	}

	public void setUnitcharge(String unitcharge) {
		this.unitcharge = unitcharge;
	}

	public String getChargedisc() {
		return chargedisc;
	}

	public void setChargedisc(String chargedisc) {
		this.chargedisc = chargedisc;
	}

	public String getPkginvissid() {
		return pkginvissid;
	}

	public void setPkginvissid(String pkginvissid) {
		this.pkginvissid = pkginvissid;
	}

	public String getCommission() {
		return commission;
	}

	public void setCommission(String commission) {
		this.commission = commission;
	}

	public ArrayList<Accounts> getSharelist() {
		return sharelist;
	}

	public void setSharelist(ArrayList<Accounts> sharelist) {
		this.sharelist = sharelist;
	}

	public String getIndivisual_shared() {
		return indivisual_shared;
	}

	public void setIndivisual_shared(String indivisual_shared) {
		this.indivisual_shared = indivisual_shared;
	}

	public String getIsShared() {
		return isShared;
	}

	public void setIsShared(String isShared) {
		this.isShared = isShared;
	}

	public String getShared_amount() {
		return shared_amount;
	}

	public void setShared_amount(String shared_amount) {
		this.shared_amount = shared_amount;
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

	public String getActiontype() {
		return actiontype;
	}

	public void setActiontype(String actiontype) {
		this.actiontype = actiontype;
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

	private ArrayList<Accounts> opdpaywiselist;
	private ArrayList<Accounts> ipdpaywiselist;
	private ArrayList<Accounts> pathlabpaywiselist;
	private ArrayList<Accounts> mdcinepaywiselist;
	private ArrayList<Accounts> advrefpaywiselist;
	
	private String commission_type;
	private String remark;
	private String all_or_indivisual;
	public String getAll_or_indivisual() {
		return all_or_indivisual;
	}

	public void setAll_or_indivisual(String all_or_indivisual) {
		this.all_or_indivisual = all_or_indivisual;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCommission_type() {
		return commission_type;
	}

	public void setCommission_type(String commission_type) {
		this.commission_type = commission_type;
	}

	public String getPaymodecash() {
		return paymodecash;
	}

	public void setPaymodecash(String paymodecash) {
		this.paymodecash = paymodecash;
	}

	public String getPaymodecheque() {
		return paymodecheque;
	}

	public void setPaymodecheque(String paymodecheque) {
		this.paymodecheque = paymodecheque;
	}

	private String paymodecheque;
	
	public String getPatientno() {
		return patientno;
	}

	public void setPatientno(String patientno) {
		this.patientno = patientno;
	}

	public String getPackagename() {
		return packagename;
	}

	public void setPackagename(String packagename) {
		this.packagename = packagename;
	}

	public String getTotalpayment() {
		return totalpayment;
	}

	public void setTotalpayment(String totalpayment) {
		this.totalpayment = totalpayment;
	}

	private int id;
	
	private int clientid;
	
	private int practitionerId;
	
	private String ClientName;
	private double altsum;
	private String practitionerName;
	private double shareAmt;
	private String commencing;
	private int appointmentid;
	private String mdicinecharge;
	private String hospchargel;
	private String appointmentType;
	private int tpid;
	private String creditCharge;
	private String userid;
	private String middlename="";
	private String percentage;
	private String refinvoiceid;

	public String getRefinvoiceid() {
		return refinvoiceid;
	}

	public void setRefinvoiceid(String refinvoiceid) {
		this.refinvoiceid = refinvoiceid;
	}

	private Integer invoiceid;
	
	private boolean paid;
	private int deleted;
	private String ipdid;
	
	private String howPaid;
	private String chargeid;
	private String selectedPayby;
	
	private boolean chargesInvoiced;
	private String priscid;
	private String selectedLocation;
	
	private boolean dna;
	
	private ArrayList<Accounts> sharedChargeList;
	private ArrayList<Accounts> patientviewbypackage;
	
	public ArrayList<Accounts> getPatientviewbypackage() {
		return patientviewbypackage;
	}

	public void setPatientviewbypackage(ArrayList<Accounts> patientviewbypackage) {
		this.patientviewbypackage = patientviewbypackage;
	}

	double totalSub=0.0;
	double totalDisc=0.0;
	double shareTotal=0.0;
	int totalQty=0;
	double qty=0.0;
	private String locationName;
	
	public double getQty() {
		return qty;
	}

	public void setQty(double qty) {
		this.qty = qty;
	}

	private String payeeName;
	private String payeeadress;
	private int apmtChargeType;
	private String month;
	private String ward;
	
	private String notes = "";
	private String policyExcess;
	
	private int policyexcesscode = 0;
	
	private boolean isPolicyExcess = false;
	
	private String paymentNote = "";
	
	private int creditInvoiceid;
	
	private double creditAmount;
	
	private String tpName = "";
	
	private String discAmmount;
	
	private String ondatetime;
	private String offdatetime;
	private String sectionroom;
	private String abrivationid;
	private String cancelUserid;
	private String cancelDT;
	private String cancelNotes;
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

	public String getAbrivationid() {
		return abrivationid;
	}

	public void setAbrivationid(String abrivationid) {
		this.abrivationid = abrivationid;
	}

	public String getSectionroom() {
		return sectionroom;
	}

	public void setSectionroom(String sectionroom) {
		this.sectionroom = sectionroom;
	}

	private String dtotal;
	private String btotal;
	private String ctotal;
	
	
	ArrayList<Charges>chargeList;
	
	private int quantity;
	
	private String chargeTotal;
	
	private int advref;
	
	private String resetinv;
	
	private int subchargeid;
	
	private String dedtn;
	private String tds;
	private String stmdcine;
	private String recdamt;
	private String runbal;
	private String tpcode;
	
	ArrayList<Master>masterAssessmentList;
	private String prepaymentid;
	
	private String disctype;
	private String discamt;
	private String pkgtotal;
	private String tpstotal;
	
	
	
	
	
	
	public String getTpstotal() {
		return tpstotal;
	}

	public void setTpstotal(String tpstotal) {
		this.tpstotal = tpstotal;
	}

	public String getPkgtotal() {
		return pkgtotal;
	}

	public void setPkgtotal(String pkgtotal) {
		this.pkgtotal = pkgtotal;
	}

	private String opdtotal;
	private String ipdtotal;
	private String pathlabtotal;
	private String mdcinetotal;
	private String advreftotal;
	private String newshftcharge;
	
	

	public String getNewshftcharge() {
		return newshftcharge;
	}

	public void setNewshftcharge(String newshftcharge) {
		this.newshftcharge = newshftcharge;
	}

	public String getDisctype() {
		return disctype;
	}

	public void setDisctype(String disctype) {
		this.disctype = disctype;
	}

	public String getDiscamt() {
		return discamt;
	}

	public void setDiscamt(String discamt) {
		this.discamt = discamt;
	}

	public String getPrepaymentid() {
		return prepaymentid;
	}

	public void setPrepaymentid(String prepaymentid) {
		this.prepaymentid = prepaymentid;
	}

	public ArrayList<Master> getMasterAssessmentList() {
		return masterAssessmentList;
	}

	public void setMasterAssessmentList(ArrayList<Master> masterAssessmentList) {
		this.masterAssessmentList = masterAssessmentList;
	}

	public String getDedtn() {
		return dedtn;
	}

	public void setDedtn(String dedtn) {
		this.dedtn = dedtn;
	}

	public String getTds() {
		return tds;
	}

	public void setTds(String tds) {
		this.tds = tds;
	}

	public String getStmdcine() {
		return stmdcine;
	}

	public void setStmdcine(String stmdcine) {
		this.stmdcine = stmdcine;
	}

	public String getRecdamt() {
		return recdamt;
	}

	public void setRecdamt(String recdamt) {
		this.recdamt = recdamt;
	}

	public String getRunbal() {
		return runbal;
	}

	public void setRunbal(String runbal) {
		this.runbal = runbal;
	}

	public int getSubchargeid() {
		return subchargeid;
	}

	public void setSubchargeid(int subchargeid) {
		this.subchargeid = subchargeid;
	}

	public String getResetinv() {
		return resetinv;
	}

	public void setResetinv(String resetinv) {
		this.resetinv = resetinv;
	}

	public int getAdvref() {
		return advref;
	}

	public void setAdvref(int advref) {
		this.advref = advref;
	}

	public String getChargeTotal() {
		return chargeTotal;
	}

	public void setChargeTotal(String chargeTotal) {
		this.chargeTotal = chargeTotal;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public ArrayList<Charges> getChargeList() {
		return chargeList;
	}

	public void setChargeList(ArrayList<Charges> chargeList) {
		this.chargeList = chargeList;
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

	public String getDiscAmmount() {
		return discAmmount;
	}

	public void setDiscAmmount(String discAmmount) {
		this.discAmmount = discAmmount;
	}

	public String getTpName() {
		return tpName;
	}

	public void setTpName(String tpName) {
		this.tpName = tpName;
	}

	public double getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(double creditAmount) {
		this.creditAmount = creditAmount;
	}

	public int getCreditInvoiceid() {
		return creditInvoiceid;
	}

	public void setCreditInvoiceid(int creditInvoiceid) {
		this.creditInvoiceid = creditInvoiceid;
	}

	public String getPaymentNote() {
		return paymentNote;
	}

	public void setPaymentNote(String paymentNote) {
		this.paymentNote = paymentNote;
	}

	public boolean isPolicyExcess() {
		return isPolicyExcess;
	}

	public void setPolicyExcess(boolean isPolicyExcess) {
		this.isPolicyExcess = isPolicyExcess;
	}

	public int getPolicyexcesscode() {
		return policyexcesscode;
	}

	public void setPolicyexcesscode(int policyexcesscode) {
		this.policyexcesscode = policyexcesscode;
	}

	public String getPolicyExcess() {
		return policyExcess;
	}

	public void setPolicyExcess(String policyExcess) {
		this.policyExcess = policyExcess;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public int getApmtChargeType() {
		return apmtChargeType;
	}

	public void setApmtChargeType(int apmtChargeType) {
		this.apmtChargeType = apmtChargeType;
	}

	public String getPayeeadress() {
		return payeeadress;
	}

	public void setPayeeadress(String payeeadress) {
		this.payeeadress = payeeadress;
	}

	public String getPayeeName() {
		return payeeName;
	}

	public String getDiscountx() {
		return discountx;
	}

	public void setDiscountx(String discountx) {
		this.discountx = discountx;
	}

	public String getTotalAmountx() {
		return totalAmountx;
	}

	public void setTotalAmountx(String totalAmountx) {
		this.totalAmountx = totalAmountx;
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

	public String getAmountx() {
		return amountx;
	}

	public void setAmountx(String amountx) {
		this.amountx = amountx;
	}

	public String getPayAmountx() {
		return payAmountx;
	}

	public void setPayAmountx(String payAmountx) {
		this.payAmountx = payAmountx;
	}

	public String getDebitAmountx() {
		return debitAmountx;
	}

	public void setDebitAmountx(String debitAmountx) {
		this.debitAmountx = debitAmountx;
	}

	public String getBalancex() {
		return balancex;
	}

	public void setBalancex(String balancex) {
		this.balancex = balancex;
	}

	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}

	private String invoicee;
	private double discount = 0;
	private String discountx;
	
	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		discount = Double.parseDouble(new DecimalFormat("##.##").format(discount));

		this.discount = discount;
	}

	public String getInvoicee() {
		return invoicee;
	}

	public void setInvoicee(String invoicee) {
		this.invoicee = invoicee;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	private String deliverstatus;
	
	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getMobno() {
		return mobno;
	}

	public void setMobno(String mobno) {
		this.mobno = mobno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	private String mobno;
	private String email;
	private String postcode;
	
	
	public String getDeliverstatus() {
		return deliverstatus;
	}

	public void setDeliverstatus(String deliverstatus) {
		this.deliverstatus = deliverstatus;
	}

	public boolean isDna() {
		return dna;
	}

	public void setDna(boolean dna) {
		this.dna = dna;
	}

	public String getSelectedLocation() {
		return selectedLocation;
	}

	public void setSelectedLocation(String selectedLocation) {
		this.selectedLocation = selectedLocation;
	}

	private String clientAdrs;
	private String policyNo;
	private String clientDob;
	
	
	private ArrayList<Invoice>invoiceList;
	private int totalChargesCount;
	
	
	
	public int getTotalChargesCount() {
		return totalChargesCount;
	}

	public void setTotalChargesCount(int totalChargesCount) {
		this.totalChargesCount = totalChargesCount;
	}

	public ArrayList<Invoice> getInvoiceList() {
		return invoiceList;
	}

	public void setInvoiceList(ArrayList<Invoice> invoiceList) {
		this.invoiceList = invoiceList;
	}

	
	
	
	public String getClientAdrs() {
		return clientAdrs;
	}

	public void setClientAdrs(String clientAdrs) {
		this.clientAdrs = clientAdrs;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public String getClientDob() {
		return clientDob;
	}

	public void setClientDob(String clientDob) {
		this.clientDob = clientDob;
	}

	public boolean isChargesInvoiced() {
		return chargesInvoiced;
	}

	public void setChargesInvoiced(boolean chargesInvoiced) {
		this.chargesInvoiced = chargesInvoiced;
	}

	public String getSelectedPayby() {
		return selectedPayby;
	}

	public void setSelectedPayby(String selectedPayby) {
		this.selectedPayby = selectedPayby;
	}

	private int payBy;
	private String whoPay;
	private String apptId;
	private String treatmentEpisodeName;
	private double totalAmount;
	private String totalAmountx;
	
	private String stdcharge;
	private int curstatus;
	
	public int getCurstatus() {
		return curstatus;
	}

	public void setCurstatus(int curstatus) {
		this.curstatus = curstatus;
	}

	public String getStdcharge() {
		return stdcharge;
	}

	public void setStdcharge(String stdcharge) {
		this.stdcharge = stdcharge;
	}

	public double getBalanceTotal() {
		return balanceTotal;
	}

	public void setBalanceTotal(double balanceTotal) {
		balanceTotal = Double.parseDouble(new DecimalFormat("##.##").format(balanceTotal));

		
		this.balanceTotal = balanceTotal;
	}

	public double getCreditTotal() {
		return creditTotal;
	}

	public void setCreditTotal(double creditTotal) {
		creditTotal = Double.parseDouble(new DecimalFormat("##.##").format(creditTotal));

		this.creditTotal = creditTotal;
	}

	public double getDebitTotal() {
		return debitTotal;
	}

	public void setDebitTotal(double debitTotal) {
		debitTotal = Double.parseDouble(new DecimalFormat("##.##").format(debitTotal));

		this.debitTotal = debitTotal;
	}

	private double creditTotal;
	private double debitTotal;
	private double balanceTotal;
	private String balanceTotalx;
	private String creditTotalx;
	private String debitTotalx;
	
	private String stdchargeid;
	private String assesmentid;
	
	public String getAssesmentid() {
		return assesmentid;
	}

	public void setAssesmentid(String assesmentid) {
		this.assesmentid = assesmentid;
	}

	public String getStdchargeid() {
		return stdchargeid;
	}

	public void setStdchargeid(String stdchargeid) {
		this.stdchargeid = stdchargeid;
	}

	public String getBalanceTotalx() {
		return balanceTotalx;
	}

	public void setBalanceTotalx(String balanceTotalx) {
		this.balanceTotalx = balanceTotalx;
	}

	private String initial;
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

	private String firstname;
	private String lastname;
	private String address;
	private String city;
	private String location;
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

	private String thirdParty;
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		totalAmount = Double.parseDouble(new DecimalFormat("##.##").format(totalAmount));

		this.totalAmount = totalAmount;
	}

	public String getTreatmentEpisodeName() {
		return treatmentEpisodeName;
	}

	public void setTreatmentEpisodeName(String treatmentEpisodeName) {
		this.treatmentEpisodeName = treatmentEpisodeName;
	}

	public String getApptId() {
		return apptId;
	}

	public void setApptId(String apptId) {
		this.apptId = apptId;
	}

	public String getWhoPay() {
		return whoPay;
	}

	public void setWhoPay(String whoPay) {
		this.whoPay = whoPay;
	}

	private String invoiceNo;
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

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		amount = Double.parseDouble(new DecimalFormat("##.##").format(amount));

		this.amount = amount;
	}

	private String date;
	private String paymentmode;
	private double amount;
	private String amountx;
	
	public int getPayBy() {
		return payBy;
	}

	public void setPayBy(int payBy) {
		this.payBy = payBy;
	}

	public String getHowPaid() {
		return howPaid;
	}

	public void setHowPaid(String howPaid) {
		this.howPaid = howPaid;
	}

	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}

	private double payAmount;
	private String payAmountx;
	
	private int numberOfChages;
	
	private String charges;
	
	private String payby;
	
	private double debitAmount;
	private String debitAmountx;
	
	private String chargeType = "";
	private double balance;
	private String balancex;
	
	private String invoicenameid;
	
	public String getInvoicenameid() {
		return invoicenameid;
	}

	public void setInvoicenameid(String invoicenameid) {
		this.invoicenameid = invoicenameid;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		balance = Double.parseDouble(new DecimalFormat("##.##").format(balance));

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

	private String fromDate;
	private String toDate;
	
	

	public double getDebitAmount() {

		return debitAmount;
	}

	public void setDebitAmount(double debitAmount) {
		debitAmount = Double.parseDouble(new DecimalFormat("##.##").format(debitAmount));

		this.debitAmount = debitAmount;
	}

	public String getPayby() {
		return payby;
	}

	public void setPayby(String payby) {
		this.payby = payby;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public int getInvoiceid() {
		return invoiceid;
	}

	public void setInvoiceid(int invoiceid) {
		this.invoiceid = invoiceid;
	}

	public String getCreditCharge() {
		return creditCharge;
	}

	public void setCreditCharge(String creditCharge) {
		this.creditCharge = creditCharge;
	}

	public String getAppointmentType() {
		return appointmentType;
	}

	public void setAppointmentType(String appointmentType) {
		this.appointmentType = appointmentType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getClientid() {
		return clientid;
	}

	public void setClientid(int clientid) {
		this.clientid = clientid;
	}

	public int getPractitionerId() {
		return practitionerId;
	}

	public void setPractitionerId(int practitionerId) {
		this.practitionerId = practitionerId;
	}

	public String getClientName() {
		return ClientName;
	}

	public void setClientName(String clientName) {
		ClientName = clientName;
	}

	public String getPractitionerName() {
		return practitionerName;
	}

	public void setPractitionerName(String practitionerName) {
		this.practitionerName = practitionerName;
	}

	public String getCommencing() {
		return commencing;
	}

	public void setCommencing(String commencing) {
		this.commencing = commencing;
	}

	public double getPayAmount() {

		return payAmount;
	}

	public void setPayAmount(double payAmount) {
		payAmount = Double.parseDouble(new DecimalFormat("##.##").format(payAmount));

		this.payAmount = payAmount;
	}

	public int getNumberOfChages() {
		return numberOfChages;
	}

	public void setNumberOfChages(int numberOfChages) {
		this.numberOfChages = numberOfChages;
	}

	public String getCharges() {
		return charges;
	}

	public void setCharges(String charges) {
		this.charges = charges;
	}

	public String getPriscid() {
		return priscid;
	}

	public void setPriscid(String priscid) {
		this.priscid = priscid;
	}

	public String getChargeid() {
		return chargeid;
	}

	public void setChargeid(String chargeid) {
		this.chargeid = chargeid;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public String getIpdid() {
		return ipdid;
	}

	public void setIpdid(String ipdid) {
		this.ipdid = ipdid;
	}

	public double getShareAmt() {
		return shareAmt;
	}

	public void setShareAmt(double shareAmt) {
		this.shareAmt = shareAmt;
	}

	public ArrayList<Accounts> getSharedChargeList() {
		return sharedChargeList;
	}

	public void setSharedChargeList(ArrayList<Accounts> sharedChargeList) {
		this.sharedChargeList = sharedChargeList;
	}

	public double getTotalSub() {
		return totalSub;
	}

	public void setTotalSub(double totalSub) {
		this.totalSub = totalSub;
	}

	public double getTotalDisc() {
		return totalDisc;
	}

	public void setTotalDisc(double totalDisc) {
		this.totalDisc = totalDisc;
	}

	public double getShareTotal() {
		return shareTotal;
	}

	public void setShareTotal(double shareTotal) {
		this.shareTotal = shareTotal;
	}

	public int getTotalQty() {
		return totalQty;
	}

	public void setTotalQty(int totalQty) {
		this.totalQty = totalQty;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

	public int getTpid() {
		return tpid;
	}

	public void setTpid(int tpid) {
		this.tpid = tpid;
	}

	public int getAppointmentid() {
		return appointmentid;
	}

	public void setAppointmentid(int appointmentid) {
		this.appointmentid = appointmentid;
	}

	public String getMdicinecharge() {
		return mdicinecharge;
	}

	public void setMdicinecharge(String mdicinecharge) {
		this.mdicinecharge = mdicinecharge;
	}

	public String getHospchargel() {
		return hospchargel;
	}

	public void setHospchargel(String hospchargel) {
		this.hospchargel = hospchargel;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getTpcode() {
		return tpcode;
	}

	public void setTpcode(String tpcode) {
		this.tpcode = tpcode;
	}

	public double getAltsum() {
		return altsum;
	}

	public void setAltsum(double altsum) {
		this.altsum = altsum;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
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

	public String getOndatetime() {
		return ondatetime;
	}

	public void setOndatetime(String ondatetime) {
		this.ondatetime = ondatetime;
	}

	public String getOffdatetime() {
		return offdatetime;
	}

	public void setOffdatetime(String offdatetime) {
		this.offdatetime = offdatetime;
	}

	public String getPercentage() {
		return percentage;
	}

	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}

	public int getTodyasadm() {
		return todyasadm;
	}

	public void setTodyasadm(int todyasadm) {
		this.todyasadm = todyasadm;
	}

	public int getTodaydis() {
		return todaydis;
	}

	public void setTodaydis(int todaydis) {
		this.todaydis = todaydis;
	}

public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

public String getNewsr() {
		return newsr;
	}

	public void setNewsr(String newsr) {
		this.newsr = newsr;
	}

public String getAdvno() {
		return advno;
	}

	public void setAdvno(String advno) {
		this.advno = advno;
	}

private String username;

private String  newsr;	
private double totalbillamount=0.0,totalrecammount=0.0,totaldiscountammount=0.0;


public double getTotalbillamount() {
	return totalbillamount;
}

public void setTotalbillamount(double totalbillamount) {
	this.totalbillamount = totalbillamount;
}

public double getTotalrecammount() {
	return totalrecammount;
}

public void setTotalrecammount(double totalrecammount) {
	this.totalrecammount = totalrecammount;
}

public double getTotaldiscountammount() {
	return totaldiscountammount;
}

public void setTotaldiscountammount(double totaldiscountammount) {
	this.totaldiscountammount = totaldiscountammount;
}
public String getRefid() {
	return refid;
}

public void setRefid(String refid) {
	this.refid = refid;
}

public String getInvoicetype() {
	return invoicetype;
}

public void setInvoicetype(String invoicetype) {
	this.invoicetype = invoicetype;
}

public boolean isDiscstatus() {
	return discstatus;
}

public void setDiscstatus(boolean discstatus) {
	this.discstatus = discstatus;
}

public String getIpdopdseq() {
	return ipdopdseq;
}

public void setIpdopdseq(String ipdopdseq) {
	this.ipdopdseq = ipdopdseq;
}

public boolean isTodays() {
	return todays;
}

public void setTodays(boolean todays) {
	this.todays = todays;
}

public boolean isDeleteddis() {
	return deleteddis;
}

public void setDeleteddis(boolean deleteddis) {
	this.deleteddis = deleteddis;
}

public String getInvdate() {
	return invdate;
}

public void setInvdate(String invdate) {
	this.invdate = invdate;
}

public String getMasterchargetype() {
	return masterchargetype;
}

public void setMasterchargetype(String masterchargetype) {
	this.masterchargetype = masterchargetype;
}



public String getAptmname() {
	return aptmname;
}

public void setAptmname(String aptmname) {
	this.aptmname = aptmname;
}

public double getRefundamountbyid() {
	return refundamountbyid;
}

public void setRefundamountbyid(double refundamountbyid) {
	this.refundamountbyid = refundamountbyid;
}

public double getTotalrefundamount() {
	return totalrefundamount;
}

public void setTotalrefundamount(double totalrefundamount) {
	this.totalrefundamount = totalrefundamount;
}

public double getDiscountbyrs() {
	return discountbyrs;
}

public void setDiscountbyrs(double discountbyrs) {
	this.discountbyrs = discountbyrs;
}


public ArrayList<Investigation> getInvstlist() {
	return invstlist;
}

public void setInvstlist(ArrayList<Investigation> invstlist) {
	this.invstlist = invstlist;
}
public String getDob() {
	return dob;
}

public void setDob(String dob) {
	this.dob = dob;
}

public String getApmtcode() {
	return apmtcode;
}

public void setApmtcode(String apmtcode) {
	this.apmtcode = apmtcode;
}

public String getStarttime() {
	return starttime;
}

public void setStarttime(String starttime) {
	this.starttime = starttime;
}

public String getDuration() {
	return duration;
}

public void setDuration(String duration) {
	this.duration = duration;
}

public int getMarkappointment() {
	return markappointment;
}

public void setMarkappointment(int markappointment) {
	this.markappointment = markappointment;
}

public int getThirdpartyid() {
	return thirdpartyid;
}

public void setThirdpartyid(int thirdpartyid) {
	this.thirdpartyid = thirdpartyid;
}

public int getActive() {
	return active;
}

public void setActive(int active) {
	this.active = active;
}

public int getTransactiontype() {
	return transactiontype;
}

public void setTransactiontype(int transactiontype) {
	this.transactiontype = transactiontype;
}

public String getBackDate() {
	return backDate;
}

public void setBackDate(String backDate) {
	this.backDate = backDate;
}

public int getStdflag() {
	return stdflag;
}

public void setStdflag(int stdflag) {
	this.stdflag = stdflag;
}

public int getLogid() {
	return logid;
}

public void setLogid(int logid) {
	this.logid = logid;
}

public int getBedid() {
	return bedid;
}

public void setBedid(int bedid) {
	this.bedid = bedid;
}

public int getAll_shared() {
	return all_shared;
}

public void setAll_shared(int all_shared) {
	this.all_shared = all_shared;
}

public int getInvpkgid() {
	return invpkgid;
}

public void setInvpkgid(int invpkgid) {
	this.invpkgid = invpkgid;
}

public int getIsshareablecharge() {
	return isshareablecharge;
}

public void setIsshareablecharge(int isshareablecharge) {
	this.isshareablecharge = isshareablecharge;
}

public int getSharedrid() {
	return sharedrid;
}

public void setSharedrid(int sharedrid) {
	this.sharedrid = sharedrid;
}

public String getTpcommencing() {
	return tpcommencing;
}

public void setTpcommencing(String tpcommencing) {
	this.tpcommencing = tpcommencing;
}

public int getTpkg() {
	return tpkg;
}

public void setTpkg(int tpkg) {
	this.tpkg = tpkg;
}

public String getPkgcode() {
	return pkgcode;
}

public void setPkgcode(String pkgcode) {
	this.pkgcode = pkgcode;
}

public int getSqno() {
	return sqno;
}

public void setSqno(int sqno) {
	this.sqno = sqno;
}

public String getTpfdate() {
	return tpfdate;
}

public void setTpfdate(String tpfdate) {
	this.tpfdate = tpfdate;
}

public String getTptodate() {
	return tptodate;
}

public void setTptodate(String tptodate) {
	this.tptodate = tptodate;
}

public String getDiscpercent() {
	return discpercent;
}

public void setDiscpercent(String discpercent) {
	this.discpercent = discpercent;
}

public String getMasterchargedisc() {
	return masterchargedisc;
}

public void setMasterchargedisc(String masterchargedisc) {
	this.masterchargedisc = masterchargedisc;
}

public String getDiscountamt() {
	return discountamt;
}

public void setDiscountamt(String discountamt) {
	this.discountamt = discountamt;

}public String getOriginalchargename() {
	return originalchargename;
}

public void setOriginalchargename(String originalchargename) {
	this.originalchargename = originalchargename;
}

private String refid;
private boolean discstatus;
private String ipdopdseq;
private boolean todays;
private boolean deleteddis;
private String invdate;
private String masterchargetype;
private String aptmname;
private double refundamountbyid;
private double totalrefundamount;
private double discountbyrs;
private ArrayList<Investigation> invstlist;
private String dob;
private String apmtcode;
private String starttime;
private String duration;
private int markappointment;
private int thirdpartyid;
private int active;
private int transactiontype;
private String backDate;
private int stdflag;
private int logid;
private int bedid;
private int all_shared;
private int invpkgid;
private int isshareablecharge;
private int  sharedrid;
private String tpcommencing;
private int tpkg;
private String pkgcode;
private int sqno;
private String tpfdate;
private String tptodate;
private String discpercent;
private String masterchargedisc;
private String discountamt;
private String originalchargename;
private ArrayList<Accounts> newdisclist;
private double discountamtt;
private String discoutrs;
private String  invoiceid1;

public String getInvoiceid1() {
	return invoiceid1;
}

public void setInvoiceid1(String invoiceid1) {
	this.invoiceid1 = invoiceid1;
}

private String krackage;
public double getDiscountamtt() {
	return discountamtt;
}

public void setDiscountamtt(double discountamtt) {
	this.discountamtt = discountamtt;
}

public ArrayList<Accounts> getNewdisclist() {
	return newdisclist;
}

public void setNewdisclist(ArrayList<Accounts> newdisclist) {
	this.newdisclist = newdisclist;
}

public String getDiscoutrs() {
	return discoutrs;
}

public void setDiscoutrs(String discoutrs) {
	this.discoutrs = discoutrs;
}

public String getKrackage() {
	return krackage;
}

public void setKrackage(String krackage) {
	this.krackage = krackage;
}

public String getPhysical_payment_id() {
	return physical_payment_id;
}

public void setPhysical_payment_id(String physical_payment_id) {
	this.physical_payment_id = physical_payment_id;
}

private String tax1,tax2,tax3,taxname1,taxname2,taxname3;
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

public String getTaxname1() {
	return taxname1;
}

public void setTaxname1(String taxname1) {
	this.taxname1 = taxname1;
}

public String getTaxname2() {
	return taxname2;
}

public void setTaxname2(String taxname2) {
	this.taxname2 = taxname2;
}

public String getTaxname3() {
	return taxname3;
}

public void setTaxname3(String taxname3) {
	this.taxname3 = taxname3;
}

public String getChargedescription() {
	return chargedescription;
}

public void setChargedescription(String chargedescription) {
	this.chargedescription = chargedescription;
}

private String physical_payment_id;
private String chargedescription;
private double taxtype1,taxtype2,taxtype3;




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

public String getDaycaretotal() {
	return daycaretotal;
}

public void setDaycaretotal(String daycaretotal) {
	this.daycaretotal = daycaretotal;
}

public String getCancelsts() {
	return cancelsts;
}

public void setCancelsts(String cancelsts) {
	this.cancelsts = cancelsts;
}

public String getReceiptid() {
	return receiptid;
}

public void setReceiptid(String receiptid) {
	this.receiptid = receiptid;
}

public String getBghseqId() {
	return bghseqId;
}

public void setBghseqId(String bghseqId) {
	this.bghseqId = bghseqId;
}

public String getWardid() {
	return wardid;
}

public void setWardid(String wardid) {
	this.wardid = wardid;
}

private String daycaretotal;
private String bghseqId;
private String apprId,apprDate,pendId,penDate;



public String getNewreceiptid() {
	return newreceiptid;
}

public void setNewreceiptid(String newreceiptid) {
	this.newreceiptid = newreceiptid;
}

public String getApprId() {
	return apprId;
}

public void setApprId(String apprId) {
	this.apprId = apprId;
}

public String getApprDate() {
	return apprDate;
}

public void setApprDate(String apprDate) {
	this.apprDate = apprDate;
}

public String getPendId() {
	return pendId;
}

public void setPendId(String pendId) {
	this.pendId = pendId;
}

public String getPenDate() {
	return penDate;
}

public void setPenDate(String penDate) {
	this.penDate = penDate;
}
public String getChildid() {
	return childid;
}

public void setChildid(String childid) {
	this.childid = childid;
}

public String getParentId() {
	return parentId;
}

public void setParentId(String parentId) {
	this.parentId = parentId;
}


public String getAmtWithouttax() {
	return amtWithouttax;
}

public void setAmtWithouttax(String amtWithouttax) {
	this.amtWithouttax = amtWithouttax;
}

public double getTotalunitCharge() {
	return totalunitCharge;
}

public void setTotalunitCharge(double totalunitCharge) {
	this.totalunitCharge = totalunitCharge;
}
public String getUnitcharges() {
	return unitcharges;
}

public void setUnitcharges(String unitcharges) {
	this.unitcharges = unitcharges;

}

public boolean isDiscreq() {
	return discreq;
}

public void setDiscreq(boolean discreq) {
	this.discreq = discreq;
}

public double getFinalTotal() {
	return finalTotal;
}

public void setFinalTotal(double finalTotal) {
	this.finalTotal = finalTotal;
}

public String getInddiscounttotal() {
	return inddiscounttotal;
}

public void setInddiscounttotal(String inddiscounttotal) {
	this.inddiscounttotal = inddiscounttotal;
}

public String getManualcharge() {
	return manualcharge;
}

public void setManualcharge(String manualcharge) {
	this.manualcharge = manualcharge;
}

public String getShowdate() {
	return showdate;
}

public void setShowdate(String showdate) {
	this.showdate = showdate;
}

public String getUpitotal() {
	return upitotal;
}

public void setUpitotal(String upitotal) {
	this.upitotal = upitotal;
}

private String childid;
private String parentId;
private String amtWithouttax;
private double totalunitCharge;
private boolean discreq;
private double finalTotal; 
private String fseqno;
private double advance;

public String getFseqno() {
	return fseqno;
}

public void setFseqno(String fseqno) {
	this.fseqno = fseqno;
}

@Override
public int compareTo(Accounts o) {
	
	return this.getInvoiceid1().compareTo(o.getInvoiceid1());
}

}
