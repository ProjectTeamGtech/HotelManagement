package com.apm.Inventory.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.apm.DiaryManagement.eu.bi.ClientDAO;
import com.apm.DiaryManagement.eu.blogic.jdbc.JDBCClientDAO;
import com.apm.DiaryManagement.eu.entity.Client;
import com.apm.DiaryManagement.eu.entity.Priscription;
import com.apm.Emr.eu.bi.EmrDAO;
import com.apm.Emr.eu.blogic.jdbc.JDBCEmrDAO;
import com.apm.Inventory.eu.bi.IndentDAO;
import com.apm.Inventory.eu.bi.InventoryProductDAO;
import com.apm.Inventory.eu.entity.Procurement;
import com.apm.Inventory.eu.entity.Product;
import com.apm.Pharmacy.eu.bi.PharmacyDAO;
import com.apm.Pharmacy.eu.blogic.jdbc.JDBCPharmacyDAO;
import com.apm.Registration.eu.bi.UserProfileDAO;
import com.apm.Registration.eu.blogic.jdbc.JDBCUserProfileDAO;
import com.apm.Registration.eu.entity.UserProfile;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;

public class JDBCIndentDAO implements IndentDAO{
	Connection connection;

	public JDBCIndentDAO(Connection connection) {

		this.connection = connection;
	}
	public ArrayList<Product> getAscStockWiseProductList(double req_stock, Product prod) {
		ArrayList<Product> arrayList = new ArrayList<Product>();
		InventoryProductDAO inventoryProductDAO =new JDBCInventoryProductDAO(connection);
		try {
			/*String sql = "select id, prodname, stock, expiry_date, batch_no, location, hsnno from inventory_product where location=32 and stock>0 and prodname like ('"
					+ prod.getProduct_name() + "') order by (stock+0) asc";*/
			String sql = "select id, prodname, stock, expiry_date, batch_no, location, hsnno from inventory_product where location='"+prod.getWarehouse_id()+"' and stock>0 and catalogueid='"+prod.getCatalogueid()+"' order by (stock+0) asc";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			double  total = 0.0;
			double reqqty = req_stock;
			double temp=0.0;
			while (rs.next()) {
				int isinadjustment = inventoryProductDAO.checkProductInAdjustment(rs.getString(1));
				if(isinadjustment==1){
					continue;
				}
				double avail_stock = (rs.getDouble(3));
				Product product = new Product();
				product.setAvail_stock(avail_stock);
				product.setProduct_id("" + rs.getInt(1));
				product.setProduct_name(rs.getString(2));
				product.setStock(rs.getString(3));
				product.setExpiry_date(rs.getString(4));
				product.setBatch_no(rs.getString(5));
				product.setLocation(rs.getString(6));
				product.setHsnno(rs.getString(7));
				String fromlocation = pharmacyLocationNameByID(rs.getString(6));
				product.setFromlocation(fromlocation);
				product.setReqqty("" + req_stock);
				product.setCatalogueid(prod.getCatalogueid());
				
				Product product2=inventoryProductDAO.getProductCatalogueDetails(prod.getCatalogueid());
				product.setUom(product2.getUom());
				product.setPro_code(product2.getPro_code());
				/*if (avail_stock >= req_stock) {
					arrayList.add(product);
				}*/
				total = total + avail_stock;
				product.setTotalbalance(total);
				Product p1=getrequestedApproveQuantity(prod.getCatalogueid(),prod.getParentid());
				product.setReq_limit_qty(p1.getReq_limit_qty());
				product.setStatus(p1.getStatus());
				product.setParentid(prod.getParentid());
				double kaalmegh_qty_limit=inventoryProductDAO.getqtyProductLimit(prod.getCatalogueid(),"");
				double delivrqty=inventoryProductDAO.getdeliverqty(prod.getCatalogueid(),"");
				kaalmegh_qty_limit=kaalmegh_qty_limit-delivrqty;
				if(DateTimeUtils.isNull(product.getStatus()).equals("1")) {
				  	//double totalqty=kaalmegh_qty_limit+Double.parseDouble(p1.getReq_limit_qty());
				  	double extratotalqty=Double.parseDouble(p1.getReq_limit_qty())+req_stock;
				  	product.setExtra_limit_qty(extratotalqty);
				  	product.setLimit_qty(kaalmegh_qty_limit);
				}else {
				product.setLimit_qty(kaalmegh_qty_limit);
				}
				if(total>=req_stock){
					temp = reqqty-avail_stock;
					if(temp>=0){
						product.setStockqty(avail_stock);
						reqqty = temp;
					}else{
						product.setStockqty(reqqty);
					}
					arrayList.add(product);
					break;
				}else{
					temp = reqqty-avail_stock;
					if(temp>=0){
						product.setStockqty(avail_stock);
						reqqty = temp;
					}else{
						product.setStockqty(reqqty);
					}
					arrayList.add(product);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}
	private Product getrequestedApproveQuantity(String catalogueid,String parentid) {
		Product product = new Product();
		int CurrentYear = Calendar.getInstance().get(Calendar.YEAR);
		int CurrentMonth = (Calendar.getInstance().get(Calendar.MONTH)+1);
		int lastdate=DateTimeUtils.getlastmonthdate(CurrentMonth, (CurrentYear));
		String fromdate=CurrentYear+"-"+"0"+CurrentMonth+"-"+"0"+1;
		String todate=CurrentYear+"-"+"0"+CurrentMonth+"-"+lastdate;
		try {
			//  get stock catlogue id 23 oct 2017
			String sql = "select req_limit_qty,approve_qty_status,req_qty_date_time from inventory_transfer_log where req_qty_date_time between '"+fromdate+"' and '"+todate+"' and catlogueid='"+catalogueid+"' and parentid='"+parentid+"' ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				product.setReq_limit_qty(rs.getString(1));
				product.setStatus(rs.getString(2));
				product.setRequest_date(rs.getString(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
		
	}
	public String pharmacyLocationNameByID(String id) {
		String name = "";
		try {
			String query = "select name from apm_condition where id=" + id;
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				name = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}
	public String getCatIdProdId(String product_id) {
		String catid="0";
		try {
			String sql="select catalogueid from inventory_product where id='"+product_id+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				catid=rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return catid;
	}
	public ArrayList<Product> getLocationWiseStock(Product prod, Product product) {
		ArrayList<Product> arrayList = new ArrayList<Product>();
		try {
			String sql = "select sum(stock),location from inventory_product where stock>0 and location!='"+prod.getWarehouse_id()+"' and catalogueid='"+prod.getCatalogueid()+"' and location!='"+product.getLocation()+"' group by location";
			// String sql ="select sum(stock),location from inventory_product
			// where stock>0 and location!=1 and
			// prodname='"+prod.getProduct_name()+"' and
			// location!="+product.getLocation()+" group by location";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int qty = rs.getInt(1);
				Product product1 = new Product();
				product1.setAvail_stock(qty);
				String location = rs.getString(2);
				product1.setFromlocation(rs.getString(2));
				product1.setLocation(rs.getString(2));
				arrayList.add(product1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}
	
	public ArrayList<Product> getProductLimitedFromOtherLocation(Product prod, String location) {
		ArrayList<Product> arrayList = new ArrayList<Product>();
		try {
			String sql = "select id, prodname, stock, expiry_date, batch_no, location, hsnno from inventory_product where location='"
					+ location + "' and stock>0 and catalogueid='"+prod.getCatalogueid()+"'";
			// String sql ="select id, prodname, stock, expiry_date, batch_no,
			// location, hsnno from inventory_product where
			// location="+location+" and stock>0 and prodname
			// ='"+prod.getProduct_name()+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			double  total = 0.0;
			double reqqty =Double.parseDouble(prod.getStock()); 
			double temp=0;
			while (rs.next()) {
				int avail_stock = Integer.parseInt(rs.getString(3));
				Product product = new Product();
				product.setAvail_stock(avail_stock);
				product.setProduct_id("" + rs.getInt(1));
				product.setProduct_name(rs.getString(2));
				product.setStock(rs.getString(3));
				product.setExpiry_date(rs.getString(4));
				product.setBatch_no(rs.getString(5));
				product.setLocation(rs.getString(6));
				product.setHsnno(rs.getString(7));
				product.setReqqty((prod.getStock()));
				String fromlocation = pharmacyLocationNameByID(rs.getString(6));
				product.setFromlocation(fromlocation);
				total = total + avail_stock;
				product.setTotalbalance(total);
				product.setCatalogueid(prod.getCatalogueid());
				if(total>=Double.parseDouble(prod.getStock())){
					temp = reqqty-avail_stock;
					if(temp>=0){
						product.setStockqty(avail_stock);
						reqqty = temp;
					}else{
						product.setStockqty(reqqty);
					}
					arrayList.add(product);
					break;
				}else{
					temp = reqqty-avail_stock;
					if(temp>=0){
						product.setStockqty(avail_stock);
						reqqty = temp;
					}else{
						product.setStockqty(reqqty);
					}
					arrayList.add(product);
				}
				
				
				//arrayList.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}
	// new methdod for request and transfer @ 

		public int requestProductTemporarySave(int prodid, String location, String qty, String parentid, String prod_name,
				String transfer_date, int count5, String catid) {
			int result = 0;
			try {
				String sql = "insert into inventory_request_temp_log (parentid, old_prodid, new_prodid, qty,location,prod_name,transfer_date,trans_count,catlogueid) values (?,?,?,?,?,?,?,?,?)";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, parentid);
				preparedStatement.setString(2, "" + prodid);
				preparedStatement.setString(3, "0");
				preparedStatement.setString(4, qty);
				preparedStatement.setString(5, location);
				preparedStatement.setString(6, prod_name);
				preparedStatement.setString(7, transfer_date);
				preparedStatement.setString(8, "" + count5);
				preparedStatement.setString(9, catid);
				result = preparedStatement.executeUpdate();
				if(result>0)
				{
					ResultSet resultSet2=preparedStatement.getGeneratedKeys();
					while(resultSet2.next()){
						result= resultSet2.getInt(1);	
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}
		
		public ArrayList<String> getChildProductName(String parentid) {
			ArrayList<String> arrayList = new ArrayList<String>();
			try {
				String sql = "select catlogueid from inventory_transfer_log where parentid='" + parentid
						+ "' and status='0'";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					String prod_name = rs.getString(1);
					arrayList.add(prod_name);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return arrayList;
		}
		public double getReqQtyFrmTemp(String prod_name, String parentid) {
			double qty = 0;
			try {
				// String sql = "select sum(qty) from inventory_request_temp_log
				// where parentid="+parentid+" and prod_name='"+prod_name+"' group
				// by prod_name;";
				String sql = "select sum(qty) from inventory_request_temp_log where parentid=" + parentid
						+ " and  catlogueid='"+prod_name+"'  group by catlogueid;";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					qty =(rs.getDouble(1));
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return qty;
		}

		public Product getReqQtyFrmAct(String parentid, String prod_name) {
			Product product = new Product();
			try {
				// String sql ="select id,qty from inventory_transfer_log where
				// prod_name='"+prod_name+"' and parentid="+parentid+"";
				String sql = "select id,qty,approve_qty_status,req_limit_qty from inventory_transfer_log where catlogueid='"+prod_name+"'  and parentid=" + parentid + " and cancel_req='0'";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					product.setId(rs.getInt(1));
					product.setQty(rs.getDouble(2));
					product.setApprove_qty_status(rs.getString(3));
					product.setReq_limit_qty(rs.getString(4));
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return product;
		}
		public int updateChildTempTransferStatus(String prod_name, String parentid, int status) {
			int result = 0;
			try {
				// String sql = "update inventory_request_temp_log set
				// status="+status+" where parentid='"+parentid+"' and
				// prod_name='"+prod_name+"'";
				String sql = "update inventory_request_temp_log set status=" + status + " where parentid='" + parentid
						+ "' and catlogueid='"+prod_name+"'";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				result = preparedStatement.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}
		public double getAllAvailableStockByCatId(String catlogueid, String location) {
			double qty = 0;
			try {
				//  get stock catlogue id 23 oct 2017
				String sql = "select sum(stock) from inventory_product where stock>0 and location="+location+" and catalogueid='"+catlogueid+"'";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					qty = rs.getDouble(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return qty;
		}
		
		public String getProdIdCatId(String catalogueid) {
			String prodid="0";
			try {
				String sql="select id from inventory_product where catalogueid='"+catalogueid+"'";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					prodid=""+rs.getInt(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return prodid;
		}
		
		public ArrayList<Product> getChildTranfserData(String parentid) {
			ArrayList<Product> arrayList = new ArrayList<Product>();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			try {
				/*String sql = "select id, parentid, old_prodid, new_prodid, qty,comment,avail_qty,status,transfer_date,catlogueid,cancel_req, cancel_req_note from inventory_transfer_log where parentid="
						+ parentid + "";*/
				String sql = "select id, parentid, old_prodid, new_prodid, qty,comment,avail_qty,status,transfer_date,"
						+ "catlogueid,cancel_req, cancel_req_note,requested_qty,req_limit_qty,approve_qty_status "
						+ "from inventory_transfer_log where parentid="
					      + parentid + "";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				double total_amount = 0;
				double totolmrp = 0;
				Product product4 = inventoryProductDAO.getParentTransferData(parentid);
				while (rs.next()) {
					Product product = new Product();
					product.setId(rs.getInt(1));
					product.setChildid("" + rs.getInt(1));
					product.setParentid(rs.getString(2));
					Product product2=inventoryProductDAO.getProductCatalogueDetails(rs.getString(10));
					Product product3 = inventoryProductDAO.getProduct(rs.getString(3));
					product.setHsnno(product2.getHsnno());
					product.setProduct_name(product2.getProduct_name());
					product.setBatch_no(product3.getBatch_no());
					product.setUom(product2.getUom());
					product.setPro_code(product2.getPro_code());
					String expirydate="";
					if(!DateTimeUtils.isNull(product3.getExpiry_date()).equals("")){
						expirydate = DateTimeUtils.getCommencingDate1(product3.getExpiry_date());
					}
					
					
					product.setExpiry_date(expirydate);
					product.setStock(rs.getString(5));
					String comment = "";
					if (rs.getString(6) != null) {
						comment = rs.getString(6);
					}
					product.setComment(comment);
					product.setSale_price(product2.getSale_price());
					product.setMrp(product2.getMrp());
					if (product2.getPack() == null) {
						product2.setPack("1");
					}
					if (product2.getPack().equals("")) {
						product2.setPack("1");
					}
					double amt = Double.parseDouble(product2.getPurchase_price()) / Integer.parseInt(product2.getPack());

					double amountno = Double.parseDouble(rs.getString(5)) * DateTimeUtils.convertToDouble(product.getSale_price());
					double amountmrp = Double.parseDouble(rs.getString(5)) * DateTimeUtils.convertToDouble(product.getMrp());
					total_amount = total_amount + amountno;
					totolmrp = totolmrp + amountmrp;
					product.setTotal_amount(total_amount);
					product.setTotolmrp(Math.round(totolmrp * 100.0) / 100.0);
					product.setAmountno(Math.round(amountno * 100.0) / 100.0);
					product.setAmountmrp(Math.round(amountmrp * 100.0) / 100.0);
					product.setPurchase_price(DateTimeUtils.changeFormat(amt));
					product.setProduct_id(rs.getString(3));
					double unit1 = Math.round(amt * 100.0) / 100.0;
					product.setUnit("" + unit1);
					
					product.setExpectedDate(product4.getExpectedDate());
					double avail_stock = getAllAvailableStockByCatId(rs.getString(10), product4.getLocation());
					product.setAvail_stock(avail_stock);
					String a_qty = "0";
					if (rs.getString(7) != null) {
						if (!rs.getString(7).equals("")) {
							a_qty = (rs.getString(7));
						}
					}
					product.setAvail_qty(Double.parseDouble(a_qty));

					double total_recived_qty = getReqQtyFrmTemp(rs.getString(10), parentid);

					if (total_recived_qty == Double.parseDouble(rs.getString(5))) {
						product.setStatus("1");
					} else if (total_recived_qty > Double.parseDouble(rs.getString(5))) {
						product.setStatus("1");
					} else if (total_recived_qty > 0) {
						product.setStatus("1");
					} else {
						product.setStatus("0");
					}
					String transfer_date = rs.getString(9);
					if (transfer_date != null) {
						if (!transfer_date.equals("")) {
							String[] data = transfer_date.split(" ");
							transfer_date = DateTimeUtils.getCommencingDate1(data[0]);
							transfer_date = transfer_date + " " + data[1];
						}
					} else {
						transfer_date = "";
					}
					product.setTransfer_date(transfer_date);
					product.setCatalogueid(rs.getString(10));
					
					
					String catlogueid = rs.getString(10);
					String totaltransferqt = getIndentTransferQuantity(parentid,catlogueid);
					product.setTotaltransferqt(totaltransferqt);
					product.setCancel_req(rs.getString(11));
					product.setCancel_req_note(rs.getString(12));
					product.setRequested_qty(rs.getString(13));
					product.setReq_limit_qty(rs.getString(14));
					product.setStatus(rs.getString(15));
					
					double store_avail_stock = getAllAvailableStockByCatId(rs.getString(10), product4.getWarehouse_id());
					product.setStore_avail_stock(store_avail_stock);
					
					arrayList.add(product);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return arrayList;
		}
		
		public String getIndentTransferQuantity(String parentid, String catlogueid) {
			String total ="";
			try {
				String sql ="select sum(qty) from inventory_request_temp_log where parentid='"+parentid+"' and catlogueid='"+catlogueid+"'";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					total = rs.getString(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return total;
		}
		
		public int getTotalReqTransfer(String parentid) {
			int count = 0;
			try {
				String sql = "select count(*) from inventory_transfer_log where parentid=" + parentid
						+ " and trans_count>0 group by trans_count";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					count = resultSet.getInt(1);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			return count;
		}
		public int transferIssueProduct(Product product, String comment1, String userId, String todate,String isfromcathlab) {
			int result = 0;
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			try {
				// minus stock
				double tqty=0.0;
				if(isfromcathlab.equals("0")){
					double oldstock =Double.parseDouble(product.getStock());
					double previousstock = oldstock;
					tqty = oldstock -Double.parseDouble((product.getTqty()));
					String query = "update inventory_product set stock='" + tqty + "' where id='" + product.getId() + "'";
					PreparedStatement statement = connection.prepareStatement(query);
					int res = statement.executeUpdate();
					
					//stock log
					String datetime = product.getDateTime();
					double qtyinout=1.0;
					String source ="Consume";
					double currentstock=tqty;
					//int changeqty=Integer.parseInt(product.getTqty());
					double changeqty=Double.parseDouble(product.getTqty());
					int reslog = inventoryProductDAO.insertIntoProductLog(product.getUserid(),datetime,product.getLocation(),qtyinout,""+product.getId(),product.getCatalogueid(),source,currentstock,previousstock,changeqty,"0","0",0,0,0,product.getParentid());
					
					boolean checkopningstockexist = pharmacyDAO.checkopeningstockexist(""+product.getId(),product.getDate());
					if(!checkopningstockexist){
						int r = pharmacyDAO.saveOpeningStock(""+product.getId(),product.getDate(),previousstock,"0");
					}
				}
				result = saveIssueTransfer(product, tqty,userId,todate,comment1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}
		private int saveIssueTransfer(Product product, double tqty, String userId, String todate, String comment1) {
			int result = 0;
			try {
				String sql = "insert into indent_patient_transfer_log "
						+ "(fromlocation, clientid, datetime, userid, prodid, qty, prodname, comment, "
						+ "previous_qty, later_qty,procedureid,hisuserid,parentid,isforuser,"
						+ "catalogueid,departid,vaccinationid) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, product.getLocation());
				preparedStatement.setString(2, product.getClientid());
				preparedStatement.setString(3, todate);
				preparedStatement.setString(4, userId);
				preparedStatement.setString(5, ""+product.getId());
				preparedStatement.setString(6, product.getTqty());
				preparedStatement.setString(7, product.getProduct_name());
				preparedStatement.setString(8, comment1);
				preparedStatement.setString(9, product.getStock());
				preparedStatement.setString(10, ""+tqty);
				preparedStatement.setString(11, product.getIssueproceid());
				preparedStatement.setString(12, product.getIssueuserid());
				preparedStatement.setString(13, product.getParentid());
				preparedStatement.setString(14, product.getHisuserfilter());
				preparedStatement.setString(15, product.getCatalogueid());
				preparedStatement.setString(16, product.getHisdepartmentfilter());
				preparedStatement.setString(17, ""+product.getVaccinationid());
				preparedStatement.setString(18, ""+product.getIssue_date());
				result = preparedStatement.executeUpdate();
				
				if(result>0){
					ResultSet rs=preparedStatement.getGeneratedKeys();
					while(rs.next()){
						result=rs.getInt(1);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}
		public int getCountPatientConsumptionReport(String fromdate, String todate, String searchtext) {
			int result=0;
			try {
				todate = todate +" "+"59:59:59";
				StringBuffer buffer = new StringBuffer();
				buffer.append("select count(*) from indent_patient_transfer_log ");
				buffer.append("inner join apm_patient on apm_patient.id = indent_patient_transfer_log.clientid ");
				buffer.append("where  datetime between '"+fromdate+"' and '"+todate+"'  ");
				if(searchtext!=null){
					buffer.append("and (firstname like('%"+searchtext+"%') or surname like('%"+searchtext+"%') or abrivationid like('%"+searchtext+"%')) ");
				}
				PreparedStatement ps = connection.prepareStatement(buffer.toString());
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					result= rs.getInt(1);
				}

			} catch (Exception e) {

				e.printStackTrace();
			}

			return result;
		}
		public ArrayList<Product> getPatientConsumptionReport(String fromdate, String todate, Pagination pagination, String searchtext,String hisdepartmentfilter) {
			ArrayList<Product> list = new ArrayList<Product>();
			try {
				ClientDAO clientDAO = new JDBCClientDAO(connection);
				PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
				InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
				StringBuffer buffer = new StringBuffer();
				todate = todate +" "+"59:59:59";
				
				buffer.append("select indent_patient_transfer_log.id, fromlocation, clientid, datetime, userid, prodid, qty, prodname, comment, ");
				buffer.append("previous_qty, later_qty from indent_patient_transfer_log ");
				buffer.append("inner join apm_patient on apm_patient.id = indent_patient_transfer_log.clientid ");
				buffer.append("where  datetime between '"+fromdate+"' and '"+todate+"'  ");
				if(searchtext!=null){
					buffer.append("and (firstname like('%"+searchtext+"%') or surname like('%"+searchtext+"%') or abrivationid like('%"+searchtext+"%')) ");
				}
				if(!hisdepartmentfilter.equals("0")){
				    buffer.append("and fromlocation='"+hisdepartmentfilter+"' ");
				}
				buffer.append("order by indent_patient_transfer_log.id desc ");
				String sql = pagination.getSQLQuery(buffer.toString());
				PreparedStatement ps = connection.prepareStatement(sql);
				double total =0;
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					Product product = new Product();
					product.setId(rs.getInt(1));
					String[] data = rs.getString(4).split(" ");
					String date = DateTimeUtils.getCommencingDate1(data[0]);
					product.setDateTime(date + " " + data[1]);
					Client client = clientDAO.getClientDetails(rs.getString(3));
					product.setClientname(client.getTitle() + " " + client.getFirstName() + " " + client.getLastName());
					product.setUserid(rs.getString(5));
					product.setTqty(rs.getString(7));
					product.setProduct_name(rs.getString(8));
					product.setMobile(client.getMobNo());
					product.setAbrivationid(client.getAbrivationid());
					String isipdid = pharmacyDAO.getIpdIdFromClientID(rs.getInt(3));
					if (isipdid.equals("0")) {
						product.setClienttype("OPD");
					} else {
						product.setClienttype("IPD");
					}
					if (client.getTypeName() != null) {
						if (!client.getTypeName().equals("0")) {
							product.setTpname("YES");
						} else {
							product.setTpname("NO");
						}
					} else {
						product.setTpname("NO");
					}
					Product product2 = inventoryProductDAO.getProduct(rs.getString(6));
					if (product2.getPack() == null) {
						product2.setPack("1");
					}
					if (product2.getPack().equals("")) {
						product2.setPack("1");
					}
					double amt = Double.parseDouble(product2.getSale_price()) ;
					//double amountno = Integer.parseInt(rs.getString(7)) * amt;
					double amountno =Double.parseDouble(rs.getString(7)) * amt;
					product.setSale_price(""+Math.round(amt * 100.0) / 100.0);
					product.setTotal_amount(Math.round(amountno * 100.0) / 100.0);
					total = total + amountno;
					product.setTotal(DateTimeUtils.changeFormat(Math.round(total * 100.0) / 100.0));
					
					product.setBatch_no(product2.getBatch_no());
					String expirydate="";
					if(product2.getExpiry_date()!=null){
						if(!product2.getExpiry_date().equals("")){
							expirydate = DateTimeUtils.getCommencingDate1(product2.getExpiry_date());
						}
					}
					product.setPurchase_price(product2.getPurchase_price());
					product.setMrp(product2.getMrp());
					product.setVat(product2.getVat());
					product.setExpiry_date(expirydate);
					list.add(product);
				}

			} catch (Exception e) {

				e.printStackTrace();
			}

			return list;
		}
		public boolean checkLocationInWarehouseid(String loc) {
			boolean flag = false;
			try {
				String sql = "select id from inventory_warehouse where id='"+loc+"' and isindent=1";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					flag = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return flag;
		}
		public String getWarehouseNameFromId(String string) {
			String name = "";
			try {
				String sql = "select name from inventory_warehouse where id='"+string+"'";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					name = resultSet.getString(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return name;
		}
		public int cancelRequestedEntry(String id,String delete_reason, String userid, String dateTime) {
			int res =0;
			try {
				String sql ="update inventory_transfer_log set cancel_req='1', cancel_req_note='"+delete_reason+"',cancel_req_dt='"+dateTime+"',cancel_req_userid='"+userid+"' where id='"+id+"'";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				res = preparedStatement.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return res;
		}
		public int getCountIndentTransferLog(String fromdate, String todate, String location1, String filter_status,
				String location_filter, String searhText, String userwise, String transferedlocation) {
			//  28 sep 2017 pagination code
			int result =0;
			try {
				StringBuffer buffer = new StringBuffer();
				buffer.append("select count(*) from inventory_parent_transfer_log ");
				if(filter_status.equals("0")){
					buffer.append("inner join inventory_request_temp_log on inventory_request_temp_log.parentid = inventory_parent_transfer_log.id ");
				}
				buffer.append("where request_date between '" + fromdate + "' and '" + todate + "' and deleted=0 ");
				buffer.append("and req_or_trans ='" + filter_status + "' ");
				
				if (searhText != null) {
					buffer.append("and inventory_parent_transfer_log.id ='" + searhText + "' ");
				}
				
				if (!location_filter.equals("0")) {
						if (filter_status.equals("0")) {
							buffer.append("and from_location ='" + location_filter + "' ");
						} else {
							buffer.append("and to_location ='" + location_filter + "' ");
						}
				}
				if(userwise!=null){
					buffer.append("and inventory_parent_transfer_log.userid='"+userwise+"' ");
				}
				
				//  20 NOV 2018 filter of from location
				if (filter_status.equals("0")) {
					if(!transferedlocation.equals("0")){
						buffer.append("and inventory_request_temp_log.location ='" + transferedlocation + "' ");
					}
				} else {
					if(!transferedlocation.equals("0")){
						buffer.append("and from_location ='" + transferedlocation + "' ");
					}
				}
				buffer.append("group by inventory_parent_transfer_log.id ");
				buffer.append(" order by request_date,time asc ");
				
				PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					result = result+1;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}
		public ArrayList<Product> getIndentTransferLog(String fromdate, String todate, String location1,
				String filter_status, String location_filter, String searhText, Pagination pagination, String userwise, String transferedlocation) {
			ArrayList<Product> arrayList = new ArrayList<Product>();
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			try {
				StringBuffer buffer = new StringBuffer();
				buffer.append("select inventory_parent_transfer_log.id, request_date, issued_date, from_location, to_location,time,r_status,req_or_trans, admin_notes, head_notes, check_availability_date, admin_aprove_date, head_aprove_date,expected_date,inventory_parent_transfer_log.userid,admin_approve_userid,indentreq,trans_indent_no,concat(apm_user.firstname,' ',apm_user.lastname) from inventory_parent_transfer_log ");
				
				if(filter_status.equals("0")){
					buffer.append("inner join inventory_request_temp_log on inventory_request_temp_log.parentid = inventory_parent_transfer_log.id ");
				}
				buffer.append("inner join apm_user on inventory_parent_transfer_log.userid = apm_user.userid ");
				buffer.append("where request_date between '" + fromdate + "' and '" + todate + "' and deleted=0 ");
				if (!filter_status.equals("10")) {
					buffer.append("and req_or_trans ='" + filter_status + "' ");
				}
				if (searhText != null) {
					buffer.append("and inventory_parent_transfer_log.id ='" + searhText + "' ");
				}

				if (!location_filter.equals("0")) {
					if (filter_status.equals("0")) {
						buffer.append("and from_location ='" + location_filter + "' ");
					} else {
						buffer.append("and to_location ='" + location_filter + "' ");
					}
				}
				if(userwise!=null){
					buffer.append("and inventory_parent_transfer_log.userid='"+userwise+"' ");
				}
				
				//  20 NOV 2018 filter of from location
				if (filter_status.equals("0")) {
					if(!transferedlocation.equals("0")){
						buffer.append("and inventory_request_temp_log.location ='" + transferedlocation + "' ");
					}
				} else {
					if(!transferedlocation.equals("0")){
						buffer.append("and from_location ='" + transferedlocation + "' ");
					}
				}
				
				buffer.append("group by inventory_parent_transfer_log.id ");
				buffer.append(" order by request_date,time asc ");
				
				String sql = pagination.getSQLQuery(buffer.toString());
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					Product product = new Product();
					product.setParentid("" + rs.getInt(1));
					String request_date = DateTimeUtils.getCommencingDate1(rs.getString(2));
					String issued_date = DateTimeUtils.getCommencingDate1(rs.getString(3));
					String time = rs.getString(6);
					product.setRequest_date(request_date + " " + time);
					product.setIssued_date(issued_date + " " + time);
					// product.setRequest_date(rs.getString(2));
					// product.setIssued_date(rs.getString(3));
					String fromlocation = pharmacyLocationNameByID(rs.getString(4));
					String tolocation = pharmacyLocationNameByID(rs.getString(5));
					product.setStatus(rs.getString(7));
					product.setFrom_location(fromlocation);
					product.setTo_location(tolocation);
					product.setReq_or_transfer(rs.getString(8));
					String adminnotes = "", headnotes = "", check_availabity_date = "", admin_aprove_date = "",
							head_aprove_date = "";
					if (rs.getString(9) != null) {
						adminnotes = rs.getString(9);
					}
					if (rs.getString(10) != null) {
						headnotes = rs.getString(10);
					}
					if (rs.getString(11) != null) {
						check_availabity_date = rs.getString(11);
					}
					if (rs.getString(12) != null) {
						admin_aprove_date = rs.getString(12);
						String[] data = admin_aprove_date.split(" ");
						String date = DateTimeUtils.getCommencingDate1(data[0]);
						admin_aprove_date = date + " " + data[1];
					}
					if (rs.getString(13) != null) {
						head_aprove_date = rs.getString(13);
						String[] data = head_aprove_date.split(" ");
						String date = DateTimeUtils.getCommencingDate1(data[0]);
						head_aprove_date = date + " " + data[1];
					}
					product.setAdmin_notes(adminnotes);
					product.setHead_notes(headnotes);
					product.setCheck_availability_date(check_availabity_date);
					product.setAdmin_aprove_date(admin_aprove_date);
					product.setHead_aprove_date(head_aprove_date);
					product.setExpectedDate(rs.getString(14));

					Priscription priscription = pharmacyDAO.getPharacyUsrLInfo(rs.getString(15));

					if (priscription.getFullname() != null) {
						product.setUserid(priscription.getFullname());
					} else {
						product.setUserid(rs.getString(19));
					}

					product.setAdmin_approve_userid(rs.getString(16));
					product.setIndent(rs.getInt(17));
					product.setTransfer_indentno(rs.getString(18));
					ArrayList<Product> requestedmedicineList = new ArrayList<Product>();
					ArrayList<Product> checkmedicinelist = new ArrayList<Product>();
					if(filter_status.equals("0")){
						requestedmedicineList = inventoryProductDAO.getChildTranfserData(product.getParentid());
						checkmedicinelist = inventoryProductDAO.getChildRequestedTempData(product.getParentid());
					}else{
						checkmedicinelist = inventoryProductDAO.getChildTranfserData(product.getParentid());
					}
					
					product.setCheckmedicinelist(checkmedicinelist);
					product.setRequestedmedicineList(requestedmedicineList);
					arrayList.add(product);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return arrayList;
		}
		
		public ArrayList<Product> getIndentLog(String fromdate, String todate, String location1, String filter_status,
				String location_filter, String searhText, Pagination pagination, String userwise) {
			ArrayList<Product> arrayList = new ArrayList<Product>();
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			try {
				StringBuffer buffer = new StringBuffer();
				buffer.append("select inventory_parent_transfer_log.id, request_date, issued_date, from_location, to_location,time,r_status,req_or_trans, admin_notes, head_notes, check_availability_date, admin_aprove_date, head_aprove_date,expected_date,inventory_parent_transfer_log.userid,admin_approve_userid,indentreq,trans_indent_no,concat(apm_user.firstname,' ',apm_user.lastname) from inventory_parent_transfer_log ");
				buffer.append("inner join apm_user on inventory_parent_transfer_log.userid = apm_user.userid ");
				/*if(filter_status.equals("0")){
					buffer.append("inner join inventory_request_temp_log on inventory_request_temp_log.parentid = inventory_parent_transfer_log.id ");
				}*/
				
				buffer.append("where request_date between '" + fromdate + "' and '" + todate + "' and deleted=0 ");
				/*if (!filter_status.equals("10")) {
					buffer.append("and req_or_trans ='" + filter_status + "' ");
				}*/
				if (searhText != null) {
					buffer.append("and inventory_parent_transfer_log.id ='" + searhText + "' ");
				}

				if(userwise!=null){
					buffer.append("and inventory_parent_transfer_log.userid='"+userwise+"' ");
				}
				if (filter_status != null) {
					buffer.append("and r_status ='" + filter_status + "' ");
				}
				buffer.append("and req_or_trans =0 ");
				
				if (!location_filter.equals("0")) {
					if (filter_status.equals("0")) {
						buffer.append("and from_location ='" + location_filter + "' ");
					} else {
						buffer.append("and (to_location ='" + location_filter + "' or warehouse_id in (" + location_filter + ")) ");
					}
				}
				
				buffer.append(" order by request_date,time asc ");
			
				/*
				 * if(!location_filter.equals("10")){
				 * if(location_filter.equals("0")){
				 * 
				 * }else if(location_filter.equals("1")){
				 * buffer.append("and warehouseid ='"+filter_status+"' "); }else
				 * if(location_filter.equals("2")){
				 * buffer.append("and warehouseid='"+filter_status+"' "); } }
				 */
				String sql = pagination.getSQLQuery(buffer.toString());
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					Product product = new Product();
					product.setParentid("" + rs.getInt(1));
					String request_date = DateTimeUtils.getCommencingDate1(rs.getString(2));
					String issued_date = DateTimeUtils.getCommencingDate1(rs.getString(3));
					String time = rs.getString(6);
					product.setRequest_date(request_date + " " + time);
					product.setIssued_date(issued_date + " " + time);
					// product.setRequest_date(rs.getString(2));
					// product.setIssued_date(rs.getString(3));
					String fromlocation = pharmacyLocationNameByID(rs.getString(4));
					String tolocation = pharmacyLocationNameByID(rs.getString(5));
					product.setStatus(rs.getString(7));
					product.setFrom_location(fromlocation);
					product.setTo_location(tolocation);
					product.setReq_or_transfer(rs.getString(8));
					String adminnotes = "", headnotes = "", check_availabity_date = "", admin_aprove_date = "",
							head_aprove_date = "";
					if (rs.getString(9) != null) {
						adminnotes = rs.getString(9);
					}
					if (rs.getString(10) != null) {
						headnotes = rs.getString(10);
					}
					if (rs.getString(11) != null) {
						check_availabity_date = rs.getString(11);
					}
					if (rs.getString(12) != null) {
						admin_aprove_date = rs.getString(12);
						String[] data = admin_aprove_date.split(" ");
						String date = DateTimeUtils.getCommencingDate1(data[0]);
						admin_aprove_date = date + " " + data[1];
					}
					if (rs.getString(13) != null) {
						head_aprove_date = rs.getString(13);
						String[] data = head_aprove_date.split(" ");
						String date = DateTimeUtils.getCommencingDate1(data[0]);
						head_aprove_date = date + " " + data[1];
					}
					product.setAdmin_notes(adminnotes);
					product.setHead_notes(headnotes);
					product.setCheck_availability_date(check_availabity_date);
					product.setAdmin_aprove_date(admin_aprove_date);
					product.setHead_aprove_date(head_aprove_date);
					product.setExpectedDate(rs.getString(14));

					Priscription priscription = pharmacyDAO.getPharacyUsrLInfo(rs.getString(15));

					if (priscription.getFullname() != null) {
						product.setUserid(priscription.getFullname());
					} else {
						product.setUserid(rs.getString(19));
					}

					product.setAdmin_approve_userid(rs.getString(16));
					product.setIndent(rs.getInt(17));
					product.setTransfer_indentno(rs.getString(18));
					ArrayList<Product> requestedmedicineList = inventoryProductDAO.getChildTranfserData(product.getParentid());
					ArrayList<Product> checkmedicinelist = inventoryProductDAO.getChildRequestedTempData(product.getParentid());
					product.setCheckmedicinelist(checkmedicinelist);
					product.setRequestedmedicineList(requestedmedicineList);
					arrayList.add(product);

				}
			} catch (Exception e) {
				e.printStackTrace();
			
			}
			return arrayList;
		
}
		public int getNewCountIndentTransferLog(String fromdate, String todate, String location1, String filter_status,
				String location_filter, String searhText, String userwise) {
			//  28 sep 2017 pagination code
			int result =0;
			try {
				StringBuffer buffer = new StringBuffer();
				
				buffer.append("select count(*) from inventory_parent_transfer_log ");
				
				/*if(filter_status.equals("0")){
					buffer.append("inner join inventory_request_temp_log on inventory_request_temp_log.parentid = inventory_parent_transfer_log.id ");
				}*/
				
				buffer.append("where request_date between '" + fromdate + "' and '" + todate + "' and deleted=0 ");
				/*if (!filter_status.equals("10")) {
					buffer.append("and req_or_trans ='" + filter_status + "' ");
				}*/
				if (searhText != null) {
					buffer.append("and inventory_parent_transfer_log.id ='" + searhText + "' ");
				}

				
				if(userwise!=null){
					buffer.append("and inventory_parent_transfer_log.userid='"+userwise+"' ");
				}
				if (filter_status != null) {
					buffer.append("and r_status ='" + filter_status + "' ");
				}
				buffer.append("and req_or_trans =0 ");
				
				if (!location_filter.equals("0")) {
					if (filter_status.equals("0")) {
						buffer.append("and from_location ='" + location_filter + "' ");
					} else {
						buffer.append("and (to_location ='" + location_filter + "' or warehouse_id in (" + location_filter + ")) ");
					}
				}
				
				buffer.append(" order by request_date,time asc ");
				
				PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					result = rs.getInt(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}
		public int getStockByProdId(int prodid) {
			int res =0;
			try {
				String sql = "select stock from inventory_product where id='"+prodid+"'";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					res = rs.getInt(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return res;
		}
		public int changeIndentRTTstatus(Procurement procurement) {
			int res = 0;
			try {
				StringBuffer buffer = new StringBuffer();
				buffer.append("select inventory_parent_transfer_log.id from inventory_parent_transfer_log ");
				buffer.append("inner join inventory_transfer_log on inventory_transfer_log.parentid = inventory_parent_transfer_log.id ");
				buffer.append("where catlogueid='"+procurement.getCatalogueid()+"' and req_or_trans='0' and (r_status='5' or r_status='6' or r_status='7') and warehouse_id='"+procurement.getLocation()+"'  ");
				PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					String sql="select id from inventory_procurement where gudreceipt='1' and id='"+procurement.getId()+"' ";
					PreparedStatement statement = connection.prepareStatement(sql);
					ResultSet  resultSet = statement.executeQuery();
					while (resultSet.next()) {
						String sql1 = "update inventory_parent_transfer_log set r_status=7, procurmentid='" + resultSet.getString(1)
								+ "' where id=" + rs.getString(1) + "";
						PreparedStatement statement1 = connection.prepareStatement(sql1);
						res = statement1.executeUpdate();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return res;
		}
		public int getIssueToProductSrNo() {
			int res =0;
			try {
				String sql ="select parentid from indent_patient_transfer_log order by (parentid+0) desc limit 1";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					res = rs.getInt(1)+1;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return res;
		}
		public int getCountUserConsumptionReport(String fromdate, String todate, String searchtext) {
			int result=0;
			try {
				todate = todate +" "+"59:59:59";
				StringBuffer buffer = new StringBuffer();
				buffer.append("select count(*) from indent_patient_transfer_log ");
				buffer.append("inner join apm_user on apm_user.id = indent_patient_transfer_log.hisuserid ");
				buffer.append("where  datetime between '"+fromdate+"' and '"+todate+"'  ");
				if(searchtext!=null){
					buffer.append("and (firstname like '%"+searchtext+"%' or lastname like '%"+searchtext+"%' or apm_user.userid like '%"+searchtext+"%') ");
				}
				PreparedStatement ps = connection.prepareStatement(buffer.toString());
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					result= rs.getInt(1);
				}

			} catch (Exception e) {

				e.printStackTrace();
			}
			return result;
		}
		public ArrayList<Product> getUserConsumptionReport(String fromdate, String todate, Pagination pagination,
				String searchtext) {
			ArrayList<Product> list = new ArrayList<Product>();
			try {
				PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
				UserProfileDAO userProfileDAO = new JDBCUserProfileDAO(connection);
				InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
				StringBuffer buffer = new StringBuffer();
				todate = todate +" "+"59:59:59";
				
				buffer.append("select indent_patient_transfer_log.id, fromlocation, clientid, datetime, indent_patient_transfer_log.userid, prodid, qty, prodname, comment, ");
				buffer.append("previous_qty, later_qty,hisuserid,parentid from indent_patient_transfer_log ");
				buffer.append("inner join apm_user on apm_user.id = indent_patient_transfer_log.hisuserid ");
				buffer.append("where  datetime between '"+fromdate+"' and '"+todate+"'  ");
				if(searchtext!=null){
					buffer.append("and (firstname like '%"+searchtext+"%' or lastname like '%"+searchtext+"%' or apm_user.userid like '%"+searchtext+"%') ");
				}
				buffer.append("order by indent_patient_transfer_log.id desc ");
				String sql = pagination.getSQLQuery(buffer.toString());
				PreparedStatement ps = connection.prepareStatement(sql);
				double total =0;
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					Product product = new Product();
					product.setId(rs.getInt(1));
					String[] data = rs.getString(4).split(" ");
					String date = DateTimeUtils.getCommencingDate1(data[0]);
					product.setDateTime(date + " " + data[1]);
					product.setParentid(rs.getString(13));
					UserProfile userProfile = userProfileDAO.getUserprofileDetails(rs.getInt(12));
					product.setClientname(userProfile.getFullname());
					product.setUserid(rs.getString(5));
					product.setTqty(rs.getString(7));
					product.setProduct_name(rs.getString(8));
					product.setMobile(userProfile.getMobile());
					
					Product product2 = inventoryProductDAO.getProduct(rs.getString(6));
					if (product2.getPack() == null) {
						product2.setPack("1");
					}
					if (product2.getPack().equals("")) {
						product2.setPack("1");
					}
					double amt =0;
					try {
						amt = Double.parseDouble(product2.getSale_price()) ;
					} catch (Exception e) {
						e.printStackTrace();
					}
					double amountno = rs.getInt(7) * amt;
					product.setSale_price(""+amt);
					product.setTotal_amount(Math.round(amountno * 100.0) / 100.0);
					total = total + amountno;
					product.setTotal(DateTimeUtils.changeFormat(Math.round(total * 100.0) / 100.0));
					
					product.setBatch_no(product2.getBatch_no());
					String expirydate="";
					if(product2.getExpiry_date()!=null){
						if(!product2.getExpiry_date().equals("")){
							expirydate = DateTimeUtils.getCommencingDate1(product2.getExpiry_date());
						}
					}
					product.setExpiry_date(expirydate);
					list.add(product);
				}

			} catch (Exception e) {

				e.printStackTrace();
			}

			return list;
		}
		public boolean checkIndentDelivered(String location1, String todate, String todate2) {
			boolean flag = false;
			try {
				todate2 = todate2+" "+"59:59:59";
				//String sql ="select * from inventory_parent_transfer_log where req_or_trans=0 and (r_status=3 or r_status=6) and from_location='"+location1+"' order by id desc limit 1";
				StringBuffer buffer = new StringBuffer();
				buffer.append("select * from inventory_parent_transfer_log ");
				buffer.append(" where req_or_trans=0 and (r_status=3 or r_status=6) ");
				buffer.append("and from_location='"+location1+"' ");
				buffer.append("and request_date between '"+todate+"' and '"+todate2+"' ");
				buffer.append("order by id desc limit 1 ");
				PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					flag =true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return flag;
		}
		public boolean checkIndentApproved(String location1, String todate, String todate2) {
			boolean flag = false;
			try {
				todate2 = todate2+" "+"59:59:59";
				//String sql ="select * from inventory_parent_transfer_log where req_or_trans=0 and r_status=1 and warehouse_id='"+location1+"' order by id desc limit 1";
				StringBuffer buffer = new StringBuffer();
				buffer.append("select * from inventory_parent_transfer_log ");
				buffer.append("where req_or_trans=0 and r_status=1 and warehouse_id='"+location1+"' ");
				buffer.append("and request_date between '"+todate+"' and '"+todate2+"' ");
				buffer.append("order by id desc limit 1 ");
				PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					flag =true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return flag;
		}
		public boolean checkIndentRequested(String location1, String fromdate1, String todate) {
			boolean flag = false;
			try {
				todate = todate+" "+"59:59:59";
				//String sql ="select * from inventory_parent_transfer_log where req_or_trans=0 and r_status=0 order by id desc limit 1";
				StringBuffer buffer = new StringBuffer();
				buffer.append("select * from inventory_parent_transfer_log ");
				buffer.append("where req_or_trans=0 and r_status=0  ");
				buffer.append("and request_date between '"+fromdate1+"' and '"+todate+"' ");
				buffer.append("order by id desc limit 1 ");
				PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					flag =true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return flag;
		}
		public int getCountUserPatientConsumptionReport(String fromdate, String todate, String searchtext, String hisdepartmentfilter) {
			int res=0;
			try {
				StringBuffer buffer = new StringBuffer();
				todate = todate +" "+"59:59:59";
				buffer.append("select count(*) ");
				buffer.append("from indent_patient_transfer_log ");
				buffer.append("inner join apm_condition on apm_condition.id = indent_patient_transfer_log.departid ");
				buffer.append("where  indent_patient_transfer_log.datetime between '"+fromdate+"' and '"+todate+"' and isforuser='2'  ");
				if(!hisdepartmentfilter.equals("0")){
					buffer.append("and departid='"+hisdepartmentfilter+"' ");
				}
				buffer.append("order by indent_patient_transfer_log.id desc ");
				PreparedStatement preparedStatement= connection.prepareStatement(buffer.toString());
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					res = rs.getInt(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return res;
		}
		public ArrayList<Product> getUserPatientConsumptionReport(String fromdate, String todate, Pagination pagination,
				String searchtext, String hisdepartmentfilter) {
			ArrayList<Product> list = new ArrayList<Product>();
			try {
				InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
				StringBuffer buffer = new StringBuffer();
				todate = todate +" "+"59:59:59";
				buffer.append("select indent_patient_transfer_log.id, fromlocation, clientid, indent_patient_transfer_log.datetime, indent_patient_transfer_log.userid, ");
				buffer.append("prodid, qty, prodname, comment,previous_qty, later_qty,isforuser,departid,apm_condition.name from indent_patient_transfer_log ");
				buffer.append("inner join apm_condition on apm_condition.id = indent_patient_transfer_log.departid ");
				buffer.append("where  indent_patient_transfer_log.datetime between '"+fromdate+"' and '"+todate+"' and isforuser='2'  ");
				if(!hisdepartmentfilter.equals("0")){
					buffer.append("and departid='"+hisdepartmentfilter+"' ");
				}
				buffer.append("order by indent_patient_transfer_log.id desc ");
				String sql = pagination.getSQLQuery(buffer.toString());
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				double total =0;
				while (rs.next()) {
					Product product = new Product();
					product.setId(rs.getInt(1));
					String[] data = rs.getString(4).split(" ");
					String date = DateTimeUtils.getCommencingDate1(data[0]);
					product.setDateTime(date + " " + data[1]);
					product.setClientname(rs.getString(14));
					product.setUserid(rs.getString(5));
					product.setTqty(rs.getString(7));
					product.setProduct_name(rs.getString(8));
					
					Product product2 = inventoryProductDAO.getProduct(rs.getString(6));
					if (product2.getPack() == null) {
						product2.setPack("1");
					}
					if (product2.getPack().equals("")) {
						product2.setPack("1");
					}
					double amt = Double.parseDouble(product2.getSale_price()) ;
					double amountno = Double.parseDouble(rs.getString(7)) * amt;
					product.setSale_price(""+Math.round(amt * 100.0) / 100.0);
					product.setTotal_amount(Math.round(amountno * 100.0) / 100.0);
					total = total + amountno;
					product.setTotal(DateTimeUtils.changeFormat(Math.round(total * 100.0) / 100.0));
					
					product.setBatch_no(product2.getBatch_no());
					String expirydate="";
					if(product2.getExpiry_date()!=null){
						if(!product2.getExpiry_date().equals("")){
							expirydate = DateTimeUtils.getCommencingDate1(product2.getExpiry_date());
						}
					}
					product.setExpiry_date(expirydate);
					product.setBalanceqty(rs.getString(11));
					list.add(product);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}
		public int saveParentCathlabTemplate(String cathtempprocedure, String cathtemplatename, String userId,
				String datetime, String comment1) {
			int result=0;
			try {
				String sql="insert into cathlab_template (template_name,procedure_id,userid,datetime,remark) values (?,?,?,?,?)";
				PreparedStatement ps=connection.prepareStatement(sql);
				ps.setString(1, cathtemplatename);
				ps.setString(2, cathtempprocedure);
				ps.setString(3, userId);
				ps.setString(4, datetime);
				ps.setString(5, comment1);
				result=ps.executeUpdate();
				if(result>0){
					ResultSet rs=ps.getGeneratedKeys();
					while(rs.next()){
						result=rs.getInt(1);
					}
				}
				
			} catch (Exception e) {

				e.printStackTrace();
			}
			
			return result;
		}
		public int saveChildCathlabTemplate(int prodid, String product_name, String tqty, String catalogueid,
				int parentid) {
			int result=0;
			try {
				String sql="insert into cathlab_template_child (parentid,qty,prodid,catlogueid,productname) values (?,?,?,?,?)";
				PreparedStatement ps=connection.prepareStatement(sql);
				ps.setString(1, ""+parentid);
				ps.setString(2, ""+tqty);
				ps.setString(3, ""+prodid);
				ps.setString(4, catalogueid);
				ps.setString(5, product_name);
				result=ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}
		public ArrayList<Product> getCathlabTemplateList(String string) {
			ArrayList<Product> arrayList = new ArrayList<Product>();
			try {
				StringBuffer buffer = new StringBuffer();
				buffer.append("select cathlab_template.id,apm_newchargetype.name,template_name from cathlab_template ");
				buffer.append("inner join apm_newchargetype on apm_newchargetype.id = cathlab_template.procedure_id ");
				buffer.append("where cathlab_template.isdeleted='"+string+"' ");
				PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					Product product = new Product();
					product.setId(rs.getInt(1));
					String data = rs.getString(3)+" - "+rs.getString(2);
					product.setName(data);
					arrayList.add(product);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return arrayList;
		}
		public int savePatientTranferlog(String isfromcathlab, String userId, String datetime,String tempid,String comment) {
			int result=0;
			try {
				String sql="insert into indent_parent_patient_transfer_log (given_userid,given_date,iscathlab,templateid,status,remarks) values (?,?,?,?,?,?)";
				PreparedStatement ps=connection.prepareStatement(sql);
				ps.setString(1, userId);
				ps.setString(2, datetime);
				ps.setString(3, isfromcathlab);
				ps.setString(4, tempid);
				ps.setString(5, "0");
				ps.setString(6, comment);
				result=ps.executeUpdate();
				if(result>0){
					ResultSet rs=ps.getGeneratedKeys();
					while(rs.next()){
						result=rs.getInt(1);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return result;
		}
		public int getCountCathLabConsumptionReport(String fromdate, String todate, String searchtext) {
			int result=0;
			try {
				todate = todate +" "+"59:59:59";
				StringBuffer buffer = new StringBuffer();
				buffer.append("select count(*) from indent_patient_transfer_log ");
				buffer.append("inner join indent_parent_patient_transfer_log on indent_parent_patient_transfer_log.id = indent_patient_transfer_log.parentid ");
				buffer.append("inner join apm_patient on apm_patient.id = indent_patient_transfer_log.clientid ");
				buffer.append("where  datetime between '"+fromdate+"' and '"+todate+"' and iscathlab='1' and indent_patient_transfer_log.isused='1'  ");
				if(searchtext!=null){
					buffer.append("and (firstname like('%"+searchtext+"%') or surname like('%"+searchtext+"%') or abrivationid like('%"+searchtext+"%')) ");
				}
				PreparedStatement ps = connection.prepareStatement(buffer.toString());
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					result= rs.getInt(1);
				}

			} catch (Exception e) {

				e.printStackTrace();
			}

			return result;
		}
		public ArrayList<Product> getCathLabConsumptionReport(String fromdate, String todate, Pagination pagination,
				String searchtext) {
			ArrayList<Product> list = new ArrayList<Product>();
			try {
				ClientDAO clientDAO = new JDBCClientDAO(connection);
				PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
				InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
				StringBuffer buffer = new StringBuffer();
				todate = todate +" "+"59:59:59";
				
				buffer.append("select indent_patient_transfer_log.id, fromlocation, clientid, datetime, userid, prodid, qty, prodname, comment, ");
				buffer.append("previous_qty, later_qty,procedureid,given_date from indent_patient_transfer_log ");
				buffer.append("inner join indent_parent_patient_transfer_log on indent_parent_patient_transfer_log.id = indent_patient_transfer_log.parentid ");
				buffer.append("inner join apm_patient on apm_patient.id = indent_patient_transfer_log.clientid ");
				buffer.append("where  datetime between '"+fromdate+"' and '"+todate+"' and iscathlab='1' and indent_patient_transfer_log.isused='1'  ");
				if(searchtext!=null){
					buffer.append("and (firstname like('%"+searchtext+"%') or surname like('%"+searchtext+"%') or abrivationid like('%"+searchtext+"%')) ");
				}
				buffer.append("order by indent_patient_transfer_log.id desc ");
				String sql = pagination.getSQLQuery(buffer.toString());
				PreparedStatement ps = connection.prepareStatement(sql);
				double total =0;
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					Product product = new Product();
					product.setId(rs.getInt(1));
					String[] data = rs.getString(4).split(" ");
					String date = DateTimeUtils.getCommencingDate1(data[0]);
					product.setDateTime(date + " " + data[1]);
					Client client = clientDAO.getClientDetails(rs.getString(3));
					product.setClientname(client.getTitle() + " " + client.getFirstName() + " " + client.getLastName());
					product.setUserid(rs.getString(5));
					product.setTqty(rs.getString(7));
					product.setProduct_name(rs.getString(8));
					product.setMobile(client.getMobNo());
					product.setAbrivationid(client.getAbrivationid());
					String isipdid = pharmacyDAO.getIpdIdFromClientID(rs.getInt(3));
					if (isipdid.equals("0")) {
						product.setClienttype("OPD");
					} else {
						product.setClienttype("IPD");
					}
					if (client.getTypeName() != null) {
						if (!client.getTypeName().equals("0")) {
							product.setTpname("YES");
						} else {
							product.setTpname("NO");
						}
					} else {
						product.setTpname("NO");
					}
					Product product2 = inventoryProductDAO.getProduct(rs.getString(6));
					if (product2.getPack() == null) {
						product2.setPack("1");
					}
					if (product2.getPack().equals("")) {
						product2.setPack("1");
					}
					double amt = Double.parseDouble(product2.getSale_price()) / Integer.parseInt(product2.getPack());
					double amountno = Integer.parseInt(rs.getString(7)) * amt;
					product.setSale_price(""+Math.round(amt * 100.0) / 100.0);
					product.setTotal_amount(Math.round(amountno * 100.0) / 100.0);
					total = total + amountno;
					product.setTotal(DateTimeUtils.changeFormat(Math.round(total * 100.0) / 100.0));
					
					product.setBatch_no(product2.getBatch_no());
					String expirydate="";
					if(product2.getExpiry_date()!=null){
						if(!product2.getExpiry_date().equals("")){
							expirydate = DateTimeUtils.getCommencingDate1(product2.getExpiry_date());
						}
					}
					product.setExpiry_date(expirydate);
					
					//  12 sep 2018 adding new field
					String procedureid = rs.getString(12);
					String proname = getProcedureNameFromId(procedureid);
					product.setProcedurename(proname);
					
					data = rs.getString(13).split(" ");
					date = DateTimeUtils.getCommencingDate1(data[0]);
					product.setGiven_date(date + " " + data[1]);
					Product master = inventoryProductDAO.getProductCatalogueDetails(product2.getCatalogueid());
					Product productcat = inventoryProductDAO.getCategory(master.getCategory_id());
					Product productsubcat =inventoryProductDAO.getSubCategory(master.getSubcategory_id());
					String categoryname = productcat.getName();
					String subcategoryname =productsubcat.getName();
					product.setPro_code(master.getPro_code());
					product.setMfg(product2.getMfg());
					product.setCategory(categoryname);
					product.setSubcategory(subcategoryname);
					list.add(product);
				}

			} catch (Exception e) {

				e.printStackTrace();
			}

			return list;
		}
		
		public String getProcedureNameFromId(String procedureid) {
			String res ="";
			try {
				StringBuffer buffer = new StringBuffer();
				buffer.append("select name from apm_newchargetype ");
				buffer.append("where apm_newchargetype.id='"+procedureid+"' ");
				PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					res = rs.getString(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return res;
		}
		public int transferIssueProductUpdate(Product product, String comment1, String userId, String todate,
				String consumeid) {
			PharmacyDAO pharmacyDAO = new JDBCPharmacyDAO(connection);
			int result = 0;
			try {
				// minus stock
				double tqty=0.0;
				double oldstock = Double.parseDouble(product.getStock());
				tqty = oldstock - Double.parseDouble((product.getTqty()));
				String query = "update inventory_product set stock='" + tqty + "' where id='" + product.getId() + "'";
				PreparedStatement statement = connection.prepareStatement(query);
				int res = statement.executeUpdate();
				int res1 = updateIssueTransfer(product, tqty,userId,todate,comment1);
				
				double openingstock = oldstock;
				boolean checkopningstockexist = pharmacyDAO.checkopeningstockexist(String.valueOf(product.getId()),product.getDate());
				
				if(!checkopningstockexist){
				   int r = pharmacyDAO.saveOpeningStock(String.valueOf(product.getId()),product.getDate(),openingstock,"1");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}
		private int updateIssueTransfer(Product product, double tqty, String userId, String todate, String comment1) {
			int result = 0;
			try {
				String sql = "update indent_patient_transfer_log set fromlocation=?, clientid=?, consume_date=?, consume_userid=?, prodid=?, qty=?, prodname=?, comment=?, previous_qty=?, later_qty=?, isused=? where id='"+product.getConsumeid()+"'";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, product.getLocation());
				preparedStatement.setString(2, product.getClientid());
				preparedStatement.setString(3, todate);
				preparedStatement.setString(4, userId);
				preparedStatement.setString(5, ""+product.getId());
				preparedStatement.setString(6, product.getTqty());
				preparedStatement.setString(7, product.getProduct_name());
				preparedStatement.setString(8, comment1);
				preparedStatement.setString(9, product.getStock());
				preparedStatement.setString(10, ""+tqty);
				preparedStatement.setString(11, "1");
				result = preparedStatement.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}
		public int updateParentCathlabTemplate(String cathtempprocedure, String cathtemplatename, int parentid) {
			int result=0;
			try {
				String sql="update cathlab_template set template_name=?,procedure_id=? where id='"+parentid+"' ";
				PreparedStatement ps=connection.prepareStatement(sql);
				ps.setString(1, cathtemplatename);
				ps.setString(2, cathtempprocedure);
				result=ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return result;
		}
		public int updateChildCathlabTemplate(int prodid, String product_name, String tqty, String catalogueid,
				int childid) {
			int result=0;
			try {
				String sql="update cathlab_template_child set qty=?,prodid=?,catlogueid=?,productname=? where id='"+childid+"'";
				PreparedStatement ps=connection.prepareStatement(sql);
				ps.setString(1, ""+tqty);
				ps.setString(2, ""+prodid);
				ps.setString(3, ""+catalogueid);
				ps.setString(4, product_name);
				result=ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}
		public int deleteChildCathTempData(String id) {
			int result = 0;

			try {

				String sql = "delete from cathlab_template_child where id=" + id + "";
				PreparedStatement ps = connection.prepareStatement(sql);
				result = ps.executeUpdate();

			} catch (Exception e) {

				e.printStackTrace();
			}

			return result;
		}
		public ArrayList<String> getDirectTransferProduct(String product_id) {
			ArrayList<String> arrayList = new ArrayList<String>();
			try {
				StringBuffer buffer= new StringBuffer();
				buffer.append("select DISTINCT inventory_transfer_log.new_prodid  from inventory_transfer_log ");
				buffer.append("inner join inventory_parent_transfer_log on inventory_parent_transfer_log.id=inventory_transfer_log.parentid ");
				buffer.append("where inventory_transfer_log.old_prodid="+product_id+" and inventory_parent_transfer_log.req_or_trans=1 ");
				PreparedStatement ps=connection.prepareStatement(buffer.toString());
				ResultSet rs =ps.executeQuery();
				while(rs.next()){
					String newprodid = rs.getString(1);
					arrayList.add(newprodid);
				}
				
				
			} catch (Exception e) {

				e.printStackTrace();
			}
			
			return arrayList;
		}
		public ArrayList<String> getRequestTransferProduct(String oldprodid) {
			ArrayList<String> arrayList = new ArrayList<String>();
			try {
				String sql = "select DISTINCT new_prodid from inventory_request_temp_log where old_prodid="+ oldprodid + "";
				PreparedStatement ps=connection.prepareStatement(sql);
				ResultSet rs =ps.executeQuery();
				while(rs.next()){
					String newprodid = rs.getString(1);
					arrayList.add(newprodid);
				}
				
				
			} catch (Exception e) {

				e.printStackTrace();
			}
			
			return arrayList;
		}
		public int updateChildIndentProductChange(String childid, String changecatlid, String productid) {
			int result=0;
			try {
				 String sql1="update inventory_transfer_log set old_prodid='"+productid+"',catlogueid='"+changecatlid+"' where id='"+childid+"'  "; 
			     PreparedStatement ps1=connection.prepareStatement(sql1);
			    
			     result=  ps1.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}
		public int insertIntoChangeIndentProduct(String oldcatid, String childid, String parentid, String changecatlid,
				String comment, String todate, String userid) {
			int result=0;
			try {
				String sql="insert into indent_product_change_log (childid, parentid, oldcatalogueid,newcatalogueid,date,userid,comment) values (?,?,?,?,?,?,?)";
				PreparedStatement ps=connection.prepareStatement(sql);
				ps.setString(1, childid);
				ps.setString(2, parentid);
				ps.setString(3, oldcatid);
				ps.setString(4, changecatlid);
				ps.setString(5, todate);
				ps.setString(6, userid);
				ps.setString(7, comment);
				result=ps.executeUpdate();
				if(result>0){
					ResultSet rs=ps.getGeneratedKeys();
					while(rs.next()){
						 result=rs.getInt(1);
					}
				}
			} catch (Exception e) {

				e.printStackTrace();
			}
			
			return result;
		}
		public int getCountRequestIndentReport(String fromdate, String todate, String location_filter, String searhText) {
			int res =0;
			try {
				todate = todate+" "+"59:59:59";
				StringBuffer buffer=new StringBuffer();
				buffer.append("SELECT count(*) FROM inventory_request_temp_log ");
				buffer.append("inner join inventory_parent_transfer_log on inventory_parent_transfer_log.id=inventory_request_temp_log.parentid ");
				buffer.append("inner join inventory_product on  inventory_product.id = inventory_request_temp_log.old_prodid ");
				buffer.append("where transfer_date between '"+fromdate+"' and '"+todate+"' and inventory_parent_transfer_log.deleted=0 ");
				/*
				 * if (!location_filter.equals("0")) {
				 * buffer.append("and inventory_parent_transfer_log.warehouse_id ='" +
				 * location_filter + "' "); }
				 */
				if (!location_filter.equals("0")) {
					buffer.append("and inventory_parent_transfer_log.from_location ='" + location_filter + "' ");
				}
				if(searhText!=null){
					buffer.append("and (inventory_parent_transfer_log.id='"+searhText+"' or  inventory_product.prodname like ('%"+searhText+"%'))");
				}
				PreparedStatement ps=connection.prepareStatement(buffer.toString());
				ResultSet rs =ps.executeQuery();
				
				while(rs.next()){
					res =rs.getInt(1);
				}
				
				
			} catch (Exception e) {

				e.printStackTrace();
			}
			
			return res;
		}
		public ArrayList<Product> getRequestIndentReport(String fromdate, String todate, String location_filter,
				String searhText) {
			ArrayList<Product> arrayList = new ArrayList<Product>();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			try {
				todate = todate+" "+"59:59:59";
				StringBuffer buffer=new StringBuffer();
				buffer.append("SELECT inventory_parent_transfer_log.id,inventory_parent_transfer_log.request_date,inventory_parent_transfer_log.time, ");
				buffer.append("inventory_parent_transfer_log.from_location,inventory_parent_transfer_log.to_location,admin_approve_userid, ");
				buffer.append("inventory_product.id,inventory_product.prodname,");
				buffer.append("inventory_product.categoryid,inventory_catalogue.subcategoryid,transfer_date,inventory_request_temp_log.location,inventory_request_temp_log.qty,");
				buffer.append("inventory_request_temp_log.qty*(purchaseprice/inventory_product.pack),inventory_request_temp_log.catlogueid ");
				buffer.append("FROM inventory_request_temp_log ");
				buffer.append("inner join inventory_parent_transfer_log on inventory_parent_transfer_log.id=inventory_request_temp_log.parentid ");
				buffer.append("inner join inventory_product on  inventory_product.id = inventory_request_temp_log.old_prodid ");
				buffer.append("inner join inventory_catalogue on  inventory_product.catalogueid = inventory_catalogue.id ");
				buffer.append("where transfer_date between '"+fromdate+"' and '"+todate+"' and inventory_parent_transfer_log.deleted=0 ");
				/*
				 * if (!location_filter.equals("0")) {
				 * buffer.append("and inventory_parent_transfer_log.warehouse_id ='" +
				 * location_filter + "' "); }
				 *///commenting beacuse request dept filter add
				if (!location_filter.equals("0")) {
					buffer.append("and inventory_parent_transfer_log.from_location ='" + location_filter + "' ");
				}
				if(searhText!=null){
					buffer.append("and (inventory_parent_transfer_log.id='"+searhText+"' or  inventory_product.prodname like ('%"+searhText+"%'))");
				}
				PreparedStatement ps=connection.prepareStatement(buffer.toString());
				ResultSet rs =ps.executeQuery();
				
				while(rs.next()){
					Product product = new Product();
					product.setId(rs.getInt(1));
					String request_date = DateTimeUtils.getCommencingDate1(rs.getString(2));
					String time = rs.getString(3);
					product.setRequest_date(request_date + " " + time);
					String fromlocation = pharmacyLocationNameByID(rs.getString(4));
					String tolocation = pharmacyLocationNameByID(rs.getString(5));
					product.setFrom_location(fromlocation);
					product.setTo_location(tolocation);
					product.setAdmin_approve_userid(rs.getString(6));
					product.setProduct_name(rs.getString(8));
					Product category = inventoryProductDAO.getCategory(rs.getString(9));
					product.setCategory(category.getName());
					Product subcategory = inventoryProductDAO.getSubCategory(rs.getString(10));
					product.setSubcategory(subcategory.getName());
					String transfer_date = DateTimeUtils.getDBDate(rs.getString(11)); 
					product.setTransfer_date(transfer_date);
					String issuedepart = pharmacyLocationNameByID(rs.getString(12));
					product.setIssue_depart(issuedepart);
					product.setIssueqty(rs.getString(13));
					product.setValuation(rs.getDouble(14));
					double req_qty = getRequestIndentQty(product.getId(),rs.getString(15));
					product.setReq_qty(req_qty);
					arrayList.add(product);
				}
				
				
			} catch (Exception e) {

				e.printStackTrace();
			}
			
			return arrayList;
		}
		private double getRequestIndentQty(int id, String string) {
			double res = 0;
			try {
				String sql ="select qty from inventory_transfer_log where parentid='"+id+"' and catlogueid='"+string+"'";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					res = rs.getDouble(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return res;
		}
		public ArrayList<Product> getDirectIndentReport(String fromdate, String todate, String location_filter,
				String searhText, String filter_status) {
			ArrayList<Product> arrayList = new ArrayList<Product>();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			try {
				StringBuffer buffer= new StringBuffer();
				buffer.append("SELECT inventory_parent_transfer_log.id,inventory_parent_transfer_log.request_date,inventory_parent_transfer_log.time, ");
				buffer.append("inventory_parent_transfer_log.from_location,inventory_parent_transfer_log.to_location,admin_approve_userid, ");
				buffer.append("inventory_product.id,inventory_product.prodname,");
				buffer.append("inventory_product.categoryid,inventory_catalogue.subcategoryid,issued_date,from_location,inventory_transfer_log.qty,");
				buffer.append("inventory_transfer_log.qty*(purchaseprice/inventory_product.pack),inventory_transfer_log.catlogueid ");
				buffer.append("from inventory_transfer_log ");
				buffer.append("inner join inventory_parent_transfer_log on inventory_parent_transfer_log.id=inventory_transfer_log.parentid ");
				buffer.append("inner join inventory_product on  inventory_product.id = inventory_transfer_log.old_prodid ");
				buffer.append("inner join inventory_catalogue on  inventory_product.catalogueid = inventory_catalogue.id ");
				buffer.append("where inventory_parent_transfer_log.issued_date between '"+fromdate+"' and '"+todate+"' and inventory_parent_transfer_log.deleted=0 ");
				if(filter_status.equals("1")){
					buffer.append("and inventory_parent_transfer_log.req_or_trans=1 ");
				}else{
					buffer.append("and inventory_parent_transfer_log.req_or_trans=2 ");
				}
				if(searhText!=null){
					buffer.append("and (inventory_parent_transfer_log.id='"+searhText+"' or  inventory_product.prodname like ('%"+searhText+"%'))");
				}
				if(!location_filter.equals("0")){
					buffer.append("and inventory_parent_transfer_log.from_location='"+location_filter+"' ");
				}
				PreparedStatement ps=connection.prepareStatement(buffer.toString());
				ResultSet rs =ps.executeQuery();
			
				while(rs.next()){
					Product product = new Product();
					product.setId(rs.getInt(1));
					String request_date = DateTimeUtils.getCommencingDate1(rs.getString(2));
					String time = rs.getString(3);
					product.setRequest_date(request_date + " " + time);
					//String fromlocation = pharmacyLocationNameByID(rs.getString(4));
					String tolocation = pharmacyLocationNameByID(rs.getString(5));
					product.setFrom_location(tolocation);
					product.setTo_location(tolocation);
					product.setAdmin_approve_userid("-");
					product.setProduct_name(rs.getString(8));
					Product category = inventoryProductDAO.getCategory(rs.getString(9));
					product.setCategory(category.getName());
					Product subcategory = inventoryProductDAO.getSubCategory(rs.getString(10));
					product.setSubcategory(subcategory.getName());
					String transfer_date = DateTimeUtils.getCommencingDate1(rs.getString(11));
					product.setTransfer_date(transfer_date + " " + time);
					String issuedepart = pharmacyLocationNameByID(rs.getString(12));
					product.setIssue_depart(issuedepart);
					product.setIssueqty(rs.getString(13));
					double valuation=Math.round(rs.getDouble(14)*100.0)/100.0;
					product.setValuation(valuation);
					product.setReq_qty(rs.getDouble(13));
					arrayList.add(product);
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return arrayList;
		}
		public int getCountDirectIndentReport(String fromdate, String todate, String location_filter,
				String searhText, String filter_status) {
			int res=0; 
			try {
				StringBuffer buffer= new StringBuffer();
				buffer.append("SELECT count(*) ");
				buffer.append("from inventory_transfer_log ");
				buffer.append("inner join inventory_parent_transfer_log on inventory_parent_transfer_log.id=inventory_transfer_log.parentid ");
				buffer.append("inner join inventory_product on  inventory_product.id = inventory_transfer_log.old_prodid ");
				buffer.append("where  inventory_parent_transfer_log.issued_date between '"+fromdate+"' and '"+todate+"' and inventory_parent_transfer_log.deleted=0 ");
				if(filter_status.equals("1")){
					buffer.append("and inventory_parent_transfer_log.req_or_trans=1 ");
				}else{
					buffer.append("and inventory_parent_transfer_log.req_or_trans=2 ");
				}
				if(searhText!=null){
					buffer.append("and (inventory_parent_transfer_log.id='"+searhText+"' or  inventory_product.prodname like ('%"+searhText+"%'))");
				}
				if(!location_filter.equals("0")){
					buffer.append("and inventory_parent_transfer_log.from_location='"+location_filter+"' ");
				}
				PreparedStatement ps=connection.prepareStatement(buffer.toString());
				ResultSet rs =ps.executeQuery();
			
				while(rs.next()){
					res = rs.getInt(1);
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return res;
		}
		public Product getChildProductFromChildId(String id) {
			Product product = new Product();
			try {
				StringBuffer buffer= new StringBuffer();
				buffer.append("select inventory_transfer_log.id,catlogueid,qty,parentid,prod_name from inventory_transfer_log ");
				buffer.append("inner join inventory_parent_transfer_log on inventory_transfer_log.parentid = inventory_parent_transfer_log.id ");
				buffer.append("where inventory_transfer_log.id='"+id+"' ");
				PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					product.setId(rs.getInt(1));
					product.setCatalogueid(rs.getString(2));
					product.setQty(rs.getDouble(3));
					product.setParentid(rs.getString(4));
					product.setProd_name(rs.getString(5));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return product;
		}
		public int saveDeleteIndentCharge(Product product) {
			int res =0;
			try {
				String sql ="insert into delete_indent_product (parentid,catalogueid,qty,prod_name,delete_id,userid,datetime) values (?,?,?,?,?,?,?)";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, product.getParentid());
				preparedStatement.setString(2, product.getCatalogueid());
				preparedStatement.setDouble(3, product.getQty());
				preparedStatement.setString(4, product.getProd_name());
				preparedStatement.setString(5, ""+product.getId());
				preparedStatement.setString(6, product.getUserid());
				preparedStatement.setString(7, product.getDateTime());
				res = preparedStatement.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return res;
		}
		public ArrayList<Product> getchildDeleteList(String parentid) {
			ArrayList<Product> arrayList = new ArrayList<Product>();
			try {
				StringBuffer buffer= new StringBuffer();
				buffer.append("select id,parentid,catalogueid,qty,prod_name,delete_id,userid,datetime from delete_indent_product ");
				buffer.append("where parentid='"+parentid+"' ");
				PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					Product product = new Product();
					product.setId(rs.getInt(1));
					product.setParentid(rs.getString(2));
					product.setCatalogueid(rs.getString(3));
					product.setQty(rs.getDouble(4));
					product.setProd_name(rs.getString(5));
					product.setUserid(rs.getString(7));
					String date = DateTimeUtils.getCommencingDate1(rs.getString(8).split(" ")[0]);
					product.setDateTime(date+" "+rs.getString(8).split(" ")[1]);
					arrayList.add(product);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return arrayList;
		}
		public ArrayList<Product> getChildMultiAdjustEditData(String parentid) {
			ArrayList<Product> arrayList = new ArrayList<Product>();
			PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
			try {
				StringBuffer buffer= new StringBuffer();
				buffer.append("select adjustment_data.id,adjustment_type, product_id, pre_stock, current_stock, change_qty,");
				buffer.append("remark,request_location,");
				buffer.append("adj_catalogueid, curr_pack, pre_pack, curr_gst, pre_gst, curr_mrp, pre_mrp, curr_saleprice, ");
				buffer.append("pre_saleprice, curr_purprice, pre_purprice, curr_expdate, pre_expdate, ");
				buffer.append("curr_pshelf, pre_pshelf, curr_sshelf, pre_sshelf, child_approve_remark,curr_batch,pre_batch, ");
				buffer.append("prodname,hsnno ");
				buffer.append("from adjustment_data ");
				buffer.append("inner join adjustment_parent_data on adjustment_parent_data.id = adjustment_data.adj_parentid ");
				buffer.append("inner join inventory_product on inventory_product.id = adjustment_data.product_id ");
				buffer.append("where adj_parentid='"+parentid+"' ");
				PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
				ResultSet rs = preparedStatement.executeQuery();
				SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
				Calendar calendar = Calendar.getInstance();
				String currdate=dateFormat1.format(calendar.getTime());
				
				while (rs.next()) {
					Product product = new Product();
					product.setId(rs.getInt(1));
					product.setAdjustment_type(rs.getInt(2));
					product.setProductid(rs.getString(3));
					product.setPre_stock(rs.getInt(4));
					product.setCurrent_qty(rs.getString(5));
					product.setChange_qty(rs.getString(6));
					product.setRemark(rs.getString(7));
					product.setLocation(rs.getString(8));
					// For Report
					product.setLocationName(pharmacyDAO.getLocationName(rs.getString(8)));
					//For edit
					product.setCatalogueid(rs.getString(9));
					product.setCurr_pack(rs.getString(10));
					product.setPre_pack(rs.getString(11));
					product.setCurr_gst(rs.getString(12));
					product.setPre_gst(rs.getString(13));
					product.setCurr_mrp(rs.getString(14));
					product.setPre_mrp(rs.getString(15));
					product.setCurr_saleprice(rs.getString(16));
					product.setPre_saleprice(rs.getString(17));
					product.setCurr_purprice(rs.getString(18));
					product.setPre_purprice(rs.getString(19));
					product.setCurr_expdate(DateTimeUtils.getCommencingDate1(rs.getString(20)));
					product.setPre_expdate(DateTimeUtils.getCommencingDate1(rs.getString(21)));
					product.setCurr_pshelf(rs.getString(22));
					product.setPre_pshelf(rs.getString(23));
					product.setCurr_sshelf(rs.getString(24));
					product.setPre_sshelf(rs.getString(25));
					product.setChild_approve_remark(rs.getString(26));
					product.setCurr_batch(rs.getString(27));
					product.setPre_batch(rs.getString(28));
					product.setProduct_name(rs.getString(29));
					product.setHsnno(rs.getString(30));
					
					String expirydate = rs.getString(20);
					product.setPreexpcolor("");
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					Calendar expireCalendar = Calendar.getInstance();
					Date date2=format.parse(currdate);
					if (!DateTimeUtils.isNull(expirydate).equals("")) {
						Date date = format.parse(expirydate);
						expireCalendar.setTime(date);
						long diff=date.getTime()-date2.getTime();
						diff = (diff / (1000*60*60*24));
						if(diff<0){
							product.setPreexpcolor("#f20404");
						}
					}
					
					expirydate = rs.getString(21);
					product.setCurrexpcolor("");
					if (!DateTimeUtils.isNull(expirydate).equals("")) {
						Date date = format.parse(expirydate);
						expireCalendar.setTime(date);
						long diff=date.getTime()-date2.getTime();
						diff = (diff / (1000*60*60*24));
						if(diff<0){
							product.setCurrexpcolor("#f20404");
						}
					}
					
					arrayList.add(product);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return arrayList;
		}
		public int updateMultiAdjustment(String id, int status, String userid, String date) {
			int res =0;
			try {
				String sql ="update adjustment_parent_data set request_status="+status+",approve_userid='"+userid+"',approve_date='"+date+"' where id="+id+"";
				PreparedStatement preparedStatement =connection.prepareStatement(sql);
				res = preparedStatement.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return res;
		}
		public int updateMultiChildAdjustment(String parentid, String comment) {
			int res =0;
			try {
				String sql ="update adjustment_data set child_approve_remark=? where id="+parentid+"";
				PreparedStatement preparedStatement =connection.prepareStatement(sql);
				preparedStatement.setString(1, comment);
				res = preparedStatement.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return res;
		}
		public ArrayList<Product> getChildMultiAdjustprintData(String parentid) {
			ArrayList<Product> arrayList = new ArrayList<Product>();
			PharmacyDAO pharmacyDAO=new JDBCPharmacyDAO(connection);
			InventoryProductDAO inventoryProductDAO=new JDBCInventoryProductDAO(connection);
			try {
				StringBuffer buffer= new StringBuffer();
				buffer.append("select adjustment_data.id,adjustment_type, product_id, pre_stock, current_stock, change_qty,remark,request_location,child_approve_remark from adjustment_data ");
				buffer.append("inner join adjustment_parent_data on adjustment_parent_data.id = adjustment_data.adj_parentid ");
//				buffer.append("inner join inventory_product on inventory_product.id=adjustment_data.product_id ");
				buffer.append("where adj_parentid='"+parentid+"' ");
				PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					Product product = new Product();
					product.setId(rs.getInt(1));
					product.setAdjustment_type(rs.getInt(2));
					product.setProductid(rs.getString(3));
					product.setPre_stock(rs.getInt(4));
					product.setCurrent_qty(rs.getString(5));
					product.setChange_qty(rs.getString(6));
					product.setRemark(rs.getString(7));
					product.setLocation(rs.getString(8));
					// For Report
					product.setLocationName(pharmacyDAO.getLocationName(rs.getString(8)));
					Product product2=inventoryProductDAO.getProduct(product.getProductid());
					product.setHsnno(DateTimeUtils.isNull(product2.getHsnno()));
					product.setProduct_name(product2.getProduct_name());
					product.setBatch_no(product2.getBatch_no());
					product.setExpiry_date(DateTimeUtils.getCommencingDate1(product2.getExpiry_date()));
					product.setStock(product2.getStock());
					product.setAdmin_approve_remark(rs.getString(9));
					product.setColor(DateTimeUtils.isNull(product2.getColor()));
					arrayList.add(product);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return arrayList;
		}
		@Override
		public int checkIndentInTransfer(String parentid) {
			int res =0;
			try {
				String sql ="select * from indent_checkproduct_temp where parentid='"+parentid+"'";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					res =1;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return res;
		}
		@Override
		public int checkIndentInTransferUserWise(String parentid, String userId) {
			int res =0;
			try {
				String sql ="select * from indent_checkproduct_temp where parentid='"+parentid+"' and userid='"+userId+"'";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					res =1;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return res;
		}
		@Override
		public int insertIntoIndentInTransfer(String parentid, String userId, String datetime) {
			int res =0;
			try {
				String sql="insert into indent_checkproduct_temp(parentid,userid,datetime) values(?,?,?)";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, parentid);
				preparedStatement.setString(2, userId);
				preparedStatement.setString(3, datetime);
				res = preparedStatement.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		}
		@Override
		public Product getIndentTempAddLog(String parentid) {
			Product product = new Product();
			try {
				String sql ="select * from indent_checkproduct_temp where parentid='"+parentid+"'";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					product.setId(resultSet.getInt("id"));
					product.setParentid(resultSet.getString("parentid"));
					product.setUserid(resultSet.getString("userid"));
					product.setDateTime(resultSet.getString("datetime"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return product;
		}
		@Override
		public void deleteTempIndentInTransfer(String datetime5) {
			try {
				String sql ="delete from indent_checkproduct_temp where datetime<='"+datetime5+"'";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		@Override
		public int updateModifyConfirmStatus(int parentid) {
			int res =0;
			try {
				String sql ="update inventory_parent_transfer_log set modify_status=1 where id="+parentid+"";
				PreparedStatement preparedStatement =connection.prepareStatement(sql);
				res = preparedStatement.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return res;
		}
}