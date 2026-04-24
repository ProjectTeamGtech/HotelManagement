package com.apm.Master.eu.bi;
import java.sql.Connection;
import java.util.ArrayList;

import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.MedicineType;
import com.apm.common.utils.Pagination;

public interface MedicineTypeDAO {

	ArrayList<MedicineType> getAllmedicinetypeList(String searchText, Pagination pagination);

	int addproductypelist(MedicineType master);
	int updateproductypelist(MedicineType master);



	MedicineType getmedicinetypemasterinfo(MedicineType master);

	int deleteproductlist(MedicineType master);

	int getTotalmedicinetypeCount();

	

}
