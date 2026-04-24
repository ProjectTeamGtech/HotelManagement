package com.apm.ThirdParties.eu.bi;

import java.util.ArrayList;

import com.apm.ThirdParties.eu.entity.GP;
import com.apm.common.utils.Pagination;

public interface GPDAO {

	int getTotalGPCount();

	ArrayList<GP> getGPList(Pagination pagination);

	ArrayList<GP> getTPCompanyList();

	int saveGPDetail(GP gp);

	GP getGPDetail(int id);

	int updateGPDetail(GP gp, int id);

	int deleteGPDetail(int id);

	int getTotalGPCountOfSearch(String searchGP);

	ArrayList<GP> geGPListOfSearch(String searchGP,	Pagination pagination);

	int getGPId(int selectedid);

}
