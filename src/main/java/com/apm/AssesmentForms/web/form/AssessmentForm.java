package com.apm.AssesmentForms.web.form;

import java.util.ArrayList;
import java.util.Collection;
import java.io.File;














import com.apm.AssesmentForms.eu.entity.Assessment;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.Master.eu.entity.Master;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.Location;

public class AssessmentForm {
	
	//combine form
	private String formtype;
	private String rowsno;
	private String hdrhisory;
	private String hdrcolumn;
	String num_admission="";

	private String clinicLogo;
	public String getFormtype() {
		return formtype;
	}

	public void setFormtype(String formtype) {
		this.formtype = formtype;
	}

	public String getRowsno() {
		return rowsno;
	}

	public void setRowsno(String rowsno) {
		this.rowsno = rowsno;
	}

	public String getHdrhisory() {
		return hdrhisory;
	}

	public void setHdrhisory(String hdrhisory) {
		this.hdrhisory = hdrhisory;
	}

	public String getHdrcolumn() {
		return hdrcolumn;
	}

	public void setHdrcolumn(String hdrcolumn) {
		this.hdrcolumn = hdrcolumn;
	}

	private String mrd_clientid;
	
	private String mrdid;
	
	private int id;
	
	private String filedname;
	
	private int clinicId;
	
	private String pagerecords;
	
	private int totalRecords;
	
	private String message;
	
	private String field;
	
	private String templateName;
	
	private String Last_Name;
	
	private String templateId;
	
	private String clientName = "";
	
	private String client = "";
	
	private String clientId = "";
	
	private String headingName;
	
	private String imageData = "";
	
	private String lableValueData = "";
	
	private String hiddenTemplateName = "";
	
	private String type;
	
	private String typeValueData = "";
	
	private String sizeValueData = "";
	
	private String typeValue;
	
	private String sizeValue;
	
	private ArrayList<String> assessmentFormsList;
	
	private int columnSize;
	
	private ArrayList<Assessment>importImageList;
	private String[] imagename;
	private String filepath;
	private File[] fileUpload;
    private String[] fileUploadFileName;
    private String[] fileUploadContentType;
    
    private String clientFullName = "";
    
    
    private String clinicName;
	private String clinicOwner;
	private String clinicemail;
	private String clinicaddress;
	private String clinicity;
	private String websiteUrl;
	private String landLine;
	private String owner_qualification;
	private ArrayList<Clinic> locationAdressList;
	
	private ArrayList<Master>customdataList;
	
	 private String specialization;
	 
	 private ArrayList<Master>specializationList;
	 
	 private String layout;
	 
	 private String repeatValueData;
	 
	 private boolean repeat;
	
	 
	 private String ipdid;
	 
	 private String wardname;
	 
	 private String bedname;
		
	 private String oldpractid;	
		
		
	 private String bodytemplate;
	 private String bodyapmtid;
	 
	 
		
		
		public String getBodytemplate() {
		return bodytemplate;
	}

	public void setBodytemplate(String bodytemplate) {
		this.bodytemplate = bodytemplate;
	}

	public String getBodyapmtid() {
		return bodyapmtid;
	}

	public void setBodyapmtid(String bodyapmtid) {
		this.bodyapmtid = bodyapmtid;
	}

		public String getOldpractid() {
		return oldpractid;
	}

	public void setOldpractid(String oldpractid) {
		this.oldpractid = oldpractid;
	}

		public String getIpdid() {
		return ipdid;
	}

	public void setIpdid(String ipdid) {
		this.ipdid = ipdid;
	}

	public String getWardname() {
		return wardname;
	}

	public void setWardname(String wardname) {
		this.wardname = wardname;
	}

	public String getBedname() {
		return bedname;
	}

	public void setBedname(String bedname) {
		this.bedname = bedname;
	}

		public boolean isRepeat() {
			return repeat;
		}

		public void setRepeat(boolean repeat) {
			this.repeat = repeat;
		}
	 
	 
	
	public String getRepeatValueData() {
		return repeatValueData;
	}

	public void setRepeatValueData(String repeatValueData) {
		this.repeatValueData = repeatValueData;
	}

	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public ArrayList<Master> getSpecializationList() {
		return specializationList;
	}

	public void setSpecializationList(ArrayList<Master> specializationList) {
		this.specializationList = specializationList;
	}

	public boolean isIncludeImage() {
		return includeImage;
	}

	public void setIncludeImage(boolean includeImage) {
		this.includeImage = includeImage;
	}

	public String getTemplateNote() {
		return templateNote;
	}

	public void setTemplateNote(String templateNote) {
		this.templateNote = templateNote;
	}

	private ArrayList<Assessment>clientAssesmentformList;
	
	private String tempaction = "";
	
	private boolean includeImage;
	
	private String templateNote;
	
    
	
	
	
	
	public String getTempaction() {
		return tempaction;
	}

	public void setTempaction(String tempaction) {
		this.tempaction = tempaction;
	}

	public ArrayList<Assessment> getClientAssesmentformList() {
		return clientAssesmentformList;
	}

	public void setClientAssesmentformList(
			ArrayList<Assessment> clientAssesmentformList) {
		this.clientAssesmentformList = clientAssesmentformList;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public ArrayList<Master> getCustomdataList() {
		return customdataList;
	}

	public void setCustomdataList(ArrayList<Master> customdataList) {
		this.customdataList = customdataList;
	}

	public String getLandLine() {
		return landLine;
	}

	public void setLandLine(String landLine) {
		this.landLine = landLine;
	}

	public String getOwner_qualification() {
		return owner_qualification;
	}

	public void setOwner_qualification(String owner_qualification) {
		this.owner_qualification = owner_qualification;
	}

	public ArrayList<Clinic> getLocationAdressList() {
		return locationAdressList;
	}

	public void setLocationAdressList(ArrayList<Clinic> locationAdressList) {
		this.locationAdressList = locationAdressList;
	}

	public String getClinicName() {
		return clinicName;
	}

	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}

	public String getClinicOwner() {
		return clinicOwner;
	}

	public void setClinicOwner(String clinicOwner) {
		this.clinicOwner = clinicOwner;
	}

	public String getClinicemail() {
		return clinicemail;
	}

	public void setClinicemail(String clinicemail) {
		this.clinicemail = clinicemail;
	}

	public String getClinicaddress() {
		return clinicaddress;
	}

	public void setClinicaddress(String clinicaddress) {
		this.clinicaddress = clinicaddress;
	}

	public String getClinicity() {
		return clinicity;
	}

	public void setClinicity(String clinicity) {
		this.clinicity = clinicity;
	}

	public String getWebsiteUrl() {
		return websiteUrl;
	}

	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}

	public String getClientFullName() {
		return clientFullName;
	}

	public void setClientFullName(String clientFullName) {
		this.clientFullName = clientFullName;
	}

	public ArrayList<Assessment> getImportImageList() {
		return importImageList;
	}

	public void setImportImageList(ArrayList<Assessment> importImageList) {
		this.importImageList = importImageList;
	}

	public String[] getImagename() {
		return imagename;
	}

	public void setImagename(String[] imagename) {
		this.imagename = imagename;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public File[] getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(File[] fileUpload) {
		this.fileUpload = fileUpload;
	}

	public String[] getFileUploadFileName() {
		return fileUploadFileName;
	}

	public void setFileUploadFileName(String[] fileUploadFileName) {
		this.fileUploadFileName = fileUploadFileName;
	}

	public String[] getFileUploadContentType() {
		return fileUploadContentType;
	}

	public void setFileUploadContentType(String[] fileUploadContentType) {
		this.fileUploadContentType = fileUploadContentType;
	}

	public ArrayList<String> getAssessmentFormsList() {
		return assessmentFormsList;
	}

	public void setAssessmentFormsList(ArrayList<String> assessmentFormsList) {
		this.assessmentFormsList = assessmentFormsList;
	}

	public int getColumnSize() {
		return columnSize;
	}

	public void setColumnSize(int columnSize) {
		this.columnSize = columnSize;
	}


	public String getTypeValue() {
		return typeValue;
	}

	public void setTypeValue(String typeValue) {
		this.typeValue = typeValue;
	}

	public String getSizeValue() {
		return sizeValue;
	}

	public void setSizeValue(String sizeValue) {
		this.sizeValue = sizeValue;
	}

	public String getSizeValueData() {
		return sizeValueData;
	}

	public void setSizeValueData(String sizeValueData) {
		this.sizeValueData = sizeValueData;
	}

	public String getTypeValueData() {
		return typeValueData;
	}

	public void setTypeValueData(String typeValueData) {
		this.typeValueData = typeValueData;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	

	public String getHiddenTemplateName() {
		return hiddenTemplateName;
	}

	public void setHiddenTemplateName(String hiddenTemplateName) {
		this.hiddenTemplateName = hiddenTemplateName;
	}

	public String getLableValueData() {
		return lableValueData;
	}

	public void setLableValueData(String lableValueData) {
		this.lableValueData = lableValueData;
	}

	public String getImageData() {
		return imageData;
	}

	public void setImageData(String imageData) {
		this.imageData = imageData;
	}

	public String getHeadingName() {
		return headingName;
	}

	public void setHeadingName(String headingName) {
		this.headingName = headingName;
	}


	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getLast_Name() {
		return Last_Name;
	}

	public void setLast_Name(String last_Name) {
		Last_Name = last_Name;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}
	
	private Collection<Assessment> assessment;
	
	private Collection<Assessment> subhead;
	
	public Collection<Assessment> getSubhead() {
		return subhead;
	}

	public void setSubhead(Collection<Assessment> subhead) {
		this.subhead = subhead;
	}

	private ArrayList<Assessment> templateNameList;
	
	private ArrayList<String> fieldTextList;
	
	private ArrayList<Assessment> fieldNameList;
	
	private ArrayList<Assessment> subHeadingList;
	
	private ArrayList<Assessment> headingFieldList;

	public ArrayList<Assessment> getHeadingFieldList() {
		return headingFieldList;
	}

	public void setHeadingFieldList(ArrayList<Assessment> headingFieldList) {
		this.headingFieldList = headingFieldList;
	}

	public ArrayList<Assessment> getSubHeadingList() {
		return subHeadingList;
	}

	public void setSubHeadingList(ArrayList<Assessment> subHeadingList) {
		this.subHeadingList = subHeadingList;
	}

	public ArrayList<Assessment> getFieldNameList() {
		return fieldNameList;
	}

	public void setFieldNameList(ArrayList<Assessment> fieldNameList) {
		this.fieldNameList = fieldNameList;
	}

	public ArrayList<String> getFieldTextList() {
		return fieldTextList;
	}

	public void setFieldTextList(ArrayList<String> fieldTextList) {
		this.fieldTextList = fieldTextList;
	}

	public ArrayList<Assessment> getTemplateNameList() {
		return templateNameList;
	}

	public void setTemplateNameList(ArrayList<Assessment> templateNameList) {
		this.templateNameList = templateNameList;
	}

	public Collection<Assessment> getAssessment() {
		return assessment;
	}
	public void setAssessment(Collection<Assessment> assessment) {
		this.assessment = assessment;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPagerecords() {
		return pagerecords;
	}
	public void setPagerecords(String pagerecords) {
		this.pagerecords = pagerecords;
	}
	public int getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
	public int getClinicId() {
		return clinicId;
	}
	public void setClinicId(int clinicId) {
		this.clinicId = clinicId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFiledname() {
		return filedname;
	}
	public void setFiledname(String filedname) {
		this.filedname = filedname;
	}

	
	//List
	ArrayList<Assessment> assessmentList;
	
	public ArrayList<Assessment> getAssessmentList() {
		return assessmentList;
	}
	public void setAssessmentList(ArrayList<Assessment> assessmentList) {
		this.assessmentList = assessmentList;
	}


	ArrayList<Assessment> fieldList;

	public ArrayList<Assessment> getFieldList() {
		return fieldList;
	}
	public void setFieldList(ArrayList<Assessment> fieldList) {
		this.fieldList = fieldList;
	}
	
	ArrayList<Assessment> templateFieldList;

	public ArrayList<Assessment> getTemplateFieldList() {
		return templateFieldList;
	}

	public void setTemplateFieldList(ArrayList<Assessment> templateFieldList) {
		this.templateFieldList = templateFieldList;
	}
	
	ArrayList<Assessment> availableList;
	
	ArrayList<Assessment> selectedList;

	public ArrayList<Assessment> getAvailableList() {
		return availableList;
	}

	public void setAvailableList(ArrayList<Assessment> availableList) {
		this.availableList = availableList;
	}

	public ArrayList<Assessment> getSelectedList() {
		return selectedList;
	}

	public void setSelectedList(ArrayList<Assessment> selectedList) {
		this.selectedList = selectedList;
	}

	ArrayList<DiaryManagement> userList;
	
	ArrayList<Client> clientList;
	
	ArrayList<Client> conditionList;
	
	private String diaryUser;
	
	private String condition;

	public String getDiaryUser() {
		return diaryUser;
	}

	public void setDiaryUser(String diaryUser) {
		this.diaryUser = diaryUser;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public ArrayList<Client> getClientList() {
		return clientList;
	}

	public void setClientList(ArrayList<Client> clientList) {
		this.clientList = clientList;
	}

	public ArrayList<Client> getConditionList() {
		return conditionList;
	}

	public void setConditionList(ArrayList<Client> conditionList) {
		this.conditionList = conditionList;
	}

	public ArrayList<DiaryManagement> getUserList() {
		return userList;
	}

	public void setUserList(ArrayList<DiaryManagement> userList) {
		this.userList = userList;
	}
	
	private String diaryUserId;
	private String conditionId;

	
	public String getDiaryUserId() {
		return diaryUserId;
	}

	public void setDiaryUserId(String diaryUserId) {
		this.diaryUserId = diaryUserId;
	}

	public String getConditionId() {
		return conditionId;
	}

	public void setConditionId(String conditionId) {
		this.conditionId = conditionId;
	}
	
	private String actionType;
	private String wardid;
	private String bedid;
	private String abrivationid;
	private String agegender;
	private String contact;
	private String address;
	private String fullname;
	private String doctorname;
	
	
	public String getWardid() {
		return wardid;
	}

	public void setWardid(String wardid) {
		this.wardid = wardid;
	}

	public String getBedid() {
		return bedid;
	}

	public void setBedid(String bedid) {
		this.bedid = bedid;
	}

	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getMrd_clientid() {
		return mrd_clientid;
	}

	public void setMrd_clientid(String mrd_clientid) {
		this.mrd_clientid = mrd_clientid;
	}
	

	public String getNum_admission() {
		return num_admission;
	}

	public void setNum_admission(String num_admission) {
		this.num_admission = num_admission;
	}

	public String getMrdid() {
		return mrdid;
	}

	public void setMrdid(String mrdid) {
		this.mrdid = mrdid;
	}

	public String getAbrivationid() {
		return abrivationid;
	}

	public void setAbrivationid(String abrivationid) {
		this.abrivationid = abrivationid;
	}

	public String getAgegender() {
		return agegender;
	}

	public void setAgegender(String agegender) {
		this.agegender = agegender;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getDoctorname() {
		return doctorname;
	}

	public void setDoctorname(String doctorname) {
		this.doctorname = doctorname;
	}

	public String getClinicLogo() {
		return clinicLogo;
	}

	public void setClinicLogo(String clinicLogo) {
		this.clinicLogo = clinicLogo;
	}
	
}
