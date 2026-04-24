package com.apm.Master.web.action;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import com.apm.Master.eu.bi.CityMasterDAO;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.bi.StateDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCCityMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCStateDAO;
import com.apm.Master.eu.entity.CityMaster;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.State;
import com.apm.Master.web.form.CityMasterForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class CityMasterAction extends BaseAction implements ModelDriven<CityMasterForm>,Preparable{

	CityMasterForm cityMasterForm = new CityMasterForm();
	String mastername;
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpSession session = request.getSession();
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
    Pagination pagination=new Pagination(15,1);
    
    public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	
	public CityMasterForm getModel() {		
		return cityMasterForm;
	}
	
	public String execute() throws Exception {
		Connection connection=null;
		try {
			connection = Connection_provider.getconnection();
			CityMasterDAO cityMasterDAO = new JDBCCityMasterDAO(connection);
			String SearchText=cityMasterForm.getSearchText();
			
			if(SearchText !=null)
			{
				if(SearchText.equals(""))
				{
					SearchText=null;
				}
			}
			
			int count=cityMasterDAO.getTotalCityCount();
			pagination.setPreperties(count);
			
			ArrayList<CityMaster> citylist = cityMasterDAO.getallCity(pagination,SearchText);
			pagination.setPage_records(citylist.size());
			
			
			cityMasterForm.setCitylist(citylist);
			cityMasterForm.setTotalRecords(count);
			cityMasterForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			mastername=request.getParameter("selectedid");
			
			if(mastername!=null){
				
				 session.setAttribute("mastername", mastername);
				
			} else {
				
				mastername=(String)session.getAttribute("mastername");
			}
			cityMasterForm.setMastername(mastername);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		return "city";
	}
	
	public String add(){
		return "addcity";	
	}
	
	public String save() throws Exception{
		Connection connection=null;
		try {
			connection = Connection_provider.getconnection();
			CityMasterDAO cityMasterDAO = new JDBCCityMasterDAO(connection);
			CityMaster cityMaster = new CityMaster();
			cityMaster.setStateid(cityMasterForm.getStateid());
			cityMaster.setCity(cityMasterForm.getCity());
			int result = cityMasterDAO.addcityDB(cityMaster);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		connection.close();
		}
		return "savecity";
	}

	public String delete(){
		Connection connection;
		try {
			connection = Connection_provider.getconnection();
			CityMasterDAO cityMasterDAO = new JDBCCityMasterDAO(connection);
			CityMaster cityMaster = new CityMaster();
			cityMaster.setId(cityMasterForm.getId());
			int i = cityMaster.getId();
			int result = cityMasterDAO.deletecityDB(cityMaster);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "deletedcity";
	}
	public String edit() throws Exception{
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			CityMasterDAO cityMasterDAO = new JDBCCityMasterDAO(connection);
			CityMaster cityMaster = new CityMaster();
			cityMaster.setId(cityMasterForm.getId());
			CityMaster city = cityMasterDAO.getcitydetails(cityMaster);
			cityMasterForm.setId(city.getId());
			cityMasterForm.setStateid(city.getStateid());
			cityMasterForm.setCity(city.getCity());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return "editcity";
	}
	
	public String update() throws Exception{
		Connection connection=null;
		try {
			connection = Connection_provider.getconnection();
			CityMasterDAO cityMasterDAO = new JDBCCityMasterDAO(connection);
			CityMaster cityMaster = new CityMaster();
			cityMaster.setId(cityMasterForm.getId());
			cityMaster.setStateid(cityMasterForm.getStateid());
			cityMaster.setCity(cityMasterForm.getCity());
			int result =cityMasterDAO.updatecityDB(cityMaster);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		connection.close();
		}
		return "updatecity";
	}
	public void prepare() throws Exception {
		Connection connection = null;
		
		try { 
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			ArrayList<Master> masterlist = masterDAO.getMasterList(loginInfo);
			cityMasterForm.setMasterlist(masterlist);
			mastername=(String)session.getAttribute("mastername");
			cityMasterForm.setMastername(mastername);
			
			StateDAO stateDAO = new JDBCStateDAO(connection);
			ArrayList<State> statelist = stateDAO.getallState(null,null);
			cityMasterForm.setStatelist(statelist);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}

	
}
