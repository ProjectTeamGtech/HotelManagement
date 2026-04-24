package com.apm.Master.web.action;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.web.form.MasterForm;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.ThirdParties.eu.bi.ThirdPartyDAO;
import com.apm.ThirdParties.eu.blogic.jdbc.JDBCThirdPartyDAO;
import com.apm.ThirdParties.eu.entity.ThirdParty;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.web.action.BaseAction;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class TPFollowerChargesAction extends BaseAction implements ModelDriven<MasterForm> ,Preparable{

	MasterForm masterForm=new MasterForm();
	HttpServletRequest request=ServletActionContext.getRequest();
	HttpServletResponse response=ServletActionContext.getResponse();
	
	
	
	public MasterForm getModel() {
		// TODO Auto-generated method stub
		return masterForm;
	}

	
	
	public String execute() throws Exception {

		Connection connection=null;
		try {
			
			connection=Connection_provider.getconnection();
			ThirdPartyDAO thirdPartyDAO=new JDBCThirdPartyDAO(connection); 
			
			ArrayList<ThirdParty>  tpmainList=thirdPartyDAO.getMainTPList();
			masterForm.setTpmainList(tpmainList);
			
			ArrayList<ThirdParty> tpsubList=thirdPartyDAO.getSubTpList();
			masterForm.setTpsubList(tpsubList);
			
			
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		return super.execute();
	}
	
	
	
public String get() throws Exception{
		
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			String maintp=request.getParameter("maintp");
			ThirdPartyDAO thirdPartyDAO=new JDBCThirdPartyDAO(connection);
			
			ArrayList<ThirdParty> allsubTpList=thirdPartyDAO.getSubTpList();
			
			ArrayList<ThirdParty> selectedTPlist=thirdPartyDAO.getSelectedSubTPList(maintp);   
			
			StringBuffer buffer=new StringBuffer(); 
			
			buffer.append("<div class='col-lg-12 col-md-12 col-sm-12' style='padding:0px;border-top: 1px solid #ddd;padding-top: 2px;'>");
			buffer.append("<div class='col-lg-8 col-md-8 col-sm-8' style='padding:0px;'>");
			buffer.append("<div class='form-group'>");
			buffer.append("<input type='checkbox' onclick='checkAll()' > Select All");
			buffer.append("</div></div></div>");
			
			for(ThirdParty thirdParty:allsubTpList){
				
				boolean isselected=false;
				
				for(ThirdParty tp:selectedTPlist){
					   if(thirdParty.getId()==tp.getId()){
						   isselected=true;
						   break;
					   }
				}
				
				buffer.append("<div class='col-lg-12 col-md-12 col-sm-12' style='padding:0px;border-top: 1px solid #ddd;padding-top: 2px;'>");
				buffer.append("<div class='col-lg-8 col-md-8 col-sm-8' style='padding:0px;'>");
				buffer.append("<div class='form-group'>");
				
				if(isselected){
					buffer.append("<input class='case' type='checkbox' checked='checked'   id='ch"+thirdParty.getId()+"' name='ch"+thirdParty.getId()+"' value='"+thirdParty.getId()+"'>");
				} else {
					buffer.append("<input class='case' type='checkbox'   id='ch"+thirdParty.getId()+"' name='ch"+thirdParty.getId()+"' value='"+thirdParty.getId()+"'>");
				}
				buffer.append(""+thirdParty.getCompanyName()+"");	
				
				buffer.append("</div></div></div>");
				
			}
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());

		} catch (Exception e) {

			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		return null;
	}
	
	
	public String saveAll() throws Exception{ 
		

		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			ThirdPartyDAO thirdPartyDAO=new JDBCThirdPartyDAO(connection);
			
			String name=masterForm.getName();
			int tpid=masterForm.getTpid();
			
			for(String str:name.split(",")){
				
				   if(str.equals("0")){
					   continue;
				   }
				
				  int d=Integer.parseInt(str);
				  int result=thirdPartyDAO.updateFollowerTp(d,tpid);
				   
			}
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		return "save";
	}


	
	
	
	
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	} 
	
	
	

}
