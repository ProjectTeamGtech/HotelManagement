package com.apm.common.utils;

import java.sql.Connection;
import java.util.Vector;

import com.apm.Accounts.eu.bi.AccountsDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCAccountsDAO;
import com.apm.Accounts.eu.entity.Accounts;
import com.apm.Ambulance.eu.bi.DutyDAO;
import com.apm.Ambulance.eu.blogic.jdbc.JDBCDutyDAO;
import com.apm.Ambulance.eu.entity.Duty;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.bi.OPDConsultDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCOpdConsult;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.CompleteAppointment;
import com.apm.DiaryManagement.eu.entity.OpdConsult;
import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.Emr.eu.entity.Investigation;
import com.apm.Ipd.eu.bi.BedDao;
import com.apm.Ipd.eu.blogic.jdbc.JDBCBedDao;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.common.eu.blogic.jdbc.Connection_provider;
import com.apm.common.web.common.helper.LoginInfo;

public class CommonOpdIpdReport {

	public void updatePriscriptionData(OpdConsult opdConsult2, OpdConsult opdConsult1, String docid, String regdate,
			int opdid, String opdipdtype, String dischargedate, String newadmissiondate, String dischargedate2,
			String dbname) {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			OPDConsultDAO opdConsultDAO = new JDBCOpdConsult(connection);
			int fakeipdid=opdConsult1.getIpdid();
			Vector<OpdConsult> priscripdatelist = opdConsultDAO.getPriscriptiondatelist(opdConsult2.getPatientid(),
					opdConsult2.getDate(), opdipdtype, opdConsult2.getIpdid(), dbname,fakeipdid);
			for (OpdConsult priscridate : priscripdatelist) {
				String newpriscdate = "";
				String pdatetime = priscridate.getLastmodified();
				String time = pdatetime.split(" ")[1];
				if (opdipdtype != "0") {

					String admitdate = opdConsult2.getDate().split(" ")[0];
					String priscdt = priscridate.getLastmodified().split(" ")[0];
					long dnumber = DateTimeUtils.getDiffofTwoDates(admitdate, priscdt);
					int days = (int) dnumber;

					newpriscdate = newadmissiondate.split(" ")[0];
					newpriscdate = DateTimeUtils.getCommencingDate2(newpriscdate);
					newpriscdate = newpriscdate + "," + "00:00:00" + " " + "PM";
					newpriscdate = DateTimeUtils.calnewdate(newpriscdate, days);
					newpriscdate = DateTimeUtils.getCommencingDate(newpriscdate);
					newpriscdate = newpriscdate + " " + priscridate.getLastmodified().split(" ")[1];
				}
				int updateprisc = opdConsultDAO.updateOriesParentPriscription(opdConsult2, regdate, time, pdatetime,
						opdipdtype, dischargedate, newpriscdate, priscridate, dbname);
				Vector<Priscription> parentprisclist = opdConsultDAO.getParentPriscriptionList(opdConsult2, regdate,
						opdipdtype, dischargedate, newadmissiondate, dischargedate2, priscridate, dbname);
				int parpriscid1 = 0;
				for (Priscription priscription : parentprisclist) {
					if(opdipdtype != "0") {
                        priscription.setFak_newpriscdate(newpriscdate);
					}else {
						priscription.setFak_newpriscdate(regdate);
					}
					int updtparprisc = opdConsultDAO.updateParentPrisc(priscription, opdConsult2, opdipdtype,
							dischargedate, dbname);
					int parentpriscid = opdConsultDAO.insertParentPriscription(priscription, opdid, opdConsult1.getId(),
							docid, opdConsult2, opdipdtype);
					parpriscid1 = opdConsultDAO.insertParentprisctable(opdConsult1.getId(), docid, parentpriscid,
							priscription, opdConsult2, opdipdtype, priscription.getLastmodified());
					int updateclientprisc = opdConsultDAO.updateOriesClientPriscription(priscription, opdConsult2,
							dbname);
					//Vector<Priscription> childlist = opdConsultDAO.getChildDataList(opdConsult2, priscription,
							//dbname, parpriscid1);

					Vector<Priscription> clientPriscList = opdConsultDAO.getClientPriscriptionList(priscription,
							dbname);

					//Vector<Priscription> parprisclist = opdConsultDAO.getParentPrictablelist(opdConsult2,
							//priscription, opdipdtype, dbname);
					/*
					 * for (Priscription priscription3 : parprisclist) {
					 * //parpriscid1=opdConsultDAO.insertParentprisctable(opdConsult1.getId(),docid,
					 * parentpriscid,priscription3,opdConsult2,opdipdtype,priscription.
					 * getLastmodified()); //int
					 * updateclientprisc=opdConsultDAO.updateOriesClientPriscription(priscription,
					 * opdConsult2,dbname); //Vector<Priscription>
					 * clientPriscList=opdConsultDAO.getClientPriscriptionList(priscription,dbname);
					 * 
					 * for (Priscription priscription2 : clientPriscList) {
					 * 
					 * int
					 * childid=opdConsultDAO.insertClientPriscription(parentpriscid,priscription2,
					 * opdConsult1.getId(),docid);
					 * Vector<Priscription>childlist=opdConsultDAO.getChildDataList(opdConsult2,
					 * priscription2,priscription,dbname); for (Priscription priscription4 :
					 * childlist) { int
					 * result=opdConsultDAO.insertChildPriscTable(parentpriscid,parpriscid1,childid,
					 * priscription4,opdConsult1.getId()); } }
					 * 
					 * }
					 */
					// Vector<Priscription>
					// clientPriscList=opdConsultDAO.getClientPriscriptionList(priscription,dbname);
					for (Priscription priscription2 : clientPriscList) {
						if(opdipdtype != "0") {
		                    priscription2.setLastmodified(newpriscdate);
						}else {
							priscription2.setLastmodified(regdate);
						}
						int childid = opdConsultDAO.insertClientPriscription(parentpriscid, priscription2,
								opdConsult1.getId(), docid);
						// Vector<Priscription>childlist=opdConsultDAO.getChildDataList(opdConsult2,priscription2,priscription,dbname);
						/*
						 * for (Priscription priscription4 : childlist) { int
						 * result=opdConsultDAO.insertChildPriscTable(parentpriscid,parpriscid1,childid,
						 * priscription4,opdConsult1.getId()); }
						 */
						int result = opdConsultDAO.insertChildPriscTable(parentpriscid, parpriscid1, childid,
								priscription2, opdConsult1.getId());

					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void updatePharmacydata(OpdConsult opdConsult1, OpdConsult opdConsult, String docid, String docname,
			String opdipdtype, String dischargedate, String newadmissiondate, String dischargedate2, String regdate,
			String dbname) {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			OPDConsultDAO opdConsultDAO = new JDBCOpdConsult(connection);
			// OpdConsult
			// opdmedbill=opdConsultDAO.getOriesmedicinebillDatabyId(opdConsult.getPatientid(),opdConsult.getDate());

			Vector<OpdConsult> opdmedbilllist = opdConsultDAO.getoriesdatalistDatabyid(opdConsult1.getPatientid(),
					opdConsult.getIpdadmissiondte(), opdipdtype, dischargedate, newadmissiondate, dischargedate2,
					opdConsult1, opdConsult.getPatientid(), dbname);

			for (OpdConsult opdmedbill : opdmedbilllist) {
				String newpharmdate = "";
				if (opdipdtype != "0") {
					String pharmdate = opdmedbill.getDate();
					String admitdate = opdConsult.getDate().split(" ")[0];
					long dnumber = DateTimeUtils.getDiffofTwoDates(admitdate, pharmdate);
					int days = (int) dnumber;

					newpharmdate = newadmissiondate.split(" ")[0];
					newpharmdate = DateTimeUtils.getCommencingDate2(newpharmdate);
					newpharmdate = newpharmdate + "," + "00:00:00" + " " + "PM";
					newpharmdate = DateTimeUtils.calnewdate(newpharmdate, days);
					newpharmdate = DateTimeUtils.getCommencingDate(newpharmdate);

				}

				int updatebill = opdConsultDAO.updateOriesmedicinebill(opdmedbill.getId(), opdConsult1, docid, docname,
						opdmedbill.getPatientid(), newadmissiondate, opdipdtype, regdate, newpharmdate, dbname);
				int updatecharge = opdConsultDAO.updateOriesmedicinecharges(opdmedbill.getId(), opdConsult1, docid,
						docname, newadmissiondate, opdipdtype, regdate, newpharmdate, dbname);
				/*
				 * int updatepayment =
				 * opdConsultDAO.updateOriesmedicinepayment(opdmedbill.getId(), opdConsult1,
				 * newadmissiondate, opdipdtype, regdate, newpharmdate, dbname);
				 */
				CompleteAppointment appointment = new CompleteAppointment();
				appointment = opdConsultDAO.getOriesmedicinebillbyId(opdConsult1.getId(), opdmedbill.getId(), dbname,regdate,opdConsult.getPatientid());
				
				int billno = opdConsultDAO.insertFakeMedicinebill(appointment);
				Vector<Priscription> medicinechargeslist = opdConsultDAO
						.getMedicineChargeslistbyId(opdConsult1.getId(), opdmedbill.getId(), dbname,regdate,opdConsult.getPatientid());
				for (Priscription priscription : medicinechargeslist) {
					int insertcharge = opdConsultDAO.insertFakeMedicineCharges(priscription, billno);
					int updatestock = opdConsultDAO.updateMedicineQty(priscription.getSaleqty(),
							priscription.getProduct_id(), 0);
				}
				Vector<CompleteAppointment> medicinepaymentlist = opdConsultDAO
						.getMedicinePaymentlistbyid(opdConsult1.getId(), opdmedbill.getId(), dbname,regdate,opdConsult.getPatientid());
				for (CompleteAppointment completeAppointment : medicinepaymentlist) {
					int paymentseqno = opdConsultDAO.getPharmacyPaymentSeqNo(completeAppointment.getLocation());
					paymentseqno = paymentseqno + 1;
					int insertpayment = opdConsultDAO.insertFakeMedicinePayment(completeAppointment, billno,
							paymentseqno);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void updateInvestigationData(OpdConsult opdConsult2, OpdConsult opdConsult1, String docid, String regdate,
			String opdipdtype, String dischargedate, String newadmissiondate, String dischargedate2, String dbname) {
		Connection connection = null;
		try {
			connection = Connection_provider.getconnection();
			OPDConsultDAO opdConsultDAO = new JDBCOpdConsult(connection);
			int fipdid=opdConsult1.getIpdid();
			Vector<Investigation> parentdatelist = opdConsultDAO.getOrisesParentdateList(opdConsult2, opdipdtype,
					dbname,fipdid);
			for (Investigation inv : parentdatelist) {
				String newinvdate = "";
				if (opdipdtype != "0") {
					String admitdate = opdConsult2.getDate().split(" ")[0];
					String invdate = inv.getDate().split(" ")[0];
					long dnumber = DateTimeUtils.getDiffofTwoDates(admitdate, invdate);
					int days = (int) dnumber;

					newinvdate = newadmissiondate.split(" ")[0];
					newinvdate = DateTimeUtils.getCommencingDate2(newinvdate);
					newinvdate = newinvdate + "," + "00:00:00" + " " + "PM";
					newinvdate = DateTimeUtils.calnewdate(newinvdate, days);
					newinvdate = DateTimeUtils.getCommencingDate(newinvdate);
					newinvdate = newinvdate + " " + inv.getDate().split(" ")[1];
				}
				//int updateparent = opdConsultDAO.updateOriesParentInvestigation(opdConsult2, regdate, newadmissiondate,
						//dischargedate2, opdipdtype, dischargedate, newinvdate, inv, dbname);
				Vector<Investigation> parentinvstlist = opdConsultDAO.getParentInvestigationlist(opdConsult2,
						regdate, opdipdtype, newadmissiondate, dischargedate2, inv, dbname);
				for (Investigation investigation : parentinvstlist) {
					//int updateclient = opdConsultDAO.updateOriesClientInvestigation(opdConsult2, regdate,
							//investigation.getId(), opdipdtype, investigation.getDate(), dbname);
					//for bams
					if (opdipdtype != "0") {
						investigation.setCreated_date(newinvdate);
					}else {
						investigation.setCreated_date(regdate);
					}
					
					investigation.setIpdid(opdConsult2.getNewipdid());
					investigation.setOpdid(opdConsult2.getOpdid());
					int parentid = opdConsultDAO.insertParentInvestigation(investigation, opdConsult1.getId(), docid);
					Vector<Investigation> clientinvstlist = opdConsultDAO.getClientInvestigationList(
							investigation.getId(), regdate, investigation.getDate(), dbname);
					for (Investigation investigation2 : clientinvstlist) {

						int result = opdConsultDAO.insertClientInvestigation(parentid, investigation2,
								opdConsult1.getId(), docid);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void updateOtdata(String cdocid, String cclientid, String cadmissionid, String cadmissiondate,
			String auadmissionid, String auadmitdate, String patientnm, String dbname) {

		try {
			Connection connection = Connection_provider.getconnection();
			OPDConsultDAO opdConsultDAO = new JDBCOpdConsult(connection);
			Vector<OpdConsult> otlist = opdConsultDAO.getOriesOtlist(auadmissionid, dbname);
			for (OpdConsult opdConsult : otlist) {
				String audate = auadmitdate.split(" ")[0];
				long dnumber = DateTimeUtils.getDiffofTwoDates(audate, opdConsult.getCommencing());
				int days = (int) dnumber;
				String cadmissiondate1 = cadmissiondate.split(" ")[0];
				String cadmissiontime = cadmissiondate.split(" ")[1];
				cadmissiondate1 = DateTimeUtils.getCommencingDate2(cadmissiondate1);
				cadmissiondate1 = cadmissiondate1 + "," + "00:00:00" + " " + "PM";
				cadmissiondate1 = DateTimeUtils.calnewdate(cadmissiondate1, days);
				String cotdate = DateTimeUtils.getCommencingDate(cadmissiondate1);
				// cotdate=cotdate+" "+cadmissiontime;
				int update = opdConsultDAO.updateOriesOtdata(opdConsult.getPclientid(), cdocid, cclientid, cotdate,
						opdConsult.getId(), dbname);
				Vector<OpdConsult> availablelist = opdConsultDAO.getOriesOtavailableList(cclientid,
						opdConsult.getId(), dbname,cotdate);
				for (OpdConsult opdConsult2 : availablelist) {
					String cdiaryuserid = opdConsultDAO.getOriesuserlastname(opdConsult2.getDiaryuserid(), dbname);
					int result = opdConsultDAO.insertOtAvailabledata(cclientid, opdConsult2, cadmissionid, cdiaryuserid,
							patientnm);
					System.out.println("data inserted successfully" + result);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/*
	 * public static void main(String[] args) { String cdocid="3"; String
	 * cclientid="18"; String cadmissionid="1"; String
	 * cadmissiondate="2019-07-29 23:54:20"; String auadmissionid="1010"; String
	 * cdiaryuserid="355"; String auadmitdate="2018-12-24 24:30:20";
	 * CommonOpdIpdReport commonOpdIpdReport=new CommonOpdIpdReport();
	 * commonOpdIpdReport.updateOtdata(cdocid, cclientid, cadmissionid,
	 * cadmissiondate, auadmissionid, cdiaryuserid, auadmitdate);
	 * System.out.println("data inserted successfully"); }
	 */
	
public void patientTranssection(String clientId, String date_time) {
		
		Connection connection=null;
		try {
			connection=Connection_provider.getconnection();
			AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
			Vector<Accounts>debitlist = new Vector<Accounts>();
			debitlist=accountsDAO.getdebitTransectionlist(clientId);
			
			double debit = 0;
			for (Accounts accounts : debitlist) {
				
				if(accounts.getDisctype().equals("0")) {
				      double discamount =(accounts.getDebitAmount()*accounts.getDiscount()/100);
				      debit=debit+(accounts.getDebitAmount()-discamount);
				}else {
					debit=debit+(accounts.getDebitAmount())-Double.parseDouble(accounts.getDiscamt());
				}
			}
			
			double payment=accountsDAO.getPaymentList(clientId);
			double debeet=accountsDAO.getDebitamountbyid(clientId);
			double advanceamt=accountsDAO.getAdvanceamtbyrefinvid(clientId);
			double chargesum=accountsDAO.getChargesSumAssessment(clientId);
			//double advance=accountsDAO.getAllAdvanceamount(clientId);
			double balance=debit-payment;
			if(advanceamt>balance) {
				advanceamt=advanceamt-balance;
				if(advanceamt>chargesum) {
					advanceamt=advanceamt-chargesum;
					balance=0;
				}else {
					balance=chargesum-advanceamt;
				}
				
				
				
			}else {
				balance=balance-advanceamt;
				balance=balance+chargesum;
				advanceamt=0;
			}
			double adv=payment+debit;
			//double advance1=advance-adv;
			//double prepayment=advance-payment;
			boolean clientexist=accountsDAO.checkClientexist(clientId);
			if(clientexist) {
			   int update=accountsDAO.updatePatientTransaction(balance,debit,payment,advanceamt,clientId,date_time);
				
			}else{
			
			   int result=accountsDAO.savePatientTransection(balance,debit,payment,advanceamt,clientId,date_time);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

public void updateVitals(OpdConsult opdConsult2,String dbname, LoginInfo loginInfo, String mregdate, int patientid) {
	Connection connection=null;
	try {
		connection=Connection_provider.getconnection();
		AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
		ClientDAO clientDAO=new JDBCClientDAO(connection);
		Vector<Client>vitaldatalist=new Vector<>();
		vitaldatalist=clientDAO.getBmiVitalsdata(opdConsult2,dbname,loginInfo,patientid);
		String newopdid=opdConsult2.getNewopdid();
		for (Client client : vitaldatalist) {
			if(opdConsult2.getFlag_opd().equals("0")) {
				client.setDate(opdConsult2.getRegdate());
			}else {
				long dnumber=DateTimeUtils.getDiffofTwoDates(opdConsult2.getNew_admissiondate(),client.getDate().split(" ")[0]);
				String newmregdate=DateTimeUtils.getCommencingDate2(mregdate);
				newmregdate=newmregdate+","+"00:00:00"+" "+"PM";
        		String newvitaldate=DateTimeUtils.calnewdate(newmregdate, (int)dnumber);
        		newvitaldate=DateTimeUtils.getCommencingDate(newvitaldate);
        		newvitaldate=newvitaldate+" 00:00:00";
				client.setDate(newvitaldate);
			}	
			client.setOpdid(newopdid);
			client.setClientid(""+opdConsult2.getFakeclientid());
			client.setNew_ipdid(opdConsult2.getNewipdid());
			int result=clientDAO.saveBamsVitalPatient(client);
		}
		
		
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
}

public void updateVitals1(OpdConsult opdConsult2, String dbname, LoginInfo loginInfo, int opdid) {
	
	Connection connection=null;
	try {
		connection=Connection_provider.getconnection();
		AccountsDAO accountsDAO = new JDBCAccountsDAO(connection);
		ClientDAO clientDAO=new JDBCClientDAO(connection);
		/*
		 * Vector<Client>vitaldatalist=new Vector<>();
		 * vitaldatalist=clientDAO.getBmiVitalsdata(opdConsult2,dbname); String
		 * newopdid=opdConsult2.getNewopdid(); for (Client client : vitaldatalist) {
		 * client.setOpdid(newopdid); client.setClientid(""+opdConsult2.getPatientid());
		 * client.setNew_ipdid(opdConsult2.getNewipdid()); int
		 * result=clientDAO.saveBamsVitalPatient(client); }
		 */
		
		Client client=clientDAO.getvital(opdConsult2.getOldipdid(),dbname,loginInfo,opdConsult2.getPatientid(),opdConsult2.getAppointmentid());
		if(opdConsult2.getFlag_opd().equals("0")) {
			client.setDate(opdConsult2.getRegdate());
		}else {
			client.setDate(opdConsult2.getNew_admissiondate());
		}	
		client.setNew_opdid(opdConsult2.getNewopdid());
		client.setClientid(opdConsult2.getFakeclientid());
		client.setNew_opdid(""+opdid);
		int result=clientDAO.saveBamsVital1(client);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public void insertPunchkarmaData(OpdConsult opdConsult2, String dbname, String mregdate) {
	Connection connection=null;
	try {
		connection=Connection_provider.getconnection();
		DutyDAO dutyDAO=new JDBCDutyDAO(connection);
		BedDao bedDao = new JDBCBedDao(connection);
		Vector<Duty>punchKarma=dutyDAO.getBamsPunchkarmaData(opdConsult2,dbname);
		String newpunchkarmadate="";
		for (Duty duty : punchKarma) {
			if(opdConsult2.getFlag_opd().equals("0")) {
				duty.setDatetime(opdConsult2.getRegdate());
			}else {
				long dnumber=DateTimeUtils.getDiffofTwoDates(opdConsult2.getNew_admissiondate(),duty.getDate_time().split(" ")[0]);
				String newmregdate=DateTimeUtils.getCommencingDate2(mregdate);
				newmregdate=newmregdate+","+"00:00:00"+" "+"PM";
        		newpunchkarmadate=DateTimeUtils.calnewdate(newmregdate, (int)dnumber);
        		newpunchkarmadate=DateTimeUtils.getCommencingDate(newpunchkarmadate);
        		newpunchkarmadate=newpunchkarmadate+" 00:00:00";
				duty.setDatetime(newpunchkarmadate);
			}	
			duty.setNewopdid(opdConsult2.getNewopdid());
			duty.setNewipdid(opdConsult2.getNewipdid());
			duty.setClientid(""+opdConsult2.getFakeclientid());
			int result=dutyDAO.insertBamsPunchkarmaData(duty);
			
			//save into child table punchkarma_note
			Vector<Duty>childpunchkarmadata=dutyDAO.getChildPunchkarmadata(duty.getId(),dbname);
			for (Duty duty2 : childpunchkarmadata) {
				if(opdConsult2.getFlag_opd().equals("0")) {
					duty2.setDatetime(opdConsult2.getRegdate());
				}else {
					duty2.setDatetime(newpunchkarmadate);
				}
				duty2.setParentid(""+result);
				result=dutyDAO.insertChildPunchkarmaData(duty2);
			}
		}
		
		
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
}

public void insertConsentData(OpdConsult opdConsult2, String dbname) {
	Connection connection=null;
	try {
		connection=Connection_provider.getconnection();
		ClientDAO clientDAO=new JDBCClientDAO(connection);
		Client client=clientDAO.getBamsDeclarationData(Integer.parseInt(opdConsult2.getOldipdid()),dbname);
		client.setIpdid(opdConsult2.getNewipdid());
		client.setClientId(opdConsult2.getFakeclientid());
		int result=clientDAO.insertBamsdeclarationData(client);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
}
public void insertPhysioData(OpdConsult opdConsult2, String dbname, String mregdate) {
	Connection connection=null;
	
	try {
		connection=Connection_provider.getconnection();
		BedDao bedDao=new JDBCBedDao(connection);
		Vector<Bed>physioData=bedDao.getBamsPhysioData(opdConsult2,dbname);
		
		for (Bed bed : physioData) {
			if(opdConsult2.getFlag_opd().equals("0")) {
				bed.setDate(opdConsult2.getRegdate());
			}else {
				long dnumber=DateTimeUtils.getDiffofTwoDates(opdConsult2.getNew_admissiondate(),bed.getDate());
				String newmregdate=DateTimeUtils.getCommencingDate2(mregdate);
				newmregdate=newmregdate+","+"00:00:00"+" "+"PM";
        		String newphysiodate=DateTimeUtils.calnewdate(newmregdate, (int)dnumber);
        		newphysiodate=DateTimeUtils.getCommencingDate(newphysiodate);
        		newphysiodate=newphysiodate+" 00:00:00";
        		bed.setDate(newphysiodate);
			}	
			bed.setOpdid(opdConsult2.getNewopdid());
			bed.setClientid(""+opdConsult2.getFakeclientid());
			bed.setIpdid(opdConsult2.getNewipdid());
			
			int result=bedDao.insertBamsPhysioData(bed);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	
}

public void insertConsultationformData(String dbname, OpdConsult opdConsult2, int opdid, int id) {
	Connection connection=null;
	try {
		connection=Connection_provider.getconnection();
		OPDConsultDAO opdConsultDAO = new JDBCOpdConsult(connection);
		OpdConsult consultform=opdConsultDAO.getConsultationFormDatabyapmtid(opdConsult2,dbname);
		if(opdConsult2.getFlag_opd().equals("0")) {
			consultform.setDate(opdConsult2.getRegdate());
		}
		int insert=opdConsultDAO.insertinConsult_form(consultform,opdid,id);
		if (consultform.getCondi_id()!=null) {

			for (String str : consultform.getCondi_id().split(",")) {

				if (str == null) {
					continue;
				}
				if (str.equals("0")) {

					continue;
				}
				/*
				 * int id = Integer.parseInt(str); String conditionname =
				 * bedDao.getIpdConditionName(str); Bed bed2 = new Bed(); bed2.setId(id);
				 * bed2.setConditionname(conditionname); bed2.setConditionid(str);
				 * finalConditions.add(bed2);
				 */
				int res=opdConsultDAO.insertBamsConditionids(opdid,str);
			}
		}
		
		System.out.println(""+insert);
	} catch (Exception e) {
		e.printStackTrace();
	}
	
}


}
