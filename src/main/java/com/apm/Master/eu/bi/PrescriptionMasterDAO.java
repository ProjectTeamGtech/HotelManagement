package com.apm.Master.eu.bi;

import java.util.ArrayList;

import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.Inventory.eu.entity.Product;
import com.apm.Master.eu.entity.Master;
import com.apm.common.utils.Pagination;

public interface PrescriptionMasterDAO {

	public ArrayList<Priscription> getPrescriptionCategoryList();
	public ArrayList<Priscription> getPrescriptionCategoryList(Pagination pagination, String searchText);
	public int getTotalPrescriptionCategoryCount(String searchText);
	public int addPrescriptionCategory(Priscription priscription);
	public Priscription getPrescriptionCategory(String selectedid);
	public int updatePrescriptionCategory(Priscription priscription);
	public int deletePrescriptionCategory(String selectedid);
	public ArrayList<Priscription> getPrescriptionDetails();
	public ArrayList<Priscription> getPrescriptionDetails(Pagination pagination, String searchText);
    public int getTotalPrescriptionDetailsCount(String searchText);
	public int addPrescriptionDetails(Priscription priscription);
	public Priscription getPrescriptionDetails(String selectedid);
	public Priscription getPrescriptionDetailsByName(String selectedid);
	public int updatePrescriptionDetails(Priscription priscription);
	public int deletePrescriptionDetails(String selectedid);
	public ArrayList<Priscription> getAllMedicineGenericList();
	public ArrayList<Master> getAllMedicineLocation(String selectedid);
	public ArrayList<Master> getAllMedicineSpecialization(String selectedid);
	public int addToMedicineMaster(Product product,int catalogueid, String istemp);
	public Priscription getPrisc(String id);
	public String getComponentFromMedId(String string);
}
