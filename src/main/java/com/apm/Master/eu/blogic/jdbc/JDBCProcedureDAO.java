package com.apm.Master.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.apm.Master.eu.bi.ProcedureMasterDao;
import com.apm.Master.eu.entity.Procedure;
import com.apm.Master.eu.entity.SittingFollowup;
import com.apm.Master.web.form.ProcedureForm;

public class JDBCProcedureDAO implements ProcedureMasterDao {
	
	ProcedureForm procedureForm=new ProcedureForm();
	
	public Connection con=null;
	
	public JDBCProcedureDAO(Connection con){
		
		this.con=con;
		
	}

	@Override
	public int saveProcedure(Procedure procedure) {
		// TODO Auto-generated method stub
		int result=0;
		PreparedStatement pmt=null;
		
		try {
			
			String sql="insert into apm_appointment_type(chargetype,name,charges,isprocedured,procedureid)values(?,?,?,?,?)";
			pmt=con.prepareStatement(sql);
			
			pmt.setString(1, procedure.getProceduretype());
			pmt.setString(2, procedure.getProcedureName());
			pmt.setString(3, procedure.getCharges());
			pmt.setString(4, "1");
			pmt.setString(5, procedure.getProcedureid());
			
			result=pmt.executeUpdate();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public ArrayList<Procedure> getprocedurelist(String searchText) {
		ArrayList<Procedure>list=new ArrayList<Procedure>();
		PreparedStatement pmt=null;
		try {
			String sql="";
			if(searchText!=null){
				sql="select id,chargetype,name,charges,isprocedured from apm_appointment_type where  isprocedured=1 AND name like ('"+searchText+"%')";
			}else{
				 sql="select id,chargetype,name,charges,isprocedured from apm_appointment_type where isprocedured=1 ";
			}
			pmt=con.prepareStatement(sql);
			ResultSet rs=pmt.executeQuery();
			while(rs.next()){
				Procedure procedure=new Procedure();
				procedure.setId(rs.getString(1));
				procedure.setProceduretype(rs.getString(2));
				procedure.setProcedureName(rs.getString(3));
				procedure.setCharges(rs.getString(4));
				procedure.setIsprocedure(rs.getString(5));
				list.add(procedure);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public Procedure geteditprocedure(String selectedid) {
		// TODO Auto-generated method stub
		
		PreparedStatement pmt=null;
		Procedure procedure=new Procedure();
		try {
			
			String sql="select chargetype,name,charges from apm_appointment_type where id="+selectedid+" ";
			
            pmt=con.prepareStatement(sql);
	        ResultSet rs =  pmt.executeQuery();
			
			while(rs.next()){
				
				procedure.setId(selectedid);
				procedure.setProceduretype(rs.getString(1));
				procedure.setProcedureName(rs.getString(2));
				procedure.setCharges(rs.getString(3));
				
				}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return procedure;
	}

	@Override
	public int updateprocedure(Procedure procedure) {
		// TODO Auto-generated method stub
		
		PreparedStatement pmt=null;
		String sql="update apm_appointment_type set chargetype=?,name=?,charges=? where id=?";
		
		int result=0;
		
		try {
			
			pmt=con.prepareStatement(sql);
			
			pmt.setString(1, procedure.getProceduretype());
			pmt.setString(2, procedure.getProcedureName());
			pmt.setString(3, procedure.getCharges());
			pmt.setString(4, procedure.getId());
			
			result=pmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public int deleteprocedureData(String selectedid) {
		// TODO Auto-generated method stub
		
		PreparedStatement pmt=null;
		int result=0;
		
		try {
			
			String sql="delete from apm_appointment_type where id="+selectedid+"";
			pmt=con.prepareStatement(sql);
			result=pmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}
	
 }

