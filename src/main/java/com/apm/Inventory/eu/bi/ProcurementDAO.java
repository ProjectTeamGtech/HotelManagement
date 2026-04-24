package com.apm.Inventory.eu.bi;

import java.sql.Connection;
import java.util.ArrayList;

import com.apm.Inventory.eu.entity.Procurement;
import com.apm.Inventory.eu.entity.Product;
import com.apm.Inventory.eu.entity.Vendor;
import com.apm.Master.eu.entity.Master;
import com.apm.common.utils.Pagination;
import com.apm.common.web.common.helper.LoginInfo;

public interface ProcurementDAO {

	ArrayList<Vendor> getVendorList();

	ArrayList<Product> getProductList(String supid);

	String getBrandidseries(String supid);

	ArrayList<Master> getBrandNameList(String supid, String brandidseries);

	ArrayList<Product> getBrandProductList(String vendor, String bid);

	Product getProductDetails(String pid);

	int saveProcurementData(Product product, String d, double total,String vendorid,String brandid,int procurementid, int isagrement, int islongpoprod, String vat);

	int getTotalQuantityCount(int id);

	int updateProduct(double res, int id);

	ArrayList<Procurement> getAllProcurementList();

	int updatePoData(String id);

	int deletePo(String id);

	int updateConfirmStatus(String proc_id);

	int updategoodsPO(String proc_id);

	Procurement getProcurement(String selectedid, int procid);

	int updatePoductpo(Procurement procurement);

	int saveParengtPrecurementData(String vendorid, String date, int isagrement, String agreementid, int grnwithpo, String grnYearSeq, String isfromworkorder);

	int getProcurementCount(String categoryid,String location, String fromdate, String todate, String voucherno, String iswithpo, String vendorid, boolean isAmcCmc);

	ArrayList<Procurement> getAllProcurementList(String categoryid,
			Pagination pagination,String location,String voucherno,String procurementType, String fromdate, String todate,String vendorid,String iswithpo,boolean isAmcCmc, LoginInfo loginInfo);
	public int updatePocurement(Product procurement);

	int addTempPo(String pid, String vendorid,String d,int tid, String childid, String purchaseprice, int isagrement, String vat, String loginsessionid, String poDescription, String pohsnno, String isfromworkorder);
	int truncateTempPo(String loginsessionid);

	ArrayList<Product> getAllPoListByVendor(String loginsessionid, String isfromworkorder);

	ArrayList<Product> getPoProductByVendor(String vendor_id, String loginsessionid, String isfromworkorder);

	ArrayList<Procurement> getListProcurement(String procurementid);

	ArrayList<Procurement> getSupplierWiseReport(String fromdate, String todate,String location);

	int updateProcurementStatus(int product_id, String string);

	ArrayList<Product> getSupplierGraphReport(String fromdate, String todate);

	int addNewProduct(String prodid, String batch, String expiry, String qty,String lastmodified,String location);
	public int saveNewProcurement(Product product);

	int saveNewProduct(Product product);

	int getVendorProcIfExist(String vendorid, String date);

	int addVendorAccountProcurement(Procurement procurement);

	boolean isVoucherExist(String voucher);

	ArrayList<Product> getProcurementProductList(String procurementid, int procid);

	int updateVendorAccountProcurement(Procurement procurement);

	ArrayList<Product> getProcurementVoucherProductList(String voucherno);

	Procurement getProcurementByVoucher(String voucherno);

	ArrayList<Product> getVoucherProductList(String vendorid);

	Product getProcProdDetails(String string, String string2);

	int returnToSupplier(Product product);

	double getTotalDebitofVendor(String vendorid);

	int saveVendorDebitOrCreditAmt(String vendorid, String debit,
			String credit, String date);

	double getTotalCreditofvendor(String vendorid);

	Product getVendorReturnAccountData(String vendorid);

	int updateVendorDebitOrCreditAmt(String vendorid, String debit,
			String credit, String date);

	int savePoVatAllocations(Product product);

	int deleteVatAllocations(String procurementid, String voucherno);
	
	ArrayList<Product> getVatAllocationList(String procurementid,String voucherno);

	Product getProcAccountDetails(String procurementid);

	Product getPreviousProductDetails(String vendorid, String product_name,Product product);

	Product getLastMedicineData(String id,String vendorid);

	ArrayList<Product> getAllSupplierProductList(String product_name, String pid);
	public ArrayList<Product> getProcurementListBeforeProcurement(String procurementid);
	public Product getLastProductDetailsOfSupplier(String product_name,String vendorid);
	ArrayList<Product> getRequestedPoList(String location,Pagination pagination, String searchtext, String supplierPoId, String locationPoId);
	public Product getLastProductDetails(String product_name);

	int saveNewTempPo(Product product);
	public int updateConfirmPOStatus(String procurementid);

	int deleteNewGrn(String id);

	int updateTempPoStatus(int id);

	ArrayList<Product> getAllVendorOfProduct(String productid, String vendor_id);

	ArrayList<Product> getProcRequestedList(String id);

	int updateQtyByAdmin(String qty, int id);

	int addConfirmProcurement(String remark, String procurementid);

	Vendor getVendorDetailsOfProcurement(String procurementid);

	String getAdminPoRemark(String procurementid);

	int updateTempPoQtyByAdmin(String qty, int newpo);

	boolean isThisProductExist(String procurementid,String prodid);

	int saveParentGrnData(String vendor_id, String date, boolean hidecalinpoprint, String grnYearSeq, String isfromworkorder);
	Product getGrnDetails(int grnno);

	Procurement getProcurementDataByProcurementID(String procurementid);

	int getVendorGrnIfExist(String vendorid, String nowdate);

	String getCatalogueIdFromProduct(int id);
	public int cancelPo(String id);
	int deleteVendorProcurementDetails(String voucherno,String procurementid);

	ArrayList<Vendor> getVendorListBylocationWise(String location);

	boolean hasLocationToUser(int clinicid);
	
	int getTotalTodayGRN(String fromdate, String todate, String location);

	int saveUpDeleteProcurment(String id, String delete_reason, String userid, String loc);

	int getTotalRequestedPOCount(String location, String searchtext, String supplierPoId, String locationPoId);

	Procurement getProcurementDetails(String product_id);
	public String getVouchernoByProcurementid(String procurementid);

	int saveParentGrnReturnData(String vendor_id, String date);

	ArrayList<Product> getAllGrnReturnProductList(String grnreturnid);

	String getVendorIdfromGRNReturn(String grnreturnid);

	int updateGrnReturnDataStatus(Product  product);

	int updateReturnQueStatus(int id);

	int saveReturnVendorAcccount(Product product);

	int deleteProcurementofThis(int procurementid);

	String getExpiryAlertDateSetting(int clinicid);

	ArrayList<Procurement> getPaybleAging(String toDate, String location);

	ArrayList<Product> getItemWisePurchase(String fromDate, String toDate, String product, String warehouseid,int vendorid, String categoryid, String subcategoryid);

	int updateProcurmentGST(int id, String vat);

	Product getProcurementBeforeProcurement(String procurementid, String catalogueid);

	int updateParentProcurment(String delivermemodate, String delivermemoid, String todaydate, String procurementid, int dmseq);

	Procurement getParentProcurmentData(String procurementid);

	ArrayList<Procurement> getDMList(String location, String vendorid);

	int updateChildProcDM(String string, String voucherno, String voucherdt);

	int updatePParentProcDM(String string, String voucherno, String voucherdt, String security, String sec_date, int dmseq, int parentid);

	int updateVendorProcDM(String string, String voucherno, String voucherdt, String security, String sec_date);

	int getDmSeq(String location);
	
	int insertoTermsNcondition(String text, String procureid);
	int updatetoTermsNcondition(String text, String procureid);
	boolean checkTermsNcondition( String procureid);
	String getTermNconditions(String procureid);
	//lokesh
	ArrayList<Procurement> getDmlist(String fromdate, String todate,Pagination pagination, String location,String vendorid,String invoice_created);
	int getDmlistCount(String fromdate, String todate, String location,String vendorid,String invoice_created);
	int cancelPOnew(String procurementid);
	//lokesh
	 double getoldpurprice(String catalaugeid);
	 int getStock(String catalaugeid, String location);
	 
	 //lokesh
	 ArrayList<Procurement> getemailPOList(String fromdate, String todate, String vendor_id, String voucherno);

	int getvendorstateforlongpo(String procurementid);
	String secondryLetterhead();

	int getMaxGRNSeqNo(String location);

	int updateProcurmentSeqNo(int i, int procurementid);

	String getCatalogueRate(String product_id);

	int updateCatalogueRate(String product_id, String rate);

	int saveCatalogueRateChangeLog(String product_id, String rate, String previousrate, String datetime, String userid);

	ArrayList<Procurement> getAllProcurementList(String location, String voucherno, String fromdate, String todate, String vendorid);

	int saveParentDMData(String date, String userId, int newprocurementid);

	int saveChildDMData(String string, int parentid);

	boolean isDMComplated(String procurementid);

	String getProcurementIDS(int dmparentid);

	int getDmparentID(String procurementid);

	int getOldDmSeqNo(String procurementid);

	boolean checkingProIDSeq(String procurementid);

	int getProcurmentSeq(String location);

	int insertProcSeqNo(String location, int seqno, String userId, String procurementid, String todaydate, String isdm, String dmparentid);

	int updatePoVatAllocation(String string, String voucherno);

	int updateProcurementId(int newprocurementid, String string, String lastdate, String dateonly);

	int updatePoVatProcurementId(int newprocurementid, String string);

	int updateVendorProcurementId(int newprocurementid, String string);

	Procurement getVendorPoDetails(String string);

	int checkIsComplatedDm(String procurementid);

	int getOldProcurementId(String id);

	int getGrnNoFromProcId(String procurementid);

	int updateDiscQty(String qty, String discount, int id, String rate, String vat, String poDescription, String poHsnno, String userid);

	int getProcurmentSeqNo(String string);

	Procurement getProcurementNew(String string, int i);

	ArrayList<Procurement> getNotificationlist(String fromDate, String toDate);
	
	String getprodidfromPro(String procurementid);

	int saveParentAgreement(String userId, String datetime, String location);

	int saveChildAgreement(Product product);

	int getAgreementCount(String location, String fromdate, String todate, String voucherno);

	ArrayList<Procurement> getAllAgreementList(Pagination pagination, String location, String voucherno,
			String fromdate, String todate);

	int deleteNewGrnBycatId(String catalogueid, String location);

	Vendor getVendorDetailsOfAgreement(String agreementidid);

	ArrayList<Product> getAgreementListBeforeProcurement(String agreementidid);

	boolean isThisProductExistINAgreement(String procurementid, String catalogueid);

	Product getAgreementListBeforeProcurement(String procurementid, String catalogueid);

	int deleteAgreement(String id);

	int updateAgreementData(String qty, String discount, int id, String ratepur);

	int updateConfirmAgreementStatus(String procurementid, String status, String userid, String datetime, String remark);

	int cancelAgreement(String procurementid, String userId, String datetime, String remark);

	Product getAgreementBeforeProcurement(String childid);

	int getAgreementOrderQty(int id);

	boolean checkVendorVatSaved(String string);
	int getprocurementidByreturn(String returnid);
	double getDiscountAmt(String procid,String productid);

	int deleteReturnGrnProduct(String id);

	int addReturnTempPo(String productid, String vendorid, double qty, int parseInt, String loginsessionid);

	ArrayList<Product> getAllReturnListByVendor(String loginsessionid);

	ArrayList<Product> getReturnProductByVendor(String vendor_id, String loginsessionid);

	int deleteReturnProdTempData(String loginsessionid);

	Product getProcurementIdFromProductId(String product_id);

	ArrayList<Product> getVatAllocationOfReturn(String grnreturnid);

	int deleteReturnVatAllocations(String grnreturnid);

	int saveReturnPoVatAllocations(Product product);

	
	int getVendoridFromProc(String procu_id);

	ArrayList<Product> getAllGrnReturnProductListPrint(String grnreturnid);

	Product getParentReturnProductData(String grnreturnid);

	ArrayList<Product> getVatAllocationOfReturnPrint(String grnreturnid);

	int updateReturnParentProductStatus(String aprovereturnbal, String aprovereturnid, String totalenteredpayamt,
			String userId, String date);

	int updateReturnProductStatus(String aprovereturnid, String string);

	int updateVendorReturnAccount(String aprovereturnid, String totalenteredpayamt);

	int getStatusRefundAmount(String string);

	boolean checkVendorLedgerPresentStatus(int vendoridnew);

	int insertLedger(Connection connection, int vendoridnew, String name);

	int vendorVoucherExistance(String vendorid, String isfromeditpo, String procurementid, String voucher);

	int vendorVoucherExistanceForDm(String vendorid, String voucher);

	Product getProcurementDataBeforeProcurement(String procurementid, int id);

	ArrayList<Product> getRequestedPoListByIDs(String tempoids, String location);

	double getpreviousledgerbalance(String ledgername);

	boolean checkPreviousCompletePo(String procurementid);

	int getProcurementPOPending(String procurementid, boolean flag, Procurement procurement2, String date);

	boolean checkIsPendingPo(String procurementid);

	ArrayList<Product> getProcPoRequestedList(String procurementid);

	int getPreviousCompletePoProcId(String procurementid);

	int getNewGrnPoNo(String procurementid);

	int getParentReqCount(int paraentpoid);

	int updatePendingPoStatus(int paraentpoid, int i);

	int updateProc_ConditionStatus(String procurementid);

	int updateDeletePoPending(String procchildids, String procurementid);

	String getOldParentProcId(String procurementid);

	int updatePoConfirmData(int parentpoid, String catalogueid);

	int getCompletedPoCount(int paraentpoid);

	int updatePOCompleteParent(String id, String reason, String userid, String date);

	int deletePoPendingNotDone(String procurementid);

	int updateProcuremeentGRnDate(int newprocurementid, String date);

	String getProcurementGRNDate(String string);

	int updateParentVendorid(String procurementid, String newvendorid);

	int updateChildVendorid(String procurementid, String newvendorid);

	ArrayList<String> getProductIdFromProcId(String procurementid);

	int updateProductSupplierid(String oldprodid, String newvendorid);

	int updateVendorPorcVendorid(String procurementid, String newvendorid, String voucherno);

	int saveVendorIdChangeLog(String procurementid, String newvendorid, String oldvendorid, String datetime,
			String userId);

	String getLocationIdFromProcurement(String procurementid);

	int updateLedgerVendor(String ledgerid, int vendoridnew, String procurementid, String oldledgerid);

	int getPurchaseExpenceId(String ledgerid, String procurementid);

	int updateExpenceChildLedger(int expenseid, String name, String ledgerid);

	int updateExpenceParentLedger(int expenseid, String ledgerid);

	ArrayList<String> getParentPoProcIDs(String procurementid);

	int updateProcurementAproveUserid(String procurementid, String userId, String date);

	int updateGrnWithPoMinusQty();

	int getRecivedQtyAgainstPO(int parentpoid, String catalogueid, String procchildids, String procurementid);

	int updateSubjectOfPO(String grnno, String textdata);

	int getChildprocId(String procurementId);

	int updatemailcontentOfPO(String grnno, String textdata);

	int updateTempPoStatus(String catalogueid, String location);

	int saveTempDataInGRNWIthPO(String datetime, String loginsessionid, int res, Product master, String product_id,
			String warehouse, double d, String vendorid, int checkedentry, int isfrompolist, String indnetid, String hsnno, String isfromworkorder);

	Product getRequestedPoListByID(String cataloguid, String location, String vendorid);

	ArrayList<Product> getRequestedPoListFromTempData(String loginsessionid, String location, String isfromworkorder);

	int updateTempGRNPOData(String colname, String tempdatagrnid, String val);

	ArrayList<Product> getAllVendorOfProduct(String warehouse);

	int truncateTempPoNew(String loginsessionid, String isfromworkorder);

	int updateGRNEnteredAmount(String procurementid, String firstnetamout);

	int saveDeletePoProductLog(Procurement procurement, String userId, String datetime);

	int updatePoDetails(String grnno, String textdata, String columname);

	String generateYearPoSequence(String location, boolean lmh, String type, String date1);

	int updateYearPoSequence(int procurementid, String grnYearSeq);

	int getProcurementEntryCount(String id);

	int updateProcurementPoDisc(double convertToDouble, String procurementid);

	boolean checkPayTermsNcondition(String procureid);

	int updatePaytoTermsNcondition(String condition, String procureid);

	int inserPaytoTermsNcondition(String condition, String procureid);

	String getPaymentTermNCondition(String procurementid);

	ArrayList<Procurement> getPendingPoProductList(String location, String voucherno, String fromdate, String todate,
			String vendorid);

	int saveProcurementDataLog(Product product, String string, int i, String vendorid, String string2, int parseInt,
			int j, int k, String vat);

	int updateDiscQtyLog(String qty, String discount, int id, String rate, String vat, String poDescription,
			String poHsnno, String userid);

	ArrayList<Procurement> getvendorrate(String fromDate, String toDate, String product);
}
