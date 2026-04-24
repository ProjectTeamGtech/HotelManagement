package com.apm.Tools.web.action;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.apm.Tools.eu.bi.EmailTemplateDAO;
import com.apm.Tools.eu.blogic.jdbc.JDBCEmailTemplateDAO;
import com.apm.Tools.eu.entity.EmailTemplate;
import com.apm.Tools.web.form.EmailTemplateForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.mysql.jdbc.Connection;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class LogoAction extends BaseAction implements Preparable, ModelDriven<EmailTemplateForm>{

	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	EmailTemplateForm emailTemplateForm = new EmailTemplateForm();
	Connection connection = null;
	//private File userImage;	
	/*private String userImageContentType;	
	private String userImageFileName;
	*/
	
	private Pagination pagination = new Pagination(10, 1);
	
	
	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	
	public String execute()throws Exception{		
		if(!verifyLogin(request)){
			return "login";
		}
		
		try{
			connection = (Connection) Connection_provider.getconnection();
			EmailTemplateDAO emailTemplateDAO = new JDBCEmailTemplateDAO(connection);
			
			int totalCount = emailTemplateDAO.getTotalLogoCount();
			pagination.setPreperties(totalCount);
			
			ArrayList<EmailTemplate> logoList = emailTemplateDAO.getLogoList(pagination);
			pagination.setPage_records(logoList.size());
			emailTemplateForm.setPagerecords(Integer.toString(pagination.getPage_records()));
			emailTemplateForm.setTotalRecords(totalCount);
			emailTemplateForm.setLogoList(logoList);
			session.setAttribute("logoList", logoList);
			session.setAttribute("pagination", pagination);			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		return SUCCESS;
	}
	
	public String add()throws Exception{		
		
		return "addLogoPage";
	}
	
	public String save()throws Exception{
		try{
			
			String filePath = request.getRealPath("/image/");
			File userImage = emailTemplateForm.getUserImage();
			String userImageFileName = emailTemplateForm.getUserImageFileName();
			String userImageContentType = emailTemplateForm.getUserImageContentType();
//			System.out.println(userImage);
//			System.out.println("Server path:" + filePath);
			File fileToCreate = new File(filePath, emailTemplateForm.getUserImageFileName());
			FileUtils.copyFile(userImage, fileToCreate);
//			System.out.println(emailTemplateForm.getUserImageFileName());
		
		
		connection = (Connection) Connection_provider.getconnection();
		//String filePath = request.getRealPath("/image/");
	       
	
		EmailTemplateDAO emailTemplateDAO = new JDBCEmailTemplateDAO(connection);
		int result = emailTemplateDAO.saveLogo(emailTemplateForm.getUserImageFileName());
		
		setFormData();
		
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return SUCCESS;
	}
	
	public String edit()throws Exception{
		
		Connection connection = null;
		int id = Integer.parseInt(request.getParameter("selectedid"));
		EmailTemplate emailTemplate = new EmailTemplate();
		try{
			connection = (Connection) Connection_provider.getconnection();
			EmailTemplateDAO emailTemplateDAO = new JDBCEmailTemplateDAO(connection);
			emailTemplate = emailTemplateDAO.getLogoDetails(id);
			emailTemplateForm.setId(emailTemplate.getId());
			emailTemplateForm.setUserImageFileName(emailTemplate.getUserImageFileName());
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return "editLogo";
	}
	
	public String update()throws Exception{
		Connection connection = null;
		try{			
			String filePath = request.getRealPath("/image/");
			File userImage = emailTemplateForm.getUserImage();
			String userImageFileName = emailTemplateForm.getUserImageFileName();
			String userImageContentType = emailTemplateForm.getUserImageContentType();
//			System.out.println(userImage);
//			System.out.println("Server path:" + filePath);
			File fileToCreate = new File(filePath, emailTemplateForm.getUserImageFileName());
			FileUtils.copyFile(userImage, fileToCreate);
//			System.out.println(emailTemplateForm.getUserImageFileName());
			
			EmailTemplate emailTemplate = new EmailTemplate();
			emailTemplate.setId(emailTemplateForm.getId());
			emailTemplate.setUserImageFileName(userImageFileName);
		
			connection = (Connection) Connection_provider.getconnection();
			//String filePath = request.getRealPath("/image/");       
	
			EmailTemplateDAO emailTemplateDAO = new JDBCEmailTemplateDAO(connection);
			int result = emailTemplateDAO.updateLogo(emailTemplate);
		
			setFormData();
		
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		return SUCCESS;
	}
	
	public String delete()throws Exception{
		Connection connection = null;
		try{
			connection = (Connection) Connection_provider.getconnection();
			EmailTemplateDAO emailTemplateDAO = new JDBCEmailTemplateDAO(connection);
			int id = Integer.parseInt(request.getParameter("selectedid"));
			int result = emailTemplateDAO.deleteLogo(id);
			setFormData();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return SUCCESS;
	}
	
	private void setFormData() throws Exception {
		try{
			connection = (Connection) Connection_provider.getconnection();
			EmailTemplateDAO emailTemplateDAO = new JDBCEmailTemplateDAO(connection);
			
			int totalCount = emailTemplateDAO.getTotalLogoCount();
			pagination.setPreperties(totalCount);
			
			ArrayList<EmailTemplate> logoList = emailTemplateDAO.getLogoList(pagination);
			pagination.setPage_records(logoList.size());
			emailTemplateForm.setPagerecords(Integer.toString(pagination.getPage_records()));
			emailTemplateForm.setTotalRecords(totalCount);
			emailTemplateForm.setLogoList(logoList);
			session.setAttribute("logoList", logoList);
			session.setAttribute("pagination", pagination);			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
	}

	public EmailTemplateForm getModel() {
		// TODO Auto-generated method stub
		return emailTemplateForm;
	}

	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}
	/*public File getUserImage() {
		return userImage;
	}

	public void setUserImage(File userImage) {
		this.userImage = userImage;
	}

	public String getUserImageContentType() {
		return userImageContentType;
	}

	public void setUserImageContentType(String userImageContentType) {
		this.userImageContentType = userImageContentType;
	}

	public String getUserImageFileName() {
		return userImageFileName;
	}

	public void setUserImageFileName(String userImageFileName) {
		this.userImageFileName = userImageFileName;
	}*/
}
