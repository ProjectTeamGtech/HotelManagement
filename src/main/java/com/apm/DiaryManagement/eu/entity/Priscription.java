package com.apm.DiaryManagement.eu.entity;

import java.util.ArrayList;

public class Priscription {
	public ArrayList<Priscription> getPriscriptionListprachi() {
		return priscriptionListprachi;
	}

	public void setPriscriptionListprachi(ArrayList<Priscription> priscriptionListprachi) {
		this.priscriptionListprachi = priscriptionListprachi;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getGstin() {
		return gstin;
	}

	public void setGstin(String gstin) {
		this.gstin = gstin;
	}

	public String getDlno() {
		return dlno;
	}

	public void setDlno(String dlno) {
		this.dlno = dlno;
	}

	private String cancel_po;
	private boolean purchase_edit;
	private String deliverydate;
	private String averagetime;
	private int ostatus=0;
	private String dosetime1="";
	private String dosetime2="";
	private String dosetime3="";
	private String dosetime4="";
	private String dosetime5="";
	private String strengthnew="";
	private String unit="0";
	private String dr_qty;
	private String molecules="";
	private  ArrayList<Priscription> priscriptionlist;
	private String billpayamt;
	private String indent_approve;
	private String srno;
	private String totalchildmedids="0";
	private String dispriscsrno;
	private String priscriptiontime;
	private String priscindivisualremark;
	private String prisctimename;
	private String unitextension;
	private String prischildid;
	private boolean prisctimestatus = false;
	private boolean priscreamrkstatus = false;
	private boolean priscunitstatus = false;
	private String dosegiventime;
	private int currdays;
	private int totaldays;
	private String prisc_status;
	private String totalid;
	private String medid;
	private String status;
	private String opdipdprisc;
	private boolean approve_po;
	private String approve_ponew;
	private String isfromipd;
	private String isipdtimeshow;
	private String ipdtimeshow1;
	private String ipdtimeshow2;
	private String ipdtimeshow3;
	private String priscqty;
	private ArrayList<Priscription> childprisclist;
	private int count;
	private int repeatstatus=0;
	private int countforcal;
	private String mdicinenametxtnew;
	private String newpaymentmode;
	private double newpayment;
	private String totalCreditbyuser;
	private double totalReceived=0;
	private 	double totalRefund=0;
	private	double totalBalance=0;
	private double totalcreditReturn;
	private double todayagainstcredit;
	private double newcredit;
	private double credit;
	private String paymentdate;
	private double discper;
	private String iswbd;
	private String frequency;
	private String caldose;
	private String masterdose;
	private boolean masterdosestatus; 
	private String locationame="";
	private String totalrefundrs;
	private int showindentreq;
	
	private String uom;
	private String emrnote;
	private String dosenotetxt;
	private double creditlimit=0;
	private boolean creditlimitaccess;
	private String tempids;
	
	private String tgstamt;
	private String gstper;
	private String sharediscount;
	private String indidiscount;
	private String stock;
	private int totalqty;
	private String temptot;
	private double actualtemptot;
	private String returnedqty;
	private int chargesessionid=0;;
	private int chargetempid=0;
	private String color;
	private int isdeliverstaus=0;
	private String requestuserid;
	private int availablestock;
	private String reqfromlocation;
	private int dummybillno;
	private int isdeletable=0;
	private double unit_price;
	private int discount_status;
	private int disc_type;
	private String approve_userid;
	private double discinper;
	private int new_prisc_status;
	private int deliver_statuss;
	private int locationid;
	private int req_qtys=0;
	private boolean shownewrequest;
	private int deliverystatus;
	private boolean dosenotesstaus;
	private String prisc_time_regional;
	private int prisc_print_taken;
	private int disc_delete;
	private String req_location;
	private String paymentuserid;
	private String default_location;
	String firstdate;
    String lastdate ;
    private int barcodesale;
    private boolean session_added_med;
    private String secondary_shelf;
    private int isinadjustment=0;
    private String tokenno;
    private String component;
    private String lastappointmentid,gynicform;
    private double bill_purpice,bill_pur_gst;
    private String margintotal;
    private boolean patient_edit;
    private double totalonline;
    private int sameeditdate;
    private int edited=0;
    private int fromtreatmentgiven;
    private String ids,oldparentid,medqty;
    private String subcategoryname;
    private String orignal_mrp;
    private String fak_newpriscdate;
    private double refund_amt;
    private double totalrefund_online;
    private String third_party_name;
    private ArrayList<Priscription> priscriptionListprachi;
    

	public String getThird_party_name() {
		return third_party_name;
	}

	public void setThird_party_name(String third_party_name) {
		this.third_party_name = third_party_name;
	}

	public double getTotalrefund_online() {
		return totalrefund_online;
	}

	public void setTotalrefund_online(double totalrefund_online) {
		this.totalrefund_online = totalrefund_online;
	}

	public double getRefund_amt() {
		return refund_amt;
	}

	public void setRefund_amt(double refund_amt) {
		this.refund_amt = refund_amt;
	}

	public String getSubcategoryname() {
		return subcategoryname;
	}

	public void setSubcategoryname(String subcategoryname) {
		this.subcategoryname = subcategoryname;
	}
    
    public String getFak_newpriscdate() {
		return fak_newpriscdate;
	}

	public void setFak_newpriscdate(String fak_newpriscdate) {
		this.fak_newpriscdate = fak_newpriscdate;
	}
    
	public String getOrignal_mrp() {
		return orignal_mrp;
	}

	public void setOrignal_mrp(String orignal_mrp) {
		this.orignal_mrp = orignal_mrp;
	}

	public String getMedqty() {
		return medqty;
	}

	public void setMedqty(String medqty) {
		this.medqty = medqty;
	}

	public String getOldparentid() {
		return oldparentid;
	}

	public void setOldparentid(String oldparentid) {
		this.oldparentid = oldparentid;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public int getFromtreatmentgiven() {
		return fromtreatmentgiven;
	}

	public void setFromtreatmentgiven(int fromtreatmentgiven) {
		this.fromtreatmentgiven = fromtreatmentgiven;
	}

	public int getEdited() {
		return edited;
	}

	public void setEdited(int edited) {
		this.edited = edited;
	}

	public int getSameeditdate() {
		return sameeditdate;
	}

	public void setSameeditdate(int sameeditdate) {
		this.sameeditdate = sameeditdate;
	}

	public double getTotalonline() {
		return totalonline;
	}

	public void setTotalonline(double totalonline) {
		this.totalonline = totalonline;
	}

	public boolean isPatient_edit() {
		return patient_edit;
	}

	public void setPatient_edit(boolean patient_edit) {
		this.patient_edit = patient_edit;
	}

	public String getMargintotal() {
		return margintotal;
	}

	public void setMargintotal(String margintotal) {
		this.margintotal = margintotal;
	}

	public double getBill_purpice() {
		return bill_purpice;
	}

	public void setBill_purpice(double bill_purpice) {
		this.bill_purpice = bill_purpice;
	}

	public double getBill_pur_gst() {
		return bill_pur_gst;
	}

	public void setBill_pur_gst(double bill_pur_gst) {
		this.bill_pur_gst = bill_pur_gst;
	}

	public String getLastappointmentid() {
		return lastappointmentid;
	}

	public void setLastappointmentid(String lastappointmentid) {
		this.lastappointmentid = lastappointmentid;
	}

	public String getGynicform() {
		return gynicform;
	}

	public void setGynicform(String gynicform) {
		this.gynicform = gynicform;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public String getTokenno() {
		return tokenno;
	}

	public void setTokenno(String tokenno) {
		this.tokenno = tokenno;
	}

	public int getIsinadjustment() {
		return isinadjustment;
	}

	public void setIsinadjustment(int isinadjustment) {
		this.isinadjustment = isinadjustment;
	}

	public String getSecondary_shelf() {
		return secondary_shelf;
	}

	public void setSecondary_shelf(String secondary_shelf) {
		this.secondary_shelf = secondary_shelf;
	}

	public boolean isSession_added_med() {
		return session_added_med;
	}

	public void setSession_added_med(boolean session_added_med) {
		this.session_added_med = session_added_med;
	}

	public int getBarcodesale() {
		return barcodesale;
	}

	public void setBarcodesale(int barcodesale) {
		this.barcodesale = barcodesale;
	}

	public String getFirstdate() {
		return firstdate;
	}

	public void setFirstdate(String firstdate) {
		this.firstdate = firstdate;
	}

	public String getLastdate() {
		return lastdate;
	}

	public void setLastdate(String lastdate) {
		this.lastdate = lastdate;
	}

	public String getDefault_location() {
		return default_location;
	}

	public void setDefault_location(String default_location) {
		this.default_location = default_location;
	}

	public String getPaymentuserid() {
		return paymentuserid;
	}

	public void setPaymentuserid(String paymentuserid) {
		this.paymentuserid = paymentuserid;
	}

	public String getReq_location() {
		return req_location;
	}

	public void setReq_location(String req_location) {
		this.req_location = req_location;
	}

	public int getDisc_delete() {
		return disc_delete;
	}

	public void setDisc_delete(int disc_delete) {
		this.disc_delete = disc_delete;
	}

	public int getPrisc_print_taken() {
		return prisc_print_taken;
	}

	public void setPrisc_print_taken(int prisc_print_taken) {
		this.prisc_print_taken = prisc_print_taken;
	}

	public String getPrisc_time_regional() {
		return prisc_time_regional;
	}

	public void setPrisc_time_regional(String prisc_time_regional) {
		this.prisc_time_regional = prisc_time_regional;
	}

	public boolean isDosenotesstaus() {
		return dosenotesstaus;
	}

	public void setDosenotesstaus(boolean dosenotesstaus) {
		this.dosenotesstaus = dosenotesstaus;
	}

	public int getDeliverystatus() {
		return deliverystatus;
	}

	public void setDeliverystatus(int deliverystatus) {
		this.deliverystatus = deliverystatus;
	}

	public boolean isShownewrequest() {
		return shownewrequest;
	}

	public void setShownewrequest(boolean shownewrequest) {
		this.shownewrequest = shownewrequest;
	}

	public int getReq_qtys() {
		return req_qtys;
	}

	public void setReq_qtys(int req_qtys) {
		this.req_qtys = req_qtys;
	}

	public int getLocationid() {
		return locationid;
	}

	public void setLocationid(int locationid) {
		this.locationid = locationid;
	}

	public int getDeliver_statuss() {
		return deliver_statuss;
	}

	public void setDeliver_statuss(int deliver_statuss) {
		this.deliver_statuss = deliver_statuss;
	}

	public int getNew_prisc_status() {
		return new_prisc_status;
	}

	public void setNew_prisc_status(int new_prisc_status) {
		this.new_prisc_status = new_prisc_status;
	}

	public double getDiscinper() {
		return discinper;
	}

	public void setDiscinper(double discinper) {
		this.discinper = discinper;
	}

	public String getApprove_userid() {
		return approve_userid;
	}

	public void setApprove_userid(String approve_userid) {
		this.approve_userid = approve_userid;
	}

	public int getDisc_type() {
		return disc_type;
	}

	public void setDisc_type(int disc_type) {
		this.disc_type = disc_type;
	}

	public int getDiscount_status() {
		return discount_status;
	}

	public void setDiscount_status(int discount_status) {
		this.discount_status = discount_status;
	}

	public double getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(double unit_price) {
		this.unit_price = unit_price;
	}

	public int getIsdeletable() {
		return isdeletable;
	}

	public void setIsdeletable(int isdeletable) {
		this.isdeletable = isdeletable;
	}

	public int getDummybillno() {
		return dummybillno;
	}

	public void setDummybillno(int dummybillno) {
		this.dummybillno = dummybillno;
	}

	public String getReqfromlocation() {
		return reqfromlocation;
	}

	public void setReqfromlocation(String reqfromlocation) {
		this.reqfromlocation = reqfromlocation;
	}

	public int getAvailablestock() {
		return availablestock;
	}

	public void setAvailablestock(int availablestock) {
		this.availablestock = availablestock;
	}

	public String getRequestuserid() {
		return requestuserid;
	}

	public void setRequestuserid(String requestuserid) {
		this.requestuserid = requestuserid;
	}

	public int getIsdeliverstaus() {
		return isdeliverstaus;
	}

	public void setIsdeliverstaus(int isdeliverstaus) {
		this.isdeliverstaus = isdeliverstaus;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getChargetempid() {
		return chargetempid;
	}

	public void setChargetempid(int chargetempid) {
		this.chargetempid = chargetempid;
	}

	public int getChargesessionid() {
		return chargesessionid;
	}

	public void setChargesessionid(int chargesessionid) {
		this.chargesessionid = chargesessionid;
	}

	public String getReturnedqty() {
		return returnedqty;
	}

	public void setReturnedqty(String returnedqty) {
		this.returnedqty = returnedqty;
	}

	public String getTemptot() {
		return temptot;
	}

	public void setTemptot(String temptot) {
		this.temptot = temptot;
	}

	public double getActualtemptot() {
		return actualtemptot;
	}

	public void setActualtemptot(double actualtemptot) {
		this.actualtemptot = actualtemptot;
	}

	public int getTotalqty() {
		return totalqty;
	}

	public void setTotalqty(int totalqty) {
		this.totalqty = totalqty;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public String getIndidiscount() {
		return indidiscount;
	}

	public void setIndidiscount(String indidiscount) {
		this.indidiscount = indidiscount;
	}

	public String getTgstamt() {
		return tgstamt;
	}

	public void setTgstamt(String tgstamt) {
		this.tgstamt = tgstamt;
	}

	public String getGstper() {
		return gstper;
	}

	public void setGstper(String gstper) {
		this.gstper = gstper;
	}

	public String getSharediscount() {
		return sharediscount;
	}

	public void setSharediscount(String sharediscount) {
		this.sharediscount = sharediscount;
	}

	public String getTempids() {
		return tempids;
	}

	public void setTempids(String tempids) {
		this.tempids = tempids;
	}

	public double getCreditlimit() {
		return creditlimit;
	}

	public void setCreditlimit(double creditlimit) {
		this.creditlimit = creditlimit;
	}

	public boolean isCreditlimitaccess() {
		return creditlimitaccess;
	}

	public void setCreditlimitaccess(boolean creditlimitaccess) {
		this.creditlimitaccess = creditlimitaccess;
	}

	public String getDosenotetxt() {
		return dosenotetxt;
	}

	public void setDosenotetxt(String dosenotetxt) {
		this.dosenotetxt = dosenotetxt;
	}

	public String getEmrnote() {
		return emrnote;
	}

	public void setEmrnote(String emrnote) {
		this.emrnote = emrnote;
	}

	private String dddose;
	private String dosefreq;
	
	
	
	
	public String getDosefreq() {
		return dosefreq;
	}

	public void setDosefreq(String dosefreq) {
		this.dosefreq = dosefreq;
	}

	public String getDddose() {
		return dddose;
	}

	public void setDddose(String dddose) {
		this.dddose = dddose;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public int getShowindentreq() {
		return showindentreq;
	}

	public void setShowindentreq(int showindentreq) {
		this.showindentreq = showindentreq;
	}

	public String getTotalrefundrs() {
		return totalrefundrs;
	}

	public void setTotalrefundrs(String totalrefundrs) {
		this.totalrefundrs = totalrefundrs;
	}

	public String getLocationame() {
		return locationame;
	}

	public void setLocationame(String locationame) {
		this.locationame = locationame;
	}

	public boolean isMasterdosestatus() {
		return masterdosestatus;
	}

	public void setMasterdosestatus(boolean masterdosestatus) {
		this.masterdosestatus = masterdosestatus;
	}

	public String getMasterdose() {
		return masterdose;
	}

	public void setMasterdose(String masterdose) {
		this.masterdose = masterdose;
	}

	public String getIswbd() {
		return iswbd;
	}

	public void setIswbd(String iswbd) {
		this.iswbd = iswbd;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getCaldose() {
		return caldose;
	}

	public void setCaldose(String caldose) {
		this.caldose = caldose;
	}

	public double getDiscper() {
		return discper;
	}

	public void setDiscper(double discper) {
		this.discper = discper;
	}

	public String getPaymentdate() {
		return paymentdate;
	}

	public void setPaymentdate(String paymentdate) {
		this.paymentdate = paymentdate;
	}

	public double getCredit() {
		return credit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

	public double getNewcredit() {
		return newcredit;
	}

	public void setNewcredit(double newcredit) {
		this.newcredit = newcredit;
	}

	public double getTodayagainstcredit() {
		return todayagainstcredit;
	}

	public void setTodayagainstcredit(double todayagainstcredit) {
		this.todayagainstcredit = todayagainstcredit;
	}

	public double getTotalcreditReturn() {
		return totalcreditReturn;
	}

	public void setTotalcreditReturn(double totalcreditReturn) {
		this.totalcreditReturn = totalcreditReturn;
	}

	public double getTotalReceived() {
		return totalReceived;
	}

	public void setTotalReceived(double totalReceived) {
		this.totalReceived = totalReceived;
	}

	public double getTotalRefund() {
		return totalRefund;
	}

	public void setTotalRefund(double totalRefund) {
		this.totalRefund = totalRefund;
	}

	public double getTotalBalance() {
		return totalBalance;
	}

	public void setTotalBalance(double totalBalance) {
		this.totalBalance = totalBalance;
	}

	public String getTotalCreditbyuser() {
		return totalCreditbyuser;
	}

	public void setTotalCreditbyuser(String totalCreditbyuser) {
		this.totalCreditbyuser = totalCreditbyuser;
	}

	public double getNewpayment() {
		return newpayment;
	}

	public void setNewpayment(double newpayment) {
		this.newpayment = newpayment;
	}

	public String getNewpaymentmode() {
		return newpaymentmode;
	}

	public void setNewpaymentmode(String newpaymentmode) {
		this.newpaymentmode = newpaymentmode;
	}

	public String getMdicinenametxtnew() {
		return mdicinenametxtnew;
	}

	public void setMdicinenametxtnew(String mdicinenametxtnew) {
		this.mdicinenametxtnew = mdicinenametxtnew;
	}

	public int getCountforcal() {
		return countforcal;
	}

	public void setCountforcal(int countforcal) {
		this.countforcal = countforcal;
	}

	public int getRepeatstatus() {
		return repeatstatus;
	}

	public void setRepeatstatus(int repeatstatus) {
		this.repeatstatus = repeatstatus;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public ArrayList<Priscription> getChildprisclist() {
		return childprisclist;
	}

	public void setChildprisclist(ArrayList<Priscription> childprisclist) {
		this.childprisclist = childprisclist;
	}

	public String getPriscqty() {
		return priscqty;
	}

	public void setPriscqty(String priscqty) {
		this.priscqty = priscqty;
	}

	public String getIpdtimeshow1() {
		return ipdtimeshow1;
	}

	public void setIpdtimeshow1(String ipdtimeshow1) {
		this.ipdtimeshow1 = ipdtimeshow1;
	}

	public String getIpdtimeshow2() {
		return ipdtimeshow2;
	}

	public void setIpdtimeshow2(String ipdtimeshow2) {
		this.ipdtimeshow2 = ipdtimeshow2;
	}

	public String getIpdtimeshow3() {
		return ipdtimeshow3;
	}

	public void setIpdtimeshow3(String ipdtimeshow3) {
		this.ipdtimeshow3 = ipdtimeshow3;
	}

	public String getIsipdtimeshow() {
		return isipdtimeshow;
	}

	public void setIsipdtimeshow(String isipdtimeshow) {
		this.isipdtimeshow = isipdtimeshow;
	}

	public String getIsfromipd() {
		return isfromipd;
	}

	public void setIsfromipd(String isfromipd) {
		this.isfromipd = isfromipd;
	}

	public String getApprove_ponew() {
		return approve_ponew;
	}

	public void setApprove_ponew(String approve_ponew) {
		this.approve_ponew = approve_ponew;
	}

	public boolean isApprove_po() {
		return approve_po;
	}

	public void setApprove_po(boolean approve_po) {
		this.approve_po = approve_po;
	}

	public String getOpdipdprisc() {
		return opdipdprisc;
	}

	public void setOpdipdprisc(String opdipdprisc) {
		this.opdipdprisc = opdipdprisc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMedid() {
		return medid;
	}

	public void setMedid(String medid) {
		this.medid = medid;
	}

	public String getTotalid() {
		return totalid;
	}

	public void setTotalid(String totalid) {
		this.totalid = totalid;
	}

	public String getPrisc_status() {
		return prisc_status;
	}

	public void setPrisc_status(String prisc_status) {
		this.prisc_status = prisc_status;
	}

	public int getTotaldays() {
		return totaldays;
	}

	public void setTotaldays(int totaldays) {
		this.totaldays = totaldays;
	}

	public int getCurrdays() {
		return currdays;
	}

	public void setCurrdays(int currdays) {
		this.currdays = currdays;
	}

	public String getDosegiventime() {
		return dosegiventime;
	}

	public void setDosegiventime(String dosegiventime) {
		this.dosegiventime = dosegiventime;
	}

	public boolean isPrisctimestatus() {
		return prisctimestatus;
	}

	public void setPrisctimestatus(boolean prisctimestatus) {
		this.prisctimestatus = prisctimestatus;
	}

	public boolean isPriscreamrkstatus() {
		return priscreamrkstatus;
	}

	public void setPriscreamrkstatus(boolean priscreamrkstatus) {
		this.priscreamrkstatus = priscreamrkstatus;
	}

	public boolean isPriscunitstatus() {
		return priscunitstatus;
	}

	public void setPriscunitstatus(boolean priscunitstatus) {
		this.priscunitstatus = priscunitstatus;
	}

	public String getPrischildid() {
		return prischildid;
	}

	public void setPrischildid(String prischildid) {
		this.prischildid = prischildid;
	}

	public String getUnitextension() {
		return unitextension;
	}

	public void setUnitextension(String unitextension) {
		this.unitextension = unitextension;
	}

	public String getPrisctimename() {
		return prisctimename;
	}

	public void setPrisctimename(String prisctimename) {
		this.prisctimename = prisctimename;
	}

	public String getPriscindivisualremark() {
		return priscindivisualremark;
	}

	public void setPriscindivisualremark(String priscindivisualremark) {
		this.priscindivisualremark = priscindivisualremark;
	}

	public String getPriscriptiontime() {
		return priscriptiontime;
	}

	public void setPriscriptiontime(String priscriptiontime) {
		this.priscriptiontime = priscriptiontime;
	}

	public String getDispriscsrno() {
		return dispriscsrno;
	}

	public void setDispriscsrno(String dispriscsrno) {
		this.dispriscsrno = dispriscsrno;
	}

	public String getTotalchildmedids() {
		return totalchildmedids;
	}

	public void setTotalchildmedids(String totalchildmedids) {
		this.totalchildmedids = totalchildmedids;
	}

	public String getSrno() {
		return srno;
	}

	public void setSrno(String srno) {
		this.srno = srno;
	}

	public String getIndent_approve() {
		return indent_approve;
	}

	public void setIndent_approve(String indent_approve) {
		this.indent_approve = indent_approve;
	}

	public String getBillpayamt() {
		return billpayamt;
	}

	public void setBillpayamt(String billpayamt) {
		this.billpayamt = billpayamt;
	}

	public ArrayList<Priscription> getPriscriptionlist() {
		return priscriptionlist;
	}

	public void setPriscriptionlist(ArrayList<Priscription> priscriptionlist) {
		this.priscriptionlist = priscriptionlist;
	}

	public String getDeliverydate() {
		return deliverydate;
	}

	public void setDeliverydate(String deliverydate) {
		this.deliverydate = deliverydate;
	}

	public String getAveragetime() {
		return averagetime;
	}

	public void setAveragetime(String averagetime) {
		this.averagetime = averagetime;
	}

	private String deliveredStatus;
	public String getDeliveredStatus() {
		return deliveredStatus;
	}

	public void setDeliveredStatus(String deliveredStatus) {
		this.deliveredStatus = deliveredStatus;
	}

	private String urgentward;
	public String getUrgentward() {
		return urgentward;
	}

	public void setUrgentward(String urgentward) {
		this.urgentward = urgentward;
	}

	private String inventory_transfer;

	private String direct_transfer;
	private String check_stock_available;
	private String edit_bill;
	private String SMS;
	private String medicine_shedule="0";
	private String islogin;
	private int refundid=0;
	private String sale_bill;
	private String disscount;
	private String ledger;
	private String account;
	private String purchase_order;
	
	private int returnbillid=0;
	private String paynote;
	private int id;
	private String av="0";
	private int pharmacyUserType;
	private int isreturn=0;
	private String cardno;
	private String notes;
	private int returnbill=0;
	private String state="0";
	private String categoryid;
	private String mdicinenameid;
	private String abrivationid="0";
	private double totalcheque=0;
	private double totalneft=0;
	private String medicineid="0";
	private String cardAmount="0";
	private String dateTime="";
	private int edit_catalogue=0;
	private int access_back_date=0;
	private int deleted=0;
	private int tpbill=0;
	private int delete_catalogue=0;
	private String cgst="0";
	private int pack=0;
	private String sgst="0";
	private String requisition_auth;
	private int estimatebill=0;
	private int hasCardPay;
	private String mdicinenametxt;
	private String hsnno="";
	private String edit_purchaseorder="0";
	private String delete_purchaseorder="0";
	private double creditBalance=0;
	private String delete_bill="0";
	private String priscdose;
	private String priscfreq;
	private String ipdlocation="0";
	private String opdlocation="0";
	private String priscdays;
	private String shelf;
	private String product_id;

	private String billcount;
	private String payment;
	private String totalpayment;

	private String balance="0";
	private int outp;
	private String address;
	private String userid;
	private double totalrefund;
	private String clientId;
	private String prectionerid;
	private String department;
	private double totalamt=0,totaldisc=0;
	private String conditionid;
	private int refundamt=0;
	private String pclientid;
	private String date;
	private String practitionerid;
	private String tpid;
	private double debit;
	private double discount;
    private String expiry_date;
    private String pkg;
    private int returnqty;
    private String batch_no;
    private String mfg;
    private String paymode;
	private String prisctype;
	private String prisccode;
	private String prisctotal;;
    private String mobile;
	private String dosenotes;
	private String advoice;
	private String dosagenote="0";
	private String lastmodified;
	private String practitionername;
	private String billno;
	private String wardbed;
	double totalcash=0;
	double totalcard = 0;
	double totalReturn =0;
	double netcash=0;
	double netcard=0;
	double netrefund=0;
	double receivedtotal=0;
	double refundtotal=0;
	double nettotal=0;
	
	
	private String available;
	private int required;
	private int estimate;
	private int delivered;
	private String specialization="0";
	private String location="0";
	private String risk="0";
	private String genericname;
	private ArrayList<Priscription> batches;
	
	private double subtotal=0;
	private String vat;
	private double total=0;
	private int sstatus=0;
	
	private int saleqty;
	private int reqqty;
	private int freeqty=0;
	private String discount_perproduct="0";
	
	public String getDiscount_perproduct() {
		return discount_perproduct;
	}

	public void setDiscount_perproduct(String discount_perproduct) {
		this.discount_perproduct = discount_perproduct;
	}

	public int getFreeqty() {
		return freeqty;
	}

	public void setFreeqty(int freeqty) {
		this.freeqty = freeqty;
	}

	// single value
    private String fullname;
    private String ageandgender;
    
    private String name;
    private String description;    
    
    private String drug;
    private String strength;
    
    private String wardname;
    
    private String bedname;
    
    private String ipdid;
    
    //dosage variables
    private boolean dos1;
    private boolean dos2;
    private boolean dos3;
    private boolean dos4;
    private boolean dos5;
    private boolean dos6;
    private boolean dos7;
    private boolean dos8;
    private boolean dos9;
    private boolean dos10;
    
    //dosage variables
    private String dosch1;
    private String dosch2;
    private String dosch3;
    private String dosch4;
    private String dosch5;
    private String dosch6;
    private String dosch7;
    private String dosch8;
    private String dosch9;
    private String dosch10;
    
    
    private String parentid="0";
    
    private int days;
    
    private String dosage;
    
    private int dosesize;
    
    private String followupdate;
    private String inhousepatient;
    
    private String clientname;
    
    
    private String dosevalue1;
    private String dosevalue2;
    private String dosevalue3;
    private String dosevalue4;
    private String dosevalue5;
    private String dosevalue6;
    private String dosevalue7;
    private String dosevalue8;
    private String dosevalue9;
    private String dosevalue10;
    
    private String templatename;
    
    
    private String whopay;
    
    private String dstatus;
    
    private String mrp="0";
    private String sale_price="0";
    private String purchase_price="0";
    
    private int checkChargeRaised;
    private double ipdper;
	private double opdper;
	private double newper;
    
	private int checkInvoiceCreated;
	
	private int ipdpatients;
	private int opdpatients;
	private int newpatients;
	
	private String age;
	private String gender;
	private String return_stock;
	private String cancel_indent;
	
	//@  for pharmacy report
		private String creditReturn;
		private String totalcredit;
		private String hospitalReturn;
		private String todaycard;
		private String todaycash;
		private String todaydisc;
		private String todayReturn;
		private String neftpayment;
		/*private String totalpayment;
		private String balance;
		private String total;
		*/private String hospital;
		private String chequepayments;
		private String fromdate;
		private String todate;
	private String return_medicine;
	private String productname;
	private String username;
	private String return_qty;
	private String chargeid;
	private String returnstatus;
	
	public String getReturnstatus() {
		return returnstatus;
	}

	public void setReturnstatus(String returnstatus) {
		this.returnstatus = returnstatus;
	}

	public String getChargeid() {
		return chargeid;
	}

	public void setChargeid(String chargeid) {
		this.chargeid = chargeid;
	}

	public String getReturn_qty() {
		return return_qty;
	}

	public void setReturn_qty(String return_qty) {
		this.return_qty = return_qty;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getReturn_medicine() {
		return return_medicine;
	}

	public void setReturn_medicine(String return_medicine) {
		this.return_medicine = return_medicine;
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

	public String getCreditReturn() {
			return creditReturn;
		}

		public void setCreditReturn(String creditReturn) {
			this.creditReturn = creditReturn;
		}

		public String getTotalcredit() {
			return totalcredit;
		}

		public void setTotalcredit(String totalcredit) {
			this.totalcredit = totalcredit;
		}

		public String getHospitalReturn() {
			return hospitalReturn;
		}

		public void setHospitalReturn(String hospitalReturn) {
			this.hospitalReturn = hospitalReturn;
		}

		public String getTodaycard() {
			return todaycard;
		}

		public void setTodaycard(String todaycard) {
			this.todaycard = todaycard;
		}

		public String getTodaycash() {
			return todaycash;
		}

		public void setTodaycash(String todaycash) {
			this.todaycash = todaycash;
		}

		public String getTodaydisc() {
			return todaydisc;
		}

		public void setTodaydisc(String todaydisc) {
			this.todaydisc = todaydisc;
		}

		public String getTodayReturn() {
			return todayReturn;
		}

		public void setTodayReturn(String todayReturn) {
			this.todayReturn = todayReturn;
		}

		public String getNeftpayment() {
			return neftpayment;
		}

		public void setNeftpayment(String neftpayment) {
			this.neftpayment = neftpayment;
		}

		public String getHospital() {
			return hospital;
		}

		public void setHospital(String hospital) {
			this.hospital = hospital;
		}

		public String getChequepayments() {
			return chequepayments;
		}

		public void setChequepayments(String chequepayments) {
			this.chequepayments = chequepayments;
		}

	public String getCancel_indent() {
		return cancel_indent;
	}

	public void setCancel_indent(String cancel_indent) {
		this.cancel_indent = cancel_indent;
	}

	public String getReturn_stock() {
		return return_stock;
	}

	public void setReturn_stock(String return_stock) {
		this.return_stock = return_stock;
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

	public int getCheckInvoiceCreated() {
		return checkInvoiceCreated;
	}

	public void setCheckInvoiceCreated(int checkInvoiceCreated) {
		this.checkInvoiceCreated = checkInvoiceCreated;
	}

	public int getCheckChargeRaised() {
		return checkChargeRaised;
	}

	public void setCheckChargeRaised(int checkChargeRaised) {
		this.checkChargeRaised = checkChargeRaised;
	}

	public String getMrp() {
		return mrp;
	}

	public void setMrp(String mrp) {
		this.mrp = mrp;
	}

	public String getSale_price() {
		return sale_price;
	}

	public void setSale_price(String sale_price) {
		this.sale_price = sale_price;
	}

	public String getPurchase_price() {
		return purchase_price;
	}

	public void setPurchase_price(String purchase_price) {
		this.purchase_price = purchase_price;
	}

	public String getDstatus() {
		return dstatus;
	}

	public void setDstatus(String dstatus) {
		this.dstatus = dstatus;
	}

	public String getWhopay() {
		return whopay;
	}

	public void setWhopay(String whopay) {
		this.whopay = whopay;
	}

	public String getDosevalue1() {
		return dosevalue1;
	}

	public void setDosevalue1(String dosevalue1) {
		this.dosevalue1 = dosevalue1;
	}

	public String getDosevalue2() {
		return dosevalue2;
	}

	public void setDosevalue2(String dosevalue2) {
		this.dosevalue2 = dosevalue2;
	}

	public String getDosevalue3() {
		return dosevalue3;
	}

	public void setDosevalue3(String dosevalue3) {
		this.dosevalue3 = dosevalue3;
	}

	public String getDosevalue4() {
		return dosevalue4;
	}

	public void setDosevalue4(String dosevalue4) {
		this.dosevalue4 = dosevalue4;
	}

	public String getDosevalue5() {
		return dosevalue5;
	}

	public void setDosevalue5(String dosevalue5) {
		this.dosevalue5 = dosevalue5;
	}

	public String getDosevalue6() {
		return dosevalue6;
	}

	public void setDosevalue6(String dosevalue6) {
		this.dosevalue6 = dosevalue6;
	}

	public String getDosevalue7() {
		return dosevalue7;
	}

	public void setDosevalue7(String dosevalue7) {
		this.dosevalue7 = dosevalue7;
	}

	public String getDosevalue8() {
		return dosevalue8;
	}

	public void setDosevalue8(String dosevalue8) {
		this.dosevalue8 = dosevalue8;
	}

	public String getDosevalue9() {
		return dosevalue9;
	}

	public void setDosevalue9(String dosevalue9) {
		this.dosevalue9 = dosevalue9;
	}

	public String getDosevalue10() {
		return dosevalue10;
	}

	public void setDosevalue10(String dosevalue10) {
		this.dosevalue10 = dosevalue10;
	}

	public String getClientname() {
		return clientname;
	}

	public void setClientname(String clientname) {
		this.clientname = clientname;
	}

	public String getFollowupdate() {
		return followupdate;
	}

	public void setFollowupdate(String followupdate) {
		this.followupdate = followupdate;
	}

	public String getDosch1() {
		return dosch1;
	}

	public void setDosch1(String dosch1) {
		this.dosch1 = dosch1;
	}

	public String getDosch2() {
		return dosch2;
	}

	public void setDosch2(String dosch2) {
		this.dosch2 = dosch2;
	}

	public String getDosch3() {
		return dosch3;
	}

	public void setDosch3(String dosch3) {
		this.dosch3 = dosch3;
	}

	public String getDosch4() {
		return dosch4;
	}

	public void setDosch4(String dosch4) {
		this.dosch4 = dosch4;
	}

	public String getDosch5() {
		return dosch5;
	}

	public void setDosch5(String dosch5) {
		this.dosch5 = dosch5;
	}

	public String getDosch6() {
		return dosch6;
	}

	public void setDosch6(String dosch6) {
		this.dosch6 = dosch6;
	}

	public String getDosch7() {
		return dosch7;
	}

	public void setDosch7(String dosch7) {
		this.dosch7 = dosch7;
	}

	public String getDosch8() {
		return dosch8;
	}

	public void setDosch8(String dosch8) {
		this.dosch8 = dosch8;
	}

	public String getDosch9() {
		return dosch9;
	}

	public void setDosch9(String dosch9) {
		this.dosch9 = dosch9;
	}

	public String getDosch10() {
		return dosch10;
	}

	public void setDosch10(String dosch10) {
		this.dosch10 = dosch10;
	}

	public int getDosesize() {
		return dosesize;
	}

	public void setDosesize(int dosesize) {
		this.dosesize = dosesize;
	}

	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public boolean isDos1() {
		return dos1;
	}

	public void setDos1(boolean dos1) {
		this.dos1 = dos1;
	}

	public boolean isDos2() {
		return dos2;
	}

	public void setDos2(boolean dos2) {
		this.dos2 = dos2;
	}

	public boolean isDos3() {
		return dos3;
	}

	public void setDos3(boolean dos3) {
		this.dos3 = dos3;
	}

	public boolean isDos4() {
		return dos4;
	}

	public void setDos4(boolean dos4) {
		this.dos4 = dos4;
	}

	public boolean isDos5() {
		return dos5;
	}

	public void setDos5(boolean dos5) {
		this.dos5 = dos5;
	}

	public boolean isDos6() {
		return dos6;
	}

	public void setDos6(boolean dos6) {
		this.dos6 = dos6;
	}

	public boolean isDos7() {
		return dos7;
	}

	public void setDos7(boolean dos7) {
		this.dos7 = dos7;
	}

	public boolean isDos8() {
		return dos8;
	}

	public void setDos8(boolean dos8) {
		this.dos8 = dos8;
	}

	public boolean isDos9() {
		return dos9;
	}

	public void setDos9(boolean dos9) {
		this.dos9 = dos9;
	}

	public boolean isDos10() {
		return dos10;
	}

	public void setDos10(boolean dos10) {
		this.dos10 = dos10;
	}

	public String getIpdid() {
		return ipdid;
	}

	public void setIpdid(String ipdid) {
		this.ipdid = ipdid;
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

	public String getStrength() {
		return strength;
	}

	public void setStrength(String strength) {
		this.strength = strength;
	}

	public String getDrug() {
		return drug;
	}

	public void setDrug(String drug) {
		this.drug = drug;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAgeandgender() {
		return ageandgender;
	}

	public void setAgeandgender(String ageandgender) {
		this.ageandgender = ageandgender;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getDosenotes() {
		return dosenotes;
	}

	public String getLastmodified() {
		return lastmodified;
	}

	public void setLastmodified(String lastmodified) {
		this.lastmodified = lastmodified;
	}

	public String getAdvoice() {
		return advoice;
	}

	public void setAdvoice(String advoice) {
		this.advoice = advoice;
	}

	public void setDosenotes(String dosenotes) {
		this.dosenotes = dosenotes;
	}

	private String prepay;
	private String postpay;
	private String otherpay;
	private String priscautoid;
	private String priscdosenotes;
	private String followupsqty;
	private String followupstype;
	private String priscadvoice;
	private String english;
	private String regional;
	private String hindi;

	private String priscdurationtype;
	private int returnchargeid;
	
	public int getReturnchargeid() {
		return returnchargeid;
	}

	public void setReturnchargeid(int returnchargeid) {
		this.returnchargeid = returnchargeid;
	}

	public String getPriscdurationtype() {
		return priscdurationtype;
	}

	public void setPriscdurationtype(String priscdurationtype) {
		this.priscdurationtype = priscdurationtype;
	}

	public String getPrisctype() {
		return prisctype;
	}

	public void setPrisctype(String prisctype) {
		this.prisctype = prisctype;
	}

	public String getPrisccode() {
		return prisccode;
	}

	public void setPrisccode(String prisccode) {
		this.prisccode = prisccode;
	}

	public String getPrisctotal() {
		return prisctotal;
	}

	public void setPrisctotal(String prisctotal) {
		this.prisctotal = prisctotal;
	}

	public String getPrepay() {
		return prepay;
	}

	public void setPrepay(String prepay) {
		this.prepay = prepay;
	}

	public String getPostpay() {
		return postpay;
	}

	public void setPostpay(String postpay) {
		this.postpay = postpay;
	}

	public String getOtherpay() {
		return otherpay;
	}

	public void setOtherpay(String otherpay) {
		this.otherpay = otherpay;
	}

	public String getPriscautoid() {
		return priscautoid;
	}

	public void setPriscautoid(String priscautoid) {
		this.priscautoid = priscautoid;
	}

	public String getPriscdosenotes() {
		return priscdosenotes;
	}

	public void setPriscdosenotes(String priscdosenotes) {
		this.priscdosenotes = priscdosenotes;
	}

	public String getFollowupsqty() {
		return followupsqty;
	}

	public void setFollowupsqty(String followupsqty) {
		this.followupsqty = followupsqty;
	}

	public String getFollowupstype() {
		return followupstype;
	}

	public void setFollowupstype(String followupstype) {
		this.followupstype = followupstype;
	}

	public String getPriscadvoice() {
		return priscadvoice;
	}

	public void setPriscadvoice(String priscadvoice) {
		this.priscadvoice = priscadvoice;
	}

	public String getEnglish() {
		return english;
	}

	public void setEnglish(String english) {
		this.english = english;
	}

	public String getRegional() {
		return regional;
	}

	public void setRegional(String regional) {
		this.regional = regional;
	}

	public String getHindi() {
		return hindi;
	}

	public void setHindi(String hindi) {
		this.hindi = hindi;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getPrectionerid() {
		return prectionerid;
	}

	public void setPrectionerid(String prectionerid) {
		this.prectionerid = prectionerid;
	}

	public String getConditionid() {
		return conditionid;
	}

	public void setConditionid(String conditionid) {
		this.conditionid = conditionid;
	}

	public String getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}

	public String getMdicinenameid() {
		return mdicinenameid;
	}

	public void setMdicinenameid(String mdicinenameid) {
		this.mdicinenameid = mdicinenameid;
	}

	public String getMdicinenametxt() {
		return mdicinenametxt;
	}

	public void setMdicinenametxt(String mdicinenametxt) {
		this.mdicinenametxt = mdicinenametxt;
	}

	public String getPriscdose() {
		return priscdose;
	}

	public void setPriscdose(String priscdose) {
		this.priscdose = priscdose;
	}

	public String getPriscfreq() {
		return priscfreq;
	}

	public void setPriscfreq(String priscfreq) {
		this.priscfreq = priscfreq;
	}

	public String getPriscdays() {
		return priscdays;
	}

	public void setPriscdays(String priscdays) {
		this.priscdays = priscdays;
	}

	public String getExpiry_date() {
		return expiry_date;
	}

	public void setExpiry_date(String expiry_date) {
		this.expiry_date = expiry_date;
	}

	public String getPkg() {
		return pkg;
	}

	public void setPkg(String pkg) {
		this.pkg = pkg;
	}

	public String getBatch_no() {
		return batch_no;
	}

	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
	}

	public String getMfg() {
		return mfg;
	}

	public void setMfg(String mfg) {
		this.mfg = mfg;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPractitionername() {
		return practitionername;
	}

	public void setPractitionername(String practitionername) {
		this.practitionername = practitionername;
	}

	public String getBillno() {
		return billno;
	}

	public void setBillno(String billno) {
		this.billno = billno;
	}

	public String getWardbed() {
		return wardbed;
	}

	public void setWardbed(String wardbed) {
		this.wardbed = wardbed;
	}

	public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}

	public int getRequired() {
		return required;
	}

	public void setRequired(int required) {
		this.required = required;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public String getVat() {
		return vat;
	}

	public void setVat(String vat) {
		this.vat = vat;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getSstatus() {
		return sstatus;
	}

	public void setSstatus(int sstatus) {
		this.sstatus = sstatus;
	}

	public int getSaleqty() {
		return saleqty;
	}

	public void setSaleqty(int saleqty) {
		this.saleqty = saleqty;
	}

	public int getReqqty() {
		return reqqty;
	}

	public void setReqqty(int reqqty) {
		this.reqqty = reqqty;
	}

	public ArrayList<Priscription> getBatches() {
		return batches;
	}

	public void setBatches(ArrayList<Priscription> batches) {
		this.batches = batches;
	}

	public int getEstimate() {
		return estimate;
	}

	public void setEstimate(int estimate) {
		this.estimate = estimate;
	}

	public int getDelivered() {
		return delivered;
	}

	public void setDelivered(int delivered) {
		this.delivered = delivered;
	}

	public String getPclientid() {
		return pclientid;
	}

	public void setPclientid(String pclientid) {
		this.pclientid = pclientid;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTemplatename() {
		return templatename;
	}

	public void setTemplatename(String templatename) {
		this.templatename = templatename;
	}

	public int getOutp() {
		return outp;
	}

	public void setOutp(int outp) {
		this.outp = outp;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getReturnqty() {
		return returnqty;
	}

	public void setReturnqty(int returnqty) {
		this.returnqty = returnqty;
	}

	public int getRefundamt() {
		return refundamt;
	}

	public void setRefundamt(int refundamt) {
		this.refundamt = refundamt;
	}

	public String getTpid() {
		return tpid;
	}

	public void setTpid(String tpid) {
		this.tpid = tpid;
	}

	public String getPaymode() {
		return paymode;
	}

	public void setPaymode(String paymode) {
		this.paymode = paymode;
	}

	public double getDebit() {
		return debit;
	}

	public void setDebit(double debit) {
		this.debit = debit;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getTotalamt() {
		return totalamt;
	}

	public void setTotalamt(double totalamt) {
		this.totalamt = totalamt;
	}

	public double getTotaldisc() {
		return totaldisc;
	}

	public void setTotaldisc(double totaldisc) {
		this.totaldisc = totaldisc;
	}

	public double getTotalrefund() {
		return totalrefund;
	}

	public void setTotalrefund(double totalrefund) {
		this.totalrefund = totalrefund;
	}

	public int getIpdpatients() {
		return ipdpatients;
	}

	public void setIpdpatients(int ipdpatients) {
		this.ipdpatients = ipdpatients;
	}

	public int getOpdpatients() {
		return opdpatients;
	}

	public void setOpdpatients(int opdpatients) {
		this.opdpatients = opdpatients;
	}

	public int getNewpatients() {
		return newpatients;
	}

	public void setNewpatients(int newpatients) {
		this.newpatients = newpatients;
	}

	public double getIpdper() {
		return ipdper;
	}

	public void setIpdper(double ipdper) {
		this.ipdper = ipdper;
	}

	public double getOpdper() {
		return opdper;
	}

	public void setOpdper(double opdper) {
		this.opdper = opdper;
	}

	public double getNewper() {
		return newper;
	}

	public void setNewper(double newper) {
		this.newper = newper;
	}

	public String getPractitionerid() {
		return practitionerid;
	}

	public void setPractitionerid(String practitionerid) {
		this.practitionerid = practitionerid;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getRisk() {
		return risk;
	}

	public void setRisk(String risk) {
		this.risk = risk;
	}

	public String getGenericname() {
		return genericname;
	}

	public void setGenericname(String genericname) {
		this.genericname = genericname;
	}

	public String getShelf() {
		return shelf;
	}

	public void setShelf(String shelf) {
		this.shelf = shelf;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getBillcount() {
		return billcount;
	}

	public void setBillcount(String billcount) {
		this.billcount = billcount;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getTotalpayment() {
		return totalpayment;
	}

	public void setTotalpayment(String totalpayment) {
		this.totalpayment = totalpayment;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getIslogin() {
		return islogin;
	}

	public void setIslogin(String islogin) {
		this.islogin = islogin;
	}

	public String getSale_bill() {
		return sale_bill;
	}

	public void setSale_bill(String sale_bill) {
		this.sale_bill = sale_bill;
	}

	public String getDisscount() {
		return disscount;
	}

	public void setDisscount(String disscount) {
		this.disscount = disscount;
	}

	public String getLedger() {
		return ledger;
	}

	public void setLedger(String ledger) {
		this.ledger = ledger;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPurchase_order() {
		return purchase_order;
	}

	public void setPurchase_order(String purchase_order) {
		this.purchase_order = purchase_order;
	}

	public String getMedicine_shedule() {
		return medicine_shedule;
	}

	public void setMedicine_shedule(String medicine_shedule) {
		this.medicine_shedule = medicine_shedule;
	}

	public String getSMS() {
		return SMS;
	}

	public void setSMS(String sms) {
		SMS = sms;
	}

	public String getCardAmount() {
		return cardAmount;
	}

	public void setCardAmount(String cardAmount) {
		this.cardAmount = cardAmount;
	}

	public int getHasCardPay() {
		return hasCardPay;
	}

	public void setHasCardPay(int hasCardPay) {
		this.hasCardPay = hasCardPay;
	}

	public String getEdit_bill() {
		return edit_bill;
	}

	public void setEdit_bill(String edit_bill) {
		this.edit_bill = edit_bill;
	}

	public String getDelete_bill() {
		return delete_bill;
	}

	public void setDelete_bill(String delete_bill) {
		this.delete_bill = delete_bill;
	}

	public String getIpdlocation() {
		return ipdlocation;
	}

	public void setIpdlocation(String ipdlocation) {
		this.ipdlocation = ipdlocation;
	}

	public String getOpdlocation() {
		return opdlocation;
	}

	public void setOpdlocation(String opdlocation) {
		this.opdlocation = opdlocation;
	}

	public String getHsnno() {
		return hsnno;
	}

	public void setHsnno(String hsnno) {
		this.hsnno = hsnno;
	}

	public String getEdit_purchaseorder() {
		return edit_purchaseorder;
	}

	public void setEdit_purchaseorder(String edit_purchaseorder) {
		this.edit_purchaseorder = edit_purchaseorder;
	}

	public String getDelete_purchaseorder() {
		return delete_purchaseorder;
	}

	public void setDelete_purchaseorder(String delete_purchaseorder) {
		this.delete_purchaseorder = delete_purchaseorder;
	}

	public String getCgst() {
		return cgst;
	}

	public void setCgst(String cgst) {
		this.cgst = cgst;
	}

	public String getSgst() {
		return sgst;
	}

	public void setSgst(String sgst) {
		this.sgst = sgst;
	}

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public double getTotalcash() {
		return totalcash;
	}

	public void setTotalcash(double totalcash) {
		this.totalcash = totalcash;
	}

	public double getTotalcard() {
		return totalcard;
	}

	public void setTotalcard(double totalcard) {
		this.totalcard = totalcard;
	}

	public double getTotalReturn() {
		return totalReturn;
	}

	public void setTotalReturn(double totalReturn) {
		this.totalReturn = totalReturn;
	}

	public int getReturnbill() {
		return returnbill;
	}

	public void setReturnbill(int returnbill) {
		this.returnbill = returnbill;
	}

	public double getNetcash() {
		return netcash;
	}

	public void setNetcash(double netcash) {
		this.netcash = netcash;
	}

	public double getNetcard() {
		return netcard;
	}

	public void setNetcard(double netcard) {
		this.netcard = netcard;
	}

	public double getNetrefund() {
		return netrefund;
	}

	public void setNetrefund(double netrefund) {
		this.netrefund = netrefund;
	}

	public double getReceivedtotal() {
		return receivedtotal;
	}

	public void setReceivedtotal(double receivedtotal) {
		this.receivedtotal = receivedtotal;
	}

	public double getRefundtotal() {
		return refundtotal;
	}

	public void setRefundtotal(double refundtotal) {
		this.refundtotal = refundtotal;
	}

	public double getNettotal() {
		return nettotal;
	}

	public void setNettotal(double nettotal) {
		this.nettotal = nettotal;
	}

	public String getInhousepatient() {
		return inhousepatient;
	}

	public void setInhousepatient(String inhousepatient) {
		this.inhousepatient = inhousepatient;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public double getCreditBalance() {
		return creditBalance;
	}

	public void setCreditBalance(double creditBalance) {
		this.creditBalance = creditBalance;
	}

	public int getReturnbillid() {
		return returnbillid;
	}

	public void setReturnbillid(int returnbillid) {
		this.returnbillid = returnbillid;
	}

	public int getEdit_catalogue() {
		return edit_catalogue;
	}

	public void setEdit_catalogue(int edit_catalogue) {
		this.edit_catalogue = edit_catalogue;
	}

	public int getDelete_catalogue() {
		return delete_catalogue;
	}

	public void setDelete_catalogue(int delete_catalogue) {
		this.delete_catalogue = delete_catalogue;
	}

	public int getEstimatebill() {
		return estimatebill;
	}

	public void setEstimatebill(int estimatebill) {
		this.estimatebill = estimatebill;
	}

	public int getPack() {
		return pack;
	}

	public void setPack(int pack) {
		this.pack = pack;
	}

	public String getAv() {
		return av;
	}

	public void setAv(String av) {
		this.av = av;
	}

	public int getAccess_back_date() {
		return access_back_date;
	}

	public void setAccess_back_date(int access_back_date) {
		this.access_back_date = access_back_date;
	}

	public int getTpbill() {
		return tpbill;
	}

	public void setTpbill(int tpbill) {
		this.tpbill = tpbill;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public String getRequisition_auth() {
		return requisition_auth;
	}

	public void setRequisition_auth(String requisition_auth) {
		this.requisition_auth = requisition_auth;
	}

	public String getMedicineid() {
		return medicineid;
	}

	public void setMedicineid(String medicineid) {
		this.medicineid = medicineid;
	}

	public double getTotalcheque() {
		return totalcheque;
	}

	public void setTotalcheque(double totalcheque) {
		this.totalcheque = totalcheque;
	}

	public double getTotalneft() {
		return totalneft;
	}

	public void setTotalneft(double totalneft) {
		this.totalneft = totalneft;
	}

	public String getCheck_stock_available() {
		return check_stock_available;
	}

	public void setCheck_stock_available(String check_stock_available) {
		this.check_stock_available = check_stock_available;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getAbrivationid() {
		return abrivationid;
	}

	public void setAbrivationid(String abrivationid) {
		this.abrivationid = abrivationid;
	}
	public String getDirect_transfer() {
		return direct_transfer;
	}

	public void setDirect_transfer(String direct_transfer) {
		this.direct_transfer = direct_transfer;
	}

	public int getPharmacyUserType() {
		return pharmacyUserType;
	}

	public void setPharmacyUserType(int pharmacyUserType) {
		this.pharmacyUserType = pharmacyUserType;
	}

	public int getIsreturn() {
		return isreturn;
	}

	public void setIsreturn(int isreturn) {
		this.isreturn = isreturn;
	}

	public String getPaynote() {
		return paynote;
	}

	public void setPaynote(String paynote) {
		this.paynote = paynote;
	}
	

	public String getInventory_transfer() {
		return inventory_transfer;
	}

	public void setInventory_transfer(String inventory_transfer) {
		this.inventory_transfer = inventory_transfer;
	}

	public int getRefundid() {
		return refundid;
	}

	public void setRefundid(int refundid) {
		this.refundid = refundid;
	}

	public String getDosagenote() {
		return dosagenote;
	}

	public void setDosagenote(String dosagenote) {
		this.dosagenote = dosagenote;
	}

	public String getMolecules() {
		return molecules;
	}

	public void setMolecules(String molecules) {
		this.molecules = molecules;
	}

	public int getOstatus() {
		return ostatus;
	}

	public void setOstatus(int ostatus) {
		this.ostatus = ostatus;
	}

	public String getDosetime1() {
		return dosetime1;
	}

	public void setDosetime1(String dosetime1) {
		this.dosetime1 = dosetime1;
	}

	public String getDosetime2() {
		return dosetime2;
	}

	public void setDosetime2(String dosetime2) {
		this.dosetime2 = dosetime2;
	}

	public String getDosetime3() {
		return dosetime3;
	}

	public void setDosetime3(String dosetime3) {
		this.dosetime3 = dosetime3;
	}

	public String getDosetime4() {
		return dosetime4;
	}

	public void setDosetime4(String dosetime4) {
		this.dosetime4 = dosetime4;
	}

	public String getDosetime5() {
		return dosetime5;
	}

	public void setDosetime5(String dosetime5) {
		this.dosetime5 = dosetime5;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getStrengthnew() {
		return strengthnew;
	}

	public void setStrengthnew(String strengthnew) {
		this.strengthnew = strengthnew;
	}

	private boolean strengthflag=false;
	private boolean quantityflag= false;
	private boolean remarkflag= false;

	public boolean isStrengthflag() {
		return strengthflag;
	}

	public void setStrengthflag(boolean strengthflag) {
		this.strengthflag = strengthflag;
	}

	public boolean isQuantityflag() {
		return quantityflag;
	}

	public void setQuantityflag(boolean quantityflag) {
		this.quantityflag = quantityflag;
	}

	public boolean isRemarkflag() {
		return remarkflag;
	}

	public void setRemarkflag(boolean remarkflag) {
		this.remarkflag = remarkflag;
	}

	public String getDr_qty() {
		return dr_qty;
	}

	public void setDr_qty(String dr_qty) {
		this.dr_qty = dr_qty;
	}

	public boolean isPurchase_edit() {
		return purchase_edit;
	}

	public void setPurchase_edit(boolean purchase_edit) {
		this.purchase_edit = purchase_edit;
	}

	public String getCancel_po() {
		return cancel_po;
	}

	public void setCancel_po(String cancel_po) {
		this.cancel_po = cancel_po;
	}  	
	public String getPurchaseedit() {
		return purchaseedit;
	}

	public void setPurchaseedit(String purchaseedit) {
		this.purchaseedit = purchaseedit;
	}
public String getPrisctypename() {
		return prisctypename;
	}

	public void setPrisctypename(String prisctypename) {
		this.prisctypename = prisctypename;
	}

public boolean isIsdotmatrix() {
		return isdotmatrix;
	}

	public void setIsdotmatrix(boolean isdotmatrix) {
		this.isdotmatrix = isdotmatrix;
	}

public boolean isPrintflag() {
		return printflag;
	}

	public void setPrintflag(boolean printflag) {
		this.printflag = printflag;
	}



public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

public double getCharges() {
		return charges;
	}

	public void setCharges(double charges) {
		this.charges = charges;
	}

public int getSumqty() {
		return sumqty;
	}

	public void setSumqty(int sumqty) {
		this.sumqty = sumqty;
	}

public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

private String prisctypename;
private String purchaseedit;
private boolean isdotmatrix;
private boolean printflag;
private double charges;
private int qty;
private int sumqty;
private String remark;
private int five=0, tweelve=0,eighteen=0,zero=0;



public int getFive() {
	return five;
}

public void setFive(int five) {
	this.five = five;
}

public int getTweelve() {
	return tweelve;
}

public void setTweelve(int tweelve) {
	this.tweelve = tweelve;
}

public int getEighteen() {
	return eighteen;
}

public void setEighteen(int eighteen) {
	this.eighteen = eighteen;
}

public int getZero() {
	return zero;
}

public void setZero(int zero) {
	this.zero = zero;
}
private double fiveprice=0, tweelveprice=0,eighteenprice=0,zeroprice=0;



public double getFiveprice() {
	return fiveprice;
}

public void setFiveprice(double fiveprice) {
	this.fiveprice = fiveprice;
}

public double getTweelveprice() {
	return tweelveprice;
}

public void setTweelveprice(double tweelveprice) {
	this.tweelveprice = tweelveprice;
}

public double getEighteenprice() {
	return eighteenprice;
}

public void setEighteenprice(double eighteenprice) {
	this.eighteenprice = eighteenprice;
}

public double getZeroprice() {
	return zeroprice;
}

public double getTweelvepricemed() {
	return tweelvepricemed;
}

public void setTweelvepricemed(double tweelvepricemed) {
	this.tweelvepricemed = tweelvepricemed;
}

public double getEighteenpricemed() {
	return eighteenpricemed;
}

public void setEighteenpricemed(double eighteenpricemed) {
	this.eighteenpricemed = eighteenpricemed;
}

public double getZeropricemed() {
	return zeropricemed;
}

public void setZeropricemed(double zeropricemed) {
	this.zeropricemed = zeropricemed;
}

public void setZeroprice(double zeroprice) {
	this.zeroprice = zeroprice;
}
public double getFivepricemed() {
	return fivepricemed;
}

public void setFivepricemed(double fivepricemed) {
	this.fivepricemed = fivepricemed;
}

public int getEighttwo() {
	return eighttwo;
}

public void setEighttwo(int eighttwo) {
	this.eighttwo = eighttwo;
}

public double getEighttwoprice() {
	return eighttwoprice;
}

public void setEighttwoprice(double eighttwoprice) {
	this.eighttwoprice = eighttwoprice;
}

public double getEighttwopricemed() {
	return eighttwopricemed;
}

public void setEighttwopricemed(double eighttwopricemed) {
	this.eighttwopricemed = eighttwopricemed;
}

private double fivepricemed=0, tweelvepricemed=0,eighteenpricemed=0,zeropricemed=0;
private int eighttwo=0;
private double eighttwoprice=0, eighttwopricemed=0;
private double fivecgst=0, tweelvecgst=0,eighteencgst=0,zerocgst=0,eighttwocgst=0;
public double getFivecgst() {
	return fivecgst;
}

public void setFivecgst(double fivecgst) {
	this.fivecgst = fivecgst;
}

public double getTweelvecgst() {
	return tweelvecgst;
}

public void setTweelvecgst(double tweelvecgst) {
	this.tweelvecgst = tweelvecgst;
}

public double getEighteencgst() {
	return eighteencgst;
}

public void setEighteencgst(double eighteencgst) {
	this.eighteencgst = eighteencgst;
}

public double getZerocgst() {
	return zerocgst;
}

public void setZerocgst(double zerocgst) {
	this.zerocgst = zerocgst;
}

public double getEighttwocgst() {
	return eighttwocgst;
}

public void setEighttwocgst(double eighttwocgst) {
	this.eighttwocgst = eighttwocgst;
}

public double getFivesgst() {
	return fivesgst;
}

public void setFivesgst(double fivesgst) {
	this.fivesgst = fivesgst;
}

public double getTweelvesgst() {
	return tweelvesgst;
}

public void setTweelvesgst(double tweelvesgst) {
	this.tweelvesgst = tweelvesgst;
}

public double getEighteensgst() {
	return eighteensgst;
}

public void setEighteensgst(double eighteensgst) {
	this.eighteensgst = eighteensgst;
}

public double getZerosgst() {
	return zerosgst;
}

public void setZerosgst(double zerosgst) {
	this.zerosgst = zerosgst;
}

public double getEighttwosgst() {
	return eighttwosgst;
}

public void setEighttwosgst(double eighttwosgst) {
	this.eighttwosgst = eighttwosgst;
}

private double fivesgst=0, tweelvesgst=0,eighteensgst=0,zerosgst=0,eighttwosgst=0;
private double fivenomrp=0, tweelvenomrp=0,eighteennomrp=0,zeronomrp=0,eighttwonomrp=0;



public double getFivenomrp() {
	return fivenomrp;
}

public void setFivenomrp(double fivenomrp) {
	this.fivenomrp = fivenomrp;
}

public double getTweelvenomrp() {
	return tweelvenomrp;
}

public void setTweelvenomrp(double tweelvenomrp) {
	this.tweelvenomrp = tweelvenomrp;
}

public double getEighteennomrp() {
	return eighteennomrp;
}

public void setEighteennomrp(double eighteennomrp) {
	this.eighteennomrp = eighteennomrp;
}

public double getZeronomrp() {
	return zeronomrp;
}

public void setZeronomrp(double zeronomrp) {
	this.zeronomrp = zeronomrp;
}

public double getEighttwonomrp() {
	return eighttwonomrp;
}

public void setEighttwonomrp(double eighttwonomrp) {
	this.eighttwonomrp = eighttwonomrp;
}
public boolean isPharm_print_backdate() {
	return pharm_print_backdate;
}

public void setPharm_print_backdate(boolean pharm_print_backdate) {
	this.pharm_print_backdate = pharm_print_backdate;
}

public int getNewqty() {
	return newqty;
}

public void setNewqty(int newqty) {
	this.newqty = newqty;
}



public boolean isPriscremark() {
	return priscremark;
}

public void setPriscremark(boolean priscremark) {
	this.priscremark = priscremark;
}

public double getTotalgst() {
	return totalgst;
}

public void setTotalgst(double totalgst) {
	this.totalgst = totalgst;
}

public double getTotalreturnneft() {
	return totalreturnneft;
}

public void setTotalreturnneft(double totalreturnneft) {
	this.totalreturnneft = totalreturnneft;
}

public double getTotalneftamtrec() {
	return totalneftamtrec;
}

public void setTotalneftamtrec(double totalneftamtrec) {
	this.totalneftamtrec = totalneftamtrec;
}



public double getTotalneftnetamt() {
	return totalneftnetamt;
}

public void setTotalneftnetamt(double totalneftnetamt) {
	this.totalneftnetamt = totalneftnetamt;
}

private boolean pharm_print_backdate;
private int newqty;
private boolean priscremark;
private double totalgst;
private double totalreturnneft;
private double totalneftamtrec;
private double totalneftnetamt;
private double ttlgst;
private double ttlnet;
private double ttlgross;


public double getTtlgst() {
	return ttlgst;
}

public void setTtlgst(double ttlgst) {
	this.ttlgst = ttlgst;
}

public double getTtlnet() {
	return ttlnet;
}

public void setTtlnet(double ttlnet) {
	this.ttlnet = ttlnet;
}

public double getTtlgross() {
	return ttlgross;
}

public void setTtlgross(double ttlgross) {
	this.ttlgross = ttlgross;
}

public ArrayList<Priscription> getInnerlist() {
	return innerlist;
}

public void setInnerlist(ArrayList<Priscription> innerlist) {
	this.innerlist = innerlist;
}

public String getNweexp() {
	return nweexp;
}

public void setNweexp(String nweexp) {
	this.nweexp = nweexp;
}

public double getBroughtfwd() {
	return broughtfwd;
}

public void setBroughtfwd(double broughtfwd) {
	this.broughtfwd = broughtfwd;
}

public String getTotalstr() {
	return totalstr;
}

public void setTotalstr(String totalstr) {
	this.totalstr = totalstr;
}

public String getFromtreatmentgivenstatus() {
	return fromtreatmentgivenstatus;
}

public void setFromtreatmentgivenstatus(String fromtreatmentgivenstatus) {
	this.fromtreatmentgivenstatus = fromtreatmentgivenstatus;
}

private ArrayList<Priscription> innerlist;
private String nweexp;
private double broughtfwd;
private String totalstr;
private String fromtreatmentgivenstatus;
private String gstin,dlno;
private String duration;
}
