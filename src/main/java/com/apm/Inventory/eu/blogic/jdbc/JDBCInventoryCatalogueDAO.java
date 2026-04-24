package com.apm.Inventory.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.apm.Inventory.eu.bi.InventoryCatalogueDAO;
import com.apm.Inventory.eu.entity.Product;
import com.apm.common.utils.DateTimeUtils;

public class JDBCInventoryCatalogueDAO implements InventoryCatalogueDAO{

	Connection connection;
    public JDBCInventoryCatalogueDAO(Connection connection) {
	    this.connection= connection;
    }
	
	public Product getProductDetails(String catalogueid) {
		
		Product product =new Product();
		
		try {
			String sql="SELECT id, categoryid, subcategoryid, product_name, mrp, purchase_price, sale_price, gst, datetime, location, lastmodified, genericname from inventory_catalogue where id="+catalogueid+" ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs =ps.executeQuery();
			while(rs.next()){
				
				 product.setId(rs.getInt(1));
				 product.setCategory_id(rs.getString(2));
				 product.setSubcategory_id(rs.getString(3));
				 product.setProduct_name(rs.getString(4));
				 product.setMrp(rs.getString(5));
				 product.setPurchase_price(rs.getString(6));
				 product.setSale_price(rs.getString(7));
				 product.setVat(rs.getString(8));
				 product.setDateTime(rs.getString(9));
				 product.setLocation(rs.getString(10));
				 product.setLastmodified(rs.getString(11));
				 product.setGenericname(rs.getString(12));
				 product.setCatalogueid(catalogueid);
				
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return product;
	}

	public ArrayList<Product> getAllProductList(String location) {

		ArrayList<Product> list= new ArrayList<Product>();
		try { 
			StringBuffer buffer= new StringBuffer();
			/*buffer.append("SELECT inventory_catalogue.id, inventory_catalogue.categoryid, subcategoryid, product_name, inventory_catalogue.mrp, purchase_price, sale_price, gst, datetime, inventory_catalogue.location, inventory_catalogue.lastmodified,inventory_catalogue.genericname,product_code,inventory_product.location ");
            buffer.append("from inventory_catalogue ");
			buffer.append("inner join inventory_product on inventory_product.catalogueid=inventory_catalogue.id ");
			if(!location.equals("0")){
				buffer.append("where inventory_product.location="+location+" ");
			}
			buffer.append("group by inventory_catalogue.id order by id desc");*/
			
			buffer.append("SELECT id, categoryid, subcategoryid, product_name, mrp, purchase_price, sale_price, gst, datetime, location, lastmodified, genericname,product_code from inventory_catalogue ");
			if(!location.equals("0")){
				buffer.append("where location="+location+" ");
			}
			buffer.append("order by id desc");

			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs =ps.executeQuery();
			while(rs.next()){
				 Product product =new Product();
				 product.setId(rs.getInt(1));
				 product.setCatalogueid(rs.getString(1));
				 product.setCategory_id(rs.getString(2));
				 product.setSubcategory_id(rs.getString(3));
				 product.setProduct_name(rs.getString(4));
				 product.setMrp(rs.getString(5));
				 product.setPurchase_price(rs.getString(6));
				 product.setSale_price(rs.getString(7));
				 product.setVat(rs.getString(8));
				 product.setDateTime(rs.getString(9));
				 product.setLocation(rs.getString(10));
				 product.setLastmodified(rs.getString(11));
				 product.setGenericname(rs.getString(12));
				 product.setPro_code(rs.getString(13));
					 String data=product.getProduct_name()+" - "+product.getGenericname()+"";
						if(product.getPro_code()!=null){
							if(!product.getPro_code().equals("")){
								data = data +" - "+product.getPro_code();
							}
						}
				 product.setData(data);
				 list.add(product);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return list;
	}

	public int saveNewCatalogue(Product product) {

		int result=0;
		try {
			StringBuffer buffer= new StringBuffer();
			buffer.append("insert into inventory_catalogue (categoryid, subcategoryid, product_name, mrp, purchase_price, sale_price, gst, datetime, location, lastmodified,genericname,pack,mfg,hsnno) ");
			buffer.append("values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?)");
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ps.setString(1, product.getCategory_id());
			ps.setString(2, product.getSubcategory_id());
			ps.setString(3, product.getProduct_name());
			ps.setString(4, product.getMrp());
			ps.setString(5, product.getPurchase_price());
			ps.setString(6, product.getSale_price());
			ps.setString(7, product.getVat());
			ps.setString(8, product.getDateTime());
			ps.setString(9, product.getLocation());
			ps.setString(10, product.getLastmodified());
			ps.setString(11, product.getGenericname());
			ps.setString(12, product.getPack());
			ps.setString(13, product.getMfg());
			ps.setString(14, product.getHsnno());
			result =ps.executeUpdate();
			if(result>0){
				 ResultSet rs =ps.getGeneratedKeys();
				 while(rs.next()){
					  result =rs.getInt(1);
				 }
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int updateCatalogueIdInProduct(int catalogueid, String product_name) {

		int res=0;
		try {
			 String sql="update inventory_product set catalogueid="+catalogueid+" where prodname=? ";
			 PreparedStatement ps=connection.prepareStatement(sql);
			 ps.setString(1, product_name);
			 res =ps.executeUpdate();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return res;
	}

	public int isExistThisName(String product_name) {

		int id=0;
		try {
			
			String sql="select id from inventory_catalogue where product_name='"+product_name+"' ";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs =ps.executeQuery();
			while(rs.next()){
				
				id= rs.getInt(1);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return id;
	}

	public String getSubcategoryId(String name ) {
		
		String res="0";
		try {
			String sql="select id from inventory_subcategory where name='"+name+"' ";
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

	public ArrayList<Product> getAllProductListItemWiseSale(String location) {
		ArrayList<Product> list= new ArrayList<Product>();
		try { 
			StringBuffer buffer= new StringBuffer();
			buffer.append("SELECT inventory_catalogue.id, inventory_catalogue.categoryid, subcategoryid, product_name, inventory_catalogue.mrp, purchase_price, sale_price, gst, datetime, inventory_catalogue.location, inventory_catalogue.lastmodified,inventory_catalogue.genericname,product_code,inventory_product.location ");
            buffer.append("from inventory_catalogue ");
			buffer.append("inner join inventory_product on inventory_product.catalogueid=inventory_catalogue.id ");
			if(!location.equals("0")){
				buffer.append("where inventory_product.location="+location+" ");
			}
			buffer.append("group by inventory_catalogue.id order by id desc");
			
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs =ps.executeQuery();
			while(rs.next()){
				 Product product =new Product();
				 product.setId(rs.getInt(1));
				 product.setCatalogueid(rs.getString(1));
				 product.setCategory_id(rs.getString(2));
				 product.setSubcategory_id(rs.getString(3));
				 product.setProduct_name(rs.getString(4));
				 product.setMrp(rs.getString(5));
				 product.setPurchase_price(rs.getString(6));
				 product.setSale_price(rs.getString(7));
				 product.setVat(rs.getString(8));
				 product.setDateTime(rs.getString(9));
				 product.setLocation(rs.getString(10));
				 product.setLastmodified(rs.getString(11));
				 product.setGenericname(rs.getString(12));
				 product.setPro_code(rs.getString(13));
				 list.add(product);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return list;
	}

	public ArrayList<Product> getProductListForIndentReplace(String location, String catalogueids) {


		ArrayList<Product> list= new ArrayList<Product>();
		try { 
			StringBuffer buffer= new StringBuffer();
			/*buffer.append("SELECT inventory_catalogue.id, inventory_catalogue.categoryid, subcategoryid, product_name, inventory_catalogue.mrp, purchase_price, sale_price, gst, datetime, inventory_catalogue.location, inventory_catalogue.lastmodified,inventory_catalogue.genericname,product_code,inventory_product.location ");
            buffer.append("from inventory_catalogue ");
			buffer.append("inner join inventory_product on inventory_product.catalogueid=inventory_catalogue.id ");
			if(!location.equals("0")){
				buffer.append("where inventory_product.location="+location+" ");
			}
			buffer.append("group by inventory_catalogue.id order by id desc");*/
			
			buffer.append("SELECT id, categoryid, subcategoryid, product_name, mrp, purchase_price, sale_price, gst, datetime, location, lastmodified, genericname,product_code from inventory_catalogue ");
			boolean flag =false;
			if(!location.equals("0")){
				flag = true;
				buffer.append("where location="+location+" ");
			}
			if(!catalogueids.equals("0")){
				if(flag){
					buffer.append("and id not in ("+catalogueids+") ");
				}else{
					buffer.append("where id not in ("+catalogueids+") ");
				}
				
			}
			buffer.append("order by id desc");

			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs =ps.executeQuery();
			while(rs.next()){
				 Product product =new Product();
				 product.setId(rs.getInt(1));
				 product.setCatalogueid(rs.getString(1));
				 product.setCategory_id(rs.getString(2));
				 product.setSubcategory_id(rs.getString(3));
				 product.setProduct_name(rs.getString(4));
				 product.setMrp(rs.getString(5));
				 product.setPurchase_price(rs.getString(6));
				 product.setSale_price(rs.getString(7));
				 product.setVat(rs.getString(8));
				 product.setDateTime(rs.getString(9));
				 product.setLocation(rs.getString(10));
				 product.setLastmodified(rs.getString(11));
				 product.setGenericname(rs.getString(12));
				 product.setPro_code(rs.getString(13));
					 String data=product.getProduct_name()+" - "+product.getGenericname()+"";
						if(product.getPro_code()!=null){
							if(!product.getPro_code().equals("")){
								data = data +" - "+product.getPro_code();
							}
						}
				 product.setData(data);
				 list.add(product);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return list;
	
	}

	@Override
	public ArrayList<Product> getAllProductListLMH(String location) {
		ArrayList<Product> list= new ArrayList<Product>();
		try { 
			StringBuffer buffer= new StringBuffer();
			
			buffer.append("SELECT id, categoryid, subcategoryid, product_name, mrp, purchase_price, sale_price, gst, datetime, location, lastmodified, genericname,product_code from inventory_catalogue ");
			if(!location.equals("0")){
				buffer.append("where location="+location+" ");
			}
			buffer.append("order by id desc");

			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs =ps.executeQuery();
			while(rs.next()){
				 Product product =new Product();
				 product.setId(rs.getInt(1));
				 product.setCatalogueid(rs.getString(1));
				 product.setCategory_id(rs.getString(2));
				 product.setSubcategory_id(rs.getString(3));
				 product.setProduct_name(rs.getString(4));
				 product.setMrp(rs.getString(5));
				 product.setPurchase_price(rs.getString(6));
				 product.setSale_price(rs.getString(7));
				 product.setVat(rs.getString(8));
				 product.setDateTime(rs.getString(9));
				 product.setLocation(rs.getString(10));
				 product.setLastmodified(rs.getString(11));
				 product.setGenericname(rs.getString(12));
				 product.setPro_code(rs.getString(13));
				 String data=product.getProduct_name();
				 if(!DateTimeUtils.isNull(product.getPro_code()).equals("")){
					 data = data +" - "+product.getPro_code();
				 }
				 String stock = getStockFromCatalogue(rs.getInt(1),rs.getString(10));
				 data = data +" - Avail. Stock( "+stock+" )";
				 product.setData(data);
				 list.add(product);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return list;
	}

	private String getStockFromCatalogue(int catalogueid, String location) {
		String res="0";
		try {
			//String sql="select stock from inventory_product where catalogueid="+catalogueid+" and location='"+location+"'";
			//for vspm
			String sql="select sum(stock) from inventory_product where catalogueid="+catalogueid+" and location='"+location+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				res = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
 	
	
}
