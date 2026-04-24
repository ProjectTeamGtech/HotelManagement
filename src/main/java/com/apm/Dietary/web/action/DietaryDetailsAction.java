package com.apm.Dietary.web.action;

import java.nio.Buffer;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.Dietary.eu.bi.DietaryDAO;
import com.apm.Dietary.eu.bi.DietaryDetailsDAO;
import com.apm.Dietary.eu.blogic.jdbc.JDBCDietaryDAO;
import com.apm.Dietary.eu.blogic.jdbc.JDBCDietaryDetailsDAO;
import com.apm.Dietary.eu.entity.Dietary;
import com.apm.Dietary.eu.entity.DietaryDetails;
import com.apm.Dietary.web.form.DietaryDetailsForm;
import com.apm.Ipd.eu.bi.BedDao;
import com.apm.Ipd.eu.bi.IpdDAO;
import com.apm.Ipd.eu.blogic.jdbc.JDBCBedDao;
import com.apm.Ipd.eu.blogic.jdbc.JDBCIpdDAO;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.entity.Master;
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
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class DietaryDetailsAction extends BaseAction implements
	ModelDriven<DietaryDetailsForm>, Preparable{
	String mastername;
	DietaryDetailsForm dietaryDetailsForm = new DietaryDetailsForm();

	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpSession session = request.getSession();
	
	LoginInfo loginInfo=LoginHelper.getLoginInfo(request);


	public DietaryDetailsForm getModel() {
		return dietaryDetailsForm;
	}
	
	public String dietary() throws Exception {
		if (!verifyLogin(request)) {
			return "login";
		}

		Connection connection=null;
		try {
			connection = Connection_provider.getconnection();
			
			String searchtext = dietaryDetailsForm.getSearchtext();
			String fromdate = dietaryDetailsForm.getFromdate();
			String todate = dietaryDetailsForm.getTodate();
			String wardnameid = dietaryDetailsForm.getWardnameid();
			
			if(searchtext!=null){
				 
				if(searchtext.equals("")){
					searchtext=null;
				}
			}
		
			if(wardnameid!=null){
				if(wardnameid.equals("0")){
						wardnameid=null;		
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
			
			DietaryDetailsDAO dietaryDetailsDAO = new JDBCDietaryDetailsDAO(connection);
			
			/*IpdDAO ipdDAO = new JDBCIpdDAO(connection);		
			//ArrayList<Bed> bedList= ipdDAO.getAllBedList("0", loginInfo.getClinicid());
			ArrayList<Bed> bedList = ipdDAO.getAllBedListForDietary("0", loginInfo.getClinicid());
			dietaryDetailsForm.setBedlist(bedList);
			
			BedDao bedDao = new JDBCBedDao(connection);
			ArrayList<Bed> wardlist = bedDao.getAllWardList("0");
			dietaryDetailsForm.setWardlist(wardlist);*/
			
			
			ArrayList<DietaryDetails> dietservelist = dietaryDetailsDAO.getdietserveplan(fromdate,todate,searchtext,wardnameid);
			dietaryDetailsForm.setDietservelist(dietservelist);
			

			IpdDAO ipdDAO = new JDBCIpdDAO(connection);		
			
			ArrayList<Bed> bedList = ipdDAO.getAllBedListForDietary("0", loginInfo.getClinicid(),fromdate);
			dietaryDetailsForm.setBedlist(bedList);
			
			fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			todate = DateTimeUtils.getCommencingDate1(todate);
			dietaryDetailsForm.setFromdate(fromdate);
			dietaryDetailsForm.setTodate(todate);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		return "dietary";
	}
	
	
	public String add(){
		return "addcall";
	}
	public String edit() throws Exception{
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			DietaryDetailsDAO dietaryDetailsDAO = new JDBCDietaryDetailsDAO(connection);
			//int id = Integer.parseInt(request.getParameter("id"));
			DietaryDetails dietaryDetails = new DietaryDetails();
			dietaryDetails.setId(dietaryDetailsForm.getId());
			DietaryDetails dietary2 = dietaryDetailsDAO.getinfoDetails(dietaryDetailsForm.getId());
			dietaryDetailsForm.setId(dietaryDetails.getId());
			dietaryDetailsForm.setCategoryid(dietary2.getCategoryid());
			dietaryDetailsForm.setName(dietary2.getName());
			dietaryDetailsForm.setEnergy(dietary2.getEnergy());
			dietaryDetailsForm.setProtein(dietary2.getProtein());
			dietaryDetailsForm.setSodium(""+dietary2.getSodium());
			dietaryDetailsForm.setPotassium(""+dietary2.getPotassium());
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return "editcall";
	}

	public String execute() throws Exception {
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			DietaryDetailsDAO dietaryDetailsDAO= new JDBCDietaryDetailsDAO(connection);
			ArrayList<DietaryDetails> listdietarydetails = dietaryDetailsDAO.viewDietaryDetails();
			dietaryDetailsForm.setListdietarydetails(listdietarydetails);
			mastername=request.getParameter("selectedid");
			
			if(mastername!=null){
				
				 session.setAttribute("mastername", mastername);
				
			} else {
				
				mastername=(String)session.getAttribute("mastername");
			}
			
			dietaryDetailsForm.setMastername(mastername);
			//dietaryDetailsForm.getDietarycategoryList();	
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		return SUCCESS;
	}

	public String save() throws Exception{
		Connection connection=null;
		try {
			connection = Connection_provider.getconnection();
			DietaryDetailsDAO dietaryDetailsDAO = new JDBCDietaryDetailsDAO(connection);
			DietaryDetails dietaryDetails = new DietaryDetails();
			dietaryDetails.setCategoryid(dietaryDetailsForm.getCategoryid());
			String str = dietaryDetailsForm.getCategoryid();
			dietaryDetails.setName(dietaryDetailsForm.getName());
			dietaryDetails.setEnergy(dietaryDetailsForm.getEnergy());
			dietaryDetails.setProtein(dietaryDetailsForm.getProtein());
			int result =dietaryDetailsDAO.addDietarydetails(dietaryDetails);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		connection.close();
		}
		return "save";
	}
	
	public String update() throws Exception{
		Connection connection=null;
		try {
			connection = Connection_provider.getconnection();
			DietaryDetailsDAO dietaryDetailsDAO = new JDBCDietaryDetailsDAO(connection);
			DietaryDetails dietaryDetails = new DietaryDetails();
			dietaryDetails.setId(dietaryDetailsForm.getId());
			dietaryDetails.setCategoryid(dietaryDetailsForm.getCategoryid());
			dietaryDetails.setName(dietaryDetailsForm.getName());
			dietaryDetails.setEnergy(dietaryDetailsForm.getEnergy());
			dietaryDetails.setProtein(dietaryDetailsForm.getProtein());
			dietaryDetails.setSodium(DateTimeUtils.convertToDouble(dietaryDetailsForm.getSodium()));
			dietaryDetails.setPotassium(DateTimeUtils.convertToDouble(dietaryDetailsForm.getPotassium()));
			int result = dietaryDetailsDAO.updateDietary(dietaryDetails);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		connection.close();
		}
		return "update";
	}
	
	public String del() throws Exception{
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			DietaryDetailsDAO dietaryDetailsDAO = new JDBCDietaryDetailsDAO(connection);
			//int id = Integer.parseInt(request.getParameter("id"));
			DietaryDetails dietaryDetails = new DietaryDetails();
			dietaryDetails.setId(dietaryDetailsForm.getId());
			int i = dietaryDetails.getId();
//			System.out.println(dietaryDetails.getId());
			int result = dietaryDetailsDAO.deleteInfo(dietaryDetails);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return "delete";
	}
	

	public String getcategorydetails()throws Exception {
		Connection connection = null;
		try {
			String id = request.getParameter("name");
			connection = Connection_provider.getconnection();
			DietaryDetailsDAO dietaryDetailsDAO=new JDBCDietaryDetailsDAO(connection);
			ArrayList<DietaryDetails> list = dietaryDetailsDAO.getcategorydetailslist(id);
			//String data=student.getRollno()+"~"+student.getName()+"~"+student.getAge()+"~"+student.getAddress();
			
			StringBuffer buffer=new StringBuffer();
			buffer.append("<select class='form-control showToolTip chosen' name='subcategory' id='subcategory' onchange='selectCalories(this.value)''>");
			buffer.append("<option value='0'>Select Diet</option>");
			
			for(DietaryDetails details:list){
				 buffer.append("<option value='"+details.getId()+"'>"+details.getName()+"</option>");
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

	public String getallcaloris() throws Exception {
		Connection connection = null;
		try {
			String id = request.getParameter("id");
			connection = Connection_provider.getconnection();
			DietaryDetailsDAO dietaryDetailsDAO=new JDBCDietaryDetailsDAO(connection);
			ArrayList<DietaryDetails> list1 = dietaryDetailsDAO.getSelCalories(id);
			StringBuffer buffer=new StringBuffer();
			int newid=0;
			String temp ="";
			String protein ="";
			String sodium="0",potassium="0",dqty="1";
			
			for(DietaryDetails details:list1){
				newid=details.getId();
				temp=details.getEnergy();
				protein = details.getProtein();
				sodium=""+details.getSodium();
				potassium=""+details.getPotassium();
				dqty=""+details.getDqty();
				 /*buffer.append("<option value='"+details.getId()+"'>"+details.getEnergy()+"</option>");*/
			}
			
			buffer.append("<input class='form-control' type='hidden' id='calories' name ='calories' value='"+newid+"'>");
			buffer.append("<input class='form-control' type='text' disabled='disabled' id='caloriesval' value='"+temp+"'>");
			
			buffer.append("<input type='hidden' id='prohd' value='"+protein+"'> <input type='hidden' id='calhi' value='"+temp+"'> <input type='hidden' id='sohi' value='"+sodium+"'> <input type='hidden' id='pothi' value='"+potassium+"'>");
		/*	buffer.append("<select class='form-control showToolTip chosen-select' name='calories' id='calories'>");
			//buffer.append("<option value='0'>Select Calories</option>");
			
			
			buffer.append("</select>");*/
			
			buffer.append("~");
			
			
			buffer.append("<input class='form-control' type='hidden' id='protein' name ='protein' value='"+newid+"'>");
			buffer.append("<input class='form-control' type='text' disabled='disabled' id='proteinval' value='"+protein+"'>");
			
			buffer.append("~");
			buffer.append(" <input type='number' id='nasodium' min='0' value='"+sodium+"' disabled='disabled' class='form-control' >");
			buffer.append("~");
			buffer.append(" <input type='number' id='kpotassium' min='0' value='"+potassium+"' disabled='disabled' class='form-control' >");
			
			/*buffer.append("<select class='form-control showToolTip chosen-select' name='protein' id='protein'>");
			//buffer.append("<option value='0'>Select Calories</option>");
			
			for(DietaryDetails details:list1){
				 buffer.append("<option value='"+details.getId()+"'>"+details.getProtein()+"</option>");
			}
			buffer.append("</select>");*/
			
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
	
	public void getpatientinfo() throws Exception{
		Connection connection = null;
		String ipdid = request.getParameter("ipdid");
		try {
			connection = Connection_provider.getconnection();
			BedDao bedDao=new JDBCBedDao(connection);
			Bed bed = bedDao.getEditIpdData(ipdid);
			String datetime;
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance(); 
			datetime = dateFormat.format(cal.getTime());
			//datetime = DateTimeUtils.getCommencingDate1(datetime);
			String[] datetime1 = datetime.split(" ");
			String date1 = datetime1[0];
			String time1 = datetime1[1];
			String[] temp = date1.split("-");
			String newdate = temp[2]+"-"+temp[1]+"-"+temp[0];
			datetime = newdate+" "+time1;
			
			String clientid = bed.getClientid();
			
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			Client client = clientDAO.getClientDetails(clientid);
			String gender = client.getGender();
			String age = DateTimeUtils.getAge(client.getDob());
			ArrayList<Bed> condlist=bedDao.getIpdConditionList(ipdid);
			String conds="";
			for(Bed b:condlist){
				conds=conds+b.getConditionname()+"<br>";
			}
			String conditioname = bedDao.getIpdConditionName(bed.getConditionid());
			
			int practionerid = Integer.parseInt(bed.getPractitionerid());
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			UserProfile userProfile = userProfileDAO.getUserprofileDetails(practionerid);
			String consultant = userProfile.getFullname();
			
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			String wardname = ipdDAO.getIpdWardName(bed.getWardid());
			String bedname = ipdDAO.getIpdBedName(bed.getBedid());
			StringBuilder builder = new StringBuilder();
			//String data = conditioname+"~"+wardname+"~"+bedname+"~"+age+"~"+gender+"~"+consultant+"~"+datetime;
			builder.append(""+conds+"~"+wardname+"~"+bedname+"~"+age+"~"+gender+"~"+consultant+"~"+datetime+"");
			/*builder.append("<label>Select Template Name:</labe>");
			builder.append("<select name='template' id='template' class='form-control showToolTip chosen-select' onchange='selectedTemplate(this.value)' data-original-title='' title=''>");
			builder.append("<option value='0'>Select Template</option>");*/
			/*ArrayList<DietaryDetails> templatelist = dietaryDetailsDAO.getTemplateList(ipdid);
			for(DietaryDetails details : templatelist){
				builder.append("<option value="+details.getId()+">"+details.getTemplatename()+"</option>");
			}
			builder.append("</select>");*/
			
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(builder.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
	}

	public String addTemp() throws Exception{
			Connection connection = null;
			DietaryDetails dietaryDetails = new DietaryDetails();
			String dietplan = request.getParameter("dietplan");
			String category_id = request.getParameter("category");
			String subcategory_id = request.getParameter("subcategory");
			String protein_id = request.getParameter("protein");
			String calories_id = request.getParameter("calories");
			String feed = request.getParameter("feed");
			String duration = request.getParameter("duration");
			String qty= request.getParameter("dqty");
			String sodium=request.getParameter("sodium");
			String potassium=request.getParameter("potassium");
			String remark=DateTimeUtils.isNull(request.getParameter("remark"));
			String category = "";
			String protein = "";
			String calories = "";
			
			String subcategory = "";
			String feedname = "";
		
		ArrayList<DietaryDetails> dietplanlist = new ArrayList<DietaryDetails>();
		if(session.getAttribute("dietplanlist")!=null){
			dietplanlist = (ArrayList<DietaryDetails>)session.getAttribute("dietplanlist");
		}
		
		try{
			connection = Connection_provider.getconnection();
			DietaryDetailsDAO dietaryDetailsDAO = new JDBCDietaryDetailsDAO(connection);
			DietaryDetails obj = dietaryDetailsDAO.getcategoryname(category_id);
			category = obj.getName();
			DietaryDetails obj1 = dietaryDetailsDAO.getSubcategoryname(subcategory_id);
			subcategory = obj1.getName();
			DietaryDetails obj2 = dietaryDetailsDAO.getEnergyname(calories_id);
			calories = obj2.getEnergy();
			protein = obj2.getProtein();
			feedname = dietaryDetailsDAO.getFeedNameFromId(feed);
		} catch (Exception e) {
			e.printStackTrace();
		}

		dietaryDetails.setDietplan(dietplan);
		dietaryDetails.setCategory(category);
		dietaryDetails.setSubcategory(subcategory);
		dietaryDetails.setProtein(protein);
		dietaryDetails.setCalories(calories);
		dietaryDetails.setFeed(feed);
		dietaryDetails.setDuration(duration);
		dietaryDetails.setCategoryid(category_id);
		dietaryDetails.setSubcategoryid(subcategory_id);
		dietaryDetails.setFeedname(feedname);
		
		dietaryDetails.setDqty(DateTimeUtils.convertToInteger(qty));
		dietaryDetails.setSodium(DateTimeUtils.convertToDouble(sodium));
		dietaryDetails.setPotassium(DateTimeUtils.convertToDouble(potassium));
		dietaryDetails.setRemark(remark);
		dietplanlist.add(dietaryDetails);
				
		 Object[] st = dietplanlist.toArray();
	      for (Object s : st) {
	        if (dietplanlist.indexOf(s) != dietplanlist.lastIndexOf(s)) {
	        	dietplanlist.remove(dietplanlist.lastIndexOf(s));
	         }
	      }
	      
		session.setAttribute("dietplanlist", dietplanlist);
		getdietplan();
		
		return null;	
	}
	
	public String getdietplan(){
		Connection connection = null;
		try{
			
			double total=0;
			double totalProtein =0;
			double totalCalories =0;
			
			StringBuffer str = new StringBuffer();
			connection = Connection_provider.getconnection();
			DietaryDetailsDAO detailsDAO = new JDBCDietaryDetailsDAO(connection);
			ArrayList<DietaryDetails> dietaryList = (ArrayList<DietaryDetails>)session.getAttribute("dietplanlist");
			
			int i = 0;
			for(DietaryDetails dietery1  : dietaryList){
				str.append("<tr>");
				String dietplanname = detailsDAO.getDietPlanName(dietery1.getDietplan());
				str.append("<td>"+dietplanname+"</td/>");
				str.append("<td>"+dietery1.getCategory()+"</td/>");
				str.append("<td>"+dietery1.getSubcategory()+"</td/>");
				str.append("<td>"+dietery1.getFeedname()+"</td/>");
				double qty=dietery1.getDqty();
				str.append("<td>"+dietery1.getDqty()+"</td/>");
				str.append("<td>"+DateTimeUtils.changeFormat(dietery1.getSodium()*qty)+"</td/>");
				str.append("<td>"+DateTimeUtils.changeFormat(dietery1.getPotassium()*qty)+"</td/>");
				
				str.append("<td>"+DateTimeUtils.changeFormat(DateTimeUtils.convertToDouble((dietery1.getProtein()))*qty)+"</td/>");
				str.append("<td>"+DateTimeUtils.changeFormat(DateTimeUtils.convertToDouble((dietery1.getCalories()))*qty)+"</td/>");
				/*str.append("<td>"+dietery1.getFeed()+"</td/>");*/
				/*str.append("<td>"+dietery1.getFeedname()+"</td/>");*/
				str.append("<td>"+dietery1.getRemark()+"</td/>");
				str.append("<td>"+dietery1.getDuration()+"</td/>");
				totalProtein = totalProtein +(( DateTimeUtils.convertToDouble(dietery1.getProtein())*qty)*100.0)/100.0;
				totalCalories = totalCalories + ((DateTimeUtils.convertToDouble(dietery1.getCalories())*qty)*100.0)/100.0;
				
				/*str.append("<td><i onclick='showedit("+i+")' class='fa fa-edit' ></i></td/>");*/
				str.append("<td><i onclick='deletedietdata("+i+")' style='cursor: pointer;' class='fa fa-trash-o' ></i></td/>");
				str.append("</tr>");
				i++;
				
			}
			str.append("<tr>");
			str.append("<td>Total</td/>");
			str.append("<td></td/>");
			str.append("<td></td/>");
			str.append("<td></td/>");
			str.append("<td></td/>");
			str.append("<td></td/>");
			str.append("<td></td/>");
			str.append("<td>"+totalProtein+"</td/>");
			str.append("<td>"+totalCalories+"</td/>");
			str.append("<td></td/>");
			str.append("<td></td/>");
			str.append("<td></td/>");
			/*str.append("<td></td/>");*/
			str.append("</tr>");
			
			
			
			
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+str.toString()+""); 
			
		}catch(Exception e){
			e.printStackTrace();
		}
			return null;
		}
	
	public String storediet() throws Exception{
		Connection connection = null;
		
		try {
			connection = Connection_provider.getconnection();
			String addmissionid = request.getParameter("addmissionid");
			String date = request.getParameter("date1");
			if(date==null){
				date=request.getParameter("fromDate");
			}
			if(date!=null){
				if(!date.equals(""))
			date = DateTimeUtils.getCommencingDate1(date);
			}if(date==null){
				date="";
			}
			if(date.equals("")){
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Calendar cal = Calendar.getInstance(); 
				date = dateFormat.format(cal.getTime());
			}
			String time="",hh="",mm="";
			int h=DateTimeUtils.convertToInteger(request.getParameter("hh"));
			int m=DateTimeUtils.convertToInteger(request.getParameter("mm"));
			if(h<10){
				hh="0"+h;
			}else{
				hh=""+h;
			}
			
			if(m<10){
				mm="0"+m;
			}else{
				mm=""+m;
			}
			
			time=hh+":"+mm+":00";
			
			//String lang = request.getParameter("lang");
			//String dietadvoice = request.getParameter("dietadvoice");
			
			ArrayList<DietaryDetails> dietplanlist = new ArrayList<DietaryDetails>();

			DietaryDetailsDAO dietaryDetailsDAO = new JDBCDietaryDetailsDAO(connection);
			int result = dietaryDetailsDAO.storedietaryparent(addmissionid,date,time);
			if(result>0){
			dietplanlist = (ArrayList<DietaryDetails>)session.getAttribute("dietplanlist");
			for(DietaryDetails dietaryDetails:dietplanlist){
				//  24 oct comment because set in aboved function
					/*String dietplan = dietaryDetails.getDietplan();
					String categoryid = dietaryDetailsDAO.getcatidfromname(dietaryDetails.getCategory()); 
					dietaryDetails.setCategoryid(categoryid);
					
					String subcategoryid = dietaryDetailsDAO.getsubcatidfromname(dietaryDetails.getSubcategory());
					dietaryDetails.setSubcategoryid(subcategoryid);*/
					
//					String caloriesid = dietaryDetailsDAO.getcalidfromname(dietaryDetails.getCalories());
//					dietaryDetails.setCaloriesid(caloriesid);
					int result1 = dietaryDetailsDAO.storedietaryplan(dietaryDetails,result,addmissionid,date);				
			}	
			
	
			dietplanlist.clear();
		}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return null;
	}
	public String storediet_ajax() throws Exception{
		Connection connection = null;
		
		try {
			connection = Connection_provider.getconnection();
			String addmissionid = request.getParameter("addmissionid");
			String date = request.getParameter("date1");
			if(date==null){
				date=request.getParameter("fromDate");
			}
			if(date!=null){
				if(!date.equals(""))
			date = DateTimeUtils.getCommencingDate1(date);
			}if(date==null){
				date="";
			}
			if(date.equals("")){
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Calendar cal = Calendar.getInstance(); 
				date = dateFormat.format(cal.getTime());
			}
			
			String time="",hh="",mm="";
			int h=DateTimeUtils.convertToInteger(request.getParameter("hh"));
			int m=DateTimeUtils.convertToInteger(request.getParameter("mm"));
			if(h<10){
				hh="0"+h;
			}else{
				hh=""+h;
			}
			
			if(m<10){
				mm="0"+m;
			}else{
				mm=""+m;
			}
			
			time=hh+":"+mm+":00";
			
			//String lang = request.getParameter("lang");
			//String dietadvoice = request.getParameter("dietadvoice");
			
			ArrayList<DietaryDetails> dietplanlist = new ArrayList<DietaryDetails>();

			DietaryDetailsDAO dietaryDetailsDAO = new JDBCDietaryDetailsDAO(connection);
			int result = dietaryDetailsDAO.storedietaryparent(addmissionid,date,time);
			if(result>0){
			dietplanlist = (ArrayList<DietaryDetails>)session.getAttribute("dietplanlist");
			for(DietaryDetails dietaryDetails:dietplanlist){
				//  24 oct comment because set in aboved function
					/*String dietplan = dietaryDetails.getDietplan();
					String categoryid = dietaryDetailsDAO.getcatidfromname(dietaryDetails.getCategory()); 
					dietaryDetails.setCategoryid(categoryid);
					
					String subcategoryid = dietaryDetailsDAO.getsubcatidfromname(dietaryDetails.getSubcategory());
					dietaryDetails.setSubcategoryid(subcategoryid);*/
					
//					String caloriesid = dietaryDetailsDAO.getcalidfromname(dietaryDetails.getCalories());
//					dietaryDetails.setCaloriesid(caloriesid);
					int result1 = dietaryDetailsDAO.storedietaryplan(dietaryDetails,result,addmissionid,date);				
			}	
		
		StringBuffer str= new StringBuffer();
			if(dietplanlist.size()>0){
				str.append("<h3>Diet :-"+DateTimeUtils.getCommencingDate1(date)+" "+time+"</h3>");
				str.append("<table class='my-table' style='width:90%'>");
				str.append("<tr>");
				str.append("<th>Day plan</th> <th>Diet Plan</th> <th>Diet</th> <th>Feed</th> <th>Qty</th> <th>Sodium</th> <th>Potassium</th>  <th>Protein</th>  <th>Calories</th> <th>Remark</th> <th>Duration</th>");
				str.append("</tr>");
				for(DietaryDetails dietery1:dietplanlist){
					str.append("<tr>");
					String dietplanname = dietaryDetailsDAO.getDietPlanName(dietery1.getDietplan());
					str.append("<td>"+dietplanname+"</td/>");
					str.append("<td>"+dietery1.getCategory()+"</td/>");
					str.append("<td>"+dietery1.getSubcategory()+"</td/>");
					str.append("<td>"+dietery1.getFeedname()+"</td/>");
					
					double qty=dietery1.getDqty();
					str.append("<td>"+dietery1.getDqty()+"</td/> ");
					str.append("<td>"+DateTimeUtils.changeFormat(dietery1.getSodium()*qty)+"</td/>");
					str.append("<td>"+DateTimeUtils.changeFormat(dietery1.getPotassium()*qty)+"</td/>");
					
					str.append("<td>"+DateTimeUtils.changeFormat(DateTimeUtils.convertToDouble((dietery1.getProtein()))*qty)+"</td/>");
					str.append("<td>"+DateTimeUtils.changeFormat(DateTimeUtils.convertToDouble((dietery1.getCalories()))*qty)+"</td/>");
					/*str.append("<td>"+dietery1.getFeed()+"</td/>");*/
					/*str.append("<td>"+dietery1.getFeedname()+"</td/>");*/
					str.append("<td>"+dietery1.getRemark()+"</td/>");
					str.append("<td>"+dietery1.getDuration()+"</td/>");
					str.append("</tr>");
				}
				
				
				str.append("</table>");	
			}
		
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+str.toString()+"");
			
			
			dietplanlist.clear();
		}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return null;
	}
	public String deleteTemp(){
		try {
			String id = request.getParameter("i");
			ArrayList<DietaryDetails> dietplanlist = (ArrayList<DietaryDetails>)session.getAttribute("dietplanlist");
			dietplanlist.remove(Integer.parseInt(id));
			getdietplan();	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public void prepare() throws Exception {
		Connection connection = null;
		
		try { 
			connection = Connection_provider.getconnection();
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			ArrayList<Master> masterlist = masterDAO.getMasterList(loginInfo);
			dietaryDetailsForm.setMasterlist(masterlist);
			mastername=(String)session.getAttribute("mastername");
			dietaryDetailsForm.setMastername(mastername);
			
			DietaryDAO dietaryDAO = new JDBCDietaryDAO(connection);
			ArrayList<Dietary> categorylist = dietaryDAO.getctaegory();
			dietaryDetailsForm.setDietarycategoryList(categorylist);
			
			DietaryDetailsDAO dietaryDetailsDAO = new JDBCDietaryDetailsDAO(connection);
			ArrayList<DietaryDetails> categorylist1 = dietaryDetailsDAO.getctaegorydetails();
			dietaryDetailsForm.setDietarycategorydetailsList(categorylist1);

			//DietaryDetailsDAO dietarydeDAO = new JDBCDietaryDetailsDAO(connection);
			ArrayList<DietaryDetails> calorieslist = dietaryDetailsDAO.getcaloriesdetails();
			dietaryDetailsForm.setDietarycaloriesList(calorieslist);
			
			
			/*IpdDAO ipdDAO = new JDBCIpdDAO(connection);		
			ArrayList<Bed> bedList= ipdDAO.getAllBedList("0", loginInfo.getClinicid(),loginInfo,"10","0");
			dietaryDetailsForm.setBedlist(bedList);
			*/
			ArrayList<DietaryDetails> dietplanlist = dietaryDetailsDAO.getDietPlanList();
			ArrayList<DietaryDetails> dietfeedlist = dietaryDetailsDAO.getDietFeedList();
			dietaryDetailsForm.setDietplanlist(dietplanlist);
			dietaryDetailsForm.setDietfeedlist(dietfeedlist);
			
			//IpdDAO ipdDAO = new JDBCIpdDAO(connection);		
			//ArrayList<Bed> bedList= ipdDAO.getAllBedList("0", loginInfo.getClinicid());
			//ArrayList<Bed> bedList = ipdDAO.getAllBedListForDietary("0", loginInfo.getClinicid());
			//dietaryDetailsForm.setBedlist(bedList);
			
			ArrayList<DietaryDetails> templatelist = dietaryDetailsDAO.getTemplateList(null);
			dietaryDetailsForm.setTemplatelist(templatelist);
			BedDao bedDao = new JDBCBedDao(connection);
			ArrayList<Bed> wardlist = bedDao.getAllWardList("0");
			dietaryDetailsForm.setWardlist(wardlist);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}

	public String deleteplan() throws Exception{
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			DietaryDetailsDAO dietaryDetailsDAO = new JDBCDietaryDetailsDAO(connection);
			String parentid = request.getParameter("parentid");
			int result = dietaryDetailsDAO.deleteplanfrmparent(parentid);
			int result1 = dietaryDetailsDAO.deleteplanfrmchild(parentid);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return "dietaryplandeleted";
	}
	
	public String printdiethistory() throws Exception {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			String fromdate = dietaryDetailsForm.getFromdate();
			String todate = dietaryDetailsForm.getTodate();
			String id = request.getParameter("parentid");
			
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
			
			
			
			
			DietaryDetailsDAO detailsDAO = new JDBCDietaryDetailsDAO(connection);
			String ipdid =detailsDAO.getipdidfrmpid(id);
			BedDao bedDao=new JDBCBedDao(connection);
			Bed bed = bedDao.getEditIpdData(ipdid);
			
			String clientid = bed.getClientid();
			
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			Client client = clientDAO.getClientDetails(clientid);
			String clientname = client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
			String gender = client.getGender();
			String age = DateTimeUtils.getAge(client.getDob());
			
			int practionerid = Integer.parseInt(bed.getPractitionerid());
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			UserProfile userProfile = userProfileDAO.getUserprofileDetails(practionerid);
			String consultant = userProfile.getFullname();
			
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			String wardname = ipdDAO.getIpdWardName(bed.getWardid());
			String bedname = ipdDAO.getIpdBedName(bed.getBedid());
			
			dietaryDetailsForm.setWardname(wardname);
			dietaryDetailsForm.setBedname(bedname);
			dietaryDetailsForm.setAge(age);
			dietaryDetailsForm.setGender(gender);
			dietaryDetailsForm.setConsultant(consultant);
			
			dietaryDetailsForm.setClientname(clientname);
		String datetime;
		datetime = bed.getAdmissiondate();
		String[] datetime1 = datetime.split(" ");
		String date1 = datetime1[0];
	    String time1 = datetime1[1];
	    String[] temp = date1.split("-");
	    String newdate = temp[2]+"-"+temp[1]+"-"+temp[0];
		datetime = newdate+" "+time1;
		dietaryDetailsForm.setAdmissiondate(datetime);
			
			//ArrayList<DietaryDetails> diethistlist = detailsDAO.getdiethistory(ipdid);
			ArrayList<DietaryDetails> parantidlist = detailsDAO.getParantIdList(ipdid,fromdate,todate);
			ArrayList<DietaryDetails> viewdietplan = new ArrayList<DietaryDetails>();
			double pro=0,cal=0;
			for (DietaryDetails dietaryDetails : parantidlist) {
				int id1 = dietaryDetails.getId();
				ArrayList<DietaryDetails> viewdietplan1 = detailsDAO.getalldietplan(""+id1);
				int size = viewdietplan1.size();
				if (size > 0) {
					double totalProtein = viewdietplan1.get(size-1).getTotalProtein();
					double totalCalories = viewdietplan1.get(size-1).getTotalCalories();
					pro=pro+totalProtein;
					cal=cal+totalCalories;
					dietaryDetailsForm.setTotalProtein(totalProtein);
					dietaryDetailsForm.setTotalCalories(totalCalories);
				}else{
					dietaryDetailsForm.setTotalProtein(0);
					dietaryDetailsForm.setTotalCalories(0);
				}
				
				
				viewdietplan.addAll(viewdietplan1);
			}
			dietaryDetailsForm.setTotalProtein(pro);
			dietaryDetailsForm.setTotalCalories(cal);
			//ArrayList<DietaryDetails> viewdietplan = detailsDAO.getalldietplan(id);
			dietaryDetailsForm.setViewdietplan(viewdietplan);
			dietaryDetailsForm.setParentid(Integer.parseInt(id));
			
			Clinic clinic = new Clinic();
 			ClinicDAO clinicDAO  = new JDBCClinicDAO(connection);
 			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
 			dietaryDetailsForm.setClinicName(clinic.getClinicName());
 			dietaryDetailsForm.setClinicOwner(clinic.getClinicOwner());
 			dietaryDetailsForm.setOwner_qualification(clinic.getOwner_qualification());
 			dietaryDetailsForm.setLandLine(clinic.getLandLine());
 			dietaryDetailsForm.setClinicemail(clinic.getEmail());
 			dietaryDetailsForm.setWebsiteUrl(clinic.getWebsiteUrl());
 			dietaryDetailsForm.setClinicLogo(clinic.getUserImageFileName());
 			
 			fromdate = DateTimeUtils.getCommencingDate1(fromdate);
 			todate = DateTimeUtils.getCommencingDate1(todate);
 			dietaryDetailsForm.setFromdate(fromdate);
 			dietaryDetailsForm.setTodate(todate);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return "printdiethistory";
	}
	

	//Edit data code
	
	public void getPatientDataForEdit() throws Exception{
		Connection connection = null;
		String ipdid = request.getParameter("ipdid");
		String parentid = request.getParameter("parentid");
		try {
			connection = Connection_provider.getconnection();
			DietaryDetailsDAO dietaryDetailsDAO = new JDBCDietaryDetailsDAO(connection);
			BedDao bedDao=new JDBCBedDao(connection);
			Bed bed = bedDao.getEditIpdData(ipdid);
			
			DietaryDAO dietaryDAO = new JDBCDietaryDAO(connection);
			ArrayList<Dietary> categorylist = dietaryDAO.getctaegory();
			
			String datetime;
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance(); 
			datetime = dateFormat.format(cal.getTime());
			//datetime = DateTimeUtils.getCommencingDate1(datetime);
			String[] datetime1 = datetime.split(" ");
			String date1 = datetime1[0];
			String time1 = datetime1[1];
			String[] temp = date1.split("-");
			String newdate = temp[2]+"-"+temp[1]+"-"+temp[0];
			datetime = newdate+" "+time1;
			
			String clientid = bed.getClientid();
			
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			Client client = clientDAO.getClientDetails(clientid);
			String gender = client.getGender();
			String age = DateTimeUtils.getAge(client.getDob());
			String conditioname = bedDao.getIpdConditionName(bed.getConditionid());
			String clientname = client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
			int practionerid = Integer.parseInt(bed.getPractitionerid());
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			UserProfile userProfile = userProfileDAO.getUserprofileDetails(practionerid);
			String consultant = userProfile.getFullname();
			
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			String wardname = ipdDAO.getIpdWardName(bed.getWardid());
			String bedname = ipdDAO.getIpdBedName(bed.getBedid());
			StringBuilder builder = new StringBuilder();
			
			
			String data = conditioname+"~"+wardname+"~"+bedname+"~"+age+"~"+gender+"~"+consultant+"~"+datetime+"~"+ipdid;
			builder.append(""+conditioname+"~"+wardname+"~"+bedname+"~"+age+"~"+gender+"~"+consultant+"~"+datetime+"~");
			//builder.append("<label for='exampleInputEmail1'>Select patient</label>");
			builder.append("<select name='patientid' id='epatient_id'>");
			builder.append("<option value='"+ipdid+"'>"+clientname+"</option>");
			builder.append("</select>");
			builder.append("~");
			
			ArrayList<DietaryDetails> dietplanlist = dietaryDetailsDAO.getalldietplan(parentid);
			
			int i = 0;
			for(DietaryDetails dietery1  : dietplanlist){
				ArrayList<DietaryDetails> subcategorylist = dietaryDetailsDAO.getcategorydetailslist(dietery1.getCategoryid());
				ArrayList<DietaryDetails> calorieslist = dietaryDetailsDAO.getSelCalories(dietery1.getCaloriesid());;
				ArrayList<DietaryDetails> dietplanlist1 = dietaryDetailsDAO.getDietPlanList();
				ArrayList<DietaryDetails> dietfeedlist = dietaryDetailsDAO.getDietFeedList();
				builder.append("<tr>");
				if(dietery1.getChildid()!=null){
					builder.append("<input type='hidden' name='dietdata["+i+"].childid' id='childid' value='"+dietery1.getChildid()+"'>");
				}else{
					builder.append("<input type='hidden' name='dietdata["+i+"].childid' id='childid' value='"+0+"'>");
				}
				builder.append("<td>");
				builder.append("<select name='dietdata["+i+"].dietplan' id='e_dietplan"+i+"' class='form-control showToolTip chosen-select'  data-original-title='' title=''>");
					for (DietaryDetails details1 : dietplanlist1) {
						if(dietery1.getDietplan().equals(""+details1.getId())){
							builder.append("<option value='"+details1.getId()+"' selected='selected'>"+details1.getName()+"</option>");
						}else
							builder.append("<option value='"+details1.getId()+"'>"+details1.getName()+"</option>");
					}
				/*if(dietery1.getDietplan().equals("Breakfast"))
					builder.append("<option value='Breakfast' selected='selected'>Breakfast</option>");
				else
					builder.append("<option value='Breakfast'>Breakfast</option>");
				
				if(dietery1.getDietplan().equals("Midmorning Snack"))
					builder.append("<option value='Midmorning Snack' selected='selected'>Midmorning Snack</option>");
				else
					builder.append("<option value='Midmorning Snack'>Midmorning Snack</option>");
				
				if(dietery1.getDietplan().equals("Lunch"))
					builder.append("<option value='Lunch' selected='selected'>Lunch</option>");
				else
					builder.append("<option value='Lunch'>Lunch</option>");
				
				if(dietery1.getDietplan().equals("Midafternoon Snack"))
					builder.append("<option value='Midafternoon Snack' selected='selected'>Midafternoon Snack</option>");
				else
					builder.append("<option value='Midafternoon Snack'>Midafternoon Snack</option>");
				
				if(dietery1.getDietplan().equals("Dinner"))
					builder.append("<option value='Dinner' selected='selected'>Dinner</option>");
				else
					builder.append("<option value='Dinner'>Dinner</option>");
				
				if(dietery1.getDietplan().equals("Midevening Snack"))
					builder.append("<option value='Midevening Snack' selected='selected'>Midevening Snack</option>");
				else
					builder.append("<option value='Midevening Snack'>Midevening Snack</option>");*/
				
				builder.append("</select>");
				builder.append("</td>");
				
				builder.append("<td>");
				builder.append("<select name='dietdata["+i+"].category' id='e_category"+i+"' onchange='selectNewSubDiet(this.value,"+i+")' class='form-control showToolTip chosen-select' data-original-title='' title=''>");
					for (Dietary details : categorylist) {
						if(dietery1.getCategory().equals(details.getName())){
							builder.append("<option value='"+details.getId()+"' selected='selected'>"+details.getName()+"</option>");
						}else
							builder.append("<option value='"+details.getId()+"'>"+details.getName()+"</option>");
					}
				builder.append("</select>");
				builder.append("</td>");
				
				builder.append("<td id='subcat"+i+"'>");
				builder.append("<select name='dietdata["+i+"].subcategory' id='e_subcategory"+i+"' class='form-control showToolTip chosen-select' onchange='selectNewSubCal(this.value,"+i+")' data-original-title='' title=''>");
					for (DietaryDetails details : subcategorylist) {
						if(dietery1.getSubcategory().equals(details.getName())){
							builder.append("<option value='"+details.getId()+"' selected='selected'>"+details.getName()+"</option>");
						}else
							builder.append("<option value='"+details.getId()+"'>"+details.getName()+"</option>");
					}
				builder.append("</select>");
				builder.append("</td>");
				
				
				
				builder.append("<td>");
				builder.append("<select name='dietdata["+i+"].feed' id='e_feed"+i+"' class='form-control showToolTip chosen-select' data-original-title='' title=''>");
				for (DietaryDetails details : dietfeedlist) {
					
					if(dietery1.getFeed().equals(""+details.getId())){
						builder.append("<option value='"+details.getId()+"' selected='selected'>"+details.getName()+"</option>");
					}else
						builder.append("<option value='"+details.getId()+"'>"+details.getName()+"</option>");
				}
				builder.append("</select>");
				builder.append("</td>");
				
				
				builder.append("<td id='prooedit"+i+"'>");
				int newid=0;
				String temp1="";
				String protein="";
				/*builder.append("<select name='dietdata["+i+"].protein' id='p_protein"+i+"' class='form-control showToolTip chosen-select' data-original-title='' title=''>");*/
					for (DietaryDetails details : calorieslist) {
						if(dietery1.getProtein().equals(details.getProtein())){
							
							newid=details.getId();
							temp1=details.getEnergy();
							protein=details.getProtein();
							
							
							/*builder.append("<option value='"+details.getId()+"' selected='selected'>"+details.getProtein()+"</option>");*/
							
						}/*else
							builder.append("<option value='"+details.getId()+"'>"+details.getProtein()+"</option>");*/
						
						builder.append("<input type='hidden' name='dietdata["+i+"].protein' id='p_protein"+i+"'value='"+newid+"'>");
						/*buffer.append("<input type='hidden' id='e_calories' name ='calories' value='"+newid+"'>");*/
						builder.append("<input type='text' readonly ='readonly' value='"+protein+"'>");
					}
				/*builder.append("</select>");*/
				builder.append("</td>");
				for (DietaryDetails details : calorieslist) {
				/*if(dietery1.getCalories().equals(details.getEnergy())){*/
					builder.append("<td id='subcal"+i+"'>");
					builder.append("<input type='hidden' name='dietdata["+i+"].calories' id='e_calories"+i+"'value='"+newid+"'>");
					builder.append("<input type='text' readonly ='readonly' value='"+details.getEnergy()+"'>");
				}
				/*}*/
				/*builder.append("<td id='subcal"+i+"'>");
				builder.append("<input type='hidden' name='dietdata["+i+"].calories' id='e_calories"+i+"'value='"+newid+"'>");
				builder.append("<input type='text' readonly ='readonly' value='"+temp+"'>");*/
				
				/*builder.append("<td id='subcal"+i+"'>");
				builder.append("<select name='dietdata["+i+"].calories' id='e_calories"+i+"' class='form-control showToolTip chosen-select' data-original-title='' title=''>");
					for (DietaryDetails details : calorieslist) {
						if(dietery1.getCalories().equals(details.getEnergy())){
							builder.append("<option value='"+details.getId()+"' selected='selected'>"+details.getEnergy()+"</option>");
						}else
							builder.append("<option value='"+details.getId()+"'>"+details.getEnergy()+"</option>");
					}
				builder.append("</select>");*/
				builder.append("</td>");
				
				/*builder.append("<td>");
				builder.append("<select name='dietdata["+i+"].feed' id='e_feed"+i+"' class='form-control showToolTip chosen-select' data-original-title='' title=''>");
				for (DietaryDetails details : dietfeedlist) {
					
					if(dietery1.getFeed().equals(""+details.getId())){
						builder.append("<option value='"+details.getId()+"' selected='selected'>"+details.getName()+"</option>");
					}else
						builder.append("<option value='"+details.getId()+"'>"+details.getName()+"</option>");
				}*/
				/*if(dietery1.getFeed().equals("15 Ml"))
					builder.append("<option value='15 Ml' selected='selected'>15 Ml</option>");
				else
					builder.append("<option value='15 ML'>15 ML</option>");
				
				if(dietery1.getFeed().equals("25 ML"))
					builder.append("<option value='25 ML' selected='selected'>25 ML</option>");
				else
					builder.append("<option value='25 ML'>25 ML</option>");
				
				if(dietery1.getFeed().equals("35 ML"))
					builder.append("<option value='35 ML' selected='selected'>35 ML</option>");
				else
					builder.append("<option value='35 ML'>35 ML</option>");
			
				if(dietery1.getFeed().equals("45 Ml"))
					builder.append("<option value='45 Ml' selected='selected'>45 Ml</option>");
				else
					builder.append("<option value='45 ML'>45 ML</option>");
				
				if(dietery1.getFeed().equals("55 ML"))
					builder.append("<option value='55 ML' selected='selected'>55 ML</option>");
				else
					builder.append("<option value='55 ML'>55 ML</option>");
			
				if(dietery1.getFeed().equals("1 Glass"))
					builder.append("<option value='1 Glass' selected='selected'>1 Glass</option>");
				else
					builder.append("<option value='1 Glass'>1 Glass</option>");
			
				if(dietery1.getFeed().equals("1 Bowl"))
					builder.append("<option value='1 Bowl' selected='selected'>1 Bowl</option>");
				else
					builder.append("<option value='1 Bowl'>1 Bowl</option>");
			
				if(dietery1.getFeed().equals("1 Katori"))
					builder.append("<option value='1 Katori' selected='selected'>1 Katori</option>");
				else
					builder.append("<option value='1 Katori'>1 Katori</option>");*/
				
				/*builder.append("</select>");
				builder.append("</td>");*/
			
				builder.append("<td>");
				builder.append("<select name='dietdata["+i+"].duration' id='e_duration"+i+"' class='form-control showToolTip chosen-select' data-original-title='' title=''>");
				if(dietery1.getDuration().equals("1"))
					builder.append("<option value='1' selected='selected'>1</option>");
				else
					builder.append("<option value='1'>1</option>");
				
				if(dietery1.getDuration().equals("2"))
					builder.append("<option value='2' selected='selected'>2</option>");
				else
					builder.append("<option value='2'>2</option>");
			
				if(dietery1.getDuration().equals("3"))
					builder.append("<option value='3' selected='selected'>3</option>");
				else
					builder.append("<option value='3'>3</option>");
				
				if(dietery1.getDuration().equals("4"))
					builder.append("<option value='4' selected='selected'>4</option>");
				else
					builder.append("<option value='4'>4</option>");
				
				builder.append("</select>");
				builder.append("</td>");
				
				/*str.append("<td><i onclick='showedit("+i+")' class='fa fa-edit' ></i></td/>");*/
				builder.append("<td><i onclick='deleteDataFromDB("+i+","+dietery1.getChildid()+")' style='cursor: pointer;' class='fa fa-trash-o' ></i></td/>");
				builder.append("<input type='hidden' id='tempcount"+i+"' name='tempcount"+i+"' value='"+i+"' />");
				builder.append("</tr>");
				i++;
			}
			builder.append("~"+parentid+"");
			int j = i+1;
			builder.append("~"+j+"");	
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(builder.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
	}

	public String getSubDiet()throws Exception {
		Connection connection = null;
		try {
			String id = request.getParameter("name");
			connection = Connection_provider.getconnection();
			DietaryDetailsDAO dietaryDetailsDAO=new JDBCDietaryDetailsDAO(connection);
			ArrayList<DietaryDetails> list = dietaryDetailsDAO.getcategorydetailslist(id);
			//String data=student.getRollno()+"~"+student.getName()+"~"+student.getAge()+"~"+student.getAddress();
			
			StringBuffer buffer=new StringBuffer();
			buffer.append("<select class='form-control showToolTip chosen-select' name='subcategory' id='e_subcategory' onchange='selectSubCal(this.value)''>");
			buffer.append("<option value='0'>Select Diet</option>");
			
			for(DietaryDetails details:list){
				 buffer.append("<option value='"+details.getId()+"'>"+details.getName()+"</option>");
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

public String getSubCaloris() throws Exception {
		Connection connection = null;
		try {
			String id = request.getParameter("id");
			connection = Connection_provider.getconnection();
			DietaryDetailsDAO dietaryDetailsDAO=new JDBCDietaryDetailsDAO(connection);
			ArrayList<DietaryDetails> list1 = dietaryDetailsDAO.getSelCalories(id);
			
			StringBuffer buffer=new StringBuffer();
			int newid=0;
			String temp ="";
			String protein ="";
			
			
			/*buffer.append("<select class='form-control showToolTip chosen-select' name='calories' id='e_calories'>");*/
			//buffer.append("<option value='0'>Select Calories</option>");
			
			for(DietaryDetails details:list1){
				newid=details.getId();
				temp=details.getEnergy();
				protein = details.getProtein();
				/* buffer.append("<option value='"+details.getId()+"'>"+details.getEnergy()+"</option>");*/
			}
			buffer.append("<input type='hidden' id='e_calories' name ='calories' value='"+newid+"'>");
			buffer.append("<input type='text' readonly ='readonly' value='"+temp+"'>");
			/*buffer.append("</select>");*/
			
			
          //buffer.append("</select>");
			
			buffer.append("~");
			
			
			
			
			
			buffer.append("<input type='hidden' id='p_protein' name ='protein' value='"+newid+"'>");
			buffer.append("<input type='text' readonly ='readonly' value='"+protein+"'>");
			
			/*buffer.append("<select class='form-control showToolTip chosen-select' name='protein' id='p_protein'>");
			//buffer.append("<option value='0'>Select Calories</option>");
			
			for(DietaryDetails details:list1){
				 buffer.append("<option value='"+details.getId()+"'>"+details.getProtein()+"</option>");
			}
			buffer.append("</select>");*/
			
			
			
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

	
	
	
	
public String editTempData() throws Exception{
	Connection connection = null;
	try {
		DietaryDetails dietaryDetails = new DietaryDetails();
		String dietplan = request.getParameter("dietplan");
		String category_id = request.getParameter("category");
		String subcategory_id = request.getParameter("subcategory");
		String protein_id = request.getParameter("protein");
		String calories_id = request.getParameter("calories");
		String feed = request.getParameter("feed");
		String duration = request.getParameter("duration");
		String category = "";
		String protein ="";
		String calories = "";
		String subcategory = "";
		String counts = request.getParameter("counts");
		int i = Integer.parseInt(counts);
		connection = Connection_provider.getconnection();
		DietaryDetailsDAO dietaryDetailsDAO = new JDBCDietaryDetailsDAO(connection);
		DietaryDAO dietaryDAO = new JDBCDietaryDAO(connection);
		
		DietaryDetails obj = dietaryDetailsDAO.getcategoryname(category_id);
		category = obj.getName();
		DietaryDetails obj1 = dietaryDetailsDAO.getSubcategoryname(subcategory_id);
		subcategory = obj1.getName();
		DietaryDetails obj2 = dietaryDetailsDAO.getEnergyname(calories_id);
		calories = obj2.getEnergy();
		protein = obj2.getProtein();
		
		ArrayList<Dietary> categorylist = dietaryDAO.getctaegory();
		ArrayList<DietaryDetails> subcategorylist = dietaryDetailsDAO.getcategorydetailslist(category_id);
		ArrayList<DietaryDetails> calorieslist = dietaryDetailsDAO.getSelCalories(calories_id);;
		ArrayList<DietaryDetails> dietplanlist = dietaryDetailsDAO.getDietPlanList();
		ArrayList<DietaryDetails> dietfeedlist = dietaryDetailsDAO.getDietFeedList();
		StringBuilder str = new StringBuilder();
		int newid=0;
		String temp ="";
		
		
		str.append("<tr>");
		
		str.append("<input type='hidden' name='dietdata["+i+"].childid' id='childid' value='"+0+"'>");
		str.append("<td>");
		str.append("<select name='dietdata["+i+"].dietplan' id='e_dietplan"+i+"' class='form-control showToolTip chosen-select'  data-original-title='' title=''>");
		for (DietaryDetails details1 : dietplanlist) {
			if(dietplan.equals(""+details1.getId())){
				str.append("<option value='"+details1.getId()+"' selected='selected'>"+details1.getName()+"</option>");
			}else
				str.append("<option value='"+details1.getId()+"'>"+details1.getName()+"</option>");
		}
		/*if(dietplan.equals("Breakfast"))
			str.append("<option value='Breakfast' selected='selected'>Breakfast</option>");
		else
			str.append("<option value='Breakfast'>Breakfast</option>");
		
		if(dietplan.equals("Midmorning Snack"))
			str.append("<option value='Midmorning Snack' selected='selected'>Midmorning Snack</option>");
		else
			str.append("<option value='Midmorning Snack'>Midmorning Snack</option>");
		
		if(dietplan.equals("Lunch"))
			str.append("<option value='Lunch' selected='selected'>Lunch</option>");
		else
			str.append("<option value='Lunch'>Lunch</option>");
		
		if(dietplan.equals("Midafternoon Snack"))
			str.append("<option value='Midafternoon Snack' selected='selected'>Midafternoon Snack</option>");
		else
			str.append("<option value='Midafternoon Snack'>Midafternoon Snack</option>");
		
		if(dietplan.equals("Dinner"))
			str.append("<option value='Dinner' selected='selected'>Dinner</option>");
		else
			str.append("<option value='Dinner'>Dinner</option>");
		
		if(dietplan.equals("Midevening Snack"))
			str.append("<option value='Midevening Snack' selected='selected'>Midevening Snack</option>");
		else
			str.append("<option value='Midevening Snack'>Midevening Snack</option>");*/
		
		str.append("</select>");
		str.append("</td>");
		
		str.append("<td>");
		str.append("<select name='dietdata["+i+"].category' id='e_category"+i+"' onchange='selectNewSubDiet(this.value,"+i+")' class='form-control showToolTip chosen-select' data-original-title='' title=''>");
			for (Dietary details : categorylist) {
				if(category.equals(details.getName())){
					str.append("<option value='"+details.getId()+"' selected='selected'>"+details.getName()+"</option>");
				}else
					str.append("<option value='"+details.getId()+"'>"+details.getName()+"</option>");
			}
		str.append("</select>");
		str.append("</td>");
		
		str.append("<td id='subcat"+i+"'>");
		str.append("<select name='dietdata["+i+"].subcategory' id='e_subcategory"+i+"' class='form-control showToolTip chosen-select' onchange='selectNewSubCal(this.value,"+i+")' data-original-title='' title=''>");
			for (DietaryDetails details : subcategorylist) {
				if(subcategory.equals(details.getName())){
					str.append("<option value='"+details.getId()+"' selected='selected'>"+details.getName()+"</option>");
				}else
					str.append("<option value='"+details.getId()+"'>"+details.getName()+"</option>");
			}
		str.append("</select>");
		str.append("</td>");
		
		
		
		for (DietaryDetails details : calorieslist) {
			newid=details.getId();
			temp=details.getEnergy();
		str.append("<td id='tdproo"+i+"'>");
		str.append("<input type='hidden' name='dietdata["+i+"].protein' id='p_protein"+i+" value='"+newid+"'>");
		str.append("<input type='text' readonly ='readonly' value='"+details.getProtein()+"'>");
		/*str.append("<select name='dietdata["+i+"].protein' id='p_protein"+i+"' class='form-control showToolTip chosen-select' data-original-title='' title=''>");
			for (DietaryDetails details : calorieslist) {
				if(protein.equals(details.getProtein())){
					str.append("<option value='"+details.getId()+"' selected='selected'>"+details.getProtein()+"</option>");
				}else
					str.append("<option value='"+details.getId()+"'>"+details.getProtein()+"</option>");
			}
		str.append("</select>");*/
		/*str.append("</td>");*/
		}
		
		
	
		
		
		
		for (DietaryDetails details : calorieslist) {
		str.append("<td id='subcal"+i+"'>");
		str.append("<input type='hidden' name='dietdata["+i+"].calories' id='e_calories"+i+"' value='"+newid+"'>");
		str.append("<input type='text' readonly ='readonly' value='"+temp+"'>");
		/*str.append("<select name='dietdata["+i+"].calories' id='e_calories"+i+"' class='form-control showToolTip chosen-select' data-original-title='' title=''>");*/
			/*for (DietaryDetails details : calorieslist) {*/
				/*if(calories.equals(details.getEnergy())){
					str.append("<option value='"+details.getId()+"' selected='selected'>"+details.getEnergy()+"</option>");
				}else
					str.append("<option value='"+details.getId()+"'>"+details.getEnergy()+"</option>");
			}*/
		str.append("</select>");
		str.append("</td>");
		}
		str.append("<td>");
		str.append("<select name='dietdata["+i+"].feed' id='e_feed"+i+"' class='form-control showToolTip chosen-select' data-original-title='' title=''>");
			for (DietaryDetails details : dietfeedlist) {
				if(feed.equals(""+details.getId())){
					str.append("<option value='"+details.getId()+"' selected='selected'>"+details.getName()+"</option>");
				}else
					str.append("<option value='"+details.getId()+"'>"+details.getName()+"</option>");
			}
		/*if(feed.equals("15 Ml"))
			str.append("<option value='15 Ml' selected='selected'>15 Ml</option>");
		else
			str.append("<option value='15 ML'>15 ML</option>");
		
		if(feed.equals("25 ML"))
			str.append("<option value='25 ML' selected='selected'>25 ML</option>");
		else
			str.append("<option value='25 ML'>25 ML</option>");
		
		if(feed.equals("35 ML"))
			str.append("<option value='35 ML' selected='selected'>35 ML</option>");
		else
			str.append("<option value='35 ML'>35 ML</option>");
	
		if(feed.equals("45 Ml"))
			str.append("<option value='45 Ml' selected='selected'>45 Ml</option>");
		else
			str.append("<option value='45 ML'>45 ML</option>");
		
		if(feed.equals("55 ML"))
			str.append("<option value='55 ML' selected='selected'>55 ML</option>");
		else
			str.append("<option value='55 ML'>55 ML</option>");
	
		if(feed.equals("1 Glass"))
			str.append("<option value='1 Glass' selected='selected'>1 Glass</option>");
		else
			str.append("<option value='1 Glass'>1 Glass</option>");
	
		if(feed.equals("1 Bowl"))
			str.append("<option value='1 Bowl' selected='selected'>1 Bowl</option>");
		else
			str.append("<option value='1 Bowl'>1 Bowl</option>");
	
		if(feed.equals("1 Katori"))
			str.append("<option value='1 Katori' selected='selected'>1 Katori</option>");
		else
			str.append("<option value='1 Katori'>1 Katori</option>");*/
		
		str.append("</select>");
		str.append("</td>");
	
		str.append("<td>");
		str.append("<select name='dietdata["+i+"].duration' id='e_duration"+i+"' class='form-control showToolTip chosen-select' data-original-title='' title=''>");
		if(duration.equals("1"))
			str.append("<option value='1' selected='selected'>1</option>");
		else
			str.append("<option value='1'>1</option>");
		
		if(duration.equals("2"))
			str.append("<option value='2' selected='selected'>2</option>");
		else
			str.append("<option value='2'>2</option>");
	
		if(duration.equals("3"))
			str.append("<option value='3' selected='selected'>3</option>");
		else
			str.append("<option value='3'>3</option>");
		
		if(duration.equals("4"))
			str.append("<option value='4' selected='selected'>4</option>");
		else
			str.append("<option value='4'>4</option>");
		
		str.append("</select>");
		str.append("</td>");

		
		str.append("<td><i onclick='deleteUpdatedData("+i+")' style='cursor: pointer;' class='fa fa-trash-o' ></i></td/>");
		str.append("</tr>");
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+str.toString()+""); 

		} catch (Exception e) {
			e.printStackTrace();
		}


//	ArrayList<DietaryDetails> dietplanlist = new ArrayList<DietaryDetails>();
//	if(session.getAttribute("dietplanlist")!=null){
//		dietplanlist = (ArrayList<DietaryDetails>)session.getAttribute("dietplanlist");
//	}
//	
//	try{
//		connection = Connection_provider.getconnection();
//		DietaryDetailsDAO dietaryDetailsDAO = new JDBCDietaryDetailsDAO(connection);
//		DietaryDetails obj = dietaryDetailsDAO.getcategoryname(category_id);
//		category = obj.getName();
//		DietaryDetails obj1 = dietaryDetailsDAO.getSubcategoryname(subcategory_id);
//		subcategory = obj1.getName();
//		DietaryDetails obj2 = dietaryDetailsDAO.getEnergyname(calories_id);
//		calories = obj2.getEnergy();
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
//	
//	dietaryDetails.setCategoryid(category_id);
//	dietaryDetails.setSubcategoryid(subcategory_id);
//	dietaryDetails.setCaloriesid(subcategory_id);
//	dietaryDetails.setDietplan(dietplan);
//	dietaryDetails.setCategory(category);
//	dietaryDetails.setSubcategory(subcategory);
//	dietaryDetails.setCalories(calories);
//	dietaryDetails.setFeed(feed);
//	dietaryDetails.setDuration(duration);
//	dietplanlist.add(dietaryDetails);
//			
//	 Object[] st = dietplanlist.toArray();
//      for (Object s : st) {
//        if (dietplanlist.indexOf(s) != dietplanlist.lastIndexOf(s)) {
//        	dietplanlist.remove(dietplanlist.lastIndexOf(s));
//         }
//      }
//      
//	session.setAttribute("dietplanlist", dietplanlist);
//	setEditDietPlan();
	
	return null;	
}

public String setEditDietPlan() throws Exception{
	Connection connection = null;
	try{
		connection = Connection_provider.getconnection();
		
		DietaryDAO dietaryDAO = new JDBCDietaryDAO(connection);
		ArrayList<Dietary> categorylist = dietaryDAO.getctaegory();
		
		DietaryDetailsDAO dietaryDetailsDAO = new JDBCDietaryDetailsDAO(connection);
		
		
		StringBuffer str = new StringBuffer();
		//ArrayList<DietaryDetails> dietaryList = (ArrayList<DietaryDetails>)session.getAttribute("dietplanlist");
		
		int i = 0;
		//for(DietaryDetails dietery1  : dietaryList){
		for(DietaryDetails dietery1 : dietaryDetailsForm.getDietdata()){
			ArrayList<DietaryDetails> subcategorylist = dietaryDetailsDAO.getcategorydetailslist(dietery1.getCategoryid());
			ArrayList<DietaryDetails> calorieslist = dietaryDetailsDAO.getSelCalories(dietery1.getCaloriesid());;
			
			str.append("<tr>");
			str.append("<input type='hidden' name='dietdata["+i+"].childid' id='childid' value='"+dietery1.getChildid()+"'>");
			str.append("<td>");
			str.append("<select name='dietdata["+i+"].dietplan' id='e_dietplan"+i+"' class='form-control showToolTip chosen-select'  data-original-title='' title=''>");
			
			if(dietery1.getDietplan().equals("Breakfast"))
				str.append("<option value='Breakfast' selected='selected'>Breakfast</option>");
			else
				str.append("<option value='Breakfast'>Breakfast</option>");
			
			if(dietery1.getDietplan().equals("Midmorning Snack"))
				str.append("<option value='Midmorning Snack' selected='selected'>Midmorning Snack</option>");
			else
				str.append("<option value='Midmorning Snack'>Midmorning Snack</option>");
			
			if(dietery1.getDietplan().equals("Lunch"))
				str.append("<option value='Lunch' selected='selected'>Lunch</option>");
			else
				str.append("<option value='Lunch'>Lunch</option>");
			
			if(dietery1.getDietplan().equals("Midafternoon Snack"))
				str.append("<option value='Midafternoon Snack' selected='selected'>Midafternoon Snack</option>");
			else
				str.append("<option value='Midafternoon Snack'>Midafternoon Snack</option>");
			
			if(dietery1.getDietplan().equals("Dinner"))
				str.append("<option value='Dinner' selected='selected'>Dinner</option>");
			else
				str.append("<option value='Dinner'>Dinner</option>");
			
			if(dietery1.getDietplan().equals("Midevening Snack"))
				str.append("<option value='Midevening Snack' selected='selected'>Midevening Snack</option>");
			else
				str.append("<option value='Midevening Snack'>Midevening Snack</option>");
			
			str.append("</select>");
			str.append("</td>");
			
			str.append("<td>");
			str.append("<select name='dietdata["+i+"].category' id='e_category"+i+"' onchange='selectNewSubDiet(this.value,"+i+")' class='form-control showToolTip chosen-select' data-original-title='' title=''>");
				for (Dietary details : categorylist) {
					if(dietery1.getCategory().equals(details.getName())){
						str.append("<option value='"+details.getId()+"' selected='selected'>"+details.getName()+"</option>");
					}else
						str.append("<option value='"+details.getId()+"'>"+details.getName()+"</option>");
				}
			str.append("</select>");
			str.append("</td>");
			
			str.append("<td id='subcat"+i+"'>");
			str.append("<select name='dietdata["+i+"].subcategory' id='e_subcategory"+i+"' class='form-control showToolTip chosen-select' onchange='selectNewSubCal(this.value,"+i+")' data-original-title='' title=''>");
				for (DietaryDetails details : subcategorylist) {
					if(dietery1.getSubcategory().equals(details.getName())){
						str.append("<option value='"+details.getId()+"' selected='selected'>"+details.getName()+"</option>");
					}else
						str.append("<option value='"+details.getId()+"'>"+details.getName()+"</option>");
				}
			str.append("</select>");
			str.append("</td>");
			
			str.append("<td id='subcal"+i+"'>");
			str.append("<select name='dietdata["+i+"].calories' id='e_calories"+i+"' class='form-control showToolTip chosen-select' data-original-title='' title=''>");
				for (DietaryDetails details : calorieslist) {
					if(dietery1.getCalories().equals(details.getEnergy())){
						str.append("<option value='"+details.getId()+"' selected='selected'>"+details.getEnergy()+"</option>");
					}else
						str.append("<option value='"+details.getId()+"'>"+details.getEnergy()+"</option>");
				}
			str.append("</select>");
			str.append("</td>");
			
			str.append("<td>");
			str.append("<select name='dietdata["+i+"].feed' id='e_feed"+i+"' class='form-control showToolTip chosen-select' data-original-title='' title=''>");
			
			if(dietery1.getFeed().equals("15 Ml"))
				str.append("<option value='15 Ml' selected='selected'>15 Ml</option>");
			else
				str.append("<option value='15 ML'>15 ML</option>");
			
			if(dietery1.getFeed().equals("25 ML"))
				str.append("<option value='25 ML' selected='selected'>25 ML</option>");
			else
				str.append("<option value='25 ML'>25 ML</option>");
			
			if(dietery1.getFeed().equals("35 ML"))
				str.append("<option value='35 ML' selected='selected'>35 ML</option>");
			else
				str.append("<option value='35 ML'>35 ML</option>");
		
			if(dietery1.getFeed().equals("45 Ml"))
				str.append("<option value='45 Ml' selected='selected'>45 Ml</option>");
			else
				str.append("<option value='45 ML'>45 ML</option>");
			
			if(dietery1.getFeed().equals("55 ML"))
				str.append("<option value='55 ML' selected='selected'>55 ML</option>");
			else
				str.append("<option value='55 ML'>55 ML</option>");
		
			if(dietery1.getFeed().equals("1 Glass"))
				str.append("<option value='1 Glass' selected='selected'>1 Glass</option>");
			else
				str.append("<option value='1 Glass'>1 Glass</option>");
		
			if(dietery1.getFeed().equals("1 Bowl"))
				str.append("<option value='1 Bowl' selected='selected'>1 Bowl</option>");
			else
				str.append("<option value='1 Bowl'>1 Bowl</option>");
		
			if(dietery1.getFeed().equals("1 Katori"))
				str.append("<option value='1 Katori' selected='selected'>1 Katori</option>");
			else
				str.append("<option value='1 Katori'>1 Katori</option>");
			
			str.append("</select>");
			str.append("</td>");
		
			str.append("<td>");
			str.append("<select name='dietdata["+i+"].duration' id='e_duration"+i+"' class='form-control showToolTip chosen-select' data-original-title='' title=''>");
			if(dietery1.getDuration().equals("1"))
				str.append("<option value='1' selected='selected'>1</option>");
			else
				str.append("<option value='1'>1</option>");
			
			if(dietery1.getDuration().equals("2"))
				str.append("<option value='2' selected='selected'>2</option>");
			else
				str.append("<option value='2'>2</option>");
		
			if(dietery1.getDuration().equals("3"))
				str.append("<option value='3' selected='selected'>3</option>");
			else
				str.append("<option value='3'>3</option>");
			
			if(dietery1.getDuration().equals("4"))
				str.append("<option value='4' selected='selected'>4</option>");
			else
				str.append("<option value='4'>4</option>");
			
			str.append("</select>");
			str.append("</td>");

			
			str.append("<td><i onclick='deleteUpdatedData("+i+")' style='cursor: pointer;' class='fa fa-trash-o' ></i></td/>");
			str.append("</tr>");
			i++;
		}
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+str.toString()+""); 
		
	}catch(Exception e){
		e.printStackTrace();
	}
		return null;
}

public String deleteDataFromDB() throws Exception{
	Connection connection = null;
	try {
		String id = request.getParameter("i");
		String childid = request.getParameter("childid");
		connection = Connection_provider.getconnection();
		DietaryDetailsDAO dietaryDetailsDAO = new JDBCDietaryDetailsDAO(connection);
		int result = dietaryDetailsDAO.deleteDataFrmChild(childid);
		//ArrayList<DietaryDetails> dietplanlist = (ArrayList<DietaryDetails>)session.getAttribute("dietplanlist");
		//dietplanlist.remove(Integer.parseInt(id));
		//setEditDietPlan();
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(id);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

public String updateDiet() throws Exception{
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		String addmissionid = request.getParameter("addmissionid");
		String parentid = request.getParameter("parentid");
		//String lang = request.getParameter("lang");
		//String dietadvoice = request.getParameter("dietadvoice");
		
		ArrayList<DietaryDetails> dietplanlist = new ArrayList<DietaryDetails>();
		DietaryDetailsDAO dietaryDetailsDAO = new JDBCDietaryDetailsDAO(connection);
		
		//delete child table data
		int res = dietaryDetailsDAO.deleteplanfrmchild(parentid);
		
		dietplanlist = (ArrayList<DietaryDetails>)session.getAttribute("dietplanlist");
		for(DietaryDetails dietaryDetails:dietplanlist){
			String dietplan = dietaryDetails.getDietplan();
			String categoryid = dietaryDetailsDAO.getcatidfromname(dietaryDetails.getCategory()); 
			dietaryDetails.setCategoryid(categoryid);
				
			String subcategoryid = dietaryDetailsDAO.getsubcatidfromname(dietaryDetails.getSubcategory());
			dietaryDetails.setSubcategoryid(subcategoryid);
			int result1 = dietaryDetailsDAO.storedietaryplan(dietaryDetails,Integer.parseInt(parentid),addmissionid,null);				
		}	
		dietplanlist.clear();
	} catch (Exception e) {
		e.printStackTrace();
	}finally{
		connection.close();
	}
	return null;
}

public String getNewSubDiet()throws Exception {
	Connection connection = null;
	try {
		String id = request.getParameter("name");
		String i = request.getParameter("i");
		connection = Connection_provider.getconnection();
		DietaryDetailsDAO dietaryDetailsDAO=new JDBCDietaryDetailsDAO(connection);
		ArrayList<DietaryDetails> list = dietaryDetailsDAO.getcategorydetailslist(id);
		//String data=student.getRollno()+"~"+student.getName()+"~"+student.getAge()+"~"+student.getAddress();
		
		StringBuffer buffer=new StringBuffer();
		buffer.append("<select class='form-control showToolTip chosen-select' name='dietdata["+i+"].subcategory' id='e_subcategory"+i+"' onchange='selectNewSubCal(this.value,"+i+")''>");
		buffer.append("<option value='0'>Select Diet</option>");
		
		for(DietaryDetails details:list){
			 buffer.append("<option value='"+details.getId()+"'>"+details.getName()+"</option>");
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

public String getNewSubCaloris() throws Exception {
	Connection connection = null;
	try {
		String id = request.getParameter("id");
		String i = request.getParameter("i");
		connection = Connection_provider.getconnection();
		DietaryDetailsDAO dietaryDetailsDAO=new JDBCDietaryDetailsDAO(connection);
		ArrayList<DietaryDetails> list1 = dietaryDetailsDAO.getSelCalories(id);
		StringBuffer buffer=new StringBuffer();
		int newid=0;
		String temp ="";
		String protein="";
		
		
		/*buffer.append("<select class='form-control showToolTip chosen-select' name='dietdata["+i+"].calories' id='e_calories"+i+"'>");*/
		//buffer.append("<option value='0'>Select Calories</option>");
		for(DietaryDetails details:list1){
			newid =details.getId();
			temp = details.getEnergy();
			protein = details.getProtein();
			/* buffer.append("<option value='"+details.getId()+"'>"+details.getEnergy()+"</option>");*/
		}
		/*buffer.append("</select>");*/
		
		buffer.append("<input type='hidden'  name='dietdata["+i+"].calories' id='e_calories"+i+"' value='"+newid+"'>");
		buffer.append("<input type='text' readonly ='readonly' value='"+temp+"'>");
		
		
		buffer.append("~");
		
		buffer.append("<input type='hidden'  name='dietdata["+i+"].protein' id='p_protein"+i+"' value='"+newid+"'>");
		buffer.append("<input type='text' readonly ='readonly' value='"+protein+"'>");
		
		
		/*buffer.append("<select class='form-control showToolTip chosen-select' name='dietdata["+i+"].protein' id='p_protein"+i+"'>");
		//buffer.append("<option value='0'>Select Calories</option>");
		
		for(DietaryDetails details:list1){
			 buffer.append("<option value='"+details.getId()+"'>"+details.getProtein()+"</option>");
		}
		buffer.append("</select>");*/
		
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

public String updateDietData() throws Exception{
	Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			DietaryDetailsDAO detailsDAO = new JDBCDietaryDetailsDAO(connection);
			String parentid = dietaryDetailsForm.getPid();
			String ipdid = dietaryDetailsForm.getPatientid();
			for (DietaryDetails details : dietaryDetailsForm.getDietdata()) {
				if(details==null){
					continue;
				}
				String childid = details.getChildid();
				if(details.getChildid()==null)
					childid="0";
				
				if(childid.equals("0")){
					details.setCategoryid(details.getCategory());
					details.setSubcategoryid(details.getSubcategory());
					int result1 = detailsDAO.storedietaryplan(details,Integer.parseInt(parentid),ipdid,null);			
				}else{
					details.setCategoryid(details.getCategory());
					details.setSubcategoryid(details.getSubcategory());
					int res = detailsDAO.updateDietData(details,Integer.parseInt(parentid),ipdid,childid);
				}
			}
			ArrayList<DietaryDetails> dietplanlist =  new ArrayList<DietaryDetails>();
			dietplanlist = (ArrayList<DietaryDetails>)session.getAttribute("dietplanlist");
			dietplanlist.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "dietupdated";
	}


// Template Store Information

public String storeDietTemplate() throws Exception{
	Connection connection = null;
	
	try {
		connection = Connection_provider.getconnection();
		String addmissionid = request.getParameter("addmissionid");
		String templatename = request.getParameter("templatename");
		
		//String lang = request.getParameter("lang");
		//String dietadvoice = request.getParameter("dietadvoice");
		
		ArrayList<DietaryDetails> dietplanlist = new ArrayList<DietaryDetails>();

		DietaryDetailsDAO dietaryDetailsDAO = new JDBCDietaryDetailsDAO(connection);
		int result = dietaryDetailsDAO.storedietaryparentTemplate(addmissionid,templatename);
		int result1=0;
		if(result>0){
		dietplanlist = (ArrayList<DietaryDetails>)session.getAttribute("dietplanlist");
		for(DietaryDetails dietaryDetails:dietplanlist){
			//  24 oct 2017
				/*String dietplan = dietaryDetails.getDietplan();
				String categoryid = dietaryDetailsDAO.getcatidfromname(dietaryDetails.getCategory()); 
				dietaryDetails.setCategoryid(categoryid);
				
				String subcategoryid = dietaryDetailsDAO.getsubcatidfromname(dietaryDetails.getSubcategory());
				dietaryDetails.setSubcategoryid(subcategoryid);*/
				
//				String caloriesid = dietaryDetailsDAO.getcalidfromname(dietaryDetails.getCalories());
//				dietaryDetails.setCaloriesid(caloriesid);
				result1 = dietaryDetailsDAO.storeDietaryChildTemplate(dietaryDetails,result,addmissionid);				
		}	
		//dietplanlist.clear();
	}
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(result1);
		
	} catch (Exception e) {
		e.printStackTrace();
	}finally{
		connection.close();
	}
	return null;
}

public void getStoredTemplateData() throws Exception{
	Connection connection = null;
	String ipdid = request.getParameter("ipdid");
	String parentid = request.getParameter("parentid");
	try {
		connection = Connection_provider.getconnection();
		DietaryDetailsDAO dietaryDetailsDAO = new JDBCDietaryDetailsDAO(connection);
		
		DietaryDAO dietaryDAO = new JDBCDietaryDAO(connection);
		ArrayList<Dietary> categorylist = dietaryDAO.getctaegory();
		
		StringBuilder builder = new StringBuilder();
		
		ArrayList<DietaryDetails> dietplanlist = dietaryDetailsDAO.getTemplateDataFrmChild(parentid);
		
		int i = 0;
		for(DietaryDetails dietery1  : dietplanlist){
			ArrayList<DietaryDetails> subcategorylist = dietaryDetailsDAO.getcategorydetailslist(dietery1.getCategoryid());
			ArrayList<DietaryDetails> calorieslist = dietaryDetailsDAO.getSelCalories(dietery1.getCaloriesid());
			ArrayList<DietaryDetails> dietplanlist1 = dietaryDetailsDAO.getDietPlanList();
			ArrayList<DietaryDetails> dietfeedlist = dietaryDetailsDAO.getDietFeedList();
			
			builder.append("<tr>");
			builder.append("<td>");
			builder.append("<select name='dietdata["+i+"].dietplan' id='dietplan"+i+"' class='form-control showToolTip chosen-select'  data-original-title='' title=''>");
			for (DietaryDetails details1 : dietplanlist1) {
				if(dietery1.getDietplan().equals(""+details1.getId())){
					builder.append("<option value='"+details1.getId()+"' selected='selected'>"+details1.getName()+"</option>");
				}else
					builder.append("<option value='"+details1.getId()+"'>"+details1.getName()+"</option>");
			}
			/*if(dietery1.getDietplan().equals("Breakfast"))
				builder.append("<option value='Breakfast' selected='selected'>Breakfast</option>");
			else
				builder.append("<option value='Breakfast'>Breakfast</option>");
			
			if(dietery1.getDietplan().equals("Midmorning Snack"))
				builder.append("<option value='Midmorning Snack' selected='selected'>Midmorning Snack</option>");
			else
				builder.append("<option value='Midmorning Snack'>Midmorning Snack</option>");
			
			if(dietery1.getDietplan().equals("Lunch"))
				builder.append("<option value='Lunch' selected='selected'>Lunch</option>");
			else
				builder.append("<option value='Lunch'>Lunch</option>");
			
			if(dietery1.getDietplan().equals("Midafternoon Snack"))
				builder.append("<option value='Midafternoon Snack' selected='selected'>Midafternoon Snack</option>");
			else
				builder.append("<option value='Midafternoon Snack'>Midafternoon Snack</option>");
			
			if(dietery1.getDietplan().equals("Dinner"))
				builder.append("<option value='Dinner' selected='selected'>Dinner</option>");
			else
				builder.append("<option value='Dinner'>Dinner</option>");
			
			if(dietery1.getDietplan().equals("Midevening Snack"))
				builder.append("<option value='Midevening Snack' selected='selected'>Midevening Snack</option>");
			else
				builder.append("<option value='Midevening Snack'>Midevening Snack</option>");*/
			
			builder.append("</select>");
			builder.append("</td>");
			
			builder.append("<td>");
			builder.append("<select name='dietdata["+i+"].category' id='category"+i+"' onchange='selectTemplateSubDiet(this.value,"+i+")' class='form-control showToolTip chosen-select' data-original-title='' title=''>");
				for (Dietary details : categorylist) {
					if(dietery1.getCategory().equals(details.getName())){
						builder.append("<option value='"+details.getId()+"' selected='selected'>"+details.getName()+"</option>");
					}else
						builder.append("<option value='"+details.getId()+"'>"+details.getName()+"</option>");
				}
			builder.append("</select>");
			builder.append("</td>");
			
			builder.append("<td id='subcat"+i+"'>");
			builder.append("<select name='dietdata["+i+"].subcategory' id='subcategory"+i+"' class='form-control showToolTip chosen-select' onchange='selectTemplateSubCal(this.value,"+i+")' data-original-title='' title=''>");
				for (DietaryDetails details : subcategorylist) {
					if(dietery1.getSubcategory().equals(details.getName())){
						builder.append("<option value='"+details.getId()+"' selected='selected'>"+details.getName()+"</option>");
					}else
						builder.append("<option value='"+details.getId()+"'>"+details.getName()+"</option>");
				}
			builder.append("</select>");
			builder.append("</td>");
			
			
			
			
			builder.append("<td id='tdproo"+i+"'>");
			builder.append("<select name='dietdata["+i+"].protein' id='protein"+i+"' class='form-control showToolTip chosen-select' data-original-title='' title=''>");
				for (DietaryDetails details : calorieslist) {
					if(dietery1.getProtein().equals(details.getProtein())){
						builder.append("<option value='"+details.getId()+"' selected='selected'>"+details.getProtein()+"</option>");
					}else
						builder.append("<option value='"+details.getId()+"'>"+details.getProtein()+"</option>");
				}
			builder.append("</select>");
			builder.append("</td>");
			
			
			
			
			
			
			builder.append("<td id='subcal"+i+"'>");
			builder.append("<select name='dietdata["+i+"].calories' id='calories"+i+"' class='form-control showToolTip chosen-select' data-original-title='' title=''>");
				for (DietaryDetails details : calorieslist) {
					if(dietery1.getCalories().equals(details.getEnergy())){
						builder.append("<option value='"+details.getId()+"' selected='selected'>"+details.getEnergy()+"</option>");
					}else
						builder.append("<option value='"+details.getId()+"'>"+details.getEnergy()+"</option>");
				}
			builder.append("</select>");
			builder.append("</td>");
			
			builder.append("<td>");
			builder.append("<select name='dietdata["+i+"].feed' id='feed"+i+"' class='form-control showToolTip chosen-select' data-original-title='' title=''>");
			for (DietaryDetails details : dietfeedlist) {
				if(dietery1.getFeed().equals(""+details.getId())){
					builder.append("<option value='"+details.getId()+"' selected='selected'>"+details.getName()+"</option>");
				}else
					builder.append("<option value='"+details.getId()+"'>"+details.getName()+"</option>");
			}
			/*if(dietery1.getFeed().equals("15 Ml"))
				builder.append("<option value='15 Ml' selected='selected'>15 Ml</option>");
			else
				builder.append("<option value='15 ML'>15 ML</option>");
			
			if(dietery1.getFeed().equals("25 ML"))
				builder.append("<option value='25 ML' selected='selected'>25 ML</option>");
			else
				builder.append("<option value='25 ML'>25 ML</option>");
			
			if(dietery1.getFeed().equals("35 ML"))
				builder.append("<option value='35 ML' selected='selected'>35 ML</option>");
			else
				builder.append("<option value='35 ML'>35 ML</option>");
		
			if(dietery1.getFeed().equals("45 Ml"))
				builder.append("<option value='45 Ml' selected='selected'>45 Ml</option>");
			else
				builder.append("<option value='45 ML'>45 ML</option>");
			
			if(dietery1.getFeed().equals("55 ML"))
				builder.append("<option value='55 ML' selected='selected'>55 ML</option>");
			else
				builder.append("<option value='55 ML'>55 ML</option>");
		
			if(dietery1.getFeed().equals("1 Glass"))
				builder.append("<option value='1 Glass' selected='selected'>1 Glass</option>");
			else
				builder.append("<option value='1 Glass'>1 Glass</option>");
		
			if(dietery1.getFeed().equals("1 Bowl"))
				builder.append("<option value='1 Bowl' selected='selected'>1 Bowl</option>");
			else
				builder.append("<option value='1 Bowl'>1 Bowl</option>");
		
			if(dietery1.getFeed().equals("1 Katori"))
				builder.append("<option value='1 Katori' selected='selected'>1 Katori</option>");
			else
				builder.append("<option value='1 Katori'>1 Katori</option>");*/
			
			builder.append("</select>");
			builder.append("</td>");
		
			builder.append("<td>");
			builder.append("<select name='dietdata["+i+"].duration' id='duration"+i+"' class='form-control showToolTip chosen-select' data-original-title='' title=''>");
			if(dietery1.getDuration().equals("1"))
				builder.append("<option value='1' selected='selected'>1</option>");
			else
				builder.append("<option value='1'>1</option>");
			
			if(dietery1.getDuration().equals("2"))
				builder.append("<option value='2' selected='selected'>2</option>");
			else
				builder.append("<option value='2'>2</option>");
		
			if(dietery1.getDuration().equals("3"))
				builder.append("<option value='3' selected='selected'>3</option>");
			else
				builder.append("<option value='3'>3</option>");
			
			if(dietery1.getDuration().equals("4"))
				builder.append("<option value='4' selected='selected'>4</option>");
			else
				builder.append("<option value='4'>4</option>");
			
			builder.append("</select>");
			builder.append("</td>");
			
			/*str.append("<td><i onclick='showedit("+i+")' class='fa fa-edit' ></i></td/>");*/
			builder.append("<td><i onclick='deleteTemplateData("+i+")' style='cursor: pointer;' class='fa fa-trash-o' ></i></td/>");
			builder.append("<input type='hidden' id='tcount"+i+"' name='tcount"+i+"' value='"+i+"' />");
			builder.append("</tr>");
			i++;
		}
		int j = i+1;
		builder.append("~"+j+"");	
		builder.append("~");
		builder.append("<a href='#' type='button' onclick='addTemplateDiet()' title='Add' class='btn btn-primary'><i class='fa fa-plus'></i></a>");
		builder.append("~");
		//builder.append("<button type='button' class='btn btn-primary' onclick='saveUpdatedTemplate()'>Save Template</button>");
		builder.append("<button type='button' class='btn btn-primary' onclick='saveNewDiet()'>Save</button>");
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(builder.toString());
	} catch (Exception e) {
		e.printStackTrace();
	}finally{
		connection.close();
	}
}
public String getTemplateSubDiet()throws Exception {
	Connection connection = null;
	try {
		String id = request.getParameter("name");
		String i = request.getParameter("i");
		connection = Connection_provider.getconnection();
		DietaryDetailsDAO dietaryDetailsDAO=new JDBCDietaryDetailsDAO(connection);
		ArrayList<DietaryDetails> list = dietaryDetailsDAO.getcategorydetailslist(id);
		//String data=student.getRollno()+"~"+student.getName()+"~"+student.getAge()+"~"+student.getAddress();
		
		StringBuffer buffer=new StringBuffer();
		buffer.append("<select class='form-control showToolTip chosen-select' name='dietdata["+i+"].subcategory' id='subcategory"+i+"' onchange='selectTemplateSubCal(this.value,"+i+")''>");
		//buffer.append("<option value='0'>Select Diet</option>");
		
		for(DietaryDetails details:list){
			 buffer.append("<option value='"+details.getId()+"'>"+details.getName()+"</option>");
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

public String getTemplateSubCaloris() throws Exception {
	Connection connection = null;
	try {
		String id = request.getParameter("id");
		String i = request.getParameter("i");
		connection = Connection_provider.getconnection();
		DietaryDetailsDAO dietaryDetailsDAO=new JDBCDietaryDetailsDAO(connection);
		ArrayList<DietaryDetails> list1 = dietaryDetailsDAO.getSelCalories(id);
		StringBuffer buffer=new StringBuffer();
		
		buffer.append("<select class='form-control showToolTip chosen-select' name='dietdata["+i+"].calories' id='calories"+i+"'>");
		buffer.append("<option value='0'>Select Calories</option>");
		for(DietaryDetails details:list1){
			 buffer.append("<option value='"+details.getId()+"'>"+details.getEnergy()+"</option>");
		}
		buffer.append("</select>");
		
		buffer.append("~");
		
		buffer.append("<select name='dietdata["+i+"].protein' id='protein"+i+"' class='form-control showToolTip chosen-select' data-original-title='' title=''>");
		for (DietaryDetails details : list1) {
				buffer.append("<option value='"+details.getId()+"'>"+details.getProtein()+"</option>");
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

public String addTemplateDietData() throws Exception{
	Connection connection = null;
	try {
		DietaryDetails dietaryDetails = new DietaryDetails();
		String dietplan = request.getParameter("dietplan");
		String category_id = request.getParameter("category");
		String subcategory_id = request.getParameter("subcategory");
		String calories_id = request.getParameter("calories");
		String feed = request.getParameter("feed");
		String duration = request.getParameter("duration");
		String category = "";
		String calories = "";
		String subcategory = "";
		String counts = request.getParameter("counts");
		int i = Integer.parseInt(counts);
		connection = Connection_provider.getconnection();
		DietaryDetailsDAO dietaryDetailsDAO = new JDBCDietaryDetailsDAO(connection);
		DietaryDAO dietaryDAO = new JDBCDietaryDAO(connection);
		
		DietaryDetails obj = dietaryDetailsDAO.getcategoryname(category_id);
		category = obj.getName();
		DietaryDetails obj1 = dietaryDetailsDAO.getSubcategoryname(subcategory_id);
		subcategory = obj1.getName();
		DietaryDetails obj2 = dietaryDetailsDAO.getEnergyname(calories_id);
		calories = obj2.getEnergy();
		
		ArrayList<Dietary> categorylist = dietaryDAO.getctaegory();
		ArrayList<DietaryDetails> subcategorylist = dietaryDetailsDAO.getcategorydetailslist(category_id);
		ArrayList<DietaryDetails> calorieslist = dietaryDetailsDAO.getSelCalories(calories_id);;
		ArrayList<DietaryDetails> dietplanlist = dietaryDetailsDAO.getDietPlanList();
		ArrayList<DietaryDetails> dietfeedlist = dietaryDetailsDAO.getDietFeedList();
		StringBuilder str = new StringBuilder();
		
		str.append("<tr>");
		str.append("<td>");
		str.append("<select name='dietdata["+i+"].dietplan' id='dietplan"+i+"' class='form-control showToolTip chosen-select'  data-original-title='' title=''>");
		for (DietaryDetails details1 : dietplanlist) {
			if(dietplan.equals(""+details1.getId())){
				str.append("<option value='"+details1.getId()+"' selected='selected'>"+details1.getName()+"</option>");
			}else
				str.append("<option value='"+details1.getId()+"'>"+details1.getName()+"</option>");
		}
		/*if(dietplan.equals("Breakfast"))
			str.append("<option value='Breakfast' selected='selected'>Breakfast</option>");
		else
			str.append("<option value='Breakfast'>Breakfast</option>");
		
		if(dietplan.equals("Midmorning Snack"))
			str.append("<option value='Midmorning Snack' selected='selected'>Midmorning Snack</option>");
		else
			str.append("<option value='Midmorning Snack'>Midmorning Snack</option>");
		
		if(dietplan.equals("Lunch"))
			str.append("<option value='Lunch' selected='selected'>Lunch</option>");
		else
			str.append("<option value='Lunch'>Lunch</option>");
		
		if(dietplan.equals("Midafternoon Snack"))
			str.append("<option value='Midafternoon Snack' selected='selected'>Midafternoon Snack</option>");
		else
			str.append("<option value='Midafternoon Snack'>Midafternoon Snack</option>");
		
		if(dietplan.equals("Dinner"))
			str.append("<option value='Dinner' selected='selected'>Dinner</option>");
		else
			str.append("<option value='Dinner'>Dinner</option>");
		
		if(dietplan.equals("Midevening Snack"))
			str.append("<option value='Midevening Snack' selected='selected'>Midevening Snack</option>");
		else
			str.append("<option value='Midevening Snack'>Midevening Snack</option>");*/
		
		str.append("</select>");
		str.append("</td>");
		
		str.append("<td>");
		str.append("<select name='dietdata["+i+"].category' id='category"+i+"' onchange='selectTemplateSubDiet(this.value,"+i+")' class='form-control showToolTip chosen-select' data-original-title='' title=''>");
			for (Dietary details : categorylist) {
				if(category.equals(details.getName())){
					str.append("<option value='"+details.getId()+"' selected='selected'>"+details.getName()+"</option>");
				}else
					str.append("<option value='"+details.getId()+"'>"+details.getName()+"</option>");
			}
		str.append("</select>");
		str.append("</td>");
		
		
		str.append("<td>");
		str.append("<select name='dietdata["+i+"].feed' id='feed"+i+"' class='form-control showToolTip chosen-select' data-original-title='' title=''>");
		for (DietaryDetails details : dietfeedlist) {
			if(feed.equals(""+details.getId())){
				str.append("<option value='"+details.getId()+"' selected='selected'>"+details.getName()+"</option>");
			}else
				str.append("<option value='"+details.getId()+"'>"+details.getName()+"</option>");
		}
		
		str.append("</select>");
		str.append("</td>");
		
		
		str.append("<td id='subcat"+i+"'>");
		str.append("<select name='dietdata["+i+"].subcategory' id='subcategory"+i+"' class='form-control showToolTip chosen-select' onchange='selectTemplateSubCal(this.value,"+i+")' data-original-title='' title=''>");
			for (DietaryDetails details : subcategorylist) {
				if(subcategory.equals(details.getName())){
					str.append("<option value='"+details.getId()+"' selected='selected'>"+details.getName()+"</option>");
				}else
					str.append("<option value='"+details.getId()+"'>"+details.getName()+"</option>");
			}
		str.append("</select>");
		str.append("</td>");
		
		str.append("<td id='subcal"+i+"'>");
		str.append("<select name='dietdata["+i+"].calories' id='calories"+i+"' class='form-control showToolTip chosen-select' data-original-title='' title=''>");
			for (DietaryDetails details : calorieslist) {
				if(calories.equals(details.getEnergy())){
					str.append("<option value='"+details.getId()+"' selected='selected'>"+details.getEnergy()+"</option>");
				}else
					str.append("<option value='"+details.getId()+"'>"+details.getEnergy()+"</option>");
			}
		str.append("</select>");
		str.append("</td>");
		
		/*str.append("<td>");
		str.append("<select name='dietdata["+i+"].feed' id='feed"+i+"' class='form-control showToolTip chosen-select' data-original-title='' title=''>");
		for (DietaryDetails details : dietfeedlist) {
			if(feed.equals(""+details.getId())){
				str.append("<option value='"+details.getId()+"' selected='selected'>"+details.getName()+"</option>");
			}else
				str.append("<option value='"+details.getId()+"'>"+details.getName()+"</option>");
		}*/
		/*if(feed.equals("15 Ml"))
			str.append("<option value='15 Ml' selected='selected'>15 Ml</option>");
		else
			str.append("<option value='15 ML'>15 ML</option>");
		
		if(feed.equals("25 ML"))
			str.append("<option value='25 ML' selected='selected'>25 ML</option>");
		else
			str.append("<option value='25 ML'>25 ML</option>");
		
		if(feed.equals("35 ML"))
			str.append("<option value='35 ML' selected='selected'>35 ML</option>");
		else
			str.append("<option value='35 ML'>35 ML</option>");
	
		if(feed.equals("45 Ml"))
			str.append("<option value='45 Ml' selected='selected'>45 Ml</option>");
		else
			str.append("<option value='45 ML'>45 ML</option>");
		
		if(feed.equals("55 ML"))
			str.append("<option value='55 ML' selected='selected'>55 ML</option>");
		else
			str.append("<option value='55 ML'>55 ML</option>");
	
		if(feed.equals("1 Glass"))
			str.append("<option value='1 Glass' selected='selected'>1 Glass</option>");
		else
			str.append("<option value='1 Glass'>1 Glass</option>");
	
		if(feed.equals("1 Bowl"))
			str.append("<option value='1 Bowl' selected='selected'>1 Bowl</option>");
		else
			str.append("<option value='1 Bowl'>1 Bowl</option>");
	
		if(feed.equals("1 Katori"))
			str.append("<option value='1 Katori' selected='selected'>1 Katori</option>");
		else
			str.append("<option value='1 Katori'>1 Katori</option>");*/
		
		/*str.append("</select>");
		str.append("</td>");*/
	
		str.append("<td>");
		str.append("<select name='dietdata["+i+"].duration' id='duration"+i+"' class='form-control showToolTip chosen-select' data-original-title='' title=''>");
		if(duration.equals("1"))
			str.append("<option value='1' selected='selected'>1</option>");
		else
			str.append("<option value='1'>1</option>");
		
		if(duration.equals("2"))
			str.append("<option value='2' selected='selected'>2</option>");
		else
			str.append("<option value='2'>2</option>");
	
		if(duration.equals("3"))
			str.append("<option value='3' selected='selected'>3</option>");
		else
			str.append("<option value='3'>3</option>");
		
		if(duration.equals("4"))
			str.append("<option value='4' selected='selected'>4</option>");
		else
			str.append("<option value='4'>4</option>");
		
		str.append("</select>");
		str.append("</td>");

		
		str.append("<td><i onclick='deleteTemplateData("+i+")' style='cursor: pointer;' class='fa fa-trash-o' ></i></td/>");
		str.append("</tr>");
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+str.toString()+""); 

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
}

public String saveDietPlan() throws Exception{
	Connection connection = null;
	
	try {
		connection = Connection_provider.getconnection();
		String addmissionid = dietaryDetailsForm.getPatient_id();
		//ArrayList<DietaryDetails> dietplanlist = new ArrayList<DietaryDetails>();

		DietaryDetailsDAO dietaryDetailsDAO = new JDBCDietaryDetailsDAO(connection);
		String date = request.getParameter("date");
		date=request.getParameter("fromDate");
		if(date!=null){
			if(!date.equals(""))
		date = DateTimeUtils.getCommencingDate1(date);
		}if(date==null){
			date="";
		}
		if(date.equals("")){
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance(); 
			date = dateFormat.format(cal.getTime());
		}
		
		int result = dietaryDetailsDAO.storedietaryparent(addmissionid,date,"00:00:00");
		
		if(result>0){
		for(DietaryDetails dietaryDetails : dietaryDetailsForm.getDietdata()){
				if(dietaryDetails==null){
					continue;
				}
				dietaryDetails.setCategoryid(dietaryDetails.getCategory());
				
				dietaryDetails.setSubcategoryid(dietaryDetails.getSubcategory());
				
//				String caloriesid = dietaryDetailsDAO.getcalidfromname(dietaryDetails.getCalories());
//				dietaryDetails.setCaloriesid(caloriesid);
				int result1 = dietaryDetailsDAO.storedietaryplan(dietaryDetails,result,addmissionid,date);				
		}	
		//dietplanlist.clear();
	}
		
	} catch (Exception e) {
		e.printStackTrace();
	}finally{
		connection.close();
	}
	return "dataSave";
}

public String storeUpdatedTemplate() throws Exception{
	Connection connection = null;
	
	try {
		connection = Connection_provider.getconnection();
		String addmissionid = request.getParameter("addmissionid");
		String templatename = request.getParameter("templatename");
	
		DietaryDetailsDAO dietaryDetailsDAO = new JDBCDietaryDetailsDAO(connection);
		int result = dietaryDetailsDAO.storedietaryparentTemplate(addmissionid,templatename);
		int result1=0;
		if(result>0){
		for(DietaryDetails dietaryDetails : dietaryDetailsForm.getDietdata()){
				String dietplan = dietaryDetails.getDietplan();
				String categoryid = dietaryDetails.getCategory(); 
				dietaryDetails.setCategoryid(categoryid);
				
				String subcategoryid = dietaryDetails.getSubcategory();
				dietaryDetails.setSubcategoryid(subcategoryid);
				
				result1 = dietaryDetailsDAO.storeDietaryChildTemplate(dietaryDetails,result,addmissionid);				
		}	
	}
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(result1);
		
	} catch (Exception e) {
		e.printStackTrace();
	}finally{
		connection.close();
	}
	return null;
}

public String generaldietplan() throws Exception{
	 Connection connection = null;
	 try {
	  connection = Connection_provider.getconnection();
	  BedDao bedDao = new JDBCBedDao(connection);
	  DietaryDAO dietaryDAO = new  JDBCDietaryDAO(connection);
	  String allward =request.getParameter("allward");
	  
	   if(allward==null){
		  
		   allward="0";		  
	   }else  if(allward.equals("")){
		   allward="0";	
	   }
	  ArrayList<Bed>bedlist = dietaryDAO.getAllIpdDetailsForDiet(allward);
	  dietaryDetailsForm.setBedlist(bedlist);
	 Bed bed1= bedlist.get(1);
	 
	 dietaryDetailsForm.setDietician_incharge(bed1.getDietician_incharge());
	  ArrayList<Bed>wardlistname = bedDao.getAllWardList("0");
	  dietaryDetailsForm.setWardlistname(wardlistname);
	  DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy ");
	  
      Calendar cal = Calendar.getInstance();
      String date = dateFormat.format(cal.getTime());  
      dietaryDetailsForm.setFromdate(date);
	 } catch (Exception e) {
	  e.printStackTrace();
	 }
	 return "generaldiet";
	 
	}

	public String savegeneraldietplan() throws Exception{
	 Connection connection = null;
	 
	 try {
		 String fromdate = dietaryDetailsForm.getFromdate();
			String todate = dietaryDetailsForm.getTodate();
	  connection = Connection_provider.getconnection();
	  DietaryDAO dietaryDAO = new  JDBCDietaryDAO(connection);
	  ArrayList<Bed>bedlist = dietaryDAO.getAllIpdDetailsForDiet("0");
	  DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  
	       Calendar cal = Calendar.getInstance();
	       String date = dateFormat.format(cal.getTime());  
	       String dietshift = request.getParameter("diettimeshift");
	      String dietician_incharge = request.getParameter("dietician_incharge");
	       String id = request.getParameter("id");
	       String userid= loginInfo.getUserId();
	       String allward =request.getParameter("allward");
	       if(allward==null){
			   allward="0";		   }
	     
	       
	     /* String  result1 = dietaryDAO.getPreviousDietTimeShift(fromdate,todate,dietshift);*/
	       int result = dietaryDAO.saveGeneralDietPlanparent(date,userid,dietshift,dietician_incharge);
	   
	  for(Bed bed:bedlist){
	   String remark = request.getParameter("remark"+bed.getId());
	   String diettimeshift = dietshift;
	   String diaetplan=request.getParameter("diaetplan"+bed.getId());
	   /*String dietician_incharge = diet_incharge;*/
	   int res = dietaryDAO.saveGeneralDietPlan(bed,diaetplan,remark,date,diettimeshift,result);
	  }
	   
	  /*for(DietaryDetails dietaryDetails:dietaryDetailsForm.getDietaryDetails()){
	   String diaetplan= dietaryDetails.getChildid();
	   String ipdid= dietaryDetails.getIpdid();
	   
	   System.out.println("od"+diaetplan+ipdid);
	  }
	  */
	 
	  session.setAttribute("adarsh", ""+result);
	 } catch (Exception e) {
	  e.printStackTrace();
	 }
	 return "generaldietplan";
	}

	public String printgeneraldietplan() throws Exception{
	 Connection connection = null;
	 try {
		 
		 /* String diettimeshift = request.getParameter("diettimeshift");*/
		 String filter_status = request.getParameter("filter_status");
		   String date = request.getParameter("date");
		   String printnew = request.getParameter("printnew");
		   if(printnew!=null){
			   if(printnew.equals("1")){
				   dietaryDetailsForm.setPrintnew("1");  
			   }
		   }else{dietaryDetailsForm.setPrintnew("0");}
		   if(date!=null){
		   dietaryDetailsForm.setDate(date);
		   }
		   if(date==null){
			 date=  dietaryDetailsForm.getDate();
		   }
		  String id = request.getParameter("id");
		   String allward =request.getParameter("allward");
		   /*String dietician_incharge = request.getParameter("dietician_incharge");*/
		   if(allward==null){
			   allward="0";		   }
		   
		  if(id==null){
			  id = (String) session.getAttribute("adarsh");
		  }else if(id.equals("")){
			  id = (String) session.getAttribute("adarsh");
		  }
		   if(filter_status!=null){
		    if(filter_status.equals("")){
		     filter_status="0";
		    }
		   }else {
		    filter_status="0";
		   }
		   if(date!=null){
		   if(date.equals("")){
		     DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		          Calendar cal = Calendar.getInstance();
		          date = dateFormat.format(cal.getTime()); 
		   }
		   }else{
		     DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		          Calendar cal = Calendar.getInstance();
		        date = dateFormat.format(cal.getTime()); 
		   }
		   
	  connection = Connection_provider.getconnection();
	  BedDao bedDao = new JDBCBedDao(connection);
	  DietaryDAO dietaryDAO = new  JDBCDietaryDAO(connection);
	 String diettimeshift = dietaryDAO.getDiettimeName(id);
	 String dietician_incharge = dietaryDAO.getDieticianName(id);
	  ArrayList<Bed>generaldietplanlist = dietaryDAO.printgeneraldietplan(id,date,filter_status,allward);
	  dietaryDetailsForm.setId(Integer.parseInt(id));
	  dietaryDetailsForm.setGeneraldietplanlist(generaldietplanlist);
	  dietaryDetailsForm.setDiettimeshift(diettimeshift);
	  dietaryDetailsForm.setDietician_incharge(dietician_incharge);
	  /*dietaryDetailsForm.setDietician_incharge(dietician_incharge);*/
	  ArrayList<Bed>wardlistname = bedDao.getAllWardList("0");
	  if(generaldietplanlist.size()!=0){
	 for(Bed bed :generaldietplanlist){
		 bed= generaldietplanlist.get(generaldietplanlist.size()-1);
		
		 dietaryDetailsForm.setFulldiet(bed.getFulldiet());
		 dietaryDetailsForm.setSoftdiet(bed.getSoftdiet());
		 dietaryDetailsForm.setLiquiddiet(bed.getLiquiddiet());
		 dietaryDetailsForm.setDiabetic(bed.getDiabetic());
		 dietaryDetailsForm.setRtfeed(bed.getRtfeed());
		 dietaryDetailsForm.setRenal(bed.getRenal());
		 dietaryDetailsForm.setHepatic(bed.getHepatic());
		 dietaryDetailsForm.setClearliquid(bed.getClearliquid());
		 dietaryDetailsForm.setSemisolid(bed.getSemisolid());
		 dietaryDetailsForm.setBlended(bed.getBlended());
		 dietaryDetailsForm.setNbm(bed.getNbm());
	 }
	 }
	  
	  for (Bed bed : wardlistname) {
		  if(allward.equals("0")){
			  bed.setStatus("0");
		  }else{
			  	for (String string : allward.split(",")) {
					if(string.equals("0")){
						continue;
					}else{
						if(string.equals(String.valueOf(bed.getId()))){
							bed.setStatus("1");
							break;
						}
					}
				}
		  }
	  }
	  dietaryDetailsForm.setWardlistname(wardlistname);
	  
	  
	  
	  Clinic clinic = new Clinic();
		ClinicDAO clinicDAO  = new JDBCClinicDAO(connection);
		clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
		dietaryDetailsForm.setClinicName(clinic.getClinicName());
		dietaryDetailsForm.setClinicOwner(clinic.getClinicOwner());
		dietaryDetailsForm.setOwner_qualification(clinic.getOwner_qualification());
		dietaryDetailsForm.setLandLine(clinic.getLandLine());
		dietaryDetailsForm.setClinicemail(clinic.getEmail());
		dietaryDetailsForm.setWebsiteUrl(clinic.getWebsiteUrl());
		dietaryDetailsForm.setClinicLogo(clinic.getUserImageFileName());
		dietaryDetailsForm.setAllward(allward);
		
	 } catch (Exception e) {
	  e.printStackTrace();
	 }
	 return "printgeneraldietplan";
	}
	public String showgeneraldietplan() throws Exception{
		  Connection connection = null;
		  try {
		   connection = Connection_provider.getconnection();
		   String searchtext = dietaryDetailsForm.getSearchtext();
			String fromdate = dietaryDetailsForm.getFromdate();
			String todate = dietaryDetailsForm.getTodate();
//			String wardnameid = dietaryDetailsForm.getWardnameid();
			
		
	
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
		     DietaryDAO dietaryDAO = new  JDBCDietaryDAO(connection);
		     ArrayList<Bed>showgeneraldietplanlist = dietaryDAO.showgeneraldietplanlist(fromdate,todate);
		     dietaryDetailsForm.setShowgeneraldietplanlist(showgeneraldietplanlist);
		    
		    
			  
			  ArrayList<Bed>bedlist = dietaryDAO.getAllIpdDetailsForDiet("0");
			  dietaryDetailsForm.setBedlist(bedlist);
		     fromdate = DateTimeUtils.getCommencingDate1(fromdate);
				todate = DateTimeUtils.getCommencingDate1(todate);
				dietaryDetailsForm.setFromdate(fromdate);
				dietaryDetailsForm.setTodate(todate);
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
		  
		   return "showgeneraldietplan";
		  
		 }
	public String editgeneraldietplan() throws Exception{
		 Connection connection = null;
		 try {
			
			 connection = Connection_provider.getconnection();
			 String id = request.getParameter("id");
			 DietaryDAO dietaryDAO = new  JDBCDietaryDAO(connection);
			 ArrayList<Bed>bedlist = dietaryDAO.getEditIpdDetailsForDiet(id);
			 dietaryDetailsForm.setBedlist(bedlist);
			 dietaryDetailsForm.getDate();
			 String date = request.getParameter("date");
			 dietaryDetailsForm.setDate( date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "editgeneraldietplan";
		
	}
	public String updategeneraldietplan() throws Exception{
		Connection connection = null;
		try {
			 String filter_status = request.getParameter("filter_status");
			   String date = request.getParameter("date");
			connection = Connection_provider.getconnection();
			 DietaryDAO dietaryDAO = new  JDBCDietaryDAO(connection);
			 ArrayList<Bed>bedlist = dietaryDAO.getEditIpdDetailsForDiet(request.getParameter("id"));
			String id = request.getParameter("id");
			   if(filter_status!=null){
				    if(filter_status.equals("")){
				     filter_status="0";
				    }
				   }else {
				    filter_status="0";
				   }
				   if(date!=null){
				   if(date.equals("")){
				     DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				          Calendar cal = Calendar.getInstance();
				          date = dateFormat.format(cal.getTime()); 
				   }
				   }else{
				     DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				          Calendar cal = Calendar.getInstance();
				        date = dateFormat.format(cal.getTime()); 
				   }
				   
			int result=0;
			 for(Bed bed:bedlist){
				   String remark = request.getParameter("remark"+bed.getId());
				/*   String diettimeshift = dietshift;*/
				   String diaetplan=request.getParameter("diaetplan"+bed.getId());
				  
				   bed.setRemark(remark);
				   bed.setDiaetplan(diaetplan);
				   
			  result=dietaryDAO.updateGeneralDietPlan(id, bed);
			 }
			 
			 String diettimeshift = dietaryDAO.getDiettimeName(id);
			 String dietician_incharge = request.getParameter("dietician_incharge");
			  ArrayList<Bed>generaldietplanlist = dietaryDAO.printgeneraldietplan(id,date,filter_status,"0");
			  dietaryDetailsForm.setGeneraldietplanlist(generaldietplanlist);
			  dietaryDetailsForm.setDiettimeshift(diettimeshift);
			 /* dietaryDetailsForm.setDietician_incharge(dietician_incharge);;*/
			  session.setAttribute("adarsh", ""+result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "generaldietplan";
	}
	public String savediettimehift() throws Exception{
		Connection connection = null;
		String result="";
		try {
			String fromdate = dietaryDetailsForm.getFromdate();
			String todate = dietaryDetailsForm.getTodate();
			if(fromdate!=null){
				   if(fromdate.equals("")){
				     DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				          Calendar cal = Calendar.getInstance();
				          fromdate = dateFormat.format(cal.getTime()); 
				   }
				   }else{
				     DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				          Calendar cal = Calendar.getInstance();
				          fromdate = dateFormat.format(cal.getTime()); 
				   }
			if(todate!=null){
				   if(todate.equals("")){
				     DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				          Calendar cal = Calendar.getInstance();
				          todate = dateFormat.format(cal.getTime()); 
				   }
				   }else{
				     DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				          Calendar cal = Calendar.getInstance();
				          todate = dateFormat.format(cal.getTime()); 
				   }
			connection = Connection_provider.getconnection();
			
			
			String dietshift = request.getParameter("diettimeshift");
			 DietaryDAO dietaryDAO = new  JDBCDietaryDAO(connection);
			  result = dietaryDAO.getPreviousDietTimeShift(fromdate,todate,dietshift);
			  response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(result);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
}
