package com.apm.Inventory.eu.bi;

import java.util.ArrayList;

import com.apm.Inventory.eu.entity.Procurement;
import com.apm.Inventory.eu.entity.Product;
import com.apm.Inventory.eu.entity.Vendor;
import com.apm.Master.eu.entity.Master;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.common.utils.Pagination;
import com.apm.common.web.common.helper.LoginInfo;

public interface InventoryProductDAO {

	ArrayList<Product> getallsupplierlist();
	
	ArrayList<Product> getAllCategories(Pagination pagination);
    
	int countAllCategories();

	int addCategory(Product product);

	Product getCategory(String id);

	int updateCategory(Product product);
	
	int deleteCategory(String id);

	int getTotalSubCategoryCount();
	ArrayList<Product> getAllSubCategoryList(Pagination pagination);
	
	Product getSubCategory(String id);
	int updateSubCategory(Product product);
	int deleteSubCategory(String id);
	int addSubCategory(Product product);

	int getTotalBrandCount();
	ArrayList<Product> getAllBrandList(Pagination pagination);
	int addBrandname(Product product);
	Product getBrandName(String id);
	int updateBrandName(Product product);
	int deleteBrandname(String id);

	ArrayList<Product> getSubCategoryList(String category_id);

	ArrayList<Product> getAllCateoryandSub();

	int addProduct(Product product);
	Product getProduct(String prodid);
	

	ArrayList<Product> getAllProducts(Pagination pagination,int i,String date,String categoryid, String searchtext,String medicine_shedule,String location,LoginInfo loginInfo,String report_filter,String withstock_filter,String orderby, String product_type, String shelf_filter,String subcategoryid);
	ArrayList<Product> getAllProducts(String id,String product_id,Pagination pagination);
	public ArrayList<Product> getAllProducts(String id);

	int updateProduct(Product product);

	int deleteProduct(String id);

	int deleteCategoryandSubcategory(String categoryid);

	int updateSubCategoryOnly(Product product);

	ArrayList<Product> getAssetProducts(String string);

	int getAssetsCount(String product_id);

	int geTotalProductCount(String date,String categoryid, String searchtext,String medicine_shedule,String location, String withstock_filter, String product_type, String shelf_filter, String subcategoryid);

	ArrayList<Product> getGodownList();

	ArrayList<Product> getProductByVendor(String vendorid);

	int addSaleProduct(Product product);

	int getSaleProductCount(String gowdown,String fromdate,String todate);

	ArrayList<Product> getSoldProduct(Pagination pagination,String gowdown,String fromdate,String todate);
	String getGodownName(String id);

	Product getSaleProducDetails(String id);
	public ArrayList<Product> geProductList(String categoryid,String location);
	int returnsaleQty(String id, String returnqty,String lastmodified);
	int updateProductQty(String id,String nowstock);

	ArrayList<Product> getListandHistoryProduct(Pagination pagination,String fromdate,String todate,String brand,String vendor);
	ArrayList<Product> getLastHistorybyVendor(String vendorid,int productid);

	int geTotalProductCountByPurchased(String fromdate,String todate,String brand,String vendor);

	ArrayList<Product> getAllWillExpireList();

	Product getMedicineProductDetails(String mdicinenameid);

	int updateMedicineQty(double qty, String mdicinenameid,int plusminus);

	int getCountProduct(String id);
	public int updateProductByProcurement(Product product, int i);

	ArrayList<Product> getAllProductList(String categoryid);
	public ArrayList<Product> getAllBrandListByCategory(String categoryid);

	int savenewProduct(Product product);

	ArrayList<Product> getPoProductList(String categoryid);

	ArrayList<Master> getcellList(String department);

	int updatePackandMedicineType(String pack, String medicine_type, String pid);
	public ArrayList<Product> getSupplierBillProcurementandDates(String vendorid,String fromdate,String todate);

	ArrayList<Product> getSupplierBillList(String vendorid,String commencing,String voucherno,String procurementid);

	Product getPriscMedicineByAvailablility(String mdicinenametxt,int required,String genericname);

	ArrayList<Product> getExpiryMedicineReport(String fromdate, String todate,String days,String location, String report, Pagination pagination);

	double[] getGraphPerUseandDead(String fromdate, String todate, String days);

	ArrayList<Product> getVatReportList(String fromdate, String todate,String location);

	String getProdPack(String prodid);

	int savemdbarcodedata(String imgname,Product product);

	int getmdBarcodeCount();
	int deleteProductStock(String id);
	int deletemdbarcode();
	int updateProductAjax(Product product);

	ArrayList<Product> getpatientlist();
	public ArrayList<Product> getMedicineListforVendor(String location);

	int geTotalProductCountfrCatalogue(String date, String categoryid,
			String searchtext,String location,String sucategory);
	ArrayList<Product> getAllProdfrCatalogue(Pagination pagination, int i,
			String commencing, String categoryid, String searchtext,String location,String subcategoryid,LoginInfo loginInfo);

	double getOpeningStock(String fromdate, String todate);

	double getPurchaseStock(String fromdate, String todate);

	double getSoldMedicineAmt(String fromdate, String todate);

	int updateStock(double finalstock, String product_id);
    int addTOMedicineMaster(Product product);
	int getMedicineDetails();

	ArrayList<Product> getItemWiseReportList(String fromdate, String todate,
			String product_id,String location);

	ArrayList<Product> getProductWiseReportList(String fromdate, String todate,
			String vendorid,String location, String purchaseinvoice,Pagination pagination);

	ArrayList<Master> getMedicineTypeList();

	ArrayList<Product> getSaleReturnReport(String fromdate, String todate,
			String location,String salereturun,String billtype, Pagination pagination);
    public boolean isStrip(String mtypeid);

	ArrayList<String> getToLocationList(String loginsessionid);

	int saveParentProductTransfer(String firstlocation, String string,
			String todate, String time, String comment1, String userid, int indentcount);

	ArrayList<Product> getTempTransferData(String string, String loginsessionid);

	int transferProductPTP(Product product1, String location, double qty,
			int prodid, int parentid, String string, String string2);

	int truncatetemporaytable(String loginsesseionid);

	int transferProductTemporary(int prodid, String firstlocation,
			String location, String qty, String comment, String loginsessionid);

	ArrayList<Product> getParentProductTransferData(String searchtext,
			String fromdate, String todate,String str,String filter_status, Pagination pagination, boolean flag, String indentapprove, String filter_bydate, int b, String department, boolean lmh);

	Product getParentTransferData(String parentid);

	ArrayList<Product> getChildTranfserData(String parentid);

	Product getChildTransferCal(String parentid);

	int savetempProduct(Product product);

	String pharmacyLocationNameByID(String location);

	int geReqTranTotalProductCountfrCatalogue(ArrayList<String> arrayList2,
			String fromlocation);

	ArrayList<Product> getReqTransAllProdfrCatalogue(Pagination pagination,
			ArrayList<String> arrayList2, String fromlocation);

	ArrayList<Product> getChildRequestedTempData(String parentid);

	Product getChildRequestTempCal(String parentid);

	int requestProductTemporarySave(int prodid, String location, String qty,
			String parentid, String string, String transfer_date, int count5);

	int updateParentProductStatus(String parentid);

	int addToProductDeletedLog(String userid, String date, String location, int i,
			String id);
	int addToProductUpdatedLog(String userid, String date, String location, int i,
			String id, String string,String catalogueid, String string2, String string3, Product product2, Product product, String datetime);

	ArrayList<Product> getDelUpdateReportList(String fromdate, String todate,
			String location, String filter, Pagination pagination);

	ArrayList<String> getChildProductName(String parentid);

	double getReqQtyFrmTemp(String prod_name, String parentid);

	Product getReqQtyFrmAct(String parentid, String prod_name);

	int updateChildTransferStatus(int id, int status);

	int updateChildTempTransferStatus(String prod_name, String parentid,
			int status);

	int getCountTransferProd(String parentid);

	int getCountTransferedProd(String parentid);

	int updateParentProductStatus(String parentid, String userid,
			int all_med_status, int r_status);

	

	ArrayList<Product> getMedinceListofType(String medicinetype,String category);

	int saveRequestIndent(Product product);

	int saveChildRequestIndent(Product master, int parentid);

	int updateRequestIndentChild(Product product);

	int deleteIndentRequest(String id);

	int getTotalIdentCount();

	int deleteIndentParent(String id);

	int getAvailableStock(String product_id, String product_name);

	ArrayList<Product> getBatchWiseProductList(int req_stock, Product prod);

	ArrayList<Product> getWithoutBatchWiseProductList(int req_stock, Product prod);

	int addTempPoRequest(String product_id, String parentid, double poqty, String string, String todate, String warehouse_id, int i, String catid);

	ArrayList<Product> getTempPoList(String parentid);

	ArrayList<Product> getLocationWiseStock(Product prod, Product product);

	ArrayList<Product> getProductListFromOtherLocation(Product prod,
			String location);

	int updateTempPoStatus(int id);

	int updateCheckAvailabilityNotes(String parentid, String comment, String transfer_date);

	Product getChildTransferTempCal(String parentid);

	ArrayList<Product> getOpeningStockList(String fromDate, String toDate, Pagination pagination,int min, int max, String ismonthlyreport, String searchbyprodname, String location_filter, String category_id);
	public ArrayList<Product> getConsumptionReport(String fromdate,
			String todate, String location,String salereturun,String billtype, Pagination pagination);

	ArrayList<Master> getWareHouseList();

	ArrayList<Master> getCategoryFromWareHouse(String id);

	int updateTempPoStatus(int id, double qty);

	int getAllAvailableStock(String product_name, String location);

	int getCountTransferDashboard(String searchtext, String fromdate,
			String todate, String location1, String filter_status, boolean flag, String indentapprove, String filter_bydate, int b, String department, boolean lmh);

	int createPoOnTransferStatus(String parentid, String string, String transfer_date, String userid);

	ArrayList<Product> getVenodrMedinceListofType(String medicinetype, String category, String vendorid);

	int updateHandover_To(String parentid, String handover_to);

	int getTotalTransferIdentCount();

	int saveUpDeleteIndent(String parentid, String delete_reason, String userid, String loc);

	int saveUpDeletePharmacyBill(String userid, String loc, String delete_reason, String bill);

	ArrayList<Product> getChildTranfserDataAfterPO(String parentid);

	int updateTransferNotes(String parentid, String comment, String transfer_date);

	ArrayList<Product> getChildTempReqData(String parentid);

	int updateParentProductStatus(String parentid, int all_med_status, int r_status);

	int getTotalReqTransferTemp(String parentid);

	int updatePendingTransferLog(String parentid, String userid);

	int requestProductTemporarySave(int prodid, String location, String qty, String parentid);

	int updateHandover_ToNew(String parentid, String handover_to);
	public Product getProductCatalogueDetails(String catalogueid);
	
	ArrayList<Product> getPaymentReceiveReport(String fromdate, String todate, String location, String salereturn,
			String billtype, String balance, Pagination pagination);
	ArrayList<Product> getWarehouse();
	Product getCategoryWarehouseById(String selectedid);

	ArrayList<Product> getAllTermsAndCondition(String string);

	ArrayList<Product> getItemWiseStockReportList(String fromdate, String todate, String product_id, String location);
	public int updateProductCatalogue(Product product);

	ArrayList<Product> getProductListForItemWiseStockReport(String string, String location);

	int addNewProduct(Product product);

	ArrayList<Product> getProductListForItemWiseSaleReport(String string, String location);

	int updateProductStockStatus(Product product);

	int checkForProcurmentItemReturn(String searchtext, String fromdate, String todate, String location1,
			String filter_status, Pagination pagination);

	int addNewProductToVendor(String loc, int res);

	ArrayList<Master> getAllLocation(String searcttext);

	int saveParentProductReturn(String firstlocation, String string, String todate, String time, String comment1,
			String userid, int i);

	String getProdidFromCatagoryid(String cataloguid);

	ArrayList<Product> getIndentTransferLog(String fromdate, String todate, String location1, String filter_status, String location_filter, String searhText, Pagination pagination);

	int updateProcurmentStatus(String procurementid, String status);

	int updateTOZeroStock(String product_id);

	int cancelIndent(String parentid);

	ArrayList<Product> getAscStockWiseProductList(int req_stock, Product prod);

	ArrayList<Product> getProductLimitedFromOtherLocation(Product prod, String location);

	boolean isPharamcistOrNot(String userid);

	String getHISUserLocation(String userid);

	ArrayList<Product> getBinCardReport(String location, String catalogueid, String fromdate, String todate,String abrivation, String userwise, String location_filter);

	ArrayList<Product> getAllProductsXLS(int i, String fromdate, String categoryid, String searchtext,
			String medicine_shedule, String location, LoginInfo loginInfo);

	String getCatIdProdId(String product_id);

	double getAllProdAvailableStock(String catid, String warehouse_id);

	int getTotalGrnReportCount(String fromdate, String todate, String vendorid, String location, String purchaseinvoice);

	int getCountSaleReturnReport(String fromdate, String todate, String location, String salereturn, String billtype);

	int getCountPaymentReceiveReport(String fromdate, String todate, String location, String salereturn,
			String billtype, String balance);

	
	int getCountOpeningStockList(String fromDate, String toDate, String searchbyprodname, String location_filter, String category_id);

	int getTotalBillByBillDate(String fromdate, String todate, String location, String salereturn, String billtype);

	Product getToalBillByPaymentDate(String fromdate, String todate, String location, String salereturn,
			String billtype, String balance);

	int getCountConsumptionReport(String fromdate, String todate, String location, String salereturn, String billtype);

	int getCountExpiryMedicineReport(String fromdate, String todate, String days, String location, String report);

	int getCountIndentTransferLog(String fromdate, String todate, String location1, String filter_status,
			String location_filter, String searhText);

	int getCountDelUpdateReportList(String fromdate, String todate, String location, String filter);

	int addToRroductReturntoSupplier(String str, String dateTime, String location);

	ArrayList<Product> getTempReturnQueList(String location);

	int addToReturnDashBoard(Product product);

	ArrayList<Product> getProductReturnList(String fromdate, String todate, String location, String searchtext,Pagination pagination);

	int updateStockToReturn(String product_id,double remainstock);
	Product getPriscAvailablilityByMolecules(String molecules,String genericname, int required);

	int getProductReturnCount(String fromdate, String todate, String location, String searchtext);

	int getTodayReturnCount();

	String getMedicineShedule(String catalogueid);

	int updateCatalogueMRP(Product product);

	boolean checkCatalogueExist(String name, String location);
	public int getStockFromProduct(String catalogueid);

	int addProductToVendor(int res, String vendorid);

	int getStockAvailable(String catalogueid);

	double getTotalStockProduct(String catalogueid);

	ArrayList<Product> getProfitandLostReport();

	String getPharakmacyUserLocation(String userid);

	ArrayList<Product> getApproveParentProductTransferData(String searchtext, String locatioon,String department);

	ArrayList<Product> getRequestParentProductTransferData(String searchtext, String locatioon,String fromdate,String todate, int forwarehouseonly, boolean multipleApprove,String depatment, boolean b);

	ArrayList<Product> getstockValuationReport(String location, String filteroforder);

	ArrayList<Product> getDeptMaterialIssueList(String fromDate, String toDate, String product, String department, String warehouseid );

	ArrayList<Product> getNonMovingItemList(String department, String toDate, String filteroforder, Pagination pagination);

	ArrayList<Product> getALLStockReport(String location, String orderby, String report_filter, String withstock_filter, String subcat_filter, String order_filter, String fromdate, int isprevious, String prductlist, String searchtext);

	ArrayList<Product> getUserMaterialIssueList(String fromDate, String toDate, String product, String department, String fromwherefilter, String warehousefilter1);

	ArrayList<Master> getCatalogueList();

	ArrayList<Master> getSecStoreNameList();

	ArrayList<Product> getDeptMaterialIssueListNew(String fromDate, String toDate, String productname,
			String department, String warehouseid, String category);

	ArrayList<Product> getPriscMedicineByAvailablilityList(String mdicinenametxt, int int1, String genericname, int default_location_new);

	Product getPreviousShelfId(String catalogueid, String location);

	int getEditGRNShelfId(String shelf);

	int saveInventoryStockLog(String todaydate, String string);

	int checkProductCodeExist(String val);

	ArrayList<Product> getchildCathData(String val, String location1);

	String getCathlabTemplateProcid(String cathtempid);

	int geTotalCathLabCount(String fromdate, String todate);

	ArrayList<Product> geTotalCathLabList(Pagination pagination, String fromdate, String todate);

	int getProductStockByCatlogue(String catalogueid, String location);

	ArrayList<Product> getCathDataChildList(String id, String string);

	Product getCathParentData(String id);

	int updateCathStatus(int parentid, String string);

	ArrayList<Product> getProductData(String location);

	int getTemplateCathLabCount();

	ArrayList<Product> getTemplateCathLabList(Pagination pagination);

	ArrayList<Product> getChildCathTempData(String id);

	Product getCathTemplateData(String id);

	ArrayList<Product> getCathlabProductData(String location);

	int getCountCathOpeningStockList(String fromDate, String toDate);

	ArrayList<Product> getCathOpeningStockList(String fromDate, String toDate, Pagination pagination, int minlimit,
			int maxlimit, String ismonthlyreport);

	int updateCathComment(int parentid, String comment1);

	int deleteCathlabTemplate(String id, String datetime, String userid);

	ArrayList<Procurement> getSupplierWisePaymentList(String fromdate, String todate, String vendorid,
			Pagination pagination);

	int getTotalSupplierPaymentReportCount(String fromdate, String todate, String vendorid);

	ArrayList<Product> getProductListForItemWise(String string, String location);

	ArrayList<Product> getItemWiseReportListNew(String fromdate, String todate, String product_id, String location);

	ArrayList<Master> getWareHouseList(String location);

	ArrayList<Product> getAllItemWiseReportList(String fromdate, String todate, String product_id, String location, Pagination pagination, String product_type, int isfromoepningclosing, String catalogueid);

	int getAllItemWiseReportListCount(String fromdate, String todate, String product_id, String location, String product_type, int isfromoepningclosing, String catalogueid);
	int changeChargesName(String chargeid,String val);
	int changeUnitCost(String chargeid,String val );
	int saveDeletedProcurement(String procchildid, String userid, String qty,String prodid, String charge,String procid );

	boolean checkProdIDInReturnList(String str);

	int updateReturnProductData(int id, String vat, String discper, String discount, String gstamount, String total, String voucherno, String returnqty, String returnfreeqty);

	int updateParentReturnProductData(String grnreturnid, double total_amount, String sale_discount, String cgst,
			String sgst, String igst, String alltotvatTotal, String total_amt, String notes);

	boolean checkVendorPresentInSystem(String str);

	String getProductNameFromID(String prodid);

	Product getProductCatalogueDetailsforsale(String catalogueid, String location);

	int getTotalStockProductforsale(String catalogueid, String location);

	int getMedLimit(String userId);

	String getOpeningStockStringNew(String fromDate, String toDate, String ismonthlyreport, String searchbyprodname,
			String location_filter, String category_id);

	ArrayList<Product> getOpeningStockListNew(String fromDate, String toDate, Pagination pagination, int minlimit,
			int maxlimit, String ismonthlyreport, String searchbyprodname, String location_filter, String category_id,
			String alreadypresentids);

	int getCountOpeningStockListNew(String fromDate, String toDate, String searchbyprodname, String location_filter,
			String category_id, String alreadypresentids);

	int cancelReturnToSupplierReq(String parentid, String delete_reason, String userid, String datetime);

	ArrayList<Product> getOpeningStockListByCatalogueWise(String fromDate, String toDate, Pagination pagination,
			int minlimit, int maxlimit, String searchbyprodname, String category_id, String location_filter);

	int getCountOpeningStockListByCatalogueWise(String fromDate, String toDate, Pagination pagination, int minlimit,
			int maxlimit, String searchbyprodname, String category_id, String location_filter);

	int updateRemainFreeQty(String proc_childid, int totalfreereturn);

	int updateProductStocks(String id, double qty);

	int insertAdjustmentProductData(String id, String adj_type, double stock, double qty, double changerqty, String comment,
			String userid, String date, int parentd, Product product1);

	ArrayList<Product> getProductWiseReportListNew(String fromdate, String todate, String product_id, String location,
			Pagination pagination, String product_type, String tpid, String payee);

	int getProductWiseReportListCount(String fromdate, String todate, String product_id, String location, String product_type);

	int changeApmtcode(String chargeid, String val);

	int changeApmtqty(String chargeid, String val);

	boolean checkPreviousDataCatAvailable(String previousdate, String catalogueid, String location_filter);

	Product getPuchaseProductData(String catalogueid, String fromDate, String location_filter);

	Product getReturnPatientProductData(String catalogueid, String fromDate, String location_filter);

	Product getPatientSaleBeforeDate(String catalogueid, String fromDate, String location_filter);

	Product getReturnToSupplierBeforeDate(String catalogueid, String fromDate, String location_filter);

	Product getConsumeBeforeDate(String catalogueid, String fromDate, String location_filter);

	Product getDirectTransferBefore(String catalogueid, String fromDate, String location_filter);

	Product getRequestTransferBefore(String catalogueid, String fromDate, String location_filter);

	Product getReturnTransferBefore(String catalogueid, String fromDate, String location_filter);

	Product getDirectTransferBeforeIn(String catalogueid, String fromDate, String location_filter);

	Product getRequestTransferBeforeIn(String catalogueid, String fromDate, String location_filter);

	Product getReturnTransferBeforeOut(String catalogueid, String fromDate, String location_filter);

	Product getAdjustmentDataBeforeOut(String catalogueid, String fromDate, String location_filter);

	Product getAdjustmentDataBeforeIn(String catalogueid, String fromDate, String location_filter);

	Product getPuchaseProductDataBetween(String catalogueid, String fromDate, String toDate, String location_filter);

	Product getReturnPatientProductDataBetween(String catalogueid, String fromDate, String toDate,
			String location_filter);

	Product getReturnTransferBetweenDate(String catalogueid, String fromDate, String location_filter, String toDate);

	Product getDirectTransferBetweenDateIn(String catalogueid, String fromDate, String location_filter, String toDate);

	Product getRequestTransferBetweenDateIn(String catalogueid, String fromDate, String location_filter, String toDate);

	Product getAdjustmentDataBetweenIn(String catalogueid, String fromDate, String location_filter, String toDate);

	Product getDirectTransferBetweenDate(String catalogueid, String fromDate, String location_filter, String toDate);

	Product getRequestTransferBetweenDate(String catalogueid, String fromDate, String location_filter, String toDate);

	Product getReturnTransferBetweenDateOut(String catalogueid, String fromDate, String location_filter, String toDate);

	Product getPatientSaleBetweenDate(String catalogueid, String fromDate, String toDate, String location_filter);

	Product getReturnToSupplierBetwwenDate(String catalogueid, String fromDate, String toDate, String location_filter);

	Product getConsumeBetweenDate(String catalogueid, String fromDate, String toDate, String location_filter);

	Product getAdjustmentDatabetweenOut(String catalogueid, String fromDate, String location_filter, String toDate);

	Product getYesterdayClosingData(String previousdate, String catalogueid, String location_filter);

	ArrayList<Product> getOpeningStockListByCatalogueWiseNew(String fromDate, String toDate, Pagination pagination,
			int minlimit, int maxlimit, String searchbyprodname, String category_id, String location_filter);

	int getCountOpeningStockListByCatalogueWiseNew(String fromDate, String toDate, Pagination pagination, int minlimit,
			int maxlimit, String searchbyprodname, String category_id, String location_filter);

	Product getNextOpeingData(String nextdate, String catalogueid, String location_filter);

	boolean checkInventoryCatalogueStatus(String fromDate, String catalogueid, String location_filter);

	int insertIntoProductLog(String userid, String datetime, String location, double qtyinout, String product_id,
			String catalogueid, String source, double currentstock, double previousstock, double changeqty, String procurementid, String grnreturnid, int billno, int adjustmentid,int indentid, String consumeid);

	int getPreviousStock(String product_id);

	int getCountExpiryMedicineReportNew(String fromdate, String todate, String days, String location, String report, String vendor_id);

	ArrayList<Product> getExpiryMedicineReportNew(String fromdate, String todate, String days, String location,
			String report, Pagination pagination, String vendor_id);

	Product getOpeingClosingBiferSale(String fromdate, String todate, String searchbyprodname, String location_filter);

	Product getOpeingClosingBiferDirectTransfer(String fromdate, String todate, String searchbyprodname,
			String location_filter);

	Product getOpeingClosingBiferRequestTransfer(String fromdate, String todate, String searchbyprodname,
			String location_filter);

	Product getOpeingClosingBiferReturnTransfer(String fromdate, String todate, String searchbyprodname,
			String location_filter);

	Product getOpeingClosingBiferReturnSupplier(String fromdate, String todate, String searchbyprodname,
			String location_filter);

	Product getOpeingClosingBiferConsume(String fromdate, String todate, String searchbyprodname,
			String location_filter);

	Product getOpeingClosingBiferAdjust(String fromdate, String todate, String searchbyprodname,
			String location_filter);

	ArrayList<Product> getProductListByCatalogueId(String catalogueid, String location, String qty);

	ArrayList<Product> getadjusmentlist(String fromDate, String toDate, Pagination pagination, String adjustmenttype, String userid, String location);

	int getadjusmentlistcount(String fromDate, String toDate, String adjustmenttype, String userid, String location);

	ArrayList<Product> getInventoryGSTReport(String fromdate, String todate,String vendorid, String location, String billdate, String filter_sortby);

	ArrayList<Product> getInventoryGSTReturnReport(String fromdate, String todate,String vendorid, String location, String billdate, String filter_sortby);

	ArrayList<Product> getopdtatreportlist(String fromDate, String toDate, Pagination pagination, String diaryuser);

	int getopdtatteportcount(String fromDate, String toDate, String diaryuser);

	int deletecharges(String chargeid, String ipdid);

	Product getOpeningClosingStock(String toDate, String location_filter, Pagination pagination);

	ArrayList<Product> getCancelBillReport(String fromdate, String todate, String location, Pagination pagination);

	int getCountCancelBillReport(String fromdate, String todate, String location);

	ArrayList<Product> getBinCardReportNew(String location, String catalogueid, String fromdate, String todate,
			String clinicabrivation, String userwise, String location_filter, String filter_sortby);

	String getProdCurrentStock(String prodid);

	int getProductIdFromGlobalID(String pid, String location);

	int updateGlobalProductId(int pid, int globalprod);

	ArrayList<Master> getGenericMasterList();

	ArrayList<Master> getMFGMasterList();

	double getStockAvailableNew(String catalogueid, String location);

	boolean checkInventoryProductLog(String todaydate, String string);

	boolean checkInventoryOpeningStockLog(String todates, String string);

	boolean checkProductInProductLog(String todates, String catalogueid,String fromdate,String location, String filter_bydate);

	Product getCatalogueProductIn(String todates, String catalogueid, String string, String fromdate, String filter_bydate);

	Product getCatalogueProductUpdateProIn(String todates, String catalogueid, String string, String fromdate, String filter_bydate);

	Product getCatalogueProductOut(String todates, String catalogueid, String string, String fromdate, int addpharmacy, String filter_bydate);

	Product getCatalogueProductUpdateProOut(String todates, String catalogueid, String string, String fromdate, String filter_bydate);

	Product getCatalogueProductSaleOut(String todates, String catalogueid, String string, String fromdate);

	int changePayee(String chargeid, String val, String tpid);

	ArrayList<Product> geProductListFast(String string, String id, boolean islmh);

	ArrayList<Product> getExpiryMedicinePopup(String fromdate, String dt, String location);

	ArrayList<Master> getWareHouseListRequest();

	int saveEditProcurementLog(String datetime, String grnupdateuserid, String grn_pre_pack, String grn_new_pack,
			String grn_pre_freeqty, String grn_new_freeqty, String grn_pre_stock, double grn_new_stock,
			String grn_pre_qty, String grn_new_qty, String grn_procurementid, String grn_child_procid);

	int getProductIdFromBarcode(String pid, String location);

	ArrayList<Product> getEditGRNLogList(String fromdate, String todate, String location, String serchtext);

	Product getManualStockEntry(String fromDate, String todates, String catalogueid, String location_filter);

	Product getCountOfOpeningClosingCatalogue(String fromDate, String toDate, String searchbyprodname, String category_id,
			String location_filter);

	ArrayList<Product> getListOfOpeningClosingCatalogue(String fromDate, String toDate, Pagination pagination,
			String searchbyprodname, String category_id, String location_filter, Product productcount, String filter_bydate);

	ArrayList<Product> getDeptMaterialSummaryList(String fromDate, String toDate, String department, String warehouseid,
			String category);

	String getCatalogueidsOfIndentRequest(String parentid);

	boolean checkAlreadyInTempGRNPO(String loginsessionid, String cataloguid, String location, String isfromworkorder);

	int changeShowingdate(String updchargeid, String showdate);

	ArrayList<Product> getCatalogueListForSale(String string, String location);

	ArrayList<Product> getProductListForSale(String val, String location);

	boolean getCheckStockFromGlobalID(String pid, String location);

	ArrayList<Master> getWareHouseListNew(String procurementid);

	String getProductCatalogueLocation(String catalogueid);

	int getCatalogueWiseSaleReprtCount(String fromdate, String todate, String product_id, String location,
			String product_type);

	ArrayList<Product> getCatalogueWiseSaleReprt(String fromdate, String todate, String product_id, String location,
			Pagination pagination, String product_type);

	int getNonMovingItemListcount(String department, String toDate, String filteroforder);

	int saveParentAdjustmentData(int status_type, String userid, String date, String location, int status);

	int getMultiAdjustEditDashboardCount(String fromdate, String todate, String location);

	ArrayList<Product> getMultiAdjustEditDashboard(String fromdate, String todate, String location,
			Pagination pagination);

	Product getMultiAdjustEditData(String parentid);

	int checkProductInAdjustment(String mdicinenameid);

	int deleteadjustmentrequest(String id, String userid);

	int saveChildMultiEditData(Product product, Product product1, String userid, String date, String location,
			int parentd);

	int checkProductInEdit(String mdicinenameid);

	int updateProductData(Product product);

	int getShelfIdFromName(String shelf, String location1);

	int updateRejectResonofMultiAdjustEdit(String id, String reason);

	int getProductIdFromGlobalIDWIthoutStckCheck(String pid, String location);

	ArrayList<Product> getVaccinationProductList(String location, String mastername);

	int getCatIdFromName(String prodname, String location);

	ArrayList<Product> getEditBillReport(String fromdate, String todate, String location, Pagination pagination, String searchtext, String userid);

	int getCountEditBillReport(String fromdate, String todate, String location, String searchtext, String userid);

	ArrayList<UserProfile> getPharmacyAndSystemUserid();

	int getCountJobWorkDashboard(String searchtext, String fromdate, String todate, String filter_status);

	ArrayList<Product> getJobWorkDashboard(String searchtext, String fromdate, String todate, String filter_status,
			Pagination pagination);

	int insertIntoTempJobWorkIssue(String loginsessionid, String issue_date, String issue_vendorid,
			String issue_isinventory, String issue_catalogueid, String issue_productname, String qty, String exp_date, String datetime);

	int deleteTempJobWorkIssueData(String loginsessionid);

	ArrayList<Product> getJobWorkIssueTempData(String loginsessionid);

	int checkJobWorkIssueTempId(String loginsessionid, String issue_catalogueid);

	int checkJobWorkIssueTempName(String loginsessionid, String issue_productname);

	int updateTempJobWorkIssueRemark(String id, String val);

	int deleteTempJobWorkIssueEntry(String id);

	int insertJobWorkIssue(String issue_date, String vendorid, String datetime, String userId);

	int insertJobTitleIssueChild(int parentid, Product product);

	ArrayList<Product> getJobWorkPendingRequest(String receipt_vendorid);

	int updateReceiptJobWorkStatus(String userId, String datetime, String receipt_status, String receipt_comment, int id);

	int getJobWorkCount(boolean totalrequest, String parentid);

	int updateJobWorkMainStatus(String parentid, String string);

	ArrayList<Product> getCatalogueListForJobWork();

	int cancelJobWork(String parentid, String delete_reason, String userid, String datetime);

	ArrayList<Product> getJobWorkPrint(String id);

	Product getJobWorkParentData(String id);

	ArrayList<Product> getJobWorkReport(String searchtext, String fromdate, String todate, String filter_status,
			Pagination pagination, String vendor);

	int getJobWorkReportCount(String searchtext, String fromdate, String todate, String filter_status, String vendor);

	double getLatestStock(String productid);

	boolean checkViewPresentOfTable(String tablename);

	void createViewOfTable(String oldTableName, String newTableName, String viewFromDate, String viewToDate);

	ArrayList<Product> getListOfOpeningClosingBatch(String fromDate, String toDate, Object object,
			String searchbyprodname, String product_id, String location_filter, Object object2, String filter_bydate);

	ArrayList<String> getUnitOfMessurementList();

	void updateProductNameFromCatalogue(String product_name, String id);

	String getConsumeProductQtySum(String consumeid);

	String getCatlogueProductCode(String catalogueid);

	int getCountOfOpeningClosingCatalogue1(String fromDate, String toDate, String searchbyprodname, String category_id,
			String location_filter, String filter_bydate);

	int saveGatePassParent(Product product);

	int saveChildGatePass(Product master);

	String generateGatePassSeq();

	ArrayList<Product> getGatePassList(String searchText, String fromdate, String todate, Pagination pagination, String gatePassTypeFilter, String vendor_id);

	int getGatePassCount(String searchtext, String fromdate, String todate, String gatePassTypeFilter, String vendor_id);


	Product getGatePassDetails(String id);

	ArrayList<Product> getGatePassProductList(String id);

	int checkLocationIsStore(String location);

	double getPoDiscountAmount(String procurementid);

	ArrayList<Product> getChildRequestedTempDataPrint(String parentid);

	ArrayList<Product> getSubCategoryList();

	ArrayList<Product> getsubcategorywiserpt(String fromDate, String toDate, String subcategory);

	ArrayList<Product> getMedicineWiseSaleReportList(String fromdate, String todate ,Pagination pagination, String payee, String tpid);

	int saveproductQtyLimit(Product product1);

	double getqtyProductLimit(String cataloguid, String description);

	int saveRequestedlimitqty(Product product);

	ArrayList<Product> getrequestedLimitQty(String fromDate, String toDate);

	int getApproveLimitQtyStatus(String indentid, String productid);

	String getdescription(int id);

	double getdeliverqty(String product_id, String string);

	double getrequestedlimitqty(String product_id, String string);

	int updateProductCode(int res, String vendocode);

	int getupadtereqlimitqty(String reqlimitqty, String catalogueid);

	
}
