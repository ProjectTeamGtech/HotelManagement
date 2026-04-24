package com.apm.Ambulance.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import com.apm.Ambulance.eu.bi.DutyDAO;
import com.apm.Ambulance.eu.entity.Duty;
import com.apm.DiaryManagement.eu.entity.OpdConsult;
import com.apm.Master.eu.entity.SittingFollowup;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.action.BaseAction;

import ij.plugin.ResultsSorter;

public class JDBCDutyDAO extends BaseAction implements DutyDAO {

	Connection con;

	public JDBCDutyDAO(Connection con) {
		
		this.con = con;
	}

	@Override
	public int addDutyDB(Duty duty) {
		int result=0;
	     String drivername=duty.getFirstname()+" "+duty.getLastname();
		try {
			
			
			String sql="insert into apm_dutyallocation(patientname, city, pickdrop, hospitalname, currentdate,driverid,drivername)values(?,?,?,?,?,?,'"+drivername+"')";
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setString(1, duty.getPatient());
			pst.setString(2, duty.getCity());
			pst.setString(3, duty.getPickdrop());
			pst.setString(4, duty.getHosp());
			pst.setString(5, duty.getCurrentdate());
			pst.setString(6, duty.getDriverid());
			
			result=pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int getTotalDutylist() {
		int result=0;
		try {
			
			String sql="select count(*) from apm_dutyallocation";
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
	public ArrayList<Duty> getDutylist(String searchText, Pagination pagination, String driverid) {
		ArrayList<Duty>list=new ArrayList<Duty>();
		try {
			Statement stmt=con.createStatement();
			String sql="";
			if(searchText!=null){
				sql="select * from apm_dutyallocation where patientname like ('"+searchText+"%')";
			}
			else {
				sql = "select * from apm_dutyallocation where driverid like('"+driverid+"')";
			}
			if(pagination!=null)
			{
				sql=pagination.getSQLQuery(sql);
			}
			
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				Duty duty=new Duty();
				duty.setId(rs.getString(1));
				duty.setPatient(rs.getString(2));
				duty.setCity(rs.getString(3));
				duty.setPickdrop(rs.getString(4));
				duty.setHosp(rs.getString(5));
				duty.setCurrentdate(rs.getString(6));
				list.add(duty);
			}

			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Duty getdutyAllocation(String selectedid) {
		Duty duty=new Duty();
		try {
			String sql="select * from apm_dutyallocation where id='"+selectedid+"'";
			PreparedStatement pst=con.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				duty.setId(rs.getString(1));
				duty.setPatient(rs.getString(2));
				duty.setCity(rs.getString(3));
				duty.setPickdrop(rs.getString(4));
				duty.setHosp(rs.getString(5));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return duty;
	}

	@Override
	public int updateDutyDB(Duty duty) {
		int result=0;
		try {
			
			String sql="update apm_dutyallocation set patientname=?, city=?, pickdrop=?, hospitalname=?";
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setString(1, duty.getPatient());
			pst.setString(2, duty.getCity());
			pst.setString(3, duty.getPickdrop());
			pst.setString(4, duty.getHosp());
			result=pst.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int deleteDuty(String selectedid) {
		PreparedStatement pst=null;
		int result=0;
		try {
			
			String sql="delete from apm_dutyallocation where id='"+selectedid+"'";
			pst=con.prepareStatement(sql);
			result=pst.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ArrayList<Duty> getambulancereportlist(String fromDate, String toDate, String searchText) {
		ArrayList<Duty>list=new ArrayList<Duty>();
		PreparedStatement pst = null;
		try {
		     String sql="";
		     if(searchText!=null){
		    	 sql="select id, patientname, city, pickdrop, hospitalname,drivername from apm_dutyallocation where drivername like ('"+searchText+"%')";
		     }
		     else{
		         sql="select id, patientname, city, pickdrop, hospitalname,drivername from apm_dutyallocation where currentdate between '"+fromDate+"' and '"+toDate+"'";
		     }
		    pst=con.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				Duty duty=new Duty();
				//String drivername=duty.getFirstname()+" "+duty.getLastname();
				duty.setId(rs.getString(1));
				duty.setPatient(rs.getString(2));
				duty.setCity(rs.getString(3));
				duty.setPickdrop(rs.getString(4));
				duty.setHosp(rs.getString(5));
				duty.setDrivername(rs.getString(6));
			
				list.add(duty);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public ArrayList<Duty> getPunchkarmaReport(String fromDate, String toDate, String searchText) {
		ArrayList<Duty>list=new ArrayList<Duty>();
		PreparedStatement pst = null;
		try {
		     String sql="";
		     toDate = toDate + " 23:59:59";
		     if(searchText!=null){
		    	 sql="select id, date_time, note, userid, procedureid,clientid from request_note where drivername like ('"+searchText+"%')";
		     }
		     else{
		         sql="select id, date_time, note, userid, procedureid,clientid from request_note where date_time between '"+fromDate+"' and '"+toDate+"'";
		     }
		    pst=con.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				ArrayList<Duty>punchkarmadata=getpunchkarmadata(rs.getString(1));
				Duty duty=new Duty();
				//String drivername=duty.getFirstname()+" "+duty.getLastname();
				
				String punchkarmaname=procedureid(rs.getString(5));
				String clientname=clientnamebyId(rs.getString(6));
				duty.setId(rs.getString(1));
				duty.setDate_time(rs.getString(2));
				duty.setNote(rs.getString(3));
				duty.setUserid(rs.getString(4));
				duty.setProcedureid(punchkarmaname);
				duty.setClientid(clientname);
				duty.setPunchkarmadata(punchkarmadata);
			
				list.add(duty);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}

	private ArrayList<Duty> getpunchkarmadata(String parentid) {
		ArrayList<Duty>list=new ArrayList<Duty>();
		PreparedStatement pst = null;
		try {
		     
		    
		    String sql="select id,date_time,punch_note from punchkarma_note where parent_id="+parentid+" ";
		     
		    pst=con.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			
			while(rs.next()){
				Duty duty=new Duty();
				//String drivername=duty.getFirstname()+" "+duty.getLastname();
				duty.setId(rs.getString(1));
				duty.setDate_time(rs.getString(2));
				duty.setNote(rs.getString(3));
				
			    list.add(duty);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private String clientnamebyId(String clienid) {
		PreparedStatement pmt=null;
        
		String name="";
         
         try {
			
			   String sql="select fullname from apm_patient where id='"+clienid+"'";
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

	private String procedureid(String procedureid) {
		PreparedStatement pmt=null;
        
		String punchkarmanamename="";
         
         try {
			
			   String sql="SELECT name FROM apm_newchargetype where id="+procedureid+" ;";
			   pmt=con.prepareStatement(sql);
			   ResultSet rs=pmt.executeQuery();
			
			   while(rs.next()){
				punchkarmanamename=rs.getString(1);
			  }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		  return punchkarmanamename ;
		
	}

	@Override
	public int savePunchkarmanote(Duty duty) {
		int result=0;
	    
		try {
			
			
			String sql="insert into punchkarma_note(date_time, parent_id, punch_note)values(?,?,?)";
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setString(1, duty.getDate_time());
			pst.setString(2, duty.getParentid());
			pst.setString(3, duty.getTemplate_text());
			
			
			result=pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

	@Override
	public Duty geteditPunchkarmanote(String selectedid) {
		PreparedStatement pmt=null;
		Duty duty=new Duty();
		
		try {
			
			String sql="select date_time,punch_note from punchkarma_note where id="+selectedid+"";
			pmt=con.prepareStatement(sql);
			
			ResultSet rs=pmt.executeQuery();
			
			while(rs.next()){
				
				duty.setDate_time(rs.getString(1));
				duty.setTemplate_text(rs.getString(2));
				duty.setId(selectedid);
				
				}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return duty;
		
	}

	@Override
	public int updatePunchkarmaNote(Duty duty) {
		PreparedStatement pmt=null;
		String sql="update punchkarma_note set date_time=?,punch_note=? where id=?";
	
		int result=0;
		
		try {
			
			pmt=con.prepareStatement(sql);
			
			pmt.setString(1, duty.getDate_time());
			pmt.setString(2, duty.getTemplate_text());
			pmt.setString(3, duty.getId());
			
			result=pmt.executeUpdate();
			
	     } catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	
	}
	public ArrayList<Duty> getPunchkarmaList(String fromDate, String toDate, int ipdid,String opdid) {
		ArrayList<Duty>list=new ArrayList<Duty>();
		PreparedStatement pst = null;
		String sql="";
		try {
			 if(ipdid!=0) {
			     sql="select id, date_time, note, userid, procedureid,clientid from request_note where date_time between '"+fromDate+"' and '"+toDate+" 23:59:59' and admissionid="+ipdid+"";

			 }else {
			     sql="select id, date_time, note, userid, procedureid,clientid from request_note where date_time between '"+fromDate+"' and '"+toDate+" 23:59:59' and opdid="+opdid+"";
 
			 }
		    pst=con.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				ArrayList<Duty>punchkarmadata=getpunchkarmadata(rs.getString(1));
				Duty duty=new Duty();
				//String drivername=duty.getFirstname()+" "+duty.getLastname();
				
				String punchkarmaname=procedureid(rs.getString(5));
				String clientname=clientnamebyId(rs.getString(6));
				duty.setId(rs.getString(1));
				duty.setDate_time(rs.getString(2));
				duty.setNote(rs.getString(3));
				duty.setUserid(rs.getString(4));
				duty.setProcedureid(punchkarmaname);
				duty.setClientid(clientname);
				duty.setPunchkarmadata(punchkarmadata);
			
				list.add(duty);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}
	
	@Override
	public int deletepunchkarmaData(String selectedid) {
		PreparedStatement pmt=null;
		int result=0;
		
		try {
			
			String sql="delete from punchkarma_note where id="+selectedid+"";
			
			pmt=con.prepareStatement(sql);
			result=pmt.executeUpdate();
			
			} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		  }
		
		return result;
	}

	@Override
	public Vector<Duty> getBamsPunchkarmaData(OpdConsult opdConsult2, String dbname) {
		Vector<Duty>list=new Vector<Duty>();
		PreparedStatement pst = null;
		String sql="";
		try {
			 if(!opdConsult2.getFlag_opd().equals("0")) {
			     sql="select id, date_time, note, userid, procedureid,clientid from "+dbname+".request_note where admissionid="+opdConsult2.getOldipdid()+"";

			 }else {
			     sql="select id, date_time, note, userid, procedureid,clientid from "+dbname+".request_note where opdid="+opdConsult2.getAppointmentid()+"";
 
			 }
		    pst=con.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				//ArrayList<Duty>punchkarmadata=getpunchkarmadata(rs.getString(1));
				Duty duty=new Duty();
				//String drivername=duty.getFirstname()+" "+duty.getLastname();
				
				//String punchkarmaname=procedureid(rs.getString(5));
				//String clientname=clientnamebyId(rs.getString(6));
				duty.setId(rs.getString(1));
				duty.setDate_time(rs.getString(2));
				duty.setNote(rs.getString(3));
				duty.setUserid(rs.getString(4));
				duty.setProcedureid(rs.getString(5));
				duty.setClientid(rs.getString(6));
				//duty.setPunchkarmadata(punchkarmadata);
			
				list.add(duty);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}

	@Override
	public int insertBamsPunchkarmaData(Duty duty) {
		int res=0;
		
		try {
			
			String sql="insert into request_note(date_time,note,userid,admissionid,isseen,ispunchkarma,procedureid,opdid,clientid)values(?,?,?,?,?,?,?,?,?) ";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, duty.getDatetime());
			preparedStatement.setString(2, duty.getNote());
			preparedStatement.setString(3, duty.getUserid());
			preparedStatement.setString(4, duty.getNewipdid());
			preparedStatement.setString(5, "1");
			preparedStatement.setString(6, duty.getPunchkarma());
			preparedStatement.setString(7, duty.getProcedureid());
			preparedStatement.setString(8, duty.getNewopdid());
			preparedStatement.setString(9, duty.getClientid());
			res = preparedStatement.executeUpdate();
			
			if(res == 1){
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if(resultSet.next()){
					res = resultSet.getInt(1);  
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return res;
		
	}

	@Override
	public Vector<Duty> getChildPunchkarmadata(String parentid,String dbname) {
		Vector<Duty>list=new Vector<Duty>();
		PreparedStatement pst = null;
		try {
		     
		    
		    String sql="select id,date_time,punch_note from "+dbname+".punchkarma_note where parent_id="+parentid+" ";
		     
		    pst=con.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			
			while(rs.next()){
				Duty duty=new Duty();
				//String drivername=duty.getFirstname()+" "+duty.getLastname();
				duty.setId(rs.getString(1));
				duty.setDate_time(rs.getString(2));
				duty.setNote(rs.getString(3));
				
			    list.add(duty);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}

	@Override
	public int insertChildPunchkarmaData(Duty duty2) {
      int result=0;
	    
		try {
			
			
			String sql="insert into punchkarma_note(date_time, parent_id, punch_note)values(?,?,?)";
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setString(1, duty2.getDatetime());
			pst.setString(2, duty2.getParentid());
			pst.setString(3, duty2.getNote());
			
			
			result=pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

	
}
