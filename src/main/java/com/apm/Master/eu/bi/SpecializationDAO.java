package com.apm.Master.eu.bi;

import java.util.ArrayList;

import com.apm.Master.eu.entity.Master;
import com.apm.common.utils.Pagination;

public interface SpecializationDAO {

	int getTotalSpecializationCount();

	ArrayList<Master> getSpecializationList(Pagination pagination);

	int saveSpecialization(Master master);

	Master getSpecializationData(int selectedId, Master master);

	int updateSpecialization(int id, Master master);

	int deleteSpecialization(int id);
	public ArrayList<Master> getMasterList();

}
