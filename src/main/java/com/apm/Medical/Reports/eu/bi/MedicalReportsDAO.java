package com.apm.Medical.Reports.eu.bi;

import java.util.ArrayList;

import com.apm.Medical.Reports.eu.entity.MedicalReports;

public interface MedicalReportsDAO {

	

	ArrayList<MedicalReports> getPendingPaymentList(String payby,
			String location, String diaryUser, String fromDate, String toDate,String order,String orderField,String thirdparty);

}
