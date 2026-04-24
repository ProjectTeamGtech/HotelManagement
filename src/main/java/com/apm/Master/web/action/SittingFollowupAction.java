package com.apm.Master.web.action;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.DiaryManagement.eu.bi.FinderDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCFinderDAO;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.bi.SittingFollowupDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCSittingFollowupDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.SittingFollowup;
import com.apm.Master.web.form.SittingFollowupForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;



public class SittingFollowupAction extends BaseAction implements ModelDriven<SittingFollowupForm>,Preparable {
	
	
	
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpSession session = request.getSession();
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	SittingFollowupForm sform=new SittingFollowupForm();
	
	
	String mastername=null;
	
	
	public String save() throws Exception{
		
		
		try {
			
			SittingFollowup sf=new SittingFollowup();
			
			sf.setSittingFollowup(sform.getSittingFollowup());
			sf.setDepartment(sform.getDepartment());
			
			
			Connection con=Connection_provider.getconnection();
			SittingFollowupDAO sdao=new JDBCSittingFollowupDAO(con);
			int result=sdao.saveSitting(sf);
			
			} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return execute();
		
	}
	
	
	public String execute() throws Exception{
		
		try {
			
			Connection con=Connection_provider.getconnection();
			SittingFollowupDAO sdao=new JDBCSittingFollowupDAO(con);
		
			MasterDAO masterDAO=new JDBCMasterDAO(con);
			
			ArrayList<SittingFollowup>list=sdao.getsittinglist();
			sform.setSlist(list);
		   
			mastername=request.getParameter("selectedid");
			
			if(mastername!=null){
				
				 session.setAttribute("mastername", mastername);
				
			}else {
				
				mastername=(String)session.getAttribute("mastername");
			}
			
			sform.setMastername(mastername);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "showallsitting";
	}
	
	
public String add() throws Exception{
	
	
	try {
		
		Connection con=Connection_provider.getconnection();
		SittingFollowupDAO sdao=new JDBCSittingFollowupDAO(con);
        MasterDAO masterDAO=new JDBCMasterDAO(con);
		
		ArrayList<Master>departmentlist=masterDAO.getDisciplineDataList();
		
		 sform.setDepartmentlist(departmentlist);
		
		} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	
	 return "addsitting";
		
	}



public String edit() throws Exception{
	
	
	try {
		
		String selectedid=request.getParameter("selectedid");
		Connection con=Connection_provider.getconnection();
		SittingFollowupDAO sdao=new JDBCSittingFollowupDAO(con);
		MasterDAO masterDAO=new JDBCMasterDAO(con);
		
		ArrayList<Master>list=masterDAO.getDisciplineDataList();
		
		SittingFollowup s=sdao.geteditsitting(selectedid);
		 
		sform.setId(s.getId());
		sform.setSittingFollowup(s.getSittingFollowup());
		sform.setDepartment(s.getDepartment());
		sform.setDepartmentlist(list);
		
		} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	
	
	return "editsitting";
	
}
	
public String update() throws Exception{
	
	 try {
		
		SittingFollowup sf=new SittingFollowup();
		
		sf.setId(sform.getId());
		sf.setSittingFollowup(sform.getSittingFollowup());
		sf.setDepartment(sform.getDepartment());
		
		Connection con=Connection_provider.getconnection();
		SittingFollowupDAO sdao=new JDBCSittingFollowupDAO(con);
		
		int result=sdao.updatesitting(sf);
		
	   } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return "updatesitting";
	
}	


public String delete() throws Exception{
	
	
	try {
		
		String selectedid=request.getParameter("selectedid");
		Connection con=Connection_provider.getconnection();
		SittingFollowupDAO sdao=new JDBCSittingFollowupDAO(con);
		
		int result=sdao.deletesittingData(selectedid);
		
	   } catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	
	return "deletesitting";
	
}

    @Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
		Connection connection= null;
		try {
			
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			ArrayList<Master> masterlist = masterDAO.getMasterList(loginInfo);
			sform.setMasterlist(masterlist);
			mastername=(String)session.getAttribute("mastername");
			sform.setMastername(mastername);
			
		/*	BookDAO bookdao= new JDBCBookDAO(connection);
			ArrayList<Book> booklist= bookdao.getallBooks(null, null);
			bookForm.setBooklist(booklist);

			SittingFollowupDAO sdao=new JDBCSittingFollowupDAO(connection);
			ArrayList<SittingFollowup>sittinglist=sdao.*/
			
			} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	@Override
	public SittingFollowupForm getModel() {
		// TODO Auto-generated method stub
		return sform;
	}

}
