package com.apm.Master.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.apm.Master.eu.bi.InvestigationTemplateDAO;
import com.apm.Master.eu.entity.InvestigationTemplate;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.MedicineType;
import com.apm.common.eu.blogic.jdbc.JDBCBaseDAO;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;

public class JDBCInvestigationTemplate extends JDBCBaseDAO implements InvestigationTemplateDAO {
	public JDBCInvestigationTemplate(Connection connection)
	 {
		 this.connection = connection;
	 }
	
	
	public int getTotalInvTempCount() {

		int result=0;
		try {
			String sql="select count(*) from  apm_investigation_template ";
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
	public ArrayList<InvestigationTemplate> getAllInvestigationTemplateList(String searchText,Pagination pagination) {
		ArrayList<InvestigationTemplate> users = new ArrayList<InvestigationTemplate>();
		try {
			String sql ="";
			StringBuffer buffer= new StringBuffer();
			
			if (searchText!=null) {
				//sql = "select id,invs_type,name,template from apm_investigation_template where name like ('"+searchText+"%')";
				buffer.append("SELECT apm_investigation_template.id, apm_investigation_template.invs_type, apm_investigation_template.name, apm_investigation_template.template,apm_investigation_template.invs_section,apm_investigation_template.datetime from apm_investigation_template left join apm_investigation_section ");
				buffer.append("on apm_investigation_section.id=apm_investigation_template.invs_section where  ");
				buffer.append("(apm_investigation_template.name like ('"+searchText+"%') or apm_investigation_section.name like ('"+searchText+"%') ) ");
				
			} else {
				//sql = "select id,invs_type,name,template from apm_investigation_template;";
				buffer.append("SELECT id, invs_type, name, template,invs_section,datetime from apm_investigation_template ");
				
			}
			if(pagination!=null)
			{
				sql=pagination.getSQLQuery(buffer.toString());
			}
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
			  InvestigationTemplate master = new InvestigationTemplate();
				master.setId(rs.getInt(1));
				master.setInvestigationtype(rs.getString(2));
				master.setTitle(rs.getString(3));
				master.setTemplate_text(rs.getString(4));
				master.setInvs_section_id(rs.getString(5));

				String dateTime= rs.getString(6);
				if(dateTime==null){
					master.setDateTime("");
				} else {
					master.setDateTime(DateTimeUtils.getDBDate(dateTime));
				}
				
				master.setInvs_section_name(getInvsSectionName(master.getInvs_section_id()));
				users.add(master);
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}
	
	public ArrayList<InvestigationTemplate> getInvestigationTypeList(){
		ArrayList<InvestigationTemplate> users=new ArrayList<InvestigationTemplate>();
		try {
     String sql="select id,name from apm_investigation_type;";
     PreparedStatement smt=connection.prepareStatement(sql);
     ResultSet rs=smt.executeQuery();
     while(rs.next()){
    	 InvestigationTemplate invTemp=new InvestigationTemplate();
    	 invTemp.setInv_id(rs.getString(1));
    	 invTemp.setTitle(rs.getString(2));
    	 users.add(invTemp);
         }
			
		} catch (Exception e) {
		}
		
		return users;
	}

	 public int addInvestigationTemplate(InvestigationTemplate master) {
		int result = 0;
		try {
			String query = "insert into apm_investigation_template(invs_type,name,template,invs_section,datetime) values(?,?,?,?,?);";
			PreparedStatement stmt = connection.prepareStatement(query); 
			stmt.setString(1, master.getInv_id());
			stmt.setString(2, master.getTitle());
			stmt.setString(3,master.getTemplate_text());
			stmt.setString(4, master.getInvs_section_id());
			stmt.setString(5, master.getDateTime());
					
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


public InvestigationTemplate getAllInvestigationTemplateListById(InvestigationTemplate master){
	 
	try {
       String sql="select id,invs_type,name,template,invs_section from apm_investigation_template where id="+master.getId();
       PreparedStatement stmt=connection.prepareStatement(sql);
       ResultSet rs=stmt.executeQuery();
       while (rs.next()) {
			
				master.setId(rs.getInt(1));
				master.setInv_id(rs.getString(2));
				master.setTitle(rs.getString(3));
				master.setTemplate_text(rs.getString(4));
				master.setInvs_section_id(rs.getString(5));
				master.setInvs_section_name(getInvsSectionName(master.getInvs_section_id()));
				
			}
       stmt.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return master;
	
}



public int updateinvestigationtemplatelist(InvestigationTemplate master) {
	int result = 0;
	try {
	
		String query = "update apm_investigation_template set  invs_type=? ,name=?,template=?,invs_section=?,datetime=? where id="+master.getId();
		PreparedStatement stmt = connection.prepareStatement(query); 
		stmt.setString(1, master.getInv_id());
		stmt.setString(2, master.getTitle());
		stmt.setString(3,master.getTemplate_text());
		stmt.setString(4, master.getInvs_section_id());
		stmt.setString(5, master.getDateTime());
		
		result = stmt.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}

public int deleteInvestigationTemplate(InvestigationTemplate master){
	 int result = 0;
	 try {
		String query="delete from apm_investigation_template  where id="+master.getId();
		PreparedStatement stmt=connection.prepareStatement(query);
		result=stmt.executeUpdate();
		 
	} catch (Exception e) {
		e.printStackTrace();
	}
	 return result;
}

public String getInvsSectionName(String invs_section_id) {
	
	String res="";
	try {
		String sql="SELECT name from apm_investigation_section where id="+invs_section_id+"";
		PreparedStatement ps=connection.prepareStatement(sql);
		ResultSet rs =ps.executeQuery();
		while(rs.next()){
			  res= rs.getString(1);
		}
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	
	return res;
}

public InvestigationTemplate getAllInvestigationTemplateListById(String getID) {
	// TODO Auto-generated method stub
	return null;
}


public ArrayList<Master> getAllInvestigationSectionList() {

	ArrayList<Master> list= new ArrayList<Master>();
	try {
		
		String sql="SELECT id,name from apm_investigation_section";
		PreparedStatement ps=connection.prepareStatement(sql);
		ResultSet rs =ps.executeQuery();
		while(rs.next()){
			 
			   Master master= new Master();
			   master.setId(rs.getInt(1));
			   master.setName(rs.getString(2));
			   list.add(master);
			   
		}
				
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	
	return list;
}

}