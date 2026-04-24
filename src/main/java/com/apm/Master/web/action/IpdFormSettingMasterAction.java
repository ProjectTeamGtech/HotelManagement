package com.apm.Master.web.action;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.web.form.MasterForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class IpdFormSettingMasterAction extends BaseAction implements ModelDriven<MasterForm> ,Preparable{

	MasterForm masterForm= new MasterForm();
	Pagination pagination= new Pagination(10,1);
	
	HttpServletRequest request= ServletActionContext.getRequest();
	HttpServletResponse response= ServletActionContext.getResponse();
	HttpSession session= request.getSession(false);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	private String mastername;
	public MasterForm getModel() {
		
		return masterForm;
	}

    public String execute() throws Exception {

    	Connection connection=null;
    	try {
    		connection=Connection_provider.getconnection();
    		MasterDAO masterDAO=new JDBCMasterDAO(connection);
    		ArrayList<Master> ipdformsettingList= masterDAO.getAllIpdFormSettingList();
    		
			masterForm.setIpdformsettingList(ipdformsettingList);
			mastername = request.getParameter("selectedid");
			if (mastername != null) {
				session.setAttribute("mastername", mastername);
			} else {

				mastername = (String) session.getAttribute("mastername");
			}
			masterForm.setMastername(mastername);
		} catch (Exception e) {

			e.printStackTrace();
		}
		finally {
			connection.close();
		}
    	return super.execute();
    }	
	
    public String add() {
    	return "add";
    }
	
    
    public String save() throws Exception {
    	
    	Connection connection=null;
    	try {
    		connection=Connection_provider.getconnection();
    		MasterDAO masterDAO=new JDBCMasterDAO(connection);
    		
    		String specialization= masterForm.getSpecialization();
    		String fields =masterForm.getFields();
    		
    		for(String str: fields.split(",")){
    			 
				 if(str.equals("0")) {
					 continue;
				 }
				 String name= masterDAO.getIpdFormFieldName(str);
    			 int m= masterDAO.saveIpdFormData(specialization, name);	
    		}
    		
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			 connection.close();
		}
    	
    	return "save";
    }
    
	
    public String edit() {
    	
    	Connection connection=null;
    	try {
    		connection=Connection_provider.getconnection();
    		MasterDAO masterDAO=new JDBCMasterDAO(connection);
			String dept_id= request.getParameter("selectedid");
    		
			ArrayList<String> list= masterDAO.getIpdFormSettingFields(dept_id);
			ArrayList<Master> ipdformfieldList= masterDAO.getIpdFormFiledList();
			
			ArrayList<Master> listNew= new ArrayList<Master>();
			
			for(Master master: ipdformfieldList){
				
				 for(String str: list){
					 
					  if(str.equals(master.getName())){
						   master.setStatus(1);
						   break;
					  }
				 }
				 listNew.add(master);
			}
			
			masterForm.setSpecialization(dept_id);
			masterForm.setIpdformfieldList(listNew);
    		
		} catch (Exception e) {

			e.printStackTrace();
		}
    	  
    	
    	return "edit";
    }
    
public String update() throws Exception {
    	
    	Connection connection=null;
    	try {
    		connection=Connection_provider.getconnection();
    		MasterDAO masterDAO=new JDBCMasterDAO(connection);
    		
    		String specialization= masterForm.getSpecialization();
    		String fields =masterForm.getFields();
    		
    		int r=masterDAO.deletePreviousDeptFields(specialization);
    		
    		for(String str: fields.split(",")){
   			 
				 if(str.equals("0")) {
					 continue;
				 }
				 String name= masterDAO.getIpdFormFieldName(str);
				 int m= masterDAO.saveIpdFormData(specialization, name);	
   		  }
    		
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			 connection.close();
		}
    	
    	return "save";
    }
	
	
	public void prepare() throws Exception {
		
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			MasterDAO masterDAO=new JDBCMasterDAO(connection);
			ArrayList<Master> ipdformfieldList= masterDAO.getIpdFormFiledList();
			masterForm.setIpdformfieldList(ipdformfieldList);
			ArrayList<Master> specializationList = masterDAO.getSpecializationList();
			masterForm.setSpecializationList(specializationList);
			
			ArrayList<Master> masterlist = masterDAO.getMasterList(loginInfo);
			masterForm.setMasterlist(masterlist);
			
			mastername = (String) session.getAttribute("mastername");
			masterForm.setMastername(mastername);
			
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	
}
