 package com.apm.Ipd.web.action;

import java.io.BufferedReader;
import java.nio.Buffer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.eclipse.jetty.io.NetworkTrafficListener.Empty;

import com.apm.Accounts.eu.bi.AccountsDAO;
import com.apm.Accounts.eu.bi.ChargesAccountProcessingDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAccountsDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCChargeAccountProcesDAO;
import com.apm.Accounts.eu.entity.Accounts;
import com.apm.Appointment.eu.bi.AppointmentTypeDAO;
import com.apm.Appointment.eu.blogic.jdbc.JDBCAppointmentTypeDAO;
import com.apm.AssesmentForms.eu.bi.AssessmentFormDAO;
import com.apm.AssesmentForms.eu.blogic.jdbc.JDBCAssessmentFormDAO;
import com.apm.Diagnosis.eu.bi.DiagnosisDAO;
import com.apm.Diagnosis.eu.blogic.jdbc.JDBCDiagnosisDAO;
import com.apm.Diagnosis.eu.entity.Diagnosis;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.bi.CompleteAptmDAO;
import com.apm.DiaryManagement.eu.bi.DiaryManagementDAO;
import com.apm.DiaryManagement.eu.bi.NotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCCompleteAptmDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCDiaryManagentDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCNotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.CompleteAppointment;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.DiaryManagement.web.action.SmsService;
import com.apm.Emr.eu.bi.EmrDAO;
import com.apm.Emr.eu.bi.InvestigationDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCEmrDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCInvestigationDAO;
import com.apm.Emr.eu.entity.Investigation;
import com.apm.Emr.eu.entity.InvstTemplate;
import com.apm.Inventory.eu.bi.InventoryVendorDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryVendorDAO;
import com.apm.Inventory.eu.entity.Product;
import com.apm.Ipd.eu.bi.BedDao;
import com.apm.Ipd.eu.bi.IpdDAO;
import com.apm.Ipd.eu.bi.IpdLogDAO;
import com.apm.Ipd.eu.blogic.jdbc.JDBCBedDao;
import com.apm.Ipd.eu.blogic.jdbc.JDBCIpdDAO;
import com.apm.Ipd.eu.blogic.jdbc.JDBCIpdLogDAO;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Ipd.eu.entity.Ipd;
import com.apm.Ipd.web.form.IpdForm;
import com.apm.Log.eu.entity.EmailLetterLog;
import com.apm.Master.eu.bi.DeclarationDAO;
import com.apm.Master.eu.bi.DischargeOutcomeDAO;
import com.apm.Master.eu.bi.DischargeStatusDAO;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.bi.TreatmentTypeDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCDeclarationDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCDischargeOutcomeDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCDischargeStatus;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCTreatmentTypeDAO;
import com.apm.Master.eu.entity.Declaration;
import com.apm.Master.eu.entity.Discharge;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.TreatmentType;
import com.apm.Pharmacy.eu.bi.PharmacyDAO;
import com.apm.Pharmacy.eu.blogic.jdbc.JDBCPharmacyDAO;
import com.apm.Registration.eu.bi.ClinicDAO;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCClinicDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.Location;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.ThirdParties.eu.bi.ThirdPartyDAO;
import com.apm.ThirdParties.eu.blogic.jdbc.JDBCThirdPartyDAO;
import com.apm.ThirdParties.eu.entity.ThirdParty;
import com.apm.Tools.eu.bi.SMSTemplateDAO;
import com.apm.Tools.eu.blogic.jdbc.JDBCSMSTemplateDao;
import com.apm.Tools.eu.entity.EmailTemplate;
import com.apm.Tools.web.action.AllTemplateAction;
import com.apm.TreatmentEpisode.eu.bi.TreatmentEpisodeDAO;
import com.apm.TreatmentEpisode.eu.blogic.jdbc.JDBCTreatmentEpisode;
import com.apm.TreatmentEpisode.eu.entity.TreatmentEpisode;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.common.web.utils.PopulateList;
import com.apm.main.common.constants.Constants;
import com.onbarcode.barcode.Code39;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import atg.taglib.json.util.JSONObject;
import oracle.sql.DATE;



public class IpdAction extends BaseAction implements ModelDriven<IpdForm>,Preparable{
	
	
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	
	IpdForm ipdForm = new IpdForm();
	
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}
	
	public String admitted()throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection = null;
		String clientid = request.getParameter("clientid");
		try{
			connection = Connection_provider.getconnection();
			AssessmentFormDAO assessmentFormDAO = new JDBCAssessmentFormDAO(connection);
			int bedid = assessmentFormDAO.getIpdBedno(clientid);
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+bedid+"");
			
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			connection.close();
		}
		
		
		return null;
	}
	
	public String dec() throws Exception{
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection = null;
		
		String id = ipdForm.getHdndecid();
		String ipdid = ipdForm.getIpdid();
		Declaration declaration = new Declaration();
		try{
			connection = Connection_provider.getconnection();
			DeclarationDAO declarationDAO = new JDBCDeclarationDAO(connection);
			declaration = declarationDAO.getDeclaration(Integer.parseInt(id),declaration);
			session.setAttribute("declarationNotes", declaration.getDeclarationNotes());
			printformdata(ipdid);
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			connection.close();
		}
		//return print();
		return "printForm";
	}
	
public String barcode()throws Exception{
		
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection = null;
		String selectedid = request.getParameter("selectedid");
		
		try{
			connection = Connection_provider.getconnection();
			
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			Code39 code128 = new Code39();
			code128.setBarcodeWidth(3);
			
			
			String howmany= request.getParameter("howmany");
			
			if(howmany!=null){
				 if(howmany.equals("")){
					  howmany="0";
				 }
			} else {
				howmany="0";
			}
			
			
			Client client = clientDAO.getClientDetails(selectedid);
			ArrayList<Client>totalBarcodeList = new ArrayList<Client>();
			String clientname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();
			
			if(howmany.equals("0")){
					
				for(int i=1;i<=1;i++){
					
					client.setClientName(clientname);
					 code128.setData(selectedid);
					 String filePath = request.getRealPath("/barcode/"+client.getId()+i+".gif");
					 code128.drawBarcode(filePath);
					 client.setImageName(client.getId()+""+i+""+".gif");
					 client.setCompanyName(loginInfo.getClinicName());
					 
					 if(client.getWhopay()==null){
						  
						 client.setWhopay("Self");
					 }
					 if(client.getWhopay().equals(Constants.PAY_BY_CLIENT) || client.getWhopay().equals("Self")){
						 client.setWhopay("Self");
					 }else{
						 ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
						 ThirdParty thirdParty = thirdPartyDAO.getThirdPartyDetails(client.getTypeName());
						 String tpname = thirdParty.getShortname();
						 if(tpname==null){
							 tpname=thirdParty.getCompanyName();
						 }
						 client.setTpName(tpname);
						 client.setWhopay(tpname);
					 }
					 
					 if(client.getGender().equals("Male") || client.getGender().equals("M")){
						 client.setGender("M");
					 }else{
						 client.setGender("F");
					 }
					
					totalBarcodeList.add(client);
				}
				
			} else {
			
				for(int i=1;i<=12;i++){
					
					client.setClientName(clientname);
					 code128.setData(selectedid);
					 String filePath = request.getRealPath("/barcode/"+client.getId()+i+".gif");
					 code128.drawBarcode(filePath);
					 client.setImageName(client.getId()+""+i+""+".gif");
					 client.setCompanyName(loginInfo.getClinicName());
				    
					 if(client.getWhopay()==null){
						  
						 client.setWhopay("Self");
					 }
				 	  
					 if(client.getWhopay().equals(Constants.PAY_BY_CLIENT) || client.getWhopay().equals("Self")){
						 client.setWhopay("Self");
					 }else{
						 ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
						 ThirdParty thirdParty = thirdPartyDAO.getThirdPartyDetails(client.getTypeName());
						 String tpname = thirdParty.getShortname();
						 if(tpname==null){
							 tpname=thirdParty.getCompanyName();
						 }
						 client.setTpName(tpname);
						 client.setWhopay(tpname);
					 }
					 
					 if(client.getGender()==null){
						 client.setGender("M");
					 }
					 
					 if(client.getGender().equals("Male") || client.getGender().equals("M")){
						 client.setGender("M");
					 }else{
						 client.setGender("F");
					 }
					
					totalBarcodeList.add(client);
				}
			}
			
			
			
			
			session.setAttribute("totalBarcodeList", totalBarcodeList);
			ipdForm.setSelectedid(selectedid);
			session.setAttribute("howmany", howmany);
			
			
			if(howmany.equals("0")){
				return "singlebarcode";
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		return "barcode";
	}
	
//This method now called from BookappointmentAjaxAction  17 April 2018  
	public String bed()throws Exception{
		if(!verifyLogin(request)) {
    		
    		return "login";
    	}
		
		String wardid = request.getParameter("wid");
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			ArrayList<Bed>bedList = ipdDAO.getWardWiseBedList(wardid);
			
			StringBuffer str = new StringBuffer();
			str.append("<select id='bedid' name='bedid' class='form-control'>");
			str.append("<option value='0'>Select Bed</option>");
			
			for(Bed bed : bedList){
				str.append("<option value='"+bed.getId()+"'>"+bed.getBedname()+"</option>");
			}
			
			str.append("</select>");
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+str.toString()+"");
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		return null;
	}
	
	public String input() throws Exception{
		
		if(!verifyLogin(request)) {
    		
    		return "login";
    	}
    	
		String wardid = request.getParameter("wardid");
		String bedid = request.getParameter("bedid");
		String action = request.getParameter("action");
		String casualtyShiftClientId=request.getParameter("clId");
		
		Connection connection = null;
		try{
			if(casualtyShiftClientId!=null){
				action="0";
			}
			connection = Connection_provider.getconnection();
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			BedDao bedDao = new JDBCBedDao(connection);
			MasterDAO masterDAO=new JDBCMasterDAO(connection);
			ArrayList<Bed>bedList = ipdDAO.getWardWiseBedList(wardid);
			
			ipdForm.setBedlist(bedList);
			
			ipdForm.setWardid(wardid);
			ipdForm.setBedid(bedid);
			
			String chief_comlint_id=masterDAO.getIpdTemplateId("Chief Complaints");
			String present_ill_id=masterDAO.getIpdTemplateId("Present Illness");
			String past_history_id=masterDAO.getIpdTemplateId("Past History");
			String family_hist_id=masterDAO.getIpdTemplateId("Family History");
			String personal_hist_id=masterDAO.getIpdTemplateId("Personal History");
			String onexami_id=masterDAO.getIpdTemplateId("On Examination");
			String tretment_given_id=masterDAO.getIpdTemplateId("Treatment Given");
			
			ArrayList<Master> chief_complaints_list=masterDAO.getIpdTemplateListNames(chief_comlint_id);
			ArrayList<Master> present_illness_list=masterDAO.getIpdTemplateListNames(present_ill_id);
			ArrayList<Master> past_history_list=masterDAO.getIpdTemplateListNames(past_history_id);
			ArrayList<Master> family_history_list=masterDAO.getIpdTemplateListNames(family_hist_id);
			ArrayList<Master> personal_hist_list=masterDAO.getIpdTemplateListNames(personal_hist_id);
			ArrayList<Master> on_exam_list=masterDAO.getIpdTemplateListNames(onexami_id);
			ArrayList<Master> treatment_given_list=masterDAO.getIpdTemplateListNames(tretment_given_id);
			ipdForm.setChief_complaints_list(chief_complaints_list);
			ipdForm.setPresent_illness_list(present_illness_list);
			ipdForm.setPast_history_list(past_history_list);
			ipdForm.setFamily_history_list(family_history_list);
			ipdForm.setPersonal_hist_list(personal_hist_list);
			ipdForm.setOn_exam_list(on_exam_list);
			ipdForm.setTreatment_given_list(treatment_given_list);
			ipdForm.setRatewardid(ipdForm.getWardid());
			request.setAttribute("casualtyShiftClientId", casualtyShiftClientId);
			ArrayList<Bed> wardlist=bedDao.getAllWardList(action);
			ipdForm.setLocationshift(DateTimeUtils.isNull(request.getParameter("actionfrom")));
			ipdForm.setWardlist(wardlist);
			
			ipdForm.setActiontype(action);
			if(action.equals("1")||action.equals("2")){
				return "addipdcasualty";
			}
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		return INPUT;
	}
	
	public String clientinfo()throws Exception{
		
		if(!verifyLogin(request)) {
    		
    		return "login";
    	}
    	
    	Connection connection=null;
    	
    	String clientid = request.getParameter("clientid");
    	
    	try {
	
    		connection=Connection_provider.getconnection();
    		BedDao bedDao=new JDBCBedDao(connection);
    		ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
    		
    		NotAvailableSlot notAvailableSlot = bedDao.getClientLastOpdRecord(clientid);
    		IpdDAO ipdDAO=new JDBCIpdDAO(connection); 
    		
    		ClientDAO clientDAO = new JDBCClientDAO(connection);
    		Client client = clientDAO.getPatient(Integer.parseInt(clientid));
    		
    		String fullname = client.getTitle() + " " + client.getFirstName() + " "+client.getMiddlename()+" " + client.getLastName();
    		String tp = clientDAO.getTPCompanyName(client.getTypeName());
    		
    		ThirdParty thirdParty = thirdPartyDAO.getThirdPartyDetails(client.getTypeName());
    		
    		String age = DateTimeUtils.getAge(client.getDob());
    		String agegender="";
    		if(Integer.parseInt(age)<2){

				String monthdays= DateTimeUtils.getMonthandDays(client.getDob());
				agegender=monthdays+" / "+client.getGender();
			} else {
				agegender = age + "Years" + " / " + client.getGender();	
			}
    		
    	    String num_admission=ipdDAO.getNumofAdmissionCount(clientid);
    	    
    	    
    	    String reference=clientDAO.getReferenceName(client.getReference());
    		if(client.getEmergencyContName()==null){
    			client.setEmergencyContName("");
    		}
    		
    		if(client.getEmergencyContNo()==null){
    			client.setEmergencyContNo("");
    		}
    		if(client.getReference()==null){
    			client.setReference("");
    		}
    		if(client.getRelation()==null){
    			
    			client.setRelation("");
    		}
    		
    		
    		String clientInfo = fullname + "~" + notAvailableSlot.getDiaryUser() + "~" + notAvailableSlot.getCondition() + 
    				"~" + notAvailableSlot.getTreatmentEpisodeId() + "~" + tp + "~" + client.getWhopay() + "~" + clientid +
    				"~" + client.getPolicyNo() + "~" +agegender+"~"+client.getAddress()+"~"+client.getDob()+"~"+client.getEmergencyContName()+"~"+client.getEmergencyContNo()+"~"+client.getRelation()+"~"+num_admission+"~"+reference;
    		
    		response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+clientInfo+"");
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	finally{
			connection.close();
		}
    	return null;
	}
	
	public String admissionform()throws Exception {
		
         if(!verifyLogin(request)) {
    		
    		return "login";
    	}
    
		return "admission";
	}
	
	
	public String updatecasualty()throws Exception{
		
	if(!verifyLogin(request)) {
    		
    		return "login";
    	}
    	
    	Connection connection=null;
    	
    	try {
	
    		connection=Connection_provider.getconnection();
    		
    		BedDao bedDao=new JDBCBedDao(connection);
    		UserProfileDAO profileDAO = new JDBCUserProfileDAO(connection);
    		ClientDAO clientDAO=new JDBCClientDAO(connection);
    		CompleteAptmDAO completeAptmDAO=new JDBCCompleteAptmDAO(connection);
    		AppointmentTypeDAO appointmentTypeDAO = new JDBCAppointmentTypeDAO(connection);
    		Bed bed=new Bed();
    		
    		int practitionerid=0;
    		
    		String payee=ipdForm.getPayee();
            if(payee.equals("0")){
            	payee="Client";
            } else {
            	payee="Third Party";
            }
            String tpid=ipdForm.getTpid();
            int r=clientDAO.updatePayeeofPatient(ipdForm.getClientid(),payee,tpid);
    		
    		
            //update emergency contact details
            Client clientcont=new Client();
            clientcont.setEmergencyContName(ipdForm.getRelativename());
            clientcont.setEmergencyContNo(ipdForm.getRelationcont());
            clientcont.setRelation(ipdForm.getRelation());
            clientcont.setId(Integer.parseInt(ipdForm.getClientid()));
            clientcont.setReference(ipdForm.getRefferedby());
            
            r=clientDAO.updateEmergencyDetails(clientcont);
    		
    		bed.setClientid(ipdForm.getClientid());
    		bed.setPractitionerid(ipdForm.getPractitionerid());
    		bed.setConditionid(ipdForm.getConditionid());
    		
    		 if(ipdForm.getPractitionerid()!=null){
  		       if(!ipdForm.getPractitionerid().equals("")){
  		         
  		            practitionerid=Integer.parseInt(ipdForm.getPractitionerid());
  		           UserProfile userProfile=profileDAO.getUserprofileDetails(practitionerid);
  		           bed.setSpeciality(userProfile.getDiciplineName());
  		           ipdForm.setDepartment(userProfile.getSpecialization());
  		       }
  		 }
    		
    		bed.setDepartment(ipdForm.getDepartment());
    		bed.setRefferedby(ipdForm.getRefferedby());
    		bed.setWardid(ipdForm.getWardid());
    		bed.setBedid(ipdForm.getBedid());
    		bed.setTpid(ipdForm.getTpid());
    		bed.setStatus(ipdForm.getStatus());
    		bed.setAddmissionfor(ipdForm.getAddmissionfor());
    		bed.setReimbursment(ipdForm.getReimbursment());
    		bed.setSecndryconsult(ipdForm.getSecndryconsult());
    		bed.setMlcno(ipdForm.getMlcno());
    		bed.setAdmissiondetails(ipdForm.getAdmissiondetails());
    		bed.setSuggestedtrtment(ipdForm.getSuggestedtrtment());
    		bed.setHosp(ipdForm.getHosp());
    		bed.setPackagename(ipdForm.getPackagename());
    		//chiefcomplains, presentillness, pasthistory, personalhist, familyhist, onexamination, treatmentepisodeid
    		bed.setChiefcomplains(ipdForm.getChiefcomplains());
    		bed.setPresentillness(ipdForm.getPresentillness());
    		bed.setPasthistory(ipdForm.getPasthistory());
    		bed.setPersonalhist(ipdForm.getPersonalhist());
    		bed.setFamilyhist(ipdForm.getFamilyhist());
    		bed.setOnexamination(ipdForm.getOnexamination());
    		bed.setTreatmentepisodeid(ipdForm.getTreatmentepisodeid());
    		
    	
    		
    		
    	
    		
    		
    		bed.setSuggestoper(ipdForm.getSuggestoper());
    		bed.setSystdepartment(bed.getSystdepartment());
    		bed.setFpcondition(ipdForm.getFpcondition());
    		bed.setFpnotest(ipdForm.getFpnotest());
    		bed.setNauseacondition(ipdForm.getNauseacondition());
    		bed.setNauseanotes(ipdForm.getNauseanotes());
    		bed.setFnucondition(ipdForm.getFnucondition());
    		bed.setFnunotes(ipdForm.getFnunotes());
    		bed.setSmcondition(ipdForm.getSmcondition());
    		bed.setSmnotes(ipdForm.getSmnotes());
    		bed.setChestpaincond(ipdForm.getChestpaincond());
    		bed.setChestpainnotes(ipdForm.getChestpainnotes());
    		bed.setDimvisioncond(ipdForm.getDimvisioncond());
    		bed.setDimvisionnotes(ipdForm.getDimvisionnotes());
    		
    		//dimvisionnotes, hgucondition, hgunotes, hmcondition, hmnotes, jointpaincond, jointpainnotes, 
    		bed.setHgucondition(ipdForm.getHgucondition());
    		bed.setHgunotes(ipdForm.getHgunotes());
    		bed.setHmcondition(ipdForm.getHmcondition());
    		bed.setHmnotes(ipdForm.getHmnotes());
    		bed.setJointpaincond(ipdForm.getJointpaincond());
    		bed.setJointpainnotes(ipdForm.getJointpainnotes());
    		
    		//constipationcond, constpationnotes, specialnotes, edemafeetcondi, edemafeetnotes, hematuriacondi, 
    		bed.setConstipationcond(ipdForm.getConstipationcond());
    		bed.setConstpationnotes(ipdForm.getConstpationnotes());
    		bed.setSpecialnotes(ipdForm.getSpecialnotes());
    		bed.setEdemafeetcondi(ipdForm.getEdemafeetcondi());
    		bed.setEdemafeetnotes(ipdForm.getEdemafeetnotes());
    		bed.setHematuriacondi(ipdForm.getHematuriacondi());
    		bed.setHematurianotes(ipdForm.getHematurianotes());
    		
    		//hematurianotes, bmcondition, bmnotes, oliguriacondi, oligurianotes, pstgucondi, pstgunotes, 
    		bed.setBmcondition(ipdForm.getBmcondition());
    		bed.setBmnotes(ipdForm.getBmnotes());
    		bed.setOliguriacondi(ipdForm.getOliguriacondi());
    		bed.setOligurianotes(ipdForm.getOligurianotes());
    		bed.setPstgucondi(ipdForm.getPstgucondi());
    		bed.setPstgunotes(ipdForm.getPstgunotes());
    		
    		//bmecondition, bmenotes, tnecondition, tnenotes, weaknesscondi, weaknessnotes, ihcondition, ihnotes
    		bed.setBmecondition(ipdForm.getBmecondition());
    		bed.setBmenotes(ipdForm.getBmenotes());
    		bed.setTnecondition(ipdForm.getTnecondition());
    		bed.setTnenotes(ipdForm.getTnenotes());
    		bed.setWeaknesscondi(ipdForm.getWeaknesscondi());
    		bed.setWeaknessnotes(ipdForm.getWeaknessnotes());
    		bed.setIhcondition(ipdForm.getIhcondition());
    		bed.setIhnotes(ipdForm.getIhnotes());
    		
    		bed.setEarlierinvest(ipdForm.getEarlierinvest());
       	    bed.setAdmission_reason(ipdForm.getAdmission_reason());
       	    bed.setAlergies(ipdForm.getAlergies());
    		
       	    
       	//gynic details
    		bed.setAlcohal(ipdForm.getAlcohal());
    		bed.setDrugs(ipdForm.getDrugs());
    		bed.setOther_medication(ipdForm.getOther_medication());
    		bed.setTobaco(ipdForm.getTobaco());
    		bed.setTobaconotes(ipdForm.getTobaconotes());
    		bed.setSmoking(ipdForm.getSmoking());
    		
    		bed.setAge_menarche(ipdForm.getAge_menarche());
    		bed.setLmp(ipdForm.getLmp());
    		bed.setLlmp(ipdForm.getLlmp());
    		bed.setPmc(ipdForm.getPmc());
    		
    		
    		bed.setCycle_length(ipdForm.getCycle_length());
    		bed.setDuration_flow(ipdForm.getDuration_flow());
    		bed.setAmount_flow(ipdForm.getAmount_flow());
    		bed.setDysmenorrhea(ipdForm.getDysmenorrhea());
    		bed.setDyspareunia(ipdForm.getDyspareunia());
    		bed.setHopassing_clots(ipdForm.getHopassing_clots());
    		
    		bed.setWhite_disc_itching(ipdForm.getWhite_disc_itching());
    		bed.setIntercourse_freq(ipdForm.getIntercourse_freq());
    		bed.setGalactorrea(ipdForm.getGalactorrea());
    		
    		bed.setHo_contraception(ipdForm.getHo_contraception());
    		bed.setRubella_vaccine(ipdForm.getRubella_vaccine());
    		bed.setMenopause(ipdForm.getMenopause());
    		bed.setNulligravida(ipdForm.getNulligravida());
    		bed.setCurrent_pregnent(ipdForm.getCurrent_pregnent());
    		bed.setIud(ipdForm.getIud());
    		
    		bed.setLive_boys(ipdForm.getLive_boys());
    		bed.setLive_girls(ipdForm.getLive_girls());
    		bed.setStillbirths(ipdForm.getStillbirths());
    		bed.setAbortions(ipdForm.getAbortions());
    		bed.setDeath_children(ipdForm.getDeath_children());
    		bed.setMlcrefdoctor(ipdForm.getMlcrefdoctor());
    		
    		
    		 
            //save gynic obs history
            if(ipdForm.getObslist()!=null){
            	 for(Bed bed2 :ipdForm.getObslist()){
                	 
            		 if(bed2.getId()==0){
            			 int res=bedDao.saveGynicObsData(ipdForm.getId(),bed2);
            		 } else {
            			 int res=bedDao.updateGynicObsData(bed2);
            		 }
            	     
              }
            }
    		
    		
    		//parity_aboration_notes,p,l,a,d
    		bed.setParity_abortion_notes(ipdForm.getParity_abortion_notes());
    		bed.setP(ipdForm.getP());
    		bed.setL(ipdForm.getL());
    		bed.setA(ipdForm.getA());
    		bed.setD(ipdForm.getD());
    		
    		String addmissiondate = DateTimeUtils.getCommencingDate1(ipdForm.getAdmissiondate()) + " " + ipdForm.getHour() + ":" + ipdForm.getMinute() + ":20" ;
    		bed.setAdmissiondate(addmissiondate);
    		
    		String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
    		
    		//log f
    		Bed bedchange = bedDao.getEditIpdData(Integer.toString(ipdForm.getId()));
    		
    		String templ[] = bedchange.getAdmissiondate().split(" ");
			String ldate = DateTimeUtils.getCommencingDate1(templ[0]);
			String time[] = templ[1].split(":");
			String hh = time[0];
			String mm = time[1];
			
			String loglastmodified = DateTimeUtils.getCommencingDate1(ipdForm.getAdmissiondate()) + " " + hh + ":" + mm + ":20" ;
    		
    		int update = bedDao.updateIpdDetails(bed,ipdForm.getId(),date);
    		
    	}catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		return "savecasualty";
	}
	
	public String update()throws Exception{
		
	if(!verifyLogin(request)) {
    		
    		return "login";
    	}
    	
    	Connection connection=null;
    	
    	try {
	
    		connection=Connection_provider.getconnection();
    		
    		BedDao bedDao=new JDBCBedDao(connection);
    		UserProfileDAO profileDAO = new JDBCUserProfileDAO(connection);
    		ClientDAO clientDAO=new JDBCClientDAO(connection);
    		CompleteAptmDAO completeAptmDAO=new JDBCCompleteAptmDAO(connection);
    		AppointmentTypeDAO appointmentTypeDAO = new JDBCAppointmentTypeDAO(connection);
    		ClinicDAO clinicDAO = new JDBCClinicDAO(connection);
    		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
    		
    		Bed bed=new Bed();
    		
    		int practitionerid=0;
    		
    		String payee=ipdForm.getPayee();
            if(payee.equals("0")){
            	payee="Client";
            } else {
            	payee="Third Party";
            }
            String tpid=ipdForm.getTpid();
            int r=clientDAO.updatePayeeofPatient(ipdForm.getClientid(),payee,tpid);
    		
    		
            //update emergency contact details
            Client clientcont=new Client();
            clientcont.setEmergencyContName(ipdForm.getRelativename());
            clientcont.setEmergencyContNo(ipdForm.getRelationcont());
            clientcont.setRelation(ipdForm.getRelation());
            clientcont.setId(Integer.parseInt(ipdForm.getClientid()));
            clientcont.setReference(ipdForm.getRefferedby());
            
            r=clientDAO.updateEmergencyDetails(clientcont);
    		
    		bed.setClientid(ipdForm.getClientid());
    		bed.setPractitionerid(ipdForm.getPractitionerid());
    		bed.setConditionid(ipdForm.getConditionid());
    		
    		 if(ipdForm.getPractitionerid()!=null){
  		       if(!ipdForm.getPractitionerid().equals("")){
  		         
  		            practitionerid=Integer.parseInt(ipdForm.getPractitionerid());
  		           UserProfile userProfile=profileDAO.getUserprofileDetails(practitionerid);
  		           bed.setSpeciality(userProfile.getDiciplineName());
  		           ipdForm.setDepartment(userProfile.getSpecialization());
  		       }
  		 }
    		
    		bed.setDepartment(ipdForm.getDepartment());
    		String id= ipdForm.getRefferedby();
    		String name= clientDAO.getReferenceName(id);
    		bed.setRefferedby(name);
    		bed.setReferenceid(id);
    		
    		
    		bed.setWardid(ipdForm.getWardid());
    		
    		
    		bed.setTpid(ipdForm.getTpid());
    		bed.setStatus(ipdForm.getStatus());
    		bed.setAddmissionfor(ipdForm.getAddmissionfor());
    		bed.setReimbursment(ipdForm.getReimbursment());
    		bed.setSecndryconsult(ipdForm.getSecndryconsult());
    		bed.setMlcno(ipdForm.getMlcno());
    		bed.setAdmissiondetails(ipdForm.getAdmissiondetails());
    		bed.setSuggestedtrtment(ipdForm.getSuggestedtrtment());
    		bed.setHosp(ipdForm.getHosp());
    		bed.setPackagename(ipdForm.getPackagename());
    		//chiefcomplains, presentillness, pasthistory, personalhist, familyhist, onexamination, treatmentepisodeid
    		bed.setChiefcomplains(ipdForm.getChiefcomplains());
    		bed.setPresentillness(ipdForm.getPresentillness());
    		bed.setPasthistory(ipdForm.getPasthistory());
    		bed.setPersonalhist(ipdForm.getPersonalhist());
    		bed.setFamilyhist(ipdForm.getFamilyhist());
    		bed.setOnexamination(ipdForm.getOnexamination());
    		bed.setTreatmentepisodeid(ipdForm.getTreatmentepisodeid());
    		
    		bed.setSuggestoper(ipdForm.getSuggestoper());
    		bed.setSystdepartment(bed.getSystdepartment());
    		bed.setFpcondition(ipdForm.getFpcondition());
    		bed.setFpnotest(ipdForm.getFpnotest());
    		bed.setNauseacondition(ipdForm.getNauseacondition());
    		bed.setNauseanotes(ipdForm.getNauseanotes());
    		bed.setFnucondition(ipdForm.getFnucondition());
    		bed.setFnunotes(ipdForm.getFnunotes());
    		bed.setSmcondition(ipdForm.getSmcondition());
    		bed.setSmnotes(ipdForm.getSmnotes());
    		bed.setChestpaincond(ipdForm.getChestpaincond());
    		bed.setChestpainnotes(ipdForm.getChestpainnotes());
    		bed.setDimvisioncond(ipdForm.getDimvisioncond());
    		bed.setDimvisionnotes(ipdForm.getDimvisionnotes());
    		
    		//dimvisionnotes, hgucondition, hgunotes, hmcondition, hmnotes, jointpaincond, jointpainnotes, 
    		bed.setHgucondition(ipdForm.getHgucondition());
    		bed.setHgunotes(ipdForm.getHgunotes());
    		bed.setHmcondition(ipdForm.getHmcondition());
    		bed.setHmnotes(ipdForm.getHmnotes());
    		bed.setJointpaincond(ipdForm.getJointpaincond());
    		bed.setJointpainnotes(ipdForm.getJointpainnotes());
    		
    		//constipationcond, constpationnotes, specialnotes, edemafeetcondi, edemafeetnotes, hematuriacondi, 
    		bed.setConstipationcond(ipdForm.getConstipationcond());
    		bed.setConstpationnotes(ipdForm.getConstpationnotes());
    		bed.setSpecialnotes(ipdForm.getSpecialnotes());
    		bed.setEdemafeetcondi(ipdForm.getEdemafeetcondi());
    		bed.setEdemafeetnotes(ipdForm.getEdemafeetnotes());
    		bed.setHematuriacondi(ipdForm.getHematuriacondi());
    		bed.setHematurianotes(ipdForm.getHematurianotes());
    		
    		//hematurianotes, bmcondition, bmnotes, oliguriacondi, oligurianotes, pstgucondi, pstgunotes, 
    		bed.setBmcondition(ipdForm.getBmcondition());
    		bed.setBmnotes(ipdForm.getBmnotes());
    		bed.setOliguriacondi(ipdForm.getOliguriacondi());
    		bed.setOligurianotes(ipdForm.getOligurianotes());
    		bed.setPstgucondi(ipdForm.getPstgucondi());
    		bed.setPstgunotes(ipdForm.getPstgunotes());
    		
    		//bmecondition, bmenotes, tnecondition, tnenotes, weaknesscondi, weaknessnotes, ihcondition, ihnotes
    		bed.setBmecondition(ipdForm.getBmecondition());
    		bed.setBmenotes(ipdForm.getBmenotes());
    		bed.setTnecondition(ipdForm.getTnecondition());
    		bed.setTnenotes(ipdForm.getTnenotes());
    		bed.setWeaknesscondi(ipdForm.getWeaknesscondi());
    		bed.setWeaknessnotes(ipdForm.getWeaknessnotes());
    		bed.setIhcondition(ipdForm.getIhcondition());
    		bed.setIhnotes(ipdForm.getIhnotes());
    		
    		bed.setEarlierinvest(ipdForm.getEarlierinvest());
       	    bed.setAdmission_reason(ipdForm.getAdmission_reason());
       	    bed.setAlergies(ipdForm.getAlergies());
    		
       	    //peditric 
       	  bed.setBirthhist(ipdForm.getBirthhist());
          bed.setDevelopmenthist(ipdForm.getDevelopmenthist());
          bed.setEmmunizationhist(ipdForm.getEmmunizationhist());
          bed.setDiethist(ipdForm.getDiethist());
       	  bed.setMidarmcircumference(ipdForm.getMidarmcircumference());  
       	  bed.setHeadcircumference(ipdForm.getHeadcircumference());
       	  bed.setLength(ipdForm.getLength());
       	  bed.setWtaddmission(ipdForm.getWtaddmission());
       	  bed.setWtdischarge(ipdForm.getWtdischarge());
       	  bed.setGstureage(ipdForm.getGstureage());
       	 bed.setWtonbirth(ipdForm.getWtonbirth());
       	  
       	//gynic details
    		bed.setAlcohal(ipdForm.getAlcohal());
    		bed.setDrugs(ipdForm.getDrugs());
    		bed.setOther_medication(ipdForm.getOther_medication());
    		bed.setTobaco(ipdForm.getTobaco());
    		bed.setTobaconotes(ipdForm.getTobaconotes());
    		bed.setSmoking(ipdForm.getSmoking());
    		
    		bed.setAge_menarche(ipdForm.getAge_menarche());
    		bed.setLmp(ipdForm.getLmp());
    		bed.setLlmp(ipdForm.getLlmp());
    		bed.setPmc(ipdForm.getPmc());
    		
    		
    		bed.setCycle_length(ipdForm.getCycle_length());
    		bed.setDuration_flow(ipdForm.getDuration_flow());
    		bed.setAmount_flow(ipdForm.getAmount_flow());
    		bed.setDysmenorrhea(ipdForm.getDysmenorrhea());
    		bed.setDyspareunia(ipdForm.getDyspareunia());
    		bed.setHopassing_clots(ipdForm.getHopassing_clots());
    		
    		bed.setWhite_disc_itching(ipdForm.getWhite_disc_itching());
    		bed.setIntercourse_freq(ipdForm.getIntercourse_freq());
    		bed.setGalactorrea(ipdForm.getGalactorrea());
    		
    		bed.setHo_contraception(ipdForm.getHo_contraception());
    		bed.setRubella_vaccine(ipdForm.getRubella_vaccine());
    		bed.setMenopause(ipdForm.getMenopause());
    		bed.setNulligravida(ipdForm.getNulligravida());
    		bed.setCurrent_pregnent(ipdForm.getCurrent_pregnent());
    		bed.setIud(ipdForm.getIud());
    		
    		bed.setLive_boys(ipdForm.getLive_boys());
    		bed.setLive_girls(ipdForm.getLive_girls());
    		bed.setStillbirths(ipdForm.getStillbirths());
    		bed.setAbortions(ipdForm.getAbortions());
    		bed.setDeath_children(ipdForm.getDeath_children());
    		bed.setMlcrefdoctor(ipdForm.getMlcrefdoctor());
    		bed.setSurgicalnotes(ipdForm.getSurgicalnotes());
    		
    		
    		bed.setMaternal_history(ipdForm.getMaternal_history());
    		bed.setPerinatal_history(ipdForm.getPerinatal_history());
    		
    		bed.setMlccase(ipdForm.getMlccase());
    		 bed.setMlcabrivationid(ipdForm.getMlcabrivationid());
            //save gynic obs history
            if(ipdForm.getObslist()!=null){
            	 for(Bed bed2 :ipdForm.getObslist()){
                	 
            		 if(bed2.getId()==0){
            			 int res=bedDao.saveGynicObsData(ipdForm.getId(),bed2);
            		 } else {
            			 int res=bedDao.updateGynicObsData(bed2);
            		 }
            	     
              }
            }
    		
    		
    		//parity_aboration_notes,p,l,a,d	
    		bed.setParity_abortion_notes(ipdForm.getParity_abortion_notes());
    		bed.setP(ipdForm.getP());
    		bed.setL(ipdForm.getL());
    		bed.setA(ipdForm.getA());
    		bed.setD(ipdForm.getD());
    		
    		//  27 July 2018
    		bed.setGiddinesscondition(ipdForm.getGiddinesscondition());
    		bed.setGiddinessnotes(ipdForm.getGiddinessnotes());
    		bed.setUnconcondition(ipdForm.getUnconcondition());
    		bed.setUnconnotes(ipdForm.getUnconnotes());
    		
    		String addmissiondate = DateTimeUtils.getCommencingDate1(ipdForm.getAdmissiondate()) + " " + ipdForm.getHour() + ":" + ipdForm.getMinute() + ":20" ;
    		bed.setAdmissiondate(addmissiondate);
    		
    		String date = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
    		
    		//log f
    		Bed bedchange = bedDao.getEditIpdData(Integer.toString(ipdForm.getId()));
    		
    		String templ[] = bedchange.getAdmissiondate().split(" ");
			String ldate = DateTimeUtils.getCommencingDate1(templ[0]);
			String time[] = templ[1].split(":");
			String hh = time[0];
			String mm = time[1];
			
			String loglastmodified = DateTimeUtils.getCommencingDate1(ipdForm.getAdmissiondate()) + " " + hh + ":" + mm + ":20" ;
			  String cutofftime = bedDao.getHospitalCutoffTime(loginInfo.getClinicid());
	            if(!cutofftime.equals("0")){
	            	loglastmodified = DateTimeUtils.getCommencingDate1(ipdForm.getAdmissiondate()) + " " + cutofftime + ":20" ;
	            }
    		int log = 0;
    		bed.setBedid(ipdForm.getBedid());
    		String logcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
    		boolean bedchangestatus=false;
    		if(!bedchange.getWardid().equals(ipdForm.getWardid()) && !bedchange.getBedid().equals(ipdForm.getBedid())){
    			log = bedDao.saveBedChangeLog(bed, loglastmodified, ipdForm.getId(),logcommencing,date,0);
    			session.setAttribute("ipddash_forreload", "1");
    			bedchangestatus = true;
    		}
    		else if(!bedchange.getWardid().equals(ipdForm.getWardid())){
    			log = bedDao.saveBedChangeLog(bed, loglastmodified, ipdForm.getId(),logcommencing,date,0);
    			session.setAttribute("ipddash_forreload", "1");
    			bedchangestatus = true;
    		}
    		else if(!bedchange.getBedid().equals(ipdForm.getBedid())){
    			log = bedDao.saveBedChangeLog(bed, loglastmodified, ipdForm.getId(),logcommencing,date,0);
    			session.setAttribute("ipddash_forreload", "1");
    			bedchangestatus = true;
    		}
    		/*if(bedchangestatus){
    			ipdDAO.createAdmissionView();
    			ipdDAO.createDischargeView();
    		}*/
    		
    		String admissiondate = DateTimeUtils.getCommencingDate1(ipdForm.getAdmissiondate()) + " " + ipdForm.getHour() + ":" + ipdForm.getMinute() + ":20" ;
    		bed.setAdmissiondate(admissiondate);
    		
    		boolean flag=bedDao.getIsWardChange(ipdForm.getId(),ipdForm.getWardid());
    		Bed bed2=new Bed();
    		bed2=bedDao.getRecentWardid(ipdForm.getId(),ipdForm.getWardid());
    		
    		if(ipdForm.getDisbedid().equals("0")){
    			bed.setBedid("0");
    		} else {

    			bed.setBedid(ipdForm.getBedid());
    		}
    		
//Lokesh code to get The current treatmentEpisodseid;    	
    		Bed bed4= bedDao.getEditIpdData(""+ipdForm.getId());
    		bed.setTreatmentepisodeid(bed4.getTreatmentepisodeid());
    		int update = bedDao.updateIpdDetails(bed,ipdForm.getId(),date);
    		/*update = bedDao.updateAdmissionDate(ipdForm.getId(),bed.getAdmissiondate());*/
    		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
 			String today = dateFormat.format(cal.getTime()); 
 			boolean datestatus=today.equals(bed2.getCommencing());
    		
 			//  22-06-2019 new code of auto charge
 			if(flag){
 				Client client=clientDAO.getClientDetails(bed.getClientid()); 
 	        	int res=bedDao.saveShiftBedLog(admissiondate,ipdForm.getId(),bedchange.getWardid(),ipdForm.getWardid());
 	        	
 	        	int wardids = completeAptmDAO.getMaxWardChargeID(ipdForm.getId(),today);
 	        	String wardid = ""+wardids;
 	        	if(wardid.equals("0")){
 	        		wardid = ipdForm.getWardid();
 	        	}
 	        	ArrayList<Master> chargeList=appointmentTypeDAO.getStandardChargeList(""+wardid, tpid, payee,loginInfo);
 	        	String stdcharges="0";
        		for(Master master:chargeList){
        			stdcharges=stdcharges+","+master.getId();
        		}
    			int result=completeAptmDAO.updateWardCharges(ipdForm.getId(),stdcharges);
    			int invoiceid=0;
    			String date1 = DateTimeUtils.getCommencingDate1(ipdForm.getAdmissiondate());
    			if(chargeList.size()!=0){
    				CompleteAppointment appointment=new CompleteAppointment();
        			appointment.setClientId(bed.getClientid());
        			appointment.setPractitionerId(bed.getPractitionerid());
        			appointment.setChargeType("Charge");
        			appointment.setLocation("1");
        		    appointment.setAdditionalcharge_id(stdcharges);
        		    appointment.setIpdid(ipdForm.getId());
        		    appointment.setInvoiceDate(date1);
        		    appointment.setAppointmentid("0");
        		    appointment.setIpd("1");
        		    appointment.setWardid(""+wardid);
        		    if(client.getWhopay()!=null){
        		    	if(client.getWhopay().equals("Self") || client.getWhopay().equals("Client")){
        		    		appointment.setPolicyExcess("0");
        		    		appointment.setPayBuy("0");
        		    	} else {
        		    		appointment.setPolicyExcess("1");
        		    		appointment.setPayBuy("1");
        		    	}
        		    }
        		    String fullname=client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
        		    appointment.setUser(fullname);
        		    appointment.setInvoiceDate(today);
         			appointment.setCommencing(today); 
          		    invoiceid=completeAptmDAO.saveStndCharge(appointment.getClientId(), String.valueOf(ipdForm.getId()), stdcharges);
          		    invoiceid=completeAptmDAO.saveAmpmInvoice(appointment,loginInfo.getId(),loginInfo.getUserId());
          		    int del = appointmentTypeDAO.deletetodayBedShiftingcharge(ipdForm.getId(),today,loginInfo);
       				appointment.setInvoiceid(String.valueOf(invoiceid));
          		    for(Master master:chargeList){
	      		       appointment.setCommencing(today); 
      		    	   appointment.setApmtType(master.getName());
      		    	   appointment.setCharges(master.getCharge());
      		    	   appointment.setAdditionalcharge_id(String.valueOf(master.getId()));
      		    	   //appointment.setMasterchargetype("Bed Charge");
      		    	   //  30-11-2019 kunal auto charges not applied
    		    	   if(loginInfo.getIskunal()!=1){
    		    		   appointment.setMasterchargetype("Bed Charge");
    				   }else{
    					   appointment.setMasterchargetype("Bed Charges");
    				   }
      		    	   appointment.setQuantity(1);
      		    	   appointment.setStdflag("1");
      		    	   res=completeAptmDAO.saveInvoiceAssesment(appointment, invoiceid);
      		    	}
      		    	
	            }
    			int autosetchargelogid = appointmentTypeDAO.getautosetchargelogid(ipdForm.getId(),""+wardid,ipdForm.getBedid());
      		    int uw = appointmentTypeDAO.updateAutosetWardID(autosetchargelogid,""+wardid,ipdForm.getBedid());

      		    
      		  
      		    //Lokesh For OnOff charges in auto charge
      	 		if(loginInfo.isBalgopal()){
      	 			ipdDAO.startOnOffChargesWardChangingProcess(""+ipdForm.getId(), ipdForm.getWardid(), loginInfo);
      	 		}
      	 			
      	 		
      	 		
      	 			

      		    
 			}
 			
 			//update auto charges..
//    		if(datestatus==true && flag==true){
////    		if(flag){
//    		
//    			Client client=clientDAO.getClientDetails(bed.getClientid()); 
//        	
//    			int res=bedDao.saveShiftBedLog(admissiondate,ipdForm.getId(),bedchange.getWardid(),ipdForm.getWardid());
//    			
//        	//	ArrayList<Master> chargeList=completeAptmDAO.getStandardCharges(ipdForm.getWardid(), client.getWhopay());
//    			ArrayList<Master> chargeList=appointmentTypeDAO.getStandardChargeList(ipdForm.getWardid(), tpid, payee,loginInfo);
//        		String stdcharges="0";
//        		for(Master master:chargeList){
//        			
//        			stdcharges=stdcharges+","+master.getId();
//        		}
//    			int result=completeAptmDAO.updateWardCharges(ipdForm.getId(),stdcharges);
//    			
//    			int invoiceid=0;
//        		
//        		
//    			/*String date1 = DateTimeUtils.getDateinSimpleFormate(new Date());
//    			String stemp[] = date1.split(" ");
//    			
//    			String temp[] = stemp[0].split("-");
//    			date1 = temp[2] + "-" + temp[1] + "-" + temp[0];*/
//    			
//    			String date1 = DateTimeUtils.getCommencingDate1(ipdForm.getAdmissiondate());
//    			
//        		
//        		if(chargeList.size()!=0){ 
//        			
//        			CompleteAppointment appointment=new CompleteAppointment();
//        			appointment.setClientId(bed.getClientid());
//        			appointment.setPractitionerId(bed.getPractitionerid());
//        			appointment.setChargeType("Charge");
//        			appointment.setLocation("1");
//        		    appointment.setAdditionalcharge_id(stdcharges);
//        		    appointment.setIpdid(ipdForm.getId());
//        		    appointment.setInvoiceDate(date1);
//        		    appointment.setAppointmentid("0");
//        		    appointment.setIpd("1");
//        		    appointment.setWardid(ipdForm.getWardid());
//        		    
//        		    if(client.getWhopay()!=null){
//        		    	
//        		    	if(client.getWhopay().equals("Self") || client.getWhopay().equals("Client")){
//        		    	       
//        		    		appointment.setPolicyExcess("0");
//        		    		appointment.setPayBuy("0");
//        		    	} else {
//        		    		appointment.setPolicyExcess("1");
//        		    		appointment.setPayBuy("1");
//        		    	}
//        		    }
//        		    
//        		    invoiceid=completeAptmDAO.saveStndCharge(appointment.getClientId(), String.valueOf(ipdForm.getId()), stdcharges);
//        		    
//        		   // invoiceid=completeAptmDAO.saveAmpmInvoice(appointment,loginInfo.getId());
//        		    
//        		    String fullname=client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
//        		    appointment.setUser(fullname);
//        		      
//        		    
//        		    //getting shifting qty
//        		  /*  String curshiftingdate = DateTimeUtils.getCommencingDate1(ipdForm.getAdmissiondate());
//        		    String edate = ipdForm.getAdmissiondate() + " " + ipdForm.getHour() + ":" + ipdForm.getMinute() + ":00";
//        		    String lastshiftingdate = appointmentTypeDAO.getLastShiftingDate(ipdForm.getId());
//        		    String lastshiftedlogid = appointmentTypeDAO.getLastShiftedLogid(ipdForm.getId());
//        		    
//        		    ArrayList<Master>lastShiftedChargeList = appointmentTypeDAO.getLastShiftedChargeList(lastshiftedlogid);
//        		    for(Master m : lastShiftedChargeList){
//        		    	
//        		    	 int  qty = DateTimeUtils.getDifferanceofDateWithTime(lastshiftingdate, edate, m.getChargehours());
//       		    	   if(qty==0){
//       		    		   qty = 1;
//       		    	   }
//       		    	   
//        		    	int assesmentid = m.getId();
//     		    	   int upd = appointmentTypeDAO.updateLasteShiftedChargeQty(lastshiftingdate,curshiftingdate,qty,ipdForm.getId(),m.getId(),assesmentid);
//        		    }
//        		    */
//        		    
//        		    
//        		/*	int qty = 0;
//        			appointment.setCommencing(curshiftingdate); 
//        		    
//        		    for(Master master:chargeList){
//        		    	  
//        		    	  appointment.setCommencing(curshiftingdate); 
//        		    	   appointment.setApmtType(master.getName());
//        		    	   appointment.setCharges(master.getCharge());
//        		    	   appointment.setAdditionalcharge_id(String.valueOf(master.getId()));
//        		    	   appointment.setMasterchargetype("Bed Charge");
//        		    	   
//        		    	    qty = DateTimeUtils.getDifferanceofDateWithTime(lastshiftingdate, edate, master.getChargehours());
//        		    	   if(qty==0){
//        		    		   qty = 1;
//        		    	   }
//        		    	   appointment.setQuantity(qty);
//        		    	   res=completeAptmDAO.saveInvoiceAssesment(appointment, invoiceid);
//        		    	   
//        		    }*/
//        		    
//        		  //reset previous in process charges
//        		    
//        		/*  int  upd = appointmentTypeDAO.resetAllInprocessCharge(ipdForm.getId());
//        			upd = appointmentTypeDAO.setInprocessforNewShiftCharges(invoiceid,log);
//        			
//        			
//        			String curdate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
//        			String tempa[] = curdate.split(" ");
//        			String dateStop = DateTimeUtils.getCommencingDate1(tempa[0]) + " " + tempa[1];
//        			
//        			ArrayList<String>chargeIdList = appointmentTypeDAO.getChargeIdList(invoiceid);
//        			for(String str : chargeIdList){
//        				int chargehour = appointmentTypeDAO.getChargeHour(str);
//        				qty = DateTimeUtils.getDifferanceofDateWithTime(edate, dateStop, chargehour);
//        				 if(qty==0){
//      		    		   qty = 1;
//      		    	   }
//        				 upd = appointmentTypeDAO.updateInprocessQty(ipdForm.getId(),qty,str);
//        			}*/
//        		    
//        		    
//        		    //bed shifting charge once i a day
//        		    /*String logidList = appointmentTypeDAO.getLogIDList(ipdForm.getId());
//        		    String apminvoiceidlist = appointmentTypeDAO.getapminvoiceidlist(logidList);*/
//        		    int del = appointmentTypeDAO.deleteallBedShiftingcharge(ipdForm.getId());
//        		    ArrayList<Master>logcommencingList = appointmentTypeDAO.getLogCommencingList(ipdForm.getId());
//        		    
//        		    //set quantity
//        		    String sdate = appointmentTypeDAO.getIpdAdmissionDate(ipdForm.getId());
//        		    String edate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
//
//        			
//        		    //applyShiftingCharges(logcommencingList,edate,connection,tpid,payee,appointment);
//        			
//        			
//        		    
//        		    //update auto set charge wardid and bedid
//        		    int autosetchargelogid = appointmentTypeDAO.getautosetchargelogid(ipdForm.getId(),ipdForm.getWardid(),ipdForm.getBedid());
//        		    int uw = appointmentTypeDAO.updateAutosetWardID(autosetchargelogid,ipdForm.getWardid(),ipdForm.getBedid());
//        		    
//        			 ArrayList<Master>sepetatrLogcommencingList = appointmentTypeDAO.getsepetatrLogcommencingList(ipdForm.getId());
//        			 applyShiftingCharges(sepetatrLogcommencingList,edate,connection,tpid,payee,appointment);
//        			 
//        			 
//        			//apply charges for individual date shifting
//         		   
//        			 
//         		  
//        			 BedDao bedDao1=new JDBCBedDao(connection);
//        				Bed b = bedDao1.getEditIpdData(Integer.toString(ipdForm.getId()));
//        				
//        				Master master = appointmentTypeDAO.getLastIpdLogData(ipdForm.getId());
//              		    String curshifydate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
//        			/* if(!master.getDate().equals(curshifydate)){
//        				 sdate = master.getLastmodified();
//        				String stemp[] = sdate.split(" ");
//        				sdate = DateTimeUtils.getCommencingDate1(stemp[0]) + " " + stemp[1];
//        				
//        				 invoiceid=completeAptmDAO.saveAmpmInvoice(appointment,loginInfo.getId());
//        				 
//        				 String temp[] = edate.split(" ");
//        					edate = DateTimeUtils.getCommencingDate1(temp[0]) + " " + temp[1];
//        				
//        				int qty1 =(int)DateTimeUtils.getDifferanceofDateWithTime(sdate, edate, master.getChargehours());
//        				String wardidList = appointmentTypeDAO.getBedShiftigWardIdLIst(ipdForm.getId(),master.getDate());
//        				 chargeList=appointmentTypeDAO.getBedShiftingStandardChargeList(wardidList, tpid, payee);
//        				 for(Master m1:chargeList){
//        			    	   bed = appointmentTypeDAO.getLogwardId(master.getDate(),ipdForm.getId());
//        			    	  appointment.setCommencing(master.getDate()); 
//        			    	   appointment.setApmtType(m1.getName());
//        			    	   appointment.setCharges(m1.getCharge());
//        			    	   appointment.setAdditionalcharge_id(String.valueOf(m1.getId()));
//        			    	   appointment.setMasterchargetype("Bed Charge");
//        			    	   appointment.setWardid(bed.getWardid());
//        			    	   appointment.setBedid(bed.getBedid());
//        			    	   appointment.setIpdid(ipdForm.getId());
//        			    	   appointment.setLogid(bed.getLogid());
//        			    	   
//        			    	 
//        			    	
//        		   	   if(qty1==0){
//        		   		   qty1++;
//        		   	   }
//
//        			    	   
//        		   	   		appointment.setQuantity(qty1);
//
//        			    	    res=completeAptmDAO.saveInvoiceAssesment(appointment, invoiceid);
//        			    	   
//        			    }
//         		    }*/
//        			 
//        			
//        			/*int  upd = appointmentTypeDAO.resetAllInprocessCharge(ipdForm.getId());
//        	           upd = appointmentTypeDAO.setInprocessforNewShiftCharges(invoiceid,log);*/
//        			
//        		    //ruchi code for send msg for bed change to patient relative or patient and other one for practioner on patient admit or update
//        			//sms akash 23 jan 2018 comment
//              		    //  20/12/2018 sms on bed change and new admission access based
//              		 
//              		    if(ipdForm.getPractitionerid()!=null){
//             		       if(!ipdForm.getPractitionerid().equals("")){
//             		         
//             		            practitionerid=Integer.parseInt(ipdForm.getPractitionerid());
//             		          
//             		       }
//              		  }
//              		  connection=Connection_provider.getconnection();
//              		    UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
//              		  UserProfile userProfile  = userProfileDAO.getUserprofileDetails(practitionerid);
//              		
//              		    if(loginInfo.isSms_on_bedchange()==true){
//              		    	
//              		    
//        			autosmsBedChange(bed.getClientid(),ipdForm.getWardid(),ipdForm.getBedid(),userProfile.getMobile());
//              		    }
////              		    if(loginInfo.isSms_on_newadm()==true){
////        	           autosmsPrimaryDoctorChange(bed.getClientid(),ipdForm.getWardid(),ipdForm.getBedid(),practitionerid);
////              		    }
//              		    }
//  	
//    		}else{
//    		
//
//        		
//        			Client client=clientDAO.getClientDetails(bed.getClientid()); 
//            	
//        			int res=bedDao.saveShiftBedLog(admissiondate,ipdForm.getId(),bedchange.getWardid(),ipdForm.getWardid());
//        			
//            	//	ArrayList<Master> chargeList=completeAptmDAO.getStandardCharges(ipdForm.getWardid(), client.getWhopay());
//        			ArrayList<Master> chargeList=appointmentTypeDAO.getStandardChargeList(ipdForm.getWardid(), tpid, payee,loginInfo);
//            		String stdcharges="0";
//            		for(Master master:chargeList){
//            			
//            			stdcharges=stdcharges+","+master.getId();
//            		}
//        			int result=completeAptmDAO.updateWardCharges(ipdForm.getId(),stdcharges);
//        			
//        			int invoiceid=0;
//            		
//            		
//        			/*String date1 = DateTimeUtils.getDateinSimpleFormate(new Date());
//        			String stemp[] = date1.split(" ");
//        			
//        			String temp[] = stemp[0].split("-");
//        			date1 = temp[2] + "-" + temp[1] + "-" + temp[0];*/
//        			
//        			String date1 = DateTimeUtils.getCommencingDate1(ipdForm.getAdmissiondate());
//        			
//        			String stdchargesetup = clinicDAO.getStdChargeSetup(loginInfo.getClinicid());
//            		
//            		if(chargeList.size()!=0){ 
//            			
//            			CompleteAppointment appointment=new CompleteAppointment();
//            			appointment.setClientId(bed.getClientid());
//            			appointment.setPractitionerId(bed.getPractitionerid());
//            			appointment.setChargeType("Charge");
//            			appointment.setLocation("1");
//            		    appointment.setAdditionalcharge_id(stdcharges);
//            		    appointment.setIpdid(ipdForm.getId());
//            		    appointment.setInvoiceDate(date1);
//            		    appointment.setAppointmentid("0");
//            		    appointment.setIpd("1");
//            		    appointment.setWardid(ipdForm.getWardid());
//            		    
//            		    if(client.getWhopay()!=null){
//            		    	
//            		    	if(client.getWhopay().equals("Self") || client.getWhopay().equals("Client")){
//            		    	       
//            		    		appointment.setPolicyExcess("0");
//            		    		appointment.setPayBuy("0");
//            		    	} else {
//            		    		appointment.setPolicyExcess("1");
//            		    		appointment.setPayBuy("1");
//            		    	}
//            		    }
//            		    
////            		    invoiceid=completeAptmDAO.saveStndCharge(appointment.getClientId(), String.valueOf(ipdForm.getId()), stdcharges);
//            		    
//            		   // invoiceid=completeAptmDAO.saveAmpmInvoice(appointment,loginInfo.getId());
//            		    
//            		    String fullname=client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
//            		    appointment.setUser(fullname);
//            		      
//            		    
//            		    //getting shifting qty
//            		    String curshiftingdate = DateTimeUtils.getCommencingDate1(ipdForm.getAdmissiondate());
//            		    String edate = ipdForm.getAdmissiondate() + " " + ipdForm.getHour() + ":" + ipdForm.getMinute() + ":00";
//            		    String lastshiftingdate = appointmentTypeDAO.getLastShiftingDate(ipdForm.getId());
//            		    String lastshiftedlogid = appointmentTypeDAO.getLastShiftedLogid(ipdForm.getId());
//            		    
//            		    ArrayList<Master>lastShiftedChargeList = appointmentTypeDAO.getLastShiftedChargeList(lastshiftedlogid);
//            		    for(Master m : lastShiftedChargeList){
//            		    	
//            		    	 int  qty = DateTimeUtils.getDifferanceofDateWithTime(lastshiftingdate, edate, m.getChargehours());
//           		    	   if(qty==0){
//           		    		   qty = 1;
//           		    	   }
//           		    	   if(qty<0){
//         		    		   qty = 1;
//         		    	   }
//           		    	   
//            		    	int assesmentid = m.getId();
//         		    	   int upd = appointmentTypeDAO.updateLasteShiftedChargeQty(lastshiftingdate,curshiftingdate,qty,ipdForm.getId(),m.getId(),assesmentid);
//            		    }
//            		    
//            		    appointment.setInvoiceDate(today);
//           			  invoiceid=completeAptmDAO.saveAmpmInvoice(appointment,loginInfo.getId(),loginInfo.getUserId());
//           			 int del = appointmentTypeDAO.deletetodayBedShiftingcharge(ipdForm.getId(),today);
//            			int qty = 0;
//            			appointment.setCommencing(today); 
//            		    appointment.setInvoiceid(String.valueOf(invoiceid));
//            		    for(Master master:chargeList){
//
//            		    	appointment.setCommencing(today); 
//            		    	appointment.setApmtType(master.getName());
//            		    	appointment.setCharges(master.getCharge());
//            		    	appointment.setAdditionalcharge_id(String.valueOf(master.getId()));
//            		    	appointment.setMasterchargetype("Bed Charge");
//
//            		    	qty = DateTimeUtils.getDifferanceofDateWithTime(lastshiftingdate, edate, master.getChargehours());
//            		    	if(qty==0){
//            		    	qty = 1;
//            		    	}else if(qty<0){
//            		    	qty=1;
//            		    	}
//            		    	appointment.setQuantity(qty);
//            		    	res=completeAptmDAO.saveInvoiceAssesment(appointment, invoiceid);
//
//            		    }
//            		    /*String currentdate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
//            		    for(Master master:chargeList){
//            		    	  
//            		    	  appointment.setCommencing(today); 
//            		    	   appointment.setApmtType(master.getName());
//            		    	   appointment.setCharges(master.getCharge());
//            		    	   appointment.setAdditionalcharge_id(String.valueOf(master.getId()));
//            		    	   appointment.setMasterchargetype("Bed Charge");
//            		    	   
//            		    	    qty = DateTimeUtils.getDifferanceofDateWithTime(lastshiftingdate, edate, master.getChargehours());
//            		    	    qty = Math.abs(qty);
//            		    	    int newqty = qty;
//            		    	
//            		    	    if(qty==0){
//            		    	    	qty++;
//            		    	    }
//            		    	   appointment.setQuantity(1);
//            		    	   appointment.setStdflag("1");
//            		    	   int res1 = 0;
//            		    	   if(stdchargesetup.equals("0")){
//            		    		   if(qty>0){
//            		    			   appointment.setCommencing(curshiftingdate);
//            		    			   for(int i =0; i<=newqty;i++){
//            		    				   SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
//            		    				   Date d=sdf1.parse(curshiftingdate);
//            		    				   Calendar cal1 = Calendar.getInstance();
//            		    				   cal1.setTime(d);
//            		    				   cal1.add(Calendar.DATE, i);
//            		    				   String dt=sdf1.format(cal1.getTime());
//            		    				   appointment.setCommencing(dt);  
//            		    				   
//            		    				   String checkingdate = dt;
//            		    				   String string =dt;
//	   				 			  			if(!cutofftime.equals("0")){
//	   				 			            	string = string + " " + cutofftime + ":20" ;
//	   				 			            }else{
//	   				 			            	string = string + " " +  DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[1];
//	   				 			            }
//	   				 			  			boolean yesflag=false;
//	   				 			            if(checkingdate.equals(currentdate)){
//	   				 			            	String currentdate1 = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
//	   				 			            	if(!cutofftime.equals("0")){
//	   				 			            		int differ = currentdate1.compareTo(string);
//	   				 			            		if(differ<0){
//	   				 			            			yesflag= true;
//	   				 			            		}
//	   				 			            	}
//	   				 			            	
//	   				 			            }
//	   				 			            if(!yesflag){
//	   				 			        		boolean alreadysetautocharge = bedDao.checkAlreadyAutoChargeApplied(dt,bed.getClientid(),ipdForm.getId(),master.getName());
//	   				 			        		if(!alreadysetautocharge){
//	   				 			        			res=completeAptmDAO.saveInvoiceAssesment(appointment, invoiceid);
//	   				 			        		}
//	   				 			        	}
//            		    			   }
//            		    		   }
//            		    	   }
//            		    }*/
//            		    
//            		  //reset previous in process charges
//            		    
//            		  int  upd = appointmentTypeDAO.resetAllInprocessCharge(ipdForm.getId());
//            			upd = appointmentTypeDAO.setInprocessforNewShiftCharges(invoiceid,log);
//            			
//            			
//            			String curdate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
//            			String tempa[] = curdate.split(" ");
//            			String dateStop = DateTimeUtils.getCommencingDate1(tempa[0]) + " " + tempa[1];
//            			
////            			ArrayList<String>chargeIdList = appointmentTypeDAO.getChargeIdList(invoiceid);
////            			for(String str : chargeIdList){
////            				int chargehour = appointmentTypeDAO.getChargeHour(str);
////            				qty = DateTimeUtils.getDifferanceofDateWithTime(edate, dateStop, chargehour);
////            				 if(qty==0){
////          		    		   qty = 1;
////          		    	   }
////            				 upd = appointmentTypeDAO.updateInprocessQty(ipdForm.getId(),qty,str);
////            			}
//            		    
//            		    
//            		    //bed shifting charge once i a day
//            		    /*String logidList = appointmentTypeDAO.getLogIDList(ipdForm.getId());
//            		    String apminvoiceidlist = appointmentTypeDAO.getapminvoiceidlist(logidList);*/
////            		    int del = appointmentTypeDAO.deleteallBedShiftingcharge(ipdForm.getId());
////            		    ArrayList<Master>logcommencingList = appointmentTypeDAO.getLogCommencingList(ipdForm.getId());
////            		    
//            		    //set quantity
////            		    String sdate = appointmentTypeDAO.getIpdAdmissionDate(ipdForm.getId());
////            		    String edate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
//
//            			
////            		    applyShiftingCharges(logcommencingList,edate,connection,tpid,payee,appointment);
//            			
//            			
//            		    
//            		    //update auto set charge wardid and bedid
//            		    int autosetchargelogid = appointmentTypeDAO.getautosetchargelogid(ipdForm.getId(),ipdForm.getWardid(),ipdForm.getBedid());
//            		    int uw = appointmentTypeDAO.updateAutosetWardID(autosetchargelogid,ipdForm.getWardid(),ipdForm.getBedid());
//            		    
////            			 ArrayList<Master>sepetatrLogcommencingList = appointmentTypeDAO.getsepetatrLogcommencingList(ipdForm.getId());
////            			 applyShiftingCharges(sepetatrLogcommencingList,edate,connection,tpid,payee,appointment);
//            			 
//            			 
//            			//apply charges for individual date shifting
//             		   
//            			 
//             		  
//            			 BedDao bedDao1=new JDBCBedDao(connection);
//            				Bed b = bedDao1.getEditIpdData(Integer.toString(ipdForm.getId()));
//            				
//            				Master master = appointmentTypeDAO.getLastIpdLogData(ipdForm.getId());
//                  		    String curshifydate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
//            			/* if(!master.getDate().equals(curshifydate)){
//            				 sdate = master.getLastmodified();
//            				String stemp[] = sdate.split(" ");
//            				sdate = DateTimeUtils.getCommencingDate1(stemp[0]) + " " + stemp[1];
//            				
//            				 invoiceid=completeAptmDAO.saveAmpmInvoice(appointment,loginInfo.getId());
//            				 
//            				 String temp[] = edate.split(" ");
//            					edate = DateTimeUtils.getCommencingDate1(temp[0]) + " " + temp[1];
//            				
//            				int qty1 =(int)DateTimeUtils.getDifferanceofDateWithTime(sdate, edate, master.getChargehours());
//            				String wardidList = appointmentTypeDAO.getBedShiftigWardIdLIst(ipdForm.getId(),master.getDate());
//            				 chargeList=appointmentTypeDAO.getBedShiftingStandardChargeList(wardidList, tpid, payee);
//            				 for(Master m1:chargeList){
//            			    	   bed = appointmentTypeDAO.getLogwardId(master.getDate(),ipdForm.getId());
//            			    	  appointment.setCommencing(master.getDate()); 
//            			    	   appointment.setApmtType(m1.getName());
//            			    	   appointment.setCharges(m1.getCharge());
//            			    	   appointment.setAdditionalcharge_id(String.valueOf(m1.getId()));
//            			    	   appointment.setMasterchargetype("Bed Charge");
//            			    	   appointment.setWardid(bed.getWardid());
//            			    	   appointment.setBedid(bed.getBedid());
//            			    	   appointment.setIpdid(ipdForm.getId());
//            			    	   appointment.setLogid(bed.getLogid());
//            			    	   
//            			    	 
//            			    	
//            		   	   if(qty1==0){
//            		   		   qty1++;
//            		   	   }
//
//            			    	   
//            		   	   		appointment.setQuantity(qty1);
//
//            			    	    res=completeAptmDAO.saveInvoiceAssesment(appointment, invoiceid);
//            			    	   
//            			    }
//             		    }*/
//            			 
//            			
//            			/*int  upd = appointmentTypeDAO.resetAllInprocessCharge(ipdForm.getId());
//            	           upd = appointmentTypeDAO.setInprocessforNewShiftCharges(invoiceid,log);*/
//            			
//            		    //ruchi code for send msg for bed change to patient relative or patient and other one for practioner on patient admit or update
//            			//sms akash 23 jan 2018 comment
//                  		    //  20/12/2018 sms on bed change and new admission access based
//                  		 
//                  		    if(ipdForm.getPractitionerid()!=null){
//                 		       if(!ipdForm.getPractitionerid().equals("")){
//                 		         
//                 		            practitionerid=Integer.parseInt(ipdForm.getPractitionerid());
//                 		          
//                 		       }
//                  		  }
//                  		  connection=Connection_provider.getconnection();
//                  		    UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
//                  		  UserProfile userProfile  = userProfileDAO.getUserprofileDetails(practitionerid);
//                  		
//                  		    if(loginInfo.isSms_on_bedchange()==true){
//                  		    	
//                  		    
//            			autosmsBedChange(bed.getClientid(),ipdForm.getWardid(),ipdForm.getBedid(),userProfile.getMobile());
//                  		    }
////                  		    if(loginInfo.isSms_on_newadm()==true){
////            	           autosmsPrimaryDoctorChange(bed.getClientid(),ipdForm.getWardid(),ipdForm.getBedid(),practitionerid);
////                  		    }
//                  		    }
//      	
//        		
//    		}
    		
    		
    		int del = bedDao.deleteIpdCondition(ipdForm.getId());
    		saveConditions(ipdForm.getId(),ipdForm.getTreatmentepisodeid());
    		boolean flag2 = ipdDAO.checkCheckListAvailable(ipdForm.getTreatmentepisodeid());
    		if(!flag2){
    			ipdDAO.saveDischargeCheckList(Integer.parseInt(ipdForm.getTreatmentepisodeid()),ipdForm.getClientid());
            }
    		//  27 Nov 2017 
    		
    		String chiefcomplatetempname = ipdForm.getChiefcomplatetempname();
    		String presentillnesstempname = ipdForm.getPresentillnesstempname();
    		String pasthistorytempname = ipdForm.getPasthistorytempname();
    		String personalhistorytempname = ipdForm.getPersonalhistorytempname();
    		String familyhistorytempname = ipdForm.getFamilyhistorytempname();
    		String onexaminationtempname = ipdForm.getOnexaminationtempname();
    		String treatmentgiventempname = ipdForm.getTreatmentgiventempname();
    		String getmaternaltempname=ipdForm.getMaternalhisttemp();
    		String getperinataltempname=ipdForm.getPerinataltemp();
    		if(chiefcomplatetempname!=null){
    			if(!chiefcomplatetempname.equals("")){
    				int res = ipdDAO.saveIPDTemplate(chiefcomplatetempname,"1",ipdForm.getDepartment(),ipdForm.getChiefcomplains());
    			}
    		}
    		if(presentillnesstempname!=null){
    			if(!presentillnesstempname.equals("")){
    				int res = ipdDAO.saveIPDTemplate(presentillnesstempname,"2",ipdForm.getDepartment(),ipdForm.getPresentillness());
    			}
    		}
    		if(pasthistorytempname!=null){
    			if(!pasthistorytempname.equals("")){
    				int res = ipdDAO.saveIPDTemplate(pasthistorytempname,"3",ipdForm.getDepartment(),ipdForm.getPasthistory());
    			}
    			
    		}
    		if(personalhistorytempname!=null){
    			if(!personalhistorytempname.equals("")){
    				int res = ipdDAO.saveIPDTemplate(personalhistorytempname,"5",ipdForm.getDepartment(),ipdForm.getPersonalhist());
    			}
    			
    		}
    		if(familyhistorytempname!=null){
    			if(!familyhistorytempname.equals("")){
    				int res = ipdDAO.saveIPDTemplate(familyhistorytempname,"4",ipdForm.getDepartment(),ipdForm.getFamilyhist());
    			}
    			
    		}
    		if(onexaminationtempname!=null){
    			if(!onexaminationtempname.equals("")){
    				int res = ipdDAO.saveIPDTemplate(onexaminationtempname,"6",ipdForm.getDepartment(),ipdForm.getOnexamination());
    			}
    		}
    		if(treatmentgiventempname!=null){
    			if(!treatmentgiventempname.equals("")){
    				int res = ipdDAO.saveIPDTemplate(treatmentgiventempname,"7",ipdForm.getDepartment(),ipdForm.getTreatmentepisodeid());
    			}
    		}
    		
    		if(getmaternaltempname!=null){
    			if(!getmaternaltempname.equals("")){
    				int res = ipdDAO.saveIPDTemplate(getmaternaltempname,"13",ipdForm.getDepartment(),ipdForm.getMaternal_history());
    			}
    		}
    		if(getperinataltempname!=null){
    			if(!getperinataltempname.equals("")){
    				int res = ipdDAO.saveIPDTemplate(getperinataltempname,"14",ipdForm.getDepartment(),ipdForm.getPerinatal_history());
    			}
    		}
    		
    		if(ipdForm.getSaveandprintadmission()==1){
    			session.setAttribute("saveandprintadmission", ""+ipdForm.getId());
    			return "ipdadmissionformprint";
    		}
    		
    		if(bedDao.isDayCare(""+ipdForm.getId())){
    			return "daycare";
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	finally{
			connection.close();
		}
		return "save";
	}
	
	private void applyShiftingCharges(ArrayList<Master> logcommencingList, String edate,Connection connection,String tpid,String payee,CompleteAppointment appointment) throws Exception{
		try{
			
			

		String temp[] = edate.split(" ");
		edate = DateTimeUtils.getCommencingDate1(temp[0]) + " " + temp[1];
		
		AppointmentTypeDAO appointmentTypeDAO = new JDBCAppointmentTypeDAO(connection);
		CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
		int i = 0;
		for(Master master : logcommencingList){
			String sdate = master.getLastmodified();
			String stemp[] = sdate.split(" ");
			sdate = DateTimeUtils.getCommencingDate1(stemp[0]) + " " + stemp[1];
			
			 String lastshiftingdate = "";
			 if(i<=logcommencingList.size()){
				 if(logcommencingList.size()==1){
					 Master mp = logcommencingList.get(0);
					 	String ltemp[] = mp.getLastmodified().split(" ");
					 	lastshiftingdate = DateTimeUtils.getCommencingDate1(ltemp[0]) + " " + ltemp[1]; 
				 }else if(i==logcommencingList.size()-1){
					 
						 Master mp = logcommencingList.get(logcommencingList.size()-1);
						 	String ltemp[] = mp.getLastmodified().split(" ");
						 	lastshiftingdate = DateTimeUtils.getCommencingDate1(ltemp[0]) + " " + ltemp[1];
					 
					
				 }
				 else{
					 Master mp = logcommencingList.get(i+1);
					 	String ltemp[] = mp.getLastmodified().split(" ");
					 	lastshiftingdate = DateTimeUtils.getCommencingDate1(ltemp[0]) + " " + ltemp[1];
				 }
				 	
			 }
			 
			
			String wardidList = appointmentTypeDAO.getBedShiftigWardIdLIst(ipdForm.getId(),master.getDate());
			 appointment.setInvoiceDate(master.getDate());
			 int invoiceid=completeAptmDAO.saveAmpmInvoice(appointment,loginInfo.getId(),loginInfo.getUserId());
			 
			  int qty1 =(int)DateTimeUtils.getDifferanceofDateWithTime(sdate, lastshiftingdate, master.getChargehours());
	    	   
	    	  if(i==logcommencingList.size()-1){
	    		  qty1 =(int)DateTimeUtils.getDifferanceofDateWithTime(sdate, edate, master.getChargehours());
	    	  }
			 
			ArrayList<Master> chargeList=appointmentTypeDAO.getBedShiftingStandardChargeList(wardidList, tpid, payee,loginInfo);
			 
			 for(Master m1:chargeList){
		    	  Bed bed = appointmentTypeDAO.getLogwardId(master.getDate(),ipdForm.getId());
		    	  appointment.setCommencing(master.getDate()); 
		    	   appointment.setApmtType(m1.getName());
		    	   appointment.setCharges(m1.getCharge());
		    	   appointment.setAdditionalcharge_id(String.valueOf(m1.getId()));
		    	   //appointment.setMasterchargetype("Bed Charge");
		    	   //  30-11-2019 kunal auto charges not applied
		    	   if(loginInfo.getIskunal()!=1){
		    		   appointment.setMasterchargetype("Bed Charge");
				   }else{
					   appointment.setMasterchargetype("Bed Charges");
				   }
		    	   appointment.setWardid(bed.getWardid());
		    	   appointment.setBedid(bed.getBedid());
		    	   appointment.setIpdid(ipdForm.getId());
		    	   appointment.setLogid(bed.getLogid());
		    	   
		    	 
		    	
	    	   if(qty1==0){
	    		   qty1++;
	    	   }
	    	   	if(qty1<=0){
	    	   		qty1=1;
	    	   	}
		    	   
	    	   		appointment.setQuantity(qty1);

		    	   int res=completeAptmDAO.saveInvoiceAssesment(appointment, invoiceid);
		    	   
		    }
			 
			 i++;
	}
		
		
		 
		}catch (Exception e) {
			e.printStackTrace();
			
		}finally{
			connection.close();
		}
		
	}

	public void autosmsBedChange(String clietid,String wardid,String bedid, String drmobile)throws Exception{
		   

		   //String appointmentid = request.getParameter("appointmentid");
		   
		   Connection connection = null;
		   
		   try{
		      connection = Connection_provider.getconnection();
		      //send sms
		        ClientDAO clientDAO = new JDBCClientDAO(connection);
		            
		        Client client = clientDAO.getClientDetails(clietid);
		       
		       
		        String message = AllTemplateAction.getBedChangeSMSTextPatient(clietid,  connection,wardid,bedid);
		        SmsService s = new SmsService();
		        String templateid="";
		        s.sendSms(message, client.getMobNo(), loginInfo, new EmailLetterLog(),templateid);
		        s.sendSms(message, drmobile, loginInfo, new EmailLetterLog(),templateid);
		        if(client.getEmergencyContNo()!=null){
		         if(client.getEmergencyContNo()!="")
		         {
		          s.sendSms(message, client.getEmergencyContNo(), loginInfo, new EmailLetterLog(),templateid);
		         }
		         else
		         {
		          s.sendSms(message, client.getMobNo(), loginInfo, new EmailLetterLog(),templateid);
		         }
		        }
		        else
		        {
		         if(client.getEmergencyContNo()!="")
		         {
		          s.sendSms(message, client.getEmergencyContNo(), loginInfo, new EmailLetterLog(),templateid);
		         }
		         else
		         {
		          s.sendSms(message, client.getMobNo(), loginInfo, new EmailLetterLog(),templateid);
		         }
		        }
		       
		       
		        
		    
		   }catch(Exception e){
		    e.printStackTrace();
		   }
		   finally{
				connection.close();
			}
		   
		  }
	
	public void autosmsPrimaryDoctorChange(String Clientid,String Wardid,String Bedid,int Practitionerid)throws Exception{
		   

		   //String appointmentid = request.getParameter("appointmentid");
		   
		   Connection connection = null;
		   
		   try{
		      connection = Connection_provider.getconnection();
		      //send sms
		        ClientDAO clientDAO = new JDBCClientDAO(connection);
		        Client client = clientDAO.getClientDetails(Clientid);
		        UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
		              UserProfile userProfile  = userProfileDAO.getUserprofileDetails(Practitionerid);
		      
		       
		      
		        String message = AllTemplateAction.getPrimaryDoctorChangeSMSText(Clientid,connection,Wardid,Bedid,Practitionerid);
		        SmsService s = new SmsService();
		        String templateid="";
		        s.sendSms(message, userProfile.getMobile(), loginInfo, new EmailLetterLog(),templateid);
		      
		        
		    
		   }catch(Exception e){
		    e.printStackTrace();
		   }finally{
				connection.close();
			}
		   
		   
		  }
	
	
	
	public String savecasualty()throws Exception{
if(!verifyLogin(request)) {
    		
    		return "login";
    	}
    	
    	Connection connection=null;
    	
    	try {
	
    		connection=Connection_provider.getconnection();
    		BedDao bedDao=new JDBCBedDao(connection);
    		UserProfileDAO profileDAO=new JDBCUserProfileDAO(connection);
    		ClinicDAO clinicDAO= new JDBCClinicDAO(connection);
    		TreatmentEpisodeDAO treatmentEpisodeDAO=new JDBCTreatmentEpisode(connection);
    		ClientDAO clientDAO=new JDBCClientDAO(connection);
    		Bed bed=new Bed();
    		
    		for(Bed tbed:ipdForm.getConditions()) {
				
    			ipdForm.setConditionid(tbed.getConditionid());
    			break;
    			
			}
    		Client client=clientDAO.getClientDetails(ipdForm.getClientid()); 
    		CompleteAptmDAO completeAptmDAO=new JDBCCompleteAptmDAO(connection);
    		String payee=ipdForm.getPayee();
            if(payee.equals("0")){
            	payee="Client";
            	String dateTime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
            	DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); 
            	Date date=Calendar.getInstance().getTime();
            	String referalDate=df.format(date);
            	String name="IPD"+client.getFirstName()+""+client.getLastName()+dateTime;
            	TreatmentEpisode treatmentEpisode=new TreatmentEpisode();
            	treatmentEpisode.setClientId(Integer.parseInt(ipdForm.getClientid()));
            	treatmentEpisode.setPayby("Client");
            	treatmentEpisode.setConsultationLimit("3");
            	treatmentEpisode.setSessions("1");
            	treatmentEpisode.setDiaryUser(ipdForm.getPractitionerid());
            	treatmentEpisode.setTreatmentEpisodeName(name);
            	treatmentEpisode.setReferalDate(referalDate);
            	
            	Calendar calendar=Calendar.getInstance();
            	calendar.add(Calendar.DATE, 30);
            	String referalEndDate=df.format(calendar.getTime());
            	treatmentEpisode.setReferalendDate(referalEndDate);
            	treatmentEpisode.setSpendLimit("3");
            	dateTime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
            	String clientName = client.getTitle()+" "+client.getFirstName()+" "+ client.getLastName();
            	treatmentEpisode.setClientName(clientName);
            	int tretmentEpisodeId=treatmentEpisodeDAO.saveTreatmentEpisode(treatmentEpisode, dateTime);
            	ipdForm.setTreatmentepisodeid(""+tretmentEpisodeId+"");
            	bed.setTreatmentepisodeid(ipdForm.getTreatmentepisodeid());
            	bed.setTpid("0");
            } else {
            	payee="Third Party";
            	bed.setTreatmentepisodeid(ipdForm.getTreatmentepisodeid());
            	bed.setTpid(ipdForm.getTpid());
            }
            String tpid=ipdForm.getTpid();
            int r=clientDAO.updatePayeeofPatient(ipdForm.getClientid(),payee,tpid);
            
            //update emergency contact details
            Client clientcont=new Client();
            clientcont.setEmergencyContName(ipdForm.getRelativename());
            clientcont.setEmergencyContNo(ipdForm.getRelationcont());
            clientcont.setRelation(ipdForm.getRelation());
            clientcont.setId(Integer.parseInt(ipdForm.getClientid()));
            clientcont.setReference(ipdForm.getRefferedby());
            // and reference
            
            r=clientDAO.updateEmergencyDetails(clientcont);
            
    		bed.setClientid(ipdForm.getClientid());
    		bed.setPractitionerid(ipdForm.getPractitionerid());
    		bed.setConditionid(ipdForm.getConditionid());
    		
    		 if(ipdForm.getPractitionerid()!=null){
    		       if(!ipdForm.getPractitionerid().equals("")){
    		         
    		           int practitionerid=Integer.parseInt(ipdForm.getPractitionerid());
    		           UserProfile userProfile=profileDAO.getUserprofileDetails(practitionerid);
    		           bed.setSpeciality(userProfile.getDiciplineName());
    		       }
    		 }
    		
    		bed.setDepartment(ipdForm.getDepartment());
    		bed.setRefferedby(ipdForm.getRefferedby());
    		bed.setWardid(ipdForm.getWardid());
    		bed.setBedid(ipdForm.getBedid());
    		bed.setStatus(ipdForm.getStatus());
    		bed.setAddmissionfor(ipdForm.getAddmissionfor());
    		bed.setReimbursment(ipdForm.getReimbursment());
    		bed.setSecndryconsult(ipdForm.getSecndryconsult());
    		bed.setMlcno(ipdForm.getMlcno());
    		bed.setAdmissiondetails(ipdForm.getAdmissiondetails());
    		bed.setSuggestedtrtment(ipdForm.getSuggestedtrtment());
    		bed.setHosp(ipdForm.getHosp());
    		bed.setPackagename(ipdForm.getPackagename());
    		//chiefcomplains, presentillness, pasthistory, personalhist, familyhist, onexamination, treatmentepisodeid
    		bed.setChiefcomplains(ipdForm.getChiefcomplains());
    		bed.setPresentillness(ipdForm.getPresentillness());
    		bed.setPasthistory(ipdForm.getPasthistory());
    		bed.setPersonalhist(ipdForm.getPersonalhist());
    		bed.setFamilyhist(ipdForm.getFamilyhist());
    		bed.setOnexamination(ipdForm.getOnexamination());
    	    bed.setEarlierinvest(ipdForm.getEarlierinvest());
    	    bed.setAdmission_reason(ipdForm.getAdmission_reason());
    	
    		
    		
    		bed.setSuggestoper(ipdForm.getSuggestoper());
    		bed.setSystdepartment(bed.getSystdepartment());
    		bed.setFpcondition(ipdForm.getFpcondition());
    		bed.setFpnotest(ipdForm.getFpnotest());
    		bed.setNauseacondition(ipdForm.getNauseacondition());
    		bed.setNauseanotes(ipdForm.getNauseanotes());
    		bed.setFnucondition(ipdForm.getFnucondition());
    		bed.setFnunotes(ipdForm.getFnunotes());
    		bed.setSmcondition(ipdForm.getSmcondition());
    		bed.setSmnotes(ipdForm.getSmnotes());
    		bed.setChestpaincond(ipdForm.getChestpaincond());
    		bed.setChestpainnotes(ipdForm.getChestpainnotes());
    		bed.setDimvisioncond(ipdForm.getDimvisioncond());
    		bed.setDimvisionnotes(ipdForm.getDimvisionnotes());
    		
    		//dimvisionnotes, hgucondition, hgunotes, hmcondition, hmnotes, jointpaincond, jointpainnotes, 
    		bed.setHgucondition(ipdForm.getHgucondition());
    		bed.setHgunotes(ipdForm.getHgunotes());
    		bed.setHmcondition(ipdForm.getHmcondition());
    		bed.setHmnotes(ipdForm.getHmnotes());
    		bed.setJointpaincond(ipdForm.getJointpaincond());
    		bed.setJointpainnotes(ipdForm.getJointpainnotes());
    		
    		//constipationcond, constpationnotes, specialnotes, edemafeetcondi, edemafeetnotes, hematuriacondi, 
    		bed.setConstipationcond(ipdForm.getConstipationcond());
    		bed.setConstpationnotes(ipdForm.getConstpationnotes());
    		bed.setSpecialnotes(ipdForm.getSpecialnotes());
    		bed.setEdemafeetcondi(ipdForm.getEdemafeetcondi());
    		bed.setEdemafeetnotes(ipdForm.getEdemafeetnotes());
    		bed.setHematuriacondi(ipdForm.getHematuriacondi());
    		bed.setHematurianotes(ipdForm.getHematurianotes());
    		
    		//hematurianotes, bmcondition, bmnotes, oliguriacondi, oligurianotes, pstgucondi, pstgunotes, 
    		bed.setBmcondition(ipdForm.getBmcondition());
    		bed.setBmnotes(ipdForm.getBmnotes());
    		bed.setOliguriacondi(ipdForm.getOliguriacondi());
    		bed.setOligurianotes(ipdForm.getOligurianotes());
    		bed.setPstgucondi(ipdForm.getPstgucondi());
    		bed.setPstgunotes(ipdForm.getPstgunotes());
    		
    		//bmecondition, bmenotes, tnecondition, tnenotes, weaknesscondi, weaknessnotes, ihcondition, ihnotes
    		bed.setBmecondition(ipdForm.getBmecondition());
    		bed.setBmenotes(ipdForm.getBmenotes());
    		bed.setTnecondition(ipdForm.getTnecondition());
    		bed.setTnenotes(ipdForm.getTnenotes());
    		bed.setWeaknesscondi(ipdForm.getWeaknesscondi());
    		bed.setWeaknessnotes(ipdForm.getWeaknessnotes());
    		bed.setIhcondition(ipdForm.getIhcondition());
    		bed.setIhnotes(ipdForm.getIhnotes());
    		bed.setAlergies(ipdForm.getAlergies());
    		
    		//gynic details
    		bed.setAlcohal(ipdForm.getAlcohal());
    		bed.setDrugs(ipdForm.getDrugs());
    		bed.setOther_medication(ipdForm.getOther_medication());
    		bed.setTobaco(ipdForm.getTobaco());
    		bed.setTobaconotes(ipdForm.getTobaconotes());
    		bed.setSmoking(ipdForm.getSmoking());
    		
    		bed.setAge_menarche(ipdForm.getAge_menarche());
    		bed.setLmp(ipdForm.getLmp());
    		bed.setLlmp(ipdForm.getLlmp());
    		bed.setPmc(ipdForm.getPmc());
    		
    		
    		bed.setCycle_length(ipdForm.getCycle_length());
    		bed.setDuration_flow(ipdForm.getDuration_flow());
    		bed.setAmount_flow(ipdForm.getAmount_flow());
    		bed.setDysmenorrhea(ipdForm.getDysmenorrhea());
    		bed.setDyspareunia(ipdForm.getDyspareunia());
    		bed.setHopassing_clots(ipdForm.getHopassing_clots());
    		
    		bed.setWhite_disc_itching(ipdForm.getWhite_disc_itching());
    		bed.setIntercourse_freq(ipdForm.getIntercourse_freq());
    		bed.setGalactorrea(ipdForm.getGalactorrea());
    		
    		bed.setHo_contraception(ipdForm.getHo_contraception());
    		bed.setRubella_vaccine(ipdForm.getRubella_vaccine());
    		bed.setMenopause(ipdForm.getMenopause());
    		bed.setNulligravida(ipdForm.getNulligravida());
    		bed.setCurrent_pregnent(ipdForm.getCurrent_pregnent());
    		bed.setIud(ipdForm.getIud());
    		
    		bed.setLive_boys(ipdForm.getLive_boys());
    		bed.setLive_girls(ipdForm.getLive_girls());
    		bed.setStillbirths(ipdForm.getStillbirths());
    		bed.setAbortions(ipdForm.getAbortions());
    		bed.setDeath_children(ipdForm.getDeath_children());
    		
    		//parity_aboration_notes,p,l,a,d
    		bed.setParity_abortion_notes(ipdForm.getParity_abortion_notes());
    		bed.setP(ipdForm.getP());
    		bed.setL(ipdForm.getL());
    		bed.setA(ipdForm.getA());
    		bed.setD(ipdForm.getD());
    		
    		bed.setMlcrefdoctor(ipdForm.getMlcrefdoctor());
    		IpdDAO ipDao= new JDBCIpdDAO(connection);
    		if(loginInfo.isKalmegha()){
    			bed.setIpdabrivationid(bedDao.generateIPDKalmeghAbrivation(ipdForm.getAdmissiondate().split(" ")[0],ipdForm.getActiontype()));
    		}else{
    			bed.setIpdabrivationid(ipDao.generateIPDSequenceNewFormat(ipdForm.getActiontype()));
    		}
    		
    		
  		
    		String stdchargesetup= clinicDAO.getStdChargeSetup(loginInfo.getClinicid());
    		bed.setStdchargesetup(stdchargesetup);
    		String date = DateTimeUtils.getCommencingDate1(ipdForm.getAdmissiondate()) + " " + ipdForm.getHour() + ":" + ipdForm.getMinute() + ":20" ;
    		bed.setAddmitedbyuserid(loginInfo.getUserId());
    		
    		String action = "1";
    		int maxcasualtyid = bedDao.getMaxCasualtyID();
    		maxcasualtyid = maxcasualtyid+1;
    		if(ipdForm.getActiontype()!=null){
    			if(ipdForm.getActiontype().equals("2")){
    				 action = "2";
    	    		 maxcasualtyid = bedDao.getMaxDayCareId();
    	    		maxcasualtyid = maxcasualtyid+1;
    			}
    		}
            int admissionid=bedDao.addIpdAdmissionForm(bed,date,action,maxcasualtyid);
            
            
            //save gynic obs history
            if(ipdForm.getObslist()!=null){
            	 for(Bed bed2 :ipdForm.getObslist()){
                	 
            	     int res=bedDao.saveGynicObsData(admissionid,bed2);
              }
            }
           
            
            if(DateTimeUtils.isNull(action).equals("2")){
            	return "daycare";
            }
            
            saveConditions(admissionid,ipdForm.getTreatmentepisodeid());
            
    	}catch (Exception e) {
			// TODO: handle exception
		}finally{
			connection.close();
		}
    	
        
		
		return "savecasualty";
	}
	
	
    /**
     * @return
     * @throws Exception
     */
	public String saveform() throws Exception
    {
    	
    	if(!verifyLogin(request)) {
    		
    		return "login";
    	}
    	
    	Connection connection=null;
    	String action = request.getParameter("action");
    	try {
	
    		connection=Connection_provider.getconnection();
    		BedDao bedDao=new JDBCBedDao(connection);
    		UserProfileDAO profileDAO=new JDBCUserProfileDAO(connection);
    		ClinicDAO clinicDAO= new JDBCClinicDAO(connection);
    		TreatmentEpisodeDAO treatmentEpisodeDAO=new JDBCTreatmentEpisode(connection);
    		ClientDAO clientDAO=new JDBCClientDAO(connection);
    		AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
    		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
    		Bed bed=new Bed();
    		
    		/*for(Bed tbed:ipdForm.getConditions()) {
				
    			ipdForm.setConditionid(tbed.getConditionid());
    			break;
    			
			}*/
    		
    		Client client=clientDAO.getClientDetails(ipdForm.getClientid()); 
    		CompleteAptmDAO completeAptmDAO=new JDBCCompleteAptmDAO(connection);
    		String payee=ipdForm.getPayee();
           
    		if(payee.equals("0")){
            	payee="Client";
            	String dateTime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
            	DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); 
            	Date date=Calendar.getInstance().getTime();
            	String referalDate=df.format(date);
            	String name="IPD"+client.getFirstName()+""+client.getLastName()+dateTime;
            	TreatmentEpisode treatmentEpisode=new TreatmentEpisode();
            	treatmentEpisode.setClientId(Integer.parseInt(ipdForm.getClientid()));
            	treatmentEpisode.setPayby("Client");
            	treatmentEpisode.setConsultationLimit("3");
            	treatmentEpisode.setSessions("1");
            	treatmentEpisode.setDiaryUser(ipdForm.getPractitionerid());
            	treatmentEpisode.setTreatmentEpisodeName(name);
            	treatmentEpisode.setReferalDate(referalDate);
            	
            	Calendar calendar=Calendar.getInstance();
            	calendar.add(Calendar.DATE, 30);
            	String referalEndDate=df.format(calendar.getTime());
            	treatmentEpisode.setReferalendDate(referalEndDate);
            	treatmentEpisode.setSpendLimit("3");
            	dateTime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
            	String clientName = client.getTitle()+" "+client.getFirstName()+" "+ client.getLastName();
            	treatmentEpisode.setClientName(clientName);
            	int tretmentEpisodeId=treatmentEpisodeDAO.saveTreatmentEpisode(treatmentEpisode, dateTime);
            	//28 oct 2017   
            	int res = ipdDAO.saveDischargeCheckList(tretmentEpisodeId,ipdForm.getClientid());
            	ipdForm.setTreatmentepisodeid(""+tretmentEpisodeId+"");
            	bed.setTreatmentepisodeid(ipdForm.getTreatmentepisodeid());
            	bed.setTpid("0");
            } else {
            	
            	 //@ jitu from auto 
            	String dateTime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
            	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            	
            	Date date=Calendar.getInstance().getTime();
            	String referalDate=df.format(date);
            	String name="IPD"+client.getFirstName()+""+client.getLastName()+dateTime;
            	TreatmentEpisode treatmentEpisode=new TreatmentEpisode();
            	treatmentEpisode.setClientId(Integer.parseInt(ipdForm.getClientid()));
            	treatmentEpisode.setPayby("Third Party");
            	treatmentEpisode.setConsultationLimit("3");
            	treatmentEpisode.setSessions("1");
            	treatmentEpisode.setDiaryUser(ipdForm.getPractitionerid());
            	treatmentEpisode.setTreatmentEpisodeName(name);
            	treatmentEpisode.setReferalDate(referalDate);
            	
            	Calendar calendar=Calendar.getInstance();
            	calendar.add(Calendar.DATE, 30);
            	String referalEndDate=df.format(calendar.getTime());
            	treatmentEpisode.setReferalendDate(referalEndDate);
            	treatmentEpisode.setSpendLimit("50000");
            	dateTime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
            	String clientName = client.getTitle()+" "+client.getFirstName()+" "+ client.getLastName();
            	treatmentEpisode.setClientName(clientName);
            	
            	ThirdParty thirdParty= client.getTpDetails();
            	treatmentEpisode.setInvoicee(thirdParty.getCompanyName());
            	int tretmentEpisodeId=treatmentEpisodeDAO.saveTreatmentEpisode(treatmentEpisode, dateTime);
            	
            	int res = ipdDAO.saveDischargeCheckList(tretmentEpisodeId,ipdForm.getClientid());
            	ipdForm.setTreatmentepisodeid(""+tretmentEpisodeId+"");
            	bed.setTreatmentepisodeid(ipdForm.getTreatmentepisodeid());
            	
            	payee="Third Party";
            	bed.setTreatmentepisodeid(ipdForm.getTreatmentepisodeid());
            	//28 oct 2017   save discharge checklist
            	if(ipdForm.getTreatmentepisodeid()==null){
            		
            	}else if(!ipdForm.getTreatmentepisodeid().equals("0")){
            		 res = ipdDAO.saveDischargeCheckList(Integer.parseInt(ipdForm.getTreatmentepisodeid()),ipdForm.getClientid());
                	bed.setTpid(ipdForm.getTpid());
            	}
            	
            }
            String tpid=ipdForm.getTpid();
            int r=clientDAO.updatePayeeofPatient(ipdForm.getClientid(),payee,tpid);
            
            //update emergency contact details
            Client clientcont=new Client();
            clientcont.setEmergencyContName(ipdForm.getRelativename());
            clientcont.setEmergencyContNo(ipdForm.getRelationcont());
            clientcont.setRelation(ipdForm.getRelation());
            clientcont.setId(Integer.parseInt(ipdForm.getClientid()));
            clientcont.setReference(ipdForm.getRefferedby());
            // and reference
            
            r=clientDAO.updateEmergencyDetails(clientcont);
            
    		bed.setClientid(ipdForm.getClientid());
    		bed.setPractitionerid(ipdForm.getPractitionerid());
    		bed.setConditionid(ipdForm.getConditionid());
    		
    		 if(ipdForm.getPractitionerid()!=null){
    		       if(!ipdForm.getPractitionerid().equals("")){
    		         
    		           int practitionerid=Integer.parseInt(ipdForm.getPractitionerid());
    		           UserProfile userProfile=profileDAO.getUserprofileDetails(practitionerid);
    		           bed.setSpeciality(userProfile.getDiciplineName());
    		       }
    		 }
    		
    		bed.setDepartment(ipdForm.getDepartment());
    		 
    		String refenceid=ipdForm.getRefferedby(); 
    		String name= clientDAO.getReferenceName(refenceid);
    		bed.setReferenceid(refenceid);
    		
    		bed.setRefferedby(name);
    		bed.setWardid(ipdForm.getWardid());
    		bed.setBedid(ipdForm.getBedid());
    		bed.setStatus(ipdForm.getStatus());
    		bed.setAddmissionfor(ipdForm.getAddmissionfor());
    		bed.setReimbursment(ipdForm.getReimbursment());
    		bed.setSecndryconsult(ipdForm.getSecndryconsult());
    		bed.setMlccase(ipdForm.getMlccase());
    		bed.setMlcno(ipdForm.getMlcno());
    		bed.setAdmissiondetails(ipdForm.getAdmissiondetails());
    		bed.setSuggestedtrtment(ipdForm.getSuggestedtrtment());
    		bed.setHosp(ipdForm.getHosp());
    		bed.setPackagename(ipdForm.getPackagename());
    		//chiefcomplains, presentillness, pasthistory, personalhist, familyhist, onexamination, treatmentepisodeid
    		bed.setChiefcomplains(ipdForm.getChiefcomplains());
    		bed.setPresentillness(ipdForm.getPresentillness());
    		bed.setPasthistory(ipdForm.getPasthistory());
    		bed.setPersonalhist(ipdForm.getPersonalhist());
    		bed.setFamilyhist(ipdForm.getFamilyhist());
    		bed.setSurgicalnotes(ipdForm.getSurgicalnotes());
    		bed.setOnexamination(ipdForm.getOnexamination());
    	    bed.setEarlierinvest(ipdForm.getEarlierinvest());
    	    bed.setAdmission_reason(ipdForm.getAdmission_reason());
    	
    		//peditric
    	    
    	     bed.setBirthhist(ipdForm.getBirthhist());
    	     bed.setDiethist(ipdForm.getDiethist());
    		bed.setDevelopmenthist(ipdForm.getDevelopmenthist());
    		bed.setEmmunizationhist(ipdForm.getEmmunizationhist());
    		bed.setHeadcircumference(ipdForm.getHeadcircumference());
    		bed.setMidarmcircumference(ipdForm.getMidarmcircumference());
    		bed.setLength(ipdForm.getLength());
    		bed.setWtaddmission(ipdForm.getWtaddmission());
    		bed.setWtdischarge(ipdForm.getWtdischarge());
    		
    		bed.setSuggestoper(ipdForm.getSuggestoper());
    		bed.setSystdepartment(bed.getSystdepartment());
    		bed.setFpcondition(ipdForm.getFpcondition());
    		bed.setFpnotest(ipdForm.getFpnotest());
    		bed.setNauseacondition(ipdForm.getNauseacondition());
    		bed.setNauseanotes(ipdForm.getNauseanotes());
    		bed.setFnucondition(ipdForm.getFnucondition());
    		bed.setFnunotes(ipdForm.getFnunotes());
    		bed.setSmcondition(ipdForm.getSmcondition());
    		bed.setSmnotes(ipdForm.getSmnotes());
    		bed.setChestpaincond(ipdForm.getChestpaincond());
    		bed.setChestpainnotes(ipdForm.getChestpainnotes());
    		bed.setDimvisioncond(ipdForm.getDimvisioncond());
    		bed.setDimvisionnotes(ipdForm.getDimvisionnotes());
    		
    		//dimvisionnotes, hgucondition, hgunotes, hmcondition, hmnotes, jointpaincond, jointpainnotes, 
    		bed.setHgucondition(ipdForm.getHgucondition());
    		bed.setHgunotes(ipdForm.getHgunotes());
    		bed.setHmcondition(ipdForm.getHmcondition());
    		bed.setHmnotes(ipdForm.getHmnotes());
    		bed.setJointpaincond(ipdForm.getJointpaincond());
    		bed.setJointpainnotes(ipdForm.getJointpainnotes());
    		
    		//constipationcond, constpationnotes, specialnotes, edemafeetcondi, edemafeetnotes, hematuriacondi, 
    		bed.setConstipationcond(ipdForm.getConstipationcond());
    		bed.setConstpationnotes(ipdForm.getConstpationnotes());
    		bed.setSpecialnotes(ipdForm.getSpecialnotes());
    		bed.setEdemafeetcondi(ipdForm.getEdemafeetcondi());
    		bed.setEdemafeetnotes(ipdForm.getEdemafeetnotes());
    		bed.setHematuriacondi(ipdForm.getHematuriacondi());
    		bed.setHematurianotes(ipdForm.getHematurianotes());
    		
    		//hematurianotes, bmcondition, bmnotes, oliguriacondi, oligurianotes, pstgucondi, pstgunotes, 
    		bed.setBmcondition(ipdForm.getBmcondition());
    		bed.setBmnotes(ipdForm.getBmnotes());
    		bed.setOliguriacondi(ipdForm.getOliguriacondi());
    		bed.setOligurianotes(ipdForm.getOligurianotes());
    		bed.setPstgucondi(ipdForm.getPstgucondi());
    		bed.setPstgunotes(ipdForm.getPstgunotes());
    		
    		//bmecondition, bmenotes, tnecondition, tnenotes, weaknesscondi, weaknessnotes, ihcondition, ihnotes
    		bed.setBmecondition(ipdForm.getBmecondition());
    		bed.setBmenotes(ipdForm.getBmenotes());
    		bed.setTnecondition(ipdForm.getTnecondition());
    		bed.setTnenotes(ipdForm.getTnenotes());
    		bed.setWeaknesscondi(ipdForm.getWeaknesscondi());
    		bed.setWeaknessnotes(ipdForm.getWeaknessnotes());
    		bed.setIhcondition(ipdForm.getIhcondition());
    		bed.setIhnotes(ipdForm.getIhnotes());
    		bed.setAlergies(ipdForm.getAlergies());
    		
    		//gynic details
    		bed.setAlcohal(ipdForm.getAlcohal());
    		bed.setDrugs(ipdForm.getDrugs());
    		bed.setOther_medication(ipdForm.getOther_medication());
    		bed.setTobaco(ipdForm.getTobaco());
    		bed.setTobaconotes(ipdForm.getTobaconotes());
    		bed.setSmoking(ipdForm.getSmoking());
    		
    		bed.setAge_menarche(ipdForm.getAge_menarche());
    		bed.setLmp(ipdForm.getLmp());
    		bed.setLlmp(ipdForm.getLlmp());
    		bed.setPmc(ipdForm.getPmc());
    		
    		
    		bed.setCycle_length(ipdForm.getCycle_length());
    		bed.setDuration_flow(ipdForm.getDuration_flow());
    		bed.setAmount_flow(ipdForm.getAmount_flow());
    		bed.setDysmenorrhea(ipdForm.getDysmenorrhea());
    		bed.setDyspareunia(ipdForm.getDyspareunia());
    		bed.setHopassing_clots(ipdForm.getHopassing_clots());
    		
    		bed.setWhite_disc_itching(ipdForm.getWhite_disc_itching());
    		bed.setIntercourse_freq(ipdForm.getIntercourse_freq());
    		bed.setGalactorrea(ipdForm.getGalactorrea());
    		
    		bed.setHo_contraception(ipdForm.getHo_contraception());
    		bed.setRubella_vaccine(ipdForm.getRubella_vaccine());
    		bed.setMenopause(ipdForm.getMenopause());
    		bed.setNulligravida(ipdForm.getNulligravida());
    		bed.setCurrent_pregnent(ipdForm.getCurrent_pregnent());
    		bed.setIud(ipdForm.getIud());
    		
    		bed.setLive_boys(ipdForm.getLive_boys());
    		bed.setLive_girls(ipdForm.getLive_girls());
    		bed.setStillbirths(ipdForm.getStillbirths());
    		bed.setAbortions(ipdForm.getAbortions());
    		bed.setDeath_children(ipdForm.getDeath_children());
    		
    		//parity_aboration_notes,p,l,a,d
    		bed.setParity_abortion_notes(ipdForm.getParity_abortion_notes());
    		bed.setP(ipdForm.getP());
    		bed.setL(ipdForm.getL());
    		bed.setA(ipdForm.getA());
    		bed.setD(ipdForm.getD());
    		
    		bed.setMlcrefdoctor(ipdForm.getMlcrefdoctor());
    		bed.setMlccase(ipdForm.getMlccase());
    		
    		
    		//  10 July 2018 MLC No generated
    		if(bed.getMlccase()!=null){
    			if(bed.getMlccase().equals("1")){
    				//save MLC abrivation seq no
    				String cdate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
    				String tempd[] = cdate.split("-");
    				String y = tempd[0];
    				boolean checkifseq = ipdDAO.checkifSequenceExist(y);
    				String mlcabrivationid = "";
    				
    				String clinicabrivation = clientDAO.getClinicAbrivation(loginInfo.getClinicid());
    				
    				
    				if(checkifseq){
    					int seqno = ipdDAO.getSqeunceNumber(cdate);
    					seqno++;
    					int k = ipdDAO.InserCdateSeq(cdate,seqno);
    					//SNH170609001
    					int yr = Integer.parseInt(y)%1000;
    					mlcabrivationid = clinicabrivation+"MLC"+ yr + seqno;
    				}else{
    					
    					int seqno = 1;
    					int k = ipdDAO.InserCdateSeq(cdate,seqno);
    					//String seqno = clientDAO.getSqeunceNumber(cdate);
    					int yr = Integer.parseInt(y)%1000;
    					mlcabrivationid = clinicabrivation+"MLC"+ yr + seqno;
    				}
    				bed.setMlcabrivationid(mlcabrivationid);
    			}else{
    				bed.setMlcrefdoctor("");
    			}
    		}else{
    			bed.setMlcrefdoctor("");
    		}
    		
    		String stdchargesetup= clinicDAO.getStdChargeSetup(loginInfo.getClinicid());
    		bed.setStdchargesetup(stdchargesetup);
    		String date = DateTimeUtils.getCommencingDate1(ipdForm.getAdmissiondate()) + " " + ipdForm.getHour() + ":" + ipdForm.getMinute() + ":20" ;
    		bed.setAddmitedbyuserid(loginInfo.getUserId());
    		
    		String action1 = "0";
    		int maxid = bedDao.getMaxIpdId();
    		maxid = maxid + 1;
    		
    		//bed.setIpdabrivationid(ipdDAO.generateIPDSequenceNewFormat(action));
    		if(loginInfo.isKalmegha()){
    			bed.setIpdabrivationid(bedDao.generateIPDKalmeghAbrivation(ipdForm.getAdmissiondate().split(" ")[0],ipdForm.getActiontype()));
    		}else{
    			bed.setIpdabrivationid(ipdDAO.generateIPDSequenceNewFormat(ipdForm.getActiontype()));
    		}
    		boolean checkbedidexsist = bedDao.checkBedidExixtst(ipdForm.getBedid());
    		if(!checkbedidexsist){
    			 int admissionid=bedDao.addIpdAdmissionForm(bed,date,action1,maxid);
    		
           //ward charges change
    			 if(!ipdForm.getWardid().equals(ipdForm.getRatewardid())){
    				/* ipdDAO.rateChangeFlagWard(""+admissionid,"1");
    				 Bed bed1= bedDao.getEditIpdData(""+admissionid);
    				 ipdDAO.rateChangeWardFromMaster(ipdForm.getRatewardid(), bed1.getBedid());*/
    			 }
            
            if(action.equals("1")){
            	int up = bedDao.updateCasualtybedEmpty(ipdForm.getId());
            }
            String shiftedfromcasualty=request.getParameter("ttyy");
            if(shiftedfromcasualty!=null){
            	int casual=bedDao.updateCasualtybedEmptyByClient(ipdForm.getClientid());
            }
            
            //save gynic obs history
            if(ipdForm.getObslist()!=null){
            	 for(Bed bed2 :ipdForm.getObslist()){
                	 
            	     int res=bedDao.saveGynicObsData(admissionid,bed2);
              }
            }
           
            //On auto charge
            int res11111=accountsDAO.updateAutochargeFlagClient(ipdForm.getClientid(),"1");
            
            //save log
            String logcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
            int log = bedDao.saveAdmissionLog(bed,date,admissionid);
            
            String cutofftime = bedDao.getHospitalCutoffTime(loginInfo.getClinicid());
            String logdate = DateTimeUtils.getCommencingDate1(ipdForm.getAdmissiondate()) + " " + cutofftime + ":20" ;
            if(!cutofftime.equals("0")){
            	int log1 = bedDao.saveBedChangeLog(bed,logdate,admissionid,logcommencing,date,0);
            }else{
            	int log1 = bedDao.saveBedChangeLog(bed,date,admissionid,logcommencing,date,0);
            }
            
            
            if(!client.getCasualtyid().equals("0")){
            	
            	int updc = bedDao.updateCasualtyid(client.getId());
            }
            
            
            saveConditions(admissionid,ipdForm.getTreatmentepisodeid());
            
    		//getting registration charge
    		Clinic registrationcharge=completeAptmDAO.getIpdRegistrationCharge(loginInfo.getClinicid()); 
    		
    		if(registrationcharge.getIpdregcharge()!=null && registrationcharge.getIpdregtype()!=null){
    			
    			  int amt=Integer.parseInt(registrationcharge.getIpdregcharge());  
    			  String ctype=registrationcharge.getIpdregtype();
    			  
    			  if(!ctype.equals("None")){
    				  
    				   if(ctype.equals("All")){
    					   
    					   saveCharges(String.valueOf(amt), bed, client, admissionid, completeAptmDAO,ipdForm.getAdmissiondate());
    					   
    				   }
    				   else if(ctype.equals("Third Party")){
    					   
    					   if(client.getWhopay().equalsIgnoreCase("Third Party")) {
    						     
    						      saveCharges(String.valueOf(amt), bed, client, admissionid, completeAptmDAO,ipdForm.getAdmissiondate());
    						   
    					   }
    				   }
    				   else if(ctype.equals("Self")){
    					   
    					   if(client.getWhopay().equalsIgnoreCase("Self") || client.getWhopay().equalsIgnoreCase("Client")){
    						   
    			                 			    
    						   saveCharges(String.valueOf(amt), bed, client, admissionid, completeAptmDAO,ipdForm.getAdmissiondate());
    					   }
    				   }
    				  
    			  }
    			  
    		}
    		if(bed.getMlccase()!=null){
    			if(bed.getMlccase().equals("1")){
    				//lokesh mlc charge reg 2/11/18
    	      		if(registrationcharge.getMlc_charge()!=0){
    	      			saveChargesMLC(String.valueOf(registrationcharge.getMlc_charge()),bed, client, admissionid, completeAptmDAO,ipdForm.getAdmissiondate());
    	      		}
    			}
    			}
    		
    		
    	   if(tpid==null){
           	  tpid="0";
           }
           if(tpid.equals("")){
            	tpid="0";
           }
           
           
   		//adding charges
   		//old code
   		//ArrayList<Master> chargeList=completeAptmDAO.getStandardCharges(bed.getWardid(), client.getWhopay());
           AppointmentTypeDAO appointmentTypeDAO= new JDBCAppointmentTypeDAO(connection);
   		   	
           
   		//new code
           ArrayList<Master> chargeList=appointmentTypeDAO.getStandardChargeList(ipdForm.getWardid(), tpid, payee,loginInfo);
    		
    		String stdcharges="0";
    		int invoiceid=0;
    		
    		
    		/*String date1 = DateTimeUtils.getDateinSimpleFormate(new Date());
			String stemp[] = date1.split(" ");
			
			String temp[] = stemp[0].split("-");
			date1 = temp[2] + "-" + temp[1] + "-" + temp[0];*/
    		
    		String date1 = DateTimeUtils.getCommencingDate1(ipdForm.getAdmissiondate());
			
    		
    		if(chargeList.size()!=0){ 
    			
    			CompleteAppointment appointment=new CompleteAppointment();
    			appointment.setClientId(bed.getClientid());
    			appointment.setPractitionerId(bed.getPractitionerid());
    			appointment.setChargeType("Charge");
    			appointment.setLocation("1");
    		    appointment.setAdditionalcharge_id("001");
    		    appointment.setIpdid(admissionid);
    		    appointment.setInvoiceDate(date1);
    		    appointment.setIpd("1");
    		    appointment.setAppointmentid("0");
    		    appointment.setGinvstid("0");
    		    appointment.setWardid(bed.getWardid());
    		    if(client.getWhopay()!=null){
    		    	
    		    	if(client.getWhopay().equals("Self") || client.getWhopay().equals("Client")){
    		    	       
    		    		appointment.setPolicyExcess("0");
    		    		appointment.setPayBuy("0");
    		    	} else {
    		    		appointment.setPolicyExcess("1");
    		    		appointment.setPayBuy("1");
    		    	}
    		    }
    		    
    		    if(stdchargesetup.equals("0")){
    		    	invoiceid=completeAptmDAO.saveStndCharge(appointment.getClientId(), String.valueOf(admissionid), stdcharges);
        		    
        		    invoiceid=completeAptmDAO.saveAmpmInvoice(appointment,loginInfo.getId(),loginInfo.getUserId());
    		    }
    		        		    
    		    String nowDate=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
    		    String n1 = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[1];
    		    String datetime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
    			String stddate= DateTimeUtils.getCommencingDate1(ipdForm.getAdmissiondate());
    			String sdate = ipdForm.getAdmissiondate() + " " + ipdForm.getHour() + ":" + ipdForm.getMinute() + ":00";
    			  
                if(!cutofftime.equals("0")){
                	sdate = ipdForm.getAdmissiondate() + " " + cutofftime + ":00";
                }
    			String edate = DateTimeUtils.getCommencingDate1(nowDate) + " "+ n1;
    			long diff= DateTimeUtils.getDifferenceOfTwoDateDBFormat(stddate, nowDate);
    			int qty =(int) diff;
    			if(qty<0){
    				 qty=0;
    			}
    			
    			if(qty==0){
    				qty=1;
    			} else {
    				qty++;
    			}
    			
    		    
    		    String fullname=client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
    		    appointment.setUser(fullname);
    		    appointment.setCommencing(date1);   
    		    String dt2=date1;
    		    for(Master master:chargeList){
    		    	  
    		    	   String chargeId=String.valueOf(master.getId());
    		    	   appointment.setApmtType(master.getName());
    		    	   appointment.setCharges(master.getCharge());
    		    	   appointment.setAdditionalcharge_id(chargeId);
    		    	   if(loginInfo.getIskunal()!=1){
    		    		   appointment.setMasterchargetype("Bed Charge");
    				   }else{
    					   appointment.setMasterchargetype("Bed Charges");
    				   }
    		    	   //appointment.setMasterchargetype("Accommodation Charges");
    		    	   appointment.setIpd(Integer.toString(admissionid));
    		    	   appointment.setWardid(ipdForm.getWardid());
    		    	  
    		    	   qty = DateTimeUtils.getDifferanceofDateWithTime(sdate, edate, master.getChargehours());
    		    	   int newqty=qty;
    		    	   if(qty==0){
    		    		   qty++;
    		    	   }
    		    	  
    		    	   appointment.setQuantity(qty);
    		    	   appointment.setStdflag("1");
    		    	   int res=0;
    		    	   if(stdchargesetup.equals("0")){
//    		    		   if(loginInfo.getIskunal()==1){
    		    			   //appointment.setMasterchargetype(master.getMasterchargetype());
    		    		   if(qty>0){
    		    			   for(int i=0;i<=newqty;i++){
    		    				   int w=1;
   		    				    	if(i==0){
   		    				    		w=0;
   		    				    		appointment.setCommencing(dt2);
   		    				    	}
    		    				   appointment.setQuantity(1);
    		    				   SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
    		    				   Date d=sdf1.parse(appointment.getCommencing());
    		    				   Calendar cal = Calendar.getInstance();
    		    				    
    		    				    cal.setTime(d);
    		    				    cal.add(Calendar.DATE, w);
    		    				    String dt=sdf1.format(cal.getTime());
    		    				    appointment.setCommencing(dt);    
    		    				    res=completeAptmDAO.saveInvoiceAssesment(appointment, invoiceid); 
    		    			   }
    		    			   
    		    			   }
    		    			   
//    		    		   }else{
//    		    			   res=completeAptmDAO.saveInvoiceAssesment(appointment, invoiceid); 
//    		    		   }
//    		    		   int res=completeAptmDAO.saveInvoiceAssesment(appointment, invoiceid);
        		    	   
        		    	   int result= appointmentTypeDAO.saveStdCharge(String.valueOf(admissionid),chargeId,res,"1",datetime,"");
    		    	   }
    		    	   
    		    }
    		    
    		 //   int upd = appointmentTypeDAO.setInprocessforNewShiftCharges(invoiceid,log1);
    		    
    		}
            
            //sending sms to practitioner
            
            
            DiagnosisDAO diagnosisDAO=new JDBCDiagnosisDAO(connection);
            
            String fullname=client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
            UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
            String wardname=ipdDAO.getIpdWardName(bed.getWardid());
            String bedname=ipdDAO.getIpdBedName(bed.getBedid());
            String condition=diagnosisDAO.getDiagnosisName(bed.getConditionid()).getName();
            
            UserProfile profile=userProfileDAO.getUserprofileDetails(Integer.parseInt(bed.getPractitionerid()));
            String datetime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
            String time[]=datetime.split(" ");
            
//            boolean smsisActive=clinicDAO.isSmsActive(loginInfo.getId());
            
//            if(smsisActive)
            if(loginInfo.isSms_on_newadm()==true){
            	String message="New patient "+fullname+" admitted to "+wardname+" ward bed "+bedname+", condition "+condition+" at "+time[1]+"";
            	SmsService smsService=new SmsService();
            	String templateid="";
            	smsService.sendSms(message, profile.getMobile(), loginInfo, new EmailLetterLog(),templateid);
            	//sms sent
            }
    		
            //  27 Nov 2017 
    		
    		String chiefcomplatetempname = ipdForm.getChiefcomplatetempname();
    		String presentillnesstempname = ipdForm.getPresentillnesstempname();
    		String pasthistorytempname = ipdForm.getPasthistorytempname();
    		String personalhistorytempname = ipdForm.getPersonalhistorytempname();
    		String familyhistorytempname = ipdForm.getFamilyhistorytempname();
    		String onexaminationtempname = ipdForm.getOnexaminationtempname();
    		String treatmentgiventempname = ipdForm.getTreatmentgiventempname();
    		
    		if(chiefcomplatetempname!=null){
    			if(!chiefcomplatetempname.equals("")){
    				int res = ipdDAO.saveIPDTemplate(chiefcomplatetempname,"1",ipdForm.getDepartment(),ipdForm.getChiefcomplains());
    			}
    		}
    		if(presentillnesstempname!=null){
    			if(!presentillnesstempname.equals("")){
    				int res = ipdDAO.saveIPDTemplate(presentillnesstempname,"2",ipdForm.getDepartment(),ipdForm.getPresentillness());
    			}
    		}
    		if(pasthistorytempname!=null){
    			if(!pasthistorytempname.equals("")){
    				int res = ipdDAO.saveIPDTemplate(pasthistorytempname,"3",ipdForm.getDepartment(),ipdForm.getPasthistory());
    			}
    			
    		}
    		if(personalhistorytempname!=null){
    			if(!personalhistorytempname.equals("")){
    				int res = ipdDAO.saveIPDTemplate(personalhistorytempname,"5",ipdForm.getDepartment(),ipdForm.getPersonalhist());
    			}
    			
    		}
    		if(familyhistorytempname!=null){
    			if(!familyhistorytempname.equals("")){
    				int res = ipdDAO.saveIPDTemplate(familyhistorytempname,"4",ipdForm.getDepartment(),ipdForm.getFamilyhist());
    			}
    			
    		}
    		if(onexaminationtempname!=null){
    			if(!onexaminationtempname.equals("")){
    				int res = ipdDAO.saveIPDTemplate(onexaminationtempname,"6",ipdForm.getDepartment(),ipdForm.getOnexamination());
    			}
    		}
    		if(treatmentgiventempname!=null){
    			if(!treatmentgiventempname.equals("")){
    				int res = ipdDAO.saveIPDTemplate(treatmentgiventempname,"7",ipdForm.getDepartment(),ipdForm.getTreatmentepisodeid());
    			}
    		}
    		
    	}
    	session.setAttribute("ipddash_forreload", "1");
    		
    	if(ipdForm.getLocationshift().equals("2")){
    		return "daycare";
    	}	
		} catch (Exception e) {
	        e.printStackTrace();	
		}
    	finally{
    		connection.close();
    	}
    	return "save";
  }
    
    
    private void saveCharges(String amount,Bed bed,Client client,int ipdid,CompleteAptmDAO completeAptmDAO,String admissiondate)throws Exception {
    	
    	try {
			
    		int invoiceid=0;
    		
    		
    		/*String date1 = DateTimeUtils.getDateinSimpleFormate(new Date());
    		String stemp[] = date1.split(" ");
    		
    		String temp[] = stemp[0].split("-");
    		date1 = temp[2] + "-" + temp[1] + "-" + temp[0];*/
    		
    		String date1 = DateTimeUtils.getCommencingDate1(admissiondate);
    		
    			CompleteAppointment appointment=new CompleteAppointment();
    			appointment.setClientId(bed.getClientid());
    			appointment.setPractitionerId(bed.getPractitionerid());
    			appointment.setChargeType("Charge");
    			appointment.setLocation("1");
    		    appointment.setAdditionalcharge_id("0");
    		    appointment.setIpdid(ipdid);
    		    appointment.setInvoiceDate(date1);
    		    appointment.setIpd("1");
    		    appointment.setAppointmentid("0");
    		    appointment.setWardid(bed.getWardid());
    		    if(client.getWhopay()!=null){
    		    	
    		    	if(client.getWhopay().equals("Self") || client.getWhopay().equals("Client")){
    		    	       
    		    		appointment.setPolicyExcess("0");
    		    		appointment.setPayBuy("0");
    		    	} else {
    		    		appointment.setPolicyExcess("1");
    		    		appointment.setPayBuy("1");
    		    	}
    		    }
    		    		    
    		    invoiceid=completeAptmDAO.saveAmpmInvoice(appointment,loginInfo.getId(),loginInfo.getUserId());
    		    
    		    String fullname=client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
    		    appointment.setUser(fullname);
    		    appointment.setCommencing(date1);     
    		    	  
    		   appointment.setApmtType("Ipd Registration Charge");
    		   appointment.setCharges(amount);
    		   appointment.setAdditionalcharge_id("0");
    		   appointment.setMasterchargetype("Ipd Registration Charge");
    		   appointment.setQuantity(1);
    		   int res=completeAptmDAO.saveInvoiceAssesment(appointment, invoiceid);
    		
		} catch (Exception e) {

			e.printStackTrace();
		}
    	
    }
    
    
    
    
 public void saveConditions(int admissionid,String treatmentepisodeid) throws Exception{
		
		Connection connection=null;
		
		try {
			connection=Connection_provider.getconnection();
            BedDao bedDao=new JDBCBedDao(connection);
            //for report
            int d=bedDao.deleteIpdConditionifExist(admissionid,treatmentepisodeid);
            
            String lastmodified=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
            int i = 0;
			for(Bed bed:ipdForm.getConditions()) {
				
				if(bed==null){
					continue;
				}
				bed.setLastmodified(lastmodified);
				bed.setTreatmentepisodeid(treatmentepisodeid);
				if(i==0){
					int upd = bedDao.updateIpdCondition(admissionid,bed.getConditionid());
				}
			     int result=bedDao.addCondition(admissionid,bed);
			     result=bedDao.addIpdConditionReport(admissionid,bed); 
			    i++;
			}
		
		} catch (Exception e) {

		    e.printStackTrace();
		}
		finally {
			connection.close();
		}
 }
		
		
public String end()throws Exception{
	 if(!verifyLogin(request)) {
	 		return "login";
	 	}
	     Connection connection=null;
		
		try {
			connection=Connection_provider.getconnection();
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			BedDao bedDao = new JDBCBedDao(connection);
			ClientDAO clientDAO= new JDBCClientDAO(connection);
			
			
			
			
			String sessionadmissionid = ipdForm.getClientip();
			String clietid = ipdForm.getClientid();
			String treatmentepisode = ipdForm.getTreatmentEpisode();
			
			Client client= clientDAO.getClientDetails(clietid);
			Bed bed = bedDao.getEditIpdData(sessionadmissionid);
			
			/*String dischargedate = ipdDAO.getIpdDischargeDate(treatmentepisode);
			*/
			String dischargedate= DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			
			 int res = bedDao.updateBedStatus(sessionadmissionid);
			 int log = bedDao.saveDischargeLog(sessionadmissionid,clietid,dischargedate,bed.getBedid(),loginInfo);
			 
			 //set treatment sttaus
			 int update = ipdDAO.updateDischaregeStatus(treatmentepisode,dischargedate);
			 
			 //update in process charges
			 update = ipdDAO.updateInProcessCharges(sessionadmissionid);
			  int rest=bedDao.updateUseridInTable(loginInfo.getUserId(), bed.getTreatmentepisodeid(), "endeduserid");
			
			  
			  DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			    Calendar cal = Calendar.getInstance();
			    
			    String dtt = dateFormat.format(cal.getTime());
			  SmsService s = new SmsService();
			  String msg=client.getFullname()+" : You have successfully completed discharge process on :"+dtt;
			  if(loginInfo.isDischarge_msg_hs()){
				  String templateid="";
				  s.sendSms(msg, client.getMobNo(), loginInfo, new EmailLetterLog(),templateid);
			  }
			 
			  
			  
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		 return "save";
}

public String updatedischarge()throws Exception{
	 
	 if(!verifyLogin(request)) {
	 		return "login";
	 	}
	     Connection connection=null;
		
		try {
			
			
			
			connection=Connection_provider.getconnection();
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			EmrDAO emrDAO = new JDBCEmrDAO(connection);
			BedDao bedDao = new JDBCBedDao(connection);
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
		    String sessionadmissionid = ipdForm.getClientip();
		    
//			System.out.println(sessionadmissionid);
			
			session.setAttribute("qipd", sessionadmissionid);
			Bed bed = bedDao.getEditIpdData(sessionadmissionid);
			String patientid = bed.getClientid();
			String treatmentEpisodeid = bed.getTreatmentepisodeid();
			String condition = bed.getConditionid();
			String practionerId = bed.getPractitionerid();
			String otnotesid = ipdForm.getOtNotesids();
			String priscids= ipdForm.getPriscid();
			
			for(String notesid: otnotesid.split(",")){
				if (notesid.equals("0")) {
					continue;
			}
				int otnote= ipdDAO.updateotnotesid(notesid);
			}
			TreatmentTypeDAO treatmentTypeDAO=new JDBCTreatmentTypeDAO(connection);
			ArrayList<TreatmentType> conditionlist=treatmentTypeDAO.getConditionList();
			
			ipdForm.setConditionlist(conditionlist);
			
			session.setAttribute("sessionselectedclientid", bed.getClientid());
			
			//update status
			
			String datetime = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			int valuetoupdate = 1;
			
			
			int update = ipdDAO.updateInitialDischargeStatus("dis_form_status","dis_form_time",valuetoupdate,datetime,bed.getTreatmentepisodeid(),loginInfo.getUserId());
	
			
			/*		Bed bed = new Bed();
			bed.setHospitalcourse(ipdForm.getHospitalcourse());
			
			
			bed.setDischargedate(dischargedate);
			bed.setHour(ipdForm.getHour());
			bed.setMinute(ipdForm.getMinute());
			bed.setDischargeStatus(ipdForm.getDischargeStatus());
			bed.setDischrgeOutcomes(ipdForm.getDischrgeOutcomes());
			bed.setDiscadvnotes(ipdForm.getDiscadvnotes());
			bed.setChkDischarge(ipdForm.isChkDischarge());*/
			
			Bed bed2 = new Bed();
			bed2.setDischrgeOutcomes(ipdForm.getDischrgeOutcomes());
			bed2.setDischargeStatus(ipdForm.getDischargeStatus());
			//bed2.setTreatmentepisodeid(ipdForm.getHospitalcourse());
			String hcourse = ipdForm.getHospitalcourse();
			bed2.setHospitalcourse(ipdForm.getHospitalcourse());
			bed2.setDiscadvnotes(ipdForm.getDiscadvnotes());
			bed2.setFindondischarge(ipdForm.getFindondischarge());
			if(ipdForm.getTreatmentgiven()!=null){
				if(ipdForm.getTreatmentgiven().equals("<br>")){
					ipdForm.setTreatmentgiven(null);
				}
			}
			bed2.setTreatmentgiven(ipdForm.getTreatmentgiven());
			if(ipdForm.getInvestigation()!=null){
				if(ipdForm.getInvestigation().equals("<br>")){
					ipdForm.setInvestigation(null);
				}
			}
			bed2.setInvestigation(ipdForm.getInvestigation());
			bed2.setOtNotes(ipdForm.getOtNotes());
			bed2.setAppointmentText(ipdForm.getAppointmentText());
			bed2.setAnesthesia(ipdForm.getAnesthesia());
			bed2.setSurgeon(ipdForm.getSurgeon());
			bed2.setAnesthesiologist(ipdForm.getAnesthesiologist());
			bed2.setTreatmentepisodeid(treatmentEpisodeid);
			bed2.setExample(ipdForm.getExample());
			bed2.setDischargebyid(loginInfo.getUserId());
			bed2.setDeathnote(ipdForm.getDeathnote());
			bed2.setEmergencydetail(ipdForm.getEmergencydetail());
			int status = 0;
			String dischargedate = DateTimeUtils.getCommencingDate1(ipdForm.getDischargedate()) + " " + ipdForm.getHour() + ":" + ipdForm.getMinute() + ":20" ;
			
			bed2.setDischargedate(dischargedate);
			int disstatus = ipdDAO.gettreatmentstatus(bed.getTreatmentepisodeid());
			if(disstatus==1){
				status = 1;
			}
//			if(ipdForm.isChkDischarge()){
//		        	status = 1;
//		     }
			
			if(status==1){
				 int res = bedDao.updateBedStatus(sessionadmissionid);
				 int log = bedDao.saveDischargeLog(sessionadmissionid,patientid,dischargedate,"",loginInfo);
			}
			
			bed2.setStatus(""+status);
			
			if(bed2.getAppointmentText()!=null){
				if(bed2.getAppointmentText().equals("0,") || bed2.getAppointmentText().equals("0, ")){
					bed2.setAppointmentText("0");
				}
			}
			String test = bed2.getAppointmentText();
			if(status==1){
		        	
				  	/*int upd = emrDAO.updateTreatmentEpisodeSischargeStatus(ipdForm.getDischrgeOutcomes(),
		        			ipdForm.getDischargeStatus(),status,treatmentEpisodeid,dischargedate,
		        			ipdForm.getHospitalcourse(),ipdForm.getDiscadvnotes(),ipdForm.getFindondischarge(),ipdForm.getTreatmentgiven(),
		        			ipdForm.getInvestigation(),ipdForm.getOtNotes());*/
					int upd = emrDAO.updateTreatmentEpisodeDischargeForm(bed2);
		        	
		        	AllTemplateAction allTemplateAction = new AllTemplateAction();
		        	allTemplateAction.sendDischargeEmail(patientid,condition,practionerId,"0",loginInfo,connection,treatmentEpisodeid);
		        }else{
		        	/*emrDAO.updateTreatmentEpisodeSischargeStatus(ipdForm.getDischrgeOutcomes(),
		        			ipdForm.getDischargeStatus(),status,treatmentEpisodeid,dischargedate,
		        			ipdForm.getHospitalcourse(),ipdForm.getDiscadvnotes(),ipdForm.getExample(),ipdForm.getFindondischarge(),ipdForm.getTreatmentgiven(),ipdForm.getInvestigation(),ipdForm.getOtNotes());*/
		        	int upd = emrDAO.updateTreatmentEpisodeDischargeForm(bed2);
		        }
			
			  
			   //update discharge ot notes and data
			    NotAvailableSlotDAO notAvailableSlotDAO= new JDBCNotAvailableSlotDAO(connection);
			    NotAvailableSlot notAvailableSlot= new NotAvailableSlot();
			    notAvailableSlot.setProcedure(ipdForm.getProcedure());
			    notAvailableSlot.setAnesthesia(ipdForm.getAnesthesia());
			    notAvailableSlot.setSurgeon(ipdForm.getSurgeon());
			    notAvailableSlot.setOtnotes(ipdForm.getOtNotes());
			    notAvailableSlot.setApmttypetext(ipdForm.getAppointmentText());
	      		ipdForm.setAnsintime(notAvailableSlot.getAnsintime());
	      		
	      		int res= notAvailableSlotDAO.updateDischargeOtData(sessionadmissionid,notAvailableSlot);
			  
			  datetime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			  
			  // for update delete previous conditions @jitu
			  res=ipdDAO.deleteFinalConditions(sessionadmissionid,treatmentEpisodeid);
			  res=ipdDAO.deleteConditionReport(sessionadmissionid,treatmentEpisodeid); 
			  
			  String allconditions="0";
			  if(ipdForm.getConditions()!=null){
			  for(Bed bedcondition:ipdForm.getConditions()) {
				  
				  if(bedcondition==null){
					  continue;
				  }
				  allconditions=allconditions+","+bedcondition.getConditionid();
				  bedcondition.setTreatmentepisodeid(treatmentEpisodeid);
				  bedcondition.setLastmodified(datetime);
				  
				  boolean isexist=bedDao.isIpdExistCondition(sessionadmissionid,treatmentEpisodeid,bedcondition.getConditionid());
				  if(!isexist){
					  int dd=bedDao.addIpdConditionReport(Integer.parseInt(sessionadmissionid), bedcondition);   
				  }
			  }}
			  int result=ipdDAO.savefinalConditionDischarge(sessionadmissionid,treatmentEpisodeid,datetime,allconditions);
			  
			  //23 jan 18   sms comment
			  //autosmsDischarge(patientid);
			   
				//  06 nov 2017
				String userid =  loginInfo.getUserId();
				SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar calendar=Calendar.getInstance();
				String todate=dateFormat.format(calendar.getTime());
				boolean flag = ipdDAO.getIsDisCheckListStatus("1",bed.getTreatmentepisodeid());
				
				if(!flag){
					int res99 = ipdDAO.updateCheckListStatus("1","1",bed.getTreatmentepisodeid(),userid,todate);
					int res1 = ipdDAO.updateCheckListStatus("1","2",bed.getTreatmentepisodeid(),userid,todate);
					int res2 = ipdDAO.updateCheckListStatus("1","3",bed.getTreatmentepisodeid(),userid,todate);
					int res3 = ipdDAO.updateCheckListStatus("1","4",bed.getTreatmentepisodeid(),userid,todate);
					int disdataid1 = ipdDAO.getDisDataId("1",bed.getTreatmentepisodeid());
					int disdataid2 = ipdDAO.getDisDataId("2",bed.getTreatmentepisodeid());
					int disdataid3 = ipdDAO.getDisDataId("3",bed.getTreatmentepisodeid());
					int disdataid4 = ipdDAO.getDisDataId("4",bed.getTreatmentepisodeid());
					int res6 =  ipdDAO.updateDischargeCKLIndi(disdataid1);
					int res7 =  ipdDAO.updateDischargeCKLIndi(disdataid2);
					int res8 =  ipdDAO.updateDischargeCKLIndi(disdataid3);
					int res9 =  ipdDAO.updateDischargeCKLIndi(disdataid4);
				}
				
				if(ipdForm.getHospitalcourse()!=null){
					if(!ipdForm.getHospitalcourse().equals("")){
						if(!ipdForm.getHospitalcourse().equals("<br>")){
							
						boolean flag2 = ipdDAO.getIsDisCheckListStatus("5",bed.getTreatmentepisodeid());
						if(!flag2){
							int res4 = ipdDAO.updateCheckListStatus("1","5",bed.getTreatmentepisodeid(),userid,todate);
							int disdataid4 = ipdDAO.getDisDataId("5",bed.getTreatmentepisodeid());
							int res8 =  ipdDAO.updateDischargeCKLIndi(disdataid4);
						}else{
							int res9 = ipdDAO.updateCheckListStatusSystemModify("1","5",bed.getTreatmentepisodeid(),userid, todate,"true");
						}
						}
					}
				}
				for(String priscid: priscids.split(",") ){
					if(priscid.equals("0")){
						continue;
					}
					int prisc= ipdDAO.updateprisc(priscid);
				}
				String invstids =ipdForm.getInvstids();
				for(String invstid: invstids.split(",") ){
					if(invstid.equals("0")){
						continue;
					}
					int invst1= ipdDAO.updateinvst(invstid);
				}
				
				String rmonotesids =ipdForm.getRmonotesids();
				for(String rmonotesid: rmonotesids.split(",") ){
					if(rmonotesid.equals("0")){
						continue;
					}
					int invst1= ipdDAO.updateRMONotesDisplayed(rmonotesid);
				}
				
				//29 NOV 2017   
				
				if(ipdForm.getDisdefaulttempname()!=null){
	    			if(!ipdForm.getDisdefaulttempname().equals("")){
	    				String discharge_default_id=masterDAO.getIpdTemplateId("Discharge Default");
	    				int res5 = ipdDAO.saveIPDTemplate(ipdForm.getDisdefaulttempname(),discharge_default_id,ipdForm.getDepartment(),ipdForm.getExample());
	    			}
	    		}
				if(ipdForm.getHospitalcoursetempname()!=null){
	    			if(!ipdForm.getHospitalcoursetempname().equals("")){
	    				String hospital_course_id=masterDAO.getIpdTemplateId("Hospital Course");
	    				int res5 = ipdDAO.saveIPDTemplate(ipdForm.getHospitalcoursetempname(),hospital_course_id,ipdForm.getDepartment(),ipdForm.getHospitalcourse());
	    			}
	    		}
				if(ipdForm.getNursingadvicetempname()!=null){
	    			if(!ipdForm.getNursingadvicetempname().equals("")){
	    				String nursing_advice_id=masterDAO.getIpdTemplateId("Nursing Advice");
	    				int res5 = ipdDAO.saveIPDTemplate(ipdForm.getNursingadvicetempname(),nursing_advice_id,ipdForm.getDepartment(),ipdForm.getDiscadvnotes());
	    			}
	    		}
				if(ipdForm.getInvestigationtempname()!=null){
	    			if(!ipdForm.getInvestigationtempname().equals("")){
	    				String invenstigations= masterDAO.getIpdTemplateId("Investigations");
	    				int res5 = ipdDAO.saveIPDTemplate(ipdForm.getInvestigationtempname(),invenstigations,ipdForm.getDepartment(),ipdForm.getInvestigation());
	    			}
	    		}
				if(ipdForm.getFindingondistempname()!=null){
	    			if(!ipdForm.getFindingondistempname().equals("")){
	    				String finding_on_discharge= masterDAO.getIpdTemplateId("FINDING ON DISCHARGE");
	    				int res5 = ipdDAO.saveIPDTemplate(ipdForm.getFindingondistempname(),finding_on_discharge,ipdForm.getDepartment(),ipdForm.getFindondischarge());
	    			}
	    		}
				
				UserProfile userProfile=userProfileDAO.getUserprofileDetails(Integer.parseInt(ipdForm.getPractitionerid()));
				if(ipdForm.getOperativetempname()!=null){
	    			if(!ipdForm.getOperativetempname().equals("")){
	    				/*Master master = new Master();
	    				master.setOther_template_text(ipdForm.getOtNotes());
	    				master.setTitle(ipdForm.getOperativetempname());
	    				master.setDiscipline_id(userProfile.getDiciplineName());
	    				int res5 = masterDAO.addOtherTemplate(master);*/
	    				String surgical_template = masterDAO.getIpdTemplateId("OT Template");
	    				int res5 = ipdDAO.saveIPDTemplate(ipdForm.getOperativetempname(),surgical_template,ipdForm.getDepartment(),ipdForm.getOtNotes());
	    			}
	    		}
			  
				//  27 Nov 2017 
				String admissionfr = ipdForm.getAddmissionfor();
				String alergies = ipdForm.getAlergies();
	    		String chiefcomplatetempname = ipdForm.getChiefcomplatetempname();
	    		String presentillnesstempname = ipdForm.getPresentillnesstempname();
	    		Bed bed3 = new Bed();
	    		bed3.setAddmissionfor(ipdForm.getAddmissionfor());
	    		bed3.setAlergies(ipdForm.getAlergies());
	    		bed3.setChiefcomplains(ipdForm.getChiefcomplains());
	    		bed3.setPresentillness(ipdForm.getPresentillness());
	    		bed3.setIpdid(sessionadmissionid);
	    		if(ipdForm.getSuggestedtrtment()!=null){
	    			if(ipdForm.getSuggestedtrtment().equals("<br>")){
	    				ipdForm.setSuggestedtrtment(null);
	    			}
	    		}
	    		bed3.setSuggestedtrtment(ipdForm.getSuggestedtrtment());
	    		//bed3.setSuggestedtrtment(ipdForm.getTreatmenthistory());
	    		bed3.setEarlierinvest(ipdForm.getEarlierinvest());
	    		bed3.setSurgicalnotes(ipdForm.getSurgicalnotes());
	    		bed3.setDiethist(ipdForm.getDiethist());
	    		bed3.setEmmunizationhist(ipdForm.getEmmunizationhist());
	    		bed3.setDevelopmenthist(ipdForm.getDevelopmenthist());
	    		bed3.setBirthhist(ipdForm.getBirthhist());
	    		bed3.setHeadcircumference(ipdForm.getHeadcircumference());
	    		bed3.setMidarmcircumference(ipdForm.getMidarmcircumference());
	    		bed3.setLength(ipdForm.getLength());
	    		bed3.setWtaddmission(ipdForm.getWtaddmission());
	    		bed3.setWtdischarge(ipdForm.getWtdischarge());
	    		
	    		//new datra
	    		bed3.setPersonalhist(ipdForm.getPersonalhist());
	    		bed3.setFamily_history(ipdForm.getFamilyhist());
	    		bed3.setPasthistory(ipdForm.getPasthistory());
	    		bed3.setSurgicalnotes(ipdForm.getSurgicalnotes());
	    		bed3.setOnexamination(ipdForm.getOnexamination());
	    		bed3.setSuggestedtrtment(ipdForm.getSuggestedtrtment());
	    		bed3.setEarlierinvest(ipdForm.getEarlierinvest());
	    		
	    		//kunal
	    		bed3.setKunal_final_diagnosis_text(ipdForm.getKunal_final_diagnosis_text());
	    		bed3.setKunal_manual_medicine_text(ipdForm.getKunal_manual_medicine_text());
	    		
	    		int resultt = ipdDAO.updateAdmDataFromDisc(bed3); 
	    		
	    		
	    		if(chiefcomplatetempname!=null){
	    			if(!chiefcomplatetempname.equals("")){
	    				int res5 = ipdDAO.saveIPDTemplate(chiefcomplatetempname,"1",ipdForm.getDepartment(),ipdForm.getChiefcomplains());
	    			}
	    		}
	    		if(presentillnesstempname!=null){
	    			if(!presentillnesstempname.equals("")){
	    				int res5 = ipdDAO.saveIPDTemplate(presentillnesstempname,"2",ipdForm.getDepartment(),ipdForm.getPresentillness());
	    			}
	    		}
	    		
	    		//  01 feb 18 
	    		//If discharge form is fillled then by default, patient to be mark d as discharge Initiated. 
	    		/*String column="dis_initiate_status";
	    		String column2="dis_initiate_time";
	    		String datetime1 = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
	    		int valuetoupdate1 = ipdDAO.getValueToUpdate(column,bed.getTreatmentepisodeid());
	    		//beacuse it return vice versa i.e i fit return 1 that means it status 0
	    		if(valuetoupdate1==1){
	    			int update1 = ipdDAO.updateInitialDischargeStatus(column,column2,valuetoupdate1,datetime1,bed.getTreatmentepisodeid());
	    		}*/
	    		
	    		//  03 April 2018
	    		String totalotids = ipdForm.getTotalotids();
	    		if(totalotids!=null){
	    			for (String id : totalotids.split(",")) {
	    				if (id.equals("0")) {
	    					continue;
	    				}
	    				String editotprocedure = request.getParameter("editotprocedure"+id);
	    				int res4 = ipdDAO.updateDischrgeOTProcedure(editotprocedure,id);
	    			}
	    		}
	    		
	    		//  04 April 2018 prisc sr wise
	    		String totalchildmedids = ipdForm.getTotalchildmedids();
	    		if(totalchildmedids!=null){
	    			for (String id : totalchildmedids.split(",")) {
	    				if (id.equals("0")) {
	    					continue;
	    				}
	    				String dicpriscmedsrno = request.getParameter("dicpriscmed"+id);
	    				String dicpriscdose = request.getParameter("discpriscdose"+id);
	    				String dicpriscdays = request.getParameter("dicpriscdays"+id);
	    				int res5 = ipdDAO.updateDischrgePriscSrNo(dicpriscmedsrno,id,dicpriscdose,dicpriscdays);
	    			}
	    		}
	    		
	    		String followupdate=request.getParameter("followupdate1");
	    		if(followupdate==null){
	    			followupdate="";
	    		}
	    		if(!followupdate.equals("")){
	    			followupdate= DateTimeUtils.getCommencingDate1(followupdate);
	    			DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
	  		      Calendar cal = Calendar.getInstance();
	  		      String date = dateFormat1.format(cal.getTime());  
	    			Client client= new Client();
	    			
		    		client.setIpdid(sessionadmissionid);
		    		client.setClientId(bed.getClientid());
		    		client.setType("1");
		    		client.setFollowupdate(followupdate);	
		    		client.setDate(date);
		    		client.setPractid(bed.getPractitionerid());
		    		client.setLocation("IPD Discharge");
		    		ClientDAO clientDAO= new JDBCClientDAO(connection);
		    		clientDAO.savefollowup(client);
	    		}
	    		
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
	 return "dischargesaved";
	 
}

public void autosmsDischarge(String clietid)throws Exception{
	  

	  //String appointmentid = request.getParameter("appointmentid");
	  
	  Connection connection = null;
	  
	  try{
	     connection = Connection_provider.getconnection();
	     //send sms
	       ClientDAO clientDAO = new JDBCClientDAO(connection);
	           
	       Client client = clientDAO.getClientDetails(clietid);
	      
	      
	       String message = AllTemplateAction.getDischargeSMSTextPatient(clietid,  connection);
	       SmsService s = new SmsService();
	    //   s.sendSms(message, client.getMobNo(), loginInfo, new EmailLetterLog());
	       String templateid="";
	       if(client.getEmergencyContNo()!=null){
	        if(client.getEmergencyContNo()!="")
	        {
	         s.sendSms(message, client.getEmergencyContNo(), loginInfo, new EmailLetterLog(),templateid);
	        }
	        else
	        {
	         s.sendSms(message, client.getMobNo(), loginInfo, new EmailLetterLog(),templateid);
	        }
	       }
	       else
	       {
	        if(client.getEmergencyContNo()!="")
	        {
	         s.sendSms(message, client.getEmergencyContNo(), loginInfo, new EmailLetterLog(),templateid);
	        }
	        else
	        {
	         s.sendSms(message, client.getMobNo(), loginInfo, new EmailLetterLog(),templateid);
	        }
	       }
	       
	   
	  }catch(Exception e){
	   e.printStackTrace();
	  }
	  finally{
			connection.close();
		}
	  
	 }

public String printdischarge() throws SQLException{
	  
	  if(!verifyLogin(request)) {
	    return "login";
	   }
	      Connection connection=null;
	      
	      String clientid = request.getParameter("clientid");
	      
	      if(clientid==null){
	       clientid = (String)session.getAttribute("sessionselectedclientid");
	      }
	     
	  
	  try {
	   
	  //  connection=Connection_provider.getconnection();
		  connection=DriverManager.getConnection(""+Constants.DB_HOST+":3306/"+loginInfo.getClinicUserid()+"?useUnicode=true&characterEncoding=UTF-8",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
	   ClientDAO clientDAO=new JDBCClientDAO(connection);
	   UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
	   MasterDAO masterDAO=new JDBCMasterDAO(connection);
	   if(clientid!=null){
	   IpdDAO ipdDAO = new JDBCIpdDAO(connection);
	   BedDao bedDao = new JDBCBedDao(connection);
	   String selectedid = ipdDAO.getDischargedIpdid(clientid);
	   
	   String reqselectedid = request.getParameter("selectedid");
	   if(reqselectedid!=null){
	    selectedid = reqselectedid;
	   }
	  
	    Bed bed = bedDao.getEditIpdData(selectedid);
	  if(loginInfo.getIpd_abbr_access()==1){
		  String newipdabbr=ipdDAO.getIpdAbrivationIds(Integer.parseInt(selectedid));
		  ipdForm.setNewipdabbr(newipdabbr);
		  if(Integer.parseInt(bed.getIpdseqno())>0){
				ipdForm.setIpdseqno(bed.getIpdseqno());
			}else{
				ipdForm.setIpdseqno(selectedid);
				}
	  }
		  else{
	    if(Integer.parseInt(bed.getIpdseqno())>0){
			ipdForm.setIpdseqno(bed.getIpdseqno());
			ipdForm.setNewipdabbr(bed.getIpdseqno());
		}else{
			ipdForm.setIpdseqno(selectedid);
			ipdForm.setNewipdabbr(selectedid);
		}

	  }
	   Client client=clientDAO.getClientDetails(clientid);
	   ipdForm.setAddress(client.getAddress());
	   ipdForm.setContact(client.getMobNo());
	   ipdForm.setClient(clientid);
	   
	   ipdForm.setAgeonadmn(bed.getAgeonAdmn());
	   UserProfile userProfile=userProfileDAO.getUserprofileDetails(Integer.parseInt(bed.getPractitionerid()));
	   Master discipline=masterDAO.getDisciplineData(userProfile.getDiciplineName());
	   ipdForm.setDiscipline(discipline.getDiscipline());
	   ipdForm.setDoctor_name(userProfile.getInitial()+" "+userProfile.getFirstname()+" "+userProfile.getLastname());
	   
	   ipdForm.setQualification(userProfile.getQualification());
	   
	   String numcount=ipdDAO.getNumofAdmissionCount(clientid);
	   ipdForm.setNum_admission(numcount);
	   
	   ipdForm.setKunal_final_diagnosis_text(bed.getKunal_final_diagnosis_text());
	   ipdForm.setKunal_manual_medicine_text(bed.getKunal_manual_medicine_text());
	   
	   //get prescription list
	   ArrayList<Priscription>dischargePriscList = ipdDAO.getDischargePrescList(selectedid);
	   if(dischargePriscList.size()>0){
		   Priscription pr= dischargePriscList.get(dischargePriscList.size()-1);
		   ipdForm.setStrengthflag(pr.isStrengthflag());
		   ipdForm.setRemarkflag(pr.isRemarkflag());
		   ipdForm.setQuantityflag(pr.isStrengthflag());
	   }
	   String discadvoice=ipdDAO.getDIscPrisc(selectedid);
	   
	   ipdForm.setAdvoice(discadvoice);
	   
	   ipdForm.setDischargePriscList(dischargePriscList);
	   session.setAttribute("dischargePriscList",dischargePriscList);
	   
	   Bed bed1 = ipdDAO.getDischargeData(bed.getTreatmentepisodeid());
	   ipdForm.setChkDischarge(bed1.isChkDischarge());
	   
	   ipdForm.setEmergencydetail(bed1.getEmergencydetail());
	   ipdForm.setHospitalcourse(bed1.getHospitalcourse());
	   ipdForm.setHospitalcourse(bed1.getHospitalcourse());
	   if(ipdForm.getHospitalcourse()!=null){
	    if(ipdForm.getHospitalcourse().equals("") || ipdForm.getHospitalcourse().equals("<br>")){
	     ipdForm.setHospitalcourse(null);
	    } 
	   }
	   
	   ipdForm.setDiscadvnotes(bed1.getDiscadvnotes());
	   if(ipdForm.getDiscadvnotes()!=null){
	    if(ipdForm.getDiscadvnotes().equals("") || ipdForm.getDiscadvnotes().equals("<br>")){
	     ipdForm.setDiscadvnotes(null);
	    } 
	   }
	   
	   ipdForm.setExample(bed1.getExample());
	   
	   DischargeOutcomeDAO dao = new JDBCDischargeOutcomeDAO(connection);
	   
	  String discdate="";
	   if(bed1.getDischargedate()!=null){
		   if(!bed1.getDischargedate().equals("")){
			    
			   String temp[]= bed1.getDischargedate().split(" ");
			   discdate= DateTimeUtils.getCommencingDate1(temp[0])+" "+temp[1];
			   if(loginInfo.isBalgopal()){
				   String time[]=temp[1].split(":"); 
				   int hourOfDay=(Integer.parseInt(time[0]));
				   int minute=(Integer.parseInt(time[1]));
				   String apmpm =  ((hourOfDay > 12) ? hourOfDay % 12 : hourOfDay) + ":" + (minute < 10 ? ("0" + minute) : minute) + " " + ((hourOfDay >= 12) ? "PM" : "AM");
				   discdate= DateTimeUtils.getCommencingDate1(temp[0])+" "+apmpm;
			   }
			   
		   }
	   }
	   
	   ipdForm.setDischargedate(discdate);
	   
	   
	   session.setAttribute("dischargeddata", ipdForm);
	   
	   Bed bedconditions=ipdDAO.getAllFinalCondition(selectedid, bed.getTreatmentepisodeid());
	   
	   ArrayList<Bed> finalConditions=new ArrayList<Bed>();
	   
	   if(bedconditions.getConditionname()!=null){
	    
	    for(String str:bedconditions.getConditionname().split(",")){
	     
	           if(str.equals("0")){
	            
	            continue;
	           }
	           
	           int id=Integer.parseInt(str);
	           String conditionname=bedDao.getIpdConditionName(str);
	           Bed bed2=new Bed();
	           bed2.setId(id);
	           bed2.setConditionname(conditionname);
	           finalConditions.add(bed2);
	     
	    }
	    
	   }
	   
	   
	   ipdForm.setFinalConditions(finalConditions);
	   
	   printformdata(selectedid);
	   
	   NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
	   //String anethesia_name = notAvailableSlotDAO.getDiaryUserName(bed1.getAnesthesia());
	   /*String surgeon_name = notAvailableSlotDAO.getDiaryUserName(bed1.getSurgeon());
	   String anesthesiologist_name = notAvailableSlotDAO.getDiaryUserName(bed1.getAnesthesiologist());*/
	   //String surgeon_name = userProfileDAO.getReferalDrName(bed1.getSurgeon());
	   String surgeon_name = notAvailableSlotDAO.getDiaryUserName(bed1.getSurgeon());
	   String anesthesiologist_name = userProfileDAO.getReferalDrName(bed1.getAnesthesiologist());
	   String anethesia_name = null;
	   if(bed1.getAnesthesia()!=null){
		   if(!bed1.getAnesthesia().equals("")){
			   if(bed1.getAnesthesia().equals("1")){
				   anethesia_name = "Local";
			   }else if(bed1.getAnesthesia().equals("2")){
				   anethesia_name = "General";
			   }else if(bed1.getAnesthesia().equals("3")){
				   anethesia_name = "Spinal";
			   }
		   }
	   }
	   ipdForm.setAnesthesia(anethesia_name);
	   ipdForm.setSurgeon(surgeon_name);
	   ipdForm.setAnesthesiologist(anesthesiologist_name);
	   
	   if(bed1.getEmergencydetail()!=null){
		   if(bed1.getEmergencydetail().equals("") || bed1.getEmergencydetail().equals("<br>")){
			   ipdForm.setEmergencydetail(null);
		   }else{
			   ipdForm.setEmergencydetail( bed1.getEmergencydetail());
		   }
	   }else{
		   ipdForm.setEmergencydetail( bed1.getEmergencydetail());
	   }
	   
	   if(ipdForm.getAppointmentText()!=null){
		   
		   if(ipdForm.getAppointmentText().equals("") || ipdForm.getAppointmentText().equals("<br>") || ipdForm.getAppointmentText().equals("0")){
			     ipdForm.setAppointmentText(null);
			 /*  Ipd ipd = ipdDAO.getProcedureName(selectedid);
				
				String procedureid = ipdDAO.getProcedureId(ipd.getProcedurename());
				ipdForm.setAppointmentText(ipd.getProcedurename());*/
				
			 }
		   
	   }/*else{
		   Ipd ipd = ipdDAO.getProcedureName(selectedid);
			
			String procedureid = ipdDAO.getProcedureId(ipd.getProcedurename());
			ipdForm.setAppointmentText(ipd.getProcedurename());
	   }*/
	   if(ipdForm.getSurgeon()!=null){
		   if(ipdForm.getSurgeon().equals("") || ipdForm.getSurgeon().equals("<br>") || ipdForm.getSurgeon().equals("0")){
			     ipdForm.setSurgeon(null);
			 } 
		   
	   }
	   if(ipdForm.getAnesthesia()!=null){
		   if(ipdForm.getAnesthesia().equals("") || ipdForm.getAnesthesia().equals("<br>") || ipdForm.getAnesthesia().equals("0")){
			     ipdForm.setAnesthesia(null);
			 } 
	   }
	   if(ipdForm.getAnesthesiologist()!=null){
		   if(ipdForm.getAnesthesiologist().equals("") || ipdForm.getAnesthesiologist().equals("<br>") || ipdForm.getAnesthesiologist().equals("0")){
			     ipdForm.setAnesthesiologist(null);
			 } 
	   }
	   if(userProfile.getMobile()=="0" || userProfile.getMobile().equals("0")){
		   ipdForm.setDoctor_phone(null);
	   }else{
		   ipdForm.setDoctor_phone(userProfile.getMobile());
	   }
	   
	   if(bed1.getDischrgeOutcomes()!=null){
	    Master master = dao.getMaster(Integer.parseInt(bed1.getDischrgeOutcomes()));
	    if(master.getName()!=null){
	     ipdForm.setDischrgeOutcomes(master.getName());
	    }else{
	     ipdForm.setDischrgeOutcomes("");
	    } 
	    
	    int selectedid1 = loginInfo.getId();
		
		ClinicDAO clinicListDAO = new JDBCClinicDAO(connection);
		com.apm.Registration.eu.entity.Clinic cliniclist = clinicListDAO.getCliniclistDetails(selectedid1);
		ipdForm.setClinicName(cliniclist.getClinicName());
	    
	    
	    DischargeStatusDAO ddao = new JDBCDischargeStatus(connection);
	    master = ddao.getMaster(Integer.parseInt(bed1.getDischargeStatus()));
	    
	    ipdForm.setDischargeStatusId(bed1.getDischargeStatus());
	    
	    if(master.getName()!=null){
	     ipdForm.setDischargeStatus(master.getName());
	    }else{
	     ipdForm.setDischargeStatus("");
	    }
	    DischargeStatusDAO statusDAO=new JDBCDischargeStatus(connection);
		String dischargehead=statusDAO.getDischargeStatusById(Integer.parseInt(bed1.getDischargeStatus()));
		if(dischargehead==null){
			dischargehead="";
		}
		ipdForm.setDischargehead(dischargehead);
	   }
	   ArrayList<String> otaptid = ipdDAO.getAllOTIds(selectedid,clientid);
	   
	   // Adarsh
		/*ArrayList<Master> otnoteslist =new ArrayList<Master>();
		for (String string : otaptid) {
			ArrayList<Master> otnoteslist1 = ipdDAO.getAllOtNotes(string);
			otnoteslist.addAll(otnoteslist1);
		}
		ipdForm.setOtnoteslist(otnoteslist);
	   */
	   
	   ArrayList<Master> otdatesandids = ipdDAO.getOtDatesAndIds(selectedid,clientid);
		ipdForm.setOtdatesandids(otdatesandids);
		String clinicname=userProfile.getClinicname();
		ipdForm.setClinicName(userProfile.getClinicname());
	   
	   }else{
	    addActionError("Please Select Client!!");
	   }
	   
	   
	  }
	  catch(Exception e) {
	   e.printStackTrace();
	  }
	  
	  finally {
	   connection.close();
	  }
	  return "printdischarge";
	}
public String discharge() throws SQLException{
	 if(!verifyLogin(request)) {
	 		return "login";
	 	}
	     
		
		try {
			session.removeAttribute("finalConditions");
			if(session.getAttribute("openedb")==null){
				session.setAttribute("openedb", "ipd");
			}
			
		
			String selectedid = request.getParameter("selectedid");
			getipddatadischarge(selectedid);
					
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
	 return "discharge";
}

 private void Empty(String string) {
	// TODO Auto-generated method stub
	
}

public String discdata()throws Exception{
	 
	 Connection connection = null;
	 
	 try{
		 connection = Connection_provider.getconnection();
		 IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		 EmrDAO emrDAO = new JDBCEmrDAO(connection);
		 String sessionadmissionid = (String)session.getAttribute("sessionadmissionid");
		 
		 ArrayList<Priscription>dischargePriscList = ipdDAO.getDischargePrescList(sessionadmissionid);
		ipdForm.setDischargePriscList(dischargePriscList);
		
		ArrayList<Master> dosageList = emrDAO.getDosageList();
		
		int size = dischargePriscList.size();
		String totalchildmedids ="0";
		if (size > 0) {
			totalchildmedids = dischargePriscList.get(size - 1).getTotalchildmedids();
		}
		
		int i = 0;
		StringBuffer str = new StringBuffer();
		for(Priscription priscription : dischargePriscList){
			str.append("<tr>");
			str.append("<td><input type='number' class='form-control' name='dicpriscmed"+priscription.getId()+"' value='"+priscription.getDispriscsrno()+"'></td>");
			str.append("<td>"+priscription.getMdicinenametxt()+"</td/>");
			//  05 June 2018
			str.append("<td>");
			str.append("<select id='discpriscdose"+priscription.getId()+"' name='discpriscdose"+priscription.getId()+"' class='form-control chosen-select'>");
			for (Master master : dosageList) {
				if(priscription.getPriscdose()!=null){
					if(master.getName()!=null){
						if(master.getName().equals(priscription.getPriscdose())){
							str.append("<option value='"+master.getName()+"' selected='selected'>"+master.getName()+"</option>");
						}else{
							str.append("<option value='"+master.getName()+"'>"+master.getName()+"</option>");
						}
					}else{
						str.append("<option value='"+master.getName()+"'>"+master.getName()+"</option>");
					}
				}else{
					str.append("<option value='"+master.getName()+"'>"+master.getName()+"</option>");
				}
			}
			str.append("</select>");
			str.append("</td>");
			//str.append("<td>"+priscription.getPriscdose()+"</td/>");
			/*str.append("<td>"+priscription.getPriscdays()+" "+priscription.getPriscdurationtype()+"</td/>");*/
			str.append("<td><input type='number' class='form-control' name='dicpriscdays"+priscription.getId()+"' value='"+priscription.getPriscdays()+"'></td>");
			str.append("<td>"+priscription.getDosenotes()+"</td/>");
			str.append("<td><a onclick='removeMedicineDisc(this,"+priscription.getId()+")' ><i class='fa fa-trash'></i></a></td>");
			str.append("</tr>");
			i++;
		}
		str.append("<input type='hidden' name='totalchildmedids' value='"+totalchildmedids+"'>");
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(""+str.toString()+"");
		 
	 }catch (Exception e) {
		e.printStackTrace();
	}
	 finally{
			connection.close();
		}
	 return null;
 }
 
 public String print() throws Exception {
	 
	 if(!verifyLogin(request)) {
 		return "login";
 	}
     Connection connection=null;
	
	try {
		connection=Connection_provider.getconnection();
		String selectedid = request.getParameter("selectedid");
		
		String saveandprintadmission = (String) session.getAttribute("saveandprintadmission");
		if(!DateTimeUtils.isNull(saveandprintadmission).equals("")){
			selectedid = saveandprintadmission;
			session.removeAttribute("saveandprintadmission");
		}
		
		printformdata(selectedid);
		session.setAttribute("declarationNotes", null);
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	
	finally {
		connection.close();
	}
	
	return "printForm";
}
	
 public void printformdata(String selectedid) throws SQLException{
	 Connection connection=null;
		
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			connection=Connection_provider.getconnection();
			//String selectedid = request.getParameter("selectedid");
			ipdForm.setClientip(selectedid);
			
			BedDao bedDao=new JDBCBedDao(connection);
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			Bed bed = bedDao.getEditIpdData(selectedid);
			MasterDAO masterDAO=new JDBCMasterDAO(connection);
			IpdLogDAO ipdLogDAO=new JDBCIpdLogDAO(connection);
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			
			ipdForm.setCasualtyipd(bed.getAction());
			ipdForm.setDaycare(bedDao.isDayCare(selectedid));
			String dd[]=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ");
			String printedBy = loginInfo.getUserId()+" "+DateTimeUtils.getCommencingDate1(dd[0])+" "+dd[1];  
			ipdForm.setPrintedBy(printedBy);
			if(loginInfo.getIpd_abbr_access()==1){
				  String newipdabbr=ipdDAO.getIpdAbrivationIds(Integer.parseInt(selectedid));
				  ipdForm.setNewipdabbr(newipdabbr);
				  if(Integer.parseInt(bed.getIpdseqno())>0){
						ipdForm.setIpdseqno(bed.getIpdseqno());
					}else{
						ipdForm.setIpdseqno(selectedid);
					}
			  }else{
			if(Integer.parseInt(bed.getIpdseqno())>0){
				ipdForm.setIpdseqno(bed.getIpdseqno());
				ipdForm.setNewipdabbr(bed.getIpdseqno());
			}else{
				ipdForm.setIpdseqno(selectedid);
				ipdForm.setNewipdabbr(selectedid);
			}
			  }
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			UserProfile userProfile = userProfileDAO.getUserprofileDetails(Integer.parseInt(bed.getPractitionerid()));
			String practitionername = userProfile.getInitial() + " " + userProfile.getFirstname() + " " + userProfile.getLastname();
			String specializationId=  userProfile.getDiciplineName();
			ipdForm.setDepartment(userProfile.getSpecialization());
			
			if(userProfile.getDoctor()!=null){
				
				 if(!userProfile.getDoctor().equals("") && !userProfile.getDoctor().equals("0")){
					 
					 UserProfile supportiveDoctorDetails= userProfileDAO.getUserprofileDetails(Integer.parseInt(userProfile.getDoctor()));  
					 String supportiveDoctorName= supportiveDoctorDetails.getInitial() + " " + supportiveDoctorDetails.getFirstname() + " " + supportiveDoctorDetails.getLastname();
					 String supportiveQualification=supportiveDoctorDetails.getQualification();
					 ipdForm.setSupportiveDoctorName(supportiveDoctorName);
					 ipdForm.setSupportiveQualification(supportiveQualification);
				 } 
			}
			
			
			
			boolean issystemicreview= masterDAO.isIpdFormFieldActive(specializationId,"Systemic Review");
			boolean history= masterDAO.isIpdFormFieldActive(specializationId,"History");
			boolean obstretic_history = masterDAO.isIpdFormFieldActive(specializationId,"OBSTRETIC HISTORY");
			boolean menstrual_history = masterDAO.isIpdFormFieldActive(specializationId, "MENSTRUAL HISTORY"); 
			boolean substance_history = masterDAO.isIpdFormFieldActive(specializationId, "SUBSTANCE HISTORY");
			boolean verification = masterDAO.isIpdFormFieldActive(specializationId, "Verification");
			boolean pediatric = masterDAO.isIpdFormFieldActive(specializationId, "Paediatric History");
			
			
			
			ipdForm.setNicuaccess(masterDAO.isIpdFormFieldActive(specializationId, "NICU Setting"));
			ipdForm.setNicuaccess(pediatric);
			
			ipdForm.setVerification(verification);
			ipdForm.setIssystemicreview(issystemicreview);
			ipdForm.setHistory(history);
			ipdForm.setObstretic_history(obstretic_history);
			ipdForm.setMenstrual_history(menstrual_history);
			ipdForm.setSubstance_history(substance_history);
			ipdForm.setPaediatrichist(pediatric);
			
			ipdForm.setEditclientid(bed.getClientid());
			ipdForm.setClientid(bed.getClientid());
			ipdForm.setPractitionerid(practitionername);
			ipdForm.setConditionid(bed.getConditionid());
			ipdForm.setDepartment(bed.getDepartment());
			ipdForm.setSecndryconsult(bed.getSecndryconsult());
			ipdForm.setAddmitedbyuserid(bed.getAddmitedbyuserid());
			
			
			ipdForm.setMaternal_history(bed.getMaternal_history());
			ipdForm.setPerinatal_history(bed.getPerinatal_history());
			
      		NotAvailableSlot notAvailableSlot= notAvailableSlotDAO.getOTDataByIpd(selectedid);
      	    ipdForm.setProcedure(notAvailableSlot.getProcedure());
      	    //ipdForm.setAnesthesia(notAvailableSlot.getAnesthesia());
      	    //ipdForm.setSurgeon(notAvailableSlot.getSurgeon());
      		ipdForm.setOtNotes(notAvailableSlot.getOtnotes());
      		session.setAttribute("ipdotnotes", notAvailableSlot.getOtnotes());
      		//ipdForm.setAppointmentText(notAvailableSlot.getApmttypetext());
      		//ipdForm.setAnsintime(notAvailableSlot.getAnsintime());
			
			if(bed.getRefferedby()!=null){
				
				if(bed.getRefferedby().equals("") || bed.getRefferedby().equals("0") ){
					bed.setRefferedby(null);
				}
			}
			
			ipdForm.setRefferedby(bed.getRefferedby());
			
			String wardname=ipdDAO.getIpdWardName(bed.getWardid());
			String bedname = ipdDAO.getIpdBedName(bed.getBedid());
			
			

			ipdForm.setWardid(wardname);
			ipdForm.setBedid(bedname);
			

			ipdForm.setTpid(bed.getTpid());
			
			
			if(ipdForm.getTpid()==null){
				
				ipdForm.setTpid("0");
			}
			
			
			ipdForm.setStatus(bed.getStatus());
			ipdForm.setAddmissionfor(bed.getAddmissionfor());
			ipdForm.setReimbursment(bed.getReimbursment());
			
			if(bed.getSecndryconsult()!=null){
				if(bed.getSecndryconsult().equals("0")){
					bed.setSecndryconsult(null);
				}
			}
			
			
			if(bed.getSecndryconsult()!=null){
				if(!bed.getSecndryconsult().equals("")){
					
					 ArrayList<String> allConsultantList= ipdDAO.getAllSecondaryConsultList(selectedid);  
					 ArrayList<UserProfile> allconsultantlistwithdepart = ipdDAO.getSecConsWithDepartment(selectedid);
					 ipdForm.setAllconsultantlistwithdepart(allconsultantlistwithdepart);
					 ipdForm.setAllConsultantList(allConsultantList);
					 bed.setAllConsultantList(allConsultantList);
				}
			} else {
				
				practitionername="";
			}
			
			
			userProfile = userProfileDAO.getUserprofileDetails(Integer.parseInt(bed.getPractitionerid()));
			Master discipline=masterDAO.getDisciplineData(userProfile.getDiciplineName());
			ipdForm.setDiscipline(discipline.getDiscipline());
			ipdForm.setDoctor_name(userProfile.getInitial()+" "+userProfile.getFirstname()+" "+userProfile.getLastname());
			ipdForm.setDoctor_phone(userProfile.getMobile());
			ipdForm.setQualification(DateTimeUtils.isNull(userProfile.getQualification()));
			ipdForm.setUseregno(userProfile.getRegisterno());
			ArrayList<Master> qualificationList= new ArrayList<Master>();
			
			for(String str:ipdForm.getQualification().split(",")){
				Master master= new Master();
				master.setName(str);
				qualificationList.add(master);
			}
			if(qualificationList.size()==0){
				Master master= new Master();
				master.setName(ipdForm.getQualification());
				qualificationList.add(master);
			}
			ipdForm.setQualificationList(qualificationList);
			//  add mlc ref dr name
			String mlcdrname = userProfileDAO.getReferalDrName(bed.getMlcrefdoctor());
			ipdForm.setMlcrefdoctor(mlcdrname);
			
			ipdForm.setSecndryconsult(practitionername);
			ipdForm.setMlcno(bed.getMlcno());
			ipdForm.setAdmissiondetails(bed.getAdmissiondetails());
			String val = bed.getSuggestedtrtment();
			ipdForm.setSuggestedtrtment(bed.getSuggestedtrtment());
			ipdForm.setHosp(bed.getHosp());
			ipdForm.setPackagename(bed.getPackagename());
			//chiefcomplains, presentillness, pasthistory, personalhist, familyhist, onexamination, treatmentepisodeid lokeshnew
			ipdForm.setChiefcomplains(bed.getChiefcomplains());
			ipdForm.setPresentillness(bed.getPresentillness());
			ipdForm.setPasthistory(bed.getPasthistory());
			ipdForm.setPersonalhist(bed.getPersonalhist());
			ipdForm.setFamilyhist(bed.getFamilyhist());
			ipdForm.setOnexamination(bed.getOnexamination());
			
			String treatmentname = bedDao.getTreatmentName(bed.getTreatmentepisodeid());
			
			ipdForm.setTreatmentepisodeid(treatmentname);

			ipdForm.setSuggestoper(bed.getSuggestoper());
			ipdForm.setSystdepartment(bed.getSystdepartment());
			ipdForm.setFpcondition(bed.getFpcondition());
			ipdForm.setFpnotest(bed.getFpnotest());
			ipdForm.setNauseacondition(bed.getNauseacondition());
			ipdForm.setNauseanotes(bed.getNauseanotes());
			ipdForm.setFnucondition(bed.getFnucondition());
			ipdForm.setFnunotes(bed.getFnunotes());
			ipdForm.setSmcondition(bed.getSmcondition());
			ipdForm.setSmnotes(bed.getSmnotes());
			ipdForm.setChestpaincond(bed.getChestpaincond());
			ipdForm.setChestpainnotes(bed.getChestpainnotes());
			ipdForm.setDimvisioncond(bed.getDimvisioncond());
			ipdForm.setDimvisionnotes(bed.getDimvisionnotes());
			
			//dimvisionnotes, hgucondition, hgunotes, hmcondition, hmnotes, jointpaincond, jointpainnotes, 
			ipdForm.setHgucondition(bed.getHgucondition());
			ipdForm.setHgunotes(bed.getHgunotes());
			ipdForm.setHmcondition(bed.getHmcondition());
			ipdForm.setHmnotes(bed.getHmnotes());
			ipdForm.setJointpaincond(bed.getJointpaincond());
			ipdForm.setJointpainnotes(bed.getJointpainnotes());
			
			//constipationcond, constpationnotes, specialnotes, edemafeetcondi, edemafeetnotes, hematuriacondi, 
			ipdForm.setConstipationcond(bed.getConstipationcond());
			ipdForm.setConstpationnotes(bed.getConstpationnotes());
			ipdForm.setSpecialnotes(bed.getSpecialnotes());
			ipdForm.setEdemafeetcondi(bed.getEdemafeetcondi());
			ipdForm.setEdemafeetnotes(bed.getEdemafeetnotes());
			ipdForm.setHematuriacondi(bed.getHematuriacondi());
			ipdForm.setHematurianotes(bed.getHematurianotes());
			
			//hematurianotes, bmcondition, bmnotes, oliguriacondi, oligurianotes, pstgucondi, pstgunotes, 
			ipdForm.setBmcondition(bed.getBmcondition());
			ipdForm.setBmnotes(bed.getBmnotes());
			ipdForm.setOliguriacondi(bed.getOliguriacondi());
			ipdForm.setOligurianotes(bed.getOligurianotes());
			ipdForm.setPstgucondi(bed.getPstgucondi());
			ipdForm.setPstgunotes(bed.getPstgunotes());
			
			//bmecondition, bmenotes, tnecondition, tnenotes, weaknesscondi, weaknessnotes, ihcondition, ihnotes
			ipdForm.setBmecondition(bed.getBmecondition());
			ipdForm.setBmenotes(bed.getBmenotes());
			ipdForm.setTnecondition(bed.getTnecondition());
			ipdForm.setTnenotes(bed.getTnenotes());
			ipdForm.setWeaknesscondi(bed.getWeaknesscondi());
			ipdForm.setWeaknessnotes(bed.getWeaknessnotes());
			ipdForm.setIhcondition(bed.getIhcondition());
			ipdForm.setIhnotes(bed.getIhnotes());
			
			ipdForm.setAdmission_reason(bed.getAdmission_reason());
    		ipdForm.setEarlierinvest(bed.getEarlierinvest());
    		ipdForm.setAlergies(bed.getAlergies());
    		
    		
    		ipdForm.setBirthhist(bed.getBirthhist());
    		ipdForm.setDiethist(bed.getDiethist());
    		ipdForm.setDevelopmenthist(bed.getDevelopmenthist());
    		ipdForm.setEmmunizationhist(bed.getEmmunizationhist());
    		//lokesh
    		ipdForm.setHeadcircumference(bed.getHeadcircumference());
    		ipdForm.setMidarmcircumference(bed.getMidarmcircumference());
    		ipdForm.setLength(bed.getLength());
    		ipdForm.setWtaddmission(bed.getWtaddmission());
    		ipdForm.setWtdischarge(bed.getWtdischarge());
			
    		//giddinesscondition,giddinessnotes,unconcondition,unconnotes
    		ipdForm.setGiddinesscondition(bed.getGiddinesscondition());
    		ipdForm.setGiddinessnotes(bed.getGiddinessnotes());
    		ipdForm.setUnconcondition(bed.getUnconcondition());
    		ipdForm.setUnconnotes(bed.getUnconnotes());
    		 
    		
    		ipdForm.setWtonbirth(bed.getWtonbirth());
    		ipdForm.setGstureage(bed.getGstureage());
    		
  /* if(!bed.getEmmunizationhist().equals("")||!bed.getBirthhist().equals("")||!bed.getDiethist().equals("")||!bed.getDevelopmenthist().equals("")){
	   bed.setPeditric(true);
   }*/
    		
    		
			ipdForm.setId(Integer.parseInt(selectedid));

			Bed bed1 = ipdDAO.getDischargeData(bed.getTreatmentepisodeid());
			ipdForm.setChkDischarge(bed1.isChkDischarge());
			ipdForm.setDischrgeOutcomes(bed1.getDischrgeOutcomes());
			ipdForm.setDischargeStatus(bed1.getDischargeStatus());
			ipdForm.setHospitalcourse(bed1.getHospitalcourse());
			//lokesh
			/*if(bed1.getHospitalcourse()!=null){
			if(bed1.getHospitalcourse().contains("<div>")){
				String hscourse= bed1.getHospitalcourse();
				hscourse=hscourse.replaceAll("<div>", "");
				hscourse=hscourse.replaceAll("</div>", "");
				bed1.setHospitalcourse(hscourse);
			}
			}*/
			ipdForm.setDiscadvnotes(bed1.getDiscadvnotes());
			ipdForm.setTreatmentgiven(bed1.getTreatmentgiven());
			ipdForm.setFindondischarge(bed1.getFindondischarge());
			ipdForm.setInvestigation(bed1.getInvestigation());
			ipdForm.setOtNotes(bed1.getOtNotes());
			ipdForm.setAppointmentText(bed1.getAppointmentText());
			ipdForm.setAnesthesia(bed1.getAnesthesia());
			ipdForm.setSurgeon(bed1.getSurgeon());
			ipdForm.setAnesthesiologist(bed1.getAnesthesiologist());
			ipdForm.setDischargebyid(bed1.getDischargebyid());
			ipdForm.setSurgicalnotes(bed.getSurgicalnotes());
			//peditric 
			
			ipdForm.setEmergencydetail(bed1.getEmergencydetail());
			
			ipdForm.setDeathnote(bed1.getDeathnote());
			ipdForm.setTreatmenthistory(bed.getTreatmenthistory());
			
			if(ipdForm.getDeathnote()!=null){
				   
				   if(ipdForm.getDeathnote().equals("") || ipdForm.getDeathnote().equals("<br>")){
					     ipdForm.setDeathnote(null);
					 } 
				   
			   }
			
			if(ipdForm.getSurgicalnotes()!=null){
				   
				   if(ipdForm.getSurgicalnotes().equals("") || ipdForm.getSurgicalnotes().equals("<br>")){
					     ipdForm.setSurgicalnotes(null);
					 } 
				   
			   }
			
			if(ipdForm.getTreatmentgiven()!=null){
				   
				   if(ipdForm.getTreatmentgiven().equals("") || ipdForm.getTreatmentgiven().equals("<br>")){
					     ipdForm.setTreatmentgiven(null);
					 } 
				   
			   }
			if(ipdForm.getFindondischarge()!=null){
				   
				   if(ipdForm.getFindondischarge().equals("") || ipdForm.getFindondischarge().equals("<br>")){
					     ipdForm.setFindondischarge(null);
					 } 
				   
			   }
			if(ipdForm.getInvestigation()!=null){
				   
				   if(ipdForm.getInvestigation().equals("") || ipdForm.getInvestigation().equals("<br>")){
					     ipdForm.setInvestigation(null);
					 } 
				   
			   }
			if(ipdForm.getOtNotes()!=null){
				   
				   if(ipdForm.getOtNotes().equals("") || ipdForm.getOtNotes().equals("<br>")){
					     ipdForm.setOtNotes(null);
					 } 
				   
			}
			if(ipdForm.getAppointmentText()!=null){
				   
				   if(ipdForm.getAppointmentText().equals("") || ipdForm.getAppointmentText().equals("<br>")){
					     ipdForm.setAppointmentText(null);
					 } else{
						 String data1="";
						String[] data = ipdForm.getAppointmentText().split(",");
						for (int i = 0; i < data.length; i++) {
							if(i!=0){
								if(data1.equals("")){
									data1 = data[i];
								}else{
									data1 = data1+data[i];
								}
								
							}
						}
						ipdForm.setAppointmentText(data1);
					 }
				   
			}
			if(ipdForm.getSurgeon()!=null){
				   
				   if(ipdForm.getSurgeon().equals("") || ipdForm.getSurgeon().equals("<br>")){
					     ipdForm.setSurgeon(null);
					 } 
				   
			}
			if(ipdForm.getAnesthesia()!=null){
				   
				   if(ipdForm.getAnesthesia().equals("") || ipdForm.getAnesthesia().equals("<br>")){
					     ipdForm.setAnesthesia(null);
					 } 
				   
			}
			if(ipdForm.getAnesthesiologist()!=null){
				   
				   if(ipdForm.getAnesthesiologist().equals("") || ipdForm.getAnesthesiologist().equals("<br>")){
					     ipdForm.setAnesthesiologist(null);
					 } 
				   
			}
			
			ipdForm.setChkDischarge(bed1.isChkDischarge());
			ipdForm.setHospitalcourse(bed1.getHospitalcourse());
			ipdForm.setHospitalcourse(bed1.getHospitalcourse());
			if(ipdForm.getHospitalcourse()!=null){
			  if(ipdForm.getHospitalcourse().equals("") || ipdForm.getHospitalcourse().equals("<br>")){
			     ipdForm.setHospitalcourse(null);
			  } 
			}
			   
			   ipdForm.setDiscadvnotes(bed1.getDiscadvnotes());
			   if(ipdForm.getDiscadvnotes()!=null){
			    if(ipdForm.getDiscadvnotes().equals("") || ipdForm.getDiscadvnotes().equals("<br>")){
			     ipdForm.setDiscadvnotes(null);
			    } 
			   }
			   ipdForm.setExample(bed1.getExample());
			   if(ipdForm.getExample()!=null){
				   
				   if(ipdForm.getExample().equals("") || ipdForm.getExample().equals("<br>")){
					     ipdForm.setExample(null);
					 } 
				   
			   }
			   
			  
			 //gynic details
			   
			    boolean issubstance=false;
			   	
	    		ipdForm.setAlcohal(bed.getAlcohal());
	    		ipdForm.setDrugs(bed.getDrugs());
	    		ipdForm.setOther_medication(bed.getOther_medication());
	    		ipdForm.setTobaco(bed.getTobaco());
	    		ipdForm.setTobaconotes(bed.getTobaconotes());
	    		ipdForm.setSmoking(bed.getSmoking());
	    		if(ipdForm.getAlcohal()!=null){
	    			
	    			if(ipdForm.getAlcohal().equals("") || ipdForm.getAlcohal().equals("No") ){
	    				 ipdForm.setAlcohal(null);
	    			} else {
	    				issubstance= true;
	    			}
	    		}
	    		if(ipdForm.getDrugs()!=null){
	    			
	    			if(ipdForm.getDrugs().equals("") || ipdForm.getDrugs().equals("No") ){
	    				 ipdForm.setDrugs(null);
	    			}else {
	    				issubstance= true;
	    			}
	    		}
	    		if(ipdForm.getOther_medication()!=null){
	    			
	    			if(ipdForm.getOther_medication().equals("") || ipdForm.getOther_medication().equals("No") ){
	    				 ipdForm.setOther_medication(null);
	    			}else {
	    				issubstance= true;
	    			}
	    		}
	    		if(ipdForm.getTobaco()!=null){
	    			
	    			if(ipdForm.getTobaco().equals("") || ipdForm.getTobaco().equals("No") ){
	    				 ipdForm.setTobaco(null);
	    			}else {
	    				issubstance= true;
	    			}
	    		}
	    		if(ipdForm.getTobaconotes()!=null){
	    			
	    			if(ipdForm.getTobaconotes().equals("") || ipdForm.getTobaconotes().equals("No") ){
	    				 ipdForm.setTobaconotes(null);
	    			}else {
	    				issubstance= true;
	    			}
	    		}
	    		if(ipdForm.getSmoking()!=null){
	    			
	    			if(ipdForm.getSmoking().equals("") || ipdForm.getSmoking().equals("No") ){
	    				 ipdForm.setSmoking(null);
	    			}else {
	    				issubstance= true;
	    			}
	    		}
	    		
	    		if(issubstance){
	    			ipdForm.setSubstancehistory("");
	    		} else {
	    			ipdForm.setSubstancehistory(null);
	    		}
	    		
	    		boolean ismenstrual=false;
	    		
	    		ipdForm.setAge_menarche(bed.getAge_menarche());
	    		ipdForm.setLmp(bed.getLmp());
	    		ipdForm.setLlmp(bed.getLlmp());
	    		ipdForm.setPmc(bed.getPmc());
	    		
	    		if(ipdForm.getAge_menarche()!=null){
	    			if(ipdForm.getAge_menarche().equals("0") || ipdForm.getAge_menarche().equals("")){
	    				ipdForm.setAge_menarche(null);
	    			} else {
	    				ismenstrual=true;
	    			}
	    		} 
	    		if(ipdForm.getLmp()!=null){
	    			if(ipdForm.getLmp().equals("0") || ipdForm.getLmp().equals("")){
	    				ipdForm.setLmp(null);
	    			} else {
	    				ismenstrual=true;
	    			}
	    		} 
	    		if(ipdForm.getLlmp()!=null){
	    			if(ipdForm.getLlmp().equals("0") || ipdForm.getLlmp().equals("")){
	    				ipdForm.setLlmp(null);
	    			} else {
	    				ismenstrual=true;
	    			}
	    		} 
	    		if(ipdForm.getPmc()!=null){
	    			if(ipdForm.getPmc().equals("0") || ipdForm.getPmc().equals("")){
	    				ipdForm.setPmc(null);
	    			} else {
	    				ismenstrual=true;
	    			}
	    		}
	    		
	    		
	    		ipdForm.setCycle_length(bed.getCycle_length());
	    		ipdForm.setDuration_flow(bed.getDuration_flow());
	    		ipdForm.setAmount_flow(bed.getAmount_flow());
	    		ipdForm.setDysmenorrhea(bed.getDysmenorrhea());
	    		ipdForm.setDyspareunia(bed.getDyspareunia());
	    		ipdForm.setHopassing_clots(bed.getHopassing_clots());
	    		if(ipdForm.getCycle_length()!=null){
	    			if(ipdForm.getCycle_length().equals("0") || ipdForm.getCycle_length().equals("")){
	    				ipdForm.setCycle_length(null);
	    			} else {
	    				ismenstrual=true;
	    			}
	    		}
	    		if(ipdForm.getDuration_flow()!=null){
	    			if(ipdForm.getDuration_flow().equals("0") || ipdForm.getDuration_flow().equals("")){
	    				ipdForm.setDuration_flow(null);
	    			} else {
	    				ismenstrual=true;
	    			}
	    		}
	    		if(ipdForm.getAmount_flow()!=null){
	    			if(ipdForm.getAmount_flow().equals("0") || ipdForm.getAmount_flow().equals("")){
	    				ipdForm.setAmount_flow(null);
	    			} else {
	    				ismenstrual=true;
	    			}
	    		}
	    		if(ipdForm.getDysmenorrhea()!=null){
	    			if(ipdForm.getDysmenorrhea().equals("0") || ipdForm.getDysmenorrhea().equals("")){
	    				ipdForm.setDysmenorrhea(null);
	    			} else {
	    				ismenstrual=true;
	    			}
	    		}
	    		if(ipdForm.getDyspareunia()!=null){
	    			if(ipdForm.getDyspareunia().equals("0") || ipdForm.getDyspareunia().equals("")){
	    				ipdForm.setDyspareunia(null);
	    			} else {
	    				ismenstrual=true;
	    			}
	    		}
	    		if(ipdForm.getHopassing_clots()!=null){
	    			if(ipdForm.getHopassing_clots().equals("0") || ipdForm.getHopassing_clots().equals("")){
	    				ipdForm.setHopassing_clots(null);
	    			} else {
	    				ismenstrual=true;
	    			}
	    		}
	    		
	    		
	    		ipdForm.setWhite_disc_itching(bed.getWhite_disc_itching());
	    		ipdForm.setIntercourse_freq(bed.getIntercourse_freq());
	    		ipdForm.setGalactorrea(bed.getGalactorrea());
	    		if(ipdForm.getWhite_disc_itching()!=null){
	    			if(ipdForm.getWhite_disc_itching().equals("0") || ipdForm.getWhite_disc_itching().equals("")){
	    				ipdForm.setWhite_disc_itching(null);
	    			} else {
	    				ismenstrual=true;
	    			}
	    		}
	    		if(ipdForm.getIntercourse_freq()!=null){
	    			if(ipdForm.getIntercourse_freq().equals("0") || ipdForm.getIntercourse_freq().equals("")){
	    				ipdForm.setIntercourse_freq(null);
	    			} else {
	    				ismenstrual=true;
	    			}
	    		}
	    		if(ipdForm.getGalactorrea()!=null){
	    			if(ipdForm.getGalactorrea().equals("0") || ipdForm.getGalactorrea().equals("")){
	    				ipdForm.setGalactorrea(null);
	    			} else {
	    				ismenstrual=true;
	    			}
	    		}
	    		
	    		
	    		
	    		ipdForm.setHo_contraception(bed.getHo_contraception());
	    		ipdForm.setRubella_vaccine(bed.getRubella_vaccine());
	    		ipdForm.setMenopause(bed.getMenopause());
	    		if(ipdForm.getHo_contraception()!=null){
	    			if(ipdForm.getHo_contraception().equals("0") || ipdForm.getHo_contraception().equals("")){
	    				ipdForm.setHo_contraception(null);
	    			} else {
	    				ismenstrual=true;
	    			}
	    		}
	    		if(ipdForm.getRubella_vaccine()!=null){
	    			if(ipdForm.getRubella_vaccine().equals("0") || ipdForm.getRubella_vaccine().equals("")){
	    				ipdForm.setRubella_vaccine(null);
	    			} else {
	    				ismenstrual=true;
	    			}
	    		}
	    		if(ipdForm.getMenopause()!=null){
	    			if(ipdForm.getMenopause().equals("0") || ipdForm.getMenopause().equals("")){
	    				ipdForm.setMenopause(null);
	    			} else {
	    				ismenstrual=true;
	    			}
	    		}
	    		
	    		if(ismenstrual){
	    			ipdForm.setMenstraulhistory("");
	    		} else {
	    			ipdForm.setMenstraulhistory(null);
	    		}
	    		
	    		
	    		ipdForm.setLive_boys(bed.getLive_boys());
	    		ipdForm.setLive_girls(bed.getLive_girls());
	    		ipdForm.setStillbirths(bed.getStillbirths());
	    		ipdForm.setAbortions(bed.getAbortions());
	    		ipdForm.setDeath_children(bed.getDeath_children());
	  		   
	    		ArrayList<Bed> gynicobsList= bedDao.getGynicObsList(selectedid);
	    		ipdForm.setGynicobsList(gynicobsList);
			   
	    		
	    		//parity_aboration_notes,p,l,a,d
	    		ipdForm.setParity_abortion_notes(bed.getParity_abortion_notes());
	    		if(ipdForm.getParity_abortion_notes()!=null){
	    			
	    			if(ipdForm.getParity_abortion_notes().equals("") || ipdForm.getParity_abortion_notes().equals("0") ){
	    				ipdForm.setParity_abortion_notes("");
	    			}
	    		}
	    		ipdForm.setP(bed.getP());
	    		ipdForm.setL(bed.getL());
	    		ipdForm.setA(bed.getA());
	    		ipdForm.setD(bed.getD());
	    		
	    		
			boolean ishistory=false;
			
			
			if(bed.getAddmissionfor()!=null  ){
				
				if(bed.getAddmissionfor().equals("") || bed.getAddmissionfor().equals("<br>")){
					bed.setAddmissionfor(null);
				}
			}
			if(bed.getAlergies()!=null){
				
				if(bed.getAlergies().equals("") || bed.getAlergies().equals("<br>")){
					bed.setAlergies(null);
				}
			}
			if(bed.getPackagename()!=null){
				
				if(bed.getPackagename().equals("") || bed.getPackagename().equals("<br>")){
					bed.setPackagename(null);
				}
				
			}
			if(bed.getAdmission_reason()!=null){
				
				if(bed.getAdmission_reason().equals("") || bed.getAdmission_reason().equals("<br>")){
					bed.setAdmission_reason(null);
				}
			}
			if(bed.getChiefcomplains()!=null){
				
				if(bed.getChiefcomplains().equals("") || bed.getChiefcomplains().equals("<br>")){
					bed.setChiefcomplains(null);
				}
			}
			if(bed.getPasthistory()!=null){
				
				if(bed.getPasthistory().equals("") || bed.getPasthistory().equals("<br>")){
					bed.setPasthistory(null);
				} else {
					ishistory=true;
				}
			}
			if(bed.getFamilyhist()!=null){
				
				if(bed.getFamilyhist().equals("") || bed.getFamilyhist().equals("<br>")){
					bed.setFamilyhist(null);
				} else {
					ishistory=true;
				}
			}
			if(bed.getPersonalhist()!=null){
				
				if(bed.getPersonalhist().equals("") || bed.getPersonalhist().equals("<br>")){
					bed.setPersonalhist(null);
				} else {
					ishistory=true;
				}
			}
			if(bed.getOnexamination()!=null){
				
				if(bed.getOnexamination().equals("") || bed.getOnexamination().equals("<br>")){
					bed.setOnexamination(null);
				} else {
					ishistory=true;
				}
				
			}
			if(bed.getSurgicalnotes()!=null){
				   
				   if(bed.getSurgicalnotes().equals("") || bed.getSurgicalnotes().equals("<br>")){
					   bed.setSurgicalnotes(null);
					 } else {
						 ishistory=true;
					 }
				   
			   }
			
			//  05 June 2018 to set content of History textbox data
			if(ipdForm.getExample()!=null){
				bed.setExample(ipdForm.getExample());
				ishistory=true;
			}
			
			
			if(bed.getSuggestedtrtment()!=null){
				
				if(bed.getSuggestedtrtment().equals("") || bed.getSuggestedtrtment().equals("<br>")){
					bed.setSuggestedtrtment(null);
				}else {
				}
			}
			if(bed.getSpecialnotes()!=null){
				
				if(bed.getSpecialnotes().equals("") || bed.getSpecialnotes().equals("<br>")){
					bed.setSpecialnotes(null);
				}
				
			}
			if(bed.getEarlierinvest()!=null){
				
				if(bed.getEarlierinvest().equals("") || bed.getEarlierinvest().equals("<br>")){
					bed.setEarlierinvest(null);
				}
			}	
			if(bed.getPresentillness()!=null){
				
				if(bed.getPresentillness().equals("") || bed.getPresentillness().equals("<br>")){
					bed.setPresentillness(null);
				}
			}	
			
			
			if(bed.getPresentillness()==null && bed.getChiefcomplains()==null && bed.getAdmission_reason()==null){
				
				 bed.setSummary(null); 
			} else {
				bed.setSummary("");
			}
			
			if(!ishistory){
				bed.setHistory(null);
			} else {
				bed.setHistory("");
			}
			
			
			
			//set treatment episode
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			Client client = clientDAO.getClientDetails(ipdForm.getClientid());
			String fullname = client.getTitle() + " " + client.getFirstName() + " "+client.getMiddlename()+" " + client.getLastName();
			ipdForm.setClient(fullname);
			ipdForm.setRegno(client.getAbrivationid());
			String whopay=client.getWhopay();
			ipdForm.setAbrivationid(client.getAbrivationid());
			ipdForm.setPatientIdAbrivation(client.getPatientIdAbrivation());
			if(whopay==null){
				whopay="";
			}
			if(!whopay.equals("Client")){
				ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
				ThirdParty thirdParty = thirdPartyDAO.getThirdPartyDetails(bed.getTpid());
				ipdForm.setThirdParty(thirdParty.getCompanyName());
			}else{
				ipdForm.setThirdParty("Self");
			}
			String birthtime=client.getBirthtime().replaceAll(" ", "");
			 String hh1="";
			 String mm1="";
			 String apmpm1="";
			 if(!birthtime.equals("00:00:00")){
				 String time[] = birthtime.split(":");
				 hh1 = time[0];
				 hh1=hh1.replaceAll(" ", "");
				 mm1 = time[1];
				 mm1=mm1.replaceAll(" ", "");
				 	int hourOfDay1=Integer.parseInt(hh1);
				   int minute1=Integer.parseInt(mm1);
				    apmpm1 =  ((hourOfDay1 > 12) ? hourOfDay1 % 12 : hourOfDay1) + ":" + (minute1 < 10 ? ("0" + minute1) : minute1) + " " + ((hourOfDay1 >= 12) ? "PM" : "AM");	 
			 }
			//lokesh
			String agegender="";
			String dob = client.getDob();
			String age = DateTimeUtils.getAge1(client.getDob());
		/*	String age1[]= age.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
			age= age1[0];
			if(Integer.parseInt(age)<2){
				if(Integer.parseInt(age)<1){
					String monthdays= DateTimeUtils.getMonthandDays(client.getDob());
					agegender=monthdays+" / "+client.getGender();
				}else{
					String monthdays= DateTimeUtils.getMonthandDays(client.getDob());
					agegender= age + " Years" + " "+monthdays+" / "+client.getGender();
				}
			} else {
				agegender = age + "Years" + " / " + client.getGender();	
			}*/
			if(!birthtime.equals("00:00:00")){
				agegender = age +"("+apmpm1+")"+ " / " + client.getGender();
			}else{
			agegender = age + " / " + client.getGender();	
			}
			ipdForm.setAge(age);
			ipdForm.setAgegender(agegender);
			ipdForm.setRelativename(client.getEmergencyContName());
    		ipdForm.setRelationcont(client.getEmergencyContNo());
    		ipdForm.setRelation(client.getRelation());
    		
    		boolean isfamilyd=false;
    		
    		if(ipdForm.getRelativename()!=null){
    			 
    			if(ipdForm.getRelativename().equals("")){
    				 
    				ipdForm.setRelativename(null);
    			}
    		}

    		if(ipdForm.getRelationcont()!=null){
    			 
    			if(ipdForm.getRelationcont().equals("")){
    				 
    				ipdForm.setRelationcont(null);
    			}
    		}

    		if(ipdForm.getRelation()!=null){
    			 
    			if(ipdForm.getRelation().equals("")){
    				 
    				ipdForm.setRelation(null);
    			}
    		}
    		
    		if(ipdForm.getRelativename()==null&&ipdForm.getRelationcont()==null&&ipdForm.getRelation()==null){
    			isfamilyd=true;
    		}
    		/*if(ipdForm.getRelationcont()==null){
    			isfamilyd=true;
    		}
    		if(ipdForm.getRelation()==null){
    			isfamilyd=true;
    		}
    		*/
    		if(isfamilyd){
    			ipdForm.setFamilyDetails("");
    		} else {
    			ipdForm.setFamilyDetails("ee");
    		}
    		
    		String numcount=ipdDAO.getNumofAdmissionCount(ipdForm.getClientid());
    		ipdForm.setNum_admission(numcount);
    		ipdForm.setDob(client.getDob());
    		ipdForm.setAddress(client.getAddress()+", "+client.getTown()+"-"+client.getPostCode()+", "+DateTimeUtils.isNull(client.getCounty())  );
			ipdForm.setContact(client.getMobNo());
			
    		ArrayList<Bed> bedLogList=ipdLogDAO.getBedChangeLogList(bed.getClientid(),selectedid);
			ipdForm.setBedLogList(bedLogList);
			
			if(bedLogList.size()>0){
				bedLogList.get(0).setStatus("1");
			}
    		
    		String payby = client.getWhopay();
			if(client.getWhopay().equals("Self")){
				payby = "Client";
			}
			TreatmentEpisodeDAO treatmentEpisodeDAO = new JDBCTreatmentEpisode(connection);
			ClinicDAO clinicDAO=new JDBCClinicDAO(connection);
			ArrayList<TreatmentEpisode> treatmentEpisodeList = treatmentEpisodeDAO.getIpdTreatmentEpisodeList(ipdForm.getClientid(), payby);
			ipdForm.setTreatmentEpisodeList(treatmentEpisodeList);
			
			ipdForm.setTreatmentEpisode(bed.getTreatmentepisodeid());
			String admissiondate = bed.getAdmissiondate();
			String[] data = admissiondate.split(" ");
			String data2 = DateTimeUtils.getCommencingDate1(data[0]);
			String data3 = data2 +" "+ data[1];
			//ipdForm.setAdmissiondate(bed.getAdmissiondate());
			if(loginInfo.isBalgopal()){
				String time[]=data[1].split(":"); 
				   int hourOfDay=(Integer.parseInt(time[0]));
				   int minute=(Integer.parseInt(time[1]));
				   String apmpm =  ((hourOfDay > 12) ? hourOfDay % 12 : hourOfDay) + ":" + (minute < 10 ? ("0" + minute) : minute) + " " + ((hourOfDay >= 12) ? "PM" : "AM");
				   data3= DateTimeUtils.getCommencingDate1(data[0])+" "+apmpm;
			}
			ipdForm.setAdmissiondate(data3);
		
		/*	Collection<Bed> conditions=bedDao.getConditionList(bed.getConditionid());
			ipdForm.setConditions(conditions);*/
			
			Clinic clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			ipdForm.setClinicLogo(clinic.getUserImageFileName());
			
			
			String treatmentName=bedDao.getTreatmentName(bed.getTreatmentepisodeid());
			  ipdForm.setTreatmentEpisode(treatmentName);
			  ArrayList<Bed>ipdConditionids = bedDao.getIpdConditionList(selectedid);
			  
			  ArrayList<String> ipdconditionnames=bedDao.getConditionNameList(ipdConditionids);
			  
			  ArrayList<Client> ipdCondtitionList = clientDAO.getTreatmentTypeList();
			  session.setAttribute("ipdConditionids", ipdConditionids);
			  session.setAttribute("ipdCondtitionList", ipdconditionnames);
			  session.setAttribute("bed", bed);
			
			  
			  ArrayList<Priscription>admissionPriscList = ipdDAO.getAdmissionPrescList(selectedid);
			   if(admissionPriscList.size()>0){
				   Priscription pr= admissionPriscList.get(admissionPriscList.size()-1);
				   ipdForm.setStrengthflag(pr.isStrengthflag());
				   ipdForm.setRemarkflag(pr.isRemarkflag());
				   ipdForm.setQuantityflag(pr.isStrengthflag());
			   }
			  
			   ipdForm.setAdmissionPriscList(admissionPriscList);
			   session.setAttribute("dischargePriscList",admissionPriscList);
			   
			  ipdForm.setMothername(client.getMothername());
			  ipdForm.setFathername(client.getFathername());
			  ipdForm.setBirthplace(client.getBirthplace());
			  ipdForm.setMlccase(bed.getMlccase());
			 /* if(bed.getMlccase().equals("1")){
				  
			  }*/
			 if(ipdForm.getDischargedate()!=null){
				 if(!ipdForm.getDischargedate().equals("")){
			  String c=ipdForm.getDischargedate();
			  
			  String h[]= c.split(" ");
			  c= DateTimeUtils.getCommencingDate1(h[0]);
		String d=DateTimeUtils.getAge1onAddmission(ipdForm.getDob(), c);
	
				 }
			 }
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
	
}	
	
	public String client() throws Exception {
		
		
		Connection connection = null;
		Client client = new Client();
		ArrayList<Client> allPatientList = new ArrayList<Client>();
		try{
			connection = Connection_provider.getconnection();
			
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			allPatientList = clientDAO.getAllPatient(loginInfo.getId(),"");
					
			StringBuffer str = new StringBuffer();
			
	    	str.append("<table width = '50%' id = 'allPatient' cellpadding='0' cellspacing='0' class='my-table' > ");
			str.append("<tr>");
			str.append("<th>Id</th> ");

			str.append("<th>Name</th> ");
			str.append("<th>Old Client Id</th> ");
			str.append("<th>Mobile</th> ");
			str.append("<th>Email</th> ");
			str.append("<th>PostCode</th> ");
			str.append("<th>Dob</th> ");
			str.append("<th>LastModified</th> ");

			str.append("</tr>");
			
			for(Client client1:allPatientList){
			String fullname = client1.getTitle() + "/" + client1.getFirstName() + "/" + client1.getLastName();
			 
			 ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
		     ThirdParty thirdParty = thirdPartyDAO.getThirdPartyDetails(client1.getTypeName());
		     String thirdPartyCompany=thirdParty.getCompanyName();
			 int tpid=thirdParty.getThirdPartyId();
			String name = client1.getTitle()+" "+client1.getFirstName()+" "+client1.getMiddlename()+" "+client1.getLastName(); 	
			str.append("<tr style='cursor: pointer;'; onclick ='setClientData('"+fullname+"','"+client1.getId()+"','"+thirdPartyCompany+"','"+tpid+"')'>");
			//String firstName= client1.getFirstName();
			str.append("<td>0000"+client1.getId()+"</td>");

			String whopay = "";
			if(client1.getWhopay().equals(Constants.PAY_BY_THIRD_PARTY)){
				whopay = "tp";
			}else{
				whopay = "Client";
			}

			
			
			str.append("<td  >"+name+"</td>");

			
			if(client1.getOldclientId()==null){
				client1.setOldclientId("");
			}
			str.append("<td>"+client1.getOldclientId()+"</td>");
			str.append("<td>"+client1.getMobNo()+"</td>");
			str.append("<td>"+client1.getEmail()+"</td>");
			str.append("<td>"+client1.getPostCode()+"</td>");
			str.append("<td>"+client1.getDob()+"</td>");
			if(client1.getLastModified()==null){
				client1.setLastModified("");
			}
			str.append("<td>"+client1.getLastModified()+"</td>");
			
			str.append("</tr>");
			}
			
			
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
	
	
	public String disstatus()throws Exception{
		Connection connection=null;
		
		try {
			
			connection=Connection_provider.getconnection();
			String tepisodeid = request.getParameter("tepisodeid");
			
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			TreatmentEpisodeDAO treatmentEpisodeDAO = new JDBCTreatmentEpisode(connection);
			String sttsus = ipdDAO.getTreatmentEpisodeDischargeStatus(tepisodeid);
			
			String refenddate = treatmentEpisodeDAO.getRefEndDate(tepisodeid);
			
			String result = sttsus + "~" + refenddate;
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+result+""); 
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		return null;
	}
	
    
	
	public String left()throws Exception{
		
		if(!verifyLogin(request)) {
    		return "login";
    	}
    	
    	Connection connection=null;
		String action = request.getParameter("action");
		String selectedid = request.getParameter("selectedid");
		try {
			
			connection=Connection_provider.getconnection();
			BedDao bedDao = new JDBCBedDao(connection);
			
			int r = bedDao.leftCasualtyPatient(selectedid);
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		finally{
			connection.close();
		}
		return "leftcasualty";
	}
    
    public String edit()throws Exception{
    	
    	if(!verifyLogin(request)) {
    		return "login";
    	}
    	
    	Connection connection=null;
		String action = request.getParameter("action");
		try {
			
			connection=Connection_provider.getconnection();
			UserProfileDAO userProfileDAO= new JDBCUserProfileDAO(connection);
			String selectedid = request.getParameter("selectedid");
			String practitionerid= request.getParameter("practitionerid");
			MasterDAO masterDAO=new JDBCMasterDAO(connection);
			NotAvailableSlotDAO notAvailableSlotDAO= new JDBCNotAvailableSlotDAO(connection);
			
			ipdForm.setClientip(selectedid);
			
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			BedDao bedDao=new JDBCBedDao(connection);
			Bed bed = bedDao.getEditIpdData(selectedid);
			if(loginInfo.getIpd_abbr_access()==1){
				  String newipdabbr=ipdDAO.getIpdAbrivationIds(Integer.parseInt(selectedid));
				  ipdForm.setNewipdabbr(newipdabbr);
				  if(Integer.parseInt(bed.getIpdseqno())>0){
						ipdForm.setIpdseqno(bed.getIpdseqno());
					}else{
						ipdForm.setIpdseqno(selectedid);
						}
			  }
				  else{
			    if(Integer.parseInt(bed.getIpdseqno())>0){
					ipdForm.setIpdseqno(bed.getIpdseqno());
					ipdForm.setNewipdabbr(bed.getIpdseqno());
				}else{
					ipdForm.setIpdseqno(selectedid);
					ipdForm.setNewipdabbr(selectedid);
				}

			  }
			
			
			if(practitionerid!=null){
				ipdForm.setPractitionerid(practitionerid);
			} else {
				ipdForm.setPractitionerid(bed.getPractitionerid());
			}
			
			ipdForm.setClientid(bed.getClientid());
			ipdForm.setEditclientid(bed.getClientid());
    		
    		ipdForm.setConditionid(bed.getConditionid());
    		ipdForm.setDepartment(bed.getDepartment());
    		ipdForm.setRefferedby(bed.getRefferedby());
    		ipdForm.setMlcabrivationid(bed.getMlcabrivationid());
    		ipdForm.setRefferedby(bed.getReferenceid());
    		
    		if(bed.getPractitionerid()!=null){
    			
    			UserProfile userProfile=  userProfileDAO.getUserprofileDetails(Integer.parseInt(ipdForm.getPractitionerid()));
    			String specializationId=  userProfile.getDiciplineName();
    			ipdForm.setDepartment(userProfile.getSpecialization());
    			if(loginInfo.getClinicUserid().equals("iconhospital")){
    				if(specializationId.equals("20")||specializationId.equals("21")){
    					ipdForm.setWeightsts("true");
    				}else{
    					ipdForm.setWeightsts("false");
    				}
    				}else{
    					ipdForm.setWeightsts("false");
    				}
    			boolean issystemicreview= masterDAO.isIpdFormFieldActive(specializationId,"Systemic Review");
    			boolean history= masterDAO.isIpdFormFieldActive(specializationId,"History");
    			boolean obstretic_history = masterDAO.isIpdFormFieldActive(specializationId,"OBSTRETIC HISTORY");
    			boolean menstrual_history = masterDAO.isIpdFormFieldActive(specializationId, "MENSTRUAL HISTORY"); 
    			boolean substance_history = masterDAO.isIpdFormFieldActive(specializationId, "SUBSTANCE HISTORY");
    			boolean peditric = masterDAO.isIpdFormFieldActive(specializationId, "Paediatric History");
    			ipdForm.setNicuaccess(masterDAO.isIpdFormFieldActive(specializationId, "NICU Setting"));
    			ipdForm.setNicuaccess(peditric);
    			ipdForm.setIssystemicreview(issystemicreview);
    			ipdForm.setHistory(history);
    			ipdForm.setObstretic_history(obstretic_history);
    			ipdForm.setMenstrual_history(menstrual_history);
    			ipdForm.setSubstance_history(substance_history);
        		ipdForm.setPaediatrichist(peditric);
    			
    		}
    		
    		ArrayList<DiaryManagement> userList=notAvailableSlotDAO.getEditUserList(loginInfo.getId(),selectedid); 
    		ipdForm.setUserList(userList);
    		ArrayList<Bed>bedlist = ipdDAO.geteditWardWiseBedList(bed.getWardid(),bed.getBedid());
    		ipdForm.setBedlist(bedlist);
    		
    		ipdForm.setWardid(bed.getWardid());

    		// 
    		if(bed.getBedid().equals("0")){
    			
    			 // get from log means patient discharge
    			String bedid= ipdDAO.getBedIdFromLog(selectedid);
    			bed.setBedid(bedid);
    			ipdForm.setDisbedid("0");
    			//after discharge bed is zero so it not allow to update from emr so add bed from log akash 23 march 2018 
    			ArrayList<Bed> bedlist1 = ipdDAO.geteditBedListAfterDischarge(bed.getBedid());
        		ipdForm.setBedlist(bedlist1);
    		}  else {
    			
    			ipdForm.setDisbedid(bed.getBedid());
    		}
    			
    		ipdForm.setBedid(bed.getBedid());
    		ipdForm.setOrigbedid(bed.getBedid());
    		
    		//ipdForm.setTpid(bed.getTpid());
    		ipdForm.setStatus(bed.getStatus());
    		ipdForm.setAddmissionfor(bed.getAddmissionfor());
    		ipdForm.setReimbursment(bed.getReimbursment());
    		ipdForm.setSecndryconsult(bed.getSecndryconsult());
    		ipdForm.setMlcno(bed.getMlcno());
    		ipdForm.setMlccase(bed.getMlccase());
    		ipdForm.setAdmissiondetails(bed.getAdmissiondetails());
    		ipdForm.setSuggestedtrtment(bed.getSuggestedtrtment());
    		ipdForm.setHosp(bed.getHosp());
    		ipdForm.setPackagename(bed.getPackagename());
    		//chiefcomplains, presentillness, pasthistory, personalhist, familyhist, onexamination, treatmentepisodeid
    		ipdForm.setChiefcomplains(bed.getChiefcomplains());
    		ipdForm.setPresentillness(bed.getPresentillness());
    		ipdForm.setPasthistory(bed.getPasthistory());
    		ipdForm.setPersonalhist(bed.getPersonalhist());
    		ipdForm.setFamilyhist(bed.getFamilyhist());
    		ipdForm.setOnexamination(bed.getOnexamination());
    		ipdForm.setTreatmentepisodeid(bed.getTreatmentepisodeid());
    		
    		
    		ipdForm.setMaternal_history(bed.getMaternal_history());
    		ipdForm.setPerinatal_history(bed.getPerinatal_history());
    		
    		ipdForm.setSuggestoper(bed.getSuggestoper());
    		ipdForm.setSystdepartment(ipdForm.getSystdepartment());
    		ipdForm.setFpcondition(bed.getFpcondition());
    		ipdForm.setFpnotest(bed.getFpnotest());
    		ipdForm.setNauseacondition(bed.getNauseacondition());
    		ipdForm.setNauseanotes(bed.getNauseanotes());
    		ipdForm.setFnucondition(bed.getFnucondition());
    		ipdForm.setFnunotes(bed.getFnunotes());
    		ipdForm.setSmcondition(bed.getSmcondition());
    		ipdForm.setSmnotes(bed.getSmnotes());
    		ipdForm.setChestpaincond(bed.getChestpaincond());
    		ipdForm.setChestpainnotes(bed.getChestpainnotes());
    		ipdForm.setDimvisioncond(bed.getDimvisioncond());
    		ipdForm.setDimvisionnotes(bed.getDimvisionnotes());
    		
    		//dimvisionnotes, hgucondition, hgunotes, hmcondition, hmnotes, jointpaincond, jointpainnotes, 
    		ipdForm.setHgucondition(bed.getHgucondition());
    		ipdForm.setHgunotes(bed.getHgunotes());
    		ipdForm.setHmcondition(bed.getHmcondition());
    		ipdForm.setHmnotes(bed.getHmnotes());
    		ipdForm.setJointpaincond(bed.getJointpaincond());
    		ipdForm.setJointpainnotes(bed.getJointpainnotes());
    		
    		//constipationcond, constpationnotes, specialnotes, edemafeetcondi, edemafeetnotes, hematuriacondi, 
    		ipdForm.setConstipationcond(bed.getConstipationcond());
    		ipdForm.setConstpationnotes(bed.getConstpationnotes());
    		ipdForm.setSpecialnotes(bed.getSpecialnotes());
    		ipdForm.setEdemafeetcondi(bed.getEdemafeetcondi());
    		ipdForm.setEdemafeetnotes(bed.getEdemafeetnotes());
    		ipdForm.setHematuriacondi(bed.getHematuriacondi());
    		ipdForm.setHematurianotes(bed.getHematurianotes());
    		
    		//hematurianotes, bmcondition, bmnotes, oliguriacondi, oligurianotes, pstgucondi, pstgunotes, 
    		ipdForm.setBmcondition(bed.getBmcondition());
    		ipdForm.setBmnotes(bed.getBmnotes());
    		ipdForm.setOliguriacondi(bed.getOliguriacondi());
    		ipdForm.setOligurianotes(bed.getOligurianotes());
    		ipdForm.setPstgucondi(bed.getPstgucondi());
    		ipdForm.setPstgunotes(bed.getPstgunotes());
    		
    		//bmecondition, bmenotes, tnecondition, tnenotes, weaknesscondi, weaknessnotes, ihcondition, ihnotes
    		ipdForm.setBmecondition(bed.getBmecondition());
    		ipdForm.setBmenotes(bed.getBmenotes());
    		ipdForm.setTnecondition(bed.getTnecondition());
    		ipdForm.setTnenotes(bed.getTnenotes());
    		ipdForm.setWeaknesscondi(bed.getWeaknesscondi());
    		ipdForm.setWeaknessnotes(bed.getWeaknessnotes());
    		ipdForm.setIhcondition(bed.getIhcondition());
    		ipdForm.setIhnotes(bed.getIhnotes());
    		ipdForm.setAdmission_reason(bed.getAdmission_reason());
    		ipdForm.setEarlierinvest(bed.getEarlierinvest());
    		ipdForm.setAlergies(bed.getAlergies());
    		
    		
    		//gynic details
    		ipdForm.setAlcohal(bed.getAlcohal());
    		ipdForm.setDrugs(bed.getDrugs());
    		ipdForm.setOther_medication(bed.getOther_medication());
    		ipdForm.setTobaco(bed.getTobaco());
    		ipdForm.setTobaconotes(bed.getTobaconotes());
    		ipdForm.setSmoking(bed.getSmoking());
    		
    		ipdForm.setAge_menarche(bed.getAge_menarche());
    		ipdForm.setLmp(bed.getLmp());
    		ipdForm.setLlmp(bed.getLlmp());
    		ipdForm.setPmc(bed.getPmc());
    		//peditric 
    		ipdForm.setBirthhist(bed.getBirthhist());
    		ipdForm.setDiethist(bed.getDiethist());
    		ipdForm.setDevelopmenthist(bed.getDevelopmenthist());
    		ipdForm.setEmmunizationhist(bed.getEmmunizationhist());
    		ipdForm.setHeadcircumference(bed.getHeadcircumference());
    		ipdForm.setMidarmcircumference(bed.getMidarmcircumference());
    		ipdForm.setLength(bed.getLength());
    		ipdForm.setWtaddmission(bed.getWtaddmission());
    		ipdForm.setWtdischarge(bed.getWtdischarge());
    		
    		
    		ipdForm.setCycle_length(bed.getCycle_length());
    		ipdForm.setDuration_flow(bed.getDuration_flow());
    		ipdForm.setAmount_flow(bed.getAmount_flow());
    		ipdForm.setDysmenorrhea(bed.getDysmenorrhea());
    		ipdForm.setDyspareunia(bed.getDyspareunia());
    		ipdForm.setHopassing_clots(bed.getHopassing_clots());
    		
    		ipdForm.setWhite_disc_itching(bed.getWhite_disc_itching());
    		ipdForm.setIntercourse_freq(bed.getIntercourse_freq());
    		ipdForm.setGalactorrea(bed.getGalactorrea());
    		
    		ipdForm.setHo_contraception(bed.getHo_contraception());
    		ipdForm.setRubella_vaccine(bed.getRubella_vaccine());
    		ipdForm.setMenopause(bed.getMenopause());
    		ipdForm.setNulligravida(bed.getNulligravida());
    		ipdForm.setCurrent_pregnent(bed.getCurrent_pregnent());
    		ipdForm.setIud(bed.getIud());
    		
    		ipdForm.setLive_boys(bed.getLive_boys());
    		ipdForm.setLive_girls(bed.getLive_girls());
    		ipdForm.setStillbirths(bed.getStillbirths());
    		ipdForm.setAbortions(bed.getAbortions());
    		ipdForm.setDeath_children(bed.getDeath_children());
  		
    		//parity_aboration_notes,p,l,a,d
    		ipdForm.setParity_abortion_notes(bed.getParity_abortion_notes());
    		ipdForm.setP(bed.getP());
    		ipdForm.setL(bed.getL());
    		ipdForm.setA(bed.getA());
    		ipdForm.setD(bed.getD());
    		ipdForm.setMlcrefdoctor(bed.getMlcrefdoctor());
    		ipdForm.setSurgicalnotes(bed.getSurgicalnotes());
    		String mlccase = bed.getMlccase();
    		ipdForm.setMlccase(bed.getMlccase());
    		
    		//giddinesscondition,giddinessnotes,unconcondition,unconnotes
    		ipdForm.setGiddinesscondition(bed.getGiddinesscondition());
    		ipdForm.setGiddinessnotes(bed.getGiddinessnotes());
    		ipdForm.setUnconcondition(bed.getUnconcondition());
    		ipdForm.setUnconnotes(bed.getUnconnotes());
    		
    		ipdForm.setGstureage(bed.getGstureage());
    		ipdForm.setWtonbirth(bed.getWtonbirth());
    		
    		
    		ArrayList<Bed> gynicobsList= bedDao.getGynicObsList(selectedid);
    		ipdForm.setGynicobsList(gynicobsList);
    		
    		
    		String temp[] = bed.getAdmissiondate().split(" ");
			String date = DateTimeUtils.getCommencingDate1(temp[0]);
			ipdForm.setAdmissiondate(date);
			String time[] = temp[1].split(":");
			String hh = time[0];
			String mm = time[1];
			ipdForm.setHour(hh);
			ipdForm.setMinute(mm);
    		
    		//ipdForm.setAdmissiondate(bed.getAdmissiondate());
    		
    		ipdForm.setId(Integer.parseInt(selectedid));
    		
    		//set treatment episode
    		
    		ClientDAO clientDAO = new JDBCClientDAO(connection);
    		Client client = clientDAO.getClientDetails(ipdForm.getClientid());
    		String fullname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();
    		ipdForm.setFname(client.getFirstName());
    		ipdForm.setLname(client.getLastName());
    		ipdForm.setTitle(client.getTitle());
    		ipdForm.setAge(client.getAge1());
    		ipdForm.setGender(client.getGender());
    		ipdForm.setClient(fullname);
    		ipdForm.setRelativename(client.getEmergencyContName());
    		ipdForm.setRelationcont(client.getEmergencyContNo());
    		ipdForm.setRelation(client.getRelation());
    		String numcount=ipdDAO.getNumofAdmissionCount(ipdForm.getClientid());
    		ipdForm.setNum_admission(numcount);
    		ipdForm.setDob(client.getDob());
    		ipdForm.setTpid(client.getTypeName());
    		ipdForm.setAddress(client.getAddress());
    		ipdForm.setAbrivationid(client.getAbrivationid());
    		String age = DateTimeUtils.getAge1(client.getDob());
    		String agegender = age + "Years" + " / " + client.getGender();
    		ipdForm.setAgegender(age+"/"+client.getGender());
    		ipdForm.setMob(client.getMobNo());
    		
    		
    		String payby = client.getWhopay();
    		if(client.getWhopay().equals("Self")){
    			payby = "Client";
    		}
    		
    		if(payby.equalsIgnoreCase("client")){
    			
    			  ipdForm.setPayee("0");
    			
    		} else {
    			  ipdForm.setPayee("1");
    		}
    		
    		TreatmentEpisodeDAO treatmentEpisodeDAO = new JDBCTreatmentEpisode(connection);
    		ArrayList<TreatmentEpisode> treatmentEpisodeList = treatmentEpisodeDAO.getIpdTreatmentEpisodeList(ipdForm.getClientid(), payby);
    		
    		if(treatmentEpisodeList.size()<1){
    			   
    			treatmentEpisodeList=treatmentEpisodeDAO.getSelectedTreatmentEpisode(bed.getTreatmentepisodeid());
    		}
    		ipdForm.setTreatmentEpisodeList(treatmentEpisodeList);
    		ipdForm.setTreatmentEpisode(bed.getTreatmentepisodeid());
    		
    		ipdForm.setDaycare(bedDao.isDayCare(selectedid));
    		
    		ArrayList<Bed>ipdConditionids = bedDao.getIpdConditionList(selectedid);
    		ArrayList<Client> ipdCondtitionList = new ArrayList<Client>(); // clientDAO.getEmrTreatmentTypeList();
    		session.setAttribute("ipdConditionids", ipdConditionids);
    		session.setAttribute("ipdCondtitionList", ipdCondtitionList);
    		
    		
    		String chief_comlint_id=masterDAO.getIpdTemplateId("Chief Complaints");
			String present_ill_id=masterDAO.getIpdTemplateId("Present Illness");
			String past_history_id=masterDAO.getIpdTemplateId("Past History");
			String family_hist_id=masterDAO.getIpdTemplateId("Family History");
			String personal_hist_id=masterDAO.getIpdTemplateId("Personal History");
			String onexami_id=masterDAO.getIpdTemplateId("On Examination");
			String tretment_given_id=masterDAO.getIpdTemplateId("Treatment Given");
			String maternalHisttempid=masterDAO.getIpdTemplateId("Maternal History");
			String perinatalHistroy=masterDAO.getIpdTemplateId("Perinatal History");
			
			ArrayList<Master> chief_complaints_list=masterDAO.getIpdTemplateListNames(chief_comlint_id);
			ArrayList<Master> present_illness_list=masterDAO.getIpdTemplateListNames(present_ill_id);
			ArrayList<Master> past_history_list=masterDAO.getIpdTemplateListNames(past_history_id);
			ArrayList<Master> family_history_list=masterDAO.getIpdTemplateListNames(family_hist_id);
			ArrayList<Master> personal_hist_list=masterDAO.getIpdTemplateListNames(personal_hist_id);
			ArrayList<Master> on_exam_list=masterDAO.getIpdTemplateListNames(onexami_id);
			ArrayList<Master> treatment_given_list=masterDAO.getIpdTemplateListNames(tretment_given_id);
			ArrayList<Master> perintal_hisry_list=masterDAO.getIpdTemplateListNames(perinatalHistroy);
			ArrayList<Master> maternal_histry_list=masterDAO.getIpdTemplateListNames(maternalHisttempid);
			
			ipdForm.setChief_complaints_list(chief_complaints_list);
			ipdForm.setPresent_illness_list(present_illness_list);
			ipdForm.setPast_history_list(past_history_list);
			ipdForm.setFamily_history_list(family_history_list);
			ipdForm.setPersonal_hist_list(personal_hist_list);
			ipdForm.setOn_exam_list(on_exam_list);
			ipdForm.setTreatment_given_list(treatment_given_list);
			ipdForm.setPerintal_hisry_list(perintal_hisry_list);
			ipdForm.setMaternal_histry_list(maternal_histry_list);
			int selectedid1 = loginInfo.getId();
			
			ClinicDAO clinicListDAO = new JDBCClinicDAO(connection);
			com.apm.Registration.eu.entity.Clinic cliniclist = clinicListDAO.getCliniclistDetails(selectedid1);
			ipdForm.setClinicName(cliniclist.getClinicName());
			
			
			ArrayList<Bed> wardlist=bedDao.getAllWardList(bed.getAction());
			ipdForm.setWardlist(wardlist);
			
			 //get prescription list
			   ArrayList<Priscription>admissionPriscList = ipdDAO.getAdmissionPrescList(selectedid);
			   if(admissionPriscList.size()>0){
				   Priscription pr= admissionPriscList.get(admissionPriscList.size()-1);
				   ipdForm.setStrengthflag(pr.isStrengthflag());
				   ipdForm.setRemarkflag(pr.isRemarkflag());
				   ipdForm.setQuantityflag(pr.isStrengthflag());
			   }
			   String discadvoice=ipdDAO.getDIscPrisc(selectedid);
			   
			   ipdForm.setAdvoice(discadvoice);
			   
			   ipdForm.setAdmissionPriscList(admissionPriscList);
			   session.setAttribute("dischargePriscList",admissionPriscList);
			ipdForm.setActiontype(bed.getAction());
			
			if(bed.getAction().equals("1")){
				if(action.equals("1")){
					 wardlist=bedDao.getAllWardList("1");
					ipdForm.setWardlist(wardlist);
					return "editcasualty";
				}
				return "editcasualty";
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
    	return "edit";
    }
    
    
    public String otinfo()throws Exception{
    	String clientid = request.getParameter("clientid");
    	 Connection connection=null;
         
         try {
         	
         	connection=Connection_provider.getconnection();
         	IpdDAO ipdDAO = new JDBCIpdDAO(connection);
         	
         	int  lastipdid = ipdDAO.getLastIpdId(clientid);
         	
         	BedDao bedDao=new JDBCBedDao(connection);
			
			Bed bed = bedDao.getEditIpdData(Integer.toString(lastipdid));
			ClientDAO clientDAO = new JDBCClientDAO(connection);
    		Client client = clientDAO.getClientDetails(ipdForm.getClientid());
    		
    		String age = DateTimeUtils.getAge(client.getDob());
    		String agegender = age + "Years" + " / " + client.getGender();
    		
    		String wardname=ipdDAO.getIpdWardName(bed.getWardid());
			String bedname = ipdDAO.getIpdBedName(bed.getBedid());
			
			String result = agegender + "~" + lastipdid + "~" + wardname + "~" + bedname;
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+result+""); 
         	
         }catch (Exception e) {
			// TODO: handle exception
		}
         finally{
 			connection.close();
 		}
    	
    	return null;
    }

    //This method now called from BookappointmentAjaxAction 
    public String prodstock()throws Exception{
    	String masterchargetype = request.getParameter("masterchargetype");
    	String prodid = request.getParameter("prodid");
    	
    	  Connection connection=null;
          
          try {
          	
          	connection=Connection_provider.getconnection();
          	IpdDAO ipdDAO = new JDBCIpdDAO(connection);
          	CompleteAptmDAO completeAptmDAO = new JDBCCompleteAptmDAO(connection);
          	
          	boolean checkInventoryProduct = ipdDAO.checkInventoryChargeType(masterchargetype);
          	Product product = completeAptmDAO.getInventoryProductDetails(prodid);
          	
          	
          	
          	String str = "";
          	if(!checkInventoryProduct){
          		int sumqty = ipdDAO.getSumOfProdQty(prodid);
          		str = "1/"+product.getStock()+"/"+sumqty;
          	}else{
          		str = "0/0/0";
          	}
          	
          	response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+str+""); 
    		
    	}catch (Exception e) {
			// TODO: handle exception
		}finally{
			connection.close();
		}
    	
    	return null;
    }
    
    public String charge()throws Exception{
    	
    	   Connection connection=null;
           
           try {
           	
           	connection=Connection_provider.getconnection();
           	IpdDAO ipdDAO = new JDBCIpdDAO(connection);
           	String chargetype = request.getParameter("chargetype");
           	
           	String ipdwhopay = request.getParameter("ipdwhopay");
           	String ipdtpid = request.getParameter("ipdtpid");
           	String ipdaddmissionid = request.getParameter("ipdaddmissionid");
           	String clientId = request.getParameter("clientId");
           	
           	if(ipdwhopay.equals("")){
           		ClientDAO clientDAO = new JDBCClientDAO(connection);
           		Client client = clientDAO.getClientDetails(clientId);
           		ipdwhopay = client.getWhopay();
           	}
           	
           	if(ipdwhopay.equals(Constants.PAY_BY_CLIENT)){
           		ipdtpid = "0";
           	}
           	
           	if(ipdaddmissionid==null){
           		
           		ipdaddmissionid="0";
           	}
           	if(ipdaddmissionid.equals("")){
           		ipdaddmissionid="0";
           	}
           	
           	BedDao bedDao=new JDBCBedDao(connection);
			Bed bed = bedDao.getEditIpdData(ipdaddmissionid);
			
			String wardid = bed.getWardid();
			
			if(wardid==null){
				wardid="0";
			}
			if(wardid.equals("")){
				wardid="0";
			}
           	
           	ArrayList<Master>list = ipdDAO.getFilteredChargeList(chargetype,ipdtpid,wardid,clientId,loginInfo.isShow_wardname());
           	StringBuffer str = new StringBuffer();
          
           		str.append("<select onchange='setAdditionalChargeAjax1(this.value)' name='chargeTYpe' id='chargeTYpe' class='form-control showToolTip chosen' >");
           		str.append("<option value='0'>Select Charge</option>");
           		for(Master master : list){
           			str.append("<option value='"+master.getId()+"'>"+master.getName()+"</option>");
           		}
           		str.append("</select>");
           		
           		response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(""+str.toString()+""); 
           	
           }catch(Exception e){
        	   e.printStackTrace();
           }
           finally{
   			connection.close();
   		}
    	return null;
    }
    
    
    public String additionalcharge()throws Exception{
    	
 	   Connection connection=null;
        
        try {
        	
        	connection=Connection_provider.getconnection();
        	IpdDAO ipdDAO = new JDBCIpdDAO(connection);
        	String chargetype = request.getParameter("chargetype");
        	
        	String ipdwhopay = request.getParameter("ipdwhopay");
        	String ipdtpid = request.getParameter("ipdtpid");
        	String ipdaddmissionid = request.getParameter("ipdaddmissionid");
        	String clientId = request.getParameter("clientId");
        	
        	if(ipdwhopay.equals("")){
        		ClientDAO clientDAO = new JDBCClientDAO(connection);
        		Client client = clientDAO.getClientDetails(clientId);
        		ipdwhopay = client.getWhopay();
        	}
        	
        	if(ipdwhopay.equals(Constants.PAY_BY_CLIENT)){
        		ipdtpid = "0";
        	}
        	
        	if(ipdaddmissionid==null){
        		
        		ipdaddmissionid="0";
        	}
        	if(ipdaddmissionid.equals("")){
        		ipdaddmissionid="0";
        	}
        	
        	BedDao bedDao=new JDBCBedDao(connection);
			Bed bed = bedDao.getEditIpdData(ipdaddmissionid);
			
			String wardid = bed.getWardid();
			
			if(wardid==null){
				wardid="0";
			}
			if(wardid.equals("")){
				wardid="0";
			}
        	
        	ArrayList<Master>list = ipdDAO.getAdditionalFilteredChargeList(chargetype,ipdtpid,wardid,clientId);
        	StringBuffer str = new StringBuffer();
       
        		str.append("<select onchange='setAdditionalChargeAjax1(this.value)' name='chargeTYpe' id='chargeTYpe' class='form-control showToolTip chosen' >");
        		str.append("<option value='0'>Select Charge</option>");
        		for(Master master : list){
        			str.append("<option value='"+master.getId()+"'>"+master.getName()+"</option>");
        		}
        		str.append("</select>");
        		
        		response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(""+str.toString()+""); 
        	
        }catch(Exception e){
     	   e.printStackTrace();
        }
        finally{
			connection.close();
		}
 	return null;
 }
    
    
    public String apmtcharge()throws Exception{
    	
 	   Connection connection=null;
        
        try {
        	
        	connection=Connection_provider.getconnection();
        	ChargesAccountProcessingDAO chargesAccountProcessingDAO= new JDBCChargeAccountProcesDAO(connection);
        	IpdDAO ipdDAO = new JDBCIpdDAO(connection);
        	String chargetype = request.getParameter("chargetype");
        	String tpid=request.getParameter("tpid");
        	String whopay = request.getParameter("whopay");
        	String clientId = request.getParameter("clientId");
        	
        	if(whopay.equals(Constants.PAY_BY_CLIENT)){
        		tpid = "0";
        	}
        	
        	String wardid = "0";
        	
        	//ArrayList<Master>list = ipdDAO.getFilteredOpdChargeList(chargetype,tpid,whopay);
        	ArrayList<Master>list = ipdDAO.getFilteredChargeList(chargetype,tpid,wardid,clientId,loginInfo.isShow_wardname());
        	StringBuffer str = new StringBuffer();
        	
       
        		str.append("<select onchange='setAdditionalChargeAjax(this.value)' name='chargeTYpe' id='addChargeType' class='form-control showToolTip chosen' >");
        		str.append("<option value='0'>Select Charge</option>");
        		for(Master master : list){
        			str.append("<option value='"+master.getId()+"'>"+master.getName()+"</option>");
        		}
        		str.append("</select>");
        		int compulsary_consultant=chargesAccountProcessingDAO.isCompulasryConsultant(chargetype);
        		str.append("!@");
        		str.append(compulsary_consultant);
        		response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(""+str.toString()+""); 
        	
        }catch(Exception e){
     	   e.printStackTrace();
        }finally{
			connection.close();
		}
 	
 	return null;
 }
    
 
    
    public String editapmtcharge()throws Exception{
    	
 	   Connection connection=null;
        
        try {
        	
        	connection=Connection_provider.getconnection();
        	IpdDAO ipdDAO = new JDBCIpdDAO(connection);
        	String chargetype = request.getParameter("chargetype");
        	String tpid=request.getParameter("tpid");
        	String whopay = request.getParameter("whopay");
        	String clientid = request.getParameter("clientid");
        	
        	ClientDAO clientDAO = new JDBCClientDAO(connection);
        	Client client = clientDAO.getClientDetails(clientid);
        	
        	tpid = client.getTypeName();
        	
        	if(whopay.equals(Constants.PAY_BY_CLIENT)){
        		tpid = "0";
        	}
        	
        	
        	
        	
        	ArrayList<Master>list = ipdDAO.getFilteredOpdChargeList(chargetype,tpid,whopay);
        	StringBuffer str = new StringBuffer();
        	
       
        		str.append("<select onchange='setAdditionalChargeAjax1(this.value)' name='chargeTYpe' id='addChargeType1' class='form-control showToolTip chosen' >");
        		str.append("<option value='0'>Select Charge</option>");
        		for(Master master : list){
        			str.append("<option value='"+master.getId()+"'>"+master.getName()+"</option>");
        		}
        		str.append("</select>");
        		
        		response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(""+str.toString()+""); 
        	
        }catch(Exception e){
     	   e.printStackTrace();
        }
        finally{
			connection.close();
		}
 	return null;
 }
 
    
	
	public IpdForm getModel() {
		// TODO Auto-generated method stub
		return ipdForm;
	}

	static int index=1;
	
public String addnewrow() throws Exception{
		
	  StringBuffer buffer=new StringBuffer();		
		
      Connection connection=null;
      
      try {
      	int index=Integer.parseInt(request.getParameter("rowcount"));
      	int count=index+1;
      	connection=Connection_provider.getconnection();
      	buffer.append("<tr>");
      	buffer.append("<td>");
          buffer.append("<select class='form-control marbot15 chosen' name='conditions["+count+"].conditionid' data-toggle='tooltip' id='condition"+count+"' >");
          buffer.append("<option value='0'>Select Diagnosis</option>");
          TreatmentTypeDAO treatmentTypeDAO=new JDBCTreatmentTypeDAO(connection);
          
          ArrayList<TreatmentType> condtitionList = treatmentTypeDAO.getConditionList();
          for (TreatmentType treatmentType : condtitionList) {
				
          		buffer.append("<option value='"+treatmentType.getId()+"'> "+treatmentType.getTreatmentName()+"</option>");
		  }
          
          buffer.append("</select>");
          buffer.append("</td");
          buffer.append("<td></td>");
          buffer.append("<td>");
          buffer.append("<input type='hidden' name='conditions["+count+"].conditionname' id='conditionname"+count+"'/>");
          buffer.append("</td>");
          buffer.append("<td width='3%' class='paddtop'> <span id='spn"+count+"'> <a type='button' class='btn btn-primary savebtn' onclick=addMoreCondition('innercondition','spn"+count+"') ><i class='fa fa-plus'></i></a></span></td>");
          buffer.append("</tr>");
         
      	response.setContentType("text/html");
  		response.setHeader("Cache-Control", "no-cache");
  		response.getWriter().write(buffer.toString());
		} catch (Exception e) {
              e.printStackTrace();
		}
        finally{
        	connection.close();
        }
        
		return null;
	}

	
	/*public String addnewrow() throws Exception{
		
        StringBuffer buffer=new StringBuffer();		
		
        Connection connection=null;
        
        try {
        	
        	connection=Connection_provider.getconnection();
        	buffer.append("<label for='inputEmail' class='control-label col-xs-3'>"+index+".</label>");
        	buffer.append("<div class='col-xs-6'>");
        	TreatmentTypeDAO treatmentTypeDAO=new JDBCTreatmentTypeDAO(connection);
        	ArrayList<TreatmentType> conditionlist=treatmentTypeDAO.getConditionList(); 	
            buffer.append("<select class='form-control'>");
            buffer.append("<option value='0'> Select Condition</option>");
            
            for (TreatmentType treatmentType : conditionlist) {
				
            	buffer.append("<option value='"+treatmentType.getId()+"'> "+treatmentType.getTreatmentName()+"</option>");
            	
			}
            buffer.append("</select>");
            buffer.append("</div>");
        	response.setContentType("text/html");
    		response.setHeader("Cache-Control", "no-cache");
    		response.getWriter().write(buffer.toString());
			index++;
		} catch (Exception e) {
              e.printStackTrace();
		}
        finally{
        	connection.close();
        }
        
		return null;
	}*/
	
	
	
	public void prepare() throws Exception {

		Connection connection=null;
		
		try {
			
			connection=Connection_provider.getconnection();
			NotAvailableSlotDAO notAvailableSlotDAO=new JDBCNotAvailableSlotDAO(connection);
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			TreatmentEpisodeDAO treatmentEpisodeDAO = new JDBCTreatmentEpisode(connection);
			DiaryManagementDAO diaryManagementDAO=new JDBCDiaryManagentDAO(connection);
			EmrDAO emrDAO = new JDBCEmrDAO(connection);
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
			ArrayList<DiaryManagement> userList = notAvailableSlotDAO.getUserList(loginInfo.getId());
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			ipdForm.setUserList(userList);
			
			
			
			ArrayList<Client> condtitionList = clientDAO.getEmrTreatmentTypeList();
			ipdForm.setCondtitionList(condtitionList);
			
			String datetime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			BedDao bedDao=new JDBCBedDao(connection);
			ArrayList<Bed> bedlist=bedDao.getAllBedList();
			ipdForm.setBedlist(bedlist);
			ArrayList<Bed> wardlist=bedDao.getAllWardList("0");
			ipdForm.setWardlist(wardlist);
			
			ArrayList<String> initialList = clientDAO.getInitialList();
			ipdForm.setInitialList(initialList);
			
			//set state and city list
			InventoryVendorDAO vendorDAO=new JDBCInventoryVendorDAO(connection);
			ArrayList<Master> statelist= vendorDAO.getAllStateList();
			ipdForm.setStatelist(statelist);
			ArrayList<Master> citylist= vendorDAO.getAllCityList();
			ipdForm.setCitylist(citylist);
			//  25 dec 2017 list get from referal table
			ArrayList<Client> refrenceList = clientDAO.getReferenceList();
			ipdForm.setRefrenceList(refrenceList);
			
			ArrayList<Location>locationList = diaryManagementDAO.getLocationList(loginInfo.getId());
			ipdForm.setLocationList(locationList);
			
			ArrayList<Client> diagnosisList=clientDAO.getEmrTreatmentTypeList();
			ipdForm.setDiagnosisList(diagnosisList);
			
			ArrayList<String> departmentList= diaryManagementDAO.getDepartmentList();
			ipdForm.setDepartmentList(departmentList);
			
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			ArrayList<Accounts>thirdPartyList = accountsDAO.getThirdPartyList(loginInfo.getId());
			ipdForm.setThirdPartyList(thirdPartyList);
			
			ArrayList<Client> clientOccupationList = clientDAO.getOccupationList();
			Client client1 = new Client();
			client1.getOther();
			clientOccupationList.add(client1);
			
			ipdForm.setClientOccupationList(clientOccupationList);
			ipdForm.setCountryList(PopulateList.countryList());
			
			ArrayList<Client>sourceOfIntroList = clientDAO.getSourceOfIntroList();
			ipdForm.setSourceOfIntroList(sourceOfIntroList);
			
			ArrayList<TreatmentEpisode> sourceOfReferralList = treatmentEpisodeDAO.getSourceOfReferralList();
			ipdForm.setSourceOfReferralList(sourceOfReferralList);
			
			SMSTemplateDAO smsTemplateDAO = new JDBCSMSTemplateDao(connection);
			ArrayList<EmailTemplate>smsTemplateList = smsTemplateDAO.getAllSMSTemplates();
			ipdForm.setSmsTemplateList(smsTemplateList);
			
			ArrayList<Client> surgeryList = clientDAO.getSurgeryList();
			if(surgeryList.size()==0){
				surgeryList = new ArrayList<Client>();
			}
			Client client3 = new Client();
			client3.getOther();
			surgeryList.add(client3);
			ipdForm.setSurgeryList(surgeryList);
			
			
			//user define date time
			ipdForm.setHourList(PopulateList.hourListNew());
			ipdForm.setMinuteList(PopulateList.getMinuteList());
			
			String temp[] = datetime.split(" ");
			String date = DateTimeUtils.getCommencingDate1(temp[0]);
			ipdForm.setAdmissiondate(date);
			ipdForm.setDischargedate(date);
			String time[] = temp[1].split(":");
			String hh = time[0];
			String mm = time[1];
			ipdForm.setHour(hh);
			ipdForm.setMinute(mm);
			
			ArrayList<Discharge>dischargeOutcomeList = emrDAO.getDischrageOutcomeList();
			ipdForm.setDischargeOutcomeList(dischargeOutcomeList);
			
			ArrayList<Discharge>dischargeStatusList = emrDAO.getDischrageStatusList();
			ipdForm.setDischargeStatusList(dischargeStatusList);
			
			String dates = DateTimeUtils.getDashboardTodayDate(loginInfo.getTimeZone());
			ipdForm.setPriscdate(dates);
			ipdForm.setPriscdateandtime(DateTimeUtils.getPriscDatetime(loginInfo.getTimeZone()));
			
			ArrayList<Master>mdicinecategoryList = emrDAO.getmedicineCategoryList();
			ipdForm.setMdicinecategoryList(mdicinecategoryList);
			
			ArrayList<Master>dosageList = emrDAO.getDosageList();
			ipdForm.setDosageList(dosageList);
			
			ArrayList<Master>mdicneTypeList = emrDAO.getMedicineTypeList();
			ipdForm.setMdicneTypeList(mdicneTypeList);
			
			//  25-01-2019
			//ArrayList<Master>medicineList = masterDAO.getMedicineList();
			ArrayList<Master>medicineList = new ArrayList<Master>();
			ipdForm.setMedicineList(medicineList);
			
			ArrayList<Priscription>parentPriscList = new ArrayList<Priscription>();
			ipdForm.setParentPriscList(parentPriscList);
			
			ArrayList<Master>dosagenoteList = masterDAO.getDosageNoteList();
			ipdForm.setDosagenoteList(dosagenoteList);
			ArrayList<Client> thirdPartyTypeList = new ArrayList<Client>();
			thirdPartyTypeList = clientDAO.getThirdPartyType();
			ipdForm.setThirdPartyTypeList(thirdPartyTypeList);
			
			
			ArrayList<String> monthList=PopulateList.monthList();
			ArrayList<String> yearList=PopulateList.yearList();
			ipdForm.setMonthList(monthList);
			ipdForm.setYearList(yearList);
			
			Clinic clinic = new Clinic();
			ClinicDAO clinicDAO  = new JDBCClinicDAO(connection);
			
			
			ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
			
			clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			ipdForm.setClinicName(clinic.getClinicName());
			ipdForm.setClinicOwner(clinic.getClinicOwner());
			ipdForm.setOwner_qualification(clinic.getOwner_qualification());
			ipdForm.setLocationAdressList(locationAdressList);
			ipdForm.setAddress(clinic.getAddress());
			ipdForm.setLandLine(clinic.getLandLine());
			ipdForm.setClinicemail(clinic.getEmail());
			ipdForm.setWebsiteUrl(clinic.getWebsiteUrl());
			ipdForm.setClinicLogo(clinic.getUserImageFileName());
			
			
			//set template name list
			
			ArrayList<Priscription>templateNameList = emrDAO.getTemplateNameList(loginInfo);
			if(templateNameList.size()==0){
				templateNameList = new ArrayList<Priscription>();
			}
			ipdForm.setTemplateNameList(templateNameList);
			ipdForm.setCountry("India");
			
			// set staff name list of OPERATING SURGEON and OPERATIVE PROCEDURE
			UserProfile userProfile = userProfileDAO.getUserprofileDetails(loginInfo.getId());
			ArrayList<Master> procedureList = notAvailableSlotDAO.getProcedureList(userProfile.getDiciplineName());
			if(procedureList.size()==0){
				procedureList = new ArrayList<Master>();
			}
			ipdForm.setProcedureList(procedureList);
			
			/*ArrayList<UserProfile> staffList = notAvailableSlotDAO.getOTstaffList();
			if(staffList.size()==0){
				staffList = new ArrayList<UserProfile>();
			}
			ipdForm.setStaffList(staffList);*/
			
			ArrayList<UserProfile>staffList = userProfileDAO.getAllPractitionerList("1",null,null,null,null);
			ipdForm.setStaffList(staffList);
			
			if(staffList.size()==0){
				staffList = new ArrayList<UserProfile>();
			}
			ArrayList<Client> anesthesiaList =  userProfileDAO.getAllAnethesiaList();
			ipdForm.setAnesthesiaList(anesthesiaList);
			
			ArrayList<UserProfile> mlcdrlist = userProfileDAO.getAllPractitionerList(null,null,null,null,"1");
			ipdForm.setMlcdrlist(mlcdrlist);
			
			ArrayList<Master>declerationTitleList = clientDAO.getDeclerationTitleList();
			ipdForm.setDeclerationTitleList(declerationTitleList);
			
			//jitu
			ArrayList<Master> specializationTemplateList= masterDAO.getMasterSpecializationList();
			ipdForm.setSpecializationTemplateList(specializationTemplateList);
			//set template name list
			
			ArrayList<Master> priscUnitList= masterDAO.getPriscUnitList();
			ipdForm.setPriscUnitList(priscUnitList);
			//  11 April 2018
			ArrayList<Priscription> medicinetimelist = emrDAO.getMedicineTimeList(); 
			ipdForm.setMedicinetimelist(medicinetimelist);
			
			ArrayList<Master>nimaidoselist = masterDAO.getnimaidoselistt();
			ArrayList<Master>nimaiqtylist = masterDAO.getnimaiqtylist();
			ArrayList<Master>nimairemarklist = masterDAO.getnimairemarlist();
			
			ipdForm.setNimaidoselist(nimaidoselist);
			ipdForm.setNimaiqtylist(nimaiqtylist);
			ipdForm.setNimairemarklist(nimairemarklist);
			
			ArrayList<Master> requestlocationlist= pharmacyDAO.getAllLocationNew();
			ipdForm.setRequestlocationlist(requestlocationlist);
			if(loginInfo.isPrisc_location_list()){
				int default_location = pharmacyDAO.getByDefaultPharmacyLocation();
				ipdForm.setRequestlocationid(""+default_location);
			}
			//ipdForm.setBirthPlaceList(masterDAO.getBirthPlaceList());
		} catch (Exception e) {

		   e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		
	}
	
	public String getspeciality() throws Exception{
		
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			String doctorid= request.getParameter("doctorid");
			UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
			UserProfile userProfile=  userProfileDAO.getUserprofileDetails(Integer.parseInt(doctorid));
			
			StringBuffer buffer= new StringBuffer();
			buffer.append("<label for='inputEmail' class='control-label'>Speciality</label>");
			buffer.append("<select class='form-control chosen' name='department' id='department'>");
			 
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
	

	
	public String getipdtemplate()throws Exception {
		
		Connection connection=null;
		
		try {
			connection=Connection_provider.getconnection();
			MasterDAO masterDAO=new JDBCMasterDAO(connection);
			String id=request.getParameter("id");
			
			Master master=masterDAO.getIpdTemplate(id);
			
			 response.setContentType("text/html");
		      response.setHeader("Cache-Control", "no-cache");
		      response.getWriter().write(""+master.getText()+"");
		      
			
		} catch (Exception e) {

			e.printStackTrace();
			
		}finally{
			connection.close();
		}
		
		return null;
		
	}
	
	public String getdisadvoice() throws Exception {
	     
	     Connection connection=null;
	     try {
	      connection=Connection_provider.getconnection();
	      TreatmentEpisodeDAO treatmentEpisodeDAO=new JDBCTreatmentEpisode(connection);
	      String treatmentid=request.getParameter("treatmentepisode");
	      String advoice=treatmentEpisodeDAO.getDischargeAdvoice(treatmentid);
	      
	      response.setContentType("text/html");
	      response.setHeader("Cache-Control", "no-cache");
	      response.getWriter().write(""+advoice+"");
	      
	  } catch (Exception e) {
	   e.printStackTrace();
	  }finally{
			connection.close();
		}
	     
	     return null;
	}

public String getstdcharge() throws Exception {
		
		Connection connection=null;
	     try {
	      connection=Connection_provider.getconnection();
	      ClientDAO clientDAO=new JDBCClientDAO(connection);
	      AppointmentTypeDAO appointmentTypeDAO= new JDBCAppointmentTypeDAO(connection);
	      String wardid= request.getParameter("wardid");
	      String tpid= request.getParameter("tpid");
	      String clientId= request.getParameter("clientId");
	      String ipdid= request.getParameter("ipdid");
	      String payee= request.getParameter("payee");
	      
	      if(payee==null){
	    	  payee="Client";
	      }
	      if(payee.equals("")){
	    	  payee="Client";
	      }
	      Client client=clientDAO.getClientDetails(clientId);
	      String fullname= client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
	      AccountsDAO accountsDAO=new JDBCAccountsDAO(connection);
	      StringBuffer buffer=new StringBuffer();
	      buffer.append(fullname);
	      buffer.append("~");
	      
	      ArrayList<Master> chargeList= appointmentTypeDAO.getStdOnoffChargeList(wardid, tpid,payee);
	      
	      for(Master master: chargeList){
	    	  
	    	  boolean isSelected= appointmentTypeDAO.isStdChargeSelected(ipdid, master.getId());
	    	 Accounts accounts= accountsDAO.showonofftime(master.getId(), ipdid);
	    	 
	    	 String ondate=DateTimeUtils.isNull(accounts.getOndatetime());
	    	 if(ondate.contains(",")){
	    		 for(String on:ondate.split(",")){
	    			 ondate=on;
	    		 }
	    	 }
	    	 
	    	 String offdate=DateTimeUtils.isNull(accounts.getOffdatetime());
	    	 if(offdate.contains(",")){
	    		 for(String off:offdate.split(",")){
	    			 offdate=off;
	    		 }
	    	 }
	    	  /*if(isSelected){
	    		  buffer.append("<input type='checkbox' checked='checked'  class='scase' id='ch"+master.getId()+"' name='ch"+master.getId()+"' value='"+master.getId()+"'  />");
	    	  } else {
	    		  buffer.append("<input type='checkbox' class='scase' id='ch"+master.getId()+"' name='ch"+master.getId()+"' value='"+master.getId()+"'  />");
	    	  }
	    	  buffer.append(master.getName()+" <input type='button' class='btn updatebtn' value='Update' onclick='updateOnOffStd("+ipdid+","+master.getId()+")' /> <br>  ");
	    	*/
	    	buffer.append("<div class='form-group col-xs-12'>");
	    	buffer.append("<label class='col-xs-4 control-label'>"+master.getName()+"</label>");
	    	buffer.append("<div class='col-xs-1 control-label'>");
	    	buffer.append("<div class='onoffswitch greensea'>");
			if(isSelected){
	    		  buffer.append("<input type='checkbox' checked='checked' id='ch"+master.getId()+"' onclick='updateOnOffStd("+ipdid+","+master.getId()+")' name='ch"+master.getId()+"' value='"+master.getId()+"' class='onoffswitch-checkbox scase'>");
	    		  buffer.append("<label class='onoffswitch-label' for='ch"+master.getId()+"'>");
				  buffer.append("<span class='onoffswitch-inner'></span>");
				  buffer.append("<span class='onoffswitch-switch'></span>");
				  buffer.append("</label>");
				 
				  
			}else{
				  buffer.append("<input type='checkbox' id='ch"+master.getId()+"' onclick='updateOnOffStd("+ipdid+","+master.getId()+")' name='ch"+master.getId()+"' value='"+master.getId()+"' class='onoffswitch-checkbox scase'>");
	    		  buffer.append("<label class='onoffswitch-label' for='ch"+master.getId()+"'>");
				  buffer.append("<span class='onoffswitch-inner'></span>");
				  buffer.append("<span class='onoffswitch-switch'></span>");
				  buffer.append("</label>");
			}
			 
			buffer.append("</div>");
			buffer.append("</div>");
			
			buffer.append("<span class='col-xs-3 control-label'>"+ondate+"</span>");
			buffer.append("<span class='col-xs-3 control-label'>"+offdate+"</span>");
			
			
			buffer.append("<input type='button' class='btn updatebtn col-xs-1' value='Info' style='margin-top: 0px;' onclick='getOnoffTime("+ipdid+","+master.getId()+")'/>  ");
			buffer.append("</div>");
			
			
			/*buffer.append(master.getName()+" <input type='button' class='btn updatebtn' value='Update' onclick='updateOnOffStd("+ipdid+","+master.getId()+")' /> <br>  ");*/
			
	      }
	      
	    
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

public String updatestdcharge() throws Exception{
		
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			String clientid = request.getParameter("clientid");
			String ipdid=request.getParameter("ipdid");
			String standardcharges=request.getParameter("standardids");
			String unselected= request.getParameter("unselected");
			String stddate= request.getParameter("stddate");
			String starthour= request.getParameter("starthour");
			String startminute= request.getParameter("startminute");
			String enddate =request.getParameter("enddate");
			String endminute =request.getParameter("endminute");
			String endhour= request.getParameter("endhour");
			
			String startTime = starthour+":"+startminute+":"+"00";
			String endTime= endhour+":"+endminute+":"+"00";
			
			AppointmentTypeDAO appointmentTypeDAO= new JDBCAppointmentTypeDAO(connection);
			CompleteAptmDAO completeAptmDAO= new JDBCCompleteAptmDAO(connection);
			BedDao bedDao= new JDBCBedDao(connection);
			ClientDAO clientDAO= new JDBCClientDAO(connection);
			//delete unchecked 
			/*for(String temp: unselected.split(",")){
				if(temp.equals("0")){
					 continue;
				 }
				 int result= appointmentTypeDAO.deleteStdCharge(ipdid,temp);
			}*/
			
			//int result= appointmentTypeDAO.deleteStdCharge(ipdid,"");
			if(stddate!=null){
    			
    			if(stddate.equals("")){
    				 stddate= DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
    			} else {
    				stddate= DateTimeUtils.getCommencingDate1(stddate);
    			}
    		} else {
    			stddate= DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
    		}
			
			if(enddate!=null){
    			
    			if(enddate.equals("")){
    				enddate= DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
    			} else {
    				enddate= DateTimeUtils.getCommencingDate1(enddate);
    			}
    		} else {
    			enddate= DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
    		}
			
			
			String nowDate=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			
			long diff= DateTimeUtils.getDifferenceOfTwoDateDBFormat(stddate, nowDate);
			int qty =(int) diff;
			if(qty<0){
				 qty=0;
			}
			
			if(qty==0){
				qty=1;
			} else {
				qty++;
			}
			
			String stdcharges="001";
    		int invoiceid=0;
    		
    		String time= DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[1];
    		
    		String date1 = DateTimeUtils.getDateinSimpleFormate(new Date());
			String stemp[] = date1.split(" ");
			
			String temp[] = stemp[0].split("-");
			date1 = temp[2] + "-" + temp[1] + "-" + temp[0];
			int result=0;
			
			Bed bed= bedDao.getEditIpdData(ipdid);
			Client client= clientDAO.getClientDetails(clientid);

			//creating invoice
    			
    			CompleteAppointment appointment=new CompleteAppointment();
    			appointment.setClientId(clientid);
    			appointment.setPractitionerId(bed.getPractitionerid());
    			appointment.setChargeType("Charge");
    			appointment.setLocation("1");
    		    appointment.setAdditionalcharge_id(stdcharges);
    		    appointment.setIpdid(Integer.parseInt(ipdid));
    		    appointment.setInvoiceDate(date1);
    		    appointment.setIpd("1");
    		    appointment.setAppointmentid("0");
    		    appointment.setWardid(bed.getWardid());
    		    if(client.getWhopay()!=null){
    		    	
    		    	if(client.getWhopay().equals("Self") || client.getWhopay().equals("Client")){
    		    	       
    		    		appointment.setPolicyExcess("0");
    		    		appointment.setPayBuy("0");
    		    	} else {
    		    		appointment.setPolicyExcess("1");
    		    		appointment.setPayBuy("1");
    		    	}
    		    }
    		    invoiceid= completeAptmDAO.getInvoiceforStandardCharges(ipdid,stdcharges);
    		    if(invoiceid==0){
    		    	invoiceid=completeAptmDAO.saveAmpmInvoice(appointment,loginInfo.getId(),loginInfo.getUserId());
    		    }
    		    
    		    String fullname=client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
    		    appointment.setUser(fullname);
    		    appointment.setCommencing(date1);     
    		    String ondatetime= stddate+" "+startTime;
    		    String offdatetime= enddate+" "+endminute;  
    		    for(String chargeId: standardcharges.split(",")) {
    		    	  
    		    	 if(chargeId.equals("0")){
    		    	 	 continue;
   				 	  }
    		    	   Master master= appointmentTypeDAO.getMasterCharges(chargeId);
    		    	   appointment.setApmtType(master.getName());
    		    	   appointment.setCharges(master.getCharge());
    		    	   appointment.setAdditionalcharge_id(chargeId);
    		    	   //appointment.setMasterchargetype("Bed Charge");
    		    	   //  30-11-2019 kunal auto charges not applied
    		    	   if(loginInfo.getIskunal()!=1){
    		    		   appointment.setMasterchargetype("Bed Charge");
    				   }else{
    					   appointment.setMasterchargetype("Bed Charges");
    				   }
    		    	   appointment.setStartTime(time);
    		    	   appointment.setQuantity(qty);
    		    	   appointment.setBackDate(stddate);
    		    	   
    		    	   int assesmentid= completeAptmDAO.getAssesmentIdforStdChargeIfExits(ipdid,chargeId,invoiceid,loginInfo);
    		    	   if(assesmentid==0){
    		    		   assesmentid=completeAptmDAO.saveInvoiceAssesment(appointment, invoiceid);
    		    	   }
    		    	   result = appointmentTypeDAO.getStdChargeIdIdExists(ipdid,chargeId);
    		    	   if(result==0){
    		    		   result= appointmentTypeDAO.saveStdCharge(ipdid,chargeId,assesmentid,"1",ondatetime,"");
    		    	   } else {
    		    		   result= appointmentTypeDAO.updateStdCharge(result,"1",ondatetime,"");
    		    	   }
    		    	   
    		  }
    		    
    		    for(String str: unselected.split(",")){
					if(str.equals("0")){
						continue;
					}
				   int assesmentid= completeAptmDAO.getAssesmentIdforStdChargeIfExits(ipdid,str,invoiceid,loginInfo);	
				   result = appointmentTypeDAO.getStdChargeIdIdExists(ipdid,str);
 		    	   if(result==0){
 		    		 //  result= appointmentTypeDAO.saveStdCharge(ipdid,str,assesmentid,"",offdate,"0",startTime,endTime);
 		    	   } else {
 		    		  result= appointmentTypeDAO.updateStdCharge(result,"1","",offdatetime);
 		    		  
 		    	   }
    		    }
			  response.setContentType("text/html");
		      response.setHeader("Cache-Control", "no-cache");
		      response.getWriter().write("");
			
		} catch (Exception e) {

			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		return null;
	}
	
public String updatestdonoff()throws Exception{
	
	Connection connection=null;
	
	try {
		connection=Connection_provider.getconnection();
		
		AppointmentTypeDAO appointmentTypeDAO= new JDBCAppointmentTypeDAO(connection);
		AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
		CompleteAptmDAO completeAptmDAO= new JDBCCompleteAptmDAO(connection);
		BedDao bedDao= new JDBCBedDao(connection);
		ClientDAO clientDAO= new JDBCClientDAO(connection);
		
		String chargeid= request.getParameter("chargeid");
		String ipdid= request.getParameter("ipdid");
		String status= request.getParameter("status");
		String stddate= request.getParameter("stddate");
		String starthour= request.getParameter("starthour");
		String startminute= request.getParameter("startminute");
		String enddate =request.getParameter("enddate");
		String endminute =request.getParameter("endminute");
		String endhour= request.getParameter("endhour");
		boolean stdbackdatechk = Boolean.parseBoolean(request.getParameter("stdbackdatechk"));
		
		if(stdbackdatechk){
			int r = appointmentTypeDAO.getStdChargeIdIdExists(ipdid,chargeid);
			if(r!=0){
				String curstatus = appointmentTypeDAO.getstddbcurstatus(r);
				if(curstatus.equals("1")){
					 response.setContentType("text/html");
				     response.setHeader("Cache-Control", "no-cache");
				     response.getWriter().write("0");
					return null;
				}
			}
		}
		String startTime = starthour+":"+startminute+":"+"00";
		String endTime= endhour+":"+endminute+":"+"00";
		
		String ondatetime= stddate+" "+startTime;
		    String offdatetime= enddate+" "+endTime;
		
		
		
		if(stddate!=null){
			
			if(stddate.equals("")){
				 stddate= DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			} else {
				stddate= DateTimeUtils.getCommencingDate1(stddate);
			}
		} else {
			stddate= DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
		}
		
		if(enddate!=null){
			
			if(enddate.equals("")){
				enddate= DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			} else {
				enddate= DateTimeUtils.getCommencingDate1(enddate);
			}
		} else {
			enddate= DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
		}
		
		
		String nowDate=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
		
		long diff= DateTimeUtils.getDifferenceOfTwoDateDBFormat(stddate, nowDate);
		int qty =(int) diff;
		if(qty<0){
			 qty=0;
		}
		
		if(qty==0){
			qty=1;
		} else {
			qty++;
		}
		
		String stdcharges="001";
		int invoiceid=0;
		
		String time= DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[1];
		
		String date1 = DateTimeUtils.getDateinSimpleFormate(new Date());
		String stemp[] = date1.split(" ");
		
		String temp[] = stemp[0].split("-");
		date1 = temp[2] + "-" + temp[1] + "-" + temp[0];
		int result=0;
		
		Bed bed= bedDao.getEditIpdData(ipdid);
		String clientid = bed.getClientid();
		Client client= clientDAO.getClientDetails(clientid);

		//creating invoice
			
			CompleteAppointment appointment=new CompleteAppointment();
			appointment.setClientId(clientid);
			appointment.setPractitionerId(bed.getPractitionerid());
			appointment.setChargeType("Charge");
			appointment.setLocation("1");
		    appointment.setAdditionalcharge_id(stdcharges);
		    appointment.setIpdid(Integer.parseInt(ipdid));
		    appointment.setInvoiceDate(date1);
		    appointment.setIpd("1");
		    appointment.setAppointmentid("0");
		    appointment.setWardid(bed.getWardid());
		    if(client.getWhopay()!=null){
		    	
		    	if(client.getWhopay().equals("Self") || client.getWhopay().equals("Client")){
		    	       
		    		appointment.setPolicyExcess("0");
		    		appointment.setPayBuy("0");
		    	} else {
		    		appointment.setPolicyExcess("1");
		    		appointment.setPayBuy("1");
		    	}
		    }
		    invoiceid= completeAptmDAO.getInvoiceforStandardCharges(ipdid,stdcharges);
		    if(invoiceid==0){
		    	invoiceid=completeAptmDAO.saveAmpmInvoice(appointment,loginInfo.getId(),loginInfo.getUserId());
		    }
		    
		    String fullname=client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
		    appointment.setUser(fullname);
		    appointment.setCommencing(date1);     
		    
		    Master master= appointmentTypeDAO.getMasterCharges(chargeid);
	    	   appointment.setApmtType(master.getName());
	    	   appointment.setCharges(master.getCharge());
	    	   appointment.setAdditionalcharge_id(chargeid);
	    	   appointment.setMasterchargetype(master.getMasterchargetype());
	    	   appointment.setStartTime(time);
	    	   
	    	   qty = DateTimeUtils.getDifferanceofDateWithTime(ondatetime, offdatetime, master.getChargehours());
	    	   if(qty==0){
	   			qty=1;
	    	   }
	    	   appointment.setQuantity(qty);
	    	   appointment.setBackDate(stddate);
	    	   appointment.setStdflag("1");
	    	   
	    	   result = appointmentTypeDAO.getStdChargeIdIdExists(ipdid,chargeid);
	    	   if(status.equals("0")){
	    		   if(result==0){
	    			   
		    	   } else {
		    		   if(!stdbackdatechk){
		    			   
		    			 /*  Accounts accounts = appointmentTypeDAO.getStdChargeDetails(result);
		    			   String temps[] = accounts.getOndatetime().split(",");
		    			   String lastdate = temps[temps.length-1];
		    			   String temp1[] = lastdate.split(" ");
		    			   diff= DateTimeUtils.getDifferenceOfTwoDateDBFormat(DateTimeUtils.getCommencingDate1(temp1[0]), enddate);
		    			   qty =(int) diff;
		    			   
		    				int assesmentid = appointmentTypeDAO.getstdAssesmentid(result);
		    				int dbqty = appointmentTypeDAO.getDbQuantity(assesmentid);
		    				 qty = qty + dbqty;
		    			     int upd = accountsDAO.updateChargeqty(Integer.toString(assesmentid),Integer.toString(qty));*/
		    			     
		    			     result= appointmentTypeDAO.updateStdCharge(result,"0","",offdatetime);
		    			   
		    		   }else{
		    			   String curstatus = appointmentTypeDAO.getstddbcurstatus(result);
		    			   if(curstatus.equals("0")){
		    				   Accounts accounts = appointmentTypeDAO.getStdChargeDetails(result);
		    				   offdatetime = accounts.getOffdatetime() + " , " + offdatetime;
		    				   ondatetime = accounts.getOndatetime() + " , " + ondatetime;
		    				   int upd = appointmentTypeDAO.updateStdChargeDateTime(result, ondatetime, offdatetime);
		    			   }
		    		   }
		    		  
		    	   }
	    		   
	    	   }else {
	    		   
	    		   int assesmentid= completeAptmDAO.getAssesmentIdforStdChargeIfExits1(ipdid,chargeid,invoiceid,master.getMasterchargetype());
		    	   if(assesmentid==0){
		    		   if(stdbackdatechk){
		    			     /*diff= DateTimeUtils.getDifferenceOfTwoDateDBFormat(stddate, enddate);
		    				 qty =(int) diff;*/
		    			     qty = DateTimeUtils.getDifferanceofDateWithTime(ondatetime, offdatetime, master.getChargehours());
		    				 appointment.setQuantity(qty);
		    				 
		    		   }
		    		   assesmentid=completeAptmDAO.saveInvoiceAssesment(appointment, invoiceid);
		    	   }
		    	   if(result==0){
		    		   if(!stdbackdatechk){
		    			   result= appointmentTypeDAO.saveStdCharge(ipdid,chargeid,assesmentid,"1",ondatetime,"");
		    		   }else{
		    			   result= appointmentTypeDAO.saveStdCharge(ipdid,chargeid,assesmentid,"0",ondatetime,offdatetime);
		    			   
		    			  /* diff= DateTimeUtils.getDifferenceOfTwoDateDBFormat(stddate, enddate);
		    				 qty =(int) diff;*/
		    			   qty = DateTimeUtils.getDifferanceofDateWithTime(ondatetime, offdatetime, master.getChargehours());
		    				 assesmentid = appointmentTypeDAO.getstdAssesmentid(result);
		    			     int upd = accountsDAO.updateChargeqty(Integer.toString(assesmentid),Integer.toString(qty));
		    		   }
		    		   
		    	   } else {
		    		   
		    		   if(stdbackdatechk){
		    			   String curstatus = appointmentTypeDAO.getstddbcurstatus(result);
		    			   if(curstatus.equals("0")){
		    				     /*diff= DateTimeUtils.getDifferenceOfTwoDateDBFormat(stddate, enddate);
			    				 qty =(int) diff;*/
		    				   qty = DateTimeUtils.getDifferanceofDateWithTime(ondatetime, offdatetime, master.getChargehours());
			    				 assesmentid = appointmentTypeDAO.getstdAssesmentid(result);
			    				 int dbqty = appointmentTypeDAO.getDbQuantity(assesmentid);
			    				 qty = qty + dbqty;
			    				 int upd = accountsDAO.updateChargeqty(Integer.toString(assesmentid),Integer.toString(qty));
		    			   }
		    			    
		    				 
		    		   }
		    		   if(!stdbackdatechk){
		    			   String curstatus = appointmentTypeDAO.getstddbcurstatus(result);
		    			   if(curstatus.equals("0")){
		    				   qty = DateTimeUtils.getDifferanceofDateWithTime(ondatetime, offdatetime, master.getChargehours());
			    			   int dbqty = appointmentTypeDAO.getDbQuantity(assesmentid);
			    				 qty = qty + dbqty;
			    			   int upd = accountsDAO.updateChargeqty(Integer.toString(assesmentid),Integer.toString(qty));
			    			   result= appointmentTypeDAO.updateStdCharge(result,"1",ondatetime,"");
		    			   }
		    		   }else{
		    			   String curstatus = appointmentTypeDAO.getstddbcurstatus(result);
		    			   if(curstatus.equals("0")){
		    				   Accounts accounts = appointmentTypeDAO.getStdChargeDetails(result);
		    				   offdatetime = accounts.getOffdatetime() + " , " + offdatetime;
		    				   ondatetime = accounts.getOndatetime() + " , " + ondatetime;
		    				   int upd = appointmentTypeDAO.updateStdChargeDateTime(result, ondatetime, offdatetime);
		    			   }
		    		   }
		    		   
		    		   
		    		   
		    	   }
	    		   
	    	   }
		
		
		
    	 response.setContentType("text/html");
	     response.setHeader("Cache-Control", "no-cache");
	     response.getWriter().write("1");
		
		
	} catch (Exception e) {

		e.printStackTrace();
	}finally{
		connection.close();
	}
	
	
	return null;
}
	
	
public String ipdformdata() throws Exception {
	
	
	if(!verifyLogin(request)) {
		
		return "login";
	}
	
	String wardid = request.getParameter("wardid");
	String bedid = request.getParameter("bedid");
	String action = request.getParameter("action");
	
	Connection connection = null;
	try{
		connection = Connection_provider.getconnection();
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
		MasterDAO masterDAO=new JDBCMasterDAO(connection);
		ClientDAO clientDAO=new JDBCClientDAO(connection);
		BedDao bedDao = new JDBCBedDao(connection);
		
		
		ArrayList<Bed> wardlist=bedDao.getAllWardList(action);
		ipdForm.setWardlist(wardlist);
		
		
		ArrayList<Bed>bedList = ipdDAO.getWardWiseBedList(wardid);
		String clientid= ipdForm.getClientid();
		Client client=clientDAO.getClientDetails(clientid);
		
		String practitionerid= ipdForm.getPractitionerid();
		ipdForm.setBedlist(bedList);
		ipdForm.setClientid(clientid);
		ipdForm.setWardid(wardid);
		ipdForm.setBedid(bedid);
		ipdForm.setTpid(client.getTypeName());
		ipdForm.setPractitionerid(practitionerid);
		
		String chief_comlint_id=masterDAO.getIpdTemplateId("Chief Complaints");
		String present_ill_id=masterDAO.getIpdTemplateId("Present Illness");
		String past_history_id=masterDAO.getIpdTemplateId("Past History");
		String family_hist_id=masterDAO.getIpdTemplateId("Family History");
		String personal_hist_id=masterDAO.getIpdTemplateId("Personal History");
		String onexami_id=masterDAO.getIpdTemplateId("On Examination");
		String tretment_given_id=masterDAO.getIpdTemplateId("Treatment Given");
		
		UserProfile userProfile=  userProfileDAO.getUserprofileDetails(Integer.parseInt(practitionerid));
		String specializationId=  userProfile.getDiciplineName();
		ipdForm.setDepartment(userProfile.getSpecialization());
		
		boolean issystemicreview= masterDAO.isIpdFormFieldActive(specializationId,"Systemic Review");
		boolean history= masterDAO.isIpdFormFieldActive(specializationId,"History");
		boolean obstretic_history = masterDAO.isIpdFormFieldActive(specializationId,"OBSTRETIC HISTORY");
		boolean menstrual_history = masterDAO.isIpdFormFieldActive(specializationId, "MENSTRUAL HISTORY"); 
		boolean substance_history = masterDAO.isIpdFormFieldActive(specializationId, "SUBSTANCE HISTORY");
		
		ipdForm.setIssystemicreview(issystemicreview);
		ipdForm.setHistory(history);
		ipdForm.setObstretic_history(obstretic_history);
		ipdForm.setMenstrual_history(menstrual_history);
		ipdForm.setSubstance_history(substance_history);
		
		
		ArrayList<Master> chief_complaints_list=masterDAO.getIpdTemplateListNames(chief_comlint_id);
		ArrayList<Master> present_illness_list=masterDAO.getIpdTemplateListNames(present_ill_id);
		ArrayList<Master> past_history_list=masterDAO.getIpdTemplateListNames(past_history_id);
		ArrayList<Master> family_history_list=masterDAO.getIpdTemplateListNames(family_hist_id);
		ArrayList<Master> personal_hist_list=masterDAO.getIpdTemplateListNames(personal_hist_id);
		ArrayList<Master> on_exam_list=masterDAO.getIpdTemplateListNames(onexami_id);
		ArrayList<Master> treatment_given_list=masterDAO.getIpdTemplateListNames(tretment_given_id);
		ipdForm.setChief_complaints_list(chief_complaints_list);
		ipdForm.setPresent_illness_list(present_illness_list);
		ipdForm.setPast_history_list(past_history_list);
		ipdForm.setFamily_history_list(family_history_list);
		ipdForm.setPersonal_hist_list(personal_hist_list);
		ipdForm.setOn_exam_list(on_exam_list);
		ipdForm.setTreatment_given_list(treatment_given_list);
		
		int selectedid = loginInfo.getId();
		ClinicDAO clinicListDAO = new JDBCClinicDAO(connection);
		com.apm.Registration.eu.entity.Clinic cliniclist = clinicListDAO.getCliniclistDetails(selectedid);
		ipdForm.setClinicName(cliniclist.getClinicName());
		ipdForm.setActiontype(action);
		if(action.equals("1")||action.equals("2")){
			return "addipdcasualty";
		}
		
	}catch(Exception e){
		e.printStackTrace();
	}
	finally {
		connection.close();
	}
	
	return INPUT;
	
}
	
	
	public String savecondition() throws Exception {
		
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			String condition= request.getParameter("condition");
			String icdcode= request.getParameter("icdcode");
			String practitionerid= request.getParameter("practitionerid");
			DiagnosisDAO  diagnosisDAO =new JDBCDiagnosisDAO(connection);
			UserProfileDAO userProfileDAO= new JDBCUserProfileDAO(connection); 
			ClientDAO clientDAO=new JDBCClientDAO(connection);
			UserProfile userProfile= userProfileDAO.getUserprofileDetails(Integer.parseInt(practitionerid));
			Diagnosis diagnosis=new Diagnosis();
			diagnosis.setName(condition);
			diagnosis.setDepartment(userProfile.getDiciplineName());
			diagnosis.setIcdcode(icdcode);
			int d=diagnosisDAO.addDiagnosisName(diagnosis);
			ArrayList<Client> list= clientDAO.getEmrTreatmentTypeList();

			StringBuffer buffer= new StringBuffer();
			for(Client diagn: list) {
				
				if(d==diagn.getId()){
					buffer.append("<option value='"+diagn.getId()+"' >"+diagn.getTreatmentType()+"</option>");
				}else {
					buffer.append("<option value='"+diagn.getId()+"'>"+diagn.getTreatmentType()+"</option>");
				}
				  
			}
			
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
public String savediagnosisfast() throws Exception {
		
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			String condition= request.getParameter("condition");
			String icdcode= "0";
			DiagnosisDAO  diagnosisDAO =new JDBCDiagnosisDAO(connection);
			Diagnosis diagnosis=new Diagnosis();
			diagnosis.setName(condition);
			diagnosis.setIcdcode(icdcode);
			int d=diagnosisDAO.addDiagnosisName(diagnosis);
			
			 response.setContentType("text/html");
		     response.setHeader("Cache-Control", "no-cache");
		     response.getWriter().write(""+d+"");
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		return null;
	}
	
	
	public String addnewobsrow() throws Exception{
		
		try {
			String rowcount =request.getParameter("rowcount");
			String type=request.getParameter("type");
			
			StringBuffer buffer=new StringBuffer();
			buffer.append("<td style='width: 5%;padding-right: 15px;'><input type='number' name='' value='"+rowcount+"' class='form-control'></td>");
			buffer.append("<td style='width: 8%;padding-right: 15px;'><input type='number' name='' value='' class='form-control' placeholder='year'></td>");
			buffer.append("<td style='width: 7%;padding-right: 15px;'>"+type+"</td>");
			buffer.append("<td style='width: 13%;padding-right: 15px;'>");
			buffer.append("<select name='' class='form-control'>");
			  buffer.append("<option value='0'>Type of Delivery</option>");
			  buffer.append("<option value='Normal'>Normal</option>");
			  buffer.append("<option value='Vaccume'>Vaccume</option>");
			  buffer.append("<option value='Forceps'>Forceps</option>");
			  buffer.append("<option value='LSCS'>LSCS</option>");
		    buffer.append("</select>");	  
			buffer.append("</td>");
			
			buffer.append("<td style='width: 20%;padding-right: 15px;'><input type='text' name='' value='' class='form-control' placeholder='Indications'></td>");
		    buffer.append("<td style='width: 20%;padding-right: 15px;'><input type='text' name='' value='' class='form-control' placeholder='Coamplications'></td>");
			buffer.append("<td style='width: 20%;padding-right: 15px;'><input type='text' name='' value='' class='form-control' placeholder='Institution'></td>");
			
			response.setContentType("text/html");
		    response.setHeader("Cache-Control", "no-cache");
		    response.getWriter().write(buffer.toString());
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		
	
		return null;
	}
	
	
	public String addMedicinePriscription() throws Exception{
		  Connection connection = null;
		  try {
		   connection = Connection_provider.getconnection();
		   IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		   StringBuffer buffer = new StringBuffer();
		   
		   String product = request.getParameter("product");
		   String[] temp = product.split(",");
		   ArrayList<Priscription> arrayList = new ArrayList<Priscription>();
		   
		   for(int i = 0; i < temp.length; i++) {
		    Priscription priscription = ipdDAO.getMNameFromId(temp[i]);
		    String id = temp[i];
		    if(temp[i]=="0" || temp[i].equals("0")){
		    }else{
		     arrayList.add(priscription);
		    }
		   }
		   
		   for (Priscription priscription : arrayList) {
		    buffer.append(""+priscription.getName()+"<br>");
		   }
		   
		   
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
	
	
public String canceldischarge()  throws Exception{
		
		Connection connection=null;
		String result="0";
		try {
			      connection=Connection_provider.getconnection();
			      IpdLogDAO ipdLogDAO= new JDBCIpdLogDAO(connection);
			      IpdDAO ipdDAO =new JDBCIpdDAO(connection);
			      String ipdid= request.getParameter("ipdid");
			      String wardid= request.getParameter("wardid");
			      String selectedbedid= request.getParameter("bedid");
			      String treatmentepisodeid= request.getParameter("treatmentepisodeid"); 
			      String admitnotes= request.getParameter("admitnotes");
			      
			      String bedid =ipdLogDAO.getDischargeBedId(ipdid);	
			      boolean isoccupy = ipdDAO.checkBedStatus(Integer.parseInt(bedid));
			      String clientid=ipdDAO.getClientIDFromIPDID(ipdid);
			      boolean alreadyadmit=ipdDAO.checkAlreadyAdmit(clientid);
			      if(isoccupy!=true && alreadyadmit!=true){
			      if(Integer.parseInt(wardid)!=0 && Integer.parseInt(selectedbedid)!=0){
			    	  int res= ipdDAO.updateDischargeBedReadmit(selectedbedid,wardid,ipdid);
			    	  res= ipdDAO.updateDischargeDateandStatus(treatmentepisodeid,admitnotes);
			    	  
			    	  result="1";
			      } else if(isoccupy){
			    	  //yes occupied bed  
			    	  // show popup to shift 
			    	  result="0";
			      }else {
			    	  // admitt in same bed
			    	  
			    	  int res= ipdDAO.updateDischargeBed(bedid,ipdid);
			    	  res= ipdDAO.updateDischargeDateandStatus(treatmentepisodeid,admitnotes);
			    	  
			    	  result="1";
			      }
			      }else if (alreadyadmit) {
			    	  result="2";
				}else if(isoccupy){
			    	  //yes occupied bed  
			    	  // show popup to shift 
			    	  //result="0";  //@rahul commented only result commented baki alredy commented
					
					  int res= ipdDAO.updateDischargeBedReadmit(selectedbedid,wardid,ipdid);
			    	  res= ipdDAO.updateDischargeDateandStatus(treatmentepisodeid,admitnotes);
			    	  
			    	  result="1";
			      }
			      
			      
			     
			      
			       
			      response.setContentType("text/html");
			      response.setHeader("Cache-Control", "no-cache");
			      response.getWriter().write(result);
			
		} catch (Exception e) {

			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		return null;
	}
		
	public String vitalstatistics()throws Exception{
		String ipdclientid = request.getParameter("clientid");
		String ipdid = request.getParameter("ipdid");
		if(ipdclientid==null){
			ipdclientid= (String) session.getAttribute("vitalclientid");
		}
		if(ipdid==null){
			ipdid= (String) session.getAttribute("vitalipdid"); 
		}
		String date= ipdForm.getDate();
		
		Connection connection = null;
		try{
			connection=Connection_provider.getconnection();
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			
			Client client = clientDAO.getClientDetails(ipdclientid);
			
			String curdate = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			if(date==null){
				date= curdate;
			} else {
				date =DateTimeUtils.getCommencingDate1(date);
			}
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			BedDao bedDao=new JDBCBedDao(connection);
			Bed bed = bedDao.getEditIpdData(ipdid);
			
			String wardname=ipdDAO.getIpdWardName(bed.getWardid());
            String bedname=ipdDAO.getIpdBedName(bed.getBedid());
            
            String fullname = client.getTitle() + " " + client.getFirstName() + " "+client.getMiddlename()+" " + client.getLastName();
            
            ipdForm.setClientid(ipdclientid);
            ipdForm.setIpdid(ipdid);
            ipdForm.setClient(fullname);
            ipdForm.setAdmissiondate(DateTimeUtils.getDBDate(bed.getAdmissiondate()));
            ipdForm.setAbrivationid(client.getAbrivationid());
            String age = DateTimeUtils.getAge(client.getDob());
    		String agegender="";
    		if(Integer.parseInt(age)<2){

				String monthdays= DateTimeUtils.getMonthandDays(client.getDob());
				agegender=monthdays+" / "+client.getGender();
			} else {
				agegender = age + "Years" + " / " + client.getGender();	
			}
    		
    		ipdForm.setAgegender(agegender);
    		ipdForm.setWardname(wardname + " / " + bedname);
    		ipdForm.setDate(DateTimeUtils.getCommencingDate1(date));
    		
    		String time = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[1];
    		ipdForm.setCurdatetime(DateTimeUtils.getCommencingDate1(curdate) + " " + time); 
    		
            ArrayList<String> timeList= ipdDAO.getDailyCareTimeList(ipdid, ipdclientid, date, "1");
    		//ArrayList<Master> vitalMasterList= ipdDAO.getVitalMasterList();
    		ArrayList<Master> vitalMasterandValueList= ipdDAO.getDailyCareDataListandValues(ipdclientid, ipdid, date, "1");
			ipdForm.setVitalMasterList(vitalMasterandValueList);
			ipdForm.setTimeList(timeList);

			
			//get clinic address header
	    	ClinicDAO clinicDAO= new JDBCClinicDAO(connection);
			Clinic clinic = clinicDAO.getCliniclistDetails(loginInfo.getClinicid());
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			
			ArrayList<Clinic> locationAdressList = accountsDAO.getLocationAddress(loginInfo.getClinicid());
			//String address = accountsDAO.getLocationAddress(locationid,loginInfo.getId());
			
			ipdForm.setClinicName(clinic.getClinicName());
			ipdForm.setClinicOwner(clinic.getClinicOwner());
			ipdForm.setOwner_qualification(clinic.getOwner_qualification());
			ipdForm.setLandLine(clinic.getLandLine());
			ipdForm.setWebsiteUrl(clinic.getWebsiteUrl());
			ipdForm.setClinicemail(clinic.getEmail());
			ipdForm.setLocationAdressList(locationAdressList);

			
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
		return "vitalstatistics";
	}
	
	
	//@ jitu save or update khi vitals data
	public String savevitals() throws Exception {
		
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			IpdDAO ipdDAO= new JDBCIpdDAO(connection);
			ArrayList<Master> vitalMasterList= ipdDAO.getVitalMasterList();
			String clientid= ipdForm.getClientid();
			String ipdid= ipdForm.getIpdid();
			session.setAttribute("vitalclientid", clientid);
			session.setAttribute("vitalipdid", ipdid);
			
			String dateTime= DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
			String date=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			Master pmaster= new Master();
			pmaster.setClientid(clientid);
			pmaster.setIpdid(ipdid);
			pmaster.setDate(date);
			pmaster.setDatetime(dateTime);
			
			boolean isDateExist= ipdDAO.isVitalDateIsExist(clientid,ipdid,date);
			
			if(isDateExist){
				 //update
				for(Master master: vitalMasterList){
					   
					   pmaster.setVital_master_id(String.valueOf(master.getId()));
					   ArrayList<String> list = ipdDAO.getVitalClientTimeList(ipdid, clientid, date);
					   for(String temp: list){
						    	
						   String name =master.getId()+"t"+temp;
						  // String time=temp+":00";
						   String value=request.getParameter(name);
						 
						   int id= ipdDAO.getVitalClientDataId(pmaster,temp);
						   if(value==null){
							   value="0";
						   }
						   if(value.equals("")){
							   value="0";
						   }
						   if(id==0) {
							   int res= ipdDAO.saveClientVitalData(pmaster,value,temp);
						   } else {
							   int res= ipdDAO.updateClientVitalData(value,id,dateTime);
						   }
						   
						   
					   }
					
				}
				
			} else {  
				  //insert
				for(Master master: vitalMasterList){
					   
					   pmaster.setVital_master_id(String.valueOf(master.getId()));
					   ArrayList<String> list = ipdDAO.getVitalClientTimeList(ipdid, clientid, date);
					   for(String temp: list){
						    	
						   String name =master.getId()+"t"+temp;
						   String value=request.getParameter(name);
						 
						   if(value==null){
							   value="0";
						   }
						   if(value.equals("")){
							   value="0";
						   }
						   int res= ipdDAO.saveClientVitalData(pmaster,value,temp);
					   }
					
				}
			}
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		return vitalstatistics();
		
	}
	
	public String updatedischargecheckliststatus() throws Exception{
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			IpdDAO ipdDAO =new JDBCIpdDAO(connection);
			String disdataid= request.getParameter("disdataid");
			String ische = request.getParameter("ische");
			String userid =  loginInfo.getUserId();
			SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar calendar=Calendar.getInstance();
			String todate=dateFormat.format(calendar.getTime());
			String result ="0";
			boolean flag = ipdDAO.isDisCheckListAlreadyPresent(disdataid);
			if(flag){
				if(ische.equals("true")){
					int res = ipdDAO.updateCheckListStatusById(disdataid,userid, todate,"1");
					result="1";
				}else{
					int res = ipdDAO.updateCheckListStatusModicfy(disdataid,userid, todate,"0",ische);
					result="0";
				}
			}else{
				if(ische.equals("true")){
					int res = ipdDAO.updateCheckListStatusModicfy(disdataid,userid, todate,"1",ische);
					result="1";
				}else{
					int res = ipdDAO.updateCheckListStatusModicfy(disdataid,userid, todate,"0",ische);
					result="0";
				}
			}
			String str =  disdataid +"~"+ result;
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+str+"");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
		return null;
	}
	
	
	
	
	public String checkinvoice() throws Exception {
		
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			String ipdid = request.getParameter("ipdid");
			String clientid= request.getParameter("clientid");
			IpdDAO ipdDAO= new JDBCIpdDAO(connection);
			
			boolean hasInvoice= ipdDAO.checkHasInvoiceCreated(ipdid,clientid);     
			String str="0";
			if(hasInvoice){
				str="1";
			}
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(str);
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		return null;
		
	}
	
	public String cancelipd() throws Exception {
		
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			String ipdid= request.getParameter("ipdid");
			String clientid= request.getParameter("clientid");
			String cancelnotes= request.getParameter("cancelnotes");
			IpdDAO ipdDAO =new JDBCIpdDAO(connection);
			
			//delete invoice
			int res=ipdDAO.cancelInvoice(ipdid,clientid);
			
			//update bedid
			
			res=ipdDAO.cancelBedStatus(ipdid,cancelnotes,loginInfo.getUserId());
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}	
		finally {
			connection.close();
		}
		
		return null;
	}
	
public String getdiagnosis() throws Exception {
		
		Connection connection=null;
		try {
			connection = Connection_provider.getconnection();
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			String search = request.getParameter("search");
			String selected= request.getParameter("selected");
			ArrayList<Client> ipdCondtitionList = ipdDAO.getAllDiagnosis(search);
			StringBuilder builder = new StringBuilder();
			for (Client client : ipdCondtitionList) {
				
				boolean flag=false;
				
				for(String s:selected.split(",")){

					     if(s==null){
					    	 continue;
					     }
					
					    int d=Integer.parseInt(s);
					    if(d==0){
					    	continue;
					    }
					    if(d==client.getId()){
					    	flag=true;
					    	break;
					    }
				}
				
				if(flag){
					
					builder.append("<tr>");
					builder.append("<td><input type='checkbox' checked='checked' onclick='setCheckedData(this)' value='"+client.getId()+"'  /></td>");
					builder.append("<td>"+client.getTreatmentType()+"</td>");
					builder.append("</tr>");
					
				} else {
					
					builder.append("<tr>");
					builder.append("<td><input type='checkbox' onclick='setCheckedData(this)' value='"+client.getId()+"'  /></td>");
					builder.append("<td>"+client.getTreatmentType()+"</td>");
					builder.append("</tr>");
					
				}
				
				
				
			}
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(builder.toString());
		} catch (Exception e) {

			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		return null;
		
	}
	

    public String setalldiagnosis() throws Exception {
    	
    	Connection connection=null;
    	try {
    		connection=Connection_provider.getconnection();
    		DiagnosisDAO diagnosisDAO=new JDBCDiagnosisDAO(connection);
    		StringBuffer buffer=new StringBuffer();
    		String selected= request.getParameter("selected");
			
    		int i=0;
    		for(String s:selected.split(",")){
    			
    			   int d=Integer.parseInt(s);
    			   if(d==0){
    				    continue;
    			   } else {
    				   	
    				    Diagnosis diagnosis=diagnosisDAO.getDiagnosisName(s);
    				    buffer.append("<tr>");
    				    buffer.append("<td><input type='checkbox' checked='checked' onclick=reoveThisRow(this,'"+diagnosis.getId()+"') value='"+diagnosis.getId()+"' class='classD' /> <input type='hidden' value="+diagnosis.getId()+" name='conditions["+i+"].conditionid'  /> </td>");
    					buffer.append("<td>"+diagnosis.getName()+"</td>");
    					buffer.append("<td class='hidden'><a onclick=reoveThisRow('"+diagnosis.getId()+"')><i class='fa fa-trash-o'></i></a></td>");
    					buffer.append("</tr>");
    				   i++;
    			   }
    		
    			    
    		}
    		
    		
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
    
    
    public String setalldiagnosisgynic() throws Exception {
    	
    	Connection connection=null;
    	try {
    		connection=Connection_provider.getconnection();
    		DiagnosisDAO diagnosisDAO=new JDBCDiagnosisDAO(connection);
    		StringBuffer builder=new StringBuffer();
    		String selected= request.getParameter("selected");
    		
    		int i=0;
    		for(String s:selected.split(",")){
    			
    			   int d=Integer.parseInt(s);
    			   if(d==0){
    				    continue;
    			   } else {
    				   	
    				    Diagnosis diagnosis=diagnosisDAO.getDiagnosisName(s);
    				    builder.append("<tr><td>");
    					builder.append("<input class='concase' checked='checked'  type='checkbox' onclick='showopdcontxtoneditornew("+d+")'  id='chh"+d+" name='ch"+d+"' value='"+d+"' /> ");
    					builder.append("<span id='ccck"+d+"' >"+diagnosis.getName()+"</span><br>");
    					builder.append("</td></tr>");
    				   i++;
    			   }
    			    
    		}
    		
    		
        	response.setContentType("text/html");
    		response.setHeader("Cache-Control", "no-cache");
    		response.getWriter().write(builder.toString());
    	} catch (Exception e) {

    		e.printStackTrace();
    	}
    	finally {
    		connection.close();
    	}
    	
    	return null;
    }

    
    

public String patientlabel()throws Exception{
		
		if(!verifyLogin(request)){
			return "login";
		}
		
		Connection connection = null;
		String selectedid = request.getParameter("selectedid");
		String labelipdid= request.getParameter("labelipdid");
		
		try{
			connection = Connection_provider.getconnection();
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			BedDao bedDao = new JDBCBedDao(connection);
			NotAvailableSlotDAO notAvailableSlotDAO = new JDBCNotAvailableSlotDAO(connection);
			UserProfileDAO profileDAO = new JDBCUserProfileDAO(connection);
			/*Code39 code128 = new Code39();
			code128.setBarcodeWidth(3);*/
			ipdForm.setLogipdid(labelipdid); 
			
			String howmany= request.getParameter("howmany");
			session.setAttribute("howmany", howmany);
			if(howmany!=null){
				 if(howmany.equals("")){
					  howmany="0";
				 }
			} else {
				howmany="0";
			}
			
			
			Client client = clientDAO.getClientDetails(selectedid);
			ArrayList<Client>totalBarcodeList = new ArrayList<Client>();
			String clientname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();
			int ipdid = ipdDAO.getLastIpdId(String.valueOf(client.getId()));
			Bed bed = new Bed();
			UserProfile userProfile = new UserProfile();
			int apptid =0;
			String practiname = "";
			String wardname="";
			String bedname ="";
			String admissiondate ="";
			String weight="";
			if(ipdid>0){
				//by lokesh for print label from log at any ipdid of same patient
				if(labelipdid!=null){
					if(!labelipdid.equals("")){
						bed = bedDao.getEditIpdData(String.valueOf(labelipdid));
					}else{
						bed = bedDao.getEditIpdData(String.valueOf(ipdid));
					}
				}else{
				bed = bedDao.getEditIpdData(String.valueOf(ipdid));
				}
				userProfile= profileDAO.getUserprofileDetails(Integer.parseInt(bed.getPractitionerid()));
				practiname=userProfile.getFullname();
				wardname = ipdDAO.getIpdWardName(bed.getWardid());
				bedname = ipdDAO.getIpdBedName(bed.getBedid());
				String tempdate[] = bed.getAdmissiondate().split(" ");
				admissiondate = DateTimeUtils.getCommencingDate1(tempdate[0])+" "+tempdate[1];
			}else{
				apptid = notAvailableSlotDAO.getLastAppointmentId(String.valueOf(client.getId()));
				if(apptid>0){
					practiname=notAvailableSlotDAO.getDrNameFromApptId(apptid);
				}
			
			}
			
			
			if(howmany.equals("0")){
					
				for(int i=1;i<=1;i++){
					
					client.setClientName(clientname);
					/* code128.setData(selectedid);
					 String filePath = request.getRealPath("/barcode/"+client.getId()+i+".gif");
					 code128.drawBarcode(filePath);*/
					 client.setImageName(client.getId()+""+i+""+".gif");
					 client.setCompanyName(loginInfo.getClinicName());
					 
					 if(client.getWhopay()==null){
						  
						 client.setWhopay("Self");
					 }
					 if(client.getWhopay().equals(Constants.PAY_BY_CLIENT) || client.getWhopay().equals("Self")){
						 client.setWhopay("Self");
					 }else{
						 ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
						 ThirdParty thirdParty = thirdPartyDAO.getThirdPartyDetails(client.getTypeName());
						 String tpname = thirdParty.getShortname();
						 if(tpname==null){
							 tpname=thirdParty.getCompanyName();
						 }
						 client.setTpName(tpname);
						 client.setWhopay(tpname);
					 }
					 
					 if(client.getGender().equals("Male") || client.getGender().equals("M")){
						 client.setGender("M");
					 }else if(client.getGender().equals("Female") || client.getGender().equals("F")){
						 client.setGender("F");
					 }else{
						 client.setGender("O");
					 }
					 String newipdabbr="";
					 if(loginInfo.getIpd_abbr_access()==1){
						 if(labelipdid!=null){
						   newipdabbr=ipdDAO.getIpdAbrivationIds(Integer.parseInt((labelipdid)));
						 }else{
							  newipdabbr=ipdDAO.getIpdAbrivationIds((ipdid));
						 }
						  client.setIpdid(newipdabbr);
					}else{
					client.setIpdid(bed.getIpdseqno());
					}
					 client.setDiaryUser(practiname);
					 client.setWardname(wardname);
					 client.setBedname(bedname);
					 client.setAdmissiondate(admissiondate);
					 String opdweight=clientDAO.getClientWeight(String.valueOf(client.getId()));
					 if(opdweight==null){
						 opdweight="";
					 }
					 if(bed.getWtaddmission()==null){
					 bed.setWtaddmission("");
					 }
					if(!bed.getWtaddmission().equals("")){
							 client.setWeight(bed.getWtaddmission());
					}else {
						if(!opdweight.equals("")&&!opdweight.equals("0")){
							client.setWeight(opdweight);
						}else{
							client.setWeight("");
						}
					}
					 
					totalBarcodeList.add(client);
				}
				
			} else {
				
				if(loginInfo.isTotehosp()) {
					for(int i=1;i<=5;i++){
						
						client.setClientName(clientname);
						/* code128.setData(selectedid);
						 String filePath = request.getRealPath("/barcode/"+client.getId()+i+".gif");
						 code128.drawBarcode(filePath);*/
						 client.setImageName(client.getId()+""+i+""+".gif");
						 client.setCompanyName(loginInfo.getClinicName());
					    
						 if(client.getWhopay()==null){
							  
							 client.setWhopay("Self");
						 }
					 	  
						 if(client.getWhopay().equals(Constants.PAY_BY_CLIENT) || client.getWhopay().equals("Self")){
							 client.setWhopay("Self");
						 }else{
							 ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
							 ThirdParty thirdParty = thirdPartyDAO.getThirdPartyDetails(client.getTypeName());
							 String tpname = thirdParty.getShortname();
							 if(tpname==null){
								 tpname=thirdParty.getCompanyName();
							 }
							 client.setTpName(tpname);
							 client.setWhopay(tpname);
						 }
						 
						 if(client.getGender()==null){
							 client.setGender("M");
						 }
						 
						 if(client.getGender().equals("Male") || client.getGender().equals("M")){
							 client.setGender("M");
						 }else if(client.getGender().equals("Female") || client.getGender().equals("F")){
							 client.setGender("F");
						 }else{
							 client.setGender("O");
						 }
						 client.setDiaryUser(practiname);
						 client.setWardname(wardname);
						 client.setBedname(bedname);
						 client.setAdmissiondate(admissiondate);
						 String newipdabbr="";
						 if(loginInfo.getIpd_abbr_access()==1){
							 if(labelipdid!=null){
							   newipdabbr=ipdDAO.getIpdAbrivationIds(Integer.parseInt((labelipdid)));
							 }else{
								  newipdabbr=ipdDAO.getIpdAbrivationIds((ipdid));
							 }
							  client.setIpdid(newipdabbr);
						}else{
						client.setIpdid(bed.getIpdseqno());
						}
						 String opdweight=clientDAO.getClientWeight(String.valueOf(client.getId()));
						 if(opdweight==null){
							 opdweight="";
						 }
						 if(bed.getWtaddmission()==null){
						 bed.setWtaddmission("");
						 }
						if(!bed.getWtaddmission().equals("")){
								 client.setWeight(bed.getWtaddmission());
						}else {
							if(!opdweight.equals("")&&!opdweight.equals("0")){
								client.setWeight(opdweight);
							}else{
								client.setWeight("");
							}
						}
						totalBarcodeList.add(client);
					}
					
				}else {
			
				for(int i=1;i<=8;i++){
					
					client.setClientName(clientname);
					/* code128.setData(selectedid);
					 String filePath = request.getRealPath("/barcode/"+client.getId()+i+".gif");
					 code128.drawBarcode(filePath);*/
					 client.setImageName(client.getId()+""+i+""+".gif");
					 client.setCompanyName(loginInfo.getClinicName());
				    
					 if(client.getWhopay()==null){
						  
						 client.setWhopay("Self");
					 }
				 	  
					 if(client.getWhopay().equals(Constants.PAY_BY_CLIENT) || client.getWhopay().equals("Self")){
						 client.setWhopay("Self");
					 }else{
						 ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
						 ThirdParty thirdParty = thirdPartyDAO.getThirdPartyDetails(client.getTypeName());
						 String tpname = thirdParty.getShortname();
						 if(tpname==null){
							 tpname=thirdParty.getCompanyName();
						 }
						 client.setTpName(tpname);
						 client.setWhopay(tpname);
					 }
					 
					 if(client.getGender()==null){
						 client.setGender("M");
					 }
					 
					 if(client.getGender().equals("Male") || client.getGender().equals("M")){
						 client.setGender("M");
					 }else if(client.getGender().equals("Female") || client.getGender().equals("F")){
						 client.setGender("F");
					 }else{
						 client.setGender("O");
					 }
					 client.setDiaryUser(practiname);
					 client.setWardname(wardname);
					 client.setBedname(bedname);
					 client.setAdmissiondate(admissiondate);
					 String newipdabbr="";
					 if(loginInfo.getIpd_abbr_access()==1){
						 if(labelipdid!=null){
						   newipdabbr=ipdDAO.getIpdAbrivationIds(Integer.parseInt((labelipdid)));
						 }else{
							  newipdabbr=ipdDAO.getIpdAbrivationIds((ipdid));
						 }
						  client.setIpdid(newipdabbr);
					}else{
					client.setIpdid(bed.getIpdseqno());
					}
					 String opdweight=clientDAO.getClientWeight(String.valueOf(client.getId()));
					 if(opdweight==null){
						 opdweight="";
					 }
					 if(bed.getWtaddmission()==null){
					 bed.setWtaddmission("");
					 }
					if(!bed.getWtaddmission().equals("")){
							 client.setWeight(bed.getWtaddmission());
					}else {
						if(!opdweight.equals("")&&!opdweight.equals("0")){
							client.setWeight(opdweight);
						}else{
							client.setWeight("");
						}
					}
					totalBarcodeList.add(client);
				}
			}
		}	
			
			
			
			session.setAttribute("totalPatientLabelList", totalBarcodeList);
			ipdForm.setSelectedid(selectedid);
			
			
			
			if(howmany.equals("0")){
				if(loginInfo.getClinicUserid().equals("iconhospital")){
					return "iconelabel";
				}
				
				if(loginInfo.isBalgopal()) {
					return "singlepatientbarcode";
				}else {
				return "singlepatientlabel";
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		if(loginInfo.isBalgopal()) {
			return "patientbarcode";
		}else {
		return "patientlabel";
		}
	}

    public String removemedicine() throws Exception {
    	
    	Connection connection=null;
    	try {
    		connection=Connection_provider.getconnection();
			String id= request.getParameter("id");
			IpdDAO ipdDAO= new JDBCIpdDAO(connection);
			int res= ipdDAO.removeMedicineDischarge(id); 
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
    	
    	return null;
    }
    
    public String gynicassesmentform() throws Exception{
    	
    	if(!verifyLogin(request)) {
    		
    		return "login";
    	}
    	
		//String wardid = request.getParameter("wardid");
    	
		String bedid = request.getParameter("bedid");
		String action = "0";
		String clientid =request.getParameter("clientid");
				
		Connection connection = null;
		try{
			connection = Connection_provider.getconnection();
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			BedDao bedDao = new JDBCBedDao(connection);
			MasterDAO masterDAO=new JDBCMasterDAO(connection);
			ClientDAO clientDAO= new JDBCClientDAO(connection);
			Client client=clientDAO.getClientDetails(clientid);
			String fullname= client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
			ipdForm.setClient(fullname);
			ipdForm.setAbrivationid(client.getAbrivationid());
			ipdForm.setClientid(clientid);
			ipdForm.setAddress(client.getAddress());
			ipdForm.setContact(client.getMobNo());
			String formtype= ipdForm.getFormtype();
			if(formtype==null){
				formtype="1";
			}
			if(loginInfo.isVermanh()){
				formtype="4";
			}
			ArrayList<String> reasonVisitList = new ArrayList<String>();
			
			if(loginInfo.isVermanh()){
				reasonVisitList.add("Amenorrhea ");
				reasonVisitList.add("Leaking PV");
				reasonVisitList.add("Pain in lower abdomen");
				reasonVisitList.add("Nausea");
				reasonVisitList.add("Vomiting");
				reasonVisitList.add("Bleeding PV");
				reasonVisitList.add("Burning Micturition");
				reasonVisitList.add("White discharge PV");
				reasonVisitList.add("Backache");
				reasonVisitList.add("Others");
				reasonVisitList.add("Spotting PV");
				reasonVisitList.add("Fetal movements");
				reasonVisitList.add("Epigastric pain");
				reasonVisitList.add("Oedema");
			}else{
				reasonVisitList= getReasonVisitData(formtype);
			}
			
			
			String chief_comlint_id=masterDAO.getIpdTemplateId("Chief Complaints");
			String present_ill_id=masterDAO.getIpdTemplateId("Present Illness");
			String past_history_id=masterDAO.getIpdTemplateId("Past History");
			String family_hist_id=masterDAO.getIpdTemplateId("Family History");
			String personal_hist_id=masterDAO.getIpdTemplateId("Personal History");
			String onexami_id=masterDAO.getIpdTemplateId("On Examination");
			String tretment_given_id=masterDAO.getIpdTemplateId("Treatment Given");
			
			ArrayList<Master> chief_complaints_list=masterDAO.getIpdTemplateListNames(chief_comlint_id);
			ArrayList<Master> present_illness_list=masterDAO.getIpdTemplateListNames(present_ill_id);
			ArrayList<Master> past_history_list=masterDAO.getIpdTemplateListNames(past_history_id);
			ArrayList<Master> family_history_list=masterDAO.getIpdTemplateListNames(family_hist_id);
			ArrayList<Master> personal_hist_list=masterDAO.getIpdTemplateListNames(personal_hist_id);
			ArrayList<Master> on_exam_list=masterDAO.getIpdTemplateListNames(onexami_id);
			ArrayList<Master> treatment_given_list=masterDAO.getIpdTemplateListNames(tretment_given_id);
			
			ipdForm.setReasonVisitList(reasonVisitList);
			ipdForm.setChief_complaints_list(chief_complaints_list);
			ipdForm.setPresent_illness_list(present_illness_list);
			ipdForm.setPast_history_list(past_history_list);
			ipdForm.setFamily_history_list(family_history_list);
			ipdForm.setPersonal_hist_list(personal_hist_list);
			ipdForm.setOn_exam_list(on_exam_list);
			ipdForm.setTreatment_given_list(treatment_given_list);
			
			ArrayList<Bed> wardlist=bedDao.getAllWardList(action);
			ipdForm.setWardlist(wardlist);
			ipdForm.setFormtype(formtype);
			
			if(loginInfo.isVermanh()){
				return "vermanhgynicform";
			}
			
		
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
		return "gynicform";
    	
    }
    
    
  public String savegynicform() throws Exception{
    	
    	Connection connection=null;
    	
    	try {
    		connection=Connection_provider.getconnection();
    		IpdDAO ipdDAO= new JDBCIpdDAO(connection);
    		MasterDAO masterDAO= new JDBCMasterDAO(connection);
    		BedDao bedDao =new JDBCBedDao(connection);
    		String clientid= ipdForm.getClientid();
    		Bed bed =new Bed();
    		bed.setClientid(clientid);
    		
    		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
    		Calendar calendar=Calendar.getInstance();
    		String commencing = dateFormat.format(calendar.getTime());
    		String lastmodified= DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
    		
    		bed.setCommencing(commencing);
    		bed.setLastmodified(lastmodified);
    		bed.setAlcohal(ipdForm.getAlcohal());
    		bed.setDrugs(ipdForm.getDrugs());
    		bed.setOther_medication(ipdForm.getOther_medication());
    		bed.setTobaco(ipdForm.getTobaco());
    		bed.setTobaconotes(ipdForm.getTobaconotes());
    		bed.setSmoking(ipdForm.getSmoking());
    		
    		bed.setAge_menarche(ipdForm.getAge_menarche());
    		bed.setLmp(ipdForm.getLmp());
    		bed.setLlmp(ipdForm.getLlmp());
    		bed.setPmc(ipdForm.getPmc());
    		
    		
    		bed.setCycle_length(ipdForm.getCycle_length());
    		bed.setDuration_flow(ipdForm.getDuration_flow());
    		bed.setAmount_flow(ipdForm.getAmount_flow());
    		bed.setDysmenorrhea(ipdForm.getDysmenorrhea());
    		bed.setDyspareunia(ipdForm.getDyspareunia());
    		bed.setHopassing_clots(ipdForm.getHopassing_clots());
    		
    		bed.setWhite_disc_itching(ipdForm.getWhite_disc_itching());
    		bed.setIntercourse_freq(ipdForm.getIntercourse_freq());
    		bed.setGalactorrea(ipdForm.getGalactorrea());
    		
    		bed.setHo_contraception(ipdForm.getHo_contraception());
    		bed.setRubella_vaccine(ipdForm.getRubella_vaccine());
    		bed.setMenopause(ipdForm.getMenopause());
    		bed.setNulligravida(ipdForm.getNulligravida());
    		bed.setCurrent_pregnent(ipdForm.getCurrent_pregnent());
    		bed.setIud(ipdForm.getIud());
    		
    		bed.setLive_boys(ipdForm.getLive_boys());
    		bed.setLive_girls(ipdForm.getLive_girls());
    		bed.setStillbirths(ipdForm.getStillbirths());
    		bed.setAbortions(ipdForm.getAbortions());
    		bed.setDeath_children(ipdForm.getDeath_children());
    		
    		//parity_aboration_notes,p,l,a,d
    		bed.setParity_abortion_notes(ipdForm.getParity_abortion_notes());
    		bed.setP(ipdForm.getP());
    		bed.setL(ipdForm.getL());
    		bed.setA(ipdForm.getA());
    		bed.setD(ipdForm.getD());
    		
    		
    		bed.setLmp(ipdForm.getLmp());
    		bed.setEdd(ipdForm.getEdd());
    		bed.setUsg(ipdForm.getUsg()); 
    		bed.setGravida(ipdForm.getGravida());
    		bed.setPara(ipdForm.getPara());
    		bed.setLive(ipdForm.getLive());
    		bed.setAbortion(ipdForm.getAbortion());
    		bed.setMtp(ipdForm.getMtp());
    		bed.setStill_born(ipdForm.getStill_born());
    		bed.setDeath(ipdForm.getDeath());
    		bed.setHigh_risk_factor(ipdForm.getHigh_risk_factor());
    		
    		
    		
    		bed.setSurgical_ho(ipdForm.getSurgical_ho());
    		bed.setDate1(ipdForm.getDate1());
    		bed.setHb1(ipdForm.getHb1());
    		bed.setFbs1(ipdForm.getFbs1());
    		bed.setDpbs1(ipdForm.getDpbs1());
    		bed.setUrm1(ipdForm.getUrm1());
    		bed.setTsh1(ipdForm.getTsh1());
    		bed.setIct1(ipdForm.getIct1());
    		bed.setGtt1(ipdForm.getGtt1());
    		
    		bed.setDate2(ipdForm.getDate2());
    		bed.setHb2(ipdForm.getHb2());
    		bed.setFbs2(ipdForm.getFbs2());
    		bed.setDpbs2(ipdForm.getDpbs2());
    		bed.setUrm2(ipdForm.getUrm2());
    		bed.setTsh2(ipdForm.getTsh2());
    		bed.setIct2(ipdForm.getIct2());
    		bed.setGtt2(ipdForm.getGtt2());
    		
    		
    		bed.setDate3(ipdForm.getDate3());
    		bed.setHb3(ipdForm.getHb3());
    		bed.setFbs3(ipdForm.getFbs3());
    		bed.setDpbs3(ipdForm.getDpbs3());
    		bed.setUrm3(ipdForm.getUrm3());
    		bed.setTsh3(ipdForm.getTsh3());
    		bed.setIct3(ipdForm.getIct3());
    		bed.setGtt3(ipdForm.getGtt3());
    		
    		bed.setDate4(ipdForm.getDate4());
    		bed.setHb4(ipdForm.getHb4());
    		bed.setFbs4(ipdForm.getFbs4());
    		bed.setDpbs4(ipdForm.getDpbs4());
    		bed.setUrm4(ipdForm.getUrm4());
    		bed.setTsh4(ipdForm.getTsh4());
    		bed.setIct4(ipdForm.getIct4());
    		bed.setGtt4(ipdForm.getGtt4());
    		
    		
    		bed.setHv_1m(ipdForm.getHv_1m());
    		bed.setHbs_ag(ipdForm.getHbs_ag());
    		bed.setVdrl(ipdForm.getVdrl());
    		bed.setHb_srecta(ipdForm.getHb_srecta());
    		bed.setHb_ac(ipdForm.getHb_ac());
    		bed.setDuet_markess(ipdForm.getDuet_markess());
    		bed.setTriple(ipdForm.getTriple());
    		bed.setQuadrple_maicers(ipdForm.getQuadrple_maicers());
    		bed.setEarlierinvest(ipdForm.getEarlierinvest());
    		
    		bed.setVisit_reason_ids(ipdForm.getVisit_reason_ids());
    		//ivf_date, down_regulation, ovarian_stimulation, os_dosage, sperm_quality, et_day, oocytes_obtained,
    		//oocytes_quality, embroyos_grade, embroyos_transfered, embroyos_description, freezing, transfer_process, betahcgcm, ivf_remark, do_family_history,
    		//ho_fertility_family, ho_genetic_family, ho_premature_family, age_of_menarche, age_of_menarche_notes, dysmenorrhoe, dysmenorrhoe_notes,
    		//flow, flow_notes, sleep_disruption_bleeding, sleep_disruption_bleeding_notes, blachouts, blachouts_notes
    		bed.setIvf_date(ipdForm.getIvf_date());
    		bed.setDown_regulation(ipdForm.getDown_regulation());
    		bed.setOvarian_stimulation(ipdForm.getOvarian_stimulation());
    		bed.setOs_dosage(ipdForm.getOs_dosage());
    		bed.setSperm_quality(ipdForm.getSperm_quality());
    		bed.setEt_day(ipdForm.getEt_day());
    		bed.setOocytes_obtained(ipdForm.getOocytes_obtained());
    		bed.setOocytes_quality(ipdForm.getOocytes_quality());
    		bed.setEmbroyos_grade(ipdForm.getEmbroyos_grade());
    		bed.setEmbroyos_transfered(ipdForm.getEmbroyos_transfered());
    		bed.setEmbroyos_description(ipdForm.getEmbroyos_description());
    		bed.setFreezing(ipdForm.getFreezing());
    		bed.setTransfer_process(ipdForm.getTransfer_process());
    		bed.setBetahcgcm(ipdForm.getBetahcgcm());
    		bed.setIvf_remark(ipdForm.getIvf_remark());
    		bed.setDo_family_history(ipdForm.getDo_family_history());
    		bed.setHo_fertility_family(ipdForm.getHo_fertility_family());
    		bed.setHo_genetic_family(ipdForm.getHo_genetic_family());
    		bed.setHo_premature_family(ipdForm.getHo_premature_family());
    		bed.setAge_of_menarche(ipdForm.getAge_of_menarche());
    		bed.setDysmenorrhoe(ipdForm.getDysmenorrhoe());
    		bed.setDysmenorrhoe_notes(ipdForm.getDysmenorrhoe_notes());
    		bed.setFlow(ipdForm.getFlow());
    		bed.setFlow_notes(ipdForm.getFlow_notes());
    		bed.setSleep_disruption_bleeding(ipdForm.getSleep_disruption_bleeding());
    		bed.setSleep_disruption_bleeding_notes(ipdForm.getSleep_disruption_bleeding_notes());
    		bed.setBlachouts(ipdForm.getBlachouts());
    		bed.setBlachouts_notes(ipdForm.getBlachouts_notes());
    		
    		 //anormaly_scan_11week, cervical_length_11week, double_marker_11week,;
			  bed.setAnormaly_scan_11week(ipdForm.getAnormaly_scan_11week());
			  bed.setCervical_length_11week(ipdForm.getCervical_length_11week());
			  bed.setDouble_marker_11week(ipdForm.getDouble_marker_11week());
			  
			  //all_investigation_16week, sikling_16week, triple_marker_16week, abstinence_1visit;
			  bed.setAll_investigation_16week(ipdForm.getAll_investigation_16week());
			  bed.setSikling_16week(ipdForm.getSikling_16week());
			  bed.setTriple_marker_16week(ipdForm.getTriple_marker_16week());
			  bed.setAbstinence_1visit(ipdForm.getAbstinence_1visit());
			  
			  //barrier_contra_1visit, bed_rest_1visit, book_1visit, csv_1visit, dispi_test_1visit;
			  bed.setBarrier_contra_1visit(ipdForm.getBarrier_contra_1visit());
			  bed.setBed_rest_1visit(ipdForm.getBed_rest_1visit());
			  bed.setBook_1visit(ipdForm.getBook_1visit());
			  bed.setCsv_1visit(ipdForm.getCsv_1visit());
			  bed.setDispi_test_1visit(ipdForm.getDispi_test_1visit());
			  
			  //drug_reaction_1visit, hcg_1visit, heparin_1visit, oral_hygeine_1visit, other_test_1visit, physio_diet_1visit,
			  bed.setDrug_reaction_1visit(ipdForm.getDrug_reaction_1visit());
			  bed.setHcg_1visit(ipdForm.getHcg_1visit());
			  bed.setHeparin_1visit(ipdForm.getHeparin_1visit());
			  bed.setOral_hygeine_1visit(ipdForm.getOral_hygeine_1visit());
			  bed.setOther_test_1visit(ipdForm.getOther_test_1visit());
			  bed.setPhysio_diet_1visit(ipdForm.getPhysio_diet_1visit());
			  
			  //rubelle_status_1visit, smart_doc_1visit, stream_cell_1visit, vaginities_1visit, animally_scan_20week
			  bed.setRubelle_status_1visit(ipdForm.getRubelle_status_1visit());
			  bed.setSmart_doc_1visit(ipdForm.getSmart_doc_1visit());
			  bed.setStream_cell_1visit(ipdForm.getStream_cell_1visit());
			  bed.setVaginities_1visit(ipdForm.getVaginities_1visit());
			  bed.setAnimally_scan_20week(ipdForm.getAnimally_scan_20week());
			  
			  //fetal_eco_20week, anti_d_24week, dipsi_24week, itc_24week, investigation_sos_30week, steroids_30week;
			  bed.setFetal_eco_20week(ipdForm.getFetal_eco_20week());
			  bed.setAnti_d_24week(ipdForm.getAnti_d_24week());
			  bed.setDipsi_24week(ipdForm.getDipsi_24week());
			  bed.setItc_24week(ipdForm.getItc_24week());
			  bed.setInvestigation_sos_30week(ipdForm.getInvestigation_sos_30week());
			  bed.setSteroids_30week(ipdForm.getSteroids_30week());
			  
			  //vaginities_treatment_30week, breast_preparation_34week, color_doppler_34week, labour_counselling_34week, nst_34week, vaginities_treatment_34week
			  bed.setVaginities_treatment_30week(ipdForm.getVaginities_treatment_30week());
			  bed.setBreast_preparation_34week(ipdForm.getBreast_preparation_34week());
			  bed.setColor_doppler_34week(ipdForm.getColor_doppler_34week());
			  bed.setLabour_counselling_34week(ipdForm.getLabour_counselling_34week());
			  bed.setNst_34week(ipdForm.getNst_34week());
			  bed.setVaginities_treatment_34week(ipdForm.getVaginities_treatment_34week());
    		
			   //nst_date1, nst_date2, nst_date3, nst_date4, nst_date5, nst_date6,
			  bed.setNst_date1(ipdForm.getNst_date1());
			  bed.setNst_date2(ipdForm.getNst_date2());
			  bed.setNst_date3(ipdForm.getNst_date3());
			  bed.setNst_date4(ipdForm.getNst_date4());
			  bed.setNst_date5(ipdForm.getNst_date5());
			  bed.setNst_date6(ipdForm.getNst_date6());
			  
			  //nst_time1, nst_time2, nst_time3, nst_time4, nst_time5, nst_time6,
			  bed.setNst_time1(ipdForm.getNst_time1());
			  bed.setNst_time2(ipdForm.getNst_time2());
			  bed.setNst_time3(ipdForm.getNst_time3());
			  bed.setNst_time4(ipdForm.getNst_time4());
			  bed.setNst_time5(ipdForm.getNst_time5());
			  bed.setNst_time6(ipdForm.getNst_time6());
			  
			  //nst_indication1, nst_indication2, nst_indication3, nst_indication4, nst_indication5, nst_indication6,
			  bed.setNst_indication1(ipdForm.getNst_indication1());
			  bed.setNst_indication2(ipdForm.getNst_indication2());
			  bed.setNst_indication3(ipdForm.getNst_indication3());
			  bed.setNst_indication4(ipdForm.getNst_indication4());
			  bed.setNst_indication5(ipdForm.getNst_indication5());
			  bed.setNst_indication6(ipdForm.getNst_indication6());
			  
			  //nst_duration1, nst_duration2, nst_duration3, nst_duration4, nst_duration5, nst_duration6,
			  bed.setNst_duration1(ipdForm.getNst_duration1());
			  bed.setNst_duration2(ipdForm.getNst_duration2());
			  bed.setNst_duration3(ipdForm.getNst_duration3());
			  bed.setNst_duration4(ipdForm.getNst_duration4());
			  bed.setNst_duration5(ipdForm.getNst_duration5());
			  bed.setNst_duration6(ipdForm.getNst_duration6());
			  
			  //nst_interpretation1, nst_interpretation2, nst_interpretation3, nst_interpretation4, nst_interpretation5, nst_interpretation6,
			  bed.setNst_interpretation1(ipdForm.getNst_interpretation1());
			  bed.setNst_interpretation2(ipdForm.getNst_interpretation2());
			  bed.setNst_interpretation3(ipdForm.getNst_interpretation3());
			  bed.setNst_interpretation4(ipdForm.getNst_interpretation4());
			  bed.setNst_interpretation5(ipdForm.getNst_interpretation5());
			  bed.setNst_interpretation6(ipdForm.getNst_interpretation6());
			  
			  //nst_intervention1, nst_intervention2, nst_intervention3, nst_intervention4, nst_intervention5, nst_intervention6
			  bed.setNst_intervention1(ipdForm.getNst_intervention1());
			  bed.setNst_intervention2(ipdForm.getNst_intervention2());
			  bed.setNst_intervention3(ipdForm.getNst_intervention3());
			  bed.setNst_intervention4(ipdForm.getNst_intervention4());
			  bed.setNst_intervention5(ipdForm.getNst_intervention5());
			  bed.setNst_intervention6(ipdForm.getNst_intervention6());
			  
			  //tt_dose1, tt_dose2, influenza_vaccine, usgdate1, usgdate2, usgdate3, usgdate4,
			  bed.setTt_dose1(ipdForm.getTt_dose1());
			  bed.setTt_dose2(ipdForm.getTt_dose2());
			  bed.setInfluenza_vaccine(ipdForm.getInfluenza_vaccine());
			  bed.setUsgdate1(ipdForm.getUsgdate1());
			  bed.setUsgdate2(ipdForm.getUsgdate2());
			  bed.setUsgdate3(ipdForm.getUsgdate3());
			  bed.setUsgdate4(ipdForm.getUsgdate4());
			  
			  //amenorrhea1, amenorrhea2, amenorrhea3, amenorrhea4, presentation1, presentation2, presentation3, presentation4,
			  bed.setAmenorrhea1(ipdForm.getAmenorrhea1());
			  bed.setAmenorrhea2(ipdForm.getAmenorrhea2());
			  bed.setAmenorrhea3(ipdForm.getAmenorrhea3());
			  bed.setAmenorrhea4(ipdForm.getAmenorrhea4());
			  bed.setPresentation1(ipdForm.getPresentation1());
			  bed.setPresentation2(ipdForm.getPresentation2());
			  bed.setPresentation3(ipdForm.getPresentation3());
			  bed.setPresentation4(ipdForm.getPresentation4());
			  
			  //bpdgs1, bpdgs2, bpdgs3, bpdgs4, hc1, hc2, hc3, hc4,
			  bed.setBpdgs1(ipdForm.getBpdgs1());
			  bed.setBpdgs2(ipdForm.getBpdgs2());
			  bed.setBpdgs3(ipdForm.getBpdgs3());
			  bed.setBpdgs4(ipdForm.getBpdgs4());
			  bed.setHc1(ipdForm.getHc1());
			  bed.setHc2(ipdForm.getHc1());
			  bed.setHc3(ipdForm.getHc1());
			  bed.setHc4(ipdForm.getHc1());
			  
			  //ac1, ac2, ac3, ac4, flcrl1, flcrl2, flcrl3, flcrl4,
			  bed.setAc1(ipdForm.getAc1());
			  bed.setAc2(ipdForm.getAc2());
			  bed.setAc3(ipdForm.getAc3());
			  bed.setAc4(ipdForm.getAc4());
			  bed.setFlcrl1(ipdForm.getFlcrl1());
			  bed.setFlcrl2(ipdForm.getFlcrl2());
			  bed.setFlcrl3(ipdForm.getFlcrl3());
			  bed.setFlcrl4(ipdForm.getFlcrl4());
			  
			  //ga_usg1, ga_usg2, ga_usg3, ga_usg4, liquor1, liquor2, liquor3, liquor4,
			  bed.setGa_usg1(ipdForm.getGa_usg1());
			  bed.setGa_usg2(ipdForm.getGa_usg2());
			  bed.setGa_usg3(ipdForm.getGa_usg3());
			  bed.setGa_usg4(ipdForm.getGa_usg4());
			  bed.setLiquor1(ipdForm.getLiquor1());
			  bed.setLiquor2(ipdForm.getLiquor2());
			  bed.setLiquor3(ipdForm.getLiquor3());
			  bed.setLiquor4(ipdForm.getLiquor4());
			  
			  //placenta1, placenta2, placenta3, placenta4, foetal_weight1, foetal_weight2, foetal_weight3, foetal_weight4,
			  bed.setPlacenta1(ipdForm.getPlacenta1());
			  bed.setPlacenta2(ipdForm.getPlacenta2());
			  bed.setPlacenta3(ipdForm.getPlacenta3());
			  bed.setPlacenta4(ipdForm.getPlacenta4());
			  bed.setFoetal_weight1(ipdForm.getFoetal_weight1());
			  bed.setFoetal_weight2(ipdForm.getFoetal_weight2());
			  bed.setFoetal_weight3(ipdForm.getFoetal_weight3());
			  bed.setFoetal_weight4(ipdForm.getFoetal_weight4());
			  
			  //cervical_length1, cervical_length2, cervical_length3, cervical_length4, nt_scan, anomaly_scan, colour_doppler_scan
			  bed.setCervical_length1(ipdForm.getCervical_length1());
			  bed.setCervical_length2(ipdForm.getCervical_length2());
			  bed.setCervical_length3(ipdForm.getCervical_length3());
			  bed.setCervical_length4(ipdForm.getCervical_length4());
			  bed.setNt_scan(ipdForm.getNt_scan());
			  bed.setAnomaly_scan(ipdForm.getAnomaly_scan());
			  bed.setColour_doppler_scan(ipdForm.getColour_doppler_scan());  
			  
			  //gen_condition, temp, pallor, pedal_edema, pulse, bmi, height,weight, sys_bp, dia_bp, wall_edema, fundal_height
			  bed.setGen_condition(ipdForm.getGen_condition());
			  bed.setTemp(ipdForm.getTemp());
			  bed.setPallor(ipdForm.getPallor());
			  bed.setPedal_edema(ipdForm.getPedal_edema());
			  bed.setPulse(ipdForm.getPulse());
			  bed.setBmi(ipdForm.getBmi());
			  bed.setHeight(ipdForm.getHeight());
			  bed.setWeight(ipdForm.getWeight() );
			  bed.setSys_bp(ipdForm.getSys_bp());
			  bed.setDia_bp(ipdForm.getDia_bp());
			  bed.setWall_edema(request.getParameter("wall_edema"));
			  bed.setFundal_height(ipdForm.getFundal_height());
			  
			  //cephalic, breech, baley_size, transverse_fhs, liquor, uterine_contractions, ps_cervix, ps_vagine,
			  
			  String cephalic =request.getParameter("cephalic");
			  String cephalicVal=request.getParameter("cephalicVal");
			  
			  if(cephalic!=null){
				  
				  if(cephalic.equals("on")){
					  
					  cephalic= cephalicVal;
				  } else {
					  cephalic="0";
				  }
			  }else {
				  cephalic="0";
			  }
			  
			  bed.setCephalic(cephalic);
			  
			  String breech= request.getParameter("breech"); 
			  if(breech!=null){
				  
				  if(breech.equals("on")){
					  breech= "1";
				  } else {
					  breech= "0";
				  }
				  
			  } else {
				  breech="0";
			  }
			  bed.setBreech(breech);
			  
			  String baley_size= request.getParameter("baley_size");
			  String baley_sizeVal= request.getParameter("baley_sizeVal");
			  if(baley_size!=null){
				  
				  if(baley_size.equals("on")){
					  baley_size= baley_sizeVal;
				  } else {
					  baley_size= "0";
				  }
				  
			  } else {
				  baley_size="0";
			  }
			  
			  bed.setBaley_size(baley_size);
			  
			  String transverse_fhs= request.getParameter("transverse_fhs");
			  String transverse_fhsVal= request.getParameter("transverse_fhsVal");
			  if(transverse_fhs!=null){
				  
				  if(transverse_fhs.equals("on")){
					  transverse_fhs= transverse_fhsVal;
				  } else {
					  transverse_fhs= "0";
				  }
				  
			  } else {
				  transverse_fhs="0";
			  }
			  
			  bed.setTransverse_fhs(transverse_fhs);
			  
			  String liquor=request.getParameter("liquor");
			  if(liquor==null){
				  liquor="0";
			  }else if(liquor.equals("")){
				  liquor="0";
			  } 
			  bed.setLiquor(liquor);
			  
			  String uterine_contractions=request.getParameter("uterine_contractions");
			  if(uterine_contractions==null){
				  uterine_contractions="0";
			  }else if(uterine_contractions.equals("")){
				  uterine_contractions="0";
			  } 
			  
			  bed.setUterine_contractions(uterine_contractions);
			  
			  String ps_cervix= request.getParameter("ps_cervix");
			  String ps_cervixVal= request.getParameter("ps_cervixVal");
			  if(ps_cervix!=null){
				  
				  if(ps_cervix.equals("on")){
					  
					  ps_cervix= ps_cervixVal;
				  } else {
					  ps_cervix="0";
				  }
			  }else {
				  ps_cervix="0";
			  }
			  
			  bed.setPs_cervix(ps_cervix);
			  
			  String ps_vagine= request.getParameter("ps_vagine");
			  String ps_vagineVal= request.getParameter("ps_vagineVal");
			  if(ps_vagine!=null){
				  
				  if(ps_vagine.equals("on")){
					  
					  ps_vagine= ps_vagineVal;
				  } else {
					  ps_vagine="0";
				  }
			  }else {
				  ps_vagine="0";
			  }
			  
			  bed.setPs_vagine(ps_vagine);
			  
			  //ps_vault, pv_trim, pv_unettacced, pv_ant, pv_os, pv_membranes, pv_tubular, pv_just_ettacced, pv_mid_posits, pv_soft, pv_ettacced, pv_post
			  
			  String ps_vault= request.getParameter("ps_vault");
			  String ps_vaultVal= request.getParameter("ps_vaultVal");
			  if(ps_vault!=null){
				  
				  if(ps_vault.equals("on")){
					  
					  ps_vault= ps_vaultVal;
				  } else {
					  ps_vault="0";
				  }
			  }else {
				  ps_vault="0";
			  }
			  
			  bed.setPs_vault(ps_vault);

			  String pv_trim=  request.getParameter("pv_trim");
			  if(pv_trim!=null){
				  
				  if(pv_trim.equals("on")){
					  pv_trim="1";
				  } else {
					  pv_trim="0";
				  }
				  
			  } else {
				  pv_trim="0";
			  }
			  
			  bed.setPv_trim(pv_trim);
			  
			  String pv_unettacced=  request.getParameter("pv_unettacced");
			  if(pv_unettacced!=null){
				  
				  if(pv_unettacced.equals("on")){
					  pv_unettacced="1";
				  } else {
					  pv_unettacced="0";
				  }
				  
			  } else {
				  pv_unettacced="0";
			  }
			  
			  bed.setPv_unettacced(pv_unettacced);
			  
			  String pv_ant=  request.getParameter("pv_ant");
			  if(pv_ant!=null){
				  
				  if(pv_ant.equals("on")){
					  pv_ant="1";
				  } else {
					  pv_ant="0";
				  }
				  
			  } else {
				  pv_ant="0";
			  }
			  
			  bed.setPv_ant(pv_ant);
			  
			  String pv_os=  request.getParameter("pv_os");
			  String pv_osVal= request.getParameter("pv_osVal");
			  if(pv_os!=null){
				  
				  if(pv_os.equals("on")){
					  pv_os=pv_osVal;
				  } else {
					  pv_os="0";
				  }
				  
			  } else {
				  pv_os="0";
			  }
			  bed.setPv_os(pv_os);
			  
			  String pv_membranes= request.getParameter("pv_membranes");
			  if(pv_membranes!=null){
				  
				  if(pv_membranes.equals("on")){
					  pv_membranes="1";
				  } else {
					  pv_membranes="0";
				  }
				  
			  } else {
				  pv_membranes="0";
			  }
			  
			  bed.setPv_membranes(pv_membranes);
			  
			  String pv_tubular= request.getParameter("pv_tubular");
			  if(pv_tubular!=null){
				  
				  if(pv_tubular.equals("on")){
					  pv_tubular="1";
				  } else {
					  pv_tubular="0";
				  }
				  
			  } else {
				  pv_tubular="0";
			  }
			  bed.setPv_tubular(pv_tubular);
			  
			  String pv_just_ettacced= request.getParameter("pv_just_ettacced");
			  if(pv_just_ettacced!=null){
				  
				  if(pv_just_ettacced.equals("on")){
					  pv_just_ettacced="1";
				  } else {
					  pv_just_ettacced="0";
				  }
				  
			  } else {
				  pv_just_ettacced="0";
			  }
			  bed.setPv_just_ettacced(pv_just_ettacced);
			  
			  String pv_mid_posits= request.getParameter("pv_mid_posits");
			  if(pv_mid_posits!=null){
				  
				  if(pv_mid_posits.equals("on")){
					  pv_mid_posits="1";
				  } else {
					  pv_mid_posits="0";
				  }
				  
			  } else {
				  pv_mid_posits="0";
			  }
			  
			  bed.setPv_mid_posits(pv_mid_posits);
			  
			  String pv_soft= request.getParameter("pv_soft");
			  if(pv_soft!=null){
				  
				  if(pv_soft.equals("on")){
					  pv_soft="1";
				  } else {
					  pv_soft="0";
				  }
				  
			  } else {
				  pv_soft="0";
			  }
			  
			  bed.setPv_soft(pv_soft);
			  
			  String pv_ettacced= request.getParameter("pv_ettacced");
			  if(pv_ettacced!=null){
				  
				  if(pv_ettacced.equals("on")){
					  pv_ettacced="1";
				  } else {
					  pv_ettacced="0";
				  }
				  
			  } else {
				  pv_ettacced="0";
			  }
			  
			  bed.setPv_ettacced(pv_ettacced);
			  
			  String pv_post= request.getParameter("pv_post");
			  if(pv_post!=null){
				  
				  if(pv_post.equals("on")){
					  pv_post="1";
				  } else {
					  pv_post="0";
				  }
				  
			  } else {
				  pv_post="0";
			  }
			  
			  bed.setPv_post(pv_post); 
    		
			  
			     String ps_fhs= request.getParameter("ps_fhs");
			     if(ps_fhs!=null){
			      
			      if(ps_fhs.equals("")){
			       ps_fhs="0";
			      }
			      
			     } else {
			      ps_fhs="0";
			     }
			     
			     bed.setPs_fhs(ps_fhs);
			      
			     String pv_membrane= request.getParameter("pv_membrane");
			     if(pv_membrane!=null){
			      
			      if(pv_membrane.equals("")){
			       pv_membrane="0";
			      } 
			      
			     } else {
			      pv_membrane="0";
			     }
			     bed.setPv_membrane(pv_membrane);
			     String pv_station= request.getParameter("pv_station");
			     bed.setPv_station(pv_station);
			     String pv_liquor= request.getParameter("pv_liquor");
			     bed.setPv_liquor(pv_liquor);
			     String pv_pelvis= request.getParameter("pv_pelvis");
			     bed.setPv_pelvis(pv_pelvis);
			     String pv_position= request.getParameter("Position");
			     bed.setPv_position(pv_position);
			    
			     	
			     bed.setBeats_min(ipdForm.getBeats_min());
			     
			     //jitu
			     bed.setPmp(ipdForm.getPmp());
			     bed.setDiagnosisgyn(ipdForm.getDiagnosisgyn());
			     String le_vulva =request.getParameter("le_vulva");
			     if(le_vulva!=null){
			    	 
			    	 if(!le_vulva.equals("Normal")){

			    		 String le_vulva_txt= request.getParameter("le_vulva_txt");	
			    		 le_vulva= le_vulva_txt;
			    	 } 
			    	 
			     }else {
			    	 
			    	 le_vulva="0";
			     }
			     bed.setLe_vulva(le_vulva);
			     
			     String le_vagina =request.getParameter("le_vagina");
			     if(le_vagina!=null){
			    	 
			    	 if(!le_vagina.equals("Normal")){

			    		 String le_vagina_txt= request.getParameter("le_vagina_txt");	
			    		 le_vagina= le_vagina_txt;
			    	 } 
			     }else {
			    	 le_vagina="0";
			     }
			     bed.setLe_vagina(le_vagina);
			     
			     bed.setPa_gynic(ipdForm.getPa_gynic());
			     
			     String pv_uterus= request.getParameter("pv_uterus");
			     if(pv_uterus!=null){
			    	 
			    	 if(!pv_uterus.equals("0")){
			    		 
			    		 String pv_uterus_val= request.getParameter("pv_uterusVal");
			    		 if(pv_uterus_val!=null){
			    			 
			    			 if(!pv_uterus_val.equals("0")){
			    				 pv_uterus=pv_uterus_val;
			    			 }else {
			    				 pv_uterus= "0";
			    			 }
			    		 }else {
			    			 pv_uterus= "0";
			    		 }
			    	 }else {
			    		 pv_uterus= "0";
			    	 }
			    	 
			     } else {
			    	 pv_uterus="0";
			     }
			     bed.setPv_uterus(pv_uterus);
			     
			     
			     String pv_uterus_size= request.getParameter("pv_uterus_size");
			     if(pv_uterus_size!=null){
			    	 
			    	 if(!pv_uterus_size.equals("0")){
			    		 
			    		 String pv_uterus_sizeVal= request.getParameter("pv_uterus_sizeVal");
			    		 if(pv_uterus_sizeVal!=null){
			    			 
			    			 if(!pv_uterus_sizeVal.equals("0")){
			    				 pv_uterus_size=pv_uterus_sizeVal;
			    			 }else {
			    				 pv_uterus_size= "0";
			    			 }
			    		 }else {
			    			 pv_uterus_size= "0";
			    		 }
			    	 }else {
			    		 pv_uterus_size= "0";
			    	 }
			    	 
			     } else {
			    	 pv_uterus_size="0";
			     }
			     
			     bed.setPv_uterus_size(pv_uterus_size);
			     
			     String pv_bl_fomices= request.getParameter("pv_bl_fomices");
			     if(pv_bl_fomices!=null){
			    	 
			    	 if(!pv_bl_fomices.equals("0")){
			    		 
			    		 String pv_bl_fomicesVal= request.getParameter("pv_bl_fomicesVal");
			    		 if(pv_bl_fomicesVal!=null){
			    			 
			    			 if(!pv_bl_fomicesVal.equals("0")){
			    				 pv_bl_fomices=pv_bl_fomicesVal;
			    			 }else {
			    				 pv_bl_fomices= "0";
			    			 }
			    		 }else {
			    			 pv_bl_fomices= "0";
			    		 }
			    	 }else {
			    		 pv_bl_fomices= "0";
			    	 }
			    	 
			     } else {
			    	 pv_bl_fomices="0";
			     }
			     bed.setPv_bl_fomices(pv_bl_fomices);
			     
			     String pv_mobility= request.getParameter("pv_mobility");
			     if(pv_mobility!=null){
			    	 
			    	 if(!pv_mobility.equals("0")){
			    		 
			    		 String pv_mobilityVal= request.getParameter("pv_mobilityVal");
			    		 if(pv_mobilityVal!=null){
			    			 
			    			 if(!pv_mobilityVal.equals("0")){
			    				 pv_mobility=pv_mobilityVal;
			    			 }else {
			    				 pv_mobility= "0";
			    			 }
			    		 }else {
			    			 pv_mobility= "0";
			    		 }
			    	 }else {
			    		 pv_mobility= "0";
			    	 }
			    	 
			     } else {
			    	 pv_mobility="0";
			     }
			     bed.setPv_mobility(pv_mobility);
			     
			     bed.setPlan(ipdForm.getPlan());
			     bed.setFinaldiagnosis(ipdForm.getFinaldiagnosis());
			     bed.setPriscription(ipdForm.getPriscription());
			     bed.setFormtype(ipdForm.getFormtype());
			     
			     //  15-06-2020
//			     bed.setHrf_gdm(DateTimeUtils.booleanCheck(ipdForm.getHrf_gdm()));
//			     bed.setHrf_pih(DateTimeUtils.booleanCheck(ipdForm.getHrf_pih()));
//			     bed.setHrf_boh(DateTimeUtils.booleanCheck(ipdForm.getHrf_boh()));
//			     bed.setHrf_hypothyroid(DateTimeUtils.booleanCheck(ipdForm.getHrf_hypothyroid()));
//			     bed.setHrf_hyperthyroid(DateTimeUtils.booleanCheck(ipdForm.getHrf_hyperthyroid()));
//			     bed.setHrf_prev_lscs(DateTimeUtils.booleanCheck(ipdForm.getHrf_prev_lscs()));
//			     bed.setHrf_rh_negative(DateTimeUtils.booleanCheck(ipdForm.getHrf_rh_negative()));
//			     bed.setHrf_other(DateTimeUtils.booleanCheck(ipdForm.getHrf_other()));
//			     bed.setNulligravida(DateTimeUtils.booleanCheck(ipdForm.getNulligravida()));
//			     bed.setCurrent_pregnent(DateTimeUtils.booleanCheck(ipdForm.getCurrent_pregnent()));
//			     bed.setIud(DateTimeUtils.booleanCheck(ipdForm.getIud()));
//			     bed.setFundalheight_weeks(DateTimeUtils.isNull(ipdForm.getFundalheight_weeks()));
//			     bed.setPa_soft(DateTimeUtils.booleanCheck(ipdForm.getPa_soft()));
//			     bed.setExternal_ballotment(DateTimeUtils.booleanCheck(ipdForm.getExternal_ballotment()));
//			     bed.setEffaced_text(DateTimeUtils.isNull(ipdForm.getEffaced_text()));
//			     bed.setPv_dialated(DateTimeUtils.booleanCheck(ipdForm.getPv_dialated()));
//			     bed.setPv_dialatedcm(DateTimeUtils.isNull(ipdForm.getPv_dialatedcm()));
//			     bed.setG(DateTimeUtils.isNull(ipdForm.getG()));
//			     bed.setDating_usg_date(ipdForm.getDating_usg_date());
//			     bed.setWeeks_dating_scan(ipdForm.getWeeks_dating_scan());
//			     bed.setPog_dating_scan(ipdForm.getPog_dating_scan());
//			     bed.setUsg_report(ipdForm.getUsg_report());
//			     bed.setAdditonal_notes(ipdForm.getAdditonal_notes());
//			     bed.setDiagonosis(ipdForm.getDiagonosis());
//			     bed.setTreatment_advice(ipdForm.getTreatment_advice());
//			     bed.setInvestigation_advice(ipdForm.getInvestigation_advice());
			     
			     int res= ipdDAO.saveIpdGynicDetails(bed);
    		
    		 //save gynic obs history
            if(ipdForm.getObslist()!=null){
            	 for(Bed bed2 :ipdForm.getObslist()){
                	  bed2.setGynicid(String.valueOf(res));
            	      int r=bedDao.saveGynicObsData(0,bed2);
              }
            }
    		
    		String chief_comlint_id=masterDAO.getIpdTemplateId("Chief Complaints");
			String present_ill_id=masterDAO.getIpdTemplateId("Present Illness");
			String past_history_id=masterDAO.getIpdTemplateId("Past History");
			String family_hist_id=masterDAO.getIpdTemplateId("Family History");
			String personal_hist_id=masterDAO.getIpdTemplateId("Personal History");
			String onexami_id=masterDAO.getIpdTemplateId("On Examination");
			String tretment_given_id=masterDAO.getIpdTemplateId("Treatment Given");
			
			ArrayList<Master> chief_complaints_list=masterDAO.getIpdTemplateListNames(chief_comlint_id);
			ArrayList<Master> present_illness_list=masterDAO.getIpdTemplateListNames(present_ill_id);
			ArrayList<Master> past_history_list=masterDAO.getIpdTemplateListNames(past_history_id);
			ArrayList<Master> family_history_list=masterDAO.getIpdTemplateListNames(family_hist_id);
			ArrayList<Master> personal_hist_list=masterDAO.getIpdTemplateListNames(personal_hist_id);
			ArrayList<Master> on_exam_list=masterDAO.getIpdTemplateListNames(onexami_id);
			ArrayList<Master> treatment_given_list=masterDAO.getIpdTemplateListNames(tretment_given_id);
			ipdForm.setChief_complaints_list(chief_complaints_list);
			ipdForm.setPresent_illness_list(present_illness_list);
			ipdForm.setPast_history_list(past_history_list);
			ipdForm.setFamily_history_list(family_history_list);
			ipdForm.setPersonal_hist_list(personal_hist_list);
			ipdForm.setOn_exam_list(on_exam_list);
			ipdForm.setTreatment_given_list(treatment_given_list);
			
			
			ArrayList<Bed> wardlist=bedDao.getAllWardList("0");
			ipdForm.setWardlist(wardlist);
    		
			
			session.setAttribute("gynicformid", String.valueOf(res));
			
		} catch (Exception e) {

			e.printStackTrace();
		}
    	finally {
    		connection.close();
    	}
    	
    	
    	return "redirecteditgynic";
    }
  
  
    public String editgynicform() throws Exception{
    	
    	Connection connection=null;
    	
    	try {
    		connection=Connection_provider.getconnection();
    		String id= request.getParameter("selectedid"); 
    		if(id==null) {
    			id=(String) session.getAttribute("gynicformid");
    		}
    		
    		IpdDAO ipdDAO= new JDBCIpdDAO(connection);
    		MasterDAO masterDAO= new JDBCMasterDAO(connection);
    		BedDao bedDao= new JDBCBedDao(connection);
    		
    		Bed bed= ipdDAO.getEditGynicFormData(id);
    		
    		ClientDAO clientDAO =new JDBCClientDAO(connection);
    		Client client=clientDAO.getClientDetails(bed.getClientid());
    		
    		String fullname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();
    		ipdForm.setClient(fullname);
    		ipdForm.setRelativename(client.getEmergencyContName());
    		ipdForm.setRelationcont(client.getEmergencyContNo());
    		ipdForm.setRelation(client.getRelation());
    		ipdForm.setClientid(bed.getClientid());
    		ipdForm.setEditclientid(bed.getClientid());
    		//String numcount=ipdDAO.getNumofAdmissionCount(ipdForm.getClientid());
    		String formtype= bed.getFormtype();
    		
    		ArrayList<String> reasonVisitList= getReasonVisitData(formtype);
    		
    		ipdForm.setReasonVisitList(reasonVisitList);
    		
    		//ipdForm.setNum_admission(numcount);
    		ipdForm.setDob(client.getDob());
    		ipdForm.setTpid(client.getTypeName());
    		ipdForm.setAddress(client.getAddress());
    		
    		String age = DateTimeUtils.getAge(client.getDob());
    		String agegender = age + "Years" + " / " + client.getGender();
    		ipdForm.setAgegender(agegender);
    		ipdForm.setVisit_reason_ids(bed.getVisit_reason_ids());
    		ipdForm.setId(bed.getId());
    		ipdForm.setLastmodified(bed.getCommencing());
    		ipdForm.setAlcohal(bed.getAlcohal());
    		ipdForm.setDrugs(bed.getDrugs());
    		ipdForm.setOther_medication(bed.getOther_medication());
    		ipdForm.setTobaco(bed.getTobaco());
    		ipdForm.setTobaconotes(bed.getTobaconotes());
    		ipdForm.setSmoking(bed.getSmoking());
    		
    		ipdForm.setAge_menarche(bed.getAge_menarche());
    		ipdForm.setLmp(bed.getLmp());
    		ipdForm.setLlmp(bed.getLlmp());
    		ipdForm.setPmc(bed.getPmc());
    		
    		
    		ipdForm.setCycle_length(bed.getCycle_length());
    		ipdForm.setDuration_flow(bed.getDuration_flow());
    		ipdForm.setAmount_flow(bed.getAmount_flow());
    		ipdForm.setDysmenorrhea(bed.getDysmenorrhea());
    		ipdForm.setDyspareunia(bed.getDyspareunia());
    		ipdForm.setHopassing_clots(bed.getHopassing_clots());
    		
    		ipdForm.setWhite_disc_itching(bed.getWhite_disc_itching());
    		ipdForm.setIntercourse_freq(bed.getIntercourse_freq());
    		ipdForm.setGalactorrea(bed.getGalactorrea());
    		
    		ipdForm.setHo_contraception(bed.getHo_contraception());
    		ipdForm.setRubella_vaccine(bed.getRubella_vaccine());
    		ipdForm.setMenopause(bed.getMenopause());
    		ipdForm.setNulligravida(bed.getNulligravida());
    		ipdForm.setCurrent_pregnent(bed.getCurrent_pregnent());
    		ipdForm.setIud(bed.getIud());
    		
    		ipdForm.setLive_boys(bed.getLive_boys());
    		ipdForm.setLive_girls(bed.getLive_girls());
    		ipdForm.setStillbirths(bed.getStillbirths());
    		ipdForm.setAbortions(bed.getAbortions());
    		ipdForm.setDeath_children(bed.getDeath_children());
    		
    		//parity_aboration_notes,p,l,a,d
    		ipdForm.setParity_abortion_notes(bed.getParity_abortion_notes());
    		ipdForm.setP(bed.getP());
    		ipdForm.setL(bed.getL());
    		ipdForm.setA(bed.getA());
    		ipdForm.setD(bed.getD());
    		
    		
    		ipdForm.setLmp(bed.getLmp());
    		ipdForm.setEdd(bed.getEdd());
    		ipdForm.setUsg(bed.getUsg()); 
    		
    		ipdForm.setPasthistory(bed.getPasthistory());
    		ipdForm.setPt_history_notes(bed.getPt_history_notes());
    		ipdForm.setFamily_history(bed.getFamily_history());
    		ipdForm.setFamily_history_notes(bed.getFamily_history_notes());
    		
    		ipdForm.setGravida(bed.getGravida());
    		ipdForm.setPara(bed.getPara());
    		ipdForm.setLive(bed.getLive());
    		ipdForm.setAbortion(bed.getAbortion());
    		ipdForm.setMtp(bed.getMtp());
    		ipdForm.setStill_born(bed.getStill_born());
    		ipdForm.setDeath(bed.getDeath());
    		ipdForm.setHigh_risk_factor(bed.getHigh_risk_factor());
    		
    		ipdForm.setSurgical_ho(bed.getSurgical_ho());
    		
    		ipdForm.setDate1(bed.getDate1());
    		ipdForm.setHb1(bed.getHb1());
    		ipdForm.setFbs1(bed.getFbs1());
    		ipdForm.setDpbs1(bed.getDpbs1());
    		ipdForm.setUrm1(bed.getUrm1());
    		ipdForm.setTsh1(bed.getTsh1());
    		ipdForm.setIct1(bed.getIct1());
    		ipdForm.setGtt1(bed.getGtt1());
    		
    		ipdForm.setDate2(bed.getDate2());
    		ipdForm.setHb2(bed.getHb2());
    		ipdForm.setFbs2(bed.getFbs2());
    		ipdForm.setDpbs2(bed.getDpbs2());
    		ipdForm.setUrm2(bed.getUrm2());
    		ipdForm.setTsh2(bed.getTsh2());
    		ipdForm.setIct2(bed.getIct2());
    		ipdForm.setGtt2(bed.getGtt2());
    		
    		
    		ipdForm.setDate3(bed.getDate3());
    		ipdForm.setHb3(bed.getHb3());
    		ipdForm.setFbs3(bed.getFbs3());
    		ipdForm.setDpbs3(bed.getDpbs3());
    		ipdForm.setUrm3(bed.getUrm3());
    		ipdForm.setTsh3(bed.getTsh3());
    		ipdForm.setIct3(bed.getIct3());
    		ipdForm.setGtt3(bed.getGtt3());
    		
    		ipdForm.setDate4(bed.getDate4());
    		ipdForm.setHb4(bed.getHb4());
    		ipdForm.setFbs4(bed.getFbs4());
    		ipdForm.setDpbs4(bed.getDpbs4());
    		ipdForm.setUrm4(bed.getUrm4());
    		ipdForm.setTsh4(bed.getTsh4());
    		ipdForm.setIct4(bed.getIct4());
    		ipdForm.setGtt4(bed.getGtt4());
    		
    		
    		ArrayList<Diagnosis> diagnosisListGynic= new ArrayList<Diagnosis>();
    		DiagnosisDAO diagnosisDAO= new JDBCDiagnosisDAO(connection);
    		//jitu
    		String dids= bed.getFinaldiagnosis();
    		if(dids!=null){
    			
    			for(String s:dids.split(",")){
    				
    				  if(s==null){
    					  continue;
    				  }
    				
    				  if(s.equals("0")){
    					  continue;
    				  }
    				  Diagnosis diagnosis= diagnosisDAO.getDiagnosisName(s);
    				  diagnosisListGynic.add(diagnosis);
    			}
    			
    		} 
    		
    		ipdForm.setDiagnosisListGynic(diagnosisListGynic);
    		
    		ipdForm.setEarlierinvest(bed.getEarlierinvest());
    		
    		ipdForm.setHv_1m(bed.getHv_1m());
    		ipdForm.setHbs_ag(bed.getHbs_ag());
    		ipdForm.setVdrl(bed.getVdrl());
    		ipdForm.setHb_srecta(bed.getHb_srecta());
    		ipdForm.setHb_ac(bed.getHb_ac());
    		ipdForm.setDuet_markess(bed.getDuet_markess());
    		ipdForm.setTriple(bed.getTriple());
    		ipdForm.setQuadrple_maicers(bed.getQuadrple_maicers());
    		
    		
    		//ivf_date, down_regulation, ovarian_stimulation, os_dosage, sperm_quality, et_day, oocytes_obtained,
    		//oocytes_quality, embroyos_grade, embroyos_transfered, embroyos_description, freezing, transfer_process, betahcgcm, ivf_remark, do_family_history,
    		//ho_fertility_family, ho_genetic_family, ho_premature_family, age_of_menarche, age_of_menarche_notes, dysmenorrhoe, dysmenorrhoe_notes,
    		//flow, flow_notes, sleep_disruption_bleeding, sleep_disruption_bleeding_notes, blachouts, blachouts_notes
    		ipdForm.setIvf_date(bed.getIvf_date());
    		ipdForm.setDown_regulation(bed.getDown_regulation());
    		ipdForm.setOvarian_stimulation(bed.getOvarian_stimulation());
    		ipdForm.setOs_dosage(bed.getOs_dosage());
    		ipdForm.setSperm_quality(bed.getSperm_quality());
    		ipdForm.setEt_day(bed.getEt_day());
    		ipdForm.setOocytes_obtained(bed.getOocytes_obtained());
    		ipdForm.setOocytes_quality(bed.getOocytes_quality());
    		ipdForm.setEmbroyos_grade(bed.getEmbroyos_grade());
    		ipdForm.setEmbroyos_transfered(bed.getEmbroyos_transfered());
    		ipdForm.setEmbroyos_description(bed.getEmbroyos_description());
    		ipdForm.setFreezing(bed.getFreezing());
    		ipdForm.setTransfer_process(bed.getTransfer_process());
    		ipdForm.setBetahcgcm(bed.getBetahcgcm());
    		
    		ipdForm.setIvf_remark(bed.getIvf_remark());
    		ipdForm.setDo_family_history(bed.getDo_family_history());
    		ipdForm.setHo_fertility_family(bed.getHo_fertility_family());
    		ipdForm.setHo_genetic_family(bed.getHo_genetic_family());
    		ipdForm.setHo_premature_family(bed.getHo_premature_family());

    		ipdForm.setAge_of_menarche(bed.getAge_of_menarche());
    		ipdForm.setDysmenorrhoe(bed.getDysmenorrhoe());
    		ipdForm.setDysmenorrhoe_notes(bed.getDysmenorrhoe_notes());
    		ipdForm.setFlow(bed.getFlow());
    		ipdForm.setFlow_notes(bed.getFlow_notes());
    		ipdForm.setSleep_disruption_bleeding(bed.getSleep_disruption_bleeding());
    		ipdForm.setSleep_disruption_bleeding_notes(bed.getSleep_disruption_bleeding_notes());
    		ipdForm.setBlachouts(bed.getBlachouts());
    		ipdForm.setBlachouts_notes(bed.getBlachouts_notes());
    		
    		 //anormaly_scan_11week, cervical_length_11week, double_marker_11week,;
    		ipdForm.setAnormaly_scan_11week(bed.getAnormaly_scan_11week());
    		ipdForm.setCervical_length_11week(bed.getCervical_length_11week());
    		ipdForm.setDouble_marker_11week(bed.getDouble_marker_11week());
			  
			  //all_investigation_16week, sikling_16week, triple_marker_16week, abstinence_1visit;
    		ipdForm.setAll_investigation_16week(bed.getAll_investigation_16week());
    		ipdForm.setSikling_16week(bed.getSikling_16week());
    		ipdForm.setTriple_marker_16week(bed.getTriple_marker_16week());
    		ipdForm.setAbstinence_1visit(bed.getAbstinence_1visit());
			  
			  //barrier_contra_1visit, bed_rest_1visit, book_1visit, csv_1visit, dispi_test_1visit;
    		ipdForm.setBarrier_contra_1visit(bed.getBarrier_contra_1visit());
    		ipdForm.setBed_rest_1visit(bed.getBed_rest_1visit());
    		ipdForm.setBook_1visit(bed.getBook_1visit());
    		ipdForm.setCsv_1visit(bed.getCsv_1visit());
    		ipdForm.setDispi_test_1visit(bed.getDispi_test_1visit());
			  
			  //drug_reaction_1visit, hcg_1visit, heparin_1visit, oral_hygeine_1visit, other_test_1visit, physio_diet_1visit,
    		ipdForm.setDrug_reaction_1visit(bed.getDrug_reaction_1visit());
    		ipdForm.setHcg_1visit(bed.getHcg_1visit());
    		ipdForm.setHeparin_1visit(bed.getHeparin_1visit());
    		ipdForm.setOral_hygeine_1visit(bed.getOral_hygeine_1visit());
    		ipdForm.setOther_test_1visit(bed.getOther_test_1visit());
    		ipdForm.setPhysio_diet_1visit(bed.getPhysio_diet_1visit());
			  
			  //rubelle_status_1visit, smart_doc_1visit, stream_cell_1visit, vaginities_1visit, animally_scan_20week
    		ipdForm.setRubelle_status_1visit(bed.getRubelle_status_1visit());
    		ipdForm.setSmart_doc_1visit(bed.getSmart_doc_1visit());
    		ipdForm.setStream_cell_1visit(bed.getStream_cell_1visit());
    		ipdForm.setVaginities_1visit(bed.getVaginities_1visit());
    		ipdForm.setAnimally_scan_20week(bed.getAnimally_scan_20week());
			  
			  //fetal_eco_20week, anti_d_24week, dipsi_24week, itc_24week, investigation_sos_30week, steroids_30week;
    		ipdForm.setFetal_eco_20week(bed.getFetal_eco_20week());
    		ipdForm.setAnti_d_24week(bed.getAnti_d_24week());
    		ipdForm.setDipsi_24week(bed.getDipsi_24week());
    		ipdForm.setItc_24week(bed.getItc_24week());
    		ipdForm.setInvestigation_sos_30week(bed.getInvestigation_sos_30week());
    		ipdForm.setSteroids_30week(bed.getSteroids_30week());
			  
			  //vaginities_treatment_30week, breast_preparation_34week, color_doppler_34week, labour_counselling_34week, nst_34week, vaginities_treatment_34week
    		ipdForm.setVaginities_treatment_30week(bed.getVaginities_treatment_30week());
    		ipdForm.setBreast_preparation_34week(bed.getBreast_preparation_34week());
    		ipdForm.setColor_doppler_34week(bed.getColor_doppler_34week());
    		ipdForm.setLabour_counselling_34week(bed.getLabour_counselling_34week());
    		ipdForm.setNst_34week(bed.getNst_34week());
    		ipdForm.setVaginities_treatment_34week(bed.getVaginities_treatment_34week());
    		
    		ArrayList<Bed> allVisitReasonList = ipdDAO.getGynicVisitReasonList(ipdForm.getVisit_reason_ids());
    		ipdForm.setAllVisitReasonList(allVisitReasonList);
    		
    		 //nst_date1, nst_date2, nst_date3, nst_date4, nst_date5, nst_date6,
    		ipdForm.setNst_date1(bed.getNst_date1());
    		ipdForm.setNst_date2(bed.getNst_date2());
    		ipdForm.setNst_date3(bed.getNst_date3());
    		ipdForm.setNst_date4(bed.getNst_date4());
    		ipdForm.setNst_date5(bed.getNst_date5());
    		ipdForm.setNst_date6(bed.getNst_date6());
			  
			  //nst_time1, nst_time2, nst_time3, nst_time4, nst_time5, nst_time6,
    		ipdForm.setNst_time1(bed.getNst_time1());
    		ipdForm.setNst_time2(bed.getNst_time2());
    		ipdForm.setNst_time3(bed.getNst_time3());
    		ipdForm.setNst_time4(bed.getNst_time4());
    		ipdForm.setNst_time5(bed.getNst_time5());
    		ipdForm.setNst_time6(bed.getNst_time6());
			  
			  //nst_indication1, nst_indication2, nst_indication3, nst_indication4, nst_indication5, nst_indication6,
    		ipdForm.setNst_indication1(bed.getNst_indication1());
    		ipdForm.setNst_indication2(bed.getNst_indication2());
    		ipdForm.setNst_indication3(bed.getNst_indication3());
    		ipdForm.setNst_indication4(bed.getNst_indication4());
    		ipdForm.setNst_indication5(bed.getNst_indication5());
    		ipdForm.setNst_indication6(bed.getNst_indication6());
			  
			  //nst_duration1, nst_duration2, nst_duration3, nst_duration4, nst_duration5, nst_duration6,
    		ipdForm.setNst_duration1(bed.getNst_duration1());
    		ipdForm.setNst_duration2(bed.getNst_duration2());
    		ipdForm.setNst_duration3(bed.getNst_duration3());
    		ipdForm.setNst_duration4(bed.getNst_duration4());
    		ipdForm.setNst_duration5(bed.getNst_duration5());
    		ipdForm.setNst_duration6(bed.getNst_duration6());
			  
			  //nst_interpretation1, nst_interpretation2, nst_interpretation3, nst_interpretation4, nst_interpretation5, nst_interpretation6,
    		ipdForm.setNst_interpretation1(bed.getNst_interpretation1());
    		ipdForm.setNst_interpretation2(bed.getNst_interpretation2());
    		ipdForm.setNst_interpretation3(bed.getNst_interpretation3());
    		ipdForm.setNst_interpretation4(bed.getNst_interpretation4());
    		ipdForm.setNst_interpretation5(bed.getNst_interpretation5());
    		ipdForm.setNst_interpretation6(bed.getNst_interpretation6());
			  
			  //nst_intervention1, nst_intervention2, nst_intervention3, nst_intervention4, nst_intervention5, nst_intervention6
    		ipdForm.setNst_intervention1(bed.getNst_intervention1());
    		ipdForm.setNst_intervention2(bed.getNst_intervention2());
    		ipdForm.setNst_intervention3(bed.getNst_intervention3());
    		ipdForm.setNst_intervention4(bed.getNst_intervention4());
    		ipdForm.setNst_intervention5(bed.getNst_intervention5());
    		ipdForm.setNst_intervention6(bed.getNst_intervention6());
    		
    		
    		  //tt_dose1, tt_dose2, influenza_vaccine, usgdate1, usgdate2, usgdate3, usgdate4,
    		ipdForm.setTt_dose1(bed.getTt_dose1());
    		ipdForm.setTt_dose2(bed.getTt_dose2());
    		ipdForm.setInfluenza_vaccine(bed.getInfluenza_vaccine());
    		ipdForm.setUsgdate1(bed.getUsgdate1());
    		ipdForm.setUsgdate2(bed.getUsgdate2());
    		ipdForm.setUsgdate3(bed.getUsgdate3());
    		ipdForm.setUsgdate4(bed.getUsgdate4());
			  
			  //amenorrhea1, amenorrhea2, amenorrhea3, amenorrhea4, presentation1, presentation2, presentation3, presentation4,
    		ipdForm.setAmenorrhea1(bed.getAmenorrhea1());
    		ipdForm.setAmenorrhea2(bed.getAmenorrhea2());
    		ipdForm.setAmenorrhea3(bed.getAmenorrhea3());
    		ipdForm.setAmenorrhea4(bed.getAmenorrhea4());
    		ipdForm.setPresentation1(bed.getPresentation1());
    		ipdForm.setPresentation2(bed.getPresentation2());
    		ipdForm.setPresentation3(bed.getPresentation3());
    		ipdForm.setPresentation4(bed.getPresentation4());
			  
			  //bpdgs1, bpdgs2, bpdgs3, bpdgs4, hc1, hc2, hc3, hc4,
    		ipdForm.setBpdgs1(bed.getBpdgs1());
    		ipdForm.setBpdgs2(bed.getBpdgs2());
    		ipdForm.setBpdgs3(bed.getBpdgs3());
    		ipdForm.setBpdgs4(bed.getBpdgs4());
    		ipdForm.setHc1(bed.getHc1());
    		ipdForm.setHc2(bed.getHc1());
    		ipdForm.setHc3(bed.getHc1());
    		ipdForm.setHc4(bed.getHc1());
			  
			  //ac1, ac2, ac3, ac4, flcrl1, flcrl2, flcrl3, flcrl4,
    		ipdForm.setAc1(bed.getAc1());
    		ipdForm.setAc2(bed.getAc2());
    		ipdForm.setAc3(bed.getAc3());
    		ipdForm.setAc4(bed.getAc4());
    		ipdForm.setFlcrl1(bed.getFlcrl1());
    		ipdForm.setFlcrl2(bed.getFlcrl2());
    		ipdForm.setFlcrl3(bed.getFlcrl3());
    		ipdForm.setFlcrl4(bed.getFlcrl4());
			  
			  //ga_usg1, ga_usg2, ga_usg3, ga_usg4, liquor1, liquor2, liquor3, liquor4,
    		ipdForm.setGa_usg1(bed.getGa_usg1());
			ipdForm.setGa_usg2(bed.getGa_usg2());
			ipdForm.setGa_usg3(bed.getGa_usg3());
			ipdForm.setGa_usg4(bed.getGa_usg4());
			ipdForm.setLiquor1(bed.getLiquor1());
			ipdForm.setLiquor2(bed.getLiquor2());
			ipdForm.setLiquor3(bed.getLiquor3());
			ipdForm.setLiquor4(bed.getLiquor4());
			  
			  //placenta1, placenta2, placenta3, placenta4, foetal_weight1, foetal_weight2, foetal_weight3, foetal_weight4,
			ipdForm.setPlacenta1(bed.getPlacenta1());
			ipdForm.setPlacenta2(bed.getPlacenta2());
			ipdForm.setPlacenta3(bed.getPlacenta3());
			ipdForm.setPlacenta4(bed.getPlacenta4());
			ipdForm.setFoetal_weight1(bed.getFoetal_weight1());
			ipdForm.setFoetal_weight2(bed.getFoetal_weight2());
			ipdForm.setFoetal_weight3(bed.getFoetal_weight3());
			ipdForm.setFoetal_weight4(bed.getFoetal_weight4());
			  
			  //cervical_length1, cervical_length2, cervical_length3, cervical_length4, nt_scan, anomaly_scan, colour_doppler_scan
			ipdForm.setCervical_length1(bed.getCervical_length1());
			ipdForm.setCervical_length2(bed.getCervical_length2());
			ipdForm.setCervical_length3(bed.getCervical_length3());
			ipdForm.setCervical_length4(bed.getCervical_length4());
			ipdForm.setNt_scan(bed.getNt_scan());
			ipdForm.setAnomaly_scan(bed.getAnomaly_scan());
			ipdForm.setColour_doppler_scan(bed.getColour_doppler_scan());  
    		
			 //gen_condition, temp, pallor, pedal_edema, pulse, bmi, height,weight, sys_bp, dia_bp, wall_edema, fundal_height
			ipdForm.setGen_condition(bed.getGen_condition());
			ipdForm.setTemp(bed.getTemp());
			ipdForm.setPallor(bed.getPallor());
			ipdForm.setPedal_edema(bed.getPedal_edema());
			ipdForm.setPulse(bed.getPulse());
			ipdForm.setBmi(bed.getBmi());
			ipdForm.setHeight(bed.getHeight());
			ipdForm.setWeight(bed.getWeight() );
			ipdForm.setSys_bp(bed.getSys_bp());
			ipdForm.setDia_bp(bed.getDia_bp());
			ipdForm.setWall_edema(bed.getWall_edema());
			ipdForm.setFundal_height(bed.getFundal_height());
			  
			  //cephalic, breech, baley_size, transverse_fhs, liquor, uterine_contractions, ps_cervix, ps_vagine,
			ipdForm.setCephalic(bed.getCephalic());
			ipdForm.setBreech(bed.getBreech());
			ipdForm.setBaley_size(bed.getBaley_size());
			ipdForm.setTransverse_fhs(bed.getTransverse_fhs());
			ipdForm.setLiquor(bed.getLiquor());
			ipdForm.setUterine_contractions(bed.getUterine_contractions());
			ipdForm.setPs_cervix(bed.getPs_cervix());
			ipdForm.setPs_vagine(bed.getPs_vagine());
			  
			  //ps_vault, pv_trim, pv_unettacced, pv_ant, pv_os, pv_membranes, pv_tubular, pv_just_ettacced, pv_mid_posits, pv_soft, pv_ettacced, pv_post
			ipdForm.setPs_vault(bed.getPs_vault());
			ipdForm.setPv_trim(bed.getPv_trim());
			ipdForm.setPv_unettacced(bed.getPv_unettacced());
			ipdForm.setPv_ant(bed.getPv_ant());
			ipdForm.setPv_os(bed.getPv_os());
			ipdForm.setPv_membranes(bed.getPv_membranes());
			ipdForm.setPv_tubular(bed.getPv_tubular());
			ipdForm.setPv_just_ettacced(bed.getPv_just_ettacced());
			ipdForm.setPv_mid_posits(bed.getPv_mid_posits());
			ipdForm.setPv_soft(bed.getPv_soft());
			ipdForm.setPv_ettacced(bed.getPv_ettacced());
			ipdForm.setPv_post(bed.getPv_post()); 
			  
			  //ps_fhs,pv_membrane,pv_station,pv_liquor,pv_pelvis,pv_position;
			ipdForm.setPs_fhs(bed.getPs_fhs());
			ipdForm.setPv_membrane(bed.getPv_membrane());
			ipdForm.setPv_liquor(bed.getPv_liquor());
			ipdForm.setPv_pelvis(bed.getPv_pelvis());
			ipdForm.setPv_position(bed.getPv_position());
			ipdForm.setPv_station(bed.getPv_station());
			
			ipdForm.setBeats_min(bed.getBeats_min());
			
			//pmp=?, diagnosisgyn=?, le_vulva=?, le_vagina=?, pa_gynic=?, plan=?, finaldiagnosis=?,
			ipdForm.setPmp(bed.getPmp());
			ipdForm.setDiagnosisgyn(bed.getDiagnosisgyn());
			ipdForm.setLe_vulva(bed.getLe_vulva());
			ipdForm.setLe_vagina(bed.getLe_vagina());
			ipdForm.setPa_gynic(bed.getPa_gynic());
			ipdForm.setPlan(bed.getPlan());
			ipdForm.setFinaldiagnosis(bed.getFinaldiagnosis());
			
			//priscription=? , pv_uterus=? , pv_uterus_size=? , pv_bl_fomices=?, pv_mobility=?
			ipdForm.setPriscription(bed.getPriscription());
			ipdForm.setPv_uterus(bed.getPv_uterus());
			ipdForm.setPv_uterus_size(bed.getPv_uterus_size());
			ipdForm.setPv_bl_fomices(bed.getPv_bl_fomices());
			ipdForm.setPv_mobility(bed.getPv_mobility());
			
			ipdForm.setFormtype(formtype);
			
			ArrayList<Bed> gynicobsList= bedDao.getGynicObsListByGynicId(id);
    		ipdForm.setGynicobsList(gynicobsList);
    		
			
    		String chief_comlint_id=masterDAO.getIpdTemplateId("Chief Complaints");
			String present_ill_id=masterDAO.getIpdTemplateId("Present Illness");
			String past_history_id=masterDAO.getIpdTemplateId("Past History");
			String family_hist_id=masterDAO.getIpdTemplateId("Family History");
			String personal_hist_id=masterDAO.getIpdTemplateId("Personal History");
			String onexami_id=masterDAO.getIpdTemplateId("On Examination");
			String tretment_given_id=masterDAO.getIpdTemplateId("Treatment Given");
			
			ArrayList<Master> chief_complaints_list=masterDAO.getIpdTemplateListNames(chief_comlint_id);
			ArrayList<Master> present_illness_list=masterDAO.getIpdTemplateListNames(present_ill_id);
			ArrayList<Master> past_history_list=masterDAO.getIpdTemplateListNames(past_history_id);
			ArrayList<Master> family_history_list=masterDAO.getIpdTemplateListNames(family_hist_id);
			ArrayList<Master> personal_hist_list=masterDAO.getIpdTemplateListNames(personal_hist_id);
			ArrayList<Master> on_exam_list=masterDAO.getIpdTemplateListNames(onexami_id);
			ArrayList<Master> treatment_given_list=masterDAO.getIpdTemplateListNames(tretment_given_id);
			ipdForm.setChief_complaints_list(chief_complaints_list);
			ipdForm.setPresent_illness_list(present_illness_list);
			ipdForm.setPast_history_list(past_history_list);
			ipdForm.setFamily_history_list(family_history_list);
			ipdForm.setPersonal_hist_list(personal_hist_list);
			ipdForm.setOn_exam_list(on_exam_list);
			ipdForm.setTreatment_given_list(treatment_given_list);
			
			
			ArrayList<Bed> wardlist=bedDao.getAllWardList("0");
			ipdForm.setWardlist(wardlist);
			
		} catch (Exception e) {

			e.printStackTrace();
		}
    	finally {
    		connection.close();
    	}
    	
    	return "editgynicform";
    }
    
    
public String printgynicform() throws Exception{
    	
    	Connection connection=null;
    	
    	try {
    		connection=Connection_provider.getconnection();
    		String id= request.getParameter("selectedid"); 
    		
    		IpdDAO ipdDAO= new JDBCIpdDAO(connection);
    		MasterDAO masterDAO= new JDBCMasterDAO(connection);
    		BedDao bedDao= new JDBCBedDao(connection);
    		
    		Bed bed= ipdDAO.getEditGynicFormData(id);
    		ClientDAO clientDAO =new JDBCClientDAO(connection);
    		Client client=clientDAO.getClientDetails(bed.getClientid());
    		
    		String fullname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();
    		ipdForm.setClient(fullname);
    		ipdForm.setRelativename(client.getEmergencyContName());
    		ipdForm.setRelationcont(client.getEmergencyContNo());
    		ipdForm.setRelation(client.getRelation());
    		//String numcount=ipdDAO.getNumofAdmissionCount(ipdForm.getClientid());
    		ipdForm.setContact(client.getMobNo());
    		//ipdForm.setNum_admission(numcount);
    		ipdForm.setDob(client.getDob());
    		ipdForm.setTpid(client.getTypeName());
    		ipdForm.setAddress(client.getAddress());
    		ipdForm.setAbrivationid(client.getAbrivationid());
    		
    		String age = DateTimeUtils.getAge(client.getDob());
    		String agegender = age + "Years" + " / " + client.getGender();
    		ipdForm.setAgegender(agegender);
    		
    		ArrayList<Diagnosis> diagnosisListGynic= new ArrayList<Diagnosis>();
    		DiagnosisDAO diagnosisDAO= new JDBCDiagnosisDAO(connection);
    		//jitu
    		String dids= bed.getFinaldiagnosis();
    		if(dids!=null){
    			
    			for(String s:dids.split(",")){
    				
    				  if(s==null){
    					  continue;
    				  }
    				
    				  if(s.equals("0")){
    					  continue;
    				  }
    				  Diagnosis diagnosis= diagnosisDAO.getDiagnosisName(s);
    				  diagnosisListGynic.add(diagnosis);
    			}
    			
    		} 
    		
    		ipdForm.setDiagnosisListGynic(diagnosisListGynic);
    		
    		
    		
    		ipdForm.setId(bed.getId());
    		ipdForm.setLastmodified(bed.getCommencing());
    		ipdForm.setAlcohal(bed.getAlcohal());
    		ipdForm.setDrugs(bed.getDrugs());
    		ipdForm.setOther_medication(bed.getOther_medication());
    		ipdForm.setTobaco(bed.getTobaco());
    		ipdForm.setTobaconotes(bed.getTobaconotes());
    		ipdForm.setSmoking(bed.getSmoking());
    		
    		ipdForm.setAge_menarche(bed.getAge_menarche());
    		ipdForm.setLmp(bed.getLmp());
    		ipdForm.setLlmp(bed.getLlmp());
    		ipdForm.setPmc(bed.getPmc());
    		
    		
    		ipdForm.setCycle_length(bed.getCycle_length());
    		ipdForm.setDuration_flow(bed.getDuration_flow());
    		ipdForm.setAmount_flow(bed.getAmount_flow());
    		ipdForm.setDysmenorrhea(bed.getDysmenorrhea());
    		ipdForm.setDyspareunia(bed.getDyspareunia());
    		ipdForm.setHopassing_clots(bed.getHopassing_clots());
    		
    		ipdForm.setWhite_disc_itching(bed.getWhite_disc_itching());
    		ipdForm.setIntercourse_freq(bed.getIntercourse_freq());
    		ipdForm.setGalactorrea(bed.getGalactorrea());
    		
    		ipdForm.setHo_contraception(bed.getHo_contraception());
    		ipdForm.setRubella_vaccine(bed.getRubella_vaccine());
    		ipdForm.setMenopause(bed.getMenopause());
    		ipdForm.setNulligravida(bed.getNulligravida());
    		ipdForm.setCurrent_pregnent(bed.getCurrent_pregnent());
    		ipdForm.setIud(bed.getIud());
    		
    		ipdForm.setLive_boys(bed.getLive_boys());
    		ipdForm.setLive_girls(bed.getLive_girls());
    		ipdForm.setStillbirths(bed.getStillbirths());
    		ipdForm.setAbortions(bed.getAbortions());
    		ipdForm.setDeath_children(bed.getDeath_children());
    		
    		//parity_aboration_notes,p,l,a,d
    		ipdForm.setParity_abortion_notes(bed.getParity_abortion_notes());
    		ipdForm.setP(bed.getP());
    		ipdForm.setL(bed.getL());
    		ipdForm.setA(bed.getA());
    		ipdForm.setD(bed.getD());
    		
    		
    		ipdForm.setLmp(bed.getLmp());
    		ipdForm.setEdd(bed.getEdd());
    		ipdForm.setUsg(bed.getUsg()); 
    		ipdForm.setGravida(bed.getGravida());
    		ipdForm.setPara(bed.getPara());
    		ipdForm.setLive(bed.getLive());
    		ipdForm.setAbortion(bed.getAbortion());
    		ipdForm.setMtp(bed.getMtp());
    		ipdForm.setStill_born(bed.getStill_born());
    		ipdForm.setDeath(bed.getDeath());
    		ipdForm.setHigh_risk_factor(bed.getHigh_risk_factor());
    		
    		ipdForm.setSurgical_ho(bed.getSurgical_ho());
    		
    		ipdForm.setDate1(bed.getDate1());
    		ipdForm.setHb1(bed.getHb1());
    		ipdForm.setFbs1(bed.getFbs1());
    		ipdForm.setDpbs1(bed.getDpbs1());
    		ipdForm.setUrm1(bed.getUrm1());
    		ipdForm.setTsh1(bed.getTsh1());
    		ipdForm.setIct1(bed.getIct1());
    		ipdForm.setGtt1(bed.getGtt1());
    		
    		ipdForm.setDate2(bed.getDate2());
    		ipdForm.setHb2(bed.getHb2());
    		ipdForm.setFbs2(bed.getFbs2());
    		ipdForm.setDpbs2(bed.getDpbs2());
    		ipdForm.setUrm2(bed.getUrm2());
    		ipdForm.setTsh2(bed.getTsh2());
    		ipdForm.setIct2(bed.getIct2());
    		ipdForm.setGtt2(bed.getGtt2());
    		
    		
    		ipdForm.setDate3(bed.getDate3());
    		ipdForm.setHb3(bed.getHb3());
    		ipdForm.setFbs3(bed.getFbs3());
    		ipdForm.setDpbs3(bed.getDpbs3());
    		ipdForm.setUrm3(bed.getUrm3());
    		ipdForm.setTsh3(bed.getTsh3());
    		ipdForm.setIct3(bed.getIct3());
    		ipdForm.setGtt3(bed.getGtt3());
    		
    		ipdForm.setDate4(bed.getDate4());
    		ipdForm.setHb4(bed.getHb4());
    		ipdForm.setFbs4(bed.getFbs4());
    		ipdForm.setDpbs4(bed.getDpbs4());
    		ipdForm.setUrm4(bed.getUrm4());
    		ipdForm.setTsh4(bed.getTsh4());
    		ipdForm.setIct4(bed.getIct4());
    		ipdForm.setGtt4(bed.getGtt4());
    		
    		ipdForm.setEarlierinvest(bed.getEarlierinvest());
    		
    		ipdForm.setHv_1m(bed.getHv_1m());
    		ipdForm.setHbs_ag(bed.getHbs_ag());
    		ipdForm.setVdrl(bed.getVdrl());
    		ipdForm.setHb_srecta(bed.getHb_srecta());
    		ipdForm.setHb_ac(bed.getHb_ac());
    		ipdForm.setDuet_markess(bed.getDuet_markess());
    		ipdForm.setTriple(bed.getTriple());
    		ipdForm.setQuadrple_maicers(bed.getQuadrple_maicers());
    		
    		ipdForm.setVisit_reason_ids(bed.getVisit_reason_ids());
    		
    		ArrayList<Bed> allVisitReasonList = ipdDAO.getGynicVisitReasonList(ipdForm.getVisit_reason_ids());
    		ipdForm.setAllVisitReasonList(allVisitReasonList);
    		
    		//ivf_date, down_regulation, ovarian_stimulation, os_dosage, sperm_quality, et_day, oocytes_obtained,
    		//oocytes_quality, embroyos_grade, embroyos_transfered, embroyos_description, freezing, transfer_process, betahcgcm, ivf_remark, do_family_history,
    		//ho_fertility_family, ho_genetic_family, ho_premature_family, age_of_menarche, age_of_menarche_notes, dysmenorrhoe, dysmenorrhoe_notes,
    		//flow, flow_notes, sleep_disruption_bleeding, sleep_disruption_bleeding_notes, blachouts, blachouts_notes
    		ipdForm.setIvf_date(bed.getIvf_date());
    		ipdForm.setDown_regulation(bed.getDown_regulation());
    		ipdForm.setOvarian_stimulation(bed.getOvarian_stimulation());
    		ipdForm.setOs_dosage(bed.getOs_dosage());
    		ipdForm.setSperm_quality(bed.getSperm_quality());
    		ipdForm.setEt_day(bed.getEt_day());
    		ipdForm.setOocytes_obtained(bed.getOocytes_obtained());
    		ipdForm.setOocytes_quality(bed.getOocytes_quality());
    		ipdForm.setEmbroyos_grade(bed.getEmbroyos_grade());
    		ipdForm.setEmbroyos_transfered(bed.getEmbroyos_transfered());
    		ipdForm.setEmbroyos_description(bed.getEmbroyos_description());
    		ipdForm.setFreezing(bed.getFreezing());
    		ipdForm.setTransfer_process(bed.getTransfer_process());
    		ipdForm.setBetahcgcm(bed.getBetahcgcm());
    		
    		ipdForm.setIvf_remark(bed.getIvf_remark());
    		ipdForm.setDo_family_history(bed.getDo_family_history());
    		ipdForm.setHo_fertility_family(bed.getHo_fertility_family());
    		ipdForm.setHo_genetic_family(bed.getHo_genetic_family());
    		ipdForm.setHo_premature_family(bed.getHo_premature_family());
    		ipdForm.setAge_of_menarche(bed.getAge_of_menarche());
    		ipdForm.setDysmenorrhoe(bed.getDysmenorrhoe());
    		ipdForm.setDysmenorrhoe_notes(bed.getDysmenorrhoe_notes());
    		ipdForm.setFlow(bed.getFlow());
    		ipdForm.setFlow_notes(bed.getFlow_notes());
    		ipdForm.setSleep_disruption_bleeding(bed.getSleep_disruption_bleeding());
    		ipdForm.setSleep_disruption_bleeding_notes(bed.getSleep_disruption_bleeding_notes());
    		ipdForm.setBlachouts(bed.getBlachouts());
    		ipdForm.setBlachouts_notes(bed.getBlachouts_notes());
    		
    		 //anormaly_scan_11week, cervical_length_11week, double_marker_11week,;
    		ipdForm.setAnormaly_scan_11week(bed.getAnormaly_scan_11week());
    		ipdForm.setCervical_length_11week(bed.getCervical_length_11week());
    		ipdForm.setDouble_marker_11week(bed.getDouble_marker_11week());
			  
			  //all_investigation_16week, sikling_16week, triple_marker_16week, abstinence_1visit;
    		ipdForm.setAll_investigation_16week(bed.getAll_investigation_16week());
    		ipdForm.setSikling_16week(bed.getSikling_16week());
    		ipdForm.setTriple_marker_16week(bed.getTriple_marker_16week());
    		ipdForm.setAbstinence_1visit(bed.getAbstinence_1visit());
			  
			  //barrier_contra_1visit, bed_rest_1visit, book_1visit, csv_1visit, dispi_test_1visit;
    		ipdForm.setBarrier_contra_1visit(bed.getBarrier_contra_1visit());
    		ipdForm.setBed_rest_1visit(bed.getBed_rest_1visit());
    		ipdForm.setBook_1visit(bed.getBook_1visit());
    		ipdForm.setCsv_1visit(bed.getCsv_1visit());
    		ipdForm.setDispi_test_1visit(bed.getDispi_test_1visit());
			  
			  //drug_reaction_1visit, hcg_1visit, heparin_1visit, oral_hygeine_1visit, other_test_1visit, physio_diet_1visit,
    		ipdForm.setDrug_reaction_1visit(bed.getDrug_reaction_1visit());
    		ipdForm.setHcg_1visit(bed.getHcg_1visit());
    		ipdForm.setHeparin_1visit(bed.getHeparin_1visit());
    		ipdForm.setOral_hygeine_1visit(bed.getOral_hygeine_1visit());
    		ipdForm.setOther_test_1visit(bed.getOther_test_1visit());
    		ipdForm.setPhysio_diet_1visit(bed.getPhysio_diet_1visit());
			  
			  //rubelle_status_1visit, smart_doc_1visit, stream_cell_1visit, vaginities_1visit, animally_scan_20week
    		ipdForm.setRubelle_status_1visit(bed.getRubelle_status_1visit());
    		ipdForm.setSmart_doc_1visit(bed.getSmart_doc_1visit());
    		ipdForm.setStream_cell_1visit(bed.getStream_cell_1visit());
    		ipdForm.setVaginities_1visit(bed.getVaginities_1visit());
    		ipdForm.setAnimally_scan_20week(bed.getAnimally_scan_20week());
			  
			  //fetal_eco_20week, anti_d_24week, dipsi_24week, itc_24week, investigation_sos_30week, steroids_30week;
    		ipdForm.setFetal_eco_20week(bed.getFetal_eco_20week());
    		ipdForm.setAnti_d_24week(bed.getAnti_d_24week());
    		ipdForm.setDipsi_24week(bed.getDipsi_24week());
    		ipdForm.setItc_24week(bed.getItc_24week());
    		ipdForm.setInvestigation_sos_30week(bed.getInvestigation_sos_30week());
    		ipdForm.setSteroids_30week(bed.getSteroids_30week());
			  
			  //vaginities_treatment_30week, breast_preparation_34week, color_doppler_34week, labour_counselling_34week, nst_34week, vaginities_treatment_34week
    		ipdForm.setVaginities_treatment_30week(bed.getVaginities_treatment_30week());
    		ipdForm.setBreast_preparation_34week(bed.getBreast_preparation_34week());
    		ipdForm.setColor_doppler_34week(bed.getColor_doppler_34week());
    		ipdForm.setLabour_counselling_34week(bed.getLabour_counselling_34week());
    		ipdForm.setNst_34week(bed.getNst_34week());
    		ipdForm.setVaginities_treatment_34week(bed.getVaginities_treatment_34week());
    		
    		  //nst_date1, nst_date2, nst_date3, nst_date4, nst_date5, nst_date6,
    		ipdForm.setNst_date1(bed.getNst_date1());
    		ipdForm.setNst_date2(bed.getNst_date2());
    		ipdForm.setNst_date3(bed.getNst_date3());
    		ipdForm.setNst_date4(bed.getNst_date4());
    		ipdForm.setNst_date5(bed.getNst_date5());
    		ipdForm.setNst_date6(bed.getNst_date6());
			  
			  //nst_time1, nst_time2, nst_time3, nst_time4, nst_time5, nst_time6,
    		ipdForm.setNst_time1(bed.getNst_time1());
    		ipdForm.setNst_time2(bed.getNst_time2());
    		ipdForm.setNst_time3(bed.getNst_time3());
    		ipdForm.setNst_time4(bed.getNst_time4());
    		ipdForm.setNst_time5(bed.getNst_time5());
    		ipdForm.setNst_time6(bed.getNst_time6());
			  
			  //nst_indication1, nst_indication2, nst_indication3, nst_indication4, nst_indication5, nst_indication6,
    		ipdForm.setNst_indication1(bed.getNst_indication1());
    		ipdForm.setNst_indication2(bed.getNst_indication2());
    		ipdForm.setNst_indication3(bed.getNst_indication3());
    		ipdForm.setNst_indication4(bed.getNst_indication4());
    		ipdForm.setNst_indication5(bed.getNst_indication5());
    		ipdForm.setNst_indication6(bed.getNst_indication6());
			  
			  //nst_duration1, nst_duration2, nst_duration3, nst_duration4, nst_duration5, nst_duration6,
    		ipdForm.setNst_duration1(bed.getNst_duration1());
    		ipdForm.setNst_duration2(bed.getNst_duration2());
    		ipdForm.setNst_duration3(bed.getNst_duration3());
    		ipdForm.setNst_duration4(bed.getNst_duration4());
    		ipdForm.setNst_duration5(bed.getNst_duration5());
    		ipdForm.setNst_duration6(bed.getNst_duration6());
			  
			  //nst_interpretation1, nst_interpretation2, nst_interpretation3, nst_interpretation4, nst_interpretation5, nst_interpretation6,
    		ipdForm.setNst_interpretation1(bed.getNst_interpretation1());
    		ipdForm.setNst_interpretation2(bed.getNst_interpretation2());
    		ipdForm.setNst_interpretation3(bed.getNst_interpretation3());
    		ipdForm.setNst_interpretation4(bed.getNst_interpretation4());
    		ipdForm.setNst_interpretation5(bed.getNst_interpretation5());
    		ipdForm.setNst_interpretation6(bed.getNst_interpretation6());
			  
			  //nst_intervention1, nst_intervention2, nst_intervention3, nst_intervention4, nst_intervention5, nst_intervention6
    		ipdForm.setNst_intervention1(bed.getNst_intervention1());
    		ipdForm.setNst_intervention2(bed.getNst_intervention2());
    		ipdForm.setNst_intervention3(bed.getNst_intervention3());
    		ipdForm.setNst_intervention4(bed.getNst_intervention4());
    		ipdForm.setNst_intervention5(bed.getNst_intervention5());
    		ipdForm.setNst_intervention6(bed.getNst_intervention6());
    		
    		  //tt_dose1, tt_dose2, influenza_vaccine, usgdate1, usgdate2, usgdate3, usgdate4,
    		ipdForm.setTt_dose1(bed.getTt_dose1());
    		ipdForm.setTt_dose2(bed.getTt_dose2());
    		ipdForm.setInfluenza_vaccine(bed.getInfluenza_vaccine());
    		ipdForm.setUsgdate1(bed.getUsgdate1());
    		ipdForm.setUsgdate2(bed.getUsgdate2());
    		ipdForm.setUsgdate3(bed.getUsgdate3());
    		ipdForm.setUsgdate4(bed.getUsgdate4());
			  
			  //amenorrhea1, amenorrhea2, amenorrhea3, amenorrhea4, presentation1, presentation2, presentation3, presentation4,
    		ipdForm.setAmenorrhea1(bed.getAmenorrhea1());
    		ipdForm.setAmenorrhea2(bed.getAmenorrhea2());
    		ipdForm.setAmenorrhea3(bed.getAmenorrhea3());
    		ipdForm.setAmenorrhea4(bed.getAmenorrhea4());
    		ipdForm.setPresentation1(bed.getPresentation1());
    		ipdForm.setPresentation2(bed.getPresentation2());
    		ipdForm.setPresentation3(bed.getPresentation3());
    		ipdForm.setPresentation4(bed.getPresentation4());
			  
			  //bpdgs1, bpdgs2, bpdgs3, bpdgs4, hc1, hc2, hc3, hc4,
    		ipdForm.setBpdgs1(bed.getBpdgs1());
    		ipdForm.setBpdgs2(bed.getBpdgs2());
    		ipdForm.setBpdgs3(bed.getBpdgs3());
    		ipdForm.setBpdgs4(bed.getBpdgs4());
    		ipdForm.setHc1(bed.getHc1());
    		ipdForm.setHc2(bed.getHc1());
    		ipdForm.setHc3(bed.getHc1());
    		ipdForm.setHc4(bed.getHc1());
			  
			  //ac1, ac2, ac3, ac4, flcrl1, flcrl2, flcrl3, flcrl4,
    		ipdForm.setAc1(bed.getAc1());
    		ipdForm.setAc2(bed.getAc2());
    		ipdForm.setAc3(bed.getAc3());
    		ipdForm.setAc4(bed.getAc4());
    		ipdForm.setFlcrl1(bed.getFlcrl1());
    		ipdForm.setFlcrl2(bed.getFlcrl2());
    		ipdForm.setFlcrl3(bed.getFlcrl3());
    		ipdForm.setFlcrl4(bed.getFlcrl4());
			  
			  //ga_usg1, ga_usg2, ga_usg3, ga_usg4, liquor1, liquor2, liquor3, liquor4,
    		ipdForm.setGa_usg1(bed.getGa_usg1());
			ipdForm.setGa_usg2(bed.getGa_usg2());
			ipdForm.setGa_usg3(bed.getGa_usg3());
			ipdForm.setGa_usg4(bed.getGa_usg4());
			ipdForm.setLiquor1(bed.getLiquor1());
			ipdForm.setLiquor2(bed.getLiquor2());
			ipdForm.setLiquor3(bed.getLiquor3());
			ipdForm.setLiquor4(bed.getLiquor4());
			  
			  //placenta1, placenta2, placenta3, placenta4, foetal_weight1, foetal_weight2, foetal_weight3, foetal_weight4,
			ipdForm.setPlacenta1(bed.getPlacenta1());
			ipdForm.setPlacenta2(bed.getPlacenta2());
			ipdForm.setPlacenta3(bed.getPlacenta3());
			ipdForm.setPlacenta4(bed.getPlacenta4());
			ipdForm.setFoetal_weight1(bed.getFoetal_weight1());
			ipdForm.setFoetal_weight2(bed.getFoetal_weight2());
			ipdForm.setFoetal_weight3(bed.getFoetal_weight3());
			ipdForm.setFoetal_weight4(bed.getFoetal_weight4());
			  
			  //cervical_length1, cervical_length2, cervical_length3, cervical_length4, nt_scan, anomaly_scan, colour_doppler_scan
			ipdForm.setCervical_length1(bed.getCervical_length1());
			ipdForm.setCervical_length2(bed.getCervical_length2());
			ipdForm.setCervical_length3(bed.getCervical_length3());
			ipdForm.setCervical_length4(bed.getCervical_length4());
			ipdForm.setNt_scan(bed.getNt_scan());
			ipdForm.setAnomaly_scan(bed.getAnomaly_scan());
			ipdForm.setColour_doppler_scan(bed.getColour_doppler_scan());  
    		
			  //gen_condition, temp, pallor, pedal_edema, pulse, bmi, height,weight, sys_bp, dia_bp, wall_edema, fundal_height
			ipdForm.setGen_condition(bed.getGen_condition());
			ipdForm.setTemp(bed.getTemp());
			ipdForm.setPallor(bed.getPallor());
			ipdForm.setPedal_edema(bed.getPedal_edema());
			ipdForm.setPulse(bed.getPulse());
			ipdForm.setBmi(bed.getBmi());
			ipdForm.setHeight(bed.getHeight());
			ipdForm.setWeight(bed.getWeight() );
			ipdForm.setSys_bp(bed.getSys_bp());
			ipdForm.setDia_bp(bed.getDia_bp());
			ipdForm.setWall_edema(bed.getWall_edema());
			ipdForm.setFundal_height(bed.getFundal_height());
			  
			  //cephalic, breech, baley_size, transverse_fhs, liquor, uterine_contractions, ps_cervix, ps_vagine,
			ipdForm.setCephalic(bed.getCephalic());
			ipdForm.setBreech(bed.getBreech());
			ipdForm.setBaley_size(bed.getBaley_size());
			ipdForm.setTransverse_fhs(bed.getTransverse_fhs());
			ipdForm.setLiquor(bed.getLiquor());
			ipdForm.setUterine_contractions(bed.getUterine_contractions());
			ipdForm.setPs_cervix(bed.getPs_cervix());
			ipdForm.setPs_vagine(bed.getPs_vagine());
			  
			  //ps_vault, pv_trim, pv_unettacced, pv_ant, pv_os, pv_membranes, pv_tubular, pv_just_ettacced, pv_mid_posits, pv_soft, pv_ettacced, pv_post
			ipdForm.setPs_vault(bed.getPs_vault());
			ipdForm.setPv_trim(bed.getPv_trim());
			ipdForm.setPv_unettacced(bed.getPv_unettacced());
			ipdForm.setPv_ant(bed.getPv_ant());
			ipdForm.setPv_os(bed.getPv_os());
			ipdForm.setPv_membranes(bed.getPv_membranes());
			ipdForm.setPv_tubular(bed.getPv_tubular());
			ipdForm.setPv_just_ettacced(bed.getPv_just_ettacced());
			ipdForm.setPv_mid_posits(bed.getPv_mid_posits());
			ipdForm.setPv_soft(bed.getPv_soft());
			ipdForm.setPv_ettacced(bed.getPv_ettacced());
			ipdForm.setPv_post(bed.getPv_post()); 
    		
			  //ps_fhs,pv_membrane,pv_station,pv_liquor,pv_pelvis,pv_position;
					ipdForm.setPs_fhs(bed.getPs_fhs());
					ipdForm.setPv_membrane(bed.getPv_membrane());
					ipdForm.setPv_liquor(bed.getPv_liquor());
					ipdForm.setPv_pelvis(bed.getPv_pelvis());
					ipdForm.setPv_position(bed.getPv_position());
					ipdForm.setPv_station(bed.getPv_station());
					
					ipdForm.setBeats_min(bed.getBeats_min());
			
					//pmp=?, diagnosisgyn=?, le_vulva=?, le_vagina=?, pa_gynic=?, plan=?, finaldiagnosis=?,
					ipdForm.setPmp(bed.getPmp());
					ipdForm.setDiagnosisgyn(bed.getDiagnosisgyn());
					ipdForm.setLe_vulva(bed.getLe_vulva());
					ipdForm.setLe_vagina(bed.getLe_vagina());
					ipdForm.setPa_gynic(bed.getPa_gynic());
					ipdForm.setPlan(bed.getPlan());
					ipdForm.setFinaldiagnosis(bed.getFinaldiagnosis());
					
					//priscription=? , pv_uterus=? , pv_uterus_size=? , pv_bl_fomices=?, pv_mobility=?
					ipdForm.setPriscription(bed.getPriscription());
					ipdForm.setPv_uterus(bed.getPv_uterus());
					ipdForm.setPv_uterus_size(bed.getPv_uterus_size());
					ipdForm.setPv_bl_fomices(bed.getPv_bl_fomices());
					ipdForm.setPv_mobility(bed.getPv_mobility());
							
					
					ArrayList<Bed> gynicobsList= bedDao.getGynicObsListByGynicId(id);
		    		ipdForm.setGynicobsList(gynicobsList);
		    		
			
    		session.setAttribute("bed", bed);
    		
    		String chief_comlint_id=masterDAO.getIpdTemplateId("Chief Complaints");
			String present_ill_id=masterDAO.getIpdTemplateId("Present Illness");
			String past_history_id=masterDAO.getIpdTemplateId("Past History");
			String family_hist_id=masterDAO.getIpdTemplateId("Family History");
			String personal_hist_id=masterDAO.getIpdTemplateId("Personal History");
			String onexami_id=masterDAO.getIpdTemplateId("On Examination");
			String tretment_given_id=masterDAO.getIpdTemplateId("Treatment Given");
			
			ArrayList<Master> chief_complaints_list=masterDAO.getIpdTemplateListNames(chief_comlint_id);
			ArrayList<Master> present_illness_list=masterDAO.getIpdTemplateListNames(present_ill_id);
			ArrayList<Master> past_history_list=masterDAO.getIpdTemplateListNames(past_history_id);
			ArrayList<Master> family_history_list=masterDAO.getIpdTemplateListNames(family_hist_id);
			ArrayList<Master> personal_hist_list=masterDAO.getIpdTemplateListNames(personal_hist_id);
			ArrayList<Master> on_exam_list=masterDAO.getIpdTemplateListNames(onexami_id);
			ArrayList<Master> treatment_given_list=masterDAO.getIpdTemplateListNames(tretment_given_id);
			ipdForm.setChief_complaints_list(chief_complaints_list);
			ipdForm.setPresent_illness_list(present_illness_list);
			ipdForm.setPast_history_list(past_history_list);
			ipdForm.setFamily_history_list(family_history_list);
			ipdForm.setPersonal_hist_list(personal_hist_list);
			ipdForm.setOn_exam_list(on_exam_list);
			ipdForm.setTreatment_given_list(treatment_given_list);
			
			
			ArrayList<Bed> wardlist=bedDao.getAllWardList("0");
			ipdForm.setWardlist(wardlist);
			
			ipdForm.setFormtype(bed.getFormtype());
		} catch (Exception e) {

			e.printStackTrace();
		}
    	finally {
    		connection.close();
    	}
    	
    	return "printgynicform";
    }
    
  
 public String updategynicform() throws Exception{
    	
    	Connection connection=null;
    	
    	try {
			
    		connection=Connection_provider.getconnection();
    		IpdDAO ipdDAO= new JDBCIpdDAO(connection);
    		MasterDAO masterDAO= new JDBCMasterDAO(connection);
    		BedDao bedDao= new JDBCBedDao(connection);
    		String clientid= ipdForm.getClientid();
    		
    		Bed bed =new Bed();
    		bed.setId(ipdForm.getId());
    		bed.setClientid(clientid);
    		
    		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
    		Calendar calendar=Calendar.getInstance();
    		String commencing = dateFormat.format(calendar.getTime());
    		String lastmodified= DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
    		
    		bed.setCommencing(commencing);
    		bed.setLastmodified(lastmodified);
    		bed.setAlcohal(ipdForm.getAlcohal());
    		bed.setDrugs(ipdForm.getDrugs());
    		bed.setOther_medication(ipdForm.getOther_medication());
    		bed.setTobaco(ipdForm.getTobaco());
    		bed.setTobaconotes(ipdForm.getTobaconotes());
    		bed.setSmoking(ipdForm.getSmoking());
    		
    		bed.setAge_menarche(ipdForm.getAge_menarche());
    		bed.setLmp(ipdForm.getLmp());
    		bed.setLlmp(ipdForm.getLlmp());
    		bed.setPmc(ipdForm.getPmc());
    		
    		
    		bed.setCycle_length(ipdForm.getCycle_length());
    		bed.setDuration_flow(ipdForm.getDuration_flow());
    		bed.setAmount_flow(ipdForm.getAmount_flow());
    		bed.setDysmenorrhea(ipdForm.getDysmenorrhea());
    		bed.setDyspareunia(ipdForm.getDyspareunia());
    		bed.setHopassing_clots(ipdForm.getHopassing_clots());
    		
    		bed.setWhite_disc_itching(ipdForm.getWhite_disc_itching());
    		bed.setIntercourse_freq(ipdForm.getIntercourse_freq());
    		bed.setGalactorrea(ipdForm.getGalactorrea());
    		
    		bed.setHo_contraception(ipdForm.getHo_contraception());
    		bed.setRubella_vaccine(ipdForm.getRubella_vaccine());
    		bed.setMenopause(ipdForm.getMenopause());
    		bed.setNulligravida(ipdForm.getNulligravida());
    		bed.setCurrent_pregnent(ipdForm.getCurrent_pregnent());
    		bed.setIud(ipdForm.getIud());
    		
    		bed.setLive_boys(ipdForm.getLive_boys());
    		bed.setLive_girls(ipdForm.getLive_girls());
    		bed.setStillbirths(ipdForm.getStillbirths());
    		bed.setAbortions(ipdForm.getAbortions());
    		bed.setDeath_children(ipdForm.getDeath_children());
    		
    		//parity_aboration_notes,p,l,a,d
    		bed.setParity_abortion_notes(ipdForm.getParity_abortion_notes());
    		bed.setP(ipdForm.getP());
    		bed.setL(ipdForm.getL());
    		bed.setA(ipdForm.getA());
    		bed.setD(ipdForm.getD());
    		
    		
    		bed.setLmp(ipdForm.getLmp());
    		bed.setEdd(ipdForm.getEdd());
    		bed.setUsg(ipdForm.getUsg()); 
    		
    		bed.setFamily_history(ipdForm.getFamily_history());
    		bed.setFamily_history_notes(ipdForm.getFamily_history_notes());
    		bed.setPasthistory(ipdForm.getPasthistory());
    		bed.setPt_history_notes(ipdForm.getPt_history_notes());
    		
    		bed.setGravida(ipdForm.getGravida());
    		bed.setPara(ipdForm.getPara());
    		bed.setLive(ipdForm.getLive());
    		bed.setAbortion(ipdForm.getAbortion());
    		bed.setMtp(ipdForm.getMtp());
    		bed.setStill_born(ipdForm.getStill_born());
    		bed.setDeath(ipdForm.getDeath());
    		bed.setHigh_risk_factor(ipdForm.getHigh_risk_factor());
    		
    		bed.setSurgical_ho(ipdForm.getSurgical_ho());
    		
    		bed.setDate1(ipdForm.getDate1());
    		bed.setHb1(ipdForm.getHb1());
    		bed.setFbs1(ipdForm.getFbs1());
    		bed.setDpbs1(ipdForm.getDpbs1());
    		bed.setUrm1(ipdForm.getUrm1());
    		bed.setTsh1(ipdForm.getTsh1());
    		bed.setIct1(ipdForm.getIct1());
    		bed.setGtt1(ipdForm.getGtt1());
    		
    		bed.setDate2(ipdForm.getDate2());
    		bed.setHb2(ipdForm.getHb2());
    		bed.setFbs2(ipdForm.getFbs2());
    		bed.setDpbs2(ipdForm.getDpbs2());
    		bed.setUrm2(ipdForm.getUrm2());
    		bed.setTsh2(ipdForm.getTsh2());
    		bed.setIct2(ipdForm.getIct2());
    		bed.setGtt2(ipdForm.getGtt2());
    		
    		
    		bed.setDate3(ipdForm.getDate3());
    		bed.setHb3(ipdForm.getHb3());
    		bed.setFbs3(ipdForm.getFbs3());
    		bed.setDpbs3(ipdForm.getDpbs3());
    		bed.setUrm3(ipdForm.getUrm3());
    		bed.setTsh3(ipdForm.getTsh3());
    		bed.setIct3(ipdForm.getIct3());
    		bed.setGtt3(ipdForm.getGtt3());
    		
    		bed.setDate4(ipdForm.getDate4());
    		bed.setHb4(ipdForm.getHb4());
    		bed.setFbs4(ipdForm.getFbs4());
    		bed.setDpbs4(ipdForm.getDpbs4());
    		bed.setUrm4(ipdForm.getUrm4());
    		bed.setTsh4(ipdForm.getTsh4());
    		bed.setIct4(ipdForm.getIct4());
    		bed.setGtt4(ipdForm.getGtt4());
    		
    		
    		bed.setHv_1m(ipdForm.getHv_1m());
    		bed.setHbs_ag(ipdForm.getHbs_ag());
    		bed.setVdrl(ipdForm.getVdrl());
    		bed.setHb_srecta(ipdForm.getHb_srecta());
    		bed.setHb_ac(ipdForm.getHb_ac());
    		bed.setDuet_markess(ipdForm.getDuet_markess());
    		bed.setTriple(ipdForm.getTriple());
    		bed.setQuadrple_maicers(ipdForm.getQuadrple_maicers());
    		bed.setEarlierinvest(ipdForm.getEarlierinvest());
    		
    		bed.setVisit_reason_ids(ipdForm.getVisit_reason_ids());
    		//ivf_date, down_regulation, ovarian_stimulation, os_dosage, sperm_quality, et_day, oocytes_obtained,
    		//oocytes_quality, embroyos_grade, embroyos_transfered, embroyos_description, freezing, transfer_process, betahcgcm, ivf_remark, do_family_history,
    		//ho_fertility_family, ho_genetic_family, ho_premature_family, age_of_menarche, age_of_menarche_notes, dysmenorrhoe, dysmenorrhoe_notes,
    		//flow, flow_notes, sleep_disruption_bleeding, sleep_disruption_bleeding_notes, blachouts, blachouts_notes
    		bed.setIvf_date(ipdForm.getIvf_date());
    		bed.setDown_regulation(ipdForm.getDown_regulation());
    		bed.setOvarian_stimulation(ipdForm.getOvarian_stimulation());
    		bed.setOs_dosage(ipdForm.getOs_dosage());
    		bed.setSperm_quality(ipdForm.getSperm_quality());
    		bed.setEt_day(ipdForm.getEt_day());
    		bed.setOocytes_obtained(ipdForm.getOocytes_obtained());
    		bed.setOocytes_quality(ipdForm.getOocytes_quality());
    		bed.setEmbroyos_grade(ipdForm.getEmbroyos_grade());
    		bed.setEmbroyos_transfered(ipdForm.getEmbroyos_transfered());
    		bed.setEmbroyos_description(ipdForm.getEmbroyos_description());
    		bed.setFreezing(ipdForm.getFreezing());
    		bed.setTransfer_process(ipdForm.getTransfer_process());
    		bed.setBetahcgcm(ipdForm.getBetahcgcm());
    		bed.setIvf_remark(ipdForm.getIvf_remark());
    		bed.setDo_family_history(ipdForm.getDo_family_history());
    		bed.setHo_fertility_family(ipdForm.getHo_fertility_family());
    		bed.setHo_genetic_family(ipdForm.getHo_genetic_family());
    		bed.setHo_premature_family(ipdForm.getHo_premature_family());
    		bed.setAge_of_menarche(ipdForm.getAge_of_menarche());
    		bed.setDysmenorrhoe(ipdForm.getDysmenorrhoe());
    		bed.setDysmenorrhoe_notes(ipdForm.getDysmenorrhoe_notes());
    		bed.setFlow(ipdForm.getFlow());
    		bed.setFlow_notes(ipdForm.getFlow_notes());
    		bed.setSleep_disruption_bleeding(ipdForm.getSleep_disruption_bleeding());
    		bed.setSleep_disruption_bleeding_notes(ipdForm.getSleep_disruption_bleeding_notes());
    		bed.setBlachouts(ipdForm.getBlachouts());
    		bed.setBlachouts_notes(ipdForm.getBlachouts_notes());
    		
    		 //anormaly_scan_11week, cervical_length_11week, double_marker_11week,;
			  bed.setAnormaly_scan_11week(ipdForm.getAnormaly_scan_11week());
			  bed.setCervical_length_11week(ipdForm.getCervical_length_11week());
			  bed.setDouble_marker_11week(ipdForm.getDouble_marker_11week());
			  
			  //all_investigation_16week, sikling_16week, triple_marker_16week, abstinence_1visit;
			  bed.setAll_investigation_16week(ipdForm.getAll_investigation_16week());
			  bed.setSikling_16week(ipdForm.getSikling_16week());
			  bed.setTriple_marker_16week(ipdForm.getTriple_marker_16week());
			  bed.setAbstinence_1visit(ipdForm.getAbstinence_1visit());
			  
			  //barrier_contra_1visit, bed_rest_1visit, book_1visit, csv_1visit, dispi_test_1visit;
			  bed.setBarrier_contra_1visit(ipdForm.getBarrier_contra_1visit());
			  bed.setBed_rest_1visit(ipdForm.getBed_rest_1visit());
			  bed.setBook_1visit(ipdForm.getBook_1visit());
			  bed.setCsv_1visit(ipdForm.getCsv_1visit());
			  bed.setDispi_test_1visit(ipdForm.getDispi_test_1visit());
			  
			  //drug_reaction_1visit, hcg_1visit, heparin_1visit, oral_hygeine_1visit, other_test_1visit, physio_diet_1visit,
			  bed.setDrug_reaction_1visit(ipdForm.getDrug_reaction_1visit());
			  bed.setHcg_1visit(ipdForm.getHcg_1visit());
			  bed.setHeparin_1visit(ipdForm.getHeparin_1visit());
			  bed.setOral_hygeine_1visit(ipdForm.getOral_hygeine_1visit());
			  bed.setOther_test_1visit(ipdForm.getOther_test_1visit());
			  bed.setPhysio_diet_1visit(ipdForm.getPhysio_diet_1visit());
			  
			  //rubelle_status_1visit, smart_doc_1visit, stream_cell_1visit, vaginities_1visit, animally_scan_20week
			  bed.setRubelle_status_1visit(ipdForm.getRubelle_status_1visit());
			  bed.setSmart_doc_1visit(ipdForm.getSmart_doc_1visit());
			  bed.setStream_cell_1visit(ipdForm.getStream_cell_1visit());
			  bed.setVaginities_1visit(ipdForm.getVaginities_1visit());
			  bed.setAnimally_scan_20week(ipdForm.getAnimally_scan_20week());
			  
			  //fetal_eco_20week, anti_d_24week, dipsi_24week, itc_24week, investigation_sos_30week, steroids_30week;
			  bed.setFetal_eco_20week(ipdForm.getFetal_eco_20week());
			  bed.setAnti_d_24week(ipdForm.getAnti_d_24week());
			  bed.setDipsi_24week(ipdForm.getDipsi_24week());
			  bed.setItc_24week(ipdForm.getItc_24week());
			  bed.setInvestigation_sos_30week(ipdForm.getInvestigation_sos_30week());
			  bed.setSteroids_30week(ipdForm.getSteroids_30week());
			  
			  //vaginities_treatment_30week, breast_preparation_34week, color_doppler_34week, labour_counselling_34week, nst_34week, vaginities_treatment_34week
			  bed.setVaginities_treatment_30week(ipdForm.getVaginities_treatment_30week());
			  bed.setBreast_preparation_34week(ipdForm.getBreast_preparation_34week());
			  bed.setColor_doppler_34week(ipdForm.getColor_doppler_34week());
			  bed.setLabour_counselling_34week(ipdForm.getLabour_counselling_34week());
			  bed.setNst_34week(ipdForm.getNst_34week());
			  bed.setVaginities_treatment_34week(ipdForm.getVaginities_treatment_34week());
    		  
			  //nst_date1, nst_date2, nst_date3, nst_date4, nst_date5, nst_date6,
			  bed.setNst_date1(ipdForm.getNst_date1());
			  bed.setNst_date2(ipdForm.getNst_date2());
			  bed.setNst_date3(ipdForm.getNst_date3());
			  bed.setNst_date4(ipdForm.getNst_date4());
			  bed.setNst_date5(ipdForm.getNst_date5());
			  bed.setNst_date6(ipdForm.getNst_date6());
			  
			  //nst_time1, nst_time2, nst_time3, nst_time4, nst_time5, nst_time6,
			  bed.setNst_time1(ipdForm.getNst_time1());
			  bed.setNst_time2(ipdForm.getNst_time2());
			  bed.setNst_time3(ipdForm.getNst_time3());
			  bed.setNst_time4(ipdForm.getNst_time4());
			  bed.setNst_time5(ipdForm.getNst_time5());
			  bed.setNst_time6(ipdForm.getNst_time6());
			  
			  //nst_indication1, nst_indication2, nst_indication3, nst_indication4, nst_indication5, nst_indication6,
			  bed.setNst_indication1(ipdForm.getNst_indication1());
			  bed.setNst_indication2(ipdForm.getNst_indication2());
			  bed.setNst_indication3(ipdForm.getNst_indication3());
			  bed.setNst_indication4(ipdForm.getNst_indication4());
			  bed.setNst_indication5(ipdForm.getNst_indication5());
			  bed.setNst_indication6(ipdForm.getNst_indication6());
			  
			  //nst_duration1, nst_duration2, nst_duration3, nst_duration4, nst_duration5, nst_duration6,
			  bed.setNst_duration1(ipdForm.getNst_duration1());
			  bed.setNst_duration2(ipdForm.getNst_duration2());
			  bed.setNst_duration3(ipdForm.getNst_duration3());
			  bed.setNst_duration4(ipdForm.getNst_duration4());
			  bed.setNst_duration5(ipdForm.getNst_duration5());
			  bed.setNst_duration6(ipdForm.getNst_duration6());
			  
			  //nst_interpretation1, nst_interpretation2, nst_interpretation3, nst_interpretation4, nst_interpretation5, nst_interpretation6,
			  bed.setNst_interpretation1(ipdForm.getNst_interpretation1());
			  bed.setNst_interpretation2(ipdForm.getNst_interpretation2());
			  bed.setNst_interpretation3(ipdForm.getNst_interpretation3());
			  bed.setNst_interpretation4(ipdForm.getNst_interpretation4());
			  bed.setNst_interpretation5(ipdForm.getNst_interpretation5());
			  bed.setNst_interpretation6(ipdForm.getNst_interpretation6());
			  
			  //nst_intervention1, nst_intervention2, nst_intervention3, nst_intervention4, nst_intervention5, nst_intervention6
			  bed.setNst_intervention1(ipdForm.getNst_intervention1());
			  bed.setNst_intervention2(ipdForm.getNst_intervention2());
			  bed.setNst_intervention3(ipdForm.getNst_intervention3());
			  bed.setNst_intervention4(ipdForm.getNst_intervention4());
			  bed.setNst_intervention5(ipdForm.getNst_intervention5());
			  bed.setNst_intervention6(ipdForm.getNst_intervention6());
    		
			  //tt_dose1, tt_dose2, influenza_vaccine, usgdate1, usgdate2, usgdate3, usgdate4,
			  bed.setTt_dose1(ipdForm.getTt_dose1());
			  bed.setTt_dose2(ipdForm.getTt_dose2());
			  bed.setInfluenza_vaccine(ipdForm.getInfluenza_vaccine());
			  bed.setUsgdate1(ipdForm.getUsgdate1());
			  bed.setUsgdate2(ipdForm.getUsgdate2());
			  bed.setUsgdate3(ipdForm.getUsgdate3());
			  bed.setUsgdate4(ipdForm.getUsgdate4());
			  
			  //amenorrhea1, amenorrhea2, amenorrhea3, amenorrhea4, presentation1, presentation2, presentation3, presentation4,
			  bed.setAmenorrhea1(ipdForm.getAmenorrhea1());
			  bed.setAmenorrhea2(ipdForm.getAmenorrhea2());
			  bed.setAmenorrhea3(ipdForm.getAmenorrhea3());
			  bed.setAmenorrhea4(ipdForm.getAmenorrhea4());
			  bed.setPresentation1(ipdForm.getPresentation1());
			  bed.setPresentation2(ipdForm.getPresentation2());
			  bed.setPresentation3(ipdForm.getPresentation3());
			  bed.setPresentation4(ipdForm.getPresentation4());
			  
			  //bpdgs1, bpdgs2, bpdgs3, bpdgs4, hc1, hc2, hc3, hc4,
			  bed.setBpdgs1(ipdForm.getBpdgs1());
			  bed.setBpdgs2(ipdForm.getBpdgs2());
			  bed.setBpdgs3(ipdForm.getBpdgs3());
			  bed.setBpdgs4(ipdForm.getBpdgs4());
			  bed.setHc1(ipdForm.getHc1());
			  bed.setHc2(ipdForm.getHc1());
			  bed.setHc3(ipdForm.getHc1());
			  bed.setHc4(ipdForm.getHc1());
			  
			  //ac1, ac2, ac3, ac4, flcrl1, flcrl2, flcrl3, flcrl4,
			  bed.setAc1(ipdForm.getAc1());
			  bed.setAc2(ipdForm.getAc2());
			  bed.setAc3(ipdForm.getAc3());
			  bed.setAc4(ipdForm.getAc4());
			  bed.setFlcrl1(ipdForm.getFlcrl1());
			  bed.setFlcrl2(ipdForm.getFlcrl2());
			  bed.setFlcrl3(ipdForm.getFlcrl3());
			  bed.setFlcrl4(ipdForm.getFlcrl4());
			  
			  //ga_usg1, ga_usg2, ga_usg3, ga_usg4, liquor1, liquor2, liquor3, liquor4,
			  bed.setGa_usg1(ipdForm.getGa_usg1());
			  bed.setGa_usg2(ipdForm.getGa_usg2());
			  bed.setGa_usg3(ipdForm.getGa_usg3());
			  bed.setGa_usg4(ipdForm.getGa_usg4());
			  bed.setLiquor1(ipdForm.getLiquor1());
			  bed.setLiquor2(ipdForm.getLiquor2());
			  bed.setLiquor3(ipdForm.getLiquor3());
			  bed.setLiquor4(ipdForm.getLiquor4());
			  
			  //placenta1, placenta2, placenta3, placenta4, foetal_weight1, foetal_weight2, foetal_weight3, foetal_weight4,
			  bed.setPlacenta1(ipdForm.getPlacenta1());
			  bed.setPlacenta2(ipdForm.getPlacenta2());
			  bed.setPlacenta3(ipdForm.getPlacenta3());
			  bed.setPlacenta4(ipdForm.getPlacenta4());
			  bed.setFoetal_weight1(ipdForm.getFoetal_weight1());
			  bed.setFoetal_weight2(ipdForm.getFoetal_weight2());
			  bed.setFoetal_weight3(ipdForm.getFoetal_weight3());
			  bed.setFoetal_weight4(ipdForm.getFoetal_weight4());
			  
			  //cervical_length1, cervical_length2, cervical_length3, cervical_length4, nt_scan, anomaly_scan, colour_doppler_scan
			  bed.setCervical_length1(ipdForm.getCervical_length1());
			  bed.setCervical_length2(ipdForm.getCervical_length2());
			  bed.setCervical_length3(ipdForm.getCervical_length3());
			  bed.setCervical_length4(ipdForm.getCervical_length4());
			  bed.setNt_scan(ipdForm.getNt_scan());
			  bed.setAnomaly_scan(ipdForm.getAnomaly_scan());
			  bed.setColour_doppler_scan(ipdForm.getColour_doppler_scan());
			  
			  //gen_condition, temp, pallor, pedal_edema, pulse, bmi, height,weight, sys_bp, dia_bp, wall_edema, fundal_height
			  bed.setGen_condition(ipdForm.getGen_condition());
			  bed.setTemp(ipdForm.getTemp());
			  bed.setPallor(ipdForm.getPallor());
			  bed.setPedal_edema(ipdForm.getPedal_edema());
			  bed.setPulse(ipdForm.getPulse());
			  bed.setBmi(ipdForm.getBmi());
			  bed.setHeight(ipdForm.getHeight());
			  bed.setWeight(ipdForm.getWeight() );
			  bed.setSys_bp(ipdForm.getSys_bp());
			  bed.setDia_bp(ipdForm.getDia_bp());
			  
			  String wall_edema= request.getParameter("wall_edema");
			  if(wall_edema!=null){
				  
				  if(!wall_edema.equals("1")){
					  wall_edema ="0";
				  } else {
					  wall_edema ="1";
				  }
			  } else {
				  wall_edema= "0";
			  }
			  
			  bed.setWall_edema(wall_edema);
			  bed.setFundal_height(ipdForm.getFundal_height());
			  
			  //cephalic, breech, baley_size, transverse_fhs, liquor, uterine_contractions, ps_cervix, ps_vagine,
			  
			  String cephalic =request.getParameter("cephalic");
			  String cephalicVal=request.getParameter("cephalicVal");
			  
			  if(cephalic!=null){
				  
				  if(cephalic.equals("on")){
					  
					  cephalic= cephalicVal;
				  } else {
					  cephalic="0";
				  }
			  }else {
				  cephalic="0";
			  }
			  
			  bed.setCephalic(cephalic);
			  
			  String breech= request.getParameter("breech"); 
			  if(breech!=null){
				  
				  if(breech.equals("on")){
					  breech= "1";
				  } else {
					  breech= "0";
				  }
				  
			  } else {
				  breech="0";
			  }
			  bed.setBreech(breech);
			  
			  String baley_size= request.getParameter("baley_size");
			  String baley_sizeVal= request.getParameter("baley_sizeVal");
			  if(baley_size!=null){
				  
				  if(baley_size.equals("on")){
					  baley_size= baley_sizeVal;
				  } else {
					  baley_size= "0";
				  }
				  
			  } else {
				  baley_size="0";
			  }
			  
			  bed.setBaley_size(baley_size);
			  
			  String transverse_fhs= request.getParameter("transverse_fhs");
			  String transverse_fhsVal= request.getParameter("transverse_fhsVal");
			  if(transverse_fhs!=null){
				  
				  if(transverse_fhs.equals("on")){
					  transverse_fhs= transverse_fhsVal;
				  } else {
					  transverse_fhs= "0";
				  }
				  
			  } else {
				  transverse_fhs="0";
			  }
			  
			  bed.setTransverse_fhs(transverse_fhs);
			  
			  String liquor=request.getParameter("liquor");
			  if(liquor==null){
				  liquor="0";
			  }else if(liquor.equals("")){
				  liquor="0";
			  } 
			  bed.setLiquor(liquor);
			  
			  String uterine_contractions=request.getParameter("uterine_contractions");
			  if(uterine_contractions==null){
				  uterine_contractions="0";
			  }else if(uterine_contractions.equals("")){
				  uterine_contractions="0";
			  } 
			  
			  bed.setUterine_contractions(uterine_contractions);
			  
			  String ps_cervix= request.getParameter("ps_cervix");
			  String ps_cervixVal= request.getParameter("ps_cervixVal");
			  if(ps_cervix!=null){
				  
				  if(ps_cervix.equals("on")){
					  
					  ps_cervix= ps_cervixVal;
				  } else {
					  ps_cervix="0";
				  }
			  }else {
				  ps_cervix="0";
			  }
			  
			  bed.setPs_cervix(ps_cervix);
			  
			  String ps_vagine= request.getParameter("ps_vagine");
			  String ps_vagineVal= request.getParameter("ps_vagineVal");
			  if(ps_vagine!=null){
				  
				  if(ps_vagine.equals("on")){
					  
					  ps_vagine= ps_vagineVal;
				  } else {
					  ps_vagine="0";
				  }
			  }else {
				  ps_vagine="0";
			  }
			  
			  bed.setPs_vagine(ps_vagine);
			  
			  //ps_vault, pv_trim, pv_unettacced, pv_ant, pv_os, pv_membranes, pv_tubular, pv_just_ettacced, pv_mid_posits, pv_soft, pv_ettacced, pv_post
			  
			  String ps_vault= request.getParameter("ps_vault");
			  String ps_vaultVal= request.getParameter("ps_vaultVal");
			  if(ps_vault!=null){
				  
				  if(ps_vault.equals("on")){
					  
					  ps_vault= ps_vaultVal;
				  } else {
					  ps_vault="0";
				  }
			  }else {
				  ps_vault="0";
			  }
			  
			  bed.setPs_vault(ps_vault);

			  String pv_trim=  request.getParameter("pv_trim");
			  if(pv_trim!=null){
				  
				  if(pv_trim.equals("on")){
					  pv_trim="1";
				  } else {
					  pv_trim="0";
				  }
				  
			  } else {
				  pv_trim="0";
			  }
			  
			  bed.setPv_trim(pv_trim);
			  
			  String pv_unettacced=  request.getParameter("pv_unettacced");
			  if(pv_unettacced!=null){
				  
				  if(pv_unettacced.equals("on")){
					  pv_unettacced="1";
				  } else {
					  pv_unettacced="0";
				  }
				  
			  } else {
				  pv_unettacced="0";
			  }
			  
			  bed.setPv_unettacced(pv_unettacced);
			  
			  String pv_ant=  request.getParameter("pv_ant");
			  if(pv_ant!=null){
				  
				  if(pv_ant.equals("on")){
					  pv_ant="1";
				  } else {
					  pv_ant="0";
				  }
				  
			  } else {
				  pv_ant="0";
			  }
			  
			  bed.setPv_ant(pv_ant);
			  
			  String pv_os=  request.getParameter("pv_os");
			  String pv_osVal= request.getParameter("pv_osVal");
			  if(pv_os!=null){
				  
				  if(pv_os.equals("on")){
					  pv_os=pv_osVal;
				  } else {
					  pv_os="0";
				  }
				  
			  } else {
				  pv_os="0";
			  }
			  bed.setPv_os(pv_os);
			  
			  String pv_membranes= request.getParameter("pv_membranes");
			  if(pv_membranes!=null){
				  
				  if(pv_membranes.equals("on")){
					  pv_membranes="1";
				  } else {
					  pv_membranes="0";
				  }
				  
			  } else {
				  pv_membranes="0";
			  }
			  
			  bed.setPv_membranes(pv_membranes);
			  
			  String pv_tubular= request.getParameter("pv_tubular");
			  if(pv_tubular!=null){
				  
				  if(pv_tubular.equals("on")){
					  pv_tubular="1";
				  } else {
					  pv_tubular="0";
				  }
				  
			  } else {
				  pv_tubular="0";
			  }
			  bed.setPv_tubular(pv_tubular);
			  
			  String pv_just_ettacced= request.getParameter("pv_just_ettacced");
			  if(pv_just_ettacced!=null){
				  
				  if(pv_just_ettacced.equals("on")){
					  pv_just_ettacced="1";
				  } else {
					  pv_just_ettacced="0";
				  }
				  
			  } else {
				  pv_just_ettacced="0";
			  }
			  bed.setPv_just_ettacced(pv_just_ettacced);
			  
			  String pv_mid_posits= request.getParameter("pv_mid_posits");
			  if(pv_mid_posits!=null){
				  
				  if(pv_mid_posits.equals("on")){
					  pv_mid_posits="1";
				  } else {
					  pv_mid_posits="0";
				  }
				  
			  } else {
				  pv_mid_posits="0";
			  }
			  
			  bed.setPv_mid_posits(pv_mid_posits);
			  
			  String pv_soft= request.getParameter("pv_soft");
			  if(pv_soft!=null){
				  
				  if(pv_soft.equals("on")){
					  pv_soft="1";
				  } else {
					  pv_soft="0";
				  }
				  
			  } else {
				  pv_soft="0";
			  }
			  
			  bed.setPv_soft(pv_soft);
			  
			  String pv_ettacced= request.getParameter("pv_ettacced");
			  if(pv_ettacced!=null){
				  
				  if(pv_ettacced.equals("on")){
					  pv_ettacced="1";
				  } else {
					  pv_ettacced="0";
				  }
				  
			  } else {
				  pv_ettacced="0";
			  }
			  
			  bed.setPv_ettacced(pv_ettacced);
			  
			  String pv_post= request.getParameter("pv_post");
			  if(pv_post!=null){
				  
				  if(pv_post.equals("on")){
					  pv_post="1";
				  } else {
					  pv_post="0";
				  }
				  
			  } else {
				  pv_post="0";
			  }
			  
			  bed.setPv_post(pv_post); 
    		
			  // jitu
			     String ps_fhs= request.getParameter("ps_fhs");
			     if(ps_fhs!=null){
			      
			      if(ps_fhs.equals("")){
			       ps_fhs="0";
			      }
			      
			     } else {
			      ps_fhs="0";
			     }
			     
			     bed.setPs_fhs(ps_fhs);
			      
			     String pv_membrane= request.getParameter("pv_membrane");
			     String pv_MVal= request.getParameter("pv_MVal");
			     if(pv_membrane!=null){
			      
			      if(!pv_membrane.equals("0")){
			       pv_membrane=pv_MVal;
			      }       
			      
			     } else {
			      pv_membrane="0";
			     }
			     bed.setPv_membrane(pv_membrane);
			     String pv_station= request.getParameter("pv_station");
			     bed.setPv_station(pv_station);
			     String pv_liquor= request.getParameter("pv_liquor");
			     bed.setPv_liquor(pv_liquor);
			     String pv_pelvis= request.getParameter("pv_pelvis");
			     bed.setPv_pelvis(pv_pelvis);
			     String pv_position= request.getParameter("pv_position");
			     bed.setPv_position(pv_position);
			     
			     
			     bed.setBeats_min(ipdForm.getBeats_min());
			  
			     //jitu
			     bed.setPmp(ipdForm.getPmp());
			     bed.setDiagnosisgyn(ipdForm.getDiagnosisgyn());
			     String le_vulva =request.getParameter("le_vulva");
			     if(le_vulva!=null){
			    	 
			    	 if(!le_vulva.equals("Normal")){

			    		 String le_vulva_txt= request.getParameter("le_vulva_txt");	
			    		 le_vulva= le_vulva_txt;
			    	 } 
			    	 
			     }else {
			    	 
			    	 le_vulva="0";
			     }
			     bed.setLe_vulva(le_vulva);
			     
			     String le_vagina =request.getParameter("le_vagina");
			     if(le_vagina!=null){
			    	 
			    	 if(!le_vagina.equals("Normal")){

			    		 String le_vagina_txt= request.getParameter("le_vagina_txt");	
			    		 le_vagina= le_vagina_txt;
			    	 } 
			     }else {
			    	 le_vagina="0";
			     }
			     bed.setLe_vagina(le_vagina);
			     
			     bed.setPa_gynic(ipdForm.getPa_gynic());
			     
			     String pv_uterus= request.getParameter("pv_uterus");
			     if(pv_uterus!=null){
			    	 
			    	 if(!pv_uterus.equals("0")){
			    		 
			    		 String pv_uterus_val= request.getParameter("pv_uterusVal");
			    		 if(pv_uterus_val!=null){
			    			 
			    			 if(!pv_uterus_val.equals("0")){
			    				 pv_uterus=pv_uterus_val;
			    			 }else {
			    				 pv_uterus= "0";
			    			 }
			    		 }else {
			    			 pv_uterus= "0";
			    		 }
			    	 }else {
			    		 pv_uterus= "0";
			    	 }
			    	 
			     } else {
			    	 pv_uterus="0";
			     }
			     bed.setPv_uterus(pv_uterus);
			     
			     
			     String pv_uterus_size= request.getParameter("pv_uterus_size");
			     if(pv_uterus_size!=null){
			    	 
			    	 if(!pv_uterus_size.equals("Normal")){
			    		 
			    		 String pv_uterus_sizeVal= request.getParameter("pv_uterus_sizeVal");
			    		 if(pv_uterus_sizeVal!=null){
			    			 
			    			 if(!pv_uterus_sizeVal.equals("0")){
			    				 pv_uterus_size=pv_uterus_sizeVal;
			    			 }else {
			    				 pv_uterus_size= "0";
			    			 }
			    		 }else {
			    			 pv_uterus_size= "0";
			    		 }
			    	 }else {
			    		 pv_uterus_size= "0";
			    	 }
			    	 
			     } else {
			    	 pv_uterus_size="0";
			     }
			     
			     bed.setPv_uterus_size(pv_uterus_size);
			     
			     String pv_bl_fomices= request.getParameter("pv_bl_fomices");
			     if(pv_bl_fomices!=null){
			    	 
			    	 if(!pv_bl_fomices.equals("0")){
			    		 
			    		 String pv_bl_fomicesVal= request.getParameter("pv_bl_fomicesVal");
			    		 if(pv_bl_fomicesVal!=null){
			    			 
			    			 if(!pv_bl_fomicesVal.equals("0")){
			    				 pv_bl_fomices=pv_bl_fomicesVal;
			    			 }else {
			    				 pv_bl_fomices= "0";
			    			 }
			    		 }else {
			    			 pv_bl_fomices= "0";
			    		 }
			    	 }else {
			    		 pv_bl_fomices= "0";
			    	 }
			    	 
			     } else {
			    	 pv_bl_fomices="0";
			     }
			     bed.setPv_bl_fomices(pv_bl_fomices);
			     
			     String pv_mobility= request.getParameter("pv_mobility");
			     if(pv_mobility!=null){
			    	 
			    	 if(!pv_mobility.equals("0")){
			    		 
			    		 String pv_mobilityVal= request.getParameter("pv_mobilityVal");
			    		 if(pv_mobilityVal!=null){
			    			 
			    			 if(!pv_mobilityVal.equals("0")){
			    				 pv_mobility=pv_mobilityVal;
			    			 }else {
			    				 pv_mobility= "0";
			    			 }
			    		 }else {
			    			 pv_mobility= "0";
			    		 }
			    	 }else {
			    		 pv_mobility= "0";
			    	 }
			    	 
			     } else {
			    	 pv_mobility="0";
			     }
			     bed.setPv_mobility(pv_mobility);
			     
			     bed.setPlan(ipdForm.getPlan());
			     bed.setFinaldiagnosis(ipdForm.getFinaldiagnosis());
			     bed.setPriscription(ipdForm.getPriscription());
			     bed.setFormtype(ipdForm.getFormtype());
			  
			  
    		 int res= ipdDAO.updateIpdGynicDetails(bed);
    		
    		
    		String chief_comlint_id=masterDAO.getIpdTemplateId("Chief Complaints");
			String present_ill_id=masterDAO.getIpdTemplateId("Present Illness");
			String past_history_id=masterDAO.getIpdTemplateId("Past History");
			String family_hist_id=masterDAO.getIpdTemplateId("Family History");
			String personal_hist_id=masterDAO.getIpdTemplateId("Personal History");
			String onexami_id=masterDAO.getIpdTemplateId("On Examination");
			String tretment_given_id=masterDAO.getIpdTemplateId("Treatment Given");
			
			ArrayList<Master> chief_complaints_list=masterDAO.getIpdTemplateListNames(chief_comlint_id);
			ArrayList<Master> present_illness_list=masterDAO.getIpdTemplateListNames(present_ill_id);
			ArrayList<Master> past_history_list=masterDAO.getIpdTemplateListNames(past_history_id);
			ArrayList<Master> family_history_list=masterDAO.getIpdTemplateListNames(family_hist_id);
			ArrayList<Master> personal_hist_list=masterDAO.getIpdTemplateListNames(personal_hist_id);
			ArrayList<Master> on_exam_list=masterDAO.getIpdTemplateListNames(onexami_id);
			ArrayList<Master> treatment_given_list=masterDAO.getIpdTemplateListNames(tretment_given_id);
			ipdForm.setChief_complaints_list(chief_complaints_list);
			ipdForm.setPresent_illness_list(present_illness_list);
			ipdForm.setPast_history_list(past_history_list);
			ipdForm.setFamily_history_list(family_history_list);
			ipdForm.setPersonal_hist_list(personal_hist_list);
			ipdForm.setOn_exam_list(on_exam_list);
			ipdForm.setTreatment_given_list(treatment_given_list);
			
			ArrayList<Bed> wardlist=bedDao.getAllWardList("0");
			ipdForm.setWardlist(wardlist);
    		
			session.setAttribute("gynicformid", String.valueOf(bed.getId()));
    		
    		
		} catch (Exception e) {

			e.printStackTrace();
		}
    	finally {
    		connection.close();
    	}
    	
    	
    	return "redirecteditgynic";
    }
 
  	public String savereasonforvisitobj() throws Exception{
  		
  		Connection  connection=null;
  		try {
  			connection=Connection_provider.getconnection();
  			IpdDAO ipdDAO= new JDBCIpdDAO(connection);
  			String clientid= request.getParameter("clientid");
  			String reason= request.getParameter("reason");
  			String quality=request.getParameter("quality");
  			String periodicity=request.getParameter("periodicity");
  			String influence= request.getParameter("influence");
  			String since= request.getParameter("since");
  			String notes= request.getParameter("notes");
  			String reasonvisit= request.getParameter("reasonvisit");
  			String decreased = request.getParameter("decreased");
  			String perceives = request.getParameter("perceives");
  			String notperceives = request.getParameter("notperceives");
  			String datetime= DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()); 
  			
  			Bed bed=new Bed();
  			bed.setClientid(clientid);
  			bed.setReason(reason);
  			bed.setQuality(quality);
  			bed.setPeriodicity(periodicity);
  			bed.setInfluence(influence);
  			bed.setSince(since);
  			bed.setNotes(notes);
  			bed.setDatetime(datetime);
  			bed.setReasonvisit(reasonvisit);
  			bed.setDecreased(decreased);
  			bed.setPerceives(perceives);
  			bed.setNotperceives(notperceives);
  			
  			int res= ipdDAO.saveReasonGynicVisit(bed);
  			String commencing =datetime.split(" ")[0];
  			
  			ArrayList<Bed> allVisitReason = ipdDAO.getGynicVisitReasonList(clientid,commencing);
  			StringBuffer buffer= new StringBuffer(); 
  			for(Bed bed2 : allVisitReason){
  				buffer.append("<h5 style='margin-bottom: 5px;margin-top: 5px;'><b>"+bed2.getReasonvisit()+"</b>&nbsp;&nbsp;<a href='#' data-toggle='modal' data-target=''><i class='fa fa-pencil'></i></a></h5>");
  				if(loginInfo.isVermanh()){
  					buffer.append("<p style='margin: 0px;'>Since : "+bed2.getSince()+"</p>");
  					buffer.append("<p style='margin: 0px;'>Note : "+bed2.getNotes()+"</p>");
  					
  					if(DateTimeUtils.numberCheck(bed.getPerceives()).equals("1")){
  						buffer.append("<p style='margin: 0px;'>Perceives</p>");
  					}
  					if(DateTimeUtils.numberCheck(bed.getNotperceives()).equals("1")){
  						buffer.append("<p style='margin: 0px;'>Not Perceives</p>");
  					}
  					if(DateTimeUtils.numberCheck(bed.getDecreased()).equals("1")){
  						buffer.append("<p style='margin: 0px;'>Decreased</p>");
  					}
  					buffer.append("<hr>");
  				}else{
  					buffer.append("<p style='margin: 0px;'>Quality : "+bed2.getQuality()+"</p>");
  					buffer.append("<p style='margin: 0px;'>Periodicity : "+bed2.getPeriodicity()+"</p>");
  					buffer.append("<p style='margin: 0px;'>Influencing Factor : "+bed2.getInfluence()+"</p>");
  					buffer.append("<p style='margin: 0px;'>Since : "+bed2.getSince()+"</p>");
  					buffer.append("<p style='margin: 0px;'>Note : "+bed2.getNotes()+"</p>");
  					buffer.append("<hr>");
  				}
  				
  			}
  			
  			buffer.append("~"+res);
  			
  			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+buffer.toString()+"");
			
		} catch (Exception e) {

			e.printStackTrace();
		}
  		
  		return null;
  		
  	}
  	
  	public String getprisctemplate() throws Exception {
  		
  		
  		Connection connection=null;
  		try {
  			connection=Connection_provider.getconnection();
  			UserProfileDAO userProfileDAO= new JDBCUserProfileDAO(connection);
  			EmrDAO emrDAO= new JDBCEmrDAO(connection);
			StringBuffer buffer= new StringBuffer();
  			String doctorid= request.getParameter("doctorid");
  			
  			UserProfile userProfile= userProfileDAO.getUserprofileDetails(Integer.parseInt(doctorid));
  			InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
			String department = investigationDAO.getConditionDepartment(userProfile.getDiciplineName());
			
			ArrayList<Priscription>templateNameList = emrDAO.getTemplateSpecializationList(department);
			//<s:select onchange="showTemplatePrisc(this.value)" id="templatename" name="templatename" list="templateNameList" headerKey="0" headerValue="Select Template" listKey="id" listValue="templatename" cssClass="form-control chosen-select"/>
			buffer.append("<select id='templatename' name='templatename' onchange='showTemplatePrisc(this.value)' class='form-control chosen' >");
			 buffer.append("<option value='0'>Select Template</option>");
			 for(Priscription priscription:templateNameList){
				 
				 buffer.append("<option value='"+priscription.getId()+"'>"+priscription.getTemplatename()+"</option>");
			 }
			 
			buffer.append("</select>");
			buffer.append("<a href='#' type='button' class='btn btn-sm btn-primary' style='margin-left: 5px;' data-toggle='modal' data-target='#selecttemplate'><i class='fa fa-align-justify' aria-hidden='true'></i></a>");
  			
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
    
public String settemplateprisc() throws Exception {
  		
  		
  		Connection connection=null;
  		try {
  			connection=Connection_provider.getconnection();
  			UserProfileDAO userProfileDAO= new JDBCUserProfileDAO(connection);
  			EmrDAO emrDAO= new JDBCEmrDAO(connection);
			StringBuffer buffer= new StringBuffer();
  			String department= request.getParameter("id");
			ArrayList<Priscription>templateNameList = emrDAO.getTemplateSpecializationList(department);
			//<s:select onchange="showTemplatePrisc(this.value)" id="templatename" name="templatename" list="templateNameList" headerKey="0" headerValue="Select Template" listKey="id" listValue="templatename" cssClass="form-control chosen-select"/>
			buffer.append("<select id='templatename' name='templatename' onchange='showTemplatePrisc(this.value)' class='form-control chosen' >");
			 buffer.append("<option value='0'>Select Template</option>");
			 for(Priscription priscription:templateNameList){
				 
				 buffer.append("<option value='"+priscription.getId()+"'>"+priscription.getTemplatename()+"</option>");
			 }
			 
			buffer.append("</select>");
			buffer.append("<a href='#' type='button' class='btn btn-sm btn-primary' style='margin-left: 5px;' data-toggle='modal' data-target='#selecttemplate'><i class='fa fa-align-justify' aria-hidden='true'></i></a>");
  			
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


public String getvisitingconsultantlist() throws Exception {
	Connection connection = null;
	try {
		connection = Connection_provider.getconnection();
		UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
		StringBuffer str = new StringBuffer();
		ArrayList<UserProfile> visitingConsultDoctors=userProfileDAO.getVisitingPractitinerList();
		str.append("<select name='consultantdr' id='consultantdr' class='form-control showToolTip chosen' >");
		str.append("<option value='0'>Select Consultant</option>");
		for (UserProfile userProfile : visitingConsultDoctors) {
			str.append("<option value='" + userProfile.getId() + "'>" + userProfile.getFullname() + "</option>");
		}
		str.append("</select>");

		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write("" + str.toString() + "");

	} catch (Exception e) {
		e.printStackTrace();
	}finally{
		connection.close();
	}
	return null;
}
    

  private ArrayList<String> getReasonVisitData(String formtype) {
	  
	  
	  ArrayList<String> list= new ArrayList<String>();
	  if(formtype.equals("1")){
		  list.add("Routine Follow up");
		  list.add("Amenorrhoea");
		  list.add("UPT +ve");
		  list.add("Pain in lower abdomen");
		  list.add("Nausea");
		  list.add("Vomiting");
		  list.add("Nausea + Vomiting");
		  list.add("Bleeding PV");
		  list.add("Burning Micturition");
		  list.add("White discharge PV");
		  list.add("Bad Obstetric History");
		  list.add("Others");
		  
	  } else if(formtype.equals("2")){
		  
		  list.add("Irregular Menses");
		  list.add("Excessive Menstual Bleeding");
		  list.add("Secondary Ammenorrhea");
		  list.add("Dysmenorrhea");
		  list.add("Pain in Abdomen ");
		  list.add("Upt � Negative");
		  list.add("Burning Micturation");
		  list.add("Comes For Followup ");
		  list.add("White Discharge");
		  list.add("Scanty Menses ");
		  list.add("Post Menopausal Bleeding");
		  list.add("Climacteric Symtoms");
		  list.add("Fue Of Endometnosis- Comes For Check Up");
		  list.add("Postmenopauasal Spotting");
		  list.add("Postmenopauasal Spotting � For Routine Check Up ");
		  list.add("Frequent Menses");
		  list.add("Itching Around Vulva");
		  list.add("Primary Ammenorrhea");
		  list.add("Pain in Left Lumbar Region");
		  list.add("Pain in Lower Abdomen");
		  
		  
	  } else if(formtype.equals("3")) {
		  list.add("Married");
		  list.add("Primary Infertility");
		  list.add("Eager To Conceive");
		  list.add("Secondary Infertility");
		  list.add("Irregular Menses");
		  list.add("Boh");
		  list.add("Comes For D-2/3 Investigation");
		  list.add("Secondary Ammenorrhea");
		  list.add("Comes For Fertility Assessment");
		  list.add("Low Amh");
		  list.add("Poor Ovarian reserve");
		  list.add("Failed Ivf � Once");
		  list.add("Excessive Menstrual Bleeding ");
		  list.add("Dysmenorrhea");
		  list.add("Scanty Menses");
		  list.add("Secondary Ovarian Failure");
		  list.add("Comes For Follow Up");
		  list.add("Low Fertility Potential");
		  list.add("Menses Only With Medicines");
		  list.add("Ammenorrhea");
		  
	  }
	  return list;
  }
  	
 public void getipddatadischarge(String selectedid){
	 

	
	     Connection connection=null;
		
		try {
			session.removeAttribute("finalConditions");
			if(session.getAttribute("openedb")==null){
				session.setAttribute("openedb", "ipd");
			}
			
			connection=Connection_provider.getconnection();
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			BedDao bedDao = new JDBCBedDao(connection);
			IpdLogDAO ipdLogDAO=new JDBCIpdLogDAO(connection);
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			MasterDAO masterDAO=new JDBCMasterDAO(connection);
			UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
			DiaryManagementDAO diaryManagementDAO = new JDBCDiaryManagentDAO(connection);
			NotAvailableSlotDAO notAvailableSlotDAO=new JDBCNotAvailableSlotDAO(connection);
		/*	UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);*/
			
			
			
			
			
			//   for procedure
			Ipd ipd = ipdDAO.getProcedureName(selectedid);
		
			Bed bed = bedDao.getEditIpdData(selectedid);
			session.setAttribute("sessionadmissionid", selectedid);
			String parentpriscid = ipdDAO.getParentPriscId(selectedid);
			ipdForm.setParentpriscid(parentpriscid);
			
			String procedureid = ipdDAO.getProcedureId(ipd.getProcedurename());
			ipdForm.setAppointmentText(procedureid);
			
			ipdForm.setNewadmndate(bed.getNewadmndate());
			ArrayList<Master> procedureList = notAvailableSlotDAO.getProcedureList(bed.getSpeciality());
			if(procedureList.size()==0){
				procedureList = new ArrayList<Master>();
			}
			
			ipdForm.setProcedureList(procedureList);

			
			if(loginInfo.getIpd_abbr_access()==1){
				String newipdabbr=ipdDAO.getIpdAbrivationIds(Integer.parseInt(selectedid));
				ipdForm.setNewipdabbr(newipdabbr);
				if(Integer.parseInt(bed.getIpdseqno())>0){
					ipdForm.setIpdseqno(bed.getIpdseqno());
				}else{
					ipdForm.setIpdseqno(selectedid);
				}
			}else{
				if(Integer.parseInt(bed.getIpdseqno())>0){
					ipdForm.setIpdseqno(bed.getIpdseqno());
					ipdForm.setNewipdabbr(bed.getIpdseqno());
				}else{
					ipdForm.setIpdseqno(selectedid);
					ipdForm.setNewipdabbr(selectedid);
				}
			}
			
			Client client=clientDAO.getClientDetails(bed.getClientid());

			UserProfile userProfile=userProfileDAO.getUserprofileDetails(Integer.parseInt(bed.getPractitionerid()));
			Master discipline=masterDAO.getDisciplineData(userProfile.getDiciplineName());
			ipdForm.setDiscipline(discipline.getDiscipline());
			ipdForm.setDoctor_name(userProfile.getInitial()+" "+userProfile.getFirstname()+" "+userProfile.getLastname());
			ipdForm.setDoctor_phone(userProfile.getMobile());
			
			String discharge_default_id=masterDAO.getIpdTemplateId("Discharge Default");
			String hospital_course_id=masterDAO.getIpdTemplateId("Hospital Course");
			String nursing_advice_id=masterDAO.getIpdTemplateId("Nursing Advice");
			String operative_notes_id= masterDAO.getIpdTemplateId("Operative Notes");
			String invenstigations= masterDAO.getIpdTemplateId("Investigations");
			String finding_on_discharge= masterDAO.getIpdTemplateId("FINDING ON DISCHARGE");
			String treatment_given_id= masterDAO.getIpdTemplateId("Discharge Treatment Given");
			String emergencydetailsid = masterDAO.getIpdTemplateId("Emergency Details");
			String treatmentgiventemplateid= masterDAO.getIpdTemplateId("Treatment Given");
			String onexami_id=masterDAO.getIpdTemplateId("On Examination");
			ArrayList<Bed> bedLogList=ipdLogDAO.getBedChangeLogList(bed.getClientid(),selectedid);
			ipdForm.setBedLogList(bedLogList);
			
			//int size=bedLogList.size()-1;
			if(bedLogList.size()>0) {
				bedLogList.get(0).setStatus("1");
			}
			
			ArrayList<Master> discharge_default_list=masterDAO.getIpdTemplateListNames(discharge_default_id);
			ArrayList<Master> hospital_course_list=masterDAO.getIpdTemplateListNames(hospital_course_id);
			ArrayList<Master> nursing_advice_list=masterDAO.getIpdTemplateListNames(nursing_advice_id);
			ArrayList<Master> operativeList= masterDAO.getIpdTemplateListNames(operative_notes_id);
			ArrayList<Master> investigationList= masterDAO.getIpdTemplateListNames(invenstigations);
			ArrayList<Master> finding_on_dischargeList=masterDAO.getIpdTemplateListNames(finding_on_discharge);
			ArrayList<Master> treatment_given_list= masterDAO.getIpdTemplateListNames(treatment_given_id);
			ArrayList<Master> emergencydetailslist = masterDAO.getIpdTemplateListNames(emergencydetailsid);
			ArrayList<Master> treatmentgiventemplist= masterDAO.getIpdTemplateListNames(treatmentgiventemplateid);
			ArrayList<Master> on_exam_list=masterDAO.getIpdTemplateListNames(onexami_id);
			ipdForm.setOperativeList(operativeList);
			ipdForm.setDischarge_default_list(discharge_default_list);
			ipdForm.setHospital_course_list(hospital_course_list);
			ipdForm.setNursing_advice_list(nursing_advice_list);
			ipdForm.setInvestigationList(investigationList);
			ipdForm.setFinding_on_dischargeList(finding_on_dischargeList);
			ipdForm.setTreatment_given_list(treatment_given_list);
			ipdForm.setEmergencydetailslist(emergencydetailslist);
			ipdForm.setTreatmentgiventemplatelist(treatmentgiventemplist);
			ipdForm.setOn_exam_list(on_exam_list);
			//Old surgical list from apm_other_template table //  11 dec 2017
			/*UserProfile userProfile2 = userProfileDAO.getUserprofileDetails(loginInfo.getId());
			ArrayList<Master> otherTemplateList = masterDAO.getEmrTemplateList(userProfile2.getDiciplineName());
			ipdForm.setOtherTemplateList(otherTemplateList);*/
			
			
			InvestigationDAO investigationDAO= new JDBCInvestigationDAO(connection);
			ArrayList<InvstTemplate>templateList = investigationDAO.getTemplateList();
			ipdForm.setInvestigationtemplatelist(templateList);
			
			
			//New from ipd_template table //the surgical notes
			String surgical_template = masterDAO.getIpdTemplateId("OT Template");
			ArrayList<Master> otherTemplateList = masterDAO.getIpdTemplateListNames(surgical_template);
			ipdForm.setOtherTemplateList(otherTemplateList);
			
			
			double balance = 0;
			Client clientData = clientDAO.getClientDetails(bed.getClientid());
			ipdForm.setContact(clientData.getMobNo());
			ipdForm.setAddress(clientData.getAddress());
			String numcount=ipdDAO.getNumofAdmissionCount(bed.getClientid());
			ipdForm.setNum_admission(numcount);
			
			
			if(client.getWhopay().equals(Constants.PAY_BY_CLIENT)){
				double debit = diaryManagementDAO.getClientDebitTotal(bed.getClientid());
				double payment = diaryManagementDAO.getClientPayment(bed.getClientid());
				
				balance = debit - payment;
				
//				System.out.println(balance);
				
				ipdForm.setWhopay("Self");
			} else {
				
				ThirdParty thirdParty=client.getTpDetails();
				ipdForm.setWhopay(thirdParty.getCompanyName());
				
			}
			
			ipdForm.setBalance(balance);
			
			
			//get prescription list
			ArrayList<Priscription>dischargePriscList = ipdDAO.getDischargePrescList(selectedid);
			ipdForm.setDischargePriscList(dischargePriscList);
			session.setAttribute("priscList", dischargePriscList);
			
			int size = dischargePriscList.size();
			if (size > 0) {
				String totalchildmedids = dischargePriscList.get(size - 1).getTotalchildmedids();
				ipdForm.setTotalchildmedids(totalchildmedids);
			} else {
				ipdForm.setTotalchildmedids("0");
			}
			
			
			
			Bed bed1 = ipdDAO.getDischargeData(bed.getTreatmentepisodeid());
			ipdForm.setChkDischarge(bed1.isChkDischarge());
			ipdForm.setDischrgeOutcomes(bed1.getDischrgeOutcomes());
			
			ipdForm.setDischargeStatus(bed1.getDischargeStatus());
			ipdForm.setHospitalcourse(bed1.getHospitalcourse());
			ipdForm.setDiscadvnotes(bed1.getDiscadvnotes());
			ipdForm.setExample(bed1.getExample());
			ipdForm.setFindondischarge(bed1.getFindondischarge());
			ipdForm.setTreatmentgiven(bed1.getTreatmentgiven());
			ipdForm.setInvestigation(bed1.getInvestigation());
			ipdForm.setOtNotes(bed1.getOtNotes());
			
			ipdForm.setDeathnote(bed1.getDeathnote());
			
			ipdForm.setAppointmentText(bed1.getAppointmentText());
			ipdForm.setAnesthesia(bed1.getAnesthesia());
			ipdForm.setSurgeon(bed1.getSurgeon());
			ipdForm.setAnesthesiologist(bed1.getAnesthesiologist());
		
			
			if(bed1.getDischargedate()!=null){
				if(!bed1.getDischargedate().equals("")){
					String temp[] = bed1.getDischargedate().split(" ");
					String date = DateTimeUtils.getCommencingDate1(temp[0]);
					ipdForm.setDischargedate(date);
					String time[] = temp[1].split(":");
					String hh = time[0];
					String mm = time[1];
					ipdForm.setHour(hh);
					ipdForm.setMinute(mm);
				}
			}
			
			
			
			printformdata(selectedid);
			
			ipdForm.setClientid(bed.getClientid());
			ipdForm.setPractitionerid(bed.getPractitionerid());
			ipdForm.setConditionid(bed.getConditionid());
			
			Bed bedconditions=ipdDAO.getAllFinalCondition(selectedid, bed.getTreatmentepisodeid());
			
			ArrayList<Bed> finalConditions=new ArrayList<Bed>();
			
			
			if(bedconditions.getConditionname()!=null){
				
				for(String str:bedconditions.getConditionname().split(",")){
					
					   if(str==null){
						   continue;
					   }
				       if(str.equals("0")){
				    	   
				    	   continue;
				       }
				       int id=Integer.parseInt(str);
				       String conditionname=bedDao.getIpdConditionName(str);
				       Bed bed2=new Bed();
				       bed2.setId(id);
				       bed2.setConditionname(conditionname);
				       bed2.setConditionid(str);
				       finalConditions.add(bed2);
					
				}
			}
			ipdForm.setAbrivationid(client.getAbrivationid());
			
			ipdForm.setFinalConditions(finalConditions);
			
			ArrayList<Client> ipdCondtitionList = new ArrayList<Client>(); //clientDAO.getEmrTreatmentTypeList();
			session.setAttribute("finalConditions", finalConditions);
			session.setAttribute("ipdCondtitionmaster", ipdCondtitionList);
			
			
			ArrayList<Bed> ipdconditionlist = bedDao.getIpdConditionList(selectedid);
    		ipdForm.setIpdconditionlist(ipdconditionlist);	
    		
    		int selectedid1 = loginInfo.getId();
			
			ClinicDAO clinicListDAO = new JDBCClinicDAO(connection);
			com.apm.Registration.eu.entity.Clinic cliniclist = clinicListDAO.getCliniclistDetails(selectedid1);
			ipdForm.setClinicName(cliniclist.getClinicName());
    	
			
			
			//29 NOV 2017
			ipdForm.setAddmissionfor(bed.getAddmissionfor());
			ipdForm.setAlergies(bed.getAlergies());
			ipdForm.setChiefcomplains(bed.getChiefcomplains());
			ipdForm.setPresentillness(bed.getPresentillness());
			
			
		
			//peditric 
    		ipdForm.setBirthhist(bed.getBirthhist());
    		ipdForm.setDiethist(bed.getDiethist());
    		ipdForm.setDevelopmenthist(bed.getDevelopmenthist());
    		ipdForm.setEmmunizationhist(bed.getEmmunizationhist());
    		ipdForm.setHeadcircumference(bed.getHeadcircumference());
    		ipdForm.setMidarmcircumference(bed.getMidarmcircumference());
    		ipdForm.setLength(bed.getLength());
    		ipdForm.setWtaddmission(bed.getWtaddmission());
    		ipdForm.setWtdischarge(bed.getWtdischarge());
    		
    		//kunal
    		ipdForm.setKunal_final_diagnosis_text(bed.getKunal_final_diagnosis_text());
    		ipdForm.setKunal_manual_medicine_text(bed.getKunal_manual_medicine_text());
			
			String chief_comlint_id=masterDAO.getIpdTemplateId("Chief Complaints");
			String present_ill_id=masterDAO.getIpdTemplateId("Present Illness");
			
			ArrayList<Master> chief_complaints_list=masterDAO.getIpdTemplateListNames(chief_comlint_id);
			ArrayList<Master> present_illness_list=masterDAO.getIpdTemplateListNames(present_ill_id);
			ipdForm.setChief_complaints_list(chief_complaints_list);
			ipdForm.setPresent_illness_list(present_illness_list);
			
			ArrayList<String> otaptid = ipdDAO.getAllOTIds(selectedid,bed.getClientid());
			
			ArrayList<Master> otnoteslist =new ArrayList<Master>();
			for (String string : otaptid) {
				ArrayList<Master> otnoteslist1 = ipdDAO.getAllOtNotes(string);
				otnoteslist.addAll(otnoteslist1);
			}
			String otNotes=bed1.getOtNotes();
			if(otNotes==null){
				otNotes="";
			}
			String otNotesids = "0";
			for(Master master :otnoteslist ){
				otNotesids = otNotesids + "," +master.getId();
				otNotes = otNotes + master.getOtnotes();
			}
			ipdForm.setOtnoteslist(otnoteslist);
			ipdForm.setOtNotes(otNotes);
			ipdForm.setOtNotesids(otNotesids);
			
			ArrayList<Master> otdatesandids = ipdDAO.getOtDatesAndIdsFromdischarge(selectedid);
			ipdForm.setOtdatesandids(otdatesandids);
			int size1 = otdatesandids.size();
			if (size1 > 0) {
				String totalotids = otdatesandids.get(size1 - 1).getTotalids();
				ipdForm.setTotalotids(totalotids);
			} else {
				ipdForm.setTotalotids("0");
			}
			
			session.setAttribute("otdatesandids", otdatesandids);
			
			ArrayList<Priscription> treatmentlist= new ArrayList<Priscription>();
			
			//  
			if(loginInfo.isMedtreatgiven()){
				treatmentlist= bedDao.gettreatmentlist(selectedid);
				ipdForm.setTreatmentlist1(treatmentlist);
			}else{
				ipdForm.setTreatmentlist1(treatmentlist);
			}
			
			
			ArrayList<Investigation> investlist= new ArrayList<Investigation>();
			investlist = bedDao.getinvestigationlist(selectedid);
			ipdForm.setInvestlist(investlist);
			String treatmentgiven="";
			String invstl="";
			StringBuffer buffer = new StringBuffer();
			StringBuffer buffer1 = new StringBuffer();
			String priscid="0";
			for(Priscription prisc : treatmentlist){
			
				/* buffer.append(prisc.getMdicinenametxt()+" - "+prisc.getDosage()+" - "+String.valueOf(prisc.getDays())+" <br>");*/
				 buffer.append(prisc.getMdicinenametxt()+" <br>");
				 priscid= priscid+","+prisc.getPrischildid();
			}
			if(bed1.getTreatmentgiven()!=null){
				if(loginInfo.isMedtreatgiven()){
					treatmentgiven=bed1.getTreatmentgiven()+"<br>"+buffer.toString();
				}else{
					treatmentgiven=bed1.getTreatmentgiven();
				}
				
			}else{
				if(loginInfo.isMedtreatgiven()){
					treatmentgiven=buffer.toString();
				}
				
			}
			ipdForm.setPriscid(priscid);
			
	
			String invstids="0";
			for(Investigation invst : investlist){
				invstids = invstids+","+invst.getParentid();
				buffer1.append(invst.getInvsttype()+"--"+invst.getDate()+"<br>");
			}
			if(bed1.getInvestigation()!=null){
				invstl= bed1.getInvestigation() +"<br>"+buffer1.toString();
			}else{
				invstl= buffer1.toString();
			}
			
			ipdForm.setInvstids(invstids);
			ipdForm.setTreatmentgiven(treatmentgiven);
			ipdForm.setInvestigation(invstl);
			
			
			if(loginInfo.getInvestigation_newaccess().equals("1")){
				ipdForm.setDischrgeOutcomes("9");
			}
			//  24 May 2018
			ArrayList<Ipd> rmonoteslist = new ArrayList<Ipd>();
			rmonoteslist = ipdDAO.getRMONotesList(selectedid);
			StringBuffer stringBuffer = new StringBuffer();
			String rmonotesids ="0";
			String hospitalcorc="";
			for (Ipd ipd2 : rmonoteslist) {
				rmonotesids = rmonotesids +","+ipd2.getId();
				stringBuffer.append("Day:"+ipd2.getDay()+"<br>");
				stringBuffer.append(""+ipd2.getNotes()+"<br>");
			}
			
			if(bed1.getHospitalcourse()!=null){
				if(bed1.getHospitalcourse().equals("<br>")){
					hospitalcorc= stringBuffer.toString();
				}else{
					hospitalcorc= bed1.getHospitalcourse() +"<br>"+stringBuffer.toString();
				}
				
			}else{
				hospitalcorc= stringBuffer.toString();
			}
			
			ipdForm.setHospitalcourse(hospitalcorc);
			ipdForm.setRmonotesids(rmonotesids);
			
			if(ipdForm.getSurgeon()==null){
				if(ipd.getSurgeon()!=null){
					if(!ipd.getSurgeon().equals("")){
						ipdForm.setSurgeon(ipd.getSurgeon());
					}
				}
			}else if(ipdForm.getSurgeon().equals("0")){
				if(ipd.getSurgeon()!=null){
					if(!ipd.getSurgeon().equals("")){
						ipdForm.setSurgeon(ipd.getSurgeon());
					}
				}
			}
			
			if(ipdForm.getAnesthesiologist()==null){
				if(ipd.getAnesthesia()!=null){
					if(!ipd.getAnesthesia().equals("")){
						ipdForm.setAnesthesiologist(ipd.getAnesthesia());
					}
				}
			}else if(ipdForm.getAnesthesiologist().equals("0")){
				if(ipd.getAnesthesia()!=null){
					if(!ipd.getAnesthesia().equals("")){
						ipdForm.setAnesthesiologist(ipd.getAnesthesia());
					}
				}
			}
			
			/*//get repeat prescription list
			EmrDAO emrDAO = new JDBCEmrDAO(connection);
			ArrayList<Priscription>parentPriscList = emrDAO.getParentPriscList(bed.getClientid(),bed.getPractitionerid(),bed.getConditionid());
			ipdForm.setParentPriscList(parentPriscList);*/
		
		}
		
		
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
 }
 
 public String predischarge(){

     Connection connection=null;
	
	try {
		session.removeAttribute("finalConditions");
		if(session.getAttribute("openedb")==null){
			session.setAttribute("openedb", "ipd");
		}
		String  selectedid= request.getParameter("selectedid");
		String finaldiagnosis= request.getParameter("finaldiagnosisnew");
		String clientidnew=request.getParameter("clientidnew");
		String newipdidcurrent= request.getParameter("clip");
		connection=Connection_provider.getconnection();
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		BedDao bedDao = new JDBCBedDao(connection);
		IpdLogDAO ipdLogDAO=new JDBCIpdLogDAO(connection);
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		MasterDAO masterDAO=new JDBCMasterDAO(connection);
		UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
		DiaryManagementDAO diaryManagementDAO = new JDBCDiaryManagentDAO(connection);
		NotAvailableSlotDAO notAvailableSlotDAO=new JDBCNotAvailableSlotDAO(connection);
	/*	UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);*/
		int idn= ipdDAO.getsecondLastIpdid(clientidnew);
		selectedid=String.valueOf(idn);
		if("0".equals(selectedid)){
			selectedid=String.valueOf(ipdDAO.getipdidbyfinalDiagnosis(finaldiagnosis));
		}
		if("0".equals(selectedid)){
			selectedid= newipdidcurrent;
		}
		
		
		Ipd ipd = ipdDAO.getProcedureName(selectedid);
	
		Bed bed = bedDao.getEditIpdData(selectedid);
		session.setAttribute("sessionadmissionid", selectedid);
		String parentpriscid = ipdDAO.getParentPriscId(selectedid);
		ipdForm.setParentpriscid(parentpriscid);
		
		String procedureid = ipdDAO.getProcedureId(ipd.getProcedurename());
		ipdForm.setAppointmentText(procedureid);
		
		
		ArrayList<Master> procedureList = notAvailableSlotDAO.getProcedureList(bed.getSpeciality());
		if(procedureList.size()==0){
			procedureList = new ArrayList<Master>();
		}
		
		ipdForm.setProcedureList(procedureList);

		
		if(Integer.parseInt(bed.getIpdseqno())>0){
			ipdForm.setIpdseqno(bed.getIpdseqno());
		}else{
			ipdForm.setIpdseqno(selectedid);
		}
		
		
		Client client=clientDAO.getClientDetails(bed.getClientid());

		UserProfile userProfile=userProfileDAO.getUserprofileDetails(Integer.parseInt(bed.getPractitionerid()));
		Master discipline=masterDAO.getDisciplineData(userProfile.getDiciplineName());
		ipdForm.setDiscipline(discipline.getDiscipline());
		ipdForm.setDoctor_name(userProfile.getInitial()+" "+userProfile.getFirstname()+" "+userProfile.getLastname());
		ipdForm.setDoctor_phone(userProfile.getMobile());
		
		String discharge_default_id=masterDAO.getIpdTemplateId("Discharge Default");
		String hospital_course_id=masterDAO.getIpdTemplateId("Hospital Course");
		String nursing_advice_id=masterDAO.getIpdTemplateId("Nursing Advice");
		String operative_notes_id= masterDAO.getIpdTemplateId("Operative Notes");
		String invenstigations= masterDAO.getIpdTemplateId("Investigations");
		String finding_on_discharge= masterDAO.getIpdTemplateId("FINDING ON DISCHARGE");
		String treatment_given_id= masterDAO.getIpdTemplateId("Discharge Treatment Given");
		String emergencydetailsid = masterDAO.getIpdTemplateId("Emergency Details");
		String treatmentgiventemplateid= masterDAO.getIpdTemplateId("Treatment Given");
		ArrayList<Bed> bedLogList=ipdLogDAO.getBedChangeLogList(bed.getClientid(),selectedid);
		ipdForm.setBedLogList(bedLogList);
		
		//int size=bedLogList.size()-1;
		if(bedLogList.size()>0) {
			bedLogList.get(0).setStatus("1");
		}
		
		ArrayList<Master> discharge_default_list=masterDAO.getIpdTemplateListNames(discharge_default_id);
		ArrayList<Master> hospital_course_list=masterDAO.getIpdTemplateListNames(hospital_course_id);
		ArrayList<Master> nursing_advice_list=masterDAO.getIpdTemplateListNames(nursing_advice_id);
		ArrayList<Master> operativeList= masterDAO.getIpdTemplateListNames(operative_notes_id);
		ArrayList<Master> investigationList= masterDAO.getIpdTemplateListNames(invenstigations);
		ArrayList<Master> finding_on_dischargeList=masterDAO.getIpdTemplateListNames(finding_on_discharge);
		ArrayList<Master> treatment_given_list= masterDAO.getIpdTemplateListNames(treatment_given_id);
		ArrayList<Master> emergencydetailslist = masterDAO.getIpdTemplateListNames(emergencydetailsid);
		ArrayList<Master> treatmentgiventemplist= masterDAO.getIpdTemplateListNames(treatmentgiventemplateid);
		ipdForm.setOperativeList(operativeList);
		ipdForm.setDischarge_default_list(discharge_default_list);
		ipdForm.setHospital_course_list(hospital_course_list);
		ipdForm.setNursing_advice_list(nursing_advice_list);
		ipdForm.setInvestigationList(investigationList);
		ipdForm.setFinding_on_dischargeList(finding_on_dischargeList);
		ipdForm.setTreatment_given_list(treatment_given_list);
		ipdForm.setEmergencydetailslist(emergencydetailslist);
		ipdForm.setTreatmentgiventemplatelist(treatmentgiventemplist);
		//Old surgical list from apm_other_template table //  11 dec 2017
		/*UserProfile userProfile2 = userProfileDAO.getUserprofileDetails(loginInfo.getId());
		ArrayList<Master> otherTemplateList = masterDAO.getEmrTemplateList(userProfile2.getDiciplineName());
		ipdForm.setOtherTemplateList(otherTemplateList);*/
		
		//New from ipd_template table //the surgical notes
		String surgical_template = masterDAO.getIpdTemplateId("OT Template");
		ArrayList<Master> otherTemplateList = masterDAO.getIpdTemplateListNames(surgical_template);
		ipdForm.setOtherTemplateList(otherTemplateList);
		
		
		double balance = 0;
		Client clientData = clientDAO.getClientDetails(bed.getClientid());
		ipdForm.setContact(clientData.getMobNo());
		ipdForm.setAddress(clientData.getAddress());
		String numcount=ipdDAO.getNumofAdmissionCount(bed.getClientid());
		ipdForm.setNum_admission(numcount);
		
		
		if(client.getWhopay().equals(Constants.PAY_BY_CLIENT)){
			double debit = diaryManagementDAO.getClientDebitTotal(bed.getClientid());
			double payment = diaryManagementDAO.getClientPayment(bed.getClientid());
			
			balance = debit - payment;
			
//			System.out.println(balance);
			
			ipdForm.setWhopay("Self");
		} else {
			
			ThirdParty thirdParty=client.getTpDetails();
			ipdForm.setWhopay(thirdParty.getCompanyName());
			
		}
		
		ipdForm.setBalance(balance);
		
		
		//get prescription list
		ArrayList<Priscription>dischargePriscList = ipdDAO.getDischargePrescList(selectedid);
		ipdForm.setDischargePriscList(dischargePriscList);
		session.setAttribute("priscList", dischargePriscList);
		
		int size = dischargePriscList.size();
		if (size > 0) {
			String totalchildmedids = dischargePriscList.get(size - 1).getTotalchildmedids();
			ipdForm.setTotalchildmedids(totalchildmedids);
		} else {
			ipdForm.setTotalchildmedids("0");
		}
		
		
		
		Bed bed1 = ipdDAO.getDischargeData(bed.getTreatmentepisodeid());
		ipdForm.setChkDischarge(bed1.isChkDischarge());
		ipdForm.setDischrgeOutcomes(bed1.getDischrgeOutcomes());
		ipdForm.setDischargeStatus(bed1.getDischargeStatus());
		ipdForm.setHospitalcourse(bed1.getHospitalcourse());
		ipdForm.setDiscadvnotes(bed1.getDiscadvnotes());
		ipdForm.setExample(bed1.getExample());
		ipdForm.setFindondischarge(bed1.getFindondischarge());
		ipdForm.setTreatmentgiven(bed1.getTreatmentgiven());
		ipdForm.setInvestigation(bed1.getInvestigation());
		ipdForm.setOtNotes(bed1.getOtNotes());
		
		ipdForm.setDeathnote(bed1.getDeathnote());
		
		ipdForm.setAppointmentText(bed1.getAppointmentText());
		ipdForm.setAnesthesia(bed1.getAnesthesia());
		ipdForm.setSurgeon(bed1.getSurgeon());
		ipdForm.setAnesthesiologist(bed1.getAnesthesiologist());
	
		
		if(bed1.getDischargedate()!=null){
			if(!bed1.getDischargedate().equals("")){
				String temp[] = bed1.getDischargedate().split(" ");
				String date = DateTimeUtils.getCommencingDate1(temp[0]);
				ipdForm.setDischargedate(date);
				String time[] = temp[1].split(":");
				String hh = time[0];
				String mm = time[1];
				ipdForm.setHour(hh);
				ipdForm.setMinute(mm);
			}
		}
		
		
		
		printformdata(selectedid);
		
		ipdForm.setClientid(bed.getClientid());
		ipdForm.setPractitionerid(bed.getPractitionerid());
		ipdForm.setConditionid(bed.getConditionid());
		
		Bed bedconditions=ipdDAO.getAllFinalCondition(selectedid, bed.getTreatmentepisodeid());
		
		ArrayList<Bed> finalConditions=new ArrayList<Bed>();
		
		
		if(bedconditions.getConditionname()!=null){
			
			for(String str:bedconditions.getConditionname().split(",")){
				
				   if(str==null){
					   continue;
				   }
			       if(str.equals("0")){
			    	   
			    	   continue;
			       }
			       int id=Integer.parseInt(str);
			       String conditionname=bedDao.getIpdConditionName(str);
			       Bed bed2=new Bed();
			       bed2.setId(id);
			       bed2.setConditionname(conditionname);
			       bed2.setConditionid(str);
			       finalConditions.add(bed2);
				
			}
		}
		ipdForm.setAbrivationid(client.getAbrivationid());
		
		ipdForm.setFinalConditions(finalConditions);
		
		ArrayList<Client> ipdCondtitionList = new ArrayList<Client>(); //clientDAO.getEmrTreatmentTypeList();
		session.setAttribute("finalConditions", finalConditions);
		session.setAttribute("ipdCondtitionmaster", ipdCondtitionList);
		
		
		ArrayList<Bed> ipdconditionlist = bedDao.getIpdConditionList(selectedid);
		ipdForm.setIpdconditionlist(ipdconditionlist);	
		
		int selectedid1 = loginInfo.getId();
		
		ClinicDAO clinicListDAO = new JDBCClinicDAO(connection);
		com.apm.Registration.eu.entity.Clinic cliniclist = clinicListDAO.getCliniclistDetails(selectedid1);
		ipdForm.setClinicName(cliniclist.getClinicName());
	
		
		
		//29 NOV 2017
		ipdForm.setAddmissionfor(bed.getAddmissionfor());
		ipdForm.setAlergies(bed.getAlergies());
		ipdForm.setChiefcomplains(bed.getChiefcomplains());
		ipdForm.setPresentillness(bed.getPresentillness());
		
		
	
		//peditric 
		ipdForm.setBirthhist(bed.getBirthhist());
		ipdForm.setDiethist(bed.getDiethist());
		ipdForm.setDevelopmenthist(bed.getDevelopmenthist());
		ipdForm.setEmmunizationhist(bed.getEmmunizationhist());
		ipdForm.setHeadcircumference(bed.getHeadcircumference());
		ipdForm.setMidarmcircumference(bed.getMidarmcircumference());
		ipdForm.setLength(bed.getLength());
		ipdForm.setWtaddmission(bed.getWtaddmission());
		ipdForm.setWtdischarge(bed.getWtdischarge());
		
		
		String chief_comlint_id=masterDAO.getIpdTemplateId("Chief Complaints");
		String present_ill_id=masterDAO.getIpdTemplateId("Present Illness");
		
		ArrayList<Master> chief_complaints_list=masterDAO.getIpdTemplateListNames(chief_comlint_id);
		ArrayList<Master> present_illness_list=masterDAO.getIpdTemplateListNames(present_ill_id);
		ipdForm.setChief_complaints_list(chief_complaints_list);
		ipdForm.setPresent_illness_list(present_illness_list);
		
		ArrayList<String> otaptid = ipdDAO.getAllOTIds(selectedid,bed.getClientid());
		
		ArrayList<Master> otnoteslist =new ArrayList<Master>();
		for (String string : otaptid) {
			ArrayList<Master> otnoteslist1 = ipdDAO.getAllOtNotes(string);
			otnoteslist.addAll(otnoteslist1);
		}
		String otNotes=bed1.getOtNotes();
		if(otNotes==null){
			otNotes="";
		}
		String otNotesids = "0";
		for(Master master :otnoteslist ){
			otNotesids = otNotesids + "," +master.getId();
			otNotes = otNotes + master.getOtnotes();
		}
		ipdForm.setOtnoteslist(otnoteslist);
		ipdForm.setOtNotes(otNotes);
		ipdForm.setOtNotesids(otNotesids);
		
		ArrayList<Master> otdatesandids = ipdDAO.getOtDatesAndIdsFromdischarge(selectedid);
		ipdForm.setOtdatesandids(otdatesandids);
		int size1 = otdatesandids.size();
		if (size1 > 0) {
			String totalotids = otdatesandids.get(size1 - 1).getTotalids();
			ipdForm.setTotalotids(totalotids);
		} else {
			ipdForm.setTotalotids("0");
		}
		
		session.setAttribute("otdatesandids", otdatesandids);
		
		ArrayList<Priscription> treatmentlist= new ArrayList<Priscription>();
		
		//  
		if(loginInfo.isMedtreatgiven()){
			treatmentlist= bedDao.gettreatmentlist(selectedid);
			ipdForm.setTreatmentlist1(treatmentlist);
		}else{
			ipdForm.setTreatmentlist1(treatmentlist);
		}
		
		
		ArrayList<Investigation> investlist= new ArrayList<Investigation>();
		investlist = bedDao.getinvestigationlist(selectedid);
		ipdForm.setInvestlist(investlist);
		String treatmentgiven="";
		String invstl="";
		StringBuffer buffer = new StringBuffer();
		StringBuffer buffer1 = new StringBuffer();
		String priscid="0";
		for(Priscription prisc : treatmentlist){
		
			/* buffer.append(prisc.getMdicinenametxt()+" - "+prisc.getDosage()+" - "+String.valueOf(prisc.getDays())+" <br>");*/
			 buffer.append(prisc.getMdicinenametxt()+" <br>");
			 priscid= priscid+","+prisc.getPrischildid();
		}
		if(bed1.getTreatmentgiven()!=null){
			if(loginInfo.isMedtreatgiven()){
				treatmentgiven=bed1.getTreatmentgiven()+"<br>"+buffer.toString();
			}else{
				treatmentgiven=bed1.getTreatmentgiven();
			}
			
		}else{
			if(loginInfo.isMedtreatgiven()){
				treatmentgiven=buffer.toString();
			}
			
		}
		ipdForm.setPriscid(priscid);
		

		String invstids="0";
		for(Investigation invst : investlist){
			invstids = invstids+","+invst.getParentid();
			buffer1.append(invst.getInvsttype()+"--"+invst.getDate()+"<br>");
		}
		if(bed1.getInvestigation()!=null){
			invstl= bed1.getInvestigation() +"<br>"+buffer1.toString();
		}else{
			invstl= buffer1.toString();
		}
		
		ipdForm.setInvstids(invstids);
		ipdForm.setTreatmentgiven(treatmentgiven);
		ipdForm.setInvestigation(invstl);
		
		//  24 May 2018
		ArrayList<Ipd> rmonoteslist = new ArrayList<Ipd>();
		rmonoteslist = ipdDAO.getRMONotesList(selectedid);
		StringBuffer stringBuffer = new StringBuffer();
		String rmonotesids ="0";
		String hospitalcorc="";
		for (Ipd ipd2 : rmonoteslist) {
			rmonotesids = rmonotesids +","+ipd2.getId();
			stringBuffer.append("Day:"+ipd2.getDay()+"<br>");
			stringBuffer.append(""+ipd2.getNotes()+"<br>");
		}
		
		if(bed1.getHospitalcourse()!=null){
			if(bed1.getHospitalcourse().equals("<br>")){
				hospitalcorc= stringBuffer.toString();
			}else{
				hospitalcorc= bed1.getHospitalcourse() +"<br>"+stringBuffer.toString();
			}
			
		}else{
			hospitalcorc= stringBuffer.toString();
		}
		
		ipdForm.setHospitalcourse(hospitalcorc);
		ipdForm.setRmonotesids(rmonotesids);
		
		if(ipdForm.getSurgeon()==null){
			if(ipd.getSurgeon()!=null){
				if(!ipd.getSurgeon().equals("")){
					ipdForm.setSurgeon(ipd.getSurgeon());
				}
			}
		}else if(ipdForm.getSurgeon().equals("0")){
			if(ipd.getSurgeon()!=null){
				if(!ipd.getSurgeon().equals("")){
					ipdForm.setSurgeon(ipd.getSurgeon());
				}
			}
		}
		
		if(ipdForm.getAnesthesiologist()==null){
			if(ipd.getAnesthesia()!=null){
				if(!ipd.getAnesthesia().equals("")){
					ipdForm.setAnesthesiologist(ipd.getAnesthesia());
				}
			}
		}else if(ipdForm.getAnesthesiologist().equals("0")){
			if(ipd.getAnesthesia()!=null){
				if(!ipd.getAnesthesia().equals("")){
					ipdForm.setAnesthesiologist(ipd.getAnesthesia());
				}
			}
		}
		
		/*//get repeat prescription list
		EmrDAO emrDAO = new JDBCEmrDAO(connection);
		ArrayList<Priscription>parentPriscList = emrDAO.getParentPriscList(bed.getClientid(),bed.getPractitionerid(),bed.getConditionid());
		ipdForm.setParentPriscList(parentPriscList);*/
	
		//getting the basic details
		ipdForm.setClientip(newipdidcurrent);
		Bed bed4 = bedDao.getEditIpdData(newipdidcurrent);
		Client client3 = clientDAO.getClientDetails(bed4.getClientid());
		String fullname = client3.getTitle() + " " + client3.getFirstName() + " "+client3.getMiddlename()+" " + client3.getLastName();
		ipdForm.setClient(fullname);
		  UserProfile userProfile3=userProfileDAO.getUserprofileDetails(Integer.parseInt(bed4.getPractitionerid()));
		   Master discipline3=masterDAO.getDisciplineData(userProfile3.getDiciplineName());
		   ipdForm.setDiscipline(discipline3.getDiscipline());
		   ipdForm.setDoctor_name(userProfile3.getInitial()+" "+userProfile3.getFirstname()+" "+userProfile3.getLastname());
		   
		   ipdForm.setQualification(userProfile3.getQualification());
		   ipdForm.setAddress(client3.getAddress());
		   ipdForm.setContact(client3.getMobNo());
		   ipdForm.setDepartment(userProfile3.getSpecialization());
		   ipdForm.setAbrivationid(client3.getAbrivationid());
		   ipdForm.setAgegender(DateTimeUtils.getAge1(client3.getDob())+" / "+client3.getGender());
		   
		   String temp[] = bed4.getAdmissiondate().split(" ");
			String date = DateTimeUtils.getCommencingDate1(temp[0]);
			ipdForm.setAdmissiondate(date);
			String time[] = temp[1].split(":");
			String hh = time[0];
			String mm = time[1];
			ipdForm.setHour(hh);
			ipdForm.setMinute(mm);
			
			if(client3.getWhopay().equals(Constants.PAY_BY_CLIENT)){
				double debit = diaryManagementDAO.getClientDebitTotal(bed4.getClientid());
				double payment = diaryManagementDAO.getClientPayment(bed4.getClientid());
				
				balance = debit - payment;
				
//				System.out.println(balance);
				
				ipdForm.setWhopay("Self");
			} else {
				
				ThirdParty thirdParty=client.getTpDetails();
				ipdForm.setWhopay(thirdParty.getCompanyName());
				
			}
			
			
			if(bed4.getSecndryconsult()!=null){
				if(bed4.getSecndryconsult().equals("0")){
					bed4.setSecndryconsult(null);
				}
			}
			
			
			if(bed4.getSecndryconsult()!=null){
				if(!bed4.getSecndryconsult().equals("")){
					
					 ArrayList<String> allConsultantList= ipdDAO.getAllSecondaryConsultList(newipdidcurrent);  
					 ArrayList<UserProfile> allconsultantlistwithdepart = ipdDAO.getSecConsWithDepartment(newipdidcurrent);
					 ipdForm.setAllconsultantlistwithdepart(allconsultantlistwithdepart);
					 ipdForm.setAllConsultantList(allConsultantList);
					
				}
			} else {
				
				
			}
			
			ArrayList<Bed> bedLogList2=ipdLogDAO.getBedChangeLogList(bed4.getClientid(),newipdidcurrent);
			ipdForm.setBedLogList(bedLogList2);
			
			ipdDAO.setfinalcond(newipdidcurrent, bed4.getTreatmentepisodeid(), finaldiagnosis);
			
			
			Bed bedconditions2=ipdDAO.getAllFinalCondition(newipdidcurrent, bed4.getTreatmentepisodeid());
			
			
			ArrayList<Bed> finalConditions2=new ArrayList<Bed>();
			
			
			if(bedconditions2.getConditionname()!=null){
				
				for(String str:bedconditions2.getConditionname().split(",")){
					
					   if(str==null){
						   continue;
					   }
				       if(str.equals("0")){
				    	   
				    	   continue;
				       }
				       int id=Integer.parseInt(str);
				       String conditionname=bedDao.getIpdConditionName(str);
				       Bed bed6=new Bed();
				       bed6.setId(id);
				       bed6.setConditionname(conditionname);
				       bed6.setConditionid(str);
				       finalConditions2.add(bed6);
					
				}
			}
			ipdForm.setAbrivationid(client.getAbrivationid());
			
			ipdForm.setFinalConditions(finalConditions2);
			
			
			ipdForm.setChiefcomplains(bed4.getChiefcomplains());
			ipdForm.setOnexamination(bed4.getOnexamination());
			ipdForm.setTreatmenthistory(bed4.getTreatmenthistory());
			ipdForm.setEarlierinvest(bed4.getEarlierinvest());
			ipdForm.setDischargeStatus("0");
			ipdForm.setDischrgeOutcomes("0");
			  if(Integer.parseInt(bed4.getIpdseqno())>0){
					ipdForm.setIpdseqno(bed4.getIpdseqno());
				}else{
					ipdForm.setIpdseqno(newipdidcurrent);
				}

			
			Bed bed6= new  Bed();
			bed6= (Bed)session.getAttribute("bed");
			bed6.setChiefcomplains(bed4.getChiefcomplains());
			bed6.setOnexamination(bed4.getOnexamination());
			bed6.setTreatmenthistory(bed4.getTreatmenthistory());
			bed6.setEarlierinvest(bed4.getEarlierinvest());
			session.setAttribute("bed", bed6);
			ArrayList<Client> ipdCondtitionList2= new ArrayList<Client>(); //clientDAO.getEmrTreatmentTypeList();
			session.setAttribute("finalConditions", finalConditions2);
			session.setAttribute("ipdCondtitionmaster", ipdCondtitionList2);
			
			ArrayList<Bed> ipdconditionlist2 = bedDao.getIpdConditionList(newipdidcurrent);
			ipdForm.setIpdconditionlist(ipdconditionlist2);	
		
	}
	
	
	
	catch(Exception e) {
		e.printStackTrace();
	}

	 
	 return "predischarge";
 }
 
public String savefromopdtoipd(){
	 Connection connection= null;
	 String wardid= request.getParameter("ward");
	 String bedid= request.getParameter("bed");
	 String clientid=request.getParameter("clientid");
	 String practionerid= request.getParameter("practitioner");
	 try {
		connection= Connection_provider.getconnection();
		connection=Connection_provider.getconnection();
		BedDao bedDao=new JDBCBedDao(connection);
		UserProfileDAO profileDAO=new JDBCUserProfileDAO(connection);
		ClinicDAO clinicDAO= new JDBCClinicDAO(connection);
		TreatmentEpisodeDAO treatmentEpisodeDAO=new JDBCTreatmentEpisode(connection);
		ClientDAO clientDAO=new JDBCClientDAO(connection);
		IpdDAO ipdDAO = new JDBCIpdDAO(connection);
		Bed bed=new Bed();
		ipdForm.setClientid(clientid);
		ipdForm.setPractitionerid(practionerid);
		
		Client client=clientDAO.getClientDetails(clientid);
		CompleteAptmDAO completeAptmDAO=new JDBCCompleteAptmDAO(connection);

		String payee="0";
		{
			payee="Client";
			String dateTime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
        	DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); 
        	Date date2=Calendar.getInstance().getTime();
        	String referalDate=df.format(date2);
        	String name="IPD"+client.getFirstName()+""+client.getLastName()+dateTime;
        	TreatmentEpisode treatmentEpisode=new TreatmentEpisode();
        	treatmentEpisode.setClientId(Integer.parseInt(ipdForm.getClientid()));
        	treatmentEpisode.setPayby("Client");
        	treatmentEpisode.setConsultationLimit("3");
        	treatmentEpisode.setSessions("1");
        	treatmentEpisode.setDiaryUser(practionerid);
        	treatmentEpisode.setTreatmentEpisodeName(name);
        	treatmentEpisode.setReferalDate(referalDate);
        	
        	Calendar calendar=Calendar.getInstance();
        	calendar.add(Calendar.DATE, 30);
        	String referalEndDate=df.format(calendar.getTime());
        	treatmentEpisode.setReferalendDate(referalEndDate);
        	treatmentEpisode.setSpendLimit("3");
        	dateTime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
        	String clientName = client.getTitle()+" "+client.getFirstName()+" "+ client.getLastName();
        	treatmentEpisode.setClientName(clientName);
        	int tretmentEpisodeId=treatmentEpisodeDAO.saveTreatmentEpisode(treatmentEpisode, dateTime);
        	
        	int res = ipdDAO.saveDischargeCheckList(tretmentEpisodeId,clientid);
        	ipdForm.setTreatmentepisodeid(""+tretmentEpisodeId+"");
        	bed.setTreatmentepisodeid(ipdForm.getTreatmentepisodeid());
        	bed.setTpid("0");
		            
		}
		String tpid=""; 
		int r=clientDAO.updatePayeeofPatient(clientid,payee,tpid);
    	Client clientcont= new Client(); 
        r=clientDAO.updateEmergencyDetails(clientcont);
        ipdForm.setClientid(clientid);
        
 		bed.setClientid(ipdForm.getClientid());
 		bed.setPractitionerid(ipdForm.getPractitionerid());
 		bed.setConditionid(ipdForm.getConditionid());
 		if(ipdForm.getPractitionerid()!=null){
	       if(!ipdForm.getPractitionerid().equals("")){
	           int practitionerid=Integer.parseInt(ipdForm.getPractitionerid());
	           UserProfile userProfile=profileDAO.getUserprofileDetails(practitionerid);
	           bed.setSpeciality(userProfile.getDiciplineName());
	           ipdForm.setDepartment(userProfile.getDiciplineName());
	       }
 		}
		         		
 		ipdForm.setConditionid("0");
	
 		ipdForm.setWardid(wardid);
 		ipdForm.setBedid(bedid);
 		
 		bed.setDepartment(ipdForm.getDepartment());
 		bed.setMlcrefdoctor("");
 		
		String refenceid=ipdForm.getRefferedby(); 
		String name1= clientDAO.getReferenceName(refenceid);
		bed.setReferenceid(refenceid);
		
		bed.setRefferedby(name1);
		bed.setWardid(ipdForm.getWardid());
		bed.setBedid(ipdForm.getBedid());
		bed.setStatus(ipdForm.getStatus());
		
		
		bed.setAddmissionfor("0");
		bed.setReimbursment("0");
		bed.setSecndryconsult("0");
		bed.setMlccase("0");
		bed.setMlcno("0");
		bed.setAdmissiondetails("0");
		bed.setSuggestedtrtment("0");
		bed.setHosp("0");
		bed.setPackagename("0");
		//chiefcomplains, presentillness, pasthistory, personalhist, familyhist, onexamination, treatmentepisodeid
		bed.setChiefcomplains(" ");
		bed.setPresentillness(" ");
		bed.setPasthistory(" ");
		bed.setPersonalhist(" ");
		bed.setFamilyhist(" ");
		bed.setSurgicalnotes(" ");
		bed.setOnexamination(" ");
	    bed.setEarlierinvest(" ");
	    bed.setAdmission_reason(" ");
	
		//peditric
	    
	     bed.setBirthhist(" ");
	     bed.setDiethist(" ");
		bed.setDevelopmenthist(" ");
		bed.setEmmunizationhist(" ");
		bed.setHeadcircumference(" ");
		bed.setMidarmcircumference(" ");
		bed.setLength(" ");
		bed.setWtaddmission(" ");
		bed.setWtdischarge(" ");
		
		bed.setSuggestoper("0");
		bed.setSystdepartment("0");
		bed.setFpcondition("0");
		bed.setFpnotest("0");
		bed.setNauseacondition("0");
		bed.setNauseanotes("0");
		bed.setFnucondition("0");
		bed.setFnunotes("0");
		bed.setSmcondition("0");
		bed.setSmnotes("0");
		bed.setChestpaincond("0");
		bed.setChestpainnotes("0");
		bed.setDimvisioncond("0");
		bed.setDimvisionnotes("0");
		
		//dimvisionnotes, hgucondition, hgunotes, hmcondition, hmnotes, jointpaincond, jointpainnotes, 
		bed.setHgucondition("0");
		bed.setHgunotes("0");
		bed.setHmcondition("0");
		bed.setHmnotes("0");
		bed.setJointpaincond("0");
		bed.setJointpainnotes("0");
		
		//constipationcond, constpationnotes, specialnotes, edemafeetcondi, edemafeetnotes, hematuriacondi, 
		bed.setConstipationcond("0");
		bed.setConstpationnotes("0");
		bed.setSpecialnotes("0");
		bed.setEdemafeetcondi("0");
		bed.setEdemafeetnotes("0");
		bed.setHematuriacondi("0");
		bed.setHematurianotes("0");
		
		//hematurianotes, bmcondition, bmnotes, oliguriacondi, oligurianotes, pstgucondi, pstgunotes, 
		bed.setBmcondition("");
		bed.setBmnotes(ipdForm.getBmnotes());
		bed.setOliguriacondi(ipdForm.getOliguriacondi());
		bed.setOligurianotes(ipdForm.getOligurianotes());
		bed.setPstgucondi("0");
		bed.setPstgunotes("0");
		
		//bmecondition, bmenotes, tnecondition, tnenotes, weaknesscondi, weaknessnotes, ihcondition, ihnotes
		bed.setBmecondition(ipdForm.getBmecondition());
		bed.setBmenotes(ipdForm.getBmenotes());
		bed.setTnecondition(ipdForm.getTnecondition());
		bed.setTnenotes(ipdForm.getTnenotes());
		bed.setWeaknesscondi(ipdForm.getWeaknesscondi());
		bed.setWeaknessnotes(ipdForm.getWeaknessnotes());
		bed.setIhcondition(ipdForm.getIhcondition());
		bed.setIhnotes(ipdForm.getIhnotes());
		bed.setAlergies(ipdForm.getAlergies());
		
		//gynic details
		bed.setAlcohal(ipdForm.getAlcohal());
		bed.setDrugs(ipdForm.getDrugs());
		bed.setOther_medication(ipdForm.getOther_medication());
		bed.setTobaco(ipdForm.getTobaco());
		bed.setTobaconotes(ipdForm.getTobaconotes());
		bed.setSmoking(ipdForm.getSmoking());
		
		bed.setAge_menarche(ipdForm.getAge_menarche());
		bed.setLmp(ipdForm.getLmp());
		bed.setLlmp(ipdForm.getLlmp());
		bed.setPmc(ipdForm.getPmc());
		
		
		bed.setCycle_length(ipdForm.getCycle_length());
		bed.setDuration_flow(ipdForm.getDuration_flow());
		bed.setAmount_flow(ipdForm.getAmount_flow());
		bed.setDysmenorrhea(ipdForm.getDysmenorrhea());
		bed.setDyspareunia(ipdForm.getDyspareunia());
		bed.setHopassing_clots(ipdForm.getHopassing_clots());
		
		bed.setWhite_disc_itching(ipdForm.getWhite_disc_itching());
		bed.setIntercourse_freq(ipdForm.getIntercourse_freq());
		bed.setGalactorrea(ipdForm.getGalactorrea());
		
		bed.setHo_contraception(ipdForm.getHo_contraception());
		bed.setRubella_vaccine(ipdForm.getRubella_vaccine());
		bed.setMenopause(ipdForm.getMenopause());
		bed.setNulligravida(ipdForm.getNulligravida());
		bed.setCurrent_pregnent(ipdForm.getCurrent_pregnent());
		bed.setIud(ipdForm.getIud());
		
		bed.setLive_boys(ipdForm.getLive_boys());
		bed.setLive_girls("0");
		bed.setStillbirths("0");
		bed.setAbortions("0");
		bed.setDeath_children("0");
		
		//parity_aboration_notes,p,l,a,d
		bed.setParity_abortion_notes(ipdForm.getParity_abortion_notes());
		bed.setP(ipdForm.getP());
		bed.setL(ipdForm.getL());
		bed.setA(ipdForm.getA());
		bed.setD(ipdForm.getD());
		
		bed.setMlcrefdoctor(ipdForm.getMlcrefdoctor());
		bed.setMlccase(ipdForm.getMlccase());
		        		
		DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
    	Date date12=Calendar.getInstance().getTime();
    	String referalDate1=df1.format(date12);
    	String date="";
    	 //for bms
        String bmsipddate=bedDao.getdatebyClientid(clientid);
        if(loginInfo.isBams1()) {
        	date=bmsipddate+" "+referalDate1.split(" ")[1];
        }else {
           date= referalDate1;
        }
		//String date= referalDate1;
		
		String xx[]=date.split(" ");
        ipdForm.setAdmissiondate(DateTimeUtils.getCommencingDate1(xx[0]));
        String xxx[]=xx[1].split(":");
        ipdForm.setHour(xxx[0]);
        ipdForm.setMinute(xxx[1]);
		
       
        
		String stdchargesetup= clinicDAO.getStdChargeSetup(loginInfo.getClinicid());
		bed.setStdchargesetup(stdchargesetup);
		/*String date = DateTimeUtils.getCommencingDate1(ipdForm.getAdmissiondate()) + " " + ipdForm.getHour() + ":" + ipdForm.getMinute() + ":20" ;*/
		bed.setAddmitedbyuserid(loginInfo.getUserId());
		
		String action1 = "0";
		int maxid = bedDao.getMaxIpdId();
		maxid = maxid + 1;
		        		
		boolean checkbedidexsist = bedDao.checkBedidExixtst(ipdForm.getBedid());
		if(!checkbedidexsist){
			//bed.setIpdabrivationid(ipdDAO.generateIPDSequenceNewFormat("0"));
			if(loginInfo.isKalmegha()){
    			bed.setIpdabrivationid(bedDao.generateIPDKalmeghAbrivation(ipdForm.getAdmissiondate().split(" ")[0],"0"));
    		}else{
    			bed.setIpdabrivationid(ipdDAO.generateIPDSequenceNewFormat("0"));
    		}
			String lastopdno=bedDao.getlastOpdNo(clientid);
			int admissionid=bedDao.addIpdAdmissionForm(bed,date,action1,maxid);
			int set=ipdDAO.settoShiftedFromIpd(admissionid,lastopdno);
		               
			String logcommencing = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
	        int log = bedDao.saveAdmissionLog(bed,date,admissionid);
		                  
		    String cutofftime = bedDao.getHospitalCutoffTime(loginInfo.getClinicid());
		    String logdate= date;
		    if(!cutofftime.equals("0")){
		    	int log1 = bedDao.saveBedChangeLog(bed,logdate,admissionid,logcommencing,date,0);
		    }else{
		    	int log1 = bedDao.saveBedChangeLog(bed,date,admissionid,logcommencing,date,0);
		    }
		    if(!client.getCasualtyid().equals("0")){
		    	int updc = bedDao.updateCasualtyid(client.getId());
		    }
		    
		    //getting registration charge
		    Clinic registrationcharge=completeAptmDAO.getIpdRegistrationCharge(loginInfo.getClinicid()); 
		    if(registrationcharge.getIpdregcharge()!=null && registrationcharge.getIpdregtype()!=null){
		    	int amt=Integer.parseInt(registrationcharge.getIpdregcharge());  
		        String ctype=registrationcharge.getIpdregtype();
		        if(!ctype.equals("None")){
		        	if(ctype.equals("All")){
		        		saveCharges(String.valueOf(amt), bed, client, admissionid, completeAptmDAO,ipdForm.getAdmissiondate());
		          	}else if(ctype.equals("Third Party")){
		          		if(client.getWhopay().equalsIgnoreCase("Third Party")) {
		          			saveCharges(String.valueOf(amt), bed, client, admissionid, completeAptmDAO,ipdForm.getAdmissiondate());
		          		}
		          	}else if(ctype.equals("Self")){
		          		if(client.getWhopay().equalsIgnoreCase("Self") || client.getWhopay().equalsIgnoreCase("Client")){
		          			saveCharges(String.valueOf(amt), bed, client, admissionid, completeAptmDAO,ipdForm.getAdmissiondate());
		          		}
		          	}
		        }
		    }
		    if(bed.getMlccase()!=null){
		    	if(bed.getMlccase().equals("1")){
		    		//lokesh mlc charge reg 2/11/18
		        	if(registrationcharge.getMlc_charge()!=0){
		        		saveChargesMLC(String.valueOf(registrationcharge.getMlc_charge()),bed, client, admissionid, completeAptmDAO,ipdForm.getAdmissiondate());
		        	}
		    	}
		   }
		   if(tpid==null){
			   tpid="0";
		   }
		   if(tpid.equals("")){
			   tpid="0";
		   }
		   //adding charges
		   //old code
		   //ArrayList<Master> chargeList=completeAptmDAO.getStandardCharges(bed.getWardid(), client.getWhopay());
		   AppointmentTypeDAO appointmentTypeDAO= new JDBCAppointmentTypeDAO(connection);
		   //new code
		   ArrayList<Master> chargeList=appointmentTypeDAO.getStandardChargeList(ipdForm.getWardid(), tpid, payee,loginInfo);
		   String stdcharges="0";
		   int invoiceid=0;
		          		
		          		
	  		/*String date = DateTimeUtils.getDateinSimpleFormate(new Date());
			String stemp[] = date.split(" ");
			
			String temp[] = stemp[0].split("-");
			date = temp[2] + "-" + temp[1] + "-" + temp[0];*/
	  		
	  		String date1 = DateTimeUtils.getCommencingDate1(ipdForm.getAdmissiondate());
			
	  		
	  		if(chargeList.size()!=0){ 
	  			
	  			/*CompleteAppointment appointment=new CompleteAppointment();
	  			appointment.setClientId(bed.getClientid());
	  			appointment.setPractitionerId(bed.getPractitionerid());
	  			appointment.setChargeType("Charge");
	  			appointment.setLocation("1");
	  		    appointment.setAdditionalcharge_id("001");
	  		    appointment.setIpdid(admissionid);
	  		    appointment.setInvoiceDate(date1);
	  		    appointment.setIpd("1");
	  		    appointment.setAppointmentid("0");
	  		    appointment.setGinvstid("0");
	  		    appointment.setWardid(bed.getWardid());
	  		    if(client.getWhopay()!=null){
	  		    	
	  		    	if(client.getWhopay().equals("Self") || client.getWhopay().equals("Client")){
	  		    	       
	  		    		appointment.setPolicyExcess("0");
	  		    		appointment.setPayBuy("0");
	  		    	} else {
	  		    		appointment.setPolicyExcess("1");
	  		    		appointment.setPayBuy("1");
	  		    	}
	  		    }
	  		    
	  		    if(stdchargesetup.equals("0")){
	  		    	invoiceid=completeAptmDAO.saveStndCharge(appointment.getClientId(), String.valueOf(admissionid), stdcharges);
	      		    
	      		    invoiceid=completeAptmDAO.saveAmpmInvoice(appointment,loginInfo.getId(),loginInfo.getUserId());
	  		    }
	  		        		    
	  		    String nowDate=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
	  		    String n1 = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[1];
	  		    String datetime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
	  			String stddate= DateTimeUtils.getCommencingDate1(ipdForm.getAdmissiondate());
	  			String sdate = ipdForm.getAdmissiondate() + " " + ipdForm.getHour() + ":" + ipdForm.getMinute() + ":00";
	  			  
	              if(!cutofftime.equals("0")){
	              	sdate = ipdForm.getAdmissiondate() + " " + cutofftime + ":00";
	              }
	  			String edate = DateTimeUtils.getCommencingDate1(nowDate) + " "+ n1;
	  			long diff= DateTimeUtils.getDifferenceOfTwoDateDBFormat(stddate, nowDate);
	  			int qty =(int) diff;
	  			if(qty<0){
	  				 qty=0;
	  			}
	  			
	  			if(qty==0){
	  				qty=1;
	  			} else {
	  				qty++;
	  			}
	  			
	  		    
	  		    String fullname=client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
	  		    appointment.setUser(fullname);
	  		    appointment.setCommencing(date);     
	  		    for(Master master:chargeList){
	  		    	  
	  		    	   String chargeId=String.valueOf(master.getId());
	  		    	   appointment.setApmtType(master.getName());
	  		    	   appointment.setCharges(master.getCharge());
	  		    	   appointment.setAdditionalcharge_id(chargeId);
	  		    	   //appointment.setMasterchargetype("Bed Charge");
	  		    	   //  30-11-2019 kunal auto charges not applied
			    	   if(loginInfo.getIskunal()!=1){
			    		   appointment.setMasterchargetype("Bed Charge");
					   }else{
						   appointment.setMasterchargetype("Bed Charges");
					   }
	  		    	   //appointment.setMasterchargetype("Accommodation Charges");
	  		    	   appointment.setIpd(Integer.toString(admissionid));
	  		    	   appointment.setWardid(ipdForm.getWardid());
	  		    	   
	  		    	   qty = DateTimeUtils.getDifferanceofDateWithTime(sdate, edate, master.getChargehours());
	  		    	   if(qty==0){
	  		    		   qty++;
	  		    	   }
	  		    	   
	  		    	   appointment.setQuantity(qty);
	  		    	   appointment.setStdflag("1");
	  		    	   if(stdchargesetup.equals("0")){
	  		    		   int res=completeAptmDAO.saveInvoiceAssesment(appointment, invoiceid);
	      		    	   
	      		    	   int result= appointmentTypeDAO.saveStdCharge(String.valueOf(admissionid),chargeId,res,"1",datetime,"");
	  		    	   }
	  		    	   
	  		    }
	  		    
	  		 //   int upd = appointmentTypeDAO.setInprocessforNewShiftCharges(invoiceid,log1);
	  		    */
	  			
	  			 
				
				CompleteAppointment appointment=new CompleteAppointment();
				appointment.setClientId(bed.getClientid());
				appointment.setPractitionerId(bed.getPractitionerid());
				appointment.setChargeType("Charge");
				appointment.setLocation("1");
			    appointment.setAdditionalcharge_id("001");
			    appointment.setIpdid(admissionid);
			    appointment.setInvoiceDate(date1);
			    appointment.setIpd("1");
			    appointment.setAppointmentid("0");
			    appointment.setGinvstid("0");
			    appointment.setWardid(bed.getWardid());
			    if(client.getWhopay()!=null){
			    	
			    	if(client.getWhopay().equals("Self") || client.getWhopay().equals("Client")){
			    	       
			    		appointment.setPolicyExcess("0");
			    		appointment.setPayBuy("0");
			    	} else {
			    		appointment.setPolicyExcess("1");
			    		appointment.setPayBuy("1");
			    	}
			    }
			    
			    if(stdchargesetup.equals("0")){
			    	invoiceid=completeAptmDAO.saveStndCharge(appointment.getClientId(), String.valueOf(admissionid), stdcharges);
	    		    
	    		    invoiceid=completeAptmDAO.saveAmpmInvoice(appointment,loginInfo.getId(),loginInfo.getUserId());
			    }
			        		    
			    String nowDate=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
			    String n1 = DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[1];
			    String datetime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
				String stddate= DateTimeUtils.getCommencingDate1(ipdForm.getAdmissiondate());
				String sdate = ipdForm.getAdmissiondate() + " " + ipdForm.getHour() + ":" + ipdForm.getMinute() + ":00";
				  
	            if(!cutofftime.equals("0")){
	            	sdate = ipdForm.getAdmissiondate() + " " + cutofftime + ":00";
	            }
				String edate = DateTimeUtils.getCommencingDate1(nowDate) + " "+ n1;
				long diff= DateTimeUtils.getDifferenceOfTwoDateDBFormat(stddate, nowDate);
				int qty =(int) diff;
				if(qty<0){
					 qty=0;
				}
				
				if(qty==0){
					qty=1;
				} else {
					qty++;
				}
				
			    
			    String fullname=client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
			    appointment.setUser(fullname);
			    appointment.setCommencing(date1);   
			    String dt2=date1;
			    for(Master master:chargeList){
			    	  
			    	   String chargeId=String.valueOf(master.getId());
			    	   appointment.setApmtType(master.getName());
			    	   appointment.setCharges(master.getCharge());
			    	   appointment.setAdditionalcharge_id(chargeId);
			    	   if(loginInfo.getIskunal()!=1){
			    		   appointment.setMasterchargetype("Bed Charge");
					   }else{
						   appointment.setMasterchargetype("Bed Charges");
					   }
			    	   //appointment.setMasterchargetype("Accommodation Charges");
			    	   appointment.setIpd(Integer.toString(admissionid));
			    	   appointment.setWardid(ipdForm.getWardid());
			    	  
			    	   qty = DateTimeUtils.getDifferanceofDateWithTime(sdate, edate, master.getChargehours());
			    	   int newqty=qty;
			    	   if(qty==0){
			    		   qty++;
			    	   }
			    	  
			    	   appointment.setQuantity(qty);
			    	   appointment.setStdflag("1");
			    	   int res=0;
			    	   if(stdchargesetup.equals("0")){
//			    		   if(loginInfo.getIskunal()==1){
			    			   //appointment.setMasterchargetype(master.getMasterchargetype());
			    		   if(qty>0){
			    			   for(int i=0;i<=newqty;i++){
			    				   int w=1;
			    				    	if(i==0){
			    				    		w=0;
			    				    		appointment.setCommencing(dt2);
			    				    	}
			    				   appointment.setQuantity(1);
			    				   SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			    				   Date d=sdf1.parse(appointment.getCommencing());
			    				   Calendar cal = Calendar.getInstance();
			    				    
			    				    cal.setTime(d);
			    				    cal.add(Calendar.DATE, w);
			    				    String dt=sdf1.format(cal.getTime());
			    				    appointment.setCommencing(dt);    
			    				    res=completeAptmDAO.saveInvoiceAssesment(appointment, invoiceid); 
			    			   }
			    			   
			    			   }
			    			   
//			    		   }else{
//			    			   res=completeAptmDAO.saveInvoiceAssesment(appointment, invoiceid); 
//			    		   }
//			    		   int res=completeAptmDAO.saveInvoiceAssesment(appointment, invoiceid);
	    		    	   
	    		    	   int result= appointmentTypeDAO.saveStdCharge(String.valueOf(admissionid),chargeId,res,"1",datetime,"");
			    	   }
			    	   
			    }
			    
			 //   int upd = appointmentTypeDAO.setInprocessforNewShiftCharges(invoiceid,log1);
			    
			
	  		}
		                  
          //sending sms to practitioner
          
          
          DiagnosisDAO diagnosisDAO=new JDBCDiagnosisDAO(connection);
          
          String fullname=client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
          UserProfileDAO userProfileDAO=new JDBCUserProfileDAO(connection);
          String wardname=ipdDAO.getIpdWardName(bed.getWardid());
          String bedname=ipdDAO.getIpdBedName(bed.getBedid());
          String condition=diagnosisDAO.getDiagnosisName(bed.getConditionid()).getName();
          
          UserProfile profile=userProfileDAO.getUserprofileDetails(Integer.parseInt(bed.getPractitionerid()));
          String datetime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone());
          String time[]=datetime.split(" ");
          
          boolean smsisActive=clinicDAO.isSmsActive(loginInfo.getId());

          if(smsisActive){
          	String message="New patient "+fullname+" admitted to "+wardname+" ward bed "+bedname+", condition "+condition+" at "+time[1]+"";
          	SmsService smsService=new SmsService();
          	String templateid="";
          	smsService.sendSms(message, profile.getMobile(), loginInfo, new EmailLetterLog(),templateid);
          	//sms sent
          }
          saveConditions(admissionid,ipdForm.getTreatmentepisodeid());
		} 	
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write("Patient Added SuccessFully");
		        			
		
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null; 
 }
	public void getIpddata2(String ipdid){
		
	}
	
	
	  private void saveChargesMLC(String amount,Bed bed,Client client,int ipdid,CompleteAptmDAO completeAptmDAO,String admissiondate)throws Exception {
	    	
	    	try {
				
	    		int invoiceid=0;
	    		
	    		
	    		/*String date1 = DateTimeUtils.getDateinSimpleFormate(new Date());
	    		String stemp[] = date1.split(" ");
	    		
	    		String temp[] = stemp[0].split("-");
	    		date1 = temp[2] + "-" + temp[1] + "-" + temp[0];*/
	    		
	    		String date1 = DateTimeUtils.getCommencingDate1(admissiondate);
	    		
	    			CompleteAppointment appointment=new CompleteAppointment();
	    			appointment.setClientId(bed.getClientid());
	    			appointment.setPractitionerId(bed.getPractitionerid());
	    			appointment.setChargeType("Charge");
	    			appointment.setLocation("1");
	    		    appointment.setAdditionalcharge_id("0");
	    		    appointment.setIpdid(ipdid);
	    		    appointment.setInvoiceDate(date1);
	    		    appointment.setIpd("1");
	    		    appointment.setAppointmentid("0");
	    		    appointment.setWardid(bed.getWardid());
	    		    if(client.getWhopay()!=null){
	    		    	
	    		    	if(client.getWhopay().equals("Self") || client.getWhopay().equals("Client")){
	    		    	       
	    		    		appointment.setPolicyExcess("0");
	    		    		appointment.setPayBuy("0");
	    		    	} else {
	    		    		appointment.setPolicyExcess("1");
	    		    		appointment.setPayBuy("1");
	    		    	}
	    		    }
	    		    Connection connection= Connection_provider.getconnection();
	    		    String mlcdr=bed.getMlcrefdoctor();
	    		    UserProfileDAO userProfileDAO= new JDBCUserProfileDAO(connection);
	    		    mlcdr= userProfileDAO.getReferalDrName(bed.getMlcrefdoctor()); 
	    		    if(mlcdr==null){
	    		    	mlcdr="";
	    		    } 
	    		    if(!mlcdr.equals("")){
	    		    	 appointment.setApmtType(" MLC Charge -"+mlcdr);
	    		    }else{
	    		    	 appointment.setApmtType(" MLC Charge");
	    		    }
	    		    invoiceid=completeAptmDAO.saveAmpmInvoice(appointment,loginInfo.getId(),loginInfo.getUserId());
	    		     
	    		    String fullname=client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
	    		    appointment.setUser(fullname);
	    		    appointment.setCommencing(date1);     
	    		    	  
	    		  
	    		   appointment.setCharges(amount);
	    		   appointment.setAdditionalcharge_id("0");
	    		   appointment.setMasterchargetype("MLC Charge");
	    		   appointment.setQuantity(1);
	    		   int res=completeAptmDAO.saveInvoiceAssesment(appointment, invoiceid);
	    		
			} catch (Exception e) {

				e.printStackTrace();
			}
	  }
	    
	 public String editadmndate(){
		 try {
			Connection connection= Connection_provider.getconnection();
			String clientid= request.getParameter("clientid");
			String date =request.getParameter("date");
			String hr=request.getParameter("hr");
			String min=request.getParameter("min");
			if(hr.equals("")){
				hr="00";
			}else if(hr.equals("0")){
				hr="00";
			}
			
			if(min.equals("")){
				min="00";
			}else if(min.equals("0")){
				min="00";
			}
			IpdDAO ipdDAO= new JDBCIpdDAO(connection);
			int ipdid= ipdDAO.getLastIpdId(clientid);
			
			String maindate= DateTimeUtils.getCommencingDate1(date)+" "+hr+":"+min+":00";
			int res=ipdDAO.updateAdmissionDate(ipdid, maindate);
			
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(""+res+"");
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return null;
	 }
	  public String getonoffcharges(){
		  try {
				Connection connection= Connection_provider.getconnection();
		  String ipdid= request.getParameter("ipdid");
			String id =request.getParameter("id");
			 Accounts accounts=new Accounts();
			 AccountsDAO accountsDAO=new JDBCAccountsDAO(connection);
	  			accounts=accountsDAO.showonofftime(Integer.parseInt(id),ipdid);
	  			accounts.setOndatetime(DateTimeUtils.isNull(accounts.getOndatetime()));
	  			accounts.setOffdatetime(DateTimeUtils.isNull(accounts.getOffdatetime()));
	  			String str=accounts.getOndatetime()+"~"+accounts.getOffdatetime();
	  			int ontimeindex=0,offtimeindex=0;
	  			if(accounts.getOndatetime().contains(",")){
	  				for(String a:accounts.getOndatetime().split(",")){
	  					ontimeindex++;
	  				}
	  			}else if(!accounts.getOndatetime().equals("")){
	  				ontimeindex++;
	  			}
	  			
	  			if(accounts.getOffdatetime().contains(",")){
	  				for(String a:accounts.getOffdatetime().split(",")){
	  					offtimeindex++;
	  				}
	  			}else if(!accounts.getOffdatetime().equals("")){
	  				offtimeindex++;
	  			}
	  			int best=0;
	  			if(offtimeindex>ontimeindex){
	  				best=offtimeindex;
	  			}else{
	  				best=ontimeindex;
	  			}
	  			StringBuffer buffer=new StringBuffer();
	  			if(best>0){
	  				buffer.append("<table class='my-table lkclass' style='width:100%'>");
	  				buffer.append("<tr>");
					buffer.append("<th>On Date/Time</th>");
					buffer.append("<th>Off Date/Time</th>");
					buffer.append("</tr>");
				
				int onnlen=ontimeindex-1,offlen=offtimeindex-1;
				String on[]=accounts.getOndatetime().split(",");
				String off[]=accounts.getOffdatetime().split(",");
				for(int i=0;i<best;i++){
					buffer.append("<tr>");
					buffer.append("<td>"+on[i]+"</td>");
					if(off.length>i){
						buffer.append("<td>"+off[i]+"</td>");
					}else{
						buffer.append("<td></td>");
					}
							
					
					buffer.append("</tr>");
				}
			
	  				buffer.append("</table>");
	  			}
	  			
	  			response.setContentType("text/html");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(""+buffer.toString()+"");
		  }catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		  
	  }
	  
	  public static void main(String args[]) {
		  String s="    ";
		  System.out.println(s.length());
		  s=s.trim();
		  System.out.println(s.length());
	  }

}
