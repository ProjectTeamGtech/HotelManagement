package com.apm.Bloodbank.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.apm.Bloodbank.eu.bi.BloodbankDAO;
import com.apm.Bloodbank.eu.entity.Bloodbank;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.Inventory.eu.entity.Product;
import com.apm.Ipd.eu.bi.BedDao;
import com.apm.Ipd.eu.bi.IpdDAO;
import com.apm.Ipd.eu.blogic.jdbc.JDBCBedDao;
import com.apm.Ipd.eu.blogic.jdbc.JDBCIpdDAO;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;

public class JDBCBloodBankDAO implements BloodbankDAO{

	Connection connection;
	
	public JDBCBloodBankDAO(Connection connection) {

         this.connection=connection;	
	}

	public ArrayList<Bloodbank> getBloodgroupList() {
		
		ArrayList<Bloodbank> list=new ArrayList<Bloodbank>();
		
		try {
	
			String sql="select id, blood_group, no_bags,expiry from apm_blood_group";
			PreparedStatement ps=connection.prepareStatement(sql);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				
				  Bloodbank bloodbank=new Bloodbank();
				  bloodbank.setId(rs.getInt(1));
				  bloodbank.setBlood_group(rs.getString(2));
				  bloodbank.setNo_bags(rs.getString(3));
				  bloodbank.setExpiry_date(rs.getString(4));
				  list.add(bloodbank);
			}
			
		} catch (Exception e) {

		  e.printStackTrace();
		}
	
		return list;
	}

	public ArrayList<Bloodbank> getBloodonorList() {
		
		ArrayList<Bloodbank> list=new ArrayList<Bloodbank>();
		
		try {
			
			String sql="select id, name, gender, age, phone, email, blood_group, donation_date, weight, dob, city, donor_state,address,lastmodified from apm_blood_donor";
			PreparedStatement ps=connection.prepareStatement(sql);
 			ResultSet rs=ps.executeQuery();
 			while(rs.next()){
 				
 				   Bloodbank bloodbank=new Bloodbank();
 				   bloodbank.setId(rs.getInt(1));
 				   bloodbank.setName(rs.getString(2));
 				   bloodbank.setGender(rs.getString(3));
 				   bloodbank.setAge(rs.getString(4));
 				   bloodbank.setPhone(rs.getString(5));
 				   bloodbank.setEmail(rs.getString(6));
 				   bloodbank.setBlood_group(rs.getString(7));
 				   bloodbank.setLast_donation_date(rs.getString(8));
 				   bloodbank.setWeight(rs.getString(9));
 				   bloodbank.setDob(rs.getString(10));
 				   bloodbank.setCity(rs.getString(11));
 				   bloodbank.setDonor_state(rs.getString(12));
 				   bloodbank.setAddress(rs.getString(13));
 				   bloodbank.setLastmodified(rs.getString(14));
 				   list.add(bloodbank);
 			}
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
	
		return list;
	}

	public int addBloodGroup(Bloodbank bloodbank) {

		int result=0;
		int count=0,id=0;
		boolean flag= false;
		try {
			
			String sql1="select id,blood_group,no_bags from apm_blood_group where blood_group='"+bloodbank.getBlood_group()+"'";
			PreparedStatement ps1=connection.prepareStatement(sql1);
			ResultSet set=ps1.executeQuery();
			
			while(set.next()){
				 
				flag=true;
				id=set.getInt(1);
				count=Integer.parseInt(set.getString(3));
			}
		    if(flag){
		    	 
		    	 int now=Integer.parseInt(bloodbank.getNo_bags());
		    	 int total=now+count;
		    	 String sql="update apm_blood_group set no_bags='"+total+"',expiry='"+bloodbank.getExpiry_date()+"' where id="+id+""; 
		    	 PreparedStatement ps=connection.prepareStatement(sql);
		    	 result=ps.executeUpdate();
		    }
		    else {
			
		    	String sql="insert into apm_blood_group (blood_group, no_bags,expiry) values (?,?,?)";
		    	PreparedStatement ps=connection.prepareStatement(sql);
		    	ps.setString(1, bloodbank.getBlood_group());
		    	ps.setString(2, bloodbank.getNo_bags());
		    	ps.setString(3, bloodbank.getExpiry_date());
		    	result=ps.executeUpdate();
		    }
		} catch (Exception e) {

		   e.printStackTrace();
		}
	
		return result;
	}

	public int addBloodDonor(Bloodbank bloodbank) {

		int result=0;
		
		try {
			
			String sql="insert into apm_blood_donor (name, gender, age, phone, email, blood_group, donation_date, weight, dob, city, donor_state, address, lastmodified) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, bloodbank.getName());
			ps.setString(2, bloodbank.getGender());
			ps.setString(3, bloodbank.getAge());
			ps.setString(4, bloodbank.getPhone());
			ps.setString(5, bloodbank.getEmail());
			ps.setString(6, bloodbank.getBlood_group());
			ps.setString(7, bloodbank.getLast_donation_date());
			ps.setString(8, bloodbank.getWeight());
			ps.setString(9, bloodbank.getDob());
			ps.setString(10, bloodbank.getCity());
			ps.setString(11, bloodbank.getDonor_state());
			ps.setString(12, bloodbank.getAddress());
			ps.setString(13, bloodbank.getLastmodified());
		
		    result=ps.executeUpdate();	
		} catch (Exception e) {

		    e.printStackTrace();
		}		
	
		return result;
	}

	public int updateBloodGroup(Bloodbank bloodbank) {

		int result=0;
		
		try {
			
			String sql="update apm_blood_group set blood_group=?,no_bags=?,expiry=? where id="+bloodbank.getId()+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, bloodbank.getBlood_group());
			ps.setString(2, bloodbank.getNo_bags());
			ps.setString(3, bloodbank.getExpiry_date());
			result=ps.executeUpdate();
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		
		return result;
	}

	public Bloodbank getBloodbankDonor(String id) {

		Bloodbank bloodbank=new Bloodbank();
		
		try {
		
			String sql="select name, gender, age, phone, email, blood_group, donation_date, weight, dob, city, donor_state, address, lastmodified from apm_blood_donor where id="+id+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				
				bloodbank.setName(rs.getString(1));
				bloodbank.setGender(rs.getString(2));
				bloodbank.setAge(rs.getString(3));
				bloodbank.setPhone(rs.getString(4));
				bloodbank.setEmail(rs.getString(5));
				bloodbank.setBlood_group(rs.getString(6));
				bloodbank.setLast_donation_date(rs.getString(7));
				bloodbank.setWeight(rs.getString(8));
				bloodbank.setDob(rs.getString(9));
				bloodbank.setCity(rs.getString(10));
				bloodbank.setDonor_state(rs.getString(11));
				bloodbank.setAddress(rs.getString(12));
				bloodbank.setLastmodified(rs.getString(13));
				bloodbank.setId(Integer.parseInt(id));
			}
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		return bloodbank;
	}

	public int updateBloodDonor(Bloodbank bloodbank) {

		int result=0;
		
		try {
			
			String sql="update apm_blood_donor set name=?, gender=?, age=?, phone=?, email=?, blood_group=?, weight=?, dob=?, city=?, donor_state=?, address=?, lastmodified=? where id="+bloodbank.getId()+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			
			ps.setString(1, bloodbank.getName());
			ps.setString(2, bloodbank.getGender());
			ps.setString(3, bloodbank.getAge());
			ps.setString(4, bloodbank.getPhone());
			ps.setString(5, bloodbank.getEmail());
			ps.setString(6, bloodbank.getBlood_group());
			ps.setString(7, bloodbank.getWeight());
			ps.setString(8, bloodbank.getDob());
			ps.setString(9, bloodbank.getCity());
			ps.setString(10,bloodbank.getDonor_state());
			ps.setString(11, bloodbank.getAddress());
			ps.setString(12, bloodbank.getLastmodified());
		
			
			result=ps.executeUpdate();
			
		} catch (Exception e) {

		   e.printStackTrace();
		   
		}
		
		return result;
	}

	public Bloodbank getBloodbankGroup(String id) {

		Bloodbank bloodbank=new Bloodbank();
		
		try {
			
			String sql="select blood_group,no_bags,expiry from apm_blood_group where id="+id+"";
			
			PreparedStatement ps=connection.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
            
            	  bloodbank.setBlood_group(rs.getString(1)); 
            	  bloodbank.setNo_bags(rs.getString(2));
            	  bloodbank.setExpiry_date(rs.getString(3));
            	  bloodbank.setId(Integer.parseInt(id));
            	  
            }
            
			
		} catch (Exception e) {

		  e.printStackTrace();
		}

		
		return bloodbank;
	}

	public int deleteDonor(String id) {

		int result=0;
		
		try {
			
			String sql="delete from apm_blood_donor where id="+id+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			result=ps.executeUpdate();
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
	
		return result;
	}

	public ArrayList<Bloodbank> getBloodonorList(String searchText) {

		ArrayList<Bloodbank> list=new ArrayList<Bloodbank>();
		
		try {
			
			String sql="select id, name, gender, age, phone, email, blood_group, donation_date from apm_blood_donor where name like '"+searchText+"%' or name like '%"+searchText+"'";
			PreparedStatement ps=connection.prepareStatement(sql);
 			ResultSet rs=ps.executeQuery();
 			while(rs.next()){
 				
 				   Bloodbank bloodbank=new Bloodbank();
 				   bloodbank.setId(rs.getInt(1));
 				   bloodbank.setName(rs.getString(2));
 				   bloodbank.setGender(rs.getString(3));
 				   bloodbank.setAge(rs.getString(4));
 				   bloodbank.setPhone(rs.getString(5));
 				   bloodbank.setEmail(rs.getString(6));
 				   bloodbank.setBlood_group(rs.getString(7));
 				   bloodbank.setLast_donation_date(rs.getString(8));
 				   list.add(bloodbank);
 			}
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
	
		return list;
				
	}

	public int saveBloodtoPatient(Bloodbank bloodbank) {

		int result=0;
		try {
			
			String sql="insert into apm_blood_given (donorid, blood_group, clientid, ipdid, qty, datetime) values (?,?,?,?,?,?)";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, bloodbank.getDonorid());
			ps.setString(2, bloodbank.getBlood_group());
			ps.setString(3, bloodbank.getClientid());
			ps.setString(4, bloodbank.getIpdid());
			ps.setString(5, "");
			ps.setString(6, bloodbank.getLastmodified());
			result=ps.executeUpdate();
		} catch (Exception e) {

		  e.printStackTrace();
		}
		
		return result;
	}

	public ArrayList<Bloodbank> getAllDonortoPatient() {

		ArrayList<Bloodbank> list=new ArrayList<Bloodbank>();
		BedDao bedDao=new JDBCBedDao(connection);
		ClientDAO clientDAO=new JDBCClientDAO(connection);
		try {
			
			String sql="select id, donorid, blood_group, clientid, ipdid, qty, datetime from apm_blood_given";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				
				  Bloodbank bloodbank=new Bloodbank();
				  bloodbank.setId(rs.getInt(1));
				  bloodbank.setDonorid(rs.getString(2));
				  bloodbank.setBlood_group(rs.getString(3));
				  bloodbank.setClientid(rs.getString(4));
				  bloodbank.setIpdid(rs.getString(5));
				  
				  String datetime=rs.getString(7);
				  
				  String str[]=datetime.split(" ");

				  String date=str[0]+"/"+str[1];
				  
				  Client client=clientDAO.getClientDetails(bloodbank.getClientid());
				  String name=client.getFirstName()+" "+client.getLastName();
				  bloodbank.setClientname(name);
				  
				  Bloodbank bloodbank2=getBloodbankDonor(bloodbank.getDonorid());
				  bloodbank.setName(bloodbank2.getName());
				  
				  Bed bed=bedDao.getEditIpdData(bloodbank.getIpdid());
				  String ward=bedDao.getWard(bed.getWardid()).getWardname();	
				  String bedname=bedDao.getBed(Integer.parseInt(bed.getBedid())).getBedname();
				  String data=ward+"/"+bedname;
				  bloodbank.setWard(data);
				  bloodbank.setLastmodified(date);
				  
				  list.add(bloodbank);
				  
			}
		} catch (Exception e) {

		    e.printStackTrace();
		}
		
		return list;
	}

	public Bloodbank getDonortoPatient(String id) {

		Bloodbank bloodbank=new Bloodbank();
		BedDao bedDao=new JDBCBedDao(connection); 
		try {
			
			String sql="select donorid, blood_group, clientid, ipdid, qty, datetime from apm_blood_given where id="+id+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				
				 bloodbank.setDonorid(rs.getString(1));
				 bloodbank.setBlood_group(rs.getString(2));
				 bloodbank.setClientid(rs.getString(3));
				 bloodbank.setIpdid(rs.getString(4));
				 bloodbank.setQty(rs.getString(5));
				 bloodbank.setLastmodified(rs.getString(6));
				 bloodbank.setId(Integer.parseInt(id));
				 
				  Bed bed=bedDao.getEditIpdData(bloodbank.getIpdid());
				  String ward=bedDao.getWard(bed.getWardid()).getWardname();	
				  String bedname=bedDao.getBed(Integer.parseInt(bed.getBedid())).getBedname();
				  String data=ward+"/"+bedname;
				  bloodbank.setWard(data);
				 
			}
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		return bloodbank;
	}

	public int updateDonateToPatient(Bloodbank bloodbank) {

		int result=0;
		
		try {
			String sql="update apm_blood_given set donorid=?, blood_group=?, clientid=?, ipdid=?, qty=?, datetime=? where id="+bloodbank.getId()+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, bloodbank.getDonorid());
			ps.setString(2, bloodbank.getBlood_group());
			ps.setString(3, bloodbank.getClientid());
			ps.setString(4, bloodbank.getIpdid());
			ps.setString(5, bloodbank.getQty());
			ps.setString(6, bloodbank.getLastmodified());
			
			result=ps.executeUpdate();
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		
		return result;
	}
	
	public String getGroupidfromGroup(String group) {
		
		String grpid="0";
		try {
			
			String sql="select id from apm_blood_group where blood_group='"+group+"'";
			PreparedStatement ps=connection.prepareStatement(sql);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				
				 grpid=rs.getString(1);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
	
		return grpid;
	}

	public int addBloodRequest(Bloodbank bloodbank) {

		int result=0;
		try {
			
			String sql="insert into apm_blood_request (requestfrom, reqid, blood_group, required_qty, commencing, time, patientid, ipdid,required_datetime,userid,bedid,wardid,practid,component,idnattested,leuco_depleted) "
					+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, bloodbank.getRequestfrom());
			ps.setString(2, bloodbank.getReqid());
			ps.setString(3, bloodbank.getBlood_group_id());
			ps.setString(4, bloodbank.getQty());
			ps.setString(5, bloodbank.getCommencing());
			ps.setString(6, bloodbank.getTime());
			ps.setString(7, bloodbank.getClientid());
			ps.setString(8, bloodbank.getIpdid());
			ps.setString(9, bloodbank.getDate());
			ps.setString(10, bloodbank.getUserid());
			ps.setString(11, bloodbank.getBedid());
			ps.setString(12, bloodbank.getWardid());
			ps.setString(13, bloodbank.getPractid());
			ps.setString(14, bloodbank.getBloodbank_component());
			ps.setString(15, bloodbank.getBloodbank_idnattested());
			ps.setString(16, bloodbank.getBloodbank_leuco_depleted());
			result=ps.executeUpdate();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}

	public ArrayList<Bloodbank> getAllRequestedBloodList(String name, String fromdate, String todate,
			String from, String bloodgroup, String status,Pagination pagination) {

		ArrayList<Bloodbank> list=new ArrayList<Bloodbank>();
		ClientDAO clientDAO=new JDBCClientDAO(connection);
		BedDao bedDao=new JDBCBedDao(connection);
	    IpdDAO ipdDAO=new JDBCIpdDAO(connection);
	    UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
		try {
			StringBuffer buffer=new StringBuffer();
			/*buffer.append("SELECT apm_blood_request.id, requestfrom, reqid, apm_blood_group.blood_group, required_qty, commencing, time, patientid, ipdid,apm_blood_request.status,no_bags,given,pending,apm_blood_group.id,required_datetime from ");
			buffer.append("apm_blood_request inner join apm_blood_group on apm_blood_group.id=apm_blood_request.blood_group ");
			if(name!=null){
				buffer.append("inner join apm_patient on apm_blood_request.patientid=apm_patient.id ");
			}
			buffer.append("where apm_blood_request.commencing between '"+fromdate+"' and '"+todate+"'  ");
			if(name!=null){
				buffer.append("and (apm_patient.firstname like ('"+name+"%') or apm_patient.surname like ('%"+name+"')  ) ");		
			}
			if(from!=null){
				buffer.append("and apm_blood_request.requestfrom ='"+from+"' ");
			}
			if(bloodgroup!=null){
				buffer.append("and apm_blood_group.blood_group='"+bloodgroup+"' "); 
			}
			if(status!=null){
				buffer.append("and apm_blood_request.status='"+status+"' ");
			}
			buffer.append("order by apm_blood_request.id desc ");*/
			todate = todate+" "+"59:59:59";
			buffer.append("SELECT apm_blood_request.id, requestfrom, reqid, inventory_catalogue.product_name, required_qty, commencing, time, ");
			buffer.append("patientid, ipdid,apm_blood_request.status,given,pending,inventory_catalogue.id,required_datetime,bedid,wardid,practid,crossmatch_productid ");
			buffer.append("from apm_blood_request ");
			buffer.append("inner join inventory_catalogue on inventory_catalogue.id= apm_blood_request.blood_group ");
			if(name!=null){
				buffer.append("inner join apm_patient on apm_blood_request.patientid=apm_patient.id ");
			}
			buffer.append("where apm_blood_request.commencing between '"+fromdate+"' and '"+todate+"'  ");
			if(name!=null){
				buffer.append("and (apm_patient.firstname like ('"+name+"%') or apm_patient.surname like ('%"+name+"') or abrivationid='"+name+"'  ) ");		
			}
			if(from!=null){
				buffer.append("and apm_blood_request.requestfrom ='"+from+"' ");
			}
			if(bloodgroup!=null){
				buffer.append("and inventory_catalogue.product_name='"+bloodgroup+"' "); 
			}
			if(status!=null){
				buffer.append("and apm_blood_request.status='"+status+"' ");
			}
			buffer.append("order by apm_blood_request.id desc ");
			
			String sql=buffer.toString();
			if(pagination!=null){
				sql= pagination.getSQLQuery(sql);
			}
			
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				    Bloodbank bloodbank=new Bloodbank();
				    bloodbank.setId(rs.getInt(1));
				    bloodbank.setRequestfrom(rs.getString(2));
				    bloodbank.setReqid(rs.getString(3));
				    bloodbank.setBlood_group(rs.getString(4));
				    bloodbank.setQty(rs.getString(5));
				    String commencing= rs.getString(14);
				    bloodbank.setCommencing(DateTimeUtils.getCommencingDate1(commencing));
				    bloodbank.setTime(rs.getString(7));
				    bloodbank.setClientid(rs.getString(8));
				    bloodbank.setIpdid(rs.getString(9));
				    bloodbank.setStatus(rs.getInt(10));
				    
				    //bloodbank.setStock(rs.getInt(11));
				    
				    bloodbank.setGiven(rs.getInt(11));
				    bloodbank.setPending(rs.getInt(12));
				    bloodbank.setBlood_group_id(rs.getString(13));
				    bloodbank.setDate(rs.getString(14));	
				    /*if(bloodbank.getRequestfrom().equals("IPD")){
				    	    Bed  bed=bedDao.getEditIpdData(bloodbank.getIpdid());
				    	    String wardname=ipdDAO.getIpdWardName(bed.getWardid());
				    	    String bedname=ipdDAO.getIpdBedName(bed.getBedid());
				    	    bloodbank.setWard(bedname+"/"+wardname);
				    }*/
				    if(rs.getInt(9)>0){
				    	String wardname=ipdDAO.getIpdWardName(rs.getString(16));
			    	    String bedname=ipdDAO.getIpdBedName(rs.getString(15));
			    	    String practitioner_name = userProfileDAO.getUserFullNameFromId(rs.getString(17));
			    	    bloodbank.setWard(bedname+"/"+wardname);
			    	    bloodbank.setPract_name(practitioner_name);
				    }else{
				    	 bloodbank.setWard("");
				    	 bloodbank.setPract_name("");
				    }
				    Client client=clientDAO.getClientDetails(bloodbank.getClientid());
				    String agegneder=client.getAge1()+"/"+client.getGender();
				    bloodbank.setClientname(client.getTitle()+" "+client.getFirstName()+" "+client.getLastName());
				    bloodbank.setAge(agegneder);
				    bloodbank.setWeight(client.getWeight());
				    bloodbank.setReqqty(rs.getInt(5)); 
				    bloodbank.setCrossmatch_productid(""+rs.getInt(18));
				    list.add(bloodbank);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return list;
	}

	
	public int updateBloodAllocate(String id, String alloc) {
		
		int result=0;
		int reqty=0;
		int status=0;
		int pending=0;
		String blood_grpid="0";
		int pgiven=0;
		try {
			
			String sql="select required_qty,blood_group,given from apm_blood_request where id="+id+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				  reqty=rs.getInt(1);
				  blood_grpid=rs.getString(2);
				  pgiven=rs.getInt(3);
			}
			int given=Integer.parseInt(alloc);
			
			
			if(given>=reqty){
				
				status=1;
				pending=0;
			}else {
				status=0;
				pending=reqty-given;
			}
			
			String sql1="update apm_blood_request set given="+alloc+",status="+status+",pending="+pending+" where id="+id+"";
			PreparedStatement ps1=connection.prepareStatement(sql1);
			result=ps1.executeUpdate();
			
			//Now Minus Qty  
			//update if previous given blood
			given=given-pgiven;
			
			updateStock(blood_grpid, given);
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}
	
	public int updateStock(String id,int given) {
		
		int stock=0;
		int result=0;
		try {
			
			String sql="SELECT no_bags from apm_blood_group where id="+id+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				 stock=rs.getInt(1);
			}
			if(given>stock){
				 
				stock=0;
			}
			else {
				
				stock=stock-given;
			}

			String sql1="update apm_blood_group set no_bags="+stock+" where id="+id+"";
			PreparedStatement ps1=connection.prepareStatement(sql1);
			
			result=ps1.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}
	
	public ArrayList<Bloodbank> getBloodonorListByGroup(String bloodgroup) {
		
		ArrayList<Bloodbank> list=new ArrayList<Bloodbank>();
		
		try {
			
			String sql="select id, name, gender, age, phone, email, blood_group, donation_date, weight, dob, city, donor_state,address,lastmodified from apm_blood_donor where blood_group='"+bloodgroup+"' ";
			PreparedStatement ps=connection.prepareStatement(sql);
 			ResultSet rs=ps.executeQuery();
 			while(rs.next()){
 				
 				   Bloodbank bloodbank=new Bloodbank();
 				   bloodbank.setId(rs.getInt(1));
 				   bloodbank.setName(rs.getString(2));
 				   bloodbank.setGender(rs.getString(3));
 				   bloodbank.setAge(rs.getString(4));
 				   bloodbank.setPhone(rs.getString(5));
 				   bloodbank.setEmail(rs.getString(6));
 				   bloodbank.setBlood_group(rs.getString(7));
 				   bloodbank.setLast_donation_date(rs.getString(8));
 				   bloodbank.setWeight(rs.getString(9));
 				   bloodbank.setDob(rs.getString(10));
 				   bloodbank.setCity(rs.getString(11));
 				   bloodbank.setDonor_state(rs.getString(12));
 				   bloodbank.setAddress(rs.getString(13));
 				   bloodbank.setLastmodified(rs.getString(14));
 				   list.add(bloodbank);
 			}
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
	
		return list;
	}

	public int saveBloodBank(Bloodbank bloodbank) {

		int result=0;
		try {
			String sql="insert into apm_blood_bank (name, address, mobile) values (?,?,?) ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, bloodbank.getName());
			ps.setString(2, bloodbank.getAddress());
			ps.setString(3, bloodbank.getMobile());
			
			result=ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Bloodbank> getAllBankList() {

		ArrayList<Bloodbank> list=new ArrayList<Bloodbank>();
		try {
			
			String sql="select id,name,address,mobile from apm_blood_bank";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				 
				  Bloodbank bloodbank=new Bloodbank();
				  bloodbank.setId(rs.getInt(1));
				  bloodbank.setName(rs.getString(2));
				  bloodbank.setAddress(rs.getString(3));
				  bloodbank.setMobile(rs.getString(4));
				  
				  list.add(bloodbank);
				
			}
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return list;
	}

	public Bloodbank getBloodBankDetails(String id) {

		Bloodbank bloodbank=new Bloodbank();
		try {
			
			String sql="SELECT id, name, address, mobile from apm_blood_bank where id="+id+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				  
				    bloodbank.setId(rs.getInt(1));
				    bloodbank.setName(rs.getString(2));
				    bloodbank.setAddress(rs.getString(3));
				    bloodbank.setMobile(rs.getString(4));
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return bloodbank;
	}

	public int updateBloodBank(Bloodbank bloodbank) {

		int result=0;
		try {
			
			String sql="update apm_blood_bank set name=?, address=?, mobile=? where id="+bloodbank.getId()+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, bloodbank.getName());
			ps.setString(2, bloodbank.getAddress());
			ps.setString(3, bloodbank.getMobile());
			
			result=ps.executeUpdate();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return result;
	}

	public int deleteBloodBank(String id) {

		int result=0;
		try {
			
			String sql="delete from apm_blood_bank where id="+id+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			result=ps.executeUpdate();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	
	
	public int getAllReuestedBloodListCount(String name, String fromdate, String todate,
			String from, String bloodgroup, String status) {

		int result=0;
		try {
			
			StringBuffer buffer=new StringBuffer();
			/*buffer.append("SELECT count(*) from ");
			buffer.append("apm_blood_request inner join apm_blood_group on apm_blood_group.id=apm_blood_request.blood_group ");
			if(name!=null){
				buffer.append("inner join apm_patient on apm_blood_request.patientid=apm_patient.id ");
			}
			buffer.append("where apm_blood_request.commencing between '"+fromdate+"' and '"+todate+"'  ");
			if(name!=null){
				
		           buffer.append("and (apm_patient.firstname like ('"+name+"%') or apm_patient.surname like ('%"+name+"')  ) ");		
			}
			if(from!=null){
				buffer.append("and apm_blood_request.requestfrom ='"+from+"' ");
			}
			if(bloodgroup!=null){
				buffer.append("and apm_blood_group.blood_group='"+bloodgroup+"' "); 
				
			}
			if(status!=null){
				buffer.append("and apm_blood_request.status='"+status+"' ");
			}*/
			todate = todate+" "+"59:59:59";
			buffer.append("SELECT count(*) ");
			buffer.append("from apm_blood_request ");
			buffer.append("inner join inventory_catalogue on inventory_catalogue.id= apm_blood_request.blood_group ");
			if(name!=null){
				buffer.append("inner join apm_patient on apm_blood_request.patientid=apm_patient.id ");
			}
			buffer.append("where apm_blood_request.commencing between '"+fromdate+"' and '"+todate+"'  ");
			if(name!=null){
				buffer.append("and (apm_patient.firstname like ('"+name+"%') or apm_patient.surname like ('%"+name+"') or abrivationid='"+name+"'  ) ");		
			}
			if(from!=null){
				buffer.append("and apm_blood_request.requestfrom ='"+from+"' ");
			}
			if(bloodgroup!=null){
				buffer.append("and inventory_catalogue.product_name='"+bloodgroup+"' "); 
			}
			if(status!=null){
				buffer.append("and apm_blood_request.status='"+status+"' ");
			}
			buffer.append("order by apm_blood_request.id desc ");
			
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				 
				result=rs.getInt(1);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		
		return result;
	}

	public ArrayList<Bloodbank> getBloodRequestList(String clientid, String ipdid, String date) {

		ArrayList<Bloodbank> list= new ArrayList<Bloodbank>();
		try {
			
			String sql="SELECT id,requestfrom, reqid, blood_group, required_qty, commencing, time, patientid, ipdid, status, given, pending, required_datetime, userid from apm_blood_request where patientid="+clientid+" and ipdid="+ipdid+" and commencing between '"+date+"' and '"+date+"' ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				 
				Bloodbank bloodbank =new Bloodbank();
				bloodbank.setId(rs.getInt(1));
				bloodbank.setRequestfrom(rs.getString(2));
				bloodbank.setReqid(rs.getString(3));
				bloodbank.setBlood_group_id(rs.getString(4));
				Bloodbank master=getBloodbankGroup(bloodbank.getBlood_group_id());
				bloodbank.setBlood_group(master.getBlood_group());
				bloodbank.setReqqty(rs.getInt(5));
				bloodbank.setCommencing(rs.getString(6));
				bloodbank.setTime(rs.getString(7));
				bloodbank.setClientid(rs.getString(8));
				bloodbank.setIpdid(rs.getString(9));
				bloodbank.setStatus(rs.getInt(10));
				bloodbank.setGiven(rs.getInt(11));
				bloodbank.setPending(rs.getInt(12));
				//rs.getString(13);
				bloodbank.setUserid(rs.getString(14));
				
				list.add(bloodbank);
				
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return list;
	}

	public int saveCrossMatchData(String id, String productid, String userid, String dateTime) {
		int result=0;
		try {
			String sql="update apm_blood_request set crossmatch_userid=?, crossmatch_datetime=?, crossmatch_productid=?,status=1 where id="+id+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, userid);
			ps.setString(2, dateTime);
			ps.setString(3, productid);
			result=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int saveIssueBloodData(String id, String handoverto, String userid, String dateTime) {
		int result=0;
		try {
			String sql="update apm_blood_request set issue_userid=?, issue_datetime=?, issue_handover=?,status=2 where id="+id+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, userid);
			ps.setString(2, dateTime);
			ps.setString(3, handoverto);
			result=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Product getBloodBankRequestDetails(String bloodbankid) {
		Product product = new Product();
		try {
			String sql ="select required_qty,crossmatch_productid,ipdid,patientid,blood_group,commencing from apm_blood_request where id='"+bloodbankid+"' ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				product.setQty(rs.getDouble(1));
				product.setProductid(""+rs.getInt(2));
				product.setIpdid(""+rs.getInt(3));
				product.setClientid(""+rs.getInt(4));
				product.setCatalogueid(rs.getString(5));
				product.setDateTime(rs.getString(6));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}

	public int updateBloodBankStatus(String bloodbankid, String userid, String dateTime, String status) {
		int result=0;
		try {
			String sql="update apm_blood_request set addcharge_userid=?, addcharge_datetime=?, status=? where id="+bloodbankid+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, userid);
			ps.setString(2, dateTime);
			ps.setString(3, ""+status);
			result=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
