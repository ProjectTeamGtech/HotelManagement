package com.apm.Inventory.web.form;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

import com.apm.Inventory.eu.entity.Procurement;
import com.apm.Inventory.eu.entity.Product;
import com.apm.Inventory.eu.entity.Vendor;
import com.apm.Master.eu.entity.Master;
import com.apm.Registration.eu.entity.Clinic;

public class ProcurementForm {
	private String mailcheck;
	public String getMailcheck() {
		return mailcheck;
	}

	public void setMailcheck(String mailcheck) {
		this.mailcheck = mailcheck;
	}
	private String searchtext;
	int id;
	String category;
	String category_id;
	String brand;
	String brand_id;
	String name;
	private File userImage;
	private String userImageContentType;
	private String userImageFileName;
	String product;
	private int haslocation=0;
	String product_id;
	String subcategory;
	private int newpo=0;
	String subcategory_id;
	private String voucherdate;
	String purchase_price;
	String sale_price;
	private ArrayList<Master> locationListPharmacy;
	private ArrayList<Procurement> returnAccountList;
	private String location="0";
	private Collection<Product> vatallocations;
	private String warehouse="0";
	private ArrayList<Master> medicineTypeList;
	private ArrayList<Product> requestedPoList;
	private ArrayList<Product> vendorProductList;
	private String disctype="0";
	ArrayList<Master> warehouseList;
	private String paymode="";
	ArrayList<Product> requestedPOList ;
	private String subTotal="0";
	private String grnno="0";
	private String grndate;
	private String netTotal="0";
	private String cgst="0";
	private String sgst="0";
	private String igst="0";
	
	private Collection<Product> products;
	private String clinicName;
	private String clinicOwner;
	private String clinicemail;
	private String clinicaddress;
	private String clinicity;
	private String websiteUrl;
	private String landLine;
	private String owner_qualification;
	private String email;
	private String fullname;
	
	
	private ArrayList<Product> vatAllocationList;
	String stock;
	private ArrayList<Master> productypelist;
	private String printedby;
	private String place="0";
	String vendor_id;
	private String security_date;
	private String remark="";
	private String security_no;
	private String debit="0";
	private String credit="0";
	private String discount="0";
	
	String amount;
	String date;
	String product_code;
	String prod_id;
	
	ArrayList<Product> brandnameList;
	String total;
	private String surcharge;
	String mrp;
	
	private  ArrayList<Master>cellList;
	
	private String voucherno;
	private String batch_no;
	private String expiry_date;
	private String vat;
	private String purchase_date;
	private String received_qty;
	private String shelf;
	
	
	ArrayList<Procurement> procurementList;
	ArrayList<Vendor>vendorList;
	ArrayList<Product> voucherList;

	ArrayList<Product> productList;
	private ArrayList<Product> procurementReorderList;
	
	ArrayList<Product>poproductList;
	private String vendor;
	private String pagerecords;
	private int totalRecords;
	private String isdelivermemo;
	private String delivermemodate;
	private String delivermemoid;
	private ArrayList<Procurement> dmlist;
	private String dmgrnids;
	private String isedit;
	private String isfromcathlab;
	private String warehouseid;
	private String isagreement;
	private ArrayList<Procurement> agreementList;
	private String iseditlocation="0";
	private ArrayList<Master>bankNameList;
	private String filter_location;
	private String grnreturnids="0";
	private String sucessmsg;
	private String invoiceid;
	private String userid;
	private String total_amt,  refundcheck, totalpaid;
	private String vendoridlongpo;
	private String totalmrp,totalrate,tttotal;
	private ArrayList<Product> selectedPOList;
	private ArrayList<Master>ledgerList;
	private String ledgername;
	private String newprocurementid;
	private String newgrno;
	private String ispodashboard;
	private double netamtttl;
	private double ttlgst;
	
	private ArrayList<Master> genericnamelist;
	private ArrayList<Master> mfglist;
	private boolean issubjectavailable;
	private String subject_msg;
	private String barcode;
	private String mail_content;
	private boolean ismail_content;
	private boolean hidecalinpoprint;
	
	private String firstnetamout;
	private int isfromuploadstock;
	private int isfrombincard;
	private String vendorGstNo;
	
	private String noteSheetNo,rateContractNo,poDepartmentId;
	private String poDepartment;
	private String poSequence;
	private ArrayList<String> unitOfMeseurementList;
	private String supplierRefNo;
	private String totaldiscount;
	private String isfromworkorder;
	private String noteSheetDate;
	private String durationFrom,durationTo,amcCmc;
	private String paymenttermsncond;
	private int isCancel;
	private ArrayList<Vendor> supplierList;
	private String supplierPoId;
	private String locationPoId;
	private double poDiscAmt;
	private String dmno;
	private String clinicid1;
	private String scheme="0";
	private String totalschmedisc="0";
	private double totalorderamt;
	private double totalreturnsettledamt;
	private double totalpaymentamt;
	private double totalpaidamt;
	private double totalbalanceamt;
	private double size;
	private ArrayList<Procurement> vendorrateList;
	
	public ArrayList<Procurement> getVendorrateList() {
		return vendorrateList;
	}

	public void setVendorrateList(ArrayList<Procurement> vendorrateList) {
		this.vendorrateList = vendorrateList;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public double getTotalorderamt() {
		return totalorderamt;
	}

	public void setTotalorderamt(double totalorderamt) {
		this.totalorderamt = totalorderamt;
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

	public double getTotalpaidamt() {
		return totalpaidamt;
	}

	public void setTotalpaidamt(double totalpaidamt) {
		this.totalpaidamt = totalpaidamt;
	}

	public double getTotalbalanceamt() {
		return totalbalanceamt;
	}

	public void setTotalbalanceamt(double totalbalanceamt) {
		this.totalbalanceamt = totalbalanceamt;
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

	public String getClinicid1() {
		return clinicid1;
	}

	public void setClinicid1(String clinicid1) {
		this.clinicid1 = clinicid1;
	}
	
	public String getDmno() {
		return dmno;
	}

	public void setDmno(String dmno) {
		this.dmno = dmno;
	}

	public double getPoDiscAmt() {
		return poDiscAmt;
	}

	public void setPoDiscAmt(double poDiscAmt) {
		this.poDiscAmt = poDiscAmt;
	}

	public String getSupplierPoId() {
		return supplierPoId;
	}

	public void setSupplierPoId(String supplierPoId) {
		this.supplierPoId = supplierPoId;
	}

	public String getLocationPoId() {
		return locationPoId;
	}

	public void setLocationPoId(String locationPoId) {
		this.locationPoId = locationPoId;
	}

	public ArrayList<Vendor> getSupplierList() {
		return supplierList;
	}

	public void setSupplierList(ArrayList<Vendor> supplierList) {
		this.supplierList = supplierList;
	}

	public int getIsCancel() {
		return isCancel;
	}

	public void setIsCancel(int isCancel) {
		this.isCancel = isCancel;
	}

	public String getPaymenttermsncond() {
		return paymenttermsncond;
	}

	public void setPaymenttermsncond(String paymenttermsncond) {
		this.paymenttermsncond = paymenttermsncond;
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

	public String getTotaldiscount() {
		return totaldiscount;
	}

	public void setTotaldiscount(String totaldiscount) {
		this.totaldiscount = totaldiscount;
	}

	public String getSupplierRefNo() {
		return supplierRefNo;
	}

	public void setSupplierRefNo(String supplierRefNo) {
		this.supplierRefNo = supplierRefNo;
	}

	public ArrayList<String> getUnitOfMeseurementList() {
		return unitOfMeseurementList;
	}

	public void setUnitOfMeseurementList(ArrayList<String> unitOfMeseurementList) {
		this.unitOfMeseurementList = unitOfMeseurementList;
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

	public String getVendorGstNo() {
		return vendorGstNo;
	}

	public void setVendorGstNo(String vendorGstNo) {
		this.vendorGstNo = vendorGstNo;
	}

	public int getIsfrombincard() {
		return isfrombincard;
	}

	public void setIsfrombincard(int isfrombincard) {
		this.isfrombincard = isfrombincard;
	}

	public int getIsfromuploadstock() {
		return isfromuploadstock;
	}

	public void setIsfromuploadstock(int isfromuploadstock) {
		this.isfromuploadstock = isfromuploadstock;
	}

	public String getFirstnetamout() {
		return firstnetamout;
	}

	public void setFirstnetamout(String firstnetamout) {
		this.firstnetamout = firstnetamout;
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

	public boolean isIsmail_content() {
		return ismail_content;
	}

	public void setIsmail_content(boolean ismail_content) {
		this.ismail_content = ismail_content;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public boolean isIssubjectavailable() {
		return issubjectavailable;
	}

	public void setIssubjectavailable(boolean issubjectavailable) {
		this.issubjectavailable = issubjectavailable;
	}

	public String getSubject_msg() {
		return subject_msg;
	}

	public void setSubject_msg(String subject_msg) {
		this.subject_msg = subject_msg;
	}

	public ArrayList<Master> getGenericnamelist() {
		return genericnamelist;
	}

	public void setGenericnamelist(ArrayList<Master> genericnamelist) {
		this.genericnamelist = genericnamelist;
	}

	public ArrayList<Master> getMfglist() {
		return mfglist;
	}

	public void setMfglist(ArrayList<Master> mfglist) {
		this.mfglist = mfglist;
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

	public String getIspodashboard() {
		return ispodashboard;
	}

	public void setIspodashboard(String ispodashboard) {
		this.ispodashboard = ispodashboard;
	}

	public String getNewgrno() {
		return newgrno;
	}

	public void setNewgrno(String newgrno) {
		this.newgrno = newgrno;
	}

	public String getNewprocurementid() {
		return newprocurementid;
	}

	public void setNewprocurementid(String newprocurementid) {
		this.newprocurementid = newprocurementid;
	}

	public String getLedgername() {
		return ledgername;
	}

	public void setLedgername(String ledgername) {
		this.ledgername = ledgername;
	}

	public ArrayList<Master> getLedgerList() {
		return ledgerList;
	}

	public void setLedgerList(ArrayList<Master> ledgerList) {
		this.ledgerList = ledgerList;
	}

	public ArrayList<Product> getSelectedPOList() {
		return selectedPOList;
	}

	public void setSelectedPOList(ArrayList<Product> selectedPOList) {
		this.selectedPOList = selectedPOList;
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

	public String getVendoridlongpo() {
		return vendoridlongpo;
	}

	public void setVendoridlongpo(String vendoridlongpo) {
		this.vendoridlongpo = vendoridlongpo;
	}

	public String getTotal_amt() {
		return total_amt;
	}

	public void setTotal_amt(String total_amt) {
		this.total_amt = total_amt;
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

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getInvoiceid() {
		return invoiceid;
	}

	public void setInvoiceid(String invoiceid) {
		this.invoiceid = invoiceid;
	}

	public String getSucessmsg() {
		return sucessmsg;
	}

	public void setSucessmsg(String sucessmsg) {
		this.sucessmsg = sucessmsg;
	}

	public String getGrnreturnids() {
		return grnreturnids;
	}

	public void setGrnreturnids(String grnreturnids) {
		this.grnreturnids = grnreturnids;
	}

	public String getFilter_location() {
		return filter_location;
	}

	public void setFilter_location(String filter_location) {
		this.filter_location = filter_location;
	}

	public ArrayList<Master> getBankNameList() {
		return bankNameList;
	}

	public void setBankNameList(ArrayList<Master> bankNameList) {
		this.bankNameList = bankNameList;
	}

	public String getIseditlocation() {
		return iseditlocation;
	}

	public void setIseditlocation(String iseditlocation) {
		this.iseditlocation = iseditlocation;
	}

	public ArrayList<Procurement> getAgreementList() {
		return agreementList;
	}

	public void setAgreementList(ArrayList<Procurement> agreementList) {
		this.agreementList = agreementList;
	}

	public String getIsagreement() {
		return isagreement;
	}

	public void setIsagreement(String isagreement) {
		this.isagreement = isagreement;
	}

	public String getWarehouseid() {
		return warehouseid;
	}

	public void setWarehouseid(String warehouseid) {
		this.warehouseid = warehouseid;
	}

	public String getIsfromcathlab() {
		return isfromcathlab;
	}

	public void setIsfromcathlab(String isfromcathlab) {
		this.isfromcathlab = isfromcathlab;
	}

	public String getIsedit() {
		return isedit;
	}

	public void setIsedit(String isedit) {
		this.isedit = isedit;
	}

	public String getDmgrnids() {
		return dmgrnids;
	}

	public void setDmgrnids(String dmgrnids) {
		this.dmgrnids = dmgrnids;
	}

	public ArrayList<Procurement> getDmlist() {
		return dmlist;
	}

	public void setDmlist(ArrayList<Procurement> dmlist) {
		this.dmlist = dmlist;
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
	private Collection<Procurement> payments;  
	String quantity;
	
	ArrayList<Procurement>paymentList;
	
	
	private Collection<Product> procurements;
	
	
	//payment variable
	 
	 private String paymentAmount;
	 
	 private String paymentType;
	 
	 private String bankName;
	 
	 private String cheqNo;
	 
	 private String paymentDate;
	 
	 private String cheqType;
	 
	 private String handoverTo;
	 
	 private String procurementid;
	 
	 private String hdnProcurementid;
	 
	 private String sumofreturn="";
	 private String hdntotalamount;
	 
	 ArrayList<Procurement> payrecieptList;
	 
	 private String balance;
	 
	 private String freeqty;
	
	 private String createdby;
	 
	 private ArrayList<Clinic> locationAdressList;
	 private String address;
	 private String clinicLogo;
	 private String mobile;
	 private ArrayList<Product> termsandconditionlist;
	 
	 private int totalgrn;
	 private String expiryDateAlert="0";
	 
	 private String fromdate;
	 private String todate;
	 private String totalnetamount;
	 private String totalgstamount;
	 
	 public String getTotalgstamount() {
		return totalgstamount;
	}

	public void setTotalgstamount(String totalgstamount) {
		this.totalgstamount = totalgstamount;
	}

	public String getTotalnetamount() {
		return totalnetamount;
	}

	public void setTotalnetamount(String totalnetamount) {
		this.totalnetamount = totalnetamount;
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

	public int getTotalgrn() {
		return totalgrn;
	}

	public void setTotalgrn(int totalgrn) {
		this.totalgrn = totalgrn;
	}

	ArrayList<Product> categoryList;
		ArrayList<Product> subcategoryList;
	public ArrayList<Product> getTermsandconditionlist() {
		return termsandconditionlist;
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

	public void setTermsandconditionlist(ArrayList<Product> termsandconditionlist) {
		this.termsandconditionlist = termsandconditionlist;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getClinicLogo() {
		return clinicLogo;
	}

	public void setClinicLogo(String clinicLogo) {
		this.clinicLogo = clinicLogo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public ArrayList<Clinic> getLocationAdressList() {
		return locationAdressList;
	}

	public void setLocationAdressList(ArrayList<Clinic> locationAdressList) {
		this.locationAdressList = locationAdressList;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getFreeqty() {
		return freeqty;
	}

	public void setFreeqty(String freeqty) {
		this.freeqty = freeqty;
	}

	public Collection<Product> getProcurements() {
		return procurements;
	}

	public void setProcurements(Collection<Product> procurements) {
		this.procurements = procurements;
	}

	public ArrayList<Procurement> getPaymentList() {
		return paymentList;
	}

	public void setPaymentList(ArrayList<Procurement> paymentList) {
		this.paymentList = paymentList;
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

	public String getProcurementid() {
		return procurementid;
	}

	public void setProcurementid(String procurementid) {
		this.procurementid = procurementid;
	}

	public String getHdnProcurementid() {
		return hdnProcurementid;
	}

	public void setHdnProcurementid(String hdnProcurementid) {
		this.hdnProcurementid = hdnProcurementid;
	}

	public String getHdntotalamount() {
		return hdntotalamount;
	}

	public void setHdntotalamount(String hdntotalamount) {
		this.hdntotalamount = hdntotalamount;
	}

	public ArrayList<Procurement> getPayrecieptList() {
		return payrecieptList;
	}

	public void setPayrecieptList(ArrayList<Procurement> payrecieptList) {
		this.payrecieptList = payrecieptList;
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

	public ArrayList<Product> getProductList() {
		return productList;
	}

	public void setProductList(ArrayList<Product> productList) {
		this.productList = productList;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getMrp() {
		return mrp;
	}

	public void setMrp(String mrp) {
		this.mrp = mrp;
	}

	public ArrayList<Procurement> getProcurementList() {
		return procurementList;
	}

	public void setProcurementList(ArrayList<Procurement> procurementList) {
		this.procurementList = procurementList;
	}

	public ArrayList<Vendor> getVendorList() {
		return vendorList;
	}

	public void setVendorList(ArrayList<Vendor> vendorList) {
		this.vendorList = vendorList;
	}

	
	
	public String getVendor_id() {
		return vendor_id;
	}

	public void setVendor_id(String vendor_id) {
		this.vendor_id = vendor_id;
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

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
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

	public String getCategory_id() {
		return category_id;
	}

	public void setCategory_id(String category_id) {
		this.category_id = category_id;
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

	public ArrayList<Product> getPoproductList() {
		return poproductList;
	}

	public void setPoproductList(ArrayList<Product> poproductList) {
		this.poproductList = poproductList;
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

	public ArrayList<Product> getBrandnameList() {
		return brandnameList;
	}

	public void setBrandnameList(ArrayList<Product> brandnameList) {
		this.brandnameList = brandnameList;
	}

	public ArrayList<Master> getCellList() {
		return cellList;
	}

	public void setCellList(ArrayList<Master> cellList) {
		this.cellList = cellList;
	}

	public ArrayList<Product> getVoucherList() {
		return voucherList;
	}

	public void setVoucherList(ArrayList<Product> voucherList) {
		this.voucherList = voucherList;
	}

	public Collection<Procurement> getPayments() {
		return payments;
	}

	public void setPayments(Collection<Procurement> payments) {
		this.payments = payments;
	}

	public String getVoucherdate() {
		return voucherdate;
	}

	public void setVoucherdate(String voucherdate) {
		this.voucherdate = voucherdate;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
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

	public ArrayList<Master> getLocationListPharmacy() {
		return locationListPharmacy;
	}

	public void setLocationListPharmacy(ArrayList<Master> locationListPharmacy) {
		this.locationListPharmacy = locationListPharmacy;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Collection<Product> getVatallocations() {
		return vatallocations;
	}

	public void setVatallocations(Collection<Product> vatallocations) {
		this.vatallocations = vatallocations;
	}

	public ArrayList<Product> getVatAllocationList() {
		return vatAllocationList;
	}

	public void setVatAllocationList(ArrayList<Product> vatAllocationList) {
		this.vatAllocationList = vatAllocationList;
	}

	public String getSurcharge() {
		return surcharge;
	}

	public void setSurcharge(String surcharge) {
		this.surcharge = surcharge;
	}

	public ArrayList<Master> getProductypelist() {
		return productypelist;
	}

	public void setProductypelist(ArrayList<Master> productypelist) {
		this.productypelist = productypelist;
	}

	public ArrayList<Master> getMedicineTypeList() {
		return medicineTypeList;
	}

	public void setMedicineTypeList(ArrayList<Master> medicineTypeList) {
		this.medicineTypeList = medicineTypeList;
	}

	public String getSecurity_date() {
		return security_date;
	}

	public void setSecurity_date(String security_date) {
		this.security_date = security_date;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSecurity_no() {
		return security_no;
	}

	public void setSecurity_no(String security_no) {
		this.security_no = security_no;
	}

	public String getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(String subTotal) {
		this.subTotal = subTotal;
	}

	public String getNetTotal() {
		return netTotal;
	}

	public void setNetTotal(String netTotal) {
		this.netTotal = netTotal;
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

	public String getOwner_qualification() {
		return owner_qualification;
	}

	public void setOwner_qualification(String owner_qualification) {
		this.owner_qualification = owner_qualification;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getPrintedby() {
		return printedby;
	}

	public void setPrintedby(String printedby) {
		this.printedby = printedby;
	}

	public ArrayList<Product> getRequestedPOList() {
		return requestedPOList;
	}

	public void setRequestedPOList(ArrayList<Product> requestedPOList) {
		this.requestedPOList = requestedPOList;
	}

	public ArrayList<Master> getWarehouseList() {
		return warehouseList;
	}

	public void setWarehouseList(ArrayList<Master> warehouseList) {
		this.warehouseList = warehouseList;
	}

	public Collection<Product> getProducts() {
		return products;
	}

	public void setProducts(Collection<Product> products) {
		this.products = products;
	}

	public String getPaymode() {
		return paymode;
	}

	public void setPaymode(String paymode) {
		this.paymode = paymode;
	}

	public int getNewpo() {
		return newpo;
	}

	public void setNewpo(int newpo) {
		this.newpo = newpo;
	}

	public ArrayList<Product> getRequestedPoList() {
		return requestedPoList;
	}

	public void setRequestedPoList(ArrayList<Product> requestedPoList) {
		this.requestedPoList = requestedPoList;
	}

	public String getDisctype() {
		return disctype;
	}

	public void setDisctype(String disctype) {
		this.disctype = disctype;
	}

	public ArrayList<Product> getVendorProductList() {
		return vendorProductList;
	}

	public void setVendorProductList(ArrayList<Product> vendorProductList) {
		this.vendorProductList = vendorProductList;
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

	public String getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}

	public int getHaslocation() {
		return haslocation;
	}

	public void setHaslocation(int haslocation) {
		this.haslocation = haslocation;
	}

	public String getSearchtext() {
		return searchtext;
	}

	public void setSearchtext(String searchtext) {
		this.searchtext = searchtext;
	}

	public ArrayList<Procurement> getReturnAccountList() {
		return returnAccountList;
	}

	public void setReturnAccountList(ArrayList<Procurement> returnAccountList) {
		this.returnAccountList = returnAccountList;
	}

	public String getSumofreturn() {
		return sumofreturn;
	}

	public void setSumofreturn(String sumofreturn) {
		this.sumofreturn = sumofreturn;
	}

	public File getUserImage() {
		return userImage;
	}

	public void setUserImage(File userImage) {
		this.userImage = userImage;
	}

	public String getUserImageContentType() {
		return userImageContentType;
	}

	public void setUserImageContentType(String userImageContentType) {
		this.userImageContentType = userImageContentType;
	}

	public String getUserImageFileName() {
		return userImageFileName;
	}

	public void setUserImageFileName(String userImageFileName) {
		this.userImageFileName = userImageFileName;
	}

	public ArrayList<Product> getProcurementReorderList() {
		return procurementReorderList;
	}

	public void setProcurementReorderList(ArrayList<Product> procurementReorderList) {
		this.procurementReorderList = procurementReorderList;
	}

	public String getExpiryDateAlert() {
		return expiryDateAlert;
	}

	public void setExpiryDateAlert(String expiryDateAlert) {
		this.expiryDateAlert = expiryDateAlert;
	}
	public Collection<Product> getTermslist() {
		return termslist;
	}

	public void setTermslist(Collection<Product> termslist) {
		this.termslist = termslist;
	}
	public String getTermsncond() {
		return termsncond;
	}

	public void setTermsncond(String termsncond) {
		this.termsncond = termsncond;
	}
	public ArrayList<Procurement> getEmaillist() {
		return emaillist;
	}

	public void setEmaillist(ArrayList<Procurement> emaillist) {
		this.emaillist = emaillist;
	}
	public String getGstnno() {
		return gstnno;
	}

	public void setGstnno(String gstnno) {
		this.gstnno = gstnno;
	}
	private Collection<Product> termslist;
   private String termsncond;
   private ArrayList<Procurement> emaillist;
   private String gstnno;
   private String bankname,ifsc,accountno,branch;
public String getBankname() {
	return bankname;
}

public void setBankname(String bankname) {
	this.bankname = bankname;
}

public String getIfsc() {
	return ifsc;
}

public void setIfsc(String ifsc) {
	this.ifsc = ifsc;
}

public String getAccountno() {
	return accountno;
}

public void setAccountno(String accountno) {
	this.accountno = accountno;
}

public String getBranch() {
	return branch;
}

public void setBranch(String branch) {
	this.branch = branch;
}
public String getSeconderyletterhead() {
	return seconderyletterhead;
}

public void setSeconderyletterhead(String seconderyletterhead) {
	this.seconderyletterhead = seconderyletterhead;
}
public String getIswithpo() {
	return iswithpo;
}

public void setIswithpo(String iswithpo) {
	this.iswithpo = iswithpo;
}
private String seconderyletterhead;
private String iswithpo;
private String proSeqNo;
private String dmseq;



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




public String getVendoremail() {
	return vendoremail;
}

public void setVendoremail(String vendoremail) {
	this.vendoremail = vendoremail;
}
public String getCheq_receiver() {
	return cheq_receiver;
}

public void setCheq_receiver(String cheq_receiver) {
	this.cheq_receiver = cheq_receiver;
}


public String getInvoice_created() {
	return invoice_created;
}

public void setInvoice_created(String invoice_created) {
	this.invoice_created = invoice_created;
}
private String vendoremail;
private String cheq_receiver;
private String invoice_created;
}
