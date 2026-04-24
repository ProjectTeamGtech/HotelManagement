package com.apm.Master.eu.blogic.jdbc;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.apm.Master.eu.bi.CityMasterDAO;
import com.apm.Master.eu.bi.InvestigationSectionDAO;
import com.apm.Master.eu.bi.InvestigationTemplateDAO;
import com.apm.Master.eu.bi.StateDAO;
import com.apm.Master.eu.entity.CityMaster;
import com.apm.Master.eu.entity.InvestigationSection;
import com.apm.Master.eu.entity.InvestigationTemplate;
import com.apm.Master.eu.entity.State;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;

public class JDBCInvestigationSectionDAO extends BaseAction implements InvestigationSectionDAO {
	Connection connection;
	public JDBCInvestigationSectionDAO(Connection connection) {
	this.connection = connection;
	}
	
	public int getTotalInvsSectionCount(){
		int result=0;
		try {
			String sql="select count(*) from apm_investigation_section";
			PreparedStatement stmt=connection.prepareStatement(sql);
			ResultSet rs=stmt.executeQuery();
			while(rs.next())
			{
				result=rs.getInt(1);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public int addInvestigationSection(InvestigationSection master) {
		int result=0;
		try {
			String sql="insert into apm_investigation_section(name,jobtitle) values(?,?)";
			PreparedStatement stmt=connection.prepareStatement(sql);
			stmt.setString(1, master.getName());
			stmt.setString(2, master.getJobtitle());
			result=stmt.executeUpdate();
			
		} catch (Exception e) {
          e.printStackTrace();
		}
		return result;
	}
	public int deleteInvestigationSection(InvestigationSection master) {
		int result=0;
		try {
	        	String sql="delete from apm_investigation_section where id="+master.getId()+"";
	        	PreparedStatement stmt=connection.prepareStatement(sql);
	        	result=stmt.executeUpdate();
	        	
			} catch (Exception e) {
				e.printStackTrace();
			}
		return result;
	}

	public InvestigationSection getAllInvestigationSectionListById(InvestigationSection invSec) {
        InvestigationSection list=new InvestigationSection();
        try {
        	String sql="select id,name,jobtitle from apm_investigation_section where id="+invSec.getId()+"";
        	PreparedStatement stmt=connection.prepareStatement(sql);
        	ResultSet rs=stmt.executeQuery();
        	while(rs.next())
        	{
        		
        		list.setId(rs.getInt(1));
        		list.setName(rs.getString(2));
        		list.setJobtitle(rs.getString(3));
        	}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public ArrayList<InvestigationSection> getInvestigationSectionList(Pagination pagination,String SearchText) {
		 
		ArrayList<InvestigationSection> list=new ArrayList<InvestigationSection>();
		try {
			String sql="SELECT id,name,jobtitle FROM apm_investigation_section";
			PreparedStatement stmt=connection.prepareStatement(sql);
			ResultSet rs=stmt.executeQuery();
			while(rs.next())
			{
				InvestigationSection isection=new InvestigationSection();
				isection.setId(rs.getInt(1));
				isection.setName(rs.getString(2));
				isection.setJobtitle(rs.getString(3));
				list.add(isection);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public int updateInvestigationSectionlist(InvestigationSection master) {
		int result=0;
		try {
			String sql="update apm_investigation_section set name=?, jobtitle=? where id="+master.getId()+"";
			PreparedStatement stmt=connection.prepareStatement(sql);
			stmt.setString(1, master.getName());
			stmt.setString(2, master.getJobtitle());
			result=stmt.executeUpdate();
			
		} catch (Exception e) {
          e.printStackTrace();
		}
		return result;
		
	}

	
}
