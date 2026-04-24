package com.apm.Dietary.eu.bi;

import java.util.ArrayList;
import com.apm.Dietary.eu.entity.DietaryDetails;
import com.apm.Ipd.eu.entity.Bed;

public interface DietaryDetailsDAO {
	ArrayList<DietaryDetails> getdietplanfrdteam();
	ArrayList<DietaryDetails> getspecificdietlist(String dailydietplankitchen);
	int deleteplanfrmchild(String parentid);
	int deleteplanfrmparent(String parentid);
	ArrayList<DietaryDetails> getalldeliveryuser();
	ArrayList<DietaryDetails> getalldietplan(String id);
	ArrayList<DietaryDetails> getcafeteriaplan();
	ArrayList<DietaryDetails> getdietserveplan(String fromdate, String todate, String searchtext, String wardnameid);
	ArrayList<DietaryDetails> viewDietaryDetails();
	ArrayList<DietaryDetails> getkitchenplan();
	int addDietarydetails(DietaryDetails details);
	DietaryDetails getinfoDetails(int id);
	int updateDietary(DietaryDetails dietaryDetails);
	int deleteInfo(DietaryDetails dietary);
	ArrayList<DietaryDetails> getctaegorydetails();
	ArrayList<DietaryDetails> getcaloriesdetails();
	ArrayList<DietaryDetails> getcategorydetailslist(String id);
	ArrayList<DietaryDetails> getSelCalories(String id);
	DietaryDetails getcategoryname(String id);
	DietaryDetails getSubcategoryname(String id);
	DietaryDetails getEnergyname(String calories_id);
	int storedietaryparent(String admissionid,String date, String time);
	int storedietaryplan(DietaryDetails dietaryDetails, int result, String admissionid,String date);
	String getcatidfromname(String categoryname);
	String getsubcatidfromname(String subcategoryname);
	String getcalidfromname(String calories);
	String getipdidfrmpid(String id);
	ArrayList<DietaryDetails> getParantIdList(String ipdid,String fromdate,String todate);
	int updateDietData(DietaryDetails details, int parentid, String ipdid, String childid);
	int deleteDataFrmChild(String childid);
	int storedietaryparentTemplate(String addmissionid, String templatename);
	int storeDietaryChildTemplate(DietaryDetails dietaryDetails, int result,
			String addmissionid);
	ArrayList<DietaryDetails> getTemplateList(String ipdid);
	ArrayList<DietaryDetails> getTemplateDataFrmChild(String parentid);
	ArrayList<DietaryDetails> getExistDiet(String todate);
	ArrayList<DietaryDetails> getAllIpdDietplan();
	DietaryDetails getSingleDietplan(String parentid, String serachDiet);
	DietaryDetails getIpdPerPatientDiet(String ipdid);
	ArrayList<DietaryDetails> getDietPlanList();
	ArrayList<DietaryDetails> getDietFeedList();
	String getFeedNameFromId(String feed);
	ArrayList<DietaryDetails> getSingleDietplanList(String parentid, String string);
	int updatedietarygivenstatus(String parentid, String dietplan, String val, String userid, String time);
	ArrayList<DietaryDetails> getIpdDietPlan(String ipdid, String date);
	String getDietPlanName(String dietplan);
	String mutiParentIds(String ipdId);
}
