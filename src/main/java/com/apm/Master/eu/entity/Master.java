package com.apm.Master.eu.entity;

import java.util.ArrayList;
import java.util.Vector;

import com.apm.Accounts.eu.entity.Accounts;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.Emr.eu.entity.Investigation;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Registration.eu.entity.UserProfile;

public class Master {
	public String getChargename() {
		return chargename;
	}

	public void setChargename(String chargename) {
		this.chargename = chargename;
	}

	public String getNotesid() {
		return notesid;
	}

	public void setNotesid(String notesid) {
		this.notesid = notesid;
	}

	public String getHistoryid() {
		return historyid;
	}

	public void setHistoryid(String historyid) {
		this.historyid = historyid;
	}

	public String getPasthistory() {
		return pasthistory;
	}

	public void setPasthistory(String pasthistory) {
		this.pasthistory = pasthistory;
	}

	public String getChiefcomplainid() {
		return chiefcomplainid;
	}

	public void setChiefcomplainid(String chiefcomplainid) {
		this.chiefcomplainid = chiefcomplainid;
	}

	public String getChiefcomplains() {
		return Chiefcomplains;
	}

	public void setChiefcomplains(String chiefcomplains) {
		Chiefcomplains = chiefcomplains;
	}

	public int getLedgerid() {
		return ledgerid;
	}

	public void setLedgerid(int ledgerid) {
		this.ledgerid = ledgerid;
	}

	public int getArvnew() {
		return arvnew;
	}

	public void setArvnew(int arvnew) {
		this.arvnew = arvnew;
	}

	public int getArvold() {
		return arvold;
	}

	public void setArvold(int arvold) {
		this.arvold = arvold;
	}

	public int getNewpatient() {
		return newpatient;
	}

	public void setNewpatient(int newpatient) {
		this.newpatient = newpatient;
	}

	public int getOldpatient() {
		return oldpatient;
	}

	public void setOldpatient(int oldpatient) {
		this.oldpatient = oldpatient;
	}

	public int getTotalcount() {
		return totalcount;
	}

	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}

	public String getCharges() {
		return charges;
	}

	public void setCharges(String charges) {
		this.charges = charges;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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

	public String getAppointmentType() {
		return appointmentType;
	}

	public void setAppointmentType(String appointmentType) {
		this.appointmentType = appointmentType;
	}

	public Accounts getAssesmentlist1() {
		return assesmentlist1;
	}

	public void setAssesmentlist1(Accounts assesmentlist1) {
		this.assesmentlist1 = assesmentlist1;
	}

	public ArrayList<Master> getAssesmentlist() {
		return assesmentlist;
	}

	public void setAssesmentlist(ArrayList<Master> assesmentlist) {
		this.assesmentlist = assesmentlist;
	}

	public String getMedicinename() {
		return medicinename;
	}

	public void setMedicinename(String medicinename) {
		this.medicinename = medicinename;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public String getMasterchargnames() {
		return masterchargnames;
	}

	public void setMasterchargnames(String masterchargnames) {
		this.masterchargnames = masterchargnames;
	}

	public String getPayroll() {
		return payroll;
	}

	public void setPayroll(String payroll) {
		this.payroll = payroll;
	}

	private String regional;
	private String pharmacycheck;
	private String sectionid;
	private int ihpatient;
	private int ipd;
	private String totalRecords;
	private String otnotes;
	private int count;
	private String givenTo;
	private boolean bghhead;
	private boolean isprntchrg;
	private String jobtitle;
	private String showdeptlist;
	private String masterchargnames;
	//ADARSH
	private ArrayList<Master> prntpwardwisechargelist;
	private String nursing_planning;
	 private String diagnosisname;
	 private String planningid;
	 private String intervention_name;
	 private String todate;
	 private String services;
	 private String actype;
	 private String procedurename;
	 private String chargetype;
	 
	 private String totalids;
	 private String nursing_intervention;
	 
	 private String address;
	 private String vendor;
	 private String invsttype;
	 private String cbal;
	 private String ctype;
	 
	 private String fdate;
	 private String tdate;
	 private int breakage;
	 private String krackage;
	 private double totaldiscount;
	 private String defaultremark;
	 private String sitting;
	 private String hosp_name;
	 private String commercial;
     private double subtotal;
     private String medicinename;
	 private ArrayList<Master>hospitallist;
	 private String clinic_name;
	 private ArrayList<NotAvailableSlot>departmentIPDPreviewList;
	 private ArrayList<Master> referdeptlist;
	 private String charges;
	 private int quantity;
	 private String unitcharge;
     private String chargedisc;
     private String appointmentType;
     private String doctorName;
     private String share;
     private double revenue;
     private String invoiceids;
     private String camp_location;
     private int ledgerid;
     private String clientType;
 	 private String Chiefcomplains;
     private String chiefcomplainid;
     private String pasthistory;
     private String historyid;
     private String notesid;
     private String chargename;
     public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

	public String getCamp_location() {
 		return camp_location;
 	}

 	public void setCamp_location(String camp_location) {
 		this.camp_location = camp_location;
 	}
	
     public String getInvoiceids() {
		return invoiceids;
	}

	public void setInvoiceids(String invoiceids) {
		this.invoiceids = invoiceids;
	}

	public double getRevenue() {
		return revenue;
	}

	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}

	public String getDoctorName() {
 		return doctorName;
 	}

 	public void setDoctorName(String doctorName) {
 		this.doctorName = doctorName;
 	}

 	public String getShare() {
 		return share;
 	}

 	public void setShare(String share) {
 		this.share = share;
 	}
	 public ArrayList<Master> getReferdeptlist() {
		return referdeptlist;
	}

	public void setReferdeptlist(ArrayList<Master> referdeptlist) {
		this.referdeptlist = referdeptlist;
	}

	public ArrayList<NotAvailableSlot> getDepartmentIPDPreviewList() {
		return departmentIPDPreviewList;
	}

	public void setDepartmentIPDPreviewList(ArrayList<NotAvailableSlot> departmentIPDPreviewList) {
		this.departmentIPDPreviewList = departmentIPDPreviewList;
	}

	public String getClinic_name() {
		return clinic_name;
	}

	public void setClinic_name(String clinic_name) {
		this.clinic_name = clinic_name;
	}

	public ArrayList<Master> getHospitallist() {
		return hospitallist;
	}

	public void setHospitallist(ArrayList<Master> hospitallist) {
		this.hospitallist = hospitallist;
	}

	public String getHosp_name() {
		return hosp_name;
	}

	public void setHosp_name(String hosp_name) {
		this.hosp_name = hosp_name;
	}

	public String getCommercial() {
		return commercial;
	}

	public void setCommercial(String commercial) {
		this.commercial = commercial;
	}

	public String getSitting() {
		return sitting;
	}

	public void setSitting(String sitting) {
		this.sitting = sitting;
	}

	private int hidecode;
	
	 private ArrayList<Bed> wardlist;
	 private String altername;	
	 
	 private int finalNewPatientCount=0;
	 private int finalOldPatientCount=0;
	 private int finalTotalPatientCount=0;
	 private int newPatientCount=0;
	 private int oldPatientCound=0;
	 private int totalPatientCount=0;
	 private String workorderterms;
	 
	public String getWorkorderterms() {
		return workorderterms;
	}

	public void setWorkorderterms(String workorderterms) {
		this.workorderterms = workorderterms;
	}

	public int getFinalNewPatientCount() {
		return finalNewPatientCount;
	}

	public void setFinalNewPatientCount(int finalNewPatientCount) {
		this.finalNewPatientCount = finalNewPatientCount;
	}

	public int getFinalOldPatientCount() {
		return finalOldPatientCount;
	}

	public void setFinalOldPatientCount(int finalOldPatientCount) {
		this.finalOldPatientCount = finalOldPatientCount;
	}

	public int getFinalTotalPatientCount() {
		return finalTotalPatientCount;
	}

	public void setFinalTotalPatientCount(int finalTotalPatientCount) {
		this.finalTotalPatientCount = finalTotalPatientCount;
	}

	public int getNewPatientCount() {
		return newPatientCount;
	}

	public void setNewPatientCount(int newPatientCount) {
		this.newPatientCount = newPatientCount;
	}

	public int getOldPatientCound() {
		return oldPatientCound;
	}

	public void setOldPatientCound(int oldPatientCound) {
		this.oldPatientCound = oldPatientCound;
	}

	public int getTotalPatientCount() {
		return totalPatientCount;
	}

	public void setTotalPatientCount(int totalPatientCount) {
		this.totalPatientCount = totalPatientCount;
	}

	public String getAltername() {
		return altername;
	}

	public void setAltername(String altername) {
		this.altername = altername;
	}

	public ArrayList<Bed> getWardlist() {
		return wardlist;
	}

	public void setWardlist(ArrayList<Bed> wardlist) {
		this.wardlist = wardlist;
	}

	public String getKrackage() {
		return krackage;
	}

	public void setKrackage(String krackage) {
		this.krackage = krackage;
	}

	

	public int getBreakage() {
		return breakage;
	}

	public void setBreakage(int breakage) {
		this.breakage = breakage;
	}

	public String getFdate() {
		return fdate;
	}

	public void setFdate(String fdate) {
		this.fdate = fdate;
	}

	public String getTdate() {
		return tdate;
	}

	public void setTdate(String tdate) {
		this.tdate = tdate;
	}

	public String getCbal() {
		return cbal;
	}

	public void setCbal(String cbal) {
		this.cbal = cbal;
	}

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	private String bcg, opv0, hep_b1, dtwp1, ipv1, hep_b2, hib1, rotavirus1, pcv1, dtwp2, ipv2, hib2, rotavirus2, pcv2, dtwp3, ipv3;
	  private String hib3, rotavirus3, pcv3, opv1, hepb3, opv2, mmr1, typhoid_conjugate, vaccine;
	  private String hepa1, mmr2, varicella1, pcvbooster, dtwpb1dtapb1, ipvb1, hibb1, hepa2, boosteroftyphoid, conjugatevaccine, dtwpb2dtapb2, tdaptd, hpv;
	  private String bcgdt, opv0dt, hep_b1dt, dtwp1dt, ipv1dt, hep_b2dt, hib1dt, rotavirus1dt, pcv1dt, dtwp2dt, ipv2dt, hib2dt, rotavirus2dt, pcv2dt, dtwp3dt, ipv3dt;
	  private String hib3dt, rotavirus3dt, pcv3dt, opv1dt, hepb3dt, opv2dt, mmr1dt, typhoid_conjugatedt, vaccinedt;
	  private String hepa1dt, mmr2dt, varicella1dt, pcvboosterdt, dtwpb1dtapb1dt, ipvb1dt, hibb1dt, hepa2dt, boosteroftyphoiddt, conjugatevaccinedt, dtwpb2dtapb2dt, tdaptddt, hpvdt;
	  private String remark1,remark2,remark3,remark4,remark5,remark6,remark7,remark8,remark9,remark10,remark11,remark12,remark13,remark14,remark15;
	  private String dob;
	  
	  ArrayList<Accounts>sdebtorlist;
	  private String debitx;
	  private String creditx;
	  private String cbalancex;
	  
	  private String dbaltotalx;
	  private String cbaltotalx;
	  
	  private String cctotal;
	  private String ddtotal;
	  private String wardname;
	  private ArrayList<Master> wardwisechargelist;
	  
	  
	 public ArrayList<Master> getWardwisechargelist() {
		return wardwisechargelist;
	}

	public void setWardwisechargelist(ArrayList<Master> wardwisechargelist) {
		this.wardwisechargelist = wardwisechargelist;
	}

	public String getWardname() {
		return wardname;
	}

	public void setWardname(String wardname) {
		this.wardname = wardname;
	}

	public String getCctotal() {
		return cctotal;
	}

	public void setCctotal(String cctotal) {
		this.cctotal = cctotal;
	}

	public String getDdtotal() {
		return ddtotal;
	}

	public void setDdtotal(String ddtotal) {
		this.ddtotal = ddtotal;
	}

	public String getDbaltotalx() {
		return dbaltotalx;
	}

	public void setDbaltotalx(String dbaltotalx) {
		this.dbaltotalx = dbaltotalx;
	}

	public String getCbaltotalx() {
		return cbaltotalx;
	}

	public void setCbaltotalx(String cbaltotalx) {
		this.cbaltotalx = cbaltotalx;
	}

	public String getDebitx() {
		return debitx;
	}

	public void setDebitx(String debitx) {
		this.debitx = debitx;
	}

	public String getCreditx() {
		return creditx;
	}

	public void setCreditx(String creditx) {
		this.creditx = creditx;
	}

	public String getCbalancex() {
		return cbalancex;
	}

	public void setCbalancex(String cbalancex) {
		this.cbalancex = cbalancex;
	}

	public ArrayList<Accounts> getSdebtorlist() {
		return sdebtorlist;
	}

	public void setSdebtorlist(ArrayList<Accounts> sdebtorlist) {
		this.sdebtorlist = sdebtorlist;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getBcg() {
			return bcg;
		}

		public void setBcg(String bcg) {
			this.bcg = bcg;
		}

		public String getOpv0() {
			return opv0;
		}

		public void setOpv0(String opv0) {
			this.opv0 = opv0;
		}

		public String getHep_b1() {
			return hep_b1;
		}

		public void setHep_b1(String hep_b1) {
			this.hep_b1 = hep_b1;
		}

		public String getDtwp1() {
			return dtwp1;
		}

		public void setDtwp1(String dtwp1) {
			this.dtwp1 = dtwp1;
		}

		public String getIpv1() {
			return ipv1;
		}

		public void setIpv1(String ipv1) {
			this.ipv1 = ipv1;
		}

		public String getHep_b2() {
			return hep_b2;
		}

		public void setHep_b2(String hep_b2) {
			this.hep_b2 = hep_b2;
		}

		public String getHib1() {
			return hib1;
		}

		public void setHib1(String hib1) {
			this.hib1 = hib1;
		}

		public String getRotavirus1() {
			return rotavirus1;
		}

		public void setRotavirus1(String rotavirus1) {
			this.rotavirus1 = rotavirus1;
		}

		public String getPcv1() {
			return pcv1;
		}

		public void setPcv1(String pcv1) {
			this.pcv1 = pcv1;
		}

		public String getDtwp2() {
			return dtwp2;
		}

		public void setDtwp2(String dtwp2) {
			this.dtwp2 = dtwp2;
		}

		public String getIpv2() {
			return ipv2;
		}

		public void setIpv2(String ipv2) {
			this.ipv2 = ipv2;
		}

		public String getHib2() {
			return hib2;
		}

		public void setHib2(String hib2) {
			this.hib2 = hib2;
		}

		public String getRotavirus2() {
			return rotavirus2;
		}

		public void setRotavirus2(String rotavirus2) {
			this.rotavirus2 = rotavirus2;
		}

		public String getPcv2() {
			return pcv2;
		}

		public void setPcv2(String pcv2) {
			this.pcv2 = pcv2;
		}

		public String getDtwp3() {
			return dtwp3;
		}

		public void setDtwp3(String dtwp3) {
			this.dtwp3 = dtwp3;
		}

		public String getIpv3() {
			return ipv3;
		}

		public void setIpv3(String ipv3) {
			this.ipv3 = ipv3;
		}

		public String getHib3() {
			return hib3;
		}

		public void setHib3(String hib3) {
			this.hib3 = hib3;
		}

		public String getRotavirus3() {
			return rotavirus3;
		}

		public void setRotavirus3(String rotavirus3) {
			this.rotavirus3 = rotavirus3;
		}

		public String getPcv3() {
			return pcv3;
		}

		public void setPcv3(String pcv3) {
			this.pcv3 = pcv3;
		}

		public String getOpv1() {
			return opv1;
		}

		public void setOpv1(String opv1) {
			this.opv1 = opv1;
		}

		public String getHepb3() {
			return hepb3;
		}

		public void setHepb3(String hepb3) {
			this.hepb3 = hepb3;
		}

		public String getOpv2() {
			return opv2;
		}

		public void setOpv2(String opv2) {
			this.opv2 = opv2;
		}

		public String getMmr1() {
			return mmr1;
		}

		public void setMmr1(String mmr1) {
			this.mmr1 = mmr1;
		}

		public String getTyphoid_conjugate() {
			return typhoid_conjugate;
		}

		public void setTyphoid_conjugate(String typhoid_conjugate) {
			this.typhoid_conjugate = typhoid_conjugate;
		}

		public String getVaccine() {
			return vaccine;
		}

		public void setVaccine(String vaccine) {
			this.vaccine = vaccine;
		}

		public String getHepa1() {
			return hepa1;
		}

		public void setHepa1(String hepa1) {
			this.hepa1 = hepa1;
		}

		public String getMmr2() {
			return mmr2;
		}

		public void setMmr2(String mmr2) {
			this.mmr2 = mmr2;
		}

		public String getVaricella1() {
			return varicella1;
		}

		public void setVaricella1(String varicella1) {
			this.varicella1 = varicella1;
		}

		public String getPcvbooster() {
			return pcvbooster;
		}

		public void setPcvbooster(String pcvbooster) {
			this.pcvbooster = pcvbooster;
		}

		public String getDtwpb1dtapb1() {
			return dtwpb1dtapb1;
		}

		public void setDtwpb1dtapb1(String dtwpb1dtapb1) {
			this.dtwpb1dtapb1 = dtwpb1dtapb1;
		}

		public String getIpvb1() {
			return ipvb1;
		}

		public void setIpvb1(String ipvb1) {
			this.ipvb1 = ipvb1;
		}

		public String getHibb1() {
			return hibb1;
		}

		public void setHibb1(String hibb1) {
			this.hibb1 = hibb1;
		}

		public String getHepa2() {
			return hepa2;
		}

		public void setHepa2(String hepa2) {
			this.hepa2 = hepa2;
		}

		public String getBoosteroftyphoid() {
			return boosteroftyphoid;
		}

		public void setBoosteroftyphoid(String boosteroftyphoid) {
			this.boosteroftyphoid = boosteroftyphoid;
		}

		public String getConjugatevaccine() {
			return conjugatevaccine;
		}

		public void setConjugatevaccine(String conjugatevaccine) {
			this.conjugatevaccine = conjugatevaccine;
		}

		public String getDtwpb2dtapb2() {
			return dtwpb2dtapb2;
		}

		public void setDtwpb2dtapb2(String dtwpb2dtapb2) {
			this.dtwpb2dtapb2 = dtwpb2dtapb2;
		}

		public String getTdaptd() {
			return tdaptd;
		}

		public void setTdaptd(String tdaptd) {
			this.tdaptd = tdaptd;
		}

		public String getHpv() {
			return hpv;
		}

		public void setHpv(String hpv) {
			this.hpv = hpv;
		}

		public String getBcgdt() {
			return bcgdt;
		}

		public void setBcgdt(String bcgdt) {
			this.bcgdt = bcgdt;
		}

		public String getOpv0dt() {
			return opv0dt;
		}

		public void setOpv0dt(String opv0dt) {
			this.opv0dt = opv0dt;
		}

		public String getHep_b1dt() {
			return hep_b1dt;
		}

		public void setHep_b1dt(String hep_b1dt) {
			this.hep_b1dt = hep_b1dt;
		}

		public String getDtwp1dt() {
			return dtwp1dt;
		}

		public void setDtwp1dt(String dtwp1dt) {
			this.dtwp1dt = dtwp1dt;
		}

		public String getIpv1dt() {
			return ipv1dt;
		}

		public void setIpv1dt(String ipv1dt) {
			this.ipv1dt = ipv1dt;
		}

		public String getHep_b2dt() {
			return hep_b2dt;
		}

		public void setHep_b2dt(String hep_b2dt) {
			this.hep_b2dt = hep_b2dt;
		}

		public String getHib1dt() {
			return hib1dt;
		}

		public void setHib1dt(String hib1dt) {
			this.hib1dt = hib1dt;
		}

		public String getRotavirus1dt() {
			return rotavirus1dt;
		}

		public void setRotavirus1dt(String rotavirus1dt) {
			this.rotavirus1dt = rotavirus1dt;
		}

		public String getPcv1dt() {
			return pcv1dt;
		}

		public void setPcv1dt(String pcv1dt) {
			this.pcv1dt = pcv1dt;
		}

		public String getDtwp2dt() {
			return dtwp2dt;
		}

		public void setDtwp2dt(String dtwp2dt) {
			this.dtwp2dt = dtwp2dt;
		}

		public String getIpv2dt() {
			return ipv2dt;
		}

		public void setIpv2dt(String ipv2dt) {
			this.ipv2dt = ipv2dt;
		}

		public String getHib2dt() {
			return hib2dt;
		}

		public void setHib2dt(String hib2dt) {
			this.hib2dt = hib2dt;
		}

		public String getRotavirus2dt() {
			return rotavirus2dt;
		}

		public void setRotavirus2dt(String rotavirus2dt) {
			this.rotavirus2dt = rotavirus2dt;
		}

		public String getPcv2dt() {
			return pcv2dt;
		}

		public void setPcv2dt(String pcv2dt) {
			this.pcv2dt = pcv2dt;
		}

		public String getDtwp3dt() {
			return dtwp3dt;
		}

		public void setDtwp3dt(String dtwp3dt) {
			this.dtwp3dt = dtwp3dt;
		}

		public String getIpv3dt() {
			return ipv3dt;
		}

		public void setIpv3dt(String ipv3dt) {
			this.ipv3dt = ipv3dt;
		}

		public String getHib3dt() {
			return hib3dt;
		}

		public void setHib3dt(String hib3dt) {
			this.hib3dt = hib3dt;
		}

		public String getRotavirus3dt() {
			return rotavirus3dt;
		}

		public void setRotavirus3dt(String rotavirus3dt) {
			this.rotavirus3dt = rotavirus3dt;
		}

		public String getPcv3dt() {
			return pcv3dt;
		}

		public void setPcv3dt(String pcv3dt) {
			this.pcv3dt = pcv3dt;
		}

		public String getOpv1dt() {
			return opv1dt;
		}

		public void setOpv1dt(String opv1dt) {
			this.opv1dt = opv1dt;
		}

		public String getHepb3dt() {
			return hepb3dt;
		}

		public void setHepb3dt(String hepb3dt) {
			this.hepb3dt = hepb3dt;
		}

		public String getOpv2dt() {
			return opv2dt;
		}

		public void setOpv2dt(String opv2dt) {
			this.opv2dt = opv2dt;
		}

		public String getMmr1dt() {
			return mmr1dt;
		}

		public void setMmr1dt(String mmr1dt) {
			this.mmr1dt = mmr1dt;
		}

		public String getTyphoid_conjugatedt() {
			return typhoid_conjugatedt;
		}

		public void setTyphoid_conjugatedt(String typhoid_conjugatedt) {
			this.typhoid_conjugatedt = typhoid_conjugatedt;
		}

		public String getVaccinedt() {
			return vaccinedt;
		}

		public void setVaccinedt(String vaccinedt) {
			this.vaccinedt = vaccinedt;
		}

		public String getHepa1dt() {
			return hepa1dt;
		}

		public void setHepa1dt(String hepa1dt) {
			this.hepa1dt = hepa1dt;
		}

		public String getMmr2dt() {
			return mmr2dt;
		}

		public void setMmr2dt(String mmr2dt) {
			this.mmr2dt = mmr2dt;
		}

		public String getVaricella1dt() {
			return varicella1dt;
		}

		public void setVaricella1dt(String varicella1dt) {
			this.varicella1dt = varicella1dt;
		}

		public String getPcvboosterdt() {
			return pcvboosterdt;
		}

		public void setPcvboosterdt(String pcvboosterdt) {
			this.pcvboosterdt = pcvboosterdt;
		}

		public String getDtwpb1dtapb1dt() {
			return dtwpb1dtapb1dt;
		}

		public void setDtwpb1dtapb1dt(String dtwpb1dtapb1dt) {
			this.dtwpb1dtapb1dt = dtwpb1dtapb1dt;
		}

		public String getIpvb1dt() {
			return ipvb1dt;
		}

		public void setIpvb1dt(String ipvb1dt) {
			this.ipvb1dt = ipvb1dt;
		}

		public String getHibb1dt() {
			return hibb1dt;
		}

		public void setHibb1dt(String hibb1dt) {
			this.hibb1dt = hibb1dt;
		}

		public String getHepa2dt() {
			return hepa2dt;
		}

		public void setHepa2dt(String hepa2dt) {
			this.hepa2dt = hepa2dt;
		}

		public String getBoosteroftyphoiddt() {
			return boosteroftyphoiddt;
		}

		public void setBoosteroftyphoiddt(String boosteroftyphoiddt) {
			this.boosteroftyphoiddt = boosteroftyphoiddt;
		}

		public String getConjugatevaccinedt() {
			return conjugatevaccinedt;
		}

		public void setConjugatevaccinedt(String conjugatevaccinedt) {
			this.conjugatevaccinedt = conjugatevaccinedt;
		}

		public String getDtwpb2dtapb2dt() {
			return dtwpb2dtapb2dt;
		}

		public void setDtwpb2dtapb2dt(String dtwpb2dtapb2dt) {
			this.dtwpb2dtapb2dt = dtwpb2dtapb2dt;
		}

		public String getTdaptddt() {
			return tdaptddt;
		}

		public void setTdaptddt(String tdaptddt) {
			this.tdaptddt = tdaptddt;
		}

		public String getHpvdt() {
			return hpvdt;
		}

		public void setHpvdt(String hpvdt) {
			this.hpvdt = hpvdt;
		}

		public String getRemark1() {
			return remark1;
		}

		public void setRemark1(String remark1) {
			this.remark1 = remark1;
		}

		public String getRemark2() {
			return remark2;
		}

		public void setRemark2(String remark2) {
			this.remark2 = remark2;
		}

		public String getRemark3() {
			return remark3;
		}

		public void setRemark3(String remark3) {
			this.remark3 = remark3;
		}

		public String getRemark4() {
			return remark4;
		}

		public void setRemark4(String remark4) {
			this.remark4 = remark4;
		}

		public String getRemark5() {
			return remark5;
		}

		public void setRemark5(String remark5) {
			this.remark5 = remark5;
		}

		public String getRemark6() {
			return remark6;
		}

		public void setRemark6(String remark6) {
			this.remark6 = remark6;
		}

		public String getRemark7() {
			return remark7;
		}

		public void setRemark7(String remark7) {
			this.remark7 = remark7;
		}

		public String getRemark8() {
			return remark8;
		}

		public void setRemark8(String remark8) {
			this.remark8 = remark8;
		}

		public String getRemark9() {
			return remark9;
		}

		public void setRemark9(String remark9) {
			this.remark9 = remark9;
		}

		public String getRemark10() {
			return remark10;
		}

		public void setRemark10(String remark10) {
			this.remark10 = remark10;
		}

		public String getRemark11() {
			return remark11;
		}

		public void setRemark11(String remark11) {
			this.remark11 = remark11;
		}

		public String getRemark12() {
			return remark12;
		}

		public void setRemark12(String remark12) {
			this.remark12 = remark12;
		}

		public String getRemark13() {
			return remark13;
		}

		public void setRemark13(String remark13) {
			this.remark13 = remark13;
		}

		public String getRemark14() {
			return remark14;
		}

		public void setRemark14(String remark14) {
			this.remark14 = remark14;
		}

		public String getRemark15() {
			return remark15;
		}

		public void setRemark15(String remark15) {
			this.remark15 = remark15;
		}

	public String getNursing_intervention() {
		return nursing_intervention;
	}

	public void setNursing_intervention(String nursing_intervention) {
		this.nursing_intervention = nursing_intervention;
	}

	public String getTotalids() {
		return totalids;
	}

	public void setTotalids(String totalids) {
		this.totalids = totalids;
	}

	public String getChargetype() {
		return chargetype;
	}

	public void setChargetype(String chargetype) {
		this.chargetype = chargetype;
	}

	public String getProcedurename() {
		return procedurename;
	}

	public void setProcedurename(String procedurename) {
		this.procedurename = procedurename;
	}

	public String getServices() {
		return services;
	}

	public void setServices(String services) {
		this.services = services;
	}

	public String getActype() {
		return actype;
	}

	public void setActype(String actype) {
		this.actype = actype;
	}

	public String getPlanningid() {
	  return planningid;
	 }

	 public void setPlanningid(String planningid) {
	  this.planningid = planningid;
	 }

	 public String getIntervention_name() {
	  return intervention_name;
	 }

	 public void setIntervention_name(String intervention_name) {
	  this.intervention_name = intervention_name;
	 }

	 public String getDiagnosisname() {
	  return diagnosisname;
	 }

	 public void setDiagnosisname(String diagnosisname) {
	  this.diagnosisname = diagnosisname;
	 }

	 public String getNursing_planning() {
	  return nursing_planning;
	 }

	 public void setNursing_planning(String nursing_planning) {
	  this.nursing_planning = nursing_planning;
	 }

	 private String nursing_diagnosis;
	 public String getNursing_diagnosis() {
	  return nursing_diagnosis;
	 }

	 public void setNursing_diagnosis(String nursing_diagnosis) {
	  this.nursing_diagnosis = nursing_diagnosis;
	 }
	public String getJobtitle() {
		return jobtitle;
	}

	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getOtnotes() {
		return otnotes;
	}

	public void setOtnotes(String otnotes) {
		this.otnotes = otnotes;
	}

	public String getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}

	private int opd;
	private String subtask;
	
	

	public String getSubtask() {
		return subtask;
	}

	public void setSubtask(String subtask) {
		this.subtask = subtask;
	}

	private String area;
	private String floor;
	private String visitingConsultant;
	private String qualification;
	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getVisitingConsultant() {
		return visitingConsultant;
	}

	public void setVisitingConsultant(String visitingConsultant) {
		this.visitingConsultant = visitingConsultant;
	}

	private String mobileNo;
	private String email;
	private String surgeon;
	private String anesthesiologist;
	private String fees;
	private String referred;
	private String mlc;
	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSurgeon() {
		return surgeon;
	}

	public void setSurgeon(String surgeon) {
		this.surgeon = surgeon;
	}

	public String getAnesthesiologist() {
		return anesthesiologist;
	}

	public void setAnesthesiologist(String anesthesiologist) {
		this.anesthesiologist = anesthesiologist;
	}

	public String getFees() {
		return fees;
	}

	public void setFees(String fees) {
		this.fees = fees;
	}

	public String getReferred() {
		return referred;
	}

	public void setReferred(String referred) {
		this.referred = referred;
	}

	public String getMlc() {
		return mlc;
	}

	public void setMlc(String mlc) {
		this.mlc = mlc;
	}

	private String room_no;
	private int id;
	private String occupation;
	private String jobTitle;
	private String lastmodified;
	private String userid;
	private String min_value_m="0";
	private String max_value_m="0";
	private ArrayList<Master> vitalDataList;
	private String keyname="";
	private String finding="0";
	private String vital_master_id="0";
	private String userFileFileName;
	private String min_value_f="0";
	private String max_value_f="0";
	private String displayname;
	private String fieldtype;
	private String imagename;
	ArrayList<Investigation>invTypeList;
	private String speciality;
	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public ArrayList<Investigation> getInvTypeList() {
		return invTypeList;
	}

	public void setInvTypeList(ArrayList<Investigation> invTypeList) {
		this.invTypeList = invTypeList;
	}

	private String vital_type;
	 private String vital_type_name;
	 private String catagoryid;
	 private String categoryname;
	private String subcategory;
	private String subcategoryname;
	public String getSubcategoryname() {
		return subcategoryname;
	}

	public void setSubcategoryname(String subcategoryname) {
		this.subcategoryname = subcategoryname;
	}

	public String getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	public String getCatagoryid() {
		return catagoryid;
	}

	public void setCatagoryid(String catagoryid) {
		this.catagoryid = catagoryid;
	}

	public String getImagename() {
		return imagename;
	}

	public void setImagename(String imagename) {
		this.imagename = imagename;
	}

	public String getFieldtype() {
		return fieldtype;
	}

	public void setFieldtype(String fieldtype) {
		this.fieldtype = fieldtype;
	}

	public String getDisplayname() {
		return displayname;
	}

	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}

	private String dosetime1="";
	private String dosetime2="";
	private String dosetime3="";
	private String dosetime4="";
	private String dosetime5="";
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getLastmodified() {
		return lastmodified;
	}

	public void setLastmodified(String lastmodified) {
		this.lastmodified = lastmodified;
	}

	public String getPharmacycheck() {
		return pharmacycheck;
	}

	public void setPharmacycheck(String pharmacycheck) {
		this.pharmacycheck = pharmacycheck;
	}

	private String reference;
	private String reporttypex;
	 private String labname;
	 private String creport;

	public String getReporttypex() {
		return reporttypex;
	}

	public void setReporttypex(String reporttypex) {
		this.reporttypex = reporttypex;
	}

	public String getLabname() {
		return labname;
	}

	public void setLabname(String labname) {
		this.labname = labname;
	}

	public String getCreport() {
		return creport;
	}

	public void setCreport(String creport) {
		this.creport = creport;
	}

	public String getSectionid() {
		return sectionid;
	}

	public void setSectionid(String sectionid) {
		this.sectionid = sectionid;
	}

	private String specialization;
	private String description;
	private String datetime;
	private String specimen;
	private int appointmentid;
	private int isstrip;
	private String report_type;
	private String unit;
	private String normal_value;
	private boolean procedure;
	private int strip=0;
	private int templateid;
	private String chargeid;
	private String fields;
	private String dept_id="0";
	private boolean roundcharge;
	private String city;
	private String genericname;
	
	private String invsparentid;
	ArrayList<Investigation> testNameList;
	private  ArrayList<UserProfile> investigationUsers;
	private String investigation_type_id;
	private String date;
	private String wardid;
	private String invsname;
	private int status;
	private String discipline_id;
	private String jobtitlegroup;
	private String jobtitlegroup_id;
	private String phone;
	private String other_template_text;
	private int days;
	private String template_nameid;
	private String ipd_template;
	private String text;
	private String department="0";
	private String title;
	private String template_text;	
	private String group;
	
	private String parentid;
	
	private String cbcid;
	
	private Vector<Accounts>assesmentList;
	
	private String stock;
	
	private String charge="0";
    private String payby;	
    private String nursingtype_id;
    private String taskname;
    private String notes;
    private String frequency;    
    private String nursingtype;
    private String clientid;
    private String ipdid;
    private String conditionid;
    private String practitionerid;
    
    private ArrayList<Master> investiNames;
    
    private String fullname;
    private String ageandgender;
    
    //dosage variables
    private boolean dos1;
    private boolean dos2;
    private boolean dos3;
    private boolean dos4;
    private boolean dos5;
    private boolean dos6;
    private boolean dos7;
    private boolean dos8;
    private boolean dos9;
    private boolean dos10;
    
    //dosage variables
    private String dosch1;
    private String dosch2;
    private String dosch3;
    private String dosch4;
    private String dosch5;
    private String dosch6;
    private String dosch7;
    private String dosch8;
    private String dosch9;
    private String dosch10;
    private String dosage;
    
    private int dosesize;
    
    private String followupdate;
    
    private String clientname;
    
    
    private String dosevalue1;
    private String dosevalue2;
    private String dosevalue3;
    private String dosevalue4;
    private String dosevalue5;
    private String dosevalue6;
    private String dosevalue7;
    private String dosevalue8;
    private String dosevalue9;
    private String dosevalue10;
    private int invoiceid;
    
    private String dchargetotal;
    
    private String masterchargetype;
    
    private int chargehours;
    private String state;
    private String stateid;
    
    ArrayList<Investigation>selectedInvstList;
    

	public ArrayList<Investigation> getSelectedInvstList() {
		return selectedInvstList;
	}

	public void setSelectedInvstList(ArrayList<Investigation> selectedInvstList) {
		this.selectedInvstList = selectedInvstList;
	}

	public int getChargehours() {
		return chargehours;
	}

	public void setChargehours(int chargehours) {
		this.chargehours = chargehours;
	}

	public String getMasterchargetype() {
		return masterchargetype;
	}

	public void setMasterchargetype(String masterchargetype) {
		this.masterchargetype = masterchargetype;
	}

	public int getInvoiceid() {
		return invoiceid;
	}

	public void setInvoiceid(int invoiceid) {
		this.invoiceid = invoiceid;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getAgeandgender() {
		return ageandgender;
	}

	public void setAgeandgender(String ageandgender) {
		this.ageandgender = ageandgender;
	}

	public boolean isDos1() {
		return dos1;
	}

	public void setDos1(boolean dos1) {
		this.dos1 = dos1;
	}

	public boolean isDos2() {
		return dos2;
	}

	public void setDos2(boolean dos2) {
		this.dos2 = dos2;
	}

	public boolean isDos3() {
		return dos3;
	}

	public void setDos3(boolean dos3) {
		this.dos3 = dos3;
	}

	public boolean isDos4() {
		return dos4;
	}

	public void setDos4(boolean dos4) {
		this.dos4 = dos4;
	}

	public boolean isDos5() {
		return dos5;
	}

	public void setDos5(boolean dos5) {
		this.dos5 = dos5;
	}

	public boolean isDos6() {
		return dos6;
	}

	public void setDos6(boolean dos6) {
		this.dos6 = dos6;
	}

	public boolean isDos7() {
		return dos7;
	}

	public void setDos7(boolean dos7) {
		this.dos7 = dos7;
	}

	public boolean isDos8() {
		return dos8;
	}

	public void setDos8(boolean dos8) {
		this.dos8 = dos8;
	}

	public boolean isDos9() {
		return dos9;
	}

	public void setDos9(boolean dos9) {
		this.dos9 = dos9;
	}

	public boolean isDos10() {
		return dos10;
	}

	public void setDos10(boolean dos10) {
		this.dos10 = dos10;
	}

	public String getDosch1() {
		return dosch1;
	}

	public void setDosch1(String dosch1) {
		this.dosch1 = dosch1;
	}

	public String getDosch2() {
		return dosch2;
	}

	public void setDosch2(String dosch2) {
		this.dosch2 = dosch2;
	}

	public String getDosch3() {
		return dosch3;
	}

	public void setDosch3(String dosch3) {
		this.dosch3 = dosch3;
	}

	public String getDosch4() {
		return dosch4;
	}

	public void setDosch4(String dosch4) {
		this.dosch4 = dosch4;
	}

	public String getDosch5() {
		return dosch5;
	}

	public void setDosch5(String dosch5) {
		this.dosch5 = dosch5;
	}

	public String getDosch6() {
		return dosch6;
	}

	public void setDosch6(String dosch6) {
		this.dosch6 = dosch6;
	}

	public String getDosch7() {
		return dosch7;
	}

	public void setDosch7(String dosch7) {
		this.dosch7 = dosch7;
	}

	public String getDosch8() {
		return dosch8;
	}

	public void setDosch8(String dosch8) {
		this.dosch8 = dosch8;
	}

	public String getDosch9() {
		return dosch9;
	}

	public void setDosch9(String dosch9) {
		this.dosch9 = dosch9;
	}

	public String getDosch10() {
		return dosch10;
	}

	public void setDosch10(String dosch10) {
		this.dosch10 = dosch10;
	}

	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public int getDosesize() {
		return dosesize;
	}

	public void setDosesize(int dosesize) {
		this.dosesize = dosesize;
	}

	public String getFollowupdate() {
		return followupdate;
	}

	public void setFollowupdate(String followupdate) {
		this.followupdate = followupdate;
	}

	public String getClientname() {
		return clientname;
	}

	public void setClientname(String clientname) {
		this.clientname = clientname;
	}

	public String getDosevalue1() {
		return dosevalue1;
	}

	public void setDosevalue1(String dosevalue1) {
		this.dosevalue1 = dosevalue1;
	}

	public String getDosevalue2() {
		return dosevalue2;
	}

	public void setDosevalue2(String dosevalue2) {
		this.dosevalue2 = dosevalue2;
	}

	public String getDosevalue3() {
		return dosevalue3;
	}

	public void setDosevalue3(String dosevalue3) {
		this.dosevalue3 = dosevalue3;
	}

	public String getDosevalue4() {
		return dosevalue4;
	}

	public void setDosevalue4(String dosevalue4) {
		this.dosevalue4 = dosevalue4;
	}

	public String getDosevalue5() {
		return dosevalue5;
	}

	public void setDosevalue5(String dosevalue5) {
		this.dosevalue5 = dosevalue5;
	}

	public String getDosevalue6() {
		return dosevalue6;
	}

	public void setDosevalue6(String dosevalue6) {
		this.dosevalue6 = dosevalue6;
	}

	public String getDosevalue7() {
		return dosevalue7;
	}

	public void setDosevalue7(String dosevalue7) {
		this.dosevalue7 = dosevalue7;
	}

	public String getDosevalue8() {
		return dosevalue8;
	}

	public void setDosevalue8(String dosevalue8) {
		this.dosevalue8 = dosevalue8;
	}

	public String getDosevalue9() {
		return dosevalue9;
	}

	public void setDosevalue9(String dosevalue9) {
		this.dosevalue9 = dosevalue9;
	}

	public String getDosevalue10() {
		return dosevalue10;
	}

	public void setDosevalue10(String dosevalue10) {
		this.dosevalue10 = dosevalue10;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getIpdid() {
		return ipdid;
	}

	public void setIpdid(String ipdid) {
		this.ipdid = ipdid;
	}

	public String getConditionid() {
		return conditionid;
	}

	public void setConditionid(String conditionid) {
		this.conditionid = conditionid;
	}

	public String getPractitionerid() {
		return practitionerid;
	}

	public void setPractitionerid(String practitionerid) {
		this.practitionerid = practitionerid;
	}

	public String getClientid() {
		return clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
	}

	public String getNursingtype() {
		return nursingtype;
	}

	public void setNursingtype(String nursingtype) {
		this.nursingtype = nursingtype;
	}

	public String getTaskname() {
		return taskname;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getNursingtype_id() {
		return nursingtype_id;
	}

	public void setNursingtype_id(String nursingtype_id) {
		this.nursingtype_id = nursingtype_id;
	}

	public String getPayby() {
		return payby;
	}

	public void setPayby(String payby) {
		this.payby = payby;
	}

	public String getWardid() {
		return wardid;
	}

	public void setWardid(String wardid) {
		this.wardid = wardid;
	}

	public String getCharge() {
		return charge;
	}

	public void setCharge(String charge) {
		this.charge = charge;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public Vector<Accounts> getAssesmentList() {
		return assesmentList;
	}

	public void setAssesmentList(Vector<Accounts> assesmentList) {
		this.assesmentList = assesmentList;
	}

	public String getCbcid() {
		return cbcid;
	}

	public void setCbcid(String cbcid) {
		this.cbcid = cbcid;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getOther_template_text() {
		return other_template_text;
	}

	public void setOther_template_text(String other_template_text) {
		this.other_template_text = other_template_text;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSpecimen() {
		return specimen;
	}

	public void setSpecimen(String specimen) {
		this.specimen = specimen;
	}

	public String getReport_type() {
		return report_type;
	}

	public void setReport_type(String report_type) {
		this.report_type = report_type;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getNormal_value() {
		return normal_value;
	}

	public void setNormal_value(String normal_value) {
		this.normal_value = normal_value;
	}

	public String getInvestigation_type_id() {
		return investigation_type_id;
	}

	public void setInvestigation_type_id(String investigation_type_id) {
		this.investigation_type_id = investigation_type_id;
	}

	ArrayList<Master> specializationList;

	private String discipline;

	private String name;

	private String countryid;

	public String getCountryid() {
		return countryid;
	}

	public void setCountryid(String countryid) {
		this.countryid = countryid;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	private String countryName;
	private int isbedcharge=0;
	
	
	public int getIsbedcharge() {
		return isbedcharge;
	}

	public void setIsbedcharge(int isbedcharge) {
		this.isbedcharge = isbedcharge;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Master> getSpecializationList() {
		return specializationList;
	}

	public void setSpecializationList(ArrayList<Master> specializationList) {
		this.specializationList = specializationList;
	}

	public String getDiscipline() {
		return discipline;
	}

	public void setDiscipline(String discipline) {
		this.discipline = discipline;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getDiscipline_id() {
		return discipline_id;
	}

	public void setDiscipline_id(String discipline_id) {
		this.discipline_id = discipline_id;
	}

	public String getJobtitlegroup() {
		return jobtitlegroup;
	}

	public void setJobtitlegroup(String jobtitlegroup) {
		this.jobtitlegroup = jobtitlegroup;
	}

	public String getJobtitlegroup_id() {
		return jobtitlegroup_id;
	}

	public void setJobtitlegroup_id(String jobtitlegroup_id) {
		this.jobtitlegroup_id = jobtitlegroup_id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTemplate_nameid() {
		return template_nameid;
	}

	public void setTemplate_nameid(String template_nameid) {
		this.template_nameid = template_nameid;
	}

	public String getIpd_template() {
		return ipd_template;
	}

	public void setIpd_template(String ipd_template) {
		this.ipd_template = ipd_template;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getTemplate_text() {
		return template_text;
	}

	public void setTemplate_text(String template_text) {
		this.template_text = template_text;
	}

	public ArrayList<Investigation> getTestNameList() {
		return testNameList;
	}

	public void setTestNameList(ArrayList<Investigation> testNameList) {
		this.testNameList = testNameList;
	}

	public int getTemplateid() {
		return templateid;
	}

	public void setTemplateid(int templateid) {
		this.templateid = templateid;
	}

	public boolean isProcedure() {
		return procedure;
	}

	public void setProcedure(boolean procedure) {
		this.procedure = procedure;
	}

	public ArrayList<Master> getInvestiNames() {
		return investiNames;
	}

	public void setInvestiNames(ArrayList<Master> investiNames) {
		this.investiNames = investiNames;
	}

	public ArrayList<UserProfile> getInvestigationUsers() {
		return investigationUsers;
	}

	public void setInvestigationUsers(ArrayList<UserProfile> investigationUsers) {
		this.investigationUsers = investigationUsers;
	}

	public String getInvsparentid() {
		return invsparentid;
	}

	public void setInvsparentid(String invsparentid) {
		this.invsparentid = invsparentid;
	}

	public int getAppointmentid() {
		return appointmentid;
	}

	public void setAppointmentid(int appointmentid) {
		this.appointmentid = appointmentid;
	}

	public String getInvsname() {
		return invsname;
	}

	public void setInvsname(String invsname) {
		this.invsname = invsname;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStateid() {
		return stateid;
	}

	public void setStateid(String stateid) {
		this.stateid = stateid;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getGenericname() {
		return genericname;
	}

	public void setGenericname(String genericname) {
		this.genericname = genericname;
	}

	public boolean isRoundcharge() {
		return roundcharge;
	}

	public void setRoundcharge(boolean roundcharge) {
		this.roundcharge = roundcharge;
	}

	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public String getDchargetotal() {
		return dchargetotal;
	}

	public void setDchargetotal(String dchargetotal) {
		this.dchargetotal = dchargetotal;
	}

	public String getChargeid() {
		return chargeid;
	}

	public void setChargeid(String chargeid) {
		this.chargeid = chargeid;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getRoom_no() {
		return room_no;
	}

	public void setRoom_no(String room_no) {
		this.room_no = room_no;
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

	public int getStrip() {
		return strip;
	}

	public void setStrip(int strip) {
		this.strip = strip;
	}

	public int getIhpatient() {
		return ihpatient;
	}

	public void setIhpatient(int ihpatient) {
		this.ihpatient = ihpatient;
	}

	public int getIsstrip() {
		return isstrip;
	}

	public void setIsstrip(int isstrip) {
		this.isstrip = isstrip;
	}

	public String getMin_value_m() {
		return min_value_m;
	}

	public void setMin_value_m(String min_value_m) {
		this.min_value_m = min_value_m;
	}

	public String getMax_value_m() {
		return max_value_m;
	}

	public void setMax_value_m(String max_value_m) {
		this.max_value_m = max_value_m;
	}

	public String getMin_value_f() {
		return min_value_f;
	}

	public void setMin_value_f(String min_value_f) {
		this.min_value_f = min_value_f;
	}

	public String getMax_value_f() {
		return max_value_f;
	}

	public void setMax_value_f(String max_value_f) {
		this.max_value_f = max_value_f;
	}

	public String getVital_master_id() {
		return vital_master_id;
	}

	public void setVital_master_id(String vital_master_id) {
		this.vital_master_id = vital_master_id;
	}

	public String getFinding() {
		return finding;
	}

	public void setFinding(String finding) {
		this.finding = finding;
	}

	public String getKeyname() {
		return keyname;
	}

	public void setKeyname(String keyname) {
		this.keyname = keyname;
	}

	public ArrayList<Master> getVitalDataList() {
		return vitalDataList;
	}

	public void setVitalDataList(ArrayList<Master> vitalDataList) {
		this.vitalDataList = vitalDataList;
	}

	public String getDosetime1() {
		return dosetime1;
	}

	public void setDosetime1(String dosetime1) {
		this.dosetime1 = dosetime1;
	}

	public String getDosetime2() {
		return dosetime2;
	}

	public void setDosetime2(String dosetime2) {
		this.dosetime2 = dosetime2;
	}

	public String getDosetime3() {
		return dosetime3;
	}

	public void setDosetime3(String dosetime3) {
		this.dosetime3 = dosetime3;
	}

	public String getDosetime4() {
		return dosetime4;
	}

	public void setDosetime4(String dosetime4) {
		this.dosetime4 = dosetime4;
	}

	public String getDosetime5() {
		return dosetime5;
	}

	public void setDosetime5(String dosetime5) {
		this.dosetime5 = dosetime5;
	}

	public String getVital_type() {
		return vital_type;
	}

	public void setVital_type(String vital_type) {
		this.vital_type = vital_type;
	}

	public String getVital_type_name() {
		return vital_type_name;
	}

	public void setVital_type_name(String vital_type_name) {
		this.vital_type_name = vital_type_name;
	}

	public String getUserFileFileName() {
		return userFileFileName;
	}

	public void setUserFileFileName(String userFileFileName) {
		this.userFileFileName = userFileFileName;
	}

	private int book_chapter_id;
	private int book_chapter_parentid;
	private String book_chapter_name;
	private String book_chapter_link;
	private String book_chapter_discription;
	public int getBook_chapter_id() {
		return book_chapter_id;
	}

	public void setBook_chapter_id(int book_chapter_id) {
		this.book_chapter_id = book_chapter_id;
	}

	public int getBook_chapter_parentid() {
		return book_chapter_parentid;
	}

	public void setBook_chapter_parentid(int book_chapter_parentid) {
		this.book_chapter_parentid = book_chapter_parentid;
	}

	public String getBook_chapter_name() {
		return book_chapter_name;
	}

	public void setBook_chapter_name(String book_chapter_name) {
		this.book_chapter_name = book_chapter_name;
	}

	public String getBook_chapter_link() {
		return book_chapter_link;
	}

	public void setBook_chapter_link(String book_chapter_link) {
		this.book_chapter_link = book_chapter_link;
	}

	public String getBook_chapter_discription() {
		return book_chapter_discription;
	}

	public void setBook_chapter_discription(String book_chapter_discription) {
		this.book_chapter_discription = book_chapter_discription;
	}
	public String getOutsource_data_name() {
		return outsource_data_name;
	}

	public void setOutsource_data_name(String outsource_data_name) {
		this.outsource_data_name = outsource_data_name;
	}

	

	public int getOutsource_data_id() {
		return outsource_data_id;
	}

	public void setOutsource_data_id(int outsource_data_id) {
		this.outsource_data_id = outsource_data_id;
	}

	public int getOutsource_id() {
		return outsource_id;
	}

	public void setOutsource_id(int outsource_id) {
		this.outsource_id = outsource_id;
	}

	public String getOutsource_name() {
		return outsource_name;
	}

	public void setOutsource_name(String outsource_name) {
		this.outsource_name = outsource_name;
	}

	public String getSharing_type() {
		return sharing_type;
	}

	public void setSharing_type(String sharing_type) {
		this.sharing_type = sharing_type;
	}

	public String getAmmount() {
		return ammount;
	}

	public void setAmmount(String ammount) {
		this.ammount = ammount;
	}

	public String getInvestigstion_id() {
		return investigstion_id;
	}

	public void setInvestigstion_id(String investigstion_id) {
		this.investigstion_id = investigstion_id;
	}

	public String getInvestigation_name() {
		return investigation_name;
	}

	public void setInvestigation_name(String investigation_name) {
		this.investigation_name = investigation_name;
	}

	public String getShowall() {
		return showall;
	}

	public void setShowall(String showall) {
		this.showall = showall;
	}

	public String getIsopd() {
		return isopd;
	}

	public void setIsopd(String isopd) {
		this.isopd = isopd;
	}

	public String getIsipd() {
		return isipd;
	}

	public void setIsipd(String isipd) {
		this.isipd = isipd;
	}

	private int outsource_data_id;
	private String outsource_data_name;
	private int outsource_id;
	private String outsource_name; 
	private String ammount ;
	private String sharing_type;
	private String investigstion_id;
	private String investigation_name;
	private String showall;
	private String  isipd;
	private String  isopd;
	private Accounts assesmentlist1;
	private ArrayList<Master> assesmentlist;
	private int arvnew,arvold,newpatient,oldpatient,totalcount;
	//lokesh  8/1/18
		private String vacinname,vacine_dependson,vacine_iscompulsary,vacine_excludes,vacine_parent,vacine_info;

		public String getVacinname() {
			return vacinname;
		}

		public void setVacinname(String vacinname) {
			this.vacinname = vacinname;
		}

		public String getVacine_dependson() {
			return vacine_dependson;
		}

		public void setVacine_dependson(String vacine_dependson) {
			this.vacine_dependson = vacine_dependson;
		}

		public String getVacine_iscompulsary() {
			return vacine_iscompulsary;
		}

		public void setVacine_iscompulsary(String vacine_iscompulsary) {
			this.vacine_iscompulsary = vacine_iscompulsary;
		}

		public String getVacine_excludes() {
			return vacine_excludes;
		}

		public void setVacine_excludes(String vacine_excludes) {
			this.vacine_excludes = vacine_excludes;
		}

		public String getVacine_parent() {
			return vacine_parent;
		}

		public void setVacine_parent(String vacine_parent) {
			this.vacine_parent = vacine_parent;
		}

		public String getVacine_info() {
			return vacine_info;
		}
	private String scheduledate;
		public void setVacine_info(String vacine_info) {
			this.vacine_info = vacine_info;
		}
		public String getVacine_scheduledon() {
			return vacine_scheduledon;
		}

		public void setVacine_scheduledon(String vacine_scheduledon) {
			this.vacine_scheduledon = vacine_scheduledon;
		}

		public String getScheduledate() {
			return scheduledate;
		}

		public void setScheduledate(String scheduledate) {
			this.scheduledate = scheduledate;
		}

		public String getVacine_givendate() {
			return vacine_givendate;
		}

		public void setVacine_givendate(String vacine_givendate) {
			this.vacine_givendate = vacine_givendate;
		}

		public String getVacine_remark() {
			return vacine_remark;
		}

		public void setVacine_remark(String vacine_remark) {
			this.vacine_remark = vacine_remark;
		}

		public String getVacine_period() {
			return vacine_period;
		}

		public void setVacine_period(String vacine_period) {
			this.vacine_period = vacine_period;
		}

		public String getDependent_name() {
			return dependent_name;
		}

		public void setDependent_name(String dependent_name) {
			this.dependent_name = dependent_name;
		}

	

		public boolean isDependant_given() {
			return dependant_given;
		}

		public void setDependant_given(boolean dependant_given) {
			this.dependant_given = dependant_given;
		}

		public String getDuration() {
			return duration;
		}

		public void setDuration(String duration) {
			this.duration = duration;
		}

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public int getVacine_parent_id() {
			return vacine_parent_id;
		}

		public void setVacine_parent_id(int vacine_parent_id) {
			this.vacine_parent_id = vacine_parent_id;
		}

		public int getMasterid() {
			return masterid;
		}

		public void setMasterid(int masterid) {
			this.masterid = masterid;
		}

		public boolean isDiscstatus() {
			return discstatus;
		}

		public void setDiscstatus(boolean discstatus) {
			this.discstatus = discstatus;
		}

		private String vacine_scheduledon;
		private String vacine_givendate;
		private String vacine_remark;
		private String vacine_period;
		private String dependent_name;
		private boolean dependant_given;
		private String duration;
		private int type;
		private int vacine_parent_id;
		private int masterid;
		private boolean discstatus;
		private String remark;
		private String remarkhindi;
		private String remarkmarathi;


		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

		public String getRemarkhindi() {
			return remarkhindi;
		}

		public void setRemarkhindi(String remarkhindi) {
			this.remarkhindi = remarkhindi;
		}

		public String getRemarkmarathi() {
			return remarkmarathi;
		}

		public void setRemarkmarathi(String remarkmarathi) {
			this.remarkmarathi = remarkmarathi;
		}
		
		public boolean isConsultant_compulsay() {
			return consultant_compulsay;
		}

		public void setConsultant_compulsay(boolean consultant_compulsay) {
			this.consultant_compulsay = consultant_compulsay;
		}

		public boolean isThird_party() {
			return third_party;
		}

		public void setThird_party(boolean third_party) {
			this.third_party = third_party;
		}

		public String getDefaultremark() {
			return defaultremark;
		}

		public void setDefaultremark(String defaultremark) {
			this.defaultremark = defaultremark;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public int getHidecode() {
			return hidecode;
		}

		public void setHidecode(int hidecode) {
			this.hidecode = hidecode;
		}

		public boolean isStdchargests() {
			return stdchargests;
		}

		public void setStdchargests(boolean stdchargests) {
			this.stdchargests = stdchargests;
		}

		public double getMedpkgamt() {
			return medpkgamt;
		}

		public void setMedpkgamt(double medpkgamt) {
			this.medpkgamt = medpkgamt;
		}

		public String getPercent() {
			return percent;
		}

		public void setPercent(String percent) {
			this.percent = percent;
		}

		public int getIsdeleted() {
			return isdeleted;
		}

		public void setIsdeleted(int isdeleted) {
			this.isdeleted = isdeleted;
		}

		public double getTaxtype1() {
			return taxtype1;
		}

		public void setTaxtype1(double taxtype1) {
			this.taxtype1 = taxtype1;
		}

		public double getTaxtype2() {
			return taxtype2;
		}

		public void setTaxtype2(double taxtype2) {
			this.taxtype2 = taxtype2;
		}

		public double getTaxtype3() {
			return taxtype3;
		}

		public void setTaxtype3(double taxtype3) {
			this.taxtype3 = taxtype3;
		}

		public int getDependson() {
			return dependson;
		}

		public void setDependson(int dependson) {
			this.dependson = dependson;
		}

		public int getDeppendsonDays() {
			return deppendsonDays;
		}

		public void setDeppendsonDays(int deppendsonDays) {
			this.deppendsonDays = deppendsonDays;
		}

		public String getDependsonscheedule() {
			return dependsonscheedule;
		}

		public void setDependsonscheedule(String dependsonscheedule) {
			this.dependsonscheedule = dependsonscheedule;
		}

		public boolean isBghhead() {
			return bghhead;
		}

		public void setBghhead(boolean bghhead) {
			this.bghhead = bghhead;
		}

		public int getGendervaccine() {
			return gendervaccine;
		}

		public void setGendervaccine(int gendervaccine) {
			this.gendervaccine = gendervaccine;
		}

		public String getRegional() {
			return regional;
		}

		public void setRegional(String regional) {
			this.regional = regional;
		}

		private boolean consultant_compulsay;
		private boolean third_party;
		private String code;
		private boolean stdchargests;
		private double medpkgamt;
		private String percent;
		private int isdeleted;
		private double taxtype1,taxtype2,taxtype3;
		private int dependson;
		private int deppendsonDays;
		private String dependsonscheedule;
		private int gendervaccine;
		private String sms, sms_type,sms_itype;












		public String getSms() {
			return sms;
		}

		public void setSms(String sms) {
			this.sms = sms;
		}

		public String getSms_type() {
			return sms_type;
		}

		public void setSms_type(String sms_type) {
			this.sms_type = sms_type;
		}

		public String getSms_itype() {
			return sms_itype;
		}

		public void setSms_itype(String sms_itype) {
			this.sms_itype = sms_itype;
		}
		public String getTpid() {
			return tpid;
		}

		public void setTpid(String tpid) {
			this.tpid = tpid;
		}

		public boolean isFromOldWard() {
			return fromOldWard;
		}

		public void setFromOldWard(boolean fromOldWard) {
			this.fromOldWard = fromOldWard;
		}

		public String getVendor() {
			return vendor;
		}

		public void setVendor(String vendor) {
			this.vendor = vendor;
		}

		public String getInvsttype() {
			return invsttype;
		}

		public void setInvsttype(String invsttype) {
			this.invsttype = invsttype;
		}

		public double getTotaldiscount() {
			return totaldiscount;
		}

		public void setTotaldiscount(double totaldiscount) {
			this.totaldiscount = totaldiscount;
		}

		

	

		public ArrayList<Accounts> getInnerPaymentList() {
			return innerPaymentList;
		}

		public void setInnerPaymentList(ArrayList<Accounts> innerPaymentList) {
			this.innerPaymentList = innerPaymentList;
		}

		public String getTodate() {
			return todate;
		}

		public void setTodate(String todate) {
			this.todate = todate;
		}

		public String getGivenTo() {
			return givenTo;
		}

		public void setGivenTo(String givenTo) {
			this.givenTo = givenTo;
		}

		public String getIsfromdcard() {
			return isfromdcard;
		}

		public void setIsfromdcard(String isfromdcard) {
			this.isfromdcard = isfromdcard;
		}

		public ArrayList<Master> getPrntpwardwisechargelist() {
			return prntpwardwisechargelist;
		}

		public void setPrntpwardwisechargelist(ArrayList<Master> prntpwardwisechargelist) {
			this.prntpwardwisechargelist = prntpwardwisechargelist;
		}

		public boolean isIsprntchrg() {
			return isprntchrg;
		}

		public void setIsprntchrg(boolean isprntchrg) {
			this.isprntchrg = isprntchrg;
		}

		public ArrayList<Master> getMnsBedList() {
			return mnsBedList;
		}

		public void setMnsBedList(ArrayList<Master> mnsBedList) {
			this.mnsBedList = mnsBedList;
		}

		public ArrayList<Bed> getBedList() {
			return bedList;
		}

		public void setBedList(ArrayList<Bed> bedList) {
			this.bedList = bedList;
		}

	

		public int getBookedBedCount() {
			return bookedBedCount;
		}

		public void setBookedBedCount(int bookedBedCount) {
			this.bookedBedCount = bookedBedCount;
		}

		public String getCreditDebit() {
			return creditDebit;
		}

		public void setCreditDebit(String creditDebit) {
			this.creditDebit = creditDebit;
		}

		public String getShowdeptlist() {
			return showdeptlist;
		}

		public void setShowdeptlist(String showdeptlist) {
			this.showdeptlist = showdeptlist;
		}

		private String tpid;
		private boolean fromOldWard;
		private ArrayList<Accounts> innerPaymentList;
		private String isfromdcard;
		
		private ArrayList<Bed> bedList;
		private ArrayList<Master> mnsBedList;
		private int bookedBedCount;
		private String creditDebit;
		private String payroll;
}

