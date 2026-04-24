package com.apm.Master.web.action;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.html.Option;

import org.apache.struts2.ServletActionContext;

import com.apm.DiaryManagement.eu.bi.NotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCDiaryManagentDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCNotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.Emr.eu.bi.EmrDAO;
import com.apm.Emr.eu.bi.InvestigationDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCEmrDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCInvestigationDAO;
import com.apm.Emr.eu.entity.Investigation;
import com.apm.Emr.web.form.EmrForm;
import com.apm.Ipd.eu.bi.BedDao;
import com.apm.Ipd.eu.blogic.jdbc.JDBCBedDao;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Master.eu.bi.InvestigationMasterDAO;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCInvestigationMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class InvestigationMasterAction extends BaseAction implements
		ModelDriven<EmrForm>, Preparable {
	EmrForm emrForm = new EmrForm();
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpSession session = request.getSession(false);
	
	LoginInfo loginInfo=LoginHelper.getLoginInfo(request);

	private Pagination pagination = new Pagination(20, 1);
	private String mastername;

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public EmrForm getModel() {
		// TODO Auto-generated method stub
		return emrForm;
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
			
			int totalcount=masterDAO.getTotalInvestigationTypesCount(searchText);
			pagination.setPreperties(totalcount);
			
			BedDao bedDao=new JDBCBedDao(connection);
			ArrayList<Bed> wardlist=bedDao.getAllWardList("0");
			
			ArrayList<Master> invsTypeList = masterDAO
					.getAllInvestigationTypes(pagination,searchText,""+DateTimeUtils.isNull(emrForm.getIsdeleted()),wardlist);
			emrForm.setInvsTypeList(invsTypeList);
			
			pagination.setPage_records(invsTypeList.size());
			emrForm.setTotalRecords(totalcount);
			
			emrForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			mastername = request.getParameter("selectedid");
			if (mastername != null) {
				session.setAttribute("mastername", mastername);
			} else {

				mastername = (String) session.getAttribute("mastername");
			}
			emrForm.setMastername(mastername);
			InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
			ArrayList<Investigation> thirdPartyTypeNameList = new ArrayList<Investigation>();
			thirdPartyTypeNameList=investigationDAO.getallThirdpartynamelist();
			emrForm.setThirdPartyTypeNameList(thirdPartyTypeNameList);
			
			ArrayList<Investigation> wardnamelist = new ArrayList<Investigation>();
			wardnamelist=investigationDAO.getallwardnamelist();
			emrForm.setWardnamelist(wardnamelist);
			
			String invstid=request.getParameter("invstid");
			session.setAttribute("invstid", invstid);
			emrForm.setInvestnameid(invstid);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}

		return SUCCESS;
	}

	public String add() throws Exception{

		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			InvestigationMasterDAO investigationMasterDAO = new JDBCInvestigationMasterDAO(connection);
			ArrayList<Master> sectionlist = investigationMasterDAO.getAllSectionList();
			emrForm.setSectionlist(sectionlist);
			ArrayList<Master> alljobtitlelist = investigationMasterDAO.getAllJobTitle();
			emrForm.setAlljobtitlelist(alljobtitlelist);
			BedDao bedDao=new JDBCBedDao(connection);
			ArrayList<Bed> wardlist=bedDao.getAllWardList("0");
			emrForm.setWardlist(wardlist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "add";
	}
	
	
private ArrayList<Master> getReportTypeList() {
		
		ArrayList<Master> list=new ArrayList<Master>();
		Master master=new Master();
		master.setId(1);
		master.setReport_type("Numerical");
		list.add(master);
		Master master1=new Master();
		master1.setId(2);
		master1.setReport_type("Whiteup");
		list.add(master1);
		Master master2=new Master();
		master2.setId(3);
		master2.setReport_type("Text");
		list.add(master2);
		return list;
	}
	
	
	public String edit() throws Exception {

		if (!verifyLogin(request)) {

			return "login";
		}

		Connection connection = null;

		try {

			String selectedid = request.getParameter("selectedid");
			connection = Connection_provider.getconnection();
			InvestigationMasterDAO masterDAO = new JDBCInvestigationMasterDAO(
					connection);
			Master master = masterDAO.getInvestigationType(selectedid);
			emrForm.setId(Integer.parseInt(selectedid));
			
			emrForm.setName(master.getName());
			
			/*if(master.getName()!=null || !master.equals("")){
				String temp[] = master.getName().split("/");
				emrForm.setName(temp[0]);
			}*/
			BedDao bedDao=new JDBCBedDao(connection);
			ArrayList<Bed> wardlist=bedDao.getAllWardList("0");
			emrForm.setWardlist(wardlist);
			ArrayList<Master> sectionlist = masterDAO.getAllSectionList();
			emrForm.setSectionlist(sectionlist);
			emrForm.setSectionid(master.getSectionid());
			emrForm.setDepartment(master.getDepartment());
			emrForm.setReport_type(master.getReport_type());
			emrForm.setCharge(master.getCharge());
			emrForm.setGroup(master.getGroup());
			emrForm.setRoundcharge(master.isRoundcharge());
			emrForm.setDefaultremark(master.getDefaultremark());
			ArrayList<Master> alljobtitlelist = masterDAO.getAllJobTitle();
			emrForm.setAlljobtitlelist(alljobtitlelist);
			
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
			String defremark=request.getParameter("hiddenremark");
			connection = Connection_provider.getconnection();
			Master master = new Master();
			master.setId(emrForm.getId());
			master.setName(emrForm.getName());
            master.setReport_type(emrForm.getReport_type()); 
            master.setGroup(emrForm.getGroup());
            master.setCharge(emrForm.getCharge());
            master.setDepartment(emrForm.getDepartment());
            master.setRoundcharge(emrForm.isRoundcharge());
            master.setSectionid(emrForm.getSectionid());
            master.setDefaultremark(defremark);
            BedDao bedDao=new JDBCBedDao(connection);
            ArrayList<Bed> wardlist=bedDao.getAllWardList("0");
            String wardid=emrForm.getWardid();
			InvestigationMasterDAO masterDAO = new JDBCInvestigationMasterDAO(
					connection);
			if(wardid==null || wardid.equals("0")){
				for (Bed bed : wardlist) {
					boolean checkExistApptype=masterDAO.checkExistAppointType(master,bed.getId());
					if(checkExistApptype){
					int res=masterDAO.updateInvAppointType(master,bed.getId(),0);	
					}
					}
				boolean checkExistApptype=masterDAO.checkExistAppointType(master,0);
				
			}else{
				boolean checkExistApptype=masterDAO.checkExistAppointType(master,Integer.parseInt(wardid));
				if(checkExistApptype){
				int res=masterDAO.updateInvAppointType(master,Integer.parseInt(wardid),1);
				}
			}
			int result = masterDAO.updateInvestigationType(master);

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

		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection = null;

		try {

			connection = Connection_provider.getconnection();
			String selectedid = request.getParameter("selectedid");
			InvestigationMasterDAO dao = new JDBCInvestigationMasterDAO(
					connection);
//			int result = dao.deleteInvestigationType(selectedid);
			int result=dao.isdeletedInvestigationType(selectedid);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "save";
	}

	public String save() throws Exception {

		if (!verifyLogin(request)) {

			return "login";
		}

		Connection connection = null;

		try {
			String defaultremark= request.getParameter("hiddenremark");
			connection = Connection_provider.getconnection();
			InvestigationMasterDAO investigationMasterDAO = new JDBCInvestigationMasterDAO(
					connection);
			BedDao bedDao=new JDBCBedDao(connection);
			ArrayList<Bed> wardlist=bedDao.getAllWardList("0");
			if(emrForm.getInvestigation_type()!=null){
			for (Master master1 : emrForm.getInvestigation_type()) {

				/*Master master = new Master();
				master.setName(master1.getName());
                master.setReport_type(master1.getReport_type());
                master.setGroup(master1.getGroup());*/
				master1.setDefaultremark(defaultremark);
				int result = investigationMasterDAO
						.addInvestigationTypeMaster(master1);
				String wardid=master1.getWardid();
				if(wardid==null || wardid.equals("0")){
					for (Bed bed : wardlist) {
						boolean checkExistApptype=investigationMasterDAO.checkExistAppointType(master1,bed.getId());
						if(!checkExistApptype){
						int res=investigationMasterDAO.addInvAppointType(master1,bed.getId());	
						}
						}
					boolean checkExistApptype=investigationMasterDAO.checkExistAppointType(master1,0);
					if(!checkExistApptype){
					int res=investigationMasterDAO.addInvAppointType(master1,0);
					}
				}else{
					boolean checkExistApptype=investigationMasterDAO.checkExistAppointType(master1,Integer.parseInt(wardid));
					if(!checkExistApptype){
					int res=investigationMasterDAO.addInvAppointType(master1,Integer.parseInt(wardid));
					}
				}
			}
			}
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

			connection=Connection_provider.getconnection();
			InvestigationDAO investigationDAO=new JDBCInvestigationDAO(connection);
			InvestigationMasterDAO investigationMasterDAO = new JDBCInvestigationMasterDAO(connection);
			int rowcount = Integer.parseInt(request.getParameter("rowcount"));
			
			int index = rowcount;
			index--;
			
			ArrayList<Master> sectionlist = investigationMasterDAO.getAllSectionList();
			
			StringBuffer buffer = new StringBuffer();
			buffer.append("<tr>");
			buffer.append("<td><input type='checkbox' name='chkbox[]' title='Delete row' title='delete row' /></td>");
			buffer.append("<td>" + (rowcount) + "</td>");
			
			buffer.append("<td>");
			buffer.append("<select name='investigation_type["+index+"].department' class='form-control'>");
			buffer.append("<option value='0'>Select Department</option>");
			
			/*ArrayList<UserProfile> allInvsUserList=investigationDAO.getAllInvestigationUsers(loginInfo.getClinicid());
		    for(UserProfile userProfile:allInvsUserList){
		     	 
		      	      buffer.append("<option value='"+userProfile.getId()+"'>"+userProfile.getFullname()+"</option>");
		    }*/
			ArrayList<Master> jotitlelist = investigationMasterDAO.getAllJobTitle();
			for(Master master:jotitlelist){
		  	      buffer.append("<option value='"+master.getId()+"'>"+master.getName()+"</option>");
			}
			buffer.append("</select>");
			buffer.append("</td>");
		    buffer.append("<td>");
			buffer.append("<input type='text' name='investigation_type["
					+ index
					+ "].name' id='name' placeholder='enter name' class='form-control showToolTip filedname' data-toggle='tooltip' />");
			buffer.append("</td>");
			
			buffer.append("<input type='text' name='investigation_type["
					+ index
					+ "].group' id='group' placeholder='enter group name' class='form-control showToolTip filedname' data-toggle='tooltip' />");
			buffer.append("</td>");
			
			buffer.append("<td>");
			buffer.append("<select name='investigation_type["
					+ index
					+ "].report_type' id='invsTypeList' class='form-control' title = 'Select Report Type'>");
			/*buffer.append("<option value='0'>Select Report Type</option>");*/
			ArrayList<Master> reporttype_list=getReportTypeList();
			for (Master master : reporttype_list) {
				buffer.append("<option value='" + master.getReport_type() + "'>"
						+ master.getReport_type() + "</option>");
			}
			buffer.append("</select>");
			buffer.append("</td>");
            buffer.append("</td>");
			buffer.append("<input type='text' name='investigation_type["+ index+ "].charge' id='charge' placeholder='enter charge' class='form-control showToolTip filedname' data-toggle='tooltip' />");
			buffer.append("</td>");
			
			buffer.append("<td>");
			buffer.append("<select name='investigation_type["+ index + "].sectionid' id='sectionid' class='form-control' title = 'Select Section Id'>");
			buffer.append("<option value='0'>Select Section id</option>");
			for (Master master2 : sectionlist) {
				buffer.append("<option value='" + master2.getId() + "'>"+ master2.getName() + "</option>");
			}
			buffer.append("</select>");
			buffer.append("</td>");
			
			buffer.append("<td>");
			buffer.append("<input type='checkbox' name='investigation_type["+ index+ "].roundcharge' />");
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
	
	
	
	
	public void prepare() throws Exception {
		// TODO Auto-generated method stub

		Connection con = null;

		try {
			con = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(con);
			InvestigationDAO investigationDAO=new JDBCInvestigationDAO(con);
			InvestigationMasterDAO investigationMasterDAO = new JDBCInvestigationMasterDAO(con);
			ArrayList<Master> masterlist = masterDAO.getMasterList(loginInfo);
			emrForm.setMasterlist(masterlist);

			ArrayList<UserProfile> allInvsUserList=investigationDAO.getAllInvestigationUsers(loginInfo.getClinicid());
			emrForm.setAllInvsUserList(allInvsUserList);
			
			
			mastername = (String) session.getAttribute("mastername");
			emrForm.setMastername(mastername);
			
			ArrayList<Master> alljobtitlelist = investigationMasterDAO.getAllJobTitle();
			emrForm.setAlljobtitlelist(alljobtitlelist);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			con.close();
		}
	}
	public String getjobtitle()throws Exception{
	 	   Connection connection = null;
	 	   try {
	 	    String id = request.getParameter("jobtitle");
	 	    connection = Connection_provider.getconnection();
	 	   InvestigationDAO investigationDAO=new JDBCInvestigationDAO(connection);
			InvestigationMasterDAO investigationMasterDAO = new JDBCInvestigationMasterDAO(connection);
			JDBCDiaryManagentDAO diaryManagentDAO = new JDBCDiaryManagentDAO(connection);
	 	  /*  ArrayList<Master>list  = masterDAO.getSubTasklist(id);*/
			String name =diaryManagentDAO.getJobTitleNamefromID(Integer.parseInt(id));
	 	    ArrayList<Master>list = investigationMasterDAO.getSectionList(name);
	 	    
	 	    StringBuffer buffer=new StringBuffer();
	 	 /* <s:select list="sectionlist" listKey="id" listValue="name" name="investigation_type[0].sectionid" headerKey="0" headerValue="Select Section" cssClass="form-control" placeholder="enter section id" id="sectionid"></s:select>*/
	 	   buffer.append("<select name='investigation_type[0].sectionid' id='sectionid'>");
	 	   for(Master master: list){
	 	    buffer.append("<option value ='"+master.getId()+"'>"+master.getName()+"</option>");
	 	   }
	 	   buffer.append("</select>");
	 	   response.setContentType("text/html");
	 	   response.setHeader("Cache-Control", "no-cache");
	 	   response.getWriter().write(buffer.toString());
	 	   
	 	   } catch (Exception e) {
	 	    e.printStackTrace();
	 	   }finally{
	 	   connection.close();
	 	  }
	 	   return null;
	 	   
	 	  }
	public String addnewoutrow() throws Exception {

		Connection connection = null;

		try {

			connection=Connection_provider.getconnection();
			InvestigationDAO investigationDAO=new JDBCInvestigationDAO(connection);
			InvestigationMasterDAO investigationMasterDAO = new JDBCInvestigationMasterDAO(connection);
			int rowcount = Integer.parseInt(request.getParameter("rowcount"));
			EmrDAO emrDAO=new JDBCEmrDAO(connection);
			int index = rowcount;
			index--;
			
			ArrayList<Master> sectionlist = investigationMasterDAO.getAllSectionList();
			
			StringBuffer buffer = new StringBuffer();
			buffer.append("<tr>");
			buffer.append("<td><input type='checkbox' name='chkbox[]' title='Delete row' title='delete row' /></td>");
			buffer.append("<td>" + (rowcount) + "</td>");
			
			buffer.append("<td>");
			buffer.append("<select name='outsourcerate["+index+"].invsttype' class='form-control' style='width:50%;'>");
			buffer.append("<option value='0'>Select Investigation</option>");
			
			/*ArrayList<UserProfile> allInvsUserList=investigationDAO.getAllInvestigationUsers(loginInfo.getClinicid());
		    for(UserProfile userProfile:allInvsUserList){
		     	 
		      	      buffer.append("<option value='"+userProfile.getId()+"'>"+userProfile.getFullname()+"</option>");
		    }*/
			ArrayList<Master>invsTypeList = emrDAO.getInvesigationTypeList();
			for(Master master:invsTypeList){
		  	      buffer.append("<option value='"+master.getId()+"'>"+master.getName()+"</option>");
			}
			buffer.append("</select>");
			buffer.append("</td>");
			
			
			buffer.append("<input type='text' name='outsourcerate["+ index+ "].charge' id='charge' placeholder='Enter charge' class='form-control showToolTip filedname' style='width:31%;' data-toggle='tooltip' />");
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
	
	public String addnewInvst(){
		
		
		try {
			Connection connection=Connection_provider.getconnection();
			InvestigationDAO investigationDAO=new JDBCInvestigationDAO(connection);
			String tpid=request.getParameter("tpid");
			String ward=request.getParameter("ward");
			String chargename=request.getParameter("chargename");
			String code=request.getParameter("code");
			String amount=request.getParameter("amount");
			Investigation investigation=new Investigation();
			investigation.setThirdparty(tpid);
			investigation.setWardname(ward);
			investigation.setChargename(chargename);
			investigation.setCode(code);
			investigation.setAmount(amount);
			String shortname=investigationDAO.getShortnamebytpid(tpid);
			investigation.setShortname(shortname);
			String invstid=(String) session.getAttribute("invstid");
			investigation.setInvestnameid(invstid);
			//investigation.setCharge(charge);
			int result=investigationDAO.savealternatInvstnameByid(investigation);
			int appttype=investigationDAO.saveInvestigationdetail(investigation);
			response.setContentType("text/html");
		    response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+result+"");
		} catch (Exception e) {
			e.printStackTrace();		}
		
		return null;
	}
}
