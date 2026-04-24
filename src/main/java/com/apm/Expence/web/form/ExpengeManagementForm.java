package com.apm.Expence.web.form;

import java.util.ArrayList;
import java.util.Collection;

import com.apm.Expence.eu.entity.Expence;
import com.apm.Master.eu.entity.Master;
import com.apm.Registration.eu.entity.Clinic;

public class ExpengeManagementForm {
	private String range;
	private String paymentmode;
	private String totalinword;
	
	ArrayList<Master>debitorList;
	private String debiorname;
	
	private String dbitamt = "0";
	
	private String credit;
	
	private String contratrans;
	
	private String ctotal;
	
	private String parentid;
	
	private String name;
	
	private String vaddress;
	
	
	
	
	public String getVaddress() {
		return vaddress;
	}
	public void setVaddress(String vaddress) {
		this.vaddress = vaddress;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public String getEditaction() {
		return editaction;
	}
	public void setEditaction(String editaction) {
		this.editaction = editaction;
	}
	private String showcd;
	
	private String editaction = "0";
	
	private String editledgername;
	
	
	
	
	
	public String getEditledgername() {
		return editledgername;
	}
	public void setEditledgername(String editledgername) {
		this.editledgername = editledgername;
	}
	public String getShowcd() {
		return showcd;
	}
	public void setShowcd(String showcd) {
		this.showcd = showcd;
	}
	public String getCtotal() {
		return ctotal;
	}
	public void setCtotal(String ctotal) {
		this.ctotal = ctotal;
	}
	public String getContratrans() {
		return contratrans;
	}
	public void setContratrans(String contratrans) {
		this.contratrans = contratrans;
	}
	public String getCredit() {
		return credit;
	}
	public void setCredit(String credit) {
		this.credit = credit;
	}
	public String getDbitamt() {
		return dbitamt;
	}
	public void setDbitamt(String dbitamt) {
		this.dbitamt = dbitamt;
	}
	public ArrayList<Master> getDebitorList() {
		return debitorList;
	}
	public void setDebitorList(ArrayList<Master> debitorList) {
		this.debitorList = debitorList;
	}
	public String getDebiorname() {
		return debiorname;
	}
	public void setDebiorname(String debiorname) {
		this.debiorname = debiorname;
	}
	public String getTotalinword() {
		return totalinword;
	}
	public void setTotalinword(String totalinword) {
		this.totalinword = totalinword;
	}
	public String getRange() {
		return range;
	}
	public void setRange(String range) {
		this.range = range;
	}
	public String getPaymentmode() {
		return paymentmode;
	}
	public void setPaymentmode(String paymentmode) {
		this.paymentmode = paymentmode;
	}
	private String paymentmodevalue;
	private String expensetypevalue;
	private String rangevalue;
	private String expenseType;
	private String mainpaymentmode;
	ArrayList<Master>ledgerList;
	private String ledgername;
	
	private String paymantto;
	private String epayment;
	private String pmode;
	private String clinicLogo;
		private String clinicemail;
		private String clinicaddress;
		private String clinicity;
		private String websiteUrl;
		private String landLine;
		private String owner_qualification;
		private ArrayList<Clinic> locationAdressList;
	    private String address;
	
	
	
	
	public String getClinicLogo() {
			return clinicLogo;
		}
		public void setClinicLogo(String clinicLogo) {
			this.clinicLogo = clinicLogo;
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
	public String getPaymantto() {
		return paymantto;
	}
	public void setPaymantto(String paymantto) {
		this.paymantto = paymantto;
	}
	public String getEpayment() {
		return epayment;
	}
	public void setEpayment(String epayment) {
		this.epayment = epayment;
	}
	public String getPmode() {
		return pmode;
	}
	public void setPmode(String pmode) {
		this.pmode = pmode;
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
	public String getMainpaymentmode() {
		return mainpaymentmode;
	}
	public void setMainpaymentmode(String mainpaymentmode) {
		this.mainpaymentmode = mainpaymentmode;
	}
	public String getExpenseType() {
		return expenseType;
	}
	public void setExpenseType(String expenseType) {
		this.expenseType = expenseType;
	}
	private int id;
	private String status;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	private String createdate;
	private String createby;
	private String printby;
	private String printdate;
	private int totalRecords;
	private String pagerecords;
	private String date;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public String getCreateby() {
		return createby;
	}
	public void setCreateby(String createby) {
		this.createby = createby;
	}
	public String getPrintby() {
		return printby;
	}
	public void setPrintby(String printby) {
		this.printby = printby;
	}
	public String getPrintdate() {
		return printdate;
	}
	public void setPrintdate(String printdate) {
		this.printdate = printdate;
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
	
	
	
	public String getPaymentmodevalue() {
		return paymentmodevalue;
	}
	public void setPaymentmodevalue(String paymentmodevalue) {
		this.paymentmodevalue = paymentmodevalue;
	}
	public String getExpensetypevalue() {
		return expensetypevalue;
	}
	public void setExpensetypevalue(String expensetypevalue) {
		this.expensetypevalue = expensetypevalue;
	}
	public String getRangevalue() {
		return rangevalue;
	}
	public void setRangevalue(String rangevalue) {
		this.rangevalue = rangevalue;
	}
	private String caldate;
	
	private String amount;
	
	private String merchant;
	private String lastmodified;
	private String category;
	private String paidby;
	private String comments;
	
	private ArrayList<Expence>expenceList;
	
	private String expenceTotal;
	
	private String totalExpenceCheckbox;
	
	private String reportName;
	
	private ArrayList<Expence>reportList;
	
	private String clinicName;
	
	private String clinicOwner;
	
	private String country;
	
	private ArrayList<Expence>reportExpenList;
	
	private String reportDate;
	
	private  ArrayList<Expence> categories;
	
	private Collection<Expence> voucher; 
	
	private String fromdate="";
	private String todate="";
	private String currency;
	
	private String hdnfrmdate;
	
	
	

	public String getHdnfrmdate() {
		return hdnfrmdate;
	}
	public void setHdnfrmdate(String hdnfrmdate) {
		this.hdnfrmdate = hdnfrmdate;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	private ArrayList<Expence> currencies;

	
	public ArrayList<Expence> getCurrencies() {
		return currencies;
	}
	public void setCurrencies(ArrayList<Expence> currencies) {
		this.currencies = currencies;
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
	public Collection<Expence> getVoucher() {
		return voucher;
	}
	public void setVoucher(Collection<Expence> voucher) {
		this.voucher = voucher;
	}
	public ArrayList<Expence> getCategories() {
		return categories;
	}
	public void setCategories(ArrayList<Expence> categories) {
		this.categories = categories;
	}
	public String getReportDate() {
		return reportDate;
	}
	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}
	public ArrayList<Expence> getReportExpenList() {
		return reportExpenList;
	}
	public void setReportExpenList(ArrayList<Expence> reportExpenList) {
		this.reportExpenList = reportExpenList;
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
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public ArrayList<Expence> getReportList() {
		return reportList;
	}
	public void setReportList(ArrayList<Expence> reportList) {
		this.reportList = reportList;
	}
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	public String getTotalExpenceCheckbox() {
		return totalExpenceCheckbox;
	}
	public void setTotalExpenceCheckbox(String totalExpenceCheckbox) {
		this.totalExpenceCheckbox = totalExpenceCheckbox;
	}
	public String getExpenceTotal() {
		return expenceTotal;
	}
	public void setExpenceTotal(String expenceTotal) {
		this.expenceTotal = expenceTotal;
	}
	public ArrayList<Expence> getExpenceList() {
		return expenceList;
	}
	public void setExpenceList(ArrayList<Expence> expenceList) {
		this.expenceList = expenceList;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCaldate() {
		return caldate;
	}
	public void setCaldate(String caldate) {
		this.caldate = caldate;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getMerchant() {
		return merchant;
	}
	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}
	public String getLastmodified() {
		return lastmodified;
	}
	public void setLastmodified(String lastmodified) {
		this.lastmodified = lastmodified;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPaidby() {
		return paidby;
	}
	public void setPaidby(String paidby) {
		this.paidby = paidby;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	
	
	

}
