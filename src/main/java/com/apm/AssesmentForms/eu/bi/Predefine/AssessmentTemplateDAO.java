package com.apm.AssesmentForms.eu.bi.Predefine;

import java.util.ArrayList;

import com.apm.AssesmentForms.eu.entity.Assessment;
import com.apm.AssesmentForms.eu.entity.Predefine.AssessmentTemplate;



public interface AssessmentTemplateDAO {

	int saveTemplateDetails(AssessmentTemplate assessmentTemplate);

	int saveGeneralHealthDetails(int physioTemplateId,AssessmentTemplate assessmentTemplate);

	int savePainAreaDetails(int physioTemplateId,AssessmentTemplate assessmentTemplate);

	ArrayList<AssessmentTemplate> getPhysioTemplateList(String clientId, String practId, String conditionId);

	AssessmentTemplate getPhysioTemplateDetails(int selectedId);

	int updateTemplateDetails(int physioTemplateId,AssessmentTemplate assessmentTemplate);

	int updateGeneralHealthDetails(int physioTemplateId,AssessmentTemplate assessmentTemplate);

	int deletePhysioTemplate(int selectedId);

	int deleteGeneralHealthTemplate(int selectedId);

	AssessmentTemplate getGeneralHealthDetails(int selectedId);

	int savePhysioAssessmentFormName(String name);

	int savePhysioClientFormName(String name, int formId, String clientId, String practId, String conditionId, String img);

	String getTemplateName(String tempalteId);

	int updateImageTemplate(int selectedId, String imageData);

	String getTemplateId(String clientId, String practId, String conditionId);

	int getDetailsById(int selectedId);

	int deletePainAreaTemplate(int selectedId);

	int deleteClientAssessment(int selectedId);

	

}
