package com.apm.AssesmentForms.eu.bi;

import java.util.ArrayList;

import com.apm.AssesmentForms.eu.entity.Assessment;
import com.apm.AssesmentForms.web.action.Template;
import com.apm.Master.eu.entity.Master;

public interface AssessmentFormDAO {

	ArrayList<Assessment> getFieldList();

	ArrayList<String> getFieldTextList();

	ArrayList<Assessment> getTemplateList();

	//int SaveTemplateField();

	int saveUpdateTemplateField(int valueId, String fieldValue, String textName, String templateName, String typeValue, String sizeValue,String rptvalue);

	int saveTemplate(String templateName, String pic,String datetime,boolean includeimg,String tempNote,String specialization,String layout,String formtype,String rowsno,String hdrcolumn);

	ArrayList<Assessment> getFieldNameListById(String templateId);

	int saveUpdateTemplateDetails(int fieldId, String fieldValue,
			String textName, String templateId,String datetime);

	//int saveIdOfTemplate(String templateId, String clientId,String imageData, String diaryUserId, String conditionId,String date,String ipdid,String oldpractid);
	
	int saveIdOfTemplate(String templateId, String clientId,String imageData, String diaryUserId, String conditionId,String date,String ipdid,String oldpractid,String mrdid,String apmtid);
	
	ArrayList<Assessment> getHeadingFieldList();

	String getImageDetails(String templateId);

	int insertImageData(int valueId, String imageData);

	boolean IsTemplateNameExist(String templateName);

	ArrayList<Assessment> getTemplateFieldList();

	ArrayList<Assessment> getAvailableFieldList(String tempListNameId);

	ArrayList<Assessment> getSelectedFieldList(String tempListNameId);

	String getTemplateName(String templateId);

	int updateTemplate(String templateName, String pic,String datetime,boolean includeimage,String templateNote,String specialization,String layout,String formtype,String rowsno,String hdrcolumn);

	int updateTemplateField(int valueId, String temp9, String lableValue,
			String templateName, String typeValue, String sizeValue, int id);

	int deleteTemplateField(int id);

	int deleteExistingId(int valueId);

	int updateImageTemplate(String templateId, String imageData);

	ArrayList<Master> getCustomDataList();

	int deleteClientTemplateFieldData(String selectedid);

	int deleteTemplate(String selectedid);

	ArrayList<Assessment> getClientAssesementFormList(String clientId, String clientName);

	int delteTemplateField(int valueId);

	int deleteTemplateFieldByTempid(String selectedid);

	Template getTemplateDetails(String templateId);

	ArrayList<Master> getSpecializationList();

	boolean checkIsRepeatTemplate(String templateId);

	int alterNewColumn(String temp10);

	int saveRepeatFormData(String strtxt, String temp10, String templateId,String clientid,String diaryuserid,String conditionid,int fieldid);
	
	ArrayList<Assessment>getRepeatFormData(Template template,String columnname,String clientassesmentfieldid);

	int updateRepeatFormData(String strtxt, int id,String column,String clientid,String diaryuserid,String conditionid);

	int getIpdBedno(String clientId);

	String getAdmissionid(String clientId);

	int sveHdrColumnData(String string, int valueId,String dbcname);

	ArrayList<Master> getLeftNameList(String templateId);

	ArrayList<Master> getTopNameList(String templateId);

	boolean checkColumnExist(String subjectname);

	int createColumnName(String subjectname);

	int saveIdOfCombineTemplate(String templateId, String clientId, String imageData, String diaryUserId,
			String conditionId, String ukCurrentDataTime, String sessionadmissionid, String oldpractid, Object object,int leftnameid);

	int saveUpdateCombineTemplateDetails(int fieldId, String subjectname, String textName, String ukCurrentDataTime);

	boolean checkTermExist(int id, String templateId, String clientId, String diaryUserId, String conditionId,int leftnameid);

	int getCombineDataid(int id, String templateId, String clientId, String diaryUserId, String conditionId, int id2);

	Assessment getCombineFormEditDetails(String id);

	int getRowCount();

	
}
