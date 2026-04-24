package com.apm.Dietary.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.Dietary.eu.bi.DietaryDetailsDAO;
import com.apm.Dietary.eu.entity.DietaryDetails;
import com.apm.Ipd.eu.bi.BedDao;
import com.apm.Ipd.eu.bi.IpdDAO;
import com.apm.Ipd.eu.blogic.jdbc.JDBCBedDao;
import com.apm.Ipd.eu.blogic.jdbc.JDBCIpdDAO;
import com.apm.Ipd.eu.entity.Bed;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.web.action.BaseAction;

public class JDBCDietaryDetailsDAO extends BaseAction implements
		DietaryDetailsDAO {
	private static final long serialVersionUID = 1L;
	Connection connection;

	public JDBCDietaryDetailsDAO(Connection connection) {
		this.connection = connection;
	}

	public ArrayList<DietaryDetails> viewDietaryDetails() {
		ArrayList<DietaryDetails> users = new ArrayList<DietaryDetails>();
		try {
			Statement stmt = connection.createStatement();
			String sql = "select id,category_id, name, energy, protein from apm_diet_details";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				DietaryDetails dietary = new DietaryDetails();
				dietary.setId(rs.getInt(1));
				dietary.setCategoryid(rs.getString(2));
				dietary.setName(rs.getString(3));
				dietary.setEnergy(rs.getString(4));
				dietary.setProtein(rs.getString(5));	
				users.add(dietary);
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;

	}

	public int addDietarydetails(DietaryDetails dietary) {
		int result = 0;
		try {
			String query = "insert into apm_diet_details(category_id, name, energy, protein) values(?,?,?,?)";
			PreparedStatement stmt = connection.prepareStatement(query); 
			stmt.setString(1, dietary.getCategoryid());
			stmt.setString(2, dietary.getName());
			stmt.setString(3, dietary.getEnergy());
			stmt.setString(4, dietary.getProtein());
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public DietaryDetails getinfoDetails(int id) {
		DietaryDetails dietaryDetails =null;
		try {
			String query= "select category_id, name, energy, protein,potassium,sodium from apm_diet_details where id="+id;
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				dietaryDetails = new DietaryDetails();
				dietaryDetails.setCategoryid((rs.getString(1)));
				dietaryDetails.setName((rs.getString(2)));
				dietaryDetails.setEnergy(rs.getString(3));
				dietaryDetails.setProtein(rs.getString(4));
				dietaryDetails.setPotassium(DateTimeUtils.convertToDouble(rs.getString(5)));
				dietaryDetails.setSodium(DateTimeUtils.convertToDouble(rs.getString(6)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dietaryDetails;
	}

	public int updateDietary(DietaryDetails dietaryDetails) {
		int result = 0;
		try {
			String query = "update apm_diet_details set category_id=?, name=?, energy=?, protein=?,sodium=?,potassium=? where id="+dietaryDetails.getId();
			PreparedStatement stmt = connection.prepareStatement(query); 
			stmt.setString(1, dietaryDetails.getCategoryid());
			stmt.setString(2, dietaryDetails.getName());
			stmt.setString(3, dietaryDetails.getEnergy());
			stmt.setString(4, dietaryDetails.getProtein());
			stmt.setString(5, ""+dietaryDetails.getSodium());
			stmt.setString(6, ""+dietaryDetails.getPotassium());
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int deleteInfo(DietaryDetails dietaryDetails) {
		int result = 0;
		try {
			
			int j =dietaryDetails.getId();
			String query = "delete from apm_diet_details where id="+dietaryDetails.getId();
			PreparedStatement stmt = connection.prepareStatement(query);
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<DietaryDetails> getctaegorydetails() {
		ArrayList<DietaryDetails> users = new ArrayList<DietaryDetails>();
		try {
			Statement stmt = connection.createStatement();
			String sql = "select id,name from apm_diet_details";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				DietaryDetails dietaryDetails = new DietaryDetails();
				dietaryDetails.setId(rs.getInt(1));
				dietaryDetails.setName(rs.getString(2));
				users.add(dietaryDetails);
				}
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
		}
		return users;
	}
	
	public ArrayList<DietaryDetails> getcaloriesdetails() {
		ArrayList<DietaryDetails> users = new ArrayList<DietaryDetails>();
		try {
			Statement stmt = connection.createStatement();
			String sql = "select id,energy from apm_diet_details";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				DietaryDetails dietaryDetails = new DietaryDetails();
				dietaryDetails.setId(rs.getInt(1));
				dietaryDetails.setName(rs.getString(2));
				users.add(dietaryDetails);
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}
	
	

	public ArrayList<DietaryDetails> getcategorydetailslist(String id) {
		ArrayList<DietaryDetails> users = new ArrayList<DietaryDetails>();
		//DietaryDetails dietaryDetails = null;
		try {
			Statement stmt = connection.createStatement();
			String query = "select id,name from apm_diet_details where category_id="+id+"";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				DietaryDetails dietaryDetails = new DietaryDetails();
				dietaryDetails.setId(rs.getInt(1));
				dietaryDetails.setName(rs.getString(2));
				users.add(dietaryDetails);
			}
		
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	public ArrayList<DietaryDetails> getSelCalories(String id) {
		ArrayList<DietaryDetails> users = new ArrayList<DietaryDetails>();
		try {
			Statement stmt = connection.createStatement();
			int id_c= Integer.parseInt(id);
			String query = "select id,energy,protein,potassium,sodium,dqty from apm_diet_details where id="+id_c+"";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				DietaryDetails dietaryDetails = new DietaryDetails();
				dietaryDetails.setId(rs.getInt(1));
				dietaryDetails.setEnergy(rs.getString(2));
				dietaryDetails.setProtein(rs.getString(3));
				dietaryDetails.setPotassium(DateTimeUtils.convertToDouble(rs.getString(4)));
				dietaryDetails.setSodium(DateTimeUtils.convertToDouble(rs.getString(5)));
				dietaryDetails.setDqty(Integer.parseInt(rs.getString(6)));
				users.add(dietaryDetails);
				}
				stmt.close();
			}catch (Exception e) {
			e.printStackTrace();
			}
		return users;	
	}

	public DietaryDetails getcategoryname(String id) {
		DietaryDetails dietaryDetails = new DietaryDetails();
		try {
			Statement stmt = connection.createStatement();
			int id_c = Integer.parseInt(id);
			String query = "select name from apm_diet_category where id="+id_c+"";
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()){
				//dietaryDetails = new DietaryDetails();
				dietaryDetails.setName(rs.getString(1));	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dietaryDetails;
	}

	public DietaryDetails getSubcategoryname(String id) {
		DietaryDetails details = new DietaryDetails();
		try {
			Statement stmt = connection.createStatement();
			int id_c = Integer.parseInt(id);
			String query = "select name from apm_diet_details where id="+id_c+"";
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()){
				details = new DietaryDetails();
				details.setName(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return details;
	}

	public DietaryDetails getEnergyname(String calories_id) {
		DietaryDetails details = new DietaryDetails();
		try {
			Statement stmt = connection.createStatement();
			int id_c = Integer.parseInt(calories_id);
			String query = "select energy,protein from apm_diet_details where id="+id_c+"";
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()){
				details = new DietaryDetails();
				details.setEnergy(rs.getString(1));
				details.setProtein(rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return details;
	}

	public int storedietaryparent(String admissionid,String date,String time) {
		int result =0;
		try {
			
			BedDao bedDao = new JDBCBedDao(connection);
			Bed bed = bedDao.getEditIpdData(admissionid);
			
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			Client client = clientDAO.getClientDetails(bed.getClientid());
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			/*Calendar cal = Calendar.getInstance(); 
			String datetime = dateFormat.format(cal.getTime());*/
			
			String query = "insert into apm_client_parent_dietary(clientid, practitionerid, conditionid, wardid, bedid, age, gender, lastmodified, ipdid,time) values(?,?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, bed.getClientid());
			preparedStatement.setString(2, bed.getPractitionerid());
			preparedStatement.setString(3, bed.getConditionid());
			preparedStatement.setString(4, bed.getWardid());
			preparedStatement.setString(5, bed.getBedid());
			preparedStatement.setString(6, DateTimeUtils.getAge(client.getDob()));
			preparedStatement.setString(7, client.getGender());
			preparedStatement.setString(8, date);
			preparedStatement.setString(9, admissionid);
			preparedStatement.setString(10, time);
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

	public int storedietaryplan(DietaryDetails dietaryDetails, int result,String admissionid,String date) {
		int result1 = 0;
		try {
			BedDao bedDao = new JDBCBedDao(connection);
			Bed bed = bedDao.getEditIpdData(admissionid);
			if(dietaryDetails.getDqty()==0){
				dietaryDetails.setDqty(1);
			}
			dietaryDetails.setPotassium(((dietaryDetails.getPotassium()*dietaryDetails.getDqty())*100.0)/100.0);
			dietaryDetails.setSodium(((dietaryDetails.getSodium()*dietaryDetails.getDqty())*100.0)/100.0);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			String DateTime = dateFormat.format(cal.getTime());
			if(date==null){
				date=DateTime;
			}
			
			String query ="insert into apm_client_dietary(clientid, practitionerid, conditionid, day_plan, diet_catagory, diet, calories, feed, duration, parentid, lastmodified,dietstatus, protein,dqty,sodium,potassium,remark) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, bed.getClientid());
			statement.setString(2,bed.getPractitionerid());
			statement.setString(3, bed.getConditionid());
			statement.setString(4, dietaryDetails.getDietplan());
			statement.setString(5, dietaryDetails.getCategoryid());
			statement.setString(6, dietaryDetails.getSubcategoryid());
			statement.setString(7, dietaryDetails.getSubcategoryid());
			statement.setString(8, dietaryDetails.getFeed());
			statement.setString(9, dietaryDetails.getDuration());
			statement.setInt(10, result);
			statement.setString(11, date);
			statement.setString(12, "Planned");
			statement.setString(13, dietaryDetails.getSubcategoryid());
			statement.setString(14, ""+dietaryDetails.getDqty());
			statement.setString(15, ""+dietaryDetails.getSodium());
			statement.setString(16, ""+dietaryDetails.getPotassium());
			statement.setString(17, dietaryDetails.getRemark());
			//statement.setString(13, dietaryDetails.getSubcategoryid());
			result1 = statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result1;
	}

	public String getcatidfromname(String categoryname) {
		String categoryid = null;
		try {
			Statement stmt = connection.createStatement();
			String query = "select id from apm_diet_category where name='"+categoryname+"'";
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()){
				categoryid = rs.getString(1);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categoryid;	
	}
	
	public String getsubcatidfromname(String subcategoryname) {
		String subcategoryid = null;
		try {
			Statement stmt = connection.createStatement();
			String query = "select id from apm_diet_details where name='"+subcategoryname+"'";
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()){
				subcategoryid = rs.getString(1);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return subcategoryid;	
	}
	
	public String getcalidfromname(String calories) {
		String caloriesid = null;
		try {
			Statement stmt = connection.createStatement();
			String query = "select id from apm_diet_details where energy='"+calories+"'";
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()){
				caloriesid = rs.getString(1);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return caloriesid;	
	}

	public ArrayList<DietaryDetails> getdietserveplan(String fromdate, String todate, String searchtext, String wardnameid) {
		ArrayList<DietaryDetails> user = new ArrayList<DietaryDetails>();
		DietaryDetails dietaryDetails = null;
		Bed bed = null;
		try {
			StringBuilder sb = new StringBuilder();
			Boolean flag = false;
			
			sb.append("select wardid, bedid,apm_client_parent_dietary.lastmodified, ipdid,clientid,apm_client_parent_dietary.id from apm_client_parent_dietary ");
			
			if(searchtext!=null){
				flag=true;
				sb.append("INNER JOIN apm_patient ON  apm_client_parent_dietary.clientid = apm_patient.id ");
				sb.append("where (apm_patient.firstname like ('"+searchtext+"%') or surname like ('%"+searchtext+"') )  ");
			}
			
			if(flag){
				sb.append("and  apm_client_parent_dietary.lastmodified between '"+fromdate+"' and '"+todate+"'  ");
			}
			else {
				sb.append("where apm_client_parent_dietary.lastmodified between '"+fromdate+"' and '"+todate+"'  ");
			}
			
			if(wardnameid!=null){
				sb.append("and apm_client_parent_dietary.wardid="+wardnameid+" ");
			}
			
			//String query = "select wardid, bedid,lastmodified, ipdid,clientid,id from apm_client_parent_dietary";
			String query = sb.toString();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()){
				dietaryDetails = new DietaryDetails();
				bed = new Bed();
				
				String wardid = rs.getString(1);
				String bedid = rs.getString(2);
				
				String lastmodified = rs.getString(3);
				String ipdid = rs.getString(4);
				String clientid = rs.getString(5);
				//dietaryDetails.setParentid(Integer.parseInt(rs.getString(6)));
				
				IpdDAO ipdDAO = new JDBCIpdDAO(connection);
				String wardname = ipdDAO.getIpdWardName(wardid);
				String bedname = ipdDAO.getIpdBedName(bedid);
			
				ClientDAO clientDAO = new JDBCClientDAO(connection);
				Client client = clientDAO.getClientDetails(clientid);
				String clientname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();
				
				String[] datetime = lastmodified.split(" ");
				String date = datetime[0];
				
				String[] temp = date.split("-");
				String newdate = temp[2]+"/"+ temp[1]+"/"+temp[0];
				
				dietaryDetails.setIpdid(ipdid);
				dietaryDetails.setClientid(clientid);
				dietaryDetails.setBedname(bedname);
				dietaryDetails.setWardname(wardname);
				dietaryDetails.setClientname(clientname);
				dietaryDetails.setLastmodified(newdate);
				dietaryDetails.setParentid(rs.getString(6));
				ArrayList<DietaryDetails> viewdietplan = getalldietplan(rs.getString(6)); 
				/*dietaryDetails.setTotalProtein(totalProtein);
				dietaryDetails.setTotalCalories(totalCalories);*/
				
				int size = viewdietplan.size();
				if (size > 0) {
				double totalProtein = viewdietplan.get(size-1).getTotalProtein();
				double totalCalories = viewdietplan.get(size-1).getTotalCalories();
				dietaryDetails.setTotalProtein(totalProtein);
				dietaryDetails.setTotalCalories(totalCalories);
				}else{
					dietaryDetails.setTotalCalories(0);
					dietaryDetails.setTotalProtein(0);
				}
				
				
				dietaryDetails.setViewdietplan(viewdietplan);
				
				DietaryDetails details = getDietCount(rs.getString(6));
				dietaryDetails.setTotaldiet(details.getTotaldiet());
				dietaryDetails.setGivendiet(details.getGivendiet());
				
				
				
				user.add(dietaryDetails);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	
	public DietaryDetails getDietCount(String id) {
		DietaryDetails details = new DietaryDetails(); 
		try {
			String sql = "select count(parentid) from apm_client_dietary where parentid="+id+"";
			PreparedStatement preparedStatement =  connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				details.setTotaldiet(rs.getString(1));
				String completediet = getCompletedDiet(id);
				details.setGivendiet(completediet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return details;
	}

	public String getCompletedDiet(String id) {
		String completediet = "";
		try {
			String sql = "select count(dietstatus) from apm_client_dietary where dietstatus='Completed' and parentid="+id+"";
			PreparedStatement preparedStatement =  connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				completediet = rs.getString(1);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return completediet;
	}

	public ArrayList<DietaryDetails> getalldietplan(String parentid) {
		ArrayList<DietaryDetails>  arrayList = new ArrayList<DietaryDetails>();
		try {
			String query = "select day_plan, diet_catagory, diet, calories, feed,id,dietstatus,cafename,lastmodified,duration,datetime,userid,potassium,sodium,dqty from apm_client_dietary where parentid='"+parentid+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			double total =0;
			double totalProtein =0;
			double totalCalories =0;
			
			while(rs.next()){
				DietaryDetails dietaryDetails = new DietaryDetails();
				dietaryDetails.setDietplan(rs.getString(1));
				String dietplanname = getDietPlanName(rs.getString(1));
				dietaryDetails.setDietplanname(dietplanname);
				String category = getcatnamefromid(rs.getString(2));
				dietaryDetails.setCategory(category);
				
				String subcategory = getsubcatnamefromid(rs.getString(3));
				dietaryDetails.setSubcategory(subcategory);
				int qty=rs.getInt(15);
				String calories = getcalnamefromid(rs.getString(4));
				
				double calory=DateTimeUtils.convertToDouble(calories);
				calory=((calory*qty)*100.00)/100.00;
				dietaryDetails.setCalories(""+calory);
				
				dietaryDetails.setPotassium(DateTimeUtils.convertToDouble(rs.getString(13)));
				dietaryDetails.setSodium(DateTimeUtils.convertToDouble(rs.getString(14)));
				
				String proteins = getproteinfromid(rs.getString(4));
				double prot=DateTimeUtils.convertToDouble(proteins);
				prot=((prot*qty)*100.00)/100.00;
				dietaryDetails.setProtein(""+prot);
				
				dietaryDetails.setCategoryid(rs.getString(2));
				dietaryDetails.setSubcategoryid(rs.getString(3));
				dietaryDetails.setCaloriesid(rs.getString(4));
				dietaryDetails.setFeed(rs.getString(5));
				String feedname = getFeedNameFromId(rs.getString(5));
				dietaryDetails.setFeedname(feedname);
				dietaryDetails.setClientid(rs.getString(6));
				dietaryDetails.setChildid(rs.getString(6));
				dietaryDetails.setDietstatus(rs.getString(7));
				dietaryDetails.setCafename(rs.getString(8));
				String lastdate = rs.getString(9);
				dietaryDetails.setUserid(rs.getString(12));
				
				totalProtein = totalProtein +(prot);
				totalCalories = totalCalories + calory;
				 
				dietaryDetails.setTotalProtein(totalProtein);
				dietaryDetails.setTotalCalories(totalCalories);
				
				String[] temp = lastdate.split("-");
				String lastmodified = temp[2]+"-"+temp[1]+"-"+temp[0];
				dietaryDetails.setLastmodified(lastmodified);
				dietaryDetails.setDuration(rs.getString(10));
				String datetime = rs.getString(11);
				if(datetime!=null){
					String[] datetime1 = datetime.split(" ");
					String[] date = datetime1[0].split("-");
					String finaldte = date[2]+"-"+ date[1]+"-"+ date[0] +" "+ datetime1[1];
			 		dietaryDetails.setDatetime(finaldte);
				}else{
					dietaryDetails.setDatetime("");
				}
				
				arrayList.add(dietaryDetails);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}
	public String getDietPlanName(String dietplanid) {
		String name="";
		try {
			String sql ="select name from dietary_dietplan where id='"+dietplanid+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				name =rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}

	public String getcatnamefromid(String categoryid) {
		String categoryname = null;
		try {
			Statement stmt = connection.createStatement();
			String query = "select name from apm_diet_category where id='"+categoryid+"'";
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()){
				categoryname = rs.getString(1);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categoryname;	
	}

	public String getsubcatnamefromid(String subcategoryid) {
		String subcategoryname = null;
		try {
			Statement stmt = connection.createStatement();
			String query = "select name from apm_diet_details where id='"+subcategoryid+"'";
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()){
				subcategoryname = rs.getString(1);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return subcategoryname;	
	}
	
	public String getcalnamefromid(String caloriesid) {
		String caloriesname = null;
		try {
			Statement stmt = connection.createStatement();
			String query = "select energy from apm_diet_details where id='"+caloriesid+"'";
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()){
				caloriesname = rs.getString(1);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return caloriesname;	
	}
	public String getproteinfromid(String proteinid) {
		String proteinname = null;
		try {
			Statement stmt = connection.createStatement();
			String query = "select protein from apm_diet_details where id='"+proteinid+"'";
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()){
				proteinname = rs.getString(1);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return proteinname;	
	}

	//Code for cafeteria
	public ArrayList<DietaryDetails> getcafeteriaplan() {
		ArrayList<DietaryDetails> user = new ArrayList<DietaryDetails>();
		DietaryDetails dietaryDetails = null;
		Bed bed = null;
		try {
			String query = "select wardid, bedid,lastmodified, ipdid,clientid,id from apm_client_parent_dietary";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()){
				dietaryDetails = new DietaryDetails();
				bed = new Bed();
				
				String wardid = rs.getString(1);
				String bedid = rs.getString(2);
				String lastmodified = rs.getString(3);
				String ipdid = rs.getString(4);
				String clientid = rs.getString(5);
				dietaryDetails.setParentid(rs.getString(6));
				
				IpdDAO ipdDAO = new JDBCIpdDAO(connection);
				String wardname = ipdDAO.getIpdWardName(wardid);
				String bedname = ipdDAO.getIpdBedName(bedid);
			
				ClientDAO clientDAO = new JDBCClientDAO(connection);
				Client client = clientDAO.getClientDetails(clientid);
				String clientname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();
				
				String[] datetime = lastmodified.split(" ");
				String date = datetime[0];
				
				String[] temp = date.split("-");
				String newdate = temp[2]+"/"+ temp[1]+"/"+temp[0];
				
				dietaryDetails.setIpdid(ipdid);
				dietaryDetails.setClientid(clientid);
				dietaryDetails.setBedname(bedname);
				dietaryDetails.setWardname(wardname);
				dietaryDetails.setClientname(clientname);
				dietaryDetails.setLastmodified(newdate);
				
				ArrayList<DietaryDetails> viewdietplan = getallcafetriadietplan(rs.getString(6)); 
				dietaryDetails.setViewdietplan(viewdietplan);
				
				//ArrayList<DietaryDetails> cafelist = getalldeliveryuser();
				//dietaryDetails.setCafeusername(cafelist);
				
				user.add(dietaryDetails);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	
	public ArrayList<DietaryDetails> getallcafetriadietplan(String parentid) {
		ArrayList<DietaryDetails>  arrayList = new ArrayList<DietaryDetails>();
		DietaryDetails dietaryDetails = null;

		try {
			dietaryDetails = new DietaryDetails();
			String query = "select day_plan, diet_catagory, diet, calories, feed, status, cafename from apm_client_dietary where parentid='"+parentid+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				dietaryDetails = new DietaryDetails();
				dietaryDetails.setDietplan(rs.getString(1));
				
				String category = getcatnamefromid(rs.getString(2));
				dietaryDetails.setCategory(category);
				
				String subcategory = getsubcatnamefromid(rs.getString(3));
				dietaryDetails.setSubcategory(subcategory);
				
				String calories = getcalnamefromid(rs.getString(4));
				dietaryDetails.setCalories(calories);
				
				dietaryDetails.setFeed(rs.getString(5));
				
				dietaryDetails.setStatus(rs.getString(6));
				
				dietaryDetails.setCafename(rs.getString(7));
				
				ArrayList<DietaryDetails> list = getalldeliveryuser();
				dietaryDetails.setCafeusername(list);
				dietaryDetails.setViewteammember(list);
				arrayList.add(dietaryDetails);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public ArrayList<DietaryDetails> getalldeliveryuser(){
		ArrayList<DietaryDetails> arrayList = new ArrayList<DietaryDetails>();
		DietaryDetails details = null;
		try {
			String query = "select firstname,lastname from apm_user where jobtitle='Cafeteria'";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				details = new DietaryDetails();
				String str1 = rs.getString(1);
				String str2 = rs.getString(2);
				String cafename =  str1+" "+str2;
				//details.setCafename(cafename);
				details.setCafeteamname(cafename);
				arrayList.add(details);
			}
		} catch (Exception e) {
			e.printStackTrace();		
		}
		return arrayList;
	}
	
	//Dietary details method
	public int deleteplanfrmparent(String parentid) {
		int result = 0;
		try {
			int parentid1 = Integer.parseInt(parentid);
			String query = "delete from apm_client_parent_dietary where id="+parentid1;
			PreparedStatement stmt = connection.prepareStatement(query);
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	//Dietary details method
	public int deleteplanfrmchild(String parentid) {
		int result = 0;
		try {
			String query = "delete from apm_client_dietary where parentid='"+parentid+"'";
			PreparedStatement stmt = connection.prepareStatement(query);
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	//Kitchen Cafeteria method
	public ArrayList<DietaryDetails> getkitchenplan() {
		ArrayList<DietaryDetails>  arrayList = new ArrayList<DietaryDetails>();
		DietaryDetails dietaryDetails = null;

		try {
			dietaryDetails = new DietaryDetails();
			String query = "select day_plan, diet_catagory, diet, calories, feed, dietstatus from apm_client_dietary";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				dietaryDetails = new DietaryDetails();
				dietaryDetails.setDietplan(rs.getString(1));
				
				String category = getcatnamefromid(rs.getString(2));
				dietaryDetails.setCategory(category);
				
				String subcategory = getsubcatnamefromid(rs.getString(3));
				dietaryDetails.setSubcategory(subcategory);
				
				String calories = getcalnamefromid(rs.getString(4));
				dietaryDetails.setCalories(calories);
				
				dietaryDetails.setFeed(rs.getString(5));
				
				dietaryDetails.setDietstatus(rs.getString(6));
	
				arrayList.add(dietaryDetails);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}


	public ArrayList<DietaryDetails> getspecificdietlist(
			String dailydietplankitchen) {
		ArrayList<DietaryDetails>  arrayList = new ArrayList<DietaryDetails>();
		DietaryDetails dietaryDetails = null;
		try {
			dietaryDetails = new DietaryDetails();
			String query = "select day_plan, diet_catagory, diet, calories, feed, dietstatus from apm_client_dietary where day_plan='"+dailydietplankitchen+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				dietaryDetails = new DietaryDetails();
				dietaryDetails.setDietplan(rs.getString(1));
				
				String category = getcatnamefromid(rs.getString(2));
				dietaryDetails.setCategory(category);
				String subcategory = getsubcatnamefromid(rs.getString(3));
				dietaryDetails.setSubcategory(subcategory);
				String calories = getcalnamefromid(rs.getString(4));
				dietaryDetails.setCalories(calories);
				dietaryDetails.setFeed(rs.getString(5));
				dietaryDetails.setDietstatus(rs.getString(6));
				arrayList.add(dietaryDetails);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	//Patient diet plan for Delivery team
	public ArrayList<DietaryDetails> getdietplanfrdteam() {
		ArrayList<DietaryDetails> user = new ArrayList<DietaryDetails>();
		DietaryDetails dietaryDetails = null;
		Bed bed = null;
		try {
			String query = "select wardid,bedid,ipdid,clientid,id from apm_client_parent_dietary";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()){
				dietaryDetails = new DietaryDetails();
				bed = new Bed();
				
				String wardid = rs.getString(1);
				String bedid = rs.getString(2);
				String ipdid = rs.getString(3);
				String clientid = rs.getString(4);
				dietaryDetails.setParentid(rs.getString(5));
				
				IpdDAO ipdDAO = new JDBCIpdDAO(connection);
				String wardname = ipdDAO.getIpdWardName(wardid);
				String bedname = ipdDAO.getIpdBedName(bedid);
			
				ClientDAO clientDAO = new JDBCClientDAO(connection);
				Client client = clientDAO.getClientDetails(clientid);
				String clientname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();
				
				dietaryDetails.setIpdid(ipdid);
				dietaryDetails.setClientid(clientid);
				dietaryDetails.setBedname(bedname);
				dietaryDetails.setWardname(wardname);
				dietaryDetails.setClientname(clientname);
				
				
				ArrayList<DietaryDetails> dietplanfrdteam = getalldietplanfrdteam();
				dietaryDetails.setDteamdietplanList(dietplanfrdteam);
				
				user.add(dietaryDetails);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	public ArrayList<DietaryDetails> getalldietplanfrdteam() {
		ArrayList<DietaryDetails>  arrayList = new ArrayList<DietaryDetails>();
		DietaryDetails dietaryDetails = null;
		try {
			dietaryDetails = new DietaryDetails();
			String query = "select day_plan, diet_catagory, diet, calories, feed, dietstatus from apm_client_dietary";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				dietaryDetails = new DietaryDetails();
				dietaryDetails.setDietplan(rs.getString(1));
				String category = getcatnamefromid(rs.getString(2));
				dietaryDetails.setCategory(category);
				String subcategory = getsubcatnamefromid(rs.getString(3));
				dietaryDetails.setSubcategory(subcategory);
				String calories = getcalnamefromid(rs.getString(4));
				dietaryDetails.setCalories(calories);
				dietaryDetails.setFeed(rs.getString(5));
				dietaryDetails.setDietstatus(rs.getString(6));
				arrayList.add(dietaryDetails);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public String getipdidfrmpid(String id) {
		String ipdid = "";
		try {
			String sql ="select ipdid from apm_client_parent_dietary where id="+id+"";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				ipdid = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ipdid;
	}

	public ArrayList<DietaryDetails> getParantIdList(String ipdid, String fromdate,String todate) {
		ArrayList<DietaryDetails> arrayList = new ArrayList<DietaryDetails>();
		try {
			String sql = "select id from apm_client_parent_dietary where ipdid ='"+ipdid+"' and lastmodified between '"+fromdate+"' and '"+todate+"' order by id desc";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				DietaryDetails details = new  DietaryDetails();
				details.setId(rs.getInt(1));
				arrayList.add(details);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public int updateDietData(DietaryDetails details, int parentid, String ipdid,String childid) {
		int result1 = 0;
		try {
			BedDao bedDao = new JDBCBedDao(connection);
			Bed bed = bedDao.getEditIpdData(ipdid);
		
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			String DateTime = dateFormat.format(cal.getTime());
			int id = Integer.parseInt(childid);
			String query ="update apm_client_dietary set clientid=?, practitionerid=?, conditionid=?, day_plan=?, diet_catagory=?, diet=?, calories=?, feed=?, duration=?, parentid=?, lastmodified=?,dietstatus=? where id="+id+"";
		
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, bed.getClientid());
			statement.setString(2,bed.getPractitionerid());
			statement.setString(3, bed.getConditionid());
			statement.setString(4, details.getDietplan());
			statement.setString(5, details.getCategoryid());
			statement.setString(6, details.getSubcategoryid());
			statement.setString(7, details.getSubcategoryid());
			statement.setString(8, details.getFeed());
			statement.setString(9, details.getDuration());
			statement.setInt(10, parentid);
			statement.setString(11, DateTime);
			statement.setString(12, "Planned");
			result1 = statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result1;
	}

	public int deleteDataFrmChild(String childid) {
		int result = 0;
		try {
			String sql = "delete from apm_client_dietary where id="+childid+"";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int storedietaryparentTemplate(String addmissionid, String templatename) {
		int result =0;
		try {
			
			BedDao bedDao = new JDBCBedDao(connection);
			Bed bed = bedDao.getEditIpdData(addmissionid);
			
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			Client client = clientDAO.getClientDetails(bed.getClientid());
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance(); 
			String datetime = dateFormat.format(cal.getTime());
			
			String query = "insert into apm_client_parent_dietary_template(clientid, practitionerid, conditionid, wardid, bedid, age, gender, lastmodified, ipdid,templatename) values(?,?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, bed.getClientid());
			preparedStatement.setString(2, bed.getPractitionerid());
			preparedStatement.setString(3, bed.getConditionid());
			preparedStatement.setString(4, bed.getWardid());
			preparedStatement.setString(5, bed.getBedid());
			preparedStatement.setString(6, DateTimeUtils.getAge(client.getDob()));
			preparedStatement.setString(7, client.getGender());
			preparedStatement.setString(8, datetime);
			preparedStatement.setString(9, addmissionid);
			preparedStatement.setString(10, templatename);
			
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
		return result;	}

	public int storeDietaryChildTemplate(DietaryDetails dietaryDetails,
			int result, String addmissionid) {
		int result1 = 0;
		try {
			BedDao bedDao = new JDBCBedDao(connection);
			Bed bed = bedDao.getEditIpdData(addmissionid);
		
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			String DateTime = dateFormat.format(cal.getTime());
			
			String query ="insert into apm_client_dietary_template(clientid, practitionerid, conditionid, day_plan, diet_catagory, diet, calories, feed, duration, parentid, lastmodified,dietstatus,protein) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, bed.getClientid());
			statement.setString(2,bed.getPractitionerid());
			statement.setString(3, bed.getConditionid());
			statement.setString(4, dietaryDetails.getDietplan());
			statement.setString(5, dietaryDetails.getCategoryid());
			statement.setString(6, dietaryDetails.getSubcategoryid());
			statement.setString(7, dietaryDetails.getSubcategoryid());
			statement.setString(8, dietaryDetails.getFeed());
			statement.setString(9, dietaryDetails.getDuration());
			statement.setInt(10, result);
			statement.setString(11, DateTime);
			statement.setString(12, "Planned");
			statement.setString(13, dietaryDetails.getSubcategoryid());
			result1 = statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result1;
	}

	public ArrayList<DietaryDetails> getTemplateList(String ipdid) {
		ArrayList<DietaryDetails> arrayList = new ArrayList<DietaryDetails>();
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select id,templatename from apm_client_parent_dietary_template ");
			if(ipdid!=null){
				buffer.append("where ipdid="+ipdid+"");
			}
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				DietaryDetails details = new DietaryDetails();
				details.setId(rs.getInt(1));
				details.setTemplatename(rs.getString(2));
				arrayList.add(details);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public ArrayList<DietaryDetails> getTemplateDataFrmChild(String parentid) {
		ArrayList<DietaryDetails>  arrayList = new ArrayList<DietaryDetails>();
		
		DietaryDetails dietaryDetails = null;
		try {
			dietaryDetails = new DietaryDetails();
			String query = "select day_plan, diet_catagory, diet, calories, feed,id,dietstatus,cafename,lastmodified,duration,protein from apm_client_dietary_template where parentid='"+parentid+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				dietaryDetails = new DietaryDetails();
				dietaryDetails.setDietplan(rs.getString(1));
				
				String category = getcatnamefromid(rs.getString(2));
				dietaryDetails.setCategory(category);
				
				String subcategory = getsubcatnamefromid(rs.getString(3));
				dietaryDetails.setSubcategory(subcategory);
				
				String calories = getcalnamefromid(rs.getString(4));
				dietaryDetails.setCalories(calories);
				
				dietaryDetails.setCategoryid(rs.getString(2));
				dietaryDetails.setSubcategoryid(rs.getString(3));
				
				dietaryDetails.setCaloriesid(rs.getString(4));
				dietaryDetails.setFeed(rs.getString(5));
				dietaryDetails.setClientid(rs.getString(6));
				dietaryDetails.setChildid(rs.getString(6));
				dietaryDetails.setDietstatus(rs.getString(7));
				dietaryDetails.setCafename(rs.getString(8));
				String lastdate = rs.getString(9);
				String[] temp = lastdate.split("-");
				String lastmodified = temp[2]+"-"+temp[1]+"-"+temp[0];
				dietaryDetails.setLastmodified(lastmodified);
				dietaryDetails.setDuration(rs.getString(10));
				dietaryDetails.setProtein(rs.getString(11));
				arrayList.add(dietaryDetails);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public ArrayList<DietaryDetails> getExistDiet(String todate) {
		ArrayList<DietaryDetails> arrayList = new ArrayList<DietaryDetails>();
	
		try {
			String sql ="SELECT ipdid FROM apm_client_parent_dietary where lastmodified='"+todate+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				DietaryDetails details = new  DietaryDetails();
				details.setIpdid(rs.getString(1));
				arrayList.add(details);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}
	
	//IPD Nurse Dietary Data
	
	public ArrayList<DietaryDetails> getAllIpdDietplan() {
		ArrayList<DietaryDetails> user = new ArrayList<DietaryDetails>();
		DietaryDetails dietaryDetails = null;
		Bed bed = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			// cal.add(Calendar.DATE, -7);
			String currDate = dateFormat.format(cal.getTime());
			
			String todate = currDate+" "+"59:59:59";
			String query = "select wardid, bedid,lastmodified, ipdid,clientid,id from apm_client_parent_dietary where lastmodified between '"+currDate+"' and '"+todate+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			IpdDAO ipdDAO = new JDBCIpdDAO(connection);
			ClientDAO clientDAO = new JDBCClientDAO(connection);
			while(rs.next()){
				dietaryDetails = new DietaryDetails();
				bed = new Bed();
				
				String wardid = rs.getString(1);
				String bedid = rs.getString(2);
				String lastmodified = rs.getString(3);
				String ipdid = rs.getString(4);
				String clientid = rs.getString(5);
				dietaryDetails.setParentid(rs.getString(6));
				
				
				String wardname = ipdDAO.getIpdWardName(wardid);
				String bedname = ipdDAO.getIpdBedName(bedid);
			
				
				Client client = clientDAO.getClientDetails(clientid);
				String clientname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();
				
				String[] datetime = lastmodified.split(" ");
				String date = datetime[0];
				
				String[] temp = date.split("-");
				String newdate = temp[2]+"/"+ temp[1]+"/"+temp[0];
				dietaryDetails.setAge(""+client.getAge());
				dietaryDetails.setIpdid(ipdid);
				dietaryDetails.setClientid(clientid);
				dietaryDetails.setBedname(bedname);
				dietaryDetails.setWardname(wardname);
				dietaryDetails.setClientname(clientname);
				dietaryDetails.setLastmodified(newdate);
				dietaryDetails.setBedid(bedid);
				dietaryDetails.setWardid(wardid);
				//ArrayList<DietaryDetails> viewdietplan = getalldietplan(rs.getString(6)); 
				//dietaryDetails.setViewdietplan(viewdietplan);
				
				//ArrayList<DietaryDetails> cafelist = getalldeliveryuser();
				//dietaryDetails.setCafeusername(cafelist);
				
				user.add(dietaryDetails);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	public DietaryDetails getSingleDietplan(String parentid, String serachDiet) {
		DietaryDetails dietaryDetails = new DietaryDetails();
		try {
			String query = "select day_plan, diet_catagory, diet, calories, feed,id,dietstatus,cafename,lastmodified,duration from apm_client_dietary where parentid='"+parentid+"' and day_plan='"+serachDiet+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				dietaryDetails.setDietplan(rs.getString(1));
				
				String category = getcatnamefromid(rs.getString(2));
				dietaryDetails.setCategory(category);
				
				String subcategory = getsubcatnamefromid(rs.getString(3));
				dietaryDetails.setSubcategory(subcategory);
				
				String calories = getcalnamefromid(rs.getString(4));
				dietaryDetails.setCalories(calories);
				
				dietaryDetails.setCategoryid(rs.getString(2));
				dietaryDetails.setSubcategoryid(rs.getString(3));
				dietaryDetails.setCaloriesid(rs.getString(4));
				dietaryDetails.setFeed(rs.getString(5));
				dietaryDetails.setClientid(rs.getString(6));
				dietaryDetails.setChildid(rs.getString(6));
				dietaryDetails.setDietstatus(rs.getString(7));
				dietaryDetails.setCafename(rs.getString(8));
				String lastdate = rs.getString(9);
				String[] temp = lastdate.split("-");
				String lastmodified = temp[2]+"-"+temp[1]+"-"+temp[0];
				dietaryDetails.setLastmodified(lastmodified);
				dietaryDetails.setDuration(rs.getString(10));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dietaryDetails;
	}

	public DietaryDetails getIpdPerPatientDiet(String ipdid1) {
		DietaryDetails dietaryDetails = new DietaryDetails();
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			// cal.add(Calendar.DATE, -7);
			String currDate = dateFormat.format(cal.getTime());
			String today = currDate +" "+"59:59:59";
			
			String query = "select wardid, bedid,lastmodified, ipdid,clientid,id from apm_client_parent_dietary where lastmodified between '"+currDate+"' and '"+today+"' and ipdid='"+ipdid1+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()){
				
				String wardid = rs.getString(1);
				String bedid = rs.getString(2);
				String lastmodified = rs.getString(3);
				String ipdid = rs.getString(4);
				String clientid = rs.getString(5);
				dietaryDetails.setParentid(rs.getString(6));
				
				IpdDAO ipdDAO = new JDBCIpdDAO(connection);
				String wardname = ipdDAO.getIpdWardName(wardid);
				String bedname = ipdDAO.getIpdBedName(bedid);
			
				ClientDAO clientDAO = new JDBCClientDAO(connection);
				Client client = clientDAO.getClientDetails(clientid);
				String clientname = client.getTitle() + " " + client.getFirstName() + " " + client.getLastName();
				
				String[] datetime = lastmodified.split(" ");
				String date = datetime[0];
				
				String[] temp = date.split("-");
				String newdate = temp[2]+"/"+ temp[1]+"/"+temp[0];
				dietaryDetails.setAge(""+client.getAge());
				dietaryDetails.setIpdid(ipdid);
				dietaryDetails.setClientid(clientid);
				dietaryDetails.setBedname(bedname);
				dietaryDetails.setWardname(wardname);
				dietaryDetails.setClientname(clientname);
				dietaryDetails.setLastmodified(newdate);
				dietaryDetails.setBedid(bedid);
				dietaryDetails.setWardid(wardid);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dietaryDetails;
	}

	public ArrayList<DietaryDetails> getDietPlanList() {
		ArrayList<DietaryDetails> arrayList = new ArrayList<DietaryDetails>();
		try {
			String sql ="select id, name, description from dietary_dietplan";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				DietaryDetails details = new DietaryDetails();
				details.setId(rs.getInt(1));
				details.setName(rs.getString(2));
				details.setDescription(rs.getString(3));
				arrayList.add(details);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public ArrayList<DietaryDetails> getDietFeedList() {
		ArrayList<DietaryDetails> arrayList = new ArrayList<DietaryDetails>();
		try {
			String sql ="select id, feed, description from dietary_feed";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				DietaryDetails details = new DietaryDetails();
				details.setId(rs.getInt(1));
				details.setName(rs.getString(2));
				details.setDescription(rs.getString(3));
				arrayList.add(details);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public String getFeedNameFromId(String feed) {
		String name ="";
		try {
			String sql ="select feed from dietary_feed where id='"+feed+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				name = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}

	public ArrayList<DietaryDetails> getSingleDietplanList(String parentid, String serachDiet) {
		ArrayList<DietaryDetails> arrayList = new ArrayList<DietaryDetails>();
		try {
			String query = "select day_plan, diet,executed from apm_client_dietary where parentid='"+parentid+"' and day_plan='"+serachDiet+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				DietaryDetails dietaryDetails = new DietaryDetails();
				String subcategory = getsubcatnamefromid(rs.getString(2));
				dietaryDetails.setSubcategory(subcategory);
				dietaryDetails.setExecuted(rs.getString(3));
				arrayList.add(dietaryDetails);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public int updatedietarygivenstatus(String parentid, String dietplan, String val, String userid, String datetime) {
		int res=0;
		try {
			String query = "update apm_client_dietary set executed='"+val+"',userid='"+userid+"', datetime='"+datetime+"' where day_plan='"+dietplan+"' and parentid='"+parentid+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<DietaryDetails> getIpdDietPlan(String ipdid, String date) {
		ArrayList<DietaryDetails> arrayList = new ArrayList<DietaryDetails>();
		try {
			String todate = date +" "+ "59:59:59";
			String sql ="select id from apm_client_parent_dietary where lastmodified between '"+date+"' and '"+todate+"' and ipdid='"+ipdid+"'";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String query = "select id, clientid, practitionerid, conditionid, day_plan, diet_catagory, diet, calories, feed, duration, parentid, lastmodified, status, cafename, dietstatus, userid, datetime, executed from apm_client_dietary where parentid='"+resultSet.getString(1)+"'";
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()){
					DietaryDetails dietaryDetails = new DietaryDetails();
					String subcategory = getsubcatnamefromid(rs.getString(7));
					dietaryDetails.setSubcategory(subcategory);
					ArrayList<DietaryDetails> dietgivenlist = new ArrayList<DietaryDetails>();
					for (int i = 1; i <=9; i++) {
						DietaryDetails details = new DietaryDetails();
						if(rs.getString(18).equals("0")){
							details.setUserid("-");
							details.setDatetime("");
						}else{
							if(rs.getString(5).equals(""+i+"")){
								String[] datetime = rs.getString(17).split(" ");
								String adate = DateTimeUtils.getCommencingDate1(datetime[0]) +" "+ datetime[1];
								details.setUserid(rs.getString(16));
								details.setDatetime(adate);
							}else{
								details.setUserid("-");
								details.setDatetime("");
							}
						}
						dietgivenlist.add(details);
					}
					dietaryDetails.setDietgivenlist(dietgivenlist);
					arrayList.add(dietaryDetails);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public String mutiParentIds(String ipdId) {
		String result="";
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			// cal.add(Calendar.DATE, -7);
			String currDate = dateFormat.format(cal.getTime());
			String today = currDate +" "+"59:59:59";
			
			String query = "select wardid, bedid,lastmodified, ipdid,clientid,id from apm_client_parent_dietary where lastmodified between '"+currDate+"' and '"+today+"' and ipdid='"+ipdId+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();	
			int i=0;
			while (rs.next()) {
				if(i>0){
					result=result+","+rs.getString(6);
				}else{
					result=rs.getString(6);
				}
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	
	
	
}


