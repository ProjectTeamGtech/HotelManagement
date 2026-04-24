package com.apm.Master.web.action;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Master.eu.bi.InvestigationTemplateDAO;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.bi.MedicineTypeDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCInvestigationTemplate;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMedicineTypeDAO;
import com.apm.Master.eu.entity.InvestigationTemplate;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.MedicineType;
import com.apm.Master.web.form.InvestigationTemplateForm;
import com.apm.Master.web.form.MedicineTypeMasterForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class InvestigationTemplateAction extends BaseAction implements ModelDriven<InvestigationTemplateForm> ,Preparable{
	
	InvestigationTemplateForm investigationtemplateform=new InvestigationTemplateForm();
	HttpServletRequest request=ServletActionContext.getRequest();
	HttpServletResponse response=ServletActionContext.getResponse();
	
	LoginInfo loginInfo=LoginHelper.getLoginInfo(request);
	
	HttpSession session=request.getSession();
	String mastername="";
	Pagination pagination=new Pagination(25,1);
	

	public void prepare() throws Exception {
		// TODO Auto-generated method stub
Connection connection = null;
		
		try { 
			
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			InvestigationTemplateDAO investigationTemplateDAO= new JDBCInvestigationTemplate(connection);
			ArrayList<Master> masterlist = masterDAO.getMasterList(loginInfo);
			investigationtemplateform.setMasterlist(masterlist);
			mastername=(String)session.getAttribute("mastername");
			investigationtemplateform.setMastername(mastername);
			
			ArrayList<Master> invsSectionList= investigationTemplateDAO.getAllInvestigationSectionList();
			investigationtemplateform.setInvsSectionList(invsSectionList);
			
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
			InvestigationTemplateDAO invtemDAO=new JDBCInvestigationTemplate(connection);
			int count=invtemDAO.getTotalInvTempCount();
			pagination.setPreperties(count);
			String searchText=investigationtemplateform.getSearchText();
			if(searchText!=null){
				if(searchText.equals("")){
					searchText=null;
				}
			}
			ArrayList<InvestigationTemplate> medicinetypelist =invtemDAO.getAllInvestigationTemplateList(searchText,pagination);
			investigationtemplateform.setInvsTypeList(medicinetypelist);
			pagination.setPage_records(medicinetypelist.size());
			investigationtemplateform.setPagerecords(String.valueOf(pagination.getPage_records()));
			investigationtemplateform.setTotalRecords(String.valueOf(count));
			mastername=request.getParameter("selectedid");
			if(mastername!=null){
			 session.setAttribute("mastername", mastername);
		} else {
			mastername=(String)session.getAttribute("mastername");
		}
			investigationtemplateform.setMastername(mastername);
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		return "investigationtemplate";
	}

	public InvestigationTemplateForm getModel() {
		// TODO Auto-generated method stub
		return investigationtemplateform;
	}
	
	public String add() throws Exception{
		Connection connection=null;
		try {
			
			connection=Connection_provider.getconnection();
			InvestigationTemplateDAO invDAO=new JDBCInvestigationTemplate(connection);
			ArrayList<InvestigationTemplate> getTypeList=invDAO.getInvestigationTypeList();
			investigationtemplateform.setInvsTypeList(getTypeList);
			
		} 
		catch (Exception e) {
			
		e.printStackTrace();
		}
		finally {
		connection.close();
		}
		return "addinvestigationtemplate";
	
		
	}
	
	public String edit() throws Exception{
		
		Connection connection=null;
		try {
			
			connection=Connection_provider.getconnection();
			InvestigationTemplateDAO invDAO=new JDBCInvestigationTemplate(connection);
			ArrayList<InvestigationTemplate> getTypeList=invDAO.getInvestigationTypeList();
			investigationtemplateform.setInvsTypeList(getTypeList);
			String getID=request.getParameter("id");
			InvestigationTemplate master = new InvestigationTemplate();
			
			master.setId(Integer.parseInt(getID));
			InvestigationTemplate invTemplist =invDAO.getAllInvestigationTemplateListById(master);
			//invTemplist.setId(Integer.parseInt(getID));
			investigationtemplateform.setId(invTemplist.getId());
			investigationtemplateform.setInv_id(invTemplist.getInv_id());
			investigationtemplateform.setTitle(invTemplist.getTitle());
			investigationtemplateform.setTemplate_text(invTemplist.getTemplate_text());
			investigationtemplateform.setInvType(invTemplist.getInv_id());
			investigationtemplateform.setInvs_section_id(invTemplist.getInvs_section_id());
			
		} 
		catch (Exception e) {
			
		e.printStackTrace();
		}
		finally {
		connection.close();
		}
		
		return "editinvestigationtemplate";
	}
	
	public String update() throws Exception{
		Connection connection=null;
		
		try {
			connection = Connection_provider.getconnection();
			InvestigationTemplateDAO invDAO=new JDBCInvestigationTemplate(connection);
			InvestigationTemplate master=new InvestigationTemplate();
			//Master master = new Master();
				
			String dateTime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			master.setId(investigationtemplateform.getId());
			master.setTitle(investigationtemplateform.getTitle());
			master.setTemplate_text(investigationtemplateform.getTemplate_text());
			master.setInv_id(investigationtemplateform.getInvType());
			master.setDateTime(dateTime);
			master.setInvs_section_id(investigationtemplateform.getInvs_section_id());
			int result = invDAO.updateinvestigationtemplatelist(master);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		connection.close();
		}
		return "updateinvestigationtemplate";
		
	}
	
	
	public String save() throws Exception{
		Connection connection=null;
		try {
			connection = Connection_provider.getconnection();
			InvestigationTemplateDAO invDAO=new JDBCInvestigationTemplate(connection);
			InvestigationTemplate master=new InvestigationTemplate();
			//Master master = new Master();
			String dateTime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			master.setInv_id(investigationtemplateform.getInvType());
			master.setTitle(investigationtemplateform.getTitle());
			master.setDateTime(dateTime);
			master.setTemplate_text(investigationtemplateform.getTemplate_text());
			master.setInvs_section_id(investigationtemplateform.getInvs_section_id());
			
			int result = invDAO.addInvestigationTemplate(master);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		connection.close();
		}
		return "saveinvestigationtemplate";
		

	}
	
	
	public String delete(){
		String  masterid=request.getParameter("id");
		Connection connection=null;
		try {
			connection = Connection_provider.getconnection();
			InvestigationTemplateDAO invDAO=new JDBCInvestigationTemplate(connection);
			InvestigationTemplate master=new InvestigationTemplate();
			master.setId(Integer.parseInt(masterid));
			int  master2 = invDAO.deleteInvestigationTemplate(master);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "deleteinvestigationtemplate";
	}


	public Pagination getPagination() {
		return pagination;
	}


	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	
}
