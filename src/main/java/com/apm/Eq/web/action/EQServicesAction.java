package com.apm.Eq.web.action;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Appointment.web.form.BranchForm;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.Encryption;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class EQServicesAction extends BaseAction implements ModelDriven<BranchForm> {


	BranchForm branchForm=new BranchForm();
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	
	public BranchForm getModel() {
		
		return branchForm;
	}

	@Override
	public String execute() throws Exception {

		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			ClientDAO clientDAO=new JDBCClientDAO(connection);
			UserProfileDAO profileDAO=new JDBCUserProfileDAO(connection);
			String email=request.getParameter("email");
			String name=request.getParameter("name"); 
			String mobile=request.getParameter("mobile");
			String lastname=request.getParameter("lastname");
			int res=profileDAO.getIdIfEmailExist(email);
			
			if(res>0){
			    Client client=clientDAO.getClientDetails(String.valueOf(res));
			    
				LoginInfo loginInfo = new LoginInfo();
				loginInfo.setId(1);
				loginInfo.setLoginType("pc");
				loginInfo.setCountry("India");
				loginInfo.setFirstName(client.getFirstName());
				loginInfo.setLastName(client.getLastName());
				loginInfo.setUserType(4);
				session.setAttribute("logininfo", loginInfo);
				LoginHelper.saveLoginInfo(request, loginInfo);		
				
			} else {
				
					Client client=new Client();
					client.setFirstName(client.getFirstName());
					client.setLastName(client.getLastName());
					client.setTitle(client.getTitle());
					client.setEmail(client.getEmail());
					client.setMobNo(client.getMobNo());
					client.setReference(client.getReference());
					client.setAddress(client.getAddress());
					
					int clientid=clientDAO.saveNewPatient(client, 1);
					LoginInfo loginInfo = new LoginInfo();
					loginInfo.setId(1);
					loginInfo.setLoginType("pc");
					loginInfo.setCountry("London");
					loginInfo.setFirstName(client.getFirstName());
					loginInfo.setLastName(client.getLastName());
					loginInfo.setUserType(4);
					
					session.setAttribute("logininfo", loginInfo);
					LoginHelper.saveLoginInfo(request, loginInfo);		
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		finally{
			
			connection.close();
		}
		return super.execute();
	}
		
	
	
	
	
}
