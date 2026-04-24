/**
 * 
 */
package com.apm.Inventory.eu.entity;

import java.util.ArrayList;
import java.util.Collection;

import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.Master.eu.entity.Master;

/**
 * @author jitu
 *
 */
public class Product implements Comparable<Product> {
	private int edit_catalogue;
	
	private int edit=0;
	
	public int getEdit_catalogue() {
		return edit_catalogue;
	}
	public void setEdit_catalogue(int edit_catalogue) {
		this.edit_catalogue = edit_catalogue;
	}
	private String stripcheck;
	private String transfer_indentno;
	private String issueqty="0";
	private boolean checked;
	private int procurement=0;
	private String rate;
	private String grnreturnid="0";
	private int balqty=0;
	private String transtype;
	private int totalindent;
	private String newexpirydate;
	private String totaltransferqt;
	private String cancel_req;
	private String cancel_req_note;
	private String admin_approve_remark;
	private ArrayList<Product> checkmedicinelist ;
	private  ArrayList<Product> requestedmedicineList;
	private String requested_qty;
	private String purpriceqty;
	private String salepriceqty;
	private int totalstock;
	private double totalpurchaseprice;
	private double avgcost;
	private String lastgrndate;
	private ArrayList<Product> materiallist;
	private String added_date;
	private String issueproceid;
	private String issueuserid;
	private String hisuserfilter;
	private String netamount;
	private String totalnetamount;
	private String gstamount;
	private String totalgstamount;
	
	private String totalmrp ="0";
	private String totalsaleprice="0";
	private double totalqty=0;  //int to double
	private String fromwhere;
	private int saleqty;
	private String isedit;
	private  ArrayList<Master> shelfList;
	private String hisdepartmentfilter;
	private String pro_code;
	private String tempname;
	private String given_date;
	private int prodid;
	private int procid;
	private int amtreturnstatus;
	private int indentqty;
	private String unitprices;
	private String sv;
	private int retrunqtycount;
	private int totalsumqty;
	private double salevalue;
	private String proSeqNo;
	private String qtyinvalue;
	private double returnfreeqty;
	private double returnqty;
	private int remainfreereturnqty;
	private int remainsqty;
	private String proc_childid;
	private ArrayList<Vendor> vendorlist;
	private String ids;
	//int to double
	private double unknownqty;
	private int is_po_prod;
	private int grnwithpo_child;
	private int newprocurementid;
	private int parentpoid;
	private double purchaseqty;
	private double totalopeningstock =0;
	private double totalopeingstockvalue =0;
	private double totalqtyin =0;
	private double totalqtyinvalue=0;
	//int to double
	private double totalqtyout=0;
	private double totalstockvalue=0;
	private double totalssaleprice=0;
	//int to double
	private double totalclosingstock=0;
	private double totalclosingvalue=0;
	private String returnlocation;
	private String productlocation;
	private String prductlistnew;
	private double totalpaidamt=0;
	private int vatratee=0;
	private double taxableamt;
	private double taxamts;
	private double ttaxableamt;
	private double ttaxamt;
	
	private String zerotaxamt="0";
	private String fivetaxamt ="0";
	private String fivesgst ="0";
	private String fivecgst ="0";
	private String fiveigst ="0";
	private String twelvetaxamt ="0";
	private String twelvesgst ="0";
	private String twelvecgst ="0";
	private String twelveigst ="0";
	private String eighteentaxamt ="0";
	private String eighteensgst ="0";
	private String eighteencgst ="0";
	private String eighteenigst ="0";
	private String twenteightaxamt ="0";
	private String twenteightsgst ="0";
	private String twenteightcgst ="0";
	private String twenteightigst ="0";
	private String netamt="0";
	private double totalunknownqty;
	private double grnqtyamtttl;
	private double grnfreeqtyamtttl;
	private double totalsubtotal;
	//int to double
	private double consumed;
	private String cancel_userid,cancel_date,cancel_notes;
	private String current_qty, previous_qty;
	private int global_prodid;
	private double unknownvalue;
	private double totalunknownvalue;
	private String new_mrp;
	private double closing;
	private String subject_msg;
	private String issue_depart;
	private String barcode;
	private double salevaluation;
	private double totalsalevaluation;
	private String grn_pre_pack, grn_new_pack,grn_pre_freeqty, grn_new_freeqty, grn_pre_stock, grn_new_stock,grn_pre_qty, grn_new_qty;
	private double vatamt;
	private String frommonth="";
	private String tomonth="";
	private String mail_content;
	private boolean hidecalinpoprint;
	private int deilverproduct;
	private int grn_with_po_tempid;
	private String secondary_shelf;
	private int previouscellid;
	private int previousseccellid;
	private  ArrayList<Master> secondshelfList;
	private String approve_date;
	private int isinadjustment;
	private String preexpcolor;
	private String currexpcolor;
	
	private String component;
	private String phar_drid;
	
	private double indi_purchase_gst;
	private int vaccinationid;
	private String abrivationid;
	private int isinventory;
	private String receipt_userid;
	private String receipt_datetime;
	private int main_status;
	private String pur_price;
	private String poHsnno;
	private String poDescription;
	
	private String noteSheetNo,rateContractNo,poDepartmentId;
	private String poDepartment;
	private String poSequence;
	private String uom;
	private String supplierRefNo;
	private double store_avail_stock;
	private String isfromworkorder;
	private String noteSheetDate;
	private String vendorcode;
	private double margin;
	private String req_limit_qty="0";
	private double limit_qty=0;
	private String approve_qty_status;
	private String req_dept_id;
	private double extra_limit_qty=0;
	private String Third_party_name;
	private String issue_date;
	
	
	
	public String getIssue_date() {
		return issue_date;
	}
	public void setIssue_date(String issue_date) {
		this.issue_date = issue_date;
	}
	public String getThird_party_name() {
		return Third_party_name;
	}
	public void setThird_party_name(String third_party_name) {
		Third_party_name = third_party_name;
	}
	public double getExtra_limit_qty() {
		return extra_limit_qty;
	}
	public void setExtra_limit_qty(double extra_limit_qty) {
		this.extra_limit_qty = extra_limit_qty;
	}
	public String getReq_dept_id() {
		return req_dept_id;
	}
	public void setReq_dept_id(String req_dept_id) {
		this.req_dept_id = req_dept_id;
	}
	public String getApprove_qty_status() {
		return approve_qty_status;
	}
	public void setApprove_qty_status(String approve_qty_status) {
		this.approve_qty_status = approve_qty_status;
	}
	public double getMargin() {
		return margin;
	}
	public String getReq_limit_qty() {
		return req_limit_qty;
	}
	public void setReq_limit_qty(String req_limit_qty) {
		this.req_limit_qty = req_limit_qty;
	}
	public double getLimit_qty() {
		return limit_qty;
	}
	public void setLimit_qty(double limit_qty) {
		this.limit_qty = limit_qty;
	}
	public void setMargin(double margin) {
		this.margin = margin;
	}
	public String getVendorcode() {
		return vendorcode;
	}
	public void setVendorcode(String vendorcode) {
		this.vendorcode = vendorcode;
	}
	public String getSuppliercode() {
		return suppliercode;
	}
	public void setSuppliercode(String suppliercode) {
		this.suppliercode = suppliercode;
	}
	private String durationFrom,durationTo,amcCmc;
	
	private String gatePassType;
	private String vehicleNumber;
	private String dateOfReturn;
	private String approximateValue;
	private String supplierId;
	private String sequence;
	private String suppliercode;
	private int modify_status;
	private String department;
	private boolean isduty;
	private String accepted_time;
	private String dmno;
	private String scheme="0";
	private String totalschmedisc="0";
	private String amountafterdisc="0";
	
	public String getAmountafterdisc() {
		return amountafterdisc;
	}
	public void setAmountafterdisc(String amountafterdisc) {
		this.amountafterdisc = amountafterdisc;
	}
	public String getTotalschmedisc() {
		return totalschmedisc;
	}
	public void setTotalschmedisc(String totalschmedisc) {
		this.totalschmedisc = totalschmedisc;
	}
	public String getScheme() {
		return scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	public String getDmno() {
		return dmno;
	}
	public void setDmno(String dmno) {
		this.dmno = dmno;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public boolean isIsduty() {
		return isduty;
	}
	public void setIsduty(boolean isduty) {
		this.isduty = isduty;
	}
	public String getAccepted_time() {
		return accepted_time;
	}
	public void setAccepted_time(String accepted_time) {
		this.accepted_time = accepted_time;
	}
	public int getModify_status() {
		return modify_status;
	}
	public void setModify_status(int modify_status) {
		this.modify_status = modify_status;
	}
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public String getGatePassType() {
		return gatePassType;
	}
	public void setGatePassType(String gatePassType) {
		this.gatePassType = gatePassType;
	}
	public String getVehicleNumber() {
		return vehicleNumber;
	}
	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}
	public String getDateOfReturn() {
		return dateOfReturn;
	}
	public void setDateOfReturn(String dateOfReturn) {
		this.dateOfReturn = dateOfReturn;
	}
	public String getApproximateValue() {
		return approximateValue;
	}
	public void setApproximateValue(String approximateValue) {
		this.approximateValue = approximateValue;
	}
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	public String getDurationFrom() {
		return durationFrom;
	}
	public void setDurationFrom(String durationFrom) {
		this.durationFrom = durationFrom;
	}
	public String getDurationTo() {
		return durationTo;
	}
	public void setDurationTo(String durationTo) {
		this.durationTo = durationTo;
	}
	public String getAmcCmc() {
		return amcCmc;
	}
	public void setAmcCmc(String amcCmc) {
		this.amcCmc = amcCmc;
	}
	public String getNoteSheetDate() {
		return noteSheetDate;
	}
	public void setNoteSheetDate(String noteSheetDate) {
		this.noteSheetDate = noteSheetDate;
	}
	public String getIsfromworkorder() {
		return isfromworkorder;
	}
	public void setIsfromworkorder(String isfromworkorder) {
		this.isfromworkorder = isfromworkorder;
	}
	public double getStore_avail_stock() {
		return store_avail_stock;
	}
	public void setStore_avail_stock(double store_avail_stock) {
		this.store_avail_stock = store_avail_stock;
	}
	public String getSupplierRefNo() {
		return supplierRefNo;
	}
	public void setSupplierRefNo(String supplierRefNo) {
		this.supplierRefNo = supplierRefNo;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	public String getPoSequence() {
		return poSequence;
	}
	public void setPoSequence(String poSequence) {
		this.poSequence = poSequence;
	}
	public String getPoDepartment() {
		return poDepartment;
	}
	public void setPoDepartment(String poDepartment) {
		this.poDepartment = poDepartment;
	}
	public String getNoteSheetNo() {
		return noteSheetNo;
	}
	public void setNoteSheetNo(String noteSheetNo) {
		this.noteSheetNo = noteSheetNo;
	}
	public String getRateContractNo() {
		return rateContractNo;
	}
	public void setRateContractNo(String rateContractNo) {
		this.rateContractNo = rateContractNo;
	}
	public String getPoDepartmentId() {
		return poDepartmentId;
	}
	public void setPoDepartmentId(String poDepartmentId) {
		this.poDepartmentId = poDepartmentId;
	}
	public String getPoDescription() {
		return poDescription;
	}
	public void setPoDescription(String poDescription) {
		this.poDescription = poDescription;
	}
	public String getPoHsnno() {
		return poHsnno;
	}
	public void setPoHsnno(String poHsnno) {
		this.poHsnno = poHsnno;
	}
	public String getPur_price() {
		return pur_price;
	}
	public void setPur_price(String pur_price) {
		this.pur_price = pur_price;
	}
	public int getMain_status() {
		return main_status;
	}
	public void setMain_status(int main_status) {
		this.main_status = main_status;
	}
	public String getReceipt_datetime() {
		return receipt_datetime;
	}
	public void setReceipt_datetime(String receipt_datetime) {
		this.receipt_datetime = receipt_datetime;
	}
	public String getReceipt_userid() {
		return receipt_userid;
	}
	public void setReceipt_userid(String receipt_userid) {
		this.receipt_userid = receipt_userid;
	}
	public int getIsinventory() {
		return isinventory;
	}
	public void setIsinventory(int isinventory) {
		this.isinventory = isinventory;
	}
	public String getAbrivationid() {
		return abrivationid;
	}
	public void setAbrivationid(String abrivationid) {
		this.abrivationid = abrivationid;
	}
	public int getVaccinationid() {
		return vaccinationid;
	}
	public void setVaccinationid(int vaccinationid) {
		this.vaccinationid = vaccinationid;
	}
	public double getIndi_purchase_gst() {
		return indi_purchase_gst;
	}
	public void setIndi_purchase_gst(double indi_purchase_gst) {
		this.indi_purchase_gst = indi_purchase_gst;
	}
	public String getPhar_drid() {
		return phar_drid;
	}
	public void setPhar_drid(String phar_drid) {
		this.phar_drid = phar_drid;
	}
	public String getComponent() {
		return component;
	}
	public void setComponent(String component) {
		this.component = component;
	}
	public String getPreexpcolor() {
		return preexpcolor;
	}
	public void setPreexpcolor(String preexpcolor) {
		this.preexpcolor = preexpcolor;
	}
	public String getCurrexpcolor() {
		return currexpcolor;
	}
	public void setCurrexpcolor(String currexpcolor) {
		this.currexpcolor = currexpcolor;
	}
	public int getIsinadjustment() {
		return isinadjustment;
	}
	public void setIsinadjustment(int isinadjustment) {
		this.isinadjustment = isinadjustment;
	}
	public String getApprove_date() {
		return approve_date;
	}
	public void setApprove_date(String approve_date) {
		this.approve_date = approve_date;
	}
	public ArrayList<Master> getSecondshelfList() {
		return secondshelfList;
	}
	public void setSecondshelfList(ArrayList<Master> secondshelfList) {
		this.secondshelfList = secondshelfList;
	}
	public int getPreviouscellid() {
		return previouscellid;
	}
	public void setPreviouscellid(int previouscellid) {
		this.previouscellid = previouscellid;
	}
	public int getPreviousseccellid() {
		return previousseccellid;
	}
	public void setPreviousseccellid(int previousseccellid) {
		this.previousseccellid = previousseccellid;
	}
	public String getSecondary_shelf() {
		return secondary_shelf;
	}
	public void setSecondary_shelf(String secondary_shelf) {
		this.secondary_shelf = secondary_shelf;
	}
	public int getGrn_with_po_tempid() {
		return grn_with_po_tempid;
	}
	public void setGrn_with_po_tempid(int grn_with_po_tempid) {
		this.grn_with_po_tempid = grn_with_po_tempid;
	}
	public int getDeilverproduct() {
		return deilverproduct;
	}
	public void setDeilverproduct(int deilverproduct) {
		this.deilverproduct = deilverproduct;
	}
	public boolean isHidecalinpoprint() {
		return hidecalinpoprint;
	}
	public void setHidecalinpoprint(boolean hidecalinpoprint) {
		this.hidecalinpoprint = hidecalinpoprint;
	}
	public String getMail_content() {
		return mail_content;
	}
	public void setMail_content(String mail_content) {
		this.mail_content = mail_content;
	}
	public String getFrommonth() {
		return frommonth;
	}
	public void setFrommonth(String frommonth) {
		this.frommonth = frommonth;
	}
	public String getTomonth() {
		return tomonth;
	}
	public void setTomonth(String tomonth) {
		this.tomonth = tomonth;
	}
	public double getVatamt() {
		return vatamt;
	}
	public void setVatamt(double vatamt) {
		this.vatamt = vatamt;
	}
	public String getGrn_pre_pack() {
		return grn_pre_pack;
	}
	public void setGrn_pre_pack(String grn_pre_pack) {
		this.grn_pre_pack = grn_pre_pack;
	}
	public String getGrn_new_pack() {
		return grn_new_pack;
	}
	public void setGrn_new_pack(String grn_new_pack) {
		this.grn_new_pack = grn_new_pack;
	}
	public String getGrn_pre_freeqty() {
		return grn_pre_freeqty;
	}
	public void setGrn_pre_freeqty(String grn_pre_freeqty) {
		this.grn_pre_freeqty = grn_pre_freeqty;
	}
	public String getGrn_new_freeqty() {
		return grn_new_freeqty;
	}
	public void setGrn_new_freeqty(String grn_new_freeqty) {
		this.grn_new_freeqty = grn_new_freeqty;
	}
	public String getGrn_pre_stock() {
		return grn_pre_stock;
	}
	public void setGrn_pre_stock(String grn_pre_stock) {
		this.grn_pre_stock = grn_pre_stock;
	}
	public String getGrn_new_stock() {
		return grn_new_stock;
	}
	public void setGrn_new_stock(String grn_new_stock) {
		this.grn_new_stock = grn_new_stock;
	}
	public String getGrn_pre_qty() {
		return grn_pre_qty;
	}
	public void setGrn_pre_qty(String grn_pre_qty) {
		this.grn_pre_qty = grn_pre_qty;
	}
	public String getGrn_new_qty() {
		return grn_new_qty;
	}
	public void setGrn_new_qty(String grn_new_qty) {
		this.grn_new_qty = grn_new_qty;
	}
	public double getSalevaluation() {
		return salevaluation;
	}
	public void setSalevaluation(double salevaluation) {
		this.salevaluation = salevaluation;
	}
	public double getTotalsalevaluation() {
		return totalsalevaluation;
	}
	public void setTotalsalevaluation(double totalsalevaluation) {
		this.totalsalevaluation = totalsalevaluation;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getIssue_depart() {
		return issue_depart;
	}
	public void setIssue_depart(String issue_depart) {
		this.issue_depart = issue_depart;
	}
	public String getSubject_msg() {
		return subject_msg;
	}
	public void setSubject_msg(String subject_msg) {
		this.subject_msg = subject_msg;
	}
	public double getClosing() {
		return closing;
	}
	public void setClosing(double closing) {
		this.closing = closing;
	}
	public String getNew_mrp() {
		return new_mrp;
	}
	public void setNew_mrp(String new_mrp) {
		this.new_mrp = new_mrp;
	}
	public double getTotalunknownvalue() {
		return totalunknownvalue;
	}
	public void setTotalunknownvalue(double totalunknownvalue) {
		this.totalunknownvalue = totalunknownvalue;
	}
	public double getUnknownvalue() {
		return unknownvalue;
	}
	public void setUnknownvalue(double unknownvalue) {
		this.unknownvalue = unknownvalue;
	}
	public int getGlobal_prodid() {
		return global_prodid;
	}
	public void setGlobal_prodid(int global_prodid) {
		this.global_prodid = global_prodid;
	}
	public String getCurrent_qty() {
		return current_qty;
	}
	public void setCurrent_qty(String current_qty) {
		this.current_qty = current_qty;
	}
	public String getPrevious_qty() {
		return previous_qty;
	}
	public void setPrevious_qty(String previous_qty) {
		this.previous_qty = previous_qty;
	}
	public String getCancel_userid() {
		return cancel_userid;
	}
	public void setCancel_userid(String cancel_userid) {
		this.cancel_userid = cancel_userid;
	}
	public String getCancel_date() {
		return cancel_date;
	}
	public void setCancel_date(String cancel_date) {
		this.cancel_date = cancel_date;
	}
	public String getCancel_notes() {
		return cancel_notes;
	}
	public void setCancel_notes(String cancel_notes) {
		this.cancel_notes = cancel_notes;
	}
	public double getConsumed() {
		return consumed;
	}
	public void setConsumed(double consumed) {
		this.consumed = consumed;
	}
	public double getTotalsubtotal() {
		return totalsubtotal;
	}
	public void setTotalsubtotal(double totalsubtotal) {
		this.totalsubtotal = totalsubtotal;
	}
	public double getGrnqtyamtttl() {
		return grnqtyamtttl;
	}
	public void setGrnqtyamtttl(double grnqtyamtttl) {
		this.grnqtyamtttl = grnqtyamtttl;
	}
	public double getGrnfreeqtyamtttl() {
		return grnfreeqtyamtttl;
	}
	public void setGrnfreeqtyamtttl(double grnfreeqtyamtttl) {
		this.grnfreeqtyamtttl = grnfreeqtyamtttl;
	}
	public double getTotalunknownqty() {
		return totalunknownqty;
	}
	public void setTotalunknownqty(double totalunknownqty) {
		this.totalunknownqty = totalunknownqty;
	}
	public String getZerotaxamt() {
		return zerotaxamt;
	}
	public void setZerotaxamt(String zerotaxamt) {
		this.zerotaxamt = zerotaxamt;
	}
	public String getFivetaxamt() {
		return fivetaxamt;
	}
	public void setFivetaxamt(String fivetaxamt) {
		this.fivetaxamt = fivetaxamt;
	}
	public String getFivesgst() {
		return fivesgst;
	}
	public void setFivesgst(String fivesgst) {
		this.fivesgst = fivesgst;
	}
	public String getFivecgst() {
		return fivecgst;
	}
	public void setFivecgst(String fivecgst) {
		this.fivecgst = fivecgst;
	}
	public String getFiveigst() {
		return fiveigst;
	}
	public void setFiveigst(String fiveigst) {
		this.fiveigst = fiveigst;
	}
	public String getTwelvetaxamt() {
		return twelvetaxamt;
	}
	public void setTwelvetaxamt(String twelvetaxamt) {
		this.twelvetaxamt = twelvetaxamt;
	}
	public String getTwelvesgst() {
		return twelvesgst;
	}
	public void setTwelvesgst(String twelvesgst) {
		this.twelvesgst = twelvesgst;
	}
	public String getTwelvecgst() {
		return twelvecgst;
	}
	public void setTwelvecgst(String twelvecgst) {
		this.twelvecgst = twelvecgst;
	}
	public String getTwelveigst() {
		return twelveigst;
	}
	public void setTwelveigst(String twelveigst) {
		this.twelveigst = twelveigst;
	}
	public String getEighteentaxamt() {
		return eighteentaxamt;
	}
	public void setEighteentaxamt(String eighteentaxamt) {
		this.eighteentaxamt = eighteentaxamt;
	}
	public String getEighteensgst() {
		return eighteensgst;
	}
	public void setEighteensgst(String eighteensgst) {
		this.eighteensgst = eighteensgst;
	}
	public String getEighteencgst() {
		return eighteencgst;
	}
	public void setEighteencgst(String eighteencgst) {
		this.eighteencgst = eighteencgst;
	}
	public String getEighteenigst() {
		return eighteenigst;
	}
	public void setEighteenigst(String eighteenigst) {
		this.eighteenigst = eighteenigst;
	}
	public String getTwenteightaxamt() {
		return twenteightaxamt;
	}
	public void setTwenteightaxamt(String twenteightaxamt) {
		this.twenteightaxamt = twenteightaxamt;
	}
	public String getTwenteightsgst() {
		return twenteightsgst;
	}
	public void setTwenteightsgst(String twenteightsgst) {
		this.twenteightsgst = twenteightsgst;
	}
	public String getTwenteightcgst() {
		return twenteightcgst;
	}
	public void setTwenteightcgst(String twenteightcgst) {
		this.twenteightcgst = twenteightcgst;
	}
	public String getTwenteightigst() {
		return twenteightigst;
	}
	public void setTwenteightigst(String twenteightigst) {
		this.twenteightigst = twenteightigst;
	}
	public String getNetamt() {
		return netamt;
	}
	public void setNetamt(String netamt) {
		this.netamt = netamt;
	}
	public double getTtaxableamt() {
		return ttaxableamt;
	}
	public void setTtaxableamt(double ttaxableamt) {
		this.ttaxableamt = ttaxableamt;
	}
	public double getTtaxamt() {
		return ttaxamt;
	}
	public void setTtaxamt(double ttaxamt) {
		this.ttaxamt = ttaxamt;
	}
	public int getVatratee() {
		return vatratee;
	}
	public void setVatratee(int vatratee) {
		this.vatratee = vatratee;
	}
	public double getTaxableamt() {
		return taxableamt;
	}
	public void setTaxableamt(double taxableamt) {
		this.taxableamt = taxableamt;
	}
	public double getTaxamts() {
		return taxamts;
	}
	public void setTaxamts(double taxamts) {
		this.taxamts = taxamts;
	}
	public double getTotalpaidamt() {
		return totalpaidamt;
	}
	public void setTotalpaidamt(double totalpaidamt) {
		this.totalpaidamt = totalpaidamt;
	}
	public String getPrductlistnew() {
		return prductlistnew;
	}
	public void setPrductlistnew(String prductlistnew) {
		this.prductlistnew = prductlistnew;
	}
	public String getProductlocation() {
		return productlocation;
	}
	public void setProductlocation(String productlocation) {
		this.productlocation = productlocation;
	}
	public String getReturnlocation() {
		return returnlocation;
	}
	public void setReturnlocation(String returnlocation) {
		this.returnlocation = returnlocation;
	}
	public double getTotalopeningstock() {
		return totalopeningstock;
	}
	public void setTotalopeningstock(double totalopeningstock) {
		this.totalopeningstock = totalopeningstock;
	}
	public double getTotalopeingstockvalue() {
		return totalopeingstockvalue;
	}
	public void setTotalopeingstockvalue(double totalopeingstockvalue) {
		this.totalopeingstockvalue = totalopeingstockvalue;
	}
	public double getTotalqtyin() {
		return totalqtyin;
	}
	public void setTotalqtyin(double totalqtyin) {
		this.totalqtyin = totalqtyin;
	}
	public double getTotalqtyinvalue() {
		return totalqtyinvalue;
	}
	public void setTotalqtyinvalue(double totalqtyinvalue) {
		this.totalqtyinvalue = totalqtyinvalue;
	}
	public double getTotalqtyout() {
		return totalqtyout;
	}
	public void setTotalqtyout(double totalqtyout) {
		this.totalqtyout = totalqtyout;
	}
	public double getTotalstockvalue() {
		return totalstockvalue;
	}
	public void setTotalstockvalue(double totalstockvalue) {
		this.totalstockvalue = totalstockvalue;
	}
	public double getTotalssaleprice() {
		return totalssaleprice;
	}
	public void setTotalssaleprice(double totalssaleprice) {
		this.totalssaleprice = totalssaleprice;
	}
	public double getTotalclosingstock() {
		return totalclosingstock;
	}
	public void setTotalclosingstock(double totalclosingstock) {
		this.totalclosingstock = totalclosingstock;
	}
	public double getTotalclosingvalue() {
		return totalclosingvalue;
	}
	public void setTotalclosingvalue(double totalclosingvalue) {
		this.totalclosingvalue = totalclosingvalue;
	}
	public double getSalepricetotal() {
		return salepricetotal;
	}
	public void setSalepricetotal(double salepricetotal) {
		this.salepricetotal = salepricetotal;
	}
	private int proc_condition;
	private String reqpoqty;
	private double salepricetotal;
	
	public String getReqpoqty() {
		return reqpoqty;
	}
	public void setReqpoqty(String reqpoqty) {
		this.reqpoqty = reqpoqty;
	}
	public int getProc_condition() {
		return proc_condition;
	}
	public void setProc_condition(int proc_condition) {
		this.proc_condition = proc_condition;
	}
	public int getParentpoid() {
		return parentpoid;
	}
	public void setParentpoid(int parentpoid) {
		this.parentpoid = parentpoid;
	}
	public int getNewprocurementid() {
		return newprocurementid;
	}
	public void setNewprocurementid(int newprocurementid) {
		this.newprocurementid = newprocurementid;
	}
	public int getGrnwithpo_child() {
		return grnwithpo_child;
	}
	public void setGrnwithpo_child(int grnwithpo_child) {
		this.grnwithpo_child = grnwithpo_child;
	}
	public int getIs_po_prod() {
		return is_po_prod;
	}
	public void setIs_po_prod(int is_po_prod) {
		this.is_po_prod = is_po_prod;
	}
	public double getUnknownqty() {
		return unknownqty;
	}
	public void setUnknownqty(double unknownqty) {
		this.unknownqty = unknownqty;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public ArrayList<Vendor> getVendorlist() {
		return vendorlist;
	}
	public void setVendorlist(ArrayList<Vendor> vendorlist) {
		this.vendorlist = vendorlist;
	}
	public String getProc_childid() {
		return proc_childid;
	}
	public void setProc_childid(String proc_childid) {
		this.proc_childid = proc_childid;
	}
	public int getRemainsqty() {
		return remainsqty;
	}
	public void setRemainsqty(int remainsqty) {
		this.remainsqty = remainsqty;
	}
	public int getRemainfreereturnqty() {
		return remainfreereturnqty;
	}
	public void setRemainfreereturnqty(int remainfreereturnqty) {
		this.remainfreereturnqty = remainfreereturnqty;
	}
	public double getReturnfreeqty() {
		return returnfreeqty;
	}
	public void setReturnfreeqty(double returnfreeqty) {
		this.returnfreeqty = returnfreeqty;
	}
	public double getReturnqty() {
		return returnqty;
	}
	public void setReturnqty(double returnqty) {
		this.returnqty = returnqty;
	}
	public String getQtyinvalue() {
		return qtyinvalue;
	}
	public void setQtyinvalue(String qtyinvalue) {
		this.qtyinvalue = qtyinvalue;
	}
	public String getProSeqNo() {
		return proSeqNo;
	}
	public void setProSeqNo(String proSeqNo) {
		this.proSeqNo = proSeqNo;
	}
	public double getSalevalue() {
		return salevalue;
	}
	public void setSalevalue(double salevalue) {
		this.salevalue = salevalue;
	}
	public int getTotalsumqty() {
		return totalsumqty;
	}
	public void setTotalsumqty(int totalsumqty) {
		this.totalsumqty = totalsumqty;
	}
	public int getRetrunqtycount() {
		return retrunqtycount;
	}
	public void setRetrunqtycount(int retrunqtycount) {
		this.retrunqtycount = retrunqtycount;
	}
	public String getUnitprices() {
		return unitprices;
	}
	public void setUnitprices(String unitprices) {
		this.unitprices = unitprices;
	}
	public String getSv() {
		return sv;
	}
	public void setSv(String sv) {
		this.sv = sv;
	}
	public int getIndentqty() {
		return indentqty;
	}
	public void setIndentqty(int indentqty) {
		this.indentqty = indentqty;
	}
	public double getPurchaseqty() {
		return purchaseqty;
	}
	public void setPurchaseqty(double purchaseqty) {
		this.purchaseqty = purchaseqty;
	}
	public int getAmtreturnstatus() {
		return amtreturnstatus;
	}
	public void setAmtreturnstatus(int amtreturnstatus) {
		this.amtreturnstatus = amtreturnstatus;
	}
	public int getProdid() {
		return prodid;
	}
	public void setProdid(int prodid) {
		this.prodid = prodid;
	}
	public int getProcid() {
		return procid;
	}
	public void setProcid(int procid) {
		this.procid = procid;
	}
	public String getGiven_date() {
		return given_date;
	}
	public void setGiven_date(String given_date) {
		this.given_date = given_date;
	}
	public String getTempname() {
		return tempname;
	}
	public void setTempname(String tempname) {
		this.tempname = tempname;
	}
	public String getPro_code() {
		return pro_code;
	}
	public void setPro_code(String pro_code) {
		this.pro_code = pro_code;
	}
	public String getHisdepartmentfilter() {
		return hisdepartmentfilter;
	}
	public void setHisdepartmentfilter(String hisdepartmentfilter) {
		this.hisdepartmentfilter = hisdepartmentfilter;
	}
	public ArrayList<Master> getShelfList() {
		return shelfList;
	}
	public void setShelfList(ArrayList<Master> shelfList) {
		this.shelfList = shelfList;
	}
	public String getIsedit() {
		return isedit;
	}
	public void setIsedit(String isedit) {
		this.isedit = isedit;
	}
	public int getSaleqty() {
		return saleqty;
	}
	public void setSaleqty(int saleqty) {
		this.saleqty = saleqty;
	}
	public String getFromwhere() {
		return fromwhere;
	}
	public void setFromwhere(String fromwhere) {
		this.fromwhere = fromwhere;
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
	public String getTotalgstamount() {
		return totalgstamount;
	}
	public void setTotalgstamount(String totalgstamount) {
		this.totalgstamount = totalgstamount;
	}
	public String getGstamount() {
		return gstamount;
	}
	public void setGstamount(String gstamount) {
		this.gstamount = gstamount;
	}
	public String getNetamount() {
		return netamount;
	}
	public void setNetamount(String netamount) {
		this.netamount = netamount;
	}
	public String getTotalnetamount() {
		return totalnetamount;
	}
	public void setTotalnetamount(String totalnetamount) {
		this.totalnetamount = totalnetamount;
	}
	public String getIssueuserid() {
		return issueuserid;
	}
	public void setIssueuserid(String issueuserid) {
		this.issueuserid = issueuserid;
	}
	public String getHisuserfilter() {
		return hisuserfilter;
	}
	public void setHisuserfilter(String hisuserfilter) {
		this.hisuserfilter = hisuserfilter;
	}
	public String getIssueproceid() {
		return issueproceid;
	}
	public void setIssueproceid(String issueproceid) {
		this.issueproceid = issueproceid;
	}
	public String getAdded_date() {
		return added_date;
	}
	public void setAdded_date(String added_date) {
		this.added_date = added_date;
	}
	public ArrayList<Product> getMateriallist() {
		return materiallist;
	}
	public void setMateriallist(ArrayList<Product> materiallist) {
		this.materiallist = materiallist;
	}
	public String getLastgrndate() {
		return lastgrndate;
	}
	public void setLastgrndate(String lastgrndate) {
		this.lastgrndate = lastgrndate;
	}
	public double getAvgcost() {
		return avgcost;
	}
	public void setAvgcost(double avgcost) {
		this.avgcost = avgcost;
	}
	public double getTotalpurchaseprice() {
		return totalpurchaseprice;
	}
	public void setTotalpurchaseprice(double totalpurchaseprice) {
		this.totalpurchaseprice = totalpurchaseprice;
	}
	public int getTotalstock() {
		return totalstock;
	}
	public void setTotalstock(int totalstock) {
		this.totalstock = totalstock;
	}
	public String getPurpriceqty() {
		return purpriceqty;
	}
	public void setPurpriceqty(String purpriceqty) {
		this.purpriceqty = purpriceqty;
	}
	public String getSalepriceqty() {
		return salepriceqty;
	}
	public void setSalepriceqty(String salepriceqty) {
		this.salepriceqty = salepriceqty;
	}
	public String getRequested_qty() {
	  return requested_qty;
	 }
	 public void setRequested_qty(String requested_qty) {
	  this.requested_qty = requested_qty;
	 }
	
	public ArrayList<Product> getCheckmedicinelist() {
		return checkmedicinelist;
	}
	public void setCheckmedicinelist(ArrayList<Product> checkmedicinelist) {
		this.checkmedicinelist = checkmedicinelist;
	}
	public ArrayList<Product> getRequestedmedicineList() {
		return requestedmedicineList;
	}
	public void setRequestedmedicineList(ArrayList<Product> requestedmedicineList) {
		this.requestedmedicineList = requestedmedicineList;
	}
	public String getCancel_req() {
		return cancel_req;
	}
	public void setCancel_req(String cancel_req) {
		this.cancel_req = cancel_req;
	}
	public String getCancel_req_note() {
		return cancel_req_note;
	}
	public void setCancel_req_note(String cancel_req_note) {
		this.cancel_req_note = cancel_req_note;
	}
	public String getTotaltransferqt() {
		return totaltransferqt;
	}
	public void setTotaltransferqt(String totaltransferqt) {
		this.totaltransferqt = totaltransferqt;
	}
	public String getNewexpirydate() {
		return newexpirydate;
	}
	public void setNewexpirydate(String newexpirydate) {
		this.newexpirydate = newexpirydate;
	}
	public int getTotalindent() {
		return totalindent;
	}
	public void setTotalindent(int totalindent) {
		this.totalindent = totalindent;
	}
	public String getApmPharmacyid() {
		return apmPharmacyid;
	}
	public void setApmPharmacyid(String apmPharmacyid) {
		this.apmPharmacyid = apmPharmacyid;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getApmuserid() {
		return apmuserid;
	}
	public void setApmuserid(String apmuserid) {
		this.apmuserid = apmuserid;
	}
	public String getApmlocation() {
		return apmlocation;
	}
	public void setApmlocation(String apmlocation) {
		this.apmlocation = apmlocation;
	}
	private String handover_to;
	private double req_qty;
	private String openingstock;
	 private String closingstock;
	 private int grnno;
	 private String returnstock;
	 private String sale;
	private String allinfo;
	private String fromlocation;
	private double avail_stock;
	private String admin_approve_userid;
	private String admin_notes;
	private double avail_qty;
	private String head_notes;
	private String locationName;
	private String medicine_type_name;
	private String billno;
	private double totalbalance=0;
	private String check_availability_date;
	private String check_availability_note;
	private String paymode="";
	private String disctype="0";
	private String admin_aprove_date;
	private String fivetaxPer="0";
	private String twelltaxPer="0";
	private String docno="";
	private String eighteentaxPer="0";
	private String twentyeighttaxPer="0";
	
	
	private int master=0;
	private String type_name;
	private double payment;
	private String transfer_date;
	private int on=0;
	private String head_aprove_date;
	private int newpo=0;
	private String billtype="0";
	private String req_location;
	private String fiveTaxable="0";
	private String twellTaxable="0";
	private String eighteenTaxable="0";
	private String twentyeightTaxable="0";
	private String remark="";
	private String old_location;
	private String req_or_transfer;
	private String reqqty;
	private double total_amount;
	private double totolmrp;
	private double amountno;
	private String comment="";
	private String discount="0";
	private double amountmrp;
	private String childid;
	private String expectedDate;
	private String parentid;
	private String request_date;
	private String issued_date;
	private String from_location;
	private int deleted=0;
	private int updated=0;
	private String to_location;
	private Collection<Product> allproduct;
	private String tlocation;
	private String tqty;
	private String prod_name;
	private String generic_name;
	private String mobile;
	private String reference;
	private String fullname;
	private String voucherdate;
	private String igst="";
	private int id;
	private int isreturn;
	private String name;
	private String medicine_shedule="0";
	private String category_id;
	private String tpname="0";
	private String dateTime="";
	private String description;
	private String priscid="0";
	private String clienttype="";
	private int index;
	private String type="";
	private String surcharge="0";
	private String time="0";
	private int indent=0;
	private String cgst="0";
	private String sgst="0";
	private String clientid="0";
	private String pclientid="0";
	private String totalamt="0";
	private String taxable="0";
	private String discvat="0";
	private String hsnno="";
	private String taxamt="0";
	private String vatrate="0";
	
	private String warehouse_id;
	private String warehouse_name;
	
	ArrayList<Product> innerProductList;
	
	private String debit="0";
	private String returnQty="0";
	private String credit="0";
	private String doctor="";
	private String clientname="";
	private String location="0";
	private String shedule="";
	private String subcategory_id;
	private String days;
	private String brand_id="0";
	private String product_id;
	private String processing;
	private String total="0";
	private String genericname;
	private String vat="0";
	private String payAmount;
	private double totalAmt=0;
	private String freeqty="0";
	private int postatus=0;
	private String minorder="0";
	private String maxorder="0";
	private ArrayList<Product> productVoucherList;
	
	
	private String allfivetatTot="0";
	private String alltwelltaxTot="0";
	private String alleighteentaxTot="0";
	private String alltwentyeighttaxTot="0";
	
	private String allfivetatPer="0";
	private String alltwelltaxPer="0";
	private String alleighteentaxPer="0";
	private String alltwentyeighttaxPer="0";
	
	
	private String sixtaxable="0";
	private String thirttentax="0";
	private String sixTaxper="";
	private String thirteenTaxper="";
	private String totalVat="0";
	private String grossVat="0";
	private String tableVal="0";
	private String netVal="0";
	private ArrayList<Product> listVat;
	private String catalogueid;
	
	private String date;
	private String tinno;
	private String subTotal="0";
	private String procurementid="0";
	private String allTotal="0";
	private ArrayList<Product> vendorList;
	private String paymentAmount="0";
	private String balance="0";
	private String payType;
	private String voucherno;
	private String purchase_date;
	private String received_qty="0";
	private String shelf;
	
    private String category;
    private String godownid;
    private String subcategory;
    private String brand;
    private String staffid;
    private String inhouse;
    private String collected_date;
    private String lastmodified;
    private String staffname;
    private String email;
    private String mfg;
    private String gowdown;
    private String machine_id;
    private String allot_date;
    private String collect_by;
    private String notes;
    private String contact;
    private String equipment;
    private String machine_name;
    private String service_date;
    private String expiry_date;
    private String tax="0";
    private String userid;
    private String medicinenameid;
    private double qty=0.0;
    private String newproduct="0";
    
    private ArrayList<Product> subcategoryList;
    private ArrayList<Product> warehouseList;
	private ArrayList<Priscription> requestedMedicineList;
    private String equipment_id;
	private String due_date;
	private String remainder_on;
	private String frequency;
	private String sheduled;
	private String lastmaintenance;
	private String address;
    
    private String fromdate;
	private String todate;
    private String product_code;
    private String product_typeid;
    private String product_name;
    private String mrp="0";
    private String purchase_price="0";
    private String sale_price="0";
    private String purchase_discount="0";
    private String sale_discount="0";
    
    private String weight;
    private String unit;
    private String vendor_id;
    private String remains;
    
    private String vendor;
    private String stock="0";
    private int soltStock;
    private String min_delivery_time;
    private String quantity="0";
    private String status;
    private String sale_date;
    private ArrayList<Product> historyvendorProduct;
    
    private String pack="0";
    private String medicine_type="1";
    
    public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	private String batch_no;
    private String allsixtaxTot="0";
	private String allthirttaxTot="0";
	private String allsixVatPer="0";
	private String allthirVatPer="0";
	private String security_date;
	private String security_no;;
	private String alltableValtot="0";
	private String alltotvatTotal="0";
	private String alltotalGross="0";
	private String alltotalNet="0";
	
	private String imagename;
	private String check_avail_userid;
    private String prodtype;
    private String req_userid;
    
    private String profit;
//ruchi usersaccessvariable
	
    
    
	private String apmPharmacyid;
	private String firstname;
	private String lastname;
	private String phone;
	private String apmuserid;
	private String apmlocation;
	
	private String accessList;
	private String accessStatus;
	private String srno;
	
	private double stockqty;
	
	private int grnwithpo;
	private int grnwithoutpo;
	private int totalgrn;
	private int direct_transfer;
	private int request;
	private int rejected;
	private int pending;
	private int pocreated;
	private int transfer;
	private int approved;
	private int delivered;
	private int received;
	private int readytotransfer;
	private int returned;
	private Collection<Product> issueproduct;
	private String consumeid;
	private String tempid;
	private String totalfreeqty="0";
	
	private String procedurename;
	private String procedureid;
	private String productid;
	private String childagreeid;
	private double totalvat;
	private double aprovedamt;
	private String balanceqty;
	private String qtyinout_status;
	
	private String curr_pack, pre_pack, curr_gst, pre_gst, curr_mrp, pre_mrp, curr_saleprice;
	private String pre_saleprice, curr_purprice, pre_purprice, curr_expdate, pre_expdate, curr_barcode, pre_barcode;
	private String curr_pshelf, pre_pshelf, curr_sshelf, pre_sshelf, child_approve_remark;
	private String curr_batch,pre_batch;
	
	
	public String getCurr_batch() {
		return curr_batch;
	}
	public void setCurr_batch(String curr_batch) {
		this.curr_batch = curr_batch;
	}
	public String getPre_batch() {
		return pre_batch;
	}
	public void setPre_batch(String pre_batch) {
		this.pre_batch = pre_batch;
	}
	public String getCurr_pack() {
		return curr_pack;
	}
	public void setCurr_pack(String curr_pack) {
		this.curr_pack = curr_pack;
	}
	public String getPre_pack() {
		return pre_pack;
	}
	public void setPre_pack(String pre_pack) {
		this.pre_pack = pre_pack;
	}
	public String getCurr_gst() {
		return curr_gst;
	}
	public void setCurr_gst(String curr_gst) {
		this.curr_gst = curr_gst;
	}
	public String getPre_gst() {
		return pre_gst;
	}
	public void setPre_gst(String pre_gst) {
		this.pre_gst = pre_gst;
	}
	public String getCurr_mrp() {
		return curr_mrp;
	}
	public void setCurr_mrp(String curr_mrp) {
		this.curr_mrp = curr_mrp;
	}
	public String getPre_mrp() {
		return pre_mrp;
	}
	public void setPre_mrp(String pre_mrp) {
		this.pre_mrp = pre_mrp;
	}
	public String getCurr_saleprice() {
		return curr_saleprice;
	}
	public void setCurr_saleprice(String curr_saleprice) {
		this.curr_saleprice = curr_saleprice;
	}
	public String getPre_saleprice() {
		return pre_saleprice;
	}
	public void setPre_saleprice(String pre_saleprice) {
		this.pre_saleprice = pre_saleprice;
	}
	public String getCurr_purprice() {
		return curr_purprice;
	}
	public void setCurr_purprice(String curr_purprice) {
		this.curr_purprice = curr_purprice;
	}
	public String getPre_purprice() {
		return pre_purprice;
	}
	public void setPre_purprice(String pre_purprice) {
		this.pre_purprice = pre_purprice;
	}
	public String getCurr_expdate() {
		return curr_expdate;
	}
	public void setCurr_expdate(String curr_expdate) {
		this.curr_expdate = curr_expdate;
	}
	public String getPre_expdate() {
		return pre_expdate;
	}
	public void setPre_expdate(String pre_expdate) {
		this.pre_expdate = pre_expdate;
	}
	public String getCurr_barcode() {
		return curr_barcode;
	}
	public void setCurr_barcode(String curr_barcode) {
		this.curr_barcode = curr_barcode;
	}
	public String getPre_barcode() {
		return pre_barcode;
	}
	public void setPre_barcode(String pre_barcode) {
		this.pre_barcode = pre_barcode;
	}
	public String getCurr_pshelf() {
		return curr_pshelf;
	}
	public void setCurr_pshelf(String curr_pshelf) {
		this.curr_pshelf = curr_pshelf;
	}
	public String getPre_pshelf() {
		return pre_pshelf;
	}
	public void setPre_pshelf(String pre_pshelf) {
		this.pre_pshelf = pre_pshelf;
	}
	public String getCurr_sshelf() {
		return curr_sshelf;
	}
	public void setCurr_sshelf(String curr_sshelf) {
		this.curr_sshelf = curr_sshelf;
	}
	public String getPre_sshelf() {
		return pre_sshelf;
	}
	public void setPre_sshelf(String pre_sshelf) {
		this.pre_sshelf = pre_sshelf;
	}
	public String getChild_approve_remark() {
		return child_approve_remark;
	}
	public void setChild_approve_remark(String child_approve_remark) {
		this.child_approve_remark = child_approve_remark;
	}
	public String getQtyinout_status() {
		return qtyinout_status;
	}
	public void setQtyinout_status(String qtyinout_status) {
		this.qtyinout_status = qtyinout_status;
	}
	public String getBalanceqty() {
		return balanceqty;
	}
	public void setBalanceqty(String balanceqty) {
		this.balanceqty = balanceqty;
	}
	public double getAprovedamt() {
		return aprovedamt;
	}
	public void setAprovedamt(double aprovedamt) {
		this.aprovedamt = aprovedamt;
	}
	public double getTotalvat() {
		return totalvat;
	}
	public void setTotalvat(double totalvat) {
		this.totalvat = totalvat;
	}
	public String getChildagreeid() {
		return childagreeid;
	}
	public void setChildagreeid(String childagreeid) {
		this.childagreeid = childagreeid;
	}
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public String getProcedureid() {
		return procedureid;
	}
	public void setProcedureid(String procedureid) {
		this.procedureid = procedureid;
	}
	public String getProcedurename() {
		return procedurename;
	}
	public void setProcedurename(String procedurename) {
		this.procedurename = procedurename;
	}
	public String getTotalfreeqty() {
		return totalfreeqty;
	}
	public void setTotalfreeqty(String totalfreeqty) {
		this.totalfreeqty = totalfreeqty;
	}
	public String getTempid() {
		return tempid;
	}
	public void setTempid(String tempid) {
		this.tempid = tempid;
	}
	public String getConsumeid() {
		return consumeid;
	}
	public void setConsumeid(String consumeid) {
		this.consumeid = consumeid;
	}
	public String getMinorder() {
		return minorder;
	}
	public void setMinorder(String minorder) {
		this.minorder = minorder;
	}
	public String getMaxorder() {
		return maxorder;
	}
	public void setMaxorder(String maxorder) {
		this.maxorder = maxorder;
	}
	public Collection<Product> getIssueproduct() {
		return issueproduct;
	}
	public void setIssueproduct(Collection<Product> issueproduct) {
		this.issueproduct = issueproduct;
	}
	public int getDirect_transfer() {
		return direct_transfer;
	}
	public void setDirect_transfer(int direct_transfer) {
		this.direct_transfer = direct_transfer;
	}
	public int getRequest() {
		return request;
	}
	public void setRequest(int request) {
		this.request = request;
	}
	public int getRejected() {
		return rejected;
	}
	public void setRejected(int rejected) {
		this.rejected = rejected;
	}
	public int getPending() {
		return pending;
	}
	public void setPending(int pending) {
		this.pending = pending;
	}
	public int getPocreated() {
		return pocreated;
	}
	public void setPocreated(int pocreated) {
		this.pocreated = pocreated;
	}
	public int getTransfer() {
		return transfer;
	}
	public void setTransfer(int transfer) {
		this.transfer = transfer;
	}
	public int getApproved() {
		return approved;
	}
	public void setApproved(int approved) {
		this.approved = approved;
	}
	public int getDelivered() {
		return delivered;
	}
	public void setDelivered(int delivered) {
		this.delivered = delivered;
	}
	public int getReceived() {
		return received;
	}
	public void setReceived(int received) {
		this.received = received;
	}
	public int getReadytotransfer() {
		return readytotransfer;
	}
	public void setReadytotransfer(int readytotransfer) {
		this.readytotransfer = readytotransfer;
	}
	public int getReturned() {
		return returned;
	}
	public void setReturned(int returned) {
		this.returned = returned;
	}
	public int getGrnwithpo() {
		return grnwithpo;
	}
	public void setGrnwithpo(int grnwithpo) {
		this.grnwithpo = grnwithpo;
	}
	public int getGrnwithoutpo() {
		return grnwithoutpo;
	}
	public void setGrnwithoutpo(int grnwithoutpo) {
		this.grnwithoutpo = grnwithoutpo;
	}
	public int getTotalgrn() {
		return totalgrn;
	}
	public void setTotalgrn(int totalgrn) {
		this.totalgrn = totalgrn;
	}
	public double getStockqty() {
		return stockqty;
	}
	public void setStockqty(double stockqty) {
		this.stockqty = stockqty;
	}
	public String getSrno() {
		return srno;
	}
	public void setSrno(String srno) {
		this.srno = srno;
	}
	public String getAccessStatus() {
		return accessStatus;
	}
	public void setAccessStatus(String accessStatus) {
		this.accessStatus = accessStatus;
	}
	private String accessListDT;
	private String accessListBy;
	private int isagreement;
    private double newpurprice;
    
    
    
    
      public double getNewpurprice() {
		return newpurprice;
	}
	public void setNewpurprice(double newpurprice) {
		this.newpurprice = newpurprice;
	}
	public int getIsagreement() {
		return isagreement;
	}
	public void setIsagreement(int isagreement) {
		this.isagreement = isagreement;
	}
	public String getAccessList() {
		return accessList;
	}
	public void setAccessList(String accessList) {
		this.accessList = accessList;
	}
	public String getAccessListDT() {
		return accessListDT;
	}
	public void setAccessListDT(String accessListDT) {
		this.accessListDT = accessListDT;
	}
	public String getAccessListBy() {
		return accessListBy;
	}
	public void setAccessListBy(String accessListBy) {
		this.accessListBy = accessListBy;
	}
	private ArrayList<Product> transferproductlist;
    
    public ArrayList<Product> getTransferproductlist() {
		return transferproductlist;
	}
	public void setTransferproductlist(ArrayList<Product> transferproductlist) {
		this.transferproductlist = transferproductlist;
	}
	public String getReq_userid() {
		return req_userid;
	}
	public void setReq_userid(String req_userid) {
		this.req_userid = req_userid;
	}
	public String getProdtype() {
		return prodtype;
	}
	public void setProdtype(String prodtype) {
		this.prodtype = prodtype;
	}
	public String getCheck_avail_userid() {
		return check_avail_userid;
	}
	public void setCheck_avail_userid(String check_avail_userid) {
		this.check_avail_userid = check_avail_userid;
	}
	public String getImagename() {
		return imagename;
	}
	public void setImagename(String imagename) {
		this.imagename = imagename;
	}
	public String getBatch_no() {
		return batch_no;
	}
	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
	}
	public String getSale_date() {
		return sale_date;
	}
	public void setSale_date(String sale_date) {
		this.sale_date = sale_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMin_delivery_time() {
		return min_delivery_time;
	}
	public void setMin_delivery_time(String min_delivery_time) {
		this.min_delivery_time = min_delivery_time;
	}
	
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getVendor_id() {
		return vendor_id;
	}
	public void setVendor_id(String vendor_id) {
		this.vendor_id = vendor_id;
	}
	public String getProduct_code() {
		return product_code;
		
	}
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}
	public String getProduct_typeid() {
		return product_typeid;
	}
	public void setProduct_typeid(String product_typeid) {
		this.product_typeid = product_typeid;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getMrp() {
		return mrp;
	}
	public void setMrp(String mrp) {
		this.mrp = mrp;
	}
	
	
	
	public String getPurchase_price() {
		return purchase_price;
	}
	public void setPurchase_price(String purchase_price) {
		this.purchase_price = purchase_price;
	}
	public String getSale_price() {
		return sale_price;
	}
	public void setSale_price(String sale_price) {
		this.sale_price = sale_price;
	}
	public String getPurchase_discount() {
		return purchase_discount;
	}
	public void setPurchase_discount(String purchase_discount) {
		this.purchase_discount = purchase_discount;
	}
	public String getSale_discount() {
		return sale_discount;
	}
	public void setSale_discount(String sale_discount) {
		this.sale_discount = sale_discount;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public ArrayList<Product> getSubcategoryList() {
		return subcategoryList;
	}
	public void setSubcategoryList(ArrayList<Product> subcategoryList) {
		this.subcategoryList = subcategoryList;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSubcategory() {
		return subcategory;
	}
	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
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
	public String getCategory_id() {
		return category_id;
	}
	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSubcategory_id() {
		return subcategory_id;
	}
	public void setSubcategory_id(String subcategory_id) {
		this.subcategory_id = subcategory_id;
	}
	public String getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(String brand_id) {
		this.brand_id = brand_id;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getStaffid() {
		return staffid;
	}
	public void setStaffid(String staffid) {
		this.staffid = staffid;
	}
	public String getInhouse() {
		return inhouse;
	}
	public void setInhouse(String inhouse) {
		this.inhouse = inhouse;
	}
	public String getCollected_date() {
		return collected_date;
	}
	public void setCollected_date(String collected_date) {
		this.collected_date = collected_date;
	}
	public String getAllot_date() {
		return allot_date;
	}
	public void setAllot_date(String allot_date) {
		this.allot_date = allot_date;
	}
	public String getCollect_by() {
		return collect_by;
	}
	public void setCollect_by(String collect_by) {
		this.collect_by = collect_by;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getProcessing() {
		return processing;
	}
	public void setProcessing(String processing) {
		this.processing = processing;
	}
	public String getLastmodified() {
		return lastmodified;
	}
	public void setLastmodified(String lastmodified) {
		this.lastmodified = lastmodified;
	}
	public String getStaffname() {
		return staffname;
	}
	public void setStaffname(String staffname) {
		this.staffname = staffname;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getRemains() {
		return remains;
	}
	public void setRemains(String remains) {
		this.remains = remains;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getEquipment_id() {
		return equipment_id;
	}
	public void setEquipment_id(String equipment_id) {
		this.equipment_id = equipment_id;
	}
	public String getDue_date() {
		return due_date;
	}
	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}
	public String getRemainder_on() {
		return remainder_on;
	}
	public void setRemainder_on(String remainder_on) {
		this.remainder_on = remainder_on;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public String getSheduled() {
		return sheduled;
	}
	public void setSheduled(String sheduled) {
		this.sheduled = sheduled;
	}
	public String getLastmaintenance() {
		return lastmaintenance;
	}
	public void setLastmaintenance(String lastmaintenance) {
		this.lastmaintenance = lastmaintenance;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEquipment() {
		return equipment;
	}
	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}
	public String getMachine_name() {
		return machine_name;
	}
	public void setMachine_name(String machine_name) {
		this.machine_name = machine_name;
	}
	public String getMachine_id() {
		return machine_id;
	}
	public void setMachine_id(String machine_id) {
		this.machine_id = machine_id;
	}
	public String getService_date() {
		return service_date;
	}
	public void setService_date(String service_date) {
		this.service_date = service_date;
	}
	public String getExpiry_date() {
		return expiry_date;
	}
	public void setExpiry_date(String expiry_date) {
		this.expiry_date = expiry_date;
	}
	public String getTax() {
		return tax;
	}
	public void setTax(String tax) {
		this.tax = tax;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public double getQty() {
		return qty;
	}
	public void setQty(double qty) {
		this.qty = qty;
	}
	public String getGowdown() {
		return gowdown;
	}
	public void setGowdown(String gowdown) {
		this.gowdown = gowdown;
	}
	public String getGodownid() {
		return godownid;
	}
	public void setGodownid(String godownid) {
		this.godownid = godownid;
	}
	public ArrayList<Product> getHistoryvendorProduct() {
		return historyvendorProduct;
	}
	public void setHistoryvendorProduct(ArrayList<Product> historyvendorProduct) {
		this.historyvendorProduct = historyvendorProduct;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getMedicinenameid() {
		return medicinenameid;
	}
	public void setMedicinenameid(String medicinenameid) {
		this.medicinenameid = medicinenameid;
	}
	public ArrayList<Priscription> getRequestedMedicineList() {
		return requestedMedicineList;
	}
	public void setRequestedMedicineList(
			ArrayList<Priscription> requestedMedicineList) {
		this.requestedMedicineList = requestedMedicineList;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getGenericname() {
		return genericname;
	}
	public void setGenericname(String genericname) {
		this.genericname = genericname;
	}
	public String getVat() {
		return vat;
	}
	public void setVat(String vat) {
		this.vat = vat;
	}
	public String getTinno() {
		return tinno;
	}
	public void setTinno(String tinno) {
		this.tinno = tinno;
	}
	public String getVoucherno() {
		return voucherno;
	}
	public void setVoucherno(String voucherno) {
		this.voucherno = voucherno;
	}
	public String getPurchase_date() {
		return purchase_date;
	}
	public void setPurchase_date(String purchase_date) {
		this.purchase_date = purchase_date;
	}
	public String getReceived_qty() {
		return received_qty;
	}
	public void setReceived_qty(String received_qty) {
		this.received_qty = received_qty;
	}
	public String getShelf() {
		return shelf;
	}
	public void setShelf(String shelf) {
		this.shelf = shelf;
	}
	public ArrayList<Product> getVendorList() {
		return vendorList;
	}
	public void setVendorList(ArrayList<Product> vendorList) {
		this.vendorList = vendorList;
	}
	public String getPack() {
		return pack;
	}
	public void setPack(String pack) {
		this.pack = pack;
	}
	public String getMedicine_type() {
		return medicine_type;
	}
	public void setMedicine_type(String medicine_type) {
		this.medicine_type = medicine_type;
	}
	public int getSoltStock() {
		return soltStock;
	}
	public void setSoltStock(int soltStock) {
		this.soltStock = soltStock;
	}
	public int getPostatus() {
		return postatus;
	}
	public void setPostatus(int postatus) {
		this.postatus = postatus;
	}
	public String getMfg() {
		return mfg;
	}
	public void setMfg(String mfg) {
		this.mfg = mfg;
	}
	public String getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}
	public String getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(String subTotal) {
		this.subTotal = subTotal;
	}
	public String getAllTotal() {
		return allTotal;
	}
	public void setAllTotal(String allTotal) {
		this.allTotal = allTotal;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(String paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getNewproduct() {
		return newproduct;
	}
	public void setNewproduct(String newproduct) {
		this.newproduct = newproduct;
	}
	public String getProcurementid() {
		return procurementid;
	}
	public void setProcurementid(String procurementid) {
		this.procurementid = procurementid;
	}
	public double getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(double totalAmt) {
		this.totalAmt = totalAmt;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getSixtaxable() {
		return sixtaxable;
	}
	public void setSixtaxable(String sixtaxable) {
		this.sixtaxable = sixtaxable;
	}
	public String getThirttentax() {
		return thirttentax;
	}
	public void setThirttentax(String thirttentax) {
		this.thirttentax = thirttentax;
	}
	public String getSixTaxper() {
		return sixTaxper;
	}
	public void setSixTaxper(String sixTaxper) {
		this.sixTaxper = sixTaxper;
	}
	public String getThirteenTaxper() {
		return thirteenTaxper;
	}
	public void setThirteenTaxper(String thirteenTaxper) {
		this.thirteenTaxper = thirteenTaxper;
	}
	public String getTotalVat() {
		return totalVat;
	}
	public void setTotalVat(String totalVat) {
		this.totalVat = totalVat;
	}
	public String getGrossVat() {
		return grossVat;
	}
	public void setGrossVat(String grossVat) {
		this.grossVat = grossVat;
	}
	public String getTableVal() {
		return tableVal;
	}
	public void setTableVal(String tableVal) {
		this.tableVal = tableVal;
	}
	public String getNetVal() {
		return netVal;
	}
	public void setNetVal(String netVal) {
		this.netVal = netVal;
	}
	public ArrayList<Product> getListVat() {
		return listVat;
	}
	public void setListVat(ArrayList<Product> listVat) {
		this.listVat = listVat;
	}
	public String getAllsixtaxTot() {
		return allsixtaxTot;
	}
	public void setAllsixtaxTot(String allsixtaxTot) {
		this.allsixtaxTot = allsixtaxTot;
	}
	public String getAllthirttaxTot() {
		return allthirttaxTot;
	}
	public void setAllthirttaxTot(String allthirttaxTot) {
		this.allthirttaxTot = allthirttaxTot;
	}
	public String getAllsixVatPer() {
		return allsixVatPer;
	}
	public void setAllsixVatPer(String allsixVatPer) {
		this.allsixVatPer = allsixVatPer;
	}
	public String getAllthirVatPer() {
		return allthirVatPer;
	}
	public void setAllthirVatPer(String allthirVatPer) {
		this.allthirVatPer = allthirVatPer;
	}
	public String getAlltableValtot() {
		return alltableValtot;
	}
	public void setAlltableValtot(String alltableValtot) {
		this.alltableValtot = alltableValtot;
	}
	public String getAlltotvatTotal() {
		return alltotvatTotal;
	}
	public void setAlltotvatTotal(String alltotvatTotal) {
		this.alltotvatTotal = alltotvatTotal;
	}
	public String getAlltotalGross() {
		return alltotalGross;
	}
	public void setAlltotalGross(String alltotalGross) {
		this.alltotalGross = alltotalGross;
	}
	public String getAlltotalNet() {
		return alltotalNet;
	}
	public void setAlltotalNet(String alltotalNet) {
		this.alltotalNet = alltotalNet;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getProd_name() {
		return prod_name;
	}
	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}
	public String getGeneric_name() {
		return generic_name;
	}
	public void setGeneric_name(String generic_name) {
		this.generic_name = generic_name;
	}
	public String getFreeqty() {
		return freeqty;
	}
	public void setFreeqty(String freeqty) {
		this.freeqty = freeqty;
	}
	public String getVoucherdate() {
		return voucherdate;
	}
	public void setVoucherdate(String voucherdate) {
		this.voucherdate = voucherdate;
	}
	public String getMedicine_shedule() {
		return medicine_shedule;
	}
	public void setMedicine_shedule(String medicine_shedule) {
		this.medicine_shedule = medicine_shedule;
	}
	public String getShedule() {
		return shedule;
	}
	public void setShedule(String shedule) {
		this.shedule = shedule;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public ArrayList<Product> getProductVoucherList() {
		return productVoucherList;
	}
	public void setProductVoucherList(ArrayList<Product> productVoucherList) {
		this.productVoucherList = productVoucherList;
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
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDoctor() {
		return doctor;
	}
	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}
	public String getClientname() {
		return clientname;
	}
	public void setClientname(String clientname) {
		this.clientname = clientname;
	}
	public String getReturnQty() {
		return returnQty;
	}
	public void setReturnQty(String returnQty) {
		this.returnQty = returnQty;
	}
	public ArrayList<Product> getInnerProductList() {
		return innerProductList;
	}
	public void setInnerProductList(ArrayList<Product> innerProductList) {
		this.innerProductList = innerProductList;
	}
	public String getTotalamt() {
		return totalamt;
	}
	public void setTotalamt(String totalamt) {
		this.totalamt = totalamt;
	}
	public String getTaxable() {
		return taxable;
	}
	public void setTaxable(String taxable) {
		this.taxable = taxable;
	}
	public String getDiscvat() {
		return discvat;
	}
	public void setDiscvat(String discvat) {
		this.discvat = discvat;
	}
	public String getTaxamt() {
		return taxamt;
	}
	public void setTaxamt(String taxamt) {
		this.taxamt = taxamt;
	}
	public String getVatrate() {
		return vatrate;
	}
	public void setVatrate(String vatrate) {
		this.vatrate = vatrate;
	}
	public String getSurcharge() {
		return surcharge;
	}
	public void setSurcharge(String surcharge) {
		this.surcharge = surcharge;
	}
	public String getHsnno() {
		return hsnno;
	}
	public void setHsnno(String hsnno) {
		this.hsnno = hsnno;
	}
	public String getClientid() {
		return clientid;
	}
	public void setClientid(String clientid) {
		this.clientid = clientid;
	}
	public String getPclientid() {
		return pclientid;
	}
	public void setPclientid(String pclientid) {
		this.pclientid = pclientid;
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
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getIsreturn() {
		return isreturn;
	}
	public void setIsreturn(int isreturn) {
		this.isreturn = isreturn;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getClienttype() {
		return clienttype;
	}
	public void setClienttype(String clienttype) {
		this.clienttype = clienttype;
	}
	public String getPriscid() {
		return priscid;
	}
	public void setPriscid(String priscid) {
		this.priscid = priscid;
	}
	public String getTpname() {
		return tpname;
	}
	public void setTpname(String tpname) {
		this.tpname = tpname;
	}
	public String getIgst() {
		return igst;
	}
	public void setIgst(String igst) {
		this.igst = igst;
	}
	public Collection<Product> getAllproduct() {
		return allproduct;
	}
	public void setAllproduct(Collection<Product> allproduct) {
		this.allproduct = allproduct;
	}
	public String getTlocation() {
		return tlocation;
	}
	public void setTlocation(String tlocation) {
		this.tlocation = tlocation;
	}
	public String getTqty() {
		return tqty;
	}
	public void setTqty(String tqty) {
		this.tqty = tqty;
	}
	public String getRequest_date() {
		return request_date;
	}
	public void setRequest_date(String request_date) {
		this.request_date = request_date;
	}
	public String getIssued_date() {
		return issued_date;
	}
	public void setIssued_date(String issued_date) {
		this.issued_date = issued_date;
	}
	public String getFrom_location() {
		return from_location;
	}
	public void setFrom_location(String from_location) {
		this.from_location = from_location;
	}
	public String getTo_location() {
		return to_location;
	}
	public void setTo_location(String to_location) {
		this.to_location = to_location;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public String getChildid() {
		return childid;
	}
	public void setChildid(String childid) {
		this.childid = childid;
	}
	public double getAmountno() {
		return amountno;
	}
	public void setAmountno(double amountno) {
		this.amountno = amountno;
	}
	public double getAmountmrp() {
		return amountmrp;
	}
	public void setAmountmrp(double amountmrp) {
		this.amountmrp = amountmrp;
	}
	public double getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(double total_amount) {
		this.total_amount = total_amount;
	}
	public double getTotolmrp() {
		return totolmrp;
	}
	public void setTotolmrp(double totolmrp) {
		this.totolmrp = totolmrp;
	}
	public String getReqqty() {
		return reqqty;
	}
	public void setReqqty(String reqqty) {
		this.reqqty = reqqty;
	}
	public String getAdmin_notes() {
		return admin_notes;
	}
	public void setAdmin_notes(String admin_notes) {
		this.admin_notes = admin_notes;
	}
	public String getHead_notes() {
		return head_notes;
	}
	public void setHead_notes(String head_notes) {
		this.head_notes = head_notes;
	}
	public String getCheck_availability_date() {
		return check_availability_date;
	}
	public void setCheck_availability_date(String check_availability_date) {
		this.check_availability_date = check_availability_date;
	}
	public String getAdmin_aprove_date() {
		return admin_aprove_date;
	}
	public void setAdmin_aprove_date(String admin_aprove_date) {
		this.admin_aprove_date = admin_aprove_date;
	}
	public String getHead_aprove_date() {
		return head_aprove_date;
	}
	public void setHead_aprove_date(String head_aprove_date) {
		this.head_aprove_date = head_aprove_date;
	}
	public String getReq_location() {
		return req_location;
	}
	public void setReq_location(String req_location) {
		this.req_location = req_location;
	}
	public String getOld_location() {
		return old_location;
	}
	public void setOld_location(String old_location) {
		this.old_location = old_location;
	}
	public String getReq_or_transfer() {
		return req_or_transfer;
	}
	public void setReq_or_transfer(String req_or_transfer) {
		this.req_or_transfer = req_or_transfer;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	public int getUpdated() {
		return updated;
	}
	public void setUpdated(int updated) {
		this.updated = updated;
	}
	public String getSecurity_date() {
		return security_date;
	}
	public void setSecurity_date(String security_date) {
		this.security_date = security_date;
	}
	public String getSecurity_no() {
		return security_no;
	}
	public void setSecurity_no(String security_no) {
		this.security_no = security_no;
	}
	public String getAdmin_approve_userid() {
		return admin_approve_userid;
	}
	public void setAdmin_approve_userid(String admin_approve_userid) {
		this.admin_approve_userid = admin_approve_userid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getExpectedDate() {
		return expectedDate;
	}
	public void setExpectedDate(String expectedDate) {
		this.expectedDate = expectedDate;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getIndent() {
		return indent;
	}
	public void setIndent(int indent) {
		this.indent = indent;
	}
	public String getFromlocation() {
		return fromlocation;
	}
	public void setFromlocation(String fromlocation) {
		this.fromlocation = fromlocation;
	}
	public double getAvail_stock() {
		return avail_stock;
	}
	public void setAvail_stock(double avail_stock) {
		this.avail_stock = avail_stock;
	}
	public String getBilltype() {
		return billtype;
	}
	public void setBilltype(String billtype) {
		this.billtype = billtype;
	}
	public String getAllinfo() {
		return allinfo;
	}
	public void setAllinfo(String allinfo) {
		this.allinfo = allinfo;
	}
	public String getOpeningstock() {
		return openingstock;
	}
	public void setOpeningstock(String openingstock) {
		this.openingstock = openingstock;
	}
	public String getClosingstock() {
		return closingstock;
	}
	public void setClosingstock(String closingstock) {
		this.closingstock = closingstock;
	}
	public String getReturnstock() {
		return returnstock;
	}
	public void setReturnstock(String returnstock) {
		this.returnstock = returnstock;
	}
	public String getSale() {
		return sale;
	}
	public void setSale(String sale) {
		this.sale = sale;
	}
	public double getReq_qty() {
		return req_qty;
	}
	public void setReq_qty(double req_qty) {
		this.req_qty = req_qty;
	}
	public int getNewpo() {
		return newpo;
	}
	public void setNewpo(int newpo) {
		this.newpo = newpo;
	}
	public int getMaster() {
		return master;
	}
	
	public String getProfit() {
		return profit;
	}
	public void setProfit(String profit) {
		this.profit = profit;
	}
	public void setMaster(int master) {
		this.master = master;
	}
	public int getOn() {
		return on;
	}
	public void setOn(int on) {
		this.on = on;
	}
	public String getPaymode() {
		return paymode;
	}
	public void setPaymode(String paymode) {
		this.paymode = paymode;
	}
	public double getAvail_qty() {
		return avail_qty;
	}
	public void setAvail_qty(double avail_qty) {
		this.avail_qty = avail_qty;
	}
	public String getCheck_availability_note() {
		return check_availability_note;
	}
	public void setCheck_availability_note(String check_availability_note) {
		this.check_availability_note = check_availability_note;
	}
	public String getDisctype() {
		return disctype;
	}
	public void setDisctype(String disctype) {
		this.disctype = disctype;
	}
	
	
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public String getHandover_to() {
		return handover_to;
	}
	public void setHandover_to(String handover_to) {
		this.handover_to = handover_to;
	}
	
	public String getTransfer_indentno() {
		return transfer_indentno;
	}
	public void setTransfer_indentno(String transfer_indentno) {
		this.transfer_indentno = transfer_indentno;
	}
	public String getWarehouse_id() {
		return warehouse_id;
	}
	public void setWarehouse_id(String warehouse_id) {
		this.warehouse_id = warehouse_id;
	}
	public String getWarehouse_name() {
		return warehouse_name;
	}
	public void setWarehouse_name(String warehouse_name) {
		this.warehouse_name = warehouse_name;
	}
	public ArrayList<Product> getWarehouseList() {
		return warehouseList;
	}
	public void setWarehouseList(ArrayList<Product> warehouseList) {
		this.warehouseList = warehouseList;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public double getPayment() {
		return payment;
	}
	public void setPayment(double payment) {
		this.payment = payment;
	}
	public String getTransfer_date() {
		return transfer_date;
	}
	public void setTransfer_date(String transfer_date) {
		this.transfer_date = transfer_date;
	}
	
	public String getMedicine_type_name() {
		return medicine_type_name;
	}
	public void setMedicine_type_name(String medicine_type_name) {
		this.medicine_type_name = medicine_type_name;
	}
	public String getBillno() {
		return billno;
	}
	public void setBillno(String billno) {
		this.billno = billno;
	}
	public double getTotalbalance() {
		return totalbalance;
	}
	public void setTotalbalance(double totalbalance) {
		this.totalbalance = totalbalance;
	}
	public int getGrnno() {
		return grnno;
	}
	public void setGrnno(int grnno) {
		this.grnno = grnno;
	}
	public String getCatalogueid() {
		return catalogueid;
	}
	public void setCatalogueid(String catalogueid) {
		this.catalogueid = catalogueid;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	 public String getStripcheck() {
			return stripcheck;
		}
		public void setStripcheck(String stripcheck) {
			this.stripcheck = stripcheck;
		}
		public int getEdit() {
			return edit;
		}
		public void setEdit(int edit) {
			this.edit = edit;
		}
		public String getFiveTaxable() {
			return fiveTaxable;
		}
		public void setFiveTaxable(String fiveTaxable) {
			this.fiveTaxable = fiveTaxable;
		}
		public String getTwellTaxable() {
			return twellTaxable;
		}
		public void setTwellTaxable(String twellTaxable) {
			this.twellTaxable = twellTaxable;
		}
		public String getEighteenTaxable() {
			return eighteenTaxable;
		}
		public void setEighteenTaxable(String eighteenTaxable) {
			this.eighteenTaxable = eighteenTaxable;
		}
		public String getTwentyeightTaxable() {
			return twentyeightTaxable;
		}
		public void setTwentyeightTaxable(String twentyeightTaxable) {
			this.twentyeightTaxable = twentyeightTaxable;
		}
		public String getFivetaxPer() {
			return fivetaxPer;
		}
		public void setFivetaxPer(String fivetaxPer) {
			this.fivetaxPer = fivetaxPer;
		}
		public String getTwelltaxPer() {
			return twelltaxPer;
		}
		public void setTwelltaxPer(String twelltaxPer) {
			this.twelltaxPer = twelltaxPer;
		}
		public String getEighteentaxPer() {
			return eighteentaxPer;
		}
		public void setEighteentaxPer(String eighteentaxPer) {
			this.eighteentaxPer = eighteentaxPer;
		}
		public String getTwentyeighttaxPer() {
			return twentyeighttaxPer;
		}
		public void setTwentyeighttaxPer(String twentyeighttaxPer) {
			this.twentyeighttaxPer = twentyeighttaxPer;
		}
		public String getAllfivetatTot() {
			return allfivetatTot;
		}
		public void setAllfivetatTot(String allfivetatTot) {
			this.allfivetatTot = allfivetatTot;
		}
		public String getAlltwelltaxTot() {
			return alltwelltaxTot;
		}
		public void setAlltwelltaxTot(String alltwelltaxTot) {
			this.alltwelltaxTot = alltwelltaxTot;
		}
		public String getAlleighteentaxTot() {
			return alleighteentaxTot;
		}
		public void setAlleighteentaxTot(String alleighteentaxTot) {
			this.alleighteentaxTot = alleighteentaxTot;
		}
		public String getAlltwentyeighttaxTot() {
			return alltwentyeighttaxTot;
		}
		public void setAlltwentyeighttaxTot(String alltwentyeighttaxTot) {
			this.alltwentyeighttaxTot = alltwentyeighttaxTot;
		}
		public String getAllfivetatPer() {
			return allfivetatPer;
		}
		public void setAllfivetatPer(String allfivetatPer) {
			this.allfivetatPer = allfivetatPer;
		}
		public String getAlltwelltaxPer() {
			return alltwelltaxPer;
		}
		public void setAlltwelltaxPer(String alltwelltaxPer) {
			this.alltwelltaxPer = alltwelltaxPer;
		}
		public String getAlleighteentaxPer() {
			return alleighteentaxPer;
		}
		public void setAlleighteentaxPer(String alleighteentaxPer) {
			this.alleighteentaxPer = alleighteentaxPer;
		}
		public String getAlltwentyeighttaxPer() {
			return alltwentyeighttaxPer;
		}
		public void setAlltwentyeighttaxPer(String alltwentyeighttaxPer) {
			this.alltwentyeighttaxPer = alltwentyeighttaxPer;
		}
		public String getTranstype() {
			return transtype;
		}
		public void setTranstype(String transtype) {
			this.transtype = transtype;
		}
		public String getDocno() {
			return docno;
		}
		public void setDocno(String docno) {
			this.docno = docno;
		}
		public String getIssueqty() {
			return issueqty;
		}
		public void setIssueqty(String issueqty) {
			this.issueqty = issueqty;
		}
		public int getBalqty() {
			return balqty;
		}
		public void setBalqty(int balqty) {
			this.balqty = balqty;
		}
		public String getGrnreturnid() {
			return grnreturnid;
		}
		public void setGrnreturnid(String grnreturnid) {
			this.grnreturnid = grnreturnid;
		}
		public int getProcurement() {
			return procurement;
		}
		public void setProcurement(int procurement) {
			this.procurement = procurement;
		}
	public int getQuantitycount() {
			return quantitycount;
		}
		public void setQuantitycount(int quantitycount) {
			this.quantitycount = quantitycount;
		}
	public String getRate() {
			return rate;
		}
		public void setRate(String rate) {
			this.rate = rate;
		}
	public double getOldvenderpurprise() {
			return oldvenderpurprise;
		}
		public void setOldvenderpurprise(double oldvenderpurprise) {
			this.oldvenderpurprise = oldvenderpurprise;
		}
	public ArrayList<Product> getCountlist() {
			return countlist;
		}
		public void setCountlist(ArrayList<Product> countlist) {
			this.countlist = countlist;
		}
	public int getLimit() {
			return limit;
		}
		public void setLimit(int limit) {
			this.limit = limit;
		}
	public String getLimitstr() {
			return limitstr;
		}
		public void setLimitstr(String limitstr) {
			this.limitstr = limitstr;
		}
	public String getColor() {
			return color;
		}
		public void setColor(String color) {
			this.color = color;
		}
		
		
	public String getOldprodid() {
			return oldprodid;
		}
		public void setOldprodid(String oldprodid) {
			this.oldprodid = oldprodid;
		}
	public double getIndividual_pur_price() {
			return individual_pur_price;
		}
		public void setIndividual_pur_price(double individual_pur_price) {
			this.individual_pur_price = individual_pur_price;
		}
		
		
	public double getValuation() {
			return valuation;
		}
		public void setValuation(double valuation) {
			this.valuation = valuation;
		}
		
	public double getUnitprice() {
			return unitprice;
		}
		public void setUnitprice(double unitprice) {
			this.unitprice = unitprice;
		}
		
	
	public String getTotalcountpurprice() {
			return totalcountpurprice;
		}
		public void setTotalcountpurprice(String totalcountpurprice) {
			this.totalcountpurprice = totalcountpurprice;
		}
		
	public String getOpentotalcount() {
			return opentotalcount;
		}
		public void setOpentotalcount(String opentotalcount) {
			this.opentotalcount = opentotalcount;
		}
	
	public double getOpeningstockvalue() {
			return openingstockvalue;
		}
		public void setOpeningstockvalue(double openingstockvalue) {
			this.openingstockvalue = openingstockvalue;
		}
	public double getTotalopeningval() {
			return totalopeningval;
		}
		public void setTotalopeningval(double totalopeningval) {
			this.totalopeningval = totalopeningval;
		}
	public int getPurchase_stock_qty() {
			return purchase_stock_qty;
		}
		public void setPurchase_stock_qty(int purchase_stock_qty) {
			this.purchase_stock_qty = purchase_stock_qty;
		}
	public double getNetammt() {
			return netammt;
		}
		public void setNetammt(double netammt) {
			this.netammt = netammt;
		}
	private int quantitycount;
	private double oldvenderpurprise;	
	private ArrayList<Product> countlist;
	private int limit;
	private String limitstr;
	private String color;
	private String oldprodid;
	private double individual_pur_price;
	private double valuation;
	private double unitprice;
	private String totalcountpurprice;
	private String opentotalcount;
	private double openingstockvalue;
	private double totalopeningval;
	private int purchase_stock_qty;
	private double netammt;
	private double discper;

	public double getDiscper() {
		return discper;
	}
	public void setDiscper(double discper) {
		this.discper = discper;
	}
	public int getTotaldebit() {
		return totaldebit;
	}
	public void setTotaldebit(int totaldebit) {
		this.totaldebit = totaldebit;
	}
	public double getDecimalbillamount() {
		return decimalbillamount;
	}
	public void setDecimalbillamount(double decimalbillamount) {
		this.decimalbillamount = decimalbillamount;
	}
	public double getTotaldecimalbillamount() {
		return totaldecimalbillamount;
	}
	public void setTotaldecimalbillamount(double totaldecimalbillamount) {
		this.totaldecimalbillamount = totaldecimalbillamount;
	}
	
	
	
	
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public double getGrossamt() {
		return grossamt;
	}
	public void setGrossamt(double grossamt) {
		this.grossamt = grossamt;
	}
	public double getPerunitamt() {
		return perunitamt;
	}
	public void setPerunitamt(double perunitamt) {
		this.perunitamt = perunitamt;
	}
	public double getTotaldiscount() {
		return totaldiscount;
	}
	public void setTotaldiscount(double totaldiscount) {
		this.totaldiscount = totaldiscount;
	}
	
	public int getAdjustment_type() {
		return adjustment_type;
	}
	public void setAdjustment_type(int adjustment_type) {
		this.adjustment_type = adjustment_type;
	}
	public int getPre_stock() {
		return pre_stock;
	}
	public void setPre_stock(int pre_stock) {
		this.pre_stock = pre_stock;
	}
	public int getCurrent_stock() {
		return current_stock;
	}
	public void setCurrent_stock(int current_stock) {
		this.current_stock = current_stock;
	}
	public String getChange_qty() {
		return change_qty;
	}
	public void setChange_qty(String change_qty) {
		this.change_qty = change_qty;
	}
	public String getIpdid() {
		return ipdid;
	}
	public void setIpdid(String ipdid) {
		this.ipdid = ipdid;
	}
	public String getCommencing() {
		return commencing;
	}
	public void setCommencing(String commencing) {
		this.commencing = commencing;
	}
	private int totaldebit;
	private double decimalbillamount;
	private double totaldecimalbillamount;
	private double perunitamt;
	private double grossamt;
	private String data;
	private double totaldiscount;
	private int adjustment_type;
	private int pre_stock;
	private int current_stock ;
	private String change_qty;
	private String ipdid;
	private String whopay;
	private String commencing;
	private String starttime;
	private String endtime;
	private String diaryusername;
	private String duration;
	private String charge;
	private String apmttypetext;
	private String complete_datetime;
	private String patient_arrived_time;
	private String patient_being_seen_time;

	public String getWhopay() {
		return whopay;
	}
	public void setWhopay(String whopay) {
		this.whopay = whopay;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getDiaryusername() {
		return diaryusername;
	}
	public void setDiaryusername(String diaryusername) {
		this.diaryusername = diaryusername;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getCharge() {
		return charge;
	}
	public void setCharge(String charge) {
		this.charge = charge;
	}
	public String getApmttypetext() {
		return apmttypetext;
	}
	public void setApmttypetext(String apmttypetext) {
		this.apmttypetext = apmttypetext;
	}
	public String getComplete_datetime() {
		return complete_datetime;
	}
	public void setComplete_datetime(String complete_datetime) {
		this.complete_datetime = complete_datetime;
	}
	public String getPatient_arrived_time() {
		return patient_arrived_time;
	}
	public void setPatient_arrived_time(String patient_arrived_time) {
		this.patient_arrived_time = patient_arrived_time;
	}
	public String getPatient_being_seen_time() {
		return patient_being_seen_time;
	}
	public void setPatient_being_seen_time(String patient_being_seen_time) {
		this.patient_being_seen_time = patient_being_seen_time;
	}
	public String getPrintId() {
		return printId;
	}
	public void setPrintId(String printId) {
		this.printId = printId;
	}
	public String getAdmin_approve_remark() {
		return admin_approve_remark;
	}
	public void setAdmin_approve_remark(String admin_approve_remark) {
		this.admin_approve_remark = admin_approve_remark;
	}
	private String printId;

	@Override
	public int compareTo(Product o) {
		return this.getProduct_name().compareTo(o.getProduct_name());
	}
}
