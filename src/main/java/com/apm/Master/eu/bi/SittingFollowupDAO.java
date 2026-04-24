package com.apm.Master.eu.bi;

import java.util.ArrayList;

import com.apm.Master.eu.entity.SittingFollowup;

public interface SittingFollowupDAO {

	int saveSitting(SittingFollowup sf);

	ArrayList<SittingFollowup> getsittinglist();

	SittingFollowup geteditsitting(String selectedid);

	int updatesitting(SittingFollowup sf);

	int deletesittingData(String selectedid);

	ArrayList<SittingFollowup> getAllSittingList(String id);

	String departmentid(String string);

	

}
