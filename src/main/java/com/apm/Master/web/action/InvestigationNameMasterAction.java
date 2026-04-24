package com.apm.Master.web.action;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Emr.eu.bi.InvestigationDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCInvestigationDAO;
import com.apm.Emr.eu.entity.Investigation;
import com.apm.Emr.web.form.EmrForm;
import com.apm.Master.eu.bi.InvestigationMasterDAO;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCInvestigationMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class InvestigationNameMasterAction extends BaseAction implements
		ModelDriven<EmrForm>, Preparable {

	EmrForm emrForm = new EmrForm();
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpSession session=request.getSession(false);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	private String mastername;

	Pagination pagination=new Pagination(20, 1);
	
	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub

		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			InvestigationMasterDAO masterDAO = new JDBCInvestigationMasterDAO(
					connection);
			String searchText = emrForm.getSearchText();
			if(searchText!=null){
				if(searchText.equals("")){
					searchText=null;
				}
			}
			
			int totalcount=masterDAO.getTotalInvestigationNamesCount(searchText);
			pagination.setPreperties(totalcount);
			ArrayList<Master> investigation_name_list = masterDAO
					.getAllInvestigationNames(pagination,searchText);
			pagination.setPage_records(investigation_name_list.size());
			emrForm.setTotalRecords(totalcount);
			emrForm.setPagerecords(Integer.toString(pagination.getPage_records()));
			emrForm.setInvestigation_name_list(investigation_name_list);
			mastername = request.getParameter("selectedid");
			if(mastername!=null) {
				
				session.setAttribute("mastername", mastername);	
			}
			else {
				
				mastername=(String)session.getAttribute("mastername");
			}
			emrForm.setMastername(mastername);
			session.setAttribute("pagination", pagination);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			connection.close();
		}

		return SUCCESS;
	}

	public String add() throws Exception {

		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InvestigationMasterDAO masterDAO = new JDBCInvestigationMasterDAO(
					connection);
			ArrayList<Master> invsTypeList = masterDAO
					.getAllInvestigationTypes();
			emrForm.setInvsTypeList(invsTypeList);
		
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "add";
	}
	

	
	
	
	public String save() throws Exception {

		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;

		try {

			connection = Connection_provider.getconnection();
			InvestigationMasterDAO dao = new JDBCInvestigationMasterDAO(
					connection);

			for (Master master : emrForm.getInvestigation_name()) {
				
				int result = dao.addInvestigationName(master);

			}
		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			connection.close();
		}

		return "save";
	}

	public String edit() throws Exception {

		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			String selectedid = request.getParameter("selectedid");
			InvestigationMasterDAO dao = new JDBCInvestigationMasterDAO(
					connection);
			Master master = dao.getInvestigationName(selectedid);
			emrForm.setId(master.getId());
			emrForm.setInvestigation_type_id(master.getInvestigation_type_id());
			emrForm.setName(master.getName());
			emrForm.setSpecimen(master.getSpecimen());
			emrForm.setReport_type(master.getReport_type());
			emrForm.setUnit(master.getUnit());
			emrForm.setNormal_value(master.getNormal_value());
			emrForm.setCharge(master.getCharge());
			
			ArrayList<Master> invsTypeList = dao.getAllInvestigationTypes();
			emrForm.setInvsTypeList(invsTypeList);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "edit";
	}

	public String update() throws Exception {

		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;

		try {

			connection = Connection_provider.getconnection();
			InvestigationMasterDAO masterDAO = new JDBCInvestigationMasterDAO(
					connection);
			Master master = new Master();
			master.setId(emrForm.getId());
			master.setName(emrForm.getName());
			master.setInvestigation_type_id(emrForm.getInvestigation_type_id());
			master.setSpecimen(emrForm.getSpecimen());
			master.setReport_type(emrForm.getReport_type());
			master.setUnit(emrForm.getUnit());
			master.setNormal_value(emrForm.getNormal_value());
			master.setCharge(emrForm.getCharge());
			int result = masterDAO.updateInvestigationName(master);
	
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "updatesave";
	}

	public String back() {
		
		return "save";
	}

	public String delete() throws Exception {

		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;

		try {

			String selectedid = request.getParameter("selectedid");
			connection = Connection_provider.getconnection();
			InvestigationMasterDAO masterDAO = new JDBCInvestigationMasterDAO(
					connection);
			int result = masterDAO.deleteInvestigationName(selectedid);
			
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "save";
	}

	public String addnewrow() throws Exception {

		Connection connection = null;

		try {

			connection = Connection_provider.getconnection();
			InvestigationMasterDAO dao = new JDBCInvestigationMasterDAO(
					connection);
			ArrayList<Master> invsTypeList = dao.getAllInvestigationTypes();
			int rowcount = Integer.parseInt(request.getParameter("rowcount"));
			int index = rowcount;
			index--;
			StringBuffer buffer = new StringBuffer();
			buffer.append("<tr>");
			buffer.append("<td><input type='checkbox' name='chkbox[]' title='Delete row' title='delete row' /></td>");
			buffer.append("<td>" + (rowcount) + "</td>");
			buffer.append("<td>");
			buffer.append("<select name='investigation_name["
					+ index
					+ "].id' id='invsTypeList' class='form-control showToolTip chosen' title = 'Select Investigation Type'  onchange = 'setinvestigationtype(this.value)'>");
			buffer.append("<option value='0'>Select Investigation Type</option>");
			for (Master master : invsTypeList) {
				buffer.append("<option value='" + master.getId() + "'>"
						+ master.getName() + "</option>");
			}
			buffer.append("</select>");
			buffer.append("</td>");
			buffer.append("<td>");
			buffer.append("<input type='text' name='investigation_name["
					+ index
					+ "].name' id='name' placeholder='enter name' class='form-control showToolTip filedname' data-toggle='tooltip' />");
			buffer.append("</td>");
			buffer.append("<td>");
			buffer.append("<input type='text' name='investigation_name["
					+ index
					+ "].specimen' id='specimen' placeholder='enter specimen' class='form-control showToolTip filedname' data-toggle='tooltip' />");
			buffer.append("</td>");
			buffer.append("<td style='display:none;'>");
			buffer.append("<input type='text' name='investigation_name["
					+ index
					+ "].report_type' id='reporttype' placeholder='enter investigation group' class='form-control showToolTip filedname' data-toggle='tooltip' />");
			buffer.append("</td>");
			buffer.append("<td>");
			buffer.append("<input type='text' name='investigation_name["
					+ index
					+ "].unit' id='unit' placeholder='enter unit' class='form-control showToolTip filedname' data-toggle='tooltip' />");
			buffer.append("</td>");
			buffer.append("<td>");
			buffer.append("<input type='text' name='investigation_name["
					+ index
					+ "].normal_value' id='normal_value' placeholder='enter normal value' class='form-control showToolTip filedname' data-toggle='tooltip' />");
			buffer.append("</td>");
			buffer.append("<td>");
			buffer.append("<input type='text' name='investigation_name["
					+ index
					+ "].charge' id='charge' placeholder='enter charge' class='form-control showToolTip filedname' data-toggle='tooltip' />");
			buffer.append("</td>");
			
			buffer.append("</tr>");
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

	public void prepare() throws Exception {
		// TODO Auto-generated method stub

		Connection con = null;

		try {
			con = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(con);
			ArrayList<Master> masterlist = masterDAO.getMasterList(loginInfo);
			emrForm.setMasterlist(masterlist);
			mastername=(String)session.getAttribute("mastername");
			emrForm.setMastername(mastername);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			con.close();
		}

	}

	public EmrForm getModel() {
		// TODO Auto-generated method stub
		return emrForm;
	}

	
	public String invstlist(){
		try {
			Connection connection= Connection_provider.getconnection();
			InvestigationDAO investigationDAO= new JDBCInvestigationDAO(connection);
			String invsttypeId=DateTimeUtils.isNull(request.getParameter("id"));
			ArrayList<Investigation> list= investigationDAO.investigationNameListMaster(invsttypeId);
			StringBuffer buffer= new  StringBuffer();
			buffer.append("<table class='my-table innerTable'>");
			buffer.append("<tr><th>Name</th><th>Type</th><th>Unit</th><th>Normal Value</th><th>Critical Value</th>"
					+ "");
			buffer.append("</tr>");
			for(Investigation investigation: list){
				buffer.append("<tr>");
				buffer.append("<td>"+investigation.getInvstname()+"</td>");
				buffer.append("<td>"+investigation.getReporttype()+"</td>");
				buffer.append("<td>"+investigation.getInvstUnit()+"</td>");
				buffer.append("<td>"+investigation.getNormvalue()+"</td>");
				buffer.append("<td>"+investigation.getCritical_value()+"</td>");
				buffer.append("</tr>");
			}
			buffer.append("</table>");
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
