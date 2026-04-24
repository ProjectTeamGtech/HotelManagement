package com.apm.Master.web.action;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Master.eu.bi.AmbulanceDAO;
import com.apm.Master.eu.bi.CityMasterDAO;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCAmbulanceDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCCityMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.entity.Ambulance;
import com.apm.Master.eu.entity.CityMaster;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.web.form.AmbulanceForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class AmbulanceAction extends BaseAction implements ModelDriven<AmbulanceForm>,Preparable{
	
	AmbulanceForm ambform=new AmbulanceForm();
	String mastername;
	
    HttpServletRequest request=ServletActionContext.getRequest();
	HttpServletResponse response=ServletActionContext.getResponse();
	HttpSession session=request.getSession();
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	Pagination pagination=new Pagination(15, 1);
	
	
	
	@Override
	public void prepare() throws Exception {
		Connection con=null;
		try {
			con=Connection_provider.getconnection();
			MasterDAO masterdao=new JDBCMasterDAO(con);
			ArrayList<Master>masterlist=masterdao.getMasterList(loginInfo);
			ambform.setMasterlist(masterlist);
			mastername=(String) session.getAttribute(mastername);
			ambform.setMastername(mastername);
			
			/*AmbulanceDAO ambdao=new JDBCAmbulanceDAO(con);
			ArrayList<Ambulance>ambulancetypelist=ambdao.getambulancelist();
			ambform.setAmbulancetypelist(ambulancetypelist);*/
			
			
		} catch (Exception e) {
          e.printStackTrace();		
          }
		
		finally {
			con.close();
		}
	}
	
	@Override
	public String execute() throws Exception {
		Connection con=null;
		try {
			con=Connection_provider.getconnection();
			AmbulanceDAO ambdao=new JDBCAmbulanceDAO(con);
			int count=ambdao.getTotalambulance();
			pagination.setPreperties(count);
			String searchText=ambform.getSearchText();
			
			if(searchText!=null){
				if(searchText.equals("")){
					searchText=null;
				}
			}
			
			ArrayList<Ambulance>ambulancelist=ambdao.getAllAmbulance(searchText,pagination);
			ambform.setAmbulancelist(ambulancelist);
			mastername=request.getParameter("selectedid");
			pagination.setPage_records(ambulancelist.size());
			ambform.setPagerecords(String.valueOf(pagination.getPage_records()));
			ambform.setTotalRecords(String.valueOf(count));
			if(mastername!=null){
				
				 session.setAttribute("mastername", mastername);
				
			} else {
				
				mastername=(String)session.getAttribute("mastername");
			}
			ambform.setMastername(mastername);
			
		} catch (Exception e) {

                 e.printStackTrace();
		}
		finally{
			con.close();
		}
		return "ambulance";
	}
	
	
	
	
	public String add(){
		
		Connection con=null;
		
		try {
			con=Connection_provider.getconnection();
			AmbulanceDAO ambdao=new JDBCAmbulanceDAO(con);
			ArrayList<Ambulance>ambulancetypelist=ambdao.getambulancelist();
			ArrayList<Ambulance>driverlist=ambdao.getDriverlist();
			ambform.setAmbulancetypelist(ambulancetypelist);
			ambform.setDriverlist(driverlist);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "addambulance";	
	}
	
	public String save() throws Exception{
		Connection connection=null;
		try {
			
			/*Ambulance amb=new Ambulance();
			amb.setVehicleno(ambform.getVehicleno());
			amb.setAmbulancetype(ambform.getAmbulancetype());*/
			
			connection = Connection_provider.getconnection();
			AmbulanceDAO ambdao = new JDBCAmbulanceDAO(connection);
			Ambulance amb=new Ambulance();
			amb.setVehicleno(ambform.getVehicleno());
			amb.setAmbulancetype(ambform.getAmbulancetype());
			amb.setDriverfname(ambform.getDriverfname());
			int result = ambdao.addambDB(amb);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		connection.close();
		}
		return "saveambulance";
	}
	
	
	
	public String edit() throws Exception{
		Connection con=null;
		try {
			String selectedid=request.getParameter("selectedid");
			 con=Connection_provider.getconnection();
			 AmbulanceDAO ambdao=new JDBCAmbulanceDAO(con);
			 ArrayList<Ambulance>ambulancetypelist=ambdao.getambulancelist();
			 ArrayList<Ambulance>driverlist=ambdao.getDriverlist();
			 
			 Ambulance amb1=ambdao.getAmbulanceDetail(selectedid);
			 ambform.setId(amb1.getId());
			 ambform.setVehicleno(amb1.getVehicleno());
			 ambform.setAmbulancetype(amb1.getAmbulancetype());
			 ambform.setDriverfname(amb1.getDriverfname());
			 ambform.setAmbulancetypelist(ambulancetypelist);
			 ambform.setDriverlist(driverlist);
			 
			 
		} catch (Exception e) {
            e.printStackTrace();
		}
		finally{
			con.close();
			}
		return "editambulance";
		
	}
	
	public String update() throws Exception{
		Connection con=null;
		try {
			con=Connection_provider.getconnection();
			AmbulanceDAO ambdao=new JDBCAmbulanceDAO(con);
			Ambulance amb=new Ambulance();
			amb.setId(ambform.getId());
			amb.setVehicleno(ambform.getVehicleno());
			amb.setAmbulancetype(ambform.getAmbulancetype());
			amb.setDriverfname(ambform.getDriverfname());
			int result=ambdao.updateAmbulanceDB(amb);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			con.close();
		}
		return "updateambulance";
	}
	
	
	public String delete(){
		
		try {
			String selectedid=request.getParameter("selectedid");
			Connection con=Connection_provider.getconnection();
			AmbulanceDAO ambdao=new JDBCAmbulanceDAO(con);
			int result=ambdao.deleteAmbulance(selectedid);
			
			
		} catch (Exception e) {
                 e.printStackTrace();
		}
		return "deleteambulance";
	}
	

  public Pagination getPagination() {
		return pagination;
	}
	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	
	@Override
	public AmbulanceForm getModel() {
	
		return ambform;
	}
	

}
