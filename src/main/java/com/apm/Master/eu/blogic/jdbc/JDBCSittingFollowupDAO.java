package com.apm.Master.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.apm.Master.eu.bi.SittingFollowupDAO;
import com.apm.Master.eu.entity.SittingFollowup;
import com.apm.Master.web.form.SittingFollowupForm;


public class JDBCSittingFollowupDAO implements SittingFollowupDAO {
	
	
	SittingFollowupForm sform=new SittingFollowupForm();
	public Connection con=null;
	
	public JDBCSittingFollowupDAO(Connection con){
		
		this.con=con;
		
		}


public int saveSitting(SittingFollowup sf) {
		// TODO Auto-generated method stub
		
		PreparedStatement pmt=null;
		
		try {
			
			String sql="insert into sittingfollowup_master(sittingFollowup,dept_id)values(?,?)";
			pmt=con.prepareStatement(sql);
			
			pmt.setString(1, sf.getSittingFollowup());
			pmt.setString(2, sf.getDepartment());
			
			
			int result=pmt.executeUpdate();
			
			} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return 0;
	}


    @Override
	public ArrayList<SittingFollowup> getsittinglist() {
		
		// TODO Auto-generated method stub
		
		ArrayList<SittingFollowup>list=new ArrayList<SittingFollowup>();
		PreparedStatement pmt=null;
		
		try {
			
			String sql="select id,sittingFollowup,dept_id from sittingfollowup_master";
			pmt=con.prepareStatement(sql);
			
			ResultSet rs=pmt.executeQuery();
			
			
			while(rs.next()){
				
				String name=departmentid(rs.getString(3));
				
				SittingFollowup sf=new SittingFollowup();
				
				sf.setId(rs.getString(1));
				sf.setSittingFollowup(rs.getString(2));
				sf.setDepartment(name);
				
				list.add(sf);
				
			}
			
			
	   } catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return list;
	}


 public String departmentid(String string) {
		// TODO Auto-generated method stub
		
        PreparedStatement pmt=null;
         
		String name="";
         
         try {
			
			   String sql="select discipline from apm_discipline where id='"+string+"'";
			   pmt=con.prepareStatement(sql);
			
			   ResultSet rs=pmt.executeQuery();
			
			   while(rs.next()){
				
				name=rs.getString(1);
				
				}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		  return name ;
		  
 }

    @Override
	public SittingFollowup geteditsitting(String selectedid) {
		// TODO Auto-generated method stub
		
		PreparedStatement pmt=null;
		SittingFollowup sf=new SittingFollowup();
		
		try {
			
			String sql="select sittingFollowup,dept_id from sittingfollowup_master where id="+selectedid+"";
			pmt=con.prepareStatement(sql);
			
			ResultSet rs=pmt.executeQuery();
			
			while(rs.next()){
				
				sf.setId(selectedid);
				sf.setSittingFollowup(rs.getString(1));
				sf.setDepartment(rs.getString(2));
				
				}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return sf;
}


public int updatesitting(SittingFollowup sf) {
		// TODO Auto-generated method stub
		
		PreparedStatement pmt=null;
		String sql="update sittingfollowup_master set sittingFollowup=?,dept_id=? where id=?";
	
		int result=0;
		
		try {
			
			pmt=con.prepareStatement(sql);
			
			pmt.setString(1, sf.getSittingFollowup());
			pmt.setString(2, sf.getDepartment());
			pmt.setString(3, sf.getId());
			
			result=pmt.executeUpdate();
			
	     } catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
		
	}


public int deletesittingData(String selectedid) {
		// TODO Auto-generated method stub
		
		PreparedStatement pmt=null;
		int result=0;
		
		try {
			
			String sql="delete from sittingfollowup_master where id="+selectedid+"";
			
			pmt=con.prepareStatement(sql);
			result=pmt.executeUpdate();
			
			} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		  }
		
		return result;
	}


@Override
public ArrayList<SittingFollowup> getAllSittingList(String location) {
	// TODO Auto-generated method stub
	
	ArrayList<SittingFollowup>list=new ArrayList<SittingFollowup>();
	
	try {
		
		StringBuffer buffer=new StringBuffer();
		buffer.append(" select id,sittingFollowup,dept_id from sittingfollowup_master ");
		
		if(!location.equals("0")){
			buffer.append("where dept_id="+location+"");
		}
		buffer.append(" order by id desc");
		
		PreparedStatement ps=con.prepareStatement(buffer.toString());
		ResultSet rs =ps.executeQuery();
		
		while(rs.next()){
			
			SittingFollowup sittingFollowup=new SittingFollowup();
			
			sittingFollowup.setId(rs.getString(1));
			sittingFollowup.setSittingFollowup(rs.getString(2));
			sittingFollowup.setDepartment(rs.getString(3));
			
			list.add(sittingFollowup);
			
			}

		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	
	return list;
}
	
}
