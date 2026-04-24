package com.apm.Appointment.eu.bi;

import java.util.ArrayList;

import com.apm.Appointment.eu.entity.Branch;


public interface BranchDAO {

	int saveBranch(Branch branch);

	ArrayList<Branch> getBranch();

	Branch getBranch(int selectedid);

	int updateBranch(Branch branch, String userID);

	int deleteBranch(int selectedid);

	boolean isUserExist(String userid);
	Branch getuser(String userID)throws Exception;

	
	int updateDeliveredStatus(int categoryID, int subcategoryID)throws Exception;


	

}
