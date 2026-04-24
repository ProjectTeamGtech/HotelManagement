package com.apm.Emr.eu.bi;

import java.util.ArrayList;

import com.apm.Master.eu.entity.Master;

public interface NursingDAO {

	int saveParentNursing(Master master);

	int saveNusingData(int saveparent, Master master2);

	ArrayList<Master> nusringCareListOfPatient(String ipdId);
	ArrayList<Master> childListOfNursingCare(String parentid);
}
