package com.apm.Master.web.action;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Master.eu.bi.CityMasterDAO;
import com.apm.Master.eu.bi.InvestigationSectionDAO;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.bi.StateDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCCityMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCInvestigationSectionDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCStateDAO;
import com.apm.Master.eu.entity.CityMaster;
import com.apm.Master.eu.entity.InvestigationSection;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.State;
import com.apm.Master.web.form.InvestigationSectionForm;
import com.apm.Master.web.form.MasterForm;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class InvestigationSectionMasterAction extends BaseAction implements Preparable,ModelDriven<InvestigationSectionForm> {
	
	    MasterForm masterForm=new MasterForm();
	    InvestigationSectionForm investigationSectionForm=new InvestigationSectionForm();
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		HttpSession session=request.getSession(false);
		LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
		Pagination pagination=new Pagination(10,1);
		String mastername;
		  public Pagination getPagination() {
				return pagination;
			}

			public void setPagination(Pagination pagination) {
				this.pagination = pagination;
			}
		
		public void prepare() throws Exception {
			Connection connection = null;
			
			try { 
				connection = Connection_provider.getconnection();
				MasterDAO masterDAO = new JDBCMasterDAO(connection);
				JDBCUserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
				ArrayList<Master> masterlist = masterDAO.getMasterList(loginInfo);
				investigationSectionForm.setMasterlist(masterlist);
				mastername=(String)session.getAttribute("mastername");
				investigationSectionForm.setMastername(mastername);
				ArrayList<String> jobTitleList = userProfileDAO.getJobTitleList();
				investigationSectionForm.setJobTitleList(jobTitleList);
				StateDAO stateDAO = new JDBCStateDAO(connection);
				ArrayList<State> statelist = stateDAO.getallState(null,null);
				investigationSectionForm.setStatelist(statelist);
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				connection.close();
			}
		}
		
		public InvestigationSectionForm getModel() {
			return investigationSectionForm;
		}
		
		public String execute() throws Exception {
			Connection connection=null;
			try {
				connection = Connection_provider.getconnection();
				InvestigationSectionDAO invsSectionMasterDAO=new JDBCInvestigationSectionDAO(connection);
				
				String SearchText=investigationSectionForm.getSearchText();
				
				if(SearchText !=null)
				{
					if(SearchText.equals(""))
					{
						SearchText=null;
					}
				}
				
				int count=invsSectionMasterDAO.getTotalInvsSectionCount();
				pagination.setPreperties(count);
				
				ArrayList<InvestigationSection> sectionlist = invsSectionMasterDAO.getInvestigationSectionList(pagination,SearchText);
				pagination.setPage_records(sectionlist.size());
				
				
				investigationSectionForm.setInvsSectionList(sectionlist);
				investigationSectionForm.setTotalRecords(String.valueOf(count));
				investigationSectionForm.setPagerecords(String.valueOf(pagination.getPage_records()));
				mastername=request.getParameter("selectedid");
				
				if(mastername!=null){
					
					 session.setAttribute("mastername", mastername);
					
				} else {
					
					mastername=(String)session.getAttribute("mastername");
				}
				investigationSectionForm.setMastername(mastername);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally{
				connection.close();
			}
			return "listInvestigationSection";
		}

 public String add() throws Exception{
	 
	 return "addInvestgationSection";
 }
 
 public String edit() throws Exception {
	 
	 Connection connection=null;
	 try {
			connection = Connection_provider.getconnection();
		InvestigationSectionDAO insSecDAO= new JDBCInvestigationSectionDAO(connection);
		InvestigationSection invOBJ=new InvestigationSection();
		invOBJ.setId(investigationSectionForm.getId());
		InvestigationSection invSec=insSecDAO.getAllInvestigationSectionListById(invOBJ);
		investigationSectionForm.setId(invSec.getId());
		investigationSectionForm.setName(invSec.getName());
		investigationSectionForm.setJobtitle(invSec.getJobtitle());
		 
	} catch (Exception e) {
		e.printStackTrace();
	}
	 
	 return "editInvestigationSection";
 }
 
 public String save() throws Exception{
	 Connection connection=null;
	 try {
		  connection = Connection_provider.getconnection();
		  InvestigationSectionDAO insSecDAO= new JDBCInvestigationSectionDAO(connection);
		  InvestigationSection invOBJ=new InvestigationSection();
		  invOBJ.setId(investigationSectionForm.getId());
		  invOBJ.setName(investigationSectionForm.getName());
		  invOBJ.setJobtitle(investigationSectionForm.getJobtitle());
		  int result=insSecDAO.addInvestigationSection(invOBJ);
		
		 
	} catch (Exception e) {
		e.printStackTrace();
	}
      finally{
		connection.close();
		}
	 
	 return "saveInvestigationSection";
 }
 
 public String update() throws Exception{
	 Connection connection=null;
	 try {
		  connection = Connection_provider.getconnection();
		  InvestigationSectionDAO insSecDAO= new JDBCInvestigationSectionDAO(connection);
		  InvestigationSection invOBJ=new InvestigationSection();
		  invOBJ.setId(investigationSectionForm.getId());
		  invOBJ.setName(investigationSectionForm.getName());
		  invOBJ.setJobtitle(investigationSectionForm.getJobtitle());
		  int result=insSecDAO.updateInvestigationSectionlist(invOBJ);
		
	} catch (Exception e) {
     e.printStackTrace();
	}finally{
		connection.close();
	}
	 return "updateInvestigationSection";
 }
 
 public String delete() throws Exception{
	 Connection connection=null;
	 try {
		 connection = Connection_provider.getconnection();
		  InvestigationSectionDAO insSecDAO= new JDBCInvestigationSectionDAO(connection);
		  InvestigationSection invOBJ=new InvestigationSection();
		  invOBJ.setId(investigationSectionForm.getId());
		
		  int result=insSecDAO.deleteInvestigationSection(invOBJ);
		
	} catch (Exception e) {
      e.printStackTrace();
	}
	finally{
		connection.close();
	}
	 return "deleteInvestigationSection";
 }
 
 
 
}
