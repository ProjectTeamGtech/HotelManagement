package com.apm.Master.eu.bi;

import java.util.ArrayList;

import com.apm.Master.eu.entity.CityMaster;
import com.apm.common.utils.Pagination;

public interface CityMasterDAO {
	
	int addcityDB(CityMaster cityMaster);
	int deletecityDB(CityMaster cityMaster);
	CityMaster getcitydetails(CityMaster cityMaster);
	int updatecityDB(CityMaster cityMaster);
	ArrayList<CityMaster> getallCity();
	ArrayList<CityMaster> getallCity(Pagination pagination,String searchText);
	int getTotalCityCount();
	
}
