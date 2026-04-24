package com.apm.Registration.eu.entity;

import java.util.ArrayList;

import com.apm.DiaryManagement.eu.entity.DiaryManagement;

public class UserProfile {
	
	public String getClinicowner() {
		return clinicowner;
	}
	public void setClinicowner(String clinicowner) {
		this.clinicowner = clinicowner;
	}
	private boolean attached;
	private String inhousepatient;
	private int id;
	private String pagelimit;
	private int userType;
	private String executive;
	private int changefre;
	private boolean delete_invoice_history;
	private String userid;
	private boolean opd_user_vid_access;
	private boolean dr_opd_vid;
	private boolean hasDiary;
	private boolean secondary_doc;
	private boolean lmh_consultation_charge, lmh_other_charge, lmh_discount_type, lmh_discount;
	private String specialization;
	private String show_dept_opd_list;
	private String practitonerType;
	private String printType="0";
	private String chargeType;
	private String procurementType="0";
	private String location;
	private String state="0";
	private String acaccess;
	private String empid;
	private String doctor;
    private String ipdlocation="0";
    private String opdlocation="0";
	private String city;
	private String fixedDate;
	private String jobgroup;
	private boolean visitingdoctor;
	private boolean indv_discount;
	private String tinno;
	private String clinicname;
	private String dlno;
	private int clinicid;
	private String phone;
	private String website;
	
	private String instruction1;
	private String instruction2;
	private String instruction3;
	private String instruction4;
	
	
	private String status="0";
	private String discount="0";
	private String sale_bill="0";
	private String ledger="0";
	private String account="0";
	private String purchase_order="0";
	private int ipd=0;
	private int opd=0;
	
	
	ArrayList<DiaryManagement>rotadataList;
	private String genptn;
	private String tpaptn;
	private String wcl;
	private String isro;
	private String ntpc;
	
	private int ipdCharge;
	private int surgeonCharge;
	private String islogin;
	private String statename;
	private String locationname;
	private String wardid;
	private String labname;
	private String selectedid;
	private String globalaccess;
	private String blankletterhead;
	private String fees;
	private boolean isvisitingconsultant;
	private boolean issurgeon;
	private boolean isanesthesiologist;
	private boolean isreferred;
	private String existingdrid;
	private String referid;
	private boolean ismlc;
	
	private String vivitingper;
	private String ipdconsultper;
	private String surgeonper;
	
	private String tds;
	private String sharepercentage;
	
	private boolean refund_dashboard;
	private boolean showinvestigation;
	private boolean edit_invst_charge;
	private String reference_shareammount;
	private String reference_sharetype;
	private boolean invst_collect;
	private boolean stock_log;
	private boolean pharm_print_backdate;
	private boolean edit_paypo;
	private boolean adjustmentaccess;
	private boolean supplier_add;
	private boolean direct_refund_disc;
	private boolean ref_dis_pay;

	private boolean prisc_new_req_access;
	private boolean additional_disc;
	private boolean cancel_invoice_new;
	private boolean add_medicine;
	private boolean proc_after_stock;
	private boolean nonsystembarcode;
	private boolean change_indent_product;
	private boolean max_phar_discount;
	private boolean edit_catalogue_name;
	private int isfromvisitingdashboard;
	private double opddiscount;
	private double ipddiscount;
	private double pharpatientdiscount;
	private boolean adjustment_approve,edit_approve,multi_edit_product,multi_adjust_approve,edit_product;
	
	private boolean pprice_sale;
	private boolean prisc_temp_showother;
	private int userwiseaceess;
	private boolean modify_disc;
	private boolean freeze_unfreeze;
	private boolean editpharmacy_bill;
	private String user_language_access;
	
	private String userkeyid,noti_dis_apprd_ids,noti_dis_reqtd_ids,noti_ref_appr_ids,noti_ref_reqtd_ids;
	private int noti_dis_apprd_count,noti_dis_reqtd_count,noti_ref_appr_count,noti_ref_reqtd_count;
	private boolean deptOpdReport;
	private boolean indent_self_approve,indent_all_approve;
	private boolean inventory_report_access;
	private boolean supplier_access;
	private String commercial;
	private String Consultatantshare;
	private String hospitalname;
	private boolean mrp_access;
	private boolean sale_price_access;
	private boolean gst_access;
	private boolean purchase_price_access;
	private boolean sale_value_access;
	private boolean net_rate_access;
	private String clinic_staff;
	private String resetpassword;
	private boolean opd_discount_access;
	private String UserImageFileName;
	private String clinicowner;
	
	public String getUserImageFileName() {
		return UserImageFileName;
	}
	public void setUserImageFileName(String userImageFileName) {
		UserImageFileName = userImageFileName;
	}
	public boolean isOpd_discount_access() {
		return opd_discount_access;
	}
	public void setOpd_discount_access(boolean opd_discount_access) {
		this.opd_discount_access = opd_discount_access;
	}
	
	public String getResetpassword() {
		return resetpassword;
	}
	public void setResetpassword(String resetpassword) {
		this.resetpassword = resetpassword;
	}
	public String getClinic_staff() {
		return clinic_staff;
	}
	public void setClinic_staff(String clinic_staff) {
		this.clinic_staff = clinic_staff;
	}
	public boolean isNet_rate_access() {
		return net_rate_access;
	}
	public void setNet_rate_access(boolean net_rate_access) {
		this.net_rate_access = net_rate_access;
	}
	public boolean isSale_value_access() {
		return sale_value_access;
	}
	public void setSale_value_access(boolean sale_value_access) {
		this.sale_value_access = sale_value_access;
	}
	public boolean isPurchase_price_access() {
		return purchase_price_access;
	}
	public void setPurchase_price_access(boolean purchase_price_access) {
		this.purchase_price_access = purchase_price_access;
	}
	public boolean isGst_access() {
		return gst_access;
	}
	public void setGst_access(boolean gst_access) {
		this.gst_access = gst_access;
	}
	public boolean isSale_price_access() {
		return sale_price_access;
	}
	public void setSale_price_access(boolean sale_price_access) {
		this.sale_price_access = sale_price_access;
	}
	public boolean isMrp_access() {
		return mrp_access;
	}
	public void setMrp_access(boolean mrp_access) {
		this.mrp_access = mrp_access;
	}
	public String getHospitalname() {
		return hospitalname;
	}
	public void setHospitalname(String hospitalname) {
		this.hospitalname = hospitalname;
	}
	public String getCommercial() {
		return commercial;
	}
	public void setCommercial(String commercial) {
		this.commercial = commercial;
	}
	public String getConsultatantshare() {
		return Consultatantshare;
	}
	public void setConsultatantshare(String consultatantshare) {
		Consultatantshare = consultatantshare;
	}
	public boolean isSupplier_access() {
		return supplier_access;
	}
	public void setSupplier_access(boolean supplier_access) {
		this.supplier_access = supplier_access;
	}
	public boolean isInventory_report_access() {
		return inventory_report_access;
	}
	public void setInventory_report_access(boolean inventory_report_access) {
		this.inventory_report_access = inventory_report_access;
	}
	public boolean isIndent_self_approve() {
		return indent_self_approve;
	}
	public void setIndent_self_approve(boolean indent_self_approve) {
		this.indent_self_approve = indent_self_approve;
	}
	public boolean isIndent_all_approve() {
		return indent_all_approve;
	}
	public void setIndent_all_approve(boolean indent_all_approve) {
		this.indent_all_approve = indent_all_approve;
	}
	public boolean isLmh_consultation_charge() {
		return lmh_consultation_charge;
	}
	public void setLmh_consultation_charge(boolean lmh_consultation_charge) {
		this.lmh_consultation_charge = lmh_consultation_charge;
	}
	public boolean isLmh_other_charge() {
		return lmh_other_charge;
	}
	public void setLmh_other_charge(boolean lmh_other_charge) {
		this.lmh_other_charge = lmh_other_charge;
	}
	public boolean isLmh_discount_type() {
		return lmh_discount_type;
	}
	public void setLmh_discount_type(boolean lmh_discount_type) {
		this.lmh_discount_type = lmh_discount_type;
	}
	public boolean isLmh_discount() {
		return lmh_discount;
	}
	public void setLmh_discount(boolean lmh_discount) {
		this.lmh_discount = lmh_discount;
	}
	public String getNoti_ref_appr_ids() {
		return noti_ref_appr_ids;
	}
	public void setNoti_ref_appr_ids(String noti_ref_appr_ids) {
		this.noti_ref_appr_ids = noti_ref_appr_ids;
	}
	public String getNoti_ref_reqtd_ids() {
		return noti_ref_reqtd_ids;
	}
	public void setNoti_ref_reqtd_ids(String noti_ref_reqtd_ids) {
		this.noti_ref_reqtd_ids = noti_ref_reqtd_ids;
	}
	public int getNoti_ref_appr_count() {
		return noti_ref_appr_count;
	}
	public void setNoti_ref_appr_count(int noti_ref_appr_count) {
		this.noti_ref_appr_count = noti_ref_appr_count;
	}
	public int getNoti_ref_reqtd_count() {
		return noti_ref_reqtd_count;
	}
	public void setNoti_ref_reqtd_count(int noti_ref_reqtd_count) {
		this.noti_ref_reqtd_count = noti_ref_reqtd_count;
	}
	public String getUserkeyid() {
		return userkeyid;
	}
	public void setUserkeyid(String userkeyid) {
		this.userkeyid = userkeyid;
	}
	public String getNoti_dis_apprd_ids() {
		return noti_dis_apprd_ids;
	}
	public void setNoti_dis_apprd_ids(String noti_dis_apprd_ids) {
		this.noti_dis_apprd_ids = noti_dis_apprd_ids;
	}
	public String getNoti_dis_reqtd_ids() {
		return noti_dis_reqtd_ids;
	}
	public void setNoti_dis_reqtd_ids(String noti_dis_reqtd_ids) {
		this.noti_dis_reqtd_ids = noti_dis_reqtd_ids;
	}
	public int getNoti_dis_apprd_count() {
		return noti_dis_apprd_count;
	}
	public void setNoti_dis_apprd_count(int noti_dis_apprd_count) {
		this.noti_dis_apprd_count = noti_dis_apprd_count;
	}
	public int getNoti_dis_reqtd_count() {
		return noti_dis_reqtd_count;
	}
	public void setNoti_dis_reqtd_count(int noti_dis_reqtd_count) {
		this.noti_dis_reqtd_count = noti_dis_reqtd_count;
	}
	public String getUser_language_access() {
		return user_language_access;
	}
	public void setUser_language_access(String user_language_access) {
		this.user_language_access = user_language_access;
	}
	public boolean isEditpharmacy_bill() {
		return editpharmacy_bill;
	}
	public void setEditpharmacy_bill(boolean editpharmacy_bill) {
		this.editpharmacy_bill = editpharmacy_bill;
	}
	public boolean isFreeze_unfreeze() {
		return freeze_unfreeze;
	}
	public void setFreeze_unfreeze(boolean freeze_unfreeze) {
		this.freeze_unfreeze = freeze_unfreeze;
	}
	public boolean isModify_disc() {
		return modify_disc;
	}
	public void setModify_disc(boolean modify_disc) {
		this.modify_disc = modify_disc;
	}
	public int getUserwiseaceess() {
		return userwiseaceess;
	}
	public void setUserwiseaceess(int userwiseaceess) {
		this.userwiseaceess = userwiseaceess;
	}
	public boolean isPrisc_temp_showother() {
		return prisc_temp_showother;
	}
	public void setPrisc_temp_showother(boolean prisc_temp_showother) {
		this.prisc_temp_showother = prisc_temp_showother;
	}
	public boolean isPprice_sale() {
		return pprice_sale;
	}
	public void setPprice_sale(boolean pprice_sale) {
		this.pprice_sale = pprice_sale;
	}
	public boolean isMulti_adjust_approve() {
		return multi_adjust_approve;
	}
	public void setMulti_adjust_approve(boolean multi_adjust_approve) {
		this.multi_adjust_approve = multi_adjust_approve;
	}
	public boolean isEdit_product() {
		return edit_product;
	}
	public void setEdit_product(boolean edit_product) {
		this.edit_product = edit_product;
	}
	public boolean isAdjustment_approve() {
		return adjustment_approve;
	}
	public void setAdjustment_approve(boolean adjustment_approve) {
		this.adjustment_approve = adjustment_approve;
	}
	public boolean isEdit_approve() {
		return edit_approve;
	}
	public void setEdit_approve(boolean edit_approve) {
		this.edit_approve = edit_approve;
	}
	public boolean isMulti_edit_product() {
		return multi_edit_product;
	}
	public void setMulti_edit_product(boolean multi_edit_product) {
		this.multi_edit_product = multi_edit_product;
	}
	public double getOpddiscount() {
		return opddiscount;
	}
	public void setOpddiscount(double opddiscount) {
		this.opddiscount = opddiscount;
	}
	public double getIpddiscount() {
		return ipddiscount;
	}
	public void setIpddiscount(double ipddiscount) {
		this.ipddiscount = ipddiscount;
	}
	public double getPharpatientdiscount() {
		return pharpatientdiscount;
	}
	public void setPharpatientdiscount(double pharpatientdiscount) {
		this.pharpatientdiscount = pharpatientdiscount;
	}
	public int getIsfromvisitingdashboard() {
		return isfromvisitingdashboard;
	}
	public void setIsfromvisitingdashboard(int isfromvisitingdashboard) {
		this.isfromvisitingdashboard = isfromvisitingdashboard;
	}
	public boolean isEdit_catalogue_name() {
		return edit_catalogue_name;
	}
	public void setEdit_catalogue_name(boolean edit_catalogue_name) {
		this.edit_catalogue_name = edit_catalogue_name;
	}
	public boolean isMax_phar_discount() {
		return max_phar_discount;
	}
	public void setMax_phar_discount(boolean max_phar_discount) {
		this.max_phar_discount = max_phar_discount;
	}
	public boolean isChange_indent_product() {
		return change_indent_product;
	}
	public void setChange_indent_product(boolean change_indent_product) {
		this.change_indent_product = change_indent_product;
	}
	public boolean isNonsystembarcode() {
		return nonsystembarcode;
	}
	public void setNonsystembarcode(boolean nonsystembarcode) {
		this.nonsystembarcode = nonsystembarcode;
	}
	public boolean isProc_after_stock() {
		return proc_after_stock;
	}
	public void setProc_after_stock(boolean proc_after_stock) {
		this.proc_after_stock = proc_after_stock;
	}
	public boolean isAdd_medicine() {
		return add_medicine;
	}
	public void setAdd_medicine(boolean add_medicine) {
		this.add_medicine = add_medicine;
	}
	public boolean isCancel_invoice_new() {
		return cancel_invoice_new;
	}
	public void setCancel_invoice_new(boolean cancel_invoice_new) {
		this.cancel_invoice_new = cancel_invoice_new;
	}
	public boolean isAdditional_disc() {
		return additional_disc;
	}
	public void setAdditional_disc(boolean additional_disc) {
		this.additional_disc = additional_disc;
	}
	public boolean isPrisc_new_req_access() {
		return prisc_new_req_access;
	}
	public void setPrisc_new_req_access(boolean prisc_new_req_access) {
		this.prisc_new_req_access = prisc_new_req_access;
	}
	private boolean invoicemodify;
	public boolean isRef_dis_pay() {
		return ref_dis_pay;
	}
	public void setRef_dis_pay(boolean ref_dis_pay) {
		this.ref_dis_pay = ref_dis_pay;
	}
	public boolean isDirect_refund_disc() {
		return direct_refund_disc;
	}
	public void setDirect_refund_disc(boolean direct_refund_disc) {
		this.direct_refund_disc = direct_refund_disc;
	}
	public boolean isSupplier_add() {
		return supplier_add;
	}
	public void setSupplier_add(boolean supplier_add) {
		this.supplier_add = supplier_add;
	}
	public boolean isAdjustmentaccess() {
		return adjustmentaccess;
	}
	public void setAdjustmentaccess(boolean adjustmentaccess) {
		this.adjustmentaccess = adjustmentaccess;
	}
	public boolean isEdit_paypo() {
		return edit_paypo;
	}
	public void setEdit_paypo(boolean edit_paypo) {
		this.edit_paypo = edit_paypo;
	}
	public boolean isStock_log() {
		return stock_log;
	}
	public void setStock_log(boolean stock_log) {
		this.stock_log = stock_log;
	}
	public boolean getInvst_collect() {
		return invst_collect;
	}
	public void setInvst_collect(boolean invst_collect) {
		this.invst_collect = invst_collect;
	}
	public String getReference_shareammount() {
		return reference_shareammount;
	}
	public void setReference_shareammount(String reference_shareammount) {
		this.reference_shareammount = reference_shareammount;
	}
	public String getReference_sharetype() {
		return reference_sharetype;
	}
	public void setReference_sharetype(String reference_sharetype) {
		this.reference_sharetype = reference_sharetype;
	}
	public boolean isEdit_invst_charge() {
		return edit_invst_charge;
	}
	public void setEdit_invst_charge(boolean edit_invst_charge) {
		this.edit_invst_charge = edit_invst_charge;
	}
	public boolean isShowinvestigation() {
		return showinvestigation;
	}
	public void setShowinvestigation(boolean showinvestigation) {
		this.showinvestigation = showinvestigation;
	}
	public boolean isRefund_dashboard() {
		return refund_dashboard;
	}
	public void setRefund_dashboard(boolean refund_dashboard) {
		this.refund_dashboard = refund_dashboard;
	}
	public String getSharepercentage() {
		return sharepercentage;
	}
	public void setSharepercentage(String sharepercentage) {
		this.sharepercentage = sharepercentage;
	}
	public String getTds() {
		return tds;
	}
	public void setTds(String tds) {
		this.tds = tds;
	}
	public String getVivitingper() {
		return vivitingper;
	}
	public void setVivitingper(String vivitingper) {
		this.vivitingper = vivitingper;
	}
	public String getIpdconsultper() {
		return ipdconsultper;
	}
	public void setIpdconsultper(String ipdconsultper) {
		this.ipdconsultper = ipdconsultper;
	}
	public String getSurgeonper() {
		return surgeonper;
	}
	public void setSurgeonper(String surgeonper) {
		this.surgeonper = surgeonper;
	}
	public boolean isIsmlc() {
		return ismlc;
	}
	public void setIsmlc(boolean ismlc) {
		this.ismlc = ismlc;
	}
	public String getReferid() {
		return referid;
	}
	public void setReferid(String referid) {
		this.referid = referid;
	}
	public boolean isIsvisitingconsultant() {
		return isvisitingconsultant;
	}
	public void setIsvisitingconsultant(boolean isvisitingconsultant) {
		this.isvisitingconsultant = isvisitingconsultant;
	}
	public boolean isIssurgeon() {
		return issurgeon;
	}
	public void setIssurgeon(boolean issurgeon) {
		this.issurgeon = issurgeon;
	}
	public boolean isIsanesthesiologist() {
		return isanesthesiologist;
	}
	public void setIsanesthesiologist(boolean isanesthesiologist) {
		this.isanesthesiologist = isanesthesiologist;
	}
	public boolean isIsreferred() {
		return isreferred;
	}
	public void setIsreferred(boolean isreferred) {
		this.isreferred = isreferred;
	}
	public String getExistingdrid() {
		return existingdrid;
	}
	public void setExistingdrid(String existingdrid) {
		this.existingdrid = existingdrid;
	}
	public String getFees() {
		return fees;
	}
	public void setFees(String fees) {
		this.fees = fees;
	}
	public String getBlankletterhead() {
		return blankletterhead;
	}
	public void setBlankletterhead(String blankletterhead) {
		this.blankletterhead = blankletterhead;
	}
	public String getGlobalaccess() {
		return globalaccess;
	}
	public void setGlobalaccess(String globalaccess) {
		this.globalaccess = globalaccess;
	}
	public String getSelectedid() {
		return selectedid;
	}
	public void setSelectedid(String selectedid) {
		this.selectedid = selectedid;
	}
	public String getLabname() {
		return labname;
	}
	public void setLabname(String labname) {
		this.labname = labname;
	}
	public String getLocationname() {
		return locationname;
	}
	public void setLocationname(String locationname) {
		this.locationname = locationname;
	}
	public String getStatename() {
		return statename;
	}
	public void setStatename(String statename) {
		this.statename = statename;
	}
	public String getIslogin() {
		return islogin;
	}
	public void setIslogin(String islogin) {
		this.islogin = islogin;
	}
	public int getIpdCharge() {
		return ipdCharge;
	}
	public void setIpdCharge(int ipdCharge) {
		this.ipdCharge = ipdCharge;
	}
	public int getSurgeonCharge() {
		return surgeonCharge;
	}
	public void setSurgeonCharge(int surgeonCharge) {
		this.surgeonCharge = surgeonCharge;
	}
	public String getGenptn() {
		return genptn;
	}
	public void setGenptn(String genptn) {
		this.genptn = genptn;
	}
	public String getTpaptn() {
		return tpaptn;
	}
	public void setTpaptn(String tpaptn) {
		this.tpaptn = tpaptn;
	}
	public String getWcl() {
		return wcl;
	}
	public void setWcl(String wcl) {
		this.wcl = wcl;
	}
	public String getIsro() {
		return isro;
	}
	public void setIsro(String isro) {
		this.isro = isro;
	}
	public String getNtpc() {
		return ntpc;
	}
	public void setNtpc(String ntpc) {
		this.ntpc = ntpc;
	}
	public ArrayList<DiaryManagement> getRotadataList() {
		return rotadataList;
	}
	public void setRotadataList(ArrayList<DiaryManagement> rotadataList) {
		this.rotadataList = rotadataList;
	}
	public String getJobgroup() {
		return jobgroup;
	}
	public void setJobgroup(String jobgroup) {
		this.jobgroup = jobgroup;
	}
	public String getDoctor() {
		return doctor;
	}
	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}
	public String getChargeType() {
		return chargeType;
	}
	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}
	public String getPractitonerType() {
		return practitonerType;
	}
	public void setPractitonerType(String practitonerType) {
		this.practitonerType = practitonerType;
	}
	public boolean isHasDiary() {
		return hasDiary;
	}
	public void setHasDiary(boolean hasDiary) {
		this.hasDiary = hasDiary;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
 
	private String loginstatus;
	
	public String getLoginstatus() {
		return loginstatus;
	}
	public void setLoginstatus(String loginstatus) {
		this.loginstatus = loginstatus;
	}
	private String password;
	
	private boolean usercheck;
	
	private boolean appointmentdiary;
	
	private int compressionrate;
	
	private boolean isavailableonline;
	
	private boolean loginsystem;
	
	private boolean apmremote;
	
	private boolean onlinebooking;
	
	private String lastchanged;
	
	private String fullname;
	
	private String firstname;
	
	private String address;
	
	private String postcode;
	
	private String town;
	
	private String county;
	
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getLastchanged() {
		return lastchanged;
	}
	public void setLastchanged(String lastchanged) {
		this.lastchanged = lastchanged;
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
	
	
	private String lastname;
	
	private String diarycolor;
	
	private String diarycolumnposition;
	
	private String initial;
	
	private String jobtitle;
	
	private String discription;
	
	private String appointmentbookingcontem;
	
	private String qualification;
	
	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}


	private String surname;
	
	public int getChangefre() {
		return changefre;
	}
	public void setChangefre(int changefre) {
		this.changefre = changefre;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isLoginsystem() {
		return loginsystem;
	}
	public void setLoginsystem(boolean loginsystem) {
		this.loginsystem = loginsystem;
	}
	public boolean isApmremote() {
		return apmremote;
	}
	public void setApmremote(boolean apmremote) {
		this.apmremote = apmremote;
	}
	public boolean isOnlinebooking() {
		return onlinebooking;
	}
	public void setOnlinebooking(boolean onlinebooking) {
		this.onlinebooking = onlinebooking;
	}
	public String getAppointmentbookingcontem() {
		return appointmentbookingcontem;
	}
	public void setAppointmentbookingcontem(String appointmentbookingcontem) {
		this.appointmentbookingcontem = appointmentbookingcontem;
	}
	public String getAppointmentbookingdnatem() {
		return appointmentbookingdnatem;
	}
	public void setAppointmentbookingdnatem(String appointmentbookingdnatem) {
		this.appointmentbookingdnatem = appointmentbookingdnatem;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	private String appointmentbookingdnatem;
	
	private String email;
	
	private String mobile;
	
	private String registerno;
	
	private String usergroup;	
	
	private String onlinename;
	
	private String diciplineName;
		
	public String getDiciplineName() {
		return diciplineName;
	}
	public void setDiciplineName(String diciplineName) {
		this.diciplineName = diciplineName;
	}
	public ArrayList<String> getDiciplineList() {
		return diciplineList;
	}
	public void setDiciplineList(ArrayList<String> diciplineList) {
		this.diciplineList = diciplineList;
	}
	private ArrayList<String>diciplineList;
	
	private ArrayList<String>diarycolorList;
	
	private ArrayList<String>abctList;
	
	private ArrayList<String>adtList;
		
	
	public int getId() {
		return id;
	}
	public ArrayList<String> getDiarycolorList() {
		return diarycolorList;
	}
	public void setDiarycolorList(ArrayList<String> diarycolorList) {
		this.diarycolorList = diarycolorList;
	}
	public void setId(int id) {
		this.id = id;
	}
	
		
		
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public boolean isUsercheck() {
		return usercheck;
	}
	public void setUsercheck(boolean usercheck) {
		this.usercheck = usercheck;
	}
	public int getCompressionrate()
	{
		return compressionrate;
	}
	
	public void setCompressionrate(int compressionrate)
	{
		this.compressionrate=compressionrate;		
	}
	
	public 	String getFullname()
	{
		return fullname;
	}
	
	public void setFullname(String fullname)
	{
		this.fullname=fullname;		
	}
	
	public 	String getDiarycolor()
	{
		return diarycolor;
	}
	
	public void setDiarycolor(String diarycolor)
	{
		this.diarycolor=diarycolor;		
	}
	
	public String getDiarycolumnposition()
	{
		return diarycolumnposition;
	}
	
	public void setDiarycolumnposition(String diarycolumnposition)
	{
		this.diarycolumnposition=diarycolumnposition;		
	}
	
	public String getInitial()
	{
		return initial;
	}
	
	public void setInitial(String initial)
	{
		this.initial=initial;
	}
	
	public String getJobtitle()
	{
		return jobtitle;
	}
	
	public void setJobtitle(String jobtitle)
	{
		this.jobtitle=jobtitle;
	}
	
	public String getDiscription()
	{
		return discription;
	}
	
	public void setDiscription(String discription)
	{
		this.discription=discription;
	}
	
	public String getRegisterno()
	{
		return registerno;
	}
	
	public void setRegisterno(String registerno)
	{
		this.registerno=registerno;
	}
	
	public String getUsergroup()
	{
		return usergroup;
	}
	
	public void setUsergroup(String usergroup)
	{
		this.usergroup=usergroup;
	}
	
	public String getOnlinename() {
		return onlinename;
	}

	public void setOnlinename(String onlinename) {
		this.onlinename = onlinename;
	}
	public boolean isAppointmentdiary() {
		return appointmentdiary;
	}
	public void setAppointmentdiary(boolean appointmentdiary) {
		this.appointmentdiary = appointmentdiary;
	}
	public boolean isIsavailableonline() {
		return isavailableonline;
	}
	public void setIsavailableonline(boolean isavailableonline) {
		this.isavailableonline = isavailableonline;
	}
	public ArrayList<String> getAbctList() {
		return abctList;
	}
	public void setAbctList(ArrayList<String> abctList) {
		this.abctList = abctList;
	}
	public ArrayList<String> getAdtList() {
		return adtList;
	}
	public void setAdtList(ArrayList<String> adtList) {
		this.adtList = adtList;
	}
	
	private int dnaCharge;
	
	private int compAppCharge;
	
	public int getDnaCharge() {
		return dnaCharge;
	}
	public void setDnaCharge(int dnaCharge) {
		this.dnaCharge = dnaCharge;
	}
	public int getCompAppCharge() {
		return compAppCharge;
	}
	public void setCompAppCharge(int compAppCharge) {
		this.compAppCharge = compAppCharge;
	}
	public boolean isVisitingdoctor() {
		return visitingdoctor;
	}
	public void setVisitingdoctor(boolean visitingdoctor) {
		this.visitingdoctor = visitingdoctor;
	}
	public String getTinno() {
		return tinno;
	}
	public void setTinno(String tinno) {
		this.tinno = tinno;
	}
	public String getClinicname() {
		return clinicname;
	}
	public void setClinicname(String clinicname) {
		this.clinicname = clinicname;
	}
	public String getDlno() {
		return dlno;
	}
	public void setDlno(String dlno) {
		this.dlno = dlno;
	}
	public int getClinicid() {
		return clinicid;
	}
	public void setClinicid(int clinicid) {
		this.clinicid = clinicid;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getSale_bill() {
		return sale_bill;
	}
	public void setSale_bill(String sale_bill) {
		this.sale_bill = sale_bill;
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
	public String getInstruction1() {
		return instruction1;
	}
	public void setInstruction1(String instruction1) {
		this.instruction1 = instruction1;
	}
	public String getInstruction2() {
		return instruction2;
	}
	public void setInstruction2(String instruction2) {
		this.instruction2 = instruction2;
	}
	public String getInstruction3() {
		return instruction3;
	}
	public void setInstruction3(String instruction3) {
		this.instruction3 = instruction3;
	}
	public String getInstruction4() {
		return instruction4;
	}
	public void setInstruction4(String instruction4) {
		this.instruction4 = instruction4;
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
	public int getIpd() {
		return ipd;
	}
	public void setIpd(int ipd) {
		this.ipd = ipd;
	}
	public int getOpd() {
		return opd;
	}
	public void setOpd(int opd) {
		this.opd = opd;
	}
	public String getProcurementType() {
		return procurementType;
	}
	public void setProcurementType(String procurementType) {
		this.procurementType = procurementType;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPrintType() {
		return printType;
	}
	public void setPrintType(String printType) {
		this.printType = printType;
	}
	public String getInhousepatient() {
		return inhousepatient;
	}
	public void setInhousepatient(String inhousepatient) {
		this.inhousepatient = inhousepatient;
	}
	public String getWardid() {
		return wardid;
	}
	public void setWardid(String wardid) {
		this.wardid = wardid;
	}
	public String getAcaccess() {
		return acaccess;
	}
	public void setAcaccess(String acaccess) {
		this.acaccess = acaccess;
	}


public String getRefsharetype() {
		return refsharetype;
	}
	public void setRefsharetype(String refsharetype) {
		this.refsharetype = refsharetype;
	}


public String getRefshareammount() {
		return refshareammount;
	}
	public void setRefshareammount(String refshareammount) {
		this.refshareammount = refshareammount;
	}


public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}


public boolean isShowletterhd() {
		return showletterhd;
	}
	public void setShowletterhd(boolean showletterhd) {
		this.showletterhd = showletterhd;
	}


public String getPagelimit() {
		return pagelimit;
	}
	public void setPagelimit(String pagelimit) {
		this.pagelimit = pagelimit;
	}


public boolean isIsdotmatrix() {
		return isdotmatrix;
	}
	public void setIsdotmatrix(boolean isdotmatrix) {
		this.isdotmatrix = isdotmatrix;
	}


public boolean isPharm_print_backdate() {
		return pharm_print_backdate;
	}
	public void setPharm_print_backdate(boolean pharm_print_backdate) {
		this.pharm_print_backdate = pharm_print_backdate;
	}




public String getEditcharges() {
		return editcharges;
	}
	public void setEditcharges(String editcharges) {
		this.editcharges = editcharges;
	}


public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}


public String getQuery_type() {
		return query_type;
	}
	public void setQuery_type(String query_type) {
		this.query_type = query_type;
	}


public boolean isIsfixable() {
		return isfixable;
	}
	public void setIsfixable(boolean isfixable) {
		this.isfixable = isfixable;
	}


public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}


public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}


public String getMedlimit() {
		return medlimit;
	}
	public void setMedlimit(String medlimit) {
		this.medlimit = medlimit;
	}


private String refsharetype;
private String refshareammount;
private String dob;	
private boolean showletterhd;
private boolean isdotmatrix;
private String editcharges;
private String query;
private String query_type;
private boolean isfixable;
private String date;
private String time;
private String medlimit;
private String handledby,usr,remark,priorityclient;
private boolean msgread;
private boolean treatmentacc;
private boolean payrollaccess;
public String getHandledby() {
	return handledby;
}
public void setHandledby(String handledby) {
	this.handledby = handledby;
}
public String getUsr() {
	return usr;
}
public void setUsr(String usr) {
	this.usr = usr;
}
public String getRemark() {
	return remark;
}
public void setRemark(String remark) {
	this.remark = remark;
}
public boolean getMsgread() {
	return msgread;
}
public void setMsgread(boolean msgread) {
	this.msgread = msgread;
}
public String getPriorityclient() {
	return priorityclient;
}
public void setPriorityclient(String priorityclient) {
	this.priorityclient = priorityclient;
}
public boolean isTreatmentacc() {
	return treatmentacc;
}
public void setTreatmentacc(boolean treatmentacc) {
	this.treatmentacc = treatmentacc;
}

public String getModulename() {
	return modulename;
}
public void setModulename(String modulename) {
	this.modulename = modulename;
}


public String getIssuetype() {
	return issuetype;
}
public void setIssuetype(String issuetype) {
	this.issuetype = issuetype;
}


public boolean isPaymentReport() {
	return paymentReport;
}
public void setPaymentReport(boolean paymentReport) {
	this.paymentReport = paymentReport;
}


public boolean isAdd_manual_charge() {
	return add_manual_charge;
}
public void setAdd_manual_charge(boolean add_manual_charge) {
	this.add_manual_charge = add_manual_charge;
}


public boolean isUpdate_investigation_charge() {
	return update_investigation_charge;
}
public void setUpdate_investigation_charge(boolean update_investigation_charge) {
	this.update_investigation_charge = update_investigation_charge;
}


public boolean isInvoicemodify() {
	return invoicemodify;
}
public void setInvoicemodify(boolean invoicemodify) {
	this.invoicemodify = invoicemodify;
}


private String modulename;
private String issuetype;
private boolean paymentReport;
private boolean add_manual_charge;
private boolean  update_investigation_charge;
private String paymentReportNoteUserid,paymentReportNote,paymentReportNoteStatus,paymentReportNoteId,paymentReportNoteReqDate,paymentReportNoteApprovedUserID,paymentReportNoteApproveDate,paymentReportNotePendingUserId,paymentReportNotePendingDate;

public String getPaymentReportNoteUserid() {
	return paymentReportNoteUserid;
}
public void setPaymentReportNoteUserid(String paymentReportNoteUserid) {
	this.paymentReportNoteUserid = paymentReportNoteUserid;
}
public String getPaymentReportNote() {
	return paymentReportNote;
}
public void setPaymentReportNote(String paymentReportNote) {
	this.paymentReportNote = paymentReportNote;
}
public String getPaymentReportNoteReqDate() {
	return paymentReportNoteReqDate;
}
public void setPaymentReportNoteReqDate(String paymentReportNoteReqDate) {
	this.paymentReportNoteReqDate = paymentReportNoteReqDate;
}
public String getPaymentReportNoteApprovedUserID() {
	return paymentReportNoteApprovedUserID;
}
public void setPaymentReportNoteApprovedUserID(String paymentReportNoteApprovedUserID) {
	this.paymentReportNoteApprovedUserID = paymentReportNoteApprovedUserID;
}
public String getPaymentReportNoteApproveDate() {
	return paymentReportNoteApproveDate;
}
public void setPaymentReportNoteApproveDate(String paymentReportNoteApproveDate) {
	this.paymentReportNoteApproveDate = paymentReportNoteApproveDate;
}
public String getPaymentReportNotePendingUserId() {
	return paymentReportNotePendingUserId;
}
public void setPaymentReportNotePendingUserId(String paymentReportNotePendingUserId) {
	this.paymentReportNotePendingUserId = paymentReportNotePendingUserId;
}
public String getPaymentReportNotePendingDate() {
	return paymentReportNotePendingDate;
}
public void setPaymentReportNotePendingDate(String paymentReportNotePendingDate) {
	this.paymentReportNotePendingDate = paymentReportNotePendingDate;
}
public String getPaymentReportNoteId() {
	return paymentReportNoteId;
}
public void setPaymentReportNoteId(String paymentReportNoteId) {
	this.paymentReportNoteId = paymentReportNoteId;
}
public String getPaymentReportNoteStatus() {
	return paymentReportNoteStatus;
}
public void setPaymentReportNoteStatus(String paymentReportNoteStatus) {
	this.paymentReportNoteStatus = paymentReportNoteStatus;
}
public boolean isIndv_discount() {
	return indv_discount;
}
public void setIndv_discount(boolean indv_discount) {
	this.indv_discount = indv_discount;
}
public String getEmpid() {
	return empid;
}
public void setEmpid(String empid) {
	this.empid = empid;
}
public boolean isPayrollaccess() {
	return payrollaccess;
}
public void setPayrollaccess(boolean payrollaccess) {
	this.payrollaccess = payrollaccess;
}
public String getLicenceId() {
	return licenceId;
}
public void setLicenceId(String licenceId) {
	this.licenceId = licenceId;
}
public String getAltMobNo() {
	return altMobNo;
}
public void setAltMobNo(String altMobNo) {
	this.altMobNo = altMobNo;
}
public String getWarname() {
	return warname;
}
public void setWarname(String warname) {
	this.warname = warname;
}
public String getFilename() {
	return filename;
}
public void setFilename(String filename) {
	this.filename = filename;
}
public String getDatetime() {
	return datetime;
}
public void setDatetime(String datetime) {
	this.datetime = datetime;
}
public boolean isAdd_charge_amt_edit() {
	return add_charge_amt_edit;
}
public void setAdd_charge_amt_edit(boolean add_charge_amt_edit) {
	this.add_charge_amt_edit = add_charge_amt_edit;
}
public boolean isOpd_user_vid_access() {
	return opd_user_vid_access;
}
public void setOpd_user_vid_access(boolean opd_user_vid_access) {
	this.opd_user_vid_access = opd_user_vid_access;
}
public boolean isDr_opd_vid() {
	return dr_opd_vid;
}
public void setDr_opd_vid(boolean dr_opd_vid) {
	this.dr_opd_vid = dr_opd_vid;
}
public boolean isManual_sms_vaccine() {
	return manual_sms_vaccine;
}
public void setManual_sms_vaccine(boolean manual_sms_vaccine) {
	this.manual_sms_vaccine = manual_sms_vaccine;
}
public boolean isChargable() {
	return chargable;
}
public void setChargable(boolean chargable) {
	this.chargable = chargable;
}
public String getTimeTaken() {
	return timeTaken;
}
public void setTimeTaken(String timeTaken) {
	this.timeTaken = timeTaken;
}
public boolean isAdmsn_date_edit() {
	return admsn_date_edit;
}
public void setAdmsn_date_edit(boolean admsn_date_edit) {
	this.admsn_date_edit = admsn_date_edit;
}
public boolean isDelete_invoice_history() {
	return delete_invoice_history;
}
public void setDelete_invoice_history(boolean delete_invoice_history) {
	this.delete_invoice_history = delete_invoice_history;
}
public String getExecutive() {
	return executive;
}
public void setExecutive(String executive) {
	this.executive = executive;
}
public String getFixedDate() {
	return fixedDate;
}
public void setFixedDate(String fixedDate) {
	this.fixedDate = fixedDate;
}
public boolean isAttached() {
	return attached;
}
public void setAttached(boolean attached) {
	this.attached = attached;
}
public String getShow_dept_opd_list() {
	return show_dept_opd_list;
}
public void setShow_dept_opd_list(String show_dept_opd_list) {
	this.show_dept_opd_list = show_dept_opd_list;
}
public boolean isSecondary_doc() {
	return secondary_doc;
}
public void setSecondary_doc(boolean secondary_doc) {
	this.secondary_doc = secondary_doc;
}
public boolean isDeptOpdReport() {
	return deptOpdReport;
}
public void setDeptOpdReport(boolean deptOpdReport) {
	this.deptOpdReport = deptOpdReport;
}
private String licenceId;
private String altMobNo;
private String warname,filename,datetime;
private boolean add_charge_amt_edit,manual_sms_vaccine;
private boolean chargable;
private String timeTaken;
private boolean admsn_date_edit ;
}
