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
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class TermsConditionMasterAction extends BaseAction implements Preparable,ModelDriven<MasterForm> {
	    MasterForm masterForm=new MasterForm();
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
					ArrayList<Master> masterlist = masterDAO.getMasterList(loginInfo);
					masterForm.setMasterlist(masterlist);
					mastername=(String)session.getAttribute("mastername");
					masterForm.setMastername(mastername);
					
					StateDAO stateDAO = new JDBCStateDAO(connection);
					ArrayList<State> statelist = stateDAO.getallState(null,null);
					masterForm.setStatelist(statelist);
					
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					connection.close();
				}
			}
			
			public MasterForm getModel() {
				return masterForm;
			}
			
			public String execute() throws Exception {
				Connection connection=null;
				try {
					connection = Connection_provider.getconnection();
					MasterDAO masterDAO=new JDBCMasterDAO(connection);
					
					String SearchText=masterForm.getSearchText();
					
					if(SearchText !=null)
					{
						if(SearchText.equals(""))
						{
							SearchText=null;
						}
					}
					
					int count=masterDAO.getTotalTermsCondition();
					pagination.setPreperties(count);
					
					ArrayList<Master> sectionlist = masterDAO.getTermsConditionList(pagination,SearchText);
					pagination.setPage_records(sectionlist.size());
					
					
					masterForm.setTermConditionList(sectionlist);
					masterForm.setTotalRecords(count);
					masterForm.setPagerecords(String.valueOf(pagination.getPage_records()));
					mastername=request.getParameter("selectedid");
					
					if(mastername!=null){
						
						 session.setAttribute("mastername", mastername);
						
					} else {
						
						mastername=(String)session.getAttribute("mastername");
					}
					masterForm.setMastername(mastername);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				finally{
					connection.close();
				}
				return "listTermsCondition";
			}
			
			
			
			public String add() throws Exception{
				 
				 return "addTermsCondition";
			 }
			
			
			 public String edit() throws Exception {
				 
				 Connection connection=null;
				 try {
						connection = Connection_provider.getconnection();
					MasterDAO masterDAO= new JDBCMasterDAO(connection);
					Master masterOBJ=new Master();
					masterOBJ.setId(masterForm.getId());
					Master termCond=masterDAO.getTermsConditionListById(masterOBJ);
					masterForm.setId(termCond.getId());
					masterForm.setName(termCond.getName());
					masterForm.setWorkorderterms(termCond.getWorkorderterms());
					 
				} catch (Exception e) {
					e.printStackTrace();
				}
				 
				 return "editTermsCondition";
			 }

			 public String save() throws Exception{
				 Connection connection=null;
				 try {
					  connection = Connection_provider.getconnection();
					  MasterDAO masterDAO= new JDBCMasterDAO(connection);
					  
					  String name=masterForm.getName();
					  
					  boolean flag=masterDAO.checkTermsandCondtion(name);
					  
					  Master msterOBJ=new Master();
					  msterOBJ.setId(masterForm.getId());
					  msterOBJ.setName(masterForm.getName());
					  msterOBJ.setWorkorderterms(masterForm.getWorkorderterms());
					  if(!flag==true){
						  int result=masterDAO.addTermsCondition(msterOBJ);
					  }
					 
				} catch (Exception e) {
					e.printStackTrace();
				}
			      finally{
					connection.close();
					}
				 
				 return "saveTermsCondition";
			 }

			 public String update() throws Exception{
				 Connection connection=null;
				 try {
					  connection = Connection_provider.getconnection();
					  MasterDAO masterDAO= new JDBCMasterDAO(connection);
					  Master masterOBJ=new Master();
					  String name=masterForm.getName();
					  
					  boolean flag=masterDAO.checkTermsandCondtion(name);
					  
					  masterOBJ.setId(masterForm.getId());
					  masterOBJ.setName(masterForm.getName());
					  masterOBJ.setWorkorderterms(masterForm.getWorkorderterms());
					  //if(!flag==true){
						  int result=masterDAO.updateTermsConditionlist(masterOBJ);
					  //}
					  
				} catch (Exception e) {
			     e.printStackTrace();
				}finally{
					connection.close();
				}
				 return "updateTermsCondition";
			 }
			 
			 
			 public String delete() throws Exception{
				 Connection connection=null;
				 try {
					 connection = Connection_provider.getconnection();
					  MasterDAO insSecDAO= new JDBCMasterDAO(connection);
					  Master masterOBJ=new Master();
					  masterOBJ.setId(masterForm.getId());
					
					  int result=insSecDAO.deleteTermsCondition(masterOBJ);
					
				} catch (Exception e) {
			      e.printStackTrace();
				}
				finally{
					connection.close();
				}
				 return "deleteTermsCondition";
			 }
			 
			 
}
