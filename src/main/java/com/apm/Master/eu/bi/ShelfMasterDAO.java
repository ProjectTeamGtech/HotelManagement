package com.apm.Master.eu.bi;

import java.util.ArrayList;

import com.apm.Master.eu.entity.ShelfMaster;
import com.apm.Master.eu.entity.State;

public interface ShelfMasterDAO {
	ArrayList<ShelfMaster> getallShelf();
	int addshelfDB(ShelfMaster shelfMaster);
	int deleteshelfDB(ShelfMaster shelfMaster);
	ShelfMaster getshelfinfo(ShelfMaster shelfMaster);
	int updateshelfDB(ShelfMaster shelfMaster);
	int checkAlreadyShelfPresent(String name, String departmentid, String id, String val);
}
