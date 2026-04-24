package com.apm.Inventory.eu.entity;

import java.util.ArrayList;
import java.util.Vector;

public class Procurement {
	private String cheq_receiver;
	String voucherdate;
	int id;
	String category;
	String categoty_id;
	String brand;
	String brand_id;
	String name;
	String product;
	String product_id;
	String subcategory;
	private String grnreturnid="0";
	String subcategory_id;
	String purchase_price;
	String sale_price;
	String stock;
	String vendor_id;
	private String location="0";
	String vendor;
	private int longpo=0;
	private int edit=0;
	private String disctype="0";
	private int transferred=0;
	private String grndate="";
	private int deleted=0;
	private String userid="0";
	private String grnno;
	private String paymode="";
	
	private String cgst="0";
	private String sgst="0";
	private String discount="0";
	private String security_date;
	private String remark="";
	private String security_no;
	private String igst="0";
	private String surcharge="0";
	private String debit="0";
	private String credit="0";
	private String address;
	private String contact;
	private String email;
	private String city;
	private String netAmt="0";
	
	String amount;
	String date;
	String product_code;
	String prod_id;
	String quantity;
	private String voucherno;
	private String batch_no;
	private String expiry_date;
	private String vat;
	private String purchase_date;
	private String received_qty;
	private String shelf;
	private String newproduct="";
	
	
	
	private String tinno;
	
	String total;
	String mrp;
	int status;
	int gudreceipt;
	int confirm;
	int completepo;
	private String check="0";
	private int grnseqno;
	
	//payment variable
	 
	 private String paymentAmount;
	 
	 private String paymentType;
	 
	 private String bankName;
	 
	 private String cheqNo;
	 
	 private String paymentDate;
	 
	 private String cheqType;
	 
	 private String handoverTo;
	 
	 private String balance;
	
	 private String procurementid;
	
	 private String time;
	 
	private int totalgrn;
	
	private String catalogueid;
	
	private String isdelivermemo;
	private String delivermemodate;
	private String delivermemoid;
	private String isedit;
	private String locationname;
	private String mfg;
	private String approve_qty;
	private String dmcmplt;
	private String dmid;
	private ArrayList<Product> procurmentlist;
	private String bankid;
	private String grnreturnids="0";
	private String parentid;
	private String returnpayment;
	private String paidpayment;
	private String total_amt, sumofreturn, refundcheck, totalpaid;
	private String freeqty;
	private String totalmrp,totalrate,tttotal;
	private int ispocomplete;
	private String isgrnwithpo;
	private double tempvatamount;
	private double totalnetamt;
	private String genericname;
	private int paymentdonestatus;
	private int isgrneditvendor;
	private String approveuserid;
	
	private String firstnetamout;
	private String grnYearSeq;
	private String poSequence;
	private String workorder;
	private String poHsnno,poDescription;
	private String balanceqty;
	private String dmno;
	private double pendingamt;
	private String scheme="0";
	private double totalorderamt;
	private double totalreturnsettledamt;
	private double totalpaymentamt;
	private double totalpaidamt;
	private double totalbalanceamt;
	private Vector<Procurement>ratelistbyvendor ;
	private Vector<Procurement>vendorlistbyproduct ;
	
	
	public Vector<Procurement> getRatelistbyvendor() {
		return ratelistbyvendor;
	}
	public void setRatelistbyvendor(Vector<Procurement> ratelistbyvendor) {
		this.ratelistbyvendor = ratelistbyvendor;
	}
	public Vector<Procurement> getVendorlistbyproduct() {
		return vendorlistbyproduct;
	}
	public void setVendorlistbyproduct(Vector<Procurement> vendorlistbyproduct) {
		this.vendorlistbyproduct = vendorlistbyproduct;
	}
	public double getTotalpaidamt() {
		return totalpaidamt;
	}
	public void setTotalpaidamt(double totalpaidamt) {
		this.totalpaidamt = totalpaidamt;
	}
	public double getTotalreturnsettledamt() {
		return totalreturnsettledamt;
	}
	public void setTotalreturnsettledamt(double totalreturnsettledamt) {
		this.totalreturnsettledamt = totalreturnsettledamt;
	}
	public double getTotalpaymentamt() {
		return totalpaymentamt;
	}
	public void setTotalpaymentamt(double totalpaymentamt) {
		this.totalpaymentamt = totalpaymentamt;
	}
	public double getTotalbalanceamt() {
		return totalbalanceamt;
	}
	public void setTotalbalanceamt(double totalbalanceamt) {
		this.totalbalanceamt = totalbalanceamt;
	}
	
	public double getTotalorderamt() {
		return totalorderamt;
	}
	public void setTotalorderamt(double totalorderamt) {
		this.totalorderamt = totalorderamt;
	}
	public double getPendingamt() {
		return pendingamt;
	}
	public String getScheme() {
		return scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	public void setPendingamt(double pendingamt) {
		this.pendingamt = pendingamt;
	}
	public String getDmno() {
		return dmno;
	}
	public void setDmno(String dmno) {
		this.dmno = dmno;
	}
	public String getBalanceqty() {
		return balanceqty;
	}
	public void setBalanceqty(String balanceqty) {
		this.balanceqty = balanceqty;
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
	public String getWorkorder() {
		return workorder;
	}
	public void setWorkorder(String workorder) {
		this.workorder = workorder;
	}
	public String getPoSequence() {
		return poSequence;
	}
	public void setPoSequence(String poSequence) {
		this.poSequence = poSequence;
	}
	public String getGrnYearSeq() {
		return grnYearSeq;
	}
	public void setGrnYearSeq(String grnYearSeq) {
		this.grnYearSeq = grnYearSeq;
	}
	public String getFirstnetamout() {
		return firstnetamout;
	}
	public void setFirstnetamout(String firstnetamout) {
		this.firstnetamout = firstnetamout;
	}
	public String getApproveuserid() {
		return approveuserid;
	}
	public void setApproveuserid(String approveuserid) {
		this.approveuserid = approveuserid;
	}
	public int getIsgrneditvendor() {
		return isgrneditvendor;
	}
	public void setIsgrneditvendor(int isgrneditvendor) {
		this.isgrneditvendor = isgrneditvendor;
	}
	public int getPaymentdonestatus() {
		return paymentdonestatus;
	}
	public void setPaymentdonestatus(int paymentdonestatus) {
		this.paymentdonestatus = paymentdonestatus;
	}
	public String getGenericname() {
		return genericname;
	}
	public void setGenericname(String genericname) {
		this.genericname = genericname;
	}
	public double getTempvatamount() {
		return tempvatamount;
	}
	public void setTempvatamount(double tempvatamount) {
		this.tempvatamount = tempvatamount;
	}
	public double getTotalnetamt() {
		return totalnetamt;
	}
	public void setTotalnetamt(double totalnetamt) {
		this.totalnetamt = totalnetamt;
	}
	public String getIsgrnwithpo() {
		return isgrnwithpo;
	}
	public void setIsgrnwithpo(String isgrnwithpo) {
		this.isgrnwithpo = isgrnwithpo;
	}
	public int getIspocomplete() {
		return ispocomplete;
	}
	public void setIspocomplete(int ispocomplete) {
		this.ispocomplete = ispocomplete;
	}
	public String getTotalmrp() {
		return totalmrp;
	}
	public void setTotalmrp(String totalmrp) {
		this.totalmrp = totalmrp;
	}
	public String getTotalrate() {
		return totalrate;
	}
	public void setTotalrate(String totalrate) {
		this.totalrate = totalrate;
	}
	public String getTttotal() {
		return tttotal;
	}
	public void setTttotal(String tttotal) {
		this.tttotal = tttotal;
	}
	public String getFreeqty() {
		return freeqty;
	}
	public void setFreeqty(String freeqty) {
		this.freeqty = freeqty;
	}
	public String getTotal_amt() {
		return total_amt;
	}
	public void setTotal_amt(String total_amt) {
		this.total_amt = total_amt;
	}
	public String getSumofreturn() {
		return sumofreturn;
	}
	public void setSumofreturn(String sumofreturn) {
		this.sumofreturn = sumofreturn;
	}
	public String getRefundcheck() {
		return refundcheck;
	}
	public void setRefundcheck(String refundcheck) {
		this.refundcheck = refundcheck;
	}
	public String getTotalpaid() {
		return totalpaid;
	}
	public void setTotalpaid(String totalpaid) {
		this.totalpaid = totalpaid;
	}
	public String getReturnpayment() {
		return returnpayment;
	}
	public void setReturnpayment(String returnpayment) {
		this.returnpayment = returnpayment;
	}
	public String getPaidpayment() {
		return paidpayment;
	}
	public void setPaidpayment(String paidpayment) {
		this.paidpayment = paidpayment;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public String getGrnreturnids() {
		return grnreturnids;
	}
	public void setGrnreturnids(String grnreturnids) {
		this.grnreturnids = grnreturnids;
	}
	public String getBankid() {
		return bankid;
	}
	public void setBankid(String bankid) {
		this.bankid = bankid;
	}
	public ArrayList<Product> getProcurmentlist() {
		return procurmentlist;
	}
	public void setProcurmentlist(ArrayList<Product> procurmentlist) {
		this.procurmentlist = procurmentlist;
	}
	public String getDmid() {
		return dmid;
	}
	public void setDmid(String dmid) {
		this.dmid = dmid;
	}
	public String getDmcmplt() {
		return dmcmplt;
	}
	public void setDmcmplt(String dmcmplt) {
		this.dmcmplt = dmcmplt;
	}
	public String getApprove_qty() {
		return approve_qty;
	}
	public void setApprove_qty(String approve_qty) {
		this.approve_qty = approve_qty;
	}
	public String getMfg() {
		return mfg;
	}
	public void setMfg(String mfg) {
		this.mfg = mfg;
	}
	public int getGrnseqno() {
		return grnseqno;
	}
	public void setGrnseqno(int grnseqno) {
		this.grnseqno = grnseqno;
	}
	public String getLocationname() {
		return locationname;
	}
	public void setLocationname(String locationname) {
		this.locationname = locationname;
	}
	public String getIsedit() {
		return isedit;
	}
	public void setIsedit(String isedit) {
		this.isedit = isedit;
	}
	public String getIsdelivermemo() {
		return isdelivermemo;
	}
	public void setIsdelivermemo(String isdelivermemo) {
		this.isdelivermemo = isdelivermemo;
	}
	public String getDelivermemodate() {
		return delivermemodate;
	}
	public void setDelivermemodate(String delivermemodate) {
		this.delivermemodate = delivermemodate;
	}
	public String getDelivermemoid() {
		return delivermemoid;
	}
	public void setDelivermemoid(String delivermemoid) {
		this.delivermemoid = delivermemoid;
	}
	public String getCatalogueid() {
		return catalogueid;
	}
	public void setCatalogueid(String catalogueid) {
		this.catalogueid = catalogueid;
	}
	public int getTotalgrn() {
		return totalgrn;
	}
	public void setTotalgrn(int totalgrn) {
		this.totalgrn = totalgrn;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getProcurementid() {
		return procurementid;
	}
	public void setProcurementid(String procurementid) {
		this.procurementid = procurementid;
	}
	public String getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(String paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getCheqNo() {
		return cheqNo;
	}
	public void setCheqNo(String cheqNo) {
		this.cheqNo = cheqNo;
	}
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getCheqType() {
		return cheqType;
	}
	public void setCheqType(String cheqType) {
		this.cheqType = cheqType;
	}
	public String getHandoverTo() {
		return handoverTo;
	}
	public void setHandoverTo(String handoverTo) {
		this.handoverTo = handoverTo;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public int getGudreceipt() {
		return gudreceipt;
	}
	public void setGudreceipt(int gudreceipt) {
		this.gudreceipt = gudreceipt;
	}
	public int getConfirm() {
		return confirm;
	}
	public void setConfirm(int confirm) {
		this.confirm = confirm;
	}
	public int getCompletepo() {
		return completepo;
	}
	public void setCompletepo(int completepo) {
		this.completepo = completepo;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMrp() {
		return mrp;
	}
	public void setMrp(String mrp) {
		this.mrp = mrp;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getVendor_id() {
		return vendor_id;
	}
	public void setVendor_id(String vendor_id) {
		this.vendor_id = vendor_id;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getProduct_code() {
		return product_code;
	}
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}
	public String getProd_id() {
		return prod_id;
	}
	public void setProd_id(String prod_id) {
		this.prod_id = prod_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCategoty_id() {
		return categoty_id;
	}
	public void setCategoty_id(String categoty_id) {
		this.categoty_id = categoty_id;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(String brand_id) {
		this.brand_id = brand_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getSubcategory() {
		return subcategory;
	}
	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}
	public String getSubcategory_id() {
		return subcategory_id;
	}
	public void setSubcategory_id(String subcategory_id) {
		this.subcategory_id = subcategory_id;
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
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
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
	public String getBatch_no() {
		return batch_no;
	}
	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
	}
	public String getExpiry_date() {
		return expiry_date;
	}
	public void setExpiry_date(String expiry_date) {
		this.expiry_date = expiry_date;
	}
	public String getVat() {
		return vat;
	}
	public void setVat(String vat) {
		this.vat = vat;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getNewproduct() {
		return newproduct;
	}
	public void setNewproduct(String newproduct) {
		this.newproduct = newproduct;
	}
	public String getCheck() {
		return check;
	}
	public void setCheck(String check) {
		this.check = check;
	}
	public String getVoucherdate() {
		return voucherdate;
	}
	public void setVoucherdate(String voucherdate) {
		this.voucherdate = voucherdate;
	}
	public String getNetAmt() {
		return netAmt;
	}
	public void setNetAmt(String netAmt) {
		this.netAmt = netAmt;
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
	public String getCheq_receiver() {
		return cheq_receiver;
	}
	public void setCheq_receiver(String cheq_receiver) {
		this.cheq_receiver = cheq_receiver;
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
	public String getIgst() {
		return igst;
	}
	public void setIgst(String igst) {
		this.igst = igst;
	}
	public String getSurcharge() {
		return surcharge;
	}
	public void setSurcharge(String surcharge) {
		this.surcharge = surcharge;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getDisctype() {
		return disctype;
	}
	public void setDisctype(String disctype) {
		this.disctype = disctype;
	}
	public String getPaymode() {
		return paymode;
	}
	public void setPaymode(String paymode) {
		this.paymode = paymode;
	}
	public int getLongpo() {
		return longpo;
	}
	public void setLongpo(int longpo) {
		this.longpo = longpo;
	}
	public String getGrnno() {
		return grnno;
	}
	public void setGrnno(String grnno) {
		this.grnno = grnno;
	}
	public String getGrndate() {
		return grndate;
	}
	public void setGrndate(String grndate) {
		this.grndate = grndate;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	public int getEdit() {
		return edit;
	}
	public void setEdit(int edit) {
		this.edit = edit;
	}
	public int getTransferred() {
		return transferred;
	}
	public void setTransferred(int transferred) {
		this.transferred = transferred;
	}
	public String getGrnreturnid() {
		return grnreturnid;
	}
	public void setGrnreturnid(String grnreturnid) {
		this.grnreturnid = grnreturnid;
	}
	
	public String getIscancel() {
		return iscancel;
	}
	public void setIscancel(String iscancel) {
		this.iscancel = iscancel;
	}

	public String getReciver() {
		return reciver;
	}
	public void setReciver(String reciver) {
		this.reciver = reciver;
	}

	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}

	public int getChildid() {
		return childid;
	}
	public void setChildid(int childid) {
		this.childid = childid;
	}
	private String dmseq;
	private String iscancel;
	private String sender;
	private String reciver;
	private int childid;
	private String proSeqNo;

	
	
	
	public String getDmseq() {
		return dmseq;
	}
	public void setDmseq(String dmseq) {
		this.dmseq = dmseq;
	}
	public String getProSeqNo() {
		return proSeqNo;
	}
	public void setProSeqNo(String proSeqNo) {
		this.proSeqNo = proSeqNo;
	}
	public int getPaymentNo() {
		return paymentNo;
	}
	public void setPaymentNo(int paymentNo) {
		this.paymentNo = paymentNo;
	}
	public double getNetamtttl() {
		return netamtttl;
	}
	public void setNetamtttl(double netamtttl) {
		this.netamtttl = netamtttl;
	}
	public double getTtlgst() {
		return ttlgst;
	}
	public void setTtlgst(double ttlgst) {
		this.ttlgst = ttlgst;
	}
	private int paymentNo;
	private double netamtttl;
	private double ttlgst;
}

