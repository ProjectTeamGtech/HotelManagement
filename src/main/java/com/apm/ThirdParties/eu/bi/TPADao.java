package com.apm.ThirdParties.eu.bi;

import java.util.ArrayList;

import com.apm.ThirdParties.eu.entity.ThirdParty;
import com.apm.common.utils.Pagination;

public interface TPADao {

	ArrayList<ThirdParty> getTpaList(String fromdate,String todate,String searchtext,Pagination pagination, int i);

	int updateTreatmentEpisodetoSubmit(String treatmentEpisodeId);

	int rejectTpaStatus(String treatmentepisodeid);

	int acceptTpaStatus(String treatmentepisodeid);

	int cancelTpa(String id, String delete_reason);

	

	int getTotalTpaListCount(String fromdate,String todate,String searchtext);

}
