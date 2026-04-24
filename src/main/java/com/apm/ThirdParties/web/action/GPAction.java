package com.apm.ThirdParties.web.action;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Accounts.eu.bi.CashDeskDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCCashDeskDAO;
import com.apm.Accounts.eu.entity.CashDesk;
import com.apm.Master.eu.bi.TreatmentTypeDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCTreatmentTypeDAO;
import com.apm.Master.eu.entity.TreatmentType;
import com.apm.ThirdParties.eu.bi.GPDAO;
import com.apm.ThirdParties.eu.blogic.jdbc.JDBCGPDAO;
import com.apm.ThirdParties.eu.entity.GP;
import com.apm.ThirdParties.web.form.GPForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class GPAction extends BaseAction implements Preparable, ModelDriven<GPForm>{
	
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	GPForm gpForm = new GPForm();

	private Pagination pagination = new Pagination(10, 1);
	
	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public String execute() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			GPDAO gpdao = new JDBCGPDAO(connection);
			int totalCount = gpdao.getTotalGPCount();
			pagination.setPreperties(totalCount);
			ArrayList<GP> gpList = gpdao.getGPList(pagination);
			pagination.setPage_records(gpList.size());
			gpForm.setTotalRecords(totalCount);
			gpForm.setPagerecords(Integer.toString(pagination.getPage_records()));
			gpForm.setGpList(gpList);
			session.setAttribute("pagination", pagination);
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return "gpList";
	}
	
	public String move(){
		setFormDataSort();
		return "gpList";
	}
	
	
	public void setFormDataSort() {

		Connection connection = null;
		int totalCount = 0;
		try{
			String searchGP = gpForm.getSearchText();	
			if(searchGP == null){
				searchGP = "";
			}
			if(searchGP.equals(""))
			{
				searchGP = (String) session.getAttribute("searchGP");
				
				if(searchGP == null){
					searchGP = "";
				}
			}
		connection = Connection_provider.getconnection();
		GPDAO gpdao = new JDBCGPDAO(connection);
		ArrayList<GP> gpList = new ArrayList<GP>();

		//pagination = (Pagination) session.getAttribute("pagination");		
		
		if(!searchGP.equals("")){
			 totalCount = gpdao.getTotalGPCountOfSearch(searchGP);
			 pagination.setPreperties(totalCount);
			 gpList = gpdao.geGPListOfSearch(searchGP,pagination);
		}else{
			 totalCount = gpdao.getTotalGPCount();
			 pagination.setPreperties(totalCount);
			 gpList = gpdao.getGPList(pagination);

		}		
		
		gpForm.setSearchText(searchGP);
		
		pagination.setPage_records(gpList.size());
		pagination.setPage_records(gpList.size());
		gpForm.setTotalRecords(totalCount);
		gpForm.setPagerecords(Integer.toString(pagination.getPage_records()));
		gpForm.setGpList(gpList);
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		
	}

	public void setFormData(){
		
		Connection connection = null;		
		try{
			connection = Connection_provider.getconnection();
			GPDAO gpdao = new JDBCGPDAO(connection);
		//	pagination = (Pagination) session.getAttribute("pagination");
			int totalCount = gpdao.getTotalGPCount();
			pagination.setPreperties(totalCount);
			ArrayList<GP> gpList = gpdao.getGPList(pagination);
			pagination.setPage_records(gpList.size());
			gpForm.setTotalRecords(totalCount);
			gpForm.setPagerecords(Integer.toString(pagination.getPage_records()));
			gpForm.setGpList(gpList);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String search() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		String searchGP = gpForm.getSearchText();
		if(searchGP==null || searchGP ==""){
			searchGP = (String) session.getAttribute("searchGP");
		}
		Connection connection = null;
		ArrayList<GP> gpList = new ArrayList<GP>();
		try{
			connection = Connection_provider.getconnection();
			GPDAO gpdao = new JDBCGPDAO(connection);
			int totalCount = gpdao.getTotalGPCountOfSearch(searchGP);
			pagination.setPreperties(totalCount);
			
			gpList = gpdao.geGPListOfSearch(searchGP,pagination);
			
			pagination.setPage_records(gpList.size());
			gpForm.setTotalRecords(totalCount);
			gpForm.setPagerecords(Integer.toString(pagination.getPage_records()));
			gpForm.setGpList(gpList);
			session.setAttribute("searchGP", searchGP);
			session.setAttribute("gpList", gpList);
			session.setAttribute("pagination", pagination);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return "gpList"; 
	}
	
	public String add() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			GPDAO gpdao = new JDBCGPDAO(connection);
			ArrayList<GP> tpCompanyList = gpdao.getTPCompanyList();
			gpForm.setTpCompanyList(tpCompanyList);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return "addGP";
	}
	
	public String save() throws Exception{
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			GPDAO gpdao = new JDBCGPDAO(connection);
			GP gp = new GP();
			gp.setThirdPartyId(gpForm.getThirdPartyId());
			gp.setName(gpForm.getName());
			gp.setWorkphno(gpForm.getWorkphno());
			gp.setEmailid(gpForm.getEmailid());
			gp.setFax(gpForm.getFax());
			gp.setDescription(gpForm.getDescription());
			int result = gpdao.saveGPDetail(gp);
			
			gpForm.setMessage("GP Detail Added Sucessfully!!");
			addActionMessage("GP Detail Added Sucessfully!!");
					
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		setFormData();
		return "gpList";
	}

	public String edit() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		int id =Integer.parseInt(request.getParameter("selectedid"));
		try{
			connection = Connection_provider.getconnection();
			GPDAO gpdao = new JDBCGPDAO(connection);
			ArrayList<GP> tpCompanyList = gpdao.getTPCompanyList();
			gpForm.setTpCompanyList(tpCompanyList);
			
			GP gp = new GP();
			gp = gpdao.getGPDetail(id);
			gpForm.setId(gp.getId());
			gpForm.setThirdPartyId(gp.getThirdPartyId());
			gpForm.setName(gp.getName());
			gpForm.setWorkphno(gp.getWorkphno());
			gpForm.setFax(gp.getFax());
			gpForm.setEmailid(gp.getEmailid());
			gpForm.setDescription(gp.getDescription());
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return "editGP";
	}
	
	public String update() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection = null;
		int result = 0;
		try{
			int id = gpForm.getId();
			connection = Connection_provider.getconnection();
			GP gp = new GP();
			GPDAO gpdao = new JDBCGPDAO(connection);
			
			gp.setId(id);
			gp.setThirdPartyId(gpForm.getThirdPartyId());
			gp.setName(gpForm.getName());
			gp.setWorkphno(gpForm.getWorkphno());
			gp.setEmailid(gpForm.getEmailid());
			gp.setFax(gpForm.getFax());
			gp.setDescription(gpForm.getDescription());
		
			result = gpdao.updateGPDetail(gp,id);
			
			gpForm.setMessage("GP Detail Updated Sucessfully!!");
			addActionMessage("GP Detail Updated Sucessfully!!");
			setFormData();
		}
		catch (Exception e) {
		e.printStackTrace();	
		}finally{
			connection.close();
		}
		return "gpList";
	}

	public String delete() throws Exception{
		
		if(!verifyLogin(request)){
			return "login";
		}
		int result = 0;
		int id =Integer.parseInt(request.getParameter("selectedid"));
		Connection connection = null;
		GP gp = new GP();
		
		try{			
			connection = Connection_provider.getconnection();
			GPDAO gpdao = new JDBCGPDAO(connection);		
			
			result = gpdao.deleteGPDetail(id);	
			gpForm.setMessage("GP Detail Deleted Sucessfully!!");
			addActionMessage("GP Detail Deleted Sucessfully!!");
			setFormData();
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		return "gpList";
	}
	
	public String back(){
		setFormData();		
		return "gpList";
	}
	
	public GPForm getModel() {
		// TODO Auto-generated method stub
		return gpForm;
	}

	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
