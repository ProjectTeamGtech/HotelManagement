package com.apm.Master.web.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.bi.UnitDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCUnitDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.Unit;

import com.apm.Master.web.form.UnitForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import freemarker.template.utility.Execute;

public class UnitAction extends BaseAction implements ModelDriven<UnitForm>,Preparable{
	
	
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpSession session = request.getSession();
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	UnitForm unitForm=new UnitForm();
	
	
	String mastername=null;
	
	
	public String save() throws Exception{
		
		try {
			
			Unit unit=new Unit();
			
			String unitmeasure=request.getParameter("unit");
			unit.setUnitMeasurement(unitmeasure);
			
			Connection con=Connection_provider.getconnection();
			UnitDAO unitDAO=new JDBCUnitDAO(con);
			int result=unitDAO.saveUnit(unit);
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+result+"");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return null;
	}
	
	
public String add() throws Exception{
		
		
		return "addunit";
		
	}
	
public String execute() throws Exception{
	
	try {
		
		Connection con=Connection_provider.getconnection();
		UnitDAO unitDAO=new JDBCUnitDAO(con);
		
		
		String searchText = unitForm.getSearchText();
		
		
		if(searchText!=null){
			if(searchText.equals("")){
				searchText=null;
			}
		}
		
		ArrayList<Unit>list=unitDAO.getallunitlist(searchText);
		unitForm.setUnitlist(list);
		
		mastername=request.getParameter("selectedid");
		
		if(mastername!=null){
			
			 session.setAttribute("mastername", mastername);
			
		} else {
			
			mastername=(String)session.getAttribute("mastername");
		}
		
		unitForm.setMastername(mastername);
		
		
	  } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	  }
	
   return "showallunit";
}


public String edit() throws Exception{
	
	try {
		
		String selectedid=request.getParameter("id");
		Connection con=Connection_provider.getconnection();
		
		UnitDAO unitDAO=new JDBCUnitDAO(con);
		
		Unit unit=unitDAO.geteditUnit(selectedid);
		
		String data=unit.getId()+"~~"+unit.getUnitMeasurement();
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+data+"");
		
		} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	
	return null;
}



public String update() throws Exception{
	
	try {
		
		String id=request.getParameter("id");
		String unitmeasure=request.getParameter("unit");
		
		Unit unit=new Unit();
		
		unit.setId(id);
		unit.setUnitMeasurement(unitmeasure);
		
		Connection con=Connection_provider.getconnection();
		UnitDAO unitDAO=new JDBCUnitDAO(con);
		
		int result=unitDAO.getupdateunit(unit);
		
		
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	
	
	return "updateunit";
}


public String delete() throws Exception{
	
	try {
		  
		String selectedid=request.getParameter("id");
		
		Connection con=Connection_provider.getconnection();
		
		UnitDAO unitDAO=new JDBCUnitDAO(con);
		
		int result=unitDAO.deleteUnitdata(selectedid);
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	
	return "deleteunit";
}




    @Override
public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
		Connection connection= null;
		
		try {
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			ArrayList<Master> masterlist = masterDAO.getMasterList(loginInfo);
			unitForm.setMasterlist(masterlist);
			mastername=(String)session.getAttribute("mastername");
			unitForm.setMastername(mastername);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	@Override
	public UnitForm getModel() {
		// TODO Auto-generated method stub
		return unitForm;
	}
	
}
