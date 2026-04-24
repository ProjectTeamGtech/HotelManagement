package com.apm.Inventory.web.form;

import java.util.ArrayList;
import java.util.Collection;

import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.Inventory.eu.entity.Procurement;
import com.apm.Inventory.eu.entity.Product;
import com.apm.Inventory.eu.entity.Vendor;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.ShelfMaster;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.Location;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.ThirdParties.eu.entity.ThirdParty;

public class ProductForm {
	private String stripcheck;
	private String inventory_transfer;
	private String direct_transfer;
	private String check_availability_date;
	private String curr_user;
	private String warehouse_id;
	private boolean checked;
	private String warehouse_name;
	private String warehouseids;
	private int todayreturn;
	private String contact;
	private String voucherno="";
	private ArrayList<Product> warehouseListNew;
	private int req_qty;
	private ArrayList<Product> openingstockList;
	private ArrayList<Product> grnreturnProductList;
	private String admin_notes;
	private String expectedDate;
	private String comment;
	private Collection<Product> podata;
	private ArrayList<Product> polist;
	private String reqqty;
	private String totalReceived;
	private String totalbalance;
	private String grnno="0";
	ArrayList<Product> productReturnList;
	private ArrayList<Product> childtransferlist;
	ArrayList<Product> returnqueList;
	private String grndate;
	private String balance="0";
	private String issueqty="0";
	private String fullname="";
	private String email;
	private String place;
	private String receiptqty="0";
	private String returnedby;
	private String printedby;
	private String createdby;
			
	private String catalogueid="0";
	private ArrayList<Product> usersaccess;
	
	private String locationName="";
	
	private String purchaseinvoice;
	private ArrayList<Client> allPatientList;
	private Collection<Product> issuedata;
	private ArrayList<ShelfMaster> shelflist;
	private String printbeforeapprove;
	
	private String userwise;
	private  ArrayList<UserProfile> userlist;
	private String isindentapprove;
	private String isstoreuser;
	private ArrayList<Product> requestparenttransferlist;
	private ArrayList<Product> aproveparenttransferlist;
	private String hidecatagoryid;
	private String filter_bydate;
	private String issueproceid;
	private String issuepatientid;
	private String issueuserid;
	private String hisuserfilter;
	private String abrivationid;
	
	private String gatePassType;
	private String vehicleNumber;
	private String dateOfReturn;
	private String approximateValue;
	private String supplierId;
	private ArrayList<Product> gatePassList;
	private String vendoremail;
	private String seconderyletterhead;
	private String vendorGstNo;
	private String gstnno;
	private Product product;
	private String accountno;
	private String ifsc;
	private String branch;
	private String bankname;
	private int isStoreLocation;
	private String gatePassTypeFilter;
	ArrayList<Master>disciplineList;
	private String discipline;
	private String indentapprove;
	private int haveAllDeptAccess;
	private String specialityName;
	private String vendorcode;
	private ArrayList<Product> requestlimitqtylist;
	private String tpid="0";
	private String payee;
	ArrayList<ThirdParty> thirdPartyTypeList;
	private String issue_date;
	
	public String getIssue_date() {
		return issue_date;
	}

	public void setIssue_date(String issue_date) {
		this.issue_date = issue_date;
	}

	public String getTpid() {
		return tpid;
	}

	public void setTpid(String tpid) {
		this.tpid = tpid;
	}

	public String getPayee() {
		return payee;
	}

	public void setPayee(String payee) {
		this.payee = payee;
	}

	public ArrayList<ThirdParty> getThirdPartyTypeList() {
		return thirdPartyTypeList;
	}

	public void setThirdPartyTypeList(ArrayList<ThirdParty> thirdPartyTypeList) {
		this.thirdPartyTypeList = thirdPartyTypeList;
	}

	public ArrayList<Product> getRequestlimitqtylist() {
		return requestlimitqtylist;
	}

	public void setRequestlimitqtylist(ArrayList<Product> requestlimitqtylist) {
		this.requestlimitqtylist = requestlimitqtylist;
	}

	public String getVendorcode() {
		return vendorcode;
	}

	public void setVendorcode(String vendorcode) {
		this.vendorcode = vendorcode;
	}

	public String getSpecialityName() {
		return specialityName;
	}

	public void setSpecialityName(String specialityName) {
		this.specialityName = specialityName;
	}

	public int getHaveAllDeptAccess() {
		return haveAllDeptAccess;
	}

	public void setHaveAllDeptAccess(int haveAllDeptAccess) {
		this.haveAllDeptAccess = haveAllDeptAccess;
	}

	public String getIndentapprove() {
		return indentapprove;
	}

	public void setIndentapprove(String indentapprove) {
		this.indentapprove = indentapprove;
	}

	public String getDiscipline() {
		return discipline;
	}

	public void setDiscipline(String discipline) {
		this.discipline = discipline;
	}

	public ArrayList<Master> getDisciplineList() {
		return disciplineList;
	}

	public void setDisciplineList(ArrayList<Master> disciplineList) {
		this.disciplineList = disciplineList;
	}

	public String getGatePassTypeFilter() {
		return gatePassTypeFilter;
	}

	public void setGatePassTypeFilter(String gatePassTypeFilter) {
		this.gatePassTypeFilter = gatePassTypeFilter;
	}

	public int getIsStoreLocation() {
		return isStoreLocation;
	}

	public void setIsStoreLocation(int isStoreLocation) {
		this.isStoreLocation = isStoreLocation;
	}

	public String getAccountno() {
		return accountno;
	}

	public void setAccountno(String accountno) {
		this.accountno = accountno;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getGstnno() {
		return gstnno;
	}

	public void setGstnno(String gstnno) {
		this.gstnno = gstnno;
	}

	public String getVendorGstNo() {
		return vendorGstNo;
	}

	public void setVendorGstNo(String vendorGstNo) {
		this.vendorGstNo = vendorGstNo;
	}

	public String getSeconderyletterhead() {
		return seconderyletterhead;
	}

	public void setSeconderyletterhead(String seconderyletterhead) {
		this.seconderyletterhead = seconderyletterhead;
	}

	public String getVendoremail() {
		return vendoremail;
	}

	public void setVendoremail(String vendoremail) {
		this.vendoremail = vendoremail;
	}

	public ArrayList<Product> getGatePassList() {
		return gatePassList;
	}

	public void setGatePassList(ArrayList<Product> gatePassList) {
		this.gatePassList = gatePassList;
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

	public String getAbrivationid() {
		return abrivationid;
	}

	public void setAbrivationid(String abrivationid) {
		this.abrivationid = abrivationid;
	}


	ArrayList<Master>catlogList;
	
	ArrayList<Master>secstoreList;
	private String secstorename;
	private String hdnledgerserviceid;
	private ArrayList<Master> issuedeptlist;
	private String hisdepartmentfilter;
	private Collection<Product> cathtemplatedata;
	private ArrayList<Master> procedurelist;
	private String cathtempprocedure;
	private String cathtemplatename;
	private String isfromcathlab;
	private ArrayList<Product> cathtemplist;
	private String cathtempid;
	private ArrayList<Product> newproductlist;
	private String cathtempparentid;
	private String cathtempdeleteids;
	private ArrayList<Location>locationList;
	private ArrayList<AppointmentType>additionalChargeList;
	private ArrayList<Master>masterChageTypeList;
	private String masterchargetype;
	private String ismonthlyreport;
	private double totalqty;
	private  ArrayList<Procurement> supplierpaymentlist;
	private ArrayList<Master> reportlocationList;
	private String reportlocation;
	private String transferedlocation;
	private String sgst ="0";
	private String cgst ="0";
	private String igst ="0";
	private Collection<Product> vatallocations;
	private ArrayList<Product> vatAllocationList ;
	private String aprovedamt;
	private String isfromsamestate;
	private int retrunqtycount;
	
	private double totalopeningstock =0;
	private double totalopeingstockvalue =0;
	private double totalqtyin =0;
	private double totalqtyinvalue=0;
	private double totalqtyout=0;
	private double totalstockvalue=0;
	private double totalssaleprice=0;
	private double totalclosingstock=0;
	private double totalclosingvalue=0;
	private String product_type;
	private String filter_type;
	private double totalunknownqty;
	private double totalsubtotal;
	private double totalvat;
	private ArrayList<Product> cancelbillreport;
	private double totalunknownvalue;
	private ArrayList<Master> genericnamelist;
	private ArrayList<Master> mfglist;
	private String filter_sortby;
	private  ArrayList<Product> editgrnreportlist;
	private String iscalculationbase;
	private int warehoseshelfrequest;
	private String shelf_filter;
	private int isadjustment;
	


	private int request_statuss;
	private String rejectremark;
	private int isfrombincard;
	private int showbreadcrumbs;
	private ArrayList<Product> jobworkprint;
	private String subcategoryid;
	private String product_id;
	private String billDate;
	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}


	private ArrayList<String> unitOfMeseurementList;
	
	public ArrayList<String> getUnitOfMeseurementList() {
		return unitOfMeseurementList;
	}

	public void setUnitOfMeseurementList(ArrayList<String> unitOfMeseurementList) {
		this.unitOfMeseurementList = unitOfMeseurementList;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getSubcategoryid() {
		return subcategoryid;
	}

	public void setSubcategoryid(String subcategoryid) {
		this.subcategoryid = subcategoryid;
	}

	public ArrayList<Product> getJobworkprint() {
		return jobworkprint;
	}

	public void setJobworkprint(ArrayList<Product> jobworkprint) {
		this.jobworkprint = jobworkprint;
	}

	public int getShowbreadcrumbs() {
		return showbreadcrumbs;
	}

	public void setShowbreadcrumbs(int showbreadcrumbs) {
		this.showbreadcrumbs = showbreadcrumbs;
	}

	public int getIsfrombincard() {
		return isfrombincard;
	}

	public void setIsfrombincard(int isfrombincard) {
		this.isfrombincard = isfrombincard;
	}

	public String getRejectremark() {
		return rejectremark;
	}

	public void setRejectremark(String rejectremark) {
		this.rejectremark = rejectremark;
	}

	public int getRequest_statuss() {
		return request_statuss;
	}

	public void setRequest_statuss(int request_statuss) {
		this.request_statuss = request_statuss;
	}

	public int getIsadjustment() {
		return isadjustment;
	}

	public void setIsadjustment(int isadjustment) {
		this.isadjustment = isadjustment;
	}

	public String getShelf_filter() {
		return shelf_filter;
	}

	public void setShelf_filter(String shelf_filter) {
		this.shelf_filter = shelf_filter;
	}

	public int getWarehoseshelfrequest() {
		return warehoseshelfrequest;
	}

	public void setWarehoseshelfrequest(int warehoseshelfrequest) {
		this.warehoseshelfrequest = warehoseshelfrequest;
	}

	public String getIscalculationbase() {
		return iscalculationbase;
	}

	public void setIscalculationbase(String iscalculationbase) {
		this.iscalculationbase = iscalculationbase;
	}

	public ArrayList<Product> getEditgrnreportlist() {
		return editgrnreportlist;
	}

	public void setEditgrnreportlist(ArrayList<Product> editgrnreportlist) {
		this.editgrnreportlist = editgrnreportlist;
	}

	public String getFilter_sortby() {
		return filter_sortby;
	}

	public void setFilter_sortby(String filter_sortby) {
		this.filter_sortby = filter_sortby;
	}

	public ArrayList<Master> getMfglist() {
		return mfglist;
	}

	public void setMfglist(ArrayList<Master> mfglist) {
		this.mfglist = mfglist;
	}

	public ArrayList<Master> getGenericnamelist() {
		return genericnamelist;
	}

	public void setGenericnamelist(ArrayList<Master> genericnamelist) {
		this.genericnamelist = genericnamelist;
	}

	public double getTotalunknownvalue() {
		return totalunknownvalue;
	}

	public void setTotalunknownvalue(double totalunknownvalue) {
		this.totalunknownvalue = totalunknownvalue;
	}

	public ArrayList<Product> getCancelbillreport() {
		return cancelbillreport;
	}

	public void setCancelbillreport(ArrayList<Product> cancelbillreport) {
		this.cancelbillreport = cancelbillreport;
	}

	public double getTotalsubtotal() {
		return totalsubtotal;
	}

	public void setTotalsubtotal(double totalsubtotal) {
		this.totalsubtotal = totalsubtotal;
	}

	public double getTotalvat() {
		return totalvat;
	}

	public void setTotalvat(double totalvat) {
		this.totalvat = totalvat;
	}

	public double getTotalunknownqty() {
		return totalunknownqty;
	}

	public void setTotalunknownqty(double totalunknownqty) {
		this.totalunknownqty = totalunknownqty;
	}

	public String getFilter_type() {
		return filter_type;
	}

	public void setFilter_type(String filter_type) {
		this.filter_type = filter_type;
	}

	public String getProduct_type() {
		return product_type;
	}

	public void setProduct_type(String product_type) {
		this.product_type = product_type;
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

	public int getRetrunqtycount() {
		return retrunqtycount;
	}

	public void setRetrunqtycount(int retrunqtycount) {
		this.retrunqtycount = retrunqtycount;
	}

	public String getIsfromsamestate() {
		return isfromsamestate;
	}

	public void setIsfromsamestate(String isfromsamestate) {
		this.isfromsamestate = isfromsamestate;
	}

	public String getAprovedamt() {
		return aprovedamt;
	}

	public void setAprovedamt(String aprovedamt) {
		this.aprovedamt = aprovedamt;
	}

	public ArrayList<Product> getVatAllocationList() {
		return vatAllocationList;
	}

	public void setVatAllocationList(ArrayList<Product> vatAllocationList) {
		this.vatAllocationList = vatAllocationList;
	}

	public Collection<Product> getVatallocations() {
		return vatallocations;
	}

	public void setVatallocations(Collection<Product> vatallocations) {
		this.vatallocations = vatallocations;
	}

	public String getSgst() {
		return sgst;
	}

	public void setSgst(String sgst) {
		this.sgst = sgst;
	}

	public String getCgst() {
		return cgst;
	}

	public void setCgst(String cgst) {
		this.cgst = cgst;
	}

	public String getIgst() {
		return igst;
	}

	public void setIgst(String igst) {
		this.igst = igst;
	}

	public String getTransferedlocation() {
		return transferedlocation;
	}

	public void setTransferedlocation(String transferedlocation) {
		this.transferedlocation = transferedlocation;
	}

	public String getReportlocation() {
		return reportlocation;
	}

	public void setReportlocation(String reportlocation) {
		this.reportlocation = reportlocation;
	}

	public ArrayList<Master> getReportlocationList() {
		return reportlocationList;
	}

	public void setReportlocationList(ArrayList<Master> reportlocationList) {
		this.reportlocationList = reportlocationList;
	}

	public ArrayList<Procurement> getSupplierpaymentlist() {
		return supplierpaymentlist;
	}

	public void setSupplierpaymentlist(ArrayList<Procurement> supplierpaymentlist) {
		this.supplierpaymentlist = supplierpaymentlist;
	}

	public double getTotalqty() {
		return totalqty;
	}

	public void setTotalqty(double totalqty) {
		this.totalqty = totalqty;
	}

	public String getIsmonthlyreport() {
		return ismonthlyreport;
	}

	public void setIsmonthlyreport(String ismonthlyreport) {
		this.ismonthlyreport = ismonthlyreport;
	}

	public ArrayList<AppointmentType> getAdditionalChargeList() {
		return additionalChargeList;
	}

	public void setAdditionalChargeList(ArrayList<AppointmentType> additionalChargeList) {
		this.additionalChargeList = additionalChargeList;
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

	public ArrayList<Location> getLocationList() {
		return locationList;
	}

	public void setLocationList(ArrayList<Location> locationList) {
		this.locationList = locationList;
	}

	public String getCathtempparentid() {
		return cathtempparentid;
	}

	public void setCathtempparentid(String cathtempparentid) {
		this.cathtempparentid = cathtempparentid;
	}

	public String getCathtempdeleteids() {
		return cathtempdeleteids;
	}

	public void setCathtempdeleteids(String cathtempdeleteids) {
		this.cathtempdeleteids = cathtempdeleteids;
	}

	public ArrayList<Product> getNewproductlist() {
		return newproductlist;
	}

	public void setNewproductlist(ArrayList<Product> newproductlist) {
		this.newproductlist = newproductlist;
	}

	public String getCathtempid() {
		return cathtempid;
	}

	public void setCathtempid(String cathtempid) {
		this.cathtempid = cathtempid;
	}

	public ArrayList<Product> getCathtemplist() {
		return cathtemplist;
	}

	public void setCathtemplist(ArrayList<Product> cathtemplist) {
		this.cathtemplist = cathtemplist;
	}

	public String getIsfromcathlab() {
		return isfromcathlab;
	}

	public void setIsfromcathlab(String isfromcathlab) {
		this.isfromcathlab = isfromcathlab;
	}

	public String getCathtempprocedure() {
		return cathtempprocedure;
	}

	public void setCathtempprocedure(String cathtempprocedure) {
		this.cathtempprocedure = cathtempprocedure;
	}

	public String getCathtemplatename() {
		return cathtemplatename;
	}

	public void setCathtemplatename(String cathtemplatename) {
		this.cathtemplatename = cathtemplatename;
	}

	public ArrayList<Master> getProcedurelist() {
		return procedurelist;
	}

	public void setProcedurelist(ArrayList<Master> procedurelist) {
		this.procedurelist = procedurelist;
	}

	public Collection<Product> getCathtemplatedata() {
		return cathtemplatedata;
	}

	public void setCathtemplatedata(Collection<Product> cathtemplatedata) {
		this.cathtemplatedata = cathtemplatedata;
	}

	public String getHisdepartmentfilter() {
		return hisdepartmentfilter;
	}

	public void setHisdepartmentfilter(String hisdepartmentfilter) {
		this.hisdepartmentfilter = hisdepartmentfilter;
	}

	public ArrayList<Master> getIssuedeptlist() {
		return issuedeptlist;
	}

	public void setIssuedeptlist(ArrayList<Master> issuedeptlist) {
		this.issuedeptlist = issuedeptlist;
	}

	public String getHdnledgerserviceid() {
		return hdnledgerserviceid;
	}

	public void setHdnledgerserviceid(String hdnledgerserviceid) {
		this.hdnledgerserviceid = hdnledgerserviceid;
	}

	public String getDbselectedservices() {
		return dbselectedservices;
	}

	public void setDbselectedservices(String dbselectedservices) {
		this.dbselectedservices = dbselectedservices;
	}


	private String dbselectedservices;
	
	
	
	public ArrayList<Master> getSecstoreList() {
		return secstoreList;
	}

	public void setSecstoreList(ArrayList<Master> secstoreList) {
		this.secstoreList = secstoreList;
	}

	public String getSecstorename() {
		return secstorename;
	}

	public void setSecstorename(String secstorename) {
		this.secstorename = secstorename;
	}

	public ArrayList<Master> getCatlogList() {
		return catlogList;
	}

	public void setCatlogList(ArrayList<Master> catlogList) {
		this.catlogList = catlogList;
	}

	public String getHisuserfilter() {
		return hisuserfilter;
	}

	public void setHisuserfilter(String hisuserfilter) {
		this.hisuserfilter = hisuserfilter;
	}

	public String getIssueuserid() {
		return issueuserid;
	}

	public void setIssueuserid(String issueuserid) {
		this.issueuserid = issueuserid;
	}

	public String getIssueproceid() {
		return issueproceid;
	}

	public void setIssueproceid(String issueproceid) {
		this.issueproceid = issueproceid;
	}

	public String getIssuepatientid() {
		return issuepatientid;
	}

	public void setIssuepatientid(String issuepatientid) {
		this.issuepatientid = issuepatientid;
	}

	public String getFilter_bydate() {
		return filter_bydate;
	}

	public void setFilter_bydate(String filter_bydate) {
		this.filter_bydate = filter_bydate;
	}

	public String getHidecatagoryid() {
		return hidecatagoryid;
	}

	public void setHidecatagoryid(String hidecatagoryid) {
		this.hidecatagoryid = hidecatagoryid;
	}

	public ArrayList<Product> getRequestparenttransferlist() {
		return requestparenttransferlist;
	}

	public void setRequestparenttransferlist(ArrayList<Product> requestparenttransferlist) {
		this.requestparenttransferlist = requestparenttransferlist;
	}

	public ArrayList<Product> getAproveparenttransferlist() {
		return aproveparenttransferlist;
	}

	public void setAproveparenttransferlist(ArrayList<Product> aproveparenttransferlist) {
		this.aproveparenttransferlist = aproveparenttransferlist;
	}

	public String getIsindentapprove() {
		return isindentapprove;
	}

	public void setIsindentapprove(String isindentapprove) {
		this.isindentapprove = isindentapprove;
	}

	public String getIsstoreuser() {
		return isstoreuser;
	}

	public void setIsstoreuser(String isstoreuser) {
		this.isstoreuser = isstoreuser;
	}

	public ArrayList<UserProfile> getUserlist() {
		return userlist;
	}

	public void setUserlist(ArrayList<UserProfile> userlist) {
		this.userlist = userlist;
	}

	public String getUserwise() {
		return userwise;
	}

	public void setUserwise(String userwise) {
		this.userwise = userwise;
	}

	public String getPrintbeforeapprove() {
		return printbeforeapprove;
	}

	public void setPrintbeforeapprove(String printbeforeapprove) {
		this.printbeforeapprove = printbeforeapprove;
	}

	public ArrayList<ShelfMaster> getShelflist() {
		return shelflist;
	}

	public void setShelflist(ArrayList<ShelfMaster> shelflist) {
		this.shelflist = shelflist;
	}

	public Collection<Product> getIssuedata() {
		return issuedata;
	}

	public void setIssuedata(Collection<Product> issuedata) {
		this.issuedata = issuedata;
	}

	public ArrayList<Client> getAllPatientList() {
		return allPatientList;
	}

	public void setAllPatientList(ArrayList<Client> allPatientList) {
		this.allPatientList = allPatientList;
	}

	public String getPurchaseinvoice() {
		return purchaseinvoice;
	}

	public void setPurchaseinvoice(String purchaseinvoice) {
		this.purchaseinvoice = purchaseinvoice;
	}

	public ArrayList<Product> getUsersaccess() {
		return usersaccess;
	}

	public void setUsersaccess(ArrayList<Product> usersaccess) {
		this.usersaccess = usersaccess;
	}


	private String expiry_date;
	private String total_amt;
	private String mainstatus;
	private String mobile;
	private String security_no="";
	private String security_date="";
	private String notes;
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMainstatus() {
		return mainstatus;
	}

	public void setMainstatus(String mainstatus) {
		this.mainstatus = mainstatus;
	}

	public String getTotal_amt() {
		return total_amt;
	}

	public void setTotal_amt(String total_amt) {
		this.total_amt = total_amt;
	}

	public String getTotalbalance() {
		return totalbalance;
	}

	public String getTotalReceived() {
		return totalReceived;
	}

	public void setTotalReceived(String totalReceived) {
		this.totalReceived = totalReceived;
	}

	public void setTotalbalance(String totalbalance) {
		this.totalbalance = totalbalance;
	}


	private String batch_no;
	private String hsnno;
	private Collection<Product> productdata;
	private String status;
	 private String check_availability_note;
	 private double total_amount;
	 private double totolmrp;
	 private ArrayList<Product> callist;
	 private String filter_status;
	 private int avail_qty;
	private ArrayList<Product>  checkmedicinelist;
	private String fromlocation;
	private String issueno;
	private ArrayList<Product> requestedmedicineList;
	private String parentid;
	private String billtype="0";
	private String request_date;
	private String issued_date;
	private String from_location;
	private String salereturn="0";
	private String to_location;
	private ArrayList<Product> parenttransferlist;
	private ArrayList<Master> locationlist;
	private Collection<Product> allproduct;
	ArrayList<Master> warehouseList;
	private ArrayList<Product> prodnamelist;
	private String prod_name;
	private String generic_name;
	private String userid="";
	
	private int id;
	private int edit_catalogue=0;
	private int delete_catalogue=0;
	private String date="";
	private String indentid="";
	private String name;
	private String category_id;
	private ArrayList<Product> updelListReport;
	private String description;
	private String subcategory_id;
	private String brand_id;
	private String medicine_shedule="0";
	private boolean manual_entry;
	private ArrayList<Product> itemWiseListReport;
	private ArrayList<Product> productWiseReport;
	private ArrayList<Master> medicineTypeList;
	String openingstock="";
	private  ArrayList<Product> bincardReportList;
	private ArrayList<Product> salesReturnProduct;
	
	String purchasestock="";
	String soldstock="";
	String totalDebit= "";
	ArrayList<Master> locationListPharmacy;
	private String location="0";
	String closingStock= "";
	String totalCredit= "";
	ArrayList<Master> cellList;
	
	String netProfit="0";
	String netLoss="0";
	
	private String searchtext;
	//
	private ArrayList<Vendor> vendorList;
	private ArrayList<Product> soldproductList;
	
	private ArrayList<Product> expiryMedicineReport;
	private String days;
	private ArrayList<Priscription> genericnameList;
	private Collection<Product> procurements;
	
	private ArrayList<Product> godownlist;
	private Collection<Product> sales;
	
    private String vendor_id;	
	ArrayList<Product> categoryList;
	ArrayList<Master> masterlist;
	private String mastername;
	
	private ArrayList<Procurement> supplierProcurementList;
	private String pagerecords;
	private int totalRecords;
	ArrayList<Product> subcategoryList;
	ArrayList<Product> brandnameList;
	private ArrayList<Product> vatReportList;
	
	private String allsixtaxTot="0";
	private String allthirttaxTot="0";
	private String allsixVatPer="0";
	private String allthirVatPer="0";
	private String alltableValtot="0";
	private String alltotvatTotal="0";
	private String alltotalGross="0";
	private String alltotalNet="0";
	private String return_stock;
	private String location_filter;
	private String report;
	private int totalgrn;
	private String delipo;
	private String isfrominventorycatalogue;
	private Collection<Product> addtopoproduct;
	private Collection<Product> alladjustmentproduct;
	
	public Collection<Product> getAlladjustmentproduct() {
		return alladjustmentproduct;
	}

	public void setAlladjustmentproduct(Collection<Product> alladjustmentproduct) {
		this.alladjustmentproduct = alladjustmentproduct;
	}

	public Collection<Product> getAddtopoproduct() {
		return addtopoproduct;
	}

	public void setAddtopoproduct(Collection<Product> addtopoproduct) {
		this.addtopoproduct = addtopoproduct;
	}

	public String getIsfrominventorycatalogue() {
		return isfrominventorycatalogue;
	}

	public void setIsfrominventorycatalogue(String isfrominventorycatalogue) {
		this.isfrominventorycatalogue = isfrominventorycatalogue;
	}

	public String getDelipo() {
		return delipo;
	}

	public void setDelipo(String delipo) {
		this.delipo = delipo;
	}

	public int getTotalgrn() {
		return totalgrn;
	}

	public void setTotalgrn(int totalgrn) {
		this.totalgrn = totalgrn;
	}

	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}

	public String getLocation_filter() {
		return location_filter;
	}

	public void setLocation_filter(String location_filter) {
		this.location_filter = location_filter;
	}

	public String getReturn_stock() {
		return return_stock;
	}

	public void setReturn_stock(String return_stock) {
		this.return_stock = return_stock;
	}

	public String getGrossVat() {
		return grossVat;
	}

	public void setGrossVat(String grossVat) {
		this.grossVat = grossVat;
	}


	private String godown;
	private String grossVat;
	public String getMfg() {
		return mfg;
	}

	public void setMfg(String mfg) {
		this.mfg = mfg;
	}


	private Collection<Product> products;
	private ArrayList<Product> termsandconditionlist;
	private String pack;
	private String mfg;
	
	public String getPack() {
		return pack;
	}

	public void setPack(String pack) {
		this.pack = pack;
	}

	public ArrayList<Product> getTermsandconditionlist() {
		return termsandconditionlist;
	}

	public void setTermsandconditionlist(ArrayList<Product> termsandconditionlist) {
		this.termsandconditionlist = termsandconditionlist;
	}

	public String getRem_balance() {
		return rem_balance;
	}

	public void setRem_balance(String rem_balance) {
		this.rem_balance = rem_balance;
	}


	private String rem_balance;
	 
	public ArrayList<Procurement> getSupplierProcurementList() {
		return supplierProcurementList;
	}

	public void setSupplierProcurementList(
			ArrayList<Procurement> supplierProcurementList) {
		this.supplierProcurementList = supplierProcurementList;
	}

	
	    private ArrayList<Product> graphSupplierReport; 
	
	    ArrayList<Product> productList;
	    ArrayList<Product> productListXLS;

	    public ArrayList<Product> getProductListXLS() {
			return productListXLS;
		}

		public void setProductListXLS(ArrayList<Product> productListXLS) {
			this.productListXLS = productListXLS;
		}


		private String category;
	    private String subcategory;
	    private String brand;
	    private String fromdate="";
	    private String todate="";
		
	    private int count;
	    private String product_code;
	    private String product_typeid;
	    private String product_name;
	    private String mrp;
	    private String purchase_price;
	    private String sale_price;
	    private String purchase_discount;
	    private String sale_discount;
	    private String weight;
	    private String grnreturnid="0";
	    private String unit;
	    private String vendor;
	    private String vat;
	   


		private String stock;
	    private String min_delivery_time;
	    
	    private String quantity;
	    private ArrayList<Product> allWillExpireProduct;
	    
	    
	    
	    private String check_avail_userid;
	    private String curr_location;
	    private String handover_to;
	    private String clinicName;
		private String clinicOwner;
		private String clinicemail;
		private String clinicaddress;
		private String clinicity;
		private String websiteUrl;
		private String landLine;
		private ArrayList<Clinic> locationAdressList;
		private String address;
		private String filter;
		private Collection<Product> returnproduct;
		private String returncomment;
		private String cancel_indent;
		
		private ArrayList<Product> profitlossreportList;
		
		
		 public String getCancel_indent() {
			return cancel_indent;
		}

		public void setCancel_indent(String cancel_indent) {
			this.cancel_indent = cancel_indent;
		}

		public String getReturncomment() {
			return returncomment;
		}

		public void setReturncomment(String returncomment) {
			this.returncomment = returncomment;
		}

		public Collection<Product> getReturnproduct() {
			return returnproduct;
		}

		public void setReturnproduct(Collection<Product> returnproduct) {
			this.returnproduct = returnproduct;
		}

		public String getVat() {
				return vat;
			}

			public void setVat(String vat) {
				this.vat = vat;
			}
		public String getFilter() {
			return filter;
		}

		public void setFilter(String filter) {
			this.filter = filter;
		}


		private String clinicLogo;
		
		public ArrayList<Clinic> getLocationAdressList() {
			return locationAdressList;
		}

		public void setLocationAdressList(ArrayList<Clinic> locationAdressList) {
			this.locationAdressList = locationAdressList;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getClinicLogo() {
			return clinicLogo;
		}

		public void setClinicLogo(String clinicLogo) {
			this.clinicLogo = clinicLogo;
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


		private String owner_qualification;
	    
	    
	    
	public String getHandover_to() {
			return handover_to;
		}

		public void setHandover_to(String handover_to) {
			this.handover_to = handover_to;
		}

	public String getCurr_location() {
			return curr_location;
		}

		public void setCurr_location(String curr_location) {
			this.curr_location = curr_location;
		}

	public String getCheck_avail_userid() {
			return check_avail_userid;
		}

		public void setCheck_avail_userid(String check_avail_userid) {
			this.check_avail_userid = check_avail_userid;
		}

	public ArrayList<Product> getAllWillExpireProduct() {
			return allWillExpireProduct;
		}
		public void setAllWillExpireProduct(ArrayList<Product> allWillExpireProduct) {
			this.allWillExpireProduct = allWillExpireProduct;
		}
	public String getQuantity() {
			return quantity;
		}
		public void setQuantity(String quantity) {
			this.quantity = quantity;
		}
	public Collection<Product> getProcurements() {
			return procurements;
		}
		public void setProcurements(Collection<Product> procurements) {
			this.procurements = procurements;
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
	public ArrayList<Product> getProductList() {
			return productList;
		}
		public void setProductList(ArrayList<Product> productList) {
			this.productList = productList;
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
	public Collection<Product> getProducts() {
			return products;
		}
		public void setProducts(Collection<Product> products) {
			this.products = products;
		}
	public String getVendor_id() {
			return vendor_id;
		}
		public void setVendor_id(String vendor_id) {
			this.vendor_id = vendor_id;
		}
	public ArrayList<Product> getBrandnameList() {
			return brandnameList;
		}
		public void setBrandnameList(ArrayList<Product> brandnameList) {
			this.brandnameList = brandnameList;
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
	public ArrayList<Product> getSubcategoryList() {
		return subcategoryList;
	}
	public void setSubcategoryList(ArrayList<Product> subcategoryList) {
		this.subcategoryList = subcategoryList;
	}
	public ArrayList<Master> getMasterlist() {
		return masterlist;
	}
	public void setMasterlist(ArrayList<Master> masterlist) {
		this.masterlist = masterlist;
	}
	public String getMastername() {
		return mastername;
	}
	public void setMastername(String mastername) {
		this.mastername = mastername;
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
	public ArrayList<Product> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(ArrayList<Product> categoryList) {
		this.categoryList = categoryList;
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
	public ArrayList<Vendor> getVendorList() {
		return vendorList;
	}
	public void setVendorList(ArrayList<Vendor> vendorList) {
		this.vendorList = vendorList;
	}
	public boolean isManual_entry() {
		return manual_entry;
	}
	public void setManual_entry(boolean manual_entry) {
		this.manual_entry = manual_entry;
	}
	public ArrayList<Product> getGodownlist() {
		return godownlist;
	}
	public void setGodownlist(ArrayList<Product> godownlist) {
		this.godownlist = godownlist;
	}
	public Collection<Product> getSales() {
		return sales;
	}
	public void setSales(Collection<Product> sales) {
		this.sales = sales;
	}
	public ArrayList<Product> getSoldproductList() {
		return soldproductList;
	}
	public void setSoldproductList(ArrayList<Product> soldproductList) {
		this.soldproductList = soldproductList;
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
	public String getGodown() {
		return godown;
	}
	public void setGodown(String godown) {
		this.godown = godown;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public ArrayList<Priscription> getGenericnameList() {
		return genericnameList;
	}
	public void setGenericnameList(ArrayList<Priscription> genericnameList) {
		this.genericnameList = genericnameList;
	}

	public ArrayList<Product> getGraphSupplierReport() {
		return graphSupplierReport;
	}

	public void setGraphSupplierReport(ArrayList<Product> graphSupplierReport) {
		this.graphSupplierReport = graphSupplierReport;
	}

	public ArrayList<Product> getExpiryMedicineReport() {
		return expiryMedicineReport;
	}

	public void setExpiryMedicineReport(ArrayList<Product> expiryMedicineReport) {
		this.expiryMedicineReport = expiryMedicineReport;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public ArrayList<Product> getVatReportList() {
		return vatReportList;
	}

	public void setVatReportList(ArrayList<Product> vatReportList) {
		this.vatReportList = vatReportList;
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

	public String getSearchtext() {
		return searchtext;
	}

	public void setSearchtext(String searchtext) {
		this.searchtext = searchtext;
	}

	public ArrayList<Product> getProdnamelist() {
		return prodnamelist;
	}

	public void setProdnamelist(ArrayList<Product> prodnamelist) {
		this.prodnamelist = prodnamelist;
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

	public String getOpeningstock() {
		return openingstock;
	}

	public void setOpeningstock(String openingstock) {
		this.openingstock = openingstock;
	}

	public String getPurchasestock() {
		return purchasestock;
	}

	public void setPurchasestock(String purchasestock) {
		this.purchasestock = purchasestock;
	}

	public String getSoldstock() {
		return soldstock;
	}

	public void setSoldstock(String soldstock) {
		this.soldstock = soldstock;
	}

	public String getTotalDebit() {
		return totalDebit;
	}

	public void setTotalDebit(String totalDebit) {
		this.totalDebit = totalDebit;
	}

	public String getClosingStock() {
		return closingStock;
	}

	public void setClosingStock(String closingStock) {
		this.closingStock = closingStock;
	}

	public String getTotalCredit() {
		return totalCredit;
	}

	public void setTotalCredit(String totalCredit) {
		this.totalCredit = totalCredit;
	}

	public String getNetProfit() {
		return netProfit;
	}

	public void setNetProfit(String netProfit) {
		this.netProfit = netProfit;
	}

	public String getNetLoss() {
		return netLoss;
	}

	public void setNetLoss(String netLoss) {
		this.netLoss = netLoss;
	}

	public ArrayList<Master> getCellList() {
		return cellList;
	}

	public void setCellList(ArrayList<Master> cellList) {
		this.cellList = cellList;
	}

	public String getMedicine_shedule() {
		return medicine_shedule;
	}

	public void setMedicine_shedule(String medicine_shedule) {
		this.medicine_shedule = medicine_shedule;
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

	public ArrayList<Product> getItemWiseListReport() {
		return itemWiseListReport;
	}

	public void setItemWiseListReport(ArrayList<Product> itemWiseListReport) {
		this.itemWiseListReport = itemWiseListReport;
	}

	public ArrayList<Product> getProductWiseReport() {
		return productWiseReport;
	}

	public void setProductWiseReport(ArrayList<Product> productWiseReport) {
		this.productWiseReport = productWiseReport;
	}

	public ArrayList<Master> getMedicineTypeList() {
		return medicineTypeList;
	}

	public void setMedicineTypeList(ArrayList<Master> medicineTypeList) {
		this.medicineTypeList = medicineTypeList;
	}

	public ArrayList<Product> getSalesReturnProduct() {
		return salesReturnProduct;
	}

	public void setSalesReturnProduct(ArrayList<Product> salesReturnProduct) {
		this.salesReturnProduct = salesReturnProduct;
	}

	public Collection<Product> getAllproduct() {
		return allproduct;
	}

	public void setAllproduct(Collection<Product> allproduct) {
		this.allproduct = allproduct;
	}

	public ArrayList<Master> getLocationlist() {
		return locationlist;
	}

	public void setLocationlist(ArrayList<Master> locationlist) {
		this.locationlist = locationlist;
	}

	public ArrayList<Product> getParenttransferlist() {
		return parenttransferlist;
	}

	public void setParenttransferlist(ArrayList<Product> parenttranferlist) {
		this.parenttransferlist = parenttranferlist;
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

	public ArrayList<Product> getUpdelListReport() {
		return updelListReport;
	}

	public void setUpdelListReport(ArrayList<Product> updelListReport) {
		this.updelListReport = updelListReport;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getIndentid() {
		return indentid;
	}

	public void setIndentid(String indentid) {
		this.indentid = indentid;
	}

	public Collection<Product> getPodata() {
		return podata;
	}

	public void setPodata(Collection<Product> podata) {
		this.podata = podata;
	}

	public ArrayList<Product> getPolist() {
		return polist;
	}

	public void setPolist(ArrayList<Product> polist) {
		this.polist = polist;
	}

	public String getReqqty() {
		return reqqty;
	}

	public void setReqqty(String reqqty) {
		this.reqqty = reqqty;
	}

	public String getExpiry_date() {
		return expiry_date;
	}

	public void setExpiry_date(String expiry_date) {
		this.expiry_date = expiry_date;
	}

	public String getBatch_no() {
		return batch_no;
	}

	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
	}

	public String getHsnno() {
		return hsnno;
	}

	public void setHsnno(String hsnno) {
		this.hsnno = hsnno;
	}

	public Collection<Product> getProductdata() {
		return productdata;
	}

	public void setProductdata(Collection<Product> productdata) {
		this.productdata = productdata;
	}

	public ArrayList<Product> getCheckmedicinelist() {
		return checkmedicinelist;
	}

	public void setCheckmedicinelist(ArrayList<Product> checkmedicinelist) {
		this.checkmedicinelist = checkmedicinelist;
	}

	public String getFromlocation() {
		return fromlocation;
	}

	public void setFromlocation(String fromlocation) {
		this.fromlocation = fromlocation;
	}

	public String getIssueno() {
		return issueno;
	}

	public void setIssueno(String issueno) {
		this.issueno = issueno;
	}

	public ArrayList<Product> getRequestedmedicineList() {
		return requestedmedicineList;
	}

	public void setRequestedmedicineList(ArrayList<Product> requestedmedicineList) {
		this.requestedmedicineList = requestedmedicineList;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getExpectedDate() {
		return expectedDate;
	}

	public void setExpectedDate(String expectedDate) {
		this.expectedDate = expectedDate;
	}

	public String getAdmin_notes() {
		return admin_notes;
	}

	public void setAdmin_notes(String admin_notes) {
		this.admin_notes = admin_notes;
	}

	public String getBilltype() {
		return billtype;
	}

	public void setBilltype(String billtype) {
		this.billtype = billtype;
	}

	public String getSalereturn() {
		return salereturn;
	}

	public void setSalereturn(String salereturn) {
		this.salereturn = salereturn;
	}

	public ArrayList<Product> getOpeningstockList() {
		return openingstockList;
	}

	public void setOpeningstockList(ArrayList<Product> openingstockList) {
		this.openingstockList = openingstockList;
	}

	public ArrayList<Master> getWarehouseList() {
		return warehouseList;
	}

	public void setWarehouseList(ArrayList<Master> warehouseList) {
		this.warehouseList = warehouseList;
	}

	public int getReq_qty() {
		return req_qty;
	}

	public void setReq_qty(int req_qty) {
		this.req_qty = req_qty;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCheck_availability_note() {
		return check_availability_note;
	}

	public void setCheck_availability_note(String check_availability_note) {
		this.check_availability_note = check_availability_note;
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

	public ArrayList<Product> getCallist() {
		return callist;
	}

	public void setCallist(ArrayList<Product> callist) {
		this.callist = callist;
	}

	public String getFilter_status() {
		return filter_status;
	}

	public void setFilter_status(String filter_status) {
		this.filter_status = filter_status;
	}

	public int getAvail_qty() {
		return avail_qty;
	}

	public void setAvail_qty(int avail_qty) {
		this.avail_qty = avail_qty;
	}
	public String getCurr_user() {
		return curr_user;
	}

	public void setCurr_user(String curr_user) {
		this.curr_user = curr_user;
	}
	public String getCheck_availability_date() {
		return check_availability_date;
	}

	public void setCheck_availability_date(String check_availability_date) {
		this.check_availability_date = check_availability_date;
	}
	public String getDirect_transfer() {
		return direct_transfer;
	}

	public void setDirect_transfer(String direct_transfer) {
		this.direct_transfer = direct_transfer;
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

	public ArrayList<Product> getWarehouseListNew() {
		return warehouseListNew;
	}

	public void setWarehouseListNew(ArrayList<Product> warehouseListNew) {
		this.warehouseListNew = warehouseListNew;
	}

	public String getWarehouseids() {
		return warehouseids;
	}

	public void setWarehouseids(String warehouseids) {
		this.warehouseids = warehouseids;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
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
	
	public String getInventory_transfer() {
		return inventory_transfer;
	}

	public void setInventory_transfer(String inventory_transfer) {
		this.inventory_transfer = inventory_transfer;
	}
	public String getStripcheck() {
		return stripcheck;
	}

	public void setStripcheck(String stripcheck) {
		this.stripcheck = stripcheck;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getCatalogueid() {
		return catalogueid;
	}

	public void setCatalogueid(String catalogueid) {
		this.catalogueid = catalogueid;
	}

	public ArrayList<Product> getBincardReportList() {
		return bincardReportList;
	}

	public void setBincardReportList(ArrayList<Product> bincardReportList) {
		this.bincardReportList = bincardReportList;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getIssueqty() {
		return issueqty;
	}

	public void setIssueqty(String issueqty) {
		this.issueqty = issueqty;
	}

	public String getReceiptqty() {
		return receiptqty;
	}

	public void setReceiptqty(String receiptqty) {
		this.receiptqty = receiptqty;
	}

	public ArrayList<Product> getReturnqueList() {
		return returnqueList;
	}

	public void setReturnqueList(ArrayList<Product> returnqueList) {
		this.returnqueList = returnqueList;
	}

	public ArrayList<Product> getProductReturnList() {
		return productReturnList;
	}

	public void setProductReturnList(ArrayList<Product> productReturnList) {
		this.productReturnList = productReturnList;
	}

	public ArrayList<Product> getGrnreturnProductList() {
		return grnreturnProductList;
	}

	public void setGrnreturnProductList(ArrayList<Product> grnreturnProductList) {
		this.grnreturnProductList = grnreturnProductList;
	}

	public String getGrnreturnid() {
		return grnreturnid;
	}

	public void setGrnreturnid(String grnreturnid) {
		this.grnreturnid = grnreturnid;
	}

	public String getSecurity_no() {
		return security_no;
	}

	public void setSecurity_no(String security_no) {
		this.security_no = security_no;
	}

	public String getSecurity_date() {
		return security_date;
	}

	public void setSecurity_date(String security_date) {
		this.security_date = security_date;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public int getTodayreturn() {
		return todayreturn;
	}

	public void setTodayreturn(int todayreturn) {
		this.todayreturn = todayreturn;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPlace() {
		return place;
	}
	

	public void setPlace(String place) {
		this.place = place;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getVoucherno() {
		return voucherno;
	}

	public void setVoucherno(String voucherno) {
		this.voucherno = voucherno;
	}

	public String getReturnedby() {
		return returnedby;
	}

	public void setReturnedby(String returnedby) {
		this.returnedby = returnedby;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getPrintedby() {
		return printedby;
	}

	public void setPrintedby(String printedby) {
		this.printedby = printedby;
	}

	public ArrayList<Product> getProfitlossreportList() {
		return profitlossreportList;
	}

	public void setProfitlossreportList(ArrayList<Product> profitlossreportList) {
		this.profitlossreportList = profitlossreportList;
	}
   public ArrayList<Product> getCountlist() {
		return countlist;
	}

	public void setCountlist(ArrayList<Product> countlist) {
		this.countlist = countlist;
	}
public int getCountval() {
		return countval;
	}

	public void setCountval(int countval) {
		this.countval = countval;
	}


public String getCountname() {
		return countname;
	}

	public void setCountname(String countname) {
		this.countname = countname;
	}


public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}


private int countval;

private ArrayList<Product> countlist;
	
private int page;
private String countname;
private String report_filter;
private String orderby;
private String withstock_filter;
private String opentotalcount;
public String getWithstock_filter() {
	return withstock_filter;
}

public void setWithstock_filter(String withstock_filter) {
	this.withstock_filter = withstock_filter;
}

public String getReport_filter() {
	return report_filter;
}

public void setReport_filter(String report_filter) {
	this.report_filter = report_filter;
}

public String getOrderby() {
	return orderby;
}

public void setOrderby(String orderby) {
	this.orderby = orderby;
}

public String getOpentotalcount() {
	return opentotalcount;
}

public void setOpentotalcount(String opentotalcount) {
	this.opentotalcount = opentotalcount;
}

public double getTotalopeningval() {
	return totalopeningval;
}

public void setTotalopeningval(double totalopeningval) {
	this.totalopeningval = totalopeningval;
}

private String gstamt;
private String netammt;

public String getSearchbyprodname() {
	return searchbyprodname;
}

public void setSearchbyprodname(String searchbyprodname) {
	this.searchbyprodname = searchbyprodname;
}


public String getGstamt() {
	return gstamt;
}

public void setGstamt(String gstamt) {
	this.gstamt = gstamt;
}


public String getNetammt() {
	return netammt;
}

public void setNetammt(String netammt) {
	this.netammt = netammt;
}


public int getTotaldebit() {
	return totaldebit;
}

public void setTotaldebit(int totaldebit) {
	this.totaldebit = totaldebit;
}


public String getPaymode() {
	return paymode;
}

public void setPaymode(String paymode) {
	this.paymode = paymode;
}


public int getFinaldeciamount() {
	return finaldeciamount;
}

public void setFinaldeciamount(int finaldeciamount) {
	this.finaldeciamount = finaldeciamount;
}


public double getTtldisc() {
	return ttldisc;
}

public void setTtldisc(double ttldisc) {
	this.ttldisc = ttldisc;
}


public ArrayList<Product> getChildtransferlist() {
	return childtransferlist;
}

public void setChildtransferlist(ArrayList<Product> childtransferlist) {
	this.childtransferlist = childtransferlist;
}


private double totalopeningval;
private int totaldebit;
private String searchbyprodname;
private String paymode;
private int finaldeciamount;
private double ttldisc;
} 
