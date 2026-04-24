package com.apm.Master.web.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.apm.Appointment.eu.bi.AppointmentTypeDAO;
import com.apm.Appointment.eu.blogic.jdbc.JDBCAppointmentTypeDAO;
import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.Appointment.web.form.AppointmentTypeForm;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.Emr.eu.bi.EmrDAO;
import com.apm.Emr.eu.bi.InvestigationDAO;
import com.apm.Emr.eu.bi.PrescriptionDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCEmrDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCInvestigationDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCPrescriptionDAO;
import com.apm.Emr.eu.entity.Investigation;
import com.apm.Inventory.eu.bi.InventoryProductDAO;
import com.apm.Inventory.eu.bi.InventoryVendorDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryProductDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryVendorDAO;
import com.apm.Inventory.eu.entity.Product;
import com.apm.Ipd.eu.bi.BedDao;
import com.apm.Ipd.eu.blogic.jdbc.JDBCBedDao;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Master.eu.bi.BookDAO;
import com.apm.Master.eu.bi.InvestigationMasterDAO;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.bi.StateDAO;
import com.apm.Master.eu.bi.TreatmentTypeDAO;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCBookDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCInvestigationMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCStateDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCTreatmentTypeDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.entity.Book;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.State;
import com.apm.Master.eu.entity.TreatmentType;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.web.form.MasterForm;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.ThirdParties.eu.bi.ThirdPartyDAO;
import com.apm.ThirdParties.eu.blogic.jdbc.JDBCThirdPartyDAO;
import com.apm.ThirdParties.eu.entity.ThirdParty;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import atg.taglib.json.util.JSONArray;
import atg.taglib.json.util.JSONObject;

public class MasterAction extends BaseAction implements Preparable, ModelDriven<MasterForm> {

	MasterForm masterForm = new MasterForm();
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse) ActionContext.getContext()
			.get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	Pagination pagination = new Pagination(25, 1);
	private String mastername;
	private String test;
	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public String occupation() {

		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();

			MasterDAO masterDAO = new JDBCMasterDAO(connection);

			int totalCount = masterDAO.getTotalOccupationCount(null);
			pagination.setPreperties(totalCount);

			ArrayList<Master> occupationList = masterDAO.getOccupationList(pagination, null);
			pagination.setPage_records(occupationList.size());
			masterForm.setTotalRecords(totalCount);
			masterForm.setPagerecords(Integer.toString(pagination.getPage_records()));
			masterForm.setOccupationList(occupationList);
			session.setAttribute("pagination", pagination);

			mastername = request.getParameter("selectedid");
			if (mastername != null) {

				session.setAttribute("mastername", mastername);
			} else {
				mastername = (String) session.getAttribute("mastername");
			}
			masterForm.setMastername(mastername);

		} catch (Exception e) {

		}

		return "occupationPage";

	}

	public void setFormData() {
		Connection connection = null;
		int result = 0;
		try {
			connection = Connection_provider.getconnection();
			Master master = new Master();

			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			pagination = (Pagination) session.getAttribute("pagination");
			int totalCount = masterDAO.getTotalOccupationCount(null);
			pagination.setPreperties(totalCount);

			ArrayList<Master> occupationList = masterDAO.getOccupationList(pagination, null);
			pagination.setPage_records(occupationList.size());
			masterForm.setTotalRecords(totalCount);
			masterForm.setPagerecords(Integer.toString(pagination.getPage_records()));
			masterForm.setOccupationList(occupationList);
		} catch (Exception e) {

		}
	}

	public String addOccupation() {

		if (!verifyLogin(request)) {
			return "login";
		}

		return "addOccupationPage";
	}

	public String saveOccupation() {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		int result = 0;
		try {
			connection = Connection_provider.getconnection();
			Master master = new Master();

			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			master.setOccupation(masterForm.getOccupation());

			result = masterDAO.saveOccupation(master);

			addActionMessage("Occupation Added Sucessfully!!");
			setFormData();
		} catch (Exception e) {

		}

		return "occupationPage";
	}

	public String editOccupation() {
		if (!verifyLogin(request)) {
			return "login";
		}
		int id = Integer.parseInt(request.getParameter("selectedid"));
		Connection connection = null;

		Master master = new Master();
		try {

			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);

			master = masterDAO.getOccupation(id, master);
			masterForm.setId(master.getId());
			masterForm.setOccupation(master.getOccupation());
		} catch (Exception e) {
			// TODO: handle exception
		}

		return "editOccupationPage";
	}

	public String updateOccupation() {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		int result = 0;
		try {
			int id = masterForm.getId();
			connection = Connection_provider.getconnection();
			Master master = new Master();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);

			master.setOccupation(masterForm.getOccupation());

			result = masterDAO.updateOccupation(master, id);

			addActionMessage("Occupation Updated Sucessfully!!");
			setFormData();
		} catch (Exception e) {

		}

		return "occupationPage";
	}

	public String deleteOccupation() {
		if (!verifyLogin(request)) {
			return "login";
		}
		int result = 0;
		int id = Integer.parseInt(request.getParameter("selectedid"));
		Connection connection = null;
		Master master = new Master();

		try {

			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);

			result = masterDAO.deleteOccupation(id, master);

			addActionMessage("Occupation Deleted Sucessfully!!");
			setFormData();
		} catch (Exception e) {

		}

		return "occupationPage";
	}

	public String jobTitle() {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();

			MasterDAO masterDAO = new JDBCMasterDAO(connection);

			int totalCount = masterDAO.getTotalJobTitleCount();
			pagination.setPreperties(totalCount);

			ArrayList<Master> jobTitleList = masterDAO.getJobTitleList(pagination);
			pagination.setPage_records(jobTitleList.size());
			masterForm.setTotalRecords(totalCount);
			masterForm.setPagerecords(Integer.toString(pagination.getPage_records()));
			masterForm.setJobTitleList(jobTitleList);
			session.setAttribute("pagination", pagination);
		} catch (Exception e) {

		}
		return "jobTitlePage";

	}

	public String addJobTitle() {
		if (!verifyLogin(request)) {
			return "login";
		}
		return "addJobTitlePage";
	}

	public void setFormDataofJobTitle() {
		Connection connection = null;
		int result = 0;
		try {
			connection = Connection_provider.getconnection();
			Master master = new Master();

			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			pagination = (Pagination) session.getAttribute("pagination");
			int totalCount = masterDAO.getTotalJobTitleCount();
			pagination.setPreperties(totalCount);

			ArrayList<Master> jobTitleList = masterDAO.getJobTitleList(pagination);
			pagination.setPage_records(jobTitleList.size());
			masterForm.setTotalRecords(totalCount);
			masterForm.setPagerecords(Integer.toString(pagination.getPage_records()));
			masterForm.setJobTitleList(jobTitleList);
		} catch (Exception e) {

		}
	}

	public String saveJobTitle() {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		int result = 0;
		try {
			connection = Connection_provider.getconnection();
			Master master = new Master();

			MasterDAO masterDAO = new JDBCMasterDAO(connection);

			master.setJobTitle(masterForm.getJobTitle());

			result = masterDAO.saveJobTitle(master);
			addActionMessage("Job Title Added Sucessfully!!");
			setFormDataofJobTitle();
		} catch (Exception e) {

		}
		return "jobTitlePage";

	}

	public String editJobTitle() {
		if (!verifyLogin(request)) {
			return "login";
		}
		int id = Integer.parseInt(request.getParameter("selectedid"));
		Connection connection = null;

		Master master = new Master();
		try {

			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);

			master = masterDAO.getJobTitle(id, master);
			masterForm.setId(master.getId());
			masterForm.setJobTitle(master.getJobTitle());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "editJobTitlePage";
	}

	public String updateJobTitle() {
		Connection connection = null;
		int result = 0;
		try {
			int id = masterForm.getId();
			connection = Connection_provider.getconnection();
			Master master = new Master();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);

			master.setJobTitle(masterForm.getJobTitle());

			result = masterDAO.updateJobTitle(master, id);
			addActionMessage("Job Title Updated Sucessfully!!");
			setFormDataofJobTitle();
		} catch (Exception e) {

		}
		return "jobTitlePage";
	}

	public String deleteJobTitle() {
		int result = 0;
		int id = Integer.parseInt(request.getParameter("selectedid"));
		Connection connection = null;
		Master master = new Master();

		try {

			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);

			result = masterDAO.deleteJobTitle(id, master);

			addActionMessage("Job Title Deleted Sucessfully!!");
			setFormDataofJobTitle();
		} catch (Exception e) {

		}
		return "jobTitlePage";
	}

	public String reference() {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();

			MasterDAO masterDAO = new JDBCMasterDAO(connection);

			int totalCount = masterDAO.getTotalReferenceCount();
			pagination.setPreperties(totalCount);

			ArrayList<Master> referenceList = masterDAO.getReferenceList(pagination);
			pagination.setPage_records(referenceList.size());
			masterForm.setTotalRecords(totalCount);
			masterForm.setPagerecords(Integer.toString(pagination.getPage_records()));
			masterForm.setReferenceList(referenceList);
			session.setAttribute("pagination", pagination);
		} catch (Exception e) {

		}
		return "referencePage";
	}

	public void setFormDataOfReference() {
		Connection connection = null;
		int result = 0;
		try {
			connection = Connection_provider.getconnection();
			Master master = new Master();

			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			int totalCount = masterDAO.getTotalReferenceCount();
			pagination.setPreperties(totalCount);

			ArrayList<Master> referenceList = masterDAO.getReferenceList(pagination);
			pagination.setPage_records(referenceList.size());
			masterForm.setTotalRecords(totalCount);
			masterForm.setPagerecords(Integer.toString(pagination.getPage_records()));
			masterForm.setReferenceList(referenceList);
		} catch (Exception e) {

		}
	}

	public String addReference() {
		if (!verifyLogin(request)) {
			return "login";
		}
		return "addReferencePage";
	}

	public String saveReference() {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		int result = 0;
		try {
			connection = Connection_provider.getconnection();
			Master master = new Master();

			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			master.setReference(masterForm.getReference());

			result = masterDAO.saveReference(master);
			addActionMessage("Reference Added Sucessfully!!");
			setFormDataOfReference();

		} catch (Exception e) {

		}
		return "referencePage";
	}

	public String editReference() {
		if (!verifyLogin(request)) {
			return "login";
		}
		int id = Integer.parseInt(request.getParameter("selectedid"));
		Connection connection = null;

		Master master = new Master();
		try {

			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);

			master = masterDAO.getReference(id, master);
			masterForm.setId(master.getId());
			masterForm.setReference(master.getReference());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "editReferencePage";

	}

	public String updateReference() {
		if (!verifyLogin(request)) {
			return "login";
		}
		Connection connection = null;
		int result = 0;
		try {
			int id = masterForm.getId();
			connection = Connection_provider.getconnection();
			Master master = new Master();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			master.setReference(masterForm.getReference());
			result = masterDAO.updateReference(master, id);

			addActionMessage("Reference Updated Sucessfully!!");

			setFormDataOfReference();
		} catch (Exception e) {

		}
		return "referencePage";
	}

	public String deleteReference() {
		if (!verifyLogin(request)) {
			return "login";
		}
		int result = 0;
		int id = Integer.parseInt(request.getParameter("selectedid"));
		Connection connection = null;
		Master master = new Master();

		try {

			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);

			result = masterDAO.deleteReference(id, master);
			addActionMessage("Reference Deleted Sucessfully!!");

			setFormDataOfReference();
		} catch (Exception e) {

		}
		return "referencePage";
	}

	public void prepare() throws Exception {

		Connection connection = null;

		try {
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			ArrayList<Master> masterlist = masterDAO.getMasterList(loginInfo);
			masterForm.setMasterlist(masterlist);
			
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			connection.close();
		}
	}

	// bookchapter
	public String showallbookChapter() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();

			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			int count = masterDAO.getBookChapterCount();
			pagination.setPreperties(count);
			String searchText = masterForm.getSearchText();
			if (searchText != null) {
				if (searchText.equals("")) {
					searchText = null;
				}
			}

			ArrayList<Master> bookChapterlist = masterDAO.getallBookChapter(searchText, pagination);

			masterForm.setBookChapterlist(bookChapterlist);
			mastername = request.getParameter("selectedid");
			pagination.setPage_records(bookChapterlist.size());
			masterForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			masterForm.setTotalRecords((count));
			if (mastername != null) {

				session.setAttribute("mastername", mastername);

			} else {

				mastername = (String) session.getAttribute("mastername");
			}
			masterForm.setMastername(mastername);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return "showallbookChapter";
	}

	public String save() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			Master master = new Master();
			master.setBook_chapter_name(masterForm.getBook_chapter_name());
			master.setBook_chapter_link(masterForm.getBook_chapter_link());
			master.setBook_chapter_discription(masterForm.getBook_chapter_discription());
			master.setBook_chapter_parentid(masterForm.getBook_chapter_parentid());
			// master.setBook_chapter_id(masterForm.getBook_chapter_id());
			int result = masterDAO.addbookChapterDB(master);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "savebookChapter";
	}

	public String edit() {
		Connection connection;
		try {
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			Master master = new Master();
			connection = Connection_provider.getconnection();
			BookDAO book = new JDBCBookDAO(connection);
			ArrayList<Book> booklist = book.getallBooks(null, null);
			masterForm.setBooklist(booklist);
			masterForm.setBook_chapter_id(Integer.parseInt(request.getParameter("id")));
			master.setBook_chapter_id(masterForm.getBook_chapter_id());
			Master book2 = masterDAO.getBookChapterinfo(master);
			
			masterForm.setBook_chapter_name(book2.getBook_chapter_name());
			masterForm.setBook_chapter_parentid(book2.getBook_chapter_parentid());
			masterForm.setBook_chapter_link(book2.getBook_chapter_link());
			masterForm.setBook_chapter_discription(book2.getBook_chapter_discription());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "editbookChapter";
	}

	public String update() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			Master master = new Master();
			master.setBook_chapter_id(masterForm.getBook_chapter_id());
			master.setBook_chapter_name(masterForm.getBook_chapter_name());
			master.setBook_chapter_parentid(masterForm.getBook_chapter_parentid());
			master.setBook_chapter_link(masterForm.getBook_chapter_link());
			master.setBook_chapter_discription(masterForm.getBook_chapter_discription());
			int result = masterDAO.updateBookChapterDB(master);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "updatebookChapter";
	}

	public String delete() {
		Connection connection;
		try {
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			Master master = new Master();
			String i=request.getParameter("id");
			master.setBook_chapter_id(Integer.parseInt(i));
			master.setBook_chapter_name(masterForm.getBook_chapter_name());
			int result = masterDAO.deleteBookChapterDB(master);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "deletedbookChapter";
	}

	public String add() {
		Connection connection;
		try {
			connection = Connection_provider.getconnection();
			BookDAO book = new JDBCBookDAO(connection);
			ArrayList<Book> booklist = book.getallBooks(null, null);
			masterForm.setBooklist(booklist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "addbookChapter";
	}

	/*public String addOutSource() {
		Connection connection;
		try {
			connection = Connection_provider.getconnection();
			MasterDAO masterdao= new JDBCMasterDAO(connection);
			ArrayList<Master> masterlist = masterdao.getallOutsource(null, null);
			masterForm.setBooklist(booklist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "addbookChapter";
	}
*/
	
	
	
	public String showallOutSource()throws Exception{
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			
			MasterDAO masterdao= new JDBCMasterDAO(connection);
			int count=masterdao.getOutsourceCount();
			pagination.setPreperties(count);
			String searchText = masterForm.getSearchText();
			if(searchText!=null){
				if(searchText.equals("")){
					searchText=null;
				}
			}
			
			ArrayList<Master> masterlist11 = masterdao.getallOutsource(searchText, pagination);
			
			masterForm.setOutsourcelist(masterlist11);
			mastername=request.getParameter("selectedid");
			pagination.setPage_records(masterlist11.size());
			masterForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			masterForm.setTotalRecords(count);
			if(mastername!=null){
				
				 session.setAttribute("mastername", mastername);
				
			} else {
				
				mastername=(String)session.getAttribute("mastername");
			}
			masterForm.setMastername(mastername);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		
		
		return "showallOutSource";
	}
	public String saveOutSource() throws Exception{
		Connection connection=null;
		try {
			connection = Connection_provider.getconnection();
			MasterDAO masterdao= new JDBCMasterDAO(connection);
		Master master= new Master();
			master.setOutsource_name(masterForm.getOutsource_name());
			int result = masterdao.addOutsourceDB(master);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		connection.close();
		}
		return "saveOutSource";
	}
	public String editOutSource(){
		Connection connection;
		try {
			String id=request.getParameter("id");
			connection = Connection_provider.getconnection();
			MasterDAO masterdao= new JDBCMasterDAO(connection);
			Master master= new Master();
			master.setOutsource_id(Integer.parseInt(id));
			Master master2=masterdao.getOutsourceinfo(master);
		masterForm.setOutsource_id(master2.getOutsource_id());
			masterForm.setOutsource_name(master2.getOutsource_name());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "editOutSource";
	}
	public String updateOutSource() throws Exception{
		Connection connection=null;
		try {
			connection = Connection_provider.getconnection();
			MasterDAO masterdao= new JDBCMasterDAO(connection);
			Master master= new Master();
		
			master.setOutsource_id(masterForm.getOutsource_id());
			master.setOutsource_name(masterForm.getOutsource_name());
			int result =masterdao.updateOutsourceDB(master);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		connection.close();
		}
		return "updateOutSource";
	}
	
	public String deleteOutSource(){
		Connection connection;
		try {
			connection = Connection_provider.getconnection();
			MasterDAO masterdao= new JDBCMasterDAO(connection);
			Master master= new Master();
			String id=request.getParameter("id");
			master.setOutsource_id(Integer.parseInt(id));
			master.setOutsource_name(masterForm.getOutsource_name());
			int result = masterdao.deleteOutsourceDB(master);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "deletedOutSource";
	}
	public String addOutSource(){
		return "addOutSource";	
	}

	//outsourcedatadatamaster
	
	public String showallOutSourceData()throws Exception{
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			
			MasterDAO masterdao= new JDBCMasterDAO(connection);
			int count=masterdao.getOutsourceDataCount();
			pagination.setPreperties(count);
			String searchText = masterForm.getSearchText();
			if(searchText!=null){
				if(searchText.equals("")){
					searchText=null;
				}
			}
			
			ArrayList<Master> masterlist1 = masterdao.getallOutsourceData(searchText, pagination);
			
			masterForm.setOutsourceDatalist(masterlist1);
			mastername=request.getParameter("selectedid");
			pagination.setPage_records(masterlist1.size());
			masterForm.setPagerecords(String.valueOf(pagination.getPage_records()));
			masterForm.setTotalRecords(count);
			if(mastername!=null){
				
				 session.setAttribute("mastername", mastername);
				
			} else {
				
				mastername=(String)session.getAttribute("mastername");
			}
			masterForm.setMastername(mastername);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		
		
		return "showallOutSourceData";
	}
	public String saveOutSourceData() throws Exception{
		Connection connection=null;
		try {
			connection = Connection_provider.getconnection();
			MasterDAO masterdao= new JDBCMasterDAO(connection);
		Master master= new Master();
		
			master.setOutsource_id(masterForm.getOutsource_id());
			master.setInvestigstion_id(masterForm.getInvestigstion_id());
			master.setAmmount(masterForm.getAmmount());
			master.setSharing_type(masterForm.getSharing_type());
			int result = masterdao.addOutsourceDataDB(master);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		connection.close();
		}
		return "saveOutSourceData";
	}
	public String editOutSourceData(){
		Connection connection;
		try {
			String id=request.getParameter("id");
			connection = Connection_provider.getconnection();
			MasterDAO masterdao= new JDBCMasterDAO(connection);
			Master master= new Master();
			master.setOutsource_data_id(Integer.parseInt(id));
			Master master2=masterdao.getOutsourceDatainfo(master);
	
		masterForm.setOutsource_data_id(master2.getOutsource_data_id());
		masterForm.setOutsource_id(master2.getOutsource_id());
			masterForm.setOutsource_name(master2.getOutsource_name());
			masterForm.setInvestigstion_id(master2.getInvestigation_type_id());
			masterForm.setAmmount(master2.getAmmount());
			masterForm.setSharing_type(master2.getSharing_type());
			
			ArrayList<Master> invst= new ArrayList<Master>();
			invst= masterdao.getallInvestigationtype();
			ArrayList<Master> outsourcelist= new ArrayList<Master>();
			outsourcelist= masterdao.getallOutsource(null, null);
			masterForm.setInvestigationlist(invst);
			masterForm.setOutsourceDatalist(outsourcelist);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "editOutSourceData";
	}
	public String updateOutSourceData() throws Exception{
		Connection connection=null;
		try {
			connection = Connection_provider.getconnection();
			MasterDAO masterdao= new JDBCMasterDAO(connection);
			Master master= new Master();
		
			master.setOutsource_data_id(masterForm.getOutsource_data_id());
		master.setOutsource_id(masterForm.getOutsource_id());
		master.setInvestigation_type_id(masterForm.getInvestigstion_id());
		master.setAmmount(masterForm.getAmmount());
		master.setSharing_type(masterForm.getSharing_type());
			int result =masterdao.updateOutsourceDataDB(master);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		connection.close();
		}
		return "updateOutSourceData";
	}
	
	public String deleteOutSourceData(){
		Connection connection;
		try {
			connection = Connection_provider.getconnection();
			MasterDAO masterdao= new JDBCMasterDAO(connection);
			Master master= new Master();
			String id=request.getParameter("id");
			master.setOutsource_data_id(Integer.parseInt(id));
			master.setOutsource_name(masterForm.getOutsource_name());
			int result = masterdao.deleteOutsourceDataDB(master);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "deletedOutSourceData";
	}
	public String addOutSourceData(){
		Connection connection;
		try{
		connection = Connection_provider.getconnection();
		MasterDAO masterdao= new JDBCMasterDAO(connection);
		ArrayList<Master> invst= new ArrayList<Master>();
		invst= masterdao.getallInvestigationtype();
		ArrayList<Master> outsourcelist= new ArrayList<Master>();
		outsourcelist= masterdao.getallOutsource(null, null);
		masterForm.setInvestigationlist(invst);
		masterForm.setOutsourceDatalist(outsourcelist);
		Master master= new Master();
		}catch(Exception e){
			e.printStackTrace();
		}
		return "addOutSourceData";	
	}

	public String showallMedicineTemplate()throws Exception{
		 Connection connection= null;
		 
		 try {
		  connection= Connection_provider.getconnection();
		  MasterDAO masterdao= new JDBCMasterDAO(connection);
		  EmrDAO  emrDAO = new JDBCEmrDAO(connection);
		  
		  ArrayList<Priscription>templateNameList = emrDAO.getTemplateNameList(loginInfo);
		  if(templateNameList.size()==0){
		   templateNameList = new ArrayList<Priscription>();
		  }
		  masterForm.setTemplatelist(templateNameList);
		  ArrayList<Master> masterlist11 = masterdao.getallOutsource(null, pagination);
		  
		  masterForm.setOutsourcelist(masterlist11);
		  mastername=request.getParameter("selectedid");
		  pagination.setPage_records(masterlist11.size());
		  masterForm.setPagerecords(String.valueOf(pagination.getPage_records()));

		  if(mastername!=null){
		   
		    session.setAttribute("mastername", mastername);
		   
		  } else {
		   
		   mastername=(String)session.getAttribute("mastername");
		  }
		  masterForm.setMastername(mastername);
		  
		  
		 } catch (Exception e) {
		  // TODO: handle exception
		  e.printStackTrace();
		 }
		 return "showAllMedicineTemplate";
		}


		public String deleteMedicineTemplate()throws Exception {
		 Connection connection= null;
		 int x=0;
		 try {
		  connection=Connection_provider.getconnection();
		  MasterDAO masterDAO= new JDBCMasterDAO(connection);
		  String id= request.getParameter("id");
		  x= masterDAO.deleteMedicineTemplateDB(id);
		 } catch (Exception e) {
		  e.printStackTrace();
		 }
		 return "deleteMedicineTemplate";
		}
		 
	public String showallFeedackTemplate(){
		Connection connection= null;
		try{
			String treatmenttype="";
			connection= Connection_provider.getconnection();
			treatmenttype= masterForm.getTreatmenttype();
			if(masterForm.getTreatmenttype()==null||masterForm.getTreatmenttype().equals("")||masterForm.getTreatmenttype().equals("hd")){
				treatmenttype="opd";
			}
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			ArrayList<Client> feedbacklist=clientDAO.getallFeedbacks(treatmenttype);
			masterForm.setFeedbacklist(feedbacklist);
			
		}catch (Exception e) {
		e.printStackTrace();
		}
		
		return "showallFeedackTemplate";
	}	
	
	public String addfeedback(){
		Connection connection= null;
		try {
			connection = Connection_provider.getconnection();
			
		} catch (Exception e) {
	e.printStackTrace();
		}
		
		return "addfeedback";
	}
	public String saveFeedbackTemplate(){
		Connection connection= null;
		try{
			connection= Connection_provider.getconnection();
			masterForm.getName();
			String isopd="",isipd="";
			isopd= request.getParameter("isopd");
			isipd= request.getParameter("isipd");
			if(isipd==null){
				isipd="";
			}
			if(isopd==null){
				isopd="";
			}
			if(isipd.equals("Car")){
				isipd="1";
			}else{
				isipd="0";
			}
			if(isopd.equals("Bike")){
				isopd="1";
			}else{
				isopd="0";
			}
			MasterDAO masterDAO= new JDBCMasterDAO(connection);
			
			int x= masterDAO.saveFeedBack(masterForm.getName(), isopd, isipd);
		}catch(Exception e ){
			e.printStackTrace();
		}
		
		return "saveFeedbackTemplate";
	}
	public String editFeedBack(){
		Connection connection= null;
		try {
		connection = Connection_provider.getconnection();
			String id= request.getParameter("id");
			Master master = new Master();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			master = masterDAO.getFeedBackInfo(id);
			session.setAttribute("matseredi", master);
			masterForm.setId(master.getId());
			masterForm.setName(master.getName());
			masterForm.setIsipd(master.getIsipd());
			masterForm.setIsopd(master.getIsopd());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "editFeedBack";
	}
	
	public String updateFeedBack(){
		Connection connection= null;
		try {
			connection= Connection_provider.getconnection();
			String id=String.valueOf(masterForm.getId());
			String name= masterForm.getName();
			String isopd=request.getParameter("isopd");
			String isipd= request.getParameter("isipd");
			if(isipd==null){
				isipd="";
			}
			if(isopd==null){
				isopd="";
			}
			if(isipd.equals("Car")){
				isipd="1";
			}else{
				isipd="0";
			}
			if(isopd.equals("Bike")){
				isopd="1";
			}else{
				isopd="0";
			}
			
			MasterDAO masterDAO= new JDBCMasterDAO(connection);
			
			int x= masterDAO.updateFeedBack(name, isopd, isipd, id); 
		} catch (Exception e) {
			e.printStackTrace();
			}
		
		return "updateFeedBack";
	}
	public String deleteFeedBack(){
		Connection connection= null;
		try {
			connection= Connection_provider.getconnection();
			String id= request.getParameter("id");
			MasterDAO masterDAO= new JDBCMasterDAO(connection);
			
			int x= masterDAO.deleteFeedBack(id);
			
		} catch (Exception e) {
		e.printStackTrace();
		}
		
		return "deleteFeedBack";
	}
	
	public MasterForm getModel() {

		return masterForm;
	}
	
	//  20 July 2018
	public String showallsharablecharge() throws Exception{
		Connection connection= null;
		 try {
			  connection= Connection_provider.getconnection();
			  MasterDAO masterdao= new JDBCMasterDAO(connection);
			  UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			  ArrayList<Master> sharablechargelist = masterdao.getAllSharedChargeList();
			  ArrayList<UserProfile> visitingConsultDoctors =userProfileDAO.getVisitingPractitinerListFromUser();
			  masterForm.setSharablechargelist(sharablechargelist);
			  masterForm.setVisitingConsultDoctors(visitingConsultDoctors);
			  mastername=request.getParameter("selectedid");
			  if(mastername!=null){
			   
			    session.setAttribute("mastername", mastername);
			   
			  } else {
			   
			   mastername=(String)session.getAttribute("mastername");
			  }
			  masterForm.setMastername(mastername);
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		return "showallsharablecharge";
	}
	
	public String addnewtempusercharge() throws Exception{
		  Connection connection=null;
		  try {
		   connection = Connection_provider.getconnection();
		   MasterDAO masterDAO = new JDBCMasterDAO(connection);
		   String sharechargeid=request.getParameter("sharechargeid");
		   String shareuserid=request.getParameter("shareuserid");
		   String count=request.getParameter("count");
		   
		   int index=Integer.parseInt(count);
		   index--;
		   
		   StringBuffer buffer=new StringBuffer();
		   int flag = masterDAO.checkIssharedchargewithuser(sharechargeid,shareuserid);
		   if(flag>0){
			  /* buffer.append("<tr>");
			   buffer.append("<td>"+count+" <input type='hidden' class='dclass' value='"+shareuserid+""+sharechargeid+"' /></td>");
			   buffer.append("<td>"+product.getProduct_name()+" <input type='hidden' value='"+pid+"' name='product_id"+shareuserid+""+sharechargeid+"' /> </td>");
			   buffer.append("<td>"+avail_qty+"<input type='hidden' value='"+avail_qty+"' name='avail_qty"+pid+"' /></td>");
			   buffer.append("<td>"+qty+" <input type='hidden' value='"+qty+"' name='qty"+pid+"' /></td>");
			   buffer.append("<td>"+exp_date+" <input type='hidden' value='"+qty+"' name='expectedDate"+pid+"' /></td>");
			   buffer.append("<td><textarea class='form-control' name='comment"+pid+"' rows='2' placeholder='Enter Comments' id='comment'></textarea></td>");
			   buffer.append("<td><a href='#' onclick=deleteIndentRow(this) ><i class='fa fa-times fa-2x text-danger' ></i></a></td>");
			   buffer.append("</tr>");*/
		   }else{
			  //String name = masterDAO.get
			   buffer.append("<tr>");
			   buffer.append(""+count+" <input type='hidden' class='dclass' value='"+shareuserid+"_"+sharechargeid+"' />");
			   buffer.append("");
			   buffer.append("");
			   buffer.append("");
			   buffer.append("</tr>");
		   }
	   	  
		   
		   response.setContentType("text/html");
		   response.setHeader("Cache-Control", "no-cache");
		   response.getWriter().write(""+buffer.toString()+""); 
		  
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		 finally {
		 	connection.close();
		 }
		 return null;
	}
	
	
	//lokesh 1-8-18
		public String savevaccinationmaster(){
			String vacinname,vacine_dependson,vacine_iscompulsary,vacine_excludes,vacine_parent,vacine_info;
			String sun,mon,tue,thu,wed,fri,sat,sched,days="";
			String duration = request.getParameter("duration");
			sun=request.getParameter("sun");
			if(sun==null){
				sun="";
			}
			mon=request.getParameter("mon");
			if(mon==null){
				mon="";
			}
			tue=request.getParameter("tue");
			if(tue==null){
				tue="";
			}
			thu=request.getParameter("thu");
			if(thu==null){
				thu="";
			}
			wed=request.getParameter("wed");
			if(wed==null){
				wed="";
			}
			fri=request.getParameter("fri");
			if(fri==null){
				fri="";
			}
			sat=request.getParameter("sat");
			if(sat==null){
				sat="";
			}
			
			String dependsonscheedule=request.getParameter("depndscheduledon");
			masterForm.setDependsonscheedule(dependsonscheedule);
			sched=request.getParameter("scheduledon");
			days=sun+""+mon+""+tue+""+thu+""+fri+""+sat;
			/*request.getParameter("");*/
			vacinname= masterForm.getVacinname();
			vacine_dependson= masterForm.getVacine_dependson();
			vacine_iscompulsary= masterForm.getVacine_iscompulsary();
			if(vacine_iscompulsary.equals("true")){
				vacine_iscompulsary="1";
			}else{
				vacine_iscompulsary="0";
			}
			vacine_parent= masterForm.getVacine_parent();
			vacine_excludes=days;
			vacine_info= masterForm.getVacine_info();
			int type=masterForm.getType();
			Connection connection= null;
			int i=0;
			try {
				connection= Connection_provider.getconnection();
				MasterDAO masterDAO= new JDBCMasterDAO(connection);
				 i=masterDAO.savevaccinationmaster(vacinname, vacine_dependson, vacine_iscompulsary, vacine_excludes, vacine_parent, vacine_info,sched,duration,type,dependsonscheedule,""+masterForm.getGendervaccine());
				
				 
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(i!=0){
			return "savevaccinationmaster";
			}else{
				return "notsavevaccinationmaster";
			}
		}
		public String allvaccinationlist(){
			Connection connection= null;
			try {
				connection= Connection_provider.getconnection();
				ArrayList<Master> list=new ArrayList<Master>();
				MasterDAO masterDAO= new JDBCMasterDAO(connection);
				list= masterDAO.getallvaccinations();
				masterForm.setVacinationlist(list);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "allvaccinationlist";
		}
		
		public String updatevacinationmster(){
			try {
				String vacinname,vacine_dependson,vacine_iscompulsary,vacine_excludes,vacine_parent,vacine_info;
				String sun,mon,tue,thu,wed,fri,sat,sched,days="";
				sun=request.getParameter("sun");
				if(sun==null){
					sun="";
				}
				mon=request.getParameter("mon");
				if(mon==null){
					mon="";
				}
				tue=request.getParameter("tue");
				if(tue==null){
					tue="";
				}
				thu=request.getParameter("thu");
				if(thu==null){
					thu="";
				}
				wed=request.getParameter("wed");
				if(wed==null){
					wed="";
				}
				fri=request.getParameter("fri");
				if(fri==null){
					fri="";
				}
				sat=request.getParameter("sat");
				if(sat==null){
					sat="";
				}
				sched=request.getParameter("scheduledon");
				days=sun+""+mon+""+tue+""+thu+""+fri+""+sat;
				/*request.getParameter("");*/
				vacinname= masterForm.getVacinname();
				vacine_dependson= masterForm.getVacine_dependson();
				vacine_iscompulsary= masterForm.getVacine_iscompulsary();
				if(vacine_iscompulsary.equals("true")){
					vacine_iscompulsary="1";
				}else{
					vacine_iscompulsary="0";
				}
				
				String dependsonscheedule=request.getParameter("depndscheduledon");
				masterForm.setDependsonscheedule(dependsonscheedule);
				vacine_parent= masterForm.getVacine_parent_new();
				vacine_excludes=days;
				vacine_info= masterForm.getVacine_info();
				Connection connection= null;
				int type= masterForm.getType();
				int i=0;
				String  id=String.valueOf(masterForm.getId());
				String duration= request.getParameter("duration");
				 connection= Connection_provider.getconnection();
				 MasterDAO masterDAO= new JDBCMasterDAO(connection);
				 
				 masterDAO.updatevaccinationmaster(vacinname, vacine_dependson, vacine_iscompulsary, vacine_excludes, vacine_parent, vacine_info, id, sched,duration,type,dependsonscheedule,""+masterForm.getGendervaccine());
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "updatevacinationmster";
		}
		
		public String addvacinationmstr(){
			try {
				Connection connection= Connection_provider.getconnection();
				MasterDAO masterDAO= new JDBCMasterDAO(connection);
				ArrayList<Master> parentlist=masterDAO.getallparentvaccinations();
				ArrayList<Master> vacinationlist=  masterDAO.getallvaccinations();
				masterForm.setParentvacinationlist(parentlist);
				masterForm.setVacinationlist(vacinationlist);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		
			return "addvacinationmstr";
		}
		
		public String deletevacinationmstr(){
			String id= request.getParameter("id");
			try {
				Connection connection= Connection_provider.getconnection();
				MasterDAO masterDAO= new JDBCMasterDAO(connection);
				int del=masterDAO.deletevaccinationmaster(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "deletevacinationmstr";
		}
		
		public String editvacinationmstr(){
			try {
				String id=request.getParameter("id");
				Connection connection= Connection_provider.getconnection();
				MasterDAO masterDAO= new JDBCMasterDAO(connection);
				Master master= masterDAO.getvaccinationMasterInfo(id);
				masterForm.setVacinname(master.getVacinname());
				masterForm.setVacine_parent_new(master.getParentid());
				masterForm.setVacine_dependson(master.getVacine_dependson());
				masterForm.setVacine_info(master.getVacine_info());
				masterForm.setScheduleon(master.getVacine_scheduledon());
				masterForm.setVacine_iscompulsary(master.getVacine_iscompulsary());
				masterForm.setType(master.getType());
				masterForm.setGendervaccine(master.getGendervaccine());
				if(master.getVacine_excludes()==null){
					master.setVacine_excludes("");
				}
				if(master.getVacine_excludes().contains("1")){
					masterForm.setSun(true);
				}
				if(master.getVacine_excludes().contains("2")){
					masterForm.setMon(true);
				}
				if(master.getVacine_excludes().contains("3")){
					masterForm.setTue(true);
				}
				if(master.getVacine_excludes().contains("4")){
					masterForm.setWed(true);
				}
				if(master.getVacine_excludes().contains("5")){
					masterForm.setThu(true);
				}
				if(master.getVacine_excludes().contains("6")){
					masterForm.setFri(true);
				}
				if(master.getVacine_excludes().contains("7")){
					masterForm.setSat(true);
				}
				ArrayList<Master> parentlist=masterDAO.getallparentvaccinations();
				ArrayList<Master> vacinationlist=  masterDAO.getallvaccinations();
				masterForm.setParentvacinationlist(parentlist);
				masterForm.setVacinationlist(vacinationlist);	
				masterForm.setId(master.getId());
				masterForm.setDuration(master.getDuration());
				masterForm.setDependsonscheedule(master.getDependsonscheedule());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return "editvacinationmstr";
		}
//lokesh clinical notes
		public String addclinicalnotesmstr(){
			try {
				Connection connection= Connection_provider.getconnection();
				MasterDAO masterDAO= new JDBCMasterDAO(connection);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return "addclinicalnotesmstr";
		}
		public String saveclinicalnotesmstr(){
			try {
				Connection connection= Connection_provider.getconnection();
				MasterDAO masterDAO= new JDBCMasterDAO(connection);
				String name=masterForm.getName();
				masterDAO.saveclinicalnotesmstr(name);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return "saveclinicalnotesmstr";
		}
		public String deleteclinicalnotesmstr(){
			try {
				Connection connection= Connection_provider.getconnection();
				MasterDAO masterDAO= new JDBCMasterDAO(connection);
				String id = request.getParameter("id");
				masterDAO.deleteclinicalmster(id);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "deleteclinicalnotesmstr";
		}
		public String listclinicalnotesmster(){
			try {
				Connection connection= Connection_provider.getconnection();
				MasterDAO masterDAO= new JDBCMasterDAO(connection);
				ArrayList<Master> list= new ArrayList<Master>();
				list=masterDAO.getclinicalnoteslist();
				masterForm.setClinicalproblemlist(list);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "listclinicalnotesmster";
		}
		
		public String editclinicalnotes(){
			Connection connection= null;
			try {
				connection= Connection_provider.getconnection();
				MasterDAO masterDAO= new JDBCMasterDAO(connection);
				String id= request.getParameter("id");
				Master master=masterDAO.getclinicalnotesInfo(id);
				masterForm.setId(master.getId());
				masterForm.setName(master.getName());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "editclinicalnotes";
		}
		
		public String updateclinicalnotes(){
			Connection connection= null;
			try {
				connection= Connection_provider.getconnection();
				MasterDAO masterDAO= new JDBCMasterDAO(connection);
				String id= request.getParameter("id");
				String name= request.getParameter("name");
				Master master= new Master();
				 master.setId(Integer.parseInt(id));
				 master.setName(name);
				int x= masterDAO.updateclinicalNote(master); 
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "updateclinicalnotes";
		}
//clinical problem master hving parent id of clinicalnts
		public String addclinicalproblemmster(){
			try {
				Connection connection= Connection_provider.getconnection();
				MasterDAO masterDAO= new JDBCMasterDAO(connection);
				ArrayList<Master> list= new ArrayList<Master>();
				list=masterDAO.getclinicalnoteslist();
				masterForm.setClinicalnoteslist(list);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "addclinicalproblemmster";
		}
		 public String deleteclinicalproblemmster(){
			 try {
					Connection connection= Connection_provider.getconnection();
					MasterDAO masterDAO= new JDBCMasterDAO(connection);
					String id = request.getParameter("id");
					masterDAO.deleteclinicalproblem(id);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			 return "deleteclinicalproblemmster";
		 }
		 public String listclinicalproblemmster(){
			 try {
					Connection connection= Connection_provider.getconnection();
					MasterDAO masterDAO= new JDBCMasterDAO(connection);
					ArrayList<Master> list= new ArrayList<Master>();
					list=masterDAO.getclinicalproblemlist();
					masterForm.setClinicalproblemlist(list);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			 return "listclinicalproblemmster";
		 }
		 
		 public String saveclinicalproblemmstr(){
				try {
					Connection connection= Connection_provider.getconnection();
					MasterDAO masterDAO= new JDBCMasterDAO(connection);
					String name=request.getParameter("name");
					String parentid=masterForm.getParentid();
					masterDAO.saveclinicalproblemmster(name, parentid);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return "saveclinicalproblemmstr";
			}
		 public String editclinicalproblem(){
				Connection connection= null;
				try {
					connection= Connection_provider.getconnection();
					MasterDAO masterDAO= new JDBCMasterDAO(connection);
					String id= request.getParameter("id");
					Master master=masterDAO.getclinicalproblemInfo(id);
					masterForm.setId(master.getId());
					masterForm.setName(master.getName());
					masterForm.setParentid(master.getParentid());
					ArrayList<Master> list= new ArrayList<Master>();
					list=masterDAO.getclinicalnoteslist();
					masterForm.setClinicalnoteslist(list);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return "editclinicalproblem";
			}
		 public String updateclinicalProblem(){
				Connection connection= null;
				try {
					connection= Connection_provider.getconnection();
					MasterDAO masterDAO= new JDBCMasterDAO(connection);
					String id= request.getParameter("id");
					String name= request.getParameter("name");
					String parentid= request.getParameter("parentid");
					Master master= new Master();
					 master.setId(Integer.parseInt(id));
					 master.setName(name);
					 master.setParentid(parentid);
					int x= masterDAO.updateclinicalProblem(master); 
				} catch (Exception e) {
					e.printStackTrace();
				}
				return "updateclinicalProblem";
			}
//clinical notes intervation
		 
		 public String addclinicalintervation(){
			 try {
					Connection connection= Connection_provider.getconnection();
					MasterDAO masterDAO= new JDBCMasterDAO(connection);
					ArrayList<Master> list= new ArrayList<Master>();
					list=masterDAO.getclinicalproblemlist();
					masterForm.setClinicalproblemlist(list);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			 return "addclinicalintervation";
		 }
		 public String deleteclinicalintervation(){
			 try {
					Connection connection= Connection_provider.getconnection();
					MasterDAO masterDAO= new JDBCMasterDAO(connection);
					String id = request.getParameter("id");
					masterDAO.deleteclinicalintervation(id);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			 return "deleteclinicalintervation";
		 }
		 public String listclinicalintervation(){
			 try {
					Connection connection= Connection_provider.getconnection();
					MasterDAO masterDAO= new JDBCMasterDAO(connection);
					ArrayList<Master> list= new ArrayList<Master>();
					list=masterDAO.getclinicalintervationlist();
					masterForm.setClinicalintervationlist(list);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			 return "listclinicalintervation";
		 }
		 
		 public String saveclinicalintervationmstr(){
				try {
					Connection connection= Connection_provider.getconnection();
					MasterDAO masterDAO= new JDBCMasterDAO(connection);
					String name=request.getParameter("name");
					String parentid=masterForm.getParentid();
					masterDAO.saveclinicalintervation(name, parentid);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return "saveclinicalintervationmstr";
			}
		 
		 public String editclinicalintervation(){
				Connection connection= null;
				try {
					connection= Connection_provider.getconnection();
					MasterDAO masterDAO= new JDBCMasterDAO(connection);
					String id= request.getParameter("id");
					Master master=masterDAO.getclinicalintervationInfo(id);
					masterForm.setId(master.getId());
					masterForm.setName(master.getName());
					masterForm.setParentid(master.getParentid());
					ArrayList<Master> list= new ArrayList<Master>();
					list=masterDAO.getclinicalproblemlist();
					masterForm.setClinicalproblemlist(list);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return "editclinicalintervation";
			}
		 
		 public String updateclinicalIntervation(){
				Connection connection= null;
				try {
					connection= Connection_provider.getconnection();
					MasterDAO masterDAO= new JDBCMasterDAO(connection);
					String id= request.getParameter("id");
					String name= request.getParameter("name");
					String parentid= request.getParameter("parentid");
					Master master= new Master();
					 master.setId(Integer.parseInt(id));
					 master.setName(name);
					 master.setParentid(parentid);
					int x= masterDAO.updateclinicalIntervation(master); 
				} catch (Exception e) {
					e.printStackTrace();
				}
				return "updateclinicalIntervation";
			}
		 
		//Remark master 
		 public String addremark(){
			 Connection connection= null;
			 try {
				connection= Connection_provider.getconnection();
				/*masterForm.setRemarkmarathi("%E0%A4%97%E0%A4%BE%E0%A4%82%E0%A4%A1%E0%A5%82%20");
				session.setAttribute("marathi", masterForm.getRemarkmarathi());*/
			} catch (Exception e) {
				e.printStackTrace();
			}
			 return "addremark";
		 }
		 
		 
		 public String saveremark(){
			 Connection connection= null;
			 try {
				connection= Connection_provider.getconnection();
				String hind=masterForm.getRemarkhindi();
				String remark=masterForm.getRemark();
				String marathi= masterForm.getRemarkmarathi();
				Master master = new Master();
				master.setRemark(remark);
				master.setRemarkhindi(hind);
				master.setRemarkmarathi(marathi);
				MasterDAO masterDAO= new JDBCMasterDAO(connection);
				masterDAO.saveremarkMaster(master);
			} catch (Exception e) {
				e.printStackTrace();
			}
			 return "saveremark";
		 }
		 
		 public String listremarks(){
			 Connection connection= null;
			 try {
				connection= Connection_provider.getconnection();
				String hind=masterForm.getRemarkhindi();
				String remark=masterForm.getRemark();
				String marathi= masterForm.getRemarkmarathi();
				ArrayList<Master> list= new ArrayList<Master>();
				MasterDAO masterDAO = new JDBCMasterDAO(connection);
				list= masterDAO.getallRemarks();
				masterForm.setList(list);
				mastername=request.getParameter("selectedid");
				if(mastername!=null){
					 session.setAttribute("mastername", mastername);
				} else {
					mastername=(String)session.getAttribute("mastername");
				}
				masterForm.setMastername(mastername);
			} catch (Exception e) {
				e.printStackTrace();
			}
			 return "listremarks";
		 }
		 
		 
		 public String addremarkmasterbyjson(){
			 
		 Connection connection=null;
			 try{
					connection =Connection_provider.getconnection();
					StringBuilder buffer1 = new StringBuilder();
					BufferedReader reader = request.getReader();
					String line;
					while ((line = reader.readLine()) != null) {
						buffer1.append(line);
					}
					String data = buffer1.toString();
					JSONObject jsonObject = new JSONObject(data);
					
					
					EmrDAO emrDAO = new JDBCEmrDAO(connection);
					String eng= jsonObject.getString("eng");
					String hindi = jsonObject.getString("hindi");
					String marathi =  jsonObject.getString("marathi");
					Master master= new Master();
					master.setRemark(eng);
					master.setRemarkhindi(hindi);
					master.setRemarkmarathi(marathi);
					MasterDAO masterDAO= new JDBCMasterDAO(connection);
					masterDAO.saveremarkMaster(master);
					StringBuffer buffer= new StringBuffer();
					ArrayList<Master>nimairemarklist = masterDAO.getnimairemarlist();
					buffer.append(" <div class='form-group' style='width:80%;display:inline-flex;''>");
					buffer.append("<select name='priscindivisualremark' class='form-control chosen-select' id='priscindivisualremark'>");
					buffer.append("<option value='0'>All Remarks</option>");
					for(Master m:nimairemarklist){
						buffer.append("<option value='"+m.getId()+"'>"+m.getName()+"</option>");
					}
					buffer.append("</select>");
					buffer.append("&nbsp;<div><i class='fa fa-plus' style='width:10px;' onclick='addremarkinotherlang()'></i></div>");
					buffer.append("</div>");
					
					String responsej=	buffer.toString();
					JSONObject jsonobj = new JSONObject();
					jsonobj.put("responsej", responsej);
					String response1 = jsonobj.toString();
					response.setContentType("application/json");
					response.setHeader("Cache-Control", "no-cache");
					response.getWriter().write(response1);
			 
			 }catch(Exception e){
				 e.printStackTrace();
			 }
			 return null;
		 }
		 public String refreshedremarks(){
			 Connection connection= null;
			 try{
				 connection = Connection_provider.getconnection();
			 MasterDAO masterDAO= new JDBCMasterDAO(connection);
				
				StringBuffer buffer= new StringBuffer();
				ArrayList<Master>nimairemarklist = masterDAO.getnimairemarlist();
				buffer.append(" <div class='form-group' style='width:80%;display:inline-flex;''>");
				buffer.append("<select name='priscindivisualremark' class='form-control chosen-select' id='priscindivisualremark'>");
				buffer.append("<option value='0'>All Remarks</option>");
				for(Master m:nimairemarklist){
					buffer.append("<option value='"+m.getId()+"'>"+m.getName()+"</option>");
				}
				buffer.append("</select>");
				buffer.append("&nbsp;<div><i class='fa fa-plus' style='width:10px;' onclick=openPopup('addremarkMaster')></i></div>&nbsp;<div><i class='fa fa-refresh' style='width:10px;' onclick='refreshremarks()'></i></div>");
				buffer.append("</div>");
				
				String responsej=	buffer.toString();
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("responsej", responsej);
				String response1 = jsonobj.toString();
				response.setContentType("application/json");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(response1);
			 }catch(Exception e){
				 e.printStackTrace();
			 }
			 return null;
		 }
		 
		 
		 public String generate(){
			 Connection connection= null;
			String fromdate= request.getParameter("fromdate");
			String todate=request.getParameter("todate");
			if(fromdate==null||todate==null){
				fromdate="";
				todate="";
			}
			 try {	
				 connection= Connection_provider.getconnection();
				 
			StringBuffer buffer= new StringBuffer();		
			 buffer.append("  select initial_paymode,final_paymode,paymode,billno from apm_medicine_bill   ");
				buffer.append("  inner join apm_medicine_payment on apm_medicine_payment.billno=apm_medicine_bill.id   ");
				buffer.append("    ");
				buffer.append("    where final_paymode !=paymode and paymode!='Credit'  and apm_medicine_payment.date between '"+fromdate+"'  and '"+todate+"' ");
				PreparedStatement ps= connection.prepareStatement(buffer.toString());
				ResultSet rs= ps.executeQuery();
				if(!fromdate.equals("")){
				while(rs.next()){
					String sql="update apm_medicine_bill set initial_paymode ='"+rs.getString(3)+"' where id='"+rs.getInt(4)+"'";
					PreparedStatement ps1=connection.prepareStatement(sql);
					int x= ps1.executeUpdate();
//					System.out.println(""+rs.getInt(4));
				}
				}	
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			 return "generatetra";
		 }
		 
		 
public String searchmed(){
	try {
		
		 response.setContentType("application/json");
		 String term = request.getParameter("term");
//         System.out.println("Data from ajax call " + term);

        Connection connection= Connection_provider.getconnection();
         
        
         InvestigationDAO investigationDAO= new JDBCInvestigationDAO(connection);
         ArrayList<Investigation> lusr= investigationDAO.getallinvsttypewisecountrpt( "02-11-2018","02-11-2018");
        
         JSONArray jsArray2 = new JSONArray(lusr);
         String searchList = jsArray2.toString();
         response.setContentType("application/json");
         response.getWriter().write(searchList);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}	
public String inventoryopeningclosing() throws Exception{
	 Connection connection= null;
		String fromdate= request.getParameter("fromdate");
		String todate=request.getParameter("todate");
		if(fromdate==null||todate==null){
			fromdate="";
			todate="";
		}
		 try {	
			 connection= Connection_provider.getconnection();
			 InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			/* System.out.println("ok");*/
				
				ArrayList<String> arrayList = new ArrayList<String>();
				arrayList.add("33");
			 	arrayList.add("36");
				
				/*long differ = DateTimeUtils.getDifferenceOfTwoDateDBFormat("2018-06-01", "2019-01-31");*/
				/*long differ = DateTimeUtils.getDifferenceOfTwoDateDBFormat("2018-10-31", "2019-02-01");*/
			 	long differ = DateTimeUtils.getDifferenceOfTwoDateDBFormat(fromdate, todate);
				int k = (int)differ;
				for (int i=k; i > 0; i--) {
				   SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
				  /* Date d=sdf1.parse("2018-10-31");*/
				   Date d=sdf1.parse(fromdate);
				   Calendar cal = Calendar.getInstance();
				   cal.setTime(d);
				   cal.add(Calendar.DATE, i);
				   String dt=sdf1.format(cal.getTime());
				   
				   SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
				   Date d2=sdf1.parse(dt);
				   Calendar cal1 = Calendar.getInstance();
				   cal1.setTime(d2);
				   cal1.add(Calendar.DATE, 1);
				   String nextdate=sdf2.format(cal1.getTime());
				   
				   
				   /*System.out.println("nextdate:"+nextdate+", currentdate:"+dt);*/
					for (String string : arrayList) {
						String location_filter = string;
						String fromDate = dt;
						String toDate = dt;
						StringBuffer buffer = new StringBuffer();
						buffer.append("select inventory_catalogue.id from inventory_catalogue ");
						buffer.append("inner join inventory_product on inventory_product.catalogueid = inventory_catalogue.id  ");
						buffer.append("where procurement=0 and product_name is not null and inventory_product.location='"+location_filter+"' ");
						buffer.append("group by inventory_product.catalogueid order by inventory_catalogue.id ");
						PreparedStatement ps=connection.prepareStatement(buffer.toString());
						ResultSet rs =ps.executeQuery();
						 while(rs.next()){
							Product product = new Product();
							
							String catalogueid = rs.getString(1);
							double opeingstockvalue =0;
							double openingstock=0;
							boolean isapplied = inventoryProductDAO.checkInventoryCatalogueStatus(fromDate,catalogueid,location_filter);
							/*boolean isapplied = true;*/
							if(!isapplied){
							//Qty in-  Purchase qty +  return from patient + retruntransfer  = in fromdate and todate
							
							//Purchase qty =  in fromdate and todate
							Product purproduct = inventoryProductDAO.getPuchaseProductDataBetween(catalogueid,fromDate,toDate,location_filter);  
							
							//return from patient =  in fromdate and todate
							Product returnpatientqty = inventoryProductDAO.getReturnPatientProductDataBetween(catalogueid,fromDate,toDate,location_filter);
							
							// retruntransfer = in fromdate and todate 
							double retruntransferqtyin =0;
							double retruntransferqtyinvalue=0;
							double directtransferqtyin=0;
							double requesttransferqtyin =0;
							double directtransferqtyinvalue=0;
							double requesttransferqtyinvalue=0;
							if(!location_filter.equals("0")){
								//Return transfer between date 
								Product returntransferproductin = inventoryProductDAO.getReturnTransferBetweenDate(catalogueid,fromDate,location_filter,toDate);
								retruntransferqtyin = returntransferproductin.getTotalqty();
								retruntransferqtyinvalue = returntransferproductin.getTotal_amount();
								
								//Direct transfer between 
								Product directtransferproductin = inventoryProductDAO.getDirectTransferBetweenDateIn(catalogueid,fromDate,location_filter,toDate);
								
								//Request Transfer between
								Product requesttransferproductin = inventoryProductDAO.getRequestTransferBetweenDateIn(catalogueid,fromDate,location_filter,toDate);
								
								directtransferqtyin = directtransferproductin.getTotalqty();
								requesttransferqtyin = requesttransferproductin.getTotalqty();
								directtransferqtyinvalue = directtransferproductin.getTotal_amount();
								requesttransferqtyinvalue = requesttransferproductin.getTotal_amount();
							}
							
							Product adjustmentbetweenin = inventoryProductDAO.getAdjustmentDataBetweenIn(catalogueid,fromDate,location_filter,toDate);
							
							double qtyin = purproduct.getTotalqty() + returnpatientqty.getTotalqty() 
										+retruntransferqtyin + directtransferqtyin +requesttransferqtyin + adjustmentbetweenin.getTotalqty() ;
							
							double qtyinvalue = purproduct.getTotal_amount() + returnpatientqty.getTotal_amount() 
										+retruntransferqtyinvalue + directtransferqtyinvalue +requesttransferqtyinvalue + adjustmentbetweenin.getTotal_amount() ;
							
							//Qty out - Patient sale + Return to Supplier + Consume + direct transfer + request return  = in Fromdate and Todate
							
							// retruntransfer = in fromdate and todate 
							double directtransferqtyout =0;
							double directtransferqtyoutvalue=0;
							double requesttransferqtyout =0;
							double requesttransferqtyoutvalue=0;
							
							double returntransferqtyout =0;
							double returntransferqtyoutvalue=0;
							
							double directsaleprice=0;
							double returnsaleprice=0;
							double requestsaleprice=0;
							
							if(!location_filter.equals("0")){
								//Direct transfer between 
								Product directtransferproductout = inventoryProductDAO.getDirectTransferBetweenDate(catalogueid,fromDate,location_filter,toDate);
								
								//Request Transfer between
								Product requesttransferproductout = inventoryProductDAO.getRequestTransferBetweenDate(catalogueid,fromDate,location_filter,toDate);
								
								directtransferqtyout = directtransferproductout.getTotalqty();
								requesttransferqtyout = requesttransferproductout.getTotalqty();
								directtransferqtyoutvalue = directtransferproductout.getTotal_amount();
								requesttransferqtyoutvalue = requesttransferproductout.getTotal_amount();
								
								//Return to store between
								Product returntransferproductin = inventoryProductDAO.getReturnTransferBetweenDateOut(catalogueid,fromDate,location_filter,toDate);
								returntransferqtyout = returntransferproductin.getTotalqty();
								returntransferqtyoutvalue = returntransferproductin.getTotal_amount();
								
								directsaleprice = directtransferproductout.getSalepricetotal();
								returnsaleprice = returntransferproductin.getSalepricetotal();
								requestsaleprice = requesttransferproductout.getSalepricetotal();
							}
							
							//Patient Sale between Fromdate and Todate
							Product salepatient = inventoryProductDAO.getPatientSaleBetweenDate(catalogueid,fromDate,toDate,location_filter);
							
							//Return to supplier in between fromdate and todate
							Product returntosuppplier = inventoryProductDAO.getReturnToSupplierBetwwenDate(catalogueid,fromDate,toDate,location_filter); 
							
							//Consume to user or patient between Fromdate and Todate
							Product consume = inventoryProductDAO.getConsumeBetweenDate(catalogueid,fromDate,toDate,location_filter);
							
							Product adjustmentbetweenOut = inventoryProductDAO.getAdjustmentDatabetweenOut(catalogueid,fromDate,location_filter,toDate);
							
							double qtyout = salepatient.getTotalqty()+returntosuppplier.getTotalqty()
										+consume.getTotalqty() + directtransferqtyout +requesttransferqtyout 
										+returntransferqtyout + adjustmentbetweenOut.getTotalqty();
							
							//Stock value =  Patient sale unit price + Return to Supplier purchase price + Consume purchase price
							double stockvalue =  salepatient.getTotal_amount()+ returntosuppplier.getTotal_amount()
													+consume.getTotal_amount() +directtransferqtyoutvalue +requesttransferqtyoutvalue 
													+returntransferqtyoutvalue + adjustmentbetweenOut.getTotal_amount();
							
							//sale price total 
							double saleprice = salepatient.getSalepricetotal()+ returntosuppplier.getSalepricetotal()
													+consume.getSalepricetotal() +directsaleprice +returnsaleprice 
													+requestsaleprice + adjustmentbetweenOut.getSalepricetotal();
							
							
							
							/*//Closing - opening + qtyin - qtyout
							int closingstock = openingstock +qtyin - qtyout;
							
							//double closingvalue = unitprice * closingstock;
							double closingvalue = opeingstockvalue + qtyinvalue - stockvalue;
							int unknownqty = 0;
							if((openingstock +qtyin)<qtyout){
								closingstock =0;
								closingvalue =0;
								unknownqty = qtyout - (openingstock +qtyin);
							}*/
							
							//double unitprice = getUnitPriceFromCatalogueid(catalogueid);
							
							//opening = closing - qtyin + qtyout
							
							Product product2 = inventoryProductDAO.getNextOpeingData(nextdate,catalogueid,location_filter);
							
							double closingstock =product2.getTotalclosingstock();
							openingstock = closingstock -qtyin + qtyout;
							
							//double closingvalue = unitprice * closingstock;
							double closingvalue =  product2.getTotalclosingvalue();
							opeingstockvalue = closingvalue - qtyinvalue + stockvalue;
							
							if(openingstock<0){
								openingstock=0;
							}
							
							if(opeingstockvalue<0){
								opeingstockvalue=0;
							}
							
							
							double unknownqty = 0;
							if((openingstock +qtyin)<qtyout){
								closingstock =0;
								closingvalue =0;
								unknownqty = qtyout - (openingstock +qtyin);
							}
							
							
							product.setUnknownqty(unknownqty);
							product.setOpeningstock(""+openingstock);
							product.setOpeningstockvalue(Math.round(opeingstockvalue * 100.0)/100.0);
							product.setPurchaseqty(qtyin);
							product.setSale(""+qtyout);
							product.setSalevalue(Math.round(stockvalue * 100.0)/100.0);
							product.setClosingstock(""+closingstock);
							product.setSv(DateTimeUtils.changeFormat(Math.round(closingvalue * 100.0)/100.0));
							product.setQtyinvalue(DateTimeUtils.changeFormat(qtyinvalue));
							product.setSale_price(DateTimeUtils.changeFormat(Math.round(saleprice * 100.0)/100.0));
						
							boolean isapplied1 = inventoryProductDAO.checkInventoryCatalogueStatus(fromDate,catalogueid,location_filter);
							/*boolean isapplied = true;*/
							if(!isapplied1){
								String sql1="insert into inventory_catalogue_log (lastmodfied, date, location, opening_stock, opeing_value, qty_in, qty_in_value, qty_out, qty_out_value, stock_value, Unknown_qty, closing_stock, closing_value, catalogueid) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
							 	PreparedStatement ps1=connection.prepareStatement(sql1);
							 	ps1.setString(1, dt);
							 	ps1.setString(2, dt);
							 	ps1.setString(3, location_filter);
							 	
							 	ps1.setString(4, ""+openingstock);
							 	ps1.setString(5, DateTimeUtils.changeFormat(Math.round(opeingstockvalue * 100.0)/100.0));
							 	ps1.setString(6, ""+qtyin);
							 	
							 	ps1.setString(7, DateTimeUtils.changeFormat(qtyinvalue));
							 	ps1.setString(8, ""+qtyout);
							 	ps1.setString(9, DateTimeUtils.changeFormat(Math.round(stockvalue * 100.0)/100.0));
							 	
							 	ps1.setString(10, DateTimeUtils.changeFormat(Math.round(saleprice * 100.0)/100.0));
							 	ps1.setString(11, ""+unknownqty);
							 	ps1.setString(12, ""+closingstock);
							 	
							 	ps1.setString(13, DateTimeUtils.changeFormat(Math.round(closingvalue * 100.0)/100.0));
							 	ps1.setString(14, rs.getString(1));
							 	int res= ps1.executeUpdate();
							}
						}
					}
				}
				}
				
				
				
//				System.out.println("ok done");
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return "generatetra";
}


public String listtaxes(){
	try {
		Connection connection= Connection_provider.getconnection();
		MasterDAO masterDAO= new JDBCMasterDAO(connection);
		ArrayList<Master> list=new ArrayList<Master>();
		list=masterDAO.getMasterList("his_tax_master");
				
		masterForm.setList(list);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "listtaxes";
}

public String addtoTaxmaster(){
	try {
		Connection connection= Connection_provider.getconnection();
		MasterDAO masterDAO= new  JDBCMasterDAO(connection);
		masterForm.setTaxtype1(!masterDAO.getTaxTypeExist("1"));
		masterForm.setTaxtype2(!masterDAO.getTaxTypeExist("2"));
		masterForm.setTaxtype3(!masterDAO.getTaxTypeExist("3"));
	} catch (Exception e) {
		// TODO: handle exception
	}
	return "addtoTaxmaster";
}

public String saveToTaxmaster(){
	try {
		Connection connection= Connection_provider.getconnection();
		MasterDAO masterDAO= new JDBCMasterDAO(connection);
		Master master= new Master();
		master.setName(request.getParameter("name"));
		master.setPercent(request.getParameter("percent"));
		master.setType(Integer.parseInt(request.getParameter("taxtype")));
		masterDAO.addToTaxMaster(master);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "savedmfg";
}

public String edittaxmaster(){
	try {
		String id=request.getParameter("id");
		Connection connection= Connection_provider.getconnection();
		MasterDAO masterDAO= new JDBCMasterDAO(connection);
		Master master= new Master();
		master= masterDAO.getMasterInfo("his_tax_master", id);
		masterForm.setId(master.getId());
		masterForm.setName(master.getName());
		masterForm.setPercent(master.getPercent());
				
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "edittaxmaster";
}
public String updatetaxmaster(){
	try {
		Master master= new  Master();
		master.setName(request.getParameter("name"));
		master.setPercent(request.getParameter("percent"));
		master.setId(masterForm.getId());
		Connection connection= Connection_provider.getconnection();
		MasterDAO masterDAO= new JDBCMasterDAO(connection);
		int res=masterDAO.updateMaster("his_tax_master", master);
	} catch (Exception e) {
		e.printStackTrace();
	}
return "updatetax";	
}

public String deletetaxmaster(){
	try {
		String id= request.getParameter("id");
		Connection connection= Connection_provider.getconnection();
		MasterDAO masterDAO= new  JDBCMasterDAO(connection);
		int res = masterDAO.deleteMaster("his_tax_master", id);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "deletetax";
}
public String listmfg(){
	try {
		Connection connection= Connection_provider.getconnection();
		MasterDAO masterDAO= new JDBCMasterDAO(connection);
		ArrayList<Master> list=new ArrayList<Master>();
		list=masterDAO.getMfgList();
				
		masterForm.setList(list);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "listmfg";
}

public String addmfg(){
	try {
		Connection connection= Connection_provider.getconnection();
		MasterDAO masterDAO= new  JDBCMasterDAO(connection);
	} catch (Exception e) {
		// TODO: handle exception
	}
	return "addmfg";
}

public String savemfg(){
	try {
		Connection connection= Connection_provider.getconnection();
		MasterDAO masterDAO= new JDBCMasterDAO(connection);
		Master master= new Master();
		master.setName(request.getParameter("name"));
		masterDAO.addToMfgMaster(master);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "savedmfg";
}

public String editmfg(){
	try {
		String id=request.getParameter("id");
		Connection connection= Connection_provider.getconnection();
		MasterDAO masterDAO= new JDBCMasterDAO(connection);
		Master master= new Master();
		master= masterDAO.getMasterInfoMfg(id);
		masterForm.setId(master.getId());
		masterForm.setName(master.getName());
				
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "editmfg";
}
public String updatemfg(){
	try {
		Master master= new  Master();
		master.setName(request.getParameter("name"));
		master.setId(masterForm.getId());
		Connection connection= Connection_provider.getconnection();
		MasterDAO masterDAO= new JDBCMasterDAO(connection);
		int res=masterDAO.updatemfgMaster("mfg_details", master);
	} catch (Exception e) {
		e.printStackTrace();
	}
return "updatemfg";	
}

public String deletemfg(){
	try {
		String id= request.getParameter("id");
		Connection connection= Connection_provider.getconnection();
		MasterDAO masterDAO= new  JDBCMasterDAO(connection);
		int res = masterDAO.deleteMaster("mfg_details", id);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "deletemfg";
}

public String checkexistmfg(){
	try {
		String mfgname= request.getParameter("mfgname");
		Connection connection= Connection_provider.getconnection();
		MasterDAO masterDAO= new  JDBCMasterDAO(connection);
		int res = masterDAO.getmfgExist(mfgname);
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
	
			response.getWriter().write(""+res+"");
		} catch (Exception e) {
			e.printStackTrace();
		}
	return null;
}
public String genericname() throws Exception{
	Connection connection=null;
	try {
		connection=Connection_provider.getconnection();
		MasterDAO masterDAO = new JDBCMasterDAO(connection);
		String searchText = masterForm.getSearchText();
		if(searchText!=null){
			if(searchText.equals("")){
				searchText=null;
			}
		}
		int count=masterDAO.getTotalGenericNameCount(searchText);
		pagination.setPreperties(count);
		
		ArrayList<Master> genericnamelist = masterDAO.getAllGenericName(searchText,pagination);
		masterForm.setGenericnamelist(genericnamelist);
		
		mastername=request.getParameter("selectedid");
		pagination.setPage_records(genericnamelist.size());
		masterForm.setPagerecords(String.valueOf(pagination.getPage_records()));
		masterForm.setTotalRecords(count);
		if(mastername!=null){
			
			 session.setAttribute("mastername", mastername);
			
		} else {
			
			mastername=(String)session.getAttribute("mastername");
		}
		masterForm.setMastername(mastername);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	finally{
		connection.close();
	}
	return "genericnamelist";
}

public String addgenericname(){
	return "addgenericname";	
}

public String savegenericname() throws Exception{
	Connection connection=null;
	try {
		connection = Connection_provider.getconnection();
		MasterDAO masterDAO = new JDBCMasterDAO(connection);
		String name = masterForm.getName();
		int result = masterDAO.addGenericName(name);
	} catch (Exception e) {
		e.printStackTrace();
	}finally{
	connection.close();
	}
	return "savegenericname";
}

public String checkexistgenericname(){
	try {
		String genericname= request.getParameter("genericname");
		Connection connection= Connection_provider.getconnection();
		MasterDAO masterDAO= new  JDBCMasterDAO(connection);
		int res = masterDAO.checkGenericName(genericname);
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
	
			response.getWriter().write(""+res+"");
		} catch (Exception e) {
			e.printStackTrace();
		}
	return null;
}


public String listdosage(){
	try {
		Connection connection= Connection_provider.getconnection();
		MasterDAO masterDAO= new JDBCMasterDAO(connection);
		masterForm.setList(masterDAO.listDosages());
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "listdosage";
}

public String savedosage(){
	
try {
	Connection connection= Connection_provider.getconnection();
	Master master= new Master();
	master.setRemark(masterForm.getRemark());
	master.setRemarkhindi(masterForm.getRemarkhindi());
	MasterDAO masterDAO= new JDBCMasterDAO(connection);
	int x=masterDAO.addDosages(master);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "savedosage";
}

public String adddosage(){
	
	return "adddosage";
}
public String listsms(){
	try {
		Connection connection= Connection_provider.getconnection();
		MasterDAO masterDAO= new JDBCMasterDAO(connection);
		masterForm.setList(masterDAO.listsms());
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "listsms";
}
public String addsms(){
	try {
		Connection connection= Connection_provider.getconnection();
		MasterDAO masterDAO= new JDBCMasterDAO(connection);
		ArrayList<Master> smsitypelist=masterDAO.getsmsitypelist();
		ArrayList<Master> smstypelist=masterDAO.getsmstypeList("");
		masterForm.setSmsitypelist(smsitypelist);
		masterForm.setSmstypelist(smstypelist);
	}catch (Exception e) {
		e.printStackTrace();
	}
	return "addsms";
}
public String getsmstypelist(){
	Connection connection = null;
	try {
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO= new JDBCMasterDAO(connection);
			String id=request.getParameter("id");
	ArrayList<Master> smstypelist=masterDAO.getsmstypeList(id);
	StringBuffer str = new StringBuffer();
	str.append("<select class='form-control' onchange='checkalreadyexist(this.value)' id='smstypeid' name='sms_type' style='width:20%'>");
	str.append("<option value='0'>Select SMS Type</option>");
	
	for (Master master : smstypelist) {
		
		str.append("<option value='"+master.getId()+"'>"+master.getSms_type()+"</option>");
	}
	
	str.append("</select>");
	response.setContentType("text/html");
	response.setHeader("Cache-Control", "no-cache");
	response.getWriter().write(""+str+"");
}catch (Exception e) {
e.printStackTrace();
}
return null;
	
}

public String checkalreadyexistsms(){
	try {
		String id= request.getParameter("id");
		Connection connection= Connection_provider.getconnection();
		MasterDAO masterDAO= new  JDBCMasterDAO(connection);
		int res = masterDAO.getsmstempExist(id);
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
	
			response.getWriter().write(""+res+"");
		} catch (Exception e) {
			e.printStackTrace();
		}
	return null;
}
public String savesms(){
try {
	Connection connection= Connection_provider.getconnection();
	Master master= new Master();
	master.setSms_type(masterForm.getSms_type());
	master.setSms_itype(masterForm.getSms_itype());
	master.setSms(masterForm.getSmstxt());
	MasterDAO masterDAO= new JDBCMasterDAO(connection);
	int x=masterDAO.addsmstmp(master);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "savedsms";
}
public String editsms(){
	try {
		String id=request.getParameter("id");
		Connection connection= Connection_provider.getconnection();
		MasterDAO masterDAO= new JDBCMasterDAO(connection);
		Master master= new Master();
		master= masterDAO.getMasterSMSTemp(id);
		masterForm.setId(master.getId());
		masterForm.setSmstxt(master.getSms());
		masterForm.setSms_itypename(masterDAO.getsmstype(master.getSms_itype(),"sms_invoice_type","type"));
		masterForm.setSms_typename(masterDAO.getsmstype(master.getSms_type(),"sms_type","sms_type"));
		masterForm.setSms_itype(master.getSms_itype());
		masterForm.setSms_type(master.getSms_type());
				
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "editsms";
}
public String updatesms(){
	try {
		Master master= new  Master();
		master.setId(masterForm.getId());
		master.setSms_type(masterForm.getSms_type());
		master.setSms_itype(masterForm.getSms_itype());
		master.setSms(masterForm.getSmstxt());
		Connection connection= Connection_provider.getconnection();
		MasterDAO masterDAO= new JDBCMasterDAO(connection);
		int res=masterDAO.updatSMSMaster( master);
	} catch (Exception e) {
		e.printStackTrace();
	}
return "updatesms";	
}
public String deletesms(){
	try {
		String id= request.getParameter("id");
		Connection connection= Connection_provider.getconnection();
		MasterDAO masterDAO= new  JDBCMasterDAO(connection);
		int res = masterDAO.deleteMaster("sms_template_master", id);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "deletesms";
}
public String listoutsourcerate(){
	try {
		Connection connection= Connection_provider.getconnection();
		MasterDAO masterDAO= new JDBCMasterDAO(connection);
		masterForm.setList(masterDAO.listoutsourcerate());
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "listoutsourcerate";
}
public String addoutsourcerate(){
	try {
		Connection connection= Connection_provider.getconnection();
		InvestigationDAO investigationDAO=new JDBCInvestigationDAO(connection);
		ArrayList<Investigation> outsourcelist = investigationDAO.getOutSourceList();
		masterForm.setOutsourcevendorlist(outsourcelist);
		EmrDAO emrDAO=new JDBCEmrDAO(connection);
		ArrayList<Master>invsTypeList = emrDAO.getInvesigationTypeList();
		masterForm.setInvsTypeList(invsTypeList);
		MasterDAO masterDAO= new JDBCMasterDAO(connection);
		ArrayList<Master> smsitypelist=masterDAO.getsmsitypelist();
		ArrayList<Master> smstypelist=masterDAO.getsmstypeList("");
		masterForm.setSmsitypelist(smsitypelist);
		masterForm.setSmstypelist(smstypelist);
	}catch (Exception e) {
		e.printStackTrace();
	}
	return "addoutsourcerate";
}
public String editoutsourcerate(){
	Connection connection = null;

	try {

		String selectedid = request.getParameter("id");
		connection = Connection_provider.getconnection();
		InvestigationDAO investigationDAO=new JDBCInvestigationDAO(connection);
		ArrayList<Investigation> outsourcelist = investigationDAO.getOutSourceList();
		masterForm.setOutsourcevendorlist(outsourcelist);
		EmrDAO emrDAO=new JDBCEmrDAO(connection);
		ArrayList<Master>invsTypeList = emrDAO.getInvesigationTypeList();
		masterForm.setInvsTypeList(invsTypeList);
		MasterDAO masterDAO = new JDBCMasterDAO(
				connection);
		Master master = masterDAO.getoutsourcerateList(selectedid);
		masterForm.setId(Integer.parseInt(selectedid));
		masterForm.setVendor(String.valueOf(master.getOutsource_id()));
		masterForm.setInvsttype(master.getInvestigstion_id());
		masterForm.setCharge(master.getCharge());
		
		
	} catch (Exception e) {

		e.printStackTrace();
	} 

	return "editoutsourcerate";
}
public String updateoutsourcerate(){
	Connection connection = null;

	try {
		connection = Connection_provider.getconnection();
		MasterDAO masterDAO=new JDBCMasterDAO(connection);
		BedDao bedDao=new JDBCBedDao(connection);
		Master master1=new Master();
			master1.setVendor(masterForm.getVendor());
			master1.setInvsttype(masterForm.getInvsttype());
			master1.setCharge(masterForm.getCharge());
			master1.setId(masterForm.getId());
			int result = masterDAO
					.updateoutsourceratemaster(master1);
	} catch (Exception e) {

		e.printStackTrace();
	} 
return "updateoutsourcerate";	
}
public String saveoutsourcerate(){
	Connection connection = null;

	try {
		connection = Connection_provider.getconnection();
		MasterDAO masterDAO=new JDBCMasterDAO(connection);
		BedDao bedDao=new JDBCBedDao(connection);
		
		if(masterForm.getOutsourcerate()!=null){
		for (Master master1 : masterForm.getOutsourcerate()) {
			master1.setVendor(masterForm.getVendor());
			int result = masterDAO
					.addoutsourceratemaster(master1);
		}
		}
	} catch (Exception e) {

		e.printStackTrace();
	} 

	return "savedoutsourcerate";
}
public String deleteoutsourcerate(){
	try {
		String id= request.getParameter("id");
		Connection connection= Connection_provider.getconnection();
		MasterDAO masterDAO= new  JDBCMasterDAO(connection);
		int res = masterDAO.deleteMaster("outsource_investigation_charges", id);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "deleteoutsourcerate";
}


public String allUsersSign(){
	try {
		Connection connection= Connection_provider.getconnection();
		MasterDAO masterDAO= new JDBCMasterDAO(connection);
		UserProfileDAO userProfileDAO= new JDBCUserProfileDAO(connection);
		ArrayList<UserProfile> userList=userProfileDAO.getAllUserListNew();
		masterForm.setUserProfileList(userList);
		masterForm.setSignatureList(masterDAO.userSignaturesList());
		for(UserProfile user:masterForm.getSignatureList()){
			//Destination Dir
			String dest_path = request.getRealPath("/liveData/signature/");
			File destFolder = new File(dest_path);
		/*	if (!destFolder.exists()) {
				destFolder.mkdirs();
			}
			
			//Source Dir
			String source_path=dest_path;
			source_path=source_path.replace("HISTEST", "HISDATA");
			source_path=source_path.replace("HISLIVE", "HISDATA");
			File sourceFolder = new File(source_path);
			if (!sourceFolder.exists()) {
				sourceFolder.mkdirs();
			}
			 File file=new File(source_path+"/"+user.getFilename());
			 File destfile = new File(dest_path+"/"+user.getFilename());
			 FileUtils.copyFile(file, destfile);
			*/
			DateTimeUtils.saveFileToLocalDirectory(dest_path, user.getFilename());
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "allUsersSign";
}

public String saveSign(){
	try {
		Connection connection= Connection_provider.getconnection();
		File uploadedFile = masterForm.getSubuploadfiles();
		//String filecontenttype = masterForm.getSubuploadfilesContentType();
		MasterDAO masterDAO= new JDBCMasterDAO(connection);
		String userid=DateTimeUtils.isNull(masterForm.getName());
		if(userid.equals("")){
			return "savesign";
		}
		String fileName=loginInfo.getClinicUserid()+userid+""+masterForm.getSubuploadfilesFileName();
		String path = request.getRealPath("/liveData/signature/");
		/*path=path.replace("HISTEST", "HISDATA");
		path=path.replace("HISLIVE", "HISDATA");
		File uploadFolder = new File(path);
		if (!uploadFolder.exists()) {
			uploadFolder.mkdirs();
		}
		File fileToCreate = new File(path, fileName);
        FileUtils.copyFile(uploadedFile, fileToCreate);*/
		DateTimeUtils.saveFileToGlobelDirectory(path, fileName, uploadedFile);
        UserProfile userProfile= new UserProfile();
        userProfile.setFilename(fileName);
        userProfile.setId(DateTimeUtils.convertToInteger((masterForm.getName())));
        boolean isadded=masterDAO.userSignExist((masterForm.getName()));
        if(isadded){
        	masterDAO.updateUserSignature(userProfile);
        }else{
        	masterDAO.addUserSignature(userProfile);
        }
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "savesign";
}

//  14-07-2020 birth place master
public String listbirthplace(){
	try {
		Connection connection= Connection_provider.getconnection();
		MasterDAO masterDAO= new JDBCMasterDAO(connection);
		ArrayList<Master> list=new ArrayList<Master>();
		list=masterDAO.getBirthPlaceList();
		masterForm.setList(list);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "listbirthplace";
}

public String addbirthplace(){
	return "addbirthplace";
}

public String savebirthplace(){
	try {
		Connection connection= Connection_provider.getconnection();
		MasterDAO masterDAO= new JDBCMasterDAO(connection);
		String name =request.getParameter("name");
		masterDAO.addBirthPlcae(name);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "savebirthplace";
}

public String editbirthplace(){
	try {
		String id=request.getParameter("id");
		Connection connection= Connection_provider.getconnection();
		MasterDAO masterDAO= new JDBCMasterDAO(connection);
		Master master= new Master();
		master= masterDAO.getMasterInfoBirthplace(id);
		masterForm.setId(master.getId());
		masterForm.setName(master.getName());
				
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "editbirthplace";
}
public String updatebirthplace(){
	try {
		Master master= new  Master();
		master.setName(request.getParameter("name"));
		master.setId(masterForm.getId());
		Connection connection= Connection_provider.getconnection();
		MasterDAO masterDAO= new JDBCMasterDAO(connection);
		int res=masterDAO.updateBirthPlaceMaster(master);
	} catch (Exception e) {
		e.printStackTrace();
	}
return "updatebirthplace";	
}

public String deletebirthplace(){
	try {
		String id= request.getParameter("id");
		Connection connection= Connection_provider.getconnection();
		MasterDAO masterDAO= new  JDBCMasterDAO(connection);
		int res = masterDAO.deleteMaster("birth_place", id);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "deletebirthplace";
}

public String checkexistbirthplace(){
	try {
		String birthplace_name= request.getParameter("birthplace_name");
		Connection connection= Connection_provider.getconnection();
		MasterDAO masterDAO= new  JDBCMasterDAO(connection);
		int res = masterDAO.getbirthplaceExist(birthplace_name);
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
	
			response.getWriter().write(""+res+"");
		} catch (Exception e) {
			e.printStackTrace();
		}
	return null;
}

public String payerCreation(){
	try {
		Connection connection= Connection_provider.getconnection();
		
		String selectedid = request.getParameter("selectedid");
		if(!DateTimeUtils.isNull(selectedid).equals("")){
			mastername = selectedid;
		}
		if(mastername!=null){
			session.setAttribute("mastername", mastername);
		} else {
			mastername=(String)session.getAttribute("mastername");
		}
		masterForm.setMastername(mastername);
		
		ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
		MasterDAO masterDAO = new JDBCMasterDAO(connection);
	
		
		ArrayList<ThirdParty> thirdPartyDetailsList = thirdPartyDAO.getThirdPartyDetailsListData();
		masterForm.setThirdPartyDetailsList(thirdPartyDetailsList);
		
		masterForm.setThirdPartyTypeList(new ArrayList<>());
		masterForm.setTpnameList(new ArrayList<>());
		
		ArrayList<Master> list=new ArrayList<Master>();
		masterForm.setList(list);
		
		ArrayList<Master> masterChargeList = masterDAO.getMasterChargeList();
		masterForm.setMasterChargeList(masterChargeList);
	
		 

	} catch (Exception e) {
		e.printStackTrace();
	}
	return "payerCreation";
}

public String followUpProcedure(){
	try {
		Connection connection= Connection_provider.getconnection();
		
		String selectedid = request.getParameter("selectedid");
		if(!DateTimeUtils.isNull(selectedid).equals("")){
			mastername = selectedid;
		}
		if(mastername!=null){
			session.setAttribute("mastername", mastername);
		} else {
			mastername=(String)session.getAttribute("mastername");
		}
		masterForm.setMastername(mastername);
		
		/*ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);*/
		MasterDAO masterDAO = new JDBCMasterDAO(connection);
	
	
		 

	} catch (Exception e) {
		e.printStackTrace();
	}
	return "followUpProcedure";
}

public String addProcedure(){
	
	return "addprocedure";
}



}