package com.apm.DiaryManagement.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.Calendar;
import java.util.Vector;

import com.a.a.a.a.a.c;
import com.a.a.a.g.m.o;
import com.a.a.a.g.m.p;
import com.apm.Appointment.eu.entity.Appointment;
import com.apm.DiaryManagement.eu.bi.OPDConsultDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.CompleteAppointment;
import com.apm.DiaryManagement.eu.entity.IpdFakeConsult;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.DiaryManagement.eu.entity.OpdConsult;
import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.Emr.eu.bi.InvestigationDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCInvestigationDAO;
import com.apm.Emr.eu.entity.Investigation;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.common.eu.blogic.jdbc.JDBCBaseDAO;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.web.common.helper.LoginInfo;

public class JDBCOpdConsult extends JDBCBaseDAO implements OPDConsultDAO{

	public JDBCOpdConsult(Connection connection) {
		this.connection=connection;
	}

	@Override
	public Vector<OpdConsult> getDoctorwithsepecialitylist() {
		PreparedStatement pst=null;
		Vector<OpdConsult>list=new Vector<OpdConsult>();
		try {
			String sql="select id,initial,firstname,lastname,discription from apm_user where usertype=4 and islogin=1  and (jobtitle='Practitioner' or hasdiary=1) order by firstname asc";
			pst=connection.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				OpdConsult opdConsult=new OpdConsult();
				String speciality=getSpecialityName(rs.getString(5));
				if(rs.getString(2)==null || rs.getString(2).equals(""))
				{
					opdConsult.setDoctor(rs.getString(3) + " " +rs.getString(4)+"  ("+(speciality)+")");

				}
				else{
					opdConsult.setDoctor(rs.getString(2) + " " + rs.getString(3) + " " +rs.getString(4)+"  ("+(speciality)+")");

				}
				opdConsult.setId(rs.getInt(1));
				list.add(opdConsult);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private String getSpecialityName(String id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT discipline FROM apm_discipline where  id = "+id+"";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Vector<OpdConsult> getFakepatientlist(String userid) {
		PreparedStatement pst=null;
		Vector<OpdConsult>list=new Vector<OpdConsult>();
		StringBuffer sql=new StringBuffer();
		try {
			String cdate = DateTimeUtils.getUKCurrentDataTime("India").split(" ")[0];
			sql.append("select id,concat(title,' ',firstname,' ',surname),regdate from apm_patient ");
			sql.append("where fake_status=1 and opd=0 and f_userid='"+userid+"' and bdate like '%"+cdate+"%'");
			pst=connection.prepareStatement(sql.toString());
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				OpdConsult opdConsult=new OpdConsult();
				opdConsult.setId(rs.getInt(1));
				opdConsult.setPatientname(rs.getString(2));
				opdConsult.setRegdate(rs.getString(3));
				list.add(opdConsult);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public String getspecialityIdbydocid(String docid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT discription from apm_user where  id = "+docid+"";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public OpdConsult getOriesdata(String docid, String specialityid) {
		PreparedStatement preparedStatement=null;
		OpdConsult opdConsult=new OpdConsult();
		try {
			String sql="select apm_consultation_note.id,apm_consultation_note.patientid,appointmentid,lastmodified from orises.apm_consultation_note inner join orises.apm_user on apm_user.id=apm_consultation_note.practitionerid where discription='"+specialityid+"' and apm_consultation_note.taken!=1 limit 0,1";
			preparedStatement=connection.prepareStatement(sql);
			ResultSet rs=preparedStatement.executeQuery();
			while(rs.next()){
				opdConsult.setId(rs.getInt(1));
				opdConsult.setPatientid(rs.getInt(2));
				opdConsult.setAppointmentid(rs.getInt(3));
				opdConsult.setDate(rs.getString(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return opdConsult;
	}

	@Override
	public Vector<OpdConsult> getoriesdatalistbyid(int patientid) {
		// TODO Auto-generated method stub
		PreparedStatement pst=null;
		Vector<OpdConsult>list=new Vector<OpdConsult>();
		try {
			String sql="select apm_consultation_note.id,patientid,appointmentid,lastmodified  from orises.apm_consultation_note inner join orises.apm_user on apm_user.id=apm_consultation_note.practitionerid where patientid='"+patientid+"'";
			pst=connection.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				OpdConsult opdConsult=new OpdConsult();
				opdConsult.setId(rs.getInt(1));
				opdConsult.setPatientid(rs.getInt(2));
				opdConsult.setAppointmentid(rs.getInt(3));
				opdConsult.setDate(rs.getString(4));
				list.add(opdConsult);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public NotAvailableSlot getNewOpdDiaryUserData(String docid, String commencing) {
		PreparedStatement preparedStatement=null;
				NotAvailableSlot n=new NotAvailableSlot();
		try {
			String sql = "SELECT id,starttime,endtime,apmtduration,location,diaryuser FROM apm_apmt_slot where commencing = '"+commencing+"' and diaryuserid = "+docid+" ";
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				n.setId(rs.getInt(1));
				n.setSTime(rs.getString(2));
				n.setEndTime(rs.getString(3));
				n.setDuration(rs.getString(4));
				n.setLocation(rs.getString(5));
				n.setDiaryUser("Dr. "+rs.getString(6));
			
				
				String slotetime = getSlotEndTime(docid,commencing);
				if(slotetime.equals("")){
					slotetime = n.getSTime();
					
				}
				n.setSlotstime(slotetime);
				
			}
		 } catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	

}

	private String getSlotEndTime(String docid, String commencing) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select endtime from  apm_available_slot  where commencing = '"+commencing+"' and diaryuserid = "+docid+" "
				+ " order by id desc limit 0,1";
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	@Override
	public int saveAppointment(String docid, String commencing, NotAvailableSlot notAvailableSlot, OpdConsult opdConsult,String specialityid) {
		int result=0;
		int id = 0;
		PreparedStatement pstm=null;
		try {
			String sql = "insert into apm_available_slot(apmslotid,commencing,starttime,endtime,"
					+ "notes,diaryuserid,diaryusername,dept,location,room,clientname,aptmtype,"
					+ "duration,clientId,charge,treatmentEpisodeId,added_by,apmttypetext,usedsession,"
					+ "condition_id,whopay,otid,category,procedures,surgeon,anesthesia,ipdno,wardid,"
					+ "anidoctorfees,psurcharge,panetcharge,sic,assistaffcharge,opdbooktime,reqdatetime,"
					+ "mobstatus,speciality,appt_type,opdabbrevationid,refferedfrom,isPreDate,opdSequnce,deptOpdId) "
					+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
					+ "?,?,?,?,?,?,?,?,?,?,?,?)";
			pstm = connection.prepareStatement(sql);
			pstm.setInt(1, notAvailableSlot.getId());
			pstm.setString(2,commencing);
			pstm.setString(3, notAvailableSlot.getSTime());
			pstm.setString(4, notAvailableSlot.getEndTime());
			pstm.setString(5, notAvailableSlot.getNotes());
			pstm.setString(6, docid);
			pstm.setString(7, notAvailableSlot.getDiaryUser());
			pstm.setString(8, specialityid);
			pstm.setString(9, "1");
			pstm.setString(10, "Room1");
			pstm.setString(11, opdConsult.getPatientname());
			pstm.setString(12, "1");
			pstm.setString(13, notAvailableSlot.getDuration());
			pstm.setInt(14, opdConsult.getId());
			pstm.setInt(15, 0);
			
			
			pstm.setString(16, notAvailableSlot.getTreatmentEpisodeId());
			pstm.setString(17, notAvailableSlot.getAddedBy());
			String apmtTYpeText = getAppointmentTypeText(notAvailableSlot.getApmtType());
			pstm.setString(18, apmtTYpeText);
			pstm.setString(19, notAvailableSlot.getUsedsession());
			pstm.setString(20, notAvailableSlot.getCondition());
			pstm.setString(21, "Client");
			pstm.setInt(22, notAvailableSlot.getOtid());
			pstm.setString(23, notAvailableSlot.getOtplan());
			pstm.setString(24, DateTimeUtils.numberCheck(notAvailableSlot.getProcedure()));
			pstm.setString(25, DateTimeUtils.numberCheck(notAvailableSlot.getSurgeon()));
			pstm.setString(26, DateTimeUtils.numberCheck(notAvailableSlot.getAnesthesia()));
			pstm.setString(27, DateTimeUtils.numberCheck(notAvailableSlot.getIpdno()));
			pstm.setString(28, DateTimeUtils.numberCheck(notAvailableSlot.getWardid()));
			
			pstm.setString(29, DateTimeUtils.numberCheck(notAvailableSlot.getAnifees()));
			pstm.setString(30, DateTimeUtils.numberCheck(notAvailableSlot.getPsurcharge()));
			pstm.setString(31, DateTimeUtils.numberCheck(notAvailableSlot.getPanetcharge()));
			pstm.setString(32, DateTimeUtils.numberCheck(notAvailableSlot.getSic()));
			pstm.setString(33, DateTimeUtils.numberCheck(notAvailableSlot.getAssistaffcharge()));
			pstm.setString(34, notAvailableSlot.getOpdbooktime());
			
			String dateTime ="";
			if(notAvailableSlot.getPreDate()==1){
				DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
				Calendar cal = Calendar.getInstance();
				String time = dateFormat.format(cal.getTime());
				dateTime = notAvailableSlot.getCommencing() +" "+time;
			}else{
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();
				dateTime = dateFormat.format(cal.getTime());
			}
			
			pstm.setString(35, dateTime);
			pstm.setString(36, notAvailableSlot.getMobstatus());
			pstm.setString(37, notAvailableSlot.getSpeciality());
			pstm.setInt(38, notAvailableSlot.getAppt_type());
			String newopdabbr="";
			String opdSeqAbbr="";
			boolean flag = true;
			if(notAvailableSlot.getOtplan().equals("0") && !DateTimeUtils.isNull(apmtTYpeText).equals("")){
				newopdabbr=generateOPDSequenceNewFormat();
				opdSeqAbbr = generateLMHOPDSeq(commencing);
				flag = false;
			}
			if(notAvailableSlot.getOtplan().equals("0") && flag){
				opdSeqAbbr = generateLMHOPDSeq(commencing);
			}
			pstm.setString(39, newopdabbr);
			pstm.setString(40, DateTimeUtils.isNull(notAvailableSlot.getRefferedfrom()));
			pstm.setString(41, ""+notAvailableSlot.getPreDate());
			pstm.setString(42, opdSeqAbbr);
			pstm.setString(43, ""+notAvailableSlot.getDeptOpdId());
			result = pstm.executeUpdate();
			
			if(result == 1){
				ResultSet resultSet = pstm.getGeneratedKeys();
				if(resultSet.next()){
					id = resultSet.getInt(1);  
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	public String generateLMHOPDSeq(String commencing) {
		String abrivationid = "";
		try {

			int seqno = checkifSequenceExistLMH(commencing);
			String clinicabrivation = "OP";

			String tempd[] = commencing.split("-");
			String y = tempd[0];
			String m = tempd[1];
			String d = tempd[2];
			String newseq = "";
			int yr = Integer.parseInt(y) % 1000;
			yr = Integer.parseInt(y);
			if (String.valueOf(seqno).length() == 1) {
				newseq = "000" + seqno;
			} else if (String.valueOf(seqno).length() == 2) {
				newseq = "00" + seqno;
			} else if (String.valueOf(seqno).length() == 3) {
				newseq = "0" + seqno;
			} else {
				newseq = "" + seqno;
			}
			abrivationid = clinicabrivation + yr + m + d + "" + newseq;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return abrivationid;
	}

	private int checkifSequenceExistLMH(String commencing) {

		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select seqno from opd_sequence where commencing = '" + commencing + "' ";
		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
				result++;
				String querry = "update opd_sequence set seqno='" + result + "' where commencing='" + commencing + "' ";
				PreparedStatement preparedStatement2 = connection.prepareStatement(querry);
				preparedStatement2.executeUpdate();
			} else {
				result = 1;
				String querry = "insert into opd_sequence(commencing,seqno) values(?,?)";
				PreparedStatement preparedStatement2 = connection.prepareStatement(querry);
				preparedStatement2.setString(1, commencing);
				preparedStatement2.setString(2, "" + result);
				preparedStatement2.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String generateOPDSequenceNewFormat() {

		String opdabrfinal = "";
		// if action =1 then casualty else ipd
		DateFormat dateFormat = new SimpleDateFormat("yyyy");
		Calendar cal = Calendar.getInstance();
		String year = dateFormat.format(cal.getTime());

		String yearRange = DateTimeUtils.getFinancialYearRange();
		int maxopdabr = getMaxOPDAbrivation(yearRange);
		int yr = Integer.parseInt(year) % 1000;

		int newmaxopdabr = maxopdabr + 1;
		String temp = String.valueOf(newmaxopdabr);
		int l = temp.length();
		String opdabrstr = "";
		if (l == 3) {
			opdabrstr = "0" + newmaxopdabr;
		} else if (l == 2) {
			opdabrstr = "00" + newmaxopdabr;
		} else if (l == 1) {
			opdabrstr = "000" + newmaxopdabr;
		} else {
			opdabrstr = "" + newmaxopdabr;
		}
		String prefix = "OP";
		//
		/*
		 * String abrivaationcode = getclinicAbrivationCode(); ipdabrfinal =
		 * abrivaationcode +prefix+ "/" + yearRange + "/" + ipdabrstr;
		 */

		// akash 10-08-2020 as per dipanjay sir new format
		String[] data = yearRange.split("-");
		String[] data1 = data[0].split("");
		String[] data2 = data[1].split("");
		String value = data1[2] + "" + data1[3] + "-" + data2[2] + "" + data2[3];
		opdabrfinal = prefix + "" + value + "/" + opdabrstr;

		int res = insertOPDAbrivation(yearRange, newmaxopdabr);
		return opdabrfinal;
	
	}

	private int insertOPDAbrivation(String yearRange, int newmaxopdabr) {
		int res = 0;
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(" insert into opd_sequence_numbers(date, seqno) values(?,?)");
			ps.setString(1, yearRange);
			ps.setInt(2, newmaxopdabr);
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	private int getMaxOPDAbrivation(String yearRange) {
		int maxid = 0;
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement(" select max(seqno) from opd_sequence_numbers where date='" + yearRange + "' ");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				maxid = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return maxid;
	}

	private String getAppointmentTypeText(String apmtType) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT name FROM apm_appointment_type where id=1 ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int updateOriesdata(OpdConsult opdConsult2, OpdConsult opdConsult1, int opdid, String docid, String regdate) {
		int res =0;
		try {
			String sql="update orises.apm_consultation_note set patientid="+opdConsult1.getId()+", appointmentid="+opdid+", lastmodified='"+regdate+"', practitionerid='"+docid+"', taken=1, oldapmtid='"+opdConsult2.getAppointmentid()+"', oldclientid='"+opdConsult2.getPatientid()+"' where appointmentid="+opdConsult2.getAppointmentid()+"";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public OpdConsult getConsultationdata(int opdid, String dbname) {
		PreparedStatement preparedStatement=null;
		OpdConsult opdConsult=new OpdConsult();
		try {
			String sql="select * from "+dbname+".apm_consultation_note where appointmentid="+opdid+"";
			preparedStatement=connection.prepareStatement(sql);
			ResultSet rs=preparedStatement.executeQuery();
			while(rs.next()){
				opdConsult.setId(rs.getInt(1));
				opdConsult.setPatientid(rs.getInt(2));
				opdConsult.setAppointmentid(rs.getInt(3));
				opdConsult.setHistory(rs.getString(4));
				opdConsult.setPractitionerid(rs.getInt(5));
				opdConsult.setDate(rs.getString(6));
				opdConsult.setCondition_id(rs.getInt(7));
				opdConsult.setDiagnosis_id(rs.getInt(8));
				opdConsult.setIpdid(rs.getInt(9));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return opdConsult;	}

	@Override
	public int insertFakeConsultationNote(OpdConsult opddata) {
		PreparedStatement pst = null;
		int result=0;
		String sql = "insert into apm_consultation_note (patientid,appointmentid,history,practitionerid,lastmodified,condition_id,diagnosis_id,ipdid) values(?,?,?,?,?,?,?,?)";
		try {
			pst=connection.prepareStatement(sql);
			pst.setInt(1, opddata.getPatientid());
			pst.setInt(2, opddata.getAppointmentid());
			pst.setString(3, opddata.getHistory());
			pst.setInt(4, opddata.getPractitionerid());
			pst.setString(5, opddata.getDate());
			pst.setInt(6, opddata.getCondition_id());
			pst.setInt(7, opddata.getDiagnosis_id());
			pst.setInt(8, opddata.getIpdid());
			result=pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public CompleteAppointment getOriesmedicinebillbyId(int patientid,int billid, String dbname,String regdate,int id) {
		PreparedStatement preparedStatement=null;
		CompleteAppointment opdConsult=new CompleteAppointment();
		try {
			String sql="select id,payby,date,discount,debit,vat,clientid,chargetype,"
					+ "notes,pclientid,priscid,balance,location,cgst,sgst,time,userid,refundamt,tpid,credit,"
					+ "oldparentid,newparentid,actual_total,actual_discount,disc_type,tempsessionid,grosstotal,"
					+ "grosssubtotal,initial_paymode,final_paymode,phar_ipdid,phar_bedid,phar_wardid,"
					+ "bill_practid,bill_practname from "+dbname+".apm_medicine_bill where clientid="+id+" and id="+billid+" limit 0,1";
			  preparedStatement=connection.prepareStatement(sql);
			  ResultSet rs=preparedStatement.executeQuery();
			  while(rs.next()){
				  opdConsult.setId(rs.getInt(1));
				  opdConsult.setPayBuy(rs.getString(2));
				  opdConsult.setInvoiceDate(regdate);
				  opdConsult.setDiscount(rs.getDouble(4));
				  opdConsult.setDebit(rs.getDouble(5));
				  opdConsult.setVat(rs.getDouble(6));
				  opdConsult.setClientId(""+patientid);
				  opdConsult.setChargeType(rs.getString(8));
				  opdConsult.setNotes(rs.getString(9));
				  opdConsult.setPclientid(rs.getString(10));
				  opdConsult.setPriscid(rs.getInt(11));
				  opdConsult.setBalance(rs.getString(12));
				  opdConsult.setLocation(rs.getString(13));
				  opdConsult.setCgst(rs.getString(14));
				  opdConsult.setSgst(rs.getString(15));
				  opdConsult.setInvoiceTime(rs.getString(16));
				  opdConsult.setUserid(rs.getString(17));
                  opdConsult.setRefundAmt(rs.getDouble(18));
                  opdConsult.setTpid(rs.getString(19));
                  opdConsult.setCredit(rs.getString(20));
                  opdConsult.setOldparentid(rs.getString(21));
                  opdConsult.setNewparentid(rs.getInt(22));
                  opdConsult.setActualtotal(rs.getString(23));
                  opdConsult.setActualdiscount(rs.getString(24));
                  opdConsult.setDisc_type(rs.getString(25));
                  opdConsult.setTempsessionid(rs.getInt(26));
                  opdConsult.setGrosstotal(rs.getString(27));
                  opdConsult.setGrosssubtotal(rs.getString(28));
                  opdConsult.setInitalpaymode(rs.getString(29));
                  opdConsult.setFinalpaymode(rs.getString(30));
                  opdConsult.setPhar_ipdid(rs.getString(31));
                  opdConsult.setBedid(rs.getString(32));
                  opdConsult.setWardid(rs.getString(33));
                  opdConsult.setPractitionerId(rs.getString(34));
                  opdConsult.setPractitionerName(rs.getString(35));
			  }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return opdConsult;
	}

	@Override
	public int updateOriesmedicinebill(int id,OpdConsult opdConsult1,String docid, String docname,int clientid, String newadmissiondate,String opdipdtype, String regdate,String newpharmdate, String dbname) {
		int res=0;
		try {
			//String date1=date.split(" ")[0];
			newadmissiondate=newadmissiondate.split(" ")[0];
			regdate=regdate.split(" ")[0];
			String sql="";
			if(opdipdtype=="0"){
			 sql="update "+dbname+".apm_medicine_bill set bill_practid="+docid+", bill_practname='"+docname+"', oldbillno='"+id+"', oldclientid='"+clientid+"' where id="+id+"";
			}else{
			 sql="update "+dbname+".apm_medicine_bill set bill_practid="+docid+", bill_practname='"+docname+"', oldbillno='"+id+"', oldclientid='"+clientid+"' where id="+id+"";
	
			}
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int updateOriesmedicinecharges(int id, OpdConsult opdConsult1,String docid, String docname, String newadmissiondate,String opdipdtype, String regdate,String newpharmdate, String dbname) {
		int res=0;
		try {
			//String date1=date.split(" ")[0];
			newadmissiondate=newadmissiondate.split(" ")[0];
			String sql="";
			regdate=regdate.split(" ")[0];
			if(opdipdtype=="0"){
			sql="update "+dbname+".apm_medicine_charges set user='"+opdConsult1.getPatientname()+"', practitionerId="+docid+", practitionerName='"+docname+"' where invoiceid="+id+"";

			}else{
			sql="update "+dbname+".apm_medicine_charges set user='"+opdConsult1.getPatientname()+"', practitionerId="+docid+", practitionerName='"+docname+"' where invoiceid="+id+"";
			}
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int updateOriesmedicinepayment(int id, OpdConsult opdConsult1, String newadmissiondate,String opdipdtype, String regdate,String newpharmdate, String dbname) {
		int res=0;
		try {
			//String date1=date.split(" ")[0];
			newadmissiondate=newadmissiondate.split(" ")[0];
			String sql="";
			String regdate1=regdate.split(" ")[0];
			if(opdipdtype=="0"){
			sql="update "+dbname+".apm_medicine_payment set  clientid="+opdConsult1.getId()+" ,date='"+regdate1+"',datetime='"+regdate+"' where billno="+id+"";
			}else{
			 sql="update "+dbname+".apm_medicine_payment set  clientid="+opdConsult1.getId()+" ,date='"+newpharmdate+"',datetime='"+newpharmdate+"' where billno="+id+"";
			}
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	/*@Override
	public OpdConsult getOriesmedicinebillDatabyId(int patientid,String date) {
		PreparedStatement preparedStatement=null;
		OpdConsult opdConsult=new OpdConsult();
		String sdate=date.split(" ")[0];
		try {
			String sql="select id,clientid,date,location from orises.apm_medicine_bill where clientid="+patientid+" and date between '"+sdate+"' and '"+sdate+"' ";
			preparedStatement=connection.prepareStatement(sql);
			ResultSet rs=preparedStatement.executeQuery();
			while(rs.next()){
				 opdConsult.setId(rs.getInt(1));
				 opdConsult.setPatientid(rs.getInt(2));
				 opdConsult.setDate(rs.getString(3));
				 opdConsult.setLocation(rs.getString(4));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return opdConsult;
	}*/

	@Override
	public int insertFakeMedicinebill(CompleteAppointment appointment) {
		int result=0;
		try {
			String sql="insert into apm_medicine_bill (payby,date,discount,debit,vat,clientid,chargetype,"
					+ "notes,pclientid,priscid,balance,location,cgst,sgst,time,userid,refundamt,tpid,credit,"
					+ "oldparentid,newparentid,actual_total,actual_discount,disc_type,tempsessionid,grosstotal,"
					+ "grosssubtotal,initial_paymode,final_paymode,phar_ipdid,phar_bedid,phar_wardid,"
					+ "bill_practid,bill_practname) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, appointment.getPayBuy());
			ps.setString(2, appointment.getInvoiceDate());
			ps.setDouble(3, appointment.getDiscount());
			ps.setDouble(4, appointment.getDebit());
			ps.setDouble(5, appointment.getVat());
			ps.setString(6, appointment.getClientId());
			ps.setString(7, appointment.getChargeType());
			ps.setString(8, appointment.getNotes());
			ps.setString(9, appointment.getPclientid());
			ps.setInt(10, appointment.getPriscid());
			ps.setString(11, appointment.getBalance());
			ps.setString(12, appointment.getLocation());
			ps.setString(13, appointment.getCgst());
			ps.setString(14, appointment.getSgst());
			ps.setString(15, appointment.getInvoiceTime());
			ps.setString(16, appointment.getUserid());
			ps.setDouble(17, appointment.getRefundAmt());
			ps.setString(18, appointment.getTpid());
			ps.setString(19, appointment.getCredit());
			ps.setString(20, appointment.getOldparentid());
			ps.setString(21, ""+appointment.getNewparentid());
			ps.setString(22, appointment.getActualtotal());
			ps.setString(23, appointment.getActualdiscount());
			ps.setString(24, appointment.getDisc_type());
			ps.setString(25, ""+appointment.getTempsessionid());
			ps.setString(26, appointment.getGrosstotal());
			ps.setString(27, appointment.getGrosssubtotal());
			ps.setString(28, appointment.getInitalpaymode());
			ps.setString(29, appointment.getFinalpaymode());
			ps.setString(30, appointment.getPhar_ipdid());
			ps.setString(31, appointment.getBedid());
			ps.setString(32, appointment.getWardid());
			ps.setString(33, appointment.getPractitionerId());
			ps.setString(34, appointment.getPractitionerName());
			result=ps.executeUpdate();
			
			if(result>0){
				  
				  ResultSet rs=ps.getGeneratedKeys();
				  while(rs.next()){
					   
					    result=rs.getInt(1);
				  }
				  
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Vector<Priscription> getMedicineChargeslistbyId(int id,int invoiceid, String dbname,String regdate,int pid) {
		PreparedStatement preparedStatement=null;
		Vector<Priscription>list=new Vector<Priscription>();
		try {
			String sql="select invoiceid,user,charge,"
					+ "practitionerId,practitionerName,clientId,commencing,paybuy,quantity,"
					+ "reqqty,medicineid,pclientid,thirdPartyId,product_id,cgst,sgst,returnbillno,"
					+ "discount_share,tgstamt,gstper,oldchargeid,isbarcodesale,"
					+ "bill_purpice,bill_pur_gst from "+dbname+".apm_medicine_charges where clientId="+pid+" and invoiceid="+invoiceid+"";
			  preparedStatement=connection.prepareStatement(sql);
			  ResultSet rs=preparedStatement.executeQuery();
			  while(rs.next()){
				  Priscription prisc=new Priscription();
				  prisc.setId(rs.getInt(1));
				  prisc .setClientname(rs.getString(2));
				  prisc.setMrp(rs.getString(3));
				  prisc.setPractitionerid(rs.getString(4));
				  prisc.setPractitionername(rs.getString(5));
				  prisc.setClientId(""+id);
				  prisc.setDate(regdate);
				  prisc.setWhopay(rs.getString(8));
				  prisc.setSaleqty(rs.getInt(9));
				  prisc.setReqqty(rs.getInt(10));
				  prisc.setMedicineid(rs.getString(11));
				  prisc.setPclientid(rs.getString(12));
				  prisc.setTpid(rs.getString(13));
				  prisc.setProduct_id(rs.getString(14));
				  prisc.setCgst(rs.getString(15));
				  prisc.setSgst(rs.getString(16));
				  prisc.setBillno(rs.getString(17));
				  prisc.setSharediscount(rs.getString(18));
				  prisc.setTgstamt(rs.getString(19));
				  prisc.setGstper(rs.getString(20));
				  prisc.setChargeid(rs.getString(21));
				  prisc.setBarcodesale(rs.getInt(22));
				  prisc.setBill_purpice(rs.getDouble(23));
				  prisc.setBill_pur_gst(rs.getDouble(24));
				  list.add(prisc);
			  }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int insertFakeMedicineCharges(Priscription priscription, int billno) {
		int result=0;
	try {
			
			String sql="insert into apm_medicine_charges (invoiceid,user,charge,"
					+ "practitionerId,practitionerName,clientId,commencing,paybuy,quantity,"
					+ "reqqty,medicineid,pclientid,thirdPartyId,product_id,cgst,sgst,returnbillno,"
					+ "discount_share,tgstamt,gstper,oldchargeid,isbarcodesale,"
					+ "bill_purpice,bill_pur_gst) values " +
					"(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setInt(1,billno);
			ps.setString(2, priscription.getClientname());
			ps.setString(3, priscription.getMrp());
			ps.setString(4, priscription.getPrectionerid());
			ps.setString(5, priscription.getFullname());
			ps.setString(6, priscription.getClientId());
			ps.setString(7, priscription.getDate());
			ps.setString(8, priscription.getWhopay());
			ps.setInt(9, priscription.getSaleqty());
			ps.setInt(10, priscription.getReqqty());
			ps.setString(11, priscription.getMdicinenameid());
			ps.setString(12, priscription.getPclientid());
			ps.setString(13, priscription.getTpid());
			ps.setString(14, priscription.getProduct_id());
			ps.setString(15, priscription.getCgst());
			ps.setString(16, priscription.getSgst());
			ps.setString(17, priscription.getBillno());
			ps.setString(18, priscription.getSharediscount());
			ps.setString(19, priscription.getTgstamt());
			ps.setString(20, priscription.getGstper());
			ps.setString(21, priscription.getChargeid());
			ps.setString(22, ""+priscription.getBarcodesale());
			ps.setString(23, ""+priscription.getBill_purpice());
			ps.setString(24, ""+priscription.getBill_pur_gst());
			result= ps.executeUpdate();
			
			if(result>0){
				
				ResultSet rs=ps.getGeneratedKeys();
				while(rs.next()){
					  result =rs.getInt(1);
				}
			}
			
			
			String date = priscription.getDate();
			int openingstock = getOpeningStock(priscription.getProduct_id());
			boolean checkopningstockexist = checkopeningstockexist(priscription.getProduct_id(),date);
			if(!checkopningstockexist){
				int r = saveOpeningStock(priscription.getProduct_id(),date,openingstock,"0");
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
		
	}

	private int saveOpeningStock(String product_id, String date, int openingstock, String isconsume) {
		 PreparedStatement preparedStatement = null;
		  int result = 0;
		  String sql = "insert into medicine_opening_stock(prodid, commencing, stock,isconsume) values(?,?,?,?) ";
		  
		  try{
		   preparedStatement = connection.prepareStatement(sql);
		   preparedStatement.setString(1, product_id);
		   preparedStatement.setString(2, date);
		   preparedStatement.setInt(3, openingstock);
		   preparedStatement.setString(4, isconsume);
		   result = preparedStatement.executeUpdate();
		   
		  }catch (Exception e) {
		   // TODO: handle exception
		  }
		  return result;
	}

	private boolean checkopeningstockexist(String product_id, String date) {
		  PreparedStatement preparedStatement = null;
		  boolean result = false;
		  String sql = "select * from medicine_opening_stock where commencing='"+date+"' and prodid="+product_id+" ";
		  
		  try{
		   preparedStatement = connection.prepareStatement(sql);
		   ResultSet rs = preparedStatement.executeQuery();
		   if(rs.next()){
		    result = true;
		   }
		   
		  }catch (Exception e) {
		   // TODO: handle exception
		  }
		  return result;
	}

	private int getOpeningStock(String product_id) {
		 PreparedStatement preparedStatement = null;
		  int result = 0;
		  String sql = "SELECT stock FROM inventory_product where id = '"+product_id+"' ";
		  
		  try{
		   preparedStatement = connection.prepareStatement(sql);
		   ResultSet rs = preparedStatement.executeQuery();
		   if(rs.next()){
		    result = rs.getInt(1);
		   }
		   
		  }catch (Exception e) {
		   // TODO: handle exception
		  }
		   
		   
		  return result;
	}

	@Override
	public Vector<CompleteAppointment> getMedicinePaymentlistbyid(int id,int billno, String dbname, String regdate,int pid) {
		PreparedStatement preparedStatement=null;
		Vector<CompleteAppointment>list=new Vector<>();
		
		try {
			 String sql="select clientid, payment, paymode, date, tpid, paymentnote, pclientid, userid, location, datetime from "+dbname+".apm_medicine_payment where clientid="+pid+" and billno="+billno+"";
			 preparedStatement = connection.prepareStatement(sql); 
			 ResultSet rs = preparedStatement.executeQuery();
			 while(rs.next()){
				 CompleteAppointment appointment=new CompleteAppointment();
				 appointment.setClientId(""+id);
				 appointment.setPayamt(rs.getString(2));
				 appointment.setPaymode(rs.getString(3));
				 appointment.setDate(regdate);
				 appointment.setTpid(rs.getString(5));
				 appointment.setPaynote(rs.getString(6));
				 appointment.setPclientid(rs.getString(7));
				 appointment.setUserid(rs.getString(8));
				 appointment.setLocation(rs.getString(9));
				 appointment.setDatetime(rs.getString(10));
				 list.add(appointment);
				
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int insertFakeMedicinePayment(CompleteAppointment completeAppointment, int billno, int paymentseqno) {
int result=0;
		
		try {

			String sql="insert into apm_medicine_payment (clientid, billno, payment, paymode, date, tpid, paymentnote, crdinvoiceid,pclientid,userid,location,onsamedate,paymentseqno) values " +
					"(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement ps=connection.prepareStatement(sql);
			Double payment=Double.parseDouble(completeAppointment.getPayamt());
			ps.setString(1, completeAppointment.getClientId());
			ps.setInt(2, billno);
			ps.setDouble(3, payment);
			ps.setString(4, completeAppointment.getPaymode());
			ps.setString(5, completeAppointment.getDate());
			ps.setString(6, completeAppointment.getTpid());
			ps.setString(7, completeAppointment.getPaynote());
			ps.setString(8, "0");	
			ps.setString(9,completeAppointment.getPclientid());
			ps.setString(10, completeAppointment.getUserid());
			ps.setString(11, completeAppointment.getLocation());
			ps.setString(12, "0");
			ps.setString(13, ""+paymentseqno);
			result=ps.executeUpdate();
			
			if(result>0){
				ResultSet rs= ps.getGeneratedKeys();
				if(rs.next()){
					result =rs.getInt(1);
				}
				
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public int getPharmacyPaymentSeqNo(String location) {
		int res =0;
		try {
			String sql="select max(paymentseqno) from apm_medicine_payment where location='"+location+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				res = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int updateMedicineQty(int saleqty, String product_id, int plusminus) {

		int result = 0;
		int stock = 0;
		try {

			String sql = "select stock from inventory_product where id=" + product_id + " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				stock = rs.getInt(1);
			}

			if (plusminus == 0) {
				stock = stock - saleqty;
			} else {
				stock = stock + saleqty;
			}

			String updatesql = "update inventory_product set stock=" + stock + " where id=" + product_id + " ";
			PreparedStatement preparedStatement = connection.prepareStatement(updatesql);

			result = preparedStatement.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	@Override
	public int updatePatientOpdStatusbyId(int patientid,LoginInfo loginInfo) {
		int result=0;
		try {
			String sql="update apm_patient set opd=1 where id="+patientid+" and f_userid='"+loginInfo.getUserId()+"' ";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			result=preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Vector<OpdConsult> getoriesdatalistDatabyid(int patientid, String date,String opdipdtype,String dischargedate, String newadmissiondate, String dischargedate2,OpdConsult opdConsult1,int clientid, String dbname) {
		// TODO Auto-generated method stub
		
		PreparedStatement preparedStatement=null;
		Vector<OpdConsult>list=new Vector<OpdConsult>();
		
		String sdate=date.split(" ")[0];
		try {
			StringBuffer buffer=new StringBuffer();
		   buffer.append("select id,clientid,date,location from "+dbname+".apm_medicine_bill");
		   if(opdipdtype=="0"){
		   buffer.append(" where clientid="+clientid+" and date between '"+sdate+"' and '"+sdate+" 23:59:59' group by date ");
		   }else{
		    buffer.append(" where clientid="+clientid+" and date between '"+sdate+"' and '"+dischargedate+"' group by date  ");
		   }
			preparedStatement=connection.prepareStatement(buffer.toString());
			ResultSet rs=preparedStatement.executeQuery();
			while(rs.next()){
				 OpdConsult opdConsult=new OpdConsult();
				 
				 opdConsult.setId(rs.getInt(1));
				 opdConsult.setPatientid(rs.getInt(2));
				 opdConsult.setDate(rs.getString(3));
				 opdConsult.setLocation(rs.getString(4));
				 
				 list.add(opdConsult);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	    return list;
	}

	@Override
	public OpdConsult getOrisesAvailableslot(String docid, String specialityid,boolean isot,String dbname) {
		PreparedStatement preparedStatement=null;
		OpdConsult opdConsult=new OpdConsult();
		StringBuffer sql = new StringBuffer();
		JDBCMasterDAO dao=new JDBCMasterDAO(connection);
		try {
			/*
			 * sql.
			 * append("select apm_available_slot.clientId,apm_available_slot.id,commencing,ipd_addmission_form.id,admissiondsate,ipd_addmission_form.treatmentepisodeid from orises.apm_available_slot inner join orises.apm_user on apm_user.id=apm_available_slot.diaryuserid "
			 * ); sql.
			 * append("inner join orises.apm_client_parent_priscription on apm_available_slot.clientId=apm_client_parent_priscription.clientid "
			 * ); sql.
			 * append("inner join orises.ipd_addmission_form on apm_available_slot.clientId=ipd_addmission_form.clientid "
			 * ); sql.append("where discription='"
			 * +specialityid+"' and apm_available_slot.taken!=1 limit 0,1");
			 */
			 
			 sql.append("select ipd_addmission_form.id,ipd_addmission_form.clientid,ipd_addmission_form.admissiondsate,ipd_addmission_form.treatmentepisodeid ");
			 sql.append("from "+dbname+".ipd_addmission_form inner join "+dbname+".apm_user on ");
			 sql.append("apm_user.id=ipd_addmission_form.practitionerid inner join  ");
			 sql.append(""+dbname+".apm_client_parent_priscription on ipd_addmission_form.clientid=apm_client_parent_priscription.clientid ");
			 
			 if(isot) {
		      if(dbname.equals("bams1")){
		    	  sql.append("inner join "+dbname+".apm_available_slot on apm_available_slot.ipdno=ipd_addmission_form.id where discription='"+specialityid+"' ");
				  sql.append("and ipd_addmission_form.taken!=1 and ipdno!=0 limit 0,1 ");
		      }	
		      else {
		    	  sql.append("inner join "+dbname+".apm_available_slot on apm_available_slot.ipdno=ipd_addmission_form.id where discription='"+specialityid+"' ");
					 sql.append("and ipd_addmission_form.taken!=1 and apm_client_parent_priscription.billno!=0 and ipdno!=0 limit 0,1 ");
				}
             }else {
				 if(dbname.equals("bams1")){
					sql.append("where discription='"+specialityid+"' and ipd_addmission_form.bedid=0 and ipd_addmission_form.taken!=1 limit 0,1");
				 }
				 else {
					 sql.append("where discription='"+specialityid+"' and ipd_addmission_form.taken!=1 and apm_client_parent_priscription.billno!=0 limit 0,1");
				}
				 
			 }
			 
			 preparedStatement = connection.prepareStatement(sql.toString());
			 dao.logj(sql.toString());
			 ResultSet rs=preparedStatement.executeQuery();
			
			while(rs.next()){
				opdConsult.setIpdid(rs.getInt(1));
				opdConsult.setPatientid(rs.getInt(2));
				opdConsult.setAppointmentid(0);
				//opdConsult.setDate(rs.getString(3));
				opdConsult.setIpdadmissiondte(rs.getString(3));
				opdConsult.setAtreatmentepid(rs.getString(4));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return opdConsult;
	}

	@Override
	public Vector<OpdConsult> getoriesAvailableslotlistbyid(int patientid,String auadmissiondate,String specialityid, String dbname) {
		PreparedStatement pst=null;
		Vector<OpdConsult>list=new Vector<OpdConsult>();
		if(auadmissiondate!=null) {
		auadmissiondate=DateTimeUtils.isNull(auadmissiondate.split(" ")[0]);
		}
		try {
			String sql="";
			int count=opdcounforFakepatient(patientid,auadmissiondate,dbname);
			if(count==0) {
				sql="select apm_available_slot.clientId,apm_available_slot.id,commencing  from "+dbname+".apm_available_slot inner join "+dbname+".apm_user on apm_user.id=apm_available_slot.diaryuserid "
						+ " where apm_user.discription='"+specialityid+"' and apm_available_slot.aptmtype='1' limit 0,1";
			}else {
			 sql="select apm_available_slot.clientId,apm_available_slot.id,commencing  from "+dbname+".apm_available_slot inner join "+dbname+".apm_user on apm_user.id=apm_available_slot.diaryuserid "
					+ " where apm_available_slot.clientId='"+patientid+"' and commencing<='"+auadmissiondate+"' group by commencing ";
			}
			pst=connection.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				OpdConsult opdConsult=new OpdConsult();
				
				opdConsult.setPatientid(rs.getInt(1));
				opdConsult.setAppointmentid(rs.getInt(2));
				opdConsult.setDate(rs.getString(3));
				list.add(opdConsult);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private int opdcounforFakepatient(int patientid, String auadmissiondate, String dbname) {
		int count=0;
		PreparedStatement pst=null;
		try {
			String sql="select count(*) from "+dbname+".apm_available_slot inner join "+dbname+".apm_user on apm_user.id=apm_available_slot.diaryuserid "
					+ " where apm_available_slot.clientId='"+patientid+"' and commencing<='"+auadmissiondate+"' ";
			pst=connection.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				count=rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int updateOriesAvailableslotdata(OpdConsult opdConsult2, OpdConsult opdConsult1, int opdid, String docid,
			String regdate, String docname, String dbname) {
		int res =0;
		String commencing=regdate.split(" ")[0];
		try {
			String sql="update "+dbname+".apm_available_slot set  diaryuserid='"+docid+"',diaryusername='"+docname+"',clientname='"+opdConsult1.getPatientname()+"',complete_datetime='"+regdate+"', taken=1, oldapmtid='"+opdConsult2.getAppointmentid()+"', oldclientid='"+opdConsult2.getPatientid()+"' where id="+opdConsult2.getAppointmentid()+"";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public Vector<Investigation> getParentInvestigationlist(OpdConsult opdConsult2,String regdate,String opdipdtype,String newadmissiondate, String dischargedate2,Investigation inv, String dbname) {
		PreparedStatement pst=null;
		dischargedate2=dischargedate2.split(" ")[0];
		dischargedate2=dischargedate2+" 23:59:59";
		newadmissiondate=newadmissiondate.split(" ")[0];
		//newadmissiondate=newadmissiondate+" 01:59:59";
		Vector<Investigation>list=new Vector<Investigation>();
		try {
			StringBuffer buffer=new StringBuffer();
			buffer.append("select clientid,practitionerid,conditionid,advoice,english,regional,hindi,prepay,postpay,other,lastmodified,reporttype,ipdid,jobtitle,invsttypeid,department,userid,updatedby,invreq,opdid,pkg,created_userid,created_datetime,id from "+dbname+".apm_client_parent_investigation");
			buffer.append(" where id= "+inv.getId()+" ");
	
			pst=connection.prepareStatement(buffer.toString());
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				Investigation investigation=new Investigation();
				investigation.setClientId(rs.getString(1));
				investigation.setPrectionerid(rs.getString(2));
				investigation.setConditionid(rs.getString(3));
				investigation.setAdvoice(rs.getString(4));
				investigation.setEnglish(rs.getString(5));
				investigation.setHindi(rs.getString(6));
				investigation.setRegional(rs.getString(7));
				investigation.setPrepay(rs.getString(8));
				investigation.setPostpay(rs.getString(9));
				investigation.setOtherpay(rs.getString(10));
				investigation.setDate(rs.getString(11));
				investigation.setReporttype(rs.getString(12));
				investigation.setIpdid(rs.getString(13));
				investigation.setJobtitle(rs.getString(14));
				investigation.setInvsttypeid(rs.getString(15));
				investigation.setDepartment(rs.getString(16));
				investigation.setUserid(rs.getString(17));
				investigation.setUpdatedby(rs.getString(18));
				investigation.setInvreq(rs.getString(19));
				investigation.setOpdid(rs.getString(20));
				investigation.setInvpkg(rs.getString(21));
				investigation.setUseridnew(rs.getString(22));
				investigation.setCreated_date(rs.getString(23));
				investigation.setId(rs.getInt(24));
				list.add(investigation);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int insertParentInvestigation(Investigation investigation, int id, String docid) {
		PreparedStatement preparedStatement=null;
		int result=0;
		try {
			String sql = "insert into apm_client_parent_investigation(clientid,practitionerid,conditionid,advoice,english,regional,hindi,prepay,postpay,other,lastmodified,reporttype,ipdid,jobtitle,invsttypeid,department,userid,updatedby,invreq,opdid,pkg,created_userid,created_datetime,compstatus,compdate,upstatus) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
               preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
   			preparedStatement.setString(2, docid);
   			preparedStatement.setString(3, investigation.getConditionid());
   			preparedStatement.setString(4, investigation.getAdvoice());
   			preparedStatement.setString(5, investigation.getEnglish());
   			preparedStatement.setString(6, investigation.getRegional());
   			preparedStatement.setString(7, investigation.getHindi());
   			preparedStatement.setString(8, investigation.getPrepay());
   			preparedStatement.setString(9, investigation.getPostpay());
   			preparedStatement.setString(10, investigation.getOtherpay());
   			preparedStatement.setString(11, investigation.getDate());
   			preparedStatement.setString(12, investigation.getReporttype());
   			preparedStatement.setString(13, investigation.getIpdid());
   			preparedStatement.setString(14, investigation.getJobtitle());
   			preparedStatement.setString(15, investigation.getInvsttypeid());

   			
   			preparedStatement.setString(16, investigation.getDepartment());
   			preparedStatement.setString(17, investigation.getUserid());
   			preparedStatement.setString(18, investigation.getUpdatedby());
   			preparedStatement.setString(19, investigation.getInvreq());
   			preparedStatement.setString(20, investigation.getOpdid());
   			preparedStatement.setString(21, investigation.getInvpkg());
   			preparedStatement.setString(22, investigation.getUseridnew());
   			preparedStatement.setString(23, investigation.getCreated_date());
   			preparedStatement.setString(24, "1");
   			preparedStatement.setString(25, investigation.getDate());
   			preparedStatement.setString(26, "1");
   			result = preparedStatement.executeUpdate();

   			if (result == 1) {
   				ResultSet resultSet = preparedStatement.getGeneratedKeys();
   				if (resultSet.next()) {
   					result = resultSet.getInt(1);
   				}
   			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Vector<Investigation> getClientInvestigationList(int parentid, String regdate, String date, String dbname) {
		PreparedStatement pst=null;
		Vector<Investigation>list=new Vector<Investigation>();
		try {
			String sql="select clientid,conditionid,invstcode,"
				+ "invsttype,invstgroup,invstname,specimen,invstUnit,invstreporttype,parentid,"
				+ "lastmodified,normvalue,invnameid,critical_value,obsvalue,value_cf from "+dbname+".apm_client_investigation where parentid='"+parentid+"' " ;
			pst=connection.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				Investigation investigation=new Investigation();
				investigation.setClientId(rs.getString(1));
				investigation.setConditionid(rs.getString(2));
				investigation.setInvstcode(rs.getString(3));
				investigation.setInvsttype(rs.getString(4));
				investigation.setInvstgroup(rs.getString(5));
				investigation.setInvstname(rs.getString(6));
				investigation.setSpecimen(rs.getString(7));
				investigation.setInvstUnit(rs.getString(8));
				investigation.setInvstreporttype(rs.getString(9));
				investigation.setParentid(rs.getInt(10));
				investigation.setDate(rs.getString(11));
				investigation.setNormvalue(rs.getString(12));
				investigation.setInvestnameid(rs.getString(13));
				investigation.setCritical_value(rs.getString(14));
				investigation.setObsvalue(rs.getString(15));
				investigation.setValue_cf(rs.getString(16));
				list.add(investigation);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int insertClientInvestigation(int parentid, Investigation investigation2,int clientid, String docid) {
		PreparedStatement preparedStatement=null;
		int result = 0;
		String sql = "insert into apm_client_investigation(clientid,practitionerid,conditionid,invstcode,"
				+ "invsttype,invstgroup,invstname,specimen,invstUnit,invstreporttype,parentid,"
				+ "lastmodified,normvalue,invnameid,critical_value,obsvalue,value_cf) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	    try {
	    	preparedStatement=connection.prepareStatement(sql);
	    	preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, ""+clientid);
			preparedStatement.setString(2, docid);
			preparedStatement.setString(3, investigation2.getConditionid());
			preparedStatement.setString(4, investigation2.getInvstcode());
			preparedStatement.setString(5, investigation2.getInvsttype());
			preparedStatement.setString(6, investigation2.getInvstgroup());
			preparedStatement.setString(7, investigation2.getInvstname());
			preparedStatement.setString(8, investigation2.getSpecimen());
			preparedStatement.setString(9, investigation2.getInvstUnit());
			preparedStatement.setString(10, investigation2.getInvstreporttype());
			preparedStatement.setInt(11, parentid);
			preparedStatement.setString(12, investigation2.getDate());
			preparedStatement.setString(13, investigation2.getNormvalue());
		    preparedStatement.setString(14, investigation2.getInvestnameid());
		    preparedStatement.setString(15, investigation2.getCritical_value());
		    preparedStatement.setString(16, investigation2.getObsvalue());
		    preparedStatement.setString(17, investigation2.getValue_cf());
			result = preparedStatement.executeUpdate();
		 	
		   } catch (Exception e) {
			e.printStackTrace();
		   }

		return result;
	}

	@Override
	public int updateOriesParentInvestigation(OpdConsult opdConsult2, String regdate, String newadmissiondate, String dischargedate2,String opdipdtype,String dischargedate,String newinvdate,Investigation inv, String dbname) {
		int res =0;
		String invdate=inv.getDate().split(" ")[0]; 
		try {
			StringBuffer buffer=new StringBuffer();
			if(opdipdtype=="0"){
			buffer.append("update "+dbname+".apm_client_parent_investigation set lastmodified='"+inv.getDate()+"' where id='"+inv.getId()+"' ");
			}else{
			buffer.append("update "+dbname+".apm_client_parent_investigation set lastmodified='"+newinvdate+"' where id='"+inv.getId()+"' ");
			}
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int updateOriesClientInvestigation(OpdConsult opdConsult2, String regdate, int parentid,String opdipdtype, String date, String dbname) {
		int res =0;
		try {
			String sql="";
			if(opdipdtype=="0"){
			 sql="update "+dbname+".apm_client_investigation set lastmodified='"+regdate+"' where clientid='"+opdConsult2.getPatientid()+"' and parentid='"+parentid+"' ";
		   }else{
				 sql="update "+dbname+".apm_client_investigation set lastmodified='"+date+"' where clientid='"+opdConsult2.getPatientid()+"' and parentid='"+parentid+"' ";

		   }
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public Vector<OpdConsult> getPriscriptiondatelist(int patientid,String date,String opdipdtype,int ipdid, String dbname,int fakeipdid) {
		PreparedStatement pst=null;
		Vector<OpdConsult>list=new Vector<OpdConsult>();
		try {
			String sql="";
			if(opdipdtype=="0") {
			 sql="select lastmodified,id from "+dbname+".apm_client_parent_priscription where clientid='"+patientid+"' and lastmodified between '"+date+"' and '"+date+" 23:59:59' and ipdid=0";
			}else {
			 sql="select lastmodified,id from "+dbname+".apm_client_parent_priscription where clientid='"+patientid+"' and ipdid="+fakeipdid+"";

			}
			pst=connection.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				OpdConsult opdConsult=new OpdConsult();
				opdConsult.setLastmodified(rs.getString(1));
				opdConsult.setId(rs.getInt(2));
				list.add(opdConsult);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int updateOriesParentPriscription(OpdConsult opdConsult2, String regdate, String time,String pdatetime,String opdipdtype, String dischargedate, String newpriscdate,OpdConsult priscridate, String dbname) {
		int res =0;
		try {
			String priscdate=priscridate.getLastmodified().split(" ")[0];
		    regdate=regdate.split(" ")[0];
		    String admissiondate=opdConsult2.getDate().split(" ")[0];
		    //admissiondate=admissiondate+" 23:59:59";
		    dischargedate =dischargedate.split(" ")[0]+" 23:59:59";
		    StringBuffer buffer=new StringBuffer();
			
			if(opdipdtype=="0"){
			//buffer.append("update "+dbname+".apm_client_parent_priscription set lastmodified='"+regdate+" "+time+"'");
			//buffer.append(" where id="+priscridate.getId()+" ");
			}else{
				//buffer.append("update "+dbname+".apm_client_parent_priscription set lastmodified='"+newpriscdate+"'");
				//buffer.append(" where clientid='"+opdConsult2.getPatientid()+"' and lastmodified  between '"+priscdate+"' and '"+priscdate+" 23:59:59' and ipdid!=0");
			}
			
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;	}

	@Override
	public Vector<Priscription> getParentPriscriptionList(OpdConsult opdConsult2, String regdate,String opdipdtype,String dischargedate, String newadmissiondate, String dischargedate2,OpdConsult priscridate, String dbname) {
		PreparedStatement pst=null;
		Vector<Priscription> list=new Vector<Priscription>();
		try {
			regdate=regdate.split(" ")[0];
			dischargedate=dischargedate  + " 23:59:59";
			dischargedate2=dischargedate2.split(" ")[0];
			dischargedate2=dischargedate2+" 23:59:59";
			newadmissiondate=newadmissiondate.split(" ")[0];
			//newadmissiondate=newadmissiondate+" 01:59:59";
			StringBuffer buffer=new StringBuffer();
			buffer.append("select conditionid,dosenotes,followupcount,followupstype,advoice,english,regional,hindi,prepay,postpay,other,");
			buffer.append(" lastmodified,ipdid,followupdate,department,userid,");
			buffer.append(" location_s,default_location,id from "+dbname+".apm_client_parent_priscription");
			buffer.append(" where id="+priscridate.getId()+"");
			
			pst=connection.prepareStatement(buffer.toString());
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
               Priscription priscription=new Priscription();
               priscription.setConditionid(rs.getString(1));
               priscription.setDosenotes(rs.getString(2));
               priscription.setFollowupsqty(rs.getString(3));
               priscription.setFollowupstype(rs.getString(4));
               priscription.setAdvoice(rs.getString(5));
               priscription.setEnglish(rs.getString(6));
               priscription.setRegional(rs.getString(7));
               priscription.setHindi(rs.getString(8));
               priscription.setPrepay(rs.getString(9));
               priscription.setPostpay(rs.getString(10));
               priscription.setOtherpay(rs.getString(11));
               priscription.setLastmodified(rs.getString(12));
               priscription.setIpdid(rs.getString(13));
               priscription.setFollowupdate(rs.getString(14));
               priscription.setDepartment(rs.getString(15));
               priscription.setUserid(rs.getString(16));
               priscription.setLocationid(rs.getInt(17));
               priscription.setDefault_location(rs.getString(18));
               priscription.setId(rs.getInt(19));
              list.add(priscription);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int insertParentPriscription(Priscription priscription, int opdid, int id, String docid, OpdConsult opdConsult2,String opdipdtype) {
		PreparedStatement preparedStatement=null;
		int result=0;
		try {
			String sql = "insert into apm_client_parent_priscription(clientid,practitionerid,conditionid,dosenotes,"
					+ "followupcount,followupstype,advoice,english,regional,hindi,prepay,postpay,other,"
					+ "lastmodified,ipdid,followupdate,discharge,department,opdid,admission,userid,"
					+ "location_s,default_location,fromtreatmentgiven,lastappointmentid,"
					+ "gynicform,cf_apmt_id) "
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, ""+id);
				preparedStatement.setString(2, docid);
				preparedStatement.setString(3, priscription.getConditionid());
				preparedStatement.setString(4, priscription.getPriscdosenotes());
				preparedStatement.setString(5, priscription.getFollowupsqty());
				preparedStatement.setString(6, priscription.getFollowupstype());
				preparedStatement.setString(7, priscription.getPriscadvoice());
				preparedStatement.setString(8, priscription.getEnglish());
				preparedStatement.setString(9, priscription.getRegional());
				preparedStatement.setString(10, priscription.getHindi());
				preparedStatement.setString(11, priscription.getPrepay());
				preparedStatement.setString(12, priscription.getPostpay());
				preparedStatement.setString(13, priscription.getOtherpay());
				preparedStatement.setString(14, priscription.getFak_newpriscdate());
				preparedStatement.setString(15, ""+opdConsult2.getIpdid());
				preparedStatement.setString(16, priscription.getFollowupdate());
				preparedStatement.setString(17, "0");
				preparedStatement.setString(18, priscription.getDepartment());
				preparedStatement.setString(19, ""+opdid);
				preparedStatement.setString(20, "0");
				preparedStatement.setString(21, priscription.getUserid());
				if(opdipdtype=="0"){
				preparedStatement.setString(22, "1");
				}else{
				preparedStatement.setString(22, "0");
				}
				preparedStatement.setString(23, priscription.getDefault_location());
				preparedStatement.setString(24, "0");
				preparedStatement.setString(25, "0");
				preparedStatement.setString(26, "0");
				preparedStatement.setString(27, "");
				result = preparedStatement.executeUpdate();
				if (result == 1) {
	   				ResultSet resultSet = preparedStatement.getGeneratedKeys();
	   				if (resultSet.next()) {
	   					result = resultSet.getInt(1);
	   				}
	   			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int updateOriesClientPriscription(Priscription priscription, OpdConsult opdConsult2, String dbname) {
		int res =0;
		try {
			String sql="update "+dbname+".apm_client_priscription set lastmodified='"+priscription.getLastmodified()+"' where clientid='"+opdConsult2.getPatientid()+"' and parentid='"+priscription.getId()+"' ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public Vector<Priscription> getClientPriscriptionList(Priscription priscription, String dbname) {
		PreparedStatement pst=null;
		Vector<Priscription> list=new Vector<Priscription>();
		try {
			
			String sql="select conditionid,categoryid,mdicineid,mdicinename,dose,frequency,days,lastmodified,code,type,total,parentid,priscdurationtype,notes,unit,sqno,prisctimeid,prisctimename,priscremark,unitextension,ipdtimeshow,dr_qty,nurse_qty,masterdose,dddose,id from "+dbname+".apm_client_priscription where parentid='"+priscription.getId()+"'";
			pst=connection.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				Priscription prisc=new Priscription();
				prisc.setConditionid(rs.getString(1));
				prisc.setCategoryid(rs.getString(2));
				prisc.setMedicineid(rs.getString(3));
				prisc.setMdicinenametxt(rs.getString(4));
				prisc.setPriscdose(rs.getString(5));
				prisc.setPriscfreq(rs.getString(6));
				prisc.setPriscdays(rs.getString(7));
				prisc.setLastmodified(rs.getString(8));
				prisc.setPrisccode(rs.getString(9));
				prisc.setPrisctype(rs.getString(10));
				prisc.setPrisctotal(rs.getString(11));
				prisc.setParentid(rs.getString(12));
				prisc.setPriscdurationtype(rs.getString(13));
				prisc.setDosenotes(rs.getString(14));
				prisc.setUnit(rs.getString(15));
				prisc.setSrno(rs.getString(16));
				prisc.setPriscriptiontime(rs.getString(17));
				prisc.setPrisctimename(rs.getString(18));
				prisc.setPriscindivisualremark(rs.getString(19));
				prisc.setUnitextension(rs.getString(20));
				prisc.setIpdtimeshow1(rs.getString(21));
				prisc.setDr_qty(rs.getString(22));
				prisc.setPriscqty(rs.getString(23));
				prisc.setMasterdose(rs.getString(24));
				prisc.setDddose(rs.getString(25));
				prisc.setId(rs.getInt(26));
				list.add(prisc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int insertClientPriscription(int parentpriscid, Priscription priscription2, int id, String docid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_client_priscription(clientid,practitionerid,conditionid,categoryid,mdicineid,mdicinename,dose,frequency,days,lastmodified,code,type,total,parentid,priscdurationtype,notes,unit,sqno,prisctimeid,prisctimename,priscremark,unitextension,ipdtimeshow,dr_qty,nurse_qty,masterdose,dddose) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, ""+id);
			preparedStatement.setString(2, docid);
			preparedStatement.setString(3, priscription2.getConditionid());
			preparedStatement.setString(4, priscription2.getCategoryid());
			preparedStatement.setString(5, priscription2.getMedicineid());
			preparedStatement.setString(6, priscription2.getMdicinenametxt());
			preparedStatement.setString(7, priscription2.getPriscdose());
			preparedStatement.setString(8, priscription2.getPriscfreq());
			preparedStatement.setString(9, priscription2.getPriscdays());
			preparedStatement.setString(10, priscription2.getLastmodified());
			preparedStatement.setString(11, priscription2.getPrisccode());
			preparedStatement.setString(12, priscription2.getPrisctype());
			preparedStatement.setString(13, priscription2.getPrisctotal());
			preparedStatement.setInt(14, parentpriscid);
			preparedStatement.setString(15, priscription2.getPriscdurationtype());
			preparedStatement.setString(16, priscription2.getDosenotes());
			preparedStatement.setString(17, priscription2.getUnit());
			preparedStatement.setString(18, priscription2.getSrno());
			preparedStatement.setString(19, priscription2.getPriscriptiontime());
			preparedStatement.setString(20, priscription2.getPrisctimename());
			preparedStatement.setString(21, priscription2.getPriscindivisualremark());
			preparedStatement.setString(22, priscription2.getUnitextension());
			preparedStatement.setString(23, priscription2.getIpdtimeshow1());
			preparedStatement.setString(24, priscription2.getDr_qty());
			preparedStatement.setString(25, priscription2.getPriscqty());
			preparedStatement.setString(26, priscription2.getMasterdose());
			preparedStatement.setString(27, priscription2.getDddose());
			result = preparedStatement.executeUpdate();
			if(result == 1){
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if(resultSet.next()){
					result = resultSet.getInt(1);  
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int updateParentPrisc(Priscription priscription, OpdConsult opdConsult2, String opdipdtype, String dischargedate, String dbname) {
		int res =0;
		try {
			//and date like '%"+opdConsult2.getDate()+"%'
			 String admissiondate=opdConsult2.getDate().split(" ")[0];
			 //admissiondate=admissiondate+" 01:59:59";
			 dischargedate =dischargedate.split(" ")[0]+" 23:59:59";
			StringBuffer buffer=new StringBuffer();
			buffer.append("update "+dbname+".apm_parent_prisc set date='"+priscription.getLastmodified()+"'");
			if(opdipdtype=="0"){
			buffer.append(" where oldparentid='"+priscription.getId()+"'");
			}else{
				buffer.append(" where clientid='"+opdConsult2.getPatientid()+"' and date  between '"+admissiondate+"' and '"+dischargedate+"'");
			}
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public Vector<Priscription> getParentPrictablelist(OpdConsult opdConsult2, Priscription priscription, String opdipdtype, String dbname) {
		PreparedStatement pst=null;
		Vector<Priscription> list=new Vector<Priscription>();
		try {
			String sql="";
			if(opdipdtype=="0"){
			  sql="select  userid, date, remark, status, conditionid,default_location_new,directtransfer,id from "+dbname+".apm_parent_prisc where oldparentid='"+priscription.getId()+"'";
			}else{
			  sql="select  userid, date, remark, status, conditionid,default_location_new,directtransfer,id from "+dbname+".apm_parent_prisc where clientid='"+opdConsult2.getPatientid()+"' and oldparentid='"+priscription.getId()+"'";
			}
			 pst=connection.prepareStatement(sql);
		     ResultSet rs=pst.executeQuery();
		     while(rs.next()){
		    	 Priscription priscription2=new Priscription();
		    	 priscription2.setUserid(rs.getString(1));
		    	 priscription2.setDate(rs.getString(2));
		    	 priscription2.setRemark(rs.getString(3));
		    	 priscription2.setStatus(rs.getString(4));
		    	 priscription2.setConditionid(rs.getString(5));
		    	 priscription2.setDefault_location(rs.getString(6));
		    	 priscription2.setDirect_transfer(rs.getString(7));
		    	 priscription2.setId(rs.getInt(8));
		    	 list.add(priscription2);
		     }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int insertParentprisctable(int id, String docid, int parentpriscid, Priscription priscription, OpdConsult opdConsult2, String opdipdtype, String lastmodified) {
		int result =0;
		try {
			String sql = "insert into apm_parent_prisc(oldparentid, clientid, ipdid, userid, date, remark, status, conditionid,practid,default_location_new,directtransfer) values(?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, ""+parentpriscid);
			preparedStatement.setString(2, ""+id);
			if(opdipdtype=="0"){
			preparedStatement.setString(3, "0");
			}else{
			preparedStatement.setString(3, ""+opdConsult2.getIpdid());
			}
			preparedStatement.setString(4, "Demo");
			preparedStatement.setString(5, lastmodified);
			preparedStatement.setString(6, priscription.getRemark());
			preparedStatement.setString(7, "1");
			preparedStatement.setString(8, priscription.getConditionid());
			preparedStatement.setString(9, docid);
			preparedStatement.setString(10, priscription.getDefault_location());
			preparedStatement.setString(11, "1");
			result = preparedStatement.executeUpdate();
			
			if(result == 1){
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if(resultSet.next()){
					result = resultSet.getInt(1);  
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Vector<Priscription> getChildDataList(OpdConsult opdConsult2,Priscription priscription3, String dbname,int parpriscid1) {
		PreparedStatement pst=null;
		Vector<Priscription> list=new Vector<Priscription>();
		try {
			 String sql="select medicinename, medicineid, qty from "+dbname+".apm_child_prisc where parentid='"+parpriscid1+"' ";
			 pst=connection.prepareStatement(sql);
			 ResultSet rs=pst.executeQuery();
			 while(rs.next()){
				 Priscription priscription=new Priscription();
				 priscription.setMdicinenametxt(rs.getString(1));
				 priscription.setMedicineid(rs.getString(2));
				 priscription.setMedqty(rs.getString(3));
				 list.add(priscription);
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public int insertChildPriscTable(int parentpriscid, int parpriscid1, int childid, Priscription priscription4,
			int id) {
		int res=0;
		try {
			String sql = "insert into apm_child_prisc(parentid, oldchildid, oldparentid, clientid, medicinename, medicineid, qty) values(?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, ""+parpriscid1);
			preparedStatement.setString(2, ""+childid);
			preparedStatement.setString(3, ""+parentpriscid);
			preparedStatement.setString(4, ""+id);
			preparedStatement.setString(5, priscription4.getMdicinenametxt());
			preparedStatement.setString(6, ""+childid);
			preparedStatement.setString(7, priscription4.getDr_qty());
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}

	@Override
	public Vector<Investigation> getOrisesParentdateList(OpdConsult opdConsult2,String opdipdtype, String dbname,int fipdid) {
		PreparedStatement pst=null;
		
		Vector<Investigation>list=new Vector<Investigation>();
		String sql="";
		try {
			if(opdipdtype=="0") {
			 sql="select lastmodified,id from "+dbname+".apm_client_parent_investigation where clientid='"+opdConsult2.getPatientid()+"' and ipdid=0 and lastmodified between '"+opdConsult2.getDate()+"' and '"+opdConsult2.getDate()+" 23:59:59'";
			}else {
				
				 sql="select lastmodified,id from "+dbname+".apm_client_parent_investigation where clientid='"+opdConsult2.getPatientid()+"' and ipdid="+fipdid+"";

			}
		     pst=connection.prepareStatement(sql);
		     ResultSet rs=pst.executeQuery();
		     while(rs.next()){
		    	 Investigation inv=new Investigation();
		    	 inv.setDate(rs.getString(1));
		    	 inv.setId(rs.getInt(2));
		    	 list.add(inv);
		    			 
		     }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Vector<Client> getFakeData(String specialityid, String commencing, String ipdpatientcount,LoginInfo loginInfo) {
		PreparedStatement preparedStatement=null;
		Vector<Client>list=new Vector<Client>();
		String id="";
		StringBuffer buffer = new StringBuffer();
		buffer.append("select id,title,firstname,surname,mobno,email,gender,dob,address,town,country,");
		buffer.append("postcode,refrence,sourceofintro,third_party_id,third_party_name_id,occupation,expiryDate,");
		buffer.append("whopay,policyauthorzcode,policyno,knownAs,county,homeNo,workNo,emailCc,prefContactMode,");
		buffer.append("emergencyContName,emergencyContNo,patientType,middlename,oldClientId,gp_id,employer_name,");
		buffer.append("treatment_type,refered_date,policyExcess,lastModified,doctor_surgery_id,second_line_address,");
		buffer.append("source_of_intro_name,note,accountnote,clinicalnote,nhs,imgname,relation,adhno,tpmemb,middlename,");
		buffer.append("mbalance,fullname,hospitalborn,companyname, neiscardno, designation, relationofuser,");
		buffer.append("unit_station, claimid, colliery, areatp,maritalsts,policyholder,mothername,fathername,birthplace,");
		buffer.append("birthtime,document_name,document_data,relation,relativeNo,relativename,pincode,docType,profileImg,docImg,town_village,relativeImg,");
		buffer.append("patientcategory,lmh_department, apmtDate, drname, drnameId,admissionDate,dischargeDate,diagnosis from vspm.fake_patient ");
		buffer.append("where transfer=0 ");
		
		//for mbbs 
		if(loginInfo.isMbbs()) {
		   if(specialityid.equals("16")){
		      buffer.append(" and gender='female' ");
		  }
		}
		//for bams streerog
		if(loginInfo.isBams1()) {
			if(specialityid.equals("7")){
				buffer.append(" and gender='female' ");
			 }
		}
		
	if(loginInfo.isMbbs()) {	
		if(specialityid.equals("21")) {
			buffer=new StringBuffer();
			buffer.append("select id,title,firstname,surname,mobno,email,gender,dob,address,town,country,");
			buffer.append("postcode,refrence,sourceofintro,third_party_id,third_party_name_id,occupation,expiryDate,");
			buffer.append("whopay,policyauthorzcode,policyno,knownAs,county,homeNo,workNo,emailCc,prefContactMode,");
			buffer.append("emergencyContName,emergencyContNo,patientType,middlename,oldClientId,gp_id,employer_name,");
			buffer.append("treatment_type,refered_date,policyExcess,lastModified,doctor_surgery_id,second_line_address,");
			buffer.append("source_of_intro_name,note,accountnote,clinicalnote,nhs,imgname,relation,adhno,tpmemb,middlename,");
			buffer.append("mbalance,fullname,hospitalborn,companyname, neiscardno, designation, relationofuser,");
			buffer.append("unit_station, claimid, colliery, areatp,maritalsts,policyholder,mothername,fathername,birthplace,");
			buffer.append("birthtime,document_name,document_data,relation,relativeNo,relativename,pincode,docType,profileImg,docImg,town_village,relativeImg,");
			buffer.append("patientcategory,lmh_department, apmtDate, drname, drnameId,admissionDate,dischargeDate,diagnosis from vspm.fake_pedo ");
			buffer.append("where transfer=0 ");
		}
	}else {
		if(specialityid.equals("2")) {
			buffer=new StringBuffer();
			buffer.append("select id,title,firstname,surname,mobno,email,gender,dob,address,town,country,");
			buffer.append("postcode,refrence,sourceofintro,third_party_id,third_party_name_id,occupation,expiryDate,");
			buffer.append("whopay,policyauthorzcode,policyno,knownAs,county,homeNo,workNo,emailCc,prefContactMode,");
			buffer.append("emergencyContName,emergencyContNo,patientType,middlename,oldClientId,gp_id,employer_name,");
			buffer.append("treatment_type,refered_date,policyExcess,lastModified,doctor_surgery_id,second_line_address,");
			buffer.append("source_of_intro_name,note,accountnote,clinicalnote,nhs,imgname,relation,adhno,tpmemb,middlename,");
			buffer.append("mbalance,fullname,hospitalborn,companyname, neiscardno, designation, relationofuser,");
			buffer.append("unit_station, claimid, colliery, areatp,maritalsts,policyholder,mothername,fathername,birthplace,");
			buffer.append("birthtime,document_name,document_data,relation,relativeNo,relativename,pincode,docType,profileImg,docImg,town_village,relativeImg,");
			buffer.append("patientcategory,lmh_department, apmtDate, drname, drnameId,admissionDate,dischargeDate,diagnosis from vspm.fake_pedo ");
			buffer.append("where transfer=0 ");
		}
	}
		buffer.append("limit 0,"+ipdpatientcount+" ");
		try {
			preparedStatement = connection.prepareStatement(buffer.toString());
			//preparedStatement.setString(1, userSessionId);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Client client = new Client();
				if(id.equals("")){
					id=rs.getString(1);
				}else{
					id=id+","+rs.getString(1);
				}
				client.setId(rs.getInt(1));
				client.setTitle(rs.getString(2));
				client.setFirstName(rs.getString(3));
				client.setLastName(rs.getString(4));
				client.setMobNo(rs.getString(5));
				client.setEmail(rs.getString(6));
				client.setGender(rs.getString(7));
				client.setDob(rs.getString(8));
				client.setAddress(rs.getString(9));
				if(!client.getDob().equals("")) {
					String age=DateTimeUtils.getAgeyearmonthdays(rs.getString(8));
					client.setYear(age.split("~~")[0]);
					client.setMonth(age.split("~~")[1]);
					client.setDays(age.split("~~")[2]);
				}
				
				
				//String city = rs.getString(10);
				client.setCity(rs.getString(10));
			    client.setTown(rs.getString(10));
				
			    String citynew = rs.getString(10);
			    citynew = citynew.charAt(0) + citynew.substring(1).toLowerCase();
			    client.setCity(citynew);
				
				client.setCountry(rs.getString(11));
				client.setPostCode(rs.getString(12));
				client.setReference(rs.getString(13));
				client.setSourceOfIntro(rs.getString(14));
				client.setType(rs.getString(15));
				client.setTypeName(rs.getString(16));
				client.setOccupation(rs.getString(17));
				client.setExpiryDate(rs.getString(18));
				client.setWhopay(rs.getString(19));
				client.setPolicyAuthorzCode(rs.getString(20));
				client.setPolicyNo(rs.getString(21));
				client.setKnownAs(rs.getString(22));
				
				client.setCounty(rs.getString(23));
				
				String statelowercase = rs.getString(23);
				statelowercase = statelowercase.charAt(0) + statelowercase.substring(1).toLowerCase();
				client.setCounty(statelowercase);
				
				
				client.setState(rs.getString(23));
				client.setHomeNo(rs.getString(24));
				client.setWorkNo(rs.getString(25));
				client.setEmailCc(rs.getString(26));
				client.setPrefContactMode(rs.getString(27));
				client.setEmergencyContName(rs.getString(28));
				client.setEmergencyContNo(rs.getString(29));
				client.setPatientType(rs.getString(30));
				client.setMiddlename(rs.getString(31));
				client.setOldclientId(rs.getString(32));
				String gpname = rs.getString(33);
				client.setGpname(gpname);
				client.setEmployerName(rs.getString(34));
				client.setTreatmentType(rs.getString(35));
				client.setReferedDate(rs.getString(36));
				client.setPolicyExcess(rs.getString(37));
				client.setLastModified(rs.getString(38));
				client.setGptypeName(rs.getString(39));
				
				if(rs.getString(40)==null){
					client.setSecondLineaddress("");
				}else{
					client.setSecondLineaddress(rs.getString(40));
				}
				
				client.setSourceOfIntroName(rs.getString(41));
				client.setClientNote(rs.getString(42));
				client.setAccountNote(rs.getString(43));
				client.setClinicalNote(rs.getString(44));
				client.setNhsNumber(rs.getString(45));
				client.setImageName(rs.getString(46));
				client.setRelation(rs.getString(47));
				client.setAdhno(rs.getString(48));
				client.setTpmemb(rs.getString(49));
				client.setMiddlename(rs.getString(50));
				client.setBalance(rs.getString(51));
				//client.setAbrivationid(rs.getString(52));
				client.setFullname(rs.getString(52));
				client.setHospitalborn(String.valueOf(rs.getInt(53)));
				//  06/12/2018
				client.setCompname(rs.getString(54));
				client.setNeisno(rs.getString(55));
				client.setDesignationbytp(rs.getString(56));
				client.setRelationvbytpe(rs.getString(57));
				client.setUnitstation(rs.getString(58));
				client.setClaimbytp(rs.getString(59));
				client.setColliery(rs.getString(60));
				client.setAreabytp(rs.getString(61));
				client.setMaritalsts(rs.getString(62));
				client.setPolicyholder(rs.getString(63));
				client.setMothername(rs.getString(64));
				client.setFathername(rs.getString(65));
				client.setBirthplace(rs.getString(66));
				client.setBirthtime(rs.getString(67));
				client.setDocumentID(DateTimeUtils.isNull(rs.getString(68)));
				client.setDocumentValue(DateTimeUtils.isNull(rs.getString(69)));
				client.setAge1(DateTimeUtils.getAge1(rs.getString(8)));
				
				client.setRelation(rs.getString(70));
				client.setRelativeno(rs.getString(71));
				client.setRelativename(rs.getString(72));
				client.setPincode(rs.getString(73));
				client.setDocType(rs.getString(74));
				client.setProfileImg(rs.getString(75));
				client.setDocImg(rs.getString(76));
				client.setTown_village(rs.getString(77));
				client.setRelativeImg(rs.getString(78));
				client.setPatientcategory(rs.getString(79));
				client.setLmh_department(rs.getString(80));
				
				client.setApmtDate(rs.getString(81));
				client.setDrname(rs.getString(82));
				client.setDrnameId(rs.getString(83));
				//admissionDate,dischargeDate,diagnosis
				client.setAdmissiondate(rs.getString(84));
				client.setDischargedate(rs.getString(85));
				client.setDiagnosis(rs.getString(86));
				client.setSelectedids(id);
				
				//int res = updateFakePatient(rs.getInt(1),date,userSessionId);
			if(loginInfo.isMbbs()) {	
				if(specialityid.equals("16")){
					int ageyear=Integer.parseInt(client.getYear());
					if(ageyear>=25 && ageyear<=55){
						list.add(client);
					}
				}else if(specialityid.equals("21")){
					int ageyear=Integer.parseInt(client.getYear());
					if(ageyear<=14){
						list.add(client);
					}
				}
				else{
				list.add(client);
				}
			}else {
				
				if(specialityid.equals("7")){
					/*
					 * int ageyear=Integer.parseInt(client.getYear()); if(ageyear>=25 &&
					 * ageyear<=55){ list.add(client); }
					 */
					list.add(client);
				}else if(specialityid.equals("2")){
					int ageyear=Integer.parseInt(client.getYear());
					if(ageyear<=14){
						list.add(client);
					}
				}
				else{
				list.add(client);
				}
				
				
			 }
				
		   }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public String getadischrgedate(String atreatmentepid, String dbname) {

		PreparedStatement preparedStatement = null;
		String adisdate="";
		
		try {
			
			String sql="select dischargedate from "+dbname+".apm_treatment_episode where id="+atreatmentepid+" ";
			preparedStatement=connection.prepareStatement(sql);
			ResultSet rs=preparedStatement.executeQuery();
			
            while(rs.next()){
				
				adisdate=rs.getString(1);
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return adisdate;
	}

	@Override
	public OpdConsult getOrisesAvailableslot1(String docid, String specialityid,int flag,String dbname) {
		PreparedStatement preparedStatement=null;
		OpdConsult opdConsult=new OpdConsult();
		try {
			StringBuffer sql=new StringBuffer();
			if(flag==1) {
			sql.append("select apm_available_slot.clientId,apm_available_slot.id,commencing from "+dbname+".apm_available_slot inner join "+dbname+".apm_user on apm_user.id=apm_available_slot.diaryuserid"
					+ " where discription='"+specialityid+"' and apm_available_slot.taken!=1 and ipdno=0 limit 0,1");
			}else {
			sql.append(" select apm_available_slot.clientId,apm_available_slot.id,commencing from "+dbname+".apm_available_slot inner join "+dbname+".apm_user on apm_user.id=apm_available_slot.diaryuserid"
						+ " inner join "+dbname+".apm_client_parent_priscription on apm_available_slot.clientId=apm_client_parent_priscription.clientid"
						+ " where discription='"+specialityid+"' and apm_available_slot.taken!=1 and apm_client_parent_priscription.billno!=0 and ipdno=0  limit 0,1");
               if (dbname.equals("bams1")){
            	   sql=new StringBuffer();
            	   sql.append(" select apm_available_slot.clientId,apm_available_slot.id,commencing from "+dbname+".apm_available_slot inner join "+dbname+".apm_user on apm_user.id=apm_available_slot.diaryuserid"
   						+ " inner join "+dbname+".apm_client_parent_priscription on apm_available_slot.clientId=apm_client_parent_priscription.clientid"
   						+ " where discription='"+specialityid+"' and apm_available_slot.taken!=1 and ipdno=0 limit 0,1");  
				 
			   }if( dbname.equals("jupiter1")) {
				   sql=new StringBuffer();
            	   sql.append(" select apm_available_slot.clientId,apm_available_slot.id,commencing from "+dbname+".apm_available_slot inner join "+dbname+".apm_user on apm_user.id=apm_available_slot.diaryuserid"
   						 + " where discription='"+specialityid+"' and apm_available_slot.taken!=1 and ipdno=0 limit 0,1");  
				 
			   }
			}
			preparedStatement=connection.prepareStatement(sql.toString());
			ResultSet rs=preparedStatement.executeQuery();
			while(rs.next()){
				opdConsult.setPatientid(rs.getInt(1));
				opdConsult.setAppointmentid(rs.getInt(2));
				//opdConsult.setDate(rs.getString(3));
				opdConsult.setIpdadmissiondte(rs.getString(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return opdConsult;
	}

	@Override
	public Vector<OpdConsult> getoriesAvailableslotlistbyid1(int patientid, String ipdadmissiondte, String dbname, boolean isfollowup) {
		PreparedStatement pst=null;
		Vector<OpdConsult>list=new Vector<OpdConsult>();
		String sql="";
		try {
			if(isfollowup) {
		     sql="select apm_available_slot.clientId,apm_available_slot.id,commencing  from "+dbname+".apm_available_slot inner join "+dbname+".apm_user on apm_user.id=apm_available_slot.diaryuserid "
							+ " where apm_available_slot.clientId='"+patientid+"' group by commencing ";	
			}else {
			 sql="select apm_available_slot.clientId,apm_available_slot.id,commencing  from "+dbname+".apm_available_slot inner join "+dbname+".apm_user on apm_user.id=apm_available_slot.diaryuserid "
					+ " where apm_available_slot.clientId='"+patientid+"' group by commencing limit 0,1";
			}
			pst=connection.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				OpdConsult opdConsult=new OpdConsult();
				
				opdConsult.setPatientid(rs.getInt(1));
				opdConsult.setAppointmentid(rs.getInt(2));
				opdConsult.setDate(rs.getString(3));
				list.add(opdConsult);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Vector<OpdConsult> getOriesOtlist(String auadmissionid,String dbname) {
		PreparedStatement pst=null;
		Vector<OpdConsult>list=new Vector<OpdConsult>();
		try {
			String sql="select commencing,clientId,id from "+dbname+".apm_available_slot where ipdno="+auadmissionid+"" ;
			pst=connection.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				OpdConsult opdConsult=new OpdConsult();
				opdConsult.setCommencing(rs.getString(1));
				opdConsult.setPclientid(rs.getInt(2));
				opdConsult.setId(rs.getInt(3));
				list.add(opdConsult);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int updateOriesOtdata(int pclientid, String cdocid, String cclientid, String cotdate,int id,String dbname) {
		int res =0;
		try {
			   String sql="update "+dbname+".apm_available_slot set surgeon='"+cdocid+"'  where id="+id+"";			
			   PreparedStatement preparedStatement = connection.prepareStatement(sql);
			   res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public Vector<OpdConsult> getOriesOtavailableList(String cclientid,int id,String dbname,String cotdate) {
		PreparedStatement pst=null;
		Vector<OpdConsult>list=new Vector<OpdConsult>();
		try {
			String sql="select  apmslotid, commencing, starttime, endtime, diaryusername, location, room, aptmtype, duration, clientId,"
					+ "added_by, apmttypetext, usedsession, condition_id, whopay, otid, category, procedures, surgeon, anesthesia, wardid, "
					+ " otnotes, otimagecount, discharge_procedure ,diaryuserid from "+dbname+".apm_available_slot where id="+id+" and ipdno!=0 "  ;
			pst=connection.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				OpdConsult opdConsult=new OpdConsult();
				opdConsult.setApmslotid(rs.getString(1));
				opdConsult.setCommencing(cotdate);
				opdConsult.setSlottime(rs.getString(3));
				opdConsult.setEndtime(rs.getString(4));
				opdConsult.setDiaryusername(rs.getString(5));
				opdConsult.setLocation(rs.getString(6));
				opdConsult.setRoom(rs.getString(7));
				opdConsult.setApmtttype(rs.getString(8));
				opdConsult.setDuration(rs.getString(9));
				opdConsult.setPatientid(Integer.parseInt(cclientid));
				opdConsult.setAdded_by(rs.getString(11));
				opdConsult.setApmttypetext(rs.getString(12));
				opdConsult.setUsedsession(rs.getString(13));
				opdConsult.setCondition_id(rs.getInt(14));
				opdConsult.setPayby(rs.getString(15));
				opdConsult.setOtid(rs.getString(16));
				opdConsult.setCategory(rs.getString(17));
				opdConsult.setProcedures(rs.getString(18));
				opdConsult.setSurgeon(rs.getString(19));
				opdConsult.setAnesthesia(rs.getString(20));
				opdConsult.setWardid(rs.getString(21));
				opdConsult.setOtnotes(rs.getString(22));
				opdConsult.setOtimagecount(rs.getString(23));
				opdConsult.setDischarge_procedure(rs.getString(24));
				opdConsult.setDiaryuserid(rs.getString(25));
				list.add(opdConsult);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int insertOtAvailabledata(String cclientid, OpdConsult opdConsult2, String cadmissionid,String cdiaryuserid,String patientnm) {
		int result=0;
		int id = 0;
		PreparedStatement pstm=null;
		try {
			String sql = "insert into apm_available_slot(apmslotid,commencing,starttime,endtime,"
					+ "notes,diaryuserid,diaryusername,dept,location,room,clientname,aptmtype,"
					+ "duration,clientId,charge,treatmentEpisodeId,added_by,apmttypetext,usedsession,"
					+ "condition_id,whopay,otid,category,procedures,surgeon,anesthesia,ipdno,wardid,"
					+ "anidoctorfees,psurcharge,panetcharge,sic,assistaffcharge,opdbooktime,reqdatetime,"
					+ "mobstatus,speciality,appt_type,opdabbrevationid,refferedfrom,isPreDate,opdSequnce,deptOpdId, otnotes, otimagecount, discharge_procedure) "
					+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
					+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstm = connection.prepareStatement(sql);
			pstm.setString(1, opdConsult2.getApmslotid());
			pstm.setString(2,opdConsult2.getCommencing());
			pstm.setString(3, opdConsult2.getSlottime());
			pstm.setString(4, opdConsult2.getEndtime());
			pstm.setString(5, opdConsult2.getNotes());
			pstm.setString(6, cdiaryuserid);
			pstm.setString(7, opdConsult2.getDiaryusername());
			pstm.setString(8, "");
			pstm.setString(9, "1");
			pstm.setString(10, "Room1");
			pstm.setString(11, patientnm);
			pstm.setString(12, "1");
			pstm.setString(13, opdConsult2.getDuration());
			pstm.setInt(14, opdConsult2.getPatientid());
			pstm.setInt(15, 0);
			
			
			pstm.setString(16, "");
			pstm.setString(17, opdConsult2.getAdded_by());
			
			pstm.setString(18, opdConsult2.getApmttypetext());
			pstm.setString(19, opdConsult2.getUsedsession());
			pstm.setInt(20, opdConsult2.getCondition_id());
			pstm.setString(21, "Client");
			pstm.setString(22, opdConsult2.getOtid());
			pstm.setString(23, opdConsult2.getCategory());
			pstm.setString(24, DateTimeUtils.numberCheck(opdConsult2.getProcedures()));
			pstm.setString(25, DateTimeUtils.numberCheck(opdConsult2.getSurgeon()));
			pstm.setString(26, DateTimeUtils.numberCheck(opdConsult2.getAnesthesia()));
			pstm.setString(27, DateTimeUtils.numberCheck(cadmissionid));
			pstm.setString(28, DateTimeUtils.numberCheck(opdConsult2.getWardid()));
			
			pstm.setString(29, "");
			pstm.setString(30,"");
			pstm.setString(31, "");
			pstm.setString(32, "");
			pstm.setString(33,"");
			pstm.setString(34, "");
			
			
			pstm.setString(35, "");
			pstm.setInt(36, 0);
			pstm.setInt(37, 0);
			pstm.setInt(38, 0);
			String newopdabbr="";
			String opdSeqAbbr="";
			boolean flag = true;
			/*if(opdConsult2.getOtplan().equals("0") && !DateTimeUtils.isNull(apmtTYpeText).equals("")){
				newopdabbr=generateOPDSequenceNewFormat();
				opdSeqAbbr = generateLMHOPDSeq(opdConsult2.getCommencing());
				flag = false;
			}
			if(opdConsult2.getOtplan().equals("0") && flag){
				opdSeqAbbr = generateLMHOPDSeq(opdConsult2.getCommencing());
			}*/
			pstm.setString(39, newopdabbr);
			pstm.setString(40, "");
			pstm.setString(41, "");
			pstm.setString(42, opdSeqAbbr);
			pstm.setString(43, "");
			pstm.setString(44, opdConsult2.getOtnotes());
			pstm.setString(45, opdConsult2.getOtimagecount());
			pstm.setString(46, opdConsult2.getDischarge_procedure());
			result = pstm.executeUpdate();
			
			if(result == 1){
				ResultSet resultSet = pstm.getGeneratedKeys();
				if(resultSet.next()){
					id = resultSet.getInt(1);  
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public String getOriesuserlastname(String diaryuserid,String dbname) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select lastname  from "+dbname+".apm_user where id = "+diaryuserid+"";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result =getdiaryuserid (rs.getString(1));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private String getdiaryuserid(String string) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select id from apm_user where lastname='"+string+"'";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result =rs.getString(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public OpdConsult getDiagnosisdata(String specialityid, String dbname) {
		PreparedStatement preparedStatement = null;
		OpdConsult opd=new OpdConsult();
		String sql = "select id,name from "+dbname+".apm_diagnosis where department='"+specialityid+"' and taken=0 limit 0,1";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				opd.setId(rs.getInt(1));
				opd.setDiagnosisname(rs.getString(2));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return opd;
	}

	@Override
	public int updateTaken(int id, String dbname) {
		PreparedStatement pst=null;
		int result=0;
		try {
			String sql="update "+dbname+".apm_diagnosis set taken=1 where id="+id+"";
			pst=connection.prepareStatement(sql);
			result=pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int saveOpddiagnosis(OpdConsult opd, int newpatientid, String specialityid) {
		PreparedStatement pst=null;
		int result=0;
		try {
			String sql="insert into opd_diagnosis(clientid,diagnosis,department) values(?,?,?)";
			pst=connection.prepareStatement(sql);
			pst.setInt(1, newpatientid);
			pst.setString(2, opd.getDiagnosisname());
			pst.setString(3, specialityid);
			result=pst.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean checkPatientexist(int newpatientid, String specialityid) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "SELECT * FROM opd_diagnosis where clientid = '"+newpatientid+"' and department='"+specialityid+"' ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = true;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String getDatabasenamebyclinicid(String clinicid) {
		PreparedStatement pst=null;
		String dbname="";
		try {
			String sql="select fakedb from admin.artificialdb where clinicid='"+clinicid+"'";
			pst=connection.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				dbname=rs.getString(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dbname;
	}

	@Override
	public OpdConsult getConsultationFormDatabyapmtid(OpdConsult opdConsult2,String dbname) {
		PreparedStatement pst=null;
		OpdConsult opd=new OpdConsult();
		try {
			String sql="select who_pay, user_id, date_time, chief_complains, investigation, discadvnotes, notes, update_date_time, medicine_text, examination_finding,"
					+ " ondiet, karma, procedurebams from "+dbname+".consultation_form where apmt_id='"+opdConsult2.getAppointmentid()+"' and client_id='"+opdConsult2.getPatientid()+"'";
			pst=connection.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				
				String conditionids=getconditionid(opdConsult2.getAppointmentid(),dbname);
				opd.setPayby(rs.getString(1));
				opd.setUserid(rs.getString(2));
				opd.setDate(rs.getString(3));
				opd.setChief_complains(rs.getString(4));
				opd.setInvestigation(rs.getString(5));
				opd.setDiscadvnotes(rs.getString(6));
				opd.setNotes(rs.getString(7));
				opd.setUpdate_date_time(rs.getString(8));
				opd.setMedicine_text(rs.getString(9));
				opd.setExamination_finding(rs.getString(10));
				opd.setOndiet(rs.getString(11));
				opd.setKarma(rs.getString(12));
				opd.setProcedurebams(rs.getString(13));
				opd.setCondi_id(conditionids);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return opd;
	}

	private String getconditionid(int appointmentid, String dbname ) {
		String conditionid="";
		PreparedStatement pst=null;
		try {
			// String sql = "Select conditionid from apm_final_diagnosis where ipdid=" +
			// ipdid + "";
			String sql = "Select conditionid from "+dbname+".consultant_notes_diagnosis where apmtid='"+appointmentid+"' ";
			pst=connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				conditionid=rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conditionid;
		
	}

	@Override
	public int insertinConsult_form(OpdConsult consultform, int opdid, int id) {
		PreparedStatement pst=null;
		int result=0;
		try {
			String sql="insert into  consultation_form(client_id, who_pay, apmt_id, user_id, date_time, chief_complains, investigation,"
					+ " discadvnotes, notes,update_date_time, medicine_text, examination_finding, ondiet, karma, procedurebams)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pst=connection.prepareStatement(sql);
			pst.setInt(1, id);	
			pst.setString(2, consultform.getPayby());
			pst.setInt(3, opdid);
			pst.setString(4, consultform.getUserid());
			pst.setString(5, consultform.getDate());
			pst.setString(6, consultform.getChief_complains());
			pst.setString(7, consultform.getInvestigation());
			pst.setString(8, consultform.getDiscadvnotes());
			pst.setString(9, consultform.getNotes());
			pst.setString(10, consultform.getUpdate_date_time());
			pst.setString(11, consultform.getMedicine_text());
			pst.setString(12, consultform.getExamination_finding());
		    pst.setString(13, consultform.getOndiet());
			pst.setString(14, consultform.getKarma());
			pst.setString(15, consultform.getProcedurebams());
			result=pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int insertBamsConditionids(int opdid, String str) {
		PreparedStatement pst=null;
		int result=0;
		try {
			String sql="insert into consultant_notes_diagnosis(conditionid,apmtid)values(?,?)";
			pst=connection.prepareStatement(sql);
			pst.setString(1, str);	
			pst.setInt(2, opdid);
			
			result=pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String generateLMHIPDSeqForMBBS(String commencing) {
		String abrivationid = "";
		try {

			int seqno = checkifSequenceExistLMH(commencing);
			String clinicabrivation = "IP";

			String tempd[] = commencing.split("-");
			String y = tempd[0];
			String m = tempd[1];
			String d = tempd[2];
			String newseq = "";
			int yr = Integer.parseInt(y) % 1000;
			yr = Integer.parseInt(y);
			if (String.valueOf(seqno).length() == 1) {
				newseq = "000" + seqno;
			} else if (String.valueOf(seqno).length() == 2) {
				newseq = "00" + seqno;
			} else if (String.valueOf(seqno).length() == 3) {
				newseq = "0" + seqno;
			} else {
				newseq = "" + seqno;
			}
			abrivationid = clinicabrivation + yr + m + d + "" + newseq;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return abrivationid;
		
	}
	
	
	}
