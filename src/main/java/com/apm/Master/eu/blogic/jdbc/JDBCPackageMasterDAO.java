package com.apm.Master.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import com.apm.Master.eu.bi.PackageMasterDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.PackageMaster;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;



public class JDBCPackageMasterDAO implements PackageMasterDAO {
	Connection connection;
	public JDBCPackageMasterDAO(Connection connection) {
		this.connection = connection;
	}
	
	
	public String getChargeNameById(String chargeid) {
		String str ="";
		try {
			String sql = "select name from apm_newchargetype where id="+chargeid+"";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				str = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}


	public int storePackageParentData(String package_name, String package_amount, String packagetype, String inveschargeid,String istp,String description, String days) {
		int result =0;
		if(istp==null){
			istp="0";
			packagetype="0";
		}
		try {
			String query = "insert into his_parent_package(name,amount,packagetype,invtypeid,third_party,days,description) values(?,?,?,?,?,?,?)";
			
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, package_name);
			preparedStatement.setString(2, package_amount);
			preparedStatement.setString(3, packagetype);
			preparedStatement.setString(4, inveschargeid);
			preparedStatement.setString(5, istp);
			preparedStatement.setString(6, DateTimeUtils.numberCheck(days));
			preparedStatement.setString(7, DateTimeUtils.isNull(description));
			result = preparedStatement.executeUpdate();
		
			if(result>0)
			{
				ResultSet resultSet=preparedStatement.getGeneratedKeys();
				
				while(resultSet.next()){
				     result= resultSet.getInt(1);	
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	public int storePackageChildData(PackageMaster packageMaster, int result) {
		int result1 = 0;
		try {
			String query ="insert into his_child_package(packageid, chargeid, chargename, percentage, cal_amount,ischargetype,tpcode) values(?,?,?,?,?,?,?)";
		
			PreparedStatement statement = connection.prepareStatement(query);
			String str = packageMaster.getChargeid();
			String str2 = packageMaster.getChargename();
			String str3 = packageMaster.getPercentage();
			String str4 = packageMaster.getCal_amount();
			statement.setString(1, ""+result);
			statement.setString(2, packageMaster.getChargeid());
			statement.setString(3, packageMaster.getChargename());
			statement.setString(4, packageMaster.getPercentage());
			statement.setString(5, packageMaster.getCal_amount());
			statement.setString(6, packageMaster.getIschargetype());
			statement.setString(7, packageMaster.getCode());
			result1 = statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result1;

	}


	public ArrayList<PackageMaster> getAllPackage(String searchText) {
		ArrayList<PackageMaster> users = new ArrayList<PackageMaster>();
		try {
			Statement stmt = connection.createStatement();
			String sql ="";
			if (searchText!=null) {
				sql = "select id,name,amount from his_parent_package where name like ('"+searchText+"%')";
			} else {
				sql = "select id,name,amount from his_parent_package";
			}
			
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				PackageMaster packageMaster = new PackageMaster();
				packageMaster.setId(rs.getInt(1));
				packageMaster.setName(rs.getString(2));
				packageMaster.setAmount(rs.getString(3));
				users.add(packageMaster);
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}


	public int deletePackage(PackageMaster packageMaster) {
		int result = 0;
		try {
			String query = "delete from his_parent_package where id="+packageMaster.getId();
			PreparedStatement stmt = connection.prepareStatement(query);
			result = stmt.executeUpdate();
			
			String sql = "delete from his_child_package where packageid="+packageMaster.getId()+"";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	public ArrayList<PackageMaster> getPerticularPackageForEdit(String id) {
		ArrayList<PackageMaster> arrayList = new ArrayList<PackageMaster>();
		try {
			String sql = "select id, name, amount from his_parent_package where id ="+id+"";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				PackageMaster packageMaster = new PackageMaster();
				packageMaster.setId(rs.getInt(1));
				packageMaster.setName(rs.getString(2));
				packageMaster.setAmount(rs.getString(3));
				ArrayList<PackageMaster> list = getPackageFromChild(rs.getInt(1));
				packageMaster.setChildpackagelist(list);
				arrayList.add(packageMaster);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}


	public ArrayList<PackageMaster> getPackageFromChild(int int1) {
		ArrayList<PackageMaster> arrayList = new ArrayList<PackageMaster>();
		try {
			String sql = "select id, chargeid, chargename, percentage, cal_amount,ischargetype,tpcode from his_child_package where packageid='"+int1+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				PackageMaster packageMaster = new PackageMaster();
				packageMaster.setChild_id(rs.getInt(1));
				packageMaster.setChargeid(rs.getString(2));
				packageMaster.setChargename(rs.getString(3));
				packageMaster.setPercentage(DateTimeUtils.isNull(rs.getString(4)));
				packageMaster.setCal_amount(rs.getString(5));
				packageMaster.setIschargetype(rs.getString(6));
				packageMaster.setCode(rs.getString(7));
				packageMaster.setId(int1);
				arrayList.add(packageMaster);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}


	public PackageMaster getPerticularPackage(String id) {
		PackageMaster packageMaster = new PackageMaster();
		try {
			String sql = "select id, name, amount,packagetype,invtypeid,third_party from his_parent_package where id ='"+id+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				packageMaster.setId(rs.getInt(1));
				packageMaster.setName(rs.getString(2));
				packageMaster.setAmount(rs.getString(3));
				packageMaster.setPackagetype(rs.getString(4));
				if(rs.getString(5).equals("0")){
					packageMaster.setIschargetype("0");
				}else{
					packageMaster.setIschargetype("1");
				}
				packageMaster.setTp(rs.getBoolean(6));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return packageMaster;
	}


	public int updateParentPackageData(String parentid, String package_name,
			String package_amount, String inveschargeid) {
		int result = 0;
		try {
			String sql ="update his_parent_package set name=?, amount=?,invtypeid=? where id="+parentid+"";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, package_name);
			preparedStatement.setString(2, package_amount);
			preparedStatement.setString(3, inveschargeid);
			result = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	public int updateChildPackageData(PackageMaster packageMaster) {
		int result = 0;
		try {
			String str = packageMaster.getChargeid();
			String str1 = packageMaster.getChargename();
			String str2 = packageMaster.getPercentage();
			String str3 =  packageMaster.getCal_amount();
			String sql ="update his_child_package set chargeid=?, chargename=?, percentage=?, cal_amount=?,tpcode=? where id="+packageMaster.getChild_id()+"";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, packageMaster.getChargeid());
			preparedStatement.setString(2, packageMaster.getChargename());
			preparedStatement.setString(3, packageMaster.getPercentage());
			preparedStatement.setString(4, packageMaster.getCal_amount());
			preparedStatement.setString(5, packageMaster.getCode());
			result = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	public ArrayList<PackageMaster> getAllPackage(String searchText,
			Pagination pagination) {
		ArrayList<PackageMaster> users = new ArrayList<PackageMaster>();
		try {
			Statement stmt = connection.createStatement();
			String sql ="";
			if (searchText!=null) {
				sql = "select id,name,amount,third_party from his_parent_package where name like ('"+searchText+"%')";
			} else {
				sql = "select id,name,amount,third_party from his_parent_package";
			}
			if(pagination!=null) 
			{
				  sql=pagination.getSQLQuery(sql);
		    }
			
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				PackageMaster packageMaster = new PackageMaster();
				packageMaster.setId(rs.getInt(1));
				packageMaster.setName(rs.getString(2));
				packageMaster.setAmount(rs.getString(3));
				packageMaster.setTp(rs.getBoolean(4));
				users.add(packageMaster);
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}


	public int getTotalPackageMasterCount() {
		int result=0;
		
		 try {
			
			 String sql="select count(*) from his_parent_package";
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


	public int checkPackageName(String name) {
		int result=0;
		
		 try {
			
			 String sql="select id from his_parent_package where name ='"+name+"'";
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


	public ArrayList<Master> getmasterChageNameList() {
		ArrayList<Master> list = new ArrayList<Master>();
		try{
			PreparedStatement preparedStatement = null;
			/*String sql = "SELECT id,name,chargeType FROM apm_appointment_type where chargeType = 'INVESTIGATION' and tpid=0 and wardid = 0;";*/
			String sql = "SELECT id,name FROM apm_investigation_type";
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


	public String getChargeNameByIdNew(String chargeid) {
		String str ="";
		try {
			//String sql = "select name from apm_appointment_type where id="+chargeid+"";
			String sql = "select name from apm_investigation_type where id="+chargeid+"";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				str = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}


	public int deleteChildPkgAjax(String childid) {
		int result=0;
		try {
			PreparedStatement ps= null;
			String sql ="delete from his_child_package where id ="+childid+"";
			ps= connection.prepareStatement(sql);
			result= ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	public String getTPPkgList() {
		String list="";
		StringBuffer buffer= new StringBuffer();
		try {
			PreparedStatement ps= connection.prepareStatement(" select name ,id from his_parent_package where third_party='1' ");
			ResultSet rs= ps.executeQuery();
			buffer.append("<select name='packagename' id='packagename'  class='form-control' onchange='showPackageData(this.value)'>");
			buffer.append("<option value='0'>All Packages</option>");
			while(rs.next()){
				buffer.append("<option value='"+rs.getInt(2)+"'>"+rs.getString(1)+"</option>");
			}
			buffer.append("</select>");
			list= buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	public String nameofApmtCharge(String id) {
		String res="";
		try {
			PreparedStatement ps= connection.prepareStatement("select name from apm_appointment_type where id='"+id+"'");
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				res=rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}


	public Master getCodeAndAmmountOfTpCharge(String clientid, String charge) {
		Master master= new Master();
		try {
			int tpnameid=getThirdptid(clientid);
			int main_tp_id=0;
			if(tpnameid>0){
				main_tp_id= getMainTp(tpnameid);
			}
			if(main_tp_id>0){
				PreparedStatement ps= connection.prepareStatement(" select code,charges from apm_appointment_type where name=? and tpid='"+main_tp_id+"' ");
				ps.setString(1, charge);
				ResultSet rs= ps.executeQuery();
				while(rs.next()){
					master.setCode(rs.getString(1));
					master.setCharge(rs.getString(2));
				}
			}else{
				PreparedStatement ps= connection.prepareStatement(" select code,charges from apm_appointment_type where name=? and tpid=0 and wardid=0");
				ps.setString(1, charge);
				ResultSet rs= ps.executeQuery();
				while(rs.next()){
					master.setCode(rs.getString(1));
					master.setCharge(rs.getString(2));
			}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return master;
	}
	private int getThirdptid(String clientid){
		int res=0;
		try {
			PreparedStatement ps= connection.prepareStatement(" select third_party_name_id from apm_patient where id='"+clientid+"'");
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				res= rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	private int getMainTp(int tp_name_id){
		int res=0;
		try {
			PreparedStatement ps= connection.prepareStatement(" select maintp,followtp from apm_third_party_details where id='"+tp_name_id+"' ");
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				if(rs.getInt(1)==1){
					res= tp_name_id;
				}else{
					res=rs.getInt(2);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}


	public ArrayList<PackageMaster> getAllPackagelist(String searchText, String whopay, Pagination pagination) {

		ArrayList<PackageMaster> users = new ArrayList<PackageMaster>();
		try {
			Statement stmt = connection.createStatement();
			String sql ="";
//			if (searchText!=null) {
//				sql = "select id,name,amount,third_party from his_parent_package where name like ('"+searchText+"%')";
//			} else {
//				sql = "select id,name,amount,third_party from his_parent_package";
//			}
			StringBuffer buffer=new StringBuffer();
			boolean flag=false;
			buffer.append("select id,name,amount,third_party,days, description from his_parent_package ");
			if(!searchText.equals("")){
			buffer.append(" where name like ('%"+searchText+"%')");
			flag=true;
			}
			if(whopay!=null){
				if(!whopay.equals("")){
				if(flag==true){
					buffer.append(" and third_party="+whopay+" ");
				}else{
					buffer.append(" where third_party="+whopay+" ");
				}
			}
		}
			if(pagination!=null) 
			{
				  sql=pagination.getSQLQuery(buffer.toString());
		    }
			
			ResultSet rs = stmt.executeQuery(buffer.toString());
			while (rs.next()) {
				PackageMaster packageMaster = new PackageMaster();
				packageMaster.setId(rs.getInt(1));
				packageMaster.setName(rs.getString(2));
				packageMaster.setAmount(rs.getString(3));
				packageMaster.setTp(rs.getBoolean(4));
				packageMaster.setDays(rs.getString(5));
				packageMaster.setDescription(rs.getString(6));
				users.add(packageMaster);
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	
	}


	public ArrayList<PackageMaster> getAllChildPackagelist(String parentid) {
		ArrayList<PackageMaster> users = new ArrayList<PackageMaster>();
		try {
			Statement stmt = connection.createStatement();
			String sql ="";
//			if (searchText!=null) {
//				sql = "select id,name,amount,third_party from his_parent_package where name like ('"+searchText+"%')";
//			} else {
//				sql = "select id,name,amount,third_party from his_parent_package";
//			}
			StringBuffer buffer=new StringBuffer();
			boolean flag=false;
			buffer.append("select name,amount,third_party,chargename,days,description,cal_amount from his_parent_package "
					+ "inner join his_child_package on his_child_package.packageid=his_parent_package.id where packageid="+parentid+" ");
			
			ResultSet rs = stmt.executeQuery(buffer.toString());
			while (rs.next()) {
				PackageMaster packageMaster = new PackageMaster();
				packageMaster.setName(rs.getString(1));
				packageMaster.setAmount(rs.getString(2));
				packageMaster.setTp(rs.getBoolean(3));
				packageMaster.setChargename(rs.getString(4));
				packageMaster.setDays(rs.getString(5));
				packageMaster.setDescription(rs.getString(6));
				packageMaster.setCal_amount(rs.getString(7));;
				users.add(packageMaster);
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}
}
