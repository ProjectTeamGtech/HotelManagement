package com.apm.Master.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.apm.Ipd.eu.entity.Bams;
import com.apm.Master.eu.bi.DischargeOutcomeDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.common.web.common.helper.LoginInfo;

public class JDBCDischargeOutcomeDAO implements DischargeOutcomeDAO {

	Connection connection;

	public JDBCDischargeOutcomeDAO(Connection connection) {
		this.connection = connection;
	}

	
	public ArrayList<Master> getAllDischargeOutcome() {

		ArrayList<Master> masters = new ArrayList<Master>();
		try {

			PreparedStatement ps = connection
					.prepareStatement("select * from apm_discharge_outcomes");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				Master master = new Master();
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String description = rs.getString(3);
				master.setId(id);
				master.setName(name);
				master.setDescription(description);
				masters.add(master);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return masters;
	}

	
	public int addDischargeOutcome(Master master) {
		int result = 0;
		try {

			PreparedStatement ps = connection
					.prepareStatement("insert into apm_discharge_outcomes (name,description) values (?,?)");
			ps.setString(1, master.getName());
			ps.setString(2, master.getDescription());
			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	
	public Master getMaster(int id) {
		// TODO Auto-generated method stub

		Master master = new Master();
		try {

			PreparedStatement ps = connection
					.prepareStatement("select * from apm_discharge_outcomes where id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				String name = rs.getString("name");
				String description = rs.getString("description");
				master.setId(id);
				master.setName(name);
				master.setDescription(description);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return master;
	}

	
	public int updateDischargeOutcome(Master master) {
		// TODO Auto-generated method stub
		int result = 0;

		try {

			PreparedStatement ps = connection
					.prepareStatement("update apm_discharge_outcomes set name=?,description=? where id=?");
			ps.setString(1, master.getName());
			ps.setString(2, master.getDescription());
			ps.setInt(3, master.getId());
			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	
	public int deleteDischargeOutcome(int id) {
		// TODO Auto-generated method stub
		int result = 0;
		try {

			PreparedStatement ps = connection
					.prepareStatement("delete from apm_discharge_outcomes where id=?");
			ps.setInt(1, id);
			result = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	
	public ArrayList<Master> getMasterList(LoginInfo loginInfo) {
		// TODO Auto-generated method stub
		ArrayList<Master> masters=new ArrayList<Master>();
		PreparedStatement ps=null;
		try {
			  if(loginInfo.getId()==1){
			       ps=connection.prepareStatement("select * from apm_select_master order by mastername");
			  }
			  else{
			       ps=connection.prepareStatement("select * from apm_select_master where id!=22 order by mastername");
			  }
			  ResultSet rs=ps.executeQuery();
			  while(rs.next())
			  {    
				    Master master=new Master();
				    int id=rs.getInt(1);
				    String mastername=rs.getString(2);
				    master.setId(id);
				    master.setName(mastername);
				    masters.add(master);
			  }
            			
		} catch (Exception e) {

		   e.printStackTrace();
		}
			
		return masters;
	}


	public ArrayList<Master> getNewChargeTypeList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list = new ArrayList<Master>();
		String sql = "SELECT id,name FROM apm_newchargetype order by name ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				
				list.add(master);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}


	public ArrayList<Master> getNewChargeTypeListProc() {
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list = new ArrayList<Master>();
		String sql = "SELECT id,name FROM apm_newchargetype where procedures='1' order by name ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				
				list.add(master);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}


	@Override
	public ArrayList<Master> getMasterChargeTypeList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Master>list = new ArrayList<Master>();
		String sql = "SELECT id,name FROM apm_newchargetype where procedures='0' order by name ";
		
		try{
			
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				
				list.add(master);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}


	@Override
	public ArrayList<Bams> getVitals(String fromdate, String todate, String ipdid) {
		ArrayList<Bams> bamsList = new ArrayList<>();
		try {

			PreparedStatement ps = connection
					.prepareStatement("select height,weight,bmi,pulse,sysbp,diabp,sugarfasting,postmeal,tempr,spo from his_bmi "
							+ "where datetime between '"+fromdate+"' and '"+todate+"' and clientid="+ipdid+"");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Bams bams=new Bams();
				bams.setHeight(rs.getString(1)!=null?rs.getString(1):"No Record");
				bams.setWeight(rs.getString(2)!=null?rs.getString(2):"No Record");
				bams.setBmi(rs.getString(3)!=null?rs.getString(3):"No Record");
				bams.setPulse(rs.getString(4)!=null?rs.getString(4):"No Record");
				bams.setSysbp(rs.getString(5)!=null?rs.getString(5):"No Record");
				bams.setDiabp(rs.getString(6)!=null?rs.getString(6):"No Record");
				bams.setSugarfasting(rs.getString(7)!=null?rs.getString(7):"No Record");
				bams.setPostmeal(rs.getString(8)!=null?rs.getString(8):"No Record");
				bams.setTemp(rs.getString(9)!=null?rs.getString(9):"No Record");
				bams.setSpo2(rs.getString(10)!=null?rs.getString(10):"No Record");
				bamsList.add(bams);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return bamsList;
	}


	@Override
	public Bams getKarmaproceduredata(String clientid, String apmtId) {
		PreparedStatement pst=null;
		Bams bams=new Bams();
		StringBuffer buffer=new StringBuffer();
		try {
			buffer.append("select karma, procedurebams from consultation_form where client_id='"+clientid+"' and apmt_id='"+apmtId+"'");
			pst=connection.prepareStatement(buffer.toString());
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				String karma=rs.getString(1).replace("<br>", "");
				bams.setKarma(karma);
				String procedurebams=rs.getString(2).replace("<br>", "");
				bams.setProcedurebams(procedurebams);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bams;
	}

}
