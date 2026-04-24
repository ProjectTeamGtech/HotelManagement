package com.apm.Emr.web.action;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.Emr.eu.bi.EmrDAO;
import com.apm.Emr.eu.bi.NursingDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCEmrDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCNursingDAO;
import com.apm.Emr.eu.entity.Investigation;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.web.form.MasterForm;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class NursingAction extends BaseAction implements ModelDriven<MasterForm>,Preparable{

    MasterForm masterForm=new MasterForm();
	HttpServletRequest request=ServletActionContext.getRequest();
	HttpServletResponse response=ServletActionContext.getResponse(); 
    HttpSession session=request.getSession(false);
    LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
    
	
	public MasterForm getModel() {
		return masterForm;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}
	
	
	public String getrecord(){
		
		Connection connection=null;
		
		try {
			connection=Connection_provider.getconnection();
			ClientDAO clientDAO=new JDBCClientDAO(connection);
			UserProfileDAO profileDAO=new JDBCUserProfileDAO(connection);
			String clientId=request.getParameter("clientId");
			String practitionerid=request.getParameter("prectionerid");
			String conditionid=request.getParameter("conditionid");
				
			StringBuffer buffer=new StringBuffer();
			
			Client client=clientDAO.getClientDetails(clientId);
			UserProfile userProfile=profileDAO.getUserprofileDetails(Integer.parseInt(practitionerid));
			String fulldaString="Create Nursing Care For "+client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
			buffer.append(""+fulldaString+" | AGE:"+client.getAge()+" | "+client.getGender()+"~");
			

			String date=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			String str[]=date.split(" ");
			buffer.append(""+str[0]+" "+str[1]+"~");
			buffer.append(userProfile.getFirstname()+" "+userProfile.getLastname());
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+buffer.toString()+""); 
			
			
		} catch (Exception e) {

		   e.printStackTrace();
		 }
		
		
		return null;
	}
	
	public String addtemp(){
		
		//  var url="addtempNursing?clientid="+clientid+"&nursingtype_id="+nursingtype_id+"&taskname="+taskname+"&freq="+freq+"&nursingcdays="+nursingcdays+"&notes="+notes+"";
		
		Connection connection=null;
		String nursingtype_id = request.getParameter("nursingtype_id");
		String taskname = request.getParameter("taskname");
		String freq = request.getParameter("freq");
		String nursingcdays = request.getParameter("nursingcdays");
		String notes = request.getParameter("notes");
		
		String clientId = request.getParameter("clientid");
		
		
		
		Master master = new Master();
		
		ArrayList<Master>nursingList = new ArrayList<Master>();
		if(session.getAttribute("nursingList")!=null){
			nursingList = (ArrayList<Master>)session.getAttribute("nursingList");
		}
		
			
		try{
			connection=Connection_provider.getconnection();
			
			String datetime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			MasterDAO masterDAO=new JDBCMasterDAO(connection);
			Master master2=masterDAO.getNursingCategory(nursingtype_id);
			master.setClientid(clientId);
			master.setNursingtype_id(nursingtype_id);
			master.setTaskname(taskname);
			master.setFrequency(freq);
			master.setDays(Integer.parseInt(nursingcdays));
			master.setDatetime(datetime);
			master.setNursingtype(master2.getName());
			master.setNotes(notes);
			
			nursingList.add(master);
			
			session.setAttribute("nursingList", nursingList);
			
			getNursingData();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	

	
	public void getNursingData() {
		
		try {
		
			StringBuffer str = new StringBuffer();
			
			ArrayList<Master>nursingList = (ArrayList<Master>)session.getAttribute("nursingList");
			
			int i = 0;
			for(Master master  : nursingList){
				str.append("<tr>");
				/*str.append("<td>"+priscription.getDate()+"</td/>");*/
				
				str.append("<td>"+master.getNursingtype()+"</td/>");
				/*str.append("<td>"+investigation.getInvstgroup()+"</td/>");*/
				str.append("<td>"+master.getTaskname()+"</td/>");
				str.append("<td>"+master.getFrequency()+"</td/>");
				str.append("<td>"+master.getDays()+"</td/>");
			/*	str.append("<td>"+master.getNotes()+"</td/>");*/
				
				/*str.append("<td><i onclick='showedit("+i+")' class='fa fa-edit' ></i></td/>");*/
				str.append("<td><i onclick='deletenursingdata("+i+")' style='cursor: pointer;' class='fa fa-trash-o' ></i></td/>");
				str.append("</tr>");
				
				i++;
			}
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+str.toString()+""); 
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		
	}

	
	public String save()throws Exception {
		
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection=null;
		try {
		    connection=Connection_provider.getconnection();
		    NursingDAO nursingDAO=new JDBCNursingDAO(connection);
		    String clientid=masterForm.getClientid();
		    String conditionid=masterForm.getConditionid();
		    String ipdid=masterForm.getIpdid();
		    String practitionerid=masterForm.getPractitionerid();
		    
		    Master master=new Master();
		    master.setNotes(masterForm.getNotes());
		    master.setClientid(clientid);
		    master.setConditionid(conditionid);
		    master.setIpdid(ipdid);
		    master.setPractitionerid(practitionerid);
		    
		    String datetime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
		    master.setDatetime(datetime);
            int saveparent=nursingDAO.saveParentNursing(master); 		    
		    
            ArrayList<Master>nursingList = (ArrayList<Master>)session.getAttribute("nursingList");
            for(Master master2:nursingList){
            	
            	master2.setClientid(clientid);
            	master2.setConditionid(conditionid);
            	master2.setIpdid(ipdid);
            	master2.setPractitionerid(practitionerid);
            	master2.setDatetime(datetime);
            	int result=nursingDAO.saveNusingData(saveparent,master2);
            	
            }   
            nursingList = new ArrayList<Master>();
            session.setAttribute("nursingList",nursingList);
            
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally{
			connection.close();
		}
		return "ipddashboard";
	}
	
	
public String save_nursing_ajax(){
	try {
		 Connection connection=Connection_provider.getconnection();
		 NursingDAO nursingDAO=new JDBCNursingDAO(connection);
		String clientId=DateTimeUtils.isNull(request.getParameter("cid"));
		String ipdid=DateTimeUtils.isNull(request.getParameter("ipdid"));
		String conditionId=DateTimeUtils.isNull(request.getParameter("condid"));
		String practionarId=DateTimeUtils.isNull(request.getParameter("practionerid"));
		String fromdate=DateTimeUtils.isNull(request.getParameter("date"));
		String todate=DateTimeUtils.isNull(request.getParameter("todate"));
		String fromtime=DateTimeUtils.isNull(request.getParameter("fromtime"));
		String totime=DateTimeUtils.isNull(request.getParameter("totime"));
		
		 Master master=new Master();
		    master.setNotes("");
		    master.setClientid(clientId);
		    master.setConditionid(conditionId);
		    master.setIpdid(ipdid);
		    master.setPractitionerid(practionarId);
		   /* String datetime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());*/
		    String datetime=DateTimeUtils.getCommencingDate1(fromdate)+" "+fromtime;
		    master.setTodate(DateTimeUtils.getCommencingDate1(todate)+" "+totime);
		    master.setGivenTo(DateTimeUtils.isNull(request.getParameter("givento")));
		    master.setDatetime(datetime);
            int saveparent=nursingDAO.saveParentNursing(master); 		    
		    
            ArrayList<Master>nursingList = (ArrayList<Master>)session.getAttribute("nursingList");
            StringBuffer buffer= new StringBuffer();
            buffer.append("<h3> Nursing Care :</h3>   <b> From : ["+fromdate+" "+fromtime+"] -  To : ["+todate+" "+totime+"] </b>  ");
            buffer.append("<table class='table' style='width:70%'><thead><tr>");
            buffer.append("<th>Category</th><th>Taskname</th><th>Frequency</th><th>Duration</th></tr></thead><tbody>");
            for(Master master2:nursingList){
            	buffer.append("<tr>");
            	buffer.append("<td>"+master2.getNursingtype()+"</td>");
            	buffer.append("<td>"+master2.getTaskname()+"</td>");
            	buffer.append("<td>"+master2.getFrequency()+"</td>");
            	buffer.append("<td>"+master2.getDays()+"</td>");
            	buffer.append("</tr>");
            	master2.setClientid(clientId);
            	master2.setConditionid(conditionId);
            	master2.setIpdid(ipdid);
            	master2.setPractitionerid(practionarId);
            	master2.setDatetime(datetime);
            	int result=nursingDAO.saveNusingData(saveparent,master2);
            	
            } 
            buffer.append("</tbody></table>");
            
            
            
            
            nursingList = new ArrayList<Master>();
            session.setAttribute("nursingList",nursingList);    
            
            response.setContentType("text/html");
    		response.setHeader("Cache-Control", "no-cache");
    		response.getWriter().write(""+buffer.toString());
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}	
	
	
	public String delnur()throws Exception 	{
		
		try {
			String clientId = request.getParameter("clientId");
			String prectionerid = request.getParameter("prectionerid");
			String conditionid = request.getParameter("conditionid");
			
			String selectedid = request.getParameter("selectedid");
			ArrayList<Master>nursingList = (ArrayList<Master>)session.getAttribute("nursingList");
			nursingList.remove(Integer.parseInt(selectedid));
			
			getNursingData();
		} catch (Exception e) {

		   e.printStackTrace();
		}
		return null;
	}
	
	
	
	public void prepare() throws Exception {

		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			MasterDAO masterDAO=new JDBCMasterDAO(connection);
			
			ArrayList<Master> nursingdetails=masterDAO.getAllNursingDetails(null,null);
			masterForm.setNursingdetails(nursingdetails);
          
			ArrayList<Master> nursingcategorylist=masterDAO.getAllNursingCategory(null);
			masterForm.setNursingcategorylist(nursingcategorylist);
			
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		
	}

	public String nursingrRequests(){
		try {
			String ipdId=DateTimeUtils.isNull(request.getParameter("ipdid"));
			Connection connection= Connection_provider.getconnection();
			NursingDAO nursingDAO= new JDBCNursingDAO(connection);
			ArrayList<Master> list= nursingDAO.nusringCareListOfPatient(ipdId);
			StringBuffer buffer= new  StringBuffer();
			buffer.append("<select class='chosen-select' id='repeatlist' onchange='loadnursingcareRepeat(this)'>");
			buffer.append("<option value='0'>Select Care</option>");
			for(Master master:list){
				buffer.append("<option value='"+master.getId()+"'>"+master.getName()+"</option>");
			}
			buffer.append("</select>");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+buffer.toString()+"");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public String  loadnursingcareRepeat() {
		try {
			String parentId=request.getParameter("parent");
			Connection connection= Connection_provider.getconnection();
			NursingDAO nursingDAO= new JDBCNursingDAO(connection);
			ArrayList<Master> nursingList= new ArrayList<Master>();
			if(session.getAttribute("nursingList")!=null){
				nursingList = (ArrayList<Master>)session.getAttribute("nursingList");
			}
			ArrayList<Master> childlist= nursingDAO.childListOfNursingCare(parentId);
			if(childlist.size()>0){
				nursingList.addAll(childlist);
			}
			
			
			session.setAttribute("nursingList", nursingList);
			
			getNursingData();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
