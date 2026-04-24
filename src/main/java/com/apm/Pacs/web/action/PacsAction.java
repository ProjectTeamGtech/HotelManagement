package com.apm.Pacs.web.action;

import ij.Executer;
import ij.IJ;
import ij.ImagePlus;
import ij.Menus;
import ij.WindowManager;
import ij.gui.ImageCanvas;
import ij.io.FileInfo;
import ij.io.FileOpener;
import ij.io.Opener;
import ij.plugin.DicomDecoder;
import ij.plugin.JpegWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.apm.Accounts.eu.bi.AccountsDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAccountsDAO;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.Emr.eu.bi.InvestigationDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCInvestigationDAO;
import com.apm.Emr.eu.entity.Emr;
import com.apm.Emr.eu.entity.Investigation;
import com.apm.Ipd.eu.bi.BedDao;
import com.apm.Ipd.eu.bi.IpdDAO;
import com.apm.Ipd.eu.blogic.jdbc.JDBCBedDao;
import com.apm.Ipd.eu.blogic.jdbc.JDBCIpdDAO;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Master.eu.bi.InvestigationTemplateDAO;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCInvestigationTemplate;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.entity.InvestigationTemplate;
import com.apm.Master.eu.entity.Master;
import com.apm.Pacs.eu.bi.PacsDAO;
import com.apm.Pacs.eu.blogic.JDBCPacsDAO;
import com.apm.Pacs.eu.entity.Pacs;
import com.apm.Pacs.web.form.PacsForm;
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
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;



public class PacsAction extends BaseAction implements ModelDriven<PacsForm>, Preparable {

	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse) ActionContext.getContext()
			.get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	PacsForm pacsform = new PacsForm();

	public PacsForm getModel() {
		// TODO Auto-generated method stub
		return pacsform;
	}

	@Override
	public String execute() throws Exception {

		if (!verifyLogin(request)) {
			return "login";
		}

		String clientid = request.getParameter("clientid");
		String abrivationid = request.getParameter("abrivationid");
		String fromDate = pacsform.getFromDate();
		String toDate = pacsform.getToDate();
        if(clientid==null) {
        	clientid="0";
        }
		if (fromDate.equals("")) {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -7);
			fromDate = dateFormat.format(cal.getTime());
			pacsform.setFromDate(fromDate);
		}
		if (toDate.equals("")) {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			// cal.add(Calendar.DATE, -7);
			toDate = dateFormat.format(cal.getTime());
			pacsform.setToDate(toDate);
		}

		if (!fromDate.equals("")) {
			String temp[] = fromDate.split("/");
			fromDate = temp[2] + "-" + temp[1] + "-" + temp[0];
		}
		if (!toDate.equals("")) {
			String temp1[] = toDate.split("/");
			toDate = temp1[2] + "-" + temp1[1] + "-" + temp1[0];
		}

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			PacsDAO pacsDAO = new JDBCPacsDAO(connection);
			ClientDAO clientDAO = new JDBCClientDAO(connection);

			ArrayList<Pacs> pacsdataList = pacsDAO.getwebpacsList(connection, pacsform.getSearchText(), fromDate,
					toDate, pacsform.getSelectedmodality(), pacsform.getSearchInvstid(), clientid,
					loginInfo.getPacsip());
			pacsform.setPacsdataList(pacsdataList);

			if (!clientid.equals("0")) {
				Client client = clientDAO.getClientDetails(clientid);
				String fiullname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();
				pacsform.setClientname(fiullname);

			}

			pacsform.setClientid(clientid);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {

			connection.close();
		}

		return super.execute();
	}

	public String download() throws IOException {
		FileInputStream in = null;
		ServletOutputStream out = null;
		/*
		 * HttpServletRequest servletRequest=(HttpServletRequest)
		 * ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		 * HttpServletResponse response =
		 * (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.
		 * HTTP_RESPONSE);
		 */

		String filename = request.getParameter("fname");
		String filePath = "/liveData/emailAttachments/";
		File file = new File(request.getRealPath(filePath + filename + ""));
		if (file.exists()) {
			// return an application file instead of html page
			// response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + filename + "");

			try {
				in = new FileInputStream(file);
				out = response.getOutputStream();

				byte[] outputByte = new byte[4096];
				// copy binary contect to output stream
				while (in.read(outputByte, 0, 4096) != -1) {
					out.write(outputByte, 0, 4096);
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		in.close();
		out.flush();
		out.close();

		return null;
	}

	public String wordfile() throws Exception {
		String fileName = "";
		for (int i = 0; i < pacsform.getFileUpload().length; i++) {
			File uploadedFile = pacsform.getFileUpload()[i];
			fileName = pacsform.getFileUploadFileName()[i];

			// save dicom info

			/*
			 * FileOpener opener = new FileOpener(fi); opener.open();
			 */

			// save dicom image
			String filePath = request.getRealPath("/liveData/emailAttachments/");
			fileName = pacsform.getSelectedinvstid() + "finding_" + fileName;

			File fileToCreate = new File(filePath, fileName);
			try {
				FileUtils.copyFile(uploadedFile, fileToCreate);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			PacsDAO pacsDAO = new JDBCPacsDAO(connection);

			int upd = pacsDAO.updatewordfilename(pacsform.getSelectedinvstid(), fileName);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {

			connection.close();
		}

		return folder();
	}

	public String folder() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}

		String clientid = request.getParameter("clientid");

		String fromDate = pacsform.getFromDate();
		String toDate = pacsform.getToDate();

		if (fromDate.equals("")) {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -7);
			fromDate = dateFormat.format(cal.getTime());
			pacsform.setFromDate(fromDate);
		}
		if (toDate.equals("")) {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			// cal.add(Calendar.DATE, -7);
			toDate = dateFormat.format(cal.getTime());
			pacsform.setToDate(toDate);
		}

		if (!fromDate.equals("")) {
			String temp[] = fromDate.split("/");
			fromDate = temp[2] + "-" + temp[1] + "-" + temp[0];
		}
		if (!toDate.equals("")) {
			String temp1[] = toDate.split("/");
			toDate = temp1[2] + "-" + temp1[1] + "-" + temp1[0];
		}

		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			PacsDAO pacsDAO = new JDBCPacsDAO(connection);

			ArrayList<Pacs> folderList = pacsDAO.getFolderList(fromDate, toDate);
			pacsform.setFolderList(folderList);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {

			connection.close();
		}

		return "folder";
	}

	public String multi() {
		String fileName = "";
		Connection connection = null;
		String id = request.getParameter("id");
		try {
			connection = Connection_provider.getconnection();
			PacsDAO pacsDAO = new JDBCPacsDAO(connection);

			int t = pacsDAO.truncatemultipacs(loginInfo.getUserId());

			String sql = "select dicomimg,filename,id from dicom_list where multpacsid = " + id + "   ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int dicomid = rs.getInt(3);
				int r = pacsDAO.insertDicomid(dicomid, loginInfo.getUserId());
				InputStream in = rs.getBinaryStream(1);
				fileName = id + "_" + rs.getString(2);
				String path = request.getRealPath("/liveData/dicom/" + fileName);
				File file = new File(path);
				if (!file.exists()) {
//						System.out.println("file not exist");
					OutputStream f = new FileOutputStream(new File(path));

					int c = 0;
					while ((c = in.read()) > -1) {
						f.write(c);
					}
					f.close();
					in.close();
				}

				String directory = request.getRealPath("/liveData/dicom/");
				String fname = directory + "/" + fileName + ".jpeg";
				file = new File(fname);
				if (!file.exists()) {
					DicomDecoder dd = new DicomDecoder(directory + "/", fileName);
					dd.insertid = 0;
					FileInfo fi = null;

					fi = dd.getFileInfo();

					// String info = dd.getDicomInfo();
					// System.out.println(info);

					String inputURL = directory + fileName;
					FileOpener opener = new FileOpener(fi);
					// opener.open();
					ImagePlus imp = opener.openImage();
					path = directory + "/";
					String err = JpegWriter.save(imp, path + fileName + ".jpeg", 85);
				}

			}

			int pacscount = pacsDAO.getPacsCount(id);
			ArrayList<Pacs> pimglist = pacsDAO.getPacsImgList(id);
			session.setAttribute("pacscount", pacscount);
			session.setAttribute("multipacsid", id);
			session.setAttribute("pimglist", pimglist);

		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "multiviewer";
	}

	public String edit() throws Exception {
		String id = request.getParameter("id");
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			PacsDAO pacsDAO = new JDBCPacsDAO(connection);

			String finding = pacsDAO.getSelectedFinding(id);

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("" + finding + "");

		} catch (Exception e) {
			// TODO: handle exception
		} finally {

			connection.close();
		}

		return null;
	}

	public String savedicom() throws Exception {
		Connection connection = null;
		String directory = "C:/dicom/";
		String fileName = "";

		// copy the uploaded files into pre-configured location
		for (int i = 0; i < pacsform.getFileUpload().length; i++) {
			File uploadedFile = pacsform.getFileUpload()[i];
			fileName = pacsform.getFileUploadFileName()[i];

			// save dicom info

			/*
			 * FileOpener opener = new FileOpener(fi); opener.open();
			 */

			// save dicom image
			String filePath = request.getRealPath("/liveData/emailAttachments/");
			fileName = pacsform.getSelectedinvstid() + "_" + fileName;

			File fileToCreate = new File(filePath, fileName);
			FileUtils.copyFile(uploadedFile, fileToCreate);

			DicomDecoder dd = new DicomDecoder(filePath, fileName);
			dd.directory = filePath + "/";
			dd.fileName = fileName;
			dd.insertid = 0;
			dd.invstid = pacsform.getSelectedinvstid();
			dd.lastmodified = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());

			FileInfo fi = null;
			try {

				fi = dd.getFileInfo();
				String info = dd.getDicomInfo();
//	 	 			System.out.println(info);

				PreparedStatement preparedStatement = null;
				String sql = "update dicom_list set dicomimg=? where id = " + dd.insertid + "";
				int re = 0;

				File imgfile = new File(filePath + "/" + fileName);
				FileInputStream fin = new FileInputStream(imgfile);

				connection = Connection_provider.getconnection();
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setBinaryStream(1, (InputStream) fin, (int) imgfile.length());
				re = preparedStatement.executeUpdate();

			} catch (IOException ex) {
//                System.out.println("Could not copy file " + fileName);
				ex.printStackTrace();
			} finally {

				connection.close();
			}
		}

		return "savedicom";
	}

	public String finding() throws Exception {
		Connection connection = null;
		try {

			String txt = pacsform.getOtnotes();
			;

			connection = Connection_provider.getconnection();
			PacsDAO pacsDAO = new JDBCPacsDAO(connection);
			int selectedid = pacsform.getId();

			int r = pacsDAO.updatefinding(selectedid, txt);

			String invstid = pacsDAO.getSelectedInvstid(selectedid);

			session.setAttribute("selectedinvstid", invstid);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {

			connection.close();
		}

		return "editfinding";
	}

	public String finding1() {
		String loginid = (String) session.getAttribute("loginid");
		String mpid = (String) session.getAttribute("mpid");
		// String clinicuserid = (String)session.getAttribute("clinicuserid");
		//String clinicuserid = Constants.CLINICID;
		// String investigationid = pacsform.getInvname();
		String investigationid = "";
		Connection connection = null;
		String multipacsid = request.getParameter("multipacsid");
		pacsform.setMultipacsid(multipacsid);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// connection=DriverManager.getConnection("jdbc:mysql://"+Constants.HISIP+":3306/"+clinicuserid+"","manasyuvi","Manas8888#$%Yuvi");

			connection = Connection_provider.getconnection();
			PacsDAO pacsDAO = new JDBCPacsDAO(connection);
			ArrayList<Pacs> templateList = pacsDAO.getTemplateList();
			pacsform.setTemplateList(templateList);
			
			// int upstatus = dicomDAO.UpdateUpStatus(investigationid,loginid,"0");

			// String patientid = dicomDAO.getpacsPatientid(investigationid);

			// save multiple files

			/*
			 * for (int i = 0; i < pacsform.getFileUpload().length; i++) { File uploadedFile
			 * = pacsform.getFileUpload()[i]; String fileName =
			 * pacsform.getFileUploadFileName()[i]; fileName = Constants.CLINICID + "_"
			 * +investigationid + "_" + fileName;
			 * 
			 * //fileName = invsttype + "_" + invstid + "_" + fileName; String filePath =
			 * request.getRealPath("/pacsimg/");
			 * 
			 * String workingDirectory = System.getProperty("user.dir"); String
			 * absoluteFilePath = workingDirectory + File.separator +
			 * loginInfo.getClinicUserid() + File.separator + fileName;
			 * 
			 * 
			 * System.out.println("Server path:" + filePath); try { File fileToCreate = new
			 * File(filePath, fileName); FileUtils.copyFile(uploadedFile, fileToCreate);
			 * 
			 * String curdate = DateTimeUtils.getUKCurrentDataTime("IST");
			 * 
			 * File file = new File(filePath + "/" + fileName); FileInputStream fis = new
			 * FileInputStream(file);
			 * 
			 * int result =
			 * dicomDAO.savePatientDocument(fileName,curdate,investigationid,"0",file,fis,
			 * patientid);
			 * 
			 * } catch (IOException ex) { System.out.println("Could not copy file " +
			 * fileName); ex.printStackTrace(); } }
			 */

		} catch (Exception e) {
			// TODO: handle exception
		}

		return "report";
	}

	public String report() throws Exception{

		Connection connection = null;
		String finding = pacsform.getOther_template_text();
		//String multipacsid = request.getParameter("multipacsid");
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			connection = Connection_provider.getconnection();
			PacsDAO pacsDAO = new JDBCPacsDAO(connection);
			String multipacsid =pacsform.getMultipacsid();
			InvestigationTemplateDAO invDAO=new JDBCInvestigationTemplate(connection);
			InvestigationTemplate master=new InvestigationTemplate();
			//Master master = new Master();
			String dateTime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());

			master.setTitle(pacsform.getTitle());
			master.setDateTime(dateTime);
			master.setTemplate_text(pacsform.getOther_template_text());
			
			
			int result = invDAO.addInvestigationTemplate(master);
			int update = pacsDAO.updateWriteupData(Integer.parseInt(multipacsid), finding, "", "");

			String curdate = DateTimeUtils.getUKCurrentDataTime("IST").split(" ")[0];
			/*
			 * ArrayList<Dicom> invstList = dicomDAO.getInvstList(curdate);
			 * dicomForm.setInvstList(invstList);
			 */

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return execute();
	}
public String template(){
		
		String mpid = (String)session.getAttribute("mpid");
		//String clinicuserid = (String)session.getAttribute("clinicuserid");
		
		String id = request.getParameter("id");
		Connection connection = null;
		
		try{
			connection = Connection_provider.getconnection();
			PacsDAO pacsDAO = new JDBCPacsDAO(connection);
			
			String data = pacsDAO.getTemplateData(id);
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+data+""); 
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}
	public void prepare() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			PacsDAO pacsDAO = new JDBCPacsDAO(connection);
			String curdate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			String temp[] = curdate.split(" ");
			curdate = temp[0];
			ArrayList<Master> invstList = pacsDAO.getInvstList(curdate);
			pacsform.setInvstList(invstList);

			// modality list
			ArrayList<Master> modalityList = pacsDAO.getModalityList();
			pacsform.setModalityList(modalityList);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {

			connection.close();
		}

	}

	public String getPacsRecordonpacstab() throws Exception {

		String clientid = request.getParameter("clientid");
		String abrivationid = request.getParameter("abrivationid");
		String fromDate = pacsform.getFromDate();
		String toDate = pacsform.getToDate();

		if (fromDate.equals("")) {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -7);
			fromDate = dateFormat.format(cal.getTime());
			pacsform.setFromDate(fromDate);
		}
		if (toDate.equals("")) {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			// cal.add(Calendar.DATE, -7);
			toDate = dateFormat.format(cal.getTime());
			pacsform.setToDate(toDate);
		}

		if (!fromDate.equals("")) {
			String temp[] = fromDate.split("/");
			fromDate = temp[2] + "-" + temp[1] + "-" + temp[0];
		}
		if (!toDate.equals("")) {
			String temp1[] = toDate.split("/");
			toDate = temp1[2] + "-" + temp1[1] + "-" + temp1[0];
		}

		Connection connection = null;
		try {

			connection = Connection_provider.getconnection();
			PacsDAO pacsDAO = new JDBCPacsDAO(connection);
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			ArrayList<Pacs> pacsdataList = pacsDAO.getwebpacsList(connection, pacsform.getSearchText(), fromDate,
					toDate, pacsform.getSelectedmodality(), pacsform.getSearchInvstid(), clientid,
					loginInfo.getPacsip());
			pacsform.setPacsdataList(pacsdataList);

			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			connection.close();
		}

		return null;
	}

	public String edituhid() throws Exception {
		Connection connection = null;
		try {
			String id = request.getParameter("abriid");
			String uhid = request.getParameter("uhid");
			String pacsid = request.getParameter("pacsid");
			connection = Connection_provider.getconnection();
			PacsDAO pacsDAO = new JDBCPacsDAO(connection);
			// Pacs pacs=pacsDAO.getPatientUhidbyid(id);
			Pacs pacs = new Pacs();
			// pacs.setAbrivationid(pacsform.getAbrivationid());
			int result = pacsDAO.updatepatientuhid(pacs, id, uhid, loginInfo.getPacsip(), pacsid);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return execute();
	}

	public String printpage() throws Exception {

		if (!verifyLogin(request)) {

			return "login";
		}
		Connection connection = null;
		String selectedid = request.getParameter("pacsid");
		if (selectedid != null) {
			if (selectedid.equals("") || selectedid.equals("0")) {
				int parentid = (Integer) session.getAttribute("invstsaveparent");
				selectedid = String.valueOf(parentid);
				session.removeAttribute("invstsaveparent");
			}

		} else {

		}

		String rpt = request.getParameter("rpt");
		if (rpt != null) {
			if (rpt.equals("") || rpt.equals("0")) {
				rpt = (String) session.getAttribute("invwriteup");
				session.removeAttribute("invwriteup");
			}
		} else {
			rpt = (String) session.getAttribute("invwriteup");
			session.removeAttribute("invwriteup");
		}
		String sectionid = request.getParameter("sectionid");
		if (sectionid != null) {
			if (sectionid.equals("") || sectionid.equals("0")) {
				sectionid = (String) session.getAttribute("invsectionid");
				session.removeAttribute("invsectionid");
			}
		} else {
			sectionid = (String) session.getAttribute("invsectionid");
			session.removeAttribute("invsectionid");
		}

		String fromdate = request.getParameter("fromdate");
		String todate = request.getParameter("todate");
		String lastday = "";
		if (fromdate != null) {
			if (fromdate.equals("") || fromdate.equals("0")) {
				lastday = (String) session.getAttribute("invlastmodi");
				session.removeAttribute("invlastmodi");
				fromdate = lastday.split(" ")[0];
				todate = fromdate;
			}
		} else {
			lastday = (String) session.getAttribute("invlastmodi");
			session.removeAttribute("invlastmodi");
			if (lastday != null) {
				fromdate = lastday.split(" ")[0];
				todate = fromdate;
			}
		}
		try {
			// connection=DriverManager.getConnection("jdbc:mysql://117.236.76.142:3306/vspm","manasyuvi","M@n@S1928YUVI#$@%");
			connection = Connection_provider.getconnection();
			InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);

			ArrayList<Emr> invstdocList = investigationDAO.getInvstDocList(selectedid);
			pacsform.setDocList(invstdocList);

			String invsttype = investigationDAO.getPrintInvsttype(selectedid);
			pacsform.setInvsttype(invsttype);
			UserProfileDAO userProfileDAO1 = new JDBCUserProfileDAO(connection);
			PacsDAO pacsDAO = new JDBCPacsDAO(connection);
			Investigation investigation = investigationDAO.getEditInvestigation(selectedid);

			pacsform.setJobtitle(investigation.getJobtitle());

			pacsform.setRequested_uerid(userProfileDAO1
					.getUserFullNameFromId("" + userProfileDAO1.getDiaryUserId(investigation.getRequested_userid())));

			pacsform.setInvesttemp(selectedid);

			// pacs report print 07/04/2023
			Pacs pacs = pacsDAO.getpacsFindingbypacsid(selectedid);
			session.setAttribute("finding", pacs.getFinding());
			session.setAttribute("findinguser", pacs.getFindinguser());
			pacsform.setAbrivationid(pacs.getAbrivationid());

			ArrayList<Investigation> selectedInvstList = investigationDAO.getPrintSelectedInvestigationData(selectedid);
			pacsform.setSelectedInvstList(selectedInvstList);

			pacsform.setReportType(rpt);

			if (investigation.getIpdid() == null) {
				pacsform.setIpdid("0");
				pacsform.setWardname("");
				pacsform.setBedname("");
			} else {
				BedDao bedDao = new JDBCBedDao(connection);
				IpdDAO ipdDAO = new JDBCIpdDAO(connection);
				String ipdid = investigation.getIpdid();
				Bed bed = bedDao.getEditIpdData(ipdid);
				String ward = ipdDAO.getIpdWardName(bed.getWardid());
				String bedname = ipdDAO.getIpdBedName(bed.getBedid());
				pacsform.setWardname(ward);
				pacsform.setBedname(bedname);
				pacsform.setIpdid(ipdid);
				pacsform.setDaycare(bedDao.isDayCare(ipdid));

			}

			// set client data

			ClientDAO clientDAO = new JDBCClientDAO(connection);
			String clientid = clientDAO.getclientidbyuhid(pacs.getAbrivationid());
			Client client = clientDAO.getClientDetails(clientid);
			if (client.getTypeName() == null) {
				client.setTypeName("0");
			}
			if (client.getTypeName().equals("")) {
				client.setTypeName("0");
			}
			pacsform.setTpid(client.getTypeName());
			if (client.getTpDetails() != null) {
				pacsform.setThirdPartyCompanyName(client.getTpDetails().getCompanyName());
			}

			if (client.getType() == null) {
				client.setType("0");
			}
			pacsform.setTptype(client.getType());
			String payby = "";
			/*
			 * if(client.getWhopay().equals(Constants.PAY_BY_THIRD_PARTY)){ payby =
			 * "Third Party"; }else{ payby = "Client"; }
			 */

			String fullname = client.getTitle() + " " + client.getFirstName() + " " + client.getMiddlename() + " "
					+ client.getLastName();
			String agegender = "";
			String dob = client.getDob();
			String age = DateTimeUtils.getAge(client.getDob());
			if (Integer.parseInt(age) < 2) {
				if (Integer.parseInt(age) < 1) {
					String monthdays = DateTimeUtils.getMonthandDays(client.getDob());
					agegender = monthdays + " / " + client.getGender();
				} else {
					String monthdays = DateTimeUtils.getMonthandDays(client.getDob());
					agegender = age + " Years" + " " + monthdays + " / " + client.getGender();
				}
			} else {
				agegender = age + " / " + client.getGender();
			}
			agegender = DateTimeUtils.getAge1(client.getDob()) + " / " + client.getGender();
			// String ageandgender = DateTimeUtils.getAge(client.getDob());

			// String ageandgender = DateTimeUtils.getAge(client.getDob());
			pacsform.setFullname(fullname);
			pacsform.setAgeandgender(agegender);
			pacsform.setClientId(clientid);
			pacsform.setGender(client.getGender());
			pacsform.setDateTime(investigation.getDate());
			// pacsform.setId(Integer.parseInt(selectedid));
			// pacsform.setAbrivationid(client.getAbrivationid());
			pacsform.setUpdate_date(investigation.getUpdate_date());
			pacsform.setComplete_date(investigation.getComplete_date());
			pacsform.setCollect_date(investigation.getCollect_date());
			pacsform.setRemark(investigation.getRemark());
			if (loginInfo.getInvestigation_newaccess().equals("1")) {
				String reporton = pacsform.getUpdate_date();
				if (reporton != null) {
					if (!reporton.equals("")) {
						String temp[] = reporton.split(" ");
						pacsform.setUpdate_date(temp[0]);
					}
				}
			}

			// show admin for all user other than practitioner
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			UserProfile userProfile = userProfileDAO
					.getUserprofileDetails(Integer.parseInt(investigation.getPrectionerid()));

			String practfullname = userProfile.getInitial() + " " + userProfile.getFirstname() + " "
					+ userProfile.getLastname();
			pacsform.setPractitionerName(practfullname);
			pacsform.setDepartment(userProfile.getSpecialization());
			pacsform.setPractDepartment(userProfile.getSpecialization());
			if (investigation.getPrectionerid().equals("0")) {
				practfullname = clientDAO.getGPname(client.getGpid());
			} else {
				if (investigation.getUserid() != null) {
					userProfile = userProfileDAO.getUserprofileDetails(Integer.parseInt(investigation.getUserid()));
					practfullname = userProfile.getInitial() + " " + userProfile.getFirstname() + " "
							+ userProfile.getLastname();
				}
			}

			pacsform.setDiaryUser(practfullname);
			String quali = userProfile.getQualification();
			pacsform.setQualification(userProfile.getQualification());
			pacsform.setSpecialization(userProfile.getSpecialization());

			int duserid = Integer.parseInt(investigation.getUpdatedby());
			userProfile = userProfileDAO.getUserprofileDetails(duserid);
			practfullname = userProfile.getInitial() + " " + userProfile.getFirstname() + " "
					+ userProfile.getLastname();
			pacsform.setPathLabuser(practfullname);
			String quali1 = userProfile.getQualification();
			if (quali == null) {
				pacsform.setQualification(quali1);
			}

			// letter head
			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			// ArrayList<Clinic> locationAdressList =
			// accountsDAO.getLetterHeadDetails(loginInfo.getClinicid());

			ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());

			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			pacsform.setClinicName(clinic.getClinicName());
			pacsform.setClinicOwner(clinic.getClinicOwner());
			pacsform.setOwner_qualification(clinic.getOwner_qualification());
			pacsform.setLocationAdressList(locationAdressList);
			pacsform.setAddress(clinic.getAddress());
			pacsform.setLandLine(clinic.getLandLine());
			pacsform.setClinicemail(clinic.getEmail());
			pacsform.setWebsiteUrl(clinic.getWebsiteUrl());
			pacsform.setClinicLogo(clinic.getUserImageFileName());
			pacsform.setUserid(loginInfo.getUserId());
			pacsform.setMobile(clinic.getMobileNo());

			if (loginInfo.getJobTitle().equals("Pathlab") || loginInfo.getJobTitle().equals("Radiologist")) {
				clinic = clinicDAO.getLettHeadDetails(loginInfo.getUserId());
				Clinic clinic1 = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
				clinic.setOwner_qualification(clinic1.getOwner_qualification());
				clinic.setClinicOwner(clinic1.getClinicOwner());
			} else {
				clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			}

			if (sectionid == null) {
				sectionid = "0";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "printpacs";

	}

}
