package com.apm.AssesmentForms.eu.bi;

import java.util.ArrayList;

import com.apm.AssesmentForms.eu.entity.Assessment;
import com.apm.common.utils.Pagination;

public interface AssessmentMasterDAO {

	ArrayList<Assessment> getAssessmentFieldList(Pagination pagination);

	int getTotalAssessmentCount();

	int saveAssessmentField(Assessment assessment, String temp);

	Assessment getAssessmentFieldDetails(int selectedid);

	int updateAssessmentField(String temp1, Assessment assessment, int selectedid);

	int deleteAssessmentField(int selectedid);

	int updateAssessmentTemplate(String temp);

	String getExistingFieldName(int selectedid);

	int updateAssessmentTemplateColumn(String updatedFieldName, String existingFieldName);

	int deleteAssessmentTemplateColumn(String existingFieldName);

	int getTotalSubHeadingCount();

	ArrayList<Assessment> getAssessmentSubHeadingList(Pagination pagination);

	int saveAssessmentSubHeading(Assessment assessment);

	Assessment getAssessmentSubHeadingDetails(int selectedid);

	int updateAssessmentField(Assessment assessment, int selectedid);

	int deleteAssessmentSubHeading(int selectedid);

	int updatdateTemplateAssesmentFields(String temp, String temp1,
			String updatedFieldName);



}
