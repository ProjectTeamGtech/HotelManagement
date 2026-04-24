package com.apm.Master.web.action;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Appointment.eu.bi.AppointmentDAO;
import com.apm.Appointment.eu.blogic.jdbc.JDBCAppointmentDAO;
import com.apm.Ipd.eu.bi.BedDao;
import com.apm.Ipd.eu.blogic.jdbc.JDBCBedDao;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.web.form.MasterForm;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class StandardChargesAction extends BaseAction implements Preparable,ModelDriven<MasterForm> {

	
    MasterForm masterForm=new MasterForm();
	
    HttpServletRequest request=ServletActionContext.getRequest();
    HttpServletResponse response=ServletActionContext.getResponse();
    HttpSession session=request.getSession(false);
    Pagination pagination=new Pagination(10,1);
    private String mastername;
    LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
    
   
	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public MasterForm getModel() {
		// TODO Auto-generated method stub
		return masterForm;
	}

	public String execute() throws Exception {

		Connection connection=null;
		
		try {
			connection=Connection_provider.getconnection();
			MasterDAO masterDAO=new JDBCMasterDAO(connection);
		
			String SearchText=masterForm.getSearchText();
			if(SearchText!=null)
			{
				if(SearchText.equals(""))
				{
					SearchText=null;
				}
			}
			
			
			int count=masterDAO.getTotalStandardChargesCount();
			pagination.setPreperties(count);
			
			ArrayList<Master> standardChargesList=masterDAO.getStandardChargesList(pagination,SearchText);
			pagination.setPage_records(standardChargesList.size());
			
			
			masterForm.setStandardChargesList(standardChargesList);
			masterForm.setTotalRecords(count);
			masterForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			
			mastername = request.getParameter("selectedid");
			if (mastername != null) {

				session.setAttribute("mastername", mastername);
			} else {
				mastername = (String) session.getAttribute("mastername");
			}
			masterForm.setMastername(mastername);
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		
		
		return super.execute();
	}
	

	public String add() {
		
		if(!verifyLogin(request)){
			return "login";
		}
		
		return "add";
	}
	
	
	public String addnewrow() throws Exception {

		Connection connection = null;

		try {

			connection=Connection_provider.getconnection();
			BedDao bedDao=new JDBCBedDao(connection);
			AppointmentDAO appointmentDAO=new JDBCAppointmentDAO(connection);
			ArrayList<Bed> wardList=bedDao.getAllWardList("0"); 
			
			ArrayList<Master>masterChageTypeList = appointmentDAO.getmasterChageTypeList(loginInfo);
			int rowcount = Integer.parseInt(request.getParameter("rowcount"));
			int index = rowcount;
			index--;
			StringBuffer buffer = new StringBuffer();
			buffer.append("<tr>");
			buffer.append("<td><input type='checkbox' name='chkbox[]' title='Delete row' title='delete row' /></td>");
			buffer.append("<td>" + (rowcount) + "</td>");
			buffer.append("<td>");
			buffer.append("<select name='standardcharges["
					+ index
					+ "].wardid' id='wardid' class='form-control' title = 'Select Ward'>");
			buffer.append("<option value='0'>Select Ward</option>");
			for (Bed bed : wardList) {
				buffer.append("<option value='"+bed.getId()+"'>"
						+ bed.getWardname() + "</option>");
			}
			buffer.append("</select>");
			buffer.append("</td>");
			
			buffer.append("<td>");
			buffer.append("<select name='standardcharges["+index+"].payby' id='payby' class='form-control' title='Select Pay By' >");
			buffer.append("<option value='Self'>Self</option>");
			buffer.append("<option value='Third Party'>Third Party</option>");
			buffer.append("</select>");
			buffer.append("</td>");
		    buffer.append("<td>");
			buffer.append("<input type='text' name='standardcharges["
					+ index
					+ "].name' id='name' placeholder='enter charge name' class='form-control showToolTip filedname' data-toggle='tooltip' />");
			buffer.append("</td>");
			
			buffer.append("<input type='text' name='standardcharges["
					+ index
					+ "].charge' id='charge' placeholder='enter amount' class='form-control showToolTip filedname' data-toggle='tooltip' />");
			buffer.append("</td>");
			
			buffer.append("</tr>");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(buffer.toString());

		} catch (Exception e) {

			e.printStackTrace();
		}

		finally {

		//	connection.close();
		}

		return null;
	}
	
	
	public String save() throws Exception{
		
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			MasterDAO masterDAO=new JDBCMasterDAO(connection);
			
			
			for(Master master:masterForm.getStandardcharges()) {
				  
				  int result=masterDAO.addStandardCharge(master); 
			}
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
	  return "save";	
	}
	
	
	public String edit() throws Exception {
		
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection=null;
		
		try {
			connection=Connection_provider.getconnection();
			MasterDAO masterDAO=new JDBCMasterDAO(connection);
			
			String id=request.getParameter("selectedid");
			
			Master master=masterDAO.getStandardCharge(id);
			
			masterForm.setId(master.getId());
			masterForm.setName(master.getName());
			masterForm.setCharge(master.getCharge());
			masterForm.setWardid(master.getWardid());
			masterForm.setPayby(master.getPayby());
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		finally {
			connection.close();
		}
	
		return "edit";
	}
	
	
	public String update() throws Exception {
		
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection=null;
		
		try {
			connection=Connection_provider.getconnection();
			MasterDAO masterDAO=new JDBCMasterDAO(connection);
			
			Master master=new Master();
			master.setId(masterForm.getId());
			master.setName(masterForm.getName());
			master.setCharge(masterForm.getCharge());
			master.setWardid(masterForm.getWardid());
			master.setPayby(masterForm.getPayby());
			
			
			int result=masterDAO.updateStandardCharge(master);
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			 connection.close();
		}
		
		
		return "save";
	}
	
	public String delete() throws Exception {
		
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection=null;
		
		try {
			connection=Connection_provider.getconnection();
			String id=request.getParameter("selectedid");
			MasterDAO masterDAO=new JDBCMasterDAO(connection);
			
			int res=masterDAO.deleteStandardCharge(id);
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		
		return "save";
	}
	
	
	
	public void prepare() throws Exception {

		Connection con = null;

		try {
			con = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(con);
			AppointmentDAO appointmentDAO=new JDBCAppointmentDAO(con);
			BedDao bedDao=new JDBCBedDao(con);
			
			ArrayList<Master> masterlist = masterDAO.getMasterList(loginInfo);
			masterForm.setMasterlist(masterlist);

			mastername = (String) session.getAttribute("mastername");
			masterForm.setMastername(mastername);
			
			ArrayList<Bed> wardList=bedDao.getAllWardList("0"); 
			masterForm.setWardList(wardList);
			

			ArrayList<Master>masterChageTypeList = appointmentDAO.getmasterChageTypeList(loginInfo);
			masterForm.setMasterChageTypeList(masterChageTypeList);
			
		
			ArrayList<String> paybyList=new ArrayList<String>();
			paybyList.add("Self");
			paybyList.add("Third Party");
			masterForm.setPaybyList(paybyList);
			
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			con.close();
		}
	}
	
	
}
