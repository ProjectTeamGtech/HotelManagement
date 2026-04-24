package com.apm.Inventory.eu.blogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import com.apm.Accounts.eu.entity.Accounts;
import com.apm.Inventory.eu.bi.InventoryProductDAO;
import com.apm.Inventory.eu.bi.InventoryVendorDAO;
import com.apm.Inventory.eu.bi.PoPaymenytDAO;
import com.apm.Inventory.eu.bi.ProcurementDAO;
import com.apm.Inventory.eu.entity.Procurement;
import com.apm.Inventory.eu.entity.Product;
import com.apm.Inventory.eu.entity.Vendor;
import com.apm.Master.eu.entity.Master;
import com.apm.common.eu.blogic.jdbc.JDBCBaseDAO;
import com.apm.common.utils.DateTimeUtils;
import com.apm.common.utils.Pagination;
import com.apm.common.web.common.helper.LoginInfo;

import ij.process.AutoThresholder.Method;

public class JDBCProcurementDAO extends JDBCBaseDAO implements ProcurementDAO {

	public JDBCProcurementDAO(Connection connection) {
		this.connection = connection;
	}

	public ArrayList<Vendor> getVendorList() {
		PreparedStatement preparedStatement = null;
		ArrayList<Vendor> list = new ArrayList<Vendor>();
		String sql = "SELECT id,name,suppliercode FROM inventory_vendor order by name ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Vendor vendor = new Vendor();
				vendor.setId(rs.getInt(1));
				vendor.setName(rs.getString(2));
				vendor.setSuppliercode(rs.getString(3));
				String data=vendor.getName();
				if(!DateTimeUtils.isNull(vendor.getSuppliercode()).equals("")){
					data = data +" - "+vendor.getSuppliercode();
				}
				vendor.setData(data);
				list.add(vendor);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Product> getProductList(String supid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Product> list = new ArrayList<Product>();
		String sql = "SELECT id,prodname FROM inventory_product where supplierid = " + supid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt(1));
				product.setName(rs.getString(2));
				list.add(product);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public String getBrandidseries(String supid) {
		PreparedStatement preparedStatement = null;
		String result = "";
		String sql = "SELECT brandname FROM inventory_vendor where id = " + supid + " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				result = rs.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;

	}

	public ArrayList<Master> getBrandNameList(String supid, String brandidseries) {
		PreparedStatement preparedStatement = null;
		ArrayList<Master> list = new ArrayList<Master>();
		String sql = "SELECT id,name FROM inventory_brandname where id in(" + brandidseries + ") ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setName(rs.getString(2));

				list.add(master);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public ArrayList<Product> getBrandProductList(String vendor, String bid) {
		PreparedStatement preparedStatement = null;
		ArrayList<Product> list = new ArrayList<Product>();
		String sql = "SELECT id,prodname FROM inventory_product where supplierid=  " + vendor + " and brandid = " + bid
				+ " ";

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt(1));
				product.setName(rs.getString(2));
				list.add(product);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public Product getProductDetails(String pid) {
		PreparedStatement preparedStatement = null;
		Product product = new Product();
		String sql = "SELECT id,prodcode,mrp,purchaseprice,brandid,supplierid,vat FROM inventory_product where id = "
				+ pid + " ";

		try {

			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				product.setId(rs.getInt(1));
				product.setProduct_code(rs.getString(2));
				product.setMrp(rs.getString(3));
				product.setPurchase_price(rs.getString(4));
				product.setBrand_id(rs.getString(5));
				product.setVendor_id(rs.getString(6));
				product.setVat("" + rs.getInt(7));
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return product;
	}

	public int saveProcurementData(Product product, String qty, double total, String vendorid, String brandid,
			int procurementid, int isagrement, int islongpoprod, String vat) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into inventory_procurement(prodid, purprice, qty, total, mrp,date,vendorid,"
				+ "brandid,procurementid,location,longpo,grnno,catalogueid,discount,child_agreementid,"
				+ "isagreement,islongpoprod,grnwithpo_child,gstper,parentpoid,proc_condition,"
				+ "p_userid,poHsnno,poDescription) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try {

			Date date = Calendar.getInstance().getTime();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String nowdate = dateFormat.format(date);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, product.getId());
			preparedStatement.setString(2, product.getPurchase_price());
			preparedStatement.setString(3, qty);
			preparedStatement.setDouble(4, total);
			preparedStatement.setString(5, product.getMrp());
			preparedStatement.setString(6, nowdate);
			preparedStatement.setString(7, vendorid);
			preparedStatement.setString(8, brandid);
			preparedStatement.setInt(9, procurementid);
			preparedStatement.setString(10, product.getLocation());
			preparedStatement.setInt(11, product.getNewpo());
			preparedStatement.setInt(12, product.getGrnno());
			preparedStatement.setString(13, product.getCatalogueid());
			preparedStatement.setString(14, product.getDiscount());
			preparedStatement.setString(15, product.getChildid());
			preparedStatement.setString(16, "" + isagrement);
			preparedStatement.setString(17, "" + islongpoprod);
			preparedStatement.setString(18, "" + product.getGrnwithpo_child());
			preparedStatement.setString(19, vat);
			preparedStatement.setString(20, "" + product.getParentpoid());
			preparedStatement.setString(21, "" + product.getProc_condition());
			preparedStatement.setString(22, "" + product.getUserid());
			preparedStatement.setString(23, product.getPoHsnno());
			preparedStatement.setString(24, product.getPoDescription());

			result = preparedStatement.executeUpdate();
			if (result > 0) {

				ResultSet set = preparedStatement.getGeneratedKeys();
				while (set.next()) {
					result = set.getInt(1);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public int getTotalQuantityCount(int id) {

		int result = 0;

		try {

			String sql = "select sum(qty) from inventory_procurement where prodid=" + id
					+ " and confirm=0 group by prodid";

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

	public int updateProduct(double qty, int id) {

		int result = 0;
		InventoryProductDAO productDAO = new JDBCInventoryProductDAO(connection);

		try {

			Product product = productDAO.getProduct(String.valueOf(id));

			qty = qty +Double.parseDouble(product.getStock());

			String sql = "update inventory_product set stock=? where id=" + id + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, String.valueOf(qty));

			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<Procurement> getAllProcurementList() {

		ArrayList<Procurement> list = new ArrayList<Procurement>();
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		InventoryVendorDAO inventoryVendorDAO = new JDBCInventoryVendorDAO(connection);
		PoPaymenytDAO poPaymenytDAO = new JDBCPoPaymengtDAO(connection);
		try {
			String sql = "select id, prodid, purprice, qty, total, mrp, completepo,date,gudreceipt,confirm,vendorid,voucherno,procurementid from inventory_procurement";

			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				Procurement procurement = new Procurement();
				procurement.setId(rs.getInt(1));
				procurement.setProduct_id(rs.getString(2));
				procurement.setPurchase_price(rs.getString(3));
				procurement.setStock(rs.getString(4));
				procurement.setTotal(rs.getString(5));
				procurement.setMrp(rs.getString(6));
				procurement.setStatus(rs.getInt(7));
				procurement.setCompletepo(rs.getInt(7));
				procurement.setDate(rs.getString(8));
				procurement.setGudreceipt(rs.getInt(9));
				procurement.setConfirm(rs.getInt(10));
				procurement.setVendor_id(rs.getString(11));
				procurement.setVoucherno(rs.getString(12));
				procurement.setProcurementid(rs.getString(13));

				Product product = inventoryProductDAO.getProduct(procurement.getProduct_id());
				Vendor vendor = inventoryVendorDAO.getVendor(product.getVendor_id());
				int dmcmplted = checkIsComplatedDm(rs.getString(13));
				double netAmt = poPaymenytDAO.getTotalVoucherAmount(procurement.getVoucherno(),
						procurement.getProcurementid(), dmcmplted);
				procurement.setNetAmt(DateTimeUtils.changeFormat(netAmt));
				procurement.setProduct(product.getProduct_name());
				procurement.setVendor(vendor.getName());

				list.add(procurement);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	public int checkIsComplatedDm(String string) {
		int res = 0;
		try {
			String sql = "select dmcmplt from inventory_parent_procurement where id='" + string + "'";
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

	public int updatePoData(String id) {

		int result = 0;

		try {

			String sql1 = "select completepo from inventory_procurement where id=" + id + "";
			PreparedStatement ps = connection.prepareStatement(sql1);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				result = rs.getInt(1);

				if (result == 0) {
					result = 1;
				} else {
					result = 0;
				}

			}

			String sql = "update inventory_procurement set completepo=? where id=" + id + "";
			PreparedStatement ps1 = connection.prepareStatement(sql);
			ps1.setInt(1, result);

			result = ps1.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public int deletePo(String id) {

		int result = 0;

		try {
			String sql = "delete from inventory_procurement where id=" + id + "";
			PreparedStatement ps = connection.prepareStatement(sql);

			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public int cancelPo(String id) {
		int result = 0;
		try {
			// String sql="update inventory_procurement set
			// deleted=1,cancel_reason='"+delete_reason+"' where id="+id+"";
			String sql = "update inventory_procurement set deleted=1 where id=" + id + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateConfirmStatus(String proc_id) {

		int result = 0;

		try {
			String sql = "update inventory_procurement set confirm=1 where id=" + proc_id + "";
			PreparedStatement ps = connection.prepareStatement(sql);

			result = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public int updateConfirmPOStatus(String procurementid) {

		int result = 0;

		try {
			String sql = "update inventory_procurement set confirm=1 where procurementid=" + procurementid + "";
			PreparedStatement ps = connection.prepareStatement(sql);

			result = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public int updategoodsPO(String proc_id) {

		int result = 0;

		try {

			String sql = "select gudreceipt from inventory_procurement where id=" + proc_id + "";
			PreparedStatement ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				result = rs.getInt(1);

				if (result == 1) {
					result = 0;
				} else {
					result = 1;
				}

			}

			String sql1 = "update inventory_procurement set gudreceipt=" + result + " where id=" + proc_id + "";
			PreparedStatement ps1 = connection.prepareStatement(sql1);

			result = ps1.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public Procurement getProcurement(String selectedid, int procid) {

		Procurement procurement = new Procurement();

		try {
			// String sql="select prodid, purprice, qty, total, mrp, completepo,
			// date, gudreceipt, confirm, vendorid,
			// brandid,procurementid,voucherno,voucherdate,location,longpo,p_time,p_userid,grnno,catalogueid,location
			// from inventory_procurement where id="+selectedid+"";
			StringBuffer buffer = new StringBuffer();
			buffer.append(
					"select prodid, purprice, qty, total, mrp, completepo, date, gudreceipt, confirm, vendorid, brandid,procurementid,voucherno, ");
			buffer.append(
					"voucherdate,location,longpo,p_time,p_userid,grnno,catalogueid,location,dmno from inventory_procurement where id="
							+ selectedid + " ");
			if (procid > 0) {
				buffer.append("and oldprocid='" + procid + "' ");
			}
			PreparedStatement ps = connection.prepareStatement(buffer.toString());

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				procurement.setProduct_id(rs.getString(1));
				procurement.setPurchase_price(rs.getString(2));
				procurement.setQuantity("" + rs.getInt(3));
				procurement.setTotal(rs.getString(4));
				procurement.setMrp(rs.getString(5));
				procurement.setCompletepo(rs.getInt(6));
				procurement.setDate(rs.getString(7));
				procurement.setGudreceipt(rs.getInt(8));
				procurement.setConfirm(rs.getInt(9));
				procurement.setVendor_id(rs.getString(10));
				procurement.setBrand_id(rs.getString(11));
				procurement.setProcurementid(rs.getString(12));
				procurement.setVoucherno(rs.getString(13));
				procurement.setVoucherdate(rs.getString(14));
				procurement.setLocation(rs.getString(15));
				procurement.setLongpo(rs.getInt(16));
				procurement.setTime(rs.getString(17));
				procurement.setUserid(rs.getString(18));
				procurement.setGrnno(rs.getString(19));
				procurement.setCatalogueid(rs.getString(20));
				procurement.setLocation(rs.getString(21));
				procurement.setId(Integer.parseInt(selectedid));
				procurement.setDmno(rs.getString(22));
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return procurement;
	}

	public int updatePoductpo(Procurement procurement) {

		int result = 0;

		try {

			String sql = "update inventory_procurement set prodid=?,purprice=?,qty=?,total=?,mrp=?,brandid=?,gudreceipt=0,confirm=0,voucherno=? where id="
					+ procurement.getId() + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, procurement.getProduct_id());
			ps.setString(2, procurement.getPurchase_price());
			ps.setString(3, procurement.getQuantity());
			ps.setString(4, procurement.getTotal());
			ps.setString(5, procurement.getMrp());
			ps.setString(6, procurement.getBrand_id());
			ps.setString(7, procurement.getVoucherno());

			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public int saveParengtPrecurementData(String vendorid, String date, int isagrement, String agreementid,
			int grnwithpo, String grnYearSeq, String isfromworkorder) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		int procurementid = 0;
		String sql = "insert into inventory_parent_procurement(vendorid, lastmodified,isagreement,"
				+ "agreementid,grnwithpo,grnYearSeq,parentworkorder) values(?,?,?,?,?,?,?)";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, vendorid);
			preparedStatement.setString(2, date);
			preparedStatement.setString(3, "" + isagrement);
			preparedStatement.setString(4, agreementid);
			preparedStatement.setString(5, "" + grnwithpo);
			preparedStatement.setString(6, grnYearSeq);
			preparedStatement.setString(7, isfromworkorder);
			result = preparedStatement.executeUpdate();

			if (result == 1) {
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if (resultSet.next()) {
					procurementid = resultSet.getInt(1);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return procurementid;
	}

	public int getProcurementCount(String catid, String location, String fromdate, String todate, String voucherno,
			String iswithpo, String vendoridi, boolean isAmcCmc) {
		int result = 0;

		StringBuffer sql = new StringBuffer();

		sql.append("select count(*) from inventory_procurement ");
		sql.append(
				"inner join inventory_parent_procurement on inventory_parent_procurement.id = inventory_procurement.procurementid ");
		sql.append("inner join inventory_product on inventory_product.id = inventory_procurement.prodid ");
		sql.append("  left join procurement_seqno on procurement_seqno.seq_procid =inventory_parent_procurement.id ");
		if (voucherno != null) {
			sql.append("left join inventory_vendor on inventory_vendor.id =  inventory_parent_procurement.vendorid ");
		}
		sql.append("left join apm_condition on apm_condition.id = inventory_procurement.location ");
		sql.append("where inventory_procurement.prodid!=0 and isdelivermemo=0 and proc_condition=0 ");

		if (!location.equals("0")) {
			sql.append("and inventory_procurement.location='" + location + "' ");
		}
		if (voucherno != null) {
			sql.append("and (inventory_procurement.voucherno='" + voucherno
					+ "' or inventory_procurement.procurementid='" + voucherno + "'  ");
			sql.append("or inventory_vendor.name like ('" + voucherno + "%') or inventory_procurement.grnno='"
					+ voucherno + "' or sequenceno='" + voucherno + "') ");
		} else {
			sql.append("and date between '" + fromdate + "' and '" + todate + "' ");
		}

		if (!vendoridi.equals("")) {
			sql.append(" and inventory_parent_procurement.vendorid='" + vendoridi + "' ");
		}
		
		if(isAmcCmc){
			sql.append("and inventory_parent_procurement.parentworkorder='2' ");
		}else{
			sql.append("and inventory_parent_procurement.parentworkorder!='2' ");
		}

		if (!iswithpo.equals("0")) {
			if (iswithpo.equals("1")) {
				sql.append("and grnno>0 ");
			} else if (iswithpo.equals("2")) {
				sql.append("and grnno=0 ");
			} else if (iswithpo.equals("3")) {
				sql.append("and inventory_parent_procurement.ispocomplete=1 ");
			} else if (iswithpo.equals("4")) {
				sql.append("and grnno>0 and inventory_procurement.confirm=0 ");
			} else if (iswithpo.equals("5")) {
				sql.append("and grnno>0 and inventory_procurement.iscancel=1 ");
			}
		}

		sql.append("group by procurementid ");

		try {

			PreparedStatement ps = connection.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updatePocurement(Product procurement) {

		int result = 0;

		try {

			String sql = "update inventory_procurement set prodid=?,purprice=?,qty=?,total=?,mrp=?,brandid=?,gudreceipt=1,confirm=1,vendorid=?,voucherno=?,voucherdate=?,discount=?,p_time=?,p_userid=?,remainfreeqty=?,dmno=?,scheme_disc=?,total_disc_amt=? where id="
					+ procurement.getId() + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, procurement.getProduct_id());
			ps.setString(2, procurement.getPurchase_price());
			ps.setString(3, procurement.getReceived_qty());
			ps.setString(4, procurement.getTotal());
			ps.setString(5, procurement.getMrp());
			ps.setString(6, procurement.getBrand_id());
			ps.setString(7, procurement.getVendor_id());
			ps.setString(8, procurement.getVoucherno());
			ps.setString(9, procurement.getVoucherdate());
			ps.setString(10, procurement.getDiscount());
			ps.setString(11, procurement.getTime());
			ps.setString(12, procurement.getUserid());
			ps.setString(13, procurement.getFreeqty());
			ps.setString(14, procurement.getDmno());
			ps.setString(15, procurement.getScheme());
			ps.setString(16, procurement.getTotalschmedisc());
			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<Procurement> getAllProcurementList(String cateid, Pagination pagination, String location,
			String voucherno, String procurementType, String fromdate, String todate, String vendoridi,
			String iswithpo,boolean isAmcCmc,LoginInfo loginInfo) {

		ArrayList<Procurement> list = new ArrayList<Procurement>();
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		InventoryVendorDAO inventoryVendorDAO = new JDBCInventoryVendorDAO(connection);
		PoPaymenytDAO poPaymenytDAO = new JDBCPoPaymengtDAO(connection);
		ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);

		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select inventory_procurement.id, prodid, inventory_procurement.qty, inventory_procurement.date, ");
			sql.append("confirm,gudreceipt,procurementid,voucherdate,voucherno,inventory_procurement.vendorid,longpo,");
			sql.append("inventory_procurement.grnno,inventory_procurement.deleted,inventory_procurement.iscancel,");
			sql.append("apm_condition.name,grnseqno,isdelivermemo,dmcmplt,ispocomplete,grnwithpo,inventory_procurement.parentpoid,");
			sql.append("p_userid,approve_userid,grnYearSeq from inventory_procurement ");
			sql.append("inner join inventory_parent_procurement on inventory_parent_procurement.id = inventory_procurement.procurementid ");
			sql.append("inner join inventory_product on inventory_product.id = inventory_procurement.prodid ");
			sql.append("  left join procurement_seqno on procurement_seqno.seq_procid =inventory_parent_procurement.id ");
			if (voucherno != null) {
				sql.append("left join inventory_vendor on inventory_vendor.id =  inventory_parent_procurement.vendorid ");
			}
			sql.append("left join apm_condition on apm_condition.id = inventory_procurement.location ");
			sql.append("where inventory_procurement.prodid!=0 and isdelivermemo=0 and proc_condition=0  ");

			if (!location.equals("0")) {
				sql.append("and inventory_procurement.location='" + location + "' ");
			}
			if (voucherno != null) {
				sql.append("and (inventory_procurement.voucherno='" + voucherno
						+ "' or inventory_procurement.procurementid='" + voucherno + "'  ");
				sql.append("or inventory_vendor.name like ('" + voucherno + "%') or inventory_procurement.grnno='"
						+ voucherno + "' or sequenceno='" + voucherno + "') or inventory_parent_procurement.grnYearSeq= '"+voucherno+"' ");
			} else {
				sql.append("and date between '" + fromdate + "' and '" + todate + "' ");
			}

			if (!vendoridi.equals("")) {
				sql.append(" and inventory_parent_procurement.vendorid='" + vendoridi + "' ");
			}
			
			if(isAmcCmc){
				sql.append("and inventory_parent_procurement.parentworkorder='2' ");
			}else{
				sql.append("and inventory_parent_procurement.parentworkorder!='2' ");
			}

			if (!iswithpo.equals("0")) {
				if (iswithpo.equals("1")) {
					sql.append("and grnno>0 ");
				} else if (iswithpo.equals("2")) {
					sql.append("and grnno=0 ");
				} else if (iswithpo.equals("3")) {
					sql.append("and inventory_parent_procurement.ispocomplete=1 ");
				} else if (iswithpo.equals("4")) {
					sql.append("and grnno>0 and inventory_procurement.confirm=0 ");
				}else if (iswithpo.equals("5")) {
					sql.append("and grnno>0 and inventory_procurement.iscancel=1 ");
				}
			}

			sql.append("group by procurementid order by inventory_parent_procurement.id desc ");

			String sqls = pagination.getSQLQuery(sql.toString());

			PreparedStatement ps = connection.prepareStatement(sqls);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				Procurement procurement = new Procurement();
				procurement.setId(rs.getInt(1));
				procurement.setProduct_id(rs.getString(2));
				procurement.setQuantity(rs.getString(3));
				procurement.setDate(DateTimeUtils.getCommencingDate1(rs.getString(4)));
				procurement.setConfirm(rs.getInt(5));
				procurement.setGudreceipt(rs.getInt(6));
				procurement.setProcurementid(rs.getString(7));
				String voucherdate = "";
				if (rs.getString(8) != null) {
					voucherdate = DateTimeUtils.getCommencingDate1(rs.getString(8));
				}

				procurement.setVoucherno(rs.getString(9));
				String vendorid = rs.getString(10);
				procurement.setVendor_id(vendorid);
				int qty = getSumOfQTy(procurement.getProcurementid());
				procurement.setQuantity("" + qty + "");
				procurement.setIscancel(rs.getString(14));

				Product vendorProcData = getProcAccountDetails(procurement.getProcurementid());
				procurement.setSecurity_date(vendorProcData.getSecurity_date());
				procurement.setSecurity_no(vendorProcData.getSecurity_no());
				Product product = inventoryProductDAO.getProduct(procurement.getProduct_id());
				String prodname = product.getProduct_name();
				String brand = product.getBrand();
				Vendor vendormaster = inventoryVendorDAO.getVendor(vendorid);
				String vendor = vendormaster.getName();
				procurement.setVoucherdate(voucherdate);
				procurement.setProduct(prodname);
				procurement.setBrand(brand);
				procurement.setVendor(vendor);
				procurement.setTinno(product.getTinno());
				int dmcmplted = checkIsComplatedDm(procurement.getProcurementid());
				double netAmt = poPaymenytDAO.getTotalVoucherAmount(procurement.getVoucherno(),
						procurement.getProcurementid(), dmcmplted);
				procurement.setNetAmt(DateTimeUtils.changeFormat(netAmt));
				int longpo = rs.getInt(11);
				procurement.setGrnno(rs.getString(12));
				int deleted = rs.getInt(13);
				/*
				 * boolean flag=
				 * isthisProcuremetProductransfer(procurement.getProcurementid()
				 * ); if(flag){ procurement.setEdit(1); }
				 */
				procurement.setEdit(0);
				
				boolean stockDiffernce = getStockAndGRNDiffernce(procurement.getProcurementid());
				
				/*int nowstock = Integer.parseInt(product.getStock());
				int masterfreeqty = Integer.parseInt(product.getFreeqty());

				if (nowstock != (rs.getInt(3) + masterfreeqty)) {
					procurement.setTransferred(1);
				}*/
				if(!stockDiffernce){
					procurement.setTransferred(1);
				}
				procurement.setDeleted(deleted);
				if (rs.getInt(12) > 0) {
					Product grndata = getGrnDetails(rs.getInt(12));
					procurement.setGrndate(grndata.getDate());
					procurement.setPoSequence(grndata.getPoSequence());
					procurement.setWorkorder(grndata.getIsfromworkorder());
				}
				procurement.setLocationname(rs.getString(15));
				procurement.setGrnseqno(rs.getInt(16));
				procurement.setIsdelivermemo(rs.getString(17));
				procurement.setDmcmplt(rs.getString(18));
				procurement.setIspocomplete(rs.getInt(19));
				procurement.setIsgrnwithpo(rs.getString(20));
				procurement.setIsgrneditvendor(rs.getInt(21));
				procurement.setUserid(rs.getString(22));
				procurement.setApproveuserid(rs.getString(23));
				procurement.setGrnYearSeq(rs.getString(24));
				
				int res = getProcurmentSeqNo(rs.getString(7));
				String proSeqNo = "";
				if (res > 0) {
					proSeqNo = String.valueOf(res);
				} else {
					proSeqNo = rs.getString(7);
				}
				procurement.setProSeqNo(proSeqNo);
				int paymentdonestatus = getPaymentDoneStatus(rs.getString(7));
				procurement.setPaymentdonestatus(paymentdonestatus);
				if (longpo > 0) {
					list.add(procurement);
				} else {

					list.add(procurement);

				}
			//for vspm show pending amount column
			if(loginInfo.isLmh()) {
				  if(procurement.getGudreceipt()==0 && procurement.getIspocomplete()==1) {
					  int newprocurementid=0;
					 Procurement procurement2 = procurementDAO.getParentProcurmentData(procurement.getProcurementid());
					 boolean flag = procurementDAO.checkPreviousCompletePo(procurement.getProcurementid());
					 if (flag) {
							newprocurementid = procurementDAO.getProcurementPOPending(procurement.getProcurementid(), flag, procurement2,
									loginInfo.getTimeZone());
						} else {
							newprocurementid = procurementDAO.getPreviousCompletePoProcId(procurement.getProcurementid());
						}
					  
				       //newprocurementid = procurementDAO.getPreviousCompletePoProcId(procurement.getProcurementid());
				       double pendingnetAmt=getpendingAmount(newprocurementid);
				       procurement.setNetAmt(DateTimeUtils.changeFormat(Math.round(pendingnetAmt)));
			    }
			}	
				
		  }

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	private double getpendingAmount(int newprocurementid) {
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		double pendingamt=0;
		try {
			String sql = "select id,prodid,purprice,qty,total,discount,longpo,date,grnno,catalogueid,location,"
					+ "isagreement,child_agreementid,procurementid,vendorid,parentpoid,gstper,poHsnno,"
					+ "poDescription,inventory_procurement.iscancel "
					+ "from inventory_procurement where procurementid=" + newprocurementid + " order by id desc ";
			
			
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			double subtotal = 0;
			double netsubtotal = 0;
			double totalgstamount = 0;
			
			while (rs.next()) {
				Product product = new Product();
				product.setDiscount(DateTimeUtils.changeFormat(rs.getDouble(6)));
				product.setId(rs.getInt(1));
				product.setProduct_id(rs.getString(2));
				product.setQuantity(rs.getString(4));
				product.setNewpo(rs.getInt(7));
				product.setDate(DateTimeUtils.getCommencingDate1(rs.getString(8)));
				product.setGrnno(rs.getInt(9));
				product.setCatalogueid(rs.getString(10));
				Product master = null;
				if (!product.getCatalogueid().equals("0")) {
					master = inventoryProductDAO.getProductCatalogueDetails(product.getCatalogueid());
				} else {
					master = inventoryProductDAO.getProduct(product.getProduct_id());
				}
				// double rate= Double.parseDouble(master.getPurchase_price());
				double rate = rs.getDouble(3);
				product.setProduct_name(master.getProduct_name());
				double pack = Double.parseDouble(master.getPack());
				if (pack == 0) {
					master.setPack("1.0");
				}
				product.setUom(master.getUom());
				product.setPro_code(master.getPro_code());
				double total = rs.getDouble(4) * rate;
				subtotal = subtotal + total;
				product.setSubTotal(String.format("%.2f", Math.round(subtotal * 100.0) / 100.0));
				product.setTotal(String.format("%.2f", Math.round(total * 100.0) / 100.0));
				product.setMrp(master.getMrp());
				product.setSale_price(master.getSale_price());
				/* product.setPurchase_price(master.getPurchase_price()); */
				product.setPurchase_price(rs.getString(3));
				product.setVendor_id("" + rs.getInt(15));
				product.setBatch_no(master.getBatch_no());
				product.setPack(master.getPack());
				product.setMfg(master.getMfg());
				product.setHsnno(master.getHsnno());

				product.setVat("" + rs.getInt(17));
				product.setSale_price(master.getSale_price());
				// 23 June 2018
				double purchaseprice = rate;
				double vat = rs.getDouble(17);
				/*
				 * if(master.getVat()!=null){ if(!master.getVat().equals("")){
				 * vat = Double.parseDouble(master.getVat()); } }
				 */
				double qty = rs.getDouble(4);
				double netamount = (purchaseprice * vat) / 100;
				netamount = netamount * qty;
				
				double tempgst = netamount * rs.getDouble(6) /100;
				netamount = netamount - tempgst;
				
				netamount = Math.round(netamount * 100.0) / 100.0;
				product.setGstamount(String.format("%.2f", Math.round(netamount * 100.0) / 100.0));
				totalgstamount = totalgstamount + netamount;
				product.setTotalgstamount(String.format("%.2f", Math.round(totalgstamount * 100.0) / 100.0));

				/*double nettotal = netamount + total;*/
				double nettotal = total;
				double temp = (nettotal * rs.getDouble(6)) / 100;
				nettotal = nettotal - temp;
				nettotal = nettotal + netamount;
				product.setNetamount(String.format("%.2f", Math.round(nettotal * 100.0) / 100.0));

				netsubtotal = netsubtotal + nettotal;
				product.setTotalnetamount(String.format("%.2f", Math.round(netsubtotal * 100.0) / 100.0));
				// lokesh
				product.setStockqty(getStock(product.getCatalogueid(), rs.getString(11)));
				double oldpurchaseprice = getoldpurprice(product.getCatalogueid());
				product.setOldvenderpurprise(oldpurchaseprice);
				String location = rs.getString(11);
				if (location == null) {
					location = "0";
				} else {
					if (location.equals("")) {
						location = "0";
					}
				}
				ArrayList<Master> cellList = new ArrayList<Master>();
				ArrayList<Master> newcellList = new ArrayList<Master>();
				if (!location.equals("0")) {
					cellList = inventoryProductDAO.getcellList(location);
					newcellList = inventoryProductDAO.getcellList(location);
				} else {
					cellList = inventoryProductDAO.getcellList("32,33,34,35,36,105");
					newcellList = inventoryProductDAO.getcellList("32,33,34,35,36,105");
				}

				Product productshelf = inventoryProductDAO.getPreviousShelfId(rs.getString(10), location);
				ArrayList<Master> cellList1 = new ArrayList<Master>();
				ArrayList<Master> cellList2 = new ArrayList<Master>();
				for (Master master2 : cellList) {
					if (master2.getId() == productshelf.getPreviouscellid()) {
						master2.setStatus(1);
					} else {
						master2.setStatus(2);
					}
					cellList1.add(master2);
				}
				for (Master master3 : newcellList) {
					if (master3.getId() == productshelf.getPreviousseccellid()) {
						master3.setStatus(1);
					} else {
						master3.setStatus(2);
					}
					cellList2.add(master3);
				}
				product.setShelfList(cellList1);
				product.setSecondshelfList(cellList2);
				product.setPro_code(master.getPro_code());
				product.setIsagreement(rs.getInt(12));
				product.setChildagreeid(rs.getString(13));
				product.setProcurementid(rs.getString(14));
				product.setParentpoid(rs.getInt(16));
				product.setLocation(location);

				double gst = rs.getDouble(17);
				double cgst = 0;
				double sgst = 0;
				if (gst != 0) {
					cgst = gst / 2;
					sgst = cgst;
				}
				product.setCgst("" + cgst);
				product.setSgst("" + sgst);

				product.setPoHsnno(rs.getString(18));
				product.setPoDescription(rs.getString(19));
				product.setDeleted(rs.getInt(20));
				if(!DateTimeUtils.isNull(rs.getString(18)).equals("")){
					product.setHsnno(rs.getString(18));
				}
				
            pendingamt=Double.parseDouble(product.getTotalnetamount());
				
			}

		} catch (Exception e) {

			e.printStackTrace();
		}



		return pendingamt;
	}

	private boolean getStockAndGRNDiffernce(String procurementid) {
		boolean flag = false;
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select sum(inventory_procurement.qty+0)+sum(freeqty),sum(stock) from inventory_procurement ");
			buffer.append("inner join inventory_product on inventory_product.id = inventory_procurement.prodid ");
			buffer.append("where procurementid='"+procurementid+"' and procurement=0 ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				if(rs.getInt(1)==rs.getInt(2)){
					flag = true;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	private int getPaymentDoneStatus(String string) {
		int res = 0;
		try {
			String sql = "SELECT sum(payamount) FROM inventory_paymentpo where procurementid='" + string + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				if (rs.getDouble(1) > 0) {
					res = 1;
				} else {
					res = 0;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int getProcurmentSeqNo(String string) {
		PreparedStatement ps = null;
		int res = 0;
		String sql = "select sequenceno from procurement_seqno where seq_procid='" + string + "'";
		try {
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				res = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}

	private boolean isthisProcuremetProductransfer(String procurementid) {

		try {
			InventoryProductDAO productDAO = new JDBCInventoryProductDAO(connection);
			String sql = "select prodid, qty from inventory_procurement where procurementid=" + procurementid + " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			int i = 0, j = 0;
			while (rs.next()) {

				String prodid = rs.getString(1);
				int qty = rs.getInt(2);
				Product product = productDAO.getProduct(prodid);
				double stock =Double.parseDouble(product.getStock());
				if (qty != stock) {
					j++;
				}
				i++;
			}

			if (i == j) {
				return true;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return false;
	}

	private int getSumOfQTy(String procurementid) {

		int result = 0;
		try {

			String sql = "select sum(qty) from inventory_procurement where procurementid=" + procurementid + " ";
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

	public int addTempPo(String pid, String vendorid, String qty, int tid, String childid, String purchaseprice,
			int isagrement, String vat, String loginsessionid, String poDescription, String pohsnno, String isfromworkorder) {

		int result = 0;
		try {

			String sql = "insert into inventory_temp_po (pid,vendorid,qty,temp_id,agreechildid,purchase_price,"
					+ "isagrement,newgstper,loginsessionid,po_Description,po_hsnno,isfromworkorder) "
					+ "values (?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, pid);
			ps.setString(2, vendorid);
			ps.setString(3, qty);
			ps.setInt(4, tid);
			ps.setString(5, childid);
			ps.setString(6, purchaseprice);
			ps.setString(7, "" + isagrement);
			ps.setString(8, vat);
			ps.setString(9, loginsessionid);
			ps.setString(10, poDescription);
			ps.setString(11, pohsnno);
			ps.setString(12, isfromworkorder);
			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public int truncateTempPo(String loginsessionid) {

		int result = 0;

		try {
			String sql = "delete from inventory_temp_po where loginsessionid=?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, loginsessionid);
			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<Product> getAllPoListByVendor(String loginsessionid, String isfromworkorder) {

		ArrayList<Product> list = new ArrayList<Product>();
		try {

			String sql = "SELECT id,vendorid,agreechildid,purchase_price,isagrement,isfromworkorder from inventory_temp_po "
					+ "where loginsessionid=? and isfromworkorder=? group by vendorid ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, loginsessionid);
			ps.setString(2, isfromworkorder);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt(1));
				product.setVendor_id(rs.getString(2));
				product.setChildid(rs.getString(3));
				product.setPurchase_price(rs.getString(4));
				product.setIsagreement(rs.getInt(5));
				product.setIsfromworkorder(rs.getString(6));
				list.add(product);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	public ArrayList<Product> getPoProductByVendor(String vendor_id, String loginsessionid, String isfromworkorder) {

		ArrayList<Product> list = new ArrayList<Product>();

		try {

			String sql = "SELECT pid,qty,temp_id,agreechildid,purchase_price,"
					+ "isagrement,newgstper,po_Hsnno,po_Description " + "from inventory_temp_po where vendorid="
					+ vendor_id + " and loginsessionid=? and isfromworkorder='"+isfromworkorder+"' ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, loginsessionid);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Product product = new Product();
				product.setId(rs.getInt(1));
				product.setProduct_id(rs.getString(1));
				product.setQty(rs.getDouble(2));
				product.setVendor_id(vendor_id);
				product.setNewpo(rs.getInt(3));
				product.setChildid(rs.getString(4));
				product.setPurchase_price(rs.getString(5));
				product.setIsagreement(rs.getInt(6));
				product.setVat("" + rs.getInt(7));
				product.setPoHsnno(rs.getString(8));
				product.setPoDescription(rs.getString(9));
				list.add(product);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	public ArrayList<Procurement> getListProcurement(String procurementid) {

		ArrayList<Procurement> list = new ArrayList<Procurement>();
		try {

			String sql = "SELECT id,prodid,qty,vendorid,purprice,total,mrp,date,voucherno from inventory_procurement where procurementid="
					+ procurementid + " and voucherno is NOT NULL  ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				Procurement procurement = new Procurement();
				procurement.setId(rs.getInt(1));
				procurement.setProd_id(rs.getString(2));
				procurement.setQuantity(rs.getString(3));
				procurement.setVendor_id(rs.getString(4));
				procurement.setPurchase_price(rs.getString(5));
				procurement.setTotal(rs.getString(6));
				procurement.setMrp(rs.getString(7));
				procurement.setDate(rs.getString(8));
				procurement.setVoucherno(rs.getString(9));

				procurement.setProcurementid(procurementid);
				list.add(procurement);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	public ArrayList<Procurement> getSupplierWiseReport(String fromdate, String todate, String location) {

		ArrayList<Procurement> list = new ArrayList<Procurement>();
		try {

			InventoryVendorDAO inventoryVendorDAO = new JDBCInventoryVendorDAO(connection);
			StringBuffer buffer = new StringBuffer();
			buffer.append("select id,prodid,vendorid from inventory_procurement where date between '" + fromdate
					+ "' and '" + todate + "' ");

			if (!location.equals("0") && !location.equals("1")) {
				buffer.append("and inventory_procurement.location='" + location + "' ");
			}
			buffer.append("group by vendorid order by id desc ");

			PreparedStatement ps = connection.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Procurement procurement = new Procurement();
				procurement.setId(rs.getInt(1));
				procurement.setProd_id(rs.getString(2));
				procurement.setVendor_id(rs.getString(3));

				Vendor vendor = inventoryVendorDAO.getVendor(procurement.getVendor_id());
				procurement.setVendor(vendor.getName());
				procurement.setAddress(vendor.getAddress());
				procurement.setCity(vendor.getCityName());
				procurement.setEmail(vendor.getEmail());
				procurement.setContact(vendor.getMobile_pri());

				double total = getTotalAmount(procurement.getVendor_id(), fromdate, todate);
				double paidAmt = getTotalPaid(procurement.getVendor_id(), fromdate, todate);

				double balanceAmt = total - paidAmt;

				procurement.setTotal(DateTimeUtils.changeFormat(total));
				procurement.setPaymentAmount(DateTimeUtils.changeFormat(paidAmt));
				procurement.setBalance(DateTimeUtils.changeFormat(balanceAmt));

				list.add(procurement);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	private double getTotalPaid(String vendor_id, String fromdate, String todate) {
		double result = 0;
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select sum(payamount) from inventory_paymentpo inner join ");
			buffer.append(
					"inventory_parent_procurement on inventory_paymentpo.procurementid= inventory_parent_procurement.id where ");
			buffer.append("inventory_parent_procurement.vendorid=" + vendor_id
					+ " and inventory_paymentpo.commencing between '" + fromdate + "' and '" + todate + "'; ");

			PreparedStatement ps = connection.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				result = rs.getDouble(1);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	private double getTotalAmount(String vendor_id, String fromdate, String todate) {

		double result = 0;
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select sum(total) from inventory_procurement where vendorid=" + vendor_id
					+ " and date between '" + fromdate + "' and '" + todate + "' ");
			PreparedStatement ps = connection.prepareStatement(buffer.toString());

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				result = rs.getDouble(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public int updateProcurementStatus(int product_id, String status) {

		int result = 0;
		try {

			String sql = "update inventory_product set procurement=" + status + " where id=" + product_id + " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Product> getSupplierGraphReport(String fromdate, String todate) {

		ArrayList<Product> list = new ArrayList<Product>();
		try {

			InventoryVendorDAO inventoryVendorDAO = new JDBCInventoryVendorDAO(connection);
			StringBuffer buffer = new StringBuffer();
			buffer.append("select id,prodid,vendorid from inventory_procurement where date between '" + fromdate
					+ "' and '" + todate + "' ");
			buffer.append("group by vendorid order by id desc ");

			PreparedStatement ps = connection.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Product product = new Product();
				product.setId(rs.getInt(1));
				product.setProduct_id(rs.getString(2));
				product.setVendor_id(rs.getString(3));

				Vendor vendor = inventoryVendorDAO.getVendor(product.getVendor_id());
				product.setVendor(vendor.getName());

				double total = getTotalAmount(product.getVendor_id(), fromdate, todate);
				double paidAmt = getTotalPaid(product.getVendor_id(), fromdate, todate);

				double balanceAmt = total - paidAmt;
				product.setTotal(DateTimeUtils.changeFormat(total));
				product.setPaymentAmount(DateTimeUtils.changeFormat(paidAmt));
				product.setBalance(DateTimeUtils.changeFormat(balanceAmt));

				list.add(product);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	public int addNewProduct(String prodid, String batch, String expiry, String qty, String lastModified,
			String location) {

		int result = 0;
		InventoryProductDAO productDAO = new JDBCInventoryProductDAO(connection);
		try {
			// 03 May 2018
			String added_date = "";
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Calendar cal = Calendar.getInstance();
			added_date = dateFormat.format(cal.getTime());
			added_date = DateTimeUtils.getCommencingDate1(added_date);

			Product product = productDAO.getProduct(prodid);

			String sql = "insert into inventory_product (supplierid,brandid,prodname,mrp,purchaseprice,saleprice,categoryid,stock,mdicinenameid,expiry_date,batch_no,vat,genericname,cell,pack,medicine_type,mfg,lastmodified,location,added_date) "
					+ "value (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
			String stock = "0";
			if (product.getMedicine_type() == null) {
				product.setMedicine_type("1");
			}
			if (productDAO.isStrip(product.getMedicine_type())) {
				int s = Integer.parseInt(product.getPack()) * Integer.parseInt(qty);
				stock = String.valueOf(s);
			} else {
				stock = qty;
			}

			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, product.getVendor_id());
			ps.setString(2, product.getBrand_id());
			ps.setString(3, product.getProduct_name());
			ps.setString(4, product.getMrp());
			ps.setString(5, product.getPurchase_price());
			ps.setString(6, product.getSale_price());
			ps.setString(7, "2");
			ps.setString(8, stock);
			ps.setString(9, product.getMedicinenameid());
			ps.setString(10, expiry);
			ps.setString(11, batch);
			ps.setString(12, product.getVat());
			ps.setString(13, product.getGenericname());
			ps.setString(14, product.getShelf());
			ps.setString(15, product.getPack());
			ps.setString(16, product.getMedicine_type());
			ps.setString(17, product.getMfg());
			ps.setString(18, lastModified);
			ps.setString(19, location);
			ps.setString(20, added_date);
			result = ps.executeUpdate();

			if (result > 0) {

				ResultSet rs = ps.getGeneratedKeys();
				while (rs.next()) {
					result = rs.getInt(1);
					// productDAO.addTOMedicineMaster(product);
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	public int saveNewProcurement(Product product) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into inventory_procurement(prodid, purprice, qty, total, mrp,date,vendorid,brandid,procurementid,voucherno,gudreceipt,confirm,location,grnno,catalogueid,voucherdate,discount,p_time,p_userid) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try {

			Date date = Calendar.getInstance().getTime();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String nowdate = dateFormat.format(date);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, product.getProduct_id());
			preparedStatement.setString(2, product.getPurchase_price());
			preparedStatement.setString(3, product.getQuantity());
			preparedStatement.setDouble(4, product.getTotalAmt());
			preparedStatement.setString(5, product.getMrp());
			preparedStatement.setString(6, nowdate);
			preparedStatement.setString(7, product.getVendor_id());
			preparedStatement.setString(8, product.getBrand_id());
			preparedStatement.setString(9, product.getProcurementid());
			preparedStatement.setString(10, product.getVoucherno());
			preparedStatement.setString(11, "1");
			preparedStatement.setString(12, "1");
			preparedStatement.setString(13, product.getLocation());
			preparedStatement.setInt(14, product.getGrnno());
			preparedStatement.setString(15, product.getCatalogueid());
			preparedStatement.setString(16, product.getVoucherdate());
			preparedStatement.setString(17, product.getDiscount());
			preparedStatement.setString(18, product.getTime());
			preparedStatement.setString(19, product.getUserid());

			result = preparedStatement.executeUpdate();
			if (result > 0) {

				ResultSet rs = preparedStatement.getGeneratedKeys();
				while (rs.next()) {
					result = rs.getInt(1);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public int saveNewProduct(Product product) {

		int result = 0;
		try {
			// 03 May 2018
			String added_date = "";
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Calendar cal = Calendar.getInstance();
			added_date = dateFormat.format(cal.getTime());
			added_date = DateTimeUtils.getCommencingDate1(added_date);

			String sql = "insert into inventory_product (supplierid,prodname,categoryid,mdicinenameid,lastmodified,pack,medicine_type,genericname,expiry_date,medicine_shedule,location,catalogueid,procurement,mrp,purchaseprice,saleprice,stock,batch_no,vat,mfg,userid,warehouseid,hsnno,freeqty,added_date) "
					+ " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, product.getVendor_id());
			ps.setString(2, product.getProduct_name());
			ps.setString(3, product.getCategory_id());
			ps.setString(4, product.getMedicinenameid());
			ps.setString(5, product.getLastmodified());
			ps.setString(6, product.getPack());
			ps.setString(7, product.getMedicine_type());
			ps.setString(8, product.getGenericname());
			ps.setString(9, product.getExpiry_date());
			ps.setString(10, product.getMedicine_shedule());
			ps.setString(11, product.getLocation());
			ps.setString(12, product.getCatalogueid());
			ps.setInt(13, product.getProcurement());
			ps.setString(14, product.getMrp());
			ps.setString(15, product.getPurchase_price());
			ps.setString(16, product.getSale_price());
			ps.setString(17, product.getStock());
			ps.setString(18, product.getBatch_no());
			ps.setString(19, product.getVat());
			ps.setString(20, product.getMfg());
			ps.setString(21, product.getUserid());
			ps.setString(22, "1");
			ps.setString(23, product.getHsnno());
			ps.setString(24, product.getFreeqty());
			ps.setString(25, added_date);
			result = ps.executeUpdate();

			if (result > 0) {

				ResultSet rs = ps.getGeneratedKeys();
				while (rs.next()) {

					result = rs.getInt(1);
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public int getVendorProcIfExist(String vendorid, String date) {

		String todate = date + " 23:59:59";
		int result = 0;
		try {
			String sql = "SELECT id FROM inventory_parent_procurement where vendorid=" + vendorid
					+ " and lastmodified between '" + date + "' and '" + todate + "' order by id desc limit 0,1";
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

	public int addVendorAccountProcurement(Procurement procurement) {

		int result = 0;
		try {
			String sql = "insert into inventory_vendor_procurement (procurementid, vat, discount,vendorid,voucherno,"
					+ "date,surcharge,cgst,sgst,igst,security_inward_no,security_inward_date,remark,userid,disctype,"
					+ "paymode,credit,debit) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, procurement.getProcurementid());
			ps.setString(2, procurement.getVat());
			ps.setString(3, procurement.getDiscount());
			ps.setString(4, procurement.getVendor_id());
			ps.setString(5, procurement.getVoucherno());
			ps.setString(6, procurement.getDate());
			ps.setString(7, procurement.getSurcharge());
			ps.setString(8, procurement.getCgst());
			ps.setString(9, procurement.getSgst());
			ps.setString(10, procurement.getIgst());
			ps.setString(11, procurement.getSecurity_no());
			ps.setString(12, procurement.getSecurity_date());
			ps.setString(13, procurement.getRemark());
			ps.setString(14, procurement.getUserid());
			ps.setString(15, procurement.getDisctype());
			ps.setString(16, procurement.getPaymode());
			ps.setString(17, procurement.getCredit());
			ps.setString(18, procurement.getDebit());
			result = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	public int updateVendorAccountProcurement(Procurement procurement) {

		int result = 0;
		try {

			String sql = "update inventory_vendor_procurement set vat=?, discount=?,vendorid=?,voucherno=?,date=?,cgst=?,sgst=?,igst=?,security_inward_no=?,security_inward_date=?,remark=?,disctype=?,paymode=?,credit=?,debit=?,surcharge=? where procurementid="
					+ procurement.getProcurementid() + " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, procurement.getVat());
			ps.setString(2, procurement.getDiscount());
			ps.setString(3, procurement.getVendor_id());
			ps.setString(4, procurement.getVoucherno());
			ps.setString(5, procurement.getDate());
			ps.setString(6, procurement.getCgst());
			ps.setString(7, procurement.getSgst());
			ps.setString(8, procurement.getIgst());
			ps.setString(9, procurement.getSecurity_no());
			ps.setString(10, procurement.getSecurity_date());
			ps.setString(11, procurement.getRemark());
			ps.setString(12, procurement.getDisctype());
			ps.setString(13, procurement.getPaymode());
			ps.setString(14, procurement.getCredit());
			ps.setString(15, procurement.getDebit());
			ps.setString(16, procurement.getSurcharge());

			result = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	public boolean isVoucherExist(String voucher) {

		try {

			String sql = "select id from inventory_procurement where voucherno=? ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, voucher);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				return true;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<Product> getProcurementProductList(String procurementid, int procid) {

		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		ArrayList<Product> list = new ArrayList<Product>();
		double subtotal = 0;

		try {
			boolean islmh =checkIsLmh();
			// String sql="select
			// id,prodid,purprice,qty,total,discount,catalogueid from
			// inventory_procurement where procurementid="+procurementid+" and
			// voucherno is NOT NULL and deleted=0 ";
			StringBuffer buffer = new StringBuffer();
			buffer.append(
					"select id,prodid,purprice,qty,total,discount,catalogueid,location,poDescription,scheme_disc,total_disc_amt from inventory_procurement ");
			buffer.append("where voucherno is NOT NULL and deleted=0 ");
			if (procid > 0) {
				buffer.append("and oldprocid=" + procid + " ");
			} else {
				buffer.append("and procurementid=" + procurementid + " ");
			}
			if(islmh) {
				buffer.append("order by id desc");
			}else {
				buffer.append("order by id asc");
			}

			PreparedStatement ps = connection.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();
			int i = 0;
			int isinadjustment = 0;
			while (rs.next()) {
				Product product = new Product();

				int isinadjustment1 = inventoryProductDAO.checkProductInAdjustment(rs.getString(2));
				if (isinadjustment == 0) {
					isinadjustment = isinadjustment1;
				}
				product.setIsinadjustment(isinadjustment);
				product.setId(rs.getInt(1));
				product.setProduct_id(rs.getString(2));
				product.setQuantity(rs.getString(4));
				double qty = rs.getDouble(4);
				Product master = inventoryProductDAO.getProduct(product.getProduct_id());
				product.setProduct_name(master.getProduct_name());
				product.setPack(master.getPack());
				product.setMedicine_shedule(master.getMedicine_shedule());
				product.setBatch_no(master.getBatch_no());
				product.setExpiry_date(DateTimeUtils.getCommencingDate1(master.getExpiry_date()));
				product.setMfg(master.getMfg());
				product.setMrp(master.getMrp());
				product.setSale_price(master.getSale_price());
				product.setVat(master.getVat());
				product.setPurchase_price(rs.getString(3));
				product.setHsnno(master.getHsnno());
				String catalogueid = rs.getString(7);
				product.setBarcode(master.getBarcode());
				/*
				 * Product cataloguemaster=
				 * inventoryProductDAO.getProductCatalogueDetails(catalogueid);
				 * product.setMinorder(cataloguemaster.getMinorder());
				 * product.setMaxorder(cataloguemaster.getMaxorder());
				 */
				// 23 nov 2017 new expiry format set edit procurment
				try {
					String[] expirydate = master.getExpiry_date().split("-");
					String newexpirydate = expirydate[1] + "/" + expirydate[0];
					// String newexpirydate =
					// expirydate[2]+"-"+expirydate[1]+"-"+expirydate[0];
					product.setNewexpirydate(newexpirydate);
				} catch (Exception e) {
					e.printStackTrace();
				}

				double nowstock = Double.parseDouble(master.getStock());
				int masterfreeqty = Integer.parseInt(master.getFreeqty());
				product.setTotalfreeqty("" + masterfreeqty);
				if (nowstock != (qty + masterfreeqty)) {
					product.setEdit(1);
				}

				String receivedqty = "0";
				String freeqty = "0";
				if (master.getMedicine_type() == null) {
					master.setMedicine_type("1");
				}
				if (master.getMedicine_type().equals("")) {
					master.setMedicine_type("1");
				}

				if (product.getMedicine_shedule() == null) {
					product.setType_name("Regular");
				}
				if (product.getMedicine_shedule().equals("")) {
					product.setType_name("Regular");
				}
				if (product.getMedicine_shedule().equals("0")) {
					product.setType_name("Regular");
				}
				if (product.getMedicine_shedule().equals("1")) {
					product.setType_name("Narcotics");
				}
				if (product.getMedicine_shedule().equals("2")) {
					product.setType_name("H1");
				}

				if (inventoryProductDAO.isStrip(master.getMedicine_type())) {

					if (master.getPack() == null) {
						master.setPack("0");
					}
					if (master.getPack().equals("0") || master.getPack().equals("")) {
						master.setPack("0");
					}
					double pack = Double.parseDouble(master.getPack());
					if (pack == 0) {
						pack = 10;
						product.setPack("10");
					} else {
						product.setPack(master.getPack());
					}
					qty = rs.getDouble(4);
				    double tem = qty / pack;
					receivedqty = String.valueOf(tem);
					double fq = Double.parseDouble(master.getFreeqty()) / pack;
					freeqty = String.valueOf(fq);
				} else {
					receivedqty = rs.getString(4);
					freeqty = master.getFreeqty();
					product.setPack("1");
				}

				product.setQty(Double.parseDouble(receivedqty));
				product.setFreeqty(freeqty);
				product.setShelf(master.getShelf());
				product.setTotal(DateTimeUtils.changeStringFormat(rs.getString(5)));

				double vat = rs.getDouble(5) * Double.parseDouble(master.getVat()) / 100;
				double cgst = vat / 2;
				product.setCgst(DateTimeUtils.changeFormat(cgst));
				product.setDiscount(rs.getString(6));
				subtotal = subtotal + rs.getDouble(5);
				product.setSubTotal(DateTimeUtils.changeFormat(subtotal));
				product.setIndex(i);

				ArrayList<Master> cellList = new ArrayList<Master>();
				ArrayList<Master> newcellList = new ArrayList<Master>();
				String location = rs.getString(8);
				if (!location.equals("0")) {
					cellList = inventoryProductDAO.getcellList(location);
					newcellList = inventoryProductDAO.getcellList(location);
				} else {
					cellList = inventoryProductDAO.getcellList("32,33,34,35,36,105");
					newcellList = inventoryProductDAO.getcellList("32,33,34,35,36,105");
				}

				Product productshelf = inventoryProductDAO.getPreviousShelfId(catalogueid, location);
				ArrayList<Master> cellList1 = new ArrayList<Master>();
				ArrayList<Master> cellList2 = new ArrayList<Master>();
				for (Master master2 : cellList) {
					if (master2.getId() == productshelf.getPreviouscellid()) {
						master2.setStatus(1);
					} else {
						master2.setStatus(2);
					}
					cellList1.add(master2);
				}
				for (Master master3 : newcellList) {
					if (master3.getId() == productshelf.getPreviousseccellid()) {
						master3.setStatus(1);
					} else {
						master3.setStatus(2);
					}
					cellList2.add(master3);
				}
				product.setShelfList(cellList1);
				product.setSecondshelfList(cellList2);
				Product master1 = inventoryProductDAO.getProductCatalogueDetails(catalogueid);
				product.setPro_code(master1.getPro_code());
				product.setUom(master1.getUom());
				product.setLocation(rs.getString(8));
				product.setPoDescription(rs.getString(9));
				product.setStock(master.getStock());
				product.setCatalogueid(catalogueid);
				product.setScheme(rs.getString(10));
				product.setTotalschmedisc(rs.getString(11));
				double amountafterdisc=0;
				if(!rs.getString(10).equals("0") || !product.getDiscount().equals("0")){	
                    double discamt=Double.parseDouble(rs.getString(5))*Double.parseDouble((product.getDiscount())) / 100;
					double schemeamt=Double.parseDouble(rs.getString(5))-(discamt);
					double schmeamtafterdis=0;
					if(!rs.getString(10).equals("0")){
						schmeamtafterdis=schemeamt*(Double.parseDouble(rs.getString(10))/100);
					}
					amountafterdisc=discamt+schmeamtafterdis;
					amountafterdisc =(Math.round(amountafterdisc*100D)/100D);
					
					}
				
				product.setAmountafterdisc(Double.toString(amountafterdisc));
				list.add(product);
				i++;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	private boolean checkIsLmh() {
		// TODO Auto-generated method stub
		try {

			String sql = "select lmh from clinic_access where id=1";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				return true;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return false;
	}

	public ArrayList<Product> getProcurementVoucherProductList(String voucherno) {

		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		ArrayList<Product> list = new ArrayList<Product>();

		try {
			String sql = "select id,prodid,purprice,qty,total from inventory_procurement where voucherno=? ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, voucherno);
			ResultSet rs = ps.executeQuery();
			int i = 0;
			while (rs.next()) {

				Product product = new Product();
				product.setId(rs.getInt(1));
				product.setProduct_id(rs.getString(2));

				Product master = inventoryProductDAO.getProduct(product.getProduct_id());
				product.setProduct_name(master.getProduct_name());
				product.setPack(master.getPack());
				product.setMedicine_shedule(master.getMedicine_shedule());
				product.setBatch_no(master.getBatch_no());
				product.setExpiry_date(DateTimeUtils.getCommencingDate1(master.getExpiry_date()));
				product.setMfg(master.getMfg());
				product.setMrp(master.getMrp());
				product.setHsnno(master.getHsnno());
				product.setSale_price(master.getSale_price());
				product.setVat(master.getVat());
				product.setMedicine_type(master.getMedicine_type());
				product.setPurchase_price(rs.getString(3));
				product.setFreeqty(master.getFreeqty());
				String receivedqty = "0";
				product.setUserid(master.getUserid());
				String freeqty = "0";
				if (master.getMedicine_type() == null) {
					master.setMedicine_type("1");
				}
				if (master.getMedicine_type().equals("")) {
					master.setMedicine_type("1");
				}

				if (inventoryProductDAO.isStrip(master.getMedicine_type())) {

					if (master.getPack() == null) {
						master.setPack("0");
					}
					if (master.getPack().equals("0") || master.getPack().equals("")) {
						master.setPack("0");
					}
					int pack = Integer.parseInt(master.getPack());
					if (pack == 0) {
						pack = 10;
					}
					int qty = rs.getInt(4);
					int tem = qty / pack;
					receivedqty = String.valueOf(tem);
					int fq = Integer.parseInt(master.getFreeqty()) / pack;
					freeqty = String.valueOf(fq);
				} else {
					receivedqty = rs.getString(4);
					freeqty = master.getFreeqty();
				}

				product.setQty(Double.parseDouble(receivedqty));
				product.setShelf(master.getShelf());
				product.setTotal(DateTimeUtils.changeStringFormat(rs.getString(5)));
				product.setIndex(i);
				list.add(product);
				i++;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	public Procurement getProcurementByVoucher(String voucherno) {

		Procurement procurement = new Procurement();

		try {

			String sql = "select prodid, purprice, qty, total, mrp, completepo, date, gudreceipt, confirm, vendorid, brandid,procurementid,voucherno,voucherdate,id from inventory_procurement where voucherno='"
					+ voucherno + "'";
			PreparedStatement ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				procurement.setProduct_id(rs.getString(1));
				procurement.setPurchase_price(rs.getString(2));
				procurement.setQuantity(rs.getString(3));
				procurement.setTotal(rs.getString(4));
				procurement.setMrp(rs.getString(5));
				procurement.setCompletepo(rs.getInt(6));
				procurement.setDate(rs.getString(7));
				procurement.setGudreceipt(rs.getInt(8));
				procurement.setConfirm(rs.getInt(9));
				procurement.setVendor_id(rs.getString(10));
				procurement.setBrand_id(rs.getString(11));
				procurement.setProcurementid(rs.getString(12));
				procurement.setVoucherno(rs.getString(13));
				procurement.setVoucherdate(rs.getString(14));
				procurement.setId(rs.getInt(15));
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return procurement;

	}

	public ArrayList<Product> getVoucherProductList(String vendorid) {

		ArrayList<Product> list = new ArrayList<Product>();
		try {

			String sql = "select id,purprice,qty,total,vendorid,voucherno,voucherdate,procurementid from inventory_procurement where vendorid="
					+ vendorid + " and voucherno is NOT NULL group by voucherno order by id desc ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Product product = new Product();
				product.setId(rs.getInt(1));
				product.setPurchase_price(rs.getString(2));
				product.setQty(rs.getDouble(3));
				product.setTotal(rs.getString(4));
				product.setVendor_id(rs.getString(5));
				product.setVoucherno(rs.getString(6));
				product.setVoucherdate(rs.getString(7));
				product.setProcurementid(rs.getString(8));

				ArrayList<Product> productList = getProductDetailsOfVoucher(product.getVoucherno(),
						product.getProcurementid());
				product.setProductVoucherList(productList);

				list.add(product);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return list;
	}

	private ArrayList<Product> getProductDetailsOfVoucher(String voucherno, String procurementid) {

		ArrayList<Product> list = new ArrayList<Product>();
		InventoryProductDAO productDAO = new JDBCInventoryProductDAO(connection);

		try {

			String sql = "select id,prodid,qty from inventory_procurement where voucherno='" + voucherno
					+ "' and procurementid=" + procurementid + " and qty>0 ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				int id = rs.getInt(1);
				String prodid = rs.getString(2);
				Product product = productDAO.getProduct(prodid);
				product.setQty(rs.getDouble(3));
				product.setId(id);
				product.setVoucherno(voucherno);
				product.setProcurementid(procurementid);
				list.add(product);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return list;
	}

	public Product getProcProdDetails(String id, String qty) {

		Product product = new Product();
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		try {

			String sql = "select  id,prodid,purprice,qty,mrp,vendorid,voucherno from inventory_procurement where id="
					+ id + " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				product = inventoryProductDAO.getProduct(rs.getString(2));
				product.setId(rs.getInt(1));
				product.setProduct_id(rs.getString(2));
				product.setPurchase_price(rs.getString(3));
				product.setMrp(rs.getString(5));
				product.setVendor_id(rs.getString(6));
				product.setVoucherno(rs.getString(7));
				product.setQty(Double.parseDouble(qty));

				if (product.getMedicine_shedule().equals("0")) {
					product.setMedicine_shedule("Regular");
				} else if (product.getMedicine_shedule().equals("1")) {
					product.setMedicine_shedule("Narcotics");
				} else if (product.getMedicine_shedule().equals("2")) {
					product.setMedicine_shedule("H1");
				}

			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return product;
	}

	public int returnToSupplier(Product product) {

		int result = 0;
		try {
			InventoryProductDAO productDAO = new JDBCInventoryProductDAO(connection);

			String sql = "update inventory_procurement set isreturn=1,retqty=?,totalamt=? where id=" + product.getId()
					+ " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			double qty =Double.parseDouble(product.getReceived_qty());
			double total = Integer.parseInt(product.getPurchase_price()) * Double.parseDouble(product.getReceived_qty());
			ps.setString(1, product.getReceived_qty());
			ps.setString(2, DateTimeUtils.changeFormat(total));
			result = ps.executeUpdate();

			if (product.getMedicine_type() == null) {

				product.setMedicine_type("1");
			}
			if (productDAO.isStrip(product.getMedicine_type())) {

				int pack = Integer.parseInt(product.getPack());
				qty = qty * pack;
			}

			result = productDAO.updateMedicineQty(qty, product.getProduct_id(),0);

		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	public double getTotalDebitofVendor(String vendorid) {

		double tot = 0;
		try {
			String sql = "select sum(debit) from inventory_procurement_return where vendorid=" + vendorid + " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				tot = rs.getDouble(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return tot;
	}

	public int saveVendorDebitOrCreditAmt(String vendorid, String debit, String credit, String date) {

		int result = 0;
		try {

			String sql = "insert into inventory_procurement_return (vendorid, voucherno, debit, credit, date) values (?,?,?,?,?) ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, vendorid);
			ps.setString(2, "");
			ps.setString(3, debit);
			ps.setString(4, credit);
			ps.setString(5, date);

			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public double getTotalCreditofvendor(String vendorid) {

		double tot = 0;
		try {
			String sql = "select sum(credit) from inventory_procurement_return where vendorid=" + vendorid + " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				tot = rs.getDouble(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return tot;
	}

	public Product getVendorReturnAccountData(String vendorid) {

		Product product = null;
		try {
			String sql = "select id, vendorid, voucherno, debit, credit, date from inventory_procurement_return where vendorid="
					+ vendorid + " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				product = new Product();
				product.setId(rs.getInt(1));
				product.setVendor_id(rs.getString(2));
				product.setVoucherno(rs.getString(3));
				product.setDebit(rs.getString(4));
				product.setCredit(rs.getString(5));
				product.setDate(rs.getString(6));

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return product;
	}

	public int updateVendorDebitOrCreditAmt(String vendorid, String debit, String credit, String date) {

		int result = 0;
		try {

			String sql = "update inventory_procurement_return set debit=?,credit=?,date=? where vendorid=" + vendorid
					+ " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, debit);
			ps.setString(2, credit);
			ps.setString(3, date);

			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public int savePoVatAllocations(Product product) {

		int result = 0;
		try {

			String sql = "insert into inventory_po_vat_allocations (procurementid, voucherno, vatrate, totalamt, taxable, discount, taxamt) values "
					+ " (?,?,?,?,?,?,?) ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, product.getProcurementid());
			ps.setString(2, product.getVoucherno());
			ps.setString(3, product.getVatrate());
			ps.setString(4, product.getTotalamt());
			ps.setString(5, product.getTaxable());
			ps.setString(6, product.getDiscvat());
			ps.setString(7, product.getTaxamt());

			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public int deleteVatAllocations(String procurementid, String voucherno) {

		int result = 0;
		try {
			String sql = "delete from inventory_po_vat_allocations where  procurementid=" + procurementid
					+ " and voucherno=? ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, voucherno);
			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<Product> getVatAllocationList(String procurementid, String voucherno) {

		ArrayList<Product> list = new ArrayList<Product>();
		try {
			String sql = "select id, vatrate, totalamt, taxable, discount, taxamt from inventory_po_vat_allocations where procurementid="
					+ procurementid + " and voucherno='" + voucherno + "' ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			/*
			 * double ttaxable=0; double ttaxamt=0;
			 */
			while (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt(1));
				product.setProcurementid(procurementid);
				product.setVoucherno(voucherno);
				product.setVatrate(rs.getString(2));
				product.setTotalamt(rs.getString(3));
				product.setTaxable(rs.getString(4));
				product.setDiscvat(rs.getString(5));
				product.setTaxamt(rs.getString(6));

				product.setVatratee(rs.getInt(2));
				product.setTaxableamt(rs.getDouble(4));
				product.setTaxamts(rs.getDouble(6));
				/*
				 * ttaxable = ttaxable +rs.getDouble(4); ttaxamt = ttaxamt
				 * +rs.getDouble(6); product.setTtaxableamt(ttaxable);
				 * product.setTtaxamt(ttaxamt);
				 */
				list.add(product);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	public Product getProcAccountDetails(String procurementid) {

		Product product = new Product();
		try {
			String sql = "select id, vat, discount, vendorid, voucherno, date, surcharge,cgst,sgst,igst,security_inward_no,security_inward_date,remark,userid,paymode,credit,debit,disctype from inventory_vendor_procurement where procurementid="
					+ procurementid + " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				product.setId(rs.getInt(1));
				product.setProcurementid(procurementid);
				product.setVat(DateTimeUtils.changeFormat(rs.getDouble(2)));
				product.setDiscvat(DateTimeUtils.changeFormat(rs.getDouble(3)));
				product.setVendor_id(rs.getString(4));
				product.setVoucherno(rs.getString(5));
				product.setDate(rs.getString(6));
				product.setSurcharge(DateTimeUtils.changeFormat(rs.getDouble(7)));
				product.setCgst(DateTimeUtils.changeFormat(rs.getDouble(8)));
				product.setSgst(DateTimeUtils.changeFormat(rs.getDouble(9)));
				product.setIgst(DateTimeUtils.changeFormat(rs.getDouble(10)));
				product.setSecurity_no(rs.getString(11));
				product.setSecurity_date(rs.getString(12));
				if (product.getSecurity_no() == null) {
					product.setSecurity_no("");
				}
				if (product.getSecurity_date() == null) {
					product.setSecurity_date("");
				}
				product.setRemark(rs.getString(13));
				product.setUserid(rs.getString(14));
				product.setPaymode(rs.getString(15));
				product.setCredit(rs.getString(16));
				product.setDebit(rs.getString(17));
				product.setDisctype(rs.getString(18));

			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return product;
	}

	public Product getPreviousProductDetails(String vendorid, String product_name, Product master) {

		Product product = new Product();
		try {

			String sql = "select id,mrp,purchaseprice,saleprice,vat,mfg,hsnno from inventory_product where prodname like ('%"
					+ product_name + "%') and supplierid=" + vendorid + " and mrp>0 order by id desc limit 0,1 ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				product.setId(rs.getInt(1));
				product.setMrp(rs.getString(2));
				product.setPurchase_price(rs.getString(3));
				product.setSale_price(rs.getString(4));
				product.setVat(rs.getString(5));
				product.setMfg(rs.getString(6));
				product.setHsnno(rs.getString(7));

			}

			if (!rs.next()) {
				return master;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return product;
	}

	public Product getLastMedicineData(String pid, String vendorid) {

		Product product = new Product();
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		try {
			boolean flag = false;
			Product master = inventoryProductDAO.getProduct(pid);
			String product_name = master.getProduct_name();
			String sql = "select id,medicine_type,medicine_shedule,pack,mrp,purchaseprice,saleprice,vat,categoryid,prodtypeid from inventory_product where prodname like ('%"
					+ product_name + "%') and supplierid=" + vendorid + " and mrp>0  order by id desc limit 0,1 ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				product.setId(rs.getInt(1));
				product.setMedicine_type(rs.getString(2));
				product.setMedicine_shedule(rs.getString(3));
				product.setPack(rs.getString(4));
				product.setMrp(rs.getString(5));
				product.setPurchase_price(rs.getString(6));
				product.setSale_price(rs.getString(7));
				product.setVat(rs.getString(8));
				product.setCategory_id(rs.getString(9));
				product.setSubcategory_id(rs.getString(10));

				flag = true;

			}

			if (!flag) {
				return master;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return product;
	}

	public ArrayList<Product> getAllSupplierProductList(String product_name, String pid) {

		ArrayList<Product> list = new ArrayList<Product>();
		try {

			InventoryVendorDAO inventoryVendorDAO = new JDBCInventoryVendorDAO(connection);
			StringBuffer buffer = new StringBuffer();
			/*
			 * buffer.
			 * append("SELECT inventory_procurement.id,purprice,inventory_procurement.qty,inventory_product.vat,  "
			 * );
			 * buffer.append("vendorid,inventory_procurement.date,discount ");
			 * buffer.append("FROM inventory_procurement "); buffer.
			 * append("inner join inventory_product on inventory_procurement.prodid = inventory_product.id "
			 * ); buffer.append("where inventory_product.prodname like ('%"
			 * +product_name+"%') and inventory_procurement.qty>0 ");
			 * buffer.append("group by vendorid order by id desc ");
			 */
			buffer.append("SELECT inventory_procurement.id,purprice,inventory_procurement.qty,inventory_product.vat, ");
			buffer.append(
					"inventory_parent_procurement.vendorid,inventory_procurement.date,discount,procurementid,inventory_product.catalogueid, ");
			buffer.append("freeqty,inventory_product.mrp, saleprice,voucherno,total,inventory_product.pack ");
			buffer.append("FROM inventory_procurement ");
			buffer.append(
					"inner join inventory_parent_procurement on inventory_procurement.procurementid =  inventory_parent_procurement.id ");
			buffer.append("inner join inventory_product on inventory_procurement.prodid = inventory_product.id ");
			buffer.append("where inventory_product.catalogueid='" + pid + "' and inventory_procurement.qty>0 ");
			buffer.append("and isdelivermemo=0 and voucherno is not null ");
			buffer.append("order by id desc limit 0,20 ");
			PreparedStatement ps = connection.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt(1));
				product.setPurchase_price(rs.getString(2));
				product.setQuantity(rs.getString(3));
				product.setVat(rs.getString(4));
				product.setVendor_id(rs.getString(5));
				product.setDate(DateTimeUtils.getCommencingDate1(rs.getString(6)));
				product.setDiscount(rs.getString(7));
				Vendor vendor = inventoryVendorDAO.getVendor(product.getVendor_id());
				product.setVendor(vendor.getName());
				int res = getProcurmentSeqNo(rs.getString(8));
				String proSeqNo = "";
				if (res > 0) {
					proSeqNo = String.valueOf(res);
				} else {
					proSeqNo = rs.getString(8);
				}
				product.setProSeqNo(proSeqNo);
				int freeqty = rs.getInt(10);
				int pack = rs.getInt(15);
				if (pack == 0) {
					pack = 1;
				}
				// freeqty = freeqty*pack;
				double mrp = rs.getDouble(11);
				double saleprice = rs.getDouble(12);
				String voucherno = rs.getString(13);
				double total = rs.getDouble(14);
				product.setVoucherno(voucherno);
				product.setTotal(DateTimeUtils.changeFormat(total));
				product.setMrp(DateTimeUtils.changeFormat(mrp));
				product.setSale_price(DateTimeUtils.changeFormat(saleprice));
				product.setFreeqty("" + freeqty);

				double discountper = getDiscountAmt(rs.getString(8), "");
				double discountamt = (total / 100) * discountper;
				product.setDiscount(DateTimeUtils.changeFormat(Math.round(discountamt * 100.00) / 100.00));
				list.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Product> getProcurementListBeforeProcurement(String procurementid) {
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		ArrayList<Product> list = new ArrayList<Product>();
		try {
			String sql = "select id,prodid,purprice,qty,total,discount,longpo,date,grnno,catalogueid,location,"
					+ "isagreement,child_agreementid,procurementid,vendorid,parentpoid,gstper,poHsnno,"
					+ "poDescription,inventory_procurement.iscancel "
					+ "from inventory_procurement where procurementid=" + procurementid + " order by id desc ";
			
			
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			double subtotal = 0;
			double netsubtotal = 0;
			double totalgstamount = 0;
			while (rs.next()) {
				Product product = new Product();
				product.setDiscount(DateTimeUtils.changeFormat(rs.getDouble(6)));
				product.setId(rs.getInt(1));
				product.setProduct_id(rs.getString(2));
				product.setQuantity(rs.getString(4));
				product.setNewpo(rs.getInt(7));
				product.setDate(DateTimeUtils.getCommencingDate1(rs.getString(8)));
				product.setGrnno(rs.getInt(9));
				product.setCatalogueid(rs.getString(10));
				Product master = null;
				if (!product.getCatalogueid().equals("0")) {
					master = inventoryProductDAO.getProductCatalogueDetails(product.getCatalogueid());
				} else {
					master = inventoryProductDAO.getProduct(product.getProduct_id());
				}
				// double rate= Double.parseDouble(master.getPurchase_price());
				double rate = rs.getDouble(3);
				product.setProduct_name(master.getProduct_name());
				double pack = Double.parseDouble(master.getPack());
				if (pack == 0) {
					master.setPack("1.0");
				}
				product.setUom(master.getUom());
				product.setPro_code(master.getPro_code());
				double total = rs.getDouble(4) * rate;
				subtotal = subtotal + total;
				product.setSubTotal(String.format("%.2f", Math.round(subtotal * 100.0) / 100.0));
				product.setTotal(String.format("%.2f", Math.round(total * 100.0) / 100.0));
				product.setMrp(master.getMrp());
				product.setSale_price(master.getSale_price());
				/* product.setPurchase_price(master.getPurchase_price()); */
				product.setPurchase_price(rs.getString(3));
				product.setVendor_id("" + rs.getInt(15));
				product.setBatch_no(master.getBatch_no());
				product.setPack(master.getPack());
				product.setMfg(master.getMfg());
				product.setHsnno(master.getHsnno());

				product.setVat("" + rs.getInt(17));
				product.setSale_price(master.getSale_price());
				// 23 June 2018
				double purchaseprice = rate;
				double vat = rs.getDouble(17);
				/*
				 * if(master.getVat()!=null){ if(!master.getVat().equals("")){
				 * vat = Double.parseDouble(master.getVat()); } }
				 */
				double qty = rs.getDouble(4);
				double netamount = (purchaseprice * vat) / 100;
				netamount = netamount * qty;
				
				double tempgst = netamount * rs.getDouble(6) /100;
				netamount = netamount - tempgst;
				
				netamount = Math.round(netamount * 100.0) / 100.0;
				product.setGstamount(String.format("%.2f", Math.round(netamount * 100.0) / 100.0));
				totalgstamount = totalgstamount + netamount;
				product.setTotalgstamount(String.format("%.2f", Math.round(totalgstamount * 100.0) / 100.0));

				/*double nettotal = netamount + total;*/
				double nettotal = total;
				double temp = (nettotal * rs.getDouble(6)) / 100;
				nettotal = nettotal - temp;
				nettotal = nettotal + netamount;
				product.setNetamount(String.format("%.2f", Math.round(nettotal * 100.0) / 100.0));

				netsubtotal = netsubtotal + nettotal;
				product.setTotalnetamount(String.format("%.2f", Math.round(netsubtotal * 100.0) / 100.0));
				// lokesh
				product.setStockqty(getStock(product.getCatalogueid(), rs.getString(11)));
				double oldpurchaseprice = getoldpurprice(product.getCatalogueid());
				product.setOldvenderpurprise(oldpurchaseprice);
				String location = rs.getString(11);
				if (location == null) {
					location = "0";
				} else {
					if (location.equals("")) {
						location = "0";
					}
				}
				ArrayList<Master> cellList = new ArrayList<Master>();
				ArrayList<Master> newcellList = new ArrayList<Master>();
				if (!location.equals("0")) {
					cellList = inventoryProductDAO.getcellList(location);
					newcellList = inventoryProductDAO.getcellList(location);
				} else {
					cellList = inventoryProductDAO.getcellList("32,33,34,35,36,105");
					newcellList = inventoryProductDAO.getcellList("32,33,34,35,36,105");
				}

				Product productshelf = inventoryProductDAO.getPreviousShelfId(rs.getString(10), location);
				ArrayList<Master> cellList1 = new ArrayList<Master>();
				ArrayList<Master> cellList2 = new ArrayList<Master>();
				for (Master master2 : cellList) {
					if (master2.getId() == productshelf.getPreviouscellid()) {
						master2.setStatus(1);
					} else {
						master2.setStatus(2);
					}
					cellList1.add(master2);
				}
				for (Master master3 : newcellList) {
					if (master3.getId() == productshelf.getPreviousseccellid()) {
						master3.setStatus(1);
					} else {
						master3.setStatus(2);
					}
					cellList2.add(master3);
				}
				product.setShelfList(cellList1);
				product.setSecondshelfList(cellList2);
				product.setPro_code(master.getPro_code());
				product.setIsagreement(rs.getInt(12));
				product.setChildagreeid(rs.getString(13));
				product.setProcurementid(rs.getString(14));
				product.setParentpoid(rs.getInt(16));
				product.setLocation(location);

				double gst = rs.getDouble(17);
				double cgst = 0;
				double sgst = 0;
				if (gst != 0) {
					cgst = gst / 2;
					sgst = cgst;
				}
				product.setCgst("" + cgst);
				product.setSgst("" + sgst);

				product.setPoHsnno(rs.getString(18));
				product.setPoDescription(rs.getString(19));
				product.setDeleted(rs.getInt(20));
				if(!DateTimeUtils.isNull(rs.getString(18)).equals("")){
					product.setHsnno(rs.getString(18));
				}
				

				list.add(product);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	public Product getLastProductDetails(String product_name) {

		Product product = new Product();
		try {
			StringBuffer sql = new StringBuffer();
			sql.append(
					"select inventory_product.id,inventory_product.mrp,inventory_product.purchaseprice,inventory_product.saleprice,inventory_product.supplierid,hsnno,mfg,batch_no,vat,inventory_procurement.discount,inventory_procurement.vendorid,pack,saleprice ");
			sql.append(
					"from inventory_product inner join inventory_procurement on inventory_procurement.prodid = inventory_product.id ");
			sql.append("where inventory_product.prodname like ('%" + product_name + "%')  ");
			sql.append("and inventory_procurement.mrp>0 order by inventory_product.id desc limit 0,1 ;");
			PreparedStatement ps = connection.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				product.setId(rs.getInt(1));
				product.setMrp(rs.getString(2));
				product.setPurchase_price(rs.getString(3));
				if (product.getPurchase_price() == null) {
					product.setPurchase_price("0.00");
				}
				product.setSale_price(rs.getString(4));
				product.setVendor_id(rs.getString(5));
				product.setHsnno(rs.getString(6));
				product.setMfg(rs.getString(7));
				product.setBatch_no(rs.getString(8));
				product.setVat(rs.getString(9));
				product.setDiscount(rs.getString(10));
				product.setVendor_id(rs.getString(11));
				product.setPack(rs.getString(12));
				product.setSale_price(rs.getString(13));
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return product;
	}

	public Product getLastProductDetailsOfSupplier(String product_name, String vendorid) {

		Product product = new Product();
		try {
			StringBuffer sql = new StringBuffer();
			sql.append(
					"select inventory_product.id,inventory_product.mrp,inventory_product.purchaseprice,inventory_product.saleprice,inventory_product.supplierid,hsnno,mfg,batch_no,vat,inventory_procurement.discount,inventory_product.prodname ");
			sql.append(
					"from inventory_product inner join inventory_procurement on inventory_procurement.prodid = inventory_product.id ");
			sql.append("where inventory_product.prodname like ?  ");
			sql.append("and inventory_procurement.mrp>0 and inventory_procurement.vendorid=" + vendorid
					+ " order by inventory_product.id desc limit 0,1 ;");
			PreparedStatement ps = connection.prepareStatement(sql.toString());
			ps.setString(1, "'%" + product_name + "%'");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				product.setId(rs.getInt(1));
				product.setMrp(rs.getString(2));
				product.setPurchase_price(rs.getString(3));
				if (product.getPurchase_price() == null) {
					product.setPurchase_price("0.00");
				}
				product.setSale_price(rs.getString(4));
				product.setVendor_id(rs.getString(5));
				product.setHsnno(rs.getString(6));
				product.setMfg(rs.getString(7));
				product.setBatch_no(rs.getString(8));
				product.setVat(rs.getString(9));
				product.setDiscount(rs.getString(10));
				product.setProduct_name(rs.getString(11));
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return product;
	}

	public ArrayList<Product> getRequestedPoList(String location, Pagination pagination, String searchtext, 
			String supplierPoId, String locationPoId) {

		ArrayList<Product> list = new ArrayList<Product>();
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		InventoryVendorDAO inventoryVendorDAO = new JDBCInventoryVendorDAO(connection);
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("SELECT temppo.id,inventory_catalogue.id,prod_id,sum(temppo.qty),date,newpo,vendorid, ");
			buffer.append("parentid,indent_no,sum(stock),minorder,from_location,inventory_parent_transfer_log.userid ");
			buffer.append("from (SELECT * FROM temp_po ORDER BY id DESC) AS temppo ");
			buffer.append("left join  inventory_catalogue on inventory_catalogue.id = temppo.catalogueid ");
			buffer.append("left join inventory_product on inventory_product.catalogueid = inventory_catalogue.id ");
			buffer.append("left join inventory_parent_transfer_log on inventory_parent_transfer_log.id = temppo.parentid ");
			buffer.append("where status=0  ");
			buffer.append("and procurement=0 ");
			if (!location.equals("0")) {
				buffer.append("and inventory_catalogue.location=" + location + " and inventory_product.location="
						+ location + " ");
			}
			if (searchtext != null) {
				buffer.append("and (product_name like '%" + searchtext + "%' or product_code like '%" + searchtext
						+ "%' or inventory_product.genericname like '%" + searchtext + "%') ");
			}
			if(!supplierPoId.equals("0")){
				buffer.append("and vendorid='"+supplierPoId+"' ");
			}
			if(!locationPoId.equals("0")){
				buffer.append("and from_location='"+locationPoId+"' ");
			}
			/*
			 * buffer.
			 * append("group by inventory_catalogue.id having sum(temppo.qty)>sum(stock) order by temppo.id desc "
			 * );
			 */
			buffer.append("group by inventory_catalogue.id having sum(stock)<=minorder order by temppo.id desc ");
			String sql = buffer.toString();
			if (searchtext == null) {
				if (pagination != null) {
					sql = pagination.getSQLQuery(sql);
				}
			}
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			// ArrayList<Vendor> vendorlist =
			// inventoryVendorDAO.getAllVendorList(location);

			while (rs.next()) {

				Product product = new Product();
				product.setId(rs.getInt(1));
				product.setCatalogueid(rs.getString(2));
				product.setProduct_id(rs.getString(3));
				Product master = null;
				if (rs.getInt(2) > 0) {
					master = inventoryProductDAO.getProductCatalogueDetails(product.getCatalogueid());
				} else {
					master = inventoryProductDAO.getProduct(product.getProduct_id());
				}
				/*
				 * int stock= inventoryProductDAO.getTotalStockProduct(product.
				 * getCatalogueid());
				 */
				// int stock=
				// inventoryProductDAO.getStockAvailableNew(product.getCatalogueid(),location);
				double stock = rs.getDouble(10);
				product.setStock(Double.toString(stock));
				product.setQty(rs.getDouble(4));
				product.setDate(rs.getString(5));
				product.setMaxorder(master.getMaxorder());
				product.setMinorder(master.getMinorder());
				product.setNewpo(rs.getInt(6));
				product.setProduct_name(master.getProduct_name());
				product.setCatalogueid(master.getCatalogueid());
				/*
				 * if(rs.getInt(6)==1){ product.setVendor_id(rs.getString(7));
				 * //product.setVendorList(getAllVendorOfProduct(product.
				 * getCatalogueid(),rs.getString(7)));
				 * //product.setVendorlist(vendorlist); } else {
				 * //product.setVendorList(getAllVendorOfProduct(product.
				 * getCatalogueid(),master.getVendor_id()));
				 * product.setVendor_id(master.getVendor_id());
				 * //product.setVendorlist(vendorlist); }
				 */
				product.setVendor_id(rs.getString(7));
				Vendor vendor = inventoryVendorDAO.getVendor(product.getVendor_id());
				product.setVendor(vendor.getName());
				Product masterlast = getLastProductDetailsOfSupplier(master.getProduct_name(), master.getVendor_id());
				double rate = 0;
				if (DateTimeUtils.isNull(masterlast.getPurchase_price()).equals("")) {
					rate = 0;
				} else {
					rate = Double.parseDouble(masterlast.getPurchase_price());
				}
				double purchaserate = 0;
				if (master.getPurchase_price() != null) {
					if (!master.getPurchase_price().equals("")) {
						purchaserate = Double.parseDouble(master.getPurchase_price());
					}
				}

				if (rate <= 0) {
					product.setPurchase_price(master.getPurchase_price());
					product.setVat(master.getVat());
				} else {
					if (purchaserate <= 0) {
						product.setPurchase_price(DateTimeUtils.changeStringFormat(masterlast.getPurchase_price()));
						product.setVat(masterlast.getVat());
					} else {
						product.setPurchase_price(DateTimeUtils.changeFormat(purchaserate));
						product.setVat(masterlast.getVat());
					}

				}
				product.setFreeqty(master.getFreeqty());

				//Product product2 = inventoryProductDAO.getParentTransferData(rs.getString(8));
				String fromlocation = inventoryProductDAO.pharmacyLocationNameByID(rs.getString(12));
				product.setFrom_location(fromlocation);
				product.setReq_userid(rs.getString(13));
				product.setIndent(rs.getInt(9));
				// lokesh
				if (master.getPro_code() == null) {
					master.setPro_code("");
				}
				product.setPro_code(master.getPro_code());
				String data=product.getName();
				if(!DateTimeUtils.isNull(product.getSuppliercode()).equals("")){
					data = data +" - "+product.getSuppliercode();
				}
				product.setData(data);
				list.add(product);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	public ArrayList<Product> getAllVendorOfProduct(String catalogueid, String vendorid) {

		ArrayList<Product> list = new ArrayList<Product>();
		if (vendorid == null) {
			vendorid = "0";
		}
		if (vendorid.equals("")) {
			vendorid = "0";
		}
		int tid = Integer.parseInt(vendorid);
		try {

			String sql = "select id,name from inventory_vendor where name is not null order by name";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				if (tid == rs.getInt(1)) {
					product.setOn(1);
				}
				product.setId(rs.getInt(1));
				product.setName(rs.getString(2));
				list.add(product);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	public int saveNewTempPo(Product product) {

		int result = 0;
		try {
			String sql = "insert into temp_po (prod_id, status, parentid, qty, date, newpo,"
					+ "vendorid,warehouseid,prod_name,catalogueid,workorder) value (?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, product.getProduct_id());
			ps.setString(2, "0");
			ps.setString(3, "0");
			ps.setDouble(4, product.getQty());
			ps.setString(5, product.getDate());
			ps.setString(6, "1");
			ps.setString(7, product.getVendor_id());
			ps.setString(8, product.getWarehouse_id());
			ps.setString(9, product.getProduct_name());
			ps.setString(10, product.getCatalogueid());
			ps.setString(11, product.getIsfromworkorder());
			result = ps.executeUpdate();

			if (result > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				while (rs.next()) {
					result = rs.getInt(1);
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public int deleteNewGrn(String id) {

		int result = 0;
		try {
			String sql = "delete from temp_po where id=" + id + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			result = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public int updateTempPoStatus(int id) {

		int result = 0;
		try {
			String sql = "update temp_po set status=1 where id=" + id + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Product> getProcRequestedList(String procurementid) {

		ArrayList<Product> list = new ArrayList<Product>();
		try {
			String sql = "select longpo from inventory_procurement where procurementid=" + procurementid
					+ " and longpo!=0 ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				Product product = getTempPo(rs.getString(1));
				list.add(product);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	private Product getTempPo(String id) {
		Product product = new Product();
		InventoryProductDAO productDAO = new JDBCInventoryProductDAO(connection);
		try {
			String sql = "select catalogueid,prod_id,qty,date from temp_po where id=" + id + " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				int catalogueid = rs.getInt(1);
				if (catalogueid != 0) {
					product = productDAO.getProductCatalogueDetails(String.valueOf(catalogueid));
				} else {
					product = productDAO.getProduct(rs.getString(2));
				}
				product.setQty(rs.getDouble(3));
				String date = rs.getString(4);
				String[] data = date.split(" ");
				String date2 = DateTimeUtils.getCommencingDate1(data[0]);
				String datetime = "";
				if (data.length > 1) {
					datetime = date2 + " " + data[1];
				} else {
					datetime = date2;
				}
				product.setDate(datetime);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return product;
	}

	public int updateQtyByAdmin(String qty, int id) {
		int res = 0;
		try {

			String sql = "update inventory_procurement set qty=" + qty + ", approve_qty='" + qty + "' where id=" + id
					+ " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			res = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return res;
	}

	public int addConfirmProcurement(String remark, String procurementid) {

		int result = 0;
		try {
			String sql = "insert into inventory_confirm_procuremt (procurementid, remark) values (?,?) ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, procurementid);
			ps.setString(2, remark);
			result = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public Vendor getVendorDetailsOfProcurement(String procurementid) {

		Vendor vendor = new Vendor();
		InventoryVendorDAO inventoryVendorDAO = new JDBCInventoryVendorDAO(connection);
		try {

			String sql = "select vendorid,podiscamt from inventory_parent_procurement where id=" + procurementid + " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				String vendorid = rs.getString(1);
				vendor = inventoryVendorDAO.getVendor(vendorid);
				vendor.setPodiscamt(rs.getDouble(2));
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return vendor;
	}

	public String getAdminPoRemark(String procurementid) {

		String str = "";
		try {
			String sql = "select remark from inventory_confirm_procuremt where procurementid=" + procurementid + " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				str = rs.getString(1);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return str;
	}

	public int updateTempPoQtyByAdmin(String qty, int newpo) {

		int res = 0;
		try {
			String sql = "update temp_po set qty=" + qty + " where id=" + newpo + "";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return res;
	}

	public boolean isThisProductExist(String procurementid, String catalogueid) {

		try {
			String sql = "select id from inventory_procurement where catalogueid=" + catalogueid + " and procurementid="
					+ procurementid + " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				return true;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return false;
	}

	public int saveParentGrnData(String vendor_id, String date, boolean hidecalinpoprint, String grnYearSeq, String isfromworkorder) {
		int result = 0;
		try {
			String sql = "insert into inventory_po_grn (vendorid, date,hidecalinpoprint,poSequence,grnworkorder) values (?,?,?,?,?) ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, vendor_id);
			ps.setString(2, date);
			ps.setBoolean(3, hidecalinpoprint);
			ps.setString(4, grnYearSeq);
			ps.setString(5, isfromworkorder);
			result = ps.executeUpdate();
			if (result > 0) {

				ResultSet rs = ps.getGeneratedKeys();
				while (rs.next()) {
					result = rs.getInt(1);
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public Product getGrnDetails(int grnno) {
		Product product = new Product();
		try {
			String sql = "select id, vendorid, date,subject_msg,mail_content,hidecalinpoprint,"
					+ "noteSheetNo,rateContractNo,poDepartmentId,poSequence,supplierRefNo,grnworkorder,noteSheetDate,"
					+ "durationFrom,durationTo,amcCmc "
					+ "from inventory_po_grn where id="+ grnno + " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			while (rs.next()) {
				product.setId(rs.getInt(1));
				product.setVendor(rs.getString(2));
				product.setDate(DateTimeUtils.getDBDate(rs.getString(3)));
				product.setSubject_msg(rs.getString(4));
				product.setMail_content(rs.getString(5));
				product.setHidecalinpoprint(rs.getBoolean(6));
				product.setNoteSheetNo(DateTimeUtils.isNull(rs.getString(7)));
				product.setRateContractNo(DateTimeUtils.isNull(rs.getString(8)));
				product.setPoDepartmentId(DateTimeUtils.numberCheck(rs.getString(9)));
				String poDepartment = inventoryProductDAO.pharmacyLocationNameByID(rs.getString(9));
				product.setPoDepartment(poDepartment);
				product.setPoSequence(rs.getString(10));
				product.setSupplierRefNo(rs.getString(11));
				product.setIsfromworkorder(DateTimeUtils.numberCheck(rs.getString(12)));
				product.setNoteSheetDate(DateTimeUtils.getCommencingDate1(rs.getString(13)));
				product.setDurationFrom(DateTimeUtils.getCommencingDate1(rs.getString(14)));
				product.setDurationTo(DateTimeUtils.getCommencingDate1(rs.getString(15)));
				product.setAmcCmc(rs.getString(16));
				product.setGrnno(grnno);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}

	public Procurement getProcurementDataByProcurementID(String procurementid) {

		Procurement procurement = new Procurement();
		try {
			String sql = "select id,prodid,grnno,date,vendorid,location from inventory_procurement where procurementid="
					+ procurementid + " order by id desc limit 0,1 ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				procurement.setId(rs.getInt(1));
				procurement.setProd_id(rs.getString(2));
				procurement.setGrnno(rs.getString(3));
				procurement.setDate(DateTimeUtils.getCommencingDate1(rs.getString(4)));
				procurement.setVendor_id(rs.getString(5));
				procurement.setLocation(rs.getString(6));
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return procurement;
	}

	public int getVendorGrnIfExist(String vendorid, String nowdate) {

		String todate = nowdate + " 23:59:59";
		int result = 0;
		try {
			String sql = "SELECT id FROM inventory_po_grn where vendorid=" + vendorid + " and date between '" + nowdate
					+ "' and '" + todate + "' order by id desc limit 0,1";
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

	public String getCatalogueIdFromProduct(int id) {

		String result = "0";
		try {
			String sql = "select catalogueid from inventory_product where id=" + id + " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				result = rs.getString(1);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public int deleteVendorProcurementDetails(String voucherno, String procurementid) {

		int result = 0;
		try {

			String sql = "delete from inventory_vendor_procurement where procurementid=? and voucherno=? ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, procurementid);
			ps.setString(2, voucherno);

			result = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Vendor> getVendorListBylocationWise(String location) {
		PreparedStatement preparedStatement = null;
		ArrayList<Vendor> list = new ArrayList<Vendor>();
		String sql = "";
		if (!location.equals("0")) {
			sql = "SELECT id,name FROM inventory_vendor where location='" + location + "' order by name";
		} else {
			sql = "SELECT id,name FROM inventory_vendor order by name";
		}

		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Vendor vendor = new Vendor();
				vendor.setId(rs.getInt(1));
				vendor.setName(rs.getString(2));
				list.add(vendor);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int getTotalTodayGRN(String fromdate, String todate, String location) {
		int res = 0;
		try {
			/*
			 * select count(*) from inventory_procurement inner join
			 * inventory_product on
			 * inventory_procurement.prodid=inventory_product.id where
			 * gudreceipt=1 and inventory_procurement.deleted=0 and date between
			 * '2017-09-07' and '2017-09-07';
			 */

			// String sql ="select count(*) from inventory_procurement where
			// gudreceipt=1 and date between '"+fromdate+"' and '"+fromdate+"'";
			// String sql ="";
			StringBuffer buffer = new StringBuffer();
			buffer.append("select inventory_procurement.id from inventory_procurement ");
			buffer.append(
					"inner join inventory_parent_procurement on inventory_procurement.procurementid = inventory_parent_procurement.id ");
			buffer.append("inner join inventory_product on inventory_procurement.prodid=inventory_product.id ");
			buffer.append(
					"where gudreceipt=1 and inventory_procurement.deleted=0 and isdelivermemo='0' and date between '"
							+ fromdate + "' and '" + todate + "' ");
			if (!location.equals("0")) {
				buffer.append("and inventory_procurement.location='" + location + "' ");
			}
			buffer.append(
					"and inventory_procurement.voucherno is not null group by inventory_procurement.procurementid ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				res++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public boolean hasLocationToUser(int clinicid) {

		try {
			int res = 0;
			String sql = "select haslocation from apm_user where id=" + clinicid + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				res = rs.getInt(1);
			}

			if (res == 1) {
				return true;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return false;
	}

	public int saveUpDeleteProcurment(String id, String delete_reason, String userid, String loc) {
		int result = 0;
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			String date = dateFormat.format(cal.getTime());

			String sql = "insert into inventory_product_log (productid, userid, location,  date, deleted, comment, isindent, procurmentid) values (?,?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "0");
			preparedStatement.setString(2, userid);
			preparedStatement.setString(3, loc);
			preparedStatement.setString(4, date);
			preparedStatement.setString(5, "1");
			preparedStatement.setString(6, delete_reason);
			preparedStatement.setString(7, "3");
			preparedStatement.setString(8, id);
			result = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getTotalRequestedPOCount(String location, String searchtext, String supplierPoId, String locationPoId) {

		int res = 0;
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("SELECT  temppo.id,minorder ");
			buffer.append("from (SELECT * FROM temp_po ORDER BY id DESC) AS temppo ");
			buffer.append("left join  inventory_catalogue on inventory_catalogue.id = temppo.catalogueid ");
			buffer.append("left join inventory_product on inventory_product.catalogueid = inventory_catalogue.id ");
			buffer.append("left join inventory_parent_transfer_log on inventory_parent_transfer_log.id = temppo.parentid ");
			buffer.append("where status=0 and procurement=0  ");
			if (!location.equals("0")) {
				buffer.append("and inventory_catalogue.location=" + location + " and inventory_product.location="
						+ location + " ");
			}
			if (searchtext != null) {
				buffer.append("and (product_name like '%" + searchtext + "%' or product_code like '%" + searchtext
						+ "%' or inventory_product.genericname like '%" + searchtext + "%') ");
			}
			if(!supplierPoId.equals("0")){
				buffer.append("and vendorid='"+supplierPoId+"' ");
			}
			if(!locationPoId.equals("0")){
				buffer.append("and from_location='"+locationPoId+"' ");
			}
			buffer.append("group by inventory_catalogue.id having sum(stock)<=minorder order by temppo.id desc ");
			PreparedStatement ps = connection.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();
			if (rs.last()) {
				res = rs.getRow();
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return res;
	}

	public Procurement getProcurementDetails(String product_id) {

		Procurement procurement = new Procurement();

		try {

			String sql = "select prodid, purprice, qty, total, mrp, completepo, date, gudreceipt, confirm, vendorid, brandid,procurementid,voucherno,voucherdate,location,longpo,p_time,p_userid,grnno,id from inventory_procurement where prodid="
					+ product_id + "";
			PreparedStatement ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				procurement.setProduct_id(rs.getString(1));
				procurement.setPurchase_price(rs.getString(2));
				procurement.setQuantity(rs.getString(3));
				procurement.setTotal(rs.getString(4));
				procurement.setMrp(rs.getString(5));
				procurement.setCompletepo(rs.getInt(6));
				procurement.setDate(rs.getString(7));
				procurement.setGudreceipt(rs.getInt(8));
				procurement.setConfirm(rs.getInt(9));
				procurement.setVendor_id(rs.getString(10));
				procurement.setBrand_id(rs.getString(11));
				procurement.setProcurementid(rs.getString(12));
				procurement.setVoucherno(rs.getString(13));
				procurement.setVoucherdate(rs.getString(14));
				procurement.setLocation(rs.getString(15));
				procurement.setLongpo(rs.getInt(16));
				procurement.setTime(rs.getString(17));
				procurement.setUserid(rs.getString(18));
				procurement.setGrnno(rs.getString(19));
				procurement.setId(rs.getInt(20));

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return procurement;
	}

	public String getVouchernoByProcurementid(String procurementid) {

		String res = "0";
		try {

			String sql = "select voucherno from inventory_procurement where procurementid=" + procurementid
					+ " and voucherno is NOT NULL ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				res = rs.getString(1);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return res;
	}

	public int saveParentGrnReturnData(String vendor_id, String date) {

		int res = 0;
		try {

			String sql = "insert into inventory_return_grn_parent (vendorid,datetime) values (?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, vendor_id);
			ps.setString(2, date);

			res = ps.executeUpdate();
			if (res > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				while (rs.next()) {
					res = rs.getInt(1);
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<Product> getAllGrnReturnProductList(String grnreturnid) {

		ArrayList<Product> list = new ArrayList<Product>();
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		InventoryVendorDAO inventoryVendorDAO = new JDBCInventoryVendorDAO(connection);

		try {
			String sql = "SELECT id, productid, vendorid, qty, datetime, location, userid, status, procurementid,sec_out_no,sec_out_date,invoice_date,notes,newprodid,newprocid from inventory_product_return_log where grnreturnid="
					+ grnreturnid + "  ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			int isinadjustment = 0;
			while (rs.next()) {
				Product product = new Product();
				int isinadjustment1 = inventoryProductDAO.checkProductInAdjustment(rs.getString(2));
				if (isinadjustment == 0) {
					isinadjustment = isinadjustment1;
				}
				product.setIsinadjustment(isinadjustment);
				product.setId(rs.getInt(1));
				product.setProduct_id(rs.getString(2));
				product.setVendor_id(rs.getString(3));
				product.setQty(rs.getDouble(4));
				product.setDateTime(rs.getString(5));
				product.setLocation(rs.getString(6));
				product.setUserid(rs.getString(7));
				product.setStatus(rs.getString(8));
				product.setProcurementid(rs.getString(9));
				product.setSecurity_no(rs.getString(10));
				product.setSecurity_date(rs.getString(11));
				product.setDate(rs.getString(12));
				product.setNotes(rs.getString(13));
				product.setGrnreturnid(grnreturnid);
				if (rs.getInt(9) == 0) {
					product.setProcurementid("" + rs.getInt(15));
				} else {
					product.setProcurementid("" + rs.getInt(9));
				}
				Vendor vendor = inventoryVendorDAO.getVendor(product.getVendor_id());

				Product master = inventoryProductDAO.getProduct(product.getProduct_id());
				product.setProduct_name(master.getProduct_name());
				product.setVendor(vendor.getName());
				product.setBatch_no(master.getBatch_no());
				product.setExpiry_date(master.getExpiry_date());
				product.setStock(master.getStock());
				product.setPurchase_price(master.getPurchase_price());
				// 22 NOV 2018 New filed added and calculation of data

				double discountper = getDiscountAmt(product.getProcurementid(), "");
				Product freeqtyproduct = getAvailableFreeQty(product.getProcurementid(), master.getCatalogueid());
				product.setVat(master.getVat());
				product.setMfg(master.getMfg());
				product.setHsnno(master.getHsnno());
				product.setMrp(master.getMrp());
				product.setPack(master.getPack());
				product.setProc_childid("" + freeqtyproduct.getId());
				double stock =Double.parseDouble(master.getStock());
				double qty = rs.getDouble(4);
				/*
				 * if(qty>stock){ qty = stock; }
				 */
				double tempqty = qty;
				double returnfreeqty = 0.0;
				if (freeqtyproduct.getRemainsqty() > qty) {
					returnfreeqty = tempqty;
					qty = 0;
				} else {
					double temp = qty - freeqtyproduct.getRemainsqty();
					qty = temp;
					returnfreeqty = tempqty - temp;
				}
				product.setReturnfreeqty(returnfreeqty);
				product.setReturnqty(qty);
				product.setFreeqty(master.getFreeqty());
				product.setRemainfreereturnqty(freeqtyproduct.getRemainsqty());
				/*
				 * double total = qty*
				 * Double.parseDouble(product.getPurchase_price());
				 */
				double total = qty * master.getIndividual_pur_price();
				total = Math.round(total * 100.0) / 100.0;

				double temptot = 0;
				double tempvat = 0;
				tempvat = total * discountper / 100;
				temptot = total - tempvat;

				double vatamt = temptot * (Double.parseDouble(master.getVat()) / 100.00);
				/*
				 * double netpayble=
				 * total+(total*(Double.parseDouble(master.getVat())/100.00));
				 */
				double netpayble = total + vatamt;
				netpayble = Math.round(netpayble * 100.0) / 100.0;
				product.setTotal(DateTimeUtils.changeFormat(total));
				/* product.setGstamount(String.valueOf(netpayble-total)); */
				vatamt = Math.round(vatamt * 100.0) / 100.0;
				product.setGstamount(String.valueOf(vatamt));
				// formula rs amt =(total amount/100)*discpercentage
				double discountamt = (total / 100) * discountper;
				discountamt = Math.round(discountamt * 100.0) / 100.0;
				product.setNetammt(netpayble - discountamt);
				product.setDiscount(DateTimeUtils.changeFormat(discountamt));
				product.setDiscper(Math.round(discountper * 100.0) / 100.0);
				String voucherno = getVouchernoByProcurementid(product.getProcurementid());
				product.setVoucherno(voucherno);

				int res = getProcurmentSeqNo(product.getProcurementid());
				String proSeqNo = "";
				if (res > 0) {
					proSeqNo = String.valueOf(res);
				} else {
					proSeqNo = product.getProcurementid();
				}
				product.setProSeqNo(proSeqNo);
				product.setReturnlocation(master.getLocation());
				product.setCatalogueid(master.getCatalogueid());
				list.add(product);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return list;
	}

	private Product getAvailableFreeQty(String procurementid, String catalogueid) {
		Product product = new Product();
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select remainfreeqty,inventory_procurement.id from inventory_procurement ");
			buffer.append("inner join inventory_product on  inventory_product.id = inventory_procurement.prodid ");
			buffer.append("where procurementid='" + procurementid + "' and inventory_product.catalogueid='"
					+ catalogueid + "' ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				product.setId(rs.getInt(2));
				product.setRemainsqty(rs.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}

	public String getVendorIdfromGRNReturn(String grnreturnid) {

		String res = "0";
		try {

			String sql = "select vendorid from inventory_return_grn_parent where id=" + grnreturnid + " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				res = rs.getString(1);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return res;
	}

	public int updateGrnReturnDataStatus(Product product) {

		int res = 0;
		try {
			String sql = " update inventory_product_return_log set sec_out_no=?, sec_out_date=?,invoice_date=?, notes=?, status=1 where grnreturnid="
					+ product.getGrnreturnid() + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, product.getSecurity_no());
			ps.setString(2, product.getSecurity_date());
			ps.setString(3, product.getDate());
			ps.setString(4, product.getNotes());

			res = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	public int updateReturnQueStatus(int id) {

		int res = 0;
		try {

			String sql = "update inventory_product_temp_return set status=1 where id=" + id + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			res = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return res;
	}

	public int saveReturnVendorAcccount(Product product) {

		int res = 0;
		try {
			String sql = "insert into inventory_return_vendor_account (grnreturnid, procurementid, vendorid, dateTime, voucherno, total, status) values (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, product.getGrnreturnid());
			ps.setString(2, product.getProcurementid());
			ps.setString(3, product.getVendor_id());
			ps.setString(4, product.getDateTime());
			ps.setString(5, product.getVoucherno());
			ps.setString(6, product.getTotalamt());
			ps.setString(7, "0");

			res = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}
		return res;
	}

	public int deleteProcurementofThis(int procurementid) {

		int res = 0;
		try {

			String sql = "delete from inventory_procurement where procurementid=" + procurementid + " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			res = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return res;
	}

	public String getExpiryAlertDateSetting(int clinicid) {

		String res = "0";
		try {

			String sql = "SELECT expirydatealert from apm_user where id=" + clinicid + " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				res = rs.getString(1);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return res;
	}

	public ArrayList<Procurement> getPaybleAging(String todate, String location) {
		ArrayList<Procurement> arrayList = new ArrayList<Procurement>();
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select inventory_procurement.id, inventory_procurement.qty,inventory_vendor.name, ");
			buffer.append(
					"voucherdate,creditdays,procurementid,inventory_procurement.location,apm_condition.name from inventory_procurement ");
			buffer.append("inner join inventory_product on inventory_product.id = inventory_procurement.prodid ");
			buffer.append("inner join inventory_vendor on inventory_vendor.id = inventory_procurement.vendorid ");
			buffer.append("left join apm_condition on apm_condition.id = inventory_procurement.location ");
			buffer.append(
					"where inventory_procurement.prodid!=0 and inventory_procurement.deleted=0 and gudreceipt=1 and payment_status=0 ");
			if (!location.equals("0")) {
				buffer.append("and inventory_procurement.location='" + location + "' ");
			}

			buffer.append("group by procurementid order by inventory_procurement.id desc ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Procurement procurement = new Procurement();
				procurement.setId(rs.getInt(1));
				procurement.setQuantity(rs.getString(2));
				procurement.setVendor(rs.getString(3));
				procurement.setVoucherdate(DateTimeUtils.getCommencingDate1(rs.getString(4)));
				procurement.setProcurementid(rs.getString(6));
				procurement.setLocation(rs.getString(8));
				if (rs.getString(4) != null) {
					if (!rs.getString(4).equals("")) {
						String date1[] = rs.getString(4).split("-");
						String date = date1[2] + "/" + date1[1] + "/" + date1[0];
						int res = getProcurmentSeqNo(procurement.getProcurementid());
						String proSeqNo = "";
						if (res > 0) {
							proSeqNo = String.valueOf(res);
						} else {
							proSeqNo = procurement.getProcurementid();
						}
						procurement.setProSeqNo(proSeqNo);
						Date d1 = new SimpleDateFormat("dd/MM/yyyy").parse(date);
						Date d2 = new SimpleDateFormat("yyyy-MM-dd").parse(todate);
						long diff = d2.getTime() - d1.getTime();
						long difference = (Math.abs((diff / (1000 * 60 * 60 * 24))));
						if (difference > rs.getInt(5)) {
							arrayList.add(procurement);
						}
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public ArrayList<Product> getItemWisePurchase(String fromDate, String toDate, String productname,
			String warehouseid, int vendorid, String categoryid, String subcategoryid) {
		ArrayList<Product> arrayList = new ArrayList<Product>();
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append(
					"SELECT date,product_name,procurementid,inventory_procurement.qty,purprice,inventory_catalogue.gst,apm_condition.name, ");
			buffer.append(
					"inventory_vendor.name,inventory_product.pack,inventory_procurement.total,inventory_product.vat,inventory_product.stock,freeqty,");
			buffer.append("inventory_catalogue.genericname,prodid,");
			buffer.append(
					"inventory_category.name,inventory_subcategory.name,inventory_product.batch_no,inventory_product.mrp,inventory_product.saleprice,inventory_procurement.voucherno, inventory_procurement.voucherdate FROM inventory_procurement ");
			buffer.append(
					"inner join inventory_parent_procurement on inventory_parent_procurement.id = inventory_procurement.procurementid ");
			buffer.append(
					"inner join inventory_catalogue on inventory_catalogue.id =inventory_procurement.catalogueid ");
			buffer.append("inner join apm_condition on apm_condition.id = inventory_procurement.location ");
			buffer.append(" inner join inventory_vendor  on inventory_vendor.id= inventory_procurement.vendorid ");
			buffer.append("inner join inventory_product on inventory_product.id =inventory_procurement.prodid ");
			buffer.append("left join inventory_category on inventory_category.id = inventory_catalogue.categoryid ");
			buffer.append(
					"left join inventory_subcategory on inventory_subcategory.id = inventory_catalogue.subcategoryid ");
			buffer.append(
					"where inventory_procurement.deleted='0' and isdelivermemo=0 and prodid is not null and inventory_procurement.voucherno is NOT NULL and date between '"
							+ fromDate + "' and '" + toDate + "' ");
			buffer.append(" and inventory_product.procurement=0 ");
			if (productname != null) {
				buffer.append("and inventory_catalogue.product_name like '" + productname + "%'");
			}
			if (!warehouseid.equals("0")) {
				buffer.append("and inventory_procurement.location='" + warehouseid + "'");
			}
			if (vendorid != 0) {
				buffer.append(" and inventory_parent_procurement.vendorid='" + vendorid + "' ");
			}
			if (!categoryid.equals("0")) {
				buffer.append(" and inventory_catalogue.categoryid='" + categoryid + "' ");
			}
			if (!subcategoryid.equals("0")) {
				buffer.append(" and inventory_catalogue.subcategoryid='" + subcategoryid + "' ");
			}
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setDate(DateTimeUtils.getCommencingDate1(rs.getString(1)));
				product.setProd_name(rs.getString(2));
				product.setGrnno(rs.getInt(3));
				// product.setQty(rs.getString(4));
				// product.setPurchase_price(rs.getString(5));
				product.setVat(rs.getString(6));
				product.setWarehouse_name(rs.getString(7));
				product.setVendor(rs.getString(8));
				int res = getProcurmentSeqNo("" + rs.getInt(3));
				String proSeqNo = "";
				if (res > 0) {
					proSeqNo = String.valueOf(res);
				} else {
					proSeqNo = "" + rs.getInt(3);
				}
				product.setProSeqNo(proSeqNo);
				// add column in item wise pur rept.13/12/2018

				int pack = rs.getInt(9);
				if (pack == 0) {
					pack = 1;
				}
				int addqty = rs.getInt(4) / pack;
				product.setQty((addqty));
				double receiveqty = rs.getDouble(4);
				product.setReceived_qty(Double.toString(receiveqty));
				double perunitamt = rs.getDouble(5) / pack;
				double perunit = Math.round(perunitamt * 100.0) / 100.0;
				product.setPerunitamt(perunit);
				Double totalamt = Math.round(rs.getDouble(10) * 100.0) / 100.0;

				product.setNetammt(totalamt);
				double discountper = getDiscountAmt(rs.getString(3), "");
				double discount = (totalamt / 100) * discountper;
				double discountamt = Math.round(discount * 100.0) / 100.0;
				product.setDiscount(String.valueOf(discountamt));
				double gst1 = (totalamt - discountamt) * rs.getInt(11) / 100;
				double gst = Math.round(gst1 * 100.0) / 100.0;
				product.setGstamount(String.valueOf(gst));
				product.setStockqty(rs.getInt(12));
				double grossamt = gst + totalamt - discountamt;
				product.setGrossamt(Math.round(grossamt * 100.0) / 100.0);
				product.setPack(String.valueOf(pack));
				double netamt = totalamt + gst;
				product.setPurchase_price(String.valueOf(Math.round(netamt * 100.0) / 100.0));
				product.setFreeqty("" + rs.getInt(13));
				product.setGenericname(rs.getString(14));
				int saleqty = getPharmacySaleqty(rs.getInt(15), 0);
				int returnqty = getPharmacySaleqty(rs.getInt(15), 1);
				product.setSaleqty(saleqty);
				product.setReturnqty(returnqty);
				product.setCategory(rs.getString(16));
				product.setSubcategory(rs.getString(17));
				product.setBatch_no(rs.getString(18));
				product.setMrp(rs.getString(19));
				product.setSale_price(rs.getString(20));
				product.setVoucherno(rs.getString(21));
				product.setVoucherdate(rs.getString(22));
				product.setPur_price(rs.getString(5));
				arrayList.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	private int getPharmacySaleqty(int prodid, int isrefund) {
		int count = 0;
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select sum(quantity)  ");
			buffer.append("from apm_medicine_charges ");
			buffer.append("inner join apm_medicine_bill on apm_medicine_charges.invoiceid = apm_medicine_bill.id ");
			buffer.append("where apm_medicine_bill.isreturn=" + isrefund
					+ " and apm_medicine_bill.deleted=0 and product_id='" + prodid + "' ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public int updateProcurmentGST(int id, String vat) {
		int res = 0;
		try {
			String sql = "update inventory_procurement set gstper=? where id='" + id + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, vat);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public Product getProcurementBeforeProcurement(String procurementid, String catalogueid) {
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		Product product = new Product();

		try {
			String sql = "select id,prodid,purprice,qty,total,discount,longpo,date,grnno,catalogueid,location,"
					+ "gstper,poHsnno,poDescription from inventory_procurement where procurementid="
					+ procurementid + " and catalogueid='" + catalogueid + "' ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			double subtotal = 0;
			double netsubtotal = 0;
			double totalgstamount = 0;
			while (rs.next()) {

				product.setId(rs.getInt(1));
				product.setProduct_id(rs.getString(2));
				product.setQuantity(rs.getString(4));
				product.setDiscount(rs.getString(6));
				product.setNewpo(rs.getInt(7));
				product.setDate(DateTimeUtils.getCommencingDate1(rs.getString(8)));
				product.setGrnno(rs.getInt(9));
				product.setCatalogueid(rs.getString(10));
				Product master = null;
				if (!product.getCatalogueid().equals("0")) {
					master = inventoryProductDAO.getProductCatalogueDetails(product.getCatalogueid());
				} else {
					master = inventoryProductDAO.getProduct(product.getProduct_id());
				}
				/*
				 * double rate= Double.parseDouble(master.getPurchase_price());
				 */
				double rate = rs.getDouble(3);
				product.setProduct_name(master.getProduct_name());
				int pack = Integer.parseInt(master.getPack());
				if (pack == 0) {
					master.setPack("1");
				}
				double total = rs.getInt(4) * rate;
				subtotal = subtotal + total;
				product.setSubTotal(DateTimeUtils.changeFormat(subtotal));
				product.setTotal(DateTimeUtils.changeFormat(total));
				product.setMrp(master.getMrp());
				product.setSale_price(master.getSale_price());
				product.setPurchase_price(rs.getString(3));
				product.setVendor_id(master.getVendor_id());
				product.setBatch_no(master.getBatch_no());
				product.setPack(master.getPack());
				product.setMfg(master.getMfg());
				product.setHsnno(master.getHsnno());
				product.setVat("" + rs.getInt(12));
				
				product.setPoHsnno(rs.getString(13));
				product.setPoDescription(rs.getString(14));
				
				product.setSale_price(master.getSale_price());
				product.setLocation(rs.getString(11));
				double purchaseprice = rate;
				double vat = rs.getDouble(12);
				/*
				 * if(master.getVat()!=null){ if(!master.getVat().equals("")){
				 * vat = Double.parseDouble(master.getVat()); } }
				 */
				int qty = rs.getInt(4);
				double netamount = (purchaseprice * vat) / 100;
				netamount = netamount * qty;
				netamount = Math.round(netamount * 100.0) / 100.0;
				product.setGstamount(String.valueOf(netamount));
				totalgstamount = totalgstamount + netamount;
				product.setTotalgstamount(String.valueOf(totalgstamount));

				double nettotal = netamount + total;

				product.setNetamount(String.valueOf(nettotal));

				netsubtotal = netsubtotal + nettotal;
				product.setTotalnetamount(String.valueOf(netsubtotal));
				product.setPro_code(master.getPro_code());
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return product;
	}

	public int updateParentProcurment(String delivermemodate, String delivermemoid, String todaydate,
			String procurementid, int dmseq) {
		int res = 0;
		try {
			String sql = "update inventory_parent_procurement set isdelivermemo=1,dmno='" + delivermemoid + "',dmdate='"
					+ delivermemodate + "',dmseq='" + dmseq + "' where id='" + procurementid + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public Procurement getParentProcurmentData(String procurementid) {
		Procurement procurement = new Procurement();
		try {
			String sql = "select id, vendorid, lastmodified, isdelivermemo, dmno, dmdate, firstnetamout from inventory_parent_procurement where id='"
					+ procurementid + "' ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				procurement.setId(rs.getInt(1));
				procurement.setVendor_id("" + rs.getInt(2));
				procurement.setIsdelivermemo("" + rs.getInt(4));
				if (rs.getString(5) == null) {
					procurement.setDelivermemoid("");
				} else {
					procurement.setDelivermemoid(rs.getString(5));
				}

				if (rs.getString(6) != null) {
					if (!rs.getString(6).equals("")) {
						procurement.setDelivermemodate(rs.getString(6));
					}
				} else {
					procurement.setDelivermemodate("");
				}
				procurement.setFirstnetamout(DateTimeUtils.numberCheck(rs.getString(7)));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return procurement;
	}

	public ArrayList<Procurement> getDMList(String location, String vendorid) {
		ArrayList<Procurement> arrayList = new ArrayList<Procurement>();
		try {
			PoPaymenytDAO poPaymenytDAO = new JDBCPoPaymengtDAO(connection);
			ProcurementDAO procurementDAO = new JDBCProcurementDAO(connection);
			StringBuffer buffer = new StringBuffer();
			/*
			 * buffer.
			 * append("select inventory_parent_procurement.id, vendorid, lastmodified, isdelivermemo, dmno, dmdate,name from inventory_parent_procurement "
			 * ); buffer.
			 * append("inner join inventory_vendor on inventory_vendor.id = inventory_parent_procurement.vendorid "
			 * ); buffer.append("where isdelivermemo=1 and dmcmplt=0");
			 */
			buffer.append(
					"select inventory_parent_procurement.id, inventory_parent_procurement.vendorid, lastmodified,  ");
			buffer.append(
					"isdelivermemo, inventory_procurement.dmno, dmdate,inventory_vendor.name,inventory_procurement.id,inventory_procurement.voucherno,sum(inventory_procurement.total),  ");
			buffer.append("apm_condition.name,inventory_procurement.grnno from inventory_parent_procurement ");
			buffer.append(
					"inner join inventory_vendor on inventory_vendor.id = inventory_parent_procurement.vendorid ");
			buffer.append(
					"inner join inventory_procurement on inventory_procurement.procurementid =inventory_parent_procurement.id ");
			buffer.append("left join apm_condition on apm_condition.id = inventory_procurement.location ");
			buffer.append("where isdelivermemo=1 and dmcmplt=0 and inventory_parent_procurement.vendorid='" + vendorid
					+ "' ");
			if (!location.equals("0")) {
				buffer.append("and inventory_procurement.location='" + location + "'");
			}
			buffer.append("group by inventory_parent_procurement.id ");

			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Procurement procurement = new Procurement();
				procurement.setId(rs.getInt(1));
				procurement.setVendor_id(rs.getString(2));
				procurement.setDelivermemoid(rs.getString(5));
				procurement.setDelivermemodate(rs.getString(6));
				procurement.setVendor(rs.getString(7));
				procurement.setChildid(rs.getInt(8));
				int dmcmplted = checkIsComplatedDm(rs.getString(1));
				double netAmt = poPaymenytDAO.getTotalVoucherAmount(rs.getString(9), rs.getString(1), dmcmplted);
				double newdisc = poPaymenytDAO.getTotalVoucherDiscount(rs.getString(9), rs.getString(1));
				Product procdata2 = procurementDAO.getProcAccountDetails(rs.getString(1));
				procurement.setCgst(procdata2.getCgst());
				procurement.setSgst(procdata2.getSgst());
				procurement.setIgst(procdata2.getIgst());
				procurement.setDiscount(DateTimeUtils.changeFormat(newdisc));
				procurement.setNetAmt(DateTimeUtils.changeFormat(netAmt));
				procurement.setTotal(rs.getString(10));
				procurement.setLocationname(rs.getString(11));
				ArrayList<Product> procurmentlist = getProcurmentProductDetails(rs.getString(1));
				procurement.setProcurmentlist(procurmentlist);
				procurement.setGrnno(rs.getString(12));
				if (rs.getInt(12) > 0) {
					Product grndata = getGrnDetails(rs.getInt(12));
					procurement.setGrndate(grndata.getDate());
					procurement.setPoSequence(grndata.getPoSequence());
				}
				arrayList.add(procurement);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	private ArrayList<Product> getProcurmentProductDetails(String procurementid) {
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		ArrayList<Product> list = new ArrayList<Product>();
		try {
			String sql = "select id,prodid,purprice,qty,total,discount,catalogueid from inventory_procurement where procurementid='"
					+ procurementid + "' and prodid is not null ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			int i = 0;
			while (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt(1));
				product.setProduct_id(rs.getString(2));
				product.setQuantity(rs.getString(4));
				Product master = inventoryProductDAO.getProduct(product.getProduct_id());
				product.setProduct_name(master.getProduct_name());
				product.setPack(master.getPack());
				product.setMedicine_shedule(master.getMedicine_shedule());
				product.setBatch_no(master.getBatch_no());
				product.setMfg(master.getMfg());
				product.setMrp(master.getMrp());
				product.setSale_price(master.getSale_price());
				product.setVat(master.getVat());
				product.setPurchase_price(rs.getString(3));
				product.setHsnno(master.getHsnno());
				product.setTotal(DateTimeUtils.changeStringFormat(rs.getString(5)));
				i++;
				list.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int updateChildProcDM(String string, String voucherno, String voucherdt) {
		int res = 0;
		try {
			String sql = "update inventory_procurement set voucherno='" + voucherno + "',oldprocid='" + string
					+ "' where procurementid='" + string + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int updatePParentProcDM(String string, String voucherno, String voucherdt, String security, String sec_date,
			int dmseq, int parentid) {
		int res = 0;
		try {
			String sql = "update inventory_parent_procurement set voucher='" + voucherno + "', voucherDT='" + voucherdt
					+ "',dmcmplt='1',dmid='" + parentid + "' where id='" + string + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int updateVendorProcDM(String string, String voucherno, String voucherdt, String security, String sec_date) {
		int res = 0;
		try {//
			String sql = "update inventory_vendor_procurement set voucherno='" + voucherno + "', security_inward_no='"
					+ security + "', security_inward_date='" + sec_date + "',oldprocid='" + string
					+ "' where procurementid='" + string + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int getDmSeq(String location) {
		int res = 0;
		try {
			StringBuffer buffer = new StringBuffer();
			// buffer.append("select max(dmseq) from
			// inventory_parent_procurement");
			buffer.append("select max(dmseq) from inventory_parent_procurement ");
			buffer.append(
					"inner join inventory_procurement on inventory_procurement.procurementid = inventory_parent_procurement.id ");
			buffer.append("where location='" + location + "' ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				res = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int insertoTermsNcondition(String text, String procureid) {
		int result = 0;
		PreparedStatement ps = null;
		String query = "insert into po_termsandcondition(po_id,terms) values(?,?)";
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(procureid));
			ps.setString(2, text);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updatetoTermsNcondition(String text, String procureid) {
		int result = 0;
		PreparedStatement ps = null;
		String query = "update po_termsandcondition set terms='" + text + "' where po_id='" + procureid + "'";
		try {
			ps = connection.prepareStatement(query);

			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean checkTermsNcondition(String procureid) {
		boolean result = false;
		PreparedStatement ps = null;
		String query = "select * from po_termsandcondition where po_id=" + procureid + " ";
		try {
			ps = connection.prepareStatement(query);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getTermNconditions(String procureid) {
		String terms = "";
		PreparedStatement ps = null;
		String query = "select terms from po_termsandcondition where po_id=" + procureid + " ";
		try {
			ps = connection.prepareStatement(query);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				terms = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return terms;
	}

	public ArrayList<Procurement> getDmlist(String fromdate, String todate, Pagination pagination, String location,
			String vendorid, String invoice_created) {
		ArrayList<Procurement> list = new ArrayList<Procurement>();
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		PoPaymenytDAO poPaymenytDAO = new JDBCPoPaymengtDAO(connection);
		PreparedStatement ps = null;
		try {
			fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			todate = DateTimeUtils.getCommencingDate1(todate);
			todate = todate + " " + "59:59:59";
			StringBuffer buffer = new StringBuffer();
			buffer.append(
					"select inventory_parent_procurement.id, vendorid, lastmodified, isdelivermemo, dmno, dmdate,name,dmid,dmcmplt,voucher,dmseq ");
			buffer.append(" from inventory_parent_procurement ");
			buffer.append(
					"inner join inventory_vendor on inventory_vendor.id = inventory_parent_procurement.vendorid ");
			buffer.append("where isdelivermemo=1 and lastmodified between '" + fromdate + "' and '" + todate + "' ");
			// 12/11/2018
			if (!invoice_created.equals("2")) {
				buffer.append("and dmcmplt='" + invoice_created + "' ");
			}
			if (!vendorid.equals("0")) {
				buffer.append("and vendorid='" + vendorid + "' ");

			}
			buffer.append("order by inventory_parent_procurement.id desc ");
			String sqls = pagination.getSQLQuery(buffer.toString());
			PreparedStatement preparedStatement = connection.prepareStatement(sqls);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Procurement procurement = new Procurement();
				procurement.setProcurementid(rs.getString(1));
				procurement.setVendor_id(rs.getString(2));
				procurement.setIsdelivermemo(rs.getString(4));
				procurement.setDelivermemoid(rs.getString(5));
				procurement.setDelivermemodate(rs.getString(6));
				procurement.setVendor(rs.getString(7));
				procurement.setDmcmplt("" + rs.getInt(9));
				procurement.setDmid("" + rs.getInt(8));
				procurement.setVoucherno(rs.getString(10));
				Procurement procurement2 = getProcurementNew(rs.getString(1), rs.getInt(9));
				double netAmt = poPaymenytDAO.getTotalVoucherAmount(procurement2.getVoucherno(),
						procurement.getProcurementid(), rs.getInt(9));
				procurement.setNetAmt(DateTimeUtils.changeFormat(netAmt));
				procurement.setConfirm(procurement2.getConfirm());
				procurement.setGudreceipt(procurement2.getGudreceipt());
				procurement.setGrnno(procurement2.getGrnno());
				if (procurement2.getGrnno() != null) {
					if (procurement2.getGrnno().equals("")) {
						procurement2.setGrnno("0");
					}
				} else {
					procurement2.setGrnno("0");
				}
				Product grndata = getGrnDetails(Integer.parseInt(procurement2.getGrnno()));
				procurement.setGrndate(grndata.getDate());
				
				procurement.setPoSequence(grndata.getPoSequence());

				procurement.setDeleted(procurement2.getDeleted());
				procurement.setEdit(0);
				String locationame = inventoryProductDAO.pharmacyLocationNameByID(procurement2.getLocation());
				procurement.setLocationname(locationame);
				procurement.setId(procurement2.getId());
				procurement.setDmseq(rs.getString(11));
				if (!location.equals("0")) {
					String proclocation = procurement2.getLocation();
					if (proclocation != null) {
						if (proclocation.equals(location)) {
							list.add(procurement);
						}
					}
				} else {
					list.add(procurement);
				}
				// list.add(procurement);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

		/*
		 * ArrayList<Procurement> list=new ArrayList<Procurement>();
		 * InventoryProductDAO inventoryProductDAO=new
		 * JDBCInventoryProductDAO(connection); InventoryVendorDAO
		 * inventoryVendorDAO=new JDBCInventoryVendorDAO(connection);
		 * PoPaymenytDAO poPaymenytDAO= new JDBCPoPaymengtDAO(connection);
		 * 
		 * try { StringBuffer sql = new StringBuffer(); sql.
		 * append("select inventory_procurement.id, prodid, inventory_procurement.qty, inventory_procurement.date,confirm,gudreceipt,procurementid,voucherdate,voucherno,inventory_procurement.vendorid,longpo,inventory_procurement.grnno,inventory_procurement.deleted, "
		 * ); sql.
		 * append("inventory_procurement.iscancel,apm_condition.name,grnseqno, dmno, dmdate,dmcmplt,dmid,isdelivermemo from inventory_procurement "
		 * ); sql.
		 * append("inner join inventory_parent_procurement on inventory_parent_procurement.id = inventory_procurement.procurementid "
		 * ); sql.
		 * append("inner join inventory_product on inventory_product.id = inventory_procurement.prodid "
		 * ); sql.
		 * append("left join apm_condition on apm_condition.id = inventory_procurement.location "
		 * ); sql.append("where isdelivermemo=1 ");
		 * 
		 * if(!location.equals("0")){
		 * sql.append("and inventory_procurement.location='"+location+"' "); }
		 * sql.append("and date between '" + fromdate + "' and '" + todate +
		 * "' "); sql.
		 * append("group by procurementid order by inventory_parent_procurement.id desc "
		 * );
		 * 
		 * String sqls = pagination.getSQLQuery(sql.toString());
		 * 
		 * PreparedStatement ps=connection.prepareStatement(sqls); ResultSet
		 * rs=ps.executeQuery(); while(rs.next()){
		 * 
		 * Procurement procurement=new Procurement();
		 * procurement.setId(rs.getInt(1));
		 * procurement.setProduct_id(rs.getString(2));
		 * procurement.setQuantity(rs.getString(3));
		 * procurement.setDate(DateTimeUtils.getCommencingDate1(rs.getString(4))
		 * ); procurement.setConfirm(rs.getInt(5));
		 * procurement.setGudreceipt(rs.getInt(6));
		 * procurement.setProcurementid(rs.getString(7)); String voucherdate="";
		 * if(rs.getString(8)!=null){ voucherdate =
		 * DateTimeUtils.getCommencingDate1(rs.getString(8)); }
		 * 
		 * procurement.setVoucherno(rs.getString(9)); String vendorid=
		 * rs.getString(10); int qty=
		 * getSumOfQTy(procurement.getProcurementid());
		 * procurement.setQuantity(""+qty+"");
		 * procurement.setIscancel(rs.getString(14));
		 * 
		 * Product vendorProcData=
		 * getProcAccountDetails(procurement.getProcurementid());
		 * procurement.setSecurity_date(vendorProcData.getSecurity_date());
		 * procurement.setSecurity_no(vendorProcData.getSecurity_no()); Product
		 * product=inventoryProductDAO.getProduct(procurement.getProduct_id());
		 * String prodname = product.getProduct_name(); String brand =
		 * product.getBrand(); Vendor
		 * vendormaster=inventoryVendorDAO.getVendor(vendorid); String vendor =
		 * vendormaster.getName(); procurement.setVoucherdate(voucherdate);
		 * procurement.setProduct(prodname); procurement.setBrand(brand);
		 * procurement.setVendor(vendor); procurement.setVendor_id(vendorid);
		 * procurement.setTinno(product.getTinno()); double netAmt=
		 * poPaymenytDAO.getTotalVoucherAmount(procurement.getVoucherno(),
		 * procurement.getProcurementid());
		 * procurement.setNetAmt(DateTimeUtils.changeFormat(netAmt)); int
		 * longpo= rs.getInt(11); procurement.setGrnno(rs.getString(12)); int
		 * deleted= rs.getInt(13); boolean flag=
		 * isthisProcuremetProductransfer(procurement.getProcurementid());
		 * if(flag){ procurement.setEdit(1); } procurement.setEdit(0); int
		 * nowstock= Integer.parseInt(product.getStock());
		 * if(nowstock!=rs.getInt(3)){ procurement.setTransferred(1); }
		 * procurement.setDeleted(deleted); if(rs.getInt(12)>0){ Product grndata
		 * =getGrnDetails(rs.getInt(12));
		 * procurement.setGrndate(grndata.getDate()); }
		 * procurement.setLocationname(rs.getString(15));
		 * procurement.setGrnseqno(rs.getInt(16));
		 * procurement.setDelivermemoid(rs.getString(17));
		 * procurement.setDelivermemodate(rs.getString(18));
		 * procurement.setDmcmplt(""+rs.getInt(19));
		 * procurement.setDmid(""+rs.getInt(20));
		 * procurement.setIsdelivermemo(rs.getString(21)); if(longpo>0){
		 * list.add(procurement); } else {
		 * 
		 * if(netAmt!=0){ list.add(procurement); } }
		 * 
		 * }
		 * 
		 * } catch (Exception e) {
		 * 
		 * e.printStackTrace(); }
		 * 
		 * return list;
		 */
	}

	public Procurement getProcurementNew(String string, int iscomplted) {
		Procurement procurement = new Procurement();

		try {

			// String sql="select prodid, purprice, qty, total, mrp, completepo,
			// date, gudreceipt, confirm, vendorid,
			// brandid,procurementid,voucherno,voucherdate,location,longpo,p_time,p_userid,grnno,catalogueid,location
			// from inventory_procurement where id="+string+"";
			StringBuffer buffer = new StringBuffer();
			buffer.append(
					"select prodid, purprice, qty, total, mrp, completepo, date, gudreceipt, confirm, vendorid, ");
			buffer.append(
					"brandid,procurementid,voucherno,voucherdate,location,longpo,p_time,p_userid,grnno,catalogueid,location,deleted,id ");
			buffer.append("from inventory_procurement   ");
			if (iscomplted > 0) {
				buffer.append("where oldprocid='" + string + "' ");
			} else {
				buffer.append("where procurementid='" + string + "' ");
			}
			buffer.append("limit 0,1 ");
			PreparedStatement ps = connection.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				procurement.setProduct_id(rs.getString(1));
				procurement.setPurchase_price(rs.getString(2));
				procurement.setQuantity(rs.getString(3));
				procurement.setTotal(rs.getString(4));
				procurement.setMrp(rs.getString(5));
				procurement.setCompletepo(rs.getInt(6));
				procurement.setDate(rs.getString(7));
				procurement.setGudreceipt(rs.getInt(8));
				procurement.setConfirm(rs.getInt(9));
				procurement.setVendor_id(rs.getString(10));
				procurement.setBrand_id(rs.getString(11));
				procurement.setProcurementid(rs.getString(12));
				procurement.setVoucherno(rs.getString(13));
				procurement.setVoucherdate(rs.getString(14));
				procurement.setLocation(rs.getString(15));
				procurement.setLongpo(rs.getInt(16));
				procurement.setTime(rs.getString(17));
				procurement.setUserid(rs.getString(18));
				procurement.setGrnno("" + rs.getInt(19));
				procurement.setCatalogueid(rs.getString(20));
				procurement.setLocation(rs.getString(21));
				procurement.setDeleted(rs.getInt(22));
				procurement.setId(rs.getInt(23));
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return procurement;
	}

	public int getDmlistCount(String fromdate, String todate, String location, String vendorid,
			String invoice_created) {

		int result = 0;
		PreparedStatement ps = null;
		try {
			fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			todate = DateTimeUtils.getCommencingDate1(todate);
			todate = todate + " " + "59:59:59";
			/*
			 * StringBuffer sql = new StringBuffer();
			 * sql.append("select count(*) ");
			 * sql.append(" from inventory_procurement "); sql.
			 * append("inner join inventory_parent_procurement on inventory_parent_procurement.id = inventory_procurement.procurementid "
			 * ); sql.
			 * append("inner join inventory_product on inventory_product.id = inventory_procurement.prodid "
			 * ); sql.
			 * append("left join apm_condition on apm_condition.id = inventory_procurement.location "
			 * ); sql.append("where isdelivermemo=1 ");
			 * 
			 * if(!location.equals("0")){
			 * sql.append("and inventory_procurement.location='"+location+"' ");
			 * } sql.append("and date between '" + fromdate + "' and '" + todate
			 * + "' "); sql.
			 * append("group by inventory_procurement.procurementid order by inventory_parent_procurement.id desc "
			 * );
			 */
			StringBuffer buffer = new StringBuffer();
			buffer.append("select inventory_parent_procurement.id,inventory_parent_procurement.isdelivermemo ");
			buffer.append(" from inventory_parent_procurement ");
			buffer.append(
					"inner join inventory_vendor on inventory_vendor.id = inventory_parent_procurement.vendorid ");
			buffer.append("where isdelivermemo=1 and lastmodified between '" + fromdate + "' and '" + todate + "' ");
			if (!vendorid.equals("0")) {
				buffer.append("and inventory_parent_procurement.vendorid='" + vendorid + "' ");
			}
			if (!invoice_created.equals("2")) {
				buffer.append("and inventory_parent_procurement.dmcmplt='" + invoice_created + "' ");

			}
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				// result= rs.getInt(1);
				if (!location.equals("0")) {
					String proclocation = getProcurmentLocation(rs.getString(1), rs.getInt(2));
					if (proclocation.equals(location)) {
						result = result + 1;
					}
				} else {
					result = result + 1;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private String getProcurmentLocation(String string, int isdelivermemo) {
		String location = "0";
		try {
			// String sql ="select location from inventory_procurement where
			// location is not null and procurementid='"+string+"' group by
			// procurementid ";
			StringBuffer buffer = new StringBuffer();
			buffer.append("select location from inventory_procurement ");
			buffer.append("where location is not null ");
			if (isdelivermemo > 0) {
				buffer.append("and oldprocid='" + string + "' ");
			} else {
				buffer.append("and procurementid='" + string + "' ");
			}
			buffer.append("group by procurementid ");

			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				location = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return location;
	}

	// lokesh

	public int cancelPOnew(String procurementid) {
		int result = 0;
		PreparedStatement ps = null;
		try {
			String sql = "update inventory_procurement set iscancel=1 where procurementid=" + procurementid + "";
			ps = connection.prepareStatement(sql);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// lokesh

	public int getStock(String catalaugeid, String location) {
		PreparedStatement ps = null;
		int qty = 0;
		String sql = "select sum(stock) from inventory_product where catalogueid='" + catalaugeid + "' and location ='"
				+ location + "'";
		try {
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				qty = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return qty;
	}

	public double getoldpurprice(String catalaugeid) {
		PreparedStatement ps = null;
		double qty = 0;
		// String sql="select purprice from inventory_procurement where
		// catalogueid='"+catalaugeid+"' and purprice>0 order by id desc limit 1
		// ";
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select purprice from inventory_procurement ");
			buffer.append("inner join inventory_product on inventory_product.id = inventory_procurement.prodid ");
			buffer.append(
					"where inventory_procurement.catalogueid='" + catalaugeid + "' and purprice>0 and procurement=0 ");
			buffer.append("order by  inventory_procurement.id desc limit 1 ");
			ps = connection.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				qty = rs.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return qty;
	}

	public ArrayList<Procurement> getemailPOList(String fromdate, String todate, String vendor_id, String voucherno) {
		ArrayList<Procurement> list = null;
		PreparedStatement ps = null;
		try {
			list = new ArrayList<Procurement>();
			fromdate = DateTimeUtils.getCommencingDate1(fromdate);
			todate = DateTimeUtils.getCommencingDate1(todate);
			StringBuffer buffer = new StringBuffer();

			/*
			 * String
			 * sql1="select datetime ,sender, receiver ,po_id from apm_email_letter_log where"
			 * ; buffer.append(sql1); if(fromdate!=null&&todate!=null){
			 * if(!fromdate.equals("")&&!todate.equals(""))
			 * buffer.append("  datetime between '"+fromdate+"' and '"
			 * +todate+" 23:59:59' and "); }
			 * buffer.append("  heading ='Purchase Order Confirmation' ");
			 */

			buffer.append(
					"select datetime ,sender, receiver ,po_id,grnno,inventory_vendor.name from apm_email_letter_log ");
			buffer.append(
					"inner join inventory_procurement on inventory_procurement.procurementid = apm_email_letter_log.po_id ");
			buffer.append(
					"inner join inventory_parent_procurement on inventory_parent_procurement.id = inventory_procurement.procurementid ");
			buffer.append(
					"inner join inventory_vendor on inventory_vendor.id = inventory_parent_procurement.vendorid ");
			buffer.append("where apm_email_letter_log.datetime between '" + fromdate + "' and '" + todate
					+ " 23:59:59' and apm_email_letter_log.heading ='Purchase Order Confirmation' ");
			if (!voucherno.equals("")) {
				buffer.append("and grnno='" + voucherno + "' ");
			}
			if (!vendor_id.equals("0")) {
				buffer.append("and inventory_vendor.id='" + vendor_id + "' ");
			}
			buffer.append("group by inventory_procurement.procurementid ");
			ps = connection.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Procurement pro = new Procurement();
				String dt = rs.getString(1);
				String x[] = dt.split(" ");
				dt = DateTimeUtils.getCommencingDate1(x[0]);
				pro.setDate(dt);
				pro.setSender(rs.getString(2));
				pro.setReciver(rs.getString(3));
				pro.setProcurementid(rs.getString(5));
				pro.setVendor(rs.getString(6));
				pro.setGrnno(rs.getString(4));
				list.add(pro);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int getvendorstateforlongpo(String procurementid) {
		int stateid = 0;
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select state from inventory_procurement ");
			buffer.append("inner join inventory_vendor on inventory_vendor.id = inventory_procurement.vendorid ");
			buffer.append("where procurementid='" + procurementid + "' group by procurementid ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				stateid = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stateid;
	}

	// lokesh
	public String secondryLetterhead() {
		String letterhd = "";
		PreparedStatement ps = null;
		try {
			String sql = "select secondry_letterhd from apm_user where id=1 ";
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				letterhd = rs.getString(1);
				if (letterhd == null) {
					letterhd = "";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return letterhd;
	}

	public int getMaxGRNSeqNo(String location) {
		int i = 0;
		try {
			String sql = "select max(grnseqno) from inventory_procurement where location='" + location + "' ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				i = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	public int updateProcurmentSeqNo(int grnseqno, int procurementid) {
		int res = 0;
		try {
			String sql = "update inventory_procurement set grnseqno='" + grnseqno + "' where procurementid='"
					+ procurementid + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public String getCatalogueRate(String product_id) {
		String res = "0";
		try {
			String sql = "select purchase_price from inventory_catalogue where id='" + product_id + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				res = DateTimeUtils.changeFormat(rs.getDouble(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int updateCatalogueRate(String product_id, String rate) {
		int res = 0;
		try {
			String sql = "update inventory_catalogue set purchase_price='" + rate + "' where id='" + product_id + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int saveCatalogueRateChangeLog(String product_id, String rate, String previousrate, String datetime,
			String userid) {
		int res = 0;
		try {
			String sql = "insert into catalogue_rate_change_log (rate,previousrate,datetime,userid,catalogueid) values (?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, rate);
			preparedStatement.setString(2, previousrate);
			preparedStatement.setString(3, datetime);
			preparedStatement.setString(4, userid);
			preparedStatement.setString(5, product_id);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<Procurement> getAllProcurementList(String location, String voucherno, String fromdate,
			String todate, String vendor_id) {
		ArrayList<Procurement> list = new ArrayList<Procurement>();
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		InventoryVendorDAO inventoryVendorDAO = new JDBCInventoryVendorDAO(connection);
		PoPaymenytDAO poPaymenytDAO = new JDBCPoPaymengtDAO(connection);

		try {
			StringBuffer sql = new StringBuffer();
			sql.append(
					"select inventory_procurement.id, prodid, inventory_procurement.qty, inventory_procurement.date,confirm,gudreceipt,procurementid,  ");
			sql.append(
					"voucherdate,voucherno,inventory_procurement.vendorid,longpo,inventory_procurement.grnno,inventory_procurement.deleted,inventory_procurement.iscancel, ");
			sql.append(
					"apm_condition.name,grnseqno,inventory_procurement.approve_qty,inventory_procurement.purprice,inventory_procurement.total,freeqty,inventory_catalogue.genericname ");
			sql.append("from inventory_procurement ");
			sql.append("inner join inventory_product on inventory_product.id = inventory_procurement.prodid ");
			sql.append(
					"inner join inventory_parent_procurement on inventory_parent_procurement.id = inventory_procurement.procurementid ");
			if (vendor_id != null) {
				sql.append("inner join inventory_vendor on inventory_vendor.id =  inventory_product.supplierid ");
			}
			sql.append("left join apm_condition on apm_condition.id = inventory_procurement.location ");
			sql.append("left join procurement_seqno on procurement_seqno.seq_procid =inventory_parent_procurement.id ");
			sql.append("inner join inventory_catalogue on inventory_catalogue.id = inventory_product.catalogueid ");
			sql.append(
					"where inventory_procurement.prodid!=0 and inventory_procurement.voucherno is NOT NULL and isdelivermemo=0 ");

			if (!location.equals("0")) {
				sql.append("and inventory_procurement.location='" + location + "' ");
			}
			if (voucherno != null) {
				sql.append("and (inventory_procurement.voucherno='" + voucherno
						+ "' or inventory_procurement.procurementid='" + voucherno + "'  ");
				sql.append("or inventory_procurement.grnno='" + voucherno + "' or sequenceno='" + voucherno
						+ "' or inventory_product.prodname like '" + voucherno + "%') ");
			}
			if (vendor_id != null) {
				sql.append("and inventory_vendor.id='" + vendor_id + "' ");
			}
			sql.append(
					"and date between '" + fromdate + "' and '" + todate + "' and inventory_procurement.deleted='0' ");
			sql.append("order by inventory_procurement.id desc ");

			PreparedStatement ps = connection.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			double totalmrp = 0;
			double totalrate = 0;
			double total = 0;
			double netamtttl = 0;
			double nettempvatamount = 0;
			while (rs.next()) {
				Procurement procurement = new Procurement();
				procurement.setId(rs.getInt(1));
				procurement.setProduct_id(rs.getString(2));
				procurement.setQuantity(rs.getString(3));
				procurement.setDate(DateTimeUtils.getCommencingDate1(rs.getString(4)));
				procurement.setConfirm(rs.getInt(5));
				procurement.setGudreceipt(rs.getInt(6));
				procurement.setProcurementid(rs.getString(7));
				String voucherdate = "";
				if (rs.getString(8) != null) {
					voucherdate = DateTimeUtils.getCommencingDate1(rs.getString(8));
				}

				procurement.setVoucherno(rs.getString(9));
				String vendorid = rs.getString(10);
				/*
				 * int qty= getSumOfQTy(procurement.getProcurementid());
				 * procurement.setQuantity(""+qty+"");
				 */
				procurement.setIscancel(rs.getString(14));

				// Product vendorProcData=
				// getProcAccountDetails(procurement.getProcurementid());
				// procurement.setSecurity_date(vendorProcData.getSecurity_date());
				// procurement.setSecurity_no(vendorProcData.getSecurity_no());
				Product product = inventoryProductDAO.getProduct(procurement.getProduct_id());
				String prodname = product.getProduct_name();
				String brand = product.getBrand();
				Vendor vendormaster = inventoryVendorDAO.getVendor(vendorid);
				String vendor = vendormaster.getName();
				procurement.setVoucherdate(voucherdate);
				procurement.setProduct(prodname);
				procurement.setBrand(brand);
				procurement.setVendor(vendor);
				procurement.setTinno(product.getTinno());
				int dmcmplted = checkIsComplatedDm(procurement.getProcurementid());
				double netAmt = poPaymenytDAO.getTotalVoucherAmount(procurement.getVoucherno(),
						procurement.getProcurementid(), dmcmplted);
				procurement.setNetAmt(DateTimeUtils.changeFormat(netAmt));
				procurement.setGrnno(rs.getString(12));
				int deleted = rs.getInt(13);

				procurement.setEdit(0);
				double nowstock =Double.parseDouble(product.getStock());
				if (nowstock != rs.getInt(3)) {
					procurement.setTransferred(1);
				}
				procurement.setDeleted(deleted);
				if (rs.getInt(12) > 0) {
					Product grndata = getGrnDetails(rs.getInt(12));
					procurement.setGrndate(grndata.getDate());
					procurement.setPoSequence(grndata.getPoSequence());
				}
				procurement.setLocationname(rs.getString(15));
				procurement.setGrnseqno(rs.getInt(16));

				procurement.setMfg(product.getMfg());
				procurement.setMrp(product.getMrp());
				procurement.setSale_price(product.getSale_price());
				procurement.setVat(product.getVat());
				procurement.setPurchase_price(rs.getString(18));
				procurement.setTotal(DateTimeUtils.changeFormat(Math.round(rs.getDouble(19) * 100.0) / 100.0));
				procurement.setFreeqty(rs.getString(20));
				totalmrp = totalmrp + Double.parseDouble(product.getMrp());
				totalrate = totalrate + rs.getDouble(18);
				total = total + rs.getDouble(19);
				procurement.setTttotal(DateTimeUtils.changeFormat(Math.round(total * 100.0) / 100.0));
				procurement.setTotalmrp(DateTimeUtils.changeFormat(Math.round(totalmrp * 100.0) / 100.0));
				procurement.setTotalrate(DateTimeUtils.changeFormat(Math.round(totalrate * 100.0) / 100.0));
				Product master = inventoryProductDAO.getProductCatalogueDetails(product.getCatalogueid());
				Product productcat = inventoryProductDAO.getCategory(master.getCategory_id());
				Product productsubcat = inventoryProductDAO.getSubCategory(master.getSubcategory_id());
				String categoryname = productcat.getName();
				String subcategoryname = productsubcat.getName();
				procurement.setCategory(categoryname);
				procurement.setSubcategory(subcategoryname);
				procurement.setProduct_code(master.getPro_code());
				procurement.setBatch_no(product.getBatch_no());
				procurement.setExpiry_date(DateTimeUtils.getCommencingDate1(product.getExpiry_date()));
				procurement.setApprove_qty("" + rs.getInt(17));
				int res = getProcurmentSeqNo(rs.getString(7));
				String proSeqNo = "";
				if (res > 0) {
					proSeqNo = String.valueOf(res);
				} else {
					proSeqNo = rs.getString(7);
				}
				procurement.setProSeqNo(proSeqNo);
				double tempvatamount = rs.getDouble(18) * Double.parseDouble(product.getVat()) / 100;
				tempvatamount = tempvatamount * rs.getInt(3);
				double totalnetamt = rs.getDouble(19) + tempvatamount;
				netamtttl = totalnetamt + netamtttl;
				nettempvatamount = nettempvatamount + tempvatamount;
				procurement.setNetamtttl(Math.round(nettempvatamount * 100.0) / 100.0);
				procurement.setTtlgst(Math.round(netamtttl * 100.0) / 100.0);
				procurement.setTempvatamount(Math.round(tempvatamount * 100.0) / 100.0);
				procurement.setTotalnetamt(Math.round(totalnetamt * 100.0) / 100.0);
				procurement.setGenericname(rs.getString(21));
				list.add(procurement);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	public int saveParentDMData(String date, String userId, int newprocurementid) {
		int res = 0;
		try {
			String sql = "insert into dm_parent_invoice (dmdatetime,dmuserid,newprocid) values (?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, date);
			preparedStatement.setString(2, userId);
			preparedStatement.setString(3, "" + newprocurementid);
			res = preparedStatement.executeUpdate();
			if (res == 1) {
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if (resultSet.next()) {
					res = resultSet.getInt(1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int saveChildDMData(String procurementid, int parentid) {
		int res = 0;
		try {
			String sql = "insert into dm_child_assessment (parentid,procurementid) values (?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "" + parentid);
			preparedStatement.setString(2, procurementid);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public boolean isDMComplated(String procurementid) {
		boolean flag = false;
		try {
			String sql = "select id from dm_child_assessment where procurementid='" + procurementid + "'";
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

	public String getProcurementIDS(int dmparentid) {
		String ids = "";
		try {
			String sql = "select procurementid from dm_child_assessment where parentid='" + dmparentid + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				if (ids.equals("")) {
					ids = "" + resultSet.getInt(1);
				} else {
					ids = ids + "," + resultSet.getInt(1);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ids;
	}

	public int getDmparentID(String procurementid) {
		int res = 0;
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select dm_parent_invoice.id from dm_parent_invoice ");
			buffer.append("inner join dm_child_assessment on dm_child_assessment.parentid = dm_parent_invoice.id ");
			buffer.append("where procurementid='" + procurementid + "' ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				res = resultSet.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int getOldDmSeqNo(String procurementid) {
		int res = 0;
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select dmseq from inventory_parent_procurement where id='" + procurementid + "'");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				res = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public boolean checkingProIDSeq(String procurementid) {
		boolean flag = false;
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select sequenceno from procurement_seqno where seq_procid='" + procurementid + "'");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public int getProcurmentSeq(String location) {
		int res = 0;
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select max(sequenceno) from procurement_seqno where seq_location='" + location + "'");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				res = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int insertProcSeqNo(String location, int seqno, String userId, String procurementid, String todaydate,
			String isdm, String dmparentid) {
		int res = 0;
		try {
			String sql = "insert into procurement_seqno (sequenceno,seq_location,seq_userid,seq_datetime,seq_procid,isfromdm,dmparentid) values (?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "" + seqno);
			preparedStatement.setString(2, location);
			preparedStatement.setString(3, userId);
			preparedStatement.setString(4, todaydate);
			preparedStatement.setString(5, procurementid);
			preparedStatement.setString(6, isdm);
			preparedStatement.setString(7, dmparentid);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int updatePoVatAllocation(String string, String voucherno) {
		int res = 0;
		try {
			String sql = "update inventory_po_vat_allocations set oldprocid='" + string + "',voucherno='" + voucherno
					+ "' where procurementid='" + string + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int updateProcurementId(int newprocurementid, String string, String lastdate, String dateonly) {
		int res = 0;
		try {
			String sql = "update inventory_procurement set procurementid='" + newprocurementid + "',actual_dm_date='"
					+ lastdate + "',date='" + dateonly + "' where procurementid='" + string + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int updatePoVatProcurementId(int newprocurementid, String string) {
		int res = 0;
		try {
			String sql = "update inventory_po_vat_allocations set procurementid='" + newprocurementid
					+ "' where procurementid='" + string + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int updateVendorProcurementId(int newprocurementid, String string) {
		int res = 0;
		try {
			String sql = "update inventory_vendor_procurement set procurementid='" + newprocurementid
					+ "' where procurementid='" + string + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public Procurement getVendorPoDetails(String string) {
		Procurement procurement = new Procurement();
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append(
					"select id, procurementid, vat, discount, vendorid, voucherno, date, surcharge, cgst, sgst, igst, ");
			buffer.append(
					"security_inward_no, security_inward_date, remark, userid, disctype, paymode, credit, debit, oldprocid ");
			buffer.append("from inventory_vendor_procurement where procurementid='" + string + "' ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				procurement.setId(rs.getInt(1));
				procurement.setProcurementid(rs.getString(2));
				procurement.setVat(DateTimeUtils.changeFormat(rs.getDouble(3)));
				procurement.setDiscount(DateTimeUtils.changeFormat(rs.getDouble(4)));
				procurement.setVendor_id(rs.getString(5));
				procurement.setVoucherno(rs.getString(6));
				procurement.setDate(rs.getString(7));
				procurement.setSurcharge(DateTimeUtils.changeFormat(rs.getDouble(8)));
				procurement.setCgst(DateTimeUtils.changeFormat(rs.getDouble(9)));
				procurement.setSgst(DateTimeUtils.changeFormat(rs.getDouble(10)));
				procurement.setIgst(DateTimeUtils.changeFormat(rs.getDouble(11)));
				procurement.setSecurity_no(rs.getString(12));
				procurement.setSecurity_date(rs.getString(13));
				procurement.setRemark(rs.getString(14));
				procurement.setUserid(rs.getString(15));
				procurement.setDisctype(rs.getString(16));
				procurement.setPaymode(rs.getString(17));
				procurement.setCredit(DateTimeUtils.changeFormat(rs.getDouble(18)));
				procurement.setDebit(DateTimeUtils.changeFormat(rs.getDouble(19)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return procurement;
	}

	public int IsDeliverMemo(String procurementid) {
		PreparedStatement ps = null;
		int result = 0;
		String sql = "select isdelivermemo from inventory_parent_procurement where id='" + procurementid + "'";
		try {
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getDeliverSeqNo(String procurementid) {
		PreparedStatement ps = null;
		int result = 0;
		String sql = "select dmseq from inventory_parent_procurement where id='" + procurementid + "'";
		try {
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public int getOldProcurementId(String id) {
		int res = 0;
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select oldprocid from inventory_procurement ");
			buffer.append("where inventory_procurement.id=" + id + " and oldprocid!='0' limit 0,1 ");
			PreparedStatement ps = connection.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				res = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int getGrnNoFromProcId(String procurementid) {
		int grnno = 0;
		try {
			String sql = "select grnno from inventory_procurement where procurementid='" + procurementid
					+ "' and grnno!='0' limit 0,1";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				grnno = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return grnno;
	}

	public int updateDiscQty(String qty, String discount, int id, String rate, String vat, String poDescription, String poHsnno,String userid) {
		int res = 0;
		try {
			String sql = "update inventory_procurement set qty=?, discount=?, purprice=?, gstper=?,"
					+ "poDescription=?,poHsnno=?,updated_by=? where id='" + id
					+ "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, qty);
			preparedStatement.setString(2, discount);
			preparedStatement.setString(3, rate);
			preparedStatement.setString(4, vat);
			preparedStatement.setString(5, poDescription);
			preparedStatement.setString(6, poHsnno);
			preparedStatement.setString(7, userid);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<Procurement> getNotificationlist(String fromDate, String toDate) {
		ArrayList<Procurement> list = new ArrayList<Procurement>();
		fromDate = DateTimeUtils.getCommencingDate1(fromDate);
		toDate = DateTimeUtils.getCommencingDate1(toDate);
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append(
					"select DISTINCT inventory_procurement.procurementid,inventory_procurement.grnno,grnseqno,inventory_vendor.name,apm_condition.name,confirm,inventory_procurement.date ");
			buffer.append(" from inventory_procurement ");
			buffer.append("left join apm_condition on apm_condition.id = inventory_procurement.location ");
			buffer.append("left join inventory_vendor on inventory_procurement.vendorid=inventory_vendor.id ");
			buffer.append("where inventory_procurement.prodid!='0' and confirm='0' and grnno!='0' and date between '"
					+ fromDate + "' and '" + toDate + "' and iscancel='0' ");
			PreparedStatement ps = connection.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Procurement procurement = new Procurement();
				procurement.setGrnno(rs.getString(2));
				procurement.setGrnseqno(rs.getInt(3));
				procurement.setVendor(rs.getString(4));
				procurement.setLocationname(rs.getString(5));
				procurement.setConfirm(rs.getInt(6));
				Product grndata = getGrnDetails(rs.getInt(2));
				procurement.setGrndate(grndata.getDate());
				if(grndata.getIsfromworkorder().equals("0")){
					list.add(procurement);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}

	public String getprodidfromPro(String procurementid) {
		PreparedStatement ps = null;
		String res = "";
		try {
			String sql = "select prodid from inventory_procurement where id='" + procurementid + "'";
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				res = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int saveParentAgreement(String userId, String datetime, String location) {
		int res = 0;
		try {
			String sql = "insert into inventory_parent_agreement (agre_userid,agre_date,agre_location,agre_status) values (?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userId);
			preparedStatement.setString(2, datetime);
			preparedStatement.setString(3, location);
			preparedStatement.setString(4, "0");
			res = preparedStatement.executeUpdate();
			if (res > 0) {
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if (resultSet.next()) {
					res = resultSet.getInt(1);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int saveChildAgreement(Product product) {
		int res = 0;
		try {
			if (product.getDiscount() != null) {
				if (product.getDiscount().equals("")) {
					product.setDiscount("0");
				}
			} else {
				product.setDiscount("0");
			}
			String sql = "insert into inventory_child_agreement (parentid,catalogueid,rate,discount,qty,pack,gst,vendorid) values (?,?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, product.getParentid());
			preparedStatement.setString(2, product.getCatalogueid());
			preparedStatement.setString(3, product.getPurchase_price());
			preparedStatement.setString(4, product.getDiscount());
			preparedStatement.setDouble(5, product.getQty());
			preparedStatement.setString(6, product.getPack());
			preparedStatement.setString(7, product.getVat());
			preparedStatement.setString(8, product.getVendor_id());
			res = preparedStatement.executeUpdate();
			if (res > 0) {
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if (resultSet.next()) {
					res = resultSet.getInt(1);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int getAgreementCount(String location, String fromdate, String todate, String voucherno) {
		int res = 0;
		try {
			todate = todate + " " + "59:59:59";
			StringBuffer buffer = new StringBuffer();
			buffer.append("select inventory_parent_agreement.id from inventory_parent_agreement ");
			buffer.append(
					"inner join inventory_child_agreement on inventory_child_agreement.parentid = inventory_parent_agreement.id ");
			buffer.append("inner join inventory_vendor on inventory_vendor.id = inventory_child_agreement.vendorid ");
			buffer.append("left join apm_condition on  apm_condition.id = inventory_parent_agreement.agre_location ");
			buffer.append("where agre_date between '" + fromdate + "' and '" + todate + "' ");
			if (voucherno != null) {
				buffer.append("and (inventory_vendor.name like '%" + voucherno + "%' or inventory_parent_agreement.id='"
						+ voucherno + "') ");
			}
			buffer.append("group by inventory_parent_agreement.id ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				res = res + 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<Procurement> getAllAgreementList(Pagination pagination, String location, String voucherno,
			String fromdate, String todate) {
		ArrayList<Procurement> arrayList = new ArrayList<Procurement>();
		try {
			todate = todate + " " + "59:59:59";
			StringBuffer buffer = new StringBuffer();
			buffer.append("select inventory_parent_agreement.id, agre_userid, agre_date, agre_location, agre_status, ");
			buffer.append("inventory_vendor.name,apm_condition.name,iscancel ");
			buffer.append("from inventory_parent_agreement ");
			buffer.append(
					"inner join inventory_child_agreement on inventory_child_agreement.parentid = inventory_parent_agreement.id ");
			buffer.append("inner join inventory_vendor on inventory_vendor.id = inventory_child_agreement.vendorid ");
			buffer.append("left join apm_condition on  apm_condition.id = inventory_parent_agreement.agre_location ");
			buffer.append("where agre_date between '" + fromdate + "' and '" + todate + "' ");
			if (voucherno != null) {
				buffer.append("and (inventory_vendor.name like '%" + voucherno + "%' or inventory_parent_agreement.id='"
						+ voucherno + "') ");
			}
			buffer.append("group by inventory_parent_agreement.id ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Procurement procurement = new Procurement();
				procurement.setId(rs.getInt(1));
				procurement.setUserid(rs.getString(2));
				procurement.setDate(rs.getString(3));
				procurement.setLocation(rs.getString(4));
				procurement.setStatus(rs.getInt(5));
				procurement.setVendor(rs.getString(6));
				procurement.setLocationname(rs.getString(7));
				procurement.setIscancel(rs.getString(8));
				arrayList.add(procurement);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public int deleteNewGrnBycatId(String catalogueid, String location) {
		int result = 0;
		try {
			// String sql="delete from temp_po where catalogueid="+catalogueid+"
			// and status='0' ";
			StringBuffer buffer = new StringBuffer();
			buffer.append("delete from temp_po where catalogueid=" + catalogueid + " and status='0' ");
			buffer.append("and EXISTS( ");
			buffer.append("SELECT 1 FROM inventory_catalogue ");
			buffer.append("WHERE inventory_catalogue.id = temp_po.catalogueid ");
			if (!location.equals("0")) {
				buffer.append("and inventory_catalogue.location='" + location + "' ");
			}
			buffer.append(") ");

			PreparedStatement ps = connection.prepareStatement(buffer.toString());
			result = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public Vendor getVendorDetailsOfAgreement(String agreementidid) {
		Vendor vendor = new Vendor();
		InventoryVendorDAO inventoryVendorDAO = new JDBCInventoryVendorDAO(connection);
		try {

			String sql = "select vendorid from inventory_child_agreement where parentid=" + agreementidid + " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String vendorid = rs.getString(1);
				vendor = inventoryVendorDAO.getVendor(vendorid);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return vendor;
	}

	public ArrayList<Product> getAgreementListBeforeProcurement(String agreementidid) {
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		ArrayList<Product> list = new ArrayList<Product>();

		try {
			// String sql="select
			// id,prodid,purprice,qty,total,discount,longpo,date,grnno,catalogueid,location
			// from inventory_procurement where procurementid="+procurementid+"
			// ";
			// select id, parentid, catalogueid, rate, discount, qty, pack, gst,
			// vendorid from inventory_child_agreement
			// String sql="select id, parentid, catalogueid, rate, discount,
			// qty, pack, gst, vendorid from inventory_child_agreement where
			// parentid="+agreementidid+" ";
			StringBuffer buffer = new StringBuffer();
			buffer.append(
					"select inventory_child_agreement.id, parentid, catalogueid, rate, discount, qty, pack, gst, vendorid,  agre_userid, agre_date, ");
			buffer.append("agre_location, agre_status, approveuserid, approvedate ");
			buffer.append("from inventory_child_agreement ");
			buffer.append(
					"inner join inventory_parent_agreement on inventory_parent_agreement.id = inventory_child_agreement.parentid ");
			buffer.append("where parentid=" + agreementidid + " ");
			PreparedStatement ps = connection.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();
			double subtotal = 0;
			double netsubtotal = 0;
			double totalgstamount = 0;
			while (rs.next()) {
				Product product = new Product();
				product.setDiscount(DateTimeUtils.changeFormat(rs.getDouble(5)));
				product.setId(rs.getInt(1));
				product.setQuantity(rs.getString(6));
				String datetime[] = rs.getString(11).split(" ");
				product.setDate(DateTimeUtils.getCommencingDate1(datetime[0]));
				product.setCatalogueid(rs.getString(3));
				Product master = null;
				master = inventoryProductDAO.getProductCatalogueDetails(product.getCatalogueid());

				double rate = rs.getDouble(4);
				product.setProduct_name(master.getProduct_name());
				int pack = rs.getInt(7);
				if (pack == 0) {
					master.setPack("1");
				} else {
					master.setPack("" + pack);
				}
				double total = rs.getInt(6) * rate;
				subtotal = subtotal + total;
				product.setSubTotal(DateTimeUtils.changeFormat(subtotal));
				product.setTotal(DateTimeUtils.changeFormat(total));
				product.setMrp(master.getMrp());
				product.setSale_price(master.getSale_price());
				product.setPurchase_price(DateTimeUtils.changeFormat(rs.getDouble(4)));
				product.setVendor_id(rs.getString(9));
				product.setBatch_no(master.getBatch_no());
				product.setPack(master.getPack());
				product.setMfg(master.getMfg());
				product.setHsnno(master.getHsnno());
				product.setVat("" + rs.getInt(8));
				product.setSale_price(master.getSale_price());
				// 23 June 2018
				double purchaseprice = rate;
				double vat = 0;
				vat = Double.parseDouble(master.getVat());
				int qty = rs.getInt(6);
				double netamount = (purchaseprice * vat) / 100;
				netamount = netamount * qty;
				netamount = Math.round(netamount * 100.0) / 100.0;
				product.setGstamount(String.valueOf(netamount));
				totalgstamount = totalgstamount + netamount;
				product.setTotalgstamount(String.valueOf(totalgstamount));

				double nettotal = netamount + total;
				double temp = (nettotal * rs.getDouble(5)) / 100;
				nettotal = nettotal - temp;
				product.setNetamount(String.valueOf(nettotal));

				netsubtotal = netsubtotal + nettotal;
				product.setTotalnetamount(String.valueOf(netsubtotal));
				// lokesh
				product.setStockqty(getStock(product.getCatalogueid(), rs.getString(12)));
				product.setOldvenderpurprise(getoldpurprice(product.getCatalogueid()));
				String location = "" + rs.getInt(12);
				product.setLocation(location);
				/*
				 * ArrayList<Master> cellList = new ArrayList<Master>();
				 * if(!location.equals("0")){ cellList =
				 * inventoryProductDAO.getcellList(location); }else{ cellList =
				 * inventoryProductDAO.getcellList("32,33,34,35,36,105"); }
				 * 
				 * int prevshelfid =
				 * inventoryProductDAO.getPreviousShelfId(product.getCatalogueid
				 * (); ArrayList<Master> cellList1 = new ArrayList<Master>();
				 * for (Master master2 : cellList) {
				 * if(master2.getId()==prevshelfid){ master2.setStatus(1);
				 * }else{ master2.setStatus(2); } cellList1.add(master2); }
				 * product.setShelfList(cellList1);
				 */
				product.setPro_code(master.getPro_code());
				list.add(product);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	public boolean isThisProductExistINAgreement(String procurementid, String catalogueid) {
		try {
			String sql = "select id from inventory_child_agreement where catalogueid=" + catalogueid + " and parentid="
					+ procurementid + " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return true;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return false;
	}

	public Product getAgreementListBeforeProcurement(String procurementid, String catalogueid) {
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		Product product = new Product();
		try {
			// String sql="select
			// id,prodid,purprice,qty,total,discount,longpo,date,grnno,catalogueid,location
			// from inventory_procurement where procurementid="+procurementid+"
			// ";
			// select id, parentid, catalogueid, rate, discount, qty, pack, gst,
			// vendorid from inventory_child_agreement
			// String sql="select id, parentid, catalogueid, rate, discount,
			// qty, pack, gst, vendorid from inventory_child_agreement where
			// parentid="+agreementidid+" ";
			StringBuffer buffer = new StringBuffer();
			buffer.append(
					"select inventory_child_agreement.id, parentid, catalogueid, rate, discount, qty, pack, gst, vendorid,  agre_userid, agre_date, ");
			buffer.append("agre_location, agre_status, approveuserid, approvedate ");
			buffer.append("from inventory_child_agreement ");
			buffer.append(
					"inner join inventory_parent_agreement on inventory_parent_agreement.id = inventory_child_agreement.parentid ");
			buffer.append("where parentid=" + procurementid + " and catalogueid='" + catalogueid + "' ");
			PreparedStatement ps = connection.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();
			double subtotal = 0;
			double netsubtotal = 0;
			double totalgstamount = 0;
			while (rs.next()) {
				product.setDiscount(DateTimeUtils.changeFormat(rs.getDouble(5)));
				product.setId(rs.getInt(1));
				product.setQuantity(rs.getString(6));
				String datetime[] = rs.getString(11).split(" ");
				product.setDate(DateTimeUtils.getCommencingDate1(datetime[0]));
				product.setCatalogueid(rs.getString(3));
				Product master = null;
				master = inventoryProductDAO.getProductCatalogueDetails(product.getCatalogueid());

				double rate = rs.getDouble(4);
				product.setProduct_name(master.getProduct_name());
				int pack = rs.getInt(7);
				if (pack == 0) {
					master.setPack("1");
				} else {
					master.setPack("" + pack);
				}
				double total = rs.getInt(6) * rate;
				subtotal = subtotal + total;
				product.setSubTotal(DateTimeUtils.changeFormat(subtotal));
				product.setTotal(DateTimeUtils.changeFormat(total));
				product.setMrp(master.getMrp());
				product.setSale_price(master.getSale_price());
				product.setPurchase_price(DateTimeUtils.changeFormat(rs.getDouble(4)));
				product.setVendor_id(rs.getString(9));
				product.setBatch_no(master.getBatch_no());
				product.setPack(master.getPack());
				product.setMfg(master.getMfg());
				product.setHsnno(master.getHsnno());
				product.setVat("" + rs.getInt(8));
				product.setSale_price(master.getSale_price());
				// 23 June 2018
				double purchaseprice = rate;
				double vat = 0;
				vat = Double.parseDouble(master.getVat());
				int qty = rs.getInt(6);
				double netamount = (purchaseprice * vat) / 100;
				netamount = netamount * qty;
				netamount = Math.round(netamount * 100.0) / 100.0;
				product.setGstamount(String.valueOf(netamount));
				totalgstamount = totalgstamount + netamount;
				product.setTotalgstamount(String.valueOf(totalgstamount));

				double nettotal = netamount + total;
				double temp = (nettotal * rs.getDouble(5)) / 100;
				nettotal = nettotal - temp;
				product.setNetamount(String.valueOf(nettotal));

				netsubtotal = netsubtotal + nettotal;
				product.setTotalnetamount(String.valueOf(netsubtotal));
				// lokesh
				product.setStockqty(getStock(product.getCatalogueid(), rs.getString(12)));
				product.setOldvenderpurprise(getoldpurprice(product.getCatalogueid()));
				String location = "" + rs.getInt(12);
				product.setLocation(location);
				/*
				 * ArrayList<Master> cellList = new ArrayList<Master>();
				 * if(!location.equals("0")){ cellList =
				 * inventoryProductDAO.getcellList(location); }else{ cellList =
				 * inventoryProductDAO.getcellList("32,33,34,35,36,105"); }
				 * 
				 * int prevshelfid =
				 * inventoryProductDAO.getPreviousShelfId(product.getCatalogueid
				 * (); ArrayList<Master> cellList1 = new ArrayList<Master>();
				 * for (Master master2 : cellList) {
				 * if(master2.getId()==prevshelfid){ master2.setStatus(1);
				 * }else{ master2.setStatus(2); } cellList1.add(master2); }
				 * product.setShelfList(cellList1);
				 */
				product.setPro_code(master.getPro_code());
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return product;
	}

	public int deleteAgreement(String id) {
		int result = 0;

		try {
			String sql = "delete from inventory_child_agreement where id=" + id + "";
			PreparedStatement ps = connection.prepareStatement(sql);

			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public int updateAgreementData(String qty, String discount, int id, String ratepur) {
		int res = 0;
		try {
			String sql = "update inventory_child_agreement set rate=?, discount=?, qty=? where id='" + id + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, ratepur);
			preparedStatement.setString(2, discount);
			preparedStatement.setString(3, qty);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int updateConfirmAgreementStatus(String procurementid, String status, String userid, String datetime,
			String remark) {
		int result = 0;

		try {
			String sql = "update inventory_parent_agreement set agre_status='" + status + "', approveuserid='" + userid
					+ "', approvedate='" + datetime + "',remark=? where id=" + procurementid + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, remark);
			result = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public int cancelAgreement(String procurementid, String userId, String datetime, String remark) {
		int result = 0;

		try {
			String sql = "update inventory_parent_agreement set iscancel='1', cancel_userid='" + userId
					+ "', cancel_date='" + datetime + "',cancel_remark=? where id=" + procurementid + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, remark);
			result = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public Product getAgreementBeforeProcurement(String childid) {
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		Product product = new Product();

		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append(
					"select inventory_child_agreement.id, parentid, catalogueid, rate, discount, qty, pack, gst, vendorid,  agre_userid, agre_date, ");
			buffer.append("agre_location, agre_status, approveuserid, approvedate ");
			buffer.append("from inventory_child_agreement ");
			buffer.append(
					"inner join inventory_parent_agreement on inventory_parent_agreement.id = inventory_child_agreement.parentid ");
			buffer.append("where inventory_child_agreement.id=" + childid + " ");
			PreparedStatement ps = connection.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();
			double subtotal = 0;
			double netsubtotal = 0;
			double totalgstamount = 0;
			while (rs.next()) {

				product.setDiscount(DateTimeUtils.changeFormat(rs.getDouble(5)));
				product.setId(rs.getInt(1));
				product.setQuantity(rs.getString(6));
				String datetime[] = rs.getString(11).split(" ");
				product.setDate(DateTimeUtils.getCommencingDate1(datetime[0]));
				product.setCatalogueid(rs.getString(3));
				Product master = null;
				master = inventoryProductDAO.getProductCatalogueDetails(product.getCatalogueid());

				double rate = rs.getDouble(4);
				product.setProduct_name(master.getProduct_name());
				int pack = rs.getInt(7);
				if (pack == 0) {
					master.setPack("1");
				} else {
					master.setPack("" + pack);
				}
				double total = rs.getInt(6) * rate;
				subtotal = subtotal + total;
				product.setSubTotal(DateTimeUtils.changeFormat(subtotal));
				product.setTotal(DateTimeUtils.changeFormat(total));
				product.setMrp(master.getMrp());
				product.setSale_price(master.getSale_price());
				product.setPurchase_price(DateTimeUtils.changeFormat(rs.getDouble(4)));
				product.setVendor_id(rs.getString(9));
				product.setGenericname(master.getGenericname());
				product.setBatch_no(master.getBatch_no());
				product.setPack(master.getPack());
				product.setMfg(master.getMfg());
				product.setHsnno(master.getHsnno());
				product.setVat("" + rs.getInt(8));
				product.setSale_price(master.getSale_price());
				// 23 June 2018
				double purchaseprice = rate;
				double vat = 0;
				vat = Double.parseDouble(master.getVat());
				int qty = rs.getInt(6);
				double netamount = (purchaseprice * vat) / 100;
				netamount = netamount * qty;
				netamount = Math.round(netamount * 100.0) / 100.0;
				product.setGstamount(String.valueOf(netamount));
				totalgstamount = totalgstamount + netamount;
				product.setTotalgstamount(String.valueOf(totalgstamount));

				double nettotal = netamount + total;
				double temp = (nettotal * rs.getDouble(5)) / 100;
				nettotal = nettotal - temp;
				product.setNetamount(String.valueOf(nettotal));

				netsubtotal = netsubtotal + nettotal;
				product.setTotalnetamount(String.valueOf(netsubtotal));
				// lokesh
				product.setStockqty(getStock(product.getCatalogueid(), rs.getString(12)));
				product.setOldvenderpurprise(getoldpurprice(product.getCatalogueid()));
				String location = "" + rs.getInt(12);
				product.setLocation(location);
				/*
				 * ArrayList<Master> cellList = new ArrayList<Master>();
				 * if(!location.equals("0")){ cellList =
				 * inventoryProductDAO.getcellList(location); }else{ cellList =
				 * inventoryProductDAO.getcellList("32,33,34,35,36,105"); }
				 * 
				 * int prevshelfid =
				 * inventoryProductDAO.getPreviousShelfId(product.getCatalogueid
				 * (); ArrayList<Master> cellList1 = new ArrayList<Master>();
				 * for (Master master2 : cellList) {
				 * if(master2.getId()==prevshelfid){ master2.setStatus(1);
				 * }else{ master2.setStatus(2); } cellList1.add(master2); }
				 * product.setShelfList(cellList1);
				 */
				product.setPro_code(master.getPro_code());

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return product;
	}

	public int getAgreementOrderQty(int id) {
		int count = 0;
		try {
			String sql = "select sum(qty) from inventory_procurement where child_agreementid='" + id + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public boolean checkVendorVatSaved(String procurement) {
		boolean flag = false;
		try {
			String sql = "select id from inventory_vendor_procurement where procurementid='" + procurement + "' ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public int getprocurementidByreturn(String returnid) {
		int res = 0;
		PreparedStatement ps = null;
		try {
			String sql = "select procurementid from inventory_product_return_log where grnreturnid='" + returnid + "'";
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				res = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public double getDiscountAmt(String procid, String productid) {
		double res = 0.0;
		PreparedStatement ps = null;
		try {
			String sql = " select discount from  inventory_vendor_procurement where procurementid='" + procid + "'";
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				res = rs.getDouble(1);
			}
			double x = getTotalAmt(procid);
			res = (res / x) * 100.00;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	private double getTotalAmt(String procid) {
		double res = 0.0;
		PreparedStatement ps = null;
		try {
			String sql = " select sum(total) from inventory_procurement where procurementid='" + procid + "' ";
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				res = rs.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int deleteReturnGrnProduct(String id) {
		int result = 0;
		try {
			String sql = "delete from inventory_product_temp_return where id=" + id + "";
			PreparedStatement ps = connection.prepareStatement(sql);
			result = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public int addReturnTempPo(String pid, String vendorid, double qty, int tid, String loginsessionid) {
		int result = 0;
		try {
			String sql = "insert into return_product_temporary (pid,vendorid,qty,temp_id,logsessionid) values (?,?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, pid);
			ps.setString(2, vendorid);
			ps.setDouble(3, qty);
			ps.setInt(4, tid);
			ps.setString(5, loginsessionid);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Product> getAllReturnListByVendor(String loginsessionid) {
		ArrayList<Product> list = new ArrayList<Product>();
		try {

			String sql = "SELECT id,vendorid from return_product_temporary where logsessionid=? group by vendorid ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, loginsessionid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				Product product = new Product();
				product.setId(rs.getInt(1));
				product.setVendor_id(rs.getString(2));
				list.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Product> getReturnProductByVendor(String vendor_id, String loginsessionid) {
		ArrayList<Product> list = new ArrayList<Product>();

		try {

			String sql = "SELECT pid,qty,temp_id from return_product_temporary where vendorid=" + vendor_id
					+ " and logsessionid=? ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, loginsessionid);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Product product = new Product();
				product.setId(rs.getInt(1));
				product.setProduct_id("" + rs.getInt(1));
				product.setQty(rs.getDouble(2));
				product.setVendor_id(vendor_id);
				product.setNewpo(rs.getInt(3));
				list.add(product);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	public int deleteReturnProdTempData(String loginsessionid) {
		int result = 0;
		try {
			String sql = "delete from return_product_temporary where logsessionid=?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, loginsessionid);
			result = ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public Product getProcurementIdFromProductId(String product_id) {
		Product product = new Product();
		try {
			int res = 0;
			String sql = " select prodid,procurementid from inventory_procurement where prodid='" + product_id + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				res = 1;
				product.setProduct_id("" + rs.getInt(1));
				product.setProcurementid("" + rs.getInt(2));
				product.setProdid(rs.getInt(1));
				product.setProcid(rs.getInt(2));
			}
			if (res == 0) {
				// direct transfer
				product = checkProductFromDirectTransfer(product_id);
				if (product.equals("0")) {
					// Request transfer
					product = checkProductFromRequestTransfer(product_id);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}

	private Product checkProductFromRequestTransfer(String product_id) {
		// Request Transfer log
		Product product = new Product();
		PreparedStatement ps = null;
		try {
			StringBuffer buffer = new StringBuffer();
			product.setStatus("0");
			buffer.append("select old_prodid from  inventory_request_temp_log   ");
			buffer.append(
					"inner join inventory_parent_transfer_log on  inventory_parent_transfer_log.id =inventory_request_temp_log.parentid where new_prodid='"
							+ product_id + "'  ");
			ps = connection.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				product = getProcurementDataFromProductId("" + rs.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}

	private Product checkProductFromDirectTransfer(String product_id) {
		// direct transfer
		Product product = new Product();
		PreparedStatement ps = null;
		try {
			StringBuffer buffer = new StringBuffer();
			product.setStatus("0");
			buffer.append("select old_prodid  from  inventory_transfer_log   ");
			buffer.append(
					"inner join inventory_parent_transfer_log on  inventory_parent_transfer_log.id =inventory_transfer_log.parentid where new_prodid='"
							+ product_id + "' and req_trans_ret='1' ");
			ps = connection.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				product = getProcurementDataFromProductId("" + rs.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}

	public Product getProcurementDataFromProductId(String product_id) {
		Product product = new Product();
		try {
			String sql = " select prodid,procurementid from inventory_procurement where prodid='" + product_id + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			product.setStatus("0");
			while (rs.next()) {
				product.setProduct_id("" + rs.getInt(1));
				product.setProcurementid("" + rs.getInt(2));
				product.setStatus("1");
				product.setProdid(rs.getInt(1));
				product.setProcid(rs.getInt(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}

	public ArrayList<Product> getVatAllocationOfReturn(String grnreturnid) {
		ArrayList<Product> list = new ArrayList<Product>();
		try {
			/*
			 * String
			 * sql="SELECT productid from inventory_product_return_log where grnreturnid="
			 * +grnreturnid+"  ";
			 */
			StringBuffer buffer = new StringBuffer();
			buffer.append("SELECT distinct inventory_product.vat from inventory_product_return_log ");
			buffer.append(
					"inner join inventory_product on inventory_product.id = inventory_product_return_log.productid ");
			buffer.append("where grnreturnid=" + grnreturnid + " ");
			PreparedStatement ps = connection.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				ArrayList<Product> arrayList = getVatAllocationOfReturnCal(grnreturnid, rs.getInt(1));
				int size = arrayList.size();
				double totalamt = 0;
				double totalvat = 0;
				String discount = "0";
				String taxable = "0";
				if (size > 0) {
					totalamt = arrayList.get(size - 1).getTotal_amount();
					totalvat = arrayList.get(size - 1).getTotalvat();
					discount = arrayList.get(size - 1).getDiscount();
					taxable = arrayList.get(size - 1).getTaxable();
				}
				product.setTotal_amount(totalamt);
				product.setTotalvat(totalvat);
				product.setVatrate("" + rs.getInt(1));
				product.setTotalamt(DateTimeUtils.changeFormat(totalamt));
				product.setTaxable(taxable);
				product.setDiscvat(discount);
				product.setTaxamt(DateTimeUtils.changeFormat(totalvat));
				list.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private ArrayList<Product> getVatAllocationOfReturnCal(String grnreturnid, int gst) {
		ArrayList<Product> list = new ArrayList<Product>();
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);

		try {
			/*
			 * String
			 * sql="SELECT id, productid, vendorid, qty, datetime, location, userid, status, procurementid,sec_out_no,sec_out_date,invoice_date,notes,newprodid,newprocid from inventory_product_return_log where grnreturnid="
			 * +grnreturnid+"  ";
			 */
			StringBuffer buffer = new StringBuffer();
			buffer.append(
					"SELECT inventory_product_return_log.id, productid, vendorid, inventory_product_return_log.qty,procurementid,newprocid ");
			buffer.append("from inventory_product_return_log ");
			buffer.append(
					"inner join inventory_product on inventory_product.id = inventory_product_return_log.productid ");
			buffer.append("where grnreturnid=" + grnreturnid + " and inventory_product.vat='" + gst + "'");
			PreparedStatement ps = connection.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();
			double totalamt = 0;
			double totalvat = 0;
			double totaltaxable = 0;
			double totaldiscount = 0;
			while (rs.next()) {
				Product product = new Product();
				Product master = inventoryProductDAO.getProduct(rs.getString(2));
				product.setProduct_name(master.getProduct_name());
				if (rs.getInt(5) == 0) {
					product.setProcurementid("" + rs.getInt(6));
				} else {
					product.setProcurementid("" + rs.getInt(5));
				}
				double stock =Double.parseDouble(master.getStock());
				int qty = rs.getInt(4);
				/*
				 * if(qty>stock){ qty = stock; }
				 */
				Product freeqtyproduct = getAvailableFreeQty(product.getProcurementid(), master.getCatalogueid());

				/*
				 * if (qty > stock) { qty = stock; }
				 */
				int tempqty = qty;
				int returnfreeqty = 0;
				if (freeqtyproduct.getRemainsqty() > qty) {
					returnfreeqty = tempqty;
					qty = 0;
				} else {
					int temp = qty - freeqtyproduct.getRemainsqty();
					qty = temp;
					returnfreeqty = tempqty - temp;
				}

				double total = qty * master.getIndividual_pur_price();
				total = Math.round(total * 100.0) / 100.0;

				double discountper = getDiscountAmt(product.getProcurementid(), "");
				double temptot = 0;
				double tempvat = 0;
				tempvat = total * discountper / 100;
				temptot = total - tempvat;
				double vatamt = temptot * (gst / 100.00);
				totalamt = totalamt + total;
				totalvat = totalvat + vatamt;
				totaltaxable = totaltaxable + temptot;
				totaldiscount = totaldiscount + tempvat;
				product.setVat("" + gst);
				product.setTotal_amount(Math.round(totalamt * 100.0) / 100.0);
				product.setTotalvat(Math.round(totalvat * 100.0) / 100.0);
				product.setDiscount(DateTimeUtils.changeFormat(Math.round(totaldiscount * 100.0) / 100.0));
				product.setTaxable(DateTimeUtils.changeFormat(Math.round(totaltaxable * 100.0) / 100.0));
				list.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int deleteReturnVatAllocations(String grnreturnid) {
		int result = 0;
		try {
			String sql = "delete from retrun_product_po_vat_allocations where returngrnid='" + grnreturnid + "' ";
			PreparedStatement ps = connection.prepareStatement(sql);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int saveReturnPoVatAllocations(Product product) {
		int result = 0;
		try {
			String sql = "insert into retrun_product_po_vat_allocations (returngrnid, voucherno, vatrate, totalamt, taxable, discount, taxamt) values "
					+ " (?,?,?,?,?,?,?) ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, product.getProcurementid());
			ps.setString(2, product.getVoucherno());
			ps.setString(3, product.getVatrate());
			ps.setString(4, product.getTotalamt());
			ps.setString(5, product.getTaxable());
			ps.setString(6, product.getDiscvat());
			ps.setString(7, product.getTaxamt());
			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public int getVendoridFromProc(String proc_id) {
		int res = 0;
		PreparedStatement ps = null;
		try {
			String sql = "select vendorid from inventory_parent_procurement where id=" + proc_id + "";
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				res = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<Product> getAllGrnReturnProductListPrint(String grnreturnid) {
		ArrayList<Product> list = new ArrayList<Product>();
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		InventoryVendorDAO inventoryVendorDAO = new JDBCInventoryVendorDAO(connection);
		try {
			/*
			 * String
			 * sql="SELECT id, productid, vendorid, qty, datetime, location, userid, status, procurementid,sec_out_no,sec_out_date,invoice_date,notes,newprodid,newprocid,newvat, newdiscper, newdiscount, newgstamt, newtotal, invoicenoo from inventory_product_return_log where grnreturnid="
			 * +grnreturnid+"  ";
			 */
			StringBuffer buffer = new StringBuffer();
			buffer.append(
					"SELECT id, productid, vendorid, qty, datetime, location, userid, status, procurementid,sec_out_no,sec_out_date, ");
			buffer.append(
					"invoice_date,notes,newprodid,newprocid,newvat, newdiscper, newdiscount, newgstamt, newtotal, invoicenoo,returnqty_ac, returnfreeqty_ac ");
			buffer.append("from inventory_product_return_log where grnreturnid=" + grnreturnid + " ");

			PreparedStatement ps = connection.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt(1));
				product.setProduct_id(rs.getString(2));
				product.setVendor_id(rs.getString(3));
				product.setQty(rs.getDouble(4));
				product.setDateTime(rs.getString(5));
				product.setLocation(rs.getString(6));
				product.setUserid(rs.getString(7));
				product.setStatus("" + rs.getInt(8));
				product.setProcurementid(rs.getString(9));
				product.setSecurity_no(rs.getString(10));
				product.setSecurity_date(rs.getString(11));
				product.setDate(rs.getString(12));
				product.setNotes(rs.getString(13));
				product.setGrnreturnid(grnreturnid);
				if (rs.getInt(9) == 0) {
					product.setProcurementid("" + rs.getInt(15));
				} else {
					product.setProcurementid("" + rs.getInt(9));
				}
				Vendor vendor = inventoryVendorDAO.getVendor(product.getVendor_id());

				Product master = inventoryProductDAO.getProduct(product.getProduct_id());
				product.setProduct_name(master.getProduct_name());
				product.setVendor(vendor.getName());
				product.setBatch_no(master.getBatch_no());
				product.setExpiry_date(master.getExpiry_date());
				product.setStock(master.getStock());
				product.setPurchase_price(master.getPurchase_price());
				// 22 NOV 2018 New filed added and calculation of data

				double discountper = rs.getDouble(17);
				product.setVat(rs.getString(16));
				product.setMfg(master.getMfg());
				product.setHsnno(master.getHsnno());
				product.setMrp(master.getMrp());
				product.setPack(master.getPack());

				double total = rs.getDouble(20);
				total = Math.round(total * 100.0) / 100.0;

				double temptot = 0;
				double tempvat = 0;
				tempvat = total * discountper / 100;
				temptot = total - tempvat;

				double vatamt = temptot * (rs.getDouble(16) / 100.00);
				double netpayble = total + vatamt;

				product.setTotal(DateTimeUtils.changeFormat(total));

				vatamt = Math.round(vatamt * 100.0) / 100.0;
				product.setGstamount(String.valueOf(vatamt));
				// formula rs amt =(total amount/100)*discpercentage
				double discountamt = (total / 100) * discountper;
				netpayble = netpayble - discountamt;
				netpayble = Math.round(netpayble * 100.0) / 100.0;
				product.setNetammt(netpayble);
				discountamt = Math.round(discountamt * 100.0) / 100.0;
				product.setDiscount(DateTimeUtils.changeFormat(discountamt));
				product.setDiscper(Math.round(discountper * 100.0) / 100.0);
				product.setVoucherno(rs.getString(21));

				int res = getProcurmentSeqNo(product.getProcurementid());
				String proSeqNo = "";
				if (res > 0) {
					proSeqNo = String.valueOf(res);
				} else {
					proSeqNo = product.getProcurementid();
				}
				product.setProSeqNo(proSeqNo);
				if (rs.getInt(22) == 0) {
					product.setReturnqty(rs.getInt(4));
				} else {
					product.setReturnqty(rs.getInt(22));
				}
				product.setReturnfreeqty(rs.getInt(23));
				String productlocation = getLocationFromProductId(rs.getString(2));
				product.setProductlocation(productlocation);
				list.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private String getLocationFromProductId(String string) {
		String location = "0";
		try {
			String sql = "select location from inventory_product where id='" + string + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				location = "" + rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return location;
	}

	public Product getParentReturnProductData(String grnreturnid) {
		Product product = new Product();
		try {

			String sql = "select id, vendorid, datetime, nettotal, netvat, netdiscount, netcgst, netsgst, netigst, netsubtotal, mainnotes,aprovedamt from inventory_return_grn_parent where id="
					+ grnreturnid + " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				product.setId(rs.getInt(1));
				product.setVendor_id(rs.getString(2));
				product.setDateTime(rs.getString(3));
				product.setNetammt(rs.getDouble(4));
				product.setVatrate(DateTimeUtils.changeFormat(rs.getDouble(5)));
				product.setDiscount(DateTimeUtils.changeFormat(rs.getDouble(6)));
				product.setCgst(DateTimeUtils.changeFormat(rs.getDouble(7)));
				product.setSgst(DateTimeUtils.changeFormat(rs.getDouble(8)));
				product.setIgst(DateTimeUtils.changeFormat(rs.getDouble(9)));
				product.setTotal(DateTimeUtils.changeFormat(rs.getDouble(10)));
				product.setAprovedamt(rs.getDouble(12));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return product;
	}

	public ArrayList<Product> getVatAllocationOfReturnPrint(String grnreturnid) {
		ArrayList<Product> list = new ArrayList<Product>();
		try {
			String sql = "select id, vatrate, totalamt, taxable, discount, taxamt from retrun_product_po_vat_allocations where returngrnid="
					+ grnreturnid + "  ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt(1));
				product.setProcurementid("" + grnreturnid);
				product.setVatrate(rs.getString(2));
				product.setTotalamt(rs.getString(3));
				product.setTaxable(rs.getString(4));
				product.setDiscvat(rs.getString(5));
				product.setTaxamt(rs.getString(6));

				product.setVatratee(rs.getInt(2));
				product.setTaxableamt(rs.getDouble(4));
				product.setTaxamts(rs.getDouble(6));
				list.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int updateReturnParentProductStatus(String aprovereturnbal, String aprovereturnid, String totalenteredpayamt,
			String userId, String date) {
		int res = 0;
		PreparedStatement ps = null;
		try {
			String sql = "update inventory_return_grn_parent set aprovedamt=?,aproveuserid=?,aprovedate=?   where id='"
					+ aprovereturnid + "'";
			ps = connection.prepareStatement(sql);
			ps.setString(1, totalenteredpayamt);
			ps.setString(2, userId);
			ps.setString(3, date);
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int updateReturnProductStatus(String aprovereturnid, String string) {
		int res = 0;
		PreparedStatement ps = null;
		try {
			String sql = "update inventory_product_return_log set status='" + string + "' where grnreturnid='"
					+ aprovereturnid + "'";
			ps = connection.prepareStatement(sql);
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int updateVendorReturnAccount(String aprovereturnid, String totalenteredpayamt) {
		int res = 0;
		PreparedStatement ps = null;
		try {
			String sql = "update inventory_return_vendor_account set aproved_amt=?  where grnreturnid='"
					+ aprovereturnid + "'";
			ps = connection.prepareStatement(sql);
			ps.setString(1, totalenteredpayamt);
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int getStatusRefundAmount(String string) {
		int res = 0;
		try {
			String sql = "select status from inventory_return_vendor_account where grnreturnid='" + string + "'";
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

	public boolean checkVendorLedgerPresentStatus(int vendoridnew) {
		boolean flag = false;
		try {
			String sql = "select * from ledger_master where vendorid='" + vendoridnew + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				flag = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public int insertLedger(Connection connection, int id, String name) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into ledger_master(name, services, paymode, bnkname, ltype, purchase,vendorid) values(?,?,?,?,?,?,?) ";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, getVendorservices());
			preparedStatement.setString(3, "0");
			preparedStatement.setString(4, "0");
			preparedStatement.setString(5, "1");
			preparedStatement.setString(6, "1");
			preparedStatement.setInt(7, id);
			result = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private String getVendorservices() {
		String s = "";
		try {
			PreparedStatement ps = connection.prepareStatement(" select def_vend_ledg_ser from apm_user where id='1' ");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				s = DateTimeUtils.isNull(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	public int vendorVoucherExistance(String vendorid, String isfromeditpo, String procurementid, String voucher) {
		int res = 0;
		try {

			// String sql="select id from inventory_procurement where
			// voucherno='"+voucher+"' ";
			StringBuffer buffer = new StringBuffer();
			buffer.append("select id from inventory_procurement where voucherno='" + voucher
					+ "' and voucherno!='' and vendorid='" + vendorid + "' ");
			if (isfromeditpo.equals("1")) {
				buffer.append("and procurementid!='" + procurementid + "' ");
			}
			PreparedStatement ps = connection.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				res = 1;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return res;
	}

	public int vendorVoucherExistanceForDm(String vendorid, String voucher) {
		int res = 0;
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select id from inventory_procurement where voucherno='" + voucher
					+ "' and voucherno!='' and vendorid='" + vendorid + "' ");
			PreparedStatement ps = connection.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				res = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public Product getProcurementDataBeforeProcurement(String procurementid, int id) {
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		Product product = new Product();
		try {
			String sql = "select id,prodid,purprice,qty,total,discount,longpo,date,grnno,catalogueid,location,isagreement,child_agreementid,procurementid,vendorid,parentpoid from inventory_procurement where id="
					+ id + " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			double subtotal = 0;
			double netsubtotal = 0;
			double totalgstamount = 0;
			while (rs.next()) {
				product.setDiscount(DateTimeUtils.changeFormat(rs.getDouble(6)));
				product.setId(rs.getInt(1));
				product.setProduct_id(rs.getString(2));
				product.setQuantity(rs.getString(4));
				product.setNewpo(rs.getInt(7));
				product.setDate(DateTimeUtils.getCommencingDate1(rs.getString(8)));
				product.setGrnno(rs.getInt(9));
				product.setCatalogueid(rs.getString(10));
				Product master = null;
				if (!product.getCatalogueid().equals("0")) {
					master = inventoryProductDAO.getProductCatalogueDetails(product.getCatalogueid());
				} else {
					master = inventoryProductDAO.getProduct(product.getProduct_id());
				}
				double rate = Double.parseDouble(master.getPurchase_price());
				product.setProduct_name(master.getProduct_name());
				int pack = Integer.parseInt(master.getPack());
				if (pack == 0) {
					master.setPack("1");
				}
				double total = rs.getInt(4) * rate;
				subtotal = subtotal + total;
				product.setSubTotal(DateTimeUtils.changeFormat(subtotal));
				product.setTotal(DateTimeUtils.changeFormat(total));
				product.setMrp(master.getMrp());
				product.setSale_price(master.getSale_price());
				product.setPurchase_price(master.getPurchase_price());
				product.setVendor_id("" + rs.getInt(15));
				product.setBatch_no(master.getBatch_no());
				product.setPack(master.getPack());
				product.setMfg(master.getMfg());
				product.setHsnno(master.getHsnno());
				product.setVat(master.getVat());
				product.setSale_price(master.getSale_price());
				// 23 June 2018
				double purchaseprice = rate;
				double vat = 0;
				if (master.getVat() != null) {
					if (!master.getVat().equals("")) {
						vat = Double.parseDouble(master.getVat());
					}
				}
				int qty = rs.getInt(4);
				double netamount = (purchaseprice * vat) / 100;
				netamount = netamount * qty;
				netamount = Math.round(netamount * 100.0) / 100.0;
				product.setGstamount(String.valueOf(netamount));
				totalgstamount = totalgstamount + netamount;
				product.setTotalgstamount(String.valueOf(totalgstamount));

				double nettotal = netamount + total;
				double temp = (nettotal * rs.getDouble(6)) / 100;
				nettotal = nettotal - temp;
				product.setNetamount(String.valueOf(nettotal));

				netsubtotal = netsubtotal + nettotal;
				product.setTotalnetamount(String.valueOf(netsubtotal));
				// lokesh
				product.setStockqty(getStock(product.getCatalogueid(), rs.getString(11)));
				product.setOldvenderpurprise(getoldpurprice(product.getCatalogueid()));
				String location = rs.getString(11);
				if (location == null) {
					location = "0";
				} else {
					if (location.equals("")) {
						location = "0";
					}
				}
				ArrayList<Master> cellList = new ArrayList<Master>();
				ArrayList<Master> newcellList = new ArrayList<Master>();
				if (!location.equals("0")) {
					cellList = inventoryProductDAO.getcellList(location);
					newcellList = inventoryProductDAO.getcellList(location);
				} else {
					cellList = inventoryProductDAO.getcellList("32,33,34,35,36,105");
					newcellList = inventoryProductDAO.getcellList("32,33,34,35,36,105");
				}

				Product productshelf = inventoryProductDAO.getPreviousShelfId(rs.getString(10), location);
				ArrayList<Master> cellList1 = new ArrayList<Master>();
				ArrayList<Master> cellList2 = new ArrayList<Master>();
				for (Master master2 : cellList) {
					if (master2.getId() == productshelf.getPreviouscellid()) {
						master2.setStatus(1);
					} else {
						master2.setStatus(2);
					}
					cellList1.add(master2);
				}
				for (Master master3 : newcellList) {
					if (master3.getId() == productshelf.getPreviousseccellid()) {
						master3.setStatus(1);
					} else {
						master3.setStatus(2);
					}
					cellList2.add(master3);
				}
				product.setShelfList(cellList1);
				product.setSecondshelfList(cellList2);
				product.setPro_code(master.getPro_code());
				product.setIsagreement(rs.getInt(12));
				product.setChildagreeid(rs.getString(13));
				product.setProcurementid(rs.getString(14));
				product.setParentpoid(rs.getInt(16));
				product.setLocation(location);
				product.setPreviouscellid(productshelf.getPreviouscellid());
				product.setPreviousseccellid(productshelf.getPreviousseccellid());
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return product;
	}

	public ArrayList<Product> getRequestedPoListByIDs(String tempoids, String location) {
		ArrayList<Product> list = new ArrayList<Product>();
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		InventoryVendorDAO inventoryVendorDAO = new JDBCInventoryVendorDAO(connection);
		try {
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -31);
			String fromdate = dateFormat.format(cal.getTime());
			fromdate = DateTimeUtils.getCommencingDate1(fromdate);

			Calendar cal1 = Calendar.getInstance();
			String todate = dateFormat.format(cal1.getTime());
			todate = DateTimeUtils.getCommencingDate1(todate);

			StringBuffer buffer = new StringBuffer();
			buffer.append("SELECT temppo.id,catalogueid,prod_id,sum(qty),date,newpo,vendorid,parentid,indent_no ");
			buffer.append("from (SELECT * FROM temp_po ORDER BY id DESC) AS temppo ");
			buffer.append("left join  inventory_catalogue on inventory_catalogue.id = temppo.catalogueid ");
			buffer.append(
					"where temppo.status=0 and temppo.catalogueid in (" + tempoids + ") and temppo.catalogueid!='0'  ");
			if (!location.equals("0")) {
				buffer.append("and  inventory_catalogue.location=" + location + " ");
			}
			buffer.append("group by catalogueid order by temppo.id desc ");
			String sql = buffer.toString();

			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			ArrayList<Vendor> vendorlist = inventoryVendorDAO.getAllVendorList(location);

			while (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt(1));
				product.setCatalogueid(rs.getString(2));
				product.setProduct_id(rs.getString(3));
				Product master = null;
				if (rs.getInt(2) > 0) {
					master = inventoryProductDAO.getProductCatalogueDetails(product.getCatalogueid());
				} else {
					master = inventoryProductDAO.getProduct(product.getProduct_id());
				}
				double stock = inventoryProductDAO.getStockAvailableNew(product.getCatalogueid(), location);
				product.setStock(Double.toString(stock));
				product.setQty(rs.getDouble(4));
				product.setDate(rs.getString(5));
				product.setMaxorder(master.getMaxorder());
				product.setMinorder(master.getMinorder());
				product.setNewpo(rs.getInt(6));
				product.setProduct_name(master.getProduct_name());
				product.setCatalogueid(master.getCatalogueid());
				if (rs.getInt(6) == 1) {
					product.setVendor_id(rs.getString(7));
					// product.setVendorList(getAllVendorOfProduct(product.getCatalogueid(),rs.getString(7)));
					product.setVendorlist(vendorlist);
				} else {
					// product.setVendorList(getAllVendorOfProduct(product.getCatalogueid(),master.getVendor_id()));
					product.setVendor_id(master.getVendor_id());
					product.setVendorlist(vendorlist);
				}
				/*
				 * Vendor vendor =
				 * inventoryVendorDAO.getVendor(product.getVendor_id());
				 * product.setVendor(vendor.getName());
				 */
				Product masterlast = getLastProductDetailsOfSupplier(master.getProduct_name(), master.getVendor_id());
				double rate = Double.parseDouble(masterlast.getPurchase_price());
				double purchaserate = 0;
				if (master.getPurchase_price() != null) {
					if (!master.getPurchase_price().equals("")) {
						purchaserate = Double.parseDouble(master.getPurchase_price());
					}
				}

				if (rate <= 0) {
					product.setPurchase_price(master.getPurchase_price());
					product.setVat(master.getVat());
				} else {
					if (purchaserate <= 0) {
						product.setPurchase_price(DateTimeUtils.changeStringFormat(masterlast.getPurchase_price()));
						product.setVat(masterlast.getVat());
					} else {
						product.setPurchase_price(DateTimeUtils.changeFormat(purchaserate));
						product.setVat(masterlast.getVat());
					}

				}
				product.setFreeqty(master.getFreeqty());

				Product product2 = inventoryProductDAO.getParentTransferData(rs.getString(8));

				product.setFrom_location(product2.getFrom_location());
				product.setReq_userid(product2.getReq_userid());
				product.setIndent(rs.getInt(9));
				// lokesh
				if (master.getPro_code() == null) {
					master.setPro_code("");
				}
				product.setPro_code(master.getPro_code());

				double consumed = 0;
				String locationss = "0";
				if (master.getLocation() == null) {
					locationss = "0";
				} else {
					locationss = master.getLocation();
				}
				/*
				 * if(locationss.equals("36")){ Product directtransferproductout
				 * = inventoryProductDAO.getDirectTransferBetweenDate(product.
				 * getCatalogueid(),fromdate,locationss,todate); Product
				 * requesttransferproductout =
				 * inventoryProductDAO.getRequestTransferBetweenDate(product.
				 * getCatalogueid(),fromdate,locationss,todate); Product consume
				 * = inventoryProductDAO.getConsumeBetweenDate(product.
				 * getCatalogueid(),fromdate,todate,locationss); consumed =
				 * directtransferproductout.getTotalqty() +
				 * requesttransferproductout.getTotalqty()
				 * +consume.getTotalqty(); }else{ Product
				 * directtransferproductout =
				 * inventoryProductDAO.getDirectTransferBetweenDate(product.
				 * getCatalogueid(),fromdate,locationss,todate); Product
				 * requesttransferproductout =
				 * inventoryProductDAO.getRequestTransferBetweenDate(product.
				 * getCatalogueid(),fromdate,locationss,todate); Product
				 * salepatient =
				 * inventoryProductDAO.getPatientSaleBetweenDate(product.
				 * getCatalogueid(),fromdate,todate,locationss); Product consume
				 * = inventoryProductDAO.getConsumeBetweenDate(product.
				 * getCatalogueid(),fromdate,todate,locationss); consumed
				 * =salepatient.getTotalqty() +
				 * directtransferproductout.getTotalqty() +
				 * requesttransferproductout.getTotalqty()
				 * +consume.getTotalqty(); }
				 */
				Product directtransferproductout = inventoryProductDAO
						.getDirectTransferBetweenDate(product.getCatalogueid(), fromdate, locationss, todate);
				Product requesttransferproductout = inventoryProductDAO
						.getRequestTransferBetweenDate(product.getCatalogueid(), fromdate, locationss, todate);
				Product salepatient = inventoryProductDAO.getPatientSaleBetweenDate(product.getCatalogueid(), fromdate,
						todate, locationss);
				Product consume = inventoryProductDAO.getConsumeBetweenDate(product.getCatalogueid(), fromdate, todate,
						locationss);
				consumed = salepatient.getTotalqty() + directtransferproductout.getTotalqty()
						+ requesttransferproductout.getTotalqty() + consume.getTotalqty();
				product.setConsumed(consumed);
				product.setFromdate(DateTimeUtils.getCommencingDate1(fromdate));
				product.setTodate(DateTimeUtils.getCommencingDate1(todate));
				list.add(product);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	public double getpreviousledgerbalance(String ledgername) {
		double balance = 0;
		try {
			String sql = "select amount from ledger_amount where ledgerid='" + ledgername + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				balance = rs.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return balance;
	}

	public boolean checkPreviousCompletePo(String procurementid) {
		boolean flag = true;
		try {
			String sql = "select * from inventory_procurement where parentpoid='" + procurementid
					+ "' and proc_condition='1'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public int getProcurementPOPending(String procurementid, boolean flag, Procurement procurement2, String date1) {
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		int newprocurementid = 0;
		try {
			String date = DateTimeUtils.getUKCurrentDataTime(date1);

			newprocurementid = saveParengtPrecurementData(procurement2.getVendor_id(), date, 0, "0", 0,"","0");

			String sql = "select id,prodid,purprice,qty,total,discount,longpo,date,grnno,"
					+ "catalogueid,location,isagreement,child_agreementid,procurementid,vendorid,"
					+ "gstper,approve_qty, poHsnno, poDescription from inventory_procurement where procurementid="
					+ procurementid + " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Product product = new Product();

				int recivedqty = getRecivedQty(rs.getString(14), rs.getString(10));
				product.setDiscount(DateTimeUtils.changeFormat(rs.getDouble(6)));
				product.setId(rs.getInt(1));
				product.setProduct_id(rs.getString(2));
				double qty = rs.getDouble(4);
				if (recivedqty < qty) {
					qty = qty - recivedqty;
				} else {
					continue;
				}
				product.setQuantity(Double.toString(qty));

				product.setNewpo(rs.getInt(7));
				product.setDate(DateTimeUtils.getCommencingDate1(rs.getString(8)));
				product.setGrnno(rs.getInt(9));
				product.setCatalogueid(rs.getString(10));
				Product master = null;
				master = inventoryProductDAO.getProductCatalogueDetails(product.getCatalogueid());
				product.setVendor("" + rs.getInt(15));
				product.setProduct_name(master.getProduct_name());
				product.setCategory_id(master.getCategory_id());
				product.setLocation("" + rs.getInt(11));
				product.setMrp(master.getMrp());
				product.setPurchase_price(DateTimeUtils.changeFormat(rs.getDouble(3)));
				product.setPack(master.getPack());
				product.setVat("" + rs.getInt(16));
				int pid = inventoryProductDAO.savetempProduct(product);
				int globleproductid = inventoryProductDAO.updateGlobalProductId(pid, pid);
				product.setId(pid);
				product.setProduct_id("" + pid);
				product.setNewprocurementid(newprocurementid);
				int parentpoid = Integer.parseInt(procurementid);
				product.setParentpoid(parentpoid);
				product.setProc_condition(1);
				int approveqty = rs.getInt(17);
				product.setPoHsnno(rs.getString(18));
				product.setPoDescription(rs.getString(19));
				int result = saveProcurementData(product, product.getQuantity(), 0, product.getVendor(), "0",
						newprocurementid, 0, 0, product.getVat());
				int result1 = updateConfirmPOStatus("" + newprocurementid);
				int result2 = addConfirmProcurement("", "" + newprocurementid);
				int r = updateQtyByAdminPending(approveqty, result);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return newprocurementid;
	}

	private int updateQtyByAdminPending(int approveqty, int result) {
		int res = 0;
		try {

			String sql = "update inventory_procurement set approve_qty='" + approveqty + "' where id=" + result + " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			res = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return res;
	}

	private int getRecivedQty(String procurementid, String catalogueid) {
		int res = 0;
		try {
			// String sql ="select sum(qty) from inventory_procurement where
			// grnwithpo_child=0 and deleted=0 and catalogueid='"+catalogueid+"'
			// and parentpoid='"+procurementid+"' and gudreceipt='1'";
			StringBuffer buffer = new StringBuffer();
			buffer.append("select sum(inventory_procurement.qty/pack) from inventory_procurement ");
			buffer.append("inner join inventory_product on inventory_product.id = inventory_procurement.prodid ");
			buffer.append(
					"where grnwithpo_child=0 and inventory_procurement.deleted=0 and inventory_procurement.catalogueid='"
							+ catalogueid + "' ");
			buffer.append("and parentpoid='" + procurementid + "' and gudreceipt='1' ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				res = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public boolean checkIsPendingPo(String procurementid) {
		boolean flag = true;
		try {
			String sql = "select * from inventory_procurement where procurementid='" + procurementid
					+ "' and grnwithpo_child=1";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public ArrayList<Product> getProcPoRequestedList(String procurementid) {
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		ArrayList<Product> list = new ArrayList<Product>();
		try {
			String sql = "select id,prodid,purprice,qty,total,discount,longpo,date,grnno,catalogueid,location,isagreement,child_agreementid,procurementid,vendorid,gstper from inventory_procurement where procurementid="
					+ procurementid + " ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setDiscount(DateTimeUtils.changeFormat(rs.getDouble(6)));
				Product master = null;
				if (!rs.getString(10).equals("0")) {
					master = inventoryProductDAO.getProductCatalogueDetails(rs.getString(10));
				} else {
					master = inventoryProductDAO.getProduct(rs.getString(2));
				}
				double rate = rs.getDouble(3);
				product.setVat("" + rs.getInt(16));
				product.setPurchase_price(DateTimeUtils.changeFormat(rate));
				product.setQty(rs.getDouble(4));
				product.setProduct_name(master.getProduct_name());
				product.setPro_code(master.getPro_code());
				String date = rs.getString(8);
				String[] data = date.split(" ");
				String date2 = DateTimeUtils.getCommencingDate1(data[0]);
				String datetime = "";
				if (data.length > 1) {
					datetime = date2 + " " + data[1];
				} else {
					datetime = date2;
				}
				product.setDate(datetime);
				//Catalogue + procurementid
				double recivedqty = getRecivedQty(rs.getString(14), rs.getString(10));
				double qty = rs.getDouble(4);
				if (recivedqty < qty) {
					qty = qty - recivedqty;
				}else{
					qty =0;
				}
				product.setReceived_qty(Double.toString(recivedqty));
				product.setBalanceqty(Double.toString(qty));
				list.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int getPreviousCompletePoProcId(String procurementid) {
		int res = 0;
		try {
			String sql = "select procurementid from inventory_procurement where parentpoid='" + procurementid
					+ "' and proc_condition='1'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				res = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int getNewGrnPoNo(String procurementid) {
		int res = 0;
		try {
			String sql = "select grnno from inventory_procurement where procurementid='" + procurementid + "' ";
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

	public int getParentReqCount(int paraentpoid) {
		int res = 0;
		try {
			String sql = "select count(*) from inventory_procurement where procurementid='" + paraentpoid
					+ "' and grnwithpo_child=1";
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

	public int updatePendingPoStatus(int paraentpoid, int i) {
		int res = 0;
		try {
			String sql = "update inventory_parent_procurement set ispocomplete='" + i + "' where id='" + paraentpoid
					+ "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int updateProc_ConditionStatus(String procurementid) {
		int res = 0;
		try {
			String sql = "update inventory_procurement set proc_condition='0' where procurementid='" + procurementid
					+ "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int updateDeletePoPending(String procchildids, String procurementid) {
		int res = 0;
		try {
			String sql = "update inventory_procurement set deleted='0' where id not in(" + procchildids
					+ ") and procurementid='" + procurementid + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public String getOldParentProcId(String procurementid) {
		String parentprocid = "0";
		try {
			String sql = "select parentpoid from inventory_procurement where procurementid='" + procurementid
					+ "' and parentpoid!='0' limit 1";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				parentprocid = "" + rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return parentprocid;
	}

	public int updatePoConfirmData(int parentpoid, String catalogueid) {
		int res = 0;
		try {
			String sql = "update inventory_procurement set completepo='1' where procurementid='" + parentpoid
					+ "' and catalogueid='" + catalogueid + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int getCompletedPoCount(int paraentpoid) {
		int res = 0;
		try {
			String sql = "select count(*) from inventory_procurement where procurementid='" + paraentpoid
					+ "' and grnwithpo_child=1 and completepo='1' group by catalogueid ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				res = res + rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int updatePOCompleteParent(String id, String reason, String userid, String date) {
		int res = 0;
		try {
			String sql = "update inventory_parent_procurement set ispocomplete='2',compltpen_userid='" + userid
					+ "',compltpen_date='" + date + "',compltpen_reason=? where id='" + id + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, reason);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int deletePoPendingNotDone(String procurementid) {
		int res = 0;
		try {
			String sql = "delete from inventory_procurement where procurementid='" + procurementid
					+ "' and grnwithpo_child=0 and parentpoid!='0' and gudreceipt='0' ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int updateProcuremeentGRnDate(int newprocurementid, String date) {
		int res = 0;
		try {
			String sql = "update inventory_procurement set date='" + date + "' where procurementid='" + newprocurementid
					+ "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public String getProcurementGRNDate(String string) {
		String date = "";
		try {
			String sql = "select date from inventory_procurement where procurementid='" + string + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				date = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	public int updateParentVendorid(String procurementid, String newvendorid) {
		int res = 0;
		try {
			String sql = "update inventory_parent_procurement set vendorid='" + newvendorid + "' where id='"
					+ procurementid + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int updateChildVendorid(String procurementid, String newvendorid) {
		int res = 0;
		try {
			String sql = "update inventory_procurement set vendorid='" + newvendorid + "' where procurementid='"
					+ procurementid + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<String> getProductIdFromProcId(String procurementid) {
		ArrayList<String> arrayList = new ArrayList<String>();
		try {
			String sql = "select prodid from inventory_procurement where procurementid='" + procurementid + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String prodid = rs.getString(1);
				arrayList.add(prodid);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public int updateProductSupplierid(String oldprodid, String newvendorid) {
		int res = 0;
		try {
			String sql = "update inventory_product set supplierid='" + newvendorid + "' where id='" + oldprodid + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int updateVendorPorcVendorid(String procurementid, String newvendorid, String voucherno) {
		int res = 0;
		try {
			String sql = "update inventory_vendor_procurement set vendorid='" + newvendorid + "' where procurementid='"
					+ procurementid + "' and voucherno='" + voucherno + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int saveVendorIdChangeLog(String procurementid, String newvendorid, String oldvendorid, String datetime,
			String userId) {
		int result = 0;
		try {
			String sql = "insert into inevntory_vendorid_change_log (oldvendorid, newvendorid, datetime,userid,procurementid) values (?,?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, oldvendorid);
			ps.setString(2, newvendorid);
			ps.setString(3, datetime);
			ps.setString(4, userId);
			ps.setString(5, procurementid);
			result = ps.executeUpdate();
			if (result > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				while (rs.next()) {
					result = rs.getInt(1);
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public String getLocationIdFromProcurement(String procurementid) {
		String location = "0";
		try {
			String sql = "select location from inventory_procurement where id='" + procurementid + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				location = "" + rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return location;
	}

	public int updateLedgerVendor(String ledgerid, int vendoridnew, String procurementid, String oldledgerid) {
		int res = 0;
		try {
			String sql = "update ledger_sheet set ledgerid='" + ledgerid + "',vendorid='" + vendoridnew
					+ "' where proc_invoiceid='" + procurementid + "' and ledgerid='" + oldledgerid + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int getPurchaseExpenceId(String ledgerid, String procurementid) {
		int res = 0;
		try {
			String sql = "select expenceid from ledger_sheet where proc_invoiceid='" + procurementid
					+ "' and ledgerid='" + ledgerid + "' ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				res = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int updateExpenceChildLedger(int expenseid, String name, String ledgerid) {
		int res = 0;
		try {
			String sql = "update apm_expence_management set category='" + name + "',lledgerid='" + ledgerid
					+ "' where parentid='" + expenseid + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int updateExpenceParentLedger(int expenseid, String ledgerid) {
		int res = 0;
		try {
			String sql = "update apm_parent_expence_management set ledgerid='" + ledgerid + "' where id='" + expenseid
					+ "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<String> getParentPoProcIDs(String procurementid) {
		ArrayList<String> arrayList = new ArrayList<String>();
		try {
			String sql = "select distinct parentpoid,procurementid from inventory_procurement where parentpoid='"
					+ procurementid + "' ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				arrayList.add(rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public int updateProcurementAproveUserid(String procurementid, String userId, String date) {
		int res = 0;
		try {
			String sql = "update inventory_parent_procurement set approve_userid='" + userId + "',approve_date='" + date
					+ "' where id='" + procurementid + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int updateGrnWithPoMinusQty() {
		int res = 0;
		try {
			String sql = "update temp_po set qty=0 where qty<0";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int getRecivedQtyAgainstPO(int parentpoid, String catalogueid, String procchildids, String procurementid) {
		int res = 0;
		try {
			// String sql ="select sum(qty) from inventory_procurement where
			// parentpoid='"+parentpoid+"' and catalogueid='"+catalogueid+"' and
			// id in("+procchildids+") group by catalogueid";
			StringBuffer buffer = new StringBuffer();
			buffer.append("select sum(inventory_procurement.qty/pack) from inventory_procurement ");
			buffer.append("inner join inventory_product on inventory_product.id = inventory_procurement.prodid ");
			buffer.append("where parentpoid='" + parentpoid + "' and inventory_procurement.catalogueid='" + catalogueid
					+ "' ");
			buffer.append("and inventory_procurement.id in(" + procchildids
					+ ") group by inventory_procurement.catalogueid ");
			PreparedStatement preparedStatement = connection.prepareStatement(buffer.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				res = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int updateSubjectOfPO(String grnno, String textdata) {
		int res = 0;
		try {
			String sql = "update inventory_po_grn set subject_msg=? where id='" + grnno + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, textdata);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int getChildprocId(String procurementId) {
		int res = 0;
		try {
			PreparedStatement ps = connection.prepareStatement(
					"  select id from inventory_procurement where   procurementid ='" + procurementId + "'");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				res = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int updatemailcontentOfPO(String grnno, String textdata) {
		int res = 0;
		try {
			String sql = "update inventory_po_grn set mail_content=? where id='" + grnno + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, textdata);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int updateTempPoStatus(String catalogueid, String location) {
		int result = 0;
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("update temp_po set status=1 WHERE status=0 and catalogueid=" + catalogueid + " ");
			buffer.append(
					"and EXISTS( SELECT * FROM inventory_catalogue WHERE inventory_catalogue.id = temp_po.catalogueid ");
			buffer.append("and inventory_catalogue.location=" + location + ") ");

			PreparedStatement ps = connection.prepareStatement(buffer.toString());
			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	public int saveTempDataInGRNWIthPO(String datetime, String loginsessionid, int res, Product product,
			String product_id, String warehouse, double qty, String vendorid, int checkedentry, int isfrompolist,
			String indnetid, String hsnno, String isfromworkorder) {
		int result = 0;
		try {
			String sql = "insert into grn_with_po_tempdata (tempoid,catalogueid,rate,gst,"
					+ "supplier,req_qty,checkedentry,logsessionid,isfrompolist,dateandtime,"
					+ "location,productid,parentid,poHsnno,isfromworkorder) value (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, "" + res);
			ps.setString(2, "" + product.getId());
			ps.setString(3, product.getPurchase_price());
			ps.setString(4, product.getVat());
			ps.setString(5, vendorid);
			ps.setDouble(6, qty);
			ps.setString(7, "" + checkedentry);
			ps.setString(8, loginsessionid);
			ps.setString(9, "" + isfrompolist);
			ps.setString(10, datetime);
			ps.setString(11, warehouse);
			ps.setString(12, product_id);
			ps.setString(13, indnetid);
			ps.setString(14, hsnno);
			ps.setString(15, isfromworkorder);
			result = ps.executeUpdate();

			if (result > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				while (rs.next()) {
					result = rs.getInt(1);
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public Product getRequestedPoListByID(String cataloguid, String location, String vendorid) {

		Product product = new Product();
		try {

			StringBuffer buffer = new StringBuffer();
			buffer.append("SELECT temppo.id,catalogueid,prod_id,sum(qty),date,newpo,vendorid,parentid,indent_no ");
			buffer.append("from (SELECT * FROM temp_po ORDER BY id DESC) AS temppo ");
			buffer.append("left join  inventory_catalogue on inventory_catalogue.id = temppo.catalogueid ");
			buffer.append("where temppo.status=0 and temppo.catalogueid in (" + cataloguid
					+ ") and temppo.catalogueid!='0'  ");
			if (!location.equals("0")) {
				buffer.append("and  inventory_catalogue.location=" + location + " ");
			}
			buffer.append("group by catalogueid order by temppo.id desc ");
			String sql = buffer.toString();

			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				product.setId(rs.getInt(1));
				product.setCatalogueid(rs.getString(2));
				product.setProduct_id(rs.getString(3));
				product.setQty(rs.getDouble(4));
				product.setDate(rs.getString(5));
				product.setNewpo(rs.getInt(6));
				if (rs.getInt(6) == 1) {
					product.setVendor_id(rs.getString(7));
				} else {
					product.setVendor_id(vendorid);
				}
				product.setParentid("" + rs.getInt(8));
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return product;

	}

	public ArrayList<Product> getRequestedPoListFromTempData(String loginsessionid, String location, String isfromworkorder) {

		ArrayList<Product> list = new ArrayList<Product>();
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		InventoryVendorDAO inventoryVendorDAO = new JDBCInventoryVendorDAO(connection);
		try {
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -31);
			String fromdate = dateFormat.format(cal.getTime());
			fromdate = DateTimeUtils.getCommencingDate1(fromdate);

			Calendar cal1 = Calendar.getInstance();
			String todate = dateFormat.format(cal1.getTime());
			todate = DateTimeUtils.getCommencingDate1(todate);

			StringBuffer buffer = new StringBuffer();
			buffer.append("SELECT tempoid,catalogueid,productid,req_qty,dateandtime,checkedentry, ");
			buffer.append("grn_with_po_tempdata.supplier,grn_with_po_tempdata.parentid,grn_with_po_tempdata.rate, ");
			buffer.append("grn_with_po_tempdata.gst,grn_with_po_tempdata.id,poHsnno,poDescription ");
			buffer.append("from grn_with_po_tempdata ");
			// buffer.append("inner join inventory_catalogue on
			// inventory_catalogue.id = grn_with_po_tempdata.catalogueid ");
			buffer.append("where grn_with_po_tempdata.catalogueid!='0' and logsessionid='" + loginsessionid + "'  ");
			buffer.append("and isfromworkorder='"+isfromworkorder+"' ");
			if (!location.equals("0")) {
				buffer.append("and  grn_with_po_tempdata.location=" + location + " ");
			}
			buffer.append("order by grn_with_po_tempdata.id desc ");
			

			String sql = buffer.toString();

			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			ArrayList<Vendor> vendorlist = inventoryVendorDAO.getAllVendorList(location);

			while (rs.next()) {
				Product product = new Product();
				product.setPoDescription(DateTimeUtils.isNull(rs.getString(13)));
				product.setPoHsnno(DateTimeUtils.isNull(rs.getString(12)));
				product.setGrn_with_po_tempid(rs.getInt(11));
				product.setId(rs.getInt(1));
				product.setCatalogueid(rs.getString(2));
				product.setProduct_id(rs.getString(3));
				Product master = null;
				if (rs.getInt(2) > 0) {
					master = inventoryProductDAO.getProductCatalogueDetails(product.getCatalogueid());
				} else {
					master = inventoryProductDAO.getProduct(product.getProduct_id());
				}
				double stock = inventoryProductDAO.getStockAvailableNew(product.getCatalogueid(), location);
				product.setStock(Double.toString(stock));
				product.setQty(rs.getDouble(4));
				product.setDate(rs.getString(5).split(" ")[0]);
				product.setMaxorder(master.getMaxorder());
				product.setMinorder(master.getMinorder());
				product.setNewpo(rs.getInt(6));
				product.setProduct_name(master.getProduct_name());
				product.setCatalogueid(master.getCatalogueid());
				product.setVendor_id(rs.getString(7));
				product.setVendorlist(vendorlist);
				product.setUom(master.getUom());
				Product masterlast = getLastProductDetailsOfSupplier(master.getProduct_name(), master.getVendor_id());
				double rate = Double.parseDouble(masterlast.getPurchase_price());
				double purchaserate = 0;
				if (master.getPurchase_price() != null) {
					if (!master.getPurchase_price().equals("")) {
						purchaserate = Double.parseDouble(master.getPurchase_price());
					}
				}
				if (rs.getDouble(9) > 0) {
					purchaserate = rs.getDouble(9);
					product.setPurchase_price(DateTimeUtils.changeFormat(purchaserate));
				} else {
					product.setPurchase_price(DateTimeUtils.changeFormat(rate));
				}
				product.setVat("" + rs.getInt(10));
				product.setFreeqty(master.getFreeqty());

				if (rs.getInt(8) > 0) {
					Product product2 = inventoryProductDAO.getParentTransferData(rs.getString(8));
					product.setFrom_location(product2.getFrom_location());
					product.setReq_userid(product2.getReq_userid());
					product.setIndent(rs.getInt(8));
				} else {
					product.setFrom_location("");
					product.setReq_userid("");
					product.setIndent(rs.getInt(8));
				}

				// lokesh
				if (master.getPro_code() == null) {
					master.setPro_code("");
				}
				product.setPro_code(master.getPro_code());

				double consumed = 0;
				String locationss = "0";
				if (master.getLocation() == null) {
					locationss = "0";
				} else {
					locationss = master.getLocation();
				}

				Product directtransferproductout = inventoryProductDAO
						.getDirectTransferBetweenDate(product.getCatalogueid(), fromdate, locationss, todate);
				Product requesttransferproductout = inventoryProductDAO
						.getRequestTransferBetweenDate(product.getCatalogueid(), fromdate, locationss, todate);
				Product salepatient = inventoryProductDAO.getPatientSaleBetweenDate(product.getCatalogueid(), fromdate,
						todate, locationss);
				Product consume = inventoryProductDAO.getConsumeBetweenDate(product.getCatalogueid(), fromdate, todate,
						locationss);
				consumed = salepatient.getTotalqty() + directtransferproductout.getTotalqty()
						+ requesttransferproductout.getTotalqty() + consume.getTotalqty();
				product.setConsumed(consumed);
				product.setFromdate(DateTimeUtils.getCommencingDate1(fromdate));
				product.setTodate(DateTimeUtils.getCommencingDate1(todate));
				String data=product.getName();
				if(!DateTimeUtils.isNull(product.getSuppliercode()).equals("")){
					data = data +" - "+product.getSuppliercode();
				}
				product.setData(data);
				
				list.add(product);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	public int updateTempGRNPOData(String colname, String tempdatagrnid, String val) {
		int res = 0;
		try {
			String sql = "update grn_with_po_tempdata set " + colname + "=? where id='" + tempdatagrnid + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, val);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int truncateTempPoNew(String loginsessionid, String isfromworkorder) {
		int result = 0;

		try {
			String sql = "delete from grn_with_po_tempdata where logsessionid=? and isfromworkorder=?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, loginsessionid);
			ps.setString(2, isfromworkorder);
			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<Product> getAllVendorOfProduct(String warehouse) {

		ArrayList<Product> list = new ArrayList<Product>();

		try {

			// String sql="select id,name from inventory_vendor where name is
			// not null order by name";
			StringBuffer buffer = new StringBuffer();
			buffer.append("select id,name,suppliercode from inventory_vendor where name is not null ");
			if (!warehouse.equals("0")) {
				buffer.append("and inventory_vendor.location='" + warehouse + "' ");
			}
			buffer.append("");
			PreparedStatement ps = connection.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt(1));
				product.setName(rs.getString(2));
				product.setSuppliercode(rs.getString(3));
				String data=product.getName();
				if(!DateTimeUtils.isNull(product.getSuppliercode()).equals("")){
					data = data +" - "+product.getSuppliercode();
				}
				product.setData(data);
				list.add(product);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}

	public int updateGRNEnteredAmount(String procurementid, String firstnetamout) {
		int res = 0;
		try {
			String sql = "update inventory_parent_procurement set firstnetamout=? where id='" + procurementid + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, firstnetamout);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int saveDeletePoProductLog(Procurement procurement, String userId, String datetime) {
		int res = 0;
		try {
			String sql = "insert delete_po_product_log (userid,datetime,product_name,productid,procid,quantity,parentprocid) values (?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userId);
			preparedStatement.setString(2, datetime);
			preparedStatement.setString(3, procurement.getProduct());
			preparedStatement.setString(4, procurement.getProduct_id());
			preparedStatement.setString(5, "" + procurement.getId());
			preparedStatement.setString(6, procurement.getQuantity());
			preparedStatement.setString(7, procurement.getProcurementid());
			res = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int updatePoDetails(String grnno, String textdata, String columname) {
		int res = 0;
		try {
			String sql = "update inventory_po_grn set "+columname+"=? where id='" + grnno + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, textdata);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public String generateYearPoSequence(String location, boolean lmh, String type, String date1) {
		String seqno="";
		try {
			
			//type=getInvoicePrefix(itype);
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		    Calendar cal = Calendar.getInstance();
		    String todaydate = dateFormat.format(cal.getTime());
		    if(date1!=null){
		    	todaydate = date1;
		    }
		    String endfinancialyeardate="31-03-"+todaydate.split("-")[2];
		    
		    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date today = sdf.parse(todaydate);
            Date lastdayoffinyr = sdf.parse(endfinancialyeardate);
		    String yearrangenew="";
		    if(today.after(lastdayoffinyr)){
		    	int year=Integer.parseInt(todaydate.split("-")[2]);
		    	String yearrange=year+"-"+(year+1);
		    	yearrangenew=yearrange;
		    	
		    }else{
		    	int year=Integer.parseInt(todaydate.split("-")[2]);
		    	String yearrange=(year-1)+"-"+year; 
		    	yearrangenew=yearrange;
		    }
		    int maxSeqNo=getMaxPOSeqNo(yearrangenew,type,location,lmh);
		    //maxSeqNo=maxSeqNo+1;
		    String yearcomb[]=yearrangenew.split("-");
			/*String yr =yearcomb[0].substring(2,4)+"-"+yearcomb[1].substring(2, 4);*/
		    String yr =yearcomb[0]+"-"+yearcomb[1].substring(2,4);
			seqno=type+"/"+yr+"/"+maxSeqNo;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return seqno;
	}

	private int getMaxPOSeqNo(String yearrangenew, String type, String location, boolean lmh) {
		int result=0;
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select sequence from inventory_procurement_sequence ");
			buffer.append("where year='"+yearrangenew+"' and type='"+type+"' ");
			if(!lmh){
				buffer.append("and location='"+location+"' ");
			}
			PreparedStatement ps=connection.prepareStatement(buffer.toString());
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
				result++;
				StringBuffer buffer2 = new StringBuffer();
				buffer2.append("update inventory_procurement_sequence set sequence='"+result+"' ");
				buffer2.append("where year='"+yearrangenew+"' and type='"+type+"' ");
				if(!lmh){
					buffer2.append("and location='"+location+"' ");
				}
				PreparedStatement preparedStatement2 = connection.prepareStatement(buffer2.toString());
				preparedStatement2.executeUpdate();
			}else{
				result =1;
				String querry ="insert into inventory_procurement_sequence(year,type,sequence,location) values(?,?,?,?)";
				PreparedStatement preparedStatement2 = connection.prepareStatement(querry);
				preparedStatement2.setString(1, yearrangenew);
				preparedStatement2.setString(2, type);
				preparedStatement2.setString(3, ""+result);
				preparedStatement2.setString(4, ""+location);
				preparedStatement2.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int updateYearPoSequence(int procurementid, String grnYearSeq) {
		int res =0;
		try {
			String sql="update inventory_parent_procurement set grnYearSeq=? where id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, grnYearSeq);
			preparedStatement.setString(2, ""+procurementid);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int getProcurementEntryCount(String selectedid) {
		int res =0;
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select count(*) from inventory_procurement ");
			buffer.append("where procurementid="+ selectedid + " ");
			PreparedStatement ps = connection.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				res = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int updateProcurementPoDisc(double podisc, String procurementid) {
		int res =0;
		try {
			String sql ="update inventory_parent_procurement set podiscamt='"+podisc+"' where id='"+procurementid+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public boolean checkPayTermsNcondition(String procureid) {
		boolean result = false;
		PreparedStatement ps = null;
		String query = "select * from po_paytermsandcondition where po_id=" + procureid + " ";
		try {
			ps = connection.prepareStatement(query);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int updatePaytoTermsNcondition(String condition, String procureid) {
		int result = 0;
		PreparedStatement ps = null;
		String query = "update po_paytermsandcondition set terms='" + condition + "' where po_id='" + procureid + "'";
		try {
			ps = connection.prepareStatement(query);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int inserPaytoTermsNcondition(String condition, String procureid) {
		int result = 0;
		PreparedStatement ps = null;
		String query = "insert into po_paytermsandcondition(po_id,terms) values(?,?)";
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(procureid));
			ps.setString(2, condition);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String getPaymentTermNCondition(String procurementid) {
		String terms = "";
		PreparedStatement ps = null;
		String query = "select terms from po_paytermsandcondition where po_id=" + procurementid + " ";
		try {
			ps = connection.prepareStatement(query);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				terms = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return terms;
	}

	@Override
	public ArrayList<Procurement> getPendingPoProductList(String location, String voucherno, String fromdate,
			String todate, String vendor_id) {

		ArrayList<Procurement> list = new ArrayList<Procurement>();
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select inventory_procurement.procurementid, inventory_procurement.date, p_userid, catalogueid, ");
			sql.append("purprice,gstper,qty,poHsnno, poDescription, grnYearSeq,inventory_procurement.vendorid ");
			sql.append("from inventory_procurement ");
			sql.append("inner join inventory_parent_procurement on inventory_parent_procurement.id = inventory_procurement.procurementid ");
			sql.append("inner join inventory_po_grn on inventory_po_grn.id = inventory_procurement.grnno ");
			sql.append("where grnno>0 and grnwithpo_child=1 and parentpoid=0 and confirm=1 and ispocomplete!=2 and grnwithpo=1 ");
			sql.append("and inventory_procurement.date between '" + fromdate + "' and '" + todate + "' and inventory_procurement.deleted='0' ");
			if (!location.equals("0")) {
				sql.append("and inventory_procurement.location='" + location + "' ");
			}
			if (voucherno != null) {
				sql.append("and (inventory_procurement.grnno='" + voucherno + "' or grnYearSeq='" + voucherno+ "') ");
			}
			if (vendor_id != null) {
				sql.append("and inventory_procurement.vendorid='" + vendor_id + "' ");
			}
			sql.append("order by inventory_procurement.id desc ");

			PreparedStatement ps = connection.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			InventoryVendorDAO inventoryVendorDAO = new JDBCInventoryVendorDAO(connection);
			String vendor = inventoryVendorDAO.getVendor(vendor_id).getName();
			while (rs.next()) {
				Procurement procurement = new Procurement();
				procurement.setId(rs.getInt(1));
				procurement.setDate(DateTimeUtils.getCommencingDate2(rs.getString(2)));
				procurement.setUserid(rs.getString(3));
				procurement.setCatalogueid(rs.getString(4));
				procurement.setPurchase_price(rs.getString(5));
				procurement.setTtlgst(rs.getInt(6));
				procurement.setQuantity(rs.getString(7));
				procurement.setPoHsnno(rs.getString(8));
				procurement.setPoDescription(rs.getString(9));
				procurement.setGrnYearSeq(rs.getString(10));
				procurement.setVendor_id(rs.getString(11));
				
				Product master = inventoryProductDAO.getProductCatalogueDetails(rs.getString(4));
				procurement.setProduct_code(master.getPro_code());
				procurement.setProduct(master.getProduct_name());
				//procurementid Catalogue 
				int recivedqty = getRecivedQty(rs.getString(1), rs.getString(4));
				int qty = rs.getInt(7);
				if (recivedqty < qty) {
					qty = qty - recivedqty;
				}else{
					continue;
				}
				procurement.setReceived_qty(""+recivedqty);
				procurement.setBalanceqty(""+qty);
				procurement.setVendor(vendor);
				double pedingpoamt=getPendingPoAmt(rs.getInt(1),rs.getString(4),""+qty);
				procurement.setPendingamt(pedingpoamt);
				list.add(procurement);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	
	}

	private double getPendingPoAmt(int procurementid,String catalogueid,String balnceqty) {
		InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
		Product product = new Product();
		try {
			String sql = "select id,prodid,purprice,qty,total,discount,longpo,date,grnno,catalogueid,location,"
					+ "isagreement,child_agreementid,procurementid,vendorid,parentpoid,gstper,poHsnno,"
					+ "poDescription,inventory_procurement.iscancel "
					+ "from inventory_procurement where procurementid=" + procurementid + " and catalogueid="+catalogueid+" order by id desc ";
			
			
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			double subtotal = 0;
			double netsubtotal = 0;
			double totalgstamount = 0;
			while (rs.next()) {
				//Product product = new Product();
				product.setDiscount(DateTimeUtils.changeFormat(rs.getDouble(6)));
				product.setId(rs.getInt(1));
				product.setProduct_id(rs.getString(2));
				product.setQuantity(rs.getString(4));
				product.setNewpo(rs.getInt(7));
				product.setDate(DateTimeUtils.getCommencingDate1(rs.getString(8)));
				product.setGrnno(rs.getInt(9));
				product.setCatalogueid(rs.getString(10));
				Product master = null;
				if (!product.getCatalogueid().equals("0")) {
					master = inventoryProductDAO.getProductCatalogueDetails(product.getCatalogueid());
				} else {
					master = inventoryProductDAO.getProduct(product.getProduct_id());
				}
				// double rate= Double.parseDouble(master.getPurchase_price());
				double rate = rs.getDouble(3);
				product.setProduct_name(master.getProduct_name());
				double pack = Double.parseDouble(master.getPack());
				if (pack == 0) {
					master.setPack("1.0");
				}
				product.setUom(master.getUom());
				product.setPro_code(master.getPro_code());
				//double total = rs.getDouble(4) * rate;
				double total = Double.parseDouble(balnceqty) * rate;
				subtotal = subtotal + total;
				product.setSubTotal(String.format("%.2f", Math.round(subtotal * 100.0) / 100.0));
				product.setTotal(String.format("%.2f", Math.round(total * 100.0) / 100.0));
				product.setMrp(master.getMrp());
				product.setSale_price(master.getSale_price());
				/* product.setPurchase_price(master.getPurchase_price()); */
				product.setPurchase_price(rs.getString(3));
				product.setVendor_id("" + rs.getInt(15));
				product.setBatch_no(master.getBatch_no());
				product.setPack(master.getPack());
				product.setMfg(master.getMfg());
				product.setHsnno(master.getHsnno());

				product.setVat("" + rs.getInt(17));
				product.setSale_price(master.getSale_price());
				// 23 June 2018
				double purchaseprice = rate;
				double vat = rs.getDouble(17);
				/*
				 * if(master.getVat()!=null){ if(!master.getVat().equals("")){
				 * vat = Double.parseDouble(master.getVat()); } }
				 */
				//double qty = rs.getDouble(4);
				double netamount = (purchaseprice * vat) / 100;
				//netamount = netamount * qty;
				netamount = netamount * Double.parseDouble(balnceqty);
				
				double tempgst = netamount * rs.getDouble(6) /100;
				netamount = netamount - tempgst;
				
				netamount = Math.round(netamount * 100.0) / 100.0;
				product.setGstamount(String.format("%.2f", Math.round(netamount * 100.0) / 100.0));
				totalgstamount = totalgstamount + netamount;
				product.setTotalgstamount(String.format("%.2f", Math.round(totalgstamount * 100.0) / 100.0));

				/*double nettotal = netamount + total;*/
				double nettotal = total;
				double temp = (nettotal * rs.getDouble(6)) / 100;
				nettotal = nettotal - temp;
				nettotal = nettotal + netamount;
				product.setNetamount(String.format("%.2f", Math.round(nettotal * 100.0) / 100.0));

				netsubtotal = netsubtotal + nettotal;
				product.setTotalnetamount(String.format("%.2f", Math.round(netsubtotal * 100.0) / 100.0));
				// lokesh
				product.setStockqty(getStock(product.getCatalogueid(), rs.getString(11)));
				double oldpurchaseprice = getoldpurprice(product.getCatalogueid());
				product.setOldvenderpurprise(oldpurchaseprice);
				String location = rs.getString(11);
				if (location == null) {
					location = "0";
				} else {
					if (location.equals("")) {
						location = "0";
					}
				}
				ArrayList<Master> cellList = new ArrayList<Master>();
				ArrayList<Master> newcellList = new ArrayList<Master>();
				if (!location.equals("0")) {
					cellList = inventoryProductDAO.getcellList(location);
					newcellList = inventoryProductDAO.getcellList(location);
				} else {
					cellList = inventoryProductDAO.getcellList("32,33,34,35,36,105");
					newcellList = inventoryProductDAO.getcellList("32,33,34,35,36,105");
				}

				Product productshelf = inventoryProductDAO.getPreviousShelfId(rs.getString(10), location);
				ArrayList<Master> cellList1 = new ArrayList<Master>();
				ArrayList<Master> cellList2 = new ArrayList<Master>();
				for (Master master2 : cellList) {
					if (master2.getId() == productshelf.getPreviouscellid()) {
						master2.setStatus(1);
					} else {
						master2.setStatus(2);
					}
					cellList1.add(master2);
				}
				for (Master master3 : newcellList) {
					if (master3.getId() == productshelf.getPreviousseccellid()) {
						master3.setStatus(1);
					} else {
						master3.setStatus(2);
					}
					cellList2.add(master3);
				}
				product.setShelfList(cellList1);
				product.setSecondshelfList(cellList2);
				product.setPro_code(master.getPro_code());
				product.setIsagreement(rs.getInt(12));
				product.setChildagreeid(rs.getString(13));
				product.setProcurementid(rs.getString(14));
				product.setParentpoid(rs.getInt(16));
				product.setLocation(location);

				double gst = rs.getDouble(17);
				double cgst = 0;
				double sgst = 0;
				if (gst != 0) {
					cgst = gst / 2;
					sgst = cgst;
				}
				product.setCgst("" + cgst);
				product.setSgst("" + sgst);

				product.setPoHsnno(rs.getString(18));
				product.setPoDescription(rs.getString(19));
				product.setDeleted(rs.getInt(20));
				if(!DateTimeUtils.isNull(rs.getString(18)).equals("")){
					product.setHsnno(rs.getString(18));
				}
				

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		
		return Double.parseDouble(product.getNetamount());
	}

	@Override
	public int saveProcurementDataLog(Product product, String qty, int total, String vendorid, String brandid,
			int procurementid, int isagrement, int islongpoprod, String vat) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String sql = "insert into inventory_procurement_log(prodid, purprice, qty, total, mrp,date,vendorid,"
				+ "brandid,procurementid,location,longpo,grnno,catalogueid,discount,child_agreementid,"
				+ "isagreement,islongpoprod,grnwithpo_child,gstper,parentpoid,proc_condition,"
				+ "p_userid,poHsnno,poDescription) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try {

			Date date = Calendar.getInstance().getTime();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String nowdate = dateFormat.format(date);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, product.getId());
			preparedStatement.setString(2, product.getPurchase_price());
			preparedStatement.setString(3, qty);
			preparedStatement.setDouble(4, total);
			preparedStatement.setString(5, product.getMrp());
			preparedStatement.setString(6, nowdate);
			preparedStatement.setString(7, vendorid);
			preparedStatement.setString(8, brandid);
			preparedStatement.setInt(9, procurementid);
			preparedStatement.setString(10, product.getLocation());
			preparedStatement.setInt(11, product.getNewpo());
			preparedStatement.setInt(12, product.getGrnno());
			preparedStatement.setString(13, product.getCatalogueid());
			preparedStatement.setString(14, product.getDiscount());
			preparedStatement.setString(15, product.getChildid());
			preparedStatement.setString(16, "" + isagrement);
			preparedStatement.setString(17, "" + islongpoprod);
			preparedStatement.setString(18, "" + product.getGrnwithpo_child());
			preparedStatement.setString(19, vat);
			preparedStatement.setString(20, "" + product.getParentpoid());
			preparedStatement.setString(21, "" + product.getProc_condition());
			preparedStatement.setString(22, "" + product.getUserid());
			preparedStatement.setString(23, product.getPoHsnno());
			preparedStatement.setString(24, product.getPoDescription());

			result = preparedStatement.executeUpdate();
			if (result > 0) {

				ResultSet set = preparedStatement.getGeneratedKeys();
				while (set.next()) {
					result = set.getInt(1);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
		
	}

	@Override
	public int updateDiscQtyLog(String qty, String discount, int id, String rate, String vat, String poDescription,
			String poHsnno, String userid) {
		int res = 0;
		try {
			String sql = "update inventory_procurement_log set qty=?, discount=?, purprice=?, gstper=?,"
					+ "poDescription=?,poHsnno=?,updated_by=? where id='" + id
					+ "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, qty);
			preparedStatement.setString(2, discount);
			preparedStatement.setString(3, rate);
			preparedStatement.setString(4, vat);
			preparedStatement.setString(5, poDescription);
			preparedStatement.setString(6, poHsnno);
			preparedStatement.setString(7, userid);
			res = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;

	}

	@Override
	public ArrayList<Procurement> getvendorrate(String fromDate, String toDate,String product) {
		ArrayList<Procurement> list = new ArrayList<Procurement>();
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select inventory_procurement.catalogueid from inventory_procurement inner join inventory_product on inventory_product.id=inventory_procurement.prodid where date between '" + fromDate +"' and '" + toDate + "' and gudreceipt='1'  ");
			
			if (!product.equals("")) {
				sql.append("and prodname like ('%" + product + "%') ");
			}
			sql.append("group by inventory_procurement.catalogueid") ;
			PreparedStatement ps = connection.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			InventoryProductDAO inventoryProductDAO = new JDBCInventoryProductDAO(connection);
			
			
			while (rs.next()) {
				Procurement procurement = new Procurement();
				String productname = getproductName(rs.getString(1));
				procurement.setProduct_id(productname);
				
				Vector<Procurement> vendorList = getVendorListByproduct(rs.getString(1),fromDate,toDate);
				procurement.setVendorlistbyproduct(vendorList);
				
				list.add(procurement);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
		
	}


	private String getproductName(String productid) {
		String productname = "";
		PreparedStatement ps = null;
		String query = "select product_name from inventory_catalogue where id=" + productid + " ";
		try {
			ps = connection.prepareStatement(query);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				productname = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productname;
		
	}

	private Vector<Procurement> getVendorListByproduct(String product_id,String fromDate, String toDate) {
		PreparedStatement preparedStatement = null;
		Vector<Procurement>list = new Vector<Procurement>();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT vendorid FROM inventory_procurement where catalogueid = "+product_id+" group by vendorid ");		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				Procurement procurement = new Procurement();
				String vendorname = getvendorName(rs.getString(1));
				procurement.setVendor_id(vendorname);
				
				
				Vector<Procurement> rateList = getratelistByProduct(rs.getString(1),product_id,fromDate,toDate);
				procurement.setRatelistbyvendor(rateList);
				
				list.add(procurement);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	private String getvendorName(String vendorid) {
		String vendorname = "";
		PreparedStatement ps = null;
		String query = "select name from inventory_vendor where id=" + vendorid + " ";
		try {
			ps = connection.prepareStatement(query);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				vendorname = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vendorname;
  
	}
	private Vector<Procurement> getratelistByProduct(String vendor_id,String catalogueid,String fromDate, String toDate) {
		PreparedStatement preparedStatement = null;
		Vector<Procurement>list = new Vector<Procurement>();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT date,purprice FROM inventory_procurement where vendorid = "+vendor_id+" and date between '" + fromDate +"' and '" + toDate + "' and catalogueid ='"+catalogueid+"' and gudreceipt=1");		
		try{
			preparedStatement = connection.prepareStatement(sql.toString());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				
				Procurement procurement = new Procurement();
				procurement.setDate(rs.getString(1));
				procurement.setPurchase_date(rs.getString(2));
				
				
				
				list.add(procurement);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
		
	}
	
}
