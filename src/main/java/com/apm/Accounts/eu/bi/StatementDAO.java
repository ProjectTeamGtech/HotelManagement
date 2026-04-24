package com.apm.Accounts.eu.bi;

import java.util.ArrayList;

import com.apm.Accounts.eu.entity.Accounts;
import com.apm.Accounts.eu.entity.Invoice;
import com.apm.common.utils.Pagination;

public interface StatementDAO {

	int getTotalAccountCount(String clientId, String payby,
			String transactionType, String location, String thirdParty);

	ArrayList<Accounts> getAccountPendingList(String clientId, String payby,
			Pagination pagination, String transactionType, String location,
			String thirdParty);

	ArrayList<Accounts> getAccountPaidList(String clientId, String payby,
			Pagination pagination, String transactionType, String location,
			String thirdParty);

	ArrayList<Accounts> getAccountList(String clientId, String payby,
			Pagination pagination, String transactionType, String location,
			String thirdParty,String fromDate,String toDate,String ipdid, String addOnTableName);

	ArrayList<Invoice> getInvoiceList(String clientId, String payby,
			String transactionType, String location, String thirdParty,
			String fromDate, String toDate,String ipdid);

	ArrayList<Invoice> getPaidInvoiceList(String clientId, String payby,
			String transactionType, String location, String thirdParty,
			String fromDate, String toDate,String ipdid);

	ArrayList<Invoice> getPendingInvoiceList(String clientId, String payby,
			String transactionType, String location, String thirdParty,
			String fromDate, String toDate,String ipdid);

	double getLastCreditBalance(String clientId);

	Accounts getInvoiceDetails(String invoiceid);

	Accounts getInvoiceChargeDetails(String chargeid);

	int updateInvoiceSharedStatus(String share_invoiceid, double total_shared);

	int updateChargeSharedStatus(String share_invoiceid, String share_chargeid, double total_shared);

	double getSumOfReundAmt(String clientId);

	double getRefundAmtAgainsInvoice(int int1);

	double getCreditAmount(int chargeinvoiceid);

	double getRefundChargeAmt(int invoiceid);

	boolean getIndDiscStatus(int id);

}
