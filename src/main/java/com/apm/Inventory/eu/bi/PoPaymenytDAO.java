package com.apm.Inventory.eu.bi;

import java.util.ArrayList;

import com.apm.Inventory.eu.entity.Procurement;
import com.apm.Inventory.eu.entity.Product;
import com.apm.common.utils.Pagination;

public interface PoPaymenytDAO {

	ArrayList<Procurement> getPoPaymentList(String location, String searchtext, Pagination pagination);

	Procurement getPaymentHistoryDetails(String selectedid);

	int savePayment(Procurement procurement, int paymentids);

	ArrayList<Procurement> getPaymentRecieptList(String selectedid,String vendor);
	public ArrayList<Procurement> getPaymentRecieptListByVendor(String vendorid);
	double getSumOfPayment(String selectedid);

	public double getTotalPaid(String vendor_id);
	public double getTotalAmount(String vendor_id);

	ArrayList<Product> getAllVoucherList(String vendorid);
	public double getPaidAmtofVoucher(String voucherno,String procurementid);

	double getTotalVoucherAmount(String voucherno,String procurementid, int iscomplt);
	public double getTotalVoucherVat(String voucherno,String procurementid);
	public double getTotalVoucherDiscount(String voucherno,String procurementid);
	public double getTotalAountoFVendor(String vendorid);

	int getTotalPayment(String location, String searchtext);

	ArrayList<Procurement> getProductReturnListByVendor(String vendorid);

	double getTotalReturedAmount(String vendorid);

	int updateProcurmnetPaymentStatus(String procurementid);

	double getTotalVoucherAmount(String voucherno, String string, String vendorid);
	
	Procurement getPaymentDetailsProcurement(String payid);

	int saveParentPaymentData(String newvendorid, String userId, String date, String total_amt, String sumofreturn, String refundcheck, double totalpaid, int isfromledger);

	int updateReturnPaymentDoneStatus(String grnreturnids, int paymentids);

	ArrayList<Procurement> getPaymentDetailsProcurementParent(String payid);

	Procurement getPaymentDetailsParent(String payid);

	int getVendorIdFromLegder(String id);

}
