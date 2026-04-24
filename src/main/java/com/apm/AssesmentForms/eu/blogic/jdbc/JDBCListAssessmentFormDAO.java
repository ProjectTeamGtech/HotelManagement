package com.apm.AssesmentForms.eu.blogic.jdbc;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.apm.AssesmentForms.eu.bi.ListAssessmentDAO;
import com.apm.AssesmentForms.eu.entity.Assessment;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.common.eu.blogic.jdbc.JDBCBaseDAO;
import com.apm.common.utils.DateTimeUtils;

public class JDBCListAssessmentFormDAO extends JDBCBaseDAO implements ListAssessmentDAO{
		
		public JDBCListAssessmentFormDAO(Connection connection){
			this.connection = connection;
			
		}

		public ArrayList<String> getAssessmentFormList(String clientId,
				String clientname, String tempalteId,String practId,String conditionId) {
			PreparedStatement preparedStatement = null;
			ArrayList<Assessment> columnList = getColumnList(tempalteId);
			ArrayList<String>list = new ArrayList<String>();
			String columnstr = "";
			for(Assessment a : columnList){
				columnstr = a.getColumnstr();
			}
			String sql = "select "+columnstr+" from apm_assessment_client_details where templateId = "+tempalteId+" and clientId = "+clientId+" and practitionerId = "+practId+" and conditionId = "+conditionId+" ";

			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				
				while(rs.next()){
					for(Assessment a : columnList){
					String str = rs.getString(a.getFiledname());
					
					
					list.add(str);
					}
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			return list;
		}

		public ArrayList<Assessment> getColumnList(String tempalteId) {
			PreparedStatement preparedStatement = null;
			ArrayList<Assessment>list = new ArrayList<Assessment>();
			String sql = "SELECT id,lable_name FROM apm_assment_template_field where templateId = "+tempalteId+" and type!= 1 ";
			String str = "";
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					
					Assessment assessment = new Assessment();
					
					assessment.setId(rs.getInt(1));
					assessment.setFiledname(rs.getString(2));
					str = str+rs.getString(2)+","; 
					String a = str.substring(0, str.lastIndexOf(","));
					
					assessment.setColumnstr(a);
					
					list.add(assessment);
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			return list;
		}

		public int getColumnLength(String tempalteId) {
			PreparedStatement preparedStatement = null;
			String sql = "SELECT count(id) FROM apm_assment_template_field where templateId = "+tempalteId+" and type != 1";
			int size = 0;
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					
					
					size = rs.getInt(1);
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			return size;
		}

		public String getTemplateId(String id) {
			PreparedStatement preparedStatement = null;
			String sql = "SELECT templateId FROM apm_assessment_client_details where id = "+id+"";
			String tempId = "";
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					
					
					tempId = rs.getString(1);
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			return tempId;
		}

		public String getColumnString(String tempId) {
			PreparedStatement preparedStatement = null;
			ArrayList<Assessment>list = new ArrayList<Assessment>();
			String sql = "SELECT id,lable_name FROM apm_assment_template_field where templateId = "+tempId+"";
			String str = "";
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					
					Assessment assessment = new Assessment();
					
					assessment.setId(rs.getInt(1));
					assessment.setFiledname(rs.getString(2));
					str = str+rs.getString(2)+","; 
					String a = str.substring(0, str.lastIndexOf(","));
					str = a;
					assessment.setColumnstr(a);
					
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			return str;
		}

		

		public String getImageDetails(String id) {
			PreparedStatement preparedStatement = null;
			
			String result = "";
			String sql = "select imagedata from apm_assessment_client_details where id = "+id+"";
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

		public ArrayList<Assessment> getFieldNameListById(String tempId,
				String id) {
			PreparedStatement preparedStatement = null;
			ArrayList<Assessment>list = new ArrayList<Assessment>();
			String sql = "SELECT id,field_name,type,typeValue,sizeValue,rptvalue FROM apm_assment_template_field where templateId = "+tempId+"";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					
					Assessment assessment = new Assessment();					
					assessment.setId(rs.getInt(1));
					assessment.setFiledname(rs.getString(2));
					String fieldname = DateTimeUtils.removeAllSpecialChar(assessment.getFiledname());
					fieldname = fieldname.replace(" ", "");
					String fieldValue = getFieldValueName(fieldname,tempId,id);
					assessment.setFiledValue(fieldValue);
					assessment.setType(rs.getString(3));
					assessment.setTypeValue(rs.getString(4));
					assessment.setSizeValue(rs.getString(5));
					assessment.setRptvalue(rs.getString(6));
					assessment.setTemplateId(tempId);
					
					if(assessment.getType().equals("6")){
						String temp[] = assessment.getFiledValue().split(":");
						String customName = getCustomName(temp[0]);
						assessment.setCustomText(customName);
					}
					
					
					
					
					list.add(assessment);
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			return list;
		}

		private String getCustomName(String id) {
			PreparedStatement preparedStatement = null;
			String resule = "";
			String sql = "SELECT name FROM apm_custom_dropdown where id = "+id+" ";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				if(rs.next()){
					resule = rs.getString(1);
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			return resule;
		}

		private String getFieldValueName(String fieldname, String tempId,String id) {
			PreparedStatement preparedStatement = null;
			String result = "";
			String sql = "SELECT "+fieldname+" FROM apm_assessment_client_details where templateid = "+tempId+" and id="+id+" ";
			
			try{
				
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				if(rs.next()){
					result = rs.getString(1);
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
//			System.out.println(fieldname);
			return result;
		}

		public ArrayList<String> getAssessmentFormClientDetails(String id,
				String columnStr, String tempId) {
			PreparedStatement preparedStatement = null;
			ArrayList<Assessment> columnList = getColumnList(tempId);
			ArrayList<String>list = new ArrayList<String>();
			String columnstr = "";
			for(Assessment a : columnList){
				columnstr = a.getColumnstr();
			}
			String sql = "select "+columnstr+" from apm_assessment_client_details where id = "+id+"";

			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				
				while(rs.next()){
					for(Assessment a : columnList){
					String str = rs.getString(a.getFiledname());
					
					
					list.add(str);
					}
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			return list;
		}

		public ArrayList<Assessment> getColumnFieldList(String tempalteId) {
			PreparedStatement preparedStatement = null;
			ArrayList<Assessment>list = new ArrayList<Assessment>();
			String sql = "SELECT id,field_name,rptvalue FROM apm_assment_template_field where templateId = "+tempalteId+" and type!= 1 ";
			String str = "";
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					
					Assessment assessment = new Assessment();
					
					assessment.setId(rs.getInt(1));
					assessment.setFiledname(rs.getString(2));
					assessment.setRptvalue(rs.getString(3));
					str = str+rs.getString(2)+"#"; 
					String a = str.substring(0, str.lastIndexOf("#"));
					
					assessment.setColumnstr(a);
					
					list.add(assessment);
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			return list;
		}

		public int getClientNameData(String tempId, String id) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "select clientId from apm_assessment_client_details where templateId = "+tempId+" and id = "+id+"";
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					result = Integer.parseInt(rs.getString(1));
				
				}
				
			}catch(Exception e){
				
			}
			return result;
		}

		public String getClientFullName(int clientId) {
			PreparedStatement preparedStatement = null;
			String result = "";
			String sql = "SELECT title,firstname,surname FROM apm_patient where  id = "+clientId+"";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					result = rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3);
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}

		public int updateAssessmentFormClient(int id, String fieldValue,String lableName, String textName,String datetime) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "update apm_assessment_client_details set "+fieldValue+" = ?,lastmodified='"+datetime+"' where id = "+id+"";
			try{
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, textName);
				result = preparedStatement.executeUpdate();
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
			return result;
		}

		public int updateAssessmentClientNameImage(int id, String clientId,String diaryUserId, String conditionId) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "update apm_assessment_client_details set clientId = ?,practitionerId = ?,conditionId = ? where id = "+id+"";
			try{
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, clientId);
				//preparedStatement.setString(2, imageData);
				preparedStatement.setString(2, diaryUserId);
				preparedStatement.setString(3, conditionId);
				result = preparedStatement.executeUpdate();
				
			}catch(Exception e){
				//e.printStackTrace();
			}
			
			return result;
		}

		public int deleteAssessmentClient(int id) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "delete from apm_assessment_client_details where id = "+id+"";
			try{
				preparedStatement = connection.prepareStatement(sql);
				result = preparedStatement.executeUpdate();
			}catch(Exception e){
				//e.printStackTrace();
			}
			
			return result;
		}

		public ArrayList<Assessment> getTemplateList(String clientId,String practId, String conditionId) {
			PreparedStatement preparedStatement = null;
			ArrayList<Assessment>list = new ArrayList<Assessment>();
			String sql = "SELECT id,templateId,templateName FROM apm_assessment_client_details where clientId = "+clientId+" and practitionerId = "+practId+" and conditionId = "+conditionId+" and templateName!= 'null'";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					
					Assessment assessment = new Assessment();					
					assessment.setId(rs.getInt(1));
					assessment.setTemplateId(rs.getString(2));
					assessment.setTemplateName(rs.getString(3));
					list.add(assessment);
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			return list;
		}

		public String getAssessmentClientId(String clientId) {
			PreparedStatement preparedStatement = null;
			String result = "";
			String sql = "select id from apm_assessment_client_details where clientId = "+clientId+" order by id desc limit 0,1 ";
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					
					result = rs.getString(1);
				}
				
			
			}catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}

		public Assessment getClientDetailsData(String tempId, String id) {
			PreparedStatement preparedStatement = null;
			Assessment assessment = new Assessment();
				String sql = "SELECT clientId,practitionerId,conditionId,templateName,ipdid,oldpractid,templateid FROM apm_assessment_client_details where templateId = "+tempId+" and id = "+id+"";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					
					assessment.setClientId(rs.getString(1));
					assessment.setDiaryUserId(rs.getString(2));
					assessment.setConditionId(rs.getString(3));
					assessment.setTemplateName(rs.getString(4));
					assessment.setIpdid(rs.getString(5));
					assessment.setOldpractid(rs.getString(6));
					if(rs.getString(6)==null){
						assessment.setOldpractid("0");
					}
					if(rs.getString(6).equals("")){
						assessment.setOldpractid("0");
					}
					assessment.setTemplateId(rs.getString(7));
					
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			return assessment;
		}

		public String getDiaryUserName(String diaryUserId) {
			PreparedStatement preparedStatement = null;
			String result = "";
			String sql = "SELECT initial,firstname,lastname FROM apm_user where  id = "+diaryUserId+"";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					result = rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3);
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}

		public String getConditionName(String conditionId) {
			PreparedStatement preparedStatement = null;
			String result = "";
			String sql = "SELECT name FROM apm_condition where  id = "+conditionId+"";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					result = rs.getString(1);
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}

		public int updateImageTemplate(int id, String imageData) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "update apm_assessment_client_details set imagedata = ? where id = "+id+"";
			try{
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, imageData);
				
				result = preparedStatement.executeUpdate();
				
			}catch(Exception e){
				//e.printStackTrace();
			}
			
			return result;
		}

		public String getTemplateId(int id) {
			PreparedStatement preparedStatement = null;
			String result = "";
			String sql = "SELECT templateId FROM apm_assessment_client_details where  id = "+id+"";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					result = rs.getString(1);
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}

		public int updateCombineImageTemplate(String id, String imageData) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "update apm_combine_assessment_client_details set imagedata = ? where id = "+id+"";
			try{
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, imageData);
				
				result = preparedStatement.executeUpdate();
				
			}catch(Exception e){
				//e.printStackTrace();
			}
			
			return result;
		}

		
}