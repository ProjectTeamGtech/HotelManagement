package com.apm.Support.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.apm.Master.eu.entity.Master;
import com.apm.Registration.eu.entity.Clinic;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.Support.eu.bi.UserAdministartionDAO;
import com.apm.Support.eu.entity.Support;
import com.apm.common.eu.blogic.jdbc.JDBCBaseDAO;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.main.common.constants.Constants;

public class JDBCUserAdministration extends JDBCBaseDAO implements UserAdministartionDAO{

	public JDBCUserAdministration(Connection connection){
		this.connection = connection;
	}

	public ArrayList<Clinic> getAllClinic() {
		ArrayList<Clinic> list= new ArrayList<Clinic>();
		PreparedStatement ps= null;
		Connection connection2= null;
		StringBuffer sql= new StringBuffer();
		sql.append("select clinic_name,clinic_userid,ip_address,subscription_expiry_date,subscription_date,is_active  from   all_client_details ");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection2=DriverManager.getConnection("jdbc:mysql://"+Constants.SUPPORT_HOST+":3306/admin",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			ps= connection2.prepareStatement(sql.toString());
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				Clinic clinic= new Clinic();
				clinic.setClinicName(rs.getString(1));
				clinic.setClinicID(rs.getString(2));
				clinic.setIpaddr(rs.getString(3));
				if(rs.getString(4)!=null){
					clinic.setExpirey_date(DateTimeUtils.getCommencingDate1(rs.getString(4)));
				}else {
					clinic.setExpirey_date("");
				}
				clinic.setSubscription_date(DateTimeUtils.getCommencingDate1(rs.getString(5)));
				/*clinic.setActive_clinic(getactiveInactive(clinic.getIpaddr(),clinic.getClinicID()));*/
				clinic.setActive_clinic(rs.getBoolean(6));
				list.add(clinic);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int giveDeadLineToClient(String userid, String date,String type) {
		PreparedStatement ps= null;
		String dbcol="subscription_expiry_date";
		if(type!=null){
			if(type.equals("subdt")){
				dbcol="subscription_date";
			}
		}
		int res=0;
		try {
			String sql="update all_client_details set "+dbcol+"='"+date+"' where clinic_userid ='"+userid+"'";
			ps= connection.prepareStatement(sql);
			res= ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int setClinicActiveDeactive(Clinic clinic, String active) {
		Connection connection2= null;
		PreparedStatement ps= null;
		String sql="";
		int x=0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection2=DriverManager.getConnection("jdbc:mysql://"+clinic.getIpaddr()+":3306/"+clinic.getClinicID(),""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			 sql=" update  apm_user set active_clinic='"+active+"' where id=1";
			 ps=connection2.prepareStatement(sql);
			  x= ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return x;
	}
	
	private boolean getactiveInactive(String ip,String clinicid) throws SQLException{
		boolean res=false;
		Connection connection2= null;
		PreparedStatement ps= null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String test="";
			connection2=DriverManager.getConnection("jdbc:mysql://"+ip+":3306/"+clinicid+"",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			if(connection2==null){
				connection2=DriverManager.getConnection("jdbc:mysql://"+ip+":3306/"+clinicid+"",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			}
			String sql="select active_clinic from  apm_user where id =1 ";
			ps= connection2.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				res=rs.getBoolean(1);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			ps.close();
			
			connection2.close();
		}
		return res;
	}

	public int sendToMainSupport(String clinicid, String userid, String qtype, String query, String datetime,String dept,String fullname,String mobile,String module,String issuetype, String almob) {
			
		Connection connection2=null;
		PreparedStatement ps= null;
		int res=0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection2=DriverManager.getConnection("jdbc:mysql://"+Constants.SUPPORT_HOST+":3306/"+Constants.SUPPORT_DB,""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			//connection2=DriverManager.getConnection("jdbc:mysql://localhost:3306/pcsadmin",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			String sql="insert into support_data (clinic_id,userid,datetime,query_type,query,department,usernamefull,mobno,module_name,issue_type,altmobno)  value(?,?,?,?,?,?,?,?,?,?,?) ";
			ps= connection2.prepareStatement(sql);
			ps.setString(1, clinicid);
			ps.setString(2, userid);
			ps.setString(3, datetime);
			ps.setString(4, qtype);
			ps.setString(5, query);
			ps.setString(6, dept);
			ps.setString(7, fullname);
			ps.setString(8, mobile);
			ps.setString(9, module);
			ps.setString(10, issuetype);
			ps.setString(11,DateTimeUtils.isNull(almob));
			res=ps.executeUpdate();
			if(res>0){
				ResultSet rs2=ps.getGeneratedKeys();
				while(rs2.next()){
					res=rs2.getInt(1);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<UserProfile> getAllSupportRequest(String clinicid, String userid, String status,Pagination pagination) {
		ArrayList<UserProfile> list= new ArrayList<UserProfile>();
		PreparedStatement ps= null;
		Connection connection2 = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			
			StringBuffer buffer=  new StringBuffer();
			buffer.append(" select datetime,query_type,query,status,isfixable,department,id,userid,executive,remark,is_chargable,time_taken,usernamefull,fixed_date  from support_data  ");
			buffer.append("  where  clinic_id='"+clinicid+"' order by id desc ");
			buffer.append("   ");
			buffer.append("   ");
			
			String sql= pagination.getSQLQuery(buffer.toString());
			ps= connection.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				UserProfile user= new UserProfile();
				user.setId(rs.getInt(7));
				user.setQuery_type(rs.getString(2));
				user.setQuery(rs.getString(3));
				user.setStatus(String.valueOf(rs.getInt(4)));
				user.setIsfixable(rs.getBoolean(5));
				String tempdate[]=rs.getString(1).split(" ");
				String date=DateTimeUtils.getCommencingDate1(tempdate[0]);
				String time=tempdate[1];
				user.setDate(date);
				user.setTime(time);
				user.setUsr(rs.getString(8));
				user.setHandledby(rs.getString(9));
				user.setRemark(rs.getString(10));
				user.setMsgread(ismsgRead(rs.getString(7), "readbyuser"));
				user.setChargable(rs.getBoolean(11));
				user.setTimeTaken(rs.getString(12));
				user.setAttached(isFilesent(""+rs.getInt(7)));
				user.setFullname(rs.getString(13));
				user.setFixedDate(rs.getString(14));
				list.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<UserProfile> getAllSupportRequestToAdmin(String clinicid, String userid, String status,String fromdate,String todate,Pagination pagination,String assignTo,String querytype) {
		ArrayList<UserProfile> list= new ArrayList<UserProfile>();
		PreparedStatement ps= null;
		Connection connection2 = null;
		fromdate= DateTimeUtils.getCommencingDate1(fromdate)+" 00:00:00";
		todate= DateTimeUtils.getCommencingDate1(todate)+" 23:59:59";
		try {
			/*Class.forName("com.mysql.jdbc.Driver");
			connection2=DriverManager.getConnection("jdbc:mysql://139.162.51.34:3306/demo",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			*/
			StringBuffer buffer=  new StringBuffer();
			
			buffer.append(" select datetime,query_type,query,status,isfixable,department,id,clinic_id,userid,executive,remark,priority,module_name,issue_type,altmobno,mobno,is_chargable,time_taken,usernamefull,fixed_date  from support_data  ");
			buffer.append(" where datetime between '"+fromdate+"' and '"+todate+"' ");
			if(!status.equals("")){
				buffer.append("  and  status='"+status+"' ");
			}
			if(!clinicid.equals("")){
				buffer.append(" and clinic_id='"+clinicid+"'   ");
			}
			if(!DateTimeUtils.isNull(assignTo).equals("")){
				buffer.append(" and executive='"+assignTo+"'   ");
			}
			
			if(!querytype.equals("")){
				buffer.append(" and query_type='"+querytype+"'   ");
			}
			buffer.append("  order by id desc ");
			
			String sql= pagination.getSQLQuery(buffer.toString());
			ps= connection.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				UserProfile user= new UserProfile();
				user.setId(rs.getInt(7));
				user.setQuery_type(rs.getString(2));
				user.setQuery(rs.getString(3));
				user.setStatus(String.valueOf(rs.getInt(4)));
				user.setIsfixable(rs.getBoolean(5));
				String tempdate[]=rs.getString(1).split(" ");
				String date=DateTimeUtils.getCommencingDate1(tempdate[0]);
				String time=tempdate[1];
				user.setDate(date);
				user.setTime(time);
				user.setClinicname(rs.getString(8));
				user.setName(rs.getString(9));
				user.setHandledby(rs.getString(10));
				user.setExecutive(getSupportExecutiveName(user.getHandledby()));
				user.setRemark(rs.getString(11));
				user.setMsgread(ismsgRead(rs.getString(7), "readbyadmn"));
				user.setPriorityclient(rs.getString(12));
				user.setModulename(rs.getString(13));
				user.setIssuetype(rs.getString(14));
				user.setAltMobNo(DateTimeUtils.isNull(rs.getString(15)));
				user.setMobile(DateTimeUtils.isNull(rs.getString(16)));
				user.setChargable(rs.getBoolean(17));
				user.setTimeTaken(DateTimeUtils.isNull(rs.getString(18)));
				user.setFullname(rs.getString(19));
				user.setFixedDate(rs.getString(20));
				user.setAttached(isFilesent(""+rs.getInt(7)));
				list.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private  boolean isFilesent(String ticketId){
		
		try {
			StringBuffer buffer= new StringBuffer();
			buffer.append(" select msg ,who,datetime,type from support_conversation where ticketid='"+ticketId+"' and type='File'   ");
			PreparedStatement ps= connection.prepareStatement(buffer.toString());
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	public int setUserreqStatus(String ticketid, String status) {
		int res=0;
		PreparedStatement ps= null;
		
		String sql="update support_data set status='"+status+"' where id='"+ticketid+"' ";
		try {
			ps= connection.prepareStatement(sql);
			res= ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<Support> getMsgList(String ticketid) {
		PreparedStatement ps= null;
		ArrayList<Support> list= new ArrayList<Support>();
		try {
			StringBuffer buffer= new StringBuffer();
			buffer.append(" select msg ,who,datetime,type from support_conversation where ticketid='"+ticketid+"' order by id asc limit 20   ");
			ps= connection.prepareStatement(buffer.toString());
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				Support support= new  Support();
				support.setMsgs(rs.getString(1));
				support.setWho(rs.getString(2));
				String date=rs.getString(3);
				if(date!=null){
					if(!date.equals("")){
						String dt[]= date.split(" ");
						dt[0]= DateTimeUtils.getCommencingDate1(dt[0]);
						date=dt[0]+" "+dt[1];
					}
				}
				support.setMsgtype(DateTimeUtils.isNull(rs.getString(4)));
				support.setDatetime(date);
				list.add(support);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public String getSupportExecutiveName(String id){
		try{
		String sql=" select name from support_excutives where id='"+id+"'   ";
		PreparedStatement ps= connection.prepareStatement(sql);
		ResultSet rs= ps.executeQuery();
		while(rs.next()){
			return rs.getString(1);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "";
	}
	
	public ArrayList<Support> getExcutiveList() {
		PreparedStatement ps= null;
		ArrayList<Support> list= new ArrayList<Support>();
		try {
			StringBuffer buffer= new StringBuffer();
			buffer.append(" select name from support_excutives    ");
			ps= connection.prepareStatement(buffer.toString());
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				Support support= new  Support();
				support.setSupport_executive(rs.getString(1));
				
				list.add(support);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public Support getSupportQueryData(String ticketid) {
		Support support= new Support();
		PreparedStatement ps= null;
		try {
			String sql=" select clinic_id,userid,datetime,query_type,query,usernamefull,mobno from support_data where id='"+ticketid+"'";
			ps= connection.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				support.setClinicid(rs.getString(1));
				support.setUserid(rs.getString(2));
				support.setDatetime(rs.getString(3));
				support.setQuerytype(rs.getString(4));
				support.setQuery(rs.getString(5));
				support.setUserName(rs.getString(6));
				support.setMobno(rs.getString(7));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return support;
	}

	public int sendMsg(String ticketid, String who, String msg, String datetime, String type) {
		int res=0;
		PreparedStatement ps= null;
		try {
			ps= connection.prepareStatement("insert into support_conversation (ticketid,who,msg,datetime,type) values(?,?,?,?,?) ");
			ps.setString(1, ticketid);
			ps.setString(2, who);
			ps.setString(3, msg);
			ps.setString(4, datetime);
			ps.setString(5, type);
			res=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int totalSupportRequest(String clinicid, String userid, String status, String fromdate, String todate,String assignTo,String querytype) {
		PreparedStatement ps= null;
		int res=0;
		fromdate= DateTimeUtils.getCommencingDate1(fromdate)+" 00:00:00";
		todate= DateTimeUtils.getCommencingDate1(todate)+" 23:59:59";
		
			StringBuffer buffer=  new StringBuffer();
			
			buffer.append(" select count(*) from support_data  ");
			buffer.append(" where datetime between '"+fromdate+"' and '"+todate+"' ");
			if(!status.equals("")){
				buffer.append("  and  status='"+status+"' ");
			}
			if(!clinicid.equals("")){
				buffer.append(" and clinic_id='"+clinicid+"'   ");
			}
			if(!DateTimeUtils.isNull(assignTo).equals("")){
				buffer.append(" and executive='"+assignTo+"'   ");
			}
			if(!querytype.equals("")){
				buffer.append(" and query_type='"+querytype+"'   ");
			}
			buffer.append("  order by id desc ");
		
		try {
			String sql=buffer.toString();
			ps= connection.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				res= rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return res;
		}

	public int setexcutiveSupport(String name, String ticketid) {
		PreparedStatement ps= null;
		int res=0;
		try {
			String sql="update support_data set executive=? where id="+ticketid+" ";
			ps= connection.prepareStatement(sql);
			ps.setString(1, name);
			
			res= ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int countOfUserRequest(String clinicid, String userid, String status) {
		PreparedStatement ps= null;
		int res=0;
		try {
			StringBuffer buffer=  new StringBuffer();
			buffer.append(" select count(*) from support_data  ");
			buffer.append("  where  clinic_id='"+clinicid+"' order by id desc ");
			ps= connection.prepareStatement(buffer.toString());
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				res=rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int saveSupportRemark(String remark, String ticketid,String priority,String chargable,String timetaken) {
		PreparedStatement ps= null;
		int res=0;
		if(priority==null||priority.equals("")){
			priority="0";
		}
		try {
			ps= connection.prepareStatement(" update support_data set remark =?,priority=?,is_chargable=?,time_taken=?    where id="+ticketid+"");
			ps.setString(1, remark);
			ps.setString(2, priority);
			ps.setString(3, chargable);
			ps.setString(4, timetaken);
			 res= ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	private boolean ismsgRead(String ticketid,String readby){
		boolean flag=false;
		PreparedStatement ps= null;
		try {
			ps= connection.prepareStatement(" select *  from support_conversation where "+readby+"='0' and ticketid='"+ticketid+"' limit 1 ");
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				flag=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public int setReadAllMsg(String ticketid, String column) {
		PreparedStatement ps=null;
		int res=0;
		try {
			ps= connection.prepareStatement("update support_conversation set "+column+" ='1' where ticketid='"+ticketid+"' ");
			res=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public Clinic getClinicDetailsFromMaster(String clinicid) {
		Clinic clinic= new Clinic();
		try {
			PreparedStatement ps= connection.prepareStatement(" select clinic_name,ip_address,subscription_date from all_client_details where clinic_userid='"+clinicid+"' ");
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				clinic.setClinicName(rs.getString(1));
				clinic.setIpaddr(rs.getString(2));
				clinic.setSubscription_date(rs.getString(3));
				clinic.setClinicID(clinicid);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clinic;
	}

	public String userListFromThierIp(Clinic clinic) {
		String list= "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection connectionClinicWise=DriverManager.getConnection("jdbc:mysql://"+clinic.getIpaddr()+":3306/"+clinic.getClinicID()+"",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			PreparedStatement ps=connectionClinicWise.prepareStatement(" select initial,firstname,lastname,userid,mobile  from apm_user  ");
			ResultSet rs=ps.executeQuery();
			StringBuffer buffer= new StringBuffer();
			buffer.append("<select  name='useres' id='useres' onchange='getusermobile(this)' class='form-control chosen-select'> ");
			buffer.append("<option value='()' >Select User</option>");
			while (rs.next()) {
				buffer.append("<option value='"+rs.getString(4)+"("+rs.getString(5)+")'>"+rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+"</option>");
			}
			buffer.append("</select>");
			list=buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public UserProfile getUserDetailsForSupport(String userid, String clinicid,Clinic clinic) {
		UserProfile userProfile= new UserProfile();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection connectionClinicWise=DriverManager.getConnection("jdbc:mysql://"+clinic.getIpaddr()+":3306/"+clinic.getClinicID()+"",""+Constants.DB_USER+"",""+Constants.DB_PWD+"");
			PreparedStatement ps=connectionClinicWise.prepareStatement(" select initial,firstname,lastname,userid,mobile ,jobtitle from apm_user  ");
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				userProfile.setFullname(""+rs.getString(1)+". "+rs.getString(2)+" "+rs.getString(3));
				userProfile.setMobile(rs.getString(4));
				userProfile.setJobtitle(rs.getString(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userProfile;
	}

	public int setClinicActivDeactiveMaster(String clinicid, String status) {
		int res=0;
		try {
			PreparedStatement ps= connection.prepareStatement(" update all_client_details set is_active='"+status+"' where clinic_userid='"+clinicid+"'");
			res=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<Master> getModuleMasterList() {
		ArrayList<Master> list =new ArrayList<Master>();
		try {
			PreparedStatement ps=connection.prepareStatement("select id, name from his_modules ");
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				Master master= new Master();
				master.setId(rs.getInt(1));
				master.setName(""+rs.getString(2));
				list.add(master);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<UserProfile> relesedNotesList(String fromDate, String toDate) {
		ArrayList<UserProfile> list=new ArrayList<UserProfile>();
		fromDate=DateTimeUtils.getCommencingDate1(fromDate)+" 00:00:00";
		toDate=DateTimeUtils.getCommencingDate1(toDate)+" 23:59:59";
		try {
			StringBuffer buffer=new StringBuffer();
			buffer.append(" select * from admin.apm_relesed_notes where datetime between '"+fromDate+"' and '"+toDate+"' order by id desc ");
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				UserProfile userProfile= new UserProfile();
				userProfile.setFilename(DateTimeUtils.isNull(rs.getString("filename")));
				userProfile.setWarname(DateTimeUtils.isNull(rs.getString("warname")));
				userProfile.setDatetime(DateTimeUtils.getCommencingDate1(rs.getString("datetime").split(" ")[0])+" "+rs.getString("datetime").split(" ")[1]);
				userProfile.setId(rs.getInt("id"));
				list.add(userProfile);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int saveReleasedNotes(UserProfile userProfile) {
		int res=0;
		try {
			String sql="insert into admin.apm_relesed_notes(filename,warname,datetime) values(?,?,?)";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, userProfile.getFilename());
			ps.setString(2, userProfile.getWarname());
			ps.setString(3, userProfile.getDatetime());
			res=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public void saveFixedDate(String ticketid, String date) {
		try {
			String sql="update support_data set fixed_date='"+date+"' where id='"+ticketid+"'";
			PreparedStatement ps=connection.prepareStatement(sql);
			int res=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
