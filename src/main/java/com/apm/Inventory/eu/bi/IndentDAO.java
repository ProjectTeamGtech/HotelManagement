package com.apm.Inventory.eu.bi;

import java.text.DateFormat;
import java.util.ArrayList;

import com.apm.Inventory.eu.entity.Procurement;
import com.apm.Inventory.eu.entity.Product;
import com.apm.common.utils.Pagination;

public interface IndentDAO {

	ArrayList<Product> getAscStockWiseProductList(double req_stock, Product prod);

	String getCatIdProdId(String product_id);

	ArrayList<Product> getLocationWiseStock(Product prod, Product product);

	ArrayList<Product> getProductLimitedFromOtherLocation(Product prod, String location);

	int requestProductTemporarySave(int prodid, String location, String qty, String parentid, String product_name,
			String transfer_date, int count5, String catid);

	ArrayList<String> getChildProductName(String parentid);

	double getReqQtyFrmTemp(String prod_name, String parentid);

	Product getReqQtyFrmAct(String parentid, String prod_name);

	int updateChildTempTransferStatus(String prod_name, String parentid, int status);

	double getAllAvailableStockByCatId(String cataloguid, String location);

	String getProdIdCatId(String catalogueid);

	ArrayList<Product> getChildTranfserData(String parentid);

	int getTotalReqTransfer(String parentid);

	int transferIssueProduct(Product product, String comment1, String userId, String todate, String isfromcathlab);

	int getCountPatientConsumptionReport(String fromdate, String todate, String searchtext);

	ArrayList<Product> getPatientConsumptionReport(String fromdate, String todate, Pagination pagination, String searchtext, String hisdepartmentfilter);

	boolean checkLocationInWarehouseid(String loc);

	String getWarehouseNameFromId(String string);

	int cancelRequestedEntry(String id, String delete_reason, String userid, String dateTime);

	String pharmacyLocationNameByID(String location);

	int getCountIndentTransferLog(String fromdate, String todate, String location1, String filter_status,
			String location_filter, String searhText, String userwise, String transferedlocation);

	ArrayList<Product> getIndentTransferLog(String fromdate, String todate, String location1, String filter_status,
			String location_filter, String searhText, Pagination pagination, String userwise, String transferedlocation);
	 ArrayList<Product> getIndentLog(String fromdate, String todate, String location1, String filter_status,
			   String location_filter, String searhText, Pagination pagination, String userwise);

	int getNewCountIndentTransferLog(String fromdate, String todate, String location1, String filter_status,
			String location_filter, String searhText, String userwise);

	int getStockByProdId(int prodid);

	int changeIndentRTTstatus(Procurement procurement);

	int getIssueToProductSrNo();

	int getCountUserConsumptionReport(String fromdate, String todate, String searchtext);

	ArrayList<Product> getUserConsumptionReport(String fromdate, String todate, Pagination pagination,
			String searchtext);

	boolean checkIndentDelivered(String location1, String todate, String todate2);

	boolean checkIndentApproved(String location1, String todate, String todate2);

	boolean checkIndentRequested(String location1, String fromdate1, String todate);

	int getCountUserPatientConsumptionReport(String fromdate, String todate, String searchtext, String hisdepartmentfilter);

	ArrayList<Product> getUserPatientConsumptionReport(String fromdate, String todate, Pagination pagination,
			String searchtext, String hisdepartmentfilter);

	int saveParentCathlabTemplate(String cathtempprocedure, String cathtemplatename, String userId, String datetime, String comment1);

	int saveChildCathlabTemplate(int prodid, String product_name, String tqty, String catalogueid, int parentid);

	ArrayList<Product> getCathlabTemplateList(String string);

	int savePatientTranferlog(String isfromcathlab, String userId, String datetime, String cathtempid, String comment1);

	int getCountCathLabConsumptionReport(String fromdate, String todate, String searchtext);

	ArrayList<Product> getCathLabConsumptionReport(String fromdate, String todate, Pagination pagination,
			String searchtext);

	int transferIssueProductUpdate(Product product, String comment1, String userId, String todate, String consumeid);

	int updateParentCathlabTemplate(String cathtempprocedure, String cathtemplatename, int parentid);

	int updateChildCathlabTemplate(int prodid, String product_name, String tqty, String catalogueid, int parentid);

	int deleteChildCathTempData(String t);

	ArrayList<String> getDirectTransferProduct(String product_id);

	ArrayList<String> getRequestTransferProduct(String oldprodid);

	int updateChildIndentProductChange(String childid, String changecatlid, String productid);

	int insertIntoChangeIndentProduct(String oldcatid, String childid, String parentid, String changecatlid,
			String comment, String todate, String userid);

	int getCountRequestIndentReport(String fromdate, String todate, String location_filter, String searhText);

	ArrayList<Product> getRequestIndentReport(String fromdate, String todate, String location_filter, String searhText);

	ArrayList<Product> getDirectIndentReport(String fromdate, String todate, String location_filter, String searhText, String filter_status);

	int getCountDirectIndentReport(String fromdate, String todate, String location_filter, String searhText, String filter_status);

	Product getChildProductFromChildId(String id);

	int saveDeleteIndentCharge(Product product);

	ArrayList<Product> getchildDeleteList(String parentid);

	ArrayList<Product> getChildMultiAdjustEditData(String parentid);

	int updateMultiAdjustment(String id, int status, String userid, String date);

	int updateMultiChildAdjustment(String parentid, String comment);

	ArrayList<Product> getChildMultiAdjustprintData(String parentid);

	int checkIndentInTransfer(String parentid);

	int checkIndentInTransferUserWise(String parentid, String userId);

	int insertIntoIndentInTransfer(String parentid, String userId, String datetime);

	Product getIndentTempAddLog(String parentid);

	void deleteTempIndentInTransfer(String datetime5);

	int updateModifyConfirmStatus(int parentid);
}
