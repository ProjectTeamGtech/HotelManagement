package com.apm.AssesmentForms.web.action;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.apm.AssesmentForms.eu.bi.ImportImageForAssessmentDAO;
import com.apm.AssesmentForms.eu.bi.ListAssessmentDAO;
import com.apm.AssesmentForms.eu.blogic.jdbc.JDBCImportImageAssessmentDAO;
import com.apm.AssesmentForms.eu.blogic.jdbc.JDBCListAssessmentFormDAO;
import com.apm.AssesmentForms.eu.entity.Assessment;
import com.apm.AssesmentForms.web.form.AssessmentForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class ImportImageForAssessmentAction extends BaseAction implements Preparable, ModelDriven<AssessmentForm>{

	AssessmentForm assessmentForm = new AssessmentForm();
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	private Pagination pagination = new Pagination(10, 1);
	
	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	
	
	
	public String execute() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			ImportImageForAssessmentDAO imageForAssessmentDAO = new JDBCImportImageAssessmentDAO(connection);
			ArrayList<Assessment> importImageList = imageForAssessmentDAO.getImportImageList(); 
			assessmentForm.setImportImageList(importImageList);
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		
		
		
		return "viewImagesListPage";
	}

	public String input(){
		if(!verifyLogin(request)){
			return "login";
		}
		
		return "addImagePage";
	}
	
	public String add() throws Exception{
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			ImportImageForAssessmentDAO imageForAssessmentDAO = new JDBCImportImageAssessmentDAO(connection);
		 if(assessmentForm.getFileUploadFileName()!=null ){
		       
        	 String[] attachFiles = new String[assessmentForm.getFileUpload().length];
		
		 // copy the uploaded files into pre-configured location
        for (int i = 0; i < assessmentForm.getFileUpload().length; i++) {
            File uploadedFile = assessmentForm.getFileUpload()[i];
            String fileName = assessmentForm.getFileUploadFileName()[i];
            
            //APM file path
        	/*String workingDirectory = System.getProperty("user.dir");
			String absoluteFilePath = workingDirectory + File.separator + loginInfo.getUserId() + File.separator + "Chart" + fileName;*/
				
			String filePath = request.getRealPath("/liveData/importAssessmentImage/");
			File destFile = new File(filePath + File.separator + fileName);
//            System.out.println("Server path:" + filePath);
            try {
                FileUtils.copyFile(uploadedFile, destFile);
            } catch (IOException ex) {
//                System.out.println("Could not copy file " + fileName);
                ex.printStackTrace();
            }
        }
        
        for (int i = 0; i < assessmentForm.getFileUpload().length; i++) {
			String imagename = assessmentForm.getImagename()[i];
			String filename = assessmentForm.getFileUploadFileName()[i];
			String filepath = "liveData/importAssessmentImage/"+filename+"";
			
			//APM file path
        	/*String workingDirectory = System.getProperty("user.dir");
			String absoluteFilePath = workingDirectory + File.separator + loginInfo.getUserId() + File.separator + "Chart/" + filename + "/" + filename;*/
			int result = imageForAssessmentDAO.insertImageData(imagename,filepath,filename); 
		}
		 }
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		return "addPageSucess";
	}
	
	public String delete() throws Exception{
		String id = request.getParameter("selectedid");
		String filename = request.getParameter("filename");
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			ImportImageForAssessmentDAO imageForAssessmentDAO = new JDBCImportImageAssessmentDAO(connection);
			int delete = imageForAssessmentDAO.deleteImage(id);
			
			String filePath = request.getRealPath("/liveData/importAssessmentImage/");
			File file = new File(filePath+"/"+filename);
			file.delete();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		return "addPageSucess";
	}
	
	public AssessmentForm getModel() {
		// TODO Auto-generated method stub
		return assessmentForm;
	}

	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}
}