package com.apm.Master.web.action;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.bi.MedicineTypeDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMedicineTypeDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.MedicineType;
import com.apm.Master.web.form.MedicineTypeMasterForm;
import com.apm.Master.web.form.ProductTypeMasterForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
public class MedicineTypeMasterAction extends BaseAction implements ModelDriven<MedicineTypeMasterForm>,Preparable{

	
	MedicineTypeMasterForm medicinetypemasterform=new MedicineTypeMasterForm();
	HttpServletRequest request=ServletActionContext.getRequest();
	HttpServletResponse response=ServletActionContext.getResponse();
	HttpSession session=request.getSession();
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	  Pagination pagination=new Pagination(10,1);
	String mastername;
	String masterid;
	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
Connection connection = null;
		
		try { 
			
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			ArrayList<Master> masterlist = masterDAO.getMasterList(loginInfo);
			medicinetypemasterform.setMasterlist(masterlist);
			mastername=(String)session.getAttribute("mastername");
			medicinetypemasterform.setMastername(mastername);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}
	}
	
	
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		
		Connection connection=null;
		try {
			connection= Connection_provider.getconnection();
			MedicineTypeDAO medicineDAO=new JDBCMedicineTypeDAO(connection);
			int count=medicineDAO.getTotalmedicinetypeCount();
			pagination.setPreperties(count);
			String searchText=medicinetypemasterform.getSearchText();
			if(searchText!=null){
				if(searchText.equals("")){
					searchText=null;
				}
			}
			ArrayList<MedicineType> medicinetypelist =medicineDAO.getAllmedicinetypeList(searchText,pagination);
			medicinetypemasterform.setMedicinetypelist(medicinetypelist);
			int sizepage=medicinetypelist.size();
			pagination.setPage_records(medicinetypelist.size());
			medicinetypemasterform.setPagerecords(String.valueOf(pagination.getPage_records()));
			medicinetypemasterform.setTotalRecords(String.valueOf(count));
			mastername=request.getParameter("selectedid");
			if(mastername!=null){
			 session.setAttribute("mastername", mastername);
		} else {
			mastername=(String)session.getAttribute("mastername");
		}
			medicinetypemasterform.setMastername(mastername);
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "medicinetypemaster";
	}
	
	public String save() throws Exception{
		Connection connection=null;
		try {
			connection = Connection_provider.getconnection();
			MedicineTypeDAO masterDAO = new JDBCMedicineTypeDAO(connection);
			MedicineType master=new MedicineType();
			//Master master = new Master();
			master.setName(medicinetypemasterform.getName());
			master.setIsstrip(medicinetypemasterform.getIsstrip());
			int result = masterDAO.addproductypelist(master);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		connection.close();
		}
		return "savemedicinetypemaster";
		
	}
	
	
	
	public String update() throws Exception{
		Connection connection=null;
		try {
			connection = Connection_provider.getconnection();
			MedicineTypeDAO masterDAO = new JDBCMedicineTypeDAO(connection);
			MedicineType master=new MedicineType();
			//Master master = new Master();
			master.setId(medicinetypemasterform.getId());
			master.setName(medicinetypemasterform.getName());
			master.setIsstrip(medicinetypemasterform.getIsstrip());
			int result = masterDAO.updateproductypelist(master);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		connection.close();
		}
		return "updatemedicinetypemaster";
		
	}
	

	public MedicineTypeMasterForm getModel() {
		// TODO Auto-generated method stub
		return medicinetypemasterform;
	}

	
	public String add(){
		return "addmedicinetypemaster";
	}
	public String edit(){
		masterid=request.getParameter("id");
		Connection connection=null;
		try {
			connection = Connection_provider.getconnection();
			MedicineTypeDAO masterDAO = new JDBCMedicineTypeDAO(connection);
			MedicineType master = new MedicineType();
		
			master.setId(Integer.parseInt(masterid));
			MedicineType master2 = masterDAO.getmedicinetypemasterinfo(master);
			
			medicinetypemasterform.setId(master2.getId());
			medicinetypemasterform.setName(master2.getName());
			medicinetypemasterform.setIsstrip(master2.getIsstrip());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "editmedicinetypemaster";
	}

public String delete(){
	masterid=request.getParameter("id");
	Connection connection=null;
	try {
		connection = Connection_provider.getconnection();
		MedicineTypeDAO masterDAO = new JDBCMedicineTypeDAO(connection);
		MedicineType master = new MedicineType();
		master.setId(Integer.parseInt(masterid));
		int  master2 = masterDAO.deleteproductlist(master);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return "deletedmedicinetypemaster";
}
}
