package com.apm.Report.eu.bi;

import java.util.ArrayList;

import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.Report.eu.entity.MisReport;
import com.apm.common.web.common.helper.LoginInfo;

public interface MisDAO {

	ArrayList<MisReport> getMisRecord(String fromDate, String toDate,LoginInfo loginInfo);

	ArrayList<NotAvailableSlot> getThreeDayRefList(String fromDate,
			String toDate);

	ArrayList<NotAvailableSlot> getTwentyEightRefList(String fromDate,String toDate);

	int getDnaCount(String fromDate, String toDate);

	int getLetterCount(String fromDate,String toDate);

	int getTotalAppt(String fromDate, String toDate);

	int getNewRefralCount(String fromDate, String toDate);

	int getNewRefralTriggeredCount(String fromDate, String toDate);

	ArrayList<NotAvailableSlot> getNewREfralNotTrigeredList(String fromDate,
			String toDate);

	int getCountInitialAssessment(String fromDate, String toDate);

	int getCountFollowupAttendence(String fromDate, String toDate);

	int getRoutineCount(String fromDate, String toDate);

	int getUrgentCount(String fromDate, String toDate);

	int getDischargeCount(String fromDate, String toDate);

	int getCountCancelledApmt(String fromDate, String toDate);

	ArrayList<NotAvailableSlot> getGreaterThanSixFollowups(String fromDate,
			String toDate);

}
