package com.apm.AssesmentForms.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.apm.AssesmentForms.eu.bi.AssessmentMasterDAO;
import com.apm.AssesmentForms.eu.entity.Assessment;
import com.apm.common.eu.blogic.jdbc.JDBCBaseDAO;
import com.apm.common.utils.Pagination;

public class JDBCAssessmentMasterDAO extends JDBCBaseDAO implements AssessmentMasterDAO{
	
	public JDBCAssessmentMasterDAO(Connection connection){
		this.connection = connection;
		
	}

	public ArrayList<Assessment> getAssessmentFieldList(Pagination pagination) {
		PreparedStatement preparedStatement = null;
		ArrayList<Assessment> list = new ArrayList<Assessment>();
		String sql = "select id,filed_name,clinicId from apm_assessment_field_master";
		sql = pagination.getSQLQuery(sql);
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Assessment assessment = new Assessment();
				assessment.setId(rs.getInt(1));
				assessment.setFiledname(rs.getString(2));
				assessment.setClinicId(rs.getInt(3));
				
				list.add(assessment);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public int getTotalAssessmentCount() {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select count(*) from  apm_assessment_field_master";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getInt(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public int saveAssessmentField(Assessment assessment,String field) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_assessment_field_master(filed_name,clinicId,lable_name) values (?,?,?) ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, assessment.getFiledname());
			preparedStatement.setInt(2, assessment.getClinicId());
			preparedStatement.setString(3, field);
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public Assessment getAssessmentFieldDetails(int selectedid) {
		PreparedStatement preparedStatement = null;
		Assessment assessment = new Assessment();
		String sql = "select id,filed_name,clinicId from apm_assessment_field_master where id = "+selectedid+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				assessment.setId(rs.getInt(1));
				assessment.setFiledname(rs.getString(2));
				assessment.setClinicId(rs.getInt(3));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return assessment;
	}

	public int updateAssessmentField(String updateAssessmentField,Assessment assessment,int selectedid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_assessment_field_master set filed_name = ?,lable_name = ? where id = "+selectedid+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, assessment.getFiledname());
			preparedStatement.setString(2, updateAssessmentField);
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public int deleteAssessmentField(int selectedid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from apm_assessment_field_master where id = "+selectedid+"";
		try{
			preparedStatement = connection.prepareStatement(sql);			
			result = preparedStatement.executeUpdate();			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public int updateAssessmentTemplate(String field) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "ALTER TABLE apm_assessment_client_details ADD COLUMN "+field+" LONGTEXT";
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public String getExistingFieldName(int selectedid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select filed_name from apm_assessment_field_master where id = "+selectedid+"";		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public int updateAssessmentTemplateColumn(String updatedFieldName, String existingFieldName) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "ALTER TABLE apm_assessment_client_details CHANGE COLUMN "+existingFieldName+" "+updatedFieldName+" LONGTEXT";
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public int deleteAssessmentTemplateColumn(String existingFieldName) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "ALTER TABLE apm_assessment_client_details DROP COLUMN "+existingFieldName+"";
		try{
			preparedStatement = connection.prepareStatement(sql);			
			result = preparedStatement.executeUpdate();			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public int getTotalSubHeadingCount() {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select count(*) from  apm_assessment_heading_master";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getInt(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Assessment> getAssessmentSubHeadingList(
			Pagination pagination) {
		PreparedStatement preparedStatement = null;
		ArrayList<Assessment> list = new ArrayList<Assessment>();
		String sql = "select id,heading_name,clinicId from apm_assessment_heading_master";
		sql = pagination.getSQLQuery(sql);
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Assessment assessment = new Assessment();
				assessment.setId(rs.getInt(1));
				assessment.setHeadingName(rs.getString(2));
				assessment.setClinicId(rs.getInt(3));
				
				list.add(assessment);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public int saveAssessmentSubHeading(Assessment assessment) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_assessment_heading_master(heading_name,clinicId) values (?,?) ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, assessment.getHeadingName());
			preparedStatement.setInt(2, assessment.getClinicId());
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public Assessment getAssessmentSubHeadingDetails(int selectedid) {
		PreparedStatement preparedStatement = null;
		Assessment assessment = new Assessment();
		String sql = "select id,heading_name,clinicId from apm_assessment_heading_master where id = "+selectedid+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				assessment.setId(rs.getInt(1));
				assessment.setHeadingName(rs.getString(2));
				assessment.setClinicId(rs.getInt(3));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return assessment;
	}

	public int updateAssessmentField(Assessment assessment, int selectedid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_assessment_heading_master set heading_name = ? where id = "+selectedid+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, assessment.getHeadingName());
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public int deleteAssessmentSubHeading(int selectedid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from apm_assessment_heading_master where id = "+selectedid+"";
		try{
			preparedStatement = connection.prepareStatement(sql);			
			result = preparedStatement.executeUpdate();			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public int updatdateTemplateAssesmentFields(String temp, String temp1,
			String updatedFieldName) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_assment_template_field set  field_name=?,lable_name=? where lable_name=? ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, updatedFieldName);
			preparedStatement.setString(2, temp1);
			preparedStatement.setString(3, temp);
			
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	
}
