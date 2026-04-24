package com.apm.TreatmentEpisode.eu.bi;

import java.util.ArrayList;

import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.TreatmentEpisode.eu.entity.TreatmentEpisode;
import com.apm.common.utils.Pagination;

public interface TreatmentEpisodeDAO {

	int saveTreatmentEpisode(TreatmentEpisode treatmentEpisode,String lastmodified);

	ArrayList<TreatmentEpisode> getTreatmentEpisodeList(String clientId,String payby);

	TreatmentEpisode getTreatmentEpisodeDetails(String tratmentepisodeid,String selectedAppointmentid);

	int updateConsultationLimit(String sessions, String treatmentepisodeid);

	TreatmentEpisode getParticularTreatEpiDetails(String id);

	ArrayList<TreatmentEpisode> getSourceOfReferralList();

	int updateTreatmentEpisode(TreatmentEpisode treatmentEpisode, int id);

	int deleteTreatmentEpisode(String id);

	boolean isTreatmentNameExist(String treatmentName, String client);

	ArrayList<TreatmentEpisode> getTreatmentEpisodeList();

	TreatmentEpisode getLastTreatmentEpi(String clientId);

	int getTotalTreatmentEpisodeCount(String clientId);

	ArrayList<TreatmentEpisode> getAllTreatmentEpisodeList(String clientId, Pagination pagination);

	TreatmentEpisode getTreatmentEpisodeSessionDetails(String tratmentepisodeid);

	int updateSessions(String id);

	

	ArrayList<TreatmentEpisode> getSessionsId(String id, String currentdate);

	int updateSessions(int id, int usedSession);

	NotAvailableSlot getLastAppointmentData(String clientid);

	String getTPNotes(String selectedAppointmentid);

	String getTreatmentEpisodeId(String apmtId);

	int editsave(TreatmentEpisode treatmentEpisode, String selectedid);

	ArrayList<NotAvailableSlot> getReportStatusList(String treatmentepisodeid);

	TreatmentEpisode getTreatmentEpisodeData(String treatmentepisodeid);

	int updateReportStatus(boolean usent, String sentDate, String sentNote,
			String appointmentid);

	int updateSentReport(String treatmentepisodeid, boolean tpreportsent,String date);

	ArrayList<TreatmentEpisode> getTreatmentEpisodeList(String clientId);

	NotAvailableSlot getAppointmentData(String appointmentid);

	NotAvailableSlot getWhoPay(String selectedAppointmentid);

	String getClientNotes(String clientId);

	ArrayList<TreatmentEpisode> getIpdTreatmentEpisodeList(String clientid,
			String payby);

	String getDischargeAdvoice(String treatmentid);
	ArrayList<TreatmentEpisode> getSelectedTreatmentEpisode(
			String treatmentepisodeid);

	String getRefEndDate(String treatmentEpisodeId);

	String getRefFromDate(String treatmentId);
	
	 String getTreatmentTemplateData(String id);

	String getDepartmentBydocId(String docid);
}
