package com.apm.Log.eu.bi;

import java.util.ArrayList;

import com.apm.Log.eu.entity.EmailLetterLog;

public interface EmailLetterLogDAO {

	int saveEmailLetterLogDetails(String from, String to, String heading,EmailLetterLog emailLetterLog, String status,String procurementid);

	ArrayList<EmailLetterLog> getEmailHistoryList(String clientid,String type);

	String getclientIdByInvoiceId(int invoiceid);

	String getClientIdByAptmentId(int appointmentid);

	ArrayList<EmailLetterLog> getLetterHistoryList(String client);

	int updateAdEmailStatus(EmailLetterLog emailLetterLog);

	

}
