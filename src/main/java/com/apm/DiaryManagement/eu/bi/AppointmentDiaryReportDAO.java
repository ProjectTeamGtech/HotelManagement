package com.apm.DiaryManagement.eu.bi;

import java.util.ArrayList;

import com.apm.DiaryManagement.eu.entity.AppointmentDiaryReport;
import com.apm.common.utils.Pagination;

public interface AppointmentDiaryReportDAO {

	ArrayList<AppointmentDiaryReport> getDiaryUserList(int id);

	ArrayList<AppointmentDiaryReport> getDiaryReportList(String diaryUserId,
			String fromDate, String toDate, Pagination pagination);

	ArrayList<AppointmentDiaryReport> getDepartmentList();

	ArrayList<AppointmentDiaryReport> getAllWalkInPreBookedList();

	ArrayList<AppointmentDiaryReport> getAllBookedList(String date,
			String diaryUserId, String dept, boolean arrived, boolean beingSeen, boolean completed, boolean dna);

	int getTotalApmtReportCount(String diaryUserId, String fromDate,
			String toDate);

}
