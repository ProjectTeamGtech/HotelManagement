package com.apm.Appointment.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.struts2.components.Head;

import com.apm.Accounts.eu.bi.ChargesAccountProcessingDAO;
import com.apm.Accounts.eu.blogic.jdbc.JDBCChargeAccountProcesDAO;
import com.apm.Accounts.eu.entity.Accounts;
import com.apm.Appointment.eu.bi.AppointmentDAO;
import com.apm.Appointment.eu.bi.AppointmentTypeDAO;
import com.apm.Appointment.eu.entity.Appointment;
import com.apm.Appointment.eu.entity.AppointmentType;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.NotAvailableSlot;
import com.apm.Emr.eu.bi.InvestigationDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCInvestigationDAO;
import com.apm.Ipd.eu.bi.IpdDAO;
import com.apm.Ipd.eu.blogic.jdbc.JDBCIpdDAO;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.SittingFollowup;
import com.apm.Report.eu.entity.Commission;
import com.apm.ThirdParties.eu.bi.ThirdPartyDAO;
import com.apm.ThirdParties.eu.blogic.jdbc.JDBCThirdPartyDAO;
import com.apm.ThirdParties.eu.entity.ThirdParty;
import com.apm.common.eu.blogic.jdbc.JDBCBaseDAO;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.common.helper.LoginInfo;
import com.apm.main.common.constants.Constants;
import com.semika.cometd.ChatService;

public class JDBCAppointmentTypeDAO extends JDBCBaseDAO implements AppointmentTypeDAO{
	
	public JDBCAppointmentTypeDAO(Connection connection){
		this.connection = connection;
		
	}

	public ArrayList<AppointmentType> getAppointmentTypeList(Pagination pagination,String searchtext,String payby,String thirdparty,String ward,String chargeType,String viewaccess,String orderby, String order) {
		PreparedStatement preparedStatement = null;
		ArrayList<AppointmentType>list = new ArrayList<AppointmentType>();
		StringBuffer sql  = new StringBuffer();
		sql.append("SELECT apm_appointment_type.id,name,description,category,duration,charges,location,chargeType,reportField,tpid,code,basecharge,apm_ipd_ward.wardname,noneditamt FROM apm_appointment_type ");
		sql.append("left join apm_ipd_ward on apm_ipd_ward.id=apm_appointment_type.wardid ");
		if(payby==null || payby.equals("")){
					 sql.append("where name like('"+searchtext+"%') ");
					 if(!viewaccess.equals("")){
							if(viewaccess.equals("1")){
								sql.append("and otchargetype=0 ");
							}
						}
					 if(ward!=null){
						 if(!ward.equals("")){
							 if(!ward.equals("0")){
							 sql.append("and wardid='"+ward+"'" );
						 }
						 }
					 }
					 if(chargeType!=null){
						 if(!chargeType.equals("")){
							 if(!chargeType.equals("0")){
							 sql.append("and chargeType='"+chargeType+"'" );
						 }
						 }
				}
		}else{
			
			if(thirdparty.equals("0")){
			
					
					if(payby.equals(Constants.PAY_BY_CLIENT)){
						 sql.append("where name like('"+searchtext+"%') and tpid=0 ");
						 if(!viewaccess.equals("")){
								if(viewaccess.equals(1)){
									sql.append("and otchargetype=0 ");
								}
							}
						 if(ward!=null){
							 
							 if(!ward.equals("")){
								 if(!ward.equals("0")){
							 }
								 sql.append("and wardid='"+ward+"'" );
							 }
						 }
						 if(chargeType!=null){
							 if(!chargeType.equals("")){
								 if(!chargeType.equals("0")){
								 sql.append("and chargeType='"+chargeType+"'" );
							 }
							 }
						 }
					}else if(payby.equals(Constants.PAY_BY_THIRD_PARTY)){
						 sql.append("where name like('"+searchtext+"%') and tpid!=0 ");
						 if(!viewaccess.equals("")){
								if(viewaccess.equals("1")){
									sql.append("and otchargetype=0 ");
								}
							}
						 if(ward!=null){
							 if(!ward.equals("")){
								 if(!ward.equals("0")){
								 sql.append("and wardid='"+ward+"'" );
							 }
							 }
						 }
						 if(chargeType!=null){
							 if(!chargeType.equals("")){
								 if(!chargeType.equals("0")){
								 sql.append("and chargeType='"+chargeType+"'" );
							 }
							 }
					}
					}else{
						sql.append("where name like('"+searchtext+"%') ");
						if(!viewaccess.equals("")){
							if(viewaccess.equals("1")){
								sql.append("and otchargetype=0 ");
							}
						}
						if(ward!=null){
							 if(!ward.equals("")){
								 if(!ward.equals("0")){
								 sql.append("and wardid='"+ward+"'" );
							 }
							 }
						 }
						if(chargeType!=null){
							 if(!chargeType.equals("")){
								 if(!chargeType.equals("0")){
								 sql.append("and chargeType='"+chargeType+"'" );
							 }
							 }
					}
					
					
				}
			}else{
				sql.append(" where name like('"+searchtext+"%') and tpid="+thirdparty+" ");
				if(!viewaccess.equals("")){
					if(viewaccess.equals("1")){
						sql.append("and otchargetype=0 ");
					}
				}
				if(ward!=null){
					 if(!ward.equals("")){
						 if(!ward.equals("0")){
						 sql.append("and wardid='"+ward+"'" );
					 }
					 }
				 }
				if(chargeType!=null){
					 if(!chargeType.equals("")){
						 if(!chargeType.equals("0")){
						 sql.append("and chargeType='"+chargeType+"'" );
					 }
					 }
			}
			}
		}
			if(!orderby.equals("")&& !order.equals("")){
		sql.append(" order by "+orderby+" "+order+" ");
			}
		
		String sqls = pagination.getSQLQuery(sql.toString());
		try{
			preparedStatement = connection.prepareStatement(sqls);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				AppointmentType appointmentType = new AppointmentType();
				
				appointmentType.setId(rs.getInt(1));
				appointmentType.setName(rs.getString(2));
				appointmentType.setDescription(rs.getString(3));
				appointmentType.setCategory(rs.getString(4));
				appointmentType.setDuration(rs.getString(5));				
				appointmentType.setCharges(rs.getString(6));
				appointmentType.setLocation(rs.getString(7));
				appointmentType.setChargeType(rs.getString(8));
				appointmentType.setReportField(rs.getString(9));
			
				if(rs.getInt(10)!=0){
					ThirdPartyDAO thirdPartyDAO = new JDBCThirdPartyDAO(connection);
					ThirdParty thirdParty = thirdPartyDAO.getThirdPartyDetails(rs.getString(10));
					appointmentType.setTpName(thirdParty.getCompanyName());
					if(thirdParty.getChargecolumnname()==null){
						thirdParty.setChargecolumnname("charges");
					}if(thirdParty.getChargecolumnname().equals("")){
						thirdParty.setChargecolumnname("charges");
					}
					String charges=getChargebyid(rs.getInt(1),thirdParty.getChargecolumnname());
					appointmentType.setCharges(charges);
				}else{
					appointmentType.setTpName("Self");
				}
				appointmentType.setTpid(""+rs.getInt(10));
				appointmentType.setCode(rs.getString(11));
				appointmentType.setBasecharge(rs.getString(12));
				String wardname=rs.getString(13);
				if(rs.getString(13)==null){
					wardname="";
				}
				appointmentType.setWardname(wardname);
				if(rs.getInt(14)==1){
					appointmentType.setNoneditamt(1);
				}else{
					appointmentType.setNoneditamt(0);
				}
				list.add(appointmentType);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	

	public ArrayList<AppointmentType> getColorList() {
		PreparedStatement preparedStatement = null;
		ArrayList<AppointmentType>list = new ArrayList<AppointmentType>();
		String sql = "SELECT id,color FROM color  ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				AppointmentType appointmentType = new AppointmentType();
				
				appointmentType.setId(rs.getInt(1));
				appointmentType.setColor(rs.getString(2));
				
				list.add(appointmentType);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public int saveAppointmentType(AppointmentType appointmentType) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_appointment_type(name,description,category,duration,charges,location,chargeType,reportField,basecharge,shareablecharge,code,clinic_name_id,session) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, appointmentType.getName());
			preparedStatement.setString(2, appointmentType.getDescription());
			preparedStatement.setString(3, appointmentType.getCategory());
			preparedStatement.setString(4, appointmentType.getDuration());		
			preparedStatement.setString(5, appointmentType.getCharges());
			preparedStatement.setString(6, appointmentType.getLocation());
			if(appointmentType.getChargeType().equals("PATHLAB") || appointmentType.getChargeType().equals("RADIOLOGY")){
				appointmentType.setChargeType("INVESTIGATION");
			}
			preparedStatement.setString(7, appointmentType.getChargeType());
			preparedStatement.setString(8, appointmentType.getReportField());
			preparedStatement.setString(9, appointmentType.getBasecharge());
			preparedStatement.setString(10, appointmentType.getShareablecharge());
			preparedStatement.setString(11, appointmentType.getCode());
			preparedStatement.setString(12, appointmentType.getClinic_name_id());
			preparedStatement.setString(13, appointmentType.getSessionno());
			result = preparedStatement.executeUpdate();
			
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}

	public AppointmentType getAppointment(int id) {
		PreparedStatement preparedStatement = null;
		AppointmentType appointmentType = new AppointmentType();
		AppointmentType appointmentType1 = new AppointmentType();
		ThirdParty thirdParty=new ThirdParty();
		ThirdPartyDAO thirdPartyDAO=new JDBCThirdPartyDAO(connection);
		appointmentType1=getMasterCharge(String.valueOf(id));
		if(appointmentType1.getTpid()==null){
			appointmentType1.setTpid("0");
		}if(appointmentType1.getTpid().equals("")){
			appointmentType1.setTpid("0");
		}
		if(!appointmentType1.getTpid().equals("0")){
			thirdParty=thirdPartyDAO.getThirdPartyDetails(appointmentType1.getTpid());
		}
		if(thirdParty.getChargecolumnname()==null){
			thirdParty.setChargecolumnname("charges");
		}
		if(thirdParty.getChargecolumnname().equals("")){
			thirdParty.setChargecolumnname("charges");
		}
		String sql = "SELECT id,name,description,category,duration,"+thirdParty.getChargecolumnname()+",location,chargeType,reportField,tpid,chargeid,code,basecharge,shareablecharge FROM apm_appointment_type where id = "+id+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				appointmentType.setId(rs.getInt(1));
				appointmentType.setName(rs.getString(2));
				appointmentType.setDescription(rs.getString(3));
				appointmentType.setCategory(rs.getString(4));
				appointmentType.setDuration(rs.getString(5));				
				appointmentType.setCharges(rs.getString(6));
				appointmentType.setLocation(rs.getString(7));
				appointmentType.setChargeType(rs.getString(8));
				appointmentType.setReportField(rs.getString(9));
				appointmentType.setTpid(rs.getString(10));
				appointmentType.setChargeid(rs.getString(11));
				appointmentType.setCode(rs.getString(12));
				appointmentType.setBasecharge(rs.getString(13));
				appointmentType.setShareablecharge(rs.getString(14));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return appointmentType;
	}

	public int updateAppointmentType(AppointmentType appointmentType,int id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		if(appointmentType.getColumnchargename()==null){
			appointmentType.setColumnchargename("charges");
		}if(appointmentType.getColumnchargename().equals("")){
			appointmentType.setColumnchargename("charges");
		}
		String sql = "update apm_appointment_type set name = ?,description = ?,category = ?,duration = ?,"+appointmentType.getColumnchargename()+" = ?,location = ?,chargeType = ?,reportField = ?,basecharge=?, shareablecharge=?,code=? where id = "+id+"";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, appointmentType.getName());
			preparedStatement.setString(2, appointmentType.getDescription());
			preparedStatement.setString(3, appointmentType.getCategory());
			preparedStatement.setString(4, appointmentType.getDuration());
			preparedStatement.setString(5, appointmentType.getCharges());
			preparedStatement.setString(6, appointmentType.getLocation());
			if(appointmentType.getChargeType().equals("PATHLAB")|| appointmentType.getChargeType().equals("RADIOLOGY")){
				appointmentType.setChargeType("INVESTIGATION");
			}
			preparedStatement.setString(7, appointmentType.getChargeType());
			preparedStatement.setString(8, appointmentType.getReportField());
			preparedStatement.setString(9, appointmentType.getBasecharge());
			preparedStatement.setString(10, appointmentType.getShareablecharge());
			preparedStatement.setString(11, appointmentType.getCode());
			result = preparedStatement.executeUpdate();
			
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}

	public int deleteAppoitmentType(int id, AppointmentType appointmentType) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from apm_appointment_type where id = "+id+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	
	public int getTotalApmtTypeCount(String searchtext,String payby,String thirdparty,String ward,String chargeType,String viewaccess,String orderby, String order) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		StringBuffer sql=new StringBuffer();
		if(payby==null || payby.equals("")){
			sql.append("select count(*) from apm_appointment_type where name like('"+searchtext+"%') ");
			if(!viewaccess.equals("")){
				if(viewaccess.equals("1")){
					sql.append("and otchargetype=0 ");
				}
			}
			if(ward!=null){
				 if(!ward.equals("")){
					 if(!ward.equals("0")){
					 sql.append("and wardid='"+ward+"'" );
				 }
				 }
			 }
			 if(chargeType!=null){
				 if(!chargeType.equals("")){
					 if(!chargeType.equals("0")){
					 sql.append("and chargeType='"+chargeType+"'" );
				 }
				 }
		}
		}else{
			if(thirdparty.equals("0")){
				if(payby.equals(Constants.PAY_BY_CLIENT)){
					sql.append("select count(*) from apm_appointment_type where name like('"+searchtext+"%') and tpid=0 ");
					if(!viewaccess.equals("")){
						if(viewaccess.equals("1")){
							sql.append("and otchargetype=0 ");
						}
					}
					if(ward!=null){
						 if(!ward.equals("")){
							 if(!ward.equals("0")){
							 sql.append("and wardid='"+ward+"'" );
						 }
						 }
					 }
					 if(chargeType!=null){
						 if(!chargeType.equals("")){
							 if(!chargeType.equals("0")){
							 sql.append("and chargeType='"+chargeType+"'" );
						 }
						 }
				}
				}else if(payby.equals(Constants.PAY_BY_THIRD_PARTY)){
					sql.append("select count(*) from apm_appointment_type where name like('"+searchtext+"%') and tpid!=0 ");
					if(!viewaccess.equals("")){
						if(viewaccess.equals("1")){
							sql.append("and otchargetype=0 ");
						}
					}
					if(ward!=null){
						 if(!ward.equals("")){
							 if(!ward.equals("0")){
							 sql.append("and wardid='"+ward+"'" );
						 }
						 }
					 }
					 if(chargeType!=null){
						 if(!chargeType.equals("")){
							 if(!chargeType.equals("0")){
							 sql.append("and chargeType='"+chargeType+"'" );
						 }
						 }
				}
				}else{
					sql.append("select count(*) from apm_appointment_type where name like('"+searchtext+"%') ");
					if(!viewaccess.equals("")){
						if(viewaccess.equals("1")){
							sql.append("and otchargetype=0 ");
						}
					}
					if(ward!=null){
						 if(!ward.equals("")){
							 if(!ward.equals("0")){
							 sql.append("and wardid='"+ward+"'" );
						 }
						 }
					 }
					 if(chargeType!=null){
						 if(!chargeType.equals("")){
							 if(!chargeType.equals("0")){
							 sql.append("and chargeType='"+chargeType+"'" );
						 }
						 }
				}
				}
			}else{
				sql.append("select count(*) from apm_appointment_type where name like('"+searchtext+"%') and tpid="+thirdparty+" ");
				if(!viewaccess.equals("")){
					if(viewaccess.equals("1")){
						sql.append("and otchargetype=0 ");
					}
				}
				if(ward!=null){
					 if(!ward.equals("")){
						 if(!ward.equals("0")){
						 sql.append("and wardid='"+ward+"'" );
					 }
					 }
				 }
				 if(chargeType!=null){
					 if(!chargeType.equals("")){
						 if(!chargeType.equals("0")){
						 sql.append("and chargeType='"+chargeType+"'" );
					 }
					 }
			}
			}
			
		}
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getInt(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean isApmtTypeExist(String apmtType) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "select name from apm_appointment_type where name = '"+apmtType+"'";
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = true;
			}
		}catch (Exception e) {
			
		}
		
		
		return result;
	}

	public ArrayList<AppointmentType> gettpAppointmentTypeList(
			String selectedid, String whopay,boolean isot,String otprocedureplaned,LoginInfo loginInfo,String docid,long newdiffer) {
		
		PreparedStatement preparedStatement = null;
		ArrayList<AppointmentType>list = new ArrayList<AppointmentType>();
		
		String sql = "";
		if(whopay.equals(Constants.PAY_BY_CLIENT)){
			if(loginInfo.isMarkhosp()) {
				if(newdiffer<=7) {
				   sql = "SELECT duration,name,id,charges FROM apm_appointment_type where tpid = 0 and chargeType = 'Appointment Charge' and docid='"+docid+"' or  id in (50,3128)  order by name ";
	
				}else {
			       sql = "SELECT duration,name,id,charges FROM apm_appointment_type where tpid = 0 and chargeType = 'Appointment Charge' and docid='"+docid+"' or id='50' order by name ";
				}
			}else {
			
			    sql = "SELECT duration,name,id,charges FROM apm_appointment_type where tpid = 0 and chargeType = 'Appointment Charge' order by name ";
			}
			if(isot){
				sql = "SELECT duration,name,id,charges FROM apm_appointment_type where tpid = 0 and chargeType = '"+otprocedureplaned+"' and otchargetype=1 order by name ";
			}
		}else{
			
			
			if(!checkifMainTp(selectedid)){
				 
				String temptpid= getFollowerTp(selectedid); 
				if(temptpid!=null){
					
					if(!temptpid.equals("0")){
						selectedid=temptpid;  
					}
				}
				
			}
			
			if(loginInfo.isBalgopal()) {
				
			  sql = "SELECT duration,name,id,charges FROM apm_appointment_type where tpid ='86' and chargeType = 'Appointment Charge'  order by name";

				
			}else {
			  sql = "SELECT duration,name,id,charges FROM apm_appointment_type where tpid ="+selectedid+" and chargeType = 'Appointment Charge'  order by name";
			
			}
			  if(isot){
				sql = "SELECT duration,name,id,charges FROM apm_appointment_type where tpid ="+selectedid+" and chargeType = '"+otprocedureplaned+"' and otchargetype=1  order by name";
			}
		}
		
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				AppointmentType appointmentType = new AppointmentType();
				appointmentType.setDuration(rs.getString(1));
				String charge = rs.getString(4);
				appointmentType.setName(rs.getString(2));
				appointmentType.setId(rs.getInt(3));
				list.add(appointmentType);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	public String getTpCompanyName(String clientId) {
		PreparedStatement preparedStatement  = null;
		String result = "";
		StringBuffer sql = new StringBuffer();
		
		sql.append("select company_name from apm_third_party_details inner join apm_patient ");
		sql.append("on apm_third_party_details.id = apm_patient.third_party_name_id where apm_patient.id = "+clientId+" ");
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				result = rs.getString(1);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}

	public ArrayList<AppointmentType> getAppointmentddList() {
		PreparedStatement preparedStatement = null;
		ArrayList<AppointmentType>list = new ArrayList<AppointmentType>();
		String sql = "SELECT id,name FROM apm_appointment_type order by name ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				AppointmentType  appointmentType = new AppointmentType();
				appointmentType.setId(rs.getInt(1));
				appointmentType.setName(rs.getString(2));
				
				list.add(appointmentType);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<AppointmentType> getSelfChargesList() {

		ArrayList<AppointmentType> list=new ArrayList<AppointmentType>();
		try {
			
			String sql="select id,name,charges,chargeType from apm_appointment_type where tpid=0";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				
				 AppointmentType appointmentType=new AppointmentType();
				 appointmentType.setId(rs.getInt(1));
				 appointmentType.setName(rs.getString(2));
				 appointmentType.setCharges(rs.getString(3));
				 appointmentType.setChargeType(rs.getString(4));
				 list.add(appointmentType);
				
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<AppointmentType> getChargesList(String chargeType,String chargecolumn) {

		ArrayList<AppointmentType> list=new ArrayList<AppointmentType>();
		try {
			if(chargecolumn==null){
				chargecolumn="charges";
			}if(chargecolumn.equals("")){
				chargecolumn="charges";
			}
			String sql="select id,name,"+chargecolumn+",chargeType,code from apm_appointment_type where tpid=0 and chargeid=0 and chargeType=?";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, chargeType);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				
				 AppointmentType appointmentType=new AppointmentType();
				 appointmentType.setId(rs.getInt(1));
				 appointmentType.setName(rs.getString(2));
				 appointmentType.setCharges(rs.getString(3));
				 appointmentType.setChargeType(rs.getString(4));
				 appointmentType.setCode(rs.getString(5));
				 list.add(appointmentType);
				
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return list;
	}

	public int saveThirdPartyCharges(String tid, String mrp, String code,
			String chargeType,String tpid,String payee,String wardid,String invstype,
			String stdchargeStatus,String onOff,String hour,String ratio,String discount,
			String discountAmt, String chargeAmount) {
		ThirdParty thirdParty=new ThirdParty();
		ThirdPartyDAO thirdPartyDAO=new JDBCThirdPartyDAO(connection);
		thirdParty=thirdPartyDAO.getThirdPartyDetails(tpid);
		String columnname=thirdParty.getChargecolumnname();
		if(columnname==null){
			columnname="charges";
		}if(columnname.equals("")){
			columnname="charges";
		}
		int result=0;
		InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
		try {
			
			String sql="insert into apm_appointment_type (name,"+columnname+",location,chargeType,tpid,chargeid,"
					+ "code,wardid,invstype,stdcharge,onoff,hours,ratio,discountShare,discountAmt,originalAmt) "
					+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
			PreparedStatement ps=connection.prepareStatement(sql);
			String chargeName="";
			if(invstype.equals("0")){
				chargeName=getChargeName(tid);
			} else {
				chargeName=getInvestiagtionName(tid);
				int roundcharge = investigationDAO.checkRoundCharge(invstype);
				if(roundcharge==1){
					chargeName = investigationDAO.getMasterChargeName(invstype);
				}
			}
			
			ps.setString(1, chargeName);
			ps.setString(2, chargeAmount);
			ps.setString(3, "0");
			ps.setString(4, chargeType);
			ps.setString(5, tpid);
			ps.setString(6, tid);
			ps.setString(7, code);
			ps.setString(8, wardid);
			ps.setString(9, invstype);
			ps.setString(10, stdchargeStatus);
			ps.setString(11, onOff);
			ps.setString(12, hour);
			ps.setString(13, ratio);
			ps.setString(14, discount);
			ps.setString(15, discountAmt);
			ps.setString(16, mrp);
			result=ps.executeUpdate();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}
	
	
	public int savePayerCharges(AppointmentType old_thirdparty_charges,String userId, String tid, String mrp, String code, String chargeType, String tpid,
			String payee, String wardid, String invstype, String standardcharge, String onoff, String hour, String ratio,
			String discount, String discountAmt, String chargeAmount, String datetime) {
		// TODO Auto-generated method stub
		
		int res=0;
		InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
		try {
			
			 String sql="insert into payercharges_log (pre_name,curr_name,pre_charges,curr_charges,pre_chargeType,curr_chargeType,pre_tpid,curr_tpid,pre_chargeid,curr_chargeid, "
			 		+ "pre_code,curr_code,pre_wardid,curr_wardid,pre_investype,curr_investype,pre_stdcharge,curr_stdcharge, "
			 		+ "pre_onoff,curr_onoff,pre_ratio,curr_ratio,pre_discountshare,curr_discountshare,pre_discountamount,curr_discountamount, "
			 		+ "pre_origanal_amt,curr_origanal_amt,datetime,userid)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			 PreparedStatement ps=connection.prepareStatement(sql);
			 
			 String chargeName="";
				if(invstype.equals("0")){
					chargeName=getChargeName(tid);
				} else {
					chargeName=getInvestiagtionName(tid);
					int roundcharge = investigationDAO.checkRoundCharge(invstype);
					if(roundcharge==1){
						chargeName = investigationDAO.getMasterChargeName(invstype);
					}
				}
			 
			 
			 ps.setString(1, old_thirdparty_charges.getName());
			 ps.setString(2, chargeName);
			 ps.setString(3, old_thirdparty_charges.getCharges());
			 ps.setString(4, chargeAmount);
			 ps.setString(5, old_thirdparty_charges.getChargeType());
			 ps.setString(6, chargeType);
			 ps.setString(7, old_thirdparty_charges.getTpid());
			 ps.setString(8, tpid);
			 ps.setString(9, old_thirdparty_charges.getChargeid());
			 ps.setString(10, tid);
			 ps.setString(11, old_thirdparty_charges.getCode());
			 ps.setString(12, code);
			 ps.setString(13, old_thirdparty_charges.getWardid());
			 ps.setString(14, wardid);
			 ps.setString(15, old_thirdparty_charges.getInvest_type());
			 ps.setString(16, invstype);
			 ps.setString(17, old_thirdparty_charges.getStdcharge());
			 ps.setString(18, standardcharge);
			 ps.setString(19, old_thirdparty_charges.getOnoff());
			 ps.setString(20, onoff);
			 ps.setString(21, old_thirdparty_charges.getRatio());
			 ps.setString(22, ratio);
			 ps.setString(23, old_thirdparty_charges.getDiscountShare());
			 ps.setString(24, discount);
			 ps.setDouble(25, old_thirdparty_charges.getDiscountAmt());
			 ps.setDouble(26, Double.valueOf(discountAmt));
			 ps.setDouble(27, old_thirdparty_charges.getOriginalAmt());
			 ps.setDouble(28, Double.valueOf(mrp));
			 ps.setString(29, datetime);
			 ps.setString(30, userId);
			 
			 if(!DateTimeUtils.isNull(old_thirdparty_charges.getName()).equals(DateTimeUtils.isNull(chargeName))
			 || !DateTimeUtils.isNull(old_thirdparty_charges.getCharges()).equals(DateTimeUtils.isNull(chargeAmount))	 
			 || !DateTimeUtils.isNull(old_thirdparty_charges.getChargeType()).equals(DateTimeUtils.isNull(chargeType)) 
			 || !DateTimeUtils.isNull(old_thirdparty_charges.getTpid()).equals(DateTimeUtils.isNull(tpid))
			 || !DateTimeUtils.isNull(old_thirdparty_charges.getCode()).equals(DateTimeUtils.isNull(code))
			 || !DateTimeUtils.isNull(old_thirdparty_charges.getWardid()).equals(DateTimeUtils.isNull(wardid))
			 || !DateTimeUtils.isNull(old_thirdparty_charges.getInvest_type()).equals(DateTimeUtils.isNull(invstype))
			 || !DateTimeUtils.isNull(old_thirdparty_charges.getStdcharge()).equals(DateTimeUtils.isNull(standardcharge))
			 || !DateTimeUtils.isNull(old_thirdparty_charges.getOnoff()).equals(DateTimeUtils.isNull(onoff))
			 || !DateTimeUtils.isNull(old_thirdparty_charges.getRatio()).equals(DateTimeUtils.isNull(ratio))
			 || !DateTimeUtils.isNull(old_thirdparty_charges.getDiscountShare()).equals(DateTimeUtils.isNull(discount))
			 || !DateTimeUtils.isNull(old_thirdparty_charges.getChargeid()).equals(DateTimeUtils.isNull(tid))
			 || old_thirdparty_charges.getDiscountAmt()!=Double.valueOf(discountAmt)
			 || old_thirdparty_charges.getOriginalAmt()!=Double.valueOf(mrp)
			 ) {
				 res=ps.executeUpdate();
			 }
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	@Override
	public AppointmentType getoldThirdPartyCharges(int chargeid) {
		// TODO Auto-generated method stub
		
		PreparedStatement preparedStatement = null;
		
		String sql="select id,name,charges,location,chargeType,tpid,chargeid, "
				+ "code,wardid,stdcharge,onoff,hours,ratio,discountShare,discountAmt,originalAmt, "
		        +" invstype from apm_appointment_type where id="+chargeid+" " ;
		
		    AppointmentType appointmentType=new AppointmentType();
		try {
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()){
				
				appointmentType.setId(rs.getInt(1));
				appointmentType.setName(rs.getString(2));
				appointmentType.setCharges(rs.getString(3));
				appointmentType.setLocation(rs.getString(4));
				appointmentType.setChargeType(rs.getString(5));
				appointmentType.setTpid(rs.getString(6));
				appointmentType.setChargeid(rs.getString(7));
				appointmentType.setCode(rs.getString(8));
				appointmentType.setWardid(rs.getString(9));
				appointmentType.setStdcharge(rs.getString(10));
				appointmentType.setOnoff(rs.getString(11));
				appointmentType.setHours(rs.getString(12));
				appointmentType.setRatio(rs.getString(13));
				appointmentType.setDiscountShare(rs.getString(14));
				appointmentType.setDiscountAmt(rs.getDouble(15));
				appointmentType.setOriginalAmt(rs.getDouble(16));
				appointmentType.setInvest_type(rs.getString(17));
				
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return appointmentType;
	}
	
	
	public String getChargeName(String chargeId) {

		String result="";
		try {
			String sql="select name from apm_appointment_type where id="+chargeId+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				
				 result=rs.getString(1);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
	
		return result;
	}
	
	
	public AppointmentType getThirdParyChargeDetails(String tpid,String chargeid,int wardid) {

		AppointmentType appointmentType=new AppointmentType();
		try {
			
			String sql="";
			if(wardid>0){
				sql="select id,name,charges,chargeType,code from apm_appointment_type where tpid="+tpid+" and chargeid="+chargeid+" and wardid="+wardid+" ";
			} else {
				sql="select id,name,charges,chargeType,code from apm_appointment_type where tpid="+tpid+" and chargeid="+chargeid+" and wardid=0 ";
			}
			
		    
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				 
				   appointmentType.setId(rs.getInt(1));
				   appointmentType.setName(rs.getString(2));
				   appointmentType.setCharges(rs.getString(3));
				   appointmentType.setChargeType(rs.getString(4));
				   appointmentType.setCode(rs.getString(5));
				   appointmentType.setTpid(tpid);
				   appointmentType.setChargeid(chargeid);
			}
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
	
		return appointmentType;
	}

	public ArrayList<AppointmentType> getNewChargeList(String tpid,
			String wardid, String chargeType,String invstype) {

		ArrayList<AppointmentType> list=new ArrayList<AppointmentType>();
		
		try {
			ThirdParty thirdParty=new ThirdParty();
			if(!tpid.equals("0")){
				ThirdPartyDAO thirdPartyDAO=new JDBCThirdPartyDAO(connection);
				thirdParty=thirdPartyDAO.getThirdPartyDetails(tpid);
			}
			if(thirdParty.getChargecolumnname()==null){
				thirdParty.setChargecolumnname("charges");
			}if(thirdParty.getChargecolumnname().equals("")){
				thirdParty.setChargecolumnname("charges");
			}
			String sql="select id,name,"+thirdParty.getChargecolumnname()+",chargeType,code,chargeid,stdcharge,onoff,hours,ratio,discountShare,originalAmt from apm_appointment_type where tpid="+tpid+" and chargeType='"+chargeType+"' and wardid="+wardid+" and invstype="+invstype+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				
				   AppointmentType appointmentType=new AppointmentType();	
				   appointmentType.setId(rs.getInt(1));
				   appointmentType.setName(rs.getString(2));
				   appointmentType.setCharges(rs.getString(3));
				   appointmentType.setChargeType(rs.getString(4));
				   appointmentType.setCode(rs.getString(5));
				   appointmentType.setChargeid(rs.getString(6));
				   appointmentType.setTpid(tpid);
				   appointmentType.setWardid(wardid);
				   appointmentType.setStdcharge(rs.getString(7));
				   appointmentType.setOnoff(rs.getString(8));
				   appointmentType.setHours(rs.getString(9));
				   appointmentType.setRatio(rs.getString(10));
				   appointmentType.setDiscountShare(rs.getString(11));
				   appointmentType.setOriginalAmt(rs.getDouble(12));
				   appointmentType.setActualAmount(DateTimeUtils.numberCheck(rs.getString(12)));
				   list.add(appointmentType);
			}
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return list;
	}
	
	public ArrayList<AppointmentType> getInvestigationChargeList(String tpid,
			String wardid, String chargeType,String invstype,String chargename) {

		ArrayList<AppointmentType> list=new ArrayList<AppointmentType>();
		
		try {
			
			String sql="select id,name,charges,chargeType,code,chargeid,stdcharge,onoff,hours,ratio from apm_appointment_type where tpid="+tpid+" and chargeType='"+chargeType+"' and wardid="+wardid+" and name='"+chargename+"' ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				
				   AppointmentType appointmentType=new AppointmentType();	
				   appointmentType.setId(rs.getInt(1));
				   appointmentType.setName(rs.getString(2));
				   appointmentType.setCharges(rs.getString(3));
				   appointmentType.setChargeType(rs.getString(4));
				   appointmentType.setCode(rs.getString(5));
				   appointmentType.setChargeid(rs.getString(6));
				   appointmentType.setTpid(tpid);
				   appointmentType.setWardid(wardid);
				   appointmentType.setStdcharge(rs.getString(7));
				   appointmentType.setOnoff(rs.getString(8));
				   appointmentType.setHours(rs.getString(9));
				   appointmentType.setRatio(rs.getString(10));
				   
				   list.add(appointmentType);
			}
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return list;
	}

	public int getChargeIdIfExists(String tpid, String wardid,
			String chargeType, String tid,String invstype) {

		int id=0;
		try {
			
			String sql="select id from apm_appointment_type where tpid="+tpid+" and chargeType='"+chargeType+"' and chargeid="+tid+" and wardid="+wardid+" and  invstype="+invstype+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()){
				 
				id=rs.getInt(1);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return id;
	}

	public int updateThirdPartyCharges(int chargeid, String mrp, String code,String stdChargeStatus, 
			String onOff,String hour,String ratio,String tpid, String discount, String discountAmt,String chargeAmount) {
		ThirdParty thirdParty=new ThirdParty();
		ThirdPartyDAO thirdPartyDAO=new JDBCThirdPartyDAO(connection);
		thirdParty=thirdPartyDAO.getThirdPartyDetails(tpid);
		String columnname=thirdParty.getChargecolumnname();
		if(columnname==null){
			columnname="charges";
		}if(columnname.equals("")){
			columnname="charges";
		}
		int result=0;
		try {
			
			String sql="update apm_appointment_type set "+columnname+"=?,code=?,stdcharge=?,onoff=?,"
					+ "hours=?,ratio=?,discountShare=?,discountAmt=?,originalAmt=? where id="+chargeid+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, chargeAmount);
			ps.setString(2, code);
			ps.setString(3, stdChargeStatus);
			ps.setString(4, onOff);
			ps.setString(5, hour);
			ps.setString(6, ratio);
			ps.setString(7, discount);
			ps.setString(8, discountAmt);
			ps.setString(9, mrp);
			result=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	public String getInvestiagtionName(String id) {
		
		String invsname="";
		try {
			String sql="select name from apm_investigation_name where id="+id+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				  
				 invsname=rs.getString(1);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return invsname;
	}

	public int getSelfChargeIdFromTpCharge(String chargeid,int wardid) {

		int result=0;
		try {
			String sql="SELECT chargeid from apm_appointment_type where id="+chargeid+" and wardid="+wardid+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				 result=rs.getInt(1);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	public AppointmentType getChargesDetailsByName(String apmtType, String chargeType,
			String tpid, int wardid) {

		AppointmentType appointmentType=null;
        try {
			
			String sql="select id,name,charges,chargeType,code,chargeid,wardid from apm_appointment_type where tpid="+tpid+" and chargeType='"+chargeType+"' and name='"+apmtType+"'  and wardid="+wardid+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				
				   appointmentType=new AppointmentType();
				   appointmentType.setId(rs.getInt(1));
				   appointmentType.setName(rs.getString(2));
				   appointmentType.setCharges(rs.getString(3));
				   appointmentType.setChargeType(rs.getString(4));
				   appointmentType.setCode(rs.getString(5));
				   appointmentType.setChargeid(rs.getString(6));
				   appointmentType.setWardid(rs.getString(7));
				   appointmentType.setTpid(tpid);
				   
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return appointmentType;
	}

	public ArrayList<Master> getStandardChargeList(String wardid, String tpid,String payee,LoginInfo loginInfo) {

		ArrayList<Master> list= new ArrayList<Master>();
		
		try {
			if(payee==null){
				payee="Client";
			}
			if(payee.equals("Client") || payee.equals("Self")){
				 tpid="0";
			}
			
String chargecolumnname = "charges";
			
			if(payee.equals(Constants.PAY_BY_THIRD_PARTY)){
				
				IpdDAO ipdDAO = new JDBCIpdDAO(connection);
				
				
				if(!ipdDAO.checkifMainTp(tpid)){
					 
					String temptpid= ipdDAO.getFollowerTp(tpid); 
					if(temptpid!=null){
						
						if(!tpid.equals("0")){
							tpid=temptpid;  



						}
					}
				}
				
				if(loginInfo.getIskunal()==1){
					ThirdPartyDAO dao = new JDBCThirdPartyDAO(connection);
					ThirdParty thirdParty = dao.getThirdPartyDetails(tpid);			
					chargecolumnname = thirdParty.getChargecolumnname();
					
				}
			}
			
			String sql="select id,name,"+chargecolumnname+",ratio,hours,chargeType from apm_appointment_type where tpid="+tpid+" and wardid="+wardid+" and stdcharge=1 and onoff=0";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				  
				 Master master=new Master();
				 master.setId(rs.getInt(1));
				 master.setName(rs.getString(2));
				 master.setCharge(rs.getString(3));
				 
				 if(rs.getInt(4)==1){
					 double charge = rs.getDouble(3) / 2;
					 master.setCharge(Double.toString(charge));
				 }
				 
				 master.setChargehours(rs.getInt(5));
				 master.setMasterchargetype(rs.getString(6));
				 master.setWardid(wardid);
				 list.add(master);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return list;
	}

	public int deleteStdCharge(String ipdid, String chargeId) {

		int result=0;
		try {
			
			String sql="delete from apm_std_onoff_charge where ipdid="+ipdid+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			result =ps.executeUpdate(); 
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}

	public int saveStdCharge(String ipdid, String chargeId,int assesmentid,String status,String ondatetime,String offdatetime) {

		int result=0;
		try {
			
			String sql="insert into apm_std_onoff_charge (ipdid, chargeid, status,assesmentid,ondatetime,offdatetime) values (?,?,?,?,?,?) ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, ipdid);
			ps.setString(2, chargeId);
			ps.setString(3, status);
			ps.setInt(4, assesmentid);
			ps.setString(5, ondatetime);
			ps.setString(6, offdatetime);
			
			
			result =ps.executeUpdate();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	public boolean isStdChargeSelected(String ipdid, int chargeId) {

		try {
			
			String sql="select id from apm_std_onoff_charge where ipdid="+ipdid+" and chargeid="+chargeId+" and status=1 ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				 return true;
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return false;
	}

	public boolean isStdChargeExist(String ipdid, int chargeId) {

		try {
			
			String sql="select id from apm_std_onoff_charge where ipdid="+ipdid+" and chargeid="+chargeId+"  ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				 return true;
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return false;
	}
	public Master getMasterCharges(String chargeId) {

		Master master= new Master();
		try {
			String sql="select name,charges,chargeType,ratio,hours from apm_appointment_type where id="+chargeId+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				
				master.setName(rs.getString(1));
				master.setCharge(rs.getString(2));
				master.setId(Integer.parseInt(chargeId));
				master.setMasterchargetype(rs.getString(3));
				
				if(rs.getInt(4)==1){
					double charge = rs.getDouble(2) / 2;
					master.setCharge(Double.toString(charge));
				}
				
				master.setChargehours(rs.getInt(5));
			
				 
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return master;
	}

	public ArrayList<String> getStdChargeHourList() {

		ArrayList<String> list=  new ArrayList<String>();
		
		try {
			String sql="select id,name from apm_std_charge_hours";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				 
				list.add(rs.getString(2)); 
				
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return list;
	}
	
	public AppointmentType getMasterCharge(String id) {
		PreparedStatement preparedStatement = null;
		AppointmentType appointmentType = new AppointmentType();
		String sql = "SELECT id,name,description,category,duration,charges,location,chargeType,reportField,tpid,chargeid,code,hours,ratio FROM apm_appointment_type where id = "+id+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				appointmentType.setId(rs.getInt(1));
				appointmentType.setName(rs.getString(2));
				appointmentType.setDescription(rs.getString(3));
				appointmentType.setCategory(rs.getString(4));
				appointmentType.setDuration(rs.getString(5));				
				appointmentType.setCharges(rs.getString(6));
				appointmentType.setLocation(rs.getString(7));
				appointmentType.setChargeType(rs.getString(8));
				appointmentType.setReportField(rs.getString(9));
				appointmentType.setTpid(rs.getString(10));
				appointmentType.setChargeid(rs.getString(11));
				appointmentType.setCode(rs.getString(12));
				appointmentType.setHours(rs.getString(13));
				appointmentType.setRatio(rs.getString(14));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return appointmentType;
	}

	public int getStdChargeIdIdExists(String ipdid, String chargeId) {

		int result=0;
		try {
			String sql="select id from apm_std_onoff_charge where ipdid="+ipdid+" and chargeid="+chargeId+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs =ps.executeQuery();
			while(rs.next()){
				 result =rs.getInt(1);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		} 
		
		return result;
	}

	public int updateStdCharge(int id, String status,String ondatetime,String offdatetime) {
		int result=0;
		
		String curstatus = getstddbcurstatus(id);
		if(!curstatus.equals(status)){
		try {
			
			StringBuffer buffer= new StringBuffer();
			buffer.append("update apm_std_onoff_charge set status="+status+", ");
			if(!ondatetime.equals("")){
				String temp= getStdOnOffTime(id, "on");
				if(temp.equals("")){
					temp = ondatetime;
				}else{
					temp=temp+" , "+ondatetime;
				}
				
				buffer.append("ondatetime='"+temp+"' ");
			} else if(!offdatetime.equals("")) {
				String temp= getStdOnOffTime(id, "off");
				if(temp.equals("")){
					temp = offdatetime;
				}else{
					temp=temp+" , "+offdatetime;
				}
				
				buffer.append("offdatetime='"+temp+"' ");
			}
			
			buffer.append(" where id="+id+" ");
			
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			result =ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}
		}
		return result;
	}

	
	public String getstddbcurstatus(int id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT status FROM apm_std_onoff_charge where id = "+id+" ";
		
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

	private String getStdOnOffTime(int id,String val) {
		
		String time="";
		try {
			String sql="";
			if(val.equals("on")){
				
				 sql="select ondatetime from apm_std_onoff_charge where id="+id+" ";
			}else {
				 sql="select offdatetime from apm_std_onoff_charge where id="+id+" ";
			}
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				 
				 time=rs.getString(1);
			}  
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return time;
	}
	
	
	
	public ArrayList<Master> getStdOnoffChargeList(String wardid, String tpid,
			String payee) {
		ArrayList<Master> list= new ArrayList<Master>();
		
		try {
			if(payee==null){
				payee="Client";
			}
			if(payee.equals("Client") || payee.equals("Self")){
				 tpid="0";
			}
			
			String sql="select id,name,charges,tpid from apm_appointment_type where  wardid="+wardid+" and stdcharge=1 and onoff=1 ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				  
				 Master master=new Master();
				 master.setId(rs.getInt(1));
				 master.setName(rs.getString(2));
				 master.setCharge(rs.getString(3));
				 master.setTpid(""+rs.getString(4));
				 master.setWardid(wardid);
				 list.add(master);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return list;
	}
	
	public String getFollowerTp(String tpid) {
		  
		  String result="0";
		  try {
			
			  String sql="select followtp from apm_third_party_details where id="+tpid+"";
			  PreparedStatement ps=connection.prepareStatement(sql);
			  ResultSet rs=ps.executeQuery();
			  
			  while(rs.next()){
				    result=rs.getString(1);
			  }
			  
		} catch (Exception e) {

			e.printStackTrace();
		}
		  
	    return result; 
	    
	  }
	
	public boolean checkifMainTp(String tpid) {
		  
		  boolean res=false;
		  try {
			  
			  String sql="select maintp from apm_third_party_details where id="+tpid+"";
			  PreparedStatement ps=connection.prepareStatement(sql);
			  ResultSet rs=ps.executeQuery();
			  while(rs.next()){
				  
				    res=rs.getBoolean(1);
			  }
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		  
		 return res; 
	  }

	public int getstdAssesmentid(int id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT assesmentid FROM apm_std_onoff_charge where id = "+id+" ";
		
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

	public int getDbQuantity(int assesmentid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT quantity FROM apm_invoice_assesments where id = "+assesmentid+" ";
		
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

	public Accounts getStdChargeDetails(int result) {
		PreparedStatement preparedStatement = null;
		Accounts accounts = new Accounts();
		String sql = "SELECT ondatetime, offdatetime FROM apm_std_onoff_charge where id = "+result+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				accounts.setOndatetime(rs.getString(1));
				accounts.setOffdatetime(rs.getString(2));
				
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return accounts;
	}
	
	
	public int updateStdChargeDateTime(int onofid, String ondate, String offdate) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_std_onoff_charge set ondatetime=?,offdatetime=?,status=0 where id="+onofid+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, ondate);
			preparedStatement.setString(2, offdate);
			result = preparedStatement.executeUpdate();
			
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public String getLastShiftingDate(int id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		//String sql = "SELECT lastmodified FROM ipd_bed_change_log where  admissionid ="+id+" order by lastmodified desc limit 0,2 ";
		String sql = "SELECT distinct(lastmodified) FROM ipd_bed_change_log where  admissionid = "+id+"  limit 0,2 ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				String temp[] = rs.getString(1).split(" ");
				result = DateTimeUtils.getCommencingDate1(temp[0]) + " " + temp[1];
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}
	
	public String getLastShiftedLogid(int id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT id FROM ipd_bed_change_log where  admissionid ="+id+" order by lastmodified desc limit 0,2 ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}
	
	

	public int resetAllInprocessCharge(int id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_invoice_assesments set newshftcharge=0 where ipdid="+id+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
		}catch (Exception e) {
		
			// TODO: handle exception
		}
		return result;
	}

	public int setInprocessforNewShiftCharges(int invoiceid,int logid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_invoice_assesments set newshftcharge=1,logid="+logid+" where invoiceid="+invoiceid+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
		}catch (Exception e) {
		
			// TODO: handle exception
		}
		return result;
	}

	public int updateLasteShiftedChargeQty(String lastshiftingdate,
			String curshiftingdate, int qty, int id,int chargeid,int assessmentid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		
		//String sql = "update apm_invoice_assesments set quantity="+qty+" where ipdid="+id+" and newshftcharge=1  ";
		String sql = "update apm_invoice_assesments set quantity="+qty+" where id = "+assessmentid+"  ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
		}catch (Exception e) {
		
			// TODO: handle exception
		}
		return result;
	}

	public int updateInprocessQty(int id,int qty,String chargeid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		
		String sql = "update apm_invoice_assesments set quantity="+qty+" where ipdid="+id+" and newshftcharge=1 and std_charge_id="+chargeid+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
		}catch (Exception e) {
		
			// TODO: handle exception
		}
		return result;
	}

	public ArrayList<String> getChargeIdList(int invoiceid) {
		PreparedStatement preparedStatement = null;
		ArrayList<String> list  = new  ArrayList<String>();
		String sql = "SELECT std_charge_id FROM apm_invoice_assesments where invoiceid = "+invoiceid+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				list.add(rs.getString(1));
				
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	public int getChargeHour(String str) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT hours FROM apm_appointment_type where id = "+str+" ";
		
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

	public int getStdChargeAssesmentId(int ipdid, String wardid, int chargeid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT id FROM apm_invoice_assesments where ipdid = "+ipdid+" and wardid = "+wardid+" and std_charge_id = "+chargeid+" ";
		
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

	public ArrayList<Master> getLastShiftedChargeList(String lastshiftedlogid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list = new ArrayList<Master>();
		String sql = "SELECT id,std_charge_id FROM apm_invoice_assesments where logid = "+lastshiftedlogid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setChargeid(rs.getString(2));
				int chargehours = getChargeHour(rs.getString(2));
				master.setChargehours(chargehours);
				
				list.add(master);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return list;
	}

	public int updateinvstgationChargeName(int chargeid, String chargeType,
			String invstype,String tid,String mrp,String code,String tpid, String discount, 
			String discountAmt,String chargeAmount) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		InvestigationDAO investigationDAO = new JDBCInvestigationDAO(connection);
		String chargeName="";
		chargeName=getInvestiagtionName(tid);
		int roundcharge = investigationDAO.checkRoundCharge(invstype);
		if(roundcharge==1){
			chargeName = investigationDAO.getMasterChargeName(invstype);
		}
		
		if(code!=null){
			if(code.equals("")){
				code = "0";
			}
		}
		ThirdParty thirdParty=new ThirdParty();
		ThirdPartyDAO thirdPartyDAO=new JDBCThirdPartyDAO(connection);
		thirdParty=thirdPartyDAO.getThirdPartyDetails(tpid);
		String columnname=thirdParty.getChargecolumnname();
		if(columnname==null){
			columnname="charges";
		}if(columnname.equals("")){
			columnname="charges";
		}
		String sql = "update apm_appointment_type set name='"+chargeName+"',"+columnname+"="+chargeAmount+",code="+code+",discount='"+discount+"',discountAmt='"+discountAmt+"',originalAmt='"+mrp+"' where name = '"+chargeName+"' and tpid="+tpid+" ";
		
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	
	//bed shifting charges

	public int deleteallBedShiftingcharge(int ipdid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from apm_invoice_assesments where ipdid ="+ipdid+" and masterchargetype='Bed Charge' ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public String getLogIDList(int id) {
		PreparedStatement preparedStatement= null;
		String result = "";
		String sql = "select id from ipd_bed_change_log where admissionid = "+id+"  ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = result + rs.getString(1) + ",";
			}
			
			if(result.length()!=0){
				result = result.substring(0,result.length()-1);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public String getapminvoiceidlist(String logidList) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT invoiceid FROM apm_invoice_assesments where logid in("+logidList+") ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = result + rs.getString(1) + ",";
			}
			
			if(result.length()!=0){
				result = result.substring(0,result.length()-1);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}


	public ArrayList<Master> getLogCommencingList(int id) {
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list = new ArrayList<Master>();
		String sql = "select count(commencing),commencing,lastmodified from ipd_bed_change_log where admissionid = "+id+" group by commencing ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Master master = new Master();
				int count = rs.getInt(1);
				master.setDate(rs.getString(2));
				master.setLastmodified(rs.getString(3));
				
				if(count>1){
					list.add(master);
				}
				
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	public String getBedShiftigWardIdLIst(int id, String date) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select wardid from ipd_bed_change_log where admissionid = "+id+" and commencing ='"+date+"' ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = result + rs.getString(1) + ",";
			}
			
			if(result.length()!=0){
				result = result.substring(0,result.length()-1);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public ArrayList<Master> getBedShiftingStandardChargeList(
			String wardidList, String tpid, String payee,LoginInfo loginInfo) {
	ArrayList<Master> list= new ArrayList<Master>();
		
		try {
			if(payee==null){
				payee="Client";
			}
			if(payee.equals("Client") || payee.equals("Self")){
				 tpid="0";
			}
			
			String chargecolumnname = "charges";
			
			if(payee.equals(Constants.PAY_BY_THIRD_PARTY)){
				
				IpdDAO ipdDAO = new JDBCIpdDAO(connection);
				
				
				if(!ipdDAO.checkifMainTp(tpid)){
					 
					String temptpid= ipdDAO.getFollowerTp(tpid); 
					if(temptpid!=null){
						
						if(!tpid.equals("0")){
							tpid=temptpid;  



						}
					}
				}
				
				if(loginInfo.getIskunal()==1){
					ThirdPartyDAO dao = new JDBCThirdPartyDAO(connection);
					ThirdParty thirdParty = dao.getThirdPartyDetails(tpid);			
					chargecolumnname = thirdParty.getChargecolumnname();
					
				}
			}
			
			
			
			
			String sql="select id,name,max("+chargecolumnname+"),ratio,hours,chargeType from apm_appointment_type where tpid="+tpid+" " +
					" and wardid in("+wardidList+") and stdcharge=1 and onoff=0 " +
					" group by name ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				  
				 Master master=new Master();
				 master.setId(rs.getInt(1));
				 master.setName(rs.getString(2));
				 master.setCharge(rs.getString(3));
				 
				 if(rs.getInt(4)==1){
					 double charge = rs.getDouble(3) / 2;
					 master.setCharge(Double.toString(charge));
				 }
				 
				 master.setChargehours(rs.getInt(5));
				 master.setMasterchargetype(rs.getString(6));
				// master.setWardid(wardid);
				 list.add(master);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return list;
	}

	public String getIpdAdmissionDate(int id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT lastmodified FROM ipd_admission_log where admissionid = "+id+" ";
		
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

	public ArrayList<Master> getsepetatrLogcommencingList(int id) {
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list = new ArrayList<Master>();
		String sql = "select count(commencing),commencing,lastmodified from ipd_bed_change_log where admissionid = "+id+" group by commencing ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Master master = new Master();
				int count = rs.getInt(1);
				master.setDate(rs.getString(2));
				master.setLastmodified(rs.getString(3));
				
				/*if(count==1){
					list.add(master);
				}*/
				
				list.add(master);
				
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	public Bed getLogwardId(String date, int id) {
		PreparedStatement preparedStatement = null;
		Bed bed = new Bed();
		String sql = "SELECT wardid,bedid,id FROM ipd_bed_change_log where commencing = '"+date+"' and admissionid = "+id+" order by id desc limit 0,1 ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				bed.setWardid(rs.getString(1));
				bed.setBedid(rs.getString(2));
				bed.setLogid(rs.getString(3));
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
			
			
		return bed;
	}

	public Master getLastIpdLogData(int id) {
		PreparedStatement preparedStatement = null;
		Master master = new Master();
		String sql = "select commencing,lastmodified from ipd_bed_change_log where admissionid ="+id+" order by id desc limit 0,1 ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				master.setDate(rs.getString(1));
				master.setLastmodified(rs.getString(2));
				
				
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
	
		
		return master;
	}

	public int getautosetchargelogid(int ipdid, String wardid, String bedid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT id FROM ipd_bed_change_log where admissionid="+ipdid+" and autosetcharge=1 order by commencing desc limit 0,1 ";
		
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

	public int updateAutosetWardID(int autosetchargelogid, String wardid, String bedid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update ipd_bed_change_log  set wardid="+wardid+", bedid="+bedid+" where id="+autosetchargelogid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public boolean checkChargeExisist(String chargetype, String chargeName, String wardid, String tpid,
			String otchargetype) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "select * from apm_appointment_type where chargeType='"+chargetype+"' and name='"+chargeName+"'"
				+ " and tpid="+tpid+" and wardid="+wardid+" and otchargetype="+otchargetype+" ";
		
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

	public int updateOtcharge(String chargetype, String chargeName, String charge, String wardid, String tpid,
			String otchargetype,String duration) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_appointment_type set charges='"+charge+"',duration='"+duration+"' where chargeType='"+chargetype+"' and name='"+chargeName+"'"
				+ " and tpid="+tpid+" and wardid="+wardid+" and otchargetype="+otchargetype+"  ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public int saveOtCharges(String chargetype, String chargeName, String charge, String wardid, String tpid,
			String otchargetype,String duration) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_appointment_type (chargeType,name,charges,tpid,wardid,otchargetype,duration) values(?,?,?,?,?,?,?) ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, chargetype);
			preparedStatement.setString(2, chargeName);
			preparedStatement.setString(3, charge);
			preparedStatement.setString(4, tpid);
			preparedStatement.setString(5, wardid);
			preparedStatement.setString(6, otchargetype);
			preparedStatement.setString(7, duration);
			
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getotChargeId(String chargetype, String chargeName, String wardid, String tpid) {
		PreparedStatement preparedStatement = null;
		String result = "0";
		String sql = "select id from apm_appointment_type where chargeType='"+chargetype+"' and name='"+chargeName+"'"
				+ " and tpid="+tpid+" and wardid="+wardid+" and otchargetype=1 ";
		
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

	public int saveAniDoctor(String doctor) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into reference(name,isanesthesiologist) values(?,?) ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, doctor);
			preparedStatement.setString(2, "1");
			result = preparedStatement.executeUpdate();
			
			if(result>0){
				
				 ResultSet rs=preparedStatement.getGeneratedKeys();
				 while(rs.next()){
					 
					  result=rs.getInt(1);
				 }
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public ArrayList<Master> getLedgerServiceList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list = new ArrayList<Master>();
		int result = 0;
		String sql = "select id,type from apm_invoice_type ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				
				boolean checkServiceExist = checkServiceExsist(master.getId());
				
				list.add(master);
				/*if(!checkServiceExist){
					list.add(master);
				}*/
				
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	private boolean checkServiceExsist(int id) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "select * from ledger_master where services like('%,"+id+",%') ";
		
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

	public ArrayList<Master> getLedgerList(String ltype) {
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list = new ArrayList<Master>();
		String sql = "select id,name from ledger_master  where ltype = "+ltype+"";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				
				list.add(master);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return list;
			
	}
	
	public ArrayList<Master> getManageLedgerList(String ltype) {
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list = new ArrayList<Master>();
		String sql = "select id,name from ledger_master ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				
				list.add(master);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return list;
			
	}
	
	
	
	public ArrayList<Master> getLedgerList1(String ltype) {
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list = new ArrayList<Master>();
		String sql = "select id,name from ledger_master ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				
				list.add(master);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return list;
			
	}

	public int updateLedgerServices(String ledgername, String hdnledgerserviceid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update ledger_master set services='"+hdnledgerserviceid+"' where id="+ledgername+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}

	public String getdbSelectedServices(String ledgername) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select services from ledger_master where id = "+ledgername+" ";
		
		try{
			preparedStatement  = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
				
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public int saveNewLedger(String hdnnewledger, String howpaid,String bname,String ltype) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into ledger_master(name,paymode,bnkname,ltype) values(?,?,?,?)";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, hdnnewledger);
			preparedStatement.setString(2, howpaid);
			preparedStatement.setString(3, bname);
			preparedStatement.setString(4, ltype);
			
			result = preparedStatement.executeUpdate();
			
			if (result == 1) {
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if (resultSet.next()) {
					result = resultSet.getInt(1);
				}
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public ArrayList<Accounts> getledgerreportData(String fromDate, String toDate, String ledgername, String servicename,
			String actype, String bnkname) {
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		//String sql = "select clientid, product, debit, credit, balance, commencing, id from ledger_sheet where ledgerid = "+ledger+" ";
		StringBuffer sql = new StringBuffer();
		
		/*sql.append("select  ledger_sheet.clientid, product, ledger_sheet.debit, credit, balance, ledger_sheet.gcommencing, ledger_sheet.id,itype,apm_charges_invoice.id ");
		sql.append("from ledger_sheet inner join ledger_master ");
		sql.append("on ledger_master.id = ledger_sheet.ledgerid ");
		sql.append("inner join apm_charges_invoice on ");
		sql.append("ledger_sheet.invid = apm_charges_invoice.id ");
		sql.append("where gcommencing between '"+fromDate+"' and '"+toDate+"' ");*/
		
		sql.append("select  ledger_sheet.clientid, product, ledger_sheet.debit, credit, balance, ledger_sheet.gcommencing, ledger_sheet.id,invid,ltype,ltype,ledger_master.name,ledger_sheet.vendorid ");
		sql.append("from ledger_sheet inner join ledger_master on ledger_master.id = ledger_sheet.ledgerid ");
		sql.append("where gcommencing between '"+fromDate+"' and '"+toDate+"' ");
		
		if(ledgername!=null){
			if(!ledgername.equals("0")){
				sql.append("and ledgerid = "+ledgername+" ");
			}
			if(!servicename.equals("0")){
				sql.append("and itype="+servicename+" ");
			}
			if(actype.equals("1")){
				sql.append("and credit != 0 ");
			}
			if(actype.equals("2")){
				sql.append("and ledger_sheet.debit != 0 ");
			}
			if(!bnkname.equals("0")){
				sql.append("and bnkname = "+bnkname+" ");
			}
			
		}
		
		sql.append("order by id desc ");
		
		
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			double ctotal = 0;
			double dtotal = 0;
			while(rs.next()){
				Accounts accounts = new Accounts();
				
				
				
				
				String clientid = rs.getString(1);
				
				accounts.setProductname(rs.getString(2));
				
				if(rs.getDouble(3)==0){
					Client client = clientDAO.getClientDetails(clientid);
					String fullname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();
					
					
					int vendorid = rs.getInt(12);
					if(vendorid!=0){
						fullname = getvendorname(vendorid);
					}
					
					accounts.setClientName(fullname);
				}else{
					Client client = clientDAO.getClientDetails(clientid);
					String fullname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();
					
					
					int vendorid = rs.getInt(12);
					if(vendorid!=0){
						fullname = getvendorname(vendorid);
					}
					
					accounts.setClientName(fullname);
				}
				
				dtotal = dtotal + rs.getDouble(3);
				ctotal = ctotal + rs.getDouble(4);
				
				accounts.setDebitAmount(dtotal);
				accounts.setCreditAmount(ctotal);
						
				accounts.setDebitAmountx(DateTimeUtils.changeFormat(rs.getDouble(3)));
				accounts.setCreditTotalx(DateTimeUtils.changeFormat(rs.getDouble(4)));
				accounts.setBalanceTotalx(DateTimeUtils.changeFormat(rs.getDouble(5)));
				
				accounts.setCommencing(rs.getString(6));
				accounts.setId(rs.getInt(7));
				
				String itypename = getItypeName(rs.getString(8));
				accounts.setItype(itypename);
				
				accounts.setInvoiceid(rs.getInt(8));
				accounts.setLtype(rs.getString(9));
				accounts.setLedgername(rs.getString(11));
				
				
				list.add(accounts);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	
	private String getvendorname(int vendorid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT name FROM inventory_vendor where id ="+vendorid+" ";
		
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

	private String getItypeName(String id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select type from apm_invoice_type where id = "+id+" ";
		
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
	
	
	public ArrayList<Master> getExpenceLedgerAmountList(LoginInfo loginInfo) {
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list = new ArrayList<Master>();
	
		StringBuffer sql = new StringBuffer();
		//sql.append("select ledgerid,name,amount from ledger_amount ");
		sql.append("select ledger_amount.id,name,amount,ledger_sheet.ledgerid from ledger_sheet inner join ledger_amount on ledger_amount.ledgerid=ledger_sheet.ledgerid group by ledgerid ");
		ChargesAccountProcessingDAO chargesAccountProcessingDAO = new JDBCChargeAccountProcesDAO(connection);
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				
				Master master = new Master();
				master.setId(rs.getInt(1));
				
				
				double amount = rs.getDouble(3);
				
				String amt = Constants.getCurrency(loginInfo) + ""+DateTimeUtils.changeFormat(amount)+"";
				
				master.setName(rs.getString(2) + " ("+amt+") " );
				
				master.setCreditDebit(isCreditDebit(rs.getString(1)));
				master.setLedgerid(rs.getInt(4));
				list.add(master);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	/*	ArrayList<Master>bnklist = getbankLederList(loginInfo);
		list.addAll(bnklist);
*/		
		return list;
			
	}

	
	public String  isCreditDebit(String ledgerId){
		String res="";
		try {
			String sql=" select cd from ledger_ahead_master where services like '%,"+ledgerId+",%' ";
			PreparedStatement ps= connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				res=""+rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	public String  getLheadId(String ledgerId){
		String res="";
		try {
			String sql=" select id from ledger_ahead_master where services like '%,"+ledgerId+",%' ";
			PreparedStatement ps= connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				res=""+rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	public ArrayList<Master> getExpenceLedgerList(LoginInfo loginInfo) {
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list = new ArrayList<Master>();
	
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ledgerid,name, sum(debit) FROM ledger_sheet ");
		sql.append("inner join ledger_master on ledger_master.id = ledger_sheet.ledgerid ");
		sql.append("where ltype in(2,3) group by ledgerid ");
		
		ChargesAccountProcessingDAO chargesAccountProcessingDAO = new JDBCChargeAccountProcesDAO(connection);
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				
				Master master = new Master();
				master.setId(rs.getInt(1));
				
				double expncpayment = getExcpencePayment(master.getId());
				double amount = rs.getDouble(3);
				if(amount>=expncpayment){
					amount = rs.getDouble(3) - expncpayment;
				}
				//double lbal = chargesAccountProcessingDAO.getAheadLedgerBalance(rs.getString(1));
				
				String amt = Constants.getCurrency(loginInfo) + ""+DateTimeUtils.changeFormat(amount)+"";
				
				master.setName(rs.getString(2) + " ("+amt+") " );
				
				
				
				list.add(master);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		/*ArrayList<Master>bnklist = getbankLederList(loginInfo);
		list.addAll(bnklist);*/
		
		return list;
			
	}

	private ArrayList<Master> getbankLederList(LoginInfo loginInfo) {
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list = new ArrayList<Master>();
		StringBuffer sql = new StringBuffer();
		
		sql.append("select ledger_master.id,name from ledger_master where not exists ");
		sql.append("(select ledger_sheet.ledgerid from ledger_sheet where ledger_master.id = ");
		sql.append("ledger_sheet.ledgerid) and ltype = 2 ");
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				
				Master master = new Master();
				master.setId(rs.getInt(1));
				
				double amount = 0;
				//double lbal = chargesAccountProcessingDAO.getAheadLedgerBalance(rs.getString(1));
				
				String amt = Constants.getCurrency(loginInfo) + ""+DateTimeUtils.changeFormat(amount)+"";
				
				master.setName(rs.getString(2) + " ("+amt+") " );
				
				
				
				list.add(master);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return list;
	}

	private double getExcpencePayment(int id) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "select sum(credit) from ledger_sheet where expnctype = 2  and ledgerid = "+id+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getDouble(1);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public int saveNewAccountHead(String hdnnewahead,String obal,String actype) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into ledger_ahead_master(name,obal,cd) values(?,?,?) ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, hdnnewahead);
			preparedStatement.setString(2, obal);
			preparedStatement.setString(3, actype);
			result = preparedStatement.executeUpdate();
			
			if (result == 1) {
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if (resultSet.next()) {
					result = resultSet.getInt(1);
				}
			}

			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public double getLedgerBalance(int saveid) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "select balance from ledger_ahead_sheet where ledgerid = "+saveid+" order by id desc limit 0,1 ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getDouble(1);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public int saveLedgerAhead(String partyid, String product, String lddebit, String credit, double lbal, int saveid,
			String ldcommencing,String gcommencing) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into ledger_ahead_sheet(clientid, product, debit, credit, balance, ledgerid, commencing,gcommencing) "
				+ "values(?,?,?,?,?,?,?,?) ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, partyid);
			preparedStatement.setString(2, product);
			preparedStatement.setString(3, lddebit);
			preparedStatement.setString(4, credit);
			preparedStatement.setDouble(5, lbal);
			preparedStatement.setInt(6, saveid);
			preparedStatement.setString(7, ldcommencing);
			preparedStatement.setString(8, gcommencing);
			result = preparedStatement.executeUpdate();
			
			if (result == 1) {
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if (resultSet.next()) {
					result = resultSet.getInt(1);
				}
			}

		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}

	public ArrayList<Master> getAheadNameList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list = new ArrayList<Master>();
		String sql = "SELECT id,name FROM ledger_ahead_master ";
		
		try{
			preparedStatement  = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				
				list.add(master);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	public ArrayList<Master> getAheadServiceNameList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list = new ArrayList<Master>();
		String sql = "select id,name from ledger_master order by name ";
		
		try{
			preparedStatement  = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
			
				master.setParentid(getLheadId(rs.getString(1)));
				list.add(master);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	
	
	public int updateAheadServices(String aheadname, String hdnaheadserviceid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update ledger_ahead_master set services='"+hdnaheadserviceid+"' where id="+aheadname+" ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}

	public Master  getdbSelectedAheadServices(String aheadname) {
		PreparedStatement preparedStatement = null;
		Master master = new Master();
		String sql = "select services,cd from ledger_ahead_master where id = "+aheadname+" ";
		
		try{
			preparedStatement  = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				master.setServices(rs.getString(1));
				master.setActype(rs.getString(2));
				
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return master;
	}

	public int checkCurrentAccountForTheDay(String lid,String cdate) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String todate = cdate + " 23:59:59";
		String sql = "SELECT id FROM ledger_ahead_sheet where  commencing between '"+cdate+"' and '"+todate+"' and ledgerid = "+lid+" ";
		
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

	public double getLastLedgerdetails(String dbselectedservices,int length) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		
		
		dbselectedservices = dbselectedservices.substring(0,dbselectedservices.length()-1);
		String sql = "SELECT balance FROM ledger_sheet where ledgerid in("+dbselectedservices+") order by id desc limit 0,"+length+" ";		
		double totale = 0;
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()){
				totale = totale + rs.getDouble(1);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return totale;
	}

	public double getAheadBalance(String aheadname) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "SELECT balance FROM ledger_ahead_sheet where ledgerid = "+aheadname+" ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getDouble(1);
			}
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public double getAheadDebit(String aheadname) {
		PreparedStatement preparedStatement = null;
		double result = 0;
		String sql = "SELECT debit FROM ledger_ahead_sheet where ledgerid = "+aheadname+" and credit=0 order by id desc limit 0,1 ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getDouble(1);
			}
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public int getLastAheadID(String aheadname) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT id FROM ledger_ahead_sheet where ledgerid = "+aheadname+" ";
		
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

	public int updateLedgerAhead(String partyid, String product, String lddebit, String credit, double lbal,
			int parseInt, String ldcommencing, int lastaheadid) {
		// TODO Auto-generated method stub
		return 0;
	}

	public ArrayList<Accounts> getAheadreportData(String selectedledgerid1,String searchtext) {
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		
		//selectedledgerid = selectedledgerid.substring(0,selectedledgerid.length()-1);
		String sql = "select  debit, credit, gcommencing, id from ledger_ahead_sheet where ledgerid = "+selectedledgerid1+" order by gcommencing ";
		
		/*StringBuffer sql = new StringBuffer();
		sql.append("select commencing,clientid,ledger_master.name,credit,debit,ledger_sheet.id,invid ");
		sql.append("from ledger_sheet  inner join ledger_master ");
		sql.append("on ledger_master.id = ledger_sheet.ledgerid ");
		sql.append("where ledgerid in("+selectedledgerid+")  ");
		
		if(!searchtext.equals("")){
			sql.append("and invid = '"+searchtext+"' order by invid ");
		}else{
			sql.append("order by invid ");
		}*/
		
		ClientDAO clientDAO = new JDBCClientDAO(connection);
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setDebitAmountx(DateTimeUtils.changeFormat(rs.getDouble(1)));
				accounts.setCreditTotalx(DateTimeUtils.changeFormat(rs.getDouble(2)));
				
				accounts.setCommencing(rs.getString(3));
				accounts.setId(rs.getInt(4));
				
				list.add(accounts);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Master> getledgersheetList(Master master) {
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list = new ArrayList<Master>();
		
		String services = master.getServices().substring(0,master.getServices().length()-1);
		
		String sql = "select sum(credit),gcommencing from ledger_sheet where ledgerid in("+services+") group by gcommencing ";
		if(master.getActype().equals("2")){
			sql = "select sum(debit),gcommencing from ledger_sheet where ledgerid in("+services+") group by gcommencing ";
		}
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Master m = new Master();
				m.setCharge(rs.getString(1));
				m.setDate(rs.getString(2));
				
				list.add(m);
			}
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	public int checkAheadSheetEntery(String date,String ledgerid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT id FROM ledger_ahead_sheet where gcommencing = '"+date+"' and ledgerid="+ledgerid+" ";
		
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

	public int updateLedgerAheadData(String credit, String lddebit, int checkaccountentry) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update ledger_ahead_sheet set credit='"+credit+"',debit='"+lddebit+"' where id = "+checkaccountentry+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public ArrayList<Accounts> getOcreptData(String fromDate, String toDate) {
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		StringBuffer sql = new StringBuffer();
		
		sql.append("select ledger_ahead_sheet.id,gcommencing,credit,debit,ledgerid from ledger_ahead_sheet ");
		sql.append("where gcommencing between '"+fromDate+"' and '"+toDate+"' ");
		sql.append("order by gcommencing ");
		
		try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			double dt = 0;
			double ct = 0;
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setId(rs.getInt(1));
				accounts.setCommencing(rs.getString(2));
				accounts.setCreditTotalx(DateTimeUtils.changeFormat(rs.getDouble(3)));
				accounts.setDebitTotalx(DateTimeUtils.changeFormat(rs.getDouble(4)));
				
				String ledgername = getLedgerAheadName(rs.getString(5));
				accounts.setLedgername(ledgername);
				
				ct = ct + rs.getDouble(3);
				dt = dt + rs.getDouble(4);
				
				accounts.setCreditTotal(ct);
				accounts.setDebitTotal(dt);
				
				list.add(accounts);
			}
			
			
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	private String getLedgerAheadName(String id) {
		PreparedStatement preparedStatement = null;
		String result=  "";
		String sql = "select name from ledger_ahead_master where id = "+id+" ";
		
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

	

	public int insertappointmentlog(String userid, String Charge, String appid,String previous) {
		int result=0;
		
		//initial setting
		  DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		   Calendar cal = Calendar.getInstance();
		String date= dateFormat.format(cal.getTime());
		if(previous!=null){
		if(!previous.contains(".")){
			previous= previous+".00";
		}
		}
		if(Charge!=null){
			if(!Charge.contains(".")){
				Charge= Charge+".00";
			}
			}
	
		//
		PreparedStatement ps= null;
		
		try{
			String sql="insert into apm_appointment_type_chargemodify_log(charge,clientid,date_modified,chargeid,previouscharge) values(?,?,?,?,?)";
			ps= connection.prepareStatement(sql);
			ps.setString(1, Charge);
			ps.setString(2, userid);
			ps.setString(3, date);
			ps.setString(4, appid);
			ps.setString(5, previous);
			result=ps.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		return result;
	}

	public boolean check() {
		// TODO Auto-generated method stub
		return false;
	}

	public ArrayList<Accounts> getSdebtorList(String fromDate, String toDate,int aheadid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		StringBuffer sql = new StringBuffer();
		String dbtorledgerid = getCDebtorledgerid(aheadid);
		
		sql.append("select sum(debit),ledgerid,ledger_master.name,sum(credit) from ledger_sheet ");
		sql.append("inner join ledger_master on ");
		sql.append("ledger_master.id = ledger_sheet.ledgerid ");
		sql.append("where ledgerid in("+dbtorledgerid+")  ");
		sql.append("and gcommencing between '"+fromDate+"' and '"+toDate+"' group by ledgerid ");
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			double total = 0;
			double ctotal = 0;
			double dbaltotal = 0;
			double cbaltotal = 0;
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setDebitAmountx(DateTimeUtils.changeFormat(rs.getDouble(1)));
				accounts.setId(rs.getInt(2));
				accounts.setLedgername(rs.getString(3));
				accounts.setCreditTotalx(DateTimeUtils.changeFormat(rs.getDouble(4)));
				
				total = total + rs.getDouble(1);
				ctotal = ctotal +rs.getDouble(4);
				accounts.setTotalAmountx(DateTimeUtils.changeFormat(total));
				accounts.setCredurAmountx(DateTimeUtils.changeFormat(ctotal));
				
				double dbal = 0;
				double cbal = 0;
				double d = rs.getDouble(1);
				double c = rs.getDouble(4);
				if(d>c){
					dbal = d - c;
				}else{
					cbal = c - d;
				}
				
				dbaltotal = dbaltotal + dbal;
				cbaltotal = cbaltotal + cbal;
				
				accounts.setDbaltotalx(DateTimeUtils.changeFormat(dbaltotal));
				accounts.setCbaltotalx(DateTimeUtils.changeFormat(cbaltotal));
				
				accounts.setDbalx(DateTimeUtils.changeFormat(dbal));
				accounts.setCbalx(DateTimeUtils.changeFormat(cbal));
				
				list.add(accounts);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return list;
	}

	private String getCDebtorledgerid(int id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT services FROM ledger_ahead_master where id = "+id+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
				
			}
			
			result = result.substring(0,result.length()-1);
			
		}catch(Exception e){
			
		}
		return result;
	}

	public ArrayList<Accounts> getScreditorList(String fromDate, String toDate) {
		PreparedStatement preparedStatement = null;
		ArrayList<Accounts>list = new ArrayList<Accounts>();
		StringBuffer sql = new StringBuffer();
		String dbtorledgerid = getCDebtorledgerid(1);
		
		sql.append("select sum(credit),ledgerid,ledger_master.name from ledger_sheet ");
		sql.append("inner join ledger_master on ");
		sql.append("ledger_master.id = ledger_sheet.ledgerid ");
		sql.append("where ledgerid in("+dbtorledgerid+")  ");
		sql.append("and gcommencing between '"+fromDate+"' and '"+toDate+"' group by ledgerid ");
		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			double total = 0;
			while(rs.next()){
				Accounts accounts = new Accounts();
				accounts.setDebitAmountx(DateTimeUtils.changeFormat(rs.getDouble(1)));
				accounts.setId(rs.getInt(2));
				accounts.setLedgername(rs.getString(3));
				
				total = total + rs.getDouble(1);
				accounts.setTotalAmountx(DateTimeUtils.changeFormat(total));
				
				list.add(accounts);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return list;
	}

	public ArrayList<Master> getDebitheadList(String fromDate, String toDate) {
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list = new ArrayList<Master>();
		String sql = "select id,name from ledger_ahead_master  ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			double cctotal = 0;
			double ddtotal = 0;
			while(rs.next()){
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				ArrayList<Accounts>sdebtorlist = getSdebtorList(fromDate,toDate,master.getId());
				
				master.setSdebtorlist(sdebtorlist);
				
				if(sdebtorlist.size()>0){
					Accounts aa = sdebtorlist.get(sdebtorlist.size()-1);
					master.setDebitx(aa.getTotalAmountx());
					master.setCreditx(aa.getCredurAmountx());
					
					double cbal = Double.parseDouble(master.getDebitx()) - Double.parseDouble(master.getCreditx());
					
					master.setCbalancex(DateTimeUtils.changeFormat(cbal));
					
					master.setDbaltotalx(aa.getDbaltotalx());
					master.setCbaltotalx(aa.getCbaltotalx());
					
					cctotal = cctotal + Double.parseDouble(aa.getCbaltotalx());
					ddtotal = ddtotal + Double.parseDouble(aa.getDbaltotalx());
				}
				
				master.setCctotal(DateTimeUtils.changeFormat(cctotal));
				master.setDdtotal(DateTimeUtils.changeFormat(ddtotal));
				list.add(master);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Master> getCreditAheadList(String fromDate, String toDate) {
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list = new ArrayList<Master>();
		String sql = "select id,name from ledger_ahead_master where cd = 1 ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				ArrayList<Accounts>sdebtorlist = getSdebtorList(fromDate,toDate,master.getId());
				
				master.setSdebtorlist(sdebtorlist);
				
				if(sdebtorlist.size()>0){
					Accounts aa = sdebtorlist.get(sdebtorlist.size()-1);
					master.setDebitx(aa.getTotalAmountx());
					master.setCreditx(aa.getCredurAmountx());
					
					double cbal = Double.parseDouble(master.getCreditx()) - Double.parseDouble(master.getDebitx());
					master.setCbalancex(DateTimeUtils.changeFormat(cbal));
				}
				list.add(master);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public String getpreviousCharge(int id,String columnname) {
		if(columnname==null){
			columnname="charges";
		}if(columnname.equals("")){
			columnname="charges";
		}
		String charge="";
		PreparedStatement preparedStatement = null;
		String sql = "select "+columnname+" from apm_appointment_type where id ='"+id+"' ";
		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs= preparedStatement.executeQuery();
			while(rs.next()){
				charge = rs.getString(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return charge;
	}

	public String getLedgerName(String id) {
		String name=""; 
		PreparedStatement ps= null;
		String sql="select id,name from ledger_master where id= '"+id+"'";
		try {
			ps = connection.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				name=rs.getString(2);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return name;
	}

public ArrayList<AppointmentType> getallApmtList1(){
	 ArrayList<AppointmentType> list= new ArrayList<AppointmentType>();
	 PreparedStatement ps= null;
	 try {
		String sql="SELECT id,name FROM apm_appointment_type group by name";
		ps=connection.prepareStatement(sql);
		ResultSet rs= ps.executeQuery();
		while(rs.next()){
			AppointmentType apptype= new AppointmentType();
			apptype.setName(rs.getString(2));
			list.add(apptype);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return list;
}

public String getLedgerStr(String aheadname) {
	PreparedStatement preparedStatement = null;
	String result = "";
	String sql = "SELECT services FROM ledger_ahead_master where id = "+aheadname+" ";
	
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

public int getGroupCount() {
	PreparedStatement preparedStatement = null;
	int result = 0;
	String sql = "SELECT count(*) FROM ledger_group_head ";
	
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

public String getServiceidstr(int grpid) {
	PreparedStatement preparedStatement = null;
	String result = "";
	String sql = "SELECT services  FROM ledger_group_head where headid="+grpid+"  ";
	
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

public int saveheadgroupid(String servicestr, int grpid) {
	PreparedStatement preparedStatement = null;
	int result = 0;
	String sql = "insert into ledger_group_head(headid, services) values(?,?) ";
	
	try{
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, grpid);
		preparedStatement.setString(2, servicestr);
		
		result = preparedStatement.executeUpdate();
	}catch(Exception e){
		e.printStackTrace();
	}
	
	return result;
}

public boolean checkheadid(int grpid) {
	PreparedStatement preparedStatement = null;
	boolean result = false;
	String sql = "select * from ledger_group_head where headid = "+grpid+" ";
	
	try{
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		if(rs.next()){
			result = true;
			
		}
		
	}catch(Exception e){
		
	}
	return result;
}

public int updateServicestr(int grpid, String servicestr) {
	PreparedStatement preparedStatement = null;
	int result = 0;
	String sql = "update ledger_group_head set services='"+servicestr+"' where headid="+grpid+"";
	
	try{
		preparedStatement = connection.prepareStatement(sql);
		result = preparedStatement.executeUpdate();
	}catch(Exception e){
		e.printStackTrace();
	}
	return result;
}

	
public String getChargebyid(int id, String columnname) {
	PreparedStatement preparedStatement = null;
	String result ="";
	String sql = "select "+columnname+" from apm_appointment_type where id = "+id+" ";
	
	try{
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		if(rs.next()){
			result = rs.getString(1);
			
		}
		
	}catch(Exception e){
		
	}
	return result;
}

public int deletetodayBedShiftingcharge(int id, String today, LoginInfo loginInfo) {
	PreparedStatement preparedStatement = null;
	int result = 0;
	//String sql = "delete from apm_invoice_assesments where ipdid ="+id+" and commencing='"+today+"' and masterchargetype='Bed Charge' ";
	
	try{
		StringBuffer buffer = new StringBuffer();
		buffer.append("delete from apm_invoice_assesments where ipdid ="+id+" and commencing='"+today+"' ");
		if(loginInfo.getIskunal()==1){
			buffer.append("and masterchargetype='Bed Charges' ");
		}else{
			buffer.append("and masterchargetype='Bed Charge' ");
		}
		preparedStatement = connection.prepareStatement(buffer.toString());
		result = preparedStatement.executeUpdate();
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}


public ArrayList<Master> getStdOnoffChargeListDiffWard(String wardid, String tpid,
		String payee, String ipdid) {
	ArrayList<Master> list= new ArrayList<Master>();
	
	try {
		if(payee==null){
			payee="Client";
		}
		if(payee.equals("Client") || payee.equals("Self")){
			 tpid="0";
		}
		
		String sql="select id,name,charges,tpid from apm_appointment_type where  wardid="+wardid+" and stdcharge=1 and onoff=1 ";
		StringBuffer buffer= new StringBuffer();
		buffer.append(" select apm_appointment_type.id,apm_appointment_type.name,apm_appointment_type.charges,tpid from apm_std_onoff_charge ");
		buffer.append(" inner join apm_appointment_type on apm_appointment_type.id=apm_std_onoff_charge.chargeid  ");
		buffer.append("   where  apm_appointment_type.wardid='"+wardid+"' and apm_appointment_type.stdcharge=1 and apm_appointment_type.onoff=1 and apm_std_onoff_charge.ipdid='"+ipdid+"'  ");
		buffer.append("    ");
		PreparedStatement ps=connection.prepareStatement(buffer.toString());
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			  
			 Master master=new Master();
			 master.setId(rs.getInt(1));
			 master.setName(rs.getString(2));
			 master.setCharge(rs.getString(3));
			 master.setTpid(""+rs.getString(4));
			 master.setWardid(wardid);
			 master.setFromOldWard(true);
			 list.add(master);
		}
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	
	return list;
}

public String getChargeAmountFromId(int aptid) {
	String rate="0";
	PreparedStatement preparedStatement = null;
	String sql =  "select  charges from apm_appointment_type  where id="+aptid+" ";
	
	try{
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()){
			rate=rs.getString(1);
		}
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	
	return rate;
}

@Override
public int validateCodeDuplicate(String code, String id) {
	int res =0;
	try {
		String sql ="select code from apm_appointment_type where code=? and id!=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, code);
		preparedStatement.setString(2, id);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			res =1;
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}

public boolean checkChargeInNDS(String ndsId, String tpid) {
	boolean flag = false;
	try {
		String sql="select * from non_discount_services where ids like '%,"+ndsId+",%' and payer='"+tpid+"' ";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			flag = true;
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return flag;
}

@Override
public String getnameFromId(String id) {
	// TODO Auto-generated method stub
	
	 PreparedStatement pmt=null;
	 String name="";
	 
	try {
		String sql="select name from apm_newchargetype where id="+id+"";
		pmt=connection.prepareStatement(sql);
		ResultSet rs=pmt.executeQuery();
		while(rs.next()){
			name=rs.getString(1);
			}
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	
	return name;
}

@Override
public ArrayList<AppointmentType> getProcedureList(String cname) {
	// TODO Auto-generated method stub
	
	PreparedStatement pmt=null;
    ArrayList<AppointmentType>list=new ArrayList<AppointmentType>();
	
	try {
		 String sql="select id,name from apm_appointment_type where procedureid='"+cname+"' and isprocedured=1 ";
		 pmt=connection.prepareStatement(sql);
		 ResultSet rs=pmt.executeQuery();
		 
		 while(rs.next()){
			 AppointmentType apptype= new AppointmentType();
			 apptype.setId(rs.getInt(1));
			 apptype.setName(rs.getString(2));
			 
			 list.add(apptype);
			 }
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	
	return list;
   }

@Override
public String getprocedureid(String proceduretype) {
	// TODO Auto-generated method stub
	
	PreparedStatement pmt=null;
	String name="";
	 
	 try {
		 
		 String sql="select name from apm_newchargetype where id="+proceduretype+"";
	     pmt=connection.prepareStatement(sql);
		 ResultSet rs=pmt.executeQuery();
		 
			while(rs.next()){
				name=rs.getString(1);
				}
		 
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	
	return name;
}

@Override
public ArrayList<AppointmentType> getManasclinictpAppointmentTypeList(String selectedid, String whopay, boolean isot,
		String otprocedureplaned, String clinicstaff_id) {
	PreparedStatement preparedStatement = null;
	ArrayList<AppointmentType>list = new ArrayList<AppointmentType>();
	
	String sql = "";
	sql = "SELECT duration,name,id,charges FROM apm_appointment_type where clinic_name_id="+clinicstaff_id+" and tpid = 0 and chargeType = 'Appointment Charge' order by name ";
		
	
	try{
		
		preparedStatement = connection.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()){
			AppointmentType appointmentType = new AppointmentType();
			appointmentType.setDuration(rs.getString(1));
			String charge = rs.getString(4);
			appointmentType.setName(rs.getString(2));
			appointmentType.setId(rs.getInt(3));
			list.add(appointmentType);
		}
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	return list;
	
}

@Override
public ArrayList<AppointmentType> getAllSessionChargeList(String selecteddept_id,String client_id) {
ArrayList<AppointmentType>list=new ArrayList<AppointmentType>();
	
	try {
		
		StringBuffer buffer=new StringBuffer();
		buffer.append(" select id,apmtType from apm_invoice_assesments  where clientId='"+client_id+"' and masterchargetype= '"+selecteddept_id+"' ");
		
		/*
		 * if(!location.equals("0")){ buffer.append("where dept_id="+location+""); }
		 */
		buffer.append(" order by id desc");
		
		PreparedStatement ps=connection.prepareStatement(buffer.toString());
		ResultSet rs =ps.executeQuery();
		
		while(rs.next()){
			
			AppointmentType appointmentType=new AppointmentType();
			
			appointmentType.setId(rs.getInt(1));
			appointmentType.setName(rs.getString(2));
			
			list.add(appointmentType);
			
			}

		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	
	return list;
	
}

@Override
public String getLastopddatebyclientid(String clientId) {
	PreparedStatement pst=null;
	String date="";
	try {
		String sql="select commencing from apm_available_slot where clientId="+clientId+" order by id desc limit 0,1";
		pst=connection.prepareStatement(sql);
		ResultSet rs=pst.executeQuery();
		while(rs.next()) {
			date=rs.getString(1);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return date;
}

@Override
public int getavailableslotclientcount(String clientId) {
	PreparedStatement pst=null;
	int count=0;
	try {
		String sql="select opd_count from apm_patient where id="+clientId+"";
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
public int updateOpd_count(int i,String clientId) {
	PreparedStatement pst=null;
	int updt=0;
	try {
		
		String sql="update apm_patient set opd_count="+i+" where id="+clientId+" ";
		pst=connection.prepareStatement(sql);
	    updt=pst.executeUpdate();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return updt;
}

@Override
public String getChargeTypebyid(String id) {
	PreparedStatement pst=null;
	String chargetype="";
	try {
		String sql="select name from apm_newchargetype where id="+id+"";
		pst=connection.prepareStatement(sql);
		ResultSet rs=pst.executeQuery();
		while(rs.next()) {
			chargetype=rs.getString(1);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return chargetype;
}

@Override
public NotAvailableSlot getAdmissiondatebyid(String clientid) {
	PreparedStatement pst=null;
    NotAvailableSlot notav=new NotAvailableSlot();
	
	try {
		String sql="select admissiondsate,tpid,treatmentepisodeid from ipd_addmission_form where clientid="+clientid+" order by id desc limit 0,1";
		pst=connection.prepareStatement(sql);
		ResultSet rs=pst.executeQuery();
		while(rs.next()) {
			notav.setAdmitdate(rs.getString(1));
		    notav.setWhopay(rs.getString(2));
		    notav.setTreatmentEpisodeId(rs.getString(3));
		    String dischargedate=getDischargeDatebyid(rs.getString(3));
		    notav.setDischargedate(dischargedate.split(" ")[0]);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return notav;
}

private String getDischargeDatebyid(String treatepid) {
	PreparedStatement pst=null;
	String dischargedate="";
	try {
		String sql="select dischargedate from apm_treatment_episode where id="+treatepid+"";
		pst=connection.prepareStatement(sql);
		ResultSet rs=pst.executeQuery();
		while(rs.next()) {
			dischargedate=rs.getString(1);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return dischargedate;}

@Override
public int updateAppointmentType1(AppointmentType appointmentType, int id) {
	PreparedStatement preparedStatement = null;
	int result = 0;
	if(appointmentType.getColumnchargename()==null){
		appointmentType.setColumnchargename("charges");
	}if(appointmentType.getColumnchargename().equals("")){
		appointmentType.setColumnchargename("charges");
	}
	String sql = "update apm_appointment_type set name = ?,description = ?,category = ?,duration = ?,"+appointmentType.getColumnchargename()+" = ?,location = ?,chargeType = ?,reportField = ?,basecharge=?, shareablecharge=?,code=?,discountAmt=?,originalAmt=? where id = "+id+"";
	
	try{
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, appointmentType.getName());
		preparedStatement.setString(2, appointmentType.getDescription());
		preparedStatement.setString(3, appointmentType.getCategory());
		preparedStatement.setString(4, appointmentType.getDuration());
		preparedStatement.setString(5, appointmentType.getCharges());
		preparedStatement.setString(6, appointmentType.getLocation());
		if(appointmentType.getChargeType().equals("PATHLAB")|| appointmentType.getChargeType().equals("RADIOLOGY")){
			appointmentType.setChargeType("INVESTIGATION");
		}
		preparedStatement.setString(7, appointmentType.getChargeType());
		preparedStatement.setString(8, appointmentType.getReportField());
		preparedStatement.setString(9, appointmentType.getBasecharge());
		preparedStatement.setString(10, appointmentType.getShareablecharge());
		preparedStatement.setString(11, appointmentType.getCode());
		preparedStatement.setDouble(12, appointmentType.getDiscountAmt());
		preparedStatement.setString(13, appointmentType.getActualAmount());
		
		result = preparedStatement.executeUpdate();
		
		
		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	
	
	return result;

}

@Override
public ArrayList<AppointmentType> getPayerlist(int id) {
    ArrayList<AppointmentType>list=new ArrayList<AppointmentType>();
	
	try {
		
		StringBuffer buffer=new StringBuffer();
		buffer.append(" select id,discountShare from apm_appointment_type  where chargeid='"+id+"' ");
		
		
		PreparedStatement ps=connection.prepareStatement(buffer.toString());
		ResultSet rs =ps.executeQuery();
		
		while(rs.next()){
			
			AppointmentType appointmentType=new AppointmentType();
			
			appointmentType.setId(rs.getInt(1));
			appointmentType.setDiscountShare(rs.getString(2));
			
			list.add(appointmentType);
			
			}

		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	
	return list;
	
}

@Override
public String getApppointmentIdbyDocid(String docid, String apmt_name, String tpid) {
	PreparedStatement pst=null;
	String apmt_type="";
	try {
		String sql="select id from apm_appointment_type where name='"+apmt_name+"' and docid='"+docid+"' and tpid='"+tpid+"'";
		pst=connection.prepareStatement(sql);
		ResultSet rs=pst.executeQuery();
		while(rs.next()) {
			apmt_type=rs.getString(1);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return apmt_type;
}

@Override
public double getavailableslotCharges(String clientid) {

		PreparedStatement pst=null;
		double charge=0;
		try {
			String sql="select charge from apm_available_slot where clientId="+clientid+"";
			pst=connection.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				charge=rs.getDouble(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return charge;
	
}

@Override
public String getLastChargedappointment(String clientid) {

	PreparedStatement pst=null;
	String date="";
	try {
		String sql="select commencing from apm_available_slot where clientId='"+clientid+"' and charge !=0 order by id desc limit 0,1;";
		pst=connection.prepareStatement(sql);
		ResultSet rs=pst.executeQuery();
		while(rs.next()) {
			date=rs.getString(1);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return date;

}

}
