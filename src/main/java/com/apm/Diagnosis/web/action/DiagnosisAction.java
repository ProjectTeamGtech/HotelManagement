package com.apm.Diagnosis.web.action;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Diagnosis.eu.bi.DiagnosisDAO;
import com.apm.Diagnosis.eu.blogic.jdbc.JDBCDiagnosisDAO;
import com.apm.Diagnosis.eu.entity.Diagnosis;
import com.apm.Diagnosis.web.form.DiagnosisForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class DiagnosisAction extends BaseAction implements ModelDriven<DiagnosisForm> ,Preparable{

	
	DiagnosisForm diagnosisForm=new DiagnosisForm();
	HttpServletRequest request=ServletActionContext.getRequest();
	HttpServletResponse response=ServletActionContext.getResponse();
	HttpSession session=request.getSession(false);
	Pagination pagination=new Pagination(10, 1);
		
	public Pagination getPagination() {
		return pagination;
	}


	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}


	@Override
	public String execute() throws Exception {

		if(!verifyLogin(request)) {
			return "login";
		}
		
		Connection connection=null;
		
		try {
			
			connection=Connection_provider.getconnection();
			DiagnosisDAO diagnosisDAO=new JDBCDiagnosisDAO(connection);
			
			ArrayList<Diagnosis> diagnosislist=diagnosisDAO.getAllDiagnosis();
			
			diagnosisForm.setDiagnosislist(diagnosislist);
			
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		return super.execute();
	}
	
	
	public String savediagnosis() throws Exception {
		
		Connection connection=null;
		
		try {
			
			connection=Connection_provider.getconnection();
			String name=request.getParameter("name");
			String diagnosisid = request.getParameter("diagnosisname");
			DiagnosisDAO diagnosisDAO=new JDBCDiagnosisDAO(connection);
			Diagnosis diagnosis=new Diagnosis();
			diagnosis.setName(name);
			
			if(!name.equals("") && name!=null) {
			   int result=diagnosisDAO.addDiagnosisName(diagnosis);
			
			}
			StringBuffer str=new StringBuffer();
			str.append("<label class='control-label'>Select Existing Diagnosis Name </label>");
			str.append("<select class='form-control' required='required' onchange='setproblemlist(this.value)'>");
			ArrayList<Diagnosis> diagnosislist=diagnosisDAO.getAllDiagnosis();
			str.append("<option value='0'>Select Diagnosis Name</option>");
            for(Diagnosis da:diagnosislist) {
            	str.append("<option value='"+da.getId()+"'>"+da.getName()+"</option>");
            	
            }
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(str.toString());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		finally {
			connection.close();
		}
		
			
		return null;
	}
	
	public String listproblemsinid() throws Exception{
		
		Connection connection=null;
		
		try {
			
			connection=Connection_provider.getconnection();
			DiagnosisDAO diagnosisDAO=new JDBCDiagnosisDAO(connection);
			
			String id=request.getParameter("id");
			ArrayList<Diagnosis> diagnosislist=diagnosisDAO.getAllDiagnosisList(id); 
			
			StringBuffer str=new StringBuffer();
			str.append("<label class='control-label'>Select Existing Problem Name </label>");
			str.append("<select class='form-control' required='required' onchange='setproblemid(this.value)'>");
			str.append("<option value='0'>Select Problem Name</option>");
            for(Diagnosis da:diagnosislist) {
            	
               str.append("<option value='"+da.getId()+"'>"+da.getProblem_name()+"</option>");
            }
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(str.toString());
			
			
		
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			connection.close();
		}
	
		return null;
	}
	
	
    public String saveproblem() throws Exception {
		
    	 
		Connection connection=null;
    	try {
			
    		connection=Connection_provider.getconnection();
    		String diagnosis_id=request.getParameter("id");
    		String problem=request.getParameter("problemname");
    		
    		
    	    DiagnosisDAO diagnosisDAO=new JDBCDiagnosisDAO(connection);
    	    Diagnosis diagnosis=new Diagnosis();
    	    diagnosis.setProblem_name(problem);
    	    diagnosis.setDiagnosis_id(diagnosis_id);
    	    
            if(!problem.equals("")) {
            	int result=diagnosisDAO.addProblemName(diagnosis);
    		}
    	    
    	    StringBuffer str=new StringBuffer();
			str.append("<label class='control-label'>Select Existing Diagnosis Name </label>");
			str.append("<select class='form-control' required='required' onchange='setproblemlist1(this.value)'>");
			ArrayList<Diagnosis> diagnosislist=diagnosisDAO.getAllDiagnosis();
			str.append("<option value='0'>Select Diagnosis Name</option>");
            for(Diagnosis da:diagnosislist) {
            	
               str.append("<option value='"+da.getId()+"'>"+da.getName()+"</option>");
            }
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(str.toString());
    	    
    		
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
    	finally {
    		connection.close();
    	}
    	
		return null;
	}
	
	
	public String saveintervention() throws Exception {
		
		Connection connection=null;
		try {
			
			connection=Connection_provider.getconnection();
			String intervention=request.getParameter("intervention");
			String id=request.getParameter("id");
			String problem_id=request.getParameter("problem_id");
			
			Diagnosis diagnosis=new Diagnosis();
			diagnosis.setDiagnosis_id(String.valueOf(id));
			diagnosis.setDiag_problem_id(problem_id);
            diagnosis.setIntervention(intervention); 			
			
            DiagnosisDAO diagnosisDAO=new JDBCDiagnosisDAO(connection);
            int result=diagnosisDAO.addInterventionName(diagnosis);
            response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("");
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		return null;
	}
	
	
	public String listdiagnosis() throws Exception{
		
		if(!verifyLogin(request)) {
			return "login";
		}
	
		Connection connection=null;
		try {
			
			connection=Connection_provider.getconnection();
			DiagnosisDAO diagnosisDAO=new JDBCDiagnosisDAO(connection);
			int total=diagnosisDAO.getTotalInterventionCount(null);
			pagination.setPreperties(total);
			diagnosisForm.setTotalRecords(total);
			ArrayList<Diagnosis> diagnosislist=diagnosisDAO.getAllDiagnosisList(pagination);
			pagination.setPage_records(diagnosislist.size());
			diagnosisForm.setPagerecords(String.valueOf(pagination.getPage_records()));
		    diagnosisForm.setDiagnosislist(diagnosislist);	
		
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
	
		return "list";
	}
	
	
	
	
	
	public void prepare() throws Exception {

		
        Connection connection=null;
		
	    try {
			
			connection=Connection_provider.getconnection();
			DiagnosisDAO diagnosisDAO=new JDBCDiagnosisDAO(connection);
			
			ArrayList<Diagnosis> diagnosislist=diagnosisDAO.getAllDiagnosisList();
			
			diagnosisForm.setDiagnosislist(diagnosislist);
			
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
	}


	public DiagnosisForm getModel() {
		return diagnosisForm;
	}
	
	
	
	
	
}
