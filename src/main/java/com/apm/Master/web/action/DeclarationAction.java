package com.apm.Master.web.action;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.Master.eu.bi.DeclarationDAO;
import com.apm.Master.eu.bi.DischargeOutcomeDAO;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCDeclarationDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCDischargeOutcomeDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.entity.Declaration;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.web.form.DeclarationForm;
import com.apm.Master.web.form.MasterForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class DeclarationAction extends BaseAction implements Preparable, ModelDriven<DeclarationForm>{
	
	DeclarationForm declarationForm = new DeclarationForm();
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	private Pagination pagination = new Pagination(20, 1);
	private String mastername;
	public Pagination getPagination() {
		return pagination;
	}


	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public String execute(){
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			DeclarationDAO declarationDAO = new JDBCDeclarationDAO(connection);
			
			String searchText = declarationForm.getSearchText();
			if(searchText!=null){
				if(searchText.equals("")){
					searchText=null;
				}
			}
			
			
			int totalCount = declarationDAO.getTotalDeclarationCount(searchText);
			pagination.setPreperties(totalCount);
			
			ArrayList<Declaration>declarationList = declarationDAO.getDeclarationList(pagination,searchText);
			pagination.setPage_records(declarationList.size());
			declarationForm.setTotalRecords(totalCount);
			declarationForm.setPagerecords(Integer.toString(pagination.getPage_records()));
			declarationForm.setDeclarationList(declarationList);
			ArrayList<Master> masterlist=declarationDAO.getMasterList(loginInfo);
			declarationForm.setMasterlist(masterlist);
			session.setAttribute("declarationList", declarationList);
			session.setAttribute("pagination", pagination);
			mastername = request.getParameter("selectedid");
			if (mastername != null) {

				session.setAttribute("mastername", mastername);
			} else {
				mastername = (String) session.getAttribute("mastername");
			}
			declarationForm.setMastername(mastername);
		}
		catch (Exception e) {
			
		}
		return "declarationList";
	}
	
	public String dec(){
		
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			String id = request.getParameter("id");
			
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			int res = clientDAO.updateAllDec();
			 res = clientDAO.updateDec(id);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}

	public String add(){
		if(!verifyLogin(request)){
			return "login";
		}
		
		return "addDeclarationPage";
	}
	
	public String save(){
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		int result = 0;
		try{
			connection = Connection_provider.getconnection();
			Declaration declaration = new Declaration();
			DeclarationDAO declarationDAO = new JDBCDeclarationDAO(connection);
			declaration.setTitle(declarationForm.getTitle());
			declaration.setDeclarationNotes(request.getParameter("declarationNotes"));
			
			result = declarationDAO.saveDeclaration(declaration,loginInfo.getClinicid());
			declarationForm.setMastername(mastername);
			declarationForm.setMessage("Declaration Added Sucessfully!!");
			addActionMessage("Declaration Added Sucessfully!!");
			setFormDataofDeclaration();
		}
		catch (Exception e) {
			
		}
		
		return "declarationList";
	}
	public String back(){
		setFormDataofDeclaration();
		declarationForm.setMastername(mastername);
		return "declarationList";
	}
	
	
	public void setFormDataofDeclaration(){
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			
			DeclarationDAO declarationDAO = new JDBCDeclarationDAO(connection);
			pagination = (Pagination) session.getAttribute("pagination");

			int totalCount = declarationDAO.getTotalDeclarationCount(null);
			pagination.setPreperties(totalCount);
			
			ArrayList<Declaration>declarationList = declarationDAO.getDeclarationList(pagination,null);
			pagination.setPage_records(declarationList.size());
			declarationForm.setTotalRecords(totalCount);
			declarationForm.setPagerecords(Integer.toString(pagination.getPage_records()));
			declarationForm.setDeclarationList(declarationList);
			session.setAttribute("pagination", pagination);
			session.setAttribute("declarationList", declarationList);
		}
		catch(Exception e){
			
		}
	}
	public String edit(){
		if(!verifyLogin(request)){
			return "login";
		}
		int id =Integer.parseInt(request.getParameter("selectedid"));
		Connection connection = null;
		
		Declaration declaration = new Declaration();
		try{
			
			connection = Connection_provider.getconnection();
			DeclarationDAO declarationDAO = new JDBCDeclarationDAO(connection);
			
			declaration = declarationDAO.getDeclaration(id,declaration);
			declarationForm.setId(declaration.getId());
			declarationForm.setDeclarationNotes(declaration.getDeclarationNotes().toString());
			declarationForm.setTitle(declaration.getTitle());
			String declarationNotes = declaration.getDeclarationNotes().toString();
			session.setAttribute("declarationNotes", declarationNotes);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		return "editDeclarationPage";
	}
	public String update(){
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		int result = 0;
		try{
			int id = declarationForm.getId();
			connection = Connection_provider.getconnection();
			Declaration declaration = new Declaration();
			DeclarationDAO declarationDAO = new JDBCDeclarationDAO(connection);
			
			declaration.setDeclarationNotes(request.getParameter("declarationNotes").toString());
			declaration.setTitle(declarationForm.getTitle());
		
			result = declarationDAO.updateDeclaration(declaration,id);
			declarationForm.setMastername(mastername);
			declarationForm.setMessage("Declaration Updated Sucessfully!!");
			addActionMessage("Declaration Updated Sucessfully!!");
			setFormDataofDeclaration();
		}
		catch (Exception e) {
			
		}
		
		return "declarationList";
	}
	public String delete(){
		if(!verifyLogin(request)){
			return "login";
		}
		int result = 0;
		int id =Integer.parseInt(request.getParameter("selectedid"));
		Connection connection = null;
		Declaration declaration = new Declaration();
		
		try{
			
			connection = Connection_provider.getconnection();
			DeclarationDAO declarationDAO = new JDBCDeclarationDAO(connection);
			result = declarationDAO.deleteDeclaration(id,declaration);
			declarationForm.setMastername(mastername);
			declarationForm.setMessage("Declaration Deleted Sucessfully!!");
			addActionMessage("Declaration Deleted Sucessfully!!");
			setFormDataofDeclaration();
		}
		catch (Exception e) {
			
		}
		
		return "declarationList";
	}
	
	
	public String payrolldec() {
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			String id = request.getParameter("id");
			
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			int res = clientDAO.updateAllpayrolldec();
			 res = clientDAO.updatepayrollDec(id);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	public DeclarationForm getModel() {
		// TODO Auto-generated method stub
		return declarationForm;
	}


	public void prepare() throws Exception {
		// TODO Auto-generated method stub

		Connection con = null;

		try {
			con = Connection_provider.getconnection();
			DeclarationDAO declarationDAO = new JDBCDeclarationDAO(con);
			ArrayList<Master> masterlist = declarationDAO.getMasterList(loginInfo);
			declarationForm.setMasterlist(masterlist);
			mastername=(String)session.getAttribute("mastername");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
	}

	

}
