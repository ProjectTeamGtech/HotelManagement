package com.apm.AssesmentForms.eu.blogic.jdbc;

import java.io.Reader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.record.LeftMarginRecord;

import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.AssesmentForms.eu.bi.AssessmentFormDAO;
import com.apm.AssesmentForms.eu.entity.Assessment;
import com.apm.AssesmentForms.web.action.Template;
import com.apm.Master.eu.entity.Master;
import com.apm.Registration.eu.entity.PracticeManager;
import com.apm.common.eu.blogic.jdbc.JDBCBaseDAO;
import com.apm.common.utils.DateTimeUtils;


	public class JDBCAssessmentFormDAO extends JDBCBaseDAO implements AssessmentFormDAO{
		
		public JDBCAssessmentFormDAO(Connection connection){
			this.connection = connection;
			
		}

		public ArrayList<Assessment> getFieldList() {
			PreparedStatement preparedStatement = null;
			ArrayList<Assessment>list = new ArrayList<Assessment>();
			String sql = "SELECT id,filed_name FROM apm_assessment_field_master order by filed_name ";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					
					Assessment assessment = new Assessment();
					
					assessment.setId(rs.getInt(1));
					assessment.setFiledname(rs.getString(2));
					
					list.add(assessment);
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			return list;
		}

		public ArrayList<String> getFieldTextList() {
			PreparedStatement preparedStatement = null;
			ArrayList<String>list = new ArrayList<String>();
			String sql = "SELECT filed_name FROM apm_assessment_field_master";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){					
					String value = rs.getString(1);
					
					list.add(value);
					
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			return list;
		}

		public ArrayList<Assessment> getTemplateList() {
			PreparedStatement preparedStatement = null;
			ArrayList<Assessment>list = new ArrayList<Assessment>();
			String sql = "SELECT id,templateName,lastmodified,formtype FROM apm_assment_template where type !=1 order by id desc";
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					
					Assessment assessment = new Assessment();					
					assessment.setId(rs.getInt(1));
					assessment.setTemplateName(rs.getString(2));
					assessment.setType("0");
					if(rs.getString(3)!=null){
						assessment.setLastmodified(DateTimeUtils.getIndianDateTimeFormat(rs.getString(3)));
					}else{
						assessment.setLastmodified("");
					}
					
					assessment.setFormtype(rs.getString(4));
					list.add(assessment);
					
				}
				
				
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			return list;
		}

		/*public int SaveTemplateField() {
			PreparedStatement preparedStatement = null;
			int result = 0,id = 0;
			String sql = "insert into apm_assessment_client_details(templateName) values(?)";
			try{
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, "");
				result = preparedStatement.executeUpdate();
				
				if(result == 1){
					ResultSet rs = preparedStatement.getGeneratedKeys();
					if(rs.next()){
						id = rs.getInt(1);
					}
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
			return id;
		}*/

		public int saveUpdateTemplateField(int valueId, String fieldValue,String lableValue,String templateName,String typeValue, String sizeValue,String rptvalue) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			int type = 0;
			String str = "*";
			int i = fieldValue.lastIndexOf(str);
			
			if(rptvalue.equals("undefined")){
				rptvalue = "0";
			}
			if(rptvalue.equals("")){
				rptvalue = "0";
			}
			
			if(typeValue.equalsIgnoreCase("Heading")){
				type = 1;
				//slableValue = lableValue.replace("*", "");
			}
			if(typeValue.equalsIgnoreCase("Single Line Text")){
				type = 2;
			}
			if(typeValue.equalsIgnoreCase("Multi Line Text")){
				type = 3;
			}
			if(fieldValue.equalsIgnoreCase("id")){
				type = 4;
			}
			if(typeValue.equalsIgnoreCase("Y/N Dropdown")){
				type = 5;
			}
			if(typeValue.equalsIgnoreCase("Custom Dropdown")){
				type = 6;
			}
			//String sql = "update apm_assessment_client_details set "+fieldValue+" = ?,templateName = ? where id = "+valueId+"";
			String sql = "insert into apm_assment_template_field(templateId,field_name,lable_name,type,typeValue,sizeValue,rptvalue) values(?,?,?,?,?,?,?)";
			try{
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, valueId);
				preparedStatement.setString(2, lableValue);
				preparedStatement.setString(3, fieldValue);
				preparedStatement.setInt(4, type);
				preparedStatement.setString(5, typeValue);
				preparedStatement.setString(6, sizeValue);
				preparedStatement.setString(7, rptvalue);
				result = preparedStatement.executeUpdate();
				
			}catch(Exception e){
				e.printStackTrace();
			}
			return result;
		}

		public int saveTemplate(String templateName,String pic,String datetime,boolean includeImg,String tempNote,String speclialization,String layout,String formtype,String rowsno,String hdrcolumn) {
			PreparedStatement preparedStatement = null;
			int result = 0,id=0;
			String sql = "insert into apm_assment_template(templateName,imagedata,lastmodified,includeimage,templatenote,specialization,layout,formtype, rowsno, hdrcolumn) values(?,?,?,?,?,?,?,?,?,?)";
			try{
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1,templateName);
				preparedStatement.setString(2, pic);
				preparedStatement.setString(3, datetime);
				preparedStatement.setBoolean(4, includeImg);
				preparedStatement.setString(5, tempNote);
				preparedStatement.setString(6, speclialization);
				preparedStatement.setString(7, layout);
				preparedStatement.setString(8, formtype);
				preparedStatement.setString(9, rowsno);
				preparedStatement.setString(10, hdrcolumn);
				result = preparedStatement.executeUpdate();
				
				if(result == 1){
					ResultSet rs = preparedStatement.getGeneratedKeys();
					if(rs.next()){
						id = rs.getInt(1);
					}
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
			return id;
		}

		
		public ArrayList<Assessment> getFieldNameListById(String templateId) {
			PreparedStatement preparedStatement = null;
			ArrayList<Assessment>list = new ArrayList<Assessment>();
			String sql = "SELECT id,field_name,type,typeValue,sizeValue,rptvalue FROM apm_assment_template_field where templateId = "+templateId+" and type!=4";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					
					Assessment assessment = new Assessment();					
					assessment.setId(rs.getInt(1));
					assessment.setFiledname(rs.getString(2));
					assessment.setType(rs.getString(3));
					assessment.setTypeValue(rs.getString(4));
					assessment.setSizeValue(rs.getString(5));
					assessment.setRptvalue(rs.getString(6));
					assessment.setTemplateId(templateId);
					
					list.add(assessment);
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			return list;
		}

		public int saveUpdateTemplateDetails(int fieldId, String fieldValue,String lableName, String textName,String datetime) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "update apm_assessment_client_details set "+fieldValue+" = ?,lastmodified='"+datetime+"' where id = "+fieldId+"";
			try{
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, textName);
				result = preparedStatement.executeUpdate();
				
			}catch(Exception e){
				//e.printStackTrace();
			}
			
			return result;
		}

		public int saveIdOfTemplate(String templateId, String clientId, String imageData, String diaryUserId,
				String conditionId, String date, String ipdid, String oldpractid, String mrdid, String apmtid) {
			PreparedStatement preparedStatement = null;
			int result = 0,id = 0;
			String sql = "insert into apm_assessment_client_details(templateId,templateName,clientId,imagedata,practitionerId,conditionId,lastmodified,ipdid,oldpractid,opdid) values(?,?,?,?,?,?,?,?,?,?)";
			try{
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, templateId);
				String templateName = getTemplateName(templateId);
				preparedStatement.setString(2, templateName);
				preparedStatement.setString(3, clientId);
				preparedStatement.setString(4, imageData);
				preparedStatement.setString(5, diaryUserId);
				preparedStatement.setString(6, conditionId);
				preparedStatement.setString(7, date);
				preparedStatement.setString(8, ipdid);
				preparedStatement.setString(9, oldpractid);
				preparedStatement.setString(10, apmtid);
				result = preparedStatement.executeUpdate();
				
				if(result == 1){
					ResultSet rs = preparedStatement.getGeneratedKeys();
					if(rs.next()){
						id = rs.getInt(1);
					}
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
			return id;
		}

		public String getTemplateName(String templateId) {
			PreparedStatement preparedStatement = null;
			String result = "";
			String sql = "select templateName from apm_assment_template where id = "+templateId+"";
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

		public ArrayList<Assessment> getHeadingFieldList() {
			PreparedStatement preparedStatement = null;
			ArrayList<Assessment>list = new ArrayList<Assessment>();
			String sql = "SELECT id,heading_name FROM apm_assessment_heading_master";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					
					Assessment assessment = new Assessment();
					
					assessment.setId(rs.getInt(1));
					assessment.setHeadingName(rs.getString(2));
					
					list.add(assessment);
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			return list;
		}

		public String getImageDetails(String templateId) {
			PreparedStatement preparedStatement = null;
			
			String result = "";
			String sql = "select imagedata from apm_assment_template where id = "+templateId+"";
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				
				
				while(rs.next()){
					Reader in = rs.getCharacterStream(1);
					result = IOUtils.toString(in);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			return result;
		}

		public int insertImageData(int valueId, String imageData) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "update apm_assment_template set imagedata = ? where id = "+valueId+"";
			try{
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, imageData);
				result = preparedStatement.executeUpdate();
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
			return result;
		}

		public boolean IsTemplateNameExist(String templateName) {
			PreparedStatement preparedStatement = null;
			boolean result = false;
			String sql = "select * from apm_assment_template where templateName = '"+templateName+"' and templateName!= ''";
			
			try{				
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					result = true;
				}
			}catch (Exception e) {
				
			}
			
			
			return result;
		}

		public ArrayList<Assessment> getTemplateFieldList() {
			PreparedStatement preparedStatement = null;
			ArrayList<Assessment>list = new ArrayList<Assessment>();
			String sql = "SELECT id,filed_name FROM apm_assessment_field_master order by filed_name";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					
					Assessment assessment = new Assessment();
					
					assessment.setId(rs.getInt(1));
					assessment.setFiledname(rs.getString(2));
					
					list.add(assessment);
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			return list;
		}

		public ArrayList<Assessment> getAvailableFieldList(String tempListNameId) {
			PreparedStatement preparedStatement = null;
			ArrayList<Assessment>list = new ArrayList<Assessment>();
			String sql = "SELECT apm_assessment_field_master.id,apm_assessment_field_master.filed_name FROM apm_assessment_field_master "
					+ 	"where not exists (select apm_assment_template_field.id from apm_assment_template_field where apm_assessment_field_master.filed_name"
					+ " = apm_assment_template_field.field_name and apm_assment_template_field.templateId = "+tempListNameId+")";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					
					Assessment assessment = new Assessment();
					
					assessment.setId(rs.getInt(1));
					assessment.setFiledname(rs.getString(2));
					//int idtempField = rs.getInt(3);
					
					list.add(assessment);
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			return list;
		}
		
		
		public ArrayList<Assessment> getSelectedFieldList(String tempListNameId) {
			PreparedStatement preparedStatement = null;
			ArrayList<Assessment>list = new ArrayList<Assessment>();
			String sql = "SELECT id,templateId,field_name,type,typeValue,sizeValue,rptvalue FROM apm_assment_template_field where templateId = "+tempListNameId+" and type!=4";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					
					Assessment assessment = new Assessment();
					
					assessment.setId(rs.getInt(1));
					assessment.setTemplateId(rs.getString(2));
					assessment.setFiledname(rs.getString(3));
					assessment.setType(rs.getString(4));
					assessment.setTypeValue(rs.getString(5));
					assessment.setSizeValue(rs.getString(6));
					assessment.setRptvalue(rs.getString(7));
					
					list.add(assessment);
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			return list;
		}

		public int updateTemplate(String templateName, String pic,String datetime,boolean includeimage,String tempNote,String specialization,String layout, String formtype,String rowsno,String hdrcolumn) {
			PreparedStatement preparedStatement = null;
			int result = 0,id=0;
			String sql = "update apm_assment_template set imagedata = ?,lastmodified=?,includeimage=?,templatenote=?,specialization=?,layout=?,formtype=?, rowsno=?, hdrcolumn=? where templateName = '"+templateName+"'";
			try{
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1,pic);
				preparedStatement.setString(2, datetime);
				preparedStatement.setBoolean(3, includeimage);
				preparedStatement.setString(4, tempNote);
				preparedStatement.setString(5, specialization);
				preparedStatement.setString(6, layout);
				preparedStatement.setString(7, formtype);
				preparedStatement.setString(8, rowsno);
				preparedStatement.setString(9, hdrcolumn);
				result = preparedStatement.executeUpdate();
				
				if(result == 1){
					id = getTemplateIdByName(templateName);
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
			return id;
		}

		private int getTemplateIdByName(String templateName) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "select id from apm_assment_template where templateName = '"+templateName+"'";
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

		public int updateTemplateField(int valueId, String fieldValue, String lableValue, String templateName, 
				String typeValue,	String sizeValue, int id) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			int type = 0;
			String str = "*";
			int i = fieldValue.lastIndexOf(str);
			if(i != -1){
				type = 1;
			}
			if(typeValue.equalsIgnoreCase("Single Line Text")){
				type = 2;
			}
			if(typeValue.equalsIgnoreCase("Multi Line Text")){
				type = 3;
			}
			if(fieldValue.equalsIgnoreCase("id")){
				type = 4;
			}
			//String sql = "update apm_assessment_client_details set "+fieldValue+" = ?,templateName = ? where id = "+valueId+"";
			String sql = "update apm_assment_template_field set templateId=?,field_name=?,lable_name=?,type=?,typeValue=?,sizeValue=? where id = "+id+"";
			try{
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, valueId);
				preparedStatement.setString(2, lableValue);
				preparedStatement.setString(3, fieldValue);
				preparedStatement.setInt(4, type);
				preparedStatement.setString(5, typeValue);
				preparedStatement.setString(6, sizeValue);
				result = preparedStatement.executeUpdate();
				
			}catch(Exception e){
				e.printStackTrace();
			}
			return result;
			
		}

		public int deleteTemplateField(int id) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "delete from apm_assment_template_field where id = "+id+"";
			try{
				preparedStatement = connection.prepareStatement(sql);
				result = preparedStatement.executeUpdate();
				
			}catch(Exception e){
				e.printStackTrace();
			}
			return result;
		}

		public int deleteExistingId(int valueId) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "delete from apm_assment_template_field where templateId = "+valueId+" and type = 4";
			try{
				preparedStatement = connection.prepareStatement(sql);
				result = preparedStatement.executeUpdate();
				
			}catch(Exception e){
				e.printStackTrace();
			}
			return result;
		}

		public int updateImageTemplate(String templateId, String imageData) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "update apm_assment_template set imagedata = ? where id = "+templateId+"";
			try{
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1,imageData);
				//preparedStatement.setString(2, pic);
				result = preparedStatement.executeUpdate();
				
			
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
			return result;
		
		}

		public ArrayList<Master> getCustomDataList() {
			PreparedStatement preparedStatement = null;
			ArrayList<Master>list = new ArrayList<Master>();
			String sql = "SELECT id,name FROM apm_custom_dropdown ";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					Master master = new Master();
					master.setId(rs.getInt(1));
					master.setName(rs.getString(2));
					
					list.add(master);
				}
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
			return list;
		}

		public int deleteClientTemplateFieldData(String templateid) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "delete  FROM apm_assessment_client_details where templateid = "+templateid+" ";
			
			try{
				
				preparedStatement = connection.prepareStatement(sql);
				result = preparedStatement.executeUpdate();
				
			}catch(Exception e){
				e.printStackTrace();
			}
			return result;
		}

		public int deleteTemplate(String selectedid) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "delete FROM apm_assment_template where id = "+selectedid+" ";
			
			try{
				
				preparedStatement = connection.prepareStatement(sql);
				result = preparedStatement.executeUpdate();
				
			}catch(Exception e){
				e.printStackTrace();
			}
			return result;
		}

		public ArrayList<Assessment> getClientAssesementFormList(String clientId,String clientName) {
			PreparedStatement preparedStatement = null; 
			ArrayList<Assessment>list = new ArrayList<Assessment>();
			String sql = "SELECT id,templateName,lastmodified FROM apm_assessment_client_details where clientid = "+clientId+" ";
			
			try{
				
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					Assessment assessment = new Assessment();
					assessment.setId(rs.getInt(1));
					assessment.setTemplateName("("+clientName+") " + rs.getString(2));
					assessment.setType("1");
					if(rs.getString(3)!=null){
						assessment.setLastmodified(DateTimeUtils.getIndianDateTimeFormat(rs.getString(3)));
					}else{
						assessment.setLastmodified("");
					}
					
					
					list.add(assessment);
				}
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
			return list;
		}

		public int delteTemplateField(int valueId) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "delete FROM apm_assment_template_field where templateid = "+valueId+" ";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				result = preparedStatement.executeUpdate();
				
			}catch(Exception e){
				e.printStackTrace();
			}
			return result;
		}

		public int deleteTemplateFieldByTempid(String selectedid) {
			PreparedStatement preparedStatement = null;
			int res = 0;
			String sql = "delete FROM apm_assment_template_field where templateid="+selectedid+"";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				res = preparedStatement.executeUpdate();
				
			}catch(Exception e){
				e.printStackTrace();
			}
			return res;
		}

		public Template getTemplateDetails(String templateId) {
			PreparedStatement preparedStatement = null;
			Template template = new Template();
			String sql = "SELECT includeimage,specialization,layout,templatenote FROM apm_assment_template where id = "+templateId+" ";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				if(rs.next()){
					template.setIncludeImage(rs.getBoolean(1));
					template.setSpecialization(rs.getString(2));
					template.setLayout(rs.getString(3));
					template.setTemplateNote(rs.getString(4));
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			return template;
		}

		public ArrayList<Master> getSpecializationList() {
			PreparedStatement preparedStatement  = null;
			ArrayList<Master>list = new ArrayList<Master>();
			String sql = "SELECT id,name FROM apm_form_specialization ";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					Master master = new Master();
					master.setId(rs.getInt(1));
					master.setName(rs.getString(2));
					list.add(master);
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			return list;
		}

		public boolean checkIsRepeatTemplate(String templateId) {
			PreparedStatement preparedStatement = null;
			boolean result = false;
			String sql ="select * from apm_assment_template_field where templateid = "+templateId+" and rptvalue!=0 ";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				if(rs.next()){
					result = true;
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			return result;
		}

		public int alterNewColumn(String field) {
			
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "ALTER TABLE apm_repeat_form_field ADD COLUMN "+field+" LONGTEXT";
			try{
				preparedStatement = connection.prepareStatement(sql);
				result = preparedStatement.executeUpdate();
				
			}catch(Exception e){
				e.printStackTrace();
			}
			return result;
		}

		public int saveRepeatFormData(String strtxt, String column,
				String templateId,String clientid,String diaryuserid,String conditionid,int fieldid) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "insert into apm_repeat_form_field("+column+",templateid,clientid,diaryuserid,conditionid,client_assesment_fieldid) values(?,?,?,?,?,?) ";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, strtxt);
				preparedStatement.setString(2, templateId);
				preparedStatement.setString(3, clientid);
				preparedStatement.setString(4, diaryuserid);
				preparedStatement.setString(5, conditionid);
				preparedStatement.setInt(6, fieldid);
				
				result = preparedStatement.executeUpdate();
				
			}catch(Exception e){
				e.printStackTrace();
			}
			return 0;
		}

		public ArrayList<Assessment> getRepeatFormData(Template template,
				String columnname,String clientassesmentfieldid) {
			String column =  DateTimeUtils.removeAllSpecialChar(columnname);
			column = column.replace(" ", "");
			
			PreparedStatement preparedStatement = null;
			ArrayList<Assessment>list = new ArrayList<Assessment>();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT "+column+",id FROM apm_repeat_form_field ");
			sql.append("where clientid = "+template.getClientid()+" and diaryuserid="+template.getDiaryuserid()+" and conditionid = "+template.getConditionid()+" and templateid="+template.getTemplateid()+" ");
			sql.append("and client_assesment_fieldid ="+clientassesmentfieldid+" and "+column+" is not null ");
			
			/*boolean clientassesmentfieldidExist = clientassesmentfieldidExist(sql.toString());
			
			if(!clientassesmentfieldidExist){
				sql = new StringBuffer();
				sql.append("SELECT "+column+",id FROM apm_repeat_form_field ");
				sql.append("where clientid = "+template.getClientid()+" and diaryuserid="+template.getDiaryuserid()+" and conditionid = "+template.getConditionid()+" and templateid="+template.getTemplateid()+" ");
				sql.append("and "+column+" is not null ");
			}
			*/
			
			try{
				preparedStatement = connection.prepareStatement(sql.toString());
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					Assessment assessment = new Assessment();
					assessment.setFiledValue(rs.getString(1));
					assessment.setId(rs.getInt(2));
					
					list.add(assessment);
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
//			System.out.println(template.getDiaryuserid());
			// TODO Auto-generated method stub
			return list;
		}

		private boolean clientassesmentfieldidExist(String sql) {
			PreparedStatement preparedStatement = null;
			boolean result = false;
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				if(rs.next()){
					result = true;
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			return result;
		}

		public int updateRepeatFormData(String strtxt, int id,String column,String clientid,String diaryuserid,String conditionid) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "update apm_repeat_form_field set clientid = ? , diaryuserid=? , conditionid = ? , "+column+"=? where id = "+id+" ";
			
			try{
				
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, clientid);
				preparedStatement.setString(2, diaryuserid);
				preparedStatement.setString(3, conditionid);
				preparedStatement.setString(4, strtxt);
				
				result = preparedStatement.executeUpdate();
			}catch(Exception e){
				e.printStackTrace();
			}
			return result;
		}

		public int saveIdOfTemplate(String templateId,String clientId, String imagedata,String diaryUserId, String conditionId,String date,String ipdid,String oldpractid,String mrdid) {
			PreparedStatement preparedStatement = null;
			int result = 0,id = 0;
			String sql = "insert into apm_assessment_client_details(templateId,templateName,clientId,imagedata,practitionerId,conditionId,lastmodified,ipdid,oldpractid,mrdid) values(?,?,?,?,?,?,?,?,?,?)";
			try{
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, templateId);
				String templateName = getTemplateName(templateId);
				preparedStatement.setString(2, templateName);
				preparedStatement.setString(3, clientId);
				preparedStatement.setString(4, imagedata);
				preparedStatement.setString(5, diaryUserId);
				preparedStatement.setString(6, conditionId);
				preparedStatement.setString(7, date);
				preparedStatement.setString(8, ipdid);
				preparedStatement.setString(9, oldpractid);
				preparedStatement.setString(10, mrdid);
				result = preparedStatement.executeUpdate();
				
				if(result == 1){
					ResultSet rs = preparedStatement.getGeneratedKeys();
					if(rs.next()){
						id = rs.getInt(1);
					}
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
			return id;
		}

		public int getIpdBedno(String clientId) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "SELECT bedid FROM ipd_addmission_form where clientid = "+clientId+" order by id desc limit 0,1 ";
			
			try{
				
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				if(rs.next()){
					result = rs.getInt(1);
				}
				
			}catch (Exception e) {
				// TODO: handle exception
			}
			return result;
		}

		public String getAdmissionid(String clientId) {
			PreparedStatement preparedStatement = null;
			String result = "";
			String sql = "SELECT id FROM ipd_addmission_form where clientid = "+clientId+" order by id desc limit 0,1 ";
			
			try{
				
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				if(rs.next()){
					result = rs.getString(1);
				}
				
			}catch (Exception e) {
				// TODO: handle exception
			}
			return result;
		}

		public int sveHdrColumnData(String name, int valueId,String dbcname) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "insert into apm_assment_hdrcolumn(name, templateid, dbcname) values(?,?,?)";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, name);
				preparedStatement.setInt(2, valueId);
				preparedStatement.setString(3, dbcname);
				
				
				
				result = preparedStatement.executeUpdate();
				
				
			}catch (Exception e) {
				// TODO: handle exception
			}
				
			return result;
		}

		public ArrayList<Master> getLeftNameList(String templateId) {
			PreparedStatement preparedStatement = null;
			ArrayList<Master>list = new ArrayList<Master>();
			String sql = "select id,field_name,typeValue from apm_assment_template_field where templateid = "+templateId+" ";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					Master master = new Master();
					master.setId(rs.getInt(1));
					master.setName(rs.getString(2));
					master.setFieldtype(rs.getString(3));
					
					list.add(master);
				}
				
			}catch (Exception e) {
				// TODO: handle exception
			}
			return list;
		}

		public ArrayList<Master> getTopNameList(String templateId) {
			PreparedStatement preparedStatement = null;
			ArrayList<Master>list = new ArrayList<Master>();
			String sql = "select id,name,dbcname from apm_assment_hdrcolumn where templateid = "+templateId+" ";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					Master master = new Master();
					master.setId(rs.getInt(1));
					master.setName(rs.getString(2));
					master.setDisplayname(rs.getString(3));
					
					list.add(master);
				}
				
			}catch (Exception e) {
				// TODO: handle exception
			}
			return list;
		}

		public boolean checkColumnExist(String subjectname) {
			PreparedStatement preparedStatement = null;
			boolean result = false;
			try{
				DatabaseMetaData md = connection.getMetaData();
				ResultSet rs = md.getColumns(null, null, "apm_combine_assessment_client_details", subjectname);
				
				 if (rs.next()) {
				      result = true;
				    }
			}catch (Exception e) {
				// TODO: handle exception
			}
			
			return result;
		}

		public int createColumnName(String subjectname) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String myColumnType = "LONGTEXT";
		    String sql = "ALTER TABLE apm_combine_assessment_client_details ADD " + subjectname + " " + myColumnType;
		    
		    try{
		    	preparedStatement = connection.prepareStatement(sql);
		    	result = preparedStatement.executeUpdate();
		    }catch (Exception e) {
				// TODO: handle exception
			}
			return result;
		}
		

		public int saveIdOfCombineTemplate(String templateId, String clientId, String imageData, String diaryUserId,
				String conditionId, String date, String ipdid, String oldpractid,
				Object object,int lefynameid) {
			PreparedStatement preparedStatement = null;
			int result = 0,id = 0;
			String sql = "insert into apm_combine_assessment_client_details(templateId,templateName,clientId,imagedata,practitionerId,conditionId,lastmodified,ipdid,oldpractid,leftnameid) values(?,?,?,?,?,?,?,?,?,?)";
			try{
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, templateId);
				String templateName = getTemplateName(templateId);
				preparedStatement.setString(2, templateName);
				preparedStatement.setString(3, clientId);
				preparedStatement.setString(4, imageData);
				preparedStatement.setString(5, diaryUserId);
				preparedStatement.setString(6, conditionId);
				preparedStatement.setString(7, date);
				preparedStatement.setString(8, ipdid);
				preparedStatement.setString(9, oldpractid);
				preparedStatement.setInt(10, lefynameid);
				
				result = preparedStatement.executeUpdate();
				
				if(result == 1){
					ResultSet rs = preparedStatement.getGeneratedKeys();
					if(rs.next()){
						id = rs.getInt(1);
					}
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
			return id;
		}

		public int saveUpdateCombineTemplateDetails(int fieldId, String fieldValue, String textName,
				String datetime) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "update apm_combine_assessment_client_details set "+fieldValue+" = '"+textName+"',lastmodified='"+datetime+"' where id = "+fieldId+"";
			try{
				preparedStatement = connection.prepareStatement(sql);
				//preparedStatement.setString(1, textName);
				result = preparedStatement.executeUpdate();
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
			return result;
		}

		public boolean checkTermExist(int id, String templateId, String clientId, String diaryUserId,
				String conditionId,int leftnameid) {
			PreparedStatement preparedStatement = null;
			boolean result = false;
			String sql = "select * from apm_combine_assessment_client_details where templateId="+templateId+" and clientId = "+clientId+" "
					+ " and practitionerid = "+diaryUserId+" and conditionid = "+conditionId+" and leftnameid = "+leftnameid+" ";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				if(rs.next()){
					result = true;
				}
				
			}catch (Exception e) {
				// TODO: handle exception
			}
			
			
			return result;
		}

		public int getCombineDataid(int id, String templateId, String clientId, String diaryUserId, String conditionId,
				int leftnameid) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "select id from apm_combine_assessment_client_details where templateId="+templateId+" and clientId = "+clientId+" "
					+ " and practitionerid = "+diaryUserId+" and conditionid = "+conditionId+" and leftnameid = "+leftnameid+" ";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				if(rs.next()){
					result = rs.getInt(1);
				}
				
			}catch (Exception e) {
				// TODO: handle exception
			}
			
			
			return result;
		}

		public Assessment getCombineFormEditDetails(String id) {
			PreparedStatement preparedStatement = null;
			Assessment assessment = new Assessment();
			//String sql = "SELECT clientId,practitionerId,conditionId FROM apm_combine_assessment_client_details where id = "+id+" ";
			
	String sql = "SELECT clientId,practitionerId,conditionId,templateName,ipdid,oldpractid,templateid,imagedata FROM apm_combine_assessment_client_details where  id = "+id+"";
			
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
					assessment.setTemplateId(rs.getString(7));
					assessment.setImageData(rs.getString(8));
					
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			return assessment;
		}

		public int getRowCount() {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "SELECT rowcounts FROM apm_assessment_row where id = 1";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				if(rs.next()){
					result = rs.getInt(1);
				}
				
			}catch (Exception e) {
				// TODO: handle exception
			}
			
			
			return result;
		}

		

		

	
}
