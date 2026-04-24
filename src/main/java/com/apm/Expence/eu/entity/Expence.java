package com.apm.Expence.eu.entity;

public class Expence {
	private String status;
	private String credit;
	private String xpayment;
	
	private String ctotal;
	
	private String showcd;
	
	private String transid;
	
	private String parantid;
	
	private String cbal;
	
	private String closingBal;
	
	
	
	
	
	
	
	
	
	
	
	public String getCbal() {
		return cbal;
	}
	public void setCbal(String cbal) {
		this.cbal = cbal;
	}
	public String getClosingBal() {
		return closingBal;
	}
	public void setClosingBal(String closingBal) {
		this.closingBal = closingBal;
	}
	public String getParantid() {
		return parantid;
	}
	public void setParantid(String parantid) {
		this.parantid = parantid;
	}
	public String getTransid() {
		return transid;
	}
	public void setTransid(String transid) {
		this.transid = transid;
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
	public String getXpayment() {
		return xpayment;
	}
	public void setXpayment(String xpayment) {
		this.xpayment = xpayment;
	}
	public String getCredit() {
		return credit;
	}
	public void setCredit(String credit) {
		this.credit = credit;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLedgerid() {
		return ledgerid;
	}
	public void setLedgerid(String ledgerid) {
		this.ledgerid = ledgerid;
	}
	public String getPtype() {
		return ptype;
	}
	public void setPtype(String ptype) {
		this.ptype = ptype;
	}
	public String getPmode() {
		return pmode;
	}
	public void setPmode(String pmode) {
		this.pmode = pmode;
	}
	public String getPto() {
		return pto;
	}
	public void setPto(String pto) {
		this.pto = pto;
	}
	public String getCommencing() {
		return commencing;
	}
	public void setCommencing(String commencing) {
		this.commencing = commencing;
	}
	private String createdate;
	private String createby;
	
	
	//ledgerid, ptype, pmode, pto, commencing, userid
	private String ledgerid;
	private String ptype;
	private String pmode;
	private String pto;
	private String commencing;
	
	
	
	
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
	private String userid;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	private String paymentmodevalue;
	private String expensetypevalue;
	private String rangevalue;
	private double totalamount;
	public double getTotalamount() {
		return totalamount;
	}
	public void setTotalamount(double totalamount) {
		this.totalamount = totalamount;
	}
	private int id;
	
	
	private String caldate;
	
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
	private String amount;
	
	private String merchant;
	private String lastmodified;
	private String category;
	private String paidby;
	private String comments;
	
	private String reportName;
	
	private String reportIdList;
	
	private String name;
	
	private String description;
	
	private int total;
	private String fromdate;
	private String todate;
	private String value;

	private String currency;
	
	private int count;
	public Object getcate;
	
	
		
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getReportIdList() {
		return reportIdList;
	}
	public void setReportIdList(String reportIdList) {
		this.reportIdList = reportIdList;
	}
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
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
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	
	

}
