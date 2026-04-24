package com.apm.DiaryManagement.web.action;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.bi.FinderDAO;
import com.apm.DiaryManagement.eu.bi.IpdFakeConsultDAO;
import com.apm.DiaryManagement.eu.bi.OPDConsultDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCFinderDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCIpdFakeConsultDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCOpdConsult;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.IpdFakeConsult;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.DiaryManagement.eu.entity.OpdConsult;
import com.apm.DiaryManagement.web.form.ClientForm;
import com.apm.DiaryManagement.web.form.IpdFakeConsultForm;
import com.apm.Ipd.eu.bi.IpdDAO;
import com.apm.Ipd.eu.bi.IpdLogDAO;
import com.apm.Ipd.eu.blogic.jdbc.JDBCIpdDAO;
import com.apm.Ipd.eu.blogic.jdbc.JDBCIpdLogDAO;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.TreatmentEpisode.eu.entity.TreatmentEpisode;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.utils.CommonOpdIpdReport;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;
import com.apm.common.web.common.helper.LoginHelper;
import com.apm.common.web.common.helper.LoginInfo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class IpdFakeConsultAction extends BaseAction implements Preparable, ModelDriven<IpdFakeConsultForm> {
	
	IpdFakeConsultForm ipConsultForm=new IpdFakeConsultForm();
	
	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo = LoginHelper.getLoginInfo(request);
	private Pagination pagination = new Pagination(25, 1);
	public Pagination getPagination() {
		return pagination;
	}


	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	
	
	@Override
	public String execute() throws Exception {
		

		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection=null;
		try {
			Vector<IpdFakeConsult> docspecialitylist=new Vector<IpdFakeConsult>();
			connection=Connection_provider.getconnection();
	        IpdFakeConsultDAO ipdFakeConsultDAO=new JDBCIpdFakeConsultDAO(connection);
			docspecialitylist=ipdFakeConsultDAO.getDoctorwithsepecialitylist();
			ipConsultForm.setDocspecialitylist(docspecialitylist);
			String date = DateTimeUtils.isNull(ipConsultForm.getFromDate()) ;
			
			if (date == null) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Calendar cal = Calendar.getInstance();
				date = dateFormat.format(cal.getTime());
				date = DateTimeUtils.getCommencingDate1(date);
			}else {

				if (date.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal = Calendar.getInstance();
					date = dateFormat.format(cal.getTime());
					date = DateTimeUtils.getCommencingDate1(date);
				} else {
					date = DateTimeUtils.getCommencingDate1(date);
				}
		   }
			
			ipConsultForm.setFromDate(DateTimeUtils.getCommencingDate1(date));
			
		} catch (Exception e) {
			e.printStackTrace();		
			}
		return SUCCESS;
	}
	
	public void opdConsult(String commencing1, String docid, String patientcount) throws Exception{
		Connection connection=null;
		try {
			Vector<OpdConsult> patientlist=new Vector<OpdConsult>();
			connection=Connection_provider.getconnection();
			int opdid=0;
			int flag=0;
			String abrivationid = "";
			//String docid=request.getParameter("doctor");
			OPDConsultDAO opdConsultDAO=new JDBCOpdConsult(connection);
	        IpdFakeConsultDAO ipdFakeConsultDAO=new JDBCIpdFakeConsultDAO(connection);
			String dbname=opdConsultDAO.getDatabasenamebyclinicid(loginInfo.getClinicid1());
			FinderDAO finderDAO=new JDBCFinderDAO(connection);
			ClientDAO clientDAO=new JDBCClientDAO(connection);
			String specialityid=opdConsultDAO.getspecialityIdbydocid(docid);
			boolean isfollowup=ipConsultForm.isFollowup();
			//String commencing1=ipConsultForm.getFromDate();
			commencing1=DateTimeUtils.getCommencingDate1(commencing1);
			session.setAttribute("commencingdte", commencing1);
			//int patientcount=DateTimeUtils.convertToInteger(request.getParameter("patientno"));
			//String ipdpatientcount=(request.getParameter("patientno1"));
			//session.setAttribute("ipdpatientcount", ipdpatientcount);
			System.out.println("Total fake Patient:"+patientcount);
			
			Vector<Client>fakelist=opdConsultDAO.getFakeData(specialityid,commencing1,""+patientcount,loginInfo);
			for (Client client : fakelist) {
				int previousDate = 0;
				String lastmodified = commencing1 +" "+ DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[1];
				if(!commencing1.equals(DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0])){
					previousDate =1;
				}
				client.setRegDate(lastmodified);
				client.setLastModified(lastmodified);
				client.setLastModifiedDate(lastmodified);
				client.setFromdate("");
				client.setFakestatus("1");
				
					String clinicabrivation = clientDAO.getClinicAbrivation(loginInfo.getClinicid());
					String tempd[] = commencing1.split("-");
					String y = tempd[0];
					String m = tempd[1];
					String d = tempd[2];
					String newseq="";
					int seqno = clientDAO.getSqeunceNumber(commencing1);
					seqno++;
					int r = clientDAO.InserCdateSeq(commencing1,seqno);
					if(String.valueOf(seqno).length()==1)
					{
						 newseq="00"+seqno;
					}else if(String.valueOf(seqno).length()==2){
						 newseq="0"+seqno;
					}else{
						newseq=""+seqno;
					}
					abrivationid = clinicabrivation + y + m +d + "" +newseq ;
					client.setAbrivationid(abrivationid);
					/*
					 * String abriid=client.getAbrivationid().split("(?=\\d)(?<!\\d)")[1];
					 * System.out.println(abriid);
					 */				
				int res=clientDAO.savePatient(client, client.getId(),loginInfo);
				int updatestatus=finderDAO.updatePatientTransferSts1(client.getId(),previousDate,specialityid);
			}
			int result=ipdFakeConsultDAO.deleteOpdTempData(loginInfo.getUserId());
			patientlist=opdConsultDAO.getFakepatientlist(loginInfo.getUserId());
			for (OpdConsult opdConsult1 : patientlist) {
				
			    //OpdConsult	opdConsult=opdConsultDAO.getOriesdata(docid,specialityid);
				if(loginInfo.isKalmegha()) {
					flag=1;
				}
				OpdConsult opdConsult=opdConsultDAO.getOrisesAvailableslot1(docid,specialityid,flag,dbname);
				//Vector<OpdConsult> oriesdatalist=opdConsultDAO.getoriesdatalistbyid(opdConsult.getPatientid());
				Vector<OpdConsult> oriesdatalist=opdConsultDAO.getoriesAvailableslotlistbyid1(opdConsult.getPatientid(),opdConsult.getIpdadmissiondte(),dbname,isfollowup);
				  int count=0;
			    String regdate="";
			    String regtime="";
				String previousdate="";
				String nextdate="";
				for (OpdConsult opdConsult2 : oriesdatalist) {
					
					
					 nextdate=opdConsult2.getDate().split(" ")[0];
					
			        if(count==0){
					 	regdate=opdConsult1.getRegdate().split(" ")[0];
					 	regtime=opdConsult1.getRegdate().split(" ")[1];
					 	//regdate=DateTimeUtils.getCommencingDate1(regdate);
					 	regdate=regdate+" "+regtime;
					 	
					 }
					else{
						 OpdConsult opdConsult3=oriesdatalist.get(count-1);
						 previousdate=opdConsult3.getDate().split(" ")[0];
						 long dnumber=DateTimeUtils.getDiffofTwoDates(previousdate, nextdate);
						 int days=(int) dnumber;
						 regdate=opdConsult1.getRegdate().split(" ")[0];
						// regdate=DateTimeUtils.getCommencingDate1(regdate);
						 regdate=DateTimeUtils.getCommencingDate2(regdate);
						 regdate=regdate+","+"00:00:00"+" "+"PM";
					     regdate=DateTimeUtils.calnewdate(regdate, days);
					     regdate=DateTimeUtils.getCommencingDate(regdate);
					     regdate=regdate+" "+opdConsult1.getRegdate().split(" ")[1];
					    
						 
					}
					
					Date date = new Date();
					String commencing= new SimpleDateFormat("yyyy-MM-dd").format(date);
					
					NotAvailableSlot notAvailableSlot=opdConsultDAO.getNewOpdDiaryUserData(docid,commencing1);
					notAvailableSlot.setAddedBy(loginInfo.getUserId());
					Date date1 = new Date();
					DateFormat format = new SimpleDateFormat("HH:mm");
					//579
					
					
					String currentTime=format.format(date1);
//					String currentTime="17:30";
					String mm=currentTime.split(":")[1];
					String strmm="";
					int rem=Integer.parseInt(mm)%5;
					if(rem==0){
						notAvailableSlot.setSTime(currentTime);
					}else{
						int mmm=Integer.parseInt(mm);
						while(mmm%5!=0){
							mmm--;
						}
						strmm=""+mmm;
						if(mmm<=9){
							String singlemm=String.valueOf(mmm);
							singlemm="0"+singlemm;
							strmm=singlemm;
						}
						String updatetime=currentTime.split(":")[0]+":"+strmm;
						notAvailableSlot.setSTime(updatetime);
					}
					opdConsult2.setFlag_opd("0");
					CommonOpdIpdReport commonopdipdconult1=new CommonOpdIpdReport();
					String endtime1=DateTimeUtils.getTotalofTwoTime(notAvailableSlot.getSTime(),notAvailableSlot.getDuration());
					notAvailableSlot.setEndTime(endtime1);
					String regidate=regdate.split(" ")[0];
					notAvailableSlot.setOtplan("0");
					opdid=opdConsultDAO.saveAppointment(docid,regidate,notAvailableSlot,opdConsult1,specialityid);
					opdConsult2.setFakeclientid(""+opdConsult1.getId());
					opdConsult2.setRegdate(regdate);
					opdConsult2.setOpdid(""+opdid);
				    opdConsult2.setNewopdid(""+opdid);
					commonopdipdconult1.updateVitals1(opdConsult2,dbname,loginInfo,opdid);
					//int result=opdConsultDAO.updateOriesdata(opdConsult2,opdConsult1,opdid,docid,regdate);
					int result1=opdConsultDAO.updateOriesAvailableslotdata(opdConsult2,opdConsult1,opdid,docid,regdate,notAvailableSlot.getDiaryUser(),dbname);
					
					/*Vector<OpdConsult>priscripdatelist=opdConsultDAO.getPriscriptiondatelist(opdConsult2.getPatientid());
					for (OpdConsult priscridate : priscripdatelist) {
						String pdatetime=priscridate.getLastmodified();
						String time=pdatetime.split(" ")[1];
						int updateprisc=opdConsultDAO.updateOriesParentPriscription(opdConsult2,regdate,time,pdatetime);
					}
					*/
					
					count++;
					OpdConsult opddata=opdConsultDAO.getConsultationdata(opdid,dbname);
					int res=opdConsultDAO.insertFakeConsultationNote(opddata);
					 CommonOpdIpdReport commonopdipdconult=new CommonOpdIpdReport();
					 String opdipdtype="0";
					 String dischargedate="0";
					 commonopdipdconult.updatePriscriptionData(opdConsult2,opdConsult1,docid,regdate,opdid,opdipdtype,dischargedate,"","",dbname);
					 commonopdipdconult.updatePharmacydata(opdConsult1,opdConsult,docid,notAvailableSlot.getDiaryUser(),opdipdtype,dischargedate,"","",regdate,dbname);
					 commonopdipdconult.updateInvestigationData(opdConsult2,opdConsult1,docid,regdate,opdipdtype,dischargedate,"","",dbname);
					 opdDiagnosis(specialityid,opdConsult2.getPatientid(),opdConsult1.getId(),dbname);
					 commonopdipdconult.updateVitals(opdConsult2,dbname,loginInfo,commencing1,opdConsult.getPatientid());
					 commonopdipdconult.insertPunchkarmaData(opdConsult2,dbname,commencing1);
					 commonopdipdconult.insertPhysioData(opdConsult2,dbname,commencing1);
					 commonopdipdconult.insertConsultationformData(dbname,opdConsult2,opdid,opdConsult1.getId());
					 String docname=notAvailableSlot.getDiaryUser();
					 session.setAttribute("docname", docname);
				}
				int updtopd=opdConsultDAO.updatePatientOpdStatusbyId(opdConsult1.getId(),loginInfo);
				
				session.setAttribute("specialityid", specialityid);
				session.setAttribute("docid", docid);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			connection.close();
		}
		
	}
	private void opdDiagnosis(String specialityid, int patientid, int newpatientid, String dbname) {
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			OPDConsultDAO opdConsultDAO=new JDBCOpdConsult(connection);
			OpdConsult opd=opdConsultDAO.getDiagnosisdata(specialityid,dbname);
			int updatetaken=opdConsultDAO.updateTaken(opd.getId(),dbname);
			boolean checkexist=opdConsultDAO.checkPatientexist(newpatientid,specialityid);
			if(checkexist==false) {
			int insert=opdConsultDAO.saveOpddiagnosis(opd,newpatientid,specialityid);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}


	public String ipdFakePatient() throws Exception{
		
		Connection connection=null;
		
		try {
			connection=Connection_provider.getconnection();
	        IpdFakeConsultDAO ipdFakeConsultDAO=new JDBCIpdFakeConsultDAO(connection);
	        OPDConsultDAO opdConsultDAO=new JDBCOpdConsult(connection);
	        FinderDAO finderDAO=new JDBCFinderDAO(connection);
	        Vector<IpdFakeConsult> fakepatientlist=new Vector<IpdFakeConsult>();
	        ClientDAO clientDAO=new JDBCClientDAO(connection);
	        TreatmentEpisode treatmentEpisode=new TreatmentEpisode();
	        String ipdpatientcount=(request.getParameter("patientno1"));
	        //String ipdpatientcount=(String)session.getAttribute("ipdpatientcount");
			/*
			 * fakepatientlist=ipdFakeConsultDAO.getIpdfakelist(ipdpatientcount);
			 * ipConsultForm.setIpdfakepatientlist(fakepatientlist);
			 */
	        JDBCMasterDAO dao=new JDBCMasterDAO(connection);
	        String commencing1=ipConsultForm.getFromDate();
	        session.setAttribute("commencingdte", commencing1);
	        boolean isot = ipConsultForm.isOtcheck();
	        //dao.logj(""+isot);
	        
	        String docid=request.getParameter("doctor");
	        String dbname=opdConsultDAO.getDatabasenamebyclinicid(loginInfo.getClinicid1());
	        String specialityid=ipdFakeConsultDAO.getspecialityid(docid);
	        String docname=ipdFakeConsultDAO.getnamebyDocid(docid);
	        
	        Vector<Client>fakelist=opdConsultDAO.getFakeData(specialityid,commencing1,""+ipdpatientcount,loginInfo);
			for (Client client : fakelist) {
				int previousDate = 0;
				String lastmodified = commencing1 +" "+ DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[1];
				if(!commencing1.equals(DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0])){
					previousDate =1;
				}
				client.setRegDate(lastmodified);
				client.setLastModified(lastmodified);
				client.setLastModifiedDate(lastmodified);
				client.setFromdate("");
				client.setFakestatus("1");
				int res=clientDAO.savePatient(client, client.getId(),loginInfo);
				int updatestatus=finderDAO.updatePatientTransferSts1(client.getId(),previousDate,specialityid);
			}
	        
			fakepatientlist=ipdFakeConsultDAO.getIpdfakelist(ipdpatientcount,loginInfo);
	        ipConsultForm.setIpdfakepatientlist(fakepatientlist);
	        
			/*
			 * String specialityid=(String) session.getAttribute("specialityid"); String
			 * docid=(String) session.getAttribute("docid"); String docname=(String)
			 * session.getAttribute("docname");
			 */
	        
	        for (IpdFakeConsult ipdFakeConsult : fakepatientlist) {
				
	        	//IpdFakeConsult avialableslot=ipdFakeConsultDAO.getMaxAvailableDate(ipdFakeConsult.getId());
	        	
	        	//IpdFakeConsult auipdFake=ipdFakeConsultDAO.getAuriusData(docid,specialityid);
	        	
	        	IpdFakeConsult auipdFake=new IpdFakeConsult();
	        	
	        	OpdConsult opdCons=opdConsultDAO.getOrisesAvailableslot(docid,specialityid,isot,dbname);
	        	
	        	auipdFake.setId(opdCons.getIpdid());
	        	auipdFake.setClientid(Integer.toString(opdCons.getPatientid()));
	        	auipdFake.setAdmissiondate(opdCons.getIpdadmissiondte());
	        	
	        	
	        	//IpdFakeConsult auipdFake=opdConsultDAO.getOrisesAvailableslot(docid,specialityid,isot);
	        	int aipdid=auipdFake.getId();
	        	String mregdate=ipdFakeConsult.getRegdate().split(" ")[0];
	        	mregdate=DateTimeUtils.getCommencingDate1(mregdate);
	        	//String mregdate=avialableslot.getCommencing();
	        	String auadmissiontime=auipdFake.getAdmissiondate().split(" ")[1];
	        	String newadmissiondate=mregdate+" "+auadmissiontime;
	        	int result=ipdFakeConsultDAO.updateIpdData(newadmissiondate,ipdFakeConsult,aipdid,auipdFake,dbname);
	        	Bed bed=ipdFakeConsultDAO.geteditIpdData(aipdid,dbname,ipdFakeConsult.getId());
	        	String dateTime=DateTimeUtils.getUKCurrentDataTime(loginInfo.getTimeZone()).split(" ")[0];
	        	DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); 
	        	Date date=Calendar.getInstance().getTime();
	        	String referalDate=df.format(date);
	        	treatmentEpisode.setClientId(Integer.parseInt(bed.getClientid()));
	        	treatmentEpisode.setPayby("Third Party");
	        	treatmentEpisode.setConsultationLimit("3");
	        	treatmentEpisode.setSessions("1");
	        	treatmentEpisode.setDiaryUser(docid);
	        	treatmentEpisode.setReferalDate(referalDate);
	        	int treatmentid=ipdFakeConsultDAO.saveTreatmentEpisode(treatmentEpisode);
	        	/*bed.setTreatmentepisodeid(Integer.toString(treatmentid));*/
	        	int ipdid=ipdFakeConsultDAO.addIpdFakedata(bed,newadmissiondate,treatmentid,docid);
	        	
	        	Bed bed1=ipdFakeConsultDAO.getDischrgedata(bed.getTreatmentepisodeid(),dbname);
	        	String admmissiondte=auipdFake.getAdmissiondate().split(" ")[0];
	        	String dischgrdate=bed1.getDischargedate().split(" ")[0];
	        	
                String dis_initiate_date=bed1.getDis_initiate_time().split(" ")[0];
	        	long inidisnum=DateTimeUtils.getDiffofTwoDates(dischgrdate, dis_initiate_date);
	        	
	        	
	        	
	        	
	        	long dnumber=DateTimeUtils.getDiffofTwoDates(admmissiondte, dischgrdate);
	        	String regdate=ipdFakeConsult.getRegdate().split(" ")[0];
	        	//String regdate=avialableslot.getCommencing();
	        	regdate=DateTimeUtils.getCommencingDate1(regdate);
	        	regdate=DateTimeUtils.getCommencingDate2(regdate);
	        	//regdate=DateTimeUtils.getCommencingDate3(regdate);
				regdate=regdate+","+"00:00:00"+" "+"PM";
				if(dnumber>60){
					dischgrdate=DateTimeUtils.calnewdate(regdate, 60);
				}else{
					dischgrdate=DateTimeUtils.calnewdate(regdate, (int)dnumber);
				}
	        	//dischgrdate=DateTimeUtils.calnewdate(regdate, (int)dnumber);
	        	regdate=DateTimeUtils.getCommencingDate(dischgrdate);
			    String dischargedate=regdate+" "+ipdFakeConsult.getRegdate().split(" ")[1];
			    
			  //for calculating initiate date
	        	if(inidisnum>0) {
	        		String idischgrdate=dischgrdate+","+"00:00:00"+" "+"PM";
	        		String newinitiatedte=DateTimeUtils.calnewdate(idischgrdate, -(int)inidisnum);
	        		dis_initiate_date=DateTimeUtils.getCommencingDate(newinitiatedte);
	        		dis_initiate_date=dis_initiate_date+" "+bed1.getDis_initiate_time().split(" ")[1];
	        		bed1.setDis_initiate_time(dis_initiate_date);
	        		
	        	}else {
	        		bed1.setDis_initiate_time(dischargedate);
	        	}
			    
			   int upd = ipdFakeConsultDAO.updateTreatmentEpisodeDischargeForm(dischargedate,bed1,treatmentid);
			   
			   CommonOpdIpdReport commonopdipdconult=new CommonOpdIpdReport();
			   String opdipdtype="1";
			   OpdConsult opdConsult1=new OpdConsult();
			   opdConsult1.setId(ipdFakeConsult.getId());
			   OpdConsult opdConsult2=new OpdConsult();
			   int patientid=Integer.parseInt(auipdFake.getClientid());
			   opdConsult2.setPatientid(patientid);
			   opdConsult2.setDate(auipdFake.getAdmissiondate());
			   opdConsult2.setIpdid(ipdid);
			   OpdConsult opdConsult=new OpdConsult();
			   opdConsult.setDate(admmissiondte);
			   opdConsult.setPatientid(patientid);
			   opdConsult.setIpdadmissiondte(auipdFake.getAdmissiondate());
			   opdConsult2.setIpdid(aipdid);
			   commonopdipdconult.updatePriscriptionData(opdConsult2, opdConsult1, docid, regdate, 0,opdipdtype,bed1.getDischargedate(),newadmissiondate,dischargedate,dbname);
	           commonopdipdconult.updatePharmacydata(opdConsult1, opdConsult, docid, docname,opdipdtype,bed1.getDischargedate(),newadmissiondate,dischargedate,"",dbname);
	           commonopdipdconult.updateInvestigationData(opdConsult2, opdConsult1, docid, regdate, opdipdtype, bed1.getDischargedate(),newadmissiondate,dischargedate,dbname);
	           String auadmitdate=auipdFake.getAdmissiondate();
	           opdConsult1.setPatientname(ipdFakeConsult.getPatientName());
	           if(isot) {
			       commonopdipdconult.updateOtdata(docid, ""+opdConsult1.getId(), ""+ipdid, newadmissiondate, ""+auipdFake.getId(), auadmitdate,opdConsult1.getPatientname(),dbname);
                }
		       //commonopdipdconult.updateOtdata(docid, ""+opdConsult1.getId(), ""+ipdid, newadmissiondate, ""+auipdFake.getId(), auadmitdate);
               int fake_patientid=ipdFakeConsult.getId();
	           ipdLogData(auipdFake,newadmissiondate,fake_patientid,ipdid,dbname);
	           String au_treatmentid=opdCons.getAtreatmentepid();
	           finalDiagnosis(auipdFake.getId(),ipdid,au_treatmentid,treatmentid,dbname);
			}
			
			
	        int updtipd=ipdFakeConsultDAO.updatePatientIpdStatusbyId(loginInfo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
		return "entry";
	}

	




	public void finalDiagnosis(int au_ipdid, int ipdid, String au_treatmentid, int treatmentid, String dbname) {
		Connection connection=null;
		
		try {
			connection=Connection_provider.getconnection();
			IpdDAO ipdDAO=new JDBCIpdDAO(connection);
			IpdFakeConsultDAO ipdFakeConsultDAO=new JDBCIpdFakeConsultDAO(connection);
			Bed bed =ipdDAO.getFinalDiagnosis(au_ipdid,au_treatmentid,dbname);
			int result=ipdFakeConsultDAO.saveFinalDiagnosis(ipdid,bed,treatmentid);
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}


	public void ipdLogData(IpdFakeConsult auipdFake,String chrai_newadmissidte,int fake_patientid,int chrai_ipdid, String dbname) {
		
		Connection connection=null;
		
		try {
			connection=Connection_provider.getconnection();
			IpdLogDAO ipdLogDAO=new JDBCIpdLogDAO(connection);
    		Vector<Bed> fakebedLogList=ipdLogDAO.getfakepatientBedLogList(auipdFake.getClientid(),""+auipdFake.getId(),dbname);
            chrai_newadmissidte=chrai_newadmissidte.split(" ")[0];
            chrai_newadmissidte=DateTimeUtils.getCommencingDate1(chrai_newadmissidte);
            chrai_newadmissidte=DateTimeUtils.getCommencingDate2(chrai_newadmissidte);
            chrai_newadmissidte=DateTimeUtils.getCommencingDate3(chrai_newadmissidte);
            chrai_newadmissidte=chrai_newadmissidte+","+"00:00:00"+" "+"PM";
            
			for (Bed bed : fakebedLogList) {
				
				String aur_admissiondte=auipdFake.getAdmissiondate().split(" ")[0];
				String commencing=bed.getCommencing();
				long dnumber=DateTimeUtils.getDiffofTwoDates(aur_admissiondte, commencing);
				String newcommencing=DateTimeUtils.calnewdate(chrai_newadmissidte, (int)dnumber);
				newcommencing=DateTimeUtils.getCommencingDate(newcommencing);
				
				String new_lastmodified=newcommencing+" "+bed.getLastmodified().split(" ")[1];
				String new_selectedshiftdata=newcommencing+" "+bed.getLastmodified().split(" ")[1];
				
				int result=ipdLogDAO.updateBedlogdata(fake_patientid,newcommencing,new_lastmodified,new_selectedshiftdata,bed.getId(),auipdFake.getId(),chrai_ipdid,dbname);
				
				Bed bed1=ipdLogDAO.getbedlodDataByid(bed.getId(),dbname);
				
				result=ipdLogDAO.insertfakepatntBedlog(bed1);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}


	@Override
	public IpdFakeConsultForm getModel() {
		// TODO Auto-generated method stub
		return ipConsultForm;
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public String saveopdfaketemp() throws Exception {
		if(!verifyLogin(request)){
			return "login";
		}
		Connection connection=null;
		try {
			Vector<IpdFakeConsult> docspecialitylist=new Vector<IpdFakeConsult>();
			connection=Connection_provider.getconnection();
	        IpdFakeConsultDAO ipdFakeConsultDAO=new JDBCIpdFakeConsultDAO(connection);
			docspecialitylist=ipdFakeConsultDAO.getDoctorwithsepecialitylist();
			String userid=loginInfo.getUserId();
			String commencing = request.getParameter("FromDate");
			String docid="";
			String patientcount="";
			for (IpdFakeConsult ipdFakeConsult : docspecialitylist) {
				docid=request.getParameter("doctor"+ipdFakeConsult.getId());
			    patientcount=request.getParameter("patientcount"+ipdFakeConsult.getId());
				
				if(!patientcount.equals("")) {
					int save=ipdFakeConsultDAO.saveOpdfaketempdata(docid,patientcount,userid,commencing);
				}
			}
			Vector<OpdConsult>opdtempdatalist=new Vector<OpdConsult>();
			opdtempdatalist=ipdFakeConsultDAO.getfakeopdtempdata(userid);
			for (OpdConsult opdConsult : opdtempdatalist) {
				commencing=opdConsult.getCommencing();
				docid=opdConsult.getDoctor();
				patientcount=opdConsult.getPatientcount();
                opdConsult(commencing,docid,patientcount);
			}
			//int result=ipdFakeConsultDAO.deleteOpdTempData(userid);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			connection.close();
		}
		return "ipdentry";
	}
}
