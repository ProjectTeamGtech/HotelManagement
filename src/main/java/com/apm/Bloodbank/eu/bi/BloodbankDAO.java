package com.apm.Bloodbank.eu.bi;

import java.util.ArrayList;

import com.apm.Bloodbank.eu.entity.Bloodbank;
import com.apm.Inventory.eu.entity.Product;
import com.apm.common.utils.Pagination;

public interface BloodbankDAO {

	ArrayList<Bloodbank> getBloodgroupList();

	ArrayList<Bloodbank> getBloodonorList();
	int addBloodGroup(Bloodbank bloodbank);
	int addBloodDonor(Bloodbank bloodbank);

	int updateBloodGroup(Bloodbank bloodbank);

	Bloodbank getBloodbankDonor(String id);

	int updateBloodDonor(Bloodbank bloodbank);

	Bloodbank getBloodbankGroup(String id);

	int deleteDonor(String id);

	ArrayList<Bloodbank> getBloodonorList(String searchText);

	int saveBloodtoPatient(Bloodbank bloodbank);

	ArrayList<Bloodbank> getAllDonortoPatient();

	Bloodbank getDonortoPatient(String id);

	int updateDonateToPatient(Bloodbank bloodbank);
	public String getGroupidfromGroup(String group);

	int addBloodRequest(Bloodbank bloodbank);

	ArrayList<Bloodbank> getAllRequestedBloodList(String name, String fromdate, String todate,
			String from, String bloodgroup, String status,Pagination pagination);

	int updateBloodAllocate(String id, String alloc);
	ArrayList<Bloodbank> getBloodonorListByGroup(String bloodgroup);

	int saveBloodBank(Bloodbank bloodbank);
    
	public ArrayList<Bloodbank> getAllBankList();

	Bloodbank getBloodBankDetails(String id);

	int updateBloodBank(Bloodbank bloodbank);

	int deleteBloodBank(String id);

	int getAllReuestedBloodListCount(String name, String fromdate,String todate ,String from,
			String bloodgroup, String status);

	ArrayList<Bloodbank> getBloodRequestList(String clientid, String ipdid, String date);

	int saveCrossMatchData(String id, String productid, String userid, String dateTime);

	int saveIssueBloodData(String id, String handoverto, String userid, String dateTime);

	Product getBloodBankRequestDetails(String bloodbankid);

	int updateBloodBankStatus(String bloodbankid, String userid, String dateTime, String status);
}
