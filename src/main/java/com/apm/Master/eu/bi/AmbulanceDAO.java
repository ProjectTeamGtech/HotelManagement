package com.apm.Master.eu.bi;

import java.util.ArrayList;

import com.apm.Master.eu.entity.Ambulance;
import com.apm.common.utils.Pagination;

public interface AmbulanceDAO {

	int getTotalambulance();

	ArrayList<Ambulance> getAllAmbulance(String searchText, Pagination pagination);

	int addambDB(Ambulance amb);

	ArrayList<Ambulance> getambulancelist();

	Ambulance getAmbulanceDetail(String selectedid);

	int updateAmbulanceDB(Ambulance amb);

	int deleteAmbulance(String selectedid);

	ArrayList<Ambulance> getDriverlist();

}
