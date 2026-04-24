package com.apm.ThirdParties.web.action;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.Mrd.eu.bi.MrdDAO;
import com.apm.Mrd.eu.blogic.JDBCMrdDAO;
import com.apm.ThirdParties.eu.bi.TPADao;
import com.apm.ThirdParties.eu.blogic.jdbc.JDBCTPADao;
import com.apm.ThirdParties.eu.entity.ThirdParty;
import com.apm.ThirdParties.web.form.ThirdPartyForm;
import com.apm.Tools.eu.bi.EmailTemplateDAO;
import com.apm.Tools.eu.blogic.jdbc.JDBCEmailTemplateDAO;
import com.apm.Tools.eu.entity.EmailTemplate;
import com.apm.TreatmentEpisode.eu.bi.TreatmentEpisodeDAO;
import com.apm.TreatmentEpisode.eu.blogic.jdbc.JDBCTreatmentEpisode;
import com.apm.TreatmentEpisode.eu.entity.TreatmentEpisode;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class TPADashboardAction extends BaseAction implements ModelDriven<ThirdPartyForm>,Preparable {

	ThirdPartyForm thirdPartyForm= new ThirdPartyForm();
	HttpServletRequest request=ServletActionContext.getRequest();
	HttpServletResponse response=ServletActionContext.getResponse();
	HttpSession session=request.getSession(true);
	LoginInfo loginInfo=LoginHelper.getLoginInfo(request);
	Pagination pagination=new Pagination(25,1);
	
	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public ThirdPartyForm getModel() {
		// TODO Auto-generated method stub
		return thirdPartyForm;
	}

	public String execute() throws Exception {
		
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			TPADao tpaDao= new JDBCTPADao(connection);
			EmailTemplateDAO emailTemplateDAO= new JDBCEmailTemplateDAO(connection);
			
			String fromdate = thirdPartyForm.getFromdate();
			String todate= thirdPartyForm.getTodate();
			if(fromdate==null){
				Calendar calendar=Calendar.getInstance();
				calendar.add(Calendar.DATE, -7);
				SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
				fromdate=dateFormat.format(calendar.getTime());
			} else {
				fromdate= DateTimeUtils.getCommencingDate1(fromdate);
			}
			
			if(todate==null){
				
				Calendar calendar=Calendar.getInstance();
				SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
				todate=dateFormat.format(calendar.getTime());
			} else {
				todate= DateTimeUtils.getCommencingDate1(todate);
			}
			
			String searchText= thirdPartyForm.getSearchText();
			if(searchText!=null){
				
				if(searchText.equals("")){
					 searchText=null;
				}
			}
			
			int count = tpaDao.getTotalTpaListCount(fromdate,todate,searchText);
			pagination.setPreperties(count);
			ArrayList<ThirdParty> tpnamelist= tpaDao.getTpaList(fromdate,todate,searchText,pagination,loginInfo.getIpd_abbr_access());
			thirdPartyForm.setTpnameList(tpnamelist);
			pagination.setPage_records(tpnamelist.size());
			thirdPartyForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			thirdPartyForm.setTotalRecords(count);
			fromdate= DateTimeUtils.getCommencingDate1(fromdate);
			todate= DateTimeUtils.getCommencingDate1(todate);
			
			thirdPartyForm.setFromdate(fromdate);
			thirdPartyForm.setTodate(todate);
			
			ArrayList<EmailTemplate> templateNameList = new ArrayList<EmailTemplate>();
			templateNameList = emailTemplateDAO.getEmailTemplateNameList("ThirdParty",loginInfo.getId());
			
			thirdPartyForm.setTemplateNameList(templateNameList);
			
			
			ClientDAO clientDAO=new JDBCClientDAO(connection);
			ArrayList<Client> condtitionList = clientDAO.getEmrTreatmentTypeList();
			thirdPartyForm.setCondtitionList(condtitionList);
			
			TreatmentEpisodeDAO treatmentEpisodeDAO= new JDBCTreatmentEpisode(connection);
			ArrayList<TreatmentEpisode> sourceOfReferralList = treatmentEpisodeDAO.getSourceOfReferralList();
			thirdPartyForm.setSourceOfReferralList(sourceOfReferralList);
		
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		return super.execute();
	}

	public String setmail() throws Exception {
		
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			ClientDAO clientDAO=new JDBCClientDAO(connection);
			String clientid= request.getParameter("clientid");
			String treatmentepisodeid= request.getParameter("treatmentepisodeid");
			
			Client client=clientDAO.getClientDetails(clientid);
			String fullname= client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
			String data=fullname+"~"+clientid+"~"+treatmentepisodeid;
			
			
			
			
			
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
	
	public String rejectform() throws Exception {
		
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			String treatmentepisodeid= request.getParameter("treatmentepisodeid");
			TPADao tpaDao= new JDBCTPADao(connection);
			
			int res= tpaDao.rejectTpaStatus(treatmentepisodeid);
			String data="";
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		
		return "tpadashboard";
		
	}
public String acceptform() throws Exception {
		
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			String treatmentepisodeid= request.getParameter("treatmentepisodeid");
			TPADao tpaDao= new JDBCTPADao(connection);
			
			int res= tpaDao.acceptTpaStatus(treatmentepisodeid);
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		return "tpadashboard";
		
	}
	
	
	
	public void prepare() throws Exception {
		
		
		
		
	}

	public String deletedata() throws Exception {
		 Connection connection = null;
		 
		 try {
		  connection = Connection_provider.getconnection();
		  TPADao tpaDao= new JDBCTPADao(connection);
		  String id= request.getParameter("id");
		  String delete_reason= request.getParameter("delete_reason");
		 
		  int del=tpaDao.cancelTpa(id,delete_reason);
		
		  
		  response.setContentType("text/html");
		  response.setHeader("Cache-Control", "no-cache");
		  response.getWriter().write("o"); 
		 } catch (Exception e) {
		  e.printStackTrace();
		 }
		 finally{
		  connection.close();
		 }
		 
		 return null;
		}
}
