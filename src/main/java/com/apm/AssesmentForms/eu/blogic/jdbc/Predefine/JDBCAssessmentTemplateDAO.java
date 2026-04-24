package com.apm.AssesmentForms.eu.blogic.jdbc.Predefine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.apm.AssesmentForms.eu.bi.Predefine.AssessmentTemplateDAO;
import com.apm.AssesmentForms.eu.entity.Assessment;
import com.apm.AssesmentForms.eu.entity.Predefine.AssessmentTemplate;
import com.apm.common.eu.blogic.jdbc.JDBCBaseDAO;

public class JDBCAssessmentTemplateDAO extends JDBCBaseDAO implements AssessmentTemplateDAO{
	
	public JDBCAssessmentTemplateDAO(Connection connection){
		this.connection = connection;
		
	}

	public int saveTemplateDetails(AssessmentTemplate assessmentTemplate) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		int physioId = 0;
		String sql = "insert into apm_assessment_physio_template (clientId,clientName,examDate,subjectiveHistory,outcomeMeasurement,"
				+ "primaryDiagnosis,secondaryDiagnosys,assessmentDate,treatmentDate,treatmentUsed,dischargeStatus,primaryDiagnosisDetails,"
				+ "secondaryDiagnosysDetails,medication,investigation,generalHealth,nightPain,familyHistory,psychoSocial,surgery,accident,"
				+ "bicepRight,bicepLeft,tricepRight,tricepLeft,brachioradialisRight,brachioradialisLeft,ulnt1Right,ulnt1Left,ulnt2Right,"
				+ "ulnt2Left,ulnt3Right,ulnt3Left,jrActiveMoment,jrPassiveMoment,myotomes,dermatomes,sensoryLossChanges,kneeRight,kneeLeft,"
				+ "ankleRight,ankleLeft,babinskiRight,babinskiLeft,slumpRight,slumpLeft,dorsiflexionRom1,dorsiflexionPain1,dorsiflexionRom2,"
				+ "dorsiflexionPain2,medicalRotaionRom1,medicalRotaionPain1,medicalRotaionRom2,medicalRotaionPain2,neckFlexionRom1,neckFlexionPain1,"
				+ "neckFlexionRom2,neckFlexionPain2,passiveKneeBendRom1,passiveKneeBendPain1,passiveKneeBendRom2,passiveKneeBendPain2,dizzyy,dizzyn,"
				+ "doubleVisiony,doubleVisionn,dysarthriay,dysarthrian,dysphagiay,dysphagian,dropAttacky,dropAttackn,nystagmusy,nystagmusn,nauseay,"
				+ "nausean,numbnessy,numbnessn,imageData,practitionerId,conditionId) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
				+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, assessmentTemplate.getClientId());
			preparedStatement.setString(2, assessmentTemplate.getClient());
			preparedStatement.setString(3, assessmentTemplate.getExamDate());
			preparedStatement.setString(4, assessmentTemplate.getSubjectiveHistory());
			preparedStatement.setString(5, assessmentTemplate.getOutcomeMeasurement());
			preparedStatement.setString(6, assessmentTemplate.getPrimaryDiagnosis());
			preparedStatement.setString(7, assessmentTemplate.getSecondaryDiagnosys());
			preparedStatement.setString(8, assessmentTemplate.getAssessmentDate());
			preparedStatement.setString(9, assessmentTemplate.getTreatmentDate());
			preparedStatement.setString(10, assessmentTemplate.getTreatmentUsed());
			preparedStatement.setString(11, assessmentTemplate.getDischargeStatus());
			preparedStatement.setString(12, assessmentTemplate.getPrimaryDiagnosisDetails());
			preparedStatement.setString(13, assessmentTemplate.getSecondaryDiagnosysDetails());
			preparedStatement.setString(14, assessmentTemplate.getMedication());
			preparedStatement.setString(15, assessmentTemplate.getInvestigation());
			preparedStatement.setString(16, assessmentTemplate.getGeneralHealth());
			preparedStatement.setString(17, assessmentTemplate.getNightPain());
			preparedStatement.setString(18, assessmentTemplate.getFamilyHistory());
			preparedStatement.setString(19, assessmentTemplate.getPsychoSocial());
			preparedStatement.setString(20, assessmentTemplate.getSurgery());
			preparedStatement.setString(21, assessmentTemplate.getAccident());
			preparedStatement.setString(22, assessmentTemplate.getBicepRight());
			preparedStatement.setString(23, assessmentTemplate.getBicepLeft());
			preparedStatement.setString(24, assessmentTemplate.getTricepRight());
			preparedStatement.setString(25, assessmentTemplate.getTricepLeft());
			preparedStatement.setString(26, assessmentTemplate.getBrachioradialisRight());
			preparedStatement.setString(27, assessmentTemplate.getBrachioradialisLeft());
			preparedStatement.setString(28, assessmentTemplate.getUlnt1Right());
			preparedStatement.setString(29, assessmentTemplate.getUlnt1Left());
			preparedStatement.setString(30, assessmentTemplate.getUlnt2Right());
			preparedStatement.setString(31, assessmentTemplate.getUlnt2Left());
			preparedStatement.setString(32, assessmentTemplate.getUlnt3Right());
			preparedStatement.setString(33, assessmentTemplate.getUlnt3Left());
			preparedStatement.setString(34, assessmentTemplate.getJrActiveMoment());
			preparedStatement.setString(35, assessmentTemplate.getJrPassiveMoment());
			preparedStatement.setString(36, assessmentTemplate.getMyotomes());
			preparedStatement.setString(37, assessmentTemplate.getDermatomes());
			preparedStatement.setString(38, assessmentTemplate.getSensoryLossChanges());
			preparedStatement.setString(39, assessmentTemplate.getKneeRight());
			preparedStatement.setString(40, assessmentTemplate.getKneeLeft());
			preparedStatement.setString(41, assessmentTemplate.getAnkleRight());
			preparedStatement.setString(42, assessmentTemplate.getAnkleLeft());
			preparedStatement.setString(43, assessmentTemplate.getBabinskiRight());
			preparedStatement.setString(44, assessmentTemplate.getBabinskiLeft());
			preparedStatement.setString(45, assessmentTemplate.getSlumpRight());
			preparedStatement.setString(46, assessmentTemplate.getSlumpLeft());
			preparedStatement.setString(47, assessmentTemplate.getDorsiflexionRom1());
			preparedStatement.setString(48, assessmentTemplate.getDorsiflexionPain1());
			preparedStatement.setString(49, assessmentTemplate.getDorsiflexionRom2());
			preparedStatement.setString(50, assessmentTemplate.getDorsiflexionPain2());
			preparedStatement.setString(51, assessmentTemplate.getMedicalRotaionRom1());
			preparedStatement.setString(52, assessmentTemplate.getMedicalRotaionPain1());
			preparedStatement.setString(53, assessmentTemplate.getMedicalRotaionRom2());
			preparedStatement.setString(54, assessmentTemplate.getMedicalRotaionPain2());
			preparedStatement.setString(55, assessmentTemplate.getNeckFlexionRom1());
			preparedStatement.setString(56, assessmentTemplate.getNeckFlexionPain1());
			preparedStatement.setString(57, assessmentTemplate.getNeckFlexionRom2());
			preparedStatement.setString(58, assessmentTemplate.getNeckFlexionPain2());
			preparedStatement.setString(59, assessmentTemplate.getPassiveKneeBendRom1());
			preparedStatement.setString(60, assessmentTemplate.getPassiveKneeBendPain1());
			preparedStatement.setString(61, assessmentTemplate.getPassiveKneeBendRom2());
			preparedStatement.setString(62, assessmentTemplate.getPassiveKneeBendPain2());
			preparedStatement.setString(63, assessmentTemplate.getDizzyy());
			preparedStatement.setString(64, assessmentTemplate.getDizzyn());
			preparedStatement.setString(65, assessmentTemplate.getDoubleVisiony());
			preparedStatement.setString(66, assessmentTemplate.getDoubleVisionn());
			preparedStatement.setString(67, assessmentTemplate.getDysarthriay());
			preparedStatement.setString(68, assessmentTemplate.getDysarthrian());
			preparedStatement.setString(69, assessmentTemplate.getDysphagiay());
			preparedStatement.setString(70, assessmentTemplate.getDysphagian());
			preparedStatement.setString(71, assessmentTemplate.getDropAttacky());
			preparedStatement.setString(72, assessmentTemplate.getDropAttackn());
			preparedStatement.setString(73, assessmentTemplate.getNystagmusy());
			preparedStatement.setString(74, assessmentTemplate.getNystagmusn());
			preparedStatement.setString(75, assessmentTemplate.getNauseay());
			preparedStatement.setString(76, assessmentTemplate.getNausean());
			preparedStatement.setString(77, assessmentTemplate.getNumbnessy());
			preparedStatement.setString(78, assessmentTemplate.getNumbnessn());
			preparedStatement.setString(79, assessmentTemplate.getImageData());
			preparedStatement.setString(80, assessmentTemplate.getDiaryUserId());
			preparedStatement.setString(81, assessmentTemplate.getConditionId());
			
			result = preparedStatement.executeUpdate();
			
			if(result == 1){
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if(resultSet.next()){
					physioId = resultSet.getInt(1);  
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return physioId;
	}

	public int saveGeneralHealthDetails(int physioTemplateId,AssessmentTemplate assessmentTemplate) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_assessment_physio_general_health (diabetesyn,diabetesDetail,epilepsyyn,epilepsyDetail,"
				+ "heartConditionyn,heartConditionDetail,lungDisorderyn,lungDisorderDetail,prostateyn,prostateDetail,"
				+ "carcinomayn,carcinomaDetail,rayn,raDetail,osteopaeniayn,osteopaeniaDetail,wtStableyn,wtStableDetail,"
				+ "bladderBowelyn,bladderBowelDetail,otheryn,otherDetail,physioId) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, assessmentTemplate.getDiabetesyn());
			preparedStatement.setString(2, assessmentTemplate.getDiabetesDetail());
			preparedStatement.setString(3, assessmentTemplate.getEpilepsyyn());
			preparedStatement.setString(4, assessmentTemplate.getEpilepsyDetail());
			preparedStatement.setString(5, assessmentTemplate.getHeartConditionyn());
			preparedStatement.setString(6, assessmentTemplate.getHeartConditionDetail());
			preparedStatement.setString(7, assessmentTemplate.getLungDisorderyn());
			preparedStatement.setString(8, assessmentTemplate.getLungDisorderDetail());
			preparedStatement.setString(9, assessmentTemplate.getProstateyn());
			preparedStatement.setString(10, assessmentTemplate.getProstateDetail());
			preparedStatement.setString(11, assessmentTemplate.getCarcinomayn());
			preparedStatement.setString(12, assessmentTemplate.getCarcinomaDetail());
			preparedStatement.setString(13, assessmentTemplate.getRayn());
			preparedStatement.setString(14, assessmentTemplate.getRaDetail());
			preparedStatement.setString(15, assessmentTemplate.getOsteopaeniayn());
			preparedStatement.setString(16, assessmentTemplate.getOsteopaeniaDetail());
			preparedStatement.setString(17, assessmentTemplate.getWtStableyn());
			preparedStatement.setString(18, assessmentTemplate.getWtStableDetail());
			preparedStatement.setString(19, assessmentTemplate.getBladderBowelyn());
			preparedStatement.setString(20, assessmentTemplate.getBladderBowelDetail());
			preparedStatement.setString(21, assessmentTemplate.getOtheryn());
			preparedStatement.setString(22, assessmentTemplate.getOtherDetail());
			preparedStatement.setString(23, Integer.toString(physioTemplateId));
			
			result = preparedStatement.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public int savePainAreaDetails(int physioTemplateId,AssessmentTemplate assessmentTemplate) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_assessment_pain_area(physioId,detail,vas) values(?,?,?)";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, Integer.toString(physioTemplateId));
			preparedStatement.setString(2, assessmentTemplate.getDetail());
			preparedStatement.setString(3, assessmentTemplate.getVas());
			
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	
	public ArrayList<AssessmentTemplate> getPhysioTemplateList(String clientId,String practId,String conditionId) {
		PreparedStatement preparedStatement = null;
		ArrayList<AssessmentTemplate> list = new ArrayList<AssessmentTemplate>();
		String sql = "select id,clientId,clientName,examDate,subjectiveHistory,outcomeMeasurement,primaryDiagnosis,secondaryDiagnosys,"
				+ "assessmentDate,treatmentDate,treatmentUsed,dischargeStatus from apm_assessment_physio_template where "
				+ "clientId = "+clientId+" and practitionerId = "+practId+" and conditionId = "+conditionId+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				AssessmentTemplate assessmentTemplate = new AssessmentTemplate();
				assessmentTemplate.setId(rs.getInt(1));
				assessmentTemplate.setClientId(rs.getString(2));
				assessmentTemplate.setClient(rs.getString(3));
				assessmentTemplate.setExamDate(rs.getString(4));
				assessmentTemplate.setSubjectiveHistory(rs.getString(5));
				assessmentTemplate.setOutcomeMeasurement(rs.getString(6));
				assessmentTemplate.setPrimaryDiagnosis(rs.getString(7));
				assessmentTemplate.setSecondaryDiagnosys(rs.getString(8));
				assessmentTemplate.setAssessmentDate(rs.getString(9));
				assessmentTemplate.setTreatmentDate(rs.getString(10));
				assessmentTemplate.setTreatmentUsed(rs.getString(11));
				assessmentTemplate.setDischargeStatus(rs.getString(12));
				
				list.add(assessmentTemplate);
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	
	public AssessmentTemplate getPhysioTemplateDetails(int selectedId) {
		PreparedStatement preparedStatement = null;
		AssessmentTemplate assessmentTemplate = new AssessmentTemplate();
		String sql = "select id,clientId,clientName,examDate,subjectiveHistory,outcomeMeasurement,"
				+ "primaryDiagnosis,secondaryDiagnosys,assessmentDate,treatmentDate,treatmentUsed,dischargeStatus,primaryDiagnosisDetails,"
				+ "secondaryDiagnosysDetails,medication,investigation,generalHealth,nightPain,familyHistory,psychoSocial,surgery,accident,"
				+ "bicepRight,bicepLeft,tricepRight,tricepLeft,brachioradialisRight,brachioradialisLeft,ulnt1Right,ulnt1Left,ulnt2Right,"
				+ "ulnt2Left,ulnt3Right,ulnt3Left,jrActiveMoment,jrPassiveMoment,myotomes,dermatomes,sensoryLossChanges,kneeRight,kneeLeft,"
				+ "ankleRight,ankleLeft,babinskiRight,babinskiLeft,slumpRight,slumpLeft,dorsiflexionRom1,dorsiflexionPain1,dorsiflexionRom2,"
				+ "dorsiflexionPain2,medicalRotaionRom1,medicalRotaionPain1,medicalRotaionRom2,medicalRotaionPain2,neckFlexionRom1,neckFlexionPain1,"
				+ "neckFlexionRom2,neckFlexionPain2,passiveKneeBendRom1,passiveKneeBendPain1,passiveKneeBendRom2,passiveKneeBendPain2,dizzyy,dizzyn,"
				+ "doubleVisiony,doubleVisionn,dysarthriay,dysarthrian,dysphagiay,dysphagian,dropAttacky,dropAttackn,nystagmusy,nystagmusn,nauseay,"
				+ "nausean,numbnessy,numbnessn,imageData,practitionerId,conditionId from apm_assessment_physio_template where id = "+selectedId+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				assessmentTemplate.setId(rs.getInt(1));
				assessmentTemplate.setClientId(rs.getString(2));
				assessmentTemplate.setClient(rs.getString(3));
				assessmentTemplate.setExamDate(rs.getString(4));
				assessmentTemplate.setSubjectiveHistory(rs.getString(5));
				assessmentTemplate.setOutcomeMeasurement(rs.getString(6));
				assessmentTemplate.setPrimaryDiagnosis(rs.getString(7));
				assessmentTemplate.setSecondaryDiagnosys(rs.getString(8));
				assessmentTemplate.setAssessmentDate(rs.getString(9));
				assessmentTemplate.setTreatmentDate(rs.getString(10));
				assessmentTemplate.setTreatmentUsed(rs.getString(11));
				assessmentTemplate.setDischargeStatus(rs.getString(12));
				assessmentTemplate.setPrimaryDiagnosisDetails(rs.getString(13));
				assessmentTemplate.setSecondaryDiagnosysDetails(rs.getString(14));
				assessmentTemplate.setMedication(rs.getString(15));
				assessmentTemplate.setInvestigation(rs.getString(16));
				assessmentTemplate.setGeneralHealth(rs.getString(17));
				assessmentTemplate.setNightPain(rs.getString(18));
				assessmentTemplate.setFamilyHistory(rs.getString(19));
				assessmentTemplate.setPsychoSocial(rs.getString(20));
				assessmentTemplate.setSurgery(rs.getString(21));
				assessmentTemplate.setAccident(rs.getString(22));
				assessmentTemplate.setBicepRight(rs.getString(23));
				assessmentTemplate.setBicepLeft(rs.getString(24));
				assessmentTemplate.setTricepRight(rs.getString(25));
				assessmentTemplate.setTricepLeft(rs.getString(26));
				assessmentTemplate.setBrachioradialisRight(rs.getString(27));
				assessmentTemplate.setBrachioradialisLeft(rs.getString(28));
				assessmentTemplate.setUlnt1Right(rs.getString(29));
				assessmentTemplate.setUlnt1Left(rs.getString(30));
				assessmentTemplate.setUlnt2Right(rs.getString(31));
				assessmentTemplate.setUlnt2Left(rs.getString(32));
				assessmentTemplate.setUlnt3Right(rs.getString(33));
				assessmentTemplate.setUlnt3Left(rs.getString(34));
				assessmentTemplate.setJrActiveMoment(rs.getString(35));
				assessmentTemplate.setJrPassiveMoment(rs.getString(36));
				assessmentTemplate.setMyotomes(rs.getString(37));
				assessmentTemplate.setDermatomes(rs.getString(38));
				assessmentTemplate.setSensoryLossChanges(rs.getString(39));
				assessmentTemplate.setKneeRight(rs.getString(40));
				assessmentTemplate.setKneeLeft(rs.getString(41));
				assessmentTemplate.setAnkleRight(rs.getString(42));
				assessmentTemplate.setAnkleLeft(rs.getString(43));
				assessmentTemplate.setBabinskiRight(rs.getString(44));
				assessmentTemplate.setBabinskiLeft(rs.getString(45));
				assessmentTemplate.setSlumpRight(rs.getString(46));
				assessmentTemplate.setSlumpLeft(rs.getString(47));
				assessmentTemplate.setDorsiflexionRom1(rs.getString(48));
				assessmentTemplate.setDorsiflexionPain1(rs.getString(49));
				assessmentTemplate.setDorsiflexionRom2(rs.getString(50));
				assessmentTemplate.setDorsiflexionPain2(rs.getString(51));
				assessmentTemplate.setMedicalRotaionRom1(rs.getString(52));
				assessmentTemplate.setMedicalRotaionPain1(rs.getString(53));
				assessmentTemplate.setMedicalRotaionRom2(rs.getString(54));
				assessmentTemplate.setMedicalRotaionPain2(rs.getString(55));
				assessmentTemplate.setNeckFlexionRom1(rs.getString(56));
				assessmentTemplate.setNeckFlexionPain1(rs.getString(57));
				assessmentTemplate.setNeckFlexionRom2(rs.getString(58));
				assessmentTemplate.setNeckFlexionPain2(rs.getString(59));
				assessmentTemplate.setPassiveKneeBendRom1(rs.getString(60));
				assessmentTemplate.setPassiveKneeBendPain1(rs.getString(61));
				assessmentTemplate.setPassiveKneeBendRom2(rs.getString(62));
				assessmentTemplate.setPassiveKneeBendPain2(rs.getString(63));
				assessmentTemplate.setDizzyy(rs.getString(64));
				assessmentTemplate.setDizzyn(rs.getString(65));
				assessmentTemplate.setDoubleVisiony(rs.getString(66));
				assessmentTemplate.setDoubleVisionn(rs.getString(67));
				assessmentTemplate.setDysarthriay(rs.getString(68));
				assessmentTemplate.setDysarthrian(rs.getString(69));
				assessmentTemplate.setDysphagiay(rs.getString(70));
				assessmentTemplate.setDysphagian(rs.getString(71));
				assessmentTemplate.setDropAttacky(rs.getString(72));
				assessmentTemplate.setDropAttackn(rs.getString(73));
				assessmentTemplate.setNystagmusy(rs.getString(74));
				assessmentTemplate.setNystagmusn(rs.getString(75));
				assessmentTemplate.setNauseay(rs.getString(76));
				assessmentTemplate.setNausean(rs.getString(77));
				assessmentTemplate.setNumbnessy(rs.getString(78));
				assessmentTemplate.setNumbnessn(rs.getString(79));
				assessmentTemplate.setImageData(rs.getString(80));
				assessmentTemplate.setDiaryUserId(rs.getString(81));
				assessmentTemplate.setConditionId(rs.getString(82));
				//assessmentTemplate = getGeneralHealthDetails(rs.getInt(1));
				
			}
			
		}catch(Exception e){
			
		}
		return assessmentTemplate;
	}

	public AssessmentTemplate getGeneralHealthDetails(int physioId) {
		PreparedStatement preparedStatement = null;
		AssessmentTemplate assessmentTemplate = new AssessmentTemplate();
		String sql = "select diabetesyn,diabetesDetail,epilepsyyn,epilepsyDetail,"
				+ "heartConditionyn,heartConditionDetail,lungDisorderyn,lungDisorderDetail,prostateyn,prostateDetail,"
				+ "carcinomayn,carcinomaDetail,rayn,raDetail,osteopaeniayn,osteopaeniaDetail,wtStableyn,wtStableDetail,"
				+ "bladderBowelyn,bladderBowelDetail,otheryn,otherDetail from apm_assessment_physio_general_health "
				+ "where physioId = "+physioId+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				assessmentTemplate.setDiabetesyn(rs.getString(1));
				assessmentTemplate.setDiabetesDetail(rs.getString(2));
				assessmentTemplate.setEpilepsyyn(rs.getString(3));
				assessmentTemplate.setEpilepsyDetail(rs.getString(4));
				assessmentTemplate.setHeartConditionyn(rs.getString(5));
				assessmentTemplate.setHeartConditionDetail(rs.getString(6));
				assessmentTemplate.setLungDisorderyn(rs.getString(7));
				assessmentTemplate.setLungDisorderDetail(rs.getString(8));
				assessmentTemplate.setProstateyn(rs.getString(9));
				assessmentTemplate.setProstateDetail(rs.getString(10));
				assessmentTemplate.setCarcinomayn(rs.getString(11));
				assessmentTemplate.setCarcinomaDetail(rs.getString(12));
				assessmentTemplate.setRayn(rs.getString(13));
				assessmentTemplate.setRaDetail(rs.getString(14));
				assessmentTemplate.setOsteopaeniayn(rs.getString(15));
				assessmentTemplate.setOsteopaeniaDetail(rs.getString(16));
				assessmentTemplate.setWtStableyn(rs.getString(17));
				assessmentTemplate.setWtStableDetail(rs.getString(18));
				assessmentTemplate.setBladderBowelyn(rs.getString(19));
				assessmentTemplate.setBladderBowelDetail(rs.getString(20));
				assessmentTemplate.setOtheryn(rs.getString(21));
				assessmentTemplate.setOtherDetail(rs.getString(22));
								
			}
					
			
		}catch(Exception e){
			
		}
		return assessmentTemplate;
	}

	
	public int updateTemplateDetails(int physioTemplateId,AssessmentTemplate assessmentTemplate) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		int physioId = 0;
		String sql = "update apm_assessment_physio_template set clientId=?,clientName=?,examDate=?,subjectiveHistory=?,outcomeMeasurement=?,"
				+ "primaryDiagnosis=?,secondaryDiagnosys=?,assessmentDate=?,treatmentDate=?,treatmentUsed=?,dischargeStatus=?,primaryDiagnosisDetails=?,"
				+ "secondaryDiagnosysDetails=?,medication=?,investigation=?,generalHealth=?,nightPain=?,familyHistory=?,psychoSocial=?,surgery=?,accident=?,"
				+ "bicepRight=?,bicepLeft=?,tricepRight=?,tricepLeft=?,brachioradialisRight=?,brachioradialisLeft=?,ulnt1Right=?,ulnt1Left=?,ulnt2Right=?,"
				+ "ulnt2Left=?,ulnt3Right=?,ulnt3Left=?,jrActiveMoment=?,jrPassiveMoment=?,myotomes=?,dermatomes=?,sensoryLossChanges=?,kneeRight=?,kneeLeft=?,"
				+ "ankleRight=?,ankleLeft=?,babinskiRight=?,babinskiLeft=?,slumpRight=?,slumpLeft=?,dorsiflexionRom1=?,dorsiflexionPain1=?,dorsiflexionRom2=?,"
				+ "dorsiflexionPain2=?,medicalRotaionRom1=?,medicalRotaionPain1=?,medicalRotaionRom2=?,medicalRotaionPain2=?,neckFlexionRom1=?,neckFlexionPain1=?,"
				+ "neckFlexionRom2=?,neckFlexionPain2=?,passiveKneeBendRom1=?,passiveKneeBendPain1=?,passiveKneeBendRom2=?,passiveKneeBendPain2=?,dizzyy=?,dizzyn=?,"
				+ "doubleVisiony=?,doubleVisionn=?,dysarthriay=?,dysarthrian=?,dysphagiay=?,dysphagian=?,dropAttacky=?,dropAttackn=?,nystagmusy=?,nystagmusn=?,nauseay=?,"
				+ "nausean=?,numbnessy=?,numbnessn=?,practitionerId=?,conditionId=? where id = "+physioTemplateId+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, assessmentTemplate.getClientId());
			preparedStatement.setString(2, assessmentTemplate.getClient());
			preparedStatement.setString(3, assessmentTemplate.getExamDate());
			preparedStatement.setString(4, assessmentTemplate.getSubjectiveHistory());
			preparedStatement.setString(5, assessmentTemplate.getOutcomeMeasurement());
			preparedStatement.setString(6, assessmentTemplate.getPrimaryDiagnosis());
			preparedStatement.setString(7, assessmentTemplate.getSecondaryDiagnosys());
			preparedStatement.setString(8, assessmentTemplate.getAssessmentDate());
			preparedStatement.setString(9, assessmentTemplate.getTreatmentDate());
			preparedStatement.setString(10, assessmentTemplate.getTreatmentUsed());
			preparedStatement.setString(11, assessmentTemplate.getDischargeStatus());
			preparedStatement.setString(12, assessmentTemplate.getPrimaryDiagnosisDetails());
			preparedStatement.setString(13, assessmentTemplate.getSecondaryDiagnosysDetails());
			preparedStatement.setString(14, assessmentTemplate.getMedication());
			preparedStatement.setString(15, assessmentTemplate.getInvestigation());
			preparedStatement.setString(16, assessmentTemplate.getGeneralHealth());
			preparedStatement.setString(17, assessmentTemplate.getNightPain());
			preparedStatement.setString(18, assessmentTemplate.getFamilyHistory());
			preparedStatement.setString(19, assessmentTemplate.getPsychoSocial());
			preparedStatement.setString(20, assessmentTemplate.getSurgery());
			preparedStatement.setString(21, assessmentTemplate.getAccident());
			preparedStatement.setString(22, assessmentTemplate.getBicepRight());
			preparedStatement.setString(23, assessmentTemplate.getBicepLeft());
			preparedStatement.setString(24, assessmentTemplate.getTricepRight());
			preparedStatement.setString(25, assessmentTemplate.getTricepLeft());
			preparedStatement.setString(26, assessmentTemplate.getBrachioradialisRight());
			preparedStatement.setString(27, assessmentTemplate.getBrachioradialisLeft());
			preparedStatement.setString(28, assessmentTemplate.getUlnt1Right());
			preparedStatement.setString(29, assessmentTemplate.getUlnt1Left());
			preparedStatement.setString(30, assessmentTemplate.getUlnt2Right());
			preparedStatement.setString(31, assessmentTemplate.getUlnt2Left());
			preparedStatement.setString(32, assessmentTemplate.getUlnt3Right());
			preparedStatement.setString(33, assessmentTemplate.getUlnt3Left());
			preparedStatement.setString(34, assessmentTemplate.getJrActiveMoment());
			preparedStatement.setString(35, assessmentTemplate.getJrPassiveMoment());
			preparedStatement.setString(36, assessmentTemplate.getMyotomes());
			preparedStatement.setString(37, assessmentTemplate.getDermatomes());
			preparedStatement.setString(38, assessmentTemplate.getSensoryLossChanges());
			preparedStatement.setString(39, assessmentTemplate.getKneeRight());
			preparedStatement.setString(40, assessmentTemplate.getKneeLeft());
			preparedStatement.setString(41, assessmentTemplate.getAnkleRight());
			preparedStatement.setString(42, assessmentTemplate.getAnkleLeft());
			preparedStatement.setString(43, assessmentTemplate.getBabinskiRight());
			preparedStatement.setString(44, assessmentTemplate.getBabinskiLeft());
			preparedStatement.setString(45, assessmentTemplate.getSlumpRight());
			preparedStatement.setString(46, assessmentTemplate.getSlumpLeft());
			preparedStatement.setString(47, assessmentTemplate.getDorsiflexionRom1());
			preparedStatement.setString(48, assessmentTemplate.getDorsiflexionPain1());
			preparedStatement.setString(49, assessmentTemplate.getDorsiflexionRom2());
			preparedStatement.setString(50, assessmentTemplate.getDorsiflexionPain2());
			preparedStatement.setString(51, assessmentTemplate.getMedicalRotaionRom1());
			preparedStatement.setString(52, assessmentTemplate.getMedicalRotaionPain1());
			preparedStatement.setString(53, assessmentTemplate.getMedicalRotaionRom2());
			preparedStatement.setString(54, assessmentTemplate.getMedicalRotaionPain2());
			preparedStatement.setString(55, assessmentTemplate.getNeckFlexionRom1());
			preparedStatement.setString(56, assessmentTemplate.getNeckFlexionPain1());
			preparedStatement.setString(57, assessmentTemplate.getNeckFlexionRom2());
			preparedStatement.setString(58, assessmentTemplate.getNeckFlexionPain2());
			preparedStatement.setString(59, assessmentTemplate.getPassiveKneeBendRom1());
			preparedStatement.setString(60, assessmentTemplate.getPassiveKneeBendPain1());
			preparedStatement.setString(61, assessmentTemplate.getPassiveKneeBendRom2());
			preparedStatement.setString(62, assessmentTemplate.getPassiveKneeBendPain2());
			preparedStatement.setString(63, assessmentTemplate.getDizzyy());
			preparedStatement.setString(64, assessmentTemplate.getDizzyn());
			preparedStatement.setString(65, assessmentTemplate.getDoubleVisiony());
			preparedStatement.setString(66, assessmentTemplate.getDoubleVisionn());
			preparedStatement.setString(67, assessmentTemplate.getDysarthriay());
			preparedStatement.setString(68, assessmentTemplate.getDysarthrian());
			preparedStatement.setString(69, assessmentTemplate.getDysphagiay());
			preparedStatement.setString(70, assessmentTemplate.getDysphagian());
			preparedStatement.setString(71, assessmentTemplate.getDropAttacky());
			preparedStatement.setString(72, assessmentTemplate.getDropAttackn());
			preparedStatement.setString(73, assessmentTemplate.getNystagmusy());
			preparedStatement.setString(74, assessmentTemplate.getNystagmusn());
			preparedStatement.setString(75, assessmentTemplate.getNauseay());
			preparedStatement.setString(76, assessmentTemplate.getNausean());
			preparedStatement.setString(77, assessmentTemplate.getNumbnessy());
			preparedStatement.setString(78, assessmentTemplate.getNumbnessn());
			//preparedStatement.setString(79, assessmentTemplate.getImageData());
			preparedStatement.setString(79, assessmentTemplate.getDiaryUserId());
			preparedStatement.setString(80, assessmentTemplate.getConditionId());
			
			result = preparedStatement.executeUpdate();
			
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	
	public int updateGeneralHealthDetails(int physioTemplateId,AssessmentTemplate assessmentTemplate) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_assessment_physio_general_health set diabetesyn=?,diabetesDetail=?,epilepsyyn=?,epilepsyDetail=?,"
				+ "heartConditionyn=?,heartConditionDetail=?,lungDisorderyn=?,lungDisorderDetail=?,prostateyn=?,prostateDetail=?,"
				+ "carcinomayn=?,carcinomaDetail=?,rayn=?,raDetail=?,osteopaeniayn=?,osteopaeniaDetail=?,wtStableyn=?,wtStableDetail=?,"
				+ "bladderBowelyn=?,bladderBowelDetail=?,otheryn=?,otherDetail=? where physioId = "+physioTemplateId+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, assessmentTemplate.getDiabetesyn());
			preparedStatement.setString(2, assessmentTemplate.getDiabetesDetail());
			preparedStatement.setString(3, assessmentTemplate.getEpilepsyyn());
			preparedStatement.setString(4, assessmentTemplate.getEpilepsyDetail());
			preparedStatement.setString(5, assessmentTemplate.getHeartConditionyn());
			preparedStatement.setString(6, assessmentTemplate.getHeartConditionDetail());
			preparedStatement.setString(7, assessmentTemplate.getLungDisorderyn());
			preparedStatement.setString(8, assessmentTemplate.getLungDisorderDetail());
			preparedStatement.setString(9, assessmentTemplate.getProstateyn());
			preparedStatement.setString(10, assessmentTemplate.getProstateDetail());
			preparedStatement.setString(11, assessmentTemplate.getCarcinomayn());
			preparedStatement.setString(12, assessmentTemplate.getCarcinomaDetail());
			preparedStatement.setString(13, assessmentTemplate.getRayn());
			preparedStatement.setString(14, assessmentTemplate.getRaDetail());
			preparedStatement.setString(15, assessmentTemplate.getOsteopaeniayn());
			preparedStatement.setString(16, assessmentTemplate.getOsteopaeniaDetail());
			preparedStatement.setString(17, assessmentTemplate.getWtStableyn());
			preparedStatement.setString(18, assessmentTemplate.getWtStableDetail());
			preparedStatement.setString(19, assessmentTemplate.getBladderBowelyn());
			preparedStatement.setString(20, assessmentTemplate.getBladderBowelDetail());
			preparedStatement.setString(21, assessmentTemplate.getOtheryn());
			preparedStatement.setString(22, assessmentTemplate.getOtherDetail());
			
			result = preparedStatement.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	
	public int deletePhysioTemplate(int selectedId) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from apm_assessment_physio_template where id = "+selectedId+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			
		}
		return result;
	}

	
	public int deleteGeneralHealthTemplate(int selectedId) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from apm_assessment_physio_general_health where physioId = "+selectedId+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			
		}
		return result;
	}

	public int savePhysioAssessmentFormName(String name) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		int formId = 0;
		boolean nameExist = false;
		String sql = "insert into apm_assment_template (templateName,type) values (?,?)";
		try{
			preparedStatement = connection.prepareStatement(sql);
			nameExist = IsNameExist(name);
			if(nameExist != true ){
				//preparedStatement.setInt(1, 0);
				preparedStatement.setString(1, name);
				preparedStatement.setInt(2, 1);
				result = preparedStatement.executeUpdate();
			}
			
			if(result == 1){
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if(resultSet.next()){
					formId = resultSet.getInt(1);  
				}
			}else{
				formId = getTemplateFormId();
			}
			
			
		}catch(Exception e){
			
		}
		return formId;
	}

	private int getTemplateFormId() {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select id from apm_assment_template where type = 1";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getInt(1);
			}

		}catch(Exception e){
			
		}
		return result;
	}

	private boolean IsNameExist(String name) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "select * from apm_assment_template where templateName = '"+name+"'";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = true;
			}

		}catch(Exception e){
			
		}
		return result;
	}

	public int savePhysioClientFormName(String name, int formId, String clientId,String practId,String conditionId,String img) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		//clientId = "";
		boolean nameExist = false;
		String sql = "insert into apm_assessment_client_details (templateId,templateName,clientId,practitionerId,conditionId,imagedata,type) values (?,?,?,?,?,?,?)";
		try{
			preparedStatement = connection.prepareStatement(sql);
			//nameExist = IsTemplateNameExist(formId,name);
			//if(nameExist != true ){
				//preparedStatement.setInt(1, 0);
				preparedStatement.setInt(1, formId);
				preparedStatement.setString(2, name);
				preparedStatement.setString(3, clientId);
				preparedStatement.setString(4, practId);
				preparedStatement.setString(5, conditionId);
				preparedStatement.setString(6, img);
				preparedStatement.setInt(7, 1);
				
				result = preparedStatement.executeUpdate();
			//}

		}catch(Exception e){
			
		}
		return result;
	}

	private boolean IsTemplateNameExist(int formId,String name) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "select * from apm_assessment_client_details where templateId = "+formId+" or templateName = '"+name+"'";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = true;
			}

		}catch(Exception e){
			
		}
		return result;
	}

	public String getTemplateName(String tempalteId) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select templateName from apm_assessment_client_details where id = "+tempalteId+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
			}
			
		}catch(Exception e){
			
		}
		return result;
	}

	public int updateImageTemplate(int selectedId, String imageData) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_assessment_physio_template set imageData = ? where id = "+selectedId+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, imageData);
			
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			//e.printStackTrace();
		}
		
		return result;
	}

	public String getTemplateId(String clientId,String practId,String conditionId) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select templateId from apm_assessment_client_details where clientId = "+clientId+" and practitionerId = "+practId+" and conditionId = "+conditionId+" and type = 1";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
			}
			
		}catch(Exception e){
			
		}
		return result;
	}

	public int getDetailsById(int selectedId) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select clientId,practitionerId,conditionId from apm_assessment_client_details where id = "+selectedId+" and type = 1";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				result = getPhysioId(rs.getString(1),rs.getString(2),rs.getString(3));
			}
			
		}catch(Exception e){
			
		}
		return result;
	}

	private int getPhysioId(String clientId, String practitionerId, String conditionId) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select id from apm_assessment_physio_template where clientId = "+clientId+" and practitionerId = "+practitionerId+" and conditionId = "+conditionId+" ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				result = rs.getInt(1);
			}
			
		}catch(Exception e){
			
		}
		return result;
	}

	public int deletePainAreaTemplate(int selectedId) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from apm_assessment_pain_area where physioId = "+selectedId+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			
		}
		return result;
	}

	public int deleteClientAssessment(int selectedId) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select clientId,practitionerId,conditionId from apm_assessment_physio_template where id = "+selectedId+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				result = deleteClientDetailsId(rs.getString(1),rs.getString(2),rs.getString(3));
			}
			
			
		}catch(Exception e){
			
		}
		return result;
	}

	private int deleteClientDetailsId(String clientId, String practitionerId, String conditionId) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from apm_assessment_client_details where clientId = "+clientId+" and practitionerId = "+practitionerId+" and conditionId = "+conditionId+" and type = 1 ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			
		}
		return result;
	}

	
	

}
