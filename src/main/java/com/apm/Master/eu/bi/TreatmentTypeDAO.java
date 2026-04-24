package com.apm.Master.eu.bi;

import java.util.ArrayList;

import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.TreatmentType;
import com.apm.common.utils.Pagination;

public interface TreatmentTypeDAO {

	int getTotalTreatmentTypeCount(String searchText);

	ArrayList<TreatmentType> getTreatmentTypeList(Pagination pagination, String searchText);

	int saveTreatmentType(TreatmentType treatmentType);

	TreatmentType getTreatmentType(int id, TreatmentType treatmentType);

	int updateTreatmentType(TreatmentType treatmentType, int id);
	
	int deleteTreatmentType(int id);

	ArrayList<TreatmentType> getConditionList();
	public ArrayList<Master> getMasterList();
	public ArrayList<TreatmentType> getDeptConditionList();
	public TreatmentType getDepartmentCondition(int id);

	int updateDeleteDiagnosis(int id);

	ArrayList<TreatmentType> getsmstemplateList();

}
