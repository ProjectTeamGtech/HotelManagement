package com.apm.Master.web.action;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.bi.StateDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCStateDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.State;
import com.apm.Master.web.form.StateForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class StateAction extends BaseAction implements ModelDriven<StateForm>,Preparable{

	StateForm stateForm = new StateForm();
	String mastername;
	
	HttpServletRequest request=ServletActionContext.getRequest();
	HttpServletResponse response=ServletActionContext.getResponse();
	HttpSession session=request.getSession();
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
    Pagination pagination=new Pagination(25,1);
    
	
		
	public StateForm getModel() {
		return stateForm;
	}
	
	public String execute() throws Exception {
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			
			StateDAO stateDAO = new JDBCStateDAO(connection);
			int count=stateDAO.getTotalStateCount();
			pagination.setPreperties(count);
			String searchText = stateForm.getSearchText();
			if(searchText!=null){
				if(searchText.equals("")){
					searchText=null;
				}
			}
			
			ArrayList<State> statelist = stateDAO.getallState(searchText,pagination);
			
			stateForm.setStatelist(statelist);
			mastername=request.getParameter("selectedid");
			pagination.setPage_records(statelist.size());
			stateForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			stateForm.setTotalRecords(String.valueOf(count));
			if(mastername!=null){
				
				 session.setAttribute("mastername", mastername);
				
			} else {
				
				mastername=(String)session.getAttribute("mastername");
			}
			stateForm.setMastername(mastername);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		return "state";
	}
	
	public String add(){
		return "addstate";	
	}
	
	public String save() throws Exception{
		Connection connection=null;
		try {
			connection = Connection_provider.getconnection();
			StateDAO stateDAO = new JDBCStateDAO(connection);
			State state = new State();
			state.setName(stateForm.getName());
			int result = stateDAO.addstateDB(state);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		connection.close();
		}
		return "savestate";
	}

	
	public String edit(){
		Connection connection;
		try {
			connection = Connection_provider.getconnection();
			StateDAO stateDAO = new JDBCStateDAO(connection);
			State state = new State();
			state.setId(stateForm.getId());
			State state2 = stateDAO.getstateinfo(state);
			stateForm.setId(state2.getId());
			stateForm.setName(state2.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "editstate";
	}
	
	public String update() throws Exception{
		Connection connection=null;
		try {
			connection = Connection_provider.getconnection();
			StateDAO stateDAO = new JDBCStateDAO(connection);
			State state = new State();
			state.setId(stateForm.getId());
			state.setName(stateForm.getName());
			int result =stateDAO.updateStateDB(state);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		connection.close();
		}
		return "updatestate";
	}

	
	public String delete(){
		Connection connection;
		try {
			connection = Connection_provider.getconnection();
			StateDAO stateDAO = new JDBCStateDAO(connection);
			State state = new State();
			state.setId(stateForm.getId());
			int i = state.getId();
			int result = stateDAO.deleteStateDB(state);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "deletedstate";
	}
	


	public void prepare() throws Exception {
		Connection connection = null;
		
		try { 
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			ArrayList<Master> masterlist = masterDAO.getMasterList(loginInfo);
			stateForm.setMasterlist(masterlist);

			mastername=(String)session.getAttribute("mastername");
			
			stateForm.setMastername(mastername);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	
}
