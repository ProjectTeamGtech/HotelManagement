package com.apm.ThirdParties.eu.bi;

import java.util.ArrayList;

import com.apm.Accounts.eu.entity.Accounts;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.ThirdParties.eu.entity.OutstandingReport;
import com.apm.common.utils.Pagination;

public interface OutstandingReportDAO {

	ArrayList<OutstandingReport> getOutStandingReportList(int clinicId, Pagination pagination);

	ArrayList<OutstandingReport> getOutStandingReportActionList(int id,
			String id2, Pagination pagination);

	int saveEmailSendDetails(OutstandingReport outstandingReport);

	int saveSmsSendDetails(OutstandingReport outstandingReport);

	int saveCallRecordDetails(OutstandingReport outstandingReport);

	int getTotalOutStandingCount(int id);

	int getTotalOutStandingActionCount(int id, String id2);

	ArrayList<OutstandingReport> getOutStandingReportOfClientList(int id,
			Pagination pagination, String payby,String clientid,String fromdate,String todate);

	ArrayList<OutstandingReport> getOutstandingReportOfTPList(int id,
			Pagination pagination, String payby,String thirdpartyid,String fromdate,String todate);

	ArrayList<OutstandingReport> getPendingInvoiceList(String clientId,
			String tpId, int creditDuration);

	int savePayment(String tpId, String clientId, String modeofPayment,
			String invoiceNo, String payamount,String date);

	int saveTPPayment(String tpId, String clientId, String modeofPayment,
			String paymentTotal);

	ArrayList<OutstandingReport> getPendingClientSelfInvoiceList(String clientId);

	int getCreditDurationDate(String tpId);

	//ArrayList<Accounts> getSelectedInvoiceList(String invoiceList);

	ArrayList<Client> getInvoicedClientidList(String invoiceList);

	String  getClientId(String invid);

	int saveAltAmount(OutstandingReport outstandingReport);

	ArrayList<OutstandingReport> getAllotList(String fromDate, String toDate);

	ArrayList<Accounts> getAltInvoiceList(String tpid,String recno);

	int updateInvoiceAllocation(String invoiceno, String dedtn, String tds,
			String stmdcine, String recdamt, String runbal, String recno);

	int updateInvoiceAllocation(String invoiceno, String dedtn, String tds,
			String stmdcine, String recdamt, String runbal, String recno,
			String date);


	boolean checkInvoiceExist(String invoiceno);

	int getLastPaymentId(String invoiceno);

	int updatePayment(int lastpamntid, String date,String payment);
	int getInvoiceId(int id);
	

}
