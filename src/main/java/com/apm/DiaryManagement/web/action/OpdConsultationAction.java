package com.apm.DiaryManagement.web.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.Appointment.eu.bi.AppointmentDAO;
import com.apm.Appointment.eu.blogic.jdbc.JDBCAppointmentDAO;
import com.apm.Appointment.eu.entity.Appointment;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.bi.DiaryManagementDAO;
import com.apm.DiaryManagement.eu.bi.FinderDAO;
import com.apm.DiaryManagement.eu.bi.IpdFakeConsultDAO;
import com.apm.DiaryManagement.eu.bi.NotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.bi.OPDConsultDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCDiaryManagentDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCFinderDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCIpdFakeConsultDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCNotAvailableSlotDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCOpdConsult;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.CompleteAppointment;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.DiaryManagement.eu.entity.IpdFakeConsult;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.DiaryManagement.eu.entity.OpdConsult;
import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.DiaryManagement.web.form.ClientForm;
import com.apm.DiaryManagement.web.form.OpdConsultForm;
import com.apm.Emr.eu.blogic.jdbc.JDBCConsultationNoteDAO;
import com.apm.Emr.eu.entity.Investigation;
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
import com.apm.main.common.constants.Constants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class OpdConsultationAction extends BaseAction implements ModelDriven<OpdConsultForm>{
	OpdConsultForm opdConsultForm= new OpdConsultForm();
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
			Vector<OpdConsult> docspecialitylist=new Vector<OpdConsult>();
			connection=Connection_provider.getconnection();
			OPDConsultDAO opdConsultDAO=new JDBCOpdConsult(connection);
			docspecialitylist=opdConsultDAO.getDoctorwithsepecialitylist();
			opdConsultForm.setDocspecialitylist(docspecialitylist);
          String date = DateTimeUtils.isNull(opdConsultForm.getCommencing()) ;
			
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
			
			opdConsultForm.setCommencing(DateTimeUtils.getCommencingDate1(date));
		} catch (Exception e) {
			e.printStackTrace();		
			}
		return SUCCESS;
	}

	
	public void opdConsult(String commencing1,String docid,String ipdpatientcount){
		Connection connection=null;
		try {
			Vector<OpdConsult> patientlist=new Vector<OpdConsult>();
			connection=Connection_provider.getconnection();
			int opdid=0;
			//String docid=request.getParameter("doctor");
			String abrivationid = "";
			OPDConsultDAO opdConsultDAO=new JDBCOpdConsult(connection);
			String dbname=opdConsultDAO.getDatabasenamebyclinicid(loginInfo.getClinicid1());
			FinderDAO finderDAO=new JDBCFinderDAO(connection);
			ClientDAO clientDAO=new JDBCClientDAO(connection);
			 JDBCMasterDAO dao=new JDBCMasterDAO(connection);
			String specialityid=opdConsultDAO.getspecialityIdbydocid(docid);
			//String commencing1=opdConsultForm.getCommencing();
			session.setAttribute("commencingdte", commencing1);
			commencing1=DateTimeUtils.getCommencingDate1(commencing1);
			//int patientcount=DateTimeUtils.convertToInteger(request.getParameter("patientno"));
			//String ipdpatientcount=(request.getParameter("patientno1"));
			boolean isot = opdConsultForm.isOtcheck();
			//dao.logj(""+isot);
			session.setAttribute("ipdpatientcount", ipdpatientcount);
			//System.out.println("Total fake Patient:"+patientcount);
			session.setAttribute("specialityid", specialityid);
			session.setAttribute("docid", docid);
			
			Vector<Client>fakelist=opdConsultDAO.getFakeData(specialityid,commencing1,ipdpatientcount,loginInfo);
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
				int res=clientDAO.savePatient(client, client.getId(),loginInfo);
				int updatestatus=finderDAO.updatePatientTransferSts1(client.getId(),previousDate,specialityid);
			}
			
			patientlist=opdConsultDAO.getFakepatientlist(loginInfo.getUserId());
			for (OpdConsult opdConsult1 : patientlist) {
			    //OpdConsult	opdConsult=opdConsultDAO.getOriesdata(docid,specialityid);
				OpdConsult opdConsult=opdConsultDAO.getOrisesAvailableslot(docid,specialityid,isot,dbname);
				String adischrdate=opdConsultDAO.getadischrgedate(opdConsult.getAtreatmentepid(),dbname);
				
				opdConsult.setAudischargedte(adischrdate);
				//Vector<OpdConsult> oriesdatalist=opdConsultDAO.getoriesdatalistbyid(opdConsult.getPatientid());
				Vector<OpdConsult> oriesdatalist=opdConsultDAO.getoriesAvailableslotlistbyid(opdConsult.getPatientid(),opdConsult.getIpdadmissiondte(),specialityid,dbname);
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
					String endtime1=DateTimeUtils.getTotalofTwoTime(notAvailableSlot.getSTime(),notAvailableSlot.getDuration());
					notAvailableSlot.setEndTime(endtime1);
					String regidate=regdate.split(" ")[0];
					if(!isot) {
						notAvailableSlot.setOtplan("0");
					}
					opdid=opdConsultDAO.saveAppointment(docid,regidate,notAvailableSlot,opdConsult1,specialityid);
					CommonOpdIpdReport commonopdipdconult1=new CommonOpdIpdReport();
					opdConsult2.setNewopdid(Integer.toString(opdid));
					opdConsult2.setOldipdid(Integer.toString(opdConsult.getIpdid()));
					opdConsult2.setFlag_opd("0");
					//commonopdipdconult1.updateVitals1(opdConsult2,dbname);
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
					 opdConsult2.setNewopdid(""+opdid);
					 opdConsult2.setPatientid(opdConsult1.getId());
					 opdConsult2.setFakeclientid(""+opdConsult1.getId());
					 opdConsult2.setRegdate(regdate);
					 commonopdipdconult.updatePriscriptionData(opdConsult2,opdConsult1,docid,regdate,opdid,opdipdtype,dischargedate,"","",dbname);
					 commonopdipdconult.updatePharmacydata(opdConsult1,opdConsult,docid,notAvailableSlot.getDiaryUser(),opdipdtype,dischargedate,"","",regdate,dbname);
					 commonopdipdconult.updateInvestigationData(opdConsult2,opdConsult1,docid,regdate,opdipdtype,dischargedate,"","",dbname);
					 commonopdipdconult.updateVitals(opdConsult2,dbname,loginInfo,commencing1,opdConsult.getPatientid());
					 commonopdipdconult.insertPunchkarmaData(opdConsult2,dbname,commencing1);
					 commonopdipdconult.insertPhysioData(opdConsult2,dbname,commencing1);
					 //opdDiagnosis(specialityid,opdConsult2.getPatientid(),opdConsult1.getId());
                     String docname=notAvailableSlot.getDiaryUser();
					 session.setAttribute("docname", docname);
					 String opdid1=Integer.toString(opdid);
					 session.setAttribute("opdid", opdid1);
				}
				int updtopd=opdConsultDAO.updatePatientOpdStatusbyId(opdConsult1.getId(),loginInfo);
				IpdFakeConsult ipdFakeConsult=new IpdFakeConsult();
				ipdFakeConsult.setRegdate(commencing1);
				ipdFakeConsult.setClientid(Integer.toString(opdConsult.getPatientid()));
				ipdFakeConsult.setAdmissiondate(opdConsult.getIpdadmissiondte());
				ipdFakeConsult.setIpdid(Integer.toString(opdConsult.getIpdid()));
				ipdFakeConsult.setPatientName(opdConsult1.getPatientname());
				ipdFakeConsult.setAutreatmentid(opdConsult.getAtreatmentepid());
				ipdFakePatient(ipdFakeConsult,opdConsult1.getId(),isot,dbname);
				
				/*
				 * session.setAttribute("specialityid", specialityid);
				 * session.setAttribute("docid", docid);
				 */
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*
		 * finally { connection.close(); }
		 */
		//return "ipdentry";
	}


	/*private void updatePriscriptionData(OpdConsult opdConsult2, OpdConsult opdConsult1, String docid, String regdate, int opdid) {
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			OPDConsultDAO opdConsultDAO=new JDBCOpdConsult(connection);
			Vector<Priscription> parentprisclist=opdConsultDAO.getParentPriscriptionList(opdConsult2,regdate);
			for (Priscription priscription : parentprisclist) {
				int updtparprisc=opdConsultDAO.updateParentPrisc(priscription,opdConsult2);
				int parentpriscid=opdConsultDAO.insertParentPriscription(priscription,opdid,opdConsult1.getId(),docid);
				Vector<Priscription>parprisclist=opdConsultDAO.getParentPrictablelist(opdConsult2,priscription);
				for (Priscription priscription3 : parprisclist) {
					int parpriscid1=opdConsultDAO.insertParentprisctable(opdConsult1.getId(),docid,parentpriscid,priscription3);
					int updateclientprisc=opdConsultDAO.updateOriesClientPriscription(priscription,opdConsult2);
					Vector<Priscription> clientPriscList=opdConsultDAO.getClientPriscriptionList(priscription);
					for (Priscription priscription2 : clientPriscList) {
						
						int childid=opdConsultDAO.insertClientPriscription(parentpriscid,priscription2,opdConsult1.getId(),docid);
						Vector<Priscription>childlist=opdConsultDAO.getChildDataList(opdConsult2,priscription2,priscription3);
						for (Priscription priscription4 : childlist) {
							int result=opdConsultDAO.insertChildPriscTable(parentpriscid,parpriscid1,childid,priscription4,opdConsult1.getId());
						}
					}
				}
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	private void updateInvestigationData(OpdConsult opdConsult2, OpdConsult opdConsult1, String docid, String regdate) {
	Connection connection=null;
	try {
		connection=Connection_provider.getconnection();
		OPDConsultDAO opdConsultDAO=new JDBCOpdConsult(connection);
		Vector<Investigation>parentinvstlist=opdConsultDAO.getParentInvestigationlist(opdConsult2,regdate);
		for (Investigation investigation : parentinvstlist) {
			int updateclient=opdConsultDAO.updateOriesClientInvestigation(opdConsult2,regdate,investigation.getId());
			int parentid=opdConsultDAO.insertParentInvestigation(investigation,opdConsult1.getId(),docid);
			Vector<Investigation>clientinvstlist=opdConsultDAO.getClientInvestigationList(investigation.getId(),regdate);
			for (Investigation investigation2 : clientinvstlist) {
				
				int result=opdConsultDAO.insertClientInvestigation(parentid,investigation2,opdConsult1.getId(),docid);
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
		
	}


	private void updatePharmacydata(OpdConsult opdConsult1, OpdConsult opdConsult, OpdConsult opddata, String docid, String docname) {
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			OPDConsultDAO opdConsultDAO=new JDBCOpdConsult(connection);
			//OpdConsult opdmedbill=opdConsultDAO.getOriesmedicinebillDatabyId(opdConsult.getPatientid(),opdConsult.getDate());
			
			Vector<OpdConsult> opdmedbilllist=opdConsultDAO.getoriesdatalistDatabyid(opdConsult.getPatientid(),opdConsult.getDate());
			
			for (OpdConsult opdmedbill : opdmedbilllist) {
				
			int updatebill=opdConsultDAO.updateOriesmedicinebill(opdmedbill.getId(),opdConsult1,opddata.getDate(),docid,docname,opdmedbill.getPatientid());
			int updatecharge=opdConsultDAO.updateOriesmedicinecharges(opdmedbill.getId(),opdConsult1,opddata.getDate(),docid,docname);
			int updatepayment=opdConsultDAO.updateOriesmedicinepayment(opdmedbill.getId(),opdConsult1,opddata.getDate());
			CompleteAppointment appointment=new CompleteAppointment();
			appointment=opdConsultDAO.getOriesmedicinebillbyId(opdConsult1.getId());
			int billno=opdConsultDAO.insertFakeMedicinebill(appointment);
			Vector<Priscription> medicinechargeslist=opdConsultDAO.getMedicineChargeslistbyId(opdConsult1.getId());
			for (Priscription priscription : medicinechargeslist) {
				int insertcharge=opdConsultDAO.insertFakeMedicineCharges(priscription,billno);
				int updatestock=opdConsultDAO.updateMedicineQty(priscription.getSaleqty(),priscription.getProduct_id(),0);
			}
			Vector<CompleteAppointment> medicinepaymentlist=opdConsultDAO.getMedicinePaymentlistbyid(opdConsult1.getId());
			for (CompleteAppointment completeAppointment : medicinepaymentlist) {
				int paymentseqno = opdConsultDAO.getPharmacyPaymentSeqNo(completeAppointment.getLocation());
				   paymentseqno = paymentseqno+1;
				int insertpayment=opdConsultDAO.insertFakeMedicinePayment(completeAppointment,billno,paymentseqno);
			}
			
		}	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

*/
	/*
	 * private void opdDiagnosis(String specialityid, int patientid, int
	 * newpatientid) { Connection connection=null; try {
	 * connection=Connection_provider.getconnection(); OPDConsultDAO
	 * opdConsultDAO=new JDBCOpdConsult(connection); OpdConsult
	 * opd=opdConsultDAO.getDiagnosisdata(specialityid); int
	 * updatetaken=opdConsultDAO.updateTaken(opd.getId()); int
	 * insert=opdConsultDAO.saveOpddiagnosis(opd,newpatientid);
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * }
	 */
public void ipdFakePatient(IpdFakeConsult auipdFake,int apmclientid,boolean isot, String dbname) throws Exception{
		
		Connection connection=null;
		
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			connection = Connection_provider.getconnection();
			connection = DriverManager.getConnection(
					"" + Constants.DB_HOST + ":3306/" + loginInfo.getClinicUserid()
							+ "?useUnicode=true&characterEncoding=UTF-8",
					"" + Constants.DB_USER + "", "" + Constants.DB_PWD + "");
	        IpdFakeConsultDAO ipdFakeConsultDAO=new JDBCIpdFakeConsultDAO(connection);
	        Vector<IpdFakeConsult> fakepatientlist=new Vector<IpdFakeConsult>();
	        TreatmentEpisode treatmentEpisode=new TreatmentEpisode();
	        String ipdpatientcount=(String)session.getAttribute("ipdpatientcount");
	        fakepatientlist=ipdFakeConsultDAO.getIpdfakelist(ipdpatientcount,loginInfo);
	        //ipConsultForm.setIpdfakepatientlist(fakepatientlist);
	        
	        //String docid=request.getParameter("doctor");
	        //String specialityid=ipdFakeConsultDAO.getspecialityid(docid);
	        String specialityid=(String) session.getAttribute("specialityid");
	        String docid=(String) session.getAttribute("docid");
	        String docname=(String) session.getAttribute("docname");
	        String opdid=(String)session.getAttribute("opdid");

	        //for (IpdFakeConsult ipdFakeConsult : fakepatientlist) {
				
	        	IpdFakeConsult avialableslot=ipdFakeConsultDAO.getMaxAvailableDate(apmclientid);
	        	
	        	if(avialableslot.getCommencing()==null || avialableslot.getCommencing().equals("")) {
	        		avialableslot.setCommencing(auipdFake.getRegdate());
	        	}
	        	
	        	//IpdFakeConsult auipdFake=ipdFakeConsultDAO.getAuriusData(docid,specialityid);
	        	IpdFakeConsult ipdFakeConsult=new IpdFakeConsult();
	        	int aipdid=Integer.parseInt(auipdFake.getIpdid());
	        	ipdFakeConsult.setId((apmclientid));
	        	//String mregdate=ipdFakeConsult.getRegdate().split(" ")[0];
	        	//mregdate=DateTimeUtils.getCommencingDate1(mregdate);
	        	String mregdate=avialableslot.getCommencing();
	        	String auadmissiontime=DateTimeUtils.isNull(auipdFake.getAdmissiondate().split(" ")[1]);
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
	        	bed.setSpeciality(specialityid);
	        	int ipdid=ipdFakeConsultDAO.addIpdFakedata(bed,newadmissiondate,treatmentid,docid);
	        	int availableslotipd=ipdFakeConsultDAO.updateAdmissionid(opdid,ipdid);
	        	Bed bed1=ipdFakeConsultDAO.getDischrgedata(bed.getTreatmentepisodeid(),dbname);
	        	String admmissiondte=auipdFake.getAdmissiondate().split(" ")[0];
	        	String dischgrdate=bed1.getDischargedate().split(" ")[0];
	        	String dis_initiate_date=DateTimeUtils.isNull(bed1.getDis_initiate_time().split(" ")[0]);
	        	
	        	long inidisnum=DateTimeUtils.getDiffofTwoDates(dischgrdate, dis_initiate_date);
	        	
	        	
	        	
	        	long dnumber=DateTimeUtils.getDiffofTwoDates(admmissiondte, dischgrdate);
	        	//String regdate=ipdFakeConsult.getRegdate().split(" ")[0];
	        	String regdate=avialableslot.getCommencing();
	        	regdate=DateTimeUtils.getCommencingDate1(regdate);
	        	regdate=DateTimeUtils.getCommencingDate2(regdate);
	        	regdate=DateTimeUtils.getCommencingDate3(regdate);
				regdate=regdate+","+"00:00:00"+" "+"PM";
				if(dnumber>60){
					dischgrdate=DateTimeUtils.calnewdate(regdate, 60);
				}else{
					dischgrdate=DateTimeUtils.calnewdate(regdate, (int)dnumber);
				}
	        	//dischgrdate=DateTimeUtils.calnewdate(regdate, (int)dnumber);
	        	regdate=DateTimeUtils.getCommencingDate(dischgrdate);
	        	String dischargedate=regdate+" "+newadmissiondate.split(" ")[1];
	        	
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
			   opdConsult1.setId(apmclientid);
			   OpdConsult opdConsult2=new OpdConsult();
			   int patientid=Integer.parseInt(auipdFake.getClientid());
			   opdConsult2.setPatientid(patientid);
			   opdConsult2.setDate(auipdFake.getAdmissiondate());
			   opdConsult2.setIpdid(ipdid);
			   OpdConsult opdConsult=new OpdConsult();
			   opdConsult.setDate(admmissiondte);
			   opdConsult.setPatientid(patientid);
			   opdConsult.setIpdadmissiondte(auipdFake.getAdmissiondate());
			   opdConsult1.setIpdid(aipdid);
			   opdConsult2.setNewipdid(""+ipdid);
			   opdConsult2.setOldipdid(""+aipdid);
			   opdConsult2.setFakeclientid(""+apmclientid);
			   opdConsult2.setNew_admissiondate(admmissiondte);
			   opdConsult2.setFlag_opd("1");
			   //opdConsult2.setIpdid(aipdid);
			   commonopdipdconult.updatePriscriptionData(opdConsult2, opdConsult1, docid, regdate, 0,opdipdtype,bed1.getDischargedate(),newadmissiondate,dischargedate,dbname);
	           commonopdipdconult.updatePharmacydata(opdConsult1, opdConsult, docid, docname,opdipdtype,bed1.getDischargedate(),newadmissiondate,dischargedate,"",dbname);
	           commonopdipdconult.updateInvestigationData(opdConsult2, opdConsult1, docid, regdate, opdipdtype, bed1.getDischargedate(),newadmissiondate,dischargedate,dbname);
	           commonopdipdconult.updateVitals(opdConsult2, dbname,loginInfo,mregdate,patientid);
	           commonopdipdconult.insertPunchkarmaData(opdConsult2,dbname,mregdate);
	           commonopdipdconult.insertConsentData(opdConsult2,dbname);
	           commonopdipdconult.insertPhysioData(opdConsult2,dbname,mregdate);
	        //String cdocid="3";
	        //String cclientid="266";
	       	//String cadmissionid="13";
	        //String cadmissiondate="2019-08-01 13:32:20";
	       	//String auadmissionid="1490";
	        //String cdiaryuserid="355";
	       	  String auadmitdate=auipdFake.getAdmissiondate();
	       	if(isot) {
	       	  commonopdipdconult.updateOtdata(docid, ""+opdConsult1.getId(), ""+ipdid, newadmissiondate, auipdFake.getIpdid(), auadmitdate,auipdFake.getPatientName(),dbname);
	       	}
			//}
			IpdFakeConsultAction ipdcon=new IpdFakeConsultAction();
			auipdFake.setId(aipdid);
			ipdcon.ipdLogData(auipdFake, newadmissiondate, apmclientid, ipdid,dbname);
			String au_treatmentid=auipdFake.getAutreatmentid();
			String auipdid=auipdFake.getIpdid();
	       
	        ipdcon.finalDiagnosis(Integer.parseInt(auipdid),ipdid,au_treatmentid,treatmentid,dbname);
	        
	        
			
	        int updtipd=ipdFakeConsultDAO.updatePatientIpdStatusbyId(loginInfo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}
	
  public String checkDiary() throws Exception{
	  
	  Connection connection =null;
	  int res=0;
	  try {
		  
		   connection = Connection_provider.getconnection();
		   AppointmentDAO appointmentDAO=new JDBCAppointmentDAO(connection);
		   String commencing=request.getParameter("commencing");
		   String diaryuserid=request.getParameter("docid");
		   commencing=DateTimeUtils.getCommencingDate1(commencing);
		   boolean flag = appointmentDAO.checksetdiary(commencing,diaryuserid);
		   
		   if(flag){
			   res=1;
		   }else {
			   res=0;
		   }
		  
		  response.setContentType("text/html");
		  response.setHeader("Cache-Control", "no-cache");
		  response.getWriter().write(""+res+"");
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	  
	  
	  
	  return null;
	  
  }

  
  public String insertDoctordiary() throws Exception{
	  
	  Connection connection=null;
	  
	  try {
		  
		  connection=Connection_provider.getconnection();
		  String commencing=request.getParameter("commencing");
		  String docid=request.getParameter("docid");
		  String actiontype=request.getParameter("actiontype");
		  DiaryManagementDAO diarydao=new JDBCDiaryManagentDAO(connection);
		  IpdFakeConsultDAO ipdFakeConsultDAO=new JDBCIpdFakeConsultDAO(connection);
		  
		  DiaryManagement diaryManagement=diarydao.getdoctorDiarydata(docid);
		  int result=diarydao.getupdatehasDiary(docid);
		  
		  
		  String docname=ipdFakeConsultDAO.getnamebyDocid(docid);
		  diaryManagement.setSelectedDiaryUser(docname);
		  diaryManagement.setDiarUserid(Integer.parseInt(docid));
		  if(loginInfo.isMbbs() || loginInfo.isBams1()) {
			  if(actiontype.equals("0")) {
			  String temp[] = commencing.split("-");
			  diaryManagement.setCommencing(DateTimeUtils.getCommencingDate1(commencing));
			  diaryManagement.setYear(temp[2]);
			  }else {
			   commencing=DateTimeUtils.getCommencingDate(commencing);
			   String temp[] = commencing.split("-");
			   diaryManagement.setCommencing((commencing));
			   diaryManagement.setYear(temp[0]);  
			  }
		  }else {
			  
			  if(actiontype.equals("0")) {
				  String temp[] = commencing.split("-");
				  diaryManagement.setCommencing(DateTimeUtils.getCommencingDate1(commencing));
				  diaryManagement.setYear(temp[2]);  
			  }else {
			     commencing=DateTimeUtils.getCommencingDate(commencing);
			     String temp[] = commencing.split("-");
			     diaryManagement.setCommencing((commencing));
			     diaryManagement.setYear(temp[0]);
			  }
		  }
			/*
			 * String temp[] = commencing.split("-");
			 * 
			 * diaryManagement.setCommencing(DateTimeUtils.getCommencingDate1(commencing));
			 * diaryManagement.setYear(temp[2]);
			 */
		  
		  int res=diarydao.saveDiaryforfakept(diaryManagement);
		   
		  
		  response.setContentType("text/html"); 
		  response.setHeader("Cache-Control","no-cache"); 
		  response.getWriter().write(""+res+"");
			 
		  
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	  
	  return null;
	  
	  
  }
  public String saveipdFakeTemp() throws Exception {
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
			String commencing = request.getParameter("commencing");
			String docid="";
			String patientcount="";
			for (IpdFakeConsult ipdFakeConsult : docspecialitylist) {
				docid=request.getParameter("doctor"+ipdFakeConsult.getId());
				patientcount=request.getParameter("ipdpatientcount"+ipdFakeConsult.getId());
				if(!patientcount.equals("")) {
					int res=ipdFakeConsultDAO.saveteporaryfakedata(userid,docid,patientcount,commencing);
				}
				
			}
			Vector<IpdFakeConsult> ipdtempdatalist=new Vector<IpdFakeConsult>();
			ipdtempdatalist=ipdFakeConsultDAO.getfakeIpdTempData(userid);
			
			for (IpdFakeConsult ipdFakeConsult : ipdtempdatalist) {
			      
				//commencing=ipdFakeConsult.getCommencing();
				docid=ipdFakeConsult.getDoctor();
				patientcount=ipdFakeConsult.getPatientcount();
                opdConsult(commencing,docid,patientcount);
				
			}
			int result=ipdFakeConsultDAO.deleteIpdTempData(userid);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "ipdentry";
	}
  
    @Override
	public OpdConsultForm getModel() {
		// TODO Auto-generated method stub
		return opdConsultForm;
	}

}
