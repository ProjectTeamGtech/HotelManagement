package com.apm.Master.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.apm.Master.eu.bi.AmbulanceDAO;
import com.apm.Master.eu.entity.Ambulance;

import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;

public class JDBCAmbulanceDAO extends BaseAction implements AmbulanceDAO {
 Connection con;


public JDBCAmbulanceDAO(Connection con) {

	this.con = con;
}


@Override
public int getTotalambulance() {
	int result=0;
	try {
		
		String sql="select count(*) from apm_ambulance";
		PreparedStatement pst=con.prepareStatement(sql);
		ResultSet rs=pst.executeQuery();
		while(rs.next())
		{
			result=rs.getInt(1);
		}
	} catch (Exception e) {
           e.printStackTrace();
	}
	return result;
}


@Override
public ArrayList<Ambulance> getAllAmbulance(String searchText, Pagination pagination) {
	ArrayList<Ambulance>users=new ArrayList<Ambulance>();
	try {
		Statement stmt=con.createStatement();
		String sql="";
		if(searchText!=null){
			sql="select * from apm_ambulance where vehicleno like ('"+searchText+"%')";
		}
		else {
			sql = "select * from apm_ambulance";
		}
		if(pagination!=null)
		{
			sql=pagination.getSQLQuery(sql);
		}
		
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			
			String typename=ambulanceid(rs.getString(3));
			String drivername=driverid(rs.getString(4));
			Ambulance amb=new Ambulance();
			amb.setId(rs.getString(1));
			amb.setVehicleno(rs.getString(2));
			amb.setAmbulancetype(typename);
			amb.setDriverfname(drivername);
			users.add(amb);
		}
		stmt.close();
	} catch (Exception e) {
      e.printStackTrace();
	}
	return users;
}


private String driverid(String string) {
	PreparedStatement pst=null;
	String drivername="";
	try {
		String sql="select concat(firstname,' ',lastname) from apm_user where id='"+string+"'";
		pst=con.prepareStatement(sql);
		ResultSet rs=pst.executeQuery();
		while(rs.next())
		{
			drivername=rs.getString(1);
		}
		
	} catch (Exception e) {
        e.printStackTrace();
	}
	return drivername;
}


private String ambulanceid(String string) {
	// TODO Auto-generated method stub
	
	 PreparedStatement pmt=null;
     String typename="";
	
	try {
		
		   String sql="select ambulancetype from apm_ambulancetype where id='"+string+"'";
		   pmt=con.prepareStatement(sql);
		
		   ResultSet rs=pmt.executeQuery();
		
		   while(rs.next()){
			
			   typename=rs.getString(1);
			
			}
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	
	return typename;
}


@Override
public int addambDB(Ambulance amb) {
   int result=0;
   try {
	   String sql="insert into apm_ambulance(vehicleno,ambulancetype,drivername)values(?,?,?)";
	   PreparedStatement pst=con.prepareStatement(sql);
	   pst.setString(1, amb.getVehicleno());
	   pst.setString(2, amb.getAmbulancetype());
	   pst.setString(3, amb.getDriverfname());
	   result=pst.executeUpdate();
	   
	
} catch (Exception e) {
      e.printStackTrace();
}
	return result;
}


@Override
public ArrayList<Ambulance> getambulancelist() {
        ArrayList<Ambulance>amblist=new ArrayList<Ambulance>();
        try {
        	String sql="select id,ambulancetype from apm_ambulancetype";
        	PreparedStatement pst=con.prepareStatement(sql);
        	ResultSet rs=pst.executeQuery();
        	while(rs.next())
        	{
        		Ambulance amb=new Ambulance();
        		amb.setId(rs.getString(1));
        		amb.setAmbulancetype(rs.getString(2));
        		amblist.add(amb);
        	}
			
		} catch (Exception e) {
            e.printStackTrace();
		}
	
	return amblist;
}


@Override
public Ambulance getAmbulanceDetail(String selectedid) {
	Ambulance ambulance1=new Ambulance();
	try {
		
		String sql="SELECT * FROM apm_ambulance where id='"+selectedid+"'";
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		while(rs.next())
		{
		
		    ambulance1.setId(selectedid);	
			ambulance1.setVehicleno(rs.getString(2));
			ambulance1.setAmbulancetype(rs.getString(3));
			ambulance1.setDriverfname(rs.getString(4));
		}
		
	} catch (Exception e) {
          e.printStackTrace();
	}
	return ambulance1;
}


@Override
public int updateAmbulanceDB(Ambulance amb) {
	int result=0;
	try {
		String sql="update apm_ambulance set vehicleno=?, ambulancetype=?, drivername=? where id='"+amb.getId()+"'";
		PreparedStatement pst=con.prepareStatement(sql);
		pst.setString(1, amb.getVehicleno());
		pst.setString(2, amb.getAmbulancetype());
		pst.setString(3, amb.getDriverfname());
		result=pst.executeUpdate();
		
	} catch (Exception e) {
   e.printStackTrace();	
   }
	return result;
}


@Override
public int deleteAmbulance(String selectedid) {
	PreparedStatement pst=null;
	int result=0;
	try {
		
		String sql="delete from apm_ambulance where id='"+selectedid+"'";
		pst=con.prepareStatement(sql);
		result=pst.executeUpdate();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}


@Override
public ArrayList<Ambulance> getDriverlist() {
	ArrayList<Ambulance>driverlist=new ArrayList<Ambulance>();
	try {
		 
		String sql="select id, concat(firstname,' ',lastname) as driverfname from apm_user where jobtitle='DRIVER'";
		PreparedStatement pst=con.prepareStatement(sql);
    	ResultSet rs=pst.executeQuery();
    	while(rs.next())
    	{
    		
    		Ambulance amb=new Ambulance();
    		
    		amb.setId(rs.getString(1));
    		amb.setDriverfname(rs.getString(2));
    		
    		driverlist.add(amb);
    		
    	}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return driverlist;
}


 
}
