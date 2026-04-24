package com.apm.Master.web.action;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Appointment.eu.bi.AppointmentTypeDAO;
import com.apm.Appointment.eu.blogic.jdbc.JDBCAppointmentTypeDAO;
import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.bi.ProcedureMasterDao;
import com.apm.Master.eu.bi.SittingFollowupDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCProcedureDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCSittingFollowupDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.Procedure;
import com.apm.Master.eu.entity.SittingFollowup;
import com.apm.Master.web.form.ProcedureForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import freemarker.template.utility.Execute;

public class ProcedureAction extends BaseAction implements ModelDriven<ProcedureForm>,Preparable {
	
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpSession session = request.getSession();
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	ProcedureForm procedureForm=new ProcedureForm();
	String mastername=null;
	
	
	
	public String execute() throws Exception{
		
		Connection con=Connection_provider.getconnection();
		ProcedureMasterDao pdao=new JDBCProcedureDAO(con);		
		MasterDAO masterDAO=new JDBCMasterDAO(con);
		SittingFollowupDAO sittingFollowupDAO=new JDBCSittingFollowupDAO(con);
	    String searchText = procedureForm.getSearchtext();
		if(searchText!=null){
			if(searchText.equals("")){
				searchText=null;
			}
		}
		
		ArrayList<Procedure>procedurelist=pdao.getprocedurelist(searchText);
		procedureForm.setPlist(procedurelist);
		mastername=request.getParameter("selectedid");
		
		if(mastername!=null){
			
			 session.setAttribute("mastername", mastername);
		}else {
			mastername=(String)session.getAttribute("mastername");
		}
		
		procedureForm.setMastername(mastername);
		
		ArrayList<Master>proceduretypelist=masterDAO.getchargetype();
		procedureForm.setProceduretypelist(proceduretypelist);
		
		ArrayList<SittingFollowup>sittinglist=sittingFollowupDAO.getsittinglist();
		procedureForm.setSittinglist(sittinglist);
		
	   
		ArrayList<Master>departmentlist=masterDAO.getDisciplineDataList();
		procedureForm.setDepartmentlist(departmentlist);
			
		
		return "showallprocedure";
	}
	
	
public String save() throws Exception{
		
	Connection connection=null;
	
		try {
			
			Connection con=Connection_provider.getconnection();
			
			Procedure procedure=new Procedure();
			String id=request.getParameter("proceduretype");
			String procedurename=request.getParameter("procedurename");
			
			AppointmentTypeDAO appointmentTypeDAO=new JDBCAppointmentTypeDAO(con);
			String name=appointmentTypeDAO.getprocedureid(id);
			
			procedure.setProceduretype(name);
			procedure.setProcedureName(procedurename);
			procedure.setProcedureid(id);
			ProcedureMasterDao pdao=new JDBCProcedureDAO(con);	
			int result=pdao.saveProcedure(procedure);
			
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
		
		try {
			Connection con=Connection_provider.getconnection();
	        MasterDAO masterDAO=new JDBCMasterDAO(con);
			ArrayList<Master>proceduretypelist=masterDAO.getchargetype();
			procedureForm.setProceduretypelist(proceduretypelist);
			
			} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "addprocedure";
	}
	
	public String edit() throws Exception{
		
		try {
			
			String selectedid=request.getParameter("id");
			Connection con=Connection_provider.getconnection();
			ProcedureMasterDao pdao=new JDBCProcedureDAO(con);
			MasterDAO masterDAO=new JDBCMasterDAO(con);
			
			ArrayList<Master>proceduretypelist=masterDAO.getchargetype();
			Procedure p=pdao.geteditprocedure(selectedid);
			String data=p.getId()+"~~"+p.getProceduretype()+"~~"+p.getProcedureName();
			
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
		String proceduretype=request.getParameter("proceduretype");
		String procedurename=request.getParameter("procedurename");
		
		Procedure procedure=new Procedure();
		procedure.setId(id);
		procedure.setProceduretype(proceduretype);
		procedure.setProcedureName(procedurename);
		
		Connection con=Connection_provider.getconnection();
		ProcedureMasterDao pdao=new JDBCProcedureDAO(con);
		int result=pdao.updateprocedure(procedure);
		
	   } catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	
	return "updateprocedure";
	
  }

   public String delete() throws Exception{
    	
    	try {
    		
    		String selectedid=request.getParameter("id");
    		Connection con=Connection_provider.getconnection();
    		ProcedureMasterDao pdao=new JDBCProcedureDAO(con);
    		
    		int result=pdao.deleteprocedureData(selectedid);
    		
         } catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	
    	return "deleteprocedure";
    }
   
   public String getsittingdepartment() throws Exception{                          
		
		Connection connection=null;
		
		try {
			
			connection=Connection_provider.getconnection();
			String id=request.getParameter("id");
			
			SittingFollowupDAO sittingFollowupDAO=new JDBCSittingFollowupDAO(connection);
			
			ArrayList<SittingFollowup>list=sittingFollowupDAO.getAllSittingList(id);
			StringBuffer buffer=new StringBuffer();
			
			buffer.append("<label>Select Sitting</label>");
			buffer.append("<select name='sitting_id' id='sitting_id' onchange='setproceduremaster(this.value)' class='form-control chosen-select'>");
			buffer.append(" <option value='0'>Select Sitting</option>");
			
			for(SittingFollowup sittingFollowup:list){
				buffer.append("<option value='" + sittingFollowup.getId() + "'>" + sittingFollowup.getSittingFollowup() + "</option>");
			}
			buffer.append("</select>");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());
			
			} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return null;
	}

  public String masterlist() throws Exception{
		
		Connection connection=null;
		
		try {
			
			connection=Connection_provider.getconnection();
			String id=request.getParameter("id");
			
			MasterDAO masterDAO=new JDBCMasterDAO(connection);
			ArrayList<Master>proceduretypelist=masterDAO.getchargetypelist(id);

			StringBuffer buffer=new StringBuffer();
			
			 buffer.append("<label>Select Procedureasterlist</label>");
		     buffer.append("<select name='master_id' id='master_id' onchange='setProcedurelist(this.value)' class='form-control chosen-select'>");
			 buffer.append(" <option value='0'>Select Procedureasterlist</option>");
			 
			 for (Master master : proceduretypelist) {
				
				 buffer.append("<option value='" + master.getId() + "'>" + master.getName() + "</option>");
				 
			}
			 
			    buffer.append("</select>");
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(buffer.toString());
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	    }
 
   
   
  public String getproceduremasterlist() throws Exception {
		
		Connection connection=null;
		
		try {
			
			connection=Connection_provider.getconnection();
			String id=request.getParameter("id");
			
			
		   AppointmentTypeDAO appointmentTypeDAO=new JDBCAppointmentTypeDAO(connection);
		   String name=appointmentTypeDAO.getnameFromId(id);
		   
		   ArrayList<AppointmentType>list=appointmentTypeDAO.getProcedureList(name);
		   
		   StringBuffer buffer=new StringBuffer();
			
			buffer.append("<label>Select Procedurelist</label>");
			buffer.append("<select name='procedure_id' id='procedure_id'  class='form-control chosen-select'>");
			buffer.append(" <option value='0'>Select procedurelist</option>");
			
			
			for (AppointmentType appointmentType : list) {
				
				buffer.append("<option value='" + appointmentType.getId() + "'>" + appointmentType.getName() + "</option>");

				}
		   
		    buffer.append("</select>");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return null;
	}

    @Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		Connection connection= null;
		try {
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			ArrayList<Master> masterlist = masterDAO.getMasterList(loginInfo);
			procedureForm.setMasterlist(masterlist);
			mastername=(String)session.getAttribute("mastername");
			procedureForm.setMastername(mastername);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	@Override
	public ProcedureForm getModel() {
		// TODO Auto-generated method stub
		return procedureForm;
	}
	

}
