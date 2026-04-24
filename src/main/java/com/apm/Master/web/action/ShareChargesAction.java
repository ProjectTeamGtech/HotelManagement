package com.apm.Master.web.action;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Master.eu.bi.DischargeOutcomeDAO;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCDischargeOutcomeDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.web.form.MasterForm;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class ShareChargesAction extends BaseAction implements ModelDriven<MasterForm>,Preparable{

	MasterForm masterForm=new MasterForm();
	
	HttpServletRequest request=ServletActionContext.getRequest();
	HttpServletResponse response=ServletActionContext.getResponse();
	HttpSession session=request.getSession(false);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	
	public MasterForm getModel() {
		// TODO Auto-generated method stub
		return masterForm;
	}
	String mastername;
	
	
	
	public String execute() throws Exception {

		Connection connection=null;
		try {
			
			connection=Connection_provider.getconnection();
			DischargeOutcomeDAO outcomeDAO=new JDBCDischargeOutcomeDAO(connection);
			
			String searchText = masterForm.getSearchText();
			
			if(searchText!=null){
				if(searchText.equals("")){
					searchText=null;
				}
			}
			
			ArrayList<Master> newChargeTypeList = outcomeDAO.getNewChargeTypeList();
			masterForm.setNewChargeTypeList(newChargeTypeList);
			
			/*ArrayList<UserProfile> userProfileList=userProfileDAO.getAllUserProfileList();
			masterForm.setUserProfileList(userProfileList);
			*/
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
	
	
	
	public String get() throws Exception{
		
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
			MasterDAO masterDAO=new JDBCMasterDAO(connection);
			String chargeType=request.getParameter("chargetype");
			
			ArrayList<UserProfile> userProfileList=userProfileDAO.getAllUserProfileList();
			
			String selectedUsers=masterDAO.getSharedChargeUser(chargeType); 
			
			StringBuffer buffer=new StringBuffer(); 
			
			buffer.append("<div class='col-lg-12 col-md-12 col-sm-12' style='padding:0px;border-top: 1px solid #ddd;padding-top: 2px;'>");
			buffer.append("<div class='col-lg-8 col-md-8 col-sm-8' style='padding:0px;'>");
			buffer.append("<div class='form-group'>");
			buffer.append("<input type='checkbox' onclick='selectAll()' > Select All");
			buffer.append("</div></div></div>");
			
			for(UserProfile userProfile:userProfileList){
				
				boolean isselected=false;
				
				for(String str:selectedUsers.split(",")){
					
					if(str.equals("0") || str.equals("")){
						continue;
					}
					int iid=Integer.parseInt(str);
					
					   if(userProfile.getId()==iid){
						   isselected=true;
						   break;
					   }
				}
				
				buffer.append("<div class='col-lg-12 col-md-12 col-sm-12' style='padding:0px;border-top: 1px solid #ddd;padding-top: 2px;'>");
				buffer.append("<div class='col-lg-8 col-md-8 col-sm-8' style='padding:0px;'>");
				buffer.append("<div class='form-group'>");
				
				if(isselected){
					buffer.append("<input class='case' type='checkbox' checked='checked'   id='ch"+userProfile.getId()+"' name='ch"+userProfile.getId()+"' value='"+userProfile.getId()+"'>");
				} else {
					buffer.append("<input class='case' type='checkbox'   id='ch"+userProfile.getId()+"' name='ch"+userProfile.getId()+"' value='"+userProfile.getId()+"'>");
				}
				buffer.append(""+userProfile.getFullname()+"");	
				
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
	
	
	public String updateall() {
		
		Connection connection=null;
		try {
			
			connection=Connection_provider.getconnection();
			MasterDAO masterDAO=new JDBCMasterDAO(connection);
			String chargeType=request.getParameter("chargeType");
			String userids=request.getParameter("userids");
			
			int tid=masterDAO.getShareIdIfExists(chargeType);
			
			if(tid>0){
				
				int res=masterDAO.updateShareCharge(userids,chargeType,tid);
			} else {
				
				int res=masterDAO.saveShareCharge(userids, chargeType);
			}
			
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	
   
    public void prepare() throws Exception {
    	
    	Connection con = null;

		try {
			con = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(con);
			ArrayList<Master> masterlist = masterDAO.getMasterList(loginInfo);
			masterForm.setMasterlist(masterlist);
			mastername = (String) session.getAttribute("mastername");
			
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			con.close();
		}
    }
	
	
	
}
