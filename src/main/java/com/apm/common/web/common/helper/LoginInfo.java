package com.apm.common.web.common.helper;






import java.util.ArrayList;



public class LoginInfo {

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

	public boolean isOpd_discount_access() {
		return opd_discount_access;
	}

	public void setOpd_discount_access(boolean opd_discount_access) {
		this.opd_discount_access = opd_discount_access;
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

	public String getSms_type() {
		return sms_type;
	}

	public void setSms_type(String sms_type) {
		this.sms_type = sms_type;
	}

	public String getPatient_name() {
		return patient_name;
	}

	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}

	public boolean isMbbs() {
		return mbbs;
	}

	public void setMbbs(boolean mbbs) {
		this.mbbs = mbbs;
	}

	public boolean isInventory_openclose_report() {
		return inventory_openclose_report;
	}

	public void setInventory_openclose_report(boolean inventory_openclose_report) {
		this.inventory_openclose_report = inventory_openclose_report;
	}

	public boolean isDetailed_inventory_openclose_report() {
		return detailed_inventory_openclose_report;
	}

	public void setDetailed_inventory_openclose_report(boolean detailed_inventory_openclose_report) {
		this.detailed_inventory_openclose_report = detailed_inventory_openclose_report;
	}

	public boolean isInventory_openclose_catalogue() {
		return inventory_openclose_catalogue;
	}

	public void setInventory_openclose_catalogue(boolean inventory_openclose_catalogue) {
		this.inventory_openclose_catalogue = inventory_openclose_catalogue;
	}

	public boolean isInventory_stock_report() {
		return inventory_stock_report;
	}

	public void setInventory_stock_report(boolean inventory_stock_report) {
		this.inventory_stock_report = inventory_stock_report;
	}

	public boolean isInventory_sale_report() {
		return inventory_sale_report;
	}

	public void setInventory_sale_report(boolean inventory_sale_report) {
		this.inventory_sale_report = inventory_sale_report;
	}

	public boolean isPayment_receive_report() {
		return payment_receive_report;
	}

	public void setPayment_receive_report(boolean payment_receive_report) {
		this.payment_receive_report = payment_receive_report;
	}

	public boolean isInventory_itemwise_sale() {
		return inventory_itemwise_sale;
	}

	public void setInventory_itemwise_sale(boolean inventory_itemwise_sale) {
		this.inventory_itemwise_sale = inventory_itemwise_sale;
	}

	public boolean isProductwise_sale_report() {
		return productwise_sale_report;
	}

	public void setProductwise_sale_report(boolean productwise_sale_report) {
		this.productwise_sale_report = productwise_sale_report;
	}

	public boolean isCataloguewise_sale_report() {
		return cataloguewise_sale_report;
	}

	public void setCataloguewise_sale_report(boolean cataloguewise_sale_report) {
		this.cataloguewise_sale_report = cataloguewise_sale_report;
	}

	public boolean isGst_report() {
		return gst_report;
	}

	public void setGst_report(boolean gst_report) {
		this.gst_report = gst_report;
	}

	public boolean isGrn_gst_report() {
		return grn_gst_report;
	}

	public void setGrn_gst_report(boolean grn_gst_report) {
		this.grn_gst_report = grn_gst_report;
	}

	public boolean isSupplier_payment_report() {
		return supplier_payment_report;
	}

	public void setSupplier_payment_report(boolean supplier_payment_report) {
		this.supplier_payment_report = supplier_payment_report;
	}

	public boolean isInventory_report() {
		return inventory_report;
	}

	public void setInventory_report(boolean inventory_report) {
		this.inventory_report = inventory_report;
	}

	public boolean isRevenue_matrix() {
		return revenue_matrix;
	}

	public void setRevenue_matrix(boolean revenue_matrix) {
		this.revenue_matrix = revenue_matrix;
	}

	public boolean isAdmission_discharge() {
		return admission_discharge;
	}

	public void setAdmission_discharge(boolean admission_discharge) {
		this.admission_discharge = admission_discharge;
	}

	public boolean isDeptwise_revenue_report() {
		return deptwise_revenue_report;
	}

	public void setDeptwise_revenue_report(boolean deptwise_revenue_report) {
		this.deptwise_revenue_report = deptwise_revenue_report;
	}

	public boolean isConsultation_report() {
		return consultation_report;
	}

	public void setConsultation_report(boolean consultation_report) {
		this.consultation_report = consultation_report;
	}

	public boolean isBed_occupancy_per_day() {
		return bed_occupancy_per_day;
	}

	public void setBed_occupancy_per_day(boolean bed_occupancy_per_day) {
		this.bed_occupancy_per_day = bed_occupancy_per_day;
	}

	public boolean isRender_charges_report() {
		return render_charges_report;
	}

	public void setRender_charges_report(boolean render_charges_report) {
		this.render_charges_report = render_charges_report;
	}

	public boolean isTotal_revenue_report() {
		return total_revenue_report;
	}

	public void setTotal_revenue_report(boolean total_revenue_report) {
		this.total_revenue_report = total_revenue_report;
	}

	public boolean isWard_wise_rev_report() {
		return ward_wise_rev_report;
	}

	public void setWard_wise_rev_report(boolean ward_wise_rev_report) {
		this.ward_wise_rev_report = ward_wise_rev_report;
	}

	public boolean isOpd_tat_report() {
		return opd_tat_report;
	}

	public void setOpd_tat_report(boolean opd_tat_report) {
		this.opd_tat_report = opd_tat_report;
	}

	public boolean isBirthplace_report() {
		return birthplace_report;
	}

	public void setBirthplace_report(boolean birthplace_report) {
		this.birthplace_report = birthplace_report;
	}

	public boolean isConsumption_report() {
		return consumption_report;
	}

	public void setConsumption_report(boolean consumption_report) {
		this.consumption_report = consumption_report;
	}

	public boolean isCathlab_stock_report() {
		return cathlab_stock_report;
	}

	public void setCathlab_stock_report(boolean cathlab_stock_report) {
		this.cathlab_stock_report = cathlab_stock_report;
	}

	public boolean isDetail_grn_report() {
		return detail_grn_report;
	}

	public void setDetail_grn_report(boolean detail_grn_report) {
		this.detail_grn_report = detail_grn_report;
	}

	public boolean isCathlab_opening_closing() {
		return cathlab_opening_closing;
	}

	public void setCathlab_opening_closing(boolean cathlab_opening_closing) {
		this.cathlab_opening_closing = cathlab_opening_closing;
	}

	public boolean isItem_wise_sale_report() {
		return item_wise_sale_report;
	}

	public void setItem_wise_sale_report(boolean item_wise_sale_report) {
		this.item_wise_sale_report = item_wise_sale_report;
	}

	public boolean isPatient_consumption_report() {
		return patient_consumption_report;
	}

	public void setPatient_consumption_report(boolean patient_consumption_report) {
		this.patient_consumption_report = patient_consumption_report;
	}

	public boolean isPayable_aging_report() {
		return payable_aging_report;
	}

	public void setPayable_aging_report(boolean payable_aging_report) {
		this.payable_aging_report = payable_aging_report;
	}

	public boolean isStock_report() {
		return stock_report;
	}

	public void setStock_report(boolean stock_report) {
		this.stock_report = stock_report;
	}

	public boolean isInvstwise_count_report() {
		return invstwise_count_report;
	}

	public void setInvstwise_count_report(boolean invstwise_count_report) {
		this.invstwise_count_report = invstwise_count_report;
	}

	public boolean isInvst_criticalval_report() {
		return invst_criticalval_report;
	}

	public void setInvst_criticalval_report(boolean invst_criticalval_report) {
		this.invst_criticalval_report = invst_criticalval_report;
	}

	public boolean isInvst_tat_report() {
		return invst_tat_report;
	}

	public void setInvst_tat_report(boolean invst_tat_report) {
		this.invst_tat_report = invst_tat_report;
	}

	public boolean isInvst_opdipd_report() {
		return invst_opdipd_report;
	}

	public void setInvst_opdipd_report(boolean invst_opdipd_report) {
		this.invst_opdipd_report = invst_opdipd_report;
	}

	public boolean isInvst_revcount_namewise() {
		return invst_revcount_namewise;
	}

	public void setInvst_revcount_namewise(boolean invst_revcount_namewise) {
		this.invst_revcount_namewise = invst_revcount_namewise;
	}

	public boolean isInvestigation_revenue_report() {
		return investigation_revenue_report;
	}

	public void setInvestigation_revenue_report(boolean investigation_revenue_report) {
		this.investigation_revenue_report = investigation_revenue_report;
	}

	public boolean isInvestigation_package_report() {
		return investigation_package_report;
	}

	public void setInvestigation_package_report(boolean investigation_package_report) {
		this.investigation_package_report = investigation_package_report;
	}

	public boolean isInvestigation_count_report() {
		return investigation_count_report;
	}

	public void setInvestigation_count_report(boolean investigation_count_report) {
		this.investigation_count_report = investigation_count_report;
	}

	public boolean isLab_report() {
		return lab_report;
	}

	public void setLab_report(boolean lab_report) {
		this.lab_report = lab_report;
	}

	public boolean isOut_source_report() {
		return out_source_report;
	}

	public void setOut_source_report(boolean out_source_report) {
		this.out_source_report = out_source_report;
	}

	public boolean isPractitioner_share_report() {
		return practitioner_share_report;
	}

	public void setPractitioner_share_report(boolean practitioner_share_report) {
		this.practitioner_share_report = practitioner_share_report;
	}

	public boolean isCharges_detaile_report() {
		return charges_detaile_report;
	}

	public void setCharges_detaile_report(boolean charges_detaile_report) {
		this.charges_detaile_report = charges_detaile_report;
	}

	public boolean isVisiting_consultation_report() {
		return visiting_consultation_report;
	}

	public void setVisiting_consultation_report(boolean visiting_consultation_report) {
		this.visiting_consultation_report = visiting_consultation_report;
	}

	public boolean isOpd_ipd_report() {
		return opd_ipd_report;
	}

	public void setOpd_ipd_report(boolean opd_ipd_report) {
		this.opd_ipd_report = opd_ipd_report;
	}

	public boolean isDoctor_share_report() {
		return doctor_share_report;
	}

	public void setDoctor_share_report(boolean doctor_share_report) {
		this.doctor_share_report = doctor_share_report;
	}

	public boolean isDeptwise_payment_report() {
		return deptwise_payment_report;
	}

	public void setDeptwise_payment_report(boolean deptwise_payment_report) {
		this.deptwise_payment_report = deptwise_payment_report;
	}

	public boolean isPayment_receipt_report() {
		return payment_receipt_report;
	}

	public void setPayment_receipt_report(boolean payment_receipt_report) {
		this.payment_receipt_report = payment_receipt_report;
	}

	public boolean isPayment_combined_report() {
		return payment_combined_report;
	}

	public void setPayment_combined_report(boolean payment_combined_report) {
		this.payment_combined_report = payment_combined_report;
	}

	public boolean isRefund_report() {
		return refund_report;
	}

	public void setRefund_report(boolean refund_report) {
		this.refund_report = refund_report;
	}


	/** Id of user that corresponds to primary key of database table row */
	private int id;
	
	/** name of user */
	private String userName = null;
	private String actionType;
	/** id of user */
	private String userId = null;
	private boolean appreception,sms_cancel_appointment ;
	private boolean clientprof;
	private boolean inhousepatient;
	private boolean lmh_consultation_charge, lmh_other_charge, lmh_discount_type, lmh_discount, deptOpdReport;
	private String branchName;
	private String puresevaclientid;
	private String show_dept_opd_list;
	private String linkaddress;
	private int userwiseaceess;
	private String dept;
	private boolean opd_user_vid_access;
	private boolean dr_opd_vid;
	private String opdfollowup;
	private boolean wardhazp;
	private boolean opd_recep_sp_list;
	private String acaccess;
	private String loginsessionid;
	private boolean invoice_groupby;
	private boolean invoice_charge_seqno;
	private boolean opd_tp_zero_invoice;
	private int grn_to_prisc_location;
	private boolean opd_video_icon_show;
	private boolean hidecalinpoprint;
	private boolean change_indent_product;
	private boolean max_phar_discount;
	private boolean disc_approve_sms;
	private boolean ot_surgeon_sms;
	private boolean ot_patient_sms;
	private boolean edit_catalogue_name;
	private boolean session_added_med;
	private boolean create_invoice_selected_charges;
	private double opddiscount;
	private double ipddiscount;
	private double pharpatientdiscount;
	private boolean adjustment_approve,edit_approve,multi_edit_product,multi_adjust_approve,edit_product;
	private boolean sms_reg_patient;
	private boolean phar_batchwise_sale;
	private boolean sms_access;
	private boolean isfrompuresevalink;
	private int tokenstatus;
	private boolean vermanh;
	private boolean pprice_sale;
	private boolean prisc_temp_showother;
	private boolean maindash_graph;
	private boolean opd_telemed;
	private boolean freeze_unfreeze;
	private boolean vaci_sms_7day;
	private boolean phar_patient_edit;
	private String sshhostname,sshpassword,sshuser,dbpassword;
	private int port;
	private boolean editpharmacy_bill;
	private String hospital_language;
	private boolean delete_invoice_history;
	private boolean package_investigation;
	private boolean delete_invoice;
	private boolean lmh,australia;
	private String dicipline;
	private boolean kalmegha;
	private boolean lmh_po_discount;
	private boolean smallClinic;
	private boolean isReferHospital;
	private boolean isclinic_use_yuvi;
	private boolean common_clinic_db;
	private String landLine;
	private boolean indent_self_approve,indent_all_approve;
	private boolean common_db_clinic;
	private boolean inventory_report_access;
	private boolean saimed;
	private int smscount;
	private int wsmscount;
	private boolean matrusevasang;
	private String opendb;
	private boolean supplier_access;
	private boolean item_wise_sale_report,patient_consumption_report,payable_aging_report,stock_report;
	private boolean consumption_report,cathlab_stock_report,detail_grn_report,cathlab_opening_closing;
	private boolean opd_tat_report,birthplace_report;
	private boolean total_revenue_report,ward_wise_rev_report,revenue_matrix,admission_discharge,deptwise_revenue_report,consultation_report,bed_occupancy_per_day,render_charges_report;
    private boolean inventory_openclose_report,detailed_inventory_openclose_report,inventory_openclose_catalogue,inventory_stock_report,inventory_sale_report;
    private boolean payment_receive_report,inventory_itemwise_sale,productwise_sale_report,cataloguewise_sale_report,gst_report,grn_gst_report,supplier_payment_report,inventory_report;
    private boolean mrp_access;
    private boolean sale_price_access;
    private boolean gst_access;
	private boolean purchase_price_access;
    private boolean sale_value_access;
    private boolean net_rate_access;
    private boolean time_utility_report;
    private boolean parihar;
    private boolean unit_price;
    private boolean sjivh;
    private boolean totehosp;
    private boolean opd_discount_access;
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

	public boolean isUnit_price() {
		return unit_price;
	}

	public void setUnit_price(boolean unit_price) {
		this.unit_price = unit_price;
	}

	public boolean isParihar() {
		return parihar;
	}

	public void setParihar(boolean parihar) {
		this.parihar = parihar;
	}

	public boolean isTime_utility_report() {
		return time_utility_report;
	}

	public void setTime_utility_report(boolean time_utility_report) {
		this.time_utility_report = time_utility_report;
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

	public boolean isSupplier_access() {
		return supplier_access;
	}

	public boolean isMrp_access() {
		return mrp_access;
	}

	public void setMrp_access(boolean mrp_access) {
		this.mrp_access = mrp_access;
	}

	public void setSupplier_access(boolean supplier_access) {
		this.supplier_access = supplier_access;
	}

	public String getOpendb() {
		return opendb;
	}

	public void setOpendb(String opendb) {
		this.opendb = opendb;
	}

	public boolean isMatrusevasang() {
		return matrusevasang;
	}

	public void setMatrusevasang(boolean matrusevasang) {
		this.matrusevasang = matrusevasang;
	}

	public int getWsmscount() {
		return wsmscount;
	}

	public void setWsmscount(int wsmscount) {
		this.wsmscount = wsmscount;
	}

	public int getSmscount() {
		return smscount;
	}

	public void setSmscount(int smscount) {
		this.smscount = smscount;
	}

	public boolean isSaimed() {
		return saimed;
	}

	public void setSaimed(boolean saimed) {
		this.saimed = saimed;
	}

	public boolean isInventory_report_access() {
		return inventory_report_access;
	}

	public void setInventory_report_access(boolean inventory_report_access) {
		this.inventory_report_access = inventory_report_access;
	}

	public boolean isCommon_db_clinic() {
		return common_db_clinic;
	}

	public void setCommon_db_clinic(boolean common_db_clinic) {
		this.common_db_clinic = common_db_clinic;
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

	public String getLandLine() {
		return landLine;
	}

	public void setLandLine(String landLine) {
		this.landLine = landLine;
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

	public boolean isReferHospital() {
		return isReferHospital;
	}

	public void setReferHospital(boolean isReferHospital) {
		this.isReferHospital = isReferHospital;
	}

	public boolean isSmallClinic() {
		return smallClinic;
	}

	public void setSmallClinic(boolean smallClinic) {
		this.smallClinic = smallClinic;
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

	public String getDicipline() {
		return dicipline;
	}

	public void setDicipline(String dicipline) {
		this.dicipline = dicipline;
	}

	public boolean isAustralia() {
		return australia;
	}

	public void setAustralia(boolean australia) {
		this.australia = australia;
	}

	public String getTelemedPharmacyMail() {
		return telemedPharmacyMail;
	}

	public void setTelemedPharmacyMail(String telemedPharmacyMail) {
		this.telemedPharmacyMail = telemedPharmacyMail;
	}


	private boolean registrationdash;
	private int patient_category;
	private String telemedPharmacyMail;
	
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

	public boolean isLmh() {
		return lmh;
	}

	public void setLmh(boolean lmh) {
		this.lmh = lmh;
	}

	public String getHospital_language() {
		return hospital_language;
	}

	public void setHospital_language(String hospital_language) {
		this.hospital_language = hospital_language;
	}

	public boolean isEditpharmacy_bill() {
		return editpharmacy_bill;
	}

	public void setEditpharmacy_bill(boolean editpharmacy_bill) {
		this.editpharmacy_bill = editpharmacy_bill;
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

	public boolean isPhar_patient_edit() {
		return phar_patient_edit;
	}

	public void setPhar_patient_edit(boolean phar_patient_edit) {
		this.phar_patient_edit = phar_patient_edit;
	}

	public boolean isVaci_sms_7day() {
		return vaci_sms_7day;
	}

	public void setVaci_sms_7day(boolean vaci_sms_7day) {
		this.vaci_sms_7day = vaci_sms_7day;
	}

	public boolean isFreeze_unfreeze() {
		return freeze_unfreeze;
	}

	public void setFreeze_unfreeze(boolean freeze_unfreeze) {
		this.freeze_unfreeze = freeze_unfreeze;
	}

	public boolean isMaindash_graph() {
		return maindash_graph;
	}

	public void setMaindash_graph(boolean maindash_graph) {
		this.maindash_graph = maindash_graph;
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

	public boolean isIsfrompuresevalink() {
		return isfrompuresevalink;
	}

	public void setIsfrompuresevalink(boolean isfrompuresevalink) {
		this.isfrompuresevalink = isfrompuresevalink;
	}

	public boolean isPhar_batchwise_sale() {
		return phar_batchwise_sale;
	}

	public boolean isSms_access() {
		return sms_access;
	}

	public void setSms_access(boolean sms_access) {
		this.sms_access = sms_access;
	}

	public void setPhar_batchwise_sale(boolean phar_batchwise_sale) {
		this.phar_batchwise_sale = phar_batchwise_sale;
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

	public boolean isSession_added_med() {
		return session_added_med;
	}

	public void setSession_added_med(boolean session_added_med) {
		this.session_added_med = session_added_med;
	}

	public boolean isEdit_catalogue_name() {
		return edit_catalogue_name;
	}

	public void setEdit_catalogue_name(boolean edit_catalogue_name) {
		this.edit_catalogue_name = edit_catalogue_name;
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

	public int getGrn_to_prisc_location() {
		return grn_to_prisc_location;
	}

	public void setGrn_to_prisc_location(int grn_to_prisc_location) {
		this.grn_to_prisc_location = grn_to_prisc_location;
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


	private int outoprisc;
	
	private int iskunal;
	private boolean invoice_date_modify;
	private boolean package_access;
	
	private int wardforcharge;
	private boolean prisc_location_list,prisc_print;
	private boolean direct_prisc;
	private boolean prachi_clinic;
	private boolean indv_discount;
	private boolean prisc_new_req_access;
	
	private boolean invoicemodify;
	private boolean medicine_barcode;
	private boolean additional_disc;
	private boolean opening_closeing_on;
	private String opening_locations;
	private boolean cancel_invoice_new;
	private boolean payrollaccess;
	private boolean proc_after_stock;
	private boolean phar_print_seq;
	private String sms_senderid;
	private int superadminid;
	
	public int getSuperadminid() {
		return superadminid;
	}

	public void setSuperadminid(int superadminid) {
		this.superadminid = superadminid;
	}

	public String getSms_senderid() {
		return sms_senderid;
	}

	public void setSms_senderid(String sms_senderid) {
		this.sms_senderid = sms_senderid;
	}

	public boolean isPhar_print_seq() {
		return phar_print_seq;
	}

	public void setPhar_print_seq(boolean phar_print_seq) {
		this.phar_print_seq = phar_print_seq;
	}

	public boolean isProc_after_stock() {
		return proc_after_stock;
	}

	public void setProc_after_stock(boolean proc_after_stock) {
		this.proc_after_stock = proc_after_stock;
	}

	public boolean isCancel_invoice_new() {
		return cancel_invoice_new;
	}

	public void setCancel_invoice_new(boolean cancel_invoice_new) {
		this.cancel_invoice_new = cancel_invoice_new;
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

	public boolean isAdditional_disc() {
		return additional_disc;
	}

	public void setAdditional_disc(boolean additional_disc) {
		this.additional_disc = additional_disc;
	}

	public boolean isMedicine_barcode() {
		return medicine_barcode;
	}

	public void setMedicine_barcode(boolean medicine_barcode) {
		this.medicine_barcode = medicine_barcode;
	}

	public boolean isPrisc_new_req_access() {
		return prisc_new_req_access;
	}

	public void setPrisc_new_req_access(boolean prisc_new_req_access) {
		this.prisc_new_req_access = prisc_new_req_access;
	}

	public boolean isPrachi_clinic() {
		return prachi_clinic;
	}

	public void setPrachi_clinic(boolean prachi_clinic) {
		this.prachi_clinic = prachi_clinic;
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

	public int getIskunal() {
		return iskunal;
	}

	public void setIskunal(int iskunal) {
		this.iskunal = iskunal;
	}

	public int getWardforcharge() {
		return wardforcharge;
	}

	public void setWardforcharge(int wardforcharge) {
		this.wardforcharge = wardforcharge;
	}

	public int getOutoprisc() {
		return outoprisc;
	}

	public void setOutoprisc(int outoprisc) {
		this.outoprisc = outoprisc;
	}

	public String getLoginsessionid() {
		return loginsessionid;
	}

	public void setLoginsessionid(String loginsessionid) {
		this.loginsessionid = loginsessionid;
	}


	private int userType;
	private boolean edit_purchaseorder;
	private boolean delete_purchaseorder;
	
	private String firstName;
	
	private String lastName;
	
	private String clinicOwner;
	private int pharmacyUserType;
	private String dbName;
	
	private String clinicName = "";
	
	private String timeZone = "";
	
	private int clinicid;
	private String clinicid1;
	
	public String getClinicid1() {
		return clinicid1;
	}

	public void setClinicid1(String clinicid1) {
		this.clinicid1 = clinicid1;
	}


	private String clinicUserid;
	
	private String commencing;
	
	private String title;
	
	private String diaryUserid;
	
	private String email;
	
	private String mob;
	
	private String dob;
	
	private String gender;
	
	private String clientOtp;
	
	private int isotpconfirmd = 0;
	
	private String country;
	
	private String clinicAddress;
	
	private String regAddress = "";
	
	private String regLocation = "";
	
	private String regContactNo = "";
	
	private String regPinCode = "";
	
	private String loginType = "";
	
	private int clinicStartTime;
	
	private String jobTitle;
	
	private boolean manageopd;
	
	private boolean apmtfinder;
	 
	 private boolean manageemr;
	 
	 private boolean expences;
	 
	 private boolean managemis;
	 
	 private String loc;
	 
	 private boolean payroll;
	 private boolean bloodbak;
	 private boolean inventory;
	 private boolean discharge;
	 
	 private boolean packs;
	 private boolean investigation_chart;
	 private boolean sheduler;
	 private boolean housekeeping;
	 private boolean dietery;
	 private boolean cafeteria;
	 private boolean packages;
	 private boolean ambulance;
	 private boolean bank_deposite;
	 private boolean account_reconcilation;  
	 private int dbsize;
	 private String islogo;
	 
	//new terms added for personal widget
		private boolean ot;
		private boolean casualty;
		private boolean pharmacy;
		private boolean mrd;
		private boolean marketing;
		private boolean voice_recording;
	
		//pharmacy setting user setting term
		private boolean islogin;
		private boolean sale_bill;
		private boolean disscount;
		private boolean ledger;
		private boolean account;
		private boolean purchase_order;
		private boolean SMS_authority;
		private boolean edit_bill;
		private boolean return_medicine;
	    public boolean isReturn_medicine() {
			return return_medicine;
		}

		public void setReturn_medicine(boolean return_medicine) {
			this.return_medicine = return_medicine;
		}


		private boolean delete_bill;
	    
	    private boolean opd_addcharges;
		private boolean opd_createinvoice;
		private boolean opd_recordpayment;
		private boolean procurementType;
		
		
	    
//opd access variables
		
		private boolean opd_modify;
		private boolean opd_cancel;
		private boolean opd_prescription;
		private boolean opd_investigation;
		private boolean opd_ot;
		private boolean opd_viewaccount;
		private boolean opd_apmtfinder;
		private boolean opd_advandref;
		private boolean opd_modifydiagnosis;
		private boolean opd_editpatient;
		private boolean opd_log;
		private boolean opd_emr;
		private boolean opd_assessmentform;
		private boolean opd_treatment;
	    
		

		
//ipd access variables
		
		
		private boolean ipd_admission;
		private boolean ipd_declaration;
		private boolean ipd_log;
		private boolean ipd_forms;
		private boolean ipd_discharge;
		private boolean ipd_emr;
		private boolean ipd_prescription;
		private boolean ipd_investigation;
		private boolean ipd_reqconsultant;
		private boolean ipd_nursingcare;
		private boolean ipd_reqblood;
		private boolean ipd_autocare;
		private boolean ipd_treatmentlog;
		private boolean ipd_addcharges;
		private boolean ipd_createinvoice;
		private boolean ipd_recordpayment;
		private boolean ipd_advandref;
		private boolean ipd_viewaccount;
		private boolean ipd_packages;
		
		
		private boolean acc_createinvoice;
		private boolean acc_recordpayment;
		private boolean acc_viewcreditaccount;
		private boolean acc_advandref;
		private boolean acc_chargeinvoice;
		private boolean acc_addcharges;
		private boolean acc_chargedetails;
		
		private boolean emr_docs;
		private boolean emr_history;
		private boolean emr_medicine;
		private boolean emr_investigation;
		private boolean emr_pacs;
		private boolean emr_media;
		private boolean emr_update;
		private boolean emr_print;
		private boolean emr_edit;
		private boolean emr_delete;
		
		private boolean client_add;
		private boolean client_edit;
		private boolean client_delete;
		private boolean client_forms;
		private boolean client_discharge;
		private boolean client_emai;
		private boolean client_print;
		private boolean client_treatment;
		private boolean client_log;
		private boolean client_recordpayment;
		private boolean client_cashdesk;
		private boolean clientadvandref;
		private boolean client_addcharge;
		private boolean client_viewaccount;
		private boolean client_emr;
		private boolean indent;
		private boolean investigation_approve;
		private boolean daily_opd;
		private boolean indent_approve;
		private boolean cash_desk;
		private boolean vaccination;
		private boolean video_training;
		private boolean setup_master;
		private boolean sale_pharmacy;
		public boolean isVaccination() {
			return vaccination;
		}

		public void setVaccination(boolean vaccination) {
			this.vaccination = vaccination;
		}

		public boolean isVideo_training() {
			return video_training;
		}

		public void setVideo_training(boolean video_training) {
			this.video_training = video_training;
		}

		public boolean isSetup_master() {
			return setup_master;
		}

		public void setSetup_master(boolean setup_master) {
			this.setup_master = setup_master;
		}

		public boolean isSale_pharmacy() {
			return sale_pharmacy;
		}

		public void setSale_pharmacy(boolean sale_pharmacy) {
			this.sale_pharmacy = sale_pharmacy;
		}


		private boolean tpa;
		private boolean nabh_quality;
		private boolean doctor_opd;
		private boolean refund;
		
		private boolean showdiscount;
		private boolean refund_dashboard;
		private boolean showinvestigation;
		
		private String uhid;
		private boolean edit_invst_charge;
		private boolean medtreatgiven;
		
		
		private String pacsip;
		private boolean invst_collect;
		private boolean approve_po;
		private boolean cancel_admsn;
		private boolean stock_log;
		
		private double creditlimit=0;
		private boolean creditlimitaccess;
		private boolean edit_paypo;
		private boolean isledgerhosp;
		private boolean adjustmentaccess;
		private boolean direct_refund_disc;
		private boolean supplier_add;
		private boolean prisc_deliver_return;
		private boolean auto_generic_name;
		private boolean ref_dis_pay;
		private boolean refund_report;
		private boolean payment_combined_report;
		private boolean payment_receipt_report;
		private boolean deptwise_payment_report;
		private boolean doctor_share_report;
		private boolean opd_ipd_report;
		private boolean visiting_consultation_report;
		private boolean charges_detaile_report;
		private boolean practitioner_share_report;
		private boolean out_source_report;
		private boolean lab_report;
		private boolean investigation_count_report;
		private boolean investigation_package_report;
		private boolean invstwise_count_report,invst_criticalval_report,invst_tat_report,invst_opdipd_report,invst_revcount_namewise,investigation_revenue_report;
		private boolean sramhosp;
	public boolean isRef_dis_pay() {
			return ref_dis_pay;
		}

		public void setRef_dis_pay(boolean ref_dis_pay) {
			this.ref_dis_pay = ref_dis_pay;
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

	public boolean isSupplier_add() {
			return supplier_add;
		}

		public void setSupplier_add(boolean supplier_add) {
			this.supplier_add = supplier_add;
		}

	public boolean isDirect_refund_disc() {
			return direct_refund_disc;
		}

		public void setDirect_refund_disc(boolean direct_refund_disc) {
			this.direct_refund_disc = direct_refund_disc;
		}

	public boolean isAdjustmentaccess() {
			return adjustmentaccess;
		}

		public void setAdjustmentaccess(boolean adjustmentaccess) {
			this.adjustmentaccess = adjustmentaccess;
		}

	public boolean isIsledgerhosp() {
			return isledgerhosp;
		}

		public void setIsledgerhosp(boolean isledgerhosp) {
			this.isledgerhosp = isledgerhosp;
		}

	public boolean isEdit_paypo() {
			return edit_paypo;
		}

		public void setEdit_paypo(boolean edit_paypo) {
			this.edit_paypo = edit_paypo;
		}

	public double getCreditlimit() {
			return creditlimit;
		}

		public void setCreditlimit(double creditlimit) {
			this.creditlimit = creditlimit;
		}

		public boolean isCreditlimitaccess() {
			return creditlimitaccess;
		}

		public void setCreditlimitaccess(boolean creditlimitaccess) {
			this.creditlimitaccess = creditlimitaccess;
		}

	public boolean isStock_log() {
			return stock_log;
		}

		public void setStock_log(boolean stock_log) {
			this.stock_log = stock_log;
		}

	public boolean isCancel_admsn() {
			return cancel_admsn;
		}

		public void setCancel_admsn(boolean cancel_admsn) {
			this.cancel_admsn = cancel_admsn;
		}

	public boolean isApprove_po() {
			return approve_po;
		}

		public void setApprove_po(boolean approve_po) {
			this.approve_po = approve_po;
		}

	public boolean isInvst_collect() {
			return invst_collect;
		}

		public void setInvst_collect(boolean invst_collect) {
			this.invst_collect = invst_collect;
		}

	public String getPacsip() {
			return pacsip;
		}

		public void setPacsip(String pacsip) {
			this.pacsip = pacsip;
		}

	public boolean isMedtreatgiven() {
			return medtreatgiven;
		}

		public void setMedtreatgiven(boolean medtreatgiven) {
			this.medtreatgiven = medtreatgiven;
		}

	public boolean isEdit_invst_charge() {
			return edit_invst_charge;
		}

		public void setEdit_invst_charge(boolean edit_invst_charge) {
			this.edit_invst_charge = edit_invst_charge;
		}

	public String getUhid() {
			return uhid;
		}

		public void setUhid(String uhid) {
			this.uhid = uhid;
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

	public boolean isShowdiscount() {
			return showdiscount;
		}

		public void setShowdiscount(boolean showdiscount) {
			this.showdiscount = showdiscount;
		}

	public boolean isRefund() {
			return refund;
		}

		public void setRefund(boolean refund) {
			this.refund = refund;
		}

	public boolean isDoctor_opd() {
			return doctor_opd;
		}

		public void setDoctor_opd(boolean doctor_opd) {
			this.doctor_opd = doctor_opd;
		}

	public boolean isTpa() {
			return tpa;
		}

		public void setTpa(boolean tpa) {
			this.tpa = tpa;
		}

		public boolean isNabh_quality() {
			return nabh_quality;
		}

		public void setNabh_quality(boolean nabh_quality) {
			this.nabh_quality = nabh_quality;
		}

	public boolean isIndent_approve() {
			return indent_approve;
		}

		public void setIndent_approve(boolean indent_approve) {
			this.indent_approve = indent_approve;
		}

		public boolean isCash_desk() {
			return cash_desk;
		}

		public void setCash_desk(boolean cash_desk) {
			this.cash_desk = cash_desk;
		}

	public boolean isDaily_opd() {
			return daily_opd;
		}

		public void setDaily_opd(boolean daily_opd) {
			this.daily_opd = daily_opd;
		}

	public boolean isInvestigation_approve() {
			return investigation_approve;
		}

		public void setInvestigation_approve(boolean investigation_approve) {
			this.investigation_approve = investigation_approve;
		}

	public boolean isIndent() {
			return indent;
		}

		public void setIndent(boolean indent) {
			this.indent = indent;
		}

	public boolean isClient_add() {
			return client_add;
		}

		public void setClient_add(boolean client_add) {
			this.client_add = client_add;
		}

		public boolean isClient_edit() {
			return client_edit;
		}

		public void setClient_edit(boolean client_edit) {
			this.client_edit = client_edit;
		}

		public boolean isClient_delete() {
			return client_delete;
		}

		public void setClient_delete(boolean client_delete) {
			this.client_delete = client_delete;
		}

		public boolean isClient_forms() {
			return client_forms;
		}

		public void setClient_forms(boolean client_forms) {
			this.client_forms = client_forms;
		}

		public boolean isClient_discharge() {
			return client_discharge;
		}

		public void setClient_discharge(boolean client_discharge) {
			this.client_discharge = client_discharge;
		}

		public boolean isClient_emai() {
			return client_emai;
		}

		public void setClient_emai(boolean client_emai) {
			this.client_emai = client_emai;
		}

		public boolean isClient_print() {
			return client_print;
		}

		public void setClient_print(boolean client_print) {
			this.client_print = client_print;
		}

		public boolean isClient_treatment() {
			return client_treatment;
		}

		public void setClient_treatment(boolean client_treatment) {
			this.client_treatment = client_treatment;
		}

		public boolean isClient_log() {
			return client_log;
		}

		public void setClient_log(boolean client_log) {
			this.client_log = client_log;
		}

		public boolean isClient_recordpayment() {
			return client_recordpayment;
		}

		public void setClient_recordpayment(boolean client_recordpayment) {
			this.client_recordpayment = client_recordpayment;
		}

		public boolean isClient_cashdesk() {
			return client_cashdesk;
		}

		public void setClient_cashdesk(boolean client_cashdesk) {
			this.client_cashdesk = client_cashdesk;
		}

		public boolean isClientadvandref() {
			return clientadvandref;
		}

		public void setClientadvandref(boolean clientadvandref) {
			this.clientadvandref = clientadvandref;
		}

		public boolean isClient_addcharge() {
			return client_addcharge;
		}

		public void setClient_addcharge(boolean client_addcharge) {
			this.client_addcharge = client_addcharge;
		}

		public boolean isClient_viewaccount() {
			return client_viewaccount;
		}

		public void setClient_viewaccount(boolean client_viewaccount) {
			this.client_viewaccount = client_viewaccount;
		}

		public boolean isClient_emr() {
			return client_emr;
		}

		public void setClient_emr(boolean client_emr) {
			this.client_emr = client_emr;
		}

	public boolean isEmr_docs() {
			return emr_docs;
		}

		public void setEmr_docs(boolean emr_docs) {
			this.emr_docs = emr_docs;
		}

		public boolean isEmr_history() {
			return emr_history;
		}

		public void setEmr_history(boolean emr_history) {
			this.emr_history = emr_history;
		}

		public boolean isEmr_medicine() {
			return emr_medicine;
		}

		public void setEmr_medicine(boolean emr_medicine) {
			this.emr_medicine = emr_medicine;
		}

		public boolean isEmr_investigation() {
			return emr_investigation;
		}

		public void setEmr_investigation(boolean emr_investigation) {
			this.emr_investigation = emr_investigation;
		}

		public boolean isEmr_pacs() {
			return emr_pacs;
		}

		public void setEmr_pacs(boolean emr_pacs) {
			this.emr_pacs = emr_pacs;
		}

		public boolean isEmr_media() {
			return emr_media;
		}

		public void setEmr_media(boolean emr_media) {
			this.emr_media = emr_media;
		}

		public boolean isEmr_update() {
			return emr_update;
		}

		public void setEmr_update(boolean emr_update) {
			this.emr_update = emr_update;
		}

		public boolean isEmr_print() {
			return emr_print;
		}

		public void setEmr_print(boolean emr_print) {
			this.emr_print = emr_print;
		}

		public boolean isEmr_edit() {
			return emr_edit;
		}

		public void setEmr_edit(boolean emr_edit) {
			this.emr_edit = emr_edit;
		}

		public boolean isEmr_delete() {
			return emr_delete;
		}

		public void setEmr_delete(boolean emr_delete) {
			this.emr_delete = emr_delete;
		}

	public boolean isAcc_createinvoice() {
			return acc_createinvoice;
		}

		public void setAcc_createinvoice(boolean acc_createinvoice) {
			this.acc_createinvoice = acc_createinvoice;
		}

		public boolean isAcc_recordpayment() {
			return acc_recordpayment;
		}

		public void setAcc_recordpayment(boolean acc_recordpayment) {
			this.acc_recordpayment = acc_recordpayment;
		}

		public boolean isAcc_viewcreditaccount() {
			return acc_viewcreditaccount;
		}

		public void setAcc_viewcreditaccount(boolean acc_viewcreditaccount) {
			this.acc_viewcreditaccount = acc_viewcreditaccount;
		}

		public boolean isAcc_advandref() {
			return acc_advandref;
		}

		public void setAcc_advandref(boolean acc_advandref) {
			this.acc_advandref = acc_advandref;
		}

		public boolean isAcc_chargeinvoice() {
			return acc_chargeinvoice;
		}

		public void setAcc_chargeinvoice(boolean acc_chargeinvoice) {
			this.acc_chargeinvoice = acc_chargeinvoice;
		}

		public boolean isAcc_addcharges() {
			return acc_addcharges;
		}

		public void setAcc_addcharges(boolean acc_addcharges) {
			this.acc_addcharges = acc_addcharges;
		}

		public boolean isAcc_chargedetails() {
			return acc_chargedetails;
		}

		public void setAcc_chargedetails(boolean acc_chargedetails) {
			this.acc_chargedetails = acc_chargedetails;
		}

	public boolean isOpd_modify() {
			return opd_modify;
		}

		public void setOpd_modify(boolean opd_modify) {
			this.opd_modify = opd_modify;
		}

		public boolean isOpd_cancel() {
			return opd_cancel;
		}

		public void setOpd_cancel(boolean opd_cancel) {
			this.opd_cancel = opd_cancel;
		}

		public boolean isOpd_prescription() {
			return opd_prescription;
		}

		public void setOpd_prescription(boolean opd_prescription) {
			this.opd_prescription = opd_prescription;
		}

		public boolean isOpd_investigation() {
			return opd_investigation;
		}

		public void setOpd_investigation(boolean opd_investigation) {
			this.opd_investigation = opd_investigation;
		}

		public boolean isOpd_ot() {
			return opd_ot;
		}

		public void setOpd_ot(boolean opd_ot) {
			this.opd_ot = opd_ot;
		}

		public boolean isOpd_viewaccount() {
			return opd_viewaccount;
		}

		public void setOpd_viewaccount(boolean opd_viewaccount) {
			this.opd_viewaccount = opd_viewaccount;
		}

		public boolean isOpd_apmtfinder() {
			return opd_apmtfinder;
		}

		public void setOpd_apmtfinder(boolean opd_apmtfinder) {
			this.opd_apmtfinder = opd_apmtfinder;
		}

		public boolean isOpd_advandref() {
			return opd_advandref;
		}

		public void setOpd_advandref(boolean opd_advandref) {
			this.opd_advandref = opd_advandref;
		}

		public boolean isOpd_modifydiagnosis() {
			return opd_modifydiagnosis;
		}

		public void setOpd_modifydiagnosis(boolean opd_modifydiagnosis) {
			this.opd_modifydiagnosis = opd_modifydiagnosis;
		}

		public boolean isOpd_editpatient() {
			return opd_editpatient;
		}

		public void setOpd_editpatient(boolean opd_editpatient) {
			this.opd_editpatient = opd_editpatient;
		}

		public boolean isOpd_log() {
			return opd_log;
		}

		public void setOpd_log(boolean opd_log) {
			this.opd_log = opd_log;
		}

		public boolean isOpd_emr() {
			return opd_emr;
		}

		public void setOpd_emr(boolean opd_emr) {
			this.opd_emr = opd_emr;
		}

		public boolean isOpd_assessmentform() {
			return opd_assessmentform;
		}

		public void setOpd_assessmentform(boolean opd_assessmentform) {
			this.opd_assessmentform = opd_assessmentform;
		}

		public boolean isOpd_treatment() {
			return opd_treatment;
		}

		public void setOpd_treatment(boolean opd_treatment) {
			this.opd_treatment = opd_treatment;
		}

	public boolean isDelete_bill() {
			return delete_bill;
		}

		public void setDelete_bill(boolean delete_bill) {
			this.delete_bill = delete_bill;
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

	public boolean isPacks() {
		return packs;
	}

	public void setPacks(boolean packs) {
		this.packs = packs;
	}

	public boolean isInvestigation_chart() {
		return investigation_chart;
	}

	public void setInvestigation_chart(boolean investigation_chart) {
		this.investigation_chart = investigation_chart;
	}

	public boolean isSheduler() {
		return sheduler;
	}

	public void setSheduler(boolean sheduler) {
		this.sheduler = sheduler;
	}

	public boolean isHousekeeping() {
		return housekeeping;
	}

	public void setHousekeeping(boolean housekeeping) {
		this.housekeeping = housekeeping;
	}

	public boolean isDietery() {
		return dietery;
	}

	public void setDietery(boolean dietery) {
		this.dietery = dietery;
	}

	public boolean isCafeteria() {
		return cafeteria;
	}

	public void setCafeteria(boolean cafeteria) {
		this.cafeteria = cafeteria;
	}

	public boolean isPackages() {
		return packages;
	}

	public void setPackages(boolean packages) {
		this.packages = packages;
	}

	public boolean isAmbulance() {
		return ambulance;
	}

	public void setAmbulance(boolean ambulance) {
		this.ambulance = ambulance;
	}

	public boolean isBank_deposite() {
		return bank_deposite;
	}

	public void setBank_deposite(boolean bank_deposite) {
		this.bank_deposite = bank_deposite;
	}

	public boolean isAccount_reconcilation() {
		return account_reconcilation;
	}

	public void setAccount_reconcilation(boolean account_reconcilation) {
		this.account_reconcilation = account_reconcilation;
	}

	public boolean isPayroll() {
		return payroll;
	}

	public void setPayroll(boolean payroll) {
		this.payroll = payroll;
	}

	public boolean isBloodbak() {
		return bloodbak;
	}

	public void setBloodbak(boolean bloodbak) {
		this.bloodbak = bloodbak;
	}

	public boolean isInventory() {
		return inventory;
	}

	public void setInventory(boolean inventory) {
		this.inventory = inventory;
	}

	public boolean isDischarge() {
		return discharge;
	}

	public void setDischarge(boolean discharge) {
		this.discharge = discharge;
	}

	public boolean isManagemis() {
		return managemis;
	}

	public void setManagemis(boolean managemis) {
		this.managemis = managemis;
	}

	public boolean isExpences() {
		return expences;
	}

	public void setExpences(boolean expences) {
		this.expences = expences;
	}

	public boolean isManageemr() {
		return manageemr;
	}

	public void setManageemr(boolean manageemr) {
		this.manageemr = manageemr;
	}

	public boolean isApmtfinder() {
		return apmtfinder;
	}

	public void setApmtfinder(boolean apmtfinder) {
		this.apmtfinder = apmtfinder;
	}

	public boolean isManageopd() {
		return manageopd;
	}

	public void setManageopd(boolean manageopd) {
		this.manageopd = manageopd;
	}

	public boolean isIOS() {
		return iOS;
	}

	public void setIOS(boolean ios) {
		iOS = ios;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getClinicLogo() {
		return clinicLogo;
	}

	public void setClinicLogo(String clinicLogo) {
		this.clinicLogo = clinicLogo;
	}


	private int clinicEndTime;
	
	private String clinicLogo;
	
	
	
	

	public int getClinicStartTime() {
		return clinicStartTime;
	}

	public void setClinicStartTime(int clinicStartTime) {
		this.clinicStartTime = clinicStartTime;
	}

	public int getClinicEndTime() {
		return clinicEndTime;
	}

	public void setClinicEndTime(int clinicEndTime) {
		this.clinicEndTime = clinicEndTime;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	public String getRegAddress() {
		return regAddress;
	}

	public void setRegAddress(String regAddress) {
		this.regAddress = regAddress;
	}

	public String getRegLocation() {
		return regLocation;
	}

	public void setRegLocation(String regLocation) {
		this.regLocation = regLocation;
	}

	public String getRegContactNo() {
		return regContactNo;
	}

	public void setRegContactNo(String regContactNo) {
		this.regContactNo = regContactNo;
	}

	public String getRegPinCode() {
		return regPinCode;
	}

	public void setRegPinCode(String regPinCode) {
		this.regPinCode = regPinCode;
	}

	public String getClinicAddress() {
		return clinicAddress;
	}

	public void setClinicAddress(String clinicAddress) {
		this.clinicAddress = clinicAddress;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getIsotpconfirmd() {
		return isotpconfirmd;
	}

	public void setIsotpconfirmd(int isotpconfirmd) {
		this.isotpconfirmd = isotpconfirmd;
	}

	public String getClientOtp() {
		return clientOtp;
	}

	public void setClientOtp(String clientOtp) {
		this.clientOtp = clientOtp;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCommencing() {
		return commencing;
	}

	public void setCommencing(String commencing) {
		this.commencing = commencing;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDiaryUserid() {
		return diaryUserid;
	}

	public void setDiaryUserid(String diaryUserid) {
		this.diaryUserid = diaryUserid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMob() {
		return mob;
	}

	public void setMob(String mob) {
		this.mob = mob;
	}

	public String getClinicUserid() {
		return clinicUserid;
	}

	public void setClinicUserid(String clinicUserid) {
		this.clinicUserid = clinicUserid;
	}

	public int getClinicid() {
		return clinicid;
	}

	public void setClinicid(int clinicid) {
		this.clinicid = clinicid;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getClinicName() {
		return clinicName;
	}

	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
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

	public String getClinicOwner() {
		return clinicOwner;
	}

	public void setClinicOwner(String clinicOwner) {
		this.clinicOwner = clinicOwner;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	
    /**
     * @return the userId
     */
    public String getUserName() {
    	return userName;
    }
	
    /**
     * @param userId the userId to set
     */
    public void setUserName(String userName) {
    	this.userName = userName;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
    	return userId;
    }
	
    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
    	this.userId = userId;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	private String emailConfigureId;
	
	private String emailUserName;
	
	private String emailPassword;
	
	private String emailConfirmPassword;
	
	private String emailHostName;
	private String ipdphysio;
	
	public String getIpdphysio() {
		return ipdphysio;
	}

	public void setIpdphysio(String ipdphysio) {
		this.ipdphysio = ipdphysio;
	}

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
	
	//Menu Settings
		private boolean DiaryManagement;
		
		public boolean isDiaryManagement() {
			return DiaryManagement;
		}



		public void setDiaryManagement(boolean diaryManagement) {
			DiaryManagement = diaryManagement;
		}



		public boolean isAppointmentBooking() {
			return AppointmentBooking;
		}



		public void setAppointmentBooking(boolean appointmentBooking) {
			AppointmentBooking = appointmentBooking;
		}



		public boolean isBasicFinance() {
			return BasicFinance;
		}



		public void setBasicFinance(boolean basicFinance) {
			BasicFinance = basicFinance;
		}



		public boolean isFullFinance() {
			return fullFinance;
		}



		public void setFullFinance(boolean fullFinance) {
			this.fullFinance = fullFinance;
		}



		public boolean isMedicalRecord() {
			return medicalRecord;
		}



		public void setMedicalRecord(boolean medicalRecord) {
			this.medicalRecord = medicalRecord;
		}



		public boolean isClinicResourceMngment() {
			return clinicResourceMngment;
		}



		public void setClinicResourceMngment(boolean clinicResourceMngment) {
			this.clinicResourceMngment = clinicResourceMngment;
		}



		public boolean isClinicPayrollMngment() {
			return clinicPayrollMngment;
		}



		public void setClinicPayrollMngment(boolean clinicPayrollMngment) {
			this.clinicPayrollMngment = clinicPayrollMngment;
		}

		private boolean AppointmentBooking;
		
		private boolean BasicFinance;
		
		private boolean fullFinance;
		
		private boolean medicalRecord;
		
		private boolean clinicResourceMngment;
		
		private boolean clinicPayrollMngment;
		
		private boolean communication;
		
		private boolean report;
		
		private boolean assessmentForms;
		
		private boolean desktop;
			
		private boolean iOS;
		
		private boolean mobile;
		
		private boolean tablet;
		
		private boolean manageclient;
		private boolean manageclinic;
		private boolean managemaster;
		private boolean manageprisc;
		private boolean manageinvst;
		private boolean manageipd;
		
		
		
		


		public boolean isManageclient() {
			return manageclient;
		}

		public void setManageclient(boolean manageclient) {
			this.manageclient = manageclient;
		}

		public boolean isManageclinic() {
			return manageclinic;
		}

		public void setManageclinic(boolean manageclinic) {
			this.manageclinic = manageclinic;
		}

		public boolean isManagemaster() {
			return managemaster;
		}

		public void setManagemaster(boolean managemaster) {
			this.managemaster = managemaster;
		}

		public boolean isManageprisc() {
			return manageprisc;
		}

		public void setManageprisc(boolean manageprisc) {
			this.manageprisc = manageprisc;
		}

		public boolean isManageinvst() {
			return manageinvst;
		}

		public void setManageinvst(boolean manageinvst) {
			this.manageinvst = manageinvst;
		}

		public boolean isManageipd() {
			return manageipd;
		}

		public void setManageipd(boolean manageipd) {
			this.manageipd = manageipd;
		}

		public boolean isCommunication() {
			return communication;
		}



		public void setCommunication(boolean communication) {
			this.communication = communication;
		}



		public boolean isReport() {
			return report;
		}



		public void setReport(boolean report) {
			this.report = report;
		}



		public boolean isAssessmentForms() {
			return assessmentForms;
		}



		public void setAssessmentForms(boolean assessmentForms) {
			this.assessmentForms = assessmentForms;
		}



		public boolean isDesktop() {
			return desktop;
		}



		public void setDesktop(boolean desktop) {
			this.desktop = desktop;
		}



		public boolean isiOS() {
			return iOS;
		}



		public void setiOS(boolean iOS) {
			this.iOS = iOS;
		}



		public boolean isMobile() {
			return mobile;
		}



		public void setMobile(boolean mobile) {
			this.mobile = mobile;
		}



		public boolean isTablet() {
			return tablet;
		}



		public void setTablet(boolean tablet) {
			this.tablet = tablet;
		}

		public boolean isOt() {
			return ot;
		}

		public void setOt(boolean ot) {
			this.ot = ot;
		}

		public boolean isCasualty() {
			return casualty;
		}

		public void setCasualty(boolean casualty) {
			this.casualty = casualty;
		}

		public boolean isPharmacy() {
			return pharmacy;
		}

		public void setPharmacy(boolean pharmacy) {
			this.pharmacy = pharmacy;
		}

		public boolean isMrd() {
			return mrd;
		}

		public void setMrd(boolean mrd) {
			this.mrd = mrd;
		}

		public boolean isMarketing() {
			return marketing;
		}

		public void setMarketing(boolean marketing) {
			this.marketing = marketing;
		}

		public boolean isVoice_recording() {
			return voice_recording;
		}

		public void setVoice_recording(boolean voice_recording) {
			this.voice_recording = voice_recording;
		}

		public boolean isIslogin() {
			return islogin;
		}

		public void setIslogin(boolean islogin) {
			this.islogin = islogin;
		}

		public boolean isSale_bill() {
			return sale_bill;
		}

		public void setSale_bill(boolean sale_bill) {
			this.sale_bill = sale_bill;
		}

		public boolean isDisscount() {
			return disscount;
		}

		public void setDisscount(boolean disscount) {
			this.disscount = disscount;
		}

		public boolean isLedger() {
			return ledger;
		}

		public void setLedger(boolean ledger) {
			this.ledger = ledger;
		}

		public boolean isAccount() {
			return account;
		}

		public void setAccount(boolean account) {
			this.account = account;
		}

		public boolean isPurchase_order() {
			return purchase_order;
		}

		public void setPurchase_order(boolean purchase_order) {
			this.purchase_order = purchase_order;
		}

		public String getLoc() {
			return loc;
		}

		public void setLoc(String loc) {
			this.loc = loc;
		}

		public boolean isSMS_authority() {
			return SMS_authority;
		}

		public void setSMS_authority(boolean sms_authority) {
			SMS_authority = sms_authority;
		}

		public boolean isEdit_bill() {
			return edit_bill;
		}

		public void setEdit_bill(boolean edit_bill) {
			this.edit_bill = edit_bill;
		}

		public boolean isOpd_addcharges() {
			return opd_addcharges;
		}

		public void setOpd_addcharges(boolean opd_addcharges) {
			this.opd_addcharges = opd_addcharges;
		}

		public boolean isOpd_createinvoice() {
			return opd_createinvoice;
		}

		public void setOpd_createinvoice(boolean opd_createinvoice) {
			this.opd_createinvoice = opd_createinvoice;
		}

		public boolean isOpd_recordpayment() {
			return opd_recordpayment;
		}

		public void setOpd_recordpayment(boolean opd_recordpayment) {
			this.opd_recordpayment = opd_recordpayment;
		}

		public boolean isIpd_admission() {
			return ipd_admission;
		}

		public void setIpd_admission(boolean ipd_admission) {
			this.ipd_admission = ipd_admission;
		}

		public boolean isIpd_declaration() {
			return ipd_declaration;
		}

		public void setIpd_declaration(boolean ipd_declaration) {
			this.ipd_declaration = ipd_declaration;
		}

		public boolean isIpd_log() {
			return ipd_log;
		}

		public void setIpd_log(boolean ipd_log) {
			this.ipd_log = ipd_log;
		}

		public boolean isIpd_forms() {
			return ipd_forms;
		}

		public void setIpd_forms(boolean ipd_forms) {
			this.ipd_forms = ipd_forms;
		}

		public boolean isIpd_discharge() {
			return ipd_discharge;
		}

		public void setIpd_discharge(boolean ipd_discharge) {
			this.ipd_discharge = ipd_discharge;
		}

		public boolean isIpd_emr() {
			return ipd_emr;
		}

		public void setIpd_emr(boolean ipd_emr) {
			this.ipd_emr = ipd_emr;
		}

		public boolean isIpd_prescription() {
			return ipd_prescription;
		}

		public void setIpd_prescription(boolean ipd_prescription) {
			this.ipd_prescription = ipd_prescription;
		}

		public boolean isIpd_investigation() {
			return ipd_investigation;
		}

		public void setIpd_investigation(boolean ipd_investigation) {
			this.ipd_investigation = ipd_investigation;
		}

		public boolean isIpd_reqconsultant() {
			return ipd_reqconsultant;
		}

		public void setIpd_reqconsultant(boolean ipd_reqconsultant) {
			this.ipd_reqconsultant = ipd_reqconsultant;
		}

		public boolean isIpd_nursingcare() {
			return ipd_nursingcare;
		}

		public void setIpd_nursingcare(boolean ipd_nursingcare) {
			this.ipd_nursingcare = ipd_nursingcare;
		}

		public boolean isIpd_reqblood() {
			return ipd_reqblood;
		}

		public void setIpd_reqblood(boolean ipd_reqblood) {
			this.ipd_reqblood = ipd_reqblood;
		}

		public boolean isIpd_autocare() {
			return ipd_autocare;
		}

		public void setIpd_autocare(boolean ipd_autocare) {
			this.ipd_autocare = ipd_autocare;
		}

		public boolean isIpd_treatmentlog() {
			return ipd_treatmentlog;
		}

		public void setIpd_treatmentlog(boolean ipd_treatmentlog) {
			this.ipd_treatmentlog = ipd_treatmentlog;
		}

		public boolean isIpd_addcharges() {
			return ipd_addcharges;
		}

		public void setIpd_addcharges(boolean ipd_addcharges) {
			this.ipd_addcharges = ipd_addcharges;
		}

		public boolean isIpd_createinvoice() {
			return ipd_createinvoice;
		}

		public void setIpd_createinvoice(boolean ipd_createinvoice) {
			this.ipd_createinvoice = ipd_createinvoice;
		}

		public boolean isIpd_recordpayment() {
			return ipd_recordpayment;
		}

		public void setIpd_recordpayment(boolean ipd_recordpayment) {
			this.ipd_recordpayment = ipd_recordpayment;
		}

		public boolean isIpd_advandref() {
			return ipd_advandref;
		}

		public void setIpd_advandref(boolean ipd_advandref) {
			this.ipd_advandref = ipd_advandref;
		}

		public boolean isIpd_viewaccount() {
			return ipd_viewaccount;
		}

		public void setIpd_viewaccount(boolean ipd_viewaccount) {
			this.ipd_viewaccount = ipd_viewaccount;
		}

		public boolean isIpd_packages() {
			return ipd_packages;
		}

		public void setIpd_packages(boolean ipd_packages) {
			this.ipd_packages = ipd_packages;
		}

		public boolean isEdit_purchaseorder() {
			return edit_purchaseorder;
		}

		public void setEdit_purchaseorder(boolean edit_purchaseorder) {
			this.edit_purchaseorder = edit_purchaseorder;
		}

		public boolean isDelete_purchaseorder() {
			return delete_purchaseorder;
		}

		public void setDelete_purchaseorder(boolean delete_purchaseorder) {
			this.delete_purchaseorder = delete_purchaseorder;
		}

		public boolean isInhousepatient() {
			return inhousepatient;
		}

		public void setInhousepatient(boolean inhousepatient) {
			this.inhousepatient = inhousepatient;
		}

		public boolean isProcurementType() {
			return procurementType;
		}

		public void setProcurementType(boolean procurementType) {
			this.procurementType = procurementType;
		}

		public int getPharmacyUserType() {
			return pharmacyUserType;
		}

		public void setPharmacyUserType(int pharmacyUserType) {
			this.pharmacyUserType = pharmacyUserType;
		}

		public String getAcaccess() {
			return acaccess;
		}

		public void setAcaccess(String acaccess) {
			this.acaccess = acaccess;
		}

		public boolean isCriticalvaluesms() {
			return criticalvaluesms;
		}

		public void setCriticalvaluesms(boolean criticalvaluesms) {
			this.criticalvaluesms = criticalvaluesms;
		}


		private boolean criticalvaluesms;
		private boolean  practioner_share, opd_practioner_share, charges, invoice, payment_report_detailed, payment_report_small, add_debtors, ca, auditors, userwise_payment, deptwise_analysis, charges_share, billing, discount, cancel_invoice, payment, kpi_dashboard, treatment_episode_list, patient_condition_list, dtr_report, patientlist, current_track_with_no_future_ampts, no_appointment_activity_record, dna_with_no_future_appointment, no_activity_record, dna_analysiis, appointment_kept_vs_dna, treatment_state_by_refferal, returning_patients, outcome_discharge, deathreport, current_patient_report, ipd_daily_report, ipd_monthly_report, bed_occupancy_report, reffered_by, mlc, report_outstandng, now_patients, total_patients_seen, dna_outstanding_action, sales_report, payment_recive, Inventory_opening, itemwise_stock, purchase_report, expiry_medicine_report, grn, indent_statement, ipd_daily_discharge, opd_ipd_cancel_refund, ipd_bill_register, service_register_details, cancel_invoice_report, KPI_report;
		public boolean isPractioner_share() {
			return practioner_share;
		}

		public void setPractioner_share(boolean practioner_share) {
			this.practioner_share = practioner_share;
		}

		public boolean isOpd_practioner_share() {
			return opd_practioner_share;
		}

		public void setOpd_practioner_share(boolean opd_practioner_share) {
			this.opd_practioner_share = opd_practioner_share;
		}

		public boolean isCharges() {
			return charges;
		}

		public void setCharges(boolean charges) {
			this.charges = charges;
		}

		public boolean isInvoice() {
			return invoice;
		}

		public void setInvoice(boolean invoice) {
			this.invoice = invoice;
		}

		public boolean isPayment_report_detailed() {
			return payment_report_detailed;
		}

		public void setPayment_report_detailed(boolean payment_report_detailed) {
			this.payment_report_detailed = payment_report_detailed;
		}

		public boolean isPayment_report_small() {
			return payment_report_small;
		}

		public void setPayment_report_small(boolean payment_report_small) {
			this.payment_report_small = payment_report_small;
		}

		public boolean isAdd_debtors() {
			return add_debtors;
		}

		public void setAdd_debtors(boolean add_debtors) {
			this.add_debtors = add_debtors;
		}

		public boolean isCa() {
			return ca;
		}

		public void setCa(boolean ca) {
			this.ca = ca;
		}

		public boolean isAuditors() {
			return auditors;
		}

		public void setAuditors(boolean auditors) {
			this.auditors = auditors;
		}

		public boolean isUserwise_payment() {
			return userwise_payment;
		}

		public void setUserwise_payment(boolean userwise_payment) {
			this.userwise_payment = userwise_payment;
		}

		public boolean isDeptwise_analysis() {
			return deptwise_analysis;
		}

		public void setDeptwise_analysis(boolean deptwise_analysis) {
			this.deptwise_analysis = deptwise_analysis;
		}

		public boolean isCharges_share() {
			return charges_share;
		}

		public void setCharges_share(boolean charges_share) {
			this.charges_share = charges_share;
		}

		public boolean isBilling() {
			return billing;
		}

		public void setBilling(boolean billing) {
			this.billing = billing;
		}

		public boolean isDiscount() {
			return discount;
		}

		public void setDiscount(boolean discount) {
			this.discount = discount;
		}

		public boolean isCancel_invoice() {
			return cancel_invoice;
		}

		public void setCancel_invoice(boolean cancel_invoice) {
			this.cancel_invoice = cancel_invoice;
		}

		public boolean isPayment() {
			return payment;
		}

		public void setPayment(boolean payment) {
			this.payment = payment;
		}

		public boolean isKpi_dashboard() {
			return kpi_dashboard;
		}

		public void setKpi_dashboard(boolean kpi_dashboard) {
			this.kpi_dashboard = kpi_dashboard;
		}

		public boolean isTreatment_episode_list() {
			return treatment_episode_list;
		}

		public void setTreatment_episode_list(boolean treatment_episode_list) {
			this.treatment_episode_list = treatment_episode_list;
		}

		public boolean isPatient_condition_list() {
			return patient_condition_list;
		}

		public void setPatient_condition_list(boolean patient_condition_list) {
			this.patient_condition_list = patient_condition_list;
		}

		public boolean isDtr_report() {
			return dtr_report;
		}

		public void setDtr_report(boolean dtr_report) {
			this.dtr_report = dtr_report;
		}

		public boolean isPatientlist() {
			return patientlist;
		}

		public void setPatientlist(boolean patientlist) {
			this.patientlist = patientlist;
		}

		public boolean isCurrent_track_with_no_future_ampts() {
			return current_track_with_no_future_ampts;
		}

		public void setCurrent_track_with_no_future_ampts(boolean current_track_with_no_future_ampts) {
			this.current_track_with_no_future_ampts = current_track_with_no_future_ampts;
		}

		public boolean isNo_appointment_activity_record() {
			return no_appointment_activity_record;
		}

		public void setNo_appointment_activity_record(boolean no_appointment_activity_record) {
			this.no_appointment_activity_record = no_appointment_activity_record;
		}

		public boolean isDna_with_no_future_appointment() {
			return dna_with_no_future_appointment;
		}

		public void setDna_with_no_future_appointment(boolean dna_with_no_future_appointment) {
			this.dna_with_no_future_appointment = dna_with_no_future_appointment;
		}

		public boolean isNo_activity_record() {
			return no_activity_record;
		}

		public void setNo_activity_record(boolean no_activity_record) {
			this.no_activity_record = no_activity_record;
		}

		public boolean isDna_analysiis() {
			return dna_analysiis;
		}

		public void setDna_analysiis(boolean dna_analysiis) {
			this.dna_analysiis = dna_analysiis;
		}

		public boolean isAppointment_kept_vs_dna() {
			return appointment_kept_vs_dna;
		}

		public void setAppointment_kept_vs_dna(boolean appointment_kept_vs_dna) {
			this.appointment_kept_vs_dna = appointment_kept_vs_dna;
		}

		public boolean isTreatment_state_by_refferal() {
			return treatment_state_by_refferal;
		}

		public void setTreatment_state_by_refferal(boolean treatment_state_by_refferal) {
			this.treatment_state_by_refferal = treatment_state_by_refferal;
		}

		public boolean isReturning_patients() {
			return returning_patients;
		}

		public void setReturning_patients(boolean returning_patients) {
			this.returning_patients = returning_patients;
		}

		public boolean isOutcome_discharge() {
			return outcome_discharge;
		}

		public void setOutcome_discharge(boolean outcome_discharge) {
			this.outcome_discharge = outcome_discharge;
		}

		public boolean isDeathreport() {
			return deathreport;
		}

		public void setDeathreport(boolean deathreport) {
			this.deathreport = deathreport;
		}

		public boolean isCurrent_patient_report() {
			return current_patient_report;
		}

		public void setCurrent_patient_report(boolean current_patient_report) {
			this.current_patient_report = current_patient_report;
		}

		public boolean isIpd_daily_report() {
			return ipd_daily_report;
		}

		public void setIpd_daily_report(boolean ipd_daily_report) {
			this.ipd_daily_report = ipd_daily_report;
		}

		public boolean isIpd_monthly_report() {
			return ipd_monthly_report;
		}

		public void setIpd_monthly_report(boolean ipd_monthly_report) {
			this.ipd_monthly_report = ipd_monthly_report;
		}

		public boolean isBed_occupancy_report() {
			return bed_occupancy_report;
		}

		public void setBed_occupancy_report(boolean bed_occupancy_report) {
			this.bed_occupancy_report = bed_occupancy_report;
		}

		public boolean isReffered_by() {
			return reffered_by;
		}

		public void setReffered_by(boolean reffered_by) {
			this.reffered_by = reffered_by;
		}

		public boolean isMlc() {
			return mlc;
		}

		public void setMlc(boolean mlc) {
			this.mlc = mlc;
		}

		public boolean isReport_outstandng() {
			return report_outstandng;
		}

		public void setReport_outstandng(boolean report_outstandng) {
			this.report_outstandng = report_outstandng;
		}

		public boolean isNow_patients() {
			return now_patients;
		}

		public void setNow_patients(boolean now_patients) {
			this.now_patients = now_patients;
		}

		public boolean isTotal_patients_seen() {
			return total_patients_seen;
		}

		public void setTotal_patients_seen(boolean total_patients_seen) {
			this.total_patients_seen = total_patients_seen;
		}

		public boolean isDna_outstanding_action() {
			return dna_outstanding_action;
		}

		public void setDna_outstanding_action(boolean dna_outstanding_action) {
			this.dna_outstanding_action = dna_outstanding_action;
		}

		public boolean isSales_report() {
			return sales_report;
		}

		public void setSales_report(boolean sales_report) {
			this.sales_report = sales_report;
		}

		public boolean isPayment_recive() {
			return payment_recive;
		}

		public void setPayment_recive(boolean payment_recive) {
			this.payment_recive = payment_recive;
		}

		public boolean isInventory_opening() {
			return Inventory_opening;
		}

		public void setInventory_opening(boolean inventory_opening) {
			Inventory_opening = inventory_opening;
		}

		public boolean isItemwise_stock() {
			return itemwise_stock;
		}

		public void setItemwise_stock(boolean itemwise_stock) {
			this.itemwise_stock = itemwise_stock;
		}

		public boolean isPurchase_report() {
			return purchase_report;
		}

		public void setPurchase_report(boolean purchase_report) {
			this.purchase_report = purchase_report;
		}

		public boolean isExpiry_medicine_report() {
			return expiry_medicine_report;
		}

		public void setExpiry_medicine_report(boolean expiry_medicine_report) {
			this.expiry_medicine_report = expiry_medicine_report;
		}

		public boolean isGrn() {
			return grn;
		}

		public void setGrn(boolean grn) {
			this.grn = grn;
		}

		public boolean isIndent_statement() {
			return indent_statement;
		}

		public void setIndent_statement(boolean indent_statement) {
			this.indent_statement = indent_statement;
		}

		public boolean isIpd_daily_discharge() {
			return ipd_daily_discharge;
		}

		public void setIpd_daily_discharge(boolean ipd_daily_discharge) {
			this.ipd_daily_discharge = ipd_daily_discharge;
		}

		public boolean isOpd_ipd_cancel_refund() {
			return opd_ipd_cancel_refund;
		}

		public void setOpd_ipd_cancel_refund(boolean opd_ipd_cancel_refund) {
			this.opd_ipd_cancel_refund = opd_ipd_cancel_refund;
		}

		public boolean isIpd_bill_register() {
			return ipd_bill_register;
		}

		public void setIpd_bill_register(boolean ipd_bill_register) {
			this.ipd_bill_register = ipd_bill_register;
		}

		public boolean isService_register_details() {
			return service_register_details;
		}

		public void setService_register_details(boolean service_register_details) {
			this.service_register_details = service_register_details;
		}

		public boolean isCancel_invoice_report() {
			return cancel_invoice_report;
		}

		public void setCancel_invoice_report(boolean cancel_invoice_report) {
			this.cancel_invoice_report = cancel_invoice_report;
		}

		public boolean isKPI_report() {
			return KPI_report;
		}

		public void setKPI_report(boolean kPI_report) {
			KPI_report = kPI_report;
		}
 public boolean isShow_master() {
			return show_master;
		}

		public void setShow_master(boolean show_master) {
			this.show_master = show_master;
		}

public boolean isAcc_refund() {
			return acc_refund;
		}

		public void setAcc_refund(boolean acc_refund) {
			this.acc_refund = acc_refund;
		}


public boolean isToken_display() {
			return token_display;
		}

		public void setToken_display(boolean token_display) {
			this.token_display = token_display;
		}

public boolean isPurchase_edit_pharmacy() {
			return purchase_edit_pharmacy;
		}

		public void setPurchase_edit_pharmacy(boolean purchase_edit_pharmacy) {
			this.purchase_edit_pharmacy = purchase_edit_pharmacy;
		}


public String getCancel_po() {
			return cancel_po;
		}

		public void setCancel_po(String cancel_po) {
			this.cancel_po = cancel_po;
		}


public boolean isAdd_medicine() {
			return add_medicine;
		}

		public void setAdd_medicine(boolean add_medicine) {
			this.add_medicine = add_medicine;
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


public boolean isMisaccess() {
			return misaccess;
		}

		public void setMisaccess(boolean misaccess) {
			this.misaccess = misaccess;
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


public boolean isShow_hospital_admin() {
			return show_hospital_admin;
		}

		public void setShow_hospital_admin(boolean show_hospital_admin) {
			this.show_hospital_admin = show_hospital_admin;
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


public boolean isEditchargesacs() {
			return editchargesacs;
		}

		public void setEditchargesacs(boolean editchargesacs) {
			this.editchargesacs = editchargesacs;
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


public boolean isSms_on_bedchange() {
			return sms_on_bedchange;
		}

		public void setSms_on_bedchange(boolean sms_on_bedchange) {
			this.sms_on_bedchange = sms_on_bedchange;
		}

		public boolean isSms_on_newadm() {
			return sms_on_newadm;
		}

		public void setSms_on_newadm(boolean sms_on_newadm) {
			this.sms_on_newadm = sms_on_newadm;
		}


public String getFullname() {
			return fullname;
		}

		public void setFullname(String fullname) {
			this.fullname = fullname;
		}


public String getInvestigation_newaccess() {
			return investigation_newaccess;
		}

		public void setInvestigation_newaccess(String investigation_newaccess) {
			this.investigation_newaccess = investigation_newaccess;
		}


public boolean isCathlab() {
			return cathlab;
		}

		public void setCathlab(boolean cathlab) {
			this.cathlab = cathlab;
		}


public int getIpd_abbr_access() {
			return ipd_abbr_access;
		}

		public void setIpd_abbr_access(int ipd_abbr_access) {
			this.ipd_abbr_access = ipd_abbr_access;
		}


public boolean isHidelogoinvst() {
			return hidelogoinvst;
		}

		public void setHidelogoinvst(boolean hidelogoinvst) {
			this.hidelogoinvst = hidelogoinvst;
		}

		public boolean isHidelogoemr() {
			return hidelogoemr;
		}

		public void setHidelogoemr(boolean hidelogoemr) {
			this.hidelogoemr = hidelogoemr;
		}

		public boolean isHidelogobillinv() {
			return hidelogobillinv;
		}

		public void setHidelogobillinv(boolean hidelogobillinv) {
			this.hidelogobillinv = hidelogobillinv;
		}


public boolean isInvst_inv_apr() {
			return invst_inv_apr;
		}

		public void setInvst_inv_apr(boolean invst_inv_apr) {
			this.invst_inv_apr = invst_inv_apr;
		}


public int getPagelimitpharmacy() {
			return pagelimitpharmacy;
		}

		public void setPagelimitpharmacy(int pagelimitpharmacy) {
			this.pagelimitpharmacy = pagelimitpharmacy;
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


public String getWarningmsg() {
			return warningmsg;
		}

		public void setWarningmsg(String warningmsg) {
			this.warningmsg = warningmsg;
		}


public String getUserMobileNo() {
			return userMobileNo;
		}

		public void setUserMobileNo(String userMobileNo) {
			this.userMobileNo = userMobileNo;
		}


public boolean isTreatment_episode_acc() {
			return treatment_episode_acc;
		}

		public void setTreatment_episode_acc(boolean treatment_episode_acc) {
			this.treatment_episode_acc = treatment_episode_acc;
		}


public boolean isPcsAdmin() {
			return pcsAdmin;
		}

		public void setPcsAdmin(boolean pcsAdmin) {
			this.pcsAdmin = pcsAdmin;
		}


private boolean purchase_edit_pharmacy;
private boolean acc_refund;
private boolean show_master; 
private boolean token_display;
private String cancel_po;
private boolean add_medicine;
private boolean isdotmatrix;
private boolean pharm_print_backdate;
private boolean bdaysms;
private boolean immusms;
private boolean f_diagnosis_discharge;
private boolean seq_no_gen;
private boolean misaccess;
private boolean removeprocurement;
private boolean modify_disc;
private boolean smsVisitingConslt;
private boolean show_hospital_admin;
private boolean direct_ipd;
private boolean drwise_ipd;
private boolean jobtitlewise_investigation;
private boolean editchargesacs;
private boolean show_wardname;
private boolean discharge_new;
private boolean show_unpost;
private boolean sms_on_bedchange;
private boolean sms_on_newadm;
private String fullname;
private String investigation_newaccess="";
private boolean cathlab; 
private int ipd_abbr_access;
private boolean hidelogoinvst;
private boolean hidelogoemr; 
private boolean hidelogobillinv;
private boolean invst_inv_apr;
private int pagelimitpharmacy;
private int discharge_validation;
private String patientname_field;
private String practitonername_field;
private int address_manual;
private String warningmsg;
private String userMobileNo;
private boolean treatment_episode_acc;
private boolean pcsAdmin;
private boolean balgopal;
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


public boolean isSupport_Access() {
	return support_Access;
}

public void setSupport_Access(boolean support_Access) {
	this.support_Access = support_Access;
}


public boolean isPaymentReport() {
	return paymentReport;
}

public void setPaymentReport(boolean paymentReport) {
	this.paymentReport = paymentReport;
}


private boolean invest_order,demo_access;
private boolean json_diagnosis;
private boolean support_Access;
private boolean paymentReport;

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
private boolean myhr;
private boolean daycare;
private boolean emergency_lbl;






public boolean isMyhr() {
	return myhr;
}

public void setMyhr(boolean myhr) {
	this.myhr = myhr;
}

public boolean isDaycare() {
	return daycare;
}

public void setDaycare(boolean daycare) {
	this.daycare = daycare;
}

public boolean isEmergency_lbl() {
	return emergency_lbl;
}

public void setEmergency_lbl(boolean emergency_lbl) {
	this.emergency_lbl = emergency_lbl;
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


public int getVacinator() {
	return vacinator;
}

public void setVacinator(int vacinator) {
	this.vacinator = vacinator;
}


private boolean add_manual_charge;
private boolean update_investigation_charge;
private int vacinator;
private boolean invest_savenprint;
private boolean prisc_savenprint;




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

public boolean isInvoicemodify() {
	return invoicemodify;
}

public void setInvoicemodify(boolean invoicemodify) {
	this.invoicemodify = invoicemodify;
}

public boolean isIndv_discount() {
	return indv_discount;
}

public void setIndv_discount(boolean indv_discount) {
	this.indv_discount = indv_discount;
}

public boolean isNewdischargecard() {
	return newdischargecard;
}

public void setNewdischargecard(boolean newdischargecard) {
	this.newdischargecard = newdischargecard;
}


public boolean isPayrollaccess() {
	return payrollaccess;
}

public void setPayrollaccess(boolean payrollaccess) {
	this.payrollaccess = payrollaccess;
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




private boolean newdischargecard;
private boolean discharge_msg_hs;
private boolean barcode_productname_show;
private boolean release_notes_upload;
private boolean new_aureus_discard;
private boolean emr_vitals_show;
private boolean deleted_invst_charge;
private String invoice_default_note;
public String getInvoice_default_note() {
	return invoice_default_note;
}

public void setInvoice_default_note(String invoice_default_note) {
	this.invoice_default_note = invoice_default_note;
}

public boolean isDisc_approve_sms() {
	return disc_approve_sms;
}

public void setDisc_approve_sms(boolean disc_approve_sms) {
	this.disc_approve_sms = disc_approve_sms;
}
public boolean isAdd_charge_amt_edit() {
	return add_charge_amt_edit;
}

public void setAdd_charge_amt_edit(boolean add_charge_amt_edit) {
	this.add_charge_amt_edit = add_charge_amt_edit;
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


public String getLinkaddress() {
	return linkaddress;
}

public void setLinkaddress(String linkaddress) {
	this.linkaddress = linkaddress;
}


public String getPuresevaclientid() {
	return puresevaclientid;
}

public void setPuresevaclientid(String puresevaclientid) {
	this.puresevaclientid = puresevaclientid;
}


public String getDept() {
	return dept;
}

public void setDept(String dept) {
	this.dept = dept;
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


public int getTokenstatus() {
	return tokenstatus;
}

public void setTokenstatus(int tokenstatus) {
	this.tokenstatus = tokenstatus;
}


public boolean isWardhazp() {
	return wardhazp;
}

public void setWardhazp(boolean wardhazp) {
	this.wardhazp = wardhazp;
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


public boolean isManual_sms_vaccine() {
	return manual_sms_vaccine;
}

public void setManual_sms_vaccine(boolean manual_sms_vaccine) {
	this.manual_sms_vaccine = manual_sms_vaccine;
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


public boolean isClientprof() {
	return clientprof;
}

public void setClientprof(boolean clientprof) {
	this.clientprof = clientprof;
}


public boolean isToDoctorIpd() {
	return toDoctorIpd;
}

public void setToDoctorIpd(boolean toDoctorIpd) {
	this.toDoctorIpd = toDoctorIpd;
}


public boolean isAppreception() {
	return appreception;
}

public void setAppreception(boolean appreception) {
	this.appreception = appreception;
}


public boolean isAdmsn_date_edit() {
	return admsn_date_edit;
}

public void setAdmsn_date_edit(boolean admsn_date_edit) {
	this.admsn_date_edit = admsn_date_edit;
}


public boolean isPatientemr() {
	return patientemr;
}

public void setPatientemr(boolean patientemr) {
	this.patientemr = patientemr;
}


public boolean isSms_cancel_appointment() {
	return sms_cancel_appointment;
}

public void setSms_cancel_appointment(boolean sms_cancel_appointment) {
	this.sms_cancel_appointment = sms_cancel_appointment;
}


private boolean add_charge_amt_edit,manual_sms_vaccine;
private boolean toDoctorIpd,admsn_date_edit,patientemr;
private String regional_lang;
private boolean opd_chat;
private boolean del_ipd_data_cancel_inv;
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

public boolean isDelete_invoice_history() {
	return delete_invoice_history;
}

public void setDelete_invoice_history(boolean delete_invoice_history) {
	this.delete_invoice_history = delete_invoice_history;
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

public boolean isAnalytics_template() {
	return analytics_template;
}

public void setAnalytics_template(boolean analytics_template) {
	this.analytics_template = analytics_template;
}


public boolean isDelete_invoice() {
	return delete_invoice;
}

public void setDelete_invoice(boolean delete_invoice) {
	this.delete_invoice = delete_invoice;
}


public int getUserwiseaceess() {
	return userwiseaceess;
}

public void setUserwiseaceess(int userwiseaceess) {
	this.userwiseaceess = userwiseaceess;
}


public String getActionType() {
	return actionType;
}

public void setActionType(String actionType) {
	this.actionType = actionType;
}


public String getShow_dept_opd_list() {
	return show_dept_opd_list;
}

public void setShow_dept_opd_list(String show_dept_opd_list) {
	this.show_dept_opd_list = show_dept_opd_list;
}


public boolean isRegistrationdash() {
	return registrationdash;
}

public void setRegistrationdash(boolean registrationdash) {
	this.registrationdash = registrationdash;
}


public int getPatient_category() {
	return patient_category;
}

public void setPatient_category(int patient_category) {
	this.patient_category = patient_category;
}


public boolean isDeptOpdReport() {
	return deptOpdReport;
}

public void setDeptOpdReport(boolean deptOpdReport) {
	this.deptOpdReport = deptOpdReport;
}


private boolean analytics_template;
private String doctorname;

public String getDoctorname() {
	return doctorname;
}

public void setDoctorname(String doctorname) {
	this.doctorname = doctorname;
}

private boolean physio;
private boolean amravati;
private boolean simpliclinic;
private boolean infinyte;
private boolean vrundawan;
private boolean bams;
private boolean bams1;
private boolean mbbs,uhiddatewise;
private boolean ayushman;
private String patient_name;
private String sms_type;
private boolean physio_ipd;
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

public boolean isPhysio() {
	return physio;
}

public void setPhysio(boolean physio) {
	this.physio = physio;
}

private String mobileno;


public String getMobileno() {
	return mobileno;
}

public void setMobileno(String mobileno) {
	this.mobileno = mobileno;
}


}
