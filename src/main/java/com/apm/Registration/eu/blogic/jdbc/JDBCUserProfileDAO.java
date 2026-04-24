package com.apm.Registration.eu.blogic.jdbc;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.apm.Ambulance.eu.entity.Duty;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.DiaryManagement;
import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.Inventory.eu.bi.InventoryVendorDAO;
import com.apm.Inventory.eu.blogic.jdbc.JDBCInventoryVendorDAO;
import com.apm.Master.eu.bi.MasterDAO;
import com.apm.Master.eu.blogic.jdbc.JDBCMasterDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Payroll.eu.bi.PayrollEmployeeDAO;
import com.apm.Payroll.eu.blogic.jdbc.JDBCPayrollEmployeeDAO;
import com.apm.Pharmacy.eu.bi.PharmacyDAO;
import com.apm.Pharmacy.eu.blogic.jdbc.JDBCPharmacyDAO;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.common.eu.blogic.jdbc.JDBCBaseDAO;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Encryption;
import com.apm.common.utils.Pagination;
import com.apm.common.web.common.helper.Access;
import com.apm.common.web.common.helper.LoginInfo;

public class JDBCUserProfileDAO extends JDBCBaseDAO implements UserProfileDAO {

	
public JDBCUserProfileDAO(Connection connection){
		
		this.connection = connection;
	}

	
	public ArrayList<UserProfile> getUserProfileList(Pagination pagination,String searchText,int userid,int usertype,String jobtitle) {
		PreparedStatement preparedStatement = null;
		ArrayList<UserProfile>list = new ArrayList<UserProfile>();
	
//		String sql = "";
//		if(usertype==2){
//			if(DateTimeUtils.isNull(pagination.sortColumn).equals("")){
//				sql = "select id,firstname,lastname,jobtitle,discription,email,mobile,userid,islogin from apm_user where (firstname like('%"+searchText+"%') or jobtitle like('%"+searchText+"%'))  and usertype = 4 order by id desc" ;
//			}else{
//				sql = "select id,firstname,lastname,jobtitle,discription,email,mobile,userid,islogin from apm_user where (firstname like('%"+searchText+"%') or jobtitle like('%"+searchText+"%'))  and usertype = 4";
//			}
//			
//		}else{
//			if(DateTimeUtils.isNull(pagination.sortColumn).equals("")){
//				sql = "select id,firstname,lastname,jobtitle,discription,email,mobile,userid,islogin from apm_user where (firstname like('%"+searchText+"%') or jobtitle like('%"+searchText+"%'))  and usertype = 4 and id="+userid+" order by id desc" ;
//			}else{
//				sql = "select id,firstname,lastname,jobtitle,discription,email,mobile,userid,islogin from apm_user where (firstname like('%"+searchText+"%') or jobtitle like('%"+searchText+"%'))  and usertype = 4 and id="+userid+"";
//			}
//		}
		StringBuffer sql=new StringBuffer();
		
		String temp[] = searchText.split(" ");
		
		sql.append("select id,firstname,lastname,jobtitle,discription,email,mobile,userid,islogin,cliniclogo from apm_user "); 
		
		sql.append("where usertype = 4 ");
		
		if(temp.length==1){
			sql.append(" and (firstname like('"+temp[0]+"%') or lastname like('"+temp[0]+"%') or userid like('"+temp[0]+"%') ) ");
			
		}else if(temp.length>1){
			sql.append(" and (firstname like('"+temp[0]+"%') and lastname like('"+temp[1]+"%'))  ");
		} 
		//sql.append("where firstname like('%"+searchText+"%') ");
		if(!(jobtitle.equals("") || jobtitle.equals("0"))){
			sql.append("and jobtitle='"+jobtitle+"'");
		}
		
		sql.append(" order by id desc");
		
		
		String sql1 = pagination.getSQLQuery(sql.toString());
		
		try{
			MasterDAO masterDAO = new JDBCMasterDAO(connection);
			preparedStatement = connection.prepareStatement(sql1);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				UserProfile userprofile = new UserProfile();
				userprofile.setId(rs.getInt(1));
				userprofile.setFirstname(rs.getString(2));
				userprofile.setLastname(rs.getString(3));
				userprofile.setJobtitle(rs.getString(4));
				Master master = new Master();
				if(rs.getString(4).equals("Pharmacist")){
					String location = getPharmacyLocationFromUserId(rs.getString(8));
					String location1 = getConditionData(location);
					userprofile.setDiscription(location1);
				}else{
					master = masterDAO.getDisciplineData(rs.getString(5));
					userprofile.setDiscription(master.getDiscipline());
				}
				
				String globalaccess = getGlobalAccessStatus(rs.getString(4));
				userprofile.setGlobalaccess(globalaccess);
				userprofile.setEmail(rs.getString(6));
				userprofile.setMobile(rs.getString(7));
				userprofile.setUserid(rs.getString(8));
				userprofile.setIslogin(rs.getString(9));
				userprofile.setUserImageFileName(rs.getString(10));
				list.add(userprofile);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	


	private String getGlobalAccessStatus(String string) {
		String res="2";
		try {
			String sql="select globalaccess from apm_role_access where usertype='"+string+"' ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs =ps.executeQuery();
			while(rs.next()){
				res =rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}


	public String getConditionData(String location) {
		String res="";
		try {
			String sql="select name from apm_condition where id='"+location+"' ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs =ps.executeQuery();
			while(rs.next()){
				res =rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}


	private String getPharmacyLocationFromUserId(String string) {
			String res="";
			try {
				String sql="select location from apm_pharmacy_user where userid='"+string+"' ";
				PreparedStatement ps=connection.prepareStatement(sql);
				ResultSet rs =ps.executeQuery();
				while(rs.next()){
					res =rs.getString(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return res;
	}


	public int getUserprofileCount(String searchText,int userid,int usertype,String jobtitle) throws Exception {
		PreparedStatement preparedStatement = null;
		int result = 0;
//		String sql = "";
//		if(usertype==2){
//			sql = "select count(*) from apm_user where (firstname like('%"+searchText+"%') or jobtitle like('%"+searchText+"%'))  and usertype = 4";
//		}else{
//			sql = "select count(*) from apm_user where (firstname like('%"+searchText+"%') or jobtitle like('%"+searchText+"%'))  and usertype = 4 and id="+userid+" ";
//		}
		StringBuffer sql=new StringBuffer();
		
		String temp[] = searchText.split(" ");
		
		sql.append("select count(*) from apm_user "); 
		
		sql.append("where usertype = 4 ");
		
		if(temp.length==1){
			sql.append(" and (firstname like('"+temp[0]+"%') or lastname like('"+temp[0]+"%')) ");
			
		}else if(temp.length>1){
			sql.append(" and (firstname like('"+temp[0]+"%') and lastname like('"+temp[1]+"%'))  ");
		} 
		//sql.append("where firstname like('%"+searchText+"%') ");
		if(!(jobtitle.equals("") || jobtitle.equals("0"))){
			sql.append("and jobtitle='"+jobtitle+"'");
		}
		
		sql.append(" order by id desc");
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
	
public int saveUserprofile(UserProfile userprofile,int clinicid) {
		
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_user(firstname,lastname,initial,jobtitle,discription,registerno,usergroup,isapractitioner,appointmentdiary,diarycolor,diarycolumnposition,compressionrate,onlinename,isavailableonline," +
				"loginsystem,userid,password,lastchanged,changefre,appointmentbookingcontem,appointmentbookingdnatem,email,mobile,apmremote,onlinebooking,usertype,clinicid,dnaCharge,completeAppCharge,ipdshare,surgeonshare,isvisitingdoctor)" +
				" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			/*preparedStatement.setInt(1, userprofile.getId());*/
			preparedStatement.setString(1, userprofile.getFirstname());
			preparedStatement.setString(2, userprofile.getLastname());
			preparedStatement.setString(3, userprofile.getInitial());
			preparedStatement.setString(4, userprofile.getJobtitle());
			/*preparedStatement.setString(4, userprofile.getDiscription());*/
			preparedStatement.setString(5, userprofile.getDiciplineName());
			preparedStatement.setString(6, userprofile.getRegisterno());
			preparedStatement.setString(7,userprofile.getUsergroup());
			preparedStatement.setBoolean(8, userprofile.isUsercheck());
			preparedStatement.setBoolean(9,userprofile.isAppointmentdiary());
			preparedStatement.setString(10,"#"+userprofile.getDiarycolor());
			preparedStatement.setString(11,userprofile.getDiarycolumnposition());
			preparedStatement.setInt(12,userprofile.getCompressionrate());
			preparedStatement.setString(13,userprofile.getOnlinename());
			preparedStatement.setBoolean(14,userprofile.isIsavailableonline());
			preparedStatement.setBoolean(15, userprofile.isLoginsystem());
			preparedStatement.setString(16, userprofile.getUserid());
			
			String encPassword = Encryption.encryptSHA(userprofile.getPassword());
			preparedStatement.setString(17,encPassword);
			/*preparedStatement.setString(16, userprofile.getPassword());*/
			preparedStatement.setString(18, userprofile.getLastchanged());
			preparedStatement.setInt(19, userprofile.getChangefre());
			preparedStatement.setString(20,userprofile.getAppointmentbookingcontem());
			preparedStatement.setString(21,userprofile.getAppointmentbookingdnatem());
			preparedStatement.setString(22, userprofile.getEmail());
			preparedStatement.setString(23, userprofile.getMobile());
			preparedStatement.setBoolean(24, userprofile.isApmremote());
			preparedStatement.setBoolean(25, userprofile.isOnlinebooking());
			preparedStatement.setInt(26,4);
			
			/*boolean usercheck;
			usercheck=userprofile.isUsercheck();
			
			if(usercheck)
			{
				preparedStatement.setInt(26,4);
			}
			else
			{
				preparedStatement.setInt(26,6);
			}*/
			
			preparedStatement.setInt(27, clinicid);
			preparedStatement.setInt(28, userprofile.getDnaCharge());
			preparedStatement.setInt(29, userprofile.getCompAppCharge());
			preparedStatement.setInt(30, userprofile.getIpdCharge());
			preparedStatement.setInt(31, userprofile.getSurgeonCharge());
			preparedStatement.setBoolean(32, userprofile.isVisitingdoctor());
			
			
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	
		return result;
	}

	public int deleteUserprofile(int selectedid) {
		int result=0;
		try{
			/*String sql="delete from userprofile where id= " +selectedid;*/
			String sql="delete from apm_user where id= " +selectedid;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			result= preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	
	public int updateUserprofile(UserProfile userprofile)
	{
		
		PreparedStatement preparedStatement = null;
		int result = 0;
		/*String sql = "UPDATE userprofile INNER JOIN usersetup ON userprofile.id = usersetup.userid set userprofile.fullname=?,userprofile.initial=?,userprofile.jobtitle=?,userprofile.discription=?,userprofile.registerno=?,userprofile.usergroup=?," +
				" usersetup.isapractitioner=?,usersetup.appointmentdiary=?,usersetup.diarycolor=?,usersetup.diarycolumnposition=?,usersetup.compressionrate=?," +
				" usersetup.onlinename=?,usersetup.isavailableonline=? where userprofile.id=?";*/
		
		String sql = "UPDATE apm_user set firstname=?,lastname=?,initial=?,jobtitle=?,discription=?,registerno=?,usergroup=?,"
			    + "isapractitioner=?,appointmentdiary=?,diarycolor=?,diarycolumnposition=?,compressionrate=?,onlinename=?,"
			    + "isavailableonline=?,loginsystem=?,userid=?,lastchanged=?,changefre=?,appointmentbookingcontem=?,"
			    + "appointmentbookingdnatem=?,email=?,mobile=?,apmremote=?,onlinebooking=?,dnaCharge=?,completeAppCharge=?,"
			    + "hasdiary=?,owner_qualification=?,p_type=?,chargetype=?,doctor=?,jobgroup=?,ipdshare=?,surgeonshare=?,"
			    + "isvisitingdoctor=?,wardid=?,labname=?,dob=?,indentlocations=?,licenseid=?,secondary_doc=?,vivitingper=? where id="+userprofile.getId()+"";
		try{
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, userprofile.getFirstname());
			preparedStatement.setString(2, userprofile.getLastname());
			preparedStatement.setString(3, userprofile.getInitial());
			preparedStatement.setString(4, userprofile.getJobtitle());
			/*preparedStatement.setString(4, userprofile.getDiscription());*/
			preparedStatement.setString(5, userprofile.getDiciplineName());
			preparedStatement.setString(6, userprofile.getRegisterno());
			
			preparedStatement.setString(7, userprofile.getUsergroup());
			preparedStatement.setBoolean(8, userprofile.isUsercheck());
			preparedStatement.setBoolean(9, userprofile.isAppointmentdiary());
			
			preparedStatement.setString(10, "#"+userprofile.getDiarycolor());
			preparedStatement.setString(11, userprofile.getDiarycolumnposition());
			preparedStatement.setInt(12, userprofile.getCompressionrate());
			
			preparedStatement.setString(13, userprofile.getOnlinename());
			preparedStatement.setBoolean(14, userprofile.isIsavailableonline());
			preparedStatement.setBoolean(15, userprofile.isLoginsystem());
			preparedStatement.setString(16, userprofile.getUserid());
			/*preparedStatement.setString(16, userprofile.getPassword());*/
			preparedStatement.setString(17, userprofile.getLastchanged());
			preparedStatement.setInt(18, userprofile.getChangefre());
			preparedStatement.setString(19,userprofile.getAppointmentbookingcontem());
			preparedStatement.setString(20,userprofile.getAppointmentbookingdnatem());
			preparedStatement.setString(21, userprofile.getEmail());
			preparedStatement.setString(22, userprofile.getMobile());
			preparedStatement.setBoolean(23, userprofile.isApmremote());
			preparedStatement.setBoolean(24, userprofile.isOnlinebooking());
			preparedStatement.setInt(25, userprofile.getDnaCharge());
			preparedStatement.setInt(26, userprofile.getCompAppCharge());
			preparedStatement.setBoolean(27, userprofile.isHasDiary());
			preparedStatement.setString(28, userprofile.getQualification());
							
			preparedStatement.setString(29, userprofile.getPractitonerType());
			preparedStatement.setString(30, userprofile.getChargeType());
			preparedStatement.setString(31, userprofile.getDoctor());
			preparedStatement.setString(32, userprofile.getJobgroup());
			preparedStatement.setInt(33, userprofile.getIpdCharge());
			preparedStatement.setInt(34, userprofile.getSurgeonCharge());
			preparedStatement.setBoolean(35, userprofile.isVisitingdoctor());
			preparedStatement.setString(36, userprofile.getWardid());
			preparedStatement.setString(37, userprofile.getLabname());
			preparedStatement.setString(38, userprofile.getDob());
			preparedStatement.setString(39, userprofile.getLocation());
			preparedStatement.setString(40, DateTimeUtils.isNull(userprofile.getLicenceId()));
			preparedStatement.setBoolean(41, userprofile.isSecondary_doc());
			preparedStatement.setString(42, userprofile.getIpdconsultper());
			result = preparedStatement.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public ArrayList<UserProfile> getUserprofileList() {
		PreparedStatement preparedStatement = null;
		ArrayList<UserProfile>list = new ArrayList<UserProfile>();
		String sql = "SELECT uf.fullname, uf.initial,uf.jobtitle,uf.discription,uf.registerno,uf.usergroup," +
					"us.isapractitioner,us.appointmentdiary,us.diarycolor,us.diarycolumnposition,us.compressionrate," +
					"us.onlinename,us.isavailableonline" +
					"FROM userprofile AS uf INNER JOIN usersetup AS us ON uf.id = us.userid order by lastname";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				UserProfile userprofile = new UserProfile();
				userprofile.setId(rs.getInt(1));
				userprofile.setFullname(rs.getString(2));
				userprofile.setInitial(rs.getString(3));
				userprofile.setJobtitle(rs.getString(4));
				/*userprofile.setDiscription(rs.getString(5));*/
				userprofile.setDiciplineName (rs.getString(5));
				userprofile.setRegisterno(rs.getString(6));
				userprofile.setUsergroup(rs.getString(7));
				userprofile.setUsercheck(rs.getBoolean(8));
				userprofile.setAppointmentdiary(rs.getBoolean(9));
				userprofile.setDiarycolor(rs.getString(10));
				userprofile.setDiarycolumnposition(rs.getString(11));
				userprofile.setCompressionrate(rs.getInt(12));
				userprofile.setOnlinename(rs.getString(13));
			
				
				list.add(userprofile);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public UserProfile getUserprofileDetails(int selectedid) {
		
		PreparedStatement preparedStatement = null;
		MasterDAO masterDAO=new JDBCMasterDAO(connection);
		UserProfile userprofile = new UserProfile();
		
		/*String sql = "SELECT uf.fullname, uf.initial,uf.jobtitle,uf.discription,uf.registerno,uf.usergroup,"+
					"us.isapractitioner,us.appointmentdiary,us.diarycolor,us.diarycolumnposition,us.compressionrate,"+
					"us.onlinename,us.isavailableonline"+
					"FROM userprofile AS uf INNER JOIN usersetup AS us ON uf.id = us.userid where userprofile.id = ?";*/
		
		/*String sql = "SELECT * from userprofile where id=?";*/
		/*String sql = "SELECT * from apm_user where id=? and usertype = 4 ";*/
		String sql = "SELECT * from apm_user where id=?";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, selectedid);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				/*userprofile.setId(rs.getInt(1));*/
				userprofile.setPhone(rs.getString("landline"));
				userprofile.setClinicname(rs.getString("clinicname"));
				userprofile.setFirstname(rs.getString("firstname"));
				userprofile.setLastname(rs.getString("lastname"));
				userprofile.setInitial(rs.getString("initial"));
				userprofile.setJobtitle(rs.getString("jobtitle"));
				/*userprofile.setDiscription(rs.getString(5));*/
				userprofile.setDiciplineName(rs.getString("discription"));
				userprofile.setRegisterno(rs.getString("registerno"));
				userprofile.setUsergroup(rs.getString("usergroup"));
				userprofile.setUsercheck(rs.getBoolean("isapractitioner"));
				userprofile.setAppointmentdiary(rs.getBoolean("appointmentdiary"));
				userprofile.setDiarycolor(rs.getString("diarycolor"));
				userprofile.setDiarycolumnposition(rs.getString("diarycolumnposition"));
				userprofile.setCompressionrate(rs.getInt("compressionrate"));
				userprofile.setOnlinename(rs.getString("onlinename"));
				userprofile.setIsavailableonline(rs.getBoolean("isavailableonline"));
				userprofile.setLoginsystem(rs.getBoolean("loginsystem"));
				userprofile.setUserid(rs.getString("userid"));
				userprofile.setLastchanged(rs.getString("lastchanged"));
				userprofile.setChangefre(rs.getInt("changefre"));
				userprofile.setAppointmentbookingcontem(rs.getString("appointmentbookingcontem"));
				userprofile.setAppointmentbookingdnatem(rs.getString("appointmentbookingdnatem"));
				userprofile.setEmail(rs.getString("email"));
				userprofile.setMobile(rs.getString("mobile"));
				userprofile.setApmremote(rs.getBoolean("apmremote"));
				userprofile.setOnlinebooking(rs.getBoolean("onlinebooking"));
				userprofile.setId(selectedid);
				userprofile.setDnaCharge(rs.getInt("dnaCharge"));
				userprofile.setCompAppCharge(rs.getInt("completeAppCharge"));
				userprofile.setDnaCharge(rs.getInt("dnaCharge"));
				userprofile.setCompAppCharge(rs.getInt("completeAppCharge"));
				userprofile.setHasDiary(rs.getBoolean("hasdiary"));
				userprofile.setAddress(rs.getString("address"));
				userprofile.setPostcode(rs.getString("pincode"));
				userprofile.setTown(rs.getString("city"));
				userprofile.setCounty(rs.getString("state"));
				userprofile.setQualification(rs.getString("owner_qualification"));
				userprofile.setPractitonerType(rs.getString("p_type"));
				userprofile.setChargeType(rs.getString("chargetype"));
				userprofile.setDoctor(rs.getString("doctor"));
				userprofile.setJobgroup(rs.getString("jobgroup"));
				userprofile.setIpdCharge(rs.getInt("ipdshare"));
				userprofile.setSurgeonCharge(rs.getInt("surgeonshare"));
				userprofile.setVisitingdoctor(rs.getBoolean("isvisitingdoctor"));
				userprofile.setWardid(rs.getString("wardid"));
				userprofile.setLabname(rs.getString("labname"));
				userprofile.setAcaccess(rs.getString("acaccess"));
				if(rs.getString("dob")!=null){
					userprofile.setDob(rs.getString("dob"));	
				}
				else{
					userprofile.setDob("");
				}
				if(userprofile.getMobile()==null){
					userprofile.setMobile("");
				}
				
				String fullname=userprofile.getInitial()+" "+userprofile.getFirstname()+" "+userprofile.getLastname();
				userprofile.setFullname(fullname);
				Master master = masterDAO.getDisciplineData(userprofile.getDiciplineName());
				userprofile.setDiscription(master.getDescription());
				userprofile.setSpecialization(master.getDiscipline()); 
				userprofile.setBlankletterhead(rs.getString("blankletterhead"));
				
				userprofile.setVivitingper(rs.getString("vivitingper"));
				userprofile.setIpdconsultper(rs.getString("ipdconsultper"));
				userprofile.setSurgeonper(rs.getString("surgeonper"));
				userprofile.setRefund_dashboard(rs.getBoolean("refund_dashboard"));
				userprofile.setShowinvestigation(rs.getBoolean("showinvestigation"));
				userprofile.setEdit_invst_charge(rs.getBoolean("edit_invst_charge"));
				userprofile.setStock_log(rs.getBoolean("stock_log"));
				userprofile.setEdit_paypo(rs.getBoolean("edit_paypo"));
				userprofile.setAdjustmentaccess(rs.getBoolean("adjustmentaccess"));
				userprofile.setSupplier_add(rs.getBoolean("supplier_add"));
				userprofile.setTreatmentacc(rs.getBoolean("treatment_episode"));
				/*userprofile.setPharm_print_backdate(rs.getBoolean("pharm_print_backdate"));*/
				userprofile.setDirect_refund_disc(rs.getBoolean("direct_refund_disc"));
				userprofile.setPrisc_new_req_access(rs.getBoolean("prisc_new_req_access"));
				userprofile.setLicenceId(DateTimeUtils.isNull(rs.getString("licenseid")));
				userprofile.setOpd_user_vid_access(rs.getBoolean("opd_user_vid_access"));
				userprofile.setSecondary_doc(rs.getBoolean("secondary_doc"));
				String empcode=getempcode(userprofile.getUserid());
				userprofile.setEmpid(empcode);
				userprofile.setUserImageFileName(DateTimeUtils.isNull(rs.getString("clinicLogo")));
				userprofile.setClinicowner(DateTimeUtils.isNull(rs.getString("clinicowner")));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return userprofile;
	}


	private String getempcode(String userid) {
		// TODO Auto-generated method stub
		String empcode="";
		try {
			String sql ="select empcode from apm_payroll_employee where userid='"+userid+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				empcode = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return empcode;
	}


	public boolean isColorExist(String diarycolor) {
		PreparedStatement preparedStatement = null;
		
		boolean result = false;
		String sql = "select diarycolor,color,colorcode from apm_user,apm_clinic_location,apm_appointment_type where diarycolor = '"+diarycolor+"' or color = '"+diarycolor+"' or colorcode = '"+diarycolor+"';";
		
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


	public ArrayList<String> getJobTitleList() {
		PreparedStatement preparedStatement = null;
		ArrayList<String>list = new ArrayList<String>();
		String sql = "select jobtitle from job_title where userid=0";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				
				String jobtitle = rs.getString(1);
			
				list.add(jobtitle);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	public int insertJobTitle(UserProfile userProfile, String jobTitle) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into job_title(jobtitle)values(?)";
		try{
			preparedStatement = connection.prepareStatement(sql);
		
			preparedStatement.setString(1, jobTitle);
			
			
			result = preparedStatement.executeUpdate();

		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}


	public List getPractitionerList(int id) {
		PreparedStatement preparedStatement = null;
		ArrayList<UserProfile>list = new ArrayList<UserProfile>();
	
		
		String sql = "select * from apm_user where usertype = 4 order by lastname" ;
		
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				UserProfile userprofile = new UserProfile();
				userprofile.setId(rs.getInt("id"));
				userprofile.setFirstname(rs.getString("firstname"));
				userprofile.setLastname(rs.getString("lastname"));
				userprofile.setInitial(rs.getString("initial"));
				userprofile.setJobtitle(rs.getString("jobtitle"));
				/*userprofile.setDiscription(rs.getString(5));*/
				userprofile.setDiciplineName(rs.getString("discription"));
				userprofile.setRegisterno(rs.getString("registerno"));
				userprofile.setUsergroup(rs.getString("usergroup"));
				userprofile.setUsercheck(rs.getBoolean("isapractitioner"));
				userprofile.setAppointmentdiary(rs.getBoolean("appointmentdiary"));
				userprofile.setDiarycolor(rs.getString("diarycolor"));
				userprofile.setDiarycolumnposition(rs.getString("diarycolumnposition"));
				userprofile.setCompressionrate(rs.getInt("compressionrate"));
				userprofile.setOnlinename(rs.getString("onlinename"));
				userprofile.setIsavailableonline(rs.getBoolean("isavailableonline"));
				userprofile.setLoginsystem(rs.getBoolean("loginsystem"));
				userprofile.setUserid(rs.getString("userid"));
				userprofile.setLastchanged(rs.getString("lastchanged"));
				userprofile.setChangefre(rs.getInt("changefre"));
				userprofile.setAppointmentbookingcontem(rs.getString("appointmentbookingcontem"));
				userprofile.setAppointmentbookingdnatem(rs.getString("appointmentbookingdnatem"));
				userprofile.setEmail(rs.getString("email"));
				userprofile.setMobile(rs.getString("mobile"));
				userprofile.setApmremote(rs.getBoolean("apmremote"));
				userprofile.setOnlinebooking(rs.getBoolean("onlinebooking"));
				userprofile.setDnaCharge(rs.getInt("dnaCharge"));
				userprofile.setCompAppCharge(rs.getInt("completeAppCharge"));
				userprofile.setIpdCharge(rs.getInt("ipdshare"));
				userprofile.setSurgeonCharge(rs.getInt("surgeonshare"));
				
				
				list.add(userprofile);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	public boolean isEmailIdExist(String emailId,String selectedid,String existEmailid) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String email = "";
		String sql = "select email from apm_user where email = '"+emailId+"' ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			//preparedStatement.setString(1, emailId);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				email = rs.getString(1);
				result = true;
			}
			
			if(!selectedid.equals("0")){
				if(email.equals(existEmailid)){
					result = false;
				}
			}
			
		}catch (Exception e) {
			
		}	
		
		return result;
	}

	public boolean isMobileNoExist(String mobileNo) {
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String sql = "select * from apm_user where mobile = '"+mobileNo+"' ";
		
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
	public int saveDetailsUserprofile(UserProfile userprofile, int clinicid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		int outoid = 0;
		String sql = "insert into apm_user(firstname,lastname,initial,jobtitle,userid,password,email,mobile,"
				+ "usertype,clinicid,dnaCharge,completeAppCharge,hasdiary,usrposition,owner_qualification,"
				+ "discription,p_type,chargetype,doctor,jobgroup,wardid,labname,clinicname,indentlocations,"
				+ "licenseid,dob,secondary_doc,refered_clinicname,clinic_staff,clinicLogo)" +
			    " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try{
			preparedStatement = connection.prepareStatement(sql);
			/*preparedStatement.setInt(1, userprofile.getId());*/
			preparedStatement.setString(1, userprofile.getFirstname());
			preparedStatement.setString(2, userprofile.getLastname());
			preparedStatement.setString(3, userprofile.getInitial());
			preparedStatement.setString(4, userprofile.getJobtitle());
			
			preparedStatement.setString(5, userprofile.getUserid());			
			String encPassword = Encryption.encryptSHA(userprofile.getPassword());
			preparedStatement.setString(6,encPassword);
			
			preparedStatement.setString(7, userprofile.getEmail());
			preparedStatement.setString(8, userprofile.getMobile());
			
			preparedStatement.setInt(9,userprofile.getUserType());
		
			preparedStatement.setInt(10, clinicid);
			
			preparedStatement.setInt(11, userprofile.getDnaCharge());
			preparedStatement.setInt(12, userprofile.getCompAppCharge()); 
			preparedStatement.setBoolean(13, userprofile.isHasDiary());
			
			int usrPosition = getUserPosition();
			usrPosition = usrPosition + 1;
			
			preparedStatement.setInt(14, usrPosition);
			preparedStatement.setString(15, userprofile.getQualification());
			preparedStatement.setString(16, userprofile.getDiciplineName());
			preparedStatement.setString(17, userprofile.getPractitonerType());
			preparedStatement.setString(18, userprofile.getChargeType());
			preparedStatement.setString(19, userprofile.getDoctor());
			preparedStatement.setString(20, userprofile.getJobgroup());
			preparedStatement.setString(21, userprofile.getWardid());
			preparedStatement.setString(22, userprofile.getLabname());
			preparedStatement.setString(23, userprofile.getClinicname());
			preparedStatement.setString(24, userprofile.getLocation());
			preparedStatement.setString(25, DateTimeUtils.isNull(userprofile.getLicenceId()));
			preparedStatement.setString(26, userprofile.getDob());
			preparedStatement.setBoolean(27, userprofile.isSecondary_doc());
			preparedStatement.setString(28, userprofile.getHospitalname());
			preparedStatement.setString(29, userprofile.getClinic_staff());
			preparedStatement.setString(30, userprofile.getUserImageFileName());
			result = preparedStatement.executeUpdate();
			
			if(result == 1){
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if(resultSet.next()){
					outoid = resultSet.getInt(1);  
				}
			}
			
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	
		return outoid;
	}


	private int getUserPosition() {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "SELECT count(*) FROM apm_user where jobtitle='Practitioner' or hasdiary =1 ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}


	public int savePractitionerToAdmin(UserProfile userprofile, String clinicid) {
		PreparedStatement preparedStatement = null;
		int res = 0;
		String sql = "insert into apm_user(userid,password,usertype,clinicid,email,mobile,dob) values(?,?,?,?,?,?,?)";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userprofile.getUserid());
			String encPassword = Encryption.encryptSHA(userprofile.getPassword());
			preparedStatement.setString(2, encPassword);
			preparedStatement.setInt(3, userprofile.getUserType());
			preparedStatement.setString(4, clinicid);
			preparedStatement.setString(5, userprofile.getEmail());
			preparedStatement.setString(6, userprofile.getMobile());
			preparedStatement.setString(7, userprofile.getDob());
			res = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}

	public int updatePractitionerToAdmin(UserProfile userprofile, String clinicid,String userID) {
		PreparedStatement preparedStatement = null;
		int res = 0;
		String sql = "update apm_user set userid=?,password=?,usertype=?,mobile=? where userid='"+userID+"' and clinicid='"+clinicid+"' ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userprofile.getUserid());
			String encPassword = Encryption.encryptSHA(userprofile.getPassword());
			preparedStatement.setString(2, encPassword);
			preparedStatement.setInt(3, userprofile.getUserType());
			preparedStatement.setString(4, userprofile.getMobile());
			res = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}

	public int getUserprofileCount(int id) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		
		String sql = "select count(*) from apm_user where usertype = 4";
		try{
			preparedStatement = connection.prepareStatement(sql);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getInt(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	public ArrayList<UserProfile> getUserProfileList(Pagination pagination,int id) {
		PreparedStatement preparedStatement = null;
		ArrayList<UserProfile>list = new ArrayList<UserProfile>();
	
		String sql = "";
		if(pagination.sortColumn==null){
			sql = "select id,firstname,lastname,jobtitle,discription,email,mobile from apm_user where usertype = 4 order by id desc" ;
		}else{
			sql = "select id,firstname,lastname,jobtitle,discription,email,mobile from apm_user where usertype = 4";
		}
			sql = pagination.getSQLQuery(sql);
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				UserProfile userprofile = new UserProfile();
				userprofile.setId(rs.getInt(1));
				userprofile.setFirstname(rs.getString(2));
				userprofile.setLastname(rs.getString(3));
				userprofile.setJobtitle(rs.getString(4));
				userprofile.setDiscription(rs.getString(5));
				
				list.add(userprofile);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}


	public ArrayList<UserProfile> getAllPractitioner(int id) {
		PreparedStatement preparedStatement = null;
		ArrayList<UserProfile>list = new ArrayList<UserProfile>();
		
		String sql = "select * from apm_user where usertype = 4 order by lastname limit 10" ;
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				UserProfile userprofile = new UserProfile();
				userprofile.setId(rs.getInt("id"));
				userprofile.setFirstname(rs.getString("firstname"));
				userprofile.setLastname(rs.getString("lastname"));
				userprofile.setInitial(rs.getString("initial"));
				userprofile.setJobtitle(rs.getString("jobtitle"));
				/*userprofile.setDiscription(rs.getString(5));*/
				userprofile.setDiciplineName(rs.getString("discription"));
				userprofile.setRegisterno(rs.getString("registerno"));
				userprofile.setUsergroup(rs.getString("usergroup"));
				userprofile.setUsercheck(rs.getBoolean("isapractitioner"));
				userprofile.setAppointmentdiary(rs.getBoolean("appointmentdiary"));
				userprofile.setDiarycolor(rs.getString("diarycolor"));
				userprofile.setDiarycolumnposition(rs.getString("diarycolumnposition"));
				userprofile.setCompressionrate(rs.getInt("compressionrate"));
				userprofile.setOnlinename(rs.getString("onlinename"));
				userprofile.setIsavailableonline(rs.getBoolean("isavailableonline"));
				userprofile.setLoginsystem(rs.getBoolean("loginsystem"));
				userprofile.setUserid(rs.getString("userid"));
				userprofile.setLastchanged(rs.getString("lastchanged"));
				userprofile.setChangefre(rs.getInt("changefre"));
				userprofile.setAppointmentbookingcontem(rs.getString("appointmentbookingcontem"));
				userprofile.setAppointmentbookingdnatem(rs.getString("appointmentbookingdnatem"));
				userprofile.setEmail(rs.getString("email"));
				userprofile.setMobile(rs.getString("mobile"));
				userprofile.setApmremote(rs.getBoolean("apmremote"));
				userprofile.setOnlinebooking(rs.getBoolean("onlinebooking"));
				userprofile.setDnaCharge(rs.getInt("dnaCharge"));
				userprofile.setCompAppCharge(rs.getInt("completeAppCharge"));
				
				
				list.add(userprofile);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}


	public String getFullName(String id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT initial,firstname,lastname FROM apm_user where  id = '"+id+"'";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public String getName(String id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT firstname,lastname FROM apm_user where  id = "+id+"";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getString(1)+" "+rs.getString(2);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	public String getUserEmail(String id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT email FROM apm_user where  id = "+id+"";
		
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


	public ArrayList<UserProfile> getAllPractitioner(String searchPract, int id) {
		PreparedStatement preparedStatement = null;
		String temp[] = searchPract.split(" ");
		ArrayList<UserProfile>list = new ArrayList<UserProfile>();
		StringBuffer sql = new StringBuffer();
		 sql.append("select * from apm_user where usertype = 4 and (mobile like('%"+searchPract+"%') or pincode like('%"+searchPract+"%') or dob like('%"+searchPract+"%') or");
		
		for(int i=0;i<temp.length;i++){
			
			
			sql.append(" firstname like('%"+temp[i]+"%') or ");
			if(i==(temp.length-1)){
			sql.append("lastname like('%"+temp[i]+"%')) order by lastname limit 10");
			}
			else{
				sql.append("lastname like('%"+temp[i]+"%') or ");
			}
			
		}
		
try{
			
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				UserProfile userprofile = new UserProfile();
				userprofile.setId(rs.getInt("id"));
				userprofile.setFirstname(rs.getString("firstname"));
				userprofile.setLastname(rs.getString("lastname"));
				userprofile.setInitial(rs.getString("initial"));
				userprofile.setJobtitle(rs.getString("jobtitle"));
				/*userprofile.setDiscription(rs.getString(5));*/
				userprofile.setDiciplineName(rs.getString("discription"));
				userprofile.setRegisterno(rs.getString("registerno"));
				userprofile.setUsergroup(rs.getString("usergroup"));
				userprofile.setUsercheck(rs.getBoolean("isapractitioner"));
				userprofile.setAppointmentdiary(rs.getBoolean("appointmentdiary"));
				userprofile.setDiarycolor(rs.getString("diarycolor"));
				userprofile.setDiarycolumnposition(rs.getString("diarycolumnposition"));
				userprofile.setCompressionrate(rs.getInt("compressionrate"));
				userprofile.setOnlinename(rs.getString("onlinename"));
				userprofile.setIsavailableonline(rs.getBoolean("isavailableonline"));
				userprofile.setLoginsystem(rs.getBoolean("loginsystem"));
				userprofile.setUserid(rs.getString("userid"));
				userprofile.setLastchanged(rs.getString("lastchanged"));
				userprofile.setChangefre(rs.getInt("changefre"));
				userprofile.setAppointmentbookingcontem(rs.getString("appointmentbookingcontem"));
				userprofile.setAppointmentbookingdnatem(rs.getString("appointmentbookingdnatem"));
				userprofile.setEmail(rs.getString("email"));
				userprofile.setMobile(rs.getString("mobile"));
				userprofile.setApmremote(rs.getBoolean("apmremote"));
				userprofile.setOnlinebooking(rs.getBoolean("onlinebooking"));
				userprofile.setDnaCharge(rs.getInt("dnaCharge"));
				userprofile.setCompAppCharge(rs.getInt("completeAppCharge"));				
				userprofile.setIpdCharge(rs.getInt("ipdshare"));
				userprofile.setSurgeonCharge(rs.getInt("surgeonshare"));
				
				list.add(userprofile);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public String getClientID(int selectedid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT max(id) FROM apm_available_slot where  diaryuserid = "+selectedid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = getClientId(rs.getString(1));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	public String getClientId(String id) {
		PreparedStatement preparedStatement = null;
		String result1 = "";
		String sql = "SELECT clientId FROM apm_available_slot where  id = "+id+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result1 =rs.getString(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result1;
	}


	public String getAdminDbUserID(int selectedid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT userid FROM apm_user where id = "+selectedid+" ";
		
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


	public int deleteAdminUserprofile(String userid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "delete from apm_user where userid='"+userid+"' ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}


	public int getPractitionerId(int selectedid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select diaryuserid from apm_available_slot where clientId = "+selectedid+" ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getInt(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}


	public int saveAdminPassword(UserProfile userprofile) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_admin(userid,password)values(?,?) ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userprofile.getUserid());
			//to store password encrypted.
			//String encPassword = Encryption.encryptSHA(userprofile.getPassword());
			//preparedStatement.setString(2, encPassword);
			preparedStatement.setString(2, userprofile.getPassword());
			
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}


	
	public String getExistingEmailID(String selectedid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT email FROM apm_user where id = "+selectedid+" ";
		
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


	
	public int updatePractitionerAdminData(String userid, String email,String mobile,String existingUserid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_user set userid=?,email=?,mobile=? where userid=? ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userid);
			preparedStatement.setString(2, email);
			preparedStatement.setString(3, mobile);
			preparedStatement.setString(4, existingUserid);
			
			result = preparedStatement.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		
		}
		return result;
	}


	
	public String getexistingUserid(int selectedid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select userid from apm_user where id = "+selectedid+" ";
		
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


	public int getDiaryUserId(String userId) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "select id from apm_user where userid='"+userId+"' ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
			
		return result;
	}


	public Access getRoleAccess(String jobtitle) {
		PreparedStatement preparedStatement = null;
		Access access = new Access();
		//get  ot, casualty, pharmacy, mrd, marketing, voice_recorder in select tag
		String sql = "select   diarymanagement, appointmentbooking, basicfinance, fullfinance, medicalrecord, communication, report, "
				+ "assessmentForm,manageclient,manageclinic,managemaster,manageprisc,manageinvst,manageipd,manageopd,apmtfinder,manageemr,managemis,payroll, "
				+ "bloodbak, inventory, discharge,expences,packs, investigation_chart, sheduler, housekeeping, dietery, cafeteria, packages, ambulance, bank_deposite, "
				+ "account_reconcilation, ot, casualty, pharmacy, mrd, marketing, voice_recorder,indent_access,investigation_approve,daily_opd,indent_approve,tpa,"
				+ "nabh_quality,doctor_opd,invst_collect,token_display,add_medicine,pharm_print_backdate,cathlab,myhr, daycare, emergency_lbl,"
				+ "medicine_barcode,refund, discount, vaccination, video_training, setup_master, sale_pharmacy,delete_invoice,registrationdash from apm_role_access where usertype='"+jobtitle+"' ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				access.setDiarymanagement(rs.getBoolean(1));
				access.setAppointmentbooking(rs.getBoolean(2));
				access.setBasicfinance(rs.getBoolean(3));
				access.setFullfinance(rs.getBoolean(4));
				access.setMedicalrecord(rs.getBoolean(5));
				access.setCommunication(rs.getBoolean(6));
				access.setReport(rs.getBoolean(7));
				access.setAssessmentForm(rs.getBoolean(8));
				//manageclient,manageclinic,managemaster,manageprisc,manageinvst,manageipd
				access.setManageclient(rs.getBoolean(9));
				access.setManageclinic(rs.getBoolean(10));
				access.setManagemaster(rs.getBoolean(11));
				access.setManageprisc(rs.getBoolean(12));
				access.setManageinvst(rs.getBoolean(13));
				access.setManageipd(rs.getBoolean(14));
				access.setManageopd(rs.getBoolean(15));
				access.setApmtfinder(rs.getBoolean(16));
				access.setManageemr(rs.getBoolean(17));
				access.setManagemis(rs.getBoolean(18));
				
				access.setPayroll(rs.getBoolean(19));
				access.setBloodbak(rs.getBoolean(20));
				access.setInventory(rs.getBoolean(21));
				access.setDischarge(rs.getBoolean(22));
				access.setExpences(rs.getBoolean(23));
				
				access.setPacks(rs.getBoolean(24));
				access.setInvestigation_chart(rs.getBoolean(25));
				access.setSheduler(rs.getBoolean(26));
				access.setHousekeeping(rs.getBoolean(27));
				access.setDietery(rs.getBoolean(28));
				access.setCafeteria(rs.getBoolean(29));
				access.setPackages(rs.getBoolean(30));
				access.setAmbulance(rs.getBoolean(31));
				access.setBank_deposite(rs.getBoolean(32));
				access.setAccount_reconcilation(rs.getBoolean(33));
				
				//set  ot, casualty, pharmacy, mrd, marketing, voice_recorder in main dashboard
				
				access.setOt(rs.getBoolean(34));
				access.setCasualty(rs.getBoolean(35));
				access.setPharmacy(rs.getBoolean(36));
				access.setMrd(rs.getBoolean(37));
				access.setMarketing(rs.getBoolean(38));
				access.setVoice_recording(rs.getBoolean(39));
				access.setIndent(rs.getBoolean(40));
				access.setInvestigation_approve(rs.getBoolean(41));
				access.setDaily_opd(rs.getBoolean(42));
				
				access.setIndent_approve(rs.getBoolean(43));
				access.setTpa(rs.getBoolean(44));
				access.setNabh_quality(rs.getBoolean(45));
				
				access.setDoctor_opd(rs.getBoolean(46));
				access.setInvst_collect(rs.getBoolean(47));
				access.setToken_display(rs.getBoolean(48));
				access.setAdd_medicine(rs.getBoolean(49));
				access.setPharm_print_backdate(rs.getBoolean(50));
				access.setCathlab(rs.getBoolean(51));
				access.setMyhr(rs.getBoolean(52));
				access.setDaycare(rs.getBoolean(53));
				access.setEmergency_lbl(rs.getBoolean(54));
				access.setMedicine_barcode(rs.getBoolean(55));
				//refund, discount, vaccination, video_training, setup_master, sale_pharmacy
				access.setRefund(rs.getBoolean(56));
				access.setDiscount(rs.getBoolean(57));
				access.setVaccination(rs.getBoolean(58));
				access.setVideo_training(rs.getBoolean(59));
				access.setSetup_master(rs.getBoolean(60));
				access.setSale_pharmacy(rs.getBoolean(61));
				access.setDelete_invoice(rs.getBoolean(62));
				access.setRegistrationdash(rs.getBoolean(63));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return access;
	}


	public int updateUserAccess(Access access, int selectedid) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_user set diarymanagement=?, appointmentbooking=?, basicfinance=?, fullfinance=?,"+
					 " medicalrecord=?, communication=?, report=?, assessmentForm=?,manageclient=?,manageclinic=?,managemaster=?," +
					 " manageprisc=?,manageinvst=?,manageipd=? where id = "+selectedid+" ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setBoolean(1, access.isDiarymanagement());
			preparedStatement.setBoolean(2, access.isAppointmentbooking());
			preparedStatement.setBoolean(3, access.isBasicfinance());
			preparedStatement.setBoolean(4, access.isFullfinance());
			preparedStatement.setBoolean(5, access.isMedicalrecord());
			preparedStatement.setBoolean(6, access.isCommunication());
			preparedStatement.setBoolean(7, access.isReport());
			preparedStatement.setBoolean(8, access.isAssessmentForm());
			preparedStatement.setBoolean(9, access.isManageclient());
			preparedStatement.setBoolean(10, access.isManageclinic());
			preparedStatement.setBoolean(11, access.isManagemaster());
			preparedStatement.setBoolean(12, access.isManageprisc());
			preparedStatement.setBoolean(13, access.isManageinvst());
			preparedStatement.setBoolean(14, access.isManageipd());
			
			result = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
							
		
		return result;
	}


	public Access getUserRoleAccess(String userId) {
		PreparedStatement preparedStatement = null;
		Access access = new Access();
		String sql = "select   diarymanagement, appointmentbooking, basicfinance, fullfinance, medicalrecord, communication, report, assessmentForm,manageclient,manageclinic,managemaster,manageprisc,manageinvst,manageipd from apm_user where userid='"+userId+"' ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				access.setDiarymanagement(rs.getBoolean(1));
				access.setAppointmentbooking(rs.getBoolean(2));
				access.setBasicfinance(rs.getBoolean(3));
				access.setFullfinance(rs.getBoolean(4));
				access.setMedicalrecord(rs.getBoolean(5));
				access.setCommunication(rs.getBoolean(6));
				access.setReport(rs.getBoolean(7));
				access.setAssessmentForm(rs.getBoolean(8));
				//manageclient,manageclinic,managemaster,manageprisc,manageinvst,manageipd
				access.setManageclient(rs.getBoolean(9));
				access.setManageclinic(rs.getBoolean(10));
				access.setManagemaster(rs.getBoolean(11));
				access.setManageprisc(rs.getBoolean(12));
				access.setManageinvst(rs.getBoolean(13));
				access.setManageipd(rs.getBoolean(14));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return access;
	}



	public ArrayList<UserProfile> getVisitingPractitiner() {

		ArrayList<UserProfile> list=new ArrayList<UserProfile>();
		
		try {
			
			String sql="SELECT id,firstname,lastname,jobtitle FROM apm_user where jobtitle='Practitioner' ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				 
				 UserProfile profile=new UserProfile();
				 profile.setId(rs.getInt(1));
				 profile.setFirstname(rs.getString(2));
				 profile.setLastname(rs.getString(3));
				 profile.setJobtitle(rs.getString(4));
				 String fullname=profile.getFirstname()+" "+profile.getLastname();
				 profile.setFullname(fullname);
				 list.add(profile);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	
		return list;
	}


	public ArrayList<Master> getJobGroupList(String string) {
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list = new ArrayList<Master>();
		String sql = "SELECT id,name FROM jobtitle_group ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				if(string.equals("1")){
					if(!rs.getString(2).equals("Other")){
						list.add(master);
					}
				}else{
					list.add(master);
				}
				
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}


	public ArrayList<Master> getUserJobTitleList(String selectedid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list = new ArrayList<Master>();
		String sql = "SELECT id,jobtitle FROM job_title where jobgroup_id = "+selectedid+" ";
		
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
			e.printStackTrace();
			// TODO: handle exception
		}
		return list;
	}


	public ArrayList<Master> getDoctorList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list = new ArrayList<Master>();
		String sql = "SELECT id,concat(initial,' ',firstname,' ',lastname) FROM apm_user where usertype = 4 and jobtitle='Practitioner' ";
		
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
			e.printStackTrace();
			// TODO: handle exception
		}
		return list;
	}


	public ArrayList<UserProfile> getAllUsers(String jobgroup) {

		ArrayList<UserProfile> list=new ArrayList<UserProfile>();
		
		try {
			
			String sql="select id,concat(firstname,' ',lastname) FROM apm_user where jobgroup ='"+jobgroup+"'";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				  
				 UserProfile userProfile=new UserProfile();
				 userProfile.setId(rs.getInt(1));
				 userProfile.setFullname(rs.getString(2));
				 list.add(userProfile);
			}
			
		} catch (Exception e) {

		    e.printStackTrace();
		} 
		
		return list;
	}


	public int getIdIfEmailExist(String email) {

		int result=0;
		try {
			
			String sql="SELECT id from apm_patient where email='"+email+"'";
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

	
	public ArrayList<UserProfile> getAllUserProfileList() {
		
		ArrayList<UserProfile> list=new ArrayList<UserProfile>();
		
		try {
			String sql="select * from apm_user where jobtitle in ('Billing','Accounts','Reception','Admin','PATHOLOGIST') order by lastname";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				UserProfile userprofile = new UserProfile();
				userprofile.setId(rs.getInt("id"));
				userprofile.setFirstname(rs.getString("firstname"));
				userprofile.setLastname(rs.getString("lastname"));
				userprofile.setInitial(rs.getString("initial"));
				userprofile.setJobtitle(rs.getString("jobtitle"));
				/*userprofile.setDiscription(rs.getString(5));*/
				userprofile.setDiciplineName(rs.getString("discription"));
				userprofile.setRegisterno(rs.getString("registerno"));
				userprofile.setUsergroup(rs.getString("usergroup"));
				userprofile.setUsercheck(rs.getBoolean("isapractitioner"));
				userprofile.setAppointmentdiary(rs.getBoolean("appointmentdiary"));
				userprofile.setDiarycolor(rs.getString("diarycolor"));
				userprofile.setDiarycolumnposition(rs.getString("diarycolumnposition"));
				userprofile.setCompressionrate(rs.getInt("compressionrate"));
				userprofile.setOnlinename(rs.getString("onlinename"));
				userprofile.setIsavailableonline(rs.getBoolean("isavailableonline"));
				userprofile.setLoginsystem(rs.getBoolean("loginsystem"));
				userprofile.setUserid(rs.getString("userid"));
				userprofile.setLastchanged(rs.getString("lastchanged"));
				userprofile.setChangefre(rs.getInt("changefre"));
				userprofile.setAppointmentbookingcontem(rs.getString("appointmentbookingcontem"));
				userprofile.setAppointmentbookingdnatem(rs.getString("appointmentbookingdnatem"));
				userprofile.setEmail(rs.getString("email"));
				userprofile.setMobile(rs.getString("mobile"));
				userprofile.setApmremote(rs.getBoolean("apmremote"));
				userprofile.setOnlinebooking(rs.getBoolean("onlinebooking"));
				userprofile.setDnaCharge(rs.getInt("dnaCharge"));
				userprofile.setCompAppCharge(rs.getInt("completeAppCharge"));
				userprofile.setIpdCharge(rs.getInt("ipdshare"));
				userprofile.setSurgeonCharge(rs.getInt("surgeonshare"));
				userprofile.setVisitingdoctor(rs.getBoolean("isvisitingdoctor"));
				
				String fullname=userprofile.getInitial()+" "+userprofile.getFirstname()+" "+userprofile.getLastname();
				userprofile.setFullname(fullname);
				
				list.add(userprofile);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
	
		return list;
	}


	public UserProfile getPharmacyUserDetails(int clinicid) {

		UserProfile userProfile= new UserProfile();
		try {
			InventoryVendorDAO inventoryVendorDAO =new JDBCInventoryVendorDAO(connection); 
			StringBuffer buffer=new StringBuffer();
			buffer.append("select id, clinicname, firstname, lastname, userid, password,");
			buffer.append("clinicid, address, city, website, email, phone, dlno, tinno, status,  ");
			buffer.append("sale_bill, discount, ledger, account, purchase_order,instruction1,instruction2,");
			buffer.append("instruction3,instruction4,ipdlocation,opdlocation,procurementType,state,printType,");
			buffer.append("inhousepatient,show_letterhd, pagelimit,isdotmatrix,medlimit,nonsystembarcode, ");
			buffer.append("opddiscount,ipddiscount,pharpatientdiscount ");
			buffer.append("from apm_pharmacy_user where id=1 ");
			
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()){
				
				userProfile.setId(rs.getInt(1));
				userProfile.setClinicname(rs.getString(2));
				userProfile.setFirstname(rs.getString(3));
				userProfile.setLastname(rs.getString(4));
				String fullname= userProfile.getFirstname()+ " "+userProfile.getLastname();
				userProfile.setFullname(fullname);
				userProfile.setUserid(rs.getString(5));
				userProfile.setPassword(rs.getString(6));
				userProfile.setClinicid(rs.getInt(7));
				userProfile.setAddress(rs.getString(8));
				userProfile.setCity(rs.getString(9));
				userProfile.setWebsite(rs.getString(10));
				userProfile.setEmail(rs.getString(11));
				userProfile.setPhone(rs.getString(12));
				userProfile.setDlno(rs.getString(13));
				userProfile.setTinno(rs.getString(14));
				userProfile.setStatus(rs.getString(15));
				userProfile.setSale_bill(rs.getString(16));
				userProfile.setDiscount(rs.getString(17));
				userProfile.setLedger(rs.getString(18));
				userProfile.setAccount(rs.getString(19));
				userProfile.setPurchase_order(rs.getString(20));
				userProfile.setInstruction1(rs.getString(21));
				userProfile.setInstruction2(rs.getString(22));
				userProfile.setInstruction3(rs.getString(23));
				userProfile.setInstruction4(rs.getString(24));
				userProfile.setIpdlocation(rs.getString(25));
				userProfile.setOpdlocation(rs.getString(26));
				userProfile.setProcurementType(rs.getString(27));
				userProfile.setState(inventoryVendorDAO.getStateName(rs.getString(28)));
				userProfile.setPrintType(rs.getString(29));
				userProfile.setInhousepatient(rs.getString(30));
				userProfile.setShowletterhd(rs.getBoolean(31));
				userProfile.setPagelimit(rs.getString(32));
				if(rs.getString(32)==null){
					userProfile.setPagelimit("6");
				}
				if(rs.getString(32).equals("")){
					userProfile.setPagelimit("6");
				}
				if(rs.getString(32).equals("0")){
					userProfile.setPagelimit("6");
				}
				userProfile.setIsdotmatrix(rs.getBoolean(33));
				userProfile.setMedlimit(rs.getString(34));
				userProfile.setNonsystembarcode(rs.getBoolean(35));
				userProfile.setOpddiscount(rs.getDouble(36));
				userProfile.setIpddiscount(rs.getDouble(37));
				userProfile.setPharpatientdiscount(rs.getDouble(38));
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return userProfile;
	}

	public int updatePharmacyProfile(int clinicid,UserProfile userProfile) {

		int result=0;
		
		try {
			StringBuffer buffer= new StringBuffer();
			buffer.append("update apm_pharmacy_user set clinicname=?,dlno=?,tinno=?,address=?, ");
			buffer.append("city=?,website=?,email=?,phone=?,instruction1=?,instruction2=?,instruction3=?,");
			buffer.append("instruction4=?,ipdlocation=?,opdlocation=?,procurementType=?,printType=?,");
			buffer.append("inhousepatient=?,show_letterhd=?, pagelimit=?,isdotmatrix=?,medlimit=?,nonsystembarcode=?,");
			buffer.append("ipddiscount=?,opddiscount=?,pharpatientdiscount=? ");
			buffer.append("where id=1 ");
			
			PreparedStatement ps= connection.prepareStatement(buffer.toString());
			ps.setString(1,userProfile.getClinicname() );
			ps.setString(2, userProfile.getDlno());
			ps.setString(3, userProfile.getTinno());
			ps.setString(4, userProfile.getAddress());
			ps.setString(5, userProfile.getCity());
			ps.setString(6, userProfile.getWebsite());
			ps.setString(7, userProfile.getEmail());
			ps.setString(8, userProfile.getPhone());
			ps.setString(9, userProfile.getInstruction1());
			ps.setString(10, userProfile.getInstruction2());
			ps.setString(11, userProfile.getInstruction3());
			ps.setString(12, userProfile.getInstruction4());
			ps.setString(13, userProfile.getIpdlocation());
			ps.setString(14, userProfile.getOpdlocation());
			ps.setString(15, userProfile.getProcurementType());
			ps.setString(16, userProfile.getPrintType());
			ps.setString(17, userProfile.getInhousepatient());
			if(userProfile.isShowletterhd()==true){
			ps.setInt(18, 1);
			}else{
				ps.setInt(18, 0);
			}
			ps.setString(19, userProfile.getPagelimit());
			ps.setBoolean(20, userProfile.isIsdotmatrix());
			ps.setString(21, userProfile.getMedlimit());
			ps.setBoolean(22, userProfile.isNonsystembarcode());
			ps.setString(23, ""+userProfile.getIpddiscount());
			ps.setString(24, ""+userProfile.getOpddiscount());
			ps.setString(25, ""+userProfile.getPharpatientdiscount());
			result =ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}


	public int saPharmacyUser(UserProfile userProfile) {

		int result=0;
		try {
			String sql="insert into apm_pharmacy_user (firstname,lastname,userid,password,clinicid,phone,usertype,location,state) values (?,?,?,?,?,?,?,?,?) ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, userProfile.getFirstname());
			ps.setString(2, userProfile.getLastname());
			ps.setString(3, userProfile.getUserid());
			ps.setString(4, userProfile.getPassword());
			ps.setInt(5, userProfile.getClinicid());
			ps.setString(6, userProfile.getPhone());
			ps.setInt(7, userProfile.getUserType());
			ps.setString(8, userProfile.getLocation());
			ps.setString(9, userProfile.getState());
			
			result= ps.executeUpdate();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}


	public ArrayList<UserProfile> getAllPharmacyUserList() {

		ArrayList<UserProfile> list= new ArrayList<UserProfile>();
		PharmacyDAO pharmacyDAO= new JDBCPharmacyDAO(connection);
		InventoryVendorDAO inventoryVendorDAO= new JDBCInventoryVendorDAO(connection);
		try {
			
			String sql="select id,firstname,lastname,phone,userid,password,islogin,location,state from apm_pharmacy_user where usertype=4 ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				  
				 UserProfile userProfile= new UserProfile();
				 userProfile.setId(rs.getInt(1));
				 userProfile.setFirstname(rs.getString(2));
				 userProfile.setLastname(rs.getString(3));
				 userProfile.setFullname(rs.getString(2)+" "+rs.getString(3));
				 userProfile.setPhone(rs.getString(4));
				 userProfile.setUserid(rs.getString(5));
				 userProfile.setPassword(rs.getString(6));
				 userProfile.setIslogin(rs.getString(7));
				 String name = pharmacyDAO.getLocationName(rs.getString(8));
				 userProfile.setLocation(name);
				 userProfile.setState(inventoryVendorDAO.getStateName(rs.getString(9)));
				 list.add(userProfile);
				 
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return list;
	}

	
	public UserProfile getPharmacyUserbyId(String id) {
		UserProfile userProfile= new UserProfile();
		try {
			
			String sql="select id,firstname,lastname,phone,userid,password,islogin,location,state from apm_pharmacy_user where id="+id+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				 userProfile.setId(rs.getInt(1));
				 userProfile.setFirstname(rs.getString(2));
				 userProfile.setLastname(rs.getString(3));
				 userProfile.setFullname(rs.getString(2)+" "+rs.getString(3));
				 userProfile.setPhone(rs.getString(4));
				 userProfile.setUserid(rs.getString(5));
				 userProfile.setPassword(rs.getString(6));
				 userProfile.setIslogin(rs.getString(7));
				 if(rs.getString(8)!=null){
					 userProfile.setLocation(rs.getString(8));
				 } else {
					 userProfile.setLocation("0");
				 }
				 userProfile.setState(rs.getString(9));
				 if(userProfile.getState()==null){
					 userProfile.setState("0");
				 }
				 String statename = getStateNameFromId(userProfile.getState());
				 userProfile.setStatename(statename);
				 
				 userProfile.setLocationname(getPharmacyLocation(userProfile.getLocation()));
				 
				 
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return userProfile;
	}
	public String getPharmacyLocation(String locid){
		
		String res="";
		try {
			
			String sql="select name from apm_pharmacy_location where id="+locid+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs =ps.executeQuery();
			while(rs.next()){
				
				res =rs.getString(1);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		 
		return res;
	}
	public String getStateNameFromId(String id) {
		String name ="";
		try {
			String query= "select name from apm_state where id="+id+"";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				name = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}
	
   public int updatePharmacyUserbyId(UserProfile userProfile) {
		
	    int result=0;
		try {
			
			String sql="update apm_pharmacy_user set firstname=?,lastname=?,phone=?,userid=?,password=?,location=?,state=? where id="+userProfile.getId()+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, userProfile.getFirstname());
			ps.setString(2, userProfile.getLastname());
			ps.setString(3, userProfile.getPhone());
			ps.setString(4, userProfile.getUserid());
			ps.setString(5, userProfile.getPassword());
			ps.setString(6, userProfile.getLocation());
			ps.setString(7, userProfile.getState());
			
			result =ps.executeUpdate();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
		
	}
	
	
	

	
	public int deletepharmacyuser(String id) {
		int result = 0;
		try {
			String query = "delete from apm_pharmacy_user where id="+id+"";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			result = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	public Access getopdRollAccess(String jobtitle) {
		PreparedStatement preparedStatement = null;
		Access access = new Access();
		String sql = "select opd_modify, opd_cancel, opd_prescription, opd_investigation, opd_ot, opd_viewaccount, opd_apmtfinder, opd_advandref, opd_modifydiagnosis, opd_editpatient, opd_log, opd_emr, opd_assessmentform, opd_treatment, opd_addcharges, opd_createinvoice, opd_recordpayment" +
				" from apm_role_access where usertype='"+jobtitle+"' ";
		
		try{
			preparedStatement  = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				access.setOpd_modify(rs.getBoolean(1));
				access.setOpd_cancel(rs.getBoolean(2));
				access.setOpd_prescription(rs.getBoolean(3));
				access.setOpd_investigation(rs.getBoolean(4));
				access.setOpd_ot(rs.getBoolean(5));
				access.setOpd_viewaccount(rs.getBoolean(6));
				access.setOpd_apmtfinder(rs.getBoolean(7));
				access.setOpd_advandref(rs.getBoolean(8));
				access.setOpd_modifydiagnosis(rs.getBoolean(9));
				access.setOpd_editpatient(rs.getBoolean(10));
				access.setOpd_log(rs.getBoolean(11));
				access.setOpd_emr(rs.getBoolean(12));
				access.setOpd_assessmentform(rs.getBoolean(13));
				access.setOpd_treatment(rs.getBoolean(14));
				access.setOpd_addcharges(rs.getBoolean(15));
				access.setOpd_createinvoice(rs.getBoolean(16));
				access.setOpd_recordpayment(rs.getBoolean(17));
				
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return access;
	}


	public Access getipdaccesss(String jobtitle) {
		PreparedStatement preparedStatement = null;
		Access access = new Access();
		String sql = "select ipd_admission, ipd_declaration, ipd_log, ipd_forms, ipd_discharge, ipd_emr, ipd_prescription, " +
				" ipd_investigation, ipd_reqconsultant, ipd_nursingcare, ipd_reqblood, ipd_autocare, ipd_treatmentlog, " +
				" ipd_addcharges, ipd_createinvoice, ipd_recordpayment, ipd_advandref, ipd_viewaccount, ipd_packages,cancel_admsn" +
				" from apm_role_access where usertype='"+jobtitle+"' ";
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				access.setIpd_admission(rs.getBoolean(1));
				access.setIpd_declaration(rs.getBoolean(2));
				access.setIpd_log(rs.getBoolean(3));
				access.setIpd_forms(rs.getBoolean(4));
				access.setIpd_discharge(rs.getBoolean(5));
				access.setIpd_emr(rs.getBoolean(6));
				access.setIpd_prescription(rs.getBoolean(7));
				access.setIpd_investigation(rs.getBoolean(8));
				access.setIpd_reqconsultant(rs.getBoolean(9));
				access.setIpd_nursingcare(rs.getBoolean(10));
				access.setIpd_reqblood(rs.getBoolean(11));
				access.setIpd_autocare(rs.getBoolean(12));
				access.setIpd_treatmentlog(rs.getBoolean(13));
				access.setIpd_addcharges(rs.getBoolean(14));
				access.setIpd_createinvoice(rs.getBoolean(15));
				access.setIpd_recordpayment(rs.getBoolean(16));
				access.setIpd_advandref(rs.getBoolean(17));
				access.setIpd_viewaccount(rs.getBoolean(18));
				access.setIpd_packages(rs.getBoolean(19));
				access.setCancel_admsn(rs.getBoolean(20));
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return access;
	}


	public Access getAccountaccesss(String jobtitle) {
		PreparedStatement preparedStatement = null;
		Access access = new Access();
		String sql = "select acc_createinvoice, acc_recordpayment, acc_viewcreditaccount, acc_advandref," +
				"  acc_chargeinvoice, acc_addcharges, acc_chargedetails,cash_desk,refund,unit_price" +
				" from apm_role_access where usertype='"+jobtitle+"'";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				access.setAcc_createinvoice(rs.getBoolean(1));
				access.setAcc_recordpayment(rs.getBoolean(2));
				access.setAcc_viewcreditaccount(rs.getBoolean(3));
				access.setAcc_advandref(rs.getBoolean(4));
				access.setAcc_chargeinvoice(rs.getBoolean(5));
				access.setAcc_addcharges(rs.getBoolean(6));
				access.setAcc_chargedetails(rs.getBoolean(7));
				access.setCash_desk(rs.getBoolean(8));
				access.setRefund(rs.getBoolean(9));
				access.setUnit_price(rs.getBoolean(10));
			}
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return access;
	}


	public Access getEmraccesss(String jobtitle) {
		PreparedStatement preparedStatement = null;
		Access access = new Access();
		String sql = "select emr_docs, emr_history, emr_medicine, emr_investigation, emr_pacs, emr_media, " +
				" emr_update, emr_print, emr_edit, emr_delete " +
				" from apm_role_access where usertype='"+jobtitle+"' ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				access.setEmr_docs(rs.getBoolean(1));
				access.setEmr_history(rs.getBoolean(2));
				access.setEmr_medicine(rs.getBoolean(3));
				access.setEmr_investigation(rs.getBoolean(4));
				access.setEmr_pacs(rs.getBoolean(5));
				access.setEmr_media(rs.getBoolean(6));
				access.setEmr_update(rs.getBoolean(7));
				access.setEmr_print(rs.getBoolean(8));
				access.setEmr_edit(rs.getBoolean(9));
				access.setEmr_delete(rs.getBoolean(10));
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return access;
	}


	public Access getClientAccesss(String jobtitle) {
		PreparedStatement preparedStatement = null;
		Access access = new Access();
		String sql = "select client_add, client_edit, client_delete, client_forms, " +
				" client_discharge, client_emai, client_print, client_treatment, client_log, " +
				" client_recordpayment, client_cashdesk, clientadvandref, client_addcharge, " +
				" client_viewaccount, client_emr,show_master from apm_role_access where usertype='"+jobtitle+"' ";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				access.setClient_add(rs.getBoolean(1));
				access.setClient_edit(rs.getBoolean(2));
				access.setClient_delete(rs.getBoolean(3));
				access.setClient_forms(rs.getBoolean(4));
				access.setClient_discharge(rs.getBoolean(5));
				access.setClient_emai(rs.getBoolean(6));
				access.setClient_print(rs.getBoolean(7));
				access.setClient_treatment(rs.getBoolean(8));
				access.setClient_log(rs.getBoolean(9));
				access.setClient_recordpayment(rs.getBoolean(10));
				access.setClient_cashdesk(rs.getBoolean(11));
				access.setClientadvandref(rs.getBoolean(12));
				access.setClient_addcharge(rs.getBoolean(13));
				access.setClient_viewaccount(rs.getBoolean(14));
				access.setClient_emr(rs.getBoolean(15));
				access.setShow_master(rs.getBoolean(16));
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return access;
	}


	public int getIdFromUserId(String userId) {
		int id =0;
		try {
			String sql ="select id from apm_user where userid='"+userId+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				id=rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}


	public int checkOldPharmacyPwd(UserProfile userProfile, String clinicUserid, String userid, String pwd) {
		int res = 0;
		try{
			PreparedStatement preparedStatement = null;
			String sql = "select userid,password from apm_user where userid='"+userid+"' and clinicid='"+clinicUserid+"' ";
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String userID = rs.getString(1);
				String password = rs.getString(2);
				String encPassword = Encryption.encryptSHA(pwd);
				if(encPassword.equals(password)){
					res=1;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}


	public int updatePharmacyUserPwdbyId(UserProfile userProfile) {
		  int result=0;
			try {
				/*String sql="update apm_pharmacy_user set phone=?,password=? where id="+userProfile.getId()+" ";
				PreparedStatement ps=connection.prepareStatement(sql);
				ps.setString(1, userProfile.getPhone());
				ps.setString(2, userProfile.getPassword());
				result =ps.executeUpdate();*/
				
				String encPassword = Encryption.encryptSHA(userProfile.getPassword());
				
				String sql1="update apm_user set mobile=?,password=? where id="+userProfile.getId()+" ";
				PreparedStatement ps1=connection.prepareStatement(sql1);
				ps1.setString(1, userProfile.getPhone());
				ps1.setString(2, encPassword);
				result =ps1.executeUpdate();
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
	}

	public int updateAdminPharmacyUsrwd(UserProfile userprofile, String clinicid,String userID) {
		PreparedStatement preparedStatement = null;
		int res = 0;
		String sql = "update apm_user set password=?,mobile=? where userid='"+userID+"' and clinicid='"+clinicid+"' ";
		try{
			preparedStatement = connection.prepareStatement(sql);
			String encPassword = Encryption.encryptSHA(userprofile.getPassword());
			preparedStatement.setString(1, encPassword);
			preparedStatement.setString(2, userprofile.getMobile());
			res = preparedStatement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}


	
	public ArrayList<Master> getLabnameList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list = new ArrayList<Master>();
		String sql = "select id,jobtitle from job_title ";
		
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


	public int saveInJobTitle(UserProfile userprofile) {
		PreparedStatement preparedStatement = null;
		int res = 0;
		try{
			String sql = "insert into job_title(jobtitle, jobgroup_id, userid) values(?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userprofile.getFirstname()+" "+userprofile.getLastname());
			preparedStatement.setString(2, "6");
			preparedStatement.setString(3, userprofile.getSelectedid());
			res = preparedStatement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}


	public int updateGlobalAccess(String jobtitle, String val) {
		 int result=0;
			try {
				String sql="update apm_role_access set globalaccess=? where usertype='"+jobtitle+"' ";
				PreparedStatement ps=connection.prepareStatement(sql);
				ps.setString(1, val);
				result =ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
	}


	public int updatewidegetstatus(String jobtitle, String val, String cname) {
		 int result=0; 
			try {
				String sql="update apm_role_access set "+cname+"="+val+" where usertype='"+jobtitle+"' ";
				PreparedStatement ps=connection.prepareStatement(sql);
				result =ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
	}

	public int updateactiveinactive(String id, String val) {
		 int result=0;
			try {
				String sql="update apm_user set islogin="+val+" where id='"+id+"' ";
				PreparedStatement ps=connection.prepareStatement(sql);
				result =ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
	}


	public boolean checkUserActive(String userid) {
		boolean flag = false;
		try {
			String sql ="select islogin from apm_user where userid='"+userid+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				flag = resultSet.getBoolean(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}


	public String getWardIdsFromUserid(String userid) {
		String string ="0";
		PreparedStatement preparedStatement = null;
		try{
			String sql = "select wardid from apm_user where userid='"+userid+"'";
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				string= rs.getString(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return string;
	}


	public int updatewardid(String userid, String val) {
		int result=0;
		try {
			if(val.equals("0")){
				val= "0,";
			}
			String sql="update apm_user set wardid='"+val+"' where userid='"+userid+"' ";
			PreparedStatement ps=connection.prepareStatement(sql);
			result =ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	public String getUserNameFromUserid(String string) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT firstname,lastname FROM apm_user where  userid = '"+string+"'";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				result = rs.getString(1)+" "+rs.getString(2);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	public int saveVisitingCosultant(UserProfile userprofile, int clinicid, String clinicName) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into apm_user(firstname,lastname,initial,jobtitle,discription," +
				"userid,password,mobile,usertype,clinicid,isvisitingdoctor,clinicname,visiconsultantfee,vivitingper,address,owner_qualification)" +
				" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userprofile.getFirstname());
			preparedStatement.setString(2, userprofile.getLastname());
			preparedStatement.setString(3, userprofile.getInitial());
			preparedStatement.setString(4, userprofile.getJobtitle());
			preparedStatement.setString(5, userprofile.getDiciplineName());
			preparedStatement.setString(6, "");
			preparedStatement.setString(7,"");
			preparedStatement.setString(8, userprofile.getMobile());
			preparedStatement.setInt(9, userprofile.getUserType());
			preparedStatement.setString(10,""+clinicid);
			preparedStatement.setBoolean(11, userprofile.isVisitingdoctor());
			preparedStatement.setString(12,clinicName);
			preparedStatement.setString(13, userprofile.getFees());
			preparedStatement.setString(14, userprofile.getSharepercentage());
			preparedStatement.setString(15, userprofile.getAddress());
			preparedStatement.setString(16, userprofile.getQualification());
			result = preparedStatement.executeUpdate();
			if(result>0)
			{
				ResultSet resultSet2=preparedStatement.getGeneratedKeys();
				while(resultSet2.next()){
					result= resultSet2.getInt(1);	
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	
		return result;
	}
	public ArrayList<UserProfile> getVisitingPractitinerList() {

		ArrayList<UserProfile> list=new ArrayList<UserProfile>();
		
		try {
			
			/*String sql="SELECT id,firstname,lastname,jobtitle FROM apm_user where jobtitle='Practitioner' or jobtitle='Visiting Consultant'";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				 
				 UserProfile profile=new UserProfile();
				 profile.setId(rs.getInt(1));
				 profile.setFirstname(rs.getString(2));
				 profile.setLastname(rs.getString(3));
				 profile.setJobtitle(rs.getString(4));
				 String fullname=profile.getFirstname()+" "+profile.getLastname();
				 profile.setFullname(fullname);
				 list.add(profile);
			}*/
			String sql="select id, name from reference where isvisitingconsultant='1'";
			
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				UserProfile userProfile = new UserProfile();
				userProfile.setId(rs.getInt(1));
				userProfile.setFullname(rs.getString(2));
				list.add(userProfile);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	
		return list;
	}


	public int checkdralreadypresent(String id) {
		int res = 0;
		try {
			String sql ="select userid from reference where userid='"+id+"'";
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


	public int saveReferralDoctor(UserProfile userprofile) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into reference(name, speciality, mobno, email, isvisitingconsultant, issurgeon, isanesthesiologist, "
				+ "fees, isrefered, userid,ismlc,mlcqualification,reference_shareammount, "
				+ "reference_sharetype, isfromvisitingdash, sharepercentage)" +
				" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userprofile.getFullname());
			preparedStatement.setString(2, userprofile.getDiciplineName());
			preparedStatement.setString(3, userprofile.getMobile());
			preparedStatement.setString(4, userprofile.getEmail());
			preparedStatement.setBoolean(5, userprofile.isIsvisitingconsultant());
			preparedStatement.setBoolean(6, userprofile.isIssurgeon());
			preparedStatement.setBoolean(7,userprofile.isIsanesthesiologist());
			preparedStatement.setString(8, userprofile.getFees());
			preparedStatement.setBoolean(9, userprofile.isIsreferred());
			preparedStatement.setString(10, userprofile.getExistingdrid());
			preparedStatement.setBoolean(11, userprofile.isIsmlc());
			preparedStatement.setString(12, userprofile.getQualification());
			preparedStatement.setString(13, userprofile.getRefshareammount());
			preparedStatement.setString(14, userprofile.getRefsharetype());
			preparedStatement.setString(15, ""+userprofile.getIsfromvisitingdashboard());
			preparedStatement.setString(16, userprofile.getSharepercentage());
			result = preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
	
		return result;
	}


	public ArrayList<UserProfile> checkExistConsultant(String val) {
		ArrayList<UserProfile> arrayList2 = new ArrayList<UserProfile>();
		try {
			String sql ="select id,name from reference where name like('%"+val+"%') and isfromvisitingdash=1 limit 15";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				UserProfile profile = new UserProfile();
				profile.setName(rs.getString(2));
				profile.setId(rs.getInt(1));
				arrayList2.add(profile);
				/*String name = rs.getString(1);
				int id = rs.getInt(2);
				arrayList.add(name);*/
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList2;
	}


	public int checkExistConsultantName(String consultantname) {
		int i =0;
		try {
			String sql ="select id from reference where name='"+consultantname+"' and isfromvisitingdash=1";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				i = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}


	public ArrayList<UserProfile> getAllPractitionerList(String issurgeon, String isanesthesiologist, String isvisitingconsultant, String isrefered, String ismlc) {
		PreparedStatement preparedStatement = null;
		ArrayList<UserProfile>list = new ArrayList<UserProfile>();
		StringBuilder builder = new StringBuilder();
		
		if(issurgeon!=null){
			builder.append("select userid, name from reference ");
			builder.append("where issurgeon='"+issurgeon+"'");
		}
		if(ismlc!=null){
			builder.append("select id, name from reference ");
			builder.append("where ismlc='"+ismlc+"'");
		}
		try{
			preparedStatement = connection.prepareStatement(builder.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				UserProfile userProfile = new UserProfile();
				if(rs.getInt(1)==0){
					continue;
				}
				userProfile.setId(rs.getInt(1));
				userProfile.setFullname(rs.getString(2));
				list.add(userProfile);
			}
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	public ArrayList<Client> getAllAnethesiaList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Client>list = new ArrayList<Client>();
		String sql = "select id, name from reference where isanesthesiologist='"+1+"'";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				Client client = new Client();
				client.setId(rs.getInt(1));
				client.setReference(rs.getString(2));
				
				list.add(client);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	public String getReferalDrName(String surgeon) {
		String name="";
		String sql = "select name from reference where id='"+surgeon+"'";
		
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				name=rs.getString(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}


	public UserProfile getrefereddrinfo(String id) {
		UserProfile userProfile = new UserProfile();
		try {
			String sql ="select id, name, speciality, mobno, email, isvisitingconsultant, "
					+ "issurgeon, isanesthesiologist, fees, isrefered, userid,ismlc,mlcqualification,"
					+ "reference_shareammount, reference_sharetype,sharepercentage from reference where id='"+id+"' and isfromvisitingdash=1";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				userProfile.setId(rs.getInt(1));
				userProfile.setName(rs.getString(2));
				userProfile.setSpecialization(rs.getString(3));
				userProfile.setMobile(rs.getString(4));
				userProfile.setEmail(rs.getString(5));
				userProfile.setIsvisitingconsultant(rs.getBoolean(6));
				userProfile.setIssurgeon(rs.getBoolean(7));
				userProfile.setIsanesthesiologist(rs.getBoolean(8));
				userProfile.setFees(rs.getString(9));
				userProfile.setIsreferred(rs.getBoolean(10));
				userProfile.setUserid(rs.getString(11));
				userProfile.setIsmlc(rs.getBoolean(12));
				userProfile.setQualification(rs.getString(13));
				userProfile.setReference_shareammount(rs.getString(14));
				userProfile.setReference_sharetype(rs.getString(15));
				userProfile.setSharepercentage(rs.getString(16));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userProfile;
	}


	public int updateReferralDoctor(UserProfile userprofile) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update reference set name=?, speciality=?, mobno=?, email=?, isvisitingconsultant=?, issurgeon=?, isanesthesiologist=?, fees=?, isrefered=?, userid=?,ismlc=?,mlcqualification=?,reference_sharetype=?,reference_shareammount=?,sharepercentage=? where id='"+userprofile.getReferid()+"'";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userprofile.getFullname());
			preparedStatement.setString(2, userprofile.getDiciplineName());
			preparedStatement.setString(3, userprofile.getMobile());
			preparedStatement.setString(4, userprofile.getEmail());
			preparedStatement.setBoolean(5, userprofile.isIsvisitingconsultant());
			preparedStatement.setBoolean(6, userprofile.isIssurgeon());
			preparedStatement.setBoolean(7,userprofile.isIsanesthesiologist());
			preparedStatement.setString(8, userprofile.getFees());
			preparedStatement.setBoolean(9, userprofile.isIsreferred());
			preparedStatement.setString(10, userprofile.getExistingdrid());
			preparedStatement.setBoolean(11, userprofile.isIsmlc());
			preparedStatement.setString(12, userprofile.getQualification());
			preparedStatement.setString(13, userprofile.getRefsharetype());
			preparedStatement.setString(14, userprofile.getRefshareammount());
			preparedStatement.setString(15, userprofile.getSharepercentage());
			result = preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
	
		return result;
	}


	public int updateLocalUserAdmin(String userId, String confirm_pwd) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "update apm_admin set password=? where userid='"+userId+"'";
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, confirm_pwd);
			result = preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
	
		return result;
	}


	public String getIndentApprovelocations(String userid) {
		String string ="";
		PreparedStatement preparedStatement = null;
		try{
			String sql = "select indentlocations from apm_user where userid='"+userid+"'";
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				string= rs.getString(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return string;
	}


	public int updateIndentLocations(String userid, String val) {
		int result=0;
		try {
			String sql="update apm_user set indentlocations='"+val+"' where userid='"+userid+"' ";
			PreparedStatement ps=connection.prepareStatement(sql);
			result =ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	public String getLocationFromDiciplineID(String diciplineName) {
		String location="";
		try {
				String sql1 ="select id from apm_condition where speciality='"+diciplineName+"'";
				PreparedStatement preparedStatement2 = connection.prepareStatement(sql1);
				ResultSet rs1 = preparedStatement2.executeQuery();
				while (rs1.next()) {
					location = rs1.getString(1);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return location;
	}


	public int getPractIdFromReferenceId(String practitionerid) {
		int id=0;
		try {
				String sql1 ="select userid from reference where id='"+practitionerid+"'";
				PreparedStatement preparedStatement2 = connection.prepareStatement(sql1);
				ResultSet rs1 = preparedStatement2.executeQuery();
				while (rs1.next()) {
					id = rs1.getInt(1);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}


	public boolean checkisshowdiscount(int clinicid) {
		boolean flag = false;
		try {
			String sql ="select isshowdiscount from apm_user where id='"+clinicid+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				flag = rs.getBoolean(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}


	public UserProfile getUserIndivisualAccess(String userid) {
		UserProfile userProfile = new UserProfile();
		try {
			String sql ="select refund_dashboard from apm_user where userid='"+userid+"' ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				userProfile.setRefund_dashboard(rs.getBoolean(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userProfile;
	}


	public int updateuserindivisualstatus(String userid, String val, String cname) {
		int result=0; 
		try {
			String sql="update apm_user set "+cname+"="+val+" where userid='"+userid+"' ";
			PreparedStatement ps=connection.prepareStatement(sql);
			result =ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updatemiswidegetstatus(String userid, String val, String cname) {
		   int result=0; 
		   try {
			 String sql="update mis_role_access set "+cname+"="+val+" where users='"+userid+"' ";
		    PreparedStatement ps=connection.prepareStatement(sql);
		 /*   if(result== 0){
				sql="insert into mis_role_access(users,"+cname+") values('"+userid+"','"+val+"') ";
				 ps=connection.prepareStatement(sql);
				result=ps.executeUpdate();
			}*/
		    result =ps.executeUpdate();
		   } catch (Exception e) {
		    e.printStackTrace();
		   }
		   return result;
		 }
	public Access getMisRoleaccess(String userid) {
		  PreparedStatement ps = null;
		  Access access= new Access();
		  
		  ArrayList<String> reports= null;
		  try{
		   StringBuffer sb= new StringBuffer();
		   sb.append("select ");
		   reports= getallReportNames();
		   int s=reports.size()-1;
		   int i=0;
		   for(String report: reports){
		    sb.append(" "+report);
		    ++i;
		    if(s>=i){
		     sb.append(",");
		    }
		   }
		   sb.append(" from mis_role_access where users='"+userid+"'");
		   
		   String sql=  sb.toString();
		   ps= connection.prepareStatement(sql);
		   ResultSet rs= ps.executeQuery();
		   while(rs.next()){
		    access.setPractioner_share(rs.getBoolean(1));
		    access.setOpd_practioner_share(rs.getBoolean(2));
		    access.setCharges(rs.getBoolean(3));
		    access.setInvoice(rs.getBoolean(4));
		    access.setPayment_report_detailed(rs.getBoolean(5));
		    access.setPayment_report_small(rs.getBoolean(6));
		    access.setAdd_debtors(rs.getBoolean(7));
		    access.setCa(rs.getBoolean(8));
		    access.setAuditors(rs.getBoolean(9));
		    access.setUserwise_payment(rs.getBoolean(10));
		    access.setDeptwise_analysis(rs.getBoolean(11));
		    access.setCharges_share(rs.getBoolean(12));
		    access.setBilling(rs.getBoolean(13));
		    access.setDiscount(rs.getBoolean(14));
		    access.setCancel_invoice(rs.getBoolean(15));
		    access.setPayment(rs.getBoolean(16));
		    access.setKpi_dashboard(rs.getBoolean(17));
		    access.setTreatment_episode_list(rs.getBoolean(18));
		    access.setPatient_condition_list(rs.getBoolean(19));
		    access.setDtr_report(rs.getBoolean(20));
		    access.setPatientlist(rs.getBoolean(21));
		    access.setCurrent_track_with_no_future_ampts(rs.getBoolean(22));
		    access.setNo_appointment_activity_record(rs.getBoolean(23));
		    access.setDna_with_no_future_appointment(rs.getBoolean(24));
		    access.setNo_activity_record(rs.getBoolean(25));
		    access.setDna_analysiis(rs.getBoolean(26));
		    access.setAppointment_kept_vs_dna(rs.getBoolean(27));
		    access.setTreatment_state_by_refferal(rs.getBoolean(28));
		    access.setReturning_patients(rs.getBoolean(29));
		    access.setOutcome_discharge(rs.getBoolean(30));
		    access.setDeathreport(rs.getBoolean(31));
		    access.setCurrent_patient_report(rs.getBoolean(32));
		    access.setIpd_daily_report(rs.getBoolean(33));
		    access.setIpd_monthly_report(rs.getBoolean(34));
		    access.setBed_occupancy_report(rs.getBoolean(35));
		    access.setReffered_by(rs.getBoolean(36));
		    access.setMlc(rs.getBoolean(37));
		    access.setReport_outstandng(rs.getBoolean(38));
		    access.setNow_patients(rs.getBoolean(39));
		    access.setTotal_patients_seen(rs.getBoolean(40));
		    access.setDna_outstanding_action(rs.getBoolean(41));
		    access.setSales_report(rs.getBoolean(42));
		    access.setPayment_recive(rs.getBoolean(43));
		    access.setInventory_opening(rs.getBoolean(44));
		    access.setItemwise_stock(rs.getBoolean(45));
		    access.setPurchase_report(rs.getBoolean(46));
		    access.setExpiry_medicine_report(rs.getBoolean(47));
		    access.setGrn(rs.getBoolean(48));
		    access.setIndent_statement(rs.getBoolean(49));
		    access.setIpd_daily_discharge(rs.getBoolean(50));
		    access.setOpd_ipd_cancel_refund(rs.getBoolean(51));
		    access.setIpd_bill_register(rs.getBoolean(52));
		    access.setService_register_details(rs.getBoolean(53));
		    access.setCancel_invoice_report(rs.getBoolean(54));
		    access.setKPI_report(rs.getBoolean(55));
		    access.setRefund_report(rs.getBoolean(56));
		    access.setPayment_combined_report(rs.getBoolean(57));
		    access.setPayment_receipt_report(rs.getBoolean(58));
		    access.setDeptwise_payment_report(rs.getBoolean(59));
		    access.setDoctor_share_report(rs.getBoolean(60));
		    access.setOpd_ipd_report(rs.getBoolean(61));
		    access.setVisiting_consultation_report(rs.getBoolean(62));
		    access.setCharges_detaile_report(rs.getBoolean(63));
		    access.setPractitioner_share_report(rs.getBoolean(64));
		    access.setOut_source_report(rs.getBoolean(65));
		    access.setLab_report(rs.getBoolean(66));
		    access.setInvestigation_count_report(rs.getBoolean(67));
		    access.setInvestigation_package_report(rs.getBoolean(68));
		    access.setInvstwise_count_report(rs.getBoolean(69));
		    access.setInvestigation_revenue_report(rs.getBoolean(70));
		    access.setInvst_revcount_namewise(rs.getBoolean(71));
		    access.setInvst_opdipd_report(rs.getBoolean(72));
		    access.setInvst_tat_report(rs.getBoolean(73));
		    access.setInvst_criticalval_report(rs.getBoolean(74));
		    access.setItem_wise_sale_report(rs.getBoolean(75));
		    access.setPatient_consumption_report(rs.getBoolean(76));
		    access.setPayable_aging_report(rs.getBoolean(77));
		    access.setStock_report(rs.getBoolean(78));
		    access.setConsumption_report(rs.getBoolean(79));
		    access.setCathlab_stock_report(rs.getBoolean(80));
		    access.setDetail_grn_report(rs.getBoolean(81));
		    access.setCathlab_opening_closing(rs.getBoolean(82));
		    access.setOpd_tat_report(rs.getBoolean(83));
		    access.setBirthplace_report(rs.getBoolean(84));
		    access.setTotal_revenue_report(rs.getBoolean(85));
		    access.setWard_wise_rev_report(rs.getBoolean(86));
		    access.setRevenue_matrix(rs.getBoolean(87));
		    access.setAdmission_discharge(rs.getBoolean(88));
		    access.setDeptwise_revenue_report(rs.getBoolean(89));
		    access.setConsultation_report(rs.getBoolean(90));
		    access.setBed_occupancy_per_day(rs.getBoolean(91));
		    access.setRender_charges_report(rs.getBoolean(92));
		    access.setInventory_openclose_report(rs.getBoolean(93));
		    access.setDetailed_inventory_openclose_report(rs.getBoolean(94));
		    access.setInventory_openclose_catalogue(rs.getBoolean(95));
		    access.setInventory_stock_report(rs.getBoolean(96));
		    access.setInventory_sale_report(rs.getBoolean(97));
		    access.setPayment_receive_report(rs.getBoolean(98));
		    access.setInventory_itemwise_sale(rs.getBoolean(99));
		    access.setProductwise_sale_report(rs.getBoolean(100));
		    access.setCataloguewise_sale_report(rs.getBoolean(101));
		    access.setGst_report(rs.getBoolean(102));
		    access.setGrn_gst_report(rs.getBoolean(103));
		    access.setSupplier_payment_report(rs.getBoolean(104));
		    access.setInventory_report(rs.getBoolean(105));
		   }
		   
		  }catch(Exception e){
		   
		  e.printStackTrace(); 
		  }
		  
		  return access;
		 }
	public ArrayList<String> getallReportNames(){
		 PreparedStatement ps= null;
		 ArrayList<String> reports= new ArrayList<String>();
		 
		 try{
		  String sql="select access from mis_role";
		  ps= connection.prepareStatement(sql);
		  ResultSet rs= ps.executeQuery();
		  while(rs.next())
		  {
		   String report= rs.getString(1);
		   reports.add(report);
		  }
		  
		  
		 }catch(Exception e){
		  e.printStackTrace();
		 }
		 return reports;
		}
	public ArrayList<UserProfile>	getallbirthdays(String clinicname, String date) {
		ArrayList<UserProfile> list= new  ArrayList<UserProfile>();
		PreparedStatement ps= null;
		String  sql="";
		try{			sql="select initial ,firstname , lastname from apm_user where clinicname='"+clinicname+"' and dob like '%"+date+"%' ";
			ps= connection.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				UserProfile up= new UserProfile();
				up.setName(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3));
				list.add(up);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}


	public ArrayList<UserProfile> getVisitingPractitinerListFromUser() {
ArrayList<UserProfile> list=new ArrayList<UserProfile>();
		try {
			String sql="SELECT id,firstname,lastname,jobtitle,userid FROM apm_user where  (jobtitle='Visiting Consultant' or isvisitingdoctor=1) and islogin=1";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				 UserProfile profile=new UserProfile();
				 profile.setId(rs.getInt(1));
				 profile.setFirstname(rs.getString(2));
				 profile.setLastname(rs.getString(3));
				 profile.setJobtitle(rs.getString(4));
				 String fullname=profile.getFirstname()+" "+profile.getLastname();
				 profile.setFullname(fullname);
				 profile.setUserid(rs.getString(5));
				 list.add(profile);
			}
			/*String sql="select id, name from reference where isvisitingconsultant='1'";
			
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				UserProfile userProfile = new UserProfile();
				userProfile.setId(rs.getInt(1));
				userProfile.setFullname(rs.getString(2));
				list.add(userProfile);
			}*/
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	
		return list;
	}


	public int checkIsValidReg(UserProfile user, String id) {
		int result=0;
		PreparedStatement ps= null;
		try{
			String sql="select userid from apm_user where userid='"+user.getUserid()+"' and clinicname='"+id+"'";
			ps= connection.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				rs.getString(1);
				result= 1;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	public ArrayList<UserProfile> getAllUserList() {
		ArrayList<UserProfile> arrayList = new ArrayList<UserProfile>();
		try {
			String sql ="select id,concat(initial,' ',firstname,' ',lastname),userid,jobtitle from apm_user order by firstname";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				UserProfile profile = new UserProfile();
				profile.setId(rs.getInt(1));
				profile.setName(rs.getString(2));
				profile.setUserid(rs.getString(3));
				
				if(rs.getString(4)!=null){
					if(rs.getString(4).equals("Pharmacist")){
						String name = getPharmacyFullname(rs.getString(3));
						profile.setName(name);
					}
				}
				
				arrayList.add(profile);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}


	public String getPharmacyFullname(String string) {
		String res="";
		try {
			String sql="select concat(firstname,' ',lastname),name from apm_pharmacy_user inner join apm_condition on apm_pharmacy_user.location = apm_condition.id where userid='"+string+"' ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs =ps.executeQuery();
			while(rs.next()){
				res =rs.getString(1)+"("+rs.getString(2)+")";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}


	public ArrayList<UserProfile> getAllPharmacyUserListNew() {
		ArrayList<UserProfile> list= new ArrayList<UserProfile>();
		PharmacyDAO pharmacyDAO= new JDBCPharmacyDAO(connection);
		InventoryVendorDAO inventoryVendorDAO= new JDBCInventoryVendorDAO(connection);
		try {
			
			String sql="select id,firstname,lastname,phone,userid,password,islogin,location,state from apm_pharmacy_user ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				  
				 UserProfile userProfile= new UserProfile();
				 userProfile.setId(rs.getInt(1));
				 userProfile.setFirstname(rs.getString(2));
				 userProfile.setLastname(rs.getString(3));
				 userProfile.setFullname(rs.getString(2)+" "+rs.getString(3));
				 userProfile.setPhone(rs.getString(4));
				 userProfile.setUserid(rs.getString(5));
				 userProfile.setPassword(rs.getString(6));
				 userProfile.setIslogin(rs.getString(7));
				 String name = pharmacyDAO.getLocationName(rs.getString(8));
				 userProfile.setLocation(name);
				 userProfile.setState(inventoryVendorDAO.getStateName(rs.getString(9)));
				 list.add(userProfile);
				 
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return list;
	}


	public Priscription getpharmaAdminaccess() {
		Priscription prisc = new Priscription();
		PreparedStatement ps= null;
		try {
			String sql="select isdotmatrix from apm_pharmacy_user where id=1";
			ps= connection.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				prisc.setIsdotmatrix(rs.getBoolean(1));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prisc;
	}


	public int updateUserWiseAccess(String userid, String val, String dbcol) {
		 int result=0; 
			try {
				String sql="update apm_user set "+dbcol+"="+val+" where userid='"+userid+"' ";
				PreparedStatement ps=connection.prepareStatement(sql);
				result =ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
	}

	
	@Override
	public int saveUserWiseAccessLog(String accessgiven_userId, String datetime, String userid, String val, String dbcol,String showingcol_name) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		int res =0;
		
		try {
			
			String sql="insert into userwise_log (userid,given_userid_access,date_time,column_name,showing_column_name,value)values(?,?,?,?,?,?)"; 
					
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, userid);
			preparedStatement.setString(2, accessgiven_userId);
			preparedStatement.setString(3, datetime);
			preparedStatement.setString(4, dbcol);
			preparedStatement.setString(5, showingcol_name);
			preparedStatement.setString(6, val);
			
			res = preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	
	public DiaryManagement getMainAccessofUser(String userid) {
		DiaryManagement diaryManagement= new DiaryManagement();
		PreparedStatement ps= null;
		try {
			String sql="select modify_disc,edit_paypo,adjustmentaccess,supplier_add,direct_refund_disc,"
					+ "ref_dis_pay,prisc_new_req_access,additional_disc,cancel_invoice,add_prisc_medicine,"
					+ "payrollaccess,userwiseaceess from apm_user where userid='"+userid+"'";
			ps=connection.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				diaryManagement.setModify_disc(rs.getBoolean(1));
				diaryManagement.setEdit_paypo(rs.getBoolean(2));
				diaryManagement.setAdjustmentaccess(rs.getBoolean(3));
				diaryManagement.setSupplier_add(rs.getBoolean(4));
				diaryManagement.setDirect_refund_disc(rs.getBoolean(5));
				diaryManagement.setRef_dis_pay(rs.getBoolean(6));
				diaryManagement.setPrisc_new_req_access(rs.getBoolean(7));
				diaryManagement.setAdditional_disc(rs.getBoolean(8));
				diaryManagement.setCancel_invoice(rs.getBoolean(9));
				diaryManagement.setAdd_medicine(rs.getBoolean(10));
				diaryManagement.setUserwiseaceess(rs.getInt(12));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return diaryManagement;
	}
	
	public UserProfile getMainAccessByUserid(String userid) {
		UserProfile userProfile= new UserProfile();
		PreparedStatement ps= null;
		try {
			String sql="select editcharges from apm_user where userid='"+userid+"'";
			ps=connection.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				userProfile.setEditcharges(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userProfile;
	}


	public Access getXMLAccess(String clinicid,String filepath) {
		Access access= new Access();
		try{
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			
			Document doc = docBuilder.parse (new File(filepath+"/support/pages/accessdb.xml"));
			
			doc.getDocumentElement ().normalize ();
//			System.out.println ("Root element of the doc is " + doc.getDocumentElement().getNodeName());
			
			XPathFactory xpathfactory = XPathFactory.newInstance();
	        XPath xpath = xpathfactory.newXPath();
	        
	        XPathExpression expr = xpath.compile("//clinic[@id='"+clinicid+"']");
	        Object result = expr.evaluate(doc, XPathConstants.NODESET);
	        NodeList nodes = (NodeList) result;
	        for(int i=0;i<nodes.getLength();i++){
	        	org.w3c.dom.Node node=nodes.item(i);
	        	Element element=(Element) node.getChildNodes();
	        	String investshow= element.getElementsByTagName("investigation-hidecolumn").item(0).getTextContent();
//	        	System.out.println(investshow);
	        	access.setInvestigation_newaccess(investshow);
	        }
				
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return access;
	}


	public String getUserFullNameFromId(String string) {
		String res ="";
		try {
			String sql ="select concat(initial,' ',firstname,' ',lastname) from apm_user where id='"+string+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				res = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}


	public boolean supportAccess(String uesrid) {
		boolean status=false;
		try {
			PreparedStatement ps= connection.prepareStatement("select support_access from apm_user where userid='"+uesrid+"'");
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				status=rs.getBoolean(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}


	public UserProfile getIndivdualAccess(String userid) {
		UserProfile useraccess= new UserProfile();
		try {
			String sql=" select payment_report,ref_dis_pay,add_manual_charge,update_investigation_charge,"
					+ "invoicemodify,additional_disc,indv_discount,cancel_invoice,payrollaccess,"
					+ "add_prisc_medicine,proc_after_stock,id,edit_paypo,adjustmentaccess,"
					+ "supplier_add,change_indent_product,max_phar_discount,add_charge_amt_edit,"
					+ "edit_catalogue_name,adjustment_approve,edit_approve,multi_edit_product,"
					+ "multi_adjust_approve,edit_product,opd_user_vid_access,dr_opd_vid,pprice_sale,prisc_temp_showother,"
					+ "userwiseaceess,refund_dashboard,prisc_new_req_access,modify_disc,direct_refund_disc,acaccess,"
					+ "editcharges,freeze_unfreeze,manual_sms_vaccine,editpharmacy_bill,admsn_date_edit,user_language_access,"
					+ "delete_invoice_history,show_dept_opd_list,lmh_consultation_charge, lmh_other_charge, "
					+ "lmh_discount,dept_opd_report,indent_self_approve,indent_all_approve,inventory_report_access,supplier,mrp,sale_price_access, "
					+ "gst_access,purchase_price_access,sale_value_access,net_rate_access,opd_discount_access "
					+ "from apm_user where userid='"+userid+"'";
			PreparedStatement ps= connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				useraccess.setPaymentReport(rs.getBoolean(1));
				useraccess.setRef_dis_pay(rs.getBoolean(2));
				useraccess.setAdd_manual_charge(rs.getBoolean(3));
				useraccess.setUpdate_investigation_charge(rs.getBoolean(4));
				useraccess.setInvoicemodify(rs.getBoolean(5));
				useraccess.setAdditional_disc(rs.getBoolean(6));
				useraccess.setIndv_discount(rs.getBoolean(7));
				useraccess.setCancel_invoice_new(rs.getBoolean(8));
				useraccess.setPayrollaccess(rs.getBoolean(9));
				useraccess.setAdd_medicine(rs.getBoolean(10));
				useraccess.setProc_after_stock(rs.getBoolean(11));
				useraccess.setId(rs.getInt(12));
				useraccess.setEdit_paypo(rs.getBoolean(13));
				useraccess.setAdjustmentaccess(rs.getBoolean(14));
				useraccess.setSupplier_add(rs.getBoolean(15));
				useraccess.setChange_indent_product(rs.getBoolean(16));
				useraccess.setMax_phar_discount(rs.getBoolean(17));
				useraccess.setAdd_charge_amt_edit(rs.getBoolean(18));
				useraccess.setEdit_catalogue_name(rs.getBoolean(19));
				useraccess.setAdjustment_approve(rs.getBoolean(20));
				useraccess.setEdit_approve(rs.getBoolean(21));
				useraccess.setMulti_edit_product(rs.getBoolean(22));
				useraccess.setMulti_adjust_approve(rs.getBoolean(23));
				useraccess.setEdit_product(rs.getBoolean(24));
				useraccess.setOpd_user_vid_access(rs.getBoolean(25));
				useraccess.setDr_opd_vid(rs.getBoolean(26));
				useraccess.setPprice_sale(rs.getBoolean(27));
				useraccess.setPrisc_temp_showother(rs.getBoolean(28));
				useraccess.setUserwiseaceess(rs.getInt(29));
				//access from jobtitle dashboard
				useraccess.setRefund_dashboard(rs.getBoolean(30));
				useraccess.setPrisc_new_req_access(rs.getBoolean(31));
				useraccess.setModify_disc(rs.getBoolean(32));
				useraccess.setDirect_refund_disc(rs.getBoolean(33));
				useraccess.setAcaccess(rs.getString(34));
				useraccess.setEditcharges(rs.getString(35));
				useraccess.setFreeze_unfreeze(rs.getBoolean(36));
				useraccess.setManual_sms_vaccine(rs.getBoolean(37));
				useraccess.setEditpharmacy_bill(rs.getBoolean(38));
				useraccess.setAdmsn_date_edit(rs.getBoolean(39));
				useraccess.setUser_language_access(rs.getString(40));
				useraccess.setDelete_invoice_history(rs.getBoolean(41));
				useraccess.setShow_dept_opd_list(rs.getString(42));
				useraccess.setLmh_consultation_charge(rs.getBoolean(43));
				useraccess.setLmh_other_charge(rs.getBoolean(44));
				useraccess.setLmh_discount(rs.getBoolean(45));
				useraccess.setDeptOpdReport(rs.getBoolean(46));
				useraccess.setIndent_self_approve(rs.getBoolean(47));
				useraccess.setIndent_all_approve(rs.getBoolean(48));
				useraccess.setInventory_report_access(rs.getBoolean(49));
				useraccess.setSupplier_access(rs.getBoolean(50));
				useraccess.setMrp_access(rs.getBoolean(51));
				useraccess.setSale_price_access(rs.getBoolean(52));
				useraccess.setGst_access(rs.getBoolean(53));
				useraccess.setPurchase_price_access(rs.getBoolean(54));
				useraccess.setSale_value_access(rs.getBoolean(55));
				useraccess.setNet_rate_access(rs.getBoolean(56));
				useraccess.setOpd_discount_access(rs.getBoolean(57));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return useraccess;
	}


	public boolean getWeekVaccineAccess() {
		boolean access=false;
		try {
			PreparedStatement ps= connection.prepareStatement(" select  vaccine_alert_week from apm_user where id=1 ");
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				access=rs.getBoolean(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return access;
	}


	public int insertToUserPaymentReportNotes(LoginInfo loginInfo, String note, String date) {
		int res=0;
		try {
			String sql=" insert into paymentreport_notes(notes,status,reqdate,userid) values(?,?,?,?)";
			
			PreparedStatement ps= connection.prepareStatement(""+sql);
			ps.setString(1, note);
			ps.setString(2, "0");
			ps.setString(3, date);
			ps.setString(4, loginInfo.getUserId());
			res=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}


	public boolean ifEntryExistUserPaymentReportNotes(String userid, String date) {
		boolean  flag=false;
		try {
			String reqdt[]=date.split(" ");
			PreparedStatement ps= connection.prepareStatement(" select * from paymentreport_notes where userid='"+userid+"' and reqdate like '"+reqdt[0]+" %'");
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				flag=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}


	public int updateStatusUserPaymentReportNotes(LoginInfo loginInfo, String status, String date, String id, String notes) {
		// TODO Auto-generated method stub
		//Status 0 = Requested, 1=Approved, 2=Pending
		int res=0;
		try {
			String col1="",col2="";
			if(status.equals("0")){
				col1="userid";
				col2="reqdate";
			}else if(status.equals("1")){
				col1="approver_userid";
				col2="approve_date";
			}else if(status.equals("2")){
				col1="pending_userid";
				col2="pending_date";
				
			}
			PreparedStatement ps= connection.prepareStatement(" update paymentreport_notes set "+col1+"='"+loginInfo.getUserId()+"' , status='"+status+"', "+col2+"='"+date+"' where id='"+id+"' ");
			if(status=="0"&&!(notes==null)){
				ps=connection.prepareStatement(" update paymentreport_notes set notes=? where id='"+id+"'");
				ps.setString(1, notes);
			}
			res=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}


	public UserProfile getPaymentReportNotesData(String id) {
		UserProfile userProfile= new UserProfile();
		try {
			
			String sql="select * from paymentreport_notes where id='"+id+"'";
			PreparedStatement ps= connection.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			while (rs.next()) {
				userProfile.setPaymentReportNoteId(rs.getString("id"));
				userProfile.setPaymentReportNote(DateTimeUtils.isNull(rs.getString("notes")));
				userProfile.setPaymentReportNoteUserid(DateTimeUtils.isNull(rs.getString("userid")));
				userProfile.setPaymentReportNoteReqDate(DateTimeUtils.isNull(rs.getString("reqdate")));
				userProfile.setPaymentReportNoteApproveDate(DateTimeUtils.isNull(rs.getString("approve_date")));
				userProfile.setPaymentReportNoteApprovedUserID(DateTimeUtils.isNull(rs.getString("approver_userid")));
				userProfile.setPaymentReportNotePendingDate(DateTimeUtils.isNull(rs.getString("pending_date")));
				userProfile.setPaymentReportNotePendingUserId(DateTimeUtils.isNull(rs.getString("pending_userid")));
				userProfile.setPaymentReportNoteStatus(DateTimeUtils.isNull(rs.getString("status")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userProfile;
	}


	public int getIdOfPaymentReportNotes(String userid, String date) {
		int id=0;
		try {
			String reqdt[]=date.split(" ");
			PreparedStatement ps= connection.prepareStatement(" select id from  paymentreport_notes where  reqdate like '"+reqdt[0]+" %' and userid='"+userid+"'");
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				id=rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}


	public int saveEmployeeDetails(UserProfile userprofile) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		int i = 0;
		PayrollEmployeeDAO employeeDAO=new JDBCPayrollEmployeeDAO(connection);
		String sql = "insert into apm_payroll_employee(name,userid,email,mobno,dob,empcode)" +
				" values(?,?,?,?,?,?)";
		
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userprofile.getFirstname()+" "+userprofile.getLastname());
			preparedStatement.setString(2, userprofile.getUserid());
			preparedStatement.setString(3, userprofile.getEmail());
			preparedStatement.setString(4, userprofile.getMobile());
			preparedStatement.setString(5, userprofile.getDob());
			preparedStatement.setString(6, userprofile.getEmpid());
			i = preparedStatement.executeUpdate();
			if(i>0){
				
				   ResultSet rs1= preparedStatement.getGeneratedKeys(); 
				   
				   while(rs1.next()) {
					   result=rs1.getInt(1);
				   }
			}
			 result = employeeDAO.addToSalary(result);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	
		return i;
	}


	public int updatePayrollEmpaxtive(String userid, String val) {
		 int result=0;
			try {
				String sql="update apm_payroll_employee set status="+val+" where userid='"+userid+"' ";
				PreparedStatement ps=connection.prepareStatement(sql);
				result =ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
	}


	public ArrayList<UserProfile> getSurgenList(String issurgeon) {
		PreparedStatement preparedStatement = null;
		ArrayList<UserProfile>list = new ArrayList<UserProfile>();
		StringBuilder builder = new StringBuilder();
		
		if(issurgeon!=null){
			builder.append("select userid, name from reference ");
			builder.append("where issurgeon='"+issurgeon+"' and userid!=0");
		}
		try{
			preparedStatement = connection.prepareStatement(builder.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				UserProfile userProfile = new UserProfile();
				userProfile.setId(rs.getInt(1));
				userProfile.setFullname(rs.getString(2));
				list.add(userProfile);
	}
	
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	public int saveUserForMisAccess(String userId) {
		int res=0;
		try {
		PreparedStatement ps=connection.prepareStatement(" insert into mis_role_access(users) values('"+userId+"')");	
		res=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}


	public boolean isExistInMisAccess(String userId) {
		boolean flag=false;
		try {
		PreparedStatement ps= connection.prepareStatement(" select * from mis_role_access where users='"+userId+"'");	
		ResultSet rs=ps.executeQuery();
		while (rs.next()) {
			flag=true;
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}


	public String getClinicLinkAddress(String clinicUserid) {
		String val="";
		try {
			String sql="select linkaddress from admin.apm_user where userid='"+clinicUserid+"' ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				val = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return val;
	}


	public ArrayList<UserProfile> getAllUserListNew() {
		ArrayList<UserProfile> arrayList = new ArrayList<UserProfile>();
		try {
			String sql ="select id,concat(initial,' ',firstname,' ',lastname),userid from apm_user where userid is not null and userid!='' and concat(initial,' ',firstname,' ',lastname) is not null order by firstname";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				UserProfile profile = new UserProfile();
				profile.setId(rs.getInt(1));
				profile.setName(rs.getString(2));
				profile.setUserid(rs.getString(3));
				arrayList.add(profile);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}


	public int setisvisitingDoctor(UserProfile userProfile) {
		int res=0;
		try {
		PreparedStatement ps=connection.prepareStatement(" update apm_user set isvisitingdoctor=1 where id="+userProfile.getExistingdrid()+" ");	
		res=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}


	public String getSpeciality(String diaryuserId) {
		String val="";
		try {
			String sql="select discription from apm_user where id='"+diaryuserId+"' ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				val = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return val;
	}


	public String getdrnamewithqualification(String diaryuserId) {
		String val="";
		try {
			String sql="select concat(initial,' ',firstname,' ',lastname,' (',owner_qualification,')') from apm_user where id ="+diaryuserId+"";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				val = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return val;
	}


	public ArrayList<UserProfile> getPharmacyDoctorList() {
		ArrayList<UserProfile> arrayList = new ArrayList<UserProfile>();
		try {
			String sql ="select id,concat(initial,' ',firstname,' ',lastname) from apm_user where initial='Dr.' or jobtitle='Pharmacy Doctor' order by firstname,lastname";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				UserProfile profile = new UserProfile();
				profile.setId(rs.getInt(1));
				profile.setName(rs.getString(2));
				arrayList.add(profile);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}


	public int checkUseridInRoleAccess(String userid) {
		int res =0;
		try {
			String sql ="select id from apm_role_access where usertype='"+userid+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				res =1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}


	public int insertUserTypeInRoleAccess(String userid, String jobtitle) {
		int res =0;
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("INSERT INTO apm_role_access ");
			buffer.append("(usertype, diarymanagement, appointmentbooking, basicfinance, fullfinance, medicalrecord,");
			buffer.append("communication, report, assessmentForm, manageclient, manageclinic, managemaster, manageprisc,");
			buffer.append("manageinvst, manageipd, manageopd, apmtfinder, manageemr, expences, managemis, payroll,");
			buffer.append("bloodbak, inventory, discharge, packs, investigation_chart, sheduler, housekeeping, dietery,");
			buffer.append("cafeteria, packages, ambulance, bank_deposite, account_reconcilation, ot, casualty, pharmacy,");
			buffer.append("mrd, marketing, voice_recorder, opd_modify, opd_cancel, opd_prescription, opd_investigation,");
			buffer.append("opd_ot, opd_viewaccount, opd_apmtfinder, opd_advandref, opd_modifydiagnosis, opd_editpatient,");
			buffer.append("opd_log, opd_emr, opd_assessmentform, opd_treatment, opd_addcharges, opd_createinvoice, opd_recordpayment,");
			buffer.append("ipd_admission, ipd_declaration, ipd_log, ipd_forms, ipd_discharge, ipd_emr, ipd_prescription,");
			buffer.append("ipd_investigation, ipd_reqconsultant, ipd_nursingcare, ipd_reqblood, ipd_autocare, ipd_treatmentlog,");
			buffer.append("ipd_addcharges, ipd_createinvoice, ipd_recordpayment, ipd_advandref, ipd_viewaccount, ipd_packages,");
			buffer.append("acc_createinvoice, acc_recordpayment, acc_viewcreditaccount, acc_advandref, acc_chargeinvoice, acc_addcharges,");
			buffer.append("acc_chargedetails, emr_docs, emr_history, emr_medicine, emr_investigation, emr_pacs, emr_media, emr_update,");
			buffer.append("emr_print, emr_edit, emr_delete, client_add, client_edit, client_delete, client_forms, client_discharge,");
			buffer.append("client_emai, client_print, client_treatment, client_log, client_recordpayment, client_cashdesk,");
			buffer.append("clientadvandref, client_addcharge, client_viewaccount, client_emr, globalaccess, indent_access,");
			buffer.append("investigation_approve, daily_opd, indent_approve, cash_desk, tpa, nabh_quality, doctor_opd,");
			buffer.append("acc_refund, show_master, invst_collect, token_display, cancel_admsn, add_medicine, pharm_print_backdate,");
			buffer.append("cathlab, myhr, daycare, emergency_lbl, medicine_barcode, refund, discount, vaccination, video_training,");
			buffer.append("setup_master, sale_pharmacy) ");
			buffer.append("SELECT '"+userid+"', diarymanagement, appointmentbooking, basicfinance, fullfinance, medicalrecord,");
			buffer.append("communication, report, assessmentForm, manageclient, manageclinic, managemaster, manageprisc,");
			buffer.append("manageinvst, manageipd, manageopd, apmtfinder, manageemr, expences, managemis, payroll,");
			buffer.append("bloodbak, inventory, discharge, packs, investigation_chart, sheduler, housekeeping, dietery,");
			buffer.append("cafeteria, packages, ambulance, bank_deposite, account_reconcilation, ot, casualty, pharmacy,");
			buffer.append("mrd, marketing, voice_recorder, opd_modify, opd_cancel, opd_prescription, opd_investigation,");
			buffer.append("opd_ot, opd_viewaccount, opd_apmtfinder, opd_advandref, opd_modifydiagnosis, opd_editpatient,");
			buffer.append("opd_log, opd_emr, opd_assessmentform, opd_treatment, opd_addcharges, opd_createinvoice, opd_recordpayment,");
			buffer.append("ipd_admission, ipd_declaration, ipd_log, ipd_forms, ipd_discharge, ipd_emr, ipd_prescription,");
			buffer.append("ipd_investigation, ipd_reqconsultant, ipd_nursingcare, ipd_reqblood, ipd_autocare, ipd_treatmentlog,");
			buffer.append("ipd_addcharges, ipd_createinvoice, ipd_recordpayment, ipd_advandref, ipd_viewaccount, ipd_packages,");
			buffer.append("acc_createinvoice, acc_recordpayment, acc_viewcreditaccount, acc_advandref, acc_chargeinvoice, acc_addcharges,");
			buffer.append("acc_chargedetails, emr_docs, emr_history, emr_medicine, emr_investigation, emr_pacs, emr_media, emr_update,");
			buffer.append("emr_print, emr_edit, emr_delete, client_add, client_edit, client_delete, client_forms, client_discharge,");
			buffer.append("client_emai, client_print, client_treatment, client_log, client_recordpayment, client_cashdesk,");
			buffer.append("clientadvandref, client_addcharge, client_viewaccount, client_emr, globalaccess, indent_access,");
			buffer.append("investigation_approve, daily_opd, indent_approve, cash_desk, tpa, nabh_quality, doctor_opd,");
			buffer.append("acc_refund, show_master, invst_collect, token_display, cancel_admsn, add_medicine, pharm_print_backdate,");
			buffer.append("cathlab, myhr, daycare, emergency_lbl, medicine_barcode, refund, discount, vaccination, video_training,");
			buffer.append("setup_master, sale_pharmacy  ");
			buffer.append("FROM apm_role_access WHERE usertype = '"+jobtitle+"' ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}


	
	public int saveInUserAccess(String userid, String userkeyid) {

		PreparedStatement preparedStatement = null;
		int res = 0;
		try{
			String sql = "insert into apm_user_access(userid, userkeyid) values(?,?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userid);
			preparedStatement.setString(2, userkeyid);
			res = preparedStatement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	
	}


	
	public int saveUseridInAccess() {
		int res =0;
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("SELECT apm_user.id,apm_user.userid FROM apm_user ");
			buffer.append("LEFT JOIN apm_user_access ON apm_user.id = apm_user_access.userkeyid ");
			buffer.append("WHERE apm_user_access.userkeyid IS null ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				saveInUserAccess(rs.getString(2), rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public ArrayList<UserProfile> getDiscApproveUserList() {
		ArrayList<UserProfile> arrayList = new ArrayList<>();
		try {
			String sql ="SELECT id,userid FROM apm_user WHERE jobtitle='Admin' OR ref_dis_pay=1";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				UserProfile profile = new UserProfile();
				profile.setId(rs.getInt(1));
				profile.setUserid(rs.getString(2));
				arrayList.add(profile);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public int updateBlankNotificationids(String string, String string2) {
		int res =0;
		try {
			String sql ="select "+string+" from apm_user_access where userid='"+string2+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				if(DateTimeUtils.isNull(rs.getString(1)).equals("")){
					updateNotificationIds(string,string2,"0");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public int updateNotificationIds(String columnname, String userid,String value){
		int res =0;
		try {
			String sql1 = "update apm_user_access set "+columnname+"='"+value+"' where userid='"+userid+"' ";
			PreparedStatement preparedStatement2 = connection.prepareStatement(sql1);
			res = preparedStatement2.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	public int updateNotificationCount(String columnname, String userId) {
		int res =0;
		try {
			String sql ="select "+columnname+" from apm_user_access where userid='"+userId+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int count = rs.getInt(1)+1;
				res = updateNotificationIds(columnname,userId,""+count);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	public int updateNotificationIdValue(String columnname, String userId, String id) {
		int res =0;
		try {
			String sql ="select "+columnname+" from apm_user_access where userid='"+userId+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String  count = rs.getString(1)+","+id;
				res = updateNotificationIds(columnname,userId,count);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public UserProfile getUserNotificationCount(String userId) {
		UserProfile profile = new UserProfile();
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("SELECT id,userid,userkeyid,noti_dis_apprd_count,noti_dis_apprd_ids,noti_dis_reqtd_count,noti_dis_reqtd_ids, ");
			buffer.append("noti_ref_appr_count,noti_ref_appr_ids,noti_ref_reqtd_count,noti_ref_reqtd_ids ");
			buffer.append("from apm_user_access  where userid='"+userId+"'");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				profile.setId(rs.getInt(1));
				profile.setUserid(rs.getString(2));
				profile.setUserkeyid(rs.getString(3));
				profile.setNoti_dis_apprd_count(rs.getInt(4));
				profile.setNoti_dis_apprd_ids(rs.getString(5));
				profile.setNoti_dis_reqtd_count(rs.getInt(6));
				profile.setNoti_dis_reqtd_ids(rs.getString(7));
				profile.setNoti_ref_appr_count(rs.getInt(8));
				profile.setNoti_ref_appr_ids(rs.getString(9));
				profile.setNoti_ref_reqtd_count(rs.getInt(10));
				profile.setNoti_ref_reqtd_ids(rs.getString(11));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return profile;
	}

	public ArrayList<UserProfile> getRefundApproveUserList(String string) {

		ArrayList<UserProfile> arrayList = new ArrayList<>();
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("SELECT id,userid FROM apm_user ");
			if(string.equals("0")){
				//request for approve
				buffer.append("where refund_dashboard=1 ");
			}else{
				//already approve go for pay
				buffer.append("WHERE ref_dis_pay=1 or refund_dashboard=1 ");
			}
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				UserProfile profile = new UserProfile();
				profile.setId(rs.getInt(1));
				profile.setUserid(rs.getString(2));
				arrayList.add(profile);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	
	}


	public boolean getIsLMH() {
		boolean flag = false;
		try {
			String sql="select lmh from clinic_access";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				flag = rs.getBoolean(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}

	public String getSMSTemplateID(String string) {
		String templateid ="";
		try {
			String sql ="select template_id from sms_templateid where template_name='"+string+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				templateid = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return templateid;
	}


	@Override
	public ArrayList<DiaryManagement> getUserListOfSecondary(String department) {
		PreparedStatement preparedStatement = null;
		ArrayList<DiaryManagement>list = new ArrayList<DiaryManagement>();
		String sql = "select id,initial,firstname,lastname from apm_user where usertype=4 and islogin=1 "
				+ "and jobtitle!='OT'  and  secondary_doc=1"
				+ " and discription="+department+" order by firstname asc";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				DiaryManagement diaryManagement = new DiaryManagement();
				diaryManagement.setId(rs.getInt(1));
				if(rs.getString(2)==null || rs.getString(2).equals(""))
				{
					diaryManagement.setDiaryUser(rs.getString(3) + " " +rs.getString(4));

				}
				else{
					diaryManagement.setDiaryUser(rs.getString(2) + " " + rs.getString(3) + " " +rs.getString(4));

				}				
				list.add(diaryManagement);
				
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}


	@Override
	public ArrayList<DiaryManagement> getAllUserListOfSecondary(String department) {
		PreparedStatement preparedStatement = null;
		ArrayList<DiaryManagement>list = new ArrayList<DiaryManagement>();
		StringBuffer buffer = new StringBuffer();
		buffer.append(" select id,initial,firstname,lastname from apm_user where usertype=4 and islogin=1 ");
		buffer.append(" and jobtitle!='OT'  and  secondary_doc=1 ");
		if(!department.equals("")){
		buffer.append(" and discription="+department+" ");
		}
		buffer.append("order by firstname asc ");
		
		try{
			
			preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				DiaryManagement diaryManagement = new DiaryManagement();
				diaryManagement.setId(rs.getInt(1));
				if(rs.getString(2)==null || rs.getString(2).equals(""))
				{
					diaryManagement.setDiaryUser(rs.getString(3) + " " +rs.getString(4));

				}
				else{
					diaryManagement.setDiaryUser(rs.getString(2) + " " + rs.getString(3) + " " +rs.getString(4));

				}				
				list.add(diaryManagement);
				
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}


	@Override
	public int getDiscriptionFromUserId(String userId) {
		int res =0;
		try {
			String sql ="select discription from apm_user where userid='"+userId+"'";
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
	public ArrayList<UserProfile> getloginlist(String clinicid) {
		ArrayList<UserProfile>loginlist=new ArrayList<UserProfile>();
		try {
			Statement stmt=connection.createStatement();
		
			String  sql = "select id, userid,loginstatus from admin.apm_user where loginstatus=1 and clinicid='"+clinicid+"'";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				UserProfile user=new UserProfile();
				user.setId(rs.getInt(1));
				user.setUserid(rs.getString(2));
				user.setLoginstatus(rs.getString(3));
				
				loginlist.add(user);
			}

			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loginlist;
	}


	@Override
	public int getUpdateloginstatus(String selectedid) {
		int result=0;
		try {
			String sql="update admin.apm_user set loginstatus=0 where id='"+selectedid+"'";
			PreparedStatement pst=connection.prepareStatement(sql);
			result=pst.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	@Override
	public ArrayList<UserProfile> getallHousekeepingstaff() {
		// TODO Auto-generated method stub
		ArrayList<UserProfile>stafflist=new ArrayList<UserProfile>();
		try {
			
			String sql ="SELECT id,concat(initial,' ',firstname,' ',lastname) FROM apm_user WHERE jobtitle='HOUSEKEPPING STAFF' and usertype = 4 ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				UserProfile profile = new UserProfile();
				profile.setId(rs.getInt(1));
				profile.setName(rs.getString(2));
				stafflist.add(profile);
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return stafflist;
	}


	@Override
	public int Updateempcode(UserProfile userProfileForm) {
		// TODO Auto-generated method stub
		
		int result=0;
		try {
			String sql="update apm_payroll_employee set empcode='"+userProfileForm.getEmpid()+"' where userid='"+userProfileForm.getUserid()+"'";
			PreparedStatement pst=connection.prepareStatement(sql);
			result=pst.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	
	}


	@Override
	public boolean isEmpidExist(String empId, String selectedid) {
		// TODO Auto-generated method stub
		
		PreparedStatement preparedStatement = null;
		boolean result = false;
		String employeeid = "";
		String sql = "select empcode from apm_payroll_employee where empcode = ? ";

		try {

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, empId);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				employeeid = rs.getString(1);
				result = true;
			}

			/*if (!selectedid.equals("0")) {
				if (empId.equals(existUserid)) {
					result = false;
				}
			}*/

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return result;
	}


	@Override
	public int saveIpdhospitaldataToAdmin(UserProfile userprofile, String clinicUserid,String useridtest) {
	
		PreparedStatement preparedStatement = null;
		int res = 0;
		String sql = "insert into ipd_hospital(hosp_name,city,commercial,referdhosp_clinicid,consultant_share,hospital_userid,mobile,user_id) values(?,?,?,?,?,?,?,?)";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userprofile.getFirstname()+" "+userprofile.getLastname());
			preparedStatement.setString(2, userprofile.getCity());
			preparedStatement.setString(3, userprofile.getCommercial());
			preparedStatement.setString(4, clinicUserid);
			preparedStatement.setString(5, userprofile.getConsultatantshare());
			preparedStatement.setString(6, userprofile.getUserid());
			preparedStatement.setString(7, userprofile.getMobile());
			preparedStatement.setString(8, useridtest);
			res = preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
		
	}


	@Override
	public ArrayList<Master> getClinicUseridlist() {
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list = new ArrayList<Master>();
		String sql = "select id,userid from manasclinic.apm_user ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Master master = new Master();
				
				master.setId(rs.getInt(1));
				master.setUserid(rs.getString(2));
			
				list.add(master);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}


	@Override
	public ArrayList<Master> getClinicStafflist() {
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list = new ArrayList<Master>();
		String sql = "select id,refered_clinicname from manasclinic.apm_user where jobtitle='Admin' ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Master master = new Master();
				
				master.setId(rs.getInt(1));
				master.setClinic_name(rs.getString(2));
			
				list.add(master);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}


	@Override
	public int getUpdatepassword(UserProfile user, String selectedid) {
		int result=0;
		
		try {
			String sql="update apm_user set password=? where id='"+selectedid+"'";
			PreparedStatement pst=connection.prepareStatement(sql);
			String encPassword = Encryption.encryptSHA(user.getResetpassword());
			pst.setString(1, encPassword);
			result=pst.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}


	@Override
	public int updatepasswordtoAdmin(UserProfile user, String userid) {
    int result=0;
		
		try {
			String sql="update admin.apm_user set password=? where userid='"+userid+"'";
			PreparedStatement pst=connection.prepareStatement(sql);
			String encPassword = Encryption.encryptSHA(user.getResetpassword());
			pst.setString(1, encPassword);
			result=pst.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}


	@Override
	public String getuseridById(String selectedid) {
		String userid ="";
		try {
			String sql ="select userid from apm_user where id='"+selectedid+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				userid = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userid;
	}


	@Override
	public double getAppointmentChargesbyid(String apmttype) {
		double charge=0.0;
		try {
			
			String sql="select charges from apm_appointment_type where id="+apmttype+"";
			PreparedStatement pst=connection.prepareStatement(sql);
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
	public String getdescriptionName(String discription) {
		String discriptioname="";
		try {
			
			String sql="select discipline from apm_discipline where id="+discription+"";
			PreparedStatement pst=connection.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				discriptioname=rs.getString(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return discriptioname;
		
	}


	@Override
	public String getPhysioIpdOpd(String diciplineid) {
		String physioipdopd="";
		try {
			
			String sql="select physio_opd_ipd from apm_discipline where id="+diciplineid+"";
			PreparedStatement pst=connection.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				physioipdopd=rs.getString(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return physioipdopd;
		
	}


	@Override
	public int updatephysioIpdOpd(String clientid) {
      int result=0;
		
		try {
			String sql="update apm_patient set physio_ipd=1 where id='"+clientid+"'";
			PreparedStatement pst=connection.prepareStatement(sql);
			result=pst.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}



}
