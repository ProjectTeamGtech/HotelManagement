package com.apm.Emr.eu.bi;

import java.util.ArrayList;

import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.Inventory.eu.entity.Product;
import com.apm.common.utils.Pagination;
import com.apm.common.web.common.helper.LoginInfo;

public interface PrescriptionDAO {
	
    public ArrayList<Priscription> getAllPriscriptionList(Pagination pagination,String searchText,String fromdate,String todate, LoginInfo loginInfo, String phar_wardid, String filter_location, String filter_phar_location);

	public int getAllPriscriptionCount(String searchText,String fromdate,String todate,String location);

	public int getDstatus(String id);

	public int updateDstatus(String id, int status, String ddate);

	public ArrayList<Priscription> getchargedMdicineList(String selectedid);

	public Product getProductDetails(String mdicinenameid);

	public int updateMedicineStatus(String treatmentepisodeid,String datetime,String mstatus);
	public ArrayList<Priscription> getAllPharmacyList(Pagination pagination,String searchText,String fromdate,String todate,String ipdloc,String opdloc,String location, LoginInfo loginInfo);
	public ArrayList<Priscription> getInternalClientData(Pagination pagination,String fromdate,String todate,String location,String searchtext, String paymode,LoginInfo loginInfo, String isreturn);
	public ArrayList<Priscription> getEstimateBills(Pagination pagination,String fromdate,
			String todate,String location,String searchtext,LoginInfo loginInfo);
	public ArrayList<Priscription> getExternalClientData(Pagination pagination,String fromdate,
			String todate,String location,String searchtext, String paymode,LoginInfo loginInfo, String isreturn);

	public ArrayList<Priscription> getAllPriscriptionListWithoutPagination(String fromdate, String todate);

	public int getAllPriscriptionCountWithoutLocation(String fromdate, String todate);

	public int getAllPriscriptionCountDelivered(String fromdate, String todate);

	public int getAllPriscriptionCountNotDelivered(String fromdate, String todate);
	
	public ArrayList<Priscription> getPriscclientforApiList(String clientid);
	public Priscription getPriscriptionParentData(String parentid);

	public ArrayList<Priscription> getMultiplePriscRequest(String id, String location);

	public ArrayList<Priscription> getMultiplePriscRequestNew(String id);

	public int getAllPriscriptionCountNew(String searchText, String fromdate, String todate, String string, String phar_wardid, String filter_location, String filter_phar_location);

	public ArrayList<Priscription> getPriscriptionAgainstSale(String formdate, String todate);

	public int updatePriscChildDeliverStatus(String childid, String date, String userId);

	public int getAllChildPriscCount(String parentid, int i);

	public int updateParentPriscDeliverStatus(String parentid, int i, String userId, String date);

	public int updateNotDeilverStatusChild(String priscreqids, String parentid);

	public int saveParentReturnPrisc(Priscription priscription, String remark, String parentid);

	public int saveChildReturnPrisc(Priscription priscription, String mdrequestqty, String parentid, String childid,
			int newparentid);

	public int getReturnQtyAgainstPrisc(int id);

	public int getReturnPriscDashboardCount(String searchText, String fromdate, String todate);

	public ArrayList<Priscription> getReturnPriscDashboard(Pagination pagination, String searchText, String fromdate,
			String todate);
	public ArrayList<Priscription> getChildPriscData(int int1);

	public int cancelPrescription(String parentid, String delete_reason, String userid, String date);

	public int saveprinttakenstatus(String id, String val1);

	public String getPriscTokenNo(int newsessiid);

	public String getPriscParentTokenNo(int newsessiid);

	public int updatePriscTokenStatus(int selectedid, int i);

	public int getPriscLastLocationId(int parseInt);

	public String getfakedatebyClientid(String fakclientid);
	
    
}
