package com.apm.Accounts.web.action;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Accounts.eu.bi.CashDeskDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCCashDeskDAO;
import com.apm.Accounts.eu.entity.CashDesk;
import com.apm.Accounts.web.form.CashDeskForm;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.Emr.eu.bi.EmrDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCEmrDAO;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class CashDeskAction extends BaseAction implements Preparable, ModelDriven<CashDeskForm>{

	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	
	CashDeskForm cashDeskForm = new CashDeskForm();
	Pagination pagination = new Pagination(10, 1);
	
	public Pagination getPagination(){
		return pagination;
	}
	public void setPagination(Pagination pagination){
		this.pagination = pagination;
	}
	
	public String execute() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			CashDeskDAO cashDeskDAO = new JDBCCashDeskDAO(connection);
			ArrayList<CashDesk> cashDeskList = new ArrayList<CashDesk>();
			
			int totalCount = cashDeskDAO.getTotalCashDeskCount();
			pagination.setPreperties(totalCount);
			
			cashDeskList = cashDeskDAO.getCashDeskList(pagination);
			
			pagination.setPage_records(cashDeskList.size());
			cashDeskForm.setTotalRecords(totalCount);
			cashDeskForm.setPagerecords(Integer.toString(pagination.getPage_records()));
			cashDeskForm.setCashDeskList(cashDeskList);
			//session.setAttribute("cashDeskList", cashDeskList);
			session.setAttribute("pagination", pagination);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		return "cashDeskList";
	}
	
	public String search() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		String searchClient = cashDeskForm.getSearchText();
		if(searchClient==null || searchClient ==""){
			searchClient = (String) session.getAttribute("searchClient");
		}
		Connection connection = null;
		ArrayList<CashDesk> cashDeskList = new ArrayList<CashDesk>();
		try{
			connection = Connection_provider.getconnection();
			CashDeskDAO cashDeskDAO = new JDBCCashDeskDAO(connection);
			int totalCount = cashDeskDAO.getTotalCashDeskCountOfSearch(loginInfo.getId(),searchClient);
			pagination.setPreperties(totalCount);
			
			cashDeskList = cashDeskDAO.getCashDeskListOfSearch(loginInfo.getId(),searchClient,pagination);
			
			pagination.setPage_records(cashDeskList.size());
			cashDeskForm.setTotalRecords(totalCount);
			cashDeskForm.setPagerecords(Integer.toString(pagination.getPage_records()));
			cashDeskForm.setCashDeskList(cashDeskList);
			session.setAttribute("searchClient", searchClient);
			session.setAttribute("cashDeskList", cashDeskList);
			session.setAttribute("pagination", pagination);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		return "cashDeskList";
	}
	
	public String add() throws Exception{
		
		return "addCashdeskPage";	
		
	}
	
	public String edit() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		int id = Integer.parseInt(request.getParameter("selectedid"));
		CashDesk cashDesk = new CashDesk();
		try{
			connection = Connection_provider.getconnection();
			CashDeskDAO cashDeskDAO = new JDBCCashDeskDAO(connection);
			cashDesk = cashDeskDAO.getCashDeskData(id);
			cashDeskForm.setId(cashDesk.getId());
			cashDeskForm.setClientName(cashDesk.getClientName());
			cashDeskForm.setAmount(cashDesk.getAmount());
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		
		return "editCashDesk";
	}
	
	public String update() throws Exception{

		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;		
		try{ 			
			connection = Connection_provider.getconnection();
			CashDeskDAO cashDeskDAO = new JDBCCashDeskDAO(connection);
			//String id = request.getParameter("id");
			int selectedid = Integer.parseInt(request.getParameter("id"));
			int amount = Integer.parseInt(request.getParameter("amount"));
			
			int result = cashDeskDAO.updateCashdesk(selectedid,amount);
			setFormData();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		return "cashDeskList";
	}
	
	public String delete() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		
		try{ 			
			connection = Connection_provider.getconnection();
			CashDeskDAO cashDeskDAO = new JDBCCashDeskDAO(connection);			
			int selectedid = Integer.parseInt(request.getParameter("selectedid"));
			
			int result = cashDeskDAO.deleteCashDesk(selectedid);
			setFormData();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		return "cashDeskList";
	}
	
	public String back() throws Exception{
		setFormData();
		return "cashDeskList";
	}
	
	public String save() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		int result=0,result1 = 0;
		int paymentAmount = 0;
		try{
			connection = Connection_provider.getconnection();
			CashDesk cashDesk = new CashDesk();
			CashDeskDAO cashDeskDAO = new JDBCCashDeskDAO(connection);
			int clientId = cashDeskForm.getClientId();	
			cashDesk.setClientId(clientId);
			cashDesk.setAmount(cashDeskForm.getAmount());
			
			boolean clientIdExist = cashDeskDAO.isClientIdExist(clientId);
			
			if(clientIdExist == true){
				
				result = cashDeskDAO.updateSaveCashdesk(cashDesk);				
			}			
			else{
			result = cashDeskDAO.saveCashDesk(cashDesk);			
			result1 = cashDeskDAO.saveCashDeskTransaction(clientId,paymentAmount);
			}
			
			setFormData();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		return "cashDeskList";
		
	}
	
	public String move() throws Exception{
		setFromDataSort();
		return "cashDeskList";
	}
	
	public void setFromDataSort() throws Exception{
		Connection connection = null;
		int totalCount =0;
		try{
			String searchClient = cashDeskForm.getSearchText();	
			if(searchClient == null){
				searchClient = "";
			}
			if(searchClient.equals(""))
			{
				searchClient = (String) session.getAttribute("searchClient");
				
				if(searchClient == null){
					searchClient = "";
				}
			}
		connection = Connection_provider.getconnection();
		CashDeskDAO cashDeskDAO = new JDBCCashDeskDAO(connection);
		ArrayList<CashDesk> cashDeskList = new ArrayList<CashDesk>();

		//pagination = (Pagination) session.getAttribute("pagination");		
		
		if(!searchClient.equals("")){
			 totalCount = cashDeskDAO.getTotalCashDeskCountOfSearch(loginInfo.getId(),searchClient);
			 pagination.setPreperties(totalCount);
			 cashDeskList = cashDeskDAO.getCashDeskListOfSearch(loginInfo.getId(),searchClient,pagination);
		}else{
			 totalCount = cashDeskDAO.getTotalCashDeskCount();
			 pagination.setPreperties(totalCount);
			 cashDeskList = cashDeskDAO.getCashDeskList(pagination);

		}		
		
		cashDeskForm.setSearchText(searchClient);
		
		pagination.setPage_records(cashDeskList.size());
		pagination.setPage_records(cashDeskList.size());
		cashDeskForm.setTotalRecords(totalCount);
		cashDeskForm.setPagerecords(Integer.toString(pagination.getPage_records()));
		cashDeskForm.setCashDeskList(cashDeskList);
		}
		catch(Exception e){
			e.printStackTrace();
			
		}finally{
			
			connection.close();
		}
	}
	
	public String setFormData() throws Exception{
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			CashDeskDAO cashDeskDAO = new JDBCCashDeskDAO(connection);
			ArrayList<CashDesk> cashDeskList = new ArrayList<CashDesk>();
			
			int totalCount = cashDeskDAO.getTotalCashDeskCount();
			pagination.setPreperties(totalCount);
			
			cashDeskList = cashDeskDAO.getCashDeskList(pagination);
			
			pagination.setPage_records(cashDeskList.size());
			cashDeskForm.setTotalRecords(totalCount);
			cashDeskForm.setPagerecords(Integer.toString(pagination.getPage_records()));
			cashDeskForm.setCashDeskList(cashDeskList);
			//session.setAttribute("cashDeskList", cashDeskList);
			session.setAttribute("pagination", pagination);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		return null;
	}
	
	public String getTotalAmount() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		int clientId = Integer.parseInt(request.getParameter("clientId"));
		int cashdeskAmt = 0;
		try{
			connection = Connection_provider.getconnection();
			CashDeskDAO cashDeskDAO = new JDBCCashDeskDAO(connection);
			
			cashdeskAmt = cashDeskDAO.getTransactionCashdeskAmount(clientId);
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+cashdeskAmt+""); 
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			connection.close();
		}
		return null;
	}
	public CashDeskForm getModel() {
		// TODO Auto-generated method stub
		return cashDeskForm;
	}

	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
