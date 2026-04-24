package com.apm.Dietary.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.apm.Diagnosis.eu.bi.DiagnosisDAO;
import com.apm.Diagnosis.eu.blogic.jdbc.JDBCDiagnosisDAO;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.Dietary.eu.bi.DietaryDAO;
import com.apm.Dietary.eu.entity.Dietary;
import com.apm.Dietary.eu.entity.DietaryDetails;
import com.apm.Ipd.eu.bi.IpdDAO;
import com.apm.Ipd.eu.blogic.jdbc.JDBCIpdDAO;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.Master.eu.entity.Master;
import com.apm.common.web.action.BaseAction;

public class JDBCDietaryDAO extends BaseAction implements DietaryDAO {
	Connection connection;
	public JDBCDietaryDAO(Connection connection) {
		this.connection = connection;
	}
	
	public ArrayList<Dietary> viewDietaryCategory() {
		ArrayList<Dietary> users = new ArrayList<Dietary>();
		try {
			Statement stmt = connection.createStatement();
			String sql = "select id,name,description from apm_diet_category";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Dietary dietary = new Dietary();
				dietary.setId(rs.getInt(1));
				dietary.setName(rs.getString(2));
				dietary.setDescription(rs.getString(3));
				users.add(dietary);
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;

	}

	public int addDietaryCategory(Dietary dietary) {
		int result = 0;
		try {
			//Statement stmt = connection.createStatement();
			String query = "insert into apm_diet_category(name,description) values(?,?)";
			PreparedStatement stmt = connection.prepareStatement(query); 
			//stmt.setInt(1, dietary.getId());
			stmt.setString(1, dietary.getName());
			stmt.setString(2, dietary.getDescription());
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Dietary getinfoCategory(int id) {
		Dietary dietary =null;
		try {
			String query= "select name,description from apm_diet_category where id="+id;
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				dietary = new Dietary();
				dietary.setName(rs.getString(1));
				dietary.setDescription(rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dietary;
	}

	public int updateDietaryCategory(Dietary dietary) {
		int result = 0;
		try {
			//Statement stmt = connection.createStatement();
			String query = "update apm_diet_category set name=?, description=? where id="+dietary.getId();
			PreparedStatement stmt = connection.prepareStatement(query); 
			//stmt.setInt(1, dietary.getId());
			stmt.setString(1, dietary.getName());
			stmt.setString(2, dietary.getDescription());
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deleteInfoCategory(Dietary dietary) {
		int result = 0;
		try {
			
			int j =dietary.getId();
			String query = "delete from apm_diet_category where id="+dietary.getId();
			PreparedStatement stmt = connection.prepareStatement(query);
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Dietary> getctaegory() {
		ArrayList<Dietary> users = new ArrayList<Dietary>();
		try {
			Statement stmt = connection.createStatement();
			String sql = "select id,name from apm_diet_category";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Dietary dietary = new Dietary();
				dietary.setId(Integer.parseInt(rs.getString(1)));
				dietary.setName(rs.getString(2));
				users.add(dietary);
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
}
	
	public ArrayList<Bed> getAllIpdDetailsForDiet(String allward) {
		  ArrayList<Bed> users = new ArrayList<Bed>();
		  try {
			  
			  //lokesh
		      String childid="";
		      DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
		    Calendar cal2 = Calendar.getInstance();
		    cal2.add(Calendar.DATE, -1); 
		    String yesterday = dateFormat2.format(cal2.getTime());
		    
		     DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		     Calendar cal1 = Calendar.getInstance();
		    
		     String  today = dateFormat1.format(cal1.getTime());
		    
			  
		   ClientDAO clientDAO=new JDBCClientDAO(connection);
		   IpdDAO ipdDAO= new JDBCIpdDAO(connection);
		   DiagnosisDAO diagnosisDAO= new JDBCDiagnosisDAO(connection);
		   Statement stmt = connection.createStatement();
		   StringBuffer buffer = new StringBuffer();
		  buffer.append("select id,wardid,bedid,clientid,conditionid,admissiondsate from ipd_addmission_form where bedid!=0 ") ;
 if(!allward.equals("0")){
			   
			   buffer.append("and wardid in (0 ");
			   
			   for(String str: allward.split(",")){
				   int t=Integer.parseInt(str);
				   if(t==0){
					   continue;
				   }else {
					   buffer.append(","+t+" ");
				   }
				   
				   
			   }
			   
			  buffer.append(") ");
			  
		   }
 buffer.append("order by (wardid+0) asc,(bedid+0) asc");
		   ResultSet rs = stmt.executeQuery(buffer.toString());
		   while (rs.next()) {
		    
		    Bed bed=new Bed();
		    bed.setId(rs.getInt(1));
		    bed.setWardid(rs.getString(2));
		    bed.setBedid(rs.getString(3));
		    bed.setConditionid(rs.getString(5));
		    bed.setClientid(rs.getString(4));
		    ArrayList<Dietary> catlist= new ArrayList<Dietary>();
		      childid=checkForTodaysPlan(today, bed.getClientid());
		      if(childid.equals("")){
		       childid=checkForTodaysPlan(yesterday, bed.getClientid());
		      }
		      if(!childid.equals("")){
		      
		       
		       catlist= getCatList(today, bed.getClientid());
		       if(catlist.size()==0){
		        catlist= getCatList(yesterday, bed.getClientid());
		       }
		      
		      }
		      ArrayList<Dietary> list2= new ArrayList<Dietary>();//0
		      ArrayList<Dietary> list3= new ArrayList<Dietary>();
		      ArrayList<Dietary> list1= new ArrayList<Dietary>();//1
		     for(Dietary d: catlist){
		    	 
		    	 if(d.getStatus()==1){
		    		 bed.setStatus("1");
		    		 break;
		    	 }else{
		    		 bed.setStatus("0");
		    	 }
		     }
 for(Dietary d: catlist){
		    	 
		    	 if(d.getStatus()==1){
		    		 list1.add(d);
		    		 
		    		continue;
		    	 }else{
		    		
		    		 list2.add(d);
		    	 }
		     }
 
 list1.addAll(list2);
 bed.setCatlist(list1);
		    String remarknincharge="";
		    //childid is actually a parentid
		    Bed bed1= new Bed();
		  bed1= getremarkAndincharge(childid,  bed.getClientid());
		
		   
		    bed.setRemark(bed1.getRemark());
		    bed.setDietician_incharge(bed1.getDietician_incharge());
		    String admissiondsate = rs.getString(6);
		    String date = admissiondsate.split(" ")[0];
		    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance(); 
			String curr_date = dateFormat.format(cal.getTime());
			if(date.equals(curr_date)){
				bed.setTodayadmit(1);
			}
			
		    String wardname= ipdDAO.getIpdWardName(rs.getString(2));
		    String bedname= ipdDAO.getIpdBedName(rs.getString(3));
		    Client client= clientDAO.getClientDetails(rs.getString(4));
		    String clientname= client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
		    String condition= diagnosisDAO.getNameOfDiagnosisFromId(rs.getString(5));
		    
		    ArrayList<Dietary> categoryList= getctaegory(); 
		    bed.setCategoryList(categoryList);
		    if( list1.size()==0){
		    	bed.setCatlist(categoryList);
		    }
		    bed.setWardname(wardname);
		    bed.setBedname(bedname);
		    bed.setClientname(clientname);
		    bed.setConditionname(condition);
		    bed.setIpdid(rs.getString(1));
		    
		    users.add(bed);
		   }
		   stmt.close();
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
		  return users;
		}
		 public int saveGeneralDietPlan(Bed bed, String diaetplan, String remark,String date,String diettimeshift,int result) {
		  int result1 = 0;
		  try {
		   String query = "insert into general_diet(ipdid, clientid, dietplan, remark, date, diaettime, wardid, bedid, conditionid,parentid) values(?,?,?,?,?,?,?,?,?,?)";
		   PreparedStatement stmt = connection.prepareStatement(query); 
		  
		   
		   stmt.setString(1, bed.getIpdid());
		   stmt.setString(2, bed.getClientid());
		   stmt.setString(3, diaetplan);
		   stmt.setString(4, remark);
		   stmt.setString(5, date);
		   stmt.setString(6, diettimeshift);
		   stmt.setString(7, bed.getWardid());
		   stmt.setString(8, bed.getBedid());
		   stmt.setString(9, bed.getConditionid());
		   stmt.setInt(10, result);
		  /* stmt.setString(11, dietician_incharge);*/
		   result = stmt.executeUpdate();
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
		  return result1;
		  
		 }
		 
		 public ArrayList<Bed> printgeneraldietplan(String id,String date, String filter_status,String allward) {
		if(date.contains("/")){
			 String date1[]=date.split("/");
			 date= date1[2]+"-"+date1[1]+"-"+date1[0];
		}
		 int fulldiet=0,softdiet=0,liquiddiet=0,diabetic=0,rtfeed=0,renal=0,Hepatic=0,clearliquid=0,semisolid=0,blended=0,nbm=0;
			 ArrayList<Bed> list = new ArrayList<Bed>();
		  try {
			 
			  String todate = date + " 23:59:59";
			  ClientDAO clientDAO=new JDBCClientDAO(connection);
			   IpdDAO ipdDAO= new JDBCIpdDAO(connection);
			   DietaryDAO dietaryDAO = new JDBCDietaryDAO(connection);
			   DiagnosisDAO diagnosisDAO= new JDBCDiagnosisDAO(connection);
		 
		   StringBuffer buffer = new StringBuffer();
		   buffer.append("select id, ipdid, clientid, dietplan, remark, date, diaettime, wardid, bedid, conditionid,parentid from general_diet");
		   buffer.append(" where date between '"+date+"' and '"+todate+"' ");
		   buffer.append("and parentid ='"+id+"' ");
		   /*buffer.append("order by (wardid+0) asc,(bedid+0) asc");*/
		   if(!allward.equals("0")){
			   
			   buffer.append("and wardid in (0 ");
			   
			   for(String str: allward.split(",")){
				   int t=Integer.parseInt(str);
				   if(t==0){
					   continue;
				   }else {
					   buffer.append(","+t+" ");
				   }
				   
				   
			   }
			   
			  buffer.append(") ");
		   }
		   
		   if(!filter_status.equals("0")){
		    if(filter_status.equals("1")){
		     buffer.append("and diaettime=1 ");
		    }else if(filter_status.equals("2")){
		     buffer.append("and diaettime=2 "); 
		    }else if(filter_status.equals("3")){
		     buffer.append("and diaettime=3 ");
		    }
		   }
		   buffer.append("order by (wardid+0) asc,(bedid+0) asc");
		   PreparedStatement stmt = connection.prepareStatement(buffer.toString());
		   ResultSet rs = stmt.executeQuery();
		   while (rs.next()) {
		  Bed bed = new Bed();
		  ArrayList<Bed>list2 = new ArrayList<Bed>();
		  ArrayList<Dietary> categoryList= getctaegory(); 
		    bed.setCategoryList(categoryList);
		    bed.setId(rs.getInt(1));
		    bed.setIpdid(rs.getString(2));
		   /* bed.setDietician_incharge(rs.getString(11));*/
		   /* bed.setClientid(rs.getString(3));*/
		    /*bed.setDiaetplan(rs.getString(4));*/
		    bed.setRemark(rs.getString(5));
		    bed.setDate(rs.getString(6));
		    bed.setDiettimeshift(rs.getString(7));
		    String wardname= ipdDAO.getIpdWardName(rs.getString(8));
		    String bedname= ipdDAO.getIpdBedName(rs.getString(9));
		    Client client= clientDAO.getClientDetails(rs.getString(3));
		    String clientname= client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
		    String condition= diagnosisDAO.getNameOfDiagnosisFromId(rs.getString(10)); 
	         String diaetplan = dietaryDAO.getCategoryName(rs.getString(4));
	         bed.setParentid(rs.getString(11));
		    bed.setWardname(wardname);
		    bed.setBedname(bedname);
		    bed.setClientname(clientname);
		    bed.setConditionname(condition);
		    bed.setDiaetplan(diaetplan);
		    String dietid=rs.getString(4);
		    ArrayList<Bed>arrayList = new ArrayList<Bed>();
		    for(String diet :dietid.split(",") ){
		    	
		    	if(diet.equals("0")){
		    		continue;
		    	}
		    	if(diet.equals("9")){
		    		 
		    		nbm=++nbm;
		    	}else if (diet.equals("1")){
		    		fulldiet=++fulldiet;
		    	}else if (diet.equals("2")){
		    		softdiet=1+softdiet;
		    	}else if (diet.equals("3")){
		    		liquiddiet=++liquiddiet;
		    	}else if (diet.equals("4")){
		    		diabetic=++diabetic;
		    	}else if (diet.equals("5")){
		    		rtfeed=++rtfeed;
		    	}else if (diet.equals("6")){
		    		renal=++renal;
		    	}else if (diet.equals("7")){
		    		Hepatic=++Hepatic;
		    	}else if (diet.equals("8")){
		    		clearliquid=++clearliquid;
		    	}else if (diet.equals("10")){
		    		semisolid=++semisolid;
		    	}else if (diet.equals("11")){
		    		blended=++blended;
		    	}
		    	
		    	
		    	 String dietplan = dietaryDAO.getCategoryName(diet);
		    	 Bed bed2 = new Bed();
		    	 bed2.setDietplan(dietplan);
		    	 arrayList.add(bed2);
		    }
		      bed.setList2(arrayList);
		      bed.setNbm(nbm);
		      bed.setFulldiet(fulldiet);
		      bed.setSoftdiet(softdiet);
		      bed.setLiquiddiet(liquiddiet);
		      bed.setDiabetic(diabetic);
		      bed.setRtfeed(rtfeed);
		      bed.setRenal(renal);
		      bed.setHepatic(Hepatic);
		      bed.setClearliquid(clearliquid);
		      bed.setSemisolid(semisolid);
		      bed.setBlended(blended);
		      list.add(bed);
		      
		      
		   }
		   stmt.close();
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
		  return list;
		  
		 }
	public String getCategoryName(String id){
		
			String diet ="";
			try {
				String query= "select name from apm_diet_category where id='"+id+"' ";
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet rs = preparedStatement.executeQuery();
				if(rs.next()){
					
					diet = rs.getString(1);
				
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return diet;
		
		
	}
	public String getDieticianName(String id){
		String dietician="";
		try {
			String query="select dietician_incharge from general_diet_parent where id="+id+"";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				
				dietician = rs.getString(1);
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	     return dietician;
	}
	public int saveGeneralDietPlanparent(String date, String userid, String dietshift,String dietician_incharge) {
		 int result = 0;
		try {
			String query ="insert into general_diet_parent (datetime, userid, dietshift,dietician_incharge) values(?,?,?,?) ";
			 PreparedStatement ps = connection.prepareStatement(query); 
			 ps.setString(1, date);
			 ps.setString(2, userid);
			 ps.setString(3, dietshift);
			 ps.setString(4, dietician_incharge);
			 result = ps.executeUpdate();
				if(result>0)
				{
					ResultSet resultSet=ps.getGeneratedKeys();
					
					while(resultSet.next()){
					     result= resultSet.getInt(1);	
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public ArrayList<Bed> showgeneraldietplanlist(String fromdate, String todate) {
		  ArrayList<Bed> list = new ArrayList<Bed>();
		  try {
				StringBuffer buffer = new StringBuffer();
				todate = todate + " " +"59:59:59";
				
				buffer.append("select id, datetime, userid, dietshift,dietician_incharge from general_diet_parent ");
				 buffer.append(" where datetime between '"+fromdate+"' and '"+todate+"' ");
			/*	if(flag){
					buffer.append("and  general_diet_parent.datetime between '"+fromdate+"' and '"+todate+"'  ");
				}
				else {
					buffer.append("where general_diet_parent.datetime between '"+fromdate+"' and '"+todate+"'  ");
				}
				*/
				
				String query = buffer.toString();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet rs = preparedStatement.executeQuery();
				
				while(rs.next()){
					
			Bed bed = new Bed();
					bed.setId(rs.getInt(1));
					//dietaryDetails.setParentid(Integer.parseInt(rs.getString(6)));
					
					
					String datetime = rs.getString(2);
					String[] date = datetime.split(" ");
					String date1 = date[0];
					
					String[] temp = date1.split("-");
					String newdate = temp[2]+"/"+ temp[1]+"/"+temp[0];
					
					bed.setDatetime(newdate);
					bed.setUserid(rs.getString(3));
				bed.setDiettimeshift(rs.getString(4));
				bed.setDietician_incharge(rs.getString(5));
			list.add(bed);
				}
	} catch (Exception e) {
		e.printStackTrace();
	}
				return list;
}
	public String getDiettimeName(String id) {
		String diettimeshift ="";
		try {
			String query= "select dietshift from general_diet_parent where id="+id;
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				
				diettimeshift = rs.getString(1);
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return diettimeshift;
	}
	public ArrayList<Bed> getEditIpdDetailsForDiet(String id){
		ArrayList<Bed> list = new ArrayList<Bed>();
		
		try {
			
			 
			/*String sql="select ipdid, clientid, dietplan, remark, date, diaettime, wardid, bedid, conditionid, parentid,id from general_diet where parentid='"+id+"' ";*/
			String sql ="select ipdid, clientid, dietplan, remark, date, diaettime, wardid, bedid, conditionid, parentid,id from general_diet where parentid='"+id+"' and bedid!=0 order by (wardid+0) asc,(bedid+0) asc";
			PreparedStatement ps=connection.prepareStatement(sql);
			 ClientDAO clientDAO=new JDBCClientDAO(connection);
			   IpdDAO ipdDAO= new JDBCIpdDAO(connection);
			   DiagnosisDAO diagnosisDAO= new JDBCDiagnosisDAO(connection);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Bed bed = new Bed();
			   bed.setIpdid(rs.getString(1));
			  /*  bed.setClientid(rs.getString(2));*/
			    bed.setDiaetplan(rs.getString(3));
			    bed.setRemark(rs.getString(4));
			    bed.setDate(rs.getString(5));
			    bed.setDiaettime(rs.getString(6));
			  /*  bed.setWardid(rs.getString(7));
			    bed.setBedid(rs.getString(8));*/
			    
			    
			  
			    String wardname= ipdDAO.getIpdWardName(rs.getString(7));
			    String bedname= ipdDAO.getIpdBedName(rs.getString(8));
			    Client client= clientDAO.getClientDetails(rs.getString(2));
			    String clientname= client.getTitle()+" "+client.getFirstName()+" "+client.getLastName();
			    String condition= diagnosisDAO.getNameOfDiagnosisFromId(rs.getString(9));
			    
			   ArrayList<Dietary> categoryList= getdietCategory(rs.getInt(3));
			   bed.setCategoryList(categoryList);
			    bed.setWardname(wardname);
			    bed.setBedname(bedname);
			    bed.setClientname(clientname);
			    bed.setConditionname(condition);
			    bed.setParentid(rs.getString(10));
			    bed.setId(rs.getInt(11));
			    ArrayList<Dietary>  arrayList = new ArrayList<Dietary>();
			   /* ArrayList<Dietary>catlist = new ArrayList<Dietary>();*/
			    ArrayList<Dietary> catlist= getdietCategory(rs.getInt(3));
			    
			   String dietid = rs.getString(3);
			   
			   for(Dietary dietary:catlist){
			    	if(dietid.equals("0")){
			    		dietary.setStatus(0);
						
					}else{
						for (String catname : dietid.split(",")) {
							if(catname.equals("0")){
								continue;
							}
							
							int temp = dietary.getId();
							if(catname.equals(""+dietary.getId())){
								dietary.setStatus(1);
								break;
							}
						}
			    }
			    	
			    
			   /* for(Dietary dietary2:catlist){
			    	if(dietid.equals("0")){
			    		dietary2.setStatus(0);
						
					}else{
						for (String catname : dietid.split(",")) {
							if(catname.equals("0")){
								continue;
							}
							
							if(catname.equals(""+dietary.getId())){
								dietary2.setStatus(1);
								break;
							}
						}
			    }*/
			    
			    	arrayList.add(dietary);
			    	
			  
			    
			   /* bed.setIpdid(rs.getString(1));*/
			}
			   ArrayList<Dietary> l1= new ArrayList<Dietary>();
			   ArrayList<Dietary> l2= new ArrayList<Dietary>();
			   for(Dietary d: arrayList){
				   if(d.getStatus()==1){
					   l1.add(d);
				   }else{
					  l2.add(d); 
				   }
			   }
			   l1.addAll(l2);
			   for(Dietary  d: l1){
				   if(d.getStatus()==1){
					   bed.setStatus("1");
					   break;
				   }else{
					   bed.setStatus("0");
				   }
			   }
			bed.setCatlist(l1);   
			  list.add(bed); 
			}
			}catch (Exception e) {

		   e.printStackTrace();
		}
		return list;
		
	}
	public ArrayList<Dietary> getdietCategory(int id) {
		ArrayList<Dietary> users = new ArrayList<Dietary>();
		try {
			Statement stmt = connection.createStatement();
			String sql = "select id,name from apm_diet_category";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Dietary dietary = new Dietary();
			
				dietary.setId(Integer.parseInt(rs.getString(1)));
	if(id==dietary.getId()){
					dietary.setSelect(1);
				}
				dietary.setName(rs.getString(2));
				users.add(dietary);
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
}
	public int updateGeneralDietPlan(String id,Bed bed){
		int result=0;
		try {
			 
			String query="update general_diet set  dietplan=?, remark=?, diaettime=? where id="+bed.getId()+" ";
			PreparedStatement ps = connection.prepareStatement(query);
			
			
			ps.setString(1, bed.getDiaetplan());
			ps.setString(2, bed.getRemark());
			   
			   ps.setString(3, bed.getDiaettime());
			  
			  
			   result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	/*public int updateGeneralDietPlanParent(String id){
		int result=0;
		try {
			String query= "update general_diet set datetime=?, userid=?, dietshift=? where id="+id+" ";
			PreparedStatement ps = connection.prepareStatement(query);
			Bed bed = new Bed();
			ps.setString(1, bed.getDatetime());
			ps.setString(2, bed.getUserid());
			ps.setString(3, bed.getDiettimeshift());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}*/

	public String getPreviousDietTimeShift(String fromdate, String todate,String dietshift) {
		String result="0";
		try {
			boolean flag = false;
		
			 todate = todate + " " +"59:59:59";
			/*String query= "select id from general_diet_parent where datetime between '"+fromdate+"' and '"+todate+"'";*/
			 StringBuffer buffer = new StringBuffer();
				todate = todate + " " +"59:59:59";
				
				buffer.append("select id from general_diet_parent ");
				 buffer.append(" where datetime between '"+fromdate+"' and '"+todate+"' ");
				 buffer.append("and dietshift='"+dietshift +"' ");
				
			PreparedStatement ps = connection.prepareStatement(buffer.toString());
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				
				  result ="1";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//lokesh
	private String checkForTodaysPlan(String date, String clientid){
		String result="";
		PreparedStatement ps= null;
		try{
		 String sql="select parentid from general_diet where date between '"+date+"' and  '"+date+" 23:59:59' and clientid='"+clientid+"' order by diaettime desc limit 1  ";
		 ps= connection.prepareStatement(sql);
		 ResultSet rs = ps.executeQuery();
		 while(rs.next()){
		  result=rs.getString(1);
		 }
		}catch(Exception e){
		 e.printStackTrace();
		}
		 
		 return result;
		} 



		private ArrayList<Dietary> getCatList(String date,String clientid){

		 ArrayList<Dietary>  arrayList = new ArrayList<Dietary>();
		 String result="";
		 PreparedStatement ps= null;
		 try{
		  String sql="select dietplan from general_diet where date between '"+date+"' and  '"+date+" 23:59:59' and clientid='"+clientid+"' order by diaettime desc limit 1  ";
		  ps= connection.prepareStatement(sql);
		  ResultSet rs = ps.executeQuery();
		  while(rs.next()){
		   
		   
		      /* ArrayList<Dietary>catlist = new ArrayList<Dietary>();*/
		    ArrayList<Dietary> catlist= getdietCategory(rs.getInt(1));
		    String dietid = rs.getString(1);
		      
		      for(Dietary dietary:catlist){
		        if(dietid.equals("0")){
		         dietary.setStatus(0);
		      
		     }else{
		      for (String catname : dietid.split(",")) {
		       if(catname.equals("0")){
		        continue;
		       }
		       
		       int temp = dietary.getId();
		       if(catname.equals(""+dietary.getId())){
		        dietary.setStatus(1);
		        break;
		       }
		      }
		       }
		        arrayList.add(dietary);
		      }
		      }
		 }catch(Exception e){
		  e.printStackTrace();
		 }
		 return arrayList;
		}
		
	private Bed getremarkAndincharge(String parentid, String clientid){
		String result="";
		Bed bed = new Bed();
		PreparedStatement ps= null;
		try{
			StringBuffer bufer= new StringBuffer();
			bufer.append(" select b.dietician_incharge, a.remark from  general_diet a ");
			bufer.append(" inner join general_diet_parent b on b.id= a.parentid ");
			bufer.append(" where a.parentid= '"+parentid+"' and a.clientid='"+clientid+"' ");
			String sql="";
			sql= bufer.toString();
			ps= connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			String remark="",incharge="";
			while(rs.next()){
				 incharge=rs.getString(1);
				 remark =rs.getString(2);
			}
			if(remark==null){
				remark="";
			}
			if(incharge==null){
				incharge="";
			}
			bed.setRemark(remark);
			bed.setDietician_incharge(incharge);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	
		return bed ;
	}
}