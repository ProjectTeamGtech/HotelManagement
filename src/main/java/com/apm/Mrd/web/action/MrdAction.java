package com.apm.Mrd.web.action;

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
import com.apm.DiaryManagement.eu.bi.NotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCNotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.Dietary.eu.entity.DietaryDetails;
import com.apm.Inventory.eu.bi.InventoryProductDAO;
import com.apm.Inventory.eu.bi.InventoryVendorDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryProductDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryVendorDAO;
import com.apm.Ipd.eu.bi.BedDao;
import com.apm.Ipd.eu.bi.IpdDAO;
import com.apm.Ipd.eu.bi.IpdLogDAO;
import com.apm.Ipd.eu.blogic.jdbc.JDBCBedDao;
import com.apm.Ipd.eu.blogic.jdbc.JDBCIpdDAO;
import com.apm.Ipd.eu.blogic.jdbc.JDBCIpdLogDAO;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Mrd.eu.bi.MrdDAO;
import com.apm.Mrd.eu.blogic.JDBCMrdDAO;
import com.apm.Mrd.eu.entity.Mrd;
import com.apm.Mrd.web.form.MrdForm;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.ThirdParties.eu.bi.ThirdPartyDAO;
import com.apm.ThirdParties.eu.blogic.jdbc.JDBCThirdPartyDAO;
import com.apm.ThirdParties.eu.entity.ThirdParty;
import com.apm.TreatmentEpisode.eu.bi.TreatmentEpisodeDAO;
import com.apm.TreatmentEpisode.eu.blogic.jdbc.JDBCTreatmentEpisode;
import com.apm.TreatmentEpisode.eu.entity.TreatmentEpisode;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.common.web.utils.PopulateList;
import com.apm.main.common.constants.Constants;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class MrdAction extends BaseAction implements ModelDriven<MrdForm>,Preparable{
	private static final long serialVersionUID = 1L;
	MrdForm mrdForm = new MrdForm();
	
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpSession session = request.getSession();
	 Pagination pagination=new Pagination(25,1);
	 String mastername;
	public Pagination getPagination() {
		return pagination;
	}


	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}


	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);

	public MrdForm getModel() {
		return mrdForm;
	}
	
	
	public String execute() throws Exception {
		 Connection connection = null;
			try {
				connection = Connection_provider.getconnection();
				
				String searchtext = mrdForm.getSearchtext();
				String fromdate = mrdForm.getFromdate();
				String todate = mrdForm.getTodate();
				String searchbyipdid=mrdForm.getIpdidsearch();
				//  03 feb 2018 add filter search by which date
				
				String searchbydate = mrdForm.getSearchbydate();
				
				if(searchbydate==null){
					searchbydate="2";
				}else if(searchbydate.equals("")){
					searchbydate="2";
				}
				
				//String wardnameid = mrdForm.getWardnameid();
				String department = mrdForm.getPatient_department();
				if(searchtext!=null){
					 
					if(searchtext.equals("")){
						searchtext=null;
					}
				}
			
				/*if(wardnameid!=null){
					if(wardnameid.equals("0")){
						wardnameid=null;		
					}
				}*/
//				if(searchbyipdid!=null){
//					 
//					if(searchbyipdid.equals("")){
//						searchbyipdid=null;
//					}
//				}
				
				if(department!=null){
					if(department.equals("0")){
						department=null;		
					}
				}
				
				if(fromdate == null){
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
					//cal.add(Calendar.DATE, -7);
					fromdate = dateFormat.format(cal.getTime());			
					fromdate = DateTimeUtils.getCommencingDate1(fromdate);
					
				}
				else {
					
					if(fromdate.equals("")) {
						DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
						Calendar cal = Calendar.getInstance();
						//cal.add(Calendar.DATE, -7);
						fromdate = dateFormat.format(cal.getTime());			
						fromdate = DateTimeUtils.getCommencingDate1(fromdate);
						
					} else {
						
						fromdate = DateTimeUtils.getCommencingDate1(fromdate);		 
					}		
				}
				
				if(todate== null){
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance(); 
					todate = dateFormat.format(cal.getTime());
					todate = DateTimeUtils.getCommencingDate1(todate);
				} else {
					
					if(todate.equals("")){
						DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
						Calendar cal = Calendar.getInstance(); 
						todate = dateFormat.format(cal.getTime());
						todate = DateTimeUtils.getCommencingDate1(todate);
					} else {
						todate = DateTimeUtils.getCommencingDate1(todate);
					}
					
				}
				
				//IpdDAO ipdDAO = new JDBCIpdDAO(connection);
				MrdDAO mrdDAO = new JDBCMrdDAO(connection);
				int count = mrdDAO.getTotalMrdCount(fromdate,todate,searchtext,department,searchbydate);
				pagination.setPreperties(count);
				
				//ArrayList<Mrd> arrayList = mrdDAO.getmrddetails(fromdate,todate,searchtext,wardnameid,pagination);
				ArrayList<Mrd> arrayList =  mrdDAO.getmrdlist(fromdate,todate,searchtext,department,pagination,searchbydate,searchbyipdid,loginInfo.getIpd_abbr_access());
				//ArrayList<Mrd> arrayList =  mrdDAO.getmrddetails(fromdate, todate, searchtext, wardnameid, pagination);
				mrdForm.setMrdlist(arrayList);
				
				pagination.setPage_records(arrayList.size());
				mrdForm.setPagerecords(String.valueOf(pagination.getPage_records()));
				mrdForm.setTotalRecords(String.valueOf(pagination.getPage_records()));
				mrdForm.setTotalRecords(String.valueOf(count));
					if (mastername != null) {
						session.setAttribute("mastername", mastername);
					} else {

						mastername = (String) session.getAttribute("mastername");
					}
					mrdForm.setMastername(mastername);
				
				
				fromdate = DateTimeUtils.getCommencingDate1(fromdate);
				todate = DateTimeUtils.getCommencingDate1(todate);
	            mrdForm.setFromdate(fromdate);
	            mrdForm.setTodate(todate);
	            mrdForm.setPatient_department(department);
				
	            BedDao bedDao = new JDBCBedDao(connection);
				ArrayList<Bed> wardlist = bedDao.getAllWardList("0");
				mrdForm.setWardlist(wardlist);
				
				String templateId = mrdDAO.gettemplateid();
				mrdForm.setTemplateID(templateId);
				
				/*NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);			
				ArrayList<DiaryManagement>userList = notAvailableSlotDAO.getUserList(loginInfo.getId());
				mrdForm.setUserList(userList);
				*/
				mrdForm.setSearchbydate(searchbydate);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				connection.close();
			}
			return "mrd";
		}
	
	
	public String updatemrd() throws Exception {
		int ipdid = Integer.parseInt(request.getParameter("ipdid"));
		String clientid = request.getParameter("clientid");
		String shelf_no = request.getParameter("shelf_no");
		String place = request.getParameter("place");
		String remark = request.getParameter("remark");
		String mlc = request.getParameter("mlc");
		String status = request.getParameter("status");
		String ondata = "on";
		
		if(status==""){
			status="1";	
		}
		
		if(status.equals("0"))
			status="1";
		Mrd mrd = new Mrd();
		mrd.setIpdid(ipdid);
		mrd.setClientid(clientid);
		mrd.setShelf_no(shelf_no);
		mrd.setPlace(place);
		if(mlc==null){
			mlc="0";
		}
		mrd.setRemark(remark);
		//if(mlc=="on")
		if(mlc.equalsIgnoreCase("on"))
		{
			mlc="1";
		}else
		{
			mlc="0";
		}
		
		mrd.setMlc(mlc);
		mrd.setStatus(status);
		
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			//IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			MrdDAO mrdDAO = new JDBCMrdDAO(connection);
			int result = mrdDAO.updatemrdetails(mrd);
//			ArrayList<Mrd> arrayList = ipdDAO.getmrddetails();
//			mrdForm.setMrdlist(arrayList);
			this.addActionMessage("Information Saved Successfully");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "updated";
	}
	
	
	
	
	
	 
	public String editstatus() throws Exception{
 		Connection connection = null;
 		try {
 			String ipdid=request.getParameter("ipdid");
 			connection=Connection_provider.getconnection();
 			//IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			MrdDAO mrdDAO = new JDBCMrdDAO(connection);
 			int result = mrdDAO.changeMrdStatus(ipdid);
			//execute();
 			response.setContentType("text/html");
 			response.setHeader("Cache-Control", "no-cache");
 			response.getWriter().write("Success");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		connection.close();
		}
	return null;
	}
	
	public String addPatient() throws Exception{
		return "mrdAddPatient";
	}

	public void prepare() throws Exception {
		Connection connection = null;
		
		ArrayList<Client> thirdPartyTypeList = new ArrayList<Client>();
		//ArrayList<Client> thirdPartyTypeNameList = new ArrayList<Client>();
		ArrayList<Client> clientOccupationList = new ArrayList<Client>();
		ArrayList<Client> refrenceList = new ArrayList<Client>();
		ArrayList<String> initialList = new ArrayList<String>();
		ArrayList<Client> sourceOfIntroList = new ArrayList<Client>();
		ArrayList<Client> gpList = new ArrayList<Client>();
		ArrayList<Client> condtitionList = new ArrayList<Client>();

		try{
			
			connection = Connection_provider.getconnection();
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			gpList = clientDAO.getGpList();
			mrdForm.setGpList(gpList);

			condtitionList = clientDAO.getEmrTreatmentTypeList();
			mrdForm.setCondtitionList(condtitionList);
			
			ArrayList<DiaryManagement> userList = notAvailableSlotDAO.getUserList(loginInfo.getId());
			mrdForm.setUserList(userList);

			thirdPartyTypeList = clientDAO.getThirdPartyType();
			mrdForm.setThirdPartyTypeList(thirdPartyTypeList);
			
			//thirdPartyTypeNameList = clientDAO.getThirdPartyTypeName();
			//mrdForm.setThirdPartyTypeNameList(thirdPartyTypeNameList);
			
			clientOccupationList = clientDAO.getOccupationList();
			mrdForm.setHourList(PopulateList.hourList());
			mrdForm.setMinuteList(PopulateList.getMinuteList());
			
			
			mrdForm.setClientOccupationList(clientOccupationList);
			
			refrenceList = clientDAO.getReferenceList();
			
			mrdForm.setRefrenceList(refrenceList);
			
			initialList = clientDAO.getInitialList();
			mrdForm.setInitialList(initialList);
			
			sourceOfIntroList = clientDAO.getSourceOfIntroList();
			mrdForm.setSourceOfIntroList(sourceOfIntroList);
			
			
			//set surgery list
			ArrayList<Client>surgeryList = clientDAO.getSurgeryList();
			mrdForm.setSurgeryList(surgeryList);
			
			ArrayList<ThirdParty> tpnameList = new ArrayList<ThirdParty>();
			ThirdPartyDAO  thirdPartyDAO = new JDBCThirdPartyDAO(connection);

			tpnameList = thirdPartyDAO.getTPNameList();
			mrdForm.setTpnameList(tpnameList);
			mrdForm.setApmtDurationList(PopulateList.apmtDurationList());
			TreatmentEpisodeDAO treatmentEpisodeDAO = new JDBCTreatmentEpisode(connection);

			ArrayList<TreatmentEpisode> sourceOfReferralList = treatmentEpisodeDAO.getSourceOfReferralList();
			mrdForm.setSourceOfReferralList(sourceOfReferralList);
			
			//set declearation list
			ArrayList<Master>declerationTitleList = clientDAO.getDeclerationTitleList();
			mrdForm.setDeclerationTitleList(declerationTitleList);
			String selecteddecid = clientDAO.getSelectedDecId();
			mrdForm.setDectitle(selecteddecid);
			
			//set state and city list
			InventoryVendorDAO vendorDAO=new JDBCInventoryVendorDAO(connection);
			ArrayList<Master> stateList= vendorDAO.getAllStateList();
			ArrayList<Master> cityList= vendorDAO.getAllCityList();
			mrdForm.setStatelist(stateList);
			mrdForm.setCitylist(cityList);
			
		
		mrdForm.setCountryList(PopulateList.countryList());
		mrdForm.setCountry("United Kingdom");
		if(loginInfo.getCountry().equals("India")){
			mrdForm.setCountry("India");
		}
		
		mrdForm.setOccupationList(PopulateList.occupationList());
		ArrayList<Client> diagnosisList=clientDAO.getEmrTreatmentTypeList();
		mrdForm.setDiagnosisList(diagnosisList);
		
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		ArrayList<Master> shelflist =  inventoryProductDAO.getcellList("63");
		mrdForm.setShelflist(shelflist);
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
	}
	public String showAllPatientList() throws SQLException{
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection = null;
		Client client = new Client();
		ArrayList<Client> allPatientList = new ArrayList<Client>();
		try{
			connection = Connection_provider.getconnection();
			
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			allPatientList = clientDAO.getAllPatientSorted(loginInfo.getId());
			
			mrdForm.setAllPatientList(allPatientList);
			
			
			StringBuffer str = new StringBuffer();
			

			
			str.append("<table class='table table-bordered' > ");
			str.append("<thead>");
			str.append("<tr class='bg-info'>");
			str.append("<th>UHID</th> ");
//			str.append("<th>IPD Id</th> ");
			str.append("<th>Patient Name</th> ");
			str.append("<th>Pay By</th> ");
			str.append("<th>Age</th> "); 
			str.append("<th>Mobile No.</th> ");
			str.append("<th>Aadhar No</th> ");
			str.append("<th>Father Name</th> ");
			str.append("<th>Mother Name</th> ");

			str.append("</tr>");
			str.append("</thead>");
			
			str.append("<tbody>");
			for(Client client1:allPatientList){
				String name = client1.getTitle()+" "+client1.getFirstName()+" "+client1.getMiddlename()+" "+client1.getLastName(); 	
				String color = "";
				if(!client1.getCasualtyid().equals("0")){
					color = "#f5a0b4";
				}
			str.append("<tr style='background-color:"+color+"' >");
			
			/*str.append("<td>0000"+client1.getId()+"</td>");*/
			//  22 dec 2017 mrd uid set in search
			if(client1.getAbrivationid()!=null){
				if(client1.getAbrivationid().equals("0") || client1.getAbrivationid().equals("")){
					str.append("<td>"+client1.getId()+"</td>");
				}else{
					str.append("<td>"+client1.getAbrivationid()+"</td>");
				}
			}else{
				str.append("<td>"+client1.getId()+"</td>");
			}
//			str.append("<td>"+DateTimeUtils.isNull(client1.getIpdid())+"</td>");
			
			String firstName= client1.getFirstName();
			
			int payee=0;
			if(client1.getWhopay()!=null){
				
				if(client1.getWhopay().equals("Client")){
					 payee=0;
				} else {
					payee=1;
				}
				
			}
			
		//	String data=client1.getAddress()+"~"+client1.getDob()+"~"+client1.getEmergencyContName()+"~"+client1.getEmergencyContNo()+"~"+client1.getRelation();
			str.append("<td style='cursor: pointer;'; onclick = setPatientForMrd('"+client1.getId()+"','"+client1.getGender()+"','"+client1.getTown()+"')>"+name+"</td>");
			if(client1.getOldclientId()==null){
				client1.setOldclientId("");
			}
			str.append("<input type='hidden' id='firstnameid"+client1.getId()+"' value='"+firstName+"'>");
			/*str.append("<td>"+client1.getOldclientId()+"</td>");*/
			String whopay="";
			if(client1.getWhopay()!=null){
				
				if(client1.getWhopay().equals(Constants.PAY_BY_THIRD_PARTY)){
					whopay = "Third Party";
				}else{
					whopay = "Client";
				}
			
			}
			str.append("<td>"+whopay+"</td>");
			String age= DateTimeUtils.isNull(DateTimeUtils.getAge1(client1.getDob()));
			str.append("<td>"+age+"</td>");
			str.append("<td>"+client1.getMobNo()+"</td>");
			str.append("<td>"+client1.getAdhno()+"</td>");
			str.append("<td>"+DateTimeUtils.isNull(client1.getFathername())+"</td>");
			str.append("<td>"+DateTimeUtils.isNull(client1.getMothername())+"</td>");

			str.append("</tr>");
			}
			str.append("</tbody>");
			str.append("</table>");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			
			response.getWriter().write(""+str.toString()+""); 
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		
		return null;
		
	}
	public String searchParticularPatient() throws SQLException{
 		if(!verifyLogin(request)){
			return "login";
		}
		String searchClient = request.getParameter("searchText");
		Connection connection = null;
		ArrayList<Client> allPatientList = new ArrayList<Client>();
		try{
			connection = Connection_provider.getconnection();
			
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			allPatientList = clientDAO.getClient(searchClient,loginInfo.getId(),"");
			
		//	clientForm.setAllPatientList(allPatientList);
        StringBuffer str = new StringBuffer();
			

			
			str.append("<table class='table table-hover table-condensed table-bordered table-striped ' > ");
			str.append("<thead>");
			str.append("<tr class='bg-info'>");
			str.append("<th>UHID</th> ");
//			str.append("<th>IPD Id</th> ");
			str.append("<th>Patient Name</th> ");
			str.append("<th>Pay By</th> ");
			str.append("<th>Age</th> "); 
			str.append("<th>Mobile No.</th> ");
			str.append("<th>Aadhar No</th> ");
			str.append("<th>Father Name</th> ");
			str.append("<th>Mother Name</th> ");
			str.append("</tr>");
			str.append("</thead>");
			
			str.append("<tbody>");
			for(Client client1:allPatientList){
				String name = client1.getTitle()+"/"+client1.getFirstName()+"/"+client1.getLastName(); 	
				String name1=client1.getTitle()+" "+client1.getFirstName()+" "+client1.getLastName(); 	
				
				String gpname=clientDAO.getGPname(client1.getGpid());
				String temo[] = gpname.split(" ");
				String doctorname = "";
				for(int i=0;i<temo.length;i++){
					doctorname += temo[i] + "/";
				}
				
//				System.out.println(doctorname);
				
			str.append("<tr>");
			
			/*str.append("<td>0000"+client1.getId()+"</td>");*/
			
			//  22 dec 2017 mrd uid set in search
			if(client1.getAbrivationid()!=null){
				if(client1.getAbrivationid().equals("0") || client1.getAbrivationid().equals("")){
					str.append("<td>"+client1.getId()+"</td>");
				}else{
					str.append("<td>"+client1.getAbrivationid()+"</td>");
				}
			}else{
				str.append("<td>"+client1.getId()+"</td>");
			}
//			str.append("<td>"+DateTimeUtils.isNull(client1.getIpdid())+"</td>");
			str.append("<td style='cursor: pointer;'; onclick = setPatientForMrd('"+client1.getId()+"','"+client1.getGender()+"','"+client1.getTown()+"')>"+name1+"</td>");
				
			str.append("<input type='hidden' id='firstnameid"+client1.getId()+"' value='"+name+"'>");
			String whopay="";
			if(client1.getWhopay()!=null){
				
				if(client1.getWhopay().equals(Constants.PAY_BY_THIRD_PARTY)){
					whopay = "Third Party";
				}else{
					whopay = "Client";
				}
			
			}
			str.append("<td>"+whopay+"</td>");
			String age= DateTimeUtils.isNull(DateTimeUtils.getAge1(client1.getDob()));
			str.append("<td>"+age+"</td>");
			str.append("<td>"+client1.getMobNo()+"</td>");
			str.append("<td>"+client1.getAdhno()+"</td>");
			str.append("<td>"+DateTimeUtils.isNull(client1.getFathername())+"</td>");
			str.append("<td>"+DateTimeUtils.isNull(client1.getMothername())+"</td>");

			str.append("</tr>");
			}
			str.append("</tbody>");
			str.append("</table>");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			
			response.getWriter().write(""+str.toString()+""); 
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		return null;
	}

	public String selectBedFromWardid() throws Exception{
 		Connection connection = null;
 		try {
 			String id = request.getParameter("id");
 			connection=Connection_provider.getconnection();
 			MrdDAO mrdDAO = new JDBCMrdDAO(connection);
 			ArrayList<Mrd> arrayList = mrdDAO.getbedlistFromWardId(id);
 			
 			StringBuilder builder = new StringBuilder();
 			builder.append("<label>Select Bed</label>");
 			builder.append("<SELECT id='bedid' name='bedid' class='form-control showToolTip chosen'>");
 			builder.append("<option value='0'>Select Bed</option>");
 			for (Mrd mrd : arrayList) {
				builder.append("<option value='"+mrd.getId()+"'>"+mrd.getBedname()+"</option>");
			}
 			builder.append("</SELECT>");
 			
 			response.setContentType("text/html");
 			response.setHeader("Cache-Control", "no-cache");
 			response.getWriter().write(builder.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		connection.close();
		}
	return null;
	}
	
public String getspeciality() throws Exception {
		
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			String doctorid= request.getParameter("doctorid");
			UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
			UserProfile userProfile=  userProfileDAO.getUserprofileDetails(Integer.parseInt(doctorid));
			
			StringBuffer buffer= new StringBuffer();
			buffer.append("<label for='inputEmail' class='control-label'>Speciality</label>");
			buffer.append("<select class='form-control chosen' name='speciality' id='speciality'>");
			 
			 buffer.append("<option value='0'>Select Speciality</option>");
			 buffer.append("<option selected='selected' value='"+userProfile.getSpecialization()+"'>"+userProfile.getSpecialization()+"</option>");
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

public String setTypeNameClntDropDown() throws Exception{
	
	Connection connection = null;
	try{
		connection = Connection_provider.getconnection();
		 
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		 ArrayList<Client>ajaxTypeNameList = clientDAO.getThirdTypeNameList(id);
		

		 StringBuffer dropDownAjax = new StringBuffer();
		 	dropDownAjax.append("<label>Third Party Name<span class='text-danger'>*</span></label>");
			dropDownAjax.append("<select id='new_typeName' name = 'typeName' class='form-control showToolTip chosen' data-toggle='tooltip'>");
				dropDownAjax.append("<option value = '0'>Select Third Party </option>");
				if(ajaxTypeNameList.size()!=0){
					for(Client client : ajaxTypeNameList){
						dropDownAjax.append("<option value = '"+client.getId()+"'>"+client.getThirdPartyCompanyName()+"</option>");
					}
					
				}
			dropDownAjax.append("</select>");
			dropDownAjax.append("<label  id ='new_typeNameError' class='text-danger'></label>");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			
			response.getWriter().write(""+dropDownAjax.toString()+""); 
		
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
	
	return null;
}

public String savePatientDetails() throws Exception{
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		MrdDAO mrdDAO = new JDBCMrdDAO(connection);
		String patient_id = request.getParameter("patient_id");
		String patient_ipdid = request.getParameter("patient_ipdid");
		String patientFrom = request.getParameter("patientFrom");
		String admission_date = request.getParameter("admission_date");
		String discharge_date = request.getParameter("discharge_date");
		String wardid = request.getParameter("wardid");
		String bedid = request.getParameter("bedid");
		String practitioner_name = request.getParameter("practitioner_name");
		String speciality = request.getParameter("speciality");
		String new_shelf = request.getParameter("new_shelf");
		String new_place = request.getParameter("new_place");
		String new_remark = request.getParameter("new_remark");
		String newmlc = request.getParameter("newmlc");
		String new_whopay = request.getParameter("new_whopay");
		String new_type = request.getParameter("new_type");
		String new_typeName = request.getParameter("new_typeName");
		String new_policyNo = request.getParameter("new_policyNo");
		String new_expiryDate = request.getParameter("new_expiryDate");
		String new_policyExcess = request.getParameter("new_policyExcess");
		String new_mlcno = request.getParameter("new_mlcno");
		
		if(newmlc==null){
			newmlc="0";
		}
		
		if(newmlc.equalsIgnoreCase("true"))
		{
			newmlc="1";
		}else
		{
			newmlc="0";
		}
		
		Mrd mrd = new Mrd();
		mrd.setClientid(patient_id);
		if (patient_ipdid!=null) {
			if (patient_ipdid!="" || !patient_ipdid.equals("")) {
				mrd.setIpdid(Integer.parseInt(patient_ipdid));
			}
		}
		
		String admissiondate = DateTimeUtils.getCommencingDate1(admission_date);
		String dischargedate = DateTimeUtils.getCommencingDate1(discharge_date);
		mrd.setAdmissiondsate(admissiondate);
		mrd.setDischargedate(dischargedate);
		mrd.setWardid(wardid);
		mrd.setBedid(bedid);
		mrd.setShelf_no(new_shelf);
		mrd.setPlace(new_place);
		mrd.setRemark(new_remark);
		mrd.setMlc(newmlc);
		mrd.setWhopay(new_whopay);
		mrd.setPractitionerid(practitioner_name);
		mrd.setPatientFrom(patientFrom);
		mrd.setType(new_type);
		mrd.setPolicyNo(new_policyNo);
		mrd.setExpiryDate(new_expiryDate);
		mrd.setPolicyExcess(new_policyExcess);
		mrd.setSpeciality(speciality);
		mrd.setTypeName(new_typeName);
		mrd.setNew_mlcno(new_mlcno);
		String lastmodified = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
		mrd.setLastmodified(lastmodified);
		int result = mrdDAO.saveMrdDetails(mrd);
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+result+""); 
		
	} catch (Exception e) {
		e.printStackTrace();
	}finally{
		connection.close();
	}
	return null;
}


public String setPatientInforamtion() throws Exception{
	Connection connection;
	try {
		String clientid = request.getParameter("clientid");
		String department = request.getParameter("department");
		connection = Connection_provider.getconnection();
		MrdDAO mrdDAO = new JDBCMrdDAO(connection);
		BedDao bedDao = new JDBCBedDao(connection);
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		IpdLogDAO ipdLogDAO = new JDBCIpdLogDAO(connection);
		UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
		
		
		if (department.equals("IPD")) {
			String ipdid = mrdDAO.getIpdidFromClientId(clientid);
			if(ipdid=="" || ipdid.equals("")){
				ipdid = null;
				
				StringBuffer buffer = new StringBuffer();
				buffer.append("IPD New");
				buffer.append("~");
				buffer.append("NEW");
				
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(""+buffer.toString()+"");
			}else{
				Bed bed = bedDao.getEditIpdData(ipdid);
				StringBuffer buffer = new StringBuffer();
				String admissiondatetime = bed.getAdmissiondate();
				String[] splited = admissiondatetime.split(" ");
				String admissiondate = splited[0];
				String admissiondate1 = DateTimeUtils.getCommencingDate1(admissiondate);
				
				String dischargedatetime = ipdLogDAO.getDischargeDate(Integer.parseInt(ipdid));			
				if(dischargedatetime=="")
					dischargedatetime = null;
				
				if(dischargedatetime==null || dischargedatetime.equals(null)){
				
				}else{
					String[] split1 = dischargedatetime.split(" ");
					String dischargedate = split1[0];	
					String[] temp = dischargedate.split("-");
					dischargedatetime = temp[2]+"-"+temp[1]+"-"+ temp[0];
				}
				boolean flag = false;
				String wardname = ipdDAO.getIpdWardName(bed.getWardid());
				String bedname = ipdDAO.getIpdBedName(bed.getBedid());
				
				UserProfile userProfile=  userProfileDAO.getUserprofileDetails(Integer.parseInt(bed.getPractitionerid()));
				
				ClientDAO clientDAO = new JDBCClientDAO(connection);
				Client client = clientDAO.getClientDetails(clientid);
				String clientname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();
				
				buffer.append("IPD OLD");
				buffer.append("~");
				
				buffer.append(""+ipdid+"");
				buffer.append("~");
				
				buffer.append("<label>Admission Date<span class='text-danger'>*</span></label>");
				buffer.append("<input type='text' id ='admission_date' name='admission_date' value='"+admissiondate1+"' class='form-control showToolTip' placeholder='Enter Admission Date' title='Enter Admission Date'>");
				buffer.append("<label id = 'admission_dateError' class='text-danger'></label>");
				
				buffer.append("~");
				
				buffer.append("<label>Discharge Date<span class='text-danger'>*</span></label>");
				buffer.append("<input type='text' id ='discharge_date' name='discharge_date' value='"+dischargedatetime+"' class='form-control showToolTip' placeholder='Enter Discharge Date' title='Enter Discharge Date'>");
				buffer.append("<label id = 'discharge_dateError' class='text-danger'></label>");
				
				buffer.append("~");
				
				buffer.append("<label>Select Ward</label>");
				buffer.append("<select class='form-control chosen' name='wardid' id='wardid'>");
				//buffer.append("<option value='0'></option>");
				buffer.append("<option selected='selected' value='"+bed.getWardid()+"'>"+wardname+"</option>");
				buffer.append("</select>");
				
				buffer.append("~");
				
				buffer.append("<label>Select Bed</label>");
				buffer.append("<select class='form-control chosen' name='bedid' id='bedid'>");
				//buffer.append("<option value='0'></option>");
				buffer.append("<option selected='selected' value='"+bed.getBedid()+"'>"+bedname+"</option>");
				buffer.append("</select>");
				
				buffer.append("~");
				
				buffer.append("<label>Practinoner Name<span class='text-danger'>*</span></label>");
				buffer.append("<select class='form-control chosen' name='practitioner_name' id='practitioner_name'>");
				//buffer.append("<option value='0'></option>");
				buffer.append("<option selected='selected' value='"+bed.getPractitionerid()+"'>"+userProfile.getFullname()+"</option>");
				buffer.append("</select>");
				buffer.append("<label id = 'practitioner_nameError' class='text-danger'></label>");
				
				buffer.append("~");
				
				buffer.append("<label>Speciality</label>");
				buffer.append("<select class='form-control chosen' name='speciality' id='speciality'>");
				//buffer.append("<option value='0'></option>");
				buffer.append("<option selected='selected' value='"+userProfile.getSpecialization()+"'>"+userProfile.getSpecialization()+"</option>");
				buffer.append("</select>");
				
				buffer.append("~");
				
				if(client.getWhopay()!=null){
					 if(client.getWhopay().equalsIgnoreCase("Client") || client.getWhopay().equalsIgnoreCase("Self")){
						 	buffer.append("<label>Empanelment <span class='text-danger'>*</span></label>");
							buffer.append("<select class='form-control chosen' name='new_whopay' id='new_whopay'>");
							buffer.append("<option selected='selected' value='Client'>Self</option>");
							buffer.append("</select>");
						} else {
							buffer.append("<label>Empanelment <span class='text-danger'>*</span></label>");
							buffer.append("<select class='form-control chosen' name='new_whopay' id='new_whopay'>");
							buffer.append("<option selected='selected' value='Client'>Self</option>");
							buffer.append("</select>");
							flag = true;
						}
				}
				
				buffer.append("~");
				
				if (flag) {
					buffer.append("<label>Type<span class='text-danger'>*</span></label>");
					buffer.append("<select class='form-control chosen' name='new_type' id='new_type'>");
					buffer.append("<option selected='selected' value='"+client.getType()+"'></option>");
					buffer.append("</select>");
				}else{
					buffer.append(" ");
				}
				
				buffer.append("~");
				if (flag) {
					buffer.append("<label>Third Party Name<span class='text-danger'>*</span></label>");
					buffer.append("<select class='form-control chosen' name='new_typeName' id='new_typeName'>");
					buffer.append("<option selected='selected' value='"+client.getTypeName()+"'></option>");
					buffer.append("</select>");
				}else{
					buffer.append(" ");
				}
				
				buffer.append("~");
				if (flag) {
					buffer.append("<label>Policy No.</label>");
					buffer.append("<input type='text' id ='new_policyNo' name='new_policyNo' value='"+client.getPolicyNo()+"' class='form-control showToolTip' placeholder='Enter Admission Date' title='Enter Admission Date'>");
				}else{
					buffer.append(" ");
				}
				
				buffer.append("~");
				if (flag) {
					String expirydate = client.getPolicyNo();
					String expirydate1 = DateTimeUtils.getCommencingDate1(expirydate);
					buffer.append("<label>Policy Expiry Date</label>");
					buffer.append("<input type='text' id ='new_expiryDate' name='new_expiryDate' value='"+expirydate1+"' class='form-control showToolTip' placeholder='Enter Admission Date' title='Enter Admission Date'>");
				}else{
					buffer.append(" ");
				}
				
				buffer.append("~");
				if (flag) {
					buffer.append("<label>Policy Excess</label>");
					buffer.append("<input type='text' id ='new_policyExcess' name='new_policyExcess' value='"+client.getPolicyNo()+"' class='form-control showToolTip' placeholder='Enter Admission Date' title='Enter Admission Date'>");
				}else{
					buffer.append(" ");
				}
				
				response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(""+buffer.toString()+""); 
			}
		} else if (department.equals("OPD")) {
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			NotAvailableSlot notAvailableSlot = clientDAO.getLastAppointmentdetails(clientid);
			if(notAvailableSlot.getId()==0){
				
			}
		} else if(department.equals("Casualty")){
			
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}
public String getPatientForEdit() throws Exception{
	Connection connection=null;
	try {
		connection = Connection_provider.getconnection();
		MrdDAO mrdDAO = new JDBCMrdDAO(connection);
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		String id = request.getParameter("id");
		Mrd mrd = mrdDAO.getPatientForEdit(id);
		String admissiondate = mrd.getAdmissiondsate();
		String wardid = mrd.getWardid();
		String admissiondate1 = DateTimeUtils.getCommencingDate1(mrd.getAdmissiondsate());
		String dischargedatetime = DateTimeUtils.getCommencingDate1(mrd.getDischargedate());
		
		String wardname = ipdDAO.getIpdWardName(mrd.getWardid());
		String bedname = ipdDAO.getIpdBedName(mrd.getBedid());
		
		UserProfile userProfile=  userProfileDAO.getUserprofileDetails(Integer.parseInt(mrd.getPractitionerid()));
		Client client = clientDAO.getPatient(Integer.parseInt(mrd.getClientid()));
		
		String name1=client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
		
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("<label>Patient From <span class='text-danger'>*</span></label>");
		buffer.append("<SELECT id='editpatientFrom' class='form-control showToolTip chosen'>");
		buffer.append("<option value='"+mrd.getPatientFrom()+"'>"+mrd.getPatientFrom()+"</option>");
		buffer.append("</SELECT>");
		
		buffer.append("~");
		buffer.append("<label>Admission Date<span class='text-danger'>*</span></label>");
		buffer.append("<input type='text' id ='editadmission_date' value='"+admissiondate1+"' class='form-control showToolTip' placeholder='Enter Admission Date' title='Enter Admission Date' disabled='disabled'>");
		buffer.append("<label id = 'editadmission_dateError' class='text-danger'></label>");
		
		buffer.append("~");
		buffer.append("<label>Discharge Date<span class='text-danger'>*</span></label>");
		buffer.append("<input type='text' id ='editdischarge_date' value='"+dischargedatetime+"' class='form-control showToolTip' placeholder='Enter Discharge Date' title='Enter Discharge Date' disabled='disabled'>");
		buffer.append("<label id = 'editdischarge_dateError' class='text-danger'></label>");
		
		buffer.append("~");
		buffer.append("<label>Select Ward</label>");
		buffer.append("<select class='form-control chosen' id='editwardid'>");
		//buffer.append("<option value='0'></option>");
		buffer.append("<option selected='selected' value='"+mrd.getWardid()+"'>"+wardname+"</option>");
		buffer.append("</select>");
		
		buffer.append("~");
		buffer.append("<label>Select Bed</label>");
		buffer.append("<select class='form-control chosen' id='editbedid'>");
		//buffer.append("<option value='0'></option>");
		buffer.append("<option selected='selected' value='"+mrd.getBedid()+"'>"+bedname+"</option>");
		buffer.append("</select>");
		
		buffer.append("~");
		buffer.append("<label>Practinoner Name<span class='text-danger'>*</span></label>");
		buffer.append("<select class='form-control chosen' id='editpractitioner_name'>");
		//buffer.append("<option value='0'></option>");
		buffer.append("<option selected='selected' value='"+mrd.getPractitionerid()+"'>"+userProfile.getFullname()+"</option>");
		buffer.append("</select>");
		buffer.append("<label id = 'editpractitioner_nameError' class='text-danger'></label>");
		
		buffer.append("~");
		buffer.append("<label>Speciality</label>");
		buffer.append("<select class='form-control chosen' id='editspecialitydiv'>");
		//buffer.append("<option value='0'></option>");
		buffer.append("<option selected='selected' value='"+userProfile.getSpecialization()+"'>"+userProfile.getSpecialization()+"</option>");
		buffer.append("</select>");
		
		buffer.append("~");
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		ArrayList<Master> shelflist =  inventoryProductDAO.getcellList("63");
		
		buffer.append("<label>Shelf No</label>");
		buffer.append("<select class='form-control chosen' id='editnew_shelf'>");
		buffer.append("<option value='0'>Select Shelf</option>");
		
		if(mrd.getShelf_no()!=null){
			if(mrd.getShelf_no().equals("")){
				mrd.setShelf_no("0");
			}
		}else{
			mrd.setShelf_no("0");
		}
		
		for (Master master : shelflist) {
			try {
				if(master.getId()==Integer.parseInt(mrd.getShelf_no())){
					buffer.append("<option selected='selected' value='"+master.getId()+"'>"+master.getName()+"</option>");
				}else{
					buffer.append("<option  value='"+master.getId()+"'>"+master.getName()+"</option>");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		buffer.append("</select>");
		//buffer.append("<input type='text' id='editnew_shelf' value='"+mrd.getShelf_no()+"' class='form-control showToolTip'>");
		
		buffer.append("~");
		buffer.append("<label>Place</label>");
		buffer.append("<input type='text' id='editnew_place' value='"+mrd.getPlace()+"' class='form-control showToolTip'>");
		
		buffer.append("~");
		buffer.append("<label>Remark</label>");
		buffer.append("<input type='text' id='edit_new_remark' value='"+mrd.getRemark()+"' class='form-control showToolTip'>");
		
		buffer.append("~");
		buffer.append("<label>MLC</label><br>");
		if (mrd.getMlc()=="1" || mrd.getMlc().equals("1")) {
			buffer.append("<input type='checkbox' name='editnew_mlc' id='editnew_mlc' checked='checked' >");	
		}else{
			buffer.append("<input type='checkbox' name='editnew_mlc' id='editnew_mlc'  >");
		}
		
		buffer.append("~");
		buffer.append(""+name1+"");
		buffer.append("~");
		
		buffer.append(""+client.getTown()+"");
		buffer.append("~");
		
		buffer.append(""+client.getGender()+"/"+DateTimeUtils.getAge(client.getDob())+"");
		buffer.append("~");
		
		buffer.append(""+mrd.getId()+"");
		buffer.append("~");
		
		buffer.append(""+mrd.getIpdid()+"");
		buffer.append("~");
		
		buffer.append(""+mrd.getClientid()+"");
		buffer.append("~");
		
		buffer.append(""+client.getMobNo()+"");
		buffer.append("~");
		
		buffer.append(""+client.getAddress()+"");
		buffer.append("~");
		
		String name = mrd.getNew_mlcno();
		buffer.append("<label>MLC No.</label>");
		buffer.append("<input type='text' id='editnew_mlcno' value='"+mrd.getNew_mlcno()+"' class='form-control showToolTip'>");
		
		buffer.append("~");
		//  22 dec 2017 mrd set uid
		if(client.getAbrivationid()!=null){
			if(client.getAbrivationid().equals("0") || client.getAbrivationid().equals("")){
				buffer.append(""+client.getId()+"");
			}else{
				buffer.append(""+client.getAbrivationid()+"");
			}
		}else{
			buffer.append(""+client.getId()+"");
		}
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+buffer.toString()+""); 
	} catch (Exception e) {
		e.printStackTrace();
	}finally{
		connection.close();	
	}
	return null;
}
public String updateInformation() throws Exception{
	Connection connection = null;
	int result = 0;
	try{
	String shelf_no = request.getParameter("shelf_no");
	String place = request.getParameter("place");
	String remark = request.getParameter("remark");
	String mlc = request.getParameter("mlc");
	String id = request.getParameter("id");
	String mlcno = request.getParameter("mlcno");
	
	
	Mrd mrd = new Mrd();
	mrd.setId(Integer.parseInt(id));
	mrd.setShelf_no(shelf_no);
	mrd.setPlace(place);
	mrd.setRemark(remark);
	mrd.setNew_mlcno(mlcno);
	if(mlc==null){
		mlc="0";
	}
	if(mlc.equalsIgnoreCase("true"))
	{
		mlc="1";
	}else
	{
		mlc="0";
	}
	
	mrd.setMlc(mlc);
	
	connection = Connection_provider.getconnection();
	MrdDAO mrdDAO = new JDBCMrdDAO(connection);
	
	result = mrdDAO.updateMrdInformation(mrd);
	
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		connection.close();
	}
	return null;
}


public String getallpatientinfo() throws Exception{
	Connection connection=null;
	try {
		connection = Connection_provider.getconnection();
		MrdDAO mrdDAO = new JDBCMrdDAO(connection);
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		BedDao bedDao = new JDBCBedDao(connection);
		NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
		UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		String id = request.getParameter("id");
		
		//UserProfile userProfile=  userProfileDAO.getUserprofileDetails(Integer.parseInt(mrd.getPractitionerid()));
		Client client = clientDAO.getPatient(Integer.parseInt(id));
		String name1=client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
		
		int ipdid = ipdDAO.getLastIpdId(String.valueOf(client.getId()));
		Bed bed = new Bed();
		UserProfile userProfile = new UserProfile();
		String admissiondate ="";
		String speciality="0";
		String dischargedate = "";
		if(ipdid>0){
			bed = bedDao.getEditIpdData(String.valueOf(ipdid));
			userProfile= userProfileDAO.getUserprofileDetails(Integer.parseInt(bed.getPractitionerid()));
			String[] tempdate = bed.getAdmissiondate().split(" ");
			admissiondate = DateTimeUtils.getCommencingDate1(tempdate[0]);
			speciality = userProfile.getSpecialization();
			Bed bed1 = ipdDAO.getDischargeData(bed.getTreatmentepisodeid());
			dischargedate = bed1.getDischargedate();
			if(dischargedate==null){
				dischargedate ="";
			}else{
				String[] tempdate1 = bed1.getDischargedate().split(" ");
				dischargedate = DateTimeUtils.getCommencingDate1(tempdate1[0]);
			}
			
		}
		
		
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(""+id+"");
		buffer.append("~");
		
		buffer.append(""+ipdid+"");
		buffer.append("~");
		
		buffer.append(""+name1+"");
		buffer.append("~");
		
		
		
		if(ipdid>0){
			buffer.append("IPD");
		}else{
			buffer.append("OPD");
		}
		buffer.append("~");
		
		buffer.append(""+client.getGender()+"/"+DateTimeUtils.getAge1(client.getDob())+"");
		buffer.append("~");
		
		buffer.append(""+client.getMobNo()+"");
		buffer.append("~");
		
		buffer.append(""+client.getTown()+"");
		buffer.append("~");
		
		buffer.append(""+client.getAddress()+"");
		buffer.append("~");
		
		buffer.append(""+admissiondate+"");
		buffer.append("~");
		
		buffer.append(""+dischargedate+"");
		buffer.append("~");
		
		ArrayList<Bed> wardlist = bedDao.getAllWardList("0");
		
		buffer.append("<label>Select Ward</label>");
		buffer.append("<select class='form-control chosen' onchange='selectBedFromWardid(this.value)' name='wardid' id='wardid'>");
		buffer.append("<option value='0'>Select</option>");
		if(bed.getWardid()==null){
			bed.setWardid("0");
		}else{
			if(bed.getWardid().equals("")){
				bed.setWardid("0");
			}
		}
		for (Bed bed2 : wardlist) {
			if(Integer.parseInt(bed.getWardid()) == bed2.getId()){
				buffer.append("<option selected='selected' value='"+bed2.getId()+"'>"+bed2.getWardname()+"</option>");
			}else{
				buffer.append("<option value='"+bed2.getId()+"'>"+bed2.getWardname()+"</option>");
			}
		}
		buffer.append("</select>");
		buffer.append("~");
		
		
		ArrayList<Mrd> arrayList = mrdDAO.getbedlistFromWardId(null);
		buffer.append("<label>Select Bed</label>");
		buffer.append("<SELECT id='bedid' name='bedid' class='form-control showToolTip chosen'>");
		buffer.append("<option value='0'>Select Bed</option>");
		if(bed.getBedid()==null){
			bed.setBedid("0");
		}else{
			if(bed.getBedid().equals("")){
				bed.setBedid("0");
			}
		}
		for (Mrd mrd : arrayList) {
			if(mrd.getId()==Integer.parseInt(bed.getBedid())){
				buffer.append("<option selected='selected' value='"+mrd.getId()+"'>"+mrd.getBedname()+"</option>");
			}else{
				buffer.append("<option value='"+mrd.getId()+"'>"+mrd.getBedname()+"</option>");
			}
		}
		buffer.append("</SELECT>");
		
		buffer.append("~");
		
		ArrayList<DiaryManagement> userList = notAvailableSlotDAO.getUserList(loginInfo.getId());
		
		buffer.append("<label>Practinoner Name<span class='text-danger'>*</span></label>");
		buffer.append("<select class='form-control chosen' onchange='getspeciality(this.value)' name='practitioner_name' id='practitioner_name'>");
		buffer.append("<option value='0'>Select</option>");
		if(bed.getPractitionerid()==null){
			bed.setPractitionerid("0");
		}else{
			if(bed.getPractitionerid().equals("")){
				bed.setPractitionerid("0");
			}
		}
		for (DiaryManagement diaryManagement : userList) {
			if(diaryManagement.getId()==Integer.parseInt(bed.getPractitionerid())){
				buffer.append("<option selected='selected' value='"+diaryManagement.getId()+"'>"+diaryManagement.getDiaryUser()+"</option>");
			}else{
				buffer.append("<option value='"+diaryManagement.getId()+"'>"+diaryManagement.getDiaryUser()+"</option>");
			}
			
		}
			
		buffer.append("</select>");
		buffer.append("<label id = 'practitioner_nameError' class='text-danger'></label>");
		
		buffer.append("~");
		
		buffer.append("<label>Speciality</label>");
		buffer.append("<select class='form-control chosen' name='speciality' id='speciality'>");
		buffer.append("<option value='0'>Select</option>");
		buffer.append("<option selected='selected' value='"+speciality+"'>"+speciality+"</option>");
		buffer.append("</select>");
		
		buffer.append("~");
		
		if(client.getWhopay()!=null){
			 if(client.getWhopay().equalsIgnoreCase("Client") || client.getWhopay().equalsIgnoreCase("Self")){
				 	buffer.append("<label>Empanelment <span class='text-danger'>*</span></label>");
					buffer.append("<select class='form-control chosen' name='new_whopay' id='new_whopay'>");
					buffer.append("<option selected='selected' value='Client'>Self</option>");
					buffer.append("<option value='Third Party'>Third Party</option>");
					buffer.append("</select>");
				} else {
					buffer.append("<label>Empanelment <span class='text-danger'>*</span></label>");
					buffer.append("<select class='form-control chosen' name='new_whopay' id='new_whopay'>");
					buffer.append("<option value='Client'>Self</option>");
					buffer.append("<option selected='selected' value='Third Party'>Third Party</option>");
					buffer.append("</select>");
				}
		}
		
		buffer.append("~");
		//  22 dec 2017 mrd set uid
		if(client.getAbrivationid()!=null){
			if(client.getAbrivationid().equals("0") || client.getAbrivationid().equals("")){
				buffer.append(""+client.getId()+"");
			}else{
				buffer.append(""+client.getAbrivationid()+"");
			}
		}else{
			buffer.append(""+client.getId()+"");
		}
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+buffer.toString()+""); 
	} catch (Exception e) {
		e.printStackTrace();
	}finally{
		connection.close();	
	}
	return null;
}
public String getPatientForShift() throws Exception{
	Connection connection=null;
	try {
		connection = Connection_provider.getconnection();
		MrdDAO mrdDAO = new JDBCMrdDAO(connection);
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		String mrdid = request.getParameter("mrdid");
		/*String shiftstatus = request.getParameter("mrdstatus");*/
		Mrd mrd = mrdDAO.getPatientForEdit(mrdid);
		StringBuffer str = new StringBuffer();
	
		ArrayList<Mrd>mrdshiftlist = mrdDAO.getallshiftmrdlist(mrdid);
		
		
		Client client = clientDAO.getPatient(Integer.parseInt(mrd.getClientid()));
		
		String name1=client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
		
		StringBuffer buffer = new StringBuffer();
		
		
		if(client.getAbrivationid()!=null){
			if(client.getAbrivationid().equals("0") || client.getAbrivationid().equals("")){
				buffer.append(""+client.getId()+"");
			}else{
				buffer.append(""+client.getAbrivationid()+"");
			}
		}else{
			buffer.append(""+client.getId()+"");
		}
		buffer.append("~");
		
		buffer.append(""+name1+"");
		buffer.append("~");
		
	
		
		buffer.append(""+client.getGender()+"/"+DateTimeUtils.getAge(client.getDob())+"");
		buffer.append("~");
		
		
	
		
		
		
		buffer.append(""+client.getTown()+"");
		buffer.append("~");
		/*buffer.append("~");
		buffer.append(""+mrd.getMrdstatus()+"");
	
	buffer.append("~");*/
		int i =0;
		for(Mrd mrd2 : mrdshiftlist){
			String shiftmrdgivendate = mrd2.getGivendate();
			String shiftmrdreceiveddate = mrd2.getReceiveddate();
			String datetime;
			if(shiftmrdgivendate!=null){
				if(!shiftmrdgivendate.equals("")){
					 String[] temp = shiftmrdgivendate.split("-");
						String newdate = temp[2]+"-"+temp[1]+"-"+temp[0];
						shiftmrdgivendate = newdate;
				}
			}
			if(shiftmrdreceiveddate!=null){
				if(!shiftmrdreceiveddate.equals("")){
					 String[] temp = shiftmrdreceiveddate.split("-");
						String newdate = temp[2]+"-"+temp[1]+"-"+temp[0];
						shiftmrdreceiveddate = newdate;
				}
			}
			buffer.append("<tr id='shifttrid"+i+"'>");
			
			buffer.append("<td>"+mrd2.getGivento()+"</td/>");
			buffer.append("<td>"+shiftmrdgivendate+"</td/>");
			buffer.append("<td>"+mrd2.getReceivedfrom()+"</td/>");
			buffer.append("<td>"+shiftmrdreceiveddate+"</td/>");
			buffer.append("<td>"+mrd2.getRemark()+"</td>");
			if(mrd2.getMrdstatus().equals("0")){
				buffer.append("<td><input type='button' value ='Edit' class='btn btn-primary' onclick='editshiftpatientMrd("+i+","+mrd2.getId()+")'></td/>");
			}else{
				buffer.append("<td></td/>");
			}
			
			buffer.append("</tr>");
			i++;
		/*	str.append("<td>"+mrd2.get+"</td/>");
		 * 
*/		}
		
		//  22 dec 2017 mrd set uid
	
		buffer.append("~");
		buffer.append(""+mrd.getClientid()+"");
		buffer.append("~");
		
		buffer.append(""+mrd.getIpdid()+"");
		buffer.append("~");
		
		buffer.append(""+mrdid+"");
buffer.append("~");
		
		buffer.append(""+mrd.getMrdstatus()+"");
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+buffer.toString()+""); 
	} catch (Exception e) {
		e.printStackTrace();
	}finally{
		connection.close();	
	}
	return null;
}
public String addnewrow() throws Exception{
	 Connection connection = null;
	 try {
	  connection = Connection_provider.getconnection();
	  MrdDAO mrdDAO = new JDBCMrdDAO(connection);
	  String mrdid = request.getParameter("mrdid");
	  String shiftstatus = request.getParameter("shiftstatus");
	  ArrayList<Mrd>mrdshiftlist = mrdDAO.getallshiftmrdlist(mrdid);
	  
	  StringBuffer buffer = new StringBuffer();
	  buffer.append("<tr>");
	  
	  
	 
	  for (Mrd mrd :mrdshiftlist) {

	   String shiftmrdgivendate = mrd.getGivendate();
	   String shiftmrdreceiveddate = mrd.getReceiveddate();
	   if(shiftmrdgivendate!=null){
	    if(!shiftmrdgivendate.equals("")){
	      String[] temp = shiftmrdgivendate.split("-");
	      String newdate = temp[2]+"-"+temp[1]+"-"+temp[0];
	      shiftmrdgivendate = newdate;
	    }
	   }
	   if(shiftmrdreceiveddate!=null){
	    if(!shiftmrdreceiveddate.equals("")){
	      String[] temp = shiftmrdreceiveddate.split("-");
	      String newdate = temp[2]+"-"+temp[1]+"-"+temp[0];
	      shiftmrdreceiveddate = newdate;
	    }
	   }
	   
	   buffer.append("<tr>");
	   /*buffer.append("<td>"+mrd.getShiftstatus()+"</td/>");*/
	   buffer.append("<td>"+mrd.getGivento()+"</td/>");
	   buffer.append("<td>"+shiftmrdgivendate+"</td/>");
	   buffer.append("<td>"+mrd.getReceivedfrom()+"</td/>");
	   buffer.append("<td>"+shiftmrdreceiveddate+"</td/>");
	   
	   buffer.append("<td>"+mrd.getRemark()+"</td/>");
	   buffer.append("<td></td/>");
	   buffer.append("</tr>");
	  }
	 
	  buffer.append("<tr>");
	  
	  buffer.append("<td ><input class='inputborderi' type='text' placeholder = 'Enter name' id='shiftmrdgiventodetails' name='shiftmrdgiventodetails'></td/>");
	  buffer.append("<td><input class='inputborderi' type='text' placeholder = 'Select Date' id='shiftmrdgivendatedetails' name='shiftmrdgivendatedetails'></td/>");
	  buffer.append("<td><input class='inputborderi' type='text'  placeholder = 'Enter name' id='shiftmrdreceivedfromdetails' name='shiftmrdreceivedfromdetails'></td/>");
	  buffer.append("<td><input class='inputborderi' type='text'  placeholder = 'Select Date' id='shiftmrdreceiveddatedetails' name='shiftmrdreceiveddatedetails'></td/>");
	  buffer.append("<td><input class='inputborderi' type='text' placeholder = 'Enter Remark' id='shiftmrdremark' name='shiftmrdremark'></td/>");
	  buffer.append("<td><input class='inputborderi savesmbtn' type='button' value ='Save' onclick='saveshiftToMrd()'></td/>");
	  buffer.append("</tr>");
	  
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




public String saveshiftPatientDetails() throws Exception{
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		MrdDAO mrdDAO = new JDBCMrdDAO(connection);
		String shiftpatientid = request.getParameter("shiftpatient_id");
		String shiftpatientipdid = request.getParameter("shiftpatient_ipdid");
		String shiftpatientmrdid = request.getParameter("shiftpatienmrd_id");
		String shiftmrdgivento = request.getParameter("shiftmrdgiventodetails");
		String shiftmrdgivendate = request.getParameter("shiftmrdgivendatedetails");
		String shiftmrdreceivedfrom = request.getParameter("shiftmrdreceivedfromdetails");
		String shiftmrdreceiveddate = request.getParameter("shiftmrdreceiveddatedetails");
		String shiftmrdremark = request.getParameter("shiftmrdremark");
	String datetime;
	if(shiftmrdgivendate!=null){
		if(!shiftmrdgivendate.equals("")){
			 String[] temp = shiftmrdgivendate.split("-");
				String newdate = temp[2]+"-"+temp[1]+"-"+temp[0];
				shiftmrdgivendate = newdate;
		}
	}
	if(shiftmrdreceiveddate!=null){
		if(!shiftmrdreceiveddate.equals("")){
			 String[] temp = shiftmrdreceiveddate.split("-");
				String newdate = temp[2]+"-"+temp[1]+"-"+temp[0];
				shiftmrdreceiveddate = newdate;
		}
	}
		
		Mrd mrd = new Mrd();
		mrd.setShiftpatientid(shiftpatientid);
		mrd.setShiftpatientipdid(shiftpatientipdid);
		mrd.setShiftpatientmrdid(shiftpatientmrdid);
		mrd.setShiftmrdgivento(shiftmrdgivento);
		mrd.setShiftmrdgivendate(shiftmrdgivendate);
	   mrd.setShiftmrdreceivedfrom(shiftmrdreceivedfrom);
		mrd.setShiftmrdreceiveddate(shiftmrdreceiveddate);
		mrd.setRemark(shiftmrdremark);
 		int result = mrdDAO.saveshiftMrdDetails(mrd);
		int res = mrdDAO.updateShiftStatus(shiftpatientmrdid);
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+result+""); 
		
	} catch (Exception e) {
		e.printStackTrace();
	}finally{
		connection.close();
	}
	return null;
}

public String editshiftpatient() throws Exception{
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		MrdDAO mrdDAO = new JDBCMrdDAO(connection);
		String id = request.getParameter("id");
		String count = request.getParameter("count");
		/*Mrd mrd = mrdDAO.getPatientForEdit(mrdid);*/
		StringBuffer buffer = new StringBuffer();
		Mrd mrd = mrdDAO.editshiftMrdDetails(id);

		
		String shiftmrdgivendate = mrd.getShiftmrdgivendate();
		String shiftmrdreceiveddate = mrd.getShiftmrdreceiveddate();
		
		String datetime;
		if(shiftmrdgivendate!=null){
			if(!shiftmrdgivendate.equals("")){
				 String[] temp = shiftmrdgivendate.split("-");
					String newdate = temp[2]+"-"+temp[1]+"-"+temp[0];
					shiftmrdgivendate = newdate;
			}
		}
		if(shiftmrdreceiveddate!=null){
			if(!shiftmrdreceiveddate.equals("")){
				 String[] temp = shiftmrdreceiveddate.split("-");
					String newdate = temp[2]+"-"+temp[1]+"-"+temp[0];
					shiftmrdreceiveddate = newdate;
			}
		}
		buffer.append("<td><input type='edit' value='"+mrd.getShiftmrdgivento()+"' id='shiftmrdgiventodetails"+count+"' name='shiftmrdgiventodetails'></td/>");
		buffer.append("<td><input type='edit' value='"+shiftmrdgivendate+"' id='shiftmrdgivendatedetails"+count+"' name='shiftmrdgivendatedetails'></td/>");
		buffer.append("<td><input type='edit' value='"+mrd.getShiftmrdreceivedfrom()+"' id='shiftmrdreceivedfromdetails"+count+"' name='shiftmrdreceivedfromdetails'></td/>");
		buffer.append("<td><input type='edit' value='"+shiftmrdreceiveddate+"' id='shiftmrdreceiveddatedetails"+count+"' name='shiftmrdreceiveddatedetails'></td/>");
		buffer.append("<td><input type='edit' value='"+mrd.getRemark()+"' id='shiftmrdremark"+count+"' name='shiftmrdremark'></td/>");
		buffer.append("<td><input type='button' value ='update' onclick='updateshiftToMrd("+count+","+id+")'></td/>");
		
		
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

public String updateshiftpatientdetails() throws Exception{
	int result = 0;
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		MrdDAO mrdDAO = new JDBCMrdDAO(connection);
		Mrd mrd = new Mrd();
		
		
		String id = request.getParameter("id");
		String shiftmrdid = request.getParameter("shiftpatienmrd_id");
		String shiftmrdgivento = request.getParameter("shiftmrdgiventodetails");
		String shiftmrdgivendate = request.getParameter("shiftmrdgivendatedetails");
		String shiftmrdreceivedfrom = request.getParameter("shiftmrdreceivedfromdetails");
		String shiftmrdreceiveddate = request.getParameter("shiftmrdreceiveddatedetails");
		String shiftmrdremark = request.getParameter("shiftmrdremark");
		String datetime;
		if(shiftmrdgivendate!=null){
			if(!shiftmrdgivendate.equals("")){
				 String[] temp = shiftmrdgivendate.split("-");
					String newdate = temp[2]+"-"+temp[1]+"-"+temp[0];
					shiftmrdgivendate = newdate;
			}
		}
		if(shiftmrdreceiveddate!=null){
			if(!shiftmrdreceiveddate.equals("")){
				 String[] temp = shiftmrdreceiveddate.split("-");
					String newdate = temp[2]+"-"+temp[1]+"-"+temp[0];
					shiftmrdreceiveddate = newdate;
			}
		}
		
       mrd.setId(Integer.parseInt(id));
		mrd.setShiftmrdgivento(shiftmrdgivento);
		mrd.setShiftmrdgivendate(shiftmrdgivendate);
	   mrd.setShiftmrdreceivedfrom(shiftmrdreceivedfrom);
		mrd.setShiftmrdreceiveddate(shiftmrdreceiveddate);
		mrd.setRemark(shiftmrdremark);
		result = mrdDAO.updateshiftMrdDetails(mrd);
		int res = mrdDAO.saveeditShiftMrdDetails(shiftmrdid);
	} catch (Exception e) {
		e.printStackTrace();
	}finally{
		
		connection.close();
	}
	return null;
	
}

public String deletedata() throws Exception {
	 Connection connection = null;
	 
	 try {
	  connection = Connection_provider.getconnection();
	  MrdDAO mrdDAO = new JDBCMrdDAO(connection);
	  String id= request.getParameter("id");
	  String delete_reason= request.getParameter("delete_reason");
	  //int del=inventoryProductDAO.deleteIndentParent(parentid);
	  int del=mrdDAO.cancelMrd(id);
	 /* String userid = loginInfo.getUserId();
	  
	  String loc= (String) session.getAttribute("location");
	   if(loc==null){
	    loc="0";
	   }
	  */
	  /*int res = mrdDAO.saveUpDeleteIndent(id,delete_reason,userid,loc);*/
	  
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
