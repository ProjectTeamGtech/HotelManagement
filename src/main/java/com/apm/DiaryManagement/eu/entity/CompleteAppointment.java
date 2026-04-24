package com.apm.DiaryManagement.eu.entity;

public class CompleteAppointment{
	public String getSessionno() {
		return sessionno;
	}
	public void setSessionno(String sessionno) {
		this.sessionno = sessionno;
	}
	public String getDocid() {
		return docid;
	}
	public void setDocid(String docid) {
		this.docid = docid;
	}
	private int id;
	private String user;
	private String apmtType;
	private String delstatus;
	private String charges;
	private String startTime;
	private String duration;
	private String practitionerId;
	private String practitionerName;
	private String invoicetype;
	private String clientId;
	private Double chargeTotal;
	private String wardid;
	private String backDate;
	private String invoiceTime;
	private String payamt="0";
	private String bedid;
	private String logid;
	private int pkgid;
	private String chargedisc = "0";
	private String unitcharge = "0";;
	private String manualinvoiceid;
	private String refundnote, requested_date, requested_userid, approve_date, approve_userid, status, isfromadvance;
	private String calCharge;
	private String date;
	private String deletechargeid;
	private String approve_note;
	private String disc_type;
	private String disc_amount;
	private String invoiceamount;
	private String totaldiscamount;
	private String discaprovestatus;
	private String remark;
	private boolean iskunal;
	private String oldparentid;
	private int newparentid;
	private String isindisharecharge;
	private String visitingconsulatntdrid;
	private String actualdiscount;
	private String actualtotal;
	private String discperc;
	private int tempsessionid;
	private String grosstotal;
	private String grosssubtotal;
	private String initalpaymode;
	private String finalpaymode;
	private String phar_ipdid;
	private int dummybillno;
	private boolean isfrommodify;
	private boolean package_access;
	private int creditid ;
	private String physicalid;
	private String manuallyadded;
	private String ipdopdseq;
	private String bill_edituserid, bill_editdatetime;
	private String originalAmt;
	private double discountShare;
	private int isfromdisc;
	private String deleteremark;
	private int isdeleted;
	private String credit;
	private String paymode;
	private String paynote,datetime;
	private String sessionno="0";
	private String third_party_name_id;
	
	public String getThird_party_name_id() {
		return third_party_name_id;
	}
	public void setThird_party_name_id(String third_party_name_id) {
		this.third_party_name_id = third_party_name_id;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public String getPaynote() {
		return paynote;
	}
	public void setPaynote(String paynote) {
		this.paynote = paynote;
	}
	public String getPaymode() {
		return paymode;
	}
	public void setPaymode(String paymode) {
		this.paymode = paymode;
	}
	public String getCredit() {
		return credit;
	}
	public void setCredit(String credit) {
		this.credit = credit;
	}
	public int getIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(int isdeleted) {
		this.isdeleted = isdeleted;
	}
	public String getDeleteremark() {
		return deleteremark;
	}
	public void setDeleteremark(String deleteremark) {
		this.deleteremark = deleteremark;
	}
	public int getIsfromdisc() {
		return isfromdisc;
	}
	public void setIsfromdisc(int isfromdisc) {
		this.isfromdisc = isfromdisc;
	}
	public double getDiscountShare() {
		return discountShare;
	}
	public void setDiscountShare(double discountShare) {
		this.discountShare = discountShare;
	}
	public String getOriginalAmt() {
		return originalAmt;
	}
	public void setOriginalAmt(String originalAmt) {
		this.originalAmt = originalAmt;
	}
	public String getBill_edituserid() {
		return bill_edituserid;
	}
	public void setBill_edituserid(String bill_edituserid) {
		this.bill_edituserid = bill_edituserid;
	}
	public String getBill_editdatetime() {
		return bill_editdatetime;
	}
	public void setBill_editdatetime(String bill_editdatetime) {
		this.bill_editdatetime = bill_editdatetime;
	}
	public String getIpdopdseq() {
		return ipdopdseq;
	}
	public void setIpdopdseq(String ipdopdseq) {
		this.ipdopdseq = ipdopdseq;
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
	public boolean isIsfrommodify() {
		return isfrommodify;
	}
	public void setIsfrommodify(boolean isfrommodify) {
		this.isfrommodify = isfrommodify;
	}
	public int getDummybillno() {
		return dummybillno;
	}
	public void setDummybillno(int dummybillno) {
		this.dummybillno = dummybillno;
	}
	public String getPhar_ipdid() {
		return phar_ipdid;
	}
	public void setPhar_ipdid(String phar_ipdid) {
		this.phar_ipdid = phar_ipdid;
	}
	public String getInitalpaymode() {
		return initalpaymode;
	}
	public void setInitalpaymode(String initalpaymode) {
		this.initalpaymode = initalpaymode;
	}
	public String getFinalpaymode() {
		return finalpaymode;
	}
	public void setFinalpaymode(String finalpaymode) {
		this.finalpaymode = finalpaymode;
	}
	public String getGrosstotal() {
		return grosstotal;
	}
	public void setGrosstotal(String grosstotal) {
		this.grosstotal = grosstotal;
	}
	public String getGrosssubtotal() {
		return grosssubtotal;
	}
	public void setGrosssubtotal(String grosssubtotal) {
		this.grosssubtotal = grosssubtotal;
	}
	public int getTempsessionid() {
		return tempsessionid;
	}
	public void setTempsessionid(int tempsessionid) {
		this.tempsessionid = tempsessionid;
	}
	public String getDiscperc() {
		return discperc;
	}
	public void setDiscperc(String discperc) {
		this.discperc = discperc;
	}
	public String getActualdiscount() {
		return actualdiscount;
	}
	public void setActualdiscount(String actualdiscount) {
		this.actualdiscount = actualdiscount;
	}
	public String getActualtotal() {
		return actualtotal;
	}
	public void setActualtotal(String actualtotal) {
		this.actualtotal = actualtotal;
	}
	
	
	public String getVisitingconsulatntdrid() {
		return visitingconsulatntdrid;
	}
	public void setVisitingconsulatntdrid(String visitingconsulatntdrid) {
		this.visitingconsulatntdrid = visitingconsulatntdrid;
	}
	public String getIsindisharecharge() {
		return isindisharecharge;
	}
	public void setIsindisharecharge(String isindisharecharge) {
		this.isindisharecharge = isindisharecharge;
	}
	
	public String getOldparentid() {
		return oldparentid;
	}
	public void setOldparentid(String oldparentid) {
		this.oldparentid = oldparentid;
	}
	public int getNewparentid() {
		return newparentid;
	}
	public void setNewparentid(int newparentid) {
		this.newparentid = newparentid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDiscaprovestatus() {
		return discaprovestatus;
	}
	public void setDiscaprovestatus(String discaprovestatus) {
		this.discaprovestatus = discaprovestatus;
	}
	public String getTotaldiscamount() {
		return totaldiscamount;
	}
	public void setTotaldiscamount(String totaldiscamount) {
		this.totaldiscamount = totaldiscamount;
	}
	public String getDisc_amount() {
		return disc_amount;
	}
	public void setDisc_amount(String disc_amount) {
		this.disc_amount = disc_amount;
	}
	public String getInvoiceamount() {
		return invoiceamount;
	}
	public void setInvoiceamount(String invoiceamount) {
		this.invoiceamount = invoiceamount;
	}
	public String getDisc_type() {
		return disc_type;
	}
	public void setDisc_type(String disc_type) {
		this.disc_type = disc_type;
	}
	public String getApprove_note() {
		return approve_note;
	}
	public void setApprove_note(String approve_note) {
		this.approve_note = approve_note;
	}
	public String getDeletechargeid() {
		return deletechargeid;
	}
	public void setDeletechargeid(String deletechargeid) {
		this.deletechargeid = deletechargeid;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCalCharge() {
		return calCharge;
	}
	public void setCalCharge(String calCharge) {
		this.calCharge = calCharge;
	}
	public String getManualinvoiceid() {
		return manualinvoiceid;
	}
	public void setManualinvoiceid(String manualinvoiceid) {
		this.manualinvoiceid = manualinvoiceid;
	}
	public String getRefundnote() {
		return refundnote;
	}
	public void setRefundnote(String refundnote) {
		this.refundnote = refundnote;
	}
	public String getRequested_date() {
		return requested_date;
	}
	public void setRequested_date(String requested_date) {
		this.requested_date = requested_date;
	}
	public String getRequested_userid() {
		return requested_userid;
	}
	public void setRequested_userid(String requested_userid) {
		this.requested_userid = requested_userid;
	}
	public String getApprove_date() {
		return approve_date;
	}
	public void setApprove_date(String approve_date) {
		this.approve_date = approve_date;
	}
	public String getApprove_userid() {
		return approve_userid;
	}
	public void setApprove_userid(String approve_userid) {
		this.approve_userid = approve_userid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIsfromadvance() {
		return isfromadvance;
	}
	public void setIsfromadvance(String isfromadvance) {
		this.isfromadvance = isfromadvance;
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
	public int getPkgid() {
		return pkgid;
	}
	public void setPkgid(int pkgid) {
		this.pkgid = pkgid;
	}
	public String getLogid() {
		return logid;
	}
	public void setLogid(String logid) {
		this.logid = logid;
	}
	public String getBedid() {
		return bedid;
	}
	public void setBedid(String bedid) {
		this.bedid = bedid;
	}
	private String chargeTotalx;
	private String cgst="0";
	private String sgst="0";
	private int refundid=0;
	private int edited=0;
	private int deleted=0;
	private String stdflag="0";
	private String edit_note;
	private String balance="0";
	private double refundAmt=0;
	private int isreturn=0;
	private int returnbillid=0;
	private String userid="0";
	private int ipdid;
	private double vat;
	private double discount;
	private double total;
	private int priscid;
	private String pclientid="0";
	private String masterchargetype;
	private String additionalcharge_id="0";
	private String chargeId="0";
	private String manualcharge;
	
	private int prodid;
	
	private String gpriscid;
	
	private String ginvstid;
	
	private String apmttypeid;
	
	
	
	public String getApmttypeid() {
		return apmttypeid;
	}
	public void setApmttypeid(String apmttypeid) {
		this.apmttypeid = apmttypeid;
	}
	public String getWardid() {
		return wardid;
	}
	public void setWardid(String wardid) {
		this.wardid = wardid;
	}
	public String getAdditionalcharge_id() {
		return additionalcharge_id;
	}
	public void setAdditionalcharge_id(String additionalcharge_id) {
		this.additionalcharge_id = additionalcharge_id;
	}
	public String getGpriscid() {
		return gpriscid;
	}
	public void setGpriscid(String gpriscid) {
		this.gpriscid = gpriscid;
	}
	public String getGinvstid() {
		return ginvstid;
	}
	public void setGinvstid(String ginvstid) {
		this.ginvstid = ginvstid;
	}
	public int getProdid() {
		return prodid;
	}
	public void setProdid(int prodid) {
		this.prodid = prodid;
	}
	public String getManualcharge() {
		return manualcharge;
	}
	public void setManualcharge(String manualcharge) {
		this.manualcharge = manualcharge;
	}
	public String getMasterchargetype() {
		return masterchargetype;
	}
	public void setMasterchargetype(String masterchargetype) {
		this.masterchargetype = masterchargetype;
	}
	public int getIpdid() {
		return ipdid;
	}
	public void setIpdid(int ipdid) {
		this.ipdid = ipdid;
	}
	public String getSTime() {
		return sTime;
	}
	public void setSTime(String time) {
		sTime = time;
	}
	public String getChargeTotalx() {
		return chargeTotalx;
	}
	public void setChargeTotalx(String chargeTotalx) {
		this.chargeTotalx = chargeTotalx;
	}
	private String commencing;
	private String payBuy;
	private String markAppointment;
	private String appointmentid;
	private String invoiceDate;
	
	private String chargeType;
	private String apmtypeText;
	private String thirdPartyContacttno;
	private String thirdPartyPostcode;
	private String thirdPartyemail;
	private String usedSession;
	private String invoicee;
	private String totalSession;
	private String policyExcess;
	
	//unnati
	
	private String tpNotes = "";
	
	private String tpid="0";
	
	private String invoiceid;
	
	private String locationid;
	
	private String ipd;
	
	private double quantity;
	
	
	
	
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public String getIpd() {
		return ipd;
	}
	public void setIpd(String ipd) {
		this.ipd = ipd;
	}
	public String getLocationid() {
		return locationid;
	}
	public void setLocationid(String locationid) {
		this.locationid = locationid;
	}
	public String getInvoiceid() {
		return invoiceid;
	}
	public void setInvoiceid(String invoiceid) {
		this.invoiceid = invoiceid;
	}
	public String getTpid() {
		return tpid;
	}
	public void setTpid(String tpid) {
		this.tpid = tpid;
	}
	public String getTpNotes() {
		return tpNotes;
	}
	public void setTpNotes(String tpNotes) {
		this.tpNotes = tpNotes;
	}
	public String getPolicyExcess() {
		return policyExcess;
	}
	public void setPolicyExcess(String policyExcess) {
		this.policyExcess = policyExcess;
	}
	public String getUsedSession() {
		return usedSession;
	}
	public void setUsedSession(String usedSession) {
		this.usedSession = usedSession;
	}
	public String getInvoicee() {
		return invoicee;
	}
	public void setInvoicee(String invoicee) {
		this.invoicee = invoicee;
	}
	public String getTotalSession() {
		return totalSession;
	}
	public void setTotalSession(String totalSession) {
		this.totalSession = totalSession;
	}
	public String getApmtypeText() {
		return apmtypeText;
	}
	public void setApmtypeText(String apmtypeText) {
		this.apmtypeText = apmtypeText;
	}
	public String getChargeType() {
		return chargeType;
	}
	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}
	private String insuranceCompanyName;
	private String insuranceCompanyAddress;
	private String insuranceCompanyOwnerName;
	
	
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
	private int apmtSlotId;
	public int getApmtSlotId() {
		return apmtSlotId;
	}
	public void setApmtSlotId(int apmtSlotId) {
		this.apmtSlotId = apmtSlotId;
	}
	public String getsTime() {
		return sTime;
	}
	public void setsTime(String sTime) {
		this.sTime = sTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	public int getDiaryUserId() {
		return diaryUserId;
	}
	public void setDiaryUserId(int diaryUserId) {
		this.diaryUserId = diaryUserId;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	private String sTime;
	private String endTime;
	private String notes;
	private int diaryUserId;
	private String dept;
	private String location;
	private String room;
	private String client;
	private String treatmentEpisodeId;
	private String diaryUser;
	private String appointmentTypeId;
	private String dna;
	
	
	public String getDna() {
		return dna;
	}
	public void setDna(String dna) {
		this.dna = dna;
	}
	public String getAppointmentTypeId() {
		return appointmentTypeId;
	}
	public void setAppointmentTypeId(String appointmentTypeId) {
		this.appointmentTypeId = appointmentTypeId;
	}
	public String getDiaryUser() {
		return diaryUser;
	}
	public void setDiaryUser(String diaryUser) {
		this.diaryUser = diaryUser;
	}
	public String getTreatmentEpisodeId() {
		return treatmentEpisodeId;
	}
	public void setTreatmentEpisodeId(String treatmentEpisodeId) {
		this.treatmentEpisodeId = treatmentEpisodeId;
	}
	public String getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public String getAppointmentid() {
		return appointmentid;
	}
	public void setAppointmentid(String appointmentid) {
		this.appointmentid = appointmentid;
	}
	public String getMarkAppointment() {
		return markAppointment;
	}
	public void setMarkAppointment(String markAppointment) {
		this.markAppointment = markAppointment;
	}
	public String getPayBuy() {
		return payBuy;
	}
	public void setPayBuy(String payBuy) {
		this.payBuy = payBuy;
	}
	public String getCommencing() {
		return commencing;
	}
	public void setCommencing(String commencing) {
		this.commencing = commencing;
	}
	public Double getChargeTotal() {
		return chargeTotal;
	}
	public void setChargeTotal(Double chargeTotal) {
		this.chargeTotal = chargeTotal;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getApmtType() {
		return apmtType;
	}
	public void setApmtType(String apmtType) {
		this.apmtType = apmtType;
	}
	public String getCharges() {
		return charges;
	}
	public void setCharges(String charges) {
		this.charges = charges;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getPractitionerId() {
		return practitionerId;
	}
	public void setPractitionerId(String practitionerId) {
		this.practitionerId = practitionerId;
	}
	public String getPractitionerName() {
		return practitionerName;
	}
	public void setPractitionerName(String practitionerName) {
		this.practitionerName = practitionerName;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getInsuranceCompanyName() {
		return insuranceCompanyName;
	}
	public void setInsuranceCompanyName(String insuranceCompanyName) {
		this.insuranceCompanyName = insuranceCompanyName;
	}
	public String getInsuranceCompanyAddress() {
		return insuranceCompanyAddress;
	}
	public void setInsuranceCompanyAddress(String insuranceCompanyAddress) {
		this.insuranceCompanyAddress = insuranceCompanyAddress;
	}
	public String getInsuranceCompanyOwnerName() {
		return insuranceCompanyOwnerName;
	}
	public void setInsuranceCompanyOwnerName(String insuranceCompanyOwnerName) {
		this.insuranceCompanyOwnerName = insuranceCompanyOwnerName;
	}
	public double getVat() {
		return vat;
	}
	public void setVat(double vat) {
		this.vat = vat;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getPclientid() {
		return pclientid;
	}
	public void setPclientid(String pclientid) {
		this.pclientid = pclientid;
	}
	public String getChargeId() {
		return chargeId;
	}
	public void setChargeId(String chargeId) {
		this.chargeId = chargeId;
	}
	public int getPriscid() {
		return priscid;
	}
	public void setPriscid(int priscid) {
		this.priscid = priscid;
	}
	public String getBackDate() {
		return backDate;
	}
	public void setBackDate(String backDate) {
		this.backDate = backDate;
	}
	public String getStdflag() {
		return stdflag;
	}
	public void setStdflag(String stdflag) {
		this.stdflag = stdflag;
	}
	public int getIsreturn() {
		return isreturn;
	}
	public void setIsreturn(int isreturn) {
		this.isreturn = isreturn;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
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
	public String getPayamt() {
		return payamt;
	}
	public void setPayamt(String payamt) {
		this.payamt = payamt;
	}
	public String getInvoiceTime() {
		return invoiceTime;
	}
	public void setInvoiceTime(String invoiceTime) {
		this.invoiceTime = invoiceTime;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getReturnbillid() {
		return returnbillid;
	}
	public void setReturnbillid(int returnbillid) {
		this.returnbillid = returnbillid;
	}
	public double getRefundAmt() {
		return refundAmt;
	}
	public void setRefundAmt(double refundAmt) {
		this.refundAmt = refundAmt;
	}
	public String getEdit_note() {
		return edit_note;
	}
	public void setEdit_note(String edit_note) {
		this.edit_note = edit_note;
	}
	public int getRefundid() {
		return refundid;
	}
	public void setRefundid(int refundid) {
		this.refundid = refundid;
	}
	public int getDeleted() {
		return deleted;
	}
	public int getEdited() {
		return edited;
	}
	public void setEdited(int edited) {
		this.edited = edited;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	
	public boolean isCancelled() {
		return cancelled;
	}
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}
	
	
	
	public String getPkgfdate() {
		return pkgfdate;
	}
	public void setPkgfdate(String pkgfdate) {
		this.pkgfdate = pkgfdate;
	}
	public String getPkgtdate() {
		return pkgtdate;
	}
	public void setPkgtdate(String pkgtdate) {
		this.pkgtdate = pkgtdate;
	}
	public String getInvestigation_request_id() {
		return investigation_request_id;
	}
	public void setInvestigation_request_id(String investigation_request_id) {
		this.investigation_request_id = investigation_request_id;
	}
	public int getTpkg() {
		return tpkg;
	}
	public void setTpkg(int tpkg) {
		this.tpkg = tpkg;
	}
	public int getInvoiced() {
		return invoiced;
	}
	public void setInvoiced(int invoiced) {
		this.invoiced = invoiced;
	}
	public String getTax1() {
		return tax1;
	}
	public void setTax1(String tax1) {
		this.tax1 = tax1;
	}
	public String getTax3() {
		return tax3;
	}
	public void setTax3(String tax3) {
		this.tax3 = tax3;
	}
	public String getTax2() {
		return tax2;
	}
	public void setTax2(String tax2) {
		this.tax2 = tax2;
	}
	public String getTotaltax() {
		return totaltax;
	}
	public void setTotaltax(String totaltax) {
		this.totaltax = totaltax;
	}
	public double getTotaltaxamt() {
		return totaltaxamt;
	}
	public void setTotaltaxamt(double totaltaxamt) {
		this.totaltaxamt = totaltaxamt;
	}
	public String getChargedescription() {
		return chargedescription;
	}
	public void setChargedescription(String chargedescription) {
		this.chargedescription = chargedescription;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getStd_charge_id() {
		return std_charge_id;
	}
	public void setStd_charge_id(String std_charge_id) {
		this.std_charge_id = std_charge_id;
	}
	public String getPatientname() {
		return patientname;
	}
	public void setPatientname(String patientname) {
		this.patientname = patientname;
	}
	private boolean cancelled;
	
	
	
	private String pkgfdate;
	private String pkgtdate;
	
	
	private String investigation_request_id;
	private int tpkg;
	
	private int invoiced; 
	private String tax1;
	private String tax2;
	private String tax3;
	private String totaltax;
	private double totaltaxamt;
	private String chargedescription; 
	private String code;
	private String std_charge_id;
	private String patientname;
	private double debit;
	private int disc_approve;

	public double getDebit() {
		return debit;
	}
	public void setDebit(double debit) {
		this.debit = debit;
	}
	public int getDisc_approve() {
		return disc_approve;
	}
	public void setDisc_approve(int disc_approve) {
		this.disc_approve = disc_approve;
	}
	public String getInvoicetype() {
		return invoicetype;
	}
	public void setInvoicetype(String invoicetype) {
		this.invoicetype = invoicetype;
	}
	public String getDelstatus() {
		return delstatus;
	}
	public void setDelstatus(String delstatus) {
		this.delstatus = delstatus;
	}
	public boolean isPackage_access() {
		return package_access;
	}
	public void setPackage_access(boolean package_access) {
		this.package_access = package_access;
	}
	public boolean isIskunal() {
		return iskunal;
	}
	public void setIskunal(boolean iskunal) {
		this.iskunal = iskunal;
	}
	public int getChargeInvoiceId() {
		return chargeInvoiceId;
	}
	public void setChargeInvoiceId(int chargeInvoiceId) {
		this.chargeInvoiceId = chargeInvoiceId;
	}
	public String getManuallyadded() {
		return manuallyadded;
	}
	public void setManuallyadded(String manuallyadded) {
		this.manuallyadded = manuallyadded;
	}
	private int chargeInvoiceId;
	private String abrivationid;



	public String getAbrivationid() {
		return abrivationid;
	}
	public void setAbrivationid(String abrivationid) {
		this.abrivationid = abrivationid;
	}
	private String regno;


	public String getRegno() {
		return regno;
	}
	public void setRegno(String regno) {
		this.regno = regno;
	}
	
	private String docid;
}