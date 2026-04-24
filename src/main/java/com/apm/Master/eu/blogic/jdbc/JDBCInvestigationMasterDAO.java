package com.apm.Master.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.apm.Emr.eu.bi.InvestigationDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCInvestigationDAO;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Master.eu.bi.InvestigationMasterDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;

public class JDBCInvestigationMasterDAO implements InvestigationMasterDAO {

	Connection connection;

	public JDBCInvestigationMasterDAO(Connection connection) {

		this.connection = connection;
	}

	public ArrayList<Master> getAllInvestigationTypes(Pagination pagination,String searchText, String isdeletd, ArrayList<Bed> wardlist) {
		// TODO Auto-generated method stub

		ArrayList<Master> list = new ArrayList<Master>();
		if(isdeletd.equals("2")||isdeletd.equals("")){
			isdeletd="0,1";
		}
		try {
			String sql ="";
			if (searchText!=null) {
				sql = "select id,name,reporttype,charge,roundcharge,isdeleted,sectionid,CGHS from apm_investigation_type where name like ('"+searchText+"%') and isdeleted in("+isdeletd+")  order by id desc";
			} else {
				sql = "select id,name,reporttype,charge,roundcharge,isdeleted,sectionid,CGHS from apm_investigation_type where isdeleted in("+isdeletd+")  order by id desc";
			}
			sql=pagination.getSQLQuery(sql);
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			InvestigationDAO investigationDAO= new  JDBCInvestigationDAO(connection);
			while (rs.next()) {

				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				master.setReport_type(rs.getString(3));
				master.setCharge(rs.getString(4));
				master.setRoundcharge(rs.getBoolean(5));
				master.setIsdeleted(rs.getInt(6));
				master.setSectionid(investigationDAO.getInvSectionName(rs.getString(7)));
				master.setAltername(DateTimeUtils.isNull(rs.getString(8)));
				String charge = getCurrentInvChargeAmount(master.getName(),"0","0");
				master.setCharge(charge);
				master.setWardlist(wardlist);
				list.add(master);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	
	private String getCurrentInvChargeAmount(String invtypename, String wardid, String paytype) {
		String res ="0";
		try {
			String sql="SELECT charges from apm_appointment_type where name=? and wardid=? and tpid=? ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, invtypename);
			ps.setString(2, wardid);
			ps.setString(3, paytype);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				
				res=rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<Master> getAllInvestigationTypes() {
		// TODO Auto-generated method stub

		ArrayList<Master> list = new ArrayList<Master>();

		try {

			String sql = "select id,name,reporttype,charge,department,roundcharge from apm_investigation_type order by id desc";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));
				master.setReport_type(rs.getString(3));
				master.setCharge(rs.getString(4));
				master.setDepartment(rs.getString(5));
				master.setRoundcharge(rs.getBoolean(6));
				list.add(master);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}
	
	
	public int addInvestigationTypeMaster(Master master) {

		int result = 0;
		
		String sql = "insert into apm_investigation_type(name,reporttype,groupname,charge,department,roundcharge,sectionid,defaultremark) values(?,?,?,?,?,?,?,?)";

		try {
			
			if(!master.getGroup().equals("")){
				master.setName(master.getName() + "/" + master.getGroup());
			}

			PreparedStatement ps = connection
					.prepareStatement(sql);
			ps.setString(1, master.getName());
			ps.setString(2,master.getReport_type());
			ps.setString(3, master.getGroup());
			ps.setString(4, master.getCharge());
			ps.setString(5, master.getDepartment()); 
			ps.setBoolean(6, master.isRoundcharge());
			ps.setString(7, master.getSectionid());
			ps.setString(8, master.getDefaultremark());
			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<Master> getAllInvestigationNames(Pagination pagination,String searchText) {

		ArrayList<Master> list = new ArrayList<Master>();

		try {
			String sql ="";
			if (searchText!=null) {
				sql = "select id,invgrpid,name,specimen,reporttype,unit,normvalue,charge from apm_investigation_name where name like ('"+searchText+"%') order by id desc";
			} else {
				sql = "select id,invgrpid,name,specimen,reporttype,unit,normvalue,charge from apm_investigation_name order by id desc";
			}
			sql=pagination.getSQLQuery(sql);
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				int id = rs.getInt(1);
				String invgrpid = rs.getString(2);
				String name = rs.getString(3);
				String specimen = rs.getString(4);
				String reporttype = rs.getString(5);
				String unit = rs.getString(6);
				String normvalue = rs.getString(7);
				
				Master master = new Master();
				master.setId(id);
				master.setParentid(invgrpid);
				
				InvestigationMasterDAO masterDAO = new JDBCInvestigationMasterDAO(
						connection);
				Master mas = masterDAO.getInvestigationType(invgrpid);
				
				
				master.setInvestigation_type_id(mas.getName());
				master.setName(name);
				master.setSpecimen(specimen);
				master.setReport_type(reporttype);
				master.setUnit(unit);
				master.setNormal_value(normvalue);
                master.setCharge(rs.getString(8));
				
				list.add(master);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}
	
	
	public ArrayList<Master> getAllInvestigationNames() {

		ArrayList<Master> list = new ArrayList<Master>();

		try {
			String sql = "select id,invgrpid,name,specimen,reporttype,unit,normvalue,charge from apm_investigation_name";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				int id = rs.getInt(1);
				String invgrpid = rs.getString(2);
				String name = rs.getString(3);
				String specimen = rs.getString(4);
				String reporttype = rs.getString(5);
				String unit = rs.getString(6);
				String normvalue = rs.getString(7);
				Master master = new Master();
				master.setId(id);
				master.setInvestigation_type_id(invgrpid);
				master.setName(name);
				master.setSpecimen(specimen);
				master.setReport_type(reporttype);
				master.setUnit(unit);
				master.setNormal_value(normvalue);
                master.setCharge(rs.getString(8));
				list.add(master);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}
	

	public int getTotalInvestigationTypesCount(String searchText) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql ="";
		if (searchText!=null) {
			sql = "select count(*) from apm_investigation_type where name like ('"+searchText+"%')";
		} else {
			sql = "select count(*) from apm_investigation_type";
		}
		try {
			preparedStatement = connection.prepareStatement(sql);

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getTotalInvestigationNamesCount(String searchText) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		int result = 0;
		
		String sql ="";
		if (searchText!=null) {
			sql = "select count(*) from apm_investigation_name where name like ('"+searchText+"%')";
		} else {
			sql = "select count(*) from apm_investigation_name";
		}
		
		try {
			preparedStatement = connection.prepareStatement(sql);

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int addInvestigationName(Master master) {
		// TODO Auto-generated method stub
		int result = 0;

		try {

			PreparedStatement ps = connection
					.prepareStatement("insert into apm_investigation_name (invgrpid,name,specimen,reporttype,unit,normvalue,charge) values (?,?,?,?,?,?,?)");
			ps.setString(1, String.valueOf(master.getId()));
			ps.setString(2, master.getName());
			ps.setString(3, master.getSpecimen());
			ps.setString(4, master.getReport_type());
			ps.setString(5, master.getUnit());
			ps.setString(6, master.getNormal_value());
			ps.setString(7, master.getCharge());
			
			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public int deleteInvestigationName(String selectedid) {
		// TODO Auto-generated method stub
		int result = 0;

		try {

			PreparedStatement ps = connection
					.prepareStatement("delete from apm_investigation_name where id=?");
			ps.setString(1, selectedid);
			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public Master getInvestigationName(String selectedid) {

		Master master = new Master();

		try {

			PreparedStatement ps = connection
					.prepareStatement("select invgrpid,name,specimen,reporttype,unit,normvalue,charge from apm_investigation_name where id=?");
			ps.setString(1, selectedid);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				master.setId(Integer.parseInt(selectedid));
				master.setInvestigation_type_id(rs.getString(1));
				master.setName(rs.getString(2));
				master.setSpecimen(rs.getString(3));
				master.setReport_type(rs.getString(4));
				master.setUnit(rs.getString(5));
				master.setNormal_value(rs.getString(6));
				master.setCharge(rs.getString(7));
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return master;
	}

	public int updateInvestigationName(Master master) {

		int result = 0;

		try {

			PreparedStatement ps = connection
					.prepareStatement("update apm_investigation_name set invgrpid=?,name=?,specimen=?,reporttype=?,unit=?,normvalue=?,charge=? where id="+master.getId()+"");
			ps.setString(1, master.getInvestigation_type_id());
			ps.setString(2, master.getName());
			ps.setString(3, master.getSpecimen());
			ps.setString(4, master.getReport_type());
			ps.setString(5, master.getUnit());
			ps.setString(6, master.getNormal_value());
			ps.setString(7, master.getCharge());
			

			result = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public Master getInvestigationType(String selectedid) {

		Master master = new Master();

		try {

			PreparedStatement ps = connection
					.prepareStatement("select name,reporttype,groupname,charge,department,roundcharge,sectionid,defaultremark from apm_investigation_type where id="+selectedid+"");
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				master.setName(rs.getString(1));
//				String name=master.getName().replaceAll(" ","");
//				master.setName(name);
				master.setId(Integer.parseInt(selectedid));
				master.setReport_type(rs.getString(2));
				master.setGroup(rs.getString(3));
                master.setCharge(rs.getString(4));    
                master.setDepartment(rs.getString(5));
                master.setRoundcharge(rs.getBoolean(6));
                master.setSectionid(rs.getString(7));
                master.setDefaultremark(rs.getString(8));
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return master;
	}

	public int updateInvestigationType(Master master) {

		int result=0;
		
		try {
			
			if(!master.getGroup().equals("")){
				master.setName(master.getName() + "/" + master.getGroup());
			}
//			String name1=master.getName().replaceAll(" ","");
//			master.setName(name1);;
			PreparedStatement ps=connection.prepareStatement("update apm_investigation_type set name=?,reporttype=?,groupname=?,charge=?,department=?,roundcharge=?,sectionid=?,defaultremark=? where id="+master.getId()+"");
			ps.setString(1, master.getName());
		//	ps.setInt(2, master.getId());
			ps.setString(2, master.getReport_type());
			ps.setString(3, master.getGroup());
			ps.setString(4, master.getCharge());
			ps.setString(5, master.getDepartment());
			ps.setBoolean(6,master.isRoundcharge());
			ps.setString(7, master.getSectionid());
			ps.setString(8, master.getDefaultremark());
			result=ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public int deleteInvestigationType(String selectedid) {

		int result=0;
		
		try {
			
			   PreparedStatement ps=connection.prepareStatement("delete from apm_investigation_type where id=?");
			   ps.setString(1, selectedid);
			   result=ps.executeUpdate();
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
	
		return result;
	}
	
	public String getInvestigationTypeId(String invstype) {

		String invsid="";
		try {
			String sql="select id from apm_investigation_type where name='"+invstype+"' ";
			PreparedStatement ps=connection.prepareStatement(sql);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				
				  invsid=rs.getString(1);
			}
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return invsid;
	}

	public int getInvsNameId(String invgrpid, String invsname) {

		int result=0;
		try {
			
			String sql="SELECT id from apm_investigation_name where invgrpid="+invgrpid+" and name='"+invsname+"' ";
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

	public ArrayList<Master> getAllSectionList() {
		ArrayList<Master> arrayList = new ArrayList<Master>();
		try {
			String sql = "select id,name from apm_investigation_section";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Master master = new Master();
				master.setId(resultSet.getInt(1));
				master.setName(resultSet.getString(2));
				arrayList.add(master);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public ArrayList<Master> getAllJobTitle() {
		ArrayList<Master> arrayList = new ArrayList<Master>();
		try {
			String sql = "select id, jobtitle  from job_title";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Master master = new Master();
				master.setId(resultSet.getInt(1));
				master.setName(resultSet.getString(2));
				arrayList.add(master);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public ArrayList<Master> getSectionList(String id) {
		
			 
			 ArrayList<Master>list = new ArrayList<Master>();
			 try {
			  Statement stmt = connection.createStatement();
			  String query = "select id,name from apm_investigation_section where jobtitle='"+id+"'";
			  
			  ResultSet rs = stmt.executeQuery(query);
			  while(rs.next()) {
			   Master master = new  Master();
			   master.setId(rs.getInt(1));
			   master.setName(rs.getString(2));
			   list.add(master);
			  }
			 
			  stmt.close();
			 } catch (Exception e) {
			  e.printStackTrace();
			 }
			 return list;
		
	}

	public int isdeletedInvestigationType(String selectedid) {

		int result=0;
		
		try {
			
			   PreparedStatement ps=connection.prepareStatement("update apm_investigation_type set isdeleted=? where id='"+selectedid+"' ");
			   ps.setString(1, "1");
			   result=ps.executeUpdate();
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
	
		return result;
	}

	public int addInvAppointType(Master master,int wardid) {
	int result = 0;
		
		String sql = "insert into apm_appointment_type(name,charges,chargeType,wardid,tpid) values(?,?,?,?,?)";

		try {
			
			String name=master.getName();
			PreparedStatement ps = connection
					.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, master.getCharge());
			ps.setString(3, "INVESTIGATION"); 
			ps.setInt(4, wardid);
			ps.setInt(5, 0);
			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public boolean checkExistAppointType(Master master1, int id) {
		boolean res=false;
		try {
			String name="";
			String name1=master1.getName();
//			name1=master1.getName().replaceAll(" ","");
			if(master1.getGroup()!=null){
				if(!master1.getGroup().equals("")){
					
					 name=name1+"/"+master1.getGroup();
				}else{
					 name=name1;
				}
			}else{
				 name=name1;
			}
			
			String sql="SELECT * from apm_appointment_type where name='"+name+"' and wardid="+id+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				
				res=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int updateInvAppointType(Master master, int id,int sts) {
		int result = 0;
		String name="";
		if(master.getGroup()!=null){
			if(!master.getGroup().equals("")){
				 name=master.getName()+"/"+master.getGroup();
			}else{
				 name=master.getName();
			}
		}else{
			 name=master.getName();
		}
		
		String sql="";
		if(sts==0){
			 sql = "update apm_appointment_type set name=?,charges=? where name='"+name+"' ";
		}else{
			 sql = "update apm_appointment_type set name=?,charges=? where name='"+name+"' and wardid="+id+"";
		}
		

		try {
			
			PreparedStatement ps = connection
					.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, master.getCharge());
			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public int updateInvestigationTypeData(String id, String alternativetext, String chargess) {
		int res =0;
		try {
			String sql ="update apm_investigation_type set CGHS=?, charge=? where id ='"+id+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, alternativetext);
			preparedStatement.setString(2, chargess);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int getInvChargeId(String invtypename, String wardid, String paytype) {
		int res =0;
		try {
			String sql="SELECT id from apm_appointment_type where name=? and wardid=? and tpid=? ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, invtypename);
			ps.setString(2, wardid);
			ps.setString(3, paytype);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				
				res=rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int updateInvApmentTypeData(int aptid, String chargess) {
		int res =0;
		try {
			String sql ="update apm_appointment_type set charges=? where id ='"+aptid+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, chargess);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	

}
