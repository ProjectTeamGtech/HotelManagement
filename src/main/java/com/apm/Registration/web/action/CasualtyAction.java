
package com.apm.Registration.web.action;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.bi.NotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCNotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.Registration.eu.bi.CasualtyDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCCasualtyDAO;
import com.apm.Registration.eu.entity.Casualty;
import com.apm.Registration.web.form.CasualtyForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class CasualtyAction extends BaseAction implements Preparable, ModelDriven<CasualtyForm> {
	

	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	CasualtyForm casualtyForm = new CasualtyForm();
	private Pagination pagination = new Pagination(10, 1);
	public Pagination getPagination() {
		return pagination;
	}


	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	
	
	@Override
	public String execute() throws Exception {
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection = null;

		try{
			
			connection = Connection_provider.getconnection();
			CasualtyDAO casualtyDAO = new JDBCCasualtyDAO(connection);
			
			int count=casualtyDAO.getTotalCasualtyList();
			pagination.setPreperties(count);
			
			ArrayList<Casualty>casualtyList = casualtyDAO.getCasualtyList(connection,pagination);
			casualtyForm.setCasualtyList(casualtyList);
            pagination.setPage_records(casualtyList.size());
			
			
        
            casualtyForm.setTotalRecords(count);
            casualtyForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			
			connection.close();
		}
		return SUCCESS;
	}
	
	
	public String edit() throws Exception{
		
		if(!verifyLogin(request)){
			return "login";
		}
		String id = request.getParameter("id");
		Connection connection = null;

		try{
			
			connection = Connection_provider.getconnection();
			CasualtyDAO casualtyDAO = new JDBCCasualtyDAO(connection);
			
			Casualty casualty = casualtyDAO.getCasualtyDetails(id);
			
			casualtyForm.setTitle(casualty.getTitle());
			casualtyForm.setFname(casualty.getFname().trim());
			casualtyForm.setLname(casualty.getLname().trim());
			casualtyForm.setAge(casualty.getAge());
			casualtyForm.setGender(casualty.getGender());
			casualtyForm.setAddress(casualty.getAddress());
			casualtyForm.setMob(casualty.getMob());
			casualtyForm.setProvdiag(casualty.getProvdiag());
			casualtyForm.setRecbelonging(casualty.getRecbelonging());
			casualtyForm.setDrincharge(casualty.getDrincharge());
			casualtyForm.setMlc(casualty.getMlc());
			casualtyForm.setTypeofdis(casualty.getTypeofdis());
			casualtyForm.setRefby(casualty.getRefby());
			casualtyForm.setNote(casualty.getNote());
			casualtyForm.setMlcno(casualty.getMlcno());
			
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			
			connection.close();
		}
		
		
		
		return "edit";
	}
	
	public String editsave() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection = null;

			try{
				
				connection = Connection_provider.getconnection();
				CasualtyDAO casualtyDAO = new JDBCCasualtyDAO(connection);
				Casualty casualty = new Casualty();
				
				casualty.setTitle(casualtyForm.getTitle());
				casualty.setFname(casualtyForm.getFname().trim());
				casualty.setLname(casualtyForm.getLname().trim());
				casualty.setAge(casualtyForm.getAge());
				casualty.setGender(casualtyForm.getGender());
				casualty.setAddress(casualtyForm.getAddress());
				casualty.setMob(casualtyForm.getMob());
				casualty.setProvdiag(casualtyForm.getProvdiag());
				casualty.setRecbelonging(casualtyForm.getRecbelonging());
				casualty.setDrincharge(casualtyForm.getDrincharge());
				casualty.setMlc(casualtyForm.getMlc());
				casualty.setRefby(casualtyForm.getRefby());
				casualty.setTypeofdis(casualtyForm.getTypeofdis());
				casualty.setNote(casualtyForm.getNote());
				casualty.setMlcno(casualtyForm.getMlcno());
				casualty.setDob(DateTimeUtils.getAgeToDob(casualtyForm.getAge()));
				
				String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				casualty.setDate(date);
				
				int id = casualtyForm.getId();
				int upd = casualtyDAO.updateCasualty(casualty,id);
				
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				
				connection.close();
			}
		
		return "editsave";
	}
	
	public String save() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection = null;

			try{
				
				connection = Connection_provider.getconnection();
				CasualtyDAO casualtyDAO = new JDBCCasualtyDAO(connection);
				Casualty casualty = new Casualty();
				
				casualty.setTitle(casualtyForm.getTitle());
				casualty.setFname(casualtyForm.getFname().trim());
				casualty.setLname(casualtyForm.getLname().trim());
				casualty.setAge(casualtyForm.getAge());
				casualty.setGender(casualtyForm.getGender());
				casualty.setAddress(casualtyForm.getAddress());
				casualty.setMob(casualtyForm.getMob());
				casualty.setProvdiag(casualtyForm.getProvdiag());
				casualty.setRecbelonging(casualtyForm.getRecbelonging());
				casualty.setDrincharge(casualtyForm.getDrincharge());
				casualty.setMlc(casualtyForm.getMlc());
				casualty.setRefby(casualtyForm.getRefby());
				casualty.setTypeofdis(casualtyForm.getTypeofdis());
				casualty.setNote(casualtyForm.getNote());
				casualty.setDob(DateTimeUtils.getAgeToDob(casualtyForm.getAge()));
				casualty.setMlcno(casualtyForm.getMlcno());
				
				String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				casualty.setDate(date);
				
				int result = casualtyDAO.saveCasualty(casualty);
				casualty.setId(result);
				
				//save in apm_patient
				int r = casualtyDAO.savePatient(casualty);
				
				
			}catch (Exception e) {
				// TODO: handle exception
			}finally{
				
				connection.close();
			}
			
			
		
		
		return "save";
	}
	

	public CasualtyForm getModel() {
		// TODO Auto-generated method stub
		return casualtyForm;
	}

	public void prepare() throws Exception {
		Connection connection = null;
		
		try{
			
			connection = Connection_provider.getconnection();
			ClientDAO clientDAO = new JDBCClientDAO(connection); 
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			
			ArrayList<String> initialList = clientDAO.getInitialList();
			casualtyForm.setInitialList(initialList);
			
			ArrayList<DiaryManagement>userList = notAvailableSlotDAO.getUserList(loginInfo.getId());
			casualtyForm.setUserList(userList);
			
			ArrayList<Client> condtitionList = clientDAO.getEmrTreatmentTypeList();
			casualtyForm.setTreatmentTypeList(condtitionList);
			
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			
			connection.close();
		}
		
	}

}
