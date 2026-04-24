package com.apm.Master.web.action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Ipd.eu.bi.BedDao;
import com.apm.Ipd.eu.blogic.jdbc.JDBCBedDao;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.bi.SittingFollowupDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCSittingFollowupDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.SittingFollowup;
import com.apm.Master.web.form.MasterForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.Pagination;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;


public class CreateNewChargeTypeAction extends ActionSupport implements
		ModelDriven<MasterForm>, Preparable {

	MasterForm form = new MasterForm();
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
    HttpSession session=request.getSession(false);
    LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
    Pagination pagination=new Pagination(25,1);
	
	String mastername;
	
	public MasterForm getModel() {
		// TODO Auto-generated method stub
		return form;
	}

	
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		Connection connection = null;
		try {

			connection = Connection_provider.getconnection();
			MasterDAO dao = new JDBCMasterDAO(connection);
			int count=dao.getTotalNewchargeCount();
			pagination.setPreperties(count);
			String searchText = form.getSearchText();
			
			if(searchText!=null){
				if(searchText.equals("")){
					searchText=null;
				}
			}
			
			ArrayList<Master> new_chargetye_list = dao.getNewChargeTypeList(searchText,pagination);
			form.setNew_chargetye_list(new_chargetye_list);
			pagination.setPage_records(new_chargetye_list.size());
			form.setPagerecords(String.valueOf(pagination.getPage_records()));
			form.setTotalRecords(count);

			mastername = request.getParameter("selectedid");
			if (mastername != null) {

				session.setAttribute("mastername", mastername);
			} else {
				mastername = (String) session.getAttribute("mastername");
			}
			form.setMastername(mastername);
			
			
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}
		return SUCCESS;
	}

	public String add() {
		return "add";
	}

	public String save() throws Exception {

		Connection connection = null;
		try {

			connection = Connection_provider.getconnection();
			MasterDAO dao = new JDBCMasterDAO(connection);
			Master master = new Master();
			master.setId(form.getId());
			master.setName(form.getName());
			master.setDescription(form.getDescription());
			master.setProcedure(form.isProcedure());
			master.setConsultant_compulsay(form.isConsultant_compulsay());
			String sitting = request.getParameter("sitting_id");
			master.setSitting(sitting);
			/*master.setWardid(form.getWardid());*/
			dao.addNewChargeType(master);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "save";
	}

	public String edit() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			int id = Integer.parseInt(request.getParameter("selectedid"));
			Master master = new Master();
			MasterDAO dao = new JDBCMasterDAO(connection);
			master = dao.getNewChargeType(id, master);
			form.setId(master.getId());
			form.setName(master.getName());
			form.setDescription(master.getDescription());
			form.setProcedure(master.isProcedure());
			form.setConsultant_compulsay(master.isConsultant_compulsay());
			/*form.setWardid(master.getWardid());*/
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "edit";
	}

	public String update() throws Exception {

		Connection connection = null;
		try {

			Master master = new Master();
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			master.setId(form.getId());
			master.setName(form.getName());
			master.setDescription(form.getDescription());
			master.setProcedure(form.isProcedure());
			master.setConsultant_compulsay(form.isConsultant_compulsay());
			/*master.setWardid(form.getWardid());*/
			masterDAO.updateNewChargetype(master);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "save";
	}

	public String back() {
		return "save";
	}

	public String delete() throws Exception {

		Connection connection = null;
		try {
			int id = Integer.parseInt(request.getParameter("selectedid"));
			connection = Connection_provider.getconnection();
			MasterDAO dao = new JDBCMasterDAO(connection);
			dao.deleteNewChargeType(id);
             
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "save";
	}

	
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			ArrayList<Master> masterlist = masterDAO.getMasterList(loginInfo);
			ArrayList<Master>disciplineList =  masterDAO.getDisciplineDataList();
			form.setDisciplineList(disciplineList);
			form.setMasterlist(masterlist);
			BedDao bedDao = new JDBCBedDao(connection);
			ArrayList<Bed> wardList = bedDao.getAllWardList("0");
			form.setWardList(wardList);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}
	}


	public Pagination getPagination() {
		return pagination;
	}


	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	public String updateBreakage() throws Exception{
		try {
		Connection connection = null;
		String id= request.getParameter("id");
		String checked=request.getParameter("checked");
		if(checked==null){
			checked="0";
		}
		if(checked.equals("")){
			checked="0";
		}
		if(checked.equals("true")){
			checked="1";
		}else{
			checked="0";
		}
		
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO=new JDBCMasterDAO(connection);
			int res=masterDAO.setbreakage(id,checked);
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
		
				response.getWriter().write(""+res+"");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		
		
		
		return null;
		
	}
	public String checkedAllBreakage() throws Exception{
		try{
			Connection connection = null;
			String selecteditem="";
			String selected=request.getParameter("allval");
			for (String t : selected.split(",")) {
				if (t.equals("")) {
					continue;
				}
				selecteditem = t;
				connection = Connection_provider.getconnection();
				MasterDAO masterDAO=new JDBCMasterDAO(connection);
				int res=masterDAO.setAllbreakage(selecteditem);
			}
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
		
				response.getWriter().write("");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	
	}
	
	
	public String getsittinglist() throws Exception{
		
		Connection connection=null;
		
		try {
			
			connection=Connection_provider.getconnection();
			String id=request.getParameter("id");
			
			SittingFollowupDAO sittingFollowupDAO=new JDBCSittingFollowupDAO(connection);
			
			ArrayList<SittingFollowup>list=sittingFollowupDAO.getAllSittingList(id);
			StringBuffer buffer=new StringBuffer();
			
			buffer.append("<label>Select Sitting</label>");
			buffer.append("<select name='sitting_id' id='sitting_id' class='form-control chosen-select'>");
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


}
	