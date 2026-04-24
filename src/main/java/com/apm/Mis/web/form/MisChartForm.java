package com.apm.Mis.web.form;

import java.io.File;
import java.util.ArrayList;

import com.apm.Accounts.eu.entity.Accounts;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.Emr.eu.entity.Investigation;
import com.apm.Expence.eu.entity.Expence;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Master.eu.entity.Master;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Report.eu.entity.MisReport;
import com.apm.TreatmentEpisode.eu.entity.TreatmentEpisode;

public class MisChartForm {
	
	private int newaddmissiontoday;
	private String paymodecash;
	private String paymodecheque;
	private ArrayList<MisReport> kpiarealist;
	private String kpiarea_filter;
	private String month_filter;
	private int totalcancel;
	private ArrayList<MisReport> submoduleList;
	private String filesubsubmodule;
	private String filesubserachkey;
	private String querysearch;
	private int newregpatient;

	private String filesubtitle, filesubfeatures;
	private ArrayList<DiaryManagement>userList;
	private int totalinvrevenue;
	
	public int getTotalinvrevenue() {
		return totalinvrevenue;
	}

	public void setTotalinvrevenue(int totalinvrevenue) {
		this.totalinvrevenue = totalinvrevenue;
	}
	
	public ArrayList<DiaryManagement> getUserList() {
		return userList;
	}

	public void setUserList(ArrayList<DiaryManagement> userList) {
		this.userList = userList;
	}

	public String getFilesubtitle() {
		return filesubtitle;
	}

	public void setFilesubtitle(String filesubtitle) {
		this.filesubtitle = filesubtitle;
	}

	public String getFilesubfeatures() {
		return filesubfeatures;
	}

	public void setFilesubfeatures(String filesubfeatures) {
		this.filesubfeatures = filesubfeatures;
	}

	public String getQuerysearch() {
		return querysearch;
	}

	public void setQuerysearch(String querysearch) {
		this.querysearch = querysearch;
	}

	public String getFilesubserachkey() {
		return filesubserachkey;
	}

	public void setFilesubserachkey(String filesubserachkey) {
		this.filesubserachkey = filesubserachkey;
	}

	public String getFilesubsubmodule() {
		return filesubsubmodule;
	}

	public void setFilesubsubmodule(String filesubsubmodule) {
		this.filesubsubmodule = filesubsubmodule;
	}

	public ArrayList<MisReport> getSubmoduleList() {
		return submoduleList;
	}

	public void setSubmoduleList(ArrayList<MisReport> submoduleList) {
		this.submoduleList = submoduleList;
	}

	public ArrayList<MisReport> getNabhsubcatagoylist() {
		return nabhsubcatagoylist;
	}

	public void setNabhsubcatagoylist(ArrayList<MisReport> nabhsubcatagoylist) {
		this.nabhsubcatagoylist = nabhsubcatagoylist;
	}

	private String isKPI;
	private int noofcasuality;
	private int noofdaycare;
	public int getNoofcasuality() {
		return noofcasuality;
	}

	public void setNoofcasuality(int noofcasuality) {
		this.noofcasuality = noofcasuality;
	}

	public int getNoofdaycare() {
		return noofdaycare;
	}

	public void setNoofdaycare(int noofdaycare) {
		this.noofdaycare = noofdaycare;
	}

	private String kpimis;
	private ArrayList<MisReport> nabhsubcatagoylist;
	private String nabhcatagory;
	private ArrayList<MisReport> nabhcatagoylist;
	private String subcatname;
	private ArrayList<MisReport>  selfassementtoollist;
	private String sat_year_filter;
	private String sat_month_filter;
	private String nabhsubcatagory;
	private ArrayList<MisReport> opdappointmenttype;
	private int totalpathlab,totalradiology,totalmaicrobiology,totalendoscopy,totalcardiology;
	private int totaldeath;
	private int totalDAMA;
	private int totalmlcaddmission;
	private int totalctinvest;
	private int totalmricount;
	private int totalxraycount;
	private int totalsonographycount;
	private int totalcardiologycount;
	private ArrayList<MisReport>  accadmictrackerlist;
	private int totalopdcompleted ;
	private int totalopddna ;
	private String subcategoryid;
	private String areaid;
	private ArrayList<MisReport> subcategorylist;
	private ArrayList<MisReport> arealist;
	private File filesub_uploadfiles;
	private ArrayList<MisReport> filesubmmissionlist;
	private String subuploadfilesContentType;
	private String subuploadfilesFileName;
	private File subuploadfiles;
	private String filesubmission_category;
	private String filesubremark;
	private int chcklistid;
	private int chkliststatus;
	private ArrayList<MisReport> indicatorlist;
	private String indicator;
	private String remark;
	

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIndicator() {
		return indicator;
	}

	public void setIndicator(String indicator) {
		this.indicator = indicator;
	}

	public ArrayList<MisReport> getIndicatorlist() {
		return indicatorlist;
	}

	public void setIndicatorlist(ArrayList<MisReport> indicatorlist) {
		this.indicatorlist = indicatorlist;
	}

	public int getChkliststatus() {
		return chkliststatus;
	}

	public void setChkliststatus(int chkliststatus) {
		this.chkliststatus = chkliststatus;
	}

	public int getChcklistid() {
		return chcklistid;
	}

	public void setChcklistid(int chcklistid) {
		this.chcklistid = chcklistid;
	}

	public String getFilesubmission_category() {
		return filesubmission_category;
	}

	public void setFilesubmission_category(String filesubmission_category) {
		this.filesubmission_category = filesubmission_category;
	}

	public String getFilesubremark() {
		return filesubremark;
	}

	public void setFilesubremark(String filesubremark) {
		this.filesubremark = filesubremark;
	}

	public String getSubuploadfilesContentType() {
		return subuploadfilesContentType;
	}

	public void setSubuploadfilesContentType(String subuploadfilesContentType) {
		this.subuploadfilesContentType = subuploadfilesContentType;
	}

	public String getSubuploadfilesFileName() {
		return subuploadfilesFileName;
	}

	public void setSubuploadfilesFileName(String subuploadfilesFileName) {
		this.subuploadfilesFileName = subuploadfilesFileName;
	}

	public File getSubuploadfiles() {
		return subuploadfiles;
	}

	public void setSubuploadfiles(File subuploadfiles) {
		this.subuploadfiles = subuploadfiles;
	}

	public ArrayList<MisReport> getFilesubmmissionlist() {
		return filesubmmissionlist;
	}

	public void setFilesubmmissionlist(ArrayList<MisReport> filesubmmissionlist) {
		this.filesubmmissionlist = filesubmmissionlist;
	}

	public File getFilesub_uploadfiles() {
		return filesub_uploadfiles;
	}

	public void setFilesub_uploadfiles(File filesub_uploadfiles) {
		this.filesub_uploadfiles = filesub_uploadfiles;
	}

	public ArrayList<MisReport> getSubcategorylist() {
		return subcategorylist;
	}

	public void setSubcategorylist(ArrayList<MisReport> subcategorylist) {
		this.subcategorylist = subcategorylist;
	}

	public ArrayList<MisReport> getArealist() {
		return arealist;
	}

	public void setArealist(ArrayList<MisReport> arealist) {
		this.arealist = arealist;
	}

	public String getAreaid() {
		return areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}

	public String getSubcategoryid() {
		return subcategoryid;
	}

	public void setSubcategoryid(String subcategoryid) {
		this.subcategoryid = subcategoryid;
	}

	public int getTotalopdcompleted() {
		return totalopdcompleted;
	}

	public void setTotalopdcompleted(int totalopdcompleted) {
		this.totalopdcompleted = totalopdcompleted;
	}

	public int getTotalopddna() {
		return totalopddna;
	}

	public void setTotalopddna(int totalopddna) {
		this.totalopddna = totalopddna;
	}

	public ArrayList<MisReport> getAccadmictrackerlist() {
		return accadmictrackerlist;
	}

	public void setAccadmictrackerlist(ArrayList<MisReport> accadmictrackerlist) {
		this.accadmictrackerlist = accadmictrackerlist;
	}

	public int getTotalcardiologycount() {
		return totalcardiologycount;
	}

	public void setTotalcardiologycount(int totalcardiologycount) {
		this.totalcardiologycount = totalcardiologycount;
	}

	public int getTotalmricount() {
		return totalmricount;
	}

	public void setTotalmricount(int totalmricount) {
		this.totalmricount = totalmricount;
	}

	public int getTotalxraycount() {
		return totalxraycount;
	}

	public void setTotalxraycount(int totalxraycount) {
		this.totalxraycount = totalxraycount;
	}

	public int getTotalsonographycount() {
		return totalsonographycount;
	}

	public void setTotalsonographycount(int totalsonographycount) {
		this.totalsonographycount = totalsonographycount;
	}

	public int getTotalctinvest() {
		return totalctinvest;
	}

	public void setTotalctinvest(int totalctinvest) {
		this.totalctinvest = totalctinvest;
	}

	public int getTotalmlcaddmission() {
		return totalmlcaddmission;
	}

	public void setTotalmlcaddmission(int totalmlcaddmission) {
		this.totalmlcaddmission = totalmlcaddmission;
	}

	public int getTotalpathlab() {
		return totalpathlab;
	}

	public void setTotalpathlab(int totalpathlab) {
		this.totalpathlab = totalpathlab;
	}

	public int getTotalradiology() {
		return totalradiology;
	}

	public void setTotalradiology(int totalradiology) {
		this.totalradiology = totalradiology;
	}

	public int getTotalmaicrobiology() {
		return totalmaicrobiology;
	}

	public void setTotalmaicrobiology(int totalmaicrobiology) {
		this.totalmaicrobiology = totalmaicrobiology;
	}

	public int getTotalendoscopy() {
		return totalendoscopy;
	}

	public void setTotalendoscopy(int totalendoscopy) {
		this.totalendoscopy = totalendoscopy;
	}

	public int getTotalcardiology() {
		return totalcardiology;
	}

	public void setTotalcardiology(int totalcardiology) {
		this.totalcardiology = totalcardiology;
	}

	public int getTotaldeath() {
		return totaldeath;
	}

	public void setTotaldeath(int totaldeath) {
		this.totaldeath = totaldeath;
	}

	public int getTotalDAMA() {
		return totalDAMA;
	}

	public void setTotalDAMA(int totalDAMA) {
		this.totalDAMA = totalDAMA;
	}

	public ArrayList<MisReport> getOpdappointmenttype() {
		return opdappointmenttype;
	}

	public void setOpdappointmenttype(ArrayList<MisReport> opdappointmenttype) {
		this.opdappointmenttype = opdappointmenttype;
	}

	public String getNabhsubcatagory() {
		return nabhsubcatagory;
	}

	public void setNabhsubcatagory(String nabhsubcatagory) {
		this.nabhsubcatagory = nabhsubcatagory;
	}

	public String getSat_year_filter() {
		return sat_year_filter;
	}

	public void setSat_year_filter(String sat_year_filter) {
		this.sat_year_filter = sat_year_filter;
	}

	public String getSat_month_filter() {
		return sat_month_filter;
	}

	public void setSat_month_filter(String sat_month_filter) {
		this.sat_month_filter = sat_month_filter;
	}

	public ArrayList<MisReport> getSelfassementtoollist() {
		return selfassementtoollist;
	}

	public void setSelfassementtoollist(ArrayList<MisReport> selfassementtoollist) {
		this.selfassementtoollist = selfassementtoollist;
	}

	public String getSubcatname() {
		return subcatname;
	}

	public void setSubcatname(String subcatname) {
		this.subcatname = subcatname;
	}

	public ArrayList<MisReport> getNabhcatagoylist() {
		return nabhcatagoylist;
	}

	public void setNabhcatagoylist(ArrayList<MisReport> nabhcatagoylist) {
		this.nabhcatagoylist = nabhcatagoylist;
	}

	public String getNabhcatagory() {
		return nabhcatagory;
	}

	public void setNabhcatagory(String nabhcatagory) {
		this.nabhcatagory = nabhcatagory;
	}

	public String getKpimis() {
		return kpimis;
	}

	public void setKpimis(String kpimis) {
		this.kpimis = kpimis;
	}

	private ArrayList<MisReport>  kpilist;
	public ArrayList<MisReport> getKpilist() {
		return kpilist;
	}

	public void setKpilist(ArrayList<MisReport> kpilist) {
		this.kpilist = kpilist;
	}
	
	public String getIsKPI() {
		return isKPI;
	}

	public void setIsKPI(String isKPI) {
		this.isKPI = isKPI;
	}

	public String getKpiarea_filter() {
		return kpiarea_filter;
	}

	public void setKpiarea_filter(String kpiarea_filter) {
		this.kpiarea_filter = kpiarea_filter;
	}

	public String getMonth_filter() {
		return month_filter;
	}

	public void setMonth_filter(String month_filter) {
		this.month_filter = month_filter;
	}

	public String getYear_filter() {
		return year_filter;
	}

	public void setYear_filter(String year_filter) {
		this.year_filter = year_filter;
	}

	private String year_filter;
	
	public ArrayList<MisReport> getKpiarealist() {
		return kpiarealist;
	}

	public void setKpiarealist(ArrayList<MisReport> kpiarealist) {
		this.kpiarealist = kpiarealist;
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

	public int getNewaddmissiontoday() {
		return newaddmissiontoday;
	}

	public void setNewaddmissiontoday(int newaddmissiontoday) {
		this.newaddmissiontoday = newaddmissiontoday;
	}

	private int id;
	private ArrayList<Master> locationListPharmacy;
	private String fromDate = "";
	private String pharmacy_location;
	private String filter_location;
	private String toDate = "";
	
	private int totalOpdPatient;
	private int bookedAppointment;
	private int totaldna;
	private int totalCompleted;
	
	private String graphName;
	
	private int newadmission;
	private int inhousepatients;
	private int dischargepatients;
	private String type;
	
	private int totalbeds;
	private int undermaintnancebed;
	private int occupiedbed;
	private int availablebed;
	
	
	private int chargeaddedd;
	private int invoicegenerated;
	private int paymentrecieved;
	private int deleted_investigation;
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
	private int transfered;
	private int totalindent;
	
	private ArrayList<Accounts> patientviewbypackage;
	
	public ArrayList<Accounts> getPatientviewbypackage() {
		return patientviewbypackage;
	}

	public void setPatientviewbypackage(ArrayList<Accounts> patientviewbypackage) {
		this.patientviewbypackage = patientviewbypackage;
	}

	public int getTotalindent() {
		return totalindent;
	}

	public void setTotalindent(int totalindent) {
		this.totalindent = totalindent;
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

	public int getTransfered() {
		return transfered;
	}

	public void setTransfered(int transfered) {
		this.transfered = transfered;
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

	private int received;
	private int readytotransfer;
	private int returned;
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

	public int getDeleted_investigation() {
		return deleted_investigation;
	}

	public void setDeleted_investigation(int deleted_investigation) {
		this.deleted_investigation = deleted_investigation;
	}

	private int totalprescription;
	public int getTotalprescription() {
		return totalprescription;
	}

	public void setTotalprescription(int totalprescription) {
		this.totalprescription = totalprescription;
	}

	public int getNotdelivered() {
		return notdelivered;
	}

	public void setNotdelivered(int notdelivered) {
		this.notdelivered = notdelivered;
	}

	public int getDelivered() {
		return delivered;
	}

	public void setDelivered(int delivered) {
		this.delivered = delivered;
	}

	private int notdelivered;
	private int delivered;
	
	
	private int upipayment;

	private int totalPayment;
	private int cashpayment;
	private int chequepayment;
	private int cardPayment;
	
	private ArrayList<Expence>expenseList;
	private ArrayList<Expence>paymenmodeList;
	private String totalExpence;
	
	private int totalopdseen;
	private int ipdnewadmission;
	private int ipdOldPatient;
	private int totalipdopdpatient;
	
	private ArrayList<Client>patientbycondition;
	private int requestedprescription;
	private int requestedInvestigation;
	private int totalClinicalView;
	
	private ArrayList<Client>patientbylocation;
	private int totalPatient;
	
	private double montwiseAdmission;
	private double montwiseBedOccupy;
	private double monthwiseinvoiucegeneated;
	private double montwisepaymentrecieved;
	

	private ArrayList<Client> opdPatientReport;
	private ArrayList<Client> opdPatientCancelReport;
    private ArrayList<Bed> ipdPatientReport;	

	public ArrayList<Priscription> priscriptionReport;
    private ArrayList<Bed> wardwisereport;
    
    public ArrayList<Bed> getWardwisereport() {
		return wardwisereport;
	}

	public void setWardwisereport(ArrayList<Bed> wardwisereport) {
		this.wardwisereport = wardwisereport;
	}
	public ArrayList<Priscription> getPriscriptionReport() {
		return priscriptionReport;
	}

	public void setPriscriptionReport(ArrayList<Priscription> priscriptionReport) {
		this.priscriptionReport = priscriptionReport;
	}

	private ArrayList<Bed> bedlist;
	private ArrayList<Priscription> pharmacydailysalelist;
	private ArrayList<Investigation> investigationlist;
	private String investigation_status;
	private int new_invistigation;
	private int new_collected;
	private int new_completed;
	private int new_aproved;
	private String totalInvestigation;
	public int getUpipayment() {
		return upipayment;
	}

	public void setUpipayment(int upipayment) {
		this.upipayment = upipayment;
	}
	public String getTotalInvestigation() {
		return totalInvestigation;
	}

	public void setTotalInvestigation(String totalInvestigation) {
		this.totalInvestigation = totalInvestigation;
	}

	public int getNew_invistigation() {
		return new_invistigation;
	}

	public void setNew_invistigation(int new_invistigation) {
		this.new_invistigation = new_invistigation;
	}

	public int getNew_collected() {
		return new_collected;
	}

	public void setNew_collected(int new_collected) {
		this.new_collected = new_collected;
	}

	public int getNew_completed() {
		return new_completed;
	}

	public void setNew_completed(int new_completed) {
		this.new_completed = new_completed;
	}

	public int getNew_aproved() {
		return new_aproved;
	}

	public void setNew_aproved(int new_aproved) {
		this.new_aproved = new_aproved;
	}

	public ArrayList<Investigation> getInvestigationlist() {
		return investigationlist;
	}

	public String getInvestigation_status() {
		return investigation_status;
	}

	public void setInvestigation_status(String investigation_status) {
		this.investigation_status = investigation_status;
	}

	public void setInvestigationlist(ArrayList<Investigation> investigationlist) {
		this.investigationlist = investigationlist;
	}

	public String getFilter_ward() {
		return filter_ward;
	}

	public void setFilter_ward(String filter_ward) {
		this.filter_ward = filter_ward;
	}

	private String filter_ward;
	public ArrayList<Priscription> getPharmacydailysalelist() {
		return pharmacydailysalelist;
	}

	public void setPharmacydailysalelist(ArrayList<Priscription> pharmacydailysalelist) {
		this.pharmacydailysalelist = pharmacydailysalelist;
	}

	private long notCompleted;
	
	private String currentMonth;
	
	private boolean misreport;
	
	private String action;
	
	private long advanced,refund;
	
	private long expenseTotal;
	
	private double ipdper,opdper,otper;
	
	private ArrayList<Client> dailySummaryList,dailySummaryList1;
    
	private ArrayList<Accounts> invoiceList;
	
	private ArrayList<Accounts> advancedRefundList;
	private ArrayList<Accounts> accountinfo,accountinfo1;
	private int otPatientCount;
	
	ArrayList<TreatmentEpisode> treatmentEpisodeList;	
	ArrayList<Expence> expenseListReport;
	
	
    
	private long otherPayment;
	
	
	private long totalPayAll;
	
	private String invoicecategory = "2";
	
	
	ArrayList<Client> clinicalViewList;
	
	//@  for pharmacy report
	private String creditReturn;
	private String totalcredit;
	private String hospitalReturn;
	private String todaycard;
	private String todaycash;
	private String todaydisc;
	private String todayReturn;
	private String neftpayment;
	private String totalpayment;
	private String balance;
	private String total;
	private String hospital;
	private String chequepayments;
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public boolean isMisreport() {
		return misreport;
	}

	public void setMisreport(boolean misreport) {
		this.misreport = misreport;
	}

	public ArrayList<Client> getClinicalViewList() {
		return clinicalViewList;
	}

	public void setClinicalViewList(ArrayList<Client> clinicalViewList) {
		this.clinicalViewList = clinicalViewList;
	}

	public String getInvoicecategory() {
		return invoicecategory;
	}

	public void setInvoicecategory(String invoicecategory) {
		this.invoicecategory = invoicecategory;
	}

	public long getOtherPayment() {
		return otherPayment;
	}

	public void setOtherPayment(long otherPayment) {
		this.otherPayment = otherPayment;
	}

	public long getTotalPayAll() {
		return totalPayAll;
	}

	public void setTotalPayAll(long totalPayAll) {
		this.totalPayAll = totalPayAll;
	}

	public int getOtPatientCount() {
		return otPatientCount;
	}

	public void setOtPatientCount(int otPatientCount) {
		this.otPatientCount = otPatientCount;
	}

	public ArrayList<Accounts> getAccountinfo() {
		return accountinfo;
	}

	public void setAccountinfo(ArrayList<Accounts> accountinfo) {
		this.accountinfo = accountinfo;
	}

	public ArrayList<Accounts> getAdvancedRefundList() {
		return advancedRefundList;
	}

	public void setAdvancedRefundList(ArrayList<Accounts> advancedRefundList) {
		this.advancedRefundList = advancedRefundList;
	}

	public ArrayList<Accounts> getInvoiceList() {
		return invoiceList;
	}

	public void setInvoiceList(ArrayList<Accounts> invoiceList) {
		this.invoiceList = invoiceList;
	}

	public ArrayList<Client> getDailySummaryList() {
		return dailySummaryList;
	}

	public void setDailySummaryList(ArrayList<Client> dailySummaryList) {
		this.dailySummaryList = dailySummaryList;
	}

	public ArrayList<Client> getDailySummaryList1() {
		return dailySummaryList1;
	}

	public void setDailySummaryList1(ArrayList<Client> dailySummaryList1) {
		this.dailySummaryList1 = dailySummaryList1;
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

	public double getOtper() {
		return otper;
	}

	public void setOtper(double otper) {
		this.otper = otper;
	}

	public long getExpenseTotal() {
		return expenseTotal;
	}

	public void setExpenseTotal(long expenseTotal) {
		this.expenseTotal = expenseTotal;
	}

	public long getAdvanced() {
		return advanced;
	}

	public void setAdvanced(long advanced) {
		this.advanced = advanced;
	}

	public long getRefund() {
		return refund;
	}

	public void setRefund(long refund) {
		this.refund = refund;
	}

	public ArrayList<Bed> getBedlist() {
		return bedlist;
	}

	public void setBedlist(ArrayList<Bed> bedlist) {
		this.bedlist = bedlist;
	}

	public ArrayList<Bed> getIpdPatientReport() {
		return ipdPatientReport;
	}

	public void setIpdPatientReport(ArrayList<Bed> ipdPatientReport) {
		this.ipdPatientReport = ipdPatientReport;
	}

	public ArrayList<Client> getOpdPatientReport() {
		return opdPatientReport;
	}

	public void setOpdPatientReport(ArrayList<Client> opdPatientReport) {
		this.opdPatientReport = opdPatientReport;
	}

	public double getMontwiseAdmission() {
		return montwiseAdmission;
	}

	public void setMontwiseAdmission(double montwiseAdmission) {
		this.montwiseAdmission = montwiseAdmission;
	}

	public double getMontwiseBedOccupy() {
		return montwiseBedOccupy;
	}

	public void setMontwiseBedOccupy(double montwiseBedOccupy) {
		this.montwiseBedOccupy = montwiseBedOccupy;
	}

	public double getMonthwiseinvoiucegeneated() {
		return monthwiseinvoiucegeneated;
	}

	public void setMonthwiseinvoiucegeneated(double monthwiseinvoiucegeneated) {
		this.monthwiseinvoiucegeneated = monthwiseinvoiucegeneated;
	}

	public double getMontwisepaymentrecieved() {
		return montwisepaymentrecieved;
	}

	public void setMontwisepaymentrecieved(double montwisepaymentrecieved) {
		this.montwisepaymentrecieved = montwisepaymentrecieved;
	}

	public int getTotalPatient() {
		return totalPatient;
	}

	public void setTotalPatient(int totalPatient) {
		this.totalPatient = totalPatient;
	}

	public ArrayList<Client> getPatientbylocation() {
		return patientbylocation;
	}

	public void setPatientbylocation(ArrayList<Client> patientbylocation) {
		this.patientbylocation = patientbylocation;
	}

	public int getTotalClinicalView() {
		return totalClinicalView;
	}

	public void setTotalClinicalView(int totalClinicalView) {
		this.totalClinicalView = totalClinicalView;
	}

	public ArrayList<Client> getPatientbycondition() {
		return patientbycondition;
	}

	public void setPatientbycondition(ArrayList<Client> patientbycondition) {
		this.patientbycondition = patientbycondition;
	}

	public int getRequestedprescription() {
		return requestedprescription;
	}

	public void setRequestedprescription(int requestedprescription) {
		this.requestedprescription = requestedprescription;
	}

	public int getRequestedInvestigation() {
		return requestedInvestigation;
	}

	public void setRequestedInvestigation(int requestedInvestigation) {
		this.requestedInvestigation = requestedInvestigation;
	}

	public int getTotalipdopdpatient() {
		return totalipdopdpatient;
	}

	public void setTotalipdopdpatient(int totalipdopdpatient) {
		this.totalipdopdpatient = totalipdopdpatient;
	}

	public int getTotalopdseen() {
		return totalopdseen;
	}

	public void setTotalopdseen(int totalopdseen) {
		this.totalopdseen = totalopdseen;
	}

	public int getIpdnewadmission() {
		return ipdnewadmission;
	}

	public void setIpdnewadmission(int ipdnewadmission) {
		this.ipdnewadmission = ipdnewadmission;
	}

	public int getIpdOldPatient() {
		return ipdOldPatient;
	}

	public void setIpdOldPatient(int ipdOldPatient) {
		this.ipdOldPatient = ipdOldPatient;
	}

	public String getTotalExpence() {
		return totalExpence;
	}

	public void setTotalExpence(String totalExpence) {
		this.totalExpence = totalExpence;
	}

	public ArrayList<Expence> getPaymenmodeList() {
		return paymenmodeList;
	}

	public void setPaymenmodeList(ArrayList<Expence> paymenmodeList) {
		this.paymenmodeList = paymenmodeList;
	}

	public ArrayList<Expence> getExpenseList() {
		return expenseList;
	}

	public void setExpenseList(ArrayList<Expence> expenseList) {
		this.expenseList = expenseList;
	}

	public int getTotalPayment() {
		return totalPayment;
	}

	public void setTotalPayment(int totalPayment) {
		this.totalPayment = totalPayment;
	}

	public int getCashpayment() {
		return cashpayment;
	}

	public void setCashpayment(int cashpayment) {
		this.cashpayment = cashpayment;
	}

	public int getChequepayment() {
		return chequepayment;
	}

	public void setChequepayment(int chequepayment) {
		this.chequepayment = chequepayment;
	}

	public int getCardPayment() {
		return cardPayment;
	}

	public void setCardPayment(int cardPayment) {
		this.cardPayment = cardPayment;
	}

	public int getChargeaddedd() {
		return chargeaddedd;
	}

	public void setChargeaddedd(int chargeaddedd) {
		this.chargeaddedd = chargeaddedd;
	}

	public int getInvoicegenerated() {
		return invoicegenerated;
	}

	public void setInvoicegenerated(int invoicegenerated) {
		this.invoicegenerated = invoicegenerated;
	}

	public int getPaymentrecieved() {
		return paymentrecieved;
	}

	public void setPaymentrecieved(int paymentrecieved) {
		this.paymentrecieved = paymentrecieved;
	}

	public int getTotalbeds() {
		return totalbeds;
	}

	public void setTotalbeds(int totalbeds) {
		this.totalbeds = totalbeds;
	}

	public int getUndermaintnancebed() {
		return undermaintnancebed;
	}

	public void setUndermaintnancebed(int undermaintnancebed) {
		this.undermaintnancebed = undermaintnancebed;
	}

	public int getOccupiedbed() {
		return occupiedbed;
	}

	public void setOccupiedbed(int occupiedbed) {
		this.occupiedbed = occupiedbed;
	}

	public int getAvailablebed() {
		return availablebed;
	}

	public void setAvailablebed(int availablebed) {
		this.availablebed = availablebed;
	}

	public int getNewadmission() {
		return newadmission;
	}

	public void setNewadmission(int newadmission) {
		this.newadmission = newadmission;
	}

	public int getInhousepatients() {
		return inhousepatients;
	}

	public void setInhousepatients(int inhousepatients) {
		this.inhousepatients = inhousepatients;
	}

	public int getDischargepatients() {
		return dischargepatients;
	}

	public void setDischargepatients(int dischargepatients) {
		this.dischargepatients = dischargepatients;
	}

	public String getGraphName() {
		return graphName;
	}

	public void setGraphName(String graphName) {
		this.graphName = graphName;
	}

	public int getTotalCompleted() {
		return totalCompleted;
	}

	public void setTotalCompleted(int totalCompleted) {
		this.totalCompleted = totalCompleted;
	}

	public int getTotalOpdPatient() {
		return totalOpdPatient;
	}

	public void setTotalOpdPatient(int totalOpdPatient) {
		this.totalOpdPatient = totalOpdPatient;
	}

	public int getBookedAppointment() {
		return bookedAppointment;
	}

	public void setBookedAppointment(int bookedAppointment) {
		this.bookedAppointment = bookedAppointment;
	}

	public int getTotaldna() {
		return totaldna;
	}

	public void setTotaldna(int totaldna) {
		this.totaldna = totaldna;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public ArrayList<Accounts> getAccountinfo1() {
		return accountinfo1;
	}

	public void setAccountinfo1(ArrayList<Accounts> accountinfo1) {
		this.accountinfo1 = accountinfo1;
	}

	public ArrayList<TreatmentEpisode> getTreatmentEpisodeList() {
		return treatmentEpisodeList;
	}

	public void setTreatmentEpisodeList(
			ArrayList<TreatmentEpisode> treatmentEpisodeList) {
		this.treatmentEpisodeList = treatmentEpisodeList;
	}

	public ArrayList<Expence> getExpenseListReport() {
		return expenseListReport;
	}

	public void setExpenseListReport(ArrayList<Expence> expenseListReport) {
		this.expenseListReport = expenseListReport;
	}

	public long getNotCompleted() {
		return notCompleted;
	}

	public void setNotCompleted(long notCompleted) {
		this.notCompleted = notCompleted;
	}

	public String getCurrentMonth() {
		return currentMonth;
	}

	public void setCurrentMonth(String currentMonth) {
		this.currentMonth = currentMonth;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
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

	public ArrayList<Master> getLocationListPharmacy() {
		return locationListPharmacy;
	}

	public void setLocationListPharmacy(ArrayList<Master> locationListPharmacy) {
		this.locationListPharmacy = locationListPharmacy;
	}

	public String getPharmacy_location() {
		return pharmacy_location;
	}

	public void setPharmacy_location(String pharmacy_location) {
		this.pharmacy_location = pharmacy_location;
	}

	public String getFilter_location() {
		return filter_location;
	}

	public void setFilter_location(String filter_location) {
		this.filter_location = filter_location;
	}
	
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

	public ArrayList<Client> getOpdPatientCancelReport() {
		return opdPatientCancelReport;
	}

	public void setOpdPatientCancelReport(ArrayList<Client> opdPatientCancelReport) {
		this.opdPatientCancelReport = opdPatientCancelReport;
	}

	public int getTotalcancel() {
		return totalcancel;
	}

	public void setTotalcancel(int totalcancel) {
		this.totalcancel = totalcancel;
	}

	public int getNewregpatient() {
		return newregpatient;
	}

	public void setNewregpatient(int newregpatient) {
		this.newregpatient = newregpatient;
	}
	

}
