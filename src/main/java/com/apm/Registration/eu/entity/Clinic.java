package com.apm.Registration.eu.entity;

import java.io.File;
import java.util.ArrayList;

public class Clinic {
	public boolean isClinic_small() {
		return clinic_small;
	}


	public void setClinic_small(boolean clinic_small) {
		this.clinic_small = clinic_small;
	}


	public boolean isNabh() {
		return nabh;
	}


	public void setNabh(boolean nabh) {
		this.nabh = nabh;
	}


	public boolean isSramhosp() {
		return sramhosp;
	}


	public void setSramhosp(boolean sramhosp) {
		this.sramhosp = sramhosp;
	}


	public boolean isSjivh() {
		return sjivh;
	}


	public void setSjivh(boolean sjivh) {
		this.sjivh = sjivh;
	}


	public boolean isPhysio_ipd() {
		return physio_ipd;
	}


	public void setPhysio_ipd(boolean physio_ipd) {
		this.physio_ipd = physio_ipd;
	}


	public boolean isParihar() {
		return parihar;
	}


	public void setParihar(boolean parihar) {
		this.parihar = parihar;
	}


	public boolean isAyushman() {
		return ayushman;
	}


	public void setAyushman(boolean ayushman) {
		this.ayushman = ayushman;
	}


	public boolean isUhiddatewise() {
		return uhiddatewise;
	}


	public void setUhiddatewise(boolean uhiddatewise) {
		this.uhiddatewise = uhiddatewise;
	}


	public boolean isMbbs() {
		return mbbs;
	}


	public void setMbbs(boolean mbbs) {
		this.mbbs = mbbs;
	}

	private String clinicLogo;
	private String sshhostname,sshpassword,sshuser,dbpassword;
	private int port;
	
	private String regional_lang;
	private boolean opd_chat;
	private boolean package_investigation;
	private boolean lmh;
	private int patient_category;
	private boolean australia;
	private boolean kalmegha;
	private boolean lmh_po_discount;
	private String isclinic;
	private boolean smallClinic;
	private String account_name,account_number,account_ifsc,account_upi;
	private boolean isclinic_use_yuvi;
	private boolean common_clinic_db;
	private boolean uhiddatewise;
	private boolean ayushman;
	private boolean sjivh;
	private boolean totehosp;
	private boolean sramhosp;
	private boolean markhosp;
	private boolean afitech;
	private boolean nabh;
	private boolean clinic_small;
	public boolean isAfitech() {
		return afitech;
	}


	public void setAfitech(boolean afitech) {
		this.afitech = afitech;
	}


	public boolean isMarkhosp() {
		return markhosp;
	}


	public void setMarkhosp(boolean markhosp) {
		this.markhosp = markhosp;
	}


	public boolean isTotehosp() {
		return totehosp;
	}


	public void setTotehosp(boolean totehosp) {
		this.totehosp = totehosp;
	}


	public boolean isMatrusevasang() {
		return matrusevasang;
	}


	public void setMatrusevasang(boolean matrusevasang) {
		this.matrusevasang = matrusevasang;
	}

	private String hospital_id;
	private String commercial_charge;
	private String consultant_charge;
	private String hospitalName;
	private boolean saimed;
	private boolean matrusevasang;
	private boolean amravati;
	private boolean simpliclinic;
    private boolean infinyte;
    private boolean vrundawan;
	private boolean bams;
    private boolean bams1;
    private boolean mbbs;
    private boolean time_utility_report;
    private boolean parihar;
    private boolean physio_ipd;
    private String manas_clinic_name;
    private String manas_clinicLogo;
    
    public String getManas_clinicLogo() {
		return manas_clinicLogo;
	}


	public void setManas_clinicLogo(String manas_clinicLogo) {
		this.manas_clinicLogo = manas_clinicLogo;
	}


	public String getManas_clinic_name() {
		return manas_clinic_name;
	}


	public void setManas_clinic_name(String manas_clinic_name) {
		this.manas_clinic_name = manas_clinic_name;
	}


	public boolean isTime_utility_report() {
		return time_utility_report;
	}


	public void setTime_utility_report(boolean time_utility_report) {
		this.time_utility_report = time_utility_report;
	}

    
	public boolean isBams() {
		return bams;
	}


	public void setBams(boolean bams) {
		this.bams = bams;
	}


	public boolean isBams1() {
		return bams1;
	}


	public void setBams1(boolean bams1) {
		this.bams1 = bams1;
	}


	public boolean isVrundawan() {
		return vrundawan;
	}


	public void setVrundawan(boolean vrundawan) {
		this.vrundawan = vrundawan;
	}


	public boolean isInfinyte() {
		return infinyte;
	}


	public void setInfinyte(boolean infinyte) {
		this.infinyte = infinyte;
	}


	public boolean isSimpliclinic() {
		return simpliclinic;
	}


	public void setSimpliclinic(boolean simpliclinic) {
		this.simpliclinic = simpliclinic;
	}


	public boolean isAmravati() {
		return amravati;
	}


	public void setAmravati(boolean amravati) {
		this.amravati = amravati;
	}


	public boolean isSaimed() {
		return saimed;
	}


	public void setSaimed(boolean saimed) {
		this.saimed = saimed;
	}


	public String getHospitalName() {
		return hospitalName;
	}


	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}


	public String getConsultant_charge() {
		return consultant_charge;
	}


	public void setConsultant_charge(String consultant_charge) {
		this.consultant_charge = consultant_charge;
	}


	public String getCommercial_charge() {
		return commercial_charge;
	}


	public void setCommercial_charge(String commercial_charge) {
		this.commercial_charge = commercial_charge;
	}


	public String getHospital_id() {
		return hospital_id;
	}


	public void setHospital_id(String hospital_id) {
		this.hospital_id = hospital_id;
	}


	public boolean isCommon_clinic_db() {
		return common_clinic_db;
	}


	public void setCommon_clinic_db(boolean common_clinic_db) {
		this.common_clinic_db = common_clinic_db;
	}


	public boolean isIsclinic_use_yuvi() {
		return isclinic_use_yuvi;
	}


	public void setIsclinic_use_yuvi(boolean isclinic_use_yuvi) {
		this.isclinic_use_yuvi = isclinic_use_yuvi;
	}


	public String getAccount_name() {
		return account_name;
	}


	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}


	public String getAccount_number() {
		return account_number;
	}


	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}


	public String getAccount_ifsc() {
		return account_ifsc;
	}


	public void setAccount_ifsc(String account_ifsc) {
		this.account_ifsc = account_ifsc;
	}


	public String getAccount_upi() {
		return account_upi;
	}


	public void setAccount_upi(String account_upi) {
		this.account_upi = account_upi;
	}


	public boolean isSmallClinic() {
		return smallClinic;
	}


	public void setSmallClinic(boolean smallClinic) {
		this.smallClinic = smallClinic;
	}


	public String getIsclinic() {
		return isclinic;
	}


	public void setIsclinic(String isclinic) {
		this.isclinic = isclinic;
	}


	public boolean isLmh_po_discount() {
		return lmh_po_discount;
	}


	public void setLmh_po_discount(boolean lmh_po_discount) {
		this.lmh_po_discount = lmh_po_discount;
	}


	public boolean isKalmegha() {
		return kalmegha;
	}


	public void setKalmegha(boolean kalmegha) {
		this.kalmegha = kalmegha;
	}


	public boolean isAustralia() {
		return australia;
	}


	public void setAustralia(boolean australia) {
		this.australia = australia;
	}


	public int getPatient_category() {
		return patient_category;
	}


	public void setPatient_category(int patient_category) {
		this.patient_category = patient_category;
	}


	public boolean isLmh() {
		return lmh;
	}


	public void setLmh(boolean lmh) {
		this.lmh = lmh;
	}


	public String getRegional_lang() {
		return regional_lang;
	}


	public void setRegional_lang(String regional_lang) {
		this.regional_lang = regional_lang;
	}


	public boolean isOpd_chat() {
		return opd_chat;
	}


	public void setOpd_chat(boolean opd_chat) {
		this.opd_chat = opd_chat;
	}


	public String getSshhostname() {
		return sshhostname;
	}


	public void setSshhostname(String sshhostname) {
		this.sshhostname = sshhostname;
	}


	public String getSshpassword() {
		return sshpassword;
	}


	public void setSshpassword(String sshpassword) {
		this.sshpassword = sshpassword;
	}


	public int getPort() {
		return port;
	}


	public void setPort(int port) {
		this.port = port;
	}

	private boolean smsrelativeDischarge,sms_cancel_appointment;
	private boolean smsdoctorBedchange;
	private boolean smscheckrelativebedchange;
	private boolean smscheckdoctor;
	private boolean smspatientCompleted;
	private boolean smspatientApproved;
	private boolean medtreatgiven;
	private boolean stock_log;
	private boolean isledgerhosp;
	private String opdfollowup;
	private boolean direct_refund_disc;
	private boolean balgopal;
	private boolean opd_recep_sp_list;
	private boolean wardhazp;
	private boolean prisc_deliver_return;
	private boolean auto_generic_name;
	private String default_phar_location;
	private String default_phar_location_name;
	private boolean prisc_location_list,prisc_print;
	private boolean direct_prisc;
	private boolean invest_savenprint;
	private boolean prisc_savenprint;
	private boolean prachi_clinic;
	private boolean opening_closeing_on;
	private boolean invoice_date_modify;
	private boolean opd_telemed;
	private String opening_locations;
	private int counts;
	private String month;
	private String year;
	private boolean phar_print_seq;
	private boolean package_access;
	private boolean invoice_groupby;
	private boolean invoice_charge_seqno;
	private boolean opd_tp_zero_invoice;
	private String sms_senderid;
	private int grn_to_prisc_location;
	private boolean opd_video_icon_show;
	private boolean hidecalinpoprint;
	private boolean ot_surgeon_sms;
	private boolean ot_patient_sms;
	private boolean create_invoice_selected_charges;
	private boolean sms_reg_patient;
	private boolean phar_batchwise_sale;
	private boolean sms_access;
	private boolean vermanh;
	private String linkaddress;
	private boolean maindash_graph;
	private boolean vaci_sms_7day;
	
	
	public boolean isVaci_sms_7day() {
		return vaci_sms_7day;
	}


	public void setVaci_sms_7day(boolean vaci_sms_7day) {
		this.vaci_sms_7day = vaci_sms_7day;
	}


	public boolean isMaindash_graph() {
		return maindash_graph;
	}


	public void setMaindash_graph(boolean maindash_graph) {
		this.maindash_graph = maindash_graph;
	}


	public boolean isSms_access() {
		return sms_access;
	}


	public void setSms_access(boolean sms_access) {
		this.sms_access = sms_access;
	}


	public boolean isPhar_batchwise_sale() {
		return phar_batchwise_sale;
	}


	public void setPhar_batchwise_sale(boolean phar_batchwise_sale) {
		this.phar_batchwise_sale = phar_batchwise_sale;
	}


	public boolean isOt_surgeon_sms() {
		return ot_surgeon_sms;
	}


	public void setOt_surgeon_sms(boolean ot_surgeon_sms) {
		this.ot_surgeon_sms = ot_surgeon_sms;
	}


	public boolean isOt_patient_sms() {
		return ot_patient_sms;
	}


	public void setOt_patient_sms(boolean ot_patient_sms) {
		this.ot_patient_sms = ot_patient_sms;
	}

	private boolean disc_approve_sms;
	

	public boolean isDisc_approve_sms() {
		return disc_approve_sms;
	}


	public void setDisc_approve_sms(boolean disc_approve_sms) {
		this.disc_approve_sms = disc_approve_sms;
	}


	public boolean isHidecalinpoprint() {
		return hidecalinpoprint;
	}


	public void setHidecalinpoprint(boolean hidecalinpoprint) {
		this.hidecalinpoprint = hidecalinpoprint;
	}


	public boolean isOpd_video_icon_show() {
		return opd_video_icon_show;
	}


	public void setOpd_video_icon_show(boolean opd_video_icon_show) {
		this.opd_video_icon_show = opd_video_icon_show;
	}


	public String getSms_senderid() {
		return sms_senderid;
	}


	public void setSms_senderid(String sms_senderid) {
		this.sms_senderid = sms_senderid;
	}


	public boolean isInvoice_groupby() {
		return invoice_groupby;
	}


	public void setInvoice_groupby(boolean invoice_groupby) {
		this.invoice_groupby = invoice_groupby;
	}


	public boolean isInvoice_charge_seqno() {
		return invoice_charge_seqno;
	}


	public void setInvoice_charge_seqno(boolean invoice_charge_seqno) {
		this.invoice_charge_seqno = invoice_charge_seqno;
	}


	public boolean isPhar_print_seq() {
		return phar_print_seq;
	}


	public void setPhar_print_seq(boolean phar_print_seq) {
		this.phar_print_seq = phar_print_seq;
	}


	public String getMonth() {
		return month;
	}


	public void setMonth(String month) {
		this.month = month;
	}


	public String getYear() {
		return year;
	}


	public void setYear(String year) {
		this.year = year;
	}


	public int getCounts() {
		return counts;
	}


	public void setCounts(int counts) {
		this.counts = counts;
	}


	public boolean isOpening_closeing_on() {
		return opening_closeing_on;
	}


	public void setOpening_closeing_on(boolean opening_closeing_on) {
		this.opening_closeing_on = opening_closeing_on;
	}


	public String getOpening_locations() {
		return opening_locations;
	}


	public void setOpening_locations(String opening_locations) {
		this.opening_locations = opening_locations;
	}


	public boolean isPrachi_clinic() {
		return prachi_clinic;
	}


	public void setPrachi_clinic(boolean prachi_clinic) {
		this.prachi_clinic = prachi_clinic;
	}


	public boolean isInvest_savenprint() {
		return invest_savenprint;
	}


	public void setInvest_savenprint(boolean invest_savenprint) {
		this.invest_savenprint = invest_savenprint;
	}


	public boolean isPrisc_savenprint() {
		return prisc_savenprint;
	}


	public void setPrisc_savenprint(boolean prisc_savenprint) {
		this.prisc_savenprint = prisc_savenprint;
	}


	public boolean isDirect_prisc() {
		return direct_prisc;
	}


	public void setDirect_prisc(boolean direct_prisc) {
		this.direct_prisc = direct_prisc;
	}


	public boolean isPrisc_location_list() {
		return prisc_location_list;
	}


	public void setPrisc_location_list(boolean prisc_location_list) {
		this.prisc_location_list = prisc_location_list;
	}


	public boolean isPrisc_print() {
		return prisc_print;
	}


	public void setPrisc_print(boolean prisc_print) {
		this.prisc_print = prisc_print;
	}


	public String getDefault_phar_location_name() {
		return default_phar_location_name;
	}


	public void setDefault_phar_location_name(String default_phar_location_name) {
		this.default_phar_location_name = default_phar_location_name;
	}


	public String getDefault_phar_location() {
		return default_phar_location;
	}


	public String isDefault_phar_location() {
		return default_phar_location;
	}


	public void setDefault_phar_location(String default_phar_location) {
		this.default_phar_location = default_phar_location;
	}


	public boolean isAuto_generic_name() {
		return auto_generic_name;
	}


	public void setAuto_generic_name(boolean auto_generic_name) {
		this.auto_generic_name = auto_generic_name;
	}


	public boolean isPrisc_deliver_return() {
		return prisc_deliver_return;
	}


	public void setPrisc_deliver_return(boolean prisc_deliver_return) {
		this.prisc_deliver_return = prisc_deliver_return;
	}


	public boolean isDirect_refund_disc() {
		return direct_refund_disc;
	}


	public void setDirect_refund_disc(boolean direct_refund_disc) {
		this.direct_refund_disc = direct_refund_disc;
	}


	public boolean isIsledgerhosp() {
		return isledgerhosp;
	}


	public void setIsledgerhosp(boolean isledgerhosp) {
		this.isledgerhosp = isledgerhosp;
	}


	public boolean isStock_log() {
		return stock_log;
	}


	public void setStock_log(boolean stock_log) {
		this.stock_log = stock_log;
	}


	public boolean isMedtreatgiven() {
		return medtreatgiven;
	}


	public void setMedtreatgiven(boolean medtreatgiven) {
		this.medtreatgiven = medtreatgiven;
	}

	private String discount_show;
	
	public String getDiscount_show() {
		return discount_show;
	}


	public void setDiscount_show(String discount_show) {
		this.discount_show = discount_show;
	}


	public boolean isSmspatientCompleted() {
		return smspatientCompleted;
	}


	public void setSmspatientCompleted(boolean smspatientCompleted) {
		this.smspatientCompleted = smspatientCompleted;
	}


	public boolean isSmspatientApproved() {
		return smspatientApproved;
	}


	public void setSmspatientApproved(boolean smspatientApproved) {
		this.smspatientApproved = smspatientApproved;
	}


	public boolean isSmsrelativeDischarge() {
		return smsrelativeDischarge;
	}


	public void setSmsrelativeDischarge(boolean smsrelativeDischarge) {
		this.smsrelativeDischarge = smsrelativeDischarge;
	}


	public boolean isSmsdoctorBedchange() {
		return smsdoctorBedchange;
	}


	public void setSmsdoctorBedchange(boolean smsdoctorBedchange) {
		this.smsdoctorBedchange = smsdoctorBedchange;
	}


	public boolean isSmscheckrelativebedchange() {
		return smscheckrelativebedchange;
	}


	public void setSmscheckrelativebedchange(boolean smscheckrelativebedchange) {
		this.smscheckrelativebedchange = smscheckrelativebedchange;
	}


	public boolean isSmscheckdoctor() {
		return smscheckdoctor;
	}


	public void setSmscheckdoctor(boolean smscheckdoctor) {
		this.smscheckdoctor = smscheckdoctor;
	}

	private String invoice_date;
	private boolean flag;
	private String modulename;
	private String name;
	private String jobtitle;
	private String withpayment;
	private String withoutpayment;
	private String loc_direction;
	private File userImage;
	private String userImageFileName;
	private String userImageContentType;
	private String dbName;
	private String clinicID;
	
	private boolean smsPayment;
	
	
	private int activestatus;
	private boolean loginstatus;
	private String setupstdcharge="0";
	
	private String refLocation = "";
	
	private String starttime;
	
	private String endtime;
	
	private String curDateTime;
	private String excess;
	
	private String advanceTime;	
	private boolean smscheck;
	private String advancerenge;
	private String ipdregcharge="0";
	private boolean ctp;
	private boolean cself;
	private int smscount;
	private String ipdregtype="";
	private int dbsize;
	private String islogo;
	
	private String registerno="";
	private String inst1;
	private String inst2;
	private String inst3;
	private String inst4;
	
	
	public String getInst1() {
		return inst1;
	}


	public void setInst1(String inst1) {
		this.inst1 = inst1;
	}


	public String getInst2() {
		return inst2;
	}


	public void setInst2(String inst2) {
		this.inst2 = inst2;
	}


	public String getInst3() {
		return inst3;
	}


	public void setInst3(String inst3) {
		this.inst3 = inst3;
	}


	public String getInst4() {
		return inst4;
	}


	public void setInst4(String inst4) {
		this.inst4 = inst4;
	}


	public String getIslogo() {
		return islogo;
	}


	public void setIslogo(String islogo) {
		this.islogo = islogo;
	}


	public int getDbsize() {
		return dbsize;
	}


	public void setDbsize(int dbsize) {
		this.dbsize = dbsize;
	}


	public String getIpdregtype() {
		return ipdregtype;
	}


	public void setIpdregtype(String ipdregtype) {
		this.ipdregtype = ipdregtype;
	}


	public String getIpdregcharge() {
		return ipdregcharge;
	}


	public void setIpdregcharge(String ipdregcharge) {
		this.ipdregcharge = ipdregcharge;
	}


	public boolean isCtp() {
		return ctp;
	}


	public void setCtp(boolean ctp) {
		this.ctp = ctp;
	}


	public boolean isCself() {
		return cself;
	}


	public void setCself(boolean cself) {
		this.cself = cself;
	}


	public String getAdvancerenge() {
		return advancerenge;
	}


	public void setAdvancerenge(String advancerenge) {
		this.advancerenge = advancerenge;
	}


	public String getAdvanceTime() {
		return advanceTime;
	}


	public void setAdvanceTime(String advanceTime) {
		this.advanceTime = advanceTime;
	}


	public String getExcess() {
		return excess;
	}


	public void setExcess(String excess) {
		this.excess = excess;
	}


	public String getCurDateTime() {
		return curDateTime;
	}


	public void setCurDateTime(String curDateTime) {
		this.curDateTime = curDateTime;
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


	public String getRefLocation() {
		return refLocation;
	}


	public void setRefLocation(String refLocation) {
		this.refLocation = refLocation;
	}


	public boolean isLoginstatus() {
		return loginstatus;
	}


	public void setLoginstatus(boolean loginstatus) {
		this.loginstatus = loginstatus;
	}

	private int checkMailId;
	
	
	
	
	

	public String getClinicID() {
		return clinicID;
	}


	public void setClinicID(String clinicID) {
		this.clinicID = clinicID;
	}


	public String getDbName() {
		return dbName;
	}


	public void setDbName(String dbName) {
		this.dbName = dbName;
	}


	public int getActivestatus() {
		return activestatus;
	}


	public void setActivestatus(int activestatus) {
		this.activestatus = activestatus;
	}


	public int getCheckMailId() {
		return checkMailId;
	}


	public void setCheckMailId(int checkMailId) {
		this.checkMailId = checkMailId;
	}


	public File getUserImage() {
		return userImage;
	}


	public void setUserImage(File userImage) {
		this.userImage = userImage;
	}


	public String getUserImageFileName() {
		return userImageFileName;
	}


	public void setUserImageFileName(String userImageFileName) {
		this.userImageFileName = userImageFileName;
	}


	public String getUserImageContentType() {
		return userImageContentType;
	}


	public void setUserImageContentType(String userImageContentType) {
		this.userImageContentType = userImageContentType;
	}


	public String getLoc_direction() {
		return loc_direction;
	}


	public void setLoc_direction(String loc_direction) {
		this.loc_direction = loc_direction;
	}


	public String getOwner_qualification() {
		return owner_qualification;
	}


	public void setOwner_qualification(String owner_qualification) {
		this.owner_qualification = owner_qualification;
	}

	private String websiteUrl;
	private String owner_qualification;

	public String getWebsiteUrl() {
		return websiteUrl;
	}


	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}

	ArrayList<Clinic>clinicList;
	ArrayList<Clinic>cliniclocationList;
	
	public ArrayList<Clinic> getClinicList() {
		return clinicList;
	}

	
	public ArrayList<Clinic> getCliniclocationList() {
		return cliniclocationList;
	}

	public void setCliniclocationList(
			ArrayList<Clinic> cliniclocationList) {
		this.cliniclocationList = cliniclocationList;
	}

	public void setClinicList(
			ArrayList<Clinic> clinicList) {
		this.clinicList = clinicList;
	}
	
	private int id;
	
	private String userId;
	
	private String password;
	
	private String firstName;
	
	private String lastName;
	
	private String uuserType;
	
	private int userType;
	
	private String adminAccessPssword;
	
	private String confirmPassword;
	private String contactNo;
	
	
	
//package subscription
	
	public String getContactNo() {
		return contactNo;
	}


	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	private boolean isStandalone = false;
	
	private boolean isHostedDB = false;
	
	private boolean isOnlineSingleDevice = false;
	
	private boolean isOnlineMultiDevice = false;
	
	
	
//functionality subscription
	
	private boolean isDiaryManagement = false;
	
	private boolean isAppointmentBooking = false;
	
	private boolean isBasicFinance = false;
	
	private boolean isFullFinance = false;
	
	private boolean isMedicalRecord = false;
	
	private boolean isClinicPayrollMngment = false;
	
	private boolean isClinicResourceMngment = false;
	
//Other Packages
	
	private boolean isCommunication = false;
	
	private boolean isReport = false;
	
	private boolean isAssessmentForms = false;
	
//Excess Devices
	
	private boolean isDesktop = false;
	
	private boolean isMobile = false;
	
	public boolean isMobile() {
		return isMobile;
	}


	public void setMobile(boolean isMobile) {
		this.isMobile = isMobile;
	}


	public boolean isTablet() {
		return isTablet;
	}


	public void setTablet(boolean isTablet) {
		this.isTablet = isTablet;
	}

	private boolean isIOS = false;
	
	private boolean isTablet = false;
		
	public boolean isCommunication() {
		return isCommunication;
	}


	public void setCommunication(boolean isCommunication) {
		this.isCommunication = isCommunication;
	}


	public boolean isReport() {
		return isReport;
	}


	public void setReport(boolean isReport) {
		this.isReport = isReport;
	}


	public boolean isAssessmentForms() {
		return isAssessmentForms;
	}


	public void setAssessmentForms(boolean isAssessmentForms) {
		this.isAssessmentForms = isAssessmentForms;
	}


	public boolean isDesktop() {
		return isDesktop;
	}


	public void setDesktop(boolean isDesktop) {
		this.isDesktop = isDesktop;
	}




	public boolean isIOS() {
		return isIOS;
	}


	public void setIOS(boolean isIOS) {
		this.isIOS = isIOS;
	}


	

	private String clinicName;
	
	private String clinicOwner;
	
	private String email;
	
	private String mobileNo;
	
	private String landLine;
	
	private String country;
	
	private String city;
	
	private String address;
	
	private String pinCode;
	
	private int locationid;
	
	private String locationname;
	
	private String colorName;
	
	private String searchText = "";
	
	private String checkLocation;
	
	private String addressType;
	
	private String emailId;
	
	
	public String getAddressType() {
		return addressType;
	}


	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}


	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public String getCheckLocation() {
		return checkLocation;
	}


	public void setCheckLocation(String checkLocation) {
		this.checkLocation = checkLocation;
	}


	public String getLocationname() {
		return locationname;
	}


	public void setLocationname(String locationname) {
		this.locationname = locationname;
	}


	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public int getLocationid() {
		return locationid;
	}

	public void setLocationid(int locationid) {
		this.locationid = locationid;
	}
	
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	

	public boolean isStandalone() {
		return isStandalone;
	}

	public void setStandalone(boolean isStandalone) {
		this.isStandalone = isStandalone;
	}
	
	

	public boolean isHostedDB() {
		return isHostedDB;
	}

	public void setHostedDB(boolean isHostedDB) {
		this.isHostedDB = isHostedDB;
	}

	public boolean isOnlineSingleDevice() {
		return isOnlineSingleDevice;
	}

	public void setOnlineSingleDevice(boolean isOnlineSingleDevice) {
		this.isOnlineSingleDevice = isOnlineSingleDevice;
	}

	public boolean isOnlineMultiDevice() {
		return isOnlineMultiDevice;
	}

	public void setOnlineMultiDevice(boolean isOnlineMultiDevice) {
		this.isOnlineMultiDevice = isOnlineMultiDevice;
	}

	public boolean isDiaryManagement() {
		return isDiaryManagement;
	}

	public void setDiaryManagement(boolean isDiaryManagement) {
		this.isDiaryManagement = isDiaryManagement;
	}

	public boolean isAppointmentBooking() {
		return isAppointmentBooking;
	}

	public void setAppointmentBooking(boolean isAppointmentBooking) {
		this.isAppointmentBooking = isAppointmentBooking;
	}

	public boolean isBasicFinance() {
		return isBasicFinance;
	}

	public void setBasicFinance(boolean isBasicFinance) {
		this.isBasicFinance = isBasicFinance;
	}

	public boolean isFullFinance() {
		return isFullFinance;
	}

	public void setFullFinance(boolean isFullFinance) {
		this.isFullFinance = isFullFinance;
	}

	public boolean isMedicalRecord() {
		return isMedicalRecord;
	}

	public void setMedicalRecord(boolean isMedicalRecord) {
		this.isMedicalRecord = isMedicalRecord;
	}

	public void setClinicPayrollMngment(boolean isClinicPayrollMngment) {
		this.isClinicPayrollMngment = isClinicPayrollMngment;
	}

	public void setClinicResourceMngment(boolean isClinicResourceMngment) {
		this.isClinicResourceMngment = isClinicResourceMngment;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getLandLine() {
		return landLine;
	}

	public void setLandLine(String landLine) {
		this.landLine = landLine;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	
	public boolean isClinicPayrollMngment() {
		return isClinicPayrollMngment;
	}

	public boolean isClinicResourceMngment() {
		return isClinicResourceMngment;
	}

	public String getAdminAccessPssword() {
		return adminAccessPssword;
	}

	public void setAdminAccessPssword(String adminAccessPssword) {
		this.adminAccessPssword = adminAccessPssword;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}


	public String getColorName() {
		return colorName;
	}


	public void setColorName(String colorName) {
		this.colorName = colorName;
	}


	public String getUuserType() {
		return uuserType;
	}


	public void setUuserType(String uuserType) {
		this.uuserType = uuserType;
	}

/*
	private String emailConfigureId;
	
	public String getEmailConfigureId() {
		return emailConfigureId;
	}


	public void setEmailConfigureId(String emailConfigureId) {
		this.emailConfigureId = emailConfigureId;
	}


	public String getEmailUserName() {
		return emailUserName;
	}


	public void setEmailUserName(String emailUserName) {
		this.emailUserName = emailUserName;
	}


	public String getEmailPassword() {
		return emailPassword;
	}


	public void setEmailPassword(String emailPassword) {
		this.emailPassword = emailPassword;
	}


	public String getEmailConfirmPassword() {
		return emailConfirmPassword;
	}


	public void setEmailConfirmPassword(String emailConfirmPassword) {
		this.emailConfirmPassword = emailConfirmPassword;
	}


	public String getEmailHostName() {
		return emailHostName;
	}


	public void setEmailHostName(String emailHostName) {
		this.emailHostName = emailHostName;
	}

	private String emailUserName;
	
	private String emailPassword;
	
	private String emailConfirmPassword;
	
	private String emailHostName;
	*/
	
	private int dnaCharges;

	public int getDnaCharges() {
		return dnaCharges;
	}

	public void setDnaCharges(int dnaCharges) {
		this.dnaCharges = dnaCharges;
	}


	public int getSmscount() {
		return smscount;
	}


	public void setSmscount(int smscount) {
		this.smscount = smscount;
	}


	public boolean isSmscheck() {
		return smscheck;
	}


	public void setSmscheck(boolean smscheck) {
		this.smscheck = smscheck;
	}


	public boolean isSmsPayment() {
		return smsPayment;
	}


	public void setSmsPayment(boolean smsPayment) {
		this.smsPayment = smsPayment;
	}


	public String getRegisterno() {
		return registerno;
	}


	public void setRegisterno(String registerno) {
		this.registerno = registerno;
	}


	public String getSetupstdcharge() {
		return setupstdcharge;
	}


	public void setSetupstdcharge(String setupstdcharge) {
		this.setupstdcharge = setupstdcharge;
	}


	public String getWithpayment() {
		return withpayment;
	}


	public void setWithpayment(String withpayment) {
		this.withpayment = withpayment;
	}


	public String getWithoutpayment() {
		return withoutpayment;
	}


	public void setWithoutpayment(String withoutpayment) {
		this.withoutpayment = withoutpayment;
	}


	public String getJobtitle() {
		return jobtitle;
	}


	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}


	public boolean isFlag() {
		return flag;
	}


	public void setFlag(boolean flag) {
		this.flag = flag;
	}


	public String getModulename() {
		return modulename;
	}


	public void setModulename(String modulename) {
		this.modulename = modulename;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getInvoice_date() {
		return invoice_date;
	}


	public void setInvoice_date(String invoice_date) {
		this.invoice_date = invoice_date;
	}
	public String getInvestigation_show() {
		return investigation_show;
	}


	public void setInvestigation_show(String investigation_show) {
		this.investigation_show = investigation_show;
	}

	public boolean isCriticalvaluesms() {
		return criticalvaluesms;
	}


	public void setCriticalvaluesms(boolean criticalvaluesms) {
		this.criticalvaluesms = criticalvaluesms;
	}

	public String getGstn_no() {
		return gstn_no;
	}


	public void setGstn_no(String gstn_no) {
		this.gstn_no = gstn_no;
	}

	public boolean isBdaysms() {
		return bdaysms;
	}


	public void setBdaysms(boolean bdaysms) {
		this.bdaysms = bdaysms;
	}

	public boolean isImmusms() {
		return immusms;
	}


	public void setImmusms(boolean immusms) {
		this.immusms = immusms;
	}

	public boolean isF_diagnosis_discharge() {
		return f_diagnosis_discharge;
	}


	public void setF_diagnosis_discharge(boolean f_diagnosis_discharge) {
		this.f_diagnosis_discharge = f_diagnosis_discharge;
	}

	public boolean isSeq_no_gen() {
		return seq_no_gen;
	}


	public void setSeq_no_gen(boolean seq_no_gen) {
		this.seq_no_gen = seq_no_gen;
	}

	public String getFromtime() {
		return fromtime;
	}


	public void setFromtime(String fromtime) {
		this.fromtime = fromtime;
	}


	public String getTotime() {
		return totime;
	}


	public void setTotime(String totime) {
		this.totime = totime;
	}

	public boolean isRemoveprocurement() {
		return removeprocurement;
	}


	public void setRemoveprocurement(boolean removeprocurement) {
		this.removeprocurement = removeprocurement;
	}

	public boolean isModify_disc() {
		return modify_disc;
	}


	public void setModify_disc(boolean modify_disc) {
		this.modify_disc = modify_disc;
	}

	public boolean isSmsVisitingConslt() {
		return smsVisitingConslt;
	}


	public void setSmsVisitingConslt(boolean smsVisitingConslt) {
		this.smsVisitingConslt = smsVisitingConslt;
	}

	public boolean isHospital_admin() {
		return hospital_admin;
	}


	public void setHospital_admin(boolean hospital_admin) {
		this.hospital_admin = hospital_admin;
	}

	public boolean isShow_hospital_admin() {
		return show_hospital_admin;
	}


	public void setShow_hospital_admin(boolean show_hospital_admin) {
		this.show_hospital_admin = show_hospital_admin;
	}

	public String getIpaddr() {
		return ipaddr;
	}


	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}

	public String getExpirey_date() {
		return expirey_date;
	}


	public void setExpirey_date(String expirey_date) {
		this.expirey_date = expirey_date;
	}

	public String getSubscription_date() {
		return subscription_date;
	}


	public void setSubscription_date(String subscription_date) {
		this.subscription_date = subscription_date;
	}

	public boolean isDirect_ipd() {
		return direct_ipd;
	}


	public void setDirect_ipd(boolean direct_ipd) {
		this.direct_ipd = direct_ipd;
	}

	public boolean isDrwise_ipd() {
		return drwise_ipd;
	}


	public void setDrwise_ipd(boolean drwise_ipd) {
		this.drwise_ipd = drwise_ipd;
	}

	public boolean isJobtitlewise_investigation() {
		return jobtitlewise_investigation;
	}


	public void setJobtitlewise_investigation(boolean jobtitlewise_investigation) {
		this.jobtitlewise_investigation = jobtitlewise_investigation;
	}

	public int getMlc_charge() {
		return mlc_charge;
	}


	public void setMlc_charge(int mlc_charge) {
		this.mlc_charge = mlc_charge;
	}

	public boolean isActive_clinic() {
		return active_clinic;
	}


	public void setActive_clinic(boolean active_clinic) {
		this.active_clinic = active_clinic;
	}

	public boolean isShow_wardname() {
		return show_wardname;
	}


	public void setShow_wardname(boolean show_wardname) {
		this.show_wardname = show_wardname;
	}

	public boolean isDischarge_new() {
		return discharge_new;
	}


	public void setDischarge_new(boolean discharge_new) {
		this.discharge_new = discharge_new;
	}
	

	public boolean isShow_unpost() {
		return show_unpost;
	}


	public void setShow_unpost(boolean show_unpost) {
		this.show_unpost = show_unpost;
	}

	
	
	public boolean isSms_on_newadm() {
		return sms_on_newadm;
	}


	public void setSms_on_newadm(boolean sms_on_newadm) {
		this.sms_on_newadm = sms_on_newadm;
	}


	public boolean isSms_on_bedchange() {
		return sms_on_bedchange;
	}


	public void setSms_on_bedchange(boolean sms_on_bedchange) {
		this.sms_on_bedchange = sms_on_bedchange;
	}

	public int getIpd_abbr_access() {
		return ipd_abbr_access;
	}


	public void setIpd_abbr_access(int ipd_abbr_access) {
		this.ipd_abbr_access = ipd_abbr_access;
	}

	public boolean isShow_new_ipd_id() {
		return show_new_ipd_id;
	}


	public void setShow_new_ipd_id(boolean show_new_ipd_id) {
		this.show_new_ipd_id = show_new_ipd_id;
	}

	public boolean isHidelettinvst() {
		return hidelettinvst;
	}


	public void setHidelettinvst(boolean hidelettinvst) {
		this.hidelettinvst = hidelettinvst;
	}


	public boolean isHidelettemr() {
		return hidelettemr;
	}


	public void setHidelettemr(boolean hidelettemr) {
		this.hidelettemr = hidelettemr;
	}


	public boolean isHidelettbillinv() {
		return hidelettbillinv;
	}


	public void setHidelettbillinv(boolean hidelettbillinv) {
		this.hidelettbillinv = hidelettbillinv;
	}

	public boolean isInvst_inv_apr() {
		return invst_inv_apr;
	}


	public void setInvst_inv_apr(boolean invst_inv_apr) {
		this.invst_inv_apr = invst_inv_apr;
	}

	public int getDischarge_validation() {
		return discharge_validation;
	}


	public void setDischarge_validation(int discharge_validation) {
		this.discharge_validation = discharge_validation;
	}

	public String getPatientname_field() {
		return patientname_field;
	}


	public void setPatientname_field(String patientname_field) {
		this.patientname_field = patientname_field;
	}

	public String getPractitonername_field() {
		return practitonername_field;
	}


	public void setPractitonername_field(String practitonername_field) {
		this.practitonername_field = practitonername_field;
	}

	public int getAddress_manual() {
		return address_manual;
	}


	public void setAddress_manual(int address_manual) {
		this.address_manual = address_manual;
	}

	private String investigation_show;
	private boolean criticalvaluesms;
	private String gstn_no;
	private boolean bdaysms;
	private boolean immusms;
	private boolean f_diagnosis_discharge;
	private boolean seq_no_gen;
	private String fromtime;
	private String totime;
	private boolean removeprocurement;
	private boolean modify_disc;
	private boolean smsVisitingConslt;
	private boolean hospital_admin;
	private boolean show_hospital_admin;
	private String ipaddr;
	private String subscription_date;
	private String expirey_date;
	private boolean direct_ipd;
	private boolean drwise_ipd;
	private boolean jobtitlewise_investigation;
	private int mlc_charge;
	private boolean active_clinic;
	private boolean show_wardname;
	private boolean discharge_new;
	private boolean show_unpost;
	private boolean sms_on_newadm;
	private boolean sms_on_bedchange;
	private int ipd_abbr_access;
	private boolean show_new_ipd_id;
	private boolean hidelettinvst;
	private boolean hidelettemr;
	private boolean hidelettbillinv;
	private boolean invst_inv_apr;
	private int discharge_validation;
	private String patientname_field;
	private String practitonername_field;
	private int address_manual;
	private String le_date;
	private String le_msg;
	private boolean treatment_episode;

	public String getLe_date() {
		return le_date;
	}


	public void setLe_date(String le_date) {
		this.le_date = le_date;
	}


	public String getLe_msg() {
		return le_msg;
	}


	public void setLe_msg(String le_msg) {
		this.le_msg = le_msg;
	}


	public boolean isTreatment_episode() {
		return treatment_episode;
	}


	public void setTreatment_episode(boolean treatment_episode) {
		this.treatment_episode = treatment_episode;
	}
	
	public boolean isInvest_order() {
		return invest_order;
	}


	public void setInvest_order(boolean invest_order) {
		this.invest_order = invest_order;
	}

	public boolean isDemo_access() {
		return demo_access;
	}


	public void setDemo_access(boolean demo_access) {
		this.demo_access = demo_access;
	}

	public boolean isBalgopal() {
		return balgopal;
	}


	public void setBalgopal(boolean balgopal) {
		this.balgopal = balgopal;
	}

	public boolean isJson_diagnosis() {
		return json_diagnosis;
	}


	public void setJson_diagnosis(boolean json_diagnosis) {
		this.json_diagnosis = json_diagnosis;
	}

	private boolean invest_order,demo_access;
	private boolean json_diagnosis;
	private boolean ipd_payamnt_sms,opd_payamnt_sms,adv_payamnt_sms,refund_payamnt_sms,other_payamnt_sms;


	public boolean isIpd_payamnt_sms() {
		return ipd_payamnt_sms;
	}


	public void setIpd_payamnt_sms(boolean ipd_payamnt_sms) {
		this.ipd_payamnt_sms = ipd_payamnt_sms;
	}


	public boolean isOpd_payamnt_sms() {
		return opd_payamnt_sms;
	}


	public void setOpd_payamnt_sms(boolean opd_payamnt_sms) {
		this.opd_payamnt_sms = opd_payamnt_sms;
	}


	public boolean isAdv_payamnt_sms() {
		return adv_payamnt_sms;
	}


	public void setAdv_payamnt_sms(boolean adv_payamnt_sms) {
		this.adv_payamnt_sms = adv_payamnt_sms;
	}


	public boolean isRefund_payamnt_sms() {
		return refund_payamnt_sms;
	}


	public void setRefund_payamnt_sms(boolean refund_payamnt_sms) {
		this.refund_payamnt_sms = refund_payamnt_sms;
	}


	public boolean isOther_payamnt_sms() {
		return other_payamnt_sms;
	}


	public void setOther_payamnt_sms(boolean other_payamnt_sms) {
		this.other_payamnt_sms = other_payamnt_sms;
	} 
	
	public boolean isNewdischargecard() {
		return newdischargecard;
	}


	public void setNewdischargecard(boolean newdischargecard) {
		this.newdischargecard = newdischargecard;
	}

	public boolean isInvoice_date_modify() {
		return invoice_date_modify;
	}


	public void setInvoice_date_modify(boolean invoice_date_modify) {
		this.invoice_date_modify = invoice_date_modify;
	}

	public boolean isPackage_access() {
		return package_access;
	}


	public void setPackage_access(boolean package_access) {
		this.package_access = package_access;
	}

	public boolean isDischarge_msg_hs() {
		return discharge_msg_hs;
	}


	public void setDischarge_msg_hs(boolean discharge_msg_hs) {
		this.discharge_msg_hs = discharge_msg_hs;
	}

	public boolean isBarcode_productname_show() {
		return barcode_productname_show;
	}


	public void setBarcode_productname_show(boolean barcode_productname_show) {
		this.barcode_productname_show = barcode_productname_show;
	}

	public boolean isOpd_tp_zero_invoice() {
		return opd_tp_zero_invoice;
	}


	public void setOpd_tp_zero_invoice(boolean opd_tp_zero_invoice) {
		this.opd_tp_zero_invoice = opd_tp_zero_invoice;
	}

	public boolean isRelease_notes_upload() {
		return release_notes_upload;
	}


	public void setRelease_notes_upload(boolean release_notes_upload) {
		this.release_notes_upload = release_notes_upload;
	}

	public boolean isNew_aureus_discard() {
		return new_aureus_discard;
	}


	public void setNew_aureus_discard(boolean new_aureus_discard) {
		this.new_aureus_discard = new_aureus_discard;
	}

	public int getGrn_to_prisc_location() {
		return grn_to_prisc_location;
	}


	public void setGrn_to_prisc_location(int grn_to_prisc_location) {
		this.grn_to_prisc_location = grn_to_prisc_location;
	}

	public boolean isEmr_vitals_show() {
		return emr_vitals_show;
	}


	public void setEmr_vitals_show(boolean emr_vitals_show) {
		this.emr_vitals_show = emr_vitals_show;
	}

	public boolean isDeleted_invst_charge() {
		return deleted_invst_charge;
	}


	public void setDeleted_invst_charge(boolean deleted_invst_charge) {
		this.deleted_invst_charge = deleted_invst_charge;
	}

	public String getInvoice_default_note() {
		return invoice_default_note;
	}


	public void setInvoice_default_note(String invoice_default_note) {
		this.invoice_default_note = invoice_default_note;
	}
public boolean isPcsadmin() {
		return pcsadmin;
	}


	public void setPcsadmin(boolean pcsadmin) {
		this.pcsadmin = pcsadmin;
	}

public boolean isCreate_invoice_selected_charges() {
		return create_invoice_selected_charges;
	}


	public void setCreate_invoice_selected_charges(boolean create_invoice_selected_charges) {
		this.create_invoice_selected_charges = create_invoice_selected_charges;
	}

public boolean isSms_reg_patient() {
		return sms_reg_patient;
	}


	public void setSms_reg_patient(boolean sms_reg_patient) {
		this.sms_reg_patient = sms_reg_patient;
	}

public String getOpdfollowup() {
		return opdfollowup;
	}


	public void setOpdfollowup(String opdfollowup) {
		this.opdfollowup = opdfollowup;
	}

public boolean isVermanh() {
		return vermanh;
	}


	public void setVermanh(boolean vermanh) {
		this.vermanh = vermanh;
	}

public String getLinkaddress() {
		return linkaddress;
	}


	public void setLinkaddress(String linkaddress) {
		this.linkaddress = linkaddress;
	}

public String getClinicLogo() {
		return clinicLogo;
	}


	public void setClinicLogo(String clinicLogo) {
		this.clinicLogo = clinicLogo;
	}

public boolean isWardhazp() {
		return wardhazp;
	}


	public void setWardhazp(boolean wardhazp) {
		this.wardhazp = wardhazp;
	}

public boolean isOpd_recep_sp_list() {
		return opd_recep_sp_list;
	}


	public void setOpd_recep_sp_list(boolean opd_recep_sp_list) {
		this.opd_recep_sp_list = opd_recep_sp_list;
	}

public boolean isOpd_telemed() {
		return opd_telemed;
	}


	public void setOpd_telemed(boolean opd_telemed) {
		this.opd_telemed = opd_telemed;
	}

public String getSshuser() {
		return sshuser;
	}


	public void setSshuser(String sshuser) {
		this.sshuser = sshuser;
	}

public String getDbpassword() {
		return dbpassword;
	}


	public void setDbpassword(String dbpassword) {
		this.dbpassword = dbpassword;
	}

public String getDefaultVendorServices() {
		return defaultVendorServices;
	}


	public void setDefaultVendorServices(String defaultVendorServices) {
		this.defaultVendorServices = defaultVendorServices;
	}

public boolean isSms_cancel_appointment() {
		return sms_cancel_appointment;
	}


	public void setSms_cancel_appointment(boolean sms_cancel_appointment) {
		this.sms_cancel_appointment = sms_cancel_appointment;
	}

public boolean isPackage_investigation() {
		return package_investigation;
	}


	public void setPackage_investigation(boolean package_investigation) {
		this.package_investigation = package_investigation;
	}

public boolean isDel_ipd_data_cancel_inv() {
		return del_ipd_data_cancel_inv;
	}


	public void setDel_ipd_data_cancel_inv(boolean del_ipd_data_cancel_inv) {
		this.del_ipd_data_cancel_inv = del_ipd_data_cancel_inv;
	}

private boolean pcsadmin;
	private boolean newdischargecard;
	private boolean discharge_msg_hs;
	private boolean barcode_productname_show;
	private boolean release_notes_upload;
	private boolean new_aureus_discard;
	private boolean emr_vitals_show;
	private boolean deleted_invst_charge;
	private String invoice_default_note;
	private String defaultVendorServices; 
	private boolean del_ipd_data_cancel_inv;
	public boolean isAnalytics_template() {
		return analytics_template;
	}

	public void setAnalytics_template(boolean analytics_template) {
		this.analytics_template = analytics_template;
	}


	private boolean analytics_template;
	private int wsms;


	public int getWsms() {
		return wsms;
	}


	public void setWsms(int wsms) {
		this.wsms = wsms;
	}
	
	private boolean physio;
	public boolean isPhysio() {
		return physio;
	}


	public void setPhysio(boolean physio) {
		this.physio = physio;
	}
	
}
