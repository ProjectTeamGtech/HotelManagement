package com.apm.Ipd.web.action;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.bi.CompleteAptmDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCCompleteAptmDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.Ipd.eu.bi.IpdLogDAO;
import com.apm.Ipd.eu.blogic.jdbc.JDBCIpdLogDAO;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Ipd.web.form.IpdForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class IpdLogAction  extends BaseAction implements ModelDriven<IpdForm>{
	
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	
	IpdForm ipdForm = new IpdForm();
	
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}
	
	public String bedchange() throws SQLException{
		
String clientId = request.getParameter("clientId");
		
		Connection connection = null;
		try{
			
			connection = Connection_provider.getconnection();
			IpdLogDAO ipdLogDAO = new JDBCIpdLogDAO(connection);
			
			ArrayList<Bed>bedchangeLogList = ipdLogDAO.getBedChangeLogList(clientId,"");
			
			StringBuffer str = new StringBuffer();
			
			
			str.append("<table class='table table-hove table-bordered table-striped table-condensed' > ");
			str.append("<thead>");
			str.append("<tr>");
			str.append("<th>Admission ID</th> ");
			str.append("<th>Ward</th> ");
			str.append("<th>Bed</th> ");
			str.append("<th>Date</th> ");
			str.append("<th>Duration</th> ");
			str.append("</tr>");
			str.append("</thead>");
			
			str.append("<tbody>");
			int i = 0;
			for(Bed bed : bedchangeLogList){
				str.append("<tr>");
				str.append("<td>"+bed.getAddmissionid()+"</td>");
				str.append("<td>"+bed.getWardname()+"</td>");
				str.append("<td>"+bed.getBedname()+"</td>");
				str.append("<td>"+bed.getDate()+"</td>");
				
				long duration = 0;
				if(i<bedchangeLogList.size()-1){
					Bed b = bedchangeLogList.get(i+1);
					String temp[] = bed.getDate().split(" ");
					String temp1[] = b.getDate().split(" ");
					
					String date1 = DateTimeUtils.getCommencingDate1(temp[0]);
					String date2 = DateTimeUtils.getCommencingDate1(temp1[0]);
					
					duration = DateTimeUtils.getDiffofTwoDates(date1 , date2);
				}else{
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Calendar cal = Calendar.getInstance();
					//cal.add(Calendar.DATE, 30); 
					String curdate = dateFormat.format(cal.getTime());
					
					String temp[] = bed.getDate().split(" ");
					
					String date1 = DateTimeUtils.getCommencingDate1(temp[0]);
					
					ClientDAO clientDAO = new JDBCClientDAO(connection);
					String dischargeDate = clientDAO.getIpdDischargeDate(bed.getAddmissionid());  
					if(dischargeDate!=null){
						if(!dischargeDate.equals("")){
							String dtemp[] = dischargeDate.split(" ");
							dischargeDate = DateTimeUtils.getCommencingDate1(dtemp[0]);
							duration = DateTimeUtils.getDiffofTwoDates(date1 , dischargeDate);
						}
					}else{
						duration = DateTimeUtils.getDiffofTwoDates(date1 , curdate);
					}
					
					
				}
				
				
				str.append("<td>"+duration+"</td>");
				str.append("</tr>");
				
				i++;
			}
			str.append("</tbody>");
			str.append("</table>");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			
			response.getWriter().write(""+str.toString()+""); 
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		return null;
	}
	
	
	public String admission() throws SQLException{
		String clientId = request.getParameter("clientId");
		
		Connection connection = null;
		try{
			
			connection = Connection_provider.getconnection();
			IpdLogDAO ipdLogDAO = new JDBCIpdLogDAO(connection);
			
			ArrayList<Bed>admissionLogList = ipdLogDAO.getAdmissionlogDAO(clientId);
			CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
			StringBuffer str = new StringBuffer();
			
			
			str.append("<table class='table table-hove table-bordered table-striped table-condensed' > ");
			str.append("<thead>");
			str.append("<tr>");
			str.append("<th>Admission ID</th> ");
			str.append("<th>Admission Date/Time</th> ");
			str.append("<th>Discharge Date/Time</th> ");
			str.append("<th>Duration</th> ");
			str.append("</tr>");
			str.append("</thead>");
			
			str.append("<tbody>");
			for(Bed bed : admissionLogList){
				str.append("<tr>");
				str.append("<td>"+bed.getAddmissionid()+"  <a href='javascript: void(0);' onclick='showbedchangedata("+bed.getAddmissionid()+")'><i class='fa fa-arrow-down' title='View Bed Change Status'></i></a> </td>");
				str.append("<td>"+bed.getAdmissiondate()+"</td>");
				str.append("<td>"+bed.getDischargeDate()+"</td>");
				
				long dischargeduration = 0;
				if(!bed.getDischargeDate().equals("")){
					String temp[] = bed.getAdmissiondate().split(" ");
					String temp1[] = bed.getDischargeDate().split(" ");
					
					String date1 = DateTimeUtils.getCommencingDate1(temp[0]);
					String date2 = DateTimeUtils.getCommencingDate1(temp1[0]);
					
					dischargeduration = DateTimeUtils.getDiffofTwoDates(date1, date2);
					
					
				}else{
					String temp[] = bed.getAdmissiondate().split(" ");
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Calendar cal = Calendar.getInstance();
					//cal.add(Calendar.DATE, 30); 
					String curdate = dateFormat.format(cal.getTime());
					
					String date1 = DateTimeUtils.getCommencingDate1(temp[0]);
					
					dischargeduration = DateTimeUtils.getDiffofTwoDates(date1, curdate);
				}
				
				str.append("<td>"+dischargeduration+" Days</td>");
				
				str.append("</tr>");
				
				str.append("<tr id='ad"+bed.getAddmissionid()+"' style='display:none;'>");
				str.append("<td colspan='4'>");
				
				str.append("<table class='table table-hove table-bordered table-striped table-condensed' > ");
				str.append("<thead>");
				str.append("<tr>");
				str.append("<th>Ward</th> ");
				str.append("<th>Bed</th> ");
				str.append("<th>Date/Time</th> ");
				str.append("<th>Duration</th> ");
				str.append("</tr>");
				str.append("</thead>");
				
				ArrayList<Bed>bedchangeLogList = ipdLogDAO.getBedChangeLogList(clientId,bed.getAddmissionid());
				session.setAttribute("bedchangeLogList", bedchangeLogList);
				
				str.append("<tbody>");
				int i = 0;
				boolean sts=false;
				int wardids=0;
				for(Bed bed1 : bedchangeLogList){
					
					str.append("<tr>");
					/*str.append("<td>"+bed.getAddmissionid()+"</td>");*/
					str.append("<td>"+bed1.getWardname()+"</td>");
					str.append("<td>"+bed1.getBedname()+"</td>");
					if(i==0){
						str.append("<td>"+bed1.getDate()+"</td>");
					}else{
						
						str.append("<td><a href='#' onclick='showbedloddatepopup("+i+","+bedchangeLogList.size()+",0,"+bed1.getId()+")'>"+bed1.getDate()+"</a></td>");
					}
					
					long duration = 0;
					if(i<bedchangeLogList.size()-1){
						Bed b = bedchangeLogList.get(i+1);
						String temp[] = bed1.getDate().split(" ");
						String temp1[] = b.getDate().split(" ");
						
						String date1 = DateTimeUtils.getCommencingDate1(temp[0]);
						String date2 = DateTimeUtils.getCommencingDate1(temp1[0]);
						if(date1.equals(date2)){
							if(bed1.getWardname().equals(b.getWardname())){
							sts=true;
							}else{
								wardids = completeAptmDAO.getMaxWardChargeID(DateTimeUtils.convertToInteger(bed1.getAddmissionid()),temp[0]);
							}
						}else{
							sts=false;
						}
						duration = DateTimeUtils.getDiffofTwoDates(date2 , date1);
					}else{
						DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						Calendar cal = Calendar.getInstance();
						//cal.add(Calendar.DATE, 30); 
						String curdate = dateFormat.format(cal.getTime());
						
						String temp[] = bed1.getDate().split(" ");
						
						String date1 = DateTimeUtils.getCommencingDate1(temp[0]);
						
						ClientDAO clientDAO = new JDBCClientDAO(connection);
						String dischargeDate = clientDAO.getIpdDischargeDate(bed.getAddmissionid());  
						if(dischargeDate!=null){
							if(!dischargeDate.equals("")){
								String dtemp[] = dischargeDate.split(" ");
								dischargeDate = DateTimeUtils.getCommencingDate1(dtemp[0]);
								duration = DateTimeUtils.getDiffofTwoDates(date1 , dischargeDate);
							}
						}else{
							duration = DateTimeUtils.getDiffofTwoDates(date1 , curdate);
						}
					}
					
					if(!sts){
						duration=duration+1;
					
					}else{
						duration=0;
					}
					str.append("<td>"+(duration)+" Days</td>");
					str.append("</tr>");
					
					i++;
				}
				str.append("</tbody>");

				
				
				str.append("</td>");
				str.append("</tr>");
				str.append("</table>");
			}
			
			
			
			
			str.append("</tbody>");
			str.append("</table>");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			
			response.getWriter().write(""+str.toString()+""); 
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		return null;
	}
	
	
	public String discharge() throws SQLException{
		
	String clientId = request.getParameter("clientId");
		
		Connection connection = null;
		try{
			
			connection = Connection_provider.getconnection();
			IpdLogDAO ipdLogDAO = new JDBCIpdLogDAO(connection);
			
			ArrayList<Bed>admissionLogList = ipdLogDAO.getDischargeLog(clientId);
			
			StringBuffer str = new StringBuffer();
			
			
			str.append("<table class='table table-hove table-bordered table-striped table-condensed' > ");
			str.append("<thead>");
			str.append("<tr>");
			str.append("<th>Admission ID</th> ");
			str.append("<th>Admission Date/Time</th> ");
			str.append("</tr>");
			str.append("</thead>");
			
			str.append("<tbody>");
			for(Bed bed : admissionLogList){
				str.append("<tr>");
				str.append("<td>"+bed.getAddmissionid()+"</td>");
				str.append("<td>"+bed.getAdmissiondate()+"</td>");
				str.append("</tr>");
			}
			str.append("</tbody>");
			str.append("</table>");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			
			response.getWriter().write(""+str.toString()+""); 
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		return null;
	}
	
	public String showpatientdetailsupdatelog() throws SQLException{
		
		String clientId = request.getParameter("clientId");
			
			Connection connection = null;
			try{
				
				connection = Connection_provider.getconnection();
				IpdLogDAO ipdLogDAO = new JDBCIpdLogDAO(connection);
				
				ArrayList<Client> patenteditLogList = ipdLogDAO.getPatientEditLog(clientId);
				
				StringBuffer str = new StringBuffer();
				
				
				str.append("<table class='table table-hove table-bordered table-striped table-condensed' > ");
				str.append("<thead>");
				str.append("<tr>");
				str.append("<th>Pre-Fullname</th> ");
				str.append("<th>Curr-Fullname</th> ");
				str.append("<th>Pre-DOB</th> ");
				str.append("<th>Curr-DOB</th> ");
				str.append("<th>Pre-Payee</th> ");
				str.append("<th>Curr-Payee</th> ");
				str.append("<th>Datetime</th> ");
				str.append("<th>Updated by</th> ");
				str.append("</tr>");
				str.append("</thead>");
				
				str.append("<tbody>");
				for(Client client : patenteditLogList){
					String prefullname = DateTimeUtils.isNull(client.getPre_initial())+" "+DateTimeUtils.isNull(client.getPre_fname())+" "+DateTimeUtils.isNull(client.getPre_mname())+" "+DateTimeUtils.isNull(client.getPre_lname());
					String currfullname = DateTimeUtils.isNull(client.getCurr_initial())+" "+DateTimeUtils.isNull(client.getCurr_fname())+" "+DateTimeUtils.isNull(client.getCurr_mname())+" "+DateTimeUtils.isNull(client.getCurr_lname());
					str.append("<tr>");
					str.append("<td>"+prefullname+"</td>");
					str.append("<td>"+currfullname+"</td>");
					str.append("<td>"+client.getPre_dob()+"</td>");
					str.append("<td>"+client.getCurr_dob()+"</td>");
					str.append("<td>"+client.getPre_payee()+"</td>");
					str.append("<td>"+client.getCurr_payee()+"</td>");
					str.append("<td>"+client.getDate()+"</td>");
					str.append("<td>"+client.getUserid()+"</td>");
					str.append("</tr>");
				}
				str.append("</tbody>");
				str.append("</table>");
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				
				response.getWriter().write(""+str.toString()+""); 
				
				
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				connection.close();
			}
			
			return null;
		}
	
	
	

	public IpdForm getModel() {
		// TODO Auto-generated method stub
		return null;
	}

}
