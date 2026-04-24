package com.apm.AssesmentForms.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.apm.AssesmentForms.eu.bi.ImportImageForAssessmentDAO;
import com.apm.AssesmentForms.eu.bi.ListAssessmentDAO;
import com.apm.AssesmentForms.eu.entity.Assessment;
import com.apm.common.eu.blogic.jdbc.JDBCBaseDAO;

public class JDBCImportImageAssessmentDAO extends JDBCBaseDAO implements ImportImageForAssessmentDAO{
		
		public JDBCImportImageAssessmentDAO(Connection connection){
			this.connection = connection;
			
		}

		
		public ArrayList<Assessment> getImportImageList() {
			PreparedStatement preparedStatement = null;
			ArrayList<Assessment>list = new ArrayList<Assessment>();
			String sql = "SELECT id,image_name,file_path,filename FROM apm_assessment_images";
			
			try{
				preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					
					Assessment assessment = new Assessment();					
					assessment.setId(rs.getInt(1));
					assessment.setImagename(rs.getString(2));
					assessment.setFilepath(rs.getString(3));
					assessment.setFilename(rs.getString(4));
					
					
					
					
					
					list.add(assessment);
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			return list;
		}


		
		public int insertImageData(String imagename, String filepath,String filename) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "insert into apm_assessment_images(image_name,file_path,filename) values (?,?,?) ";
			try{
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, imagename);
				preparedStatement.setString(2, filepath);
				preparedStatement.setString(3, filename);
				result = preparedStatement.executeUpdate();
				
			}catch(Exception e){
				e.printStackTrace();
			}
			return result;
		}


		
		public int deleteImage(String id) {
			PreparedStatement preparedStatement = null;
			int result = 0;
			String sql = "delete from apm_assessment_images where id = "+id+"";
			try{
				preparedStatement = connection.prepareStatement(sql);			
				result = preparedStatement.executeUpdate();			
				
			}catch(Exception e){
				e.printStackTrace();
			}
			return result;
		}
}