package com.apm.Dietary.eu.bi;

import java.util.ArrayList;

import com.apm.Dietary.eu.entity.Dietary;
import com.apm.Ipd.eu.entity.Bed;

public interface DietaryDAO {
	ArrayList<Dietary> viewDietaryCategory();
	int addDietaryCategory(Dietary dietary);
	Dietary getinfoCategory(int id);
	int updateDietaryCategory(Dietary dietary);
	int deleteInfoCategory(Dietary dietary);
	ArrayList<Dietary> getctaegory();
	ArrayList<Bed> getAllIpdDetailsForDiet(String allward);
	 int saveGeneralDietPlan(Bed bed, String diaetplan, String remark, String date,String diettimeshift, int result);
	 ArrayList<Bed> printgeneraldietplan(String date,String filter_status,String id, String allward);
	 public String getCategoryName(String id);
	ArrayList<Bed> showgeneraldietplanlist(String fromdate, String todate);
	int saveGeneralDietPlanparent(String date, String userid, String dietshift, String dietician_incharge);
	String getDiettimeName(String id);
	public ArrayList<Bed> getEditIpdDetailsForDiet(String id);
	public ArrayList<Dietary> getdietCategory(int id);
	/*public int updateGeneralDietPlanParent(String id);*/
	public int updateGeneralDietPlan(String id,Bed bed);
	/*String getPreviousDietTimeShift(String parameter);*/
	String getPreviousDietTimeShift(String fromdate, String todate, String dietshift);
	public String getDieticianName(String id);
}
