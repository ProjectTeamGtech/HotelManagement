
package com.apm.Expence.eu.bi;

import java.util.ArrayList;

import com.apm.Expence.eu.entity.Expence;
import com.apm.Master.eu.entity.Master;

public interface ExpenManagementDAO {

	ArrayList<Expence> getExpenceList(String fromdate, String todate, String expenseType, String range, String paymentmode,String action);

	double getExpenceTotal();
	double getExpenceTotal(String fromdate,String todate);

	int addReportData(String totalExpenceCheckbox, String date,String reportName);

	ArrayList<Expence> getReportList();

	Expence getReportIdList(String id);

	ArrayList<Expence> getReportExpenseList(String reportidlist);

	double getReportExpenceTotal(String reportidlist);

	ArrayList<Expence> getAllCategories(String xpayment);

	int addPaymentVoucher(Expence expence,int parentid,String epayment);

	Expence getPaymentVoucher(String selectedid);

	int updatePaymentVoucher(Expence expence);

	int deleteExpenses(String id, String uid);

	ArrayList<Expence> getSortedExpenceList(Expence expence);

	public ArrayList<Expence> getAllCurrencies();

	public int getCurrencyValue(String curr);

	int getTotalPaymentVoucher(String fromdate, String todate, String expenseType, String range, String paymentmode);

	ArrayList<Expence> getReportExpenseListByid(String id,int parentid,String epayment);


	int saveParentExpData(String pledgerid, String ptype, String pmode, String paymantto, String pcommencing,
			String uid,int pno,int rno,int cno,int jno,int purno);

	int getExpenceParentID(String id);

	Expence getExpenceParentDetail(int parentid);

	ArrayList<Master> getDebitorList();

	int saveNewLedgerDebitors(String debitor);
	int cancelExpense(String id,String delete_reason);

	String getDebtorname(String debiorname);

	int getExpenceMaxno(String column);
	
	Expence getVoucherDetails(String selectedid);

	int updateExpence(String selectedid, String amt, String caldate, String description,String cname);

	int updateExpenceLedger(String editcatgoryid, String amt, String colname,String expenceid);

	String getExpLedgid(String editcatgoryid);

	int updateExpenceLedgerNew(String ledgerid, String vat, String colname, String procurementid, String invoicecolmn);

	int updateJvExpence(String selectedid, String credit, String amount, String caldate, String description);

	int updateJvExpenceLedger(String catid, String credit, String amount, String selectedid);

	int deleteLedgerEntery(String id);

	int getOfficiallederid(String id);

	String getPtoName(int oficicledgerid);

	Expence geteoficialledgerdetails(int parseInt, int i);

	String getVendorName(int vendoridnew);

	int checkforvendor(String ledgerid);

	String getLedgerPaymentMode(String ledgerid);

	int getExpenceVendorid(String vexpenseType);

	Master getVendorDetails(int vendorid);

	double getDbLedgerAmount(String ledgerid);

	int updateLedgerAmount(String ledgerid, double lamount);

	double getEditLedgerAmount(String editledgername);

	String getParentExpLedgerId(String id);

	int upddateExpenceId(int expenceid,int ledgerid,String xpayment);

	int updatePaymentVoucher(int expenceid, int paymentids);

	int updatePaymentLedgerSheet(int saveledger, int paymentids);

	int truncateLedgerAmount();

	int refreshLedgerAmount();

	int updateLedgerName(Master master);
	
	int delLedgerWithStatus(int ledgerid);
	
	void savePatientToLedgerMaster(String clientId);
	int getLedgerIdOfClient(String client);
}

