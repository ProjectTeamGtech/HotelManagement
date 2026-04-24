package com.apm.Report.eu.bi;

import java.util.ArrayList;

import com.apm.Report.eu.entity.Commission;
import com.apm.common.utils.Pagination;

public interface CommissionDAO {

	//ArrayList<Commission> getCommissionList(int practitionerId);

	int getTotalCommissionCount();

	ArrayList<Commission> getPractitionerList(String fromDate, String toDate,String diaryuser,String location);

	ArrayList<Commission> getClientCommissionList(int practitionerId);

	ArrayList<Commission> getVisitingChargeList(String fromDate, String toDate, String diaryUser, String location);

	ArrayList<Commission> getIpdConsultingList(String fromDate, String toDate, String diaryUser, String location);

	ArrayList<Commission> getSurgeonChargeList(String fromDate, String toDate, String diaryUser, String location);

	ArrayList<Commission> getPractitionerShareList(String fromDate, String toDate, String diaryUser, String location);

	//ArrayList<Commission> getPractitionerList();

}
