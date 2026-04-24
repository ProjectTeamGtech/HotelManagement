package com.apm.AssesmentForms.web.form.Predefine;

import java.util.ArrayList;
import java.util.Collection;

import com.apm.AssesmentForms.eu.entity.Assessment;
import com.apm.AssesmentForms.eu.entity.Predefine.AssessmentTemplate;
import com.apm.AssesmentForms.eu.entity.Predefine.GeneralHealth;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.Registration.eu.entity.Location;

public class AssessmentTemplateForm {
	
	private int id;	
	
	//Client Details
	private String client;	
	private String clientId;	
	private String examDate;
	private String subjectiveHistory;
	private String outcomeMeasurement;
	private String primaryDiagnosis;
	private String secondaryDiagnosys;
	private String assessmentDate;
	private String treatmentDate;
	private String treatmentUsed;
	private String dischargeStatus;
	
	private String primaryDiagnosisDetails;
	private String secondaryDiagnosysDetails;
	
	//PainArea	
	private String detail;
	private String vas;	
	
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getVas() {
		return vas;
	}
	public void setVas(String vas) {
		this.vas = vas;
	}
	//General Health
	private String diabetesyn;
	private String diabetesDetail;
	private String epilepsyyn;
	private String epilepsyDetail;
	private String heartConditionyn;
	private String heartConditionDetail;
	private String lungDisorderyn;
	private String lungDisorderDetail;
	private String prostateyn;
	private String prostateDetail;
	private String carcinomayn;
	private String carcinomaDetail;
	private String rayn;
	private String raDetail;
	private String osteopaeniayn;
	private String osteopaeniaDetail;
	private String wtStableyn;
	private String wtStableDetail;
	private String bladderBowelyn;
	private String bladderBowelDetail;
	private String otheryn;
	private String otherDetail;
	
	private String medication;
	private String investigation;
	
	private String generalHealthData;
	private String nightPain;
	private String familyHistory;
	private String psychoSocial;
	private String surgery;
	private String accident;
	
	//Upper Limb
	private String bicepRight;
	private String bicepLeft;
	private String tricepRight;
	private String tricepLeft;
	public String getTricepLeft() {
		return tricepLeft;
	}
	public void setTricepLeft(String tricepLeft) {
		this.tricepLeft = tricepLeft;
	}
	private String brachioradialisRight;
	private String brachioradialisLeft;
	private String ulnt1Right;
	private String ulnt1Left;
	private String ulnt2Right;
	private String ulnt2Left;
	private String ulnt3Right;
	private String ulnt3Left;
	private String jrActiveMoment;
	private String jrPassiveMoment;
	private String myotomes;
	private String dermatomes;
	private String sensoryLossChanges;
	
	//Neural Test
	private String kneeRight;
	private String kneeLeft;
	private String ankleRight;
	private String ankleLeft;
	private String babinskiRight;
	private String babinskiLeft;
	
	//Neural Tenshn
	private String slumpRight;
	private String slumpLeft;
	private String dorsiflexionRom1;
	private String dorsiflexionPain1;
	private String dorsiflexionRom2;
	private String dorsiflexionPain2;
	private String medicalRotaionRom1;
	private String medicalRotaionPain1;
	private String medicalRotaionRom2;
	private String medicalRotaionPain2;
	private String neckFlexionRom1;
	private String neckFlexionPain1;
	private String neckFlexionRom2;
	private String neckFlexionPain2;
	private String passiveKneeBendRom1;
	private String passiveKneeBendPain1;
	private String passiveKneeBendRom2;
	private String passiveKneeBendPain2;
	
	//vad
	private String dizzyy;
	private String dizzyn;
	private String doubleVisiony;
	private String doubleVisionn;
	private String dysarthriay;
	private String dysarthrian;
	private String dysphagiay;
	private String dysphagian;
	private String dropAttacky;
	private String dropAttackn;
	private String nystagmusy;
	private String nystagmusn;
	private String nauseay;
	private String nausean;
	private String numbnessy;
	private String numbnessn;
	
	private String imageData = "";
	
	public String getImageData() {
		return imageData;
	}
	public void setImageData(String imageData) {
		this.imageData = imageData;
	}
	
	private ArrayList<Assessment> importImageList;
	
	private ArrayList<AssessmentTemplate> physioTemplateList;
	
	
	public ArrayList<AssessmentTemplate> getPhysioTemplateList() {
		return physioTemplateList;
	}
	public void setPhysioTemplateList(
			ArrayList<AssessmentTemplate> physioTemplateList) {
		this.physioTemplateList = physioTemplateList;
	}
	public ArrayList<Assessment> getImportImageList() {
		return importImageList;
	}
	public void setImportImageList(ArrayList<Assessment> importImageList) {
		this.importImageList = importImageList;
	}
	//private Collection<GeneralHealth> generalHealth;
	
	private Collection<GeneralHealth> painArea;
	
	/*public Collection<GeneralHealth> getGeneralHealth() {
		return generalHealth;
	}
	public void setGeneralHealth(Collection<GeneralHealth> generalHealth) {
		this.generalHealth = generalHealth;
	}*/
	public Collection<GeneralHealth> getPainArea() {
		return painArea;
	}
	public void setPainArea(Collection<GeneralHealth> painArea) {
		this.painArea = painArea;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getExamDate() {
		return examDate;
	}
	public void setExamDate(String examDate) {
		this.examDate = examDate;
	}
	public String getSubjectiveHistory() {
		return subjectiveHistory;
	}
	public void setSubjectiveHistory(String subjectiveHistory) {
		this.subjectiveHistory = subjectiveHistory;
	}
	public String getOutcomeMeasurement() {
		return outcomeMeasurement;
	}
	public void setOutcomeMeasurement(String outcomeMeasurement) {
		this.outcomeMeasurement = outcomeMeasurement;
	}
	public String getPrimaryDiagnosis() {
		return primaryDiagnosis;
	}
	public void setPrimaryDiagnosis(String primaryDiagnosis) {
		this.primaryDiagnosis = primaryDiagnosis;
	}
	public String getSecondaryDiagnosys() {
		return secondaryDiagnosys;
	}
	public void setSecondaryDiagnosys(String secondaryDiagnosys) {
		this.secondaryDiagnosys = secondaryDiagnosys;
	}
	public String getAssessmentDate() {
		return assessmentDate;
	}
	public void setAssessmentDate(String assessmentDate) {
		this.assessmentDate = assessmentDate;
	}
	public String getTreatmentDate() {
		return treatmentDate;
	}
	public void setTreatmentDate(String treatmentDate) {
		this.treatmentDate = treatmentDate;
	}
	public String getTreatmentUsed() {
		return treatmentUsed;
	}
	public void setTreatmentUsed(String treatmentUsed) {
		this.treatmentUsed = treatmentUsed;
	}
	public String getDischargeStatus() {
		return dischargeStatus;
	}
	public void setDischargeStatus(String dischargeStatus) {
		this.dischargeStatus = dischargeStatus;
	}
	public String getPrimaryDiagnosisDetails() {
		return primaryDiagnosisDetails;
	}
	public void setPrimaryDiagnosisDetails(String primaryDiagnosisDetails) {
		this.primaryDiagnosisDetails = primaryDiagnosisDetails;
	}
	public String getSecondaryDiagnosysDetails() {
		return secondaryDiagnosysDetails;
	}
	public void setSecondaryDiagnosysDetails(String secondaryDiagnosysDetails) {
		this.secondaryDiagnosysDetails = secondaryDiagnosysDetails;
	}
	public String getDiabetesyn() {
		return diabetesyn;
	}
	public void setDiabetesyn(String diabetesyn) {
		this.diabetesyn = diabetesyn;
	}
	public String getDiabetesDetail() {
		return diabetesDetail;
	}
	public void setDiabetesDetail(String diabetesDetail) {
		this.diabetesDetail = diabetesDetail;
	}
	public String getEpilepsyyn() {
		return epilepsyyn;
	}
	public void setEpilepsyyn(String epilepsyyn) {
		this.epilepsyyn = epilepsyyn;
	}
	public String getEpilepsyDetail() {
		return epilepsyDetail;
	}
	public void setEpilepsyDetail(String epilepsyDetail) {
		this.epilepsyDetail = epilepsyDetail;
	}
	public String getHeartConditionyn() {
		return heartConditionyn;
	}
	public void setHeartConditionyn(String heartConditionyn) {
		this.heartConditionyn = heartConditionyn;
	}
	public String getHeartConditionDetail() {
		return heartConditionDetail;
	}
	public void setHeartConditionDetail(String heartConditionDetail) {
		this.heartConditionDetail = heartConditionDetail;
	}
	public String getLungDisorderyn() {
		return lungDisorderyn;
	}
	public void setLungDisorderyn(String lungDisorderyn) {
		this.lungDisorderyn = lungDisorderyn;
	}
	public String getLungDisorderDetail() {
		return lungDisorderDetail;
	}
	public void setLungDisorderDetail(String lungDisorderDetail) {
		this.lungDisorderDetail = lungDisorderDetail;
	}
	public String getProstateyn() {
		return prostateyn;
	}
	public void setProstateyn(String prostateyn) {
		this.prostateyn = prostateyn;
	}
	public String getProstateDetail() {
		return prostateDetail;
	}
	public void setProstateDetail(String prostateDetail) {
		this.prostateDetail = prostateDetail;
	}
	public String getCarcinomayn() {
		return carcinomayn;
	}
	public void setCarcinomayn(String carcinomayn) {
		this.carcinomayn = carcinomayn;
	}
	public String getCarcinomaDetail() {
		return carcinomaDetail;
	}
	public void setCarcinomaDetail(String carcinomaDetail) {
		this.carcinomaDetail = carcinomaDetail;
	}
	public String getRayn() {
		return rayn;
	}
	public void setRayn(String rayn) {
		this.rayn = rayn;
	}
	public String getRaDetail() {
		return raDetail;
	}
	public void setRaDetail(String raDetail) {
		this.raDetail = raDetail;
	}
	public String getOsteopaeniayn() {
		return osteopaeniayn;
	}
	public void setOsteopaeniayn(String osteopaeniayn) {
		this.osteopaeniayn = osteopaeniayn;
	}
	public String getOsteopaeniaDetail() {
		return osteopaeniaDetail;
	}
	public void setOsteopaeniaDetail(String osteopaeniaDetail) {
		this.osteopaeniaDetail = osteopaeniaDetail;
	}
	public String getWtStableyn() {
		return wtStableyn;
	}
	public void setWtStableyn(String wtStableyn) {
		this.wtStableyn = wtStableyn;
	}
	public String getWtStableDetail() {
		return wtStableDetail;
	}
	public void setWtStableDetail(String wtStableDetail) {
		this.wtStableDetail = wtStableDetail;
	}
	public String getBladderBowelyn() {
		return bladderBowelyn;
	}
	public void setBladderBowelyn(String bladderBowelyn) {
		this.bladderBowelyn = bladderBowelyn;
	}
	public String getBladderBowelDetail() {
		return bladderBowelDetail;
	}
	public void setBladderBowelDetail(String bladderBowelDetail) {
		this.bladderBowelDetail = bladderBowelDetail;
	}
	public String getOtheryn() {
		return otheryn;
	}
	public void setOtheryn(String otheryn) {
		this.otheryn = otheryn;
	}
	public String getOtherDetail() {
		return otherDetail;
	}
	public void setOtherDetail(String otherDetail) {
		this.otherDetail = otherDetail;
	}
	public String getMedication() {
		return medication;
	}
	public void setMedication(String medication) {
		this.medication = medication;
	}
	public String getInvestigation() {
		return investigation;
	}
	public void setInvestigation(String investigation) {
		this.investigation = investigation;
	}
	
	public String getGeneralHealthData() {
		return generalHealthData;
	}
	public void setGeneralHealthData(String generalHealthData) {
		this.generalHealthData = generalHealthData;
	}
	public String getNightPain() {
		return nightPain;
	}
	public void setNightPain(String nightPain) {
		this.nightPain = nightPain;
	}
	public String getFamilyHistory() {
		return familyHistory;
	}
	public void setFamilyHistory(String familyHistory) {
		this.familyHistory = familyHistory;
	}
	public String getPsychoSocial() {
		return psychoSocial;
	}
	public void setPsychoSocial(String psychoSocial) {
		this.psychoSocial = psychoSocial;
	}
	public String getSurgery() {
		return surgery;
	}
	public void setSurgery(String surgery) {
		this.surgery = surgery;
	}
	public String getAccident() {
		return accident;
	}
	public void setAccident(String accident) {
		this.accident = accident;
	}
	public String getBicepRight() {
		return bicepRight;
	}
	public void setBicepRight(String bicepRight) {
		this.bicepRight = bicepRight;
	}
	public String getBicepLeft() {
		return bicepLeft;
	}
	public void setBicepLeft(String bicepLeft) {
		this.bicepLeft = bicepLeft;
	}
	public String getTricepRight() {
		return tricepRight;
	}
	public void setTricepRight(String tricepRight) {
		this.tricepRight = tricepRight;
	}
	public String getBrachioradialisRight() {
		return brachioradialisRight;
	}
	public void setBrachioradialisRight(String brachioradialisRight) {
		this.brachioradialisRight = brachioradialisRight;
	}
	public String getBrachioradialisLeft() {
		return brachioradialisLeft;
	}
	public void setBrachioradialisLeft(String brachioradialisLeft) {
		this.brachioradialisLeft = brachioradialisLeft;
	}
	public String getUlnt1Right() {
		return ulnt1Right;
	}
	public void setUlnt1Right(String ulnt1Right) {
		this.ulnt1Right = ulnt1Right;
	}
	public String getUlnt1Left() {
		return ulnt1Left;
	}
	public void setUlnt1Left(String ulnt1Left) {
		this.ulnt1Left = ulnt1Left;
	}
	public String getUlnt2Right() {
		return ulnt2Right;
	}
	public void setUlnt2Right(String ulnt2Right) {
		this.ulnt2Right = ulnt2Right;
	}
	public String getUlnt2Left() {
		return ulnt2Left;
	}
	public void setUlnt2Left(String ulnt2Left) {
		this.ulnt2Left = ulnt2Left;
	}
	public String getUlnt3Right() {
		return ulnt3Right;
	}
	public void setUlnt3Right(String ulnt3Right) {
		this.ulnt3Right = ulnt3Right;
	}
	public String getUlnt3Left() {
		return ulnt3Left;
	}
	public void setUlnt3Left(String ulnt3Left) {
		this.ulnt3Left = ulnt3Left;
	}
	public String getJrActiveMoment() {
		return jrActiveMoment;
	}
	public void setJrActiveMoment(String jrActiveMoment) {
		this.jrActiveMoment = jrActiveMoment;
	}
	public String getJrPassiveMoment() {
		return jrPassiveMoment;
	}
	public void setJrPassiveMoment(String jrPassiveMoment) {
		this.jrPassiveMoment = jrPassiveMoment;
	}
	public String getMyotomes() {
		return myotomes;
	}
	public void setMyotomes(String myotomes) {
		this.myotomes = myotomes;
	}
	public String getDermatomes() {
		return dermatomes;
	}
	public void setDermatomes(String dermatomes) {
		this.dermatomes = dermatomes;
	}
	public String getSensoryLossChanges() {
		return sensoryLossChanges;
	}
	public void setSensoryLossChanges(String sensoryLossChanges) {
		this.sensoryLossChanges = sensoryLossChanges;
	}
	public String getKneeRight() {
		return kneeRight;
	}
	public void setKneeRight(String kneeRight) {
		this.kneeRight = kneeRight;
	}
	public String getKneeLeft() {
		return kneeLeft;
	}
	public void setKneeLeft(String kneeLeft) {
		this.kneeLeft = kneeLeft;
	}
	public String getAnkleRight() {
		return ankleRight;
	}
	public void setAnkleRight(String ankleRight) {
		this.ankleRight = ankleRight;
	}
	public String getAnkleLeft() {
		return ankleLeft;
	}
	public void setAnkleLeft(String ankleLeft) {
		this.ankleLeft = ankleLeft;
	}
	public String getBabinskiRight() {
		return babinskiRight;
	}
	public void setBabinskiRight(String babinskiRight) {
		this.babinskiRight = babinskiRight;
	}
	public String getBabinskiLeft() {
		return babinskiLeft;
	}
	public void setBabinskiLeft(String babinskiLeft) {
		this.babinskiLeft = babinskiLeft;
	}
	public String getSlumpRight() {
		return slumpRight;
	}
	public void setSlumpRight(String slumpRight) {
		this.slumpRight = slumpRight;
	}
	public String getSlumpLeft() {
		return slumpLeft;
	}
	public void setSlumpLeft(String slumpLeft) {
		this.slumpLeft = slumpLeft;
	}
	public String getDorsiflexionRom1() {
		return dorsiflexionRom1;
	}
	public void setDorsiflexionRom1(String dorsiflexionRom1) {
		this.dorsiflexionRom1 = dorsiflexionRom1;
	}
	public String getDorsiflexionPain1() {
		return dorsiflexionPain1;
	}
	public void setDorsiflexionPain1(String dorsiflexionPain1) {
		this.dorsiflexionPain1 = dorsiflexionPain1;
	}
	public String getDorsiflexionRom2() {
		return dorsiflexionRom2;
	}
	public void setDorsiflexionRom2(String dorsiflexionRom2) {
		this.dorsiflexionRom2 = dorsiflexionRom2;
	}
	public String getDorsiflexionPain2() {
		return dorsiflexionPain2;
	}
	public void setDorsiflexionPain2(String dorsiflexionPain2) {
		this.dorsiflexionPain2 = dorsiflexionPain2;
	}
	public String getMedicalRotaionRom1() {
		return medicalRotaionRom1;
	}
	public void setMedicalRotaionRom1(String medicalRotaionRom1) {
		this.medicalRotaionRom1 = medicalRotaionRom1;
	}
	public String getMedicalRotaionPain1() {
		return medicalRotaionPain1;
	}
	public void setMedicalRotaionPain1(String medicalRotaionPain1) {
		this.medicalRotaionPain1 = medicalRotaionPain1;
	}
	public String getMedicalRotaionRom2() {
		return medicalRotaionRom2;
	}
	public void setMedicalRotaionRom2(String medicalRotaionRom2) {
		this.medicalRotaionRom2 = medicalRotaionRom2;
	}
	public String getMedicalRotaionPain2() {
		return medicalRotaionPain2;
	}
	public void setMedicalRotaionPain2(String medicalRotaionPain2) {
		this.medicalRotaionPain2 = medicalRotaionPain2;
	}
	public String getNeckFlexionRom1() {
		return neckFlexionRom1;
	}
	public void setNeckFlexionRom1(String neckFlexionRom1) {
		this.neckFlexionRom1 = neckFlexionRom1;
	}
	public String getNeckFlexionPain1() {
		return neckFlexionPain1;
	}
	public void setNeckFlexionPain1(String neckFlexionPain1) {
		this.neckFlexionPain1 = neckFlexionPain1;
	}
	public String getNeckFlexionRom2() {
		return neckFlexionRom2;
	}
	public void setNeckFlexionRom2(String neckFlexionRom2) {
		this.neckFlexionRom2 = neckFlexionRom2;
	}
	public String getNeckFlexionPain2() {
		return neckFlexionPain2;
	}
	public void setNeckFlexionPain2(String neckFlexionPain2) {
		this.neckFlexionPain2 = neckFlexionPain2;
	}
	public String getPassiveKneeBendRom1() {
		return passiveKneeBendRom1;
	}
	public void setPassiveKneeBendRom1(String passiveKneeBendRom1) {
		this.passiveKneeBendRom1 = passiveKneeBendRom1;
	}
	public String getPassiveKneeBendPain1() {
		return passiveKneeBendPain1;
	}
	public void setPassiveKneeBendPain1(String passiveKneeBendPain1) {
		this.passiveKneeBendPain1 = passiveKneeBendPain1;
	}
	public String getPassiveKneeBendRom2() {
		return passiveKneeBendRom2;
	}
	public void setPassiveKneeBendRom2(String passiveKneeBendRom2) {
		this.passiveKneeBendRom2 = passiveKneeBendRom2;
	}
	public String getPassiveKneeBendPain2() {
		return passiveKneeBendPain2;
	}
	public void setPassiveKneeBendPain2(String passiveKneeBendPain2) {
		this.passiveKneeBendPain2 = passiveKneeBendPain2;
	}
	public String getDizzyy() {
		return dizzyy;
	}
	public void setDizzyy(String dizzyy) {
		this.dizzyy = dizzyy;
	}
	public String getDizzyn() {
		return dizzyn;
	}
	public void setDizzyn(String dizzyn) {
		this.dizzyn = dizzyn;
	}
	public String getDoubleVisiony() {
		return doubleVisiony;
	}
	public void setDoubleVisiony(String doubleVisiony) {
		this.doubleVisiony = doubleVisiony;
	}
	public String getDoubleVisionn() {
		return doubleVisionn;
	}
	public void setDoubleVisionn(String doubleVisionn) {
		this.doubleVisionn = doubleVisionn;
	}
	public String getDysarthriay() {
		return dysarthriay;
	}
	public void setDysarthriay(String dysarthriay) {
		this.dysarthriay = dysarthriay;
	}
	public String getDysarthrian() {
		return dysarthrian;
	}
	public void setDysarthrian(String dysarthrian) {
		this.dysarthrian = dysarthrian;
	}
	public String getDysphagiay() {
		return dysphagiay;
	}
	public void setDysphagiay(String dysphagiay) {
		this.dysphagiay = dysphagiay;
	}
	public String getDysphagian() {
		return dysphagian;
	}
	public void setDysphagian(String dysphagian) {
		this.dysphagian = dysphagian;
	}
	public String getDropAttacky() {
		return dropAttacky;
	}
	public void setDropAttacky(String dropAttacky) {
		this.dropAttacky = dropAttacky;
	}
	public String getDropAttackn() {
		return dropAttackn;
	}
	public void setDropAttackn(String dropAttackn) {
		this.dropAttackn = dropAttackn;
	}
	public String getNystagmusy() {
		return nystagmusy;
	}
	public void setNystagmusy(String nystagmusy) {
		this.nystagmusy = nystagmusy;
	}
	public String getNystagmusn() {
		return nystagmusn;
	}
	public void setNystagmusn(String nystagmusn) {
		this.nystagmusn = nystagmusn;
	}
	public String getNauseay() {
		return nauseay;
	}
	public void setNauseay(String nauseay) {
		this.nauseay = nauseay;
	}
	public String getNausean() {
		return nausean;
	}
	public void setNausean(String nausean) {
		this.nausean = nausean;
	}
	public String getNumbnessy() {
		return numbnessy;
	}
	public void setNumbnessy(String numbnessy) {
		this.numbnessy = numbnessy;
	}
	public String getNumbnessn() {
		return numbnessn;
	}
	public void setNumbnessn(String numbnessn) {
		this.numbnessn = numbnessn;
	}
	
	ArrayList<DiaryManagement> userList;
	
	ArrayList<Client> clientList;
	
	ArrayList<Client> conditionList;
	
	private String diaryUser;
	
	private String condition;
	
	private String generalHealth;

	public String getGeneralHealth() {
		return generalHealth;
	}
	public void setGeneralHealth(String generalHealth) {
		this.generalHealth = generalHealth;
	}
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

	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
}
