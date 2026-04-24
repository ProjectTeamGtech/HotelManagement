package com.apm.DiaryManagement.eu.bi;

import java.util.Vector;
import java.util.Vector;

import com.apm.Appointment.eu.entity.Appointment;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.CompleteAppointment;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.DiaryManagement.eu.entity.OpdConsult;
import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.Emr.eu.entity.Investigation;
import com.apm.common.web.common.helper.LoginInfo;

public interface OPDConsultDAO {

	Vector<OpdConsult> getDoctorwithsepecialitylist();

	Vector<OpdConsult> getFakepatientlist(String string);

	String getspecialityIdbydocid(String docid);

	OpdConsult getOriesdata(String docid, String specialityid);

	Vector<OpdConsult> getoriesdatalistbyid(int patientid);

	NotAvailableSlot getNewOpdDiaryUserData(String docid, String commencing);

	int saveAppointment(String docid, String commencing, NotAvailableSlot notAvailableSlot, OpdConsult opdConsult, String specialityid);

	int updateOriesdata(OpdConsult opdConsult2, OpdConsult opdConsult1, int opdid, String docid, String regdate);

	OpdConsult getConsultationdata(int opdid, String dbname);

	int insertFakeConsultationNote(OpdConsult opddata);

	CompleteAppointment getOriesmedicinebillbyId(int patientid, int billid, String dbname, String regdate, int id);

	int updateOriesmedicinebill(int id, OpdConsult opdConsult1, String date, String docid, int clientid, String newadmissiondate, String opdipdtype, String regdate, String newpharmdate, String dbname);

	int updateOriesmedicinecharges(int id, OpdConsult opdConsult1, String date, String docid, String docname, String newadmissiondate, String opdipdtype, String regdate, String dbname);

	int updateOriesmedicinepayment(int id, OpdConsult opdConsult1, String date, String newadmissiondate, String opdipdtype, String regdate, String dbname);

	//OpdConsult getOriesmedicinebillDatabyId(int patientid, String string);

	int insertFakeMedicinebill(CompleteAppointment appointment);

	Vector<Priscription> getMedicineChargeslistbyId(int id, int invoiceid, String dbname, String regdate, int pid);

	int insertFakeMedicineCharges(Priscription priscription, int billno);

	Vector<CompleteAppointment> getMedicinePaymentlistbyid(int id, int billno, String dbname, String regdate, int pid);

	int insertFakeMedicinePayment(CompleteAppointment completeAppointment, int billno, int paymentseqno);

	int getPharmacyPaymentSeqNo(String location);

	int updateMedicineQty(int saleqty, String product_id, int plusminus);

	int updatePatientOpdStatusbyId(int patientid, LoginInfo loginInfo);

	Vector<OpdConsult> getoriesdatalistDatabyid(int patientid, String date, String opdipdtype, String dischargedate, String newadmissiondate, String dischargedate2, OpdConsult opdConsult1, int clientid, String dbname);

	OpdConsult getOrisesAvailableslot(String docid, String specialityid, boolean isot, String dbname);

	Vector<OpdConsult> getoriesAvailableslotlistbyid(int patientid, String string, String specialityid, String dbname);

	int updateOriesAvailableslotdata(OpdConsult opdConsult2, OpdConsult opdConsult1, int opdid, String docid,
			String regdate, String docname, String dbname);

	Vector<Investigation> getParentInvestigationlist(OpdConsult opdConsult2, String regdate, String opdipdtype, String newadmissiondate, String dischargedate2, Investigation inv, String dbname);

	int insertParentInvestigation(Investigation investigation, int id, String docid);

	Vector<Investigation> getClientInvestigationList(int parentid, String regdate, String date, String dbname);

	int insertClientInvestigation(int parentid, Investigation investigation2, int clientid, String docid);

	int updateOriesParentInvestigation(OpdConsult opdConsult2, String regdate, String newadmissiondate, String dischargedate2, String opdipdtype, String dischargedate, String newinvdate, Investigation inv, String dbname);

	int updateOriesClientInvestigation(OpdConsult opdConsult2, String regdate, int parentid, String opdipdtype, String date, String dbname);

	Vector<OpdConsult> getPriscriptiondatelist(int patientid, String date, String opdipdtype, int ipdid, String dbname, int fakeipdid);

	int updateOriesParentPriscription(OpdConsult opdConsult2, String regdate, String time, String pdatetime, String opdipdtype, String dischargedate, String newpriscdate, OpdConsult priscridate, String dbname);

	Vector<Priscription> getParentPriscriptionList(OpdConsult opdConsult2, String regdate, String opdipdtype, String dischargedate, String newadmissiondate, String dischargedate2, OpdConsult priscridate, String dbname);

	int insertParentPriscription(Priscription priscription, int opdid, int id, String docid, OpdConsult opdConsult2, String opdipdtype);

	int updateOriesClientPriscription(Priscription priscription, OpdConsult opdConsult2, String dbname);

	Vector<Priscription> getClientPriscriptionList(Priscription priscription, String dbname);

	int insertClientPriscription(int parentpriscid, Priscription priscription2, int id, String docid);

	int updateParentPrisc(Priscription priscription, OpdConsult opdConsult2, String opdipdtype, String dischargedate, String dbname);

	Vector<Priscription> getParentPrictablelist(OpdConsult opdConsult2, Priscription priscription, String opdipdtype, String dbname);

	int insertParentprisctable(int id, String docid, int parentpriscid, Priscription priscription3, OpdConsult opdConsult2, String opdipdtype, String lastmodified);

	Vector<Priscription> getChildDataList(OpdConsult opdConsult2, Priscription priscription2, String dbname, int parpriscid1);

	int insertChildPriscTable(int parentpriscid, int parpriscid1, int childid, Priscription priscription4, int id);

	Vector<Investigation> getOrisesParentdateList(OpdConsult opdConsult2, String opdipdtype, String dbname, int fipdid);

	Vector<Client> getFakeData(String specialityid, String commencing, String ipdpatientcount, LoginInfo loginInfo);

	String getadischrgedate(String atreatmentepid, String dbname);

	OpdConsult getOrisesAvailableslot1(String docid, String specialityid, int flag, String dbname);

	Vector<OpdConsult> getoriesAvailableslotlistbyid1(int patientid, String ipdadmissiondte, String dbname, boolean isfollowup);

	Vector<OpdConsult> getOriesOtlist(String auadmissionid, String dbname);

	int updateOriesOtdata(int pclientid, String cdocid, String cdiaryuserid, String cclientid, int id, String dbname);

	Vector<OpdConsult> getOriesOtavailableList(String cclientid, int id, String dbname, String cotdate);

	int insertOtAvailabledata(String cclientid, OpdConsult opdConsult2, String cadmissionid, String cdiaryuserid, String patientnm);

	String getOriesuserlastname(String diaryuserid, String dbname);

	OpdConsult getDiagnosisdata(String specialityid, String dbname);

	int updateTaken(int id, String dbname);

	int saveOpddiagnosis(OpdConsult opd, int newpatientid, String specialityid);

	boolean checkPatientexist(int newpatientid, String specialityid);

	String getDatabasenamebyclinicid(String clinicid);

	OpdConsult getConsultationFormDatabyapmtid(OpdConsult opdConsult2, String dbname);

	int insertinConsult_form(OpdConsult consultform, int opdid, int id);

	int insertBamsConditionids(int opdid, String str);

	String generateOPDSequenceNewFormat();

	String generateLMHOPDSeq(String string);

	String generateLMHIPDSeqForMBBS(String string);

}
