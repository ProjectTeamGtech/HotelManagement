package com.apm.Master.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.Inventory.eu.entity.Product;
import com.apm.Master.eu.bi.PrescriptionMasterDAO;
import com.apm.Master.eu.bi.TreatmentTypeDAO;
import com.apm.Master.eu.entity.Master;
import com.apm.Master.eu.entity.TreatmentType;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;

public class JDBCPrescriptionMasterDAO implements PrescriptionMasterDAO {

	private Connection connection;

	public JDBCPrescriptionMasterDAO(Connection connection) {

		this.connection = connection;
	}

	public ArrayList<Priscription> getPrescriptionCategoryList() {

		ArrayList<Priscription> list = new ArrayList<Priscription>();

		try {

			String sql = "select id,name,discription from apm_medicine_categeory order by id desc";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				Priscription priscription = new Priscription();
				priscription.setId(rs.getInt(1));
				priscription.setName(rs.getString(2));
				priscription.setDescription(rs.getString(3));
				list.add(priscription);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	public ArrayList<Priscription> getPrescriptionCategoryList(
			Pagination pagination,String searchText) {

		ArrayList<Priscription> list = new ArrayList<Priscription>();

		try {
			String sql ="";
			if (searchText!=null) {
				sql = "select id,name,discription from apm_medicine_categeory where name like ('"+searchText+"%') order by id desc";
			} else {
				sql = "select id,name,discription from apm_medicine_categeory order by id desc";
			}
			sql = pagination.getSQLQuery(sql);
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				Priscription priscription = new Priscription();
				priscription.setId(rs.getInt(1));
				priscription.setName(rs.getString(2));
				priscription.setDescription(rs.getString(3));
				list.add(priscription);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	public int getTotalPrescriptionCategoryCount(String searchText) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql ="";
		if (searchText!=null) {
			sql = "select count(*) from apm_medicine_categeory where name like ('"+searchText+"%')";
		} else {
			sql = "select count(*) from apm_medicine_categeory";
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

	public int addPrescriptionCategory(Priscription priscription) {

		int result = 0;

		try {

			PreparedStatement ps = connection
					.prepareStatement("insert into apm_medicine_categeory (name,discription) values (?,?)");
			ps.setString(1, priscription.getName());
			ps.setString(2, priscription.getDescription());
			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public Priscription getPrescriptionCategory(String selectedid) {

		Priscription priscription = new Priscription();

		try {

			PreparedStatement statement = connection
					.prepareStatement("select name,discription from apm_medicine_categeory where id=?");
			statement.setString(1, selectedid);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {

				priscription.setName(rs.getString(1));
				priscription.setDescription(rs.getString(2));
				priscription.setId(Integer.valueOf(selectedid));

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return priscription;
	}

	public int updatePrescriptionCategory(Priscription priscription) {

		int result = 0;

		try {

			PreparedStatement ps = connection
					.prepareStatement("update apm_medicine_categeory set name=?,discription=? where id=?");
			ps.setString(1, priscription.getName());
			ps.setString(2, priscription.getDescription());
			ps.setInt(3, priscription.getId());
			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();

		}

		return result;
	}

	public int deletePrescriptionCategory(String selectedid) {

		int result = 0;

		try {

			PreparedStatement ps = connection
					.prepareStatement("delete from apm_medicine_categeory where id=?");
			ps.setString(1, selectedid);
			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<Priscription> getPrescriptionDetails() {

		ArrayList<Priscription> priscriptions = new ArrayList<Priscription>();

		try {

			String sql = "select id,categeory,drug,strength,mrp,purchase_price,sale_price,expiry_date,pkg,batch_no,mfg,locations,specializations,genericname,risk,dosenoteid,molecules,uom,rnote from apm_medicine_details order by id desc";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				Priscription priscription = new Priscription();
				priscription.setId(rs.getInt(1));
				priscription.setCategoryid(rs.getString(2));
				priscription.setDrug(rs.getString(3)+" ("+rs.getString(14)+")");
				priscription.setStrength(rs.getString(4));
				priscription.setMrp(rs.getString(5));
				priscription.setPurchase_price(rs.getString(6));
				priscription.setSale_price(rs.getString(7));
				priscription.setExpiry_date(rs.getString(8));
				priscription.setPkg(rs.getString(9));
				priscription.setBatch_no(rs.getString(10));
				priscription.setMfg(rs.getString(11));
				priscription.setLocation(rs.getString(12));
				priscription.setSpecialization(rs.getString(13));
				priscription.setGenericname(rs.getString(14));
				priscription.setRisk(rs.getString(15));
				priscription.setDosagenote(rs.getString(16));
				priscription.setMolecules(rs.getString(17));
				
				priscription.setUom(rs.getString(18));
				priscription.setRemark(rs.getString(19));
				priscriptions.add(priscription);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return priscriptions;

	}

	public ArrayList<Priscription> getPrescriptionDetails(Pagination pagination,String searchText) {

		ArrayList<Priscription> priscriptions = new ArrayList<Priscription>();

		try {
			String sql ="";
			if (searchText!=null) {
				sql = "select id,categeory,drug,strength,mrp,purchase_price,sale_price,expiry_date,pkg,batch_no,mfg,locations,specializations,genericname,risk from apm_medicine_details where drug like ('"+searchText+"%') order by id desc";
			} else {
				sql = "select id,categeory,drug,strength,mrp,purchase_price,sale_price,expiry_date,pkg,batch_no,mfg,locations,specializations,genericname,risk from apm_medicine_details order by id desc";
			}
			//sql = "select id,categeory,drug,strength,mrp,purchase_price,sale_price,expiry_date,pkg,batch_no,mfg,locations,specializations,genericname,risk from apm_medicine_details order by id desc";
			sql = pagination.getSQLQuery(sql);
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				Priscription priscription = new Priscription();
				priscription.setId(rs.getInt(1));
				
				PrescriptionMasterDAO prescriptionMasterDAO=new JDBCPrescriptionMasterDAO(connection);
				String selectedid=rs.getString(2);
				Priscription priscription1=prescriptionMasterDAO.getPrescriptionCategory(selectedid); 
				priscription.setCategoryid(priscription1.getName());
				
				//priscription.setCategoryid(rs.getString(2));
				priscription.setDrug(rs.getString(3));
				priscription.setStrength(rs.getString(4));
				priscription.setMrp(rs.getString(5));
				priscription.setPurchase_price(rs.getString(6));
				priscription.setSale_price(rs.getString(7));
				priscription.setExpiry_date(rs.getString(8));
				priscription.setPkg(rs.getString(9));
				priscription.setBatch_no(rs.getString(10));
				priscription.setMfg(rs.getString(11));
				priscription.setLocation(rs.getString(12));
				priscription.setSpecialization(rs.getString(13));
				priscription.setGenericname(rs.getString(14));
				priscription.setRisk(rs.getString(15));
				priscriptions.add(priscription);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return priscriptions;
	}

	public int getTotalPrescriptionDetailsCount(String searchText) {

		int result = 0;

		try {
			String sql="";
			
			if(searchText!=null){
				sql = "select count(*) from apm_medicine_details where drug like ('"+searchText+"%')";
			}else{
				sql ="select count(*) from apm_medicine_details";
			}
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				result = rs.getInt(1);
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return result;
	}

	public int addPrescriptionDetails(Priscription priscription) {

		int result=0;
		int res=0;
		try {
               			
             PreparedStatement ps=connection.prepareStatement("insert into apm_medicine_details (categeory,drug,strength,mrp,purchase_price,sale_price,expiry_date,pkg,batch_no,mfg,locations,specializations,genericname,risk,dosenoteid,frequency,iswbd,caldose,type,days,qty,uom,rnote,dosefreq,component)  values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"); 
			 ps.setString(1,"1");
			 ps.setString(2, priscription.getDrug());
			 ps.setString(3, priscription.getStrength());
			 ps.setString(4, priscription.getMrp());
			 ps.setString(5, priscription.getPurchase_price());
			 ps.setString(6, priscription.getSale_price());
			 ps.setString(7, priscription.getExpiry_date());
			 ps.setString(8, priscription.getPkg());
			 ps.setString(9, priscription.getBatch_no());
			 ps.setString(10, priscription.getMfg());
			 ps.setString(11, priscription.getLocation());
			 ps.setString(12, priscription.getSpecialization());
			 ps.setString(13, priscription.getGenericname());
			 ps.setString(14, priscription.getRisk());
			 ps.setString(15, priscription.getDosagenote());
			 ps.setString(16, priscription.getFrequency());
			 ps.setString(17, priscription.getIswbd());
			 ps.setString(18, priscription.getCaldose());
			 ps.setString(19, priscription.getPrisctypename());
			 ps.setInt(20, priscription.getDays());
			 ps.setString(21, priscription.getDr_qty());
			 ps.setString(22, priscription.getUom());
			 ps.setString(23, priscription.getRemark());
			 ps.setString(24, priscription.getDosefreq());
			 ps.setString(25, priscription.getComponent());
			 result=ps.executeUpdate();
			
			 if(result>0){
				 
				    ResultSet set=ps.getGeneratedKeys();
				    while(set.next()){
				    	
				    	  res=set.getInt(1);
				    }
				   
				// addToInventoryMedicine(priscription, res);
				    
			 }
			 
			 
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
	
		return result;
	}
	
	
	public int addToInventoryMedicine(Priscription priscription,int categoryID) {
		
		int result=0;
		
		try {
			//  03 May 2018
			String added_date="";
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Calendar cal = Calendar.getInstance();
			added_date = dateFormat.format(cal.getTime());
			added_date = DateTimeUtils.getCommencingDate1(added_date);
			
			String sql="insert into inventory_product (prodtypeid,prodname,mrp,purchaseprice,saleprice,categoryid,mdicinenameid,added_date) values (?,?,?,?,?,?,?,?)";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, "4");
			ps.setString(2, priscription.getDrug());
			ps.setString(3, priscription.getMrp());
			ps.setString(4, priscription.getPurchase_price());
			ps.setString(5, priscription.getSale_price());
			ps.setString(6, "2");
			ps.setInt(7, categoryID);
			ps.setString(8, added_date);
			result=ps.executeUpdate();
			
		} catch (Exception e) {

		    e.printStackTrace();
		}
		
		return result;
	}

	public Priscription getPrescriptionDetails(String selectedid) {

		  Priscription priscription=new Priscription();
		  
		  try {
		   String sql="select categeory,drug,strength,mrp,purchase_price,sale_price,expiry_date,pkg,batch_no,mfg,locations,specializations,genericname,risk,dosenoteid,molecules, presc_time,frequency,iswbd,caldose,type,days,qty,uom,rnote,dosefreq,component from apm_medicine_details where id="+selectedid+"";
		   PreparedStatement ps=connection.prepareStatement(sql);
		   ResultSet rs=ps.executeQuery();
		   while(rs.next()) {
		    
		    priscription.setCategoryid(rs.getString(1));
		    priscription.setDrug(rs.getString(2));
		    priscription.setStrength(""+rs.getDouble(3));
		    priscription.setMrp(rs.getString(4));
		    priscription.setPurchase_price(rs.getString(5));
		    priscription.setSale_price(rs.getString(6));
		    priscription.setExpiry_date(rs.getString(7));
		    priscription.setPkg(rs.getString(8));
		    priscription.setBatch_no(rs.getString(9));
		    priscription.setMfg(rs.getString(10));
		    priscription.setLocation(rs.getString(11));
		    priscription.setSpecialization(rs.getString(12));
		    priscription.setGenericname(rs.getString(13));
		    priscription.setRisk(rs.getString(14));
		    priscription.setDosagenote(rs.getString(15));
		    
		    String dosenotetxt = getDoseNoteTxt(rs.getInt(15));
		    priscription.setDosenotetxt(dosenotetxt);
		    
		    priscription.setMolecules(rs.getString(16));
		    priscription.setPriscriptiontime(rs.getString(17));
		    
		    priscription.setFrequency(rs.getString(18));
		    priscription.setIswbd(""+rs.getInt(19));
		    priscription.setCaldose(rs.getString(20));
		    if(rs.getString(21)==null){
		    	priscription.setPrisctypename("");
		    }else{
		    	priscription.setPrisctypename(rs.getString(21));
		    }
		    if(priscription.getMolecules()==null){
		     priscription.setMolecules("");
		    }
		    priscription.setId(Integer.parseInt(selectedid));
		    priscription.setDays(rs.getInt(22));
		    priscription.setDr_qty(rs.getString(23));
		    
		    priscription.setUom(rs.getString(24));
			priscription.setRemark(rs.getString(25));
			priscription.setDosefreq(rs.getString(26));
			priscription.setComponent(rs.getString(27));
		    
		   }
		   
		  } catch (Exception e) {

		     e.printStackTrace();
		     
		  }
		  
		  return priscription;
		 }
	
	private String getDoseNoteTxt(int id) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "select note  from apm_dosage_note where id = "+id+"";
		
		try{
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}

	public int updatePrescriptionDetails(Priscription priscription) {

		int result=0;
		
		try {
			
			String sql="update apm_medicine_details set categeory=?,drug=?,strength=?,"
					+ "mrp=?,purchase_price=?,sale_price=?,expiry_date=?,"
					+ "pkg=?,batch_no=?,mfg=?,locations=?,specializations=?,"
					+ "genericname=?,risk=?,dosenoteid=?,type=?,strength=?, "
					+ "frequency=?,caldose=?,iswbd=?,days=?,qty=?,uom=?,rnote=?,dosefreq=?,component=? "
					+ "where id="+priscription.getId()+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, priscription.getCategoryid());
			ps.setString(2, priscription.getDrug());
            ps.setString(3, priscription.getStrength());
            ps.setString(4, priscription.getMrp());
            ps.setString(5, priscription.getPurchase_price());
            ps.setString(6, priscription.getSale_price());
            ps.setString(7, priscription.getExpiry_date());
            ps.setString(8, priscription.getPkg());
            ps.setString(9, priscription.getBatch_no());
            ps.setString(10, priscription.getMfg());
            ps.setString(11, priscription.getLocation());
            ps.setString(12, priscription.getSpecialization());
            ps.setString(13, priscription.getGenericname());
            ps.setString(14, priscription.getRisk());
            ps.setString(15, priscription.getDosagenote());
            ps.setString(16, priscription.getPrisctypename());
            ps.setString(17, priscription.getStrength());
            ps.setString(18, priscription.getFrequency());
            ps.setString(19, priscription.getCaldose());
            ps.setString(20, priscription.getIswbd());
            ps.setInt(21, priscription.getDays());
            ps.setString(22, priscription.getDr_qty());
            
            ps.setString(23, priscription.getUom());
            ps.setString(24, priscription.getRemark());
            ps.setString(25, priscription.getDosefreq());
            ps.setString(26, priscription.getComponent());
			result=ps.executeUpdate();
			
		} catch (Exception e) {

		  e.printStackTrace();
		}
		
		return result;
	}

	public int deletePrescriptionDetails(String selectedid) {

		 int result=0;
		
		 try {
			
			 PreparedStatement ps=connection.prepareStatement("delete from apm_medicine_details where id=?");
			 ps.setString(1, selectedid);
			 result=ps.executeUpdate();
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
		
		return result;
	}

	public Priscription getPrescriptionDetailsByName(String drugname) {

		Priscription priscription=new Priscription();
		
		try {
			String sql="select categeory,drug,strength,mrp,purchase_price,sale_price,expiry_date,pkg,batch_no,mfg,id,component from apm_medicine_details where drug='"+drugname+"'";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				
				priscription.setCategoryid(rs.getString(1));
				priscription.setDrug(rs.getString(2));
				priscription.setStrength(rs.getString(3));
				priscription.setMrp(rs.getString(4));
				priscription.setPurchase_price(rs.getString(5));
				priscription.setSale_price(rs.getString(6));
				priscription.setExpiry_date(rs.getString(7));
				priscription.setPkg(rs.getString(8));
				priscription.setBatch_no(rs.getString(9));
				priscription.setMfg(rs.getString(10));
				priscription.setId(rs.getInt(11));
				priscription.setComponent(rs.getString(12));
				
			}
			
		} catch (Exception e) {

		   e.printStackTrace();
		   
		}
		
		return priscription;
		
	}

	public ArrayList<Master> getAllMedicineLocation(String selectedid) {

		ArrayList<Master> list= new ArrayList<Master>();
		try {
			String result="0";
			String sql="SELECT locations from apm_medicine_details where id="+selectedid+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				result=rs.getString(1);	
			}
			
			for(String str:result.split(",")){
				
				 int id=Integer.parseInt(str);
				 if(id>0){
					  Master master=new Master();
					  master.setId(id);
					  list.add(master);
				 }
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public ArrayList<Master> getAllMedicineSpecialization(String selectedid) {

		ArrayList<Master> list=new ArrayList<Master>();
		TreatmentTypeDAO treatmentTypeDAO=new JDBCTreatmentTypeDAO(connection);
		String result="0";
		try {
			String sql="SELECT specializations from apm_medicine_details where id="+selectedid+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				
				  result=rs.getString(1);
			}
			
			for(String str:result.split(",")){
				
				   int id=Integer.parseInt(str);
				   if(id>0){
					     Master master=new Master();
					     TreatmentType treatmentType= treatmentTypeDAO.getDepartmentCondition(id);
					     master.setId(id);
					     master.setName(treatmentType.getTreatmentName());
					     list.add(master);
				   }
			}
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return list;
	}

	public ArrayList<Priscription> getAllMedicineGenericList() {
		
		ArrayList<Priscription> list=new ArrayList<Priscription>();
		try {
			
			String sql="SELECT id,drug,genericname from apm_medicine_details;";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				
				Priscription priscription=new Priscription();
				priscription.setId(rs.getInt(1));
				priscription.setDrug(rs.getString(2));
				priscription.setGenericname(priscription.getDrug()+" ("+rs.getString(3)+")");
				
				list.add(priscription);
				 
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return list;
	}

	public int addToMedicineMaster(Product product,int catalogueid, String istemp) {
		
		int result=0;
		try {
               			
             PreparedStatement ps=connection.prepareStatement("insert into apm_medicine_details (categeory,drug,mrp,purchase_price,mfg,genericname,catalogueid,istempmed,addeduserId,addedDT,component) values (?,?,?,?,?,?,?,?,?,?,?)"); 
			 ps.setString(1,product.getCategory_id());
			 ps.setString(2, product.getProduct_name());
			 ps.setString(3, product.getMrp());
			 ps.setString(4, product.getPurchase_price());
			 ps.setString(5, product.getMfg());
			 ps.setString(6, product.getGeneric_name());
			 ps.setInt(7,catalogueid);
			 
			 ps.setString(8, istemp);
			 ps.setString(9, product.getUserid());
			 ps.setString(10,product.getTodate());
			 ps.setString(11,product.getComponent());
			 
			 result=ps.executeUpdate();
			 
			 if(result>0){
				 
				    ResultSet set=ps.getGeneratedKeys();
				    while(set.next()){
				    	
				    	  result=set.getInt(1);
				    }
			 }
			 
			 
			
		} catch (Exception e) {

		   e.printStackTrace();
		}
	
		return result;  
		
	}
	public Priscription getPrisc(String id){
		 Priscription prisc= new Priscription();
		
		 try{
		
			 String sql= "select dose,days,note,unit,dosegiventime from apm_medicine_details where id= "+id;
			 PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
		
			 int i=0;
			
				 while(rs.next()){
					 
				 prisc.setPriscdose(rs.getString(1));
				 prisc.setPriscdays(rs.getString(2));
				 prisc.setDosenotes(rs.getString(3));
				 prisc.setUnit(rs.getString(4));
				 prisc.setDosegiventime(rs.getString(5));
			 }
				 
			 
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 
		 
		 
		return prisc;
	}

	public String getComponentFromMedId(String string) {
		String res ="";
		try {
			String sql="SELECT component from apm_medicine_details where id="+string+"";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				res = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	
	
}
