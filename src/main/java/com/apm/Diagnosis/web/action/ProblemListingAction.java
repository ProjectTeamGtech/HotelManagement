package com.apm.Diagnosis.web.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.allcolor.yahp.converter.CYaHPConverter;
import org.allcolor.yahp.converter.IHtmlToPdfTransformer;
import org.apache.struts2.ServletActionContext;

import com.apm.Accounts.eu.bi.AccountsDAO;
import com.apm.Accounts.eu.bi.ChargesAccountProcessingDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAccountsDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCChargeAccountProcesDAO;
import com.apm.Diagnosis.eu.bi.DiagnosisDAO;
import com.apm.Diagnosis.eu.blogic.jdbc.JDBCDiagnosisDAO;
import com.apm.Diagnosis.eu.entity.Diagnosis;
import com.apm.Diagnosis.web.form.DiagnosisForm;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.DiaryManagement.web.action.EmbeddedImageEmailUtil;
import com.apm.Emr.eu.bi.EmrDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCEmrDAO;
import com.apm.Inventory.eu.bi.ProcurementDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCProcurementDAO;
import com.apm.Inventory.eu.entity.Product;
import com.apm.Inventory.eu.entity.Vendor;
import com.apm.Ipd.eu.bi.BedDao;
import com.apm.Ipd.eu.bi.IpdDAO;
import com.apm.Ipd.eu.blogic.jdbc.JDBCBedDao;
import com.apm.Ipd.eu.blogic.jdbc.JDBCIpdDAO;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Log.eu.entity.EmailLetterLog;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Registration.eu.bi.ClinicDAO;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCClinicDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.main.common.constants.Constants;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import atg.taglib.json.util.JSONObject;

public class ProblemListingAction extends BaseAction implements ModelDriven<DiagnosisForm>,Preparable{

	
    DiagnosisForm diagnosisForm=new DiagnosisForm();	 
    HttpServletRequest request=ServletActionContext.getRequest();
    HttpServletResponse response=ServletActionContext.getResponse();
    HttpSession session=request.getSession(false); 
    LoginInfo loginInfo=LoginHelper.getLoginInfo(request);
       
	public DiagnosisForm getModel() {
		// TODO Auto-generated method stub
		return diagnosisForm;
	}

	@Override
	public String execute() throws Exception {
	
		Connection connection=null;
		
		try {
			
			String ipdid=request.getParameter("ipdid");
			String clientid=request.getParameter("clientid");
			
			connection=Connection_provider.getconnection();
			DiagnosisDAO diagnosisDAO=new JDBCDiagnosisDAO(connection);
			
			// Adarsh
			BedDao bedDao = new JDBCBedDao(connection);
			ArrayList<Bed>ipdConditionids = bedDao.getIpdConditionList(ipdid);
			int result=diagnosisDAO.addPatientIllness(ipdid,clientid);
			session.setAttribute("ipdid", ipdid);
			session.setAttribute("clientid",clientid);
		    session.setAttribute("illnessid", result);
			
			/*ArrayList<Diagnosis> diagnosislist=diagnosisDAO.getAllDiagnosisList();*/
		    ArrayList<Diagnosis> diagnosislist=diagnosisDAO.getAllAutoDiagnosisList(ipdConditionids);
			diagnosisForm.setDiagnosislist(diagnosislist);
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		return super.execute();
	}
	
	public String listproblem() throws Exception {
		
		Connection connection=null;
		StringBuffer buffer=new StringBuffer();
		
		try {
			connection=Connection_provider.getconnection();
			DiagnosisDAO diagnosisDAO=new JDBCDiagnosisDAO(connection);
            String data=request.getParameter("count");       			

            String selectedpids=request.getParameter("selectedpids");
            String strarr[]=selectedpids.split(",");
            
            int i=0;
            
            for(String id:data.split(",")) {
            	
                if(i!=0) {
                     ArrayList<Diagnosis> problemlist=diagnosisDAO.getProblemList(id);       
                	 
                     if(problemlist.size()!=0) {
                     
                       String diagnosisname=diagnosisDAO.getNameOfDiagnosisFromId(id);
                	   buffer.append("<div  class='col-lg-12 col-md-12 col-xs-12 col-sm-12' id='count"+i+"'>");
                	   buffer.append("<div class='some-content-related-div'>");
                	   buffer.append("<div class='row martop10'>");
                	   
                	   buffer.append("<div class='col-lg-12 col-md-12'>");
                	   buffer.append("<h4>"+diagnosisname+"</h4>");   
                	   buffer.append("</div>"); 
                	   buffer.append("<table class='table table-bordered' cellspacing='0' width='100%'>");
                	   buffer.append("<thead>");
                	   buffer.append("<tr class='tableback'><th><input type='checkbox' class='dcase"+i+"' onclick='selectAllProb("+i+")'/></th><th>Problem (Step 2 Choose Problems)</th></tr></thead>");
                	   buffer.append("<tbody>");
                	   buffer.append("<input type='hidden' name='conditionid' value='"+id+"'>");
                	   
                	   
                	   for(Diagnosis diagnosis:problemlist) {
                		   
                		      boolean flag=false;
                			  
                			  for(String str:strarr){
                				  int tpid=Integer.parseInt(str);
                				  if(tpid==diagnosis.getId()){
                    				 
                    				  flag=true;
                    				  break;
                    			  } 
                			  }
                			  if(flag){
                				  
                				  buffer.append("<tr>");           
  		                	     buffer.append("<td><input type='checkbox' checked='checked' class='dcase"+i+"' id='ch"+i+"' name='ch"+i+"' value='"+diagnosis.getId()+"' onclick='showproblemstemp(this.value,this)' /></td>");
  		                	     buffer.append("<td>"+diagnosis.getProblem_name()+"</td>");
  		                	     buffer.append("</tr>");
                				  
                			  } else {
                				  buffer.append("<tr>");           
 		                	      buffer.append("<td><input type='checkbox' class='dcase"+i+"' id='ch"+i+"' name='ch"+i+"' value='"+diagnosis.getId()+"' onclick='showproblemstemp(this.value,this)' /></td>");
 		                	      buffer.append("<td>"+diagnosis.getProblem_name()+"</td>");
 		                	      buffer.append("</tr>");
                			  }
                		 
                	   }
                	   buffer.append("</tbody>");
                	   buffer.append("</table>");
                	   buffer.append("</div>");
                	   buffer.append("</div>");
                	   
                	   buffer.append("</div>");
                    }
                }
            	 
               i++;   
            }
            		
 
            response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());
            
		} catch (Exception e) {

            e.printStackTrace();		
		}
		finally {
			connection.close();
		}
		
		return null;
	}
	
	
	public String sendtoconsult() throws Exception{
		
		
		if(!verifyLogin(request)){
			
			return "login";
		}
		
        Connection connection=null;
		
		try {
			
			connection=Connection_provider.getconnection();
			DiagnosisDAO diagnosisDAO=new JDBCDiagnosisDAO(connection);
			UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
           
			Integer illnessid=(Integer)session.getAttribute("illnessid");
		    ArrayList<Diagnosis> list=diagnosisDAO.getIpdIllnessCondition(illnessid);
			StringBuffer buffer=new StringBuffer();
		    buffer.append("<table width='100%'>");
		    buffer.append("<tr>");
		    buffer.append("<td><b>Diagnosis</b></td><td><b>Problem</b></td><td><b>Intervention</b></td>");
		    buffer.append("</tr>");
			for(Diagnosis diagnosis:list) {
				      int x=0;     
				 
				      String conditionname=diagnosisDAO.getNameOfDiagnosisFromId(diagnosis.getConditionid());
				      String problemsid=diagnosis.getProblemid();
				      buffer.append("<tr>"); 
                      buffer.append("<td>"+conditionname+"</td>");
				      
                      for(String str:problemsid.split(",")) {
                  
                    	  int i=0;
                    	  String problem_name=diagnosisDAO.getNameOfDiagnosisProblemNameFromId(str);
                    	  ArrayList<Diagnosis> interventionlist=diagnosisDAO.getDiagnosisIntervention(str);
                    	  if(x==0) {
                    		      
                    		   buffer.append("<td>"+problem_name+"</td>");
                    		   for(Diagnosis diagnosis2:interventionlist) {
                    			   
                                   if(i==0) {                              		 
                          		    buffer.append("<td>"+diagnosis2.getIntervention()+"</td>");
                          		    buffer.append("</tr>");
                                   }
                                   else {
                                  	 buffer.append("<tr>");
                                  	 buffer.append("<tD></td>");
                                  	 buffer.append("<td></td>");
                                  	 buffer.append("<td>"+diagnosis2.getIntervention()+"</td>");
                                  	 buffer.append("</tr>");
                                   }
                                   i++;
                          	  }
                    		      
                    	  }
                    	  else  {
                    		  
                    		  buffer.append("<tr>");
                    		  buffer.append("<td></td>");
                    		  buffer.append("<td>"+problem_name+"</td>");
                   		         for(Diagnosis diagnosis2:interventionlist) {
                   			   
                   		        	 if(i==0) {                              		 
                   		        		 buffer.append("<td>"+diagnosis2.getIntervention()+"</td>");
                   		        		 buffer.append("</tr>");
                   		        	 }
                   		        	 else {
                   		        		 buffer.append("<tr>");
                   		        		 buffer.append("<tD></td>");
                   		        		 buffer.append("<td></td>");
                   		        		 buffer.append("<td>"+diagnosis2.getIntervention()+"</td>");
                   		        		 buffer.append("</tr>");
                   		        	 }
                                  i++;
                         	  }
                    		  
                    	  }
                    	  
                    	  x++;
                    	  
                      }
				 
			}
			buffer.append("</table>");
		    
			String ipdid=(String)session.getAttribute("ipdid");
            BedDao bedDao=new JDBCBedDao(connection);
			Bed bed=bedDao.getEditIpdData(ipdid);
			
			if(bed.getPractitionerid()!=null){
				
				
				if(!bed.getPractitionerid().equals("")){
					
					UserProfile userProfile=userProfileDAO.getUserprofileDetails(Integer.parseInt(bed.getPractitionerid()));
					bed.setConditionid(userProfile.getDiciplineName());
				}
			}
			
			session.setAttribute("practitionerid", bed.getPractitionerid());
			session.setAttribute("condition",bed.getConditionid());
			
			String data=bed.getPractitionerid()+","+bed.getConditionid();
			String currentdate=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			int result=diagnosisDAO.addConsultationNote(bed,buffer.toString(),currentdate); 
          		
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(data);
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			
			connection.close();
		}
		
	
		return null;
	}
	
	
	
	public String save() throws Exception{
		
		if(!verifyLogin(request)) {
			return "login";
		}
		
		Connection connection=null;
		
		try {
			
			 connection=Connection_provider.getconnection();
             DiagnosisDAO diagnosisDAO=new JDBCDiagnosisDAO(connection);
             String conditionid=request.getParameter("conditionid");
             String problemids=request.getParameter("probemids");

             StringBuilder sb=new StringBuilder(); 
             int i=0;

             for(String str:problemids.split(",")) {
                 if(i==0) {
                      
                 }
                 else {
            	   sb.append(str+",");
                 }
            	 i++;
             }
              
             int illnessid=(Integer)session.getAttribute("illnessid");
             
             int result=diagnosisDAO.addConditionsandProblems(String.valueOf(illnessid),conditionid,sb.toString());
             
             session.setAttribute("selectedid",String.valueOf(result));
             
      			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
	
		return null;
	}

	
	public String listintervention() throws Exception {
		
		StringBuffer buffer=new StringBuffer();
		Connection connection=null;
		
		try {
			
			connection=Connection_provider.getconnection();
			DiagnosisDAO diagnosisDAO=new JDBCDiagnosisDAO(connection);
			
			String data=request.getParameter("countintervention");
			int i=0;			
			buffer.append("<table class='table table-bordered' cellspacing='0' width='100%'>");
			buffer.append("<thead>");
			buffer.append("<th>Diagnosis</th><th>Problem</th><th>Intervention</th>");
			buffer.append("</thead>");
			buffer.append("<tbody>");
		    for(String problem_id:data.split(",")) {
		    	
		    	if(i!=0) {
		    		
		    	   //	String diagnosisname=diagnosisDAO.getDiagnosisNamefromProblemID(problem_id);
		    	     
		    		
		    		ArrayList<Diagnosis> interventionlist=diagnosisDAO.getDiagnosisIntervention(problem_id);
		    		
		    		int index=0;
		    		if(interventionlist.size()>0) {
		    		
		    		for(Diagnosis intervention:interventionlist) {
		    		      	
		    		    if(index==0) {
		    		    	buffer.append("<tr>");
		    		    	buffer.append("<td>"+intervention.getDiagnosis_id()+"</td>");
		    		    	buffer.append("<td>"+intervention.getProblem_name()+"</td>");
		    		    	buffer.append("<td>"+intervention.getIntervention()+"</td>");
		    		    	buffer.append("</tr>");
		    		    }
		    		    else {
		    		    	
		    		    	buffer.append("<tr>");
		    		    	buffer.append("<td></td>");
		    		    	buffer.append("<td></td>");
		    		    	buffer.append("<td>"+intervention.getIntervention()+"</td>");
		    		    	buffer.append("</tr>");
		    		    	
		    		    }
		    		
		    		    index++;
		    		}
		    	   }
		    			
		  		}
		    	
		    	i++;
		    	
		    }
			buffer.append("</tbody>");
			buffer.append("</table>");
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		return null;
	}
	
	
	//  05 Aug 2018
	public String clinicalnotes() throws Exception {
		Connection connection=null;
		try {
			
			String ipdid=request.getParameter("ipdid");
			String clientid=request.getParameter("clientid");
			if(ipdid==null){
				ipdid="0";
			}else if(ipdid.equals("")){
				ipdid="0";
			}
			connection=Connection_provider.getconnection();
			DiagnosisDAO diagnosisDAO=new JDBCDiagnosisDAO(connection);
			//int result=diagnosisDAO.addPatientIllness(ipdid,clientid);
			session.setAttribute("ipdid", ipdid);
			session.setAttribute("clientid",clientid);
		    //session.setAttribute("illnessid", result);
			
		    ArrayList<Diagnosis> diagnosislist=diagnosisDAO.getAllClinicalDiagnosisList();
		    diagnosisForm.setDiagnosislist(diagnosislist);
		    diagnosisForm.setLastclinicalnotesid(diagnosislist.get(diagnosislist.size()-1).getId());
		    diagnosisForm.setIpdid(ipdid);
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		return "clinicalnotes";
	}
	
	
	public String setclinicalcareproblem() throws Exception {
		try {

			if (!verifyLogin(request)) {
				return "login";
			}
			Connection connection = null;
			Bed bed = new Bed();
			connection = Connection_provider.getconnection();
			StringBuffer strbuffer = new StringBuffer();

			StringBuilder buffer = new StringBuilder();
			BufferedReader reader = request.getReader();
			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			String data1 = buffer.toString();
			JSONObject jsonObject = new JSONObject(data1);

			String data = jsonObject.getString("totalExpenceCheckbox");
			DiagnosisDAO diagnosisDAO=new JDBCDiagnosisDAO(connection);
			 String diagnosisname ="";
	        int i=0;
	        StringBuffer buffer2 = new StringBuffer();
	        for(String id:data.split(",")) {
	         	
	             if(i!=0) {
	                  ArrayList<Diagnosis> problemlist=diagnosisDAO.getClinicalNotesProblemList(id);       
	                  diagnosisname =diagnosisDAO.getClinicalNotesName(id);
	                 
	              	
	                    buffer2.append("<div  class='col-lg-12 col-md-12 col-xs-12 col-sm-12' id='count"+i+"'>");
	                    buffer2.append("<div class='some-content-related-div'>");
	                    buffer2.append("<div class='row martop10'>");
	             	   	buffer2.append("<p style='padding-left:0px;'><input type='text' name='clnprob' id='clnprob' onkeyup='searchclnprob("+id+")' class='form-control' style='width:80%'  placeholder='Search Problem'></p>");
	                    buffer2.append("<div class='col-lg-12 col-md-12'>");
	                    buffer2.append("<h4>"+diagnosisname+"</h4>");   
	                    buffer2.append("</div>"); 
	                    buffer2.append("<table class='table table-bordered' cellspacing='0' width='100%'>");
	                    buffer2.append("<thead>");
	             	   /*buffer.append("<tr class='tableback'><th><input type='checkbox' class='dcase"+i+"' onclick='selectAllProb("+i+")'/></th><th>Problem (Step 2 Choose Problems)</th></tr></thead>");*/
	             	  //  04 Aug 2018
	                    buffer2.append("<tr class='tableback'><th></th><th>Problem (Step 2 Choose Problems) &nbsp;&nbsp;&nbsp;<i class='fa fa-plus' onclick='addtoproblemmaster()'></i> </th></tr></thead>");
	                    buffer2.append("<tbody id='clinicalproblembody'>");
	                    buffer2.append("<input type='hidden' name='conditionid' value='"+id+"' id='problemparentid'>");
	                
	                    if(problemlist.size()!=0) {
	             	   //lokesh
	             	   for(Diagnosis diagnosis:problemlist) {
	             		      boolean flag=false;
	             			  /*for(String str:strarr){
	             				  int tpid=Integer.parseInt(str);
	             				  if(tpid==diagnosis.getId()){
	                 				 
	                 				  flag=true;
	                 				  break;
	                 			  } 
	             			  }*/
	             			  if(flag){
	             				buffer2.append("<tr>");           
	             				buffer2.append("<td><input type='checkbox' checked='checked' class='dcase' id='ch"+i+"' name='ch"+i+"' value='"+diagnosis.getId()+"' onclick='showClinicalNotesIntervation(this.value)' /></td>");
	             				buffer2.append("<td>"+diagnosis.getProblem_name()+"</td>");
	             				buffer2.append("</tr>");
	             			  } else {
	             				 buffer2.append("<tr>");           
			                	      /*buffer.append("<td><input type='checkbox' class='dcase"+i+"' id='ch"+i+"' name='ch"+i+"' value='"+diagnosis.getId()+"' onclick='showproblemstemp(this.value,this)' /></td>");*/
	             				buffer2.append("<td><input type='checkbox' class='dcase' id='ch"+i+"' name='ch"+i+"' value='"+diagnosis.getId()+"' onclick='showClinicalNotesIntervation(this.value)' /></td>");
	             				buffer2.append("<td>"+diagnosis.getProblem_name()+"</td>");
	             				buffer2.append("</tr>");
	             			  }
	             		 
	             	   }
	             	  buffer2.append("</tbody>");
	             	 buffer2.append("</table>");
	             	buffer2.append("</div>");
	             	buffer2.append("</div>");
	             	   
	             	buffer2.append("</div>");
	                 }
	             }
	         	 
	            i++;   
	         }
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("problemdiagnosis", buffer2.toString());
			jsonobj.put("diagnosisname",diagnosisname);
			
			
			String response1 = jsonobj.toString();
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(response1);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}
	
	
	public String setclinicalnotesitervention() throws Exception {
		try {

			if (!verifyLogin(request)) {
				return "login";
			}
			Connection connection = null;
			Bed bed = new Bed();
			connection = Connection_provider.getconnection();
			StringBuffer strbuffer = new StringBuffer();

			StringBuilder buffer = new StringBuilder();
			BufferedReader reader = request.getReader();
			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			String data1 = buffer.toString();
			JSONObject jsonObject = new JSONObject(data1);

			String data = jsonObject.getString("totalExpenceCheckbox");
			DiagnosisDAO diagnosisDAO=new JDBCDiagnosisDAO(connection);
			 String diagnosisname ="";
	        int i=0;
	        StringBuffer buffer2 = new StringBuffer();
	        for(String id:data.split(",")) {
	         	
	             if(i!=0) {
	                  ArrayList<Diagnosis> problemlist=diagnosisDAO.getClinicalNotesInterventionList(id);
	                  
	                  
	                	diagnosisname =diagnosisDAO.getClinicalNotesProblemName(id);
	                    buffer2.append("<div  class='col-lg-12 col-md-12 col-xs-12 col-sm-12' id='count"+i+"'>");
	                    buffer2.append("<div class='some-content-related-div'>");
	                    buffer2.append("<div class='row martop10'>");
	                	buffer2.append("<p style='padding-left:0px;'><input type='text' name='clnintr' id='clnintr' onkeyup='searchclnintr("+id+")' class='form-control' style='width:80%' placeholder='Search Intervention'></p>");
	                    buffer2.append("<div class='col-lg-12 col-md-12'>");
	                    buffer2.append("<h4>"+diagnosisname+"</h4>");   
	                    buffer2.append("</div>"); 
	                    buffer2.append("<table class='table table-bordered' cellspacing='0' width='100%'>");
	                    buffer2.append("<thead>");
	             	   /*buffer.append("<tr class='tableback'><th><input type='checkbox' class='dcase"+i+"' onclick='selectAllProb("+i+")'/></th><th>Problem (Step 2 Choose Problems)</th></tr></thead>");*/
	             	  //  04 Aug 2018
	                    buffer2.append("<tr class='tableback'><th></th><th>Intervention (Step 3 Choose Intervention) &nbsp;&nbsp;&nbsp;<i class='fa fa-plus' onclick='addtoclinicalintervationmaster()'></i></th></tr></thead>");
	                  
	                    buffer2.append("<tbody id='intervationbody'>");
	                    buffer2.append("<input type='hidden' name='intervationparent' value='"+id+"' id='intervationparent'>");
	                    if(problemlist.size()!=0) {
	                   //lokesh
	                    
	                   /* buffer.append("<input type='hidden' id='intervationparent' name='intervationparent' value='"+id+"'>");*/
	             	   for(Diagnosis diagnosis:problemlist) {
	             		      boolean flag=false;
	             			  if(flag){
	             				buffer2.append("<tr>");           
	             				buffer2.append("<td><input type='checkbox' checked='checked' class='akashtest' id='ch"+i+"' name='ch"+i+"' value='"+diagnosis.getId()+"' onclick='setClinicalIntervation(this.value)' /></td>");
	             				buffer2.append("<td>"+diagnosis.getIntervention()+"</td>");
	             				buffer2.append("</tr>");
	             			  } else {
	             				 buffer2.append("<tr>");           
			                	      /*buffer.append("<td><input type='checkbox' class='dcase"+i+"' id='ch"+i+"' name='ch"+i+"' value='"+diagnosis.getId()+"' onclick='showproblemstemp(this.value,this)' /></td>");*/
	             				buffer2.append("<td><input type='checkbox' class='akashtest' id='ch"+i+"' name='ch"+i+"' value='"+diagnosis.getId()+"' onclick='setClinicalIntervation(this.value)' /></td>");
	             				buffer2.append("<td>"+diagnosis.getIntervention()+"</td>");
	             				buffer2.append("</tr>");
	             			  }
	             		 
	             	   } }
	             	  buffer2.append("</tbody>");
	             	 buffer2.append("</table>");
	             	buffer2.append("</div>");
	             	buffer2.append("</div>");
	             	   
	             	buffer2.append("</div>");
	                
	             }
	         	 
	            i++;   
	         }
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("problemdiagnosis", buffer2.toString());
			jsonobj.put("diagnosisname",diagnosisname);
			
			
			String response1 = jsonobj.toString();
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(response1);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}
	
	
	public String setiterventionnotes() throws Exception {
		try {

			if (!verifyLogin(request)) {
				return "login";
			}
			Connection connection = null;
			connection = Connection_provider.getconnection();
			
			StringBuilder buffer = new StringBuilder();
			BufferedReader reader = request.getReader();
			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			String data1 = buffer.toString();
			JSONObject jsonObject = new JSONObject(data1);

			String data = jsonObject.getString("totalExpenceCheckbox");
			DiagnosisDAO diagnosisDAO=new JDBCDiagnosisDAO(connection);
			 String diagnosisname ="";
	        int i=0;
	        for(String id:data.split(",")) {
	         	 if(i!=0) {
	                  diagnosisname =diagnosisDAO.getClinicalNotesIntervation(id);
	             }
	         	i++;   
	         }
			
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("diagnosisname",diagnosisname);
			
			
			String response1 = jsonobj.toString();
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(response1);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}
	
	public String saveclinicalnotes() throws Exception {
		try {

			if (!verifyLogin(request)) {
				return "login";
			}
			
			
			Connection connection = null;
			connection = Connection_provider.getconnection();
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			
			connection = Connection_provider.getconnection();
			connection=DriverManager.getConnection(""+Constants.DB_HOST+":3306/"+loginInfo.getClinicUserid()+"?useUnicode=true&characterEncoding=UTF-8",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");

			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			StringBuilder buffer = new StringBuilder();
			request.setCharacterEncoding("UTF-8");
			BufferedReader reader = request.getReader();
			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			String data1 = buffer.toString();
			JSONObject jsonObject = new JSONObject(data1);

			
			String maintextarea = new String(jsonObject.getString("maintextarea"));
			DiagnosisDAO diagnosisDAO=new JDBCDiagnosisDAO(connection);
			
	        String ipdid=(String)session.getAttribute("ipdid");
            BedDao bedDao=new JDBCBedDao(connection);
			Bed bed=bedDao.getEditIpdData(ipdid);
			
			if(bed.getPractitionerid()!=null){
				
				
				if(!bed.getPractitionerid().equals("")){
					
					UserProfile userProfile=userProfileDAO.getUserprofileDetails(Integer.parseInt(bed.getPractitionerid()));
					bed.setConditionid(userProfile.getDiciplineName());
				}
			}
			
			
			ClientDAO clientDAO= new JDBCClientDAO(connection);
			String clientId= request.getParameter("clientname");
			
			clientId=(String)session.getAttribute("clientid");
			
			if(ipdid==null||ipdid.equals("")||ipdid.equals("0")){
				
					NotAvailableSlot notAvailableSlot = clientDAO.getLastAppointmentdetails(clientId);
					bed.setPractitionerid(String.valueOf(notAvailableSlot.getDiaryUserId()));
					bed.setConditionid(notAvailableSlot.getCondition());
					bed.setClientid(clientId);
					
				}
			session.setAttribute("practitionerid", bed.getPractitionerid());
			session.setAttribute("condition",bed.getConditionid());
			
			//String data=bed.getPractitionerid()+","+bed.getConditionid();
			String currentdate=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			int result=diagnosisDAO.addConsultationNote(bed,maintextarea,currentdate); 
	        
			ChargesAccountProcessingDAO chargesAccountProcessingDAO = new JDBCChargeAccountProcesDAO(connection);
			String appoitmentid = (String)session.getAttribute("videoconfappoitmentid");
			int res = chargesAccountProcessingDAO.getMobStatusFromAppoitmentId(appoitmentid,clientId);
			if(res==1){
				sendMailtoPatientOfClinicalNotes(clientId,bed.getPractitionerid(),bed.getConditionid(),maintextarea);
			}
			
			
	        
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("practiidd",bed.getPractitionerid());
			jsonobj.put("condiiid",bed.getConditionid());
			
			String response1 = jsonobj.toString();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(response1);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}
	
	
	private void sendMailtoPatientOfClinicalNotes(String clientId, String practitionerid, String conditionid, String maintextarea) {
		Connection connection = null;
		try {
			practitionerid = DateTimeUtils.numberCheck(practitionerid);
			connection = Connection_provider.getconnection();
			connection=DriverManager.getConnection(""+Constants.DB_HOST+":3306/"+loginInfo.getClinicUserid()+"?useUnicode=true&characterEncoding=UTF-8",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			EmrDAO emrDAO = new JDBCEmrDAO(connection);
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			
			UserProfile userProfile=userProfileDAO.getUserprofileDetails(Integer.parseInt(practitionerid));
			UserProfile userProfile1 = userProfileDAO.getUserprofileDetails(loginInfo.getClinicid());
			String currentdate=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			Client client = clientDAO.getClientDetails(clientId);
			String fullname = client.getTitle() + "." + client.getFirstName() + " " + client.getLastName();
			String date1 = DateTimeUtils.getCommencingDate1(currentdate.split(" ")[0]);
			StringBuffer buffer = new StringBuffer();
			buffer.append("<style>");
			buffer.append(".padright {padding-left: 40px;}");
			buffer.append(".table.table {color: RGBA(85, 85, 85, 0.85);background-color: #fff;}");
			buffer.append(".comtitle { font-size: 13px; background: rgb(102, 153, 204) none repeat scroll 0% 0% !important; color: rgb(255, 255, 255);}");
			buffer.append(" .marbot25 {margin-bottom: 25px;}");
			buffer.append(".editcompany {float: right;font-size: 17px; color: #fff; }");
			buffer.append(".borright {border-right: 1px dashed rgb(192, 192, 192);}");
			buffer.append(".buildinglogo { width: 60%;margin-top: 30px;}");
			buffer.append("#sidebar .panel-group .panel > .panel-heading + .panel-collapse > .panel-body {border-top: 0;min-height: auto !important;}");
			buffer.append(".miheight {min-height: auto !important;}");
			buffer.append(" .my-table th {background-color: #424A5D;color: #fff !important;border-bottom: 1px solid #DFD8D4;border-right: 1px solid #DFD8D4;");
			buffer.append(" border-top: 1px solid #DFD8D4;padding: 3px 3px 4px 5px;text-align: left;font-weight: bold;font-size: 11px;background-size: 100% 100%;}");
			buffer.append(".table > tbody > tr > td, .table > tbody > tr > th, .table > tfoot > tr > td, .table > tfoot > tr > th, .table > thead > tr > td, .table > thead > tr > th {");
			buffer.append("padding: 1px 7px 1px 7px !important;}");
			buffer.append(".sidebar-xs #header .branding > a {background-position: 6px 10px;width: 100% !important;font-size: 21px;padding: 0px 1px 2px 15px;text-align: center;color: #fff;}");
			buffer.append(".sidebar-xs #header .branding > a > span { display: inline-block;}");
			buffer.append(".sidebar-xs #header .branding {width: 100%;padding-top: 7px;text-align: center;}");
			buffer.append(".theight {height: 21px;}");
			buffer.append(" </style>");
			buffer.append("<style>");
			buffer.append(".loc{background-color: #6699cc;color: white;}");
			buffer.append("@media print {.loc{background-color: #6699cc  !important;color: white;}}");
			buffer.append("</style>");
			
			buffer.append("<style>");
			buffer.append(".tableback{background-color: #6699cc;color: white;}");
			buffer.append("@media print {.tableback{background-color: #6699cc  !important;color: white;}}");
			buffer.append("</style>");
			
			buffer.append("<style>");
			buffer.append(".logoste{width: 100%;margin-left:auto;margin-right:auto;}");
			buffer.append(".logoste1{width: 100%;margin-left:auto;margin-right:auto;padding-right: 0px;}");
			buffer.append(".bghlogo{width: 147px !important;margin-top: -33px !important;}");
			buffer.append(".bghlogo1{width: 147px !important;margin-top: -33px !important;}");
			buffer.append("@media print {.bghlogo{width: 13% !important;margin-top: -5px !important;}");
			buffer.append(".bghlogo1{width: 18% !important;margin-top: -5px !important;padding-top: 10px !important;}");
			buffer.append(".logoste1 {margin-top: 6px !important;}");
			buffer.append("#ltterimg{  margin-left: -11px ;}");
			buffer.append(".logowidth {width: 150% !important;}}");
			buffer.append("@media (orientation: landscape) {.bghlogo{width: 9% !important;margin-top: -5px !important;}");
			buffer.append("</style>");
			
			buffer.append("<body>");
			
			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
			String logofilePath = request.getRealPath("/liveData/clinicLogo/");	
			buffer.append("<div class='col-lg-12 col-md-12 col-xs-12 col-sm-12' id='ltterimg'>");
			
			buffer.append("<div class='col-lg-2 col-md-2 col-sm-2 col-xs-2 logoimg'  id='mainlogoclinic'>");
			buffer.append("<div id='newletter' >");
			buffer.append("<img class='img-responsive logoste1' src='"+logofilePath+""+clinic.getUserImageFileName()+"' />");
			buffer.append("</div>");
			buffer.append("</div>");
			
			buffer.append("<div class='col-lg-10 col-md-10 col-sm-10 col-xs-10' id='lttertext'>");
			
			buffer.append("<div class='clinicname' id='clinicnamenew' align='center'>");
			buffer.append(" "+clinic.getClinicName()+"");
			buffer.append("</div>");
			
			buffer.append("<div class='clicniaddress' >");
			for (Clinic clinic2 : locationAdressList) {
				buffer.append(""+clinic2.getAddress()+"");
			}
			buffer.append("</div>");
			
			buffer.append("<div class='bottext'>Phone:"+clinic.getLandLine()+",	 Email: "+clinic.getEmail()+"");
			buffer.append(""+clinic.getOwner_qualification()+"");
			
			/*if(!DateTimeUtils.isNull(clinic.getWebsiteUrl()).equals("")){
				buffer.append("<span style='white-space: nowrap;'>Website: "+clinic.getWebsiteUrl()+"</span>");
			}*/
			buffer.append("</div>");
			
			buffer.append("</div>");
			buffer.append("</div>");
			
			buffer.append("<h4 align='center'>PRESCRIPTION</h4><br>");
			buffer.append("<span><b>Doctor's Name :</b></span><span> </span><span>"+userProfile.getFullname()+"</span><br>");
			buffer.append("<span><b>Qualification :</b></span><span> </span><span>"+DateTimeUtils.isNull(userProfile.getQualification())+"</span><br>");
			buffer.append("<span><b>Maharashtra Medical Council Reg. No. (ALLOPATHY) :</b></span><span> </span><span>MAHA525252</span><br>");
			buffer.append("<span><b>Full Address :</b></span><span> </span><span>"+userProfile1.getAddress()+"</span><br>");
			buffer.append("<span><b>Telephone No.(Clinc):</b>&nbsp;"+userProfile1.getPhone()+"</span><br>");
			buffer.append("<span><b>Mobile No.:</b>&nbsp;"+DateTimeUtils.isNull(userProfile.getMobile())+"</span><br>");
			buffer.append("<span><b>Email Id :</b></span><span> </span><span>"+DateTimeUtils.isNull(userProfile.getEmail())+"</span><br>");
			buffer.append("<span style='float: right;'><b>Date :</b> &nbsp; "+date1+"</span><br><br>");
			
			buffer.append("<span><b>Patient Name :</b>&nbsp;"+fullname+"</span><br>");
			buffer.append("<span><b>Address* :</b>&nbsp;"+DateTimeUtils.isNull(client.getAddress())+"</span><br>");
			buffer.append("<span><b>Age :</b>&nbsp;"+client.getAge1()+"</span><br><span><b>Sex* :</b>&nbsp;"+client.getGender()+"</span><br><span><b>Weight :</b>&nbsp;-</span><br>");
			buffer.append("<span><b>Rx</b></span><br>");
			
			/*buffer.append("<table  class='table' >");
			buffer.append("<tr class='loc' align='right'>");
			buffer.append("<th style='text-align:center !important'>Sr. No</th> ");
			buffer.append("<th style='text-align:center !important'>Drug Name</th> ");
			buffer.append("<th style='text-align:center !important'>Dose</th> ");
			buffer.append("<th style='text-align:center !important'>Duration</th> ");
			buffer.append("<th style='text-align:center !important'>Routes</th>");
			buffer.append("<th style='text-align:center !important'>Frequency</th> ");
			buffer.append("<th style='text-align:center !important'>Remark</th> ");
			buffer.append("</tr>");
			
			ArrayList<Priscription>priscList = new ArrayList<Priscription>();
			if (session.getAttribute("newpriscmednewparentid") != null) {
				String newpriscmednewparentid = (String) session.getAttribute("newpriscmednewparentid");
				for (String mdicinenameid : newpriscmednewparentid.split(",")) {
					if (mdicinenameid.equals("0")) {
						continue;
					}
					priscList = emrDAO.getPrintPriscList(mdicinenameid,loginInfo);
					int i =0;
					for(Priscription priscription : priscList){
						buffer.append("<tr>");
						buffer.append("<td>"+(++i)+"</td>");
						buffer.append("<td>"+priscription.getMdicinenametxt()+"</td>");
						//String regional = emrDAO.getregionalText(priscription.getPriscdose());
						//buffer.append("<td>"+regional.toString()+"</td>");
						buffer.append("<td>"+priscription.getPriscdose()+"</td>");
						if(!priscription.getPriscdays().equals("")){
							buffer.append("<td>"+priscription.getPriscdays()+" days "+priscription.getPriscdurationtype()+"</td>");
						}else{
							buffer.append("<td>"+priscription.getPriscdays()+"  "+priscription.getPriscdurationtype()+"</td>");
						}
						buffer.append("<td>"+priscription.getDosenotes().toString()+"</td>");
						buffer.append("<td>"+priscription.getPrisctimename()+"</td>");
						buffer.append("<td>"+priscription.getPriscindivisualremark()+"</td>");
						buffer.append("</tr>");
					}
				}
			}
			buffer.append("</table><br><br>");*/
			
			buffer.append(""+maintextarea+"");
			buffer.append("<br><br>");
			
			String newdate = date1+" "+currentdate.split(" ")[1];
			MasterDAO masterDAO= new JDBCMasterDAO(connection);
			UserProfile uf=masterDAO.userSignature(practitionerid);
			UserProfile uf1=masterDAO.userSignature(""+loginInfo.getClinicid());
			
			buffer.append("<img class='dd' src ='liveData/signature/"+uf.getFilename()+"' alt='' style='float:right;'><br>");
			buffer.append(" <span style='float:right;'>"+newdate+"</span><br>");
			buffer.append("<span style='float:right;'>Doctor's Signature & Date</span><br>");
			buffer.append("<img class='dd' src ='liveData/signature/"+uf1.getFilename()+"' alt='' style='float:right;' >");
			buffer.append("</body>");
			
			String filePath = request.getRealPath("/liveData/document/");
			String filename3 = "Clinical_" + clientId + "_" + practitionerid + "prisc.pdf";
			htmlToPdfFile(buffer.toString(), filePath, filename3);
			
			String attachfile = filePath + "/" + filename3;
			
			int loginId = loginInfo.getId();
			String to = client.getEmail();
			//String to = "akashjamgade5594@gmail.com";
			String cc = "";
			String subject = "Prescription";
			String notes = "";
			String filename = attachfile;
			String[] temp1 = filename.split("/");
			String filename1 = temp1[1];
			int procurementid1 = 0;
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			String d1 = dateFormat.format(date);
			String[] temp = d1.split("\\s+");
			String time = temp[1];

			String type = "";

			// set deliver status
			String status = "Email";
			
			EmailLetterLog emailLetterLog = new EmailLetterLog();

			emailLetterLog.setType(status);

			EmbeddedImageEmailUtil.sendMailAttachment(connection, loginId, to, cc, subject, notes, filename, loginInfo,
					emailLetterLog, "0");
			
			int result = accountsDAO.saveEmailLogDetails(to, cc, subject, notes, filename1, procurementid1,
					DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()), time, type);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void htmlToPdfFile(String htmlString, String filepath, String fileaName) throws Exception {

		try {

			CYaHPConverter converter = new CYaHPConverter();
			File fout = new File(filepath + "/" + fileaName);
			FileOutputStream out = new FileOutputStream(fout);
			Map properties = new HashMap();
			ArrayList headerFooterList = new ArrayList();

			properties.put(IHtmlToPdfTransformer.PDF_RENDERER_CLASS, IHtmlToPdfTransformer.FLYINGSAUCER_PDF_RENDERER);
			// properties.put(IHtmlToPdfTransformer.FOP_TTF_FONT_PATH,
			// fontPath);
			converter.convertToPdf(htmlString, IHtmlToPdfTransformer.A4P, (java.util.List) headerFooterList,
					"file:///temp/", // root for
										// relative
										// external
										// CSS and
										// IMAGE
					out, properties);
			out.flush();
			out.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public String saveclinicalnotesbyajax(){
		Connection connection= null;
		String mastername= request.getParameter("master");
		try {
			connection= Connection_provider.getconnection();
			MasterDAO masterDAO= new JDBCMasterDAO(connection);
			int x=masterDAO.saveclinicalnotesmstr(mastername);
			DiagnosisDAO diagnosisDAO= new JDBCDiagnosisDAO(connection);
			ArrayList<Diagnosis> diagnosislist=diagnosisDAO.getAllClinicalDiagnosisList();
			StringBuffer buffer= new StringBuffer();
			
			for(Diagnosis dia:diagnosislist){
				buffer.append("<tr>");
				if(dia.getAutodiagnosis()==1){
					buffer.append("<td>");
					buffer.append(" <input type='checkbox' checked='checked' class='case' id='ch"+dia.getId()+"' name='ch"+dia.getId()+"' value='"+dia.getId()+"'");
					buffer.append(" onclick='showclinicalnotesproblem(this.value)' />  ");
					buffer.append("</td>");
				}else{
					buffer.append("<td>");
					buffer.append(" <input type='checkbox'  class='case' id='ch"+dia.getId()+"' name='ch"+dia.getId()+"' value='"+dia.getId()+"'");
					buffer.append(" onclick='showclinicalnotesproblem(this.value)' />  ");
					buffer.append("</td>");
				}
				buffer.append("<td>"+dia.getName()+"</td>");
				buffer.append("</tr>");	
			}
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());
							
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String saveclinicalproblembyajax(){
		String mastername= request.getParameter("master");
		String parentid= request.getParameter("parentid");
		Connection connection= null;
		try {
			connection= Connection_provider.getconnection();
			MasterDAO masterDAO=new JDBCMasterDAO(connection);
			masterDAO.saveclinicalproblemmster(mastername, parentid);
			DiagnosisDAO diagnosisDAO = new JDBCDiagnosisDAO(connection);
			  ArrayList<Diagnosis> problemlist=diagnosisDAO.getClinicalNotesProblemList(parentid); 
			  StringBuffer buffer2=new StringBuffer();
			  int i=Integer.parseInt(parentid);
			  buffer2.append("<input type='hidden' name='conditionid' value='"+parentid+"' id='problemparentid'>");
			  for(Diagnosis diagnosis:problemlist){
				  buffer2.append("<tr>");           
	    	      /*buffer.append("<td><input type='checkbox' class='dcase"+i+"' id='ch"+i+"' name='ch"+i+"' value='"+diagnosis.getId()+"' onclick='showproblemstemp(this.value,this)' /></td>");*/
				buffer2.append("<td><input type='checkbox' class='dcase' id='ch"+i+"' name='ch"+i+"' value='"+diagnosis.getId()+"' onclick='showClinicalNotesIntervation(this.value)' /></td>");
				buffer2.append("<td>"+diagnosis.getProblem_name()+"</td>");
				buffer2.append("</tr>");
				  
			  }
			  
			  response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(buffer2.toString());

					
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
public String saveclinicalintervationbyajax(){
	String mastername= request.getParameter("master");
	String parentid= request.getParameter("parentid");
	Connection connection= null;
	try {
		connection= Connection_provider.getconnection();
		MasterDAO masterDAO=new JDBCMasterDAO(connection);
		masterDAO.saveclinicalintervation(mastername, parentid);
		DiagnosisDAO diagnosisDAO = new JDBCDiagnosisDAO(connection);
		 ArrayList<Diagnosis> problemlist=diagnosisDAO.getClinicalNotesInterventionList(parentid);
		StringBuffer buffer2= new StringBuffer();
		int i=Integer.parseInt(parentid);
		buffer2.append("<input type='hidden' name='intervationparent' value='"+i+"' id='intervationparent'>");
		for(Diagnosis diagnosis:problemlist){
			 buffer2.append("<tr>");           
		      /*buffer.append("<td><input type='checkbox' class='dcase"+i+"' id='ch"+i+"' name='ch"+i+"' value='"+diagnosis.getId()+"' onclick='showproblemstemp(this.value,this)' /></td>");*/
		buffer2.append("<td><input type='checkbox' class='akashtest' id='ch"+i+"' name='ch"+i+"' value='"+diagnosis.getId()+"' onclick='setClinicalIntervation(this.value)' /></td>");
		buffer2.append("<td>"+diagnosis.getIntervention()+"</td>");
		buffer2.append("</tr>");
		}
	
		  response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer2.toString());
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}	
	

public String addtoothertemplatebyajax(){
	String template= request.getParameter("templatename");
	String text=request.getParameter("text33");
	String ipdid= request.getParameter("ipdid");
	
	String clientid="";
	
	
	clientid= (String)session.getAttribute("clientid");
	Connection connection= null;
	try {
		
		

		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		String data1 = buffer.toString();
		JSONObject jsonObject = new JSONObject(data1);

		
		template = jsonObject.getString("templatename");
		text= jsonObject.getString("text");
		ipdid= jsonObject.getString("ipdid");
		if(ipdid==null){
			ipdid="0";
		}
		if(ipdid.equals("")){
			ipdid="0";
		}
		
		connection= Connection_provider.getconnection();
        Master master=new Master();
        master.setTitle(template);
        master.setOther_template_text(text);
       
        UserProfile userProfile3= new UserProfile();
        UserProfileDAO userProfileDAO= new  JDBCUserProfileDAO(connection);
        ClientDAO clientDAO= new JDBCClientDAO(connection);
        if(ipdid.equals("0")){
        	
        	NotAvailableSlot notAvailableSlot = clientDAO.getLastAppointmentdetails(clientid);
			userProfile3= userProfileDAO.getUserprofileDetails(notAvailableSlot.getDiaryUserId());
			master.setDiscipline_id(userProfile3.getDiciplineName());
        }else{
        	  BedDao bedDao = new JDBCBedDao(connection);
              Bed bed4 = bedDao.getEditIpdData(ipdid);
              userProfile3=userProfileDAO.getUserprofileDetails(Integer.parseInt(bed4.getPractitionerid()));
        }
       
        
        
        master.setDiscipline_id(userProfile3.getDiciplineName());
        
        MasterDAO masterDAO=new JDBCMasterDAO(connection);
        masterDAO.addOtherTemplate(master); 
        
        JSONObject jsonobj = new JSONObject();
        jsonobj.put("", "");
        response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(jsonobj.toString());

	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}
	

public String searchcln(){
	Connection connection= null;
	try {
		connection= Connection_provider.getconnection();
		String name=request.getParameter("master");
		DiagnosisDAO diagnosisDAO= new JDBCDiagnosisDAO(connection);
		ArrayList<Diagnosis> list= new ArrayList<Diagnosis>();
		list=diagnosisDAO.getAllClinicalNotes(name);
		StringBuffer buffer= new  StringBuffer();
		for(Diagnosis dia:list){
			buffer.append("<tr>");
			if(dia.getAutodiagnosis()==1){
				buffer.append("<td>");
				buffer.append(" <input type='checkbox' checked='checked' class='case' id='ch"+dia.getId()+"' name='ch"+dia.getId()+"' value='"+dia.getId()+"'");
				buffer.append(" onclick='showclinicalnotesproblem(this.value)' />  ");
				buffer.append("</td>");
			}else{
				buffer.append("<td>");
				buffer.append(" <input type='checkbox'  class='case' id='ch"+dia.getId()+"' name='ch"+dia.getId()+"' value='"+dia.getId()+"'");
				buffer.append(" onclick='showclinicalnotesproblem(this.value)' />  ");
				buffer.append("</td>");
			}
			buffer.append("<td>"+dia.getName()+"</td>");
			buffer.append("</tr>");	
		}
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(buffer.toString());
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

public String searchprobcln(){
	Connection connection= null;
	try {
		connection= Connection_provider.getconnection();
		String name=request.getParameter("master");
		String parent=request.getParameter("parentid");
		DiagnosisDAO diagnosisDAO= new JDBCDiagnosisDAO(connection);
		ArrayList<Diagnosis> list= new ArrayList<Diagnosis>();
		list=diagnosisDAO.getAllClinicalNotesProblemSearch(name,parent);
		  StringBuffer buffer2=new StringBuffer();
		  int i=Integer.parseInt(parent);
		  buffer2.append("<input type='hidden' name='conditionid' value='"+parent+"' id='problemparentid'>");
		  for(Diagnosis diagnosis:list){
			  buffer2.append("<tr>");           
    	      /*buffer.append("<td><input type='checkbox' class='dcase"+i+"' id='ch"+i+"' name='ch"+i+"' value='"+diagnosis.getId()+"' onclick='showproblemstemp(this.value,this)' /></td>");*/
			buffer2.append("<td><input type='checkbox' class='dcase' id='ch"+i+"' name='ch"+i+"' value='"+diagnosis.getId()+"' onclick='showClinicalNotesIntervation(this.value)' /></td>");
			buffer2.append("<td>"+diagnosis.getProblem_name()+"</td>");
			buffer2.append("</tr>");
			  
		  }
		  
		  	response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer2.toString());

				

	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

public String searchintrcln(){
	Connection connection= null;
	try{
	connection= Connection_provider.getconnection();
	String name=request.getParameter("master");
	String parent=request.getParameter("parentid");
	DiagnosisDAO diagnosisDAO= new JDBCDiagnosisDAO(connection);
	ArrayList<Diagnosis> list= new ArrayList<Diagnosis>();
	list=diagnosisDAO.getAllClinicalNotesIntervationSearch(name, parent);
	
	StringBuffer buffer2= new StringBuffer();
	int i=Integer.parseInt(parent);
	buffer2.append("<input type='hidden' name='intervationparent' value='"+i+"' id='intervationparent'>");
	for(Diagnosis diagnosis:list){
		 buffer2.append("<tr>");           
	      /*buffer.append("<td><input type='checkbox' class='dcase"+i+"' id='ch"+i+"' name='ch"+i+"' value='"+diagnosis.getId()+"' onclick='showproblemstemp(this.value,this)' /></td>");*/
	buffer2.append("<td><input type='checkbox' class='akashtest' id='ch"+i+"' name='ch"+i+"' value='"+diagnosis.getId()+"' onclick='setClinicalIntervation(this.value)' /></td>");
	buffer2.append("<td>"+diagnosis.getIntervention()+"</td>");
	buffer2.append("</tr>");
	}

	  response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(buffer2.toString());
	
	}catch(Exception e){
		e.printStackTrace();
	}
	return null;
}

    public void prepare() throws Exception {
		
	}
	
}
