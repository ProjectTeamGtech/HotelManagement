package com.apm.AssesmentForms.eu.bi;

import java.util.ArrayList;

import com.apm.AssesmentForms.eu.entity.Assessment;
import com.apm.DiaryManagement.eu.entity.Client;

public interface ListAssessmentDAO {

	ArrayList<String> getAssessmentFormList(String clientId, String clientname, String tempalteId, String practId, String conditionId);

	int getColumnLength(String tempalteId);

	ArrayList<Assessment> getColumnList(String tempalteId);

	String getTemplateId(String id);

	String getColumnString(String tempId);


	String getImageDetails(String id);

	ArrayList<Assessment> getFieldNameListById(String tempId, String id);

	ArrayList<String> getAssessmentFormClientDetails(String id,
			String columnStr, String tempId);

	ArrayList<Assessment> getColumnFieldList(String tempId);

	int getClientNameData(String tempId, String id);

	String getClientFullName(int clientId);

	int updateAssessmentFormClient(int id, String temp9, String lableName, String textName,String datetime);

	int updateAssessmentClientNameImage(int id, String clientId, String diaryUserId,String conditionId);

	int deleteAssessmentClient(int id);

	ArrayList<Assessment> getTemplateList(String id, String practId, String conditionId);

	String getAssessmentClientId(String clientId);

	Assessment getClientDetailsData(String tempId, String id);

	String getDiaryUserName(String diaryUserId);

	String getConditionName(String conditionId);

	int updateImageTemplate(int id, String imageData);

	String getTemplateId(int id);

	int updateCombineImageTemplate(String id, String imageData);
	

}
