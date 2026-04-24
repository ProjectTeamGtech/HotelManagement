package com.apm.Master.eu.bi;

import java.util.ArrayList;

import com.apm.Master.eu.entity.State;
import com.apm.common.utils.Pagination;

public interface StateDAO {
	ArrayList<State> getallState(String searchText, Pagination pagination);
	int addstateDB(State state);
	int deleteStateDB(State state);
	State getstateinfo(State state);
	int updateStateDB(State state);
	int getTotalStateCount();
	ArrayList<State> getallState(String searchText);
}
