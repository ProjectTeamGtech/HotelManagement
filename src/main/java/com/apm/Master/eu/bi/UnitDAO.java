package com.apm.Master.eu.bi;

import java.util.ArrayList;

import com.apm.Master.eu.entity.Unit;

public interface UnitDAO {

	int saveUnit(Unit unit);

	ArrayList<Unit> getallunitlist(String seachtext);

	Unit geteditUnit(String selectedid);

	int getupdateunit(Unit unit);

	int deleteUnitdata(String selectedid);

}
