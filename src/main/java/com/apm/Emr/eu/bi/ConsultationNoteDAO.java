package com.apm.Emr.eu.bi;

import java.util.ArrayList;

import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.Emr.eu.entity.Emr;
import com.apm.Master.eu.entity.Master;
import com.apm.TreatmentEpisode.eu.entity.TreatmentEpisode;
import com.apm.common.web.common.helper.LoginInfo;

public interface ConsultationNoteDAO {

	ArrayList<Client> getClientList(int practId);

	ArrayList<Client> getConditionList(int clientId);

	ArrayList<Emr> getConsultationNoteList(String diaryUser, String clientname,
			String condition);

	int saveNote(String practId, String clientId, String conditionId,
			String consNote);

	String getNote(String id);

	int updateNote(String practId, String clientId, String conditionId,
			String consNote, String id);

	int deleteNote(String id);

	String getCondtion(int apmtId);

	ArrayList<Client> getClientList();

	ArrayList<Client> getConditionList();

	int deleteAllConsultationNote(String apmtId);

	ArrayList<Emr> getConsultationNoteList(String practionerId,
			String patientid, String condition, int id);

	int getconditionID(int apmtId);

	ArrayList<TreatmentEpisode> getTreatmentEpisodeList(String clientId);

	String getTreatmentEpisodeid(String apmtId);

	TreatmentEpisode getTreatmentEpisodeDischargeData(String treatmentEpisodeid);

	double getInvoiceDebitTotal(String whopay, String tpid, String patientid);

	double getTotalPaymentDone(String whopay, String tpid, String patientid);

	ArrayList<Emr> getPrintConsultationNoteList(String practionerId,
			String clientId, String condition, String date);

	String getIpdTreatmentEpisodeid(String clientname);

	ArrayList<Emr> printEditConsultationNote(String editid);

	ArrayList<Client> getClientListEmr(int parseInt, String clientId,String oldpractid,String apmtid);

	ArrayList<Client> getpbodyclient(String clientname);

	ArrayList<Emr> getConsultationNoteListwithDate(String practionerId, String clientId, String condition,
			String fromdate, String todate, String dept, String apmtid, LoginInfo loginInfo);

	ArrayList<Emr> getConsultationNoteListwithDateInv(String practionerId, String clientId, String condition,
			String fromdate, String todate, String dept);

	ArrayList<Emr> getConsultationNoteListwithDatePrisc(String practionerId, String clientId, String condition,
			String fromdate, String todate, String dept);

	ArrayList<Emr> getConsultationNoteListInvestigation(String practionerId, String clientId, String condition);

	ArrayList<Emr> printEditConsultationNoteInvestigation(String editid);

	ArrayList<Emr> getPrintConsultationNoteListInvestigation(String practionerId, String clientId, String condition,
			String date);

	ArrayList<Emr> getConsultationNoteListPriscription(String practionerId, String clientId, String condition);

	ArrayList<Emr> printEditConsultationNotePriscription(String editid);

	ArrayList<Emr> getPrintConsultationNoteListPriscription(String practionerId, String clientId, String condition,
			String date);

	ArrayList<Master> getodmrprocedureonly();

	

	


	

}
